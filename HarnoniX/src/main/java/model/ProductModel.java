package model;



import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.UUID;

import utils.StringUtils;

import javax.servlet.http.Part;

public class ProductModel {
	private int prod_id;
	private String prod_name;
	private int prod_quantity;
	private int prod_price;
	private String prod_desc;
	private String prod_type;
	private String prod_quality;
	private String prod_image;

	public ProductModel(String prod_name,int prod_quantity,int prod_price, String prod_desc, String prod_type,String prod_quality,Part imagePart) {
		super();
		this.prod_name=prod_name;
		this.prod_quantity=prod_quantity;
		this.prod_price=prod_price;
		this.prod_desc=prod_desc;	
		this.prod_type=prod_type;
		this.prod_quality=prod_quality;
		this.prod_image = getImageUrl(imagePart);
	}

	public ProductModel() {

	}
	
	public int getprodid() {
		return prod_id;
	}
	
	public void setprodId(int prod_id) {
		this.prod_id = prod_id;
	}

	public String getProductName() {
		return prod_name;
	}

	public void setProductName(String prod_name) {
		this.prod_name = prod_name;
	}
	public String getProductDesc() {
		return prod_desc;
	}

	public void setProductDesc(String prod_desc) {
		this.prod_desc = prod_desc;
	}
	

	public int getProductQuantity() {
		return prod_quantity;
	}

	public void setProductQuantity(int prod_quantity) {
		this.prod_quantity = prod_quantity;
	}


	public int getProductPrice() {
		return prod_price;
	}

	public void setProductPrice(int prod_price) {
		this.prod_price = prod_price;
	}
	
	
	public String getProductType() {
		return prod_type;
	}

	public void setProductType(String prod_type) {
		this.prod_type = prod_type;
	}
	
	
	public String getProductQuality() {
		return prod_quality;
	}

	public void setProductQuality(String prod_quality) {
		this.prod_quality = prod_quality;
	}
	private String getImageUrl(Part imagePart) {
		 if (imagePart == null) {
		        return "default.jpg"; // Or handle the null case as desired
		   }
		String savePath = "C:\\Users\\niraj\\eclipse-workspace\\HarnoniX\\src\\main\\webapp\\resources\\images\\";
		File fileSaveDir = new File(savePath);
		String prod_image = null;
		if (!fileSaveDir.exists()) {
			fileSaveDir.mkdir();
		}
		String contentDisp = imagePart.getHeader("content-disposition");
		String[] items = contentDisp.split(";");
		for (String s : items) {
			if (s.trim().startsWith("filename")) {
				prod_image = s.substring(s.indexOf("=") + 2, s.length() - 1);
			}
		}
		if (prod_image == null || prod_image.isEmpty()) {
			prod_image = "default.jpg";
		}
		return prod_image;
	}

	
	



//	private String getImageUrl(Part imagePart) {
//		 Path directory=Paths.get(StringUtils.IMAGE_DIR);
//	   try {
//		   if(Files.notExists(directory)){
//			   Files.createDirectories(directory);
//		   }
//		   
//	   String filename=UUID.randomUUID().toString()+"_" + getFileName(imagePart);
//	   Path filepath=directory.resolve(filename);
//	   Files.copy(imagePart.getInputStream(), filepath);
//	   return filename;
//	}catch(IOException e) {
//		e.printStackTrace();
//		return "default.jpg";
//	}
//
//	}
//	
//	private String getFileName(Part Part) {
//		String contentDisp=Part.getHeader("content-disposition");
//		for(String content : contentDisp.split(",")) {
//			if( content.trim().startsWith("filename")){
//				return content.substring(content.indexOf('=')+2,content.length() - 1);
//			}
//		}
//		return null;
//	}
	
	  public String getProductImage() {
	        return prod_image;
	    }

	    public void setProductImage(String prod_image) {
	        this.prod_image= prod_image;
	    }
}
	
	




