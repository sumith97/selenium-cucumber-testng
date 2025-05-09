Feature: Window and Alert Handling
  As a user
  I want to interact with multiple windows and alerts
  So that I can handle different browser scenarios

  Background:
    Given I am on the internet homepage

  Scenario: Handle multiple windows
    When I navigate to the "Multiple Windows" page
    And I click "Click Here" link
    Then I should be able to switch to the new window
    And I should see "New Window" text in the new window
    When I switch back to the main window
    Then I should see the original content

  Scenario: Handle JavaScript Alerts
    When I navigate to the "JavaScript Alerts" page
    And I click the "Click for JS Alert" button
    Then I should be able to accept the alert
    And I should see "You successfully clicked an alert" message
    
    When I click the "Click for JS Confirm" button
    Then I should be able to dismiss the confirm dialog
    And I should see "You clicked: Cancel" message
    
    When I click the "Click for JS Prompt" button
    Then I should be able to enter "Test Input" in the prompt
    And I should see "You entered: Test Input" message

  Scenario: Handle Frames
    When I navigate to the "Frames" page
    And I click on "iFrame" link
    Then I should be able to switch to the frame
    And I should be able to type "Hello from iframe!" in the editor
    When I switch back to default content
    Then I should see the frame page header 