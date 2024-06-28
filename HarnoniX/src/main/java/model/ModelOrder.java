package model;

public class ModelOrder {
    private int order_id;
    private int prod_id;
    private int id;
    private int quantity;
    private String status;
    private String ship_Address;
    private String dateOfOrder;
    private String prod_name;
    private int prod_price;

    // Constructors
    public ModelOrder() {
    }

    public ModelOrder( int prod_id, int id, int quantity, String status, String ship_Address, String dateOfOrder) {
        
        this.prod_id = prod_id;
        this.id = id;
        this.quantity = quantity;
        this.status = status;
        this.dateOfOrder = dateOfOrder;
    }

    // Getters
    public int getOrderId() {
        return order_id;
    }
    public void setOrderId(int order_id) {
        this.order_id = order_id;
    }


    public int getProdId() {
        return prod_id;
    }
  
    public void setProdId(int prod_id) {
        this.prod_id = prod_id;
    }

    public int getid() {
        return id;
    }

    public void setid(int id) {
        this.id = id;
    }


    public int getQuantity() {
        return quantity;
    }
    
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getstatus() {
        return status;
    }

    public void setstatus(String status) {
        this.status = status;
    }
    public String getShipAddress() {
        return ship_Address;
    }
    
    public void setShipAddress(String ship_Address) {
        this.ship_Address = ship_Address;
    }


    public String getOrderDate() {
        return dateOfOrder;
    }

    public void setOrderDate(String dateOfOrder) {
        this.dateOfOrder = dateOfOrder;
    }

 

  


   

	public int getProductPrice() {
		return prod_price;
	}

	public void setProductPrice(int prod_price) {
		this.prod_price = prod_price;
	}

	public String getProductName() {
		return prod_name;
	}

	public void setProductName(String prod_name) {
		this.prod_name = prod_name;
	}
}
