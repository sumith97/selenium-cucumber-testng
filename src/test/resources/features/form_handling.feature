Feature: Form Handling and File Upload
  As a user
  I want to interact with web forms
  So that I can submit data and files

  Background: 
    Given I am on the internet homepage

  Scenario: Submit a basic form with validation
    When I navigate to the "Form Authentication" page
    And I enter username "tomsmith" and password "SuperSecretPassword!"
    And I click the login button
    Then I should see a success message
    And I should be logged in

  Scenario: Upload a file
    When I navigate to the "File Upload" page
    And I select a file to upload
    And I click the upload button
    Then I should see the file uploaded successfully

  Scenario Outline: Login with different credentials
    When I navigate to the "Form Authentication" page
    And I enter username "<username>" and password "<password>"
    And I click the login button
    Then I should see "<message>"

    Examples:
      | username | password             | message                        |
      | tomsmith | SuperSecretPassword! | You logged into a secure area!|
      | invalid  | invalid123           | Your username is invalid!     |
      | tomsmith | wrongpassword       | Your password is invalid!     | 