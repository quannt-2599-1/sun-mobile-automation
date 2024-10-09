package testcases;

import actions.API_Demo_PageObject;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class TestCase_Demo {

    API_Demo_PageObject apiDemoPage;
    public static void main(String[] args) throws MalformedURLException {
//        AppiumDriver driver;
        UiAutomator2Options options = new UiAutomator2Options();
        options.setDeviceName("emulator-5554")
                .setPlatformVersion("11.0")
                .setAppPackage("io.appium.android.apis")
                .setAppActivity(".ApiDemos")
                .setAvdLaunchTimeout(Duration.ofSeconds(60));
        AppiumDriver driver = new AndroidDriver(new URL("http://127.0.0.1:4723/"), options);
        API_Demo_PageObject apiDemoPage = new API_Demo_PageObject(driver);
        apiDemoPage.clickToAccessibilityButton(driver);
    }
}
