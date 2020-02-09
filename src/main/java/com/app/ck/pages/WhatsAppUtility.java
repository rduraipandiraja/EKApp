package com.app.ck.pages;

import java.util.List;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.app.ck.base.WrapperMethods;
import com.aventstack.extentreports.ExtentTest;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class WhatsAppUtility extends WrapperMethods {

	public WhatsAppUtility(AppiumDriver<MobileElement> driver, ExtentTest testInfo, String udid, String port,
			String automationName, String deviceName, Integer systemPort) {

		WebDriverWait wait = new WebDriverWait(driver, 30);

		this.driver = driver;
		this.testInfo = testInfo;
		this.udid = udid;
		this.port = port;
		this.automationName = automationName;
		this.deviceName = deviceName;
		this.systemPort = systemPort;

		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
		reportStep("What App class constructor Intialization ", "INFO");

	}

	// Earn Karo Whats app scenario :

	@FindBy(how = How.ID, using = "com.whatsapp:id/menuitem_search")
	MobileElement whatsAppSearchIcon;

	@FindBy(how = How.ID, using = "com.whatsapp:id/search_src_text")
	MobileElement whatsAppSearchBar;

	@FindBy(how = How.XPATH, using = "//android.widget.RelativeLayout")
	MobileElement requiredWhatsAppcontact;

	@FindBy(how = How.XPATH, using = "//android.widget.ImageButton[@content-desc=\"Send\"]")
	MobileElement send;

	@FindBy(how = How.XPATH, using = "//android.widget.ImageButton[@content-desc=\"Send\"]")
	MobileElement earnKaro;

	@FindBy(how = How.XPATH, using = "//android.view.View[contains(@content-desc,'https://ekaro.in/')]")
	List<MobileElement> lastSharedLink;
	
	@FindBy(how = How.XPATH, using = "//android.view.View[contains(@content-desc,'https://ekaro.in/')]")
	MobileElement lastSharedWhatsAppLink;

	@FindBy(how = How.XPATH, using = "//*[@text='EarnKaro']")
	MobileElement earnKaroIntermediatePage;

	@FindBy(how = How.ID, using = "com.whatsapp:id/caption")
	List<MobileElement> whatAppCaption;

	@FindBy(how = How.ID, using = "More options")
	MobileElement moreOption;

	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='More']")
	MobileElement more;

	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='Clear chat']")
	MobileElement clearChat;
	
	@FindBy(how = How.XPATH, using = "//*[contains(@text,'CLEAR')]")
	MobileElement clear;
	
	@FindBy(how = How.XPATH, using = "//*[contains(@text,'have earned')]")
	MobileElement referAndEarnText;
	
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@content-desc=\"btn_storeCardSearch_whatsapp_share\"]")
	MobileElement shareNow;

	// EarnKaro special :

	public WhatsAppUtility clickOnWhatAppMenuItemSearch() {

		reportStep("About to click on the Whats App menu item search ", "INFO");
		
		if (clickAfterWait(whatsAppSearchIcon)) {

			reportStep("Successfully clicked on the Whats App Search Icon ", "PASS");
		} else {

			reportStep("Failed to click on the Whats App Search Icon ", "FAIL");
		}

		return this;
	}

	public WhatsAppUtility enterWhatsAppSearchText(String contactName) {

		reportStep("About to Search the Contact from the Whats app search bar ", "PASS");

		if (enterText(whatsAppSearchBar, contactName)) {

			reportStep("About to Search the Whats app contact " + contactName, "PASS");
		} else {

			reportStep("Failed to Search the Contact " + contactName + " in Whats APP ", "FAIL");
		}

		return this;
	}

	public WhatsAppUtility clickOnRequiredContact() {

		reportStep("About to click on the required conatact from the whats app Contact menu ", "INFO");

		if (clickAfterWait(requiredWhatsAppcontact)) {

			reportStep("Successfully clicked on the Whats App contact ", "PASS");

		} else {

			reportStep("Failed to click  on the Required Whats App  contact - may be contact is nt thre ", "FAIL");
		}

		return this;
	}

	public WhatsAppUtility clickOnSend() {

		reportStep("About to click  on the Send in Whats APP ", "INFO");
		
		try {

			if (clickAfterWait(send)) {

				reportStep("Succesfully clicked on the Send button for the first time", "PASS");

			} else {

				reportStep("Failed to click on the Send button for the first time", "INFO");
			}
			
		} catch (Exception e) {

			if (clickAfterWait(send)) {

				reportStep("Succesfully clicked on the Send button for the second time", "PASS");

			} else {

				reportStep("Failed to click on the Send button for the second time", "FAIL");
			}
		}

		return this;

	}

	public WhatsAppUtility clickOnLastSharedLink() {

		reportStep("About to click on the last shared link - EarnKaro Shared Products", "INFO");
		
		hideKeyboard_All_APILevel();

		if (clickAfterWait(lastSharedLink.get(lastSharedLink.size() - 1))) {

			reportStep("Successfully clicked on the Shared link - on Whats app shared ", "PASS");

		} else {

			reportStep("Failed ot click on the Shared link  -  on Whats app shared ", "FAIL");
		}

		return this;
	}

	public WhatsAppUtility validateIntermediatePage() {

		reportStep("About to validate the Earn Karo Redirection Intermediate Page ", "INFO");

		validateTheElementPresence(earnKaroIntermediatePage);

		return this;

	}

	public WhatsAppUtility validateRetailerPage() {

		reportStep("About to validate the Earn Karo Redirection Intermediate Page ", "INFO");

		validateTheElementPresence(earnKaroIntermediatePage);

		return this;

	}

	public String getWhatsUpSharedCaption() {

		reportStep("About to get the fetch the Whats App Caption ", "INFO");
		
		hideKeyboard_All_APILevel();

		String whatsappCaptionOne = getText(whatAppCaption.get(whatAppCaption.size() - 1));

		return whatsappCaptionOne;
	}
	
	public WhatsAppUtility validateFullDescription(String fullDescription) {

		reportStep("About to validate the full description "+fullDescription+"", "INFO");
		
		hideKeyboard_All_APILevel();

		String fullDescriptionXpath = "//android.widget.TextView[contains(@text,'"+fullDescription+"')]";
		
		if(isElementLocatedByXpathPresent(fullDescriptionXpath)){
			reportStep("Verified the presence of full description: "+fullDescription+"", "INFO");

		}else {
			reportStep("Not able to verify the presence of full description: "+fullDescription+"", "FAIL");

		}

		return this;
	}
	
	public WhatsAppUtility validateShortenURL(String shortenURL) {

		reportStep("About to validate the shortenURL "+shortenURL+"", "INFO");
		
		hideKeyboard_All_APILevel();

		String shortenURLXpath = "//android.widget.TextView[contains(@text,'"+shortenURL+"')]";
		
		if(isElementLocatedByXpathPresent(shortenURLXpath)){
			reportStep("Verified the presence of shortenURL: "+shortenURL+"", "INFO");

		}else {
			reportStep("Not able to verify the presence of shortenURL: "+shortenURL+"", "FAIL");

		}

		return this;
	}

	public WhatsAppUtility clearWhatsAppChat() {

		reportStep("About to clear whatsapp chat", "INFO");

		click(moreOption);
		click(more);
		click(clearChat);
		click(clear);

		reportStep("Sucessfully cleared whatsapp chat", "PASS");

		return this;

		// Need to implement once after discussion
	}

	public void validateReferAndEarnSharedTextInWhatsApp() {
		
		reportStep("About to validate the Whats App Share details for Refer and Earn ", "INFO");
		
		String referEarnSharedText = getText(referAndEarnText);
		String referEarnSharedText_Two = "Just share amazing deals & earn maximum profits! https://earnkaro.onelink.me/";
		
		reportStep("Refer and earn text is " +  referEarnSharedText, "INFO");
		
		if (referEarnSharedText.contains(getTestData(18, "ReferAndEarnWhatsAppShareText")) && 
				referEarnSharedText.contains(referEarnSharedText_Two)) {
			
			reportStep("Successfully  Validated the Message shared for Refer and Earn Life Time - Whats App", "PASS");
		}
		else {
			
			reportStep("Failed to Validate the Message shared for Refer and Earn Life Time - Whats App", "FAIL");
		}
		
	}
	
	public void validateWhatsAppSharingLink(String actual, String expected) {
		
		reportStep("About to validate the WhatApp Sharing link is Correct or Not ", "Info");
		
		if(actual.trim().equals(expected)) {
			
			reportStep("Successfully validated Actual and Expected ", "Pass");
			
		} else {
			
			reportStep("Failed to validate Actual and Expected - SomeThing Went Wrong while sharing via WatsApp ", "Fail");
		}
		
	}
	
	
	
}
