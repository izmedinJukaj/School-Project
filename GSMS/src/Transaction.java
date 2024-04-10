public class Transaction extends Product {
    private double total;

    public Transaction() {
    }

    public Transaction(String name, int quantity, double price) {
        super(name, quantity, price);
        calculateTotal(); // Calculate the initial total
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    // Recalculate total based on quantity and price
    public void calculateTotal() {
        this.total = getQuantity() * getPrice();
    }
}
