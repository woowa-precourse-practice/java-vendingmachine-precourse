package vendingmachine.model;

import vendingmachine.Coin;

import java.util.Collections;
import java.util.List;

public class HoldingCoins {

    private final List<Coin> coins;

    public HoldingCoins(List<Coin> coins) {
        this.coins = coins;
    }

    public static HoldingCoins from(List<Coin> coins) {
        return new HoldingCoins(coins);
    }

    public int count(Coin coin) {
        return Collections.frequency(coins, coin);
    }
}
