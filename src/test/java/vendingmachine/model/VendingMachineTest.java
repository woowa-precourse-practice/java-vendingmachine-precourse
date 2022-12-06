package vendingmachine.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class VendingMachineTest {

    VendingMachine vendingMachine;

    @BeforeEach
    void setUp() {
        HoldingCoins holdingCoins = HoldingCoins.from(Money.from("1000"), new TestCoinAmountGenerator());
        Products products = Products.from("[사과,100,1];[배,150,1]");
        Money balance = Money.from("200");

        vendingMachine = VendingMachine.of(holdingCoins, products, balance);
    }

    @Test
    void 상품을_구매하면_재고가_줄어든다() {
        vendingMachine.buy(Name.from("사과"));

        Money actualBalance = vendingMachine.getBalance();
        Money expectedBalance = Money.from("100");

        assertThat(actualBalance).isEqualTo(expectedBalance);
    }

    @Test
    void 해당_상품이_존재하지_않으면_예외가_발생한다() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> vendingMachine.buy(Name.from("낑깡")));
    }

    @Test
    void 잔액이_부족하면_상품을_구매할_수_없다() {
        vendingMachine.buy(Name.from("배"));

        assertThatIllegalArgumentException()
                .isThrownBy(() -> vendingMachine.buy(Name.from("사과")));
    }

    @Test
    void 재고가_부족하면_상품을_구매할_수_없다() {
        vendingMachine.buy(Name.from("사과"));

        assertThatIllegalArgumentException()
                .isThrownBy(() -> vendingMachine.buy(Name.from("사과")));
    }

    @ParameterizedTest
    @ValueSource(strings = {"사과", "배"})
    void 구매_가능_여부를_확인할_수_있다_(String name) {
        vendingMachine.buy(Name.from(name));

        assertThat(vendingMachine.isPurchasable()).isFalse();
    }
}
