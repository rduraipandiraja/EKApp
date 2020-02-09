package com.app.ck.pages;

import org.openqa.selenium.WebElement;
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

public class TermsAndConditionPage extends WrapperMethods {
	
	
	//Constructor call
		public TermsAndConditionPage(AppiumDriver<MobileElement> driver, ExtentTest testInfo) {

			WebDriverWait wait = new WebDriverWait(driver, 30);

			this.driver = driver;
			this.testInfo = testInfo;
			PageFactory.initElements(new AppiumFieldDecorator(driver), this);

			reportStep("Waiting for the Terms And Condition page ", "INFO");
			try {
				
				wait.until(ExpectedConditions.visibilityOf(termsAndCondition));
				wait.until(ExpectedConditions.visibilityOf(termsAndConditionHeadings));
				reportStep("Successfully landed on the Terms And Condition  page ", "PASS");

			}catch(Exception e) {

				reportStep(e.getMessage(), "INFO");
				reportStep("Not able to land on theTerms And Condition Page  ", "FAIL");
			}

		}

	
		@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='Terms and Conditions']")
		WebElement termsAndCondition ;
		@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='Terms & Conditions for EarnKaro.com']")
		WebElement termsAndConditionHeadings ;
	

}
