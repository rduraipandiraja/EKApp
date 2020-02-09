package com.app.ck.test;

import static io.appium.java_client.touch.offset.PointOption.point;

import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.app.ck.base.Base;
import com.app.ck.base.WrapperMethods;
import com.app.ck.pages.EKOnboardingPage;
import com.app.ck.pages.HomePage;
import com.app.ck.pages.JoinFreePage;
import com.app.ck.pages.OTPPage;
import com.app.ck.pages.StorePage;
import com.app.ck.pages.admin.AdminCoreFunction;
import com.app.ck.pages.admin.AdminUsersPage;
import com.app.ck.utilities.CashKaroUtility;
import com.app.ck.utilities.Utilities;
import com.aventstack.extentreports.Status;
import io.appium.java_client.MobileElement;
import io.appium.java_client.MultiTouchAction;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.PressesKey;
import io.appium.java_client.touch.WaitOptions;


public class EKTestLogin extends WrapperMethods {

	/*******************************************************************/
	  /*******************   LOGIN   ***********************/
	/*******************************************************************/

	// Valid Login from GetStarted Page
	@Test
	public void validGetStartedPageLogin_TC001() {

		// REPORT STEP - START
		reportStep("LOGIN FROM THE GET STARTED PAGE : STARTED", "INFO");

		// TEST STEPS :
		new EKOnboardingPage(driver, testInfo).
		clickOnSignInbutton().
		enterUserName().
		enterPassword().
		clickSignInButtonForSuccess();

		// REPORT STEP - END
		reportStep("validGetStartedPageLogin_TC001 - COMPLETED", "INFO");

	}
	
	// FB LOGIN :
	@Test
	public void validateGetStartedPageFacebookLogin_TC003() {

		// REPORT STEP - START
		reportStep("validateGetStartedPageFacebookLogin_TC003 : STARTED", "INFO");

		new EKOnboardingPage(driver, testInfo).
		clickOnSignInbutton().
		clickOnSignInWithFacebook().
		enterFBUserEmail().
		enterFBPassword().
		clickOnFBLoginButton().
		clickOnContinue();

		reportStep("validateGetStartedPageFacebookLogin_TC003 : END", "INFO");

	}
	//Login Negative validation ;
	@Test
	public void validatingTheErrorMessageOnUserNameField_And_Password_TC002() {

		testInfo.log(Status.INFO, "Validating the error message on username and password -");

		new EKOnboardingPage(driver, testInfo).
		clickOnSignInbutton().
		clickOnSignInWithEmailForFailure().
		validateUserNameFieldErrorMessage().
		validatePasswordFieldErrorMessage().
		clickOnBackButton_SecondTimeNavigationToTheGetStartedPage().
		clickOnSignInbutton().enterInvalidUserName().
		enterInvalidPassword().
		clickOnSignInWithEmailForFailure().
		validateAuthenticationFailedErrorMessage().
		clickOnBackButton_SecondTimeNavigationToTheGetStartedPage().
		clickOnSignInbutton().
		enterUserName().
		enterPasswordwithAsNull().
		clickOnSignInWithEmailForFailure().
		validatePasswordFieldErrorMessage().
		clickOnBackButton_SecondTimeNavigationToTheGetStartedPage().
		clickOnSignInbutton().
		enterPassword().
		clickOnSignInWithEmailForFailure().
		validateUserNameFieldErrorMessage().
		clickOnBackButton_SecondTimeNavigationToTheGetStartedPage().
		clickOnSignInbutton().
		enterUserName().
		enterLessNumberOfCharacterToThePassword().
		clickOnSignInWithEmailForFailure().
		validatePasswordFieldLessNumberOfCharachetersError().
		clickOnBackButton_SecondTimeNavigationToTheGetStartedPage().
		clickOnSignInbutton().
		enterNumericIntoUserNameField().
		clickOnSignInWithEmailForFailure().
		validateUserNameFieldErrorMessage();
//		clickOnBackButton().
//		ClickOnGetStartedButton().
//		enterExcessBoundaryValueToUsernameField().
//		enterExcessBoundaryValueToPasswordField().
//		clickOnSignInWithEmailForInvalidScenario().
//		validateExcessBoundaryValueErrorMessage();
//

	}

	/*******************************************************************/
	/********              		ENHANCEMENT    		     	***********/
	/*******************************************************************/
	
	// Valid Login from GetStarted Page
	@Test
	public void validateLoginPageTexts_TC004() {

		// REPORT STEP - START
		reportStep("Validate Login Page Texts : ", "INFO");

		// TEST STEPS :
		new EKOnboardingPage(driver, testInfo).
		clickOnSignInbutton(). 
		validateSignInPage();

		// REPORT STEP - END
		reportStep("Validate Login Page Texts : COMPLETED", "INFO");

	}
	
