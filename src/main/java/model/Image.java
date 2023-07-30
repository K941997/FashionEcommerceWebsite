package model;

public class Image {
	private int imageId;
	private String image;
	private Product product; //1 Image-1 Product, 1 Product - N Image
	
	public int getImageId() {
		return imageId;
	}
	public void setImageId(int imageId) {
		this.imageId = imageId;
	}
	
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	
	public Image() {
		super();
	}
	
	public Image(int imageId, String image, Product product) {
		super();
		this.imageId = imageId;
		this.image = image;
		this.product = product;
	}
	
	
	
}
