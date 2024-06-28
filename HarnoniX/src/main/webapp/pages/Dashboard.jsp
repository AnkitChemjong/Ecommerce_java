<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Add New Product</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/stylesheet/dashboard.css"> 
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css"
        integrity="sha512-SnH5WK+bZxgPHs44uWIX+LLJAJ9/2PkPKZ5QiAj6Ta86w+fsb2TkcmfRyVX3pBnMFcV7oQPJkl9QevSCWr3W6A=="
                                                     crossorigin="anonymous" referrerpolicy="no-referrer" />
</head>
<body>
     <nav>
        <div class="logo">
          <img src="${pageContext.request.contextPath}/resources/images/logo.png" alt="Logo">
        </div>
        <form action="${pageContext.request.contextPath}/LogoutServlet" method = "post" >
         <button type="submit" style="background-color: black; color: white; padding: 10px 20px; border: none; border-radius: 4px; cursor: pointer;">
        LogOut
    </button>
    </form>
         <h1>Dashboard</h1>
      
    </nav>
        <div class="nav-items">
            <ul>
                <li><a href="${pageContext.request.contextPath}/pages/addproduct.jsp">Add Product</a></li>
                <li><a href="${pageContext.request.contextPath}/AdminServlet">View Products</a></li>
                <li><a href="${pageContext.request.contextPath}/OrderAdminServlet">View Orders</a></li>
            </ul>
        </div>
    
    
</body>
</html>