package pageobject.mainLayout;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;

public class HomePage extends MainLayoutPage {
    public static String HOME_PAGE_URL = "https://www.amazon.com";

    public HomePage(WebDriver driver) {
        super(driver, HomePage.class.getSimpleName());
    }

    @Step("Open HomePage")
    public HomePage open(){
        logger.info("Opening home page");
        driver.navigate().to(HOME_PAGE_URL);
        return this;
    }
}
