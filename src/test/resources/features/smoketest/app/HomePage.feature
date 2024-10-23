@homepage-feature
Feature: HomePage scenarios

  Scenario Outline: Access to HomePage successful
    Given I login successful
    Then I should see HomePage Logo
    Examples:
      | example  |
      | example  |

  Scenario Outline: Open menu list when tapping on hamburger menu
      Given I login successful
      When I tapping on hamburger menu
      Then I should see Menu list
      Examples:
        | example  |
        | example  |

  Scenario Outline: Close Menu List when tapping X button
        Given I login successful
        When I tapping on hamburger menu
        When I tapping on X button
        Then I should see HomePage Logo
        Examples:
          | example  |
          | example  |

  Scenario Outline: Click to add to Cart product Sauce Labs Onesie
          Given I login successful
          When I scroll to element with "<childLocAttr>" and "<childLocValue>"
          When I add to cart sauce labs onesise
          Then I should see Cart displays 1 item
          Examples:
            | childLocAttr  |childLocValue|
            | text  |Sauce Labs Onesie|

  Scenario Outline: Open Cart when click to Cart icon
            Given I login successful
            When I click to cart icon
            Then I should see Cart screen displays
            Examples:
              | example  |
              | example  |
  Scenario Outline: Check price
            Given I login successful
            When I scroll to Sauce Labs Fleece Jacket with "<childLocAttr>" and "<childLocValue>"
            Then I should see price of Sauce Labs Fleece Jacket
            Examples:
            | childLocAttr  |childLocValue|
            | text  |Sauce Labs Fleece Jacket|
