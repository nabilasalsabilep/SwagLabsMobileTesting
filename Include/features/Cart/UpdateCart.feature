@Cart
Feature: Cart feature

  @UpdateCart
  Scenario: User wants to update the cart by removing products from the cart
    Given User opens swag labs application via mobile device
    And User input valid username
    And User input valid password
    And User tap Login button
    And User taps the add to cart button of 6 products from the product list or details
    And User taps on the cart button in the header
    When User removes product from cart
    Then User verifies cart after update