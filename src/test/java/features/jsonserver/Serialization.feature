Feature: Verify different Serialization operations using REST Assured

  @Serialization
  Scenario: Serialization
    Given User performs serialization for '/photos'
    Then Status code is 201
    And Print id
