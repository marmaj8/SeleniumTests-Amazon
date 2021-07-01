import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class CreateAccountTest extends BaseTest {
    static String MISSING_NAME_ALERT_TEXT = "Enter your name";
    static String MISSING_EMAIL_ALERT_TEXT = "Enter your email";
    static String MISSING_PASSWORD_ALERT_TEXT = "Enter your password";
    static String NOT_VALID_EMAIL_ALERT_TEXT = "Enter a valid email address";

    static String NOT_VALID_EMAIL_SINGLE_CHAR = "a";
    static String NOT_VALID_EMAIL_MULTIPLE_CHARS = "aa";
    static String NOT_VALID_EMAIL_SINGLE_CHAR_FOR_LOCAL_ONE_CHAR_FOR_TOP_DOMAIN = "a@a.a";
    static String NOT_VALID_EMAIL_MULTIPLE_CHARS_FOR_LOCAL_ONE_CHAR_FOR_TOP_DOMAIN = "aa@a.a";
    static String NOT_VALID_EMAIL_MULTIPLE_LEVEL_DOMAIN_ONE_CHAR_FOR_MAIN_DOMAIN = "a@a.a.a";
    static String NOT_VALID_EMAIL_SECOND_LEVELS_DOMAINS_MULTIPLE_CHARS_ONE_CHAR_FOR_MAIN_DOMAIN = "a@aa.aa.a";
    static String NOT_VALID_EMAIL_EMPTY_LOCAL_CORRECT_DOMAIN = "@a.aa";

    @DataProvider(name = "invalid-emails-set")
    public Object[][] invalidEmails() {
        return new Object[][]
                {
                        {NOT_VALID_EMAIL_SINGLE_CHAR},
                        {NOT_VALID_EMAIL_MULTIPLE_CHARS},
                        {NOT_VALID_EMAIL_SINGLE_CHAR_FOR_LOCAL_ONE_CHAR_FOR_TOP_DOMAIN},
                        {NOT_VALID_EMAIL_MULTIPLE_CHARS_FOR_LOCAL_ONE_CHAR_FOR_TOP_DOMAIN},
                        {NOT_VALID_EMAIL_MULTIPLE_LEVEL_DOMAIN_ONE_CHAR_FOR_MAIN_DOMAIN},
                        {NOT_VALID_EMAIL_SECOND_LEVELS_DOMAINS_MULTIPLE_CHARS_ONE_CHAR_FOR_MAIN_DOMAIN},
                        {NOT_VALID_EMAIL_EMPTY_LOCAL_CORRECT_DOMAIN}
                };
    }

    @BeforeMethod
    public void open() {
        homePage.open()
                .dismissLanguageChangePopUp()
                .enterLoginPage();
        loginPage.enterCreateAccount();
    }

    @Test
    public void registerWithEmptyInputsShowCorrespondingAlerts() {
        logger.info("Checking lack of visibility of missing name alert");
        Assert.assertFalse(createAccountPage.missingNameAlert.isDisplayed());
        logger.info("Checking lack of visibility of missing email alert");
        Assert.assertFalse(createAccountPage.missingEmailAlert.isDisplayed());
        logger.info("Checking lack of visibility of missing password alert");
        Assert.assertFalse(createAccountPage.missingPasswordAlert.isDisplayed());

        createAccountPage.createAccount();

        logger.info("Checking missing name alert");
        Assert.assertEquals(createAccountPage.missingNameAlert.getText().trim(), MISSING_NAME_ALERT_TEXT);
        logger.info("Checking missing email alert");
        Assert.assertEquals(createAccountPage.missingEmailAlert.getText().trim(), MISSING_EMAIL_ALERT_TEXT);
        logger.info("Checking missing password alert");
        Assert.assertEquals(createAccountPage.missingPasswordAlert.getText().trim(), MISSING_PASSWORD_ALERT_TEXT);
    }

    @Test(dataProvider = "invalid-emails-set")
    public void registerWithNotValidEmailShowCorrespondingAlert(String email) {
        logger.info("Checking lack of visibility of not valid email alert");
        Assert.assertFalse(createAccountPage.notValidEmailAlert.isDisplayed());

        createAccountPage.typeEmail(email)
                .createAccount();

        logger.info("Checking not valid email alert");
        Assert.assertEquals(createAccountPage.notValidEmailAlert.getText().trim(), NOT_VALID_EMAIL_ALERT_TEXT);

    }
}
