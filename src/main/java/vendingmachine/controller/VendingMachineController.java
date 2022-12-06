package vendingmachine.controller;

import vendingmachine.model.CoinAmountGenerator;
import vendingmachine.model.CoinMaker;
import vendingmachine.model.HoldingCoins;
import vendingmachine.model.Money;
import vendingmachine.model.Name;
import vendingmachine.model.Products;
import vendingmachine.model.RandomCoinAmountGenerator;
import vendingmachine.model.VendingMachine;
import vendingmachine.view.InputView;
import vendingmachine.view.OutputView;

import java.util.function.Supplier;

public class VendingMachineController {

    private final InputView inputView;
    private final OutputView outputView;
    private final VendingMachine vendingMachine;

    public VendingMachineController() {
        inputView = new InputView();
        outputView = new OutputView();
        vendingMachine = initVendingMachine();
    }

    private VendingMachine initVendingMachine() {
        HoldingCoins holdingCoins = initHoldingCoins();

        Products products = checkError(inputView::readProducts);
        Money balance = checkError(inputView::readBalance);

        return VendingMachine.of(holdingCoins, products, balance);
    }

    private HoldingCoins initHoldingCoins() {
        HoldingCoins holdingCoins = getHoldingCoins();
        outputView.printHoldingCoins(holdingCoins);

        return holdingCoins;
    }

    private HoldingCoins getHoldingCoins() {
        CoinAmountGenerator amountGenerator = new RandomCoinAmountGenerator();
        CoinMaker coinMaker = new CoinMaker(amountGenerator);
        Money money = checkError(inputView::readMoney);

        return HoldingCoins.from(coinMaker.makeCoins(money));
    }

    public void run() {
        while (vendingMachine.isPurchasable()) {
            outputView.printBalance(vendingMachine);

            Name name = checkError(inputView::readName);
            vendingMachine.buy(name);
        }
        outputView.printBalance(vendingMachine);
        outputView.printBalanceCoins(vendingMachine.changeBalance());
    }

    private <T> T checkError(Supplier<T> inputReader) {
        try {
            return inputReader.get();
        } catch (IllegalArgumentException error) {
            outputView.printError(error);
            return checkError(inputReader);
        }
    }
}
