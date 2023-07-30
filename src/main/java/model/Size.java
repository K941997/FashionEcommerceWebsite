package model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Size {
	private int sizeId;
	private String sizeName;
	private Timestamp created_at;
	private Timestamp modified_at;
	private List<Product> products = new ArrayList<>(); //1 Size-N Products, 1 Product-N Sizes = N Sizes - N Products
	
	public Size() {
		super();
	}
	
	public Size(String sizeName) {
		super();
		this.sizeName = sizeName;
	}
	
	public Size(String sizeName, Timestamp modified_at) {
		super();
		this.sizeName = sizeName;
		this.modified_at = modified_at;
	}

	public Size(String sizeName, Timestamp created_at, Timestamp modified_at) {
		super();
		this.sizeName = sizeName;
		this.created_at = created_at;
		this.modified_at = modified_at;
	}

	public Size(String sizeName, Timestamp created_at, Timestamp modified_at, List<Product> products) {
		super();
		this.sizeName = sizeName;
		this.created_at = created_at;
		this.modified_at = modified_at;
		this.products = products;
	}

	public Size(int sizeId, String sizeName) {
		super();
		this.sizeId = sizeId;
		this.sizeName = sizeName;
	}
	
	public Size(int sizeId, String sizeName, Timestamp created_at, Timestamp modified_at, List<Product> products) {
		super();
		this.sizeId = sizeId;
		this.sizeName = sizeName;
		this.created_at = created_at;
		this.modified_at = modified_at;
		this.products = products;
	}
	
	public int getSizeId() {
		return sizeId;
	}
	public void setSizeId(int sizeId) {
		this.sizeId = sizeId;
	}
	public String getSizeName() {
		return sizeName;
	}
	public void setSizeName(String sizeName) {
		this.sizeName = sizeName;
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
	public List<Product> getProducts() {
		return products;
	}
	public void setProducts(List<Product> products) {
		this.products = products;
	}

	@Override
	public String toString() {
		return "Size [sizeId=" + sizeId + ", sizeName=" + sizeName + ", created_at=" + created_at + ", modified_at="
				+ modified_at + ", products=" + products + "]";
	}
	
	
	
	
}
