package com.app.ck.test;

import org.testng.annotations.Test;

import com.app.ck.base.WrapperMethods;
import com.app.ck.pages.EKOnboardingPage;
import com.app.ck.pages.HomePage;
import com.app.ck.pages.IntermediateRetailerPage;
import com.app.ck.pages.OTPPage;
import com.app.ck.pages.PaymentOTPPage;
import com.app.ck.pages.admin.AdminBonusCreditPage;
import com.app.ck.pages.admin.AdminCashbackPage;
import com.app.ck.pages.admin.AdminCoreFunction;
import com.app.ck.pages.admin.AdminPendingCashoutsPage;
import com.app.ck.pages.admin.AdminReportsPage;
import com.app.ck.utilities.CashKaroUtility;
import com.app.ck.utilities.Utilities;

public class EKTestLive extends WrapperMethods {

	/*********************************************************************/
	/***************** 	   Normal/ FaceBook Sign In		 *****************/
	/*********************************************************************/

	//@Test(priority=1)
	public void validateNormalLoginFromOnBoardingPage() {

		reportStep("Started normal sign-in from get started page", "INFO");

		new EKOnboardingPage(driver, testInfo).
		clickOnSignInbutton().
		enterUserName(getLiveTestData(0, "NormalEmail")).
		enterPassword(getLiveTestData(0, "Password")).
		clickSignInButtonForSuccess();

		reportStep("Sucessfully completed normal sign-in from get started page", "INFO");

	}

	//@Test(priority=2)
	public void validateFacebookLoginFromOnBoardingPage() {

		reportStep("Started facebook sign-in from get started page", "INFO");

		new EKOnboardingPage(driver, testInfo).
		clickOnSignInbutton().
		clickOnSignInWithFacebook().
		enterFBUserEmail(getLiveTestData(1, "FacebookEmail")).
		enterFBPassword(getLiveTestData(1, "Password")).
		clickOnFBLoginButton().
		clickOnContinue();

		reportStep("Sucessfully completed facebook sign-in from get started page", "INFO");

	}

	/*********************************************************************/
	/***************** 	   			   Bonus		 		 *************/
	/*********************************************************************/

	//@Test(priority=3)
	public void validatePaymentRequest() {

		reportStep("Started my earnings page confirmed & requested status validation", "INFO");

		String currentDate = Utilities.today_dd_MMM_YYYY_ISTZone().trim();
		String currentMonthYear = Utilities.currentMonthYear().trim();
		String otpValue = "000000";

		new EKOnboardingPage(driver, testInfo).
		clickOnSignInbutton().
		enterUserName(getLiveTestData(0, "NormalEmail")).
		enterPassword(getLiveTestData(0, "Password")).
		clickSignInButtonForSuccess();
		
		CashKaroUtility cuOne = new CashKaroUtility(udid, port, automationName, deviceName);
		driver = cuOne.launchChromeWebView(driver);

		AdminCoreFunction adminFunctions = new AdminCoreFunction(driver, testInfo);
		AdminBonusCreditPage adminBonusCredit = new AdminBonusCreditPage(driver, testInfo);
		adminFunctions.naviagateAdminPage_ThenSelectEarnKaro();
		adminFunctions.enterUsername(getLiveTestData(2, "UserName"));
		adminFunctions.enterPassword(getLiveTestData(2, "Password"));
		adminFunctions.clickSubmit();

		adminFunctions.clickOnHamburgerMenu();
		adminFunctions.clickCashbackMenu();
		adminBonusCredit.clickBonusCreditsSubMenu();
		adminBonusCredit.enterUser(getLiveTestData(0, "NormalEmail"));
		adminBonusCredit.enterBonusValue("250");
		adminBonusCredit.setDateRangeDate();
		adminBonusCredit.clickSubmit();
		
		CashKaroUtility reopenCKApp = new CashKaroUtility(udid, port, automationName, deviceName,systemPort);
		driver  = reopenCKApp.launchEarnKaroApp(driver);
		
		PaymentOTPPage objPaymentOTP = new HomePage(driver, testInfo).
		clickOnProfileIconForSignedUser().
		clickOnMyEarnings().
		clickProfitTab().
		clickMonthYearDropdown(currentMonthYear).
		validateDate(currentDate).
		validateRetailer(getTestData(5, "bonusStore")).
		validateProfitAmount(getTestData(11, "TwoFifty")).
		validateStatus(getTestData(11, "Confirmed")).
		clickProfitTab().
		clickOnRequestProfitPaymentButton().
		clickOnPaymentMethodDropDown().
		selectAmazonGiftCardFromDropDown().
		enterAmazonGiftCardEmailID().
		clickOnGETPAID(); 

		objPaymentOTP.
		enterOTP_Live(otpValue).
		clickOnVerifyOTPAndValidateThePaymentSuccessMessage().
		clickProfitTab().
		clickMonthYearDropdown(currentMonthYear).
		validateDate(currentDate).
		validateRetailer(getTestData(5, "bonusStore")).
		validateProfitAmount(getTestData(11, "TwoFifty")).
		validateStatus(getTestData(11, "Requested"));

		reportStep("Completed my earnings page confirmed & requested status validation", "INFO");

	}

