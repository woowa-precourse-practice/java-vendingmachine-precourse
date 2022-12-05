package vendingmachine.model;

import vendingmachine.utils.NumberUtils;

public class Quantity {

    private static final int NO_STOCK = 0;
    private static final String OUT_OF_STOCK = "재고가 모두 소진되었습니다.";

    private int quantity;

    public Quantity(String quantity) {
        this.quantity = NumberUtils.parseInt(quantity);
    }

    public static Quantity from(String quantity) {
        return new Quantity(quantity);
    }

    public boolean hasStock() {
        return quantity > NO_STOCK;
    }

    public void decrease() {
        checkOutOfStock();
        quantity--;
    }

    private void checkOutOfStock() {
        if (quantity == NO_STOCK) {
            throw new IllegalArgumentException(OUT_OF_STOCK);
        }
    }
}
