package vendingmachine;

import camp.nextstep.edu.missionutils.Randoms;
import vendingmachine.model.Coin;

import java.util.List;

public class RandomCoinAmountGenerator implements CoinAmountGenerator {

    private static final List<Integer> AMOUNTS = Coin.amounts();

    @Override
    public int generate() {
        return Randoms.pickNumberInList(AMOUNTS);
    }
}
