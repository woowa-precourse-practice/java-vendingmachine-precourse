package vendingmachine.model;

import vendingmachine.utils.NumberUtils;

public class Quantity {

    private static final int OUT_OF_STOCK = 0;
    private static final String INVALID_NEGATIVE_QUANTITY = "수량은 음수로 입력할 수 없습니다.";
    private static final int QUANTITY_LOWER_BOUND = 0;

    private final int quantity;

    private Quantity(int quantity) {
        validate(quantity);
        this.quantity = quantity;
    }

    private void validate(int quantity) {
        if (isInvalidQuantity(quantity)) {
            throw new IllegalArgumentException(INVALID_NEGATIVE_QUANTITY);
        }
    }

    private boolean isInvalidQuantity(int quantity) {
        return quantity < QUANTITY_LOWER_BOUND;
    }

    public static Quantity from(String quantity) {
        return new Quantity(NumberUtils.parseInt(quantity));
    }

    public boolean isEmpty() {
        return quantity == OUT_OF_STOCK;
    }

    public Quantity decrease(Quantity decreased) {
        return new Quantity(quantity - decreased.quantity);
    }
}
