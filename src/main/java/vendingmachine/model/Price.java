package vendingmachine.model;

import vendingmachine.utils.NumberUtils;

public class Price implements Comparable<Price> {

    private static final int PRICE_LOWER_BOUND = 100;
    private static final int PRICE_MIN_UNIT = 10;
    private static final int DIVIDED = 0;
    private static final String OUT_OF_PRICE_RANGE = "상품 가격은 100원 이상이어야 합니다.";
    private static final String INVALID_PRICE_UNIT = "상품 가격은 10원 단위로 나누어 떨어져야 합니다.";

    private final int amount;

    private Price(int amount) {
        validate(amount);
        this.amount = amount;
    }

    private void validate(int amount) {
        if (isOutOfPriceRange(amount)) {
            throw new IllegalArgumentException(OUT_OF_PRICE_RANGE);
        }
        if (hasValidPriceUnit(amount)) {
            throw new IllegalArgumentException(INVALID_PRICE_UNIT);
        }
    }

    private boolean isOutOfPriceRange(int amount) {
        return amount < PRICE_LOWER_BOUND;
    }

    private boolean hasValidPriceUnit(int amount) {
        return amount % PRICE_MIN_UNIT != DIVIDED;
    }

    public static Price from(String amount) {
        return new Price(NumberUtils.parseInt(amount));
    }

    public int getAmount() {
        return amount;
    }

    @Override
    public int compareTo(Price other) {
        return this.amount - other.amount;
    }
}
