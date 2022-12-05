package vendingmachine.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.*;

public class ProductTest {

    @DisplayName("상품 정보 유효성 검증 테스트")
    @ParameterizedTest
    @ValueSource(strings = {"[사과,500,10", "사과,500,10]", "사과,500,10", "[사과,500]", "[사과,500,10,10]"})
    void createInvalidProductInfo(String product) {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> Product.from(product));
    }
}
