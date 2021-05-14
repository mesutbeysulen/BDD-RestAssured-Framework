Feature: Verify different POST operations using REST Assured

  Scenario Outline: Create users using <body_type> - Success
    When Add user with <name> and <job> for '/api/users' using <body_type>
    Then Status code is 201
    And Print id
    And Print createdAt
    Examples:
      |name   |job |body_type |
      |user1  |QA  |jsonString|
      |user2  |Dev |jsonString|
      |user3  |Eng |hashmap   |
      |user4  |Sec |hashmap   |

  Scenario Outline: Create users using <body_type> - Fail
    When Add user with <name> and <job> for '/api/users' using <body_type>
    Then Status code is 204
    And Print id
    And Print createdAt
    Examples:
      |name   |job |body_type |
      |user1  |QA  |jsonString|
      |user2  |Dev |jsonString|
      |user3  |Eng |hashmap   |
      |user4  |Sec |hashmap   |

  @JSONFileForBody
  Scenario: Create user using json file
    When Add user from 'src/test/resources/Files/SampleJsonFile1.json' for '/api/users'
    Then Status code is 201
    And Print id
    And Print createdAt

  Scenario Outline: Successful user registration
    When Add user with <email> and <password> for '/api/register'
    Then Status code is 200
    And Print id
    And Print token
    Examples:
      |email            |password  |
      |user1@user1.com  |qwerty11  |
      |user2@user2.com  |qwerty11  |

  Scenario Outline: Successful user login
    When Add user with <email> and <password> for '/api/login'
    Then Status code is 200
    And Print token
    Examples:
      |email            |password  |
      |user1@user1.com  |qwerty11  |
      |user2@user2.com  |qwerty11  |

  Scenario Outline: Un-successful user <type>
    When Add user with <email> and <password> for '<api>'
    Then Status code is 400
    And 'error' message <error_msg> is displayed
    Examples:
      |email            |password  |error_msg                |api          |type        |
      |user1@user1.com  |          |Missing password         |/api/register|registration|
      |                 |qwerty11  |Missing email or username|/api/login   |registration|
      |user1@user1.com  |          |Missing password         |/api/register|login       |
      |                 |qwerty11  |Missing email or username|/api/login   |login       |

  @DataTable
  Scenario: Create users using data table
    When Add user for '/api/users' using data table
      |user1  |QA  |
    Then Status code is 201
    And Print id
    And Print createdAt