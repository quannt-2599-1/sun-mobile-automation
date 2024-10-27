@cart-feature
Feature: Cart scenarios

#   Scenario Outline: Access to Cart screen successful
#    Given I login and click to Cart Icon successful
#     When I click to cart icon
#     Then I should see Cart screen displays
#     And I should see Quantity label
#     And I should see Cart Description
#     Examples:
#      | example  |
#       | example  |
#   Scenario Outline: Add product to cart successful
#    Given I login successful
#     When I add product Sauce Labs Backpack to cart
#     And I click to cart icon
#     Then I should see product Sauce Labs Backpack to cart displays on Cart
#     And I should see quantity is 1
#     And I should see product description
#     And I should see price product
#
#    Examples:
#       | example  |
#       | example  |
#
#  Scenario Outline: Delete product from cart
#      Given I login successful
#      When I add product Sauce Labs Backpack to cart
#      And I click to cart icon
#      And I click to remove button
#      Then I should see product Sauce Labs Backpack remove from cart
#      Examples:
#        | example  |
#        | example  |
  Scenario Outline: Delete product from cart
    Given I login successful
    When I add product Sauce Labs Backpack to cart
    And I click to cart icon
    And I click to remove button
    Then I should see product Sauce Labs Backpack remove from cart
    Examples:
      | example  |
      | example  |
#  Scenario Outline: Add more item to Cart
#    Given I login successful
#    When I add product Sauce Labs Backpack to cart
#    And I click to cart icon
#    And I click Continue shopping button
#    And I scroll to Sauce Labs Bolt TShirt with "<childLocAttr>" and "<childLocValue>"
#    And I add Bolt T-shirt to cart
#    And I click to cart icon
#    Then I should see Backpack and Bolt Tshirt display on Cart
#    Examples:
#      | childLocAttr  |childLocValue|
#      | text  |Sauce Labs Bolt T-Shirt|

#  Scenario Outline: Checkout confirmation screen displays when click to Checkout button
#    Given I login successful
#    When I add product Sauce Labs Backpack to cart
#    And I click to cart icon
#    And I click to checkout Button
#    Then I should see Checkout confirmation screen displays
#    Examples:
#      | example  |
#      | example  |