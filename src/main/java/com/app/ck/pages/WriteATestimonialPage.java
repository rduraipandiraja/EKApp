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

public class WriteATestimonialPage extends WrapperMethods {
	
	//Constructor call
	public WriteATestimonialPage(AppiumDriver<MobileElement> driver, ExtentTest testInfo) {

		WebDriverWait wait = new WebDriverWait(driver, 30);

		this.driver = driver;
		this.testInfo = testInfo;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);

		reportStep("Waiting for the Write a Testimonials  page ", "INFO");
		try {
			wait.until(ExpectedConditions.visibilityOf(WriteAtestimonial));
			reportStep("Successfully landed on the Write a Testimonials Page", "PASS");

		}catch(Exception e) {

			reportStep(e.getMessage(), "INFO");
			reportStep("Not able to land on the Write a Testimonials Page  ", "FAIL");
		}

	}
	
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text  = 'WRITE A TESTIMONIAL']")
	MobileElement WriteAtestimonial ;
	@FindBy(how = How.XPATH, using = "//android.widget.EditText[@content-desc='et_testimonialsWrite_msg']")
	MobileElement feedbackTextArea ;
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='SUBMIT']")
	MobileElement submit ;
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='How was your experience with us?']")
	MobileElement andCountingText ;
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='	Rate your experience']")
	MobileElement seeWhatOurHappyUsersSaying ;
	
	@FindBy(how = How.XPATH, using = "(//android.widget.TextView)[5]")
	MobileElement firstStar ;
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[contains(@text,'Terrible')]")
	MobileElement firstStarTitle ;
	@FindBy(how = How.XPATH, using = "(//android.widget.TextView)[6]")
	MobileElement secondStar ;
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[contains(@text,'Not Great')]")
	MobileElement secondStarTitle ;
	@FindBy(how = How.XPATH, using = "(//android.widget.TextView)[7]")
	MobileElement thirdStar ;
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[contains(@text,'Average')]")
	MobileElement thirdStarTitle ;
	@FindBy(how = How.XPATH, using = "(//android.widget.TextView)[8]")
	MobileElement fourthStar ;
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[contains(@text,'Good Experience')]")
	MobileElement fourthStarTitle ;
	@FindBy(how = How.XPATH, using = "(//android.widget.TextView)[9]")
	MobileElement fifthStar ;
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[contains(@text,'Awesome Experience')]")
	MobileElement fifthStarTitle ;
	

	
	//Methods :

	public WriteATestimonialPage enterfeedBack(String testimonial) {

		reportStep("About to write a testimonial as  " +  testimonial, "INFO");

		if (enterText(feedbackTextArea, testimonial)) {

			reportStep("Successfully entered the testimonial as : " + testimonial, "PASS");

		}else {

			reportStep("Failed to enter the testimonial as " + testimonial, "FAIL");
		}
		
		return this;

	}

	public TestimonialsPage clickOnSubmitButton() {

		reportStep("About to click on the Submit button at the Write a Testimonial page ", "INFO");

		if (click(submit)) {

			reportStep("Successfully clicked on the testimonial - submit button ", "PASS");
		}else {

			reportStep("Failed to click on the Testimonial - submit Button ", "FAIL");
		}
		
		return new TestimonialsPage(driver, testInfo);
	}

	public WriteATestimonialPage clickStarOne() {

		reportStep("About to click on the One star ", "INFO");

		if (click(firstStar)) {

			reportStep("Successfully clicked on the First star ", "PASS");

		}else {

			reportStep("Failed - Not able to click on the First star ", "FAIL");

		}
		
		validateTheElementPresence(firstStarTitle);
		
		return this;
		
	}

	public WriteATestimonialPage clickStarTwo() {

		reportStep("About to click on the Two stars ", "INFO");

		if (click(secondStar)) {

			reportStep("Successfully clicked on the Second stars", "PASS");

		}else {

			reportStep("Failed - click on the Second stars", "FAIL");

		}
		
		validateTheElementPresence(secondStarTitle);
		
		return this;
	}

	public WriteATestimonialPage clickStarThree() {

		reportStep("About to click on the Three stars ", "INFO");

		if (click(thirdStar)) {

			reportStep("Successfully clicked on the Three stars", "PASS");

		}else {

			reportStep("Failed - click on the Three stars", "FAIL");

		}
		
		validateTheElementPresence(thirdStarTitle);
		
		return this;
	}

	public WriteATestimonialPage clickStarFour() {

		reportStep("About to click on the Four stars ", "INFO");

		if (click(fourthStar)) {

			reportStep("Successfully clicked on the Four stars", "PASS");

		}else {

			reportStep("Failed - click on the Four stars", "FAIL");

		}
		
		validateTheElementPresence(fourthStarTitle);
		
		return this;
	}

	public WriteATestimonialPage clickStarFive() {

		reportStep("About to click on the Five stars ", "INFO");

		if (click(fifthStar)) {

			reportStep("Successfully clicked on the Five stars", "PASS");

		}else {

			reportStep("Failed - click on the Five stars", "FAIL");

		}
		validateTheElementPresence(fifthStarTitle);
		
		return this;
	}
	
	
	
	
	
	
	
	
	




}
