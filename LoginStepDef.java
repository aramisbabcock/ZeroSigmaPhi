package org.cucumbertaf.stepdefs;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import org.cucumbertaf.testbase.BaseStepDef;
import org.cucumbertaf.testlib.TestContext;
import org.cucumbertaf.utils.CryptoUtil;
import org.cucumbertaf.utils.PropertyUtil;

import com.google.j2objc.annotations.Property;
//fix
public class LoginStepDef extends BaseStepDef {

	public LoginStepDef(TestContext context) {
		super(context);
	}

	@When("enter username and password")
	public void user_enters_username_password() throws InterruptedException {
		try {
			loginpage.setusername(username);
			loginpage.setpassword(password);
			assertLogger.log("Entered username and password");
		} catch (InterruptedException e) {

			assertLogger.log(e.getMessage());
		}
	}

	@Then("click on agree button")
	public void click_on_agree_button() {
		try {
			if (loginpage.isAgreeButtonDisplayed()) {
				loginpage.clickAgreeButton();
				assertLogger.log("Clicked on Agree Button");
			} else {
				assertLogger.log("Agree button is not present");
			}
		} catch (Exception e) {
			assertLogger.log(e.getMessage());
		}
	}

	@When("enter alternate username and password")
	public void enter_alternate_username_password() throws InterruptedException {
		if (alteidvalue == null || alteidvalue == "") {
			alteidvalue = context.getMoreData().get("empid");
		}

		String password = PropertyUtil.getProperty("password");
		try {

			loginpage.setusername(alteidvalue);
			loginpage.setpassword(password);
			assertLogger.log("Entered Mnager username and password");
		} catch (InterruptedException e) {
			assertLogger.log(e.getMessage());
		}
		alteidvalue = null;
	}

	@Then("click on login button")
	public void user_clicks_login() {
		try {
			loginpage.clicklogin();
			assertLogger.log("Clicked on login button");
		} catch (InterruptedException e) {

			assertLogger.log(e.getMessage());
		}
	}

	@And("validate home page is displayed")
	public void user_validates_homepage() throws InterruptedException {
		boolean homepageval = loginpage.isdisplayedhome();
		assertLogger.assert_equals(homepageval, true, "Home page is loaded as expected",
				"Home page is not loaded as expected");
	}
}