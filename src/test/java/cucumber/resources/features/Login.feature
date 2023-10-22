Feature: Login to kasirAja

  @Regression @Positive
  Scenario: Login with Valid Account
    Given User on login page
    When Input username
    And Input password
    And Click login button
    Then User will be on dashboard page

  @Negative
  Scenario: Login with Invalid Account
    Given User on login page
    When Input username
    And Input invalid password
    And Click login button
    Then User got error message

  @TDD
# menjalankan scenario yg sama, dengan data input yang berbeda beda
  Scenario Outline: Login with TDD
    Given I am on login page
    When I input <email> as email
    And I input <password> as password
    And I click login button
    Then I verify <status> login result with <username> as username
#   mendeklarasikan data input beserta value nya
    Examples:
      | email               | password  | status  | username    |
      | ikbalshop@shop.com  | 123456    | success | Ikbal Shop  |
      | ikbalshop@shop.com  | 123223    | failed  | Ikbal Shop  |
