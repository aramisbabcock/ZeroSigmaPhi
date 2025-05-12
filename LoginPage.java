package org.cucumbertaf.pageobjects;

import org.cucumbertaf.basepageobjects.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {
	public LoginPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//*[@id='loginForm:accountId']")
	private WebElement txt_username;

	@FindBy(xpath = "//*[@id='loginForm:password']")
	private WebElement txt_password;

	@FindBy(xpath = "//a[contains(text(),'Agree')]")
	private WebElement btn_agreeBtn;

	@FindBy(xpath = "//*[@id='loginForm:loginButton']")
	private WebElement btn_login;

	@FindBy(xpath = "//div/h1[normalize-space()='Home']")
	private WebElement ele_homepage;

	public void setusername(String usernameval) throws InterruptedException {
		setInputBoxValue(txt_username, usernameval);
	}

	public void setpassword(String pwdval) throws InterruptedException {
		setInputBoxValue(txt_password, pwdval);
	}

	public boolean isAgreeButtonDisplayed() {
		return isElementDisplayed(btn_agreeBtn);
	}

	public void clickAgreeButton() throws InterruptedException {
		clickElement(btn_agreeBtn);
		waitForElement(btn_login);
	}

	public void clicklogin() throws InterruptedException {
		clickElement(btn_login);
	}

	public boolean isdisplayedhome() throws InterruptedException {
		waitForElement(ele_homepage);
		return isElementDisplayed(ele_homepage);
	}

	public void load(String url) {
		driver.get(url);
	}

}
