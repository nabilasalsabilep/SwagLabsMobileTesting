@Login
Feature: Login feature

  @SuccessfullyLogin
  Scenario: User wants to successfully login with valid credential
    Given User opens swag labs application via mobile device
    When User input valid username
    And User input valid password
    And User tap Login button
    Then The system displays the products page