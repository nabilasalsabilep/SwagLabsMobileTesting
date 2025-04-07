@Checkout
Feature: Checkout feature

  @Checkout
  Scenario: Users want to successfully check out products from their cart
    Given User opens swag labs application via mobile device
    And User input valid username
    And User input valid password
    And User tap Login button
    And User taps the add to cart button of 3 products from the product list or details
    And User taps on the cart button in the header
    And User verifies the product in the cart
    When User taps Checkout button on the cart page
    And User enters the required data on the Checkout: Information
    And User taps continue button
    And User Verifies Checkout Page: Overview
    And User taps Finish button
    Then User verifies the Finish page after completing the checkout process