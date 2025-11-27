package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ProductsPage {
    private static final String ALL_ADD_TO_CART_BTNS_TEMPLATE = "//button[text()='Add to cart']";

    WebDriver driver;

    By pageTitle = By.xpath("//*[@data-test='title']");
    By shoppingCartBadge = By.xpath("//span[@class = 'shopping_cart_badge']");

    public ProductsPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isPageLoaded() {
        return driver.findElement(pageTitle).isDisplayed();
    }

    public boolean addAllGoodsToCart() throws InterruptedException {
        int i = 0;
        List<WebElement> addToCartButtons = driver.findElements(By.xpath(ALL_ADD_TO_CART_BTNS_TEMPLATE));
        for (WebElement btn : addToCartButtons) {
            btn.click();
            i++;
        }

        return (Integer.valueOf(driver.findElement(shoppingCartBadge).getText()) == i) ;
    }
}