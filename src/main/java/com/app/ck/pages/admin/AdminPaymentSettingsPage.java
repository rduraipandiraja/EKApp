package com.app.ck.pages.admin;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.app.ck.base.WrapperMethods;
import com.aventstack.extentreports.ExtentTest;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class AdminPaymentSettingsPage extends WrapperMethods {


	public AdminPaymentSettingsPage(AppiumDriver<MobileElement> driver, ExtentTest testInfo) {

		this.driver = driver;
		this.testInfo = testInfo;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);


	}


	@FindBy(how = How.XPATH, using = "//a[@data-original-title='Edit Payment Methods']") 
	MobileElement paymentMethodEditButton;
	
	@FindBy(how = How.ID, using = "btn_Submit") 
	MobileElement updatePaymentSettings;


	public void clickOnPartnerPaymentEditButton() {

		reportStep("About to click on the Partner Payment edit button ", "INFO");

		if(clickAfterWait(paymentMethodEditButton)) {

			reportStep("successfully clicked on Partner Payment edit button in Admin ", "PASS");

		}else {

			reportStep("Not able to clicked on Partner Payment edit button in Admin ", "FAIL");
		}

	}

	/* editPaymentMethod_Select_Status in Partner_Settings Menu */
	public void editPaymentMethod_Select_Status(int paymentID, String status) {

		reportStep("About to select the Payment method number "+ paymentID +  " Status as: "+ status , "INFO");
		
		if(select_Dropdown(driver, "//select[@id='status"+paymentID+"']", status)) {
			
			reportStep("Successfully selected payment method ID "+ paymentID + " 's status as :"+ status , "PASS");
			
		}else {
			
			reportStep("Failed to select the payment method ID  "+ paymentID + " 's  status as :"+ status , "FAIL");
		}
		
	}
	
	
	/* editPaymentMethod_Select_CashbackType in Partner_Settings Menu */
	public void dropDown_Select_CashbackType(int paymentID, String cashbackType) {
		
		reportStep("About to select cashback type - editPaymentMethod in Partner_Settings", "INFO");
		
		if(select_Dropdown(driver, "//select[@id='cashback_type_"+paymentID+"']", cashbackType)) {
			
			reportStep("Successfully selected payment method ID "+ paymentID + " 's Cashback type  as :"+ cashbackType , "PASS");
		}else {
			
			reportStep("Failed to  select  payment method ID "+ paymentID + " 's Cashback type  as :"+ cashbackType , "FAIL");
		}
		


	}


	public void clickOnUpdatePaymentSettingsButton() {
		
		reportStep("About to click on the Update Payment  Setting  button ", "INFO");
		
		if(clickAfterWait(updatePaymentSettings)) {
			
			reportStep("Successfully clicked on the Update Payment settigs button ", "PASS");
			
		}else {
			
			reportStep("Failed to  click on the Update Payment settigs button ", "FAIL");
		}
		
	}


}
