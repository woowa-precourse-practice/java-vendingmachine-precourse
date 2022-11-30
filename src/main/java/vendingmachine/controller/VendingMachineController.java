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
        Money money = inputView.readHoldingMoney();
        CoinConverter converter = new CoinConverter(new RandomCoinAmountGenerator());
        List<Coin> coins = converter.convert(money);
        HoldingCoins holdingCoins = HoldingCoins.from(coins);

        outputView.printHoldingCoins(holdingCoins);

        Products products = inputView.readProducts();
        Money insertedMoney = inputView.readInsertedMoney();

        VendingMachine vendingMachine = VendingMachine.of(products, holdingCoins);
        vendingMachine.insertMoney(insertedMoney);

        while (vendingMachine.purchasable()) {
            outputView.printInsertedMoney(vendingMachine);
            String productName = inputView.readProductName();
            vendingMachine.purchase(productName);
        }
        outputView.printInsertedMoney(vendingMachine);
        outputView.printBalanceCoin(vendingMachine.convertBalanceToCoins());
    }
}
