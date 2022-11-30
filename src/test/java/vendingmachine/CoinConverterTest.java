package vendingmachine;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import vendingmachine.model.Money;

import java.util.List;
import java.util.Map;


public class CoinConverterTest {

    @DisplayName("보유 금액을 임의의 동전으로 변환하는 기능 테스트")
    @Test
    void convertToCoinsTest() {
        CoinAmountGenerator amountGenerator = new TestCoinAmountGenerator();
        CoinConverter converter = new CoinConverter(amountGenerator);

        List<Coin> coins = converter.convert(Money.from("760"));

        Assertions.assertThat(coins)
                .containsExactly(
                        Coin.COIN_500,
                        Coin.COIN_100,
                        Coin.COIN_100,
                        Coin.COIN_50,
                        Coin.COIN_10
                );
    }

    @DisplayName("금액을 최소한의 동전으로 변환하는 기능 추가")
    @Test
    void convertOptimalTest() {
        CoinAmountGenerator amountGenerator = new TestCoinAmountGenerator();
        CoinConverter converter = new CoinConverter(amountGenerator);

        Map<Coin, Integer> convertCounts = converter.convertOptimal(Money.from("900"));

        Assertions.assertThat(convertCounts)
                .containsEntry(Coin.COIN_500, 1)
                .containsEntry(Coin.COIN_100, 4);
    }
}
