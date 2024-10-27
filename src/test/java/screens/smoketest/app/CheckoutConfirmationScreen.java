package screens.smoketest.app;

import common.BaseTest;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

public class CheckoutConfirmationScreen extends BaseTest {
    /*---Locator---*/
    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"CHECKOUT: INFORMATION\"]")
    private WebElement checkoutConfirmationTitle;
    @AndroidFindBy(accessibility = "test-First Name")
    private WebElement firstNameTextbox;
    @AndroidFindBy(accessibility = "test-Last Name")
    private WebElement lastNameTextbox;
    @AndroidFindBy(accessibility = "test-Zip/Postal Code")
    private WebElement zipPostalCodeTextbox;
    @AndroidFindBy(accessibility = "test-CONTINUE")
    private WebElement continueButton;
    @AndroidFindBy(accessibility = "test-Error message")
    private WebElement errorMessage;




    /*---Action---*/
    public boolean isDisplaysCartTitle() {
        return find(checkoutConfirmationTitle,3);
    }
    public boolean isFisrtNameTextboxEnable() {
        return isElementEnable(firstNameTextbox,3);
    }
    public boolean isLastNameTextboxEnable() {
        return isElementEnable(lastNameTextbox,3);
    }
    public boolean isZipPostalCodeTextboxEnable() {
        return isElementEnable(zipPostalCodeTextbox,3);
    }

    public void sendKeysToFirstName(String firstName) {
        sendKeys(firstNameTextbox,firstName);
    }

    public void clickToContinueButton() {
        click(continueButton);
    }
    public String getErrorMessageLastNameRequired() {
       return getAttribute(errorMessage,"text");
    }
}
