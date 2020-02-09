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

public class HelpPage extends WrapperMethods  {
	
	//Constructor call
	public HelpPage(AppiumDriver<MobileElement> driver, ExtentTest testInfo) {

		WebDriverWait wait = new WebDriverWait(driver, 30);

		this.driver = driver;
		this.testInfo = testInfo;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);

		reportStep("Waiting for the HelpPage", "INFO");
		try {
			wait.until(ExpectedConditions.visibilityOf(helpHeader));
			reportStep("Successfully landed on the HelpPage", "PASS");

		}catch(Exception e) {
			
			reportStep(e.getMessage(), "INFO");
			reportStep("Not able to land on the HelpPage", "FAIL");
		}

	}
	
	//Elements :	
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='Email Us']") 
	MobileElement EmailUs;

	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='Call Us']") 
	MobileElement CallUs;

	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='FAQs']") 
	MobileElement faq;

	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='Help']") 
	MobileElement helpHeader;

	public AskAQuestionPage clickEmailUs() {

		reportStep("About to click on EmailUs", "INFO");

		if(click(EmailUs)) {

			reportStep("Successfully clicked on EmailUs", "PASS");
		}else {

			reportStep("Failed to  click on EmailUs", "FAIL");
		}

		return new AskAQuestionPage(driver, testInfo);
		
	}

	public void clickCallUs() {

		reportStep("About to click on CallUs", "INFO");

		if(click(CallUs)) {

			reportStep("Successfully clicked on the CallUs", "PASS");
		}else {

			reportStep("Failed to  click on the CallUs", "FAIL");
		}
		
	}

	public FAQPage clickFAQ() {

		reportStep("About to click on FAQ", "INFO");

		if(click(faq)) {

			reportStep("Successfully clicked on the FAQ", "PASS");
		}else {

			reportStep("Failed to  click on the FAQ", "FAIL");
		}

		return new FAQPage(driver, testInfo);
		
	}

	public SignedInProfilePage clickBackButton() {

		reportStep("About to click on back button in MyReferral", "INFO");

		backButton();

		return new SignedInProfilePage(driver, testInfo);
		
	}
}
