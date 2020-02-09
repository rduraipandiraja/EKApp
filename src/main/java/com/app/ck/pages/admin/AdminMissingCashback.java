package com.app.ck.pages.admin;

import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.app.ck.base.WrapperMethods;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.ReportStatusStats;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class AdminMissingCashback extends WrapperMethods {
	
	
	public AdminMissingCashback(AppiumDriver<MobileElement> driver, ExtentTest testInfo) {
			
			this.driver = driver;
			this.testInfo = testInfo;
			 PageFactory.initElements(new AppiumFieldDecorator(driver), this);
		}



	@FindBy(how = How.XPATH, using = "//select[@id='sby']") 
	MobileElement searchByDropDown;
	
	@FindBy(how = How.XPATH, using = "//button[@id='btn_Submit']") 
	MobileElement searchButton;
	
	@FindBy(how = How.XPATH, using = "//textarea[@id='reply']") 
	MobileElement amdinReplyTextField;
	
	@FindBy(how = How.XPATH, using = "//button[@id='btn_Submit']") 
	MobileElement submitButton;
	
	@FindBy(how = How.XPATH, using = "//input[@id='key']") 
	MobileElement keyword;
	
	@FindBy(how = How.ID, using = "status") 
	MobileElement ticketStatusDropDown;

	
	

	public void selectSearchbyExitClickIDFromDropDown() {

		reportStep("About to select the Search by drop down from the Missing Cashback  page ", "INFO");

		selectValuesFromDropdown(searchByDropDown, "Exit ID");

	}
	

	public void selectTicketStatus(String visibleTextForTicketStatus) {

		reportStep("About to select the Search by drop down from the Missing Cashback  page ", "INFO");

		selectValuesFromDropdown(ticketStatusDropDown, visibleTextForTicketStatus);

	}
	
	public void enerKeyWordatAdminMissingCashbackPage(String exitClickID) {

		reportStep("About to enter the keyword at the Admin missing casbhack page  ", "INFO");

		if (enterTextInChrome(keyword, exitClickID)) {
			
			reportStep("Successfully entered the Exit click id as keyword ", "PASS");
			
		}else {
			reportStep("Failed to entered the Exit click id as keyword ", "FAIL");
			
		}
		

	}

	public void clickOnSearchButton() {

		reportStep("About to click on the Search button at the Missing Cashback ticket admin page", "INFO");

		if (clickAfterWait(searchButton)) {

			reportStep("Successfully clicked on the Search button from the Missing Cashback Page ", "PASS");
		}else {

			reportStep("Failed to click on the Search button from the Missing Cashback Page ", "FAIL");
		}

	}

	public void clickOnEditButton(String strExitClick_Value) {

		reportStep("About to fetch the Ticket ID from the Missing cashback ticket page at admin ", "INFO");

		String xpath = "(//td[@title='"+strExitClick_Value+"']/following::td/a[@class='fa fa-edit'])[1]" ;

		try {
			WebDriverWait wait = new WebDriverWait(driver, 30);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
		}catch (Exception e) {

			reportStep("Excetption occured during the locating the xpath (//td[@title='\"+strExitClick_Value+\"']/following::td/a[@class='fa fa-edit'])[1] to find the Ticket id", "PASS");
		}

		MobileElement ele = driver.findElementByXPath(xpath);

		if (clickAfterWait(ele)) {
			
			reportStep("Successfully clicked on the Edit button to write the Ticket admin reply - Admin Missing casbhak page", "PASS");
			
		}else {
			
			reportStep("FAIL to  click on the Edit button to write the Ticket admin reply - Admin Missing casbhak page", "FAIL");
		}
	


	}
	
	public String fetchTicketID(String strExitClick_Value) {

		reportStep("About to fetch the Ticket ID from the Missing cashback ticket page at admin ", "INFO");

		String xpath = "//td[@title='" + strExitClick_Value + "']/../td[2]" ;

		try {
			WebDriverWait wait = new WebDriverWait(driver, 30);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
		}catch (Exception e) {

			reportStep("Excetption occured during the locating the xpath //td[@title=' + strExitClick_Value + ']/../td[2] to find the Ticket id", "PASS");
		}

		MobileElement ele = driver.findElementByXPath(xpath);

		return getText(ele);


	}

	public void enterAdminReply(String adminReply) {

		reportStep("About to enter the Admin Reply at the Missing Cashback page  ", "INFO");

		if(enterTextInChrome(amdinReplyTextField, adminReply)) {

			reportStep("Successfully entered the admin reply as : "+ adminReply, "PASS");
		}else {

			reportStep("Failed entered the admin reply as : "+ adminReply, "FAIL");
		}


	}

	public void clickOnSubmitButton() {

		reportStep("About to click on the Submit button at the Missing Cashback ticket admin page", "INFO");

		if (clickAfterWait(submitButton)) {

			reportStep("Successfully clicked on the Submit button from the Missing Cashback Page ", "PASS");
		}else {

			reportStep("Failed to click on the Submit button from the Missing Cashback Page ", "FAIL");
		}

	}


}
