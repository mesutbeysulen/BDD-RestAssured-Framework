package steps;

import utilities.ASyncRequest;
import constants.GlobalVars;
import io.cucumber.java.en.Given;

import java.util.concurrent.ExecutionException;

public class ASyncRequestSteps {
    @Given("^Perform async operation for '(.*)'$")
    public void asyncRequest(String endpoint) throws ExecutionException, InterruptedException {
        ASyncRequest.getResponse(GlobalVars.getUrl().concat(endpoint));
    }
}
