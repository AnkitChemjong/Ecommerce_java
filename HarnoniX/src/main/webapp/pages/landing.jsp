<% 
	
	if(request.getAttribute("products") == null){
		response.sendRedirect(request.getContextPath()+"/DisplayServlet");
	}

%>




<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="model.ProductModel"%>

<%
// Check if user is logged in
boolean isLoggedIn = session.getAttribute("customer") != null;
%>

<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <title>E-com</title>
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/stylesheet/style.css">


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

nav {
  padding: 1em;
  width: 100%;
  height:50px;
}

.nav_ul {
  display: flex;
  align-items: center;
  justify-content: space-around;
}

nav li {
  list-style: none;
}

nav a {
  color: rgb(249, 244, 244);
  text-decoration: none;
}

nav a:hover {
  color: #325bed;
  border-radius: 50px;
  padding: 10px;
}

#search {
  border: none;
  border-radius: 100px;
  padding: 8px 0 8px 29px;
  font-size: medium;
  font-weight: bold;
}

.nav_li6 {
  position: relative;
}

.nav_li6 img.search {
  position: absolute;
  margin-top: 6px;
  margin-left: 5px;
}

img,
button {
  cursor: pointer;

}

.btn.nav_li7,
.btn.nav_li8,
button {
  background-color: #7b7d7c;
  border: 3px solid rgb(200, 196, 196);
  color: rgb(0, 0, 0);
  border-radius: 6px;
  padding: 6px 14px;
}

