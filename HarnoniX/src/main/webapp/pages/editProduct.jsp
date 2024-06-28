<%@page import="utils.StringUtils"%>
<%@ page import="model.ProductModel" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/stylesheet/login.css">
     <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/stylesheet/editproduct.css">
    <title>lapBuy - Login</title>
</head>
<body>
 <%
            ProductModel product = (ProductModel)request.getAttribute("product");
        %>
    <form action="${pageContext.request.contextPath}/UpdateProductServlet" method="post" enctype="multipart/form-data">
        <div class="cdwcdw">
            <p class="p1">Login</p>
            <label for="ProductName">ProductName</label>
             <input type="text" id="productName" name="prod_name" value="<%= product.getProductName() %>">
            

            <label for="description">Description:</label>
            <input type="text" id="description" name="prod_desc" value="<%= product.getProductDesc() %>">

            <label for="price">Price:</label>
            <input type="number" id="price" name="prod_price" value="<%= product.getProductPrice() %>">

            <label for="quantity">Quantity:</label>
            <input type="number" id="quantity" name="prod_quantity" value="<%= product.getProductQuantity() %>">

         <label for="image_url">Image:</label>
            <input type="file" id="image_url" name="image_url" value="<%=product.getProductImage() %>"> 
            </div> 
			
			<div class="column-right">
            <label for="processor">Quality:</label>
            <input type="text" id="processor" name="prod_quality" value="<%= product.getProductQuality() %>">

            <label for="ram">Type:</label>
            <input type="text" id="ram" name="prod_type" value="<%= product.getProductType() %>">

        
            <button type="submit">Save</button>
   		<input type="hidden" name="prod_id" value="<%= product.getprodid() %>" readonly>
            <button class="login-btn" type="submit">Update</button>
        
    </form>
</body>
</html>
