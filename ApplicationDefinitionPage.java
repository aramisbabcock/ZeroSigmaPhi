package org.cucumbertaf.pageobjects;

import java.util.List;

import org.cucumbertaf.basepageobjects.BasePage;
import org.cucumbertaf.utils.WebTable;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ApplicationDefinitionPage extends BasePage {
	
	public ApplicationDefinitionPage(WebDriver driver) {
        super(driver);
    }
	
	@FindBy(xpath = "//nav/ul/li[5]/a[@class='dropdown-toggle']")
	WebElement drpdwn_applications;
	
	@FindBy(xpath = "//nav/ul/li[5]/ul/li/a")
	List<WebElement> items_applications;
	
	@FindBy(xpath = "//input[@id='searchfield-1025-inputEl']")
	WebElement txt_searchApp;
	
	@FindBy(xpath="//div[@id='ext-gen1099']")
	WebElement btn_searchApp;
	
	@FindBy(xpath="//div[@id='bodyDivTitle']")
	WebElement ele_title;
	
	@FindBy(xpath="//span[text()='Configuration']")
	WebElement btn_config;
	
	@FindBy(xpath = "//button/span[text()='Accounts']")
	WebElement btn_accounts;
	
	@FindBy(xpath="//div[@id='GeneralDetailsContainer']/table/tbody/tr[2]/td/textarea[contains(@id,'editForm')]")
	WebElement  txt_storeprocedure;
	
	@FindBy(xpath="//span[text()='Test Connection']")
	WebElement  btn_testconnection;
	
	@FindBy(xpath="//div[@id='testResultsDiv']")
	WebElement ele_testconnectionmsg;
	
	@FindBy(id = "editForm:saveAppBtn")
	WebElement btn_save;
	
	@FindBy(id="editForm:appName")
	WebElement ele_AppNameInput;
	
	@FindBy(xpath = "//select[@id='editForm:appType']/option[@selected='selected']")
	WebElement ele_AppType;
	
	@FindBy(xpath = "//span[text()=\"Schema\"]")
	WebElement btn_schemaSubTab;
	
	@FindBy(xpath = "//input[@value='Preview']")
	List<WebElement> btn_preview;
	
	@FindBy(xpath = "//div[@id='connectorDebug-account']//table")
	WebElement tbl_ApplicationAccountPreview;
	
	@FindBy(xpath = "//img[@class='x-tool-close']")
	WebElement previewPaneCloseButton;
	
	@FindBy(xpath="//div[contains(@id,'loadmask')]")
	List<WebElement> ele_loading;
	
	@FindBy(id = "searchfield-1103-inputEl")
	WebElement txt_accountsSearch;
	
	@FindBy(id = "ext-gen1237")
	WebElement btn_accountsSearch;
	
	@FindBy(xpath = "//div[contains(@id, 'loadmask')]")
	List<WebElement> ele_loadmaskElements;
	
	@FindBy(xpath = "(//div[@id='accountsGridCmp-body']//table//table)[1]")
	static WebElement tbl_accountsSearchFirstResult;
	
	@FindBy(xpath="//div[@id='gridview-1023']/table")
	static WebElement tbl_searchresult;
	
	public static WebElement getTable() {
        return tbl_searchresult;
    }
	
	public static WebElement getTableAccountsFirst() {
        return tbl_accountsSearchFirstResult;
    }
	
	WebTable table = new WebTable(ApplicationDefinitionPage.getTable());
	
	WebTable tableaccountsfirst = new WebTable(ApplicationDefinitionPage.getTableAccountsFirst());
	
	public void clickapplication() throws InterruptedException {
		waitForElement(drpdwn_applications);
		clickElement(drpdwn_applications);
	}
	
	public void selectapplicationtype(String appname) throws InterruptedException {
		selectElementFromDropDown(items_applications,appname);
	}
	
	public void setApplication(String hrappname) {
		setInputBoxValue(txt_searchApp,hrappname);
	}
	
	public void clicksearch() throws InterruptedException{
		waitForElement(btn_searchApp);
		clickElement(btn_searchApp);
		
	}
	
	public String gettitle() {
		return getElementText(ele_title);
	}
	
	public String getAppName() {
		return getElementValue(ele_AppNameInput);
	}
	
	public String getApptype() {
		return getElementText(ele_AppType);
	}
	
	public boolean isdisplayedsearchtable() throws InterruptedException {
		//System.out.println(tbl_searchresult.getText());
		waitForPage(2000);
    	return isElementDisplayed(tbl_searchresult);
    }
	
	public boolean isdisplayedpreviewtable() throws InterruptedException {
		waitForPage(4000);
		waitForElement(tbl_ApplicationAccountPreview);
		waitForAllElementsToHide(ele_loading);
		WebTable tableprvTable = new WebTable(tbl_ApplicationAccountPreview);
		if (isElementDisplayed(tbl_ApplicationAccountPreview) && tableprvTable.getNumberOfRows()>1) {
			return true;
		}
		return false;
    }
	
	public void clickonconfig() throws InterruptedException{
		clickElement(btn_config);
	}
	
	public void clickaccountstab() throws InterruptedException{
		clickElement(btn_accounts);
	}
	
	public void clickonSchema() throws InterruptedException{
		clickElement(btn_schemaSubTab);
	}
	
	
	public void setusereid(String procedureval )  {
		setInputBoxValue(txt_storeprocedure,procedureval);
	}
	
	public String validateeidset() {
		return getInputBoxValue(txt_storeprocedure);
	}
	
	public void clicktestconnection() throws InterruptedException {
		clickElement(btn_testconnection);
		waitForPage(3000);
	}
	
	public String validatetestconnection() {
		return getElementText(ele_testconnectionmsg);
	}
	
	public void clickonsave() throws InterruptedException{
		clickElement(btn_save);
		waitForPage(1000);
	}
	
	public void clickonPreview() throws InterruptedException{
		waitForElement(btn_preview.get(0));
		clickElement(btn_preview.get(0));
	}
	
	public String getcelldata(int row,int col) {
		return table.getCellValueAt(row, col);
	}
	
	public void clickonclosePreviewPane() {
		clickElement(previewPaneCloseButton);
	}
	
	public void clickcelldata(int row,int col) {
		table.clickAt(row, col);
	}
	
	public String getElementValue(WebElement ele){
		return ele.getAttribute("value");
	}
	
	public void setaccountsearchvalue(String name) throws InterruptedException {
		waitForElement(txt_accountsSearch);
		setInputBoxValue(txt_accountsSearch, name);
	}
	
	public void clicksearchaccounts() {
		clickElement(btn_accountsSearch);
		waitForAllElementsToHide(ele_loadmaskElements);
	}
	
	public String getAccountSearchFirstStatus() {
		return tableaccountsfirst.getCellValueAt(1, 4);
	}
}
