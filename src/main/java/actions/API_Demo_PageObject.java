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
    public void clickToViewsButton(AppiumDriver driver){
//        clickToElement(driver, API_Demo_UI.VIEWS_BUTTON);
        clickGesture(driver,API_Demo_UI.VIEWS_BUTTON);
    }
    public void clickToDragAndDropButton(AppiumDriver driver){
        clickToElement(driver, API_Demo_UI.DRAG_AND_DROP_BUTTON);
    }
    public void longClickToDragDot(AppiumDriver driver){
        longClickToElement(driver, API_Demo_UI.DRAG_DOT);
    }
    public void dragDotToNewLocation(AppiumDriver driver,int endX, int endY){
        dragGesture(driver, API_Demo_UI.DRAG_DOT,endX,endY);
    }
    public void swipeUpListView(AppiumDriver driver){
        swipeUpScreenFromElement(driver,API_Demo_UI.LIST_VIEW);
    }
    public void swipeDownListView(AppiumDriver driver){
        swipeDownScreenFromElement(driver,API_Demo_UI.LIST_VIEW);
    }
    public void swipeLeftPhotoView(AppiumDriver driver){
        swipeLeftScreenFromElement(driver,API_Demo_UI.FIST_PHOTO_ON_LIST);
    }
    public void goToPreviousScreen(AppiumDriver driver){
        backToPreviousScreen(driver);
    }
    public void sleepThread(int second){
        sleepInSecond(second);
    }
    public void clickToGalleryButton(AppiumDriver driver){
        clickToElement(driver,API_Demo_UI.GALLERY_BUTTON);
    }
    public void clickToPhotoButton(AppiumDriver driver){
        clickToElement(driver,API_Demo_UI.PHOTO_BUTTON);
    }

}
