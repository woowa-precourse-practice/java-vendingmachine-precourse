package vendingmachine.model;

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
}
