<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="model.ModelOrder"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>User Orders</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css"
        integrity="sha512-SnH5WK+bZxgPHs44uWIX+LLJAJ9/2PkPKZ5QiAj6Ta86w+fsb2TkcmfRyVX3pBnMFcV7oQPJkl9QevSCWr3W6A=="
        crossorigin="anonymous" referrerpolicy="no-referrer" />
          <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css" integrity="sha512-DTOQO9RWCH3ppGqcWaEA1BIZOC6xxalwEsw9c2QQeAIftl+Vegovlnee1c9QX4TctnWMn13TZye+giMm8e2LwA==" crossorigin="anonymous" referrerpolicy="no-referrer" />
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/stylesheet/order.css">
          <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/stylesheet/nav.css">
    
</head>
<style>

@charset "UTF-8";

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
	right:450px;
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
	left:200px;
	
}
.navbar>form>button{
	border-radius:20px;
	position:relative;
	left:295px;
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
   left:-180px;
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

.purchase {
    margin-top:12%;
    background-color:azure;
    width:500px;
    margin-left:30%;
    border-radius:20px;
    display:grid;
    justify-content:center;
    align-item:center;
    background:linear-gradient(rgba(0,0,0,0.5),rgba(0,0,0,0.5))
}


.purchase h2 {
    margin-bottom: 20px;
}

.r_purchase {
    background-color: #fff;
    border: 1px solid #ddd;
    border-radius: 5px;
    padding: 10px;
    margin-bottom: 10px;
    width:100%;
}
.r_purchase:hover{
	transform:scale(1.3);
}
.r_purchase .one_detail {
    display: flex;
    flex-direction: column;
}

.r_purchase .one_detail span {
    margin-bottom: 5px;
}

/* Styling for Font Awesome icons */
.purchase i {
    margin-right: 5px;
}





</style>
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

<div class="purchase">
    <h2>Recent Purchases</h2>
    <% 
    List<ModelOrder> orders = (List<ModelOrder>) request.getAttribute("orders");
    if (orders != null && !orders.isEmpty()) {
        for (ModelOrder order : orders) {
    %>
        <div class="r_purchase">
            <div class="one_detail">
                <span>Product Name:<%= order.getProductName() %></span>
                <span>Date: <%= order.getOrderDate() %></span>
                <span>Quantity: <%= order.getQuantity() %></span>
                <span>Price: $<%= order.getProductPrice() %></span>
                <span>Status: <%= order.getstatus() %></span>
            </div>
        </div>
    <% 
        }
    } else {
    %>
        <p>No recent purchases found.</p>
    <% 
    }
    %>
    </div>
</body>
</html>