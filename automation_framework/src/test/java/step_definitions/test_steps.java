package step_definitions;

        import cucumber.api.java.en.Then;
        import cucumber.api.java.en.When;
        import framework.Env;
        import page_objects.login;
        import page_objects.secure;

        import static org.junit.Assert.assertTrue;


public class test_steps {
    @When("^Entering valid credentials$")
    public void enteringValidCredentials() throws Throwable {
        Env.getBrowser().get(Env.getBaseUrl());
        login page = new login();
        page.getUserName().sendKeys("tomsmith");
        page.getPassWord().sendKeys("SuperSecretPassword!");
        page.getSubmit().click();
    }

    @Then("^Assert having logged in$")
    public void assertHavingLoggedIn() throws Throwable {
        secure page = new secure();
        assertTrue("Login unsuccessful", page.getAlertMessage().getText().contains("You logged into a secure area!"));
    }
}
