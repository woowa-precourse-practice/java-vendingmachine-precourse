package vendingmachine.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class VendingMachineTest {

    VendingMachine vendingMachine;

    @BeforeEach
    void setUp() {
        HoldingCoins holdingCoins = HoldingCoins.from(Money.from("1000"), new TestCoinAmountGenerator());
        Products products = Products.from("[사과,100,1];[배,950,1]");
        Money balance = Money.from("1000");

        vendingMachine = VendingMachine.of(holdingCoins, products, balance);
    }

    @Test
    void 상품을_구매하면_재고가_줄어든다() {
        vendingMachine.buy(Name.from("사과"));

        Money actualBalance = vendingMachine.getBalance();
        Money expectedBalance = Money.from("900");

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

    @Test
    void 구매_가능_여부를_확인할_수_있다() {
        vendingMachine.buy(Name.from("배"));

        assertThat(vendingMachine.isPurchasable()).isFalse();
    }
}
