package com.app.ck.test;

import java.util.Set;

import org.openqa.selenium.By;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.app.ck.base.GetCodeSetUP;
import com.app.ck.base.WrapperMethods;
import com.app.ck.pages.EKOnboardingPage;
import com.app.ck.pages.HomePage;
import com.app.ck.pages.IntermediateRetailerPage;
import com.app.ck.pages.JoinFreePage;
import com.app.ck.pages.MyEarningsPage;
import com.app.ck.pages.NotificationPage;
import com.app.ck.pages.OTPPage;
import com.app.ck.pages.PaymentOTPPage;
import com.app.ck.pages.SignInPage;
import com.app.ck.pages.StoreDetailPage;
import com.app.ck.pages.admin.AdminCashbackPage;
import com.app.ck.pages.admin.AdminCoreFunction;
import com.app.ck.pages.admin.AdminPendingCashoutsPage;
import com.app.ck.pages.admin.AdminReportsPage;
import com.app.ck.pages.admin.AdminUsersPage;
import com.app.ck.pages.admin.EmailMasterBrowserPage;
import com.app.ck.utilities.CashKaroUtility;
import com.app.ck.utilities.Utilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class EKTestNotificationTests extends WrapperMethods {


	/*
	 * 
	 * 
	 * 
	=================================================================
					 Notification Document 
	-------------------------------------------------------------
	Read Me,Before You start automating with Notification
	----------------------------------------------------------
	Author : Mallikarjunaswamy B
	Date of Automation script development : 01_05_2019 to 7_05_2019
	Reviewed By : Jayakumar (Developer)
	----------------------------------------------------------------
	Points to follow strictly ,while automating the Notification :
	==================================================================

	1. While Automating notification , We usually login with the User "cktest@gmail.com"  and Password : "ppounds" ,
	   make sure this user is added into the Email Master.If you change any other user to login from the test data
	   We should add the user to the Email Master .Then only we start receive the notification
	   In order to add the User to the Email master , Kindly contact any developer.

	2. Notification Automation -  mostly 2 types one is With Template and other one is Without Template .
	   Without Template is not a problem for automation but with Template is a dependency  which means Templates are hard coded.
	   If you forget to create the Template as per the script written , the test case might get FAILed . I repeat again,that Template must be 
	   created as the script is being written.

   3. While doing Parallel execution , If you use the same user to login  then it will be Problem and it is not right to use same user login 
      in Parallel devices for the Notification Testing. Kindly ensure that different user login in different devices for  Parallel execution.


    4. The challenging Part while automating Notification is that device specific issues - make sure you have right set of devices to run Notification automation
        and do check device stability for Notification automation before you start with Notification Automation.

	5. For Notification Redirection testing some hard coded store test data is created , Ensure that the required store Page is available.

	6. For Transactional Notification - We SignUp with Date stamp created user details running the Transactional notification in Parallel is not a Problem. Which in terms
	   You can execute the Transaction Notification in parallel.


	 *
	 */
	//Upadted date : 08_05_2019

	/*******************************************************************/
	/************************* Without Template  ********************/
	/*******************************************************************/

	//Positive flow :
	@Parameters({"deviceName","user"})
	@Test
	public void notificationTest_AppBackGround(String deviceName,String user) {

		try {
			// TEST STEPS :
			new EKOnboardingPage(driver, testInfo).
			clickOnSignInbutton().
			enterUserName(user).
			enterPassword().
			clickSignInButtonForSuccess();

			//Open the Notification bar :
			openNotification();

			//Clear the Notification
			clearNotificationFromNotificationBar(deviceName);

			closeNotification();

			CashKaroUtility cu = new CashKaroUtility(udid, port, automationName, deviceName);
			driver = cu.launchChromeWebView(driver);
			AdminCoreFunction adminFunctions = new AdminCoreFunction(driver, testInfo);
			adminFunctions.naviagateAdminPage_ThenSelectEarnKaro();
			adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
			adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
			adminFunctions.clickSubmit();

			adminFunctions.clickOnHamburgerMenu();
			adminFunctions.clickOnEmailMaster();
			adminFunctions.clickOnEmailMasterBrowser();
			adminFunctions.selectEmailFromSearchByDropDown_EmailMaster();
			adminFunctions.enterUserEmailAsKeyForSearchEmailMaster(user);
			adminFunctions.clickOnSearchButtonEmailMaster();

			EmailMasterBrowserPage emailMasterBrowserpage = new EmailMasterBrowserPage(driver, testInfo);

			emailMasterBrowserpage.clickOnSendNotification();
			emailMasterBrowserpage.clickOnWithoutNotificationTemplate();
			emailMasterBrowserpage.enterAppNotificationTitle(testdata.get(17).get("NotificationTitle"));
			emailMasterBrowserpage.enterImageURL(testdata.get(17).get("BigImage"));
			emailMasterBrowserpage.enterAppNotificationBody(testdata.get(17).get("NotificationBody"));
			emailMasterBrowserpage.enterBigText(testdata.get(17).get("BigText"));
			emailMasterBrowserpage.enterRedirectionalURL(testdata.get(17).get("RedirectionalURL"));
			emailMasterBrowserpage.enterLargeIcon(GetCodeSetUP.NOTIFICATIONLARGEICON);
			emailMasterBrowserpage.enterSubText(testdata.get(17).get("SubText"));
			emailMasterBrowserpage.clickOnSubmit();

			Set<String> contextNames = driver.getContextHandles();
			driver.context("NATIVE_APP");

			toggleApp(driver);
			clickEarnkaroApp(driver);
			new HomePage(driver, testInfo);
			toggleApp();
			clickChromeApp();

			driver.context((String) contextNames.toArray()[1]);
			emailMasterBrowserpage.clickOnConfirmButton();
			driver.context("NATIVE_APP");

			//Open the Notification bar :
			openNotification();

			new NotificationPage(driver, testInfo).
			validateNotificationTitle().
			validateNotificationBody(deviceName).
			clickOnExpand(deviceName).
			validateBigImage(deviceName).
			validateBigText().
			validatelargeIcon(deviceName).
			clickOnBigImage().
			validateAjioStoreRedirection();

		}catch (Exception e) {

			closeNotification();
			reportStep("Test FAILed , unexpectedly :(", "FAIL");

		}

	}

	@Parameters({"deviceName","user"})
	@Test
	public void notificationTest_AppClosed(String deviceName,String user) {

		try {
			// TEST STEPS :
			new EKOnboardingPage(driver, testInfo).
			clickOnSignInbutton().
			enterUserName(user).
			enterPassword().
			clickSignInButtonForSuccess();

			//Open the Notification bar :
			openNotification();

			//Clear the Notification
			clearNotificationFromNotificationBar(deviceName);

			closeNotification();

			CashKaroUtility cu = new CashKaroUtility(udid, port, automationName, deviceName);
			driver = cu.launchChromeWebView(driver);
			AdminCoreFunction adminFunctions = new AdminCoreFunction(driver, testInfo);
			adminFunctions.naviagateAdminPage_ThenSelectEarnKaro();
			adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
			adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
			adminFunctions.clickSubmit();

			adminFunctions.clickOnHamburgerMenu();
			adminFunctions.clickOnEmailMaster();
			adminFunctions.clickOnEmailMasterBrowser();
			adminFunctions.selectEmailFromSearchByDropDown_EmailMaster();
			adminFunctions.enterUserEmailAsKeyForSearchEmailMaster(user);
			adminFunctions.clickOnSearchButtonEmailMaster();

			EmailMasterBrowserPage emailMasterBrowserpage = new EmailMasterBrowserPage(driver, testInfo);

			emailMasterBrowserpage.clickOnSendNotification();
			emailMasterBrowserpage.clickOnWithoutNotificationTemplate();
			emailMasterBrowserpage.enterAppNotificationTitle(testdata.get(17).get("NotificationTitle"));
			emailMasterBrowserpage.enterImageURL(testdata.get(17).get("BigImage"));
			emailMasterBrowserpage.enterAppNotificationBody(testdata.get(17).get("NotificationBody"));
			emailMasterBrowserpage.enterBigText(testdata.get(17).get("BigText"));
			emailMasterBrowserpage.enterRedirectionalURL(testdata.get(17).get("RedirectionalURL"));
			emailMasterBrowserpage.enterLargeIcon(GetCodeSetUP.NOTIFICATIONLARGEICON);
			emailMasterBrowserpage.enterSubText(testdata.get(17).get("SubText"));
			emailMasterBrowserpage.clickOnSubmit();

			Set<String> contextNames = driver.getContextHandles();
			driver.context("NATIVE_APP");

			toggleApp(driver);
			clickEarnkaroApp(driver);
			new HomePage(driver, testInfo);
			toggleApp();
			closeEarnKaroApp(deviceName);
			clickChromeApp();

			driver.context((String) contextNames.toArray()[1]);
			emailMasterBrowserpage.clickOnConfirmButton();
			driver.context("NATIVE_APP");

			//Open the Notification bar :
			openNotification();

			new NotificationPage(driver, testInfo).
			validateNotificationTitle().
			validateNotificationBody(deviceName).
			clickOnExpand(deviceName).
			validateBigImage(deviceName).
			validateBigText().
			validatelargeIcon(deviceName).
			clickOnBigImage().
			validateAjioStoreRedirection();

		} catch (Exception e) {

			closeNotification();
			reportStep("Test FAILed , unexpectedly - May be Notification arrived late - Please look into the Step by Step report and Analyse the FAILured :(", "FAIL");

		}


	}

	@Parameters({"deviceName","user"})
	@Test
	public void notificationTest_AppBackGround_WithOutImageURL_WithoutRedirectionURL_WithoutLargeIcon(String deviceName,String user) {

		try {
			// TEST STEPS :
			new EKOnboardingPage(driver, testInfo).
			clickOnSignInbutton().
			enterUserName(user).
			enterPassword().
			clickSignInButtonForSuccess();

			//Open the Notification bar :
			openNotification();

			//Clear the Notification
			clearNotificationFromNotificationBar(deviceName);

			closeNotification();

			CashKaroUtility cu = new CashKaroUtility(udid, port, automationName, deviceName);
			driver = cu.launchChromeWebView(driver);
			AdminCoreFunction adminFunctions = new AdminCoreFunction(driver, testInfo);
			adminFunctions.naviagateAdminPage_ThenSelectEarnKaro();
			adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
			adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
			adminFunctions.clickSubmit();

			adminFunctions.clickOnHamburgerMenu();
			adminFunctions.clickOnEmailMaster();
			adminFunctions.clickOnEmailMasterBrowser();
			adminFunctions.selectEmailFromSearchByDropDown_EmailMaster();
			adminFunctions.enterUserEmailAsKeyForSearchEmailMaster(user);
			adminFunctions.clickOnSearchButtonEmailMaster();

			EmailMasterBrowserPage emailMasterBrowserpage = new EmailMasterBrowserPage(driver, testInfo);

			emailMasterBrowserpage.clickOnSendNotification();
			emailMasterBrowserpage.clickOnWithoutNotificationTemplate();
			emailMasterBrowserpage.enterAppNotificationTitle(testdata.get(17).get("NotificationTitle"));
			emailMasterBrowserpage.enterAppNotificationBody(testdata.get(17).get("NotificationBody"));
			emailMasterBrowserpage.enterBigText(testdata.get(17).get("BigText"));
			emailMasterBrowserpage.enterSubText(testdata.get(17).get("SubText"));
			emailMasterBrowserpage.clickOnSubmit();

			Set<String> contextNames = driver.getContextHandles();
			driver.context("NATIVE_APP");

			toggleApp(driver);
			clickEarnkaroApp(driver);
			new HomePage(driver, testInfo);
			toggleApp();
			clickChromeApp();

			driver.context((String) contextNames.toArray()[1]);
			emailMasterBrowserpage.clickOnConfirmButton();
			driver.context("NATIVE_APP");

			//Open the Notification bar :
			openNotification();

			new NotificationPage(driver, testInfo).
			validateNotificationTitle().
			validateBigText().
			clickOnNotificationTitle();

			new HomePage(driver, testInfo);


		}catch (Exception e) {

			closeNotification();
			reportStep("Test FAILed , unexpectedly :(", "FAIL");

		}

	}

	@Parameters({"deviceName","user"})
	@Test
	public void notificationTest_AppClosed_WithOutImageURL_WithoutRedirectionURL_WithoutLargeIcon(String deviceName,String user) {

		try {
			// TEST STEPS :
			new EKOnboardingPage(driver, testInfo).
			clickOnSignInbutton().
			enterUserName(user).
			enterPassword().
			clickSignInButtonForSuccess();

			//Open the Notification bar :
			openNotification();

			//Clear the Notification
			clearNotificationFromNotificationBar(deviceName);

			closeNotification();

			CashKaroUtility cu = new CashKaroUtility(udid, port, automationName, deviceName);
			driver = cu.launchChromeWebView(driver);
			AdminCoreFunction adminFunctions = new AdminCoreFunction(driver, testInfo);
			adminFunctions.naviagateAdminPage_ThenSelectEarnKaro();
			adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
			adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
			adminFunctions.clickSubmit();

			adminFunctions.clickOnHamburgerMenu();
			adminFunctions.clickOnEmailMaster();
			adminFunctions.clickOnEmailMasterBrowser();
			adminFunctions.selectEmailFromSearchByDropDown_EmailMaster();
			adminFunctions.enterUserEmailAsKeyForSearchEmailMaster(user);
			adminFunctions.clickOnSearchButtonEmailMaster();

			EmailMasterBrowserPage emailMasterBrowserpage = new EmailMasterBrowserPage(driver, testInfo);

			emailMasterBrowserpage.clickOnSendNotification();
			emailMasterBrowserpage.clickOnWithoutNotificationTemplate();
			emailMasterBrowserpage.enterAppNotificationTitle(testdata.get(17).get("NotificationTitle"));
			emailMasterBrowserpage.enterAppNotificationBody(testdata.get(17).get("NotificationBody"));
			emailMasterBrowserpage.enterBigText(testdata.get(17).get("BigText"));
			emailMasterBrowserpage.enterSubText(testdata.get(17).get("SubText"));
			emailMasterBrowserpage.clickOnSubmit();

			Set<String> contextNames = driver.getContextHandles();
			driver.context("NATIVE_APP");

			toggleApp(driver);
			clickEarnkaroApp(driver);
			new HomePage(driver, testInfo);
			toggleApp();
			closeEarnKaroApp(deviceName);
			clickChromeApp();

			driver.context((String) contextNames.toArray()[1]);
			emailMasterBrowserpage.clickOnConfirmButton();
			driver.context("NATIVE_APP");

			//Open the Notification bar :
			openNotification();

			new NotificationPage(driver, testInfo).
			validateNotificationTitle().
			validateBigText().
			clickOnNotificationTitle();

			new HomePage(driver, testInfo);

		} catch (Exception e) {

			closeNotification();
			reportStep("Test FAILed , unexpectedly - May be Notification arrived late - Please look into the Step by Step report and Analyse the FAILured :(", "FAIL");

		}


	}

	@Parameters({"deviceName","user"})
	@Test
	public void notificationTest_AppBackGround_WithoutLargeIcon(String deviceName,String user) {

		try {
			// TEST STEPS :
			new EKOnboardingPage(driver, testInfo).
			clickOnSignInbutton().
			enterUserName(user).
			enterPassword().
			clickSignInButtonForSuccess();

			//Open the Notification bar :
			openNotification();

			//Clear the Notification
			clearNotificationFromNotificationBar(deviceName);

			closeNotification();

			CashKaroUtility cu = new CashKaroUtility(udid, port, automationName, deviceName);
			driver = cu.launchChromeWebView(driver);
			AdminCoreFunction adminFunctions = new AdminCoreFunction(driver, testInfo);
			adminFunctions.naviagateAdminPage_ThenSelectEarnKaro();
			adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
			adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
			adminFunctions.clickSubmit();

			adminFunctions.clickOnHamburgerMenu();
			adminFunctions.clickOnEmailMaster();
			adminFunctions.clickOnEmailMasterBrowser();
			adminFunctions.selectEmailFromSearchByDropDown_EmailMaster();
			adminFunctions.enterUserEmailAsKeyForSearchEmailMaster(user);
			adminFunctions.clickOnSearchButtonEmailMaster();

			EmailMasterBrowserPage emailMasterBrowserpage = new EmailMasterBrowserPage(driver, testInfo);

			emailMasterBrowserpage.clickOnSendNotification();
			emailMasterBrowserpage.clickOnWithoutNotificationTemplate();
			emailMasterBrowserpage.enterAppNotificationTitle(testdata.get(17).get("NotificationTitle"));
			emailMasterBrowserpage.enterImageURL(testdata.get(17).get("BigImage"));
			emailMasterBrowserpage.enterAppNotificationBody(testdata.get(17).get("NotificationBody"));
			emailMasterBrowserpage.enterBigText(testdata.get(17).get("BigText"));
			emailMasterBrowserpage.enterRedirectionalURL(testdata.get(17).get("RedirectionalURL"));
			emailMasterBrowserpage.enterSubText(testdata.get(17).get("SubText"));
			emailMasterBrowserpage.clickOnSubmit();

			Set<String> contextNames = driver.getContextHandles();
			driver.context("NATIVE_APP");

			toggleApp(driver);
			clickEarnkaroApp(driver);
			new HomePage(driver, testInfo);
			toggleApp();
			clickChromeApp();

			driver.context((String) contextNames.toArray()[1]);
			emailMasterBrowserpage.clickOnConfirmButton();
			driver.context("NATIVE_APP");

			//Open the Notification bar :
			openNotification();

			new NotificationPage(driver, testInfo).
			validateNotificationTitle().
			validateNotificationBody(deviceName).
			clickOnExpand(deviceName).
			validateBigImage(deviceName).
			validateBigText().
			validateTheAbsenceoflargeIcon(deviceName).
			clickOnBigImage().
			validateAjioStoreRedirection();

		}catch (Exception e) {

			closeNotification();
			reportStep("Test FAILed , unexpectedly :(", "FAIL");

		}

	}

	@Parameters({"deviceName","user"})
	@Test
	public void notificationTest_AppClosed_WithoutLargeIcon(String deviceName,String user) {

		try {
			// TEST STEPS :
			new EKOnboardingPage(driver, testInfo).
			clickOnSignInbutton().
			enterUserName(user).
			enterPassword().
			clickSignInButtonForSuccess();

			//Open the Notification bar :
			openNotification();

			//Clear the Notification
			clearNotificationFromNotificationBar(deviceName);

			closeNotification();

			CashKaroUtility cu = new CashKaroUtility(udid, port, automationName, deviceName);
			driver = cu.launchChromeWebView(driver);
			AdminCoreFunction adminFunctions = new AdminCoreFunction(driver, testInfo);
			adminFunctions.naviagateAdminPage_ThenSelectEarnKaro();
			adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
			adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
			adminFunctions.clickSubmit();

			adminFunctions.clickOnHamburgerMenu();
			adminFunctions.clickOnEmailMaster();
			adminFunctions.clickOnEmailMasterBrowser();
			adminFunctions.selectEmailFromSearchByDropDown_EmailMaster();
			adminFunctions.enterUserEmailAsKeyForSearchEmailMaster(user);
			adminFunctions.clickOnSearchButtonEmailMaster();

			EmailMasterBrowserPage emailMasterBrowserpage = new EmailMasterBrowserPage(driver, testInfo);

			emailMasterBrowserpage.clickOnSendNotification();
			emailMasterBrowserpage.clickOnWithoutNotificationTemplate();
			emailMasterBrowserpage.enterAppNotificationTitle(testdata.get(17).get("NotificationTitle"));
			emailMasterBrowserpage.enterImageURL(testdata.get(17).get("BigImage"));
			emailMasterBrowserpage.enterAppNotificationBody(testdata.get(17).get("NotificationBody"));
			emailMasterBrowserpage.enterBigText(testdata.get(17).get("BigText"));
			emailMasterBrowserpage.enterRedirectionalURL(testdata.get(17).get("RedirectionalURL"));
			emailMasterBrowserpage.enterSubText(testdata.get(17).get("SubText"));
			emailMasterBrowserpage.clickOnSubmit();

			Set<String> contextNames = driver.getContextHandles();
			driver.context("NATIVE_APP");

			toggleApp(driver);
			clickEarnkaroApp(driver);
			new HomePage(driver, testInfo);
			toggleApp();
			closeEarnKaroApp(deviceName);
			clickChromeApp();

			driver.context((String) contextNames.toArray()[1]);
			emailMasterBrowserpage.clickOnConfirmButton();
			driver.context("NATIVE_APP");

			//Open the Notification bar :
			openNotification();

			new NotificationPage(driver, testInfo).
			validateNotificationTitle().
			validateNotificationBody(deviceName).
			clickOnExpand(deviceName).
			validateBigImage(deviceName).
			validateBigText().
			validateTheAbsenceoflargeIcon(deviceName).
			clickOnBigImage().
			validateAjioStoreRedirection();

		} catch (Exception e) {

			closeNotification();
			reportStep("Test FAILed , unexpectedly - May be Notification arrived late - Please look into the Step by Step report and Analyse the FAILured :(", "FAIL");

		}


	}

	@Parameters({"deviceName","user"})
	@Test
	public void notificationTest_AppBackGround_WithoutRedirectionURL(String deviceName,String user) {

		try {
			// TEST STEPS :
			new EKOnboardingPage(driver, testInfo).
			clickOnSignInbutton().
			enterUserName(user).
			enterPassword().
			clickSignInButtonForSuccess();

			//Open the Notification bar :
			openNotification();

			//Clear the Notification
			clearNotificationFromNotificationBar(deviceName);

			closeNotification();

			CashKaroUtility cu = new CashKaroUtility(udid, port, automationName, deviceName);
			driver = cu.launchChromeWebView(driver);
			AdminCoreFunction adminFunctions = new AdminCoreFunction(driver, testInfo);
			adminFunctions.naviagateAdminPage_ThenSelectEarnKaro();
			adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
			adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
			adminFunctions.clickSubmit();

			adminFunctions.clickOnHamburgerMenu();
			adminFunctions.clickOnEmailMaster();
			adminFunctions.clickOnEmailMasterBrowser();
			adminFunctions.selectEmailFromSearchByDropDown_EmailMaster();
			adminFunctions.enterUserEmailAsKeyForSearchEmailMaster(user);
			adminFunctions.clickOnSearchButtonEmailMaster();

			EmailMasterBrowserPage emailMasterBrowserpage = new EmailMasterBrowserPage(driver, testInfo);

			emailMasterBrowserpage.clickOnSendNotification();
			emailMasterBrowserpage.clickOnWithoutNotificationTemplate();
			emailMasterBrowserpage.enterAppNotificationTitle(testdata.get(17).get("NotificationTitle"));
			emailMasterBrowserpage.enterImageURL(testdata.get(17).get("BigImage"));
			emailMasterBrowserpage.enterAppNotificationBody(testdata.get(17).get("NotificationBody"));
			emailMasterBrowserpage.enterBigText(testdata.get(17).get("BigText"));
			emailMasterBrowserpage.enterLargeIcon(GetCodeSetUP.NOTIFICATIONLARGEICON);
			emailMasterBrowserpage.enterSubText(testdata.get(17).get("SubText"));
			emailMasterBrowserpage.clickOnSubmit();

			Set<String> contextNames = driver.getContextHandles();
			driver.context("NATIVE_APP");

			toggleApp(driver);
			clickEarnkaroApp(driver);
			new HomePage(driver, testInfo);
			toggleApp();
			clickChromeApp();

			driver.context((String) contextNames.toArray()[1]);
			emailMasterBrowserpage.clickOnConfirmButton();
			driver.context("NATIVE_APP");

			//Open the Notification bar :
			openNotification();

			new NotificationPage(driver, testInfo).
			validateNotificationTitle().
			validateNotificationBody(deviceName).
			clickOnExpand(deviceName).
			validateBigImage(deviceName).
			validateBigText().
			validatelargeIcon(deviceName).
			clickOnBigImage();

			new HomePage(driver, testInfo);


		}catch (Exception e) {

			closeNotification();
			reportStep("Test FAILed , unexpectedly :(", "FAIL");

		}

	}

	@Parameters({"deviceName","user"})
	@Test
	public void notificationTest_AppClosed_WithoutRedirectionURL(String deviceName,String user) {

		try {
			// TEST STEPS :
			new EKOnboardingPage(driver, testInfo).
			clickOnSignInbutton().
			enterUserName(user).
			enterPassword().
			clickSignInButtonForSuccess();

			//Open the Notification bar :
			openNotification();

			//Clear the Notification
			clearNotificationFromNotificationBar(deviceName);

			closeNotification();

			CashKaroUtility cu = new CashKaroUtility(udid, port, automationName, deviceName);
			driver = cu.launchChromeWebView(driver);
			AdminCoreFunction adminFunctions = new AdminCoreFunction(driver, testInfo);
			adminFunctions.naviagateAdminPage_ThenSelectEarnKaro();
			adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
			adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
			adminFunctions.clickSubmit();

			adminFunctions.clickOnHamburgerMenu();
			adminFunctions.clickOnEmailMaster();
			adminFunctions.clickOnEmailMasterBrowser();
			adminFunctions.selectEmailFromSearchByDropDown_EmailMaster();
			adminFunctions.enterUserEmailAsKeyForSearchEmailMaster(user);
			adminFunctions.clickOnSearchButtonEmailMaster();

			EmailMasterBrowserPage emailMasterBrowserpage = new EmailMasterBrowserPage(driver, testInfo);

			emailMasterBrowserpage.clickOnSendNotification();
			emailMasterBrowserpage.clickOnWithoutNotificationTemplate();
			emailMasterBrowserpage.enterAppNotificationTitle(testdata.get(17).get("NotificationTitle"));
			emailMasterBrowserpage.enterImageURL(testdata.get(17).get("BigImage"));
			emailMasterBrowserpage.enterAppNotificationBody(testdata.get(17).get("NotificationBody"));
			emailMasterBrowserpage.enterBigText(testdata.get(17).get("BigText"));
			emailMasterBrowserpage.enterLargeIcon(GetCodeSetUP.NOTIFICATIONLARGEICON);
			emailMasterBrowserpage.enterSubText(testdata.get(17).get("SubText"));
			emailMasterBrowserpage.clickOnSubmit();

			Set<String> contextNames = driver.getContextHandles();
			driver.context("NATIVE_APP");

			toggleApp(driver);
			clickEarnkaroApp(driver);
			new HomePage(driver, testInfo);
			toggleApp();
			closeEarnKaroApp(deviceName);
			clickChromeApp();

			driver.context((String) contextNames.toArray()[1]);
			emailMasterBrowserpage.clickOnConfirmButton();
			driver.context("NATIVE_APP");

			//Open the Notification bar :
			openNotification();

			new NotificationPage(driver, testInfo).
			validateNotificationTitle().
			validateNotificationBody(deviceName).
			clickOnExpand(deviceName).
			validateBigImage(deviceName).
			validateBigText().
			validatelargeIcon(deviceName).
			clickOnBigImage();

			new HomePage(driver, testInfo);

		} catch (Exception e) {

			closeNotification();
			reportStep("Test FAILed , unexpectedly - May be Notification arrived late - Please look into the Step by Step report and Analyse the FAILured :(", "FAIL");

		}


	}

	@Parameters({"deviceName","user"})
	@Test
	public void notificationTest_AppBackGround_WithoutBigImage(String deviceName,String user) {

		try {
			// TEST STEPS :
			new EKOnboardingPage(driver, testInfo).
			clickOnSignInbutton().
			enterUserName(user).
			enterPassword().
			clickSignInButtonForSuccess();

			//Open the Notification bar :
			openNotification();

			//Clear the Notification
			clearNotificationFromNotificationBar(deviceName);

			closeNotification();

			CashKaroUtility cu = new CashKaroUtility(udid, port, automationName, deviceName);
			driver = cu.launchChromeWebView(driver);
			AdminCoreFunction adminFunctions = new AdminCoreFunction(driver, testInfo);
			adminFunctions.naviagateAdminPage_ThenSelectEarnKaro();
			adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
			adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
			adminFunctions.clickSubmit();

			adminFunctions.clickOnHamburgerMenu();
			adminFunctions.clickOnEmailMaster();
			adminFunctions.clickOnEmailMasterBrowser();
			adminFunctions.selectEmailFromSearchByDropDown_EmailMaster();
			adminFunctions.enterUserEmailAsKeyForSearchEmailMaster(user);
			adminFunctions.clickOnSearchButtonEmailMaster();

			EmailMasterBrowserPage emailMasterBrowserpage = new EmailMasterBrowserPage(driver, testInfo);

			emailMasterBrowserpage.clickOnSendNotification();
			emailMasterBrowserpage.clickOnWithoutNotificationTemplate();
			emailMasterBrowserpage.enterAppNotificationTitle(testdata.get(17).get("NotificationTitle"));
			emailMasterBrowserpage.enterAppNotificationBody(testdata.get(17).get("NotificationBody"));
			emailMasterBrowserpage.enterBigText(testdata.get(17).get("BigText"));
			emailMasterBrowserpage.enterRedirectionalURL(testdata.get(17).get("RedirectionalURL"));
			emailMasterBrowserpage.enterLargeIcon(GetCodeSetUP.NOTIFICATIONLARGEICON);
			emailMasterBrowserpage.enterSubText(testdata.get(17).get("SubText"));
			emailMasterBrowserpage.clickOnSubmit();

			Set<String> contextNames = driver.getContextHandles();
			driver.context("NATIVE_APP");

			toggleApp(driver);
			clickEarnkaroApp(driver);
			new HomePage(driver, testInfo);
			toggleApp();
			clickChromeApp();

			driver.context((String) contextNames.toArray()[1]);
			emailMasterBrowserpage.clickOnConfirmButton();
			driver.context("NATIVE_APP");

			//Open the Notification bar :
			openNotification();

			new NotificationPage(driver, testInfo).
			validateNotificationTitle().
			validateTheAbsenceofBigImage().
			validateBigText().
			validatelargeIcon(deviceName).
			clickOnNotificationTitle().
			validateAjioStoreRedirection();

		}catch (Exception e) {

			closeNotification();
			reportStep("Test FAILed , unexpectedly :(", "FAIL");

		}

	}

	@Parameters({"deviceName","user"})
	@Test
	public void notificationTest_AppClosed_WithoutBigImage(String deviceName,String user) {

		try {
			// TEST STEPS :
			new EKOnboardingPage(driver, testInfo).
			clickOnSignInbutton().
			enterUserName(user).
			enterPassword().
			clickSignInButtonForSuccess();

			//Open the Notification bar :
			openNotification();

			//Clear the Notification
			clearNotificationFromNotificationBar(deviceName);

			closeNotification();

			CashKaroUtility cu = new CashKaroUtility(udid, port, automationName, deviceName);
			driver = cu.launchChromeWebView(driver);
			AdminCoreFunction adminFunctions = new AdminCoreFunction(driver, testInfo);
			adminFunctions.naviagateAdminPage_ThenSelectEarnKaro();
			adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
			adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
			adminFunctions.clickSubmit();

			adminFunctions.clickOnHamburgerMenu();
			adminFunctions.clickOnEmailMaster();
			adminFunctions.clickOnEmailMasterBrowser();
			adminFunctions.selectEmailFromSearchByDropDown_EmailMaster();
			adminFunctions.enterUserEmailAsKeyForSearchEmailMaster(user);
			adminFunctions.clickOnSearchButtonEmailMaster();

			EmailMasterBrowserPage emailMasterBrowserpage = new EmailMasterBrowserPage(driver, testInfo);

			emailMasterBrowserpage.clickOnSendNotification();
			emailMasterBrowserpage.clickOnWithoutNotificationTemplate();
			emailMasterBrowserpage.enterAppNotificationTitle(testdata.get(17).get("NotificationTitle"));
			emailMasterBrowserpage.enterAppNotificationBody(testdata.get(17).get("NotificationBody"));
			emailMasterBrowserpage.enterBigText(testdata.get(17).get("BigText"));
			emailMasterBrowserpage.enterRedirectionalURL(testdata.get(17).get("RedirectionalURL"));
			emailMasterBrowserpage.enterLargeIcon(GetCodeSetUP.NOTIFICATIONLARGEICON);
			emailMasterBrowserpage.enterSubText(testdata.get(17).get("SubText"));
			emailMasterBrowserpage.clickOnSubmit();

			Set<String> contextNames = driver.getContextHandles();
			driver.context("NATIVE_APP");

			toggleApp(driver);
			clickEarnkaroApp(driver);
			new HomePage(driver, testInfo);
			toggleApp();
			closeEarnKaroApp(deviceName);
			clickChromeApp();

			driver.context((String) contextNames.toArray()[1]);
			emailMasterBrowserpage.clickOnConfirmButton();
			driver.context("NATIVE_APP");

			//Open the Notification bar :
			openNotification();

			new NotificationPage(driver, testInfo).
			validateNotificationTitle().
			validateBigText().
			validatelargeIcon(deviceName).
			clickOnNotificationTitle().
			validateAjioStoreRedirection();

		} catch (Exception e) {

			closeNotification();
			reportStep("Test FAILed , unexpectedly - May be Notification arrived late - Please look into the Step by Step report and Analyse the FAILured :(", "FAIL");

		}


	}

	//After logout Negative scenarios : Negative flow
	@Parameters({"deviceName","user"})
	//@Test
	public void notificationTest_AppBackGround_LogoutThenClickNotificaiton(String deviceName,String user) {

		try {
			// TEST STEPS :
			new EKOnboardingPage(driver, testInfo).
			clickOnSignInbutton().
			enterUserName(user).
			enterPassword().
			clickSignInButtonForSuccess();

			//Open the Notification bar :
			openNotification();

			//Clear the Notification
			clearNotificationFromNotificationBar(deviceName);

			closeNotification();

			CashKaroUtility cu = new CashKaroUtility(udid, port, automationName, deviceName);
			driver = cu.launchChromeWebView(driver);
			AdminCoreFunction adminFunctions = new AdminCoreFunction(driver, testInfo);
			adminFunctions.naviagateAdminPage_ThenSelectEarnKaro();
			adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
			adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
			adminFunctions.clickSubmit();

			adminFunctions.clickOnHamburgerMenu();
			adminFunctions.clickOnEmailMaster();
			adminFunctions.clickOnEmailMasterBrowser();
			adminFunctions.selectEmailFromSearchByDropDown_EmailMaster();
			adminFunctions.enterUserEmailAsKeyForSearchEmailMaster(user);
			adminFunctions.clickOnSearchButtonEmailMaster();

			EmailMasterBrowserPage emailMasterBrowserpage = new EmailMasterBrowserPage(driver, testInfo);

			emailMasterBrowserpage.clickOnSendNotification();
			emailMasterBrowserpage.clickOnWithoutNotificationTemplate();
			emailMasterBrowserpage.enterAppNotificationTitle(testdata.get(17).get("NotificationTitle"));
			emailMasterBrowserpage.enterImageURL(testdata.get(17).get("BigImage"));
			emailMasterBrowserpage.enterAppNotificationBody(testdata.get(17).get("NotificationBody"));
			emailMasterBrowserpage.enterBigText(testdata.get(17).get("BigText"));
			emailMasterBrowserpage.enterRedirectionalURL(testdata.get(17).get("RedirectionalURL"));
			emailMasterBrowserpage.enterLargeIcon(GetCodeSetUP.NOTIFICATIONLARGEICON);
			emailMasterBrowserpage.enterSubText(testdata.get(17).get("SubText"));
			emailMasterBrowserpage.clickOnSubmit();

			Set<String> contextNames = driver.getContextHandles();
			driver.context("NATIVE_APP");

			toggleApp(driver);

			clickEarnkaroApp(driver);
			new HomePage(driver, testInfo);

			toggleApp();
			clickChromeApp();

			driver.context((String) contextNames.toArray()[1]);
			emailMasterBrowserpage.clickOnConfirmButton();
			driver.context("NATIVE_APP");

			toggleApp(driver);
			clickEarnkaroApp(driver);
			new HomePage(driver, testInfo).
			clickOnProfileIconForSignedUser().
			clickOnLogout();

			//Open the Notification bar :
			openNotification();

			new NotificationPage(driver, testInfo).
			validateNotificationTitle().
			validateNotificationBody(deviceName).
			clickOnExpand(deviceName).
			validateBigImage(deviceName).
			validateBigText().
			validatelargeIcon(deviceName).
			clickOnBigImage();

			new SignInPage(driver, testInfo);


		}catch (Exception e) {

			closeNotification();
			reportStep("Test FAILed , unexpectedly :(", "FAIL");

		}

	}

	@Parameters({"deviceName","user"})
	//@Test
	public void notificationTest_AppClosed_LogoutThenClickNotificaiton(String deviceName,String user) {

		try {
			// TEST STEPS :
			new EKOnboardingPage(driver, testInfo).
			clickOnSignInbutton().
			enterUserName(user).
			enterPassword().
			clickSignInButtonForSuccess();

			//Open the Notification bar :
			openNotification();

			//Clear the Notification
			clearNotificationFromNotificationBar(deviceName);

			closeNotification();

			CashKaroUtility cu = new CashKaroUtility(udid, port, automationName, deviceName);
			driver = cu.launchChromeWebView(driver);
			AdminCoreFunction adminFunctions = new AdminCoreFunction(driver, testInfo);
			adminFunctions.naviagateAdminPage_ThenSelectEarnKaro();
			adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
			adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
			adminFunctions.clickSubmit();

			adminFunctions.clickOnHamburgerMenu();
			adminFunctions.clickOnEmailMaster();
			adminFunctions.clickOnEmailMasterBrowser();
			adminFunctions.selectEmailFromSearchByDropDown_EmailMaster();
			adminFunctions.enterUserEmailAsKeyForSearchEmailMaster(user);
			adminFunctions.clickOnSearchButtonEmailMaster();

			EmailMasterBrowserPage emailMasterBrowserpage = new EmailMasterBrowserPage(driver, testInfo);

			emailMasterBrowserpage.clickOnSendNotification();
			emailMasterBrowserpage.clickOnWithoutNotificationTemplate();
			emailMasterBrowserpage.enterAppNotificationTitle(testdata.get(17).get("NotificationTitle"));
			emailMasterBrowserpage.enterImageURL(testdata.get(17).get("BigImage"));
			emailMasterBrowserpage.enterAppNotificationBody(testdata.get(17).get("NotificationBody"));
			emailMasterBrowserpage.enterBigText(testdata.get(17).get("BigText"));
			emailMasterBrowserpage.enterRedirectionalURL(testdata.get(17).get("RedirectionalURL"));
			emailMasterBrowserpage.enterLargeIcon(GetCodeSetUP.NOTIFICATIONLARGEICON);
			emailMasterBrowserpage.enterSubText(testdata.get(17).get("SubText"));
			emailMasterBrowserpage.clickOnSubmit();

			Set<String> contextNames = driver.getContextHandles();
			driver.context("NATIVE_APP");

			toggleApp(driver);
			clickEarnkaroApp(driver);
			new HomePage(driver, testInfo);

			toggleApp();
			clickChromeApp();

			driver.context((String) contextNames.toArray()[1]);
			emailMasterBrowserpage.clickOnConfirmButton();
			driver.context("NATIVE_APP");

			toggleApp(driver);
			clickEarnkaroApp(driver);

			new HomePage(driver, testInfo).
			clickOnProfileIconForSignedUser().
			clickOnLogout();

			toggleApp();
			closeEarnKaroApp(deviceName);
			clickChromeApp();

			//Open the Notification bar :
			openNotification();

			new NotificationPage(driver, testInfo).
			validateNotificationTitle().
			validateNotificationBody(deviceName).
			clickOnExpand(deviceName).
			validateBigImage(deviceName).
			validateBigText().
			validatelargeIcon(deviceName).
			clickOnBigImage();

			new SignInPage(driver, testInfo);

		} catch (Exception e) {

			closeNotification();
			reportStep("Test FAILed , unexpectedly - May be Notification arrived late - Please look into the Step by Step report and Analyse the FAILured :(", "FAIL");

		}


	}

	@Parameters({"deviceName","user"})
	//@Test
	public void notificationTest_AppBackGround_WithOutImageURL_WithoutRedirectionURL_WithoutLargeIcon_LogoutThenClickNotificaiton(String deviceName,String user) {

		try {
			// TEST STEPS :
			new EKOnboardingPage(driver, testInfo).
			clickOnSignInbutton().
			enterUserName(user).
			enterPassword().
			clickSignInButtonForSuccess();

			//Open the Notification bar :
			openNotification();

			//Clear the Notification
			clearNotificationFromNotificationBar(deviceName);

			closeNotification();

			CashKaroUtility cu = new CashKaroUtility(udid, port, automationName, deviceName);
			driver = cu.launchChromeWebView(driver);
			AdminCoreFunction adminFunctions = new AdminCoreFunction(driver, testInfo);
			adminFunctions.naviagateAdminPage_ThenSelectEarnKaro();
			adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
			adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
			adminFunctions.clickSubmit();

			adminFunctions.clickOnHamburgerMenu();
			adminFunctions.clickOnEmailMaster();
			adminFunctions.clickOnEmailMasterBrowser();
			adminFunctions.selectEmailFromSearchByDropDown_EmailMaster();
			adminFunctions.enterUserEmailAsKeyForSearchEmailMaster(user);
			adminFunctions.clickOnSearchButtonEmailMaster();

			EmailMasterBrowserPage emailMasterBrowserpage = new EmailMasterBrowserPage(driver, testInfo);

			emailMasterBrowserpage.clickOnSendNotification();
			emailMasterBrowserpage.clickOnWithoutNotificationTemplate();
			emailMasterBrowserpage.enterAppNotificationTitle(testdata.get(17).get("NotificationTitle"));
			emailMasterBrowserpage.enterAppNotificationBody(testdata.get(17).get("NotificationBody"));
			emailMasterBrowserpage.enterBigText(testdata.get(17).get("BigText"));
			emailMasterBrowserpage.enterSubText(testdata.get(17).get("SubText"));
			emailMasterBrowserpage.clickOnSubmit();

			Set<String> contextNames = driver.getContextHandles();
			driver.context("NATIVE_APP");

			toggleApp(driver);
			clickEarnkaroApp(driver);
			new HomePage(driver, testInfo);

			toggleApp();
			clickChromeApp();

			driver.context((String) contextNames.toArray()[1]);
			emailMasterBrowserpage.clickOnConfirmButton();
			driver.context("NATIVE_APP");

			toggleApp(driver);
			clickEarnkaroApp(driver);
			new HomePage(driver, testInfo).
			clickOnProfileIconForSignedUser().
			clickOnLogout();

			//Open the Notification bar :
			openNotification();

			new NotificationPage(driver, testInfo).
			validateNotificationTitle().
			validateBigText().
			clickOnNotificationTitle();

			new EKOnboardingPage(driver, testInfo);


		}catch (Exception e) {

			closeNotification();
			reportStep("Test FAILed , unexpectedly :(", "FAIL");

		}

	}

	@Parameters({"deviceName","user"})
	//@Test
	public void notificationTest_AppClosed_WithOutImageURL_WithoutRedirectionURL_WithoutLargeIcon_LogoutThenClickNotificaiton(String deviceName,String user) {

		try {
			// TEST STEPS :
			new EKOnboardingPage(driver, testInfo).
			clickOnSignInbutton().
			enterUserName(user).
			enterPassword().
			clickSignInButtonForSuccess();

			//Open the Notification bar :
			openNotification();

			//Clear the Notification
			clearNotificationFromNotificationBar(deviceName);

			closeNotification();

			CashKaroUtility cu = new CashKaroUtility(udid, port, automationName, deviceName);
			driver = cu.launchChromeWebView(driver);
			AdminCoreFunction adminFunctions = new AdminCoreFunction(driver, testInfo);
			adminFunctions.naviagateAdminPage_ThenSelectEarnKaro();
			adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
			adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
			adminFunctions.clickSubmit();

			adminFunctions.clickOnHamburgerMenu();
			adminFunctions.clickOnEmailMaster();
			adminFunctions.clickOnEmailMasterBrowser();
			adminFunctions.selectEmailFromSearchByDropDown_EmailMaster();
			adminFunctions.enterUserEmailAsKeyForSearchEmailMaster(user);
			adminFunctions.clickOnSearchButtonEmailMaster();

			EmailMasterBrowserPage emailMasterBrowserpage = new EmailMasterBrowserPage(driver, testInfo);

			emailMasterBrowserpage.clickOnSendNotification();
			emailMasterBrowserpage.clickOnWithoutNotificationTemplate();
			emailMasterBrowserpage.enterAppNotificationTitle(testdata.get(17).get("NotificationTitle"));
			emailMasterBrowserpage.enterAppNotificationBody(testdata.get(17).get("NotificationBody"));
			emailMasterBrowserpage.enterBigText(testdata.get(17).get("BigText"));
			emailMasterBrowserpage.enterSubText(testdata.get(17).get("SubText"));
			emailMasterBrowserpage.clickOnSubmit();

			Set<String> contextNames = driver.getContextHandles();
			driver.context("NATIVE_APP");

			toggleApp(driver);
			clickEarnkaroApp(driver);
			new HomePage(driver, testInfo);

			toggleApp();
			clickChromeApp();

			driver.context((String) contextNames.toArray()[1]);
			emailMasterBrowserpage.clickOnConfirmButton();
			driver.context("NATIVE_APP");

			toggleApp(driver);
			clickEarnkaroApp(driver);
			new HomePage(driver, testInfo).
			clickOnProfileIconForSignedUser().
			clickOnLogout();

			toggleApp();
			closeEarnKaroApp(deviceName);
			clickChromeApp();

			//Open the Notification bar :
			openNotification();

			new NotificationPage(driver, testInfo).
			validateNotificationTitle().
			validateBigText().
			clickOnNotificationTitle();

			new EKOnboardingPage(driver, testInfo);

		} catch (Exception e) {

			closeNotification();
			reportStep("Test FAILed , unexpectedly - May be Notification arrived late - Please look into the Step by Step report and Analyse the FAILured :(", "FAIL");

		}


	}

	@Parameters({"deviceName","user"})
	//@Test
	public void notificationTest_AppBackGround_WithoutLargeIcon_LogoutThenClickNotificaiton(String deviceName,String user) {

		try {
			// TEST STEPS :
			new EKOnboardingPage(driver, testInfo).
			clickOnSignInbutton().
			enterUserName(user).
			enterPassword().
			clickSignInButtonForSuccess();

			//Open the Notification bar :
			openNotification();

			//Clear the Notification
			clearNotificationFromNotificationBar(deviceName);

			closeNotification();

			CashKaroUtility cu = new CashKaroUtility(udid, port, automationName, deviceName);
			driver = cu.launchChromeWebView(driver);
			AdminCoreFunction adminFunctions = new AdminCoreFunction(driver, testInfo);
			adminFunctions.naviagateAdminPage_ThenSelectEarnKaro();
			adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
			adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
			adminFunctions.clickSubmit();

			adminFunctions.clickOnHamburgerMenu();
			adminFunctions.clickOnEmailMaster();
			adminFunctions.clickOnEmailMasterBrowser();
			adminFunctions.selectEmailFromSearchByDropDown_EmailMaster();
			adminFunctions.enterUserEmailAsKeyForSearchEmailMaster(user);
			adminFunctions.clickOnSearchButtonEmailMaster();

			EmailMasterBrowserPage emailMasterBrowserpage = new EmailMasterBrowserPage(driver, testInfo);

			emailMasterBrowserpage.clickOnSendNotification();
			emailMasterBrowserpage.clickOnWithoutNotificationTemplate();
			emailMasterBrowserpage.enterAppNotificationTitle(testdata.get(17).get("NotificationTitle"));
			emailMasterBrowserpage.enterImageURL(testdata.get(17).get("BigImage"));
			emailMasterBrowserpage.enterAppNotificationBody(testdata.get(17).get("NotificationBody"));
			emailMasterBrowserpage.enterBigText(testdata.get(17).get("BigText"));
			emailMasterBrowserpage.enterRedirectionalURL(testdata.get(17).get("RedirectionalURL"));
			emailMasterBrowserpage.enterSubText(testdata.get(17).get("SubText"));
			emailMasterBrowserpage.clickOnSubmit();

			Set<String> contextNames = driver.getContextHandles();
			driver.context("NATIVE_APP");

			toggleApp(driver);
			clickEarnkaroApp(driver);

			new HomePage(driver, testInfo);

			toggleApp();
			clickChromeApp();

			driver.context((String) contextNames.toArray()[1]);
			emailMasterBrowserpage.clickOnConfirmButton();
			driver.context("NATIVE_APP");

			toggleApp(driver);
			clickEarnkaroApp(driver);
			new HomePage(driver, testInfo).
			clickOnProfileIconForSignedUser().
			clickOnLogout();

			//Open the Notification bar :
			openNotification();

			new NotificationPage(driver, testInfo).
			validateNotificationTitle().
			validateNotificationBody(deviceName).
			clickOnExpand(deviceName).
			validateBigImage(deviceName).
			validateBigText().
			validateTheAbsenceoflargeIcon(deviceName).
			clickOnBigImage();

			new SignInPage(driver, testInfo);

		}catch (Exception e) {

			closeNotification();
			reportStep("Test FAILed , unexpectedly :(", "FAIL");

		}

	}

	@Parameters({"deviceName","user"})
	//@Test
	public void notificationTest_AppClosed_WithoutLargeIcon_LogoutThenClickNotificaiton(String deviceName,String user) {

		try {
			// TEST STEPS :
			new EKOnboardingPage(driver, testInfo).
			clickOnSignInbutton().
			enterUserName(user).
			enterPassword().
			clickSignInButtonForSuccess();

			//Open the Notification bar :
			openNotification();

			//Clear the Notification
			clearNotificationFromNotificationBar(deviceName);

			closeNotification();

			CashKaroUtility cu = new CashKaroUtility(udid, port, automationName, deviceName);
			driver = cu.launchChromeWebView(driver);
			AdminCoreFunction adminFunctions = new AdminCoreFunction(driver, testInfo);
			adminFunctions.naviagateAdminPage_ThenSelectEarnKaro();
			adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
			adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
			adminFunctions.clickSubmit();

			adminFunctions.clickOnHamburgerMenu();
			adminFunctions.clickOnEmailMaster();
			adminFunctions.clickOnEmailMasterBrowser();
			adminFunctions.selectEmailFromSearchByDropDown_EmailMaster();
			adminFunctions.enterUserEmailAsKeyForSearchEmailMaster(user);
			adminFunctions.clickOnSearchButtonEmailMaster();

			EmailMasterBrowserPage emailMasterBrowserpage = new EmailMasterBrowserPage(driver, testInfo);

			emailMasterBrowserpage.clickOnSendNotification();
			emailMasterBrowserpage.clickOnWithoutNotificationTemplate();
			emailMasterBrowserpage.enterAppNotificationTitle(testdata.get(17).get("NotificationTitle"));
			emailMasterBrowserpage.enterImageURL(testdata.get(17).get("BigImage"));
			emailMasterBrowserpage.enterAppNotificationBody(testdata.get(17).get("NotificationBody"));
			emailMasterBrowserpage.enterBigText(testdata.get(17).get("BigText"));
			emailMasterBrowserpage.enterRedirectionalURL(testdata.get(17).get("RedirectionalURL"));
			emailMasterBrowserpage.enterSubText(testdata.get(17).get("SubText"));
			emailMasterBrowserpage.clickOnSubmit();

			Set<String> contextNames = driver.getContextHandles();
			driver.context("NATIVE_APP");

			toggleApp(driver);
			clickEarnkaroApp(driver);
			new HomePage(driver, testInfo);

			toggleApp();
			clickChromeApp();

			driver.context((String) contextNames.toArray()[1]);
			emailMasterBrowserpage.clickOnConfirmButton();
			driver.context("NATIVE_APP");

			toggleApp(driver);
			clickEarnkaroApp(driver);
			new HomePage(driver, testInfo).
			clickOnProfileIconForSignedUser().
			clickOnLogout();

			toggleApp();
			closeEarnKaroApp(deviceName);
			clickChromeApp();

			//Open the Notification bar :
			openNotification();

			new NotificationPage(driver, testInfo).
			validateNotificationTitle().
			validateNotificationBody(deviceName).
			clickOnExpand(deviceName).
			validateBigImage(deviceName).
			validateBigText().
			validateTheAbsenceoflargeIcon(deviceName).
			clickOnBigImage();

			new SignInPage(driver, testInfo);

		} catch (Exception e) {

			closeNotification();
			reportStep("Test FAILed , unexpectedly - May be Notification arrived late - Please look into the Step by Step report and Analyse the FAILured :(", "FAIL");

		}


	}

	@Parameters({"deviceName","user"})
	//@Test
	public void notificationTest_AppBackGround_WithoutRedirectionURL_LogoutThenClickNotificaiton(String deviceName,String user) {

		try {
			// TEST STEPS :
			new EKOnboardingPage(driver, testInfo).
			clickOnSignInbutton().
			enterUserName(user).
			enterPassword().
			clickSignInButtonForSuccess();

			//Open the Notification bar :
			openNotification();

			//Clear the Notification
			clearNotificationFromNotificationBar(deviceName);

			closeNotification();

			CashKaroUtility cu = new CashKaroUtility(udid, port, automationName, deviceName);
			driver = cu.launchChromeWebView(driver);
			AdminCoreFunction adminFunctions = new AdminCoreFunction(driver, testInfo);
			adminFunctions.naviagateAdminPage_ThenSelectEarnKaro();
			adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
			adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
			adminFunctions.clickSubmit();

			adminFunctions.clickOnHamburgerMenu();
			adminFunctions.clickOnEmailMaster();
			adminFunctions.clickOnEmailMasterBrowser();
			adminFunctions.selectEmailFromSearchByDropDown_EmailMaster();
			adminFunctions.enterUserEmailAsKeyForSearchEmailMaster(user);
			adminFunctions.clickOnSearchButtonEmailMaster();

			EmailMasterBrowserPage emailMasterBrowserpage = new EmailMasterBrowserPage(driver, testInfo);

			emailMasterBrowserpage.clickOnSendNotification();
			emailMasterBrowserpage.clickOnWithoutNotificationTemplate();
			emailMasterBrowserpage.enterAppNotificationTitle(testdata.get(17).get("NotificationTitle"));
			emailMasterBrowserpage.enterImageURL(testdata.get(17).get("BigImage"));
			emailMasterBrowserpage.enterAppNotificationBody(testdata.get(17).get("NotificationBody"));
			emailMasterBrowserpage.enterBigText(testdata.get(17).get("BigText"));
			emailMasterBrowserpage.enterLargeIcon(GetCodeSetUP.NOTIFICATIONLARGEICON);
			emailMasterBrowserpage.enterSubText(testdata.get(17).get("SubText"));
			emailMasterBrowserpage.clickOnSubmit();

			Set<String> contextNames = driver.getContextHandles();
			driver.context("NATIVE_APP");

			toggleApp(driver);
			clickEarnkaroApp(driver);
			new HomePage(driver, testInfo);

			toggleApp();
			clickChromeApp();

			driver.context((String) contextNames.toArray()[1]);
			emailMasterBrowserpage.clickOnConfirmButton();
			driver.context("NATIVE_APP");

			toggleApp(driver);
			clickEarnkaroApp(driver);
			new HomePage(driver, testInfo).
			clickOnProfileIconForSignedUser().
			clickOnLogout();

			//Open the Notification bar :
			openNotification();

			new NotificationPage(driver, testInfo).
			validateNotificationTitle().
			validateNotificationBody(deviceName).
			clickOnExpand(deviceName).
			validateBigImage(deviceName).
			validateBigText().
			validatelargeIcon(deviceName).
			clickOnBigImage();

			new EKOnboardingPage(driver, testInfo);


		}catch (Exception e) {

			closeNotification();
			reportStep("Test FAILed , unexpectedly :(", "FAIL");

		}

	}

	@Parameters({"deviceName","user"})
	//@Test
	public void notificationTest_AppClosed_WithoutRedirectionURL_LogoutThenClickNotificaiton(String deviceName,String user) {

		try {
			// TEST STEPS :
			new EKOnboardingPage(driver, testInfo).
			clickOnSignInbutton().
			enterUserName(user).
			enterPassword().
			clickSignInButtonForSuccess();

			//Open the Notification bar :
			openNotification();

			//Clear the Notification
			clearNotificationFromNotificationBar(deviceName);

			closeNotification();

			CashKaroUtility cu = new CashKaroUtility(udid, port, automationName, deviceName);
			driver = cu.launchChromeWebView(driver);
			AdminCoreFunction adminFunctions = new AdminCoreFunction(driver, testInfo);
			adminFunctions.naviagateAdminPage_ThenSelectEarnKaro();
			adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
			adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
			adminFunctions.clickSubmit();

			adminFunctions.clickOnHamburgerMenu();
			adminFunctions.clickOnEmailMaster();
			adminFunctions.clickOnEmailMasterBrowser();
			adminFunctions.selectEmailFromSearchByDropDown_EmailMaster();
			adminFunctions.enterUserEmailAsKeyForSearchEmailMaster(user);
			adminFunctions.clickOnSearchButtonEmailMaster();

			EmailMasterBrowserPage emailMasterBrowserpage = new EmailMasterBrowserPage(driver, testInfo);

			emailMasterBrowserpage.clickOnSendNotification();
			emailMasterBrowserpage.clickOnWithoutNotificationTemplate();
			emailMasterBrowserpage.enterAppNotificationTitle(testdata.get(17).get("NotificationTitle"));
			emailMasterBrowserpage.enterImageURL(testdata.get(17).get("BigImage"));
			emailMasterBrowserpage.enterAppNotificationBody(testdata.get(17).get("NotificationBody"));
			emailMasterBrowserpage.enterBigText(testdata.get(17).get("BigText"));
			emailMasterBrowserpage.enterLargeIcon(GetCodeSetUP.NOTIFICATIONLARGEICON);
			emailMasterBrowserpage.enterSubText(testdata.get(17).get("SubText"));
			emailMasterBrowserpage.clickOnSubmit();

			Set<String> contextNames = driver.getContextHandles();
			driver.context("NATIVE_APP");

			toggleApp(driver);
			clickEarnkaroApp(driver);
			new HomePage(driver, testInfo);

			toggleApp();
			clickChromeApp();

			driver.context((String) contextNames.toArray()[1]);
			emailMasterBrowserpage.clickOnConfirmButton();
			driver.context("NATIVE_APP");

			toggleApp(driver);
			clickEarnkaroApp(driver);
			new HomePage(driver, testInfo).
			clickOnProfileIconForSignedUser().
			clickOnLogout();

			toggleApp();
			closeEarnKaroApp(deviceName);
			clickChromeApp();

			//Open the Notification bar :
			openNotification();

			new NotificationPage(driver, testInfo).
			validateNotificationTitle().
			validateNotificationBody(deviceName).
			clickOnExpand(deviceName).
			validateBigImage(deviceName).
			validateBigText().
			validatelargeIcon(deviceName).
			clickOnBigImage();

			new EKOnboardingPage(driver, testInfo);

		} catch (Exception e) {

			closeNotification();
			reportStep("Test FAILed , unexpectedly - May be Notification arrived late - Please look into the Step by Step report and Analyse the FAILured :(", "FAIL");

		}


	}

	@Parameters({"deviceName","user"})
	//@Test
	public void notificationTest_AppBackGround_WithoutBigImage_LogoutThenClickNotificaiton(String deviceName,String user) {

		try {
			// TEST STEPS :
			new EKOnboardingPage(driver, testInfo).
			clickOnSignInbutton().
			enterUserName(user).
			enterPassword().
			clickSignInButtonForSuccess();

			//Open the Notification bar :
			openNotification();

			//Clear the Notification
			clearNotificationFromNotificationBar(deviceName);

			closeNotification();

			CashKaroUtility cu = new CashKaroUtility(udid, port, automationName, deviceName);
			driver = cu.launchChromeWebView(driver);
			AdminCoreFunction adminFunctions = new AdminCoreFunction(driver, testInfo);
			adminFunctions.naviagateAdminPage_ThenSelectEarnKaro();
			adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
			adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
			adminFunctions.clickSubmit();

			adminFunctions.clickOnHamburgerMenu();
			adminFunctions.clickOnEmailMaster();
			adminFunctions.clickOnEmailMasterBrowser();
			adminFunctions.selectEmailFromSearchByDropDown_EmailMaster();
			adminFunctions.enterUserEmailAsKeyForSearchEmailMaster(user);
			adminFunctions.clickOnSearchButtonEmailMaster();

			EmailMasterBrowserPage emailMasterBrowserpage = new EmailMasterBrowserPage(driver, testInfo);

			emailMasterBrowserpage.clickOnSendNotification();
			emailMasterBrowserpage.clickOnWithoutNotificationTemplate();
			emailMasterBrowserpage.enterAppNotificationTitle(testdata.get(17).get("NotificationTitle"));
			emailMasterBrowserpage.enterAppNotificationBody(testdata.get(17).get("NotificationBody"));
			emailMasterBrowserpage.enterBigText(testdata.get(17).get("BigText"));
			emailMasterBrowserpage.enterRedirectionalURL(testdata.get(17).get("RedirectionalURL"));
			emailMasterBrowserpage.enterLargeIcon(GetCodeSetUP.NOTIFICATIONLARGEICON);
			emailMasterBrowserpage.enterSubText(testdata.get(17).get("SubText"));
			emailMasterBrowserpage.clickOnSubmit();

			Set<String> contextNames = driver.getContextHandles();
			driver.context("NATIVE_APP");

			toggleApp(driver);
			clickEarnkaroApp(driver);
			new HomePage(driver, testInfo);

			toggleApp();
			clickChromeApp();

			driver.context((String) contextNames.toArray()[1]);
			emailMasterBrowserpage.clickOnConfirmButton();
			driver.context("NATIVE_APP");

			toggleApp(driver);
			clickEarnkaroApp(driver);
			new HomePage(driver, testInfo).
			clickOnProfileIconForSignedUser().
			clickOnLogout();

			//Open the Notification bar :
			openNotification();

			new NotificationPage(driver, testInfo).
			validateNotificationTitle().
			validateTheAbsenceofBigImage().
			validateBigText().
			validatelargeIcon(deviceName).
			clickOnNotificationTitle();

			new SignInPage(driver, testInfo);



		}catch (Exception e) {

			closeNotification();
			reportStep("Test FAILed , unexpectedly :(", "FAIL");

		}

	}

	@Parameters({"deviceName","user"})
	//@Test
	public void notificationTest_AppClosed_WithoutBigImage_LogoutThenClickNotificaiton(String deviceName,String user) {

		try {
			// TEST STEPS :
			new EKOnboardingPage(driver, testInfo).
			clickOnSignInbutton().
			enterUserName(user).
			enterPassword().
			clickSignInButtonForSuccess();

			//Open the Notification bar :
			openNotification();

			//Clear the Notification
			clearNotificationFromNotificationBar(deviceName);

			closeNotification();

			CashKaroUtility cu = new CashKaroUtility(udid, port, automationName, deviceName);
			driver = cu.launchChromeWebView(driver);
			AdminCoreFunction adminFunctions = new AdminCoreFunction(driver, testInfo);
			adminFunctions.naviagateAdminPage_ThenSelectEarnKaro();
			adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
			adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
			adminFunctions.clickSubmit();

			adminFunctions.clickOnHamburgerMenu();
			adminFunctions.clickOnEmailMaster();
			adminFunctions.clickOnEmailMasterBrowser();
			adminFunctions.selectEmailFromSearchByDropDown_EmailMaster();
			adminFunctions.enterUserEmailAsKeyForSearchEmailMaster(user);
			adminFunctions.clickOnSearchButtonEmailMaster();

			EmailMasterBrowserPage emailMasterBrowserpage = new EmailMasterBrowserPage(driver, testInfo);

			emailMasterBrowserpage.clickOnSendNotification();
			emailMasterBrowserpage.clickOnWithoutNotificationTemplate();
			emailMasterBrowserpage.enterAppNotificationTitle(testdata.get(17).get("NotificationTitle"));
			emailMasterBrowserpage.enterAppNotificationBody(testdata.get(17).get("NotificationBody"));
			emailMasterBrowserpage.enterBigText(testdata.get(17).get("BigText"));
			emailMasterBrowserpage.enterRedirectionalURL(testdata.get(17).get("RedirectionalURL"));
			emailMasterBrowserpage.enterLargeIcon(GetCodeSetUP.NOTIFICATIONLARGEICON);
			emailMasterBrowserpage.enterSubText(testdata.get(17).get("SubText"));
			emailMasterBrowserpage.clickOnSubmit();

			Set<String> contextNames = driver.getContextHandles();
			driver.context("NATIVE_APP");

			toggleApp(driver);
			clickEarnkaroApp(driver);
			new HomePage(driver, testInfo);

			toggleApp();
			clickChromeApp();


			driver.context((String) contextNames.toArray()[1]);
			emailMasterBrowserpage.clickOnConfirmButton();
			driver.context("NATIVE_APP");

			toggleApp(driver);
			clickEarnkaroApp(driver);
			new HomePage(driver, testInfo).
			clickOnProfileIconForSignedUser().
			clickOnLogout();

			toggleApp();
			closeEarnKaroApp(deviceName);
			clickChromeApp();

			//Open the Notification bar :
			openNotification();

			new NotificationPage(driver, testInfo).
			validateNotificationTitle().
			validateTheAbsenceofBigImage().
			validateBigText().
			validatelargeIcon(deviceName).
			clickOnNotificationTitle();

			new SignInPage(driver, testInfo);

		} catch (Exception e) {

			closeNotification();
			reportStep("Test FAILed , unexpectedly - May be Notification arrived late - Please look into the Step by Step report and Analyse the FAILured :(", "FAIL");

		}


	}



	/*******************************************************************/
	/************************* With Template ********************/
	/*******************************************************************/
	//Positive flow :
	@Parameters({"deviceName","user"})
	@Test
	public void notificationTest_AppBackGround_WithTemplate(String deviceName,String user) {

		try {

			// TEST STEPS :
			new EKOnboardingPage(driver, testInfo).
			clickOnSignInbutton().
			enterUserName(user).
			enterPassword().
			clickSignInButtonForSuccess();

			//Open the Notification bar :
			openNotification();

			//Clear the Notification
			clearNotificationFromNotificationBar(deviceName);

			closeNotification();

			CashKaroUtility cu = new CashKaroUtility(udid, port, automationName, deviceName);
			driver = cu.launchChromeWebView(driver);
			AdminCoreFunction adminFunctions = new AdminCoreFunction(driver, testInfo);
			adminFunctions.naviagateAdminPage_ThenSelectEarnKaro();
			adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
			adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
			adminFunctions.clickSubmit();

			adminFunctions.clickOnHamburgerMenu();
			adminFunctions.clickOnEmailMaster();
			adminFunctions.clickOnEmailMasterBrowser();
			adminFunctions.selectEmailFromSearchByDropDown_EmailMaster();
			adminFunctions.enterUserEmailAsKeyForSearchEmailMaster(user);
			adminFunctions.clickOnSearchButtonEmailMaster();

			EmailMasterBrowserPage emailMasterBrowserpage = new EmailMasterBrowserPage(driver, testInfo);

			emailMasterBrowserpage.clickOnSendNotification();
			emailMasterBrowserpage.clickOnWithNotificationTemplate();
			emailMasterBrowserpage.selectHighPriorityWithNotificationTemplate();
			emailMasterBrowserpage.selectSiteTitleAsUserName();
			emailMasterBrowserpage.clickOnSendNotificatonButtonForWithTemplate();

			Set<String> contextNames = driver.getContextHandles();
			driver.context("NATIVE_APP");

			toggleApp(driver);
			clickEarnkaroApp(driver);
			new HomePage(driver, testInfo);
			toggleApp();
			clickChromeApp();

			driver.context((String) contextNames.toArray()[1]);
			emailMasterBrowserpage.clickOnConfirmButton();
			driver.context("NATIVE_APP");

			//Open the Notification bar :
			openNotification();

			new NotificationPage(driver, testInfo).
			validateNotificationTitle_WithTemplateHighPriority().
			validateNotificationBody(deviceName).
			clickOnExpand(deviceName).
			validateBigImage(deviceName).
			validateBigText().
			validatelargeIcon(deviceName).
			clickOnBigImage();

			new  MyEarningsPage(driver, testInfo);


		}catch (Exception e) {

			closeNotification();
			reportStep("Test FAILed , unexpectedly :(", "FAIL");

		}

	}

	@Parameters({"deviceName","user"})
	@Test
	public void notificationTest_AppClosed_WithTemplate(String deviceName,String user) {

		try {
			// TEST STEPS :
			new EKOnboardingPage(driver, testInfo).
			clickOnSignInbutton().
			enterUserName(user).
			enterPassword().
			clickSignInButtonForSuccess();

			//Open the Notification bar :
			openNotification();

			//Clear the Notification
			clearNotificationFromNotificationBar(deviceName);

			closeNotification();

			CashKaroUtility cu = new CashKaroUtility(udid, port, automationName, deviceName);
			driver = cu.launchChromeWebView(driver);
			AdminCoreFunction adminFunctions = new AdminCoreFunction(driver, testInfo);
			adminFunctions.naviagateAdminPage_ThenSelectEarnKaro();
			adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
			adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
			adminFunctions.clickSubmit();

			adminFunctions.clickOnHamburgerMenu();
			adminFunctions.clickOnEmailMaster();
			adminFunctions.clickOnEmailMasterBrowser();
			adminFunctions.selectEmailFromSearchByDropDown_EmailMaster();
			adminFunctions.enterUserEmailAsKeyForSearchEmailMaster(user);
			adminFunctions.clickOnSearchButtonEmailMaster();

			EmailMasterBrowserPage emailMasterBrowserpage = new EmailMasterBrowserPage(driver, testInfo);

			emailMasterBrowserpage.clickOnSendNotification();
			emailMasterBrowserpage.clickOnWithNotificationTemplate();
			emailMasterBrowserpage.selectHighPriorityWithNotificationTemplate();
			emailMasterBrowserpage.selectSiteTitleAsUserName();
			emailMasterBrowserpage.clickOnSendNotificatonButtonForWithTemplate();

			Set<String> contextNames = driver.getContextHandles();
			driver.context("NATIVE_APP");

			toggleApp(driver);
			clickEarnkaroApp(driver);
			new HomePage(driver, testInfo);
			toggleApp();
			closeEarnKaroApp(deviceName);
			clickChromeApp();

			driver.context((String) contextNames.toArray()[1]);
			emailMasterBrowserpage.clickOnConfirmButton();
			driver.context("NATIVE_APP");

			//Open the Notification bar :
			openNotification();

			new NotificationPage(driver, testInfo).
			validateNotificationTitle_WithTemplateHighPriority().
			validateNotificationBody(deviceName).
			clickOnExpand(deviceName).
			validateBigImage(deviceName).
			validateBigText().
			validatelargeIcon(deviceName).
			clickOnBigImage();

			new  MyEarningsPage(driver, testInfo);

		}catch (Exception e) {

			closeNotification();

			reportStep("Test FAILed , unexpectedly - May be Notification arrived late - Please look into the Step by Step report and Analyse the FAILured :(", "FAIL");

		}


	}

	//Negative flow :
	@Parameters({"deviceName","user"})
	@Test
	public void notificationTest_AppBackGround_WithTemplate_WithOutImageURL_WithoutRedirectionURL_WithoutLargeIcon(String deviceName,String user) {

		try {

			// TEST STEPS :
			new EKOnboardingPage(driver, testInfo).
			clickOnSignInbutton().
			enterUserName(user).
			enterPassword().
			clickSignInButtonForSuccess();

			//Open the Notification bar :
			openNotification();

			//Clear the Notification
			clearNotificationFromNotificationBar(deviceName);

			closeNotification();

			CashKaroUtility cu = new CashKaroUtility(udid, port, automationName, deviceName);
			driver = cu.launchChromeWebView(driver);
			AdminCoreFunction adminFunctions = new AdminCoreFunction(driver, testInfo);
			adminFunctions.naviagateAdminPage_ThenSelectEarnKaro();
			adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
			adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
			adminFunctions.clickSubmit();

			adminFunctions.clickOnHamburgerMenu();
			adminFunctions.clickOnEmailMaster();
			adminFunctions.clickOnEmailMasterBrowser();
			adminFunctions.selectEmailFromSearchByDropDown_EmailMaster();
			adminFunctions.enterUserEmailAsKeyForSearchEmailMaster(user);
			adminFunctions.clickOnSearchButtonEmailMaster();

			EmailMasterBrowserPage emailMasterBrowserpage = new EmailMasterBrowserPage(driver, testInfo);

			emailMasterBrowserpage.clickOnSendNotification();
			emailMasterBrowserpage.clickOnWithNotificationTemplate();
			emailMasterBrowserpage.selectHighPriorityWithNotificationTemplate_WithoutRedirctURL_WithoutLargeIcon_WithoutBigImage();
			emailMasterBrowserpage.selectSiteTitleAsUserName();
			emailMasterBrowserpage.clickOnSendNotificatonButtonForWithTemplate();

			Set<String> contextNames = driver.getContextHandles();
			driver.context("NATIVE_APP");

			toggleApp(driver);
			clickEarnkaroApp(driver);
			new HomePage(driver, testInfo);
			toggleApp();
			clickChromeApp();

			driver.context((String) contextNames.toArray()[1]);
			emailMasterBrowserpage.clickOnConfirmButton();
			driver.context("NATIVE_APP");

			//Open the Notification bar :
			openNotification();

			new NotificationPage(driver, testInfo).
			validateNotificationTitle_WithTemplateHighPriority_WithoutRedirectURL_WithoutBigImage_WithoutLargeIcon().
			validateBigText().
			clickOnNotificationTitle_WithoutRedirection_WithoutLargeIcon_WithoutBigImage();

			new HomePage(driver, testInfo);


		}catch (Exception e) {

			closeNotification();
			reportStep("Test FAILed , unexpectedly :(", "FAIL");

		}

	}

	@Parameters({"deviceName","user"})
	@Test
	public void notificationTest_AppClosed_WithTemplate_WithOutImageURL_WithoutRedirectionURL_WithoutLargeIcon(String deviceName,String user) {

		try {
			// TEST STEPS :
			new EKOnboardingPage(driver, testInfo).
			clickOnSignInbutton().
			enterUserName(user).
			enterPassword().
			clickSignInButtonForSuccess();

			//Open the Notification bar :
			openNotification();

			//Clear the Notification
			clearNotificationFromNotificationBar(deviceName);

			closeNotification();

			CashKaroUtility cu = new CashKaroUtility(udid, port, automationName, deviceName);
			driver = cu.launchChromeWebView(driver);
			AdminCoreFunction adminFunctions = new AdminCoreFunction(driver, testInfo);
			adminFunctions.naviagateAdminPage_ThenSelectEarnKaro();
			adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
			adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
			adminFunctions.clickSubmit();

			adminFunctions.clickOnHamburgerMenu();
			adminFunctions.clickOnEmailMaster();
			adminFunctions.clickOnEmailMasterBrowser();
			adminFunctions.selectEmailFromSearchByDropDown_EmailMaster();
			adminFunctions.enterUserEmailAsKeyForSearchEmailMaster(user);
			adminFunctions.clickOnSearchButtonEmailMaster();

			EmailMasterBrowserPage emailMasterBrowserpage = new EmailMasterBrowserPage(driver, testInfo);

			emailMasterBrowserpage.clickOnSendNotification();
			emailMasterBrowserpage.clickOnWithNotificationTemplate();
			emailMasterBrowserpage.selectHighPriorityWithNotificationTemplate_WithoutRedirctURL_WithoutLargeIcon_WithoutBigImage();
			emailMasterBrowserpage.selectSiteTitleAsUserName();
			emailMasterBrowserpage.clickOnSendNotificatonButtonForWithTemplate();

			Set<String> contextNames = driver.getContextHandles();
			driver.context("NATIVE_APP");

			toggleApp(driver);
			clickEarnkaroApp(driver);
			new HomePage(driver, testInfo);
			toggleApp();
			closeEarnKaroApp(deviceName);
			clickChromeApp();

			driver.context((String) contextNames.toArray()[1]);
			emailMasterBrowserpage.clickOnConfirmButton();
			driver.context("NATIVE_APP");

			//Open the Notification bar :
			openNotification();

			new NotificationPage(driver, testInfo).
			validateNotificationTitle_WithTemplateHighPriority_WithoutRedirectURL_WithoutBigImage_WithoutLargeIcon().

			validateBigText().
			clickOnNotificationTitle_WithoutRedirection_WithoutLargeIcon_WithoutBigImage();

			new HomePage(driver, testInfo);

		}catch (Exception e) {

			closeNotification();

			reportStep("Test FAILed , unexpectedly - May be Notification arrived late - Please look into the Step by Step report and Analyse the FAILured :(", "FAIL");

		}


	}

	//Negative flow :
	@Parameters({"deviceName","user"})
	@Test
	public void notificationTest_AppBackGround_WithTemplate_WithoutRedirectionURL(String deviceName,String user) {

		try {

			// TEST STEPS :
			new EKOnboardingPage(driver, testInfo).
			clickOnSignInbutton().
			enterUserName(user).
			enterPassword().
			clickSignInButtonForSuccess();

			//Open the Notification bar :
			openNotification();

			//Clear the Notification
			clearNotificationFromNotificationBar(deviceName);

			closeNotification();

			CashKaroUtility cu = new CashKaroUtility(udid, port, automationName, deviceName);
			driver = cu.launchChromeWebView(driver);
			AdminCoreFunction adminFunctions = new AdminCoreFunction(driver, testInfo);
			adminFunctions.naviagateAdminPage_ThenSelectEarnKaro();
			adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
			adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
			adminFunctions.clickSubmit();

			adminFunctions.clickOnHamburgerMenu();
			adminFunctions.clickOnEmailMaster();
			adminFunctions.clickOnEmailMasterBrowser();
			adminFunctions.selectEmailFromSearchByDropDown_EmailMaster();
			adminFunctions.enterUserEmailAsKeyForSearchEmailMaster(user);
			adminFunctions.clickOnSearchButtonEmailMaster();

			EmailMasterBrowserPage emailMasterBrowserpage = new EmailMasterBrowserPage(driver, testInfo);

			emailMasterBrowserpage.clickOnSendNotification();
			emailMasterBrowserpage.clickOnWithNotificationTemplate();
			emailMasterBrowserpage.selectHighPriorityWithNotificationTemplate_WithoutRedirctURL();
			emailMasterBrowserpage.selectSiteTitleAsUserName();
			emailMasterBrowserpage.clickOnSendNotificatonButtonForWithTemplate();

			Set<String> contextNames = driver.getContextHandles();
			driver.context("NATIVE_APP");

			toggleApp(driver);
			clickEarnkaroApp(driver);
			new HomePage(driver, testInfo);
			toggleApp();
			clickChromeApp();

			driver.context((String) contextNames.toArray()[1]);
			emailMasterBrowserpage.clickOnConfirmButton();
			driver.context("NATIVE_APP");

			//Open the Notification bar :
			openNotification();

			new NotificationPage(driver, testInfo).
			validateNotificationTitle_WithTemplateHighPriority_WithoutRedirectURL().
			validateNotificationBody(deviceName).
			clickOnExpand(deviceName).
			validateBigImage(deviceName).
			validateBigText().
			validatelargeIcon(deviceName).
			clickOnBigImage();

			new HomePage(driver, testInfo);


		}catch (Exception e) {

			closeNotification();
			reportStep("Test FAILed , unexpectedly :(", "FAIL");

		}

	}

	@Parameters({"deviceName","user"})
	@Test
	public void notificationTest_AppClosed_WithTemplate_WithoutRedirectionURL(String deviceName,String user) {

		try {
			// TEST STEPS :
			new EKOnboardingPage(driver, testInfo).
			clickOnSignInbutton().
			enterUserName(user).
			enterPassword().
			clickSignInButtonForSuccess();

			//Open the Notification bar :
			openNotification();

			//Clear the Notification
			clearNotificationFromNotificationBar(deviceName);

			closeNotification();

			CashKaroUtility cu = new CashKaroUtility(udid, port, automationName, deviceName);
			driver = cu.launchChromeWebView(driver);
			AdminCoreFunction adminFunctions = new AdminCoreFunction(driver, testInfo);
			adminFunctions.naviagateAdminPage_ThenSelectEarnKaro();
			adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
			adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
			adminFunctions.clickSubmit();

			adminFunctions.clickOnHamburgerMenu();
			adminFunctions.clickOnEmailMaster();
			adminFunctions.clickOnEmailMasterBrowser();
			adminFunctions.selectEmailFromSearchByDropDown_EmailMaster();
			adminFunctions.enterUserEmailAsKeyForSearchEmailMaster(user);
			adminFunctions.clickOnSearchButtonEmailMaster();

			EmailMasterBrowserPage emailMasterBrowserpage = new EmailMasterBrowserPage(driver, testInfo);

			emailMasterBrowserpage.clickOnSendNotification();
			emailMasterBrowserpage.clickOnWithNotificationTemplate();
			emailMasterBrowserpage.selectHighPriorityWithNotificationTemplate_WithoutRedirctURL();
			emailMasterBrowserpage.selectSiteTitleAsUserName();
			emailMasterBrowserpage.clickOnSendNotificatonButtonForWithTemplate();

			Set<String> contextNames = driver.getContextHandles();
			driver.context("NATIVE_APP");

			toggleApp(driver);
			clickEarnkaroApp(driver);
			new HomePage(driver, testInfo);
			toggleApp();
			closeEarnKaroApp(deviceName);
			clickChromeApp();

			driver.context((String) contextNames.toArray()[1]);
			emailMasterBrowserpage.clickOnConfirmButton();
			driver.context("NATIVE_APP");

			//Open the Notification bar :
			openNotification();

			new NotificationPage(driver, testInfo).
			validateNotificationTitle_WithTemplateHighPriority_WithoutRedirectURL().
			validateNotificationBody(deviceName).
			clickOnExpand(deviceName).
			validateBigImage(deviceName).
			validateBigText().
			validatelargeIcon(deviceName).
			clickOnBigImage();

			new HomePage(driver, testInfo);

		}catch (Exception e) {

			closeNotification();

			reportStep("Test FAILed , unexpectedly - May be Notification arrived late - Please look into the Step by Step report and Analyse the FAILured :(", "FAIL");

		}


	}

	//After logout Negative scenarios : Negative flow

	@Parameters({"deviceName","user"})
	//@Test
	public void notificationTest_AppBackGround_LogoutThenClickNotificaiton_WithTemplate(String deviceName,String user) {

		try {
			// TEST STEPS :
			new EKOnboardingPage(driver, testInfo).
			clickOnSignInbutton().
			enterUserName(user).
			enterPassword().
			clickSignInButtonForSuccess();

			//Open the Notification bar :
			openNotification();

			//Clear the Notification
			clearNotificationFromNotificationBar(deviceName);

			closeNotification();

			CashKaroUtility cu = new CashKaroUtility(udid, port, automationName, deviceName);
			driver = cu.launchChromeWebView(driver);
			AdminCoreFunction adminFunctions = new AdminCoreFunction(driver, testInfo);
			adminFunctions.naviagateAdminPage_ThenSelectEarnKaro();
			adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
			adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
			adminFunctions.clickSubmit();

			adminFunctions.clickOnHamburgerMenu();
			adminFunctions.clickOnEmailMaster();
			adminFunctions.clickOnEmailMasterBrowser();
			adminFunctions.selectEmailFromSearchByDropDown_EmailMaster();
			adminFunctions.enterUserEmailAsKeyForSearchEmailMaster(user);
			adminFunctions.clickOnSearchButtonEmailMaster();

			EmailMasterBrowserPage emailMasterBrowserpage = new EmailMasterBrowserPage(driver, testInfo);

			emailMasterBrowserpage.clickOnSendNotification();
			emailMasterBrowserpage.clickOnWithNotificationTemplate();
			emailMasterBrowserpage.selectHighPriorityWithNotificationTemplate();
			emailMasterBrowserpage.selectSiteTitleAsUserName();
			emailMasterBrowserpage.clickOnSendNotificatonButtonForWithTemplate();

			Set<String> contextNames = driver.getContextHandles();
			driver.context("NATIVE_APP");

			toggleApp(driver);

			clickEarnkaroApp(driver);
			new HomePage(driver, testInfo);

			toggleApp();
			clickChromeApp();

			driver.context((String) contextNames.toArray()[1]);
			emailMasterBrowserpage.clickOnConfirmButton();
			driver.context("NATIVE_APP");

			toggleApp(driver);
			clickEarnkaroApp(driver);
			new HomePage(driver, testInfo).
			clickOnProfileIconForSignedUser().
			clickOnLogout();

			//Open the Notification bar :
			openNotification();

			new NotificationPage(driver, testInfo).
			validateNotificationTitle_WithTemplateHighPriority().
			validateNotificationBody(deviceName).
			clickOnExpand(deviceName).
			validateBigImage(deviceName).
			validateBigText().
			validatelargeIcon(deviceName).
			clickOnBigImage();

			new SignInPage(driver, testInfo);


		}catch (Exception e) {

			closeNotification();
			reportStep("Test FAILed , unexpectedly :(", "FAIL");

		}

	}

	@Parameters({"deviceName","user"})
	//@Test
	public void notificationTest_AppClosed_LogoutThenClickNotificaiton_WithTemplate(String deviceName,String user) {

		try {
			// TEST STEPS :
			new EKOnboardingPage(driver, testInfo).
			clickOnSignInbutton().
			enterUserName(user).
			enterPassword().
			clickSignInButtonForSuccess();

			//Open the Notification bar :
			openNotification();

			//Clear the Notification
			clearNotificationFromNotificationBar(deviceName);

			closeNotification();

			CashKaroUtility cu = new CashKaroUtility(udid, port, automationName, deviceName);
			driver = cu.launchChromeWebView(driver);
			AdminCoreFunction adminFunctions = new AdminCoreFunction(driver, testInfo);
			adminFunctions.naviagateAdminPage_ThenSelectEarnKaro();
			adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
			adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
			adminFunctions.clickSubmit();

			adminFunctions.clickOnHamburgerMenu();
			adminFunctions.clickOnEmailMaster();
			adminFunctions.clickOnEmailMasterBrowser();
			adminFunctions.selectEmailFromSearchByDropDown_EmailMaster();
			adminFunctions.enterUserEmailAsKeyForSearchEmailMaster(user);
			adminFunctions.clickOnSearchButtonEmailMaster();

			EmailMasterBrowserPage emailMasterBrowserpage = new EmailMasterBrowserPage(driver, testInfo);

			emailMasterBrowserpage.clickOnSendNotification();
			emailMasterBrowserpage.clickOnWithNotificationTemplate();
			emailMasterBrowserpage.selectHighPriorityWithNotificationTemplate();
			emailMasterBrowserpage.selectSiteTitleAsUserName();
			emailMasterBrowserpage.clickOnSendNotificatonButtonForWithTemplate();

			Set<String> contextNames = driver.getContextHandles();
			driver.context("NATIVE_APP");

			toggleApp(driver);
			clickEarnkaroApp(driver);
			new HomePage(driver, testInfo);

			toggleApp();
			clickChromeApp();

			driver.context((String) contextNames.toArray()[1]);
			emailMasterBrowserpage.clickOnConfirmButton();
			driver.context("NATIVE_APP");

			toggleApp(driver);
			clickEarnkaroApp(driver);

			new HomePage(driver, testInfo).
			clickOnProfileIconForSignedUser().
			clickOnLogout();

			toggleApp();
			closeEarnKaroApp(deviceName);
			clickChromeApp();

			//Open the Notification bar :
			openNotification();

			new NotificationPage(driver, testInfo).
			validateNotificationTitle_WithTemplateHighPriority().
			validateNotificationBody(deviceName).
			clickOnExpand(deviceName).
			validateBigImage(deviceName).
			validateBigText().
			validatelargeIcon(deviceName).
			clickOnBigImage();

			new SignInPage(driver, testInfo);

		} catch (Exception e) {

			closeNotification();
			reportStep("Test FAILed , unexpectedly - May be Notification arrived late - Please look into the Step by Step report and Analyse the FAILured :(", "FAIL");

		}


	}

	@Parameters({"deviceName","user"})
	//@Test
	public void notificationTest_AppBackGround_WithOutImageURL_WithoutRedirectionURL_WithoutLargeIcon_LogoutThenClickNotificaiton_WithTemplate(String deviceName,String user) {

		try {
			// TEST STEPS :
			new EKOnboardingPage(driver, testInfo).
			clickOnSignInbutton().
			enterUserName(user).
			enterPassword().
			clickSignInButtonForSuccess();

			//Open the Notification bar :
			openNotification();

			//Clear the Notification
			clearNotificationFromNotificationBar(deviceName);

			closeNotification();

			CashKaroUtility cu = new CashKaroUtility(udid, port, automationName, deviceName);
			driver = cu.launchChromeWebView(driver);
			AdminCoreFunction adminFunctions = new AdminCoreFunction(driver, testInfo);
			adminFunctions.naviagateAdminPage_ThenSelectEarnKaro();
			adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
			adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
			adminFunctions.clickSubmit();

			adminFunctions.clickOnHamburgerMenu();
			adminFunctions.clickOnEmailMaster();
			adminFunctions.clickOnEmailMasterBrowser();
			adminFunctions.selectEmailFromSearchByDropDown_EmailMaster();
			adminFunctions.enterUserEmailAsKeyForSearchEmailMaster(user);
			adminFunctions.clickOnSearchButtonEmailMaster();

			EmailMasterBrowserPage emailMasterBrowserpage = new EmailMasterBrowserPage(driver, testInfo);

			emailMasterBrowserpage.clickOnSendNotification();
			emailMasterBrowserpage.clickOnWithNotificationTemplate();
			emailMasterBrowserpage.selectHighPriorityWithNotificationTemplate_WithoutRedirctURL_WithoutLargeIcon_WithoutBigImage();
			emailMasterBrowserpage.selectSiteTitleAsUserName();
			emailMasterBrowserpage.clickOnSendNotificatonButtonForWithTemplate();

			Set<String> contextNames = driver.getContextHandles();
			driver.context("NATIVE_APP");

			toggleApp(driver);
			clickEarnkaroApp(driver);
			new HomePage(driver, testInfo);

			toggleApp();
			clickChromeApp();

			driver.context((String) contextNames.toArray()[1]);
			emailMasterBrowserpage.clickOnConfirmButton();
			driver.context("NATIVE_APP");

			toggleApp(driver);
			clickEarnkaroApp(driver);
			new HomePage(driver, testInfo).
			clickOnProfileIconForSignedUser().
			clickOnLogout();

			//Open the Notification bar :
			openNotification();

			new NotificationPage(driver, testInfo).
			validateNotificationTitle_WithTemplateHighPriority_WithoutRedirectURL_WithoutBigImage_WithoutLargeIcon().

			validateBigText().
			clickOnNotificationTitle_WithoutRedirection_WithoutLargeIcon_WithoutBigImage();

			new EKOnboardingPage(driver, testInfo);


		}catch (Exception e) {

			closeNotification();
			reportStep("Test FAILed , unexpectedly :(", "FAIL");

		}

	}

	@Parameters({"deviceName","user"})
	//@Test
	public void notificationTest_AppClosed_WithOutImageURL_WithoutRedirectionURL_WithoutLargeIcon_LogoutThenClickNotificaiton_WithTemplate(String deviceName,String user) {

		try {
			// TEST STEPS :
			new EKOnboardingPage(driver, testInfo).
			clickOnSignInbutton().
			enterUserName(user).
			enterPassword().
			clickSignInButtonForSuccess();

			//Open the Notification bar :
			openNotification();

			//Clear the Notification
			clearNotificationFromNotificationBar(deviceName);

			closeNotification();

			CashKaroUtility cu = new CashKaroUtility(udid, port, automationName, deviceName);
			driver = cu.launchChromeWebView(driver);
			AdminCoreFunction adminFunctions = new AdminCoreFunction(driver, testInfo);
			adminFunctions.naviagateAdminPage_ThenSelectEarnKaro();
			adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
			adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
			adminFunctions.clickSubmit();

			adminFunctions.clickOnHamburgerMenu();
			adminFunctions.clickOnEmailMaster();
			adminFunctions.clickOnEmailMasterBrowser();
			adminFunctions.selectEmailFromSearchByDropDown_EmailMaster();
			adminFunctions.enterUserEmailAsKeyForSearchEmailMaster(user);
			adminFunctions.clickOnSearchButtonEmailMaster();

			EmailMasterBrowserPage emailMasterBrowserpage = new EmailMasterBrowserPage(driver, testInfo);

			emailMasterBrowserpage.clickOnSendNotification();
			emailMasterBrowserpage.clickOnWithNotificationTemplate();
			emailMasterBrowserpage.selectHighPriorityWithNotificationTemplate_WithoutRedirctURL_WithoutLargeIcon_WithoutBigImage();
			emailMasterBrowserpage.selectSiteTitleAsUserName();
			emailMasterBrowserpage.clickOnSendNotificatonButtonForWithTemplate();

			Set<String> contextNames = driver.getContextHandles();
			driver.context("NATIVE_APP");

			toggleApp(driver);
			clickEarnkaroApp(driver);
			new HomePage(driver, testInfo);

			toggleApp();
			clickChromeApp();

			driver.context((String) contextNames.toArray()[1]);
			emailMasterBrowserpage.clickOnConfirmButton();
			driver.context("NATIVE_APP");

			toggleApp(driver);
			clickEarnkaroApp(driver);
			new HomePage(driver, testInfo).
			clickOnProfileIconForSignedUser().
			clickOnLogout();

			toggleApp();
			closeEarnKaroApp(deviceName);
			clickChromeApp();

			//Open the Notification bar :
			openNotification();

			new NotificationPage(driver, testInfo).
			validateNotificationTitle_WithTemplateHighPriority_WithoutRedirectURL_WithoutBigImage_WithoutLargeIcon().
			validateBigText().
			clickOnNotificationTitle_WithoutRedirection_WithoutLargeIcon_WithoutBigImage();

			new EKOnboardingPage(driver, testInfo);

		} catch (Exception e) {

			closeNotification();
			reportStep("Test FAILed , unexpectedly - May be Notification arrived late - Please look into the Step by Step report and Analyse the FAILured :(", "FAIL");

		}


	}

	@Parameters({"deviceName","user"})
	//@Test
	public void notificationTest_AppBackGround_WithoutRedirectionURL_LogoutThenClickNotificaiton_WithTemplate(String deviceName,String user) {

		try {
			// TEST STEPS :
			new EKOnboardingPage(driver, testInfo).
			clickOnSignInbutton().
			enterUserName(user).
			enterPassword().
			clickSignInButtonForSuccess();

			//Open the Notification bar :
			openNotification();

			//Clear the Notification
			clearNotificationFromNotificationBar(deviceName);

			closeNotification();

			CashKaroUtility cu = new CashKaroUtility(udid, port, automationName, deviceName);
			driver = cu.launchChromeWebView(driver);
			AdminCoreFunction adminFunctions = new AdminCoreFunction(driver, testInfo);
			adminFunctions.naviagateAdminPage_ThenSelectEarnKaro();
			adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
			adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
			adminFunctions.clickSubmit();

			adminFunctions.clickOnHamburgerMenu();
			adminFunctions.clickOnEmailMaster();
			adminFunctions.clickOnEmailMasterBrowser();
			adminFunctions.selectEmailFromSearchByDropDown_EmailMaster();
			adminFunctions.enterUserEmailAsKeyForSearchEmailMaster(user);
			adminFunctions.clickOnSearchButtonEmailMaster();

			EmailMasterBrowserPage emailMasterBrowserpage = new EmailMasterBrowserPage(driver, testInfo);

			emailMasterBrowserpage.clickOnSendNotification();
			emailMasterBrowserpage.clickOnWithNotificationTemplate();
			emailMasterBrowserpage.selectHighPriorityWithNotificationTemplate_WithoutRedirctURL();
			emailMasterBrowserpage.selectSiteTitleAsUserName();
			emailMasterBrowserpage.clickOnSendNotificatonButtonForWithTemplate();

			Set<String> contextNames = driver.getContextHandles();
			driver.context("NATIVE_APP");

			toggleApp(driver);
			clickEarnkaroApp(driver);
			new HomePage(driver, testInfo);

			toggleApp();
			clickChromeApp();

			driver.context((String) contextNames.toArray()[1]);
			emailMasterBrowserpage.clickOnConfirmButton();
			driver.context("NATIVE_APP");

			toggleApp(driver);
			clickEarnkaroApp(driver);
			new HomePage(driver, testInfo).
			clickOnProfileIconForSignedUser().
			clickOnLogout();

			//Open the Notification bar :
			openNotification();

			new NotificationPage(driver, testInfo).
			validateNotificationTitle_WithTemplateHighPriority_WithoutRedirectURL().
			validateNotificationBody(deviceName).
			clickOnExpand(deviceName).
			validateBigImage(deviceName).
			validateBigText().
			validatelargeIcon(deviceName).
			clickOnBigImage();

			new EKOnboardingPage(driver, testInfo);


		}catch (Exception e) {

			closeNotification();
			reportStep("Test FAILed , unexpectedly :(", "FAIL");

		}

	}

	@Parameters({"deviceName","user"})
	//@Test
	public void notificationTest_AppClosed_WithoutRedirectionURL_LogoutThenClickNotificaiton_WithTemplate(String deviceName,String user) {

		try {
			// TEST STEPS :
			new EKOnboardingPage(driver, testInfo).
			clickOnSignInbutton().
			enterUserName(user).
			enterPassword().
			clickSignInButtonForSuccess();

			//Open the Notification bar :
			openNotification();

			//Clear the Notification
			clearNotificationFromNotificationBar(deviceName);

			closeNotification();

			CashKaroUtility cu = new CashKaroUtility(udid, port, automationName, deviceName);
			driver = cu.launchChromeWebView(driver);
			AdminCoreFunction adminFunctions = new AdminCoreFunction(driver, testInfo);
			adminFunctions.naviagateAdminPage_ThenSelectEarnKaro();
			adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
			adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
			adminFunctions.clickSubmit();

			adminFunctions.clickOnHamburgerMenu();
			adminFunctions.clickOnEmailMaster();
			adminFunctions.clickOnEmailMasterBrowser();
			adminFunctions.selectEmailFromSearchByDropDown_EmailMaster();
			adminFunctions.enterUserEmailAsKeyForSearchEmailMaster(user);
			adminFunctions.clickOnSearchButtonEmailMaster();

			EmailMasterBrowserPage emailMasterBrowserpage = new EmailMasterBrowserPage(driver, testInfo);

			emailMasterBrowserpage.clickOnSendNotification();
			emailMasterBrowserpage.clickOnWithNotificationTemplate();
			emailMasterBrowserpage.selectHighPriorityWithNotificationTemplate_WithoutRedirctURL();
			emailMasterBrowserpage.selectSiteTitleAsUserName();
			emailMasterBrowserpage.clickOnSendNotificatonButtonForWithTemplate();

			Set<String> contextNames = driver.getContextHandles();
			driver.context("NATIVE_APP");

			toggleApp(driver);
			clickEarnkaroApp(driver);
			new HomePage(driver, testInfo);

			toggleApp();
			clickChromeApp();

			driver.context((String) contextNames.toArray()[1]);
			emailMasterBrowserpage.clickOnConfirmButton();
			driver.context("NATIVE_APP");

			toggleApp(driver);
			clickEarnkaroApp(driver);
			new HomePage(driver, testInfo).
			clickOnProfileIconForSignedUser().
			clickOnLogout();

			toggleApp();
			closeEarnKaroApp(deviceName);
			clickChromeApp();

			//Open the Notification bar :
			openNotification();

			new NotificationPage(driver, testInfo).
			validateNotificationTitle_WithTemplateHighPriority_WithoutRedirectURL().
			validateNotificationBody(deviceName).
			clickOnExpand(deviceName).
			validateBigImage(deviceName).
			validateBigText().
			validatelargeIcon(deviceName).
			clickOnBigImage();

			new EKOnboardingPage(driver, testInfo);

		} catch (Exception e) {

			closeNotification();
			reportStep("Test FAILed , unexpectedly - May be Notification arrived late - Please look into the Step by Step report and Analyse the FAILured :(", "FAIL");

		}


	}


	/*******************************************************************/
	/************* Redirectional URL in different Pages **************/
	/*******************************************************************/

	@Parameters({"deviceName","user"})
	@Test
	public void clickingRedirectionURLFromHomePage(String deviceName,String user) {

		try {

			// TEST STEPS :
			new EKOnboardingPage(driver, testInfo).
			clickOnSignInbutton().
			enterUserName(user).
			enterPassword().
			clickSignInButtonForSuccess();

			//Open the Notification bar :
			openNotification();

			//Clear the Notification
			clearNotificationFromNotificationBar(deviceName);

			closeNotification();

			CashKaroUtility cu = new CashKaroUtility(udid, port, automationName, deviceName);
			driver = cu.launchChromeWebView(driver);
			AdminCoreFunction adminFunctions = new AdminCoreFunction(driver, testInfo);
			adminFunctions.naviagateAdminPage_ThenSelectEarnKaro();
			adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
			adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
			adminFunctions.clickSubmit();

			adminFunctions.clickOnHamburgerMenu();
			adminFunctions.clickOnEmailMaster();
			adminFunctions.clickOnEmailMasterBrowser();
			adminFunctions.selectEmailFromSearchByDropDown_EmailMaster();
			adminFunctions.enterUserEmailAsKeyForSearchEmailMaster(user);
			adminFunctions.clickOnSearchButtonEmailMaster();

			EmailMasterBrowserPage emailMasterBrowserpage = new EmailMasterBrowserPage(driver, testInfo);

			emailMasterBrowserpage.clickOnSendNotification();
			emailMasterBrowserpage.clickOnWithNotificationTemplate();
			emailMasterBrowserpage.selectHighPriorityWithNotificationTemplate_RedirectionalURLAtDifferentPage();
			emailMasterBrowserpage.selectSiteTitleAsUserName();
			emailMasterBrowserpage.clickOnSendNotificatonButtonForWithTemplate();

			Set<String> contextNames = driver.getContextHandles();
			driver.context("NATIVE_APP");

			toggleApp(driver);
			clickEarnkaroApp(driver);
			new HomePage(driver, testInfo);
			toggleApp();
			clickChromeApp();

			driver.context((String) contextNames.toArray()[1]);
			emailMasterBrowserpage.clickOnConfirmButton();
			driver.context("NATIVE_APP");

			//Now Earnkaro app should be in the Home page 
			toggleApp(driver);
			clickEarnkaroApp(driver);

			//Open the Notification bar :
			openNotification();

			new NotificationPage(driver, testInfo).
			validateNotificationTitle_withDifferentRedirectionTitle().
			validateNotificationBody(deviceName).
			clickOnExpand(deviceName).
			validateBigImage(deviceName).
			validateBigText().
			validatelargeIcon(deviceName).
			clickOnBigImage().
			validateNotificationTestStorePage();

		} catch (Exception e) {

			closeNotification();
			reportStep("Test FAILed , unexpectedly :(", "FAIL");

		}

	}

	@Parameters({"deviceName","user"})
	@Test
	public void clickingRedirectionURLFromMyEarningsPage(String deviceName,String user) {

		try {

			// TEST STEPS :
			new EKOnboardingPage(driver, testInfo).
			clickOnSignInbutton().
			enterUserName(user).
			enterPassword().
			clickSignInButtonForSuccess();

			//Open the Notification bar :
			openNotification();

			//Clear the Notification
			clearNotificationFromNotificationBar(deviceName);

			closeNotification();

			CashKaroUtility cu = new CashKaroUtility(udid, port, automationName, deviceName);
			driver = cu.launchChromeWebView(driver);
			AdminCoreFunction adminFunctions = new AdminCoreFunction(driver, testInfo);
			adminFunctions.naviagateAdminPage_ThenSelectEarnKaro();
			adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
			adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
			adminFunctions.clickSubmit();

			adminFunctions.clickOnHamburgerMenu();
			adminFunctions.clickOnEmailMaster();
			adminFunctions.clickOnEmailMasterBrowser();
			adminFunctions.selectEmailFromSearchByDropDown_EmailMaster();
			adminFunctions.enterUserEmailAsKeyForSearchEmailMaster(user);
			adminFunctions.clickOnSearchButtonEmailMaster();

			EmailMasterBrowserPage emailMasterBrowserpage = new EmailMasterBrowserPage(driver, testInfo);

			emailMasterBrowserpage.clickOnSendNotification();
			emailMasterBrowserpage.clickOnWithNotificationTemplate();
			emailMasterBrowserpage.selectHighPriorityWithNotificationTemplate_RedirectionalURLAtDifferentPage();
			emailMasterBrowserpage.selectSiteTitleAsUserName();
			emailMasterBrowserpage.clickOnSendNotificatonButtonForWithTemplate();

			Set<String> contextNames = driver.getContextHandles();
			driver.context("NATIVE_APP");

			toggleApp(driver);
			clickEarnkaroApp(driver);
			new HomePage(driver, testInfo);
			toggleApp();
			clickChromeApp();

			driver.context((String) contextNames.toArray()[1]);
			emailMasterBrowserpage.clickOnConfirmButton();
			driver.context("NATIVE_APP");

			//Now Earnkaro app should be in the Home page 
			toggleApp(driver);
			clickEarnkaroApp(driver);
			new HomePage(driver, testInfo).
			clickOnProfileIconForSignedUser().
			clickOnMyEarnings();

			//Open the Notification bar :
			openNotification();

			new NotificationPage(driver, testInfo).
			validateNotificationTitle_withDifferentRedirectionTitle().
			validateNotificationBody(deviceName).
			clickOnExpand(deviceName).
			validateBigImage(deviceName).
			validateBigText().
			validatelargeIcon(deviceName).
			clickOnBigImage().
			validateNotificationTestStorePage();

			new StoreDetailPage(driver, testInfo).
			clickOnProfileIconForSignedUser().
			clickOnLogout();


		}catch (Exception e) {

			closeNotification();
			reportStep("Test FAILed , unexpectedly :(", "FAIL");

		}

	}

	@Parameters({"deviceName","user"})
	@Test
	public void clickingRedirectionURLFromHistoryPage(String deviceName,String user) {

		try {

			// TEST STEPS :
			new EKOnboardingPage(driver, testInfo).
			clickOnSignInbutton().
			enterUserName(user).
			enterPassword().
			clickSignInButtonForSuccess();

			//Open the Notification bar :
			openNotification();

			//Clear the Notification
			clearNotificationFromNotificationBar(deviceName);

			closeNotification();

			CashKaroUtility cu = new CashKaroUtility(udid, port, automationName, deviceName);
			driver = cu.launchChromeWebView(driver);
			AdminCoreFunction adminFunctions = new AdminCoreFunction(driver, testInfo);
			adminFunctions.naviagateAdminPage_ThenSelectEarnKaro();
			adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
			adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
			adminFunctions.clickSubmit();

			adminFunctions.clickOnHamburgerMenu();
			adminFunctions.clickOnEmailMaster();
			adminFunctions.clickOnEmailMasterBrowser();
			adminFunctions.selectEmailFromSearchByDropDown_EmailMaster();
			adminFunctions.enterUserEmailAsKeyForSearchEmailMaster(user);
			adminFunctions.clickOnSearchButtonEmailMaster();

			EmailMasterBrowserPage emailMasterBrowserpage = new EmailMasterBrowserPage(driver, testInfo);

			emailMasterBrowserpage.clickOnSendNotification();
			emailMasterBrowserpage.clickOnWithNotificationTemplate();
			emailMasterBrowserpage.selectHighPriorityWithNotificationTemplate_RedirectionalURLAtDifferentPage();
			emailMasterBrowserpage.selectSiteTitleAsUserName();
			emailMasterBrowserpage.clickOnSendNotificatonButtonForWithTemplate();

			Set<String> contextNames = driver.getContextHandles();
			driver.context("NATIVE_APP");

			toggleApp(driver);
			clickEarnkaroApp(driver);
			new HomePage(driver, testInfo);
			toggleApp();
			clickChromeApp();

			driver.context((String) contextNames.toArray()[1]);
			emailMasterBrowserpage.clickOnConfirmButton();
			driver.context("NATIVE_APP");

			//Now Earnkaro app should be in the Home page 
			toggleApp(driver);
			clickEarnkaroApp(driver);
			new HomePage(driver, testInfo).
			clickOnHistoryIcon();

			//Open the Notification bar :
			openNotification();

			new NotificationPage(driver, testInfo).
			validateNotificationTitle_withDifferentRedirectionTitle().
			validateNotificationBody(deviceName).
			clickOnExpand(deviceName).
			validateBigImage(deviceName).
			validateBigText().
			validatelargeIcon(deviceName).
			clickOnBigImage().
			validateNotificationTestStorePage();


		}catch (Exception e) {

			closeNotification();
			reportStep("Test FAILed , unexpectedly :(", "FAIL");

		}

	}

	@Parameters({"deviceName","user"})
	@Test
	public void clickingRedirectionURLFromMakeLinksPage(String deviceName,String user) {

		try {

			// TEST STEPS :
			new EKOnboardingPage(driver, testInfo).
			clickOnSignInbutton().
			enterUserName(user).
			enterPassword().
			clickSignInButtonForSuccess();

			//Open the Notification bar :
			openNotification();

			//Clear the Notification
			clearNotificationFromNotificationBar(deviceName);

			closeNotification();

			CashKaroUtility cu = new CashKaroUtility(udid, port, automationName, deviceName);
			driver = cu.launchChromeWebView(driver);
			AdminCoreFunction adminFunctions = new AdminCoreFunction(driver, testInfo);
			adminFunctions.naviagateAdminPage_ThenSelectEarnKaro();
			adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
			adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
			adminFunctions.clickSubmit();

			adminFunctions.clickOnHamburgerMenu();
			adminFunctions.clickOnEmailMaster();
			adminFunctions.clickOnEmailMasterBrowser();
			adminFunctions.selectEmailFromSearchByDropDown_EmailMaster();
			adminFunctions.enterUserEmailAsKeyForSearchEmailMaster(user);
			adminFunctions.clickOnSearchButtonEmailMaster();

			EmailMasterBrowserPage emailMasterBrowserpage = new EmailMasterBrowserPage(driver, testInfo);

			emailMasterBrowserpage.clickOnSendNotification();
			emailMasterBrowserpage.clickOnWithNotificationTemplate();
			emailMasterBrowserpage.selectHighPriorityWithNotificationTemplate_RedirectionalURLAtDifferentPage();
			emailMasterBrowserpage.selectSiteTitleAsUserName();
			emailMasterBrowserpage.clickOnSendNotificatonButtonForWithTemplate();

			Set<String> contextNames = driver.getContextHandles();
			driver.context("NATIVE_APP");

			toggleApp(driver);
			clickEarnkaroApp(driver);
			new HomePage(driver, testInfo);
			toggleApp();
			clickChromeApp();

			driver.context((String) contextNames.toArray()[1]);
			emailMasterBrowserpage.clickOnConfirmButton();
			driver.context("NATIVE_APP");

			//Now Earnkaro app should be in the Home page 
			toggleApp(driver);
			clickEarnkaroApp(driver);
			new HomePage(driver, testInfo).
			clickOnMakeLinksIcon();

			//Open the Notification bar :
			openNotification();

			new NotificationPage(driver, testInfo).
			validateNotificationTitle_withDifferentRedirectionTitle().
			validateNotificationBody(deviceName).
			clickOnExpand(deviceName).
			validateBigImage(deviceName).
			validateBigText().
			validatelargeIcon(deviceName).
			clickOnBigImage().
			validateNotificationTestStorePage();


		}catch (Exception e) {

			closeNotification();
			reportStep("Test FAILed , unexpectedly :(", "FAIL");

		}

	}

	@Parameters({"deviceName","user"})
	@Test
	public void clickingRedirectionURLFromSignedInprofilePage(String deviceName,String user) {

		try {

			// TEST STEPS :
			new EKOnboardingPage(driver, testInfo).
			clickOnSignInbutton().
			enterUserName(user).
			enterPassword().
			clickSignInButtonForSuccess();

			//Open the Notification bar :
			openNotification();

			//Clear the Notification
			clearNotificationFromNotificationBar(deviceName);

			closeNotification();

			CashKaroUtility cu = new CashKaroUtility(udid, port, automationName, deviceName);
			driver = cu.launchChromeWebView(driver);
			AdminCoreFunction adminFunctions = new AdminCoreFunction(driver, testInfo);
			adminFunctions.naviagateAdminPage_ThenSelectEarnKaro();
			adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
			adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
			adminFunctions.clickSubmit();

			adminFunctions.clickOnHamburgerMenu();
			adminFunctions.clickOnEmailMaster();
			adminFunctions.clickOnEmailMasterBrowser();
			adminFunctions.selectEmailFromSearchByDropDown_EmailMaster();
			adminFunctions.enterUserEmailAsKeyForSearchEmailMaster(user);
			adminFunctions.clickOnSearchButtonEmailMaster();

			EmailMasterBrowserPage emailMasterBrowserpage = new EmailMasterBrowserPage(driver, testInfo);

			emailMasterBrowserpage.clickOnSendNotification();
			emailMasterBrowserpage.clickOnWithNotificationTemplate();
			emailMasterBrowserpage.selectHighPriorityWithNotificationTemplate_RedirectionalURLAtDifferentPage();
			emailMasterBrowserpage.selectSiteTitleAsUserName();
			emailMasterBrowserpage.clickOnSendNotificatonButtonForWithTemplate();

			Set<String> contextNames = driver.getContextHandles();
			driver.context("NATIVE_APP");

			toggleApp(driver);
			clickEarnkaroApp(driver);
			new HomePage(driver, testInfo);
			toggleApp();
			clickChromeApp();

			driver.context((String) contextNames.toArray()[1]);
			emailMasterBrowserpage.clickOnConfirmButton();
			driver.context("NATIVE_APP");

			//Now Earnkaro app should be in the Home page 
			toggleApp(driver);
			clickEarnkaroApp(driver);
			new HomePage(driver, testInfo).
			clickOnProfileIconForSignedUser();

			//Open the Notification bar :
			openNotification();

			new NotificationPage(driver, testInfo).
			validateNotificationTitle_withDifferentRedirectionTitle().
			validateNotificationBody(deviceName).
			clickOnExpand(deviceName).
			validateBigImage(deviceName).
			validateBigText().
			validatelargeIcon(deviceName).
			clickOnBigImage().
			validateNotificationTestStorePage();


		}catch (Exception e) {

			closeNotification();
			reportStep("Test FAILed , unexpectedly :(", "FAIL");

		}

	}

	@Parameters({"deviceName","user"})
	@Test
	public void clickingRedirectionURLFromStorePage(String deviceName,String user) {

		try {

			// TEST STEPS :
			new EKOnboardingPage(driver, testInfo).
			clickOnSignInbutton().
			enterUserName(user).
			enterPassword().
			clickSignInButtonForSuccess();

			//Open the Notification bar :
			openNotification();

			//Clear the Notification
			clearNotificationFromNotificationBar(deviceName);

			closeNotification();

			CashKaroUtility cu = new CashKaroUtility(udid, port, automationName, deviceName);
			driver = cu.launchChromeWebView(driver);
			AdminCoreFunction adminFunctions = new AdminCoreFunction(driver, testInfo);
			adminFunctions.naviagateAdminPage_ThenSelectEarnKaro();
			adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
			adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
			adminFunctions.clickSubmit();

			adminFunctions.clickOnHamburgerMenu();
			adminFunctions.clickOnEmailMaster();
			adminFunctions.clickOnEmailMasterBrowser();
			adminFunctions.selectEmailFromSearchByDropDown_EmailMaster();
			adminFunctions.enterUserEmailAsKeyForSearchEmailMaster(user);
			adminFunctions.clickOnSearchButtonEmailMaster();

			EmailMasterBrowserPage emailMasterBrowserpage = new EmailMasterBrowserPage(driver, testInfo);

			emailMasterBrowserpage.clickOnSendNotification();
			emailMasterBrowserpage.clickOnWithNotificationTemplate();
			emailMasterBrowserpage.selectHighPriorityWithNotificationTemplate_RedirectionalURLAtDifferentPage();
			emailMasterBrowserpage.selectSiteTitleAsUserName();
			emailMasterBrowserpage.clickOnSendNotificatonButtonForWithTemplate();

			Set<String> contextNames = driver.getContextHandles();
			driver.context("NATIVE_APP");

			toggleApp(driver);
			clickEarnkaroApp(driver);
			new HomePage(driver, testInfo);
			toggleApp();
			clickChromeApp();

			driver.context((String) contextNames.toArray()[1]);
			emailMasterBrowserpage.clickOnConfirmButton();
			driver.context("NATIVE_APP");

			//Now Earnkaro app should be in the Home page 
			toggleApp(driver);
			clickEarnkaroApp(driver);
			new HomePage(driver, testInfo).
			clickOnProfileIconForSignedUser();

			//Open the Notification bar :
			openNotification();

			NotificationPage objNotification = 
					new NotificationPage(driver, testInfo).
					validateNotificationTitle_withDifferentRedirectionTitle().
					validateNotificationBody(deviceName).
					clickOnExpand(deviceName).
					validateBigImage(deviceName).
					validateBigText().
					validatelargeIcon(deviceName).
					clickOnBigImage().
					validateNotificationTestStorePage();

			toggleApp();
			clickChromeApp();

			driver.context((String) contextNames.toArray()[1]);
			emailMasterBrowserpage.clickOnSendNotificatonButtonForWithTemplate();
			emailMasterBrowserpage.clickOnConfirmButton();

			driver.context("NATIVE_APP");

			toggleApp(driver);
			clickEarnkaroApp(driver);

			objNotification.validateNotificationTestStorePage();

			//Open the Notification bar :
			openNotification();

			objNotification.
			validateNotificationTitle_withDifferentRedirectionTitle().
			validateNotificationBody(deviceName).
			clickOnExpand(deviceName).
			validateBigImage(deviceName).
			validateBigText().
			validatelargeIcon(deviceName).
			clickOnBigImage().
			validateNotificationTestStorePage();



		}catch (Exception e) {

			closeNotification();
			reportStep("Test FAILed , unexpectedly :(", "FAIL");

		}

	}

	/*******************************************************************/
	/************* Transactional Notification ********************/
	/*******************************************************************/

	@Parameters({"deviceName","user"})
	@Test
	public void pending_Cancell_Notification_AppBackGround(String deviceName,String user) {

		try {

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

			Set<String> contextNames = driver.getContextHandles();
			driver.context((String) contextNames.toArray()[1]);
			driver.context("NATIVE_APP");


			toggleApp();
			clickEarnkaroApp(driver);
			new HomePage(driver, testInfo);

			toggleApp();
			clickChromeApp();

			driver.context((String) contextNames.toArray()[1]);
			ProfitPage.clickSaveCashbackUsingAppium();
			driver.context("NATIVE_APP");

			openNotification();

			new NotificationPage(driver, testInfo).
			validatePendingNotification().
			validatePendingNotification_BigText().
			clickOnExpand(deviceName).
			validateBigImage(deviceName).
			validatelargeIcon(deviceName).
			clickPendingNotification();
			new MyEarningsPage(driver, testInfo);


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

			contextNames = driver.getContextHandles();
			driver.context("NATIVE_APP");

			toggleApp();
			clickEarnkaroApp(driver);
			new MyEarningsPage(driver, testInfo);

			toggleApp();
			clickChromeApp();
			scrollFromDownToUpinApp();

			driver.context((String) contextNames.toArray()[1]);
			ProfitPage.clickSaveCashbackUsingAppium();
			driver.context("NATIVE_APP");

			openNotification();

			new NotificationPage(driver, testInfo).
			validateCancelledNotification().
			validateCancelledNotification_BigText().
			clickOnExpand(deviceName).
			validateBigImage(deviceName).
			validatelargeIcon(deviceName).
			clickCancelledNotificaiton();

			new MyEarningsPage(driver, testInfo);

		}catch (Exception e) {

			closeNotification();
			reportStep("Test FAILed , unexpectedly :(", "FAIL");
		}

	}

	@Parameters({"deviceName","user"})
	@Test
	public void confirmed_PaidNotification_AppBackGround(String deviceName,String user) {

		try {

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
			enterTextIntoTheSearchBar(cbStoreName)
			.clickStoreCardShortDescription(cbShortDescription).
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


			//Change the Status from Pending to Confirm :
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

			//Validate Confirm Notification here : 
			Set<String> contextNames = driver.getContextHandles();
			driver.context("NATIVE_APP");

			toggleApp();
			clickEarnkaroApp(driver);
			new HomePage(driver, testInfo);

			toggleApp();
			clickChromeApp();
			scrollFromDownToUpinApp();

			driver.context((String) contextNames.toArray()[1]);
			ProfitPage.clickSaveCashbackUsingAppium();
			driver.context("NATIVE_APP");

			openNotification();

			new NotificationPage(driver, testInfo).
			validateConfirmedNotification().
			validateConfirmedNotification_BigText().
			clickOnExpand(deviceName).
			validateBigImage(deviceName).
			validatelargeIcon(deviceName).
			clickOnConfirmedCashbackNotification();

			PaymentOTPPage objPaymentOTP =
					new MyEarningsPage(driver,testInfo).
					clickOnRequestProfitPaymentButton().
					clickOnPaymentMethodDropDown().
					selectBankPaymentNEFTFromDropDown().
					enterBankNEFTPaymentDetails().
					clickOnGETPAID();

			otpValue = objCashKaroUtility.generatePaymentOTP(mobileNumber);

			try {
				objPaymentOTP.
				enterOTP(otpValue).
				clickOnVerifyOTPAndValidateThePaymentSuccessMessage();
			}catch (Exception e) {

				System.out.println("Handled the exception - start the cashout process");
				e.printStackTrace();
			}

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
			PendingCashoutsPage.
			validateSuccessMessage();

			//Validate Confirm Notification here : 
			contextNames = driver.getContextHandles();
			driver.context("NATIVE_APP");

			openNotification();

			new NotificationPage(driver, testInfo).
			validatePaidNotification().
			validatePaidNotification_BigText().
			clickOnPaidNotification();

			new MyEarningsPage(driver,testInfo);

		}catch (Exception e) {

			closeNotification();
			reportStep("Test FAILed , unexpectedly :(", "FAIL");

		}



	}

	@Parameters({"deviceName","user"})
	@Test
	public void referralNotifcation_AppBackGround(String deviceName,String user) {

		try {

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

			//Open the App in the back ground :
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
			String name = "QAengineer";

			String OtpValue = new JoinFreePage(driver, testInfo,0).
					validateReferralByUserAtJoinFree(userName).
					enterFullName(name).
					enterEmail(emailAddressReferralJoinee).
					enterPassword().
					enterMobileNumber(mobileNumberReferralJoinee).
					clickOnGetOTPButton(mobileNumberReferralJoinee);

			new OTPPage(driver, testInfo).
			enterOTP(OtpValue).
			clickOnVerifyOTP();

			new HomePage(driver, testInfo).
			clickOnSearchIcon().
			enterTextIntoTheSearchBar(cbStoreName).
			clickStoreCardShortDescription(cbShortDescription).
			validateStoreShortDescription(cbStoreName, cbShortDescription).
			clickOnAddToShareButton().
			clickOnShareIconWithCount(1);

			new StoreDetailPage(driver, testInfo).clickOnCopyLink();

			String url1 = getClipBoardText();

			CashKaroUtility cuOne = new CashKaroUtility(udid, port, automationName, deviceName);
			driver = cuOne.launchChrome();

			cuOne.closeAllTabsAndOneTabInChromeBrowser(driver);
			cuOne.clickAndEnterURLInSearchOrTypeWebAddress(driver, url1);

			new IntermediateRetailerPage(driver, testInfo).
			validateRetailerPage(cbLinkURL, cbLinkURL);

			CashKaroUtility reopenCKApp = new CashKaroUtility(udid, port, automationName, deviceName, systemPort);
			driver = reopenCKApp.launchEarnKaroApp(driver);

			new HomePage(driver, testInfo).
			clickOnProfileIconForSignedUser().
			clickOnLogout().
			clickOnSignInbutton_ForSecondTime().
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
			clickBackButton();


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

			//Validate Confirm Notification here : 
			Set<String> contextNames = driver.getContextHandles();
			driver.context("NATIVE_APP");

			toggleApp();
			clickEarnkaroApp(driver);
			new HomePage(driver, testInfo);
			toggleApp();
			clickChromeApp();
			scrollFromDownToUpinApp();

			driver.context((String) contextNames.toArray()[1]);
			ProfitPage.clickSaveCashbackUsingAppium();
			driver.context("NATIVE_APP");

			openNotification();

			new NotificationPage(driver, testInfo).
			validateReferralConfirmedCashbackNotification().
			validateNotificationBody(deviceName).
			clickOnExpand(deviceName).
			validateReferralConfirmed_BigText().
			clickOnReferralCashbackNotification();

			new MyEarningsPage(driver,testInfo);

		}catch (Exception e) {

			closeNotification();
			reportStep("Test FAILed , unexpectedly :(", "FAIL");

		}


	}

	@Parameters({"deviceName","user"})
	@Test
	public void pending_Cancell_Notification_AppClosed(String deviceName,String user) {

		try {

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

			Set<String> contextNames = driver.getContextHandles();
			driver.context("NATIVE_APP");


			toggleApp();
			clickEarnkaroApp(driver);
			new HomePage(driver, testInfo);
			toggleApp();
			closeEarnKaroApp(deviceName);

			clickChromeApp();

			driver.context((String) contextNames.toArray()[1]);
			ProfitPage.clickSaveCashbackUsingAppium();
			driver.context("NATIVE_APP");

			openNotification();

			new NotificationPage(driver, testInfo).
			validatePendingNotification().
			validatePendingNotification_BigText().
			clickOnExpand(deviceName).
			validateBigImage(deviceName).
			validatelargeIcon(deviceName).
			clickPendingNotification();

			new MyEarningsPage(driver, testInfo);


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

			contextNames = driver.getContextHandles();
			driver.context("NATIVE_APP");


			toggleApp();
			clickEarnkaroApp(driver);
			new MyEarningsPage(driver, testInfo);
			toggleApp();
			closeEarnKaroApp(deviceName);

			clickChromeApp();
			scrollFromDownToUpinApp();

			driver.context((String) contextNames.toArray()[1]);
			ProfitPage.clickSaveCashbackUsingAppium();
			driver.context("NATIVE_APP");

			openNotification();

			new NotificationPage(driver, testInfo).
			validateCancelledNotification().
			validateCancelledNotification_BigText().
			clickOnExpand(deviceName).
			validateBigImage(deviceName).
			validatelargeIcon(deviceName).
			clickCancelledNotificaiton();

			new MyEarningsPage(driver, testInfo);

		}catch (Exception e) {

			closeNotification();
			reportStep("Test FAILed , unexpectedly :(", "FAIL");
		}


	}

	@Parameters({"deviceName","user"})
	@Test
	public void confirmed_PaidNotification_AppClosed(String deviceName,String user) {

		try {

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


			//Change the Status from Pending to Confirm :
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

			//Validate Confirm Notification here : 
			Set<String> contextNames = driver.getContextHandles();
			driver.context("NATIVE_APP");

			toggleApp();
			closeEarnKaroApp(deviceName);

			clickChromeApp();
			scrollFromDownToUpinApp();

			driver.context((String) contextNames.toArray()[1]);
			ProfitPage.clickSaveCashbackUsingAppium();
			driver.context("NATIVE_APP");

			openNotification();

			new NotificationPage(driver, testInfo).
			validateConfirmedNotification().
			validateConfirmedNotification_BigText().
			clickOnExpand(deviceName).
			validateBigImage(deviceName).
			validatelargeIcon(deviceName).
			clickOnConfirmedCashbackNotification();

			PaymentOTPPage objPaymentOTP =
					new MyEarningsPage(driver,testInfo).
					clickOnRequestProfitPaymentButton().
					clickOnPaymentMethodDropDown().
					selectBankPaymentNEFTFromDropDown().
					enterBankNEFTPaymentDetails().
					clickOnGETPAID();

			otpValue = objCashKaroUtility.generatePaymentOTP(mobileNumber);

			try {
				objPaymentOTP.
				enterOTP(otpValue).
				clickOnVerifyOTPAndValidateThePaymentSuccessMessage();
			}catch (Exception e) {

				System.out.println("Handled the exception - start the cashout process");
				e.printStackTrace();
			}

			toggleApp();
			closeEarnKaroApp(deviceName);
			toggleApp();

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
			PendingCashoutsPage.
			validateSuccessMessage();

			//Validate Confirm Notification here : 
			contextNames = driver.getContextHandles();
			driver.context("NATIVE_APP");


			openNotification();

			new NotificationPage(driver, testInfo).
			validatePaidNotification().
			validatePaidNotification_BigText().
			clickOnPaidNotification();

			new MyEarningsPage(driver,testInfo);

		}catch (Exception e) {

			closeNotification();
			reportStep("Test FAILed , unexpectedly :(", "FAIL");

		}

	}

	@Parameters({"deviceName","user"})
	@Test
	public void referralNotifcation_AppClosed(String deviceName,String user) {

		try {

			reportStep("TC009 is  started", "INFO");

			String cbStoreName = CashKaroUtility.extractValuesFromStoreCategoryJsonAndLoadTheVariablesForStore("CB_Store_One", 0, "CB_Store_One_Name");
			String cbShortDescription = CashKaroUtility.extractValuesFromStoreCategoryJsonAndLoadTheVariablesForStore("CB_Store_One", 0, "CB_Store_One_App_Short_Description");
			String cbLinkURL = CashKaroUtility.extractValuesFromStoreCategoryJsonAndLoadTheVariablesForStore("CB_Store_One", 0, "CB_Store_One_linkURL");

			String currentDate = Utilities.today_dd_MMM_YYYY_ISTZone().trim();
			String currentMonthYear = Utilities.currentMonthYear().trim();

			CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo);
			String emailAddress = objCashKaroUtility.generateEmail();
			String mobileNumber = objCashKaroUtility.generateMobileNumber();

			String otpValue = 
					new EKOnboardingPage(driver, testInfo).
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

			//Open the App in the back ground :
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
			String name = "QAengineer";

			String OtpValue = new JoinFreePage(driver, testInfo,0).
					validateReferralByUserAtJoinFree(userName).
					enterFullName(name).
					enterEmail(emailAddressReferralJoinee).
					enterPassword().
					enterMobileNumber(mobileNumberReferralJoinee).
					clickOnGetOTPButton(mobileNumberReferralJoinee);

			new OTPPage(driver, testInfo).
			enterOTP(OtpValue).
			clickOnVerifyOTP();

			new HomePage(driver, testInfo).
			clickOnSearchIcon().
			enterTextIntoTheSearchBar(cbStoreName).
			clickStoreCardShortDescription(cbShortDescription).
			validateStoreShortDescription(cbStoreName, cbShortDescription).
			clickOnAddToShareButton().
			clickOnShareIconWithCount(1);

			new StoreDetailPage(driver, testInfo).clickOnCopyLink();

			String url1 = getClipBoardText();

			CashKaroUtility cuOne = new CashKaroUtility(udid, port, automationName, deviceName);
			this.driver = cuOne.launchChrome();

			cuOne.closeAllTabsAndOneTabInChromeBrowser(driver);
			cuOne.clickAndEnterURLInSearchOrTypeWebAddress(driver, url1);

			new IntermediateRetailerPage(driver, testInfo).
			validateRetailerPage(cbLinkURL, cbLinkURL);

			CashKaroUtility reopenCKApp = new CashKaroUtility(udid, port, automationName, deviceName, systemPort);
			driver = reopenCKApp.launchEarnKaroApp(driver);

			new HomePage(driver, testInfo).
			clickOnProfileIconForSignedUser().
			clickOnLogout().
			clickOnSignInbutton_ForSecondTime().
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
			clickBackButton();


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

			//Validate Confirm Notification here : 
			Set<String> contextNames = driver.getContextHandles();
			driver.context("NATIVE_APP");

			toggleApp();
			closeEarnKaroApp(deviceName);

			clickChromeApp();
			scrollFromDownToUpinApp();

			driver.context((String) contextNames.toArray()[1]);
			ProfitPage.clickSaveCashbackUsingAppium();
			driver.context("NATIVE_APP");

			openNotification();

			new NotificationPage(driver, testInfo).
			validateReferralConfirmedCashbackNotification().
			validateNotificationBody(deviceName).
			clickOnExpand(deviceName).
			validateReferralConfirmed_BigText().
			clickOnReferralCashbackNotification();

			new MyEarningsPage(driver,testInfo);

		}catch (Exception e) {

			closeNotification();
			reportStep("Test FAILed , unexpectedly :(", "FAIL");
		}



	}

	/*******************************************************************/
	/************* Normal Priority Test Cases Without Template *********/
	/*******************************************************************/

	//Positive flow :
	@Parameters({"deviceName","user"})
	@Test
	public void notificationTest_AppBackGround_WithNormalPriority(String deviceName,String user) {

		try {
			// TEST STEPS :
			new EKOnboardingPage(driver, testInfo).
			clickOnSignInbutton().
			enterUserName(user).
			enterPassword().
			clickSignInButtonForSuccess();

			//Open the Notification bar :
			openNotification();

			//Clear the Notification
			clearNotificationFromNotificationBar(deviceName);

			closeNotification();

			CashKaroUtility cu = new CashKaroUtility(udid, port, automationName, deviceName);
			driver = cu.launchChromeWebView(driver);
			AdminCoreFunction adminFunctions = new AdminCoreFunction(driver, testInfo);
			adminFunctions.naviagateAdminPage_ThenSelectEarnKaro();
			adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
			adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
			adminFunctions.clickSubmit();

			adminFunctions.clickOnHamburgerMenu();
			adminFunctions.clickOnEmailMaster();
			adminFunctions.clickOnEmailMasterBrowser();
			adminFunctions.selectEmailFromSearchByDropDown_EmailMaster();
			adminFunctions.enterUserEmailAsKeyForSearchEmailMaster(user);
			adminFunctions.clickOnSearchButtonEmailMaster();

			EmailMasterBrowserPage emailMasterBrowserpage = new EmailMasterBrowserPage(driver, testInfo);

			emailMasterBrowserpage.clickOnSendNotification();
			emailMasterBrowserpage.clickOnWithoutNotificationTemplate();
			emailMasterBrowserpage.enterAppNotificationTitle(testdata.get(17).get("NotificationTitle"));
			emailMasterBrowserpage.clickOnNormalPriority();
			emailMasterBrowserpage.enterImageURL(testdata.get(17).get("BigImage"));
			emailMasterBrowserpage.enterAppNotificationBody(testdata.get(17).get("NotificationBody"));
			emailMasterBrowserpage.enterBigText(testdata.get(17).get("BigText"));
			emailMasterBrowserpage.enterRedirectionalURL(testdata.get(17).get("RedirectionalURL"));
			emailMasterBrowserpage.enterLargeIcon(GetCodeSetUP.NOTIFICATIONLARGEICON);
			emailMasterBrowserpage.enterSubText(testdata.get(17).get("SubText"));
			emailMasterBrowserpage.clickOnSubmit();

			Set<String> contextNames = driver.getContextHandles();
			driver.context("NATIVE_APP");

			toggleApp(driver);
			clickEarnkaroApp(driver);
			new HomePage(driver, testInfo);
			toggleApp();
			clickChromeApp();

			driver.context((String) contextNames.toArray()[1]);
			emailMasterBrowserpage.clickOnConfirmButton();
			driver.context("NATIVE_APP");

			//Open the Notification bar :
			openNotification();

			new NotificationPage(driver, testInfo).
			validateNotificationTitle().
			validateNotificationBody(deviceName).
			clickOnExpand(deviceName).
			validateBigImage(deviceName).
			validateBigText().
			validatelargeIcon(deviceName).
			clickOnBigImage().
			validateAjioStoreRedirection();

		}catch (Exception e) {

			closeNotification();
			reportStep("Test FAILed , unexpectedly :(", "FAIL");

		}

	}

	@Parameters({"deviceName","user"})
	@Test
	public void notificationTest_AppClosed_WithNormalPriority(String deviceName,String user) {

		try {
			// TEST STEPS :
			new EKOnboardingPage(driver, testInfo).
			clickOnSignInbutton().
			enterUserName(user).
			enterPassword().
			clickSignInButtonForSuccess();

			//Open the Notification bar :
			openNotification();

			//Clear the Notification
			clearNotificationFromNotificationBar(deviceName);

			closeNotification();

			CashKaroUtility cu = new CashKaroUtility(udid, port, automationName, deviceName);
			driver = cu.launchChromeWebView(driver);
			AdminCoreFunction adminFunctions = new AdminCoreFunction(driver, testInfo);
			adminFunctions.naviagateAdminPage_ThenSelectEarnKaro();
			adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
			adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
			adminFunctions.clickSubmit();

			adminFunctions.clickOnHamburgerMenu();
			adminFunctions.clickOnEmailMaster();
			adminFunctions.clickOnEmailMasterBrowser();
			adminFunctions.selectEmailFromSearchByDropDown_EmailMaster();
			adminFunctions.enterUserEmailAsKeyForSearchEmailMaster(user);
			adminFunctions.clickOnSearchButtonEmailMaster();

			EmailMasterBrowserPage emailMasterBrowserpage = new EmailMasterBrowserPage(driver, testInfo);

			emailMasterBrowserpage.clickOnSendNotification();
			emailMasterBrowserpage.clickOnWithoutNotificationTemplate();
			emailMasterBrowserpage.enterAppNotificationTitle(testdata.get(17).get("NotificationTitle"));
			emailMasterBrowserpage.clickOnNormalPriority();
			emailMasterBrowserpage.enterImageURL(testdata.get(17).get("BigImage"));
			emailMasterBrowserpage.enterAppNotificationBody(testdata.get(17).get("NotificationBody"));
			emailMasterBrowserpage.enterBigText(testdata.get(17).get("BigText"));
			emailMasterBrowserpage.enterRedirectionalURL(testdata.get(17).get("RedirectionalURL"));
			emailMasterBrowserpage.enterLargeIcon(GetCodeSetUP.NOTIFICATIONLARGEICON);
			emailMasterBrowserpage.enterSubText(testdata.get(17).get("SubText"));
			emailMasterBrowserpage.clickOnSubmit();

			Set<String> contextNames = driver.getContextHandles();
			driver.context("NATIVE_APP");

			toggleApp(driver);
			clickEarnkaroApp(driver);
			new HomePage(driver, testInfo);
			toggleApp();
			closeEarnKaroApp(deviceName);
			clickChromeApp();

			driver.context((String) contextNames.toArray()[1]);
			emailMasterBrowserpage.clickOnConfirmButton();
			driver.context("NATIVE_APP");

			//Open the Notification bar :
			openNotification();

			new NotificationPage(driver, testInfo).
			validateNotificationTitle().
			validateNotificationBody(deviceName).
			clickOnExpand(deviceName).
			validateBigImage(deviceName).
			validateBigText().
			validatelargeIcon(deviceName).
			clickOnBigImage().
			validateAjioStoreRedirection();

		} catch (Exception e) {

			closeNotification();
			reportStep("Test FAILed , unexpectedly - May be Notification arrived late - Please look into the Step by Step report and Analyse the FAILured :(", "FAIL");

		}


	}

	@Parameters({"deviceName","user"})
	@Test
	public void notificationTest_AppBackGround_WithOutImageURL_WithoutRedirectionURL_WithoutLargeIcon_WithNormalPriority(String deviceName,String user) {

		try {
			// TEST STEPS :
			new EKOnboardingPage(driver, testInfo).
			clickOnSignInbutton().
			enterUserName(user).
			enterPassword().
			clickSignInButtonForSuccess();

			//Open the Notification bar :
			openNotification();

			//Clear the Notification
			clearNotificationFromNotificationBar(deviceName);

			closeNotification();

			CashKaroUtility cu = new CashKaroUtility(udid, port, automationName, deviceName);
			driver = cu.launchChromeWebView(driver);
			AdminCoreFunction adminFunctions = new AdminCoreFunction(driver, testInfo);
			adminFunctions.naviagateAdminPage_ThenSelectEarnKaro();
			adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
			adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
			adminFunctions.clickSubmit();

			adminFunctions.clickOnHamburgerMenu();
			adminFunctions.clickOnEmailMaster();
			adminFunctions.clickOnEmailMasterBrowser();
			adminFunctions.selectEmailFromSearchByDropDown_EmailMaster();
			adminFunctions.enterUserEmailAsKeyForSearchEmailMaster(user);
			adminFunctions.clickOnSearchButtonEmailMaster();

			EmailMasterBrowserPage emailMasterBrowserpage = new EmailMasterBrowserPage(driver, testInfo);

			emailMasterBrowserpage.clickOnSendNotification();
			emailMasterBrowserpage.clickOnWithoutNotificationTemplate();
			emailMasterBrowserpage.enterAppNotificationTitle(testdata.get(17).get("NotificationTitle"));
			emailMasterBrowserpage.clickOnNormalPriority();
			emailMasterBrowserpage.enterAppNotificationBody(testdata.get(17).get("NotificationBody"));
			emailMasterBrowserpage.enterBigText(testdata.get(17).get("BigText"));
			emailMasterBrowserpage.enterSubText(testdata.get(17).get("SubText"));
			emailMasterBrowserpage.clickOnSubmit();

			Set<String> contextNames = driver.getContextHandles();
			driver.context("NATIVE_APP");

			toggleApp(driver);
			clickEarnkaroApp(driver);
			new HomePage(driver, testInfo);
			toggleApp();
			clickChromeApp();

			driver.context((String) contextNames.toArray()[1]);
			emailMasterBrowserpage.clickOnConfirmButton();
			driver.context("NATIVE_APP");

			//Open the Notification bar :
			openNotification();

			new NotificationPage(driver, testInfo).
			validateNotificationTitle().
			validateBigText().
			clickOnNotificationTitle();

			new HomePage(driver, testInfo);


		}catch (Exception e) {

			closeNotification();
			reportStep("Test FAILed , unexpectedly :(", "FAIL");

		}

	}

	@Parameters({"deviceName","user"})
	@Test
	public void notificationTest_AppClosed_WithOutImageURL_WithoutRedirectionURL_WithoutLargeIcon_WithNormalPriority(String deviceName,String user) {

		try {
			// TEST STEPS :
			new EKOnboardingPage(driver, testInfo).
			clickOnSignInbutton().
			enterUserName(user).
			enterPassword().
			clickSignInButtonForSuccess();

			//Open the Notification bar :
			openNotification();

			//Clear the Notification
			clearNotificationFromNotificationBar(deviceName);

			closeNotification();

			CashKaroUtility cu = new CashKaroUtility(udid, port, automationName, deviceName);
			driver = cu.launchChromeWebView(driver);
			AdminCoreFunction adminFunctions = new AdminCoreFunction(driver, testInfo);
			adminFunctions.naviagateAdminPage_ThenSelectEarnKaro();
			adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
			adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
			adminFunctions.clickSubmit();

			adminFunctions.clickOnHamburgerMenu();
			adminFunctions.clickOnEmailMaster();
			adminFunctions.clickOnEmailMasterBrowser();
			adminFunctions.selectEmailFromSearchByDropDown_EmailMaster();
			adminFunctions.enterUserEmailAsKeyForSearchEmailMaster(user);
			adminFunctions.clickOnSearchButtonEmailMaster();

			EmailMasterBrowserPage emailMasterBrowserpage = new EmailMasterBrowserPage(driver, testInfo);

			emailMasterBrowserpage.clickOnSendNotification();
			emailMasterBrowserpage.clickOnWithoutNotificationTemplate();
			emailMasterBrowserpage.enterAppNotificationTitle(testdata.get(17).get("NotificationTitle"));
			emailMasterBrowserpage.clickOnNormalPriority();
			emailMasterBrowserpage.enterAppNotificationBody(testdata.get(17).get("NotificationBody"));
			emailMasterBrowserpage.enterBigText(testdata.get(17).get("BigText"));
			emailMasterBrowserpage.enterSubText(testdata.get(17).get("SubText"));
			emailMasterBrowserpage.clickOnSubmit();

			Set<String> contextNames = driver.getContextHandles();
			driver.context("NATIVE_APP");

			toggleApp(driver);
			clickEarnkaroApp(driver);
			new HomePage(driver, testInfo);
			toggleApp();
			closeEarnKaroApp(deviceName);
			clickChromeApp();

			driver.context((String) contextNames.toArray()[1]);
			emailMasterBrowserpage.clickOnConfirmButton();
			driver.context("NATIVE_APP");

			//Open the Notification bar :
			openNotification();

			new NotificationPage(driver, testInfo).
			validateNotificationTitle().
			validateBigText().
			clickOnNotificationTitle();

			new HomePage(driver, testInfo);

		} catch (Exception e) {

			closeNotification();
			reportStep("Test FAILed , unexpectedly - May be Notification arrived late - Please look into the Step by Step report and Analyse the FAILured :(", "FAIL");

		}


	}

	@Parameters({"deviceName","user"})
	//@Test
	public void notificationTest_AppBackGround_LogoutThenClickNotificaiton_WithNormalPriority(String deviceName,String user) {

		try {
			// TEST STEPS :
			new EKOnboardingPage(driver, testInfo).
			clickOnSignInbutton().
			enterUserName(user).
			enterPassword().
			clickSignInButtonForSuccess();

			//Open the Notification bar :
			openNotification();

			//Clear the Notification
			clearNotificationFromNotificationBar(deviceName);

			closeNotification();

			CashKaroUtility cu = new CashKaroUtility(udid, port, automationName, deviceName);
			driver = cu.launchChromeWebView(driver);
			AdminCoreFunction adminFunctions = new AdminCoreFunction(driver, testInfo);
			adminFunctions.naviagateAdminPage_ThenSelectEarnKaro();
			adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
			adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
			adminFunctions.clickSubmit();

			adminFunctions.clickOnHamburgerMenu();
			adminFunctions.clickOnEmailMaster();
			adminFunctions.clickOnEmailMasterBrowser();
			adminFunctions.selectEmailFromSearchByDropDown_EmailMaster();
			adminFunctions.enterUserEmailAsKeyForSearchEmailMaster(user);
			adminFunctions.clickOnSearchButtonEmailMaster();

			EmailMasterBrowserPage emailMasterBrowserpage = new EmailMasterBrowserPage(driver, testInfo);

			emailMasterBrowserpage.clickOnSendNotification();
			emailMasterBrowserpage.clickOnWithoutNotificationTemplate();
			emailMasterBrowserpage.enterAppNotificationTitle(testdata.get(17).get("NotificationTitle"));
			emailMasterBrowserpage.clickOnNormalPriority();
			emailMasterBrowserpage.enterImageURL(testdata.get(17).get("BigImage"));
			emailMasterBrowserpage.enterAppNotificationBody(testdata.get(17).get("NotificationBody"));
			emailMasterBrowserpage.enterBigText(testdata.get(17).get("BigText"));
			emailMasterBrowserpage.enterRedirectionalURL(testdata.get(17).get("RedirectionalURL"));
			emailMasterBrowserpage.enterLargeIcon(GetCodeSetUP.NOTIFICATIONLARGEICON);
			emailMasterBrowserpage.enterSubText(testdata.get(17).get("SubText"));
			emailMasterBrowserpage.clickOnSubmit();

			Set<String> contextNames = driver.getContextHandles();
			driver.context("NATIVE_APP");

			toggleApp(driver);

			clickEarnkaroApp(driver);
			new HomePage(driver, testInfo);

			toggleApp();
			clickChromeApp();

			driver.context((String) contextNames.toArray()[1]);
			emailMasterBrowserpage.clickOnConfirmButton();
			driver.context("NATIVE_APP");

			toggleApp(driver);
			clickEarnkaroApp(driver);
			new HomePage(driver, testInfo).
			clickOnProfileIconForSignedUser().
			clickOnLogout();

			//Open the Notification bar :
			openNotification();

			new NotificationPage(driver, testInfo).
			validateNotificationTitle().
			validateNotificationBody(deviceName).
			clickOnExpand(deviceName).
			validateBigImage(deviceName).
			validateBigText().
			validatelargeIcon(deviceName).
			clickOnBigImage();

			new SignInPage(driver, testInfo);


		}catch (Exception e) {

			closeNotification();
			reportStep("Test FAILed , unexpectedly :(", "FAIL");

		}

	}

	@Parameters({"deviceName","user"})
	//@Test
	public void notificationTest_AppClosed_LogoutThenClickNotificaiton_WithNormalPriority(String deviceName,String user) {

		try {
			// TEST STEPS :
			new EKOnboardingPage(driver, testInfo).
			clickOnSignInbutton().
			enterUserName(user).
			enterPassword().
			clickSignInButtonForSuccess();

			//Open the Notification bar :
			openNotification();

			//Clear the Notification
			clearNotificationFromNotificationBar(deviceName);

			closeNotification();

			CashKaroUtility cu = new CashKaroUtility(udid, port, automationName, deviceName);
			driver = cu.launchChromeWebView(driver);
			AdminCoreFunction adminFunctions = new AdminCoreFunction(driver, testInfo);
			adminFunctions.naviagateAdminPage_ThenSelectEarnKaro();
			adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
			adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
			adminFunctions.clickSubmit();

			adminFunctions.clickOnHamburgerMenu();
			adminFunctions.clickOnEmailMaster();
			adminFunctions.clickOnEmailMasterBrowser();
			adminFunctions.selectEmailFromSearchByDropDown_EmailMaster();
			adminFunctions.enterUserEmailAsKeyForSearchEmailMaster(user);
			adminFunctions.clickOnSearchButtonEmailMaster();

			EmailMasterBrowserPage emailMasterBrowserpage = new EmailMasterBrowserPage(driver, testInfo);

			emailMasterBrowserpage.clickOnSendNotification();
			emailMasterBrowserpage.clickOnWithoutNotificationTemplate();
			emailMasterBrowserpage.enterAppNotificationTitle(testdata.get(17).get("NotificationTitle"));
			emailMasterBrowserpage.clickOnNormalPriority();
			emailMasterBrowserpage.enterImageURL(testdata.get(17).get("BigImage"));
			emailMasterBrowserpage.enterAppNotificationBody(testdata.get(17).get("NotificationBody"));
			emailMasterBrowserpage.enterBigText(testdata.get(17).get("BigText"));
			emailMasterBrowserpage.enterRedirectionalURL(testdata.get(17).get("RedirectionalURL"));
			emailMasterBrowserpage.enterLargeIcon(GetCodeSetUP.NOTIFICATIONLARGEICON);
			emailMasterBrowserpage.enterSubText(testdata.get(17).get("SubText"));
			emailMasterBrowserpage.clickOnSubmit();

			Set<String> contextNames = driver.getContextHandles();
			driver.context("NATIVE_APP");

			toggleApp(driver);
			clickEarnkaroApp(driver);
			new HomePage(driver, testInfo);

			toggleApp();
			clickChromeApp();

			driver.context((String) contextNames.toArray()[1]);
			emailMasterBrowserpage.clickOnConfirmButton();
			driver.context("NATIVE_APP");

			toggleApp(driver);
			clickEarnkaroApp(driver);

			new HomePage(driver, testInfo).
			clickOnProfileIconForSignedUser().
			clickOnLogout();

			toggleApp();
			closeEarnKaroApp(deviceName);
			clickChromeApp();

			//Open the Notification bar :
			openNotification();

			new NotificationPage(driver, testInfo).
			validateNotificationTitle().
			validateNotificationBody(deviceName).
			clickOnExpand(deviceName).
			validateBigImage(deviceName).
			validateBigText().
			validatelargeIcon(deviceName).
			clickOnBigImage();

			new SignInPage(driver, testInfo);

		} catch (Exception e) {

			closeNotification();
			reportStep("Test FAILed , unexpectedly - May be Notification arrived late - Please look into the Step by Step report and Analyse the FAILured :(", "FAIL");

		}


	}

	/*****************************************************************************/
	/************************* Normal Priority With Template  ********************/
	/*****************************************************************************/

	//Positive flow :
	@Parameters({"deviceName","user"})
	@Test
	public void notificationTest_AppBackGround_WithTemplate_NormalPriority(String deviceName,String user) {

		try {

			// TEST STEPS :
			new EKOnboardingPage(driver, testInfo).
			clickOnSignInbutton().
			enterUserName(user).
			enterPassword().
			clickSignInButtonForSuccess();

			//Open the Notification bar :
			openNotification();

			//Clear the Notification
			clearNotificationFromNotificationBar(deviceName);

			closeNotification();

			CashKaroUtility cu = new CashKaroUtility(udid, port, automationName, deviceName);
			driver = cu.launchChromeWebView(driver);
			AdminCoreFunction adminFunctions = new AdminCoreFunction(driver, testInfo);
			adminFunctions.naviagateAdminPage_ThenSelectEarnKaro();
			adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
			adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
			adminFunctions.clickSubmit();

			adminFunctions.clickOnHamburgerMenu();
			adminFunctions.clickOnEmailMaster();
			adminFunctions.clickOnEmailMasterBrowser();
			adminFunctions.selectEmailFromSearchByDropDown_EmailMaster();
			adminFunctions.enterUserEmailAsKeyForSearchEmailMaster(user);
			adminFunctions.clickOnSearchButtonEmailMaster();

			EmailMasterBrowserPage emailMasterBrowserpage = new EmailMasterBrowserPage(driver, testInfo);

			emailMasterBrowserpage.clickOnSendNotification();
			emailMasterBrowserpage.clickOnWithNotificationTemplate();
			emailMasterBrowserpage.selectNormalPriorityWithNotificationTemplate();
			emailMasterBrowserpage.selectSiteTitleAsUserName();
			emailMasterBrowserpage.clickOnSendNotificatonButtonForWithTemplate();

			Set<String> contextNames = driver.getContextHandles();
			driver.context("NATIVE_APP");

			toggleApp(driver);
			clickEarnkaroApp(driver);
			new HomePage(driver, testInfo);
			toggleApp();
			clickChromeApp();

			driver.context((String) contextNames.toArray()[1]);
			emailMasterBrowserpage.clickOnConfirmButton();
			driver.context("NATIVE_APP");

			//Open the Notification bar :
			openNotification();

			new NotificationPage(driver, testInfo).
			validateNotificationTitle_WithTemplateNormalPriority().
			validateNotificationBody(deviceName).
			clickOnExpand(deviceName).
			validateBigImage(deviceName).
			validateBigText().
			validatelargeIcon(deviceName).
			clickOnBigImage().
			validateNotificationTestStorePage();

		}catch (Exception e) {

			closeNotification();
			reportStep("Test FAILed , unexpectedly :(", "FAIL");

		}

	}

	@Parameters({"deviceName","user"})
	@Test
	public void notificationTest_AppClosed_WithTemplate_NormalPriority(String deviceName,String user) {

		try {
			// TEST STEPS :
			new EKOnboardingPage(driver, testInfo).
			clickOnSignInbutton().
			enterUserName(user).
			enterPassword().
			clickSignInButtonForSuccess();

			//Open the Notification bar :
			openNotification();

			//Clear the Notification
			clearNotificationFromNotificationBar(deviceName);

			closeNotification();

			CashKaroUtility cu = new CashKaroUtility(udid, port, automationName, deviceName);
			driver = cu.launchChromeWebView(driver);
			AdminCoreFunction adminFunctions = new AdminCoreFunction(driver, testInfo);
			adminFunctions.naviagateAdminPage_ThenSelectEarnKaro();
			adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
			adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
			adminFunctions.clickSubmit();

			adminFunctions.clickOnHamburgerMenu();
			adminFunctions.clickOnEmailMaster();
			adminFunctions.clickOnEmailMasterBrowser();
			adminFunctions.selectEmailFromSearchByDropDown_EmailMaster();
			adminFunctions.enterUserEmailAsKeyForSearchEmailMaster(user);
			adminFunctions.clickOnSearchButtonEmailMaster();

			EmailMasterBrowserPage emailMasterBrowserpage = new EmailMasterBrowserPage(driver, testInfo);

			emailMasterBrowserpage.clickOnSendNotification();
			emailMasterBrowserpage.clickOnWithNotificationTemplate();
			emailMasterBrowserpage.selectNormalPriorityWithNotificationTemplate();
			emailMasterBrowserpage.selectSiteTitleAsUserName();
			emailMasterBrowserpage.clickOnSendNotificatonButtonForWithTemplate();

			Set<String> contextNames = driver.getContextHandles();
			driver.context("NATIVE_APP");

			toggleApp(driver);
			clickEarnkaroApp(driver);
			new HomePage(driver, testInfo);
			toggleApp();
			closeEarnKaroApp(deviceName);
			clickChromeApp();

			driver.context((String) contextNames.toArray()[1]);
			emailMasterBrowserpage.clickOnConfirmButton();
			driver.context("NATIVE_APP");

			//Open the Notification bar :
			openNotification();

			new NotificationPage(driver, testInfo).
			validateNotificationTitle_WithTemplateNormalPriority().
			validateNotificationBody(deviceName).
			clickOnExpand(deviceName).
			validateBigImage(deviceName).
			validateBigText().
			validatelargeIcon(deviceName).
			clickOnBigImage().
			validateNotificationTestStorePage();

		}catch (Exception e) {

			closeNotification();

			reportStep("Test FAILed , unexpectedly - May be Notification arrived late - Please look into the Step by Step report and Analyse the FAILured :(", "FAIL");

		}


	}

	//After logout Negative scenarios : Negative flow

	@Parameters({"deviceName","user"})
	//@Test
	public void notificationTest_AppBackGround_LogoutThenClickNotificaiton_WithTemplate_NormalPriority(String deviceName,String user) {

		try {
			// TEST STEPS :
			new EKOnboardingPage(driver, testInfo).
			clickOnSignInbutton().
			enterUserName(user).
			enterPassword().
			clickSignInButtonForSuccess();

			//Open the Notification bar :
			openNotification();

			//Clear the Notification
			clearNotificationFromNotificationBar(deviceName);

			closeNotification();

			CashKaroUtility cu = new CashKaroUtility(udid, port, automationName, deviceName);
			driver = cu.launchChromeWebView(driver);
			AdminCoreFunction adminFunctions = new AdminCoreFunction(driver, testInfo);
			adminFunctions.naviagateAdminPage_ThenSelectEarnKaro();
			adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
			adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
			adminFunctions.clickSubmit();

			adminFunctions.clickOnHamburgerMenu();
			adminFunctions.clickOnEmailMaster();
			adminFunctions.clickOnEmailMasterBrowser();
			adminFunctions.selectEmailFromSearchByDropDown_EmailMaster();
			adminFunctions.enterUserEmailAsKeyForSearchEmailMaster(user);
			adminFunctions.clickOnSearchButtonEmailMaster();

			EmailMasterBrowserPage emailMasterBrowserpage = new EmailMasterBrowserPage(driver, testInfo);

			emailMasterBrowserpage.clickOnSendNotification();
			emailMasterBrowserpage.clickOnWithNotificationTemplate();
			emailMasterBrowserpage.selectNormalPriorityWithNotificationTemplate();
			emailMasterBrowserpage.selectSiteTitleAsUserName();
			emailMasterBrowserpage.clickOnSendNotificatonButtonForWithTemplate();

			Set<String> contextNames = driver.getContextHandles();
			driver.context("NATIVE_APP");

			toggleApp(driver);

			clickEarnkaroApp(driver);
			new HomePage(driver, testInfo);

			toggleApp();
			clickChromeApp();

			driver.context((String) contextNames.toArray()[1]);
			emailMasterBrowserpage.clickOnConfirmButton();
			driver.context("NATIVE_APP");

			toggleApp(driver);
			clickEarnkaroApp(driver);
			new HomePage(driver, testInfo).
			clickOnProfileIconForSignedUser().
			clickOnLogout();

			//Open the Notification bar :
			openNotification();

			new NotificationPage(driver, testInfo).
			validateNotificationTitle_WithTemplateNormalPriority().
			validateNotificationBody(deviceName).
			clickOnExpand(deviceName).
			validateBigImage(deviceName).
			validateBigText().
			validatelargeIcon(deviceName).
			clickOnBigImage();

			new SignInPage(driver, testInfo);


		}catch (Exception e) {

			closeNotification();
			reportStep("Test FAILed , unexpectedly :(", "FAIL");

		}

	}

	@Parameters({"deviceName","user"})
	//@Test
	public void notificationTest_AppClosed_LogoutThenClickNotificaiton_WithTemplate_NormalPriority(String deviceName,String user) {

		try {
			// TEST STEPS :
			new EKOnboardingPage(driver, testInfo).
			clickOnSignInbutton().
			enterUserName(user).
			enterPassword().
			clickSignInButtonForSuccess();

			//Open the Notification bar :
			openNotification();

			//Clear the Notification
			clearNotificationFromNotificationBar(deviceName);
			closeNotification();


			CashKaroUtility cu = new CashKaroUtility(udid, port, automationName, deviceName);
			driver = cu.launchChromeWebView(driver);
			AdminCoreFunction adminFunctions = new AdminCoreFunction(driver, testInfo);
			adminFunctions.naviagateAdminPage_ThenSelectEarnKaro();
			adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
			adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
			adminFunctions.clickSubmit();

			adminFunctions.clickOnHamburgerMenu();
			adminFunctions.clickOnEmailMaster();
			adminFunctions.clickOnEmailMasterBrowser();
			adminFunctions.selectEmailFromSearchByDropDown_EmailMaster();
			adminFunctions.enterUserEmailAsKeyForSearchEmailMaster(user);
			adminFunctions.clickOnSearchButtonEmailMaster();

			EmailMasterBrowserPage emailMasterBrowserpage = new EmailMasterBrowserPage(driver, testInfo);

			emailMasterBrowserpage.clickOnSendNotification();
			emailMasterBrowserpage.clickOnWithNotificationTemplate();
			emailMasterBrowserpage.selectNormalPriorityWithNotificationTemplate();
			emailMasterBrowserpage.selectSiteTitleAsUserName();
			emailMasterBrowserpage.clickOnSendNotificatonButtonForWithTemplate();

			Set<String> contextNames = driver.getContextHandles();
			driver.context("NATIVE_APP");

			toggleApp(driver);
			clickEarnkaroApp(driver);
			new HomePage(driver, testInfo);

			toggleApp();
			clickChromeApp();

			driver.context((String) contextNames.toArray()[1]);
			emailMasterBrowserpage.clickOnConfirmButton();
			driver.context("NATIVE_APP");

			toggleApp(driver);
			clickEarnkaroApp(driver);

			new HomePage(driver, testInfo).
			clickOnProfileIconForSignedUser().
			clickOnLogout();

			toggleApp();
			closeEarnKaroApp(deviceName);
			clickChromeApp();

			//Open the Notification bar :
			openNotification();

			new NotificationPage(driver, testInfo).
			validateNotificationTitle_WithTemplateNormalPriority().
			validateNotificationBody(deviceName).
			clickOnExpand(deviceName).
			validateBigImage(deviceName).
			validateBigText().
			validatelargeIcon(deviceName).
			clickOnBigImage();

			new SignInPage(driver, testInfo);

		} catch (Exception e) {

			closeNotification();
			reportStep("Test FAILed , unexpectedly - May be Notification arrived late - Please look into the Step by Step report and Analyse the FAILured :(", "FAIL");

		}


	}

	/*******************************************************************/
	/************************* Exceptional Scenarios********************/
	/*******************************************************************/

	//Positive flow :
	@Parameters({"deviceName","user"})
	//@Test
	public void pushNotificationOnceAfterLogout_AppBackGround(String deviceName,String user) {

		try {
			// TEST STEPS :
			new EKOnboardingPage(driver, testInfo).
			clickOnSignInbutton().
			enterUserName(user).
			enterPassword().
			clickSignInButtonForSuccess();

			//Open the Notification bar :
			openNotification();

			//Clear the Notification
			clearNotificationFromNotificationBar(deviceName);

			closeNotification();

			CashKaroUtility cu = new CashKaroUtility(udid, port, automationName, deviceName);
			driver = cu.launchChromeWebView(driver);
			AdminCoreFunction adminFunctions = new AdminCoreFunction(driver, testInfo);
			adminFunctions.naviagateAdminPage_ThenSelectEarnKaro();
			adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
			adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
			adminFunctions.clickSubmit();

			adminFunctions.clickOnHamburgerMenu();
			adminFunctions.clickOnEmailMaster();
			adminFunctions.clickOnEmailMasterBrowser();
			adminFunctions.selectEmailFromSearchByDropDown_EmailMaster();
			adminFunctions.enterUserEmailAsKeyForSearchEmailMaster(user);
			adminFunctions.clickOnSearchButtonEmailMaster();

			EmailMasterBrowserPage emailMasterBrowserpage = new EmailMasterBrowserPage(driver, testInfo);

			emailMasterBrowserpage.clickOnSendNotification();
			emailMasterBrowserpage.clickOnWithoutNotificationTemplate();
			emailMasterBrowserpage.enterAppNotificationTitle(testdata.get(17).get("NotificationTitle"));
			emailMasterBrowserpage.enterImageURL(testdata.get(17).get("BigImage"));
			emailMasterBrowserpage.enterAppNotificationBody(testdata.get(17).get("NotificationBody"));
			emailMasterBrowserpage.enterBigText(testdata.get(17).get("BigText"));
			emailMasterBrowserpage.enterRedirectionalURL(testdata.get(17).get("RedirectionalURL"));
			emailMasterBrowserpage.enterLargeIcon(GetCodeSetUP.NOTIFICATIONLARGEICON);
			emailMasterBrowserpage.enterSubText(testdata.get(17).get("SubText"));
			emailMasterBrowserpage.clickOnSubmit();

			Set<String> contextNames = driver.getContextHandles();
			driver.context("NATIVE_APP");

			toggleApp(driver);
			clickEarnkaroApp(driver);
			new HomePage(driver, testInfo).
			clickOnProfileIconForSignedUser().
			clickOnLogout();
			toggleApp();
			clickChromeApp();

			driver.context((String) contextNames.toArray()[1]);
			emailMasterBrowserpage.clickOnConfirmButton();
			driver.context("NATIVE_APP");

			//Open the Notification bar :
			openNotification();

			new NotificationPage(driver, testInfo).
			validateNotificationIsnotAppearedForUnsignedUser();

		}catch (Exception e) {

			closeNotification();
			reportStep("Test FAILed , unexpectedly :(", "FAIL");

		}

	}

	@Parameters({"deviceName","user"})
	//@Test
	public void pushNotificationOnceAfterLogout_AppClosed(String deviceName,String user) {

		try {
			// TEST STEPS :
			new EKOnboardingPage(driver, testInfo).
			clickOnSignInbutton().
			enterUserName(user).
			enterPassword().
			clickSignInButtonForSuccess();

			//Open the Notification bar :
			openNotification();

			//Clear the Notification
			clearNotificationFromNotificationBar(deviceName);

			closeNotification();

			CashKaroUtility cu = new CashKaroUtility(udid, port, automationName, deviceName);
			driver = cu.launchChromeWebView(driver);
			AdminCoreFunction adminFunctions = new AdminCoreFunction(driver, testInfo);
			adminFunctions.naviagateAdminPage_ThenSelectEarnKaro();
			adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
			adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
			adminFunctions.clickSubmit();

			adminFunctions.clickOnHamburgerMenu();
			adminFunctions.clickOnEmailMaster();
			adminFunctions.clickOnEmailMasterBrowser();
			adminFunctions.selectEmailFromSearchByDropDown_EmailMaster();
			adminFunctions.enterUserEmailAsKeyForSearchEmailMaster(user);
			adminFunctions.clickOnSearchButtonEmailMaster();

			EmailMasterBrowserPage emailMasterBrowserpage = new EmailMasterBrowserPage(driver, testInfo);

			emailMasterBrowserpage.clickOnSendNotification();
			emailMasterBrowserpage.clickOnWithoutNotificationTemplate();
			emailMasterBrowserpage.enterAppNotificationTitle(testdata.get(17).get("NotificationTitle"));
			emailMasterBrowserpage.enterImageURL(testdata.get(17).get("BigImage"));
			emailMasterBrowserpage.enterAppNotificationBody(testdata.get(17).get("NotificationBody"));
			emailMasterBrowserpage.enterBigText(testdata.get(17).get("BigText"));
			emailMasterBrowserpage.enterRedirectionalURL(testdata.get(17).get("RedirectionalURL"));
			emailMasterBrowserpage.enterLargeIcon(GetCodeSetUP.NOTIFICATIONLARGEICON);
			emailMasterBrowserpage.enterSubText(testdata.get(17).get("SubText"));
			emailMasterBrowserpage.clickOnSubmit();

			Set<String> contextNames = driver.getContextHandles();
			driver.context("NATIVE_APP");

			toggleApp(driver);
			clickEarnkaroApp(driver);
			new HomePage(driver, testInfo).
			clickOnProfileIconForSignedUser().
			clickOnLogout();

			toggleApp();
			closeEarnKaroApp(deviceName);
			clickChromeApp();

			driver.context((String) contextNames.toArray()[1]);
			emailMasterBrowserpage.clickOnConfirmButton();
			driver.context("NATIVE_APP");

			//Open the Notification bar :
			openNotification();

			new NotificationPage(driver, testInfo).
			validateNotificationIsnotAppearedForUnsignedUser();

		} catch (Exception e) {

			closeNotification();
			reportStep("Test FAILed , unexpectedly - May be Notification arrived late - Please look into the Step by Step report and Analyse the FAILured :(", "FAIL");

		}


	}

	@Parameters({"deviceName","user"})
	@Test
	public void clickOnOtherUserNotificaton_AppBackGround(String deviceName,String user) {

		try {
			// TEST STEPS :
			new EKOnboardingPage(driver, testInfo).
			clickOnSignInbutton().
			enterUserName(user).
			enterPassword().
			clickSignInButtonForSuccess();

			//Open the Notification bar :
			openNotification();

			//Clear the Notification
			clearNotificationFromNotificationBar(deviceName);

			closeNotification();

			CashKaroUtility cu = new CashKaroUtility(udid, port, automationName, deviceName);
			driver = cu.launchChromeWebView(driver);
			AdminCoreFunction adminFunctions = new AdminCoreFunction(driver, testInfo);
			adminFunctions.naviagateAdminPage_ThenSelectEarnKaro();
			adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
			adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
			adminFunctions.clickSubmit();

			adminFunctions.clickOnHamburgerMenu();
			adminFunctions.clickOnEmailMaster();
			adminFunctions.clickOnEmailMasterBrowser();
			adminFunctions.selectEmailFromSearchByDropDown_EmailMaster();
			adminFunctions.enterUserEmailAsKeyForSearchEmailMaster(user);
			adminFunctions.clickOnSearchButtonEmailMaster();

			EmailMasterBrowserPage emailMasterBrowserpage = new EmailMasterBrowserPage(driver, testInfo);

			emailMasterBrowserpage.clickOnSendNotification();
			emailMasterBrowserpage.clickOnWithoutNotificationTemplate();
			emailMasterBrowserpage.enterAppNotificationTitle(testdata.get(17).get("NotificationTitle"));
			emailMasterBrowserpage.enterImageURL(testdata.get(17).get("BigImage"));
			emailMasterBrowserpage.enterAppNotificationBody(testdata.get(17).get("NotificationBody"));
			emailMasterBrowserpage.enterBigText(testdata.get(17).get("BigText"));
			emailMasterBrowserpage.enterRedirectionalURL(testdata.get(17).get("RedirectionalURL"));
			emailMasterBrowserpage.enterLargeIcon(GetCodeSetUP.NOTIFICATIONLARGEICON);
			emailMasterBrowserpage.enterSubText(testdata.get(17).get("SubText"));
			emailMasterBrowserpage.clickOnSubmit();

			Set<String> contextNames = driver.getContextHandles();
			driver.context("NATIVE_APP");

			toggleApp(driver);

			clickEarnkaroApp(driver);
			new HomePage(driver, testInfo);

			toggleApp();
			clickChromeApp();

			driver.context((String) contextNames.toArray()[1]);
			emailMasterBrowserpage.clickOnConfirmButton();
			driver.context("NATIVE_APP");

			toggleApp(driver);
			clickEarnkaroApp(driver);
			new HomePage(driver, testInfo).
			clickOnProfileIconForSignedUser().
			clickOnLogout().
			clickOnSignInbutton().
			enterUserName(testdata.get(0).get("TC001_AntotherUser")).
			enterPassword().
			clickSignInButtonForSuccess();

			//Open the Notification bar :
			openNotification();

			new NotificationPage(driver, testInfo).
			validateNotificationTitle().
			validateNotificationBody(deviceName).
			clickOnExpand(deviceName).
			validateBigImage(deviceName).
			validateBigText().
			validatelargeIcon(deviceName).
			clickOnBigImage().
			validateAjioStoreRedirection();

			new StoreDetailPage(driver, testInfo).
			clickOnProfileIconForSignedUser().
			clickOnLogout();


		}catch (Exception e) {

			closeNotification();
			reportStep("Test FAILed , unexpectedly :(", "FAIL");

		}

	}

	@Parameters({"deviceName","user"})
	@Test
	public void clickOnOtherUserNotificaton_AppClosed(String deviceName,String user) {

		try {
			// TEST STEPS :
			new EKOnboardingPage(driver, testInfo).
			clickOnSignInbutton().
			enterUserName(user).
			enterPassword().
			clickSignInButtonForSuccess();

			//Open the Notification bar :
			openNotification();

			//Clear the Notification
			clearNotificationFromNotificationBar(deviceName);

			closeNotification();

			CashKaroUtility cu = new CashKaroUtility(udid, port, automationName, deviceName);
			driver = cu.launchChromeWebView(driver);
			AdminCoreFunction adminFunctions = new AdminCoreFunction(driver, testInfo);
			adminFunctions.naviagateAdminPage_ThenSelectEarnKaro();
			adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
			adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
			adminFunctions.clickSubmit();

			adminFunctions.clickOnHamburgerMenu();
			adminFunctions.clickOnEmailMaster();
			adminFunctions.clickOnEmailMasterBrowser();
			adminFunctions.selectEmailFromSearchByDropDown_EmailMaster();
			adminFunctions.enterUserEmailAsKeyForSearchEmailMaster(user);
			adminFunctions.clickOnSearchButtonEmailMaster();

			EmailMasterBrowserPage emailMasterBrowserpage = new EmailMasterBrowserPage(driver, testInfo);

			emailMasterBrowserpage.clickOnSendNotification();
			emailMasterBrowserpage.clickOnWithoutNotificationTemplate();
			emailMasterBrowserpage.enterAppNotificationTitle(testdata.get(17).get("NotificationTitle"));
			emailMasterBrowserpage.enterImageURL(testdata.get(17).get("BigImage"));
			emailMasterBrowserpage.enterAppNotificationBody(testdata.get(17).get("NotificationBody"));
			emailMasterBrowserpage.enterBigText(testdata.get(17).get("BigText"));
			emailMasterBrowserpage.enterRedirectionalURL(testdata.get(17).get("RedirectionalURL"));
			emailMasterBrowserpage.enterLargeIcon(GetCodeSetUP.NOTIFICATIONLARGEICON);
			emailMasterBrowserpage.enterSubText(testdata.get(17).get("SubText"));
			emailMasterBrowserpage.clickOnSubmit();

			Set<String> contextNames = driver.getContextHandles();
			driver.context("NATIVE_APP");

			toggleApp(driver);
			clickEarnkaroApp(driver);
			new HomePage(driver, testInfo);

			toggleApp();
			clickChromeApp();

			driver.context((String) contextNames.toArray()[1]);
			emailMasterBrowserpage.clickOnConfirmButton();
			driver.context("NATIVE_APP");

			toggleApp(driver);
			clickEarnkaroApp(driver);

			new HomePage(driver, testInfo).
			clickOnProfileIconForSignedUser().
			clickOnLogout().
			clickOnSignInbutton().
			enterUserName(testdata.get(0).get("TC001_AntotherUser")).
			enterPassword().
			clickSignInButtonForSuccess();

			toggleApp();
			closeEarnKaroApp(deviceName);
			clickChromeApp();

			//Open the Notification bar :
			openNotification();

			new NotificationPage(driver, testInfo).
			validateNotificationTitle().
			validateNotificationBody(deviceName).
			clickOnExpand(deviceName).
			validateBigImage(deviceName).
			validateBigText().
			validatelargeIcon(deviceName).
			clickOnBigImage().
			validateAjioStoreRedirection();

			new StoreDetailPage(driver, testInfo).
			clickOnProfileIconForSignedUser().
			clickOnLogout();

		} catch (Exception e) {

			closeNotification();
			reportStep("Test FAILed , unexpectedly - May be Notification arrived late - Please look into the Step by Step report and Analyse the FAILured :(", "FAIL");

		}

	}

	@Parameters({"deviceName","user"})
	@Test
	public void multipleNotificationValidation(String deviceName,String user) {

		try {
			// TEST STEPS :
			new EKOnboardingPage(driver, testInfo).
			clickOnSignInbutton().
			enterUserName(user).
			enterPassword().
			clickSignInButtonForSuccess();

			//Open the Notification bar :
			openNotification();

			//Clear the Notification
			clearNotificationFromNotificationBar(deviceName);

			closeNotification();

			CashKaroUtility cu = new CashKaroUtility(udid, port, automationName, deviceName);
			driver = cu.launchChromeWebView(driver);
			AdminCoreFunction adminFunctions = new AdminCoreFunction(driver, testInfo);
			adminFunctions.naviagateAdminPage_ThenSelectEarnKaro();
			adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
			adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
			adminFunctions.clickSubmit();

			adminFunctions.clickOnHamburgerMenu();
			adminFunctions.clickOnEmailMaster();
			adminFunctions.clickOnEmailMasterBrowser();
			adminFunctions.selectEmailFromSearchByDropDown_EmailMaster();
			adminFunctions.enterUserEmailAsKeyForSearchEmailMaster(user);
			adminFunctions.clickOnSearchButtonEmailMaster();

			EmailMasterBrowserPage emailMasterBrowserpage = new EmailMasterBrowserPage(driver, testInfo);

			emailMasterBrowserpage.clickOnSendNotification();
			emailMasterBrowserpage.clickOnWithoutNotificationTemplate();
			emailMasterBrowserpage.enterAppNotificationTitle(testdata.get(17).get("NotificationTitle"));
			emailMasterBrowserpage.enterImageURL(testdata.get(17).get("BigImage"));
			emailMasterBrowserpage.enterAppNotificationBody(testdata.get(17).get("NotificationBody"));
			emailMasterBrowserpage.enterBigText(testdata.get(17).get("BigText"));
			emailMasterBrowserpage.enterRedirectionalURL(testdata.get(17).get("RedirectionalURL"));
			emailMasterBrowserpage.enterLargeIcon(GetCodeSetUP.NOTIFICATIONLARGEICON);
			emailMasterBrowserpage.enterSubText(testdata.get(17).get("SubText"));
			emailMasterBrowserpage.clickOnSubmit();

			Set<String> contextNames = driver.getContextHandles();
			driver.context("NATIVE_APP");

			toggleApp(driver);
			clickEarnkaroApp(driver);
			new HomePage(driver, testInfo);
			toggleApp();
			closeEarnKaroApp(deviceName);
			clickChromeApp();

			//1st time push notification :
			driver.context((String) contextNames.toArray()[1]);
			emailMasterBrowserpage.clickOnConfirmButton();


			//2nd time push notification :
			emailMasterBrowserpage.clickOnSubmit();
			emailMasterBrowserpage.clickOnConfirmButton();

			//3rd time push notification :
			emailMasterBrowserpage.clickOnSubmit();
			emailMasterBrowserpage.clickOnConfirmButton();

			//Chnage to Native App
			driver.context("NATIVE_APP");

			//Open the Notification bar :
			openNotification();


			NotificationPage objNotification = new NotificationPage(driver, testInfo).
					validateTheMultipleNotificationOnNotificationBar(3).
					validateNotificationBody(deviceName).
					clickOnExpand(deviceName).
					validateBigImage(deviceName).
					validateBigText().
					validatelargeIcon(deviceName).
					clickOnBigImage().
					validateAjioStoreRedirection();

			//Open the Notification bar :
			openNotification();

			objNotification.
			validateTheMultipleNotificationOnNotificationBar(2).
			validateNotificationBody(deviceName).
			clickOnExpand(deviceName).
			validateBigImage(deviceName).
			validateBigText().
			validatelargeIcon(deviceName).
			clickOnBigImage().
			validateAjioStoreRedirection();

			//Open the Notification bar :
			openNotification();

			objNotification.
			validateTheMultipleNotificationOnNotificationBar(1).
			validateNotificationBody(deviceName).
			clickOnExpand(deviceName).
			validateBigImage(deviceName).
			validateBigText().
			validatelargeIcon(deviceName).
			clickOnBigImage().
			validateAjioStoreRedirection();

			//Open the Notification bar :
			openNotification();

			objNotification.
			validateNotificationIsnotAppearedForUnsignedUser();

			closeNotification();


		} catch (Exception e) {

			e.printStackTrace();

			System.out.println(e.getMessage());

			closeNotification();
			reportStep("Test FAILed , unexpectedly - May be Notification arrived late - Please look into the Step by Step report and Analyse the FAILured :(", "FAIL");

		}


	}


	/*******************************************************************/
	/*************** Fore Ground Notification - High priority**********/
	/*******************************************************************/

	@Parameters({"deviceName","user"})
	@Test
	public void notificationTest_ForeGround_FromHomePage(String deviceName,String user) throws InterruptedException {

		try {

			SignInPage signInPage = 
					new EKOnboardingPage(driver, testInfo).
					clickOnSignInbutton();

			CashKaroUtility ckUtility = new CashKaroUtility(driver, testInfo);


			signInPage.
			enterUserName(user).
			enterPassword().
			clickSignInButtonForSuccess();

			String fcmID =  ckUtility.getFCMID(user);

			//Open the Notification bar :
			openNotification();

			//Clear the Notification
			clearNotificationFromNotificationBar(deviceName);

			closeNotification();

			ckUtility.notificationAPICall(fcmID);

			//Open the Notification bar :
			openNotification();

			new NotificationPage(driver, testInfo).
			validateNotificationTitle().
			validateNotificationBody(deviceName).
			clickOnExpand(deviceName).
			validateBigImage(deviceName).
			validateBigText().
			validatelargeIcon(deviceName).
			clickOnBigImage().
			validateAjioStoreRedirection();

		} catch (Exception e) {

			closeNotification();
			reportStep("Test FAILed , unexpectedly :( - close the notification bar if open", "FAIL");

		}



	}

	@Parameters({"deviceName","user"})
	@Test
	public void notificationTest_ForeGround_FromMyEarningsPage(String deviceName,String user) throws InterruptedException {

		try {

			SignInPage signInPage = 
					new EKOnboardingPage(driver, testInfo).
					clickOnSignInbutton();

			CashKaroUtility ckUtility = new CashKaroUtility(driver, testInfo);


			signInPage.
			enterUserName(user).
			enterPassword().
			clickSignInButtonForSuccess().
			clickOnProfileIconForSignedUser().
			clickOnMyEarnings();

			String fcmID =  ckUtility.getFCMID(user);

			//Open the Notification bar :
			openNotification();

			//Clear the Notification
			clearNotificationFromNotificationBar(deviceName);

			closeNotification();

			ckUtility.notificationAPICall(fcmID);

			//Open the Notification bar :
			openNotification();

			new NotificationPage(driver, testInfo).
			validateNotificationTitle().
			validateNotificationBody(deviceName).
			clickOnExpand(deviceName).
			validateBigImage(deviceName).
			validateBigText().
			validatelargeIcon(deviceName).
			clickOnBigImage().
			validateAjioStoreRedirection();

		} catch (Exception e) {

			closeNotification();
			reportStep("Test FAILed , unexpectedly :( - close the notification bar if open", "FAIL");

		}



	}

	@Parameters({"deviceName","user"})
	@Test
	public void notificationTest_ForeGround_FromMakeMyLinkPage(String deviceName,String user) throws InterruptedException {

		try {


			SignInPage signInPage = 
					new EKOnboardingPage(driver, testInfo).
					clickOnSignInbutton();

			CashKaroUtility ckUtility = new CashKaroUtility(driver, testInfo);


			signInPage.
			enterUserName(user).
			enterPassword().
			clickSignInButtonForSuccess().
			clickOnProfileIconForSignedUser().
			clickOnMakeProfitLink();

			String fcmID =  ckUtility.getFCMID(user);

			//Open the Notification bar :
			openNotification();

			//Clear the Notification
			clearNotificationFromNotificationBar(deviceName);

			closeNotification();

			ckUtility.notificationAPICall(fcmID);

			//Open the Notification bar :
			openNotification();

			new NotificationPage(driver, testInfo).
			validateNotificationTitle().
			validateNotificationBody(deviceName).
			clickOnExpand(deviceName).
			validateBigImage(deviceName).
			validateBigText().
			validatelargeIcon(deviceName).
			clickOnBigImage().
			validateAjioStoreRedirection();

		} catch (Exception e) {

			closeNotification();
			reportStep("Test FAILed , unexpectedly :( - close the notification bar if open", "FAIL");

		}



	}

	@Parameters({"deviceName","user"})
	@Test
	public void notificationTest_ForeGround_AddtoShareThenIntermediatePage(String deviceName,String user) throws InterruptedException {


		try {
			String shortDescriptionOne = CashKaroUtility.extractValuesFromStoreCategoryJsonAndLoadTheVariablesForStore("CB_Store_One", 0, "CB_Store_One_App_Short_Description");

			SignInPage signInPage = 
					new EKOnboardingPage(driver, testInfo).
					clickOnSignInbutton();

			CashKaroUtility ckUtility = new CashKaroUtility(driver, testInfo);


			signInPage.
			enterUserName(user).
			enterPassword().
			clickSignInButtonForSuccess().
			clickOnHamburgerIcon().
			presenceOfHomeIcon().
			clickRetailerCategory().
			clickAutomationSpecificCategoryInRetailerCategoryMenuUntilAllSubCategoryVisible().
			clickAutomationSpecificSubCategoryOne().
			clickPopularTab().
			clickOnAddToShareButton(shortDescriptionOne);
			String fcmID =  ckUtility.getFCMID(user);

			//Open the Notification bar :
			openNotification();

			//Clear the Notification
			clearNotificationFromNotificationBar(deviceName);

			closeNotification();

			ckUtility.notificationAPICall(fcmID);

			//Open the Notification bar :
			openNotification();

			new NotificationPage(driver, testInfo).
			validateNotificationTitle().
			validateNotificationBody(deviceName).
			clickOnExpand(deviceName).
			validateBigImage(deviceName).
			validateBigText().
			validatelargeIcon(deviceName).
			clickOnBigImage().
			validateAjioStoreRedirection();

		} catch (Exception e) {

			closeNotification();
			reportStep("Test FAILed , unexpectedly :( - close the notification bar if open", "FAIL");

		}



	}

	@Parameters({"deviceName","user"})
	@Test
	public void notificationTest_ForeGround_StoreDetailedPage(String deviceName,String user) throws InterruptedException {

		try {
			String shortDescriptionOne = CashKaroUtility.extractValuesFromStoreCategoryJsonAndLoadTheVariablesForStore("CB_Store_One", 0, "CB_Store_One_App_Short_Description");
			SignInPage signInPage = 
					new EKOnboardingPage(driver, testInfo).
					clickOnSignInbutton();

			CashKaroUtility ckUtility = new CashKaroUtility(driver, testInfo);


			signInPage.
			enterUserName(user).
			enterPassword().
			clickSignInButtonForSuccess().
			clickOnHamburgerIcon().
			presenceOfHomeIcon().
			clickRetailerCategory().
			clickAutomationSpecificCategoryInRetailerCategoryMenuUntilAllSubCategoryVisible().
			clickAutomationSpecificSubCategoryOne().
			clickPopularTab().
			clickStoreCardImage(shortDescriptionOne);
			String fcmID =  ckUtility.getFCMID(user);

			//Open the Notification bar :
			openNotification();

			//Clear the Notification
			clearNotificationFromNotificationBar(deviceName);

			closeNotification();

			ckUtility.notificationAPICall(fcmID);

			//Open the Notification bar :
			openNotification();

			new NotificationPage(driver, testInfo).
			validateNotificationTitle().
			validateNotificationBody(deviceName).
			clickOnExpand(deviceName).
			validateBigImage(deviceName).
			validateBigText().
			validatelargeIcon(deviceName).
			clickOnBigImage().
			validateAjioStoreRedirection();

		} catch (Exception e) {

			closeNotification();
			reportStep("Test FAILed , unexpectedly :( - close the notification bar if open", "FAIL");

		}



	}

	@Parameters({"deviceName","user"})
	@Test
	public void notificationTest_ForeGround_HistoryPage(String deviceName,String user) throws InterruptedException {

		try {

			SignInPage signInPage = 
					new EKOnboardingPage(driver, testInfo).
					clickOnSignInbutton();

			CashKaroUtility ckUtility = new CashKaroUtility(driver, testInfo);


			signInPage.
			enterUserName(user).
			enterPassword().
			clickSignInButtonForSuccess().
			clickOnHistoryIcon();
			String fcmID =  ckUtility.getFCMID(user);

			//Open the Notification bar :
			openNotification();

			//Clear the Notification
			clearNotificationFromNotificationBar(deviceName);

			closeNotification();

			ckUtility.notificationAPICall(fcmID);

			//Open the Notification bar :
			openNotification();

			new NotificationPage(driver, testInfo).
			validateNotificationTitle().
			validateNotificationBody(deviceName).
			clickOnExpand(deviceName).
			validateBigImage(deviceName).
			validateBigText().
			validatelargeIcon(deviceName).
			clickOnBigImage().
			validateAjioStoreRedirection();

		} catch (Exception e) {

			closeNotification();
			reportStep("Test FAILed , unexpectedly :( - close the notification bar if open", "FAIL");

		}



	}

	@Parameters({"deviceName","user"})
	@Test
	public void notificationTest_ForeGround_howEKWorks(String deviceName,String user) throws InterruptedException {

		try {

			SignInPage signInPage = 
					new EKOnboardingPage(driver, testInfo).
					clickOnSignInbutton();

			CashKaroUtility ckUtility = new CashKaroUtility(driver, testInfo);


			signInPage.
			enterUserName(user).
			enterPassword().
			clickSignInButtonForSuccess().
			clickOnProfileIconForSignedUser().
			clickOnHowEarnKaroWorks();

			String fcmID =  ckUtility.getFCMID(user);

			//Open the Notification bar :
			openNotification();

			//Clear the Notification
			clearNotificationFromNotificationBar(deviceName);

			closeNotification();

			ckUtility.notificationAPICall(fcmID);

			//Open the Notification bar :
			openNotification();

			new NotificationPage(driver, testInfo).
			validateNotificationTitle().
			validateNotificationBody(deviceName).
			clickOnExpand(deviceName).
			validateBigImage(deviceName).
			validateBigText().
			validatelargeIcon(deviceName).
			clickOnBigImage().
			validateAjioStoreRedirection();

		} catch (Exception e) {

			closeNotification();
			reportStep("Test FAILed , unexpectedly :( - close the notification bar if open", "FAIL");

		}


	}

	//Without Redirection Page ,Without Big Image , Without Large ICon
	@Parameters({"deviceName","user"})
	@Test
	public void notificationTest_ForeGround_FromHomePage_WithoutRedirectionURL_WithoutLargeIcon_WithoutBigImage(String deviceName,String user) throws InterruptedException {


		SignInPage signInPage = 
				new EKOnboardingPage(driver, testInfo).
				clickOnSignInbutton();

		CashKaroUtility ckUtility = new CashKaroUtility(driver, testInfo);


		signInPage.
		enterUserName(user).
		enterPassword().
		clickSignInButtonForSuccess();

		String fcmID =  ckUtility.getFCMID(user);

		//Open the Notification bar :
		openNotification();

		//Clear the Notification
		clearNotificationFromNotificationBar(deviceName);

		closeNotification();

		ckUtility.notificataionAPICall_WithoutRedirectionURL_WithoutBigImage_WithoutLargeIcon(fcmID);

		//Open the Notification bar :
		openNotification();

		new NotificationPage(driver, testInfo).
		validateNotificationTitle().
		validateBigText().
		clickOnNotificationTitle();

		new HomePage(driver, testInfo);


	}

	@Parameters({"deviceName","user"})
	@Test
	public void notificationTest_ForeGround_FromMyEarningsPage_WithoutRedirectionURL_WithoutLargeIcon_WithoutBigImage(String deviceName,String user) throws InterruptedException {

		try {
			SignInPage signInPage = 
					new EKOnboardingPage(driver, testInfo).
					clickOnSignInbutton();

			CashKaroUtility ckUtility = new CashKaroUtility(driver, testInfo);


			signInPage.
			enterUserName(user).
			enterPassword().
			clickSignInButtonForSuccess().
			clickOnProfileIconForSignedUser().
			clickOnMyEarnings();

			String fcmID =  ckUtility.getFCMID(user);

			//Open the Notification bar :
			openNotification();

			//Clear the Notification
			clearNotificationFromNotificationBar(deviceName);

			closeNotification();

			ckUtility.notificataionAPICall_WithoutRedirectionURL_WithoutBigImage_WithoutLargeIcon(fcmID);

			//Open the Notification bar :
			openNotification();

			new NotificationPage(driver, testInfo).
			validateNotificationTitle().
			validateBigText().
			clickOnNotificationTitle();

			new MyEarningsPage(driver, testInfo);

		} catch (Exception e) {

			closeNotification();
			reportStep("Test FAILed , unexpectedly :( - close the notification bar if open", "FAIL");

		}



	}

	@Parameters({"deviceName","user"})
	@Test
	public void notificationTest_ForeGround_StoreDetailedPage_WithoutRedirectionURL_WithoutLargeIcon_WithoutBigImage(String deviceName,String user) throws InterruptedException {

		try {


			String shortDescriptionOne = CashKaroUtility.extractValuesFromStoreCategoryJsonAndLoadTheVariablesForStore("CB_Store_One", 0, "CB_Store_One_App_Short_Description");
			SignInPage signInPage = 
					new EKOnboardingPage(driver, testInfo).
					clickOnSignInbutton();

			CashKaroUtility ckUtility = new CashKaroUtility(driver, testInfo);


			signInPage.
			enterUserName(user).
			enterPassword().
			clickSignInButtonForSuccess().
			clickOnHamburgerIcon().
			presenceOfHomeIcon().
			clickRetailerCategory().
			clickAutomationSpecificCategoryInRetailerCategoryMenuUntilAllSubCategoryVisible().
			clickAutomationSpecificSubCategoryOne().
			clickPopularTab().
			clickStoreCardImage(shortDescriptionOne);

			String fcmID =  ckUtility.getFCMID(user);

			//Open the Notification bar :
			openNotification();

			//Clear the Notification
			clearNotificationFromNotificationBar(deviceName);

			closeNotification();

			ckUtility.notificataionAPICall_WithoutRedirectionURL_WithoutBigImage_WithoutLargeIcon(fcmID);

			//Open the Notification bar :
			openNotification();

			new NotificationPage(driver, testInfo).
			validateNotificationTitle().
			validateBigText().
			clickOnNotificationTitle();

			new StoreDetailPage(driver, testInfo);

		} catch (Exception e) {

			closeNotification();
			reportStep("Test FAILed , unexpectedly :( - close the notification bar if open", "FAIL");

		}

	}

	//Without Big_Image :
	@Parameters({"deviceName","user"})
	@Test
	public void notificationTest_ForeGround_FromHomePage_WithoutBigImage(String deviceName,String user) throws InterruptedException {

		try {

			SignInPage signInPage = 
					new EKOnboardingPage(driver, testInfo).
					clickOnSignInbutton();

			CashKaroUtility ckUtility = new CashKaroUtility(driver, testInfo);


			signInPage.
			enterUserName(user).
			enterPassword().
			clickSignInButtonForSuccess();

			String fcmID =  ckUtility.getFCMID(user);

			//Open the Notification bar :
			openNotification();

			//Clear the Notification
			clearNotificationFromNotificationBar(deviceName);

			closeNotification();

			ckUtility.notificataionAPICall_WithoutBigImage(fcmID);

			//Open the Notification bar :
			openNotification();

			new NotificationPage(driver, testInfo).
			validateNotificationTitle().
			validateTheAbsenceofBigImage().
			validateBigText().
			validatelargeIcon(deviceName).
			clickOnNotificationTitle().
			validateAjioStoreRedirection();

			new HomePage(driver, testInfo);

		} catch (Exception e) {

			closeNotification();
			reportStep("Test FAILed , unexpectedly :( - close the notification bar if open", "FAIL");

		}



	}

	@Parameters({"deviceName","user"})
	@Test
	public void notificationTest_ForeGround_FromMyEarningsPage_WithoutBigImage(String deviceName,String user) throws InterruptedException {

		try {

			SignInPage signInPage = 
					new EKOnboardingPage(driver, testInfo).
					clickOnSignInbutton();

			CashKaroUtility ckUtility = new CashKaroUtility(driver, testInfo);


			signInPage.
			enterUserName(user).
			enterPassword().
			clickSignInButtonForSuccess().
			clickOnProfileIconForSignedUser().
			clickOnMyEarnings();

			String fcmID =  ckUtility.getFCMID(user);

			//Open the Notification bar :
			openNotification();

			//Clear the Notification
			clearNotificationFromNotificationBar(deviceName);

			closeNotification();

			ckUtility.notificataionAPICall_WithoutBigImage(fcmID);

			//Open the Notification bar :
			openNotification();

			new NotificationPage(driver, testInfo).
			validateNotificationTitle().
			validateTheAbsenceofBigImage().
			validateBigText().
			validatelargeIcon(deviceName).
			clickOnNotificationTitle().
			validateAjioStoreRedirection();

			new HomePage(driver, testInfo);

		} catch (Exception e) {

			closeNotification();
			reportStep("Test FAILed , unexpectedly :( - close the notification bar if open", "FAIL");

		}



	}

	@Parameters({"deviceName","user"})
	@Test
	public void notificationTest_ForeGround_StoreDetailedPage_WithoutBigImage(String deviceName,String user) throws InterruptedException {

		try {


			String shortDescriptionOne = CashKaroUtility.extractValuesFromStoreCategoryJsonAndLoadTheVariablesForStore("CB_Store_One", 0, "CB_Store_One_App_Short_Description");
			SignInPage signInPage = 
					new EKOnboardingPage(driver, testInfo).
					clickOnSignInbutton();

			CashKaroUtility ckUtility = new CashKaroUtility(driver, testInfo);


			signInPage.
			enterUserName(user).
			enterPassword().
			clickSignInButtonForSuccess().
			clickOnHamburgerIcon().
			presenceOfHomeIcon().
			clickRetailerCategory().
			clickAutomationSpecificCategoryInRetailerCategoryMenuUntilAllSubCategoryVisible().
			clickAutomationSpecificSubCategoryOne().
			clickPopularTab().
			clickStoreCardImage(shortDescriptionOne);

			String fcmID =  ckUtility.getFCMID(user);

			//Open the Notification bar :
			openNotification();

			//Clear the Notification
			clearNotificationFromNotificationBar(deviceName);

			closeNotification();

			ckUtility.notificataionAPICall_WithoutBigImage(fcmID);

			//Open the Notification bar :
			openNotification();

			new NotificationPage(driver, testInfo).
			validateNotificationTitle().
			validateTheAbsenceofBigImage().
			validateBigText().
			validatelargeIcon(deviceName).
			clickOnNotificationTitle().
			validateAjioStoreRedirection();

			new HomePage(driver, testInfo);

		} catch (Exception e) {

			closeNotification();
			reportStep("Test FAILed , unexpectedly :( - close the notification bar if open", "FAIL");

		}


	}


	//Exceptional Scenarios :

	@Parameters({"deviceName","user"})
	//@Test
	public void notificationTest_ForeGround_FromHomePage_LogoutThenClickNotificaiton(String deviceName,String user) throws InterruptedException {

		try {
			SignInPage signInPage = 
					new EKOnboardingPage(driver, testInfo).
					clickOnSignInbutton();

			CashKaroUtility ckUtility = new CashKaroUtility(driver, testInfo);


			HomePage objHomePage = signInPage.
					enterUserName(user).
					enterPassword().
					clickSignInButtonForSuccess();

			String fcmID =  ckUtility.getFCMID(user);

			//Open the Notification bar :
			openNotification();

			//Clear the Notification
			clearNotificationFromNotificationBar(deviceName);

			closeNotification();

			ckUtility.notificationAPICall(fcmID);

			objHomePage.
			clickOnProfileIconForSignedUser().
			clickOnLogout();

			//Open the Notification bar :
			openNotification();

			new NotificationPage(driver, testInfo).
			validateNotificationTitle().
			validateNotificationBody(deviceName).
			clickOnExpand(deviceName).
			validateBigImage(deviceName).
			validateBigText().
			validatelargeIcon(deviceName).
			clickOnBigImage();

			new SignInPage(driver, testInfo);

		} catch (Exception e) {

			closeNotification();
			reportStep("Test FAILed , unexpectedly :( - close the notification bar if open", "FAIL");

		}



	}

	@Parameters({"deviceName","user"})
	//@Test
	public void notificationTest_ForeGround_FromMyEarningsPage_LogoutThenClickNotificaiton(String deviceName,String user) throws InterruptedException {

		try {


			SignInPage signInPage = 
					new EKOnboardingPage(driver, testInfo).
					clickOnSignInbutton();

			CashKaroUtility ckUtility = new CashKaroUtility(driver, testInfo);


			MyEarningsPage objMyEarningsPage = 
					signInPage.
					enterUserName(user).
					enterPassword().
					clickSignInButtonForSuccess().
					clickOnProfileIconForSignedUser().
					clickOnMyEarnings();

			String fcmID =  ckUtility.getFCMID(user);

			//Open the Notification bar :
			openNotification();

			//Clear the Notification
			clearNotificationFromNotificationBar(deviceName);

			closeNotification();

			ckUtility.notificationAPICall(fcmID);

			objMyEarningsPage.
			clickBackButton().
			clickOnLogout();


			//Open the Notification bar :
			openNotification();

			new NotificationPage(driver, testInfo).
			validateNotificationTitle().
			validateNotificationBody(deviceName).
			clickOnExpand(deviceName).
			validateBigImage(deviceName).
			validateBigText().
			validatelargeIcon(deviceName).
			clickOnBigImage();

			new SignInPage(driver, testInfo);

		} catch (Exception e) {

			closeNotification();
			reportStep("Test FAILed , unexpectedly :( - close the notification bar if open", "FAIL");

		}



	}

	@Parameters({"deviceName","user"})
	@Test
	public void multipleNotificationRedirectionalValidation(String deviceName,String user) {

		try {

			SignInPage signInPage = 
					new EKOnboardingPage(driver, testInfo).
					clickOnSignInbutton();

			CashKaroUtility ckUtility = new CashKaroUtility(driver, testInfo);


			signInPage.
			enterUserName(user).
			enterPassword().
			clickSignInButtonForSuccess();

			String fcmID =  ckUtility.getFCMID(user);

			//Open the Notification bar :
			openNotification();

			//Clear the Notification
			clearNotificationFromNotificationBar(deviceName);
			closeNotification();
			ckUtility.notificationAPICall(fcmID);
			ckUtility.notifationAPICall_WithRedirectionalURLMyEarningsPage(fcmID);

			//Open the Notification bar :
			openNotification();

			new NotificationPage(driver, testInfo).
			validateNotificationTitle().
			validateNotificationBody(deviceName).
			clickOnExpand(deviceName).
			validateBigImage(deviceName).
			validateBigText().
			validatelargeIcon(deviceName).
			clickOnBigImage();

			new MyEarningsPage(driver, testInfo);

			new NotificationPage(driver, testInfo).
			validateNotificationTitle().
			validateNotificationBody(deviceName).
			clickOnExpand(deviceName).
			validateBigImage(deviceName).
			validateBigText().
			validatelargeIcon(deviceName).
			clickOnBigImage().
			validateAjioStoreRedirection();

			ckUtility.notifationAPICall_WithRedirectionalURLMyEarningsPage(fcmID);

			//Open the Notification bar :
			openNotification();

			new NotificationPage(driver, testInfo).
			validateNotificationTitle().
			validateNotificationBody(deviceName).
			clickOnExpand(deviceName).
			validateBigImage(deviceName).
			validateBigText().
			validatelargeIcon(deviceName).
			clickOnBigImage();

			new MyEarningsPage(driver, testInfo);

		} catch (Exception e) {

			closeNotification();
			reportStep("Test FAILed , unexpectedly :( - close the notification bar if open", "FAIL");

		}



	}

	@Parameters({"deviceName","user"})
	@Test
	public void notificationTest_ForeGround_FromHomePage_AppClosed(String deviceName,String user) throws InterruptedException {

		try {

			SignInPage signInPage = 
					new EKOnboardingPage(driver, testInfo).
					clickOnSignInbutton();

			CashKaroUtility ckUtility = new CashKaroUtility(driver, testInfo);


			signInPage.
			enterUserName(user).
			enterPassword().
			clickSignInButtonForSuccess();

			String fcmID =  ckUtility.getFCMID(user);

			//Open the Notification bar :
			openNotification();

			//Clear the Notification
			clearNotificationFromNotificationBar(deviceName);

			closeNotification();

			//Close the EarnKaro App :
			toggleApp();
			closeEarnKaroApp(deviceName);
			clickInApp();

			ckUtility.notificationAPICall(fcmID);

			//Open the Notification bar :
			openNotification();

			new NotificationPage(driver, testInfo).
			validateNotificationTitle().
			validateNotificationBody(deviceName).
			clickOnExpand(deviceName).
			validateBigImage(deviceName).
			validateBigText().
			validatelargeIcon(deviceName).
			clickOnBigImage().
			validateAjioStoreRedirection();

		} catch (Exception e) {

			closeNotification();
			reportStep("Test FAILed , unexpectedly :( - close the notification bar if open", "FAIL");

		}



	}

	@Parameters({"deviceName","user"})
	@Test
	public void notificationTest_ForeGround_FromMyEarningsPage_AppClosed(String deviceName,String user) throws InterruptedException {

		try {

			SignInPage signInPage = 
					new EKOnboardingPage(driver, testInfo).
					clickOnSignInbutton();

			CashKaroUtility ckUtility = new CashKaroUtility(driver, testInfo);


			signInPage.
			enterUserName(user).
			enterPassword().
			clickSignInButtonForSuccess().
			clickOnProfileIconForSignedUser().
			clickOnMyEarnings();

			String fcmID =  ckUtility.getFCMID(user);

			//Open the Notification bar :
			openNotification();

			//Clear the Notification
			clearNotificationFromNotificationBar(deviceName);

			closeNotification();

			//Close the EarnKaro App :
			toggleApp();
			closeEarnKaroApp(deviceName);
			clickInApp();

			ckUtility.notifationAPICall_WithRedirectionalURLMyEarningsPage(fcmID);

			//Open the Notification bar :
			openNotification();

			new NotificationPage(driver, testInfo).
			validateNotificationTitle().
			validateNotificationBody(deviceName).
			clickOnExpand(deviceName).
			validateBigImage(deviceName).
			validateBigText().
			validatelargeIcon(deviceName).
			clickOnBigImage();

			new MyEarningsPage(driver, testInfo);

		} catch (Exception e) {

			closeNotification();
			reportStep("Test FAILed , unexpectedly :( - close the notification bar if open", "FAIL");

		}



	}

	/*******************************************************************/
	/*********** Fore Ground Notification  - Normal priority**********/
	/*******************************************************************/

	@Parameters({"deviceName","user"})
	@Test
	public void notificationTest_ForeGround_FromHomePage_NormalPriority(String deviceName,String user) throws InterruptedException {

		try { 

			SignInPage signInPage = 
					new EKOnboardingPage(driver, testInfo).
					clickOnSignInbutton();

			CashKaroUtility ckUtility = new CashKaroUtility(driver, testInfo);
			signInPage.
			enterUserName(user).
			enterPassword().
			clickSignInButtonForSuccess();

			String fcmID =  ckUtility.getFCMID(user);

			//Open the Notification bar :
			openNotification();

			//Clear the Notification
			clearNotificationFromNotificationBar(deviceName);

			closeNotification();

			ckUtility.notifationAPICall_NormalPriority(fcmID);

			//Open the Notification bar :
			openNotification();

			new NotificationPage(driver, testInfo).
			validateNotificationTitle().
			validateNotificationBody(deviceName).
			clickOnExpand(deviceName).
			validateBigImage(deviceName).
			validateBigText().
			validatelargeIcon(deviceName).
			clickOnBigImage().
			validateAjioStoreRedirection();

		} catch (Exception e) {

			closeNotification();
			reportStep("Test FAILed , unexpectedly :( - close the notification bar if open", "FAIL");

		}



	}

	@Parameters({"deviceName","user"})
	@Test
	public void notificationTest_ForeGround_FromMyEarningsPage_NormalPriority(String deviceName,String user) throws InterruptedException {

		try {

			SignInPage signInPage = 
					new EKOnboardingPage(driver, testInfo).
					clickOnSignInbutton();

			CashKaroUtility ckUtility = new CashKaroUtility(driver, testInfo);


			signInPage.
			enterUserName(user).
			enterPassword().
			clickSignInButtonForSuccess().
			clickOnProfileIconForSignedUser().
			clickOnMyEarnings();

			String fcmID =  ckUtility.getFCMID(user);

			//Open the Notification bar :
			openNotification();

			//Clear the Notification
			clearNotificationFromNotificationBar(deviceName);

			closeNotification();

			ckUtility.notifationAPICall_NormalPriority(fcmID);

			//Open the Notification bar :
			openNotification();

			new NotificationPage(driver, testInfo).
			validateNotificationTitle().
			validateNotificationBody(deviceName).
			clickOnExpand(deviceName).
			validateBigImage(deviceName).
			validateBigText().
			validatelargeIcon(deviceName).
			clickOnBigImage().
			validateAjioStoreRedirection();

		} catch (Exception e) {

			closeNotification();
			reportStep("Test FAILed , unexpectedly :( - close the notification bar if open", "FAIL");

		}



	}

	@Parameters({"deviceName","user"})
	@Test
	public void notificationTest_ForeGround_StoreDetailedPage_NormalPriority(String deviceName,String user) throws InterruptedException {

		try {
			String shortDescriptionOne = CashKaroUtility.extractValuesFromStoreCategoryJsonAndLoadTheVariablesForStore("CB_Store_One", 0, "CB_Store_One_App_Short_Description");
			SignInPage signInPage = 
					new EKOnboardingPage(driver, testInfo).
					clickOnSignInbutton();

			CashKaroUtility ckUtility = new CashKaroUtility(driver, testInfo);


			signInPage.
			enterUserName(user).
			enterPassword().
			clickSignInButtonForSuccess().
			clickOnHamburgerIcon().
			presenceOfHomeIcon().
			clickRetailerCategory().
			clickAutomationSpecificCategoryInRetailerCategoryMenuUntilAllSubCategoryVisible().
			clickAutomationSpecificSubCategoryOne().
			clickPopularTab().
			clickStoreCardImage(shortDescriptionOne);

			String fcmID =  ckUtility.getFCMID(user);

			//Open the Notification bar :
			openNotification();

			//Clear the Notification
			clearNotificationFromNotificationBar(deviceName);

			closeNotification();

			ckUtility.notifationAPICall_NormalPriority(fcmID);

			//Open the Notification bar :
			openNotification();

			new NotificationPage(driver, testInfo).
			validateNotificationTitle().
			validateNotificationBody(deviceName).
			clickOnExpand(deviceName).
			validateBigImage(deviceName).
			validateBigText().
			validatelargeIcon(deviceName).
			clickOnBigImage().
			validateAjioStoreRedirection();

		} catch (Exception e) {

			closeNotification();
			reportStep("Test FAILed , unexpectedly :( - close the notification bar if open", "FAIL");

		}



	}

	//Without Redirection Page ,Without Big Image , Without Large ICon
	@Parameters({"deviceName","user"})
	@Test
	public void notificationTest_ForeGround_FromHomePage_WithoutRedirectionURL_WithoutLargeIcon_WithoutBigImage_NormalPriority(String deviceName,String user) throws InterruptedException {

		try {


			SignInPage signInPage = 
					new EKOnboardingPage(driver, testInfo).
					clickOnSignInbutton();

			CashKaroUtility ckUtility = new CashKaroUtility(driver, testInfo);

			signInPage.
			enterUserName(user).
			enterPassword().
			clickSignInButtonForSuccess();

			String fcmID =  ckUtility.getFCMID(user);

			//Open the Notification bar :
			openNotification();

			//Clear the Notification
			clearNotificationFromNotificationBar(deviceName);

			closeNotification();

			ckUtility.notificataionAPICall_WithoutRedirectionURL_WithoutBigImage_WithoutLargeIcon_NormalPriority(fcmID);

			//Open the Notification bar :
			openNotification();

			new NotificationPage(driver, testInfo).
			validateNotificationTitle().
			validateBigText().
			clickOnNotificationTitle();

			new HomePage(driver, testInfo);

		} catch (Exception e) {

			closeNotification();
			reportStep("Test FAILed , unexpectedly :( - close the notification bar if open", "FAIL");

		}



	}

	@Parameters({"deviceName","user"})
	@Test
	public void notificationTest_ForeGround_FromMyEarningsPage_WithoutRedirectionURL_WithoutLargeIcon_WithoutBigImage_NormalPriority(String deviceName,String user) throws InterruptedException {

		try {

			SignInPage signInPage = 
					new EKOnboardingPage(driver, testInfo).
					clickOnSignInbutton();

			CashKaroUtility ckUtility = new CashKaroUtility(driver, testInfo);


			signInPage.
			enterUserName(user).
			enterPassword().
			clickSignInButtonForSuccess().
			clickOnProfileIconForSignedUser().
			clickOnMyEarnings();

			String fcmID =  ckUtility.getFCMID(user);

			//Open the Notification bar :
			openNotification();

			//Clear the Notification
			clearNotificationFromNotificationBar(deviceName);

			closeNotification();

			ckUtility.notificataionAPICall_WithoutRedirectionURL_WithoutBigImage_WithoutLargeIcon_NormalPriority(fcmID);

			//Open the Notification bar :
			openNotification();

			new NotificationPage(driver, testInfo).
			validateNotificationTitle().
			validateBigText().
			clickOnNotificationTitle();

			new MyEarningsPage(driver, testInfo);

		} catch (Exception e) {

			closeNotification();
			reportStep("Test FAILed , unexpectedly :( - close the notification bar if open", "FAIL");

		}



	}

	@Parameters({"deviceName","user"})
	@Test
	public void notificationTest_ForeGround_StoreDetailedPage_WithoutRedirectionURL_WithoutLargeIcon_WithoutBigImage_NormalPriority(String deviceName,String user) throws InterruptedException {

		try {

			String shortDescriptionOne = CashKaroUtility.extractValuesFromStoreCategoryJsonAndLoadTheVariablesForStore("CB_Store_One", 0, "CB_Store_One_App_Short_Description");
			SignInPage signInPage = 
					new EKOnboardingPage(driver, testInfo).
					clickOnSignInbutton();

			CashKaroUtility ckUtility = new CashKaroUtility(driver, testInfo);


			signInPage.
			enterUserName(user).
			enterPassword().
			clickSignInButtonForSuccess().
			clickOnHamburgerIcon().
			presenceOfHomeIcon().
			clickRetailerCategory().
			clickAutomationSpecificCategoryInRetailerCategoryMenuUntilAllSubCategoryVisible().
			clickAutomationSpecificSubCategoryOne().
			clickPopularTab().
			clickStoreCardImage(shortDescriptionOne);

			String fcmID =  ckUtility.getFCMID(user);

			//Open the Notification bar :
			openNotification();

			//Clear the Notification
			clearNotificationFromNotificationBar(deviceName);

			closeNotification();

			ckUtility.notificataionAPICall_WithoutRedirectionURL_WithoutBigImage_WithoutLargeIcon_NormalPriority(fcmID);

			//Open the Notification bar :
			openNotification();

			new NotificationPage(driver, testInfo).
			validateNotificationTitle().
			validateBigText().
			clickOnNotificationTitle();

			new StoreDetailPage(driver, testInfo);

		} catch (Exception e) {

			closeNotification();
			reportStep("Test FAILed , unexpectedly :( - close the notification bar if open", "FAIL");

		}


	}

	//Without Big_Image :
	@Parameters({"deviceName","user"})
	@Test
	public void notificationTest_ForeGround_FromHomePage_WithoutBigImage_NormalPriority(String deviceName,String user) throws InterruptedException {

		try {
			SignInPage signInPage = 
					new EKOnboardingPage(driver, testInfo).
					clickOnSignInbutton();

			CashKaroUtility ckUtility = new CashKaroUtility(driver, testInfo);


			signInPage.
			enterUserName(user).
			enterPassword().
			clickSignInButtonForSuccess();

			String fcmID =  ckUtility.getFCMID(user);

			//Open the Notification bar :
			openNotification();

			//Clear the Notification
			clearNotificationFromNotificationBar(deviceName);

			closeNotification();

			ckUtility.notificataionAPICall_WithoutBigImage_NormalPriority(fcmID);

			//Open the Notification bar :
			openNotification();

			new NotificationPage(driver, testInfo).
			validateNotificationTitle().
			validateTheAbsenceofBigImage().
			validateBigText().
			validatelargeIcon(deviceName).
			clickOnNotificationTitle().
			validateAjioStoreRedirection();

			new HomePage(driver, testInfo);

		} catch (Exception e) {

			closeNotification();
			reportStep("Test FAILed , unexpectedly :( - close the notification bar if open", "FAIL");

		}



	}

	@Parameters({"deviceName","user"})
	@Test
	public void notificationTest_ForeGround_FromMyEarningsPage_WithoutBigImage_NormalPriority(String deviceName,String user) throws InterruptedException {

		try {


			SignInPage signInPage = 
					new EKOnboardingPage(driver, testInfo).
					clickOnSignInbutton();

			CashKaroUtility ckUtility = new CashKaroUtility(driver, testInfo);


			signInPage.
			enterUserName(user).
			enterPassword().
			clickSignInButtonForSuccess().
			clickOnProfileIconForSignedUser().
			clickOnMyEarnings();

			String fcmID =  ckUtility.getFCMID(user);

			//Open the Notification bar :
			openNotification();

			//Clear the Notification
			clearNotificationFromNotificationBar(deviceName);

			closeNotification();

			ckUtility.notificataionAPICall_WithoutBigImage_NormalPriority(fcmID);

			//Open the Notification bar :
			openNotification();

			new NotificationPage(driver, testInfo).
			validateNotificationTitle().
			validateTheAbsenceofBigImage().
			validateBigText().
			validatelargeIcon(deviceName).
			clickOnNotificationTitle().
			validateAjioStoreRedirection();

			new HomePage(driver, testInfo);

		} catch (Exception e) {

			closeNotification();
			reportStep("Test FAILed , unexpectedly :( - close the notification bar if open", "FAIL");

		}



	}

	@Parameters({"deviceName","user"})
	@Test
	public void notificationTest_ForeGround_StoreDetailedPage_WithoutBigImage_NormalPriority(String deviceName,String user) throws InterruptedException {

		try { 
			String shortDescriptionOne = CashKaroUtility.extractValuesFromStoreCategoryJsonAndLoadTheVariablesForStore("CB_Store_One", 0, "CB_Store_One_App_Short_Description");
			SignInPage signInPage = 
					new EKOnboardingPage(driver, testInfo).
					clickOnSignInbutton();

			CashKaroUtility ckUtility = new CashKaroUtility(driver, testInfo);


			signInPage.
			enterUserName(user).
			enterPassword().
			clickSignInButtonForSuccess().
			clickOnHamburgerIcon().
			presenceOfHomeIcon().
			clickRetailerCategory().
			clickAutomationSpecificCategoryInRetailerCategoryMenuUntilAllSubCategoryVisible().
			clickAutomationSpecificSubCategoryOne().
			clickPopularTab().
			clickStoreCardImage(shortDescriptionOne);

			String fcmID =  ckUtility.getFCMID(user);

			//Open the Notification bar :
			openNotification();

			//Clear the Notification
			clearNotificationFromNotificationBar(deviceName);

			closeNotification();

			ckUtility.notificataionAPICall_WithoutBigImage_NormalPriority(fcmID);

			//Open the Notification bar :
			openNotification();

			new NotificationPage(driver, testInfo).
			validateNotificationTitle().
			validateTheAbsenceofBigImage().
			validateBigText().
			validatelargeIcon(deviceName).
			clickOnNotificationTitle().
			validateAjioStoreRedirection();

			new HomePage(driver, testInfo);

		} catch (Exception e) {

			closeNotification();
			reportStep("Test FAILed , unexpectedly :( - close the notification bar if open", "FAIL");

		}



	}

	//Exceptional Scenarios :

	@Parameters({"deviceName","user"})
	//@Test
	public void notificationTest_ForeGround_FromHomePage_LogoutThenClickNotificaiton_NormalPriority(String deviceName,String user) throws InterruptedException {

		try {

			SignInPage signInPage = 
					new EKOnboardingPage(driver, testInfo).
					clickOnSignInbutton();

			CashKaroUtility ckUtility = new CashKaroUtility(driver, testInfo);


			HomePage objHomePage = signInPage.
					enterUserName(user).
					enterPassword().
					clickSignInButtonForSuccess();

			String fcmID =  ckUtility.getFCMID(user);

			//Open the Notification bar :
			openNotification();

			//Clear the Notification
			clearNotificationFromNotificationBar(deviceName);

			closeNotification();

			ckUtility.notifationAPICall_NormalPriority(fcmID);

			objHomePage.
			clickOnProfileIconForSignedUser().
			clickOnLogout();

			//Open the Notification bar :
			openNotification();

			new NotificationPage(driver, testInfo).
			validateNotificationTitle().
			validateNotificationBody(deviceName).
			clickOnExpand(deviceName).
			validateBigImage(deviceName).
			validateBigText().
			validatelargeIcon(deviceName).
			clickOnBigImage();

			new SignInPage(driver, testInfo);

		}catch (Exception e) {

			closeNotification();
			reportStep("Test FAILed , unexpectedly :( - close the notification bar if open", "FAIL");

		}



	}

	@Parameters({"deviceName","user"})
	//@Test
	public void notificationTest_ForeGround_FromMyEarningsPage_LogoutThenClickNotificaiton_NormalPriority(String deviceName,String user) throws InterruptedException {

		try {
			SignInPage signInPage = 
					new EKOnboardingPage(driver, testInfo).
					clickOnSignInbutton();
			CashKaroUtility ckUtility = new CashKaroUtility(driver, testInfo);


			MyEarningsPage objMyEarningsPage = 
					signInPage.
					enterUserName(user).
					enterPassword().
					clickSignInButtonForSuccess().
					clickOnProfileIconForSignedUser().
					clickOnMyEarnings();

			String fcmID =  ckUtility.getFCMID(user);

			//Open the Notification bar :
			openNotification();

			//Clear the Notification
			clearNotificationFromNotificationBar(deviceName);

			closeNotification();

			ckUtility.notifationAPICall_NormalPriority(fcmID);

			objMyEarningsPage.
			clickOnProfileIconForSignedUser().
			clickOnLogout();


			//Open the Notification bar :
			openNotification();

			new NotificationPage(driver, testInfo).
			validateNotificationTitle().
			validateNotificationBody(deviceName).
			clickOnExpand(deviceName).
			validateBigImage(deviceName).
			validateBigText().
			validatelargeIcon(deviceName).
			clickOnBigImage();

			new SignInPage(driver, testInfo);

		} catch (Exception e) {

			System.out.println(e.getMessage());
			closeNotification();
			reportStep("Test FAILed , unexpectedly :( - close the notification bar if open", "FAIL");

		}



	}

	@Parameters({"deviceName","user"})
	@Test
	public void multipleNotificationRedirectionalValidation_NormalPriority(String deviceName,String user) {

		try {

			SignInPage signInPage = 
					new EKOnboardingPage(driver, testInfo).
					clickOnSignInbutton();

			CashKaroUtility ckUtility = new CashKaroUtility(driver, testInfo);


			signInPage.
			enterUserName(user).
			enterPassword().
			clickSignInButtonForSuccess();

			String fcmID =  ckUtility.getFCMID(user);

			//Open the Notification bar :
			openNotification();

			//Clear the Notification
			clearNotificationFromNotificationBar(deviceName);
			closeNotification();
			ckUtility.notifationAPICall_NormalPriority(fcmID);
			ckUtility.notifationAPICall_WithRedirectionalURLMyEarningsPage_NormalPriority(fcmID);

			//Open the Notification bar :
			openNotification();

			new NotificationPage(driver, testInfo).
			validateNotificationTitle().
			validateNotificationBody(deviceName).
			clickOnExpand(deviceName).
			validateBigImage(deviceName).
			validateBigText().
			validatelargeIcon(deviceName).
			clickOnBigImage();

			new MyEarningsPage(driver, testInfo);

			ckUtility.notifationAPICall_NormalPriority(fcmID);
			//Open the Notification bar :
			openNotification();

			new NotificationPage(driver, testInfo).
			validateNotificationTitle().
			validateNotificationBody(deviceName).
			clickOnExpand(deviceName).
			validateBigImage(deviceName).
			validateBigText().
			validatelargeIcon(deviceName).
			clickOnBigImage().
			validateAjioStoreRedirection();

			ckUtility.notifationAPICall_WithRedirectionalURLMyEarningsPage(fcmID);

			//Open the Notification bar :
			openNotification();

			new NotificationPage(driver, testInfo).
			validateNotificationTitle().
			validateNotificationBody(deviceName).
			clickOnExpand(deviceName).
			validateBigImage(deviceName).
			validateBigText().
			validatelargeIcon(deviceName).
			clickOnBigImage();

			new MyEarningsPage(driver, testInfo);

		} catch (Exception e) {

			closeNotification();
			reportStep("Test FAILed , unexpectedly :( - close the notification bar if open", "FAIL");

		}



	}

	@Parameters({"deviceName","user"})
	@Test
	public void notificationTest_ForeGround_FromHomePage_AppClosed_NormalPriority(String deviceName,String user) throws InterruptedException {

		try {

			SignInPage signInPage = 
					new EKOnboardingPage(driver, testInfo).
					clickOnSignInbutton();

			CashKaroUtility ckUtility = new CashKaroUtility(driver, testInfo);


			signInPage.
			enterUserName(user).
			enterPassword().
			clickSignInButtonForSuccess();

			String fcmID =  ckUtility.getFCMID(user);

			//Open the Notification bar :
			openNotification();

			//Clear the Notification
			clearNotificationFromNotificationBar(deviceName);

			closeNotification();

			//Close the EarnKaro App :
			toggleApp();
			closeEarnKaroApp(deviceName);
			clickInApp();



			ckUtility.notifationAPICall_NormalPriority(fcmID);

			//Open the Notification bar :
			openNotification();

			new NotificationPage(driver, testInfo).
			validateNotificationTitle().
			validateNotificationBody(deviceName).
			clickOnExpand(deviceName).
			validateBigImage(deviceName).
			validateBigText().
			validatelargeIcon(deviceName).
			clickOnBigImage().
			validateAjioStoreRedirection();

		} catch (Exception e) {

			closeNotification();
			reportStep("Test FAILed , unexpectedly :( - close the notification bar if open", "FAIL");

		}



	}

	@Parameters({"deviceName","user"})
	@Test
	public void notificationTest_ForeGround_FromMyEarningsPage_AppClosed_NormalPriority(String deviceName,String user) throws InterruptedException {

		try {
			SignInPage signInPage = 
					new EKOnboardingPage(driver, testInfo).
					clickOnSignInbutton();

			CashKaroUtility ckUtility = new CashKaroUtility(driver, testInfo);


			signInPage.
			enterUserName(user).
			enterPassword().
			clickSignInButtonForSuccess().
			clickOnProfileIconForSignedUser().
			clickOnMyEarnings();

			String fcmID =  ckUtility.getFCMID(user);

			//Open the Notification bar :
			openNotification();

			//Clear the Notification
			clearNotificationFromNotificationBar(deviceName);

			closeNotification();

			//Close the EarnKaro App :
			toggleApp();
			closeEarnKaroApp(deviceName);
			clickInApp();

			ckUtility.notifationAPICall_WithRedirectionalURLMyEarningsPage_NormalPriority(fcmID);

			//Open the Notification bar :
			openNotification();

			new NotificationPage(driver, testInfo).
			validateNotificationTitle().
			validateNotificationBody(deviceName).
			clickOnExpand(deviceName).
			validateBigImage(deviceName).
			validateBigText().
			validatelargeIcon(deviceName).
			clickOnBigImage();

			new MyEarningsPage(driver, testInfo);

		} catch (Exception e) {

			closeNotification();
			reportStep("Test FAILed , unexpectedly :( - close the notification bar if open", "FAIL");

		}


	}

	/*******************************************************************/
	/*********** WithTemplate  - Fore Ground **********/
	/*******************************************************************/
	//Positive flow :
	@Parameters({"deviceName","user"})
	@Test
	public void notificaitonForeGround_WithTemplate_HighPriority_TemplateId257(String deviceName,String user) {

		try {

			SignInPage signInPage = 
					new EKOnboardingPage(driver, testInfo).
					clickOnSignInbutton();

			CashKaroUtility ckUtility = new CashKaroUtility(driver, testInfo);


			signInPage.enterUserName(user).
			enterPassword().
			clickSignInButtonForSuccess();

			String fcmID =  ckUtility.getFCMID(user);

			//Open the Notification bar :
			openNotification();

			//Clear the Notification
			clearNotificationFromNotificationBar(deviceName);
			closeNotification();

			ckUtility.notifationAPICall_WithTemplate(getTestData(17, "HighPriorityNotificaitonTemplateID"),fcmID);

			//Open the Notification bar :
			openNotification();

			new NotificationPage(driver, testInfo).
			validateNotificationTitle_WithTemplateHighPriority().
			validateNotificationBody(deviceName).
			clickOnExpand(deviceName).
			validateBigImage(deviceName).
			validateBigText().
			validatelargeIcon(deviceName).
			clickOnBigImage();

			new  MyEarningsPage(driver, testInfo);


		}catch (Exception e) {

			closeNotification();
			reportStep("Test FAILed , unexpectedly :(", "FAIL");

		}

	}
	//Positive flow :
	@Parameters({"deviceName","user"})
	@Test
	public void notificaitonForeGround_WithTemplate_HighPriority_TemplateId257_MutipleNotification(String deviceName,String user) {

		try {

			SignInPage signInPage = 
					new EKOnboardingPage(driver, testInfo).
					clickOnSignInbutton();

			CashKaroUtility ckUtility = new CashKaroUtility(driver, testInfo);


			signInPage.enterUserName(user).
			enterPassword().
			clickSignInButtonForSuccess();

			String fcmID =  ckUtility.getFCMID(user);

			//Open the Notification bar :
			openNotification();

			//Clear the Notification
			clearNotificationFromNotificationBar(deviceName);
			closeNotification();

			ckUtility.notifationAPICall_WithTemplate(getTestData(17, "HighPriorityNotificaitonTemplateID"),fcmID);
			ckUtility.notifationAPICall_WithTemplate(getTestData(17, "HighPriorityNotificaitonTemplateID"),fcmID);

			//Open the Notification bar :
			openNotification();

			new NotificationPage(driver, testInfo).
			validateNotificationTitle_WithTemplateHighPriority().
			validateNotificationBody(deviceName).
			clickOnExpand(deviceName).
			validateBigImage(deviceName).
			validateBigText().
			validatelargeIcon(deviceName).
			clickOnBigImage();

			new  MyEarningsPage(driver, testInfo);

			//Open the Notification bar :
			openNotification();

			new NotificationPage(driver, testInfo).
			validateNotificationTitle_WithTemplateHighPriority().
			validateNotificationBody(deviceName).
			clickOnExpand(deviceName).
			validateBigImage(deviceName).
			validateBigText().
			validatelargeIcon(deviceName).
			clickOnBigImage();

			new  MyEarningsPage(driver, testInfo);



		}catch (Exception e) {

			closeNotification();
			reportStep("Test FAILed , unexpectedly :(", "FAIL");

		}

	}
	//Positive flow :
	@Parameters({"deviceName","user"})
	@Test
	public void notificaitonForeGround_WithTemplate_HighPriority_TemplateId257_WithoutRedirectURL(String deviceName,String user) {

		try {

			SignInPage signInPage = 
					new EKOnboardingPage(driver, testInfo).
					clickOnSignInbutton();

			CashKaroUtility ckUtility = new CashKaroUtility(driver, testInfo);


			signInPage.enterUserName(user).
			enterPassword().
			clickSignInButtonForSuccess();

			String fcmID =  ckUtility.getFCMID(user);

			//Open the Notification bar :
			openNotification();

			//Clear the Notification
			clearNotificationFromNotificationBar(deviceName);
			closeNotification();

			ckUtility.notifationAPICall_WithTemplate(getTestData(17, "HighPriorityWithoutRedirectURLTemplateID"),fcmID);

			//Open the Notification bar :
			openNotification();


			new NotificationPage(driver, testInfo).
			validateNotificationTitle_WithTemplateHighPriority_WithoutRedirectURL().
			validateNotificationBody(deviceName).
			clickOnExpand(deviceName).
			validateBigImage(deviceName).
			validateBigText().
			validatelargeIcon(deviceName).
			clickOnBigImage();

			new HomePage(driver, testInfo);



		}catch (Exception e) {

			closeNotification();
			reportStep("Test FAILed , unexpectedly :(", "FAIL");

		}

	}
	//Positive flow :
	@Parameters({"deviceName","user"})
	@Test
	public void notificaitonForeGround_WithTemplate_NormalPriority_TemplateId258(String deviceName,String user) {

		try {

			SignInPage signInPage = 
					new EKOnboardingPage(driver, testInfo).
					clickOnSignInbutton();

			CashKaroUtility ckUtility = new CashKaroUtility(driver, testInfo);


			signInPage.enterUserName(user).
			enterPassword().
			clickSignInButtonForSuccess();

			String fcmID =  ckUtility.getFCMID(user);

			//Open the Notification bar :
			openNotification();

			//Clear the Notification
			clearNotificationFromNotificationBar(deviceName);
			closeNotification();

			ckUtility.notifationAPICall_WithTemplate(getTestData(17, "NormalPriorityNotificationTemplateID"),fcmID);

			//Open the Notification bar :
			openNotification();

			new NotificationPage(driver, testInfo).
			validateNotificationTitle_WithTemplateNormalPriority().
			validateNotificationBody(deviceName).
			clickOnExpand(deviceName).
			validateBigImage(deviceName).
			validateBigText().
			validatelargeIcon(deviceName).
			clickOnBigImage().
			validateNotificationTestStorePage();


		}catch (Exception e) {

			closeNotification();
			reportStep("Test FAILed , unexpectedly :(", "FAIL");

		}

	}
	//Positive flow :
	@Parameters({"deviceName","user"})
	@Test
	public void notificaitonForeGround_WithTemplate_NormalPriority_TemplateId258_MultipleNotificaition(String deviceName,String user) {

		try {

			SignInPage signInPage = 
					new EKOnboardingPage(driver, testInfo).
					clickOnSignInbutton();

			CashKaroUtility ckUtility = new CashKaroUtility(driver, testInfo);


			signInPage.enterUserName(user).
			enterPassword().
			clickSignInButtonForSuccess();

			String fcmID =  ckUtility.getFCMID(user);

			//Open the Notification bar :
			openNotification();

			//Clear the Notification
			clearNotificationFromNotificationBar(deviceName);
			closeNotification();

			ckUtility.notifationAPICall_WithTemplate(getTestData(17, "NormalPriorityNotificationTemplateID"),fcmID);
			ckUtility.notifationAPICall_WithTemplate(getTestData(17, "NormalPriorityNotificationTemplateID"),fcmID);

			//Open the Notification bar :
			openNotification();

			new NotificationPage(driver, testInfo).
			validateNotificationTitle_WithTemplateNormalPriority().
			validateNotificationBody(deviceName).
			clickOnExpand(deviceName).
			validateBigImage(deviceName).
			validateBigText().
			validatelargeIcon(deviceName).
			clickOnBigImage().
			validateNotificationTestStorePage();

			//Open the Notification bar :
			openNotification();

			new NotificationPage(driver, testInfo).
			validateNotificationTitle_WithTemplateNormalPriority().
			validateNotificationBody(deviceName).
			clickOnExpand(deviceName).
			validateBigImage(deviceName).
			validateBigText().
			validatelargeIcon(deviceName).
			clickOnBigImage().
			validateNotificationTestStorePage();



		}catch (Exception e) {

			closeNotification();
			reportStep("Test FAILed , unexpectedly :(", "FAIL");

		}

	}



}
