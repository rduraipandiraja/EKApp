package com.app.ck.test;

import org.testng.annotations.Test;
import com.app.ck.base.WrapperMethods;
import com.app.ck.pages.EKOnboardingPage;
import com.app.ck.pages.HomePage;
import com.app.ck.pages.IntermediateRetailerPage;
import com.app.ck.pages.JoinFreePage;
import com.app.ck.pages.MyEarningsPage;
import com.app.ck.pages.OTPPage;
import com.app.ck.pages.PaymentOTPPage;
import com.app.ck.pages.StoreCategoryPage;
import com.app.ck.pages.StoreDetailPage;
import com.app.ck.pages.admin.AdminCashbackPage;
import com.app.ck.pages.admin.AdminCoreFunction;
import com.app.ck.pages.admin.AdminPartnerSettingsPage;
import com.app.ck.pages.admin.AdminPendingCashoutsPage;
import com.app.ck.pages.admin.AdminReportsPage;
import com.app.ck.pages.admin.AdminStoresPage;
import com.app.ck.pages.admin.AdminUsersPage;
import com.app.ck.pages.admin.ProductBrowserEditModePage;
import com.app.ck.utilities.CashKaroUtility;
import com.app.ck.utilities.Utilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.PressesKey;

public class EKTestMyEarningsPageValidation extends WrapperMethods {

	/*******************************************************************/
	/**************************** My Earnings **************************/
	/*******************************************************************/

	@Test
	public void earningsPage_TC001() {
		
		reportStep("TC001 is  started", "INFO");

		String OtpValue	=	new EKOnboardingPage(driver,testInfo).
		clickOnSignUpButton().
		enterAllJoinFreeFieldsAndVerifyOTP();

		OTPPage objOtpPage = new OTPPage(driver, testInfo);
		objOtpPage.
		enterOTP(OtpValue).
		clickOnVerifyOTP();

		new HomePage(driver, testInfo).
		clickOnProfileIconForSignedUser().
		clickOnMyEarnings().
		clickProfitTab().
		validateTotalProfitEarnedValue(getTestData(6, "Zero")).
		validateProfitEarningsValueProfitTab(getTestData(6, "Zero")).
		clickInfoIcon().
		validateEarningsInfoPoints().
		clickOnBackButtonForMyEarningsPage().
		clickOnRequestProfitPaymentButtonForThresholdNotReached().
		validateThresholdNotReached().
		clickReferralTabClick().
		validateTotalProfitEarnedValue(getTestData(6, "Zero")).
		validateReferralEarningsValueReferralTab(getTestData(6, "Zero")).
		clickOnRequestProfitPaymentButtonForThresholdNotReached().
		validateThresholdNotReached().
		validateBottomTextInAllTab().
		clickReferNow().
		clickBackButton().
		clickBackButton().
		clickBackButton();
			
		reportStep("TC001 is  ended", "INFO");
		
	}

	@Test
	public void earningsPage_TC002() {

		reportStep("TC002 is  started", "INFO");

		String cbStoreName = CashKaroUtility.extractValuesFromStoreCategoryJsonAndLoadTheVariablesForStore("CB_Store_One", 0, "CB_Store_One_Name");
		String cbShortDescription = CashKaroUtility.extractValuesFromStoreCategoryJsonAndLoadTheVariablesForStore("CB_Store_One", 0, "CB_Store_One_App_Short_Description");
		String cbLinkURL = CashKaroUtility.extractValuesFromStoreCategoryJsonAndLoadTheVariablesForStore("CB_Store_One", 0, "CB_Store_One_linkURL");

		String currentDate = Utilities.today_dd_MMM_YYYY_ISTZone().trim();
		String nintyDaysAhead = Utilities.generic_AheadDate_dd_MMM_yyyy(90).trim();
		String currentMonthYear = Utilities.currentMonthYear().trim();

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo);
		String emailAddress = objCashKaroUtility.generateEmail();
		String mobileNumber = objCashKaroUtility.generateMobileNumber();

		String otpValue = new EKOnboardingPage(driver, testInfo).
		clickOnSignUpButton().
		enterFullName().
		enterEmail(emailAddress).
		enterPassword().
		enterMobileNumber(mobileNumber).
		clickOnGetOTPButton(mobileNumber);

		new OTPPage(driver, testInfo).
		enterOTP(otpValue).
		clickOnVerifyOTP();

		new HomePage(driver, testInfo).
		clickOnSearchIcon().
		enterTextIntoTheSearchBar(cbStoreName).
		clickStoreCardShortDescription(cbShortDescription).
		validateStoreShortDescription(cbStoreName, cbShortDescription).
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
		adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
		adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
		adminFunctions.clickSubmit();

		AdminReportsPage reportsPage = new AdminReportsPage(driver, testInfo);
		adminFunctions.clickOnHamburgerMenu();
		adminFunctions.clickOnReports();
		reportsPage.clickExitClickSubMenu();
		reportsPage.clickClear();
		reportsPage.selectSearchByDropDown("User Email");
		reportsPage.enterKeyword(emailAddress);
		reportsPage.clickSubmit();
		String exitClick = reportsPage.extractExitClickValueFromResultstableFirstRow();

		AdminCashbackPage ProfitPage = new AdminCashbackPage(driver, testInfo);
		adminFunctions.clickOnHamburgerMenu();
		adminFunctions.clickCashbackMenu();
		ProfitPage.clickCashbackSubMenu();
		ProfitPage.clickAddNewButton();
		ProfitPage.enterExitId(exitClick);
		ProfitPage.entertransactionId(exitClick);
		ProfitPage.enterOrderId(exitClick);
		ProfitPage.setOrderDate();
		ProfitPage.enterOrderValue("1000");
		ProfitPage.enterconfirmCommisionNetwork(getTestData(11, "FiveHundered"));
		ProfitPage.entercashback(getTestData(11, "TwoFifty"));
		ProfitPage.selectCashbackTypeFromDropDown(getTestData(11, "Cashback"));
		ProfitPage.selectCashbackStatusFromDropDown(getTestData(11, "Pending"));
		ProfitPage.setPendingDate();
		ProfitPage.enterDetails(exitClick);
		ProfitPage.clickSubmit();

		CashKaroUtility reopenCKApp = new CashKaroUtility(udid, port, automationName, deviceName, systemPort);
		driver = reopenCKApp.launchEarnKaroApp(driver);

		new HomePage(driver, testInfo).
		clickOnProfileIconForSignedUser().
		clickOnMyEarnings().
		clickProfitTab().
		validateTotalProfitEarnedValue(getTestData(11, "TwoFiftyZeroZero")).
		clickTotalProfitEarnedValueHavingValueMoreThanZero(getTestData(11, "TwoFiftyZeroZero")).
		validateTotalProfitEarnedValue(getTestData(11, "TwoFiftyZeroZero")).
		validatePaidProfitValue(getTestData(6, "Zero")).
		validatePendingProfitValue(getTestData(11, "TwoFiftyZeroZero")).
		validateReferralProfitValue(getTestData(6, "Zero")).
		validateAvailableProfitValue(getTestData(6, "Zero")).
		clickBackButton().
		clickProfitTab().
		validateProfitEarningsValueProfitTab(getTestData(11, "Zero")).
		clickOnRequestProfitPaymentButtonForThresholdNotReached().
		validateThresholdNotReached().
		clickMonthYearDropdown(currentMonthYear).
		validateDate(currentDate).
		validateRetailer(cbStoreName).
		validateOrderAmount(getTestData(11, "OneZeroZeroZero")).
		validateProfitAmount(getTestData(11, "TwoFifty")).
		validateStatus(getTestData(11, "Pending")).
		validateExpected(nintyDaysAhead);

		CashKaroUtility cuTwo = new CashKaroUtility(udid, port, automationName, deviceName);
		driver = cuTwo.launchChromeWebView(driver);
		
		AdminCoreFunction adminFunctionsTwo = new AdminCoreFunction(driver, testInfo);
		AdminCashbackPage ProfitPageTwo = new AdminCashbackPage(driver, testInfo);
		adminFunctions = adminFunctionsTwo;
		ProfitPage = ProfitPageTwo;

		adminFunctions.naviagateAdminPage_ThenSelectEarnKaro();
		adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
		adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
		adminFunctions.clickSubmit();

		adminFunctions.clickOnHamburgerMenu();
		adminFunctions.clickCashbackMenu();
		ProfitPage.clickCashbackSubMenu();
		ProfitPage.selectSearchByStatusFromDropDown("Exit ID");
		ProfitPage.enterKeyword(exitClick);
		ProfitPage.selectCashbackStatusFromDropDown(getTestData(11, "Pending"));
		ProfitPage.clickSubmit();
		ProfitPage.clickEdit();
		ProfitPage.selectCashbackStatusFromDropDown(getTestData(11, "Confirmed"));
		ProfitPage.setConfirmDate();
		ProfitPage.clickSubmit();

		CashKaroUtility reopenCKAppAgain = new CashKaroUtility(udid, port, automationName, deviceName, systemPort);
		driver = reopenCKAppAgain.launchEarnKaroApp(driver);
		PaymentOTPPage objPaymentOTP = new HomePage(driver, testInfo).
		clickOnProfileIconForSignedUser().
		clickOnMyEarnings().
		clickProfitTab().
		validateTotalProfitEarnedValue(getTestData(11, "TwoFiftyZeroZero")).
		clickTotalProfitEarnedValueHavingValueMoreThanZero(getTestData(11, "TwoFiftyZeroZero")).
		validateTotalProfitEarnedValue(getTestData(11, "TwoFiftyZeroZero")).
		validatePaidProfitValue(getTestData(6, "Zero")).
		validatePendingProfitValue(getTestData(6, "Zero")).
		validateReferralProfitValue(getTestData(6, "Zero")).
		validateAvailableProfitValue(getTestData(11, "TwoFiftyZeroZero")).
		clickBackButton().
		clickProfitTab().
		validateProfitEarningsValueProfitTab(getTestData(11, "TwoFiftyZeroZero")).
		clickMonthYearDropdown(currentMonthYear).
		validateDate(currentDate).
		validateRetailer(cbStoreName).
		validateOrderAmount(getTestData(11, "OneZeroZeroZero")).
		validateProfitAmount(getTestData(11, "TwoFifty")).
		validateStatus(getTestData(11, "Confirmed")).
		validateAbsenceExpected(nintyDaysAhead).
		clickProfitTab().
		clickOnRequestProfitPaymentButton().
		clickOnPaymentMethodDropDown().
		selectBankPaymentNEFTFromDropDown().
		enterBankNEFTPaymentDetails().
		clickOnGETPAID();

		otpValue = objCashKaroUtility.generatePaymentOTP(mobileNumber);

		objPaymentOTP.
		enterOTP(otpValue).
		clickOnVerifyOTPAndValidateThePaymentSuccessMessage().
		clickProfitTab().
		validateTotalProfitEarnedValue(getTestData(11, "TwoFiftyZeroZero")).
		clickTotalProfitEarnedValueHavingValueMoreThanZero(getTestData(11, "TwoFiftyZeroZero")).
		validateTotalProfitEarnedValue(getTestData(11, "TwoFiftyZeroZero")).
		validatePaidProfitValue(getTestData(11, "TwoFiftyZeroZero")).
		validatePendingProfitValue(getTestData(6, "Zero")).
		validateReferralProfitValue(getTestData(6, "Zero")).
		validateAvailableProfitValue(getTestData(6, "Zero")).
		clickBackButton().
		clickProfitTab().
		validateProfitEarningsValueProfitTab(getTestData(6, "Zero")).
		clickMonthYearDropdown(currentMonthYear).
		validateDate(currentDate).
		validateRetailer(cbStoreName).
		validateOrderAmount(getTestData(11, "OneZeroZeroZero")).
		validateProfitAmount(getTestData(11, "TwoFifty")).
		validateStatus(getTestData(11, "Requested")).
		validateAbsenceExpected(nintyDaysAhead);

		/********************************************************************************/
		/************************************** CASHOUT**********************************/
		/********************************************************************************/

		CashKaroUtility cu3 = new CashKaroUtility(udid, port, automationName, deviceName);
		cu = cu3;
		driver = cu.launchChromeWebView(driver);

		AdminCoreFunction adminFunctions2 = new AdminCoreFunction(driver, testInfo);
		adminFunctions = adminFunctions2;
		adminFunctions.naviagateAdminPage_ThenSelectEarnKaro();
		adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
		adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
		adminFunctions.clickSubmit();

		adminFunctions.clickOnHamburgerMenu();
		adminFunctions.clickCashbackMenu();
		adminFunctions.clickPendingCashoutsSubMenu();

		AdminPendingCashoutsPage PendingCashoutsPage = new AdminPendingCashoutsPage(driver, testInfo);
		PendingCashoutsPage.selectSearchByDropDown("User Email");
		PendingCashoutsPage.enterKeyword(emailAddress);
		PendingCashoutsPage.clickSubmit();
		PendingCashoutsPage.clickEmail(emailAddress);
		PendingCashoutsPage.clickCreateCashout();

		driver.switchTo().alert().accept();
		PendingCashoutsPage.validateSuccessMessage();

		/********************************************************************************/

		CashKaroUtility reopenCKAppTwo = new CashKaroUtility(udid, port, automationName, deviceName, systemPort);
		driver = reopenCKAppTwo.launchEarnKaroApp(driver);

		new HomePage(driver, testInfo).
		clickOnProfileIconForSignedUser().
		clickOnMyEarnings().
		clickProfitTab().
		validateTotalProfitEarnedValue(getTestData(11, "TwoFiftyZeroZero")).
		clickTotalProfitEarnedValueHavingValueMoreThanZero(getTestData(11, "TwoFiftyZeroZero")).
		validateTotalProfitEarnedValue(getTestData(11, "TwoFiftyZeroZero")).
		validatePaidProfitValue(getTestData(11, "TwoFiftyZeroZero")).
		validatePendingProfitValue(getTestData(6, "Zero")).
		validateReferralProfitValue(getTestData(6, "Zero")).
		validateAvailableProfitValue(getTestData(6, "Zero")).
		clickBackButton().
		clickProfitTab().
		validateProfitEarningsValueProfitTab(getTestData(6, "Zero")).
		clickMonthYearDropdown(currentMonthYear).
		validateDate(currentDate).
		validateRetailer(cbStoreName).
		validateOrderAmount(getTestData(11, "OneZeroZeroZero")).
		validateProfitAmount(getTestData(11, "TwoFifty")).
		validateStatus(getTestData(11, "Paid")).
		validateAbsenceExpected(nintyDaysAhead);

