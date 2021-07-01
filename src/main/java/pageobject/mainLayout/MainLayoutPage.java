package pageobject.mainLayout;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pageobject.BasePage;

public abstract class MainLayoutPage extends BasePage {

    @FindBy(xpath = "//*[@data-action-type='DISMISS']")
    public WebElement dismissChangeLanguagePopUpButton;

    @FindBy(xpath = "//*[@id='twotabsearchtextbox']")
    public WebElement searchTextBox;

    @FindBy(xpath = "//*[@id='searchDropdownBox']")
    public WebElement departmentSelectBox;

    @FindBy(xpath = "//*[@id='nav-search-submit-button']")
    public WebElement searchButton;

    @FindBy(xpath = "//*[@id='icp-nav-flyout']")
    public WebElement changeLanguageCurrencyButton;

    @FindBy(xpath = "//*[@id='nav-link-accountList']")
    public WebElement loginButton;

    public MainLayoutPage(WebDriver driver, String className) {
        super(driver, className);
    }

    @Step("Enter into search text box: '{0}'.")
    public MainLayoutPage typeSearchTerm(String searchTerm) {
        logger.info("Entering into search box: '" + searchTerm + "'");
        searchTextBox.sendKeys(searchTerm);
        return this;
    }

    @Step("Select department '{0}' to look up")
    public MainLayoutPage selectDepartmentByText(String department) {
        logger.info("Selecting department '" + department + "' to look up");
        selectFromSelectBoxByText(departmentSelectBox, department);
        return this;
    }

    @Step("Start searching")
    public MainLayoutPage startSearching(){
        logger.info("Starting searching");
        searchButton.click();
        return this;
    }

    @Step("Enter Change language and currency settings")
    public MainLayoutPage enterChangeLanguageCurrencyPage(){
        logger.info("Entering Change language and currency settings");
        changeLanguageCurrencyButton.click();
        return this;
    }

    @Step("Enter Login page")
    public MainLayoutPage enterLoginPage(){
        logger.info("Entering Login page");
        loginButton.click();
        return this;
    }

    @Step("Dismiss language change pop up")
    public MainLayoutPage dismissLanguageChangePopUp(){
        WebElement element;
        try {
            logger.info("Dismissing change language pop up");
            dismissChangeLanguagePopUpButton.click();
        }
        catch (Exception ex){
            logger.info("Change language pop up is not visible");
        }
        return this;
    }
}
