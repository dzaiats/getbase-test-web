import driver.WebDriverFactory;
import org.junit.AfterClass;
import org.junit.BeforeClass;

public class BaseTest extends WebDriverFactory {

    private String browserName = System.getenv("BROWSER_NAME");
    private String mobile = System.getenv("MOBILE");
    private String mobileDeviceName = System.getenv("MOBILE_DEVICE_NAME");


    @BeforeClass
    public void setUp() throws Exception {
        initDriver(browserName, mobile, mobileDeviceName);
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
