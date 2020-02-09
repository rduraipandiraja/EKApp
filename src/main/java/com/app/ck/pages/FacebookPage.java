package com.app.ck.pages;

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

public class FacebookPage extends WrapperMethods {
	
	
	//Constructor call to initialize the driver object
	public FacebookPage(AppiumDriver<MobileElement> driver, ExtentTest testInfo) {
	
		WebDriverWait wait = new WebDriverWait(driver, 30);
		
		this.driver = driver;
		this.testInfo = testInfo;
	
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
		
		reportStep("Waiting for the Facebook page", "INFO");
		try {
		wait.until(ExpectedConditions.visibilityOf(facebookEmail));
		reportStep("Successfully landed on the Facebook page", "PASS");
		
	}catch(Exception e) {
		
		reportStep(e.getMessage(), "INFO");
		reportStep("Not able to land on the Facebook page", "FAIL");
	}
		
	}

	//variables initialization

	@FindBy(how = How.XPATH, using = "//android.widget.Button[contains(@text,'Log In')]")
	MobileElement facebookLoginButton;
	
	@FindBy(how = How.XPATH, using = "(//android.widget.EditText)[1]")
	MobileElement facebookEmail;
	
	@FindBy(how = How.XPATH, using = "(//android.widget.EditText)[2]")
	MobileElement facebookPassword;
	
	@FindBy(how = How.XPATH, using = "//android.widget.Button[contains(@text,'Continue')]")
	MobileElement facebookIntermediatePageContinueButton;
	
	@FindBy(how = How.XPATH, using = "//android.widget.Button[contains(@text,'Post')]")
	MobileElement facebookSharePost;
	
	//Method creation:
	
	public FacebookPage enterFBUserEmail() {

		reportStep("About to enter the valid userEmail to the Facebook login ", "INFO");

		if(enterTextUsingSendKeys(facebookEmail, getTestData(1, "TC001_ValidFBEmail"))) {

			reportStep("Entered the Facebook email :"+ getTestData(1, "TC001_ValidFBEmail")+" Successfuly" , "PASS");
		}else {

			reportStep("Failed to Entered the Facebook email :"+ getTestData(1, "TC001_ValidFBEmail") , "FAIL");
		}

		return this;

	}
	
	public FacebookPage enterFBNewUserEmail() {

		reportStep("About to enter the valid New userEmail to the Facebook login ", "INFO");

		if(enterText(facebookEmail, getTestData(1, "FBTestUserEmail2"))) {
		
	//	if(enterText(facebookEmail,this.fbUserEmail)) {

			reportStep("Entered the Facebook email :"+ getTestData(1, "FBTestUserEmail2")+" Successfuly" , "PASS");
		}else {

			reportStep("Failed to Entered the New Facebook user email :"+ getTestData(1, "FBTestUserEmail2") , "FAIL");
		}

		return this;

	}

	public FacebookPage enterFBUserEmail(String email) {

		reportStep("About to enter the valid userEmail to the Facebook login ", "INFO");

		if(enterTextUsingSendKeys(facebookEmail, email)) {

			reportStep("Entered the Facebook email :"+ email +" Successfuly" , "PASS");
		}else {

			reportStep("Failed to Entered the Facebook email :"+ email , "FAIL");
		}

		return this;

	}

	public FacebookPage enterFBPassword() {

		reportStep("About to enter the valid Password to the Facebook login ", "INFO");

		if(enterText(facebookPassword, getTestData(1, "TC001_ValidFBPassword"))) {

			reportStep("Entered the Facebook Password :"+ getTestData(1, "TC001_ValidFBPassword")+" Successfuly" , "PASS");
		}else {

			reportStep("Failed to Entered the Facebook Password :"+ getTestData(1, "TC001_ValidFBPassword") , "FAIL");
		}

		return this;

	}
	
	public FacebookPage enterNewFBUserPassword() {

		reportStep("About to enter the new valid facebook Password to the Facebook login ", "INFO");

		if(enterText(facebookPassword, getTestData(1, "FBTestUserPwd2"))) {

			reportStep("Entered the Facebook Password :"+ getTestData(1, "FBTestUserPwd2")+" Successfuly" , "PASS");
		}else {

			reportStep("Failed to Entered the new Facebook Password :"+ getTestData(1, "FBTestUserPwd2") , "FAIL");
		}

		return this;

	}

	public FacebookPage enterFBPassword(String password) {

		reportStep("About to enter the valid Password to the Facebook login ", "INFO");

		if(enterText(facebookPassword, password)) {

			reportStep("Entered the Facebook Password :"+ password +" Successfuly" , "PASS");
		}else {

			reportStep("Failed to Entered the Facebook Password :"+ password , "FAIL");
		}

		return this;

	}

	public FacebookPage clickOnFBLoginButton() {

		reportStep("About to click on the Facebook login button ", "INFO");

		if(clickAfterWait(facebookLoginButton)) {
			
			reportStep("Clicked on the Facebook login button successfully", "PASS");


		}else {
			reportStep("Failed to Click on the Facebook login button", "FAIL");
		}

		return this;

	}
	
	public HomePage clickOnContinue() {

		reportStep("About to click on the continue button at the Facebbook intermediate page ", "INFO");

		if(click(facebookIntermediatePageContinueButton)) {

			reportStep("Successfully clicked on the Continue button ", "PASS");
		}else {

			reportStep("Failed to click on the Continue button ", "FAIL");
		}


		return new HomePage(driver, testInfo);

	}
	
	public void clickOnContinue_DefferedDL() {

		reportStep("About to click on the continue button at the Facebbook intermediate page ", "INFO");

		if(click(facebookIntermediatePageContinueButton)) {

			reportStep("Successfully clicked on the Continue button ", "PASS");
		}else {

			reportStep("Failed to click on the Continue button ", "FAIL");
		}


	}
	
	public FBSignUpIntermediatePage clickOnContinueForNewUser() {

		reportStep("About to click on the continue button at the Facebbook intermediate page ", "INFO");

		if(click(facebookIntermediatePageContinueButton)) {

			reportStep("Successfully clicked on the Continue button ", "PASS");
		}else {

			reportStep("Failed to click on the Continue button ", "FAIL");
		}


		return new FBSignUpIntermediatePage(driver, testInfo);

	}
	//This method used in Refer and Earn Facebook share
	public ReferAndEarnLifeTime clickOnFbPost() {
		
		reportStep("About to click on the Fb share Post in the FB Share Page ", "INFO");
		
		if(clickAfterWait(facebookSharePost)) {
			
			reportStep("Successfully clicked on the facebook Share Post ", "PASS");
		} else {
			
			reportStep("Failed to click on the facebook Shared Post ", "Fail");
		}
		
		return new ReferAndEarnLifeTime(driver, testInfo);
	}
	
	
}





