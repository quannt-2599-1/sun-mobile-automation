package stepsdef.smoketest.app;

import constant.Constant;
import drivers.PropertyManager;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import screens.smoketest.app.CartScreen;
import screens.smoketest.app.HomepageScreen;
import screens.smoketest.app.LoginScreen;

import java.io.IOException;
import java.util.Properties;

public class HomePageStepsDef {

    public Properties getProps() throws IOException {
        return PropertyManager.getProps(Constant.PROP_ACCOUNT_FILE);
    }
    @Given("I login successful")
    public void loginSuccessful() throws IOException {
        String username = getProps().getProperty("username");
        String password = getProps().getProperty("password");
        new LoginScreen().enterUsername(username);
        new LoginScreen().enterPassword(password);
        new LoginScreen().pressLoginBtn();
    }
    @When("I add product Sauce Labs Backpack to cart")
    public void addProductBackpackToCart(){
        new HomepageScreen().addBackpackToCart();
    }
    @When("I add product Sauce Labs Bolt T-Shirt to cart")
    public void addProductSauceLabsBoltTShirt(){
        new HomepageScreen().addSauceLabsBoltTShirt();
    }
    @When("I tapping on hamburger menu")
    public void tappingOnHamburgerMenu(){
        new HomepageScreen().tappingOnHamburgerMenu();
    }
    @When("I tapping on X button")
    public void tappingXButton(){
        new HomepageScreen().tappingXButton();
    }
    @When("I add to cart sauce labs onesise")
    public void addToCartSauceLabsOnesie(){
        new HomepageScreen().tappingAddToCartSauceLabsOnesie();
    }
    @When("I add Bolt T-shirt to cart")
    public void addToCartBoltTshirt(){
        new HomepageScreen().addToCartBoltTshirt();
    }
    @When("I scroll to element with {string} and {string}")
    public void iScrollToElement(String childLocAttr, String childLocValue) {
        new HomepageScreen().scrollToElement(childLocAttr,childLocValue);
    }
    @When("I click to cart icon")
    public void clickToCartIcon() {
        new HomepageScreen().clickToCartIcon();
    }
    @When("I scroll to Sauce Labs Fleece Jacket with {string} and {string}")
    public void iScrollToSauceLabsFleeceJacket(String childLocAttr, String childLocValue) {
        new HomepageScreen().scrollToElement(childLocAttr,childLocValue);
        new HomepageScreen().scrollDown();
    }
    @When("I scroll to Sauce Labs Bolt TShirt with {string} and {string}")
    public void iScrollToSauceLabsBoltTShirt(String childLocAttr, String childLocValue) {
        new HomepageScreen().scrollToElement(childLocAttr,childLocValue);
        new HomepageScreen().scrollDown();
    }

    @Then("I should see Menu list")
    public void shouldSeeMenuList(){
        Assert.assertTrue(new HomepageScreen().isMenuListDisplays());
    }

    @Then("I should see HomePage Logo")
    public void shouldSeeHomePageLogo(){
        Assert.assertTrue(new HomepageScreen().isHomePageImageDisplays());
    }
    @Then("I should see Cart displays 1 item")
    public void shouldSeeItemInCartIncrease(){
        Assert.assertEquals(new HomepageScreen().isDisplaysOnCart(),"1");
        new HomepageScreen().sleep(3);
    }
    @Then("I should see Cart screen displays")
    public void shouldSeeCartTitle(){
        Assert.assertEquals(new CartScreen().isDisplaysCartTitle(), "YOUR CART");
    }
    @Then("I should see price of Sauce Labs Fleece Jacket")
    public void shouldSeePriceOfSauceLabFleeceJacket(){
        Assert.assertEquals(new HomepageScreen().priceOfsSauceLabsFleeceJacket(),"$49.99");
        new HomepageScreen().sleep(3);
    }



}
