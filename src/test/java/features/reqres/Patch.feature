Feature: Verify different PATCH operations using REST Assured

  Scenario: Patch Operation - Success
    Given Perform patch operation for '/api/users/1'
    Then Status code is 200
    And Print updatedAt

  Scenario: Patch Operation - Fail
    Given Perform patch operation for '/api/users/1'
    Then Status code is 201
    And Print updatedAt