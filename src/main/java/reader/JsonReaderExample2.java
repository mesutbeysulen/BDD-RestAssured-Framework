package reader;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class JsonReaderExample2 {
    public static void main(String[] args) throws IOException {

        byte[] jsonData = Files.readAllBytes(Paths.get("src/test/resources/Files/ComplexJSON2.json"));
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode json = objectMapper.readTree(jsonData);

        // Sample Outputs
        JsonNode value = json.path("value");
        JsonNode nodes = json.path("value").findValue("nodes");
        JsonNode id = json.findValue("id");
        List<JsonNode> ids = json.findValues("id");

        System.out.println(value);
        System.out.println(nodes);
        System.out.println(id);
        System.out.println(ids);

    }
}
