package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import user.User;
import utils.PropertyReader;

public class LoginPage {
    WebDriver driver;

    By loginField = By.cssSelector("[placeholder='Username']");
    By passwordField = By.cssSelector("[placeholder='Password']");
    By loginBtn = By.id("login-button");
    By error = By.xpath("//*[@data-test='error']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Открытие основной страницы")
    public void open() {
        driver.get(PropertyReader.getProperty("saucedemo.url"));
    }

    @Step("Авторизация пользователя")
    public void login(User user) {
        fillInLoginField(user.email());
        fillInPasswordField(user.password());
        driver.findElement(loginBtn).click();
    }

    @Step("Отображение сообщения об ошибке")
    public boolean isErrorMsgAppear() {
        return driver.findElement(error).isDisplayed();
    }

    @Step("Получение сообщения о ошибке")
    public String errorMessageText() {
        return driver.findElement(error).getText();
    }

    @Step("Заполнение поля логина")
    public void fillInLoginField(String login) {
        driver.findElement(loginField).sendKeys(login);
    }

    @Step("Заполение поля пароля")
    public void fillInPasswordField(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }
}