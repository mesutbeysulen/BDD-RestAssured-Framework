package steps.files;

import constants.GlobalVars;
import httpmethods.Get;
import io.cucumber.java.en.Given;
import threadsafety.ApiResponse;
import threadsafety.StatusCode;
import utilities.Common;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class UploadDownloadSteps {

    @Given("^User performs file upload to '(.*)'$")
    public void fileUpload(String url) {
        ApiResponse.setResponse(Common.upload(url, "src/test/resources/config.properties", "multipart/form-data"));
        StatusCode.setStatusCode(ApiResponse.getResponse().getStatusCode());
    }

    @Given("^User save response to file for '(.*)'$")
    public void fileDownload(String apiPath) throws IOException {
        ApiResponse.setResponse(Get.getResponse(GlobalVars.getUrl().concat(apiPath)));
        StatusCode.setStatusCode(ApiResponse.getResponse().getStatusCode());
        byte[] bytes = ApiResponse.getResponse().asByteArray();
        File file = new File("src/test/resources/FileDownloadTest/test.txt");
        Files.write(file.toPath(), bytes);
    }
}
