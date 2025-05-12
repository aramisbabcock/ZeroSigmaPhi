package org.cucumbertaf.pageobjects;

import java.util.List;

import org.cucumbertaf.basepageobjects.BasePage;
import org.cucumbertaf.testlib.TestContext;
import org.cucumbertaf.utils.WebTable;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import freemarker.core.ReturnInstruction.Return;

public class ManagerCertificationPage extends BasePage {

	public ManagerCertificationPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//nav/ul/li[7]/a[@class='dropdown-toggle']")
	WebElement ele_setuplink;

	@FindBy(xpath = "//nav/ul/li[7]/a[@class='dropdown-toggle']/following-sibling::ul/li/a")
	List<WebElement> ele_setupLinkDropdownItems;

	@FindBy(xpath = "//div[@id='bodyDivTitle']/h1")
	WebElement ele_pageTitle;

	@FindBy(xpath = "//button/span[contains(text(), 'Certifications')]")
	WebElement ele_certificationsTab;

	@FindBy(id = "newCertButton-btnEl")
	WebElement btn_newcertification;

	@FindBy(id = "newCertButton-Manager")
	WebElement btn_newManagerCertification;

	@FindBy(id = "certificationScheduleForm:certificationTemplateName")
	WebElement txt_certificationNameInput;

	@FindBy(id = "crsComponent-inputEl")
	WebElement txt_managerInput;

	@FindBy(xpath = "//div[@id='boundlist-1030']//li")
	List<WebElement> ele_managerInputDropdown;

	@FindBy(id = "certificationScheduleForm:runNowCheckbox")
	WebElement ele_runNowCheckbox;

	@FindBy(id = "baseSuggest-1073-inputEl")
	WebElement txt_applicationInput;

	@FindBy(xpath = "//div[@id='boundlist-1080']//li")
	List<WebElement> ele_applicationInputDropdown;

	@FindBy(id = "certificationScheduleForm:certifyAccounts:1")
	WebElement ele_accountsRadio;
	
	@FindBy(id = "certificationScheduleForm:certifyAccounts:0")
	WebElement ele_entitlementRadio;

	@FindBy(id = "schedulePanel-card-next-btnEl")
	WebElement btn_nextButton;

	@FindBy(id = "certificationScheduleForm:certifyEnableStagingSelect")
	WebElement ele_stagingCheckbox;

	@FindBy(id = "certificationScheduleForm:processRevokesImmediately")
	WebElement ele_processRevokesImmediatelysCheckbox;

	@FindBy(id = "certificationScheduleForm:suppressInitialEmail")
	WebElement ele_suppressInitialEmail;

	@FindBy(id = "certificationScheduleForm:exclusionRules")
	WebElement sel_exclutionRuleSelect;

	@FindBy(id = "schedule-cert-btn-btnEl")
	WebElement btn_scheduleCertificationButton;
	
	@FindBy(xpath = "//div[@id='stagedContainer']/button[contains(text(), 'Activate')]")
	WebElement btn_activateCertificationButton;
	
	@FindBy(xpath = "//table/thead/tr/th//button")
	WebElement btn_bulkselectiondropdown;
	
	@FindBy(xpath = "//table/thead/tr/th//ul/li/a[contains(text(), 'Everything')]")
	WebElement ele_bulkSelectionEverything;
	
	@FindBy(xpath = "//span[@id='bulkDecisionSelector']//button")
	WebElement btn_bulkDecision;
	
	@FindBy(xpath = "//span[@id='bulkDecisionSelector']//ul/li/a[contains(text(), 'Approve')]")
	WebElement ele_bulkDecisionApprove; 
	
	@FindBy(xpath = "//div[@class='data-table-full']/table")
	WebElement tbl_certificationAccounts;
	
	@FindBy(xpath = "//div[@id='certificationGroupsGrid-body']//table")
	WebElement tbl_certificationsSearchResult;
	
	@FindBy(xpath = "//div[@id='accessReviewGrid']//div[contains(@id, 'body')]/div/table")
	WebElement tbl_accessSearch;
	
	@FindBy(xpath = "//div[@id='accessReviewGrid']//div[contains(@id, 'body')]/div/table/tbody/tr[2]/td[1]")
	WebElement tbl_accessSearchtemp;
	
	@FindBy(id = "certSignOffOverlayBtn")
	WebElement btn_certificationSignOffButton;
	
	@FindBy(id = "searchfield-1022-inputEl")
	WebElement txt_certificationSearch;
	
	@FindBy(id = "ext-gen1241")
	WebElement btn_certificationSearch;
	
	@FindBy(xpath = "(//button[contains(@aria-label, 'Revoke Account')])[1]")
	WebElement btn_firstRevokeButton; 
	
	@FindBy(xpath = "//div[contains(@id, 'loadmask')]")
	List<WebElement> ele_loadmaskElements;
	
	@FindBy(id = "button-1041")
	WebElement ele_cerfificationSearchRefresh;
	
	@FindBy(id = "certSaveBtn")
	WebElement btn_certificateSaveButton;
	
	@FindBy(id = "confirmSaveBtn")
	WebElement btn_certificateConfirmSaveButton;
	
	@FindBy(xpath = "//h1[contains(@class, 'decision-page-title-text')]")
	WebElement ele_accessReviewPageTitle;
	
	WebTable certificationSearchResultTable = new WebTable(tbl_certificationsSearchResult);
	
	WebTable accessSearchResultTable = new WebTable(tbl_accessSearch);
	
