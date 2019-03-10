Feature: Shopping cart

  Scenario: Add product to shopping cart
    Given I am on the page for a product
    When I add the product to the cart
    Then The cart contains the product

  Scenario: Remove product from shopping cart
    Given I have a product in the cart
    When I remove the product from the cart
    Then The cart does not contain the product

  Scenario: Update cart item quantity
    Given I have a product in the cart
    When I update the quantity to 2
    Then The product total price is updated according to the new quantity