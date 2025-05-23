@java
Feature: JavaScript

Scenario: Basic JavaScript Functionality
Given I am in the test page
When I click on the long page
Then I should see the text "This is a long page"
Then I scroll down the page by "1000" pixels
Then I scroll up the page by "1000" pixels
Then I refresh the page
Then I scroll to last paragraph


Scenario: Test Infinate Scroll Functionality
Given I am in the test page
When I click on the infinite scroll page
Then I should see the text "Infinite scroll"
And check if scroll is infinite
# Then I scroll to last paragraph


Scenario: Test Asynchronous JavaScript
Given I am in the test page
Then I test the Asynchronous JavaScript


Scenario: Test Color Picker Functionality
Given I am in the test page
When I click on the Web Form
Then I check the inital color is "#563d7c"
Then I should set a new color to red and check


