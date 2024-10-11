package screens.smoketest.app;

import common.BaseTest;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;

public class HomepageScreen extends BaseTest {

    @AndroidFindBy(accessibility = "test-Cart drop zone")
    @iOSXCUITFindBy()
    private WebElement cardItem;

    @AndroidFindBy(accessibility = "test-PRODUCTS")
    @iOSXCUITFindBy()
    private WebElement productsItem;

    public boolean isCardItemPresent() {
        return find(cardItem, 1);
    }

    public boolean isProductsItemPresent() {
        return find(productsItem, 1);
    }
}
