package com.app.ck.pages;

import java.io.IOException;

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

public class PersonalDetailsIntermediatePage extends WrapperMethods {
	
	//Constructor call to initialize the driver object
	public PersonalDetailsIntermediatePage(AppiumDriver<MobileElement> driver, ExtentTest testInfo) {

		WebDriverWait wait = new WebDriverWait(driver, 30);

		this.driver = driver;
		this.testInfo = testInfo;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
		
//		if(clickOnNoneOFTheAbove()) {
//			
//			reportStep("Handled the Mobile number suggestion popup ", "INFO");
//			
//		}else {
//			
//			reportStep("Not able to handle the Mobile number suggestion popup ", "INFO");
//		}

		reportStep("Waiting for the personal Details intermediate page ", "INFO");
		try {
			wait.until(ExpectedConditions.visibilityOf(enterMobileNumber));
			reportStep("Successfully landed on the personal Details intermdiate page", "PASS");

		}catch(Exception e) {

			reportStep(e.getMessage(), "INFO");
			reportStep("Not able to land on the personal Details  page", "FAIL");
		}

	}
	
	//Variable declaration
		@FindBy(how = How.XPATH, using = "//android.widget.EditText[@content-desc='et_new_mobile_number']") MobileElement enterMobileNumber ;
		
		@FindBy(how = How.XPATH, using = "//android.widget.TextView[@content-desc='txt_new_mobile_get_otp_enabled']") MobileElement getOTPEnabled;

		@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='Number already in use. Please enter different number.']") MobileElement existingMobileNumErrorValidation;
		
		@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text ='Please enter a valid Mobile Number']") MobileElement pleaseEnterValidMobileNumberError;
		
		@FindBy(how = How.XPATH, using = "(//android.widget.ImageView)[1]") MobileElement backButton;

		@FindBy(how = How.XPATH, using = "//*[@text='Please enter new mobile number']") MobileElement pleaseEnterYourNewMobileNumber;

		@FindBy(how = How.XPATH, using = "//*[@text='Your number is safe with us. We wont share your details with anyone.']") MobileElement yourNumIsSafe_Text;

		@FindBy(how = How.XPATH, using = "//*[@text='Get OTP']") MobileElement getOTPText;
		
		
	//Method Creation :
		public String enterMobileNumberAndGetTheOTP(){

			String mobileNumber = Utilities.generateRandomNumber(10); //It randomly generates the mobile number starts with the 6,7,8 or 9

			reportStep("About to enter the Mobile at the personal Details ", "INFO");

			if(enterText(enterMobileNumber, mobileNumber)) {

				reportStep("Successfully entered the MobileNumber in to the personal Details Intermediate page", "PASS");

			}else {

				reportStep("Failed to enter the MobileNumber in to the Personal Details page  ", "FAIL");

			}

			return	this.clickOnGetOTPButton(mobileNumber);

		}

		public String enterMobileNumberAndGetTheOTP(String mobileNumber){

			reportStep("About to enter the Mobile at the personal Details ", "INFO");

			if(enterText(enterMobileNumber, mobileNumber)) {

				reportStep("Successfully entered the MobileNumber in to the personal Details Intermediate page", "PASS");

			}else {

				reportStep("Failed to enter the MobileNumber in to the Personal Details page  ", "FAIL");

			}

			return	this.clickOnGetOTPButton(mobileNumber);

		}

		public void clickOnGetOTPButtonForFailure() {

			reportStep("About to click on the GetOTP button at the Personal detail intermediate  page ", "INFO");

			if(click(getOTPEnabled)) {

				reportStep("Successfully clicked on the Get OTP button at the Personal detail intermediate  page ", "PASS");
			}else {

				reportStep("Failed to click on the getcode button at the Personal detail intermediate Page ", "FAIL");

			}


		}

		public String clickOnGetOTPButton(String mobileNumber) {

			reportStep("About to click on the GETOTP  button ","INFO");

			if(click(getOTPEnabled)) {

				reportStep("Successfully clicked on the GETOTP button", "PASS");
			}else {

				reportStep("Failed to click on the GETOTP button", "FAIL");
			}
			
			new OTPPage(driver, testInfo);

			return Utilities.getSignUPOTP(mobileNumber); 

		}
		
		public PersonalDetailsIntermediatePage clickOnGetOTPButtonForFailure(String mobileNumber) {

			reportStep("About to click on the GETOTP  button ","INFO");

			if(click(getOTPEnabled)) {

				reportStep("Successfully clicked on the GETOTP button", "PASS");
			}else {

				reportStep("Failed to click on the GETOTP button", "FAIL");
			}

			return this;

		}

		public PersonalDetailsIntermediatePage enterMobileNumberAndClickOnGetOTPButton(String mobileNumber){


			reportStep("About to enter the Mobile to the Personal detail intermediate page ", "INFO");

			if(enterText(enterMobileNumber, mobileNumber)) {

				reportStep("Successfully entered the MobileNumber in to the Personal detail intermediate page ", "PASS");

			}else {

				reportStep("Failed to enter the MobileNumber in to the Personal detail intermediate page ", "FAIL");

			}
			
			this.clickOnGetOTPButtonForFailure();

			return	this;

		}

		public PersonalDetailsIntermediatePage validateExistingMobileNumErrorAtPersonalDetailsIntermediatePage() {
			
			reportStep("Once after entering the existing mobile number in to the Personal detail intermediate page , Validating the error message", "INFO");
			
			validateTheElementPresence(existingMobileNumErrorValidation);
			
			return this;
			
			
		}

		public PersonalDetailsIntermediatePage inValidMobileNumberErrorAndMobilenumberIntermediatePageTextValidataion() {

			reportStep("Once after entering the invalid mobile number at the Personal detail intermediate page ", "INFO");

			validateTheElementPresence(pleaseEnterValidMobileNumberError);
			
			validateTheElementPresence(pleaseEnterYourNewMobileNumber);
			
			validateTheElementPresence(yourNumIsSafe_Text);
			
			validateTheElementPresence(getOTPText);

			return this;


		}

		public AccountSettingsPage clickOnBackButton() {
			
			reportStep("About to click on the back button at the Personal detail intermediate page mobile number entering intermediate page", "INFO");
			
//			if(click(backButton)) {

//				reportStep("Successfully clicked on the back button the ", "PASS");
//				
//			}else {
//				
//				reportStep("Failed to  click on the back button the ", "FAIL");
//			}
			
			driver.navigate().back();
			
			return new AccountSettingsPage(driver, testInfo);
		}

		public PersonalDetailsIntermediatePage validateEnteredMobileNumberRemainSameWhenbackFromOTPPage(String mobileNumber) {
			
			reportStep("About to validate the mobile number remain same once after clicking back from the OTP page ", "INFO");
			
			String xpath = "//*[@text="+mobileNumber+"]";
			
			if(isElementLocatedByXpathPresent(xpath)) {
				
				reportStep("Successfully valited that once after clicking the back button from the OTP page , The mobile number entered remains unchanged ", "PASS");
			}else {
				
				reportStep("Failed - Once the user clicked back button from the OTP entering page - The mobile number entered is not remained same - Changed ", "FAIL");
			}
			
			return this;
			
		}
		
}
