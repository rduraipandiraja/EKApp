package com.app.ck.pages;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.app.ck.base.WrapperMethods;
import com.app.ck.utilities.CashKaroUtility;
import com.aventstack.extentreports.ExtentTest;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class ForgotPasswordPage extends WrapperMethods {

	
	//Hi Mallik - Test method is created Please use it when ever you work on Forgot pwd

	//Constructor call to initialize the driver object
	public ForgotPasswordPage(AppiumDriver<MobileElement> driver, ExtentTest testInfo) {

		WebDriverWait wait = new WebDriverWait(driver, 30);

		this.driver = driver;
		this.testInfo = testInfo;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);

		reportStep("Waiting for the ForgotPassword page", "INFO");
		try {
			wait.until(ExpectedConditions.visibilityOf(forgotPasswordPageTitle));

			reportStep("Successfully landed on the ForgotPassword page", "PASS");

		}catch(Exception e) {

			reportStep(e.getMessage(), "INFO");
			reportStep("Not able to land on the Facebook page", "FAIL");
		}

	}

	//Forgot Password filed objects
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[contains(@text,'Forgot Password')]")
	MobileElement forgotPasswordPageTitle;
	@FindBy(how = How.XPATH, using = "//*[@text='Forgot Password ?']")
	MobileElement forgotPwdWithQuestionMark;
	@FindBy(how = How.XPATH, using = "//*[@text='Enter Registered Email ID to reset your Password']")
	MobileElement enterYourEmail;
	@FindBy(how = How.XPATH, using = "//*[@text='Tell us the Email id you used to create your EarnKaro A/c, and we will send you the Password Reset link in return.']")
	MobileElement tellUsTheEmail_Text;
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[contains(@text,'SUBMIT REQUEST')]")
	MobileElement submitRequestButton;
	@FindBy(how = How.XPATH, using = "//*[@text='Remember your password? LOGIN']")
	MobileElement rememberYourPwdLoginText;
	@FindBy(how = How.XPATH, using = "//android.widget.EditText[@content-desc='et_forgotpassword_email']")
	MobileElement enterEmailField;

	//Error messsage :
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[contains(@text,'Enter your Email ID')]")
	MobileElement enterYourEmailID_Error;
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[contains(@text,'Please enter the Email')]")
	MobileElement pleaseEnterTheEmail;
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[contains(@text,'Please enter minimum 2 characters long')]")
	MobileElement pleaseEnterMin2CharsLong;
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[contains(@text,'The email must be a valid email address')]") 
	MobileElement theEmailMustbe_Error;
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[contains(@text,'Please enter a valid Email ID')]")
	MobileElement pleaseEnterValidEmail;

	
	//Success Message :
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[contains(@text,'New Password has been sent your Email')]")
	MobileElement forgotPasswordSuccessMessage;
	
	//Forgot Password functions : 

	public ForgotPasswordPage enterYourEmail(String email) {
		
		reportStep("About to enter the email as " +email +" id to get the new password in forgot password field : "+ email , "INFO");
		
		if (enterText(enterEmailField, email)) {
			
			reportStep("Successfully entered the "+ email + " to the Forgor password page email ", "PASS");
			
		}else {
			
			reportStep("Failed to enter the email as  " + email + " at the Forgot password page ", "FAIL");
		}
		
		return this;
		
	}

	public ForgotPasswordPage clickOnSubmitRequestButton() {
		
		reportStep("About to click on submit Request button at the Forgot password page ", "INFO");
		
		if (click(submitRequestButton)) {
			
			reportStep("Successfully clicked on the submit Request button at the Forgot password page ", "PASS");
		}else {
			
			reportStep("Successfully clicked on the submit Request button at the Forgot password page ", "FAIL");
		}
		
		return this ; 
		
	}
	
	public ForgotPasswordPage validatePleaseEnterValidEmailerror() {
		
		reportStep("About to validate the Please enter valid email error message ", "INFO");
		
		validateTheElementPresence(pleaseEnterValidEmail);
		validateTheElementPresence(enterYourEmailID_Error);
		
		return this;
		
	}
	
	public ForgotPasswordPage validateMin2CharsLongError() {
		
		reportStep("About to validate the error message which is - Please enter minimum 2 characters long ", "INFO");
		
		validateTheElementPresence(pleaseEnterMin2CharsLong);
		
		return this;
	}

	public ForgotPasswordPage validateSorryThereIsNoActiveUserError(String email) {
		
		reportStep("About to validate the Sorry, there is no active account which match with this e-mail address: "+ email, "INFO");
		
		String xpath = "//android.widget.TextView[contains(@text,'Sorry, there is no active account which match with this e-mail address: "+email+"')]";
		
		if(isElementLocatedByXpathPresent(xpath)) {
			
			reportStep("Success validted the - Sorry, there is no active account which match with this e-mail address: message", "PASS");
			
		}else {
			
			reportStep("Failed to locate the requiered error message as : Sorry, there is no active account which match with this e-mail address: "  , "FAIL");
		}
		
		
		return this;
	}

	public ForgotPasswordPage validateEmailMustBeAValidEmail() {
		
		reportStep("About to validate the error message - The email must be a valid email ", "INFO");
		
		validateTheElementPresence(theEmailMustbe_Error);
		
		return this;
		
	}
	
	public ForgotPasswordPage validatePleaseEnterTheEmail() {
		
		reportStep("About to validate the Please enter email error message   ", "INFO");
		
		validateTheElementPresence(pleaseEnterTheEmail);
		
		return this;
		
		
	}

	public ForgotPasswordPage validateTheForgotPasswordSuccessMessage() {
		
		
		reportStep("About to validate the Forgot password Success message as  : New Password has been sent your Email ", "INFO");
		
		validateTheElementPresence(forgotPasswordSuccessMessage);
		
		return this;
		
	}

	public ForgotPasswordPage validateAllobjects() {
		
		validateTheElementPresence(forgotPasswordPageTitle);
		validateTheElementPresence(forgotPwdWithQuestionMark);
		validateTheElementPresence(enterYourEmail);
		validateTheElementPresence(submitRequestButton);
		validateTheElementPresence(tellUsTheEmail_Text);
		validateTheElementPresence(submitRequestButton);
		validateTheElementPresence(rememberYourPwdLoginText);
		return this;
		
	}
	
	public SignInPage clickOnLogin() {
		
		String xpath = "//android.widget.TextView[contains(@text,'Remember your password? LOGIN')]";
		String expectedXpath = "//android.widget.EditText[@content-desc='et_signin_email']";
		
		clickMultipleXCoordinatesByXpath(xpath, 25, expectedXpath);
		
		return new SignInPage(driver, testInfo);
		
		
	}

	public SignInPage clickOnBackButton() {

		reportStep("About click on the back button at the Sign in page", "INFO");

//		if(click(backButton)) {
//
//			reportStep("Successfully clicked on the back button at the SignIn Page", "INFO");
//		}else {
//
//			reportStep("Not clicked on the back button at the SignIn page", "FAIL");
//		}
		
		driver.navigate().back();

		return new SignInPage(driver, testInfo);
	}
	

}
