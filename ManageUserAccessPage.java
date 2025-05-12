package org.cucumbertaf.pageobjects;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

import java.util.List;
import java.util.NoSuchElementException;

import org.cucumbertaf.basepageobjects.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import io.cucumber.core.internal.com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
//Remove all Thread.sleep and focus on method calling and Sort my work better
public class ManageUserAccessPage extends BasePage {
	public ManageUserAccessPage(WebDriver driver) {
		super(driver);
	}
	
	//@FindBy(xpath = "<..table xpath>")
	//public WebElement parentElement; 
	
	//Front Page
	@FindBy(id = "quicklinkButton")
	private WebElement btn_quicklinkButton;

	@FindBy(id = "quickLinkCategoryAccess")
	private WebElement ele_ManageAccess;
	
	@FindBy(id = "quickLinkCategoryTasks")
	private WebElement ele_ManageTasks;
	
	@FindBy(xpath = "//button[contains(@id,'ManageButton')]")
	private WebElement btn_manage;

	@FindBy(xpath = "//li/a[contains(text(), 'Manage User Access')]")
	private WebElement ele_ManageUserAccess;
	
	@FindBy(xpath = "//li/a[contains(text(), 'Manage Accounts')]")
	private WebElement ele_ManageAccounts;
	
	@FindBy(xpath = "//li/a[contains(text(), 'Approvals')]")
	private WebElement ele_ManageUserTasks;
	//Page Checker
	@FindBy(xpath = "//div/h1[contains(@class, 'text h4')]")
	private WebElement ele_pageTitle;
	
	//DisableEnableDelete Items
	@FindBy(id = "searchInput")
	private WebElement txt_UserSearchInputDED;
	
	@FindBy(id = "searchBtn")
	private WebElement btn_userSearchBtnDED;
	
	
	//Tasks Buttons
	@FindBy(id = "gridview-1026-bd-Identity")
	private WebElement btn_refresh;

	@FindBy(id = "tab-1091-btnEl")
	private WebElement btn_taskList;
	
	
	//Access Request User Search Buttons and TXT
	@FindBy(id = "userSearchText")
	private WebElement txt_UserSearchInput;
	
	@FindBy(id = "userSearchBtn")
	private WebElement btn_userSearchBtn;
	
	@FindBy(id = "selectBtn-0")
	private WebElement btn_selectBtn;
	
	@FindBy(xpath = "//div[@class='form-group']/input")
	private WebElement txt_mailaddress;

	@FindBy(xpath = "//div[contains(@class, 'panel-heading')]//strong")
	private List<WebElement> ele_userFullName;

	@FindBy(xpath = "//div[contains(@class, 'panel-heading')]//button[@role='checkbox']")
	private List<WebElement> ele_selectUserCheckBox;

	
	//Access Request Entitlement Buttons and TXT
	@FindBy(xpath = "//div/li[2]")
	private WebElement btn_removeaccessButton;

	@FindBy(id = "accessSearchText")
	private WebElement txt_accessSearchInput;

	@FindBy(id = "accessSearchBtn")
	private WebElement btn_accessSearchButton;

	@FindBy(id = "removeAccessSearchText")
	private WebElement txt_removeAccessSearchInput;


	@FindBy(id = "currentAccessSearchBtn")
	private WebElement btn_removeAccessSearchBtn;
	

	
	@FindBy(xpath = "//button[@ng-repeat='button in buttons'][normalize-space()='Complete']")
	private WebElement btn_completeapproveButton;
	
	@FindBy(xpath = "//button[contains(text(),'OK')]")
	private WebElement btn_globalokformButton;

	@FindBy(xpath = "//button[contains(text(),'OK')]")
	private WebElement btn_okButton;
	
	@FindBy(xpath = "//button[contains(text(),'Submit')]")
	private WebElement btn_submitformButton;
	//Filter Items for Entitlements 
	@FindBy(xpath = "//button[contains(@id,'itemsFilterBtn')]")
	private WebElement btn_filter;
	
	@FindBy(xpath = "//input[@id='itemsFilterPanelItem5']")
	private WebElement txt_itemsFilterPanelItem5Input;
	
	@FindBy(xpath = "//input[@id='itemsFilterPanelItem6']")
	private WebElement txt_itemsFilterPanelItem6Input;
	
