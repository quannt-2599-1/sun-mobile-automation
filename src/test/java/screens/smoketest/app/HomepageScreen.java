package screens.smoketest.app;

import common.BaseTest;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;

public class HomepageScreen extends BaseTest {

    @AndroidFindBy(accessibility = "test-Cart drop zone")
    private WebElement cardItem;

    @AndroidFindBy(accessibility = "test-PRODUCTS")
    private WebElement productsItem;
    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"test-Menu\"]/following-sibling::android.widget.ImageView")
    private WebElement homePageImage;

    public boolean isCardItemPresent() {
        return find(cardItem, 1);
    }

    public boolean isProductsItemPresent() {
        return find(productsItem, 1);
    }
    public boolean isHomePageImageDisplays(){
        return find(homePageImage, 1);
    }
}
