package com.app.ck.pages;

import static io.appium.java_client.touch.offset.PointOption.point;

import java.time.Duration;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.app.ck.base.WrapperMethods;
import com.aventstack.extentreports.ExtentTest;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.MultiTouchAction;
import io.appium.java_client.TouchAction;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.touch.WaitOptions;

public class ProductCategoryPage extends WrapperMethods {

	// Method creation
	public ProductCategoryPage(AppiumDriver<MobileElement> driver, ExtentTest testInfo) {

		WebDriverWait wait = new WebDriverWait(driver, 30);

		this.driver = driver;
		this.testInfo = testInfo;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);

		reportStep("Waiting for the Product Category page", "INFO");
		try {

			// scrollTillRequiredElementIsVisibleFromUpToDown(headerProductCategoryPage);

			wait.until(ExpectedConditions.visibilityOf(filter));
			reportStep("Successfully landed on the Product Category page", "PASS");

		} catch (Exception e) {

			reportStep(e.getMessage(), "INFO");
			reportStep("Not able to land on the Product Category page", "FAIL");
		}

	}

	// Variable initialization
	@FindBy(how = How.XPATH, using = "//android.view.ViewGroup[@content-desc='btn_productTop_filters']/android.widget.TextView|//android.view.ViewGroup[@content-desc='btn_productCategory_filters']/android.widget.TextView")
	MobileElement filter;

	public HomePage clickBackButton() {

		reportStep("About to click on back button in ProductCategoryPage", "INFO");
		
		driver.navigate().back();

		reportStep("Successfully redirected to home page", "INFO");

		return new HomePage(driver, testInfo);
		
	}

}
