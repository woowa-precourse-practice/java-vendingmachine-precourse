package vendingmachine.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

public class ProductsTest {

    Products products;

    @BeforeEach
    void setUp() {
        products = Products.of("[사과,100,20];[배,500,10]");
    }

    @DisplayName("상품 이름으로 해당 상품을 반환받는 기능")
    @Test
    void findByNameTest() {
        assertThat(products.findByName("사과"))
                .usingRecursiveComparison()
                .isEqualTo(Product.from("[사과,100,20]"));
    }

    @DisplayName("잘못된 상품 이름 입력 예외 테스트")
    @ParameterizedTest
    @ValueSource(strings = {"사고", "없는 상품", "", " "})
    void cannotFindName(String name) {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> products.findByName(name));
    }
}
