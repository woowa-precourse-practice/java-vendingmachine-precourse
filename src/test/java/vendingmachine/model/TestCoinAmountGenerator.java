package vendingmachine.model;

import java.util.List;

import static org.assertj.core.util.Lists.newArrayList;

public class TestCoinAmountGenerator implements CoinAmountGenerator {

    final List<Integer> amounts = newArrayList(500, 500, 500, 100, 100, 100, 100, 50, 10, 10, 10, 10, 10);

    @Override
    public int generate() {
        return amounts.remove(0);
    }
}
