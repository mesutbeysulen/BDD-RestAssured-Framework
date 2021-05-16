Feature: File Upload

  @FileUpload
  Scenario: File Upload
    Given User performs file upload to 'https://the-internet.herokuapp.com/upload'
    Then Status code is 200