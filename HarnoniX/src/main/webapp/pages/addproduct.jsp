<%@page import="model.CustomerModel"%>
<%@page import="model.ProductModel"%>
<%@page import="utils.StringUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    


<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Product add</title>
<style>
@charset "UTF-8";
body{
	
	 background-image:url(../resources/images/final.jpg);
	 background-position:center;
	 background-repeat:no-repeat;
	 background-size:cover;
}
nav {
    margin: 0; /* Remove top and bottom margins */
    padding: 0; /* Remove padding */
    background: linear-gradient(rgba(0, 0, 0, 0.5), rgba(0, 0, 0, 0.5));
    color: #fff;
    padding: 10px 0;
}

.container-nav {
    display: flex;
    justify-content: space-between;
    align-items: center;
    max-width: 1200px;
    margin: 0 auto;
    padding: 0 20px; /* Add padding only to left and right */
}
.container-nav>img{
	width:100px;
	height:100px;
}
.navigation ul {
  list-style: none;
}

.navigation ul li {
  display: inline-block;
  margin-right: 20px;
}

.navigation ul li a {
  color: #fff;
  text-decoration: none;
}

.navigation ul li a:hover {
  color: #ddd;
}

/* Image styles */
.small-image {
  width: 50px;
  height: auto;
  display: block;
  margin: 0 auto;
}
.container {
    margin-top: 50px;
    position:relative;
    display:flex;
    justify-content:center;
    align-item:center;
}
.card {
    box-shadow: 0 8px 8px 0 rgb(255, 255, 255);
    border:2px solid black;
    transition: 0.3s;
    border-radius: 10px;
    width:500px;
    background:linear-gradient(rgba(0,0,0,0.2),rgba(0,0,0,0.2));
}


.card:hover {
    box-shadow: 0 8px 16px 0 rgba(0,0,0,0.2);
}

.card p {
    margin-bottom: 0;
}
.card>img{
	width:150px;
	height:150px;
	position:relative;
	left:20px;
}
.card>h1{
	position:relative;
	color:azure;
    font-family:sand-sarif;
	left:230px;
	bottom:90px;
}
.card>form{
	position:relative;
	bottom:40px;
	left:100px;
}

.form-group {
    margin-bottom: 20px;
    
}
.form-control {
   border-radius:20px;
   border:1px solid black;
   width:300px;
   height:40px;
   text-align:center;
   background:linear-gradient(rgba(0,0,0,0.2),rgba(0,0,0,0.2));
}
.form-group>label{
	color:azure;
	font-family:sand-sarif;
	font-size:20px;
	
}

.btn{
    background-color: #007bff;
    border-color: #007bff;
    border-radius:50px;
    width:200px;
    height:40px;
    position:relative;
    left:40px;
}

.btn:hover {
	transform:scale(1.2);
    background-color: #0056b3;
    border-color: #0056b3;
}

</style>
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
                <div class="card p-4">
                <img src="../resources/images/logo.png">
                    <h1>Add product</h1>
     <%
        String errorMessage = (String) request.getAttribute(StringUtils.ERROR_MESSAGE);
    
        
        if (errorMessage !=null && !errorMessage.isEmpty()) {
    %>
        <div class="alert alert-danger" role="alert">
            <%= errorMessage %>
        </div>
        <% 
        }
        %>
                    <form action="${pageContext.request.contextPath}/ProductServlet" method="post" enctype="multipart/form-data">
                 
                        <div class="form-group">
                            <label for="firstName">Product Name:</label><br>
                            <input type="text" class="form-control" id="firstName" name="product_name" required>
                        </div>
                        <div class="form-group">
                            <label for="lastName">Product Description:</label><br>
                            <input type="text" class="form-control" id="lastName" name="description" required>
                        </div>
                        <div class="form-group">
                            <label for="username">Price:</label><br>
                            <input type="number" class="form-control" id="username" name="price" required>
                        </div>
                        <div class="form-group">
                            <label for="birthday">Quantity:</label><br>
                            <input type="number" class="form-control" id="birthday" name="quantity" required>
                        </div>
                       
               
                        <div class="form-group">
                            <label for="text">Type:</label><br>
                            <input type="text" class="form-control" id="email" name="type" required>
                        </div>
                        <div class="form-group">
                            <label for="text">Quality:</label><br>
                            <input type="text" class="form-control" id="quality" name="quality" required>
                        </div>
                
                        <div class="form-group">
                        	<label for="image">Image:</label><br>
                        	<input type="file" class="form-control-file" id="image" name="image_url" required />
                        </div>
                        <button type="submit" class="btn btn-primary btn-block">Submit</button>
                
                    </form>
            
                </div>
    </div>
    
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>

