package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.testng.AllureTestNg;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.ITestContext;
import org.testng.annotations.*;
import pages.CartPage;
import pages.LoginPage;
import pages.ProductsPage;
import utils.PropertyReader;
import utils.TestListener;

@Listeners({AllureTestNg.class, TestListener.class})
public class BaseTest {
    private final static String CHROME_BROWSER = "chrome";
    WebDriver driver;
    LoginPage loginPage;
    ProductsPage productsPage;
    CartPage cartPage;
    String login;
    String password;

    @BeforeMethod
    @Parameters({"browser"})
    public void setUp(@Optional("chrome") String browser, ITestContext testContext) {
        login = PropertyReader.getProperty("saucedemo.user");
        password = PropertyReader.getProperty("saucedemo.password");
        if (CHROME_BROWSER.equals(browser)) {
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("start-maximized");
            options.addArguments("--guest");
            options.addArguments("--headless");
            driver = new ChromeDriver(options);
        } else {
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
        }
        //driver.manage().timeouts().implicitlyWait(Duration.of(5, TimeUnit.SECONDS.toChronoUnit()));
        loginPage = new LoginPage(driver);
        productsPage = new ProductsPage(driver);
        cartPage = new CartPage(driver);
        testContext.setAttribute("driver", driver);
    }

    @AfterMethod
    public void close() {
        driver.quit();
    }
}
