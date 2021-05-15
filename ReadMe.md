## _BDD RestAssured Automation Framework_

Sample automation project to practice RestApi using:
- Java Cucumber
- TestNG (with parallel test)
- Rest Assured
- Maven
- Allure Reporting


### Test Runner
Tests can be executed in parallel using:
- [pom.xml](pom.xml)
- [testng.xml](testng.xml)
- [TestRunner.java](src/test/java/runner/TestRunner.java)

### To Deploy Sample JSON Server
- Setup Node
- Install `npm i json-server` or `npm i json-server -g`
- Create a [db.json](src/test/resources/Files/db.json) file
- Run `json-server --watch db.json`
- Access server on `localhost:3000`  
[Reference Link](https://www.npmjs.com/package/json-server)
  
### Note:
- In [Common.java](src/main/java/utilities/Common.java), reusable methods are added using `RequestSpecBuilder` and `RequestSpecification`
- In [httpmethods package](src/main/java/httpmethods), reusable methods are added using given(), when(), etc

### Important Links:
- To generate POJO - [https://www.jsonschema2pojo.org/](https://www.jsonschema2pojo.org/)
- JSON Schema Validation
    - [Reference Video](https://www.youtube.com/watch?v=3NiHE311Dbw&list=PL6tu16kXT9PpgqfMbMdzUzDenYgb0gbk0&index=18) 
    - [Maven Dependency](https://mvnrepository.com/artifact/io.rest-assured/json-schema-validator/4.3.3)
- File Upload and Download using RestAssured
    - [Reference Video](https://www.youtube.com/watch?v=_NRgpI48ogQ&list=PL8VbCbavWfeE5aEeEpoXp2xiHi5K_7BMT&index=21)
- Allure Tags 
  ```
    @Epic
    @Features
    @Stories/@Story
    @Severity(SeverityLevel.BLOCKER)
    @Description("In this cool test we will check cool thing")
    @Step
    @Attachment
    @Link
    @Owner
  ```