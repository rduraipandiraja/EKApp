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

public class FAQPage extends WrapperMethods {

	//Constructor call to initialize the driver object
	public FAQPage(AppiumDriver<MobileElement> driver, ExtentTest testInfo) {

		WebDriverWait wait = new WebDriverWait(driver, 30);

		this.driver = driver;
		this.testInfo = testInfo;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);

		reportStep("Waiting for the FAQPage", "INFO");
		try {
			wait.until(ExpectedConditions.visibilityOf(FAQHeader));

			reportStep("Successfully landed on the FAQPage", "PASS");

		}catch(Exception e) {

			reportStep(e.getMessage(), "INFO");
			reportStep("Not able to land on the FAQPage", "FAIL");
		}

	}
	
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='FAQs']")
	MobileElement FAQHeader;

	public String HowcanIstartearning = "To start earning click on the \"Get Started\" button on the EarnKaro Home page. Enter your email id, mobile number & set a password to login in. Once you're logged in, you will see more than 100,000+ share worthy deals. Pick a deal you like based on the product, your profit per sale, discount or price and share this link (your profit link) with anyone who would be interested in buying it. You can share as many Profit Links as you want with people on (No Suggestions), Facebook, Instagram or any other social platform. Whenever someone shops through your \"Profit Link\" you earn \"Profit\" which is \"Real Cash\". This cash is added to your EarnKaro Account & you can transfer it directly to your bank account in just a few clicks!";
	
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='ASK A QUESTION']")
	MobileElement askAQuestion ;

	public SignInPage clickOnBackButton() {

		reportStep("About click on the back button in FAQPage", "INFO");
		
		driver.navigate().back();

		return new SignInPage(driver, testInfo);
	}

	public AskAQuestionPage clickAskAQuestionButton() {

		reportStep("About to click askAQuestion in FAQPage", "INFO");
		
		if(click(askAQuestion)) {
			
			reportStep("Sucessfully clicked on the askAQuestion in FAQPage ", "PASS");
			
		}else {
			
			reportStep("Not able to click on the askAQuestion in FAQPage ", "FAIL");
		}

		return new AskAQuestionPage(driver, testInfo);
		
	
		
	}

}
