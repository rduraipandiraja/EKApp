package com.app.ck.test;

import org.testng.annotations.Test;

import com.app.ck.base.MakeLinkURLS;
import com.app.ck.base.WrapperMethods;
import com.app.ck.pages.EKOnboardingPage;
import com.app.ck.pages.HomePage;
import com.app.ck.pages.MakeProfitLink;
import com.app.ck.pages.OTPPage;
import com.app.ck.pages.admin.AdminCoreFunction;
import com.app.ck.pages.admin.AdminStoresPage;
import com.app.ck.pages.admin.NetworkStoreDomainMapping;
import com.app.ck.utilities.CashKaroUtility;

public class EKTestMakeLinkValidation extends WrapperMethods {
	
	
	/*
	 * Author : Mallikarjunaswamy
	 * Date of Creation : 06_08_2019
	 * 
	 * 
	 * Pre- Requisite info :
	 * As this suit fully depends on hard coded admin test data
	 * Make sure Thre is Network store mapping for Amazon, Flipkart ,Jabong and myntra are available
	 * Create the store with the name : AppiumMakeLinkStore - This store mapping must be done and also this store should be Inactive
	 * 
	 * 
	 */

	//Pre- Requisites :
	/*This Test case navigates to the admin and sets the shorten URL for Make my profit link */
	@Test
	public void AAAA_configShortenURLToNetworkStoreDomain() {

		try {
		reportStep("About to enter the Shroten URLs to the Network Store Domain Mapping", "INFO");

		CashKaroUtility cu = new CashKaroUtility(udid, port, automationName, deviceName);
		driver = cu.launchChromeWebView(driver);
		//object Creation :
		NetworkStoreDomainMapping networkMapping = new NetworkStoreDomainMapping(driver, testInfo);
		AdminCoreFunction adminFunctions = new AdminCoreFunction(driver, testInfo);

		//Function call to Navigate to admin
		adminFunctions.naviagateAdminPage_ThenSelectEarnKaro();
		adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
		adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
		adminFunctions.clickSubmit();

		adminFunctions.clickOnHamburgerMenu();
		networkMapping.clickOnNetworkDomainMapping();
		networkMapping.clickOnToolsButton();
		networkMapping.clickOnUpdateShortenURLDomain();
		networkMapping.enterShortenURL();
		networkMapping.clickOnSubmit();
		networkMapping.validateShortenURLSuccessMessage();

		reportStep("Successfully set the Shorten URL to the Network Store Domaini Mapping ", "PASS");
		}
		
		catch(Exception e) {
			
			reportStep("Exception is added - It is a pre - requisite test", "INFO");
			
		}
	}

	/*Navigate to admin and config the Product Cashback URL for Amazon */
	@Test
	public void AAAA_configAmazonNewCashbackURL() {
		
		CashKaroUtility cu = new CashKaroUtility(udid, port, automationName, deviceName);
		driver = cu.launchChromeWebView(driver);
		//object Creation :
		NetworkStoreDomainMapping networkMapping = new NetworkStoreDomainMapping(driver, testInfo);
		AdminCoreFunction adminFunctions = new AdminCoreFunction(driver, testInfo);

		reportStep("About to enter the Shroten URLs to the Network Store Domain Mapping", "INFO");

		//Function call to Navigate to admin
		adminFunctions.naviagateAdminPage_ThenSelectEarnKaro();
		adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
		adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
		adminFunctions.clickSubmit();

		adminFunctions.clickOnHamburgerMenu();
		adminFunctions.clickOnSettings();
		adminFunctions.clickOnAffiliateNetworks();
		adminFunctions.editTheAffiliateNetwork(testdata.get(15).get("AmazonNew"));
		adminFunctions.enterProductCashbackURL(MakeLinkURLS.AMAZON_NEW_PRODUCT_CASHABACK_URL);
		adminFunctions.clickOnSubmitButton();
		adminFunctions.successMessageValidation();
				

	}
	
	/*Navigate to admin and config the Product Cashback URL for FlipKart*/
	@Test
	public void AAAA_configFlipKartNewCashbackURL() {
		
		CashKaroUtility cu = new CashKaroUtility(udid, port, automationName, deviceName);
		driver = cu.launchChromeWebView(driver);
		//object Creation :
		NetworkStoreDomainMapping networkMapping = new NetworkStoreDomainMapping(driver, testInfo);
		AdminCoreFunction adminFunctions = new AdminCoreFunction(driver, testInfo);

		reportStep("About to enter the Shroten URLs to the Network Store Domain Mapping", "INFO");

		//Function call to Navigate to admin
		adminFunctions.naviagateAdminPage_ThenSelectEarnKaro();
		adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
		adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
		adminFunctions.clickSubmit();

		adminFunctions.clickOnHamburgerMenu();
		adminFunctions.clickOnSettings();
		adminFunctions.clickOnAffiliateNetworks();
		adminFunctions.editTheAffiliateNetwork(testdata.get(15).get("FlipKart"));
		adminFunctions.enterProductCashbackURL(MakeLinkURLS.FLIPKART_PRODUCT_CASHBACK_URL);
		adminFunctions.clickOnSubmitButton();
		adminFunctions.successMessageValidation();
				

	}

	/*validating the Amazon - Share link End to end flow */
	@Test
	public void validSharedProductURL_ForAmazon() {
		
		
		//Cashback URL :  {PRDURL}&tag={AFFLTID}&ascsubtag={EXTID}{(ihre_partner_id)AFFLTID}

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo);
		String email = objCashKaroUtility.generateEmail();
		String mobNum = objCashKaroUtility.generateMobileNumber();

		String otpValue = 
		new EKOnboardingPage(driver, testInfo).
		clickOnSignUpButton().
		enterFullName().
		enterEmail(email).
		enterPassword().
		enterMobileNumber(mobNum).
		clickOnGetOTPButton(mobNum);
		
		new OTPPage(driver, testInfo).
		enterOTP(otpValue).
		clickOnVerifyOTP();
		
		// Once after clicking on the Click on verify OTP , it should navigate to the
		// Home page
		HomePage objHomePage = new HomePage(driver, testInfo);

		MakeProfitLink objMakeProfitLinkPage = objHomePage.
		clickOnProfileIconForSignedUser().
		clickOnMakeProfitLink().
		pasteTheURL(MakeLinkURLS.VALIDPRODUCTURL).
		clickOnMakeProfitLink();
		
		String profitLink = objMakeProfitLinkPage.validateShortenURL();
		
		CashKaroUtility cuOne = new CashKaroUtility(udid, port, automationName, deviceName);
		driver = cuOne.launchChromeWebView(driver);
		
		driver.get(profitLink);
		String sellerURL = driver.getCurrentUrl();
		System.out.println("First time "+ sellerURL);
		
		
		//Admin actions
		CashKaroUtility cu = new CashKaroUtility(udid, port, automationName, deviceName);
		driver = cu.launchChromeWebView(driver);
				
		AdminCoreFunction adminFunctions = new AdminCoreFunction(driver, testInfo);		
		adminFunctions.naviagateAdminPage_ThenSelectEarnKaro();
		adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
		adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
		adminFunctions.clickSubmit();
		adminFunctions.clickOnHamburgerMenu();
		adminFunctions.clickOnReports();
		adminFunctions.clickOnExitClick();
		adminFunctions.selectSearchByEmailFromExitClickPage();
		adminFunctions.enterSearchKeywordFromExitClickPage(email);
		adminFunctions.clickOnSearchButtonFromExitClickPage();
				
