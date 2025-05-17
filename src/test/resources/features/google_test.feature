Feature: Google Search


#   Background: 
#     Given I am on the internet homepage
@smoke
  Scenario: Search for "Selenium"
    Given I am on the Google search page
    When I search for "Selenium"
    Then I should see "gsdgfghfdasghf" in the search results
