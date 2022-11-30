package vendingmachine;

import org.assertj.core.util.Lists;

import java.util.List;

public class TestCoinAmountGenerator implements CoinAmountGenerator {

    private final List<Integer> amounts;

    public TestCoinAmountGenerator() {
        this.amounts = Lists.newArrayList(500, 100, 100, 50, 10);
    }

    @Override
    public int generate() {
        return amounts.remove(0);
    }
}
