package actions;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;

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
        getWebElement(driver,xpathLocator).click();
    }
}
