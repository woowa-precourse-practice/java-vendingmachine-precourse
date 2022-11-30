package vendingmachine;

import vendingmachine.model.Money;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CoinConverter {

    private final CoinAmountGenerator amountGenerator;

    public CoinConverter(CoinAmountGenerator amountGenerator) {
        this.amountGenerator = amountGenerator;
    }

    public List<Coin> convert(Money money) {
        List<Coin> coins = new ArrayList<>();
        while (money.convertable()) {
            int amount = amountGenerator.generate();
            if (money.convertable(amount)) {
                money.decrease(amount);
                coins.add(Coin.from(amount));
            }
        }
        return coins;
    }

    public Map<Coin, Integer> convertOptimal(Money money) {
        Map<Coin, Integer> convertCounts = new HashMap<>();

        for (Coin coin : Coin.values()) {
            Integer convertedCount = coin.countConvertable(money.getAmount());
            convertCounts.put(coin, convertedCount);
            money.decrease(calculateConvertedAmount(coin, convertedCount));
        }
        return convertCounts;
    }

    private int calculateConvertedAmount(Coin coin, Integer convertedCount) {
        return coin.getAmount() * convertedCount;
    }
}