		reportStep("TC002 is  ended", "INFO");

	}

	@Test
	public void earningsPage_TC003() {

		reportStep("TC004 is  started", "INFO");

		String cbStoreName = CashKaroUtility.extractValuesFromStoreCategoryJsonAndLoadTheVariablesForStore("CB_Store_One", 0, "CB_Store_One_Name");
		String cbShortDescription = CashKaroUtility.extractValuesFromStoreCategoryJsonAndLoadTheVariablesForStore("CB_Store_One", 0, "CB_Store_One_App_Short_Description");
		String cbLinkURL = CashKaroUtility.extractValuesFromStoreCategoryJsonAndLoadTheVariablesForStore("CB_Store_One", 0, "CB_Store_One_linkURL");

		String currentDate = Utilities.today_dd_MMM_YYYY_ISTZone().trim();
		String nintyDaysAhead = Utilities.generic_AheadDate_dd_MMM_yyyy(90).trim();
		String currentMonthYear = Utilities.currentMonthYear().trim();

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo);
		String emailAddress = objCashKaroUtility.generateEmail();
		String mobileNumber = objCashKaroUtility.generateMobileNumber();

		String otpValue = new EKOnboardingPage(driver, testInfo).
		clickOnSignUpButton().
		enterFullName().
		enterEmail(emailAddress).
		enterPassword().
		enterMobileNumber(mobileNumber).
		clickOnGetOTPButton(mobileNumber);

		OTPPage objOtpPage = new OTPPage(driver, testInfo);
		objOtpPage.
		enterOTP(otpValue).
		clickOnVerifyOTP();

		new HomePage(driver, testInfo).
		clickOnSearchIcon().
		enterTextIntoTheSearchBar(cbStoreName).
		clickStoreCardShortDescription(cbShortDescription).
		validateStoreShortDescription(cbStoreName, cbShortDescription).
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
		adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
		adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
		adminFunctions.clickSubmit();

		AdminReportsPage reportsPage = new AdminReportsPage(driver, testInfo);
		adminFunctions.clickOnHamburgerMenu();
		adminFunctions.clickOnReports();
		reportsPage.clickExitClickSubMenu();
		reportsPage.clickClear();
		reportsPage.selectSearchByDropDown("User Email");
		reportsPage.enterKeyword(emailAddress);
		reportsPage.clickSubmit();
		String exitClick = reportsPage.extractExitClickValueFromResultstableFirstRow();

		AdminCashbackPage ProfitPage = new AdminCashbackPage(driver, testInfo);
		adminFunctions.clickOnHamburgerMenu();
		adminFunctions.clickCashbackMenu();
		ProfitPage.clickCashbackSubMenu();
		ProfitPage.clickAddNewButton();
		ProfitPage.enterExitId(exitClick);
		ProfitPage.entertransactionId(exitClick);
		ProfitPage.enterOrderId(exitClick);
		ProfitPage.setOrderDate();
		ProfitPage.enterOrderValue("1000");
		ProfitPage.enterconfirmCommisionNetwork(getTestData(11, "FiveHundered"));
		ProfitPage.entercashback(getTestData(11, "TwoFifty"));
		ProfitPage.selectCashbackTypeFromDropDown(getTestData(11, "Cashback"));
		ProfitPage.selectCashbackStatusFromDropDown(getTestData(11, "Pending"));
		ProfitPage.setPendingDate();
		ProfitPage.enterDetails(exitClick);
		ProfitPage.clickSubmit();

		CashKaroUtility reopenCKApp = new CashKaroUtility(udid, port, automationName, deviceName, systemPort);
		driver = reopenCKApp.launchEarnKaroApp(driver);

		new HomePage(driver, testInfo).
		clickOnProfileIconForSignedUser().
		clickOnMyEarnings().
		clickProfitTab().
		validateTotalProfitEarnedValue(getTestData(11, "TwoFiftyZeroZero")).
		clickTotalProfitEarnedValueHavingValueMoreThanZero(getTestData(11, "TwoFiftyZeroZero")).
		validateTotalProfitEarnedValue(getTestData(11, "TwoFiftyZeroZero")).
		validatePaidProfitValue(getTestData(6, "Zero")).
		validatePendingProfitValue(getTestData(11, "TwoFiftyZeroZero")).
		validateReferralProfitValue(getTestData(6, "Zero")).
		validateAvailableProfitValue(getTestData(6, "Zero")).
		clickBackButton().
		clickProfitTab().
		validateProfitEarningsValueProfitTab(getTestData(11, "Zero")).
		clickOnRequestProfitPaymentButtonForThresholdNotReached().
		validateThresholdNotReached().
		clickMonthYearDropdown(currentMonthYear).
		validateDate(currentDate).
		validateRetailer(cbStoreName).
		validateOrderAmount(getTestData(11, "OneZeroZeroZero")).
		validateProfitAmount(getTestData(11, "TwoFifty")).
		validateStatus(getTestData(11, "Pending")).
		validateExpected(nintyDaysAhead);

		CashKaroUtility cuTwo = new CashKaroUtility(udid, port, automationName, deviceName);
		driver = cuTwo.launchChromeWebView(driver);
		AdminCoreFunction adminFunctionsTwo = new AdminCoreFunction(driver, testInfo);
		AdminCashbackPage ProfitPageTwo = new AdminCashbackPage(driver, testInfo);
		adminFunctions = adminFunctionsTwo;
		ProfitPage = ProfitPageTwo;

		adminFunctions.naviagateAdminPage_ThenSelectEarnKaro();
		adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
		adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
		adminFunctions.clickSubmit();

		adminFunctions.clickOnHamburgerMenu();
		adminFunctions.clickCashbackMenu();
		ProfitPage.clickCashbackSubMenu();
		ProfitPage.selectSearchByStatusFromDropDown("Exit ID");
		ProfitPage.enterKeyword(exitClick);
		ProfitPage.selectCashbackStatusFromDropDown(getTestData(11, "Pending"));
		ProfitPage.clickSubmit();
		ProfitPage.clickEdit();
		ProfitPage.selectCashbackStatusFromDropDown(getTestData(11, "Cancelled"));
		ProfitPage.setFailDate();
		ProfitPage.clickSubmit();

		CashKaroUtility reopenCKAppAgain = new CashKaroUtility(udid, port, automationName, deviceName, systemPort);
		driver = reopenCKAppAgain.launchEarnKaroApp(driver);
		new HomePage(driver, testInfo).
		clickOnProfileIconForSignedUser().
		clickOnMyEarnings().
		clickProfitTab().
		validateTotalProfitEarnedValue(getTestData(6, "Zero")).
		clickTotalProfitEarnedValue(getTestData(6, "Zero")).
		clickProfitTab().
		validateProfitEarningsValueProfitTab(getTestData(6, "Zero")).
		clickMonthYearDropdown(currentMonthYear).
		validateDate(currentDate).
		validateRetailer(cbStoreName).
		validateOrderAmount(getTestData(11, "OneZeroZeroZero")).
		validateProfitAmount(getTestData(11, "TwoFifty")).
		validateStatus(getTestData(11, "Cancelled")).
		validateAbsenceExpected(nintyDaysAhead).
		clickReferralTabClick().
		validateReferralEarningsValueReferralTab(getTestData(6, "Zero"));

		reportStep("TC004 is  ended", "INFO");

	}

	@Test
	public void earningsPage_TC004() {

		reportStep("TC006 is  started", "INFO");

		String currentDate = Utilities.today_dd_MMM_YYYY_ISTZone().trim();
		String nintyDaysAhead = Utilities.generic_AheadDate_dd_MMM_yyyy(90).trim();
		String currentMonthYear = Utilities.currentMonthYear().trim();

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo);
		String emailAddress = objCashKaroUtility.generateEmail();
		String mobileNumber = objCashKaroUtility.generateMobileNumber();

		String otpValue = new EKOnboardingPage(driver, testInfo).
		clickOnSignUpButton().
		enterFullName().
		enterEmail(emailAddress).
		enterPassword().
		enterMobileNumber(mobileNumber).
		clickOnGetOTPButton(mobileNumber);

		OTPPage objOtpPage = new OTPPage(driver, testInfo);
		objOtpPage.
		enterOTP(otpValue).
		clickOnVerifyOTP();

		new HomePage(driver, testInfo);

		objCashKaroUtility.addCashbackBonus(emailAddress, getTestData(11, "TwoFifty"), getTestData(11, "Cashback"));

		PaymentOTPPage objPaymentOTP = new HomePage(driver, testInfo).
		clickOnProfileIconForSignedUser().
		clickOnMyEarnings().
		clickProfitTab().
		validateTotalProfitEarnedValue(getTestData(11, "TwoFiftyZeroZero")).
		clickTotalProfitEarnedValueHavingValueMoreThanZero(getTestData(11, "TwoFiftyZeroZero")).
		validateTotalProfitEarnedValue(getTestData(11, "TwoFiftyZeroZero")).
		validatePaidProfitValue(getTestData(6, "Zero")).
		validatePendingProfitValue(getTestData(6, "Zero")).
		validateReferralProfitValue(getTestData(6, "Zero")).
		validateAvailableProfitValue(getTestData(11, "TwoFiftyZeroZero")).
		clickBackButton().
		clickProfitTab().
		validateProfitEarningsValueProfitTab(getTestData(11, "TwoFiftyZeroZero")).
		clickMonthYearDropdown(currentMonthYear).
		validateDate(currentDate).
		validateRetailer(getTestData(5, "bonusStore")).
		validateAbsenceOrderAmount().
		validateProfitAmount(getTestData(11, "TwoFifty")).
		validateStatus(getTestData(11, "Confirmed")).
		validateAbsenceExpected(nintyDaysAhead).
		clickReferralTabClick().
		validateReferralEarningsValueReferralTab(getTestData(6, "Zero")).
		clickProfitTab().
		clickOnRequestProfitPaymentButton().
		clickOnPaymentMethodDropDown().
		selectAmazonGiftCardFromDropDown().
		enterAmazonGiftCardEmailID().
		clickOnGETPAID();

		otpValue = objCashKaroUtility.generatePaymentOTP(mobileNumber);

		objPaymentOTP.
		enterOTP(otpValue).
		clickOnVerifyOTPAndValidateThePaymentSuccessMessage().
		clickProfitTab().
		validateTotalProfitEarnedValue(getTestData(11, "TwoFiftyZeroZero")).
		clickTotalProfitEarnedValueHavingValueMoreThanZero(getTestData(11, "TwoFiftyZeroZero")).
		validateTotalProfitEarnedValue(getTestData(11, "TwoFiftyZeroZero")).
		validatePaidProfitValue(getTestData(11, "TwoFiftyZeroZero")).
		validatePendingProfitValue(getTestData(6, "Zero")).
		validateReferralProfitValue(getTestData(6, "Zero")).
		validateAvailableProfitValue(getTestData(6, "Zero")).
		clickBackButton().
		clickProfitTab().
		validateProfitEarningsValueProfitTab(getTestData(6, "Zero")).
		clickMonthYearDropdown(currentMonthYear).validateDate(currentDate).
		validateRetailer(getTestData(5, "bonusStore")).
		validateAbsenceOrderAmount().
		validateProfitAmount(getTestData(11, "TwoFifty")).
		validateStatus(getTestData(11, "Requested")).
		validateAbsenceExpected(nintyDaysAhead).
		clickReferralTabClick().
		validateReferralEarningsValueReferralTab(getTestData(6, "Zero"));

		/********************************************************************************/
		/************************************** CASHOUT**********************************/
		/********************************************************************************/

		CashKaroUtility cu = new CashKaroUtility(udid, port, automationName, deviceName);
		driver = cu.launchChromeWebView(driver);

		AdminCoreFunction adminFunctions = new AdminCoreFunction(driver, testInfo);
		adminFunctions.naviagateAdminPage_ThenSelectEarnKaro();
		adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
		adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
		adminFunctions.clickSubmit();

		adminFunctions.clickOnHamburgerMenu();
		adminFunctions.clickCashbackMenu();
		adminFunctions.clickPendingCashoutsSubMenu();

		AdminPendingCashoutsPage PendingCashoutsPage = new AdminPendingCashoutsPage(driver, testInfo);
		PendingCashoutsPage.selectSearchByDropDown("User Email");
		PendingCashoutsPage.enterKeyword(emailAddress);
		PendingCashoutsPage.clickSubmit();
		PendingCashoutsPage.clickEmail(emailAddress);
		PendingCashoutsPage.clickCreateCashout();

		driver.switchTo().alert().accept();
		PendingCashoutsPage.validateSuccessMessage();

		/********************************************************************************/

		CashKaroUtility reopenCKAppTwo = new CashKaroUtility(udid, port, automationName, deviceName, systemPort);
		driver = reopenCKAppTwo.launchEarnKaroApp(driver);

		new HomePage(driver, testInfo).
		clickOnProfileIconForSignedUser().
		clickOnMyEarnings().
		clickProfitTab().
		validateTotalProfitEarnedValue(getTestData(11, "TwoFiftyZeroZero")).
		clickTotalProfitEarnedValueHavingValueMoreThanZero(getTestData(11, "TwoFiftyZeroZero")).
		validateTotalProfitEarnedValue(getTestData(11, "TwoFiftyZeroZero")).
		validatePaidProfitValue(getTestData(11, "TwoFiftyZeroZero")).
		validatePendingProfitValue(getTestData(6, "Zero")).
		validateReferralProfitValue(getTestData(6, "Zero")).
		validateAvailableProfitValue(getTestData(6, "Zero")).
		clickBackButton().
		clickProfitTab().
		validateProfitEarningsValueProfitTab(getTestData(6, "Zero")).
		clickMonthYearDropdown(currentMonthYear).
		validateDate(currentDate).
		validateRetailer(getTestData(5, "bonusStore")).
		validateAbsenceOrderAmount().
		validateProfitAmount(getTestData(11, "TwoFifty")).
		validateStatus(getTestData(11, "Paid")).
		validateAbsenceExpected(nintyDaysAhead).
		clickReferralTabClick().
		validateReferralEarningsValueReferralTab(getTestData(6, "Zero"));

		reportStep("TC006 is  ended", "INFO");

	}

	/*******************************************************************/
	/******** Sign up bonus test cases please do not uncomment *********/
	/*******************************************************************/

	// @Test
	public void earningsPage_TC005() {

		reportStep("TC008 is  started", "INFO");

		String currentDate = Utilities.today_dd_MMM_YYYY_ISTZone().trim();
		String nintyDaysAhead = Utilities.generic_AheadDate_dd_MMM_yyyy(90).trim();
		String currentMonthYear = Utilities.currentMonthYear().trim();

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo);
		String emailAddress = objCashKaroUtility.generateEmail();
		String mobileNumber = objCashKaroUtility.generateMobileNumber();

		CashKaroUtility cu = new CashKaroUtility(udid, port, automationName, deviceName);
		driver = cu.launchChromeWebView(driver);

		AdminCoreFunction adminFunctions = new AdminCoreFunction(driver, testInfo);
		adminFunctions.naviagateAdminPage_ThenSelectEarnKaro();
		adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
		adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
		adminFunctions.clickSubmit();

		AdminPartnerSettingsPage partnerSettingsPage = new AdminPartnerSettingsPage(driver, testInfo);
		adminFunctions.clickOnHamburgerMenu();
		adminFunctions.clickOnSettings();
		adminFunctions.clickOnPartnerSettings();
		partnerSettingsPage.clickEditPartnerDetails();
		partnerSettingsPage.enterSignUpBonus(getTestData(11, "TwoFiftyZeroZero"));
		partnerSettingsPage.clickSubmit();

		CashKaroUtility reopenCKApp = new CashKaroUtility(udid, port, automationName, deviceName, systemPort);
		driver = reopenCKApp.launchEarnKaroApp(driver);

		String otpValue = new EKOnboardingPage(driver, testInfo).
		clickOnSignUpButton().
		enterFullName().
		enterEmail(emailAddress).
		enterPassword().
		enterMobileNumber(mobileNumber).
		clickOnGetOTPButton(mobileNumber);

		OTPPage objOtpPage = new OTPPage(driver, testInfo);
		objOtpPage.enterOTP(otpValue).clickOnVerifyOTP();

		new HomePage(driver, testInfo);

		PaymentOTPPage objPaymentOTP = new HomePage(driver, testInfo).
		clickOnProfileIconForSignedUser().
		clickOnMyEarnings().
		clickProfitTab().
		validateTotalProfitEarnedValue(getTestData(11, "TwoFiftyZeroZero")).
		clickTotalProfitEarnedValueHavingValueMoreThanZero(getTestData(11, "TwoFiftyZeroZero")).
		validateTotalProfitEarnedValue(getTestData(11, "TwoFiftyZeroZero")).
		validatePaidProfitValue(getTestData(6, "Zero")).
		validatePendingProfitValue(getTestData(6, "Zero")).
		validateReferralProfitValue(getTestData(6, "Zero")).
		validateAvailableProfitValue(getTestData(11, "TwoFiftyZeroZero")).
		clickBackButton().
		clickProfitTab().
		validateProfitEarningsValueProfitTab(getTestData(11, "TwoFiftyZeroZero")).
		clickMonthYearDropdown(currentMonthYear).validateDate(currentDate).
		
		validateAbsenceOrderAmount().
		validateProfitAmount(getTestData(11, "TwoFifty")).
		validateStatus(getTestData(11, "Confirmed")).
		validateAbsenceExpected(nintyDaysAhead).
		clickReferralTabClick().
		validateReferralEarningsValueReferralTab(getTestData(6, "Zero")).
		clickProfitTab().
		clickOnRequestProfitPaymentButton().
		clickOnPaymentMethodDropDown().
		selectAmazonGiftCardFromDropDown().
		enterAmazonGiftCardEmailID().
		clickOnGETPAID();

		otpValue = objCashKaroUtility.generatePaymentOTP(mobileNumber);

		objPaymentOTP.
		enterOTP(otpValue).
		clickOnVerifyOTPAndValidateThePaymentSuccessMessage().
		clickProfitTab().validateTotalProfitEarnedValue(getTestData(11, "TwoFiftyZeroZero")).
		clickTotalProfitEarnedValueHavingValueMoreThanZero(getTestData(11, "TwoFiftyZeroZero")).
		validateTotalProfitEarnedValue(getTestData(11, "TwoFiftyZeroZero")).
		validatePaidProfitValue(getTestData(11, "TwoFiftyZeroZero")).
		validatePendingProfitValue(getTestData(6, "Zero")).
		validateReferralProfitValue(getTestData(6, "Zero")).
		validateAvailableProfitValue(getTestData(6, "Zero")).
		clickBackButton().
		clickProfitTab().
		validateProfitEarningsValueProfitTab(getTestData(6, "Zero")).
		clickMonthYearDropdown(currentMonthYear).validateDate(currentDate).
		
		validateAbsenceOrderAmount().
		validateProfitAmount(getTestData(11, "TwoFifty")).
		validateStatus(getTestData(11, "Requested")).
		validateAbsenceExpected(nintyDaysAhead).
		clickReferralTabClick().
		validateReferralEarningsValueReferralTab(getTestData(6, "Zero"));

		objCashKaroUtility.cashOut(emailAddress);

		scrollFromUpToDowninApp();
		scrollFromUpToDowninApp();
		scrollFromUpToDowninApp();

		new MyEarningsPage(driver, testInfo).
		clickProfitTab().
		validateTotalProfitEarnedValue(getTestData(11, "TwoFiftyZeroZero")).
		clickTotalProfitEarnedValueHavingValueMoreThanZero(getTestData(11, "TwoFiftyZeroZero")).
		validateTotalProfitEarnedValue(getTestData(11, "TwoFiftyZeroZero")).
		validatePaidProfitValue(getTestData(11, "TwoFiftyZeroZero")).
		validatePendingProfitValue(getTestData(6, "Zero")).
		validateReferralProfitValue(getTestData(6, "Zero")).
		validateAvailableProfitValue(getTestData(6, "Zero")).
		clickBackButton().
		clickProfitTab().
		validateProfitEarningsValueProfitTab(getTestData(6, "Zero")).
		clickMonthYearDropdown(currentMonthYear).validateDate(currentDate).
		
		validateAbsenceOrderAmount().
		validateProfitAmount(getTestData(11, "TwoFifty")).
		validateStatus(getTestData(11, "Paid")).
		validateAbsenceExpected(nintyDaysAhead).
		clickReferralTabClick().
		validateReferralEarningsValueReferralTab(getTestData(6, "Zero"));

		CashKaroUtility cuOne = new CashKaroUtility(udid, port, automationName, deviceName);
		driver = cuOne.launchChromeWebView(driver);
		AdminCoreFunction adminFunctionsTwo = new AdminCoreFunction(driver, testInfo);
		AdminPartnerSettingsPage partnerSettingsPageTwo = new AdminPartnerSettingsPage(driver, testInfo);
		adminFunctions = adminFunctionsTwo;
		partnerSettingsPage = partnerSettingsPageTwo;

		adminFunctions.naviagateAdminPage_ThenSelectEarnKaro();
		adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
		adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
		adminFunctions.clickSubmit();

		adminFunctions.clickOnHamburgerMenu();
		adminFunctions.clickOnSettings();
		adminFunctions.clickOnPartnerSettings();
		partnerSettingsPage.clickEditPartnerDetails();
		partnerSettingsPage.enterSignUpBonus(getTestData(6, "Zero"));
		partnerSettingsPage.clickSubmit();

		reportStep("TC008 is  ended", "INFO");

	}

	/*******************************************************************/
	/******** Sign up bonus test cases please do not uncomment *********/
	/*******************************************************************/
	
	@Test
	public void earningsPage_TC006() {

		reportStep("TC009 is  started", "INFO");
		
		try {
			
			String currentDate = Utilities.today_dd_MMM_YYYY_ISTZone().trim();
			String currentMonthYear = Utilities.currentMonthYear().trim();
			String nintyDaysAhead = Utilities.generic_AheadDate_dd_MMM_yyyy(90).trim();

			CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo);
			String emailAddress = objCashKaroUtility.generateEmail();
			String mobileNumber = objCashKaroUtility.generateMobileNumber();

			String otpValue = new EKOnboardingPage(driver, testInfo).
			clickOnSignUpButton().
			enterFullName().
			enterEmail(emailAddress).
			enterPassword().
			enterMobileNumber(mobileNumber).
			clickOnGetOTPButton(mobileNumber);

			OTPPage objOtpPage = new OTPPage(driver, testInfo);
			objOtpPage.
			enterOTP(otpValue).
			clickOnVerifyOTP();

			new HomePage(driver, testInfo).
			clickOnProfileIconForSignedUser().
			clickOnMyReferral().
			clickReferralNetwork().
			validateObjectsMyReferrals().
			validateTotalReferralProfitEarnedValue("0").
			validateFriendsJoinedValue("0").
			clickReferNow().
			clickBackButtonRedirectToReferralNetwork().
			clickBackButton().
			clickBackButton().
			clickOnLogout();

			CashKaroUtility cu = new CashKaroUtility(udid, port, automationName, deviceName);
			driver = cu.launchChromeWebView(driver);

			AdminCoreFunction adminFunctions = new AdminCoreFunction(driver, testInfo);
			adminFunctions.naviagateAdminPage_ThenSelectEarnKaro();
			adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
			adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
			adminFunctions.clickSubmit();

			AdminPartnerSettingsPage partnerSettingsPage = new AdminPartnerSettingsPage(driver, testInfo);
			adminFunctions.clickOnHamburgerMenu();
			adminFunctions.clickOnSettings();
			adminFunctions.clickOnPartnerSettings();
			partnerSettingsPage.clickEditPartnerDetails();
			partnerSettingsPage.enterReferralSignUpBonus(getTestData(11, "TwoFiftyZeroZero"));
			partnerSettingsPage.clickSubmit();

			AdminUsersPage usersPage = new AdminUsersPage(driver, testInfo);
			adminFunctions.clickOnHamburgerMenu();
			adminFunctions.clickUsersMenu();
			adminFunctions.clickUsersSubMenu();
			usersPage.selectSearchByDropDown("User Email");
			usersPage.enterKeyword(emailAddress);
			usersPage.clickSubmitButton();
			String userId = usersPage.extractUserIdValueFromResultstableFirstRow();
			String userName = usersPage.extractUserNameValueFromResultstableFirstRow();

			CashKaroUtility reopenCKApp1 = new CashKaroUtility(udid, port, automationName, deviceName, systemPort);
			driver = reopenCKApp1.launchEarnKaroApp(driver);

			new EKOnboardingPage(driver, testInfo);

			CashKaroUtility objcashKaroutility = new CashKaroUtility(udid, port, automationName, deviceName);
			AppiumDriver<MobileElement> driver2 = objcashKaroutility.launchChrome();
			driver = driver2;

			String url = Utilities.referralLinkGetCode(userId, userName);

			objcashKaroutility.closeAllTabsAndOneTabInChromeBrowser(driver);
			objcashKaroutility.clickAndEnterURLInSearchOrTypeWebAddress(driver, url);
			objcashKaroutility.clickJoinFreeLink(driver);

			String emailAddressReferralJoinee = objCashKaroUtility.generateEmail();
			String mobileNumberReferralJoinee = objCashKaroUtility.generateMobileNumber();
			String name = "SignUpReferral";

			String OtpValue = new JoinFreePage(driver, testInfo).
			validateReferralByUserAtJoinFree(userName).
			enterFullName(name).
			enterEmail(emailAddressReferralJoinee).
			enterPassword().
			enterMobileNumber(mobileNumberReferralJoinee).
			clickOnGetOTPButton(mobileNumberReferralJoinee);

			new OTPPage(driver, testInfo).
			enterOTP(OtpValue).
			clickOnVerifyOTP();

			new HomePage(driver, testInfo);

			PaymentOTPPage objPaymentOTP = new HomePage(driver, testInfo).
			clickOnProfileIconForSignedUser().
			clickOnMyEarnings().
			clickProfitTab().
			validateTotalProfitEarnedValue(getTestData(11, "TwoFiftyZeroZero")).
			clickTotalProfitEarnedValueHavingValueMoreThanZero(getTestData(11, "TwoFiftyZeroZero")).
			validateTotalProfitEarnedValue(getTestData(11, "TwoFiftyZeroZero")).
			validatePaidProfitValue(getTestData(6, "Zero")).
			validatePendingProfitValue(getTestData(6, "Zero")).
			validateReferralProfitValue(getTestData(6, "Zero")).
			validateAvailableProfitValue(getTestData(11, "TwoFiftyZeroZero")).
			clickBackButton().
			clickProfitTab().
			validateProfitEarningsValueProfitTab(getTestData(11, "TwoFiftyZeroZero")).
			clickMonthYearDropdown(currentMonthYear).
			validateDate(currentDate).
			
			validateAbsenceOrderAmount().
			validateProfitAmount(getTestData(11, "TwoFifty")).
			validateStatus(getTestData(11, "Confirmed")).
			validateAbsenceExpected(nintyDaysAhead).
			clickReferralTabClick().
			validateReferralEarningsValueReferralTab(getTestData(6, "Zero")).
			clickProfitTab().
			clickOnRequestProfitPaymentButton().
			clickOnPaymentMethodDropDown().
			selectAmazonGiftCardFromDropDown().
			enterAmazonGiftCardEmailID().
			clickOnGETPAID();

			otpValue = objCashKaroUtility.generatePaymentOTP(mobileNumberReferralJoinee);

			objPaymentOTP.
			enterOTP(otpValue).
			clickOnVerifyOTPAndValidateThePaymentSuccessMessage().
			clickProfitTab().
			validateTotalProfitEarnedValue(getTestData(11, "TwoFiftyZeroZero")).
			clickTotalProfitEarnedValueHavingValueMoreThanZero(getTestData(11, "TwoFiftyZeroZero")).
			validateTotalProfitEarnedValue(getTestData(11, "TwoFiftyZeroZero")).
			validatePaidProfitValue(getTestData(11, "TwoFiftyZeroZero")).
			validatePendingProfitValue(getTestData(6, "Zero")).
			validateReferralProfitValue(getTestData(6, "Zero")).
			validateAvailableProfitValue(getTestData(6, "Zero")).
			clickBackButton()
			.clickProfitTab().
			validateProfitEarningsValueProfitTab(getTestData(6, "Zero")).
			clickMonthYearDropdown(currentMonthYear).validateDate(currentDate).
			
			validateAbsenceOrderAmount().
			validateProfitAmount(getTestData(11, "TwoFifty")).
			validateStatus(getTestData(11, "Requested")).
			validateAbsenceExpected(nintyDaysAhead).
			clickReferralTabClick().
			validateReferralEarningsValueReferralTab(getTestData(6, "Zero"));

			/********************************************************************************/
			/************************************** CASHOUT**********************************/
			/********************************************************************************/

			CashKaroUtility cu1 = new CashKaroUtility(udid, port, automationName, deviceName);
			cu = cu1;
			driver = cu.launchChromeWebView(driver);

			AdminCoreFunction adminFunctions1 = new AdminCoreFunction(driver, testInfo);
			adminFunctions = adminFunctions1;
			adminFunctions.naviagateAdminPage_ThenSelectEarnKaro();
			adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
			adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
			adminFunctions.clickSubmit();

			adminFunctions.clickOnHamburgerMenu();
			adminFunctions.clickCashbackMenu();
			adminFunctions.clickPendingCashoutsSubMenu();

			AdminPendingCashoutsPage PendingCashoutsPage = new AdminPendingCashoutsPage(driver, testInfo);
			PendingCashoutsPage.selectSearchByDropDown("User Email");
			PendingCashoutsPage.enterKeyword(emailAddressReferralJoinee);
			PendingCashoutsPage.clickSubmit();
			PendingCashoutsPage.clickEmail(emailAddressReferralJoinee);
			PendingCashoutsPage.clickCreateCashout();

			driver.switchTo().alert().accept();
			PendingCashoutsPage.validateSuccessMessage();

			/********************************************************************************/

			CashKaroUtility reopenCKApp = new CashKaroUtility(udid, port, automationName, deviceName, systemPort);
			driver = reopenCKApp.launchEarnKaroApp(driver);

			new HomePage(driver, testInfo).
			clickOnProfileIconForSignedUser().
			clickOnMyEarnings().
			clickProfitTab().
			validateTotalProfitEarnedValue(getTestData(11, "TwoFiftyZeroZero")).
			clickTotalProfitEarnedValueHavingValueMoreThanZero(getTestData(11, "TwoFiftyZeroZero")).
			validateTotalProfitEarnedValue(getTestData(11, "TwoFiftyZeroZero")).
			validatePaidProfitValue(getTestData(11, "TwoFiftyZeroZero")).
			validatePendingProfitValue(getTestData(6, "Zero")).
			validateReferralProfitValue(getTestData(6, "Zero")).
			validateAvailableProfitValue(getTestData(6, "Zero")).
			clickBackButton().
			clickProfitTab().
			validateProfitEarningsValueProfitTab(getTestData(6, "Zero")).
			clickMonthYearDropdown(currentMonthYear).validateDate(currentDate).
			
			validateAbsenceOrderAmount().
			validateProfitAmount(getTestData(11, "TwoFifty")).
			validateStatus(getTestData(11, "Paid")).
			validateAbsenceExpected(nintyDaysAhead).
			clickReferralTabClick().
			validateReferralEarningsValueReferralTab(getTestData(6, "Zero"));

			CashKaroUtility cuOne = new CashKaroUtility(udid, port, automationName, deviceName);
			driver = cuOne.launchChromeWebView(driver);
			AdminCoreFunction adminFunctionsTwo = new AdminCoreFunction(driver, testInfo);
			AdminPartnerSettingsPage partnerSettingsPageTwo = new AdminPartnerSettingsPage(driver, testInfo);
			adminFunctions = adminFunctionsTwo;
			partnerSettingsPage = partnerSettingsPageTwo;

			adminFunctions.naviagateAdminPage_ThenSelectEarnKaro();
			adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
			adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
			adminFunctions.clickSubmit();

			adminFunctions.clickOnHamburgerMenu();
			adminFunctions.clickOnSettings();
			adminFunctions.clickOnPartnerSettings();
			partnerSettingsPage.clickEditPartnerDetails();
			partnerSettingsPage.enterReferralSignUpBonus(getTestData(6, "Zero"));
			partnerSettingsPage.clickSubmit();

			
		} catch (Exception e) {

			/********************************************************************************/

			CashKaroUtility cuOne = new CashKaroUtility(udid, port, automationName, deviceName);
			driver = cuOne.launchChromeWebView(driver);
			AdminCoreFunction adminFunctions = new AdminCoreFunction(driver, testInfo);
			AdminPartnerSettingsPage partnerSettingsPage = new AdminPartnerSettingsPage(driver, testInfo);

			adminFunctions.naviagateAdminPage_ThenSelectEarnKaro();
			adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
			adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
			adminFunctions.clickSubmit();

			adminFunctions.clickOnHamburgerMenu();
			adminFunctions.clickOnSettings();
			adminFunctions.clickOnPartnerSettings();
			partnerSettingsPage.clickEditPartnerDetails();
			partnerSettingsPage.enterReferralSignUpBonus(getTestData(6, "Zero"));
			partnerSettingsPage.clickSubmit();
		}


		reportStep("TC009 is  completed", "PASS");

	}

	@Test
	public void earningsPage_TC007() {

		reportStep("TC009 is  started", "INFO");

		String currentDate = Utilities.today_dd_MMM_YYYY_ISTZone().trim();
		String currentMonthYear = Utilities.currentMonthYear().trim();
		String nintyDaysAhead = Utilities.generic_AheadDate_dd_MMM_yyyy(90).trim();

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo);
		String emailAddress = objCashKaroUtility.generateEmail();
		String mobileNumber = objCashKaroUtility.generateMobileNumber();

		String otpValue = new EKOnboardingPage(driver, testInfo).
		clickOnSignUpButton().
		enterFullName().
		enterEmail(emailAddress).
		enterPassword().
		enterMobileNumber(mobileNumber).
		clickOnGetOTPButton(mobileNumber);

		OTPPage objOtpPage = new OTPPage(driver, testInfo);
		objOtpPage.enterOTP(otpValue).clickOnVerifyOTP();

		new HomePage(driver, testInfo).
		clickOnProfileIconForSignedUser().
		clickOnMyReferral().
		clickReferralNetwork().
		validateObjectsMyReferrals().
		validateTotalReferralProfitEarnedValue("0").
		validateFriendsJoinedValue("0").
		clickReferNow().
		clickBackButtonRedirectToReferralNetwork().
		clickBackButton().
		clickBackButton().
		clickOnLogout();

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
		String userId = usersPage.extractUserIdValueFromResultstableFirstRow();
		String userName = usersPage.extractUserNameValueFromResultstableFirstRow();
		usersPage.clickEditButton();
		usersPage.enterEmail(emailAddress);
		usersPage.enterReferralBonus("250.00");
		usersPage.clickSubmitButtonUserForm();

		CashKaroUtility reopenCKApp1 = new CashKaroUtility(udid, port, automationName, deviceName, systemPort);
		driver = reopenCKApp1.launchEarnKaroApp(driver);

		new EKOnboardingPage(driver, testInfo);

		CashKaroUtility objcashKaroutility = new CashKaroUtility(udid, port, automationName, deviceName);
		AppiumDriver<MobileElement> driver2 = objcashKaroutility.launchChrome();
		driver = driver2;

		String url = Utilities.referralLinkGetCode(userId, userName);

		objcashKaroutility.closeAllTabsAndOneTabInChromeBrowser(driver);
		objcashKaroutility.clickAndEnterURLInSearchOrTypeWebAddress(driver, url);
