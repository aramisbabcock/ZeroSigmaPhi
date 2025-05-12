package org.cucumbertaf.pageobjects;

import java.util.List;

import org.cucumbertaf.basepageobjects.BasePage;
import org.jsoup.internal.ReturnsAreNonnullByDefault;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccessRequestPage extends BasePage {

	public AccessRequestPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//nav/ul/li[3]/a[@class='dropdown-toggle']")
	WebElement ele_mywork;
	
	@FindBy(xpath =  "//div[@id='menuMainDiv']//li/a[contains(.,'Access Requests')]")
	WebElement ele_AccessRequestItemElement;
	
	@FindBy(id = "cardListSearchInput")
	WebElement accessRequestInputBox; 
	
	@FindBy(id = "cardListSearchBtn")
	WebElement accessRequestSearchBtn;
	
	@FindBy(xpath = "//button[contains(@id, 'requestDetailsBtn')]")
	List<WebElement> btn_accessRequestDetails;
	
	@FindBy(xpath = "//button[contains(@id, 'interactionsDetailBtn')]")
	List<WebElement> btn_workItemDetails;
	
	@FindBy(xpath = "//*[contains(text(), 'Execution Status:')]/parent::li")
	WebElement ele_executionStatus;
	
	@FindBy(tagName = "sp-identity-request-interactions-email")
	List<WebElement> workItemOwners;
	
	@FindBy(xpath = "//sp-identity-request-interactions//td[@headers='Description']")
	List<WebElement> workItemDescriptions;
	
	@FindBy(xpath = "//span[contains(.,'Work Item ID')]")
	WebElement workItemID;
	
	@FindBy(xpath ="//sp-identity-request-interactions//tbody//tr")
	List<WebElement> ele_interactionRows;
	
	@FindBy(xpath="//div[contains(@id,'loadmask')]")
	List<WebElement> ele_loading;
	
	
	public void clickonmywork() throws InterruptedException {
		clickElement(ele_mywork);
	}
	
	public void clickonAccessRequest() throws InterruptedException {
		waitForElement(ele_AccessRequestItemElement);
		clickElement(ele_AccessRequestItemElement);
		waitForElement(accessRequestInputBox);
	}
	
	public void searchAccessRequest(String reqID) throws InterruptedException{
		setInputBoxValue(accessRequestInputBox, reqID);
		clickElement(accessRequestSearchBtn);	
		waitForAllElementsToHide(ele_loading);
	}
	
	public int openDetails() throws InterruptedException {
		waitForAllElements(btn_accessRequestDetails);
		if(btn_accessRequestDetails.size() == 1) {
			clickElement(btn_accessRequestDetails.get(0));
			return 1;
		} else {
			return btn_accessRequestDetails.size();
		}
	}
	
	public int isWorkLeft() throws InterruptedException {
		waitForElement(ele_executionStatus);
		String tempString = ele_executionStatus.getText();
		System.out.println(tempString.contains("Executing"));
		if(!ele_executionStatus.getText().contains("Executing")) {
			return -1;
		}
		
		String templateString = "row-idx-column-4-Status";
		for(int i=0; i< ele_interactionRows.size(); ++i) {
			WebElement temp =  driver.findElement(By.id(templateString.replace("idx", String.valueOf(i))));
			if(temp.getText().equalsIgnoreCase("open")) {
				return i;
			}
		}
		
		return -1;
	}
	
	public String getApproverDescription() throws InterruptedException {
		waitForAllElements(workItemDescriptions);
		return workItemDescriptions.get(isWorkLeft()).getText().trim();
	}
	
	public String getApproverName() throws InterruptedException {
		waitForAllElements(workItemOwners);
		return workItemOwners.get(isWorkLeft()).getText().trim();
	}
	
	public void openManagerWorkitem() throws InterruptedException {
		clickElement(btn_workItemDetails.get(isWorkLeft()));
	}
	
	public String getWorkItemID() throws InterruptedException {
		waitForElement(workItemID);
		String textWorkItem = workItemID.getText();
		textWorkItem = textWorkItem.replaceAll("[^0-9]", "");
		return textWorkItem;
	}

}
