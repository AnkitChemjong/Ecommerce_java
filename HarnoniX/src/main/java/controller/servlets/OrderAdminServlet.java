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

@WebServlet(asyncSupported = true, urlPatterns = { "/OrderAdminServlet" })
public class OrderAdminServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private DatabaseController dbController = new DatabaseController();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        HttpSession session = request.getSession(false);

//        if (session == null || session.getAttribute("admin_email") == null) {
//            response.sendRedirect(request.getContextPath() + "pages/login.jsp");
//            return;
//        }

        try {
         
            List<ModelOrder> orders = dbController.getAllOrders();

            // Forward the orders to a JSP page for display
            request.setAttribute("orders", orders);
            request.getRequestDispatcher("/pages/showAdminOrder.jsp").forward(request, response);
        } catch (SQLException | ClassNotFoundException e) {
            // Handle database and class not found errors
            request.setAttribute("message", "Failed to retrieve orders: " + e.getMessage());
            request.getRequestDispatcher("/pages/message.jsp").forward(request, response);
        }
    }
}
