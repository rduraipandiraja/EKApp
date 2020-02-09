package com.app.ck.test;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.app.ck.base.WrapperMethods;
import com.app.ck.pages.EKOnboardingPage;
import com.app.ck.pages.HomePage;
import com.app.ck.pages.IntermediateRetailerPage;
import com.app.ck.pages.JoinFreePage;
import com.app.ck.pages.OTPPage;
import com.app.ck.pages.StoreCategoryPage;
import com.app.ck.pages.StoreDetailPage;
import com.app.ck.pages.WhatsAppUtility;
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

public class EKTestStoreCategoryPageValidation extends WrapperMethods {

	/*******************************************************************/
	/************************ STORE CATEGORY ***************************/
	/*******************************************************************/

	public int bannerCount;
	public String storename;
	public String shortDescription;

	@Test
	public void storeCategoryPage_ValidateHamburgerMenuFieldsCategoriesSubCategoriesNavigation_TC001() {

		reportStep("TC001 is  started", "INFO");

		new EKOnboardingPage(driver, testInfo).
		clickOnSignInbutton().
		enterUserName().
		enterPassword().
		clickSignInButtonForSuccess().
		clickOnHamburgerIcon().
		presenceOfHomeIcon().
		presenceOfAppExclusiveCategory().
		presenceOfHighestProfitRates().
		presenceOfRetailerCategory();

		new HomePage(driver, testInfo).
		clickOnProfileIcon_enhanced().
		clickOnHamburgerIcon().
		clickHomeIcon().
		validateTheAbsenceOfSliderMenu().
		clickOnHamburgerIcon().
		clickAppExclusiveCategory().
		clickBackButton().
		clickOnHamburgerIcon().
		clickHighestProfitRates().
		clickBackButton().
		clickOnHamburgerIcon().
		clickRetailerCategory().
		verifyRetailerCategoryMenuNavigation().
		clickAutomationSpecificCategoryInRetailerCategoryMenuUntilAllSubCategoryVisible().
		verifyRetailerCategoryMenuIsGettingMaximizedAndMinimized(getTestData(9, "CountSeven")).
		clickAutomationSpecificCategoryInRetailerCategoryMenu().
		verifyRetailerCategoryMenuIsGettingMaximizedAndMinimized(getTestData(9, "CountOne")).
		clickSearchIcon().
		clickBackButton().
		clickBackButton().
		clickOnHamburgerIcon().
		clickRetailerCategory().
		verifyRetailerCategoryMenuNavigation().
		clickAutomationSpecificCategoryInRetailerCategoryMenuUntilAllSubCategoryVisible().
		clickAutomationSpecificSubCategoryOne().verifyAutomationSpecificSubCategoryNavigation().
		clickBackButtonForRetailerCategoryPageNavigation().
		verifyRetailerCategoryMenuNavigation().
		clickBackButton();

		reportStep("TC001 is  ended", "INFO");

	}

	@Test
	public void storeCategoryPage_ValidateTabFunctionalityInStoreCategoryPage_TC002() {

		reportStep("TC002 is  started", "INFO");

		String popularityTabFirstCardAppShortDescription = CashKaroUtility.extractValuesFromStoreCategoryJsonAndLoadTheVariablesForStore("CB_Store_One", 0, "CB_Store_One_App_Short_Description");
		String popularityTabLastCardAppShortDescription = CashKaroUtility.extractValuesFromStoreCategoryJsonAndLoadTheVariablesForStore("RW_Store_One", 2, "RW_Store_One_App_Short_Description");
		String percentTabFirstCardAppShortDescription = CashKaroUtility.extractValuesFromStoreCategoryJsonAndLoadTheVariablesForStore("RW_Store_One", 2, "RW_Store_One_App_Short_Description");
		String amountTabFirstCardAppShortDescription = CashKaroUtility.extractValuesFromStoreCategoryJsonAndLoadTheVariablesForStore("RW_Store_Two", 3,	"RW_Store_Two_App_Short_Description");
		String atozTabFirstCardAppShortDescription = CashKaroUtility.extractValuesFromStoreCategoryJsonAndLoadTheVariablesForStore("CB_Store_Two", 1, "CB_Store_Two_App_Short_Description");
		String atozTabLastCardAppShortDescription = CashKaroUtility.extractValuesFromStoreCategoryJsonAndLoadTheVariablesForStore("RW_Store_Two", 3, "RW_Store_Two_App_Short_Description");

		new EKOnboardingPage(driver, testInfo).
		clickOnSignInbutton().
		enterUserName().
		enterPassword().
		clickSignInButtonForSuccess().
		clickOnHamburgerIcon().
		clickRetailerCategory().
		verifyRetailerCategoryMenuNavigation().
		clickAutomationSpecificCategoryInRetailerCategoryMenuUntilAllSubCategoryVisible().
		clickAutomationSpecificSubCategoryOne().
		verifyAutomationSpecificSubCategoryNavigation().
		clickPopularTab().
		verifyFirstCardDescription(popularityTabFirstCardAppShortDescription).
		verifyLastCardDescription(popularityTabLastCardAppShortDescription).
		clickPercentTab().
		verifyFirstCardDescription(percentTabFirstCardAppShortDescription).
		clickAmountTab().
		verifyFirstCardDescription(amountTabFirstCardAppShortDescription).
		clickAzTab().
		verifyFirstCardDescription(atozTabFirstCardAppShortDescription).
		verifyLastCardDescription(atozTabLastCardAppShortDescription);

		reportStep("TC002 is  ended", "INFO");

	}

	//@Test(priority = 10)
	public void storeCategoryPage_ValidateBannerCountInStoreCategoryPageBeforeAndAfterAddingStore_TC009() {

		reportStep("TC009 is  started", "INFO");
		
		try {
			
			int bannerCountAfterAddingStore;

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
			clickOnHamburgerIcon().
			clickRetailerCategory().
			verifyRetailerCategoryMenuNavigation().
			clickAutomationSpecificCategoryInRetailerCategoryMenuUntilAllSubCategoryVisible().
			clickAutomationSpecificSubCategoryOne().verifyAutomationSpecificSubCategoryNavigation().
			verifyStoreCategoryPageHeaderTitle().
			verifyStoreCategoryPageBannerTitle().
			verifyStoreCategoryPageDropdown();

			StoreCategoryPage objDriver = new StoreCategoryPage(driver, testInfo);
			bannerCount = objDriver.verifyExtractCountFromBannerTitle();

			CashKaroUtility cu = new CashKaroUtility(udid, port, automationName, deviceName);
			driver = cu.launchChromeWebView(driver);

			AdminCoreFunction adminFunctions = new AdminCoreFunction(driver, testInfo);
			adminFunctions.naviagateAdminPage_ThenSelectEarnKaro();
			adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
			adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
			adminFunctions.clickSubmit();

			adminFunctions.clickOnHamburgerMenu();
			adminFunctions.clickStoresMenu();
			adminFunctions.clickStoresSubMenu();

			storename = Utilities.datestamp("Profit Store: ");

			AdminStoresPage strPage = new AdminStoresPage(driver, testInfo);
			strPage.clickAddNewButton();
			strPage.enterName(storename);
			strPage.enterUrlKey(storename);
			strPage.clickCheckBoxApp();
			shortDescription = strPage.enterShortDescription("App short description for temporary store");
			strPage.selectStatusFromDropDown("Active");
			strPage.selectAffiliateNetworkFromDropDown("Adda52");
			strPage.enterExitClick("500000");
			strPage.selectStoreTypeFromDropDown("Cashback");
			strPage.enterLinkURL(getTestData(4, "StoreURL"));
			strPage.clickCategoryTab();
			strPage.clickAutomationSpecificCategory();
			strPage.clickPrimaryCashbackTab();
			strPage.enterNetworkCommision("1000");
			strPage.enterCashback("50");
			strPage.enterCashbackPercentage("5");
			strPage.clickSecondaryCashbackTab();
			strPage.enterSecondaryCashback("1000");
			strPage.enterSecondaryCashbackDetails("Details");
			strPage.clickSecondaryCashbackCheckBoxApp();
			strPage.clickSubmitButton();

			ProductBrowserEditModePage pdtBrowserEdit = new ProductBrowserEditModePage(driver, testInfo);
			adminFunctions.clickOnHamburgerMenu();
			adminFunctions.clickProductFeedMenu();
			adminFunctions.clickProductBrowserEditModeSubMenu();
			
			pdtBrowserEdit.clickRefreshPrimaryCashaback();
			pdtBrowserEdit.clickRefreshButton();
			pdtBrowserEdit.clickOkButton();

			CashKaroUtility reopenCKApp = new CashKaroUtility(udid, port, automationName, deviceName, systemPort);
			driver = reopenCKApp.launchEarnKaroApp(driver);

			new HomePage(driver, testInfo).
			clickOnHamburgerIcon().
			clickRetailerCategory().
			verifyRetailerCategoryMenuNavigation().
			clickAutomationSpecificCategoryInRetailerCategoryMenuUntilAllSubCategoryVisible().
			clickAutomationSpecificSubCategoryOne().
			verifyFirstCardDescription(shortDescription);

			StoreCategoryPage objDriver1 = new StoreCategoryPage(driver, testInfo);
			bannerCountAfterAddingStore = objDriver1.verifyExtractCountFromBannerTitle();

			validateTheActualValueWithExpectedValue(bannerCount + Integer.parseInt(getTestData(9, "CountOne")), bannerCountAfterAddingStore);

			CashKaroUtility cuOne = new CashKaroUtility(udid, port, automationName, deviceName);
			driver = cuOne.launchChromeWebView(driver);

			AdminCoreFunction adminFunctionsTwo = new AdminCoreFunction(driver, testInfo);
			adminFunctions = adminFunctionsTwo;
			AdminStoresPage strPageTwo = new AdminStoresPage(driver, testInfo);
			strPage = strPageTwo;
			ProductBrowserEditModePage pdtBrowserEditTwo = new ProductBrowserEditModePage(driver, testInfo);
			pdtBrowserEdit = pdtBrowserEditTwo;

			adminFunctions.naviagateAdminPage_ThenSelectEarnKaro();
			adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
			adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
			adminFunctions.clickSubmit();

			adminFunctions.clickOnHamburgerMenu();
			adminFunctions.clickStoresMenu();
			adminFunctions.clickStoresSubMenu();

			strPage.enterStoreName(storename);
			strPage.clickSubmitButton();
			strPage.clickEditButton(1);
			strPage.selectStatusFromDropDown("In-Active");
			strPage.clickSubmitButton();

			adminFunctions.clickOnHamburgerMenu();
			adminFunctions.clickProductFeedMenu();
			adminFunctions.clickProductBrowserEditModeSubMenu();
			
			pdtBrowserEdit.clickRefreshPrimaryCashaback();
			pdtBrowserEdit.clickRefreshButton();
			pdtBrowserEdit.clickOkButton();

			CashKaroUtility reopenCKAppAgain = new CashKaroUtility(udid, port, automationName, deviceName, systemPort);
			driver = reopenCKAppAgain.launchEarnKaroApp(driver);

			new HomePage(driver, testInfo).
			clickOnHamburgerIcon().
			clickRetailerCategory().
			verifyRetailerCategoryMenuNavigation().
			clickAutomationSpecificCategoryInRetailerCategoryMenuUntilAllSubCategoryVisible().
			clickAutomationSpecificSubCategoryOne();

			StoreCategoryPage objDriver2 = new StoreCategoryPage(driver, testInfo);
			bannerCountAfterAddingStore = objDriver2.verifyExtractCountFromBannerTitle();

			validateTheActualValueWithExpectedValue(Integer.parseInt(getTestData(9, "CountSix")), bannerCountAfterAddingStore);

			
		} catch (Exception e) {

			CashKaroUtility cuOne = new CashKaroUtility(udid, port, automationName, deviceName);
			driver = cuOne.launchChromeWebView(driver);

			AdminCoreFunction adminFunctions = new AdminCoreFunction(driver, testInfo);
			AdminStoresPage strPage = new AdminStoresPage(driver, testInfo);
			ProductBrowserEditModePage pdtBrowserEdit = new ProductBrowserEditModePage(driver, testInfo);

			adminFunctions.naviagateAdminPage_ThenSelectEarnKaro();
			adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
			adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
			adminFunctions.clickSubmit();

			adminFunctions.clickOnHamburgerMenu();
			adminFunctions.clickStoresMenu();
			adminFunctions.clickStoresSubMenu();

			strPage.enterStoreName(storename);
			strPage.clickSubmitButton();
			strPage.clickEditButton(1);
			strPage.selectStatusFromDropDown("In-Active");
			strPage.clickSubmitButton();

			adminFunctions.clickOnHamburgerMenu();
			adminFunctions.clickProductFeedMenu();
			adminFunctions.clickProductBrowserEditModeSubMenu();
			
			pdtBrowserEdit.clickRefreshPrimaryCashaback();
			pdtBrowserEdit.clickRefreshButton();
			pdtBrowserEdit.clickOkButton();
		}


		reportStep("TC009 is  ended", "INFO");

	}

	@Test
	public void storeCategoryPage_ValidateCountIncrementsAfterEachAddToShareClick_DecrementsAfterEachRemoveClick_TC010() {

		String storeNameOne = CashKaroUtility.extractValuesFromStoreCategoryJsonAndLoadTheVariablesForStore("CB_Store_One", 0, "CB_Store_One_Name");
		String shortDescriptionOne = CashKaroUtility.extractValuesFromStoreCategoryJsonAndLoadTheVariablesForStore("CB_Store_One", 0, "CB_Store_One_App_Short_Description");

		String storeNameTwo = CashKaroUtility.extractValuesFromStoreCategoryJsonAndLoadTheVariablesForStore("CB_Store_Two", 1, "CB_Store_Two_Name");
		String shortDescriptionTwo = CashKaroUtility.extractValuesFromStoreCategoryJsonAndLoadTheVariablesForStore("CB_Store_Two", 1, "CB_Store_Two_App_Short_Description");

		String storeNameThree = CashKaroUtility.extractValuesFromStoreCategoryJsonAndLoadTheVariablesForStore("RW_Store_Two", 3, "RW_Store_Two_Name");
		String shortDescriptionThree = CashKaroUtility.extractValuesFromStoreCategoryJsonAndLoadTheVariablesForStore("RW_Store_Two", 3, "RW_Store_Two_App_Short_Description");

		String storeNameFour = CashKaroUtility.extractValuesFromStoreCategoryJsonAndLoadTheVariablesForStore("RW_Store_One", 2, "RW_Store_One_Name");
		String shortDescriptionFour = CashKaroUtility.extractValuesFromStoreCategoryJsonAndLoadTheVariablesForStore("RW_Store_One", 2, "RW_Store_One_App_Short_Description");

		String storeNameFive = CashKaroUtility.extractValuesFromStoreCategoryJsonAndLoadTheVariablesForStore("N18_Store_Two", 5, "N18_Store_Two_Name");
		String shortDescriptionFive = CashKaroUtility.extractValuesFromStoreCategoryJsonAndLoadTheVariablesForStore("N18_Store_Two", 5, "N18_Store_Two_App_Short_Description");

		String storeNameSix = CashKaroUtility.extractValuesFromStoreCategoryJsonAndLoadTheVariablesForStore("N18_Store_One", 4, "N18_Store_One_Name");
		String shortDescriptionSix = CashKaroUtility.extractValuesFromStoreCategoryJsonAndLoadTheVariablesForStore("N18_Store_One", 4, "N18_Store_One_App_Short_Description");

		reportStep("TC003 is  started", "INFO");

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
		clickOnHamburgerIcon().
		presenceOfHomeIcon().
		clickRetailerCategory().
		clickAutomationSpecificCategoryInRetailerCategoryMenuUntilAllSubCategoryVisible().
		clickAutomationSpecificSubCategoryOne().
		clickPopularTab().
		clickOnAddToShareButton(shortDescriptionOne).
		clickOnShareIconWithCount(1).
		validateHorizontalScrollViewAbsence(2).
		clickOncloseButtonInPopup().
		clickOnAddToShareButton(shortDescriptionTwo).
		clickOnShareIconWithCount(2).
		validateHorizontalScrollViewPresence(3).
		clickOncloseButtonInPopup().
		clickOnAddToShareButton(shortDescriptionThree).
		clickOnShareIconWithCount(3).
		validateHorizontalScrollViewPresence(4).
		clickOncloseButtonInPopup().
		clickOnAddToShareButton(shortDescriptionFour).
		clickOnShareIconWithCount(4).
		validateHorizontalScrollViewPresence(5).
		clickOncloseButtonInPopup().
		clickOnAddToShareButton(shortDescriptionFive).
		clickOnShareIconWithCount(5).
		validateHorizontalScrollViewPresence(6).
		clickOncloseButtonInPopup().
		clickOnAddToShareButton(shortDescriptionSix).
		clickOnShareIconWithCount(6).
		validateHorizontalScrollViewPresence(7).
		clickOncloseButtonInPopup().
		clickPercentTab().
		clickOnShareIconWithCount(6).
		validateHorizontalScrollViewPresence(7).
		clickOncloseButtonInPopup().
		clickAmountTab().
		clickOnShareIconWithCount(6).
		validateHorizontalScrollViewPresence(7).
		clickOncloseButtonInPopup().
		clickAzTab().
		clickOnShareIconWithCount(6).
		validateHorizontalScrollViewPresence(7).
		clickOncloseButtonInPopup().
		clickPopularTab().
		clickOnRemoveButton(shortDescriptionOne).
		clickOnShareIconWithCount(5).
		validateHorizontalScrollViewPresence(6).
		clickOncloseButtonInPopup().
		clickOnRemoveButton(shortDescriptionTwo).
		clickOnShareIconWithCount(4).
		validateHorizontalScrollViewPresence(5).
		clickOncloseButtonInPopup().
		clickOnRemoveButton(shortDescriptionThree).
		clickOnShareIconWithCount(3).
		validateHorizontalScrollViewPresence(4).
		clickOncloseButtonInPopup().
		clickOnRemoveButton(shortDescriptionFour).
		clickOnShareIconWithCount(2).
		validateHorizontalScrollViewPresence(3).
		clickOncloseButtonInPopup().
		clickOnRemoveButton(shortDescriptionFive).
		clickOnShareIconWithCount(1).
		validateHorizontalScrollViewAbsence(2).
		clickOncloseButtonInPopup().
		clickOnRemoveButton(shortDescriptionSix).
		validateShareIconWithCountAbsence(1).
		clickPercentTab().
		validateShareIconWithCountAbsence(1).
		clickAmountTab().
		validateShareIconWithCountAbsence(1).
		clickAzTab().
		validateShareIconWithCountAbsence(1);

	}