//		objcashKaroutility.clickJoinFreeLink(driver);
//
//		toggleApp();
//		clickEarnkaroApp();
//		toggleApp();
//		clickChromeApp();

		objcashKaroutility.clickJoinFreeLink(driver);

		String emailAddressReferralJoinee = objCashKaroUtility.generateEmail();
		String mobileNumberReferralJoinee = objCashKaroUtility.generateMobileNumber();
		String name = "UserReferral";

		String OtpValue = new JoinFreePage(driver, testInfo).
		validateReferralByUserAtJoinFree(userName).
		enterFullName(name).
		enterEmail(emailAddressReferralJoinee).
		enterPassword().
		enterMobileNumber(mobileNumberReferralJoinee).
		clickOnGetOTPButton(mobileNumberReferralJoinee);

		new OTPPage(driver, testInfo).enterOTP(OtpValue).clickOnVerifyOTP();

		new HomePage(driver, testInfo);

		PaymentOTPPage objPaymentOTP = new HomePage(driver, testInfo).
		clickOnProfileIconForSignedUser().
		clickOnMyEarnings().
		clickProfitTab().
		validateTotalProfitEarnedValue(getTestData(11, "TwoFiftyZeroZero")).
		clickTotalProfitEarnedValueHavingValueMoreThanZero(getTestData(11, "TwoFiftyZeroZero")).
		validateTotalProfitEarnedValue(getTestData(11, "TwoFiftyZeroZero")).
		validatePaidProfitValue(getTestData(6, "Zero")).
		validatePendingProfitValue(getTestData(6, "Zero")).
		validateReferralProfitValue(getTestData(6, "Zero")).
		validateAvailableProfitValue(getTestData(11, "TwoFiftyZeroZero")).
		clickBackButton().
		clickProfitTab().
		validateProfitEarningsValueProfitTab(getTestData(11, "TwoFiftyZeroZero")).
		clickMonthYearDropdown(currentMonthYear).
		validateDate(currentDate).
		
		validateAbsenceOrderAmount().
		validateProfitAmount(getTestData(11, "TwoFifty")).
		validateStatus(getTestData(11, "Confirmed")).
		validateAbsenceExpected(nintyDaysAhead).
		clickReferralTabClick().
		validateReferralEarningsValueReferralTab(getTestData(6, "Zero")).
		clickProfitTab().
		clickOnRequestProfitPaymentButton().
		clickOnPaymentMethodDropDown().
		selectAmazonGiftCardFromDropDown().
		enterAmazonGiftCardEmailID().
		clickOnGETPAID();

		otpValue = objCashKaroUtility.generatePaymentOTP(mobileNumberReferralJoinee);

		objPaymentOTP.
		enterOTP(otpValue).
		clickOnVerifyOTPAndValidateThePaymentSuccessMessage().
		clickProfitTab().
		validateTotalProfitEarnedValue(getTestData(11, "TwoFiftyZeroZero")).
		clickTotalProfitEarnedValueHavingValueMoreThanZero(getTestData(11, "TwoFiftyZeroZero")).
		validateTotalProfitEarnedValue(getTestData(11, "TwoFiftyZeroZero")).
		validatePaidProfitValue(getTestData(11, "TwoFiftyZeroZero")).
		validatePendingProfitValue(getTestData(6, "Zero")).
		validateReferralProfitValue(getTestData(6, "Zero")).
		validateAvailableProfitValue(getTestData(6, "Zero")).
		clickBackButton().
		clickProfitTab().
		validateProfitEarningsValueProfitTab(getTestData(6, "Zero")).
		clickMonthYearDropdown(currentMonthYear).
		validateDate(currentDate).
		
		validateAbsenceOrderAmount().
		validateProfitAmount(getTestData(11, "TwoFifty")).
		validateStatus(getTestData(11, "Requested")).
		validateAbsenceExpected(nintyDaysAhead).
		clickReferralTabClick().
		validateReferralEarningsValueReferralTab(getTestData(6, "Zero"));

		/********************************************************************************/
		/************************************** CASHOUT**********************************/
		/********************************************************************************/

		CashKaroUtility cu3 = new CashKaroUtility(udid, port, automationName, deviceName);
		cu = cu3;
		driver = cu.launchChromeWebView(driver);

		AdminCoreFunction adminFunctions2 = new AdminCoreFunction(driver, testInfo);
		adminFunctions = adminFunctions2;
		adminFunctions.naviagateAdminPage_ThenSelectEarnKaro();
		adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
		adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
		adminFunctions.clickSubmit();

		adminFunctions.clickOnHamburgerMenu();
		adminFunctions.clickCashbackMenu();
		adminFunctions.clickPendingCashoutsSubMenu();

		AdminPendingCashoutsPage PendingCashoutsPage = new AdminPendingCashoutsPage(driver, testInfo);
		PendingCashoutsPage.selectSearchByDropDown("User Email");
		PendingCashoutsPage.enterKeyword(emailAddressReferralJoinee);
		PendingCashoutsPage.clickSubmit();
		PendingCashoutsPage.clickEmail(emailAddressReferralJoinee);
		PendingCashoutsPage.clickCreateCashout();

		driver.switchTo().alert().accept();
		PendingCashoutsPage.validateSuccessMessage();

		/********************************************************************************/

		CashKaroUtility reopenCKAppTwo = new CashKaroUtility(udid, port, automationName, deviceName, systemPort);
		driver = reopenCKAppTwo.launchEarnKaroApp(driver);

		new HomePage(driver, testInfo).
		clickOnProfileIconForSignedUser().
		clickOnMyEarnings().
		clickProfitTab().
		validateTotalProfitEarnedValue(getTestData(11, "TwoFiftyZeroZero")).
		clickTotalProfitEarnedValueHavingValueMoreThanZero(getTestData(11, "TwoFiftyZeroZero")).
		validateTotalProfitEarnedValue(getTestData(11, "TwoFiftyZeroZero")).
		validatePaidProfitValue(getTestData(11, "TwoFiftyZeroZero")).
		validatePendingProfitValue(getTestData(6, "Zero")).
		validateReferralProfitValue(getTestData(6, "Zero")).
		validateAvailableProfitValue(getTestData(6, "Zero")).
		clickBackButton().
		clickProfitTab().
		validateProfitEarningsValueProfitTab(getTestData(6, "Zero")).
		clickMonthYearDropdown(currentMonthYear).
		validateDate(currentDate).
		
		validateAbsenceOrderAmount().
		validateProfitAmount(getTestData(11, "TwoFifty")).
		validateStatus(getTestData(11, "Paid")).
		validateAbsenceExpected(nintyDaysAhead).
		clickReferralTabClick().
		validateReferralEarningsValueReferralTab(getTestData(6, "Zero"));

		CashKaroUtility cuOne = new CashKaroUtility(udid, port, automationName, deviceName);
		driver = cuOne.launchChromeWebView(driver);
		AdminCoreFunction adminFunctionsTwo = new AdminCoreFunction(driver, testInfo);
		adminFunctions = adminFunctionsTwo;
		AdminUsersPage usersPageOne = new AdminUsersPage(driver, testInfo);
		usersPage = usersPageOne;

		adminFunctions.naviagateAdminPage_ThenSelectEarnKaro();
		adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
		adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
		adminFunctions.clickSubmit();

		adminFunctions.clickOnHamburgerMenu();
		adminFunctions.clickUsersMenu();
		adminFunctions.clickUsersSubMenu();
		usersPage.selectSearchByDropDown("User Email");
		usersPage.enterKeyword(emailAddress);
		usersPage.clickSubmitButton();
		usersPage.clickEditButton();
		usersPage.enterEmail(emailAddress);
		usersPage.enterReferralBonus(getTestData(6, "Zero"));
		usersPage.clickSubmitButtonUserForm();

		reportStep("TC009 is  completed", "PASS");

	}

	@Test
	public void earningsPage_TC008() {

		reportStep("TC009 is  started", "INFO");

		String cbStoreName = CashKaroUtility.extractValuesFromStoreCategoryJsonAndLoadTheVariablesForStore("CB_Store_One", 0, "CB_Store_One_Name");
		String cbShortDescription = CashKaroUtility.extractValuesFromStoreCategoryJsonAndLoadTheVariablesForStore("CB_Store_One", 0, "CB_Store_One_App_Short_Description");
		String cbLinkURL = CashKaroUtility.extractValuesFromStoreCategoryJsonAndLoadTheVariablesForStore("CB_Store_One", 0, "CB_Store_One_linkURL");

		String currentDate = Utilities.today_dd_MMM_YYYY_ISTZone().trim();
		String currentMonthYear = Utilities.currentMonthYear().trim();

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo);
		String emailAddress = objCashKaroUtility.generateEmail();
		String mobileNumber = objCashKaroUtility.generateMobileNumber();

		String otpValue = new EKOnboardingPage(driver, testInfo).
		clickOnSignUpButton().
		enterFullName().
		enterEmail(emailAddress).
		enterPassword().
		enterMobileNumber(mobileNumber).
		clickOnGetOTPButton(mobileNumber);

		OTPPage objOtpPage = new OTPPage(driver, testInfo);
		objOtpPage.enterOTP(otpValue).clickOnVerifyOTP();

		new HomePage(driver, testInfo).
		clickOnProfileIconForSignedUser().
		clickOnMyReferral().
		clickReferralNetwork().
		validateObjectsMyReferrals().
		validateTotalReferralProfitEarnedValue("0").
		validateFriendsJoinedValue("0").
		clickReferNow().
		clickBackButtonRedirectToReferralNetwork().
		clickBackButton().
		clickBackButton().
		clickOnLogout();

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
		String userId = usersPage.extractUserIdValueFromResultstableFirstRow();
		String userName = usersPage.extractUserNameValueFromResultstableFirstRow();

		CashKaroUtility reopenCKApp1 = new CashKaroUtility(udid, port, automationName, deviceName, systemPort);
		driver = reopenCKApp1.launchEarnKaroApp(driver);

		new EKOnboardingPage(driver, testInfo);

		CashKaroUtility objcashKaroutility = new CashKaroUtility(udid, port, automationName, deviceName);
		AppiumDriver<MobileElement> driver2 = objcashKaroutility.launchChrome();
		driver = driver2;

		String url = Utilities.referralLinkGetCode(userId, userName);

		objcashKaroutility.closeAllTabsAndOneTabInChromeBrowser(driver);
		objcashKaroutility.clickAndEnterURLInSearchOrTypeWebAddress(driver, url);
