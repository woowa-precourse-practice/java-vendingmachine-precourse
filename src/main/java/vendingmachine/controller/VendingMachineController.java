package vendingmachine.controller;

import vendingmachine.CoinConverter;
import vendingmachine.RandomCoinAmountGenerator;
import vendingmachine.model.Coin;
import vendingmachine.model.HoldingCoins;
import vendingmachine.model.Money;
import vendingmachine.model.Products;
import vendingmachine.model.VendingMachine;
import vendingmachine.view.InputView;
import vendingmachine.view.OutputView;

import java.util.List;
import java.util.function.Supplier;

public class VendingMachineController {

    private final InputView inputView;
    private final OutputView outputView;

    public VendingMachineController() {
        inputView = new InputView();
        outputView = new OutputView();
    }

    public void run() {
        HoldingCoins holdingCoins = initHoldingCoins();
        VendingMachine vendingMachine = initVendingMachine(holdingCoins);

        purchase(vendingMachine);

        printResult(vendingMachine);
    }

    private HoldingCoins initHoldingCoins() {
        HoldingCoins holdingCoins = readHoldingCoins();
        outputView.printHoldingCoins(holdingCoins);

        return holdingCoins;
    }

    private HoldingCoins readHoldingCoins() {
        Money money = read(inputView::readHoldingMoney);
        List<Coin> coins = toCoins(money);

        return HoldingCoins.from(coins);
    }

    private List<Coin> toCoins(Money money) {
        CoinConverter converter = new CoinConverter(new RandomCoinAmountGenerator());

        return converter.convert(money);
    }

    private VendingMachine initVendingMachine(HoldingCoins holdingCoins) {
        Products products = read(inputView::readProducts);
        Money insertedMoney = read(inputView::readInsertedMoney);

        return VendingMachine.of(products, holdingCoins, insertedMoney);
    }

    private void purchase(VendingMachine vendingMachine) {
        while (vendingMachine.purchasable()) {
            purchaseProduct(vendingMachine);
        }
    }

    private void purchaseProduct(VendingMachine vendingMachine) {
        outputView.printInsertedMoney(vendingMachine);
        String productName = read(inputView::readProductName);
        vendingMachine.purchase(productName);
    }

    private void printResult(VendingMachine vendingMachine) {
        outputView.printInsertedMoney(vendingMachine);
        outputView.printBalanceCoin(vendingMachine.convertBalanceToCoins());
    }

    private <T> T read(Supplier<T> supplier) {
        try {
            return supplier.get();
        } catch (IllegalArgumentException error) {
            outputView.printError(error);
            return read(supplier);
        }
    }
}
