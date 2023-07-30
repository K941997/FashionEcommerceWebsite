package model;

import java.sql.Date;
import java.sql.Timestamp;

public class Order {
	private int orderId;
	private String customId; //custom_id
	private double totalmoney;
	private int status; //3 = Cancelled, 2 = processing, 1 = completed
	private User user; //1 Order - 1 User, 1 User - N Order
	private Timestamp created_at;
	private Timestamp modified_at;
	
	
	//Bổ sung cho (Client) List Orders by 1 User:
	private String addressShipment;
	private String shipmentName;
	private String paymentName;
	
	//Bổ sung cho (Admin) List Orders All User:
	private String userFullName;
	private String userPhone;
	
	
	
	public Order() {
		super();
	}
	
	//Bổ sung cho (Admin) List Orders All User:
	public Order(int orderId, String customId,String userFullName, String userPhone, String addressShipment,
			double totalmoney, int status, Timestamp created_at
			) {
		super();
		this.orderId = orderId;
		this.customId = customId;
		this.userFullName = userFullName;
		this.userPhone = userPhone;
		this.addressShipment = addressShipment;
		this.totalmoney = totalmoney;
		this.status = status;
		this.created_at = created_at;
		
		
	}

	public Order(int orderId, String customId, double totalmoney, int status, User user, Timestamp created_at,
			String addressShipment, String shipmentName, String paymentName) {
		super();
		this.orderId = orderId;
		this.customId = customId;
		this.totalmoney = totalmoney;
		this.status = status;
		this.user = user;
		this.created_at = created_at;
		this.addressShipment = addressShipment;
		this.shipmentName = shipmentName;
		this.paymentName = paymentName;
	}




	public Order(int orderId, String customId, double totalmoney, int status, Timestamp created_at,
			String addressShipment, String shipmentName, String paymentName) {
		super();
		this.orderId = orderId;
		this.customId = customId;
		this.totalmoney = totalmoney;
		this.status = status;
		this.created_at = created_at;
		this.addressShipment = addressShipment;
		this.shipmentName = shipmentName;
		this.paymentName = paymentName;
	}




	public Order(String customId, double totalmoney, int status, User user, Timestamp created_at,
			Timestamp modified_at) {
		super();
		this.customId = customId;
		this.totalmoney = totalmoney;
		this.status = status;
		this.user = user;
		this.created_at = created_at;
		this.modified_at = modified_at;
	}



	public Order(double totalmoney, int status, User user, Timestamp created_at, Timestamp modified_at) {
		super();
		this.totalmoney = totalmoney;
		this.status = status;
		this.user = user;
		this.created_at = created_at;
		this.modified_at = modified_at;
	}





	public Order(double totalmoney, int status, User user) {
		super();
		this.totalmoney = totalmoney;
		this.status = status;
		this.user = user;
	}




	public Order(String customId, double totalmoney, int status, User user) {
		super();
		this.customId = customId;
		this.totalmoney = totalmoney;
		this.status = status;
		this.user = user;
	}



	public Order(int orderId, String customId, double totalmoney, int status, User user, Timestamp created_at,
			Timestamp modified_at) {
		super();
		this.orderId = orderId;
		this.customId = customId;
		this.totalmoney = totalmoney;
		this.status = status;
		this.user = user;
		this.created_at = created_at;
		this.modified_at = modified_at;
	}
	
	//Bổ sung cho (Client) List Orders:
	public Order(String customId, double totalmoney, int status, Timestamp created_at, String addressShipment,
			String shipmentName, String paymentName) {
		super();
		this.customId = customId;
		this.totalmoney = totalmoney;
		this.status = status;
		this.created_at = created_at;
		this.addressShipment = addressShipment;
		this.shipmentName = shipmentName;
		this.paymentName = paymentName;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public String getCustomId() {
		return customId;
	}

	public void setCustomId(String customId) {
		this.customId = customId;
	}

	public double getTotalmoney() {
		return totalmoney;
	}

	//Paypal dung USD ko dung VND: -> chia cho 23000
	public String getStringTotalmoney() {
		return String.format("%.2f", totalmoney/23000);
	}
	
	public void setTotalmoney(double totalmoney) {
		this.totalmoney = totalmoney;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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
	
	
	
	



	


	public String getUserFullName() {
		return userFullName;
	}






	public void setUserFullName(String userFullName) {
		this.userFullName = userFullName;
	}






	public String getUserPhone() {
		return userPhone;
	}






	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}






	public String getAddressShipment() {
		return addressShipment;
	}


	public void setAddressShipment(String addressShipment) {
		this.addressShipment = addressShipment;
	}


	public String getShipmentName() {
		return shipmentName;
	}



	public void setShipmentName(String shipmentName) {
		this.shipmentName = shipmentName;
	}



	public String getPaymentName() {
		return paymentName;
	}



	public void setPaymentName(String paymentName) {
		this.paymentName = paymentName;
	}



	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", customId=" + customId + ", totalmoney=" + totalmoney + ", status="
				+ status + ", user=" + user + ", created_at=" + created_at + ", modified_at=" + modified_at + "]";
	}
	
	
	
	
	
	
}
