package com.app.ck.pages.admin;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.app.ck.base.Base;
import com.app.ck.base.WrapperMethods;
import com.app.ck.utilities.Utilities;
import com.aventstack.extentreports.ExtentTest;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class AdminPendingCashoutsPage extends WrapperMethods {
	
	
	public AdminPendingCashoutsPage(AppiumDriver<MobileElement> driver, ExtentTest testInfo) {
		
		this.driver = driver;
		this.testInfo = testInfo;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@FindBy(how = How.ID, using = "sby") 
	MobileElement searchByDropDown;

	@FindBy(how = How.ID, using = "user") 
	MobileElement keywordTextbox;
	
	@FindBy(how = How.ID, using = "btnCreateCashout") 
	MobileElement createCashout;

	public void selectSearchByDropDown(String dropDownValue) {

		reportStep("About to select value "+dropDownValue+" from drop down in pending cashout page", "INFO");

		if(selectValuesFromDropdown(searchByDropDown, dropDownValue)) {
		
		reportStep("Successfully selected value "+dropDownValue+" from the dropdown in pending cashout page", "PASS");
		}else {
		
		reportStep("Not able to select value "+dropDownValue+" from dropdown in pending cashout page", "FAIL");
		}
	}

	public void enterKeyword(String keyword) {

		reportStep("About to enter keyword in keyword text box", "INFO");

		if(enterTextInChrome(keywordTextbox, keyword)) {

			reportStep("Successfully entered keyword "+keyword+" in keyword text box in pending cashout page", "PASS");
		}else {

			reportStep("Not able to enter keyword "+keyword+" in keyword text box in pending cashout page", "FAIL");
		}
		
	}

	public void clickSubmit() {

		reportStep("About to click submit in pending cashout page", "INFO");
		
		if(jsClickUsingID("btnSubmit")) {

			reportStep("Successfully clicked on the submit in pending cashout page", "PASS");
		}else {

			reportStep("Not able to click on the submit in pending cashout page", "FAIL");
		}
		
	}

	public void clickEmail(String email) {

		reportStep("About to click email "+email+" in pending cashout page", "INFO");
		
		String emailXpath = "//a[contains(.,'"+email+"')]";
		
		if(isElementLocatedByXpathPresent(emailXpath)){
			reportStep("Verified the presence of share button", "INFO");

		}else {
			reportStep("Not able to verify the presence share button", "FAIL");

		}
		
		MobileElement emailAddress = driver.findElement(By.xpath(emailXpath));
		
		if(clickAfterWait(emailAddress)) {

			reportStep("Successfully clicked email "+email+" in pending cashout page", "PASS");
		}else {

			reportStep("Not able to click email "+email+" in pending cashout page", "FAIL");
		}
		
	}

	public void clickCreateCashout() {

		reportStep("About to click create cashout in pending cashout page", "INFO");
		
		try {
			
			clickAfterWait(createCashout);
			
		 } catch (Exception e) {
		
			reportStep("Not Able to CashOut the Pending Cashouts - Admin failures", "Fail");
			
		}
		
	}

	public void clickOk() {

		reportStep("About to click OK in pending cashout page", "INFO");
		
		String okXpath = "//*[text()='OK']";
		
		if(isElementLocatedByXpathPresent(okXpath)){
			reportStep("Verified the presence of ok button", "INFO");

		}else {
			reportStep("Not able to verify the presence ok button", "FAIL");

		}
		
		MobileElement emailAddress = driver.findElement(By.xpath(okXpath));
		
		if(clickAfterWait(emailAddress)) {

			reportStep("Successfully clicked ok "+okXpath+" in pending cashout page", "PASS");
		}else {

			reportStep("Not able to click ok "+okXpath+" in pending cashout page", "FAIL");
		}
		
	}
	
	public void validateSuccessMessage() {

		reportStep("About to validateSuccessMessage in pending cashout page", "INFO");
		
		String successMessage = "//div[@id='adminMessageSuccess']";
		
		if(isElementLocatedByXpathPresent(successMessage)){
			
			reportStep("Verified the SuccessMessage - i.e Successfully created the Cashout in the admin", "INFO");

		}else {
			reportStep("Not able to verify the SuccessMessage", "FAIL");

		}
		
	}
	

}





