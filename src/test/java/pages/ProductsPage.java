package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ProductsPage extends BasePage {
    private static final String ALL_ADD_TO_CART_BTNS_TEMPLATE = "//button[text()='Add to cart']";
    private static final String ADD_TO_CART_BUTTON_TEMPLATE =
            "//div[text()='%s']//ancestor::div[@class='inventory_item']//button";

    By pageTitle = By.xpath("//*[@data-test='title']");
    By shoppingCartBadge = By.xpath("//span[@class = 'shopping_cart_badge']");

    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    @Step("Определение количества товаров в корзине")
    public String checkGoodsQuantity() {
        return driver.findElement(shoppingCartBadge).getText();
    }

    @Step("Ожидание загрузки страницы")
    public boolean isPageLoaded() {
        return driver.findElement(pageTitle).isDisplayed();
    }

    @Step("Добавление товара в корзину по имени товара")
    public void addToCart(final String goodsName) {
        // By addGoodsToCart = By.xpath(String.format(ADD_TO_CART_BUTTON_PATTERN, "Sauce Labs Bike Light"));
        By addGoodsToCart = By.xpath(ADD_TO_CART_BUTTON_TEMPLATE.formatted(goodsName));
        driver.findElement(addGoodsToCart).click();
    }

    @Step("Добавление товара в корзину по порядоковому номеру")
    public void addToCart(final int goodsOrder) {
        driver.findElements(By.xpath(TEXT_LOCATOR_PATTERN.formatted("Add to cart"))).get(goodsOrder).click();
    }

    @Step("Добавление всех товаров со страницы в корзину")
    public boolean addAllGoodsToCart() throws InterruptedException {
        int i = 0;
        List<WebElement> addToCartButtons = driver.findElements(By.xpath(ALL_ADD_TO_CART_BTNS_TEMPLATE));
        for (WebElement btn : addToCartButtons) {
            btn.click();
            i++;
        }

        return (Integer.valueOf(checkGoodsQuantity()) == i);
    }

    @Step("Переход к корзине")
    public void switchToCart() {
        driver.findElement(shoppingCartBadge).click();
    }
}

