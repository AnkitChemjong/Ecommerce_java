package controller.servlets;

import javax.servlet.*;
import javax.servlet.http.*;

import controller.DatabaseController;

import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "ModifyCartServlet", urlPatterns = {"/ModifyCartServlet"})
public class ModifyCartServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private DatabaseController dbController = new DatabaseController();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String updateId = request.getParameter("updateId");
        String deleteId = request.getParameter("deleteId");

        if (updateId != null && !updateId.isEmpty()) {
        
            doPut(request, response);
        } else if (deleteId != null && !deleteId.isEmpty()) {
     
            doDelete(request, response);
        }
    }
    
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String deleteId = request.getParameter("deleteId");
        if (deleteId != null && !deleteId.isEmpty()) {
            try {
                // Handle delete action here
                int cartId = Integer.parseInt(deleteId);
             
                if (dbController.removeFromCart(cartId)) {
              
                    request.setAttribute("successMessage", "Item removed from cart successfully.");
                } else {
                 
                    request.setAttribute("errorMessage", "Failed to remove item from cart.");
                }
            } catch (NumberFormatException e) {
           
                request.setAttribute("errorMessage", "Invalid product ID format.");
            } catch (SQLException | ClassNotFoundException e) {
            
                request.setAttribute("errorMessage", "Database error: " + e.getMessage());
            }
         
            request.getRequestDispatcher("/pages/message.jsp").forward(request, response);
        }
    }
}