	//Approval/Deny Buttons
	@FindBy(xpath = "//button[contains(@id, 'btnApproveAllApproval0')]")
	private WebElement btn_approveallButton;
	
	@FindBy(xpath = "//button[@id='btnRejectAllApproval0']")
	private WebElement btn_rejectallButton;

	//Single approve deny Item 0-X
	@FindBy(xpath = "//button[@id='btnApproveApproval0Item0']")
	private WebElement btn_approveButtonItem0;
	
	@FindBy(xpath = "//button[@id='btnRejectApproval0Item0']")
	private WebElement btn_rejectButtonItem0;
	
	@FindBy(xpath = "//button[@id='btnApproveApproval0Item1']")
	private WebElement btn_approveButtonItem1;
	
	@FindBy(xpath = "//button[@id='btnRejectApproval0Item1']")
	private WebElement btn_rejectButtonItem1;
	//experiment
	
	WebElement btn_approveButtonItemX;
	WebElement btn_rejectButtonItemX;
	
	
	/////////////////////////////////////////////////////////////////

//////////////////////////////////////////////////////////////////////////////add more based on attributes//////////////////
	//AFIPPS
		//
	//AFM
	@FindBy(xpath = "//input[contains(@id,'contractExpirationDate')]")
	private WebElement txt_contractExpirationDateInput;
	//AFPROMS
	@FindBy(xpath = "//input[contains(@id,'mpf')]")
	private WebElement txt_mpfLevelIDinput;
	@FindBy(xpath = "//input[contains(@id,'mgmtLevel')]")
	private WebElement txt_managementLevelIDinput;
	@FindBy(xpath = "//input[contains(@id,'boardType')]")
	private WebElement txt_boardTypeinput;
	//ALMSS
	@FindBy(xpath = "//input[contains(@id,'Type')]")
	private WebElement txt_TypelabelInput;
	@FindBy(xpath = "//input[contains(@id,'IATraining')]")
	private WebElement txt_IATrainingInput;
	//CON-IT
	@FindBy(xpath = "//input[contains(@id,'supervisorEmail')]")
	private WebElement txt_supervisorEmailInput;
	@FindBy(xpath = "//input[contains(@id,'fpdsngID')]")
	private WebElement txt_fpdsngIDInput;
	@FindBy(xpath = "//input[contains(@id,'grade')]")
	private WebElement txt_gradeInput;
	//EBIS
		//NA
	//EESOH-MIS
	@FindBy(xpath = "//input[contains(@id,'nationalGuardReserveFlag')]")
	private WebElement txt_nationalGuardReserveFlagInput;
	@FindBy(xpath = "//input[contains(@id,'contractNumber')]")
	private WebElement txt_contractNumberInput;
	@FindBy(xpath = "//input[contains(@id,'contractorName')]")
	private WebElement txt_contractorNameInput;
	@FindBy(xpath = "//input[contains(@id,'contractStartDate')]")
	private WebElement txt_contractStartDateInput;
	@FindBy(xpath = "//input[contains(@id,'contractEndDate')]")
	private WebElement txt_contractEndDateInput;
	//FMSuite
	@FindBy(xpath = "//input[contains(@id,'jobTitle')]")
	private WebElement txt_jobtitleInput;
	@FindBy(xpath = "//input[contains(@id,'rank')]")
	private WebElement txt_rankInput;
	@FindBy(xpath = "//input[contains(@id,'base')]")
	private WebElement txt_baseInput;
	//ILS-S
		//Check Multiple Systems
	//JOCASII-****
		//NA
	//REMIS
		//NA
	//RTS
		//NA
	//Multiple Systems
	//@FindBy(xpath = "//input[contains(@id,'phone')]")//CON-IT, EESOH-MIS, FMSuite, ILS-S
	@FindBy(xpath = "//input[contains(@id,'Phone')]")//CON-IT, EESOH-MIS, FMSuite, ILS-S
	private WebElement txt_phonecommInput;
	//////////////////////////////////////////////////////////////////////
	@FindBy(xpath = "//button[contains(@id, 'submitWithViolationsBtn')]")
	private WebElement btn_submitWithViolationsBtn;
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	@FindBy(xpath = "//input[@id='AddApprovals-form-manager-field']")
	private WebElement txt_managerInput;
	
