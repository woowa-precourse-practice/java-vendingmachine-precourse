package vendingmachine.model;

import vendingmachine.utils.NumberUtils;

public class Money {

    private int amount;

    public Money(String amount) {
        this.amount = NumberUtils.parseInt(amount);
    }

    public static Money from(String amount) {
        return new Money(amount);
    }

    public boolean convertableBy(int amount) {
        return this.amount >= amount;
    }

    public void decreaseBy(int amount) {
        this.amount -= amount;
    }

    public boolean convertable() {
        return Coin.convertable(amount);
    }
}
