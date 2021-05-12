package httpmethods;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import static io.restassured.RestAssured.given;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class Get {

    public static Response getResponse(String url) {
        return given().contentType(ContentType.JSON).when()
                .get(url);
    }

    public static int getStatusCode(String url) {
        return given().contentType(ContentType.JSON).when()
                .get(url).getStatusCode();
    }

    public static JsonPath getBodyAsJsonPath(String url) {
        return given().auth().none().contentType(ContentType.JSON).when()
                .get(url).body().jsonPath();
    }

    public static Response getResponseUsingPathParams(String key, String value, String url) {
        return given().contentType(ContentType.JSON).with()
                .pathParams(key, value)
                .when().get(String.format("%s/{%s}", url, key));
    }

    public static Response getResponseUsingQueryParams(String key, String value, String url) {
        return given().contentType(ContentType.JSON)
                .queryParam(key, value)
                .when().get(url);
        // url will become - http://url?page=2
    }
}
