package vendingmachine.view;

import vendingmachine.model.Coin;
import vendingmachine.model.HoldingCoins;
import vendingmachine.model.VendingMachine;

import java.util.Map;

public class OutputView {

    private static final String HOLDING_COINS_INTRO = "자판기가 보유한 동전";
    private static final String COIN_500_STATISTIC = "500원 - %d개\n";
    private static final String COIN_100_STATISTIC = "100원 - %d개\n";
    private static final String COIN_50_STATISTIC = "50원 - %d개\n";
    private static final String COIN_10_STATISTIC = "10원 - %d개\n";
    private static final String INSERTED_MONEY_IS = "투입 금액: %d원\n";
    private static final String BALANCE_COIN_IS = "잔돈";
    private static final String BALANCE_COIN_INFO = "%d원 - %d개\n";
    private static final String ERROR_PREFIX = "[ERROR] ";

    public void printHoldingCoins(HoldingCoins holdingCoins) {
        System.out.println(HOLDING_COINS_INTRO);
        System.out.printf(COIN_500_STATISTIC, holdingCoins.count(Coin.COIN_500));
        System.out.printf(COIN_100_STATISTIC, holdingCoins.count(Coin.COIN_100));
        System.out.printf(COIN_50_STATISTIC, holdingCoins.count(Coin.COIN_50));
        System.out.printf(COIN_10_STATISTIC, holdingCoins.count(Coin.COIN_10));
    }

    public void printInsertedMoney(VendingMachine vendingMachine) {
        System.out.printf(INSERTED_MONEY_IS, vendingMachine.getBalance());
    }

    public void printBalanceCoin(Map<Coin, Integer> coinsOfBalance) {
        System.out.println(BALANCE_COIN_IS);

        StringBuilder message = new StringBuilder();
        coinsOfBalance.keySet()
                .forEach(coin -> message.append(String.format(BALANCE_COIN_INFO, coin.getAmount(), coinsOfBalance.get(coin))));

        System.out.println(message);
    }

    public void printError(IllegalArgumentException error) {
        System.out.print(ERROR_PREFIX);
        System.out.println(error.getMessage());
    }
}
