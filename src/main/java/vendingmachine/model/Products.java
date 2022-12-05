package vendingmachine.model;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Products {

    private static final String INVALID_PRODUCTS_DELIMITER = "상품 묶음은 ; 로 구분되어야 합니다.";
    private static final String PRODUCTS_REGEX = ";";
    private static final int BEGIN_INDEX = 1;
    private static final String BLANK = "";
    private static final String PRODUCTS_FRONT_WRAPPER = "[";
    private static final String DUPLICATED_PRODUCT_NAME = "상품명은 서로 중복될 수 없습니다.";
    private static final String NO_SUCH_PRODUCT_MESSAGE = "해당 상품이 존재하지 않습니다.";

    private final List<Product> products;

    public Products(String bundle) {
        List<Product> products = toProducts(bundle);
        validateDuplicated(products);

        this.products = products;
    }

    private void validateDuplicated(List<Product> products) {
        if (hasDuplicatedName(products)) {
            throw new IllegalArgumentException(DUPLICATED_PRODUCT_NAME);
        }
    }

    private boolean hasDuplicatedName(List<Product> products) {
        return countUniqueNames(products) != products.size();
    }

    private int countUniqueNames(List<Product> products) {
        return (int) products.stream()
                .map(Product::getName)
                .distinct()
                .count();
    }

    private List<Product> toProducts(String bundle) {
        return Arrays.stream(bundle.split(PRODUCTS_REGEX))
                .map(this::unwrap)
                .map(Product::of)
                .collect(Collectors.toList());
    }

    private String unwrap(String product) {
        validateWrapping(product);

        return product.substring(BEGIN_INDEX, product.length() - 1);
    }

    private void validateWrapping(String product) {
        if (isProperlyWrapped(product)) {
            throw new IllegalArgumentException(INVALID_PRODUCTS_DELIMITER);
        }
    }

    private boolean isProperlyWrapped(String product) {
        return countChar(product) > 1 && !product.contains(PRODUCTS_REGEX);
    }

    private int countChar(String input) {
        return (int) Arrays.stream(input.split(BLANK))
                .filter(character -> character.equals(PRODUCTS_FRONT_WRAPPER))
                .distinct()
                .count();
    }

    public static Products from(String products) {
        return new Products(products);
    }

    public Product findByName(Name name) {
        return products.stream()
                .filter(product -> product.hasSameName(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(NO_SUCH_PRODUCT_MESSAGE));
    }
}
