package model;

import java.io.File;


import javax.servlet.http.Part;

public class CustomerModel {
	private int id;
	private String firstName;
	private String lastName;
	private String username;
	private String email;
	private String address;
	private String password;
	private String image;
	private String role;


	public CustomerModel(String firstName, String lastName, String username,String email
			  ,String address, String password,String role, Part imagePart) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.email = email;
		this.address=address;
		this.password = password;
		this.role="user";
    	this.image = getImageUrl(imagePart);
    
    	
	}

	public CustomerModel() {
	}
	
	


	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}


	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address=address;
	}


	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	    
//
//	private String getImageUrl(Part imagePart) {
//		 if (imagePart == null) {
//		        return "default.jpg"; // Or handle the null case as desired
//		   }
//		String savePath = "C:\\Users\\niraj\\eclipse-workspace\\HarnoniX\\src\\main\\webapp\\resources\\images";
//		File fileSaveDir = new File(savePath);
//		String imageUrlFromPart = null;
//		if (!fileSaveDir.exists()) {
//			fileSaveDir.mkdir();
//		}
//		String contentDisp = imagePart.getHeader("content-disposition");
//		String[] items = contentDisp.split(";");
//		for (String s : items) {
//			if (s.trim().startsWith("filename")) {
//				imageUrlFromPart = s.substring(s.indexOf("=") + 2, s.length() - 1);
//			}
//		}
//		if (imageUrlFromPart == null || imageUrlFromPart.isEmpty()) {
//			imageUrlFromPart = "default.jpg";
//		}
//		return imageUrlFromPart;
//	}
//

	private String getImageUrl(Part imagePart) {
		 if (imagePart == null) {
		        return "default.jpg"; // Or handle the null case as desired
		   }
		String savePath = "C:\\Users\\niraj\\eclipse-workspace\\HarnoniX\\src\\main\\webapp\\resources\\images\\";
		File fileSaveDir = new File(savePath);
		String image = null;
		if (!fileSaveDir.exists()) {
			fileSaveDir.mkdir();
		}
		String contentDisp = imagePart.getHeader("content-disposition");
		String[] items = contentDisp.split(";");
		for (String s : items) {
			if (s.trim().startsWith("filename")) {
				image = s.substring(s.indexOf("=") + 2, s.length() - 1);
			}
		}
		if (image == null || image.isEmpty()) {
			image = "default.jpg";
		}
		return image;
	}
	
	  public String getImage() {
	        return image;
	    }

	    public void setImage(String image) {
	        this.image= image;
	    }
}




