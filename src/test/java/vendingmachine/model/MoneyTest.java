package vendingmachine.model;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class MoneyTest {

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "100j"})
    void 금액_유효성_검증(String money) {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> Money.from(money));
    }
}
