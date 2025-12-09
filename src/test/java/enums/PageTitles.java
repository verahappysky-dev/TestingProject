package enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum PageTitles {
    PRODUCTS("Products"),
    CART("Your cart"),
    CHECKOUT("Checkout: Your Information");

    private final String displatName;
}