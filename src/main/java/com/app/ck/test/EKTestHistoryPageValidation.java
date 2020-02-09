package com.app.ck.test;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.app.ck.base.WrapperMethods;
import com.app.ck.pages.EKOnboardingPage;
import com.app.ck.pages.HomePage;
import com.app.ck.pages.IntermediateRetailerPage;
import com.app.ck.pages.JoinFreePage;
import com.app.ck.pages.OTPPage;
import com.app.ck.pages.SignedInProfilePage;
import com.app.ck.pages.StoreCategoryPage;
import com.app.ck.pages.StoreDetailPage;
import com.app.ck.pages.WhatsAppUtility;
import com.app.ck.pages.admin.AdminCashbackPage;
import com.app.ck.pages.admin.AdminCoreFunction;
import com.app.ck.pages.admin.AdminReportsPage;
import com.app.ck.pages.admin.AdminStoresPage;
import com.app.ck.pages.admin.ProductBrowserEditModePage;
import com.app.ck.utilities.CashKaroUtility;
import com.app.ck.utilities.Utilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.PressesKey;

public class EKTestHistoryPageValidation extends WrapperMethods {

	/*******************************************************************/
	/********              			HISTORY    		     	 ***********/
	/*******************************************************************/

	@Test
	public void HistoryPage_TC001() {
		
		reportStep("TC001 is  started", "INFO");

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
		clickOnProfileIconForSignedUser().
		clickonHistory().
		validateObjectsHistoryPage().
		clickBackButton();
		
		new HomePage(driver, testInfo).
		clickOnProfileIconForSignedUser().
		clickOnLogout();

	}

	@Test
	public void HistoryPage_TC002() {
		
		reportStep("TC002 is  started", "INFO");

		String cbStoreName = CashKaroUtility.extractValuesFromStoreCategoryJsonAndLoadTheVariablesForStore("CB_Store_One", 0, "CB_Store_One_Name");
		String cbShortDescription = CashKaroUtility.extractValuesFromStoreCategoryJsonAndLoadTheVariablesForStore("CB_Store_One", 0, "CB_Store_One_App_Short_Description");

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
		clickOnProfileIconForSignedUser().
		clickonHistory().
		validateObjectsHistoryPage().
		clickBackButton();
		
		new HomePage(driver, testInfo).
		clickOnSearchIcon().
		enterTextIntoTheSearchBar(cbStoreName).
		clickStoreCardShortDescription(cbShortDescription).
		validateStoreShortDescription(cbStoreName, cbShortDescription).
		clickOnAddToShareButton().
		clickOnShareIconWithCount(1).
		clickOnCopyLink();
		
		backButton();
		backButton();
		backButton();
		backButton();
		
		new HomePage(driver, testInfo).
		clickOnProfileIconForSignedUser();

		new SignedInProfilePage(driver, testInfo).
		clickonHistory().
		validateObjectsHistoryPage().
		clickBackButton();
		
		new HomePage(driver, testInfo).
		clickOnProfileIconForSignedUser().
		clickOnLogout();

	}

	@Test
	public void HistoryPage_TC003() {
		
		reportStep("TC003 is  started", "INFO");

		String cbStoreName = CashKaroUtility.extractValuesFromStoreCategoryJsonAndLoadTheVariablesForStore("CB_Store_One", 0, "CB_Store_One_Name");
		String cbShortDescription = CashKaroUtility.extractValuesFromStoreCategoryJsonAndLoadTheVariablesForStore("CB_Store_One", 0, "CB_Store_One_App_Short_Description");
		String cbLinkURL = CashKaroUtility.extractValuesFromStoreCategoryJsonAndLoadTheVariablesForStore("CB_Store_One", 0, "CB_Store_One_linkURL");

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo); 
		String emailAddress = objCashKaroUtility.generateEmail();
		String mobileNumber = objCashKaroUtility.generateMobileNumber();	
		String presentDate = Utilities.today_dd_MMMM_YYYY_ISTZone().trim();
		String currentDate = presentDate.replace("-", " ");
		String currentMonthYear = Utilities.currentMonthYear().trim();
		
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
		clickOnProfileIconForSignedUser().
		clickonHistory().
		validateObjectsHistoryPage().
		clickBackButton();
		
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
			
		new IntermediateRetailerPage(driver, testInfo).
		validateRetailerPage(cbLinkURL, cbLinkURL);
		
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
		String exitClickIDOne = reportsPage.extractRequiredExitIdUsingStoreNameExitClickType(cbStoreName, getTestData(7, "Shared"));
		
		CashKaroUtility reopenCKApp = new CashKaroUtility(udid, port, automationName, deviceName,systemPort);
		driver  = reopenCKApp.launchEarnKaroApp(driver);
		
		new HomePage(driver, testInfo).
		clickOnProfileIconForSignedUser().
		clickonHistory().
		validateMonthYear(currentMonthYear).
		validateEntireBlock(exitClickIDOne, currentDate, cbStoreName, "1", "0", "0");

		CashKaroUtility cuTwo = new CashKaroUtility(udid, port, automationName, deviceName);
		AppiumDriver<MobileElement> driver2 = cuTwo.launchChrome();
		driver = driver2;
		
		cu.closeAllTabsAndOneTabInChromeBrowser(driver);
		cu.clickAndEnterURLInSearchOrTypeWebAddress(driver, url);
			
		new IntermediateRetailerPage(driver, testInfo).
		validateRetailerPage(cbLinkURL, cbLinkURL);
	
		CashKaroUtility reopenCKAppOne = new CashKaroUtility(udid, port, automationName, deviceName,systemPort);
		reopenCKApp = reopenCKAppOne;
		driver  = reopenCKApp.launchEarnKaroApp(driver);
		
		new HomePage(driver, testInfo).
		clickOnProfileIconForSignedUser().
		clickonHistory().
		validateMonthYear(currentMonthYear).
		validateEntireBlock(exitClickIDOne, currentDate, cbStoreName, "2", "0", "0");

		CashKaroUtility cuThree = new CashKaroUtility(udid, port, automationName, deviceName);
		cuOne = cuThree;
		driver = cuOne.launchChromeWebView(driver);
		
		AdminCoreFunction adminFunctionsOne = new AdminCoreFunction(driver, testInfo);	
		adminFunctions = adminFunctionsOne;
		adminFunctions.naviagateAdminPage_ThenSelectEarnKaro();
		adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
		adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
		adminFunctions.clickSubmit();
		
		AdminReportsPage reportsPageOne = new AdminReportsPage(driver, testInfo);
		reportsPage = reportsPageOne;
		adminFunctions.clickOnHamburgerMenu();
		adminFunctions.clickOnReports();
		reportsPage.clickExitClickSubMenu();
		reportsPage.clickClear();
		reportsPage.selectSearchByDropDown("User Email");
		reportsPage.enterKeyword(emailAddress);
		reportsPage.clickSubmit();
		String exitClickIdOne = reportsPage.extractRequiredExitIdUsingStoreNameExitClickType(cbStoreName, getTestData(7, "Normal"), 1);
		String exitClickIdTwo = reportsPage.extractRequiredExitIdUsingStoreNameExitClickType(cbStoreName, getTestData(7, "Normal"), 2);
		
