package screens.smoketest.app;

import common.BaseTest;
import drivers.DriverManager;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;

public class LoginScreen extends BaseTest {

    @AndroidFindBy(accessibility = "test-Username")
    @iOSXCUITFindBy()
    private WebElement usernameField;

    @AndroidFindBy(accessibility = "test-Password")
    @iOSXCUITFindBy()
    private WebElement passwordField;
    @AndroidFindBy(accessibility = "test-LOGIN")
    @iOSXCUITFindBy()
    private WebElement loginBtn;

    @AndroidFindBy(className = "android.webkit.WebView")
    @iOSXCUITFindBy()
    private WebElement titlePageTxt;

    @AndroidFindBy(xpath = "//*[@content-desc='test-Error message']//android.widget.TextView[1]")
    @iOSXCUITFindBy()
    private WebElement invalidAccountErrorTxt;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Username is required\"]")
    private WebElement errorMessageUsernameRequired;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Password is required\"]")
    private WebElement errorMessagePasswordRequired;

    public void pressLoginBtn() {
        click(loginBtn);
    }

    public void enterUsername(String username) {
        clear(usernameField);
        sendKeys(usernameField, username);
    }

    public void enterPassword(String password) {
        clear(passwordField);
        sendKeys(passwordField, password);
    }

    public String getInvalidAccountError() {
        return getAttribute(invalidAccountErrorTxt, "text");
    }
    public String getErrorMessageUsernameRequired(){
        return getAttribute(errorMessageUsernameRequired,"text");
    }
    public String getErrorMessagePasswordRequired(){
        return getAttribute(errorMessagePasswordRequired,"text");
    }

}
