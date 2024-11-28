Feature: User Registration and Login

  Scenario: Register and login with a new account
    When the user registers an account
    Then the user logs in with the registered credentials
