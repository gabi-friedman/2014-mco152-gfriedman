package friedman.vendingmachine;

public class Item {

	private String code;
	private String name;
	private double price;
	private int quantity;
	
	public Item(String code, String name, double price, int quantity) {
		this.code = code;
		this.name = name;
		this.price = price;
		this.quantity = quantity;
	}
	public String getCode() {
		return code;
	}
	public String getName() {
		return name;
	}
	public double getPrice() {
		return price;
	}
	public int getQuantity() {
		return quantity;
	}
	public void decreaseQuantity() {
		this.quantity-=1;
	}
	
	/*public String toString(){
		StringBuilder item = new StringBuilder();
		item.append(code);
		item.append("  ");
		item.append(name);
		item.append("  @ ");
		item.append(price);
		item.append("  x ");
		item.append(quantity);
		item.append("\n");
		return item.toString();
	}*/
}
