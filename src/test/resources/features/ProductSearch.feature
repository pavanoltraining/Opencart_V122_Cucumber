Feature: Product Search functionality

  @regression
  Scenario: Verify product search functionality
    Given I navigate to the application URL
    When I enter a product name "MacBook" in the search field
    And I click on the search button
    Then I should see the search results page
    And I should see the product "MacBook" in the search results
