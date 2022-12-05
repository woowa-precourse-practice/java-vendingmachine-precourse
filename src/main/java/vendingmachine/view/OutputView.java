package vendingmachine.view;

import vendingmachine.model.Coin;
import vendingmachine.model.HoldingCoins;
import vendingmachine.model.VendingMachine;

import java.util.Arrays;
import java.util.stream.Collectors;

public class OutputView {

    private static final String NEW_LINE = "\n";
    private static final String RESULT_FORMAT = "%d원 - %d개";
    private static final String BALANCE_IS = "투입금액: %s원";

    public void printHoldingCoins(HoldingCoins holdingCoins) {
        System.out.println(getStatisticOf(holdingCoins));
    }

    private String getStatisticOf(HoldingCoins holdingCoins) {
        return Arrays.stream(Coin.values())
                .map(coin -> String.format(RESULT_FORMAT, coin.getAmount(), holdingCoins.count(coin)))
                .collect(Collectors.joining(NEW_LINE));
    }

    public void printBalance(VendingMachine vendingMachine) {
        System.out.println(String.format(BALANCE_IS, vendingMachine.getBalance()));
    }
}
