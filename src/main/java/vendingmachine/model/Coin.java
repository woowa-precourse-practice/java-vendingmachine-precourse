package vendingmachine.model;

import java.util.Arrays;

public enum Coin {
    COIN_500(500),
    COIN_100(100),
    COIN_50(50),
    COIN_10(10);

    private final int amount;

    Coin(final int amount) {
        this.amount = amount;
    }

    public static Coin create(int amount) {
        return Arrays.stream(values())
                .filter(coin -> coin.isSameAmount(amount))
                .findFirst()
                .get();
    }

    private boolean isSameAmount(int amount) {
        return this.amount == amount;
    }
}
