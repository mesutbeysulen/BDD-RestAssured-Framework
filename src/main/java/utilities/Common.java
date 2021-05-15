package utilities;

import static io.restassured.RestAssured.*;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.File;

public class Common {

     static RequestSpecification setSpec(String url, String apiPath) {
        /*
         This method will set the url and api path and can be reused
         Use: given().spec(getResponseUsingParams(url, apiPath)).when().get();
         No need to provide url in get()
         */
        RequestSpecBuilder rsb = new RequestSpecBuilder();
        rsb.setBaseUri(url);
        rsb.setBasePath(apiPath);
        RequestSpecification spec = rsb.build();
        return spec;
    }

    public static Response getResponse(String url, String apiPath) {
         return given().spec(setSpec(url, apiPath)).when().get();
    }

    public static Response getResponseUsingQueryParams(String url, String apiPath, String key, String val) {
        return given().spec(setSpec(url, apiPath)).queryParam(key, val).when().get();
    }

    public static Response getResponseUsingPathParams(String url, String apiPath, String key, String val) {
        return given().spec(setSpec(url, apiPath)).pathParams(key, val).when().get();
    }

    public static int getStatusCode(String url, String apiPath) {
        return given().spec(setSpec(url, apiPath)).when().get().getStatusCode();
    }

    public static JsonPath getJsonPath(String url, String apiPath) {
        return given().spec(setSpec(url, apiPath)).when().get().jsonPath();
    }

    public static Response upload(String apiPath, String filePath, String mimeType) {
         File file = new File(filePath);
        return given().spec(setSpec(apiPath, apiPath)).multiPart("file", file, mimeType)
                .post(apiPath);
    }
}
