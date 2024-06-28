<%@page import="model.CustomerModel"%>
<%@page import="utils.StringUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
// Check if user is logged in
boolean isLoggedIn = session.getAttribute("customer") != null;
%>


<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Registration Form</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/stylesheet/register.css">
</head>
<style>
@import url("https://fonts.googleapis.com/css2?family=Roboto+Slab:wght@100..900&display=swap");


* {
  box-sizing: border-box;
  margin: 0;
  padding: 0;
}

body {
  font-family: "Roboto Slab", serif;
  display: flex;
  flex-direction: column;
  gap: 1.5rem;
}

/* NavBar */
header,
footer {
  color: rgb(253, 253, 253);
  font-size: large;
  background-color: #000000;
  width: 100vw;
}

.nav {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 1em 2em; /* Added padding to the nav */
}

.nav_ul {
  display: flex;
  align-items: center;
  list-style: none;
}

.nav_ul li {
  margin-right: 1em; /* Adjusted margin between list items */
}

.nav_ul li:last-child {
  margin-right: 0; /* Remove margin from the last list item */
}

nav a {
  color: rgb(249, 244, 244);
  text-decoration: none;
  padding: 10px; /* Added padding to the anchor elements */
}

nav a:hover {
  color: #325bed;
  border-radius: 50px;
  background-color: rgba(255, 255, 255, 0.1); /* Added background color on hover */
}

.nav_li7,
.nav_li8 {
  margin: 0 1em; /* Adjusted margin for sign up and login buttons */
}

/* Moving section */
.main {
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: 2rem;
  justify-content: center;
}

.main1 {
  display: flex;
  align-items: center;
  gap: 1rem;
  justify-content: space-around;
}

.section1 {
  gap: 3rem;
  width: 60%;
}

.section1_prdt {
  display: flex;
  gap: 1rem;
}

.price {
  flex-direction: column;
  padding-top: 10em;
}

.price p {
  font-size: large;
  font-weight: bolder;
}

.s1ProductDetails,
.s2_detailsP {
  text-align: justify;
}

.btnPrice,
.price {
  display: flex;
  gap: 1rem;
}

.section2 {
  width: 70%;
  gap: 4rem;
}

.heroProduct {
  border-radius: 10px;
}

.s2_details {
  display: flex;
  flex-direction: column;
  gap: 2rem;
}

.section3 {
  width: 90%;
  background-color: #000000;
}

.section3_d1 {
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  gap: 2rem;
}

/* default section */

/* Footer */
.footer {
  width: 100%;
  padding-top: 3em;
  padding-bottom: 5em;
  display: flex;
  align-items: center;
  justify-content: space-around;
}

.footer h4 {
  margin-bottom: 15px;
}

.icn {
  background-color: white;
  border-radius: 100px;
}

/* Carosel section */
.carousel {
  width: 80%;
  margin: 20px auto;
  overflow: hidden;
  position: relative;
  display: flex;
  align-items: center;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
}

.carousel-container {
  display: flex;
  width: 200%;
  transition: transform 0.5s ease;
}

.carousel-item {
  display: flex;
  width: 100%;
  flex-shrink: 0;
  align-items: center;
}

.carousel-item img {
  width: 30%;
  height: auto;
}

.description {
  width: 70%;
  padding: 20px;
  box-sizing: border-box;
  height: 250px;
  /* Adjust based on your content or design */
  overflow-y: auto;
}

.prev,
.next {
  cursor: pointer;
  position: absolute;
  top: 50%;
  transform: translateY(-50%);
  background-color: rgba(0, 0, 0, 0.5);
  color: white;
  border: none;
  padding: 10px;
  font-size: 18px;
  z-index: 1000;
}

.prev {
  left: 10px;
}

.next {
  right: 10px;
}

.hover-image {
  width: 300px;
  transition: transform 0.5s ease, filter 0.5s ease;
}

.hover-image:hover {
  transform: scale(1.1);
  /* Scales the image to 110% when you hover over it */
  /* filter: grayscale(100%);  */
}

.collection {
    width: 30%;
    margin-bottom: 20px;
    background-color: #fff;
    border-radius: 20px;
    box-shadow: 0 0 5px rgba(0, 0, 0, 0.1);
    overflow: hidden;
    height:500px;
}
.collection a{
	 text-decoration:"none";
	 align-items:center;	}
  

.product-img {
    width: 100%;
    height: auto;
    display: block;
}

.details {
    padding: 10px;
}

.details h4 {
	margin:10px;
	font-family:sand-sarif;
	text-decoration:none;
    margin: 0;
    font-size: 18px;
    color: #333;
}

.details p {
	text-decoration:none;
	font-family:sand-sarif;
    margin: 0;
    color: #666;
}


  

.product-img {
    width: 100px;
    height: 100px;
    display: block;
}

.details {
    padding: 10px;
}

.details h4 {
	margin:10px;
	font-family:sand-sarif;
	text-decoration:none;
    margin: 0;
    font-size: 18px;
    color: #333;
}

