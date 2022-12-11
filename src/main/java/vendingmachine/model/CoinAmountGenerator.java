package vendingmachine.model;

public interface CoinAmountGenerator {

    int generate();

    default void update(Money money) {
    }
}