		AdminCashbackPage ProfitPage = new AdminCashbackPage(driver, testInfo);
		adminFunctions.clickOnHamburgerMenu();
		adminFunctions.clickCashbackMenu();
		ProfitPage.clickCashbackSubMenu();
		ProfitPage.clickAddNewButton();
		ProfitPage.enterExitId(exitClickIdOne);
		ProfitPage.entertransactionId(exitClickIdOne);
		ProfitPage.enterOrderId(exitClickIdOne);
		ProfitPage.setOrderDate();
		ProfitPage.enterOrderValue("1000");
		ProfitPage.enterconfirmCommisionNetwork(getTestData(11, "FiveHundered"));
		ProfitPage.entercashback(getTestData(11, "TwoFifty"));
		ProfitPage.selectCashbackTypeFromDropDown(getTestData(11, "Cashback"));
		ProfitPage.selectCashbackStatusFromDropDown(getTestData(11, "Pending"));
		ProfitPage.setPendingDate();
		ProfitPage.enterDetails(exitClickIdOne);
		ProfitPage.clickSubmit();

		ProfitPage.clickAddNewButton();
		ProfitPage.enterExitId(exitClickIdTwo);
		ProfitPage.entertransactionId(exitClickIdTwo);
		ProfitPage.enterOrderId(exitClickIdTwo);
		ProfitPage.setOrderDate();
		ProfitPage.enterOrderValue("1000");
		ProfitPage.enterconfirmCommisionNetwork(getTestData(11, "FiveHundered"));
		ProfitPage.entercashback(getTestData(11, "TwoFifty"));
		ProfitPage.selectCashbackTypeFromDropDown(getTestData(11, "Cashback"));
		ProfitPage.selectCashbackStatusFromDropDown(getTestData(11, "Pending"));
		ProfitPage.setPendingDate();
		ProfitPage.enterDetails(exitClickIdTwo);
		ProfitPage.clickSubmit();

		CashKaroUtility reopenCKAppTwo = new CashKaroUtility(udid, port, automationName, deviceName,systemPort);
		reopenCKApp = reopenCKAppTwo;
		driver  = reopenCKApp.launchEarnKaroApp(driver);
		
		new HomePage(driver, testInfo).
		clickOnProfileIconForSignedUser().
		clickonHistory().
		validateMonthYear(currentMonthYear).
		validateEntireBlock(exitClickIDOne, currentDate, cbStoreName, "2", "2", "500.00");

		CashKaroUtility cuFour = new CashKaroUtility(udid, port, automationName, deviceName);
		cuOne = cuFour;
		driver = cuOne.launchChromeWebView(driver);
		
		AdminCoreFunction adminFunctionsTwo = new AdminCoreFunction(driver, testInfo);	
		adminFunctions = adminFunctionsTwo;
		adminFunctions.naviagateAdminPage_ThenSelectEarnKaro();
		adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
		adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
		adminFunctions.clickSubmit();
		
		AdminCashbackPage ProfitPageOne = new AdminCashbackPage(driver, testInfo);
		ProfitPage = ProfitPageOne;
		adminFunctions.clickOnHamburgerMenu();
		adminFunctions.clickCashbackMenu();
		ProfitPage.clickCashbackSubMenu();
		ProfitPage.selectSearchByStatusFromDropDown("Exit ID");
		ProfitPage.enterKeyword(exitClickIdOne);
		ProfitPage.selectCashbackStatusFromDropDown(getTestData(11, "Pending"));
		ProfitPage.clickSubmit();
		ProfitPage.clickEdit();
		ProfitPage.selectCashbackStatusFromDropDown(getTestData(11, "Confirmed"));
		ProfitPage.setConfirmDate();
		ProfitPage.clickSubmit();

		adminFunctions.clickOnHamburgerMenu();
		ProfitPage.clickCashbackSubMenu();
		ProfitPage.selectSearchByStatusFromDropDown("Exit ID");
		ProfitPage.enterKeyword(exitClickIdTwo);
		ProfitPage.selectCashbackStatusFromDropDown(getTestData(11, "Pending"));
		ProfitPage.clickSubmit();
		ProfitPage.clickEdit();
		ProfitPage.selectCashbackStatusFromDropDown(getTestData(11, "Cancelled"));
		ProfitPage.setFailDate();
		ProfitPage.clickSubmit();

		CashKaroUtility reopenCKAppThree = new CashKaroUtility(udid, port, automationName, deviceName,systemPort);
		reopenCKApp = reopenCKAppThree;
		driver  = reopenCKApp.launchEarnKaroApp(driver);
		
		new HomePage(driver, testInfo).
		clickOnProfileIconForSignedUser().
		clickonHistory().
		validateMonthYear(currentMonthYear).
		validateEntireBlock(exitClickIDOne, currentDate, cbStoreName, "2", "2", "250.00");
		
		CashKaroUtility cu1 = new CashKaroUtility(udid, port, automationName, deviceName);
		cu = cu1;
		AppiumDriver<MobileElement> driver3 = cu.launchChrome();
		driver = driver3;
		
		cu.closeAllTabsAndOneTabInChromeBrowser(driver);
		cu.clickAndEnterURLInSearchOrTypeWebAddress(driver, url);
			
		new IntermediateRetailerPage(driver, testInfo).
		validateRetailerPage(cbLinkURL, cbLinkURL);
	
		CashKaroUtility reopenCKAppFour = new CashKaroUtility(udid, port, automationName, deviceName,systemPort);
		reopenCKApp = reopenCKAppFour;
		driver  = reopenCKApp.launchEarnKaroApp(driver);
		
		new HomePage(driver, testInfo).
		clickOnProfileIconForSignedUser().
		clickonHistory().
		validateMonthYear(currentMonthYear).
		validateEntireBlock(exitClickIDOne, currentDate, cbStoreName, "3", "2", "250.00");

		CashKaroUtility cuFive = new CashKaroUtility(udid, port, automationName, deviceName);
		cuOne = cuFive;
		driver = cuOne.launchChromeWebView(driver);
		
		AdminCoreFunction adminFunctionsThree = new AdminCoreFunction(driver, testInfo);	
		adminFunctions = adminFunctionsThree;
		adminFunctions.naviagateAdminPage_ThenSelectEarnKaro();
		adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
		adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
		adminFunctions.clickSubmit();
		
		AdminReportsPage reportsPageTwo = new AdminReportsPage(driver, testInfo);
		reportsPage = reportsPageTwo;
		adminFunctions.clickOnHamburgerMenu();
		adminFunctions.clickOnReports();
		reportsPage.clickExitClickSubMenu();
		reportsPage.clickClear();
		reportsPage.selectSearchByDropDown("User Email");
		reportsPage.enterKeyword(emailAddress);
		reportsPage.clickSubmit();
		String exitClickIDThree = reportsPage.extractRequiredExitIdUsingStoreNameExitClickType(cbStoreName, getTestData(7, "Normal"));
		
