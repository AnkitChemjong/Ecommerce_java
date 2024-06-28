<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="model.ProductModel"%>
<%@ page import="controller.DatabaseController"%>



<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css" integrity="sha512-DTOQO9RWCH3ppGqcWaEA1BIZOC6xxalwEsw9c2QQeAIftl+Vegovlnee1c9QX4TctnWMn13TZye+giMm8e2LwA==" crossorigin="anonymous" referrerpolicy="no-referrer" />
<title>Home Page</title>
<%
  request.getSession(false);
	if(session.getAttribute("admin_mail") != null && session.getAttribute("customer_mail")==null){
		response.sendRedirect(request.getContextPath()+"/pages/Dashboard.jsp");
	}

%>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/stylesheet/eachproduct.css">

</head>

   <div class="navbar">
   
     
            <img src="${pageContext.request.contextPath}/resources/images/logo.png" alt="Logo">
            <div class="links">
                 
                <a href="<%=request.getContextPath()%>/HomeServlet">Product</a>
                <a href="<%=request.getContextPath()%>/OrderServlet"><i class="fa-solid fa-truck-fast"></i>Orders</a>
                <a href="${pageContext.request.contextPath}/CartServlet"><i class="fa-solid fa-cart-shopping"></i>Cart</a>
               <a href="${pageContext.request.contextPath}/pages/userProfile.jsp"><i class="fa-solid fa-user"></i>Profile</a>
 
            </div>
		
    </div>
<body>
<%
    String prod_id = request.getParameter("id");
    DatabaseController dbController = new DatabaseController();
    ProductModel product = null;

    if (prod_id != null && !prod_id.isEmpty()) {
        try {
            product = dbController.getProdById(Integer.parseInt(prod_id));
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().write("Error while fetching product detail");
            return; 
        }
    }

    if (product != null) {
%>
    <div class="prod_detailed">
        <img src="<%=request.getContextPath()%>/resources/images/<%=product.getProductImage()%>" alt="<%=product.getProductName()%>">
        <div class="prod_desc">
            <h1><%=product.getProductName()%></h1>
            <p>Features: </p>
            <div class="featu">
                  <span>Product Desc: <%=product.getProductDesc()%></span>
				     <span>Product type: <%=product.getProductType()%></span>
				      <span>Product quality:<%=product.getProductQuality()%></span>
				       <span>Product quantity: <%=product.getProductQuantity()%></span>
            </div>
           
        </div>
    </div>
<%
    } else {
        out.println("<p>Product not found.</p>");
    }
%>

</body>
</html>