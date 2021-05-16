package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import utilities.FileSystem;

@CucumberOptions(
        features = "src/test/java/features",
        glue={"steps"},
        monochrome = true
        // tags = "@Test1",
        // tags = "@Test1, @Test3",
        // tags = "not @Test1",
        // tags = "@wip and not @slow",
        // tags = "@smoke and @fast",
        // tags = "@gui or @database",
        // tags = "(@smoke or @ui) and (not @slow)",
        // To run specific tag using mvn -
        //          mvn clean test -Dcucumber.filter.tags="@TAGNAME"
        //          mvn clean test -Dcucumber.filter.tags="@TAGNAME and @TAGENAME2"
        //          mvn clean test -Dcucumber.filter.tags="@TAGNAME or @TAGNAME2"
)
public class TestRunner extends AbstractTestNGCucumberTests {

    @BeforeSuite
    public void beforeSuite() {
        FileSystem.deleteOldReports();
    }

    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}
