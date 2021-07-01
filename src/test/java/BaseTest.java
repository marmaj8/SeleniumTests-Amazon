import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;
import pageobject.authentication.CreateAccountPage;
import pageobject.authentication.LoginPage;
import pageobject.mainLayout.HomePage;
import pageobject.mainLayout.LanguageCurrencySettingsPage;
import pageobject.mainLayout.ResultsPage;

public abstract class BaseTest {
    protected static final String CHROME_DRIVER_PATH = "drivers/windows/chromedriver.exe";

    protected WebDriver driver;
    protected Logger logger = LogManager.getLogger("");
    protected SoftAssert softAssert;

    protected HomePage homePage;
    protected ResultsPage resultsPage;
    protected LanguageCurrencySettingsPage languageCurrencySettingsPage;
    protected LoginPage loginPage;
    protected CreateAccountPage createAccountPage;


    @Parameters({"browserName"})
    @BeforeClass
    public void setup(@Optional("Chrome") String browserName) {
        System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_PATH);
        driver = DriverManager.getDriver(browserName);
        driver.manage().window().maximize();
        softAssert = new SoftAssert();
        logger = LogManager.getLogger(this.getClass().getSimpleName());

        homePage = new HomePage(driver);
        resultsPage = new ResultsPage(driver);
        languageCurrencySettingsPage = new LanguageCurrencySettingsPage(driver);
        loginPage = new LoginPage(driver);
        createAccountPage = new CreateAccountPage(driver);
    }

    @AfterClass
    public void cleanUp() {
        logger.info("Closing " + driver.getClass().getSimpleName());
        driver.manage().deleteAllCookies();
        driver.quit();
    }

}