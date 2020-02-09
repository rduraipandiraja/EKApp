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

public class ReferralNetworkPage extends WrapperMethods  {
	
	//Constructor call
	public ReferralNetworkPage(AppiumDriver<MobileElement> driver, ExtentTest testInfo) {

		WebDriverWait wait = new WebDriverWait(driver, 30);

		this.driver = driver;
		this.testInfo = testInfo;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);

		reportStep("Waiting for the Referral Network page", "INFO");
		try {
			wait.until(ExpectedConditions.visibilityOf(myReferralsHeader));
			reportStep("Successfully landed on the Referral network  page", "PASS");

		}catch(Exception e) {
			
			reportStep(e.getMessage(), "INFO");
			reportStep("Not able to land on the Referral network page", "FAIL");
		}

	}
	
	//Elements :	
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@content-desc='txt_myReferral_joinedDateValue']") 
	MobileElement dateJoined;
	
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@content-desc='txt_myReferral_referralNameValue']") 
	MobileElement referralName;
	
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@content-desc='txt_myReferral_statusValue']") 
	MobileElement status;

	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='Referral Network']") 
	MobileElement myReferralsHeader;

	@FindBy(how = How.XPATH, using = "//android.widget.ImageView[@content-desc='img_myReferral_noreferral']/parent::android.view.ViewGroup/android.widget.TextView[@text='There is no referral Profit']/parent::android.view.ViewGroup/android.widget.TextView[@text='You are now entitled to 10% Extra Profit everytime your friend shops via us! You can earn more if you refer us to more people in your network.']|//android.widget.ImageView[@content-desc='img_myReferral_noreferral']/parent::android.view.View/android.widget.TextView[@text='There is no referral Profit']/parent::android.view.View/android.widget.TextView[@text='You are now entitled to 10% Extra Profit everytime your friend shops via us! You can earn more if you refer us to more people in your network.']") 
	MobileElement myReferralsBodyText;

	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='My Referrals']") 
	MobileElement referFriends;

	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='My Referrals']") 
	MobileElement totalReferralProfitEarned;

	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='REFER & EARN NOW']") 
	MobileElement referAndEarnNow;

	@FindBy(how = How.XPATH, using = "(//android.widget.ImageView)[1]") 
	MobileElement backButtonClick;

	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='My Referral Network']") 
	MobileElement referralNetworkTitle;

	public ReferralNetworkPage validateDateJoined(String date) {
		
		reportStep("About to validate Date Value at Referral network page", "INFO");
		
		validateTheElementPresence(referralNetworkTitle);
		
		String dateXpath = "//android.widget.TextView[@text='Date Joined']/parent::android.view.ViewGroup/parent::android.view.ViewGroup/android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.widget.TextView[@text='"+date+"']|//android.widget.TextView[@text='Date Joined']/parent::android.view.View/parent::android.view.View/android.widget.ScrollView/android.view.View/android.view.View/android.view.ViewGroup/android.widget.TextView[@text='"+date+"']";

		if(isElementLocatedByXpathPresent(dateXpath)) {
			reportStep("Validated referralName in ReferralNetworkPage", "PASS");
			
		}else {
			reportStep("Not able to validate referralName in ReferralNetworkPage", "FAIL");
			
		}
		
		MobileElement dateValue = driver.findElement(By.xpath(dateXpath));
		
		validateTheElementPresence(dateValue);
		
		return this;
	}
	
	public ReferralNetworkPage validateReferralName(String referralName) {
		
		reportStep("About to validate referralName at Referral network", "INFO");

		String referralNameXpath = "//android.widget.TextView[@text='Referral Name']/parent::android.view.ViewGroup/parent::android.view.ViewGroup/android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.widget.TextView[@text='"+referralName+"']|//android.widget.TextView[@text='Referral Name']/parent::android.view.View/parent::android.view.View/android.widget.ScrollView/android.view.View/android.view.View/android.view.ViewGroup/android.widget.TextView[@text='"+referralName+"']";

		if(isElementLocatedByXpathPresent(referralNameXpath)) {
			reportStep("Validated referralName in ReferralNetworkPage", "PASS");
			
		}else {
			reportStep("Not able to validate referralName in ReferralNetworkPage", "FAIL");
			
		}
		
		MobileElement referralNameValue = driver.findElement(By.xpath(referralNameXpath));
		
		validateTheElementPresence(referralNameValue);
		
		return this;
	}
	
	public ReferralNetworkPage validateStatus(String status) {
				
		reportStep("About to validate status at Referral network", "INFO");

		String statusXpath = "//android.widget.TextView[@text='Status']/parent::android.view.ViewGroup/parent::android.view.ViewGroup/android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.widget.TextView[@text='"+status+"']|//android.widget.TextView[@text='Status']/parent::android.view.View/parent::android.view.View/android.widget.ScrollView/android.view.View/android.view.View/android.view.ViewGroup/android.widget.TextView[@text='"+status+"']";

		if(isElementLocatedByXpathPresent(statusXpath)) {
			reportStep("Validated status in ReferralNetworkPage", "PASS");
			
		}else {
			reportStep("Not able to validate status in ReferralNetworkPage", "FAIL");
			
		}
		
		MobileElement statusValue = driver.findElement(By.xpath(statusXpath));
		
		validateTheElementPresence(statusValue);
	
		return this;
	}

	public ReferralNetworkPage validateTotalReferralProfitEarnedValue(String totalReferralProfitEarnedValue) {
				
		reportStep("About to validate totalReferralProfitEarned in my referrals page", "INFO");

		String totalReferralProfitEarnedXpath = "//android.widget.TextView[@text='Total Referral Profit Earned']/parent::android.view.ViewGroup/android.widget.TextView[contains(@text,'"+totalReferralProfitEarnedValue+"')]|//android.widget.TextView[@text='Total Referral Profit Earned']/parent::android.view.View/android.widget.TextView[contains(@text,'"+totalReferralProfitEarnedValue+"')]";

		if(isElementLocatedByXpathPresent(totalReferralProfitEarnedXpath)) {
			reportStep("Validated totalReferralProfitEarnedValue in ReferralNetworkPage", "PASS");
			
		}else {
			reportStep("Not able to validate totalReferralProfitEarnedValue in ReferralNetworkPage", "FAIL");
			
		}
		
		MobileElement totalReferralProfitEarned = driver.findElement(By.xpath(totalReferralProfitEarnedXpath));
		
		validateTheElementPresence(totalReferralProfitEarned);
		
		return this;
	}

	public ReferralNetworkPage validateFriendsJoinedValue(String friendsJoinedValue) {
		
		reportStep("About to validate friendsJoined in my referrals page", "INFO");

		String friendsJoinedXpath = "//android.widget.TextView[@text='Friends Joined']/parent::android.view.ViewGroup/android.widget.TextView[contains(@text,'"+friendsJoinedValue+"')]|//android.widget.TextView[@text='Friends Joined']/parent::android.view.View/android.widget.TextView[contains(@text,'"+friendsJoinedValue+"')]";

		if(isElementLocatedByXpathPresent(friendsJoinedXpath)) {
			reportStep("Validated friendsJoinedValue in ReferralNetworkPage", "PASS");
			
		}else {
			reportStep("Not able to validate friendsJoinedValue in ReferralNetworkPage", "FAIL");
			
		}
		
		MobileElement friendsJoined = driver.findElement(By.xpath(friendsJoinedXpath));
		
		validateTheElementPresence(friendsJoined);
		
		return this;
	}

	public ReferralNetworkPage validateObjectsMyReferrals() {
		
		reportStep("About to validate objects in ReferralNetworkPage", "INFO");

		validateTheElementPresence(myReferralsHeader);
		validateTheElementPresence(myReferralsBodyText);
		
		return this;
	}

	public ReferAndEarnLifeTime clickReferNow() {

		reportStep("About to click on refer now", "INFO");

		if(click(referAndEarnNow)){

			reportStep("Successfully clicked on the referAndEarnNow in ReferralNetworkPage", "PASS");

		}else {

			reportStep("Not able to click referAndEarnNow in ReferralNetworkPage", "FAIL");
		}

		return new ReferAndEarnLifeTime(driver, testInfo);

	}

	public MyReferral clickBackButton() {

		reportStep("About to click on back button in ReferralNetworkPage", "INFO");

		backButton();

		return new MyReferral(driver, testInfo);
		
	}

}
