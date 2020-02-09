package com.app.ck.pages.admin;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.app.ck.base.Base;
import com.app.ck.base.WrapperMethods;
import com.app.ck.utilities.CashKaroUtility;
import com.app.ck.utilities.Utilities;
import com.aventstack.extentreports.ExtentTest;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class AdminVoucherPage extends WrapperMethods {
	
	public AdminVoucherPage(AppiumDriver<MobileElement> driver, ExtentTest testInfo) {
		
		this.driver = driver;
		this.testInfo = testInfo;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@FindBy(how = How.ID, using = "sstring") 
	MobileElement storeNameTextBox;

	@FindBy(how = How.ID, using = "title") 
	MobileElement voucherNameTextBox;

	@FindBy(how = How.ID, using = "device_type_desktop") 
	MobileElement desktopDeviceType;

	@FindBy(how = How.ID, using = "device_type_mobile") 
	MobileElement mobileDeviceType;

	@FindBy(how = How.ID, using = "device_type_tablet") 
	MobileElement tabletDeviceType;

	@FindBy(how = How.ID, using = "device_type_app") 
	MobileElement appDeviceType;

	@FindBy(how = How.ID, using = "app_title") 
	MobileElement appVoucherNameTextBox;

	@FindBy(how = How.ID, using = "btn_Submit") 
	MobileElement submitButton;

	@FindBy(how = How.ID, using = "status") 
	MobileElement statusDropDown;

	@FindBy(how = How.XPATH, using = "//select[@id='storeid']") 
	MobileElement storeNameDropDown;

	@FindBy(how = How.ID, using = "anc_AddNew") 
	MobileElement addNewVoucher;

	@FindBy(how = How.ID, using = "vtype") 
	MobileElement voucherTypeDropDown;

	@FindBy(how = How.ID, using = "code") 
	MobileElement codeTextBox;

	@FindBy(how = How.ID, using = "sby") 
	MobileElement dropDownSearchBy;

	@FindBy(how = How.ID, using = "sstring") 
	MobileElement keywordTextBox;

	@FindBy(how = How.ID, using = "issuedate") 
	MobileElement issueDate;

	@FindBy(how = How.ID, using = "expirydate") 
	MobileElement expiryDate;

	@FindBy(how = How.ID, using = "expirytime") 
	MobileElement expiryTime;

	@FindBy(how = How.ID, using = "btn_Clear") 
	MobileElement clearButton;
	
	String clearButtonXpath = "btn_Clear";

	@FindBy(how = How.XPATH, using = "//a[@class='fa fa-edit']") 
	List <MobileElement> editIConClick;

	public void enterStoreName(String storeName) {

		reportStep("About to enter store name in storename text box", "INFO");

		//Enter store name in storename textbox
		if(enterTextInChrome(storeNameTextBox, storeName)) {

			reportStep("Successfully entered store name "+storeName+" in storename text box", "PASS");
		}else {

			reportStep("Not able to enter store name "+storeName+" in storename text box", "FAIL");
		}
		
	}
	
	public void clickSubmitButton() {
	
	reportStep("About to click submit button", "INFO");
	
	//Click submit button
	if(jsClickUsingID("btn_Submit")) {
	
	reportStep("Successfully clicked submit button", "PASS");
	}else {
	
	reportStep("Not able to click submit button", "FAIL");
	}
	
	}
	
	public void clickEditButton(String storeName, int index) {
	
	reportStep("About to click edit button", "INFO");
	
	//Click submit button
	if(clickAfterWait(editIConClick.get(index-1))) {
	
	reportStep("Successfully clicked edit button", "PASS");
	}else {
	
	reportStep("Not able to click edit button", "FAIL");
	}
	
	}

	public void selectStatusFromDropDown(String dropDownValue) {

		reportStep("About to select value "+dropDownValue+" from drop down", "INFO");

		if(selectValuesFromDropdown(statusDropDown, dropDownValue)) {
		
		reportStep("Successfully selected value "+dropDownValue+" from the dropdown", "PASS");
		}else {
		
		reportStep("Not able to select value "+dropDownValue+" from dropdown", "FAIL");
		}
	}

	public void selectVoucherTypeFromDropDown(String dropDownValue) {

		reportStep("About to select value "+dropDownValue+" from drop down", "INFO");

		if(selectValuesFromDropdown(voucherTypeDropDown, dropDownValue)) {
		
		reportStep("Successfully selected value "+dropDownValue+" from the dropdown", "PASS");
		}else {
		
		reportStep("Not able to select value "+dropDownValue+" from dropdown", "FAIL");
		}
	}

	public void selectStoreNameFromDropDown(String dropDownValue) {

		reportStep("About to select value "+dropDownValue+" from drop down", "INFO");

		if(selectValuesFromDropdown(storeNameDropDown, dropDownValue)) {
		
		reportStep("Successfully selected value "+dropDownValue+" from the dropdown", "PASS");
		}else {
		
		reportStep("Not able to select value "+dropDownValue+" from dropdown", "FAIL");
		
		}
		
		
	}
	
	public void clickAddNewButton() {
		
	reportStep("About to click add new button", "INFO");
	
	jsClickUsingID("anc_AddNew");

	reportStep("Successfully clicked add new button", "PASS");
	
	}

	public void enterVoucherName(String voucherName) {

		reportStep("About to enter voucher name in storename text box", "INFO");

		//Enter store name in voucherName textbox
		if(enterTextInChrome(voucherNameTextBox, voucherName)) {

			reportStep("Successfully entered voucher name "+voucherName+" in vouchername text box", "PASS");
		}else {

			reportStep("Not able to enter voucher name "+voucherName+" in vouchername text box", "FAIL");
		}
		
	}

	public void enterAppVoucherName(String appVoucherName) {

		reportStep("About to enter voucher name in storename text box", "INFO");

		//Enter store name in appVoucherName textbox
		if(enterTextInChrome(appVoucherNameTextBox, appVoucherName)) {

			reportStep("Successfully entered appvoucher name "+appVoucherName+" in appvouchername text box", "PASS");
		}else {

			reportStep("Not able to enter appvoucher name "+appVoucherName+" in appvouchername text box", "FAIL");
		}
		
	}

	public void clickCheckBoxDesktop() {
		
	reportStep("About to click checkbox desktop", "INFO");
	
	//Click submit button
	if(jsClickUsingID("device_type_desktop")) {
	
	reportStep("Successfully clicked checkbox desktop", "PASS");
	}else {
	
	reportStep("Not able to click checkbox desktop", "FAIL");
	}
	
	}

	public void clickCheckBoxMobile() {
		
	reportStep("About to click checkbox mobile", "INFO");
	
	//Click submit button
	if(jsClickUsingID("device_type_mobile")) {
	
	reportStep("Successfully clicked checkbox mobile", "PASS");
	}else {
	
	reportStep("Not able to click checkbox mobile", "FAIL");
	}
	
	}

	public void clickCheckBoxTablet() {
		
	reportStep("About to click checkbox tablet", "INFO");
	
	//Click submit button
	if(jsClickUsingID("device_type_tablet")) {
	
	reportStep("Successfully clicked checkbox tablet", "PASS");
	}else {
	
	reportStep("Not able to click checkbox tablet", "FAIL");
	}
	
	}

	public void clickCheckBoxApp() {
		
	reportStep("About to click checkbox app", "INFO");
	
	//Click submit button
	if(jsClickUsingID("device_type_app")) {
	
	reportStep("Successfully clicked checkbox app", "PASS");
	}else {
	
	reportStep("Not able to click checkbox app", "FAIL");
	}
	
	}

	public void setIssueDate() {
		
	reportStep("About set issue date", "INFO");


	try {

		System.out.println("In try block about to set issue date");

		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='issuedate']")));
			
	} catch (Exception e) {

		System.out.println("In catch block not able to set issue date");
		
		reportStep("Failed - Not able to set issue date", "FAIL");
	}
	
	String date = Utilities.setOneDayBeforeCurrentDate();
	
	((JavascriptExecutor)driver).executeScript("document.getElementById('issuedate').setAttribute('value', '"+date+"')");
	
	reportStep("Successfully set issue date", "INFO");
	
	}
	
	public void setExpiryDate() {
		
	reportStep("About set expiry date", "INFO");


	try {

		System.out.println("In try block about to set expiry date");
		
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='expirydate']")));
			
	} catch (Exception e) {

		System.out.println("In catch block not able to set expiry date");
	}
	
	String date = Utilities.setFourDaysAfterCurrentDate();
	
	((JavascriptExecutor)driver).executeScript("document.getElementById('expirydate').setAttribute('value', '"+date+"')");
	
	reportStep("Successfully set expiry date", "INFO");
	
	}

	public void setCurrentDateAsExpiryDate() {
		
	reportStep("About set expiry date", "INFO");

	try {

		System.out.println("In try block about to set expiry date");
		
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("expiryDate")));
			
	} catch (Exception e) {

		System.out.println("In catch block not able to set expiry date");
	}
	
	String date = Utilities.setCurrentDate();
	
	((JavascriptExecutor)driver).executeScript("document.getElementById('expirydate').setAttribute('value', '"+date+"')");
	
	reportStep("Successfully set expiry date", "INFO");
	
	}
	
	public void enterCode(String code) {

		reportStep("About to enter code "+code+" from drop down", "INFO");

		if(enterTextInChrome(codeTextBox, code)) {
		
		reportStep("Successfully entered code "+code+"", "PASS");
		}else {
		
		reportStep("Not able to enter code "+code+"", "FAIL");
		}
	}

	public void dropdownSearchBy(String dropDownValue) {

		reportStep("About to select value from search by dropdown in vouchers page", "INFO");

		//select value from search by dropdown
		if(selectValuesFromDropdown(dropDownSearchBy, dropDownValue)) {

			reportStep("Successfully selected value "+dropDownValue+" from dropdown", "PASS");
		}else {

			reportStep("Not able to select value "+dropDownValue+" from dropdown", "FAIL");
		}
		
	}

	public void enterKeyword(String storeName) {

		reportStep("About to enter keyword text box", "INFO");

		//Enter store name in storename textbox
		if(enterTextInChrome(keywordTextBox, storeName)) {

			reportStep("Successfully entered keyword "+storeName+" text box", "PASS");
		}else {

			reportStep("Not able to enter keyword "+storeName+" text box", "FAIL");
		}
		
	}
	
	public void clickEditButton(int index) {
		
	reportStep("About to click edit button", "INFO");
	
	String xpath = "(//a[@class='fa fa-edit'])["+index+"]" ;
	
	isElementLocatedByXpathPresent(xpath);
	
	MobileElement element = driver.findElementByXPath(xpath);
	//Click submit button
	if(clickAfterWait(element)) {
	
	reportStep("Successfully clicked edit button", "PASS");
	
	}else {
	
	reportStep("Not able to click edit button", "FAIL");
	}
	
	
	}

	public void clickVoucherExpiryTime(){

		reportStep("About to click voucher expiry time", "INFO");

		if(clickAfterWait(expiryTime)) {

			reportStep("Successfully clicked voucher expiry time", "PASS");
		}else {

			reportStep("Not able to click voucher expiry time", "FAIL");
		}

	}

	public void clickClearButton() {
		
	reportStep("About to click clear button", "INFO");
	
	//Click submit button
	if(jsClickUsingID(clearButtonXpath)) {
	
	reportStep("Successfully clicked clear button", "PASS");
	}else {
	
	reportStep("Not able to click clear button", "FAIL");
	
	}
	
	}

}





