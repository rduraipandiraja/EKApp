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

public class TotalProfitEarnedPage extends WrapperMethods{
	
	public TotalProfitEarnedPage(AppiumDriver<MobileElement> driver, ExtentTest testInfo) {

		WebDriverWait wait = new WebDriverWait(driver, 30);

		this.driver = driver;
		this.testInfo = testInfo;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);

		reportStep("Waiting for the totalProfitEarned Page", "INFO");

		try {
			wait.until(ExpectedConditions.visibilityOf(totalProfitEarned));

			reportStep("Successfully landed on the totalProfitEarned page", "PASS");

		}catch(Exception e) {

			reportStep(e.getMessage(), "INFO");
			reportStep("Not able to land on the  totalProfitEarned page", "FAIL");
		}

	}
		
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='Total Profit Earned']")
	MobileElement totalProfitEarned;

	@FindBy(how = How.XPATH, using = "(//android.widget.ImageView)[1]") 
	MobileElement backButtonClick;

	String aboutProfitStatus = "//android.widget.TextView[@text='ABOUT PROFIT STATUS']";

	String paidProfit= "//android.widget.TextView[@text='Paid Profit - Profit that has been successfully paid to you through bank transfer.']";

	String pendingProfit= "//android.widget.TextView[@text='Pending Profit - Profit that has tracked as a transaction but is yet to be Confirmed by the retailer.']";

	String referralProfit= "//android.widget.TextView[@text='Referral Profit - Every time your referral gets Confirmed Profit, you get 10% of their earnings.']";

	String availableProfit = "//android.widget.TextView[@text='Available Profit - Confirmed Profit which you can request and transfer to your bank account.']";

	public TotalProfitEarnedPage validateTotalProfitEarnedValue(String totalProfitEarnedValue) {

		reportStep("About to verify the value total Profit earned "+totalProfitEarnedValue+" in my earnings page ", "INFO");
		
		String totalCbEarnedValueXpath = "//android.widget.TextView[@text='Total Profit Earned']/parent::android.view.ViewGroup/android.widget.TextView[contains(@text,"+totalProfitEarnedValue+")]|//android.widget.TextView[@text='Total Profit Earned']/parent::android.view.View/android.widget.TextView[contains(@text,"+totalProfitEarnedValue+")]";

		if(isElementLocatedByXpathPresent(totalCbEarnedValueXpath)) {
			reportStep("Verified the value total Profit earned "+totalProfitEarnedValue+" in my earnings page ", "PASS");

		}else {
			reportStep("Not able to verify the value total Profit earned "+totalProfitEarnedValue+" in my earnings page ", "FAIL");

		}
		
		MobileElement totalCbEarnedValue = driver.findElement(By.xpath(totalCbEarnedValueXpath));

		validateTheElementPresence(totalCbEarnedValue);

		reportStep("Successfully verified the value total Profit earned "+totalProfitEarnedValue+" in my earnings page ", "INFO");

		return new TotalProfitEarnedPage(driver, testInfo);

		
	}
	
	public TotalProfitEarnedPage validatePaidProfitValue(String paidProfitValue) {

		reportStep("About to verify the value paid Profit value "+paidProfitValue+" in total Profit earned page ", "INFO");
		
		String paidCbValueXpath = "//android.widget.TextView[contains(@text,'Paid Profit')]/parent::android.view.ViewGroup/android.widget.TextView[contains(@text,'"+paidProfitValue+"/-')]|//android.widget.TextView[contains(@text,'Paid Profit')]/parent::android.view.View/android.widget.TextView[contains(@text,'"+paidProfitValue+"/-')]";
	
		if(isElementLocatedByXpathPresent(paidCbValueXpath)) {
			reportStep("Verified the value paid Profit value "+paidProfitValue+" in total Profit earned page ", "PASS");

		}else {
			reportStep("Not able to verify the value paid Profit value "+paidProfitValue+" in total Profit earned page ", "FAIl");

		}
		
		MobileElement paidCbValue = driver.findElement(By.xpath(paidCbValueXpath));

		validateTheElementPresence(paidCbValue);

		reportStep("Successfully verified the value paid Profit value "+paidProfitValue+" in total Profit earned page ", "INFO");

		return new TotalProfitEarnedPage(driver, testInfo);

		
	}
	
	public TotalProfitEarnedPage validatePendingProfitValue(String pendingProfitValue) {

		reportStep("About to verify the value pending Profit value "+pendingProfitValue+" in total Profit earned page ", "INFO");
		
		String pendingCbValueXpath = "//android.widget.TextView[contains(@text,'Pending Profit')]/parent::android.view.ViewGroup/android.widget.TextView[contains(@text,'"+pendingProfitValue+"/-')]|//android.widget.TextView[contains(@text,'Pending Profit')]/parent::android.view.View/android.widget.TextView[contains(@text,'"+pendingProfitValue+"/-')]";

		if(isElementLocatedByXpathPresent(pendingCbValueXpath)) {
			reportStep("Verified the value pending Profit value "+pendingProfitValue+" in total Profit earned page ", "PASS");

		}else {
			reportStep("Not able to verify the value pending Profit value "+pendingProfitValue+" in total Profit earned page ", "FAIL");

		}
		
		MobileElement pendingCbValue = driver.findElement(By.xpath(pendingCbValueXpath));

		validateTheElementPresence(pendingCbValue);

		reportStep("Successfully verified the value pending Profit value "+pendingProfitValue+" in total Profit earned page ", "INFO");

		return new TotalProfitEarnedPage(driver, testInfo);

		
	}
	
	public TotalProfitEarnedPage validateReferralProfitValue(String referralProfitValue) {

		reportStep("About to verify the value referral Profit value "+referralProfitValue+" in total Profit earned page ", "INFO");

		String referralCbValueXpath = "//android.widget.TextView[contains(@text,'Referral Profit')]/parent::android.view.ViewGroup/android.widget.TextView[contains(@text,'"+referralProfitValue+"/-')]|//android.widget.TextView[contains(@text,'Referral Profit')]/parent::android.view.View/android.widget.TextView[contains(@text,'"+referralProfitValue+"/-')]";

		if(isElementLocatedByXpathPresent(referralCbValueXpath)) {
			reportStep("Verify the value referral Profit value "+referralProfitValue+" in total Profit earned page ", "PASS");

		}else {
			reportStep("Not able to verify the value referral Profit value "+referralProfitValue+" in total Profit earned page ", "FAIL");

		}
		
		MobileElement referralCbValue = driver.findElement(By.xpath(referralCbValueXpath));

		validateTheElementPresence(referralCbValue);

		reportStep("Successfully verified the value referral Profit value "+referralProfitValue+" in total Profit earned page ", "INFO");

		return new TotalProfitEarnedPage(driver, testInfo);

		
	}
	
	public TotalProfitEarnedPage validateAvailableProfitValue(String availProfitValue) {

		reportStep("About to verify the value available Profit "+availProfitValue+" in total Profit earned page ", "INFO");
		
		String availCbValueXpath = "//android.widget.TextView[contains(@text,'Available Profit')]/parent::android.view.ViewGroup/android.widget.TextView[contains(@text,'"+availProfitValue+"/-')]|//android.widget.TextView[contains(@text,'Available Profit')]/parent::android.view.View/android.widget.TextView[contains(@text,'"+availProfitValue+"/-')]";

		if(isElementLocatedByXpathPresent(availCbValueXpath)) {
			reportStep("Verify the value available Profit "+availProfitValue+" in total Profit earned page ", "PASS");

		}else {
			reportStep("Not able to verify the value available Profit "+availProfitValue+" in total Profit earned page ", "FAIL");

		}
		
		MobileElement availCbValue = driver.findElement(By.xpath(availCbValueXpath));

		validateTheElementPresence(availCbValue);

		reportStep("Successfully verified the value available Profit "+availProfitValue+" in total Profit earned page ", "INFO");

		return new TotalProfitEarnedPage(driver, testInfo);

		
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

	public TotalProfitEarnedPage clickAboutProfitStatus() {

		reportStep("About to click on AboutProfitStatus", "INFO");
		
		scrollTillRequiredElementIsVisibleFromDownToUp(aboutProfitStatus);
		
		if(isElementLocatedByXpathPresent(aboutProfitStatus)) {
			reportStep("clicked on AboutProfitStatus", "PASS");

		}else {
			reportStep("Not able to click on AboutProfitStatus", "FAIL");

		}

		MobileElement aboutProfitStatusLink = driver.findElement(By.xpath(aboutProfitStatus));

		if(clickAfterWait(aboutProfitStatusLink)){

			reportStep("Successfully clicked on the aboutProfitStatus", "PASS");

		}else {

			reportStep("Not able to click aboutProfitStatus", "FAIL");
		}

		return this;

	}

	public TotalProfitEarnedPage validateAboutProfitStatusText() {

		reportStep("About to verify the AboutProfitStatusText", "INFO");
	
		scrollTillRequiredElementIsVisible(paidProfit);
		if(isElementLocatedByXpathPresent(paidProfit)) {
			
			reportStep("About to verify the paidProfit Text ", "PASS");

		}else {
			reportStep("Not able to verify the paidProfit Text ", "FAIL");

		}
		
		scrollTillRequiredElementIsVisible(pendingProfit);
		if(isElementLocatedByXpathPresent(pendingProfit)) {
			
			reportStep("About to verify the pendingProfit Text ", "PASS");

		}else {
			reportStep("Not able to verify the pendingProfit Text ", "FAIL");

		}
		
		scrollTillRequiredElementIsVisible(referralProfit);
		if(isElementLocatedByXpathPresent(referralProfit)) {
			
			reportStep("About to verify the referralProfit Text ", "PASS");

		}else {
			reportStep("Not able to verify the referralProfit Text ", "FAIL");

		}
		
		scrollTillRequiredElementIsVisible(availableProfit);
		if(isElementLocatedByXpathPresent(availableProfit)) {
			
			reportStep("About to verify the availableProfit Text ", "PASS");

		}else {
			reportStep("Not able to verify the availableProfit Text ", "FAIL");

		}

		return this;

		
	}

}
