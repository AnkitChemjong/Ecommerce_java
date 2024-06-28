<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="model.ProductModel"%>

<%
request.getSession(false);
if (session.getAttribute("admin_mail") == null && session.getAttribute("customer_mail") != null) {
	response.sendRedirect(request.getContextPath() + "/manageDisplayServlet");
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin Dashboard - Product Management</title>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
 <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/stylesheet/mainadmin.css">

</head>
<body>

   <nav>
        <div class="container-nav">
          <img src="${pageContext.request.contextPath}/resources/images/logo.png" alt="Logo">
            <div class="navigation">
  
              <ul>
        
                <li><a href="${pageContext.request.contextPath}/pages/addproduct.jsp">Add Product</a></li>
                <li><a href="${pageContext.request.contextPath}/AdminServlet">View Products</a></li>
                <li><a href="${pageContext.request.contextPath}/OrderAdminServlet">View Orders</a></li>
                  <li><a href="${pageContext.request.contextPath}/pages/Dashboard.jsp">Dashboard</a></li>
            </ul>
        </div>
     
        </div>
    </nav>
	<div class="container">
		<div class="content">
			<h1>Product Management</h1>
			<table>
				<thead>
					<tr>
						<th>Product ID</th>
						<th>Name</th>
						<th>Description</th>
						<th>Price</th>
						<th>Image</th>
						<th>Quantity</th>
						<th>Quality</th>
						<th>Type</th>
						<th>Actions</th>
					</tr>
				</thead>
				<tbody>
					<%
					List<ProductModel> products = (List<ProductModel>) request.getAttribute("products");
					if (products != null && !products.isEmpty()) {
						for (ProductModel product : products) {
					%>
					<tr>
						<td><%=product.getprodid()%></td>
						<td><%=product.getProductName()%></td>
						<td><%=product.getProductDesc()%></td>
						<td>$<%=product.getProductPrice()%></td>
						<td><img src="${pageContext.request.contextPath}/resources/images/<%= product.getProductImage() %>" alt="Product Image" class="small-image" /></td>
						<td><%= product.getProductQuantity()%></td>
						<td><%= product.getProductQuality()%></td>
						<td><%= product.getProductType()%></td>
						<td> <form action="${pageContext.request.contextPath}/GetProductServlet" method="post">
    						<input type="hidden" name="prod_id" value="<%=product.getprodid()%>">
   							<button type="submit" class="btn">Edit</button>
						</form>
						
							<form method="post"
								action="${pageContext.request.contextPath}/EditProductServlet">
								<input type="hidden" name="deleteId"
									value="<%=product.getprodid()%>">
								<button type="submit" class="btn">Delete</button>
							</form>
						</td>
					</tr>
					<%
					}
					} else {
					%>
					<tr>
						<td colspan="5">No products found.</td>
					</tr>
					<%
					}
					%>
				</tbody>
			</table>
		</div>
	</div>