package com.app.ck.pages.admin;

import java.util.List;

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

public class AdminUsersPage extends WrapperMethods {
	
	public AdminUsersPage(AppiumDriver<MobileElement> driver, ExtentTest testInfo) {
		
		this.driver = driver;
		this.testInfo = testInfo;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	String userId = "//table[@id='all_list']/tbody/tr[2]/td[1]";
	String userName = "//table[@id='all_list']/tbody/tr[2]/td[3]";

	@FindBy(how = How.ID, using = "sby") 
	MobileElement searchByDropDown;

	@FindBy(how = How.ID, using = "key") 
	MobileElement keywordTextbox;

	@FindBy(how = How.XPATH, using = "//table[@id='all_list']/tbody/tr[2]/td[1]") 
	MobileElement userIdXpath;

	@FindBy(how = How.XPATH, using = "//table[@id='all_list']/tbody/tr[2]/td[3]") 
	MobileElement userNameXpath;

	@FindBy(how = How.XPATH, using = "//a[@class='fa fa-edit']") 
	MobileElement editIConClick;

	@FindBy(how = How.ID, using = "status") 
	MobileElement statusDropDown;

	@FindBy(how = How.ID, using = "referral_bonus") 
	MobileElement referralBonus;

	@FindBy(how = How.ID, using = "email") 
	MobileElement email;


	@FindBy(how = How.XPATH, using = "//div[@id='adminMessageSuccess']") 
	MobileElement successMessage;

	
	public void selectSearchByDropDown(String dropDownValue) {

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

	public void clickSubmitButton() {

		reportStep("About to click submit button", "INFO");

		//Click submit button
		if(jsClickUsingID("users_search_form_submit")) {

			reportStep("Successfully clicked submit button", "PASS");
		}else {

			reportStep("Not able to click submit button", "FAIL");
		}

	}

	public String extractUserIdValueFromResultstableFirstRow(){

		reportStep("About to extract UserId from User page", "INFO");

		validateTheElementPresence(userIdXpath);
		String userIdValue = driver.findElement(By.xpath(userId)).getText();

		reportStep("Successfully retrieved the extract UserId from User page", "INFO");

		return userIdValue;

	}

	public String extractUserNameValueFromResultstableFirstRow(){

		reportStep("About to extract UserName from User page", "INFO");

		validateTheElementPresence(userNameXpath);
		String userNameValue = driver.findElement(By.xpath(userName)).getText();

		reportStep("Successfully retrieved the extract UserName from User page", "INFO");

		return userNameValue;

	}

	public void clickEditButton() {

		reportStep("About to click edit button", "INFO");

		//Click submit button
		if(jsClickUsingXpath(editIConClick)) {

			reportStep("Successfully clicked edit button", "PASS");
		}else {

			reportStep("Not able to click edit button", "FAIL");
		}

		reportStep("Successfully clicked edit button", "PASS");

	}
	
	public void selectStatusFromDropDown(String dropDownValue) {

		reportStep("About to select value "+dropDownValue+" from drop down", "INFO");

		if(selectValuesFromDropdown(statusDropDown, dropDownValue)) {
		
		reportStep("Successfully selected value "+dropDownValue+" from the dropdown", "PASS");
		}else {
		
		reportStep("Not able to select value "+dropDownValue+" from dropdown", "FAIL");
		}
	}

	public void clickSubmitButtonUserForm() {

		reportStep("About to click submit button after doing changes in users form", "INFO");

		//Click submit button
		if(jsClickUsingID("btn_Submit")) {

			reportStep("Successfully clicked submit button", "PASS");
		}else {

			reportStep("Not able to click submit button", "FAIL");
		}
		
		if(validateTheElementPresence(successMessage)) {

			reportStep("Successfully updated user", "PASS");
		}else {

			reportStep("Not able update user", "FAIL");
		}
		
	}

	public void enterReferralBonus(String bonus) {

		reportStep("About to enter referral bonus in users page", "INFO");


		if(enterTextInChrome(referralBonus, bonus)) {

			reportStep("Successfully entered referral bonus "+bonus+" in users page", "PASS");

		}else {

			reportStep("Not able to enter referral bonus "+bonus+" in users page", "FAIL");
		}
	}

	public void enterEmail(String emailAddress) {

		reportStep("About to enter emailAddress in users page", "INFO");


		if(enterTextInChrome(email, emailAddress)) {

			reportStep("Successfully entered email "+emailAddress+" in users page", "PASS");

		}else {

			reportStep("Not able to enter email "+emailAddress+" in users page", "FAIL");
		}
	}

	public void validateUserEditSuccessMessage() {
		
		reportStep("Once after the User is been de activated - Verifying the Success message in the admin", "INFO");
		validateTheElementPresence(successMessage);
		
	}
}





