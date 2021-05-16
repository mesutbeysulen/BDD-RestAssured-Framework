package steps.reqres;

import constants.GlobalVars;
import servicehelpers.Post;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import lombok.extern.java.Log;
import settergetter.ThreadSafety;
import java.util.HashMap;
import java.util.List;

import static org.hamcrest.Matchers.*;

@Log
public class PostSteps {

    @When("^Add user with (.*) and (.*) for '(.*)' using (.*)$")
    public void addUser(String val1, String val2, String endpoint, String bodyType) {
        switch (bodyType) {
            case "jsonString":
                String body = "{\"name\": \"" + val1 + "\",\"job\": \"" + val2 + "\"}";
                ThreadSafety.setResponse(Post.getResponseUsingJsonStringBody(body, GlobalVars.getUrl().concat(endpoint)));
                break;
            case "hashmap":
                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put("name", val1);
                hashMap.put("job", val2);
                ThreadSafety.setResponse(Post.getResponseUsingHashMapBody(hashMap, GlobalVars.getUrl().concat(endpoint)));
                break;
        }
        ThreadSafety.setStatusCode(ThreadSafety.getResponse().getStatusCode());
    }

    @And("^Print (.*)$")
    public void printValue(String key) {
        log.info(key + " is " + ThreadSafety.getResponse().jsonPath().getString(key));
        // or
        // Response res = ThreadSafety.getResponse();
        // JsonPath jsonPath = res.jsonPath();
        // log.info(jsonPath.getString(key));
    }

    @When("^Add user from '(.*)' for '(.*)'$")
    public void addUserFromJsonFile(String filePath, String endpoint) {
        ThreadSafety.setResponse(Post.getResponseUsingJsonFile(filePath, GlobalVars.getUrl().concat(endpoint)));
        ThreadSafety.setStatusCode(ThreadSafety.getResponse().getStatusCode());
    }

    @And("^'(.*)' message (.*) is displayed$")
    public void errorMessage(String field, String errorMsg) {
        ThreadSafety.getResponse().then().body(field, equalTo(errorMsg));
    }

    @When("^Add user with (.*) and (.*) for '(.*)'$")
    public void userRegistration(String val1, String val2, String endpoint) {
        String body = "{\"email\": \"" + val1 + "\",\"password\": \"" + val2 + "\"}";
        ThreadSafety.setResponse(Post.getResponseUsingJsonStringBody(body, GlobalVars.getUrl().concat(endpoint)));
        ThreadSafety.setStatusCode(ThreadSafety.getResponse().getStatusCode());
    }

    @When("^Add user for '(.*)' using data table$")
    public void addUserUsingDataTable(String endpoint, DataTable dt) {
        List<List<String>> data = dt.asLists();
        String name = data.get(0).get(0); // 0th column, 0th row
        String job = data.get(0).get(1); //  1st row , 0th column
        String body = "{\"name\": \"" + name + "\",\"job\": \"" + job + "\"}";
        ThreadSafety.setResponse(Post.getResponseUsingJsonStringBody(body, GlobalVars.getUrl().concat(endpoint)));
        ThreadSafety.setStatusCode(ThreadSafety.getResponse().getStatusCode());
    }
}
