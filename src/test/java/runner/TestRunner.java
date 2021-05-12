package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

@CucumberOptions(
        features = "src/test/java/features",
        glue={"steps"},
        monochrome = true
        // tags = "@Test1",
        // tags = "@Test1, @Test3",
        // To run specific tag using mvn -
        //          mvn clean test -Dcucumber.filter.tags="@TAGNAME"
        //          mvn clean test -Dcucumber.filter.tags="@TAGNAME and @TAGENAME2"
        //          mvn clean test -Dcucumber.filter.tags="@TAGNAME or @TAGNAME2"
)
public class TestRunner extends AbstractTestNGCucumberTests {

    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}