	@FindBy(xpath = "//input[contains(@id,'AddApprovals-form-securityManager-field')]")
	private WebElement txt_securitymanagerInput;


	
	@FindBy(xpath = "//div[@class='input-group']//ul//li")
	private List<WebElement> ele_formmultivalues;

	
	@FindBy(id = "submitBtn")
	private WebElement btn_submitBtn;
	
	@FindBy(xpath = "//li[@class='formInfo']")
	private WebElement ele_fromSubmitResponse;

	@FindBy(xpath = "//input[contains(@id,'ownerNameSuggestBoxWorkItemListForward')]")
	private WebElement txt_ownerNameSuggestFwd;
	
	//Multi Use Buttons and TXT (Comments and Next Buttons)
	@FindBy(id = "accessRequestFooterNextBtn")
	private WebElement btn_nextButton;
	
	@FindBy(xpath = "//button[contains(text(),'Complete Form')]")
	private WebElement btn_globalcommentButton;
	
	@FindBy(xpath = "//div[@id='commentArea']/textarea[@id='commentTextArea']")
	private WebElement txt_CommentareaInput;
	
	@FindBy(xpath = "//button[@id='saveCommentBtn']")
	private WebElement btn_commentsavebutton;
	
	
	
	
	
	
	
	public void clickQuickLinks() throws InterruptedException {
		waitForElement(btn_quicklinkButton);
		clickElement(btn_quicklinkButton);
	}

	public void clickManageAccess() throws InterruptedException {
		waitForElement(ele_ManageAccess);
		clickElement(ele_ManageAccess);
	}
	
	public void clickManageTasks() throws InterruptedException {
		waitForElement(ele_ManageTasks);
		clickElement(ele_ManageTasks);
	}

	public void clickManageUserAccess() throws InterruptedException {
		waitForElement(ele_ManageUserAccess);
		clickElement(ele_ManageUserAccess);
	}
	
	public void clickManageAccounts() throws InterruptedException {
		waitForElement(ele_ManageAccounts);
		clickElement(ele_ManageAccounts);
	}
	
	public void clickApprovals() throws InterruptedException {
		waitForElement(ele_ManageUserTasks);
		clickElement(ele_ManageUserTasks);
	}

	public String getPageTitle() throws InterruptedException {
		waitForElement(ele_pageTitle);
		return getElementText(ele_pageTitle);
	}

	public void inputmailaddress(String useremailaddress) throws InterruptedException {
		waitForElement(btn_okButton);
		setInputBoxValue(txt_mailaddress, useremailaddress);

	}

	public void inputeid(String eidval, String accessrequest_type) throws InterruptedException {
		if(accessrequest_type.contains("entitlement"))
		{
			waitForElement(txt_UserSearchInput);
			setInputBoxValue(txt_UserSearchInput, eidval);
		}
		else if(accessrequest_type.contains("disable")||accessrequest_type.contains("enable")||accessrequest_type.contains("delete"))
		{
			waitForElement(txt_UserSearchInputDED);
			setInputBoxValue(txt_UserSearchInputDED, eidval);
		}
		
	}
	

	public void clicksearchUserButton(String accessrequest_type) throws InterruptedException {
		if(accessrequest_type.contains("entitlement"))
		{
			waitForElement(btn_userSearchBtn);
			clickElement(btn_userSearchBtn);
		}
		else if(accessrequest_type.contains("disable")||accessrequest_type.contains("enable")||accessrequest_type.contains("delete"))
		{
			
			waitForElement(btn_userSearchBtnDED);
			clickElement(btn_userSearchBtnDED);
		}
	}
	
	public void clickmanageButton() throws InterruptedException
	{
		waitForElement(btn_manage);
		clickElement(btn_manage);
	}
	

	
	
	public void clickselectUserButton() throws InterruptedException {
		waitForElement(btn_selectBtn);
		clickElement(btn_selectBtn);
	}

	public void clickremoveaccessbutton() throws InterruptedException {
		waitForElement(btn_removeaccessButton);
		clickElement(btn_removeaccessButton);
	}

	public List<WebElement> getuserFullNames() throws InterruptedException {
		waitForAllElements(ele_userFullName);
		return ele_userFullName;
	}

