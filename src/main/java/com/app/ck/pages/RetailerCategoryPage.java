package com.app.ck.pages;

import java.util.List;

import org.openqa.selenium.By;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.app.ck.base.WrapperMethods;
import com.app.ck.utilities.Utilities;
import com.aventstack.extentreports.ExtentTest;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class RetailerCategoryPage extends WrapperMethods {
	

	//Constructor call to initialize the driver object
	public RetailerCategoryPage(AppiumDriver<MobileElement> driver, ExtentTest testInfo) {

		WebDriverWait wait = new WebDriverWait(driver, 30);

		this.driver = driver;
		this.testInfo = testInfo;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);

		reportStep("Waiting for the RetailerCategory page", "INFO");
		try {
		
			scrollFromDownToUpTillRequiredElementIsVisible(automationSpecificCategoryXpath);
			wait.until(ExpectedConditions.visibilityOf(automationSpecificCategory));
			
			reportStep("Successfully landed on the RetailerCategoryPage ", "PASS");

		}catch(Exception e) {
			
			reportStep(e.getMessage(), "INFO");
			reportStep("Not able to land on the RetailerCategoryPage", "FAIL");
		}

	}

	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='PPS ATM APP SPECIFIC CATEGORY']") 
	MobileElement automationSpecificCategory;
	
	String automationSpecificCategoryXpath = "//android.widget.TextView[@text='PPS ATM APP SPECIFIC CATEGORY']";

	@FindBy(how = How.XPATH, using = "//android.widget.TextView[contains(@text,'PPS ATM APP SPECIFIC SUBCATEGORY ONE')]") 
	MobileElement automationSpecificSubCategoryOne;

	@FindBy(how = How.XPATH, using = "//android.widget.TextView[contains(@text,'PPS ATM APP SPECIFIC SUBCATEGORY TWO')]") 
	MobileElement automationSpecificSubCategoryTwo;

	@FindBy(how = How.XPATH, using = "//android.widget.TextView[contains(@text,'PPS ATM APP SPECIFIC SUBCATEGORY THREE')]") 
	MobileElement automationSpecificSubCategoryThree;

	@FindBy(how = How.XPATH, using = "//android.widget.TextView[contains(@text,'PPS ATM APP SPECIFIC SUBCATEGORY FOUR')]") 
	MobileElement automationSpecificSubCategoryFour;

	@FindBy(how = How.XPATH, using = "//android.widget.TextView[contains(@text,'PPS ATM APP SPECIFIC SUBCATEGORY FIVE')]") 
	MobileElement automationSpecificSubCategoryFive;

	@FindBy(how = How.XPATH, using = "//android.widget.TextView[contains(@text,'PPS ATM APP SPECIFIC SUBCATEGORY SIX')]") 
	MobileElement automationSpecificSubCategorySix;

	@FindBy(how = How.XPATH, using = "//android.view.ViewGroup/android.view.ViewGroup[3]/android.widget.TextView|//android.view.View/android.view.View[3]/android.widget.TextView") 
	MobileElement searchIcon;

	@FindBy(how = How.XPATH, using = "(//android.widget.ImageView)[1]") 
	MobileElement backButtonClick;
	
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[contains(@text,'PPS ATM APP')]") 
	List<MobileElement> automationSpecificCategoryPartialText;
	
	String automationSpecificSubCategorySixXpath = "//android.widget.TextView[contains(@text,'PPS ATM APP SPECIFIC SUBCATEGORY SIX')]";

	public RetailerCategoryPage clickAutomationSpecificCategoryInRetailerCategoryMenuUntilAllSubCategoryVisible() {
		
		reportStep("About to verify the retailer category in slide menu", "INFO");
		
		this.clickAutomationSpecificCategoryInRetailerCategoryMenu();
		
		scrollFromDownToUpTillRequiredElementIsVisible(automationSpecificSubCategorySixXpath);
		
		reportStep("Successfully clicked the retailer category in slide menu", "INFO");
		
		return this;
		
	}	
	
	public RetailerCategoryPage clickAutomationSpecificCategoryInRetailerCategoryMenu() {
		
		reportStep("About to verify the retailer category in slide menu", "INFO");
		
		scrollFromDownToUpTillRequiredElementIsVisible(automationSpecificCategoryXpath);
		
		if(clickAfterWait(automationSpecificCategory)) {

			reportStep("Sucessfully verified that the menu has maximizes and minimized ", "PASS");
		}else {

			reportStep("Failed to verify that the menu has maximizes and minimized ", "FAIL");
		}
		
		return this;
		
	}	
	
	public RetailerCategoryPage verifyRetailerCategoryMenuNavigation() {

		reportStep("About to verify the menu retailer category after clicking retailer category menu in slider menu", "INFO");
		
		validateTheElementPresence(automationSpecificCategory);
		
		return this;
			
	}

	public RetailerCategoryPage verifyRetailerCategoryMenuIsGettingMaximizedAndMinimized(String count) {

		reportStep("About to verify the after clicking automation main menu sub menus are getting displayed in retailer category menu in slider menu", "INFO");
		
		int maximizingValue = getListOfElementsSize(automationSpecificCategoryPartialText);
		
		String maximizeValue = Integer.toString(maximizingValue);
		
		validateTheActualValueWithExpectedValue(maximizeValue, count);
		
		return this;
			
	}
	
	public StoreCategoryPage clickAutomationSpecificSubCategoryOne() {
		
		reportStep("About to click automation specific sub category one in slide menu", "INFO");
		
		if(clickAfterWait(automationSpecificSubCategoryOne)) {

			reportStep("Sucessfully clicked automation specific sub category one", "PASS");
		}else {

			reportStep("Failed to click automation specific sub category one", "FAIL");
		}
		
		return new StoreCategoryPage(driver, testInfo);
		
	}	
	
	public StoreCategoryPage clickAutomationSpecificSubCategoryTwo() {
		
		reportStep("About to click automation specific sub category two in slide menu", "INFO");
		
		if(clickAfterWait(automationSpecificSubCategoryTwo)) {

			reportStep("Sucessfully clicked automation specific sub category two", "PASS");
		}else {

			reportStep("Failed to click automation specific sub category two", "FAIL");
		}
		
		return new StoreCategoryPage(driver, testInfo);
		
	}	
	
	public StoreCategoryPage clickAutomationSpecificSubCategoryThree() {
		
		reportStep("About to click automation specific sub category three in slide menu", "INFO");
		
		if(click(automationSpecificSubCategoryThree)) {

			reportStep("Sucessfully clicked automation specific sub category three", "PASS");
		}else {

			reportStep("Failed to click automation specific sub category three", "FAIL");
		}
		
		return new StoreCategoryPage(driver, testInfo);
		
	}	
	
	public StoreCategoryPage clickAutomationSpecificSubCategoryFour() {
		
		reportStep("About to click automation specific sub category Four in slide menu", "INFO");
		
		if(click(automationSpecificSubCategoryFour)) {

			reportStep("Sucessfully clicked automation specific sub category Four", "PASS");
		}else {

			reportStep("Failed to click automation specific sub category Four", "FAIL");
		}
		
		return new StoreCategoryPage(driver, testInfo);
		
	}	
	
	public StoreCategoryPage clickAutomationSpecificSubCategoryFive() {
		
		reportStep("About to click automation specific sub category Five in slide menu", "INFO");
		
		if(click(automationSpecificSubCategoryFive)) {

			reportStep("Sucessfully clicked automation specific sub category Five", "PASS");
		}else {

			reportStep("Failed to click automation specific sub category Five", "FAIL");
		}
		
		return new StoreCategoryPage(driver, testInfo);
		
	}	
	
	public StoreCategoryPage clickAutomationSpecificSubCategorySix() {
		
		reportStep("About to click automation specific sub category Six in slide menu", "INFO");
		
		if(click(automationSpecificSubCategorySix)) {

			reportStep("Sucessfully clicked automation specific sub category Six", "PASS");
		}else {

			reportStep("Failed to click automation specific sub category Six", "FAIL");
		}
		
		return new StoreCategoryPage(driver, testInfo);
		
	}	

	public HomePage clickBackButton() {

		reportStep("About to click on back button in store category page", "INFO");

		if(click(backButtonClick)) {

			reportStep("Successfully clicked on the back button in store cat page", "PASS");
		}else {

			reportStep("Failed to  click on the back button in store cat page", "FAIL");
		}

		return new HomePage(driver, testInfo);
		
	}

	public SearchPage clickSearchIcon() {

		reportStep("About to click search icon in retailer page", "INFO");

		if(click(searchIcon)) {

			reportStep("Successfully clicked search icon in retailer page", "PASS");
		}else {

			reportStep("Failed to  click search icon in retailer page", "FAIL");
		}

		return new SearchPage(driver, testInfo);
		
	}

	
}
