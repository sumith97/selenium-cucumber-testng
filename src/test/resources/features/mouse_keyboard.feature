@kid
Feature: Mouse and Keyboard


  Scenario: Testing the mouse functionality
    Given I am in the test page
    When I click on the Web Form
    Then I should click on the checkbox and check
    And I should click on the radio button and check


  Scenario: Testing the keyboard functionality
    Given I am in the test page
    When I click on the Web Form
    Then I should type "Funny Fellow" in the text field and check the text
    And I should upload a file
    When I submit the form
    Then I should see the text "Form submitted"


    Scenario: Testing advanced mouse functionality
    Given I am in the test page
    When I click on the canvas page
    Then I should see the text "canvas" and must draw a circle
    Then Navigate back to previous page
    When I click on the Dropdown Menu page
    Then I perform right click and check
    Then I perform double click and check
    Then Navigate back to previous page

