package vendingmachine;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum Coin {
    COIN_500(500),
    COIN_100(100),
    COIN_50(50),
    COIN_10(10);

    private static final String NO_SUCH_AMOUNT_COINS = "적절하지 않은 동전 금액입니다.";

    private final int amount;

    Coin(final int amount) {
        this.amount = amount;
    }

    public static List<Integer> amounts() {
        return Arrays.stream(values())
                .map(Coin::getAmount)
                .collect(Collectors.toList());
    }

    public static Coin from(int amount) {
        return Arrays.stream(values())
                .filter(coin -> coin.isSameAmount(amount))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(NO_SUCH_AMOUNT_COINS));
    }

    private boolean isSameAmount(int amount) {
        return this.amount == amount;
    }

    public int getAmount() {
        return amount;
    }

    public int countConvertable(int money) {
        return money / amount;
    }
}
