package actions;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
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
}
