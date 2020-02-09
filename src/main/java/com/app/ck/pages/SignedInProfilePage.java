package com.app.ck.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.app.ck.base.Base;
import com.app.ck.base.WrapperMethods;
import com.aventstack.extentreports.ExtentTest;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class SignedInProfilePage extends WrapperMethods {

	//Constructor call
	public SignedInProfilePage(AppiumDriver<MobileElement> driver, ExtentTest testInfo) {

		WebDriverWait wait = new WebDriverWait(driver, 30);

		this.driver = driver;
		this.testInfo = testInfo;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);

		reportStep("Waiting for the SignedIn ProfilePage", "INFO");
		try {
			wait.until(ExpectedConditions.visibilityOf(myEarnings));
			reportStep("Successfully landed on the  SignedIn ProfilePage page", "PASS");

		}catch(Exception e) {

			reportStep(e.getMessage(), "INFO");
			reportStep("Not able to land on the SignedIn ProfilePage page", "FAIL");
		}

	}

	//Variable declaration
	
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='Profile']") 
	MobileElement profile;

	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='Account Settings']") 
	MobileElement accountSettings;
	
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='Logout']") 
	MobileElement logout;
	
	@FindBy(how = How.XPATH, using = "//android.widget.Button[@text='OK']") 
	MobileElement logOutOK;
	
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='My Referral']") 
	MobileElement myReferral;
	
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='My Earnings']")
	MobileElement myEarnings ;
	
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='Missing Profit']")
	MobileElement missingProfit ;
	
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='Payments']")
	MobileElement payment ;

	@FindBy(how = How.XPATH, using = "//android.widget.TextView[contains(@text,'Payment threshold not reached')]") 
	MobileElement paymentThresholdNotReached ;
	
	@FindBy(how = How.XPATH, using = "//android.widget.Button[@text='OK']") 
	MobileElement paymentThresholdNotReachedAlertPopupOKButton ;
	
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='Alert']") 
	MobileElement paymentThresholdNotReachedAlertText ;

	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@content-desc='icon_profileMenu_withLogin_goBackHome']") 
	MobileElement backButtonClick;

	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='Search']") 
	MobileElement searchIcon;
	
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text  = 'Account Settings']")
	MobileElement AccountSettings ;
	
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='Rate Us']")
	MobileElement rateUs ;
	
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='How EarnKaro Works?']")
	MobileElement howEKWorks ;
	
	String howEKWorksXpath = "//android.widget.TextView[@text='How EarnKaro Works?']";
	
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='Call Us']")
	MobileElement callUs ;
	
	String callUsXpath = "//android.widget.TextView[@text='Call Us']";

	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='FAQ']")
	MobileElement faq ;
	
	String faqXpath = "//android.widget.TextView[@text='FAQ']";

	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='History']")
	MobileElement history;
	
	String historyXpath = "//android.widget.TextView[@text='History']";

	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='Success Stories']")
	MobileElement successStoriesPage;
	
	String successStoriesXpath = "//android.widget.TextView[@text='Success Stories']";

	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='Help']") 
	MobileElement helpLink;

	@FindBy(how = How.XPATH, using = "//android.widget.TextView[contains(@text ,'Make Profit Link')]") 
	MobileElement makeProfitLinks;

	String helpXpath = "//android.widget.TextView[@text='Help']";

	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@content-desc='txt_profileMenu_withLogin_Language']") 
	MobileElement chooseLanguage;

	String chooseLanguageXpath = "//android.widget.TextView[@content-desc='txt_profileMenu_withLogin_Language']";
	
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@content-desc=\"txt_profileMenu_withLogin_totalCashbackAmount\"]") 
	MobileElement profitEarnedValue;
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='Total Profit Earned:']") 
	MobileElement profitEarnedText;
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='See Partners & Profit Rates']") 
	MobileElement seePartnersAndProfitRates;
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='Privacy Policy']") 
	MobileElement privacyPolicy;
	
	
	

	//Method Reusability
	
	public EKOnboardingPage clickOnLogout() {

		reportStep("About to logout the user ", "INFO");
		
		scrollFromDownToUpinApp();
				
		if(click_WaitFor25Sec(logout)) {

			reportStep("Sucessfully clicked on the Logout button ", "PASS");

		}else {

			reportStep("Not able to click on the Logout button ", "FAIL");
		}

		if(click(logOutOK)) {

			reportStep("Sucessfully clicked on the Logout OK button ", "PASS");

		}else {

			reportStep("Not able to click on the Logout OK button ", "FAIL");
		}
		return new EKOnboardingPage(driver, testInfo);
		
	}

	public SignInPage clickOnLogout_RedirectTo_SignInPage() {

		reportStep("About to logout the user ", "INFO");
		
		scrollFromDownToUpinApp();
				
		if(click(logout)) {

			reportStep("Sucessfully clicked on the Logout button ", "PASS");

		}else {

			reportStep("Not able to click on the Logout button ", "FAIL");
		}

		if(click(logOutOK)) {

			reportStep("Sucessfully clicked on the Logout OK button ", "PASS");

		}else {

			reportStep("Not able to click on the Logout OK button ", "FAIL");
		}
		return new SignInPage(driver, testInfo);
		
	}

	public MyReferral clickOnMyReferral() {

		reportStep("About to click on the my Referral on the Profile Page", "INFO");
		
		if(click(myReferral)) {
			reportStep("Successfully clicked on the my Referral", "PASS");
		}else {
			reportStep("Failed to click on the my Referral ", "FAIL");
		}

		return new MyReferral(driver, testInfo);

	}

	public MyEarningsPage clickOnMyEarnings() {

		reportStep("About to click on the MyEarnings link from the Profile Page ", "INFO");

		if(click(myEarnings)) {

			reportStep("Successfully clicked on the MyEarnings from the profile slider ", "PASS");

		}else {

			reportStep("Fail to click on the MyEarnings icon from the Profile slider ", "FAIL");
		}

		return new MyEarningsPage(driver, testInfo);
	}
	
	public PaymentPage clickOnPayments() {

		reportStep("About to click on the Payment link from the Profile Page ", "INFO");

		if(click(payment)) {

			reportStep("Successfully clicked on the Payment from the profile slider ", "PASS");

		}else {

			reportStep("Fail to click on the MyEarnings icon from the Profile slider ", "FAIL");
		}

		return new PaymentPage(driver, testInfo);
	}

	public MyEarningsPage clickOnPaymentsForThresholdNotReached() {

		reportStep("About to click on the Payment link from the Profile Page for threshold not reached ", "INFO");

		if(click(payment)) {

			reportStep("Successfully clicked on the Payment from the profile slider ", "PASS");

		}else {

			reportStep("Fail to click on the MyEarnings icon from the Profile slider ", "FAIL");
		}

		reportStep("About to validate the Threshold not reached popup ", "INFO");

		validateTheElementPresence(paymentThresholdNotReached);
		validateTheElementPresence(paymentThresholdNotReachedAlertPopupOKButton);
		validateTheElementPresence(paymentThresholdNotReachedAlertText);

		if(click(paymentThresholdNotReachedAlertPopupOKButton)) {

			reportStep("Successfully clicked on the Ok button at the alert popup", "INFO");
			
		}else {
			
			reportStep("Failed to click on the Ok button at the alert popup", "FAIL");
		}

		return new MyEarningsPage(driver, testInfo);

		
	}

	public HomePage clickBackButton() {

		reportStep("About to click on back button in SignedInProfilePage", "INFO");

//		if(click(backButtonClick)) {
//
//			reportStep("Successfully clicked on the back button SignedInProfilePage", "PASS");
//		}else {
//
//			reportStep("Failed to  click on the back button in SignedInProfilePage", "FAIL");
//		}
		
		driver.navigate().back();

		return new HomePage(driver, testInfo);

	}

	public SearchPage clickOnSearchIcon() {
		
		reportStep("About to click on the Search Icon from the Profile page ", "INFO");

		if(click(searchIcon)){

			reportStep("Sucessfully clicked on the Search icon ", "PASS");

		}else {

			reportStep("Failed to click on the Search icon", "FAIL");
		}

		return new SearchPage(driver, testInfo);
	}

	public AccountSettingsPage clickOnAccountSettings() {
		
		reportStep("About to click on the Account settings from the Profile menu  ", "INFO");
		
		if (click(accountSettings)) {
			
			reportStep("Successfully clicked on the Account Setting Profile page menu Option ", "PASS");
		}else {
			
			reportStep("Failed to click on the Profile Page Account settings Profile Menu Option ", "FAIL");
		}
		
		return new AccountSettingsPage(driver, testInfo);
	}

	public SignedInProfilePage validateTheUserNameChangesInProfilePage(String userFullName) {
		
		reportStep("About to valdiate the User full name in Signed In Profile page, once after changing the User Full Name  ", "INFO");
		
		String xpath =  "//android.widget.TextView[@text='Hello, "+userFullName+"']";
		
		if(isElementLocatedByXpathPresent(xpath)) {
			
			reportStep("Successfully validated the User Full name in Profile page,  once after changing the user full name in Account settings", "PASS");
		}else {
			
			reportStep("Failed to validate  the User Full name in Profile page,  once after changing the user full name in Account settings", "FAIL");
		}
		
		return this;
		
	}
	
	public TestimonialsPage clickonRateUs() {
		
		scrollFromDownToUpinApp();
		
		reportStep("About to click on Rate us link from the Profile menu ", "INFO");
		
		if(click(rateUs)) {
			
			reportStep("Successfully clicked on the Rate us link from the profile menu ", "PASS");
			
		}else {
			
			reportStep("Failed - Not able to click on the Rate Us Profile menu ", "FAIL");
		}
		
		return new TestimonialsPage(driver, testInfo);
	}
	
	public HistoryPage clickonHistory() {
		
		reportStep("About to click on history link from the Profile menu ", "INFO");
		
		scrollTillRequiredElementIsVisibleFromDownToUp(historyXpath);
		
		if(click(history)) {
			
			reportStep("Successfully clicked on the SharedActivities link from the profile menu ", "PASS");
			
		}else {
			
			reportStep("Failed - Not able to click SharedActivities from the Profile menu", "FAIL");
		}
		
		return new HistoryPage(driver, testInfo);
		
		
	}

	public SuccessStories clickonSuccessStories() {
		
		reportStep("About to click on SuccessStories link from the Profile menu ", "INFO");

		scrollTillRequiredElementIsVisibleFromDownToUp(successStoriesXpath);
		
		if(click(successStoriesPage)) {
			
			reportStep("Successfully clicked on the SuccessStories link from the profile menu ", "PASS");
			
		}else {
			
			reportStep("Failed - Not able to click SuccessStories from the Profile menu", "FAIL");
		}
		
		return new SuccessStories(driver, testInfo);
		
		
	}

	public HowItWorks clickOnHowEarnKaroWorks() {
		
		reportStep("About to click How EarnKaro Works link ", "INFO");

		scrollTillRequiredElementIsVisibleFromDownToUp(howEKWorksXpath);

		if(click(howEKWorks)) {
			
			reportStep("Successfully clicked How EarnKaro Works ", "PASS");
			
		}else {
			
			reportStep("Failed - Not able to click on the How EarnKaro Works ", "FAIL");
		}
		
		return new HowItWorks(driver, testInfo);
	}

	public HelpPage clickHelpLink() {

		reportStep("About to click HelpLink in profile page", "INFO");

		scrollTillRequiredElementIsVisibleFromDownToUp(helpXpath);

		if(click(helpLink)) {
			
			reportStep("Sucessfully clicked on the HelpLink in profile page", "PASS");
			
		}else {
			
			reportStep("Not able to click on the HelpLink in profile page", "FAIL");
		}

		return new HelpPage(driver, testInfo);
		
	}

	public MakeProfitLink clickOnMakeProfitLink() {
		
		reportStep("About to click on Make Profit Link on the Profile Menu ", "INFO");
		
		if(click(makeProfitLinks)) {
			
			reportStep("Successfully clicked on the Make Profit link ", "PASS");
			
		}else {
			
			reportStep("Failed to click on the Make Profit Link on the  Profile Menu ", "FAIL");
		}
		
		return new MakeProfitLink(driver, testInfo);
		
	}

	public ChooseLanguagePage clickOnChooseLanguage() {
		
		reportStep("About to click on ChooseLanguage on the Profile Menu ", "INFO");
		
		scrollFromDownToUpTillRequiredElementIsVisible(chooseLanguageXpath);
		
		if(click(chooseLanguage)) {
			
			reportStep("Successfully clicked on the ChooseLanguage link ", "PASS");
			
		}else {
			
			reportStep("Failed to click on the ChooseLanguage Link on the  Profile Menu ", "FAIL");
		}
		
		return new ChooseLanguagePage(driver, testInfo);
		
	}

	public void validateProfitEarnedValue(String expected) {
		
		reportStep("About to validate the Profit Earned Value ", "INFO");
		
		validateTheElementPresence(profitEarnedText);
		String strprofitEarnedValue = getText(profitEarnedValue);
		validateTheActualValueWithExpectedValue(strprofitEarnedValue, expected);
		
	}
	
	public PartnersPage clickSeePartnersAndProfitRates() {
		
		reportStep("About to click See Partners and Profit Rates", "Info");
		
		if(click(seePartnersAndProfitRates)) {
			
			reportStep("Successfully clicked on SeePartnersAndProfitRates", "INFO");
			
		}else {
			
			reportStep("Failed to click on the SeeProfit Rates and Profit Rates", "PASS");
		}
		return new PartnersPage(driver, testInfo);
		
	}
	
	public PrivacyPolicy clickPrivacyAndPolicy() {
		
		reportStep("About to click on Privacy and Policy ", "Info");
		
		scrollFromDownToUpinApp();
		
		if(click(privacyPolicy)) {
			
			reportStep("Successfully clicked on Privacy Policy ", "Pass");
			
		} else {
			
			reportStep("Failed to click on Privacy Policy", "Fail");
		}
		
		return new PrivacyPolicy(driver, testInfo);
	}

	public SignedInProfilePage validateNetworkErrorsInHistoryPage() {

		reportStep("About to TurnOff the Wifi", "Info");
		//Wifi - ON to OFF
		toggleWifi();

		new NetworkErrorPage(driver, testInfo).
		validateNetworkPageErrors();

		//Wifi - OFF to ON
		toggleWifi();
		new SignedInProfilePage(driver, testInfo);
		reportStep("Successfully Turned On the Wifi ", "Pass");
		return this;

	}

}
