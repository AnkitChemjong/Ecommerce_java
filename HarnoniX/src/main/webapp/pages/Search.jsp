<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="model.ProductModel"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
         <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/stylesheet/search.css">
    <title>Search Results</title>

     <style>
     @charset "UTF-8";



.container {
    max-width: 1200px;
    margin: 0 auto;
    padding: 0 20px;
}

h1 {
    text-align: center;
    margin-top: 20px;
}


.collections {
    display: flex;
    flex-wrap: wrap;
    justify-content: center;
}

.collection {
    margin: 20px;
    padding: 20px;
    background-color: #fff;
    border-radius: 5px;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    width: 250px;
}

.product-img {
    max-width: 100%;
    height: auto;
    border-radius: 5px;
}

.details {
    padding: 10px 0;
}

.details h4 {
    margin: 0;
}

.details p {
    margin: 5px 0;
}

.btn-cartadd {
    background-color: #007bff;
    color: #fff;
    border: none;
    padding: 8px 15px;
    border-radius: 5px;
    cursor: pointer;
    margin-left:70px;
    transition: background-color 0.3s ease;
}

.btn-cartadd:hover {
    background-color: #0056b3;
}

.navbar{
	width:100%;
	background:azure;
	height:80px;
	margin-bottom:20px;
	display:flex;
	justify-content:center;
	align-item:center;
}

.navbar>img{
	position:relative;
	right:250px;
}
form {
    position:relative;
    right:30px;
    
}
.navbar>form>input{
	border-radius:20px;
	width:300px;
	position:relative;
	text-align:center;
	top:20px;
	left:380px;
	
}
.navbar>form>button{
	border-radius:20px;
	position:relative;
	left:395px;
	width:0px;
	height:0px;
	background-color:white;
	top:30px;
}
.navbar>form>button>i{
	font-size:20px;
	position:relative;
	bottom:8px;
}
input[type="text"] {
    padding: 8px;
    width: 20%;
    min-width: 200px;
}


button[type="submit"] {
    padding: 10px 20px;
    border: none;
    cursor: pointer;
}

.links {
   position:relative;
   top:30px;
   left:30px;
}

.links a {
    margin-right: 20px;
    margin-left:20px;
    text-decoration: none;
    color: #000;
}
.links a:hover {
    color: #007bff; /* Change color on hover */
}


     
     
     </style>
 
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

    </div>

    <h1 style="text-align: center; margin-top: 20px;">Search Results</h1>

    <section class="collections">
        <% 
        List<ProductModel> products = (List<ProductModel>) request.getAttribute("searchResults");
        if (products != null && !products.isEmpty()) {
            for (ProductModel product : products) {
        %>
        <div class="collection">
                 <a href="<%=request.getContextPath()%>/pages/eachproduct.jsp?id=<%=product.getprodid()%>">
				<img
				src="<%=request.getContextPath()%>/resources/images/<%=product.getProductImage()%>"
				alt="<%=product.getProductName()%>" class="product-img">
				<div class="details">
					<h4><%=product.getProductName()%></h4>
					<p>
						Price: $<%=product.getProductPrice()%></p>
				</div>
				</a>
			<form action="${pageContext.request.contextPath}/CartServlet" method="POST">
			   <input type="hidden" name="prod_id" value="<%=product.getprodid()%>">
                <input type="hidden" name="quantity" value="1">
                    <button class="btn-cartadd">
                	Add to Cart <i class="fa-solid fa-cart-shopping"></i>
			</button>
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


    <footer>
        <!-- Footer code here (You can place your existing footer code here) -->
    </footer>
</body>
</html>