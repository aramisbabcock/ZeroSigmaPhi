package org.cucumbertaf.stepdefs;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.cucumbertaf.testbase.BaseStepDef;
import org.cucumbertaf.testlib.TestContext;
import org.cucumbertaf.utils.PropertyUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import com.google.common.util.concurrent.Uninterruptibles;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;


public class ManageUserAccessStepDef extends BaseStepDef{

	
	@FindBy(xpath = "//div[@id='gridview-1069']")
	private WebElement topTaskResult;
	
	public ManageUserAccessStepDef(TestContext context) {
		super(context);
	}
	
////////////////////////////////////////////////////////////////////Open Page to add entitlements to user/////////////////////////////
	@Then("click on quickinks")
	public void click_on_applications_link() {
		try {
			if(!accessrequest_type.contains("refresh"))
			{
				manageUserAccessPage.clickQuickLinks();
				assertLogger.log("Clicked on Quick Links Button");
			}
		} catch (InterruptedException e) {
			assertLogger.log(e.getMessage());
		}
	}
	
	@Then("identity Refresh")
	public void click_on_setup() throws InterruptedException {
		if(accessrequest_type.contains("refresh"))
		{
			if(env.contains("TDE"))
			{
				driver.get("https://a1icam.a1vdc.us.af.mil/identityiqTDE/monitor/tasks/viewTasks.jsf?resetTab=true");
			}
			else if(env.contains("PREPROD"))
			{
				driver.get("https://a1icam.a1vdc.us.af.mil/identityiqPREPROD/monitor/tasks/viewTasks.jsf?resetTab=true");
			}
			manageUserAccessPage.waitForElement(driver.findElement(By.xpath("//input[@id='tasksSearchField-inputEl']")));
			driver.findElement(By.xpath("//input[@id='tasksSearchField-inputEl']")).sendKeys("Refresh Identity Cube Concurrent");
			manageUserAccessPage.waitForElement(driver.findElement(By.id("tasksSearchField-inputEl")));
			driver.findElement(By.id("tasksSearchField-inputEl")).sendKeys(Keys.ENTER);
			manageUserAccessPage.clickRefresh();
			//driver.findElement(By.id("gridview-1026-bd-Identity")).click();
			manageUserAccessPage.waitForElement(driver.findElement(By.xpath("//input[@id='editForm:filter_str']")));
			driver.findElement(By.xpath("//input[@id='editForm:filter_str']")).clear();
			manageUserAccessPage.waitForElement(driver.findElement(By.xpath("//input[@id='editForm:filter_str']")));
			driver.findElement(By.xpath("//input[@id='editForm:filter_str']")).sendKeys("name==\""+eidvalue+"\"");
			manageUserAccessPage.waitForElement(driver.findElement(By.cssSelector("body")));
			driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL, Keys.END);
			manageUserAccessPage.waitForElement(driver.findElement(By.id("editForm:validateBeforeExecuteButton")));
			driver.findElement(By.id("editForm:validateBeforeExecuteButton")).click();
			Thread.sleep(20000);
			if(env.contains("TDE"))
			{
				driver.get("https://a1icam.a1vdc.us.af.mil/identityiqTDE/monitor/tasks/viewTasks.jsf?resetTab=true");
			}
			else if(env.contains("PREPROD"))
			{
				driver.get("https://a1icam.a1vdc.us.af.mil/identityiqPREPROD/monitor/tasks/viewTasks.jsf?resetTab=true");
			}
			manageUserAccessPage.clickTaskList();
			//driver.findElement(By.xpath("//button[@id='tab-1091-btnEl']")).click();
			manageUserAccessPage.waitForElement(driver.findElement(By.xpath("//input[@id='resultsSearchField-inputEl']")));
			driver.findElement(By.xpath("//input[@id='resultsSearchField-inputEl']")).sendKeys("Refresh Identity Cube Concurrent", Keys.ENTER);
			Thread.sleep(4000);
			//driver.findElement(By.id("gridview-1069")).click();
			//Actions builder = new Actions(driver);
		    //builder.moveToElement(topTaskResult,15,15).click().build().perform();
			//Thread.sleep(1000);
			//driver.findElement(By.xpath("//div[contains(@text, 'Success')]")).click();
		}
	}
	
	@Then("check identity cube pre")
	public void check_identity_cube_pre() throws InterruptedException
	{
		driver.get(identitycubeURL);
		Thread.sleep(3000);
		driver.findElement(By.id("tab-1027-btnEl")).click();
		Thread.sleep(5000);
		if(accessrequest_type.contains("create"))
		{
			try
			{
				if(driver.getPageSource().contains(systemname))
				{
					System.err.println("Failure/Choose a User without an Account (Create)");
					driver.findElement(By.id("SigmaPhiFailure"));
				}
			}catch(NoSuchElementException e)
			{
				System.err.println("Success");
			}
		}
		else if(accessrequest_type.contains("modify")||accessrequest_type.contains("remove"))
		{
			try
			{
				if(!driver.getPageSource().contains(systemname))
				{
					System.err.println("Failure/Choose a User an Account (Modify)");
					driver.findElement(By.id("SigmaPhiFailure"));
				}
			}catch(NoSuchElementException e)
			{
				System.err.println("Success");
			}
		}
		//Remove
		//Perform Identity Request Maintenance
		
		//driver.findElement(By.xpath("//button[contains(text(),"+systemname+")]")).click();
		//Thread.sleep(1000);
	}
	
	@Then("check identity cube post")/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public void check_identity_cube_post() throws InterruptedException
	{
		//needs a rewrite
		if(!accessrequest_type.contains("break"))
		{
			driver.get(identitycubeURL);
			Thread.sleep(4000);
			driver.findElement(By.id("tab-1027-btnEl")).click();
			Thread.sleep(4000);
			if(accessrequest_type.contains("create")&&!accessrequest_type.contains("succ"))
			{
				try
				{
					if(driver.getPageSource().contains(systemname))
					{
						System.err.println("Failure/Choose a User without an Account (Create No Success)");
						driver.findElement(By.id("SigmaPhiFailure"));
					}
				}catch(NoSuchElementException e)
				{
					System.err.println("Success");
				}
			}
			if(accessrequest_type.contains("create")&&accessrequest_type.contains("succ"))
			{
				try
				{//add ents
					if(!driver.getPageSource().contains(systemname))
					{
						System.err.println("Failure/Choose a User without an Account (Create Success)");
						driver.findElement(By.id("SigmaPhiFailure"));
					}
				}catch(NoSuchElementException e)
				{
					System.err.println("Success");
				}
			}
			if(accessrequest_type.contains("modify")&&!accessrequest_type.contains("succ"))
			{
				try
				{
					accessvals = Arrays.stream(accessval.trim().split("\\|")).map(String::trim).toArray(String[]::new);
					for(int i = 0; i<accessvals.length; i++)
					{
						if(!driver.getPageSource().contains(systemname)||driver.getPageSource().contains(accessvals[i]))
						{
							System.err.println("Failure/Choose a User an Account (Modify No Success)");
							driver.findElement(By.id("SigmaPhiFailure"));
						}
					}

				}catch(NoSuchElementException e)
				{
					System.err.println("Success");
				}
			}
			if(accessrequest_type.contains("modify")&&accessrequest_type.contains("succ"))
			{
				
				//need to rewrite at some point, limit to only two ents, overhaul for more
				try
				{
					accessvals = Arrays.stream(accessval.trim().split("\\|")).map(String::trim).toArray(String[]::new);
					Boolean[] appdeny = new Boolean[accessvals.length];
					if(accessrequest_type.contains("add"))
					{
						for(int j = 0; j<appdeny.length; j++)
						{
						
							if(ent0Man.contains("deny all")&&ent0SecMan.contains("deny all"))
							{
								
							}
							else if(ent0Man.contains("approve all")&&ent0SecMan.contains("approve all"))
							{
								appdeny[j]=true;	
							}
							else if(ent0Man.contains("approve")||ent1Man.contains("approve"))
							{
								if((ent1Man.contains("deny")||ent1SecMan.contains("deny"))&&j==0)
								{
									appdeny[j]=true;
								}
								else if((ent0Man.contains("deny")||ent0SecMan.contains("deny"))&&j==1)
								{
									appdeny[j]=true;
								}
							}
							//develop better system later for x entitlements, not just two
						}
						for(int i = 0; i<accessvals.length; i++)
						{
							if(driver.getPageSource().contains(systemname)&&driver.getPageSource().contains(accessvals[i])&&appdeny[i])
							{
							
							}
							else
							{
								System.err.println("Failure/Choose a User an Account (Add Modify Success)");
								driver.findElement(By.id("SigmaPhiFailure"));
							}
						}
					}
					
					accessRemoveVals = Arrays.stream(accessRemoveVal.trim().split("\\|")).map(String::trim).toArray(String[]::new);
					Boolean[] appdenyRem = new Boolean[accessRemoveVals.length];
					if(accessrequest_type.contains("remove"))
					{
						for(int j = 0; j<appdeny.length; j++)
						{
						
							if(ent0Man.contains("deny all")&&ent0SecMan.contains("deny all"))
							{
								
							}
							else if(ent0Man.contains("approve all")&&ent0SecMan.contains("approve all"))
							{
								appdenyRem[j]=true;	
							}
							else if(ent0Man.contains("approve")||ent1Man.contains("approve"))
							{
								if((ent1Man.contains("deny")||ent1SecMan.contains("deny"))&&j==0)
								{
									appdenyRem[j]=true;
								}
								else if((ent0Man.contains("deny")||ent0SecMan.contains("deny"))&&j==1)
								{
									appdenyRem[j]=true;
								}
							}
							//develop better system later for x entitlements, not just two
						}
						for(int i = 0; i<accessvals.length; i++)
						{
							if(driver.getPageSource().contains(systemname)&&!driver.getPageSource().contains(accessRemoveVals[i])&&appdenyRem[i])
							{
			
							}
							else
							{
								System.err.println("Failure/Choose a User an Account (Remove Modify Success)");
								driver.findElement(By.id("SigmaPhiFailure"));
							}
						}
					}

				}catch(NoSuchElementException e)
				{
					System.err.println("Success");
				}
			}
		
		}
		//driver.findElement(By.xpath("//button[contains(text(),"+systemname+")]")).click();
		//Thread.sleep(1000);
	}

	@Then("open Manage User Access")
	public void click_on_manage_user_access() {
		if(accessrequest_type.contains("entitlement"))
		{
			try {
				manageUserAccessPage.clickManageAccess();
				manageUserAccessPage.clickManageUserAccess();
				assertLogger.log("Clicked on manage user access page");
			} catch (InterruptedException e) {
				assertLogger.log(e.getMessage());
			}
		}
		
		
	}
	
	@Then("open Manage Accounts")
	public void click_on_manage_accounts() {
		if(accessrequest_type.contains("enable")||accessrequest_type.contains("disable")||accessrequest_type.contains("delete"))
		{
			try {
				manageUserAccessPage.clickManageAccess();
				manageUserAccessPage.clickManageAccounts();
				assertLogger.log("Clicked on manage accounts page");
			} catch (InterruptedException e) {
				assertLogger.log(e.getMessage());
			}
		}
	}
	
	@And("validate manage user access page is displayed")
	public void validate_manage_user_access_page_is_displayed() throws InterruptedException {
		
		if(accessrequest_type.contains("entitlement"))
		{
			String pageTitle = manageUserAccessPage.getPageTitle();
			assertLogger.assert_equals(pageTitle.contains("Manage User Access"), true, "Manage User Access page is displayed as expected",
					"Manage User Access page is not displayed as expected");
		}
	
	}
	
	@And("validate manage accounts page is displayed")
	public void validate_manage_accounts_page_is_displayed() throws InterruptedException {
		
		if(accessrequest_type.contains("enable")||accessrequest_type.contains("disable")||accessrequest_type.contains("delete"))
		{
			String pageTitle = manageUserAccessPage.getPageTitle();
			assertLogger.assert_equals(pageTitle.contains("Manage Accounts"), true, "Manage Accounts page is displayed as expected",
					"Manage Accounts page is not displayed as expected");
		}
	}
	
	////////////////////////////////////////////////////////////////////approve reject items//////////////////////////////////////////////////
	@Then("click on Approve/Reject button Item 0")
	public void click_on_approve_reject_button_item0(String type) {
		try {
			Thread.sleep(3000);
			if(type.equals("approve all"))
			{
				manageUserAccessPage.clickonapproveallButton();
				assertLogger.log("Clicked on Approve All Button");
			}
			else if(type.equals("deny all"))
			{
				manageUserAccessPage.clickonrejectallButton();
				assertLogger.log("Clicked on Reject All Button");
			}
			else if(type.equals("approve"))
			{
				manageUserAccessPage.clickonapproveButtonItem0();
				assertLogger.log("Clicked on Approve Button for Item 0");
			}
			else if(type.equals("deny"))
			{
				manageUserAccessPage.clickonrejectButtonItem0();
				assertLogger.log("Clicked on Reject Button for Item 0");
			}
	
			
		} catch (InterruptedException e) {
			assertLogger.log(e.getMessage());
		}
	}
	
	@Then("click on Approve/Reject button Item 1")
	public void click_on_approve_reject_button_item1(String type1) {
		try {
			Thread.sleep(3000);
			if(type1.equals("approve all"))
			{
				manageUserAccessPage.clickonapproveallButton();
				assertLogger.log("Clicked on Approve All Button");
			}
			else if(type1.equals("deny all"))
			{
				manageUserAccessPage.clickonrejectallButton();
				assertLogger.log("Clicked on Reject All Button");
			}
			else if(type1.equals("approve"))
			{
				manageUserAccessPage.clickonapproveButtonItem1();
				assertLogger.log("Clicked on Approve Button for Item 1");
			}
			else if(type1.equals("deny"))
			{
				manageUserAccessPage.clickonrejectButtonItem1();
				assertLogger.log("Clicked on Reject Button for Item 1");
			}
			
		} catch (InterruptedException e) {
			assertLogger.log(e.getMessage());
		}
	}
	
	////////////////////////////////////////////////////////////////////delete above possible//////////////////////////////////////////////////
	
	@Then("click on Approve/Reject button Item X")
	public void click_on_approve_reject_button_itemX(String type, String ent) {
		try {
			Thread.sleep(3000);
			if(type.equals("approve all"))
			{
				manageUserAccessPage.clickonapproveallButton();
				assertLogger.log("Clicked on Approve All Button for all Entitlements");
			}
			else if(type.equals("deny all"))
			{
				manageUserAccessPage.clickonrejectallButton();
				assertLogger.log("Clicked on Reject All Button for all Entitlements");
			}
			else if(type.equals("approve"))
			{
				manageUserAccessPage.clickonapproveButtonItemX(ent,accessrequest_type);
				assertLogger.log("Clicked on Approve Button for "+ent);
			}
			else if(type.equals("deny"))
			{
				manageUserAccessPage.clickonrejectButtonItemX(ent,accessrequest_type);
				assertLogger.log("Clicked on Reject Button for "+ent);
			}
			else if(type.equals("na"))
			{
				assertLogger.log("NA for Entitlement: "+ent);
			}
	
			
		} catch (InterruptedException e) {
			assertLogger.log(e.getMessage());
		}
	}
	
	///////////////////////////////////////////////////////////////////Approval Flow search for User///////////////////////////////////
	@Then("click on complete access button")
	public void click_on_complete() {
		try {
			//Thread.sleep(3000);
			manageUserAccessPage.clickcompleteaccessButton();
			assertLogger.log("Clicked on Complete Access Button");
		} catch (InterruptedException e) {
			assertLogger.log(e.getMessage());
		}
	}
	
	@Then("search for user")
	public void search_user_in_manageuseraccessPage() throws InterruptedException {
		Thread.sleep(3000);
		try {
			manageUserAccessPage.inputeid(eidvalue, accessrequest_type);
			//Thread.sleep(2000);
			manageUserAccessPage.clicksearchUserButton(accessrequest_type);
			
		} catch (InterruptedException e) {
			assertLogger.log(e.getMessage());
		}
	}

	@And("validate user in search result")
	public void validate_user_in_search_result() throws InterruptedException {
		if(accessrequest_type.contains("entitlement"))
		{
			List<WebElement> userNamesElements = manageUserAccessPage.getuserFullNames();
			Thread.sleep(4000);
			for (int i = 0; i < userNamesElements.size(); ++i) {
				if (userNamesElements.get(i).getText().contains(fnameval)
						&& userNamesElements.get(i).getText().contains(lnameval)) {
					manageUserAccessPage.clickCheckboxofIndex(i);
					assertLogger.log("Selected the user :" + userNamesElements.get(i).getText());
					break;
				}
			}
		}

	}
	
	@Then("input user mail address")
	public void input_user_mail_address() {
		try {
			manageUserAccessPage.inputmailaddress(useremailaddress);
			assertLogger.log("Mail address has been given as input");
		} catch (InterruptedException e) {
			assertLogger.log(e.getMessage());
		}
	}
		
	@Then("click on next")
	public void click_on_next_after_user() {
		if(accessrequest_type.contains("entitlement"))
		{
			try {
				manageUserAccessPage.clickNextButton();
				assertLogger.log("Clicked on Next Button");
			} catch (InterruptedException e) {
				assertLogger.log(e.getMessage());
			}
		}
	}
	
	@Then("click on manage")
	public void click_on_manage_after_user() {
		if(accessrequest_type.contains("enable")||accessrequest_type.contains("disable")||accessrequest_type.contains("delete"))
		{
			try {
				manageUserAccessPage.clickmanageButton();
				//driver.findElement(By.xpath("//button[@aria-label='Manage Accounts for "+eidvalue+"']")).click();
				assertLogger.log("Clicked on Next Button");
			} catch (InterruptedException e) {
				assertLogger.log(e.getMessage());
			}
		}
	}
	
	@Then("click on delete disable enable")
	public void click_on_system_enable_disable_delete() throws InterruptedException {
		if(accessrequest_type.contains("enable")||accessrequest_type.contains("disable")||accessrequest_type.contains("delete"))
		{
			manageUserAccessPage.waitForElement(driver.findElement(By.xpath("//button[contains(@aria-label,'"+systemname+"')]")));
			driver.findElement(By.xpath("//button[contains(@aria-label,'"+systemname+"')]")).click();
			if(accessrequest_type.contains("enable"))
			{
				manageUserAccessPage.waitForElement(driver.findElement(By.xpath("//button[contains(@aria-label, 'Enable')]")));
				driver.findElement(By.xpath("//button[contains(@aria-label, 'Enable')]")).click();
			}
			else if(accessrequest_type.contains("disable"))
			{
				manageUserAccessPage.waitForElement(driver.findElement(By.xpath("//button[contains(@aria-label, 'Disable')]")));
				driver.findElement(By.xpath("//button[contains(@aria-label, 'Disable')]")).click();
			}
			else if(accessrequest_type.contains("delete"))
			{
				manageUserAccessPage.waitForElement(driver.findElement(By.xpath("//button[contains(@aria-label, 'Delete')]")));
				driver.findElement(By.xpath("//button[contains(@aria-label, 'Delete')]")).click();
			}
			manageUserAccessPage.waitForElement(driver.findElement(By.xpath("//button[@id='confirmBtn']")));
			driver.findElement(By.xpath("//button[@id='confirmBtn']")).click();
			manageUserAccessPage.waitForElement(driver.findElement(By.xpath("//button[contains(@id, 'btn-comment')]")));
			driver.findElement(By.xpath("//button[contains(@id, 'btn-comment')]")).click();
			manageUserAccessPage.inputGlobalComment(comment);
			//driver.findElement(By.xpath("//button[contains(@class, 'btn  btn-info btn-default')]")).click();
			manageUserAccessPage.clickSubmitButton();	
		}
	}
	
	@Then("approve delete disable enable")
	public void approve_system_enable_disable_delete() {
		if(accessrequest_type.contains("enable")||accessrequest_type.contains("disable")||accessrequest_type.contains("delete"))
		{
			driver.findElement(By.xpath("//li/a[contains(text(), 'My Work')]")).click();
			driver.findElement(By.xpath("//ul/li/a[contains(text(), 'Work Items')]")).click();
			
		}
	}
	
