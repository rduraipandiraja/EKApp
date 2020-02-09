package com.app.ck.pages;

import java.util.List;


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

public class PaymentOTPPage extends WrapperMethods {

	
	//Constructor call to initialize the driver object
		public PaymentOTPPage(AppiumDriver<MobileElement> driver, ExtentTest testInfo) {

			WebDriverWait wait = new WebDriverWait(driver, 30);

			this.driver = driver;
			this.testInfo = testInfo;
			PageFactory.initElements(new AppiumFieldDecorator(driver), this);
			

			reportStep("Waiting for the Payment OTP Page", "INFO");
			try {
				wait.until(ExpectedConditions.visibilityOf(verifyOTP));
				reportStep("Successfully landed on the Payment OTP page", "PASS");

			}catch(Exception e) {

				reportStep(e.getMessage(), "INFO");
				reportStep("Not able to land on the  Payment OTP page", "FAIL");
			}

		}

		//Variable declaration
		@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='Verify OTP']") MobileElement verifyOTP ;
		@FindBy(how = How.XPATH, using = "(//android.widget.EditText)[1]") MobileElement otpTextArea_1 ;
		@FindBy(how = How.XPATH, using = "(//android.widget.EditText)[2]") MobileElement otpTextArea_2 ;
		@FindBy(how = How.XPATH, using = "(//android.widget.EditText)[3]") MobileElement otpTextArea_3 ;
		@FindBy(how = How.XPATH, using = "(//android.widget.EditText)[4]") MobileElement otpTextArea_4 ;
		@FindBy(how = How.XPATH, using = "(//android.widget.EditText)[5]") MobileElement otpTextArea_5 ;
		@FindBy(how = How.XPATH, using = "(//android.widget.EditText)[6]") MobileElement otpTextArea_6 ;
		@FindBy(how = How.XPATH, using = "//android.widget.EditText") List<MobileElement> OTPMobileElements;
		@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='Invalid OTP code']") MobileElement inValidOTP ;
		@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='OTP was expired']") MobileElement OTPExpired ;
		@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='RESEND CODE']")MobileElement ResendCode ;
		@FindBy(how = How.XPATH, using = "//android.view.ViewGroup[@content-desc=\"Payment Request\"]/android.view.ViewGroup/android.widget.ImageView\r\n" + 
				"") MobileElement backButton;
		@FindBy(how = How.XPATH, using = "//android.widget.TextView[contains(@text ,'Resend Code in' )]") MobileElement resendCodeIn;
		@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text = 'Enter the OTP sent to your registered mobile number' ]") MobileElement otpPageText;
		
		@FindBy(how = How.XPATH, using = "//android.widget.Button[@text='OK']") 
		MobileElement paymentPopupOKButton ;
		@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='Alert']") 
		MobileElement paymentPopupAlertText ;
		@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='Congratulations! We have received your payment request. Please allow 6-8 business days for the money to reach you. Enjoy your earnings!']") 
		MobileElement paymentSuccessMessage ;
		@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='Thank you for your donation and generosity. Your donation shall be sent shortly. Also, please allow 3-5 working days for your cashback to reach you.']") 
		MobileElement charitySuccessMessage ;
		
		
		
		//Method creation
		public PaymentOTPPage enterOTP(String OTPvalue) {

			reportStep("Entering the OTP value as " + OTPvalue , "INFO");

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

		public PaymentOTPPage enterOTP_Live(String OTPvalue) {

			reportStep("Entering the OTP value as " + OTPvalue , "INFO");

			for(int counter = 0;counter<6;counter++) {

				if(enterTextWithOutClear_And_WithoutHidingKeyBoard(OTPMobileElements.get(counter), Character.toString(OTPvalue.charAt(counter)))) {

					if(counter==5) {

						reportStep("Entered the OTP "+ Character.toString(OTPvalue.charAt(counter)) + " Suceesfully ", "PASS");

					}
				}else {

					reportStep("Failed to enter the OTP "+ Character.toString(OTPvalue.charAt(counter)) , "FAIL");
				}

			}
			
			sleep(15000);

			return this;
		}

		public MyEarningsPage clickOnVerifyOTPAndValidateThePaymentSuccessMessage() {

			reportStep("About to click on the Verify OTP  ", "INFO");

			if(clickAfterWait(verifyOTP)) {

				reportStep("Successfully clicked on the verify otp button ", "PASS");

			}else {
				reportStep("Successfully clicked on the verify otp button ", "FAIL");
			}

			reportStep("About to validate the Payment success message as "
					+ "Congratulations! We have received your payment request. "
					+ "Please allow 6-8 business days for the money to reach you. "
					+ "Enjoy your earnings!", "INFO");

			validateTheElementPresence(paymentSuccessMessage);
			validateTheElementPresence(paymentPopupAlertText);

			if(click(paymentPopupOKButton)) {

				reportStep("Successfully clicked on the Ok button at the alert popup", "INFO");
			}else {
				reportStep("Failed to click on the Ok button at the alert popup", "FAIL");
			}

			return new MyEarningsPage(driver, testInfo);
		}

		public PaymentOTPPage clickOnVerifyOTPforFailure() {

			reportStep("About to click on the Verify OTP for failure ", "INFO");

			if(clickAfterWait(verifyOTP)) {

				reportStep("Successfully clicked on the verify otp button ", "PASS");

			}else {
				reportStep("Successfully clicked on the verify otp button ", "FAIL");
			}

			return this;
		}

		public MyEarningsPage clickOnVerifyOTPAndvalidateCharityDonationAmountSuccessMessage() {

			reportStep("About to click on the Verify OTP  ", "INFO");

			if(clickAfterWait(verifyOTP)) {

				reportStep("Successfully clicked on the verify otp button ", "PASS");

			}else {
				reportStep("Successfully clicked on the verify otp button ", "FAIL");
			}

			reportStep("About to validate : Thank you for your donation and generosity. Your donation shall be sent shortly. Also, please allow 3-5 working days for your cashback to reach you.", "INFO");

			validateTheElementPresence(charitySuccessMessage);
			validateTheElementPresence(paymentPopupAlertText);

			if(click(paymentPopupOKButton)) {

				reportStep("Successfully clicked on the Ok button at the alert popup", "INFO");
			}else {
				reportStep("Failed to click on the Ok button at the alert popup", "FAIL");
			}

			return new MyEarningsPage(driver, testInfo);
		}

		public PaymentOTPPage verifyInValidOTP() {

			reportStep("Verifying the invalid OTP error message  ", "INFO");

			validateTheElementPresence(inValidOTP);

			return this;
		}

		public PaymentOTPPage verifyReSendCodeIn() {

			validateTheElementPresence(resendCodeIn);

			return this;

		}

		public PaymentOTPPage clickOnResendCode() {

			sleep(5000);

			reportStep("About to click on the Resend code link on the OTP Page", "INFO");

			if(click(ResendCode)){

				reportStep("Successfully clicked on the Resend code after the 30 seconds ", "PASS");

			}else{

				reportStep("Failed to click on the Resend code on the OTP Page ", "FAIL");
			}

			return new PaymentOTPPage(driver, testInfo);
		}

		public PaymentOTPPage validateOTPSentToText() {

			MobileElement otpSendToMobile = driver.findElementsByAccessibilityId("txt_otp_send_number").get(0);

			reportStep("About to verify the OTP sent to Mobile number text ", "INFO");

			if(validateTheElementPresence(otpSendToMobile)) {

				reportStep("Successfully Verified the OTP sent to Mobile Number text at the OTP Page", "PASS");
			}else {

				reportStep("Failed to validate the OTP sent to Mobile Number text at the OTP Page", "FAIL");
			}

			return new PaymentOTPPage(driver, testInfo);

		}

		public String getTheMobileNumberFromPaymentOTPPage() {

			MobileElement otpSendToMobile = driver.findElementsByAccessibilityId("txt_otp_send_number").get(0);

			String strMobileNumber = getText(otpSendToMobile);

			System.out.println("Mobile number " + strMobileNumber);

			return strMobileNumber;

		}

		public PaymentOTPPage waitFor3Minutes() {

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

		public PaymentOTPPage waitFor30Seconds() {

			try {

				reportStep("Waiting for 30 seconds to click on Resend button ", "INFO");

				for(int count = 1;count<=10;count++) {

					tapOnNonClickableElement(otpTextArea_1);
					sleep(3000);

				}
			}catch (Exception e) {

				reportStep("Tapping on the element for 30 seconds to get the OTP expired was failed ", "FAIL");
			}
			return this;
		}

		public PaymentOTPPage validateOTPExpiredvalidation() {

			reportStep("About to validate the OTP expired validation - After 30 seconds entering the valid OTP should throw the OTP Expird error", "INFO");

			validateTheElementPresence(otpPageText);

			if(validateTheElementPresence(OTPExpired)) {

				reportStep("Successfully validated the OTP expird error validation ", "PASS");
			}else {

				reportStep("Fail to validate the OTP expird error validation ", "FAIL");
			}

			return this;
		}

		public PaymentRequestPage clickOnBackButton() {

			reportStep("About to click on the back button at the Payment OTP Page ", "INFO");

			if(click(backButton)) {

				reportStep("Successfully clicked on the back button at the Payment OTP page ", "PASS");

			}else {

				reportStep("Failed to  click on the back button Payment OTP page ", "FAIL");
			}
		

			return new PaymentRequestPage(driver, testInfo);
		}

	

		
}
