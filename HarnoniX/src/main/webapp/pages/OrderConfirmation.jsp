<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% 
    request.getSession(false);
	if(session.getAttribute("admin_mail") == null && session.getAttribute("customer_mail") != null){
		response.sendRedirect(request.getContextPath()+"/manageDisplayServlet");
	}

%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/stylesheet/orderconfirmation.css">
    <title>Order Confirmation</title>
</head>
<body>
    
    <div class="content">
        <h1>Order Confirmation</h1>
        <p><%= request.getAttribute("message") %></p> <!-- Displaying the message from servlet -->
        <a href="<%=request.getContextPath()%>/Order.jsp">Return to Home</a>
    </div>
</body>
</html> 