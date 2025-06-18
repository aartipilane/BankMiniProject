package com.BMP.qa.base;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseClass {

	protected WebDriver driver;
	public static Properties prop;	
	public static org.apache.logging.log4j.Logger logger;
	
	@BeforeMethod
	public void setUp() throws IOException {
		logger=LogManager.getLogger(BaseClass.class.getName());
		logger.info("Test Executation is started");

		readDataFromPropertiesFile();
		String url=prop.getProperty("url");
		String browser = prop.getProperty("browser");
		
		if(browser.equals("chrome"))
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
		else if(browser.equals("firefox"))
		{
			driver = new FirefoxDriver();
		}
		else if(browser.equals("edge"))
		{
			driver = new EdgeDriver();
		}
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.get(url);


	}

	@AfterMethod
	public void tearDown() {
		logger.info("Test execution is ended here");
		driver.quit();
	}
	
	
	public void readDataFromPropertiesFile() throws IOException
	{
		FileInputStream fis = new FileInputStream("/home/cm_geeta/AutomationTesting/Practice2024/BankMiniProject/src/main/java/com/BMP/qa/config/userData.properties");
		prop = new Properties();
		prop.load(fis);		
	}
	
	public boolean isAlertPresent() {
		try {
			driver.switchTo().alert();
			return true;
		} catch (NoAlertPresentException e) {
			return false;
		}
	}
	
	
}
