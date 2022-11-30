package vendingmachine.model;

public class Quantity {

    private final int quantity;

    public Quantity(String quantity) {
        this.quantity = Integer.parseInt(quantity);
    }

    public static Quantity from(String quantity) {
        return new Quantity(quantity);
    }
}
