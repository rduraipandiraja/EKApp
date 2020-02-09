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

public class AdminCashbackPage extends WrapperMethods {
	
	
	public AdminCashbackPage(AppiumDriver<MobileElement> driver, ExtentTest testInfo) {
		
		this.driver = driver;
		this.testInfo = testInfo;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@FindBy(how = How.ID, using = "SubMenu_Cashbacks") 
	MobileElement subMenuCashback;

	@FindBy(how = How.ID, using = "sby") 
	MobileElement searchByDropDown;

	@FindBy(how = How.ID, using = "status") 
	MobileElement cashbackDropDown;

	@FindBy(how = How.ID, using = "key") MobileElement keywordTextbox;

	@FindBy(how = How.ID, using = "exit_id") 
	MobileElement exitIDTextbox;

	@FindBy(how = How.ID, using = "tranactionid") 
	MobileElement transactionIdTextbox;

	@FindBy(how = How.ID, using = "orderid") 
	MobileElement orderIdTextbox;

	@FindBy(how = How.XPATH, using = "//input[@id='ordervalue']") 
	MobileElement orderValueTextbox;

	@FindBy(how = How.ID, using = "cmsn_frm_network") 
	MobileElement confirmCommisionNetworkTextbox;

	@FindBy(how = How.ID, using = "cashbackvalue") 
	MobileElement cashbackTextbox;

	@FindBy(how = How.ID, using = "details") 
	MobileElement detailsTextbox;

	@FindBy(how = How.ID, using = "cashback_type") 
	MobileElement cashbackTypeDropDown;

	@FindBy(how = How.ID, using = "status") 
	MobileElement statusDropDown;
	
	@FindBy(how = How.ID, using = "btn_Submit") 
	MobileElement submitButton;

	@FindBy(how = How.ID, using = "btn_Clear") 
	MobileElement clearButton;

	@FindBy(how = How.XPATH, using = "(//a[@class='fa fa-edit'])[1]") 
	MobileElement editButton;

	@FindBy(how = How.ID, using = "adminMessageSuccess") 
	MobileElement successMessage;
	
	@FindBy(how = How.XPATH, using = "//*[@text='Save Cashback']|//*[text()='Save Cashback']") 
	MobileElement saveCashback;


	
	public void clickCashbackSubMenu() {

		reportStep("About to click submenu cashback in admin homepage", "INFO");
		
		//Click cashback submenu button
		if(jsClickUsingID("SubMenu_Cashbacks")) {

			reportStep("Successfully clicked on the submenu cashback", "PASS");
		}else {

			reportStep("Not able to click the submenu cashback", "FAIL");
		}
		
	}
	
	public void selectSearchByStatusFromDropDown(String dropDownValue) {

		reportStep("About to select value "+dropDownValue+" from drop down", "INFO");

		if(selectValuesFromDropdown(searchByDropDown, dropDownValue)) {
		
		reportStep("Successfully selected value "+dropDownValue+" from the dropdown", "PASS");
		}else {
		
		reportStep("Not able to select value "+dropDownValue+" from dropdown", "FAIL");
		}
	}

	public void enterKeyword(String keyword) {

		reportStep("About to enter keyword in keyword text box", "INFO");

		if(enterTextInChrome(keywordTextbox, keyword)) {

			reportStep("Successfully entered keyword "+keyword+" in keyword text box", "PASS");
		}else {

			reportStep("Not able to enter keyword "+keyword+" in keyword text box", "FAIL");
		}
		
	}
	
	public void selectCashbackStatusFromDropDown(String dropDownValue) {

		reportStep("About to select value "+dropDownValue+" from drop down", "INFO");

		if(selectValuesFromDropdown(cashbackDropDown, dropDownValue)) {
		
		reportStep("Successfully selected value "+dropDownValue+" from the dropdown", "PASS");
		}else {
		
		reportStep("Not able to select value "+dropDownValue+" from dropdown", "FAIL");
		}
	}

	public void clickSubmit() {

		reportStep("About to click submit in all cashback page", "INFO");
		
		if(jsClickUsingID("btn_Submit")) {

			reportStep("Successfully clicked on the submit in all cashback page", "PASS");
		}else {

			reportStep("Not able to click on the submit in all cashback page", "FAIL");
		}
		
	}
	
	public void clickSaveCashbackUsingAppium() {

		reportStep("About to click on Save Cashback Using Appium ", "INFO");
		
		if(clickAfterWait(saveCashback)) {

			reportStep("Successfully clicked on the  Save Cashback ", "PASS");
		}else {

			reportStep("Not able to click on the Save Cashback at cashback page", "FAIL");
		}
		
	}

	public void clickClear() {

		reportStep("About to click clear in all cashback page", "INFO");
		
		if(jsClickUsingID("btn_Clear")) {

			reportStep("Successfully clicked on the clear in all cashback page", "PASS");
		}else {

			reportStep("Not able to click on the clear in all cashback page", "FAIL");
		}
		
	}

	public void clickAddNewButton() {
		
	reportStep("About to click add new button", "INFO");
	
	if(jsClickUsingID("anc_AddNew")) {

		reportStep("Successfully clicked add new button in AllCashbackPage", "PASS");
	}else {

		reportStep("Not able to click add new button in AllCashbackPage", "FAIL");
	}

	
	}

	public void enterExitId(String exitId) {

		reportStep("About to enter exitId in keyword text box", "INFO");

		if(enterTextInChrome(exitIDTextbox, exitId)) {

			reportStep("Successfully entered exitId "+exitId+" in exitId text box", "PASS");
		}else {

			reportStep("Not able to enter exitId "+exitId+" in exitId text box", "FAIL");
		}
		
	}
	
	public void entertransactionId(String transactionId) {

		reportStep("About to enter transactionId in transactionId text box", "INFO");

		if(enterTextInChrome(transactionIdTextbox, transactionId)) {

			reportStep("Successfully entered transactionId "+transactionId+" in transactionId text box", "PASS");
		}else {

			reportStep("Not able to enter transactionId "+transactionId+" in transactionId text box", "FAIL");
		}
		
	}
	
	public void enterOrderId(String orderId) {

		reportStep("About to enter orderId in orderId text box", "INFO");

		if(enterTextInChrome(orderIdTextbox, orderId)) {

			reportStep("Successfully entered orderId "+orderId+" in orderId text box", "PASS");
		}else {

			reportStep("Not able to enter orderId "+orderId+" in orderId text box", "FAIL");
		}
		
	}

	public void enterOrderValue(String orderValue) {

		reportStep("About to enter orderValue in orderValue text box", "INFO");

		if(enterTextAfterClick(orderValueTextbox, orderValue)) {


			reportStep("Successfully entered orderValue "+orderValue+" in orderValue text box", "PASS");
		}else {

			reportStep("Not able to enter orderValue "+orderValue+" in orderValue text box", "FAIL");
		}
		
	}

	public void enterconfirmCommisionNetwork(String confirmCommisionNetwork) {

		reportStep("About to enter confirmCommisionNetwork in confirmCommisionNetwork text box", "INFO");

		if(enterTextInChrome(confirmCommisionNetworkTextbox, confirmCommisionNetwork)) {

			reportStep("Successfully entered confirmCommisionNetwork "+confirmCommisionNetwork+" in confirmCommisionNetwork text box", "PASS");
		}else {

			reportStep("Not able to enter confirmCommisionNetwork "+confirmCommisionNetwork+" in confirmCommisionNetwork text box", "FAIL");
		}
		
	}

	public void entercashback(String cashback) {

		reportStep("About to enter cashback in cashback text box", "INFO");

		if(enterTextInChrome(cashbackTextbox, cashback)) {

			reportStep("Successfully entered orderValue "+cashback+" in cashback text box", "PASS");
		}else {

			reportStep("Not able to enter orderValue "+cashback+" in cashback text box", "FAIL");
		}
		
	}

	public void selectCashbackTypeFromDropDown(String dropDownValue) {

		reportStep("About to select value "+dropDownValue+" from drop down", "INFO");

		if(selectValuesFromDropdown(cashbackTypeDropDown, dropDownValue)) {
		
		reportStep("Successfully selected value "+dropDownValue+" from the dropdown", "PASS");
		}else {
		
		reportStep("Not able to select value "+dropDownValue+" from dropdown", "FAIL");
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

	public void enterDetails(String details) {

		reportStep("About to enter details in details text box", "INFO");

		if(enterTextInChrome(detailsTextbox, details)) {

			reportStep("Successfully entered details "+details+" in details text box", "PASS");
		}else {

			reportStep("Not able to enter details "+details+" in details text box", "FAIL");
		}
		
	}

	public void setOrderDate() {
		
	reportStep("About to set OrderDate", "INFO");


	try {

		System.out.println("In try block about to set OrderDate");

		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("order_date")));
			
	} catch (Exception e) {

		System.out.println("In catch block not able to set OrderDate");
	}
	
	String date = Utilities.setCurrentDate();
	
	((JavascriptExecutor)driver).executeScript("document.getElementById('order_date').setAttribute('value', '"+date+"')");
	
	reportStep("Successfully set OrderDate", "INFO");
	
	}
	
	public void setPendingDate() {
		
	reportStep("About to set PendingDate", "INFO");


	try {

		System.out.println("In try block about to set PendingDate");

		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("pending_date")));
			
	} catch (Exception e) {

		System.out.println("In catch block not able to set PendingDate");
	}
	
