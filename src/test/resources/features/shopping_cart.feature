Feature: Shopping cart

  Scenario: Add product to shopping cart
    Given I am on the page for product 1_1_0_0
    When I add the product to the cart
    Then The cart contains the product 1_1_0_0

  Scenario: Remove product from shopping cart
    Given I have product 1_1_0_0 in the cart
    When I remove product 1_1_0_0 from the cart
    Then The cart does not contain the product 1_1_0_0