package StepDefinitions;

import Utils.CommonMethods;
import Utils.ConfigReader;
import io.cucumber.java.en.*;

public class Login extends CommonMethods {
    @Given("user launched the browser and navigate to the URL")
    public void user_launched_the_browser_and_navigate_to_the_url() {
        openBrowserAndLaunchApplication();
    }
    @And("user clicked on My Account button")
    public void user_clicked_on_my_account_button() {
        doClick(login.MyAcctBtn);
    }
    @When("user enters Email Address")
    public void user_enters_email_address() {
        sendText(login.EnterEmailAddress, ConfigReader.getPropertyValue("username"));
    }
    @When("user enters  password")
    public void when_user_enters_password() {
        sendText(login.EnterPassword, ConfigReader.getPropertyValue("password"));
    }
    @When("user clicked on LOGIN button to log in")
    public void then_user_clicked_on_login_button_to_log_in() {
        doClick(login.ClickLoginBtn);

    }
    @When("user enters valid {string}")
    public void user_enters_valid(String EmailAddress) {
        sendText(login.EnterValidEmail, EmailAddress);


    }
    @When("user enters  invalid {string}")
    public void user_enters_invalid(String password) {
        sendText(login.EnterInvalidPassword, password);

    }

    }

