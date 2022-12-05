package vendingmachine.view;

import camp.nextstep.edu.missionutils.Console;
import vendingmachine.model.Money;

public class InputView {

    private static final String REQUEST_HOLDING_MONEY_MESSAGE = "자판기가 보유하고 있는 금액을 입력해 주세요.";
    private static final String REQUEST_BALANCE_MESSAGE = "투입 금액을 입력해 주세요.";

    public Money readMoney() {
        System.out.println(REQUEST_HOLDING_MONEY_MESSAGE);

        return Money.from(Console.readLine());
    }

    public Money readBalance() {
        System.out.println(REQUEST_BALANCE_MESSAGE);

        return Money.from(Console.readLine());
    }
}
