package vendingmachine.model;

import vendingmachine.utils.NumberUtils;

public class Money {

    private final int amount;

    public Money(String amount) {
        this.amount = NumberUtils.parseInt(amount);
    }

    public static Money from(String amount) {
        return new Money(amount);
    }
}
