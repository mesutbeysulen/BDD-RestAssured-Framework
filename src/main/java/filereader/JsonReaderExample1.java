package filereader;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;

import static io.restassured.RestAssured.given;

public class JsonReaderExample1 {
    public static void main(String[] args) throws IOException {

        // To convert JSON response to byte array and use
//        Response res = given().contentType(ContentType.JSON).when()
//                .get("https://reqres.in/api/users");
//        byte[] jsonData = res.asByteArray();

        byte[] jsonData = Files.readAllBytes(Paths.get("src/test/resources/Files/ComplexJSON1.json"));
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode json = objectMapper.readTree(jsonData);

        // Sample Outputs
        JsonNode usingGet = json.path("person").get("email");
        JsonNode findByKey = json.findValue("emergencyContacts");

        JsonNode nestedFindByKey1 = json.findValue("emergencyContacts").findPath("name");
        JsonNode nestedFindByKey2 = json.findValue("emergencyContacts").findValue("name");

        JsonNode usingIndex1 = json.path("person").get("email").get(0);

        System.out.println(usingGet + "        " + findByKey);
        System.out.println(nestedFindByKey1 + "        " + nestedFindByKey2);
        System.out.println(usingIndex1 + "        ");

        JsonNode nestedSubValue = json.findValue("emergencyContacts");
        Iterator<JsonNode> elements = nestedSubValue.elements();
        while(elements.hasNext()){
            JsonNode value = elements.next();
            System.out.println(value);
        }
    }
}
