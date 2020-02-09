package com.app.ck.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import com.app.ck.base.MakeLinkURLS;
import com.app.ck.base.WrapperMethods;
import com.app.ck.pages.admin.AdminCoreFunction;
import com.app.ck.utilities.CashKaroUtility;
import com.aventstack.extentreports.ExtentTest;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class PartnersDetailPage extends WrapperMethods {

	//Constructor call
	public PartnersDetailPage(AppiumDriver<MobileElement> driver, ExtentTest testInfo) {

		WebDriverWait wait = new WebDriverWait(driver, 30);

		this.driver = driver;
		this.testInfo = testInfo;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
		
		reportStep("Waiting for the partnersDetailPage", "INFO");
		try {

			wait.until(ExpectedConditions.visibilityOf(profitRates));
			reportStep("Successfully landed on the partnersDetailPage", "PASS");

		}catch(Exception e) {

			reportStep(e.getMessage(), "INFO");
			reportStep("Not able to land on the partnersDetailPage", "FAIL");
		}

	}

	//Xpath  :
	@FindBy(how = How.XPATH, using = "//android.widget.ImageView[@content-desc='img_profitPartnersDetails_logo']")
	MobileElement partnersPageImage;

	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='PROFIT RATES']")
	MobileElement profitRates;

	public PartnersDetailPage validatePartnerImage() {

		reportStep("About to validate the PartnerImage", "INFO");

		validateTheElementPresence(partnersPageImage);

		reportStep("Successfully validated the PartnerImage", "INFO");

		return this;

	}

	public PartnersDetailPage validatePartnerNameInHeader(String partnerName) {

		reportStep("About to validate the PartnerName In Header: "+partnerName+"", "INFO");

		String partnerNameXpath = "(//android.widget.TextView[@text='"+partnerName+"'])[1]";

		MobileElement partnername = driver.findElement(By.xpath(partnerNameXpath));

		validateTheElementPresence(partnername);

		reportStep("Successfully validated the PartnerName In Header: "+partnerName+"", "INFO");

		return this;

	}

	public PartnersDetailPage validatePartnerName(String partnerName) {

		reportStep("About to validate the Partner Name "+partnerName+"", "INFO");

		String partnerNameXpath = "(//android.widget.TextView[@text='"+partnerName+"'])[2]";

		MobileElement partnername = driver.findElement(By.xpath(partnerNameXpath));

		validateTheElementPresence(partnername);

		reportStep("Successfully validated the Partner Name: "+partnerName+"", "INFO");

		return this;

	}

	public PartnersDetailPage validatePartnerCashbackValuePresence(String cashbackValue) {

		reportStep("About to validate the cashback value: "+cashbackValue+"", "INFO");

		String cashbackValueXpath = "//android.widget.TextView[contains(@text,'"+cashbackValue+"')]";

		MobileElement cashbackvalue = driver.findElement(By.xpath(cashbackValueXpath));

		validateTheElementPresence(cashbackvalue);

		reportStep("Successfully validated the cashback value: "+cashbackValue+"", "INFO");

		return this;

	}

	public PartnersDetailPage validatePartnerCashbackDetailsPresence(String cashbackDetails) {

		reportStep("About to validate the cashback details: "+cashbackDetails+"", "INFO");

		String cashbackDetailsXpath = "//android.widget.TextView[@text='"+cashbackDetails+"']";

		MobileElement cashbackdetails = driver.findElement(By.xpath(cashbackDetailsXpath));

		validateTheElementPresence(cashbackdetails);

		reportStep("Successfully validated the cashback details: "+cashbackDetails+"", "INFO");

		return this;

	}

	public PartnersDetailPage validatePartnerCashbackValueAbsence(String cashbackValue) {

		reportStep("About to validate the cashback value: "+cashbackValue+"", "INFO");

		String cashbackValueXpath = "//android.widget.TextView[contains(@text,'"+cashbackValue+"')]";

		validateTheElementAbsence(cashbackValueXpath);

		reportStep("Successfully validated the cashback value: "+cashbackValue+"", "INFO");

		return this;

	}

	public PartnersDetailPage validatePartnerCashbackDetailsAbsence(String cashbackDetails) {

		reportStep("About to validate the cashback details: "+cashbackDetails+"", "INFO");

		String cashbackDetailsXpath = "//android.widget.TextView[@text='"+cashbackDetails+"']";

		validateTheElementAbsence(cashbackDetailsXpath);

		reportStep("Successfully validated the cashback details: "+cashbackDetails+"", "INFO");

		return this;

	}

	
}
