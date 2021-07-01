package pageobject.authentication;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pageobject.BasePage;

public class LoginPage extends BasePage {
    @FindBy(xpath = "//*[@id='createAccountSubmit']")
    public WebElement createAccountButton;

    public LoginPage(WebDriver driver) {
        super(driver, LoginPage.class.getSimpleName());
    }

    @Step("Enter Create account page")
    public LoginPage enterCreateAccount(){
        logger.info("Entering create account page");
        createAccountButton.click();
        return this;
    }
}
