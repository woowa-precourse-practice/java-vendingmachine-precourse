package vendingmachine.model;

public class Name {

    private static final String INVALID_BLANK = "이름에 공백을 입력할 수 없습니다";
    
    private final String name;

    public Name(String name) {
        validate(name);
        this.name = name;
    }

    private void validate(String name) {
        if (name.isBlank()) {
            throw new IllegalArgumentException(INVALID_BLANK);
        }
    }

    public static Name from(String name) {
        return new Name(name);
    }
}
