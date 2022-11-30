package vendingmachine.model;

import vendingmachine.Coin;

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
        if (product.isAvailable(balance)) {
            balance.deduct(product.getPrice());
            product.decreaseQuantity();
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
        for (Coin coin : Coin.values()) {
            if (isConvertableBy(coin)) {
                convert(convertedCoins, coin);
            }
        }
        return convertedCoins;
    }

    private void convert(Map<Coin, Integer> convertedCoins, Coin coin) {
        int convertedCount = countConvertable(coin);
        int convertedAmount = calculateConvertedAmount(coin, convertedCount);

        convertedCoins.put(coin, convertedCount);
        balance.decrease(convertedAmount);
    }

    private boolean isConvertableBy(Coin coin) {
        return balance.convertable(coin.getAmount());
    }

    private int calculateConvertedAmount(Coin coin, int convertedCount) {
        return convertedCount * coin.getAmount();
    }

    private int countConvertable(Coin coin) {
        int maxCountOfConversion = coin.countConvertable(balance.getAmount());
        int holdingCount = holdingCoins.count(coin);

        if (holdingCount >= maxCountOfConversion) {
            return maxCountOfConversion;
        }
        return holdingCount;
    }
}
