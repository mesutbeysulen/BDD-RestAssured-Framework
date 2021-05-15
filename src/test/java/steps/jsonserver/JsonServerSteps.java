package steps.jsonserver;

import com.fasterxml.jackson.core.JsonProcessingException;
import constants.GlobalVars;
import deserialization.DeSerialization;
import httpmethods.Get;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import serialization.Serialization;
import threadsafety.ApiResponse;
import threadsafety.StatusCode;

import static org.hamcrest.Matchers.*;

public class JsonServerSteps {

    @Given("^User performs get operation for '(.*)'$")
    public void performOperation(String path) {
        ApiResponse.setResponse(Get.getResponse(GlobalVars.getUrl2().concat(path)));
        StatusCode.setStatusCode(ApiResponse.getResponse().getStatusCode());
    }

    @And("^'(.*)' for request is '(.*)'$")
    public void result(String key, String val) {
        ApiResponse.getResponse().then().log().all().body(key, equalTo(val));
    }

    @Given("^User performs serialization for '(.*)'$")
    public void userPerformsSerialization(String apiPath) throws JsonProcessingException {
        ApiResponse.setResponse(Serialization.serExample(apiPath));
        StatusCode.setStatusCode(ApiResponse.getResponse().getStatusCode());
    }

    @Given("^User performs de-serialization for '(.*)'$")
    public void userPerformsDeSerializationForPhotos(String apiPath) throws JsonProcessingException {
        DeSerialization.deSerExample(apiPath);
    }
}
