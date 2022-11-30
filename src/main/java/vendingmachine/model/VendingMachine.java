package vendingmachine.model;

import vendingmachine.Coin;
import vendingmachine.CoinConverter;
import vendingmachine.RandomCoinAmountGenerator;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class VendingMachine {

    private final Products products;
    private final HoldingCoins holdingCoins;
    private Money insertedMoney;

    public VendingMachine(Products products, HoldingCoins holdingCoins) {
        this.products = products;
        this.holdingCoins = holdingCoins;
    }

    public static VendingMachine of(Products products, HoldingCoins holdingCoins) {
        return new VendingMachine(products, holdingCoins);
    }

    public void insertMoney(Money money) {
        this.insertedMoney = money;
    }

    public void purchase(String productName) {
        Product product = products.findByName(productName);
        if (product.isAvailable(insertedMoney)) {
            insertedMoney.deduct(product.getPrice());
            product.decreaseQuantity();
        }
    }

    public int getInsertedMoney() {
        return insertedMoney.getAmount();
    }

    public boolean purchasable() {
        int cheapestPrice = products.getCheapestPrice();

        return insertedMoney.isAvailable(cheapestPrice) && products.hasStock();
    }

    public Map<Coin, Integer> convertBalanceToCoins() {
        Map<Coin, Integer> convertedCoins = new HashMap<>();
        for (Coin coin : Coin.values()) {
            if (insertedMoney.convertable(coin.getAmount())) {
                int convertedCount = countConvertable(coin);
                convertedCoins.put(coin, convertedCount);
                insertedMoney.decrease(convertedCount * coin.getAmount());
            }
        }
        return convertedCoins;
    }

    private int countConvertable(Coin coin) {
        int maxCountOfConversion = coin.countConvertable(insertedMoney.getAmount());
        int holdingCount = holdingCoins.count(coin);

        if (holdingCount >= maxCountOfConversion) {
            return maxCountOfConversion;
        }
        return holdingCount;
    }
}
