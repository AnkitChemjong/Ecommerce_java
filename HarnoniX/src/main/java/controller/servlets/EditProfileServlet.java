package controller.servlets;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import controller.DatabaseController;
import model.CustomerModel;
import utils.StringUtils;

@WebServlet(name = "EditProfileServlet", urlPatterns = {StringUtils.UPDATE_PROFILE})
@MultipartConfig(
    fileSizeThreshold = 2097152,    // 2 MB
    maxFileSize = 10485760L,        // 10 MB
    maxRequestSize = 52428800L      // 50 MB
)
public class EditProfileServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private DatabaseController dbController = new DatabaseController();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPut(request, response);
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String customerEmail = (String) session.getAttribute("customer_mail");

        System.out.println("Session User Email: " + customerEmail);

        try {
            int id = dbController.getCustomerIdByEmail(customerEmail);
            if (id == -1) {
                throw new Exception("User not found with email: " + customerEmail);
            }

            System.out.println("User ID Retrieved: " + id);

            // Retrieve form data
            String userName = request.getParameter("userName");
            String email = request.getParameter("email");
            String address = request.getParameter("address");
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
           

            System.out.println("Form Data - Username: " + userName);
            System.out.println("Form Data - Email: " + email);
            System.out.println("Form Data - Address: " + address);
            Part imagePart = request.getPart("userImage"); // Image part from the form
            String imageName = imagePart.getSubmittedFileName(); // Get the image name

            System.out.println("Image File Name: " + imageName);

            CustomerModel customer = new CustomerModel(id,firstName,lastName,userName, email, address,"role", imageName);
            boolean updateSuccess = dbController.updateUserData(customer);
            if (updateSuccess) {
                System.out.println("Update Successful, Redirecting...");
                response.sendRedirect(request.getContextPath() + "/FetchProductsServlet"); // Redirect on successful update
            } else {
                throw new Exception("Database update failed - No rows affected.");
            }
        } catch (Exception e) {
            e.printStackTrace();  // Print the full stack trace to logs
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Internal server error: " + e.getMessage());
        }
    }
}
