package com.app.ck.pages;


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

public class ReferAndEarnLifeTime extends WrapperMethods{
	
	public ReferAndEarnLifeTime(AppiumDriver<MobileElement> driver, ExtentTest testInfo) {

		WebDriverWait wait = new WebDriverWait(driver, 30);

		this.driver = driver;
		this.testInfo = testInfo;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);

		reportStep("Waiting for the ReferAndEarn Page", "INFO");

		try {
			wait.until(ExpectedConditions.visibilityOf(referAndEarnLifeTime));

			reportStep("Successfully landed on the ReferAndEarn page", "PASS");

		}catch(Exception e) {
			
			reportStep(e.getMessage(), "INFO");
			reportStep("Not able to land on the  ReferAndEarn page", "FAIL");
		}

	}
		
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='Refer & Earn Lifetime']")
	MobileElement referAndEarnLifeTime ;

	@FindBy(how = How.XPATH, using = "(//android.widget.ImageView)[1]") 
	MobileElement backButtonClick;

	@FindBy(how = How.XPATH, using = "//android.widget.ImageView[@content-desc='img_referAndEarn_referIMG']") 
	MobileElement referAndEarnImg;
	
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[contains(@text,'Invite Friends to EarnKaro')]") 
	MobileElement inviteFriendText;
	
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[contains(@text,'Your Referral Link')]") 
	MobileElement yourReferralText;
	
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[contains(@text,'Copy the link below and share with your friends!')]") 
	MobileElement copyTheLinkText;
	
	@FindBy(how = How.XPATH, using = "//*[contains(@text,'Tap to Copy')]") 
	MobileElement tapToCopyText;
	
	@FindBy(how = How.XPATH, using = "//*[contains(@text,'Facebook')]") 
	MobileElement facebookText;
	
	@FindBy(how = How.XPATH, using = "//*[contains(@text,'Twitter')]") 
	MobileElement twitterText;
	
	@FindBy(how = How.XPATH, using = "//*[contains(@text,'WhatsApp')]") 
	MobileElement whatsAppText;
	
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@content-desc=\"icon_referAndEarn_shareViaFacebook\"]") 
	MobileElement facebookShareIcon;
	
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@content-desc=\"icon_referAndEarn_shareViaTwitter\"]") 
	MobileElement twitterShareIcon;
	
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@content-desc=\"icon_referAndEarn_shareViaWhatsApp\"]") 
	MobileElement whatAppShareIcon;
	
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[contains(@text,'https://earnkaro.onelink.me/')]") 
	MobileElement referralLinkURL;
	
	@FindBy(how = How.XPATH, using = "//*[contains(@text,'Invite by Social Media')]") 
	MobileElement inviteBySocialMedia;
	
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[contains(@text,'Share your invite code & get 10% of the profit your friends make forever!')]") 
	MobileElement shareYourInviteCodeText;
	
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[contains(@text,'Help Friends Earn!')]") 
	MobileElement helpFriendsEarn;
	
	@FindBy(how = How.XPATH, using = "//android.widget.EditText[contains(@text,'twitter')]") 
	MobileElement twitterSharedText;

	
	//Methods:
	
	public MyEarningsPage clickBackButton() {

		reportStep("About to click on back button in refer & earn page", "INFO");

		if(click(backButtonClick)) {

			reportStep("Successfully clicked on the back button in refer & earn page", "PASS");
		}else {

			reportStep("Failed to  click on the back button in refer & earn page", "FAIL");
		}

		return new MyEarningsPage(driver, testInfo);
		
	}
	
	public ReferralNetworkPage clickBackButtonRedirectToReferralNetwork() {

		reportStep("About to click on back button in refer & earn page", "INFO");

		if(click(backButtonClick)) {

			reportStep("Successfully clicked on the back button in refer & earn page", "PASS");
		}else {

			reportStep("Failed to  click on the back button in refer & earn page", "FAIL");
		}

		return new ReferralNetworkPage(driver, testInfo);
		
	}

	public void validateReferAndEarnPage() {

		reportStep("About to validate the Refer and Earn Page ", "INFO");

		//Other elements :
		validateTheElementPresence(referAndEarnLifeTime);
		validateTheElementPresence(helpFriendsEarn);
		validateTheElementPresence(inviteFriendText);
		validateTheElementPresence(shareYourInviteCodeText);
		validateTheElementPresence(referAndEarnImg);

		//Scrolling down Twice :
		scrollFromDownToUpinApp();
		scrollFromDownToUpinApp();

		validateTheElementPresence(yourReferralText);
		validateTheElementPresence(copyTheLinkText);
		validateTheElementPresence(referralLinkURL);
		validateTheElementPresence(tapToCopyText);
		validateTheElementPresence(backButtonClick);
		validateTheElementPresence(inviteBySocialMedia);

		//Icon validation
		validateTheElementPresence(whatAppShareIcon);
		validateTheElementPresence(twitterShareIcon);
		validateTheElementPresence(facebookShareIcon);
		//Icon Text validation :
		validateTheElementPresence(whatsAppText);
		validateTheElementPresence(twitterText);
		validateTheElementPresence(facebookText);
		
		
	}

	public FacebookPage clickOnFacebook() {
		
		reportStep("About to click on Facebook icon ", "INFO");
		
		scrollFromDownToUpinApp();
		scrollFromDownToUpinApp();
		
		if(click(facebookShareIcon)) {
			
			reportStep("Successfully clicked on the Facebook share icon ", "PASS");
		}else {
			
			reportStep("Failed to click on the Facebook Share Icon", "Fail");
		}
		
		return new FacebookPage(driver, testInfo);
		
	}

	public void clickOnTwitter() {

		reportStep("About to click on Twitter icon ", "INFO");

		scrollFromDownToUpinApp();
		scrollFromDownToUpinApp();

		if(click(twitterShareIcon)) {

			reportStep("Successfully clicked on the Twitter share icon ", "PASS");
		}else {

			reportStep("Failed to click on the Twitter Share Icon", "Fail");
		}

		validateTheElementPresence(twitterSharedText);
	}

	public void clickOnWhatsApp() {

		reportStep("About to click Whats App Share ICon - It will Open the Whats App  ", "INFO");

		scrollFromDownToUpinApp();
		scrollFromDownToUpinApp();

		if(click(whatAppShareIcon)) {

			reportStep("Successfully clicked on the Whats App share icon ", "PASS");
		}else {

			reportStep("Failed to click on the Whats App Share Icon", "Fail");
		}

	}





	
	
	
	
	
	
	
	
	
	
}
