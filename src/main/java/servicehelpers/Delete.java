package servicehelpers;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import lombok.NoArgsConstructor;

import static io.restassured.RestAssured.given;

@NoArgsConstructor
public final class Delete {

    public static Response delete(String apiPath) {
        return given().contentType(ContentType.JSON).when()
                .delete(apiPath);
    }
}
