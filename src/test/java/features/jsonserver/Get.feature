Feature: Verify different GET operations using REST Assured

  Scenario: Verify ping api result
    Given User performs get operation for '/ping'
    Then Status code is 200
    And 'result' for request is 'pong'
    And Print result

#  Scenario: