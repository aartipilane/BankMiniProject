package com.BMP.qa.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReporter {

	public static ExtentReports generateExtentReports() throws IOException {
		ExtentReports extentReports = new ExtentReports();
		File extentReportFile = new File(
				System.getProperty("user.dir") + "/test-output/ExtentReports/eR+" + JavaUtility.getDateTime() + ".html");
		ExtentSparkReporter sparkR = new ExtentSparkReporter(extentReportFile);
		sparkR.config().setTheme(Theme.DARK);
		sparkR.config().setReportName("Bank Mini Project Testing Report");
		sparkR.config().setDocumentTitle("Bank Mini Project");
		sparkR.config().setTimeStampFormat("yyyy-mm-dd_HH-mm-ss");
		extentReports.attachReporter(sparkR);

		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(
				"/home/cm_geeta/AutomationTesting/Practice2024/BankMiniProject/src/main/java/com/BMP/qa/config/userData.properties");
		prop.load(fis);

		extentReports.setSystemInfo("Application URL: ", prop.getProperty("url"));
		extentReports.setSystemInfo("Browser Name: ", prop.getProperty("browser"));
		extentReports.setSystemInfo("Operating System: ", System.getProperty("os.name"));
		extentReports.setSystemInfo("Java Version: ", System.getProperty("java.version"));
		
		return extentReports;
	}
}
