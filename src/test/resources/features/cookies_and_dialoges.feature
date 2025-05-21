@cook
Feature: Cookies and Dialoges

Scenario: Testing the cookies functionality
    Given I am in the test page
    When I click on Cookies page
    Then I should add a cookie with the name "test" and value "test"
    Then I should read the cookie with the name "test"
    Then I should click on cookies display button and check cookie with name "test" is displayed
    Then I should edit the cookie with the name "test" and value "test2"
    And I should delete the cookie with the name "test"


Scenario: Testing the dialoges functionality
    Given I am in the test page
    When I click on Dialoges page
    When I click on the Launch Alert button
    Then I should see the alert and verify it has the text "Hello world!"
    And I should close the alert
    When I click on the Launch Prompt button
    Then I should see the prompt and verify it has the text "Please enter your name" and send the text "PatherBaba"
    And I should accept the alert
    When I click on the Launch Modal button
    Then I should see the modal and verify it has the text "This is the modal body"
    And I should close the modal








