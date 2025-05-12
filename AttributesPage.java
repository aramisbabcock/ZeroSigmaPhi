package org.cucumbertaf.pageobjects;

import java.util.ArrayList;
import java.util.List;

import org.cucumbertaf.basepageobjects.BasePage;
import org.cucumbertaf.utils.PropertyUtil;
import org.cucumbertaf.utils.WebTable;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class AttributesPage extends BasePage {

	public AttributesPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//nav/ul/li[4]/a[@class='dropdown-toggle']")
	WebElement drpdwn_identities;

	@FindBy(xpath = "//nav/ul/li[4]/ul/li/a")
	WebElement items_identities;

	@FindBy(xpath = "//*[@id=\"bodyDivTitle\"]")
	WebElement ele_identityWarehouse;

	@FindBy(xpath = "//div[contains(@id,'paginggrid')]")
	WebElement ele_identitesParentElement;

	@FindBy(xpath = "//div[contains(@id, 'loadmask')]")
	List<WebElement> ele_loadingbox;

	@FindBy(xpath = "//input[contains(@placeholder, 'Filter by Identity Name')]")
	WebElement txt_searchItentity;

	@FindBy(xpath = "//div[contains(@class,'search-trigger')]")
	WebElement btn_searchicon;
	
	@FindBy(id="tab-1026-btnInnerEl")
	WebElement ele_entitlementsTab;
	
	@FindBy(xpath = "//div[@id = 'identityentitlementsgrid-body']//div[@class='x-grid-rowwrap-div']/table/tbody/tr/td[2]")
	List<WebElement> ele_entitlementNames;
	
	@FindBy(xpath = "//div[@id = 'identityentitlementsgrid-body']//div[@class='x-grid-rowwrap-div']/table/tbody/tr/td[1]/div[@class='x-grid-cell-inner ']")
	List<WebElement> ele_entitlementType;
	
	
	@FindBy(xpath = "//div[@id = 'identityentitlementsgrid-body']//div[@class='x-grid-rowwrap-div']/table/tbody/tr/td[2]")
	List<WebElement> ele_RoleNames;

	
	@FindBy(xpath="//div[@id='gridview-1030']/table")
	static WebElement tbl_searchresult;
	
	@FindBy(xpath = "//td[ @class='attributeTitleColumn' and contains(.,'User Name')]/following-sibling::td/span")
	WebElement ele_id;
	
	@FindBy(xpath = "//span[.='Change Password']")
	WebElement ele_passwordReset;
	
	@FindBy(id = "editForm:identityPassword")
	WebElement input_userPassword;
	
	@FindBy(id = "editForm:confirmPassword")
	WebElement input_confirmPassword;
	
	@FindBy(id = "editForm:saveButton")
	WebElement btn_save;
	
	@FindBy(id = "spErrorMsgsDiv")
	WebElement ele_responceElement;
	
	public static WebElement getTable() {
        return tbl_searchresult;
    }
	
	WebTable table = new WebTable(AttributesPage.getTable());
	
	public void clickcelldata(int row,int col) throws InterruptedException {
		waitForPage(2);

		int repeat = 0;
		while (repeat < 4) {
			++repeat;
			waitForPage(1);
			try {
				WebElement ele= driver.findElement(By.xpath("//div[@id='gridview-1030']//tr[2]"));
				ele.click();
				break;
			} catch (StaleElementReferenceException e) {
				//e.printStackTrace();
			}
		}
		
		
	}

	public void clickidentities() throws InterruptedException {
		clickElement(drpdwn_identities);
	}

	public void clickidentitywarehouse() throws InterruptedException {
		waitForElement(items_identities);
		clickElement(items_identities);

	}

	public String validateidwarehousetitle() throws InterruptedException {
		waitForElement(ele_identityWarehouse);
		return getElementText(ele_identityWarehouse);
	}

	public void inputusertoSearch(String usr) throws InterruptedException {
		waitForElement(ele_identitesParentElement);
		waitForAllElementsToHide(ele_loadingbox);
		setInputBoxValue(txt_searchItentity, usr);
	}

	public void clickidentitySearchButton() throws InterruptedException {
		clickElement(btn_searchicon);
	}
	
	public void clickonEntitlementsTab() throws InterruptedException{
		waitForElement(ele_entitlementsTab);
		clickElement(ele_entitlementsTab);
	}
	
	public List<WebElement> getEntitlementNamesOnAccount(){
		waitForAllElements(ele_entitlementNames);
		return ele_entitlementNames;
	}
	
	public List<WebElement> getEntitlementAttributes(){
		waitForAllElements(ele_entitlementType);
		return ele_entitlementType;
	}

	public String getid() {
		return ele_id.getText().trim();
	}
	
	
	public void resetPassword() {
		clickElement(ele_passwordReset);
	}
	
	public String setNewPassword() throws InterruptedException {
		waitForElement(input_confirmPassword);
		
		input_userPassword.clear();
		input_confirmPassword.clear();
		setInputBoxValue(input_confirmPassword, PropertyUtil.getProperty("password"));
		setInputBoxValue(input_userPassword, PropertyUtil.getProperty("password"));
		clickElement(btn_save);
		waitForElement(ele_responceElement);
		return ele_responceElement.getText();
	}

}
