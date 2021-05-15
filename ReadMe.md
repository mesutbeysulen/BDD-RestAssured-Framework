## _BDD RestAssured Automation Framework_

Sample automation project to practice RestApi using:
- Java Cucumber
- TestNG (with parallel test)
- Rest Assured
- Maven


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

