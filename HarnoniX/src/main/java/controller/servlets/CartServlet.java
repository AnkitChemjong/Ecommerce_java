package controller.servlets;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import controller.DatabaseController;
import model.CartItemModel;
import utils.StringUtils;

import java.io.IOException;
import java.sql.*;
import java.util.List;

@WebServlet(name = "CartServlet", urlPatterns = {StringUtils.ADD_CART_SERVLET})
public class CartServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final DatabaseController dbController = new DatabaseController();
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String customerEmail = (String) session.getAttribute("customer_mail"); 

        // Check if user is logged in
        if (customerEmail == null) {
            // User is not logged in, set message and forward to product page
            request.setAttribute("messageTitle", "Login Required");
            request.setAttribute("message", "Login is required to add products to the cart.");
            request.setAttribute("backLink", request.getContextPath() + "/pages/product.jsp");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/message.jsp");
            dispatcher.forward(request, response);
            return;
        }

        try {
            int id = dbController.getCustomerIdByEmail(customerEmail);
            Integer prod_id = Integer.parseInt(request.getParameter("prod_id"));
            int quantity = 1;

            int result = dbController.addToCart(id, prod_id, quantity);
            switch (result) {
                case 0: // Success
                    request.setAttribute("messageTitle", "Success");
                    request.setAttribute("message", "Item successfully added to your cart!");
                    break;
                case 1: // Already exists
                    request.setAttribute("messageTitle", "Notice");
                    request.setAttribute("message", "Item already exists in your cart.");
                    break;
                default: // Error
                    request.setAttribute("messageTitle", "Error");
                    request.setAttribute("message", "An unexpected error occurred.");
                    break;
            }
            request.setAttribute("backLink", request.getContextPath() + "/pages/cart.jsp");
        } catch (NumberFormatException | SQLException e) {
            request.setAttribute("messageTitle", "Error");
            request.setAttribute("message", "Error processing request: " + e.getMessage());
        } catch (Exception e) {
            request.setAttribute("messageTitle", "Error");
            request.setAttribute("message", "An unexpected error occurred due to an internal issue.");
            e.printStackTrace(); // Log to console or log files
        } finally {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/message.jsp");
            dispatcher.forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String customerEmail = (String) session.getAttribute("customer_mail");

        // Check if user is logged in
        if (customerEmail == null) {
         // User is not logged in, set message and forward to product page
            request.setAttribute("messageTitle", "Login Required");
            request.setAttribute("message", "Login is required to add products to the cart.");
            request.setAttribute("backLink", request.getContextPath() + "/pages/product.jsp");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/message.jsp");
            dispatcher.forward(request, response);
            return;  
        }

        try {
            int id = dbController.getCustomerIdByEmail(customerEmail); // Fetch userId using email
            List<CartItemModel> cartItems = dbController.getCartItems(id);
            request.setAttribute("cartItems", cartItems);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/cart.jsp");
            dispatcher.forward(request, response);
        } catch (SQLException e) {
            request.setAttribute("errorMessage", "Database error: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "An unexpected error occurred.");
        }
    }
}