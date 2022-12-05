package vendingmachine.model;

import vendingmachine.utils.NumberUtils;

public class Price {

    private static final String INVALID_PRICE_RANGE = "가격은 100원 이상이어야 합니다.";
    private static final String INVALID_PRICE_UNIT = "가격은 최소 10원 단위로 입력해주세요.";
    private static final int MIN_PRICE = 100;
    private static final int MIN_PRICE_UNIT = 10;
    private static final int DIVIDED = 0;

    private final int amount;

    public Price(String input) {
        int price = NumberUtils.parseInt(input);
        validate(price);

        this.amount = price;
    }

    private void validate(int price) {
        validateRange(price);
        validateUnit(price);
    }

    private void validateRange(int price) {
        if (price < MIN_PRICE) {
            throw new IllegalArgumentException(INVALID_PRICE_RANGE);
        }
    }

    private void validateUnit(int price) {
        if (hasInvalidUnit(price)) {
            throw new IllegalArgumentException(INVALID_PRICE_UNIT);
        }
    }

    private boolean hasInvalidUnit(int price) {
        return price % MIN_PRICE_UNIT != DIVIDED;
    }

    public static Price from(String price) {
        return new Price(price);
    }

    public boolean isAvailable(Money money) {
        return money.isAvailable(amount);
    }

    public int getAmount() {
        return amount;
    }
}
