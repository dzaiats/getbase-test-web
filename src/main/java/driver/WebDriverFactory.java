package driver;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class WebDriverFactory {

    public static final String CHROME = "chrome";
    public static final String FIREFOX = "firefox";
    public static final String SAFARI = "firefox";
    public static final String INTERNET_EXPLORER = "ie";
    public static final String MOBILE = "true";
    public static WebDriver driver;
    public static boolean IS_MOBILE;

    public static WebDriver initDriver(String browser, String mobile, String mobileDeviceName) throws Exception {
        if (CHROME.equals(browser)) {
            setChromeDriver();
            if (MOBILE.equals(mobile)) {
                IS_MOBILE = true;
                Map<String, String> mobileEmulation = new HashMap<>();
                mobileEmulation.put("deviceName", mobileDeviceName);

                Map<String, Object> chromeOptions = new HashMap<>();
                chromeOptions.put("mobileEmulation", mobileEmulation);
                DesiredCapabilities capabilities = DesiredCapabilities.chrome();
                capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
                driver = new ChromeDriver(capabilities);
            } else {
                driver = new ChromeDriver();
            }
        } else if (FIREFOX.equals(browser)) {
            FirefoxProfile ffProfile = new FirefoxProfile();
            driver = new FirefoxDriver(ffProfile);
        } else if (INTERNET_EXPLORER.equals(browser)) {
            driver = new SafariDriver();
        } else {
            driver = new HtmlUnitDriver();
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().deleteAllCookies();

        return driver;
    }

    private static void setChromeDriver() {
        Platform platform = Platform.getCurrent();
        String os = System.getProperty("os.name").toLowerCase().substring(0, 3);
        String chromeBinary = "src/main/resources/drivers/chrome/chromedriver"
                + (Platform.WINDOWS.is(platform) ? ".exe" : "");
        System.setProperty("webdriver.chrome.driver", chromeBinary);
    }
}