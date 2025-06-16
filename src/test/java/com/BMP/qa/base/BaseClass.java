package com.BMP.qa.base;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;

public class BaseClass {

	WebDriver driver;
	public static Properties prop;	
	
	public void readDataFromPropertiesFile() throws IOException
	{
		FileInputStream fis = new FileInputStream("/home/cm_geeta/AutomationTesting/Practice2024/BankMiniProject/src/main/java/com/BMP/qa/config/userData.properties");
		prop = new Properties();
		prop.load(fis);		
	}

	
	public WebDriver launchBrowserandURL(String browserName) throws IOException
	{
		readDataFromPropertiesFile();
		
		String url=prop.getProperty("url");
		
		if(browserName.equals("chrome"))
		{
			ChromeOptions option = new ChromeOptions();
			option.addArguments("--headless");
			option.addArguments("--no-sandbox");
			option.addArguments("--disable-dev-shm-usage");
        	option.addArguments("--remote-allow-origins=*");
        	option.addArguments("--disable-gpu");
        	option.addArguments("user-data-dir=/tmp/temporary-profile-" + System.currentTimeMillis());

        	driver = new ChromeDriver(option);
		}
		else if(browserName.equals("firefox"))
		{
			driver = new FirefoxDriver();
		}
		else if(browserName.equals("edge"))
		{
			driver = new EdgeDriver();
		}
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.get(url);
		return driver;
		
	}
	
	@AfterMethod
	public void tearDown()
	{
		driver.quit();
	}
}
