package steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.qameta.allure.Allure;
import io.qameta.allure.AllureLifecycle;
import org.testng.Reporter;
import threadsafety.ApiResponse;
import threadsafety.StatusCode;

public class Hooks {

    @Before
    public void before() {
        Object[] paramNames = Reporter.getCurrentTestResult().getParameters();
        String scenarioName = paramNames[0].toString().replaceAll("\"", "");
        String featureName = paramNames[1].toString().replaceAll("Optional|\\[|\\]|\"", "");

        AllureLifecycle lifecycle = Allure.getLifecycle();
        lifecycle.updateTestCase(testResult -> testResult.setFullName(featureName));
        lifecycle.updateTestCase(testResult -> testResult.setName(scenarioName));
        lifecycle.updateTestCase(testResult -> testResult.setDescription(scenarioName));

    }

    @After
    public void after() {
        StatusCode.unloadStatusCode();
        ApiResponse.unloadRes();
    }
}
