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

public class HistoryPage extends WrapperMethods {

	//Constructor call
	public HistoryPage(AppiumDriver<MobileElement> driver, ExtentTest testInfo) {

		WebDriverWait wait = new WebDriverWait(driver, 30);

		this.driver = driver;
		this.testInfo = testInfo;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);

		reportStep("Waiting for the historyPage", "INFO");
		try {

			wait.until(ExpectedConditions.visibilityOf(history));
			reportStep("Successfully landed on the historyPage", "PASS");

		}catch(Exception e) {

			reportStep(e.getMessage(), "INFO");
			reportStep("Not able to land on the historyPage", "FAIL");
		}

	}

	//Common
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='History']")
	MobileElement history;

	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='Deal Sharing Activity Report']")
	MobileElement dealSharingActivity;

	String dealSharingActivityXpath = "//android.widget.TextView[@text='Sharing Report']";

	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='Whoops!']/parent::android.view.ViewGroup/android.widget.TextView[@text='You do not have any history from EarnKaro yet.']/parent::android.view.ViewGroup/android.widget.TextView[@text='Saving are just one click away.']")
	MobileElement historyBodyText ;

	@FindBy(how = How.XPATH, using = "//android.widget.ImageView")
	MobileElement historyImage ;

	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='Profile']")
	MobileElement profileIcon;
	
	
	public HistoryPage validateEntireBlock(String exitID, String date, String retailerName, String numberOfClicks, String numberOfTransaction, String totalEarnings) {

		reportStep("About to validate entire block having exitID: "+exitID+", date: "+date+", retailerName: "+retailerName+", numberOfClicks: "+numberOfClicks+", numberOfTransaction: "+numberOfTransaction+" & totalEarnings "+totalEarnings ,"INFO");

		scrollTillRequiredElementIsVisibleFromUpToDown(dealSharingActivityXpath);

		String entireBlockXpath = "//android.widget.TextView[@text='Shared ID']/parent::android.view.ViewGroup/android.view.ViewGroup/android.widget.TextView[@text='"+exitID+"']/parent::android.view.ViewGroup/parent::android.view.ViewGroup/android.widget.TextView[@text='Shared Date & Time']/parent::android.view.ViewGroup/android.widget.TextView[contains(@text,'"+date+"')]/parent::android.view.ViewGroup/android.widget.TextView[@text='Retailer Name']/parent::android.view.ViewGroup/android.widget.TextView[@text='"+retailerName+"']/parent::android.view.ViewGroup/android.widget.TextView[@text='No Of Clicks']/parent::android.view.ViewGroup/android.widget.TextView[@text='"+numberOfClicks+"']/parent::android.view.ViewGroup/android.widget.TextView[@text='No Of Transactions']/parent::android.view.ViewGroup/android.widget.TextView[@text='"+numberOfTransaction+"']/parent::android.view.ViewGroup/android.widget.TextView[@text='Total Earnings']/parent::android.view.ViewGroup/android.widget.TextView[contains(@text,'"+totalEarnings+"')]";

		scrollTillRequiredElementIsVisibleFromDownToUp(entireBlockXpath);

		if(isElementLocatedByXpathPresent(entireBlockXpath)){
			reportStep("Verified the presence of entireBlock exitID: "+exitID+", date: "+date+", retailerName: "+retailerName+", numberOfClicks: "+numberOfClicks+", numberOfTransaction: "+numberOfTransaction+" & totalEarnings "+totalEarnings, "INFO");

		}else {
			reportStep("Not able to verify the presence of entireBlock exitID: "+exitID+", date: "+date+", retailerName: "+retailerName+", numberOfClicks: "+numberOfClicks+", numberOfTransaction: "+numberOfTransaction+" & totalEarnings "+totalEarnings, "FAIL");

		}

		return this;

	}

	public HistoryPage clickMonthYearDropdown(String date, String anotherDate) {

		reportStep("About to click the month year dropdown "+date+" in respective tab ", "INFO");

		String dateValueXpath = "//android.widget.TextView[@text='Shared Link Details']/parent::android.view.ViewGroup/android.view.ViewGroup/android.widget.EditText[@text='"+date+"']|//android.widget.TextView[@text='Shared Link Details']/parent::android.view.View/android.view.View/android.widget.EditText[@text='"+date+"']";

		if(clickByXpath(dateValueXpath)) {
			reportStep("Clicked the month year dropdown "+date+" in respective tab ", "PASS");

		}else {
			reportStep("Not able to click the month year dropdown "+date+" in respective tab ", "FAIL");

		}

		clickDateAfterClickingMonthYearDropdown(anotherDate);

		return this;


	}

	public HistoryPage clickDateAfterClickingMonthYearDropdown(String monthYear) {

		reportStep("About to click on date in respective tab", "INFO");

		String dropDownValueXpath = "//android.widget.CheckedTextView[@text='"+monthYear+"']";

		if(clickByXpath(dropDownValueXpath)) {

			reportStep("Click on date in respective tab", "PASS");

		}else {
			reportStep("Not able to click on date in respective tab", "FAIL");

		}

		return this;

	}

	public HistoryPage validateMonthYear(String monthYear) {

		reportStep("About to verify the monthYear "+monthYear+" in historyPage", "INFO");

		String monthYearXpath = "//android.widget.TextView[@text='Shared Link Details']/parent::android.view.ViewGroup/android.view.ViewGroup/android.widget.EditText[@text='"+monthYear+"']";

		if(isElementLocatedByXpathPresent(monthYearXpath)) {

			reportStep("Verify the monthYear "+monthYear+" in historyPage", "PASS");

		}else {
			reportStep("Not able to verify the monthYear "+monthYear+" in historyPage", "FAIL");

		}

		return this;


	}

	public HistoryPage validateObjectsHistoryPage() {

		reportStep("About to validate the objects in HistoryPage", "INFO");

		validateTheElementPresence(historyImage);
		validateTheElementPresence(historyBodyText);

		return this;

	}

	public HomePage clickBackButton() {

		reportStep("About to click on back button in RecentClicksPage", "INFO");

		driver.navigate().back();

		return new HomePage(driver, testInfo);

	}

	public HistoryPage clickViewMore() {

		reportStep("About to click on ViewMore", "INFO");

		//String viewMore = "//android.widget.TextView[contains(@text,'View') and contains(@text,'more')]";
		String viewMore = "//*[contains(@text,'View more')]|//android.widget.TextView[@content-desc='txt_earningsReferral_viewMore']|//android.widget.TextView[@content-desc='txt_earningsCashback_viewMore']|//android.widget.TextView[@content-desc='txt_myShareActivites_viewMore']";

		scrollTillRequiredElementIsVisibleFromDownToUp(viewMore);

		if(clickByXpath(viewMore)) {

			reportStep("clicked on ViewMore", "PASS");

		}else {
			reportStep("Not able to click on ViewMore", "FAIL");

		}
		return this;

	}

	public String clickCopy(String exitID) {

		reportStep("About to click on copy", "INFO");

		String copy = "//android.widget.TextView[@text='"+exitID+"']/parent::android.view.ViewGroup/parent::android.view.ViewGroup/android.widget.TextView[@content-desc='txt_myShareActivites_shared_copy']";

		scrollTillRequiredElementIsVisibleFromDownToUp(copy);

		if(clickByXpath(copy)) {
			reportStep("clicked on copy in historyPage", "PASS");

		}else {
			reportStep("Not able to click on copy in historyPage", "FAIL");

		}

		String link = getClipBoardText();

		return link;

	}

	public SignedInProfilePage clickOnProfileIconForSignedUser() {

		reportStep("About to click on the Profile icon for  Signed user ", "INFO");

		if(click(profileIcon)) {

			reportStep("Successfully clicked on the Profile icon at the Home page for  Signed user ", "PASS");
		} else {

			reportStep("Failed to  click on the Profile icon at the Home page for  Signed user", "FAIL");
		}

		return new SignedInProfilePage(driver, testInfo);
	}

	public HistoryPage validateNetworkErrorsInHistoryPage() {

		reportStep("About to TurnOff the Wifi", "Info");
		//Wifi - ON to OFF
		toggleWifi();

		new NetworkErrorPage(driver, testInfo).
		validateNetworkPageErrors();

		//Wifi - OFF to ON
		toggleWifi();
		new HistoryPage(driver, testInfo);
		reportStep("Successfully Turned On the Wifi ", "Pass");
		return this;

	}

}