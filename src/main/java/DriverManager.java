import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;

import java.io.File;

public class DriverManager {
    static final String FIREFOX_PROFILE_PATH = "drivers/windows/firefoxProfile";

    private static final Logger logger = LogManager.getLogger(DriverManager.class.getSimpleName());

    private DriverManager(){}

    public static WebDriver getDriver(String driverName){
        switch (driverName.toUpperCase()){
            case "FIREFOX":
                return runFirefox();
            case "CHROME":
            default:
                return runChrome();
        }
    }

    private static WebDriver runChrome(){
        logger.info("Starting ChromeDriver");
        return new ChromeDriver();
    }

    private static WebDriver runFirefox() {
        logger.info("Preparing Firefox profile");
        System.setProperty("webdriver.gecko.driver", System.getProperty("driver.path") + "/geckodriver.exe");
        File file = new File(FIREFOX_PROFILE_PATH);
        file.mkdirs();
        FirefoxProfile profile = new FirefoxProfile(file);
        FirefoxOptions options = new FirefoxOptions();
        options.setProfile(profile);
        logger.info("Starting FirefoxDriver");
        return new FirefoxDriver(options);
    }
}