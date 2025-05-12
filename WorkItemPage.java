package org.cucumbertaf.pageobjects;

import java.util.List;

import org.cucumbertaf.basepageobjects.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class WorkItemPage extends BasePage {

	public WorkItemPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//nav/ul/li[3]/a[@class='dropdown-toggle']")
	WebElement ele_mywork;

	@FindBy(xpath = "//nav/ul/li[3]/ul/li/a")
	List<WebElement> ele_workitems;

	@FindBy(xpath = "//*[@id='ext-gen1035']/div[2]/div[2]/div/div/sp-work-item-list/div/sp-card-list/section[1]/div/div[1]/sp-header-left/span/h1")
	WebElement ele_workitemtitle;

	@FindBy(xpath = "//input[@id='cardListSearchInput']")
	WebElement ele_description;

	@FindBy(xpath = "//button[@id='cardListSearchBtn']")
	WebElement ele_search;

	@FindBy(xpath = "//div[contains(@id,'workItemCard-')]/div/div/div[2]/span/div[1]/table/tbody/tr/td[1]/div/span/span")
	WebElement ele_workitemaction;

	@FindBy(xpath = "//button[contains(@id, 'workitem-goto-btn-')]")
	WebElement ele_view;

	@FindBy(xpath = "//button[contains(@id, 'workitem-forward-btn')]")
	WebElement btn_workitemforward;

	@FindBy(id = "ownerNameSuggestBoxWorkItemListForward")
	WebElement txt_forwardToUserInput;

	@FindBy(xpath = "//div[contains(@class, 'identity-suggest-container')]//ul/li")
	List<WebElement> ele_forwardtoUserSuggested;

	@FindBy(xpath = "//button[contains(text(), 'Forward')]")
	WebElement btn_submitforward;

	@FindBy(xpath = "//button[contains(@id, 'workitem-details-btn')]")
	WebElement btn_workitemdetails;

	@FindBy(xpath = "//div/h1")
	WebElement ele_approvalpageTitle;

	@FindBy(xpath = "//button[contains(@id, 'btnApproveApproval0Item')]")
	List<WebElement> btn_approveItem;

	@FindBy(xpath = "//button[contains(@id, 'btnRejectApproval0Item')]")
	List<WebElement> btn_denyItem;

	@FindBy(xpath = "//div[@id='completionDialog']//button[contains(text(),'Complete')]")
	WebElement btn_completeworkitem;

	public void clickonmywork() throws InterruptedException {
		clickElement(ele_mywork);
	}

	public void selectworkitems(String myworktype) throws InterruptedException {
		selectElementFromDropDown(ele_workitems, myworktype);
		waitForPage(3000);
	}

	public String gettitle() throws InterruptedException {
		waitForElement(ele_workitemtitle);
		return getElementText(ele_workitemtitle);
	}

	public void searchworkitem(String WorkItemName) {
		setInputBoxValue(ele_description, WorkItemName);
	}

	public void clicksearch() throws InterruptedException {
		clickElement(ele_search);

	}

	public String getdescription() throws InterruptedException {
		waitForElement(ele_workitemaction);
		return getElementText(ele_workitemaction);
	}

	public void clickview() throws InterruptedException {
		clickElement(ele_view);
	}
	
	public void clickWorkItemForward() throws InterruptedException {
		clickElement(btn_workitemforward);
	}

	public String gettitleApprovalPage() throws InterruptedException {
		waitForElement(ele_approvalpageTitle);
		return getElementText(ele_approvalpageTitle);
	}

	public void clickdetails() throws InterruptedException {
		clickElement(btn_workitemdetails);
	}

	public void clickforward() throws InterruptedException {
		clickElement(btn_workitemforward);
	}
	
	public void submitforward() throws InterruptedException {
		clickElement(btn_submitforward);
	}
	
	public void fillForwardtoinput(String forwardto_user) throws InterruptedException{
		waitForElement(txt_forwardToUserInput);
		setInputBoxValue(txt_forwardToUserInput, forwardto_user);
	}
	
	public List<WebElement> getforwarduseroptions() throws InterruptedException{
		waitForAllElements(ele_forwardtoUserSuggested);
		return ele_forwardtoUserSuggested;
	}
	
	public void chooseforwarduseroptions(int index) throws InterruptedException{
		clickElement(ele_forwardtoUserSuggested.get(index));
	}

	public void clickapproveWorkitem() throws InterruptedException {
		waitForAllElements(btn_approveItem);
		for(WebElement element: btn_approveItem) {
			clickElement(element);
		}
	}

	public void clickdenyWorkitem() throws InterruptedException {
		waitForAllElements(btn_denyItem);
		for(WebElement element : btn_denyItem) {
			clickElement(element);
		}
	}
	
	public void clickCompleteButton() throws InterruptedException {
		waitForElement(btn_completeworkitem);
		clickElement(btn_completeworkitem);
	}

}
