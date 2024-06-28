package controller.servlets;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import controller.DatabaseController;
import model.ModelOrder;
import utils.StringUtils;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(asyncSupported = true, urlPatterns = {StringUtils.ORDER_SERVLET})
public class OrderServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    DatabaseController dbController = new DatabaseController();
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("customer_mail") == null) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }

        String customerEmail = (String) session.getAttribute("customer_mail");
        try {
            int cartId = Integer.parseInt(request.getParameter("cartId"));
            int prod_id = Integer.parseInt(request.getParameter("prod_id"));
            int quantity = Integer.parseInt(request.getParameter("quantity")); 
            String status = "pending";
            try {
                int id = dbController.getCustomerIdByEmail(customerEmail);

             
                dbController.orderPlaced(id,status, prod_id, quantity); // Assumes this method is updated to handle quantity
           
                dbController.removeFromCart(cartId);

             
                request.setAttribute("message", "Order placed successfully.");
                request.getRequestDispatcher("/pages/message.jsp").forward(request, response);
            } catch (SQLException | ClassNotFoundException e) {

                request.setAttribute("message", "Failed to place order: " + e.getMessage());
                request.getRequestDispatcher("/pages/message.jsp").forward(request, response);
            }
        } catch (NumberFormatException e) {
            // Handle parsing errors
            request.setAttribute("message", "Invalid input for cart ID, product ID, or quantity.");
            request.getRequestDispatcher("/pages/message.jsp").forward(request, response);
        }
    }
    
    
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
       

        String customerEmail = (String) session.getAttribute("customer_mail");
        try {
        	 int id = dbController.getCustomerIdByEmail(customerEmail);
        	 
            // Retrieve orders for the logged-in user
            List<ModelOrder> orders = dbController.fetchOrderdetail(id);
            

            // Forward the orders to a JSP page for display
            request.setAttribute("orders", orders);
            request.getRequestDispatcher("/pages/Order.jsp").forward(request, response);
        } catch (SQLException | ClassNotFoundException e) {
            // Handle database and class not found errors
            request.setAttribute("message", "Failed to retrieve orders: " + e.getMessage());
            request.getRequestDispatcher("/pages/message.jsp").forward(request, response);
        }
    }
}
