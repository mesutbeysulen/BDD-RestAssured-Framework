package servicehelpers;

import io.restassured.response.Response;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import io.restassured.http.ContentType;

import java.io.File;
import java.util.HashMap;

import static io.restassured.RestAssured.given;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class Post {

    public static Response getResponseUsingJsonStringBody(String bodyData, String url) {
        return given().contentType(ContentType.JSON).when().body(bodyData).post(url);
    }

    public static Response getResponseUsingHashMapBody(HashMap<String, String> bodyData, String url) {
        return given().contentType(ContentType.JSON).when().body(bodyData).post(url);
    }

    public static Response getResponseUsingJsonFile(String filePath, String url) {
        return given().contentType(ContentType.JSON).when().body(new File(filePath)).post(url);
    }
}
