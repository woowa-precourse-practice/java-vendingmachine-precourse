package vendingmachine.view;

import vendingmachine.Coin;
import vendingmachine.model.HoldingCoins;

public class OutputView {

    private static final String HOLDING_COINS_INTRO = "자판기가 보유한 동전";
    private static final String COIN_500_STATISTIC = "500원 - %d개\n";
    private static final String COIN_100_STATISTIC = "100원 - %d개\n";
    private static final String COIN_50_STATISTIC = "50원 - %d개\n";
    private static final String COIN_10_STATISTIC = "10원 - %d개\n";

    public void printHoldingCoins(HoldingCoins holdingCoins) {
        System.out.println(HOLDING_COINS_INTRO);
        System.out.printf(COIN_500_STATISTIC, holdingCoins.count(Coin.COIN_500));
        System.out.printf(COIN_100_STATISTIC, holdingCoins.count(Coin.COIN_100));
        System.out.printf(COIN_50_STATISTIC, holdingCoins.count(Coin.COIN_50));
        System.out.printf(COIN_10_STATISTIC, holdingCoins.count(Coin.COIN_10));
    }
}
