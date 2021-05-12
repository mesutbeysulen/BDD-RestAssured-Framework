package steps;

import constants.GlobalVars;
import httpmethods.Get;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.testng.Assert;
import threadsafety.StatusCode;

import static org.hamcrest.Matchers.*;

public class GetSteps {

    @Given("Perform get operation for {string}")
    public void performGetOperation(String apiPath) {
        StatusCode.setStatusCode(Get.getStatusCode(GlobalVars.getUrl().concat(apiPath)));
    }

    @Then("Status code is {int}")
    public void statusCodeShouldBe(int code) {
        Assert.assertEquals(StatusCode.getStatusCode(), code, String.format("Status code is not %s", code));
    }

    @Then("{string} is {int} for {string}")
    public void total_pagesIsForApiUsers(String field, int expected, String apiPath) {
        int actual = Get.getBodyAsJsonPath(GlobalVars.getUrl().concat(apiPath)).getInt(field);
        Assert.assertEquals(actual, expected, String.format("Count is not %s", expected));
    }

    @Then("IDs are displayed for {string}")
    public void idIsDisplayedForApiUsers(String apiPath) {
       Get.getResponse(GlobalVars.getUrl().concat(apiPath)).then().body("data.id", containsInAnyOrder(1, 2, 3, 4, 5, 6));
    }

    @Then("{string} is {string} for {string}")
    public void first_nameIsJanetForApiUsers(String field, String expectedValue, String apiPath) {
        String actualValue = Get.getBodyAsJsonPath(GlobalVars.getUrl().concat(apiPath)).getString("data.".concat(field));
        Assert.assertEquals(actualValue, expectedValue, String.format("%s is not %s", field, expectedValue));
    }

    @Given("Perform query get operation for {string}")
    public void performQueryGetOperationForApiUsers(String apiPath) {
        int statusCode = Get.getResponseUsingQueryParams("page", "2", GlobalVars.getUrl().concat(apiPath)).getStatusCode();
        StatusCode.setStatusCode(statusCode);
    }

    @Given("Perform path get operation for {string}")
    public void performPathGetOperationForApiUsers(String apiPath) {
        int statusCode = Get.getResponseUsingPathParams("id", "2", GlobalVars.getUrl().concat(apiPath)).getStatusCode();
        StatusCode.setStatusCode(statusCode);
    }
}
