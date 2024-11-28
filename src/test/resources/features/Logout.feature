Feature: User Logout

  @regression
  Scenario: Verify user can successfully log out and navigate back to the Home Page
    Given I navigate to the application URL
    When I navigate to the Login page from the Home page
    And I enter valid login credentials
    Then I should be redirected to the My Account page
    When I click on the Logout link
    And I click on the Continue button
    Then I should be navigated back to the Home Page
