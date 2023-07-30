package model;


import java.sql.Timestamp;
import java.sql.Date;

public class Category {
	private int categoryId;
	private String categoryName;
	private Timestamp created_at;
	private Timestamp modified_at;
	
	public Category() {
		super();
	}
	
	public Category( String categoryName) {
		super();
		this.categoryName = categoryName;
	}

	public Category( String categoryName, Timestamp created_at, Timestamp modified_at) {
		super();
		this.categoryName = categoryName;
		this.created_at = created_at;
		this.modified_at = modified_at;
	}

	public Category(int categoryId,  String categoryName) {
		super();
		this.categoryId = categoryId;
		this.categoryName = categoryName;
	}
	
	public Category(int categoryId,  String categoryName, Timestamp created_at,
			Timestamp modified_at) {
		super();
		this.categoryId = categoryId;
		this.categoryName = categoryName;
		this.created_at = created_at;
		this.modified_at = modified_at;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}



	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
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

	@Override
	public String toString() {
		return "Category [categoryId=" + categoryId +  ", categoryName="
				+ categoryName + ", created_at=" + created_at + ", modified_at=" + modified_at + "]";
	}

	

	
}
