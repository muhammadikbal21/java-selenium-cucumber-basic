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