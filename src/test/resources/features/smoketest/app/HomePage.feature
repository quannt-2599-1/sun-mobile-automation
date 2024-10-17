@homepage-feature
Feature: HomePage scenarios

  Scenario Outline: Access to HomePage successful
    Given I login successful
    Then I should see HomePage Logo
    Examples:
      | example  |
      | example  |
