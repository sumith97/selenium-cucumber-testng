@down
Feature: Down and History

Scenario: Test data list and historyfunctionality
    Given I am in the test page
    When I click on the Web Form
    Then I click on data list and check "New York" is present
    Then Navigate back to previous page
    Then Navigate back to forward page
    Then Navigate back to previous page
    Then check if I am in Home page
