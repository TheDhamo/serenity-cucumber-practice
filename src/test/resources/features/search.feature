Feature: Search

  Scenario: Search for product
    Given I am on the homepage
    When I search for 'print'
    Then The search returns related products