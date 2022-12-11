package vendingmachine.model;

import vendingmachine.utils.NumberUtils;

import java.util.Objects;

public class Money {

    private static final String NOT_AFFORDABLE_MESSAGE = "잔액이 부족합니다.";
    private static final String INVALID_NEGATIVE_PRICE = "금액은 음수가 될 수 없습니다";
    private static final int PRICE_LOWER_BOUND = 0;

    private int amount;

    public Money(int amount) {
        validate(amount);
        this.amount = amount;
    }

    private void validate(int amount) {
        if (amount < PRICE_LOWER_BOUND) {
            throw new IllegalArgumentException(INVALID_NEGATIVE_PRICE);
        }
    }

    public static Money from(String amount) {
        return new Money(NumberUtils.parseInt(amount));
    }

    public boolean convertableBy(int amount) {
        return this.amount >= amount;
    }

    public void decreaseBy(int amount) {
        if (this.amount < amount) {
            throw new IllegalArgumentException(NOT_AFFORDABLE_MESSAGE);
        }
        this.amount -= amount;
    }

    public boolean convertable() {
        return Coin.convertable(amount);
    }

    public boolean isAffordable(Product product) {
        return product.isPurchasableWith(amount);
    }

    public int countConverted(Coin coin) {
        return amount / coin.getAmount();
    }

    public boolean isLessThan(int amount) {
        return this.amount < amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Money money = (Money) o;
        return amount == money.amount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount);
    }

    @Override
    public String toString() {
        return String.valueOf(amount);
    }
}
