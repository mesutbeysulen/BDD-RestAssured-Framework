package steps.reqres;

import constants.GlobalVars;
import httpmethods.Put;
import io.cucumber.java.en.Given;
import threadsafety.ApiResponse;
import threadsafety.StatusCode;

public class PutSteps {

    @Given("^Perform put operation for '(.*)'$")
    public void putOperation(String apiPath) {
        String body = "{\"name\": \"user1\",\"job\": \"qa\"}";
        ApiResponse.setResponse(Put.put(body, GlobalVars.getUrl().concat(apiPath)));
        StatusCode.setStatusCode(ApiResponse.getResponse().getStatusCode());
    }
}
