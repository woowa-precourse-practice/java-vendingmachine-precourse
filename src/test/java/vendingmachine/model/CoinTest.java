package vendingmachine.model;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class CoinTest {

    @ParameterizedTest(name = "금액 단위: {0}, 동전: {1}")
    @MethodSource("coinAmountAndResultSource")
    void 특정_금액을_특정_단위의_동전으로_바꿀_수_있다(int amount, Coin expected) {
        Coin actual = Coin.create(amount);

        assertThat(actual).isEqualTo(expected);
    }

    public static Stream<Arguments> coinAmountAndResultSource() {
        return Stream.of(
                Arguments.arguments(500, Coin.COIN_500),
                Arguments.arguments(100, Coin.COIN_100),
                Arguments.arguments(50, Coin.COIN_50),
                Arguments.arguments(10, Coin.COIN_10)
        );
    }
}
