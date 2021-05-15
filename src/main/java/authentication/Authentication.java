package authentication;

import io.restassured.RestAssured;
import io.restassured.authentication.FormAuthConfig;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public final class Authentication {

    public static Response getResponseUsingNoAuth(String url) {
        return RestAssured.given().auth().none().get(url);
    }

    public static Response getResponseUsingBasicAuth(String url, String username, String pass) {
        return RestAssured.given().auth().basic(username, pass).get(url);
    }

    public static Response getResponseUsingDigestAuth(String url, String username, String pass) {
        // Change according to other params like MD5 sum, etc
        return RestAssured.given().auth().digest(username, pass).get(url);
    }

    public static Response getResponseUsingPrimitiveAuth(String url, String username, String pass) {
        return RestAssured.given().auth().preemptive().basic(username, pass).get(url);
    }

    public static Response getResponseUsingFormAuthConfig(String url, String formAuthPath, String username, String pass) {
        return RestAssured.given().auth().form(
                username,
                pass,
                new FormAuthConfig(formAuthPath, username, pass)).get(url);
    }

    public static Response getResponseUsingFormAuth(String url, String username, String pass) {
        return RestAssured.given().auth().form(username, pass).get(url);
    }

    public static Response getResponseOAuth1(String url, String consumerKey, String consumerSecret, String accessToken, String tokenSecret) {
        return RestAssured.given().accept(ContentType.JSON).auth().oauth(consumerKey, consumerSecret, accessToken, tokenSecret).get(url);
    }

    public static Response getResponseOAuth2(String url, String accessToken) {
        return RestAssured.given().auth().oauth2(accessToken).get(url);
        // or
        // RestAssured.given().header("Authorization", "Bearer ACCESSTOKEN").get(url);
    }
}
