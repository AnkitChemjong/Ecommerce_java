package controller.servlets;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import java.io.IOException;

import model.ProductModel;
import utils.StringUtils;
import controller.DatabaseController;

@WebServlet(asyncSupported = true, urlPatterns = { StringUtils.INDIVIDUAL_PRODUCT_SERVLET})
public class EachProductServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String Prod_Id_Get= request.getParameter("id");
        if (Prod_Id_Get != null) {
            try {
                int prod_id = Integer.parseInt(Prod_Id_Get);
                DatabaseController dbController = new DatabaseController();
                ProductModel product = dbController.getProdById(prod_id);

                if (product != null) {
                    request.setAttribute("product", product);
                    request.getRequestDispatcher("/pages/eachproduct.jsp").forward(request, response);
                } else {
                    response.sendRedirect("errorPage.jsp?message=Product not found");
                }
            } catch (NumberFormatException e) {
                response.sendRedirect("errorPage.jsp?message=Id Invalid");
            } catch (Exception e) {
                e.printStackTrace();
                response.sendRedirect("errorPage.jsp?message=Server Error");
            }
        } else {
            response.sendRedirect("errorPage.jsp?message=Missing product id");
        }
    }
}