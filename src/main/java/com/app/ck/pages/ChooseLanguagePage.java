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

public class ChooseLanguagePage extends WrapperMethods {

	//Constructor call to initialize the driver object
	public ChooseLanguagePage(AppiumDriver<MobileElement> driver, ExtentTest testInfo) {

		WebDriverWait wait = new WebDriverWait(driver, 30);

		this.driver = driver;
		this.testInfo = testInfo;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);

		reportStep("Waiting for the ChooseLanguagePage", "INFO");
		try {
			wait.until(ExpectedConditions.visibilityOf(buttonContinue));

			reportStep("Successfully landed on the ChooseLanguagePage", "PASS");

		}catch(Exception e) {

			reportStep(e.getMessage(), "INFO");
			reportStep("Not able to land on the ChooseLanguagePage", "FAIL");
		}

	}
	
	//Constructor call to initialize the driver object
	public ChooseLanguagePage(AppiumDriver<MobileElement> driver, ExtentTest testInfo,boolean status) {

		WebDriverWait wait = new WebDriverWait(driver, 30);

		this.driver = driver;
		this.testInfo = testInfo;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);

	}
	
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@content-desc='btn_language_change']")
	MobileElement buttonContinue;

	@FindBy(how = How.ID, using = "android:id/button2")
	MobileElement buttonCancel;

	@FindBy(how = How.ID, using = "android:id/button1")
	MobileElement buttonRelaunch;

	@FindBy(how = How.XPATH, using = "//android.widget.TextView[contains(@text,'English')]")
	MobileElement linkEnglish;

	@FindBy(how = How.XPATH, using = "//android.widget.TextView[contains(@text,'Tamil')]")
	MobileElement linkTamil;

	@FindBy(how = How.XPATH, using = "//android.widget.TextView[contains(@text,'Hindi')]")
	MobileElement linkHindi;

	@FindBy(how = How.XPATH, using = "//android.widget.TextView[contains(@text,'Telugu')]")
	MobileElement linkTelugu;

	@FindBy(how = How.XPATH, using = "//android.widget.TextView[contains(@text,'Malayalam')]")
	MobileElement linkMalayalam;

	@FindBy(how = How.XPATH, using = "//android.widget.TextView[contains(@text,'Kannada')]")
	MobileElement linkKannada;

	@FindBy(how = How.XPATH, using = "//android.widget.TextView[contains(@text,'Bengali')]")
	MobileElement linkBengali;

	@FindBy(how = How.XPATH, using = "//android.widget.TextView[contains(@text,'Marathi')]")
	MobileElement linkMarathi;

	@FindBy(how = How.XPATH, using = "//android.widget.TextView[contains(@text,'Gujarati')]")
	MobileElement linkGujarati;

	public SignInPage clickOnBackButton() {

		reportStep("About click on the back button in FAQPage", "INFO");
		
		driver.navigate().back();

		return new SignInPage(driver, testInfo);
	}

	public ChooseLanguagePage clickEnglishLink() {

		reportStep("About to click EnglishLink in ChooseLanguagePage", "INFO");
		
		if(click(linkEnglish)) {
			
			reportStep("Sucessfully clicked on the EnglishLink in ChooseLanguagePage ", "PASS");
			
		}else {
			
			reportStep("Not able to click on the EnglishLink in ChooseLanguagePage ", "FAIL");
		}

		return this;
				
	}

	public ChooseLanguagePage clickContinueButton() {

		reportStep("About to click ContinueButton in ChooseLanguagePage", "INFO");
		
		if(click(buttonContinue)) {
			
			reportStep("Sucessfully clicked on the ContinueButton in ChooseLanguagePage ", "PASS");
			
		}else {
			
			reportStep("Not able to click on the ContinueButton in ChooseLanguagePage ", "FAIL");
		}

		return this;
				
	}

	public ChooseLanguagePage clickCancelButton() {

		reportStep("About to click CancelButton in ChooseLanguagePage", "INFO");
		
		if(click(buttonCancel)) {
			
			reportStep("Sucessfully clicked on the CancelButton in ChooseLanguagePage ", "PASS");
			
		}else {
			
			reportStep("Not able to click on the CancelButton in ChooseLanguagePage ", "FAIL");
		}

		return this;
				
	}

	public HomePage clickRelaunchButton() {

		reportStep("About to click RelaunchButton in ChooseLanguagePage", "INFO");
		
		if(click(buttonRelaunch)) {
			
			reportStep("Sucessfully clicked on the RelaunchButton in ChooseLanguagePage ", "PASS");
			
		}else {
			
			reportStep("Not able to click on the RelaunchButton in ChooseLanguagePage ", "FAIL");
		}

		return new HomePage(driver, testInfo);
				
	}

	public ChooseLanguagePage clickTamilLink() {

		reportStep("About to click TamilLink in ChooseLanguagePage", "INFO");
		
		if(click(linkTamil)) {
			
			reportStep("Sucessfully clicked on the TamilLink in ChooseLanguagePage ", "PASS");
			
		}else {
			
			reportStep("Not able to click on the TamilLink in ChooseLanguagePage ", "FAIL");
		}
		
		return this;
				
	}

	public ChooseLanguagePage clickHindiLink() {

		reportStep("About to click HindiLink in ChooseLanguagePage", "INFO");
		
		if(click(linkHindi)) {
			
			reportStep("Sucessfully clicked on the HindiLink in ChooseLanguagePage ", "PASS");
			
		}else {
			
			reportStep("Not able to click on the HindiLink in ChooseLanguagePage ", "FAIL");
		}

		return this;
				
	}

	public ChooseLanguagePage clickTeluguLink() {

		reportStep("About to click TeluguLink in ChooseLanguagePage", "INFO");
		
		if(click(linkTelugu)) {
			
			reportStep("Sucessfully clicked on the TeluguLink in ChooseLanguagePage ", "PASS");
			
		}else {
			
			reportStep("Not able to click on the TeluguLink in ChooseLanguagePage ", "FAIL");
		}

		return this;
				
	}

	public ChooseLanguagePage clickMalayalamLink() {

		reportStep("About to click MalayalamLink in ChooseLanguagePage", "INFO");
		
		if(click(linkMalayalam)) {
			
			reportStep("Sucessfully clicked on the MalayalamLink in ChooseLanguagePage ", "PASS");
			
		}else {
			
			reportStep("Not able to click on the MalayalamLink in ChooseLanguagePage ", "FAIL");
		}

		return this;
				
	}

	public ChooseLanguagePage clickKannadaLink() {

		reportStep("About to click KannadaLink in ChooseLanguagePage", "INFO");
		
		if(click(linkKannada)) {
			
			reportStep("Sucessfully clicked on the KannadaLink in ChooseLanguagePage ", "PASS");
			
		}else {
			
			reportStep("Not able to click on the KannadaLink in ChooseLanguagePage ", "FAIL");
		}

		return this;
				
	}

	public ChooseLanguagePage clickBengaliLink() {

		reportStep("About to click BengaliLink in ChooseLanguagePage", "INFO");
		
		if(click(linkBengali)) {
			
			reportStep("Sucessfully clicked on the BengaliLink in ChooseLanguagePage ", "PASS");
			
		}else {
			
			reportStep("Not able to click on the BengaliLink in ChooseLanguagePage ", "FAIL");
		}

		return this;
				
	}

	public ChooseLanguagePage clickMarathiLink() {

		reportStep("About to click MarathiLink in ChooseLanguagePage", "INFO");
		
		if(click(linkMarathi)) {
			
			reportStep("Sucessfully clicked on the MarathiLink in ChooseLanguagePage ", "PASS");
			
		}else {
			
			reportStep("Not able to click on the MarathiLink in ChooseLanguagePage ", "FAIL");
		}

		return this;
				
	}

	public ChooseLanguagePage clickGujaratiLink() {

		reportStep("About to click GujaratiLink in ChooseLanguagePage", "INFO");
		
		if(click(linkGujarati)) {
			
			reportStep("Sucessfully clicked on the GujaratiLink in ChooseLanguagePage ", "PASS");
			
		}else {
			
			reportStep("Not able to click on the GujaratiLink in ChooseLanguagePage ", "FAIL");
		}

		return this;
				
	}

	public boolean clickByXpath(String xPath) {
		
		reportStep("About to click on the Elements Xpath "+ xPath, "Info");
		try {
			WebDriverWait wait = new WebDriverWait(driver, 30);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xPath)));
			driver.findElementByXPath(xPath).click(); 
			return true;
		} catch (Exception e) {
			reportStep("Failed  to click on the Elements Xpath "+ xPath, "Fail");
			e.printStackTrace();
		}
		return false;
	}

}