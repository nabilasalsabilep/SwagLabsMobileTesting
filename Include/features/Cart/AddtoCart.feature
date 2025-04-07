@Cart
Feature: Cart feature

  @AddtoCart
  Scenario: User wants to add multiple products to the cart
    Given User opens swag labs application via mobile device
    And User input valid username
    And User input valid password
    And User tap Login button
    When User taps the add to cart button of 4 products from the product list or details
    Then The number of products in the cart will increase
    And User taps on the cart button in the header
    And User verifies the product in the cart