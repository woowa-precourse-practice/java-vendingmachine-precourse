package vendingmachine.model;

import camp.nextstep.edu.missionutils.Randoms;

import java.util.List;

public class RandomCoinAmountGenerator implements CoinAmountGenerator {

    private static final List<Integer> amounts = Coin.amounts();

    @Override
    public int generate() {
        return Randoms.pickNumberInList(amounts);
    }
}
