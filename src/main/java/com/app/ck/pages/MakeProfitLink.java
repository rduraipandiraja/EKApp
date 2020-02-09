package com.app.ck.pages;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import com.app.ck.base.MakeLinkURLS;
import com.app.ck.base.WrapperMethods;
import com.app.ck.pages.admin.AdminCoreFunction;
import com.app.ck.utilities.CashKaroUtility;
import com.aventstack.extentreports.ExtentTest;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class MakeProfitLink extends WrapperMethods {

	//Constructor call
	public MakeProfitLink(AppiumDriver < MobileElement > driver, ExtentTest testInfo) {

		WebDriverWait wait = new WebDriverWait(driver, 30);

		this.driver = driver;
		this.testInfo = testInfo;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);

		reportStep("Waiting for the MakeProfitLink page ", "INFO");
		try {

			wait.until(ExpectedConditions.visibilityOf(MakeProfitLinkPageTitle));
			reportStep("Successfully landed on the MakeProfitLink  page ", "PASS");

		} catch (Exception e) {

			reportStep(e.getMessage(), "INFO");
			reportStep("Not able to land on the MakeProfitLink Page  ", "FAIL");
		}

	}

	//Xpath  :
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text  = 'Make Links']")
	MobileElement MakeProfitLinkPageTitle;
	@FindBy(how = How.XPATH, using = "//android.widget.EditText[@content-desc=\"input_makeProfit_profitlink_url\"]")
	MobileElement pasteLinkField;
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@content-desc=\"btn_makeProfit_checkChanges\"]")
	MobileElement makeProfitLinkButton;
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@content-desc=\"txt_makeProfit_success_msg\"]")
	MobileElement makeProfitLinkShortURL;
	@FindBy(how = How.XPATH, using = "//*[contains(@text,'Share this deal with your friends, when your friends shop through this link, you get money')]")
	MobileElement shareThisDealText;
	@FindBy(how = How.XPATH, using = "//android.view.ViewGroup[@content-desc=\"txt_makeProfit_whatsapp_share_top\"]")
	MobileElement makeProfitWhatsAppShareNowButton;
	@FindBy(how = How.XPATH, using = "//android.view.ViewGroup[@content-desc=\"txt_makeProfit_others_share_top\"]")
	MobileElement makeProfitothersButton;
	@FindBy(how = How.XPATH, using = "//android.view.ViewGroup[@content-desc=\"txt_makeProfit_copy_link_top\"]")
	MobileElement makeProfitcopyLinkButton;
	@FindBy(how = How.XPATH, using = "//*[contains(@text,'Please enter URL')]")
	MobileElement pleaseEnterURLText;
	@FindBy(how = How.XPATH, using = "//*[contains(@text,'Please enter valid URL')]")
	MobileElement pleaseEnterValidURLText;
	@FindBy(how = How.XPATH, using = "//*[contains(@text,'Invalid retailer URL')]")
	MobileElement invalidRetailerURL;
	@FindBy(how = How.XPATH, using = "//*[contains(@text,'The link format is invalid.')]")
	MobileElement theLinkFormatIsInvalid;
	@FindBy(how = How.XPATH, using = "//*[contains(@text,'Too many retailer URLs in pasted link')]")
	MobileElement tooManyURLsError;

	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='SEE PARTNERS & PROFIT RATES']")
	MobileElement seePartnersButton;

	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='History']")
	MobileElement historyIcon;
	
	//Paste the URL
	public MakeProfitLink pasteTheURL(String url) {

		reportStep("About to paste the Profit Link  :  " + url, "INFO");

		if (enterText(pasteLinkField, url)) {

			reportStep("Successfully Pasted  the URL  :  " + url, "PASS");

		} else {

			reportStep("Failed to Paste the URL : " + url, "FAIL");
		}

		return this;

	}

	public MakeProfitLink clickOnMakeProfitLink() {

		reportStep("About to click on the Make Profit link button ", "INFO");

		if (click(makeProfitLinkButton)) {

			reportStep("Successfully clicked on the MakeProfit link button", "PASS");
		} else {

			reportStep("Failed to click on the Make Profit link button ", "FAIL");
		}

		return this;

	}

	public MakeProfitLink validatePleaseEnterURL() {

		reportStep("About to validate the Please enter URL Error Message ", "INFO");

		validateTheElementPresence(pleaseEnterURLText);

		return this;


	}

	public MakeProfitLink pleaseEnterValidURL() {

		reportStep("About to validate Please enter Valid URL ", "INFO");

		validateTheElementPresence(pleaseEnterValidURLText);

		return this;
	}

	public String validateShortenURL() {

		reportStep("About to validate the EarnKaro Shorten URL - Whether it is generated or Not ", "INFO");

		validateTheElementPresence(makeProfitLinkShortURL);

		reportStep("The shorten URL generated is : " + makeProfitLinkShortURL.getText(), "INFO");
		System.out.println("Shrot link : " + makeProfitLinkShortURL.getText());

		return makeProfitLinkShortURL.getText();
	}

	public MakeProfitLink invalidRetailerURL() {

		reportStep("About to validate the Invalid Retailer error Validation ", "INFO");
		validateTheElementPresence(invalidRetailerURL);
		return this;

	}

	public MakeProfitLink linkFormatIsInvalid() {

		reportStep("About to validate the Invalid Link Format ", "INFO");
		validateTheElementPresence(theLinkFormatIsInvalid);
		return this;

	}

	public MakeProfitLink validateTooManyURLsInPastedLink() {

		reportStep("About to validate Too many link in the pasted urls ", "INFO");
		validateTheElementPresence(tooManyURLsError);
		return this;

	}

	public void assertProfitLinkURLWithSellerLandedURL(String profitLink, String sellerLink) {

		reportStep("About to Assert the Profit link URL with the Seller URL (Seller URL must contain the Network tags as specified in the Network's Product Cashback Rule  ", "INFO");

		if (profitLink.equals(sellerLink)) {
			reportStep("Actual : " + profitLink + " & Expected : " + sellerLink, "INFO");
			reportStep("Successfully validated the Profilt lInk assertion with the required seller Cashback URL  ", "PASS");

		} else {
			reportStep("Actual : " + profitLink + " & Expected : " + sellerLink, "INFO");
			reportStep("Failed to  validate the Profilt lInk assertion with the required seller Cashback URL : It is a critical issue check this immediately  ", "FAIL");
		}

	}

	@Test
	public void validSharedProductURL_ForAmazon() {


		//Cashbac URL :  {PRDURL}&tag={AFFLTID}&ascsubtag={EXTID}{(ihre_partner_id)AFFLTID}

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo);
		String email = objCashKaroUtility.generateEmail();
		String mobNum = objCashKaroUtility.generateMobileNumber();

		String otpValue = new EKOnboardingPage(driver, testInfo).
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

		MakeProfitLink objMakeProfitLinkPage = objHomePage.clickOnProfileIconForSignedUser().
				clickOnMakeProfitLink().
				pasteTheURL(MakeLinkURLS.VALIDPRODUCTURL).
				clickOnMakeProfitLink();

		String profitLink = objMakeProfitLinkPage.validateShortenURL();

		CashKaroUtility cuOne = new CashKaroUtility(udid, port, automationName, deviceName);
		driver = cuOne.launchChromeWebView(driver);

		driver.get(profitLink);
		String sellerURL = driver.getCurrentUrl();
		System.out.println("First time " + sellerURL);


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

		System.out.println("Exit click id is : " + exitClickID);
		reportStep("Exit click id is : " + exitClickID, "INFO");

		objMakeProfitLinkPage.assertProfitLinkURLWithSellerLandedURL(sellerURL, MakeLinkURLS.VALIDPRODUCTURL_SELLER + exitClickID);

	}

	public PartnersPage clickOnSeePartnersButton() {

		reportStep("About to click on the SeePartnersButton", "INFO");

		if (click(seePartnersButton)) {

			reportStep("Successfully clicked on the SeePartnersButton", "PASS");
		} else {

			reportStep("Failed to click on the SeePartnersButton", "FAIL");
		}

		return new PartnersPage(driver, testInfo);

	}

	public void validateMakeProfitLinkBasedOnStoreStatus(String storeStatus) {

		reportStep("About to validate the Make Profit link - based on the Store status : ", "INFO");
		reportStep("If the Store Status is Active : Profit link has to get generated if not ,  Invalid retailer error has to be shown ", "INFO");

		if (storeStatus.equalsIgnoreCase("Active")) {

			String shortenURL = this.validateShortenURL();
			reportStep("ShortenURL generated is: " + shortenURL, "INFO");

		} else if (storeStatus.equalsIgnoreCase("InActive")) {

			this.invalidRetailerURL();

		} else {

			reportStep("Test Case failed due to invalid Parameter passing as : " + storeStatus, "FAIL");
		}



	}

	public MakeProfitLink validateNetworkErrorsInMakeLinksPage() {

		reportStep("About to TurnOff the Wifi", "Info");
		//Wifi - ON to OFF
		toggleWifi();

		new NetworkErrorPage(driver, testInfo).
		validateNetworkPageErrors();

		//Wifi - OFF to ON
		toggleWifi();
		new MakeProfitLink(driver, testInfo);
		reportStep("Successfully Turned On the Wifi ", "Pass");
		return this;

	}

	public HistoryPage clickOnHistoryIcon() {

		reportStep("About to click on the History from the Home Page ", "INFO");

		if(click(historyIcon)) {

			reportStep("Successfully clicked on the History from the Home Page ", "PASS");
		} else {

			reportStep("Failed to click on the History from the Home Page ", "FAIL");
		}


		return new HistoryPage(driver, testInfo);
	}



}