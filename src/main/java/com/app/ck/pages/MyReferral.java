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

public class MyReferral extends WrapperMethods  {
	
	//Constructor call
	public MyReferral(AppiumDriver<MobileElement> driver, ExtentTest testInfo) {

		WebDriverWait wait = new WebDriverWait(driver, 30);

		this.driver = driver;
		this.testInfo = testInfo;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);

		reportStep("Waiting for the Referral Network page", "INFO");
		try {
			wait.until(ExpectedConditions.visibilityOf(myReferralHeader));
			reportStep("Successfully landed on the my Referral page", "PASS");

		}catch(Exception e) {
			
			reportStep(e.getMessage(), "INFO");
			reportStep("Not able to land on the my Referral page", "FAIL");
		}

	}
	
	//Elements :	
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@content-desc='txt_profileMenu_withLogin_referAndEarn']") 
	MobileElement referAndEarnLifeTime;

	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@content-desc='txt_profileMenu_withLogin_myReferral']") 
	MobileElement referralNetwork;

	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='My Referral']") 
	MobileElement myReferralHeader;

	public ReferAndEarnLifeTime clickReferAndEarnLifeTime() {

		reportStep("About to click on Refer&EarnLifetime", "INFO");

		if(click(referAndEarnLifeTime)) {

			reportStep("Successfully clicked on Refer&EarnLifetime", "PASS");
		}else {

			reportStep("Failed to  click on Refer&EarnLifetime", "FAIL");
		}

		return new ReferAndEarnLifeTime(driver, testInfo);
		
	}

	public ReferralNetworkPage clickReferralNetwork() {

		reportStep("About to click on ReferralNetwork", "INFO");

		if(click(referralNetwork)) {

			reportStep("Successfully clicked on the ReferralNetwork", "PASS");
		}else {

			reportStep("Failed to  click on the ReferralNetwork", "FAIL");
		}

		return new ReferralNetworkPage(driver, testInfo);
		
	}

	public SignedInProfilePage clickBackButton() {

		reportStep("About to click on back button in MyReferral", "INFO");

		backButton();

		return new SignedInProfilePage(driver, testInfo);
		
	}

	

}
