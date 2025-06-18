package com.BMP.qa.testcases;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.BMP.qa.base.BaseClass;
import com.BMP.qa.pages.LoginPage;
import com.BMP.qa.utils.ExcelFileUtility;


public class TC_01_LoginTest extends BaseClass {

	LoginPage lp;
	

	@Test(dataProvider = "userCredentials")
	public void loginWithCredentials(String userid, String password) throws InterruptedException {
		lp = new LoginPage(driver);
		lp.setUserID(userid);
		lp.setPassword(password);
		lp.clickOnLoginButton();
		Thread.sleep(1500);	
		
		if (isAlertPresent()) {
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
	
	@DataProvider(name = "userCredentials")
	public Object[][] supplyTestData() throws EncryptedDocumentException, IOException {

		Object[][] data = ExcelFileUtility.readDataFromExcelFile("Sheet1");
		return data;
	}

}
