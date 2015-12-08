package pages;

import driver.DriverHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.PropertyLoader;

public class NewEditLeadPage extends BasePage {

    @FindBy(id = "lead-first-name")
    private WebElement firstNameField;

    @FindBy(id = "lead-last-name")
    private WebElement lastNameField;

    @FindBy(id = "lead-company-name")
    private WebElement companyNameField;

    @FindBy(css = "button.save")
    private WebElement saveButton;

    @FindBy(css = ".remove")
    public WebElement removeLink;

    @FindBy(css = ".confirm")
    private WebElement confirmDeleteLead;

    public NewEditLeadPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public void fillFormForNewLeadAndClickSave() {
        DriverHelper.sendKeys(firstNameField, PropertyLoader.loadProperty("first.name"));
        DriverHelper.sendKeys(lastNameField, PropertyLoader.loadProperty("last.name"));
        DriverHelper.sendKeys(companyNameField, PropertyLoader.loadProperty("company.name"));

        saveButton.click();
    }

    public void clickDeleteThisLead() throws InterruptedException {
        removeLink.click();
        DriverHelper.wait(2); // Need to have it here to avoid strange failure on Chrome
        confirmDeleteLead.click();
    }
}
