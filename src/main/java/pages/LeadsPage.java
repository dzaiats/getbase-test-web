package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LeadsPage extends BasePage {

    @FindBy(id = "leads-new")
    private WebElement addNewLeadButton;

    public LeadsPage(WebDriver driver) {
        super(driver);
    }

    public void clickAddNewLeadButton() {
        addNewLeadButton.click();
    }
}
