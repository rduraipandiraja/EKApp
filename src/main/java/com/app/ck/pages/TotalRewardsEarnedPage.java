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

public class TotalRewardsEarnedPage extends WrapperMethods{
	
	public TotalRewardsEarnedPage(AppiumDriver<MobileElement> driver, ExtentTest testInfo) {

		WebDriverWait wait = new WebDriverWait(driver, 30);

		this.driver = driver;
		this.testInfo = testInfo;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);

		reportStep("Waiting for the totalRewardsEarned Page", "INFO");

		try {
			wait.until(ExpectedConditions.visibilityOf(totalRewardsEarned));

			reportStep("Successfully landed on the totalRewardsEarned page", "PASS");

		}catch(Exception e) {

			reportStep(e.getMessage(), "INFO");
			reportStep("Not able to land on the  totalRewardsEarned page", "FAIL");
		}

	}
		
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='Total Rewards Earned']")
	MobileElement totalRewardsEarned;

	@FindBy(how = How.XPATH, using = "(//android.widget.ImageView)[1]") 
	MobileElement backButtonClick;

	public TotalRewardsEarnedPage validateTotalRewardsEarnedValue(String totalRewardsEarnedValue) {

		reportStep("About to verify the value total Rewards earned "+totalRewardsEarnedValue+" in my earnings page ", "INFO");
		
		String totalRwEarnedValueXpath = "//android.widget.TextView[@text='Total Rewards Earned']/parent::android.view.ViewGroup/android.widget.TextView[contains(@text,'"+totalRewardsEarnedValue+"')]|//android.widget.TextView[@text='Total Rewards Earned']/parent::android.view.View/android.widget.TextView[contains(@text,'"+totalRewardsEarnedValue+"')]";

		if(isElementLocatedByXpathPresent(totalRwEarnedValueXpath)) {
			reportStep("Verify the value total Rewards earned "+totalRewardsEarnedValue+" in my earnings page ", "PASS");

		}else {
			reportStep("Not able to verify the value total Rewards earned "+totalRewardsEarnedValue+" in my earnings page ", "FAIL");

		}
		
		MobileElement totalRwEarnedValue = driver.findElement(By.xpath(totalRwEarnedValueXpath));

		validateTheElementPresence(totalRwEarnedValue);

		reportStep("Successfully verified the value total Rewards earned "+totalRewardsEarnedValue+" in my earnings page ", "INFO");

		return new TotalRewardsEarnedPage(driver, testInfo);

		
	}
	
	public TotalRewardsEarnedPage validatePaidRewardsValue(String paidRewardsValue) {

		reportStep("About to verify the value paid Rewards value "+paidRewardsValue+" in total Rewards earned page ", "INFO");

		String paidCbValueXpath= "//android.widget.TextView[contains(@text,'Paid Rewards')]/parent::android.view.ViewGroup/android.widget.TextView[contains(@text,'"+paidRewardsValue+"/-')]|//android.widget.TextView[contains(@text,'Paid Rewards')]/parent::android.view.View/android.widget.TextView[contains(@text,'"+paidRewardsValue+"/-')]";

		if(isElementLocatedByXpathPresent(paidCbValueXpath)) {
			reportStep("About to verify the value paid Rewards value "+paidRewardsValue+" in total Rewards earned page ", "PASS");

		}else {
			reportStep("Not able to verify the value paid Rewards value "+paidRewardsValue+" in total Rewards earned page ", "FAIL");

		}
		
		MobileElement paidCbValue = driver.findElement(By.xpath(paidCbValueXpath));

		validateTheElementPresence(paidCbValue);

		reportStep("Successfully verified the value paid Rewards value "+paidRewardsValue+" in total Rewards earned page ", "INFO");

		return new TotalRewardsEarnedPage(driver, testInfo);

		
	}
	
	public TotalRewardsEarnedPage validatePendingRewardsValue(String pendingRewardsValue) {

		reportStep("About to verify the value pending Rewards value "+pendingRewardsValue+" in total Rewards earned page ", "INFO");

		String pendingCbValueXpath = "//android.widget.TextView[contains(@text,'Pending Rewards')]/parent::android.view.ViewGroup/android.widget.TextView[contains(@text,'"+pendingRewardsValue+"/-')]|//android.widget.TextView[contains(@text,'Pending Rewards')]/parent::android.view.View/android.widget.TextView[contains(@text,'"+pendingRewardsValue+"/-')]";

		if(isElementLocatedByXpathPresent(pendingCbValueXpath)) {
			reportStep("Verify the value pending Rewards value "+pendingRewardsValue+" in total Rewards earned page ", "PASS");

		}else {
			reportStep("Not able to verify the value pending Rewards value "+pendingRewardsValue+" in total Rewards earned page ", "FAIL");

		}
		
		MobileElement pendingCbValue = driver.findElement(By.xpath(pendingCbValueXpath));

		validateTheElementPresence(pendingCbValue);

		reportStep("Successfully verified the value pending Rewards value "+pendingRewardsValue+" in total Rewards earned page ", "INFO");

		return new TotalRewardsEarnedPage(driver, testInfo);

		
	}
		
	public TotalRewardsEarnedPage validateAvailableRewardsValue(String availRewardsValue) {

		reportStep("About to verify the value available Rewards "+availRewardsValue+" in total Rewards earned page ", "INFO");
		
		String availCbValueXpath = "//android.widget.TextView[contains(@text,'Available Rewards')]/parent::android.view.ViewGroup/android.widget.TextView[contains(@text,'"+availRewardsValue+"/-')]|//android.widget.TextView[contains(@text,'Available Rewards')]/parent::android.view.View/android.widget.TextView[contains(@text,'"+availRewardsValue+"/-')]";

		if(isElementLocatedByXpathPresent(availCbValueXpath)) {
			reportStep("Verify the value available Rewards "+availRewardsValue+" in total Rewards earned page ", "PASS");

		}else {
			reportStep("Not able to verify the value available Rewards "+availRewardsValue+" in total Rewards earned page ", "FAIL");

		}
		
		MobileElement availCbValue = driver.findElement(By.xpath(availCbValueXpath));

		validateTheElementPresence(availCbValue);

		reportStep("Successfully verified the value available Rewards "+availRewardsValue+" in total Rewards earned page ", "INFO");

		return new TotalRewardsEarnedPage(driver, testInfo);

		
	}

	public MyEarningsPage clickBackButton() {

		reportStep("About to click on back button in my earnings page", "INFO");

//		if(click(backButtonClick)) {
//
//			reportStep("Successfully clicked on the back button in my earnings page", "PASS");
//		}else {
//
//			reportStep("Failed to  click on the back button in my earnings page", "FAIL");
//		}
		
		driver.navigate().back();

		return new MyEarningsPage(driver, testInfo);
		
	}

}
