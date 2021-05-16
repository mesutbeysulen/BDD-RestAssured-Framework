Feature: Verify different async request using REST Assured

  @ASyncResponse
  # ASync request do not blocks the execution until response is available. Here it will not wait for 5 sec for response
    # and continues the execution. It returns a callback when execution is done.
  Scenario: Verify async request
    Given Perform async operation for '/api/users?delay=5'
    When Status code is 200