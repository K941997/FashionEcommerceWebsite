package model;

import java.sql.Timestamp;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Set;

public class Product {
	private int productId;
	private String productCustomId;
	private String productName;
	private String productImage;
	private double productPrice;
	private double productOriginalPrice;
	private String productDescription;
	private int productStatus;
	private Category category; //Relation 1-1, 1-N (1Product-1Category, 1Category-NProduct)
	
	//Test Min-Max PCS của 1 Product: (Fail)
	private double minPricePCS;
	private double maxPricePCS;
	
	private List<Size> sizes = new ArrayList<>(); //Không dùng vì đã có trung gian Relation N-N (1Product-NSizes, 1Size-NProducts)
	private List<Color> colors = new ArrayList<>(); //Không dùng vì đã có trung gian Relation N-N (1Product-NColors, 1Color-NProducts)
	
	//Test SumQuantity PCS show in JSP list_product (Fail)
	private int sumQuantity;
	
	private Timestamp created_at;
	private Timestamp modified_at;
	
	
	public Product() {
		super();
	}

	//Test SumQuantity PCS show in JSP list_product (Fail)
	public Product(int productId, String productCustomId, String productName, String productImage,
			double productPrice, double productOriginalPrice, String productDescription, int productStatus, 
			Category category, int sumQuantity) {
		super();
	}
	
	public Product(int productId, String productCustomId, String productName, String productImage, double productPrice,
			double productOriginalPrice, String productDescription, int productStatus, Category category,
			List<Size> sizes, List<Color> colors, Timestamp created_at, Timestamp modified_at) {
		super();
		this.productId = productId;
		this.productCustomId = productCustomId;
		this.productName = productName;
		this.productImage = productImage;
		this.productPrice = productPrice;
		this.productOriginalPrice = productOriginalPrice;
		this.productDescription = productDescription;
		this.productStatus = productStatus;
		this.category = category;
		this.sizes = sizes;
		this.colors = colors;
		this.created_at = created_at;
		this.modified_at = modified_at;
	}




	public Product(int productId, String productCustomId, String productName, String productImage, double productPrice,
			double productOriginalPrice, String productDescription, int productStatus, Category category) {
		super();
		this.productId = productId;
		this.productCustomId = productCustomId;
		this.productName = productName;
		this.productImage = productImage;
		this.productPrice = productPrice;
		this.productOriginalPrice = productOriginalPrice;
		this.productDescription = productDescription;
		this.productStatus = productStatus;
		this.category = category;
	}
	
	

	public Product(int productId, String productCustomId, String productName, String productImage,
			double productOriginalPrice, String productDescription, int productStatus, Category category,
			double minPricePCS, double maxPricePCS) {
		super();
		this.productId = productId;
		this.productCustomId = productCustomId;
		this.productName = productName;
		this.productImage = productImage;
		this.productOriginalPrice = productOriginalPrice;
		this.productDescription = productDescription;
		this.productStatus = productStatus;
		this.category = category;
		this.minPricePCS = minPricePCS;
		this.maxPricePCS = maxPricePCS;
	}

	public int getProductId() {
		return productId;
	}


	public void setProductId(int productId) {
		this.productId = productId;
	}


	public String getProductCustomId() {
		return productCustomId;
	}


	public void setProductCustomId(String productCustomId) {
		this.productCustomId = productCustomId;
	}


	public String getProductName() {
		return productName;
	}


	public void setProductName(String productName) {
		this.productName = productName;
	}


	public String getProductImage() {
		return productImage;
	}


	public void setProductImage(String productImage) {
		this.productImage = productImage;
	}


	public double getProductPrice() {
		return productPrice;
	}


	public void setProductPrice(double productPrice) {
		this.productPrice = productPrice;
	}


	public double getProductOriginalPrice() {
		return productOriginalPrice;
	}


	public void setProductOriginalPrice(double productOriginalPrice) {
		this.productOriginalPrice = productOriginalPrice;
	}


	public String getProductDescription() {
		return productDescription;
	}


	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}


	public int getProductStatus() {
		return productStatus;
	}


	public void setProductStatus(int productStatus) {
		this.productStatus = productStatus;
	}


	public Category getCategory() {
		return category;
	}


	public void setCategory(Category category) {
		this.category = category;
	}


	public List<Size> getSizes() {
		return sizes;
	}


	public void setSizes(List<Size> sizes) {
		this.sizes = sizes;
	}


	public List<Color> getColors() {
		return colors;
	}


	public void setColors(List<Color> colors) {
		this.colors = colors;
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
	
	
	public double getMinPricePCS() {
		return minPricePCS;
	}

	public void setMinPricePCS(double minPricePCS) {
		this.minPricePCS = minPricePCS;
	}

	public double getMaxPricePCS() {
		return maxPricePCS;
	}

	public void setMaxPricePCS(double maxPricePCS) {
		this.maxPricePCS = maxPricePCS;
	}

	//Test SumQuantity PCS show in JSP list_product (Fail)
	public int getSumQuantity() {
		return sumQuantity;
	}

	public void setSumQuantity(int sumQuantity) {
		this.sumQuantity = sumQuantity;
	}

	@Override
	public String toString() {
		return "Product [productId=" + productId + ", productCustomId=" + productCustomId + ", productName="
				+ productName + ", productImage=" + productImage + ", productPrice=" + productPrice
				+ ", productOriginalPrice=" + productOriginalPrice + ", productDescription=" + productDescription
				+ ", productStatus=" + productStatus + ", category=" + category + ", sizes=" + sizes + ", colors="
				+ colors + ", created_at=" + created_at + ", modified_at=" + modified_at + "]";
	}


	
	// method to format the price to VND
    public String getFormattedPrice() {
        NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
        return nf.format(this.productPrice);
    }
	
	

	
	
	
}