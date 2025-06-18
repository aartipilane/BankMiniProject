package com.BMP.qa.utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

public class JavaUtility {

	public  static String getDateTime() {
		Date date = new Date();
		SimpleDateFormat dateTime = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
		String dateAndTime =dateTime.format(date);
		System.out.println(dateAndTime);
		return dateAndTime;
	}
	
	public static String captureScreenShot(WebDriver driver, String testName) throws IOException
	{
		if (driver == null) {
	        System.out.println("Driver is null. Screenshot not taken.");
	        return null;
	    }
		File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String destFile = System.getProperty("user.dir")+"/ScreenShots/"+testName+JavaUtility.getDateTime()+".png";
		FileHandler.copy(srcFile, new File (destFile));
		return destFile;
	}
	
	public static String  generateEmailID()
	{
		String randomNo = RandomStringUtils.randomAlphanumeric(4);
		String emailID = "user"+randomNo+"@gmail.com";
		return emailID;
		
	}
}