.details p {
	text-decoration:none;
	font-family:sand-sarif;
    margin: 0;
    color: #666;
}

.btn-cartadd {
    background-color: #007bff;
    border-color: #007bff;
    border-radius:50px;
    width:200px;
    position:relative;
    left:20px;
    height:30px;
}

.btn-cartadd:hover {
	transform:scale(1.2);
    background-color: #0056b3;
    border-color: #0056b3;
}


.collection {
    text-align: center;
}

.product-link {
    text-decoration: none;
}

.product-heading {
    margin-top: 0;
    text-decoration: none;
}

.product-price {
    margin-bottom: 0;
    text-decoration: none;
}

.product-heading,
.product-price {
    text-align: center;
}


body {
    display: flex;
    flex-direction: column;
    gap: 1.5rem;
}

/* Collection Styles */
.collections {
    display: flex;
    flex-wrap: wrap;
    justify-content: space-around;
}

.collection {
    width: 30%;
    margin-bottom: 20px;
    background-color: #fff;
    border-radius: 20px;
    box-shadow: 0 0 5px rgba(0, 0, 0, 0.1);
    overflow: hidden;
    height: 500px;
}

.product-img {
    width: 100%;
    height: auto;
    display: block;
}

.details {
    padding: 10px;
}

.details h4 {
    margin: 10px;
    font-family: sans-serif; /* Corrected typo */
    font-size: 18px;
    color: #333;
}

.details p {
    margin: 0;
    color: #666;
}

.btn-cartadd {
    background-color: #007bff;
    border: none;
    border-radius: 50px;
    width: 80%;
    margin: 0 auto;
    padding: 10px 0;
    cursor: pointer;
    transition: transform 0.3s ease;
}

.btn-cartadd:hover {
    transform: scale(1.1);
    background-color: #0056b3;
}






</style>
<body>
  <header>
    <nav class="nav">
      <ul class="nav_ul">
        <li class="nav_li1">
          <li><a href="<%=request.getContextPath()%>/pages/landing.jsp">Landing</a></li>
        </li>
      <li class="nav_li2">
                <a href="<%=request.getContextPath()%>/CartServlet">
                    <%-- Check if user is logged in, if not, show a message --%>
                    <% if (!isLoggedIn) { %>
                        <span onclick="showLoginRequired()">Cart</span>
                    <% } else { %>
                        Cart
                    <% } %>
                </a>
            </li>
       
        <li class="nav_li5">
           <a href="${pageContext.request.contextPath}/pages/feedback.jsp">login</a>
        </li>
      
     
        <li class="btn nav_li7 signin">
          <a href="${pageContext.request.contextPath}/pages/register.jsp">sign up</a>

        </li>
        <li class="btn nav_li8 login">
          <a href="${pageContext.request.contextPath}/pages/login.jsp">login</a>
        </li>
      </ul>
    </nav>
  </header>



    <div class="container">
      <div class="card p-4">
      <img src="../resources/images/logo.png">
      <h1>SignUp</h1>
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
                    <form action="${pageContext.request.contextPath}/RegisterServlet" method="post" enctype="multipart/form-data">
                        <div class="form-group">
                            <label for="firstName">First Name:</label>
                            <input type="text" class="form-control" id="firstName" name="firstName" required>
                        </div>
                        <div class="form-group">
                            <label for="lastName">Last Name:</label>
                            <input type="text" class="form-control" id="lastName" name="lastName" required>
                        </div>
                        <div class="form-group">
                            <label for="username">Username:</label>
                            <input type="text" class="form-control" id="user_name" name="user_name" required>
                        </div>
                  
                        <div class="form-group">
                            <label for="email">Email:</label>
                            <input type="email" class="form-control" id="email" name="email" required>
                        </div>
                      
                         <div class="form-group">
                            <label for="address">Address:</label>
                            <input type="text" class="form-control" id="email" name="address" required>
                        </div>
                      
                        <div class="form-group">
                            <label for="password">Password:</label>
                            <input type="password" class="form-control" id="password" name="password" required>
                        </div>
                        <div class="form-group">
                            <label for="retypePassword">Retype Password:</label>
                            <input type="password" class="form-control" id="retypePassword" name="retypePassword" required>
                        </div>
                        <div class="form-group">
                        	<label for="image">Image:</label>
                        	<input type="file" class="form-control-file" id="image" name="image" required />
                        </div>
                        <button type="submit" class="btn btn-primary btn-block">Submit</button>
                    </form>
                    <div class="text-center mt-3">
            <p>Already have an account? <a href="${pageContext.request.contextPath}/pages/login.jsp">Login here</a></p>
        </div>
                </div>
          <script>
          // Function to show login required message
          function showLoginRequired() {
              alert("Login is required to access this feature.");
              // You can also redirect to the login page if needed
              // window.location.href = "<%=request.getContextPath()%>/pages/login.jsp";
          }
          </script>
    </div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>