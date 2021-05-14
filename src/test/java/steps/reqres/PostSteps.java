package steps.reqres;

import constants.GlobalVars;
import httpmethods.Post;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import lombok.extern.java.Log;
import threadsafety.ApiResponse;
import threadsafety.StatusCode;
import java.util.HashMap;
import java.util.List;

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

    @And("^Print (id|createdAt|token|updatedAt)$")
    public void printId(String field) {
        ApiResponse.getResponse().then().statusCode(StatusCode.getStatusCode());
        log.info(field + " is " + ApiResponse.getResponse().jsonPath().getString(field));
    }

    @When("^Add user from '(.*)' for '(.*)'$")
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

    @When("^Add user for '(.*)' using data table$")
    public void addUserForApiUsersUsingDataTable(String apiPath, DataTable dt) {
        List<List<String>> data = dt.asLists();
        String name = data.get(0).get(0); // 0th column, 0th row
        String job = data.get(0).get(1); //  1st row , 0th column
        String body = "{\"name\": \"" + name + "\",\"job\": \"" + job + "\"}";
        ApiResponse.setResponse(Post.getResponseUsingJsonStringBody(body, GlobalVars.getUrl().concat(apiPath)));
        StatusCode.setStatusCode(ApiResponse.getResponse().getStatusCode());
    }
}
