package steps;

import io.cucumber.core.backend.TestCaseState;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeStep;
import io.cucumber.java.Scenario;
import io.cucumber.plugin.event.PickleStepTestStep;
import io.cucumber.plugin.event.TestCase;
import io.qameta.allure.Allure;
import io.qameta.allure.AllureLifecycle;
import org.testng.Reporter;
import settergetter.ThreadSafety;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class Hooks {

    private int currentStepDefIndex = 0;
    private AllureLifecycle lifecycle;

    @Before
    public void before(Scenario scenario) {
        Object[] paramNames = Reporter.getCurrentTestResult().getParameters();
        String scenarioName = paramNames[0].toString().replaceAll("\"", "");
        String featureName = paramNames[1].toString().replaceAll("Optional|\\[|\\]|\"", "");

        lifecycle = Allure.getLifecycle();
        lifecycle.updateTestCase(testResult -> testResult.setFullName(featureName));
        lifecycle.updateTestCase(testResult -> testResult.setName(scenarioName));

        String featureFilePath = scenario.getUri().toString();
        Collection<String> tags = scenario.getSourceTagNames();
        if (!tags.isEmpty())
            Allure.parameter("Tags", "\"" + tags.toString().replaceAll("\\[|\\]|\"", "") + "\"");
        Allure.parameter("Feature File", "\"" + featureFilePath.substring(featureFilePath.lastIndexOf("/")+1) + "\"");
        Allure.feature(featureName);
        Allure.description(scenarioName);
    }

    @BeforeStep
    public void beforeStep(Scenario scenario) throws Exception {
        Field f = scenario.getClass().getDeclaredField("delegate");
        f.setAccessible(true);
        TestCaseState tcs = (TestCaseState) f.get(scenario);

        Field f2 = tcs.getClass().getDeclaredField("testCase");
        f2.setAccessible(true);
        TestCase r = (TestCase) f2.get(tcs);

        List<PickleStepTestStep> stepDef = r.getTestSteps()
                .stream()
                .filter(x -> x instanceof PickleStepTestStep)
                .map(x -> (PickleStepTestStep) x)
                .collect(Collectors.toList());

        PickleStepTestStep currentStepDef = stepDef.get(currentStepDefIndex);

        String currentKeyword = currentStepDef.getStep().getKeyword().trim();
        String currentStepText = currentStepDef.getStep().getText().trim();
        currentStepDefIndex++;

        Allure.step(currentKeyword + ": " + currentStepText);
    }

    @After
    public void after() {
        lifecycle = null;
        currentStepDefIndex = 0;
        ThreadSafety.unloadStatusCode();
        ThreadSafety.unloadRes();
    }
}