	/*********************************************************************/
	/***************** 	   			Exit Click		 	 *****************/
	/*********************************************************************/

//	@Test(priority=4)
	public void validateExitClickGeneratingInAdmin() {

		reportStep("Started exit click validation", "INFO");

		String cbStoreName = "Amazon";
		String cbShortDescription = "You Earn";
		String cbLinkURL = getLiveTestData(3, "StoreUrl");

		new EKOnboardingPage(driver, testInfo).
		clickOnSignInbutton().
		enterUserName(getLiveTestData(0, "NormalEmail")).
		enterPassword(getLiveTestData(0, "Password")).
		clickSignInButtonForSuccess();

		new HomePage(driver, testInfo).
		clickOnSearchIcon().
		enterTextIntoTheSearchBar(cbStoreName).
		clickStoreCardShortDescriptionOnLive(cbShortDescription).
		clickOnAddToShareButton().
		clickOnShareIconWithCount(1).
		clickOnCopyLink();

		String url = getClipBoardText();

		CashKaroUtility cu = new CashKaroUtility(udid, port, automationName, deviceName);
		driver = cu.launchChrome();

		cu.closeAllTabsAndOneTabInChromeBrowser(driver);
		cu.clickAndEnterURLInSearchOrTypeWebAddress(driver, url);

		new IntermediateRetailerPage(driver, testInfo).validateRetailerPage(cbLinkURL, cbLinkURL);

		CashKaroUtility cuOne = new CashKaroUtility(udid, port, automationName, deviceName);
		driver = cuOne.launchChromeWebView(driver);

		AdminCoreFunction adminFunctions = new AdminCoreFunction(driver, testInfo);
		adminFunctions.naviagateAdminPage_ThenSelectEarnKaro();
		adminFunctions.enterUsername(getLiveTestData(2, "UserName"));
		adminFunctions.enterPassword(getLiveTestData(2, "Password"));
		adminFunctions.clickSubmit();

		AdminReportsPage reportsPage = new AdminReportsPage(driver, testInfo);
		adminFunctions.clickOnHamburgerMenu();
		adminFunctions.clickOnReports();
		reportsPage.clickExitClickSubMenu();
		reportsPage.clickClear();
		reportsPage.selectSearchByDropDown("User Email");
		reportsPage.enterKeyword(getLiveTestData(0, "NormalEmail"));
		reportsPage.clickSubmit();
		String exitClick = reportsPage.extractExitClickValueFromResultstableFirstRow();

		reportStep("Created Exit ID: "+exitClick, "INFO");

		reportStep("Sucessfuly completed exit click validation", "INFO");

	}

	/*********************************************************************/
	/***************** 	   			Re-Direction	 	 *****************/
	/*********************************************************************/

	@Test(priority=5)
	public void validateStoreRedirection() {

		reportStep("Started exit click validation", "INFO");

		String cbStoreName = "Amazon";
		String cbShortDescription = "You Earn";
		String cbLinkURL = getLiveTestData(3, "StoreUrl");

		new EKOnboardingPage(driver, testInfo).
		clickOnSignInbutton().
		enterUserName(getLiveTestData(0, "NormalEmail")).
		enterPassword(getLiveTestData(0, "Password")).
		clickSignInButtonForSuccess();

		new HomePage(driver, testInfo).
		clickOnSearchIcon().
		enterTextIntoTheSearchBar(cbStoreName).
		clickStoreCardShortDescriptionOnLive(cbShortDescription).
		clickOnAddToShareButton().
		clickOnShareIconWithCount(1).
		clickOnCopyLink();

		String url = getClipBoardText();

		CashKaroUtility cu = new CashKaroUtility(udid, port, automationName, deviceName);
		driver = cu.launchChrome();

		cu.closeAllTabsAndOneTabInChromeBrowser(driver);
		cu.clickAndEnterURLInSearchOrTypeWebAddress(driver, url);

		new IntermediateRetailerPage(driver, testInfo).
		validateRetailerPage(cbLinkURL, cbLinkURL);

		reportStep("Sucessfuly completed exit click validation", "INFO");

	}

	
	
	
	
}