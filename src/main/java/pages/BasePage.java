package pages;


import driver.WebDriverFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BasePage extends WebDriverFactory {

    @FindBy(id = "nav-leads")
    protected WebElement leadNavBarItem;

    public BasePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void clickOnLeadNavBarItem() {
        leadNavBarItem.click();
    }
}