		AdminCashbackPage ProfitPageThree = new AdminCashbackPage(driver, testInfo);
		ProfitPage = ProfitPageThree;
		adminFunctions.clickOnHamburgerMenu();
		adminFunctions.clickCashbackMenu();
		ProfitPage.clickCashbackSubMenu();
		ProfitPage.clickAddNewButton();
		ProfitPage.enterExitId(exitClickIDThree);
		ProfitPage.entertransactionId(exitClickIDThree);
		ProfitPage.enterOrderId(exitClickIDThree);
		ProfitPage.setOrderDate();
		ProfitPage.enterOrderValue("1000");
		ProfitPage.enterconfirmCommisionNetwork(getTestData(11, "FiveHundered"));
		ProfitPage.entercashback(getTestData(11, "TwoFifty"));
		ProfitPage.selectCashbackTypeFromDropDown(getTestData(11, "Cashback"));
		ProfitPage.selectCashbackStatusFromDropDown(getTestData(11, "Pending"));
		ProfitPage.setPendingDate();
		ProfitPage.enterDetails(exitClickIDThree);
		ProfitPage.clickSubmit();
		
		CashKaroUtility reopenCKAppFive = new CashKaroUtility(udid, port, automationName, deviceName,systemPort);
		reopenCKApp = reopenCKAppFive;
		driver  = reopenCKApp.launchEarnKaroApp(driver);
		
		new HomePage(driver, testInfo).
		clickOnProfileIconForSignedUser().
		clickonHistory().
		validateMonthYear(currentMonthYear).
		validateEntireBlock(exitClickIDOne, currentDate, cbStoreName, "3", "3", "500.00");

		CashKaroUtility cuSix = new CashKaroUtility(udid, port, automationName, deviceName);
		cuOne = cuSix;
		driver = cuOne.launchChromeWebView(driver);
		
		AdminCoreFunction adminFunctionsFour = new AdminCoreFunction(driver, testInfo);	
		adminFunctions = adminFunctionsFour;
		adminFunctions.naviagateAdminPage_ThenSelectEarnKaro();
		adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
		adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
		adminFunctions.clickSubmit();
		
		AdminCashbackPage ProfitPageFour = new AdminCashbackPage(driver, testInfo);
		ProfitPage = ProfitPageFour;
		adminFunctions.clickOnHamburgerMenu();
		adminFunctions.clickCashbackMenu();
		ProfitPage.clickCashbackSubMenu();
		ProfitPage.selectSearchByStatusFromDropDown("Exit ID");
		ProfitPage.enterKeyword(exitClickIDThree);
		ProfitPage.selectCashbackStatusFromDropDown(getTestData(11, "Pending"));
		ProfitPage.clickSubmit();
		ProfitPage.clickEdit();
		ProfitPage.selectCashbackStatusFromDropDown(getTestData(11, "Confirmed"));
		ProfitPage.setConfirmDate();
		ProfitPage.clickSubmit();

		CashKaroUtility reopenCKAppSix = new CashKaroUtility(udid, port, automationName, deviceName,systemPort);
		reopenCKApp = reopenCKAppSix;
		driver  = reopenCKApp.launchEarnKaroApp(driver);
		
