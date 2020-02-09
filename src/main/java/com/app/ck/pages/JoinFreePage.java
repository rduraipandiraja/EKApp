package com.app.ck.pages;

import java.io.IOException;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.core.env.SystemEnvironmentPropertySource;

import com.app.ck.base.WrapperMethods;
import com.app.ck.utilities.Utilities;
import com.aventstack.extentreports.ExtentTest;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class JoinFreePage extends WrapperMethods {

	//Constructor call to initialize the driver object
	public JoinFreePage(AppiumDriver<MobileElement> driver, ExtentTest testInfo) {

		WebDriverWait wait = new WebDriverWait(driver, 30);

		this.driver = driver;
		this.testInfo = testInfo;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);

		try {
			
			reportStep("About to click on the Allow on the permission popup in Join Free page", "INFO");

			allowThePermissionPopup(); //It handles the Permission popUp

		} catch (Exception e) {

			System.out.println("No permission popup comes in the JoinFree page");
			
			reportStep("No permission popup comes in the JoinFree page", "INFO");
			
		}

		try {
			
			reportStep("About to click on the Allow on the permission popup in JoinFree page", "INFO");

			allowThePermissionPopup(); //It handles the Permission popUp

		} catch (Exception e) {

			System.out.println("No permission popup comes in the JoinFree page");
			
			reportStep("No permission popup comes in the JoinFree page", "INFO");
			
		}

		try {
			reportStep("Waiting for the JoinFreePage ", "INFO");
			wait.until(ExpectedConditions.visibilityOf(userFullName));
			reportStep("Successfully landed on the JoinFree page", "PASS");

		}catch(Exception e) {

			reportStep("Not able to land on the JoinFree page", "FAIL");

		}

	}

	//Constructor call to initialize the driver object
	public JoinFreePage(AppiumDriver<MobileElement> driver, ExtentTest testInfo,int joinFreePopUpCounter) {

		System.out.println("join Free Popup Counter "+ joinFreePopUpCounter);

		WebDriverWait wait = new WebDriverWait(driver, 15);

		this.driver = driver;
		this.testInfo = testInfo;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);

		try {
			reportStep("Waiting for the JoinFreePage ", "INFO");
			wait.until(ExpectedConditions.visibilityOf(userFullName));
			reportStep("Successfully landed on the JoinFree page", "PASS");

		}catch(Exception e) {

			reportStep("Not able to land on the JoinFree page", "FAIL");

		}

	}

	
	//Variable declaration
	@FindBy(how = How.XPATH, using = "//android.widget.EditText[@content-desc= 'et_signup_fullname']") 
	MobileElement userFullName ;
	@FindBy(how = How.XPATH, using = "//android.widget.EditText[@content-desc= 'et_signup_email']") 
	MobileElement emailAddress ;
	@FindBy(how = How.XPATH, using = "//android.widget.EditText[@content-desc= 'et_signup_password']")
	MobileElement choosePassword ;
	@FindBy(how = How.XPATH, using = "//android.widget.EditText[@content-desc= 'et_signup_mobile']")
	MobileElement mobileNumber ;
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='Get OTP']")
	MobileElement GetOTP ;

	//Error validation
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='Full Name:*']")
	MobileElement fullNameFieldError_1;
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='Please enter your Name']") 
	MobileElement fullNameFieldError_2;
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='Email Address:*']") 
	MobileElement emailAddressError_1;
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='Please enter the Email']") 
	MobileElement emailAddressError_2;
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='Choose Password:*']")
	MobileElement choosePasswordError_1;
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='Please enter the Password']") 
	MobileElement choosePasswordError_2;
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='Mobile Number:*']") 
	MobileElement mobileError_1;
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='Please enter the Mobile Number']") 
	MobileElement mobileError_2;

	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='Number already in use. Please enter different number.']") 
	MobileElement existingMobileNumErrorValidation;
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='An account with this Email ID already exists!']") 
	MobileElement emailhasalreadybeenTakenError;
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='JOIN WITH FACEBOOK']") 
	MobileElement joinWithFBButton;
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='SIGN UP']")
	MobileElement joinFreeText;
	@FindBy(how = How.XPATH, using = "//android.widget.ImageView") 
	MobileElement JoinFreePageBackButton ;
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='Already Registered? ']")
	MobileElement alreadyRegisteredText;
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='SIGN IN']")
	MobileElement signInLink;
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='Terms and Conditions']")
	MobileElement termsConitionLink;
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='By signing up, you agree to ']")
	MobileElement termsConitionText;
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='Please enter minimum 2 characters long']") 
	MobileElement fullNameMinMinusBoundaryError;
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='Please enter a valid Email ID']")
	MobileElement emailMinMinusBoundaryError;
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='Password must be at least 6 characters long']") 
	MobileElement passwordMinMinusBoundaryError;
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='OR']") 
	MobileElement orText;
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='Please enter minimum 10 digits']") 
	MobileElement mobileMinMinusBoundaryError;
	@FindBy(how = How.ID, using = "com.android.packageinstaller:id/permission_allow_button") 
	MobileElement allowLinkInPermissionPopup;
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='Please enter valid Name']") 
	MobileElement pleaseEnterValidName;
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='Please enter a valid Email ID']") 
	MobileElement pleaseEnterValidEmail;
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='Please enter a valid Mobile Number']") 
	MobileElement pleaseEnterInvalidMobNum;
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[contains(@text,'Invited by chandru')]") 
	MobileElement referralBy;

	//Google Suggestions ;
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='Sign in with']") 
	MobileElement gSuggestion_signInWithText;
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='Cashkaro Development']") 
	MobileElement gSuggestion_Name;
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[contains(@text,'@gmail.com')]") 
	MobileElement gSuggestion_Gmail;
	//Join Free Text :
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='Join EarnKaro Free']") 
	MobileElement joinEarnKaroFreeText;

	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='Join 1000+ re-sellers earning up to Rs 20,000 each month by sharing deals!']") 
	MobileElement join20KReSellersText;

	//Method Creation for JoinFree Page validation : 
	
	public JoinFreePage allowThePermissionPopup() {

		System.out.println("About to click onn the Allow on the permissionn popup");

		try {
		if(clickAfterWait(allowLinkInPermissionPopup)) {

			reportStep("Cliked on the Allow link "+ allowLinkInPermissionPopup + " on the permission popup at the JoinFree page", "INFO");

		}else {

			reportStep("Not able to click on the Allow link on the permission popup at the JoinFree  page", "INFO");

		}
		
		}
		
		catch (Exception e) {
		
			System.out.println("No Pemission popup comes in the JoinFree popup");
		}
	

		return this;
	}

	public JoinFreePage enterFullName() {

		reportStep("About to enter the full name in to the username field", "INFO");

		if(enterText(userFullName, getTestData(3, "UserFullName"))) {

			reportStep("Successfully entered the Userfullname as "+ getTestData(3, "UserFullName") , "PASS");

		}else {

			reportStep("Failed to enter the Userfullname as "+ getTestData(3, "UserFullName") , "FAIL");

		}
		return this;

	}
	
	public JoinFreePage enterFullName(String userFull_Name) {

		reportStep("About to enter the full name in to the username field", "INFO");

		if(enterText(userFullName, userFull_Name)) {

			reportStep("Successfully entered the Userfullname as "+ userFull_Name , "PASS");

		}else {

			reportStep("Failed to enter the Userfullname as "+ userFull_Name , "FAIL");

		}
		return this;

	}

	public JoinFreePage enterEmail() {

		String email =  Utilities.generateEmailWithTimeStamp();

		reportStep("About to enter the full name in to the username field", "INFO");
		
		
		if(clickOnNoneOFTheAbove(emailAddress)) {

			reportStep("Successfully clicked on the None Of the Above link in the popup ", "INFO");
		}else {

			reportStep("Fail to click on the None Of the Above link in the popup ", "INFO");
		}
		

		if(enterTextUsingSendKeys(emailAddress,email)) {

			reportStep("Successfully entered the emailAddress as "+ email , "PASS");

		}else {

			reportStep("Failed to enter the emailAddress as "+ email , "FAIL");

		}
		
		return this;

	}
	
	public JoinFreePage enterEmail(String email) {


		reportStep("About to enter the  Email in to the Email field as "+ email, "INFO");

		if(clickOnNoneOFTheAbove(emailAddress)) {

			reportStep("Successfully clicked on the None Of the Above link in the popup ", "INFO");
		}else {

			reportStep("Fail to click on the None Of the Above link in the popup ", "INFO");
		}

		
		if(enterTextUsingSendKeys(emailAddress,email)) {

			reportStep("Successfully entered the emailAddress as "+ email , "PASS");

		}else {

			reportStep("Failed to enter the emailAddress as "+ email , "FAIL");

		}
		
		return this;

	}
	
	public String returnEnteredEmail() {

		String email =  Utilities.generateEmailWithTimeStamp();

		reportStep("About to enter the full name in to the username field", "INFO");
		
		if(clickOnNoneOFTheAbove(emailAddress)) {

			reportStep("Successfully clicked on the None Of the Above link in the popup ", "INFO");
		}else {

			reportStep("Fail to click on the None Of the Above link in the popup ", "INFO");
		}


		if(enterTextUsingSendKeys(emailAddress,email)) {

			reportStep("Successfully entered the emailAddress as "+ email , "PASS");

		}else {

			reportStep("Failed to enter the emailAddress as "+ email , "FAIL");

		}
		
		return email;

	}

	public JoinFreePage enterPassword() {

		reportStep("About to enter the password in to the username field", "INFO");

		if(enterText(choosePassword,getTestData(3, "Password"))) {

			reportStep("Successfully entered the choosePassword as "+ getTestData(3, "Password") , "PASS");

		}else {

			reportStep("Failed to enter the choosePassword as "+ getTestData(3, "Password") , "FAIL");

		}

		return this;
	}
	
	public JoinFreePage enterPassword(String password) {

		reportStep("About to enter the password in to the username field", "INFO");

		if(enterText(choosePassword,password)) {

			reportStep("Successfully entered the choosePassword as "+ getTestData(3, "Password") , "PASS");

		}else {

			reportStep("Failed to enter the choosePassword as "+ getTestData(3, "Password") , "FAIL");

		}

		return this;
	}

	public String enterMobileNumber() {

		String randomMobileNum = Utilities.generateRandomNumber(10); //It randomly generates the mobile number starts with the 6,7,8 or 9

		reportStep("About to enter the password in to the username field", "INFO");

		if(enterText(mobileNumber,randomMobileNum)) {

			reportStep("Successfully entered the MobileNumber as "+ randomMobileNum , "PASS");

		}else {

			reportStep("Failed to enter the MobileNumber as "+  randomMobileNum  , "FAIL");

		}
		
		
		hideKeyboard();
			
		return randomMobileNum;

	}
	
	public JoinFreePage enterMobileNumber(String mobNum) {


		reportStep("About to enter the password in to the username field", "INFO");
		
//		if(clickOnNoneOFTheAbove(mobileNumber)) {
//			
//			try {
//				
//				driver.hideKeyboard();
//				
//			}catch (Exception ee) {
//				
//				System.out.println("Hide keyboard exception");
//				
//				reportStep("Not able to close the keyboard", "INFO");
//				
//			}
//
//			reportStep("Successfully clicked on the None Of the Above link in the popup ", "INFO");
//		}else {
//
//			reportStep("Fail to click on the None Of the Above link in the popup ", "INFO");
//		}

		if(enterText(mobileNumber,mobNum)) {

			reportStep("Successfully entered the MobileNumber as "+ mobNum , "PASS");

		}else {

			reportStep("Failed to enter the MobileNumber as "+  mobNum  , "FAIL");

		}
		
		return this;
		
	}

	public String enterAllJoinFreeFieldsAndVerifyOTP()  {

		String otp = "";

		reportStep("About to enter the JoinFree fields one by one", "INFO");

		String mobileNumber = enterFullName().enterEmail().enterPassword().enterMobileNumber();

		String strresponse;

		otp = clickOnGetOTPButton(mobileNumber);

		new OTPPage(driver, testInfo);
		
		System.out.println("otp value is   "+ otp);

		System.out.println(mobileNumber);

		return otp;

	}

	public String clickOnGetOTPButton(String mobileNumber)  {
		
		hideKeyboard();

		reportStep("About to click on the GETOTP  button ","INFO");
		
		String getOTPXpath = "//android.widget.TextView[@text='Get OTP']";
		
		scrollTillRequiredElementIsVisibleFromDownToUp(getOTPXpath);

		if(clickAfterWait(GetOTP)) {

			reportStep("Successfully clicked on the GETOTP button", "PASS");
		}else {

			reportStep("Failed to click on the GETOTP button", "FAIL");
		}

		new OTPPage(driver, testInfo); // Dont remove this, once the OTP page comes then only we can hit the getCode endPoint
		
		return Utilities.getSignUPOTP(mobileNumber); 

	}
	
	public JoinFreePage clickOnGetOTPButtonForFailure(String mobileNumber)  {
		
		hideKeyboard();

		reportStep("About to click on the GETOTP  button ","INFO");

		if(click_WaitFor25Sec(GetOTP)) {

			reportStep("Successfully clicked on the GETOTP button", "PASS");
		}else {

			reportStep("Failed to click on the GETOTP button", "FAIL");
		}

		return this;

	}

	public  JoinFreePage clickOnGetOTPButton()  {
		
		hideKeyboard();

		reportStep("About to click on the GETOTP  button ","INFO");

		if(click_WaitFor25Sec(GetOTP)) {

			reportStep("Successfully clicked on the GETOTP button", "PASS");
		}else {

			reportStep("Failed to click on the GETOTP button", "FAIL");
		}

		return this;

	}
	
	public  OTPPage clickOnGetOTPButtonForSuccess()  {
		
		hideKeyboard();

		reportStep("About to click on the GETOTP  button ","INFO");

		if(click_WaitFor25Sec(GetOTP)) {

			reportStep("Successfully clicked on the GETOTP button", "PASS");
		}else {

			reportStep("Failed to click on the GETOTP button", "FAIL");
		}

		return new OTPPage(driver, testInfo);

	}

	public void validateFullNameErrorMessage() {

		validateTheElementPresence(fullNameFieldError_1);
		validateTheElementPresence(fullNameFieldError_2);

	}
	
	public void validateFullNameErrorMessageForNumericEntry() {

		validateTheElementPresence(pleaseEnterValidName);

	}

	public void validateEmailAddressErrorMessage() {

		validateTheElementPresence(emailAddressError_1);
		validateTheElementPresence(emailAddressError_1);

	}

	public void validatePasswordFieldErrorMessage() {

		validateTheElementPresence(choosePasswordError_1);
		validateTheElementPresence(choosePasswordError_2);

	}

	public void validateMobileNumberFieldErrorValidation() {
		
		scrollFromDownToUpinApp_FourSecDuration();
		validateTheElementPresence(mobileError_1);
		validateTheElementPresence(mobileError_2);
	}

	public void validateMinimumBoundaryValidationForFullName() {

		validateTheElementPresence(fullNameMinMinusBoundaryError);

	}

	public void validateMinimumBoundaryValidationForEmailAddress() {

		validateTheElementPresence(emailMinMinusBoundaryError);

	}

	public void validateMinimumBoundaryValidationForPassword() {

		validateTheElementPresence(passwordMinMinusBoundaryError);

	}

	public void validateMinimumBoundaryValidationForMobileNumber() {

		validateTheElementPresence(mobileMinMinusBoundaryError);

	}

	public void enterNumbericValueTotheFullNameAndValidateErrorMessage() {

		reportStep("Entering the numeric characters to the UserFull Name and validating the error message ", "INFO");

		userFullName.sendKeys(getTestData(3, "InvalidNumeric"));

		validateTheElementPresence(fullNameFieldError_1);

		validateTheElementPresence(fullNameFieldError_1);


	}

	public JoinFreePage validateInvalidEmailErrorMessage() {

		reportStep("Entering the invalid special characters to the Email address and validating the error message ", "INFO");

		validateTheElementPresence(pleaseEnterValidEmail);
	
		return this;

	}

	public void validateExistingEmailIdErrorMessage() {

		if(validateTheElementPresence(emailhasalreadybeenTakenError)) {

			reportStep("Successfully validated the Existing email address error message ", "PASS");

		}else {

			reportStep("Failed to show the Existing email address error message ", "FAIL");

		}
	}

	public void validateThisMobileNumberBeenTaken() {

		if(validateTheElementPresence(existingMobileNumErrorValidation)) {

			reportStep("Successfully validated this Mobile number already been taken  ", "PASS");

		}else {

			reportStep("Failed to validate the this Mobile number already been taken ", "FAIL");

		}
	}

	public void inValidMobileNumberErrorValidation() {

		if(validateTheElementPresence(pleaseEnterInvalidMobNum)) {

			reportStep("Successfully validated that invalid mobile number validation ", "PASS");

		}else {

			reportStep("Failed to validate invalid mobile number validation", "FAIL");

		}
	}

	public FacebookPage clickOnJoinWithFacebook() {

		reportStep("About to click on the Join With Face book button ", "INFO");

		if(click(joinWithFBButton)) {

			reportStep("Successfully clicked on the JoinWithFacebook button ", "Pass");

		}else {

			reportStep("Failed to  click on the JoinWithFacebook button ", "FAIL");

		}
		
		return new FacebookPage(driver, testInfo);
	}

	public EKOnboardingPage clickOnJoinFreeBackButton() {

		reportStep("About to click on the JoinFree page back button ", "INFO");

		if(click(JoinFreePageBackButton)) {

			reportStep("Successfully clicked on the JoinFree back button  ", "Pass");

		}else {

			reportStep("Failed to  click on the JoinFree back button ", "FAIL");

		}

		return new EKOnboardingPage(driver, testInfo);
	}

	public  JoinFreePage validateReferralByUserAtJoinFree() {

		reportStep("Validate the referral user name at the joinfree page ", "INFO");

		validateTheElementPresence(referralBy);

		return this;
	}

	public  JoinFreePage validateReferralByUserAtJoinFree(String userName) {

		reportStep("Validate the referral user name at the joinfree page ", "INFO");

		MobileElement referralBy = driver.findElement(By.xpath("//android.widget.TextView[contains(@text,'Invited by "+userName+"')]"));

		validateTheElementPresence(referralBy);

		return this;
	}

	public JoinFreePage validateJoinFreePage(){

		reportStep("About to validate the JoinFree page ", "INFO");

		validateTheElementPresence(joinEarnKaroFreeText);
		validateTheElementPresence(join20KReSellersText);
		
		//Scroll Down :
		scrollFromDownToUpinApp();
		validateTheElementPresence(alreadyRegisteredText);
		validateTheElementPresence(signInLink);
		validateTheElementPresence(termsConitionText);
		validateTheElementPresence(termsConitionLink);
		validateTheElementPresence(orText);
		clickOnJoinFreeBackButton();
		
		return this;

	}
	
	public JoinFreePage validateGoogleSuggestionInJoinFree() {
		
		reportStep("About to validate the Google suggestion Popup in JoinFree page ", "INFO");
		
		if (clickAfterWait(emailAddress)){
			
			reportStep("Successfully clicked on the element to Handle the Google Email or Mobile number suggesstion popup", "PASS");
		}else {
			
			reportStep("Failed to click on the web element where the Google suggestion popup appears", "INFO");
			
		}
		
		validateTheElementPresence(gSuggestion_signInWithText);
		validateTheElementPresence(gSuggestion_Name);
		validateTheElementPresence(gSuggestion_Gmail);
		
		reportStep("About to get the Gmail Id configured with the system ", "INFO");
		
		String gSuggestionEMail = getText(gSuggestion_Gmail);
		
		gSuggestion_Gmail = driver.findElement(By.xpath("//android.widget.TextView[@text='"+gSuggestionEMail+"']"));
		
		if (click(gSuggestion_Gmail)) {
			
			reportStep("Successfully clicked on Google suggestions -  Mail ", "PASS");
		}else {
			
			reportStep("Failed to click on the Google Suggestion - Mail ", "FAIL");
		}
		
		isElementLocatedByXpathPresent("//android.widget.EditText[@text='"+ gSuggestionEMail+ "']");
		
		return this;
		
	}

	public SignInPage clickOnSignIn() {

		reportStep("About to click on SignIn link in the JoinFree Page ", "INFO");

		scrollFromDownToUpinApp();

		if (click(signInLink)) {

			reportStep("Successfully clicked on the SignIn link  ", "PASS");

		}else {

			reportStep("Failed to click on the SignIn link in the JoinFree page ", "FAIL");
		}

		return new SignInPage(driver, testInfo);
	}

	public TermsAndConditionPage clickOnTermsAndConditions() {

		reportStep("About to click on Terms and Condition in JoinFree Page", "INFO");

		scrollFromDownToUpinApp();

		if (click(termsConitionLink)) {

			reportStep("Successfully clicked on Terms and Conditions in Join free page ", "PASS");

		}else {

			reportStep("Failed to click on the Terms and Condition link ", "FAIL");
		}

		return new TermsAndConditionPage(driver, testInfo);
	}

}
	

	

