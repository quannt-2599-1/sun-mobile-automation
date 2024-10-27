@checkoutConfirmation-feature
Feature: Checkout Confirmation scenarios

  Scenario Outline: Firstname, LastName, Zip/PostalCode textbox is enable
    Given I access to Checkout Confirmation screen successful
    Then I should see Firstname, LastName, ZipPostalCode textbox is enable
    Examples:
      | example  |
      | example  |
  Scenario Outline: Error message displays when Lastname is null
    Given I access to Checkout Confirmation screen successful
    When I input Firstname
    And I click to Continue button
    Then I should see Error messsage Lastname is required
    Examples:
      | example  |
      | example  |