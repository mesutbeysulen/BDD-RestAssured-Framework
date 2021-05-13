package httpmethods;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import lombok.NoArgsConstructor;

import static io.restassured.RestAssured.given;

@NoArgsConstructor
public final class Put {

    public static Response put(String body, String apiPath) {
        return given().contentType(ContentType.JSON).when()
                .body(body)
                .put(apiPath);
    }
}
