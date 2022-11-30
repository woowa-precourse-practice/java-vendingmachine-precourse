package vendingmachine.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import vendingmachine.Coin;
import vendingmachine.CoinConverter;
import vendingmachine.TestCoinAmountGenerator;

import static org.assertj.core.api.Assertions.assertThat;

public class VendingMachineTest {

    Products products;
    HoldingCoins holdingCoins;

    @BeforeEach
    void setUp() {
        products = getProducts();
        holdingCoins = getHoldingCoins();
    }

    private static Products getProducts() {
        return Products.of("[사과,100,20];[배,500,10]");
    }

    private static HoldingCoins getHoldingCoins() {
        CoinConverter converter = new CoinConverter(new TestCoinAmountGenerator());

        return HoldingCoins.from(converter.convert(Money.from("760")));
    }

    @DisplayName("상품을 구매하면 수량이 줄어든다.")
    @Test
    void buyProductThenQuantityDecrease() {
        Money insertedMoney = Money.from("5000");
        VendingMachine vendingMachine = VendingMachine.of(products, holdingCoins, insertedMoney);

        vendingMachine.purchase("사과");

        assertThat(products.findByName("사과"))
                .usingRecursiveComparison()
                .isEqualTo(Product.from("[사과,100,19]"));
    }

    @DisplayName("상품을 구매하면 투입 금액이 줄어든다.")
    @Test
    void buyProductThenInsertedMoneyDecrease() {
        Money insertedMoney = Money.from("5000");
        VendingMachine vendingMachine = VendingMachine.of(products, holdingCoins, insertedMoney);

        vendingMachine.purchase("사과");

        assertThat(vendingMachine.getBalance()).isEqualTo(4900);
    }

    @DisplayName("잔돈을 최소 동전으로 반환하는 기능")
    @Test
    void convertBalance() {
        Money insertedMoney = Money.from("900");
        VendingMachine vendingMachine = VendingMachine.of(products, holdingCoins, insertedMoney);

        assertThat(vendingMachine.convertBalanceToCoins())
                .containsEntry(Coin.COIN_500, 1)
                .containsEntry(Coin.COIN_100, 2)
                .containsEntry(Coin.COIN_50, 1)
                .containsEntry(Coin.COIN_10, 1);
    }
}
