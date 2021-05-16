package steps.reqres;

import constants.GlobalVars;
import servicehelpers.Get;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.qameta.allure.*;
import org.testng.Assert;
import settergetter.ThreadSafety;

import java.io.File;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class GetSteps {

    @Owner("User1")
    @Severity(SeverityLevel.CRITICAL)
    @Given("^Perform get operation for '(.*)'$")
    public void performGetOperation(String endpoint) {
        ThreadSafety.setStatusCode(Get.getStatusCode(GlobalVars.getUrl().concat(endpoint)));
        // or
        // StatusCode.setStatusCode(Common.getResponse(GlobalVars.getUrl(), endpoint).getStatusCode());
    }

    @Then("^Status code is (.*)$")
    public void statusCodeIs(int code) {
        assertThat(String.format("Status code is not %s", code), ThreadSafety.getStatusCode(), is(code));
        // or
        // Assert.assertEquals(StatusCode.getStatusCode(), code, String.format("Status code is not %s", code));
    }

    @Then("^'(.*)' are (.*) for '(.*)'$")
    public void fieldsValidation(String field, int expected, String endpoint) {
        int actual = Get.getBodyAsJsonPath(GlobalVars.getUrl().concat(endpoint)).getInt(field);
        assertThat(String.format("Count is not %s", expected), actual, is(expected));
        // or
        // Assert.assertEquals(actual, expected, String.format("Count is not %s", expected));
    }

    @Then("^IDs are displayed for '(.*)'$")
    public void idIsDisplayed(String endpoint) {
       Get.getResponse(GlobalVars.getUrl().concat(endpoint)).then().body("data.id", containsInAnyOrder(1, 2, 3, 4, 5, 6));
    }

    @Then("^'(.*)' is '(.*)' for '(.*)'$")
    public void dataForApiUsers(String field, String expectedValue, String endpoint) {
        String actualValue = Get.getBodyAsJsonPath(GlobalVars.getUrl().concat(endpoint)).getString("data.".concat(field));
        Assert.assertEquals(actualValue, expectedValue, String.format("%s is not %s", field, expectedValue));
    }

    @Given("^Perform query get operation for '(.*)'$")
    public void queryGetOperation(String endpoint) {
        int statusCode = Get.getResponseUsingQueryParams("page", "2", GlobalVars.getUrl().concat(endpoint)).getStatusCode();
        ThreadSafety.setStatusCode(statusCode);
    }

    @Given("^Perform path get operation for '(.*)'$")
    public void pathGetOperation(String endpoint) {
        int statusCode = Get.getResponseUsingPathParams("id", "2", GlobalVars.getUrl().concat(endpoint)).getStatusCode();
        ThreadSafety.setStatusCode(statusCode);
    }

    @Given("^Perform get operation for '(.*)' for JSON Schema Validation$")
    public void pathGetOperationForJSONSchema(String endpoint) {
        ThreadSafety.setResponse(Get.getResponse(GlobalVars.getUrl().concat(endpoint)));
        // or
        // ThreadSafety.setResponse(Common.getResponse(GlobalVars.getUrl(), endpoint));
    }

    @Then("^Validate response using JSON file$")
    public void validateUserDataUsingJSONSchemaValidation() {
        assertThat(ThreadSafety.getResponse().asString(), matchesJsonSchema(new File("src/test/resources/Files/JSONSchemaValidationExample.json")));
    }
}
