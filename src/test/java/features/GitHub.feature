Feature: Authentication

  @NoAuth
  Scenario: No Auth
    Given Using performs no auth to 'https://api.github.com/users/prashant1507'
    Then Status code is 200
    And Print login

  @BasicAuth
  Scenario: Basic Auth
    Given Using performs basic auth to 'https://api.github.com/users/prashant1507' with 'username' and 'password'
    Then Status code is 200

  @DigestAuth
  Scenario: Digest Auth
    Given Using performs digest auth to 'https://github.com/users/prashant1507' with 'username' and 'password'
    Then Status code is 200

  @OAuth2
  Scenario: OAuth2
    Given Using performs digest auth to 'https://urlforOAuth2.com' using 'AccessToken'
    Then Status code is 200


