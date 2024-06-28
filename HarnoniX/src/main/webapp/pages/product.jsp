<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="model.ProductModel"%>



 <!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Home Page</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css" integrity="sha512-DTOQO9RWCH3ppGqcWaEA1BIZOC6xxalwEsw9c2QQeAIftl+Vegovlnee1c9QX4TctnWMn13TZye+giMm8e2LwA==" crossorigin="anonymous" referrerpolicy="no-referrer" />
   <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/stylesheet/nav.css">
   <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/stylesheet/product.css">
</head>
<body>
    <div class="navbar">
   
     
            <img src="${pageContext.request.contextPath}/resources/images/logo.png" alt="Logo">
            <div class="links">
                 
                <a href="<%=request.getContextPath()%>/HomeServlet">Product</a>
                <a href="<%=request.getContextPath()%>/OrderServlet"><i class="fa-solid fa-truck-fast"></i>Orders</a>
                <a href="${pageContext.request.contextPath}/CartServlet"><i class="fa-solid fa-cart-shopping"></i>Cart</a>
               <a href="${pageContext.request.contextPath}/pages/userProfile.jsp"><i class="fa-solid fa-user"></i>Profile</a>
 
            </div>
			<form action="${pageContext.request.contextPath}/SearchServlet" method="get">
			        <input type="text" placeholder="Search products..." name="search">
			        <button type="submit" >
			           <i class="fa-solid fa-magnifying-glass"></i>
			        </button>

       </form>
    </div>







<section class="collections">
		<%
		List<ProductModel> products = (List<ProductModel>) request.getAttribute("products");
		if (products != null && !products.isEmpty()) {
			for (ProductModel product : products) {
		%>

	<div class="collection">
    <a href="<%=request.getContextPath()%>/pages/eachproduct.jsp?id=<%=product.getprodid()%>" class="product-link">
        <img src="<%=request.getContextPath()%>/resources/images/<%=product.getProductImage()%>" alt="<%=product.getProductName()%>" class="product-img">
        <div class="details">
            <h4 class="product-heading"><%=product.getProductName()%></h4>
            <p class="product-price">Price: $<%=product.getProductPrice()%></p>
        </div>
    </a>
    <form action="${pageContext.request.contextPath}/CartServlet" method="POST">
        <input type="hidden" name="prod_id" value="<%=product.getprodid()%>">
        <input type="hidden" name="quantity" value="1">
        <button class="btn-cartadd">Add to Cart <i class="fa-solid fa-cart-shopping"></i></button>
    </form>
</div>
		<%
		}
		} else {
		%>
		<p>No products found.</p>
		<%
		}
		%>
	</section>

</body>
</html>