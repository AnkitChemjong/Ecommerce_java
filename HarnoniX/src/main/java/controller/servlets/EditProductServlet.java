package controller.servlets;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import utils.StringUtils;
import model.ProductModel;
import controller.DatabaseController;

@WebServlet(asyncSupported = true, urlPatterns = { "/EditProductServlet" })
public class EditProductServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private DatabaseController dbController;

    public EditProductServlet() {
        super();
        // Initialize the database controller here, which handles all database operations
        this.dbController = new DatabaseController();
    }

   

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String S_updateId = request.getParameter("productId");
        String S_deleteId = request.getParameter("deleteId");

  
        try {
            if (S_updateId != null && !S_updateId.isEmpty()) {
                int updateId = Integer.parseInt(S_updateId);
                doPut(request, response, updateId);
            } else if (S_deleteId != null && !S_deleteId.isEmpty()) {
                int deleteId = Integer.parseInt(S_deleteId);
                doDelete(request, response, deleteId);
            } else {
                response.sendRedirect("/pages/message.jsp");
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid ID format");
        }
    }

    protected void doPut(HttpServletRequest request, HttpServletResponse response, int prod_id)
            throws ServletException, IOException {
        try {
        	
            String productName = request.getParameter("prod_name");
            String productDescription = request.getParameter("prod_desc");
            Part imagePart = request.getPart("prod_image"); 
            int productPrice = Integer.parseInt(request.getParameter("prod_price"));
            String productQuality = request.getParameter("prod_quality");
            String productType = request.getParameter("prod_type");
            int productQuantity = Integer.parseInt(request.getParameter("prod_quantity"));
           

            ProductModel product = new ProductModel(productName, productQuantity,productPrice,productDescription,productType,productQuality,imagePart);

            dbController.editProduct(prod_id, product);
            response.sendRedirect(StringUtils.ADMIN_SERVLET);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Failed to update product due to invalid input or server error.");
        }
    }

    protected void doDelete(HttpServletRequest request, HttpServletResponse response, int prod_id)
            throws ServletException, IOException {
        try {
            dbController.deleteProduct(prod_id);
             response.sendRedirect(request.getContextPath() + "/AdminServlet"); 
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error deleting product");
        }
    }
}


