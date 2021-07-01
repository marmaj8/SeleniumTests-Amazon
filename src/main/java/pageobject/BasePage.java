package pageobject;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public abstract class BasePage {
    protected WebDriver driver;
    protected Logger logger;

    public BasePage(WebDriver driver, String className){
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
        logger = LogManager.getLogger(className);
    }

    protected void selectFromSelectBoxByText(WebElement element, String text){
        logger.info("Trying to select option with text '" + text + "' from select box '" + element + "'");
        Select select = new Select(element);

        try {
            select.selectByVisibleText(text);
        }
        catch (Exception e){
            logger.error("Option cannot be selected");
            throw e;
        }
    }

    protected void selectFromSelectBoxByValue(WebElement element, String value){
        logger.info("Trying to select option with value '" + value + "' from select box '" + element + "'");
        Select select = new Select(element);

        try {
            select.selectByValue(value);
        }
        catch (Exception e){
            logger.error("Option cannot be selected");
            throw e;
        }
    }
}
