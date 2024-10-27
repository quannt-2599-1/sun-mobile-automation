package common;

import com.google.common.collect.ImmutableMap;
import drivers.DriverManager;
import drivers.GlobalParams;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.InteractsWithApps;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.TestUtils;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class BaseTest {
    private AppiumDriver driver;

    public BaseTest() {
        this.driver = new DriverManager().getDriver();
        PageFactory.initElements(new AppiumFieldDecorator(this.driver), this);
    }

    public AppiumDriver getDriver() {
        return this.driver;
    }

    public void closeApp() {
        if (new GlobalParams().getPlatformName().equals("Android")) {
            ((InteractsWithApps) driver).terminateApp(driver.getCapabilities().getCapability("appPackage").toString());
            return;
        }

        ((InteractsWithApps) driver).terminateApp(driver.getCapabilities().getCapability("bundleId").toString());

    }

    public void launchThenOpenApp(String emulator) throws Exception {
        launchApp();
        new DriverManager().initializeDriver(emulator);
    }

    public void launchApp() {
        if (new GlobalParams().getPlatformName().equals("Android")) {
            ((InteractsWithApps) driver).activateApp(driver.getCapabilities().getCapability("appPackage").toString());
            return;
        }

        ((InteractsWithApps) driver).activateApp(driver.getCapabilities().getCapability("bundleId").toString());
    }

    public void switchToWebView(String webViewContext) {
        if (driver instanceof AndroidDriver) {
            AndroidDriver androidDriver = (AndroidDriver) driver;
            Set<String> contextHandles = androidDriver.getContextHandles();
            for (String contextHandle : contextHandles) {
                if (contextHandle.contains("WEBVIEW")) {
                    androidDriver.context(webViewContext);
                    return;
                }
            }
        }
        if (driver instanceof IOSDriver) {
            IOSDriver iosDriver = (IOSDriver) driver;
            Set<String> contextHandles = iosDriver.getContextHandles();
            for (String contextHandle : contextHandles) {
                if (contextHandle.contains("WEBVIEW")) {
                    iosDriver.context(webViewContext);
                    return;
                }
            }
        }
    }

    public void switchToNative() {
        if (driver instanceof AndroidDriver) {
            ((AndroidDriver) driver).context("NATIVE_APP");
            return;
        }
        if (driver instanceof IOSDriver) {
            ((IOSDriver) driver).context("NATIVE_APP");
        }
    }

    // actions with web elements
    public void waitForVisibility(WebElement e) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TestUtils.WAIT));
        wait.until(ExpectedConditions.visibilityOf(e));
    }

    public void waitForVisibility(By e) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TestUtils.WAIT));
        wait.until(ExpectedConditions.visibilityOfElementLocated(e));
    }
    public void waitForClickable(WebElement e) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TestUtils.WAIT));
        wait.until(ExpectedConditions.elementToBeClickable(e));
    }

    public void clear(WebElement e) {
        waitForVisibility(e);
        e.clear();
    }

    public void click(WebElement e) {
        waitForClickable(e);
        e.click();
    }

    public void hideKeyboard() {
        if (driver instanceof AndroidDriver) {
            ((AndroidDriver) driver).hideKeyboard();
            return;
        }
        if (driver instanceof IOSDriver) {
            ((IOSDriver) driver).hideKeyboard();
            return;
        }
        TestUtils.log().error("Driver does not support keyboard hiding method");
    }

    public void click(WebElement e, String msg) {
        waitForVisibility(e);
        TestUtils.log().info(msg);
        e.click();
    }

    public void click(By e, String msg) {
        waitForVisibility(e);
        TestUtils.log().info(msg);
        driver.findElement(e).click();
    }

    public void sendKeys(WebElement e, String txt) {
        waitForVisibility(e);
        e.sendKeys(txt);
    }

    public void sendKeys(WebElement e, String txt, String msg) {
        waitForVisibility(e);
        TestUtils.log().info(msg);
        e.sendKeys(txt);
    }

    public String getAttribute(WebElement e, String attribute) {
        waitForVisibility(e);
        return e.getAttribute(attribute);
    }

    public String getAttribute(By e, String attribute) {
        waitForVisibility(e);
        return driver.findElement(e).getAttribute(attribute);
    }
    public void scrollDownToElement(WebElement e){
        waitForVisibility(e);
        boolean canScrollMore = (Boolean) driver.executeScript("mobile: scrollGesture", ImmutableMap.of(
                "elementId", ((RemoteWebElement) e).getId(),
                "direction", "down",
                "percent", 1.0
        ));
    }

    public String getText(WebElement e, String msg) {
        String txt;
        switch (new GlobalParams().getPlatformName()) {
            case "Android":
                txt = getAttribute(e, "text");
                break;
            case "iOS":
                txt = getAttribute(e, "label");
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + new GlobalParams().getPlatformName());
        }
        TestUtils.log().info(msg + txt);
        return txt;
    }

    public String getText(By e, String msg) {
        String txt;
        switch (new GlobalParams().getPlatformName()) {
            case "Android":
                txt = getAttribute(e, "text");
                break;
            case "iOS":
                txt = getAttribute(e, "label");
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + new GlobalParams().getPlatformName());
        }
        TestUtils.log().info(msg + txt);
        return txt;
    }

    public boolean find(final WebElement element, int timeout) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
            return wait.until((ExpectedCondition<Boolean>) driver -> element.isDisplayed());
        } catch (TimeoutException e) {
            return false;
        }
    }

    public boolean find(final By element, int timeout) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
            return wait.until((ExpectedCondition<Boolean>) driver -> driver.findElement(element).isDisplayed());
        } catch (TimeoutException e) {
            return false;
        }
    }

    public boolean checkElementExitsImmediately(By by, final WebElement element) {
        return driver.findElements(by).contains(element);
    }

    // Gesture mobile
    public WebElement andScrollToElementUsingUiScrollable(String childLocAttr, String childLocValue) {
        return driver.findElement(
                AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()" + ".scrollable(true)).scrollIntoView("
                        + "new UiSelector()." + childLocAttr + "(\"" + childLocValue + "\"));"));
    }

    public WebElement andScrollDownToElementWithUiScrollable(String childLocAttr, String childLocValue) {
        return driver.findElement(
                AppiumBy.androidUIAutomator(
                        "new UiScrollable(new UiSelector()" + ".scrollable(true))" +
                                ".setAsVerticalList()" +
                                ".scrollForward()" +  // add scrollForward() to AlwaysScrollDown
                                ".scrollIntoView(new UiSelector()." + childLocAttr + "(\"" + childLocValue + "\"));"

                )
        );
    }
    public void scrollDownGesture(){

        boolean canScrollMore = (Boolean) driver.executeScript("mobile: scrollGesture", ImmutableMap.of(
                "left",100,"top",100,"width",600,"height",600,
              //  "elementId", ((RemoteWebElement) element).getId(),
                "direction", "down",
                "percent", 1.0
        ));
    }

    public WebElement iOSScrollToElementUsingMobileScroll(WebElement e) {
        RemoteWebElement element = ((RemoteWebElement) e);
        String elementID = element.getId();
        HashMap<String, String> scrollObject = new HashMap<String, String>();
        scrollObject.put("element", elementID);
        scrollObject.put("toVisible", "sdfnjksdnfkld");
        driver.executeScript("mobile:scroll", scrollObject);
        return e;
    }

    public By iOSScrollToElementUsingMobileScrollParent(WebElement parentE, String predicateString) {
        RemoteWebElement parent = (RemoteWebElement) parentE;
        String parentID = parent.getId();
        HashMap<String, String> scrollObject = new HashMap<String, String>();
        scrollObject.put("element", parentID);

        scrollObject.put("predicateString", predicateString);
        driver.executeScript("mobile:scroll", scrollObject);
        By m = AppiumBy.iOSNsPredicateString(predicateString);
        return m;
    }

    public void andPressOnCoordinates(AppiumDriver driver, int x, int y) {
        sleep(2000);
        driver.executeScript("mobile: clickGesture", ImmutableMap.of(
                "x", x,
                "y", y
        ));
    }

    public void sleep(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isElementFoundByScroll(WebElement element, double percent, int maxScrolls) {
        int scrollCount = 0;

        Duration originalImplicitWait = driver.manage().timeouts().getImplicitWaitTimeout();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));

        while (scrollCount < maxScrolls) {
            try {
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
                wait.until(ExpectedConditions.visibilityOf(element));

                if (element.isDisplayed()) {
                    driver.manage().timeouts().implicitlyWait(originalImplicitWait);
                    return true;
                }
            } catch (Exception e) {
                TestUtils.log().error("Element not found or not visible, scrolling... " + e.getMessage());
                performSwipeGesture(percent);
            }
            scrollCount++;
        }

        driver.manage().timeouts().implicitlyWait(originalImplicitWait);
        TestUtils.log().error("Reached end of screen, element not found.");
        return false;
    }

    public void performSwipeGesture(double percent) {
        Map<String, Object> params = new HashMap<>();
        params.put("left", 500);
        params.put("top", 1000);
        params.put("width", 300);
        params.put("height", 300);
        params.put("direction", "up");
        params.put("percent", percent);
        driver.executeScript("mobile: swipeGesture", params);
    }
    public boolean isElementEnable(final WebElement element, int timeout) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
            return wait.until((ExpectedCondition<Boolean>) driver -> element.isEnabled());
        } catch (TimeoutException e) {
            return false;
        }

    }

    public void injectionImageWithBrowserstack(String tokenImageUpload) {
        if (tokenImageUpload != null) {
            String script = String.format("browserstack_executor: {\"action\":\"cameraImageInjection\", \"arguments\": {\"imageUrl\" : \"%s\"}}", tokenImageUpload);
            ((JavascriptExecutor) driver).executeScript(script);
        } else {
            TestUtils.log().warn("Not found tokenImageUpload");
        }
    }

}
