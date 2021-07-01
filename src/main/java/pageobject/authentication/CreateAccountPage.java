package pageobject.authentication;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import pageobject.BasePage;

import java.util.List;

public class CreateAccountPage extends BasePage {

    @FindBy(xpath = "//*[@id='ap_email']")
    public WebElement emailTextBox;

    @FindBy(xpath = "//*[@id='continue']")
    public WebElement createAccountButton;

    @FindBy(xpath = "//*[@id='auth-customerName-missing-alert']")
    public WebElement missingNameAlert;

    @FindBy(xpath = "//*[@id='auth-email-missing-alert']")
    public WebElement missingEmailAlert;

    @FindBy(xpath = "//*[@id='auth-password-missing-alert']")
    public WebElement missingPasswordAlert;

    @FindBy(xpath = "//*[@id='auth-email-invalid-email-alert']")
    public WebElement notValidEmailAlert;

    @FindAll(@FindBy(xpath = "//*[contains(@id,'alert')]"))
    public List<WebElement> alerts;

    public CreateAccountPage(WebDriver driver) {
        super(driver, CreateAccountPage.class.getSimpleName());
    }

    @Step("Type email '{0}'")
    public CreateAccountPage typeEmail(String email) {
        logger.info("Typing email " + email);
        emailTextBox.sendKeys(email);
        return this;
    }

    @Step("Create account")
    public CreateAccountPage createAccount() {
        logger.info("Creating account");
        createAccountButton.click();
        return this;
    }
}
