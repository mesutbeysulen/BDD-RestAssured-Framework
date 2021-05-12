package steps;

import io.cucumber.java.After;
import threadsafety.ApiResponse;
import threadsafety.StatusCode;

public class Hooks {

    @After
    public void tear() {
        StatusCode.unloadStatusCode();
        ApiResponse.unloadRes();
    }
}
