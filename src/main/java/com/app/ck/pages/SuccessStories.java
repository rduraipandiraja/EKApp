package com.app.ck.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.app.ck.base.WrapperMethods;
import com.app.ck.utilities.CashKaroUtility;
import com.aventstack.extentreports.ExtentTest;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class SuccessStories extends WrapperMethods {

	//Constructor call
	public SuccessStories(AppiumDriver<MobileElement> driver, ExtentTest testInfo) {

		WebDriverWait wait = new WebDriverWait(driver, 30);

		this.driver = driver;
		this.testInfo = testInfo;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);

		reportStep("Waiting for the SuccessStoriesPage", "INFO");
		try {
			
			wait.until(ExpectedConditions.visibilityOf(successStoriesPage));
			reportStep("Successfully landed on the SuccessStoriesPage", "PASS");

		}catch(Exception e) {

			reportStep(e.getMessage(), "INFO");
			reportStep("Not able to land on the SuccessStoriesPage", "FAIL");
		}

	}
	
	//Common
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='Success Stories']")
	MobileElement successStoriesPage;

}
