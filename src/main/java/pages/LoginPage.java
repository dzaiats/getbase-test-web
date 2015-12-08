package pages;

import driver.DriverHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

    @FindBy(id = "user_email")
    private WebElement emailField;

    @FindBy(id = "user_password")
    private WebElement passwordField;

    @FindBy(xpath = "//*[@id='user_new']//button")
    private WebElement loginButton;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void login(String userEmail, String userPassword) throws InterruptedException {
        DriverHelper.sendKeys(emailField, userEmail);
        DriverHelper.sendKeys(passwordField, userPassword);

        loginButton.click();
    }
}
