package vendingmachine.controller;

import vendingmachine.Coin;
import vendingmachine.CoinConverter;
import vendingmachine.RandomCoinAmountGenerator;
import vendingmachine.model.HoldingCoins;
import vendingmachine.model.Money;
import vendingmachine.model.Products;
import vendingmachine.model.VendingMachine;
import vendingmachine.view.InputView;
import vendingmachine.view.OutputView;

import java.util.List;

public class VendingMachineController {

    private final InputView inputView;
    private final OutputView outputView;

    public VendingMachineController() {
        inputView = new InputView();
        outputView = new OutputView();
    }

    public void run() {
        HoldingCoins holdingCoins = getHoldingCoins();

        outputView.printHoldingCoins(holdingCoins);

        VendingMachine vendingMachine = initVendingMachine(holdingCoins);

        while (vendingMachine.purchasable()) {
            purchaseProduct(vendingMachine);
        }
        outputView.printInsertedMoney(vendingMachine);
        outputView.printBalanceCoin(vendingMachine.convertBalanceToCoins());
    }

    private VendingMachine initVendingMachine(HoldingCoins holdingCoins) {
        Products products = readProducts();
        Money insertedMoney = readInsertedMoney();

        VendingMachine vendingMachine = VendingMachine.of(products, holdingCoins);
        vendingMachine.insertMoney(insertedMoney);
        return vendingMachine;
    }

    private void purchaseProduct(VendingMachine vendingMachine) {
        outputView.printInsertedMoney(vendingMachine);
        String productName = readProductName();
        vendingMachine.purchase(productName);
    }

    private String readProductName() {
        try {
            return inputView.readProductName();
        } catch (IllegalArgumentException error) {
            outputView.printError(error);
            return readProductName();
        }
    }

    private Money readInsertedMoney() {
        try {
            return inputView.readInsertedMoney();
        } catch (IllegalArgumentException error) {
            outputView.printError(error);
            return readInsertedMoney();
        }
    }

    private Products readProducts() {
        try {
            return inputView.readProducts();
        } catch (IllegalArgumentException error) {
            outputView.printError(error);
            return readProducts();
        }
    }

    private HoldingCoins getHoldingCoins() {
        CoinConverter converter = new CoinConverter(new RandomCoinAmountGenerator());
        List<Coin> coins = converter.convert(readMoney());

        return HoldingCoins.from(coins);
    }

    private Money readMoney() {
        try {
            return inputView.readHoldingMoney();
        } catch (IllegalArgumentException error) {
            outputView.printError(error);
            return readMoney();
        }
    }
}
