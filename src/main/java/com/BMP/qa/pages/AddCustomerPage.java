package com.BMP.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AddCustomerPage {

	WebDriver driver;
	
	public AddCustomerPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(linkText="New Customer")
	private WebElement link_NewCustomer;
	
	@FindBy(xpath="//input[@name='name']")
	private WebElement txt_CustomerName;
	
	@FindBy(xpath="(//input[@name='rad1'])[2]")
	private WebElement radioBtn_gender;
	
	@FindBy(xpath="//input[@id='dob']")
	private WebElement txt_DOB;
	
	@FindBy(xpath="//textarea[@name='addr']")
	private WebElement txt_Address;
	
	@FindBy(xpath="//input[@name='city']")
	private WebElement txt_City;
	
	@FindBy(xpath="//input[@name='state']")
	private WebElement txt_State;
	
	@FindBy(xpath="//input[@name='pinno']")
	private WebElement txt_Pincode;
	
	@FindBy(xpath="//input[@name='telephoneno']")
	private WebElement txt_TelNo;
	
	@FindBy(xpath="//input[@name='emailid']")
	private WebElement txt_EmailID;
	
	@FindBy(xpath="//input[@name='sub']")
	private WebElement btn_Submit;
	
	public void addNewCustomer(String cName, String dob, String addr, String city, String state,String pincode,String telNo,String emailID)
	{
		link_NewCustomer.click();
		txt_CustomerName.sendKeys(cName);
		radioBtn_gender.click();
		txt_DOB.sendKeys(dob);
		txt_Address.sendKeys(addr);
		txt_City.sendKeys(city);
		txt_State.sendKeys(state);
		txt_Pincode.sendKeys(pincode);
		txt_TelNo.sendKeys(telNo);
		txt_EmailID.sendKeys(emailID);
		btn_Submit.click();
	}
}
