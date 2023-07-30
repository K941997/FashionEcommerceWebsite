package model;

public class CartItem extends ProductColorSize {
	private int quantityCartItem; //Số lượng trong Cart khác số lượng ManyToMany

	public CartItem(int quantityCartItem) {
		super();
		this.quantityCartItem = quantityCartItem;
	}

	public CartItem() {
		super();
	}

	public int getQuantityCartItem() {
		return quantityCartItem;
	}

	public void setQuantityCartItem(int quantityCartItem) {
		this.quantityCartItem = quantityCartItem;
	}

	
	
	
	
	
	
}
