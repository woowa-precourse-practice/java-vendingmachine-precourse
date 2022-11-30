package vendingmachine.model;

public class Product {

    private static final String INVALID_PRODUCT_WRAPPING = "상품은 대괄호로 묶여있어야 합니다.";
    private static final String INVALID_PRODUCT_CONTENTS = "상품 정보는 상품명, 가격, 수량으로 구성되어야 합니다.";
    private static final String CONTENT_SEPARATOR = ",";
    private static final char PRODUCT_START = '[';
    private static final char PRODUCT_END = ']';
    private static final int CONTENTS_SIZE = 3;
    private static final int FIRST_LETTER = 0;
    private static final int BEGIN_INDEX = 1;
    private static final int NAME_INDEX = 0;
    private static final int PRICE_INDEX = 1;
    private static final int QUANTITY_INDEX = 2;

    private final String name;
    private final Price price;
    private final Quantity quantity;

    public Product(String product) {
        validate(product);
        String[] contents = divideContents(product);

        this.name = contents[NAME_INDEX];
        this.price = Price.from(contents[PRICE_INDEX]);
        this.quantity = Quantity.from(contents[QUANTITY_INDEX]);
    }

    private void validate(String product) {
        validateWrapping(product);
        validateContents(product);
    }


    private void validateWrapping(String product) {
        if (isInvalidWrapping(product)) {
            throw new IllegalArgumentException(INVALID_PRODUCT_WRAPPING);
        }
    }

    private void validateContents(String product) {
        if (isInvalidContents(product)) {
            throw new IllegalArgumentException(INVALID_PRODUCT_CONTENTS);
        }
    }

    private boolean isInvalidWrapping(String product) {
        return product.charAt(FIRST_LETTER) != PRODUCT_START || product.charAt(product.length() - 1) != PRODUCT_END;
    }

    private boolean isInvalidContents(String product) {
        return divideContents(product).length != CONTENTS_SIZE;
    }

    private String[] divideContents(String product) {
        return unwrapping(product).split(CONTENT_SEPARATOR);
    }

    private String unwrapping(String product) {
        return product.substring(BEGIN_INDEX, product.length() - 1);
    }

    public static Product from(String product) {
        return new Product(product);
    }

    public boolean is(String name) {
        return this.name.equals(name);
    }

    public boolean isAvailable(Money money) {
        return quantity.hasStock() && price.isAvailable(money);
    }

    public int getPrice() {
        return price.getAmount();
    }

    public void decreaseQuantity() {
        quantity.decrease();
    }

    public boolean hasStock() {
        return quantity.hasStock();
    }
}
