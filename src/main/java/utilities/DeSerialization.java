package utilities;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import constants.GlobalVars;
import io.restassured.RestAssured;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import pojo.Pojo1;
import settergetter.ThreadSafety;

import java.lang.reflect.Type;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class DeSerialization {

    // To convert POJO to JSON
    private static final ObjectMapper MAPPER = new ObjectMapper();

    public static void deSerExample(String apiPath) throws JsonProcessingException {
        String json = "{\"albumId\":7777,\"id\":7777,\"title\":\"x\",\"url\":\"x\",\"thumbnailUrl\":\"x\"}";
        Pojo1 po = MAPPER.readValue(json, Pojo1.class);

        String url = GlobalVars.getUrl2().concat(apiPath);
        Pojo1 pojo1 = RestAssured.given().get(url).as(Pojo1.class);

        ThreadSafety.setResponse(RestAssured.given().get(url));
        ThreadSafety.setStatusCode(ThreadSafety.getResponse().getStatusCode());

        // Get all the result in array
        String url2 = GlobalVars.getUrl2().concat("/photos");
        Pojo1 allData[] = RestAssured.given().get(url2)
                .as(Pojo1[].class);

        // Get all the result in List
        Type type = new TypeReference<List<Pojo1>>() {}.getType();
        List<Pojo1> data = RestAssured.get(url2).as(type);

    }
}
