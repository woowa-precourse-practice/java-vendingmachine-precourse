package vendingmachine.model;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Products {

    private static final String PRODUCT_SEPARATOR = ";";
    private static final String PRODUCT_NOT_FOUND = "해당 상품은 존재하지 않습니다.";

    private final List<Product> products;

    public Products(String products) {
        this.products = Arrays.stream(products.split(PRODUCT_SEPARATOR))
                .map(Product::from)
                .collect(Collectors.toList());
    }

    public static Products of(String products) {
        return new Products(products);
    }

    public Product findByName(String name) {
        return products.stream()
                .filter(product -> product.is(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(PRODUCT_NOT_FOUND));
    }

    public int getCheapestPrice() {
        return products.stream()
                .mapToInt(product -> product.getPrice().getAmount())
                .min()
                .getAsInt();
    }

    public boolean hasStock() {
        return products.stream()
                .anyMatch(Product::hasStock);
    }
}
