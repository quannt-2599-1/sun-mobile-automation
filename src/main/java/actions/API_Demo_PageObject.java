package actions;

import interfaces.API_Demo_UI;
import io.appium.java_client.AppiumDriver;

public class API_Demo_PageObject extends BasePage{
    private AppiumDriver driver;
    public API_Demo_PageObject(AppiumDriver driver){
        this.driver = driver;
    }
    public void clickToAccessibilityButton(AppiumDriver driver){
        clickToElement(driver, API_Demo_UI.ACCESSIBILITY_BUTTON);
    }
    public String getTextOfAccessibilityButton(AppiumDriver driver){
        return getTextElement(driver, API_Demo_UI.ACCESSIBILITY_BUTTON);
    }

}
