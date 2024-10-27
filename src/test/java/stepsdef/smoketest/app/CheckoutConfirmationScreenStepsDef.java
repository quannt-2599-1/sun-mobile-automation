package stepsdef.smoketest.app;

import constant.Constant;
import drivers.PropertyManager;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import screens.smoketest.app.CartScreen;
import screens.smoketest.app.CheckoutConfirmationScreen;
import screens.smoketest.app.HomepageScreen;
import screens.smoketest.app.LoginScreen;

import java.io.IOException;
import java.util.Properties;

public class CheckoutConfirmationScreenStepsDef {

    public Properties getProps() throws IOException {
        return PropertyManager.getProps(Constant.PROP_ACCOUNT_FILE);
    }
    @Given("I access to Checkout Confirmation screen successful")
    public void loginSuccessful() throws IOException {
        String username = getProps().getProperty("username");
        String password = getProps().getProperty("password");
        new LoginScreen().enterUsername(username);
        new LoginScreen().enterPassword(password);
        new LoginScreen().pressLoginBtn();
        new HomepageScreen().addBackpackToCart();
        new HomepageScreen().clickToCartIcon();
        new CartScreen().clickToCheckoutButton();
    }


    @Then("I should see Firstname, LastName, ZipPostalCode textbox is enable")
    public void shouldSeeTextboxEnable(){
        Assert.assertTrue(new CheckoutConfirmationScreen().isFisrtNameTextboxEnable());
        Assert.assertTrue(new CheckoutConfirmationScreen().isLastNameTextboxEnable());
        Assert.assertTrue(new CheckoutConfirmationScreen().isZipPostalCodeTextboxEnable());
    }

    @When("I input Firstname")
    public void iInputFirstname(String firstName) {
        new CheckoutConfirmationScreen().sendKeysToFirstName(firstName);
    }

    @And("I click to Continue button")
    public void iClickToContinueButton() {
        new CheckoutConfirmationScreen().clickToContinueButton();
    }

    @Then("I should see Error messsage Lastname is required")
    public void iShouldSeeErrorMesssageLastnameIsRequired() {
        String errorMessage = new CheckoutConfirmationScreen().getErrorMessageLastNameRequired();
        Assert.assertEquals(errorMessage,"Last Name is required" );
    }
}
