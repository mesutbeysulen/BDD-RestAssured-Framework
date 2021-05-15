package constants;

import filereader.PropertyReader;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class GlobalVars {

    @Getter static String url = PropertyReader.getValue("URL", "src/test/resources/config.properties");
    @Getter static String url2 = PropertyReader.getValue("URL2", "src/test/resources/config.properties");
}
