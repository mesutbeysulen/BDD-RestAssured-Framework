package steps;

import utilities.Authentication;
import io.cucumber.java.en.Given;
import settergetter.ThreadSafety;

public class AuthSteps {

    @Given("^Using performs basic auth to '(.*)' with '(.*)' and '(.*)'$")
    public void basicAuth(String url, String username, String pass) {
        ThreadSafety.setResponse(Authentication.getResponseUsingBasicAuth(url, username, pass));
        ThreadSafety.setStatusCode(ThreadSafety.getResponse().getStatusCode());
    }

    @Given("^Using performs digest auth to '(.*)' with '(.*)' and '(.*)'$")
    public void digestAuth(String url, String username, String pass) {
        ThreadSafety.setResponse(Authentication.getResponseUsingDigestAuth(url, username, pass));
        ThreadSafety.setStatusCode(ThreadSafety.getResponse().getStatusCode());
    }

    @Given("^Using performs no auth to '(.*)'$")
    public void noAuth(String url) {
        ThreadSafety.setResponse(Authentication.getResponseUsingNoAuth(url));
        ThreadSafety.setStatusCode(ThreadSafety.getResponse().getStatusCode());
    }

    @Given("^Using performs digest auth to '(.*)' using '(.*)'$")
    public void oAuth2(String url, String accessToken) {
        ThreadSafety.setResponse(Authentication.getResponseOAuth2(url, accessToken));
        ThreadSafety.setStatusCode(ThreadSafety.getResponse().getStatusCode());
    }
}
