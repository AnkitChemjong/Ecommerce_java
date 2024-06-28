package controller;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.CartItemModel;
import model.CartModel;
import model.Hashing;
import model.PasswordEncryptionWIthAes;
import model.CustomerModel;
import model.ProductModel;
import model.ModelOrder;
import utils.StringUtils;






public class DatabaseController {

    public Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/harmonix";
        String user = "root";
        String pass = "";
        return DriverManager.getConnection(url, user, pass);
    }
    
   

   public int addCustomer(CustomerModel customerModel) {
       try (Connection con = getConnection();
            PreparedStatement st = con.prepareStatement(StringUtils.INSERT_CUSTOMER)) {
            String hashPassword = Hashing.encryptPassword(customerModel.getPassword(), "U3CdwubLD5yQbUOG92ZnHw==");
    
            st.setString(1, customerModel.getFirstName());
              st.setString(2, customerModel.getLastName());
              st.setString(3, customerModel.getUsername());
              st.setString(4, customerModel.getEmail());
              st.setString(5, customerModel.getAddress());
              st.setString(6, hashPassword);
              st.setString(7, customerModel.getRole());
              st.setString(8, customerModel.getImage());

             return st.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
            return -1;
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
            return -1;
        } catch (Exception ex) {
            ex.printStackTrace();
            return -1;
        }
    }
   

	  public int getCustomerIdByEmail(String email) throws SQLException, ClassNotFoundException {
		    int id = -1; // Default value for userId
		    
		    try (Connection con = getConnection()) {
		        
		        PreparedStatement statement = con.prepareStatement(StringUtils.FETCH_USER_DETAIL);
		        statement.setString(1, email);
		        
		        try (ResultSet resultSet = statement.executeQuery()) {
		            if (resultSet.next()) {
		                id = resultSet.getInt("id");
		                System.out.println("Retrieved userId: " + id); // Print the userId for debugging
		            } else {
		                System.out.println("No user found with email: " + email); // Print when no user is found
		            }
		        }
		    } catch (SQLException | ClassNotFoundException e) {
		        System.out.println("Error retrieving user by email: " + e.getMessage()); // Print error message
		        throw e; // Rethrow the exception to handle it upstream
		    }
		    
		    return id;
		}

   public int CustomerLogin(String email, String password) {
       try (Connection con = getConnection()) {
           PreparedStatement st = con.prepareStatement(StringUtils.FETCH_USER_DETAIL);
           st.setString(1, email);

           try (ResultSet resultSet = st.executeQuery()) {
               if (resultSet.next()) {
                   String getEmail = resultSet.getString("email"); 
                   String gethashedPassword = resultSet.getString("password"); 
                   String getRole = resultSet.getString("role");

                   
                   
                   String unHashPassword = Hashing.decryptPassword(gethashedPassword, "U3CdwubLD5yQbUOG92ZnHw==");
                   
                   System.out.println( unHashPassword);
                   
                   if (unHashPassword != null && getEmail.equals(email) && unHashPassword.equals(password) && getRole.equals("admin")) {
                       return 7; // Admin login successful
                       }

                   else if (unHashPassword != null && getEmail.equals(email) && unHashPassword.equals(password)) {
                       return 1; // Login successful
                   } else {
                       return 0; // Incorrect password
                   }
               } else {
                   return 0; // No match for this record
               }
           }
       } catch (SQLException | ClassNotFoundException ex) {
           ex.printStackTrace(); // Log the exception for debugging
           return -1;
       } catch (Exception e) {
           e.printStackTrace();
           return -1;
       }
   }
   
   public CustomerModel fetchCustomerDetails(String email) throws ClassNotFoundException {
       try (Connection con = getConnection();
            PreparedStatement op = con.prepareStatement(StringUtils.FETCH_USER_DETAIL)) {
           op.setString(1, email);

           try (ResultSet resultSet = op.executeQuery()) {
               if (resultSet.next()) {
                   CustomerModel customer = new CustomerModel();
                   customer.setEmail(resultSet.getString("email"));
                   customer.setAddress(resultSet.getString("address"));
                   customer.setUsername(resultSet.getString("user_name"));
                   customer.setImage(resultSet.getString("image"));
               
                   return customer;
               } else {
                   return null;
               }
           } catch (SQLException e) {
               e.printStackTrace();
               return null;
           }
       } catch (SQLException e) {
           e.printStackTrace();
           return null;
       }
   }

   private boolean isValidProducts(ProductModel productModel) {
       return productModel != null &&
              productModel.getProductName() != null && !productModel.getProductName().isEmpty() &&
              productModel.getProductDesc() != null && !productModel.getProductDesc().isEmpty() &&
              productModel.getProductPrice() > 0 &&
              productModel.getProductQuantity() > 0 &&
              productModel.getProductImage() != null && !productModel.getProductImage().isEmpty() &&
              productModel.getProductType() != null && !productModel.getProductType().isEmpty() &&
              productModel.getProductQuality() != null && !productModel.getProductQuality().isEmpty();
   }


    public int addProduct(ProductModel productModel) {
    	
        if (!isValidProducts(productModel)) {
            System.err.println("Invalid product data.");
        }
        try (Connection con = getConnection();
             PreparedStatement st = con.prepareStatement(StringUtils.INSERT_PRODUCT)) {

            st.setString(1, productModel.getProductName());
            st.setString(2, productModel.getProductDesc());
            st.setInt(3, productModel.getProductQuantity());
            st.setDouble(4, productModel.getProductPrice());
            st.setString(5, productModel.getProductType());
            st.setString(6, productModel.getProductQuality());
            st.setString(7, productModel.getProductImage());
   
            return st.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
            return -1;
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
            return -1;
        } catch (Exception ex) {
            ex.printStackTrace();
            return -1;
        }
    }
    
    

    public List<ProductModel> fetchAllProducts() {
        List<ProductModel> products = new ArrayList<>();
        try (Connection con = getConnection()) {
            PreparedStatement st = con.prepareStatement(StringUtils.GET_PRODUCT);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                ProductModel product = new ProductModel();
                product.setprodId(rs.getInt("prod_id")); 
                product.setProductName(rs.getString("prod_name"));
                product.setProductDesc(rs.getString("prod_desc"));
                product.setProductQuantity(rs.getInt("prod_quantity"));
                product.setProductPrice(rs.getInt("prod_price"));
                product.setProductQuality(rs.getString("prod_quality"));
                product.setProductType(rs.getString("prod_type"));
                product.setProductImage(rs.getString("prod_image"));
                products.add(product);
                
                
            }
            System.out.println(products);
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();  // Log the exception for debugging
        }
        return products;
    }




