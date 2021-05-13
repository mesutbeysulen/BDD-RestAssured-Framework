Feature: Verify different GET operations using REST Assured

  Scenario Outline: <scenario>
    Given Perform get operation for '<apiPath>'
    Then Status code is <status_code>
    Examples:
      |scenario                                 |status_code  |apiPath           |
      |Verify status code                       |200          |/api/users        |
      |Verify status code for non-existence user|404          |/api/users/9999999|

  Scenario Outline: <scenario>
    Given Perform get operation for '<apiPath>'
    When Status code is 200
    Then '<field>' are <count> for '<apiPath>'
    Examples:
      |scenario             |field        |count |apiPath           |
      |Verify total_pages   |total_pages  |2     |/api/users        |
      |Verify delay response|per_page     |6     |/api/users?delay=2|

  Scenario: Verify IDs - Success
    Given Perform get operation for '/api/users'
    When Status code is 200
    Then IDs are displayed for '/api/users'

  Scenario: Verify IDs - Fail
    Given Perform get operation for '/api/users'
    When Status code is 201
    Then IDs are displayed for '/api/users'

  Scenario: Single user verification
    Given Perform get operation for '/api/users/2'
    When Status code is 200
    Then 'first_name' is 'Janet' for '/api/users/2'
    And 'last_name' is 'Weaver' for '/api/users/2'

  Scenario: Query Parameters
    Given Perform query get operation for '/api/users'
    Then Status code is 200

  Scenario: Path Parameter
    Given Perform path get operation for '/api/users'
    Then Status code is 200
    And 'email' is 'janet.weaver@reqres.in' for '/api/users/2'