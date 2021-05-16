package steps;

import com.fasterxml.jackson.core.JsonProcessingException;
import constants.GlobalVars;
import utilities.DeSerialization;
import servicehelpers.Get;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import utilities.Serialization;
import settergetter.ThreadSafety;

import static org.hamcrest.Matchers.*;

public class LocalJsonServerSteps {

    @Given("^User performs get operation for '(.*)'$")
    public void performOperation(String endpoint) {
        ThreadSafety.setResponse(Get.getResponse(GlobalVars.getUrl2().concat(endpoint)));
        ThreadSafety.setStatusCode(ThreadSafety.getResponse().getStatusCode());
    }

    @And("^'(.*)' for request is '(.*)'$")
    public void result(String key, String val) {
        ThreadSafety.getResponse().then().log().all().body(key, equalTo(val));
    }

    @Given("^User performs serialization for '(.*)'$")
    public void userPerformsSerialization(String endpoint) throws JsonProcessingException {
        ThreadSafety.setResponse(Serialization.serExample(endpoint));
        ThreadSafety.setStatusCode(ThreadSafety.getResponse().getStatusCode());
    }

    @Given("^User performs de-serialization for '(.*)'$")
    public void userPerformsDeSerializationForPhotos(String endpoint) throws JsonProcessingException {
        DeSerialization.deSerExample(endpoint);
    }
}
