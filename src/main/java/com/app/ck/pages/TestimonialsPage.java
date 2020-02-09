package com.app.ck.pages;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

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

public class TestimonialsPage extends WrapperMethods {
	
	//Constructor call
	public TestimonialsPage(AppiumDriver<MobileElement> driver, ExtentTest testInfo) {

		WebDriverWait wait = new WebDriverWait(driver, 30);

		this.driver = driver;
		this.testInfo = testInfo;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);

		reportStep("Waiting for the Testimonial  page ", "INFO");
		try {
			wait.until(ExpectedConditions.visibilityOf(testimonials));
			reportStep("Successfully landed on the Testimonial page ", "PASS");

		}catch(Exception e) {

			reportStep(e.getMessage(), "INFO");
			reportStep("Not able to land on the Testimonial Page  ", "FAIL");
		}

	}
	
	
	//Elements : - 
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text  = 'Testimonials']")
	MobileElement testimonials ;
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='+ WRITE A TESTIMONIAL']")
	MobileElement writeTestimonial ;
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='and Counting!']")
	MobileElement andCountingText ;
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='See what our happy users are saying!']")
	MobileElement seeWhatOurHappyUsersSaying ;
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@content-desc=\"txt_testimonials_totalValue\"]")
	MobileElement testimonialsCount ;
	@FindBy(how = How.XPATH, using = "(//android.widget.TextView[@content-desc=\"txt_testimonials_postedByAndDate\"])[1]")
	MobileElement testimonialPostedData ;
	
	@FindBy(how = How.XPATH, using = "//*[@text='APPIUM GIVES 5 STARS']")
	MobileElement adminTestimonialTitle_FiveStar ;
	@FindBy(how = How.XPATH, using = "//*[@text='APPIUM GIVES 4 STARS']")
	MobileElement adminTestimonialTitle_FourStar ;
	@FindBy(how = How.XPATH, using = "//*[@text='APPIUM GIVES 3 STARS']")
	MobileElement adminTestimonialTitle_ThreeStar ;
	@FindBy(how = How.XPATH, using = "//*[@text='APPIUM GIVES 2 STARS']")
	MobileElement adminTestimonialTitle_TwoStar ;
	@FindBy(how = How.XPATH, using = "//*[@text='APPIUM GIVES 1 STARS']")
	MobileElement adminTestimonialTitle_OneStar ;
	
	@FindBy(how = How.XPATH, using = "//*[@text='FIVE STAR FEEDBACK']")
	MobileElement userTestimonialDesc_FiveStar ;
	@FindBy(how = How.XPATH, using = "//*[@text='FOUR STAR FEEDBACK']")
	MobileElement userTestimonialDesc_FourStar ;
	@FindBy(how = How.XPATH, using = "//*[@text='THREE STAR FEEDBACK']")
	MobileElement userTestimonialDesc_ThreeStar ;
	@FindBy(how = How.XPATH, using = "//*[@text='TWO STAR FEEDBACK']")
	MobileElement userTestimonialDesc_TwoStar ;
	@FindBy(how = How.XPATH, using = "//*[@text='ONE STAR FEEDBACK']")
	MobileElement userTestimonialDesc_OneStar ;
	
	
	//Methods :
	public WriteATestimonialPage clickOnWriteTestimonialPage() {

		reportStep("About to click on the Write TestiMonial page ", "INFO");

		if (click(writeTestimonial)) {

			reportStep("Successfully clicked on the Write testimonial button ", "PASS");

		}else {

			reportStep("Failed - to click on the Write Testimonial button", "FAIL");
		}

		return new WriteATestimonialPage(driver, testInfo);
	}

	public String getTestimonialsCount() {

		reportStep("About to get the Testimonial count in the Testimonial page ", "INFO");

		String testimonialCount = getText(testimonialsCount);

		reportStep("Testimonial Counts before - enabling the testimonial to active is  " + testimonialCount, "PASS");

		return testimonialCount;

	}

	public TestimonialsPage validateTestimonialPostedDate(String userFullName) {

		//Testimonial Date formate :

		SimpleDateFormat dateFormatGmt = new SimpleDateFormat("MMMM dd,yyyy");
		dateFormatGmt.setTimeZone(TimeZone.getTimeZone("IST"));
		Calendar objCalender  		= Calendar.getInstance();
		Date currentDate   			= objCalender.getTime();
		String strNumber_Days_To_BackDated_Exit_Clicks  = dateFormatGmt.format(currentDate);

		String strTestimonialPostedDate = userFullName+","+strNumber_Days_To_BackDated_Exit_Clicks;

		System.out.println(strTestimonialPostedDate);

		reportStep("Testimonial Posted date is and its info : " + strTestimonialPostedDate , "INFO" );

		String postedDate = getText(testimonialPostedData);

		if(postedDate.replaceAll("\\s", "").contains(strTestimonialPostedDate.replaceAll("\\s", ""))){

			reportStep("Successfully validated the Testimonial posted date ", "PASS");

		}else {

			reportStep("Failed to  validate the Testimonial posted date : Expected  :"+ strTestimonialPostedDate + "  but Actual  : "+ postedDate , "FAIL");
		}

		return this;


	}

	public TestimonialsPage validateTestimonialCountIncreased(String intialCount) {

		reportStep("When the Testimonials are made active from the admin - Testimonial count should be get increased ", "INFO");

		String strCount = getTestimonialsCount();

		if(Integer.parseInt(strCount) == Integer.parseInt(intialCount) +5) {

			reportStep("When the Testimonial made active from the Admin - Testimonial count had been increased in the testimonial page - Intial count is "
					+ intialCount + " After making active 5 testimonial Active ,The increased count is : "+ strCount, "PASS");

		}else {

			reportStep("Failed - When the Testimonial made active from the Admin - Testimonial count had not been increased in the testimonial page - Intial count is "
					+ intialCount + " After making active 5 testimonial active ,The increased count is : "+ strCount, "FAIL");

		}

		return this;
	}

	public TestimonialsPage validateTestimonialCount_Decreased(String intialCount) {

		reportStep("When the Testimonials are made In-Active from the admin - Testimonial count should be get Decresed ", "INFO");

		String strCount = getTestimonialsCount();

		if(Integer.parseInt(strCount) == Integer.parseInt(intialCount)) {

			reportStep("When the Testimonial made InActive from the Admin - Testimonial count had been decreased in the testimonial page - Intial count is "
					+ (intialCount+5) + " After making  5 testimonial Active ,The Decreased count is : "+ intialCount, "PASS");

		}else {

			reportStep("Failed - When the Testimonial made In-Active from the Admin - Testimonial count had not been decreased in the testimonial page - Intial count is "
					+ (intialCount+5) + " After making  5 testimonial In-active ,The Decreased count is : "+ intialCount, "FAIL");

		}

		return this;
	}

	public TestimonialsPage validateAdminTestimonialTitle_UserFeedBack_PostedTimings(String userFullName) {
		
		reportStep("About to validate the Admin testimonial Title , user Testimonial Feed back & then testimonial posted timings ", "INFO");
		
		validateTheElementPresence(adminTestimonialTitle_FiveStar);
		validateTheElementPresence(userTestimonialDesc_FiveStar);
		validateTestimonialPostedDate(userFullName);
		
		validateTheElementPresence(adminTestimonialTitle_FourStar);
		validateTheElementPresence(userTestimonialDesc_FourStar);
		
		scrollFromDownToUpinApp_FourSecDuration();
		
		validateTheElementPresence(adminTestimonialTitle_ThreeStar);
		validateTheElementPresence(userTestimonialDesc_ThreeStar);
		
		validateTheElementPresence(adminTestimonialTitle_TwoStar);
		validateTheElementPresence(userTestimonialDesc_TwoStar);
		
		scrollFromDownToUpinApp_FourSecDuration();
		scrollFromDownToUpinApp_FourSecDuration();
		
		validateTheElementPresence(adminTestimonialTitle_OneStar);
		validateTheElementPresence(userTestimonialDesc_OneStar);
		
		scrollFromUpToDowninApp();
		scrollFromUpToDowninApp();
		
		validateTheElementPresence(andCountingText);
		validateTheElementPresence(seeWhatOurHappyUsersSaying);
		
		return this;
		
	}
}
