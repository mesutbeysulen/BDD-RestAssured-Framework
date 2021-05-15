package steps.reqres;

import constants.GlobalVars;
import httpmethods.Delete;
import io.cucumber.java.en.Given;
import threadsafety.StatusCode;

public class DeleteSteps {
    @Given("^Perform delete operation for '(.*)'$")
    public void deleteOperation(String apiPath) {
        StatusCode.setStatusCode(Delete.delete(GlobalVars.getUrl().concat(apiPath)).getStatusCode());
    }
}
