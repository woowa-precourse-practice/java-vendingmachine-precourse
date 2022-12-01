package vendingmachine.model;

import vendingmachine.Coin;
import vendingmachine.utils.NumberUtils;

public class Money {

    private static final String INVALID_MONEY_RANGE = "금액은 마이너스로 입력할 수 없습니다.";
    private static final String INSUFFICIENT_BALANCE = "잔액이 부족합니다.";
    private static final int NO_MONEY = 0;

    private int amount;

    public Money(String input) {
        int money = NumberUtils.parseInt(input);
        validate(money);
        this.amount = money;
    }

    private void validate(int money) {
        validateRange(money);
    }

    private void validateRange(int money) {
        if (money < NO_MONEY) {
            throw new IllegalArgumentException(INVALID_MONEY_RANGE);
        }
    }

    public static Money from(String money) {
        return new Money(money);
    }

    public boolean convertable() {
        return amount >= Coin.COIN_10.getAmount();
    }

    public boolean convertableWith(int amount) {
        return this.amount >= amount;
    }

    public void decrease(int amount) {
        this.amount -= amount;
    }

    public boolean isAvailable(int amount) {
        return this.amount >= amount;
    }

    public void deduct(int price) {
        checkAvailable(price);
        amount -= price;
    }

    private void checkAvailable(int price) {
        if (amount < price) {
            throw new IllegalArgumentException(INSUFFICIENT_BALANCE);
        }
    }

    public int getAmount() {
        return amount;
    }
}
