package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    WebDriver driver;

    By loginField = By.cssSelector("[placeholder='Username']");
    By passwordField = By.cssSelector("[placeholder='Password']");
    By loginBtn = By.id("login-button");
    By error = By.xpath("//*[@data-test='error']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void open() {
        driver.get("https://www.saucedemo.com/");
    }

    public void login(String login, String password) {
        fillInLoginField(login);
        fillInPasswordField(password);
        driver.findElement(loginBtn).click();
    }

    public boolean isErrorMsgAppear() {
        return driver.findElement(error).isDisplayed();
    }

    public String errorMessageText() {
        return driver.findElement(error).getText();
    }

    public void fillInLoginField(String login) {
        driver.findElement(loginField).sendKeys(login);
    }

    public void fillInPasswordField(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }
}
