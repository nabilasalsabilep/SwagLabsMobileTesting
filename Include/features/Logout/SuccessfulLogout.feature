@Logout
Feature: Logout feature

  @SuccessfullyLogout
  Scenario: User wants to successfully logout from the app
    Given User opens swag labs application via mobile device
    And User input valid username
    And User input valid password
    And User tap Login button
    When User tap burger menu on the header
    And User tap LOGOUT sub menu
    Then User returns to the login page