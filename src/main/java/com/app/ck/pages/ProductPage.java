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

public class ProductPage extends WrapperMethods {

	//Constructor call
	public ProductPage(AppiumDriver<MobileElement> driver, ExtentTest testInfo) {

		WebDriverWait wait = new WebDriverWait(driver, 30);

		this.driver = driver;
		this.testInfo = testInfo;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);

		reportStep("Waiting for the product page", "INFO");

		try {
			wait.until(ExpectedConditions.visibilityOf(grabDealButton));
			reportStep("Successfully landed on the product page ", "PASS");

		}catch(Exception e) {

			reportStep(e.getMessage(), "INFO");
			reportStep("Not able to land on the product page", "FAIL");
		}

	}

	//Variable initialization
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text= 'GRAB DEAL']") 
	MobileElement grabDealButton;

	//Method creation:
	public SignInPage clickOnGrabDeal() {

		reportStep("Going to click on the GrabDeal button at the product page", "INFO");

		if(click(grabDealButton)) {

			reportStep("Sucessfully clicked on the GrabDeal button ", "PASS");

		}else {

			reportStep("Failed to  click on the GrabDeal button at the product page ", "FAIL");
		}

		return new SignInPage(driver, testInfo);
	}



}
