package steps.reqres;

import constants.GlobalVars;
import servicehelpers.Patch;
import io.cucumber.java.en.Given;
import settergetter.ThreadSafety;

public class PatchSteps {
    @Given("^Perform patch operation for '(.*)'$")
    public void patchOperation(String endpoint) {
        String body = "{\"name\": \"user1\",\"job\": \"qa\"}";
        ThreadSafety.setResponse(Patch.patch(body, GlobalVars.getUrl().concat(endpoint)));
        ThreadSafety.setStatusCode(ThreadSafety.getResponse().getStatusCode());
    }
}
