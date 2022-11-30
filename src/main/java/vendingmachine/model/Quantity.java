package vendingmachine.model;

public class Quantity {

    private static final int NO_STOCK = 0;

    private int quantity;

    public Quantity(String quantity) {
        this.quantity = Integer.parseInt(quantity);
    }

    public static Quantity from(String quantity) {
        return new Quantity(quantity);
    }

    public boolean hasStock() {
        return quantity > NO_STOCK;
    }

    public void decrease() {
        quantity--;
    }

    public int getQuantity() {
        return quantity;
    }
}
