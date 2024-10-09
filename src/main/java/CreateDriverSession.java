import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class CreateDriverSession {
    public static void main(String[] args) throws MalformedURLException {
        UiAutomator2Options options = new UiAutomator2Options();
        options.setDeviceName("emulator-5554")
                .setPlatformVersion("11.0")
                .setAppPackage("io.appium.android.apis")
                .setAppActivity(".ApiDemos")
                .setAvdLaunchTimeout(Duration.ofSeconds(60));
        AppiumDriver driver = new AndroidDriver(new URL("http://127.0.0.1:4723/"), options);
        driver.findElement(AppiumBy.xpath("//android.widget.TextView[@content-desc=\"Accessibility\"]")).click();
        driver.findElement(AppiumBy.xpath("//android.widget.TextView[@content-desc=\"Accessibility Node Querying\"]")).click();
        driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Conquer World']/following-sibling::android.widget.CheckBox")).click();
    }
}

