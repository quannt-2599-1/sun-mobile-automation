package testcases;

import actions.API_Demo_PageObject;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class TestCase_Demo {
    AppiumDriver driver;
    API_Demo_PageObject apiDemoPage;
    private int endX = 655;
    private int endY = 531;

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
    public void TC_02_clickToAccessibilityButton(){
        API_Demo_PageObject apiDemoPage = new API_Demo_PageObject(this.driver);
        apiDemoPage.clickToAccessibilityButton(driver);
    }

    public void TC_03_longClickGesture(){
        API_Demo_PageObject apiDemoPage = new API_Demo_PageObject(this.driver);
        apiDemoPage.clickToViewsButton(driver);
        apiDemoPage.clickToDragAndDropButton(driver);
        apiDemoPage.longClickToDragDot(driver);
    }

    public void TC_04_dragDot(){
        API_Demo_PageObject apiDemoPage = new API_Demo_PageObject(this.driver);
        apiDemoPage.clickToViewsButton(driver);
        apiDemoPage.clickToDragAndDropButton(driver);
        apiDemoPage.dragDotToNewLocation(driver,endX,endY);
    }
    @Test
    public void TC_05_swipeListView(){
        API_Demo_PageObject apiDemoPage = new API_Demo_PageObject(this.driver);
        apiDemoPage.clickToViewsButton(driver);
        apiDemoPage.swipeUpListView(driver);
        apiDemoPage.sleepThread(3);
        apiDemoPage.swipeDownListView(driver);
        apiDemoPage.clickToGalleryButton(driver);
        apiDemoPage.clickToPhotoButton(driver);
        apiDemoPage.swipeLeftPhotoView(driver);

    }



    public void afterClass(){
    driver.quit();
    }

}
