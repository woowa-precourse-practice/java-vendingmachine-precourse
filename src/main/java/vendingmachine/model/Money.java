package vendingmachine.model;

import vendingmachine.utils.NumberUtils;

import java.util.Objects;

public class Money {

    private static final String NOT_AFFORDAABLE_MESSAGE = "잔액이 부족합니다.";
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
        if (this.amount < amount) {
            throw new IllegalArgumentException(NOT_AFFORDAABLE_MESSAGE);
        }
        this.amount -= amount;
    }

    public boolean convertable() {
        return Coin.convertable(amount);
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
