Feature: Add product to the shopping cart

	@regression
  Scenario: Verify adding a product to the cart
		Given I navigate to the application URL
    When I enter a product name "MacBook" in the search field
    And I click on the search button
    Then I should see the product "MacBook" in the search results
    When I select the product "MacBook" from the search results
    And I set the quantity to "2"
    And I add the product to the cart
    Then I should see a success message confirming the product was added to the cart
