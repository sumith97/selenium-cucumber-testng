Feature: Google Search


#   Background: 
#     Given I am on the internet homepage
@smoke
  Scenario: Search for "Selenium"
    Given I am on the Google search page
    When I search for "Selenium"
    Then I should see "Selenium" in the search results
