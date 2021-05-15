package steps.authentication;

import authentication.Authentication;
import io.cucumber.java.en.Given;
import threadsafety.ApiResponse;
import threadsafety.StatusCode;

public class AuthSteps {

    @Given("^Using performs basic auth to '(.*)' with '(.*)' and '(.*)'$")
    public void basicAuth(String url, String username, String pass) {
        ApiResponse.setResponse(Authentication.getResponseUsingBasicAuth(url, username, pass));
        StatusCode.setStatusCode(ApiResponse.getResponse().getStatusCode());
    }

    @Given("^Using performs digest auth to '(.*)' with '(.*)' and '(.*)'$")
    public void digestAuth(String url, String username, String pass) {
        ApiResponse.setResponse(Authentication.getResponseUsingDigestAuth(url, username, pass));
        StatusCode.setStatusCode(ApiResponse.getResponse().getStatusCode());
    }

    @Given("^Using performs no auth to '(.*)'$")
    public void digestAuth(String url) {
        ApiResponse.setResponse(Authentication.getResponseUsingNoAuth(url));
        StatusCode.setStatusCode(ApiResponse.getResponse().getStatusCode());
    }

    @Given("^Using performs digest auth to '(.*)' with '(.*)'$")
    public void oAuth2(String url, String accessToken) {
        ApiResponse.setResponse(Authentication.getResponseOAuth2(url, accessToken));
        StatusCode.setStatusCode(ApiResponse.getResponse().getStatusCode());
    }
}
