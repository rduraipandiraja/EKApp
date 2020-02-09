package com.app.ck.pages;

import java.util.List;

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

public class PrivacyPolicy extends WrapperMethods {
	

	//Constructor call
	public PrivacyPolicy(AppiumDriver<MobileElement> driver, ExtentTest testInfo) {

		WebDriverWait wait = new WebDriverWait(driver, 30);

		this.driver = driver;
		this.testInfo = testInfo;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);

		reportStep("About to verify Privacy Policy Page ", "INFO");
		try {
			
			wait.until(ExpectedConditions.visibilityOf(privacyPolicy));
			reportStep("Successfully landed on the Privacy Policy", "PASS");

		}catch(Exception e) {

			reportStep(e.getMessage(), "INFO");
			reportStep("Not able to land on the Privacy Policy ", "FAIL");
		}

	}
	
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text  = 'Privacy Policy']")
	MobileElement privacyPolicy ;
	
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text  = 'Privacy Policy']")
	List<MobileElement> listOfPrivacyPolicy ;
	
	public void validateCountOfPrivacyPolicy() {
		
		reportStep("About to validate the Privacy Policy  Text ", "INFO");
		
		if(listOfPrivacyPolicy.size()==2) {
			
			reportStep("Successfully validated the Privacy Policy is Equal to 2 ", "Pass");
		} else {
			
			reportStep("Failed - Count of Privacy Policy Text is not Equal to 2 ", "Fail");
		}
	}
	

}
