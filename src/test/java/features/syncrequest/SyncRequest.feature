Feature: Verify different Sync requests using REST Assured

  @SyncResponse
  # Sync request blocks the execution until response is available. Here it will wait for 5 sec for response
  Scenario: Verify sync request
    Given Perform get operation for '/api/users?delay=5'
    When Status code is 200
    Then 'per_page' are 6 for '/api/users?delay=5'