//		objcashKaroutility.clickJoinFreeLink(driver);
//
//		toggleApp();
//		clickEarnkaroApp();
//		toggleApp();
//		clickChromeApp();

		objcashKaroutility.clickJoinFreeLink(driver);

		String emailAddressReferralJoinee = objCashKaroUtility.generateEmail();
		String mobileNumberReferralJoinee = objCashKaroUtility.generateMobileNumber();
		String name = "QAengineer";

		String OtpValue = new JoinFreePage(driver, testInfo).
		validateReferralByUserAtJoinFree(userName).
		enterFullName(name).
		enterEmail(emailAddressReferralJoinee).
		enterPassword().
		enterMobileNumber(mobileNumberReferralJoinee).
		clickOnGetOTPButton(mobileNumberReferralJoinee);

		new OTPPage(driver, testInfo).enterOTP(OtpValue).clickOnVerifyOTP();

		new HomePage(driver, testInfo).
		clickOnSearchIcon().
		enterTextIntoTheSearchBar(cbStoreName).
		clickStoreCardShortDescription(cbShortDescription).
		validateStoreShortDescription(cbStoreName, cbShortDescription).
		clickOnAddToShareButton().
		clickOnShareIconWithCount(1).
		clickOnCopyLink();

		String url1 = getClipBoardText();

		CashKaroUtility cuOne = new CashKaroUtility(udid, port, automationName, deviceName);
		this.driver = cuOne.launchChrome();

		cuOne.closeAllTabsAndOneTabInChromeBrowser(driver);
		cuOne.clickAndEnterURLInSearchOrTypeWebAddress(driver, url1);

		new IntermediateRetailerPage(driver, testInfo).validateRetailerPage(cbLinkURL, cbLinkURL);

		CashKaroUtility reopenCKApp = new CashKaroUtility(udid, port, automationName, deviceName, systemPort);
		driver = reopenCKApp.launchEarnKaroApp(driver);

		new HomePage(driver, testInfo).clickOnProfileIconForSignedUser().clickOnLogout();

		CashKaroUtility reopenCKAppOne = new CashKaroUtility(udid, port, automationName, deviceName, systemPort);
		driver = reopenCKAppOne.launchEarnKaroApp(driver);

		new EKOnboardingPage(driver, testInfo).
		clickOnSignInbutton().
		enterUserName(emailAddress).
		enterPassword("ppounds").
		clickSignInButtonForSuccess().
		clickOnProfileIconForSignedUser().
		clickOnMyReferral().
		clickReferralNetwork().
		validateTotalReferralProfitEarnedValue("0.00").
		validateFriendsJoinedValue("1").
		validateDateJoined(currentDate).
		validateReferralName(name).
		validateStatus("Active").
		clickReferNow().
		clickBackButtonRedirectToReferralNetwork().
		clickBackButton().
		clickBackButton().
		clickOnLogout();

		CashKaroUtility cu1 = new CashKaroUtility(udid, port, automationName, deviceName);
		cu = cu1;
		driver = cu.launchChromeWebView(driver);

		AdminCoreFunction adminFunctions1 = new AdminCoreFunction(driver, testInfo);
		adminFunctions = adminFunctions1;
		adminFunctions.naviagateAdminPage_ThenSelectEarnKaro();
		adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
		adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
		adminFunctions.clickSubmit();

		AdminReportsPage reportsPage = new AdminReportsPage(driver, testInfo);
		adminFunctions.clickOnHamburgerMenu();
		adminFunctions.clickOnReports();
		reportsPage.clickExitClickSubMenu();
		reportsPage.clickClear();
		reportsPage.selectSearchByDropDown("User Email");
		reportsPage.enterKeyword(emailAddressReferralJoinee);
		reportsPage.clickSubmit();
		String exitClick = reportsPage.extractExitClickValueFromResultstableFirstRow();

		AdminCashbackPage ProfitPage = new AdminCashbackPage(driver, testInfo);
		adminFunctions.clickOnHamburgerMenu();
		adminFunctions.clickCashbackMenu();
		ProfitPage.clickCashbackSubMenu();
		ProfitPage.clickAddNewButton();
		ProfitPage.enterExitId(exitClick);
		ProfitPage.entertransactionId(exitClick);
		ProfitPage.enterOrderId(exitClick);
		ProfitPage.setOrderDate();
		ProfitPage.enterOrderValue("1000");
		ProfitPage.enterconfirmCommisionNetwork(getTestData(11, "FiveHundered"));
		ProfitPage.entercashback(getTestData(11, "TwoFifty"));
		ProfitPage.selectCashbackTypeFromDropDown(getTestData(11, "Cashback"));
		ProfitPage.selectCashbackStatusFromDropDown(getTestData(11, "Pending"));
		ProfitPage.setPendingDate();
		ProfitPage.enterDetails(exitClick);
		ProfitPage.clickSubmit();

		CashKaroUtility reopenCKAppTwo = new CashKaroUtility(udid, port, automationName, deviceName, systemPort);
		driver = reopenCKAppTwo.launchEarnKaroApp(driver);

		new EKOnboardingPage(driver, testInfo).
		clickOnSignInbutton().
		enterUserName(emailAddressReferralJoinee).
		enterPassword("ppounds").
		clickSignInButtonForSuccess().
		clickOnProfileIconForSignedUser().
		clickOnMyEarnings().
		clickProfitTab().
		validateStatus(getTestData(11, "Pending"));

		CashKaroUtility cuTwo = new CashKaroUtility(udid, port, automationName, deviceName);
		driver = cuTwo.launchChromeWebView(driver);
		AdminCoreFunction adminFunctionsTwo = new AdminCoreFunction(driver, testInfo);
		AdminCashbackPage ProfitPageTwo = new AdminCashbackPage(driver, testInfo);
		adminFunctions = adminFunctionsTwo;
		ProfitPage = ProfitPageTwo;

		adminFunctions.naviagateAdminPage_ThenSelectEarnKaro();
		adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
		adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
		adminFunctions.clickSubmit();

		adminFunctions.clickOnHamburgerMenu();
		adminFunctions.clickCashbackMenu();
		ProfitPage.clickCashbackSubMenu();
		ProfitPage.selectSearchByStatusFromDropDown("Exit ID");
		ProfitPage.enterKeyword(exitClick);
		ProfitPage.selectCashbackStatusFromDropDown(getTestData(11, "Pending"));
		ProfitPage.clickSubmit();
		ProfitPage.clickEdit();
		ProfitPage.selectCashbackStatusFromDropDown(getTestData(11, "Confirmed"));
		ProfitPage.setConfirmDate();
		ProfitPage.clickSubmit();

		CashKaroUtility reopenCKAppAgain = new CashKaroUtility(udid, port, automationName, deviceName, systemPort);
		driver = reopenCKAppAgain.launchEarnKaroApp(driver);
		new HomePage(driver, testInfo).
		clickOnProfileIconForSignedUser().
		clickOnMyEarnings().
		clickProfitTab().
		validateStatus(getTestData(11, "Confirmed")).
		clickBackButton().
		clickOnLogout();

		new EKOnboardingPage(driver, testInfo).
		clickOnSignInbutton().
		enterUserName(emailAddress).
		enterPassword("ppounds").
		clickSignInButtonForSuccess().
		clickOnProfileIconForSignedUser().
		clickOnMyEarnings().
		clickProfitTab().
		validateTotalProfitEarnedValue(getTestData(11, "TwoFive")).
		clickTotalProfitEarnedValueHavingValueMoreThanZero(getTestData(11, "TwoFive")).
		validateTotalProfitEarnedValue(getTestData(11, "TwoFive")).
		validatePaidProfitValue(getTestData(6, "Zero")).
		validatePendingProfitValue(getTestData(6, "Zero")).
		validateReferralProfitValue(getTestData(11, "TwoFive")).
		validateAvailableProfitValue(getTestData(11, "TwoFive")).
		clickBackButton().
		clickProfitTab().
		validateProfitEarningsValueProfitTab(getTestData(6, "Zero")).
		clickOnRequestProfitPaymentButtonForThresholdNotReached().
		validateThresholdNotReached().
		clickReferralTabClick().
		validateReferralEarningsValueReferralTab(getTestData(11, "TwoFive")).
		clickOnRequestProfitPaymentButtonForThresholdNotReached().
		validateThresholdNotReached().
		clickMonthYearDropdown(currentMonthYear).
		validateDateReferralTab(currentDate).
		validateReferralNameReferralTab(name).
		validateProfitReferralTab(getTestData(11, "TwoFive")).
		validateStatusReferralTab(getTestData(11, "Confirmed")).
		clickBackButton().
		clickOnMyReferral().
		clickReferralNetwork().
		validateTotalReferralProfitEarnedValue("25.00").
		validateFriendsJoinedValue("1").
		validateDateJoined(currentDate).
		validateReferralName(name).
		validateStatus("Active").
		clickReferNow().
		clickBackButtonRedirectToReferralNetwork().
		clickBackButton();

		CashKaroUtility cuThree = new CashKaroUtility(udid, port, automationName, deviceName);
		driver = cuThree.launchChromeWebView(driver);

		String newEmailAddress = objCashKaroUtility.generateEmail();

		AdminCoreFunction adminFunctionsThree = new AdminCoreFunction(driver, testInfo);
		adminFunctions = adminFunctionsThree;
		adminFunctions.naviagateAdminPage_ThenSelectEarnKaro();
		adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
		adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
		adminFunctions.clickSubmit();

		AdminUsersPage usersPageThree = new AdminUsersPage(driver, testInfo);
		usersPage = usersPageThree;
		adminFunctions.clickOnHamburgerMenu();
		adminFunctions.clickUsersMenu();
		adminFunctions.clickUsersSubMenu();
		usersPage.selectSearchByDropDown("User Email");
		usersPage.enterKeyword(emailAddressReferralJoinee);
		usersPage.clickSubmitButton();
		usersPage.clickEditButton();
		usersPage.enterEmail(newEmailAddress);
		usersPage.selectStatusFromDropDown("In-Active");
		usersPage.clickSubmitButtonUserForm();

		CashKaroUtility reopenCKAppAgainTwo = new CashKaroUtility(udid, port, automationName, deviceName, systemPort);
		driver = reopenCKAppAgainTwo.launchEarnKaroApp(driver);

		new HomePage(driver, testInfo).
		clickOnProfileIconForSignedUser().
		clickOnMyReferral().
		clickReferralNetwork().
		validateTotalReferralProfitEarnedValue("25.00").
		validateFriendsJoinedValue("1").
		validateDateJoined(currentDate).
		validateReferralName(name).
		validateStatus("In Active").
		clickReferNow().
		clickBackButtonRedirectToReferralNetwork().
		clickBackButton().
		clickBackButton().
		clickOnLogout();

		CashKaroUtility reopenCKApp2 = new CashKaroUtility(udid, port, automationName, deviceName, systemPort);
		driver = reopenCKApp2.launchEarnKaroApp(driver);

		new EKOnboardingPage(driver, testInfo);

		CashKaroUtility objcashKaroutility1 = new CashKaroUtility(udid, port, automationName, deviceName);
		AppiumDriver<MobileElement> driver3 = objcashKaroutility1.launchChrome();
		objcashKaroutility = objcashKaroutility1;
		driver = driver3;

		objcashKaroutility.closeAllTabsAndOneTabInChromeBrowser(driver);
		objcashKaroutility.clickAndEnterURLInSearchOrTypeWebAddress(driver, url);
