package vendingmachine;

import camp.nextstep.edu.missionutils.Randoms;

public class RandomCoinAmountGenerator implements CoinAmountGenerator {

    @Override
    public int generate() {
        return Randoms.pickNumberInList(Coin.amounts());
    }
}
