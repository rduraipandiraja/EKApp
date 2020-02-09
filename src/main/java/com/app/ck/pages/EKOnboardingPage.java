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

public class EKOnboardingPage extends WrapperMethods {
	
	public EKOnboardingPage(AppiumDriver<MobileElement> driver, ExtentTest testInfo) {

		this.driver = driver;
		this.testInfo = testInfo;
		
		 PageFactory.initElements(new AppiumFieldDecorator(driver), this);

		reportStep("Waiting for the OnBoarding Screen..!!", "INFO");
		
		WebDriverWait wait = new WebDriverWait(driver, 15);
		
		try {
			wait.until(ExpectedConditions.visibilityOf(signUp));
			
			reportStep("Successfully landed on the EKOnBoarding page", "PASS");

		} catch (Exception e) {
			
			reportStep("Not able to land on the EKOnBoarding  page  ", "FAIL");
		}
	}

	public EKOnboardingPage(AppiumDriver<MobileElement> driver, ExtentTest testInfo,int popUpCounter) {
		
		this.driver = driver;
		this.testInfo = testInfo;
		
		System.out.println(" Login page counter " + popUpCounter);
		
		reportStep("Second time onwards this constructor is being called to avoid Waiting for permission Popup  ", "INFO");
		reportStep(popUpCounter + " th time Entering into the OnBoarding Screen  ", "INFO");
		
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
		
		reportStep("Waiting for the OnBoarding Screen..!!", "INFO");
		
		WebDriverWait wait = new WebDriverWait(driver, 15);
		
		try {
			
			wait.until(ExpectedConditions.visibilityOf(signUp));
			reportStep("Successfully landed to OnBoarding Screen", "PASS");

		} catch (Exception e) {
			
			reportStep("Not able to land on to the OnBoarding Screen", "FAIL");

		}
	}

	// OnBoarding Screen Elements
	@FindBy(how = How.XPATH, using = "//android.view.ViewGroup[@content-desc='txt_join']")
	MobileElement signUp;
	@FindBy(how = How.XPATH, using = "//android.view.ViewGroup[@content-desc='txt_login']")
	MobileElement signIn; 
	@FindBy(how = How.ID, using = "com.android.packageinstaller:id/permission_allow_button")
	MobileElement allowLinkInPermissionPopup;
	@FindBy(how = How.XPATH, using = "//android.view.ViewGroup[@content-desc='btn_intro_hindi']/android.widget.TextView")
	MobileElement buttonHindi;
	@FindBy(how = How.XPATH, using = "//android.view.ViewGroup[@content-desc='btn_intro_malayalam']/android.widget.TextView")
	MobileElement buttonMalayalam;
	@FindBy(how = How.XPATH, using = "//android.view.ViewGroup[@content-desc='btn_intro_tamil']/android.widget.TextView")
	MobileElement buttonTamil;
	@FindBy(how = How.XPATH, using = "//android.view.ViewGroup[@content-desc='btn_intro_bengali']/android.widget.TextView")
	MobileElement buttonBengali;
	@FindBy(how = How.XPATH, using = "//android.view.ViewGroup[@content-desc='txt_makeProfit_closemodal']")
	MobileElement buttonClose;
		
	//Method creation :

	public JoinFreePage clickOnSignUpButton() {

		reportStep("About click on the Sign Up link at OnBoarding Screen ", "INFO");

		if (clickAfterWait(signUp)) {

			reportStep("Clicked on the SignUp button" + signUp + " successfully ", "PASS");
		}else {
			
			reportStep("Fail to click on the SignUp button" + signUp + "  ", "FAIL");
			
		}
		
		return new JoinFreePage(driver, testInfo);
	}
	
	public JoinFreePage clickOnSignUpButton_ForSecondTime() {

		reportStep("About click on the signUp button at the Onboarding Page", "INFO");

		if (click(signUp)) {

			reportStep("Clicked on the signUp button" + signUp + " successfully ", "PASS");
		}

		return new JoinFreePage(driver, testInfo,0);
	}

