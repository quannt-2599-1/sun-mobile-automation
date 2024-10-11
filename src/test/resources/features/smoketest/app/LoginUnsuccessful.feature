@login-unsuccessfully-feature
Feature: Login scenarios

  Scenario Outline: Login unsuccessfully - incorrect username
    When I enter username as "<username>"
    And I enter password
    And I press login button
    Then I should see a invalid account error like "<error>"
    Examples:
      | username | error                                                        |
      | invalid  | Username and password do not match any user in this service. |

  Scenario Outline: Login unsuccessfully - incorrect password
    When I enter username
    And I enter password as "<password>"
    And I press login button
    Then I should see a invalid account error like "<error>"
    Examples:
      | password | error                                                        |
      | invalid  | Username and password do not match any user in this service. |