public ProductModel getProdById(int prod_id) {
	ProductModel product=null;
	
	try (Connection con= getConnection();
			PreparedStatement st=con.prepareStatement(StringUtils.SINGLE_PRODUCT)){
		    st.setInt(1, prod_id);
		    ResultSet rs=st.executeQuery();
		    
		    if(rs.next()) {
		    	    product = new ProductModel(); 
	                product.setProductName(rs.getString("prod_name"));
	                product.setProductDesc(rs.getString("prod_desc"));
	                product.setProductQuantity(rs.getInt("prod_quantity"));
	                product.setProductPrice(rs.getInt("prod_price"));
	                product.setProductQuality(rs.getString("prod_quality"));
	                product.setProductType(rs.getString("prod_type"));
	                product.setProductImage(rs.getString("prod_image"));
	                	
		    }
		    
		    rs.close();
	  } catch (SQLException | ClassNotFoundException ex) {
          ex.printStackTrace();  // Log the exception for debugging
      }
	return product;
	
}
public int addToCart(int id, int prod_id, int quantity) {
    Connection con = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    try {
        con = getConnection();

        // Check if the product already exists in the user's cart
        String checkQuery = "SELECT count(*) FROM cart WHERE id = ? AND prod_id = ?";
        pstmt = con.prepareStatement(checkQuery);
        pstmt.setInt(1, id);
        pstmt.setInt(2, prod_id);
        rs = pstmt.executeQuery();

        if (rs.next() && rs.getInt(1) > 0) {
            return 1;  // Product already exists
        }

        String insertQuery = "INSERT INTO cart (id, prod_id, quantity) VALUES (?, ?, ?)";
        pstmt = con.prepareStatement(insertQuery);
        pstmt.setInt(1, id);
        pstmt.setInt(2, prod_id);
        pstmt.setInt(3, quantity);
        int affectedRows = pstmt.executeUpdate();

        if (affectedRows == 0) {
            return -1;  // Failed to add the product, no rows affected
        }

        return 0; // Successfully added
    } catch (SQLException | ClassNotFoundException e) {
        e.printStackTrace();
    } finally {
        try {
            if (rs != null) rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        try {
            if (pstmt != null) pstmt.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        try {
            if (con != null) con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    return -1;  // Indicates an error occurred during the operation
}

public boolean removeFromCart(int cartId) throws SQLException, ClassNotFoundException {
	String sql = "DELETE FROM cart WHERE cartId = ?";
	try (Connection con = getConnection(); PreparedStatement pstmt = con.prepareStatement(sql)) {
		pstmt.setInt(1, cartId);
		int rowsAffected = pstmt.executeUpdate();
		return rowsAffected > 0; // Return true if at least one row is affected (item removed)
	} catch (SQLException | ClassNotFoundException e) {
		e.printStackTrace();
		throw e; // Rethrow to allow higher-level handlers to manage the error
	}
}

// Clears all items from a user's cart
public void clearCart(int id) throws Exception {
    try (Connection con = getConnection(); 
         PreparedStatement pstmt = con.prepareStatement(StringUtils.DELETE_CART)) {
        pstmt.setInt(1, id);
        pstmt.executeUpdate();
    } catch (SQLException | ClassNotFoundException e) {
        e.printStackTrace();
        throw e; // Rethrow to allow higher-level handlers to manage the error
    }
}

public List<CartItemModel> getCartItems(int id) throws Exception {
    List<CartItemModel> cartItems = new ArrayList<>();
    String sql = "SELECT c.cartId, c.id, c.prod_id, c.quantity, p.prod_name, p.prod_image, p.prod_price FROM cart c INNER JOIN product_info p ON c.prod_id = p.prod_id WHERE c.id = ?";
    try (Connection con = getConnection(); 
         PreparedStatement pstmt = con.prepareStatement(sql)) {
        pstmt.setInt(1, id);
        System.out.println("Executing query with userId: " + id); // Log the user ID being queried
        ResultSet rs = pstmt.executeQuery();
        if (!rs.isBeforeFirst()) {
            System.out.println("No data found for user: " + id); // No rows returned
        }
        while (rs.next()) {
            CartItemModel item = new CartItemModel(
                rs.getInt("cartId"),
                rs.getInt("id"),
                rs.getInt("prod_id"),
                rs.getInt("quantity"),
                rs.getString("prod_name"),
                rs.getString("prod_image"),
                rs.getInt("prod_price")
            );
            cartItems.add(item);
            System.out.println("Added item: " + item.getProductName() + " with quantity: " + item.getQuantity()); // Log details of each item added
        }
        rs.close();
    } catch (SQLException | ClassNotFoundException e) {
        e.printStackTrace(); // Print stack trace
        throw e; // Rethrow to allow higher-level handlers to manage the error
    }
    System.out.println("Total items retrieved: " + cartItems.size()); // Log total items retrieved
    return cartItems;
}

public boolean orderPlaced(int id, String status, int prod_id, int quantity) {
	
	
	String sql = "INSERT INTO orders (id, status, prod_id, quantity, dateOfOrder) VALUES (?, ?, ?, ?, NOW())";
	try (Connection con = getConnection(); PreparedStatement pstmt = con.prepareStatement(sql)) {
		pstmt.setInt(1, id);
		pstmt.setString(2, status);
		pstmt.setInt(3, prod_id);
		pstmt.setInt(4, quantity);

		int rowsAffected = pstmt.executeUpdate();
		return rowsAffected > 0; // Return true if rows were affected
	} catch (SQLException | ClassNotFoundException e) {
		e.printStackTrace();
		return false; // Return false if an exception occurs
	}
}


public List<ModelOrder> fetchOrderdetail(int id) throws ClassNotFoundException {
    List<ModelOrder> orders = new ArrayList<>();

    // SQL joins orders table with users and products table to fetch address, product name, and product price
    String sql = "SELECT o.order_id, o.id, o.prod_id, o.quantity, o.status, o.dateOfOrder, c.address AS customerAddress, " +
            "p.prod_name, p.prod_price " +
            "FROM orders o " +
            "JOIN customer c ON o.id = c.id " +
            "JOIN product_info p ON o.prod_id = p.prod_id " +
            "WHERE o.id = ?";

    Connection con = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    try {
        con = getConnection(); // Get connection from your connection method
        pstmt = con.prepareStatement(sql);
        pstmt.setInt(1, id);
        rs = pstmt.executeQuery();

        while (rs.next()) {
            ModelOrder order = new ModelOrder();
            order.setOrderId(rs.getInt("order_id"));
            order.setid(rs.getInt("id"));
            order.setProdId(rs.getInt("prod_id"));
            order.setQuantity(rs.getInt("quantity"));
            order.setstatus(rs.getString("status"));
            order.setOrderDate(rs.getString("dateOfOrder"));
            order.setShipAddress(rs.getString("customerAddress"));
            order.setProductName(rs.getString("prod_name")); 
            order.setProductPrice(rs.getInt("prod_price")); 
            orders.add(order);
        }

    } catch (SQLException e) {
        System.out.println("Database access error occurred: " + e.getMessage());
    } finally {
        try {
            if (rs != null) rs.close();
            if (pstmt != null) pstmt.close();
            if (con != null) con.close();
        } catch (SQLException e) {
            System.out.println("Error closing database resources: " + e.getMessage());
        }
    }

    return orders;
}

//Combined method to search products by name or price
public List<ProductModel> searchProducts(String searchQuery) throws Exception {
    List<ProductModel> products = new ArrayList<>();
    Connection conn = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;

    try {
        conn = getConnection(); 
        if (searchQuery.matches("-?\\d+(\\.\\d+)?")) { 
            double price = Double.parseDouble(searchQuery);
            String sql = "SELECT * FROM product_info WHERE prod_price = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setDouble(1, price);
        } else {
            String sql = "SELECT * FROM product_info WHERE prod_name LIKE ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, "%" + searchQuery + "%");
        }

        rs = stmt.executeQuery();
        while (rs.next()) {
            ProductModel product = new ProductModel();
            product.setprodId(rs.getInt("prod_id"));
            product.setProductName(rs.getString("prod_name"));
            product.setProductPrice(rs.getInt("prod_price")); 
            product.setProductImage(rs.getString("prod_image")); 
            products.add(product);
        }
    } finally {
        if (rs != null) {
            rs.close();
        }
        if (stmt != null) {
            stmt.close();
        }
        if (conn != null) {
            conn.close();
        }
    }
    return products;
}

public List<ModelOrder> getAllOrders() throws SQLException, ClassNotFoundException {
    List<ModelOrder> orders = new ArrayList<>();
    String query = "SELECT o.order_id, o.prod_id, o.id, o.quantity, o.status, o.dateOfOrder, " +
            "p.prod_name, p.prod_price, c.address " +
            "FROM orders o " +
            "JOIN product_info p ON o.prod_id = p.prod_id " +
            "JOIN customer c ON o.id = c.id";

    try (Connection connection = getConnection();
         PreparedStatement statement = connection.prepareStatement(query);
         ResultSet resultSet = statement.executeQuery()) {

        while (resultSet.next()) {
            int order_id = resultSet.getInt("order_id");
            int prod_id = resultSet.getInt("prod_id");
            int id = resultSet.getInt("id");
            int quantity = resultSet.getInt("quantity");
            String status = resultSet.getString("status");
            String dateOfOrder = resultSet.getString("dateOfOrder");
            String prod_name = resultSet.getString("prod_name");
            String address = resultSet.getString("address");
            int prod_price = resultSet.getInt("prod_price");
   

            ModelOrder order = new ModelOrder(prod_id, id, quantity,status,address, dateOfOrder);
            order.setOrderId(order_id);
            order.setProductName(prod_name);
            order.setShipAddress(address);
            order.setProductPrice(prod_price);
            orders.add(order);
        }
    }
    return orders;
}


 public void OrderAsCompleted(int order_id) throws SQLException, ClassNotFoundException {
    String query = "UPDATE orders SET status = 'completed' WHERE order_id = ?";

    try (Connection connection = getConnection();
         PreparedStatement statement = connection.prepareStatement(query)) {
        statement.setInt(1, order_id);
        statement.executeUpdate();
    }
}
 
 public boolean editProduct(int prod_id, ProductModel product) throws ClassNotFoundException {
     String sql ="UPDATE product_info SET prod_name = ?, prod_desc = ?, prod_price = ?, prod_quantity = ?, prod_type = ?, " +
     		"prod_quality = ?, prod_image = ?, WHERE prod_id = ?";

     try (Connection conn = getConnection();
          PreparedStatement stmt = conn.prepareStatement(sql)) {
         
         stmt.setString(1, product.getProductName());
         stmt.setString(2, product.getProductDesc());
         stmt.setInt(3, product.getProductPrice());
         stmt.setInt(4, product.getProductQuantity());
         stmt.setString(5, product.getProductType());
         stmt.setString(6, product.getProductQuality());
         stmt.setString(7, product.getProductImage());
    

         int rowsAffected = stmt.executeUpdate();
         return rowsAffected > 0;
     } catch (SQLException e) {
         e.printStackTrace(); // Consider a more robust logging or error handling mechanism
         return false;
     }
 }
 
 public boolean deleteProduct(int prod_id) throws ClassNotFoundException, Exception {
     String sql = "DELETE FROM product_info WHERE prod_id = ?";

     try (Connection conn = getConnection();
          PreparedStatement stmt = conn.prepareStatement(sql)) {

         stmt.setInt(1, prod_id);  

         int rowsAffected = stmt.executeUpdate(); 
         return rowsAffected > 0;  
     } catch (SQLException e) {
         e.printStackTrace(); 
         return false;  
     }
 }
 
 
 public ProductModel getProductByName(int prodid) {
		ProductModel product = null;
		try (Connection con = getConnection();
				PreparedStatement st = con.prepareStatement(StringUtils.SINGLE_PRODUCT)) {

			st.setInt(1, prodid);

			try (ResultSet rs = st.executeQuery()) {
				if (rs.next()) {
					product = new ProductModel();
					product.setprodId(rs.getInt("prod_id"));
					product.setProductName(rs.getString("Prod_name"));
					product.setProductDesc(rs.getString("prod_desc"));
					product.setProductQuantity(rs.getInt("prod_quantity"));
					product.setProductPrice(rs.getInt("prod_price"));
					product.setProductType(rs.getString("prod_type"));
					product.setProductQuality(rs.getString("prod_quality"));
					product.setProductImage(rs.getString("prod_image"));
					
				}
			}
		} catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace(); // Log the exception for debugging
		}
		return product;
	}

	
 public int updateProduct(ProductModel product, int prod_id) {
	    try (Connection connection = getConnection()) {
	        PreparedStatement statement = connection.prepareStatement(StringUtils.UPDATE_PRODUCT);

	        statement.setString(1, product.getProductName());
	        statement.setString(2, product.getProductDesc());
	        statement.setInt(3, product.getProductQuantity());
	        statement.setInt(4, product.getProductPrice());
	        statement.setString(5, product.getProductType());
	        statement.setString(6, product.getProductQuality());
	        statement.setString(7, product.getProductImage()); // Set prod_image parameter

	        // Set prod_id parameter
	        statement.setInt(8, prod_id);

	        int result = statement.executeUpdate();
	        return result > 0 ? 1 : 0;

	    } catch (SQLException e) {
	        e.printStackTrace();
	        return -1;
	    } catch (Exception e) {
	        e.printStackTrace();
	        return -1;
	    }
	}


}







   





