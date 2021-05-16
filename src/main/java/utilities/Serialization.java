package utilities;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import constants.GlobalVars;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import pojo.Pojo1;

public class Serialization {

    // To convert POJO to JSON
    private static final ObjectMapper MAPPER = new ObjectMapper();

    public static Response serExample(String apiPath) throws JsonProcessingException {
        Pojo1 pojo1 = new Pojo1();
        pojo1.setAlbumId(99999);
        pojo1.setId(99999);
        pojo1.setTitle("Title 99999");
        pojo1.setUrl("http://example99999.com");
        pojo1.setThumbnailUrl("Thumbnail 99999");

        String url = GlobalVars.getUrl2().concat(apiPath);

        String json = MAPPER.writeValueAsString(pojo1);
        byte[] data = MAPPER.writeValueAsBytes(pojo1);

        return given().contentType(ContentType.JSON).body(json)
                .post(url).andReturn();
    }
}