	@Test 
	public void storeCategoryPage_TopSharedStoreCardsSwipeValidation_TC011() {

		String storeNameOne = CashKaroUtility.extractValuesFromStoreCategoryJsonAndLoadTheVariablesForStore("CB_Store_One", 0, "CB_Store_One_Name");
		String shortDescriptionOne = CashKaroUtility.extractValuesFromStoreCategoryJsonAndLoadTheVariablesForStore("CB_Store_One", 0, "CB_Store_One_App_Short_Description");

		String storeNameTwo = CashKaroUtility.extractValuesFromStoreCategoryJsonAndLoadTheVariablesForStore("CB_Store_Two", 1, "CB_Store_Two_Name");
		String shortDescriptionTwo = CashKaroUtility.extractValuesFromStoreCategoryJsonAndLoadTheVariablesForStore("CB_Store_Two", 1, "CB_Store_Two_App_Short_Description");

		String storeNameThree = CashKaroUtility.extractValuesFromStoreCategoryJsonAndLoadTheVariablesForStore("RW_Store_Two", 3, "RW_Store_Two_Name");
		String shortDescriptionThree = CashKaroUtility.extractValuesFromStoreCategoryJsonAndLoadTheVariablesForStore("RW_Store_Two", 3, "RW_Store_Two_App_Short_Description");

		String storeNameFour = CashKaroUtility.extractValuesFromStoreCategoryJsonAndLoadTheVariablesForStore("RW_Store_One", 2, "RW_Store_One_Name");
		String shortDescriptionFour = CashKaroUtility.extractValuesFromStoreCategoryJsonAndLoadTheVariablesForStore("RW_Store_One", 2, "RW_Store_One_App_Short_Description");

		String storeNameFive = CashKaroUtility.extractValuesFromStoreCategoryJsonAndLoadTheVariablesForStore("N18_Store_Two", 5, "N18_Store_Two_Name");
		String shortDescriptionFive = CashKaroUtility.extractValuesFromStoreCategoryJsonAndLoadTheVariablesForStore("N18_Store_Two", 5, "N18_Store_Two_App_Short_Description");

		String storeNameSix = CashKaroUtility.extractValuesFromStoreCategoryJsonAndLoadTheVariablesForStore("N18_Store_One", 4, "N18_Store_One_Name");
		String shortDescriptionSix = CashKaroUtility.extractValuesFromStoreCategoryJsonAndLoadTheVariablesForStore("N18_Store_One", 4, "N18_Store_One_App_Short_Description");

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
		objOtpPage.enterOTP(otpValue).
		clickOnVerifyOTP();

		new HomePage(driver, testInfo).
		clickOnHamburgerIcon().
		presenceOfHomeIcon().
		clickRetailerCategory()
		.clickAutomationSpecificCategoryInRetailerCategoryMenuUntilAllSubCategoryVisible()
		.clickAutomationSpecificSubCategoryOne().

		clickPopularTab().
		clickOnAddToShareButton(shortDescriptionOne).
		clickPopularTab().
		clickOnAddToShareButton(shortDescriptionTwo).
		clickPopularTab().
		clickOnAddToShareButton(shortDescriptionThree).
		clickPopularTab().
		clickOnAddToShareButton(shortDescriptionFour).
		clickPopularTab().
		clickOnAddToShareButton(shortDescriptionFive).
		clickPopularTab().
		clickOnAddToShareButton(shortDescriptionSix).
		clickOnShareIconWithCount(6);
		
		//create list
		List<Object> list = new ArrayList<>();
		
		//Add the objects to the List :
		list.add(storeNameOne);
		list.add(storeNameTwo);
		list.add(storeNameThree);
		list.add(storeNameFour);
		list.add(storeNameFive);
		list.add(storeNameSix);
		
		int iniSize = list.size(); // Get the ini List size
		String xPath = "//android.widget.TextView[@content-desc='txt_sharecount_name']";
		
		reportStep("list size is  " +  list.size(), "INFO");
		
		for(int counter=0;counter< iniSize;counter++) {
			
		isElementLocatedByXpathPresent(xPath);
			
		String elementString = driver.findElement(By.xpath(xPath)).getText();
		 
		if(list.contains(elementString)) {
			
		//System.out.println("loop in " + counter);
		
		reportStep("The store " +  list.get(0) + " is Present , when we swipe from right to left [ In the sharing Top view section ]", "PASS");
		reportStep("Removing ..! : " +  elementString + " From the list  ", "INFO");
		
		list.remove(elementString);
		
		swipeFromRightToLeft();
		
		}else {
			
			//System.out.println("loop in " + counter);
			reportStep("The store " +  list.get(0) + " is not present or visible , when we swipe from right to left in the sharing top view section ", "INFO");
			}
		}
		
		if(list.size() == 0) {
			
			reportStep("Successfully ensured that - once after swiping the top shared view store cards - all required store data were present ", "PASS");
		}
		else {
			
			reportStep("Failed - while swiping all the required store data were not listed properly - Analyse this , May be an issue/bug", "FAIL");
		}
		
		
		
	}

	@Test
	public void storeCategoryPage_ValidateCbStoreOneStoreCardValuesInStoreCategoryPageAndStoreDetailPage_TC03() {

		String storeName = CashKaroUtility.extractValuesFromStoreCategoryJsonAndLoadTheVariablesForStore("CB_Store_One", 0, "CB_Store_One_Name");
		String shortDescription = CashKaroUtility.extractValuesFromStoreCategoryJsonAndLoadTheVariablesForStore("CB_Store_One", 0, "CB_Store_One_App_Short_Description");
		String primaryProfitValue = CashKaroUtility.extractValuesFromStoreCategoryJsonAndLoadTheVariablesForStore("CB_Store_One", 0, "CB_Store_One_Primary_Profit_Value");
		String linkURL = CashKaroUtility.extractValuesFromStoreCategoryJsonAndLoadTheVariablesForStore("CB_Store_One", 0, "CB_Store_One_linkURL");
		String fullDescription = "App Full Description";
				
		reportStep("TC03 is  started", "INFO");

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

		/*******************************************************************************************************/
		
		new HomePage(driver, testInfo).
		clickOnHamburgerIcon().
		presenceOfHomeIcon().
		clickRetailerCategory().
		clickAutomationSpecificCategoryInRetailerCategoryMenuUntilAllSubCategoryVisible().
		clickAutomationSpecificSubCategoryOne().
		clickPopularTab().
		clickStoreCardImage(shortDescription).
		clickBackButton().
		clickAndVerifyStoreCardShortDescription(shortDescription).
		clickBackButton().
		clickOnAddToShareButton(shortDescription).
		clickOnShareIconWithCount(1).
		validateStoreImageInPopup(shortDescription).
		validateStoreNameInPopup(shortDescription).
		validateShortDescriptionInPopup(shortDescription).
		clickOnShareNowInPopup();

		WhatsAppUtility whatsAppUtility = new WhatsAppUtility(driver, testInfo, udid, port, automationName, deviceName, systemPort);
				
		String fullDescription_ShortenURL_One = whatsAppUtility.clickOnWhatAppMenuItemSearch().
		enterWhatsAppSearchText("Mallik").
		clickOnRequiredContact().
		clickOnSend().
		getWhatsUpSharedCaption();
		
		String fullDescription_ShortenURL_Two = whatsAppUtility.
		clickOnSend().
		getWhatsUpSharedCaption();
		
		whatsAppUtility.validateWhatsAppSharingLink(fullDescription_ShortenURL_One, fullDescription_ShortenURL_Two);
		
		whatsAppUtility.
		clickOnLastSharedLink();
		
		String retailer_Url_One = new IntermediateRetailerPage(driver, testInfo).
		validateRetailerPage(linkURL, linkURL).
		getUrlFromDefaultBrowser(linkURL);

		hideKeyboard_All_APILevel();
		backButton_WaitForFewSeconds();
		hideKeyboard_All_APILevel();
		backButton_WaitForFewSeconds();
		hideKeyboard_All_APILevel();
		backButton_WaitForFewSeconds();
		hideKeyboard_All_APILevel();
		backButton_WaitForFewSeconds();

		/*******************************************************************************************************/
		
		new StoreCategoryPage(driver, testInfo).
		clickOnShareIconWithCount(1).
		clickOnCopyLink().
		clickBackButton_StoreCategoryPage().
		clickOnShareIconWithCount(1).
		clickOncloseButtonInPopup().
		validateShareIconWithCountPresence(1).
		clickOnShareIconWithCount(1).
		clickOnclearAllButtonInPopup().
		clickOnAddToShareButton(shortDescription).
		validateShareIconWithCountPresence(1).
		clickOnRemoveButton(shortDescription).
		validateShareIconWithCountAbsence(1).
		clickOnShareNowButton(shortDescription);
		
		String fullDescription_ShortenURL_Three = whatsAppUtility.clickOnWhatAppMenuItemSearch().
		enterWhatsAppSearchText("Mallik").
		clickOnRequiredContact().
		clickOnSend().
		getWhatsUpSharedCaption();
		
		String fullDescription_ShortenURL_Four = whatsAppUtility.
		clickOnSend().
		getWhatsUpSharedCaption();
		
		whatsAppUtility.validateWhatsAppSharingLink(fullDescription_ShortenURL_Three, fullDescription_ShortenURL_Four);
		
		whatsAppUtility.
		clickOnLastSharedLink();
		
		String retailer_Url_Two = new IntermediateRetailerPage(driver, testInfo).
		validateRetailerPage(linkURL, linkURL).
		getUrlFromDefaultBrowser(linkURL);

		hideKeyboard_All_APILevel();
		backButton_WaitForFewSeconds();
		hideKeyboard_All_APILevel();
		backButton_WaitForFewSeconds();
		hideKeyboard_All_APILevel();
		backButton_WaitForFewSeconds();
		hideKeyboard_All_APILevel();
		backButton_WaitForFewSeconds();

		/*******************************************************************************************************/
		
		new StoreCategoryPage(driver,testInfo).
		clickOnShareIcon(shortDescription).
		clickOnWhatsApp();

		String fullDescription_ShortenURL_Five = whatsAppUtility.clickOnWhatAppMenuItemSearch().
		enterWhatsAppSearchText("Mallik").
		clickOnRequiredContact().
		clickOnSend().
		getWhatsUpSharedCaption();
		
		String fullDescription_ShortenURL_Six = whatsAppUtility.
		clickOnSend().
		getWhatsUpSharedCaption();
		
		whatsAppUtility.validateWhatsAppSharingLink(fullDescription_ShortenURL_Five, fullDescription_ShortenURL_Six);
		
		whatsAppUtility.
		clickOnLastSharedLink();
		
		String retailer_Url_Three = new IntermediateRetailerPage(driver, testInfo).
		validateRetailerPage(linkURL, linkURL).
		getUrlFromDefaultBrowser(linkURL);

		/*******************************************************************************************************/

		String shortenURL = getClipBoardText();

		CashKaroUtility cu = new CashKaroUtility(udid, port, automationName, deviceName);
		 driver = cu.launchChrome();

		cu.closeAllTabsAndOneTabInChromeBrowser(driver);
		cu.clickAndEnterURLInSearchOrTypeWebAddress(driver, shortenURL);

		String retailer_Url_Four = new IntermediateRetailerPage(driver, testInfo).
		validateRetailerPage(linkURL, linkURL).
		getUrlFromDefaultBrowser(linkURL);

		/*******************************************************************************************************/
		
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

		/*******************************************************************************************************/
		
		String sharedID_One = reportsPage.extractRequiredExitIdUsingStoreNameExitClickType(storeName, "Shared", 4);
		String normalID_One = reportsPage.extractRequiredExitIdUsingStoreNameExitClickTypeSharedId(storeName, sharedID_One);
		
		String sharedID_Two = reportsPage.extractRequiredExitIdUsingStoreNameExitClickType(storeName, "Shared", 3);
		String normalID_Two = reportsPage.extractRequiredExitIdUsingStoreNameExitClickTypeSharedId(storeName, sharedID_Two);
		
		String sharedID_Three = reportsPage.extractRequiredExitIdUsingStoreNameExitClickType(storeName, "Shared", 2);
		String normalID_Three = reportsPage.extractRequiredExitIdUsingStoreNameExitClickTypeSharedId(storeName, sharedID_Three);
		
		String sharedID_Four = reportsPage.extractRequiredExitIdUsingStoreNameExitClickType(storeName, "Shared", 1);
		String normalID_Four = reportsPage.extractRequiredExitIdUsingStoreNameExitClickTypeSharedId(storeName, sharedID_Four);
		
		/*******************************************************************************************************/
		
		validateTheActualValueContainsExpectedValue(fullDescription_ShortenURL_One, fullDescription);
		validateTheActualValueContainsExpectedValue(fullDescription_ShortenURL_One, getTestData(7, "shortenURL")+sharedID_One.toLowerCase());
		
		validateTheActualValueContainsExpectedValue(fullDescription_ShortenURL_Two, fullDescription);
		validateTheActualValueContainsExpectedValue(fullDescription_ShortenURL_Two, getTestData(7, "shortenURL")+sharedID_One.toLowerCase());

		validateTheActualValueContainsExpectedValue(retailer_Url_One.toLowerCase(), normalID_One.toLowerCase());
		
		/*******************************************************************************************************/
		
		validateTheActualValueContainsExpectedValue(shortenURL, getTestData(7, "shortenURL")+sharedID_Two.toLowerCase());
		validateTheActualValueContainsExpectedValue(retailer_Url_Four.toLowerCase(), normalID_Two.toLowerCase());
		
		/*******************************************************************************************************/
		
		validateTheActualValueContainsExpectedValue(fullDescription_ShortenURL_Three, fullDescription);
		validateTheActualValueContainsExpectedValue(fullDescription_ShortenURL_Three, getTestData(7, "shortenURL")+sharedID_Three.toLowerCase());
		
		validateTheActualValueContainsExpectedValue(fullDescription_ShortenURL_Four, fullDescription);
		validateTheActualValueContainsExpectedValue(fullDescription_ShortenURL_Four, getTestData(7, "shortenURL")+sharedID_Three.toLowerCase());

		validateTheActualValueContainsExpectedValue(retailer_Url_Three.toLowerCase(), normalID_Four.toLowerCase());
		
		/*******************************************************************************************************/
		
		validateTheActualValueContainsExpectedValue(fullDescription_ShortenURL_Five, fullDescription);
		validateTheActualValueContainsExpectedValue(fullDescription_ShortenURL_Five, getTestData(7, "shortenURL")+sharedID_Four.toLowerCase());
		
		validateTheActualValueContainsExpectedValue(fullDescription_ShortenURL_Six, fullDescription);
		validateTheActualValueContainsExpectedValue(fullDescription_ShortenURL_Six, getTestData(7, "shortenURL")+sharedID_Four.toLowerCase());

		validateTheActualValueContainsExpectedValue(retailer_Url_Two.toLowerCase(), normalID_Three.toLowerCase());

		/*******************************************************************************************************/

		CashKaroUtility reopenCKApp = new CashKaroUtility(udid, port, automationName, deviceName, systemPort);
		driver = reopenCKApp.launchEarnKaroApp(driver);

		/*******************************************************************************************************/

		new HomePage(driver, testInfo).
		clickOnHamburgerIcon().
		presenceOfHomeIcon().
		clickRetailerCategory().
		clickAutomationSpecificCategoryInRetailerCategoryMenuUntilAllSubCategoryVisible().
		clickAutomationSpecificSubCategoryOne().
		clickPopularTab().
		clickAndVerifyStoreCardShortDescription(shortDescription).
		validateStoreImage(shortDescription).
		validateStoreNameInPopup(storeName).
		validateShortDescriptionInPopup(storeName).
		validateprimaryProfit(primaryProfitValue).
		clickOnAddToShareButton().
		clickOnShareIconWithCount(1).
		validateStoreImageInPopup(shortDescription).
		validateStoreNameInPopup(shortDescription).
		validateShortDescriptionInPopup(shortDescription).		
		clickOnShareNowInPopup();

		WhatsAppUtility whatsAppUtilityOne = new WhatsAppUtility(driver, testInfo, udid, port, automationName, deviceName, systemPort);
	
		String fullDescription_ShortenURL_Seven = whatsAppUtilityOne.clickOnWhatAppMenuItemSearch().
		enterWhatsAppSearchText("Mallik").
		clickOnRequiredContact().
		clickOnSend().
		getWhatsUpSharedCaption();
		
		String fullDescription_ShortenURL_Eight = whatsAppUtilityOne.
		clickOnSend().
		getWhatsUpSharedCaption();
		
		whatsAppUtilityOne.
		clickOnLastSharedLink();
		
		String retailer_Url_Five = new IntermediateRetailerPage(driver, testInfo).
		validateRetailerPage(linkURL, linkURL).
		getUrlFromDefaultBrowser(linkURL);

		hideKeyboard_All_APILevel();
		backButton_WaitForFewSeconds();
		hideKeyboard_All_APILevel();
		backButton_WaitForFewSeconds();
		hideKeyboard_All_APILevel();
		backButton_WaitForFewSeconds();
		hideKeyboard_All_APILevel();
		backButton_WaitForFewSeconds();

		/*******************************************************************************************************/

		new StoreDetailPage(driver, testInfo).
		clickOnRemoveButton().
		clickOnAddToShareButton().
		clickOnShareIconWithCount(1).
		clickOnCopyLink().
		clickBackButton_StoreDetailPage().
		clickOnShareIconWithCount(1).
		clickOncloseButtonInPopup().
		clickOnShareIconWithCount(1).
		clickOnclearAllButtonInPopup().
		validateShareIconWithCountAbsence(1).
		clickOnAddToShareButton().
		validateShareIconWithCountPresence(1).
		clickOnRemoveButton().
		validateShareIconWithCountAbsence(1).
 		clickOnAddToShareButton().
		clickOnShareNow();
	
		String fullDescription_ShortenURL_Nine = whatsAppUtilityOne.clickOnWhatAppMenuItemSearch().
		enterWhatsAppSearchText("Mallik").
		clickOnRequiredContact().
		clickOnSend().
		getWhatsUpSharedCaption();
		
		String fullDescription_ShortenURL_Ten = whatsAppUtilityOne.
		clickOnSend().
		getWhatsUpSharedCaption();
		
		whatsAppUtilityOne.
		clickOnLastSharedLink();
		
		String retailer_Url_Six = new IntermediateRetailerPage(driver, testInfo).
		validateRetailerPage(linkURL, linkURL).
		getUrlFromDefaultBrowser(linkURL);

		hideKeyboard_All_APILevel();
		backButton_WaitForFewSeconds();
		hideKeyboard_All_APILevel();
		backButton_WaitForFewSeconds();
		hideKeyboard_All_APILevel();
		backButton_WaitForFewSeconds();
		hideKeyboard_All_APILevel();
		backButton_WaitForFewSeconds();

		/*******************************************************************************************************/

		new StoreDetailPage(driver,testInfo).
		clickOnShareIcon().
		clickOnWhatsApp();

		String fullDescription_ShortenURL_Eleven = whatsAppUtilityOne.clickOnWhatAppMenuItemSearch().
		enterWhatsAppSearchText("Mallik").
		clickOnRequiredContact().
		clickOnSend().
		getWhatsUpSharedCaption();
		
		String fullDescription_ShortenURL_Twelve = whatsAppUtilityOne.
		clickOnSend().
		getWhatsUpSharedCaption();
		
		whatsAppUtilityOne.
		clickOnLastSharedLink();
		
		String retailer_Url_Seven = new IntermediateRetailerPage(driver, testInfo).
		validateRetailerPage(linkURL, linkURL).
		getUrlFromDefaultBrowser(linkURL);

		hideKeyboard_All_APILevel();
		backButton_WaitForFewSeconds();
		hideKeyboard_All_APILevel();
		backButton_WaitForFewSeconds();
		hideKeyboard_All_APILevel();
		backButton_WaitForFewSeconds();
		hideKeyboard_All_APILevel();
		backButton_WaitForFewSeconds();

		/*******************************************************************************************************/

		String shortenURL_Two = getClipBoardText(driver);

		CashKaroUtility cu1 = new CashKaroUtility(udid, port, automationName, deviceName);
		AppiumDriver<MobileElement> driver1 = cu1.launchChrome();
		driver = driver1;
		cu = cu1;

		cu.closeAllTabsAndOneTabInChromeBrowser(driver);
		cu.clickAndEnterURLInSearchOrTypeWebAddress(driver, shortenURL_Two);

		String retailer_Url_Eight = new IntermediateRetailerPage(driver, testInfo).
		validateRetailerPage(linkURL, linkURL).
		getUrlFromDefaultBrowser(linkURL);

		hideKeyboard_All_APILevel();
		backButton_WaitForFewSeconds();
		hideKeyboard_All_APILevel();
		backButton_WaitForFewSeconds();
		hideKeyboard_All_APILevel();
		backButton_WaitForFewSeconds();
		hideKeyboard_All_APILevel();
		backButton_WaitForFewSeconds();
		
		/*******************************************************************************************************/
		
		CashKaroUtility cuTwo = new CashKaroUtility(udid, port, automationName, deviceName);
		driver = cuTwo.launchChromeWebView(driver);

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

		/*******************************************************************************************************/
		
		String sharedID_Five = reportsPage.extractRequiredExitIdUsingStoreNameExitClickType(storeName, "Shared", 4);
		String normalID_Five = reportsPage.extractRequiredExitIdUsingStoreNameExitClickTypeSharedId(storeName, sharedID_Five);
		
		String sharedID_Six = reportsPage.extractRequiredExitIdUsingStoreNameExitClickType(storeName, "Shared", 3);
		String normalID_Six = reportsPage.extractRequiredExitIdUsingStoreNameExitClickTypeSharedId(storeName, sharedID_Six);
		
		String sharedID_Seven = reportsPage.extractRequiredExitIdUsingStoreNameExitClickType(storeName, "Shared", 2);
		String normalID_Seven = reportsPage.extractRequiredExitIdUsingStoreNameExitClickTypeSharedId(storeName, sharedID_Seven);
		
		String sharedID_Eight = reportsPage.extractRequiredExitIdUsingStoreNameExitClickType(storeName, "Shared", 1);
		String normalID_Eight = reportsPage.extractRequiredExitIdUsingStoreNameExitClickTypeSharedId(storeName, sharedID_Eight);
		
		/*******************************************************************************************************/
		
		validateTheActualValueContainsExpectedValue(fullDescription_ShortenURL_Seven, fullDescription);
		validateTheActualValueContainsExpectedValue(fullDescription_ShortenURL_Seven, getTestData(7, "shortenURL")+sharedID_Five.toLowerCase());
		
		validateTheActualValueContainsExpectedValue(fullDescription_ShortenURL_Eight, fullDescription);
		validateTheActualValueContainsExpectedValue(fullDescription_ShortenURL_Eight, getTestData(7, "shortenURL")+sharedID_Five.toLowerCase());

		validateTheActualValueContainsExpectedValue(retailer_Url_Five.toLowerCase(), normalID_Five.toLowerCase());
		
		/*******************************************************************************************************/
		
		validateTheActualValueContainsExpectedValue(shortenURL_Two, getTestData(7, "shortenURL")+sharedID_Six.toLowerCase());
		validateTheActualValueContainsExpectedValue(retailer_Url_Eight.toLowerCase(), normalID_Six.toLowerCase());
		
		/*******************************************************************************************************/
		
		validateTheActualValueContainsExpectedValue(fullDescription_ShortenURL_Nine, fullDescription);
		validateTheActualValueContainsExpectedValue(fullDescription_ShortenURL_Nine, getTestData(7, "shortenURL")+sharedID_Seven.toLowerCase());
		
		validateTheActualValueContainsExpectedValue(fullDescription_ShortenURL_Ten, fullDescription);
		validateTheActualValueContainsExpectedValue(fullDescription_ShortenURL_Ten, getTestData(7, "shortenURL")+sharedID_Seven.toLowerCase());

		validateTheActualValueContainsExpectedValue(retailer_Url_Seven.toLowerCase(), normalID_Eight.toLowerCase());
		
		/*******************************************************************************************************/
		
		validateTheActualValueContainsExpectedValue(fullDescription_ShortenURL_Eleven, fullDescription);
		validateTheActualValueContainsExpectedValue(fullDescription_ShortenURL_Eleven, getTestData(7, "shortenURL")+sharedID_Eight.toLowerCase());
		
		validateTheActualValueContainsExpectedValue(fullDescription_ShortenURL_Twelve, fullDescription);
		validateTheActualValueContainsExpectedValue(fullDescription_ShortenURL_Twelve, getTestData(7, "shortenURL")+sharedID_Eight.toLowerCase());

		validateTheActualValueContainsExpectedValue(retailer_Url_Six.toLowerCase(), normalID_Seven.toLowerCase());
		
		/*******************************************************************************************************/
	}

