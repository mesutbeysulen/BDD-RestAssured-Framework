package steps.async;

import async.ASyncRequest;
import constants.GlobalVars;
import io.cucumber.java.en.Given;
import threadsafety.ApiResponse;

import java.util.concurrent.ExecutionException;

public class ASyncRequestSteps {
    @Given("^Perform async operation for '(.*)'$")
    public void asyncRequest(String apiPath) throws ExecutionException, InterruptedException {
        ASyncRequest.getResponse(GlobalVars.getUrl().concat(apiPath));
    }
}
