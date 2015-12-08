import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import pages.*;

import static org.assertj.core.api.Assertions.assertThat;

public class LoginPageTest extends BaseTestRunner {

    private NewEditLeadPage newEditLeadPage;
    private LeadPage leadPage;
    private ProfileSettingsPage profileSettingsPage;
    private String lastCreatedLeadUrl;

    protected String setStoryPath() {
        return "new_lead_could_be_created_and_status_changed.story";
    }

    @Given("user logged in to the system")
    public void giverUserLoggedInToTheSystem() throws InterruptedException {
        driver.get(baseLoginUrl + loginPartUrl);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(userEmail, userPassword);
    }

    @When("user added a new lead")
    public void whenUserAddedANewLead() {
        SalesPage salesPage = new SalesPage(driver);
        salesPage.clickOnLeadNavBarItem();

        LeadsPage leadsPage = new LeadsPage(driver);
        leadsPage.clickAddNewLeadButton();

        newEditLeadPage = new NewEditLeadPage(driver);
        newEditLeadPage.fillFormForNewLeadAndClickSave();
    }

    @Then("new lead is created with status $status")
    public void thenNewLeadIsCreatedWithStatus(String status) {
        leadPage = new LeadPage(driver);

        assertThat(leadPage.getLeadStatus()).isEqualTo(status);
    }

    @When("user changed status to $newStatus")
    public void whenUserChangedStatusToNewStatus(String newStatus) {
        lastCreatedLeadUrl = driver.getCurrentUrl();

        driver.get(baseUrl + settingsUrl);

        profileSettingsPage = new ProfileSettingsPage(driver);
        profileSettingsPage.clickOnLeftSideNavigationPanelLink("Leads");
        profileSettingsPage.clickOnLeadStatusesTab();
        profileSettingsPage.clickEditButtonByStatus("New");
        profileSettingsPage.changeStatusToNewAndSave("Created");
    }

    @Then("status is updated on lead page to $newStatus")
    public void thenStatusIsUpdatedOnLeadPageToNewStatus(String newStatus) {
        driver.get(lastCreatedLeadUrl);
        assertThat(leadPage.getLeadStatus()).isEqualTo(newStatus);
    }

    @Then("user needs to be deleted")
    public void thenUserNeedsToBeDeleted() throws InterruptedException {
        leadPage.clickEditLeadButton();
        newEditLeadPage.clickDeleteThisLead();
    }

    @Then("status name should be reverted to previous one")
    public void thenStatusNameShouldBeReveretedToPreviosOne() {
        driver.get(baseUrl + settingsUrl);
        profileSettingsPage.clickOnLeftSideNavigationPanelLink("Leads");
        profileSettingsPage.clickOnLeadStatusesTab();
        profileSettingsPage.clickEditButtonByStatus("Created");
        profileSettingsPage.changeStatusToNewAndSave("New");
    }
}
