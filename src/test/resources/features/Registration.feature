Feature: User Registration

	@sanity
  Scenario: Successful user registration
    Given I navigate to the application URL
    When I navigate to My Account and click Register
    And I fill in the registration details
    And I agree to the Privacy Policy and submit the registration form
    Then I should see the confirmation message "Your Account Has Been Created!"
