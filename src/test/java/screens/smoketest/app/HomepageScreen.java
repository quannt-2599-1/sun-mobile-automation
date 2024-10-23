package screens.smoketest.app;

import common.BaseTest;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

public class HomepageScreen extends BaseTest {

    @AndroidFindBy(accessibility = "test-Cart drop zone")
    private WebElement cardItem;
    @AndroidFindBy(accessibility = "test-PRODUCTS")
    private WebElement productsItem;
    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"test-Menu\"]/following-sibling::android.widget.ImageView")
    private WebElement homePageImage;
    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"test-Menu\"]//android.widget.ImageView")
    private WebElement hamburgerMenu;
    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"test-ALL ITEMS\"]/..")
    private WebElement menuList;
    @AndroidFindBy(accessibility = "test-Close")
    private WebElement xButton;
    @AndroidFindBy(xpath = "//android.widget.TextView[@content-desc=\"test-Item title\" and @text=\"Sauce Labs Onesie\"]/following-sibling::android.view.ViewGroup[@content-desc=\"test-ADD TO CART\"]")
    private WebElement addToCartSauceLabsOnesie;
    @AndroidFindBy(xpath = "//android.widget.TextView[@content-desc=\"test-Item title\" and @text=\"Sauce Labs Onesie\"]")
    private WebElement sauceLabsOnesieProductName;
    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"test-Cart\"]//android.widget.TextView")
    private WebElement itemOnCart;
    @AndroidFindBy(accessibility = "test-Cart")
    private WebElement cartIcon;
    @AndroidFindBy(xpath = "//android.widget.TextView[@content-desc=\"test-Item title\" and @text=\"Sauce Labs Backpack\"]/following-sibling::android.view.ViewGroup[@content-desc=\"test-ADD TO CART\"]")
    private WebElement addToCartBackpack;
    @AndroidFindBy(xpath = "//android.widget.TextView[@content-desc=\"test-Item title\" and @text=\"Sauce Labs Bolt T-Shirt\"]/following-sibling::android.view.ViewGroup[@content-desc=\"test-ADD TO CART\"]")
    private WebElement addToCartBoltTShirt;
    @AndroidFindBy(xpath = "//android.widget.TextView[@content-desc=\"test-Item title\" and @text=\"Sauce Labs Fleece Jacket\"]/following-sibling::android.widget.TextView")
    private WebElement sauceLabsJacketPrice;

    public boolean isCardItemPresent() {
        return find(cardItem, 1);
    }
    public boolean isProductsItemPresent() {
        return find(productsItem, 1);
    }
    public boolean isHomePageImageDisplays(){
        return find(homePageImage, 1);
    }
    public void tappingOnHamburgerMenu(){
        click(hamburgerMenu);
    }
    public boolean isMenuListDisplays(){
        return find(menuList, 1);
    }

    public void tappingXButton() {
        click(xButton);
    }
    public void tappingAddToCartSauceLabsOnesie() {
        click(addToCartSauceLabsOnesie);
    }
    public void addToCartBoltTshirt() {
        click(addToCartBoltTShirt);
    }
    public String isDisplaysOnCart(){
        return getAttribute(itemOnCart,"text");
    }
    public void sleepThread(int second){
        sleep(second*1000);
    }

    public void scrollToElement(String childLocAttr, String childLocValue) {
        andScrollDownToElementWithUiScrollable(childLocAttr, childLocValue);
    }
    public void scrollDown(){
            scrollDownGesture();
    }
    public void clickToCartIcon() {
        click(cartIcon);
    }
    public void addBackpackToCart() {
        click(addToCartBackpack);
    }
    public void addSauceLabsBoltTShirt() {
        click(addToCartBoltTShirt);
    }

    public String priceOfsSauceLabsFleeceJacket() {
        return getAttribute(sauceLabsJacketPrice,"text");
    }

}
