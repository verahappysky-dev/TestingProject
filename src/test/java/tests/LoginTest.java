package tests;

import io.qameta.allure.*;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import user.User;
import user.UserFactory;
import utils.PropertyReader;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class LoginTest extends BaseTest {

    @DataProvider
    public Object[][] loginData() {
        return new Object[][]{
                {UserFactory.withLockedUserPermission(), "Epic sadface: Sorry, this user has been locked out."},
                {UserFactory.withPasswordOnly(PropertyReader.getProperty("saucedemo.password")), "Epic sadface: Username is required"},
                {UserFactory.withUsernameOnly(PropertyReader.getProperty("saucedemo.user")), "Epic sadface: Password is required"},
                {UserFactory.emptyUser(), "Epic sadface: Username is required"},
                {UserFactory.createUser("Standard_user", "secret_sauce"), "Epic sadface: Username and password do not match any user in this service"}
        };
    }

    @TmsLink("TestingProject")
    @Severity(SeverityLevel.BLOCKER)
    @Test(description = "Проверка корректного логина", priority = 2)
    public void checkSuccessLogin() {
        loginPage.open();
        loginPage.login(UserFactory.createUser(login, password));
        assertTrue(productsPage.isPageLoaded());
    }

    @Epic("Создание страницы авторизации")
    @Story("Добавление верстка страницы авторизации")
    @Feature("Добавление кнопки авторизации")
    @TmsLink("TestingProject")
    @Severity(SeverityLevel.BLOCKER)
    @Owner("verahappysky@gmail.com")
    @Issue("TestingProject")
    @Flaky
    @Test(description = "Проверка некорректного логина под реквизитами ${user.login}/*****", dataProvider = "loginData", priority = 1)
    public void checkFailedLogin(User user, String errorMessage) {
        loginPage.open();
        loginPage.login(user);
        assertTrue(loginPage.isErrorMsgAppear(), "Error message is not appear");
        assertEquals(loginPage.errorMessageText(), errorMessage);
    }
}