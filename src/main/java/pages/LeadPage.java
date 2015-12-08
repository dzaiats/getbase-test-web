package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LeadPage extends BasePage {

    @FindBy(css = ".lead-status")
    private WebElement leadStatusLabel;

    @FindBy(css = ".detail-edit")
    private WebElement editLeadButton;

    public LeadPage(WebDriver driver) {
        super(driver);
    }

    public String getLeadStatus(){
        return leadStatusLabel.getText();
    }

    public void clickEditLeadButton(){
        editLeadButton.click();
    }
}
