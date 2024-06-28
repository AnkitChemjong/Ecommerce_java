<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="model.ModelOrder"%>

<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/stylesheet/showAdminOrder.css">
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

   <h1>All Orders</h1>
    <table>
        <thead>
            <tr>
                <th>Order ID</th>
                <th>Product ID</th>
                <th>User ID</th>
                <th>Quantity</th>
                <th>Status</th>
                <th>Order Date</th>
                <th>Product Name</th>
                <th>Product Price</th>
                <th>Shipping Address</th>
                <th>Action</th>
            </tr>
        </thead>
        <tbody>
            <%
                List<ModelOrder> orders = (List<ModelOrder>) request.getAttribute("orders");
                if (orders != null && !orders.isEmpty()) {
                    for (ModelOrder order : orders) {
            %>
            <tr>
                <td><%= order.getOrderId() %></td>
                <td><%= order.getProdId() %></td>
                <td><%= order.getid() %></td>
                <td><%= order.getQuantity() %></td>
                <td><%= order.getstatus() %></td>
                <td><%= order.getOrderDate() %></td>
                <td><%= order.getProductName() %></td>
                <td><%= order.getProductPrice() %></td>
                <td><%= order.getShipAddress() %></td>
                <td>
                    <form action="<%=request.getContextPath()%>/CompOrderServlet" method="post">
                        <input type="hidden" name="order_id" value="<%= order.getOrderId() %>">
                        <button type="submit" class="complete-btn">Complete</button>
                    </form>
                    
                </td>
            </tr>
            <%
                    }
                } else {
            %>
            <tr>
                <td colspan="11">No orders found.</td>
            </tr>
            <%
                }
            %>
        </tbody>
    </table>



</body>
</html>