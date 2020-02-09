package com.app.ck.test;

import org.testng.annotations.Test;

import com.app.ck.base.GetCodeSetUP;
import com.app.ck.base.WrapperMethods;
import com.app.ck.pages.OTPPage;
import com.app.ck.pages.SignInPage;
import com.app.ck.pages.StoreDetailPage;
import com.app.ck.utilities.CashKaroUtility;

public class EKDefferedDeepLink extends WrapperMethods {
	

	/*********************************************************************/
	/*********************************************************************/

	@Test
	public void deferredDLFromStorePage_NormalSignUp() {

		reportStep("Deferred Deep linking Validation and Normal SignUp then validate the Retailer Navigationn", "INFO");
		
		CashKaroUtility cu = new CashKaroUtility(driver, testInfo, udid, port, automationName, deviceName, systemPort);

		cu.removeApp(driver,"com.earnkaro");

		driver = cu.launchChrome();
		
		cu.closeAllTabsAndOneTabInChromeBrowser(driver);
		cu.clickAndEnterURLInSearchOrTypeWebAddress(driver, GetCodeSetUP.ONELINK);
		cu.clickOneLink(driver);	
		cu.playStoreRedirectionVerification(driver);

		driver = cu.launchEarnKaroApp((System.getProperty("user.dir") + "/apk/ekapp.apk"));
		
		cu.allowLinkInPermissionPopup(driver);
		cu.allowLinkInPermissionPopup(driver);

		//Once after signing up, it should navigate to deferred deep link Page (Amazon)
		String otpValue = new SignInPage(driver, testInfo).
		clickOnJoinFreeLink().
		enterAllJoinFreeFieldsAndVerifyOTP();

		new OTPPage(driver, testInfo).
		enterOTP(otpValue).
		clickOnVerifyOTP();
		
		new StoreDetailPage(driver, testInfo).
		validateDeepLinkRedirection();
		
		reportStep("Deferred Deep linking Validation and Normal SignUp then validate the Retailer Navigation - Completed :)", "INFO");

	}

	@Test
	public void deferredDLFromStorePageNormalSignIn() {

		reportStep("Deferred Deep linking Validation and Normal SignUp then validate the Retailer Navigationn", "INFO");
		
		CashKaroUtility cu = new CashKaroUtility(driver, testInfo, udid, port, automationName, deviceName, systemPort);

		cu.removeApp(driver,"com.earnkaro");

		driver = cu.launchChrome();
		
		cu.closeAllTabsAndOneTabInChromeBrowser(driver);
		cu.clickAndEnterURLInSearchOrTypeWebAddress(driver, GetCodeSetUP.ONELINK);
		cu.clickOneLink(driver);	
		cu.playStoreRedirectionVerification(driver);

		driver = cu.launchEarnKaroApp((System.getProperty("user.dir") + "/apk/ekapp.apk"));
		
		cu.allowLinkInPermissionPopup(driver);
		cu.allowLinkInPermissionPopup(driver);

		//Once after signing up, it should navigate to deferred deep link Page (Amazon)
		new SignInPage(driver, testInfo).
				enterUserName().
				enterPassword().
				clickSignInButtonForSuccess_RedirectTo_Deeplink();
		
		new StoreDetailPage(driver, testInfo).
		validateDeepLinkRedirection();
		
		reportStep("Deferred Deep linking Validation and Normal SignUp then validate the Retailer Navigation - Completed :)", "INFO");

	}
	
	@Test
	public void deferredDLFromStorePageFacebookSignIn() {

		reportStep("Deferred Deep linking Validation and Normal SignUp then validate the Retailer Navigationn", "INFO");
		
		CashKaroUtility cu = new CashKaroUtility(driver, testInfo, udid, port, automationName, deviceName, systemPort);

		cu.removeApp(driver,"com.earnkaro");

		driver = cu.launchChrome();
		
		cu.closeAllTabsAndOneTabInChromeBrowser(driver);
		cu.clickAndEnterURLInSearchOrTypeWebAddress(driver, GetCodeSetUP.ONELINK);
		cu.clickOneLink(driver);	
		cu.playStoreRedirectionVerification(driver);

		driver = cu.launchEarnKaroApp((System.getProperty("user.dir") + "/apk/ekapp.apk"));
		
		cu.allowLinkInPermissionPopup(driver);
		cu.allowLinkInPermissionPopup(driver);

		//Once after signing up, it should navigate to deferred deep link Page (Amazon)
		new SignInPage(driver, testInfo).
		clickOnSignInWithFacebook().
		enterFBUserEmail().
		enterFBPassword().
		clickOnFBLoginButton().
		clickOnContinue_DefferedDL();
		
		new StoreDetailPage(driver, testInfo).
		validateDeepLinkRedirection();
		
		reportStep("Deferred Deep linking Validation and Normal SignUp then validate the Retailer Navigation - Completed :)", "INFO");

	}
	
	@Test
	public void deferredDLFromStorePageFacebookSignUP() {

		reportStep("Deferred Deep linking Validation and Normal SignUp then validate the Retailer Navigationn", "INFO");
		
		CashKaroUtility cu = new CashKaroUtility(driver, testInfo, udid, port, automationName, deviceName, systemPort);

		cu.removeApp(driver,"com.earnkaro");

		driver = cu.launchChrome();
		
		cu.closeAllTabsAndOneTabInChromeBrowser(driver);
		cu.clickAndEnterURLInSearchOrTypeWebAddress(driver, GetCodeSetUP.ONELINK);
		cu.clickOneLink(driver);	
		cu.playStoreRedirectionVerification(driver);

		driver = cu.launchEarnKaroApp((System.getProperty("user.dir") + "/apk/ekapp.apk"));
		
		cu.allowLinkInPermissionPopup(driver);
		cu.allowLinkInPermissionPopup(driver);

		//Once after signing up, it should navigate to deferred deep link Page (Amazon)
		String otpValue = new SignInPage(driver, testInfo).
		clickOnSignInWithFacebook().
		enterFBNewUserEmail().
		enterNewFBUserPassword().
		clickOnFBLoginButton().
		clickOnContinueForNewUser().
		enterMobileNumberAndGetTheOTP();

		// creating the new object for the OTP page

		OTPPage objOtpPage = new OTPPage(driver, testInfo);
		objOtpPage.enterOTP(otpValue).clickOnVerifyOTP();
		
		new StoreDetailPage(driver, testInfo).
		validateDeepLinkRedirection();
		
		reportStep("Deferred Deep linking Validation and Normal SignUp then validate the Retailer Navigation - Completed :)", "INFO");

	}
		
	
}
