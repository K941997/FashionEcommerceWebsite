package model;

import java.sql.Timestamp;

public class Payment {
	private int paymentId;
	private String name; //payment_method: "COD Pay", "Online Pay"
	private int orderId; //FK 1-1 1-N Order
	private Timestamp created_at;
	private Timestamp modified_at;
	
	public Payment() {
		super();
	}
	
	

	public Payment(String name, int orderId, Timestamp created_at, Timestamp modified_at) {
		super();
		this.name = name;
		this.orderId = orderId;
		this.created_at = created_at;
		this.modified_at = modified_at;
	}



	public Payment(int paymentId, String name, int orderId, Timestamp created_at, Timestamp modified_at) {
		super();
		this.paymentId = paymentId;
		this.name = name;
		this.orderId = orderId;
		this.created_at = created_at;
		this.modified_at = modified_at;
	}

	public int getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(int paymentId) {
		this.paymentId = paymentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
	
	
	
	
	
	
	
}
