package com.app.ck.test;

import static io.appium.java_client.touch.offset.PointOption.point;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;

import com.app.ck.base.Base;
import com.app.ck.base.WrapperMethods;
import com.app.ck.pages.EKOnboardingPage;
import com.app.ck.pages.HomePage;
import com.app.ck.pages.IntermediateRetailerPage;
import com.app.ck.pages.JoinFreePage;
import com.app.ck.pages.OTPPage;
import com.app.ck.pages.ProfilePage;
import com.app.ck.pages.StorePage;
import com.app.ck.utilities.CashKaroUtility;
import com.app.ck.utilities.Utilities;
import com.aventstack.extentreports.Status;

import io.appium.java_client.MobileElement;
import io.appium.java_client.MultiTouchAction;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.PressesKey;
import io.appium.java_client.touch.WaitOptions;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class EKTestJoinFree extends WrapperMethods {
	
	
	/*******************************************************************/
	/************************ JOIN FREE ********************************/
	/******************************************************************/

	@Test
	public void joinFromGetStartedPage_TC001() {

		reportStep("TC001 is  started", "INFO");
		String OtpValue = new EKOnboardingPage(driver, testInfo).
		clickOnSignUpButton().
		enterAllJoinFreeFieldsAndVerifyOTP();

		// creating the new object for the OTP page
		OTPPage objOtpPage = new OTPPage(driver, testInfo);
		objOtpPage.
		enterOTP(OtpValue).
		clickOnVerifyOTP();

		// Once after clicking on the ClickOn verify OTP , it should navigate to the
		// Home page
		new HomePage(driver, testInfo);

		reportStep("TC001 is completed", "INFO");

	}

	@Test
	public void FBSignUpFromGetStartedPage_TC002() {

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo);
		objCashKaroUtility.resetFBUser(getTestData(1, "FBTestUserEmail2"));

		reportStep("TC001 is  started", "INFO");
		String OtpValue = new EKOnboardingPage(driver, testInfo).
		clickOnSignUpButton().
		clickOnJoinWithFacebook().
		enterFBNewUserEmail().
		enterNewFBUserPassword().
		clickOnFBLoginButton().
		clickOnContinueForNewUser().
		enterMobileNumberAndGetTheOTP();

		// creating the new object for the OTP page

		OTPPage objOtpPage = new OTPPage(driver, testInfo);
		objOtpPage.enterOTP(OtpValue).clickOnVerifyOTP();

		// Once after clicking on the Clickon verify OTP , it should navigate to the
		// Home page
		new HomePage(driver, testInfo);
		
		objCashKaroUtility.resetFBUser(getTestData(1, "FBTestUserEmail2"));

	}

	// JoinFree Negative validation :

	@Test
	public void validateJoinFreePageErrorMessage_TC003() {

		JoinFreePage objJoinFree = 
				new EKOnboardingPage(driver, testInfo).
				clickOnSignUpButton().
				clickOnGetOTPButton();

		// Error validation :
		objJoinFree.validateFullNameErrorMessage();

		objJoinFree.validateEmailAddressErrorMessage();

		objJoinFree.validatePasswordFieldErrorMessage();

		objJoinFree.validateMobileNumberFieldErrorValidation();

	}

	@Test
	public void validateExistingEmailIdErrorValidation_TC004() throws IOException {

		String email = 
				new EKOnboardingPage(driver, testInfo).
				clickOnSignUpButton().
				enterFullName().
				returnEnteredEmail();

		String mobileNumber = 
				new JoinFreePage(driver, testInfo).
				enterPassword().
				enterMobileNumber();

		String OtpValue = "";

		OtpValue = new JoinFreePage(driver, testInfo).
		clickOnGetOTPButton(mobileNumber);

		// creating the new object for the OTP page
		OTPPage objOtpPage = new OTPPage(driver, testInfo);

		objOtpPage.
		enterOTP(OtpValue).
		clickOnVerifyOTP();

		// Once after clicking on the Clickon verify OTP , it should navigate to the
		// Home page

		mobileNumber = new HomePage(driver, testInfo).
		clickOnProfileIconForSignedUser().
		clickOnLogout().
		clickOnSignUpButton_ForSecondTime().
		enterFullName().
		enterEmail(email).
		enterPassword().
		enterMobileNumber();

		OtpValue = new JoinFreePage(driver, testInfo).
		clickOnGetOTPButton(mobileNumber);

		objOtpPage.enterOTP(OtpValue).
		clickOnVerifyOTP();

		new JoinFreePage(driver, testInfo).
		validateExistingEmailIdErrorMessage();

	}

	@Test
	public void validateExistingMobileNumberErrorValidation_TC005() {

		String mobileNumber = 
				new EKOnboardingPage(driver, testInfo).
				clickOnSignUpButton().
				enterFullName().
				enterEmail().
				enterPassword().
				enterMobileNumber();

		String OtpValue = "";

		OtpValue = new JoinFreePage(driver, testInfo).
				clickOnGetOTPButton(mobileNumber);

		// creating the new object for the OTP page
		OTPPage objOtpPage = 
		new OTPPage(driver, testInfo);
		objOtpPage.
		enterOTP(OtpValue).
		clickOnVerifyOTP();

		// Once after clicking on the Clickon verify OTP , it should navigate to the
		// Home page
		new HomePage(driver, testInfo).
		clickOnProfileIconForSignedUser().
		clickOnLogout().
		clickOnSignUpButton_ForSecondTime().
		enterFullName().
		enterEmail().
		enterPassword().
		enterMobileNumber(mobileNumber);

		new JoinFreePage(driver, testInfo).clickOnGetOTPButton().validateThisMobileNumberBeenTaken();

	}

	@Test
	public void enterNumericToUserFullNameAndValidateUserFullName_TC006() throws IOException {

		String mobileNumber = 
		new EKOnboardingPage(driver, testInfo).
		clickOnSignUpButton().
		enterFullName("258625").
		enterEmail().
		enterPassword().
		enterMobileNumber();

		new JoinFreePage(driver, testInfo).
		clickOnGetOTPButtonForFailure(mobileNumber).
		validateFullNameErrorMessageForNumericEntry();

	}

	@Test
	public void enterNumericSpecialCharactersIntoEmailAddress_TC007() throws IOException {

		String mobileNumber = new EKOnboardingPage(driver, testInfo).
		clickOnSignUpButton().
		enterFullName().
		enterEmail("##12458@Test.com").
		enterPassword().
		enterMobileNumber();

		new JoinFreePage(driver, testInfo).
		clickOnGetOTPButtonForFailure(mobileNumber).
		validateInvalidEmailErrorMessage();

	}

	@Test
	public void enterInvalidMobileNumber_TC008() throws IOException {

		new EKOnboardingPage(driver, testInfo).
		clickOnSignUpButton().
		enterFullName().
		enterEmail().
		enterPassword().
		enterMobileNumber("5555522563");

		new JoinFreePage(driver, testInfo).
		clickOnGetOTPButton().
		inValidMobileNumberErrorValidation();

	}

	@Test
	public void validatePasswordFieldMinBoundaryValueError_TC009() throws IOException {

		new EKOnboardingPage(driver, testInfo).
		clickOnSignUpButton().
		enterFullName().
		enterEmail().
		enterPassword("pp").
		enterMobileNumber();

		new JoinFreePage(driver, testInfo).
		clickOnGetOTPButton().
		validateMinimumBoundaryValidationForPassword();

	}

	@Test
	public void validateUserFullNameMinBoundaryValueError_TC010() throws IOException {

		new EKOnboardingPage(driver, testInfo).
		clickOnSignUpButton().
		enterFullName("C").
		enterEmail().
		enterPassword().
		enterMobileNumber();

		new JoinFreePage(driver, testInfo).
		clickOnGetOTPButton().
		validateMinimumBoundaryValidationForFullName();

	}

	@Test
	public void validateEmailFieldMinBoundaryValueError_TC011() throws IOException {

		new EKOnboardingPage(driver, testInfo).
		clickOnSignUpButton().
		enterFullName().
		enterEmail("@co").
		enterPassword().
		enterMobileNumber();

		new JoinFreePage(driver, testInfo).clickOnGetOTPButton().validateMinimumBoundaryValidationForEmailAddress();

	}

	@Test
	public void validateMobileFieldMinBoundaryValueError_TC012() throws IOException {

		new EKOnboardingPage(driver, testInfo).
		clickOnSignUpButton().
		enterFullName().
		enterEmail().
		enterPassword().
		enterMobileNumber("78");

		new JoinFreePage(driver, testInfo).
		clickOnGetOTPButton().
		validateMinimumBoundaryValidationForMobileNumber();

	}
	
	@Test
	public void validateInvalidOTPAndThenEnterValidOTP_TC013() {

		String InvalidOtpValue = "999999";

		String validOtpValue = new EKOnboardingPage(driver, testInfo).clickOnSignUpButton()
				.enterAllJoinFreeFieldsAndVerifyOTP();

		// Creating the new object for the OTP Page
		OTPPage objOtpPage = new OTPPage(driver, testInfo);
		objOtpPage.enterOTP(InvalidOtpValue).clickOnVerifyOTP().verifyInValidOTP().enterOTP(validOtpValue)
				.clickOnVerifyOTP();

		// Once after clicking on the Click_On Verify OTP , It should navigate to the
		// Home Page
		new HomePage(driver, testInfo);

	}

	@Test
	public void validateOTPpage_TC014() {

		String validFirstOtpValue = new EKOnboardingPage(driver, testInfo).clickOnSignUpButton()
				.enterAllJoinFreeFieldsAndVerifyOTP();

		// Creating the new object for the OTP Page
		String mobileNumber = new OTPPage(driver, testInfo).validateOTPPage().clickOnChange()
				.clickOnGetOTPButtonForSuccess().enterOTP(validFirstOtpValue).clickOnVerifyOTP().verifyInValidOTP()
				.clickOnResendCode().getTheMobileNumberFromOTPPage().replaceAll("[^0-9]", "");

		String OTPvalue = Utilities.getSignUPOTP(mobileNumber);

		new OTPPage(driver, testInfo).enterOTP(OTPvalue).clickOnVerifyOTP();

		// Once after clicking on the Click_On Verify OTP , It should navigate to the
		// Home Page
		new HomePage(driver, testInfo);

	}

	@Test
	public void otpExpiredValidation_TC015() {

		String validFirstOtpValue = new EKOnboardingPage(driver, testInfo).clickOnSignUpButton()
				.enterAllJoinFreeFieldsAndVerifyOTP();

		// Creating the new object for the OTP Page
		String mobileNumber = new OTPPage(driver, testInfo).validateOTPPage().clickOnChange()
				.clickOnGetOTPButtonForSuccess().enterOTP(validFirstOtpValue).clickOnVerifyOTP().verifyInValidOTP()
				.clickOnResendCode().getTheMobileNumberFromOTPPage().replaceAll("[^0-9]", "");

		String OTPvalue = Utilities.getSignUPOTP(mobileNumber);

		new OTPPage(driver, testInfo).waitFor3Minutes().enterOTP(OTPvalue).clickOnVerifyOTP()
				.validateOTPExpiredvalidation().clickOnBackButton_JF();

	}

	// Negative FB sign up validation :

	@Test
	public void fBSignUPExistingMobileNumErrorValidation_TC016() {

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo);
		objCashKaroUtility.resetFBUser(getTestData(1, "FBTestUserEmail2"));

		String mobileNumber = new EKOnboardingPage(driver, testInfo).clickOnSignUpButton().enterFullName()
				.enterEmail().enterPassword().enterMobileNumber();

		String OtpValue = "";

		OtpValue = new JoinFreePage(driver, testInfo).clickOnGetOTPButton(mobileNumber);

		// creating the new object for the OTP page
		OTPPage objOtpPage = new OTPPage(driver, testInfo);
		objOtpPage.enterOTP(OtpValue).clickOnVerifyOTP();

		// Once after clicking on the Clickon verify OTP , it should navigate to the
		// Home page
		new HomePage(driver, testInfo).clickOnProfileIconForSignedUser().clickOnLogout()
				.clickOnSignUpButton_ForSecondTime().clickOnJoinWithFacebook().enterFBNewUserEmail()
				.enterNewFBUserPassword().clickOnFBLoginButton().clickOnContinueForNewUser()
				.enterMobileNumberAndClickOnGetOTPButton(mobileNumber)
				.validateExistingMobileNumErrorAtFBSignUpIntermediatePage();

	}

	@Test
	public void fBSignUPinValidMobileNumErrorValidation_TC017() {

		String invalidMobileNumber = "2355556666";

		new EKOnboardingPage(driver, testInfo).
		clickOnSignUpButton().
		clickOnJoinWithFacebook().
		enterFBNewUserEmail().
		enterNewFBUserPassword().
		clickOnFBLoginButton().
		clickOnContinueForNewUser().
		enterMobileNumberAndClickOnGetOTPButton(invalidMobileNumber).
		inValidMobileNumberErrorAndMobilenumberIntermediatePageTextValidataion();

	}

	@Test
	public void fBSignUpClickingBackbuttonFromOTPScreen_TC018() {
		
		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo);
		objCashKaroUtility.resetFBUser(getTestData(1, "FBTestUserEmail2"));
		String OtpValue = 
				new EKOnboardingPage(driver, testInfo).
				clickOnSignUpButton().
				clickOnJoinWithFacebook().
				enterFBNewUserEmail().
				enterNewFBUserPassword().
				clickOnFBLoginButton().
				clickOnContinueForNewUser().
				enterMobileNumberAndGetTheOTP();

		// creating the new object for the OTP page

		OTPPage objOtpPage = new OTPPage(driver, testInfo);
		objOtpPage.enterOTP(OtpValue).
		clickOnBackButton();

	}

	@Test
	public void fBSignUpClickingBackbuttonFromMobileNumberEntryScreen_TC019() {

		String strMobileNumber = "2222222222";

		reportStep("TC001 is  started", "INFO");
		new EKOnboardingPage(driver, testInfo).
		clickOnSignUpButton().
		clickOnJoinWithFacebook().
		enterFBNewUserEmail().
		enterNewFBUserPassword().
		clickOnFBLoginButton().
		clickOnContinueForNewUser().
		enterMobileNumberAndClickOnGetOTPButton(strMobileNumber).
		clickOnBackButton();

	}

	/*******************************************************************/
	/********              		ENHANCEMENT    		     	 ***********/
	/*******************************************************************/
	
	@Test
	public void validateInvalidOTPAndThenEnterValidOTP_NavigationFBSignUp_TC020() {

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo);
		objCashKaroUtility.resetFBUser(getTestData(1, "FBTestUserEmail2"));

		String InvalidOtpValue = "999999";

		String validOtpValue =
		new EKOnboardingPage(driver, testInfo).
		clickOnSignUpButton().
		clickOnJoinWithFacebook().
		enterFBNewUserEmail().
		enterNewFBUserPassword().
		clickOnFBLoginButton().
		clickOnContinueForNewUser().
		enterMobileNumberAndGetTheOTP();

		// Creating the new object for the OTP Page
		OTPPage objOtpPage = new OTPPage(driver, testInfo);

		objOtpPage.enterOTP(InvalidOtpValue).
		clickOnVerifyOTP().
		verifyInValidOTP().
		enterOTP(validOtpValue).
		clickOnVerifyOTP();

		// Once after clicking on the Click_On Verify OTP , It should navigate to the
		// Home Page
		new HomePage(driver, testInfo);

	}

	@Test
	public void validateOTPpage_NavigationFBSignUp_TC021() {


		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo);
		objCashKaroUtility.resetFBUser(getTestData(1, "FBTestUserEmail2"));
		String mobileNumber = objCashKaroUtility.generateMobileNumber();

		String validFirstOtpValue = 
		new EKOnboardingPage(driver, testInfo).
		clickOnSignUpButton().
		clickOnJoinWithFacebook().
		enterFBNewUserEmail().
		enterNewFBUserPassword().
		clickOnFBLoginButton().
		clickOnContinueForNewUser().
		enterMobileNumberAndGetTheOTP();

		// Creating the new object for the OTP Page
		mobileNumber = new OTPPage(driver, testInfo).
		validateOTPPage().
		clickOnChange_FBIntermediatePage().
		clickOnGetOTPBtn(mobileNumber).
		enterOTP(validFirstOtpValue).
		clickOnVerifyOTP().
		verifyInValidOTP().
		clickOnResendCode().
		getTheMobileNumberFromOTPPage().
		replaceAll("[^0-9]", "");

		String OTPvalue = Utilities.getSignUPOTP(mobileNumber);

		new OTPPage(driver, testInfo).
		enterOTP(OTPvalue).
		clickOnVerifyOTP();

		// Once after clicking on the Click_On Verify OTP , It should navigate to the
		// Home Page
		new HomePage(driver, testInfo);

	}

	@Test
	public void otpExpiredValidation_NavigationFBSignUp_TC022() {


		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo);
		objCashKaroUtility.resetFBUser(getTestData(1, "FBTestUserEmail2"));
		String mobileNumber = objCashKaroUtility.generateMobileNumber();

		String validFirstOtpValue =
				new EKOnboardingPage(driver, testInfo).
				clickOnSignUpButton().
				clickOnJoinWithFacebook().
				enterFBNewUserEmail().
				enterNewFBUserPassword().
				clickOnFBLoginButton().
				clickOnContinueForNewUser().
				enterMobileNumberAndGetTheOTP();

		// Creating the new object for the OTP Page
		mobileNumber = new OTPPage(driver, testInfo).
				validateOTPPage().
				clickOnChange_FBIntermediatePage().
				clickOnGetOTPBtn(mobileNumber).
				enterOTP(validFirstOtpValue).
				clickOnVerifyOTP().
				verifyInValidOTP().
				clickOnResendCode().
				getTheMobileNumberFromOTPPage().
				replaceAll("[^0-9]", "");

		String OTPvalue = Utilities.getSignUPOTP(mobileNumber);

		new OTPPage(driver, testInfo).
		waitFor3Minutes().
		enterOTP(OTPvalue).
		clickOnVerifyOTP().
		validateOTPExpiredvalidation().
		clickOnBackButton();

	}

	@Test
	public void validateGoogleSuggestionInJoinFreePage_TC023() {
		
		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo);
		String mobileNumber = objCashKaroUtility.generateMobileNumber();

		reportStep("Google Suggestion In JoinFree Page is Started Successfully ", "INFO");
		
		String OtpValue = 
		new EKOnboardingPage(driver, testInfo).
		clickOnSignUpButton().
		enterFullName().
		validateGoogleSuggestionInJoinFree().
		enterPassword().
		enterMobileNumber(mobileNumber).
		clickOnGetOTPButton(mobileNumber);;
		
		// creating the new object for the OTP page
		OTPPage objOtpPage = new OTPPage(driver, testInfo);

		reportStep("Google Suggestion In JoinFree Page is completed Successfully ", "INFO");

	}
	
	@Test
	public void validateJoinFreePageTexts_TC024() {
		
		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo);
		String mobileNumber = objCashKaroUtility.generateMobileNumber();

		reportStep("About to validate the JoinFree Texts ", "INFO");
		
		new EKOnboardingPage(driver, testInfo).
		clickOnSignUpButton().
		validateJoinFreePage();
		reportStep("Validate JoinFree Texts ", "INFO");

	}
	
	@Test
	public void signUpFromLoginPage_TC025() {
		
		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo);
		String mobileNumber = objCashKaroUtility.generateMobileNumber();
		String email = objCashKaroUtility.generateEmail();

		reportStep("About to SignUp  from the Login page ", "INFO");
		
		String otpValue = new EKOnboardingPage(driver, testInfo).
		clickOnSignInbutton().
		clickOnJoinFreeLink().
		enterFullName().
		enterEmail(email).
		enterPassword().
		enterMobileNumber(mobileNumber).
		clickOnGetOTPButton(mobileNumber);

		new OTPPage(driver, testInfo).
		enterOTP(otpValue).
		clickOnVerifyOTP();
		
		new HomePage(driver, testInfo);
		 
		
		reportStep("Successfully Signed Up from  Login Page ", "INFO");

	}
	
	
}