	@Test
	public void storeCategoryPage_ValidateCbStoreTwoStoreCardValuesInStoreCategoryPageAndStoreDetailPage_TC04() {

		String storeName = CashKaroUtility.extractValuesFromStoreCategoryJsonAndLoadTheVariablesForStore("CB_Store_Two", 1, "CB_Store_Two_Name");
		String shortDescription = CashKaroUtility.extractValuesFromStoreCategoryJsonAndLoadTheVariablesForStore("CB_Store_Two", 1, "CB_Store_Two_App_Short_Description");
		String primaryProfitValue = CashKaroUtility.extractValuesFromStoreCategoryJsonAndLoadTheVariablesForStore("CB_Store_Two", 1, "CB_Store_Two_Primary_Profit_Value");
		String linkURL = CashKaroUtility.extractValuesFromStoreCategoryJsonAndLoadTheVariablesForStore("CB_Store_Two", 1, "CB_Store_Two_linkURL");
		String fullDescription = "App Full Description";
				
		reportStep("TC03 is  started", "INFO");

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

		/*******************************************************************************************************/
		
		new HomePage(driver, testInfo).
		clickOnHamburgerIcon().
		presenceOfHomeIcon().
		clickRetailerCategory().
		clickAutomationSpecificCategoryInRetailerCategoryMenuUntilAllSubCategoryVisible().
		clickAutomationSpecificSubCategoryOne().
		clickPopularTab().
		clickStoreCardImage(shortDescription).
		clickBackButton().
		clickAndVerifyStoreCardShortDescription(shortDescription).
		clickBackButton().
		clickOnAddToShareButton(shortDescription).
		clickOnShareIconWithCount(1).
		validateStoreImageInPopup(shortDescription).
		validateStoreNameInPopup(shortDescription).
		validateShortDescriptionInPopup(shortDescription).
		clickOnShareNowInPopup();

		WhatsAppUtility whatsAppUtility = new WhatsAppUtility(driver, testInfo, udid, port, automationName, deviceName, systemPort);
				
		String fullDescription_ShortenURL_One = whatsAppUtility.clickOnWhatAppMenuItemSearch().
		enterWhatsAppSearchText("Mallik").
		clickOnRequiredContact().
		clickOnSend().
		getWhatsUpSharedCaption();
		
		String fullDescription_ShortenURL_Two = whatsAppUtility.
		clickOnSend().
		getWhatsUpSharedCaption();
		
		whatsAppUtility.
		clickOnLastSharedLink();
		
		String retailer_Url_One = new IntermediateRetailerPage(driver, testInfo).
		validateRetailerPage(linkURL, linkURL).
		getUrlFromDefaultBrowser(linkURL);

		hideKeyboard_All_APILevel();
		backButton_WaitForFewSeconds();
		hideKeyboard_All_APILevel();
		backButton_WaitForFewSeconds();
		hideKeyboard_All_APILevel();
		backButton_WaitForFewSeconds();
		hideKeyboard_All_APILevel();
		backButton_WaitForFewSeconds();

		/*******************************************************************************************************/
		
		new StoreCategoryPage(driver, testInfo).
		clickOnShareIconWithCount(1).
		clickOnCopyLink().
		clickBackButton_StoreCategoryPage().
		clickOnShareIconWithCount(1).
		clickOncloseButtonInPopup().
		validateShareIconWithCountPresence(1).
		clickOnShareIconWithCount(1).
		clickOnclearAllButtonInPopup().
		clickOnAddToShareButton(shortDescription).
		validateShareIconWithCountPresence(1).
		clickOnRemoveButton(shortDescription).
		validateShareIconWithCountAbsence(1).
		clickOnShareNowButton(shortDescription);
		
		String fullDescription_ShortenURL_Three = whatsAppUtility.clickOnWhatAppMenuItemSearch().
		enterWhatsAppSearchText("Mallik").
		clickOnRequiredContact().
		clickOnSend().
		getWhatsUpSharedCaption();
		
		String fullDescription_ShortenURL_Four = whatsAppUtility.
		clickOnSend().
		getWhatsUpSharedCaption();
		
		whatsAppUtility.
		clickOnLastSharedLink();
		
		String retailer_Url_Two = new IntermediateRetailerPage(driver, testInfo).
		validateRetailerPage(linkURL, linkURL).
		getUrlFromDefaultBrowser(linkURL);

		hideKeyboard_All_APILevel();
		backButton_WaitForFewSeconds();
		hideKeyboard_All_APILevel();
		backButton_WaitForFewSeconds();
		hideKeyboard_All_APILevel();
		backButton_WaitForFewSeconds();
		hideKeyboard_All_APILevel();
		backButton_WaitForFewSeconds();

		/*******************************************************************************************************/
		
		new StoreCategoryPage(driver,testInfo).
		clickOnShareIcon(shortDescription).
		clickOnWhatsApp();

		String fullDescription_ShortenURL_Five = whatsAppUtility.clickOnWhatAppMenuItemSearch().
		enterWhatsAppSearchText("Mallik").
		clickOnRequiredContact().
		clickOnSend().
		getWhatsUpSharedCaption();
		
		String fullDescription_ShortenURL_Six = whatsAppUtility.
		clickOnSend().
		getWhatsUpSharedCaption();
		
		whatsAppUtility.
		clickOnLastSharedLink();
		
		String retailer_Url_Three = new IntermediateRetailerPage(driver, testInfo).
		validateRetailerPage(linkURL, linkURL).
		getUrlFromDefaultBrowser(linkURL);

		/*******************************************************************************************************/

		String shortenURL = getClipBoardText();

		CashKaroUtility cu = new CashKaroUtility(udid, port, automationName, deviceName);
		 driver = cu.launchChrome();

		cu.closeAllTabsAndOneTabInChromeBrowser(driver);
		cu.clickAndEnterURLInSearchOrTypeWebAddress(driver, shortenURL);

		String retailer_Url_Four = new IntermediateRetailerPage(driver, testInfo).
		validateRetailerPage(linkURL, linkURL).
		getUrlFromDefaultBrowser(linkURL);

		/*******************************************************************************************************/
		
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

		/*******************************************************************************************************/
		
		String sharedID_One = reportsPage.extractRequiredExitIdUsingStoreNameExitClickType(storeName, "Shared", 4);
		String normalID_One = reportsPage.extractRequiredExitIdUsingStoreNameExitClickTypeSharedId(storeName, sharedID_One);
		
		String sharedID_Two = reportsPage.extractRequiredExitIdUsingStoreNameExitClickType(storeName, "Shared", 3);
		String normalID_Two = reportsPage.extractRequiredExitIdUsingStoreNameExitClickTypeSharedId(storeName, sharedID_Two);
		
		String sharedID_Three = reportsPage.extractRequiredExitIdUsingStoreNameExitClickType(storeName, "Shared", 2);
		String normalID_Three = reportsPage.extractRequiredExitIdUsingStoreNameExitClickTypeSharedId(storeName, sharedID_Three);
		
		String sharedID_Four = reportsPage.extractRequiredExitIdUsingStoreNameExitClickType(storeName, "Shared", 1);
		String normalID_Four = reportsPage.extractRequiredExitIdUsingStoreNameExitClickTypeSharedId(storeName, sharedID_Four);
		
		/*******************************************************************************************************/
		
		validateTheActualValueContainsExpectedValue(fullDescription_ShortenURL_One, fullDescription);
		validateTheActualValueContainsExpectedValue(fullDescription_ShortenURL_One, getTestData(7, "shortenURL")+sharedID_One.toLowerCase());
		
		validateTheActualValueContainsExpectedValue(fullDescription_ShortenURL_Two, fullDescription);
		validateTheActualValueContainsExpectedValue(fullDescription_ShortenURL_Two, getTestData(7, "shortenURL")+sharedID_One.toLowerCase());

		validateTheActualValueContainsExpectedValue(retailer_Url_One.toLowerCase(), normalID_One.toLowerCase());
		
		/*******************************************************************************************************/
		
		validateTheActualValueContainsExpectedValue(shortenURL, getTestData(7, "shortenURL")+sharedID_Two.toLowerCase());
		validateTheActualValueContainsExpectedValue(retailer_Url_Four.toLowerCase(), normalID_Two.toLowerCase());
		
		/*******************************************************************************************************/
		
		validateTheActualValueContainsExpectedValue(fullDescription_ShortenURL_Three, fullDescription);
		validateTheActualValueContainsExpectedValue(fullDescription_ShortenURL_Three, getTestData(7, "shortenURL")+sharedID_Three.toLowerCase());
		
		validateTheActualValueContainsExpectedValue(fullDescription_ShortenURL_Four, fullDescription);
		validateTheActualValueContainsExpectedValue(fullDescription_ShortenURL_Four, getTestData(7, "shortenURL")+sharedID_Three.toLowerCase());

		validateTheActualValueContainsExpectedValue(retailer_Url_Three.toLowerCase(), normalID_Four.toLowerCase());
		
		/*******************************************************************************************************/
		
		validateTheActualValueContainsExpectedValue(fullDescription_ShortenURL_Five, fullDescription);
		validateTheActualValueContainsExpectedValue(fullDescription_ShortenURL_Five, getTestData(7, "shortenURL")+sharedID_Four.toLowerCase());
		
		validateTheActualValueContainsExpectedValue(fullDescription_ShortenURL_Six, fullDescription);
		validateTheActualValueContainsExpectedValue(fullDescription_ShortenURL_Six, getTestData(7, "shortenURL")+sharedID_Four.toLowerCase());

		validateTheActualValueContainsExpectedValue(retailer_Url_Two.toLowerCase(), normalID_Three.toLowerCase());

		/*******************************************************************************************************/

		CashKaroUtility reopenCKApp = new CashKaroUtility(udid, port, automationName, deviceName, systemPort);
		driver = reopenCKApp.launchEarnKaroApp(driver);

		/*******************************************************************************************************/

		new HomePage(driver, testInfo).
		clickOnHamburgerIcon().
		presenceOfHomeIcon().
		clickRetailerCategory().
		clickAutomationSpecificCategoryInRetailerCategoryMenuUntilAllSubCategoryVisible().
		clickAutomationSpecificSubCategoryOne().
		clickPopularTab().
		clickAndVerifyStoreCardShortDescription(shortDescription).
		validateStoreImage(shortDescription).
		validateStoreNameInPopup(storeName).
		validateShortDescriptionInPopup(storeName).
		validateprimaryProfit(primaryProfitValue).
		clickOnAddToShareButton().
		clickOnShareIconWithCount(1).
		validateStoreImageInPopup(shortDescription).
		validateStoreNameInPopup(shortDescription).
		validateShortDescriptionInPopup(shortDescription).		
		clickOnShareNowInPopup();

		WhatsAppUtility whatsAppUtilityOne = new WhatsAppUtility(driver, testInfo, udid, port, automationName, deviceName, systemPort);
	
		String fullDescription_ShortenURL_Seven = whatsAppUtilityOne.clickOnWhatAppMenuItemSearch().
		enterWhatsAppSearchText("Mallik").
		clickOnRequiredContact().
		clickOnSend().
		getWhatsUpSharedCaption();
		
		String fullDescription_ShortenURL_Eight = whatsAppUtilityOne.
		clickOnSend().
		getWhatsUpSharedCaption();
		
		whatsAppUtilityOne.
		clickOnLastSharedLink();
		
		String retailer_Url_Five = new IntermediateRetailerPage(driver, testInfo).
		validateRetailerPage(linkURL, linkURL).
		getUrlFromDefaultBrowser(linkURL);

		hideKeyboard_All_APILevel();
		backButton_WaitForFewSeconds();
		hideKeyboard_All_APILevel();
		backButton_WaitForFewSeconds();
		hideKeyboard_All_APILevel();
		backButton_WaitForFewSeconds();
		hideKeyboard_All_APILevel();
		backButton_WaitForFewSeconds();

		/*******************************************************************************************************/

		new StoreDetailPage(driver, testInfo).
		clickOnRemoveButton().
		clickOnAddToShareButton().
		clickOnShareIconWithCount(1).
		clickOnCopyLink().
		clickBackButton_StoreDetailPage().
		clickOnShareIconWithCount(1).
		clickOncloseButtonInPopup().
		clickOnShareIconWithCount(1).
		clickOnclearAllButtonInPopup().
		validateShareIconWithCountAbsence(1).
		clickOnAddToShareButton().
		validateShareIconWithCountPresence(1).
		clickOnRemoveButton().
		validateShareIconWithCountAbsence(1).
 		clickOnAddToShareButton().
		clickOnShareNow();
	
		String fullDescription_ShortenURL_Nine = whatsAppUtilityOne.clickOnWhatAppMenuItemSearch().
		enterWhatsAppSearchText("Mallik").
		clickOnRequiredContact().
		clickOnSend().
		getWhatsUpSharedCaption();
		
		String fullDescription_ShortenURL_Ten = whatsAppUtilityOne.
		clickOnSend().
		getWhatsUpSharedCaption();
		
		whatsAppUtilityOne.
		clickOnLastSharedLink();
		
		String retailer_Url_Six = new IntermediateRetailerPage(driver, testInfo).
		validateRetailerPage(linkURL, linkURL).
		getUrlFromDefaultBrowser(linkURL);

		hideKeyboard_All_APILevel();
		backButton_WaitForFewSeconds();
		hideKeyboard_All_APILevel();
		backButton_WaitForFewSeconds();
		hideKeyboard_All_APILevel();
		backButton_WaitForFewSeconds();
		hideKeyboard_All_APILevel();
		backButton_WaitForFewSeconds();

		/*******************************************************************************************************/

		new StoreDetailPage(driver,testInfo).
		clickOnShareIcon().
		clickOnWhatsApp();

		String fullDescription_ShortenURL_Eleven = whatsAppUtilityOne.clickOnWhatAppMenuItemSearch().
		enterWhatsAppSearchText("Mallik").
		clickOnRequiredContact().
		clickOnSend().
		getWhatsUpSharedCaption();
		
		String fullDescription_ShortenURL_Twelve = whatsAppUtilityOne.
		clickOnSend().
		getWhatsUpSharedCaption();
		
		whatsAppUtilityOne.
		clickOnLastSharedLink();
		
		String retailer_Url_Seven = new IntermediateRetailerPage(driver, testInfo).
		validateRetailerPage(linkURL, linkURL).
		getUrlFromDefaultBrowser(linkURL);

		hideKeyboard_All_APILevel();
		backButton_WaitForFewSeconds();
		hideKeyboard_All_APILevel();
		backButton_WaitForFewSeconds();
		hideKeyboard_All_APILevel();
		backButton_WaitForFewSeconds();
		hideKeyboard_All_APILevel();
		backButton_WaitForFewSeconds();

		/*******************************************************************************************************/

		String shortenURL_Two = getClipBoardText(driver);

		CashKaroUtility cu1 = new CashKaroUtility(udid, port, automationName, deviceName);
		AppiumDriver<MobileElement> driver1 = cu1.launchChrome();
		driver = driver1;
		cu = cu1;

		cu.closeAllTabsAndOneTabInChromeBrowser(driver);
		cu.clickAndEnterURLInSearchOrTypeWebAddress(driver, shortenURL_Two);

		String retailer_Url_Eight = new IntermediateRetailerPage(driver, testInfo).
		validateRetailerPage(linkURL, linkURL).
		getUrlFromDefaultBrowser(linkURL);

		hideKeyboard_All_APILevel();
		backButton_WaitForFewSeconds();
		hideKeyboard_All_APILevel();
		backButton_WaitForFewSeconds();
		hideKeyboard_All_APILevel();
		backButton_WaitForFewSeconds();
		hideKeyboard_All_APILevel();
		backButton_WaitForFewSeconds();
		
		/*******************************************************************************************************/
		
		CashKaroUtility cuTwo = new CashKaroUtility(udid, port, automationName, deviceName);
		driver = cuTwo.launchChromeWebView(driver);

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

		/*******************************************************************************************************/
		
		String sharedID_Five = reportsPage.extractRequiredExitIdUsingStoreNameExitClickType(storeName, "Shared", 4);
		String normalID_Five = reportsPage.extractRequiredExitIdUsingStoreNameExitClickTypeSharedId(storeName, sharedID_Five);
		
		String sharedID_Six = reportsPage.extractRequiredExitIdUsingStoreNameExitClickType(storeName, "Shared", 3);
		String normalID_Six = reportsPage.extractRequiredExitIdUsingStoreNameExitClickTypeSharedId(storeName, sharedID_Six);
		
		String sharedID_Seven = reportsPage.extractRequiredExitIdUsingStoreNameExitClickType(storeName, "Shared", 2);
		String normalID_Seven = reportsPage.extractRequiredExitIdUsingStoreNameExitClickTypeSharedId(storeName, sharedID_Seven);
		
		String sharedID_Eight = reportsPage.extractRequiredExitIdUsingStoreNameExitClickType(storeName, "Shared", 1);
		String normalID_Eight = reportsPage.extractRequiredExitIdUsingStoreNameExitClickTypeSharedId(storeName, sharedID_Eight);
		
		/*******************************************************************************************************/
		
		validateTheActualValueContainsExpectedValue(fullDescription_ShortenURL_Seven, fullDescription);
		validateTheActualValueContainsExpectedValue(fullDescription_ShortenURL_Seven, getTestData(7, "shortenURL")+sharedID_Five.toLowerCase());
		
		validateTheActualValueContainsExpectedValue(fullDescription_ShortenURL_Eight, fullDescription);
		validateTheActualValueContainsExpectedValue(fullDescription_ShortenURL_Eight, getTestData(7, "shortenURL")+sharedID_Five.toLowerCase());

		validateTheActualValueContainsExpectedValue(retailer_Url_Five.toLowerCase(), normalID_Five.toLowerCase());
		
		/*******************************************************************************************************/
		
		validateTheActualValueContainsExpectedValue(shortenURL_Two, getTestData(7, "shortenURL")+sharedID_Six.toLowerCase());
		validateTheActualValueContainsExpectedValue(retailer_Url_Eight.toLowerCase(), normalID_Six.toLowerCase());
		
		/*******************************************************************************************************/
		
		validateTheActualValueContainsExpectedValue(fullDescription_ShortenURL_Nine, fullDescription);
		validateTheActualValueContainsExpectedValue(fullDescription_ShortenURL_Nine, getTestData(7, "shortenURL")+sharedID_Seven.toLowerCase());
		
		validateTheActualValueContainsExpectedValue(fullDescription_ShortenURL_Ten, fullDescription);
		validateTheActualValueContainsExpectedValue(fullDescription_ShortenURL_Ten, getTestData(7, "shortenURL")+sharedID_Seven.toLowerCase());

		validateTheActualValueContainsExpectedValue(retailer_Url_Seven.toLowerCase(), normalID_Eight.toLowerCase());
		
		/*******************************************************************************************************/
		
		validateTheActualValueContainsExpectedValue(fullDescription_ShortenURL_Eleven, fullDescription);
		validateTheActualValueContainsExpectedValue(fullDescription_ShortenURL_Eleven, getTestData(7, "shortenURL")+sharedID_Eight.toLowerCase());
		
		validateTheActualValueContainsExpectedValue(fullDescription_ShortenURL_Twelve, fullDescription);
		validateTheActualValueContainsExpectedValue(fullDescription_ShortenURL_Twelve, getTestData(7, "shortenURL")+sharedID_Eight.toLowerCase());

		validateTheActualValueContainsExpectedValue(retailer_Url_Six.toLowerCase(), normalID_Seven.toLowerCase());
		
		/*******************************************************************************************************/

	}

