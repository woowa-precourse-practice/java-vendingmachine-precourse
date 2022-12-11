package vendingmachine.view;

import vendingmachine.model.Coin;
import vendingmachine.model.HoldingCoins;
import vendingmachine.model.Money;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {

    private static final String NEW_LINE = "\n";
    private static final String RESULT_FORMAT = "%d원 - %d개";
    private static final String BALANCE_IS = "투입 금액: %s원\n";
    private static final String ERROR_PREFIX = "[ERROR] ";
    private static final String HOLDING_COINS_ARE = "자판기가 보유한 동전";

    public void printHoldingCoins(HoldingCoins holdingCoins) {
        System.out.println(HOLDING_COINS_ARE);
        System.out.println(getStatisticOf(holdingCoins));
    }

    private String getStatisticOf(HoldingCoins holdingCoins) {
        return Arrays.stream(Coin.values())
                .map(coin -> String.format(RESULT_FORMAT, coin.getAmount(), holdingCoins.count(coin)))
                .collect(Collectors.joining(NEW_LINE));
    }

    public void printBalance(Money balance) {
        System.out.printf(BALANCE_IS, balance);
    }

    public void printError(IllegalArgumentException error) {
        System.out.print(ERROR_PREFIX);
        System.out.println(error.getMessage());
    }

    public void printBalanceCoins(Map<Coin, Integer> coins) {
        System.out.println(getCoinsInfo(coins));
    }

    private String getCoinsInfo(Map<Coin, Integer> coins) {
        return coins.keySet().stream()
                .map(coin -> String.format(RESULT_FORMAT, coin.getAmount(), coins.get(coin)))
                .collect(Collectors.joining(NEW_LINE));
    }
}
