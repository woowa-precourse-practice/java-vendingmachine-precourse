package vendingmachine.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import vendingmachine.CoinConverter;
import vendingmachine.TestCoinAmountGenerator;

import static org.assertj.core.api.Assertions.assertThat;

public class VendingMachineTest {

    @DisplayName("상품을 구매하면 수량이 줄어든다.")
    @Test
    void buyProductThenQuantityDecrease() {
        Products products = Products.of("[사과,100,20];[배,500,10]");
        CoinConverter converter = new CoinConverter(new TestCoinAmountGenerator());
        HoldingCoins holdingCoins = HoldingCoins.from(converter.convert(Money.from("760")));

        VendingMachine vendingMachine = VendingMachine.of(products, holdingCoins);

        vendingMachine.insertMoney(Money.from("5000"));
        vendingMachine.purchase("사과");


        assertThat(products.findByName("사과"))
                .usingRecursiveComparison()
                .isEqualTo(Product.from("[사과,100,19]"));
    }

    @DisplayName("상품을 구매하면 투입 금액이 줄어든다.")
    @Test
    void buyProductThenInsertedMoneyDecrease() {
        Products products = Products.of("[사과,100,20];[배,500,10]");
        CoinConverter converter = new CoinConverter(new TestCoinAmountGenerator());
        HoldingCoins holdingCoins = HoldingCoins.from(converter.convert(Money.from("760")));

        VendingMachine vendingMachine = VendingMachine.of(products, holdingCoins);

        vendingMachine.insertMoney(Money.from("5000"));
        vendingMachine.purchase("사과");


        assertThat(vendingMachine.getInsertedMoney()).isEqualTo(4900);
    }
}
