package tests;

import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.TmsLink;
import org.testng.annotations.Test;
import user.UserFactory;

import static enums.PageTitles.PRODUCTS;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class ProductsTest extends BaseTest {

    @TmsLink("TestingProject")
    @Severity(SeverityLevel.NORMAL)
    @Test(description = "Проверка кнопки 'Добавить в корзину'")
    public void checkGoodsAdded() throws InterruptedException {
        loginPage.open();
        loginPage.login(UserFactory.withAdminPermission());
        productsPage.isPageLoaded(PRODUCTS.getDisplatName());
        productsPage.addToCart(0);
        productsPage.addToCart("Sauce Labs Bike Light");
        assertEquals(productsPage.checkGoodsQuantity(), "2");
    }

    @TmsLink("TestingProject")
    @Severity(SeverityLevel.NORMAL)
    @Test(description = "Проверка все кнопок 'Добавить в корзину' на странице")
    public void checkAllGoodsAdded() throws InterruptedException {
        loginPage.open();
        loginPage.login(UserFactory.withAdminPermission());
        productsPage.isPageLoaded(PRODUCTS.getDisplatName());
        assertTrue(productsPage.addAllGoodsToCart());
    }

}
