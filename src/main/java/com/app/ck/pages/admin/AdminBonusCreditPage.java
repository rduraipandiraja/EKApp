package com.app.ck.pages.admin;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
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

public class AdminBonusCreditPage extends WrapperMethods {
	
	
	public AdminBonusCreditPage(AppiumDriver<MobileElement> driver, ExtentTest testInfo) {
		
		this.driver = driver;
		this.testInfo = testInfo;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@FindBy(how = How.ID, using = "SubMenu_AddBonusCredits") 
	MobileElement subMenuBonusCredits;

	@FindBy(how = How.ID, using = "username") 
	MobileElement usernameTextbox;

	@FindBy(how = How.ID, using = "cashback_type") 
	MobileElement cashbackTypeDropDown;

	@FindBy(how = How.ID, using = "cashbackvalue") 
	MobileElement bonusValueTextbox;

	@FindBy(how = How.ID, using = "confirm_date") 
	MobileElement dateRangeTextbox;
	
	public void clickBonusCreditsSubMenu() {

		reportStep("About to click submenu BonusCredits in admin homepage", "INFO");
		
		//Click BonusCredits sub menu button
		if(jsClickUsingID("SubMenu_AddBonusCredits")) {

			reportStep("Successfully clicked on the submenu BonusCredits", "PASS");
		}else {

			reportStep("Not able to click the submenu BonusCredits", "FAIL");
		}
		
	}

	public void enterUser(String username) {

		reportStep("About to enter username in username text box", "INFO");

		if(enterTextInChrome(usernameTextbox, username)) {

			reportStep("Successfully entered username "+username+" in username text box", "PASS");
		}else {

			reportStep("Not able to enter username "+username+" in username text box", "FAIL");
		}
		
		driver.findElementById("username").sendKeys(Keys.DOWN);
		driver.findElementById("username").sendKeys(Keys.ENTER);
		
	}

	public void enterBonusValue(String BonusValue) {

		reportStep("About to enter BonusValue in BonusValue text box", "INFO");

		if(enterTextInChrome(bonusValueTextbox, BonusValue)) {

			reportStep("Successfully entered BonusValue "+BonusValue+" in BonusValue text box", "PASS");
		}else {

			reportStep("Not able to enter BonusValue "+BonusValue+" in BonusValue text box", "FAIL");
		}
		
	}

	public void setDateRangeDate() {
		
	reportStep("About to set OrderDate", "INFO");


	try {

		System.out.println("In try block about to setDateRange");

		WebDriverWait wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("confirm_date")));
			
	} catch (Exception e) {

		System.out.println("In catch block not able to setDateRange");
	}
	
	String date = Utilities.setCurrentDate();
	
	((JavascriptExecutor)driver).executeScript("document.getElementById('confirm_date').setAttribute('value', '"+date+"')");
	
	reportStep("Successfully setDateRange", "INFO");
	
	}

	public void selectCashbackTypeFromDropDown(String dropDownValue) {

		reportStep("About to select value "+dropDownValue+" from drop down", "INFO");

		if(selectValuesFromDropdown(cashbackTypeDropDown, dropDownValue)) {
		
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

}