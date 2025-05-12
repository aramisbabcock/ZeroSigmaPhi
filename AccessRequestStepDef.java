package org.cucumbertaf.stepdefs;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.commons.math3.genetics.Fitness;
import org.cucumbertaf.pageobjects.AccessRequestPage;
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

public class AccessRequestStepDef extends BaseStepDef {


	public AccessRequestStepDef(TestContext context) {
		super(context);
	}

	
	@Then("open Access requests page")
	public void openAccessrequestspage() {
		try {
			accessRequestPage.clickonmywork();
			assertLogger.log("Clicked on My Works Button");
			accessRequestPage.clickonAccessRequest();
			assertLogger.log("Clicked on Access Request");
		} catch (InterruptedException e) {
			assertLogger.log(e.getMessage());
		}
	}
	
	@Then("search for access request")
	public void search_for_access_request() {
		try {
			accessRequestPage.searchAccessRequest(context.getStringvalue());
			
			int countOfAccessRequests = accessRequestPage.openDetails();
			assertLogger.assert_equals(countOfAccessRequests, 1, "Open the details page", "Expected 1 Access request" + "Actual cout: " + countOfAccessRequests);
		} catch (InterruptedException e) {
			assertLogger.log(e.getMessage());
		}
	}
	
	@And("extract interaction description")
	public void extractInteractionDescription() throws InterruptedException {
		String managerNameString =  accessRequestPage.getApproverDescription();
		
		Map<String, String> mpMap = context.getMoreData();
		mpMap.put("interactionDescription", managerNameString);
		context.setMoreData(mpMap);
	}
	
	@And("extract manager name")
	public void extractmanagername() throws InterruptedException {
		String managerNameString =  accessRequestPage.getApproverName();
		
		Map<String, String> mpMap = context.getMoreData();
		mpMap.put("fullName", managerNameString);
		context.setMoreData(mpMap);
	}
	
	
	@And("extract workgroup name")
	public void extractworkgroupname() throws InterruptedException {
		String managerNameString =  accessRequestPage.getApproverName();
		
		Map<String, String> mpMap = context.getMoreData();
		mpMap.put("workgroup", managerNameString);
		context.setMoreData(mpMap);
	}
	
	
	@And("extract workitem number")
	public void extract_workitem_number() throws InterruptedException {
		accessRequestPage.openManagerWorkitem();

		String managerWorkItemId = accessRequestPage.getWorkItemID();
		Map<String, String> mpMap = context.getMoreData();
		mpMap.put("workItem", managerWorkItemId);
		context.setMoreData(mpMap);
	}

	
	
	
}
