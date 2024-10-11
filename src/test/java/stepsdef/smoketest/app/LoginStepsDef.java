package stepsdef.smoketest.app;

import constant.Constant;
import drivers.PropertyManager;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import screens.smoketest.app.HomepageScreen;
import screens.smoketest.app.LoginScreen;

import java.io.IOException;
import java.util.Properties;

public class LoginStepsDef {

    public Properties getProps() throws IOException {
        return PropertyManager.getProps(Constant.PROP_ACCOUNT_FILE);
    }

    @When("I enter username as {string}")
    public void enterUsernameAs(String username) {
        new LoginScreen().enterUsername(username);
    }

    @When("I enter username")
    public void enterUsernameFromProperties() throws IOException {
        String username = getProps().getProperty("username");
        new LoginScreen().enterUsername(username);
    }

    @When("I enter password as {string}")
    public void enterPasswordAs(String password) {
        new LoginScreen().enterPassword(password);
    }

    @When("I enter password")
    public void enterPasswordFromProperties() throws IOException {
        String password = getProps().getProperty("password");
        new LoginScreen().enterPassword(password);
    }

    @When("I press login button")
    public void pressLoginBtn() {
        new LoginScreen().pressLoginBtn();
    }

    @Then("I should see a invalid account error like {string}")
    public void shouldSeeInvalidAccountError(String error) throws Exception {
        Assert.assertEquals(new LoginScreen().getInvalidAccountError().trim(), error.trim());
    }

    @Then("I should see homepage")
    public void shouldSeeHomepageItems() throws Exception {
        Assert.assertTrue(new HomepageScreen().isCardItemPresent());
        Assert.assertTrue(new HomepageScreen().isProductsItemPresent());
    }
}
