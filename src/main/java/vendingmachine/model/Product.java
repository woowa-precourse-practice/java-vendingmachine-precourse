package vendingmachine.model;

import java.util.Arrays;
import java.util.List;

public class Product implements Comparable<Product> {

    private static final int NAME_INDEX = 0;
    private static final int PRICE_INDEX = 1;
    private static final int QUANTITY_INDEX = 2;
    private static final String MISSING_PRODUCT_INFORMATION = "상품 정보가 누락되었습니다.";
    private static final int PRODUCT_INFORMATION_SIZE = 3;
    private static final String INFORMATION_REGEX = ",";
    private static final String OUT_OF_STOCK_MESSAGE = "재고가 없습니다.";

    private final Name name;
    private final Price price;
    private Quantity quantity;

    public Product(String input) {
        List<String> productInformation = Arrays.asList(input.split(INFORMATION_REGEX));
        validate(productInformation);

        name = Name.from(productInformation.get(NAME_INDEX));
        price = Price.from(productInformation.get(PRICE_INDEX));
        quantity = Quantity.from(productInformation.get(QUANTITY_INDEX));
    }

    private void validate(List<String> productInformation) {
        if (hasValidSize(productInformation)) {
            throw new IllegalArgumentException(MISSING_PRODUCT_INFORMATION);
        }
    }

    private boolean hasValidSize(List<String> productInformation) {
        return productInformation.size() != PRODUCT_INFORMATION_SIZE;
    }

    public static Product of(String input) {
        return new Product(input);
    }

    public Name getName() {
        return name;
    }

    public boolean hasSameName(Name name) {
        return this.name.equals(name);
    }

    public void decreaseQuantity() {
        if (quantity.isEmpty()) {
            throw new IllegalArgumentException(OUT_OF_STOCK_MESSAGE);
        }
        this.quantity = quantity.decrease(Quantity.from("1"));
    }

    public int getPrice() {
        return price.getAmount();
    }

    public boolean isOutOfStock() {
        return quantity.isEmpty();
    }

    public boolean isPurchasableWith(int amount) {
        return price.isPurchasableWith(amount);
    }

    @Override
    public int compareTo(Product other) {
        return this.price.compareTo(other.price);
    }
}