//		objcashKaroutility.clickJoinFreeLink(driver);
//
//		toggleApp();
//		clickEarnkaroApp();
//		toggleApp();
//		clickChromeApp();

		objcashKaroutility.clickJoinFreeLink(driver);

		String emailAddressAnotherReferralJoinee = objCashKaroUtility.generateEmail();
		String mobileNumberAnotherReferralJoinee = objCashKaroUtility.generateMobileNumber();
		String anotherName = "QAanalyst";

		String OtpValue1 = new JoinFreePage(driver, testInfo).
		validateReferralByUserAtJoinFree(userName).
		enterFullName(anotherName).
		enterEmail(emailAddressAnotherReferralJoinee).
		enterPassword().
		enterMobileNumber(mobileNumberAnotherReferralJoinee).
		clickOnGetOTPButton(mobileNumberAnotherReferralJoinee);

		// OTPPage objOtpPage1 = new OTPPage(driver, testInfo);

		new OTPPage(driver, testInfo).enterOTP(OtpValue1).clickOnVerifyOTP();

		new HomePage(driver, testInfo).
		clickOnSearchIcon().
		enterTextIntoTheSearchBar(cbStoreName).
		clickStoreCardShortDescription(cbShortDescription).
		validateStoreShortDescription(cbStoreName, cbShortDescription).
		clickOnAddToShareButton().
		clickOnShareIconWithCount(1).
		clickOnCopyLink();

		String urlTwo = getClipBoardText(driver);

		CashKaroUtility cu2 = new CashKaroUtility(udid, port, automationName, deviceName);
		AppiumDriver<MobileElement> driver4 = cu2.launchChrome();
		driver = driver4;

		cu.closeAllTabsAndOneTabInChromeBrowser(driver);
		cu.clickAndEnterURLInSearchOrTypeWebAddress(driver, urlTwo);

		new IntermediateRetailerPage(driver, testInfo).validateRetailerPage(cbLinkURL, cbLinkURL);

		CashKaroUtility reopenCKAppThree = new CashKaroUtility(udid, port, automationName, deviceName, systemPort);
		driver = reopenCKAppThree.launchEarnKaroApp(driver);

		new HomePage(driver, testInfo).clickOnProfileIconForSignedUser().clickOnLogout();

		new EKOnboardingPage(driver, testInfo).
		clickOnSignInbutton().
		enterUserName(emailAddress).
		enterPassword("ppounds").
		clickSignInButtonForSuccess().
		clickOnProfileIconForSignedUser().
		clickOnMyReferral().
		clickReferralNetwork().
		validateTotalReferralProfitEarnedValue("25.00").
		validateFriendsJoinedValue("2").
		validateDateJoined(currentDate).
		validateReferralName(anotherName).
		validateStatus("Active").
		validateDateJoined(currentDate).
		validateReferralName(name).
		validateStatus("In Active").
		clickReferNow().
		clickBackButtonRedirectToReferralNetwork().
		clickBackButton().
		clickBackButton().
		clickOnLogout();

		CashKaroUtility cu3 = new CashKaroUtility(udid, port, automationName, deviceName);
		cu = cu3;
		driver = cu.launchChromeWebView(driver);

		AdminCoreFunction adminFunctions2 = new AdminCoreFunction(driver, testInfo);
		adminFunctions = adminFunctions2;
		adminFunctions.naviagateAdminPage_ThenSelectEarnKaro();
		adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
		adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
		adminFunctions.clickSubmit();

		AdminReportsPage reportsPage1 = new AdminReportsPage(driver, testInfo);
		reportsPage = reportsPage1;
		adminFunctions.clickOnHamburgerMenu();
		adminFunctions.clickOnReports();
		reportsPage.clickExitClickSubMenu();
		reportsPage.clickClear();
		reportsPage.selectSearchByDropDown("User Email");
		reportsPage.enterKeyword(emailAddressAnotherReferralJoinee);
		reportsPage.clickSubmit();
		String exitClick1 = reportsPage.extractExitClickValueFromResultstableFirstRow();

		AdminCashbackPage ProfitPage1 = new AdminCashbackPage(driver, testInfo);
		ProfitPage = ProfitPage1;
		adminFunctions.clickOnHamburgerMenu();
		adminFunctions.clickCashbackMenu();
		ProfitPage.clickCashbackSubMenu();
		ProfitPage.clickAddNewButton();
		ProfitPage.enterExitId(exitClick1);
		ProfitPage.entertransactionId(exitClick1);
		ProfitPage.enterOrderId(exitClick1);
		ProfitPage.setOrderDate();
		ProfitPage.enterOrderValue("6000");
		ProfitPage.enterconfirmCommisionNetwork("4000");
		ProfitPage.entercashback("2250");
		ProfitPage.selectCashbackTypeFromDropDown(getTestData(11, "Cashback"));
		ProfitPage.selectCashbackStatusFromDropDown(getTestData(11, "Pending"));
		ProfitPage.setPendingDate();
		ProfitPage.enterDetails(exitClick1);
		ProfitPage.clickSubmit();

		CashKaroUtility reopenCKApp3 = new CashKaroUtility(udid, port, automationName, deviceName, systemPort);
		driver = reopenCKApp3.launchEarnKaroApp(driver);

		new EKOnboardingPage(driver, testInfo).
		clickOnSignInbutton().
		enterUserName(emailAddressAnotherReferralJoinee).
		enterPassword("ppounds").
		clickSignInButtonForSuccess().
		clickOnProfileIconForSignedUser().
		clickOnMyEarnings().
		clickProfitTab().
		validateStatus(getTestData(11, "Pending"));

		CashKaroUtility cuOne1 = new CashKaroUtility(udid, port, automationName, deviceName);
		driver = cuOne1.launchChromeWebView(driver);
		AdminCoreFunction adminFunctionsFour = new AdminCoreFunction(driver, testInfo);
		AdminCashbackPage ProfitPageThree = new AdminCashbackPage(driver, testInfo);
		adminFunctions = adminFunctionsFour;
		ProfitPage = ProfitPageThree;

		adminFunctions.naviagateAdminPage_ThenSelectEarnKaro();
		adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
		adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
		adminFunctions.clickSubmit();

		adminFunctions.clickOnHamburgerMenu();
		adminFunctions.clickCashbackMenu();
		ProfitPage.clickCashbackSubMenu();
		ProfitPage.selectSearchByStatusFromDropDown("Exit ID");
		ProfitPage.enterKeyword(exitClick1);
		ProfitPage.selectCashbackStatusFromDropDown(getTestData(11, "Pending"));
		ProfitPage.clickSubmit();
		ProfitPage.clickEdit();
		ProfitPage.selectCashbackStatusFromDropDown(getTestData(11, "Confirmed"));
		ProfitPage.setConfirmDate();
		ProfitPage.clickSubmit();

		CashKaroUtility reopenCKAppAgain1 = new CashKaroUtility(udid, port, automationName, deviceName, systemPort);
		driver = reopenCKAppAgain1.launchEarnKaroApp(driver);
		new HomePage(driver, testInfo).
		clickOnProfileIconForSignedUser().
		clickOnMyEarnings().
		clickProfitTab().
		validateStatus(getTestData(11, "Confirmed")).
		clickBackButton().
		clickOnLogout();

		CashKaroUtility reopenCKAppAgain2 = new CashKaroUtility(udid, port, automationName, deviceName, systemPort);
		driver = reopenCKAppAgain2.launchEarnKaroApp(driver);

		PaymentOTPPage objPaymentOTP = new EKOnboardingPage(driver, testInfo).
		clickOnSignInbutton().
		enterUserName(emailAddress).
		enterPassword("ppounds").
		clickSignInButtonForSuccess().
		clickOnProfileIconForSignedUser().
		clickOnMyEarnings().
		clickProfitTab().
		validateTotalProfitEarnedValue(getTestData(11, "TwoFive")).
		clickTotalProfitEarnedValueHavingValueMoreThanZero(getTestData(11, "TwoFive")).
		validateTotalProfitEarnedValue(getTestData(11, "TwoFive")).
		validatePaidProfitValue(getTestData(6, "Zero")).
		validatePendingProfitValue(getTestData(6, "Zero")).
		validateReferralProfitValue("250.00").
		validateAvailableProfitValue("250.00").
		clickBackButton().
		clickProfitTab().
		validateProfitEarningsValueProfitTab(getTestData(6, "Zero")).
		clickReferralTabClick().
		validateReferralEarningsValueReferralTab("250.00").
		clickMonthYearDropdown(currentMonthYear).
		validateDateReferralTab(currentDate).
		validateReferralNameReferralTab(name).
		validateProfitReferralTab(getTestData(11, "TwoFive")).
		validateStatusReferralTab(getTestData(11, "Confirmed")).
		clickReferralTabClick().validateReferralEarningsValueReferralTab("250.00").
		clickMonthYearDropdown(currentMonthYear).
		validateDateReferralTab(currentDate).
		validateReferralNameReferralTab(anotherName).
		validateProfitReferralTab("225.00").
		validateStatusReferralTab(getTestData(11, "Confirmed")).
		clickBackButton().
		clickOnMyReferral().
		clickReferralNetwork().
		validateTotalReferralProfitEarnedValue("250.00").
		validateFriendsJoinedValue("2").
		validateDateJoined(currentDate).
		validateReferralName(name).
		validateStatus("In Active").
		validateDateJoined(currentDate).
		validateReferralName(name).
		validateStatus("Active").
		clickReferNow().
		clickBackButtonRedirectToReferralNetwork().
		clickBackButton().
		clickBackButton().
		clickOnMyEarnings().
		clickReferralTabClick().
		clickOnRequestProfitPaymentButton().
		clickOnPaymentMethodDropDown().
		selectBankPaymentNEFTFromDropDown().
		enterBankNEFTPaymentDetails().
		clickOnGETPAID();

		otpValue = objCashKaroUtility.generatePaymentOTP(mobileNumber);

		objPaymentOTP.
		enterOTP(otpValue).
		clickOnVerifyOTPAndValidateThePaymentSuccessMessage().
		clickProfitTab().
		validateTotalProfitEarnedValue("250.00").
		clickTotalProfitEarnedValueHavingValueMoreThanZero("250.00").
		validateTotalProfitEarnedValue("250.00").validatePaidProfitValue("250.00").
		validatePendingProfitValue(getTestData(6, "Zero")).
		validateReferralProfitValue(getTestData(6, "Zero")).
		validateAvailableProfitValue(getTestData(6, "Zero")).
		clickBackButton().
		clickProfitTab().
		validateProfitEarningsValueProfitTab(getTestData(6, "Zero")).
		clickReferralTabClick().
		validateReferralEarningsValueReferralTab(getTestData(6, "Zero")).
		clickMonthYearDropdown(currentMonthYear).
		validateDateReferralTab(currentDate).
		validateReferralNameReferralTab(name).
		validateProfitReferralTab(getTestData(11, "TwoFive")).
		validateStatusReferralTab(getTestData(11, "Requested")).
		clickReferralTabClick().
		validateReferralEarningsValueReferralTab(getTestData(6, "Zero")).
		clickMonthYearDropdown(currentMonthYear).
		validateDateReferralTab(currentDate).
		validateReferralNameReferralTab(anotherName).
		validateProfitReferralTab("225.00").
		validateStatusReferralTab(getTestData(11, "Requested")).
		clickProfitTab().
		validateTotalProfitEarnedValue("250.00").
		clickTotalProfitEarnedValueHavingValueMoreThanZero("250.00").
		validateTotalProfitEarnedValue("250.00").validatePaidProfitValue("250.00").
		validatePendingProfitValue(getTestData(6, "Zero")).
		validateReferralProfitValue(getTestData(6, "Zero")).
		validateAvailableProfitValue(getTestData(6, "Zero")).
		clickBackButton();

		/********************************************************************************/
		/************************************ CASHOUT************************************/
		/********************************************************************************/

		CashKaroUtility cu4 = new CashKaroUtility(udid, port, automationName, deviceName);
		cu = cu4;
		driver = cu.launchChromeWebView(driver);

		AdminCoreFunction adminFunctions3 = new AdminCoreFunction(driver, testInfo);
		adminFunctions = adminFunctions3;
		adminFunctions.naviagateAdminPage_ThenSelectEarnKaro();
		adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
		adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
		adminFunctions.clickSubmit();

		adminFunctions.clickOnHamburgerMenu();
		adminFunctions.clickCashbackMenu();
		adminFunctions.clickPendingCashoutsSubMenu();

		AdminPendingCashoutsPage PendingCashoutsPage = new AdminPendingCashoutsPage(driver, testInfo);
		PendingCashoutsPage.selectSearchByDropDown("User Email");
		PendingCashoutsPage.enterKeyword(emailAddress);
		PendingCashoutsPage.clickSubmit();
		PendingCashoutsPage.clickEmail(emailAddress);
		PendingCashoutsPage.clickCreateCashout();

		driver.switchTo().alert().accept();
		PendingCashoutsPage.validateSuccessMessage();

		/********************************************************************************/

		CashKaroUtility reopenCKAppFour = new CashKaroUtility(udid, port, automationName, deviceName, systemPort);
		driver = reopenCKAppFour.launchEarnKaroApp(driver);

		new HomePage(driver, testInfo).
		clickOnProfileIconForSignedUser().
		clickOnMyEarnings().
		clickProfitTab().
		validateTotalProfitEarnedValue("250.00").
		clickTotalProfitEarnedValueHavingValueMoreThanZero("250.00").
		validateTotalProfitEarnedValue("250.00").
		validatePaidProfitValue("250.00").
		validatePendingProfitValue(getTestData(6, "Zero")).
		validateReferralProfitValue(getTestData(6, "Zero")).
		validateAvailableProfitValue(getTestData(6, "Zero")).
		clickBackButton().
		clickProfitTab().
		validateProfitEarningsValueProfitTab(getTestData(6, "Zero")).
		clickReferralTabClick().
		validateReferralEarningsValueReferralTab(getTestData(6, "Zero")).
		clickMonthYearDropdown(currentMonthYear).
		validateDateReferralTab(currentDate).
		validateReferralNameReferralTab(name).
		validateProfitReferralTab(getTestData(11, "TwoFive")).
		validateStatusReferralTab(getTestData(11, "Paid")).
		clickReferralTabClick().
		validateReferralEarningsValueReferralTab(getTestData(6, "Zero")).
		clickMonthYearDropdown(currentMonthYear).
		validateDateReferralTab(currentDate).
		validateReferralNameReferralTab(anotherName).
		validateProfitReferralTab("225.00").
		validateStatusReferralTab(getTestData(11, "Paid"));

		reportStep("TC009 is  completed", "PASS");

	}

	@Test
	public void earningsPage_TC009() {

		reportStep("TC010 is  started", "INFO");

		String cbStoreName = CashKaroUtility.extractValuesFromStoreCategoryJsonAndLoadTheVariablesForStore("CB_Store_One", 0, "CB_Store_One_Name");
		String cbShortDescription = CashKaroUtility.extractValuesFromStoreCategoryJsonAndLoadTheVariablesForStore("CB_Store_One", 0, "CB_Store_One_App_Short_Description");
		String cbLinkURL = CashKaroUtility.extractValuesFromStoreCategoryJsonAndLoadTheVariablesForStore("CB_Store_One", 0, "CB_Store_One_linkURL");

		String currentDate = Utilities.today_dd_MMM_YYYY_ISTZone().trim();
		String currentMonthYear = Utilities.currentMonthYear().trim();

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo);
		String emailAddress = objCashKaroUtility.generateEmail();
		String mobileNumber = objCashKaroUtility.generateMobileNumber();

		String otpValue = new EKOnboardingPage(driver, testInfo).
		clickOnSignUpButton().
		enterFullName().
		enterEmail(emailAddress).
		enterPassword().
		enterMobileNumber(mobileNumber).
		clickOnGetOTPButton(mobileNumber);

		OTPPage objOtpPage = new OTPPage(driver, testInfo);
		objOtpPage.enterOTP(otpValue).clickOnVerifyOTP();

		new HomePage(driver, testInfo).
		clickOnProfileIconForSignedUser().
		clickOnMyReferral().
		clickReferralNetwork().
		validateObjectsMyReferrals().
		validateTotalReferralProfitEarnedValue("0").
		validateFriendsJoinedValue("0").
		clickReferNow().
		clickBackButtonRedirectToReferralNetwork().
		clickBackButton().
		clickBackButton().
		clickOnLogout();

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
		String userId = usersPage.extractUserIdValueFromResultstableFirstRow();
		String userName = usersPage.extractUserNameValueFromResultstableFirstRow();

		CashKaroUtility reopenCKApp1 = new CashKaroUtility(udid, port, automationName, deviceName, systemPort);
		driver = reopenCKApp1.launchEarnKaroApp(driver);

		new EKOnboardingPage(driver, testInfo);

		CashKaroUtility objcashKaroutility = new CashKaroUtility(udid, port, automationName, deviceName);
		AppiumDriver<MobileElement> driver2 = objcashKaroutility.launchChrome();
		driver = driver2;

		String url = Utilities.referralLinkGetCode(userId, userName);

		objcashKaroutility.closeAllTabsAndOneTabInChromeBrowser(driver);
		objcashKaroutility.clickAndEnterURLInSearchOrTypeWebAddress(driver, url);
