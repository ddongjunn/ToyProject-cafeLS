
class Items {
	int items_code;
	String name;
	int quantity;
	int price;
	boolean orderFlag = false;
	
	public Items(int items_code,String name, int quantity, int price) {
		this.items_code = items_code;
		this.name = name;
		this.quantity = quantity;
		this.price = price;
	}
	
	public Items() {
		
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public boolean isOrderFlag() {
		return orderFlag;
	}

	public void setOrderFlag(boolean orderFlag) {
		this.orderFlag = orderFlag;
	}

}
