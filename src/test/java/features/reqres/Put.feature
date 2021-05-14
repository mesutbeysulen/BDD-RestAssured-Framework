Feature: Verify different PUT operations using REST Assured

  Scenario: Put Operation - Success
    Given Perform put operation for '/api/users/1'
    Then Status code is 200
    And Print updatedAt

  Scenario: Put Operation - Fail
    Given Perform put operation for '/api/users/1'
    Then Status code is 201
    And Print updatedAt