//		objcashKaroutility.clickJoinFreeLink(driver);
//
//		toggleApp();
//		clickEarnkaroApp();
//		toggleApp();
//		clickChromeApp();

		objcashKaroutility.clickJoinFreeLink(driver);

		String emailAddressReferralJoinee = objCashKaroUtility.generateEmail();
		String mobileNumberReferralJoinee = objCashKaroUtility.generateMobileNumber();
		String name = "QAengineer";

		String OtpValue = new JoinFreePage(driver, testInfo).
		validateReferralByUserAtJoinFree(userName).
		enterFullName(name).
		enterEmail(emailAddressReferralJoinee).
		enterPassword().
		enterMobileNumber(mobileNumberReferralJoinee).
		clickOnGetOTPButton(mobileNumberReferralJoinee);

		new OTPPage(driver, testInfo).enterOTP(OtpValue).clickOnVerifyOTP();

		new HomePage(driver, testInfo).
		clickOnSearchIcon().
		enterTextIntoTheSearchBar(cbStoreName).
		clickStoreCardShortDescription(cbShortDescription).
		validateStoreShortDescription(cbStoreName, cbShortDescription).
		clickOnAddToShareButton().
		clickOnShareIconWithCount(1).
		clickOnCopyLink();

		String url1 = getClipBoardText();

		CashKaroUtility cuTwo = new CashKaroUtility(udid, port, automationName, deviceName);
		 driver = cuTwo.launchChrome();

		cuTwo.closeAllTabsAndOneTabInChromeBrowser(driver);
		cuTwo.clickAndEnterURLInSearchOrTypeWebAddress(driver, url1);

		new IntermediateRetailerPage(driver, testInfo).validateRetailerPage(cbLinkURL, cbLinkURL);

		CashKaroUtility reopenCKApp = new CashKaroUtility(udid, port, automationName, deviceName, systemPort);
		driver = reopenCKApp.launchEarnKaroApp(driver);

		new HomePage(driver, testInfo).clickOnProfileIconForSignedUser().clickOnLogout();

		new EKOnboardingPage(driver, testInfo).
		clickOnSignInbutton().
		enterUserName(emailAddress).
		enterPassword("ppounds").
		clickSignInButtonForSuccess().
		clickOnProfileIconForSignedUser().
		clickOnMyReferral().
		clickReferralNetwork().
		validateTotalReferralProfitEarnedValue("0.00").
		validateFriendsJoinedValue("1").
		validateDateJoined(currentDate).
		validateReferralName(name).
		validateStatus("Active").
		clickReferNow().
		clickBackButtonRedirectToReferralNetwork().
		clickBackButton().
		clickBackButton().
		clickOnLogout();

		CashKaroUtility cu2 = new CashKaroUtility(udid, port, automationName, deviceName);
		cu = cu2;
		driver = cu.launchChromeWebView(driver);

		AdminCoreFunction adminFunctions1 = new AdminCoreFunction(driver, testInfo);
		adminFunctions = adminFunctions1;
		adminFunctions.naviagateAdminPage_ThenSelectEarnKaro();
		adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
		adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
		adminFunctions.clickSubmit();

		AdminReportsPage reportsPage = new AdminReportsPage(driver, testInfo);
		adminFunctions.clickOnHamburgerMenu();
		adminFunctions.clickOnReports();
		reportsPage.clickExitClickSubMenu();
		reportsPage.clickClear();
		reportsPage.selectSearchByDropDown("User Email");
		reportsPage.enterKeyword(emailAddressReferralJoinee);
		reportsPage.clickSubmit();
		String exitClick = reportsPage.extractExitClickValueFromResultstableFirstRow();

		AdminCashbackPage ProfitPage = new AdminCashbackPage(driver, testInfo);
		adminFunctions.clickOnHamburgerMenu();
		adminFunctions.clickCashbackMenu();
		ProfitPage.clickCashbackSubMenu();
		ProfitPage.clickAddNewButton();
		ProfitPage.enterExitId(exitClick);
		ProfitPage.entertransactionId(exitClick);
		ProfitPage.enterOrderId(exitClick);
		ProfitPage.setOrderDate();
		ProfitPage.enterOrderValue("1000");
		ProfitPage.enterconfirmCommisionNetwork(getTestData(11, "FiveHundered"));
		ProfitPage.entercashback(getTestData(11, "TwoFifty"));
		ProfitPage.selectCashbackTypeFromDropDown(getTestData(11, "Cashback"));
		ProfitPage.selectCashbackStatusFromDropDown(getTestData(11, "Pending"));
		ProfitPage.setPendingDate();
		ProfitPage.enterDetails(exitClick);
		ProfitPage.clickSubmit();

		CashKaroUtility reopenCKAppOne = new CashKaroUtility(udid, port, automationName, deviceName, systemPort);
		driver = reopenCKAppOne.launchEarnKaroApp(driver);

		new EKOnboardingPage(driver, testInfo).
		clickOnSignInbutton().
		enterUserName(emailAddressReferralJoinee).
		enterPassword("ppounds").
		clickSignInButtonForSuccess().
		clickOnProfileIconForSignedUser().
		clickOnMyEarnings().
		clickProfitTab().
		validateStatus(getTestData(11, "Pending"));

		CashKaroUtility cuOne = new CashKaroUtility(udid, port, automationName, deviceName);
		driver = cuOne.launchChromeWebView(driver);
		AdminCoreFunction adminFunctionsTwo = new AdminCoreFunction(driver, testInfo);
		AdminCashbackPage ProfitPageTwo = new AdminCashbackPage(driver, testInfo);
		adminFunctions = adminFunctionsTwo;
		ProfitPage = ProfitPageTwo;

		adminFunctions.naviagateAdminPage_ThenSelectEarnKaro();
		adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
		adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
		adminFunctions.clickSubmit();

		adminFunctions.clickOnHamburgerMenu();
		adminFunctions.clickCashbackMenu();
		ProfitPage.clickCashbackSubMenu();
		ProfitPage.selectSearchByStatusFromDropDown("Exit ID");
		ProfitPage.enterKeyword(exitClick);
		ProfitPage.selectCashbackStatusFromDropDown(getTestData(11, "Pending"));
		ProfitPage.clickSubmit();
		ProfitPage.clickEdit();
		ProfitPage.selectCashbackStatusFromDropDown(getTestData(11, "Confirmed"));
		ProfitPage.setConfirmDate();
		ProfitPage.clickSubmit();

		CashKaroUtility reopenCKAppAgain = new CashKaroUtility(udid, port, automationName, deviceName, systemPort);
		driver = reopenCKAppAgain.launchEarnKaroApp(driver);
		new HomePage(driver, testInfo).
		clickOnProfileIconForSignedUser().
		clickOnMyEarnings().
		clickProfitTab().
		validateStatus(getTestData(11, "Confirmed")).
		clickBackButton().
		clickOnLogout();

		objCashKaroUtility.addCashbackBonus(emailAddress, "225", getTestData(11, "Cashback"));

		PaymentOTPPage objPaymentOTP = new EKOnboardingPage(driver, testInfo).
		clickOnSignInbutton().
		enterUserName(emailAddress).
		enterPassword("ppounds").
		clickSignInButtonForSuccess().
		clickOnProfileIconForSignedUser()
		.clickOnMyEarnings().
		clickProfitTab().
		validateTotalProfitEarnedValue(getTestData(11, "TwoFiftyZeroZero")).
		clickTotalProfitEarnedValueHavingValueMoreThanZero(getTestData(11, "TwoFiftyZeroZero")).
		validateTotalProfitEarnedValue(getTestData(11, "TwoFive")).
		validatePaidProfitValue(getTestData(6, "Zero")).
		validatePendingProfitValue(getTestData(6, "Zero")).
		validateReferralProfitValue(getTestData(11, "TwoFive")).
		validateAvailableProfitValue(getTestData(11, "TwoFiftyZeroZero")).
		clickBackButton().
		clickProfitTab().
		validateProfitEarningsValueProfitTab("225").
		clickMonthYearDropdown(currentMonthYear).
		validateDate(currentDate).
		validateRetailer(getTestData(5, "bonusStore")).
		validateAbsenceOrderAmount().
		validateProfitAmount("225").
		validateStatus(getTestData(11, "Confirmed")).
		clickReferralTabClick().
		validateReferralEarningsValueReferralTab(getTestData(11, "TwoFive")).
		clickMonthYearDropdown(currentMonthYear).
		validateDateReferralTab(currentDate).
		validateReferralNameReferralTab(name).
		validateProfitReferralTab(getTestData(11, "TwoFive")).
		validateStatusReferralTab(getTestData(11, "Confirmed")).
		clickReferralTabClick().
		clickOnRequestProfitPaymentButton().
		clickOnPaymentMethodDropDown().
		selectBankPaymentNEFTFromDropDown().
		enterBankNEFTPaymentDetails().
		clickOnGETPAID();

		otpValue = objCashKaroUtility.generatePaymentOTP(mobileNumber);

		objPaymentOTP.
		enterOTP(otpValue).
		clickOnVerifyOTPAndValidateThePaymentSuccessMessage().
		clickProfitTab().
		validateTotalProfitEarnedValue(getTestData(11, "TwoFiftyZeroZero")).
		clickTotalProfitEarnedValueHavingValueMoreThanZero(getTestData(11, "TwoFiftyZeroZero")).
		validateTotalProfitEarnedValue(getTestData(11, "TwoFive")).
		validatePaidProfitValue(getTestData(11, "TwoFiftyZeroZero")).
		validatePendingProfitValue(getTestData(6, "Zero")).
		validateReferralProfitValue(getTestData(6, "Zero")).
		validateAvailableProfitValue(getTestData(6, "Zero")).
		clickBackButton().
		clickProfitTab().
		validateProfitEarningsValueProfitTab(getTestData(6, "Zero")).
		clickMonthYearDropdown(currentMonthYear).validateDate(currentDate).
		validateRetailer(getTestData(5, "bonusStore")).
		validateAbsenceOrderAmount().
		validateProfitAmount("225").
		validateStatus(getTestData(11, "Requested")).
		clickReferralTabClick().
		validateReferralEarningsValueReferralTab(getTestData(6, "Zero")).
		clickMonthYearDropdown(currentMonthYear).
		validateDateReferralTab(currentDate).
		validateReferralNameReferralTab(name).
		validateProfitReferralTab(getTestData(11, "TwoFive")).
		validateStatusReferralTab(getTestData(11, "Requested"));

		/********************************************************************************/
		/************************************ CASHOUT************************************/
		/********************************************************************************/

		CashKaroUtility cu3 = new CashKaroUtility(udid, port, automationName, deviceName);
		cu = cu3;
		driver = cu.launchChromeWebView(driver);

		AdminCoreFunction adminFunctions2 = new AdminCoreFunction(driver, testInfo);
		adminFunctions = adminFunctions2;
		adminFunctions.naviagateAdminPage_ThenSelectEarnKaro();
		adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
		adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
		adminFunctions.clickSubmit();

		adminFunctions.clickOnHamburgerMenu();
		adminFunctions.clickCashbackMenu();
		adminFunctions.clickPendingCashoutsSubMenu();

		AdminPendingCashoutsPage PendingCashoutsPage = new AdminPendingCashoutsPage(driver, testInfo);
		PendingCashoutsPage.selectSearchByDropDown("User Email");
		PendingCashoutsPage.enterKeyword(emailAddress);
		PendingCashoutsPage.clickSubmit();
		PendingCashoutsPage.clickEmail(emailAddress);
		PendingCashoutsPage.clickCreateCashout();

		driver.switchTo().alert().accept();
		PendingCashoutsPage.validateSuccessMessage();

		/********************************************************************************/

		CashKaroUtility reopenCKAppTwo = new CashKaroUtility(udid, port, automationName, deviceName, systemPort);
		driver = reopenCKAppTwo.launchEarnKaroApp(driver);

		new HomePage(driver, testInfo).
		clickOnProfileIconForSignedUser().
		clickOnMyEarnings().
		clickProfitTab().
		validateTotalProfitEarnedValue(getTestData(11, "TwoFiftyZeroZero")).
		clickTotalProfitEarnedValueHavingValueMoreThanZero(getTestData(11, "TwoFiftyZeroZero")).
		validateTotalProfitEarnedValue(getTestData(11, "TwoFive")).
		validatePaidProfitValue(getTestData(11, "TwoFiftyZeroZero")).
		validatePendingProfitValue(getTestData(6, "Zero")).
		validateReferralProfitValue(getTestData(6, "Zero")).
		validateAvailableProfitValue(getTestData(6, "Zero")).
		clickBackButton().
		clickProfitTab().
		validateProfitEarningsValueProfitTab(getTestData(6, "Zero")).
		clickMonthYearDropdown(currentMonthYear).validateDate(currentDate).
		validateRetailer(getTestData(5, "bonusStore")).validateAbsenceOrderAmount().
		validateProfitAmount("225").
		validateStatus(getTestData(11, "Paid")).
		clickReferralTabClick().
		validateReferralEarningsValueReferralTab(getTestData(6, "Zero")).
		clickMonthYearDropdown(currentMonthYear).
		validateDateReferralTab(currentDate).
		validateReferralNameReferralTab(name).
		validateProfitReferralTab(getTestData(11, "TwoFive")).
		validateStatusReferralTab(getTestData(11, "Paid"));

		reportStep("TC010 is  completed", "PASS");

	}

	@Test
	public void earningsPage_TC010() {

		reportStep("TC013 is  started", "INFO");

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo);

		String cbStoreNameOne = CashKaroUtility.extractValuesFromStoreCategoryJsonAndLoadTheVariablesForStore("CB_Store_One", 0, "CB_Store_One_Name");
		String cbShortDescriptionOne = CashKaroUtility.extractValuesFromStoreCategoryJsonAndLoadTheVariablesForStore("CB_Store_One", 0, "CB_Store_One_App_Short_Description");

		String cbStoreNameTwo = CashKaroUtility.extractValuesFromStoreCategoryJsonAndLoadTheVariablesForStore("CB_Store_Two", 1, "CB_Store_Two_Name");
		String cbShortDescriptionTwo = CashKaroUtility.extractValuesFromStoreCategoryJsonAndLoadTheVariablesForStore("CB_Store_Two", 1, "CB_Store_Two_App_Short_Description");

		String cbStoreNameThree = CashKaroUtility.extractValuesFromStoreCategoryJsonAndLoadTheVariablesForStore("RW_Store_One", 2, "RW_Store_One_Name");
		String cbShortDescriptionThree = CashKaroUtility.extractValuesFromStoreCategoryJsonAndLoadTheVariablesForStore("RW_Store_One", 2, "RW_Store_One_App_Short_Description");

		String cbStoreNameFour = CashKaroUtility.extractValuesFromStoreCategoryJsonAndLoadTheVariablesForStore("RW_Store_Two", 3, "RW_Store_Two_Name");
		String cbShortDescriptionFour = CashKaroUtility.extractValuesFromStoreCategoryJsonAndLoadTheVariablesForStore("RW_Store_Two", 3, "RW_Store_Two_App_Short_Description");

		String cbStoreNameFive = CashKaroUtility.extractValuesFromStoreCategoryJsonAndLoadTheVariablesForStore("N18_Store_One", 4, "N18_Store_One_Name");
		String cbShortDescriptionFive = CashKaroUtility.extractValuesFromStoreCategoryJsonAndLoadTheVariablesForStore("N18_Store_One", 4, "N18_Store_One_App_Short_Description");

		String linkURL = CashKaroUtility.extractValuesFromStoreCategoryJsonAndLoadTheVariablesForStore("CB_Store_One", 0, "CB_Store_One_linkURL");

		String currentDate = Utilities.today_dd_MMM_YYYY_ISTZone().trim();
		String currentMonthYear = Utilities.currentMonthYear().trim();
		String nintyDaysAhead = Utilities.generic_AheadDate_dd_MMM_yyyy(90).trim();

		String emailAddress = objCashKaroUtility.generateEmail();
		String mobileNumber = objCashKaroUtility.generateMobileNumber();

		String otpValue = new EKOnboardingPage(driver, testInfo).
		clickOnSignUpButton().
		enterFullName().
		enterEmail(emailAddress).
		enterPassword().
		enterMobileNumber(mobileNumber).
		clickOnGetOTPButton(mobileNumber);

		OTPPage objOtpPage = new OTPPage(driver, testInfo);
		objOtpPage.
		enterOTP(otpValue).
		clickOnVerifyOTP();

		new HomePage(driver, testInfo).clickOnProfileIconForSignedUser().clickOnLogout();

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
		String userId = usersPage.extractUserIdValueFromResultstableFirstRow();
		String userName = usersPage.extractUserNameValueFromResultstableFirstRow();

		CashKaroUtility reopenCKApp1 = new CashKaroUtility(udid, port, automationName, deviceName, systemPort);
		driver = reopenCKApp1.launchEarnKaroApp(driver);

		new EKOnboardingPage(driver, testInfo);

		CashKaroUtility objcashKaroutility = new CashKaroUtility(udid, port, automationName, deviceName);
		AppiumDriver<MobileElement> driver2 = objcashKaroutility.launchChrome();
		driver = driver2;

		String url = Utilities.referralLinkGetCode(userId, userName);

		objcashKaroutility.closeAllTabsAndOneTabInChromeBrowser(driver);
		objcashKaroutility.clickAndEnterURLInSearchOrTypeWebAddress(driver, url);
