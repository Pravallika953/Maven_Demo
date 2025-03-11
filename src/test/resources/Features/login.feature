Feature: Shopping Cart functionality on SauceDemo website

  Scenario: User logs in and adds a product to the cart
    Given the user is logged in to SauceDemo
    When the user adds a product to the cart
    Then the user views the cart with the selected product
    And the cart should display the correct product name
