package org.cucumbertaf.stepdefs;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import org.apache.commons.math3.genetics.Fitness;
import org.apache.pdfbox.contentstream.operator.state.Save;
import org.cucumbertaf.pageobjects.ApplicationDefinitionPage;
import org.cucumbertaf.testbase.BaseStepDef;
import org.cucumbertaf.testlib.TestContext;
import org.cucumbertaf.testlib.CTAFAssert;
import org.cucumbertaf.utils.PropertyUtil;
import org.cucumbertaf.utils.WebTable;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import io.cucumber.core.internal.com.fasterxml.jackson.databind.deser.std.StringArrayDeserializer;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;

public class ManagerCertificationStepDef extends BaseStepDef {

	public ManagerCertificationStepDef(TestContext context) {
		super(context);
	}

	@Then("click on setup dropdown")
	public void click_on_setup_dropdown() {
		try {
			managercertificationpage.clickonSetupLink();
			assertLogger.log("Clicked on Setup link");
		} catch (InterruptedException e) {
			assertLogger.log(e.getMessage());
		}
	}

	@Then("select Cetrification from setup drop down")
	public void click_on_certification_link() {
		try {
			managercertificationpage.selectCertificationinDropdown("Certifications");
			assertLogger.log("Selected Certification link from dropdown");
		} catch (InterruptedException e) {
			assertLogger.log(e.getMessage());
		}
	}

