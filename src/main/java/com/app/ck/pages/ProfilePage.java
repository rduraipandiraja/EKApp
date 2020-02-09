package com.app.ck.pages;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.app.ck.base.Base;
import com.app.ck.base.WrapperMethods;
import com.aventstack.extentreports.ExtentTest;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class ProfilePage extends WrapperMethods {

	//Constructor call
	public ProfilePage(AppiumDriver<MobileElement> driver, ExtentTest testInfo) {

		WebDriverWait wait = new WebDriverWait(driver, 30);

		this.driver = driver;
		this.testInfo = testInfo;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);

		reportStep("Waiting for the ProfilePage", "INFO");
		try {
			wait.until(ExpectedConditions.visibilityOf(joinFreeButton));
			reportStep("Successfully landed on the ProfilePage page", "PASS");

		}catch(Exception e) {

			reportStep(e.getMessage(), "INFO");
			reportStep("Not able to land on the ProfilePage page", "FAIL");
		}

	}

	//Variable declaration
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='JOIN FREE']") 
	MobileElement joinFreeButton;

	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='SIGN IN']") 
	MobileElement signInButton;
	
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='Logout']") 
	MobileElement logout;
	
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@content-desc='icon_profileMenu_withLogin_myReferral']") 
	MobileElement referralNetwork;

	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='Contact Us']") 
	MobileElement contactUsLink;

	//Method reusability
	public SignInPage clickOnSignInButton() {

		reportStep("About to login from the profile page", "INFO");

		if(click(signInButton)) {

			reportStep("Successfully clicked on the SignIN button at the profile page","PASS");

		}else {

			reportStep("Failed to click on the SignIN button at the profile page","FAIL");
		}

		return new SignInPage(driver, testInfo);
	}

	public JoinFreePage clickOnJoinFreeButton() {

		reportStep("About to SignUp from the Profile page", "INFO");

		if(click(joinFreeButton)) {

			reportStep("Successfully clicked on the JoinFree button at the profile page","PASS");

		}else {

			reportStep("Failed to click on the JoinFree button at the profile page","FAIL");
		}

		return new JoinFreePage(driver, testInfo);
	}

	public void clickOnLogout() {

		reportStep("About to logout the user ", "INFO");
		
		scrollFromDownToUpinApp();
		scrollFromDownToUpinApp();
		
		if(click(logout)) {
			
			reportStep("Sucessfully clicked on the Logout button ", "PASS");
			
		}else {
			
			reportStep("Not able to click on the Logout button ", "FAIL");
		}
		
	
		
	}

	public AskAQuestionPage clickContactUsLink() {

		reportStep("About to click contact us link in profile page", "INFO");
		
		if(click(contactUsLink)) {
			
			reportStep("Sucessfully clicked on the contact us link in profile page", "PASS");
			
		}else {
			
			reportStep("Not able to click on the contact us link in profile page", "FAIL");
		}

		return new AskAQuestionPage(driver, testInfo);
		
	
		
	}

}
