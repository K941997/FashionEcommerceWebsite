package model;

import java.sql.Timestamp;

public class ProductColorSize {
	//BẢNG TRUNG GIAN RELATION ManyToMany:
	private int pcsId;
	private String pcsImage;
	private Product product; //Relation 1-1, 1-N from Relation ManyToMany
	private Color color; //Relation 1-1, 1-N from Relation ManyToMany
	private Size size; //Relation 1-1, 1-N from Relation ManyToMany
	private int quantity;
	private double price;
	private Timestamp created_at;
	private Timestamp modified_at;
	
	//Constructor:
	public ProductColorSize() {
		super();
	}
	
	public ProductColorSize(int pcsId, String pcsImage, Product product, Color color, Size size, int quantity,
			double price, Timestamp created_at, Timestamp modified_at) {
		super();
		this.pcsId = pcsId;
		this.pcsImage = pcsImage;
		this.product = product;
		this.color = color;
		this.size = size;
		this.quantity = quantity;
		this.price = price;
		this.created_at = created_at;
		this.modified_at = modified_at;
	}
	
	




	public ProductColorSize(Product product, Color color, Size size) {
		super();
		this.product = product;
		this.color = color;
		this.size = size;
	}

	public ProductColorSize(int pcsId, String pcsImage, Product product, Color color, Size size, int quantity,
			double price) {
		super();
		this.pcsId = pcsId;
		this.pcsImage = pcsImage;
		this.product = product;
		this.color = color;
		this.size = size;
		this.quantity = quantity;
		this.price = price;
	}

	//Getter Setter:
	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Size getSize() {
		return size;
	}

	public void setSize(Size size) {
		this.size = size;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
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

	public int getPcsId() {
		return pcsId;
	}


	public void setPcsId(int pcsId) {
		this.pcsId = pcsId;
	}
	
	


	public String getPcsImage() {
		return pcsImage;
	}


	public void setPcsImage(String pcsImage) {
		this.pcsImage = pcsImage;
	}



	public double getPrice() {
		return price;
	}



	public void setPrice(double price) {
		this.price = price;
	}



	@Override
	public String toString() {
		return "ProductColorSize [pcsId=" + pcsId + ", pcsImage=" + pcsImage + ", product=" + product + ", color="
				+ color + ", size=" + size + ", quantity=" + quantity + ", price=" + price + ", created_at="
				+ created_at + ", modified_at=" + modified_at + "]";
	}

	


	
	
	
	
	
	
}
