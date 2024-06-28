package controller.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.DatabaseController;
import model.ProductModel;
import utils.StringUtils;

@WebServlet(asyncSupported = true, urlPatterns = {StringUtils.DISPLAY_SERVLET})
public class DisplayServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private DatabaseController dbController = new DatabaseController();

    public DisplayServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	  List<ProductModel> products = dbController.fetchAllProducts();
          request.setAttribute("products", products);
          request.getRequestDispatcher("/pages/landing.jsp").forward(request, response);
        
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	// Fetch all product details from the database
        List<ProductModel> products = dbController.fetchAllProducts();
        req.setAttribute("products", products);
        req.getRequestDispatcher("/pages/product.jsp").forward(req, resp);
    }
}