	public void clickCheckboxofIndex(int index) throws InterruptedException {
		waitForAllElements(ele_selectUserCheckBox);
		selectCheckBox(ele_selectUserCheckBox.get(index));
	}

	public void clickNextButton() throws InterruptedException {
		waitForElement(btn_nextButton);
		clickElement(btn_nextButton);
	}

	public void inputEntitlement(String accessval) throws InterruptedException {
		waitForElement(txt_accessSearchInput);
		setInputBoxValue(txt_accessSearchInput, accessval);
		
	}

	public void clicksearchAccessButton() throws InterruptedException {
		waitForElement(btn_accessSearchButton);
		clickElement(btn_accessSearchButton);
	}

	public void inputRemoveEntitlement(String accessval) throws InterruptedException {
		waitForElement(txt_removeAccessSearchInput);
		setInputBoxValue(txt_removeAccessSearchInput, accessval);
		
	}

	public void clicksearchRemoveAccessButton() throws InterruptedException {
		waitForElement(btn_removeAccessSearchBtn);
		clickElement(btn_removeAccessSearchBtn);
	}

	
	///damn this solution for filters
	public void clickFilter() throws InterruptedException {
		waitForElement(btn_filter);
		clickElement(btn_filter);
	}
	
	//identity refresh fix
	public void clickRefresh() throws InterruptedException {
		waitForElement(btn_refresh);
		clickElement(btn_refresh);
	}
	
	//Task List fix
	public void clickTaskList() throws InterruptedException {
		waitForElement(btn_taskList);
		clickElement(btn_taskList);
	}
	
	public void clickGlobalCommentButton() throws InterruptedException {
		waitForElement(btn_globalcommentButton);
		clickElement(btn_globalcommentButton);
	}

	public void clickcompleteButton() throws InterruptedException {
		waitForElement(btn_globalcommentButton);
		clickElement(btn_globalcommentButton);
	}
	
	public void clickcompleteaccessButton() throws InterruptedException {
		waitForElement(btn_completeapproveButton);
		clickElement(btn_completeapproveButton);
	}
	
	public void clickokformButton() throws InterruptedException {
		waitForElement(btn_globalokformButton);
		clickElement(btn_globalokformButton);
	}

	public void clickokButton() throws InterruptedException {
		waitForElement(btn_okButton);
		clickElement(btn_okButton);
	}
	
	public void clickonsubmitformButton() throws InterruptedException {
		waitForElement(btn_submitformButton);
		clickElement(btn_submitformButton);
	}

	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public void clickonapproveButtonItem0() throws InterruptedException 
	{
		Thread.sleep(3000);
		waitForElement(btn_approveButtonItem0);
		clickElement(btn_approveButtonItem0);
	}
	
	public void clickonrejectButtonItem0() throws InterruptedException 
	{
		Thread.sleep(3000);
		waitForElement(btn_rejectButtonItem0);
		clickElement(btn_rejectButtonItem0);
	}
	
	public void clickonapproveButtonItem1() throws InterruptedException 
	{
		Thread.sleep(3000);
		waitForElement(btn_approveButtonItem1);
		clickElement(btn_approveButtonItem1);
	}
	
	public void clickonrejectButtonItem1() throws InterruptedException 
	{
		Thread.sleep(3000);
		waitForElement(btn_rejectButtonItem1);
		clickElement(btn_rejectButtonItem1);
	}
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//approval/rejection methods
	public void clickonapproveallButton() throws InterruptedException {
		Thread.sleep(3000);
		waitForElement(btn_approveallButton);
		clickElement(btn_approveallButton);
	}
	
	public void clickonrejectallButton() throws InterruptedException {
		Thread.sleep(3000);
		waitForElement(btn_rejectallButton);
		clickElement(btn_rejectallButton);
	}
	
	
	public void clickonapproveButtonItemX(String entName, String access)throws InterruptedException
	{
		btn_approveButtonItemX = driver.findElement(By.xpath("//button[contains(@aria-label, 'Approve request "+access+" "+entName+"')]"));
		waitForElement(btn_approveButtonItemX);
		clickElement(btn_approveButtonItemX);
	}

