package utils;



public class StringUtils {


	// Start SQL Queries
   public static final String INSERT_CUSTOMER = "INSERT INTO customer"
 	        + "(first_name,last_name,user_name,email,address,password,role,image)"
            + "VALUES (?, ?, ?, ?, ?,?, ?,?)";
		public static final String FETCH_USER_DETAIL = "SELECT * FROM customer WHERE email = ?";
		public static final String FETCH_USER_NAME = "SELECT * FROM customer WHERE user_name = ?";
		public static final String GET_USERNAME = "SELECT COUNT(*) FROM customer WHERE user_name = ?";
		public static final String GET_EMAIL = "SELECT COUNT(*) FROM customer WHERE email = ?";
		public static final String GET_ALL_STUDENTS = "SELECT * FROM customer";
		// End SQL Queries

   


	
	public static final String INSERT_PRODUCT = "INSERT INTO product_info "
	        + "(prod_name, prod_desc,prod_price,prod_quantity,prod_type,prod_quality,prod_image) "
	        + "VALUES (?, ?, ?, ?, ?, ?, ?)";
    public static final String GET_PRODUCT = "SELECT * FROM product_info";
    public static final String SINGLE_PRODUCT = "SELECT * FROM product_info WHERE prod_id = ?";
    public static final String DELETE_PRODUCT = "DELETE FROM product WHERE prod_name= ?";
	public static final String DELETE_PRODUCT_INFO_BY_ID = "DELETE FROM product_info WHERE prod_id = ?";
	public static final String UPDATE_PRODUCT = "UPDATE product_info SET prod_name=?, prod_desc=?, prod_quantity=?, prod_price=?, prod_type=?, prod_quality=?, prod_image=? WHERE prod_id=?";
    public static final String ADD_TO_CART = "INSERT INTO cart (id, prod_id, quantity) VALUES (?, ?, ?)\r\n"
    		+ "ON DUPLICATE KEY UPDATE quantity = quantity + VALUES(quantity);\r\n"
    		+ "";
    public static final String EDIT_CART ="UPDATE cart SET quantity = ? WHERE id = ? AND prod_id = ?";
    public static final String DELETE_INDIVIDUAL_CART = "DELETE FROM cart WHERE  id = ? AND prod_id = ?";
    public static final String DELETE_CART = "DELETE FROM cart WHERE id = ? ";



	// Start Parameter names
 

	
	
	public static final String FIRST_NAME = "firstName";
	public static final String LAST_NAME = "lastName";
	public static final String USERNAME = "user_name";
	public static final String EMAIL = "email";
	public static final String ADDRESS = "address";
	public static final String PASSWORD = "password";
	public static final String RETYPE_PASSWORD = "retypePassword";
	public static final String IMAGE = "image"; 
	
	public static final String PRODUCT_NAME = "product_name";
	public static final String DESCRIPTION = "description";
	public static final String PRICE = "price";
	public static final String QUANTITY = "quantity";
	public static final String TYPE = "type";
	public static final String QUALITY = "quality";
	public static final String IMAGE_P = "image_url";
	
	
	public static final String IMAGE_DIR = "C:\\Users\\niraj\\eclipse-workspace\\HarnoniX\\src\\main\\webapp\\resources\\images\\";
	
	// End Parameter names


	// Start string messages 
//	// Start register page messages
	public static final String SUCCESS_REGISTER_MESSAGE = "Successfully Registered!";
	public static final String ERROR_REGISTER_MESSAGE = "Please correct the form data.";
	public static final String SERVER_ERROR_MESSAGE = "An unexpected server error occurred.";
	public static final String USERNAME_ERROR_MESSAGE = "Username is already registered.";
	public static final String EMAIL_ERROR_MESSAGE = "Email is already registered.";
	public static final String PHONE_NUMBER_ERROR_MESSAGE = "Phone Number is already registered.";
	public static final String PASSWORD_UNMATCHED_ERROR_MESSAGE = "Password not matched.";
	public static final String SUCCESS_UPDATE_MESSAGE = " Updated!";
	public static final String ERROR_UPDATE_MESSAGE = "Could not update product!";
	public static final String SUCCESS_DELETE_MESSAGE ="Deleted!";
	public static final String ERROR_DELETE_MESSAGE = "Could not update product!";
	// End register page messages
	
	// Start login page message
	public static final String SUCCESS_LOGIN_MESSAGE = "Successfully LoggedIn!";
	public static final String ERROR_LOGIN_MESSAGE = " email or password is not correct!";
	public static final String ERROR_USER_MESSAGE = " useer is not found, create account";
	// End login page message
	
	public static final String SUCCESS_MESSAGE = "successMessage";
	public static final String ERROR_MESSAGE = "errorMessage";
	// End string messages 


	// Start JSP Route
	public static final String LOGIN_PAGE = "/pages/login.jsp";
	public static final String REGISTER_PAGE = "/pages/register.jsp";
	public static final String WELCOME_PAGE = "/pages/home.jsp";
	public static final String PRODUCT_PAGE = "/pages/product.jsp";
	public static final String ADD_PRODUCT_PAGE = "/pages/addproduct.jsp";
	public static final String ADMIN_DASHBOARD= "/pages/Dashboard.jsp";
	
	// End JSP Route
	
	// Start Servlet Route
	public static final String REGISTER_SERVLET = "/RegisterServlet";
	public static final String LOGIN_SERVLET = "/LoginServlet";
	public static final String ADD_PRODUCT = "/ProductServlet";
	public static final String ORDER_SERVLET="/OrderServlet";
	
 	public static final String DISPLAY_SERVLET= "/DisplayServlet";
 	public static final String INDIVIDUAL_PRODUCT_SERVLET= "/EachProductServlet";
 	public static final String ADD_CART_SERVLET = "/CartServlet";
 	public static final String ADMIN_SERVLET= "/AdminServlet";
 	
	// End Servlet Route
}