package constants;

import reader.PropertyReader;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class GlobalVars {

    @Getter static String url = PropertyReader.getValue("URL1", "src/test/resources/config.properties");
    @Getter static String url2 = PropertyReader.getValue("URL2", "src/test/resources/config.properties");
}
