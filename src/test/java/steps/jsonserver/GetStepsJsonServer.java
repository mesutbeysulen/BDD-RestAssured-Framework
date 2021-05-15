package steps.jsonserver;

import constants.GlobalVars;
import httpmethods.Get;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import threadsafety.ApiResponse;
import threadsafety.StatusCode;

import static org.hamcrest.Matchers.*;

public class GetStepsJsonServer {

    @Given("^User performs get operation for '(.*)'$")
    public void userDoesGetOperationForPing(String path) {
        ApiResponse.setResponse(Get.getResponse(GlobalVars.getUrl2().concat(path)));
        StatusCode.setStatusCode(ApiResponse.getResponse().getStatusCode());
    }

    @And("^'(.*)' for request is '(.*)'$")
    public void result(String key, String val) {
        ApiResponse.getResponse().then().log().all().body(key, equalTo(val));
    }
}
