package vendingmachine.utils;

public class NumberUtils {

    private static final String NON_NUMERIC = "숫자만 입력할 수 있습니다.";

    public static int parseInt(String money) {
        try {
            return Integer.parseInt(money);
        } catch (NumberFormatException error) {
            throw new IllegalArgumentException(NON_NUMERIC);
        }
    }
}
