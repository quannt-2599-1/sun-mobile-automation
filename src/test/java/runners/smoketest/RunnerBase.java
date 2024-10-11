package runners.smoketest;

import drivers.DriverManager;
import drivers.GlobalParams;
import io.appium.java_client.AppiumDriver;
import io.cucumber.testng.FeatureWrapper;
import io.cucumber.testng.PickleWrapper;
import io.cucumber.testng.TestNGCucumberRunner;
import org.apache.logging.log4j.ThreadContext;
import org.testng.annotations.*;
import server.ServerManager;

public class RunnerBase {
    private static final ThreadLocal<TestNGCucumberRunner> testNGCucumberRunner = new ThreadLocal<>();

    public TestNGCucumberRunner getRunner() {
        if (testNGCucumberRunner.get() == null) {
            testNGCucumberRunner.set(new TestNGCucumberRunner(this.getClass()));
        }

        return testNGCucumberRunner.get();
    }

    private static void setRunner(TestNGCucumberRunner testNGCucumberRunner1) {
        testNGCucumberRunner.set(testNGCucumberRunner1);
    }

    @Parameters({"emulator", "platformName", "udid", "deviceName", "systemPort", "chromeDriverPort", "wdaLocalPort", "webkitDebugProxyPort"})
    @BeforeClass(alwaysRun = true)
    public void beforeTest(@Optional("androidOnly") String emulator, @Optional("platformName") String platformName, @Optional("udid") String udid, @Optional("deviceName") String deviceName, @Optional("androidOnly") String systemPort, @Optional("androidOnly") String chromeDriverPort, @Optional("iOSOnly") String wdaLocalPort, @Optional("iOSOnly") String webkitDebugProxyPort) throws Exception {
        ThreadContext.put("ROUTINGKEY", platformName + "_" + deviceName);

        GlobalParams params = new GlobalParams();
        params.setPlatformName(platformName);
        params.setUDID(udid);
        params.setDeviceName(deviceName);

        switch (platformName) {
            case "Android":
                params.setSystemPort(systemPort);
                params.setChromeDriverPort(chromeDriverPort);
                break;
            case "iOS":
                params.setWdaLocalPort(wdaLocalPort);
                params.setWebkitDebugProxyPort(webkitDebugProxyPort);
                break;
        }

        new ServerManager().startServer();

        setRunner(new TestNGCucumberRunner(this.getClass()));
    }

    @Test(groups = "cucumber", description = "Runs Cucumber Scenarios", dataProvider = "scenarios")
    public void scenario(PickleWrapper pickle, FeatureWrapper cucumberFeature) throws Throwable {
        getRunner().runScenario(pickle.getPickle());
    }

    @DataProvider
    public Object[][] scenarios() {
        return getRunner().provideScenarios();
    }

    @AfterClass(alwaysRun = true)
    public void tearDownClass() {
        if (getRunner() != null) {
            getRunner().finish();
        }

        AppiumDriver driver = new DriverManager().getDriver();
        if (driver != null) {
            driver.quit();
        }
    }

    @BeforeMethod(alwaysRun = true)
    public void launchApp() throws Exception {
        new DriverManager().initializeDriver("true");
    }

    @AfterMethod(alwaysRun = true)
    public void closeApp() {
        DriverManager driverManager = new DriverManager();
        if (driverManager.getDriver() != null) {
            driverManager.getDriver().quit();
            driverManager.setDriver(null);
        }
    }

}
