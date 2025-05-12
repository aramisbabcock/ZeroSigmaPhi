package org.cucumbertaf.pageobjects;

import java.util.List;

import org.cucumbertaf.basepageobjects.BasePage;
import org.cucumbertaf.utils.WebTable;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class TaskPage extends BasePage {
	public TaskPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//nav/ul/li[7]/a[@class='dropdown-toggle']")
	WebElement drpdwn_setup;

	@FindBy(xpath = "//nav/ul/li[7]/ul/li/a")
	List<WebElement> items_setuplinks;

	@FindBy(xpath = "//div[@id='bodyDivTitle']")
	WebElement ele_tasktitle;

	@FindBy(xpath = "//span[text()='Tasks']")
	WebElement ele_task;

	@FindBy(xpath = "//input[@id='tasksSearchField-inputEl']")
	WebElement txt_tasksearch;

	@FindBy(xpath = "//*[@id='tasksSearchField-bodyEl']/table/tbody/tr/td[3]")
	WebElement ele_search;

	@FindBy(xpath = "//div/h1[text()='Edit Task']")
	WebElement ele_edittask;

	@FindBy(xpath = "//*[contains(@id, 'gridview-1026-bd-')]/td/table/tbody/tr[2]/td[1]")
	WebElement ele_systemtaskresult;

	@FindBy(xpath = "//span[text()='Save and Execute']")
	WebElement btn_execute;

	@FindBy(xpath = "//div[@id='messagebox-1009-displayfield-inputEl']")
	WebElement ele_popupmessage;

	@FindBy(xpath = "//button[@id='button-1013-btnEl']")
	WebElement btn_ok;

	@FindBy(xpath = "//button[@id='tab-1091-btnEl']")
	WebElement btn_taskresult;

	@FindBy(xpath = "//input[@id='resultsSearchField-inputEl']")
	WebElement txt_searchresult;

	@FindBy(xpath = "//*[@id='toolbar-1070-targetEl']/table[1]/tbody/tr/td[2]/table/tbody/tr/td[3]")
	WebElement btn_searchresulticon;

	@FindBy(xpath = "//button[@id='button-1086-btnEl']")
	WebElement btn_Refresh;

	@FindBy(xpath = "//div[@id='gridview-1069']/table")
	static WebElement tbl_taskresulttable;

	public static WebElement getsearchTable() {
		return tbl_taskresulttable;
	}

	WebTable table = new WebTable(TaskPage.getsearchTable());

	public void clicksetup() throws InterruptedException {
		clickElement(drpdwn_setup);
	}

	public void clicktasks(String taskname) throws InterruptedException {
		selectElementFromDropDown(items_setuplinks, taskname);
	}

	public String gettasktitle() throws InterruptedException {
		waitForElement(ele_tasktitle);
		return getElementText(ele_tasktitle);
	}

	public String getedittasktitle() throws InterruptedException {
		waitForElement(ele_edittask);
		return getElementText(ele_edittask);
	}

	public void clicktasktab() throws InterruptedException {
		clickElement(ele_task);
	}

	public void searchtask(String tasknameval) throws InterruptedException {
		setInputBoxValue(txt_tasksearch, tasknameval);
	}

	public void clicksearchbutton() throws InterruptedException {
		clickElement(ele_search);
		waitForPage(1000);
	}

	public void clicksaveexecute() throws InterruptedException {
		clickElement(btn_execute);
	}

	
	public String getNameFromSearchTable() throws InterruptedException {
		waitForPage(2000);
		return getElementText(ele_systemtaskresult);
		
	}
	
	public void clicktasktype() throws InterruptedException {
		clickElement(ele_systemtaskresult);
		waitForPage(1000);
	}

	public String getmessagetext() throws InterruptedException {
		waitForElement(ele_popupmessage);
		return getElementText(ele_popupmessage);
	}

	public void clickok() throws InterruptedException {
		clickElement(btn_ok);
	}

	public void clicktaskresults() throws InterruptedException {
		waitForElement(btn_taskresult);
		clickElement(btn_taskresult);
		waitForPage(1000);
	}

	public void settaskresult(String taskname) {
		setInputBoxValue(txt_searchresult, taskname);
	}

	public void clicktaskresulticon() throws InterruptedException {
		clickElement(btn_searchresulticon);
		waitForPage(1000);
	}

	public void clickrefresh() throws InterruptedException {
		clickElement(btn_Refresh);
		waitForPage(5000);
	}

	public String getcelldata(int row, int col) {
		return table.getCellValueAt(row, col);
	}

}