	@Test
	public void storeCategoryPage_ValidateRwStoreTwoStoreCardValuesInStoreCategoryPageAndStoreDetailPage_TC05() {

		String storeName = CashKaroUtility.extractValuesFromStoreCategoryJsonAndLoadTheVariablesForStore("RW_Store_Two", 3, "RW_Store_Two_Name");
		String shortDescription = CashKaroUtility.extractValuesFromStoreCategoryJsonAndLoadTheVariablesForStore("RW_Store_Two", 3, "RW_Store_Two_App_Short_Description");
		String primaryProfitValue = CashKaroUtility.extractValuesFromStoreCategoryJsonAndLoadTheVariablesForStore("RW_Store_Two", 3, "RW_Store_Two_Primary_Profit_Value");
		String linkURL = CashKaroUtility.extractValuesFromStoreCategoryJsonAndLoadTheVariablesForStore("RW_Store_Two", 3, "RW_Store_Two_linkURL");
		String fullDescription = "App Full Description";
				
		reportStep("TC03 is  started", "INFO");

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

		/*******************************************************************************************************/
		
		new HomePage(driver, testInfo).
		clickOnHamburgerIcon().
		presenceOfHomeIcon().
		clickRetailerCategory().
		clickAutomationSpecificCategoryInRetailerCategoryMenuUntilAllSubCategoryVisible().
		clickAutomationSpecificSubCategoryOne().
		clickPopularTab().
		clickStoreCardImage(shortDescription).
		clickBackButton().
		clickAndVerifyStoreCardShortDescription(shortDescription).
		clickBackButton().
		clickOnAddToShareButton(shortDescription).
		clickOnShareIconWithCount(1).
		validateStoreImageInPopup(shortDescription).
		validateStoreNameInPopup(shortDescription).
		validateShortDescriptionInPopup(shortDescription).
		clickOnShareNowInPopup();

		WhatsAppUtility whatsAppUtility = new WhatsAppUtility(driver, testInfo, udid, port, automationName, deviceName, systemPort);
				
		String fullDescription_ShortenURL_One = whatsAppUtility.clickOnWhatAppMenuItemSearch().
		enterWhatsAppSearchText("Mallik").
		clickOnRequiredContact().
		clickOnSend().
		getWhatsUpSharedCaption();
		
		String fullDescription_ShortenURL_Two = whatsAppUtility.
		clickOnSend().
		getWhatsUpSharedCaption();
		
		whatsAppUtility.
		clickOnLastSharedLink();
		
		String retailer_Url_One = new IntermediateRetailerPage(driver, testInfo).
		validateRetailerPage(linkURL, linkURL).
		getUrlFromDefaultBrowser(linkURL);

		hideKeyboard_All_APILevel();
		backButton_WaitForFewSeconds();
		hideKeyboard_All_APILevel();
		backButton_WaitForFewSeconds();
		hideKeyboard_All_APILevel();
		backButton_WaitForFewSeconds();
		hideKeyboard_All_APILevel();
		backButton_WaitForFewSeconds();

		/*******************************************************************************************************/
		
		new StoreCategoryPage(driver, testInfo).
		clickOnShareIconWithCount(1).
		clickOnCopyLink().
		clickBackButton_StoreCategoryPage().
		clickOnShareIconWithCount(1).
		clickOncloseButtonInPopup().
		validateShareIconWithCountPresence(1).
		clickOnShareIconWithCount(1).
		clickOnclearAllButtonInPopup().
		clickOnAddToShareButton(shortDescription).
		validateShareIconWithCountPresence(1).
		clickOnRemoveButton(shortDescription).
		validateShareIconWithCountAbsence(1).
		clickOnShareNowButton(shortDescription);
		
		String fullDescription_ShortenURL_Three = whatsAppUtility.clickOnWhatAppMenuItemSearch().
		enterWhatsAppSearchText("Mallik").
		clickOnRequiredContact().
		clickOnSend().
		getWhatsUpSharedCaption();
		
		String fullDescription_ShortenURL_Four = whatsAppUtility.
		clickOnSend().
		getWhatsUpSharedCaption();
		
		whatsAppUtility.
		clickOnLastSharedLink();
		
		String retailer_Url_Two = new IntermediateRetailerPage(driver, testInfo).
		validateRetailerPage(linkURL, linkURL).
		getUrlFromDefaultBrowser(linkURL);

		hideKeyboard_All_APILevel();
		backButton_WaitForFewSeconds();
		hideKeyboard_All_APILevel();
		backButton_WaitForFewSeconds();
		hideKeyboard_All_APILevel();
		backButton_WaitForFewSeconds();
		hideKeyboard_All_APILevel();
		backButton_WaitForFewSeconds();

		/*******************************************************************************************************/
		
		new StoreCategoryPage(driver,testInfo).
		clickOnShareIcon(shortDescription).
		clickOnWhatsApp();

		String fullDescription_ShortenURL_Five = whatsAppUtility.clickOnWhatAppMenuItemSearch().
		enterWhatsAppSearchText("Mallik").
		clickOnRequiredContact().
		clickOnSend().
		getWhatsUpSharedCaption();
		
		String fullDescription_ShortenURL_Six = whatsAppUtility.
		clickOnSend().
		getWhatsUpSharedCaption();
		
		whatsAppUtility.
		clickOnLastSharedLink();
		
		String retailer_Url_Three = new IntermediateRetailerPage(driver, testInfo).
		validateRetailerPage(linkURL, linkURL).
		getUrlFromDefaultBrowser(linkURL);

		/*******************************************************************************************************/

		String shortenURL = getClipBoardText();

		CashKaroUtility cu = new CashKaroUtility(udid, port, automationName, deviceName);
		 driver = cu.launchChrome();

		cu.closeAllTabsAndOneTabInChromeBrowser(driver);
		cu.clickAndEnterURLInSearchOrTypeWebAddress(driver, shortenURL);

		String retailer_Url_Four = new IntermediateRetailerPage(driver, testInfo).
		validateRetailerPage(linkURL, linkURL).
		getUrlFromDefaultBrowser(linkURL);

		/*******************************************************************************************************/
		
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

		/*******************************************************************************************************/
		
		String sharedID_One = reportsPage.extractRequiredExitIdUsingStoreNameExitClickType(storeName, "Shared", 4);
		String normalID_One = reportsPage.extractRequiredExitIdUsingStoreNameExitClickTypeSharedId(storeName, sharedID_One);
		
		String sharedID_Two = reportsPage.extractRequiredExitIdUsingStoreNameExitClickType(storeName, "Shared", 3);
		String normalID_Two = reportsPage.extractRequiredExitIdUsingStoreNameExitClickTypeSharedId(storeName, sharedID_Two);
		
		String sharedID_Three = reportsPage.extractRequiredExitIdUsingStoreNameExitClickType(storeName, "Shared", 2);
		String normalID_Three = reportsPage.extractRequiredExitIdUsingStoreNameExitClickTypeSharedId(storeName, sharedID_Three);
		
		String sharedID_Four = reportsPage.extractRequiredExitIdUsingStoreNameExitClickType(storeName, "Shared", 1);
		String normalID_Four = reportsPage.extractRequiredExitIdUsingStoreNameExitClickTypeSharedId(storeName, sharedID_Four);
		
		/*******************************************************************************************************/
		
		validateTheActualValueContainsExpectedValue(fullDescription_ShortenURL_One, fullDescription);
		validateTheActualValueContainsExpectedValue(fullDescription_ShortenURL_One, getTestData(7, "shortenURL")+sharedID_One.toLowerCase());
		
		validateTheActualValueContainsExpectedValue(fullDescription_ShortenURL_Two, fullDescription);
		validateTheActualValueContainsExpectedValue(fullDescription_ShortenURL_Two, getTestData(7, "shortenURL")+sharedID_One.toLowerCase());

		validateTheActualValueContainsExpectedValue(retailer_Url_One.toLowerCase(), normalID_One.toLowerCase());
		
		/*******************************************************************************************************/
		
		validateTheActualValueContainsExpectedValue(shortenURL, getTestData(7, "shortenURL")+sharedID_Two.toLowerCase());
		validateTheActualValueContainsExpectedValue(retailer_Url_Four.toLowerCase(), normalID_Two.toLowerCase());
		
		/*******************************************************************************************************/
		
		validateTheActualValueContainsExpectedValue(fullDescription_ShortenURL_Three, fullDescription);
		validateTheActualValueContainsExpectedValue(fullDescription_ShortenURL_Three, getTestData(7, "shortenURL")+sharedID_Three.toLowerCase());
		
		validateTheActualValueContainsExpectedValue(fullDescription_ShortenURL_Four, fullDescription);
		validateTheActualValueContainsExpectedValue(fullDescription_ShortenURL_Four, getTestData(7, "shortenURL")+sharedID_Three.toLowerCase());

		validateTheActualValueContainsExpectedValue(retailer_Url_Three.toLowerCase(), normalID_Four.toLowerCase());
		
		/*******************************************************************************************************/
		
		validateTheActualValueContainsExpectedValue(fullDescription_ShortenURL_Five, fullDescription);
		validateTheActualValueContainsExpectedValue(fullDescription_ShortenURL_Five, getTestData(7, "shortenURL")+sharedID_Four.toLowerCase());
		
		validateTheActualValueContainsExpectedValue(fullDescription_ShortenURL_Six, fullDescription);
		validateTheActualValueContainsExpectedValue(fullDescription_ShortenURL_Six, getTestData(7, "shortenURL")+sharedID_Four.toLowerCase());

		validateTheActualValueContainsExpectedValue(retailer_Url_Two.toLowerCase(), normalID_Three.toLowerCase());

		/*******************************************************************************************************/

		CashKaroUtility reopenCKApp = new CashKaroUtility(udid, port, automationName, deviceName, systemPort);
		driver = reopenCKApp.launchEarnKaroApp(driver);

		/*******************************************************************************************************/

		new HomePage(driver, testInfo).
		clickOnHamburgerIcon().
		presenceOfHomeIcon().
		clickRetailerCategory().
		clickAutomationSpecificCategoryInRetailerCategoryMenuUntilAllSubCategoryVisible().
		clickAutomationSpecificSubCategoryOne().
		clickPopularTab().
		clickAndVerifyStoreCardShortDescription(shortDescription).
		validateStoreImage(shortDescription).
		validateStoreNameInPopup(storeName).
		validateShortDescriptionInPopup(storeName).
		validateprimaryProfit(primaryProfitValue).
		clickOnAddToShareButton().
		clickOnShareIconWithCount(1).
		validateStoreImageInPopup(shortDescription).
		validateStoreNameInPopup(shortDescription).
		validateShortDescriptionInPopup(shortDescription).		
		clickOnShareNowInPopup();

		WhatsAppUtility whatsAppUtilityOne = new WhatsAppUtility(driver, testInfo, udid, port, automationName, deviceName, systemPort);
	
		String fullDescription_ShortenURL_Seven = whatsAppUtilityOne.clickOnWhatAppMenuItemSearch().
		enterWhatsAppSearchText("Mallik").
		clickOnRequiredContact().
		clickOnSend().
		getWhatsUpSharedCaption();
		
		String fullDescription_ShortenURL_Eight = whatsAppUtilityOne.
		clickOnSend().
		getWhatsUpSharedCaption();
		
		whatsAppUtilityOne.
		clickOnLastSharedLink();
		
		String retailer_Url_Five = new IntermediateRetailerPage(driver, testInfo).
		validateRetailerPage(linkURL, linkURL).
		getUrlFromDefaultBrowser(linkURL);

		hideKeyboard_All_APILevel();
		backButton_WaitForFewSeconds();
		hideKeyboard_All_APILevel();
		backButton_WaitForFewSeconds();
		hideKeyboard_All_APILevel();
		backButton_WaitForFewSeconds();
		hideKeyboard_All_APILevel();
		backButton_WaitForFewSeconds();

		/*******************************************************************************************************/

		new StoreDetailPage(driver, testInfo).
		clickOnRemoveButton().
		clickOnAddToShareButton().
		clickOnShareIconWithCount(1).
		clickOnCopyLink().
		clickBackButton_StoreDetailPage().
		clickOnShareIconWithCount(1).
		clickOncloseButtonInPopup().
		clickOnShareIconWithCount(1).
		clickOnclearAllButtonInPopup().
		validateShareIconWithCountAbsence(1).
		clickOnAddToShareButton().
		validateShareIconWithCountPresence(1).
		clickOnRemoveButton().
		validateShareIconWithCountAbsence(1).
 		clickOnAddToShareButton().
		clickOnShareNow();
	
		String fullDescription_ShortenURL_Nine = whatsAppUtilityOne.clickOnWhatAppMenuItemSearch().
		enterWhatsAppSearchText("Mallik").
		clickOnRequiredContact().
		clickOnSend().
		getWhatsUpSharedCaption();
		
		String fullDescription_ShortenURL_Ten = whatsAppUtilityOne.
		clickOnSend().
		getWhatsUpSharedCaption();
		
		whatsAppUtilityOne.
		clickOnLastSharedLink();
		
		String retailer_Url_Six = new IntermediateRetailerPage(driver, testInfo).
		validateRetailerPage(linkURL, linkURL).
		getUrlFromDefaultBrowser(linkURL);

		hideKeyboard_All_APILevel();
		backButton_WaitForFewSeconds();
		hideKeyboard_All_APILevel();
		backButton_WaitForFewSeconds();
		hideKeyboard_All_APILevel();
		backButton_WaitForFewSeconds();
		hideKeyboard_All_APILevel();
		backButton_WaitForFewSeconds();

		/*******************************************************************************************************/

		new StoreDetailPage(driver,testInfo).
		clickOnShareIcon().
		clickOnWhatsApp();

		String fullDescription_ShortenURL_Eleven = whatsAppUtilityOne.clickOnWhatAppMenuItemSearch().
		enterWhatsAppSearchText("Mallik").
		clickOnRequiredContact().
		clickOnSend().
		getWhatsUpSharedCaption();
		
		String fullDescription_ShortenURL_Twelve = whatsAppUtilityOne.
		clickOnSend().
		getWhatsUpSharedCaption();
		
		whatsAppUtilityOne.
		clickOnLastSharedLink();
		
		String retailer_Url_Seven = new IntermediateRetailerPage(driver, testInfo).
		validateRetailerPage(linkURL, linkURL).
		getUrlFromDefaultBrowser(linkURL);

		hideKeyboard_All_APILevel();
		backButton_WaitForFewSeconds();
		hideKeyboard_All_APILevel();
		backButton_WaitForFewSeconds();
		hideKeyboard_All_APILevel();
		backButton_WaitForFewSeconds();
		hideKeyboard_All_APILevel();
		backButton_WaitForFewSeconds();

		/*******************************************************************************************************/

		String shortenURL_Two = getClipBoardText(driver);

		CashKaroUtility cu1 = new CashKaroUtility(udid, port, automationName, deviceName);
		AppiumDriver<MobileElement> driver1 = cu1.launchChrome();
		driver = driver1;
		cu = cu1;

		cu.closeAllTabsAndOneTabInChromeBrowser(driver);
		cu.clickAndEnterURLInSearchOrTypeWebAddress(driver, shortenURL_Two);

		String retailer_Url_Eight = new IntermediateRetailerPage(driver, testInfo).
		validateRetailerPage(linkURL, linkURL).
		getUrlFromDefaultBrowser(linkURL);

		hideKeyboard_All_APILevel();
		backButton_WaitForFewSeconds();
		hideKeyboard_All_APILevel();
		backButton_WaitForFewSeconds();
		hideKeyboard_All_APILevel();
		backButton_WaitForFewSeconds();
		hideKeyboard_All_APILevel();
		backButton_WaitForFewSeconds();
		
		/*******************************************************************************************************/
		
		CashKaroUtility cuTwo = new CashKaroUtility(udid, port, automationName, deviceName);
		driver = cuTwo.launchChromeWebView(driver);

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

		/*******************************************************************************************************/
		
		String sharedID_Five = reportsPage.extractRequiredExitIdUsingStoreNameExitClickType(storeName, "Shared", 4);
		String normalID_Five = reportsPage.extractRequiredExitIdUsingStoreNameExitClickTypeSharedId(storeName, sharedID_Five);
		
		String sharedID_Six = reportsPage.extractRequiredExitIdUsingStoreNameExitClickType(storeName, "Shared", 3);
		String normalID_Six = reportsPage.extractRequiredExitIdUsingStoreNameExitClickTypeSharedId(storeName, sharedID_Six);
		
		String sharedID_Seven = reportsPage.extractRequiredExitIdUsingStoreNameExitClickType(storeName, "Shared", 2);
		String normalID_Seven = reportsPage.extractRequiredExitIdUsingStoreNameExitClickTypeSharedId(storeName, sharedID_Seven);
		
		String sharedID_Eight = reportsPage.extractRequiredExitIdUsingStoreNameExitClickType(storeName, "Shared", 1);
		String normalID_Eight = reportsPage.extractRequiredExitIdUsingStoreNameExitClickTypeSharedId(storeName, sharedID_Eight);
		
		/*******************************************************************************************************/
		
		validateTheActualValueContainsExpectedValue(fullDescription_ShortenURL_Seven, fullDescription);
		validateTheActualValueContainsExpectedValue(fullDescription_ShortenURL_Seven, getTestData(7, "shortenURL")+sharedID_Five.toLowerCase());
		
		validateTheActualValueContainsExpectedValue(fullDescription_ShortenURL_Eight, fullDescription);
		validateTheActualValueContainsExpectedValue(fullDescription_ShortenURL_Eight, getTestData(7, "shortenURL")+sharedID_Five.toLowerCase());

		validateTheActualValueContainsExpectedValue(retailer_Url_Five.toLowerCase(), normalID_Five.toLowerCase());
		
		/*******************************************************************************************************/
		
		validateTheActualValueContainsExpectedValue(shortenURL_Two, getTestData(7, "shortenURL")+sharedID_Six.toLowerCase());
		validateTheActualValueContainsExpectedValue(retailer_Url_Eight.toLowerCase(), normalID_Six.toLowerCase());
		
		/*******************************************************************************************************/
		
		validateTheActualValueContainsExpectedValue(fullDescription_ShortenURL_Nine, fullDescription);
		validateTheActualValueContainsExpectedValue(fullDescription_ShortenURL_Nine, getTestData(7, "shortenURL")+sharedID_Seven.toLowerCase());
		
		validateTheActualValueContainsExpectedValue(fullDescription_ShortenURL_Ten, fullDescription);
		validateTheActualValueContainsExpectedValue(fullDescription_ShortenURL_Ten, getTestData(7, "shortenURL")+sharedID_Seven.toLowerCase());

		validateTheActualValueContainsExpectedValue(retailer_Url_Seven.toLowerCase(), normalID_Eight.toLowerCase());
		
		/*******************************************************************************************************/
		
		validateTheActualValueContainsExpectedValue(fullDescription_ShortenURL_Eleven, fullDescription);
		validateTheActualValueContainsExpectedValue(fullDescription_ShortenURL_Eleven, getTestData(7, "shortenURL")+sharedID_Eight.toLowerCase());
		
		validateTheActualValueContainsExpectedValue(fullDescription_ShortenURL_Twelve, fullDescription);
		validateTheActualValueContainsExpectedValue(fullDescription_ShortenURL_Twelve, getTestData(7, "shortenURL")+sharedID_Eight.toLowerCase());

		validateTheActualValueContainsExpectedValue(retailer_Url_Six.toLowerCase(), normalID_Seven.toLowerCase());
		
		/*******************************************************************************************************/

	}