//		objcashKaroutility.clickJoinFreeLink(driver);
//
//		toggleApp();
//		clickEarnkaroApp();
//		toggleApp();
//		clickChromeApp();

		objcashKaroutility.clickJoinFreeLink(driver);

		String emailAddressReferralJoinee = objCashKaroUtility.generateEmail();
		String mobileNumberReferralJoinee = objCashKaroUtility.generateMobileNumber();
		String name = "QAengineer";

		String OtpValue = new JoinFreePage(driver, testInfo).
		validateReferralByUserAtJoinFree(userName).
		enterFullName(name).
		enterEmail(emailAddressReferralJoinee).
		enterPassword().
		enterMobileNumber(mobileNumberReferralJoinee).
		clickOnGetOTPButton(mobileNumberReferralJoinee);

		new OTPPage(driver, testInfo).enterOTP(OtpValue).clickOnVerifyOTP();

		new HomePage(driver, testInfo).
		clickOnSearchIcon().
		enterTextIntoTheSearchBar(cbStoreNameOne).
		clickStoreCardShortDescription(cbShortDescriptionOne).
		validateStoreShortDescription(cbStoreNameOne, cbShortDescriptionOne).
		clickOnAddToShareButton().
		clickOnShareIconWithCount(1).
		clickOnCopyLink().
		clickOnclearAllButtonInPopup();
		
		backButton();
		backButton();
		backButton();

		String urlOne = getClipBoardText();

		new HomePage(driver, testInfo).
		clickOnSearchIcon().
		enterTextIntoTheSearchBar(cbStoreNameTwo).
		clickStoreCardShortDescription(cbShortDescriptionTwo).
		validateStoreShortDescription(cbStoreNameTwo, cbShortDescriptionTwo).
		clickOnAddToShareButton().
		clickOnShareIconWithCount(1).
		clickOnCopyLink().
		clickOnclearAllButtonInPopup();
		
		backButton();
		backButton();
		backButton();

		String urlTwo = getClipBoardText();

		new HomePage(driver, testInfo).
		clickOnSearchIcon().
		enterTextIntoTheSearchBar(cbStoreNameThree).
		clickStoreCardShortDescription(cbShortDescriptionThree).
		validateStoreShortDescription(cbStoreNameThree, cbShortDescriptionThree).
		clickOnAddToShareButton().
		clickOnShareIconWithCount(1).
		clickOnCopyLink().
		clickOnclearAllButtonInPopup();
		
		backButton();
		backButton();
		backButton();

		String urlThree = getClipBoardText();

		new HomePage(driver, testInfo).
		clickOnSearchIcon().
		enterTextIntoTheSearchBar(cbStoreNameFour).
		clickStoreCardShortDescription(cbShortDescriptionFour).
		validateStoreShortDescription(cbStoreNameFour, cbShortDescriptionFour).
		clickOnAddToShareButton().
		clickOnShareIconWithCount(1).
		clickOnCopyLink().
		clickOnclearAllButtonInPopup();
		
		backButton();
		backButton();
		backButton();

		String urlFour = getClipBoardText();

		new HomePage(driver, testInfo).
		clickOnSearchIcon().
		enterTextIntoTheSearchBar(cbStoreNameFive).
		clickStoreCardShortDescription(cbShortDescriptionFive).
		validateStoreShortDescription(cbStoreNameFive, cbShortDescriptionFive).
		clickOnAddToShareButton().
		clickOnShareIconWithCount(1).
		clickOnCopyLink().
		clickOnclearAllButtonInPopup();
		
		backButton();
		backButton();
		backButton();

		String urlFive = getClipBoardText();

		CashKaroUtility cu1 = new CashKaroUtility(udid, port, automationName, deviceName);
		driver = cu1.launchChrome();
		cu = cu1;

		cu.closeAllTabsAndOneTabInChromeBrowser(driver);
		cu.clickAndEnterURLInSearchOrTypeWebAddress(driver, urlOne);

		new IntermediateRetailerPage(driver, testInfo).validateRetailerPage(linkURL, linkURL);

		cu.closeAllTabsAndOneTabInChromeBrowser(driver);
		cu.clickAndEnterURLInSearchOrTypeWebAddress(driver, urlTwo);

		new IntermediateRetailerPage(driver, testInfo).validateRetailerPage(linkURL, linkURL);

		cu.closeAllTabsAndOneTabInChromeBrowser(driver);
		cu.clickAndEnterURLInSearchOrTypeWebAddress(driver, urlThree);

		new IntermediateRetailerPage(driver, testInfo).validateRetailerPage(linkURL, linkURL);

		cu.closeAllTabsAndOneTabInChromeBrowser(driver);
		cu.clickAndEnterURLInSearchOrTypeWebAddress(driver, urlFour);

		new IntermediateRetailerPage(driver, testInfo).validateRetailerPage(linkURL, linkURL);

		cu.closeAllTabsAndOneTabInChromeBrowser(driver);
		cu.clickAndEnterURLInSearchOrTypeWebAddress(driver, urlFive);

		new IntermediateRetailerPage(driver, testInfo).validateRetailerPage(linkURL, linkURL);

		CashKaroUtility cu2 = new CashKaroUtility(udid, port, automationName, deviceName);
		cu = cu2;
		driver = cu.launchChromeWebView(driver);

		AdminCoreFunction adminFunctions1 = new AdminCoreFunction(driver, testInfo);
		adminFunctions = adminFunctions1;
		adminFunctions.naviagateAdminPage_ThenSelectEarnKaro();
		adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
		adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
		adminFunctions.clickSubmit();

		AdminReportsPage reportsPage = new AdminReportsPage(driver, testInfo);
		adminFunctions.clickOnHamburgerMenu();
		adminFunctions.clickOnReports();
		reportsPage.clickExitClickSubMenu();
		reportsPage.clickClear();
		reportsPage.selectSearchByDropDown("User Email");
		reportsPage.enterKeyword(emailAddressReferralJoinee);
		reportsPage.clickSubmit();

		String exitClickOne = reportsPage.extractRequiredExitIdUsingStoreNameExitClickType(cbStoreNameFive, getTestData(7, "Normal"));
		String exitClickTwo = reportsPage.extractRequiredExitIdUsingStoreNameExitClickType(cbStoreNameFour, getTestData(7, "Normal"));
		String exitClickThree = reportsPage.extractRequiredExitIdUsingStoreNameExitClickType(cbStoreNameThree, getTestData(7, "Normal"));
		String exitClickFour = reportsPage.extractRequiredExitIdUsingStoreNameExitClickType(cbStoreNameTwo, getTestData(7, "Normal"));
		String exitClickFive = reportsPage.extractRequiredExitIdUsingStoreNameExitClickType(cbStoreNameOne, getTestData(7, "Normal"));

		AdminCashbackPage ProfitPage = new AdminCashbackPage(driver, testInfo);
		adminFunctions.clickOnHamburgerMenu();
		adminFunctions.clickCashbackMenu();
		ProfitPage.clickCashbackSubMenu();

		ProfitPage.clickAddNewButton();
		ProfitPage.enterExitId(exitClickOne);
		ProfitPage.entertransactionId(exitClickOne);
		ProfitPage.enterOrderId(exitClickOne);
		ProfitPage.setOrderDate();
		ProfitPage.enterOrderValue("1000");
		ProfitPage.enterconfirmCommisionNetwork(getTestData(11, "EightHundered"));
		ProfitPage.entercashback("700");
		ProfitPage.selectCashbackTypeFromDropDown(getTestData(11, "Cashback"));
		ProfitPage.selectCashbackStatusFromDropDown(getTestData(11, "Pending"));
		ProfitPage.setPendingDate();
		ProfitPage.enterDetails(exitClickOne);
		ProfitPage.clickSubmit();
		ProfitPage.clickClear();

		ProfitPage.clickAddNewButton();
		ProfitPage.enterExitId(exitClickTwo);
		ProfitPage.entertransactionId(exitClickTwo);
		ProfitPage.enterOrderId(exitClickTwo);
		ProfitPage.setOrderDate();
		ProfitPage.enterOrderValue("1000");
		ProfitPage.enterconfirmCommisionNetwork(getTestData(11, "EightHundered"));
		ProfitPage.entercashback("600");
		ProfitPage.selectCashbackTypeFromDropDown(getTestData(11, "Cashback"));
		ProfitPage.selectCashbackStatusFromDropDown(getTestData(11, "Pending"));
		ProfitPage.setPendingDate();
		ProfitPage.enterDetails(exitClickTwo);
		ProfitPage.clickSubmit();
		ProfitPage.clickClear();

		ProfitPage.clickAddNewButton();
		ProfitPage.enterExitId(exitClickThree);
		ProfitPage.entertransactionId(exitClickThree);
		ProfitPage.enterOrderId(exitClickThree);
		ProfitPage.setOrderDate();
		ProfitPage.enterOrderValue("1000");
		ProfitPage.enterconfirmCommisionNetwork(getTestData(11, "EightHundered"));
		ProfitPage.entercashback("500");
		ProfitPage.selectCashbackTypeFromDropDown(getTestData(11, "Cashback"));
		ProfitPage.selectCashbackStatusFromDropDown(getTestData(11, "Pending"));
		ProfitPage.setPendingDate();
		ProfitPage.enterDetails(exitClickThree);
		ProfitPage.clickSubmit();
		ProfitPage.clickClear();

		ProfitPage.clickAddNewButton();
		ProfitPage.enterExitId(exitClickFour);
		ProfitPage.entertransactionId(exitClickFour);
		ProfitPage.enterOrderId(exitClickFour);
		ProfitPage.setOrderDate();
		ProfitPage.enterOrderValue("1000");
		ProfitPage.enterconfirmCommisionNetwork(getTestData(11, "EightHundered"));
		ProfitPage.entercashback("400");
		ProfitPage.selectCashbackTypeFromDropDown(getTestData(11, "Cashback"));
		ProfitPage.selectCashbackStatusFromDropDown(getTestData(11, "Pending"));
		ProfitPage.setPendingDate();
		ProfitPage.enterDetails(exitClickFour);
		ProfitPage.clickSubmit();
		ProfitPage.clickClear();

		ProfitPage.clickAddNewButton();
		ProfitPage.enterExitId(exitClickFive);
		ProfitPage.entertransactionId(exitClickFive);
		ProfitPage.enterOrderId(exitClickFive);
		ProfitPage.setOrderDate();
		ProfitPage.enterOrderValue("1000");
		ProfitPage.enterconfirmCommisionNetwork(getTestData(11, "EightHundered"));
		ProfitPage.entercashback("300");
		ProfitPage.selectCashbackTypeFromDropDown(getTestData(11, "Cashback"));
		ProfitPage.selectCashbackStatusFromDropDown(getTestData(11, "Pending"));
		ProfitPage.setPendingDate();
		ProfitPage.enterDetails(exitClickFive);
		ProfitPage.clickSubmit();
		ProfitPage.clickClear();

		CashKaroUtility reopenCKAppFour = new CashKaroUtility(udid, port, automationName, deviceName, systemPort);
		driver = reopenCKAppFour.launchEarnKaroApp(driver);

		new HomePage(driver, testInfo).
		clickOnProfileIconForSignedUser().
		clickOnMyEarnings().
		clickProfitTab().
		validateTotalProfitEarnedValue("2500.00").
		clickTotalProfitEarnedValueHavingValueMoreThanZero("2500.00").
		validateTotalProfitEarnedValue("2500.00").
		validatePaidProfitValue(getTestData(6, "Zero")).
		validatePendingProfitValue("2500.00").
		validateReferralProfitValue(getTestData(6, "Zero")).
		validateAvailableProfitValue(getTestData(6, "Zero")).
		clickBackButton().
		clickProfitTab().
		validateProfitEarningsValueProfitTab("0.00").
		clickOnRequestProfitPaymentButtonForThresholdNotReached().
		validateThresholdNotReached().
		clickMonthYearDropdown(currentMonthYear).
		clickViewMore().
		clickProfitTab().
		validateEntireBlockBelowRespectiveTab(currentDate, cbStoreNameOne, getTestData(11, "OneZeroZeroZero"), "300", getTestData(11, "Pending"), nintyDaysAhead).
		validateEntireBlockBelowRespectiveTab(currentDate, cbStoreNameTwo, getTestData(11, "OneZeroZeroZero"), "400", getTestData(11, "Pending"), nintyDaysAhead).
		validateEntireBlockBelowRespectiveTab(currentDate, cbStoreNameThree, getTestData(11, "OneZeroZeroZero"), "500", getTestData(11, "Pending"), nintyDaysAhead).
		validateEntireBlockBelowRespectiveTab(currentDate, cbStoreNameFour, getTestData(11, "OneZeroZeroZero"), "600", getTestData(11, "Pending"), nintyDaysAhead).
		validateEntireBlockBelowRespectiveTab(currentDate, cbStoreNameFive, getTestData(11, "OneZeroZeroZero"), "700", getTestData(11, "Pending"), nintyDaysAhead).
		clickReferralTabClick().
		validateReferralEarningsValueReferralTab(getTestData(6, "Zero")).
		clickOnRequestProfitPaymentButtonForThresholdNotReached().
		validateThresholdNotReached();

		CashKaroUtility cuOne = new CashKaroUtility(udid, port, automationName, deviceName);
		driver = cuOne.launchChromeWebView(driver);
		AdminCoreFunction adminFunctionsTwo = new AdminCoreFunction(driver, testInfo);
		AdminCashbackPage ProfitPageTwo = new AdminCashbackPage(driver, testInfo);
		adminFunctions = adminFunctionsTwo;
		ProfitPage = ProfitPageTwo;

		adminFunctions.naviagateAdminPage_ThenSelectEarnKaro();
		adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
		adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
		adminFunctions.clickSubmit();

		adminFunctions.clickOnHamburgerMenu();
		adminFunctions.clickCashbackMenu();
		ProfitPage.clickCashbackSubMenu();

		ProfitPage.selectSearchByStatusFromDropDown("Exit ID");
		ProfitPage.enterKeyword(exitClickOne);
		ProfitPage.selectCashbackStatusFromDropDown(getTestData(11, "Pending"));
		ProfitPage.clickSubmit();
		ProfitPage.clickEdit();
		ProfitPage.selectCashbackStatusFromDropDown(getTestData(11, "Confirmed"));
		ProfitPage.setConfirmDate();
		ProfitPage.clickSubmit();
		ProfitPage.clickClear();

		ProfitPage.selectSearchByStatusFromDropDown("Exit ID");
		ProfitPage.enterKeyword(exitClickTwo);
		ProfitPage.selectCashbackStatusFromDropDown(getTestData(11, "Pending"));
		ProfitPage.clickSubmit();
		ProfitPage.clickEdit();
		ProfitPage.selectCashbackStatusFromDropDown(getTestData(11, "Confirmed"));
		ProfitPage.setConfirmDate();
		ProfitPage.clickSubmit();
		ProfitPage.clickClear();

		ProfitPage.selectSearchByStatusFromDropDown("Exit ID");
		ProfitPage.enterKeyword(exitClickThree);
		ProfitPage.selectCashbackStatusFromDropDown(getTestData(11, "Pending"));
		ProfitPage.clickSubmit();
		ProfitPage.clickEdit();
		ProfitPage.selectCashbackStatusFromDropDown(getTestData(11, "Confirmed"));
		ProfitPage.setConfirmDate();
		ProfitPage.clickSubmit();
		ProfitPage.clickClear();

		ProfitPage.selectSearchByStatusFromDropDown("Exit ID");
		ProfitPage.enterKeyword(exitClickFour);
		ProfitPage.selectCashbackStatusFromDropDown(getTestData(11, "Pending"));
		ProfitPage.clickSubmit();
		ProfitPage.clickEdit();
		ProfitPage.selectCashbackStatusFromDropDown(getTestData(11, "Confirmed"));
		ProfitPage.setConfirmDate();
		ProfitPage.clickSubmit();
		ProfitPage.clickClear();

		ProfitPage.selectSearchByStatusFromDropDown("Exit ID");
		ProfitPage.enterKeyword(exitClickFive);
		ProfitPage.selectCashbackStatusFromDropDown(getTestData(11, "Pending"));
		ProfitPage.clickSubmit();
		ProfitPage.clickEdit();
		ProfitPage.selectCashbackStatusFromDropDown(getTestData(11, "Confirmed"));
		ProfitPage.setConfirmDate();
		ProfitPage.clickSubmit();
		ProfitPage.clickClear();

		CashKaroUtility reopenCKAppAgain = new CashKaroUtility(udid, port, automationName, deviceName, systemPort);
		driver = reopenCKAppAgain.launchEarnKaroApp(driver);
		PaymentOTPPage objPaymentOTP = new HomePage(driver, testInfo).
		clickOnProfileIconForSignedUser().
		clickOnMyEarnings().
		clickProfitTab().
		validateTotalProfitEarnedValue("2500.00").
		clickTotalProfitEarnedValueHavingValueMoreThanZero("2500.00").
		validateTotalProfitEarnedValue("2500.00").
		validatePaidProfitValue(getTestData(6, "Zero")).
		validatePendingProfitValue(getTestData(6, "Zero")).
		validateReferralProfitValue(getTestData(6, "Zero")).
		validateAvailableProfitValue("2500.00").
		clickBackButton().
		clickProfitTab().
		validateProfitEarningsValueProfitTab("2500.00").
		clickMonthYearDropdown(currentMonthYear).
		clickViewMore().
		clickProfitTab().
		validateEntireBlockBelowRespectiveTabExceptExpected(currentDate, cbStoreNameOne, getTestData(11, "OneZeroZeroZero"), "300", getTestData(11, "Confirmed")).
		validateEntireBlockBelowRespectiveTabExceptExpected(currentDate, cbStoreNameTwo, getTestData(11, "OneZeroZeroZero"), "400", getTestData(11, "Confirmed")).
		validateEntireBlockBelowRespectiveTabExceptExpected(currentDate, cbStoreNameThree, getTestData(11, "OneZeroZeroZero"), "500", getTestData(11, "Confirmed")).
		validateEntireBlockBelowRespectiveTabExceptExpected(currentDate, cbStoreNameFour, getTestData(11, "OneZeroZeroZero"), "600", getTestData(11, "Confirmed")).
		validateEntireBlockBelowRespectiveTabExceptExpected(currentDate, cbStoreNameFive, getTestData(11, "OneZeroZeroZero"), "700", getTestData(11, "Confirmed")).
		clickReferralTabClick().
		validateReferralEarningsValueReferralTab(getTestData(6, "Zero")).
		clickProfitTab().
		clickOnRequestProfitPaymentButton().
		clickOnPaymentMethodDropDown().
		selectBankPaymentNEFTFromDropDown().
		enterBankNEFTPaymentDetails().
		clickOnGETPAID();

		otpValue = objCashKaroUtility.generatePaymentOTP(mobileNumberReferralJoinee);

		objPaymentOTP.
		enterOTP(otpValue).
		clickOnVerifyOTPAndValidateThePaymentSuccessMessage().
		clickProfitTab().
		validateTotalProfitEarnedValue("2500.00").
		clickTotalProfitEarnedValueHavingValueMoreThanZero("2500.00").
		validateTotalProfitEarnedValue("2500.00").
		validatePaidProfitValue("2500.00").
		validatePendingProfitValue(getTestData(6, "Zero")).
		validateReferralProfitValue(getTestData(6, "Zero")).
		validateAvailableProfitValue(getTestData(6, "Zero")).
		clickBackButton().
		clickProfitTab().
		validateProfitEarningsValueProfitTab(getTestData(6, "Zero")).
		clickMonthYearDropdown(currentMonthYear).
		clickViewMore().
		clickProfitTab().
		validateEntireBlockBelowRespectiveTabExceptExpected(currentDate, cbStoreNameOne, getTestData(11, "OneZeroZeroZero"), "300", getTestData(11, "Requested")).
		validateEntireBlockBelowRespectiveTabExceptExpected(currentDate, cbStoreNameTwo, getTestData(11, "OneZeroZeroZero"), "400", getTestData(11, "Requested")).
		validateEntireBlockBelowRespectiveTabExceptExpected(currentDate, cbStoreNameThree, getTestData(11, "OneZeroZeroZero"), "500", getTestData(11, "Requested")).
		validateEntireBlockBelowRespectiveTabExceptExpected(currentDate, cbStoreNameFour, getTestData(11, "OneZeroZeroZero"), "600", getTestData(11, "Requested")).
		validateEntireBlockBelowRespectiveTabExceptExpected(currentDate, cbStoreNameFive, getTestData(11, "OneZeroZeroZero"), "700", getTestData(11, "Requested"));

		/********************************************************************************/
		/************************************ CASHOUT************************************/
		/********************************************************************************/

		CashKaroUtility cu7 = new CashKaroUtility(udid, port, automationName, deviceName);
		cu = cu7;
		driver = cu.launchChromeWebView(driver);

		AdminCoreFunction adminFunctions2 = new AdminCoreFunction(driver, testInfo);
		adminFunctions = adminFunctions2;
		adminFunctions.naviagateAdminPage_ThenSelectEarnKaro();
		adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
		adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
		adminFunctions.clickSubmit();

		adminFunctions.clickOnHamburgerMenu();
		adminFunctions.clickCashbackMenu();
		adminFunctions.clickPendingCashoutsSubMenu();

		AdminPendingCashoutsPage PendingCashoutsPage = new AdminPendingCashoutsPage(driver, testInfo);
		PendingCashoutsPage.selectSearchByDropDown("User Email");
		PendingCashoutsPage.enterKeyword(emailAddressReferralJoinee);
		PendingCashoutsPage.clickSubmit();
		PendingCashoutsPage.clickEmail(emailAddressReferralJoinee);
		PendingCashoutsPage.clickCreateCashout();

		driver.switchTo().alert().accept();
		PendingCashoutsPage.validateSuccessMessage();

		/********************************************************************************/

		CashKaroUtility reopenCKAppSix = new CashKaroUtility(udid, port, automationName, deviceName, systemPort);
		driver = reopenCKAppSix.launchEarnKaroApp(driver);

		new HomePage(driver, testInfo).
		clickOnProfileIconForSignedUser().
		clickOnMyEarnings().
		clickProfitTab().
		validateTotalProfitEarnedValue("2500.00").
		clickTotalProfitEarnedValueHavingValueMoreThanZero("2500.00").
		validateTotalProfitEarnedValue("2500.00").
		validatePaidProfitValue("2500.00").
		validatePendingProfitValue(getTestData(6, "Zero")).
		validateReferralProfitValue(getTestData(6, "Zero")).
		validateAvailableProfitValue(getTestData(6, "Zero")).
		clickBackButton().
		clickProfitTab().
		validateProfitEarningsValueProfitTab(getTestData(6, "Zero")).
		clickMonthYearDropdown(currentMonthYear).
		clickViewMore().
		clickProfitTab().
		validateEntireBlockBelowRespectiveTabExceptExpected(currentDate, cbStoreNameOne, getTestData(11, "OneZeroZeroZero"), "300", getTestData(11, "Paid")).
		validateEntireBlockBelowRespectiveTabExceptExpected(currentDate, cbStoreNameTwo, getTestData(11, "OneZeroZeroZero"), "400", getTestData(11, "Paid")).
		validateEntireBlockBelowRespectiveTabExceptExpected(currentDate, cbStoreNameThree, getTestData(11, "OneZeroZeroZero"), "500", getTestData(11, "Paid")).
		validateEntireBlockBelowRespectiveTabExceptExpected(currentDate, cbStoreNameFour, getTestData(11, "OneZeroZeroZero"), "600", getTestData(11, "Paid")).
		validateEntireBlockBelowRespectiveTabExceptExpected(currentDate, cbStoreNameFive, getTestData(11, "OneZeroZeroZero"), "700", getTestData(11, "Paid")).
		clickReferralTabClick().
		validateReferralEarningsValueReferralTab(getTestData(6, "Zero")).
		clickBackButton().
		clickOnLogout().
		clickOnSignInbutton().
		enterUserName(emailAddress).
		enterPassword("ppounds").
		clickSignInButtonForSuccess().
		clickOnProfileIconForSignedUser().
		clickOnMyEarnings().
		clickProfitTab().
		validateTotalProfitEarnedValue("250.00").
		clickTotalProfitEarnedValueHavingValueMoreThanZero("250.00").
		validateTotalProfitEarnedValue("250.00").
		validatePaidProfitValue("0.00").
		validatePendingProfitValue(getTestData(6, "Zero")).
		validateReferralProfitValue("250.00").
		validateAvailableProfitValue("250.00").
		clickBackButton().
		clickProfitTab().
		validateProfitEarningsValueProfitTab(getTestData(6, "Zero")).
		clickReferralTabClick().
		validateReferralEarningsValueReferralTab("250.00").
		clickMonthYearDropdown(currentMonthYear).
		clickViewMore().
		validateEntireBlockBelowReferralTab(currentDate, name, "30.00", getTestData(11, "Confirmed")).
		validateEntireBlockBelowReferralTab(currentDate, name, "40.00", getTestData(11, "Confirmed")).
		validateEntireBlockBelowReferralTab(currentDate, name, "50.00", getTestData(11, "Confirmed")).
		validateEntireBlockBelowReferralTab(currentDate, name, "50.00", getTestData(11, "Confirmed")).
		validateEntireBlockBelowReferralTab(currentDate, name, "70.00", getTestData(11, "Confirmed")).
		clickProfitTab().
		clickOnRequestProfitPaymentButton().
		clickOnPaymentMethodDropDown().
		selectBankPaymentNEFTFromDropDown().
		enterBankNEFTPaymentDetails().
		clickOnGETPAID();

		otpValue = objCashKaroUtility.generatePaymentOTP(mobileNumber);

		new PaymentOTPPage(driver, testInfo).
		enterOTP(otpValue).
		clickOnVerifyOTPAndValidateThePaymentSuccessMessage().
		clickProfitTab().
		validateTotalProfitEarnedValue("50.00").
		clickTotalProfitEarnedValueHavingValueMoreThanZero("50.00").
		validateTotalProfitEarnedValue("50.00").
		validatePaidProfitValue("50.00").
		validatePendingProfitValue(getTestData(6, "Zero")).
		validateReferralProfitValue(getTestData(6, "Zero")).
		validateAvailableProfitValue(getTestData(6, "Zero")).
		clickBackButton().
		clickReferralTabClick().
		validateReferralEarningsValueReferralTab(getTestData(6, "Zero")).
		clickMonthYearDropdown(currentMonthYear).
		clickViewMore().
		clickReferralTabClick().
		validateEntireBlockBelowReferralTab(currentDate, name, "30.00", getTestData(11, "Requested")).
		validateEntireBlockBelowReferralTab(currentDate, name, "40.00", getTestData(11, "Requested")).
		validateEntireBlockBelowReferralTab(currentDate, name, "50.00", getTestData(11, "Requested")).
		validateEntireBlockBelowReferralTab(currentDate, name, "60.00", getTestData(11, "Requested")).
		validateEntireBlockBelowReferralTab(currentDate, name, "70.00", getTestData(11, "Requested"));

		/********************************************************************************/
		/************************************ CASHOUT************************************/
		/********************************************************************************/

		CashKaroUtility cu8 = new CashKaroUtility(udid, port, automationName, deviceName);
		cu = cu8;
		driver = cu.launchChromeWebView(driver);

		AdminCoreFunction adminFunctions3 = new AdminCoreFunction(driver, testInfo);
		adminFunctions = adminFunctions3;
		adminFunctions.naviagateAdminPage_ThenSelectEarnKaro();
		adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
		adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
		adminFunctions.clickSubmit();

		adminFunctions.clickOnHamburgerMenu();
		adminFunctions.clickCashbackMenu();
		adminFunctions.clickPendingCashoutsSubMenu();

		AdminPendingCashoutsPage PendingCashoutsPage1 = new AdminPendingCashoutsPage(driver, testInfo);
		PendingCashoutsPage = PendingCashoutsPage1;
		PendingCashoutsPage.selectSearchByDropDown("User Email");
		PendingCashoutsPage.enterKeyword(emailAddress);
		PendingCashoutsPage.clickSubmit();
		PendingCashoutsPage.clickEmail(emailAddress);
		PendingCashoutsPage.clickCreateCashout();

		driver.switchTo().alert().accept();
		PendingCashoutsPage.validateSuccessMessage();

		/********************************************************************************/

		CashKaroUtility reopenCKAppSeven = new CashKaroUtility(udid, port, automationName, deviceName, systemPort);
		driver = reopenCKAppSeven.launchEarnKaroApp(driver);

		new HomePage(driver, testInfo).
		clickOnProfileIconForSignedUser().
		clickOnMyEarnings().
		clickProfitTab().
		validateTotalProfitEarnedValue("50.00").
		clickTotalProfitEarnedValueHavingValueMoreThanZero("50.00").
		validateTotalProfitEarnedValue("50.00").
		validatePaidProfitValue("50.00").
		validatePendingProfitValue(getTestData(6, "Zero")).
		validateReferralProfitValue(getTestData(6, "Zero")).
		validateAvailableProfitValue(getTestData(6, "Zero")).
		clickBackButton().
		validateProfitEarningsValueProfitTab(getTestData(6, "Zero")).
		clickReferralTabClick().validateReferralEarningsValueReferralTab(getTestData(6, "Zero")).
		clickMonthYearDropdown(currentMonthYear).
		clickViewMore().
		clickReferralTabClick().
		validateEntireBlockBelowReferralTab(currentDate, name, "30.00", getTestData(11, "Paid")).
		validateEntireBlockBelowReferralTab(currentDate, name, "40.00", getTestData(11, "Paid")).
		validateEntireBlockBelowReferralTab(currentDate, name, "50.00", getTestData(11, "Paid")).
		validateEntireBlockBelowReferralTab(currentDate, name, "60.00", getTestData(11, "Paid")).
		validateEntireBlockBelowReferralTab(currentDate, name, "70.00", getTestData(11, "Paid")).
		clickReferralTabClick().
		validateReferralEarningsValueReferralTab(getTestData(6, "Zero")).
		clickBackButton().
		clickOnLogout().

		/*******************************************************************************************************************/

		reportStep("TC013 is  completed", "PASS");

	}

	@Test
	public void earningsPage_TC011() {

		reportStep("TC011 is  started", "INFO");

		String currentDate = Utilities.today_dd_MMM_YYYY_ISTZone().trim();
		String nintyDaysAhead = Utilities.generic_AheadDate_dd_MMM_yyyy(90).trim();
		String currentMonthYear = Utilities.currentMonthYear().trim();

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo);
		String emailAddress = objCashKaroUtility.generateEmail();
		String mobileNumber = objCashKaroUtility.generateMobileNumber();

		String otpValue = new EKOnboardingPage(driver, testInfo).
		clickOnSignUpButton().
		enterFullName().
		enterEmail(emailAddress).
		enterPassword().
		enterMobileNumber(mobileNumber).
		clickOnGetOTPButton(mobileNumber);

		OTPPage objOtpPage = new OTPPage(driver, testInfo);
		objOtpPage.
		enterOTP(otpValue).
		clickOnVerifyOTP();

		new HomePage(driver, testInfo);

		objCashKaroUtility.addCashbackBonus(emailAddress, getTestData(11, "TwoFifty"), getTestData(11, "Cashback"));

		new HomePage(driver, testInfo).
		clickOnProfileIconForSignedUser().
		clickOnMyEarnings().
		clickProfitTab().
		validateTotalProfitEarnedValue(getTestData(11, "TwoFiftyZeroZero")).
		clickTotalProfitEarnedValueHavingValueMoreThanZero(getTestData(11, "TwoFiftyZeroZero")).
		validateTotalProfitEarnedValue(getTestData(11, "TwoFiftyZeroZero")).
		validatePaidProfitValue(getTestData(6, "Zero")).
		validatePendingProfitValue(getTestData(6, "Zero")).
		validateReferralProfitValue(getTestData(6, "Zero")).
		validateAvailableProfitValue(getTestData(11, "TwoFiftyZeroZero")).
		clickAboutProfitStatus().
		validateAboutProfitStatusText().
		clickBackButton().
		clickProfitTab().
		validateProfitEarningsValueProfitTab(getTestData(11, "TwoFiftyZeroZero")).
		clickMonthYearDropdown(currentMonthYear).
		validateDate(currentDate).
		validateRetailer(getTestData(5, "bonusStore")).
		validateAbsenceOrderAmount().
		validateProfitAmount(getTestData(11, "TwoFifty")).
		validateStatus(getTestData(11, "Confirmed")).
		validateAbsenceExpected(nintyDaysAhead);

		reportStep("TC011 is  ended", "INFO");

	}

}