		new HomePage(driver, testInfo).
		clickOnProfileIconForSignedUser().
		clickonHistory().
		validateMonthYear(currentMonthYear).
		validateEntireBlock(exitClickIDOne, currentDate, cbStoreName, "3", "3", "500.00");

	}

	@Test
	public void HistoryPage_TC004() {

		reportStep("TC004 is  started", "INFO");

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

		String presentDate = Utilities.today_dd_MMMM_YYYY_ISTZone().trim();
		String currentDate = presentDate.replace("-", " ");
		String currentMonthYear = Utilities.currentMonthYear().trim();

		String emailAddress = objCashKaroUtility.generateEmail();
		String mobileNumber = objCashKaroUtility.generateMobileNumber();

		String otpValue = new EKOnboardingPage(driver, testInfo).
		clickOnSignUpButton().
		enterFullName().
		enterEmail(emailAddress).
		enterPassword().
		enterMobileNumber(mobileNumber).
		clickOnGetOTPButton(mobileNumber);

		new OTPPage(driver, testInfo).enterOTP(otpValue).clickOnVerifyOTP();

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

		CashKaroUtility cu = new CashKaroUtility(udid, port, automationName, deviceName);
		driver = cu.launchChrome();

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

		CashKaroUtility cu1 = new CashKaroUtility(udid, port, automationName, deviceName);
		cu = cu1;
		driver = cu.launchChromeWebView(driver);

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

		String exitClickSharedOne = reportsPage.extractRequiredExitIdUsingStoreNameExitClickType(cbStoreNameOne, getTestData(7, "Shared"));
		String exitClickSharedTwo = reportsPage.extractRequiredExitIdUsingStoreNameExitClickType(cbStoreNameTwo, getTestData(7, "Shared"));
		String exitClickSharedThree = reportsPage.extractRequiredExitIdUsingStoreNameExitClickType(cbStoreNameThree, getTestData(7, "Shared"));
		String exitClickSharedFour = reportsPage.extractRequiredExitIdUsingStoreNameExitClickType(cbStoreNameFour, getTestData(7, "Shared"));
		String exitClickSharedFive = reportsPage.extractRequiredExitIdUsingStoreNameExitClickType(cbStoreNameFive, getTestData(7, "Shared"));

		String exitClickOne = reportsPage.extractRequiredExitIdUsingStoreNameExitClickType(cbStoreNameOne, getTestData(7, "Normal"));
		String exitClickTwo = reportsPage.extractRequiredExitIdUsingStoreNameExitClickType(cbStoreNameTwo, getTestData(7, "Normal"));
		String exitClickThree = reportsPage.extractRequiredExitIdUsingStoreNameExitClickType(cbStoreNameThree, getTestData(7, "Normal"));
		String exitClickFour = reportsPage.extractRequiredExitIdUsingStoreNameExitClickType(cbStoreNameFour, getTestData(7, "Normal"));
		String exitClickFive = reportsPage.extractRequiredExitIdUsingStoreNameExitClickType(cbStoreNameFive, getTestData(7, "Normal"));

		CashKaroUtility reopenCKApp = new CashKaroUtility(udid, port, automationName, deviceName, systemPort);
		driver = reopenCKApp.launchEarnKaroApp(driver);

		new HomePage(driver, testInfo).
		clickOnProfileIconForSignedUser().
		clickonHistory().
		validateMonthYear(currentMonthYear).clickViewMore().
		validateEntireBlock(exitClickSharedFive, currentDate, cbStoreNameFive, "1", "0", "0").
		validateEntireBlock(exitClickSharedFour, currentDate, cbStoreNameFour, "1", "0", "0").
		validateEntireBlock(exitClickSharedThree, currentDate, cbStoreNameThree, "1", "0", "0").
		validateEntireBlock(exitClickSharedTwo, currentDate, cbStoreNameTwo, "1", "0", "0").
		validateEntireBlock(exitClickSharedOne, currentDate, cbStoreNameOne, "1", "0", "0");

		CashKaroUtility cu2 = new CashKaroUtility(udid, port, automationName, deviceName);
		cu = cu2;
		driver = cu.launchChromeWebView(driver);

		AdminCoreFunction adminFunctionsOne = new AdminCoreFunction(driver, testInfo);
		adminFunctions = adminFunctionsOne;
		adminFunctions.naviagateAdminPage_ThenSelectEarnKaro();
		adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
		adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
		adminFunctions.clickSubmit();

		AdminCashbackPage cashbackPage = new AdminCashbackPage(driver, testInfo);
		adminFunctions.clickOnHamburgerMenu();
		adminFunctions.clickCashbackMenu();
		cashbackPage.clickCashbackSubMenu();

		cashbackPage.clickAddNewButton();
		cashbackPage.enterExitId(exitClickOne);
		cashbackPage.entertransactionId(exitClickOne);
		cashbackPage.enterOrderId(exitClickOne);
		cashbackPage.setOrderDate();
		cashbackPage.enterOrderValue("1000");
		cashbackPage.enterconfirmCommisionNetwork(getTestData(11, "EightHundered"));
		cashbackPage.entercashback("700");
		cashbackPage.selectCashbackTypeFromDropDown(getTestData(11, "Cashback"));
		cashbackPage.selectCashbackStatusFromDropDown(getTestData(11, "Pending"));
		cashbackPage.setPendingDate();
		cashbackPage.enterDetails(exitClickOne);
		cashbackPage.clickSubmit();
		cashbackPage.clickClear();

		cashbackPage.clickAddNewButton();
		cashbackPage.enterExitId(exitClickTwo);
		cashbackPage.entertransactionId(exitClickTwo);
		cashbackPage.enterOrderId(exitClickTwo);
		cashbackPage.setOrderDate();
		cashbackPage.enterOrderValue("1000");
		cashbackPage.enterconfirmCommisionNetwork(getTestData(11, "EightHundered"));
		cashbackPage.entercashback("600");
		cashbackPage.selectCashbackTypeFromDropDown(getTestData(11, "Cashback"));
		cashbackPage.selectCashbackStatusFromDropDown(getTestData(11, "Pending"));
		cashbackPage.setPendingDate();
		cashbackPage.enterDetails(exitClickTwo);
		cashbackPage.clickSubmit();
		cashbackPage.clickClear();

		cashbackPage.clickAddNewButton();
		cashbackPage.enterExitId(exitClickThree);
		cashbackPage.entertransactionId(exitClickThree);
		cashbackPage.enterOrderId(exitClickThree);
		cashbackPage.setOrderDate();
		cashbackPage.enterOrderValue("1000");
		cashbackPage.enterconfirmCommisionNetwork(getTestData(11, "EightHundered"));
		cashbackPage.entercashback("500");
		cashbackPage.selectCashbackTypeFromDropDown(getTestData(11, "Cashback"));
		cashbackPage.selectCashbackStatusFromDropDown(getTestData(11, "Pending"));
		cashbackPage.setPendingDate();
		cashbackPage.enterDetails(exitClickThree);
		cashbackPage.clickSubmit();
		cashbackPage.clickClear();

		cashbackPage.clickAddNewButton();
		cashbackPage.enterExitId(exitClickFour);
		cashbackPage.entertransactionId(exitClickFour);
		cashbackPage.enterOrderId(exitClickFour);
		cashbackPage.setOrderDate();
		cashbackPage.enterOrderValue("1000");
		cashbackPage.enterconfirmCommisionNetwork(getTestData(11, "EightHundered"));
		cashbackPage.entercashback("400");
		cashbackPage.selectCashbackTypeFromDropDown(getTestData(11, "Cashback"));
		cashbackPage.selectCashbackStatusFromDropDown(getTestData(11, "Pending"));
		cashbackPage.setPendingDate();
		cashbackPage.enterDetails(exitClickFour);
		cashbackPage.clickSubmit();
		cashbackPage.clickClear();

		cashbackPage.clickAddNewButton();
		cashbackPage.enterExitId(exitClickFive);
		cashbackPage.entertransactionId(exitClickFive);
		cashbackPage.enterOrderId(exitClickFive);
		cashbackPage.setOrderDate();
		cashbackPage.enterOrderValue("1000");
		cashbackPage.enterconfirmCommisionNetwork(getTestData(11, "EightHundered"));
		cashbackPage.entercashback("300");
		cashbackPage.selectCashbackTypeFromDropDown(getTestData(11, "Cashback"));
		cashbackPage.selectCashbackStatusFromDropDown(getTestData(11, "Pending"));
		cashbackPage.setPendingDate();
		cashbackPage.enterDetails(exitClickFive);
		cashbackPage.clickSubmit();
		cashbackPage.clickClear();

		CashKaroUtility reopenCKAppOne = new CashKaroUtility(udid, port, automationName, deviceName, systemPort);
		reopenCKApp = reopenCKAppOne;
		driver = reopenCKApp.launchEarnKaroApp(driver);

		new HomePage(driver, testInfo).
		clickOnProfileIconForSignedUser().
		clickonHistory().
		validateMonthYear(currentMonthYear).clickViewMore().
		validateEntireBlock(exitClickSharedFive, currentDate, cbStoreNameFive, "1", "1", "300").
		validateEntireBlock(exitClickSharedFour, currentDate, cbStoreNameFour, "1", "1", "400").
		validateEntireBlock(exitClickSharedThree, currentDate, cbStoreNameThree, "1", "1", "500").
		validateEntireBlock(exitClickSharedTwo, currentDate, cbStoreNameTwo, "1", "1", "600").
		validateEntireBlock(exitClickSharedOne, currentDate, cbStoreNameOne, "1", "1", "700");

		CashKaroUtility cuOne = new CashKaroUtility(udid, port, automationName, deviceName);
		driver = cuOne.launchChromeWebView(driver);
		AdminCoreFunction adminFunctionsTwo = new AdminCoreFunction(driver, testInfo);
		AdminCashbackPage cashbackPageTwo = new AdminCashbackPage(driver, testInfo);
		adminFunctions = adminFunctionsTwo;
		cashbackPage = cashbackPageTwo;

		adminFunctions.naviagateAdminPage_ThenSelectEarnKaro();
		adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
		adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
		adminFunctions.clickSubmit();

		adminFunctions.clickOnHamburgerMenu();
		adminFunctions.clickCashbackMenu();
		cashbackPage.clickCashbackSubMenu();

		cashbackPage.selectSearchByStatusFromDropDown("Exit ID");
		cashbackPage.enterKeyword(exitClickOne);
		cashbackPage.selectCashbackStatusFromDropDown(getTestData(11, "Pending"));
		cashbackPage.clickSubmit();
		cashbackPage.clickEdit();
		cashbackPage.selectCashbackStatusFromDropDown(getTestData(11, "Confirmed"));
		cashbackPage.setConfirmDate();
		cashbackPage.clickSubmit();
		cashbackPage.clickClear();

		cashbackPage.selectSearchByStatusFromDropDown("Exit ID");
		cashbackPage.enterKeyword(exitClickTwo);
		cashbackPage.selectCashbackStatusFromDropDown(getTestData(11, "Pending"));
		cashbackPage.clickSubmit();
		cashbackPage.clickEdit();
		cashbackPage.selectCashbackStatusFromDropDown(getTestData(11, "Confirmed"));
		cashbackPage.setConfirmDate();
		cashbackPage.clickSubmit();
		cashbackPage.clickClear();

		cashbackPage.selectSearchByStatusFromDropDown("Exit ID");
		cashbackPage.enterKeyword(exitClickThree);
		cashbackPage.selectCashbackStatusFromDropDown(getTestData(11, "Pending"));
		cashbackPage.clickSubmit();
		cashbackPage.clickEdit();
		cashbackPage.selectCashbackStatusFromDropDown(getTestData(11, "Confirmed"));
		cashbackPage.setConfirmDate();
		cashbackPage.clickSubmit();
		cashbackPage.clickClear();

		cashbackPage.selectSearchByStatusFromDropDown("Exit ID");
		cashbackPage.enterKeyword(exitClickFour);
		cashbackPage.selectCashbackStatusFromDropDown(getTestData(11, "Pending"));
		cashbackPage.clickSubmit();
		cashbackPage.clickEdit();
		cashbackPage.selectCashbackStatusFromDropDown(getTestData(11, "Confirmed"));
		cashbackPage.setConfirmDate();
		cashbackPage.clickSubmit();
		cashbackPage.clickClear();

		cashbackPage.selectSearchByStatusFromDropDown("Exit ID");
		cashbackPage.enterKeyword(exitClickFive);
		cashbackPage.selectCashbackStatusFromDropDown(getTestData(11, "Pending"));
		cashbackPage.clickSubmit();
		cashbackPage.clickEdit();
		cashbackPage.selectCashbackStatusFromDropDown(getTestData(11, "Confirmed"));
		cashbackPage.setConfirmDate();
		cashbackPage.clickSubmit();
		cashbackPage.clickClear();

		CashKaroUtility reopenCKAppTwo = new CashKaroUtility(udid, port, automationName, deviceName, systemPort);
		reopenCKApp = reopenCKAppTwo;
		driver = reopenCKApp.launchEarnKaroApp(driver);

		new HomePage(driver, testInfo).
		clickOnProfileIconForSignedUser().
		clickonHistory().
		validateMonthYear(currentMonthYear).clickViewMore().
		validateEntireBlock(exitClickSharedFive, currentDate, cbStoreNameFive, "1", "1", "300").
		validateEntireBlock(exitClickSharedFour, currentDate, cbStoreNameFour, "1", "1", "400").
		validateEntireBlock(exitClickSharedThree, currentDate, cbStoreNameThree, "1", "1", "500").
		validateEntireBlock(exitClickSharedTwo, currentDate, cbStoreNameTwo, "1", "1", "600").
		validateEntireBlock(exitClickSharedOne, currentDate, cbStoreNameOne, "1", "1", "700");
		
		/*******************************************************************************************************************/

		reportStep("TC004 is  completed", "PASS");

	}

	@Test
	public void HistoryPage_TC005() {

		reportStep("TC005 is  started", "INFO");

		String cbStoreName = CashKaroUtility.extractValuesFromStoreCategoryJsonAndLoadTheVariablesForStore("CB_Store_One", 0, "CB_Store_One_Name");
		String cbShortDescription = CashKaroUtility.extractValuesFromStoreCategoryJsonAndLoadTheVariablesForStore("CB_Store_One", 0, "CB_Store_One_App_Short_Description");

		String cbStoreNameTwo = CashKaroUtility.extractValuesFromStoreCategoryJsonAndLoadTheVariablesForStore("CB_Store_Two", 1, "CB_Store_Two_Name");
		String cbShortDescriptionTwo = CashKaroUtility.extractValuesFromStoreCategoryJsonAndLoadTheVariablesForStore("CB_Store_Two", 1, "CB_Store_Two_App_Short_Description");

		String cbStoreNameThree = CashKaroUtility.extractValuesFromStoreCategoryJsonAndLoadTheVariablesForStore("RW_Store_One", 2, "RW_Store_One_Name");
		String cbShortDescriptionThree = CashKaroUtility.extractValuesFromStoreCategoryJsonAndLoadTheVariablesForStore("RW_Store_One", 2, "RW_Store_One_App_Short_Description");

		String cbStoreNameFour = CashKaroUtility.extractValuesFromStoreCategoryJsonAndLoadTheVariablesForStore("RW_Store_Two", 3, "RW_Store_Two_Name");
		String cbShortDescriptionFour = CashKaroUtility.extractValuesFromStoreCategoryJsonAndLoadTheVariablesForStore("RW_Store_Two", 3, "RW_Store_Two_App_Short_Description");

		String cbStoreNameFive = CashKaroUtility.extractValuesFromStoreCategoryJsonAndLoadTheVariablesForStore("N18_Store_One", 4, "N18_Store_One_Name");
		String cbShortDescriptionFive = CashKaroUtility.extractValuesFromStoreCategoryJsonAndLoadTheVariablesForStore("N18_Store_One", 4, "N18_Store_One_App_Short_Description");

		String linkURL = CashKaroUtility.extractValuesFromStoreCategoryJsonAndLoadTheVariablesForStore("CB_Store_One", 0, "CB_Store_One_linkURL");

		String currentDate = Utilities.today_dd_MMMM_YYYY_ISTZone().replace("-", " ").trim();
		String currentMonthYear = Utilities.currentMonthYear().trim();
		
		String previousMonthCurrentDate = Utilities.customBackDate_dd_MMMM_YYYY(32).replace("-", " ").trim();
		String previousMonthCurrentYear = Utilities.previousMonthCurrentYear(1).trim();

		String previousTwoMonthCurrentDate = Utilities.customBackDate_dd_MMMM_YYYY(62).replace("-", " ").trim();
		String previousTwoMonthCurrentYear = Utilities.previousMonthCurrentYear(2).trim();

		String previousThreeMonthCurrentDate = Utilities.customBackDate_dd_MMMM_YYYY(92).replace("-", " ").trim();
		String previousThreeMonthCurrentYear = Utilities.previousMonthCurrentYear(3).trim();

//		String previousFourMonthCurrentDate = Utilities.customBackDate_dd_MMMM_YYYY(120).replace("-", " ").trim();
//		String previousFourMonthCurrentYear = Utilities.previousMonthCurrentYear(4).trim();

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
		clickOnSearchIcon().
		enterTextIntoTheSearchBar(cbStoreName).
		clickStoreCardShortDescription(cbShortDescription).
		validateStoreShortDescription(cbStoreName, cbShortDescription).
		clickOnAddToShareButton().
		clickOnShareIconWithCount(1).
		clickOnCopyLink().
		clickOnclearAllButtonInPopup();

		String url1 = getClipBoardText();
		
		backButton();
		backButton();
		backButton();
		
		new HomePage(driver, testInfo).
		clickOnSearchIcon().
		enterTextIntoTheSearchBar(cbStoreNameTwo).
		clickStoreCardShortDescription(cbShortDescriptionTwo).
		validateStoreShortDescription(cbStoreNameTwo, cbShortDescriptionTwo).
		clickOnAddToShareButton().
		clickOnShareIconWithCount(1).
		clickOnCopyLink().
		clickOnclearAllButtonInPopup();

		String url2 = getClipBoardText();
		
		backButton();
		backButton();
		backButton();
		
		new HomePage(driver, testInfo).
		clickOnSearchIcon().
		enterTextIntoTheSearchBar(cbStoreNameThree).
		clickStoreCardShortDescription(cbShortDescriptionThree).
		validateStoreShortDescription(cbStoreNameThree, cbShortDescriptionThree).
		clickOnAddToShareButton().
		clickOnShareIconWithCount(1).
		clickOnCopyLink().
		clickOnclearAllButtonInPopup();

		String url3 = getClipBoardText();

		backButton();
		backButton();
		backButton();
		
		new HomePage(driver, testInfo).
		clickOnSearchIcon().
		enterTextIntoTheSearchBar(cbStoreNameFour).
		clickStoreCardShortDescription(cbShortDescriptionFour).
		validateStoreShortDescription(cbStoreNameFour, cbShortDescriptionFour).
		clickOnAddToShareButton().
		clickOnShareIconWithCount(1).
		clickOnCopyLink().
		clickOnclearAllButtonInPopup();

		String url4 = getClipBoardText();

		backButton();
		backButton();
		backButton();
		
		new HomePage(driver, testInfo).
		clickOnSearchIcon().
		enterTextIntoTheSearchBar(cbStoreNameFive).
		clickStoreCardShortDescription(cbShortDescriptionFive).
		validateStoreShortDescription(cbStoreNameFive, cbShortDescriptionFive).
		clickOnAddToShareButton().
		clickOnShareIconWithCount(1).
		clickOnCopyLink().
		clickOnclearAllButtonInPopup();

		String url5 = getClipBoardText();
		
		CashKaroUtility cu = new CashKaroUtility(udid, port, automationName, deviceName);
		 driver = cu.launchChrome();

		cu.closeAllTabsAndOneTabInChromeBrowser(driver);
		cu.clickAndEnterURLInSearchOrTypeWebAddress(driver, url1);

		new IntermediateRetailerPage(driver, testInfo).
		validateRetailerPage(linkURL, linkURL);
		
		cu.closeAllTabsAndOneTabInChromeBrowser(driver);
		cu.clickAndEnterURLInSearchOrTypeWebAddress(driver, url2);

		new IntermediateRetailerPage(driver, testInfo).
		validateRetailerPage(linkURL, linkURL);
		
		cu.closeAllTabsAndOneTabInChromeBrowser(driver);
		cu.clickAndEnterURLInSearchOrTypeWebAddress(driver, url3);

		new IntermediateRetailerPage(driver, testInfo).
		validateRetailerPage(linkURL, linkURL);
		
		cu.closeAllTabsAndOneTabInChromeBrowser(driver);
		cu.clickAndEnterURLInSearchOrTypeWebAddress(driver, url4);

		new IntermediateRetailerPage(driver, testInfo).
		validateRetailerPage(linkURL, linkURL);
		
		cu.closeAllTabsAndOneTabInChromeBrowser(driver);
		cu.clickAndEnterURLInSearchOrTypeWebAddress(driver, url5);

		new IntermediateRetailerPage(driver, testInfo).
		validateRetailerPage(linkURL, linkURL);

		CashKaroUtility cu1 = new CashKaroUtility(udid, port, automationName, deviceName);
		driver = cu1.launchChromeWebView(driver);
		
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
		String exitClickOne = reportsPage.extractRequiredExitIdUsingStoreNameExitClickType(cbStoreName, getTestData(7, "Shared"));
		String exitClickTwo = reportsPage.extractRequiredExitIdUsingStoreNameExitClickType(cbStoreNameTwo, getTestData(7, "Shared"));
		String exitClickThree = reportsPage.extractRequiredExitIdUsingStoreNameExitClickType(cbStoreNameThree, getTestData(7, "Shared"));
		String exitClickFour = reportsPage.extractRequiredExitIdUsingStoreNameExitClickType(cbStoreNameFour, getTestData(7, "Shared"));
		String exitClickFive = reportsPage.extractRequiredExitIdUsingStoreNameExitClickType(cbStoreNameFive, getTestData(7, "Shared"));

		CashKaroUtility objcashKaroUtility = new CashKaroUtility(driver, testInfo);
		objcashKaroUtility.backDateTheExitClickForGivenDate(exitClickTwo, 32);
		objcashKaroUtility.backDateTheExitClickForGivenDate(exitClickThree, 62);
		objcashKaroUtility.backDateTheExitClickForGivenDate(exitClickFour, 92);
		objcashKaroUtility.backDateTheExitClickForGivenDate(exitClickFive, 122);

		CashKaroUtility reopenCKAppOne = new CashKaroUtility(udid, port, automationName, deviceName, systemPort);
		driver = reopenCKAppOne.launchEarnKaroApp(driver);

		new HomePage(driver, testInfo).
		clickOnProfileIconForSignedUser().
		clickonHistory().
		clickMonthYearDropdown(currentMonthYear, currentMonthYear).
		validateMonthYear(currentMonthYear).
		validateEntireBlock(exitClickOne, currentDate, cbStoreName, "1", "0", "0").
		clickMonthYearDropdown(currentMonthYear, previousMonthCurrentYear).
		validateMonthYear(previousMonthCurrentYear).
		validateEntireBlock(exitClickTwo, previousMonthCurrentDate, cbStoreNameTwo, "1", "0", "0").
		clickMonthYearDropdown(previousMonthCurrentYear, previousTwoMonthCurrentYear).
		validateMonthYear(previousTwoMonthCurrentYear).
		validateEntireBlock(exitClickThree, previousTwoMonthCurrentDate, cbStoreNameThree, "1", "0", "0").
		clickMonthYearDropdown(previousTwoMonthCurrentYear, previousThreeMonthCurrentYear).
		validateMonthYear(previousThreeMonthCurrentYear).
		validateEntireBlock(exitClickFour, previousThreeMonthCurrentDate, cbStoreNameFour, "1", "0", "0");

	}

	@Test
	public void HistoryPage_TC006() {
		
		reportStep("TC003 is  started", "INFO");

		String cbStoreName = CashKaroUtility.extractValuesFromStoreCategoryJsonAndLoadTheVariablesForStore("CB_Store_One", 0, "CB_Store_One_Name");
		String cbShortDescription = CashKaroUtility.extractValuesFromStoreCategoryJsonAndLoadTheVariablesForStore("CB_Store_One", 0, "CB_Store_One_App_Short_Description");
		String cbLinkURL = CashKaroUtility.extractValuesFromStoreCategoryJsonAndLoadTheVariablesForStore("CB_Store_One", 0, "CB_Store_One_linkURL");

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo); 
		String emailAddress = objCashKaroUtility.generateEmail();
		String mobileNumber = objCashKaroUtility.generateMobileNumber();	
		String presentDate = Utilities.today_dd_MMMM_YYYY_ISTZone().trim();
		String currentDate = presentDate.replace("-", " ");
		String currentMonthYear = Utilities.currentMonthYear().trim();
		
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
		clickOnProfileIconForSignedUser().
		clickonHistory().
		validateObjectsHistoryPage().
		clickBackButton();
		
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
			
		new IntermediateRetailerPage(driver, testInfo).
		validateRetailerPage(cbLinkURL, cbLinkURL);
		
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
		String exitClickIDOne = reportsPage.extractRequiredExitIdUsingStoreNameExitClickType(cbStoreName, getTestData(7, "Shared"));
		
		CashKaroUtility reopenCKApp = new CashKaroUtility(udid, port, automationName, deviceName,systemPort);
		driver  = reopenCKApp.launchEarnKaroApp(driver);
		
		String shareUrl = new HomePage(driver, testInfo).
		clickOnProfileIconForSignedUser().
		clickonHistory().
		validateMonthYear(currentMonthYear).
		validateEntireBlock(exitClickIDOne, currentDate, cbStoreName, "1", "0", "0").
		clickCopy(exitClickIDOne);

		CashKaroUtility cuTwo = new CashKaroUtility(udid, port, automationName, deviceName);
		AppiumDriver<MobileElement> driver2 = cuTwo.launchChrome();
		driver = driver2;
		
		cu.closeAllTabsAndOneTabInChromeBrowser(driver);
		cu.clickAndEnterURLInSearchOrTypeWebAddress(driver, shareUrl);
			
		new IntermediateRetailerPage(driver, testInfo).
		validateRetailerPage(cbLinkURL, cbLinkURL);
	
		CashKaroUtility reopenCKAppOne = new CashKaroUtility(udid, port, automationName, deviceName,systemPort);
		reopenCKApp = reopenCKAppOne;
		driver  = reopenCKApp.launchEarnKaroApp(driver);
		
		new HomePage(driver, testInfo).
		clickOnProfileIconForSignedUser().
		clickonHistory().
		validateMonthYear(currentMonthYear).
		validateEntireBlock(exitClickIDOne, currentDate, cbStoreName, "2", "0", "0");

		CashKaroUtility cuThree = new CashKaroUtility(udid, port, automationName, deviceName);
		cuOne = cuThree;
		driver = cuOne.launchChromeWebView(driver);
		
		AdminCoreFunction adminFunctionsOne = new AdminCoreFunction(driver, testInfo);	
		adminFunctions = adminFunctionsOne;
		adminFunctions.naviagateAdminPage_ThenSelectEarnKaro();
		adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
		adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
		adminFunctions.clickSubmit();
		
		AdminReportsPage reportsPageOne = new AdminReportsPage(driver, testInfo);
		reportsPage = reportsPageOne;
		adminFunctions.clickOnHamburgerMenu();
		adminFunctions.clickOnReports();
		reportsPage.clickExitClickSubMenu();
		reportsPage.clickClear();
		reportsPage.selectSearchByDropDown("User Email");
		reportsPage.enterKeyword(emailAddress);
		reportsPage.clickSubmit();
		String exitClickIdOne = reportsPage.extractRequiredExitIdUsingStoreNameExitClickType(cbStoreName, getTestData(7, "Normal"), 1);
		String exitClickIdTwo = reportsPage.extractRequiredExitIdUsingStoreNameExitClickType(cbStoreName, getTestData(7, "Normal"), 2);
		
		AdminCashbackPage ProfitPage = new AdminCashbackPage(driver, testInfo);
		adminFunctions.clickOnHamburgerMenu();
		adminFunctions.clickCashbackMenu();
		ProfitPage.clickCashbackSubMenu();
		ProfitPage.clickAddNewButton();
		ProfitPage.enterExitId(exitClickIdOne);
		ProfitPage.entertransactionId(exitClickIdOne);
		ProfitPage.enterOrderId(exitClickIdOne);
		ProfitPage.setOrderDate();
		ProfitPage.enterOrderValue("1000");
		ProfitPage.enterconfirmCommisionNetwork(getTestData(11, "FiveHundered"));
		ProfitPage.entercashback(getTestData(11, "TwoFifty"));
		ProfitPage.selectCashbackTypeFromDropDown(getTestData(11, "Cashback"));
		ProfitPage.selectCashbackStatusFromDropDown(getTestData(11, "Pending"));
		ProfitPage.setPendingDate();
		ProfitPage.enterDetails(exitClickIdOne);
		ProfitPage.clickSubmit();

		ProfitPage.clickAddNewButton();
		ProfitPage.enterExitId(exitClickIdTwo);
		ProfitPage.entertransactionId(exitClickIdTwo);
		ProfitPage.enterOrderId(exitClickIdTwo);
		ProfitPage.setOrderDate();
		ProfitPage.enterOrderValue("1000");
		ProfitPage.enterconfirmCommisionNetwork(getTestData(11, "FiveHundered"));
		ProfitPage.entercashback(getTestData(11, "TwoFifty"));
		ProfitPage.selectCashbackTypeFromDropDown(getTestData(11, "Cashback"));
		ProfitPage.selectCashbackStatusFromDropDown(getTestData(11, "Pending"));
		ProfitPage.setPendingDate();
		ProfitPage.enterDetails(exitClickIdTwo);
		ProfitPage.clickSubmit();

		CashKaroUtility reopenCKAppTwo = new CashKaroUtility(udid, port, automationName, deviceName,systemPort);
		reopenCKApp = reopenCKAppTwo;
		driver  = reopenCKApp.launchEarnKaroApp(driver);
		
		new HomePage(driver, testInfo).
		clickOnProfileIconForSignedUser().
		clickonHistory().
		validateMonthYear(currentMonthYear).
		validateEntireBlock(exitClickIDOne, currentDate, cbStoreName, "2", "2", "500.00");

		CashKaroUtility cuFour = new CashKaroUtility(udid, port, automationName, deviceName);
		cuOne = cuFour;
		driver = cuOne.launchChromeWebView(driver);
		
		AdminCoreFunction adminFunctionsTwo = new AdminCoreFunction(driver, testInfo);	
		adminFunctions = adminFunctionsTwo;
		adminFunctions.naviagateAdminPage_ThenSelectEarnKaro();
		adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
		adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
		adminFunctions.clickSubmit();
		
		AdminCashbackPage ProfitPageOne = new AdminCashbackPage(driver, testInfo);
		ProfitPage = ProfitPageOne;
		adminFunctions.clickOnHamburgerMenu();
		adminFunctions.clickCashbackMenu();
		ProfitPage.clickCashbackSubMenu();
		ProfitPage.selectSearchByStatusFromDropDown("Exit ID");
		ProfitPage.enterKeyword(exitClickIdOne);
		ProfitPage.selectCashbackStatusFromDropDown(getTestData(11, "Pending"));
		ProfitPage.clickSubmit();
		ProfitPage.clickEdit();
		ProfitPage.selectCashbackStatusFromDropDown(getTestData(11, "Confirmed"));
		ProfitPage.setConfirmDate();
		ProfitPage.clickSubmit();

		adminFunctions.clickOnHamburgerMenu();
		ProfitPage.clickCashbackSubMenu();
		ProfitPage.selectSearchByStatusFromDropDown("Exit ID");
		ProfitPage.enterKeyword(exitClickIdTwo);
		ProfitPage.selectCashbackStatusFromDropDown(getTestData(11, "Pending"));
		ProfitPage.clickSubmit();
		ProfitPage.clickEdit();
		ProfitPage.selectCashbackStatusFromDropDown(getTestData(11, "Cancelled"));
		ProfitPage.setFailDate();
		ProfitPage.clickSubmit();

		CashKaroUtility reopenCKAppThree = new CashKaroUtility(udid, port, automationName, deviceName,systemPort);
		reopenCKApp = reopenCKAppThree;
		driver  = reopenCKApp.launchEarnKaroApp(driver);
		
		new HomePage(driver, testInfo).
		clickOnProfileIconForSignedUser().
		clickonHistory().
		validateMonthYear(currentMonthYear).
		validateEntireBlock(exitClickIDOne, currentDate, cbStoreName, "2", "2", "250.00");
		
		CashKaroUtility cu1 = new CashKaroUtility(udid, port, automationName, deviceName);
		cu = cu1;
		AppiumDriver<MobileElement> driver3 = cu.launchChrome();
		driver = driver3;
		
		cu.closeAllTabsAndOneTabInChromeBrowser(driver);
		cu.clickAndEnterURLInSearchOrTypeWebAddress(driver, shareUrl);
			
		new IntermediateRetailerPage(driver, testInfo).
		validateRetailerPage(cbLinkURL, cbLinkURL);
	
		CashKaroUtility reopenCKAppFour = new CashKaroUtility(udid, port, automationName, deviceName,systemPort);
		reopenCKApp = reopenCKAppFour;
		driver  = reopenCKApp.launchEarnKaroApp(driver);
		
		new HomePage(driver, testInfo).
		clickOnProfileIconForSignedUser().
		clickonHistory().
		validateMonthYear(currentMonthYear).
		validateEntireBlock(exitClickIDOne, currentDate, cbStoreName, "3", "2", "250.00");

		CashKaroUtility cuFive = new CashKaroUtility(udid, port, automationName, deviceName);
		cuOne = cuFive;
		driver = cuOne.launchChromeWebView(driver);
		
		AdminCoreFunction adminFunctionsThree = new AdminCoreFunction(driver, testInfo);	
		adminFunctions = adminFunctionsThree;
		adminFunctions.naviagateAdminPage_ThenSelectEarnKaro();
		adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
		adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
		adminFunctions.clickSubmit();
		
		AdminReportsPage reportsPageTwo = new AdminReportsPage(driver, testInfo);
		reportsPage = reportsPageTwo;
		adminFunctions.clickOnHamburgerMenu();
		adminFunctions.clickOnReports();
		reportsPage.clickExitClickSubMenu();
		reportsPage.clickClear();
		reportsPage.selectSearchByDropDown("User Email");
		reportsPage.enterKeyword(emailAddress);
		reportsPage.clickSubmit();
		String exitClickIDThree = reportsPage.extractRequiredExitIdUsingStoreNameExitClickType(cbStoreName, getTestData(7, "Normal"));
		
		AdminCashbackPage ProfitPageThree = new AdminCashbackPage(driver, testInfo);
		ProfitPage = ProfitPageThree;
		adminFunctions.clickOnHamburgerMenu();
		adminFunctions.clickCashbackMenu();
		ProfitPage.clickCashbackSubMenu();
		ProfitPage.clickAddNewButton();
		ProfitPage.enterExitId(exitClickIDThree);
		ProfitPage.entertransactionId(exitClickIDThree);
		ProfitPage.enterOrderId(exitClickIDThree);
		ProfitPage.setOrderDate();
		ProfitPage.enterOrderValue("1000");
		ProfitPage.enterconfirmCommisionNetwork(getTestData(11, "FiveHundered"));
		ProfitPage.entercashback(getTestData(11, "TwoFifty"));
		ProfitPage.selectCashbackTypeFromDropDown(getTestData(11, "Cashback"));
		ProfitPage.selectCashbackStatusFromDropDown(getTestData(11, "Pending"));
		ProfitPage.setPendingDate();
		ProfitPage.enterDetails(exitClickIDThree);
		ProfitPage.clickSubmit();
		
		CashKaroUtility reopenCKAppFive = new CashKaroUtility(udid, port, automationName, deviceName,systemPort);
		reopenCKApp = reopenCKAppFive;
		driver  = reopenCKApp.launchEarnKaroApp(driver);
		
		new HomePage(driver, testInfo).
		clickOnProfileIconForSignedUser().
		clickonHistory().
		validateMonthYear(currentMonthYear).
		validateEntireBlock(exitClickIDOne, currentDate, cbStoreName, "3", "3", "500.00");

		CashKaroUtility cuSix = new CashKaroUtility(udid, port, automationName, deviceName);
		cuOne = cuSix;
		driver = cuOne.launchChromeWebView(driver);
		
		AdminCoreFunction adminFunctionsFour = new AdminCoreFunction(driver, testInfo);	
		adminFunctions = adminFunctionsFour;
		adminFunctions.naviagateAdminPage_ThenSelectEarnKaro();
		adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
		adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
		adminFunctions.clickSubmit();
		
		AdminCashbackPage ProfitPageFour = new AdminCashbackPage(driver, testInfo);
		ProfitPage = ProfitPageFour;
		adminFunctions.clickOnHamburgerMenu();
		adminFunctions.clickCashbackMenu();
		ProfitPage.clickCashbackSubMenu();
		ProfitPage.selectSearchByStatusFromDropDown("Exit ID");
		ProfitPage.enterKeyword(exitClickIDThree);
		ProfitPage.selectCashbackStatusFromDropDown(getTestData(11, "Pending"));
		ProfitPage.clickSubmit();
		ProfitPage.clickEdit();
		ProfitPage.selectCashbackStatusFromDropDown(getTestData(11, "Confirmed"));
		ProfitPage.setConfirmDate();
		ProfitPage.clickSubmit();

		CashKaroUtility reopenCKAppSix = new CashKaroUtility(udid, port, automationName, deviceName,systemPort);
		reopenCKApp = reopenCKAppSix;
		driver  = reopenCKApp.launchEarnKaroApp(driver);
		
		new HomePage(driver, testInfo).
		clickOnProfileIconForSignedUser().
		clickonHistory().
		validateMonthYear(currentMonthYear).
		validateEntireBlock(exitClickIDOne, currentDate, cbStoreName, "3", "3", "500.00");

	}

	/*******************************************************************/
	/********              			HISTORY    		     	 ***********/
	/*******************************************************************/

}