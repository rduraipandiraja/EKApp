package com.app.ck.test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import java.util.Set;
import java.util.TimeZone;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;

import org.apache.commons.collections.set.SynchronizedSortedSet;
import org.codehaus.groovy.runtime.dgmimpl.arrays.IntegerArrayGetAtMetaMethod;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.SkipException;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.app.ck.base.Base;
import com.app.ck.base.ExtractLanguage;
import com.app.ck.base.GetCodeSetUP;
import com.app.ck.base.MakeLinkURLS;
import com.app.ck.base.WrapperMethods;
import com.app.ck.pages.AccountSettingsPage;
import com.app.ck.pages.AskAQuestionPage;
import com.app.ck.pages.DeepLinkPage;
import com.app.ck.pages.FAQPage;
import com.app.ck.pages.ForgotPasswordPage;
import com.app.ck.pages.EKOnboardingPage;
import com.app.ck.pages.HistoryPage;
import com.app.ck.pages.HomePage;
import com.app.ck.pages.HowItWorks;
import com.app.ck.pages.IntermediateRetailerPage;
import com.app.ck.pages.JoinFreePage;
import com.app.ck.pages.MakeProfitLink;
import com.app.ck.pages.MyEarningsPage;
import com.app.ck.pages.MyReferral;
import com.app.ck.pages.NotificationPage;
import com.app.ck.pages.OTPPage;
import com.app.ck.pages.PaymentOTPPage;
import com.app.ck.pages.PersonalDetailsIntermediatePage;
import com.app.ck.pages.ProductCategoryPage;
import com.app.ck.pages.ProductPage;
import com.app.ck.pages.ReferAndEarnLifeTime;
import com.app.ck.pages.ReferralNetworkPage;
import com.app.ck.pages.SignInPage;
import com.app.ck.pages.SignedInProfilePage;
import com.app.ck.pages.SliderScreenPage;
import com.app.ck.pages.StoreCategoryPage;
import com.app.ck.pages.StoreDetailPage;
import com.app.ck.pages.StorePage;
import com.app.ck.pages.SuccessStories;
import com.app.ck.pages.TermsAndConditionPage;
import com.app.ck.pages.TestimonialsPage;
import com.app.ck.pages.WhatsAppUtility;
import com.app.ck.pages.PartnersDetailPage;
import com.app.ck.pages.admin.AdminBonusCreditPage;
import com.app.ck.pages.admin.AdminCashbackPage;
import com.app.ck.pages.admin.AdminCoreFunction;
import com.app.ck.pages.admin.AdminMissingCashback;
import com.app.ck.pages.admin.AdminPartnerSettingsPage;
import com.app.ck.pages.admin.AdminPaymentSettingsPage;
import com.app.ck.pages.admin.AdminPendingCashoutsPage;
import com.app.ck.pages.admin.AdminReportsPage;
import com.app.ck.pages.admin.AdminStoresPage;
import com.app.ck.pages.admin.AdminUsersPage;
import com.app.ck.pages.admin.AdminVoucherPage;
import com.app.ck.pages.admin.EmailMasterBrowserPage;
import com.app.ck.pages.admin.NetworkStoreDomainMapping;
import com.app.ck.pages.admin.ProductBrowserEditModePage;
import com.app.ck.pages.admin.TestimonialPages;
import com.app.ck.utilities.CashKaroUtility;
import com.app.ck.utilities.Utilities;
import com.app.ck.utilities.XMLReader;
import com.aventstack.extentreports.Status;
import com.beust.jcommander.Parameter;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.PressesKey;
import io.appium.java_client.clipboard.ClipboardContentType;
import io.appium.java_client.clipboard.HasClipboard;
import io.appium.java_client.touch.LongPressOptions;
import io.appium.java_client.touch.offset.ElementOption;

import static io.restassured.RestAssured.*;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

public class DevelopmentArea_Malik extends WrapperMethods  {



	@Test
	public void myEarningsPageAddBonusValidateStatusConfirmedRequested_TC003() {

		reportStep("Started my earnings page confirmed & requested status validation", "INFO");

		String currentDate = Utilities.today_dd_MMM_YYYY_ISTZone().trim();
		String currentMonthYear = Utilities.currentMonthYear().trim();
		String otpValue = "000000";

//		new EKOnboardingPage(driver, testInfo).
//		clickOnSignInbutton().
//		enterUserName(getLiveTestData(0, "NormalEmail")).
//		enterPassword(getLiveTestData(0, "Password")).
//		clickSignInButtonForSuccess();
		
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

	
	
}
