package filereader;

import lombok.NoArgsConstructor;

import java.io.FileReader;
import java.util.Properties;

@NoArgsConstructor
public class PropertyReader {

    public static String getValue(String key, String filePath) {
        Properties properties = new Properties();
        try {
            FileReader fileReader = new FileReader(filePath);
            properties.load(fileReader);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return properties.getProperty(key);
    }
}
