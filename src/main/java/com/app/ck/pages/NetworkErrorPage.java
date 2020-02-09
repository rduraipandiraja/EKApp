package com.app.ck.pages;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.app.ck.base.WrapperMethods;
import com.aventstack.extentreports.ExtentTest;
import com.google.common.base.Equivalence.Wrapper;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class NetworkErrorPage extends WrapperMethods {
	

	//Constructor call
	public NetworkErrorPage(AppiumDriver<MobileElement> driver, ExtentTest testInfo) {

		WebDriverWait wait = new WebDriverWait(driver, 30);

		this.driver = driver;
		this.testInfo = testInfo;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);

		reportStep("Waiting for No network Error Page ", "INFO");
		try {

			wait.until(ExpectedConditions.visibilityOf(noNetworkConnection));
			reportStep("Successfully landed on the No network Error Page Since , Wifi is Turned Off", "PASS");

		}catch(Exception e) {

			reportStep(e.getMessage(), "INFO");
			reportStep("Not able to land on the No netowork Error Page", "FAIL");
		}

	}


	//Network Error Messages
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text=\"Whoops!\"]")
	MobileElement whoopsError;
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text=\"No Network Connection!\"]")
	MobileElement noNetworkConnection;
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text=\"Check your connection or try again.\"]")
	MobileElement checkYourConnection;
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text=\"REFRESH\"]")
	MobileElement refreshButton;
	@FindBy(how = How.XPATH, using = "//android.widget.ImageView[@content-desc=\"img_noConnection_err\"]")
	MobileElement imgNoConnectionErr;
	
	//validate the Network Page errors
	public void validateNetworkPageErrors() {
		
		reportStep("About to validate the Nework Page Errors ",  "Info" );
		
		try {
			
		validateTheElementPresence(whoopsError);
		validateTheElementPresence(noNetworkConnection);
		validateTheElementPresence(checkYourConnection);
		validateTheElementPresence(refreshButton);
		validateTheElementPresence(imgNoConnectionErr);
		
		reportStep(" Successfully validated the Network Errors  ",  "PASS" );
		
		}catch (Exception e) {
			
			reportStep("Failed to validate the Network Error Page " +  e.getMessage(), "Fail");
		}
		
	}
	
	public void toggleWifiFromOffToON() {
		
		reportStep("About to Toggle the Wifi From OFF to ON ", "info");
			
		try {
			toggleWifi();
			reportStep("Successfully Toggled the Wifi from Off to ON ", "Pass");
		} catch (Exception e) {
		
			reportStep("Failed to Toggle the Wifi From Off to On", "Fail");
		}
		
		
		
	}
	

}
