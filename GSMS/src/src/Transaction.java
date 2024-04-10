
public class Transaction extends Product {
	private double total;
	
	public Transaction() {
	}
	public Transaction(String name, int quantity, double price) {
		super(name, quantity, price);
		this.total=quantity*price;	
	}
	
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total=total;
	}
	
}
