package vendingmachine.model;

import vendingmachine.utils.NumberUtils;

public class Quantity {

    private static final int OUT_OF_STOCK = 0;

    private int quantity;

    private Quantity(int quantity) {
        this.quantity = quantity;
    }

    public static Quantity from(String quantity) {
        return new Quantity(NumberUtils.parseInt(quantity));
    }

    public boolean isEmpty() {
        return quantity == OUT_OF_STOCK;
    }

    public void decrease() {
        quantity--;
    }
}
