package com.app.ck.pages;

import java.util.List;

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

public class NotificationPage  extends WrapperMethods {
	
	//document the hard coded values
	//Store is required which we take up for redirectional URL

	//Constructor call
	public NotificationPage(AppiumDriver<MobileElement> driver, ExtentTest testInfo) {

		WebDriverWait wait = new WebDriverWait(driver, 30);

		this.driver = driver;
		this.testInfo = testInfo;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);


	}
	
	@FindBy(how = How.XPATH, using = "(//android.widget.Button[@content-desc='Expand'])[1]")
	MobileElement expand ;
	@FindBy(how = How.XPATH, using = "//android.view.NotificationHeaderView.2[@content-desc=\"Expand button\"]")
	MobileElement expand_Moto ;
	@FindBy(how = How.XPATH, using = "(//android.widget.Button[@content-desc='Expand'])[1]")
	List<MobileElement> ListOfexpand ;
	@FindBy(how = How.ID, using = "android:id/big_picture")
	MobileElement bigImage;
	@FindBy(how = How.ID, using = "android:id/big_picture")
	List<MobileElement> listOfBigImage;
	@FindBy(how = How.ID, using = "android:id/right_icon")
	MobileElement largeIcon;
	@FindBy(how = How.XPATH, using = "//android.widget.ImageView[@content-desc=\"EarnKaro\"]")
	MobileElement largeIcon_RedMi;
	@FindBy(how = How.ID, using = "android:id/right_icon")
	List<MobileElement> listOfLargeIcon;
	@FindBy(how = How.XPATH, using = "//*[contains(@text,'Notification Title')]")
	MobileElement notificationTitle;
	@FindBy(how = How.XPATH, using = "//*[@text='Notification Title']")
	List<MobileElement> listOfNotificationTitle;
	@FindBy(how = How.XPATH, using = "//*[@text='APPIUM AUTOMATION TEST HP']")
	MobileElement notificationTitle_WithTemplateHighPriority;
	
	@FindBy(how = How.XPATH, using = "//*[@text='NOTIFICATION AT DIFFERENT PAGE']")
	MobileElement notificationTitle_WithTemplateHighPriority_DifferentPage;
	@FindBy(how = How.XPATH, using = "//*[@text='APPIUM AUTOMATION TEST NP']")
	MobileElement notificationTitle_WithTemplateNormalPriority;
	@FindBy(how = How.XPATH, using = "//*[@text='TEST WITHOUT LARGE ICON REDIRECT URL IMAGE URL']")
	MobileElement notificationTitle_WithTemplateHighPriority_WithoutRedirectURL_WithoutBigImage_WithoutLargeICon;
	@FindBy(how = How.XPATH, using = "//*[@text='TEST WITHOUT REDIRECT URL']")
	MobileElement notificationTitle_WithTemplateHighPriority_WithoutRedirectURL;
	
	@FindBy(how = How.XPATH, using = "//*[@text='Notification Body']") 
	MobileElement notificationBody;
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='Big Text']")
	MobileElement bigText;
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='Ajio']")
	MobileElement ajioStore;
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='NotificationTest']")
	MobileElement NotificationTestStore;
	@FindBy(how = How.XPATH, using = "//*[@text='YOUR PENDING CASHBACK CONFIRMED!']")
	MobileElement pendingNotification;
	@FindBy(how = How.XPATH, using = "//*[contains(@text,'Big Text')]")
	MobileElement pendingNotification_BigText;
	@FindBy(how = How.XPATH, using = "//*[@text='YOUR FAILED CASHBACK']")
	MobileElement cancelledNotification;
	@FindBy(how = How.XPATH, using = "//*[contains(@text,'Big Text')]")
	MobileElement cancelledNotification_BigText;
	@FindBy(how = How.XPATH, using = "//*[@text='YOUR CASHBACK GOT CONFIRMED!']")
	MobileElement confirmedNotification;
	@FindBy(how = How.XPATH, using = "//*[contains(@text,'Big Text')]")
	MobileElement confirmedNotification_BigText;
	@FindBy(how = How.XPATH, using = "//*[@text='CASHOUT CREATED']")
	MobileElement cashOutNotification;
	@FindBy(how = How.XPATH, using = "//*[contains(@text,'Big Text')]")
	MobileElement cashOutNotification_BigText;
	
	//Confirmed Referral Notification : 
	@FindBy(how = How.XPATH, using = "//*[@text='Your Referral Cashback']")
	MobileElement referralCbNotification;
	@FindBy(how = How.XPATH, using = "//*[contains(@text,'Notification Body')]")
	MobileElement referralCbNotification_SubText;
	@FindBy(how = How.XPATH, using = "//*[@text='Big Text']")
	MobileElement referraltNotificationAfterExpand;
	
	//REDMI Notification Changes :
	@FindBy(how = How.ID, using = "android:id/up_arrow")
	MobileElement notificationUpArrow;
	
	
	
	public NotificationPage clickOnExpand() {

		reportStep("About to click on the Expand button", "INFO");

		if(clickAfterWait(expand)) {

			reportStep("Successfully clicked on the Expand button ", "PASS");

		} else {

			reportStep("Failed to Click on the Expand Button ", "FAIL");
		}

		return this;
	}
	
	public NotificationPage clickOnExpand(String deviceName) {

		deviceName = deviceName.toLowerCase();
		
		reportStep("Device Name is  " +  deviceName, "INFO");

		switch (deviceName) {

		case "samsung":

			reportStep("About to click on the Expand button", "INFO");

			if (clickAfterWait(expand)) {

				reportStep("Successfully clicked on the Expand button ", "PASS");

			} else {

				reportStep("Failed to Click on the Expand Button ", "FAIL");
			}

			break;

		case "redmi":

			reportStep("No need to Expand In Redmi ... Device specific behaviour  ", "INFO");

			break;

		case "moto":

			reportStep("About to click on the Expand button", "INFO");

			if (clickAfterWait(expand_Moto)) {

				reportStep("Successfully clicked on the Expand button ", "PASS");

			} else {

				reportStep("Failed to Click on the Expand Button ", "FAIL");
			}

			break;

		}

		return this;


	}
	
	public NotificationPage validateAbsenceOfExpand() {
		
		reportStep("About to valildate the Absence of Expand ", "INFO");
		
		int sizeOfExpand = ListOfexpand.size();
		
		if(sizeOfExpand==0) {
			
			reportStep("Successfully validated that , No Notification Expand is present ", "PASS");
		} else {
			
			reportStep("Failed - We dont have Big image - but it is showing the Expand link ", "FAIL");
		}
		return this;
		
	}

	public NotificationPage validateBigImage() {

		reportStep("About to validate the Big Image - Presence", "INFO");

		validateTheElementPresence(bigImage);
		return this;

	}

	public NotificationPage validateBigImage(String deviceName) {

		deviceName = deviceName.toLowerCase();
		
		reportStep("Device Name is  " +  deviceName, "INFO");

		switch (deviceName) {

		case "samsung":
			
			reportStep("About to validate the Big Image - Presence", "INFO");

			validateTheElementPresence(bigImage);

			break;

		case "redmi":
			reportStep("About to close the Noitifcation Bar and Reopen to validate the Big Image in RedMi", "INFO");
			
			closeNotification();
			openNotification();
			validateTheElementPresence(bigImage);

			break;

		case "moto":

			reportStep("About to validate the Big Image - Presence", "INFO");

			validateTheElementPresence(bigImage);

			break;



		}

		return this;

	}

	public NotificationPage validatelargeIcon() {

		reportStep("About to validate the Large Icon presence ", "INFO");
		validateTheElementPresence(largeIcon);
		return this;
	}

	public NotificationPage validatelargeIcon(String deviceName) {
		
		switch (deviceName) {

		case "samsung":

			reportStep("About to validate the Large Icon presence ", "INFO");

			validateTheElementPresence(largeIcon);
			
			break;

		case "redmi":
			
			reportStep("About to validate the Large Icon presence in RedmI Device , Its changed as device specific ", "INFO");

			validateTheElementPresence(largeIcon_RedMi);
			
			break;

		case "moto":
			
			reportStep("About to validate the Large Icon presence ", "INFO");

			validateTheElementPresence(largeIcon);
			
			break;

		}
		
		return this;
	}

	public NotificationPage validateNotificationTitle() {

		reportStep("About to validate the Notification Title ", "INFO");
		validateTheElementPresence(notificationTitle);
		return this;
	}
	
	public NotificationPage validateTheMultipleNotificationOnNotificationBar(int expected) {

		reportStep("About to validate the MultipleNotification on Notification Bar : Number of Notificaion must be equal to " + expected , "INFO");
		if(isElementLocatedByXpathPresent("//*[@text='Notification Title']")) {
			
			int actual = listOfNotificationTitle.size();
			validateTheActualValueWithExpectedValue(actual, expected);
		} else {
			
			reportStep("Notification is not arrived , Please analyse the report ", "FAIL");
		}
	
		return this;
	}
	
	public NotificationPage validateNotificationIsnotAppearedForUnsignedUser() {

		reportStep("About to validate the Notification Title Absence", "INFO");

		if(isElementLocatedByXpathPresent("//*[@text='Notification Title']")) {
			
			reportStep("After logout, pushing notification is sending the notification to the user - This is a bug"
					+ " contact deveoper ", "FAIL"); 
		} else {
			
			reportStep("After logout, pushing notification is not sending the notification to the user - This is expected"
					+ " Behaviour ", "PASS"); 
			
			
		}
		return this;
	}
	
	//With / Witout Template
	public NotificationPage validateNotificationTitle_WithTemplateHighPriority() {

		reportStep("About to validate the Notification title for the With Template High Priority notificaiton ", "INFO");

		validateTheElementPresence(notificationTitle_WithTemplateHighPriority);
		return this;
	}
	
	public NotificationPage validateNotificationTitle_withDifferentRedirectionTitle() {

		reportStep("About to validate the Notification title for the With Template High Priority notificaiton ", "INFO");

		validateTheElementPresence(notificationTitle_WithTemplateHighPriority_DifferentPage);
		return this;
	}
	
	public NotificationPage validateNotificationTitle_WithTemplateHighPriority_WithoutRedirectURL_WithoutBigImage_WithoutLargeIcon() {

		reportStep("About to validate the Notification title for the With Template High Priority notificaiton where without "
				+ "  Redirect URL, without large icon and without big image ", "INFO");

		validateTheElementPresence(notificationTitle_WithTemplateHighPriority_WithoutRedirectURL_WithoutBigImage_WithoutLargeICon);
		return this;
	}
	
	public NotificationPage validateNotificationTitle_WithTemplateHighPriority_WithoutRedirectURL() {

		reportStep("About to validate the Notification title for the With Template High Priority notificaiton without Redirect URL", "INFO");

		validateTheElementPresence(notificationTitle_WithTemplateHighPriority_WithoutRedirectURL);
		return this;
	}
	
	public NotificationPage validateNotificationTitle_WithTemplateNormalPriority() {

		reportStep("About to validate the Notification title for the With Template Normal Priority notificaiton ", "INFO");

		validateTheElementPresence(notificationTitle_WithTemplateNormalPriority);
		return this;
	}

	public NotificationPage validateNotificationBody() {

		reportStep("About to validate the Notification Body presence ", "INFO");

		validateTheElementPresence(notificationBody);
		return this;
	}
	
	public NotificationPage validateNotificationBody(String deviceName) {
		
		deviceName = deviceName.toLowerCase();
		
		reportStep("Device Name is " +  deviceName, "INFO");
		
		switch (deviceName) {

		case "samsung":
			
			reportStep("About to validate the Notification Body presence ", "INFO");

			validateTheElementPresence(notificationBody);
			
			break;

		case "redmi":
			
			reportStep("About to validate the Notification Body presence Once after clicking the UpArrow in RedmI device ", "INFO");

			if (clickAfterWait(notificationUpArrow)) {
				
				reportStep("Successfully clicked on the UpArrow To Validate the Notification body in RedMi Phone", "PASS");
			} else {
				
				reportStep("Failed to click on the UpArrow To Validate the Notification body in RedMi Phone", "FAIL");
			}
			
			validateTheElementPresence(notificationBody);
			
			break;

		case "moto":
			
			this.clickOnExpand(deviceName);
			
			reportStep("About to validate the Notification Body presence ", "INFO");

			validateTheElementPresence(notificationBody);
			
			break;
			
			

		}
		
		return this;

	
	}

	public NotificationPage validateBigText() {

		reportStep("About to validate the BigText ", "INFO");

		validateTheElementPresence(bigText);
		return this;
	}

	public NotificationPage validateAjioStoreRedirection() {

		reportStep("About to validate Ajio Store - Redirection once after clicking on the Notification ", "INFO");
		
		if(validateTheElementPresence(ajioStore,"INFO")) {

			reportStep("Successfully validated the AJIO Store Page Redirection ", "PASS");
		}else {

			reportStep("Notification is not redircted to the AJIO store Page - Redirection Issue in Notificaion", "FAIL");
		}

		return this;
	}
	
	public NotificationPage validateNotificationTestStorePage() {

		reportStep("About to validate Store - Notification Test ", "INFO");
 
		if(validateTheElementPresence(NotificationTestStore,"INFO")) {
		
		reportStep("Successfully validated the  Notification Test Store Page Redirection ", "PASS");
	} else {

		reportStep("Notification is not redircted to the  Notification Test store - - Redirection Issue in Notificaion ", "FAIL");
	}
		
		return this;
	}

	public NotificationPage clickOnBigImage() {
		
		reportStep("About to click on the Big Image ", "INFO");
		
		if(clickAfterWait(bigImage)) {
			reportStep("Successfully clicked on the Big  Image", "PASS");
		}else {
			reportStep("Failed to click on the Big Image ", "FAIL");
		}
		
		return this;
		
	}


	public NotificationPage validateTheAbsenceoflargeIcon(String deviceName) {

		deviceName = deviceName.toLowerCase();
		
		reportStep("Device Name is  " +  deviceName, "INFO");

		switch (deviceName) {

		case "samsung":
			
			reportStep("About to validate the Absence of Large icon ", "INFO");

			if(listOfLargeIcon.size()==0) {

				reportStep("No Large icon is present as expectd ", "PASS");

			} else {

				reportStep("FAIL - We didnt configure the Large icon to be dispalyed - But it is getting displayed .  ", "FAIL");
			}

			break;

		case "redmi":
			
			// No validation required - due to device specific behaviour
			return this;

		case "moto":

			reportStep("About to validate the Absence of Large icon ", "INFO");

			if(listOfLargeIcon.size()==0) {

				reportStep("No Large icon is present as expectd ", "PASS");

			} else {

				reportStep("FAIL - We didnt configure the Large icon to be dispalyed - But it is getting displayed .  ", "FAIL");
			}

			break;
			
		}

		return this;
	}

	public NotificationPage validateTheAbsenceofBigImage() {

		reportStep("About to validate the Absence of Big Image ", "INFO");

		if(listOfBigImage.size()==0) {

			reportStep("No Big Image is present as expectd ", "PASS");

		} else {

			reportStep("FAIL - We didnt configure the Big Image to be dispalyed - But it is getting displayed .  ", "FAIL");
		}

		return this;
	}

	public NotificationPage clickOnNotificationTitle() {

		reportStep("About to click on the Notification Title ", "INFO");

		if(clickAfterWait(notificationTitle)) {

			reportStep("Successfully clicked on the Notification Title", "PASS");
		} else {

			reportStep("Failed to click on the Notification Title", "FAIL");
		}

		return this;
	}

	public NotificationPage clickOnNotificationTitle_WithoutRedirection_WithoutLargeIcon_WithoutBigImage() {

		reportStep("About to click on the Notification Title  - Without large icon , without redirect URL and without Big Image", "INFO");

		if(clickAfterWait(notificationTitle_WithTemplateHighPriority_WithoutRedirectURL_WithoutBigImage_WithoutLargeICon)) {

			reportStep("Successfully clicked on the Notification Title -  Without large icon , without redirect URL and without Big Image", "PASS");
		} else {

			reportStep("Failed to click on the Notification Title -  Without large icon , without redirect URL and without Big Image", "FAIL");
		}

		return this;
	}
	
	//Transaction Notification :
	
	//Validate Pending Notifcation :
	public NotificationPage validatePendingNotification() {

		reportStep("About to validate the Pending Notification  ", "INFO");
		validateTheElementPresence(pendingNotification);
		return this;
	}
	
	//Validate Pending Notification Sub Text : 
	public NotificationPage validatePendingNotification_BigText() {

		reportStep("About to validate the Pending Notification Big Text ", "INFO");
		validateTheElementPresence(pendingNotification_BigText);
		return this;
	}
	
	//Validate Pending Notifcation :
	public NotificationPage clickPendingNotification() {

		reportStep("About to click on Pending Notification  ", "INFO");
		if(click(pendingNotification)) {
			
			reportStep("Successfully clicked on the Pending Notification , which redirected to the My Earnings page", "PASS");
		} else {
			reportStep("Failed to click on the Pending Notification , which redirected to the My Earnings page", "FAIL");
			
		}
		return this;
	}
	
	
	//Validate Cancelled Notification :

	public NotificationPage validateCancelledNotification() {

		reportStep("Validate the Cancelled Notifcation", "INFO");
		validateTheElementPresence(cancelledNotification);
		return this;

	}
	
	public NotificationPage clickCancelledNotificaiton() {

		reportStep("About to click on Cancel Notification", "INFO");
		
		if(click(cancelledNotification)) {
			
			reportStep("Successfully clicked on the cancell Notification ", "PASS");
		}else {
			
			reportStep("Failed to click on the Cancell Notification ", "FAIL");
		}
		return this;

	}

	public NotificationPage validateCancelledNotification_BigText() {

		reportStep("Validate the Cancelled Notifcation Big Text", "INFO");
		validateTheElementPresence(cancelledNotification_BigText);
		return this;

	}

	public NotificationPage validateConfirmedNotification() {

		reportStep("Validate the Confirmed Cashback Notification", "INFO");
		validateTheElementPresence(confirmedNotification);
		return this;

	}
	
	public NotificationPage validateConfirmedNotification_BigText() {

		reportStep("Validate the Confirmed Cashback Notification Big Text", "INFO");
		validateTheElementPresence(confirmedNotification_BigText);
		return this;

	}
	
	public NotificationPage clickOnConfirmedCashbackNotification() {
		
		reportStep("About to click on the Confirmed Notification Popup Which internally redirectes to the MyEarnings PopUp", "INFO");
		
		if(clickAfterWait(confirmedNotification)){
			
			reportStep("Successfully clicked on the Confirmed Notification - Which internally redirects to the MyEarnings Page", "PASS");
		} else {
			
			reportStep("Failed to click on the confirmed Notification - Which internally redirects to the My Earnings Page ", "FAIL");
		}
		
		return this;
	}

	public NotificationPage clickOnPaidNotification() {
		
		reportStep("About to click on the Paid Notification Popup Which internally redirectes to the MyEarnings PopUp", "INFO");
		
		if(clickAfterWait(cashOutNotification)){
			
			reportStep("Successfully clicked on the Paid Notification - Which internally redirects to the MyEarnings Page", "PASS");
		} else {
			
			reportStep("Failed to click on the Paid Notification - Which internally redirects to the My Earnings Page ", "FAIL");
		}
		
		return this;
	}

	public NotificationPage validatePaidNotification() {

		reportStep("Validate the Paid or CashOut Notification", "INFO");
		validateTheElementPresence(cashOutNotification);
		return this;

	}
	
	public NotificationPage validatePaidNotification_BigText() {

		reportStep("Validate the Paid or CashOut Notification BigText", "INFO");
		validateTheElementPresence(cashOutNotification_BigText);
		return this;

	}
	
	//Confirmend Referral Notification ; 
	public NotificationPage validateReferralConfirmedCashbackNotification() {

		reportStep("About to validate the Confirmed Referral Cashback ", "INFO");
		validateTheElementPresence(referralCbNotification);
		return this;

	}
	
	public NotificationPage validateReferralConfirmed_BigText() {

		reportStep("About to validate the Confirmed Referral Cashback Notification Big Text ", "INFO");
		validateTheElementPresence(referraltNotificationAfterExpand);
		return this;

	}
	
	public NotificationPage clickOnReferralCashbackNotification() {

		reportStep("About to Click on   Confirmed Referral Cashback Notification ...", "INFO");
		
		if(click(referralCbNotification)) {
			
			reportStep("Successfully Click on the Referral Cashback Notification  ", "PASS");
		} else {
			
			reportStep("Failed to click on the Referral Cashback Notification ", "FAIL");
		}
		
		return this;

	}
	
	
	
	
	
	
}
