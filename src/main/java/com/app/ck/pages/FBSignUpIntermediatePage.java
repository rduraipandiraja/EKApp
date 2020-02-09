package com.app.ck.pages;


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

public class FBSignUpIntermediatePage extends WrapperMethods {
	
	//Constructor call to initialize the driver object
	public FBSignUpIntermediatePage(AppiumDriver<MobileElement> driver, ExtentTest testInfo) {

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

		reportStep("Waiting for the Facebook signUp page ", "INFO");
		try {
			wait.until(ExpectedConditions.visibilityOf(enterMobileNumber));
			reportStep("Successfully landed on the FB SignUp intermdiate page", "PASS");

		}catch(Exception e) {

			reportStep(e.getMessage(), "INFO");
			reportStep("Not able to land on the FB SignUp intermdiate  page", "FAIL");
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
		
		
		
		
		public String enterMobileNumberAndGetTheOTP(){
			
			String mobileNumber = Utilities.generateRandomNumber(10); //It randomly generates the mobile number starts with the 6,7,8 or 9

			reportStep("About to enter the Mobile to the FBSignUP ", "INFO");
	
			if(enterText(enterMobileNumber, mobileNumber)) {
				
				reportStep("Successfully entered the MobileNumber in to the Facebook signUp page ", "PASS");
				
			}else {
				
				reportStep("Failed to enter the MobileNumber in to the Facebook signUp page ", "FAIL");
				
			}
			
			return	this.clickOnGetOTPButton(mobileNumber);
			
		}

		public void clickOnGetOTPButtonForFailure() {

			reportStep("About to click on the GetOTP button at the Facebbook SignUp page ", "INFO");

			if(click(getOTPEnabled)) {

				reportStep("Successfully clicked on the Get OTP button at the Facebook signUP page ", "PASS");
			}else {

				reportStep("Failed to click on the getcode button at the signUp OTP button ", "FAIL");

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
		

		public OTPPage clickOnGetOTPBtn(String mobileNumber) {

			reportStep("About to click on the GETOTP  button ","INFO");

			if(click(getOTPEnabled)) {

				reportStep("Successfully clicked on the GETOTP button", "PASS");
			}else {

				reportStep("Failed to click on the GETOTP button", "FAIL");
			}
			
			return new OTPPage(driver, testInfo);

		}
		
		public FBSignUpIntermediatePage clickOnGetOTPButtonForFailure(String mobileNumber) {

			reportStep("About to click on the GETOTP  button ","INFO");

			if(click(getOTPEnabled)) {

				reportStep("Successfully clicked on the GETOTP button", "PASS");
			}else {

				reportStep("Failed to click on the GETOTP button", "FAIL");
			}

			return this;

		}

		public FBSignUpIntermediatePage enterMobileNumberAndClickOnGetOTPButton(String mobileNumber){


			reportStep("About to enter the Mobile to the FBSignUP ", "INFO");

			if(enterText(enterMobileNumber, mobileNumber)) {

				reportStep("Successfully entered the MobileNumber in to the Facebook signUp page ", "PASS");

			}else {

				reportStep("Failed to enter the MobileNumber in to the Facebook signUp page ", "FAIL");

			}
			
			this.clickOnGetOTPButtonForFailure();

			return	this;

		}

		public FBSignUpIntermediatePage validateExistingMobileNumErrorAtFBSignUpIntermediatePage() {
			
			reportStep("Once after entering the existing mobile number in to the FB signUP page , Validating the error message", "INFO");
			
			validateTheElementPresence(existingMobileNumErrorValidation);
			
			return this;
			
			
		}

		public FBSignUpIntermediatePage inValidMobileNumberErrorAndMobilenumberIntermediatePageTextValidataion() {

			reportStep("Once after entering the invalid mobile number at the FB signUp intermediate page ", "INFO");

			validateTheElementPresence(pleaseEnterValidMobileNumberError);
			
			validateTheElementPresence(pleaseEnterYourNewMobileNumber);
			
			validateTheElementPresence(yourNumIsSafe_Text);
			
			validateTheElementPresence(getOTPText);

			return this;


		}

		public JoinFreePage clickOnBackButton() {
			
			reportStep("About to click on the back button at the Facebook SignUp mobile number entering intermediate page", "INFO");
			
//			if(click(backButton)) {
//				
//				reportStep("Successfully clicked on the back button the ", "PASS");
//				
//			}else {
//				
//				reportStep("Failed to  click on the back button the ", "FAIL");
//			}
			
			driver.navigate().back();
			
			return new JoinFreePage(driver, testInfo);
		}

}
