Feature: Dynamic Controls on The Internet Website
  As a user
  I want to interact with dynamic controls
  So that I can test different web elements

  Scenario: Toggle checkbox and input field
    Given I am on the dynamic controls page
    When I click the checkbox remove button
    Then the checkbox should disappear
    When I click the enable button
    Then the input field should be enabled
    And I should be able to type "Hello World" in the input field 