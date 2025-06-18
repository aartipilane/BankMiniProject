package com.BMP.qa.listeners;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.BMP.qa.utils.ExtentReporter;
import com.BMP.qa.utils.JavaUtility;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class MyListeners implements ITestListener {

	ExtentReports extentReports;
	ExtentTest extentTest;
	String testName;

	public void onStart(ITestContext context) {

		try {
			extentReports = ExtentReporter.generateExtentReports();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void onTestStart(ITestResult result) {

		testName = result.getName();
		extentTest = extentReports.createTest(testName);
		extentTest.log(Status.INFO, testName + " starts execution.-->>>>>>>>>");
	}

	public void onTestSuccess(ITestResult result) {

		testName = result.getName();
		extentTest = extentReports.createTest(testName);
		extentTest.log(Status.PASS, testName + " got successfully executed.-->>>>>>>> ");
	}

	public void onTestFailure(ITestResult result) {
		testName = result.getName();
		
		WebDriver driver = null;
		try {
			driver = (WebDriver)result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
			String screenShotPath = null;
			try {
				screenShotPath = JavaUtility.captureScreenShot(driver, testName);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		extentTest.addScreenCaptureFromPath(screenShotPath);
		extentTest.log(Status.FAIL, result.getThrowable());
		extentTest.log(Status.INFO,testName+" got failed");
	}

	public void onTestSkipped(ITestResult result) {

		testName = result.getName();
		extentTest.log(Status.SKIP, testName + " got skipped.-->>>>>>>>");
		extentTest.log(Status.INFO, result.getThrowable());
	}

	public void onFinish(ITestContext context) {

		System.out.println("Project execution finished");
		extentReports.flush();
	}
}
