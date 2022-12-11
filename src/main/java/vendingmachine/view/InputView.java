package vendingmachine.view;

import camp.nextstep.edu.missionutils.Console;
import vendingmachine.model.Money;
import vendingmachine.model.Name;
import vendingmachine.model.Products;

public class InputView {

    private static final String REQUEST_HOLDING_MONEY_MESSAGE = "자판기가 보유하고 있는 금액을 입력해 주세요.";
    private static final String REQUEST_BALANCE_MESSAGE = "투입 금액을 입력해 주세요.";
    private static final String REQUEST_PRODUCTS_MESSAGE = "상품명과 가격, 수량을 입력해 주세요.";
    private static final String REQUEST_NAME_MESSAGE = "구매할 상품명을 입력해 주세요.";

    public Money readMoney() {
        System.out.println(REQUEST_HOLDING_MONEY_MESSAGE);

        return Money.from(Console.readLine());
    }

    public Money readBalance() {
        System.out.println(REQUEST_BALANCE_MESSAGE);

        return Money.from(Console.readLine());
    }

    public Products readProducts() {
        System.out.println(REQUEST_PRODUCTS_MESSAGE);

        return Products.from(Console.readLine());
    }

    public Name readName() {
        System.out.println(REQUEST_NAME_MESSAGE);

        return Name.from(Console.readLine());
    }
}
