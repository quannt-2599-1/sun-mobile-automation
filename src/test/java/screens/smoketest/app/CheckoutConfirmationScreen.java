package screens.smoketest.app;

import common.BaseTest;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

public class CheckoutConfirmationScreen extends BaseTest {
    /*---Locator---*/
    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"CHECKOUT: INFORMATION\"]")
    private WebElement checkoutConfirmationTitle;





    /*---Action---*/
    public boolean isDisplaysCartTitle() {
        return find(checkoutConfirmationTitle,3);
    }

}