	@Test
	public void storeCategoryPage_ValidateRwStoreOneStoreCardValuesInStoreCategoryPageAndStoreDetailPage_TC06() {

		String storeName = CashKaroUtility.extractValuesFromStoreCategoryJsonAndLoadTheVariablesForStore("RW_Store_One", 2, "RW_Store_One_Name");
		String shortDescription = CashKaroUtility.extractValuesFromStoreCategoryJsonAndLoadTheVariablesForStore("RW_Store_One", 2, "RW_Store_One_App_Short_Description");
		String primaryProfitValue = CashKaroUtility.extractValuesFromStoreCategoryJsonAndLoadTheVariablesForStore("RW_Store_One", 2, "RW_Store_One_Primary_Profit_Value");
		String linkURL = CashKaroUtility.extractValuesFromStoreCategoryJsonAndLoadTheVariablesForStore("RW_Store_One", 2, "RW_Store_One_linkURL");
		String fullDescription = "App Full Description";
				
		reportStep("TC03 is  started", "INFO");

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

		/*******************************************************************************************************/
		
		new HomePage(driver, testInfo).
		clickOnHamburgerIcon().
		presenceOfHomeIcon().
		clickRetailerCategory().
		clickAutomationSpecificCategoryInRetailerCategoryMenuUntilAllSubCategoryVisible().
		clickAutomationSpecificSubCategoryOne().
		clickPopularTab().
		clickStoreCardImage(shortDescription).
		clickBackButton().
		clickAndVerifyStoreCardShortDescription(shortDescription).
		clickBackButton().
		clickOnAddToShareButton(shortDescription).
		clickOnShareIconWithCount(1).
		validateStoreImageInPopup(shortDescription).
		validateStoreNameInPopup(shortDescription).
		validateShortDescriptionInPopup(shortDescription).
		clickOnShareNowInPopup();

		WhatsAppUtility whatsAppUtility = new WhatsAppUtility(driver, testInfo, udid, port, automationName, deviceName, systemPort);
				
		String fullDescription_ShortenURL_One = whatsAppUtility.clickOnWhatAppMenuItemSearch().
		enterWhatsAppSearchText("Mallik").
		clickOnRequiredContact().
		clickOnSend().
		getWhatsUpSharedCaption();
		
		String fullDescription_ShortenURL_Two = whatsAppUtility.
		clickOnSend().
		getWhatsUpSharedCaption();
		
		whatsAppUtility.
		clickOnLastSharedLink();
		
		String retailer_Url_One = new IntermediateRetailerPage(driver, testInfo).
		validateRetailerPage(linkURL, linkURL).
		getUrlFromDefaultBrowser(linkURL);

		hideKeyboard_All_APILevel();
		backButton_WaitForFewSeconds();
		hideKeyboard_All_APILevel();
		backButton_WaitForFewSeconds();
		hideKeyboard_All_APILevel();
		backButton_WaitForFewSeconds();
		hideKeyboard_All_APILevel();
		backButton_WaitForFewSeconds();

		/*******************************************************************************************************/
		
		new StoreCategoryPage(driver, testInfo).
		clickOnShareIconWithCount(1).
		clickOnCopyLink().
		clickBackButton_StoreCategoryPage().
		clickOnShareIconWithCount(1).
		clickOncloseButtonInPopup().
		validateShareIconWithCountPresence(1).
		clickOnShareIconWithCount(1).
		clickOnclearAllButtonInPopup().
		clickOnAddToShareButton(shortDescription).
		validateShareIconWithCountPresence(1).
		clickOnRemoveButton(shortDescription).
		validateShareIconWithCountAbsence(1).
		clickOnShareNowButton(shortDescription);
		
		String fullDescription_ShortenURL_Three = whatsAppUtility.clickOnWhatAppMenuItemSearch().
		enterWhatsAppSearchText("Mallik").
		clickOnRequiredContact().
		clickOnSend().
		getWhatsUpSharedCaption();
		
		String fullDescription_ShortenURL_Four = whatsAppUtility.
		clickOnSend().
		getWhatsUpSharedCaption();
		
		whatsAppUtility.
		clickOnLastSharedLink();
		
		String retailer_Url_Two = new IntermediateRetailerPage(driver, testInfo).
		validateRetailerPage(linkURL, linkURL).
		getUrlFromDefaultBrowser(linkURL);

		hideKeyboard_All_APILevel();
		backButton_WaitForFewSeconds();
		hideKeyboard_All_APILevel();
		backButton_WaitForFewSeconds();
		hideKeyboard_All_APILevel();
		backButton_WaitForFewSeconds();
		hideKeyboard_All_APILevel();
		backButton_WaitForFewSeconds();

		/*******************************************************************************************************/
		
		new StoreCategoryPage(driver,testInfo).
		clickOnShareIcon(shortDescription).
		clickOnWhatsApp();

		String fullDescription_ShortenURL_Five = whatsAppUtility.clickOnWhatAppMenuItemSearch().
		enterWhatsAppSearchText("Mallik").
		clickOnRequiredContact().
		clickOnSend().
		getWhatsUpSharedCaption();
		
		String fullDescription_ShortenURL_Six = whatsAppUtility.
		clickOnSend().
		getWhatsUpSharedCaption();
		
		whatsAppUtility.
		clickOnLastSharedLink();
		
		String retailer_Url_Three = new IntermediateRetailerPage(driver, testInfo).
		validateRetailerPage(linkURL, linkURL).
		getUrlFromDefaultBrowser(linkURL);

		/*******************************************************************************************************/

		String shortenURL = getClipBoardText();

		CashKaroUtility cu = new CashKaroUtility(udid, port, automationName, deviceName);
		 driver = cu.launchChrome();

		cu.closeAllTabsAndOneTabInChromeBrowser(driver);
		cu.clickAndEnterURLInSearchOrTypeWebAddress(driver, shortenURL);

		String retailer_Url_Four = new IntermediateRetailerPage(driver, testInfo).
		validateRetailerPage(linkURL, linkURL).
		getUrlFromDefaultBrowser(linkURL);

		/*******************************************************************************************************/
		
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

		/*******************************************************************************************************/
		
		String sharedID_One = reportsPage.extractRequiredExitIdUsingStoreNameExitClickType(storeName, "Shared", 4);
		String normalID_One = reportsPage.extractRequiredExitIdUsingStoreNameExitClickTypeSharedId(storeName, sharedID_One);
		
		String sharedID_Two = reportsPage.extractRequiredExitIdUsingStoreNameExitClickType(storeName, "Shared", 3);
		String normalID_Two = reportsPage.extractRequiredExitIdUsingStoreNameExitClickTypeSharedId(storeName, sharedID_Two);
		
		String sharedID_Three = reportsPage.extractRequiredExitIdUsingStoreNameExitClickType(storeName, "Shared", 2);
		String normalID_Three = reportsPage.extractRequiredExitIdUsingStoreNameExitClickTypeSharedId(storeName, sharedID_Three);
		
		String sharedID_Four = reportsPage.extractRequiredExitIdUsingStoreNameExitClickType(storeName, "Shared", 1);
		String normalID_Four = reportsPage.extractRequiredExitIdUsingStoreNameExitClickTypeSharedId(storeName, sharedID_Four);
		
		/*******************************************************************************************************/
		
		validateTheActualValueContainsExpectedValue(fullDescription_ShortenURL_One, fullDescription);
		validateTheActualValueContainsExpectedValue(fullDescription_ShortenURL_One, getTestData(7, "shortenURL")+sharedID_One.toLowerCase());
		
		validateTheActualValueContainsExpectedValue(fullDescription_ShortenURL_Two, fullDescription);
		validateTheActualValueContainsExpectedValue(fullDescription_ShortenURL_Two, getTestData(7, "shortenURL")+sharedID_One.toLowerCase());

		validateTheActualValueContainsExpectedValue(retailer_Url_One.toLowerCase(), normalID_One.toLowerCase());
		
		/*******************************************************************************************************/
		
		validateTheActualValueContainsExpectedValue(shortenURL, getTestData(7, "shortenURL")+sharedID_Two.toLowerCase());
		validateTheActualValueContainsExpectedValue(retailer_Url_Four.toLowerCase(), normalID_Two.toLowerCase());
		
		/*******************************************************************************************************/
		
		validateTheActualValueContainsExpectedValue(fullDescription_ShortenURL_Three, fullDescription);
		validateTheActualValueContainsExpectedValue(fullDescription_ShortenURL_Three, getTestData(7, "shortenURL")+sharedID_Three.toLowerCase());
		
		validateTheActualValueContainsExpectedValue(fullDescription_ShortenURL_Four, fullDescription);
		validateTheActualValueContainsExpectedValue(fullDescription_ShortenURL_Four, getTestData(7, "shortenURL")+sharedID_Three.toLowerCase());

		validateTheActualValueContainsExpectedValue(retailer_Url_Three.toLowerCase(), normalID_Four.toLowerCase());
		
		/*******************************************************************************************************/
		
		validateTheActualValueContainsExpectedValue(fullDescription_ShortenURL_Five, fullDescription);
		validateTheActualValueContainsExpectedValue(fullDescription_ShortenURL_Five, getTestData(7, "shortenURL")+sharedID_Four.toLowerCase());
		
		validateTheActualValueContainsExpectedValue(fullDescription_ShortenURL_Six, fullDescription);
		validateTheActualValueContainsExpectedValue(fullDescription_ShortenURL_Six, getTestData(7, "shortenURL")+sharedID_Four.toLowerCase());

		validateTheActualValueContainsExpectedValue(retailer_Url_Two.toLowerCase(), normalID_Three.toLowerCase());

		/*******************************************************************************************************/

		CashKaroUtility reopenCKApp = new CashKaroUtility(udid, port, automationName, deviceName, systemPort);
		driver = reopenCKApp.launchEarnKaroApp(driver);

		/*******************************************************************************************************/

		new HomePage(driver, testInfo).
		clickOnHamburgerIcon().
		presenceOfHomeIcon().
		clickRetailerCategory().
		clickAutomationSpecificCategoryInRetailerCategoryMenuUntilAllSubCategoryVisible().
		clickAutomationSpecificSubCategoryOne().
		clickPopularTab().
		clickAndVerifyStoreCardShortDescription(shortDescription).
		validateStoreImage(shortDescription).
		validateStoreNameInPopup(storeName).
		validateShortDescriptionInPopup(storeName).
		validateprimaryProfit(primaryProfitValue).
		clickOnAddToShareButton().
		clickOnShareIconWithCount(1).
		validateStoreImageInPopup(shortDescription).
		validateStoreNameInPopup(shortDescription).
		validateShortDescriptionInPopup(shortDescription).		
		clickOnShareNowInPopup();

		WhatsAppUtility whatsAppUtilityOne = new WhatsAppUtility(driver, testInfo, udid, port, automationName, deviceName, systemPort);
	
		String fullDescription_ShortenURL_Seven = whatsAppUtilityOne.clickOnWhatAppMenuItemSearch().
		enterWhatsAppSearchText("Mallik").
		clickOnRequiredContact().
		clickOnSend().
		getWhatsUpSharedCaption();
		
		String fullDescription_ShortenURL_Eight = whatsAppUtilityOne.
		clickOnSend().
		getWhatsUpSharedCaption();
		
		whatsAppUtilityOne.
		clickOnLastSharedLink();
		
		String retailer_Url_Five = new IntermediateRetailerPage(driver, testInfo).
		validateRetailerPage(linkURL, linkURL).
		getUrlFromDefaultBrowser(linkURL);

		hideKeyboard_All_APILevel();
		backButton_WaitForFewSeconds();
		hideKeyboard_All_APILevel();
		backButton_WaitForFewSeconds();
		hideKeyboard_All_APILevel();
		backButton_WaitForFewSeconds();
		hideKeyboard_All_APILevel();
		backButton_WaitForFewSeconds();

		/*******************************************************************************************************/

		new StoreDetailPage(driver, testInfo).
		clickOnRemoveButton().
		clickOnAddToShareButton().
		clickOnShareIconWithCount(1).
		clickOnCopyLink().
		clickBackButton_StoreDetailPage().
		clickOnShareIconWithCount(1).
		clickOncloseButtonInPopup().
		clickOnShareIconWithCount(1).
		clickOnclearAllButtonInPopup().
		validateShareIconWithCountAbsence(1).
		clickOnAddToShareButton().
		validateShareIconWithCountPresence(1).
		clickOnRemoveButton().
		validateShareIconWithCountAbsence(1).
 		clickOnAddToShareButton().
		clickOnShareNow();
	
		String fullDescription_ShortenURL_Nine = whatsAppUtilityOne.clickOnWhatAppMenuItemSearch().
		enterWhatsAppSearchText("Mallik").
		clickOnRequiredContact().
		clickOnSend().
		getWhatsUpSharedCaption();
		
		String fullDescription_ShortenURL_Ten = whatsAppUtilityOne.
		clickOnSend().
		getWhatsUpSharedCaption();
		
		whatsAppUtilityOne.
		clickOnLastSharedLink();
		
		String retailer_Url_Six = new IntermediateRetailerPage(driver, testInfo).
		validateRetailerPage(linkURL, linkURL).
		getUrlFromDefaultBrowser(linkURL);

		hideKeyboard_All_APILevel();
		backButton_WaitForFewSeconds();
		hideKeyboard_All_APILevel();
		backButton_WaitForFewSeconds();
		hideKeyboard_All_APILevel();
		backButton_WaitForFewSeconds();
		hideKeyboard_All_APILevel();
		backButton_WaitForFewSeconds();

		/*******************************************************************************************************/

		new StoreDetailPage(driver,testInfo).
		clickOnShareIcon().
		clickOnWhatsApp();

		String fullDescription_ShortenURL_Eleven = whatsAppUtilityOne.clickOnWhatAppMenuItemSearch().
		enterWhatsAppSearchText("Mallik").
		clickOnRequiredContact().
		clickOnSend().
		getWhatsUpSharedCaption();
		
		String fullDescription_ShortenURL_Twelve = whatsAppUtilityOne.
		clickOnSend().
		getWhatsUpSharedCaption();
		
		whatsAppUtilityOne.
		clickOnLastSharedLink();
		
		String retailer_Url_Seven = new IntermediateRetailerPage(driver, testInfo).
		validateRetailerPage(linkURL, linkURL).
		getUrlFromDefaultBrowser(linkURL);

		hideKeyboard_All_APILevel();
		backButton_WaitForFewSeconds();
		hideKeyboard_All_APILevel();
		backButton_WaitForFewSeconds();
		hideKeyboard_All_APILevel();
		backButton_WaitForFewSeconds();
		hideKeyboard_All_APILevel();
		backButton_WaitForFewSeconds();

		/*******************************************************************************************************/

		String shortenURL_Two = getClipBoardText(driver);

		CashKaroUtility cu1 = new CashKaroUtility(udid, port, automationName, deviceName);
		AppiumDriver<MobileElement> driver1 = cu1.launchChrome();
		driver = driver1;
		cu = cu1;

		cu.closeAllTabsAndOneTabInChromeBrowser(driver);
		cu.clickAndEnterURLInSearchOrTypeWebAddress(driver, shortenURL_Two);

		String retailer_Url_Eight = new IntermediateRetailerPage(driver, testInfo).
		validateRetailerPage(linkURL, linkURL).
		getUrlFromDefaultBrowser(linkURL);

		hideKeyboard_All_APILevel();
		backButton_WaitForFewSeconds();
		hideKeyboard_All_APILevel();
		backButton_WaitForFewSeconds();
		hideKeyboard_All_APILevel();
		backButton_WaitForFewSeconds();
		hideKeyboard_All_APILevel();
		backButton_WaitForFewSeconds();
		
		/*******************************************************************************************************/
		
		CashKaroUtility cuTwo = new CashKaroUtility(udid, port, automationName, deviceName);
		driver = cuTwo.launchChromeWebView(driver);

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

		/*******************************************************************************************************/
		
		String sharedID_Five = reportsPage.extractRequiredExitIdUsingStoreNameExitClickType(storeName, "Shared", 4);
		String normalID_Five = reportsPage.extractRequiredExitIdUsingStoreNameExitClickTypeSharedId(storeName, sharedID_Five);
		
		String sharedID_Six = reportsPage.extractRequiredExitIdUsingStoreNameExitClickType(storeName, "Shared", 3);
		String normalID_Six = reportsPage.extractRequiredExitIdUsingStoreNameExitClickTypeSharedId(storeName, sharedID_Six);
		
		String sharedID_Seven = reportsPage.extractRequiredExitIdUsingStoreNameExitClickType(storeName, "Shared", 2);
		String normalID_Seven = reportsPage.extractRequiredExitIdUsingStoreNameExitClickTypeSharedId(storeName, sharedID_Seven);
		
		String sharedID_Eight = reportsPage.extractRequiredExitIdUsingStoreNameExitClickType(storeName, "Shared", 1);
		String normalID_Eight = reportsPage.extractRequiredExitIdUsingStoreNameExitClickTypeSharedId(storeName, sharedID_Eight);
		
		/*******************************************************************************************************/
		
		validateTheActualValueContainsExpectedValue(fullDescription_ShortenURL_Seven, fullDescription);
		validateTheActualValueContainsExpectedValue(fullDescription_ShortenURL_Seven, getTestData(7, "shortenURL")+sharedID_Five.toLowerCase());
		
		validateTheActualValueContainsExpectedValue(fullDescription_ShortenURL_Eight, fullDescription);
		validateTheActualValueContainsExpectedValue(fullDescription_ShortenURL_Eight, getTestData(7, "shortenURL")+sharedID_Five.toLowerCase());

		validateTheActualValueContainsExpectedValue(retailer_Url_Five.toLowerCase(), normalID_Five.toLowerCase());
		
		/*******************************************************************************************************/
		
		validateTheActualValueContainsExpectedValue(shortenURL_Two, getTestData(7, "shortenURL")+sharedID_Six.toLowerCase());
		validateTheActualValueContainsExpectedValue(retailer_Url_Eight.toLowerCase(), normalID_Six.toLowerCase());
		
		/*******************************************************************************************************/
		
		validateTheActualValueContainsExpectedValue(fullDescription_ShortenURL_Nine, fullDescription);
		validateTheActualValueContainsExpectedValue(fullDescription_ShortenURL_Nine, getTestData(7, "shortenURL")+sharedID_Seven.toLowerCase());
		
		validateTheActualValueContainsExpectedValue(fullDescription_ShortenURL_Ten, fullDescription);
		validateTheActualValueContainsExpectedValue(fullDescription_ShortenURL_Ten, getTestData(7, "shortenURL")+sharedID_Seven.toLowerCase());

		validateTheActualValueContainsExpectedValue(retailer_Url_Seven.toLowerCase(), normalID_Eight.toLowerCase());
		
		/*******************************************************************************************************/
		
		validateTheActualValueContainsExpectedValue(fullDescription_ShortenURL_Eleven, fullDescription);
		validateTheActualValueContainsExpectedValue(fullDescription_ShortenURL_Eleven, getTestData(7, "shortenURL")+sharedID_Eight.toLowerCase());
		
		validateTheActualValueContainsExpectedValue(fullDescription_ShortenURL_Twelve, fullDescription);
		validateTheActualValueContainsExpectedValue(fullDescription_ShortenURL_Twelve, getTestData(7, "shortenURL")+sharedID_Eight.toLowerCase());

		validateTheActualValueContainsExpectedValue(retailer_Url_Six.toLowerCase(), normalID_Seven.toLowerCase());
		
		/*******************************************************************************************************/

	}

