package tests.leadscenarios;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import pages.*;
import tests.BaseTestRunner;

import static org.assertj.core.api.Assertions.assertThat;

public class CreateNewLeadAndUpdateStatusTest extends BaseTestRunner {

    private String lastCreatedLeadUrl;

    protected String setStoryPath() {
        return "create_new_lead_and_update_status.story";
    }

    @Given("user logged in to the system")
    public void giverUserLoggedInToTheSystem() throws InterruptedException {
        driver.get(baseLoginUrl + loginPartUrl);
        new LoginPage(driver).login(userEmail, userPassword);
    }

    @When("user added a new lead")
    public void whenUserAddedANewLead() {
        new SalesPage(driver).clickOnLeadNavBarItem();

        new LeadsPage(driver).clickAddNewLeadButton();

        new NewEditLeadPage(driver)
                .fillFormForNewLeadAndClickSave();
    }

    @Then("new lead is created with status $status")
    public void thenNewLeadIsCreatedWithStatus(String status) {
        String actualStatus = new LeadPage(driver).getLeadStatus();
        assertThat(actualStatus).isEqualTo(status);
    }

    @When("user changed status from $oldStatus to $newStatus")
    public void whenUserChangedStatusToNewStatus(String oldStatus, String newStatus) {
        lastCreatedLeadUrl = driver.getCurrentUrl();

        driver.get(baseUrl + settingsUrl);

        new ProfileSettingsPage(driver)
                .clickOnLeftSideNavigationPanelLink("Leads")
                .clickOnLeadStatusesTab()
                .clickEditButtonByStatus(oldStatus)
                .changeStatusToNewAndSave(newStatus);
    }

    @Then("status is updated on lead page to $newStatus")
    public void thenStatusIsUpdatedOnLeadPageToNewStatus(String newStatus) {
        driver.get(lastCreatedLeadUrl);
        String actualStatus = new LeadPage(driver).getLeadStatus();
        assertThat(actualStatus).isEqualTo(newStatus);
    }

    @Then("user needs to be deleted")
    public void thenUserNeedsToBeDeleted() throws InterruptedException {
        new LeadPage(driver).clickEditLeadButton();

        new NewEditLeadPage(driver).clickDeleteThisLead();
    }

    @Then("status name should be reverted from $oldStatus to $newStatus")
    public void thenStatusNameShouldBeRevertedToPreviousOne(String oldStatus, String newStatus) {
        driver.get(baseUrl + settingsUrl);
        new ProfileSettingsPage(driver)
                .clickOnLeftSideNavigationPanelLink("Leads")
                .clickOnLeadStatusesTab()
                .clickEditButtonByStatus(oldStatus)
                .changeStatusToNewAndSave(newStatus);
    }
}
