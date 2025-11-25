import org.openqa.selenium.Alert;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class LoginTest extends BaseTest{

    @Test
    public void checkFailedLogin() {
        loginPage.open();
        loginPage.login("locked_out_user", "secret_sauce");

        assertTrue(loginPage.isErrorMsgAppear(), "Error message is not appear");
        assertEquals(loginPage.errorMessageText(), "Epic sadface: Sorry, this user has been locked out.", "Wrong error message text");
    }


    @Test
    public void checkSuccessLogin() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");

        assertTrue(productsPage.isPageLoaded());
    }
}
