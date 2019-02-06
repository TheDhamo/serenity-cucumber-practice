Feature: Search

  Scenario: Search for product
    Given I am on the homepage
    When I search for 'print'
    Then The search returns related products

  Scenario: View product page
    Given I have searched for a product
    When I view the page of the first product
    Then I see the related product page

  Scenario: View product preview
    Given I have searched for a product
    When I view the product preview
    Then I see the related product preview
