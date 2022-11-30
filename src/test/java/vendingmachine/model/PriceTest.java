package vendingmachine.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

public class PriceTest {

    @DisplayName("상품 가격 유효성 검증 테스트")
    @ParameterizedTest
    @ValueSource(strings = {"", " ", "-100", "99", "101"})
    void createInvalidMoney(String price) {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> Price.from(price));
    }
}
