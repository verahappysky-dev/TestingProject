package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {

    public final static String TEXT_LOCATOR_PATTERN = "//*[text()='%s']";


    public WebDriver driver;
    public WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    @Step("Ждем загрузки страницы")
    public boolean isPageLoaded(final String pageTitle) {
        // wait.until(ExpectedConditions.visibilityOfElementLocated(pageTitle));
        return driver.findElement(By.xpath(TEXT_LOCATOR_PATTERN.formatted(pageTitle))).isDisplayed();
    }

}
