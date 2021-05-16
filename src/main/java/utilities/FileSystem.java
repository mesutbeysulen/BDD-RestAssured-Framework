package utilities;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import java.io.File;
import java.util.Objects;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class FileSystem {

    public static void deleteOldReports() {
        File reportDir = new File("allure-results");
        if (reportDir.exists()) {
            for (File f : Objects.requireNonNull(reportDir.listFiles())) {
                f.delete();
            }
        }
    }
}
