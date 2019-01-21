Feature: Shopping cart

  Scenario: Add product to shopping cart
    Given I am on the page for product 1_1_0_0
    When I add the product to the cart
    Then The cart contains the product 1_1_0_0
