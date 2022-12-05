package vendingmachine.model;

import java.util.EnumMap;
import java.util.Map;

public class VendingMachine {

    private final HoldingCoins holdingCoins;
    private final Products products;
    private final Money balance;

    private VendingMachine(HoldingCoins holdingCoins, Products products, Money balance) {
        this.holdingCoins = holdingCoins;
        this.products = products;
        this.balance = balance;
    }

    public static VendingMachine of(HoldingCoins holdingCoins, Products products, Money balance) {
        return new VendingMachine(holdingCoins, products, balance);
    }

    public Money getBalance() {
        return balance;
    }

    public void buy(Name name) {
        Product product = products.findByName(name);

        product.decreaseQuantity();
        balance.decreaseBy(product.getPrice());
    }

    public boolean isPurchasable() {
        return isAffordable() || hasStock();
    }

    private boolean hasStock() {
        return products.hasStock();
    }

    private boolean isAffordable() {
        return balance.isAffordable(products.getCheapestPrice());
    }

    public Map<Coin, Integer> changeBalance() {
        EnumMap<Coin, Integer> coins = new EnumMap<>(Coin.class);
        Coin.amounts().stream()
                .filter(balance::convertableBy)
                .forEach(amount -> toCoins(amount, coins));

        return coins;
    }

    private void toCoins(int amount, EnumMap<Coin, Integer> coins) {
        Coin coin = Coin.create(amount);
        coins.put(coin, countOptimal(coin));
    }

    private int countOptimal(Coin coin) {
        return Integer.min(holdingCoins.count(coin), balance.countConverted(coin));
    }
}
