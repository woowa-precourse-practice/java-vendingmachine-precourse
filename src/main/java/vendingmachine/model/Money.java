package vendingmachine.model;

public class Money {

    private static final String NON_NUMERIC = "숫자만 입력할 수 있습니다.";
    private static final String INVALID_MONEY_RANGE = "금액은 마이너스로 입력할 수 없습니다.";
    private static final int NO_MONEY = 0;

    private final int money;

    public Money(String input) {
        int money = parseInt(input);
        validate(money);
        this.money = money;
    }

    private void validate(int money) {
        validateRange(money);
    }

    private int parseInt(String money) {
        try {
            return Integer.parseInt(money);
        } catch (NumberFormatException error) {
            throw new IllegalArgumentException(NON_NUMERIC);
        }
    }

    private void validateRange(int money) {
        if (money < NO_MONEY) {
            throw new IllegalArgumentException(INVALID_MONEY_RANGE);
        }
    }

    public static Money from(String money) {
        return new Money(money);
    }
}
