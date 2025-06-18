package com.BMP.qa.testcases;

import java.io.IOException;

import org.testng.annotations.Test;

import com.BMP.qa.base.BaseClass;
import com.BMP.qa.pages.AddCustomerPage;
import com.BMP.qa.pages.LoginPage;
import com.BMP.qa.utils.JavaUtility;

public class TC_02_AddCustomerTest extends BaseClass {
	LoginPage lp;
	AddCustomerPage cp;


	@Test
	public void addNewCustomer() throws IOException, InterruptedException
	{
		lp=new LoginPage(driver);
		cp = new AddCustomerPage(driver);
		
		readDataFromPropertiesFile();
		lp.setUserID(prop.getProperty("userid"));
		lp.setPassword(prop.getProperty("password"));
		lp.clickOnLoginButton();
		
		cp.addNewCustomer("Aarti", "15/07/1998", "Pune", "Pune", "MH", "411046", "8753212345",JavaUtility.generateEmailID() );
		Thread.sleep(1500);
		

	}
}
