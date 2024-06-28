<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="model.CartItemModel"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Your Cart</title>
   <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/stylesheet/nav.css"> 
   <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/stylesheet/cart.css">
     <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css" integrity="sha512-DTOQO9RWCH3ppGqcWaEA1BIZOC6xxalwEsw9c2QQeAIftl+Vegovlnee1c9QX4TctnWMn13TZye+giMm8e2LwA==" crossorigin="anonymous" referrerpolicy="no-referrer" />
   
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

	<div class="cart_info">
		<h1>Your Cart</h1>
	

		<%
		List<CartItemModel> cartItems = (List<CartItemModel>) request.getAttribute("cartItems");
		if (cartItems != null && !cartItems.isEmpty()) {
			for (CartItemModel item : cartItems) {
		%>
		<div class="add_carts">
			<div class="image">
				<img
					src="<%=request.getContextPath()%>/resources/images/<%=item.getProductImage()%>"
					alt="<%=item.getProductName()%>">
				<div class="prod_name">
					<span><%=item.getProductName()%></span> <span>$<%=item.getProductPrice()%></span>
				</div>
			</div>

			<div class="quantity">
				<span>Quantity</span>
				<div class="Quant">
					<button class="add">+</button>
					<p><%=item.getQuantity()%></p>
					<button class="minus">-</button>
				</div>
			</div>
			<div class="total_amt">
				<span>Total amount</span>
				<p>
					$<%=item.getProductPrice() * item.getQuantity()%></p>
			</div>
		
			<form action="<%=request.getContextPath()%>/ModifyCartServlet"
				method="post">
				     <input type="hidden" name="deleteId" value="<%=item.getCartId()%>">
				<button type="submit" class="delete_btn">Remove Item</button>
			</form>
		
			   <form action="<%=request.getContextPath()%>/OrderServlet"
                method="post">
                <input type="hidden" name="cartId" value="<%=item.getCartId()%>">
                <input type="hidden" name="prod_id" value="<%=item.getProductId()%>">
                <input type="hidden" name="quantity" class="quantity-input" value="<%=item.getQuantity()%>">
                <button type="submit" class="order_btn">Place Order</button>
            </form>
        </div>
		</div>
		<%
		}
		} else {
		%>
		<p>Your cart is empty.</p>
		<%
		}
		%>

	</div>
   <script>
        document.querySelectorAll('.Q_btn button').forEach(button => {
            button.addEventListener('click', function () {
                const parentDiv = this.closest('.add_cart');
                const quantityElement = parentDiv.querySelector('.Quant p');
                let quantity = parseInt(quantityElement.textContent);

                if (this.classList.contains('add')) {
                    quantity++;
                } else if (this.classList.contains('minus') && quantity > 1) {
                    quantity--;
                }

                quantityElement.textContent = quantity;

      
                const hiddenQuantityInput = parentDiv.querySelector('.quantity-input');
                hiddenQuantityInput.value = quantity; 
            });
        });
    </script>











</body>
</html>