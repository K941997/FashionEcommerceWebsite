package model;

import java.sql.Timestamp;


public class Shipment {
	private int shipmentId;
	private String name; //shipment_method: "express shipping", "normal shipping"
	private int userId; //FK 1-1 1-N User
	private int orderId; //FK 1-1 1-N Order
	private Timestamp created_at;
	private Timestamp modified_at;
	private String shipAddress;
	
	
	public Shipment() {
		super();
	}
	
	


	public Shipment(String name, int userId, int orderId, Timestamp created_at, Timestamp modified_at,
			String shipAddress) {
		super();
		this.name = name;
		this.userId = userId;
		this.orderId = orderId;
		this.created_at = created_at;
		this.modified_at = modified_at;
		this.shipAddress = shipAddress;
	}




	public Shipment(String name, int userId, int orderId, Timestamp created_at, Timestamp modified_at) {
		super();
		this.name = name;
		this.userId = userId;
		this.orderId = orderId;
		this.created_at = created_at;
		this.modified_at = modified_at;
	}




	public Shipment(int shipmentId, String name, int userId, int orderId, Timestamp created_at, Timestamp modified_at) {
		super();
		this.shipmentId = shipmentId;
		this.name = name;
		this.userId = userId;
		this.orderId = orderId;
		this.created_at = created_at;
		this.modified_at = modified_at;
	}


	public int getShipmentId() {
		return shipmentId;
	}


	public void setShipmentId(int shipmentId) {
		this.shipmentId = shipmentId;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public int getUserId() {
		return userId;
	}


	public void setUserId(int userId) {
		this.userId = userId;
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
	
	


	public String getShipAddress() {
		return shipAddress;
	}




	public void setShipAddress(String shipAddress) {
		this.shipAddress = shipAddress;
	}




	@Override
	public String toString() {
		return "Shipment [shipmentId=" + shipmentId + ", name=" + name + ", userId=" + userId + ", orderId=" + orderId
				+ ", created_at=" + created_at + ", modified_at=" + modified_at + "]";
	}
	
	
	
	
	
}
