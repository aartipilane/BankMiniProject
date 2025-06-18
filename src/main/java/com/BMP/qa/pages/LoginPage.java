package com.BMP.qa.pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

	WebDriver driver;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//input[@name='uid']")
	private WebElement txtUserID;

	@FindBy(xpath = "//input[@type='password']")
	private WebElement txtPassword;

	@FindBy(xpath = "//input[@type='submit']")
	private WebElement btnLogin;

	@FindBy(xpath = "//input[@type='reset']")
	private WebElement btnReset;

	@FindBy(xpath = "//a[@href='Managerhomepage.php']")
	private WebElement managerLinkText;

	public void setUserID(String uid) {
		txtUserID.sendKeys(uid);
	}

	public void setPassword(String password) {
		txtPassword.sendKeys(password);
	}

	public void clickOnLoginButton() {
		btnLogin.click();
	}

	public void clickOnReset() {
		btnReset.click();
	}

//	public void loginToApp(String uid, String password) {
//		txtUserID.sendKeys(uid);
//		txtPassword.sendKeys(password);
//		btnLogin.click();
//	}

	

	public String getLoginErrorAlertMessage() {
		Alert alert = driver.switchTo().alert();
		String getAlertMessage = alert.getText();
		alert.accept();
		return getAlertMessage;
	}

	public boolean isManagerLinkDisplayed() {

		return managerLinkText.isDisplayed();
	}
}
