package screens.smoketest.app;

import common.BaseTest;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

public class CartScreen extends BaseTest {
    /*---Locator---*/
    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"YOUR CART\"]")
    private WebElement cartScreenTitle;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"QTY\"]")
    private WebElement quantityLabel;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"DESCRIPTION\"]")
    private WebElement description;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Sauce Labs Backpack\"]")
    private WebElement backpackName;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Sauce Labs Bolt T-Shirt\"]")
    private WebElement boltTshirtName;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Sauce Labs Backpack\"]/following-sibling::*")
    private WebElement backpackDescription;
    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"test-Amount\"]/*")
    private WebElement backpackQuantity;
    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"test-Price\"]/android.widget.TextView")
    private WebElement backpackPrice;
    @AndroidFindBy(xpath ="//android.view.ViewGroup[@content-desc=\"test-Item\"]//android.view.ViewGroup[@content-desc=\"test-REMOVE\"]")
    private WebElement removeButton;
    @AndroidFindBy(accessibility ="test-CONTINUE SHOPPING")
    private WebElement continueShoppingButton;
    @AndroidFindBy(accessibility ="test-CHECKOUT")
    private WebElement checkoutButton;




    /*---Action---*/
    public String isDisplaysCartTitle() {
        return getAttribute(cartScreenTitle,"text");
    }

    public String isDisplaysQuantityLabel() {
        return getAttribute(quantityLabel,"text");
    }

    public String isDisplaysDescription() {
        return getAttribute(description,"text");
    }
    public String getBackpackName(){
        return getAttribute(backpackName,"text");
    }
    public String isDisplaysBackpackDescription(){
        return getAttribute(backpackDescription,"text");
    }
    public String isDisplaysBackpackQuantity(){
        return getAttribute(backpackQuantity,"text");
    }
    public String isDisplaysBackpackPrice(){
        return getAttribute(backpackPrice,"text");
    }
    public boolean isBackpackNameDisplays(){
        return find(backpackName,3);
    }
    public boolean isTShirtNameDisplays(){
        return find(boltTshirtName,3);
    }
    public void clickToRemoveButton(){
        click(removeButton);
    }

    public void clickToContinueShoppingButton() {
        click(continueShoppingButton);
    }

    public void clickToCheckoutButton() {

        click(checkoutButton);
    }
}
