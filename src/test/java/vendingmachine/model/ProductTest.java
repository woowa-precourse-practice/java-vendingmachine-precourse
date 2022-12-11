package vendingmachine.model;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.Assertions.assertThatNoException;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class ProductTest {

    @ParameterizedTest
    @ValueSource(strings = {"사과,,10", "사과,100,", ",100,10", "사과,100", "사과"})
    void 상품_정보_유효성_검증(String input) {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> Product.of(input));
    }

    @Test
    void 정상적인_상품_정보() {
        assertThatNoException()
                .isThrownBy(() -> Product.of("사과,100,10"));
    }
}
