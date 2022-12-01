package vendingmachine.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

public class MoneyTest {

    @DisplayName("금액 유효성 검증 테스트")
    @ParameterizedTest
    @ValueSource(strings = {"", " ", "-100"})
    void createInvalidMoney(String money) {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> Money.from(money));
    }
}
