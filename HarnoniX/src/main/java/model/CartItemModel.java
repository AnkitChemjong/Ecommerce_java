package model;
public class CartItemModel {
    private int cartId;
    private int id;
    private int prod_id;
    private int quantity;
    private String prod_name;
    private String prod_image;
    private int prod_price;

    // Constructors, getters, and setters

    // Constructor
    public CartItemModel(int cartId, int id, int prod_id, int quantity, String prod_name, String prod_image, int prod_price) {
        this.cartId = cartId;
        this.id = id;
        this.prod_id = prod_id;
        this.quantity = quantity;
        this.prod_name = prod_name;
        this.prod_image = prod_image;
        this.prod_price = prod_price;
    }
    
    
    public CartItemModel() {
    	
    }

    // Getters and setters
    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public int getid() {
        return id;
    }

    public void setid(int id) {
        this.id = id;
    }

    public int getProductId() {
        return prod_id;
    }

    public void setProductId(int prod_id) {
        this.prod_id = prod_id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getProductName() {
        return prod_name;
    }

    public void setProductName(String prod_name) {
        this.prod_name = prod_name;
    }


    public String getProductImage() {
        return prod_image;
    }

    public void setProductImage(String prod_image) {
        this.prod_image = prod_image;
    }

    public int getProductPrice() {
        return prod_price;
    }

    public void setProductPrice(int prod_price) {
        this.prod_price = prod_price;
    }
}
