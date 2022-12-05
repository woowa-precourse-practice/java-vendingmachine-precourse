package vendingmachine.model;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class PriceTest {

    @ParameterizedTest
    @ValueSource(strings = {"99", "101", "100j"})
    void 상품_가격_유효성_검증(String amount) {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> Price.from(amount));
    }
}
