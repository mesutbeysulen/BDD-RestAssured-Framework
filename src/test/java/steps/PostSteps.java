package steps;

import constants.GlobalVars;
import httpmethods.Post;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import lombok.extern.java.Log;
import threadsafety.ApiResponse;
import threadsafety.StatusCode;
import java.util.HashMap;

import static org.hamcrest.Matchers.*;

@Log
public class PostSteps {

    @When("^Add user with (.*) and (.*) for '(.*)' using (.*)$")
    public void addUserWithNameAndJobForApiUsers(String val1, String val2, String apiPath, String bodyType) {
        switch (bodyType) {
            case "jsonString":
                String body = "{\"name\": \"" + val1 + "\",\"job\": \"" + val2 + "\"}";
                ApiResponse.setResponse(Post.getResponseUsingJsonStringBody(body, GlobalVars.getUrl().concat(apiPath)));
                break;
            case "hashmap":
                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put("name", val1);
                hashMap.put("job", val2);
                ApiResponse.setResponse(Post.getResponseUsingHashMapBody(hashMap, GlobalVars.getUrl().concat(apiPath)));
                break;
        }
        StatusCode.setStatusCode(ApiResponse.getResponse().getStatusCode());
    }

    @And("^Print (id|createdAt|token)$")
    public void printId(String field) {
        ApiResponse.getResponse().then().statusCode(201);
        log.info(field + " is " + ApiResponse.getResponse().jsonPath().getString(field));
    }

    @When("Add user from {string} for {string}")
    public void addUserFromSrcTestResourcesFilesSampleJsonFileJsonForApiUsers(String filePath, String apiPath) {
        ApiResponse.setResponse(Post.getResponseUsingJsonFile(filePath, GlobalVars.getUrl().concat(apiPath)));
        StatusCode.setStatusCode(ApiResponse.getResponse().getStatusCode());
    }

    @And("^'(.*)' message (.*) is displayed$")
    public void errorMessageError_msgIsDisplayed(String field, String errorMsg) {
        ApiResponse.getResponse().then().body(field, equalTo(errorMsg));
    }

    @When("^Add user with (.*) and (.*) for '(.*)'$")
    public void addUserWithEmailAndPasswordForApiRegister(String val1, String val2, String apiPath) {
        String body = "{\"email\": \"" + val1 + "\",\"password\": \"" + val2 + "\"}";
        ApiResponse.setResponse(Post.getResponseUsingJsonStringBody(body, GlobalVars.getUrl().concat(apiPath)));
        StatusCode.setStatusCode(ApiResponse.getResponse().getStatusCode());
    }
}
