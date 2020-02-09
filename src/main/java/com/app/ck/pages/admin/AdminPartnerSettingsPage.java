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
import com.app.ck.base.GetCodeSetUP;
import com.app.ck.base.PropertyFile;
import com.app.ck.base.WrapperMethods;
import com.app.ck.utilities.Utilities;
import com.aventstack.extentreports.ExtentTest;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class AdminPartnerSettingsPage extends WrapperMethods {
	
	public AdminPartnerSettingsPage(AppiumDriver<MobileElement> driver, ExtentTest testInfo) {
		
		this.driver = driver;
		this.testInfo = testInfo;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@FindBy(how = How.XPATH, using = "//td[contains(.,'earnkaro.iamsavings.co.uk')]/parent::tr/td/a[1]") 
	MobileElement editPartnerDetails_Beta;

	@FindBy(how = How.XPATH, using = "//td[contains(.,'trackingstaging.earnkaro.com')]/parent::tr/td/a[1]") 
	MobileElement editPartnerDetails_Staging;

	@FindBy(how = How.ID, using = "sign_up_bonus") 
	MobileElement signUpBonus;

	@FindBy(how = How.ID, using = "referral_signup_bonus") 
	MobileElement referralSignUpBonus;

	@FindBy(how = How.ID, using = "btn_Submit") 
	MobileElement submitButton;

	public void clickEditPartnerDetails(){

		reportStep("About to click edit partner details in partner settings page", "INFO");

		if(PropertyFile.environment().equalsIgnoreCase("Beta")) {
			
			if(clickAfterWait(editPartnerDetails_Beta)) {

				reportStep("Successfully clicked edit partner details in partner settings page", "PASS");
			}else {

				reportStep("Not able to click edit partner details in partner settings page", "FAIL");
			}
			
		}else {
			
			if(clickAfterWait(editPartnerDetails_Staging)) {

				reportStep("Successfully clicked edit partner details in partner settings page", "PASS");
			}else {

				reportStep("Not able to click edit partner details in partner settings page", "FAIL");
			}
			
		}
	}

	public void enterSignUpBonus(String bonus) {

		reportStep("About to enter signup bonus in partner settings page", "INFO");


		if(enterTextInChrome(signUpBonus, bonus)) {

			reportStep("Successfully entered signup bonus "+bonus+" in partner settings page", "PASS");

		}else {

			reportStep("Not able to enter signup bonus "+bonus+" in partner settings page", "FAIL");
		}
	}

	public void enterReferralSignUpBonus(String bonus) {

		reportStep("About to enter referral signup bonus in partner settings page", "INFO");


		if(enterTextInChrome(referralSignUpBonus, bonus)) {

			reportStep("Successfully entered referral signup bonus "+bonus+" in partner settings page", "PASS");

		}else {

			reportStep("Not able to enter referral signup bonus "+bonus+" in partner settings page", "FAIL");
		}
	}

	public void clickSubmit(){

		reportStep("About to click submit button after filling the form in admin page", "INFO");

		//Click submit
		if(clickAfterWait(submitButton)) {

			reportStep("Successfully clicked on the submit button", "PASS");
		}else {

			reportStep("Not able to click the submit button", "FAIL");
		}
	}

	
}





