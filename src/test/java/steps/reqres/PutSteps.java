package steps.reqres;

import constants.GlobalVars;
import servicehelpers.Put;
import io.cucumber.java.en.Given;
import settergetter.ThreadSafety;

public class PutSteps {

    @Given("^Perform put operation for '(.*)'$")
    public void putOperation(String endpoint) {
        String body = "{\"name\": \"user1\",\"job\": \"qa\"}";
        ThreadSafety.setResponse(Put.put(body, GlobalVars.getUrl().concat(endpoint)));
        ThreadSafety.setStatusCode(ThreadSafety.getResponse().getStatusCode());
    }
}
