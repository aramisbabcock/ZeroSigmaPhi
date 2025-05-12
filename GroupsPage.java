package org.cucumbertaf.pageobjects;

import java.util.List;

import org.cucumbertaf.basepageobjects.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class GroupsPage extends BasePage {
	public GroupsPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//nav/ul/li[7]/a[@class='dropdown-toggle']")
	WebElement drpdwn_setup;

	@FindBy(xpath = "//nav/ul/li[7]/ul/li/a")
	List<WebElement> items_links;

	@FindBy(xpath = "//div[@id='bodyDivTitle']")
	WebElement ele_tasktitle;

	@FindBy(xpath = "//button[.='Workgroups']")
	WebElement btn_WorkgroupTab;

	@FindBy(id = "workgroupSearchField-inputEl")
	WebElement input_workgroupSearch;

	@FindBy(xpath = "//td[@id='workgroupSearchField-inputCell']/following-sibling::td/div[@role='button' and contains(@class, 'x-form-search-trigger')]")
	WebElement btn_workgroupSearch;

	@FindBy(xpath = "//div[@id='membersGrid']//tr[2]/td[2]")
	WebElement ele_firstUserInWorkgroup;

	public void clicksetup() throws InterruptedException {
		clickElement(drpdwn_setup);
	}

	public void clickGroups(String itemName) throws InterruptedException {
		selectElementFromDropDown(items_links, itemName);
	}

	public String gettitle() throws InterruptedException {
		waitForElement(ele_tasktitle);
		return getElementText(ele_tasktitle);
	}

	public void clickWorkgroupsTab() throws InterruptedException {
		clickElement(btn_WorkgroupTab);
		waitForElement(input_workgroupSearch);
	}

	public void searchForWorkgroup(String workgroup) throws InterruptedException {
		setInputBoxValue(input_workgroupSearch, workgroup);
		clickElement(btn_workgroupSearch);
		
		int repeat = 0;
		while (repeat < 4) {
			++repeat;
			waitForPage(1);
			try {
				WebElement searchedItem = driver.findElement(By.xpath("//div[.='" + workgroup + "']"));
				clickElement(searchedItem);
				break;
			} catch (StaleElementReferenceException e) {
				//e.printStackTrace();
			}
		}

	}

	public String getuserFromWorkgroup() throws InterruptedException {
		waitForElement(ele_firstUserInWorkgroup);
		return ele_firstUserInWorkgroup.getText();
	}

}