	public void clickonrejectButtonItemX(String entName, String access)throws InterruptedException
	{
		btn_rejectButtonItemX = driver.findElement(By.xpath("//button[contains(@aria-label, 'Deny request "+access+" "+entName+"')]"));
		waitForElement(btn_rejectButtonItemX);
		clickElement(btn_rejectButtonItemX);
	}
	

	
	
	///////////////////////////////////////////////
	
	public void inputGlobalComment(String comment) throws InterruptedException {
		waitForElement(txt_CommentareaInput);
		setInputBoxValue(txt_CommentareaInput, comment);
	}
	

	public void inputOwnerNameForwardDropdown(String manager) throws InterruptedException {//dropdown +? //possible edits
		waitForElement(txt_ownerNameSuggestFwd);
		setInputBoxValue(txt_ownerNameSuggestFwd, manager);
		waitForAllElements(ele_formmultivalues);
		selectElementFromDropDown(ele_formmultivalues, manager);

	}
	
	/////////////////////////////////////////////////////////////////////////////////
	//AFIPPS
		//NA
	//AFM
	public void inputcontractExpirationDate(String contractExpirationDate) throws InterruptedException {
		waitForElement(txt_contractExpirationDateInput);
		setInputBoxValue(txt_contractExpirationDateInput, contractExpirationDate);

	}
	//AFPROMS
	public void inputmpfLevelID(String mpfLevelID) throws InterruptedException {//dropdown +? //possible edits
		waitForElement(txt_mpfLevelIDinput);
		setInputBoxValue(txt_mpfLevelIDinput, mpfLevelID);
		waitForAllElements(ele_formmultivalues);
		selectElementFromDropDown(ele_formmultivalues, mpfLevelID);

	}
	public void inputmanagementLevelID(String managementLevelID) throws InterruptedException {//dropdown +? //possible edits
		waitForElement(txt_managementLevelIDinput);
		setInputBoxValue(txt_managementLevelIDinput, managementLevelID);
		waitForAllElements(ele_formmultivalues);
		selectElementFromDropDown(ele_formmultivalues, managementLevelID);

	}
	public void inputboardType(String boardType) throws InterruptedException {//dropdown +? //possible edits
		waitForElement(txt_boardTypeinput);
		setInputBoxValue(txt_boardTypeinput, boardType);
		waitForAllElements(ele_formmultivalues);
		selectElementFromDropDown(ele_formmultivalues, boardType);

	}
	//ALMSS
	public void inputTypelabel(String typeLabel) throws InterruptedException {
		waitForElement(txt_TypelabelInput);
		setInputBoxValue(txt_TypelabelInput, typeLabel);
	}
	public void inputIATraining(String iaTraining) throws InterruptedException {
		waitForElement(txt_IATrainingInput);
		setInputBoxValue(txt_IATrainingInput, iaTraining);
	}
	//CON-IT
	public void inputsupervisorEmail(String supervisorEmail) throws InterruptedException {
		waitForElement(txt_supervisorEmailInput);
		setInputBoxValue(txt_supervisorEmailInput, supervisorEmail);
	}
	public void inputfpdsngID(String fpdsngID) throws InterruptedException {
		waitForElement(txt_fpdsngIDInput);
		setInputBoxValue(txt_fpdsngIDInput, fpdsngID);
	}
	public void inputgrade(String grade) throws InterruptedException {//dropdown
		waitForElement(txt_gradeInput);
		setInputBoxValue(txt_gradeInput, grade);
		waitForAllElements(ele_formmultivalues);
		selectElementFromDropDown(ele_formmultivalues, grade);
	}
	//EBIS
		//NA
	//EESOH-MIS
	
	//middleName
	
