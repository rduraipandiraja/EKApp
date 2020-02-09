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

public class PartnersPage extends WrapperMethods {

	//Constructor call
	public PartnersPage(AppiumDriver<MobileElement> driver, ExtentTest testInfo) {

		WebDriverWait wait = new WebDriverWait(driver, 30);

		this.driver = driver;
		this.testInfo = testInfo;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);

		reportStep("Waiting for the partners page ", "INFO");
		try {

			wait.until(ExpectedConditions.visibilityOf(partnersPageTitle));
			reportStep("Successfully landed on the partners  page ", "PASS");

		}catch(Exception e) {

			reportStep(e.getMessage(), "INFO");
			reportStep("Not able to land on the partners Page  ", "FAIL");
		}

	}

	//Xpath  :
	@FindBy(how = How.XPATH, using = "(//android.widget.TextView[@text  = 'Partners'])[1]")
	MobileElement partnersPageTitle ;

	public PartnersDetailPage clickOnPartnerLink(String partnerName) {

		reportStep("About to click on the PartnerLink", "INFO");

		String partnerNameXpath = "//android.widget.TextView[@text='"+partnerName+"']";
		
		scrollTillRequiredElementIsVisibleFromDownToUp(partnerNameXpath);	

		MobileElement partnername = driver.findElement(By.xpath(partnerNameXpath));

		if(clickAfterWait(partnername)) {

			reportStep("Successfully clicked on the partnername: "+partnerName+"", "PASS");
		}else {

			reportStep("Failed to click on the partnername: "+partnerName+"", "FAIL");
		}

		return new PartnersDetailPage(driver, testInfo);

	}

	public PartnersDetailPage validatePartnerLinkPresence(String partnerName) {

		reportStep("About to validatePartnerLinkPresence", "INFO");

		String partnerNameXpath = "//android.widget.TextView[@text='"+partnerName+"']";
		
		scrollTillRequiredElementIsVisibleFromDownToUp(partnerNameXpath);	

		MobileElement partnername = driver.findElement(By.xpath(partnerNameXpath));

		validateTheElementPresence(partnername);

		return new PartnersDetailPage(driver, testInfo);

	}

	public PartnersDetailPage validatePartnerLinkAbsence(String partnerName) {

		reportStep("About to validatePartnerLinkAbsence", "INFO");

		String partnerNameXpath = "//android.widget.TextView[@text='"+partnerName+"']";
		
		//scrollTillRequiredElementIsVisibleFromDownToUp(partnerNameXpath);	

		validateTheElementAbsence(partnerNameXpath);

		return new PartnersDetailPage(driver, testInfo);

	}

	public PartnersPage validatePartnerLinkAbsence_partnersPage(String partnerName) {

		reportStep("About to validatePartnerLinkAbsence", "INFO");

		String partnerNameXpath = "//android.widget.TextView[@text='"+partnerName+"']";
		
		//scrollTillRequiredElementIsVisibleFromDownToUp(partnerNameXpath);	

		validateTheElementAbsence(partnerNameXpath);

		return this;

	}

	public PartnersPage validateNameInPartnerLink(String partnerNameExpected, int index) {

		reportStep("About to validatePartnerLinkAlphabetOrder", "INFO");

		String partnerNameXpath = "(//android.widget.TextView[@content-desc='txt_success_stories_title']/parent::android.view.ViewGroup/parent::android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.widget.TextView)["+index+"]";
		
		scrollTillRequiredElementIsVisibleFromUpToDown(partnerNameXpath);	

		MobileElement partnername = driver.findElement(By.xpath(partnerNameXpath));
		
		String partnerNameActual = getText(partnername);

		validateTheActualValueWithExpectedValue(partnerNameActual, partnerNameExpected);

		return this;

	}

}
