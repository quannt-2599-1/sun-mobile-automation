@login-successfully-feature
Feature: Login scenarios

  Scenario Outline: Login successfully
    When I enter username
    And I enter password
    And I press login button
    Then I should see homepage
    Examples:
      | example       |
      | example value |
