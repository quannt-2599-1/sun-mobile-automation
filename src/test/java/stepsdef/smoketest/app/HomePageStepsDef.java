package stepsdef.smoketest.app;

import constant.Constant;
import drivers.PropertyManager;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
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
    @Then("I should see HomePage Logo")
    public void shouldSeeHomePageLogo(){
        Assert.assertTrue(new HomepageScreen().isHomePageImageDisplays());
    }


}
