package driver;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.util.concurrent.TimeUnit;

public class WebDriverFactory {

    protected static final String CHROME = "chrome";
    protected static final String FIREFOX = "firefox";
    protected static final String SAFARI = "safari";
    protected static WebDriver driver;
    protected static String browserName;

    public static WebDriver initDriver(String browser) throws Exception {
        browserName = browser;
        if (CHROME.equals(browser)) {
            setChromeDriver();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--start-maximized");
            driver = new ChromeDriver(options);
        } else if (FIREFOX.equals(browser)) {
            driver = new FirefoxDriver();
        } else if (SAFARI.equals(browser)) {
            driver = new SafariDriver();
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().deleteAllCookies();

        return driver;
    }

    private static void setChromeDriver() {
        Platform platform = Platform.getCurrent();
        String chromeBinary = "src/main/resources/drivers/chrome/chromedriver"
                + (Platform.WINDOWS.is(platform) ? ".exe" : "");
        System.setProperty("webdriver.chrome.driver", chromeBinary);
    }

}