///////////////////////////////////////////////////////////////////Approval Flow search for Ents///////////////////////////////////
	@Then("Search and click on entitlement")
	public void Search_and_click_on_entitlement() throws InterruptedException {
		if(accessrequest_type.contains("entitlement"))
		{
			Thread.sleep(2000);
			if(accessrequest_type.contains("add"))
			{
				accessvals = Arrays.stream(accessval.trim().split("\\|")).map(String::trim).toArray(String[]::new);
				for (String s: accessvals) {
					try {
			
						//manageUserAccessPage.clickFilter();
						//manageUserAccessPage.inputFilterItems(systemname, "add");
						//driver.findElement(By.xpath("//button[@id='itemsFilterPanelApplyBtn']")).click();
						//Thread.sleep(1000);
						//manageUserAccessPage.clicksearchAccessButton();
						//Thread.sleep(3000);
						//
						manageUserAccessPage.inputEntitlement(s);
						manageUserAccessPage.clicksearchAccessButton();	
					} catch (InterruptedException e) {
						assertLogger.log(e.getMessage());
					}
					//
					try {
						List<WebElement> entitlementNamesElements = manageUserAccessPage.getuserFullNames();
						for (int i = 0; i < entitlementNamesElements.size(); ++i) {
							if (entitlementNamesElements.get(i).getText().contains(s)) {
								manageUserAccessPage.clickCheckboxofIndex(i);
								assertLogger.log("Selected the Entitlement :" + entitlementNamesElements.get(i));
								break;
							}
						}
					}catch (InterruptedException e){
						assertLogger.log(e.getMessage());
					}
					
				}
			}
			if(accessrequest_type.contains("remove"))
			{
				Thread.sleep(3000);
				accessRemoveVals = Arrays.stream(accessRemoveVal.trim().split("\\|")).map(String::trim).toArray(String[]::new); 
				for (String s: accessRemoveVals) {
					try {
						//manageUserAccessPage.clickremoveaccessbutton();
						//Thread.sleep(10000);
						//manageUserAccessPage.clickFilter();
						//manageUserAccessPage.inputFilterItems(systemname, "remove");
						//driver.findElement(By.xpath("//button[@id='itemsFilterPanelApplyBtn']")).click();
						//Thread.sleep(1000);
						//manageUserAccessPage.clicksearchRemoveAccessButton();
						//Thread.sleep(3000);
						//
						manageUserAccessPage.inputRemoveEntitlement(s);
						manageUserAccessPage.clicksearchRemoveAccessButton();
					} catch (InterruptedException e) {
						assertLogger.log(e.getMessage());
					}

					try {
						List<WebElement> entitlementNamesElements = manageUserAccessPage.getuserFullNames();
						for (int i = 0; i < entitlementNamesElements.size(); ++i) {
							if (entitlementNamesElements.get(i).getText().contains(s)) {
								manageUserAccessPage.clickCheckboxofIndex(i);
								assertLogger.log("Selected the Entitlement :" + entitlementNamesElements.get(i));
								break;
							}
						}
					}catch (InterruptedException e){
						assertLogger.log(e.getMessage());
					}
				}
			}
		}
		
		
	}
	

	//////////////////////////////////////////////////////////////////////Comments (need to work on)///////////////////////////
	
	@Then("enter comments") //click on multiple comment buttons?
	public void enter_comments_button() {
		if(accessrequest_type.contains("entitlement"))
		{
			accessvals = Arrays.stream(accessval.trim().split("\\|")).map(String::trim).toArray(String[]::new);
			//List<WebElement> elements = driver.findElements(By.xpath("//button[contains(@aria-label, 'Comment for')]"));
			for (String s: accessvals) {
				try {
					manageUserAccessPage.waitForElement(driver.findElement(By.xpath("//button[contains(@aria-label,'Comment for "+s+"')]")));
					driver.findElement(By.xpath("//button[contains(@aria-label,'Comment for "+s+"')]")).click();
					//Thread.sleep(2000);
					input_global_comment();
					assertLogger.log("Clicked on "+s+" Comment Button");
				}catch (InterruptedException e){
					assertLogger.log(e.getMessage());
				}
			}		
		}
	}
		
	
	
	/*@Then("click on comment button") //click on multiple comment buttons?
	public void click_on_global_comment_button() {
		try {
			manageUserAccessPage.clickGlobalCommentButton();
			assertLogger.log("Clicked on Comment Button");
		} catch (InterruptedException e) {
			assertLogger.log(e.getMessage());
		}
	}*/
	
	
	@And("enter a comment and save") //click on multiple comment buttons?
	public void input_global_comment() throws InterruptedException{
		manageUserAccessPage.inputGlobalComment(comment+" for "+systemname);
		manageUserAccessPage.clickGlobalCommentSaveButton();
	}
	

	
	@Then("click on complete form")
	public void click_on_complete_form() {
		if(accessrequest_type.contains("entitlement"))
		{
			try {
				manageUserAccessPage.clickcompleteButton();
				assertLogger.log("Clicked on Complete Form Button");
			} catch (InterruptedException e) {
				assertLogger.log(e.getMessage());
			}
		}
	}
	
	@Then("click on ok form button")
	public void click_on_ok_form_button() {
		try {
			manageUserAccessPage.clickokformButton();
			assertLogger.log("Clicked on Ok Form Button");
		} catch (InterruptedException e) {
			assertLogger.log(e.getMessage());
		}	
	}
	/////////////////////////////////////////////////////////////Attributes, depends on App and Create/Modify)///////////////////
	@And("enter attributes")
	public void input_attributes() throws InterruptedException{
		Thread.sleep(3000);
		/*Template
		if(driver.getPageSource().contains("xpath def key word")&&driver.getPageSource().contains("Question"))
		{
			manageUserAccessPage.inputx(BaseStepDef def);
			assertLogger.log("[attribute] has been selected");
			i=1;
		}
		*/
		if(accessrequest_type.contains("entitlement")&&!accessrequest_type.contains("break"))
		{
			int i = 0;
			//AFIPPS
				//NA
			//AFM
			if(driver.getPageSource().contains("contractExpirationDate")&&driver.getPageSource().contains("Question")&&systemname.contains("AFM"))
			{
				manageUserAccessPage.inputcontractExpirationDate(contractExpirationDate);
				assertLogger.log("contract expiration date has been selected");
				i=1;
			}
			//AFPROMS
			if(driver.getPageSource().contains("mpf")&&driver.getPageSource().contains("Question")&&systemname.contains("AFPROMS"))
			{
				manageUserAccessPage.inputmpfLevelID(mpfLevelID);
				assertLogger.log("MPF Level ID has been selected");
				i=1;
			}
			if(driver.getPageSource().contains("mgmtLevel")&&driver.getPageSource().contains("Question")&&systemname.contains("AFPROMS"))
			{
				manageUserAccessPage.inputmanagementLevelID(managementLevelID);
				assertLogger.log("Management Level ID has been selected");
				i=1;
			}
			if(driver.getPageSource().contains("boardType")&&driver.getPageSource().contains("Question")&&systemname.contains("AFPROMS"))
			{
				manageUserAccessPage.inputboardType(boardType);
				assertLogger.log("Board Type has been selected");
				i=1;
			}
			//ALMSS
			if(driver.getPageSource().contains("Type")&&driver.getPageSource().contains("Question")&&systemname.contains("ALMSS"))
			{
				manageUserAccessPage.inputTypelabel(typeLabel);
				assertLogger.log("Type-Label has been selected");
				i=1;
			}
			if(driver.getPageSource().contains("IATraining")&&driver.getPageSource().contains("Question")&&systemname.contains("ALMSS"))
			{
				manageUserAccessPage.inputIATraining(iaTraining);
				assertLogger.log("IA Training has been selected");
				i=1;
			}
			//CON-IT
			if(driver.getPageSource().contains("supervisorEmail")&&driver.getPageSource().contains("Question")&&systemname.contains("CON-IT"))
			{
				manageUserAccessPage.inputsupervisorEmail(supervisorEmail);
				assertLogger.log("Supervisor Email has been selected");
				i=1;
			}
			if(driver.getPageSource().contains("fpdsngID")&&driver.getPageSource().contains("Question")&&systemname.contains("CON-IT"))
			{
				manageUserAccessPage.inputfpdsngID(fpdsngID);
				assertLogger.log("FPDSNG ID has been selected");
				i=1;
			}
			if(driver.getPageSource().contains("grade")&&driver.getPageSource().contains("Question")&&systemname.contains("CON-IT"))
			{
				manageUserAccessPage.inputgrade(grade);
				assertLogger.log("grade has been selected");
				i=1;
			}
			//EBIS
				//NA
			//EESOH-MIS
			if(driver.getPageSource().contains("nationalGuardReserveFlag")&&driver.getPageSource().contains("Question")&&systemname.contains("EESOH-MIS"))
			{
				manageUserAccessPage.inputnationalGuardReserveFlag(nationalGuardReserveFlag);
				assertLogger.log("National Guard Reserve Flag has been selected");
				i=1;
			}
			if(driver.getPageSource().contains("contractNumber")&&driver.getPageSource().contains("Question")&&systemname.contains("EESOH-MIS"))
			{
				manageUserAccessPage.inputcontractNumber(contractNumber);
				assertLogger.log("Contract Number has been selected");
				i=1;
			}
			if(driver.getPageSource().contains("contractorName")&&driver.getPageSource().contains("Question")&&systemname.contains("EESOH-MIS"))
			{
				manageUserAccessPage.inputcontractorName(contractorName);
				assertLogger.log("Contractor Name has been selected");
				i=1;
			}
			if(driver.getPageSource().contains("contractStartDate")&&driver.getPageSource().contains("Question")&&systemname.contains("EESOH-MIS"))
			{
				manageUserAccessPage.inputcontractStartDate(contractStartDate);
				assertLogger.log("Contract Start Date has been selected");
				i=1;
			}
			if(driver.getPageSource().contains("contractEndDate")&&driver.getPageSource().contains("Question")&&systemname.contains("EESOH-MIS"))
			{
				manageUserAccessPage.inputcontractEndDate(contractEndDate);
				assertLogger.log("Contract End Date has been selected");
				i=1;
			}
			//FMSuite
			if(driver.getPageSource().contains("jobTitle")&&driver.getPageSource().contains("Question")&&systemname.contains("FMSuite"))
			{
				manageUserAccessPage.inputjobtitle(jobtitle);
				assertLogger.log("job title has been selected");
				i=1;
			}
			if(driver.getPageSource().contains("rank")&&driver.getPageSource().contains("Question")&&systemname.contains("FMSuite"))
			{
				manageUserAccessPage.inputrank(rank);
				assertLogger.log("rank has been selected");
				i=1;
			}
			if(driver.getPageSource().contains("base")&&driver.getPageSource().contains("Question")&&systemname.contains("FMSuite"))
			{
				manageUserAccessPage.inputbase(base);
				assertLogger.log("base has been selected");
				i=1;
			}
			//ILS-S
				//See Multiple Systems
			//JOCASII-****
				//NA		
			//REMIS
				//NA		
			//RTS
				//NA
			//Multiple Systems
			if(driver.getPageSource().contains("phone")&&driver.getPageSource().contains("Question")&&
					(systemname.contains("CON-IT")||systemname.contains("EESOH-MIS")||systemname.contains("FMSuite")||systemname.contains("ILS-S")))//CON-IT, EESOH-MIS, FMSuite, ILS-S
			{
				manageUserAccessPage.inputphonecomm(phonecomm);
				assertLogger.log("phone number has been selected");
				i=1;
			}
			//Final Check
			if(i==1)
			{
				click_on_ok_form_button();
			}
		}	
	}
	
	//////////////////anything above this comment and below the previous needs to be added to for adding new systems to function/////////////////
	
	
	@Then("check for violations")
	public void violations_check() throws InterruptedException{
		if(accessrequest_type.contains("entitlement"))
			{
			Thread.sleep(5000);
			if(driver.getPageSource().contains("submitWithViolationsBtn"))
			{
				manageUserAccessPage.submitwithviolations();
				manageUserAccessPage.inputGlobalComment(comment);
				manageUserAccessPage.clickGlobalCommentSaveButton();
			}
		}
	}
	
	////////////////////////////////////////////////////////////Input manager, sec manager//////////////////////////////////////////////
	
	@And("enter manager")
	public void input_manager() throws InterruptedException{
		if(accessrequest_type.contains("entitlement")&&!accessrequest_type.contains("same")&&!accessrequest_type.contains("break"))
		{
			manageUserAccessPage.inputmanager(manager);
			assertLogger.log("manager has been selected");
		}
	}
	
	@And("enter security manager")
	public void input_securitymanager() throws InterruptedException{
		if(accessrequest_type.contains("entitlement")&&!accessrequest_type.contains("same")&&!accessrequest_type.contains("break"))
		{
			manageUserAccessPage.inputsecuritymanager(securitymanager);
			assertLogger.log("security manager has been selected");
		}
	}
	
	@Then("click on ok button in manage user access page")
	public void click_on_ok_button() {
		try {
			manageUserAccessPage.clickokButton();
			assertLogger.log("Clicked on Ok Button");
		} catch (InterruptedException e) {
			assertLogger.log(e.getMessage());
		}
	}
	
	@Then("click on Submit form button")
	public void click_on_submit_form_button() {
		if(accessrequest_type.contains("entitlement"))
		{
			try {
				manageUserAccessPage.clickonsubmitformButton();
				assertLogger.log("Clicked on Submit Form Button");
			} catch (InterruptedException e) {
				assertLogger.log(e.getMessage());
			}
		}
	}
	
	
	
	
	@Then("submit access request")
	public void submit_access_request() throws InterruptedException{
		if(accessrequest_type.contains("entitlement"))
		{
			manageUserAccessPage.clickSubmitButton();
		}
		
	}
	
