package com.BMP.qa.testcases;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.WebDriver;
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
	
	
	public TC_01_LoginTest()
	{
		super();
	}
	
	@BeforeMethod
	public void setUp() throws IOException
	{
		readDataFromPropertiesFile();
		driver=launchBrowserandURL(prop.getProperty("browser"));
		lp=new LoginPage(driver);
		
	}
	
	@AfterMethod
	public void tearDown()
	{
		driver.quit();
	}
	
	@DataProvider(name="userCredentials")
	public Object[][] supplyTestData() throws EncryptedDocumentException, IOException
	{
		
		Object[][] data = ExcelFileUtility.readDataFromExcelFile("Sheet1");
		return data;
	}
	
	@Test(dataProvider="userCredentials")
	public void loginWithValidCredentials(String userid , String password)
	{
		lp.setUserID(userid);
		lp.setPassword(password);
		lp.clickOnLoginButton();
		
	}
	
}
