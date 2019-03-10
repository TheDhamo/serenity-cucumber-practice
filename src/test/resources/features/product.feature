Feature: Product

  Scenario: Send to a friend
    Given I am on the page for a product
    When I send the product to a friend
    Then I see a success message