	@Test
	public void storeCategoryPage_ValidateN18StoreTwoStoreCardValuesInStoreCategoryPageAndStoreDetailPage_TC07() {

		String storeName = CashKaroUtility.extractValuesFromStoreCategoryJsonAndLoadTheVariablesForStore("N18_Store_Two", 5, "N18_Store_Two_Name");
		String shortDescription = CashKaroUtility.extractValuesFromStoreCategoryJsonAndLoadTheVariablesForStore("N18_Store_Two", 5, "N18_Store_Two_App_Short_Description");
		String linkURL = CashKaroUtility.extractValuesFromStoreCategoryJsonAndLoadTheVariablesForStore("N18_Store_Two", 5, "N18_Store_Two_linkURL");
		String primaryProfitValue = "0";
		String fullDescription = "App Full Description";
				
		reportStep("TC03 is  started", "INFO");

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

		/*******************************************************************************************************/
		
		new HomePage(driver, testInfo).
		clickOnHamburgerIcon().
		presenceOfHomeIcon().
		clickRetailerCategory().
		clickAutomationSpecificCategoryInRetailerCategoryMenuUntilAllSubCategoryVisible().
		clickAutomationSpecificSubCategoryOne().
		clickPopularTab().
		clickStoreCardImage(shortDescription).
		clickBackButton().
		clickAndVerifyStoreCardShortDescription(shortDescription).
		clickBackButton().
		clickOnAddToShareButton(shortDescription).
		clickOnShareIconWithCount(1).
		validateStoreImageInPopup(shortDescription).
		validateStoreNameInPopup(shortDescription).
		validateShortDescriptionInPopup(shortDescription).
		clickOnShareNowInPopup();

		WhatsAppUtility whatsAppUtility = new WhatsAppUtility(driver, testInfo, udid, port, automationName, deviceName, systemPort);
				
		String fullDescription_ShortenURL_One = whatsAppUtility.clickOnWhatAppMenuItemSearch().
		enterWhatsAppSearchText("Mallik").
		clickOnRequiredContact().
		clickOnSend().
		getWhatsUpSharedCaption();
		
		String fullDescription_ShortenURL_Two = whatsAppUtility.
		clickOnSend().
		getWhatsUpSharedCaption();
		
		whatsAppUtility.
		clickOnLastSharedLink();
		
		String retailer_Url_One = new IntermediateRetailerPage(driver, testInfo).
		validateRetailerPage(linkURL, linkURL).
		getUrlFromDefaultBrowser(linkURL);

		hideKeyboard_All_APILevel();
		backButton_WaitForFewSeconds();
		hideKeyboard_All_APILevel();
		backButton_WaitForFewSeconds();
		hideKeyboard_All_APILevel();
		backButton_WaitForFewSeconds();
		hideKeyboard_All_APILevel();
		backButton_WaitForFewSeconds();

		/*******************************************************************************************************/
		
		new StoreCategoryPage(driver, testInfo).
		clickOnShareIconWithCount(1).
		clickOnCopyLink().
		clickBackButton_StoreCategoryPage().
		clickOnShareIconWithCount(1).
		clickOncloseButtonInPopup().
		validateShareIconWithCountPresence(1).
		clickOnShareIconWithCount(1).
		clickOnclearAllButtonInPopup().
		clickOnAddToShareButton(shortDescription).
		validateShareIconWithCountPresence(1).
		clickOnRemoveButton(shortDescription).
		validateShareIconWithCountAbsence(1).
		clickOnShareNowButton(shortDescription);
		
		String fullDescription_ShortenURL_Three = whatsAppUtility.clickOnWhatAppMenuItemSearch().
		enterWhatsAppSearchText("Mallik").
		clickOnRequiredContact().
		clickOnSend().
		getWhatsUpSharedCaption();
		
		String fullDescription_ShortenURL_Four = whatsAppUtility.
		clickOnSend().
		getWhatsUpSharedCaption();
		
		whatsAppUtility.
		clickOnLastSharedLink();
		
		String retailer_Url_Two = new IntermediateRetailerPage(driver, testInfo).
		validateRetailerPage(linkURL, linkURL).
		getUrlFromDefaultBrowser(linkURL);

		hideKeyboard_All_APILevel();
		backButton_WaitForFewSeconds();
		hideKeyboard_All_APILevel();
		backButton_WaitForFewSeconds();
		hideKeyboard_All_APILevel();
		backButton_WaitForFewSeconds();
		hideKeyboard_All_APILevel();
		backButton_WaitForFewSeconds();

		/*******************************************************************************************************/
		
		new StoreCategoryPage(driver,testInfo).
		clickOnShareIcon(shortDescription).
		clickOnWhatsApp();

		String fullDescription_ShortenURL_Five = whatsAppUtility.clickOnWhatAppMenuItemSearch().
		enterWhatsAppSearchText("Mallik").
		clickOnRequiredContact().
		clickOnSend().
		getWhatsUpSharedCaption();
		
		String fullDescription_ShortenURL_Six = whatsAppUtility.
		clickOnSend().
		getWhatsUpSharedCaption();
		
		whatsAppUtility.
		clickOnLastSharedLink();
		
		String retailer_Url_Three = new IntermediateRetailerPage(driver, testInfo).
		validateRetailerPage(linkURL, linkURL).
		getUrlFromDefaultBrowser(linkURL);

		/*******************************************************************************************************/

		String shortenURL = getClipBoardText();

		CashKaroUtility cu = new CashKaroUtility(udid, port, automationName, deviceName);
		 driver = cu.launchChrome();

		cu.closeAllTabsAndOneTabInChromeBrowser(driver);
		cu.clickAndEnterURLInSearchOrTypeWebAddress(driver, shortenURL);

		String retailer_Url_Four = new IntermediateRetailerPage(driver, testInfo).
		validateRetailerPage(linkURL, linkURL).
		getUrlFromDefaultBrowser(linkURL);

		/*******************************************************************************************************/
		
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

		/*******************************************************************************************************/
		
		String sharedID_One = reportsPage.extractRequiredExitIdUsingStoreNameExitClickType(storeName, "Shared", 4);
		String normalID_One = reportsPage.extractRequiredExitIdUsingStoreNameExitClickTypeSharedId(storeName, sharedID_One);
		
		String sharedID_Two = reportsPage.extractRequiredExitIdUsingStoreNameExitClickType(storeName, "Shared", 3);
		String normalID_Two = reportsPage.extractRequiredExitIdUsingStoreNameExitClickTypeSharedId(storeName, sharedID_Two);
		
		String sharedID_Three = reportsPage.extractRequiredExitIdUsingStoreNameExitClickType(storeName, "Shared", 2);
		String normalID_Three = reportsPage.extractRequiredExitIdUsingStoreNameExitClickTypeSharedId(storeName, sharedID_Three);
		
		String sharedID_Four = reportsPage.extractRequiredExitIdUsingStoreNameExitClickType(storeName, "Shared", 1);
		String normalID_Four = reportsPage.extractRequiredExitIdUsingStoreNameExitClickTypeSharedId(storeName, sharedID_Four);
		
		/*******************************************************************************************************/
		
		validateTheActualValueContainsExpectedValue(fullDescription_ShortenURL_One, fullDescription);
		validateTheActualValueContainsExpectedValue(fullDescription_ShortenURL_One, getTestData(7, "shortenURL")+sharedID_One.toLowerCase());
		
		validateTheActualValueContainsExpectedValue(fullDescription_ShortenURL_Two, fullDescription);
		validateTheActualValueContainsExpectedValue(fullDescription_ShortenURL_Two, getTestData(7, "shortenURL")+sharedID_One.toLowerCase());

		validateTheActualValueContainsExpectedValue(retailer_Url_One.toLowerCase(), normalID_One.toLowerCase());
		
		/*******************************************************************************************************/
		
		validateTheActualValueContainsExpectedValue(shortenURL, getTestData(7, "shortenURL")+sharedID_Two.toLowerCase());
		validateTheActualValueContainsExpectedValue(retailer_Url_Four.toLowerCase(), normalID_Two.toLowerCase());
		
		/*******************************************************************************************************/
		
		validateTheActualValueContainsExpectedValue(fullDescription_ShortenURL_Three, fullDescription);
		validateTheActualValueContainsExpectedValue(fullDescription_ShortenURL_Three, getTestData(7, "shortenURL")+sharedID_Three.toLowerCase());
		
		validateTheActualValueContainsExpectedValue(fullDescription_ShortenURL_Four, fullDescription);
		validateTheActualValueContainsExpectedValue(fullDescription_ShortenURL_Four, getTestData(7, "shortenURL")+sharedID_Three.toLowerCase());

		validateTheActualValueContainsExpectedValue(retailer_Url_Three.toLowerCase(), normalID_Four.toLowerCase());
		
		/*******************************************************************************************************/
		
		validateTheActualValueContainsExpectedValue(fullDescription_ShortenURL_Five, fullDescription);
		validateTheActualValueContainsExpectedValue(fullDescription_ShortenURL_Five, getTestData(7, "shortenURL")+sharedID_Four.toLowerCase());
		
		validateTheActualValueContainsExpectedValue(fullDescription_ShortenURL_Six, fullDescription);
		validateTheActualValueContainsExpectedValue(fullDescription_ShortenURL_Six, getTestData(7, "shortenURL")+sharedID_Four.toLowerCase());

		validateTheActualValueContainsExpectedValue(retailer_Url_Two.toLowerCase(), normalID_Three.toLowerCase());

		/*******************************************************************************************************/

		CashKaroUtility reopenCKApp = new CashKaroUtility(udid, port, automationName, deviceName, systemPort);
		driver = reopenCKApp.launchEarnKaroApp(driver);

		/*******************************************************************************************************/

		new HomePage(driver, testInfo).
		clickOnHamburgerIcon().
		presenceOfHomeIcon().
		clickRetailerCategory().
		clickAutomationSpecificCategoryInRetailerCategoryMenuUntilAllSubCategoryVisible().
		clickAutomationSpecificSubCategoryOne().
		clickPopularTab().
		clickAndVerifyStoreCardShortDescription(shortDescription).
		validateStoreImage(shortDescription).
		validateStoreNameInPopup(storeName).
		validateShortDescriptionInPopup(storeName).
		validateprimaryProfit(primaryProfitValue).
		clickOnAddToShareButton().
		clickOnShareIconWithCount(1).
		validateStoreImageInPopup(shortDescription).
		validateStoreNameInPopup(shortDescription).
		validateShortDescriptionInPopup(shortDescription).		
		clickOnShareNowInPopup();

		WhatsAppUtility whatsAppUtilityOne = new WhatsAppUtility(driver, testInfo, udid, port, automationName, deviceName, systemPort);
	
		String fullDescription_ShortenURL_Seven = whatsAppUtilityOne.clickOnWhatAppMenuItemSearch().
		enterWhatsAppSearchText("Mallik").
		clickOnRequiredContact().
		clickOnSend().
		getWhatsUpSharedCaption();
		
		String fullDescription_ShortenURL_Eight = whatsAppUtilityOne.
		clickOnSend().
		getWhatsUpSharedCaption();
		
		whatsAppUtilityOne.
		clickOnLastSharedLink();
		
		String retailer_Url_Five = new IntermediateRetailerPage(driver, testInfo).
		validateRetailerPage(linkURL, linkURL).
		getUrlFromDefaultBrowser(linkURL);

		hideKeyboard_All_APILevel();
		backButton_WaitForFewSeconds();
		hideKeyboard_All_APILevel();
		backButton_WaitForFewSeconds();
		hideKeyboard_All_APILevel();
		backButton_WaitForFewSeconds();
		hideKeyboard_All_APILevel();
		backButton_WaitForFewSeconds();

		/*******************************************************************************************************/

		new StoreDetailPage(driver, testInfo).
		clickOnRemoveButton().
		clickOnAddToShareButton().
		clickOnShareIconWithCount(1).
		clickOnCopyLink().
		clickBackButton_StoreDetailPage().
		clickOnShareIconWithCount(1).
		clickOncloseButtonInPopup().
		clickOnShareIconWithCount(1).
		clickOnclearAllButtonInPopup().
		validateShareIconWithCountAbsence(1).
		clickOnAddToShareButton().
		validateShareIconWithCountPresence(1).
		clickOnRemoveButton().
		validateShareIconWithCountAbsence(1).
 		clickOnAddToShareButton().
		clickOnShareNow();
	
		String fullDescription_ShortenURL_Nine = whatsAppUtilityOne.clickOnWhatAppMenuItemSearch().
		enterWhatsAppSearchText("Mallik").
		clickOnRequiredContact().
		clickOnSend().
		getWhatsUpSharedCaption();
		
		String fullDescription_ShortenURL_Ten = whatsAppUtilityOne.
		clickOnSend().
		getWhatsUpSharedCaption();
		
		whatsAppUtilityOne.
		clickOnLastSharedLink();
		
		String retailer_Url_Six = new IntermediateRetailerPage(driver, testInfo).
		validateRetailerPage(linkURL, linkURL).
		getUrlFromDefaultBrowser(linkURL);

		hideKeyboard_All_APILevel();
		backButton_WaitForFewSeconds();
		hideKeyboard_All_APILevel();
		backButton_WaitForFewSeconds();
		hideKeyboard_All_APILevel();
		backButton_WaitForFewSeconds();
		hideKeyboard_All_APILevel();
		backButton_WaitForFewSeconds();

		/*******************************************************************************************************/

		new StoreDetailPage(driver,testInfo).
		clickOnShareIcon().
		clickOnWhatsApp();

		String fullDescription_ShortenURL_Eleven = whatsAppUtilityOne.clickOnWhatAppMenuItemSearch().
		enterWhatsAppSearchText("Mallik").
		clickOnRequiredContact().
		clickOnSend().
		getWhatsUpSharedCaption();
		
		String fullDescription_ShortenURL_Twelve = whatsAppUtilityOne.
		clickOnSend().
		getWhatsUpSharedCaption();
		
		whatsAppUtilityOne.
		clickOnLastSharedLink();
		
		String retailer_Url_Seven = new IntermediateRetailerPage(driver, testInfo).
		validateRetailerPage(linkURL, linkURL).
		getUrlFromDefaultBrowser(linkURL);

		hideKeyboard_All_APILevel();
		backButton_WaitForFewSeconds();
		hideKeyboard_All_APILevel();
		backButton_WaitForFewSeconds();
		hideKeyboard_All_APILevel();
		backButton_WaitForFewSeconds();
		hideKeyboard_All_APILevel();
		backButton_WaitForFewSeconds();

		/*******************************************************************************************************/

		String shortenURL_Two = getClipBoardText(driver);

		CashKaroUtility cu1 = new CashKaroUtility(udid, port, automationName, deviceName);
		AppiumDriver<MobileElement> driver1 = cu1.launchChrome();
		driver = driver1;
		cu = cu1;

		cu.closeAllTabsAndOneTabInChromeBrowser(driver);
		cu.clickAndEnterURLInSearchOrTypeWebAddress(driver, shortenURL_Two);

		String retailer_Url_Eight = new IntermediateRetailerPage(driver, testInfo).
		validateRetailerPage(linkURL, linkURL).
		getUrlFromDefaultBrowser(linkURL);

		hideKeyboard_All_APILevel();
		backButton_WaitForFewSeconds();
		hideKeyboard_All_APILevel();
		backButton_WaitForFewSeconds();
		hideKeyboard_All_APILevel();
		backButton_WaitForFewSeconds();
		hideKeyboard_All_APILevel();
		backButton_WaitForFewSeconds();
		
		/*******************************************************************************************************/
		
		CashKaroUtility cuTwo = new CashKaroUtility(udid, port, automationName, deviceName);
		driver = cuTwo.launchChromeWebView(driver);

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

		/*******************************************************************************************************/
		
		String sharedID_Five = reportsPage.extractRequiredExitIdUsingStoreNameExitClickType(storeName, "Shared", 4);
		String normalID_Five = reportsPage.extractRequiredExitIdUsingStoreNameExitClickTypeSharedId(storeName, sharedID_Five);
		
		String sharedID_Six = reportsPage.extractRequiredExitIdUsingStoreNameExitClickType(storeName, "Shared", 3);
		String normalID_Six = reportsPage.extractRequiredExitIdUsingStoreNameExitClickTypeSharedId(storeName, sharedID_Six);
		
		String sharedID_Seven = reportsPage.extractRequiredExitIdUsingStoreNameExitClickType(storeName, "Shared", 2);
		String normalID_Seven = reportsPage.extractRequiredExitIdUsingStoreNameExitClickTypeSharedId(storeName, sharedID_Seven);
		
		String sharedID_Eight = reportsPage.extractRequiredExitIdUsingStoreNameExitClickType(storeName, "Shared", 1);
		String normalID_Eight = reportsPage.extractRequiredExitIdUsingStoreNameExitClickTypeSharedId(storeName, sharedID_Eight);
		
		/*******************************************************************************************************/
		
		validateTheActualValueContainsExpectedValue(fullDescription_ShortenURL_Seven, fullDescription);
		validateTheActualValueContainsExpectedValue(fullDescription_ShortenURL_Seven, getTestData(7, "shortenURL")+sharedID_Five.toLowerCase());
		
		validateTheActualValueContainsExpectedValue(fullDescription_ShortenURL_Eight, fullDescription);
		validateTheActualValueContainsExpectedValue(fullDescription_ShortenURL_Eight, getTestData(7, "shortenURL")+sharedID_Five.toLowerCase());

		validateTheActualValueContainsExpectedValue(retailer_Url_Five.toLowerCase(), normalID_Five.toLowerCase());
		
		/*******************************************************************************************************/
		
		validateTheActualValueContainsExpectedValue(shortenURL_Two, getTestData(7, "shortenURL")+sharedID_Six.toLowerCase());
		validateTheActualValueContainsExpectedValue(retailer_Url_Eight.toLowerCase(), normalID_Six.toLowerCase());
		
		/*******************************************************************************************************/
		
		validateTheActualValueContainsExpectedValue(fullDescription_ShortenURL_Nine, fullDescription);
		validateTheActualValueContainsExpectedValue(fullDescription_ShortenURL_Nine, getTestData(7, "shortenURL")+sharedID_Seven.toLowerCase());
		
		validateTheActualValueContainsExpectedValue(fullDescription_ShortenURL_Ten, fullDescription);
		validateTheActualValueContainsExpectedValue(fullDescription_ShortenURL_Ten, getTestData(7, "shortenURL")+sharedID_Seven.toLowerCase());

		validateTheActualValueContainsExpectedValue(retailer_Url_Seven.toLowerCase(), normalID_Eight.toLowerCase());
		
		/*******************************************************************************************************/
		
		validateTheActualValueContainsExpectedValue(fullDescription_ShortenURL_Eleven, fullDescription);
		validateTheActualValueContainsExpectedValue(fullDescription_ShortenURL_Eleven, getTestData(7, "shortenURL")+sharedID_Eight.toLowerCase());
		
		validateTheActualValueContainsExpectedValue(fullDescription_ShortenURL_Twelve, fullDescription);
		validateTheActualValueContainsExpectedValue(fullDescription_ShortenURL_Twelve, getTestData(7, "shortenURL")+sharedID_Eight.toLowerCase());

		validateTheActualValueContainsExpectedValue(retailer_Url_Six.toLowerCase(), normalID_Seven.toLowerCase());
		
		/*******************************************************************************************************/

	}

