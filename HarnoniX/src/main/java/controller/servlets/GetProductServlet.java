package controller.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.DatabaseController;
import model.ProductModel;


@WebServlet("/GetProductServlet")
public class GetProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GetProductServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		int selectedProductId = Integer.parseInt(request.getParameter("prod_id"));
		System.out.println(selectedProductId);
		System.out.println("Selected Product Name: " + selectedProductId);

		if (selectedProductId != 0) {
			DatabaseController dbController = new DatabaseController();
			ProductModel product = dbController.getProductByName(selectedProductId);
			if (product != null) {
				request.setAttribute("product", product);
				request.getRequestDispatcher("/pages/editProduct.jsp").forward(request, response);
			} else {
				response.getWriter().println("Product not found.");
			}
		} else {
			response.getWriter().println("No product selected.");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}