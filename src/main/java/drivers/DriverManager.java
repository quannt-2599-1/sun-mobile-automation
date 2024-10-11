package drivers;

import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;

import constant.Constant;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import server.ServerManager;
import utils.TestUtils;

public class DriverManager {
    private static final ThreadLocal<AppiumDriver> driver = new ThreadLocal<>();

    public AppiumDriver getDriver() {
        return driver.get();
    }

    public void setDriver(AppiumDriver driver) {
        DriverManager.driver.set(driver);
    }

    public Properties getProps() throws IOException {
        Properties propsImplicitlyWait;
        propsImplicitlyWait = PropertyManager.getProps(Constant.PROP_IMPLICITLY_WAIT_DATA_FILE);
        return propsImplicitlyWait;
    }

    public void initializeDriver(String emulator) throws Exception {
        GlobalParams params = new GlobalParams();

        try {
            TestUtils.log().info("[SETUP] Initializing Appium driver");

            Properties propBrowserStack = PropertyManager.getProps(Constant.PROP_CONFIG_FILE);
            String testDeviceEnv = propBrowserStack.getProperty("testDevice", "local");

            // Initialize remoteUrl
            URL remoteUrl;
            if (testDeviceEnv.equals("browserstack")) {
                remoteUrl = new URL("http://hub.browserstack.com/wd/hub");
            } else {
                remoteUrl = new ServerManager().getServer().getUrl();
            }

            // Innitialize driver object
            AppiumDriver driver = null;
            if (params.getPlatformName().equals("Android")) {
                driver = new AndroidDriver(remoteUrl, new UiAutomator2OptionsManage().getCapabilities(emulator));
            } else {
                driver = new IOSDriver(new ServerManager().getServer().getUrl(), new UiAutomator2OptionsManage().getCapabilities(emulator));
            }

            if (driver == null) {
                throw new Exception("driver is null. ABORT!!!");
            }

            TestUtils.log().info("[SETUP] Driver is initialized");
            DriverManager.driver.set(driver);

            PageFactory.initElements(new AppiumFieldDecorator(driver), this);
            Properties propsImplicitlyWait = new PropertyManager().getProps(Constant.PROP_IMPLICITLY_WAIT_DATA_FILE);
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(
                    Integer.parseInt(propsImplicitlyWait.getProperty("valuePropsImplicitlyWait"))
            ));

        } catch (IOException e) {
            TestUtils.log().fatal("[SETUP] Driver initialization failure. ABORT !!!!" + e.toString());
            throw e;
        }

    }

}
