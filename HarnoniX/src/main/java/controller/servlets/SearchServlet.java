package controller.servlets;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import controller.DatabaseController;
import model.ProductModel;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "SearchServlet", urlPatterns = { "/SearchServlet" })
public class SearchServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    DatabaseController dbController = new DatabaseController();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String searchQuery = request.getParameter("search");

        try {
            List<ProductModel> searchResults = dbController.searchProducts(searchQuery);
            request.setAttribute("searchResults", searchResults);
            request.getRequestDispatcher("/pages/Search.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Error processing search: " + e.getMessage());
            request.getRequestDispatcher("/pages/message.jsp").forward(request, response);
        }
    }
}