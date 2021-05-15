Feature: Verify different DELETE operations using REST Assured

  Scenario: Delete Operation - Success
    Given Perform delete operation for '/api/users/1'
    Then Status code is 204

  Scenario: Delete Operation - Fail
    Given Perform delete operation for '/api/users/1'
    Then Status code is 205