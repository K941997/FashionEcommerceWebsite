package model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Color {
	private int colorId;
	private String colorName;
	private Timestamp created_at;
	private Timestamp modified_at;
	private List<Product> products = new ArrayList<>(); //1 Color - N Products, 1 Products - N Colors = N Colors - N Products
	
	public Color() {
		super();
	}

	public Color(String colorName, Timestamp created_at, Timestamp modified_at) {
		super();
		this.colorName = colorName;
		this.created_at = created_at;
		this.modified_at = modified_at;
	}

	public Color(int colorId, String colorName, Timestamp created_at, Timestamp modified_at) {
		super();
		this.colorId = colorId;
		this.colorName = colorName;
		this.created_at = created_at;
		this.modified_at = modified_at;
	}

	public Color(int colorId, String colorName, Timestamp created_at, Timestamp modified_at, List<Product> products) {
		super();
		this.colorId = colorId;
		this.colorName = colorName;
		this.created_at = created_at;
		this.modified_at = modified_at;
		this.products = products;
	}
	
	public Color(String colorName, Timestamp created_at, Timestamp modified_at, List<Product> products) {
		super();
		this.colorName = colorName;
		this.created_at = created_at;
		this.modified_at = modified_at;
		this.products = products;
	}
	
	


	public Color(int colorId, String colorName) {
		super();
		this.colorId = colorId;
		this.colorName = colorName;
	}

	public int getColorId() {
		return colorId;
	}
	public void setColorId(int colorId) {
		this.colorId = colorId;
	}
	public String getColorName() {
		return colorName;
	}
	public void setColorName(String colorName) {
		this.colorName = colorName;
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
		return "Color [colorId=" + colorId + ", colorName=" + colorName + ", created_at=" + created_at
				+ ", modified_at=" + modified_at + ", products=" + products + "]";
	}

	
	
	
}
