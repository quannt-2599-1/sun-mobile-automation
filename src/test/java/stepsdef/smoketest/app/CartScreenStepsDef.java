package stepsdef.smoketest.app;

import constant.Constant;
import drivers.PropertyManager;
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

public class CartScreenStepsDef {

    public Properties getProps() throws IOException {
        return PropertyManager.getProps(Constant.PROP_ACCOUNT_FILE);
    }
    @Given("I login and click to Cart Icon successful")
    public void loginSuccessful() throws IOException {
        String username = getProps().getProperty("username");
        String password = getProps().getProperty("password");
        new LoginScreen().enterUsername(username);
        new LoginScreen().enterPassword(password);
        new LoginScreen().pressLoginBtn();
        new HomepageScreen().clickToCartIcon();
    }

    @When("I click to remove button")
    public void clickToRemoveButton(){
        new CartScreen().clickToRemoveButton();
        new CartScreen().sleep(3000);
    }
    @When("I click Continue shopping button")
    public void clickToContinueShoppingButton(){
        new CartScreen().clickToContinueShoppingButton();
    }
    @When("I click to checkout Button")
    public void clickToCheckoutButton(){
        new CartScreen().clickToCheckoutButton();
    }

    @Then("I should see Quantity label")
    public void shouldSeeQuantityLabel(){
        Assert.assertEquals(new CartScreen().isDisplaysQuantityLabel(),"QTY");
    }
    @Then("I should see Cart Description")
    public void shouldSeeDescription(){
        Assert.assertEquals(new CartScreen().isDisplaysDescription(),"DESCRIPTION");
    }
    @Then("I should see product Sauce Labs Backpack to cart displays on Cart")
    public void shouldSeeBackpackOnCart(){
        Assert.assertEquals(new CartScreen().getBackpackName(),"Sauce Labs Backpack");
    }
    @Then("I should see product description")
    public void shouldSeeBackpackDescription(){
        Assert.assertEquals(new CartScreen().isDisplaysBackpackDescription(),"carry.allTheThings() with the sleek, streamlined Sly Pack that melds uncompromising style with unequaled laptop and tablet protection.");
    }
    @Then("I should see quantity is 1")
    public void shouldSeeBackpackQuantity(){
        Assert.assertEquals(new CartScreen().isDisplaysBackpackQuantity(),"1");
    }
    @Then("I should see price product")
    public void shouldSeeBackpackPrice(){
        Assert.assertEquals(new CartScreen().isDisplaysBackpackPrice(),"$29.99");
    }
    @Then("I should see product Sauce Labs Backpack remove from cart")
    public void shouldSeeBackpackRemove(){
        Assert.assertFalse(new CartScreen().isBackpackNameDisplays());
    }
    @Then("I should see Backpack and Bolt Tshirt display on Cart")
    public void shouldSeeBackpackAndTshirtDisplays(){
        Assert.assertTrue(new CartScreen().isBackpackNameDisplays());
        Assert.assertTrue(new CartScreen().isTShirtNameDisplays());
        new CartScreen().sleep(3000);
    }
    @Then("I should see Checkout confirmation screen displays")
    public void shouldSeeConfirmationScreen(){
        Assert.assertTrue(new CheckoutConfirmationScreen().isDisplaysCartTitle());
    }

}
