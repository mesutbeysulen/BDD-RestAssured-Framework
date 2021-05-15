Feature: Verify different DeSerialization operations using REST Assured

  @DeSerialization
  Scenario: De-Serialization
    Given User performs de-serialization for '/photos/1'
    Then Status code is 200
    And Print id