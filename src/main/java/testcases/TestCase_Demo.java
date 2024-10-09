package testcases;

import actions.API_Demo_PageObject;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class TestCase_Demo {
    AppiumDriver driver;
    API_Demo_PageObject apiDemoPage;
//public TestCase_Demo(){
//
//}
    @BeforeClass
    public void beforeClass() throws MalformedURLException{
        UiAutomator2Options options = new UiAutomator2Options();
        options.setDeviceName("emulator-5554")
                .setPlatformVersion("11.0")
                .setAppPackage("io.appium.android.apis")
                .setAppActivity(".ApiDemos")
                .setAvdLaunchTimeout(Duration.ofSeconds(60));
        this.driver = new AndroidDriver(new URL("http://127.0.0.1:4723/"), options);
    }
    @Test
    public void TC_01_getTextOfAccessibilityButton(){
        API_Demo_PageObject apiDemoPage = new API_Demo_PageObject(this.driver);
        System.out.println(apiDemoPage.getTextOfAccessibilityButton(driver));

    }
    @Test
    public void TC_02_clickToAccessibilityButton(){
        API_Demo_PageObject apiDemoPage = new API_Demo_PageObject(this.driver);
        apiDemoPage.clickToAccessibilityButton(driver);
    }
    @AfterClass
    public void afterClass(){
    driver.quit();
    }

}
