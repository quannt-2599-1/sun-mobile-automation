package actions;
import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class BasePage {

    private static BasePage getBasePageObject() {
        return new BasePage();
    }
    private WebElement getWebElement(AppiumDriver driver, String xpathLocator){
        return driver.findElement(AppiumBy.xpath(xpathLocator));
    }
    private List<WebElement> getListWebElement(AppiumDriver driver, String xpathLocator){
        return driver.findElements(AppiumBy.xpath(xpathLocator));
    }
    public void clickToElement(AppiumDriver driver, String xpathLocator){
        waitForElementClickable(driver,xpathLocator);
        getWebElement(driver,xpathLocator).click();
    }
    public void clickGesture(AppiumDriver driver, String xpathLocator){
        waitForElementClickable(driver,xpathLocator);
        WebElement element = getWebElement(driver, xpathLocator);
        driver.executeScript("mobile: clickGesture", ImmutableMap.of(
                "elementId", ((RemoteWebElement) element).getId()
        ));
    }
    public void waitForElementClickable(AppiumDriver driver, String xpathLocator){
        WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));
        explicitWait.until(ExpectedConditions.elementToBeClickable(getWebElement(driver,xpathLocator)));
    }
    public void waitForElementVisible(AppiumDriver driver, String xpathLocator){
        WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));
        explicitWait.until(ExpectedConditions.visibilityOf(getWebElement(driver,xpathLocator)));
    }
    public String getTextElement(AppiumDriver driver, String xpathLocator){
        waitForElementVisible(driver,xpathLocator);
        return getWebElement(driver,xpathLocator).getText();
    }
    public void longClickToElement(AppiumDriver driver, String xpathLocator){
        waitForElementClickable(driver,xpathLocator);
        WebElement element = getWebElement(driver, xpathLocator);
        driver.executeScript("mobile: longClickGesture", ImmutableMap.of(
                "elementId", ((RemoteWebElement) element).getId(),
                "duration",2000
        ));
    }
    public void dragGesture(AppiumDriver driver, String xpathLocator, int endX, int endY){
        waitForElementClickable(driver,xpathLocator);
        WebElement element = getWebElement(driver, xpathLocator);
        driver.executeScript("mobile: dragGesture", ImmutableMap.of(
                "elementId", ((RemoteWebElement) element).getId(),
                "endX", endX,
                "endY", endY
        ));
    }
    public void swipeUpScreenFromElement(AppiumDriver driver, String xpathLocator){
        waitForElementVisible(driver,xpathLocator);
        WebElement element = getWebElement(driver, xpathLocator);
        driver.executeScript("mobile: swipeGesture", ImmutableMap.of(
//                "left", 100, "top", 100, "width", 200, "height", 200,
                "elementId", ((RemoteWebElement) element).getId(),
                "direction", "up",
                "percent", 0.75
        ));
    }
    public void swipeLeftScreenFromElement(AppiumDriver driver, String xpathLocator){
        waitForElementVisible(driver,xpathLocator);
        WebElement element = getWebElement(driver, xpathLocator);
        driver.executeScript("mobile: swipeGesture", ImmutableMap.of(
                "elementId", ((RemoteWebElement) element).getId(),
                "direction", "left",
                "percent", 0.75
        ));
    }
    public void swipeDownScreenFromElement(AppiumDriver driver, String xpathLocator){
        waitForElementVisible(driver,xpathLocator);
        WebElement element = getWebElement(driver, xpathLocator);
        driver.executeScript("mobile: swipeGesture", ImmutableMap.of(
//                "left", 100, "top", 100, "width", 200, "height", 200,
                "elementId", ((RemoteWebElement) element).getId(),
                "direction", "down",
                "percent", 0.75
        ));
    }
    public void backToPreviousScreen(AppiumDriver driver){
        driver.navigate().back();

    }
    public void sleepInSecond(int second) {
        try {
            Thread.sleep((long)second);
        } catch (InterruptedException var3) {
            InterruptedException e = var3;
            e.printStackTrace();
        }

    }

}
