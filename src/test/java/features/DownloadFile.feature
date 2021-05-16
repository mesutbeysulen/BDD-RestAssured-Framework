Feature: File Download

  @FileDownload
  Scenario: File Download
    Given User save response to file for '/api/users'
    Then Status code is 200