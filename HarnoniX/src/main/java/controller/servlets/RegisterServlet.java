package controller.servlets;

import java.io.IOException;

import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import controller.DatabaseController;
import model.CustomerModel;
import utils.StringUtils;

/**
 * @author Sujan
 */
@WebServlet(asyncSupported = true, urlPatterns = { StringUtils.REGISTER_SERVLET })
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
		maxFileSize = 1024 * 1024 * 10, // 10MB
		maxRequestSize = 1024 * 1024 * 50) // 50MB)
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	DatabaseController dbController = new DatabaseController();

	public RegisterServlet() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String firstName = request.getParameter(StringUtils.FIRST_NAME);
		String lastName = request.getParameter(StringUtils.LAST_NAME);
		String userName = request.getParameter(StringUtils.USERNAME);
		String email = request.getParameter(StringUtils.EMAIL);
		String address = request.getParameter(StringUtils.ADDRESS);
		String password = request.getParameter(StringUtils.PASSWORD);
		String retypePassword = request.getParameter(StringUtils.RETYPE_PASSWORD);;
		String role = "user";
    	Part image = request.getPart(StringUtils.IMAGE);
    	  
     
		
    

		CustomerModel customerModel = new CustomerModel(firstName, lastName,userName, email, address,password,role,image);
		
		   
	    	String savePath = StringUtils.IMAGE_DIR;
			String fileName = customerModel.getImage();
			
			
			if (!fileName.isEmpty() && fileName != null) {
				image.write(savePath + fileName);
	   	}
			

		int result = dbController.addCustomer(customerModel);

		if (password.equals(retypePassword)) {
			switch (result) {
				case 1 -> {
					request.setAttribute(StringUtils.SUCCESS_MESSAGE, StringUtils.SUCCESS_REGISTER_MESSAGE);
					response.sendRedirect(request.getContextPath() + StringUtils.LOGIN_PAGE);
				}
				case 0 -> {
					request.setAttribute(StringUtils.ERROR_MESSAGE, StringUtils.ERROR_REGISTER_MESSAGE);
					request.getRequestDispatcher(StringUtils.REGISTER_PAGE).forward(request, response);
				}
				case -1 -> {
					request.setAttribute(StringUtils.ERROR_MESSAGE, StringUtils.SERVER_ERROR_MESSAGE);
					request.getRequestDispatcher(StringUtils.REGISTER_PAGE).forward(request, response);
				}
				case -2 -> {
					request.setAttribute(StringUtils.ERROR_MESSAGE, StringUtils.USERNAME_ERROR_MESSAGE);
					request.getRequestDispatcher(StringUtils.REGISTER_PAGE).forward(request, response);
				}
				case -3 -> {
					request.setAttribute(StringUtils.ERROR_MESSAGE, StringUtils.EMAIL_ERROR_MESSAGE);
					request.getRequestDispatcher(StringUtils.REGISTER_PAGE).forward(request, response);
				}
				case -4 -> {
					request.setAttribute(StringUtils.ERROR_MESSAGE, StringUtils.PHONE_NUMBER_ERROR_MESSAGE);
					request.getRequestDispatcher(StringUtils.REGISTER_PAGE).forward(request, response);
				}
				default -> {
					request.setAttribute(StringUtils.ERROR_MESSAGE, StringUtils.SERVER_ERROR_MESSAGE);
					request.getRequestDispatcher(StringUtils.REGISTER_PAGE).forward(request, response);
				}
			}
		} else {
			request.setAttribute(StringUtils.ERROR_MESSAGE, StringUtils.PASSWORD_UNMATCHED_ERROR_MESSAGE);
			request.getRequestDispatcher(StringUtils.REGISTER_PAGE).forward(request, response);
		}
	}
}
