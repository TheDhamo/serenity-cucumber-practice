Feature: Shopping cart

  Scenario: Add item to shopping cart
    Given I am on a product page
    When I add the item to the cart
    Then The cart contains the added item
