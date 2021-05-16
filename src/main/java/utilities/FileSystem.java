package utilities;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.io.FileUtils;
import java.io.File;
import java.io.IOException;
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

    public static void copy(String from, String to) {
        File fromPath = new File(from);
        File toPath = new File(to);
        try {
            FileUtils.copyFile(fromPath, toPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
