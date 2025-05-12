package org.cucumbertaf.stepdefs;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.cucumbertaf.testbase.BaseStepDef;
import org.cucumbertaf.testlib.TestContext;
import org.openqa.selenium.WebElement;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;

public class AttributesStepDef extends BaseStepDef {

	public AttributesStepDef(TestContext context) {
		super(context);
	}

	@Then("click on identities")
	public void click_on_identities() {
		try {
			attributespage.clickidentities();
			assertLogger.log("Clicked on identities link");
		} catch (InterruptedException e) {

			assertLogger.log(e.getMessage());
		}
	}

	@Then("click on identity warehouse")
	public void click_on_identity_warehouse() throws InterruptedException {
		try {
			attributespage.clickidentitywarehouse();
			assertLogger.log("Clicked on idenity warehouse dropdown");
		} catch (InterruptedException e) {

			assertLogger.log(e.getMessage());
		}
	}

	@And("validate identity warehouse page is displayed")
	public void validate_identity_warehouse_page_is_displayed() throws InterruptedException {
		String appdefval = attributespage.validateidwarehousetitle();
		assertLogger.assert_equals(appdefval, "Identity Warehouse", "Identity warehouse page is displayed",
				"Identity warehouse page is not displayed");
	}

	@Then("search user in identity cube")
	public void search_user_in_identity_cube() throws InterruptedException {
		if(context.getMoreData().containsKey("empid")) {
			eidvalue = context.getMoreData().get("empid");
		}
		attributespage.inputusertoSearch(eidvalue);
		attributespage.clickidentitySearchButton();
	}
	
	@Then("search user in identity cube by fullname")
	public void search_user_in_identity_cube_by_fullname() throws InterruptedException {
		if(fullName == null) {
			fullName = context.getMoreData().get("fullName");
		}
		attributespage.inputusertoSearch(fullName);
		attributespage.clickidentitySearchButton();
	}

	@Then("click on valid identity")
	public void click_on_valid_identity() throws InterruptedException {
		attributespage.clickcelldata(2, 1);
	}
	
	@And("validate identity page is displayed")
	public void validate_identity_page_is_displayed() throws InterruptedException {
		String appdefval = attributespage.validateidwarehousetitle();
		boolean value = appdefval.contains(fnameval);
		assertLogger.assert_equals(value, true, "Identity page is displayed", "Identity page is not displayed");
	}

	@Then("click on entitlements tab")
	public void click_on_entitlements_tab() throws InterruptedException {
		attributespage.clickonEntitlementsTab();
	}

	@Then("validate access present on account")
	public void validate_access_request_is_processed() throws InterruptedException {
		accessvals = Arrays.stream(accessval.trim().split("\\|")).map(String::trim).toArray(String[]::new);
		
		switch (accessrequest_type) {
		case "entitlement":
			for (String ent : accessvals) {
				int idx = -1;
				String tempString = "";

				List<WebElement> entitlements = attributespage.getEntitlementNamesOnAccount();

				for (int i = 0; i < entitlements.size(); ++i) {
					if (entitlements.get(i).getText().contains(ent)) {
						tempString = entitlements.get(i).getText();
						idx = i;
						break;
					}
				}

				if (idx == -1) {
					assertLogger.assert_contains(tempString, ent, "Entitlement present on account",
							"Entitlement not present on account");
					return;
				} else {
					List<WebElement> entitlementAtts = attributespage.getEntitlementAttributes();
					String tempString2 = entitlementAtts.get(idx).getAttribute("textContent");
					assertLogger.assert_not_contains(tempString2, "This entitlement does not exist on the account.",
							"Entitlement exist on account", "Entitlement does not exist on account");
				}
			}

			break;

		case "role":

			// need to implement . no use case in environment

			break;

		default:
			break;
		}
		
	}

	@Then("validate access not on account")
	public void validate_access_not_on_account() throws InterruptedException {
		accessvals = Arrays.stream(accessval.trim().split("\\|")).map(String::trim).toArray(String[]::new);

		switch (accessrequest_type) {
		case "entitlement":
			for (String ent : accessvals) {

				Boolean isPresent = false;

				List<WebElement> entitlements = attributespage.getEntitlementNamesOnAccount();

				for (int i = 0; i < entitlements.size(); ++i) {
					if (entitlements.get(i).getText().contains(ent)) {
						isPresent = true;
						break;
					}
				}

				assertLogger.assert_equals(isPresent, false, "Access not on account", "Access present on account");
				return;

			}

			break;

		case "role":

			// need to implement . no use case in environment

			break;

		default:
			break;
		}

	}
	
	@Then("save user id and reset password")
	public void extract_and_save_approver_id() throws InterruptedException {
		String id = attributespage.getid();
		
		Map<String, String> mpMap = context.getMoreData();
		mpMap.put("empid", id);
		context.setMoreData(mpMap);
		
		attributespage.resetPassword();
		String responceString = attributespage.setNewPassword();
		assertLogger.assert_contains(responceString, "saved", "password updated", "password update failed :" + responceString);
	}

}
