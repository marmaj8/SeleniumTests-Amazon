package pageobject.mainLayout;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ResultsPage extends MainLayoutPage {

    @FindBy(xpath = "//*[@cel_widget_id = 'UPPER-RESULT_INFO_BAR-0']//div/span[last()]")
    public WebElement searchedTerm;

    @FindAll(
            @FindBy(xpath = "" +
                    "//*[contains(@data-component-type, 'search-result')]" +
                    "//*[contains(@data-component-type, 'search-result')]" +
                    "//*[not(@visibility = hidden)]//h2//span"))
    public List<WebElement> productNames;

    @FindAll(
            @FindBy(xpath = "//*[@id='departments']//li[not(@id='n')][position()>1]")
    )
    public List<WebElement> subDepartments;

    @FindBy(xpath = "//*[@id='departments']//li[not(@id='n')][1]")
    public WebElement mainDepartment;

    @FindAll(
            @FindBy(xpath = "//*[contains(@class,'a-price-symbol')]")
    )
    public List<WebElement> currencySymbols;

    public ResultsPage(WebDriver driver) {
        super(driver, ResultsPage.class.getSimpleName());
    }
}
