package com.BMP.qa.testcases;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.BMP.qa.base.BaseClass;
import com.BMP.qa.pages.LoginPage;
import com.BMP.qa.utils.ExcelFileUtility;


public class TC_01_LoginTest extends BaseClass {

	public WebDriver driver;
	LoginPage lp;

	public TC_01_LoginTest() {
		super();
	}

	@BeforeMethod
	public void setUp() throws IOException {
		readDataFromPropertiesFile();
		driver = launchBrowserandURL(prop.getProperty("browser"));
		lp = new LoginPage(driver);
		logger.info("Test Executation is started");

	}

	@AfterMethod
	public void tearDown() {
		logger.info("Test execution is ended here");
		driver.quit();
	}

	@DataProvider(name = "userCredentials")
	public Object[][] supplyTestData() throws EncryptedDocumentException, IOException {

		Object[][] data = ExcelFileUtility.readDataFromExcelFile("Sheet1");
		return data;
	}

//	@Test(dataProvider = "userCredentials",priority=1)
//	public void loginWithValidCredentials(String userid, String password) {
//		lp.setUserID(userid);
//		lp.setPassword(password);
//		lp.clickOnLoginButton();
//		
//		
//		Assert.assertEquals(lp.getTitle(),"GTPL Bank Home Page","Expexted Tittle is diplayed");
//
//	}
	@Test(dataProvider = "userCredentials")
	public void loginWithCredentials(String userid, String password) throws InterruptedException {
		lp.setUserID(userid);
		lp.setPassword(password);
		lp.clickOnLoginButton();
		Thread.sleep(1500);	
		
		if (lp.isAlertPresent()) {
			String alertText = lp.getLoginErrorAlertMessage(); // This will also accept the alert
			System.out.println("❌ Invalid Login - Alert: " + alertText);
			logger.info("Logger - Invalid Login");
			Assert.assertEquals(alertText.trim(), "User is not valid", "Invalid User ");
		} else {
			// If no alert, continue with valid login check
			Assert.assertTrue(lp.isManagerLinkDisplayed(), "Login successful but Manager link not found.");
			System.out.println("✅ Login Successful for user: " + userid);
			logger.info("Logger - Login successfully");
		}
	}

}
