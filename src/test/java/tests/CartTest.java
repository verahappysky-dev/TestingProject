package tests;


import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.TmsLink;
import org.testng.annotations.Test;

import static enums.PageTitles.CART;
import static enums.PageTitles.PRODUCTS;
import static org.testng.Assert.*;
import static user.UserFactory.withAdminPermission;

public class CartTest extends BaseTest {


    @TmsLink("TestingProject")
    @Severity(SeverityLevel.NORMAL)
    @Test(description = "Проверка добавления товаров в корзину")
    public void checkGoodsInCart() {
        System.out.println("CartTest inc is running in thread: " + Thread.currentThread().getId());

        loginPage.open();
        loginPage.login(withAdminPermission());
        productsPage.isPageLoaded(PRODUCTS.getDisplatName());
        productsPage.addToCart("Test.allTheThings() T-Shirt (Red)");
        productsPage.addToCart("Sauce Labs Bolt T-Shirt");
        productsPage.switchToCart();

        assertTrue(cartPage.isPageLoaded(CART.getDisplatName()));
        assertEquals(cartPage.getProductsNames().size(), 2);
        assertFalse(cartPage.getProductsNames().isEmpty());
        assertTrue(cartPage.getProductsNames().contains("Sauce Labs Bolt T-Shirt"));
    }


}
