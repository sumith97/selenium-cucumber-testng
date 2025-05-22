@down
Feature: Down and History

Scenario: Test data list, select and history functionality
    Given I am in the test page
    When I click on the Web Form
    Then I click on data list and check "New York" is present
    Then I select "New York" from the data list
    Then I select "Two" from a selection dropdown
    Then Navigate back to previous page
    Then Navigate back to forward page
    Then Navigate back to previous page
    Then check if I am in Home page
