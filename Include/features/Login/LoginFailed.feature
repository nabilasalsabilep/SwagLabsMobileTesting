@Login
Feature: Login feature

@LoginFailed
  Scenario: User login with invalid email and password
  	Given User opens swag labs application via mobile device
  	When User input invalid username
  	And User input invalid password
  	And User tap Login button
  	Then The system will display a failed login error message