		String exitClickID = adminFunctions.extractExitClickFromTable();
				 
		reportStep("Exit click id is : "+ exitClickID, "INFO");
		objMakeProfitLinkPage.assertProfitLinkURLWithSellerLandedURL(sellerURL, MakeLinkURLS.VALIDPRODUCTURL_SELLER+exitClickID);

	}
	
	/*Sharing the Long invalid URLS and validating the error messages*/
	@Test
	public void nonWorkingDomainErrorValidation() {

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo);
		String email = objCashKaroUtility.generateEmail();
		String mobNum = objCashKaroUtility.generateMobileNumber();

		String otpValue = 
		new EKOnboardingPage(driver, testInfo).
		clickOnSignUpButton().
		enterFullName().
		enterEmail(email).
		enterPassword().
		enterMobileNumber(mobNum).
		clickOnGetOTPButton(mobNum);
		
		new OTPPage(driver, testInfo).
		enterOTP(otpValue).
		clickOnVerifyOTP();
		
		// Once after clicking on the Click on verify OTP , it should navigate to the
		// Home page
		HomePage objHomePage = new HomePage(driver, testInfo);

		objHomePage.
		clickOnProfileIconForSignedUser().
		clickOnMakeProfitLink().
		pasteTheURL(testdata.get(14).get("NondomainURL_1")).
		clickOnMakeProfitLink().
		invalidRetailerURL().
		pasteTheURL(testdata.get(14).get("NondomainURL_2")).
		clickOnMakeProfitLink().
		invalidRetailerURL().
		pasteTheURL(testdata.get(14).get("NondomainURL_3")).
		clickOnMakeProfitLink().
		invalidRetailerURL().
		pasteTheURL(testdata.get(14).get("NondomainURL_4")).
		clickOnMakeProfitLink().
		invalidRetailerURL().
		pasteTheURL(testdata.get(14).get("NondomainURL_5")).
		clickOnMakeProfitLink().
		invalidRetailerURL().
		pasteTheURL(testdata.get(14).get("NondomainURL_6")).
		clickOnMakeProfitLink().
		invalidRetailerURL().
		pasteTheURL(testdata.get(14).get("NondomainURL_7")).
		clickOnMakeProfitLink().
		invalidRetailerURL().
		pasteTheURL(testdata.get(14).get("NondomainURL_8")).
		clickOnMakeProfitLink().
		invalidRetailerURL().
		pasteTheURL(testdata.get(14).get("NondomainURL_9")).
		clickOnMakeProfitLink().
		invalidRetailerURL();
		
		
	}
	
	/*Validating the other client level validation*/
	@Test
	public void otherClientErrorValidation() {

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo);
		String email = objCashKaroUtility.generateEmail();
		String mobNum = objCashKaroUtility.generateMobileNumber();

		String otpValue = 
		new EKOnboardingPage(driver, testInfo).
		clickOnSignUpButton().
		enterFullName().
		enterEmail(email).
		enterPassword().
		enterMobileNumber(mobNum).
		clickOnGetOTPButton(mobNum);
		
		new OTPPage(driver, testInfo).
		enterOTP(otpValue).
		clickOnVerifyOTP();
		
		// Once after clicking on the Click on verify OTP , it should navigate to the
		// Home page
		HomePage objHomePage = new HomePage(driver, testInfo);

		objHomePage.
		clickOnProfileIconForSignedUser().
		clickOnMakeProfitLink().
		clickOnMakeProfitLink().
		validatePleaseEnterURL().
		pasteTheURL(testdata.get(14).get("ErrorData_1")).
		clickOnMakeProfitLink().
		pleaseEnterValidURL().
		pasteTheURL(testdata.get(14).get("ErrorData_2")).
		clickOnMakeProfitLink().
		pleaseEnterValidURL().
		pasteTheURL(testdata.get(14).get("ErrorData_3")).
		clickOnMakeProfitLink().
		pleaseEnterValidURL().
		pasteTheURL(testdata.get(14).get("ErrorData_4")).
		clickOnMakeProfitLink().
		pleaseEnterValidURL().
		pasteTheURL(testdata.get(14).get("ErrorData_5")).
		clickOnMakeProfitLink().
		pleaseEnterValidURL().
		pasteTheURL(testdata.get(14).get("ErrorData_6")).
		clickOnMakeProfitLink().
		pleaseEnterValidURL().
		pasteTheURL(testdata.get(14).get("ErrorData_8")).
		clickOnMakeProfitLink().
		linkFormatIsInvalid().
		pasteTheURL(testdata.get(14).get("ErrorData_9")).
		clickOnMakeProfitLink().
		invalidRetailerURL().
		pasteTheURL(MakeLinkURLS.DOUBLEQUESTIONMARK_INVALID_URL).
		clickOnMakeProfitLink().
		invalidRetailerURL();
		
	}

	/*Invalid Shared URL - With Tags : Expected that Tags should get removed  */
	@Test
	public void validSharedProductURLWithTags_ForAmazon() {
		
		
		//Cashback URL :  {PRDURL}&tag={AFFLTID}&ascsubtag={EXTID}{(ihre_partner_id)AFFLTID}

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo);
		String email = objCashKaroUtility.generateEmail();
		String mobNum = objCashKaroUtility.generateMobileNumber();

		String otpValue = 
		new EKOnboardingPage(driver, testInfo).
		clickOnSignUpButton().
		enterFullName().
		enterEmail(email).
		enterPassword().
		enterMobileNumber(mobNum).
		clickOnGetOTPButton(mobNum);
		
		new OTPPage(driver, testInfo).
		enterOTP(otpValue).
		clickOnVerifyOTP();
		
		// Once after clicking on the Click on verify OTP , it should navigate to the
		// Home page
		HomePage objHomePage = new HomePage(driver, testInfo);

		MakeProfitLink objMakeProfitLinkPage = objHomePage.
		clickOnProfileIconForSignedUser().
		clickOnMakeProfitLink().
		pasteTheURL(MakeLinkURLS.VALIDPRODUCTURLWITHTAGS).
		clickOnMakeProfitLink();
		
		String profitLink = objMakeProfitLinkPage.validateShortenURL();
		
		CashKaroUtility cuOne = new CashKaroUtility(udid, port, automationName, deviceName);
		driver = cuOne.launchChromeWebView(driver);
		
		driver.get(profitLink);
		String sellerURL = driver.getCurrentUrl();
		System.out.println("First time "+ sellerURL);
		
		
		//Admin actions
		CashKaroUtility cu = new CashKaroUtility(udid, port, automationName, deviceName);
		driver = cu.launchChromeWebView(driver);
				
		AdminCoreFunction adminFunctions = new AdminCoreFunction(driver, testInfo);		
		adminFunctions.naviagateAdminPage_ThenSelectEarnKaro();
		adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
		adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
		adminFunctions.clickSubmit();
		adminFunctions.clickOnHamburgerMenu();
		adminFunctions.clickOnReports();
		adminFunctions.clickOnExitClick();
		adminFunctions.selectSearchByEmailFromExitClickPage();
		adminFunctions.enterSearchKeywordFromExitClickPage(email);
		adminFunctions.clickOnSearchButtonFromExitClickPage();
				
		String exitClickID = adminFunctions.extractExitClickFromTable();
				 
		System.out.println("Exit click id is : "+ exitClickID);
		reportStep("Exit click id is : "+ exitClickID, "INFO");
		objMakeProfitLinkPage.assertProfitLinkURLWithSellerLandedURL(sellerURL, MakeLinkURLS.VALIDPRODUCTURL_SELLER+exitClickID);

	}

	/*Valid Shared shorten FlipKart URL- With coupon dunia Tags : Expected that Tags should get removed then added with cashkaro tags  */
	@Test
	public void validSharedProductURLWithCouponDuniaTags_ForFlipKart() {
		
		
		//Cashback URL :  {PRDURL}&tag={AFFLTID}&ascsubtag={EXTID}{(ihre_partner_id)AFFLTID}

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo);
		String email = objCashKaroUtility.generateEmail();
		String mobNum = objCashKaroUtility.generateMobileNumber();

		String otpValue = 
		new EKOnboardingPage(driver, testInfo).
		clickOnSignUpButton().
		enterFullName().
		enterEmail(email).
		enterPassword().
		enterMobileNumber(mobNum).
		clickOnGetOTPButton(mobNum);
		
		new OTPPage(driver, testInfo).
		enterOTP(otpValue).
		clickOnVerifyOTP();
		
		// Once after clicking on the Click on verify OTP , it should navigate to the
		// Home page
		HomePage objHomePage = new HomePage(driver, testInfo);

		MakeProfitLink objMakeProfitLinkPage = objHomePage.
		clickOnProfileIconForSignedUser().
		clickOnMakeProfitLink().
		pasteTheURL(MakeLinkURLS.FLIPKARTMAKELINKSWITHCOUOPONDUNIATAG).
		clickOnMakeProfitLink();
		
		String profitLink = objMakeProfitLinkPage.validateShortenURL();
		
		CashKaroUtility cuOne = new CashKaroUtility(udid, port, automationName, deviceName);
		driver = cuOne.launchChromeWebView(driver);
		
		driver.get(profitLink);
		String sellerURL = driver.getCurrentUrl();
		System.out.println("First time "+ sellerURL);
		
		
		//Admin actions
		CashKaroUtility cu = new CashKaroUtility(udid, port, automationName, deviceName);
		driver = cu.launchChromeWebView(driver);
				
		AdminCoreFunction adminFunctions = new AdminCoreFunction(driver, testInfo);		
		adminFunctions.naviagateAdminPage_ThenSelectEarnKaro();
		adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
		adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
		adminFunctions.clickSubmit();
		adminFunctions.clickOnHamburgerMenu();
		adminFunctions.clickOnReports();
		adminFunctions.clickOnExitClick();
		adminFunctions.selectSearchByEmailFromExitClickPage();
		adminFunctions.enterSearchKeywordFromExitClickPage(email);
		adminFunctions.clickOnSearchButtonFromExitClickPage();
				
		String exitClickID = adminFunctions.extractExitClickFromTable();
				 
		System.out.println("Exit click id is : "+ exitClickID);
		reportStep("Exit click id is : "+ exitClickID, "INFO");
		objMakeProfitLinkPage.assertProfitLinkURLWithSellerLandedURL(sellerURL, MakeLinkURLS.FLIPKARTMAKELINKSWITHCOUOPONDUNIATAG_AFTERREPLACED+exitClickID+"&aid=rohanpouri");

	}
	
	/*Valid Shared shorten FlipKart URL without any tags Expected that Cashbackk URL has to be formed as expected */
	@Test
	public void validSharedProductURLWithOutAnyTags_ForFlipKart() {
		
		
		//Cashback URL :  {PRDURL}&tag={AFFLTID}&ascsubtag={EXTID}{(ihre_partner_id)AFFLTID}

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo);
		String email = objCashKaroUtility.generateEmail();
		String mobNum = objCashKaroUtility.generateMobileNumber();

		String otpValue = 
		new EKOnboardingPage(driver, testInfo).
		clickOnSignUpButton().
		enterFullName().
		enterEmail(email).
		enterPassword().
		enterMobileNumber(mobNum).
		clickOnGetOTPButton(mobNum);
		
		new OTPPage(driver, testInfo).
		enterOTP(otpValue).
		clickOnVerifyOTP();
		
		// Once after clicking on the Click on verify OTP , it should navigate to the
		// Home page
		HomePage objHomePage = new HomePage(driver, testInfo);

		MakeProfitLink objMakeProfitLinkPage = objHomePage.
		clickOnProfileIconForSignedUser().
		clickOnMakeProfitLink().
		pasteTheURL(MakeLinkURLS.FLIPKARTMAKELINK).
		clickOnMakeProfitLink();
		
		String profitLink = objMakeProfitLinkPage.validateShortenURL();
		
		CashKaroUtility cuOne = new CashKaroUtility(udid, port, automationName, deviceName);
		driver = cuOne.launchChromeWebView(driver);
		
		driver.get(profitLink);
		String sellerURL = driver.getCurrentUrl();
		System.out.println("First time "+ sellerURL);
		
		
		//Admin actions
		CashKaroUtility cu = new CashKaroUtility(udid, port, automationName, deviceName);
		driver = cu.launchChromeWebView(driver);
				
		AdminCoreFunction adminFunctions = new AdminCoreFunction(driver, testInfo);		
		adminFunctions.naviagateAdminPage_ThenSelectEarnKaro();
		adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
		adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
		adminFunctions.clickSubmit();
		adminFunctions.clickOnHamburgerMenu();
		adminFunctions.clickOnReports();
		adminFunctions.clickOnExitClick();
		adminFunctions.selectSearchByEmailFromExitClickPage();
		adminFunctions.enterSearchKeywordFromExitClickPage(email);
		adminFunctions.clickOnSearchButtonFromExitClickPage();
				
		String exitClickID = adminFunctions.extractExitClickFromTable();
				 
		System.out.println("Exit click id is : "+ exitClickID);
		reportStep("Exit click id is : "+ exitClickID, "INFO");
		objMakeProfitLinkPage.assertProfitLinkURLWithSellerLandedURL(sellerURL, MakeLinkURLS.FLIPKARTMAKELINK_SELLER+exitClickID+"&aid=rohanpouri");

	}
	
	/*=========================================Shorten URL validations  =======================================================*/
	/* ==========Make Link with shorten URLs like bitly, bitdo, tinycc , tinyURL,rebrandly ..etc  ================= */

	/*Going to admin and removing the shorten URL from the field - Validating the error by entering invalid shorten URL*/
	@Test
	public void ZZZZ_shortenURLNegative_AdminConfig() {
		
		CashKaroUtility cu = new CashKaroUtility(udid, port, automationName, deviceName);
		driver = cu.launchChromeWebView(driver);
		//object Creation :
		NetworkStoreDomainMapping networkMapping = new NetworkStoreDomainMapping(driver, testInfo);
		AdminCoreFunction adminFunctions = new AdminCoreFunction(driver, testInfo);

		try {
		reportStep("About to enter the Shroten URLs to the Network Store Domain Mapping", "INFO");

		//Function call to Navigate to admin
		adminFunctions.naviagateAdminPage_ThenSelectEarnKaro();
		adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
		adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
		adminFunctions.clickSubmit();

		adminFunctions.clickOnHamburgerMenu();
		networkMapping.clickOnNetworkDomainMapping();
		networkMapping.clickOnToolsButton();
		networkMapping.clickOnUpdateShortenURLDomain();
		networkMapping.enterNegativeShortURL();
		networkMapping.clickOnSubmit();
		networkMapping.validateShortenURLSuccessMessage();

		reportStep("Successfully set the Shorten URL to the Network Store Domaini Mapping ", "PASS");


		CashKaroUtility reopenCKAppTwo = new CashKaroUtility(udid, port, automationName, deviceName, systemPort);
		driver = reopenCKAppTwo.launchEarnKaroApp(driver);

		reportStep("TC001 is  started", "INFO");
		String OtpValue = 
		new EKOnboardingPage(driver, testInfo).
		clickOnSignUpButton().
		enterAllJoinFreeFieldsAndVerifyOTP();

		// creating the new object for the OTP page
		OTPPage objOtpPage = new OTPPage(driver, testInfo);

		objOtpPage.
		enterOTP(OtpValue).
		clickOnVerifyOTP();

		// Once after clicking on the ClickOn verify OTP , it should navigate to the
		// Home page

		new HomePage(driver, testInfo).
		clickOnProfileIconForSignedUser().
		clickOnMakeProfitLink().
		pasteTheURL(testdata.get(14).get("shortenbitly_google_Invalid")).
		clickOnMakeProfitLink().
		invalidRetailerURL().
		pasteTheURL(testdata.get(14).get("shortentinycc_google_Invalid")).
		clickOnMakeProfitLink().
		invalidRetailerURL().
		pasteTheURL(testdata.get(14).get("shortenbitdo_google_Invalid")).
		clickOnMakeProfitLink().
		invalidRetailerURL().
		pasteTheURL(testdata.get(14).get("shortenrebrandly_google_Invalid")).
		clickOnMakeProfitLink().
		invalidRetailerURL().
		pasteTheURL(testdata.get(14).get("shortenurlZs_google_Invalid")).
		clickOnMakeProfitLink().
		invalidRetailerURL().
		pasteTheURL(testdata.get(14).get("shortentinyURL_google_Invalid")).
		clickOnMakeProfitLink().
		invalidRetailerURL().
		pasteTheURL(testdata.get(14).get("shortenBitly_Amazon")).
		clickOnMakeProfitLink().
		invalidRetailerURL().
		pasteTheURL(testdata.get(14).get("shortentinycc_Amazon")).
		clickOnMakeProfitLink().
		invalidRetailerURL().
		pasteTheURL(testdata.get(14).get("shortenbitdo_Amazon")).
		clickOnMakeProfitLink().
		invalidRetailerURL().
		pasteTheURL(testdata.get(14).get("shortenreBrandly_Amazon")).
		clickOnMakeProfitLink().
		invalidRetailerURL().
		pasteTheURL(testdata.get(14).get("shortenurlZs_Amazon")).
		clickOnMakeProfitLink().
		invalidRetailerURL().
		pasteTheURL(testdata.get(14).get("shortentinyURL_Amazon")).
		clickOnMakeProfitLink().
		invalidRetailerURL();

		CashKaroUtility cu2 = new CashKaroUtility(udid, port, automationName, deviceName);
		driver = cu2.launchChromeWebView(driver);

		//object Creation :
		NetworkStoreDomainMapping networkMapping2 = new NetworkStoreDomainMapping(driver, testInfo);
		AdminCoreFunction adminFunctions2 = new AdminCoreFunction(driver, testInfo);
		networkMapping =networkMapping2;
		adminFunctions = adminFunctions2;

		//Function call to Navigate to admin
		adminFunctions.naviagateAdminPage_ThenSelectEarnKaro();
		adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
		adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
		adminFunctions.clickSubmit();

		adminFunctions.clickOnHamburgerMenu();
		networkMapping.clickOnNetworkDomainMapping();
		networkMapping.clickOnToolsButton();
		networkMapping.clickOnUpdateShortenURLDomain();
		networkMapping.enterShortenURL();
		networkMapping.clickOnSubmit();
		networkMapping.validateShortenURLSuccessMessage();

		reportStep("Successfully set the Shorten URL to the Network Store Domaini Mapping ", "PASS");
		
		} catch(Exception e) {
			
			reportStep("These steps are been added to avoid the Unexpected failure and ReSet-UP the Admin Configuration ", "INFO");
			
			CashKaroUtility cu3 = new CashKaroUtility(udid, port, automationName, deviceName);
			driver = cu3.launchChromeWebView(driver);

			//object Creation :
			NetworkStoreDomainMapping networkMapping3 = new NetworkStoreDomainMapping(driver, testInfo);
			AdminCoreFunction adminFunctions3 = new AdminCoreFunction(driver, testInfo);
			networkMapping =networkMapping3;
			adminFunctions = adminFunctions3;

			//Function call to Navigate to admin
			adminFunctions.naviagateAdminPage_ThenSelectEarnKaro();
			adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
			adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
			adminFunctions.clickSubmit();

			adminFunctions.clickOnHamburgerMenu();
			networkMapping.clickOnNetworkDomainMapping();
			networkMapping.clickOnToolsButton();
			networkMapping.clickOnUpdateShortenURLDomain();
			networkMapping.enterShortenURL();
			networkMapping.clickOnSubmit();
			networkMapping.validateShortenURLSuccessMessage();

			reportStep("Successfully set the Shorten URL to the Network Store Domaini Mapping ", "PASS");
			
			
		}

	}

	/*Entering the shorten Invalid or non working domain URL - and validating the error message*/
	@Test
	public void nonWorkingDomainErrorValidationShortenURL() {

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo);
		String email = objCashKaroUtility.generateEmail();
		String mobNum = objCashKaroUtility.generateMobileNumber();

		String otpValue = 
		new EKOnboardingPage(driver, testInfo).
		clickOnSignUpButton().
		enterFullName().
		enterEmail(email).
		enterPassword().
		enterMobileNumber(mobNum).
		clickOnGetOTPButton(mobNum);

		new OTPPage(driver, testInfo).
		enterOTP(otpValue).
		clickOnVerifyOTP();

		// Once after clicking on the Click on verify OTP , it should navigate to the
		// Home page
		HomePage objHomePage = new HomePage(driver, testInfo);

		objHomePage.
		clickOnProfileIconForSignedUser().
		clickOnMakeProfitLink().
		pasteTheURL(testdata.get(14).get("shortenbitly_google_Invalid")).
		clickOnMakeProfitLink().
		invalidRetailerURL().
		pasteTheURL(testdata.get(14).get("shortentinycc_google_Invalid")).
		clickOnMakeProfitLink().
		invalidRetailerURL().
		pasteTheURL(testdata.get(14).get("shortenbitdo_google_Invalid")).
		clickOnMakeProfitLink().
		invalidRetailerURL().
		pasteTheURL(testdata.get(14).get("shortenrebrandly_google_Invalid")).
		clickOnMakeProfitLink().
		invalidRetailerURL().
		pasteTheURL(testdata.get(14).get("shortenurlZs_google_Invalid")).
		clickOnMakeProfitLink().
		invalidRetailerURL().
		pasteTheURL(testdata.get(14).get("shortentinyURL_google_Invalid")).
		clickOnMakeProfitLink().
		invalidRetailerURL();

	}
	
	/*Invalid Shared shorten bitly URL - With coupon dunia Tags : Expected that Tags should get removed then added with cashkaro tags  */
	@Test
	public void ValidSharedProductURLWithCouponDuniaTags_ForAmazonBitly() {
		
		
		//Cashback URL :  {PRDURL}&tag={AFFLTID}&ascsubtag={EXTID}{(ihre_partner_id)AFFLTID}

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo);
		String email = objCashKaroUtility.generateEmail();
		String mobNum = objCashKaroUtility.generateMobileNumber();

		String otpValue = 
		new EKOnboardingPage(driver, testInfo).
		clickOnSignUpButton().
		enterFullName().
		enterEmail(email).
		enterPassword().
		enterMobileNumber(mobNum).
		clickOnGetOTPButton(mobNum);
		
		new OTPPage(driver, testInfo).
		enterOTP(otpValue).
		clickOnVerifyOTP();
		
		// Once after clicking on the Click on verify OTP , it should navigate to the
		// Home page
		HomePage objHomePage = new HomePage(driver, testInfo);

		MakeProfitLink objMakeProfitLinkPage = objHomePage.
		clickOnProfileIconForSignedUser().
		clickOnMakeProfitLink().
		pasteTheURL(MakeLinkURLS.VALIDSHORTENURLWITHTAGS_BITLY).
		clickOnMakeProfitLink();
		
		String profitLink = objMakeProfitLinkPage.validateShortenURL();
		
		CashKaroUtility cuOne = new CashKaroUtility(udid, port, automationName, deviceName);
		driver = cuOne.launchChromeWebView(driver);
		
		driver.get(profitLink);
		String sellerURL = driver.getCurrentUrl();
		System.out.println("First time "+ sellerURL);
		
		
		//Admin actions
		CashKaroUtility cu = new CashKaroUtility(udid, port, automationName, deviceName);
		driver = cu.launchChromeWebView(driver);
				
		AdminCoreFunction adminFunctions = new AdminCoreFunction(driver, testInfo);		
		adminFunctions.naviagateAdminPage_ThenSelectEarnKaro();
		adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
		adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
		adminFunctions.clickSubmit();
		adminFunctions.clickOnHamburgerMenu();
		adminFunctions.clickOnReports();
		adminFunctions.clickOnExitClick();
		adminFunctions.selectSearchByEmailFromExitClickPage();
		adminFunctions.enterSearchKeywordFromExitClickPage(email);
		adminFunctions.clickOnSearchButtonFromExitClickPage();
				
		String exitClickID = adminFunctions.extractExitClickFromTable();
				 
		System.out.println("Exit click id is : "+ exitClickID);
		reportStep("Exit click id is : "+ exitClickID, "INFO");
		objMakeProfitLinkPage.assertProfitLinkURLWithSellerLandedURL(sellerURL, MakeLinkURLS.VALIDPRODUCTURL_SELLER+exitClickID);

	}
	
	/*Invalid Shared shorten bitDo URL - With coupon dunia Tags : Expected that Tags should get removed then added with cashkaro tags  */
	@Test
	public void ValidSharedProductURLWithCouponDuniaTags_ForAmazonBitDo() {
		
		//Cashback URL :  {PRDURL}&tag={AFFLTID}&ascsubtag={EXTID}{(ihre_partner_id)AFFLTID}

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo);
		String email = objCashKaroUtility.generateEmail();
		String mobNum = objCashKaroUtility.generateMobileNumber();

		String otpValue = 
		new EKOnboardingPage(driver, testInfo).
		clickOnSignUpButton().
		enterFullName().
		enterEmail(email).
		enterPassword().
		enterMobileNumber(mobNum).
		clickOnGetOTPButton(mobNum);
		
		new OTPPage(driver, testInfo).
		enterOTP(otpValue).
		clickOnVerifyOTP();
		
		// Once after clicking on the Click on verify OTP , it should navigate to the
		// Home page
		HomePage objHomePage = new HomePage(driver, testInfo);

		MakeProfitLink objMakeProfitLinkPage = objHomePage.
		clickOnProfileIconForSignedUser().
		clickOnMakeProfitLink().
		pasteTheURL(MakeLinkURLS.VALIDSHORTENURLWITHTAGS_BITDO).
		clickOnMakeProfitLink();
		
		String profitLink = objMakeProfitLinkPage.validateShortenURL();
		
		CashKaroUtility cuOne = new CashKaroUtility(udid, port, automationName, deviceName);
		driver = cuOne.launchChromeWebView(driver);
		
		driver.get(profitLink);
		String sellerURL = driver.getCurrentUrl();
		System.out.println("First time "+ sellerURL);
		
		
		//Admin actions
		CashKaroUtility cu = new CashKaroUtility(udid, port, automationName, deviceName);
		driver = cu.launchChromeWebView(driver);
				
		AdminCoreFunction adminFunctions = new AdminCoreFunction(driver, testInfo);		
		adminFunctions.naviagateAdminPage_ThenSelectEarnKaro();
		adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
		adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
		adminFunctions.clickSubmit();
		adminFunctions.clickOnHamburgerMenu();
		adminFunctions.clickOnReports();
		adminFunctions.clickOnExitClick();
		adminFunctions.selectSearchByEmailFromExitClickPage();
		adminFunctions.enterSearchKeywordFromExitClickPage(email);
		adminFunctions.clickOnSearchButtonFromExitClickPage();
				
		String exitClickID = adminFunctions.extractExitClickFromTable();
				 
		System.out.println("Exit click id is : "+ exitClickID);
		reportStep("Exit click id is : "+ exitClickID, "INFO");
		objMakeProfitLinkPage.assertProfitLinkURLWithSellerLandedURL(sellerURL, MakeLinkURLS.VALIDPRODUCTURL_SELLER+exitClickID);

	}

	/*Invalid Shared shorten tinyCC URL - With coupon dunia Tags : Expected that Tags should get removed then added with cashkaro tags  */
	@Test
	public void ValidSharedProductURLWithCouponDuniaTags_ForAmazonTinyCC() {
		
		
		//Cashback URL :  {PRDURL}&tag={AFFLTID}&ascsubtag={EXTID}{(ihre_partner_id)AFFLTID}

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo);
		String email = objCashKaroUtility.generateEmail();
		String mobNum = objCashKaroUtility.generateMobileNumber();

		String otpValue = 
		new EKOnboardingPage(driver, testInfo).
		clickOnSignUpButton().
		enterFullName().
		enterEmail(email).
		enterPassword().
		enterMobileNumber(mobNum).
		clickOnGetOTPButton(mobNum);
		
		new OTPPage(driver, testInfo).
		enterOTP(otpValue).
		clickOnVerifyOTP();
		
		// Once after clicking on the Click on verify OTP , it should navigate to the
		// Home page
		HomePage objHomePage = new HomePage(driver, testInfo);

		MakeProfitLink objMakeProfitLinkPage = objHomePage.
		clickOnProfileIconForSignedUser().
		clickOnMakeProfitLink().
		pasteTheURL(MakeLinkURLS.VALIDSHORTENURLWITHTAGS_TINYCC).
		clickOnMakeProfitLink();
		
		String profitLink = objMakeProfitLinkPage.validateShortenURL();
		
		CashKaroUtility cuOne = new CashKaroUtility(udid, port, automationName, deviceName);
		driver = cuOne.launchChromeWebView(driver);
		
		driver.get(profitLink);
		String sellerURL = driver.getCurrentUrl();
		System.out.println("First time "+ sellerURL);
		
		
		//Admin actions
		CashKaroUtility cu = new CashKaroUtility(udid, port, automationName, deviceName);
		driver = cu.launchChromeWebView(driver);
				
		AdminCoreFunction adminFunctions = new AdminCoreFunction(driver, testInfo);		
		adminFunctions.naviagateAdminPage_ThenSelectEarnKaro();
		adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
		adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
		adminFunctions.clickSubmit();
		adminFunctions.clickOnHamburgerMenu();
		adminFunctions.clickOnReports();
		adminFunctions.clickOnExitClick();
		adminFunctions.selectSearchByEmailFromExitClickPage();
		adminFunctions.enterSearchKeywordFromExitClickPage(email);
		adminFunctions.clickOnSearchButtonFromExitClickPage();
				
		String exitClickID = adminFunctions.extractExitClickFromTable();
				 
		System.out.println("Exit click id is : "+ exitClickID);
		reportStep("Exit click id is : "+ exitClickID, "INFO");
		objMakeProfitLinkPage.assertProfitLinkURLWithSellerLandedURL(sellerURL, MakeLinkURLS.VALIDPRODUCTURL_SELLER+exitClickID);

	}
	
	/*Invalid Shared shorten tiny URL - With coupon dunia Tags : Expected that Tags should get removed then added with cashkaro tags  */
	@Test
	public void ValidSharedProductURLWithCouponDuniaTags_ForAmazonTinyURL() {
		
		
		//Cashback URL :  {PRDURL}&tag={AFFLTID}&ascsubtag={EXTID}{(ihre_partner_id)AFFLTID}

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo);
		String email = objCashKaroUtility.generateEmail();
		String mobNum = objCashKaroUtility.generateMobileNumber();

		String otpValue = 
		new EKOnboardingPage(driver, testInfo).
		clickOnSignUpButton().
		enterFullName().
		enterEmail(email).
		enterPassword().
		enterMobileNumber(mobNum).
		clickOnGetOTPButton(mobNum);
		
		new OTPPage(driver, testInfo).
		enterOTP(otpValue).
		clickOnVerifyOTP();
		
		// Once after clicking on the Click on verify OTP , it should navigate to the
		// Home page
		HomePage objHomePage = new HomePage(driver, testInfo);

		MakeProfitLink objMakeProfitLinkPage = objHomePage.
		clickOnProfileIconForSignedUser().
		clickOnMakeProfitLink().
		pasteTheURL(MakeLinkURLS.VALIDSHORTENURLWITHTAGS_TINYURL).
		clickOnMakeProfitLink();
		
		String profitLink = objMakeProfitLinkPage.validateShortenURL();
		
		CashKaroUtility cuOne = new CashKaroUtility(udid, port, automationName, deviceName);
		driver = cuOne.launchChromeWebView(driver);
		
		driver.get(profitLink);
		String sellerURL = driver.getCurrentUrl();
		System.out.println("First time "+ sellerURL);
		
		
		//Admin actions
		CashKaroUtility cu = new CashKaroUtility(udid, port, automationName, deviceName);
		driver = cu.launchChromeWebView(driver);
				
		AdminCoreFunction adminFunctions = new AdminCoreFunction(driver, testInfo);		
		adminFunctions.naviagateAdminPage_ThenSelectEarnKaro();
		adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
		adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
		adminFunctions.clickSubmit();
		adminFunctions.clickOnHamburgerMenu();
		adminFunctions.clickOnReports();
		adminFunctions.clickOnExitClick();
		adminFunctions.selectSearchByEmailFromExitClickPage();
		adminFunctions.enterSearchKeywordFromExitClickPage(email);
		adminFunctions.clickOnSearchButtonFromExitClickPage();
				
		String exitClickID = adminFunctions.extractExitClickFromTable();
				 
		System.out.println("Exit click id is : "+ exitClickID);
		reportStep("Exit click id is : "+ exitClickID, "INFO");
		objMakeProfitLinkPage.assertProfitLinkURLWithSellerLandedURL(sellerURL, MakeLinkURLS.VALIDPRODUCTURL_SELLER+exitClickID);

	}

	/*Ensuring the required hard coded test data is present in the admin  */
	@Test
	public void valdiateErrorMessageWhenProfitLinkDomainStoreIs_InActive() {
		
		String domainURL = testdata.get(14).get("makeProfitLinkStoreName_InActiveStore_DomainURL");
		String storeStatus = null;
	
		reportStep("Ensuring that required hard coded test data is available or Not ", "INFO");

		CashKaroUtility cu = new CashKaroUtility(udid, port, automationName, deviceName);
		driver = cu.launchChromeWebView(driver);
		//object Creation :
		NetworkStoreDomainMapping networkMapping = new NetworkStoreDomainMapping(driver, testInfo);
		AdminCoreFunction adminFunctions = new AdminCoreFunction(driver, testInfo);
		//create admin store page object :
		AdminStoresPage adminStorePage = new AdminStoresPage(driver, testInfo);

		//Function call to Navigate to admin
		adminFunctions.naviagateAdminPage_ThenSelectEarnKaro();
		adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
		adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
		adminFunctions.clickSubmit();

		adminFunctions.clickOnHamburgerMenu();
		networkMapping.clickOnNetworkDomainMapping();
		networkMapping.enterDomainURLForSearch(domainURL);
		networkMapping.clickOnSearchButton();
		networkMapping.verifySearchDomainIsAvailable();
		
		adminFunctions.clickOnHamburgerMenu();
		adminFunctions.clickStoresMenu();
		adminFunctions.clickStoresSubMenu();
		
		adminStorePage.enterStoreName(testdata.get(14).get("makeProfitLinkStoreName_InActiveStore"));
		adminStorePage.clickSubmitButton();
		storeStatus = adminStorePage.getTheStoreStatus();

		reportStep("Completed - search for the requird Domain - Required testdata (Test data is hard coded) : "
				+ " The Status of Active Store is " + storeStatus, "PASS");

		CashKaroUtility reopenCKAppTwo = new CashKaroUtility(udid, port, automationName, deviceName, systemPort);
		driver = reopenCKAppTwo.launchEarnKaroApp(driver);

		
		String OtpValue = 
		new EKOnboardingPage(driver, testInfo).
		clickOnSignUpButton().
		enterAllJoinFreeFieldsAndVerifyOTP();

		// creating the new object for the OTP page
		OTPPage objOtpPage = new OTPPage(driver, testInfo);

		objOtpPage.
		enterOTP(OtpValue).
		clickOnVerifyOTP();

		// Once after clicking on the ClickOn verify OTP , it should navigate to the
		// Home page

		new HomePage(driver, testInfo).
		clickOnProfileIconForSignedUser().
		clickOnMakeProfitLink().
		pasteTheURL(testdata.get(14).get("makeProfitLinkStoreName_InActiveStore_SharedURL")). //Shared link store in inactive
		clickOnMakeProfitLink().
		validateMakeProfitLinkBasedOnStoreStatus(storeStatus);
	
	}

	//==

	/*validating the Myntra Long URL - Share link */
	@Test(dependsOnMethods = { "AAAA_configShortenURLToNetworkStoreDomain" })
	public void validateShortenURLisGenerated_ForMyntraLongURL() {

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo);
		String email = objCashKaroUtility.generateEmail();
		String mobNum = objCashKaroUtility.generateMobileNumber();

		String otpValue = 
		new EKOnboardingPage(driver, testInfo).
		clickOnSignUpButton().
		enterFullName().
		enterEmail(email).
		enterPassword().
		enterMobileNumber(mobNum).
		clickOnGetOTPButton(mobNum);
		
		new OTPPage(driver, testInfo).
		enterOTP(otpValue).
		clickOnVerifyOTP();
		
		// Once after clicking on the Click on verify OTP , it should navigate to the
		// Home page
		HomePage objHomePage = new HomePage(driver, testInfo);

		MakeProfitLink objMakeProfitLinkPage = objHomePage.
		clickOnProfileIconForSignedUser().
		clickOnMakeProfitLink().
		pasteTheURL(MakeLinkURLS.MYNTRAVALIDURL).
		clickOnMakeProfitLink();
		
		objMakeProfitLinkPage.validateShortenURL();
		
		
	}
	
	/*validating the Myntra SHORT URL - Share link */
	@Test(dependsOnMethods = { "AAAA_configShortenURLToNetworkStoreDomain" })
	public void validateShortenURLisGenerated_ForMyntraShortenURL() {
		
		try {
		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo);
		String email = objCashKaroUtility.generateEmail();
		String mobNum = objCashKaroUtility.generateMobileNumber();

		String otpValue = 
		new EKOnboardingPage(driver, testInfo).
		clickOnSignUpButton().
		enterFullName().
		enterEmail(email).
		enterPassword().
		enterMobileNumber(mobNum).
		clickOnGetOTPButton(mobNum);
		
		new OTPPage(driver, testInfo).
		enterOTP(otpValue).
		clickOnVerifyOTP();
		
		// Once after clicking on the Click on verify OTP , it should navigate to the
		// Home page
		HomePage objHomePage = new HomePage(driver, testInfo);

		MakeProfitLink objMakeProfitLinkPage = objHomePage.
		clickOnProfileIconForSignedUser().
		clickOnMakeProfitLink().
		pasteTheURL(MakeLinkURLS.MYNTRAVALIDURL_BITLY).
		clickOnMakeProfitLink();
		
		objMakeProfitLinkPage.validateShortenURL();
		
		}catch (Exception e) {
			
		reportStep("This Method is Supportive Method - So if it is Failed , No need to stop the other test cases ", "INFO");
		reportStep("Handled the Exception in Catch ", "INFO");
		
		
		}
		
		
	}
	
	/*MYNTRA MULTIPLE LONG URL - ONLY ONE VALID URL */
	@Test(dependsOnMethods = { "AAAA_configShortenURLToNetworkStoreDomain" })
	public void validateShortenURLisGenerated_ForMyntraLongMultipleOneValidURL() {
		
		

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo);
		String email = objCashKaroUtility.generateEmail();
		String mobNum = objCashKaroUtility.generateMobileNumber();

		String otpValue = 
		new EKOnboardingPage(driver, testInfo).
		clickOnSignUpButton().
		enterFullName().
		enterEmail(email).
		enterPassword().
		enterMobileNumber(mobNum).
		clickOnGetOTPButton(mobNum);
		
		new OTPPage(driver, testInfo).
		enterOTP(otpValue).
		clickOnVerifyOTP();
		
		// Once after clicking on the Click on verify OTP , it should navigate to the
		// Home page
		HomePage objHomePage = new HomePage(driver, testInfo);

		MakeProfitLink objMakeProfitLinkPage = objHomePage.
		clickOnProfileIconForSignedUser().
		clickOnMakeProfitLink().
		pasteTheURL(MakeLinkURLS.MYNTRAMULTIPLEURLWITHONLYONEVALID).
		clickOnMakeProfitLink();
		
		objMakeProfitLinkPage.validateShortenURL();
		
		
	}
	
	/*MYNTRA MULTIPLE SHORTEN URL - ONLY ONE VALID URL */
	@Test(dependsOnMethods = { "AAAA_configShortenURLToNetworkStoreDomain" })
	public void validateShortenURLisGenerated_ForMyntraShortenMultipleOneValidURL() {

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo);
		String email = objCashKaroUtility.generateEmail();
		String mobNum = objCashKaroUtility.generateMobileNumber();

		String otpValue = 
		new EKOnboardingPage(driver, testInfo).
		clickOnSignUpButton().
		enterFullName().
		enterEmail(email).
		enterPassword().
		enterMobileNumber(mobNum).
		clickOnGetOTPButton(mobNum);
		
		new OTPPage(driver, testInfo).
		enterOTP(otpValue).
		clickOnVerifyOTP();
		
		// Once after clicking on the Click on verify OTP , it should navigate to the
		// Home page
		HomePage objHomePage = new HomePage(driver, testInfo);

		MakeProfitLink objMakeProfitLinkPage = objHomePage.
		clickOnProfileIconForSignedUser().
		clickOnMakeProfitLink().
		pasteTheURL(MakeLinkURLS.MYNTRAMULTIPLEURLWITHONLYONEVALID_BILTY).
		clickOnMakeProfitLink();
		
		objMakeProfitLinkPage.validateShortenURL();
		
		
	}
	
	/*validating the Jabong Long URL - Share link */
	@Test(dependsOnMethods = { "AAAA_configShortenURLToNetworkStoreDomain" })
	public void validateShortenURLisGenerated_ForJabongLongURL() {
		
		

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo);
		String email = objCashKaroUtility.generateEmail();
		String mobNum = objCashKaroUtility.generateMobileNumber();

		String otpValue = 
		new EKOnboardingPage(driver, testInfo).
		clickOnSignUpButton().
		enterFullName().
		enterEmail(email).
		enterPassword().
		enterMobileNumber(mobNum).
		clickOnGetOTPButton(mobNum);
		
		new OTPPage(driver, testInfo).
		enterOTP(otpValue).
		clickOnVerifyOTP();
		
		// Once after clicking on the Click on verify OTP , it should navigate to the
		// Home page
		HomePage objHomePage = new HomePage(driver, testInfo);

		MakeProfitLink objMakeProfitLinkPage = objHomePage.
		clickOnProfileIconForSignedUser().
		clickOnMakeProfitLink().
		pasteTheURL(MakeLinkURLS.JABONGVALIDURL).
		clickOnMakeProfitLink();
		
		objMakeProfitLinkPage.validateShortenURL();
		
		
	}
	
	/*validating the Jabong SHORTEN URL - Share link */
	@Test(dependsOnMethods = { "AAAA_configShortenURLToNetworkStoreDomain" })
	public void validateShortenURLisGenerated_ForjabongShortenURL() {
		
		

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo);
		String email = objCashKaroUtility.generateEmail();
		String mobNum = objCashKaroUtility.generateMobileNumber();

		String otpValue = 
		new EKOnboardingPage(driver, testInfo).
		clickOnSignUpButton().
		enterFullName().
		enterEmail(email).
		enterPassword().
		enterMobileNumber(mobNum).
		clickOnGetOTPButton(mobNum);
		
		new OTPPage(driver, testInfo).
		enterOTP(otpValue).
		clickOnVerifyOTP();
		
		// Once after clicking on the Click on verify OTP , it should navigate to the
		// Home page
		HomePage objHomePage = new HomePage(driver, testInfo);

		MakeProfitLink objMakeProfitLinkPage = objHomePage.
		clickOnProfileIconForSignedUser().
		clickOnMakeProfitLink().
		pasteTheURL(MakeLinkURLS.JABONGVALIDURL_BITLY).
		clickOnMakeProfitLink();
		
		objMakeProfitLinkPage.validateShortenURL();
		
		
	}

	/*validating the Jabong Long URL where it has only one valid URL - Share link */
	@Test(dependsOnMethods = { "AAAA_configShortenURLToNetworkStoreDomain" })
	public void validateShortenURLisGenerated_ForJabongLongMultipleOneValidURL() {
		
		

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo);
		String email = objCashKaroUtility.generateEmail();
		String mobNum = objCashKaroUtility.generateMobileNumber();

		String otpValue = 
		new EKOnboardingPage(driver, testInfo).
		clickOnSignUpButton().
		enterFullName().
		enterEmail(email).
		enterPassword().
		enterMobileNumber(mobNum).
		clickOnGetOTPButton(mobNum);
		
		new OTPPage(driver, testInfo).
		enterOTP(otpValue).
		clickOnVerifyOTP();
		
		// Once after clicking on the Click on verify OTP , it should navigate to the
		// Home page
		HomePage objHomePage = new HomePage(driver, testInfo);

		MakeProfitLink objMakeProfitLinkPage = objHomePage.
		clickOnProfileIconForSignedUser().
		clickOnMakeProfitLink().
		pasteTheURL(MakeLinkURLS.JABONGMULTIPLEURLWITHONLYONEVALID).
		clickOnMakeProfitLink();
		
		objMakeProfitLinkPage.validateShortenURL();
		
		
	}
	
	/*validating the Jabong Shorten URL where it has only one valid URL - Share link */
	@Test(dependsOnMethods = { "AAAA_configShortenURLToNetworkStoreDomain" })
	public void validateShortenURLisGenerated_ForjabongShortenMultipleOneValidURL() {
		
		

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo);
		String email = objCashKaroUtility.generateEmail();
		String mobNum = objCashKaroUtility.generateMobileNumber();

		String otpValue = 
		new EKOnboardingPage(driver, testInfo).
		clickOnSignUpButton().
		enterFullName().
		enterEmail(email).
		enterPassword().
		enterMobileNumber(mobNum).
		clickOnGetOTPButton(mobNum);
		
		new OTPPage(driver, testInfo).
		enterOTP(otpValue).
		clickOnVerifyOTP();
		
		// Once after clicking on the Click on verify OTP , it should navigate to the
		// Home page
		HomePage objHomePage = new HomePage(driver, testInfo);

		MakeProfitLink objMakeProfitLinkPage = objHomePage.
		clickOnProfileIconForSignedUser().
		clickOnMakeProfitLink().
		pasteTheURL(MakeLinkURLS.JABONGMULTIPLEURLWITHONLYONEVALID_BILTY).
		clickOnMakeProfitLink();
		
		objMakeProfitLinkPage.validateShortenURL();
		
		
	}

	/*validating the Jabong and Myntra both valid Long URL - Share link */
	@Test(dependsOnMethods = { "AAAA_configShortenURLToNetworkStoreDomain" })
	public void validateTooManyLongValidURLErrorWhenHavingTooManyValidURLWithDifferentDomain() {
		

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo);
		String email = objCashKaroUtility.generateEmail();
		String mobNum = objCashKaroUtility.generateMobileNumber();

		String otpValue = 
		new EKOnboardingPage(driver, testInfo).
		clickOnSignUpButton().
		enterFullName().
		enterEmail(email).
		enterPassword().
		enterMobileNumber(mobNum).
		clickOnGetOTPButton(mobNum);
		
		new OTPPage(driver, testInfo).
		enterOTP(otpValue).
		clickOnVerifyOTP();
		
		// Once after clicking on the Click on verify OTP , it should navigate to the
		// Home page
		HomePage objHomePage = new HomePage(driver, testInfo);

		MakeProfitLink objMakeProfitLinkPage = objHomePage.
		clickOnProfileIconForSignedUser().
		clickOnMakeProfitLink().
		pasteTheURL(MakeLinkURLS.JABONGANDMYNTRA_TWO_VALID_URL).
		clickOnMakeProfitLink().
		validateTooManyURLsInPastedLink();
		
	}
	
	/*having multiple urls with different domain domain - as shorten url*/
	@Test(dependsOnMethods = { "AAAA_configShortenURLToNetworkStoreDomain" })
	public void validateTooManyShortenValidURLErrorWhenHavingTooManyValidURLWithDifferentDomain() {
		
		

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo);
		String email = objCashKaroUtility.generateEmail();
		String mobNum = objCashKaroUtility.generateMobileNumber();

		String otpValue = 
		new EKOnboardingPage(driver, testInfo).
		clickOnSignUpButton().
		enterFullName().
		enterEmail(email).
		enterPassword().
		enterMobileNumber(mobNum).
		clickOnGetOTPButton(mobNum);
		
		new OTPPage(driver, testInfo).
		enterOTP(otpValue).
		clickOnVerifyOTP();
		
		// Once after clicking on the Click on verify OTP , it should navigate to the
		// Home page
		HomePage objHomePage = new HomePage(driver, testInfo);

		MakeProfitLink objMakeProfitLinkPage = objHomePage.
		clickOnProfileIconForSignedUser().
		clickOnMakeProfitLink().
		pasteTheURL(MakeLinkURLS.JABONGANDMYNTRA_TWO_VALID_URL_BITLY).
		clickOnMakeProfitLink().
		validateTooManyURLsInPastedLink();
		
	}
	
	/*having multiple urls with same domain - Long URL*/
	@Test(dependsOnMethods = { "AAAA_configShortenURLToNetworkStoreDomain" })
	public void validateTooManyLongValidURLErrorWhenHavingTooManyValidURLWithSameDomain() {
		
		

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo);
		String email = objCashKaroUtility.generateEmail();
		String mobNum = objCashKaroUtility.generateMobileNumber();

		String otpValue = 
		new EKOnboardingPage(driver, testInfo).
		clickOnSignUpButton().
		enterFullName().
		enterEmail(email).
		enterPassword().
		enterMobileNumber(mobNum).
		clickOnGetOTPButton(mobNum);
		
		new OTPPage(driver, testInfo).
		enterOTP(otpValue).
		clickOnVerifyOTP();
		
		// Once after clicking on the Click on verify OTP , it should navigate to the
		// Home page
		HomePage objHomePage = new HomePage(driver, testInfo);

		MakeProfitLink objMakeProfitLinkPage = objHomePage.
		clickOnProfileIconForSignedUser().
		clickOnMakeProfitLink().
		pasteTheURL(MakeLinkURLS.HAVING_MULTIPLE_URLS_WITH_SAME_VALID_DOMAIN).
		clickOnMakeProfitLink().
		validateTooManyURLsInPastedLink();
		
	}
	
	/*having multiple urls with same domain - Long URL*/
	@Test(dependsOnMethods = { "AAAA_configShortenURLToNetworkStoreDomain" })
	public void validateTooManyShortenValidURLErrorWhenHavingTooManyValidURLWithSameDomain() {
		
		

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo);
		String email = objCashKaroUtility.generateEmail();
		String mobNum = objCashKaroUtility.generateMobileNumber();

		String otpValue = 
		new EKOnboardingPage(driver, testInfo).
		clickOnSignUpButton().
		enterFullName().
		enterEmail(email).
		enterPassword().
		enterMobileNumber(mobNum).
		clickOnGetOTPButton(mobNum);
		
		new OTPPage(driver, testInfo).
		enterOTP(otpValue).
		clickOnVerifyOTP();
		
		// Once after clicking on the Click on verify OTP , it should navigate to the
		// Home page
		HomePage objHomePage = new HomePage(driver, testInfo);

		MakeProfitLink objMakeProfitLinkPage = objHomePage.
		clickOnProfileIconForSignedUser().
		clickOnMakeProfitLink().
		pasteTheURL(MakeLinkURLS.HAVING_MULTIPLE_URLS_WITH_SAME_VALID_DOMAIN_BITLY).
		clickOnMakeProfitLink().
		validateTooManyURLsInPastedLink();
		
	}



}
