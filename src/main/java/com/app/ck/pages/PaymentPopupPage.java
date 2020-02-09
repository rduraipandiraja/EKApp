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

public class PaymentPopupPage extends WrapperMethods{
	
	//Constructor call
			public PaymentPopupPage(AppiumDriver<MobileElement> driver, ExtentTest testInfo) {

				WebDriverWait wait = new WebDriverWait(driver, 30);

				this.driver = driver;
				this.testInfo = testInfo;
				PageFactory.initElements(new AppiumFieldDecorator(driver), this);

				reportStep("Waiting for the Payment popup page ", "INFO");
				try {
					wait.until(ExpectedConditions.visibilityOf(PaymentPopup));
					reportStep("Successfully landed on the My Payment popup ", "PASS");

				}catch(Exception e) {

					reportStep(e.getMessage(), "INFO");
					reportStep("Not able to land on the My Payment popup ", "FAIL");
				}

			}
			
			@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='Payment Popup']")
			MobileElement PaymentPopup ;

		
			@FindBy(how = How.XPATH, using = "//android.widget.TextView[@content-desc='txt_paymentPopup_Msg']")
			MobileElement intermediatePopupMessage ;
			@FindBy(how = How.XPATH, using = "//android.widget.TextView[contains(@text,'COMBINE REWARDS + CASHBACK')]")
			MobileElement combineRewardsCashbackButton ;
			@FindBy(how = How.XPATH, using = "//android.widget.ImageView[@content-desc='img_paymentPopup_walletIMG']")
			MobileElement paymentPopUpPageImage ;
			@FindBy(how = How.XPATH, using = "//android.widget.TextView[contains(@text,'OR')]")
			MobileElement oRText ;
			@FindBy(how = How.XPATH, using = "//android.widget.TextView[contains(@text,'ONLY REDEEM CASHBACK EARNINGS')]")
			MobileElement onlyRedeemCashbackEarnings ;
			@FindBy(how = How.XPATH, using = "//android.widget.TextView[contains(@text,'ONLY REDEEM REWARDS EARNINGS')]")
			MobileElement onlyRedeemRewardsEarnings ;
			@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='Sorry, you have not reached the minimum limit to withdraw your Cashback. However, since your Cashback + Rewards (₹250.00) are greater than the ₹250.00 limit you can redeem them together via Gift Cards or Wallet transfer (not NEFT).']")
			MobileElement sorryText ;
			
			@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='Thanks. You also have ₹250.00 Rewards earnings ready to be withdrawn. Would you like to combine them with these Cashback & redeem together via Gift Cards or Wallet transfer (not NEFT)?']")
			MobileElement thanksTextCashbackRequest ;
			@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='Thanks. You also have ₹250.00 Cashback earnings ready to be withdrawn. Would you like to combine them with these Rewards & redeem together via Gift Cards or Wallet transfer (not NEFT)?']")
			MobileElement thanksTextRewardsRequest ;
			
			public PaymentRequestPage clickOnCombineCashbackAndRewardsButton() {
				
				reportStep("About to click on the Combine Cashback and Rewards button at the Payment Intermediate page ", "INFO");
				
				if(click(combineRewardsCashbackButton)) {
					
					reportStep("Successfully clicked on the Cashback and Rewards Combine button at the Payment Popup page ", "PASS");
					
				}else {
					
					reportStep("Failed to click on Cashback and Rewards Combine button at the Payment Popup page ", "FAIL");
				}
				
				
				return new PaymentRequestPage(driver, testInfo);
				
				
			}

			public PaymentRequestPage clickOnOnlyRedeemCashbackButton() {
				
				reportStep("About to click on the Only Redeem Cashback button ", "INFO");

				if(click(onlyRedeemCashbackEarnings)) {

					reportStep("Successfully clicked on the Only Redeem Cashback at the Payment Popup page ", "PASS");

				}else {

					reportStep("Failed to click on Only Redeem Cashback at the Payment Popup page ", "FAIL");
				}
				
				return new PaymentRequestPage(driver, testInfo);
			}

			public PaymentRequestPage clickOnOnlyRedeemRewardsButton() {
				
				reportStep("About to click on the Only Redeem Rewards button " , "INFO");
				
				if(click(onlyRedeemRewardsEarnings)) {

					reportStep("Successfully clicked on the Only Redeem Rewards at the Payment Popup page ", "PASS");

				}else {

					reportStep("Failed to click on Only Redeem Rewards at the Payment Popup page ", "FAIL");
				}
				
				return new PaymentRequestPage(driver, testInfo);
			}

			public PaymentPopupPage validateSorryTextForCashbackRequest() {

				reportStep("About to validate the Sorry text due to insufficient cashback fund  when the Cashback request is made ", "INFO");

				validateTheElementPresence(sorryText);

				return this;

			}

			public PaymentPopupPage validateSorryTextForCashbackRequest(float amount) {
				
				String Xpath  = "//android.widget.TextView[@text='Sorry, you have not reached the minimum limit to withdraw your Cashback. However, since your Cashback + Rewards (₹"+amount+"0) are greater than the ₹250.00 limit you can redeem them together via Gift Cards or Wallet transfer (not NEFT).']";

				try {
					WebDriverWait wait = new WebDriverWait(driver, 10);
					
					 wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Xpath))).isDisplayed();
					
				} catch (Exception e) {
					
					System.err.println(e.getMessage());
				}
				
				MobileElement sorryText = driver.findElementByXPath(Xpath);
				
				reportStep("About to validate the Sorry text due to insufficient cashback fund  when the Cashback request is made ", "INFO");

				validateTheElementPresence(sorryText);

				return this;

			}

			public PaymentPopupPage validateThanksTextForRewardRequestWhenTheRewardAmountIsInsufficient(float rewardAmount ,float cashbackAmount) {

				String Xpath  = "//android.widget.TextView[@text='Thanks. You only have ₹"+rewardAmount+"0 in your Rewards account. However, combining your Cashback earnings (₹"+cashbackAmount+"0) you have reached the minimum limit of ₹250.00. Would you like to redeem both your Cashback and Rewards earnings? Please note in this case, you will not be able to withdraw your Cashback via NEFT. You can withdraw Rewards + Cashback via Gift Cards or Wallet transfer only.']";
			
				try {
					WebDriverWait wait = new WebDriverWait(driver, 10);

					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Xpath))).isDisplayed();

				} catch (Exception e) {

					System.out.println(e.getMessage());
				}

				MobileElement sorryText = driver.findElementByXPath(Xpath);

				reportStep("About to validate the Sorry text due to insufficient cashback fund  when the Cashback request is made ", "INFO");

				validateTheElementPresence(sorryText);

				return this;

			}
			
			public PaymentPopupPage validateThanksTextForCashbackRequest_WhenTheRewardAmount_And_CasbhbackAmount_IsSufficient(float rewardAmount ) {
				
				reportStep("About to validate the intermediate popup message - ", "INFO");

				String xpath  = "//android.widget.TextView[@text='Thanks. You also have ₹"+rewardAmount+"0 Rewards earnings ready to be withdrawn. Would you like to combine them with these Cashback & redeem together via Gift Cards or Wallet transfer (not NEFT)?']";
			
				try {
					
					WebDriverWait wait = new WebDriverWait(driver, 10);

					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).isDisplayed();

				} catch (Exception e) {

					reportStep("Not able to locate the xpath "+ xpath + "till its visibility  . ", "INFO");
					
				}

				MobileElement sorryText = driver.findElementByXPath(xpath);

				reportStep("About to validate the Sorry text due to insufficient cashback fund  when the Cashback request is made ", "INFO");

				validateTheElementPresence(sorryText);

				return this;

			}
			
			public PaymentPopupPage validateThanksTextForCashbackRequest_WhenTheRewardAmount_IsInSufficient_And_CasbhbackAmount_IsSufficient(float rewardAmount ) {

				String xpath = "//android.widget.TextView[@text='Thanks. You also have ₹"+rewardAmount+"0 in Reward earnings but have not reached the minimum Rewards threshold. Would you like to combine your Cashback + Reward earnings and redeem them together? In this case you can redeem via Gift Cards or Wallet transfer (not NEFT).']";
			
				try {
					
					WebDriverWait wait = new WebDriverWait(driver, 10);

					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).isDisplayed();

				} catch (Exception e) {

					System.out.println(e.getMessage());
				}

				MobileElement sorryText = driver.findElementByXPath(xpath);

				reportStep("About to validate the Sorry text due to insufficient cashback fund  when the Cashback request is made ", "INFO");

				validateTheElementPresence(sorryText);

				return this;

			}
			
			public PaymentPopupPage validateThanksTextForRewardsRequest_WhenTheRewardAmount_And_CasbhbackAmount_IsSufficient(float cashbackAmount ) {

				String Xpath  = "//android.widget.TextView[@text='Thanks. You also have ₹"+cashbackAmount+"0 Cashback earnings ready to be withdrawn. Would you like to combine them with these Rewards & redeem together via Gift Cards or Wallet transfer (not NEFT)?']";
			
				try {
					
					WebDriverWait wait = new WebDriverWait(driver, 10);

					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Xpath))).isDisplayed();

				} catch (Exception e) {

					System.out.println(e.getMessage());
				}

				MobileElement sorryText = driver.findElementByXPath(Xpath);

				reportStep("About to validate the Sorry text due to insufficient cashback fund  when the Cashback request is made ", "INFO");

				validateTheElementPresence(sorryText);

				return this;

			}

			
			
}