.btn.nav_li7 a,
.btn.nav_li8 a:hover {
  color: rgb(255, 255, 255);

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
          <a href="${pageContext.request.contextPath}/pages/feedback.jsp">feedback</a>
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

  <div class="main">
    <!-- carosel section start  -->
    <div class="carousel">
      <div class="carousel-container">
        <div class="carousel-item">
        <img src="${pageContext.request.contextPath}/resources/images/headphone1.png" alt="Image 1" class="hover-image">
          <div class="description">
            <h3>Boat USA R</h3>
            <p> The boAt Airdopes, such as the Airdopes 441, offer a true wireless experience with touch controls and
              are well-suited for sports enthusiasts due to their secure fit and water resistance. For wired options,
              the boAt Bassheads series includes models like the Bassheads 242, which are designed with sports users in
              mind, featuring ear hooks for stability and water resistance, while the Bassheads 100 delivers heavy bass
              for everyday listening. Additionally, for those seeking silence, the boAt NIRVANAA 715 ANC provides active
              noise cancellation, creating an immersive audio experience.</p>
          </div>
        </div>
        <div class="carousel-item">
        <img src="${pageContext.request.contextPath}/resources/images/headphone2.png" alt="Image 2" class="hover-iamge">
          <div class="description">
            <h3>Boat USA C</h3>
            <p> The boAt Airdopes, such as the Airdopes 441, offer a true wireless experience with touch controls and
              are well-suited for sports enthusiasts due to their secure fit and water resistance. For wired options,
              the boAt Bassheads series includes models like the Bassheads 242, which are designed with sports users in
              mind, featuring ear hooks for stability and water resistance, while the Bassheads 100 delivers heavy bass
              for everyday listening. Additionally, for those seeking silence, the boAt NIRVANAA 715 ANC provides active
              noise cancellation, creating an immersive audio experience.</p>
          </div>
        </div>
        <div class="carousel-item">
          <img src="${pageContext.request.contextPath}/resources/images/headphone3.png" alt="Image 3" class="hover-iamge">
          <div class="description">
            <h3>Boat USA C</h3>
            <p>The boAt Airdopes, such as the Airdopes 441, offer a true wireless experience with touch controls and
              are well-suited for sports enthusiasts due to their secure fit and water resistance. For wired options,
              the boAt Bassheads series includes models like the Bassheads 242, which are designed with sports users in
              mind, featuring ear hooks for stability and water resistance, while the Bassheads 100 delivers heavy bass
              for everyday listening. Additionally, for those seeking silence, the boAt NIRVANAA 715 ANC provides active
              noise cancellation, creating an immersive audio experience.</p>
          </div>
        </div>
      </div>
      <button class="prev" onclick="moveSlide(-1)">&#10094;</button>
      <button class="next" onclick="moveSlide(1)">&#10095;</button>
    </div>
    <!-- carosel section end  -->

    <!-- <section class="main1 section2">
      <img src="./img/heroProduct.png" height="350" width="400" class="heroProduct" alt="hero product" />
      <div class="s2_details">
        <h2>Boat USA R</h2>
        <p class="s2_detailsP">
          Lorem ipsum dolor sit amet, consectetur adipisicing elit. Similique
          laborum quia, esse error odio, eos inventore necessitatibus totam
          maxime cupiditate exercitationem saepe, maiores sunt harum
          voluptatem nulla ea laudantium ut? Tempora voluptas at voluptatum
          quia deserunt? Earum maiores nihil eveniet rerum animi, tempore quae
          aliquid? Laudantium, aspernatur sit ullam doloribus impedit, dolores
          omnis beatae accusamus est atque labore commodi nostrum corporis
          ipsum enim eum ex! Sit nihil doloribus quae. Iure sunt recusandae
          quasi aliquam excepturi repellat esse saepe eveniet, fuga porro,
          enim non consequatur similique nihil incidunt.
        </p>
      </div>
    </section> -->

 

     <section class="collections">
  <% List<ProductModel> products = (List<ProductModel>) request.getAttribute("products");
     if (products != null && !products.isEmpty()) {
         for (ProductModel product : products) {
  %>
  <div class="collection">
      <a href="${pageContext.request.contextPath}/pages/eachProduct.jsp?id=<%=product.getprodid()%>">
          <img src="${pageContext.request.contextPath}/resources/images/<%=product.getProductImage()%>"
              alt="<%=product.getProductName()%>" class="product-img">
          <div class="details">
              <h4><%=product.getProductName()%></h4>
              <p>Price: $<%=product.getProductPrice()%></p>
          </div>
      </a>
      
         
         <form id="addToCartForm<%=product.getprodid()%>" action="${pageContext.request.contextPath}/CartServlet" method="POST">
                    <input type="hidden" name="prod_id" value="<%=product.getprodid()%>">
                    <input type="hidden" name="quantity" value="1">
                    <button class="btn-cartadd" onclick="<%= isLoggedIn ? "" : "redirectToLogin()" %>">
                        Add to Cart <i class="fa-solid fa-cart-shopping"></i>
                    </button>
                </form>
    
  </div>
  <%   }
     } else {
  %>
  <p>No products found.</p>
  <%   }
  %>
</section>

    <!-- <section class="main1 section1">
      <div class="section1_prdt">
        <img src="./img/headphone1.png" width="300" class="hover-image" alt="hero product" />
        <div class="price">
          <h3>Boat USA B</h3>
          <p>$106</p>
          <div class="btnPrice">
            <button class="order">order</button>
            <button class="cart">cart</button>
          </div>
        </div>
      </div>
      <p class="s1ProductDetails">
        Lorem ipsum dolor sit, amet consectetur adipisicing elit. Ex fugit
        voluptates dolores natus harum facilis sequi. Inventore odio iure
        molestias, corrupti odit libero necessitatibus fugiat. Magnam
        consequatur nulla dolor impedit veritatis? Aliquam, impedit nemo natus
        sit voluptas atque sunt soluta asperiores nisi laboriosam voluptates
        cumque quisquam dolorem dicta architecto aspernatur dignissimos, totam
        eos earum voluptate? Dolor nisi deleniti sit eum?
      </p>
    </section>
    <section class="main1 section1">
      <div class="section1_prdt">
        <img src="./img/headphone3.png" width="300" class="hover-image" alt="hero product" />
        <div class="price">
          <h3>Boat USA B</h3>
          <p>$106</p>
          <div class="btnPrice">
            <button class="order">order</button>
            <button class="cart">cart</button>
          </div>
        </div>
      </div>
      <p class="s1ProductDetails">
        Lorem ipsum dolor sit, amet consectetur adipisicing elit. Ex fugit
        voluptates dolores natus harum facilis sequi. Inventore odio iure
        molestias, corrupti odit libero necessitatibus fugiat. Magnam
        consequatur nulla dolor impedit veritatis? Aliquam, impedit nemo natus
        sit voluptas atque sunt soluta asperiores nisi laboriosam voluptates
        cumque quisquam dolorem dicta architecto aspernatur dignissimos, totam
        eos earum voluptate? Dolor nisi deleniti sit eum?
      </p>
    </section> -->
    <!-- Items section end  -->
  </div>
  <!-- footer section start -->
  <footer>
    <div class="footer">
      <div class="footer1">
        <h4>HELP</h4>
        <p>Support</p>
        <p>FAQ's</p>
        <p>Terms and conditions</p>
        <p>Privacy policy</p>
      </div>
      <div class="footer2">
        <h4>COMPANY</h4>
        <p>About</p>
        <p>Contact</p>
        <p>License</p>
        <p>What's new</p>
      </div>
      <div class="footer3">
        <h4>SOCIAL MEDIA</h4>
        <div class="ftr1" style="padding-bottom: 15px">
            <img src="${pageContext.request.contextPath}/resources/images/facebook-logo.png" height="25"alt="facebook">
            <img src="${pageContext.request.contextPath}/resources/images/instagram-logo.png" height="25" alt="instagram">
              <img src="${pageContext.request.contextPath}/resources/images/twitter-logo.png"  height="25" alt="twitter">
        </div>
        <div class="ftr2">
          <img src="${pageContext.request.contextPath}/resources/images/logo.png" height="40" alt="footerLogo">
        </div>
      </div>
    </div>
  </footer>

 <script>
 let currentIndex = 0;
 const slides = document.querySelectorAll('.carousel-item');
 const totalSlides = slides.length;

 function updateCarousel() {
     const offset = -currentIndex * 100;
     const carouselContainer = document.querySelector('.carousel-container');
     carouselContainer.style.transform = `translateX(${offset}%)`;
 }

 function moveSlide(direction) {
     currentIndex += direction;
     if (currentIndex >= totalSlides) {
         currentIndex = 0;
     } else if (currentIndex < 0) {
         currentIndex = totalSlides - 1;
     }
     updateCarousel();
 }
 

 // Function to show login required message
 function showLoginRequired() {
     alert("Login is required to access this feature.");
     // You can also redirect to the login page if needed
     // window.location.href = "<%=request.getContextPath()%>/pages/login.jsp";
 }
 </script>

</body>


</html>