	WebTable certificationAccountsTable = new WebTable(tbl_certificationAccounts);
	
	
	public WebTable certificationSearchResultTable() {
		return certificationSearchResultTable;
	}
	
	public WebTable accessSearchResultTable() {
		return accessSearchResultTable;
	}
	
	public WebTable certificationAccountsTable() throws InterruptedException {
		waitForElement(tbl_certificationAccounts);
		return certificationAccountsTable;
	}
	
	
	public void clickonSetupLink() throws InterruptedException {
		clickElement(ele_setuplink);
	}

	public void selectCertificationinDropdown(String val) throws InterruptedException {
		selectElementFromDropDown(ele_setupLinkDropdownItems, val);
	}

	public String getpageTitle() throws InterruptedException {
		return getElementText(ele_pageTitle);
	}

	public void clicknewCertification() throws InterruptedException {
		clickElement(btn_newcertification);
	}

	public void clicknewManagerCertification() throws InterruptedException {
		waitForElement(btn_newManagerCertification);
		clickElement(btn_newManagerCertification);
	}

	public void setcertificationName(String certnameval) throws InterruptedException {
		setInputBoxValue(txt_certificationNameInput, certnameval);
	}

	public void setrecipientname(String managereid) throws InterruptedException {
		setInputBoxValue(txt_managerInput, managereid);

	}

	public void selectmanagerfromdropdown(String managereid) throws InterruptedException {
		waitForAllElements(ele_managerInputDropdown);
		selectElementFromDropDown(ele_managerInputDropdown, managereid);
	}

	public void selectrunnowcheckbox() throws InterruptedException {
		selectCheckBox(ele_runNowCheckbox);
	}

	public void setapplication(String appname) throws InterruptedException {
		setInputBoxValue(txt_applicationInput, appname);
	}

	public void selectapplicationfromdropdown(String appname) throws InterruptedException {
		waitForAllElements(ele_applicationInputDropdown);
		selectElementFromDropDown(ele_applicationInputDropdown, appname);
	}

	public void chooseCertificationaccessLevelaccounts() throws InterruptedException {
		selectCheckBox(ele_accountsRadio);
	}
	
	public void chooseCertificationaccessLevelentitlement() throws InterruptedException {
		selectCheckBox(ele_entitlementRadio);
	}

	public void clicknextpage() throws InterruptedException {
		clickElement(btn_nextButton);
	}

	public void selectstagingperiod() throws InterruptedException {
		selectCheckBox(ele_stagingCheckbox);
	}

	public void unselectrevocationperiod() throws InterruptedException {
		unSelectCheckBox(ele_processRevokesImmediatelysCheckbox);
	}

	public void selectprocessrevokesimmediately() throws InterruptedException {
		selectCheckBox(ele_processRevokesImmediatelysCheckbox);
	}

	public void selectsupressinitailnotification() throws InterruptedException {
		selectCheckBox(ele_suppressInitialEmail);
	}

	public void selectExclusionRule(String rule) throws InterruptedException {
		selectByValueFromDropdown(sel_exclutionRuleSelect, rule);
	}
	
	public void clickschedulecertification() throws InterruptedException {
		clickElement(btn_scheduleCertificationButton);
		waitForElement(txt_certificationSearch);
	}
	
	public void inputcertificationsname(String certification) throws InterruptedException {
		setInputBoxValue(txt_certificationSearch, certification);

	}
	
	public void searchcertifications() throws InterruptedException {
		clickElement(btn_certificationSearch);
		waitForAllElementsToHide(ele_loadmaskElements);
	}
	
	public void clickcertificationsearchRefresh() throws InterruptedException {
		clickElement(ele_cerfificationSearchRefresh);
		Thread.sleep(1000);
	}
	
	public void clickactivateCertification() throws InterruptedException{
		waitForElement(btn_activateCertificationButton);
		clickElement(btn_activateCertificationButton);
	}
	
	public String gettitleaccessreview() throws InterruptedException{
		waitForElement(ele_accessReviewPageTitle);
		return getElementText(ele_accessReviewPageTitle);
	}
	
	public void clickbulkselectdropdown() throws InterruptedException{
		clickElement(btn_bulkselectiondropdown);
	}
	
	public void clickbulkselectEverything() throws InterruptedException{
		waitForElement(ele_bulkSelectionEverything);
		clickElement(ele_bulkSelectionEverything);
	}
	
	
	public void clickbulkdecisiondropdown() throws InterruptedException{
		clickElement(btn_bulkDecision);
	}
	
	public void clickbulkdecisionapprove() throws InterruptedException{
		waitForElement(ele_bulkDecisionApprove);
		clickElement(ele_bulkDecisionApprove);
	}
	
	public void openManagerAccessReview() throws InterruptedException {
		waitForElement(tbl_accessSearchtemp);
		clickElement(tbl_accessSearchtemp);
		
	}
	
	public void clickrevokeFirstaccount() throws InterruptedException{
		clickElement(btn_firstRevokeButton);
	}
	
	public void clickSaveButton() throws InterruptedException{
		clickElement(btn_certificateSaveButton);
	}
	
	public void clickConfirmSaveButton() throws InterruptedException{
		waitForElement(btn_certificateConfirmSaveButton);
		clickElement(btn_certificateConfirmSaveButton);
	}
	
	public void clickSignOffButton() throws InterruptedException{
		waitForElement(btn_certificationSignOffButton);
		clickElement(btn_certificationSignOffButton);
	}
	
}
