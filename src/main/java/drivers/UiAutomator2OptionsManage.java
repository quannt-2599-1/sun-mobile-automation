package drivers;

import constant.Constant;
import io.appium.java_client.android.options.UiAutomator2Options;
import utils.BrowserStackUtils;
import utils.TestUtils;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

public class UiAutomator2OptionsManage {
    private static final String LOCAL = "local";
    private static final String BROWSERSTACK = "browserstack";
    private static final String ANDROID = "Android";
    private static final String IOS = "iOS";

    public UiAutomator2Options getCapabilities(String emulator) throws IOException {
        GlobalParams params = new GlobalParams();
        Properties props = PropertyManager.getProps(Constant.PROP_CONFIG_FILE);

        try {
            TestUtils.log().info("Getting capabilities by UiAutomator2Options");
            UiAutomator2Options options = new UiAutomator2Options();
            setCommonCapabilities(options, params, props);
            String testDeviceEnv = getTestDeviceEnvironment(params, props);

            if (BROWSERSTACK.equalsIgnoreCase(testDeviceEnv)) {
                setBrowserStackCapabilities(options, props);
                return options;
            }

            setPlatformSpecificCapabilities(options, params, props, emulator);
            return options;

        } catch (Exception e) {
            TestUtils.log().fatal("Failed to load capabilities. ABORT!!", e);
            throw e;
        }
    }

    private void setCommonCapabilities(UiAutomator2Options options, GlobalParams params, Properties props) {
        String deviceName = params.getDeviceName();
        if (deviceName == null || "deviceName".equalsIgnoreCase(deviceName)) {
            deviceName = props.getProperty("androidAvd");
        }
        System.out.println("Device name:" + deviceName);
        options.setCapability("deviceName", deviceName);
        options.setCapability("platformName", params.getPlatformName());
    }

    private String getTestDeviceEnvironment(GlobalParams params, Properties props) {
        String testDeviceEnv = params.getTestDeviceEnvironment();
        if (testDeviceEnv == null) {
            testDeviceEnv = props.getProperty("testDevice", LOCAL);
        }

        if (!LOCAL.equalsIgnoreCase(testDeviceEnv) && !BROWSERSTACK.equalsIgnoreCase(testDeviceEnv)) {
            testDeviceEnv = LOCAL;
        }

        params.setTestDeviceEnvironment(testDeviceEnv);

        return testDeviceEnv;
    }

    private void setBrowserStackCapabilities(UiAutomator2Options options, Properties props) throws IOException {
        Properties browserStackProps = PropertyManager.getProps(Constant.PROP_BROWSERSTACK_FILE);
        HashMap<String, Object> browserstackOptions = BrowserStackUtils.getBrowserstackOptions(browserStackProps);
        options.setCapability("bstack:options", browserstackOptions);
        options.setCapability("app", props.getProperty("androidAppLocation"));
    }

    private void setPlatformSpecificCapabilities(UiAutomator2Options options, GlobalParams params, Properties props, String emulator) {
        String platformName = params.getPlatformName();
        switch (platformName) {
            case ANDROID:
                setAndroidCapabilities(options, params, props, emulator);
                break;
            case IOS:
                setIOSCapabilities(options, params, props);
                break;
            default:
                throw new IllegalArgumentException("Unsupported platform: " + platformName);
        }
    }

    private void setAndroidCapabilities(UiAutomator2Options options, GlobalParams params, Properties props, String emulator) {
        if (params.getUDID() != null) {
            options.setCapability("udid", params.getUDID());
        }

        options.setCapability("automationName", props.getProperty("androidAutomationName"));
        options.setCapability("app", loadPathApp(props.getProperty("androidAppLocation")));
        options.setCapability("appPackage", props.getProperty("androidAppPackage"));
        options.setCapability("appActivity", props.getProperty("androidAppActivity"));

        if (Boolean.parseBoolean(emulator)) {
            options.setCapability("avd", props.getProperty("androidAvd"));
            options.setCapability("avdLaunchTimeout", 900000);
        }
    }

    private void setIOSCapabilities(UiAutomator2Options options, GlobalParams params, Properties props) {
        options.setCapability("udid", params.getUDID());
        options.setCapability("deviceName", params.getDeviceName());
        options.setCapability("platformName", params.getPlatformName());
        options.setCapability("automationName", props.getProperty("iOSAutomationName"));
        options.setCapability("bundleId", props.getProperty("iOSBundleId"));
        options.setCapability("wdaLocalPort", params.getWdaLocalPort());
        options.setCapability("webkitDebugProxyPort", params.getWebkitDebugProxyPort());
        options.setCapability("app", loadPathApp(props.getProperty("iOSAppLocation")));
    }

    private String loadPathApp(String namePath) {
        return System.getProperty("user.dir") + File.separator + "src" + File.separator + "test" + File.separator + "resources" + File.separator + "app" + File.separator + namePath;
    }
}
