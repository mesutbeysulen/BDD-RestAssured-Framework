package httpmethods;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import lombok.NoArgsConstructor;

import static io.restassured.RestAssured.given;

@NoArgsConstructor
public final class Patch {

    public static Response patch(String body, String apiPath) {
        return given().contentType(ContentType.JSON).when()
                .body(body)
                .patch(apiPath);
    }
}
