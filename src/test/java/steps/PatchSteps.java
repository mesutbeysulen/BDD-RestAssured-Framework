package steps;

import constants.GlobalVars;
import httpmethods.Patch;
import io.cucumber.java.en.Given;
import threadsafety.ApiResponse;
import threadsafety.StatusCode;

public class PatchSteps {
    @Given("^Perform patch operation for '(.*)'$")
    public void performPatchOperationForApiUsers(String apiPath) {
        String body = "{\"name\": \"user1\",\"job\": \"qa\"}";
        ApiResponse.setResponse(Patch.patch(body, GlobalVars.getUrl().concat(apiPath)));
        StatusCode.setStatusCode(ApiResponse.getResponse().getStatusCode());
    }
}