	@Test
	public void storeCategoryPage_ValidateN18StoreOneStoreCardValuesInStoreCategoryPageAndStoreDetailPage_TC08() {

		String storeName = CashKaroUtility.extractValuesFromStoreCategoryJsonAndLoadTheVariablesForStore("N18_Store_One", 4, "N18_Store_One_Name");
		String shortDescription = CashKaroUtility.extractValuesFromStoreCategoryJsonAndLoadTheVariablesForStore("N18_Store_One", 4, "N18_Store_One_App_Short_Description");
		String linkURL = CashKaroUtility.extractValuesFromStoreCategoryJsonAndLoadTheVariablesForStore("N18_Store_One", 4, "N18_Store_One_linkURL");
		String primaryProfitValue = "0";
		String fullDescription = "App Full Description";
				
		reportStep("TC03 is  started", "INFO");

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

		/*******************************************************************************************************/
		
		new HomePage(driver, testInfo).
		clickOnHamburgerIcon().
		presenceOfHomeIcon().
		clickRetailerCategory().
		clickAutomationSpecificCategoryInRetailerCategoryMenuUntilAllSubCategoryVisible().
		clickAutomationSpecificSubCategoryOne().
		clickPopularTab().
		clickStoreCardImage(shortDescription).
		clickBackButton().
		clickAndVerifyStoreCardShortDescription(shortDescription).
		clickBackButton().
		clickOnAddToShareButton(shortDescription).
		clickOnShareIconWithCount(1).
		validateStoreImageInPopup(shortDescription).
		validateStoreNameInPopup(shortDescription).
		validateShortDescriptionInPopup(shortDescription).
		clickOnShareNowInPopup();

		WhatsAppUtility whatsAppUtility = new WhatsAppUtility(driver, testInfo, udid, port, automationName, deviceName, systemPort);
				
		String fullDescription_ShortenURL_One = whatsAppUtility.clickOnWhatAppMenuItemSearch().
		enterWhatsAppSearchText("Mallik").
		clickOnRequiredContact().
		clickOnSend().
		getWhatsUpSharedCaption();
		
		String fullDescription_ShortenURL_Two = whatsAppUtility.
		clickOnSend().
		getWhatsUpSharedCaption();
		
		whatsAppUtility.
		clickOnLastSharedLink();
		
		String retailer_Url_One = new IntermediateRetailerPage(driver, testInfo).
		validateRetailerPage(linkURL, linkURL).
		getUrlFromDefaultBrowser(linkURL);

		hideKeyboard_All_APILevel();
		backButton_WaitForFewSeconds();
		hideKeyboard_All_APILevel();
		backButton_WaitForFewSeconds();
		hideKeyboard_All_APILevel();
		backButton_WaitForFewSeconds();
		hideKeyboard_All_APILevel();
		backButton_WaitForFewSeconds();

		/*******************************************************************************************************/
		
		new StoreCategoryPage(driver, testInfo).
		clickOnShareIconWithCount(1).
		clickOnCopyLink().
		clickBackButton_StoreCategoryPage().
		clickOnShareIconWithCount(1).
		clickOncloseButtonInPopup().
		validateShareIconWithCountPresence(1).
		clickOnShareIconWithCount(1).
		clickOnclearAllButtonInPopup().
		clickOnAddToShareButton(shortDescription).
		validateShareIconWithCountPresence(1).
		clickOnRemoveButton(shortDescription).
		validateShareIconWithCountAbsence(1).
		clickOnShareNowButton(shortDescription);
		
		String fullDescription_ShortenURL_Three = whatsAppUtility.clickOnWhatAppMenuItemSearch().
		enterWhatsAppSearchText("Mallik").
		clickOnRequiredContact().
		clickOnSend().
		getWhatsUpSharedCaption();
		
		String fullDescription_ShortenURL_Four = whatsAppUtility.
		clickOnSend().
		getWhatsUpSharedCaption();
		
		whatsAppUtility.
		clickOnLastSharedLink();
		
		String retailer_Url_Two = new IntermediateRetailerPage(driver, testInfo).
		validateRetailerPage(linkURL, linkURL).
		getUrlFromDefaultBrowser(linkURL);

		hideKeyboard_All_APILevel();
		backButton_WaitForFewSeconds();
		hideKeyboard_All_APILevel();
		backButton_WaitForFewSeconds();
		hideKeyboard_All_APILevel();
		backButton_WaitForFewSeconds();
		hideKeyboard_All_APILevel();
		backButton_WaitForFewSeconds();

		/*******************************************************************************************************/
		
		new StoreCategoryPage(driver,testInfo).
		clickOnShareIcon(shortDescription).
		clickOnWhatsApp();

		String fullDescription_ShortenURL_Five = whatsAppUtility.clickOnWhatAppMenuItemSearch().
		enterWhatsAppSearchText("Mallik").
		clickOnRequiredContact().
		clickOnSend().
		getWhatsUpSharedCaption();
		
		String fullDescription_ShortenURL_Six = whatsAppUtility.
		clickOnSend().
		getWhatsUpSharedCaption();
		
		whatsAppUtility.
		clickOnLastSharedLink();
		
		String retailer_Url_Three = new IntermediateRetailerPage(driver, testInfo).
		validateRetailerPage(linkURL, linkURL).
		getUrlFromDefaultBrowser(linkURL);

		/*******************************************************************************************************/

		String shortenURL = getClipBoardText();

		CashKaroUtility cu = new CashKaroUtility(udid, port, automationName, deviceName);
		 driver = cu.launchChrome();

		cu.closeAllTabsAndOneTabInChromeBrowser(driver);
		cu.clickAndEnterURLInSearchOrTypeWebAddress(driver, shortenURL);

		String retailer_Url_Four = new IntermediateRetailerPage(driver, testInfo).
		validateRetailerPage(linkURL, linkURL).
		getUrlFromDefaultBrowser(linkURL);

		/*******************************************************************************************************/
		
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

		/*******************************************************************************************************/
		
		String sharedID_One = reportsPage.extractRequiredExitIdUsingStoreNameExitClickType(storeName, "Shared", 4);
		String normalID_One = reportsPage.extractRequiredExitIdUsingStoreNameExitClickTypeSharedId(storeName, sharedID_One);
		
		String sharedID_Two = reportsPage.extractRequiredExitIdUsingStoreNameExitClickType(storeName, "Shared", 3);
		String normalID_Two = reportsPage.extractRequiredExitIdUsingStoreNameExitClickTypeSharedId(storeName, sharedID_Two);
		
		String sharedID_Three = reportsPage.extractRequiredExitIdUsingStoreNameExitClickType(storeName, "Shared", 2);
		String normalID_Three = reportsPage.extractRequiredExitIdUsingStoreNameExitClickTypeSharedId(storeName, sharedID_Three);
		
		String sharedID_Four = reportsPage.extractRequiredExitIdUsingStoreNameExitClickType(storeName, "Shared", 1);
		String normalID_Four = reportsPage.extractRequiredExitIdUsingStoreNameExitClickTypeSharedId(storeName, sharedID_Four);
		
		/*******************************************************************************************************/
		
		validateTheActualValueContainsExpectedValue(fullDescription_ShortenURL_One, fullDescription);
		validateTheActualValueContainsExpectedValue(fullDescription_ShortenURL_One, getTestData(7, "shortenURL")+sharedID_One.toLowerCase());
		
		validateTheActualValueContainsExpectedValue(fullDescription_ShortenURL_Two, fullDescription);
		validateTheActualValueContainsExpectedValue(fullDescription_ShortenURL_Two, getTestData(7, "shortenURL")+sharedID_One.toLowerCase());

		validateTheActualValueContainsExpectedValue(retailer_Url_One.toLowerCase(), normalID_One.toLowerCase());
		
		/*******************************************************************************************************/
		
		validateTheActualValueContainsExpectedValue(shortenURL, getTestData(7, "shortenURL")+sharedID_Two.toLowerCase());
		validateTheActualValueContainsExpectedValue(retailer_Url_Four.toLowerCase(), normalID_Two.toLowerCase());
		
		/*******************************************************************************************************/
		
		validateTheActualValueContainsExpectedValue(fullDescription_ShortenURL_Three, fullDescription);
		validateTheActualValueContainsExpectedValue(fullDescription_ShortenURL_Three, getTestData(7, "shortenURL")+sharedID_Three.toLowerCase());
		
		validateTheActualValueContainsExpectedValue(fullDescription_ShortenURL_Four, fullDescription);
		validateTheActualValueContainsExpectedValue(fullDescription_ShortenURL_Four, getTestData(7, "shortenURL")+sharedID_Three.toLowerCase());

		validateTheActualValueContainsExpectedValue(retailer_Url_Three.toLowerCase(), normalID_Four.toLowerCase());
		
		/*******************************************************************************************************/
		
		validateTheActualValueContainsExpectedValue(fullDescription_ShortenURL_Five, fullDescription);
		validateTheActualValueContainsExpectedValue(fullDescription_ShortenURL_Five, getTestData(7, "shortenURL")+sharedID_Four.toLowerCase());
		
		validateTheActualValueContainsExpectedValue(fullDescription_ShortenURL_Six, fullDescription);
		validateTheActualValueContainsExpectedValue(fullDescription_ShortenURL_Six, getTestData(7, "shortenURL")+sharedID_Four.toLowerCase());

		validateTheActualValueContainsExpectedValue(retailer_Url_Two.toLowerCase(), normalID_Three.toLowerCase());

		/*******************************************************************************************************/

		CashKaroUtility reopenCKApp = new CashKaroUtility(udid, port, automationName, deviceName, systemPort);
		driver = reopenCKApp.launchEarnKaroApp(driver);

		/*******************************************************************************************************/

		new HomePage(driver, testInfo).
		clickOnHamburgerIcon().
		presenceOfHomeIcon().
		clickRetailerCategory().
		clickAutomationSpecificCategoryInRetailerCategoryMenuUntilAllSubCategoryVisible().
		clickAutomationSpecificSubCategoryOne().
		clickPopularTab().
		clickAndVerifyStoreCardShortDescription(shortDescription).
		validateStoreImage(shortDescription).
		validateStoreNameInPopup(storeName).
		validateShortDescriptionInPopup(storeName).
		validateprimaryProfit(primaryProfitValue).
		clickOnAddToShareButton().
		clickOnShareIconWithCount(1).
		validateStoreImageInPopup(shortDescription).
		validateStoreNameInPopup(shortDescription).
		validateShortDescriptionInPopup(shortDescription).		
		clickOnShareNowInPopup();

		WhatsAppUtility whatsAppUtilityOne = new WhatsAppUtility(driver, testInfo, udid, port, automationName, deviceName, systemPort);
	
		String fullDescription_ShortenURL_Seven = whatsAppUtilityOne.clickOnWhatAppMenuItemSearch().
		enterWhatsAppSearchText("Mallik").
		clickOnRequiredContact().
		clickOnSend().
		getWhatsUpSharedCaption();
		
		String fullDescription_ShortenURL_Eight = whatsAppUtilityOne.
		clickOnSend().
		getWhatsUpSharedCaption();
		
		whatsAppUtilityOne.
		clickOnLastSharedLink();
		
		String retailer_Url_Five = new IntermediateRetailerPage(driver, testInfo).
		validateRetailerPage(linkURL, linkURL).
		getUrlFromDefaultBrowser(linkURL);

		hideKeyboard_All_APILevel();
		backButton_WaitForFewSeconds();
		hideKeyboard_All_APILevel();
		backButton_WaitForFewSeconds();
		hideKeyboard_All_APILevel();
		backButton_WaitForFewSeconds();
		hideKeyboard_All_APILevel();
		backButton_WaitForFewSeconds();

		/*******************************************************************************************************/

		new StoreDetailPage(driver, testInfo).
		clickOnRemoveButton().
		clickOnAddToShareButton().
		clickOnShareIconWithCount(1).
		clickOnCopyLink().
		clickBackButton_StoreDetailPage().
		clickOnShareIconWithCount(1).
		clickOncloseButtonInPopup().
		clickOnShareIconWithCount(1).
		clickOnclearAllButtonInPopup().
		validateShareIconWithCountAbsence(1).
		clickOnAddToShareButton().
		validateShareIconWithCountPresence(1).
		clickOnRemoveButton().
		validateShareIconWithCountAbsence(1).
 		clickOnAddToShareButton().
		clickOnShareNow();
	
		String fullDescription_ShortenURL_Nine = whatsAppUtilityOne.clickOnWhatAppMenuItemSearch().
		enterWhatsAppSearchText("Mallik").
		clickOnRequiredContact().
		clickOnSend().
		getWhatsUpSharedCaption();
		
		String fullDescription_ShortenURL_Ten = whatsAppUtilityOne.
		clickOnSend().
		getWhatsUpSharedCaption();
		
		whatsAppUtilityOne.
		clickOnLastSharedLink();
		
		String retailer_Url_Six = new IntermediateRetailerPage(driver, testInfo).
		validateRetailerPage(linkURL, linkURL).
		getUrlFromDefaultBrowser(linkURL);

		hideKeyboard_All_APILevel();
		backButton_WaitForFewSeconds();
		hideKeyboard_All_APILevel();
		backButton_WaitForFewSeconds();
		hideKeyboard_All_APILevel();
		backButton_WaitForFewSeconds();
		hideKeyboard_All_APILevel();
		backButton_WaitForFewSeconds();

		/*******************************************************************************************************/

		new StoreDetailPage(driver,testInfo).
		clickOnShareIcon().
		clickOnWhatsApp();

		String fullDescription_ShortenURL_Eleven = whatsAppUtilityOne.clickOnWhatAppMenuItemSearch().
		enterWhatsAppSearchText("Mallik").
		clickOnRequiredContact().
		clickOnSend().
		getWhatsUpSharedCaption();
		
		String fullDescription_ShortenURL_Twelve = whatsAppUtilityOne.
		clickOnSend().
		getWhatsUpSharedCaption();
		
		whatsAppUtilityOne.
		clickOnLastSharedLink();
		
		String retailer_Url_Seven = new IntermediateRetailerPage(driver, testInfo).
		validateRetailerPage(linkURL, linkURL).
		getUrlFromDefaultBrowser(linkURL);

		hideKeyboard_All_APILevel();
		backButton_WaitForFewSeconds();
		hideKeyboard_All_APILevel();
		backButton_WaitForFewSeconds();
		hideKeyboard_All_APILevel();
		backButton_WaitForFewSeconds();
		hideKeyboard_All_APILevel();
		backButton_WaitForFewSeconds();

		/*******************************************************************************************************/

		String shortenURL_Two = getClipBoardText(driver);

		CashKaroUtility cu1 = new CashKaroUtility(udid, port, automationName, deviceName);
		AppiumDriver<MobileElement> driver1 = cu1.launchChrome();
		driver = driver1;
		cu = cu1;

		cu.closeAllTabsAndOneTabInChromeBrowser(driver);
		cu.clickAndEnterURLInSearchOrTypeWebAddress(driver, shortenURL_Two);

		String retailer_Url_Eight = new IntermediateRetailerPage(driver, testInfo).
		validateRetailerPage(linkURL, linkURL).
		getUrlFromDefaultBrowser(linkURL);

		hideKeyboard_All_APILevel();
		backButton_WaitForFewSeconds();
		hideKeyboard_All_APILevel();
		backButton_WaitForFewSeconds();
		hideKeyboard_All_APILevel();
		backButton_WaitForFewSeconds();
		hideKeyboard_All_APILevel();
		backButton_WaitForFewSeconds();
		
		/*******************************************************************************************************/
		
		CashKaroUtility cuTwo = new CashKaroUtility(udid, port, automationName, deviceName);
		driver = cuTwo.launchChromeWebView(driver);

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

		/*******************************************************************************************************/
		
		String sharedID_Five = reportsPage.extractRequiredExitIdUsingStoreNameExitClickType(storeName, "Shared", 4);
		String normalID_Five = reportsPage.extractRequiredExitIdUsingStoreNameExitClickTypeSharedId(storeName, sharedID_Five);
		
		String sharedID_Six = reportsPage.extractRequiredExitIdUsingStoreNameExitClickType(storeName, "Shared", 3);
		String normalID_Six = reportsPage.extractRequiredExitIdUsingStoreNameExitClickTypeSharedId(storeName, sharedID_Six);
		
		String sharedID_Seven = reportsPage.extractRequiredExitIdUsingStoreNameExitClickType(storeName, "Shared", 2);
		String normalID_Seven = reportsPage.extractRequiredExitIdUsingStoreNameExitClickTypeSharedId(storeName, sharedID_Seven);
		
		String sharedID_Eight = reportsPage.extractRequiredExitIdUsingStoreNameExitClickType(storeName, "Shared", 1);
		String normalID_Eight = reportsPage.extractRequiredExitIdUsingStoreNameExitClickTypeSharedId(storeName, sharedID_Eight);
		
		/*******************************************************************************************************/
		
		validateTheActualValueContainsExpectedValue(fullDescription_ShortenURL_Seven, fullDescription);
		validateTheActualValueContainsExpectedValue(fullDescription_ShortenURL_Seven, getTestData(7, "shortenURL")+sharedID_Five.toLowerCase());
		
		validateTheActualValueContainsExpectedValue(fullDescription_ShortenURL_Eight, fullDescription);
		validateTheActualValueContainsExpectedValue(fullDescription_ShortenURL_Eight, getTestData(7, "shortenURL")+sharedID_Five.toLowerCase());

		validateTheActualValueContainsExpectedValue(retailer_Url_Five.toLowerCase(), normalID_Five.toLowerCase());
		
		/*******************************************************************************************************/
		
		validateTheActualValueContainsExpectedValue(shortenURL_Two, getTestData(7, "shortenURL")+sharedID_Six.toLowerCase());
		validateTheActualValueContainsExpectedValue(retailer_Url_Eight.toLowerCase(), normalID_Six.toLowerCase());
		
		/*******************************************************************************************************/
		
		validateTheActualValueContainsExpectedValue(fullDescription_ShortenURL_Nine, fullDescription);
		validateTheActualValueContainsExpectedValue(fullDescription_ShortenURL_Nine, getTestData(7, "shortenURL")+sharedID_Seven.toLowerCase());
		
		validateTheActualValueContainsExpectedValue(fullDescription_ShortenURL_Ten, fullDescription);
		validateTheActualValueContainsExpectedValue(fullDescription_ShortenURL_Ten, getTestData(7, "shortenURL")+sharedID_Seven.toLowerCase());

		validateTheActualValueContainsExpectedValue(retailer_Url_Seven.toLowerCase(), normalID_Eight.toLowerCase());
		
		/*******************************************************************************************************/
		
		validateTheActualValueContainsExpectedValue(fullDescription_ShortenURL_Eleven, fullDescription);
		validateTheActualValueContainsExpectedValue(fullDescription_ShortenURL_Eleven, getTestData(7, "shortenURL")+sharedID_Eight.toLowerCase());
		
		validateTheActualValueContainsExpectedValue(fullDescription_ShortenURL_Twelve, fullDescription);
		validateTheActualValueContainsExpectedValue(fullDescription_ShortenURL_Twelve, getTestData(7, "shortenURL")+sharedID_Eight.toLowerCase());

		validateTheActualValueContainsExpectedValue(retailer_Url_Six.toLowerCase(), normalID_Seven.toLowerCase());
		
		/*******************************************************************************************************/

	}
	
}