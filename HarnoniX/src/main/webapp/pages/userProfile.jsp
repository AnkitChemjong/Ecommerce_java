<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="model.CustomerModel"%>
<!DOCTYPE html>
<html>
<style>
 .attribution { font-size :11 px ; text-align:center; }
 .attribution {color : hsl(228,45%,44%);' }
 
</style>
<head>
    <meta charset="UTF-8">
    <title>Profile</title>
      <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css" integrity="sha512-DTOQO9RWCH3ppGqcWaEA1BIZOC6xxalwEsw9c2QQeAIftl+Vegovlnee1c9QX4TctnWMn13TZye+giMm8e2LwA==" crossorigin="anonymous" referrerpolicy="no-referrer" />
       <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/stylesheet/userprofile.css">
         <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/stylesheet/nav.css">
    
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


    <%
            CustomerModel customer = (CustomerModel) session.getAttribute("profileDetails");
            if (customer!= null) {
            %>

    <div class=" profile">
        <div class="p_side">
           <img src="<%=request.getContextPath()%>/resources/images/<%=customer.getImage()%>" alt="<%=customer.getUsername()%>" class="customer-img">
            </div>
            <div class="detail">
                <p><strong>Name: <%= customer.getUsername() %></strong></p>
                <p><strong>Email:<%= customer.getEmail() %></strong></p>
                <p><strong>Address:<%= customer.getAddress()%></strong></p>
            </div>
            <div class="edit">
                <i id="pen" class="fa-sharp fa-solid fa-pen"></i>
            </div>
                  <form action="${pageContext.request.contextPath}/LogoutServlet" method = "post" >
         <button type="submit" style="background-color: black; position:relative; bottom:60px; left:60px; color: white; padding: 10px 20px; border: none; border-radius: 4px; cursor: pointer;">
        LogOut
    </button>
    </form>
        </div>
        <%
                } else {
                    // Handle the case where user is null
                }
            %>
    </div>

  
</body>
</html>