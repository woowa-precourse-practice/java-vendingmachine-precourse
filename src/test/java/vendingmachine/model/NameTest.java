package vendingmachine.model;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class NameTest {

    @ParameterizedTest
    @ValueSource(strings = {"", " "})
    void 상품_이름_유효성_검증(String name) {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> Name.from(name));
    }
}
