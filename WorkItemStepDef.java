package org.cucumbertaf.stepdefs;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.commons.math3.genetics.Fitness;
import org.cucumbertaf.pageobjects.WorkItemPage;
import org.cucumbertaf.testbase.BaseStepDef;
import org.cucumbertaf.testlib.TestContext;
import org.cucumbertaf.utils.PropertyUtil;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import net.bytebuddy.asm.Advice.This;

public class WorkItemStepDef extends BaseStepDef {
	boolean titleval;

	public WorkItemStepDef(TestContext context) {
		super(context);
	}

	@Then("click on my work link")
	public void click_on_my_work_link() {
		try {
			workitempage.clickonmywork();
			assertLogger.log("Validation Passed < Clicked on My Work link >");
		} catch (InterruptedException e) {

			assertLogger.log(e.getMessage());
		}

	}

	@Then("select work items link")
	public void select_work_items_link() {
		try {
			workitempage.selectworkitems(PropertyUtil.getProperty("myworktype"));
			assertLogger.log("Validation Passed < Selected Work Items link from dropdown >");
		} catch (InterruptedException e) {

			assertLogger.log(e.getMessage());
		}
	}

	@And("validate work items page is displayed")
	public void validate_work_items_page_is_displayed() throws InterruptedException {
		titleval = workitempage.gettitle().contains("Work Items");
		Assert.assertEquals(titleval, true, "Validation Failed");
		assertLogger.log("Validation Passed" + " with actual value as < " + titleval
				+ " > and expected value as < Work Items >");

	}

//	@Then("search work item")
//	public void search_manager_work_item() {
//		String curUserString = jsExecutor.executeScript("return SailPoint.CURR_USER_NAME").toString();
//		System.out.println(curUserString);
//		System.out.println(manager_eid);
//		if (curUserString.equalsIgnoreCase(manager_eid)) {
//			descriptionval = workitemmanagerval + ": " + fnameval + " " + lnameval;
//		} else if (curUserString.equalsIgnoreCase(workgroupuser_eid)) {
//			descriptionval = workitemworkgroupval + ": " + fnameval + " " + lnameval;
//		}
//
//		System.out.println(descriptionval);
//		try {
//			workitempage.searchworkitem(descriptionval);
//			workitempage.clicksearch();
//		} catch (InterruptedException e) {
//
//			assertLogger.log(e.getMessage());
//		}
//		desval = workitempage.getdescription();
//		Assert.assertEquals(desval, descriptionval, "Validation Failed");
//		assertLogger.log("Validation Passed" + " with actual value as < " + desval + " > and expected value as < "
//				+ descriptionval + " >");
//
//	}

	@Then("search work item")
	public void search_work_item() throws InterruptedException {
		String workItemId = context.getMoreData().get("workItem");

		try {
			workitempage.searchworkitem(workItemId);
			workitempage.clicksearch();
		} catch (InterruptedException e) {

			assertLogger.log(e.getMessage());
		}

		String descriptionString = workitempage.getdescription();
		assertLogger.log("Work item : " + descriptionString);

	}

	@Then("click on view button")
	public void click_on_view_button() {
		try {
			workitempage.clickview();
			assertLogger.log("Validation Passed < Clicked on View button >");
		} catch (InterruptedException e) {
			assertLogger.log(e.getMessage());
		}
	}

	@And("validate Approval page is displayed")
	public void validate_approval_page_is_displayed() throws InterruptedException {
		titleval = workitempage.gettitleApprovalPage().contains("Approval");
		Assert.assertEquals(titleval, true, "Validation Failed");
		assertLogger.log(
				"Validation Passed" + " with actual value as < " + titleval + " > and expected value as < Approval >");

	}

	@And("manager take action on workitem")
	public void take_action_on_workitem_manager() throws InterruptedException {
		String action = workitemmanageraction;

		switch (action) {
		case "approve":
			workitempage.clickapproveWorkitem();
			assertLogger.log("Validation Passed < Clicked on Approve button >");
			break;
		case "deny":
			workitempage.clickdenyWorkitem();
			assertLogger.log("Validation Passed < Clicked on Deny Button button >");
			break;
		}
	}

	@And("workgroup take action on workitem")
	public void workgroup_take_action_on_workitem_manager() throws InterruptedException {
		String action = workitemworkgroupaction;

		switch (action) {
		case "approve":
			workitempage.clickapproveWorkitem();
			assertLogger.log("Validation Passed < Clicked on Approve button >");
			break;
		case "deny":
			workitempage.clickdenyWorkitem();
			assertLogger.log("Validation Passed < Clicked on Deny Button button >");
			break;
		}
	}

	public void take_action_on_workitem(int count, String approver, String description) {
		String action = workitem_action[count-1];
		
		try{
			switch (action) {
			case "approve":
				workitempage.clickapproveWorkitem();
				assertLogger.log("Level :" + count + " Approver :" + approver + " <Clicked on Approve button >" + " Description :" + description);
				break;
			case "deny":
				workitempage.clickdenyWorkitem();
				assertLogger.log("Level :" + count + " Approver :" + approver + " <Clicked on Deny button >" + " Description :" + description);
				break;
			default :
				assertLogger.log("Validation Failed - " + "Level :" + count + " Approver :" + approver + " Recheck Actions");
				break;
			}
		} catch (InterruptedException e) {
			assertLogger.log(e.getMessage());
		}
		
	}

	@Then("confirm work item action")
	public void confirm_workitem_action() throws InterruptedException {
		workitempage.clickCompleteButton();
	}

	@Then("click on forward button")
	public void click_on_forward_button() {
		try {
			workitempage.clickWorkItemForward();
			assertLogger.log("Validation Passed < Clicked on Forward button >");
		} catch (InterruptedException e) {
			assertLogger.log(e.getMessage());
		}
	}

	@And("enter user to forward work item")
	public void enter_user_to_forward_to() throws InterruptedException {
		workitempage.fillForwardtoinput(forwardto_user);
		assertLogger.log("Validation Passed < filler the user in form >" + forwardto_user);

		List<WebElement> listofOptions = workitempage.getforwarduseroptions();

		for (int i = 0; i < listofOptions.size(); ++i) {
			if (listofOptions.get(i).getText().contains(forwardto_user)) {
				workitempage.chooseforwarduseroptions(i);
				assertLogger.log("Validation Passed < Clicked on user >");
			}
		}
	}

	@Then("submit forward form")
	public void submit_forward_form() {
		try {
			workitempage.submitforward();
			assertLogger.log("Validation Passed < Clicked on Forward button >");
		} catch (InterruptedException e) {
			assertLogger.log(e.getMessage());
		}
	}

}
