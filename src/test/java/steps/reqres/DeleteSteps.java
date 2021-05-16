package steps.reqres;

import constants.GlobalVars;
import servicehelpers.Delete;
import io.cucumber.java.en.Given;
import settergetter.ThreadSafety;

public class DeleteSteps {
    @Given("^Perform delete operation for '(.*)'$")
    public void deleteOperation(String endpoint) {
        ThreadSafety.setStatusCode(Delete.delete(GlobalVars.getUrl().concat(endpoint)).getStatusCode());
    }
}
