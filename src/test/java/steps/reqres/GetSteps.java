package steps.reqres;

import constants.GlobalVars;
import httpmethods.Get;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.testng.Assert;
import threadsafety.ApiResponse;
import threadsafety.StatusCode;

import java.io.File;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class GetSteps {

    @Given("^Perform get operation for '(.*)'$")
    public void performGetOperation(String apiPath) {
        StatusCode.setStatusCode(Get.getStatusCode(GlobalVars.getUrl().concat(apiPath)));
    }

    @Then("^Status code is (.*)$")
    public void statusCodeShouldBe(int code) {
        Assert.assertEquals(StatusCode.getStatusCode(), code, String.format("Status code is not %s", code));
    }

    @Then("^'(.*)' are (.*) for '(.*)'$")
    public void fieldsValidation(String field, int expected, String apiPath) {
        int actual = Get.getBodyAsJsonPath(GlobalVars.getUrl().concat(apiPath)).getInt(field);
        Assert.assertEquals(actual, expected, String.format("Count is not %s", expected));
    }

    @Then("^IDs are displayed for '(.*)'$")
    public void idIsDisplayedForApiUsers(String apiPath) {
       Get.getResponse(GlobalVars.getUrl().concat(apiPath)).then().body("data.id", containsInAnyOrder(1, 2, 3, 4, 5, 6));
    }

    @Then("^'(.*)' is '(.*)' for '(.*)'$")
    public void dataForApiUsers(String field, String expectedValue, String apiPath) {
        String actualValue = Get.getBodyAsJsonPath(GlobalVars.getUrl().concat(apiPath)).getString("data.".concat(field));
        Assert.assertEquals(actualValue, expectedValue, String.format("%s is not %s", field, expectedValue));
    }

    @Given("^Perform query get operation for '(.*)'$")
    public void performQueryGetOperationForApiUsers(String apiPath) {
        int statusCode = Get.getResponseUsingQueryParams("page", "2", GlobalVars.getUrl().concat(apiPath)).getStatusCode();
        StatusCode.setStatusCode(statusCode);
    }

    @Given("^Perform path get operation for '(.*)'$")
    public void performPathGetOperationForApiUsers(String apiPath) {
        int statusCode = Get.getResponseUsingPathParams("id", "2", GlobalVars.getUrl().concat(apiPath)).getStatusCode();
        StatusCode.setStatusCode(statusCode);
    }

    @Given("^Perform get operation for '(.*)' for JSON Schema Validation$")
    public void performPathGetOperationForJSONSchema(String apiPath) {
        ApiResponse.setResponse(Get.getResponse(GlobalVars.getUrl().concat(apiPath)));
    }

    @Then("^Validate response using JSON file$")
    public void dataForApiUserUsingJSONSchemaValidation() {
        assertThat(ApiResponse.getResponse().asString(), matchesJsonSchema(new File("src/test/resources/Files/JSONSchemaValidationExample.json")));
    }
}
