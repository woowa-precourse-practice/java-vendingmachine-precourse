package vendingmachine.view;

import camp.nextstep.edu.missionutils.Console;
import vendingmachine.model.Money;

public class InputView {

    private static final String REQUEST_HOLDING_MONEY = "자판기가 보유하고 있는 금액을 입력해 주세요.";

    public Money readHoldingMoney() {
        System.out.println(REQUEST_HOLDING_MONEY);

        return Money.from(Console.readLine());
    }
}
