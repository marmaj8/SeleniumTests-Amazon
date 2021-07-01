package pageobject.mainLayout;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LanguageCurrencySettingsPage extends MainLayoutPage {

    @FindBy(xpath = "//*[@id='icp-sc-dropdown']")
    public WebElement currencySelectBox;

    @FindBy(xpath = "//*[@id='icp-btn-save']//input")
    public WebElement saveChangesButton;

    public LanguageCurrencySettingsPage(WebDriver driver) {
        super(driver, LanguageCurrencySettingsPage.class.getSimpleName());
    }

    @Step("Select currency '{0}'")
    public LanguageCurrencySettingsPage selectCurrencyByName(String currency){
        logger.info("Selecting currency '" + currency + "'");
        selectFromSelectBoxByValue(currencySelectBox, currency);
        return this;
    }

    @Step("Save language and currency settings")
    public LanguageCurrencySettingsPage saveLanguageCurrencySettings(){
        logger.info("Saving language and currency settings");
        saveChangesButton.click();
        return this;
    }
}