//////////////////////////////////////////////////////////////////////////Approving Requests////////////////////////////////////////////
	
	@Then("Cancel if applicable")
	public void cancel_if_applicable() throws InterruptedException
	{
		if(accessrequest_type.contains("cancel"))
		{
			if(env.contains("TDE"))
			{
				driver.get("https://a1icam.a1vdc.us.af.mil/identityiqTDE/identityRequest/identityRequest.jsf#/requests");
			}
			else if(env.contains("PRE-PROD"))
			{
				//need link
			}
			manageUserAccessPage.waitForElement(driver.findElement(By.xpath("//input[@id='cardListSearchInput']")));
			driver.findElement(By.xpath("//input[@id='cardListSearchInput']")).sendKeys(eidvalue);
			driver.findElement(By.xpath("//input[@id='cardListSearchInput']")).sendKeys(Keys.ENTER);
			manageUserAccessPage.waitForElement(driver.findElement(By.xpath("//button[contains(@id, 'cancelRequestBtn')]")));
			driver.findElement(By.xpath("//button[contains(@id, 'cancelRequestBtn')]")).click();
			manageUserAccessPage.waitForElement(driver.findElement(By.xpath("//textarea[@id='commentTextArea']")));
			driver.findElement(By.xpath("//textarea[@id='commentTextArea']")).sendKeys(comment);
			driver.findElement(By.xpath("//textarea[@id='commentTextArea']")).sendKeys(Keys.TAB, Keys.TAB, Keys.ENTER);
		}
	}
	
	@And("Same if applicable")
	public void same_if_applicable() throws InterruptedException
	{
		if(accessrequest_type.contains("same"))
		{
			manageUserAccessPage.inputmanager(manager);
			manageUserAccessPage.inputsecuritymanager(securitymanager);
			click_on_submit_form_button();
			manageUserAccessPage.inputmanager(username);
			manageUserAccessPage.inputsecuritymanager(username);
			
		}
	}
	
	@Then("Approval Process")
	public void approval_process() throws InterruptedException
	{	
		
		if(accessrequest_type.contains("entitlement")&&!accessrequest_type.contains("cancel")&&!accessrequest_type.contains("same")
				&&!accessrequest_type.contains("break"))
		{
			String[] temp = Arrays.stream(entAddApproveDeny.trim().split("\\|")).map(String::trim).toArray(String[]::new);
			String[] tempRem = Arrays.stream(entRemoveApproveDeny.trim().split("\\|")).map(String::trim).toArray(String[]::new);
			entAddApproveDenyList = new String[numberOfApprovers][1];
			entRemoveApproveDenyList = new String[numberOfApprovers][1];
			//Thread.sleep(3000);
			int i = 0;
			int j = 0;
			if(accessrequest_type.contains("add")||accessrequest_type.contains("remove"))
			{
				if(accessrequest_type.contains("add"))
				{
					accessvals = Arrays.stream(accessval.trim().split("\\|")).map(String::trim).toArray(String[]::new);
					entAddApproveDenyList = new String[numberOfApprovers][accessvals.length];
				}
				if(accessrequest_type.contains("remove"))
				{
					accessRemoveVals = Arrays.stream(accessRemoveVal.trim().split("\\|")).map(String::trim).toArray(String[]::new);
					entRemoveApproveDenyList = new String[numberOfApprovers][accessRemoveVals.length];
				}
				for(int row = 0; row < numberOfApprovers; row++)
				{
					for(int col = 0; col < entAddApproveDenyList[row].length; col++)
					{
							entAddApproveDenyList[row][col] = temp[i];
							i++;
					}
				}
				for(int row = 0; row < numberOfApprovers; row++)
				{
					for(int col = 0; col < entRemoveApproveDenyList[row].length; col++)
					{
							entRemoveApproveDenyList[row][col] = tempRem[j];
							j++;
					}
				}
				int larger = (temp.length > tempRem.length) ? temp.length/numberOfApprovers:tempRem.length/numberOfApprovers;
				for(int row = 0; row < 2; row++)
				{
					for(int col = 0; col < larger; col++)
					{
						
						if((entAddApproveDenyList[row][0].contains("deny all")||entRemoveApproveDenyList[row][0].contains("deny all"))&&col==0)
						{
							manageUserAccessPage.clickonrejectallButton();	
							//need to add more
						}
						else if((entAddApproveDenyList[row][0].contains("approve all")||entRemoveApproveDenyList[row][0].contains("approve all"))&&col==0)
						{
							manageUserAccessPage.clickonapproveallButton();	
						}
						else
						{
						
							if(temp.length > tempRem.length)//if adds ents array > remove ents array
							{
								System.err.println(String.format("column:%d \ntempRem: %d \ntempRem/numOfApp: %d",col,tempRem.length,tempRem.length/numberOfApprovers));
								if(col<tempRem.length/numberOfApprovers)
								{
									remove_Ents_Click(row,col);
								}
								add_Ents_Click(row,col);
							}
							else if(temp.length < tempRem.length)
							{
								if(col<temp.length/numberOfApprovers)
								{
									add_Ents_Click(row,col);
								}
								remove_Ents_Click(row,col);
							}
							else
							{
								add_Ents_Click(row,col);
								remove_Ents_Click(row,col);
							}
						}
						
					}	
					click_on_complete();
				}
				//3rd level approver onward
				Thread.sleep(10000);
				driver.get("https://a1icam.a1vdc.us.af.mil/identityiqTDE/workitem/workItems.jsf#/workItems");
				//driver.get("https://a1icam.a1vdc.us.af.mil/identityiqPREPROD/workitem/workItems.jsf#/workItems");
				for(int row = 2; row < numberOfApprovers; row++)
				{
					if(entAddApproveDenyList[row][0].contains("na")&&entRemoveApproveDenyList[row][0].contains("na"))
					{}
					else
					{
						Thread.sleep(3000);
						manageUserAccessPage.waitForElement(driver.findElement(By.xpath("//input[@id='cardListSearchInput']")));
						driver.findElement(By.xpath("//input[@id='cardListSearchInput']")).sendKeys("Owner Approval - Account Changes for User: "+eidvalue, Keys.ENTER);
						Thread.sleep(3000);
						driver.findElement(By.xpath("//input[@id='cardListSearchInput']")).sendKeys(Keys.TAB, Keys.TAB, Keys.TAB, Keys.TAB, Keys.TAB, Keys.ENTER);
						//manageUserAccessPage.waitForElement(driver.findElement(By.xpath("//button[contains(@id,'workitem-forward-tablet-btn')]")));
						//driver.findElement(By.xpath("//button[contains(@id,'workitem-forward-tablet-btn')]")).click();
						manageUserAccessPage.inputOwnerNameForwardDropdown(manager);
						//driver.findElement(By.xpath("//input[@id='ownerNameSuggestBoxWorkItemListForward']")).sendKeys(Keys.ENTER);
						manageUserAccessPage.waitForElement(driver.findElement(By.xpath("//input[@id='ownerNameSuggestBoxWorkItemListForward']")));
						driver.findElement(By.xpath("//input[@id='ownerNameSuggestBoxWorkItemListForward']")).sendKeys(Keys.TAB, Keys.TAB, Keys.TAB, Keys.TAB, Keys.ENTER);
						driver.findElement(By.xpath("//input[@id='cardListSearchInput']")).sendKeys(Keys.TAB, Keys.TAB, Keys.TAB, Keys.TAB, Keys.TAB, Keys.TAB, Keys.ENTER);
						//manageUserAccessPage.waitForElement(driver.findElement(By.xpath("//button[contains(@id,'workitem-goto-btn')]")));
						//driver.findElement(By.xpath("//button[contains(@id,'workitem-goto-btn')]")).click();
						//manageUserAccessPage.waitForElement();
						for(int col = 0; col < larger; col++)
						{
								if((entAddApproveDenyList[row][0].contains("deny all")||entRemoveApproveDenyList[row][0].contains("deny all"))&&col==0)
								{
									manageUserAccessPage.clickonrejectallButton();	
								}
								else if((entAddApproveDenyList[row][0].contains("approve all")||entRemoveApproveDenyList[row][0].contains("approve all"))&&col==0)
								{
									manageUserAccessPage.clickonapproveallButton();		
								}
								else
								{
									if(temp.length > tempRem.length)//if adds ents array > remove ents array
									{
										if(col<tempRem.length/numberOfApprovers)
										{
											remove_Ents_Click(row,col);
										}
										add_Ents_Click(row,col);
									}
									else if(temp.length < tempRem.length)
									{
										if(col<temp.length/numberOfApprovers)
										{
											add_Ents_Click(row,col);
										}
										remove_Ents_Click(row,col);
									}
									else
									{
										add_Ents_Click(row,col);
										remove_Ents_Click(row,col);
									}
								}
						}
						click_on_complete();
						Thread.sleep(3000);
						assertLogger.log("Third Approver Approval Successful");
					}
				
				}
				Thread.sleep(3000);//remove later
			}
		}	
	}
	
	public void remove_Ents_Click(int row, int col) throws InterruptedException
	{
		switch(entRemoveApproveDenyList[row][col])
		{
		case "approve":
			manageUserAccessPage.clickonapproveButtonItemX(accessRemoveVals[col], "Remove");
			break;
		case "deny":
			manageUserAccessPage.clickonrejectButtonItemX(accessRemoveVals[col], "Remove");
			break;
		case "na":
			assertLogger.log("entitlement choice is na due to test case");
			break;
		default:
			assertLogger.log("please fix excel sheet column 'entRemove approval list'");
			break;
		}
	}
	public void add_Ents_Click(int row, int col) throws InterruptedException
	{
		switch(entAddApproveDenyList[row][col])
		{
		case "approve":
			manageUserAccessPage.clickonapproveButtonItemX(accessvals[col], "Add");
			break;
		case "deny":
			manageUserAccessPage.clickonrejectButtonItemX(accessvals[col], "Add");
			break;
		case "na":
			assertLogger.log("entitlement choice is na due to test case");
			break;
		default:
			assertLogger.log("please fix excel sheet 'entAdd approval list'");
			break;
		}
	}
	////////////remove below
	@Then("Manager Approval")
	public void manager_approval() throws InterruptedException
	{	

		if(accessrequest_type.contains("entitlement")&&!accessrequest_type.contains("cancel")&&!accessrequest_type.contains("same")
				&&!accessrequest_type.contains("break"))
		{
			Thread.sleep(3000);
			if(accessrequest_type.contains("entitlement"))
			{
				if(ent0Man.contains("approve")||ent0Man.contains("deny"))
				{
					click_on_approve_reject_button_item0(ent0Man);
				}
				if(ent1Man.contains("approve")||ent1Man.contains("deny"))
				{
					click_on_approve_reject_button_item1(ent1Man);
				}
				click_on_complete();
				assertLogger.log("Manager Approval Successful");
			}
		}	
	}
	
	@Then("Security Manager Approval")
	public void security_manager_approval() throws InterruptedException
	{
		if(accessrequest_type.contains("entitlement")&&!accessrequest_type.contains("cancel")&&!accessrequest_type.contains("same")
				&&!accessrequest_type.contains("break"))
		{
			Thread.sleep(3000);
			if(accessrequest_type.contains("entitlement"))
			{
				if(ent0Man.contains("deny"))
				{
					assertLogger.log("Skip Successful");
				}
				else
				{
					if(ent0SecMan.contains("approve")||ent0SecMan.contains("deny"))
					{
						click_on_approve_reject_button_item0(ent0SecMan);
					}
					if(ent1SecMan.contains("approve")||ent1SecMan.contains("deny"))
					{
						click_on_approve_reject_button_item1(ent1SecMan);
					}
					click_on_complete();
					assertLogger.log("Security Manager Approval Successful");
				}

			}
		}
	}
	
	@Then("Third Approver Approval")
	public void thrid_approver_approval() throws InterruptedException
	{	
		if(accessrequest_type.contains("entitlement")&&!accessrequest_type.contains("cancel")&&!accessrequest_type.contains("same")
				&&!accessrequest_type.contains("break"))
		{
			Thread.sleep(3000);
			if(accessrequest_type.contains("entitlement"))
			{		
				if(ent0Man.contains("deny")||ent0SecMan.contains("deny"))
				{
					assertLogger.log("Skip Successful");
				}
				else
				{	
					if(env.contains("TDE"))
					{
						driver.get("https://a1icam.a1vdc.us.af.mil/identityiqTDE/workitem/workItems.jsf#/workItems");
					}
					else if(env.contains("PREPROD"))
					{
						driver.get("https://a1icam.a1vdc.us.af.mil/identityiqPREPROD/workitem/workItems.jsf#/workItems");
					}
					Thread.sleep(3000);
					driver.findElement(By.xpath("//input[@id='cardListSearchInput']")).sendKeys("Owner Approval - Account Changes for User: "+eidvalue, Keys.ENTER);
					Thread.sleep(1000);
					driver.findElement(By.xpath("//button[contains(@id,'workitem-forward-btn')]")).click();
					manageUserAccessPage.inputOwnerNameForwardDropdown(manager);
					//driver.findElement(By.xpath("//input[@id='ownerNameSuggestBoxWorkItemListForward']")).sendKeys(Keys.ENTER);
					driver.findElement(By.xpath("//input[@id='ownerNameSuggestBoxWorkItemListForward']")).sendKeys(Keys.TAB, Keys.TAB, Keys.TAB, Keys.TAB, Keys.ENTER);
					Thread.sleep(2000);
					driver.findElement(By.xpath("//button[contains(@id,'workitem-goto-btn')]")).click();
					Thread.sleep(500);
					if(ent0Third.contains("approve")||ent0Third.contains("deny"))
					{
						click_on_approve_reject_button_item0(ent0Third);
					}
					if(ent1Third.contains("approve")||ent1Third.contains("deny"))
					{
						click_on_approve_reject_button_item1(ent1Third);
					}
					
					click_on_complete();
					Thread.sleep(2000);
					
					assertLogger.log("Third Approver Approval Successful");
				}
			}
		}
	}
		
	//remove above
	
	
	
	@And("sunrise sunset testing")
	public void sunrise_sunset_testing() throws InterruptedException
	{
		if(accessrequest_type.contains("sunrise") || accessrequest_type.contains("sunset"))
		{
			if(accessrequest_type.contains("ssreject"))
			{
				String[][] sunriseSunsetMatrix = {{"None","None"},
						{"None","Past"},
						{"None","Present"},
						{"None","Future"},
						{"Past","None"},
						{"Past","Past"},
						{"Past","Present"},
						{"Past","Future"},
						{"Present","None"},
						{"Present","Past"},
						{"Present","Present"},
						{"Present","Future"},
						{"Future","None"},
						{"Future","Past"},
						{"Future","Present"},
						{"Future","Future"}};
				for(int i = 0; i<sunriseSunsetMatrix.length ;i++)
				{
					for(int j = 0; j<sunriseSunsetMatrix[i].length ;j++)
					{
						if(sunriseSunsetMatrix[i][j].contains("None"))
						{
							
						}
						else
						{
							if(j==0)
							{
			
							}
							else if(j==1)
							{
			
							}
						}
					}
				}
			}
			
			else
			{
				
			}
		}			
	}
	
	
	
	
	
	@And("save access request id for further validation")
	public void note_accessRequest_id() throws InterruptedException{
		System.out.println(manageUserAccessPage.getaccessRequestID());
		context.setStringvalue(manageUserAccessPage.getaccessRequestID()); 
	}
	
	
	@And("manager and role owner approve request")
	public void manager_and_role_owner_approve_request() throws InterruptedException {
		AccessRequestStepDef tempAccessRequestStepDef = new AccessRequestStepDef(context);		
		AttributesStepDef tempAttributesStepDef = new AttributesStepDef(context);
		GenericStepDef tempGenericStepDef = new GenericStepDef(context);
		LoginStepDef tempLoginStepDef = new LoginStepDef(context);
		WorkItemStepDef tempWorkItemStepDef = new WorkItemStepDef(context);
		GroupsStepDef tempGroupsStepDef = new GroupsStepDef(context);
		
		int count = 1;
		
		do {
			tempAccessRequestStepDef.openAccessrequestspage();
			tempAccessRequestStepDef.search_for_access_request();
			
			
			String approverName = accessRequestPage.getApproverName();
			
			boolean isPerson = approverName.contains(" ");
			
			if(isPerson) {
				// Manager
				tempAccessRequestStepDef.extractmanagername();
				tempAccessRequestStepDef.extractInteractionDescription();
				tempAccessRequestStepDef.extract_workitem_number();

			} else {
				// Workgroup
				tempAccessRequestStepDef.extractworkgroupname();
				tempAccessRequestStepDef.extractInteractionDescription();
				tempAccessRequestStepDef.extract_workitem_number();
				tempGroupsStepDef.click_on_my_work_link();
				tempGroupsStepDef.select_work_items_link();
				tempGroupsStepDef.validate_work_items_page_is_displayed();
				tempGroupsStepDef.searchForWorkgroup();
				tempGroupsStepDef.save_workgroup_member_id();
			}
			
			tempAttributesStepDef.click_on_identities();
			tempAttributesStepDef.click_on_identity_warehouse();
			tempAttributesStepDef.validate_identity_warehouse_page_is_displayed();
			if(isPerson) {
				tempAttributesStepDef.search_user_in_identity_cube_by_fullname();
					
			} else {
				tempAttributesStepDef.search_user_in_identity_cube();
			}
			tempAttributesStepDef.click_on_valid_identity();
			tempAttributesStepDef.extract_and_save_approver_id();
			
			tempGenericStepDef.launch_application();
			tempLoginStepDef.enter_alternate_username_password();
			tempLoginStepDef.user_clicks_login();
			tempLoginStepDef.user_validates_homepage();
			tempWorkItemStepDef.click_on_my_work_link();
			tempWorkItemStepDef.select_work_items_link();
			tempWorkItemStepDef.validate_work_items_page_is_displayed();
			tempWorkItemStepDef.search_work_item();
			tempWorkItemStepDef.click_on_view_button();
			tempWorkItemStepDef.validate_approval_page_is_displayed();
			tempWorkItemStepDef.take_action_on_workitem(count ,context.getMoreData().get("empid"), context.getMoreData().get("interactionDescription"));
			tempWorkItemStepDef.confirm_workitem_action();
			tempWorkItemStepDef.validate_work_items_page_is_displayed();
			
			tempGenericStepDef.launch_application();
			tempLoginStepDef.user_enters_username_password();
			tempLoginStepDef.user_clicks_login();
			tempLoginStepDef.user_validates_homepage();
			
			
			tempAccessRequestStepDef.openAccessrequestspage();
			tempAccessRequestStepDef.search_for_access_request();
			++count;
		} while (accessRequestPage.isWorkLeft() > -1);
		
		
	}
	


}
