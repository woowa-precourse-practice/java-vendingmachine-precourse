package vendingmachine.model;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class HoldingCoinsTest {

    @ParameterizedTest(name = "동전: {0}, 갯수: {1}")
    @MethodSource("coinAndExpectedCountSource")
    void 보유_금액을_동전으로_바꿀_수_있다(Coin coin, int expected) {
        TestCoinAmountGenerator testCoinAmountGenerator = new TestCoinAmountGenerator();
        CoinMaker coinMaker = new CoinMaker(testCoinAmountGenerator);
        List<Coin> coins = coinMaker.makeCoins(Money.from("2000"));

        HoldingCoins holdingCoins = HoldingCoins.from(coins);

        Assertions.assertThat(holdingCoins.count(coin)).isEqualTo(expected);
    }

    public static Stream<Arguments> coinAndExpectedCountSource() {
        return Stream.of(
                Arguments.arguments(Coin.COIN_500, 3),
                Arguments.arguments(Coin.COIN_100, 4),
                Arguments.arguments(Coin.COIN_50, 1),
                Arguments.arguments(Coin.COIN_10, 5)
        );
    }
}
