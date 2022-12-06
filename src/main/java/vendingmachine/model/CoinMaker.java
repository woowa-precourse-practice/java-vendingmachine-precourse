package vendingmachine.model;

import java.util.ArrayList;
import java.util.List;

public class CoinMaker {

    private final CoinAmountGenerator coinAmountGenerator;

    public CoinMaker(CoinAmountGenerator coinAmountGenerator) {
        this.coinAmountGenerator = coinAmountGenerator;
    }

    public List<Coin> makeCoins(Money money) {
        List<Coin> coins = new ArrayList<>();
        while (money.convertable()) {
            int amount = coinAmountGenerator.generate();
            if (money.convertableBy(amount)) {
                money.decreaseBy(amount);
                coins.add(Coin.create(amount));
            }
        }
        return coins;
    }
}
