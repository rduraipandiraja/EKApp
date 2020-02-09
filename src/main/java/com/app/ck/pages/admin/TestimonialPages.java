package com.app.ck.pages.admin;

import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.app.ck.base.WrapperMethods;
import com.aventstack.extentreports.ExtentTest;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class TestimonialPages extends WrapperMethods {

	public TestimonialPages(AppiumDriver<MobileElement> driver, ExtentTest testInfo) {
		
		this.driver = driver;
		this.testInfo = testInfo;
		 PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	private String searchByDropDwonXpath = "//select[@id='sby']";
	private String statusDropDwonXpath = "//select[@id='status']";
	
	
	
	@FindBy(how = How.XPATH, using = "//input[@id='tsearch']") 
	MobileElement keywordField;
	
	@FindBy(how = How.XPATH, using = "//button[@id='btn_Submit']") 
	MobileElement searchButton;
	
	@FindBy(how = How.XPATH, using = "//input[@id='title']") 
	MobileElement titleField;
	
	@FindBy(how = How.XPATH, using = "//button[@id='btn_Submit']") 
	MobileElement submitTestimonial;
	
	@FindBy(how = How.XPATH, using = "//div[@id='adminMessageSuccess']") 
	MobileElement adminSuccessMessage;
	
	
	
	
	
	public void selectUserEmailInSearchByDropDown() {
		
		reportStep("About to select the User Email by Search By drop down in Testimonial page ", "INFO");
		
		select_Dropdown(driver, searchByDropDwonXpath, "User Email");
		
		reportStep("Successfully selected the User Email from the Search by drop down "  , "PASS");
		
	}

	public void enterKeyWordAsUserEmail(String userEmail) {
		
		reportStep("About to enter the User email as Keyword  .. ", "INFO");
		
		if (enterTextInChrome(keywordField, userEmail)) {
			
			reportStep("Successfully entered the value as Keyword : "+ userEmail , "PASS");
			
		}else {
			
			reportStep("Failed to enter the value as keyword  :"+ userEmail, "FAIL");
		}
		
	}
	
	public void clickOnSearchButton() {
		
		reportStep("About to click on the Search button  in the Testimonials Page ", "INFO");
		
		if(clickAfterWait(searchButton)) {
			
			reportStep("Sucessfully Clicked on the Search button -", "PASS");
		}else {
			
			reportStep("Failed to click on the Search Button at the Testimonial page - admin ", "FAIL");
		}
		
	}
	
	public void clickOnEditButtonBasedOnStarts(String userEmail,String ratings) {
		
		reportStep("About to click on the edit button based on the star rating to give approval - The user Email is : "+userEmail + "  Ratings given : " +  ratings, "INFO");
		
		userEmail = userEmail.substring(0, userEmail.indexOf("@"));
		
		String xpath = "//td[contains(text(),'"+userEmail+"')]/following-sibling::td[text()='"+ratings+"']/following-sibling::td/a[1]";
		
		isElementLocatedByXpathPresent(xpath);
		
		if(click(driver.findElement(By.xpath(xpath)))){
			
			reportStep("Successfully clicked on the edit button for the User : "+ userEmail + " Ratings : " + ratings, "PASS");
		}else {
			
			reportStep("Failed to click on the edit button for the User : "+ userEmail + " Ratings : " + ratings, "FAIL");
		}
		
	}

	public void changeStatus(String status) {
		
		//'In-Active'  or 'Active' Should be the Status
		
		reportStep("About to Change the Status  From In Active to Active   ", "INFO");
		
		select_Dropdown(driver, statusDropDwonXpath, status);
		
		reportStep("Successfully Changed the Status from In Active to Active ", "PASS");
		
	}
	
	public void enterTitle(String value){
		
		reportStep("About to enter the Title in Testimonial page  ", "INFO");
		
		if(enterTextInChrome(titleField, value)) {
			
			reportStep("Successfully entered the Title as : "+ value, "PASS");
		}else {
			
			reportStep("Failed to enter the Title as : "+ value, "FAIL");
		}
		
		
	}

	public void clickOnSaveTestimonial() {
		
		reportStep("About to click on the Save Testimonial button  ", "INFO");
		
		if(click(submitTestimonial)){
			
			reportStep("Successfully clicked on the Save Testimonial button - Admin ", "PASS");
			
		} else {
			
			reportStep("Failed -  Not able to click on the Save Testimonial button ", "FAIL");
		}
		
		validateTheElementPresence(adminSuccessMessage);
	}

	
	
}
