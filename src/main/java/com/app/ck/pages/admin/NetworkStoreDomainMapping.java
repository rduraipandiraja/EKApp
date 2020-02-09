package com.app.ck.pages.admin;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.app.ck.base.WrapperMethods;
import com.aventstack.extentreports.ExtentTest;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class NetworkStoreDomainMapping extends WrapperMethods {

	/*
	 *  Author : Mallikarjunaswamy 
	 *  Title  : NetworkDomain setting in admin - Make link end point settings
	 * 	
	 * 
	 * 
	 * 
	 * 
*/
	/*
	 * Follwing are the shorten URL domains - go through the official sites for more info
	 * 
		bitly.com =https://bitly.com/
		tiny.cc = https://tiny.cc/
		bit.do = https://bit.do/
		rebrand.ly/d93d5 = https://app.rebrandly.com/links
		http://j.mp/2VvtbN0 = https://smallseotools.com/url-shortener/
		goo.gl = Not supported
		t2m.io = https://t2m.io
		tinyurl.com = https://tinyurl.com 
		https://bit.ly/2KjvdPh
*/	
	
	
	//Constructor creation :
	public NetworkStoreDomainMapping(AppiumDriver<MobileElement> driver, ExtentTest testInfo) {

		this.driver = driver;
		this.testInfo = testInfo;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	//Element creation:

	@FindBy(how = How.XPATH, using = "(//span[contains(text(),'Network Store Domain Mapping')])[1]") 
	MobileElement networkStoreDomain;

	@FindBy(how = How.ID, using = "btn_Tools") 
	MobileElement toolButton;
	
	@FindBy(how = How.XPATH, using = "//a[text()='Update Shorten URL Domains']") 
	MobileElement updateShortenURL;
	
	@FindBy(how = How.ID, using = "shorten_url_domains") 
	MobileElement updateShortenURLTextArea;
	
	@FindBy(how = How.XPATH, using = "//button[@id='btn_Submit'][text()='Submit']") 
	MobileElement submitButton;
	
	@FindBy(how = How.XPATH, using = "//input[@id='domainurl']") 
	MobileElement domainURLField;

	@FindBy(how = How.XPATH, using = "//button[@id='btn_Submit'][text()='Search']") 
	MobileElement searchButton;
	
	@FindBy(how = How.XPATH, using = "//td[text()='AppiumMakeLinkStore']") 
	MobileElement searchResultsStoreName;
	
	@FindBy(how = How.XPATH, using = "//span[text()='Please try different search criteria.']") 
	MobileElement searchResultsErrorMessage;
	
	
	//Method creations :
	
	public void clickOnNetworkDomainMapping() {
		
		reportStep("About to click submenu stores in admin homepage", "INFO");

		if(jsClickUsingID("SubMenu_NetworkStoreDomainMapping")) {
			
			reportStep("Successfully clicked on the Network Store Domain Mapping  ", "PASS");
			
		}else {
			reportStep("Failed to click on the Network Store Domain Mapping ", "FAIL");
		}

		
	}

	public void clickOnToolsButton() {
		
		reportStep("About to click on the Tools button to select Shorten URL Domain ", "INFO");
		
		if(jsClickUsingID("btn_Tools")){
			
			reportStep("Successfuly clicked on the Tools button ", "PASS");
			
		}else {
			
			reportStep("Failed to click on the Tools button ", "FAIL");
		}
		
	}

	public void clickOnUpdateShortenURLDomain() {
		
		reportStep("About to click on the Update Shorten URL Domain", "INFO");
		
		if(jsClickUsingXpath(updateShortenURL)){
			
			reportStep("Successfully clicked on the Update Shorten URL Domains", "PASS");
		}else {
			
			reportStep("Failed to click on the Update Shorten URL Domains", "FAIL");
		}
	}
	
	public void enterShortenURL() {
		
		reportStep("About to enter the Shorten URLs to the Domain URL text area", "INFO");
		
		if (enterTextInChrome(updateShortenURLTextArea, getTestData(14, "shortenDomainURLs_8"))) {
			
			reportStep("Successfully entered or Updates the shorten URLS as " +  getTestData(14, "shortenDomainURLs_8"), "PASS");
			
		}else {
			
			reportStep("Failed to enter or Update the shorten URLS as " +  getTestData(14, "shortenDomainURLs_8"), "Fail");
		}
		
	}
	
	public void enterNegativeShortURL() {
		
		reportStep("About to enter the Shorten URLs to the Domain URL text area", "INFO");
		
		if (enterTextInChrome(updateShortenURLTextArea, getTestData(14, "shortenDummyURLs_11"))) {
			
			reportStep("Successfully entered or Updates the shorten URLS as " +  getTestData(14, "shortenDummyURLs_11"), "PASS");
			
		}else {
			
			reportStep("Failed to enter or Update the shorten URLS as " +  getTestData(14, "shortenDummyURLs_11"), "Fail");
		}
		
	}

	public void clickOnSubmit() {
		
		reportStep("Successfully clicked on Submit button ", "INFO");
		
		if(clickAfterWait(submitButton)) {
			
			reportStep("Successfully clicked on Submit button ", "PASS");
			
		}else {
			
			reportStep("Failed to click on Submit buttno  ", "FAIL");
		}
	}

	public void validateShortenURLSuccessMessage() {
		
		reportStep("About validate the Success message once after Config the Shorten URLS", "INFO");
		
		isElementLocatedByXpathPresent("//div[text()='Shorten URL domains has been successfully updated.']");
		
		reportStep("Successfully config the Shorten URLS", "INFO");
		
	}
	
	public void enterDomainURLForSearch(String domainURL) {
		
		reportStep("About to enter the Domain URL "+  domainURL +  " to Search  Network Store Domain Mapping", "INFO");
		
		if(enterTextInChrome(domainURLField, domainURL)){
			
			reportStep("Successfully entered the Domain URL as : " + domainURL, "PASS");
		}else {
			
			reportStep("Failed to enter the Domain URL as : " + domainURL, "FAIL");
		}
		
		
	}

	public void clickOnSearchButton() {
		
		reportStep("About to click on Search Button in Network Store Domain Mapping  ", "INFO");
		
		if(clickAfterWait(searchButton)) {
			
			reportStep("Successfully clicked on the Search Buttoon In the Network Store Domain Mapping ", "PASS");
		}else {
			
			reportStep("Failed to click on the Search Buttoon In the Network Store Domain Mapping ", "FAIL");
		}
	}

	public void verifySearchDomainIsAvailable() {

		reportStep("About to vlaidate the Searched Domain is Available or Not  ", "INFO");

		if(validateTheElementPresence(searchResultsStoreName)) {

			reportStep("Success - Requied Domain : appium.io - Which is required test data is available ", "PASS");

		}else {
			
			if(validateTheElementPresence(searchResultsErrorMessage)) {
				
				reportStep("Fail - Requied Domain : appium.io - Which is required test data is not available - Might be Test Data issue"
						+ " Plz, Set the set data Properly , before you run this test case ", "FAIL");
			}else {
				
				reportStep("Fail - Check in the admin manually  ", "FAIL");
				
			}

			
		}
		
	}

	

}
