package com.app.ck.pages;


import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.app.ck.base.WrapperMethods;
import com.aventstack.extentreports.ExtentTest;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class DeepLinkPage extends WrapperMethods {

	//Constructor call
	public DeepLinkPage(AppiumDriver<MobileElement> driver, ExtentTest testInfo) {

		WebDriverWait wait = new WebDriverWait(driver, 30);

		this.driver = driver;
		this.testInfo = testInfo;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);

	}

	@FindBy(how = How.XPATH, using = "//android.view.View[@text='Home']") 
	MobileElement homeLink;

	@FindBy(how = How.XPATH, using = "//android.view.View[@text='Store Page - Make my trip Store']") 
	MobileElement amazonStoreLink;

	@FindBy(how = How.XPATH, using = "//android.view.View[@text='JOIN FREE']") 
	MobileElement joinFreeLink;

	@FindBy(how = How.XPATH, using = "//android.view.View[@text='Sign In']") 
	MobileElement signInLink;

	@FindBy(how = How.XPATH, using = "//android.view.View[@text='Product Category Page - Mobile Phones']") 
	MobileElement mobilePhonesLink;

	@FindBy(how = How.XPATH, using = "//android.view.View[@text='Product Category Page - Mobile Phones Filter']") 
	MobileElement mobilePhonesFilterLink;

	@FindBy(how = How.XPATH, using = "//android.view.View[@text='Popular Today']") 
	MobileElement popularTodayLink;

	@FindBy(how = How.XPATH, using = "//android.view.View[@text='Store Category Page - Clothing']") 
	MobileElement clothingLink;

	@FindBy(how = How.XPATH, using = "//android.view.View[@text='Store Category Page - App Exclusive']") 
	MobileElement appExclusiveLink;

	@FindBy(how = How.XPATH, using = "//android.view.View[@text='Product Feeds Page - Highest Profit Stores']") 
	MobileElement highestCashbackLink;

	@FindBy(how = How.XPATH, using = "//android.view.View[@text='How IT Works']") 
	MobileElement howItWorksLink;

	@FindBy(how = How.XPATH, using = "//android.view.View[@text='Testimonials - All']") 
	MobileElement testimonialsLink;

	@FindBy(how = How.XPATH, using = "//android.view.View[@text='Contact US']") 
	MobileElement contactUsLink;

	@FindBy(how = How.XPATH, using = "//android.view.View[@text='Seach Result Page - of']") 
	MobileElement searchResultsPageLink;

	@FindBy(how = How.XPATH, using = "//android.view.View[@text='Seach Result Store - of']") 
	MobileElement searchResultsStoreLink;

	@FindBy(how = How.XPATH, using = "//android.view.View[@text='Seach Result Products - Apple']") 
	MobileElement searchResultsProductsLink;

	@FindBy(how = How.XPATH, using = "//android.view.View[@text='Seach Result Products - Apple - Filter']") 
	MobileElement searchResultsProductsFilterLink;

	@FindBy(how = How.XPATH, using = "//android.view.View[@text='PPS Product Page']") 
	MobileElement ppsProductLink;

	@FindBy(how = How.XPATH, using = "//android.view.View[@text='Terms Conditions']") 
	MobileElement termsAndConditionsLink;

	@FindBy(how = How.XPATH, using = "//android.view.View[@text='Forgot Password']") 
	MobileElement forgotPasswordLink;

	@FindBy(how = How.XPATH, using = "//android.view.View[@text='Settings']") 
	MobileElement settingsLink;

	@FindBy(how = How.XPATH, using = "//android.view.View[@text='Settings - Profile']") 
	MobileElement settingsProfileLink;

	@FindBy(how = How.XPATH, using = "//android.view.View[@text='Recent Clicks']") 
	MobileElement recentClicksLink;

	@FindBy(how = How.XPATH, using = "//android.view.View[@text='My Earnings']") 
	MobileElement myEarningsLink;

	@FindBy(how = How.XPATH, using = "//android.view.View[@text='History']") 
	MobileElement mySharedActivitiesLink;

	@FindBy(how = How.XPATH, using = "//android.view.View[@text='Make Profit Link']") 
	MobileElement makeProfitLink;

	@FindBy(how = How.XPATH, using = "//android.view.View[@text='Success Stories']") 
	MobileElement successStoriesLink;

	@FindBy(how = How.XPATH, using = "//android.view.View[@text='Refer And Earn']") 
	MobileElement referEarnLink;

	@FindBy(how = How.XPATH, using = "//android.view.View[@text='My Referral Network']") 
	MobileElement myReferralNetworkLink;

	@FindBy(how = How.XPATH, using = "//android.view.View[@text='Faq - All']") 
	MobileElement faqLink;

	@FindBy(how = How.XPATH, using = "//android.view.View[@text='Payment']") 
	MobileElement paymentLink;

	 String homeLinkXpath = "//android.view.View[@text='Home']";
		

	 String amazonStoreLinkXpath = "//android.view.View[@text='Store Page - Make my trip Store']";
	

	 String joinFreeLinkXpath = "//android.view.View[@text='JOIN FREE']";
	

	 String signInLinkXpath = "//android.view.View[@text='Sign In']";
	

	 String mobilePhonesLinkXpath = "//android.view.View[@text='Product Category Page - Mobile Phones']";
	

	 String mobilePhonesFilterLinkXpath = "//android.view.View[@text='Product Category Page - Mobile Phones Filter']";
	

	 String popularTodayLinkXpath = "//android.view.View[@text='Popular Today']";
	

	 String clothingLinkXpath = "//android.view.View[@text='Store Category Page - Clothing']";
	

	 String appExclusiveLinkXpath = "//android.view.View[@text='Store Category Page - App Exclusive']";
	

	 String highestCashbackLinkXpath = "//android.view.View[@text='Product Feeds Page - Highest Profit Stores']";
	

	 String howItWorksLinkXpath = "//android.view.View[@text='How IT Works']";
	

	 String testimonialsLinkXpath = "//android.view.View[@text='Testimonials - All']";
	

	 String contactUsLinkXpath = "//android.view.View[@text='Contact US']";
	

	 String searchResultsPageLinkXpath = "//android.view.View[@text='Seach Result Page - of']";
	

	 String searchResultsStoreLinkXpath = "//android.view.View[@text='Seach Result Store - of']";
	

	 String searchResultsProductsLinkXpath = "//android.view.View[@text='Seach Result Products - Apple']";
	

	 String searchResultsProductsFilterLinkXpath = "//android.view.View[@text='Seach Result Products - Apple - Filter']";
	

	 String ppsProductLinkXpath = "//android.view.View[@text='PPS Product Page']";
	

	 String termsAndConditionsLinkXpath = "//android.view.View[@text='Terms Conditions']";
	

	 String forgotPasswordLinkXpath = "//android.view.View[@text='Forgot Password']";
	

	 String settingsLinkXpath = "//android.view.View[@text='Settings']";
	

	 String settingsProfileLinkXpath = "//android.view.View[@text='Settings - Profile']";
	

	 String recentClicksLinkXpath = "//android.view.View[@text='Recent Clicks']";
	

	 String myEarningsLinkXpath = "//android.view.View[@text='My Earnings']";
	

	 String mySharedActivitiesLinkXpath = "//android.view.View[@text='History']";
	

	 String makeProfitLinkXpath = "//android.view.View[@text='Make Profit Link']";
	

	 String successStoriesLinkXpath = "//android.view.View[@text='Success Stories']";
	

	 String referEarnLinkXpath = "//android.view.View[@text='Refer And Earn']";
	

	 String myReferralNetworkLinkXpath = "//android.view.View[@text='My Referral Network']";
	

	 String faqLinkXpath = "//android.view.View[@text='Faq - All']";
	

	 String paymentLinkXpath = "//android.view.View[@text='Payment']";
	

	public void clickHomeLink() {
		
		reportStep("About to click on home link", "INFO");	

		scrollTillRequiredElementIsVisibleFromDownToUpFor10Times(homeLinkXpath);
	
		click(homeLink);

		reportStep("Successfully clicked on home link", "PASS");
		
	}
	
	public void clickMakeMyTripStoreLink() {
		
		reportStep("About to click on MakeMyTripStoreLink", "INFO");	

		scrollTillRequiredElementIsVisibleFromDownToUpFor10Times(amazonStoreLinkXpath);
	
		click(amazonStoreLink);

		reportStep("Successfully clicked on MakeMyTripStoreLink", "PASS");
		
	}

	public void clickJoinFreeLink() {
		
		reportStep("About to click on joinFreeLink", "INFO");	

		scrollTillRequiredElementIsVisibleFromDownToUpFor10Times(joinFreeLinkXpath);
	
		click(joinFreeLink);

		reportStep("Successfully clicked on joinFreeLink", "PASS");
	}

	public void clickSignInLink() {
		
		reportStep("About to click on signInLink", "INFO");	

		scrollTillRequiredElementIsVisibleFromDownToUpFor10Times(signInLinkXpath);
	
		click(signInLink);

		reportStep("Successfully clicked on signInLink", "PASS");
	}

	public void clickMobilePhonesLink() {
		
		reportStep("About to click on mobilePhonesLink", "INFO");	

		scrollTillRequiredElementIsVisibleFromDownToUpFor10Times(mobilePhonesLinkXpath);
	
		click(mobilePhonesLink);

		reportStep("Successfully clicked on mobilePhonesLink", "PASS");
		
	}

	public void clickMobilePhonesFilterLink() {
		
		reportStep("About to click on mobilePhonesFilterLink", "INFO");	

		scrollTillRequiredElementIsVisibleFromDownToUpFor10Times(mobilePhonesFilterLinkXpath);
	
		click(mobilePhonesFilterLink);

		reportStep("Successfully clicked on mobilePhonesFilterLink", "PASS");
		
	}

	public void clickPopularTodayLink() {
		
		reportStep("About to click on popularTodayLink", "INFO");	

		scrollTillRequiredElementIsVisibleFromDownToUpFor10Times(popularTodayLinkXpath);
	
		click(popularTodayLink);

		reportStep("Successfully clicked on popularTodayLink", "PASS");
	}

	public void clickClothingLink() {
		
		reportStep("About to click on clothingLink", "INFO");	

		scrollTillRequiredElementIsVisibleFromDownToUpFor10Times(clothingLinkXpath);
	
		click(clothingLink);

		reportStep("Successfully clicked on clothingLink", "PASS");
	}

	public void clickAppExclusiveLink() {
		
		reportStep("About to click on appExclusiveLink", "INFO");		

		scrollTillRequiredElementIsVisibleFromDownToUpFor10Times(appExclusiveLinkXpath);
	
		click(appExclusiveLink);

		reportStep("Successfully clicked on appExclusiveLink", "PASS");
		
	}

	public void clickHighestCashbackLink() {
		
		reportStep("About to click on highestCashbackLink", "INFO");	

		scrollTillRequiredElementIsVisibleFromDownToUpFor10Times(highestCashbackLinkXpath);
	
		click(highestCashbackLink);

		reportStep("Successfully clicked on highestCashbackLink", "PASS");
		
	}

	public void clickHowItWorksLink() {
		
		reportStep("About to click on howItWorksLink", "INFO");	

		scrollTillRequiredElementIsVisibleFromDownToUpFor10Times(howItWorksLinkXpath);	
	
		click(howItWorksLink);

		reportStep("Successfully clicked on howItWorksLink", "PASS");
		
	}

	public void clickTestimonialsLink() {
		
		reportStep("About to click on testimonialsLink", "INFO");	

		scrollTillRequiredElementIsVisibleFromDownToUpFor10Times(testimonialsLinkXpath);	
	
		click(testimonialsLink);

		reportStep("Successfully clicked on testimonialsLink", "PASS");
		
	}

	public void clickContactUsLink() {
		
		reportStep("About to click on contactUsLink", "INFO");	

		scrollTillRequiredElementIsVisibleFromDownToUpFor10Times(contactUsLinkXpath);	

		click(contactUsLink);

		reportStep("Successfully clicked on contactUsLink", "PASS");
		
	}

	public void clickSearchResultsPageLink() {
		
		reportStep("About to click on searchResultsPageLink", "INFO");	

		scrollTillRequiredElementIsVisibleFromDownToUpFor10Times(searchResultsPageLinkXpath);	
	
		click(searchResultsPageLink);

		reportStep("Successfully clicked on searchResultsPageLink", "PASS");
	}

	public void clickSearchResultsStoreLink() {
		
		reportStep("About to click on searchResultsStoreLink", "INFO");	

		scrollTillRequiredElementIsVisibleFromDownToUpFor10Times(searchResultsStoreLinkXpath);		
	
		click(searchResultsStoreLink);

		reportStep("Successfully clicked on searchResultsStoreLink", "PASS");
	}

	public void clickSearchResultsProductsLink() {
		
		reportStep("About to click on searchResultsProductsLink", "INFO");

		scrollTillRequiredElementIsVisibleFromDownToUpFor10Times(searchResultsProductsLinkXpath);		
	
		click(searchResultsProductsLink);

		reportStep("Successfully clicked on searchResultsProductsLink", "PASS");
	}

	public void clickSearchResultsProductsFilterLink() {
		
		reportStep("About to click on searchResultsProductsFilterLink", "INFO");

		scrollTillRequiredElementIsVisibleFromDownToUpFor10Times(searchResultsProductsFilterLinkXpath);	
	
		click(searchResultsProductsFilterLink);

		reportStep("Successfully clicked on searchResultsProductsFilterLink", "PASS");
	}

	public void clickPpsProductLink() {
		
		reportStep("About to click on ppsProductLink", "INFO");	

		scrollTillRequiredElementIsVisibleFromDownToUpFor10Times(ppsProductLinkXpath);
	
		click(ppsProductLink);

		reportStep("Successfully clicked on ppsProductLink", "PASS");
	}

	public void clickTermsAndConditionsLink() {
		
		reportStep("About to click on termsAndConditionsLink", "INFO");	

		scrollTillRequiredElementIsVisibleFromDownToUpFor10Times(termsAndConditionsLinkXpath);
		
		click(termsAndConditionsLink);

		reportStep("Successfully clicked on termsAndConditionsLink", "PASS");
		
	}

	public void clickForgotPasswordLink() {
		
		reportStep("About to click on forgotPasswordLink", "INFO");	

		scrollTillRequiredElementIsVisibleFromDownToUpFor10Times(forgotPasswordLinkXpath);
		
		click(forgotPasswordLink);

		reportStep("Successfully clicked on forgotPasswordLink", "PASS");
		
	}

	public void clickSettingsLink() {
		
		reportStep("About to click on settingsLink", "INFO");	

		scrollTillRequiredElementIsVisibleFromDownToUpFor10Times(settingsLinkXpath);
		
		click(settingsLink);

		reportStep("Successfully clicked on settingsLink", "PASS");
		
	}

	public void clickSettingsProfileLink() {
		
		reportStep("About to click on settingsProfileLink", "INFO");

		scrollTillRequiredElementIsVisibleFromDownToUpFor10Times(settingsProfileLinkXpath);
		
		click(settingsProfileLink);

		reportStep("Successfully clicked on settingsProfileLink", "PASS");
		
	}

	public void clickRecentClicksLink() {
		
		reportStep("About to click on recentClicksLink", "INFO");	

		scrollTillRequiredElementIsVisibleFromDownToUpFor10Times(recentClicksLinkXpath);
		
		click(recentClicksLink);

		reportStep("Successfully clicked on recentClicksLink", "PASS");
		
	}

	public void clickMyEarningsLink() {
		
		reportStep("About to click on myEarningsLink", "INFO");	

		scrollTillRequiredElementIsVisibleFromDownToUpFor10Times(myEarningsLinkXpath);
		
		click(myEarningsLink);

		reportStep("Successfully clicked on myEarningsLink", "PASS");
		
	}

	public void clickMySharedActivitiesLink() {
		
		reportStep("About to click on mySharedActivitiesLink", "INFO");	

		scrollTillRequiredElementIsVisibleFromDownToUpFor10Times(mySharedActivitiesLinkXpath);
		
		click(mySharedActivitiesLink);

		reportStep("Successfully clicked on mySharedActivitiesLink", "PASS");
		
	}

	public void clickMakeProfitLinksLink() {
		
		reportStep("About to click on makeProfitLinksLink", "INFO");	

		scrollFromDownToUpinApp_FourSecDuration();
		scrollFromDownToUpinApp_FourSecDuration();
		scrollFromDownToUpinApp_FourSecDuration();
		scrollFromDownToUpinApp_FourSecDuration();
		scrollFromDownToUpinApp_FourSecDuration();
		click(makeProfitLink);

		reportStep("Successfully clicked on makeProfitLinksLink", "PASS");
		
	}

	public void clickSuccessStoriesLink() {
		
		reportStep("About to click on successStoriesLink", "INFO");	

		scrollFromDownToUpinApp_FourSecDuration();
		scrollFromDownToUpinApp_FourSecDuration();
		scrollFromDownToUpinApp_FourSecDuration();
		scrollFromDownToUpinApp_FourSecDuration();
		scrollFromDownToUpinApp_FourSecDuration();
		click(successStoriesLink);

		reportStep("Successfully clicked on successStoriesLink", "PASS");
		
	}

	public void clickReferEarnLink() {
		
		reportStep("About to click on referEarnLink", "INFO");	

		scrollTillRequiredElementIsVisibleFromDownToUpFor10Times(referEarnLinkXpath);
		
		click(referEarnLink);

		reportStep("Successfully clicked on referEarnLink", "PASS");
		
	}

	public void clickMyReferralNetworkLink() {
		
		reportStep("About to click on myReferralNetworkLink", "INFO");	

		scrollTillRequiredElementIsVisibleFromDownToUpFor10Times(myReferralNetworkLinkXpath);
		
		click(myReferralNetworkLink);

		reportStep("Successfully clicked on myReferralNetworkLink", "PASS");
		
	}

	public void clickFaqLink() {
		
		reportStep("About to click on faqLink", "INFO");

		scrollTillRequiredElementIsVisibleFromDownToUpFor10Times(faqLinkXpath);
		
		click(faqLink);

		reportStep("Successfully clicked on faqLink", "PASS");
		
	}

	public void clickPaymentLink() {
		
		reportStep("About to click on paymentLink", "INFO");	

		scrollTillRequiredElementIsVisibleFromDownToUpFor10Times(paymentLinkXpath);
		
		click(paymentLink);

		reportStep("Successfully clicked on paymentLink", "PASS");
		
	}

}