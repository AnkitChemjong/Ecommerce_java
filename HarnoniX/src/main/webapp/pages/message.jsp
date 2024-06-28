<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Notification</title>
     <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/stylesheet/message.css">
</head>

<% 
    request.getSession(false);
	if(session.getAttribute("admin_mail") != null && session.getAttribute("customer_mail")==null){
		response.sendRedirect(request.getContextPath()+"/pages/Dashboard.jsp");
	}

%>
<body>

    <div class="message-container">
        <h1>${messageTitle}</h1>
        <p>${message}</p>
        
        <form action="$(pageContext.request.contextPath)/CartServlet" method="POST">
        </form>
       <a href="${pageContext.request.contextPath}/HomeServlet">Return to Home Page</a>
    </div>
</body>
</html>