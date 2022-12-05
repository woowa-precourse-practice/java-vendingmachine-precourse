package vendingmachine.model;

public class Product {

    private static final int NAME_INDEX = 0;
    private static final int PRICE_INDEX = 1;
    private static final int QUANTITY_INDEX = 2;

    private final Name name;
    private final Price price;
    private final Quantity quantity;

    public Product(String input) {
        String[] productInformation = input.split(",");

        name = Name.from(productInformation[NAME_INDEX]);
        price = Price.from(productInformation[PRICE_INDEX]);
        quantity = Quantity.from(productInformation[QUANTITY_INDEX]);

    }

    public static Product of(String input) {
        return new Product(input);
    }

    public Name getName() {
        return name;
    }
}
