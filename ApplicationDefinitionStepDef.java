package org.cucumbertaf.stepdefs;

import java.util.List;
import java.util.Map;

import org.cucumbertaf.pageobjects.ApplicationDefinitionPage;
import org.cucumbertaf.testbase.BaseStepDef;
import org.cucumbertaf.testlib.TestContext;
import org.cucumbertaf.testlib.CTAFAssert;
import org.cucumbertaf.utils.PropertyUtil;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;

public class ApplicationDefinitionStepDef extends BaseStepDef{


	public ApplicationDefinitionStepDef(TestContext context) {
		super(context);
		}

	@Then("click on applications dropdown")
	public void click_on_applications_link() {
		try {
			applicationdefinitionpage.clickapplication();
			assertLogger.log("Clicked on Application link");
		} catch (InterruptedException e) {
			assertLogger.log(e.getMessage());
		}
	}

	@Then("select application type from application drop down")
	public void click_on_application_definition_link() {
		try {
			applicationdefinitionpage.selectapplicationtype(PropertyUtil.getProperty("applicationtype"));
			assertLogger.log("Selected Application Definition link from dropdown");
		} catch (InterruptedException e) {

			assertLogger.log(e.getMessage());
		}
	}

	@And("validate application definition page is displayed")
	public void validate_application_definition_page_is_displayed() {
		String appdefval = applicationdefinitionpage.gettitle();
		assertLogger.assert_equals(appdefval, PropertyUtil.getProperty("applicationtype"),
				"Application Definition page is displayed as expected",
				"Application Definition page is not displayed as expected");
	}

	@Then("search for application in application definition page")
	public void search_app_in_appdefinitionPage() throws InterruptedException {
		applicationdefinitionpage.setApplication(applicationNameval);
		applicationdefinitionpage.clicksearch();
	}

	@Then("validate searched application is displayed")
	public void validate_search_result_app_in_appdefinitionPage() throws InterruptedException {
		boolean isTable = applicationdefinitionpage.isdisplayedsearchtable();
		String rowval = "";
		// assertLogger.assert_equals(isTable, true, "Application found in application
		// definition page as expected",
		// "Search result in application definition page is not as expected");
		if (isTable) {
			rowval = applicationdefinitionpage.getcelldata(2, 1);
		}
		assertLogger.assert_equals(rowval, applicationNameval,
				"Clicked on app link - Applicaion Name is displayed as expected",
				"cannot click on link - Applicaion Name is not displayed as expected");

	}

	@Then("click on valid application link")
	public void click_on_searched_application_link() {
		applicationdefinitionpage.clickcelldata(2, 1);
	}

	@And("validate application name on application page is displayed correctly")
	public void validate_name_on_application_page() {
		String appnameval = applicationdefinitionpage.getAppName();

		assertLogger.assert_equals(appnameval, applicationNameval,
				"Application Page - Applicaion Name is displayed as expected",
				"Incorrect name - Applicaion Name is not displayed as expected");
	}

	@And("validate application type on application page is displayed correctly")
	public void validate_type_on_application_page() {

		String apptypeval = applicationdefinitionpage.getApptype();

		assertLogger.assert_equals(apptypeval, applicationtypeval,
				"Application Page - Applicaion type is displayed as expected",
				"Incorrect App Type - Applicaion Type is not displayed as expected");
	}

	@And("validate application owner on application page is displayed correctly")
	public void validate_owner_on_application_page() {
		String appOwnerval = jsExecutor.executeScript("return document.getElementById('applicationPageOwner-inputEl').value").toString();
		System.out.print(appOwnerval);
		System.out.println(jsExecutor);
		assertLogger.assert_equals(appOwnerval, applicationOwnerval,
				"Application Page - Applicaion Owner is displayed as expected",
				"Incorrect App Owner - Applicaion Owner is not displayed as expected");
	}

	@Then("click on configuration tab")
	public void click_on_configuration_tab() {
		try {
			applicationdefinitionpage.clickonconfig();
			assertLogger.log("Clicked on Configuration Tab");
		} catch (InterruptedException e) {

			assertLogger.log(e.getMessage());
		}
	}
	
	@Then("click on accounts tab")
	public void click_on_accounts_tab() {
		try {
			applicationdefinitionpage.clickaccountstab();
			assertLogger.log("Clicked on accounts Tab");
		} catch (InterruptedException e) {

			assertLogger.log(e.getMessage());
		}
	}

	@Then("click on test connection button")
	public void click_on_test_connection_button() {
		try {
			applicationdefinitionpage.clicktestconnection();
			assertLogger.log("Clicked on Test Connection Button");
		} catch (InterruptedException e) {

			assertLogger.log(e.getMessage());
		}
	}

	@And("validate if test connection is successful")
	public void validate_if_connection_is_successful() {
		String testval = applicationdefinitionpage.validatetestconnection();
		assertLogger.assert_equals(testval, PropertyUtil.getProperty("testconnectionmsg"),
				"Test connection is successful as expected", "Test connection is not successful as expected");
	}

	@Then("click on schema tab")
	public void click_on_schema_tab() {
		try {
			applicationdefinitionpage.clickonSchema();
			assertLogger.log("Clicked on Schema Tab");
		} catch (InterruptedException e) {
			assertLogger.log(e.getMessage());
		}
	}

	@Then("click on preview button")
	public void click_on_preview_button() {
		try {
			applicationdefinitionpage.clickonPreview();
			assertLogger.log("Clicked on preview button");
		} catch (InterruptedException e) {
			assertLogger.log(e.getMessage());
		}
	}

	@And("validate preview table is displayed")
	public void validate_preview_table_disaplyed() throws InterruptedException {
		boolean isTable = applicationdefinitionpage.isdisplayedpreviewtable();
		assertLogger.assert_equals(isTable, true, "Schema preview is showing as expected",
				"Schema preview table is not as expected.");
	}

	@And("close preview table")
	public void close_preview_table() throws InterruptedException {
		applicationdefinitionpage.clickonclosePreviewPane();
	}
	
	@And("search for the account")
	public void search_for_the_account() throws InterruptedException {
		applicationdefinitionpage.setaccountsearchvalue(context.getStringvalue());
		applicationdefinitionpage.clicksearchaccounts();
	}
	
	@And("validate account is active")
	public void validate_account_is_active() throws InterruptedException {
		String valString = applicationdefinitionpage.getAccountSearchFirstStatus();
		assertLogger.assert_equals(valString, "Active", "Account Status is active as expected", "Fail: Account status is not active");
		
	}
	
	@And("validate account is disabled")
	public void validate_account_is_disabled() throws InterruptedException {
		String valString = applicationdefinitionpage.getAccountSearchFirstStatus();
		assertLogger.assert_equals(valString, "Disabled", "Account Status is active as expected", "Fail: Account status is not active");
		
	}

}