	public EKOnboardingPage allowThePermissionPopup() {

		System.out.println("About to click on the Allow on the permission popup in OnBoarding Screen ");

		try {

			if (clickAfterWait(allowLinkInPermissionPopup)){

				reportStep("Clicked on the Allow link " + allowLinkInPermissionPopup + " on the permission popup at  OnBoarding Screen", "INFO");

			} else {

				reportStep("Not able to click on the Allow link on the permission popup at the OnBoarding Screen", "INFO");
			}

		}catch (Exception ee) {

			reportStep(ee.getMessage(), "INFO");

		}


	return this;

	}

	public SignInPage clickOnSignInbutton() {

		reportStep("About to click on Login link in the OnBoarding Screen", "INFO");

		if(click(signIn)) {

			reportStep("Successfully clicked on the signIn from the OnBoarding Screen ", "PASS");

		}else {

			reportStep("Failed to click on the signIn from the OnBoarding Screen", "FaIL");
		}
				
		return new SignInPage(driver, testInfo);

	}
	
	public SignInPage clickOnSignInbutton_ForSecondTime() {

		reportStep("About to click on signIn link in the OnBoarding Screen ", "INFO");

		if(click(signIn)) {

			reportStep("Successfully clicked on the signIn from the OnBoarding Screen ", "PASS");

		}else {

			reportStep("Failed to click on the Login from the OnBoarding Screen ", "FaIL");
		}
				
		return new SignInPage(driver, testInfo,0);

	}

	public EKOnboardingPage clickOnHindibutton() {

		reportStep("About to click on Hindibutton", "INFO");

		if(clickAfterWait(buttonHindi)) {

			reportStep("Successfully clicked on Hindibutton", "PASS");

		}else {

			reportStep("Failed to click on Hindibutton", "FaIL");
		}

		if(waitForElementPresence(buttonClose)) {

			reportStep("Successfully validated close button", "PASS");

		}else {

			reportStep("Failed to validate close button", "FaIL");
		}	
		
		return this;

	}
	
	public EKOnboardingPage clickOnMalayalambutton() {

		reportStep("About to click on Malayalambutton", "INFO");

		if(clickAfterWait(buttonMalayalam)) {

			reportStep("Successfully clicked on Malayalambutton", "PASS");

		}else {

			reportStep("Failed to click on Malayalambutton", "FaIL");
		}

		if(waitForElementPresence(buttonClose)) {

			reportStep("Successfully validated close button", "PASS");

		}else {

			reportStep("Failed to validate close button", "FaIL");
		}
		
		return this;	

	}

	public EKOnboardingPage clickOnTamilbutton() {

		reportStep("About to click on Tamilbutton", "INFO");

		if(clickAfterWait(buttonTamil)) {

			reportStep("Successfully clicked on Tamilbutton", "PASS");

		}else {

			reportStep("Failed to click on Tamilbutton", "FaIL");
		}

		if(waitForElementPresence(buttonClose)) {

			reportStep("Successfully validated close button", "PASS");

		}else {

			reportStep("Failed to validate close button", "FaIL");
		}	
		
		return this;

	}

	public EKOnboardingPage clickOnBengalibutton() {

		reportStep("About to click on Bengalibutton", "INFO");

		if(clickAfterWait(buttonBengali)) {

			reportStep("Successfully clicked on Bengalibutton", "PASS");

		}else {

			reportStep("Failed to click on Bengalibutton", "FaIL");
		}

		if(waitForElementPresence(buttonClose)) {

			reportStep("Successfully validated close button", "PASS");

		}else {

			reportStep("Failed to validate close button", "FaIL");
		}	
		
		return this;

	}

	public EKOnboardingPage clickOnClosebutton() {

		reportStep("About to click on buttonClose", "INFO");

		if(clickAfterWait(buttonClose)) {

			reportStep("Successfully clicked on buttonClose", "PASS");

		}else {

			reportStep("Failed to click on buttonClose", "FaIL");
		}
		
		return this;

	}
	
}
