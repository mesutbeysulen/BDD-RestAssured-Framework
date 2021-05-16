package steps;

import constants.GlobalVars;
import servicehelpers.Get;
import io.cucumber.java.en.Given;
import settergetter.ThreadSafety;
import utilities.Common;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class UploadDownloadFileSteps {

    @Given("^User performs file upload to '(.*)'$")
    public void fileUpload(String url) {
        ThreadSafety.setResponse(Common.upload(url, "src/test/resources/config.properties", "multipart/form-data"));
        ThreadSafety.setStatusCode(ThreadSafety.getResponse().getStatusCode());
    }

    @Given("^User save response to file for '(.*)'$")
    public void fileDownload(String endpoint) throws IOException {
        ThreadSafety.setResponse(Get.getResponse(GlobalVars.getUrl().concat(endpoint)));
        ThreadSafety.setStatusCode(ThreadSafety.getResponse().getStatusCode());
        byte[] bytes = ThreadSafety.getResponse().asByteArray();
        File file = new File("src/test/resources/FileDownloadTest/test.txt");
        Files.write(file.toPath(), bytes);
    }
}