	// Validate Google suggestion Popup
	@Test
	public void validatGoogleSuggestionInLoginPage_TC006() {

		// REPORT STEP - START
		reportStep("Google suggestion in login Page is started ", "INFO");

		// TEST STEPS :
		new EKOnboardingPage(driver, testInfo).
		clickOnSignInbutton().
		validateGoogleSuggestionInSignInPage().
		enterPassword().
		clickSignInButtonForFailure().
		validateAuthenticationFailedErrorMessage();

		// REPORT STEP - END
		reportStep("Google suggestion Joinfree page is completed", "INFO");

	}
	
	@Test
	public void DeactivatingTheUserInAdmin_TC007() {
		
		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo);
		String emailAddress = objCashKaroUtility.generateEmail();
		String NewEmailAddress = objCashKaroUtility.generateEmail();
		String mobileNumber = objCashKaroUtility.generateMobileNumber();
		
		reportStep("TC001 is  started", "INFO");
		String OtpValue =
		new EKOnboardingPage(driver, testInfo).
		clickOnSignUpButton().
		enterFullName().
		enterEmail(emailAddress).
		enterPassword().
		enterMobileNumber(mobileNumber).
		clickOnGetOTPButton(mobileNumber);

		// creating the new object for the OTP page
		OTPPage objOtpPage = new OTPPage(driver, testInfo);

		objOtpPage.
		enterOTP(OtpValue).
		clickOnVerifyOTP();

		// Once after clicking on the ClickOn verify OTP , it should navigate to the
		// Home page
		new HomePage(driver, testInfo).
		clickOnProfileIconForSignedUser().
		clickOnLogout().
		clickOnSignInbutton_ForSecondTime().
		enterUserName(emailAddress).
		enterPassword().
		clickSignInButtonForSuccess().
		clickOnProfileIconForSignedUser().
		clickOnLogout();
		
		
		reportStep("TC001 is completed", "INFO");
		//Navigate to the Admin and deactivating the User 
		CashKaroUtility cu = new CashKaroUtility(udid, port, automationName, deviceName);
		driver = cu.launchChromeWebView(driver);
		
		AdminCoreFunction adminFunctions = new AdminCoreFunction(driver, testInfo);
		adminFunctions.naviagateAdminPage_ThenSelectEarnKaro();
		adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
		adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
		adminFunctions.clickSubmit();

		AdminUsersPage usersPage = new AdminUsersPage(driver, testInfo);
		adminFunctions.clickOnHamburgerMenu();
		adminFunctions.clickUsersMenu();
		adminFunctions.clickUsersSubMenu();
		usersPage.selectSearchByDropDown("User Email");
		usersPage.enterKeyword(emailAddress);
		usersPage.clickSubmitButton();
		usersPage.clickEditButton();
		usersPage.enterEmail(NewEmailAddress);
		usersPage.selectStatusFromDropDown("In-Active");
		usersPage.clickSubmitButtonUserForm();
		usersPage.validateUserEditSuccessMessage();
		
		CashKaroUtility reopenCKApp1 = new CashKaroUtility(udid, port, automationName, deviceName, systemPort);
		driver = reopenCKApp1.launchEarnKaroApp(driver);
		
		new EKOnboardingPage(driver, testInfo).
		clickOnSignInbutton().
		enterUserName(emailAddress).
		enterPassword().
		clickSignInButtonForFailure().
		validateAuthenticationFailedErrorMessage();

	}
	
	@Test
	public void LoginFromJoinFreePage_TC008() {
		
		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo);
		String mobileNumber = objCashKaroUtility.generateMobileNumber();

		reportStep("About to validate the JoinFree Texts ", "INFO");
		
		new EKOnboardingPage(driver, testInfo).
		clickOnSignUpButton().
		clickOnSignIn().
		enterUserName().
		enterPassword().
		clickSignInButtonForSuccess();
		
		reportStep("Validate JoinFree Texts ", "INFO");

	}
	
	@Test
	public void validateTermAndConditionFromJoinFreePage_TC009() {
		
		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo);
		String mobileNumber = objCashKaroUtility.generateMobileNumber();

		reportStep("About to validate the JoinFree Texts ", "INFO");
		
		new EKOnboardingPage(driver, testInfo).
		clickOnSignUpButton().
		clickOnTermsAndConditions();
		
		reportStep("Validate JoinFree Texts ", "INFO");

	}

	
	
}
