package vendingmachine.model;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class VendingMachine {

    private final Products products;
    private final HoldingCoins holdingCoins;
    private final Money balance;

    public VendingMachine(Products products, HoldingCoins holdingCoins, Money balance) {
        this.products = products;
        this.holdingCoins = holdingCoins;
        this.balance = balance;
    }

    public static VendingMachine of(Products products, HoldingCoins holdingCoins, Money balance) {
        return new VendingMachine(products, holdingCoins, balance);
    }

    public void purchase(String productName) {
        Product product = products.findByName(productName);
        if (product.isPurchasable(balance)) {
            product.purchase(balance);
        }
    }

    public int getBalance() {
        return balance.getAmount();
    }

    public boolean purchasable() {
        int cheapestPrice = products.getCheapestPrice();

        return balance.isAvailable(cheapestPrice) && products.hasStock();
    }

    public Map<Coin, Integer> convertBalanceToCoins() {
        Map<Coin, Integer> convertedCoins = new HashMap<>();
        Arrays.stream(Coin.values())
                .filter(this::isConvertableBy)
                .forEach(coin -> convert(convertedCoins, coin));

        return convertedCoins;
    }

    private void convert(Map<Coin, Integer> convertedCoins, Coin coin) {
        int convertedCount = countConvertable(coin);
        int convertedAmount = calculateConvertedAmount(coin, convertedCount);

        convertedCoins.put(coin, convertedCount);
        balance.decrease(convertedAmount);
    }

    private boolean isConvertableBy(Coin coin) {
        return balance.convertableWith(coin.getAmount());
    }

    private int calculateConvertedAmount(Coin coin, int convertedCount) {
        return convertedCount * coin.getAmount();
    }

    private int countConvertable(Coin coin) {
        int maxCountOfConversion = coin.countConvertable(balance.getAmount());
        int holdingCount = holdingCoins.count(coin);

        return Math.min(holdingCount, maxCountOfConversion);
    }
}
