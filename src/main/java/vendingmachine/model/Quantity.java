package vendingmachine.model;

import vendingmachine.utils.NumberUtils;

public class Quantity {

    private int quantity;

    private Quantity(int quantity) {
        this.quantity = quantity;
    }

    public static Quantity from(String quantity) {
        return new Quantity(NumberUtils.parseInt(quantity));
    }
}
