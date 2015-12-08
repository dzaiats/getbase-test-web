package driver;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

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
}
