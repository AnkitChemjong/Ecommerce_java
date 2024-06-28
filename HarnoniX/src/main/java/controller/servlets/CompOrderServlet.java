package controller.servlets;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import controller.DatabaseController;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(asyncSupported = true, urlPatterns = { "/CompOrderServlet" })
public class CompOrderServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private DatabaseController dbController = new DatabaseController();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String orderIdStr = request.getParameter("order_id");

        try {
            int order_id = Integer.parseInt(orderIdStr);
            dbController.OrderAsCompleted(order_id);
            response.sendRedirect(request.getContextPath() + "/OrderAdminServlet");
        } catch (SQLException | ClassNotFoundException | NumberFormatException e) {
            request.setAttribute("message", "Failed to mark order as completed: " + e.getMessage());
            request.getRequestDispatcher("/pages/message.jsp").forward(request, response);
        }
    }
}
