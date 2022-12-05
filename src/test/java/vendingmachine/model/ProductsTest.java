package vendingmachine.model;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.Assertions.assertThatNoException;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class ProductsTest {

    @ParameterizedTest
    @ValueSource(strings = {"[사과,100,10];[사과,200,20]", "[사과,100,10]:[배,200,20]"})
    void 상품_묶음_유효성_검증(String products) {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> Products.from(products));
    }

    @Test
    void 정상적인_상품_묶음_테스트() {
        assertThatNoException()
                .isThrownBy(() -> Products.from("[사과,100,10];[배,200,20]"));
    }
}
