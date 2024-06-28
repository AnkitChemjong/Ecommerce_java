
package controller.servlets;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;

import controller.DatabaseController;
import model.ProductModel;
import utils.StringUtils;

@WebServlet(asyncSupported = true, urlPatterns = { StringUtils.ADD_PRODUCT })
@MultipartConfig(
    fileSizeThreshold = 2097152, 
    maxFileSize = 10485760L,     
    maxRequestSize = 52428800L   
)
public class ProductServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final DatabaseController db = new DatabaseController();

    public ProductServlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	 response.setContentType("text/html;charset=UTF-8");
        try {
            String prodName = request.getParameter(StringUtils.PRODUCT_NAME);
            int prodQuantity = Integer.parseInt(request.getParameter(StringUtils.QUANTITY));
            int prodPrice = Integer.parseInt(request.getParameter(StringUtils.PRICE));
            String prodDescription = request.getParameter(StringUtils.DESCRIPTION);//
            String prodQuality= request.getParameter(StringUtils.QUALITY);
            String prodType = request.getParameter(StringUtils.TYPE);
            Part prodImage = request.getPart(StringUtils.IMAGE_P);
            
           
      
            if (prodName == null || prodDescription == null || prodType==null|| prodImage == null ) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Not enough details");
                return;
            }

            ProductModel model = new ProductModel(prodName,prodQuantity,prodPrice,prodDescription,prodQuality,prodType,prodImage);
            
        	String savePath = StringUtils.IMAGE_DIR;
    		String fileName = model.getProductImage();
    		
    		
    		if (!fileName.isEmpty() && fileName != null) {
    			prodImage.write(savePath + fileName);
       	}
    		
            int prod_id= db.addProduct(model);
            switch (prod_id) {
            
            case 1: 
                request.setAttribute("messageTitle", "Notice");
                request.setAttribute("message", "Product Sucessfully added");
                break;
            default: // Error
                request.setAttribute("messageTitle", "Error");
                request.setAttribute("message", "An unexpected error occurred.");
                break;
        }
            request.setAttribute("backLink", request.getContextPath() + "/pages/Dashboard.jsp");


        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid format for the price.");
        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Server error.");
        } finally {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/messageAddProduct.jsp");
            dispatcher.forward(request, response);
        }
    }
}