	@And("validate certification page is displayed")
	public void validate_schedule_Certification_page_is_displayed() {
		String certpagetitle;
		try {
			certpagetitle = managercertificationpage.getpageTitle();
			assertLogger.assert_equals(certpagetitle, "Certifications", "Certifications page is displayed as expected",
					"Certifications page is not displayed as expected");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	@Then("create new manager certification")
	public void search_app_in_appdefinitionPage() throws InterruptedException {
		managercertificationpage.clicknewCertification();
		managercertificationpage.clicknewManagerCertification();
	}

	@And("validate schedule certification page is displayed")
	public void validate_Certification_page_is_displayed() {
		String certpagetitle;
		try {
			certpagetitle = managercertificationpage.getpageTitle();
			assertLogger.assert_equals(certpagetitle, "Schedule Certification",
					"Certifications page is displayed as expected", "Certifications page is not displayed as expected");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	@Then("enter certification name")
	public void input_certification_name() throws InterruptedException {
		String inputvalString = certificationtype + " Certification" + " - " + certificationaccesslevel + " - "
				+ applicationNameval + " - " + "[${fullDate}]";
		managercertificationpage.setcertificationName(inputvalString);
		assertLogger.log("certification name entered :" + inputvalString);
	}

	@And("enter certification recipient")
	public void input_certification_recipient() {

		try {
			managercertificationpage.setrecipientname(manager_eid);
			managercertificationpage.selectmanagerfromdropdown(manager_eid);
			assertLogger.log("manager selected");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@And("select run now")
	public void select_run_now() throws InterruptedException {
		managercertificationpage.selectrunnowcheckbox();
		assertLogger.log("Run now selected");
	}

	@And("select appliaction to certify")
	public void input_application_name_and_select() {
		try {
			managercertificationpage.setapplication(applicationNameval);
			managercertificationpage.selectapplicationfromdropdown(applicationNameval);
			assertLogger.log("Application selected");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@And("choose access level")
	public void choose_access_level() throws InterruptedException {
		switch (certificationaccesslevel) {
		case "accounts":
		case "Accounts":
			managercertificationpage.chooseCertificationaccessLevelaccounts();
			assertLogger.log("Access level selected - Account");
			break;
		case "entitlements":
		case "Entitlements":
			managercertificationpage.chooseCertificationaccessLevelentitlement();
			assertLogger.log("Access level selected - Entitlement");
			break;
		default:
			assertLogger.log("set [certification_accesslevel] to account or Entitlement");
			break;
		}

	}

	@Then("click next")
	public void click_next_page_button() {
		try {
			managercertificationpage.clicknextpage();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@And("select staging period")
	public void select_staging_period() throws InterruptedException {
		managercertificationpage.selectstagingperiod();
		assertLogger.log("selected: Staging Period");
	}

	@And("unselect enable revocation period")
	public void unselect_enable_revocation_period() throws InterruptedException {
		managercertificationpage.unselectrevocationperiod();
		assertLogger.log("Unselected: enable revocation period");
	}

	@And("select process revokes immediately")
	public void select_process_revokes_immediately() throws InterruptedException {
		managercertificationpage.selectprocessrevokesimmediately();
		assertLogger.log("selected: Process Revikes Immediately");
	}

	@And("select supress initial notification")
	public void select_supress_initial_notification() throws InterruptedException {
		managercertificationpage.selectsupressinitailnotification();
		assertLogger.log("selected: Supress Inetial Notification");
	}

	@And("choose exclusion rule")
	public void choose_exclusion_rule() throws InterruptedException {
		managercertificationpage.selectExclusionRule(certificationexclusionrule);
		assertLogger.log("Selected: exclusion rule");
	}

	@Then("click schedule certification button")
	public void click_schedule_certification_button() {
		try {
			managercertificationpage.clickschedulecertification();
			assertLogger.log("Certification Scheduled");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Then("search for certification created")
	public void search_for_certification_created() throws InterruptedException {
		String inputvalString = certificationtype + " Certification" + " - " + certificationaccesslevel + " - "
				+ applicationNameval;
		managercertificationpage.inputcertificationsname(inputvalString);
		managercertificationpage.searchcertifications();
		assertLogger.log("Searched for certification :" + inputvalString);
	}

	@Then("validate certification is staged")
	public void validate_certification_is_staged() throws InterruptedException {

		for (int i = 0; i < 30; i++) {
			if (managercertificationpage.certificationSearchResultTable().getCellValueAt(2, 3).contains("Staged")) {
				break;
			}
			managercertificationpage.clickcertificationsearchRefresh();
		}
		assertLogger.log("Certification is staged");
	}
	
	@Then("open the certification")
	public void open_the_certification(){
		managercertificationpage.certificationSearchResultTable().clickAt(2, 1);
		assertLogger.log("Clicked on the certification");
	}
	
	@Then("activate the certification")
	public void activate_the_certification() throws InterruptedException {
		managercertificationpage.clickactivateCertification();
		assertLogger.log("Certification Activated");
	}
	
	
	@Then("validate certification is active")
	public void validate_certification_is_active() throws InterruptedException {

		for (int i = 0; i < 30; i++) {
			if (managercertificationpage.certificationSearchResultTable().getCellValueAt(2, 3).contains("Active")) {
				break;
			}
			managercertificationpage.clickcertificationsearchRefresh();
		}
		assertLogger.log("Certification is Active");
	}
	
	@Then("open access review")
	public void open_access_review() throws InterruptedException {
		managercertificationpage.openManagerAccessReview();
	}
	
	@Then("validate manager access review page")
	public void validate_manager_access_review() throws InterruptedException {
		String title = managercertificationpage.gettitleaccessreview();
		assertLogger.assert_equals(title.contains("Manager Access Review"), true, "Manager Access Review page is displayed as expected",
				"Manager Access Review page is not displayed as expected");
	}
	
	@And("bulk select all accounts")
	public void bulk_select_all_accounts() throws InterruptedException {
		managercertificationpage.clickbulkselectdropdown();
		managercertificationpage.clickbulkselectEverything();
	}
	
	@And("bulk approve all accounts")
	public void bulk_approve_all_accounts() throws InterruptedException {
		managercertificationpage.clickbulkdecisiondropdown();
		managercertificationpage.clickbulkdecisionapprove();
	}
	
	@Then("save first user in access review")
	public void save_first_user_in_access_review() throws NoSuchElementException, InterruptedException {
		context.setStringvalue(managercertificationpage.certificationAccountsTable().getCellValueAt(1, 7).trim());
		assertLogger.log("saved for validation :" + context.getStringvalue()); 
	}
	
	@And("revoke first user")
	public void revoke_first_user() throws InterruptedException {
		managercertificationpage.clickrevokeFirstaccount();
	
	}
	
	@And("save and confirm desision")
	public void save_and_confirm_desision() throws InterruptedException {
		managercertificationpage.clickSaveButton();
		managercertificationpage.clickConfirmSaveButton();
	
	}
	
	@And("click confirm sign off")
	public void click_confirm_sign_off() throws InterruptedException {
		managercertificationpage.clickSignOffButton();
	}
	
	
}
