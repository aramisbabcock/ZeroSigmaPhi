package org.cucumbertaf.stepdefs;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.testng.Assert.assertEquals;

import java.util.Map;

import org.cucumbertaf.testbase.BaseStepDef;
import org.cucumbertaf.testlib.TestContext;
import org.cucumbertaf.utils.CryptoUtil;
import org.cucumbertaf.utils.PropertyUtil;
import org.testng.Assert;

import com.google.j2objc.annotations.Property;

public class GroupsStepDef extends BaseStepDef {

	public GroupsStepDef(TestContext context) {
        super(context);
    }
	
    
	@Then("click on setup")
	public void click_on_my_work_link() {
		try {
			groupsPage.clicksetup();
			assertLogger.log("Validation Passed < Clicked on Setup Link >");
		} catch (InterruptedException e) {

			assertLogger.log(e.getMessage());
		}

	}

	@Then("select Groups link")
	public void select_work_items_link() {
		try {
			groupsPage.clickGroups("Groups");
			assertLogger.log("Validation Passed < Selected Groups link from dropdown >");
		} catch (InterruptedException e) {

			assertLogger.log(e.getMessage());
		}
	}

	@And("validate group configuration page is displayed")
	public void validate_work_items_page_is_displayed() throws InterruptedException {
		Boolean titleval = groupsPage.gettitle().contains("Group Configuration");
		assertLogger.log("Validation Passed" + " with actual value as < " + titleval
				+ " > and expected value as < Group Configuration >");
	}
	
	
	@Then ("search for workgroup")
	public void searchForWorkgroup() throws InterruptedException {
		groupsPage.clickWorkgroupsTab();
		groupsPage.searchForWorkgroup(context.getMoreData().get("workgroup"));
	}
	
	@Then("save workgroup member id")
	public void save_workgroup_member_id() throws InterruptedException {
		String user = groupsPage.getuserFromWorkgroup();
		Map<String, String> mpMap = context.getMoreData();
		mpMap.put("empid", user);
		context.setMoreData(mpMap);
	}
	
 }