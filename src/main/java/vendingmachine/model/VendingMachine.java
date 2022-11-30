package vendingmachine.model;

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
}
