Meta: Create new Lead and update the name of status

Narrative:
As a user
    I want be able to create a new user
    and update status to new one

Scenario: scenario of user creation
Given user logged in to the system
When user added a new lead
Then new lead is created with status New
When user changed status to Created
Then status is updated on lead page to Created
And user needs to be deleted
And status name should be reverted to previous one