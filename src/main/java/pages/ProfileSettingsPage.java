package pages;

import driver.DriverHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProfileSettingsPage extends BasePage {

    @FindBy(linkText = "Lead Statuses")
    private WebElement leadStatusesTab;

    @FindBy(xpath = "//*[@id='name' and @data-current-value]")
    private WebElement statusNameField;

    @FindBy(xpath = "//*[@id='lead-status' and contains(@class,'active')]//fieldset[//h4[text()='Edit Lead Status']]//button")
    private WebElement saveButton;

    @FindBy(css = ".alert-success")
    private WebElement alertStatusChanged;

    public ProfileSettingsPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    private WebElement getLinkFromLeftSideNavigationPanel(String linkName) {
        return driver.findElement(By.xpath("//*[@id='sidebar']//a[text()='" + linkName + "']"));
    }

    public ProfileSettingsPage clickOnLeftSideNavigationPanelLink(String linkName) {
        getLinkFromLeftSideNavigationPanel(linkName).click();
        return this;
    }

    public ProfileSettingsPage clickOnLeadStatusesTab() {
        leadStatusesTab.click();
        return this;
    }

    public ProfileSettingsPage clickEditButtonByStatus(String status) {
        driver.findElement(By.xpath("//div[./label/h4[text()='" + status + "']]//button[text()='Edit']")).click();
        return this;
    }

    public ProfileSettingsPage changeStatusToNewAndSave(String newStatus) {
        DriverHelper.sendKeys(statusNameField, newStatus);
        saveButton.click();
        alertStatusChanged.isDisplayed();
        return this;
    }
}
