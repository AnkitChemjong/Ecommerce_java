package controller.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import controller.DatabaseController;
import model.ProductModel;
import utils.StringUtils;

@WebServlet("/UpdateProductServlet")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
maxFileSize = 1024 * 1024 * 10, // 10MB
maxRequestSize = 1024 * 1024 * 50) // 50MB)
public class UpdateProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public UpdateProductServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int prod_id = Integer.parseInt(request.getParameter("prod_id"));
		if (prod_id != 0) {
			DatabaseController dbController = new DatabaseController();
			ProductModel product = dbController.getProductByName(prod_id);
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

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("sgdahghjdaghadgaddadaukh");
		String prod_name = request.getParameter("prod_name");
		System.out.println("P NAme: " + prod_name);
		String prod_desc = request.getParameter("prod_desc");
		System.out.println("P Brand: " + prod_desc);
		int prod_quantity = Integer.parseInt(request.getParameter("prod_quantity"));
		System.out.println("P Quantity: " + prod_quantity);
		int prod_price = Integer.parseInt(request.getParameter("prod_price"));
		System.out.println("P Price: " + prod_price);
		String prod_type = request.getParameter("prod_type");
		System.out.println("P Brand: " + prod_type);
		String prod_quality = request.getParameter("prod_quality");
		System.out.println("P Brand: " + prod_quality);;
		Part imagePart = request.getPart("image_url");
		System.out.println("P Image: " + imagePart);
		int prod_id = Integer.parseInt(request.getParameter("prod_id"));
		
		

		// Update the product details in the database
		DatabaseController dbController = new DatabaseController();
		ProductModel productmodel = new ProductModel(prod_name, prod_quantity,prod_price,prod_desc, prod_type,prod_quality,imagePart);
		int result = dbController.updateProduct(productmodel , prod_id);
		String savePath = StringUtils.IMAGE_DIR;
		String fileName = productmodel.getProductImage();
		
		
		if (!fileName.isEmpty() && fileName != null) {
			imagePart.write(savePath + fileName);
   	}
	        
		

		if (result > 0) {
			// Product details updated successfully
			response.sendRedirect(request.getContextPath() + "/AdminServlet"); // Redirect to product list
																								// page
		} else {
			// Error occurred while updating product details
			response.getWriter().println("Error updating product details.");
		}
	}

}
