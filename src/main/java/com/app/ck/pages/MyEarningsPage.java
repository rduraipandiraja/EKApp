package com.app.ck.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.app.ck.base.WrapperMethods;
import com.aventstack.extentreports.ExtentTest;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class MyEarningsPage extends WrapperMethods {

	//Constructor call
	public MyEarningsPage(AppiumDriver<MobileElement> driver, ExtentTest testInfo) {

		WebDriverWait wait = new WebDriverWait(driver, 30);

		this.driver = driver;
		this.testInfo = testInfo;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);

		reportStep("Waiting for the My Earnings page ", "INFO");
		try {
			wait.until(ExpectedConditions.visibilityOf(myEarningsPageName));
			reportStep("Successfully landed on the My Earnings page ", "PASS");

		}catch(Exception e) {

			reportStep(e.getMessage(), "INFO");
			reportStep("Not able to land on the My Earnings page ", "FAIL");
		}

	}

	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='My Earnings']")
	MobileElement myEarningsPageName ;
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='Total Profit Earned']")
	MobileElement totalProfitEarnedText ;
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='REQUEST PROFIT PAYMENT']")
	MobileElement requestProfitPaymentButton ;
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='REQUEST REWARDS PAYMENT']")
	MobileElement requestRewardsPaymentButton ;
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@content-desc='txt_myEarnings_ProfitEarningsAmount']")
	MobileElement ProfitEarningsAmount ;
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@content-desc='btn_myAccEarnings_totalProfitAmount']") 
	MobileElement buttonTotalProfitAmount ;
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@content-desc='txt_myAccEarnings_totalRewardsAmount']") 
	MobileElement buttonTotalRewardsAmount ;
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[contains(@text,'Payment threshold not reached')]") 
	MobileElement paymentThresholdNotReached ;
	@FindBy(how = How.XPATH, using = "//android.widget.Button[@text='OK']") 
	MobileElement paymentThresholdNotReachedAlertPopupOKButton ;
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='Alert']") 
	MobileElement paymentThresholdNotReachedAlertText ;
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='A payment request from you is already Pending. This will be processed shortly. Once this is paid, you can request for another payment.']") 
	MobileElement paymentReqAlreadyPendingMsg ;
	@FindBy(how = How.XPATH, using = "//android.widget.Button[@text='OK']") 
	MobileElement paymentPopupOKButton ;
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='Alert']") 
	MobileElement paymentPopupAlertText ;

	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='Profit']")
	MobileElement profitTab;
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='Rewards']")
	MobileElement rewardsTab;
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='Referral']")
	MobileElement referralTab;

	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@content-desc='txt_myEarnings_cashbackEarningsInfo']")
	MobileElement infoIcon;
	
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@content-desc='txt_myEarnings_rewardsEarningsInfo']")
	MobileElement infoIconRewardsTab;

	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@content-desc='txt_myEarnings_mc']")
	MobileElement raiseTicketProfit;

	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@content-desc='txt_myEarnings_mr']")
	MobileElement raiseTicketRewards;

	@FindBy(how = How.XPATH, using = "//android.widget.TextView[contains(@text,'earned any Profit Yet!')]")
	MobileElement youHaventtextProfitTab;
	
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='Shop today to start your Profit Journey.']")
	MobileElement shopTodayTextProfitTab;
	
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='You haven�t earned any Profit Yet!']")
	MobileElement youHaventtextRewardsTab;
	
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='Shop today to start your Rewards Journey.']")
	MobileElement shopTodayTextRewardsTab;
	
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='You Have No Friends On EarnKaro!']")
	MobileElement youHaveNoTextReferralTab;
	
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[contains(@text,'Profit is more fun with friends,')]")
	MobileElement ProfitIsMoreTextReferralTab;
	
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='invite some friends to your Profit party today.']")
	MobileElement inviteSomeFriendsTextReferralTab;

	@FindBy(how = How.XPATH, using = "(//android.widget.ImageView)[1]") 
	MobileElement backButtonClick;

	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='REFER NOW']") 
	MobileElement referNow;

	@FindBy(how = How.XPATH, using = "//*[contains(@text,'View more')]") 
	MobileElement viewMore;

	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='Profile']")
	MobileElement profileIcon;
	
	public MyEarningsPage validateProfitEarningsAmount(String expected){

		reportStep("About to validate the ProfitEarnings amount in the MyEarnings page ", "INFO");

		String actual = getText(ProfitEarningsAmount);

		if(actual.substring(actual.indexOf(".")+1).length()==2) {

			reportStep("Successfully validated the decimal point length : After the decimal point it  contains 2 digits  ", "INFO"); 

		}else {

			reportStep("After the decimal pointer , More than 2 digits are present - this is not the requirement  ", "FAIL"); 

		}
		validateTheActualValueContainsExpectedValue(actual, expected);

		return this;
	}

	public MyEarningsPage validateTotalProfitEarningsAmount(String expected){

		reportStep("About to validate the Total Profit Earnings amount in the MyEarnings page ", "INFO");

		String actual = getText(buttonTotalProfitAmount);

		if(actual.substring(actual.indexOf(".")+1).length()==2) {

			reportStep("Successfully validated the decimal point length : After the decimal point it  contains 2 digits  ", "INFO"); 

		}else {

			reportStep("After the decimal pointer , More than 2 digits are present - this is not the requirement  ", "FAIL"); 

		}

		validateTheActualValueContainsExpectedValue(actual, expected);

		return this;

	}

	public MyEarningsPage validateTotalRewardskEarningsAmount(String expected){

		reportStep("About to validate the Total Rewards earnings amount in the MyEarnings page ", "INFO");

		String actual = getText(buttonTotalRewardsAmount);

		if(actual.substring(actual.indexOf(".")+1).length()==2) {

			reportStep("Successfully validated the decimal point length : After the decimal point it  contains 2 digits  ", "INFO"); 

		}else {

			reportStep("After the decimal pointer , More than 2 digits are present - this is not the requirement  ", "FAIL"); 

		}

		validateTheActualValueContainsExpectedValue(actual, expected);

		return this;

	}

	public PaymentRequestPage clickOnRequestProfitPaymentButton() {

		clickProfitTab();
		
		reportStep("About to click on Request Profit payment button ", "INFO");
		
		if(click(requestProfitPaymentButton)){

			reportStep("Successfully clicked on the Request Profit payment Button at the My Earnings page ", "PASS");

		}else {

			reportStep("Not able to click on the Request Profit payment button  ", "FAIL");
		}

		return new PaymentRequestPage(driver, testInfo);

	}

	public PaymentPopupPage clickOnRequestProfitPaymentButtonForPaymentPopup() {

		clickProfitTab();

		reportStep("About to click on Request Profit payment button for intermediate  Payment popup ", "INFO");

		if(click(requestProfitPaymentButton)){

			reportStep("Successfully clicked on the Request Profit payment Button at the My Earnings page ", "PASS");

		}else {

			reportStep("Not able to click on the Request Profit payment button  ", "FAIL");
		}

		return new PaymentPopupPage(driver, testInfo);

	}

	public MyEarningsPage clickOnRequestProfitPaymentButtonForThresholdNotReached() {

		//clickProfitTab();

		reportStep("About to click on Request Profit payment button to validate the Threshold not reached  ", "INFO");

		if(click(requestProfitPaymentButton)){

			reportStep("Successfully clicked on the Request Profit payment Button at the My Earnings page ", "PASS");

		}else {

			reportStep("Not able to click on the Request Profit payment button  ", "FAIL");
		}

		return this;

	}

	public PaymentRequestPage clickOnRequestRewardsPaymentButton() {

		reportStep("About to click on Request Rewards payment button ", "INFO");

		if(click(requestRewardsPaymentButton)){

			reportStep("Successfully clicked on the Requesst RewardsPaymentPayment Button at the My Earnings page ", "PASS");

		}else {

			reportStep("Not able to click on the Request Rewards Payment button  ", "FAIL");
		}

		return new PaymentRequestPage(driver, testInfo);

	}

	public PaymentPopupPage clickOnRequestRewardsPaymentButtonForPaymentPopup() {

		reportStep("About to click on Request Rewards payment button for Payment Popup", "INFO");

		if(click(requestRewardsPaymentButton)){

			reportStep("Successfully clicked on the Requesst RewardsPaymentPayment Button at the My Earnings page ", "PASS");

		}else {

			reportStep("Not able to click on the Request Rewards Payment button  ", "FAIL");
		}

		return new PaymentPopupPage(driver, testInfo);

	}

	public MyEarningsPage clickOnRequestRewardsPaymentButtonForThresholdNotReached() {

		reportStep("About to click on Request Rewards payment button to validate the Threshold not reached  ", "INFO");

		if(click(requestRewardsPaymentButton)){

			reportStep("Successfully clicked on the Requesst RewardsPaymentPayment Button at the My Earnings page ", "PASS");

		}else {

			reportStep("Not able to click on the Request Rewards Payment button  ", "FAIL");
		}

		return this;
	}

	public MyEarningsPage validateThresholdNotReached() {

		reportStep("About to validate the Threshold not reached popup ", "INFO");

		validateTheElementPresence(paymentThresholdNotReached);
		validateTheElementPresence(paymentThresholdNotReachedAlertPopupOKButton);
		validateTheElementPresence(paymentThresholdNotReachedAlertText);

		if(click(paymentThresholdNotReachedAlertPopupOKButton)) {

			reportStep("Successfully clicked on the Ok button at the alert popup", "INFO");
		}else {
			reportStep("Failed to click on the Ok button at the alert popup", "FAIL");
		}

		return this;
	}

	public MyEarningsPage validatePaymentAlreadyPendingAlert() {


		reportStep("About to validate : A payment request from you is already Pending. This will be processed shortly. Once this is paid, you can request for another payment ", "INFO");

		validateTheElementPresence(paymentReqAlreadyPendingMsg);
		validateTheElementPresence(paymentPopupAlertText);

		if(click(paymentPopupOKButton)) {

			reportStep("Successfully clicked on the Ok button at the alert popup", "INFO");
		}else {
			reportStep("Failed to click on the Ok button at the alert popup", "FAIL");
		}

		return this;
	}

	public MyEarningsPage validateTotalProfitEarnedValue(String TotalProfitEarnedValue) {

		reportStep("About to verify the value Total Profit Earned "+TotalProfitEarnedValue+" in my earnings page ", "INFO");
		
		String totalProfitEarnedValueXpath = "//android.widget.TextView[@text='Total Profit Earned']/parent::android.view.ViewGroup/android.widget.TextView[contains(@text,"+TotalProfitEarnedValue+")]|//android.widget.TextView[@text='Total Profit Earned']/parent::android.view.View/android.widget.TextView[contains(@text,"+TotalProfitEarnedValue+")]";
		
		if(isElementLocatedByXpathPresent(totalProfitEarnedValueXpath)) {
			
			reportStep("Validated value Total Profit Earned "+TotalProfitEarnedValue+" in my earnings page ", "PASS");

		}else {
			reportStep("Not able to locate value Total Profit Earned "+TotalProfitEarnedValue+" in my earnings page ", "FAIL");

		}

		reportStep("Successfully verified the value Total Profit Earned "+TotalProfitEarnedValue+" in my earnings page ", "INFO");

		return this;

		
	}
	
	public MyEarningsPage validateTotalRewardsEarnedValue(String totalRewardsEarnedValue) {

		reportStep("About to verify the value total rewards earned "+totalRewardsEarnedValue+" in my earnings page ", "INFO");

		String totalRwEarnedValueXpath= "//android.widget.TextView[@text='Total Rewards Earned']/parent::android.view.ViewGroup/android.widget.TextView[contains(@text,"+totalRewardsEarnedValue+")]|//android.widget.TextView[@text='Total Rewards Earned']/parent::android.view.View/android.widget.TextView[contains(@text,"+totalRewardsEarnedValue+")]";

		if(isElementLocatedByXpathPresent(totalRwEarnedValueXpath)) {
			reportStep("Validated value total rewards earned "+totalRewardsEarnedValue+" in my earnings page ", "PASS");

		}else {
			reportStep("Not able to verify the value total rewards earned "+totalRewardsEarnedValue+" in my earnings page ", "FAIL");

		}
		
		return this;

		
	}

	public MyEarningsPage clickTotalProfitEarnedValue(String totalProfitEarnedValue) {

		reportStep("About to click the value Total Profit Earned "+totalProfitEarnedValue+" in my earnings page ", "INFO");
		
		String totalCbEarnedValueXpath = "//android.widget.TextView[@text='Total Profit Earned']/parent::android.view.ViewGroup/android.widget.TextView[contains(@text,"+totalProfitEarnedValue+")]|//android.widget.TextView[@text='Total Profit Earned']/parent::android.view.View/android.widget.TextView[contains(@text,"+totalProfitEarnedValue+")]";

		if(clickByXpath(totalCbEarnedValueXpath)){
			
			reportStep("Validated value Total Profit Earned "+totalProfitEarnedValue+" in my earnings page and Clicked on It ", "PASS");

		}else {
			reportStep("Not able to verify value Total Profit Earned "+totalProfitEarnedValue+" in my earnings page & clicked on It", "FAIL");

		}
		
		return new MyEarningsPage(driver, testInfo);

	}
	
	
	
	public MyEarningsPage clickTotalRewardsEarnedValue(String totalRewardsEarnedValue) {

		reportStep("About to click the value total rewards earned "+totalRewardsEarnedValue+" in my earnings page ", "INFO");

		String totalRwEarnedValueXpath = "//android.widget.TextView[@text='Total Rewards Earned']/parent::android.view.ViewGroup/android.widget.TextView[contains(@text,"+totalRewardsEarnedValue+")]|//android.widget.TextView[@text='Total Rewards Earned']/parent::android.view.View/android.widget.TextView[contains(@text,"+totalRewardsEarnedValue+")]";
		
		if(clickByXpath(totalRwEarnedValueXpath)){
			
			reportStep("Clicked the value total rewards earned "+totalRewardsEarnedValue+" in my earnings page ", "PASS");

		}else {
			reportStep("Not able to click the value total rewards earned "+totalRewardsEarnedValue+" in my earnings page ", "FAIL");

		}
		return new MyEarningsPage(driver, testInfo);

		
	}
	
	public TotalProfitEarnedPage clickTotalProfitEarnedValueHavingValueMoreThanZero(String totalProfitEarnedValue) {

		reportStep("About to click the value Total Profit Earned "+totalProfitEarnedValue+" in my earnings page ", "INFO");

		String totalCbEarnedValueXpath = "//android.widget.TextView[@text='Total Profit Earned']/parent::android.view.ViewGroup/android.widget.TextView[contains(@text,"+totalProfitEarnedValue+")]|//android.widget.TextView[@text='Total Profit Earned']/parent::android.view.View/android.widget.TextView[contains(@text,"+totalProfitEarnedValue+")]";
	
		if(clickByXpath(totalCbEarnedValueXpath)){
			
			reportStep("Clicked the value Total Profit Earned "+totalProfitEarnedValue+" in my earnings page ", "PASS");

		}else {
			reportStep("Not able to click the value Total Profit Earned "+totalProfitEarnedValue+" in my earnings page ", "FAIL");

		}
		
		return new TotalProfitEarnedPage(driver, testInfo);
	}
	
	public TotalRewardsEarnedPage clickTotalRewardsEarnedValueHavingValueMoreThanZero(String totalRewardsEarnedValue) {

		reportStep("About to click the value total rewards earned "+totalRewardsEarnedValue+" in my earnings page ", "INFO");
		
		String totalRwEarnedValueXpath = "//android.widget.TextView[@text='Total Rewards Earned']/parent::android.view.ViewGroup/android.widget.TextView[contains(@text,"+totalRewardsEarnedValue+")]|//android.widget.TextView[@text='Total Rewards Earned']/parent::android.view.View/android.widget.TextView[contains(@text,"+totalRewardsEarnedValue+")]";

		if(clickByXpath(totalRwEarnedValueXpath)) {
			reportStep("Clicked the value total rewards earned "+totalRewardsEarnedValue+" in my earnings page ", "PASS");

		}else {
			reportStep("Not able to click the value total rewards earned "+totalRewardsEarnedValue+" in my earnings page ", "FAIL");

		}
		return new TotalRewardsEarnedPage(driver, testInfo);

		
	}

	public MyEarningsPage clickProfitTab() {

		reportStep("About to click Profit tab", "INFO");
		
		String profitTabXpath = "//android.widget.TextView[@text='Profit']";
		
		//Dont comment the below line - It is required for some scenarios
		scrollTillRequiredElementIsVisibleFromUpToDown(profitTabXpath);	
				
		if(clickAfterWait(profitTab)) {
			
			reportStep("Sucessfully clicked on profitTab", "PASS");
			
		}else {
			
			reportStep("Not able to click on profitTab", "FAIL");
		}
		
		return this;
		
	}
	
	
	public MyEarningsPage clickReferralTabClick() {

		reportStep("About to click referral tab", "INFO");
		
		String ReferralTabXpath = "//android.widget.TextView[@text='Referral']";

		scrollTillRequiredElementIsVisibleFromUpToDown(ReferralTabXpath);	
				
		if(clickAfterWait(referralTab)) {
			
			reportStep("Sucessfully clicked on referral tab", "PASS");
			
		}else {
			
			reportStep("Not able to click on referral tab", "FAIL");
		}
		
		return this;
		
	}
	
	public EarningsInfoPage clickInfoIcon() {

		reportStep("About to click infoIcon", "INFO");
		
		scrollFromDownToUpinApp();
				
		if(click(infoIcon)) {
			
			reportStep("Sucessfully clicked on infoIcon", "PASS");
			
		}else {
			
			reportStep("Not able to click on infoIcon", "FAIL");
		}
		
		return new EarningsInfoPage(driver, testInfo);
		
	}

	public EarningsInfoPage clickInfoIconRewardsTab() {

		reportStep("About to click infoIcon", "INFO");
		
		scrollFromDownToUpinApp();
				
		if(click(infoIconRewardsTab)) {
			
			reportStep("Sucessfully clicked on infoIcon", "PASS");
			
		}else {
			
			reportStep("Not able to click on infoIcon", "FAIL");
		}
		
		return new EarningsInfoPage(driver, testInfo);
		
	}

	public MyEarningsPage validateRaiseTicketProfitTab() {

		reportStep("About to click raiseTicket", "INFO");
		
		String raiseTicketText = getText(raiseTicketProfit);
		
		validateTheActualValueContainsExpectedValue(raiseTicketText.trim(), getTestData(7, "raiseTicketProfit").trim());
		validateTheActualValueContainsExpectedValue(raiseTicketText.trim(), getTestData(7, "raiseTicketProfit2").trim());
		
		return this;
		
	}

	public MyEarningsPage validateRaiseTicketRewardsTab() {

		reportStep("About to click raiseTicket", "INFO");
		
		String raiseTicketText = getText(raiseTicketRewards);
		
		validateTheActualValueContainsExpectedValue(raiseTicketText.trim(), getTestData(7, "raiseTicketRewards").trim());
		validateTheActualValueContainsExpectedValue(raiseTicketText.trim(), getTestData(7, "raiseTicketRewards2").trim());
		
		return this;
		
	}

	public MyEarningsPage validateProfitEarningsValueProfitTab(String ProfitEarning) {

		reportStep("About to verify the value ProfitEarning "+ProfitEarning+" in Profit tab ", "INFO");

		MobileElement totalCbEarnedValue = driver.findElement(By.xpath("//android.widget.TextView[@text='Profit Earnings Available for Payment']/parent::android.view.ViewGroup/android.widget.TextView[contains(@text,"+ProfitEarning+")]|//android.widget.TextView[@text='Profit Earnings Available for Payment']/parent::android.view.View/android.widget.TextView[contains(@text,"+ProfitEarning+")]"));

		validateTheElementPresence(totalCbEarnedValue);

		reportStep("Successfully verified the ProfitEarning value "+ProfitEarning+" in Profit tab ", "INFO");

		return this;

		
	}
	
	public MyEarningsPage validateRewardEarningsValueRewardsTab(String rewardEarning) {

		reportStep("About to verify the value rewardEarning "+rewardEarning+" in rewards tab ", "INFO");

		MobileElement totalRwEarnedValue = driver.findElement(By.xpath("//android.widget.TextView[@text='Reward Earnings']/parent::android.view.ViewGroup/android.widget.TextView[contains(@text,"+rewardEarning+")]|//android.widget.TextView[@text='Reward Earnings']/parent::android.view.View/android.widget.TextView[contains(@text,"+rewardEarning+")]"));

		validateTheElementPresence(totalRwEarnedValue);

		reportStep("Successfully verified the value total rewards earned "+rewardEarning+" in rewards tab ", "INFO");

		return this;

		
	}

	public MyEarningsPage validateReferralEarningsValueReferralTab(String referralEarning) {

		reportStep("About to verify the value referralEarning "+referralEarning+" in referral tab ", "INFO");
				
		MobileElement totalRwEarnedValue = driver.findElement(By.xpath("//android.widget.TextView[@text='Referral Earnings']/parent::android.view.ViewGroup/android.widget.TextView[contains(@text,"+referralEarning+")]|//android.widget.TextView[@text='Referral Earnings']/parent::android.view.View/android.widget.TextView[contains(@text,"+referralEarning+")]"));

		validateTheElementPresence(totalRwEarnedValue);

		reportStep("Successfully verified the value total rewards earned "+referralEarning+" in referral tab ", "INFO");

		return this;

		
	}

	public MyEarningsPage validateBottomTextInAllTab() {

		reportStep("About to bottom text in Profit tab, rewards tab & referral tab", "INFO");
		
		clickProfitTab();
		validateTheElementPresence(youHaventtextProfitTab);
		validateTheElementPresence(shopTodayTextProfitTab);
		
		clickReferralTabClick();		
		scrollFromDownToUpinApp();
		scrollFromDownToUpinApp();		
		validateTheElementPresence(youHaveNoTextReferralTab);
		validateTheElementPresence(ProfitIsMoreTextReferralTab);
		validateTheElementPresence(inviteSomeFriendsTextReferralTab);

		reportStep("Validated the bottom text in Profit tab, rewards tab & referral tab", "PASS");

		return this;

		
	}

	public SignedInProfilePage clickBackButton() {

		reportStep("About to click on back button in MyEarningsPage", "INFO");
		
		driver.navigate().back();

		return new SignedInProfilePage(driver, testInfo);
		
	}

	public ReferAndEarnLifeTime clickReferNow() {

		reportStep("About to click on refer now", "INFO");

		if(click(referNow)){

			reportStep("Successfully clicked on the refer now", "PASS");

		}else {

			reportStep("Not able to click refer now", "FAIL");
		}

		return new ReferAndEarnLifeTime(driver, testInfo);

	}
	
	public MyEarningsPage clickMonthYearDropdown(String date) {

		reportStep("About to click the month year dropdown "+date+" in respective tab ", "INFO");
		
		String dateValueXpath = "//android.widget.TextView[@text='Order Details']/parent::android.view.ViewGroup/android.view.ViewGroup/android.widget.EditText[@text='"+date+"']|//android.widget.TextView[@text='Order Details']/parent::android.view.View/android.view.view//android.widget.EditText[@text='"+date+"']";

		if(clickByXpath(dateValueXpath)) {
			reportStep("Clicked the month year dropdown "+date+" in respective tab ", "PASS");

		}else {
			reportStep("Not able to click the month year dropdown "+date+" in respective tab ", "FAIL");

		}
		
		this.clickDateAfterClickingMonthYearDropdown(date);

		return this;

		
	}

	public MyEarningsPage clickDateAfterClickingMonthYearDropdown(String monthYear) {

		reportStep("About to click on date in respective tab", "INFO");
		
		String dropDownValueXpath = "//android.widget.CheckedTextView[@text='"+monthYear+"']";
		
		if(clickByXpath(dropDownValueXpath)) {
			reportStep("Click on date in respective tab", "PASS");

		}else {
			reportStep("Not able to click on date in respective tab", "FAIL");

		}
		
		return this;
	}

	public MyEarningsPage validateDate(String date) {

		reportStep("About to verify the date "+date+" in respective tab ", "INFO");
		
		String dateValueXpath = "//android.widget.TextView[@text='Date']/parent::android.view.ViewGroup/android.widget.TextView[@text='"+date+"']|//android.widget.TextView[@text='Date']/parent::android.view.View/android.widget.TextView[@text='"+date+"']";
		
		//scrollTillRequiredElementIsVisible(dateValueXpath);
		
		if(isElementLocatedByXpathPresent(dateValueXpath)) {
			
			reportStep("Verify the date "+date+" in respective tab ", "PASS");

		}else {
			reportStep("Not able to verify the date "+date+" in respective tab ", "FAIL");

		}

		return this;

		
	}
	
	public MyEarningsPage validateRetailer(String retailer) {

		reportStep("About to verify the Retailer "+retailer+" in respective tab ", "INFO");
		
		String retailerValueXpath = "//android.widget.TextView[@text='Retailer']/parent::android.view.ViewGroup/android.widget.TextView[@text='"+retailer+"']|//android.widget.TextView[@text='Retailer']/parent::android.view.View/android.widget.TextView[@text='"+retailer+"']";
		
		if(isElementLocatedByXpathPresent(retailerValueXpath)) {
			
			reportStep("Verified the Retailer "+retailer+" in respective tab ", "PASS");

		}else {
			reportStep("Not able to verify the Retailer "+retailer+" in respective tab ", "FAIL");

		}

		return this;
		
	}
	
	public MyEarningsPage validateOrderAmount(String orderAmount) {

		reportStep("About to verify the orderAmount "+orderAmount+" in respective tab ", "INFO");
		
		String orderAmountValueXpath = "//android.widget.TextView[@text='Order Amount']/parent::android.view.ViewGroup/android.widget.TextView[contains(@text,'"+orderAmount+"')]|//android.widget.TextView[@text='Order Amount']/parent::android.view.View/android.widget.TextView[contains(@text,'"+orderAmount+"')]";
		
		if(isElementLocatedByXpathPresent(orderAmountValueXpath)) {
			
			reportStep("Verified the orderAmount "+orderAmount+" in respective tab ", "PASS");

		}else {
			reportStep("Not able to verify the orderAmount "+orderAmount+" in respective tab ", "FAIL");

		}
		
		return this;

	}
	
	public MyEarningsPage validateProfitAmount(String ProfitAmount) {

		reportStep("About to verify the ProfitAmount "+ProfitAmount+" in respective tab ", "INFO");

		String statusValueXpath = "//android.widget.TextView[@text='Profit Amount']/parent::android.view.ViewGroup/android.widget.TextView[contains(@text,'"+ProfitAmount+"')]|//android.widget.TextView[@text='Profit Amount']/parent::android.view.View/android.widget.TextView[contains(@text,'"+ProfitAmount+"')]";

		//scrollTillRequiredElementIsVisible(statusValueXpath);
		
		scrollFromDownToUpinApp();
		scrollFromDownToUpinApp();
		scrollFromDownToUpinApp();
		
		if(isElementLocatedByXpathPresent(statusValueXpath)) {
			reportStep("Verified the ProfitAmount "+ProfitAmount+" in respective tab ", "PASS");

		}else {
			reportStep("Not able to verify the ProfitAmount "+ProfitAmount+" in respective tab ", "FAIL");

		}

		return this;

		
	}

	public MyEarningsPage validateStatus(String status) {

		reportStep("About to verify the status "+status+" in respective tab ", "INFO");
		
		String statusValueXpath = "//android.widget.TextView[@text='Status']/parent::android.view.ViewGroup/android.widget.TextView[contains(@text,'"+status+"')]|//android.widget.TextView[@text='Status']/parent::android.view.View/android.widget.TextView[contains(@text,'"+status+"')]";

		//scrollTillRequiredElementIsVisible(statusValueXpath);
		
		scrollFromDownToUpinApp();
		scrollFromDownToUpinApp();
		scrollFromDownToUpinApp();
		
		if(isElementLocatedByXpathPresent(statusValueXpath)) {
			reportStep("Verify the status "+status+" in respective tab ", "PASS");

		}else {
			reportStep("Not to verify the status "+status+" in respective tab ", "FAIL");
		
		}
		return this;
		
	}

	public MyEarningsPage validateExpected(String expected) {

		reportStep("About to verify the expected "+expected+" in respective tab ", "INFO");

		String expectedValueXpath = "//android.widget.TextView[@text='Expected']/parent::android.view.ViewGroup/android.widget.TextView[@text='"+expected+"']|//android.widget.TextView[@text='Expected']/parent::android.view.View/android.widget.TextView[@text='"+expected+"']";

		//scrollTillRequiredElementIsVisible(expectedValueXpath);
			
		scrollFromDownToUpinApp();
		scrollFromDownToUpinApp();
		scrollFromDownToUpinApp();
		
		if(isElementLocatedByXpathPresent(expectedValueXpath)) {
			
			reportStep("About to verify the expected "+expected+" in respective tab ", "PASS");

		}else {
			reportStep("Not able to verify the expected "+expected+" in respective tab ", "FAIL");

		}
		return this;
		
	}
	
	public MyEarningsPage validateAbsenceExpected(String expected) {

		reportStep("About to verify the absence of expected "+expected+" in respective tab ", "INFO");
		
		scrollFromDownToUpinApp();
		scrollFromDownToUpinApp();
		scrollFromDownToUpinApp();

		String expectedValue = "//android.widget.TextView[@text='Expected']/parent::android.view.ViewGroup/android.widget.TextView[@text='"+expected+"']|//android.widget.TextView[@text='Expected']/parent::android.view.View/android.widget.TextView[@text='"+expected+"']";

		validateTheElementAbsence(expectedValue);

		reportStep("Successfully verified the absence of expected "+expected+" in respective tab ", "INFO");

		return this;

		
	}

	public MyEarningsPage validateAbsenceOrderAmount() {

		reportStep("About to verify the absence orderAmount in respective tab ", "INFO");
		
		scrollFromDownToUpinApp();
		scrollFromDownToUpinApp();
		scrollFromDownToUpinApp();

		String orderAmount = "//android.widget.TextView[@text='Order Amount']";

		validateTheElementAbsence(orderAmount);

		reportStep("Successfully verified the absence orderAmount in respective tab ", "INFO");

		return this;

		
	}

	public MyEarningsPage validateDateReferralTab(String date) {

		reportStep("About to verify the date "+date+" in referral tab ", "INFO");
		
		scrollFromDownToUpinApp();
		scrollFromDownToUpinApp();
		
		String dateXpath= "//android.widget.TextView[@text='Date']/parent::android.view.ViewGroup/parent::android.view.ViewGroup/android.view.ViewGroup/android.widget.TextView[@text='"+date+"']|//android.widget.TextView[@text='Date']/parent::android.view.View/parent::android.view.View/android.view.View/android.widget.TextView[@text='"+date+"']";
	
		if(isElementLocatedByXpathPresent(dateXpath)) {

			reportStep("Verify the date "+date+" in referral tab ", "INFO");
		}else {

			reportStep("Not able to verify the date "+date+" in referral tab ", "FAIL");
		}
		
		return this;
		
	}
	
	public MyEarningsPage validateReferralNameReferralTab(String referralName) {

		reportStep("About to verify the referral name "+referralName+" in referral tab ", "INFO");
		
		scrollFromDownToUpinApp();
		scrollFromDownToUpinApp();

		String referalNameXpath = "//android.widget.TextView[@text='Referral Name']/parent::android.view.ViewGroup/parent::android.view.ViewGroup/android.view.ViewGroup/android.widget.TextView[@text='"+referralName+"']|//android.widget.TextView[@text='Referral Name']/parent::android.view.View/parent::android.view.View/android.view.View/android.widget.TextView[@text='"+referralName+"']";

		if(isElementLocatedByXpathPresent(referalNameXpath)) {
			reportStep("Verify the referral name "+referralName+" in referral tab ", "INFO");

		}else {
			reportStep("Not able to verify the referral name "+referralName+" in referral tab ", "FAIL");

		}

		return this;

		
	}
	
	public MyEarningsPage validateProfitReferralTab(String status) {

		reportStep("About to verify the cb "+status+" in referral tab ", "INFO");
		
		scrollFromDownToUpinApp();
		scrollFromDownToUpinApp();

		String statusValueXpath = "//android.widget.TextView[@text='Profit']/parent::android.view.ViewGroup/parent::android.view.ViewGroup/android.view.ViewGroup/android.widget.TextView[contains(@text,'"+status+"')]|//android.widget.TextView[@text='Profit']/parent::android.view.View/parent::android.view.View/android.view.View/android.widget.TextView[contains(@text,'"+status+"')]";
		
		if(isElementLocatedByXpathPresent(statusValueXpath)) {
			
			reportStep("Verify the cb "+status+" in referral tab ", "INFO");

		}else {
			reportStep("Not able to verify the cb "+status+" in referral tab ", "FAIL");

		}

		return this;

		
	}
	
	public MyEarningsPage validateStatusReferralTab(String status) {

		reportStep("About to verify the status "+status+" in referral tab ", "INFO");
		
		scrollFromDownToUpinApp();
		scrollFromDownToUpinApp();

		String statusValueXpath = "//android.widget.TextView[@text='Status']/parent::android.view.ViewGroup/parent::android.view.ViewGroup/android.view.ViewGroup/android.widget.TextView[contains(@text,'"+status+"')]|//android.widget.TextView[@text='Status']/parent::android.view.View/parent::android.view.View/android.view.View/android.widget.TextView[contains(@text,'"+status+"')]";
		
		if(isElementLocatedByXpathPresent(statusValueXpath)){
			reportStep("Verify the status "+status+" in referral tab ", "INFO");

		}else {
			reportStep("Not able to verify the status "+status+" in referral tab ", "FAIL");

		}

		return this;

		
	}

	public MyEarningsPage validateEntireBlockBelowRespectiveTab(String currentDate, String retailer, String orderAmount, String ProfitAmount, String status, String ninetyDaysFromCurrentDate) {

		reportStep("About to verify the currentDate "+currentDate+", retailer "+retailer+", orderAmount "+orderAmount+", ProfitAmount "+ProfitAmount+", status "+status+", ninetyDaysFromCurrentDate "+ninetyDaysFromCurrentDate+" in respective tab ", "INFO");
	
		String ProfitTabXpath = "//android.widget.TextView[@text='Profit']";
		
		scrollTillRequiredElementIsVisibleFromUpToDown(ProfitTabXpath);	
		
		String entireBlockXpath = "//android.widget.TextView[@text='Date']/parent::android.view.ViewGroup/android.widget.TextView[@text='"+currentDate+"']/parent::android.view.ViewGroup/android.widget.TextView[@text='Retailer']/parent::android.view.ViewGroup/android.widget.TextView[@text='"+retailer+"']/parent::android.view.ViewGroup/android.widget.TextView[@text='Order Amount']/parent::android.view.ViewGroup/android.widget.TextView[contains(@text,'"+orderAmount+"')]/parent::android.view.ViewGroup/android.widget.TextView[@text='Profit Amount']/parent::android.view.ViewGroup/android.widget.TextView[contains(@text,'"+ProfitAmount+"')]/parent::android.view.ViewGroup/android.widget.TextView[@text='Status']/parent::android.view.ViewGroup/android.widget.TextView[contains(@text,'"+status+"')]/parent::android.view.ViewGroup/android.widget.TextView[contains(@text,'Expected')]/parent::android.view.ViewGroup/android.widget.TextView[contains(@text,'"+ninetyDaysFromCurrentDate+"')]";

		scrollTillRequiredElementIsVisibleFromDownToUp(entireBlockXpath);

		MobileElement entireBlock = driver.findElement(By.xpath(entireBlockXpath));
		
		validateTheElementPresence(entireBlock);

		reportStep("Successfully verified the the currentDate "+currentDate+", retailer "+retailer+", orderAmount "+orderAmount+", ProfitAmount "+ProfitAmount+", status "+status+", ninetyDaysFromCurrentDate "+ninetyDaysFromCurrentDate+" in respective tab ", "INFO");

		return this;

		
	}

	public MyEarningsPage validateEntireBlockBelowRespectiveTabExceptExpected(String currentDate, String retailer, String orderAmount, String ProfitAmount, String status) {

		reportStep("About to verify the currentDate "+currentDate+", retailer "+retailer+", orderAmount "+orderAmount+", ProfitAmount "+ProfitAmount+", status "+status+" in respective tab ", "INFO");
		
		String ProfitTabXpath = "//android.widget.TextView[@text='Profit']";
		
		scrollTillRequiredElementIsVisibleFromUpToDown(ProfitTabXpath);	
		
		String entireBlockXpath = "//android.widget.TextView[@text='Date']/parent::android.view.ViewGroup/android.widget.TextView[@text='"+currentDate+"']/parent::android.view.ViewGroup/android.widget.TextView[@text='Retailer']/parent::android.view.ViewGroup/android.widget.TextView[@text='"+retailer+"']/parent::android.view.ViewGroup/android.widget.TextView[@text='Order Amount']/parent::android.view.ViewGroup/android.widget.TextView[contains(@text,'"+orderAmount+"')]/parent::android.view.ViewGroup/android.widget.TextView[@text='Profit Amount']/parent::android.view.ViewGroup/android.widget.TextView[contains(@text,'"+ProfitAmount+"')]/parent::android.view.ViewGroup/android.widget.TextView[@text='Status']/parent::android.view.ViewGroup/android.widget.TextView[contains(@text,'"+status+"')]";

		scrollTillRequiredElementIsVisibleFromDownToUp(entireBlockXpath);

		MobileElement entireBlock = driver.findElement(By.xpath(entireBlockXpath));
		
		validateTheElementPresence(entireBlock);

		reportStep("Successfully verified the the currentDate "+currentDate+", retailer "+retailer+", orderAmount "+orderAmount+", ProfitAmount "+ProfitAmount+", status "+status+" in respective tab ", "INFO");

		return this;

		
	}

	public MyEarningsPage validateEntireBlockBelowReferralTab(String currentDate, String referralName, String ProfitAmount, String status) {

		reportStep("About to verify the currentDate "+currentDate+", referralName "+referralName+", ProfitAmount "+ProfitAmount+", status "+status+" in referral tab ", "INFO");

		String referralTabXpath = "//android.widget.TextView[@text='Referral']";
		
		scrollTillRequiredElementIsVisibleFromUpToDown(referralTabXpath);	

		String entireBlockXpath = "//android.widget.TextView[@text='"+currentDate+"']/parent::android.view.ViewGroup/android.widget.TextView[@text='"+referralName+"']/parent::android.view.ViewGroup/android.widget.TextView[contains(@text,'"+ProfitAmount+"')]/parent::android.view.ViewGroup/android.widget.TextView[contains(@text,'"+status+"')]";
		
		scrollTillRequiredElementIsVisibleFromDownToUp(entireBlockXpath);

		MobileElement entireBlock = driver.findElement(By.xpath(entireBlockXpath));
		
		validateTheElementPresence(entireBlock);

		reportStep("Successfully verify the currentDate "+currentDate+", referralName "+referralName+", ProfitAmount "+ProfitAmount+", status "+status+" in referral tab ", "INFO");

		return this;

		
	}

	public MyEarningsPage clickViewMore() {

		reportStep("About to click on ViewMore", "INFO");
		
		String viewMore = "//android.widget.TextView[contains(@text,'View') and contains(@text,'more')]";
		
		scrollTillRequiredElementIsVisibleFromDownToUp(viewMore);
		
		if(clickByXpath(viewMore)) {
			
			reportStep("clicked on ViewMore", "PASS");

		}else {
			reportStep("Not able to click on ViewMore", "FAIL");

		}

		return this;

	}

	public SignedInProfilePage clickOnProfileIconForSignedUser() {
		
		clickBackButton();
		
		reportStep("About to click on the Profile icon for  Signed user ", "INFO");

		if(click(profileIcon)) {

			reportStep("Successfully clicked on the Profile icon at the Home page for  Signed user ", "PASS");
		}else {

			reportStep("Failed to  click on the Profile icon at the Home page for  Signed user", "FAIL");
		}

		return new SignedInProfilePage(driver, testInfo);
	}


	public MyEarningsPage validateNetoworkErrorsInMyEarningsPage() {

		reportStep("About to TurnOff the Wifi", "Info");
		//Wifi - ON to OFF
		toggleWifi();

		new NetworkErrorPage(driver, testInfo).
		validateNetworkPageErrors();

		//Wifi - OFF to ON
		toggleWifi();

		new MyEarningsPage(driver, testInfo);
		reportStep("About to Turn On the Wifi", "Info");

		return this;

	}

}