package com.app.ck.pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.app.ck.base.WrapperMethods;
import com.aventstack.extentreports.ExtentTest;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class OTPPage extends WrapperMethods {

	
	//Constructor call to initialize the driver object
		public OTPPage(AppiumDriver<MobileElement> driver, ExtentTest testInfo) {

			WebDriverWait wait = new WebDriverWait(driver, 30);

			this.driver = driver;
			this.testInfo = testInfo;
			 PageFactory.initElements(new AppiumFieldDecorator(driver), this);
			

			reportStep("Waiting for the OTP Page", "INFO");
			try {
				wait.until(ExpectedConditions.visibilityOf(mobileNumber));
				reportStep("Successfully landed on the OTP page", "PASS");

			}catch(Exception e) {

				reportStep(e.getMessage(), "INFO");
				reportStep("Not able to land on the OTP page", "FAIL");
			}

		}

		//Variable declaration
		@FindBy(how = How.XPATH, using = "//android.widget.TextView[@content-desc='txt_otp_send_number']") MobileElement mobileNumber ;
		@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='Verify OTP']") MobileElement verifyOTP ;
		@FindBy(how = How.XPATH, using = "(//android.widget.EditText)[1]") MobileElement otpTextArea_1 ;
		@FindBy(how = How.XPATH, using = "(//android.widget.EditText)[2]") MobileElement otpTextArea_2 ;
		@FindBy(how = How.XPATH, using = "(//android.widget.EditText)[3]") MobileElement otpTextArea_3 ;
		@FindBy(how = How.XPATH, using = "(//android.widget.EditText)[4]") MobileElement otpTextArea_4 ;
		@FindBy(how = How.XPATH, using = "(//android.widget.EditText)[5]") MobileElement otpTextArea_5 ;
		@FindBy(how = How.XPATH, using = "(//android.widget.EditText)[6]") MobileElement otpTextArea_6 ;
		@FindBy(how = How.XPATH, using = "//android.widget.EditText[@content-desc='et_otp_number_box']") List<MobileElement> OTPMobileElements;
		@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='Invalid OTP code']") MobileElement inValidOTP ;
		@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='OTP was expired']") MobileElement OTPExpired ;
		@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='Change']") MobileElement change ;
		@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='RESEND CODE']")MobileElement ResendCode ;
		@FindBy(how = How.XPATH, using = "(//android.widget.ImageView)[1]") MobileElement backButton;
		
	
		
		//Method creation
		public OTPPage enterOTP(String OTPvalue) {
			

			reportStep("Entering the OTP value as : " + OTPvalue , "INFO");

			for(int counter = 0;counter<6;counter++) {

				if(enterTextWithOutClear_And_WithoutHidingKeyBoard(OTPMobileElements.get(counter), Character.toString(OTPvalue.charAt(counter)))) {

					if(counter==5) {

						reportStep("Entered the OTP "+ Character.toString(OTPvalue.charAt(counter)) + " Suceesfully ", "PASS");

					}
				}else {

					reportStep("Failed to enter the OTP "+ Character.toString(OTPvalue.charAt(counter)) , "FAIL");
				}

			}


			return this;
		}

		public OTPPage clickOnVerifyOTP() {

			reportStep("About to click on the Verify OTP  ", "INFO");

			if(clickAfterWait(verifyOTP)) {

				reportStep("Successfully clicked on the verify otp button ", "PASS");

			}else {
				reportStep("Not able to click on the verify otp button ", "FAIL");
			}

			return this;
		}

		public OTPPage verifyInValidOTP() {

			reportStep("Verifying the invalid OTP error message  ", "INFO");

			validateTheElementPresence(inValidOTP);
			
			return this;
		}

		public OTPPage verifyReSendCodeIn() {
			
			MobileElement reSendCodeIn = driver.findElementsByAccessibilityId("txt_otp_resend_timer_on").get(0);
			
			reportStep("Validating the ResendCodeIn text in  the OTP Page", "INFO"); 
			
			if(validateTheElementPresence(reSendCodeIn)) {
				
				reportStep("Successfully validated the ResendCodeIn text in the OTP page ", "PASS");
			}else {
				
				reportStep("Failed to validate the ResendCodeIn text in the OTP page ", "FAIL");
			}
			
			return new OTPPage(driver, testInfo);
			
		}
		
		public OTPPage clickOnResendCode() {
		
			reportStep("About to click on the Resend code link on the OTP Page", "INFO");
			
			if(clickAfterWait(ResendCode)){
				
				reportStep("Successfully clicked on the Resend code after the 30 seconds ", "PASS");
				
			}else{
				
				reportStep("Failed to click on the Resend code on the OTP Page ", "FAIL");
			 }
			
			return new OTPPage(driver, testInfo);
		}

		public JoinFreePage clickOnChange() {
			
			reportStep("About to click on the Change link at the OTP Page", "INFO");
			
			if(click(change)){
				
				reportStep("Successfully clicked on the change link at the OTP page ", "PASS");
			}else {
				
				reportStep("Fail to click on the change link at the OTP page ", "FAIL");
				
			}
			
			return new JoinFreePage(driver, testInfo);
			
		}
		
		public FBSignUpIntermediatePage clickOnChange_FBIntermediatePage() {
			
			reportStep("About to click on the Change link at the OTP Page", "INFO");
			
			if(click(change)){
				
				reportStep("Successfully clicked on the change link at the OTP page ", "PASS");
			}else {
				
				reportStep("Fail to click on the change link at the OTP page ", "FAIL");
				
			}
			
			return new FBSignUpIntermediatePage(driver, testInfo);
			
		}
		
		public OTPPage validateOTPSentToText() {
			
			MobileElement otpSendToMobile = driver.findElementsByAccessibilityId("txt_otp_send_number").get(0);
			
			reportStep("About to verify the OTP sent to Mobile number text ", "INFO");
			
			if(validateTheElementPresence(otpSendToMobile)) {
				
				reportStep("Successfully Verified the OTP sent to Mobile Number text at the OTP Page", "PASS");
			}else {
				
				reportStep("Failed to validate the OTP sent to Mobile Number text at the OTP Page", "FAIL");
			}
			
			return new OTPPage(driver, testInfo);
			
		}

		public OTPPage validateOTPPage() {

			MobileElement reSendCodeIn = driver.findElementsByAccessibilityId("txt_otp_resend_timer_on").get(0);
			
			MobileElement otpSendToMobile = driver.findElementsByAccessibilityId("txt_otp_send_number").get(0);

			reportStep("Validate the OTP page ", "INFO");

			if(validateTheElementPresence(change)){

				reportStep("Successfully validated the change link  Presence", "PASS");

			}else {

				reportStep("Failed to  validate the change link  Presence", "FAIL");
			}

			if(validateTheElementPresence(reSendCodeIn)){

				reportStep("Successfully validated the ResendCodeIn timer  Presence", "PASS");

			}else {

				reportStep("Failed to  validate the ResendCodeIn timer  Presence", "FAIL");
			}

			if(validateTheElementPresence(otpSendToMobile)){

				reportStep("Successfully validated the OTP sent to Mobile Number   Presence", "PASS");

			}else {

				reportStep("Failed to  validate the OTP sent to Mobile Number   Presence", "FAIL");
			}
			
			return  this;
	
		}
		
		public String getTheMobileNumberFromOTPPage() {
			
			MobileElement otpSendToMobile = driver.findElementsByAccessibilityId("txt_otp_send_number").get(0);
			
			String strMobileNumber = getText(otpSendToMobile);
			
			System.out.println("Mobile number " + strMobileNumber);
			
			return strMobileNumber;
			
		}

		public OTPPage waitFor3Minutes() {
			
			try {
			for(int count = 1;count<=61;count++) {
				
				tapOnNonClickableElement(otpTextArea_1);
				sleep(3000);
				
			}
			}catch (Exception e) {
				
				reportStep("Tapping on the element for 3 minutes to get the OTP expired was failed ", "FAIL");
			}
			return this;
		}

		public OTPPage validateOTPExpiredvalidation() {

			reportStep("About to validate the OTP expired validation - After 30 seconds entering the valid OTP should throw the OTP Expird error", "INFO");

			if(validateTheElementPresence(OTPExpired)) {

				reportStep("Successfully validated the OTP expird error validation ", "PASS");
			}else {

				reportStep("Fail to validate the OTP expird error validation ", "FAIL");
			}

			return this;
		}

		public FBSignUpIntermediatePage clickOnBackButton() {

			reportStep("About to click on the back button at the Facebook SignUp mobile number entering intermediate page", "INFO");

			if(click(backButton)) {

				reportStep("Successfully clicked on the back button the ", "PASS");

			}else {

				reportStep("Failed to  click on the back button the ", "FAIL");
			}
			
			
			return new FBSignUpIntermediatePage(driver, testInfo);
		}
		
		public PersonalDetailsIntermediatePage clickOnBackButton_PersonalDetails() {

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

			return new PersonalDetailsIntermediatePage(driver, testInfo);
		}

		public JoinFreePage clickOnBackButton_JF() {

			reportStep("About to click on the back button at the Facebook SignUp mobile number entering intermediate page", "INFO");

			if(click(backButton)) {

				reportStep("Successfully clicked on the back button the ", "PASS");

			}else {

				reportStep("Failed to  click on the back button the ", "FAIL");
			}

			return new JoinFreePage(driver, testInfo);
		}

	
}
