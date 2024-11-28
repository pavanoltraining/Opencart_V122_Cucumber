Feature: Login 
  As a user, I want to login using valid credentials so that I can access my account.

	@sanity
  # Scenario 1: Login with valid credentials
  Scenario: Login with valid credentials
    Given I navigate to the application URL
    When I navigate to the Login page from the Home page
    And I enter valid login credentials
    Then I should be redirected to the My Account page

   #Scenario 2: Login with different valid credentials (Data-driven)
  @datadriven
  Scenario Outline: Login with different valid credentials
    Given I navigate to the application URL
    When I navigate to the Login page from the Home page
    And I enter login credentials with email "<email>" and password "<password>"
    Then I should be redirected to the My Account page

    Examples: 
      | email                     | password |
      | pavanoltraining@gmail.com | test@123 |
      | pavanol@gmail.com         | test123  |
      | pavanol@xyz.com           | test123 |
      | pavanol@abc.com           | test@123 |

  
  # Scenario 3: Login Data Driven from Excel
  @datadriven
  Scenario Outline: Login Data Driven from Excel
    Given I navigate to the application URL
    When I navigate to the Login page from the Home page
    And I enter different login credentials with "<row_index>"
    Then I should be redirected to the My Account page if data is valid in "<row_index>"

    Examples: 
      | row_index |
      |         1 |
      |         2 |
      |         3 |
      |         4 |
      |         5 |
