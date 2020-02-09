package com.app.ck.pages;

import static io.appium.java_client.touch.offset.PointOption.point;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.app.ck.base.WrapperMethods;
import com.app.ck.utilities.Utilities;
import com.aventstack.extentreports.ExtentTest;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.MultiTouchAction;
import io.appium.java_client.TouchAction;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.touch.WaitOptions;

public class IntermediateRetailerPage extends WrapperMethods {

	//Constructor call
	public IntermediateRetailerPage(AppiumDriver<MobileElement> driver, ExtentTest testInfo) {

		WebDriverWait wait = new WebDriverWait(driver, 30);

		this.driver = driver;
		this.testInfo = testInfo;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);

		try {
			wait.until(ExpectedConditions.visibilityOf(retailerRediretionalURL));
			reportStep("Successfully landed on the Retailer page", "PASS");

		}catch(Exception e) {

			reportStep(e.getMessage(), "INFO");
			reportStep("Not able to land on Retailer page", "FAIL");

		}
	}


	//Element Identification
	@FindBy(how = How.XPATH, using = "//android.widget.EditText[contains(@text,'myntra.com')]|//android.widget.EditText[contains(@text,'amazon.in')]") //ID =com.android.chrome:id/url_bar
	MobileElement retailerRediretionalURL;
	@FindBy(how = How.XPATH, using = "//android.widget.Button[@text='ALLOW']|//android.widget.Button[@text='Allow']")
	MobileElement redirectionalPageAllowPopup;
	@FindBy(how = How.ID, using = "com.android.chrome:id/close_button")
	MobileElement retilerPageBackButton;


	public IntermediateRetailerPage validateRetailerPage(String expected, String retailerName) {


		String retailerRedirectionalURLXpath = "//android.widget.TextView[contains(@text,'"+retailerName+"')]|//android.widget.EditText[contains(@text,'"+retailerName+"')]";

		if(isElementLocatedByXpathPresent(retailerRedirectionalURLXpath)) {

			reportStep("Verified URL "+retailerName+"", "PASS");

		}else {
			reportStep("Not able to verify URL "+retailerName+"", "FAIL");

		}

		try {

			reportStep("About to close the retailer Popup,Specially Myntra retailer, If it comes ", "INFO");

			WebDriverWait wait = new WebDriverWait(driver, 40);
			wait.until(ExpectedConditions.visibilityOf(redirectionalPageAllowPopup));

			if(redirectionalPageAllowPopup.isDisplayed()){

				redirectionalPageAllowPopup.click();

				reportStep("Permisson notification allow popup has been clicked and handled","INFO");
			}

		}catch(Exception e) {

			reportStep("Even after waiting for 40 seconds the Permisson notification popup has not","INFO");

		}
		//
		//		MobileElement retailerRediretionalURL = driver.findElement(By.xpath(retailerRedirectionalURLXpath));
		//
		//		WebDriverWait wait = new WebDriverWait(driver, 30);
		//		wait.until(ExpectedConditions.visibilityOf(retailerRediretionalURL));
		//		
		//		String actual = getText(retailerRediretionalURL);
		//
		//		reportStep("About to assert expected URL: "+expected+" with the actual URL: "+actual+"","INFO");
		//		
		//		validateTheActualValueContainsExpectedValue(actual, expected);
		//
		//		reportStep("Successfully asserted expected URL: "+expected+" with the actual URL: "+actual+"","PASS");

		return this;

	}

	public String getUrlFromDefaultBrowser(String retailerName) {


		String retailerRedirectionalURLXpath = "//android.widget.TextView[contains(@text,'"+retailerName+"')]|//android.widget.EditText[contains(@text,'"+retailerName+"')]";

		if(isElementLocatedByXpathPresent(retailerRedirectionalURLXpath)) {

			reportStep("Verified URL "+retailerName+"", "PASS");

		}else {
			reportStep("Not able to verify URL "+retailerName+"", "FAIL");

		}

		if(clickByXpath(retailerRedirectionalURLXpath)) {

			reportStep("Successfully clicked retailer URL", "PASS");

		}else {
			reportStep("Not able to click retailer URL", "FAIL");

		}

		MobileElement retailerRediretionalURL = driver.findElement(By.xpath(retailerRedirectionalURLXpath));

		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOf(retailerRediretionalURL));

		String actual = getText(retailerRediretionalURL);

		reportStep("Successfully retrieved the URL in default browser: "+actual+"", "PASS");

		return actual;

	}

	public IntermediateRetailerPage validateRetailerPageDontHandlePopup(String expected, String retailerName) {


		MobileElement retailerRediretionalURL = driver.findElement(By.xpath("//android.widget.TextView[contains(@text,'"+retailerName+"')]"));

		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOf(retailerRediretionalURL));

		String actual = getText(retailerRediretionalURL);
		validateTheActualValueContainsExpectedValue(actual, expected);


		return this;

	}

	public StorePage clickOnIntermediateBackbuttoon() {

		if(clickAfterWait(retilerPageBackButton)){

			reportStep("Successfully clicked on the back button from the Retailer page", "PASS");
		}else {

			reportStep("Fail to click on the back button at the retialer page ", "FAIL");
		}

		return new StorePage(driver, testInfo);
	}

}