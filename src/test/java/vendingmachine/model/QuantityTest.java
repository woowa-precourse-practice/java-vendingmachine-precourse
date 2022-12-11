package vendingmachine.model;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class QuantityTest {

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "10j"})
    void 상품_수량_유효성_검증(String quantity) {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> Quantity.from(quantity));
    }
}
