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

public class AdminReportsPage extends WrapperMethods {
	
	public AdminReportsPage(AppiumDriver<MobileElement> driver, ExtentTest testInfo) {
		
		this.driver = driver;
		this.testInfo = testInfo;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@FindBy(how = How.ID, using = "SubMenu_ExitClicks") 
	MobileElement subMenuExitClick;

	@FindBy(how = How.ID, using = "sby") 
	MobileElement searchByDropDown;

	@FindBy(how = How.ID, using = "sterm") 
	MobileElement keywordTextbox;
	
	@FindBy(how = How.ID, using = "btn_Submit") 
	MobileElement submitButton;

	@FindBy(how = How.ID, using = "btn_Clear") 
	MobileElement clearButton;
	
	public void clickExitClickSubMenu() {

		reportStep("About to click submenu ExitClick in admin homepage", "INFO");
		
		if(clickAfterWait(subMenuExitClick)) {

			reportStep("Successfully clicked on the submenu ExitClick", "PASS");
		}else {

			reportStep("Not able to click the submenu ExitClick", "FAIL");
		}
		
	}
	
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
	
	public void clickSubmit() {

		reportStep("About to click submit in view exit click page", "INFO");
		
		if(clickAfterWait(submitButton)) {

			reportStep("Successfully clicked on the submit in view exit click page", "PASS");
		}else {

			reportStep("Not able to click on the submit in view exit click page", "FAIL");
		}
		
	}

	public void clickClear() {

		reportStep("About to click clear in view exit click page", "INFO");
		
		if(clickAfterWait(clearButton)) {

			reportStep("Successfully clicked on the clear in view exit click page", "PASS");
		}else {

			reportStep("Not able to click on the clear in view exit click page", "FAIL");
		}
		
	}

	public String extractExitClickValueFromResultstableFirstRow(){

		reportStep("About to extract exit id from reports page", "INFO");
		
		String searchResultsTable= "//table[@id='all_list']/tbody/tr[2]/td[1]";
		
		if(isElementLocatedByXpathPresent(searchResultsTable)) {

			reportStep("Presence exit id from reports page", "INFO");
		}else {

			reportStep("Successfully retrieved the extract exit id from reports page", "INFO");
		}
	
		String reqExitClick = driver.findElement(By.xpath(searchResultsTable)).getText();

		reportStep("Successfully retrieved the extract exit id from reports page", "INFO");
		
		return reqExitClick;

	}
	
	public String extractRequiredExitIdUsingStoreNameExitClickType(String storeName, String exitClickType){

		reportStep("About to extract UserId from view exit click page", "INFO");
	
		String reqExitClick= "//table[@id='all_list']/tbody/tr/td[contains(.,'"+storeName+"')]/parent::tr/td[contains(.,'"+exitClickType+"')]/parent::tr/td[1]";
		
		if(isElementLocatedByXpathPresent(reqExitClick)) {

			reportStep("For the store name: "+storeName+" there is exitclicktype "+exitClickType+"", "INFO");
		}else {

			reportStep("Not able to find the store name: "+storeName+" having exitclicktype "+exitClickType+"", "FAIL");
		}
		
		String exitId = driver.findElement(By.xpath(reqExitClick)).getText();

		reportStep("Successfully retrieved the extract exitId: "+exitId+" from view exit click page", "INFO");

		return exitId;

	}

	public String extractRequiredExitIdUsingStoreNameExitClickType(String storeName, String exitClickType, int index){

		reportStep("About to extract shared exitId from view exit click page", "INFO");
	
		String reqExitClick= "(//table[@id='all_list']/tbody/tr/td[contains(.,'"+storeName+"')]/parent::tr/td[contains(.,'"+exitClickType+"')]/parent::tr/td[1])["+index+"]";
		
		if(isElementLocatedByXpathPresent(reqExitClick)) {

			reportStep("For the store name: "+storeName+" there is exitclicktype "+exitClickType+"", "INFO");
		}else {

			reportStep("Not able to find the store name: "+storeName+" having exitclicktype "+exitClickType+"", "FAIL");
		}
		
		String exitId = driver.findElement(By.xpath(reqExitClick)).getText();

		reportStep("Successfully retrieved the extract shared exitId: "+exitId+" from view exit click page", "INFO");

		return exitId;

	}

	public String extractRequiredExitIdUsingStoreNameExitClickTypeSharedId(String storeName, String sharedID){

		reportStep("About to extract normal exitId from view exit click page", "INFO");
	
		String reqExitClick= "//table[@id='all_list']/tbody/tr/td[contains(.,'"+storeName+"')]/parent::tr/td[contains(.,'"+sharedID+"')]/parent::tr/td[1]/parent::tr/td[contains(.,'Normal')]/parent::tr/td[1]";
		
		if(isElementLocatedByXpathPresent(reqExitClick)) {

			reportStep("For the store name: "+storeName+" there is exitclicktype "+sharedID+"", "INFO");
		}else {

			reportStep("Not able to find the store name: "+storeName+" having exitclicktype "+sharedID+"", "FAIL");
		}
		
		String exitId = driver.findElement(By.xpath(reqExitClick)).getText();

		reportStep("Successfully retrieved the extract normal exitId: "+exitId+" from view exit click page", "INFO");

		return exitId;

	}

}