	String date = Utilities.setCurrentDate();
	
	((JavascriptExecutor)driver).executeScript("document.getElementById('pending_date').setAttribute('value', '"+date+"')");
	
	reportStep("Successfully set PendingDate", "INFO");
	
	}
	
	public void setConfirmDate() {
		
	reportStep("About to set ConfirmDate", "INFO");


	try {

		System.out.println("In try block about to set ConfirmDate");

		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("confirm_date")));
			
	} catch (Exception e) {

		System.out.println("In catch block not able to set ConfirmDate");
	}
	
	String date = Utilities.setCurrentDate();
	
	((JavascriptExecutor)driver).executeScript("document.getElementById('confirm_date').setAttribute('value', '"+date+"')");
	
	reportStep("Successfully set ConfirmDate", "INFO");
	
	}
	
	public void setFailDate() {
		
	reportStep("About to set FailDate", "INFO");


	try {

		System.out.println("In try block about to set FailDate");

		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("fail_date")));
			
	} catch (Exception e) {

		System.out.println("In catch block not able to set FailDate");
	}
	
	String date = Utilities.setCurrentDate();
	
	((JavascriptExecutor)driver).executeScript("document.getElementById('fail_date').setAttribute('value', '"+date+"')");
	
	reportStep("Successfully set FailDate", "INFO");
	
	}

	public void clickEdit() {

		reportStep("About to click edit in all cashback page", "INFO");
		
		if(clickAfterWait(editButton)) {

			reportStep("Successfully clicked on the edit in all cashback page", "PASS");
		}else {

			reportStep("Not able to click on the edit in all cashback page", "FAIL");
		}
		
	}

}





