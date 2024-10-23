package runners.smoketest.app;

import io.cucumber.testng.CucumberOptions;
import runners.smoketest.RunnerBase;

@CucumberOptions(
        plugin = {"pretty"
                , "html:target/App/Login/Successful/cucumber/report.html"
                , "json:target/cucumber-reports/CucumberReport.json"
                , "summary"
        }
        , features = {"src/test/resources"}
        , glue = {"stepsdef/smoketest/app"}
        , dryRun = false
        , monochrome = true
        , tags = "@cart-feature"
)
public class CartScreenRunnerTest extends RunnerBase {
}