	//nationalGuardReserveFlag //Need to figure out checkbox
	public void inputnationalGuardReserveFlag(String nationalGuardReserveFlag) throws InterruptedException {
		if(nationalGuardReserveFlag.contains("TRUE"))
		{
			//selectCheckBox(txt_nationalGuardReserveFlagInput);
			waitForElement(txt_nationalGuardReserveFlagInput);
			selectCheckBox(txt_nationalGuardReserveFlagInput);
			//setInputBoxValue(txt_nationalGuardReserveFlagInput, nationalGuardReserveFlag);
		}
	}
	public void inputcontractNumber(String contractNumber) throws InterruptedException {
		waitForElement(txt_contractNumberInput);
		setInputBoxValue(txt_contractNumberInput, contractNumber);
	}
	public void inputcontractorName(String contractorName) throws InterruptedException {
		waitForElement(txt_contractorNameInput);
		setInputBoxValue(txt_contractorNameInput, contractorName);
	}
	public void inputcontractStartDate(String contractStartDate) throws InterruptedException {
		waitForElement(txt_contractStartDateInput);
		setInputBoxValue(txt_contractStartDateInput, contractStartDate);
	}
	public void inputcontractEndDate(String contractEndDate) throws InterruptedException {
		waitForElement(txt_contractEndDateInput);
		setInputBoxValue(txt_contractEndDateInput, contractEndDate);
	}
	//FMSuite
	public void inputjobtitle(String jobtitle) throws InterruptedException {
		waitForElement(txt_jobtitleInput);
		setInputBoxValue(txt_jobtitleInput, jobtitle);
	}
	public void inputrank(String rank) throws InterruptedException {//dropdown
		waitForElement(txt_rankInput);
		setInputBoxValue(txt_rankInput, rank);
		waitForAllElements(ele_formmultivalues);
		selectElementFromDropDown(ele_formmultivalues, rank);
	}
	public void inputbase(String base) throws InterruptedException {//dropdown
		waitForElement(txt_baseInput);
		setInputBoxValue(txt_baseInput, base);
		waitForAllElements(ele_formmultivalues);
		selectElementFromDropDown(ele_formmultivalues, base);
	}
	//ILS-S
		//Check Multiple Systems
	//JOCASII-****
		//NA
	//REMIS
		//NA
	//RTS
		//NA
	//Multiple Systems
	public void inputphonecomm(String phonecomm) throws InterruptedException {//CON-IT, EESOH-MIS, FMSuite, ILS-S
		waitForElement(txt_phonecommInput);
		setInputBoxValue(txt_phonecommInput, phonecomm);
	}
	//////////////////////////////////////////////////////
	
	public void submitwithviolations() throws InterruptedException {
		waitForElement(btn_submitWithViolationsBtn);
		clickElement(btn_submitWithViolationsBtn);
		
		
	}
	
	
	//////////////////////////////////////////////////////
	public void inputmanager(String manager) throws InterruptedException {
		waitForElement(txt_managerInput);
		setInputBoxValue(txt_managerInput, manager);
		waitForAllElements(ele_formmultivalues);
		selectElementFromDropDown(ele_formmultivalues, manager);

	}
	
	public void inputsecuritymanager(String securitymanager) throws InterruptedException {
		waitForElement(txt_securitymanagerInput);
		setInputBoxValue(txt_securitymanagerInput, securitymanager);
		waitForAllElements(ele_formmultivalues);
		selectElementFromDropDown(ele_formmultivalues, securitymanager);

	}
	
	public void inputFilterItems(String systemname, String type) throws InterruptedException {
		if(type.contains("add"))
		{
			waitForElement(txt_itemsFilterPanelItem5Input);
			setInputBoxValue(txt_itemsFilterPanelItem5Input, systemname);
			waitForAllElements(ele_formmultivalues);
			selectElementFromDropDown(ele_formmultivalues, systemname);
		}
		if(type.contains("remove"))
		{
			waitForElement(txt_itemsFilterPanelItem6Input);
			setInputBoxValue(txt_itemsFilterPanelItem6Input, systemname);
			waitForAllElements(ele_formmultivalues);
			selectElementFromDropDown(ele_formmultivalues, systemname);
		}


	}


	public void clickGlobalCommentSaveButton() throws InterruptedException {
		waitForElement(btn_commentsavebutton);
		clickElement(btn_commentsavebutton);
	}

	public void clickSubmitButton() throws InterruptedException {
		waitForElement(btn_submitBtn);
		clickElement(btn_submitBtn);
	}
	

	public String getaccessRequestID() throws InterruptedException {
		waitForElement(ele_fromSubmitResponse);
		String responceString = ele_fromSubmitResponse.getText();
		responceString = responceString.replaceAll("\\D+", "");
		return responceString;
	}

	public void load(String url) {
		driver.get(url);
	}
	
	
	public boolean isElementPresent(By by) 
	{
		try 
		{
			driver.findElement(by);
		    return true;
		} catch (NoSuchElementException e) 
		{
			return false;
		}
	}

}
