package model;

import java.sql.Timestamp;

public class OrderDetail { //ManyToMany with ProductColorSize N - N Order
	private int orderDetailId;
	private int quantity;
	private double price;
	private int productColorSizeId; //(Moi doi Object sang ID) 1 ManyToMany - 1 PCS, 1 PCS - N ManyToMany
	private int orderId; //(Moi doi Object sang ID) 1 ManyToMany - 1 Order, 1 Order - N ManyToMany
	private Timestamp created_at;
	private Timestamp modified_at;
	
	
	//Bổ sung cho JSP detail_order phải liệt kê ra:
	private String orderCustomId;
	private String pcsImage;
	private String productName;
	private double pcsPrice;
	private String colorName;
	private String sizeName;
	
	//Bổ sung Admin Detail Order:
	private String paymentName;
	private String shipmentName;
	
	
	
	
	
	public OrderDetail() {
		super();
	}

	
	


	public OrderDetail(int orderDetailId, int quantity, double price, String orderCustomId, String pcsImage,
			String productName, double pcsPrice, String colorName, String sizeName, String paymentName,
			String shipmentName) {
		super();
		this.orderDetailId = orderDetailId;
		this.quantity = quantity;
		this.price = price;
		this.orderCustomId = orderCustomId;
		this.pcsImage = pcsImage;
		this.productName = productName;
		this.pcsPrice = pcsPrice;
		this.colorName = colorName;
		this.sizeName = sizeName;
		this.paymentName = paymentName;
		this.shipmentName = shipmentName;
	}





	public OrderDetail(int quantity, double price, int productColorSizeId, int orderId, Timestamp created_at,
			Timestamp modified_at) {
		super();
		this.quantity = quantity;
		this.price = price;
		this.productColorSizeId = productColorSizeId;
		this.orderId = orderId;
		this.created_at = created_at;
		this.modified_at = modified_at;
	}



	//Bổ sung cho JSP detail_order:
	public OrderDetail(int orderDetailId, int quantity, double price, String orderCustomId, String pcsImage,
			String productName, double pcsPrice, String colorName, String sizeName) {
		super();
		this.orderDetailId = orderDetailId;
		this.quantity = quantity;
		this.price = price;
		this.orderCustomId = orderCustomId;
		this.pcsImage = pcsImage;
		this.productName = productName;
		this.pcsPrice = pcsPrice;
		this.colorName = colorName;
		this.sizeName = sizeName;
	}


	public OrderDetail(int orderDetailId, int quantity, double price, int productColorSizeId, int orderId,
			Timestamp created_at, Timestamp modified_at) {
		super();
		this.orderDetailId = orderDetailId;
		this.quantity = quantity;
		this.price = price;
		this.productColorSizeId = productColorSizeId;
		this.orderId = orderId;
		this.created_at = created_at;
		this.modified_at = modified_at;
	}



	public int getOrderDetailId() {
		return orderDetailId;
	}



	public void setOrderDetailId(int orderDetailId) {
		this.orderDetailId = orderDetailId;
	}



	public int getQuantity() {
		return quantity;
	}



	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}



	public double getPrice() {
		return price;
	}
	
	



	public String getPaymentName() {
		return paymentName;
	}



	public void setPaymentName(String paymentName) {
		this.paymentName = paymentName;
	}



	public String getShipmentName() {
		return shipmentName;
	}



	public void setShipmentName(String shipmentName) {
		this.shipmentName = shipmentName;
	}



	public void setPrice(double price) {
		this.price = price;
	}



	public int getProductColorSizeId() {
		return productColorSizeId;
	}



	public void setProductColorSizeId(int productColorSizeId) {
		this.productColorSizeId = productColorSizeId;
	}



	public int getOrderId() {
		return orderId;
	}



	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}



	public Timestamp getCreated_at() {
		return created_at;
	}



	public void setCreated_at(Timestamp created_at) {
		this.created_at = created_at;
	}



	public Timestamp getModified_at() {
		return modified_at;
	}



	public void setModified_at(Timestamp modified_at) {
		this.modified_at = modified_at;
	}


	public String getOrderCustomId() {
		return orderCustomId;
	}

	

	public double getPcsPrice() {
		return pcsPrice;
	}


	public void setPcsPrice(double pcsPrice) {
		this.pcsPrice = pcsPrice;
	}


	public void setOrderCustomId(String orderCustomId) {
		this.orderCustomId = orderCustomId;
	}


	public String getPcsImage() {
		return pcsImage;
	}


	public void setPcsImage(String pcsImage) {
		this.pcsImage = pcsImage;
	}


	public String getProductName() {
		return productName;
	}


	public void setProductName(String productName) {
		this.productName = productName;
	}


	public String getColorName() {
		return colorName;
	}


	public void setColorName(String colorName) {
		this.colorName = colorName;
	}


	public String getSizeName() {
		return sizeName;
	}


	public void setSizeName(String sizeName) {
		this.sizeName = sizeName;
	}

	
	
	
	
	

}
