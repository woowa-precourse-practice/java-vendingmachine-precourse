package vendingmachine.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HoldingCoins {

    private final List<Coin> coins;

    private HoldingCoins(List<Coin> coins) {
        this.coins = coins;
    }

    public static HoldingCoins from(Money money, CoinAmountGenerator amountGenerator) {
        List<Coin> coins = new ArrayList<>();
        while (money.convertable()) {
            int amount = amountGenerator.generate();
            if (money.convertableBy(amount)) {
                money.decreaseBy(amount);
                coins.add(Coin.create(amount));
            }
        }
        return new HoldingCoins(coins);
    }

    public int count(Coin coin) {
        return Collections.frequency(coins, coin);
    }
}
