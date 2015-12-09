package driver;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;

import java.io.File;

import static java.lang.Thread.sleep;

public class DriverHelper {

    public static void sendKeys(WebElement element, String text) {
        element.click();
        element.clear();
        element.sendKeys(text);
    }

    public static void scrollDown(WebDriver driver) {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("window.scroll(0,1000)", "");
    }

    public static void scrollUp(WebDriver driver) {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("window.scroll(0,-1000)", "");
    }

    public static void wait(int seconds) throws InterruptedException {
        sleep(1000 * seconds);
    }

    public static void takeScreenshot(WebDriver driver) throws Exception {
        String fullFileName = System.getProperty("user.dir")
                + "/target/reports/screenshots/screenshot_"
                + System.currentTimeMillis() + ".png";

        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screenshot, new File(fullFileName));
    }
}
