package com.app.ck.test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.app.ck.base.WrapperMethods;
import com.app.ck.pages.AccountSettingsPage;
import com.app.ck.pages.ChooseLanguagePage;
import com.app.ck.pages.FAQPage;
import com.app.ck.pages.EKOnboardingPage;
import com.app.ck.pages.HomePage;
import com.app.ck.pages.IntermediateRetailerPage;
import com.app.ck.pages.JoinFreePage;
import com.app.ck.pages.MyEarningsPage;
import com.app.ck.pages.OTPPage;
import com.app.ck.pages.PaymentOTPPage;
import com.app.ck.pages.SignedInProfilePage;
import com.app.ck.pages.SliderScreenPage;
import com.app.ck.pages.StoreCategoryPage;
import com.app.ck.pages.StoreDetailPage;
import com.app.ck.pages.SuccessStories;
import com.app.ck.pages.WhatsAppUtility;
import com.app.ck.pages.admin.AdminCashbackPage;
import com.app.ck.pages.admin.AdminCoreFunction;
import com.app.ck.pages.admin.AdminPartnerSettingsPage;
import com.app.ck.pages.admin.AdminPaymentSettingsPage;
import com.app.ck.pages.admin.AdminPendingCashoutsPage;
import com.app.ck.pages.admin.AdminReportsPage;
import com.app.ck.pages.admin.AdminStoresPage;
import com.app.ck.pages.admin.AdminUsersPage;
import com.app.ck.pages.admin.ProductBrowserEditModePage;
import com.app.ck.pages.admin.TestimonialPages;
import com.app.ck.utilities.CashKaroUtility;
import com.app.ck.utilities.Utilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class EKTestChooseLanguageValidation extends WrapperMethods {



	@Test
	public void aaa_setAllPaymentMethod_CashbackTypeAs_All_And_PaymentMethod_StatusAs_Active(){

		reportStep("TC004 is  started", "INFO");


		CashKaroUtility cu = new CashKaroUtility(udid, port, automationName, deviceName);
		driver = cu.launchChromeWebView(driver);

		AdminCoreFunction adminFunctions = new AdminCoreFunction(driver, testInfo);		
		adminFunctions.naviagateAdminPage_ThenSelectEarnKaro();
		adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
		adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
		adminFunctions.clickSubmit();

		adminFunctions.clickOnHamburgerMenu();
		adminFunctions.clickOnSettings();
		adminFunctions.clickOnPartnerSettings();

		AdminPaymentSettingsPage adminPaymentSettingsPage = new AdminPaymentSettingsPage(driver, testInfo);

		adminPaymentSettingsPage.clickOnPartnerPaymentEditButton();

		for(int counter = 1 ;counter<=17;counter++) {

			if (counter == 4 || counter == 5 || counter == 6) {

				System.out.println("NO Payment method available to this Payment status ID " + counter + "So skipping to set the status ");

			}else if(counter == 1 || counter == 2 || counter == 9 || counter == 10 || counter == 11 || counter == 15 || counter == 16 || counter == 17){

				adminPaymentSettingsPage.editPaymentMethod_Select_Status(counter, "In-Active");

			}else{

				adminPaymentSettingsPage.editPaymentMethod_Select_Status(counter, "Active");
				adminPaymentSettingsPage.dropDown_Select_CashbackType(counter, "All");

			}
		}

		try {
			driver.findElement(By.id("isdefaultCashback_3")).click();
			driver.findElement(By.id("isDefaultRewards_12")).click();
		}catch (Exception e) {
			e.getMessage();
		}

		adminPaymentSettingsPage.clickOnUpdatePaymentSettingsButton();


	}

	@Test
	public void chooseLanguage_LanguageTamilValidation_SuccessStories() throws UnsupportedEncodingException, FileNotFoundException, IOException, ParseException {

		JSONObject jsonObj1 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "tamilFileName"), getTestData(16, "common_label"));
		JSONObject jsonObj4 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "tamilFileName"), getTestData(16, "home"));
		JSONObject jsonObj17 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "tamilFileName"), getTestData(16, "profile_right_menu"));
		JSONObject jsonObj22 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "tamilFileName"), getTestData(16, "success_stories"));

		reportStep("chooseLanguage_LanguageTamilValidation_SuccessStories started", "INFO");

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo);

		String emailAddress = objCashKaroUtility.generateEmail();
		String mobileNumber = objCashKaroUtility.generateMobileNumber();	

		String otpValue = new EKOnboardingPage(driver, testInfo).
				clickOnSignUpButton().
				enterFullName().
				enterEmail(emailAddress).
				enterPassword().
				enterMobileNumber(mobileNumber).
				clickOnGetOTPButton(mobileNumber);

		new OTPPage(driver, testInfo).
		enterOTP(otpValue).
		clickOnVerifyOTP();

		new HomePage(driver, testInfo).		
		clickOnProfileIconForSignedUser().
		clickOnChooseLanguage().
		clickTamilLink().
		clickContinueButton().
		clickRelaunchButton();

		validateRequiredText(retrieveChildKeyValues(jsonObj4, "dynamic_home_top_Category"), "home page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "profile_tab"), "home page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj17, "success_stories_menu"), "profile page");
		validateRequiredText(retrieveChildKeyValues(jsonObj17, "success_stories_menu"), "success stories page");
		scrollFromUpToDowninApp();scrollFromUpToDowninApp();
		validateRequiredText(retrieveChildKeyValues(jsonObj22, "title"), "success stories page");
		validateRequiredText(retrieveChildKeyValues(jsonObj22, "testimonial_txt1"), "success stories page");
		validateRequiredText(retrieveChildKeyValues(jsonObj22, "testimonial_name1"), "success stories page");
		validateRequiredText(retrieveChildKeyValues(jsonObj22, "testimonial_txt2"), "success stories page");
		validateRequiredText(retrieveChildKeyValues(jsonObj22, "testimonial_name2"), "success stories page");
		validateRequiredText(retrieveChildKeyValues(jsonObj22, "testimonial_txt3"), "success stories page");
		validateRequiredText(retrieveChildKeyValues(jsonObj22, "testimonial_name3"), "success stories page");
		validateRequiredText(retrieveChildKeyValues(jsonObj22, "testimonial_txt4"), "success stories page");
		validateRequiredText(retrieveChildKeyValues(jsonObj22, "testimonial_name4"), "success stories page");
		validateRequiredText(retrieveChildKeyValues(jsonObj22, "testimonial_txt5"), "success stories page");
		validateRequiredText(retrieveChildKeyValues(jsonObj22, "testimonial_name5"), "success stories page");


	}

	@Test
	public void chooseLanguage_LanguageTamilValidation_HowItWorks() throws UnsupportedEncodingException, FileNotFoundException, IOException, ParseException {

		JSONObject jsonObj1 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "tamilFileName"), getTestData(16, "common_label"));
		JSONObject jsonObj4 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "tamilFileName"), getTestData(16, "home"));
		JSONObject jsonObj17 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "tamilFileName"), getTestData(16, "profile_right_menu"));
		JSONObject jsonObj23 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "tamilFileName"), getTestData(16, "how_it_works"));

		reportStep("chooseLanguage_LanguageTamilValidation_HowItWorks started", "INFO");

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo);

		String emailAddress = objCashKaroUtility.generateEmail();
		String mobileNumber = objCashKaroUtility.generateMobileNumber();	

		String otpValue = new EKOnboardingPage(driver, testInfo).
				clickOnSignUpButton().
				enterFullName().
				enterEmail(emailAddress).
				enterPassword().
				enterMobileNumber(mobileNumber).
				clickOnGetOTPButton(mobileNumber);

		new OTPPage(driver, testInfo).
		enterOTP(otpValue).
		clickOnVerifyOTP();

		new HomePage(driver, testInfo).		
		clickOnProfileIconForSignedUser().
		clickOnChooseLanguage().
		clickTamilLink().
		clickContinueButton().
		clickRelaunchButton();

		validateRequiredText(retrieveChildKeyValues(jsonObj4, "dynamic_home_top_Category"), "home page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "profile_tab"), "home page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj17, "how_it_works_menu"), "profile page");
		scrollFromUpToDowninApp();scrollFromUpToDowninApp();
		validateRequiredText(retrieveChildKeyValues(jsonObj17, "how_it_works_menu"), "how earnkaro works? page");
		validateRequiredText(retrieveChildKeyValues(jsonObj23, "watch_video_title1"), "how earnkaro works? page");
		validateRequiredText(retrieveChildKeyValues(jsonObj23, "watch_video_txt"), 1, "how earnkaro works? page");
		validateRequiredText(retrieveChildKeyValues(jsonObj23, "watch_video_title2"), "how earnkaro works? page");
		validateRequiredText(retrieveChildKeyValues(jsonObj23, "watch_video_txt"), 2, "how earnkaro works? page");
		validateRequiredText(retrieveChildKeyValues(jsonObj23, "watch_video_title3"), "how earnkaro works? page");
		validateRequiredText(retrieveChildKeyValues(jsonObj23, "watch_video_txt"), 3, "how earnkaro works? page");
		validateRequiredText(retrieveChildKeyValues(jsonObj23, "watch_video_title4"), "how earnkaro works? page");
		validateRequiredText(retrieveChildKeyValues(jsonObj23, "watch_video_txt"), 4, "how earnkaro works? page");
		validateRequiredText(retrieveChildKeyValues(jsonObj23, "watch_video_title5"), "how earnkaro works? page");
		validateRequiredText(retrieveChildKeyValues(jsonObj23, "watch_video_txt"), 5, "how earnkaro works? page");
		validateRequiredText(retrieveChildKeyValues(jsonObj23, "watch_video_title6"), "how earnkaro works? page");
		validateRequiredText(retrieveChildKeyValues(jsonObj23, "watch_video_txt"), 6, "how earnkaro works? page");


	}

	@Test
	public void chooseLanguage_LanguageTamilValidation_Testimonials() throws UnsupportedEncodingException, FileNotFoundException, IOException, ParseException {

		JSONObject jsonObj1 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "tamilFileName"), getTestData(16, "common_label"));
		JSONObject jsonObj4 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "tamilFileName"), getTestData(16, "home"));
		JSONObject jsonObj17 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "tamilFileName"), getTestData(16, "profile_right_menu"));
		JSONObject jsonObj24 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "tamilFileName"), getTestData(16, "testimonial"));
		JSONObject jsonObj25 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "tamilFileName"), getTestData(16, "testimonial_write"));

		reportStep("chooseLanguage_LanguageTamilValidation_Testimonials started", "INFO");

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo);

		String fourStarTestimonials = "(//android.widget.TextView)[8]";
		String threeStarTestimonials = "(//android.widget.TextView)[7]";
		String twoStarTestimonials = "(//android.widget.TextView)[6]";
		String oneStarTestimonials = "(//android.widget.TextView)[5]";

		String emailAddress = objCashKaroUtility.generateEmail();
		String mobileNumber = objCashKaroUtility.generateMobileNumber();	

		String otpValue = new EKOnboardingPage(driver, testInfo).
				clickOnSignUpButton().
				enterFullName().
				enterEmail(emailAddress).
				enterPassword().
				enterMobileNumber(mobileNumber).
				clickOnGetOTPButton(mobileNumber);

		new OTPPage(driver, testInfo).
		enterOTP(otpValue).
		clickOnVerifyOTP();

		new HomePage(driver, testInfo).		
		clickOnProfileIconForSignedUser().
		clickOnChooseLanguage().
		clickTamilLink().
		clickContinueButton().
		clickRelaunchButton();

		validateRequiredText(retrieveChildKeyValues(jsonObj4, "dynamic_home_top_Category"), "home page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "profile_tab"), "home page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj17, "rate_us_menu"), "profile page");
		validateRequiredText(retrieveChildKeyValues(jsonObj24, "count_txt"), "testimonials page");
		validateRequiredText(retrieveChildKeyValues(jsonObj24, "happy_users_txt"), "testimonials page");

		clickOnPartialRequiredText(retrieveChildKeyValues(jsonObj24, "write_testimonial_btn"), "testimonials page");
		validateRequiredText(retrieveChildKeyValues(jsonObj24, "write_testimonial_btn"), "write testimonials page");
		validateRequiredText(retrieveChildKeyValues(jsonObj25, "title"), "write testimonials page");

		validateRequiredText(retrieveChildKeyValues(jsonObj25, "rate_experience_txt"), "write testimonials page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj25, "star_txt_awesome_experience"), "write testimonials page");
		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(fourStarTestimonials);
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj25, "star_txt_good_experience"), "write testimonials page");
		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(threeStarTestimonials);
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj25, "star_txt_average"), "write testimonials page");
		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(twoStarTestimonials);
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj25, "star_txt_not_great"), "write testimonials page");
		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(oneStarTestimonials);
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj25, "star_txt_terrible"), "write testimonials page");


	}

	@Test
	public void chooseLanguage_LanguageTamilValidation_ContactUs() throws UnsupportedEncodingException, FileNotFoundException, IOException, ParseException {

		JSONObject jsonObj1 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "tamilFileName"), getTestData(16, "common_label"));
		JSONObject jsonObj2 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "tamilFileName"), getTestData(16, "common_validate_label"));
		JSONObject jsonObj4 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "tamilFileName"), getTestData(16, "home"));
		JSONObject jsonObj17 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "tamilFileName"), getTestData(16, "profile_right_menu"));
		JSONObject jsonObj20 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "tamilFileName"), getTestData(16, "contact_us"));		
		JSONObject jsonObj21 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "tamilFileName"), getTestData(16, "faq"));

		reportStep("chooseLanguage_LanguageTamilValidation_ContactUs started", "INFO");

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo);

		String textBoxUserName = "//android.widget.EditText[@content-desc='et_contactUs_yourName']";
		String textBoxEmail = "//android.widget.EditText[@content-desc='et_contactUs_email']";

		String emailAddress = objCashKaroUtility.generateEmail();
		String mobileNumber = objCashKaroUtility.generateMobileNumber();	

		String otpValue = new EKOnboardingPage(driver, testInfo).
				clickOnSignUpButton().
				enterFullName().
				enterEmail(emailAddress).
				enterPassword().
				enterMobileNumber(mobileNumber).
				clickOnGetOTPButton(mobileNumber);

		new OTPPage(driver, testInfo).
		enterOTP(otpValue).
		clickOnVerifyOTP();

		new HomePage(driver, testInfo).		
		clickOnProfileIconForSignedUser().
		clickOnChooseLanguage().
		clickTamilLink().
		clickContinueButton().
		clickRelaunchButton();

		validateRequiredText(retrieveChildKeyValues(jsonObj4, "dynamic_home_top_Category"), "home page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "profile_tab"), "home page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj17, "help"), "profile page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj17, "contact_us_menu"), "help page");
		enterTextByXpath(textBoxUserName, "..");
		enterTextByXpath(textBoxEmail, "..");
		validateRequiredText(retrieveChildKeyValues(jsonObj21, "faq_btn"), "contact us page");
		validateRequiredText(retrieveChildKeyValues(jsonObj20, "name_inputfield_txt"), "contact us page");
		validateRequiredText(retrieveChildKeyValues(jsonObj20, "email_inputfield_txt"), "contact us page");
		validateRequiredText(retrieveChildKeyValues(jsonObj20, "message_inputfield_txt"), "contact us page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj20, "submit_btn"), "contact us page");

		validateRequiredText(retrieveChildKeyValues(jsonObj2, "select_subject_error_msg"), "contact us page");
		validateRequiredText(retrieveChildKeyValues(jsonObj20, "name_inputfield_txt"), "contact us page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "valid_name_error_msg"), "contact us page");
		validateRequiredText(retrieveChildKeyValues(jsonObj20, "email_inputfield_txt"), "contact us page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "valid_email_error_msg"), "contact us page");
		validateRequiredText(retrieveChildKeyValues(jsonObj20, "message_inputfield_txt"), "contact us page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "valid_details_error_msg"), "contact us page");

		enterTextByXpath(textBoxUserName, getTestData(7, "oneUserChar"));
		enterTextByXpath(textBoxEmail, getTestData(7, "oneUserChar"));
		clickOnRequiredText(retrieveChildKeyValues(jsonObj20, "submit_btn"), "contact us page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "select_subject_error_msg"), "contact us page");
		validateRequiredText(retrieveChildKeyValues(jsonObj20, "name_inputfield_txt"), "contact us page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "minimum_characters_error_msg"), 1, "contact us page");
		validateRequiredText(retrieveChildKeyValues(jsonObj20, "email_inputfield_txt"), "contact us page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "minimum_characters_error_msg"), 2, "contact us page");
		validateRequiredText(retrieveChildKeyValues(jsonObj20, "message_inputfield_txt"), "contact us page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "valid_details_error_msg"), "contact us page");

		enterTextByXpath(textBoxUserName, getTestData(7, "FiftyPlusUserChar"));
		enterTextByXpath(textBoxEmail, getTestData(7, "FiftyPlusUserChar"));
		clickOnRequiredText(retrieveChildKeyValues(jsonObj20, "submit_btn"), "contact us page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "select_subject_error_msg"), "contact us page");
		validateRequiredText(retrieveChildKeyValues(jsonObj20, "name_inputfield_txt"), "contact us page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "maximum_characters_error_msg"), 1, "contact us page");
		validateRequiredText(retrieveChildKeyValues(jsonObj20, "email_inputfield_txt"), "contact us page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "maximum_characters_error_msg"), 2, "contact us page");
		validateRequiredText(retrieveChildKeyValues(jsonObj20, "message_inputfield_txt"), "contact us page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "valid_details_error_msg"), "contact us page");

	}

	@Test
	public void chooseLanguage_LanguageTamilValidation_FAQs() throws UnsupportedEncodingException, FileNotFoundException, IOException, ParseException {

		JSONObject jsonObj1 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "tamilFileName"), getTestData(16, "common_label"));
		JSONObject jsonObj4 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "tamilFileName"), getTestData(16, "home"));
		JSONObject jsonObj17 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "tamilFileName"), getTestData(16, "profile_right_menu"));		
		JSONObject jsonObj21 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "tamilFileName"), getTestData(16, "faq"));

		reportStep("chooseLanguage_LanguageTamilValidation_FAQs started", "INFO");

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo);

		String emailAddress = objCashKaroUtility.generateEmail();
		String mobileNumber = objCashKaroUtility.generateMobileNumber();	

		String otpValue = new EKOnboardingPage(driver, testInfo).
				clickOnSignUpButton().
				enterFullName().
				enterEmail(emailAddress).
				enterPassword().
				enterMobileNumber(mobileNumber).
				clickOnGetOTPButton(mobileNumber);

		new OTPPage(driver, testInfo).
		enterOTP(otpValue).
		clickOnVerifyOTP();

		new HomePage(driver, testInfo).		
		clickOnProfileIconForSignedUser().
		clickOnChooseLanguage().
		clickTamilLink().
		clickContinueButton().
		clickRelaunchButton();

		validateRequiredText(retrieveChildKeyValues(jsonObj4, "dynamic_home_top_Category"), "home page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "profile_tab"), "home page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj17, "help"), "profile page");

		validateRequiredText(retrieveChildKeyValues(jsonObj17, "call_us_menu"), "help page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj17, "faq_menu"), "help page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj21, "question1"), "faq page");
		validatePartialRequiredText(getTestData(16, "faqAnswer1_1"), "faq page");
		validatePartialRequiredText(getTestData(16, "faqAnswer1_2"), "faq page");
		validatePartialRequiredText(getTestData(16, "faqAnswer1_3"), "faq page");
		validatePartialRequiredText(getTestData(16, "faqAnswer1_4"), "faq page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj21, "question2"), "faq page");
		validateRequiredText(retrieveChildKeyValues(jsonObj21, "answer2"), "faq page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj21, "question3"), "faq page");
		validateRequiredText(getTestData(16, "faqAnswer3"), "faq page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj21, "question4"), "faq page");
		validateRequiredText(retrieveChildKeyValues(jsonObj21, "answer4"), "faq page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj21, "question5"), "faq page");
		validateRequiredText(retrieveChildKeyValues(jsonObj21, "answer5"), "faq page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj21, "question6"), "faq page");
		validateRequiredText(retrieveChildKeyValues(jsonObj21, "answer6"), "faq page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj21, "question7"), "faq page");
		validateRequiredText(retrieveChildKeyValues(jsonObj21, "answer7"), "faq page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj21, "question8"), "faq page");
		validateRequiredText(retrieveChildKeyValues(jsonObj21, "answer8"), "faq page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj21, "question9"), "faq page");
		validatePartialRequiredText(getTestData(16, "faqAnswer9"), "faq page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj21, "question10"), "faq page");
		validatePartialRequiredText(getTestData(16, "faqAnswer10"), "faq page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj21, "question11"), "faq page");
		validateRequiredText(getTestData(16, "faqAnswer11"), "faq page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj21, "question12"), "faq page");
		validateRequiredText(getTestData(16, "faqAnswer12"), "faq page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj21, "question13"), "faq page");
		validateRequiredText(retrieveChildKeyValues(jsonObj21, "answer13"), "faq page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj21, "question14"), "faq page");
		validateRequiredText(getTestData(16, "faqAnswer14"), "faq page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj21, "question15"), "faq page");
		validateRequiredText(retrieveChildKeyValues(jsonObj21, "answer15"), "faq page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj21, "question16"), "faq page");
		validateRequiredText(retrieveChildKeyValues(jsonObj21, "answer16"), "faq page");

		validateRequiredText(retrieveChildKeyValues(jsonObj21, "faq_btn"), "faq page");


	}

	@Test
	public void chooseLanguage_LanguageTamilValidation_History_NewUser() throws UnsupportedEncodingException, FileNotFoundException, IOException, ParseException {

		JSONObject jsonObj1 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "tamilFileName"), getTestData(16, "common_label"));
		JSONObject jsonObj4 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "tamilFileName"), getTestData(16, "home"));
		JSONObject jsonObj17 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "tamilFileName"), getTestData(16, "profile_right_menu"));
		JSONObject jsonObj52 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "tamilFileName"), getTestData(16, "my_shared_activities"));

		reportStep("chooseLanguage_LanguageTamilValidation_History_NewUser started", "INFO");

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo);

		String emailAddress = objCashKaroUtility.generateEmail();
		String mobileNumber = objCashKaroUtility.generateMobileNumber();	

		String otpValue = new EKOnboardingPage(driver, testInfo).
				clickOnSignUpButton().
				enterFullName().
				enterEmail(emailAddress).
				enterPassword().
				enterMobileNumber(mobileNumber).
				clickOnGetOTPButton(mobileNumber);

		new OTPPage(driver, testInfo).
		enterOTP(otpValue).
		clickOnVerifyOTP();

		new HomePage(driver, testInfo).		
		clickOnProfileIconForSignedUser().
		clickOnChooseLanguage().
		clickTamilLink().
		clickContinueButton().
		clickRelaunchButton();

		validateRequiredText(retrieveChildKeyValues(jsonObj4, "dynamic_home_top_Category"), "home page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "profile_tab"), "home page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj17, "history_menu"), "profile page");
		validateRequiredText(retrieveChildKeyValues(jsonObj17, "recent_clicks_menu"), "history page");
		validateRequiredText(retrieveChildKeyValues(jsonObj1, "whoops_msg"), "history page");
		validateRequiredText(retrieveChildKeyValues(jsonObj52, "no_shared_activites"), "history page");
		validateRequiredText(retrieveChildKeyValues(jsonObj52, "saving_click_away"), "history page");


	}

	@Test
	public void chooseLanguage_LanguageTamilValidation_AccountSettings() throws UnsupportedEncodingException, FileNotFoundException, IOException, ParseException {

		JSONObject jsonObj1 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "tamilFileName"), getTestData(16, "common_label"));
		JSONObject jsonObj2 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "tamilFileName"), getTestData(16, "common_validate_label"));
		JSONObject jsonObj17 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "tamilFileName"), getTestData(16, "profile_right_menu"));
		JSONObject jsonObj26 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "tamilFileName"), getTestData(16, "personal_details"));
		JSONObject jsonObj27 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "tamilFileName"), getTestData(16, "change_password"));
		JSONObject jsonObj40 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "tamilFileName"), getTestData(16, "giftcard_wallet_inputfield"));

		reportStep("chooseLanguage_LanguageTamilValidation_AccountSettings started", "INFO");

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo);

		String textBoxExistingPwd = "//android.widget.EditText[@content-desc='et_changePassword_currentPassword']";
		String textBoxNewPwd = "//android.widget.EditText[@content-desc='et_changePassword_fullName']";
		String textBoxConfirmNewPwd = "//android.widget.EditText[@content-desc='et_changePassword_confirmPassword']";

		String dropDownPaymentSettings = "(//android.widget.EditText)[1]";
		String textNEFT = "//android.widget.CheckedTextView[@text='Bank Payment (NEFT)']";
		String textPaytmWallet = "//android.widget.CheckedTextView[@text='Paytm Wallet']";
		String textAmazonGiftCard = "//android.widget.CheckedTextView[@text='Amazon Gift Card']";

		String textBoxPaytmMobileNumber = "//android.widget.EditText[@content-desc='et_settingsPaytm_mobile']";
		String textBoxPaytmPassword = "//android.widget.EditText[@content-desc='et_settingsPaytm_password']";

		String textBoxAmazonEmail = "//android.widget.EditText[@content-desc='et_settingsAmazon_email']";
		String textBoxAmazonPassword = "//android.widget.EditText[@content-desc='et_settingsAmazon_password']";
		String emailAddress = objCashKaroUtility.generateEmail();
		String mobileNumber = objCashKaroUtility.generateMobileNumber();	

		String otpValue = new EKOnboardingPage(driver, testInfo).
				clickOnSignUpButton().
				enterFullName().
				enterEmail(emailAddress).
				enterPassword().
				enterMobileNumber(mobileNumber).
				clickOnGetOTPButton(mobileNumber);

		new OTPPage(driver, testInfo).
		enterOTP(otpValue).
		clickOnVerifyOTP();

		new HomePage(driver, testInfo).		
		clickOnProfileIconForSignedUser().
		clickOnChooseLanguage().
		clickTamilLink().
		clickContinueButton().
		clickRelaunchButton();

		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "profile_tab"), "home page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj17, "account_setting_menu"), "profile page");
		validateRequiredText(retrieveChildKeyValues(jsonObj17, "account_setting_menu"), "profile page");
		clickOnPartialRequiredText(getTestData(16, "personalDetails_1"), "account settings page");
		clickOnPartialRequiredText(getTestData(16, "personalDetails_2"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj26, "name_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj26, "email_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj26, "mobile_number_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj26, "receive_newsletter_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj26, "receive_referral_earning_txt"), "account settings page");
		validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj26, "note_txt"), "account settings page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "save_changes_btn"), "account settings page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj26, "no_value_change_error_msg"), "account settings page");

		clickOnPartialRequiredText(getTestData(16, "changePwd_1"), "profile page");
		clickOnPartialRequiredText(getTestData(16, "changePwd_2"), "profile page");
		validateRequiredText(retrieveChildKeyValues(jsonObj27, "change_password_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj27, "new_password_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj27, "confirm_password_inputfield_txt"), "account settings page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "save_changes_btn"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj27, "change_password_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "current_password_error_msg"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj27, "new_password_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "new_password_error_msg"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj27, "confirm_password_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "confirm_password_error_msg"), "account settings page");

		enterTextByXpath(textBoxExistingPwd, getTestData(7, "oneUserChar"));
		enterTextByXpath(textBoxNewPwd, getTestData(7, "oneUserChar"));
		enterTextByXpath(textBoxConfirmNewPwd, getTestData(7, "oneUserChar"));
		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "save_changes_btn"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj27, "change_password_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "password_minimum_characters_error_msg"), 1, "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj27, "new_password_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "password_minimum_characters_error_msg"), 2, "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj27, "confirm_password_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "password_minimum_characters_error_msg"), 3, "account settings page");

		enterTextByXpath(textBoxExistingPwd, getTestData(7, "FiftyUserChar"));
		enterTextByXpath(textBoxNewPwd, getTestData(7, "FiftyUserChar"));
		enterTextByXpath(textBoxConfirmNewPwd, getTestData(7, "FiftyUserChar"));
		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "save_changes_btn"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj27, "change_password_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "password_maximum_characters_error_msg"), 1, "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj27, "new_password_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "password_maximum_characters_error_msg"), 2, "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj27, "confirm_password_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "password_maximum_characters_error_msg"), 3, "account settings page");


		enterTextByXpath(textBoxExistingPwd, getTestData(0, "TC001_ValidLoginPassword"));
		enterTextByXpath(textBoxNewPwd, getTestData(0, "TC001_ValidLoginPassword"));
		enterTextByXpath(textBoxConfirmNewPwd, getTestData(0, "TC001_InValidPassword"));
		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "save_changes_btn"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj27, "change_password_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj27, "new_password_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj27, "confirm_password_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "password_not_match_error_msg"), "account settings page");

		clickOnPartialRequiredText(getTestData(16, "paymentSettings_1"), "profile page");
		clickOnPartialRequiredText(getTestData(16, "paymentSettings_2"), "profile page");

		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(dropDownPaymentSettings);
		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(textPaytmWallet);
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "mobile_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "confirm_password_inputfield_txt"), "account settings page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "save_changes_btn"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "mobile_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "mobile_number_error_msg"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "confirm_password_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "password_error_msg"), "account settings page");

		enterTextByXpath(textBoxPaytmMobileNumber, "1");
		enterTextByXpath(textBoxPaytmPassword, "1");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "save_changes_btn"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "mobile_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "minimum_digits_error_msg"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "confirm_password_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "password_minimum_characters_error_msg"), "account settings page");

		enterTextByXpath(textBoxPaytmPassword, getTestData(7, "FiftyUserChar"));
		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "save_changes_btn"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "confirm_password_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "password_maximum_characters_error_msg"), "account settings page");

		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(dropDownPaymentSettings);
		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(textAmazonGiftCard);

		validateRequiredText(retrieveChildKeyValues(jsonObj40, "email_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "confirm_password_inputfield_txt"), "account settings page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "save_changes_btn"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "email_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "email_error_msg"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "confirm_password_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "password_error_msg"), "account settings page");

		enterTextByXpath(textBoxAmazonEmail, "1");
		enterTextByXpath(textBoxAmazonPassword, "1");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "save_changes_btn"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "email_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "minimum_characters_error_msg"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "confirm_password_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "password_minimum_characters_error_msg"), "account settings page");

		enterTextByXpath(textBoxAmazonEmail, "asdfghjkl");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "save_changes_btn"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "email_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "valid_email_error_msg"), "account settings page");

		enterTextByXpath(textBoxAmazonEmail, getTestData(7, "FiftyUserCharWithSpace"));
		enterTextByXpath(textBoxAmazonPassword, getTestData(7, "FiftyUserChar"));
		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "save_changes_btn"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "email_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "maximum_characters_error_msg"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "confirm_password_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "maximum_characters_error_msg"), "account settings page");

		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(dropDownPaymentSettings);
		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(textNEFT);

		validateRequiredText(retrieveChildKeyValues(jsonObj40, "bank_account_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "bank_name_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "branch_name_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "bank_account_number_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "bank_ifsc_code_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "confirm_password_inputfield_txt"), "account settings page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "save_changes_btn"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "bank_account_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "account_holder_error_msg"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "bank_name_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "valid_bank_name_error_msg"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "branch_name_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "branch_minimum_digits_error_msg"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "bank_account_number_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "account_number_error_msg"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "bank_ifsc_code_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "ifsc_code_error_msg"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "confirm_password_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "password_error_msg"), "account settings page");

	}

	@Test
	public void chooseLanguage_LanguageTamilValidation_ReferAndEarn() throws UnsupportedEncodingException, FileNotFoundException, IOException, ParseException {

		JSONObject jsonObj1 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "tamilFileName"), getTestData(16, "common_label"));
		JSONObject jsonObj17 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "tamilFileName"), getTestData(16, "profile_right_menu"));
		JSONObject jsonObj18 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "tamilFileName"), getTestData(16, "referandearn"));

		reportStep("chooseLanguage_LanguageTamilValidation_AccountSettings started", "INFO");

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo);

		String emailAddress = objCashKaroUtility.generateEmail();
		String mobileNumber = objCashKaroUtility.generateMobileNumber();	

		String otpValue = new EKOnboardingPage(driver, testInfo).
				clickOnSignUpButton().
				enterFullName().
				enterEmail(emailAddress).
				enterPassword().
				enterMobileNumber(mobileNumber).
				clickOnGetOTPButton(mobileNumber);

		new OTPPage(driver, testInfo).
		enterOTP(otpValue).
		clickOnVerifyOTP();

		new HomePage(driver, testInfo).		
		clickOnProfileIconForSignedUser().
		clickOnChooseLanguage().
		clickTamilLink().
		clickContinueButton().
		clickRelaunchButton();

		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "profile_tab"), "home page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj17, "my_referral_menu"), "profile page");
		validateRequiredText(retrieveChildKeyValues(jsonObj17, "my_referral_menu"), "my referral page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj17, "referandearn_menu"), "my referral page");
		validateRequiredText(retrieveChildKeyValues(jsonObj17, "referandearn_menu"), "my referral page");
		scrollFromUpToDowninApp();scrollFromUpToDowninApp();
		validateRequiredText(retrieveChildKeyValues(jsonObj18, "title"), "my referral page");
		validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj18, "sub_title"), "my referral page");
		validateRequiredText(retrieveChildKeyValues(jsonObj18, "message"), "my referral page");
		validateRequiredText(retrieveChildKeyValues(jsonObj18, "referral_link_title"), "my referral page");
		validateRequiredText(retrieveChildKeyValues(jsonObj18, "copy_message_title"), "my referral page");
		validateRequiredText(retrieveChildKeyValues(jsonObj18, "tap_copy"), "my referral page");
		validateRequiredText(retrieveChildKeyValues(jsonObj18, "invite_title"), "my referral page");


	}

	@Test
	public void chooseLanguage_LanguageTamilValidation_ReferralNetwork() throws UnsupportedEncodingException, FileNotFoundException, IOException, ParseException {

		JSONObject jsonObj1 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "tamilFileName"), getTestData(16, "common_label"));
		JSONObject jsonObj17 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "tamilFileName"), getTestData(16, "profile_right_menu"));
		JSONObject jsonObj19 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "tamilFileName"), getTestData(16, "referral_network"));

		reportStep("chooseLanguage_LanguageTamilValidation_AccountSettings started", "INFO");

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo);

		String emailAddress = objCashKaroUtility.generateEmail();
		String mobileNumber = objCashKaroUtility.generateMobileNumber();	

		String otpValue = new EKOnboardingPage(driver, testInfo).
				clickOnSignUpButton().
				enterFullName().
				enterEmail(emailAddress).
				enterPassword().
				enterMobileNumber(mobileNumber).
				clickOnGetOTPButton(mobileNumber);

		new OTPPage(driver, testInfo).
		enterOTP(otpValue).
		clickOnVerifyOTP();

		new HomePage(driver, testInfo).		
		clickOnProfileIconForSignedUser().
		clickOnChooseLanguage().
		clickTamilLink().
		clickContinueButton().
		clickRelaunchButton();

		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "profile_tab"), "home page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj17, "my_referral_menu"), "profile page");
		validateRequiredText(retrieveChildKeyValues(jsonObj17, "my_referral_menu"), "my referral page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj17, "referral_network_menu"), "profile page");
		validateRequiredText(retrieveChildKeyValues(jsonObj17, "referral_network_menu"), "referral nw page");
		validateRequiredText(retrieveChildKeyValues(jsonObj19, "title"), "referral nw page");
		validateRequiredText(retrieveChildKeyValues(jsonObj19, "total_referral_earn_txt"), "referral nw page");
		validateRequiredText(retrieveChildKeyValues(jsonObj19, "friend_joined_txt"), "referral nw page");
		validateRequiredText(retrieveChildKeyValues(jsonObj19, "no_referral_title"), "referral nw page");
		validateRequiredText(retrieveChildKeyValues(jsonObj19, "no_referral_message"), "referral nw page");
		validateRequiredText(retrieveChildKeyValues(jsonObj19, "refer_earn_btn"), "referral nw page");


	}

	@Test
	public void chooseLanguage_LanguageTamilValidation_MyEarnings_NewUser() throws UnsupportedEncodingException, FileNotFoundException, IOException, ParseException {

		JSONObject jsonObj1 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "tamilFileName"), getTestData(16, "common_label"));
		JSONObject jsonObj4 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "tamilFileName"), getTestData(16, "home"));
		JSONObject jsonObj17 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "tamilFileName"), getTestData(16, "profile_right_menu"));
		JSONObject jsonObj28 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "tamilFileName"), getTestData(16, "my_account_earnings"));
		JSONObject jsonObj30 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "tamilFileName"), getTestData(16, "earnings_info"));

		JSONObject jsonObj33 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "tamilFileName"), getTestData(16, "my_earnings"));

		reportStep("chooseLanguage_LanguageTamilValidation_MyEarnings_NewUser started", "INFO");

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo);

		String linkEarningsInfo = "//android.widget.TextView[@content-desc='txt_myEarnings_cashbackEarningsInfo']";

		String emailAddress = objCashKaroUtility.generateEmail();
		String mobileNumber = objCashKaroUtility.generateMobileNumber();	

		String otpValue = new EKOnboardingPage(driver, testInfo).
				clickOnSignUpButton().
				enterFullName().
				enterEmail(emailAddress).
				enterPassword().
				enterMobileNumber(mobileNumber).
				clickOnGetOTPButton(mobileNumber);

		new OTPPage(driver, testInfo).
		enterOTP(otpValue).
		clickOnVerifyOTP();

		new HomePage(driver, testInfo).		
		clickOnProfileIconForSignedUser().
		clickOnChooseLanguage().
		clickTamilLink().
		clickContinueButton().
		clickRelaunchButton();

		validateRequiredText(retrieveChildKeyValues(jsonObj4, "dynamic_home_top_Category"), "home page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "profile_tab"), "home page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj17, "my_earnings_menu"), "profile page");
		validateRequiredText(retrieveChildKeyValues(jsonObj17, "my_earnings_menu"), "my earnings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj28, "total_earning_txt"), "my earnings page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj33, "cashback_tap"), "my earnings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj33, "cashback_earning_title"), "my earnings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj33, "request_cashback_btn"), "my earnings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj33, "no_cashback_yet_txt"), "my earnings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj33, "shop_today_txt"), "my earnings page");

		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(linkEarningsInfo);
		validateRequiredText(retrieveChildKeyValues(jsonObj30, "message_first_txt"), "earnings info page");


		backButton();

		clickOnRequiredText(retrieveChildKeyValues(jsonObj33, "referral_tap"), "my earnings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj33, "referral_earning_title"), "my earnings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj33, "request_cashback_btn"), "my earnings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj33, "no_referral_yet_txt"), "my earnings page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj33, "no_referral_yet_sub_txt"), "my earnings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj33, "no_referral_invite_some_friends"), "my earnings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj33, "refer_now_btn"), "my earnings page");


	}

	@Test
	public void chooseLanguage_LanguageTamilValidation_GetStarted_Joinfree_TermsAndConditions() throws UnsupportedEncodingException, FileNotFoundException, IOException, ParseException {

		JSONObject jsonObj1 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "tamilFileName"), getTestData(16, "common_label"));
		JSONObject jsonObj2 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "tamilFileName"), getTestData(16, "common_validate_label"));
		JSONObject jsonObj17 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "tamilFileName"), getTestData(16, "profile_right_menu"));
		JSONObject jsonObj34 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "tamilFileName"), getTestData(16, "on_boarding"));
		JSONObject jsonObj36 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "tamilFileName"), getTestData(16, "join_free"));
		JSONObject jsonObj50 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "tamilFileName"), getTestData(16, "terms_conditions"));

		reportStep("chooseLanguage_LanguageTamilValidation_GetStarted_Joinfree_Login_ForgotPassword started", "INFO");

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo);

		String textboxName = "//android.widget.EditText[@content-desc='et_signup_fullname']";
		String textboxEmail = "//android.widget.EditText[@content-desc='et_signup_email']|//android.widget.EditText[@content-desc='et_signin_email']|//android.widget.EditText[@content-desc='et_forgotpassword_email']";
		String textboxPwd = "//android.widget.EditText[@content-desc='et_signup_password']|//android.widget.EditText[@content-desc='et_signin_password']";
		String textboxMobileNumber = "//android.widget.EditText[@content-desc='et_signup_mobile']";		
		String emailAddress = objCashKaroUtility.generateEmail();
		String mobileNumber = objCashKaroUtility.generateMobileNumber();	

		String otpValue = new EKOnboardingPage(driver, testInfo).
				clickOnSignUpButton().
				enterFullName().
				enterEmail(emailAddress).
				enterPassword().
				enterMobileNumber(mobileNumber).
				clickOnGetOTPButton(mobileNumber);

		new OTPPage(driver, testInfo).
		enterOTP(otpValue).
		clickOnVerifyOTP();

		new HomePage(driver, testInfo).		
		clickOnProfileIconForSignedUser().
		clickOnChooseLanguage().
		clickTamilLink().
		clickContinueButton().
		clickRelaunchButton();

		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "profile_tab"), "home page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj17, "logout_menu"), "profile page");
		validateRequiredText(retrieveChildKeyValues(jsonObj17, "alert_logout"), "logout popup");
		validateRequiredText(retrieveChildKeyValues(jsonObj17, "alert_logout_msg"), "logout popup");
		validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj17, "alert_cancel_btn"), "logout popup");
		clickOnPartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj17, "alert_ok_btn"), "logout popup");

		new EKOnboardingPage(driver, testInfo).
		clickOnSignUpButton();

		validateRequiredText(retrieveChildKeyValues(jsonObj17, "join_free_menu"), "join free page");
		scrollFromUpToDowninApp();
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "title"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "subtitle"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "join_facebook_txt"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "join_or_txt"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "name_inputfield_txt"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "email_inputfield_txt"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "password_inputfield_txt"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "mobile_inputfield_txt"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "otp_btn"), "join free page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj36, "already_register"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "sign_in_txt"), "join free page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj36, "signup_terms_txt"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "terms_condition_txt"), "join free page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj36, "otp_btn"), "join free page");		
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "name_inputfield_txt"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "name_error_msg"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "email_inputfield_txt"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "email_error_msg"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "password_inputfield_txt"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "password_error_msg"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "mobile_inputfield_txt"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "mobile_number_error_msg"), "join free page");

		enterTextByXpath(textboxName, getTestData(7, "oneUserChar"));
		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(textboxEmail);
		clickOnNoneOFTheAbove();
		enterTextByXpath(textboxEmail, getTestData(7, "oneUserChar"));
		enterTextByXpath(textboxPwd, getTestData(7, "oneUserChar"));
		enterTextByXpath(textboxMobileNumber, getTestData(7, "oneUserChar"));
		clickOnRequiredText(retrieveChildKeyValues(jsonObj36, "otp_btn"), "join free page");		
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "name_inputfield_txt"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "minimum_characters_error_msg"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "email_inputfield_txt"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "minimum_characters_error_msg"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "password_inputfield_txt"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "password_minimum_characters_error_msg"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "mobile_inputfield_txt"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "minimum_digits_error_msg"), "join free page");

		enterTextByXpath(textboxName, getTestData(7, "FiftyUserCharWithSpace"));
		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(textboxEmail);
		clickOnNoneOFTheAbove();
		enterTextByXpath(textboxEmail, getTestData(7, "FiftyUserCharWithSpace"));
		enterTextByXpath(textboxPwd, getTestData(7, "FiftyUserChar"));
		enterTextByXpath(textboxMobileNumber, "2222222222");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj36, "otp_btn"), "join free page");		
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "name_inputfield_txt"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "maximum_characters_error_msg"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "email_inputfield_txt"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "maximum_characters_error_msg"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "password_inputfield_txt"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "password_maximum_characters_error_msg"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "mobile_inputfield_txt"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "valid_mobile_number_error_msg"), "join free page");

		enterTextByXpath(textboxName, "2*2");
		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(textboxEmail);
		clickOnNoneOFTheAbove();
		enterTextByXpath(textboxEmail, "2*2");
		enterTextByXpath(textboxPwd, "2 2 2 2 2");
		enterTextByXpath(textboxMobileNumber, "2*22222222");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj36, "otp_btn"), "join free page");		
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "name_inputfield_txt"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "valid_name_error_msg"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "email_inputfield_txt"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "valid_email_error_msg"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "password_inputfield_txt"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "valid_password_error_msg"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "mobile_inputfield_txt"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "valid_mobile_number_error_msg"), "join free page");

		backButton();

		new EKOnboardingPage(driver, testInfo).
		clickOnSignUpButton();
		clickOnRequiredText(retrieveChildKeyValues(jsonObj36, "terms_condition_txt"), "join free page");
		scrollFromUpToDowninApp();scrollFromUpToDowninApp();
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "terms_condition_txt"), "terms & conditions page");
		validateRequiredText(retrieveChildKeyValues(jsonObj50, "title"), "terms & conditions page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj50, "introduction_title"), "terms & conditions page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj50, "introduction_details"), "terms & conditions page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj50, "membership_title"), "terms & conditions page");
		validateRequiredText(retrieveChildKeyValues(jsonObj50, "membership_details"), "terms & conditions page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj50, "our_service_title"), "terms & conditions page");
		validateRequiredText(retrieveChildKeyValues(jsonObj50, "our_service_details"), "terms & conditions page");


	}

	@Test
	public void chooseLanguage_LanguageTamilValidation_MakeLinks_NewUser() throws UnsupportedEncodingException, FileNotFoundException, IOException, ParseException {

		JSONObject jsonObj1 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "tamilFileName"), getTestData(16, "common_label"));
		JSONObject jsonObj2 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "tamilFileName"), getTestData(16, "common_validate_label"));
		JSONObject jsonObj4 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "tamilFileName"), getTestData(16, "home"));
		JSONObject jsonObj17 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "tamilFileName"), getTestData(16, "profile_right_menu"));
		JSONObject jsonObj51 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "tamilFileName"), getTestData(16, "make_profit_links"));
		JSONObject jsonObj53 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "tamilFileName"), getTestData(16, "profit_partners_details"));
		JSONObject jsonObj54 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "tamilFileName"), getTestData(16, "profit_partners_list"));

		reportStep("chooseLanguage_LanguageTamilValidation_MakeLinks_NewUser started", "INFO");

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo);

		String imagePartner = "//android.widget.ImageView[@content-desc='img_profitPartnersList_partnersIMG']";
		String emailAddress = objCashKaroUtility.generateEmail();
		String mobileNumber = objCashKaroUtility.generateMobileNumber();	

		String otpValue = new EKOnboardingPage(driver, testInfo).
				clickOnSignUpButton().
				enterFullName().
				enterEmail(emailAddress).
				enterPassword().
				enterMobileNumber(mobileNumber).
				clickOnGetOTPButton(mobileNumber);

		new OTPPage(driver, testInfo).
		enterOTP(otpValue).
		clickOnVerifyOTP();

		new HomePage(driver, testInfo).		
		clickOnProfileIconForSignedUser().
		clickOnChooseLanguage().
		clickTamilLink().
		clickContinueButton().
		clickRelaunchButton();

		validateRequiredText(retrieveChildKeyValues(jsonObj4, "dynamic_home_top_Category"), "home page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "profile_tab"), "home page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj17, "make_profit_link_menu"), "profile page");
		validateRequiredText(retrieveChildKeyValues(jsonObj51, "title"), "make links page");
		validateRequiredText(retrieveChildKeyValues(jsonObj51, "subtitle"), "make links page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj51, "see_partner_txt"), "make links page");
		validateRequiredText(retrieveChildKeyValues(jsonObj54, "toolbar_profit_partners_title"), "partners page");
		validateRequiredText(retrieveChildKeyValues(jsonObj54, "profit_partners_information"), "partners page");
		reportStep("About to click Parnters Store Image ", "Info");
		if(new ChooseLanguagePage(driver,testInfo,true).clickByXpath(imagePartner)) {
			reportStep("Successfully clicked on the Report Step ", "Pass");
		}else {
			reportStep("Not able to click on Partner image ", "Fail");
		}
		validateRequiredText(retrieveChildKeyValues(jsonObj53, "profit_rates"), "store page");

		backButton();
		backButton();

		validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj51, "paste_link"), "make links page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj51, "make_profit_link_txt"), "make links page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "url_error_msg"), "make links page");
		validateRequiredText(retrieveChildKeyValues(jsonObj51, "how_make_profit_txt"), "make links page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "url_error_msg"), "make links page");		

		//Here no need to scroll : 
		//Since the swiping happens from left to Right
		scrollFromDownToUpinApp();
		validateRequiredText_WithOutScrolling(retrieveChildKeyValues(jsonObj51, "step_one_title"), "make links page");
		validateRequiredText_WithOutScrolling(retrieveChildKeyValues(jsonObj51, "step_one_txt"), "make links page");
		validateRequiredText_WithOutScrolling(retrieveChildKeyValues(jsonObj51, "step_two_title"), "make links page");
		validateRequiredText_WithOutScrolling(getTestData(16, "makeProfitLinksStep1_1"), "make links page");
		validateRequiredText_WithOutScrolling(getTestData(16, "makeProfitLinksStep1_2"), "make links page");
		validateRequiredText_WithOutScrolling(getTestData(16, "makeProfitLinksStep1_3"), "make links page");
		validateRequiredText_WithOutScrolling(retrieveChildKeyValues(jsonObj51, "step_three_title"), "make links page");
		validateRequiredText_WithOutScrolling(getTestData(16, "makeProfitLinksStep2_1"), "make links page");
		validateRequiredText_WithOutScrolling(getTestData(16, "makeProfitLinksStep2_2"), "make links page");
		validateRequiredText_WithOutScrolling(retrieveChildKeyValues(jsonObj51, "step_four_title"), "make links page");
		validateRequiredText_WithOutScrolling(getTestData(16, "makeProfitLinksStep3_1"), "make links page");
		validateRequiredText_WithOutScrolling(getTestData(16, "makeProfitLinksStep3_2"), "make links page");
		validateRequiredText_WithOutScrolling(getTestData(16, "makeProfitLinksStep3_3"), "make links page");


	}



	@Test
	public void chooseLanguage_LanguageTamilValidation_Login() throws UnsupportedEncodingException, FileNotFoundException, IOException, ParseException {

		JSONObject jsonObj1 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "tamilFileName"), getTestData(16, "common_label"));
		JSONObject jsonObj2 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "tamilFileName"), getTestData(16, "common_validate_label"));
		JSONObject jsonObj17 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "tamilFileName"), getTestData(16, "profile_right_menu"));
		JSONObject jsonObj35 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "tamilFileName"), getTestData(16, "sign_in"));
		JSONObject jsonObj36 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "tamilFileName"), getTestData(16, "join_free"));

		reportStep("chooseLanguage_LanguageTamilValidation_GetStarted_Joinfree_Login_ForgotPassword started", "INFO");

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo);

		String textboxEmail = "//android.widget.EditText[@content-desc='et_signup_email']|//android.widget.EditText[@content-desc='et_signin_email']|//android.widget.EditText[@content-desc='et_forgotpassword_email']";
		String textboxPwd = "//android.widget.EditText[@content-desc='et_signup_password']|//android.widget.EditText[@content-desc='et_signin_password']";
		String emailAddress = objCashKaroUtility.generateEmail();
		String mobileNumber = objCashKaroUtility.generateMobileNumber();	

		String otpValue = new EKOnboardingPage(driver, testInfo).
				clickOnSignUpButton().
				enterFullName().
				enterEmail(emailAddress).
				enterPassword().
				enterMobileNumber(mobileNumber).
				clickOnGetOTPButton(mobileNumber);

		new OTPPage(driver, testInfo).
		enterOTP(otpValue).
		clickOnVerifyOTP();

		new HomePage(driver, testInfo).		
		clickOnProfileIconForSignedUser().
		clickOnChooseLanguage().
		clickTamilLink().
		clickContinueButton().
		clickRelaunchButton();

		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "profile_tab"), "home page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj17, "logout_menu"), "profile page");
		validateRequiredText(retrieveChildKeyValues(jsonObj17, "alert_logout"), "logout popup");
		validateRequiredText(retrieveChildKeyValues(jsonObj17, "alert_logout_msg"), "logout popup");
		validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj17, "alert_cancel_btn"), "logout popup");
		clickOnPartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj17, "alert_ok_btn"), "logout popup");

		new EKOnboardingPage(driver, testInfo).
		clickOnSignInbutton();

		validateRequiredText(retrieveChildKeyValues(jsonObj36, "sign_in_txt"), "login page");
		validateRequiredText(retrieveChildKeyValues(jsonObj35, "subtitle"), "login page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj35, "login_txt"), "login page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj35, "share_txt"), "login page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj35, "earn_txt"), "login page");
		validateRequiredText(retrieveChildKeyValues(jsonObj35, "subtitle"), "login page");
		validateRequiredText(retrieveChildKeyValues(jsonObj35, "sign_in_facebook_txt"), "login page");
		validateRequiredText(retrieveChildKeyValues(jsonObj35, "sign_in_or_txt"), "login page");
		validateRequiredText(retrieveChildKeyValues(jsonObj35, "email_inputfield_txt"), "login page");
		validateRequiredText(retrieveChildKeyValues(jsonObj35, "password_inputfield_txt"), "login page");
		validateRequiredText(retrieveChildKeyValues(jsonObj35, "forgot_password_txt"), "login page");
		validateRequiredText(retrieveChildKeyValues(jsonObj35, "sign_with_email_btn"), "login page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj35, "new_user_txt"), "login page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj35, "join_free_txt"), "login page");


		clickOnRequiredText(retrieveChildKeyValues(jsonObj35, "sign_with_email_btn"), "login page");
		validateRequiredText(retrieveChildKeyValues(jsonObj35, "email_inputfield_txt"), "login page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "email_error_msg"), "login page");
		validateRequiredText(retrieveChildKeyValues(jsonObj35, "password_inputfield_txt"), "login page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "password_error_msg"), "login page");

		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(textboxEmail);
		clickOnNoneOFTheAbove();
		enterTextByXpath(textboxEmail, getTestData(7, "oneUserChar"));
		enterTextByXpath(textboxPwd, getTestData(7, "oneUserChar"));
		clickOnRequiredText(retrieveChildKeyValues(jsonObj35, "sign_with_email_btn"), "login page");
		validateRequiredText(retrieveChildKeyValues(jsonObj35, "email_inputfield_txt"), "login page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "minimum_characters_error_msg"), "login page");
		validateRequiredText(retrieveChildKeyValues(jsonObj35, "password_inputfield_txt"), "login page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "password_minimum_characters_error_msg"), "login page");

		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(textboxEmail);
		clickOnNoneOFTheAbove();
		enterTextByXpath(textboxEmail, getTestData(7, "FiftyUserCharWithSpace"));
		enterTextByXpath(textboxPwd, getTestData(7, "FiftyUserChar"));
		clickOnRequiredText(retrieveChildKeyValues(jsonObj35, "sign_with_email_btn"), "login page");
		validateRequiredText(retrieveChildKeyValues(jsonObj35, "email_inputfield_txt"), "login page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "maximum_characters_error_msg"), "login page");
		validateRequiredText(retrieveChildKeyValues(jsonObj35, "password_inputfield_txt"), "login page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "password_maximum_characters_error_msg"), "login page");

		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(textboxEmail);
		clickOnNoneOFTheAbove();
		enterTextByXpath(textboxEmail, "2*2");
		enterTextByXpath(textboxPwd, "2 2 2 2 2");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj35, "sign_with_email_btn"), "login page");
		validateRequiredText(retrieveChildKeyValues(jsonObj35, "email_inputfield_txt"), "login page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "valid_email_error_msg"), "login page");
		validateRequiredText(retrieveChildKeyValues(jsonObj35, "password_inputfield_txt"), "login page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "valid_password_error_msg"), "login page");


	}

	@Test
	public void chooseLanguage_LanguageTamilValidation_ForgotPassword() throws UnsupportedEncodingException, FileNotFoundException, IOException, ParseException {

		JSONObject jsonObj1 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "tamilFileName"), getTestData(16, "common_label"));
		JSONObject jsonObj2 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "tamilFileName"), getTestData(16, "common_validate_label"));
		JSONObject jsonObj17 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "tamilFileName"), getTestData(16, "profile_right_menu"));
		JSONObject jsonObj35 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "tamilFileName"), getTestData(16, "sign_in"));
		JSONObject jsonObj37 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "tamilFileName"), getTestData(16, "forgot_password"));

		reportStep("chooseLanguage_LanguageTamilValidation_GetStarted_Joinfree_Login_ForgotPassword started", "INFO");

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo);

		String textboxEmail = "//android.widget.EditText[@content-desc='et_signup_email']|//android.widget.EditText[@content-desc='et_signin_email']|//android.widget.EditText[@content-desc='et_forgotpassword_email']";
		String emailAddress = objCashKaroUtility.generateEmail();
		String mobileNumber = objCashKaroUtility.generateMobileNumber();	

		String otpValue = new EKOnboardingPage(driver, testInfo).
				clickOnSignUpButton().
				enterFullName().
				enterEmail(emailAddress).
				enterPassword().
				enterMobileNumber(mobileNumber).
				clickOnGetOTPButton(mobileNumber);

		new OTPPage(driver, testInfo).
		enterOTP(otpValue).
		clickOnVerifyOTP();

		new HomePage(driver, testInfo).		
		clickOnProfileIconForSignedUser().
		clickOnChooseLanguage().
		clickTamilLink().
		clickContinueButton().
		clickRelaunchButton();

		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "profile_tab"), "home page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj17, "logout_menu"), "profile page");
		validateRequiredText(retrieveChildKeyValues(jsonObj17, "alert_logout"), "logout popup");
		validateRequiredText(retrieveChildKeyValues(jsonObj17, "alert_logout_msg"), "logout popup");
		validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj17, "alert_cancel_btn"), "logout popup");
		clickOnPartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj17, "alert_ok_btn"), "logout popup");

		new EKOnboardingPage(driver, testInfo).
		clickOnSignInbutton();


		clickOnRequiredText(retrieveChildKeyValues(jsonObj35, "forgot_password_txt"), "login page");
		validateRequiredText(retrieveChildKeyValues(jsonObj35, "forgot_password_txt"), "forgot password page");
		validateRequiredText(retrieveChildKeyValues(jsonObj37, "title"), "forgot password page");
		validateRequiredText(retrieveChildKeyValues(jsonObj37, "subtitle"), "forgot password page");
		validateRequiredText(retrieveChildKeyValues(jsonObj37, "email_inputfield_txt"), "forgot password page");
		validateRequiredText(retrieveChildKeyValues(jsonObj37, "password_link_txt"), "forgot password page");
		validateRequiredText(retrieveChildKeyValues(jsonObj37, "submit_btn"), "forgot password page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj37, "remember_password_txt"), "forgot password page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj37, "login_txt"), "forgot password page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj37, "submit_btn"), "forgot password page");
		validateRequiredText(retrieveChildKeyValues(jsonObj37, "email_inputfield_txt"), "forgot password page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "email_error_msg"), "login page");

		enterTextByXpath(textboxEmail, getTestData(7, "oneUserChar"));		
		clickOnRequiredText(retrieveChildKeyValues(jsonObj37, "submit_btn"), "forgot password page");
		validateRequiredText(retrieveChildKeyValues(jsonObj37, "email_inputfield_txt"), "forgot password page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "minimum_characters_error_msg"), "login page");

		enterTextByXpath(textboxEmail, getTestData(7, "FiftyUserCharWithSpace"));		
		clickOnRequiredText(retrieveChildKeyValues(jsonObj37, "submit_btn"), "forgot password page");
		validateRequiredText(retrieveChildKeyValues(jsonObj37, "email_inputfield_txt"), "forgot password page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "maximum_characters_error_msg"), "login page");

		enterTextByXpath(textboxEmail, "2*2");		
		clickOnRequiredText(retrieveChildKeyValues(jsonObj37, "submit_btn"), "forgot password page");
		validateRequiredText(retrieveChildKeyValues(jsonObj37, "email_inputfield_txt"), "forgot password page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "valid_email_error_msg"), "login page");


	}

	@Test
	public void chooseLanguage_LanguageTamilValidation_MyEarnings_History_Payment_ExistingUser() throws UnsupportedEncodingException, FileNotFoundException, IOException, ParseException {

		JSONObject jsonObj1 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "tamilFileName"), getTestData(16, "common_label"));
		JSONObject jsonObj2 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "tamilFileName"), getTestData(16, "common_validate_label"));
		JSONObject jsonObj14 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "tamilFileName"), getTestData(16, "search_result_all"));
		JSONObject jsonObj17 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "tamilFileName"), getTestData(16, "profile_right_menu"));
		JSONObject jsonObj28 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "tamilFileName"), getTestData(16, "my_account_earnings"));
		JSONObject jsonObj29 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "tamilFileName"), getTestData(16, "total_cashback_earned"));
		JSONObject jsonObj30 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "tamilFileName"), getTestData(16, "earnings_info"));		
		JSONObject jsonObj31 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "tamilFileName"), getTestData(16, "earnings_referral"));
		JSONObject jsonObj32 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "tamilFileName"), getTestData(16, "earnings_cashback"));
		JSONObject jsonObj33 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "tamilFileName"), getTestData(16, "my_earnings"));
		JSONObject jsonObj35 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "tamilFileName"), getTestData(16, "sign_in"));
		JSONObject jsonObj36 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "tamilFileName"), getTestData(16, "join_free"));
		JSONObject jsonObj40 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "tamilFileName"), getTestData(16, "giftcard_wallet_inputfield"));
		JSONObject jsonObj46 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "tamilFileName"), getTestData(16, "payment"));
		JSONObject jsonObj52 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "tamilFileName"), getTestData(16, "my_shared_activities"));
		JSONObject jsonObj53 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "tamilFileName"), getTestData(16, "giftcard_wallet_request_payment"));

		reportStep("chooseLanguage_LanguageTamilValidation_MyEarnings_History_Payment_ExistingUser started", "INFO");

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo);

		String dropDownPaymentSettings = "(//android.widget.EditText)[1]";
		String textNEFT = "//android.widget.CheckedTextView[@text='Bank Payment (NEFT)']";
		String textPaytmWallet = "//android.widget.CheckedTextView[@text='Paytm Wallet']";
		String textAmazonGiftCard = "//android.widget.CheckedTextView[@text='Amazon Gift Card']";
		String textBoxPaytmMobileNumberPaymentRequestPage = "//android.widget.EditText[@content-desc='et_requestpaymentPaytm_mobile']";
		String textBoxAmazonEmailPaymentRequestPage = "//android.widget.EditText[@content-desc='et_requestpaymentAmazon_email']";
		String linkEarningsInfoPaymentPage = "//android.widget.TextView[@content-desc='txt_payment_earningsInfo']";	
		String textboxName = "//android.widget.EditText[@content-desc='et_signup_fullname']";
		String textboxEmail = "//android.widget.EditText[@content-desc='et_signup_email']|//android.widget.EditText[@content-desc='et_signin_email']|//android.widget.EditText[@content-desc='et_forgotpassword_email']";
		String textboxPwd = "//android.widget.EditText[@content-desc='et_signup_password']|//android.widget.EditText[@content-desc='et_signin_password']";
		String textboxMobileNumber = "//android.widget.EditText[@content-desc='et_signup_mobile']";
		String searchIcon = "(//android.widget.TextView)[2]";
		String textAreaSearchIcon = "//android.widget.EditText[@content-desc='et_search_enterValue']";

		String cbStoreNameOne = CashKaroUtility.extractValuesFromStoreCategoryJsonAndLoadTheVariablesForStore("CB_Store_One", 0, "CB_Store_One_Name");
		String linkURL = CashKaroUtility.extractValuesFromStoreCategoryJsonAndLoadTheVariablesForStore("CB_Store_One", 0, "CB_Store_One_linkURL");

		String emailAddress = objCashKaroUtility.generateEmail();
		String mobileNumber = objCashKaroUtility.generateMobileNumber();	

		String emailAddressReferralJoinee = objCashKaroUtility.generateEmail();
		String mobileNumberReferralJoinee = objCashKaroUtility.generateMobileNumber();	

		String otpValue = new EKOnboardingPage(driver, testInfo).
				clickOnSignUpButton().
				enterFullName().
				enterEmail(emailAddress).
				enterPassword().
				enterMobileNumber(mobileNumber).
				clickOnGetOTPButton(mobileNumber);

		new OTPPage(driver, testInfo).
		enterOTP(otpValue).
		clickOnVerifyOTP();

		new HomePage(driver, testInfo).		
		clickOnProfileIconForSignedUser().
		clickOnChooseLanguage().
		clickTamilLink().
		clickContinueButton().
		clickRelaunchButton();

		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "profile_tab"), "home page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj17, "logout_menu"), "profile page");
		validateRequiredText(retrieveChildKeyValues(jsonObj17, "alert_logout"), "logout popup");
		validateRequiredText(retrieveChildKeyValues(jsonObj17, "alert_logout_msg"), "logout popup");
		validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj17, "alert_cancel_btn"), "logout popup");
		clickOnPartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj17, "alert_ok_btn"), "logout popup");

		new EKOnboardingPage(driver, testInfo);

		CashKaroUtility cu = new CashKaroUtility(udid, port, automationName, deviceName);
		driver = cu.launchChromeWebView(driver);

		AdminCoreFunction adminFunctions = new AdminCoreFunction(driver, testInfo);
		adminFunctions.naviagateAdminPage_ThenSelectEarnKaro();
		adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
		adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
		adminFunctions.clickSubmit();

		AdminUsersPage usersPage = new AdminUsersPage(driver, testInfo);
		adminFunctions.clickOnHamburgerMenu();
		adminFunctions.clickUsersMenu();
		adminFunctions.clickUsersSubMenu();
		usersPage.selectSearchByDropDown("User Email");
		usersPage.enterKeyword(emailAddress);
		usersPage.clickSubmitButton();
		String userId = usersPage.extractUserIdValueFromResultstableFirstRow();
		String userName = usersPage.extractUserNameValueFromResultstableFirstRow();

		CashKaroUtility reopenCKApp1 = new CashKaroUtility(udid, port, automationName, deviceName, systemPort);
		driver = reopenCKApp1.launchEarnKaroApp(driver);

		CashKaroUtility objcashKaroutility = new CashKaroUtility(udid, port, automationName, deviceName);
		AppiumDriver<MobileElement> driver2 = objcashKaroutility.launchChrome();
		driver = driver2;

		String url = Utilities.referralLinkGetCode(userId, userName);

		objcashKaroutility.closeAllTabsAndOneTabInChromeBrowser(driver);
		objcashKaroutility.clickAndEnterURLInSearchOrTypeWebAddress(driver, url);

		objcashKaroutility.clickJoinFreeLink(driver);

		enterTextByXpath(textboxName, "ReferralJoinee");
		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(textboxEmail);
		clickOnNoneOFTheAbove();
		enterTextByXpath(textboxEmail, emailAddressReferralJoinee);
		enterTextByXpath(textboxPwd, "ppounds");
		enterTextByXpath(textboxMobileNumber, mobileNumberReferralJoinee);
		clickOnRequiredText(retrieveChildKeyValues(jsonObj36, "otp_btn"), "join free page");	

		new OTPPage(driver, testInfo);

		String otp = Utilities.getSignUPOTP(mobileNumberReferralJoinee);

		new OTPPage(driver, testInfo).
		enterOTP(otp);

		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "verify_otp"), "otp page");	

		sleep(15000);

		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(searchIcon);
		hideKeyboard();
		//validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj13, "search_inputfield_txt"), "search page");
		enterTextByXpath(textAreaSearchIcon, cbStoreNameOne);
		pressEnter();
		validateRequiredText(retrieveChildKeyValues(jsonObj14, "offer_count_single_message"), "search page");
		validateRequiredText(retrieveChildKeyValues(jsonObj1, "add_to_share"), "search page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "share_now"), "search page");

		WhatsAppUtility whatsAppUtility = new WhatsAppUtility(driver, testInfo, udid, port, automationName, deviceName, systemPort);
		whatsAppUtility.clickOnWhatAppMenuItemSearch().
		enterWhatsAppSearchText("Mallik").
		clickOnRequiredContact().
		clickOnSend().
		clickOnSend().
		clickOnLastSharedLink();

		new IntermediateRetailerPage(driver, testInfo).
		validateRetailerPage(linkURL, linkURL);

		CashKaroUtility cu2 = new CashKaroUtility(udid, port, automationName, deviceName);
		cu = cu2;
		driver = cu.launchChromeWebView(driver);

		AdminCoreFunction adminFunctions2 = new AdminCoreFunction(driver, testInfo);
		adminFunctions = adminFunctions2;
		adminFunctions.naviagateAdminPage_ThenSelectEarnKaro();
		adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
		adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
		adminFunctions.clickSubmit();

		AdminReportsPage reportsPage = new AdminReportsPage(driver, testInfo);
		adminFunctions.clickOnHamburgerMenu();
		adminFunctions.clickOnReports();
		reportsPage.clickExitClickSubMenu();
		reportsPage.clickClear();
		reportsPage.selectSearchByDropDown("User Email");
		reportsPage.enterKeyword(emailAddressReferralJoinee);
		reportsPage.clickSubmit();

		String exitClickOne = reportsPage.extractRequiredExitIdUsingStoreNameExitClickType(cbStoreNameOne, getTestData(7, "Normal"));

		AdminCashbackPage ProfitPage = new AdminCashbackPage(driver, testInfo);
		adminFunctions.clickOnHamburgerMenu();
		adminFunctions.clickCashbackMenu();
		ProfitPage.clickCashbackSubMenu();

		ProfitPage.clickAddNewButton();
		ProfitPage.enterExitId(exitClickOne);
		ProfitPage.entertransactionId(exitClickOne);
		ProfitPage.enterOrderId(exitClickOne);
		ProfitPage.setOrderDate();
		ProfitPage.enterOrderValue("1000");
		ProfitPage.enterconfirmCommisionNetwork(getTestData(11, "EightHundered"));
		ProfitPage.entercashback("700");
		ProfitPage.selectCashbackTypeFromDropDown(getTestData(11, "Cashback"));
		ProfitPage.selectCashbackStatusFromDropDown(getTestData(11, "Pending"));
		ProfitPage.setPendingDate();
		ProfitPage.enterDetails(exitClickOne);
		ProfitPage.clickSubmit();
		ProfitPage.clickClear();

		CashKaroUtility reopenCKAppAgain = new CashKaroUtility(udid, port, automationName, deviceName, systemPort);
		driver = reopenCKAppAgain.launchEarnKaroApp(driver);
		new HomePage(driver, testInfo);

		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "profile_tab"), "home page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj17, "history_menu"), "profile page");
		validateRequiredText(retrieveChildKeyValues(jsonObj1, "recent_clicks_tab"), "history page");
		validateRequiredText(retrieveChildKeyValues(jsonObj52, "title"), "history page");
		validateRequiredText(retrieveChildKeyValues(jsonObj52, "shared_link_details"), "history page");
		validateRequiredText(retrieveChildKeyValues(jsonObj52, "shared_id"), "history page");
		validateRequiredText(retrieveChildKeyValues(jsonObj52, "shared_date_time"), "history page");
		validateRequiredText(retrieveChildKeyValues(jsonObj52, "retailer_name"), "history page");
		validateRequiredText(retrieveChildKeyValues(jsonObj52, "no_of_clicks"), "history page");
		validateRequiredText(retrieveChildKeyValues(jsonObj52, "no_of_transactions"), "history page");
		validateRequiredText(retrieveChildKeyValues(jsonObj52, "total_earning"), "history page");

		backButton();

		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "profile_tab"), "home page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj17, "my_earnings_menu"), "profile page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj29, "total_cashback_earned_txt"), "my earnings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj29, "total_cashback_earned_txt"), 1, "profit page");
		validateRequiredText(retrieveChildKeyValues(jsonObj29, "total_cashback_earned_txt"), 2, "profit page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj29, "paid_cashback_txt"), "profit page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj29, "pending_cashback_txt"), "profit page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj29, "referral_cashback_txt"), "profit page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj29, "available_cashback_txt"), "profit page");

		backButton();

		validateRequiredText(retrieveChildKeyValues(jsonObj32, "order_details"), "my earnings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj32, "date"), "my earnings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj32, "retailer"), "my earnings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj32, "order_amount"), "my earnings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj32, "cashback_amount"), "my earnings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj32, "status"), "my earnings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj32, "expected_date"), "my earnings page");

		backButton();

		clickOnRequiredText(retrieveChildKeyValues(jsonObj17, "logout_menu"), "profile page");
		validateRequiredText(retrieveChildKeyValues(jsonObj17, "alert_logout"), "logout popup");
		validateRequiredText(retrieveChildKeyValues(jsonObj17, "alert_logout_msg"), "logout popup");
		validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj17, "alert_cancel_btn"), "logout popup");
		clickOnPartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj17, "alert_ok_btn"), "logout popup");

		new EKOnboardingPage(driver, testInfo);

		CashKaroUtility cu3 = new CashKaroUtility(udid, port, automationName, deviceName);
		cu = cu3;
		driver = cu.launchChromeWebView(driver);

		AdminCoreFunction adminFunctions3 = new AdminCoreFunction(driver, testInfo);
		adminFunctions = adminFunctions3;
		adminFunctions.naviagateAdminPage_ThenSelectEarnKaro();
		adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
		adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
		adminFunctions.clickSubmit();

		AdminCashbackPage ProfitPage1 = new AdminCashbackPage(driver, testInfo);
		ProfitPage = ProfitPage1;
		adminFunctions.clickOnHamburgerMenu();
		adminFunctions.clickCashbackMenu();
		ProfitPage.clickCashbackSubMenu();

		ProfitPage.selectSearchByStatusFromDropDown("Exit ID");
		ProfitPage.enterKeyword(exitClickOne);
		ProfitPage.selectCashbackStatusFromDropDown(getTestData(11, "Pending"));
		ProfitPage.clickSubmit();
		ProfitPage.clickEdit();
		ProfitPage.selectCashbackStatusFromDropDown(getTestData(11, "Confirmed"));
		ProfitPage.setConfirmDate();
		ProfitPage.clickSubmit();
		ProfitPage.clickClear();

		CashKaroUtility reopenCKAppOnceAgain = new CashKaroUtility(udid, port, automationName, deviceName, systemPort);
		driver = reopenCKAppOnceAgain.launchEarnKaroApp(driver);

		new EKOnboardingPage(driver, testInfo).clickOnSignInbutton();

		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(textboxEmail);
		clickOnNoneOFTheAbove();
		enterTextByXpath(textboxEmail, emailAddress);
		enterTextByXpath(textboxPwd, "ppounds");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj35, "sign_with_email_btn"), "login page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "profile_tab"), "home page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj17, "my_earnings_menu"), "profile page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj33, "referral_tap"), "my earnings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj31, "order_details"), "my earnings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj31, "date"), "my earnings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj31, "referral_name"), "my earnings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj31, "cashback"), "my earnings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj31, "status"), "my earnings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj33, "refer_now_btn"), "my earnings page");

		backButton();

		clickOnRequiredText(retrieveChildKeyValues(jsonObj17, "logout_menu"), "profile page");
		validateRequiredText(retrieveChildKeyValues(jsonObj17, "alert_logout"), "logout popup");
		validateRequiredText(retrieveChildKeyValues(jsonObj17, "alert_logout_msg"), "logout popup");
		validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj17, "alert_cancel_btn"), "logout popup");
		clickOnPartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj17, "alert_ok_btn"), "logout popup");

		new EKOnboardingPage(driver, testInfo).clickOnSignInbutton();

		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(textboxEmail);
		clickOnNoneOFTheAbove();
		enterTextByXpath(textboxEmail, emailAddressReferralJoinee);
		enterTextByXpath(textboxPwd, "ppounds");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj35, "sign_with_email_btn"), "login page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "profile_tab"), "home page");
		validateRequiredText(retrieveChildKeyValues(jsonObj1, "profile_tab"), "profile page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj17, "hello_user_txt"), "profile page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj17, "total_cashback_txt"), "profile page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj17, "payment_menu"), "profile page");

		validateRequiredText(retrieveChildKeyValues(jsonObj17, "payment_menu"), "profile page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj29, "total_cashback_earned_txt"), "payments page");
		validateRequiredText(retrieveChildKeyValues(jsonObj29, "total_cashback_earned_txt"), 1, "profit page");
		validateRequiredText(retrieveChildKeyValues(jsonObj29, "total_cashback_earned_txt"), 2, "profit page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj29, "paid_cashback_txt"), "profit page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj29, "pending_cashback_txt"), "profit page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj29, "referral_cashback_txt"), "profit page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj29, "available_cashback_txt"), "profit page");

		backButton();

		validatePartialRequiredText(retrieveChildKeyValues(jsonObj28, "tap_to_see_details_txt"), "payments page");
		validateRequiredText(retrieveChildKeyValues(jsonObj46, "cashback_earnings"), "payments page");
		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(linkEarningsInfoPaymentPage);
		validateRequiredText(retrieveChildKeyValues(jsonObj30, "message_first_txt"), "earnings info page");


		backButton();

		validateRequiredText(retrieveChildKeyValues(jsonObj46, "request_cashback_btn"), "payments page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj46, "static_text_title"), "payments page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj46, "static_text_1"), "payments page");


		clickOnRequiredText(retrieveChildKeyValues(jsonObj46, "request_cashback_btn"), "payments page");

		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(dropDownPaymentSettings);
		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(textPaytmWallet);
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "mobile_inputfield_txt"), "payment request page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "get_paid_btn"), "payment req page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "mobile_inputfield_txt"), "payment request page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "mobile_number_error_msg"), "payment request page");

		enterTextByXpath(textBoxPaytmMobileNumberPaymentRequestPage, "1");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "get_paid_btn"), "payment req page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "mobile_inputfield_txt"), "payment request page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "minimum_digits_error_msg"), "payment request page");

		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(dropDownPaymentSettings);
		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(textAmazonGiftCard);

		validateRequiredText(retrieveChildKeyValues(jsonObj53, "email_inputfield_txt"), "payment request page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "get_paid_btn"), "payment req page");
		validateRequiredText(retrieveChildKeyValues(jsonObj53, "email_inputfield_txt"), "payment request page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "email_error_msg"), "payment request page");

		enterTextByXpath(textBoxAmazonEmailPaymentRequestPage, "1");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "get_paid_btn"), "payment req page");
		validateRequiredText(retrieveChildKeyValues(jsonObj53, "email_inputfield_txt"), "payment request page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "minimum_characters_error_msg"), "payment request page");

		enterTextByXpath(textBoxAmazonEmailPaymentRequestPage, "asdfghjkl");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "get_paid_btn"), "payment req page");
		validateRequiredText(retrieveChildKeyValues(jsonObj53, "email_inputfield_txt"), "payment request page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "valid_email_error_msg"), "payment request page");

		enterTextByXpath(textBoxAmazonEmailPaymentRequestPage, getTestData(7, "FiftyUserCharWithSpace"));
		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "get_paid_btn"), "payment req page");
		validateRequiredText(retrieveChildKeyValues(jsonObj53, "email_inputfield_txt"), "payment request page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "maximum_characters_error_msg"), "payment request page");

		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(dropDownPaymentSettings);
		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(textNEFT);

		validateRequiredText(retrieveChildKeyValues(jsonObj40, "bank_account_inputfield_txt"), "payment request page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "bank_name_inputfield_txt"), "payment request page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "branch_name_inputfield_txt"), "payment request page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "bank_account_number_inputfield_txt"), "payment request page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "bank_ifsc_code_inputfield_txt"), "payment request page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "get_paid_btn"), "payment req page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "bank_account_inputfield_txt"), "payment request page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "account_holder_error_msg"), "payment request page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "bank_name_inputfield_txt"), "payment request page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "valid_bank_name_error_msg"), "payment request page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "branch_name_inputfield_txt"), "payment request page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "branch_minimum_digits_error_msg"), "payment request page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "bank_account_number_inputfield_txt"), "payment request page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "account_number_error_msg"), "payment request page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "bank_ifsc_code_inputfield_txt"), "payment request page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "ifsc_code_error_msg"), "payment request page");


	}

	@Test
	public void chooseLanguage_LanguageTamilValidation_StoreCategory_StoreDetail() throws UnsupportedEncodingException, FileNotFoundException, IOException, ParseException {

		JSONObject jsonObj1 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "tamilFileName"), getTestData(16, "common_label"));
		JSONObject jsonObj3 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "tamilFileName"), getTestData(16, "main_menu"));
		JSONObject jsonObj5 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "tamilFileName"), getTestData(16, "store_category"));
		JSONObject jsonObj12 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "tamilFileName"), getTestData(16, "share_counts"));
		JSONObject jsonObj17 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "tamilFileName"), getTestData(16, "profile_right_menu"));

		reportStep("chooseLanguage_LanguageTamilValidation_Store_Product_StoreCategory started", "INFO");

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo);

		String shareButtonWithCount = "//android.view.ViewGroup[@content-desc='txt_sharecount_open_modal']/android.view.ViewGroup/android.widget.TextView[@text='1']";
		String imageStoreCard = "//android.widget.ImageView[@content-desc='img_storeCard_imageValue']";
		String emailAddress = objCashKaroUtility.generateEmail();
		String mobileNumber = objCashKaroUtility.generateMobileNumber();	

		String otpValue = new EKOnboardingPage(driver, testInfo).
				clickOnSignUpButton().
				enterFullName().
				enterEmail(emailAddress).
				enterPassword().
				enterMobileNumber(mobileNumber).
				clickOnGetOTPButton(mobileNumber);

		new OTPPage(driver, testInfo).
		enterOTP(otpValue).
		clickOnVerifyOTP();

		new HomePage(driver, testInfo).		
		clickOnProfileIconForSignedUser().
		clickOnChooseLanguage().
		clickTamilLink().
		clickContinueButton().
		clickRelaunchButton();

		validateRequiredText(retrieveChildKeyValues(jsonObj3, "home_lbl"), "home page");

		new HomePage(driver, testInfo).
		clickOnHamburgerIcon();

		validateRequiredText(retrieveChildKeyValues(jsonObj3, "home_lbl"), "home page");

		new SliderScreenPage(driver, testInfo).
		clickRetailerCategory().
		verifyRetailerCategoryMenuNavigation().
		clickAutomationSpecificCategoryInRetailerCategoryMenuUntilAllSubCategoryVisible().
		clickAutomationSpecificSubCategoryOne();

		validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj5, "popular"), "store category page");
		validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj5, "az"), "store category page");
		validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj5, "percent"), "store category page");
		validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj5, "amount"), "store category page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "add_to_share"), "store category page");
		validateRequiredText(retrieveChildKeyValues(jsonObj1, "share_now"), "store category page");
		validateRequiredText(retrieveChildKeyValues(jsonObj1, "remove"), "store category page");

		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(shareButtonWithCount);
		validateRequiredText(retrieveChildKeyValues(jsonObj1, "share_now"), "store category page");
		validateRequiredText(retrieveChildKeyValues(jsonObj1, "copy_Link"), "store category page");
		validateRequiredText(retrieveChildKeyValues(jsonObj12, "close"), "store category page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj12, "clear"), "store category page");

		validateRequiredText(retrieveChildKeyValues(jsonObj12, "clear"), "store category page");
		validateRequiredText(retrieveChildKeyValues(jsonObj12, "confirm_msg_clear_share"), "store category page");
		validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj17, "alert_cancel_btn"), "store category page");
		clickOnPartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj17, "alert_ok_btn"), "store category page");

		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(imageStoreCard);
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj1, "you_will_earn"), "store detail page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "add_to_share"), "store detail page");
		validateRequiredText(retrieveChildKeyValues(jsonObj1, "share_now"), "store detail page");
		validateRequiredText(retrieveChildKeyValues(jsonObj1, "remove"), "store category page");

		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(shareButtonWithCount);
		validateRequiredText(retrieveChildKeyValues(jsonObj1, "share_now"), "store detail page");
		validateRequiredText(retrieveChildKeyValues(jsonObj1, "copy_Link"), "store detail page");
		validateRequiredText(retrieveChildKeyValues(jsonObj12, "close"), "store detail page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj12, "clear"), "store detail page");

		validateRequiredText(retrieveChildKeyValues(jsonObj12, "clear"), "store detail page");
		validateRequiredText(retrieveChildKeyValues(jsonObj12, "confirm_msg_clear_share"), "store detail page");
		validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj17, "alert_cancel_btn"), "store detail page");
		clickOnPartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj17, "alert_ok_btn"), "store detail page");

		new StoreDetailPage(driver, testInfo);

	}

	@Test
	public void chooseLanguage_LanguageTamilValidation_Product() throws UnsupportedEncodingException, FileNotFoundException, IOException, ParseException {

		JSONObject jsonObj1 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "tamilFileName"), getTestData(16, "common_label"));
		JSONObject jsonObj3 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "tamilFileName"), getTestData(16, "main_menu"));
		JSONObject jsonObj6 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "tamilFileName"), getTestData(16, "prduct_category"));
		JSONObject jsonObj9 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "tamilFileName"), getTestData(16, "product_details"));
		JSONObject jsonObj10 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "tamilFileName"), getTestData(16, "product_card"));
		JSONObject jsonObj49 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "tamilFileName"), getTestData(16, "prduct_filter"));

		reportStep("chooseLanguage_LanguageTamilValidation_Store_Product_StoreCategory started", "INFO");

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo);

		String menFashionCategory = "//android.view.ViewGroup[@content-desc='txt_mainMenu_productDealsTop']";
		String tshirtCategory = "//android.widget.TextView[@content-desc='txt_subMenuProduct_mainMenuName']";
		String menfashionLink = "//android.widget.TextView[@content-desc='txt_filter_category_name']";
		String imageProductCard = "//android.widget.ImageView[@content-desc='img_product_image']";
		String productcard = "(//android.widget.ImageView)[3]";		
		String emailAddress = objCashKaroUtility.generateEmail();
		String mobileNumber = objCashKaroUtility.generateMobileNumber();	

		String otpValue = new EKOnboardingPage(driver, testInfo).
				clickOnSignUpButton().
				enterFullName().
				enterEmail(emailAddress).
				enterPassword().
				enterMobileNumber(mobileNumber).
				clickOnGetOTPButton(mobileNumber);

		new OTPPage(driver, testInfo).
		enterOTP(otpValue).
		clickOnVerifyOTP();

		new HomePage(driver, testInfo).		
		clickOnProfileIconForSignedUser().
		clickOnChooseLanguage().
		clickTamilLink().
		clickContinueButton().
		clickRelaunchButton();

		validateRequiredText(retrieveChildKeyValues(jsonObj3, "home_lbl"), "home page");

		new HomePage(driver, testInfo).
		clickOnHamburgerIcon();

		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(menFashionCategory);
		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(tshirtCategory);

		clickOnPartialRequiredText(retrieveChildKeyValues(jsonObj6, "filter"), "product page");
		validateRequiredText(retrieveChildKeyValues(jsonObj49, "filter_by"), "product page");
		validateRequiredText(retrieveChildKeyValues(jsonObj49, "clerar_all"), "product page");
		validateRequiredText(retrieveChildKeyValues(jsonObj49, "close_btn"), "product page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj49, "apply_btn"), "product page");

		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(menfashionLink);
		clickOnRequiredText(retrieveChildKeyValues(jsonObj49, "clerar_all"), "product page");
		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(menfashionLink);
		clickOnRequiredText(retrieveChildKeyValues(jsonObj49, "close_btn"), "product page");
		validateRequiredText(retrieveChildKeyValues(jsonObj49, "discard_changes_title"), "product page");
		validateRequiredText(retrieveChildKeyValues(jsonObj49, "discard_changes_msg"), "product page");
		validateRequiredText(retrieveChildKeyValues(jsonObj49, "apply_changes_btn"), "product page");
		validateRequiredText(retrieveChildKeyValues(jsonObj49, "discard_changes_btn"), "product page");

		backButton();

		clickOnPartialRequiredText(retrieveChildKeyValues(jsonObj6, "sort"), "product page");
		validateRequiredText(retrieveChildKeyValues(jsonObj6, "sort_title"), "product page");
		validateRequiredText(retrieveChildKeyValues(jsonObj6, "sort_popular"), "product page");
		validateRequiredText(retrieveChildKeyValues(jsonObj6, "sort_discount"), "product page");
		validateRequiredText(retrieveChildKeyValues(jsonObj6, "sort_high_price"), "product page");
		validateRequiredText(retrieveChildKeyValues(jsonObj6, "sort_low_price"), "product page");

		backButton();

		validatePartialRequiredText(retrieveChildKeyValues(jsonObj10, "brand"), "product page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj10, "price"), "product page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj10, "sale"), "product page");
		validateRequiredText(retrieveChildKeyValues(jsonObj1, "add_to_share"), "product page");
		validateRequiredText(retrieveChildKeyValues(jsonObj1, "share_now"), "product page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj10, "see_details"), "product page");

		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(imageProductCard);
		clickOnRequiredText(retrieveChildKeyValues(jsonObj9, "seller_price"), "product detail page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "you_will_earn"), "product detail page");
		validateRequiredText(retrieveChildKeyValues(jsonObj1, "add_to_share"), "product page");
		validateRequiredText(retrieveChildKeyValues(jsonObj1, "share_now"), "product page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj9, "product_highlights"), "product detail page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj9, "similar_products"), "product detail page");



	}

	@Test
	public void chooseLanguage_LanguageHindiValidation_SuccessStories() throws UnsupportedEncodingException, FileNotFoundException, IOException, ParseException {

		JSONObject jsonObj1 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "hindiFileName"), getTestData(16, "common_label"));
		JSONObject jsonObj4 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "hindiFileName"), getTestData(16, "home"));
		JSONObject jsonObj17 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "hindiFileName"), getTestData(16, "profile_right_menu"));
		JSONObject jsonObj22 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "hindiFileName"), getTestData(16, "success_stories"));

		reportStep("chooseLanguage_LanguageHindiValidation_SuccessStories started", "INFO");

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo);

		String emailAddress = objCashKaroUtility.generateEmail();
		String mobileNumber = objCashKaroUtility.generateMobileNumber();	

		String otpValue = new EKOnboardingPage(driver, testInfo).
				clickOnSignUpButton().
				enterFullName().
				enterEmail(emailAddress).
				enterPassword().
				enterMobileNumber(mobileNumber).
				clickOnGetOTPButton(mobileNumber);

		new OTPPage(driver, testInfo).
		enterOTP(otpValue).
		clickOnVerifyOTP();

		new HomePage(driver, testInfo).		
		clickOnProfileIconForSignedUser().
		clickOnChooseLanguage().
		clickHindiLink().
		clickContinueButton().
		clickRelaunchButton();

		validateRequiredText(retrieveChildKeyValues(jsonObj4, "dynamic_home_top_Category"), "home page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "profile_tab"), "home page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj17, "success_stories_menu"), "profile page");
		validateRequiredText(retrieveChildKeyValues(jsonObj17, "success_stories_menu"), "success stories page");
		scrollFromUpToDowninApp();scrollFromUpToDowninApp();
		validateRequiredText(retrieveChildKeyValues(jsonObj22, "title"), "success stories page");
		validateRequiredText(retrieveChildKeyValues(jsonObj22, "testimonial_txt1"), "success stories page");
		validateRequiredText(retrieveChildKeyValues(jsonObj22, "testimonial_name1"), "success stories page");
		validateRequiredText(retrieveChildKeyValues(jsonObj22, "testimonial_txt2"), "success stories page");
		validateRequiredText(retrieveChildKeyValues(jsonObj22, "testimonial_name2"), "success stories page");
		validateRequiredText(retrieveChildKeyValues(jsonObj22, "testimonial_txt3"), "success stories page");
		validateRequiredText(retrieveChildKeyValues(jsonObj22, "testimonial_name3"), "success stories page");
		validateRequiredText(retrieveChildKeyValues(jsonObj22, "testimonial_txt4"), "success stories page");
		validateRequiredText(retrieveChildKeyValues(jsonObj22, "testimonial_name4"), "success stories page");
		validateRequiredText(retrieveChildKeyValues(jsonObj22, "testimonial_txt5"), "success stories page");
		validateRequiredText(retrieveChildKeyValues(jsonObj22, "testimonial_name5"), "success stories page");


	}

	@Test
	public void chooseLanguage_LanguageHindiValidation_HowItWorks() throws UnsupportedEncodingException, FileNotFoundException, IOException, ParseException {

		JSONObject jsonObj1 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "hindiFileName"), getTestData(16, "common_label"));
		JSONObject jsonObj4 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "hindiFileName"), getTestData(16, "home"));
		JSONObject jsonObj17 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "hindiFileName"), getTestData(16, "profile_right_menu"));
		JSONObject jsonObj23 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "hindiFileName"), getTestData(16, "how_it_works"));

		reportStep("chooseLanguage_LanguageHindiValidation_HowItWorks started", "INFO");

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo);

		String emailAddress = objCashKaroUtility.generateEmail();
		String mobileNumber = objCashKaroUtility.generateMobileNumber();	

		String otpValue = new EKOnboardingPage(driver, testInfo).
				clickOnSignUpButton().
				enterFullName().
				enterEmail(emailAddress).
				enterPassword().
				enterMobileNumber(mobileNumber).
				clickOnGetOTPButton(mobileNumber);

		new OTPPage(driver, testInfo).
		enterOTP(otpValue).
		clickOnVerifyOTP();

		new HomePage(driver, testInfo).		
		clickOnProfileIconForSignedUser().
		clickOnChooseLanguage().
		clickHindiLink().
		clickContinueButton().
		clickRelaunchButton();

		validateRequiredText(retrieveChildKeyValues(jsonObj4, "dynamic_home_top_Category"), "home page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "profile_tab"), "home page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj17, "how_it_works_menu"), "profile page");
		scrollFromUpToDowninApp();scrollFromUpToDowninApp();
		validateRequiredText(retrieveChildKeyValues(jsonObj17, "how_it_works_menu"), "how earnkaro works? page");
		validateRequiredText(retrieveChildKeyValues(jsonObj23, "watch_video_title1"), "how earnkaro works? page");
		validateRequiredText(retrieveChildKeyValues(jsonObj23, "watch_video_txt"), 1, "how earnkaro works? page");
		validateRequiredText(retrieveChildKeyValues(jsonObj23, "watch_video_title2"), "how earnkaro works? page");
		validateRequiredText(retrieveChildKeyValues(jsonObj23, "watch_video_txt"), 2, "how earnkaro works? page");
		validateRequiredText(retrieveChildKeyValues(jsonObj23, "watch_video_title3"), "how earnkaro works? page");
		validateRequiredText(retrieveChildKeyValues(jsonObj23, "watch_video_txt"), 3, "how earnkaro works? page");
		validateRequiredText(retrieveChildKeyValues(jsonObj23, "watch_video_title4"), "how earnkaro works? page");
		validateRequiredText(retrieveChildKeyValues(jsonObj23, "watch_video_txt"), 4, "how earnkaro works? page");
		validateRequiredText(retrieveChildKeyValues(jsonObj23, "watch_video_title5"), "how earnkaro works? page");
		validateRequiredText(retrieveChildKeyValues(jsonObj23, "watch_video_txt"), 5, "how earnkaro works? page");
		validateRequiredText(retrieveChildKeyValues(jsonObj23, "watch_video_title6"), "how earnkaro works? page");
		validateRequiredText(retrieveChildKeyValues(jsonObj23, "watch_video_txt"), 6, "how earnkaro works? page");



	}

	@Test
	public void chooseLanguage_LanguageHindiValidation_Testimonials() throws UnsupportedEncodingException, FileNotFoundException, IOException, ParseException {

		JSONObject jsonObj1 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "hindiFileName"), getTestData(16, "common_label"));
		JSONObject jsonObj4 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "hindiFileName"), getTestData(16, "home"));
		JSONObject jsonObj17 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "hindiFileName"), getTestData(16, "profile_right_menu"));
		JSONObject jsonObj24 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "hindiFileName"), getTestData(16, "testimonial"));
		JSONObject jsonObj25 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "hindiFileName"), getTestData(16, "testimonial_write"));

		reportStep("chooseLanguage_LanguageHindiValidation_Testimonials started", "INFO");

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo);

		String fourStarTestimonials = "(//android.widget.TextView)[8]";
		String threeStarTestimonials = "(//android.widget.TextView)[7]";
		String twoStarTestimonials = "(//android.widget.TextView)[6]";
		String oneStarTestimonials = "(//android.widget.TextView)[5]";

		String emailAddress = objCashKaroUtility.generateEmail();
		String mobileNumber = objCashKaroUtility.generateMobileNumber();	

		String otpValue = new EKOnboardingPage(driver, testInfo).
				clickOnSignUpButton().
				enterFullName().
				enterEmail(emailAddress).
				enterPassword().
				enterMobileNumber(mobileNumber).
				clickOnGetOTPButton(mobileNumber);

		new OTPPage(driver, testInfo).
		enterOTP(otpValue).
		clickOnVerifyOTP();

		new HomePage(driver, testInfo).		
		clickOnProfileIconForSignedUser().
		clickOnChooseLanguage().
		clickHindiLink().
		clickContinueButton().
		clickRelaunchButton();

		validateRequiredText(retrieveChildKeyValues(jsonObj4, "dynamic_home_top_Category"), "home page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "profile_tab"), "home page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj17, "rate_us_menu"), "profile page");
		validateRequiredText(retrieveChildKeyValues(jsonObj24, "count_txt"), "testimonials page");
		validateRequiredText(retrieveChildKeyValues(jsonObj24, "happy_users_txt"), "testimonials page");

		clickOnPartialRequiredText(retrieveChildKeyValues(jsonObj24, "write_testimonial_btn"), "testimonials page");
		validateRequiredText(retrieveChildKeyValues(jsonObj24, "write_testimonial_btn"), "write testimonials page");
		validateRequiredText(retrieveChildKeyValues(jsonObj25, "title"), "write testimonials page");

		validateRequiredText(retrieveChildKeyValues(jsonObj25, "rate_experience_txt"), "write testimonials page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj25, "star_txt_awesome_experience"), "write testimonials page");
		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(fourStarTestimonials);
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj25, "star_txt_good_experience"), "write testimonials page");
		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(threeStarTestimonials);
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj25, "star_txt_average"), "write testimonials page");
		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(twoStarTestimonials);
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj25, "star_txt_not_great"), "write testimonials page");
		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(oneStarTestimonials);
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj25, "star_txt_terrible"), "write testimonials page");


	}

	@Test
	public void chooseLanguage_LanguageHindiValidation_ContactUs() throws UnsupportedEncodingException, FileNotFoundException, IOException, ParseException {

		JSONObject jsonObj1 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "hindiFileName"), getTestData(16, "common_label"));
		JSONObject jsonObj2 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "hindiFileName"), getTestData(16, "common_validate_label"));
		JSONObject jsonObj4 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "hindiFileName"), getTestData(16, "home"));
		JSONObject jsonObj17 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "hindiFileName"), getTestData(16, "profile_right_menu"));
		JSONObject jsonObj20 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "hindiFileName"), getTestData(16, "contact_us"));		
		JSONObject jsonObj21 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "hindiFileName"), getTestData(16, "faq"));

		reportStep("chooseLanguage_LanguageHindiValidation_ContactUs started", "INFO");

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo);

		String textBoxUserName = "//android.widget.EditText[@content-desc='et_contactUs_yourName']";
		String textBoxEmail = "//android.widget.EditText[@content-desc='et_contactUs_email']";

		String emailAddress = objCashKaroUtility.generateEmail();
		String mobileNumber = objCashKaroUtility.generateMobileNumber();	

		String otpValue = new EKOnboardingPage(driver, testInfo).
				clickOnSignUpButton().
				enterFullName().
				enterEmail(emailAddress).
				enterPassword().
				enterMobileNumber(mobileNumber).
				clickOnGetOTPButton(mobileNumber);

		new OTPPage(driver, testInfo).
		enterOTP(otpValue).
		clickOnVerifyOTP();

		new HomePage(driver, testInfo).		
		clickOnProfileIconForSignedUser().
		clickOnChooseLanguage().
		clickHindiLink().
		clickContinueButton().
		clickRelaunchButton();

		validateRequiredText(retrieveChildKeyValues(jsonObj4, "dynamic_home_top_Category"), "home page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "profile_tab"), "home page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj17, "help"), "profile page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj17, "contact_us_menu"), "help page");
		enterTextByXpath(textBoxUserName, "..");
		enterTextByXpath(textBoxEmail, "..");
		validateRequiredText(retrieveChildKeyValues(jsonObj21, "faq_btn"), "contact us page");
		validateRequiredText(retrieveChildKeyValues(jsonObj20, "name_inputfield_txt"), "contact us page");
		validateRequiredText(retrieveChildKeyValues(jsonObj20, "email_inputfield_txt"), "contact us page");
		validateRequiredText(retrieveChildKeyValues(jsonObj20, "message_inputfield_txt"), "contact us page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj20, "submit_btn"), "contact us page");

		validateRequiredText(retrieveChildKeyValues(jsonObj2, "select_subject_error_msg"), "contact us page");
		validateRequiredText(retrieveChildKeyValues(jsonObj20, "name_inputfield_txt"), "contact us page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "valid_name_error_msg"), "contact us page");
		validateRequiredText(retrieveChildKeyValues(jsonObj20, "email_inputfield_txt"), "contact us page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "valid_email_error_msg"), "contact us page");
		validateRequiredText(retrieveChildKeyValues(jsonObj20, "message_inputfield_txt"), "contact us page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "valid_details_error_msg"), "contact us page");

		enterTextByXpath(textBoxUserName, getTestData(7, "oneUserChar"));
		enterTextByXpath(textBoxEmail, getTestData(7, "oneUserChar"));
		clickOnRequiredText(retrieveChildKeyValues(jsonObj20, "submit_btn"), "contact us page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "select_subject_error_msg"), "contact us page");
		validateRequiredText(retrieveChildKeyValues(jsonObj20, "name_inputfield_txt"), "contact us page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "minimum_characters_error_msg"), 1, "contact us page");
		validateRequiredText(retrieveChildKeyValues(jsonObj20, "email_inputfield_txt"), "contact us page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "minimum_characters_error_msg"), 2, "contact us page");
		validateRequiredText(retrieveChildKeyValues(jsonObj20, "message_inputfield_txt"), "contact us page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "valid_details_error_msg"), "contact us page");

		enterTextByXpath(textBoxUserName, getTestData(7, "FiftyPlusUserChar"));
		enterTextByXpath(textBoxEmail, getTestData(7, "FiftyPlusUserChar"));
		clickOnRequiredText(retrieveChildKeyValues(jsonObj20, "submit_btn"), "contact us page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "select_subject_error_msg"), "contact us page");
		validateRequiredText(retrieveChildKeyValues(jsonObj20, "name_inputfield_txt"), "contact us page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "maximum_characters_error_msg"), 1, "contact us page");
		validateRequiredText(retrieveChildKeyValues(jsonObj20, "email_inputfield_txt"), "contact us page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "maximum_characters_error_msg"), 2, "contact us page");
		validateRequiredText(retrieveChildKeyValues(jsonObj20, "message_inputfield_txt"), "contact us page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "valid_details_error_msg"), "contact us page");

	}

	@Test
	public void chooseLanguage_LanguageHindiValidation_History_NewUser() throws UnsupportedEncodingException, FileNotFoundException, IOException, ParseException {

		JSONObject jsonObj1 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "hindiFileName"), getTestData(16, "common_label"));
		JSONObject jsonObj4 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "hindiFileName"), getTestData(16, "home"));
		JSONObject jsonObj17 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "hindiFileName"), getTestData(16, "profile_right_menu"));
		JSONObject jsonObj52 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "hindiFileName"), getTestData(16, "my_shared_activities"));

		reportStep("chooseLanguage_LanguageHindiValidation_History_NewUser started", "INFO");

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo);

		String emailAddress = objCashKaroUtility.generateEmail();
		String mobileNumber = objCashKaroUtility.generateMobileNumber();	

		String otpValue = new EKOnboardingPage(driver, testInfo).
				clickOnSignUpButton().
				enterFullName().
				enterEmail(emailAddress).
				enterPassword().
				enterMobileNumber(mobileNumber).
				clickOnGetOTPButton(mobileNumber);

		new OTPPage(driver, testInfo).
		enterOTP(otpValue).
		clickOnVerifyOTP();

		new HomePage(driver, testInfo).		
		clickOnProfileIconForSignedUser().
		clickOnChooseLanguage().
		clickHindiLink().
		clickContinueButton().
		clickRelaunchButton();

		validateRequiredText(retrieveChildKeyValues(jsonObj4, "dynamic_home_top_Category"), "home page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "profile_tab"), "home page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj17, "history_menu"), "profile page");
		validateRequiredText(retrieveChildKeyValues(jsonObj17, "recent_clicks_menu"), "history page");
		validateRequiredText(retrieveChildKeyValues(jsonObj1, "whoops_msg"), "history page");
		validateRequiredText(retrieveChildKeyValues(jsonObj52, "no_shared_activites"), "history page");
		validateRequiredText(retrieveChildKeyValues(jsonObj52, "saving_click_away"), "history page");


	}

	@Test
	public void chooseLanguage_LanguageHindiValidation_AccountSettings() throws UnsupportedEncodingException, FileNotFoundException, IOException, ParseException {

		JSONObject jsonObj1 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "hindiFileName"), getTestData(16, "common_label"));
		JSONObject jsonObj2 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "hindiFileName"), getTestData(16, "common_validate_label"));
		JSONObject jsonObj17 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "hindiFileName"), getTestData(16, "profile_right_menu"));
		JSONObject jsonObj26 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "hindiFileName"), getTestData(16, "personal_details"));
		JSONObject jsonObj27 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "hindiFileName"), getTestData(16, "change_password"));
		JSONObject jsonObj40 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "hindiFileName"), getTestData(16, "giftcard_wallet_inputfield"));

		reportStep("chooseLanguage_LanguageHindiValidation_AccountSettings started", "INFO");

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo);

		String textBoxExistingPwd = "//android.widget.EditText[@content-desc='et_changePassword_currentPassword']";
		String textBoxNewPwd = "//android.widget.EditText[@content-desc='et_changePassword_fullName']";
		String textBoxConfirmNewPwd = "//android.widget.EditText[@content-desc='et_changePassword_confirmPassword']";

		String dropDownPaymentSettings = "(//android.widget.EditText)[1]";
		String textNEFT = "//android.widget.CheckedTextView[@text='Bank Payment (NEFT)']";
		String textPaytmWallet = "//android.widget.CheckedTextView[@text='Paytm Wallet']";
		String textAmazonGiftCard = "//android.widget.CheckedTextView[@text='Amazon Gift Card']";

		String textBoxPaytmMobileNumber = "//android.widget.EditText[@content-desc='et_settingsPaytm_mobile']";
		String textBoxPaytmPassword = "//android.widget.EditText[@content-desc='et_settingsPaytm_password']";

		String textBoxAmazonEmail = "//android.widget.EditText[@content-desc='et_settingsAmazon_email']";
		String textBoxAmazonPassword = "//android.widget.EditText[@content-desc='et_settingsAmazon_password']";
		String emailAddress = objCashKaroUtility.generateEmail();
		String mobileNumber = objCashKaroUtility.generateMobileNumber();	

		String otpValue = new EKOnboardingPage(driver, testInfo).
				clickOnSignUpButton().
				enterFullName().
				enterEmail(emailAddress).
				enterPassword().
				enterMobileNumber(mobileNumber).
				clickOnGetOTPButton(mobileNumber);

		new OTPPage(driver, testInfo).
		enterOTP(otpValue).
		clickOnVerifyOTP();

		new HomePage(driver, testInfo).		
		clickOnProfileIconForSignedUser().
		clickOnChooseLanguage().
		clickHindiLink().
		clickContinueButton().
		clickRelaunchButton();

		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "profile_tab"), "home page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj17, "account_setting_menu"), "profile page");
		validateRequiredText(retrieveChildKeyValues(jsonObj17, "account_setting_menu"), "profile page");
		clickOnPartialRequiredText(retrieveChildKeyValues(jsonObj1, "personal_details_tab"), "account settings page");
		scrollFromUpToDowninApp();scrollFromUpToDowninApp();
		validateRequiredText(retrieveChildKeyValues(jsonObj26, "name_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj26, "email_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj26, "mobile_number_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj26, "receive_newsletter_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj26, "receive_referral_earning_txt"), "account settings page");
		validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj26, "note_txt"), "account settings page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "save_changes_btn"), "account settings page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj26, "no_value_change_error_msg"), "account settings page");

		clickOnPartialRequiredText(retrieveChildKeyValues(jsonObj1, "change_password_tab"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj27, "change_password_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj27, "new_password_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj27, "confirm_password_inputfield_txt"), "account settings page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "save_changes_btn"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj27, "change_password_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "current_password_error_msg"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj27, "new_password_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "new_password_error_msg"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj27, "confirm_password_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "confirm_password_error_msg"), "account settings page");

		enterTextByXpath(textBoxExistingPwd, getTestData(7, "oneUserChar"));
		enterTextByXpath(textBoxNewPwd, getTestData(7, "oneUserChar"));
		enterTextByXpath(textBoxConfirmNewPwd, getTestData(7, "oneUserChar"));
		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "save_changes_btn"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj27, "change_password_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "password_minimum_characters_error_msg"), 1, "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj27, "new_password_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "password_minimum_characters_error_msg"), 2, "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj27, "confirm_password_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "password_minimum_characters_error_msg"), 3, "account settings page");

		enterTextByXpath(textBoxExistingPwd, getTestData(7, "FiftyUserChar"));
		enterTextByXpath(textBoxNewPwd, getTestData(7, "FiftyUserChar"));
		enterTextByXpath(textBoxConfirmNewPwd, getTestData(7, "FiftyUserChar"));
		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "save_changes_btn"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj27, "change_password_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "password_maximum_characters_error_msg"), 1, "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj27, "new_password_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "password_maximum_characters_error_msg"), 2, "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj27, "confirm_password_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "password_maximum_characters_error_msg"), 3, "account settings page");


		enterTextByXpath(textBoxExistingPwd, getTestData(0, "TC001_ValidLoginPassword"));
		enterTextByXpath(textBoxNewPwd, getTestData(0, "TC001_ValidLoginPassword"));
		enterTextByXpath(textBoxConfirmNewPwd, getTestData(0, "TC001_InValidPassword"));
		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "save_changes_btn"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj27, "change_password_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj27, "new_password_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj27, "confirm_password_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "password_not_match_error_msg"), "account settings page");

		clickOnPartialRequiredText(retrieveChildKeyValues(jsonObj1, "payment_settings_tab"), "account settings page");

		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(dropDownPaymentSettings);
		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(textPaytmWallet);
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "mobile_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "confirm_password_inputfield_txt"), "account settings page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "save_changes_btn"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "mobile_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "mobile_number_error_msg"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "confirm_password_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "password_error_msg"), "account settings page");

		enterTextByXpath(textBoxPaytmMobileNumber, "1");
		enterTextByXpath(textBoxPaytmPassword, "1");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "save_changes_btn"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "mobile_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "minimum_digits_error_msg"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "confirm_password_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "password_minimum_characters_error_msg"), "account settings page");

		enterTextByXpath(textBoxPaytmPassword, getTestData(7, "FiftyUserChar"));
		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "save_changes_btn"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "confirm_password_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "password_maximum_characters_error_msg"), "account settings page");

		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(dropDownPaymentSettings);
		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(textAmazonGiftCard);

		validateRequiredText(retrieveChildKeyValues(jsonObj40, "email_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "confirm_password_inputfield_txt"), "account settings page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "save_changes_btn"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "email_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "email_error_msg"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "confirm_password_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "password_error_msg"), "account settings page");

		enterTextByXpath(textBoxAmazonEmail, "1");
		enterTextByXpath(textBoxAmazonPassword, "1");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "save_changes_btn"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "email_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "minimum_characters_error_msg"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "confirm_password_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "password_minimum_characters_error_msg"), "account settings page");

		enterTextByXpath(textBoxAmazonEmail, "asdfghjkl");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "save_changes_btn"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "email_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "valid_email_error_msg"), "account settings page");

		enterTextByXpath(textBoxAmazonEmail, getTestData(7, "FiftyUserCharWithSpace"));
		enterTextByXpath(textBoxAmazonPassword, getTestData(7, "FiftyUserChar"));
		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "save_changes_btn"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "email_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "maximum_characters_error_msg"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "confirm_password_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "maximum_characters_error_msg"), "account settings page");

		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(dropDownPaymentSettings);
		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(textNEFT);

		validateRequiredText(retrieveChildKeyValues(jsonObj40, "bank_account_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "bank_name_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "branch_name_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "bank_account_number_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "bank_ifsc_code_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "confirm_password_inputfield_txt"), "account settings page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "save_changes_btn"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "bank_account_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "account_holder_error_msg"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "bank_name_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "valid_bank_name_error_msg"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "branch_name_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "branch_minimum_digits_error_msg"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "bank_account_number_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "account_number_error_msg"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "bank_ifsc_code_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "ifsc_code_error_msg"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "confirm_password_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "password_error_msg"), "account settings page");

	}

	@Test
	public void chooseLanguage_LanguageHindiValidation_ReferAndEarn() throws UnsupportedEncodingException, FileNotFoundException, IOException, ParseException {

		JSONObject jsonObj1 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "hindiFileName"), getTestData(16, "common_label"));
		JSONObject jsonObj17 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "hindiFileName"), getTestData(16, "profile_right_menu"));
		JSONObject jsonObj18 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "hindiFileName"), getTestData(16, "referandearn"));

		reportStep("chooseLanguage_LanguageHindiValidation_AccountSettings started", "INFO");

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo);

		String emailAddress = objCashKaroUtility.generateEmail();
		String mobileNumber = objCashKaroUtility.generateMobileNumber();	

		String otpValue = new EKOnboardingPage(driver, testInfo).
				clickOnSignUpButton().
				enterFullName().
				enterEmail(emailAddress).
				enterPassword().
				enterMobileNumber(mobileNumber).
				clickOnGetOTPButton(mobileNumber);

		new OTPPage(driver, testInfo).
		enterOTP(otpValue).
		clickOnVerifyOTP();

		new HomePage(driver, testInfo).		
		clickOnProfileIconForSignedUser().
		clickOnChooseLanguage().
		clickHindiLink().
		clickContinueButton().
		clickRelaunchButton();

		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "profile_tab"), "home page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj17, "my_referral_menu"), "profile page");
		validateRequiredText(retrieveChildKeyValues(jsonObj17, "my_referral_menu"), "my referral page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj17, "referandearn_menu"), "my referral page");
		validateRequiredText(retrieveChildKeyValues(jsonObj17, "referandearn_menu"), "my referral page");
		scrollFromUpToDowninApp();scrollFromUpToDowninApp();
		validateRequiredText(retrieveChildKeyValues(jsonObj18, "title"), "my referral page");
		validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj18, "sub_title"), "my referral page");
		validateRequiredText(retrieveChildKeyValues(jsonObj18, "message"), "my referral page");
		validateRequiredText(retrieveChildKeyValues(jsonObj18, "referral_link_title"), "my referral page");
		validateRequiredText(retrieveChildKeyValues(jsonObj18, "copy_message_title"), "my referral page");
		validateRequiredText(retrieveChildKeyValues(jsonObj18, "tap_copy"), "my referral page");
		validateRequiredText(retrieveChildKeyValues(jsonObj18, "invite_title"), "my referral page");


	}

	@Test
	public void chooseLanguage_LanguageHindiValidation_ReferralNetwork() throws UnsupportedEncodingException, FileNotFoundException, IOException, ParseException {

		JSONObject jsonObj1 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "hindiFileName"), getTestData(16, "common_label"));
		JSONObject jsonObj17 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "hindiFileName"), getTestData(16, "profile_right_menu"));
		JSONObject jsonObj19 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "hindiFileName"), getTestData(16, "referral_network"));

		reportStep("chooseLanguage_LanguageHindiValidation_AccountSettings started", "INFO");

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo);

		String emailAddress = objCashKaroUtility.generateEmail();
		String mobileNumber = objCashKaroUtility.generateMobileNumber();	

		String otpValue = new EKOnboardingPage(driver, testInfo).
				clickOnSignUpButton().
				enterFullName().
				enterEmail(emailAddress).
				enterPassword().
				enterMobileNumber(mobileNumber).
				clickOnGetOTPButton(mobileNumber);

		new OTPPage(driver, testInfo).
		enterOTP(otpValue).
		clickOnVerifyOTP();

		new HomePage(driver, testInfo).		
		clickOnProfileIconForSignedUser().
		clickOnChooseLanguage().
		clickHindiLink().
		clickContinueButton().
		clickRelaunchButton();

		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "profile_tab"), "home page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj17, "my_referral_menu"), "profile page");
		validateRequiredText(retrieveChildKeyValues(jsonObj17, "my_referral_menu"), "my referral page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj17, "referral_network_menu"), "profile page");
		validateRequiredText(retrieveChildKeyValues(jsonObj17, "referral_network_menu"), "referral nw page");
		validateRequiredText(retrieveChildKeyValues(jsonObj19, "title"), "referral nw page");
		validateRequiredText(retrieveChildKeyValues(jsonObj19, "total_referral_earn_txt"), "referral nw page");
		validateRequiredText(retrieveChildKeyValues(jsonObj19, "friend_joined_txt"), "referral nw page");
		validateRequiredText(retrieveChildKeyValues(jsonObj19, "no_referral_title"), "referral nw page");
		validateRequiredText(retrieveChildKeyValues(jsonObj19, "no_referral_message"), "referral nw page");
		validateRequiredText(retrieveChildKeyValues(jsonObj19, "refer_earn_btn"), "referral nw page");


	}

	@Test
	public void chooseLanguage_LanguageHindiValidation_MyEarnings_NewUser() throws UnsupportedEncodingException, FileNotFoundException, IOException, ParseException {

		JSONObject jsonObj1 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "hindiFileName"), getTestData(16, "common_label"));
		JSONObject jsonObj4 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "hindiFileName"), getTestData(16, "home"));
		JSONObject jsonObj17 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "hindiFileName"), getTestData(16, "profile_right_menu"));
		JSONObject jsonObj28 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "hindiFileName"), getTestData(16, "my_account_earnings"));
		JSONObject jsonObj30 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "hindiFileName"), getTestData(16, "earnings_info"));

		JSONObject jsonObj33 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "hindiFileName"), getTestData(16, "my_earnings"));

		reportStep("chooseLanguage_LanguageHindiValidation_MyEarnings_NewUser started", "INFO");

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo);

		String linkEarningsInfo = "//android.widget.TextView[@content-desc='txt_myEarnings_cashbackEarningsInfo']";

		String emailAddress = objCashKaroUtility.generateEmail();
		String mobileNumber = objCashKaroUtility.generateMobileNumber();	

		String otpValue = new EKOnboardingPage(driver, testInfo).
				clickOnSignUpButton().
				enterFullName().
				enterEmail(emailAddress).
				enterPassword().
				enterMobileNumber(mobileNumber).
				clickOnGetOTPButton(mobileNumber);

		new OTPPage(driver, testInfo).
		enterOTP(otpValue).
		clickOnVerifyOTP();

		new HomePage(driver, testInfo).		
		clickOnProfileIconForSignedUser().
		clickOnChooseLanguage().
		clickHindiLink().
		clickContinueButton().
		clickRelaunchButton();

		validateRequiredText(retrieveChildKeyValues(jsonObj4, "dynamic_home_top_Category"), "home page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "profile_tab"), "home page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj17, "my_earnings_menu"), "profile page");
		validateRequiredText(retrieveChildKeyValues(jsonObj17, "my_earnings_menu"), "my earnings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj28, "total_earning_txt"), "my earnings page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj33, "cashback_tap"), "my earnings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj33, "cashback_earning_title"), "my earnings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj33, "request_cashback_btn"), "my earnings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj33, "no_cashback_yet_txt"), "my earnings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj33, "shop_today_txt"), "my earnings page");

		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(linkEarningsInfo);
		validateRequiredText(retrieveChildKeyValues(jsonObj30, "message_first_txt"), "earnings info page");


		backButton();

		clickOnRequiredText(retrieveChildKeyValues(jsonObj33, "referral_tap"), "my earnings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj33, "referral_earning_title"), "my earnings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj33, "request_cashback_btn"), "my earnings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj33, "no_referral_yet_txt"), "my earnings page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj33, "no_referral_yet_sub_txt"), "my earnings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj33, "no_referral_invite_some_friends"), "my earnings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj33, "refer_now_btn"), "my earnings page");


	}

	@Test
	public void chooseLanguage_LanguageHindiValidation_GetStarted_Joinfree_TermsAndConditions() throws UnsupportedEncodingException, FileNotFoundException, IOException, ParseException {

		JSONObject jsonObj1 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "hindiFileName"), getTestData(16, "common_label"));
		JSONObject jsonObj2 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "hindiFileName"), getTestData(16, "common_validate_label"));
		JSONObject jsonObj17 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "hindiFileName"), getTestData(16, "profile_right_menu"));
		JSONObject jsonObj34 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "hindiFileName"), getTestData(16, "on_boarding"));
		JSONObject jsonObj36 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "hindiFileName"), getTestData(16, "join_free"));
		JSONObject jsonObj50 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "hindiFileName"), getTestData(16, "terms_conditions"));

		reportStep("chooseLanguage_LanguageHindiValidation_GetStarted_Joinfree_Login_ForgotPassword started", "INFO");

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo);

		String textboxName = "//android.widget.EditText[@content-desc='et_signup_fullname']";
		String textboxEmail = "//android.widget.EditText[@content-desc='et_signup_email']|//android.widget.EditText[@content-desc='et_signin_email']|//android.widget.EditText[@content-desc='et_forgotpassword_email']";
		String textboxPwd = "//android.widget.EditText[@content-desc='et_signup_password']|//android.widget.EditText[@content-desc='et_signin_password']";
		String textboxMobileNumber = "//android.widget.EditText[@content-desc='et_signup_mobile']";		
		String emailAddress = objCashKaroUtility.generateEmail();
		String mobileNumber = objCashKaroUtility.generateMobileNumber();	

		String otpValue = new EKOnboardingPage(driver, testInfo).
				clickOnSignUpButton().
				enterFullName().
				enterEmail(emailAddress).
				enterPassword().
				enterMobileNumber(mobileNumber).
				clickOnGetOTPButton(mobileNumber);

		new OTPPage(driver, testInfo).
		enterOTP(otpValue).
		clickOnVerifyOTP();

		new HomePage(driver, testInfo).		
		clickOnProfileIconForSignedUser().
		clickOnChooseLanguage().
		clickHindiLink().
		clickContinueButton().
		clickRelaunchButton();

		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "profile_tab"), "home page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj17, "logout_menu"), "profile page");
		validateRequiredText(retrieveChildKeyValues(jsonObj17, "alert_logout"), "logout popup");
		validateRequiredText(retrieveChildKeyValues(jsonObj17, "alert_logout_msg"), "logout popup");
		validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj17, "alert_cancel_btn"), "logout popup");
		clickOnPartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj17, "alert_ok_btn"), "logout popup");

		new EKOnboardingPage(driver, testInfo).
		clickOnSignUpButton();

		validateRequiredText(retrieveChildKeyValues(jsonObj17, "join_free_menu"), "join free page");
		scrollFromUpToDowninApp();
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "title"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "subtitle"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "join_facebook_txt"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "join_or_txt"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "name_inputfield_txt"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "email_inputfield_txt"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "password_inputfield_txt"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "mobile_inputfield_txt"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "otp_btn"), "join free page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj36, "already_register"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "sign_in_txt"), "join free page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj36, "signup_terms_txt"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "terms_condition_txt"), "join free page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj36, "otp_btn"), "join free page");		
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "name_inputfield_txt"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "name_error_msg"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "email_inputfield_txt"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "email_error_msg"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "password_inputfield_txt"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "password_error_msg"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "mobile_inputfield_txt"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "mobile_number_error_msg"), "join free page");

		enterTextByXpath(textboxName, getTestData(7, "oneUserChar"));
		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(textboxEmail);
		clickOnNoneOFTheAbove();
		enterTextByXpath(textboxEmail, getTestData(7, "oneUserChar"));
		enterTextByXpath(textboxPwd, getTestData(7, "oneUserChar"));
		enterTextByXpath(textboxMobileNumber, getTestData(7, "oneUserChar"));
		clickOnRequiredText(retrieveChildKeyValues(jsonObj36, "otp_btn"), "join free page");		
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "name_inputfield_txt"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "minimum_characters_error_msg"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "email_inputfield_txt"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "minimum_characters_error_msg"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "password_inputfield_txt"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "password_minimum_characters_error_msg"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "mobile_inputfield_txt"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "minimum_digits_error_msg"), "join free page");

		enterTextByXpath(textboxName, getTestData(7, "FiftyUserCharWithSpace"));
		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(textboxEmail);
		clickOnNoneOFTheAbove();
		enterTextByXpath(textboxEmail, getTestData(7, "FiftyUserCharWithSpace"));
		enterTextByXpath(textboxPwd, getTestData(7, "FiftyUserChar"));
		enterTextByXpath(textboxMobileNumber, "2222222222");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj36, "otp_btn"), "join free page");		
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "name_inputfield_txt"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "maximum_characters_error_msg"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "email_inputfield_txt"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "maximum_characters_error_msg"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "password_inputfield_txt"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "password_maximum_characters_error_msg"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "mobile_inputfield_txt"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "valid_mobile_number_error_msg"), "join free page");

		enterTextByXpath(textboxName, "2*2");
		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(textboxEmail);
		clickOnNoneOFTheAbove();
		enterTextByXpath(textboxEmail, "2*2");
		enterTextByXpath(textboxPwd, "2 2 2 2 2");
		enterTextByXpath(textboxMobileNumber, "2*22222222");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj36, "otp_btn"), "join free page");		
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "name_inputfield_txt"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "valid_name_error_msg"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "email_inputfield_txt"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "valid_email_error_msg"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "password_inputfield_txt"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "valid_password_error_msg"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "mobile_inputfield_txt"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "valid_mobile_number_error_msg"), "join free page");

		backButton();

		new EKOnboardingPage(driver, testInfo).
		clickOnSignUpButton();
		clickOnRequiredText(retrieveChildKeyValues(jsonObj36, "terms_condition_txt"), "join free page");
		scrollFromUpToDowninApp();scrollFromUpToDowninApp();
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "terms_condition_txt"), "terms & conditions page");
		validateRequiredText(retrieveChildKeyValues(jsonObj50, "title"), "terms & conditions page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj50, "introduction_title"), "terms & conditions page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj50, "introduction_details"), "terms & conditions page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj50, "membership_title"), "terms & conditions page");
		validateRequiredText(retrieveChildKeyValues(jsonObj50, "membership_details"), "terms & conditions page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj50, "our_service_title"), "terms & conditions page");
		validateRequiredText(retrieveChildKeyValues(jsonObj50, "our_service_details"), "terms & conditions page");


	}

	@Test
	public void chooseLanguage_LanguageHindiValidation_MakeLinks_NewUser() throws UnsupportedEncodingException, FileNotFoundException, IOException, ParseException {

		JSONObject jsonObj1 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "hindiFileName"), getTestData(16, "common_label"));
		JSONObject jsonObj2 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "hindiFileName"), getTestData(16, "common_validate_label"));
		JSONObject jsonObj4 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "hindiFileName"), getTestData(16, "home"));
		JSONObject jsonObj17 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "hindiFileName"), getTestData(16, "profile_right_menu"));
		JSONObject jsonObj51 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "hindiFileName"), getTestData(16, "make_profit_links"));
		JSONObject jsonObj53 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "hindiFileName"), getTestData(16, "profit_partners_details"));
		JSONObject jsonObj54 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "hindiFileName"), getTestData(16, "profit_partners_list"));

		reportStep("chooseLanguage_LanguageHindiValidation_MakeLinks_NewUser started", "INFO");

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo);

		String imagePartner = "//android.widget.ImageView[@content-desc='img_profitPartnersList_partnersIMG']";
		String emailAddress = objCashKaroUtility.generateEmail();
		String mobileNumber = objCashKaroUtility.generateMobileNumber();	

		String otpValue = new EKOnboardingPage(driver, testInfo).
				clickOnSignUpButton().
				enterFullName().
				enterEmail(emailAddress).
				enterPassword().
				enterMobileNumber(mobileNumber).
				clickOnGetOTPButton(mobileNumber);

		new OTPPage(driver, testInfo).
		enterOTP(otpValue).
		clickOnVerifyOTP();

		new HomePage(driver, testInfo).		
		clickOnProfileIconForSignedUser().
		clickOnChooseLanguage().
		clickHindiLink().
		clickContinueButton().
		clickRelaunchButton();

		validateRequiredText(retrieveChildKeyValues(jsonObj4, "dynamic_home_top_Category"), "home page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "profile_tab"), "home page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj17, "make_profit_link_menu"), "profile page");
		validateRequiredText(retrieveChildKeyValues(jsonObj51, "title"), "make links page");
		validateRequiredText(retrieveChildKeyValues(jsonObj51, "subtitle"), "make links page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj51, "see_partner_txt"), "make links page");
		validateRequiredText(retrieveChildKeyValues(jsonObj54, "toolbar_profit_partners_title"), "partners page");
		validateRequiredText(retrieveChildKeyValues(jsonObj54, "profit_partners_information"), "partners page");

		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(imagePartner);
		validateRequiredText(retrieveChildKeyValues(jsonObj53, "profit_rates"), "store page");

		backButton();
		backButton();

		validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj51, "paste_link"), "make links page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj51, "make_profit_link_txt"), "make links page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "url_error_msg"), "make links page");
		validateRequiredText(retrieveChildKeyValues(jsonObj51, "how_make_profit_txt"), "make links page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "url_error_msg"), "make links page");		
		validateRequiredText(retrieveChildKeyValues(jsonObj51, "step_one_title"), "make links page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj51, "step_one_txt"), "make links page");
		validateRequiredText(retrieveChildKeyValues(jsonObj51, "step_two_title"), "make links page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj51, "step_two_txt"), "make links page");
		validateRequiredText(retrieveChildKeyValues(jsonObj51, "step_three_title"), "make links page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj51, "step_three_txt"), "make links page");
		validateRequiredText(retrieveChildKeyValues(jsonObj51, "step_four_title"), "make links page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj51, "step_four_txt"), "make links page");


	}

	@Test
	public void chooseLanguage_LanguageHindiValidation_FAQs() throws UnsupportedEncodingException, FileNotFoundException, IOException, ParseException {

		JSONObject jsonObj1 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "hindiFileName"), getTestData(16, "common_label"));
		JSONObject jsonObj4 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "hindiFileName"), getTestData(16, "home"));
		JSONObject jsonObj17 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "hindiFileName"), getTestData(16, "profile_right_menu"));		
		JSONObject jsonObj21 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "hindiFileName"), getTestData(16, "faq"));

		reportStep("chooseLanguage_LanguageHindiValidation_FAQs started", "INFO");

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo);

		String emailAddress = objCashKaroUtility.generateEmail();
		String mobileNumber = objCashKaroUtility.generateMobileNumber();	

		String otpValue = new EKOnboardingPage(driver, testInfo).
				clickOnSignUpButton().
				enterFullName().
				enterEmail(emailAddress).
				enterPassword().
				enterMobileNumber(mobileNumber).
				clickOnGetOTPButton(mobileNumber);

		new OTPPage(driver, testInfo).
		enterOTP(otpValue).
		clickOnVerifyOTP();

		new HomePage(driver, testInfo).		
		clickOnProfileIconForSignedUser().
		clickOnChooseLanguage().
		clickHindiLink().
		clickContinueButton().
		clickRelaunchButton();

		validateRequiredText(retrieveChildKeyValues(jsonObj4, "dynamic_home_top_Category"), "home page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "profile_tab"), "home page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj17, "help"), "profile page");

		validateRequiredText(retrieveChildKeyValues(jsonObj17, "call_us_menu"), "help page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj17, "faq_menu"), "help page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj21, "question1"), "faq page");
		//validateRequiredText(retrieveChildKeyValues(jsonObj21, "answer1"), "faq page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj21, "question2"), "faq page");
		//validateRequiredText(retrieveChildKeyValues(jsonObj21, "answer2"), "faq page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj21, "question3"), "faq page");
		//validateRequiredText(retrieveChildKeyValues(jsonObj21, "answer3"), "faq page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj21, "question4"), "faq page");
		//validateRequiredText(retrieveChildKeyValues(jsonObj21, "answer4"), "faq page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj21, "question5"), "faq page");
		//validateRequiredText(retrieveChildKeyValues(jsonObj21, "answer5"), "faq page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj21, "question6"), "faq page");
		//validateRequiredText(retrieveChildKeyValues(jsonObj21, "answer6"), "faq page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj21, "question7"), "faq page");
		//validateRequiredText(retrieveChildKeyValues(jsonObj21, "answer7"), "faq page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj21, "question8"), "faq page");
		//validateRequiredText(retrieveChildKeyValues(jsonObj21, "answer8"), "faq page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj21, "question9"), "faq page");
		//validateRequiredText(retrieveChildKeyValues(jsonObj21, "answer9"), "faq page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj21, "question10"), "faq page");
		//validateRequiredText(retrieveChildKeyValues(jsonObj21, "answer10"), "faq page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj21, "question11"), "faq page");
		//validateRequiredText(retrieveChildKeyValues(jsonObj21, "answer11"), "faq page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj21, "question12"), "faq page");
		//validateRequiredText(retrieveChildKeyValues(jsonObj21, "answer12"), "faq page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj21, "question13"), "faq page");
		//validateRequiredText(retrieveChildKeyValues(jsonObj21, "answer13"), "faq page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj21, "question14"), "faq page");
		//validateRequiredText(retrieveChildKeyValues(jsonObj21, "answer14"), "faq page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj21, "question15"), "faq page");
		//validateRequiredText(retrieveChildKeyValues(jsonObj21, "answer15"), "faq page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj21, "question16"), "faq page");
		//validateRequiredText(retrieveChildKeyValues(jsonObj21, "answer16"), "faq page");

		validateRequiredText(retrieveChildKeyValues(jsonObj21, "faq_btn"), "faq page");

	}
	
	@Test
	public void chooseLanguage_LanguageHindiValidation_Login() throws UnsupportedEncodingException, FileNotFoundException, IOException, ParseException {

		JSONObject jsonObj1 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "hindiFileName"), getTestData(16, "common_label"));
		JSONObject jsonObj2 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "hindiFileName"), getTestData(16, "common_validate_label"));
		JSONObject jsonObj17 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "hindiFileName"), getTestData(16, "profile_right_menu"));
		JSONObject jsonObj35 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "hindiFileName"), getTestData(16, "sign_in"));
		JSONObject jsonObj36 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "hindiFileName"), getTestData(16, "join_free"));

		reportStep("chooseLanguage_LanguageHindiValidation_GetStarted_Joinfree_Login_ForgotPassword started", "INFO");

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo);

		String textboxEmail = "//android.widget.EditText[@content-desc='et_signup_email']|//android.widget.EditText[@content-desc='et_signin_email']|//android.widget.EditText[@content-desc='et_forgotpassword_email']";
		String textboxPwd = "//android.widget.EditText[@content-desc='et_signup_password']|//android.widget.EditText[@content-desc='et_signin_password']";
		String emailAddress = objCashKaroUtility.generateEmail();
		String mobileNumber = objCashKaroUtility.generateMobileNumber();	

		String otpValue = new EKOnboardingPage(driver, testInfo).
				clickOnSignUpButton().
				enterFullName().
				enterEmail(emailAddress).
				enterPassword().
				enterMobileNumber(mobileNumber).
				clickOnGetOTPButton(mobileNumber);

		new OTPPage(driver, testInfo).
		enterOTP(otpValue).
		clickOnVerifyOTP();

		new HomePage(driver, testInfo).		
		clickOnProfileIconForSignedUser().
		clickOnChooseLanguage().
		clickHindiLink().
		clickContinueButton().
		clickRelaunchButton();

		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "profile_tab"), "home page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj17, "logout_menu"), "profile page");
		validateRequiredText(retrieveChildKeyValues(jsonObj17, "alert_logout"), "logout popup");
		validateRequiredText(retrieveChildKeyValues(jsonObj17, "alert_logout_msg"), "logout popup");
		validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj17, "alert_cancel_btn"), "logout popup");
		clickOnPartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj17, "alert_ok_btn"), "logout popup");

		new EKOnboardingPage(driver, testInfo).
		clickOnSignInbutton();

		validateRequiredText(retrieveChildKeyValues(jsonObj36, "sign_in_txt"), "login page");
		validateRequiredText(retrieveChildKeyValues(jsonObj35, "subtitle"), "login page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj35, "login_txt"), "login page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj35, "share_txt"), "login page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj35, "earn_txt"), "login page");
		validateRequiredText(retrieveChildKeyValues(jsonObj35, "subtitle"), "login page");
		validateRequiredText(retrieveChildKeyValues(jsonObj35, "sign_in_facebook_txt"), "login page");
		validateRequiredText(retrieveChildKeyValues(jsonObj35, "sign_in_or_txt"), "login page");
		validateRequiredText(retrieveChildKeyValues(jsonObj35, "email_inputfield_txt"), "login page");
		validateRequiredText(retrieveChildKeyValues(jsonObj35, "password_inputfield_txt"), "login page");
		validateRequiredText(retrieveChildKeyValues(jsonObj35, "forgot_password_txt"), "login page");
		validateRequiredText(retrieveChildKeyValues(jsonObj35, "sign_with_email_btn"), "login page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj35, "new_user_txt"), "login page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj35, "join_free_txt"), "login page");


		clickOnRequiredText(retrieveChildKeyValues(jsonObj35, "sign_with_email_btn"), "login page");
		validateRequiredText(retrieveChildKeyValues(jsonObj35, "email_inputfield_txt"), "login page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "email_error_msg"), "login page");
		validateRequiredText(retrieveChildKeyValues(jsonObj35, "password_inputfield_txt"), "login page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "password_error_msg"), "login page");

		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(textboxEmail);
		clickOnNoneOFTheAbove();
		enterTextByXpath(textboxEmail, getTestData(7, "oneUserChar"));
		enterTextByXpath(textboxPwd, getTestData(7, "oneUserChar"));
		clickOnRequiredText(retrieveChildKeyValues(jsonObj35, "sign_with_email_btn"), "login page");
		validateRequiredText(retrieveChildKeyValues(jsonObj35, "email_inputfield_txt"), "login page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "minimum_characters_error_msg"), "login page");
		validateRequiredText(retrieveChildKeyValues(jsonObj35, "password_inputfield_txt"), "login page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "password_minimum_characters_error_msg"), "login page");

		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(textboxEmail);
		clickOnNoneOFTheAbove();
		enterTextByXpath(textboxEmail, getTestData(7, "FiftyUserCharWithSpace"));
		enterTextByXpath(textboxPwd, getTestData(7, "FiftyUserChar"));
		clickOnRequiredText(retrieveChildKeyValues(jsonObj35, "sign_with_email_btn"), "login page");
		validateRequiredText(retrieveChildKeyValues(jsonObj35, "email_inputfield_txt"), "login page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "maximum_characters_error_msg"), "login page");
		validateRequiredText(retrieveChildKeyValues(jsonObj35, "password_inputfield_txt"), "login page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "password_maximum_characters_error_msg"), "login page");

		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(textboxEmail);
		clickOnNoneOFTheAbove();
		enterTextByXpath(textboxEmail, "2*2");
		enterTextByXpath(textboxPwd, "2 2 2 2 2");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj35, "sign_with_email_btn"), "login page");
		validateRequiredText(retrieveChildKeyValues(jsonObj35, "email_inputfield_txt"), "login page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "valid_email_error_msg"), "login page");
		validateRequiredText(retrieveChildKeyValues(jsonObj35, "password_inputfield_txt"), "login page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "valid_password_error_msg"), "login page");


	}

	@Test
	public void chooseLanguage_LanguageHindiValidation_ForgotPassword() throws UnsupportedEncodingException, FileNotFoundException, IOException, ParseException {

		JSONObject jsonObj1 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "hindiFileName"), getTestData(16, "common_label"));
		JSONObject jsonObj2 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "hindiFileName"), getTestData(16, "common_validate_label"));
		JSONObject jsonObj17 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "hindiFileName"), getTestData(16, "profile_right_menu"));
		JSONObject jsonObj35 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "hindiFileName"), getTestData(16, "sign_in"));
		JSONObject jsonObj37 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "hindiFileName"), getTestData(16, "forgot_password"));

		reportStep("chooseLanguage_LanguageHindiValidation_GetStarted_Joinfree_Login_ForgotPassword started", "INFO");

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo);

		String textboxEmail = "//android.widget.EditText[@content-desc='et_signup_email']|//android.widget.EditText[@content-desc='et_signin_email']|//android.widget.EditText[@content-desc='et_forgotpassword_email']";
		String emailAddress = objCashKaroUtility.generateEmail();
		String mobileNumber = objCashKaroUtility.generateMobileNumber();	

		String otpValue = new EKOnboardingPage(driver, testInfo).
				clickOnSignUpButton().
				enterFullName().
				enterEmail(emailAddress).
				enterPassword().
				enterMobileNumber(mobileNumber).
				clickOnGetOTPButton(mobileNumber);

		new OTPPage(driver, testInfo).
		enterOTP(otpValue).
		clickOnVerifyOTP();

		new HomePage(driver, testInfo).		
		clickOnProfileIconForSignedUser().
		clickOnChooseLanguage().
		clickHindiLink().
		clickContinueButton().
		clickRelaunchButton();

		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "profile_tab"), "home page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj17, "logout_menu"), "profile page");
		validateRequiredText(retrieveChildKeyValues(jsonObj17, "alert_logout"), "logout popup");
		validateRequiredText(retrieveChildKeyValues(jsonObj17, "alert_logout_msg"), "logout popup");
		validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj17, "alert_cancel_btn"), "logout popup");
		clickOnPartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj17, "alert_ok_btn"), "logout popup");

		new EKOnboardingPage(driver, testInfo).
		clickOnSignInbutton();


		clickOnRequiredText(retrieveChildKeyValues(jsonObj35, "forgot_password_txt"), "login page");
		validateRequiredText(retrieveChildKeyValues(jsonObj35, "forgot_password_txt"), "forgot password page");
		validateRequiredText(retrieveChildKeyValues(jsonObj37, "title"), "forgot password page");
		validateRequiredText(retrieveChildKeyValues(jsonObj37, "subtitle"), "forgot password page");
		validateRequiredText(retrieveChildKeyValues(jsonObj37, "email_inputfield_txt"), "forgot password page");
		validateRequiredText(retrieveChildKeyValues(jsonObj37, "password_link_txt"), "forgot password page");
		validateRequiredText(retrieveChildKeyValues(jsonObj37, "submit_btn"), "forgot password page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj37, "remember_password_txt"), "forgot password page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj37, "login_txt"), "forgot password page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj37, "submit_btn"), "forgot password page");
		validateRequiredText(retrieveChildKeyValues(jsonObj37, "email_inputfield_txt"), "forgot password page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "email_error_msg"), "login page");

		enterTextByXpath(textboxEmail, getTestData(7, "oneUserChar"));		
		clickOnRequiredText(retrieveChildKeyValues(jsonObj37, "submit_btn"), "forgot password page");
		validateRequiredText(retrieveChildKeyValues(jsonObj37, "email_inputfield_txt"), "forgot password page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "minimum_characters_error_msg"), "login page");

		enterTextByXpath(textboxEmail, getTestData(7, "FiftyUserCharWithSpace"));		
		clickOnRequiredText(retrieveChildKeyValues(jsonObj37, "submit_btn"), "forgot password page");
		validateRequiredText(retrieveChildKeyValues(jsonObj37, "email_inputfield_txt"), "forgot password page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "maximum_characters_error_msg"), "login page");

		enterTextByXpath(textboxEmail, "2*2");		
		clickOnRequiredText(retrieveChildKeyValues(jsonObj37, "submit_btn"), "forgot password page");
		validateRequiredText(retrieveChildKeyValues(jsonObj37, "email_inputfield_txt"), "forgot password page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "valid_email_error_msg"), "login page");


	}

	@Test
	public void chooseLanguage_LanguageHindiValidation_MyEarnings_History_Payment_ExistingUser() throws UnsupportedEncodingException, FileNotFoundException, IOException, ParseException {

		JSONObject jsonObj1 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "hindiFileName"), getTestData(16, "common_label"));
		JSONObject jsonObj2 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "hindiFileName"), getTestData(16, "common_validate_label"));
		JSONObject jsonObj14 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "hindiFileName"), getTestData(16, "search_result_all"));
		JSONObject jsonObj17 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "hindiFileName"), getTestData(16, "profile_right_menu"));
		JSONObject jsonObj28 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "hindiFileName"), getTestData(16, "my_account_earnings"));
		JSONObject jsonObj29 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "hindiFileName"), getTestData(16, "total_cashback_earned"));
		JSONObject jsonObj30 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "hindiFileName"), getTestData(16, "earnings_info"));		
		JSONObject jsonObj31 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "hindiFileName"), getTestData(16, "earnings_referral"));
		JSONObject jsonObj32 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "hindiFileName"), getTestData(16, "earnings_cashback"));
		JSONObject jsonObj33 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "hindiFileName"), getTestData(16, "my_earnings"));
		JSONObject jsonObj35 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "hindiFileName"), getTestData(16, "sign_in"));
		JSONObject jsonObj36 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "hindiFileName"), getTestData(16, "join_free"));
		JSONObject jsonObj40 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "hindiFileName"), getTestData(16, "giftcard_wallet_inputfield"));
		JSONObject jsonObj46 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "hindiFileName"), getTestData(16, "payment"));
		JSONObject jsonObj52 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "hindiFileName"), getTestData(16, "my_shared_activities"));
		JSONObject jsonObj53 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "hindiFileName"), getTestData(16, "giftcard_wallet_request_payment"));

		reportStep("chooseLanguage_LanguageHindiValidation_MyEarnings_History_Payment_ExistingUser started", "INFO");

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo);

		String dropDownPaymentSettings = "(//android.widget.EditText)[1]";
		String textNEFT = "//android.widget.CheckedTextView[@text='Bank Payment (NEFT)']";
		String textPaytmWallet = "//android.widget.CheckedTextView[@text='Paytm Wallet']";
		String textAmazonGiftCard = "//android.widget.CheckedTextView[@text='Amazon Gift Card']";
		String textBoxPaytmMobileNumberPaymentRequestPage = "//android.widget.EditText[@content-desc='et_requestpaymentPaytm_mobile']";
		String textBoxAmazonEmailPaymentRequestPage = "//android.widget.EditText[@content-desc='et_requestpaymentAmazon_email']";
		String linkEarningsInfoPaymentPage = "//android.widget.TextView[@content-desc='txt_payment_earningsInfo']";	
		String textboxName = "//android.widget.EditText[@content-desc='et_signup_fullname']";
		String textboxEmail = "//android.widget.EditText[@content-desc='et_signup_email']|//android.widget.EditText[@content-desc='et_signin_email']|//android.widget.EditText[@content-desc='et_forgotpassword_email']";
		String textboxPwd = "//android.widget.EditText[@content-desc='et_signup_password']|//android.widget.EditText[@content-desc='et_signin_password']";
		String textboxMobileNumber = "//android.widget.EditText[@content-desc='et_signup_mobile']";
		String searchIcon = "(//android.widget.TextView)[2]";
		String textAreaSearchIcon = "//android.widget.EditText[@content-desc='et_search_enterValue']";

		String cbStoreNameOne = CashKaroUtility.extractValuesFromStoreCategoryJsonAndLoadTheVariablesForStore("CB_Store_One", 0, "CB_Store_One_Name");
		String linkURL = CashKaroUtility.extractValuesFromStoreCategoryJsonAndLoadTheVariablesForStore("CB_Store_One", 0, "CB_Store_One_linkURL");

		String emailAddress = objCashKaroUtility.generateEmail();
		String mobileNumber = objCashKaroUtility.generateMobileNumber();	

		String emailAddressReferralJoinee = objCashKaroUtility.generateEmail();
		String mobileNumberReferralJoinee = objCashKaroUtility.generateMobileNumber();	

		String otpValue = new EKOnboardingPage(driver, testInfo).
				clickOnSignUpButton().
				enterFullName().
				enterEmail(emailAddress).
				enterPassword().
				enterMobileNumber(mobileNumber).
				clickOnGetOTPButton(mobileNumber);

		new OTPPage(driver, testInfo).
		enterOTP(otpValue).
		clickOnVerifyOTP();

		new HomePage(driver, testInfo).		
		clickOnProfileIconForSignedUser().
		clickOnChooseLanguage().
		clickHindiLink().
		clickContinueButton().
		clickRelaunchButton();

		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "profile_tab"), "home page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj17, "logout_menu"), "profile page");
		validateRequiredText(retrieveChildKeyValues(jsonObj17, "alert_logout"), "logout popup");
		validateRequiredText(retrieveChildKeyValues(jsonObj17, "alert_logout_msg"), "logout popup");
		validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj17, "alert_cancel_btn"), "logout popup");
		clickOnPartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj17, "alert_ok_btn"), "logout popup");

		new EKOnboardingPage(driver, testInfo);

		CashKaroUtility cu = new CashKaroUtility(udid, port, automationName, deviceName);
		driver = cu.launchChromeWebView(driver);

		AdminCoreFunction adminFunctions = new AdminCoreFunction(driver, testInfo);
		adminFunctions.naviagateAdminPage_ThenSelectEarnKaro();
		adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
		adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
		adminFunctions.clickSubmit();

		AdminUsersPage usersPage = new AdminUsersPage(driver, testInfo);
		adminFunctions.clickOnHamburgerMenu();
		adminFunctions.clickUsersMenu();
		adminFunctions.clickUsersSubMenu();
		usersPage.selectSearchByDropDown("User Email");
		usersPage.enterKeyword(emailAddress);
		usersPage.clickSubmitButton();
		String userId = usersPage.extractUserIdValueFromResultstableFirstRow();
		String userName = usersPage.extractUserNameValueFromResultstableFirstRow();

		CashKaroUtility reopenCKApp1 = new CashKaroUtility(udid, port, automationName, deviceName, systemPort);
		driver = reopenCKApp1.launchEarnKaroApp(driver);

		CashKaroUtility objcashKaroutility = new CashKaroUtility(udid, port, automationName, deviceName);
		AppiumDriver<MobileElement> driver2 = objcashKaroutility.launchChrome();
		driver = driver2;

		String url = Utilities.referralLinkGetCode(userId, userName);

		objcashKaroutility.closeAllTabsAndOneTabInChromeBrowser(driver);
		objcashKaroutility.clickAndEnterURLInSearchOrTypeWebAddress(driver, url);

		objcashKaroutility.clickJoinFreeLink(driver);

		enterTextByXpath(textboxName, "ReferralJoinee");
		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(textboxEmail);
		clickOnNoneOFTheAbove();
		enterTextByXpath(textboxEmail, emailAddressReferralJoinee);
		enterTextByXpath(textboxPwd, "ppounds");
		enterTextByXpath(textboxMobileNumber, mobileNumberReferralJoinee);
		clickOnRequiredText(retrieveChildKeyValues(jsonObj36, "otp_btn"), "join free page");	

		new OTPPage(driver, testInfo);

		String otp = Utilities.getSignUPOTP(mobileNumberReferralJoinee);

		new OTPPage(driver, testInfo).
		enterOTP(otp);

		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "verify_otp"), "otp page");	

		sleep(15000);

		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(searchIcon);
		hideKeyboard();
		//validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj13, "search_inputfield_txt"), "search page");
		enterTextByXpath(textAreaSearchIcon, cbStoreNameOne);
		pressEnter();
		validateRequiredText(retrieveChildKeyValues(jsonObj14, "offer_count_single_message"), "search page");
		validateRequiredText(retrieveChildKeyValues(jsonObj1, "add_to_share"), "search page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "share_now"), "search page");

		WhatsAppUtility whatsAppUtility = new WhatsAppUtility(driver, testInfo, udid, port, automationName, deviceName, systemPort);
		whatsAppUtility.clickOnWhatAppMenuItemSearch().
		enterWhatsAppSearchText("Mallik").
		clickOnRequiredContact().
		clickOnSend().
		clickOnSend().
		clickOnLastSharedLink();

		new IntermediateRetailerPage(driver, testInfo).
		validateRetailerPage(linkURL, linkURL);

		CashKaroUtility cu2 = new CashKaroUtility(udid, port, automationName, deviceName);
		cu = cu2;
		driver = cu.launchChromeWebView(driver);

		AdminCoreFunction adminFunctions2 = new AdminCoreFunction(driver, testInfo);
		adminFunctions = adminFunctions2;
		adminFunctions.naviagateAdminPage_ThenSelectEarnKaro();
		adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
		adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
		adminFunctions.clickSubmit();

		AdminReportsPage reportsPage = new AdminReportsPage(driver, testInfo);
		adminFunctions.clickOnHamburgerMenu();
		adminFunctions.clickOnReports();
		reportsPage.clickExitClickSubMenu();
		reportsPage.clickClear();
		reportsPage.selectSearchByDropDown("User Email");
		reportsPage.enterKeyword(emailAddressReferralJoinee);
		reportsPage.clickSubmit();

		String exitClickOne = reportsPage.extractRequiredExitIdUsingStoreNameExitClickType(cbStoreNameOne, getTestData(7, "Normal"));

		AdminCashbackPage ProfitPage = new AdminCashbackPage(driver, testInfo);
		adminFunctions.clickOnHamburgerMenu();
		adminFunctions.clickCashbackMenu();
		ProfitPage.clickCashbackSubMenu();

		ProfitPage.clickAddNewButton();
		ProfitPage.enterExitId(exitClickOne);
		ProfitPage.entertransactionId(exitClickOne);
		ProfitPage.enterOrderId(exitClickOne);
		ProfitPage.setOrderDate();
		ProfitPage.enterOrderValue("1000");
		ProfitPage.enterconfirmCommisionNetwork(getTestData(11, "EightHundered"));
		ProfitPage.entercashback("700");
		ProfitPage.selectCashbackTypeFromDropDown(getTestData(11, "Cashback"));
		ProfitPage.selectCashbackStatusFromDropDown(getTestData(11, "Pending"));
		ProfitPage.setPendingDate();
		ProfitPage.enterDetails(exitClickOne);
		ProfitPage.clickSubmit();
		ProfitPage.clickClear();

		CashKaroUtility reopenCKAppAgain = new CashKaroUtility(udid, port, automationName, deviceName, systemPort);
		driver = reopenCKAppAgain.launchEarnKaroApp(driver);
		new HomePage(driver, testInfo);

		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "profile_tab"), "home page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj17, "history_menu"), "profile page");
		validateRequiredText(retrieveChildKeyValues(jsonObj1, "recent_clicks_tab"), "history page");
		validateRequiredText(retrieveChildKeyValues(jsonObj52, "title"), "history page");
		validateRequiredText(retrieveChildKeyValues(jsonObj52, "shared_link_details"), "history page");
		validateRequiredText(retrieveChildKeyValues(jsonObj52, "shared_id"), "history page");
		validateRequiredText(retrieveChildKeyValues(jsonObj52, "shared_date_time"), "history page");
		validateRequiredText(retrieveChildKeyValues(jsonObj52, "retailer_name"), "history page");
		validateRequiredText(retrieveChildKeyValues(jsonObj52, "no_of_clicks"), "history page");
		validateRequiredText(retrieveChildKeyValues(jsonObj52, "no_of_transactions"), "history page");
		validateRequiredText(retrieveChildKeyValues(jsonObj52, "total_earning"), "history page");

		backButton();

		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "profile_tab"), "home page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj17, "my_earnings_menu"), "profile page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj29, "total_cashback_earned_txt"), "my earnings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj29, "total_cashback_earned_txt"), 1, "profit page");
		validateRequiredText(retrieveChildKeyValues(jsonObj29, "total_cashback_earned_txt"), 2, "profit page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj29, "paid_cashback_txt"), "profit page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj29, "pending_cashback_txt"), "profit page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj29, "referral_cashback_txt"), "profit page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj29, "available_cashback_txt"), "profit page");

		backButton();

		validateRequiredText(retrieveChildKeyValues(jsonObj32, "order_details"), "my earnings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj32, "date"), "my earnings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj32, "retailer"), "my earnings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj32, "order_amount"), "my earnings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj32, "cashback_amount"), "my earnings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj32, "status"), "my earnings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj32, "expected_date"), "my earnings page");

		backButton();

		clickOnRequiredText(retrieveChildKeyValues(jsonObj17, "logout_menu"), "profile page");
		validateRequiredText(retrieveChildKeyValues(jsonObj17, "alert_logout"), "logout popup");
		validateRequiredText(retrieveChildKeyValues(jsonObj17, "alert_logout_msg"), "logout popup");
		validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj17, "alert_cancel_btn"), "logout popup");
		clickOnPartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj17, "alert_ok_btn"), "logout popup");

		new EKOnboardingPage(driver, testInfo);

		CashKaroUtility cu3 = new CashKaroUtility(udid, port, automationName, deviceName);
		cu = cu3;
		driver = cu.launchChromeWebView(driver);

		AdminCoreFunction adminFunctions3 = new AdminCoreFunction(driver, testInfo);
		adminFunctions = adminFunctions3;
		adminFunctions.naviagateAdminPage_ThenSelectEarnKaro();
		adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
		adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
		adminFunctions.clickSubmit();

		AdminCashbackPage ProfitPage1 = new AdminCashbackPage(driver, testInfo);
		ProfitPage = ProfitPage1;
		adminFunctions.clickOnHamburgerMenu();
		adminFunctions.clickCashbackMenu();
		ProfitPage.clickCashbackSubMenu();

		ProfitPage.selectSearchByStatusFromDropDown("Exit ID");
		ProfitPage.enterKeyword(exitClickOne);
		ProfitPage.selectCashbackStatusFromDropDown(getTestData(11, "Pending"));
		ProfitPage.clickSubmit();
		ProfitPage.clickEdit();
		ProfitPage.selectCashbackStatusFromDropDown(getTestData(11, "Confirmed"));
		ProfitPage.setConfirmDate();
		ProfitPage.clickSubmit();
		ProfitPage.clickClear();

		CashKaroUtility reopenCKAppOnceAgain = new CashKaroUtility(udid, port, automationName, deviceName, systemPort);
		driver = reopenCKAppOnceAgain.launchEarnKaroApp(driver);

		new EKOnboardingPage(driver, testInfo).clickOnSignInbutton();

		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(textboxEmail);
		clickOnNoneOFTheAbove();
		enterTextByXpath(textboxEmail, emailAddress);
		enterTextByXpath(textboxPwd, "ppounds");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj35, "sign_with_email_btn"), "login page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "profile_tab"), "home page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj17, "my_earnings_menu"), "profile page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj33, "referral_tap"), "my earnings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj31, "order_details"), "my earnings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj31, "date"), "my earnings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj31, "referral_name"), "my earnings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj31, "cashback"), "my earnings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj31, "status"), "my earnings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj33, "refer_now_btn"), "my earnings page");

		backButton();

		clickOnRequiredText(retrieveChildKeyValues(jsonObj17, "logout_menu"), "profile page");
		validateRequiredText(retrieveChildKeyValues(jsonObj17, "alert_logout"), "logout popup");
		validateRequiredText(retrieveChildKeyValues(jsonObj17, "alert_logout_msg"), "logout popup");
		validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj17, "alert_cancel_btn"), "logout popup");
		clickOnPartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj17, "alert_ok_btn"), "logout popup");

		new EKOnboardingPage(driver, testInfo).clickOnSignInbutton();

		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(textboxEmail);
		clickOnNoneOFTheAbove();
		enterTextByXpath(textboxEmail, emailAddressReferralJoinee);
		enterTextByXpath(textboxPwd, "ppounds");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj35, "sign_with_email_btn"), "login page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "profile_tab"), "home page");
		validateRequiredText(retrieveChildKeyValues(jsonObj1, "profile_tab"), "profile page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj17, "hello_user_txt"), "profile page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj17, "total_cashback_txt"), "profile page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj17, "payment_menu"), "profile page");

		validateRequiredText(retrieveChildKeyValues(jsonObj17, "payment_menu"), "profile page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj29, "total_cashback_earned_txt"), "payments page");
		validateRequiredText(retrieveChildKeyValues(jsonObj29, "total_cashback_earned_txt"), 1, "profit page");
		validateRequiredText(retrieveChildKeyValues(jsonObj29, "total_cashback_earned_txt"), 2, "profit page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj29, "paid_cashback_txt"), "profit page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj29, "pending_cashback_txt"), "profit page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj29, "referral_cashback_txt"), "profit page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj29, "available_cashback_txt"), "profit page");

		backButton();

		validatePartialRequiredText(retrieveChildKeyValues(jsonObj28, "tap_to_see_details_txt"), "payments page");
		validateRequiredText(retrieveChildKeyValues(jsonObj46, "cashback_earnings"), "payments page");
		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(linkEarningsInfoPaymentPage);
		validateRequiredText(retrieveChildKeyValues(jsonObj30, "message_first_txt"), "earnings info page");


		backButton();

		validateRequiredText(retrieveChildKeyValues(jsonObj46, "request_cashback_btn"), "payments page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj46, "static_text_title"), "payments page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj46, "static_text_1"), "payments page");


		clickOnRequiredText(retrieveChildKeyValues(jsonObj46, "request_cashback_btn"), "payments page");

		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(dropDownPaymentSettings);
		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(textPaytmWallet);
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "mobile_inputfield_txt"), "payment request page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "get_paid_btn"), "payment req page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "mobile_inputfield_txt"), "payment request page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "mobile_number_error_msg"), "payment request page");

		enterTextByXpath(textBoxPaytmMobileNumberPaymentRequestPage, "1");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "get_paid_btn"), "payment req page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "mobile_inputfield_txt"), "payment request page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "minimum_digits_error_msg"), "payment request page");

		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(dropDownPaymentSettings);
		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(textAmazonGiftCard);

		validateRequiredText(retrieveChildKeyValues(jsonObj53, "email_inputfield_txt"), "payment request page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "get_paid_btn"), "payment req page");
		validateRequiredText(retrieveChildKeyValues(jsonObj53, "email_inputfield_txt"), "payment request page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "email_error_msg"), "payment request page");

		enterTextByXpath(textBoxAmazonEmailPaymentRequestPage, "1");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "get_paid_btn"), "payment req page");
		validateRequiredText(retrieveChildKeyValues(jsonObj53, "email_inputfield_txt"), "payment request page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "minimum_characters_error_msg"), "payment request page");

		enterTextByXpath(textBoxAmazonEmailPaymentRequestPage, "asdfghjkl");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "get_paid_btn"), "payment req page");
		validateRequiredText(retrieveChildKeyValues(jsonObj53, "email_inputfield_txt"), "payment request page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "valid_email_error_msg"), "payment request page");

		enterTextByXpath(textBoxAmazonEmailPaymentRequestPage, getTestData(7, "FiftyUserCharWithSpace"));
		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "get_paid_btn"), "payment req page");
		validateRequiredText(retrieveChildKeyValues(jsonObj53, "email_inputfield_txt"), "payment request page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "maximum_characters_error_msg"), "payment request page");

		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(dropDownPaymentSettings);
		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(textNEFT);

		validateRequiredText(retrieveChildKeyValues(jsonObj40, "bank_account_inputfield_txt"), "payment request page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "bank_name_inputfield_txt"), "payment request page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "branch_name_inputfield_txt"), "payment request page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "bank_account_number_inputfield_txt"), "payment request page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "bank_ifsc_code_inputfield_txt"), "payment request page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "get_paid_btn"), "payment req page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "bank_account_inputfield_txt"), "payment request page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "account_holder_error_msg"), "payment request page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "bank_name_inputfield_txt"), "payment request page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "valid_bank_name_error_msg"), "payment request page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "branch_name_inputfield_txt"), "payment request page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "branch_minimum_digits_error_msg"), "payment request page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "bank_account_number_inputfield_txt"), "payment request page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "account_number_error_msg"), "payment request page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "bank_ifsc_code_inputfield_txt"), "payment request page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "ifsc_code_error_msg"), "payment request page");


	}

	@Test
	public void chooseLanguage_LanguageHindiValidation_StoreCategory_StoreDetail() throws UnsupportedEncodingException, FileNotFoundException, IOException, ParseException {

		JSONObject jsonObj1 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "hindiFileName"), getTestData(16, "common_label"));
		JSONObject jsonObj3 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "hindiFileName"), getTestData(16, "main_menu"));
		JSONObject jsonObj5 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "hindiFileName"), getTestData(16, "store_category"));
		JSONObject jsonObj12 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "hindiFileName"), getTestData(16, "share_counts"));
		JSONObject jsonObj17 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "hindiFileName"), getTestData(16, "profile_right_menu"));

		reportStep("chooseLanguage_LanguageHindiValidation_Store_Product_StoreCategory started", "INFO");

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo);

		String shareButtonWithCount = "//android.view.ViewGroup[@content-desc='txt_sharecount_open_modal']/android.view.ViewGroup/android.widget.TextView[@text='1']";
		String imageStoreCard = "//android.widget.ImageView[@content-desc='img_storeCard_imageValue']";
		String emailAddress = objCashKaroUtility.generateEmail();
		String mobileNumber = objCashKaroUtility.generateMobileNumber();	

		String otpValue = new EKOnboardingPage(driver, testInfo).
				clickOnSignUpButton().
				enterFullName().
				enterEmail(emailAddress).
				enterPassword().
				enterMobileNumber(mobileNumber).
				clickOnGetOTPButton(mobileNumber);

		new OTPPage(driver, testInfo).
		enterOTP(otpValue).
		clickOnVerifyOTP();

		new HomePage(driver, testInfo).		
		clickOnProfileIconForSignedUser().
		clickOnChooseLanguage().
		clickHindiLink().
		clickContinueButton().
		clickRelaunchButton();

		validateRequiredText(retrieveChildKeyValues(jsonObj3, "home_lbl"), "home page");

		new HomePage(driver, testInfo).
		clickOnHamburgerIcon();

		validateRequiredText(retrieveChildKeyValues(jsonObj3, "home_lbl"), "home page");

		new SliderScreenPage(driver, testInfo).
		clickRetailerCategory().
		verifyRetailerCategoryMenuNavigation().
		clickAutomationSpecificCategoryInRetailerCategoryMenuUntilAllSubCategoryVisible().
		clickAutomationSpecificSubCategoryOne();

		validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj5, "popular"), "store category page");
		validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj5, "az"), "store category page");
		validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj5, "percent"), "store category page");
		validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj5, "amount"), "store category page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "add_to_share"), "store category page");
		validateRequiredText(retrieveChildKeyValues(jsonObj1, "share_now"), "store category page");
		validateRequiredText(retrieveChildKeyValues(jsonObj1, "remove"), "store category page");

		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(shareButtonWithCount);
		validateRequiredText(retrieveChildKeyValues(jsonObj1, "share_now"), "store category page");
		validateRequiredText(retrieveChildKeyValues(jsonObj1, "copy_Link"), "store category page");
		validateRequiredText(retrieveChildKeyValues(jsonObj12, "close"), "store category page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj12, "clear"), "store category page");

		validateRequiredText(retrieveChildKeyValues(jsonObj12, "clear"), "store category page");
		validateRequiredText(retrieveChildKeyValues(jsonObj12, "confirm_msg_clear_share"), "store category page");
		validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj17, "alert_cancel_btn"), "store category page");
		clickOnPartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj17, "alert_ok_btn"), "store category page");

		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(imageStoreCard);
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj1, "you_will_earn"), "store detail page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "add_to_share"), "store detail page");
		validateRequiredText(retrieveChildKeyValues(jsonObj1, "share_now"), "store detail page");
		validateRequiredText(retrieveChildKeyValues(jsonObj1, "remove"), "store category page");

		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(shareButtonWithCount);
		validateRequiredText(retrieveChildKeyValues(jsonObj1, "share_now"), "store detail page");
		validateRequiredText(retrieveChildKeyValues(jsonObj1, "copy_Link"), "store detail page");
		validateRequiredText(retrieveChildKeyValues(jsonObj12, "close"), "store detail page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj12, "clear"), "store detail page");

		validateRequiredText(retrieveChildKeyValues(jsonObj12, "clear"), "store detail page");
		validateRequiredText(retrieveChildKeyValues(jsonObj12, "confirm_msg_clear_share"), "store detail page");
		validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj17, "alert_cancel_btn"), "store detail page");
		clickOnPartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj17, "alert_ok_btn"), "store detail page");

		new StoreDetailPage(driver, testInfo);

	}

	@Test
	public void chooseLanguage_LanguageHindiValidation_Product() throws UnsupportedEncodingException, FileNotFoundException, IOException, ParseException {

		JSONObject jsonObj1 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "hindiFileName"), getTestData(16, "common_label"));
		JSONObject jsonObj3 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "hindiFileName"), getTestData(16, "main_menu"));
		JSONObject jsonObj6 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "hindiFileName"), getTestData(16, "prduct_category"));
		JSONObject jsonObj9 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "hindiFileName"), getTestData(16, "product_details"));
		JSONObject jsonObj10 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "hindiFileName"), getTestData(16, "product_card"));
		JSONObject jsonObj49 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "hindiFileName"), getTestData(16, "prduct_filter"));

		reportStep("chooseLanguage_LanguageHindiValidation_Store_Product_StoreCategory started", "INFO");

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo);

		String menFashionCategory = "//android.view.ViewGroup[@content-desc='txt_mainMenu_productDealsTop']";
		String tshirtCategory = "//android.widget.TextView[@content-desc='txt_subMenuProduct_mainMenuName']";
		String menfashionLink = "//android.widget.TextView[@content-desc='txt_filter_category_name']";
		String imageProductCard = "//android.widget.ImageView[@content-desc='img_product_image']";
		String productcard = "(//android.widget.ImageView)[3]";		
		String emailAddress = objCashKaroUtility.generateEmail();
		String mobileNumber = objCashKaroUtility.generateMobileNumber();	

		String otpValue = new EKOnboardingPage(driver, testInfo).
				clickOnSignUpButton().
				enterFullName().
				enterEmail(emailAddress).
				enterPassword().
				enterMobileNumber(mobileNumber).
				clickOnGetOTPButton(mobileNumber);

		new OTPPage(driver, testInfo).
		enterOTP(otpValue).
		clickOnVerifyOTP();

		new HomePage(driver, testInfo).		
		clickOnProfileIconForSignedUser().
		clickOnChooseLanguage().
		clickHindiLink().
		clickContinueButton().
		clickRelaunchButton();

		validateRequiredText(retrieveChildKeyValues(jsonObj3, "home_lbl"), "home page");

		new HomePage(driver, testInfo).
		clickOnHamburgerIcon();

		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(menFashionCategory);
		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(tshirtCategory);

		clickOnPartialRequiredText(retrieveChildKeyValues(jsonObj6, "filter"), "product page");
		validateRequiredText(retrieveChildKeyValues(jsonObj49, "filter_by"), "product page");
		validateRequiredText(retrieveChildKeyValues(jsonObj49, "clerar_all"), "product page");
		validateRequiredText(retrieveChildKeyValues(jsonObj49, "close_btn"), "product page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj49, "apply_btn"), "product page");

		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(menfashionLink);
		clickOnRequiredText(retrieveChildKeyValues(jsonObj49, "clerar_all"), "product page");
		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(menfashionLink);
		clickOnRequiredText(retrieveChildKeyValues(jsonObj49, "close_btn"), "product page");
		validateRequiredText(retrieveChildKeyValues(jsonObj49, "discard_changes_title"), "product page");
		validateRequiredText(retrieveChildKeyValues(jsonObj49, "discard_changes_msg"), "product page");
		validateRequiredText(retrieveChildKeyValues(jsonObj49, "apply_changes_btn"), "product page");
		validateRequiredText(retrieveChildKeyValues(jsonObj49, "discard_changes_btn"), "product page");

		backButton();

		clickOnPartialRequiredText(retrieveChildKeyValues(jsonObj6, "sort"), "product page");
		validateRequiredText(retrieveChildKeyValues(jsonObj6, "sort_title"), "product page");
		validateRequiredText(retrieveChildKeyValues(jsonObj6, "sort_popular"), "product page");
		validateRequiredText(retrieveChildKeyValues(jsonObj6, "sort_discount"), "product page");
		validateRequiredText(retrieveChildKeyValues(jsonObj6, "sort_high_price"), "product page");
		validateRequiredText(retrieveChildKeyValues(jsonObj6, "sort_low_price"), "product page");

		backButton();

		validatePartialRequiredText(retrieveChildKeyValues(jsonObj10, "brand"), "product page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj10, "price"), "product page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj10, "sale"), "product page");
		validateRequiredText(retrieveChildKeyValues(jsonObj1, "add_to_share"), "product page");
		validateRequiredText(retrieveChildKeyValues(jsonObj1, "share_now"), "product page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj10, "see_details"), "product page");

		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(imageProductCard);
		clickOnRequiredText(retrieveChildKeyValues(jsonObj9, "seller_price"), "product detail page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "you_will_earn"), "product detail page");
		validateRequiredText(retrieveChildKeyValues(jsonObj1, "add_to_share"), "product page");
		validateRequiredText(retrieveChildKeyValues(jsonObj1, "share_now"), "product page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj9, "product_highlights"), "product detail page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj9, "similar_products"), "product detail page");



	}

	@Test
	public void chooseLanguage_LanguageMalayalamValidation_SuccessStories() throws UnsupportedEncodingException, FileNotFoundException, IOException, ParseException {

		JSONObject jsonObj1 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "malayalamFileName"), getTestData(16, "common_label"));
		JSONObject jsonObj4 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "malayalamFileName"), getTestData(16, "home"));
		JSONObject jsonObj17 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "malayalamFileName"), getTestData(16, "profile_right_menu"));
		JSONObject jsonObj22 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "malayalamFileName"), getTestData(16, "success_stories"));

		reportStep("chooseLanguage_LanguageMalayalamValidation_SuccessStories started", "INFO");

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo);

		String emailAddress = objCashKaroUtility.generateEmail();
		String mobileNumber = objCashKaroUtility.generateMobileNumber();	

		String otpValue = new EKOnboardingPage(driver, testInfo).
				clickOnSignUpButton().
				enterFullName().
				enterEmail(emailAddress).
				enterPassword().
				enterMobileNumber(mobileNumber).
				clickOnGetOTPButton(mobileNumber);

		new OTPPage(driver, testInfo).
		enterOTP(otpValue).
		clickOnVerifyOTP();

		new HomePage(driver, testInfo).		
		clickOnProfileIconForSignedUser().
		clickOnChooseLanguage().
		clickMalayalamLink().
		clickContinueButton().
		clickRelaunchButton();

		validateRequiredText(retrieveChildKeyValues(jsonObj4, "dynamic_home_top_Category"), "home page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "profile_tab"), "home page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj17, "success_stories_menu"), "profile page");
		validateRequiredText(retrieveChildKeyValues(jsonObj17, "success_stories_menu"), "success stories page");
		scrollFromUpToDowninApp();scrollFromUpToDowninApp();
		validateRequiredText(retrieveChildKeyValues(jsonObj22, "title"), "success stories page");
		validateRequiredText(retrieveChildKeyValues(jsonObj22, "testimonial_txt1"), "success stories page");
		validateRequiredText(retrieveChildKeyValues(jsonObj22, "testimonial_name1"), "success stories page");
		validateRequiredText(retrieveChildKeyValues(jsonObj22, "testimonial_txt2"), "success stories page");
		validateRequiredText(retrieveChildKeyValues(jsonObj22, "testimonial_name2"), "success stories page");
		validateRequiredText(retrieveChildKeyValues(jsonObj22, "testimonial_txt3"), "success stories page");
		validateRequiredText(retrieveChildKeyValues(jsonObj22, "testimonial_name3"), "success stories page");
		validateRequiredText(retrieveChildKeyValues(jsonObj22, "testimonial_txt4"), "success stories page");
		validateRequiredText(retrieveChildKeyValues(jsonObj22, "testimonial_name4"), "success stories page");
		validateRequiredText(retrieveChildKeyValues(jsonObj22, "testimonial_txt5"), "success stories page");
		validateRequiredText(retrieveChildKeyValues(jsonObj22, "testimonial_name5"), "success stories page");


	}

	@Test
	public void chooseLanguage_LanguageMalayalamValidation_HowItWorks() throws UnsupportedEncodingException, FileNotFoundException, IOException, ParseException {

		JSONObject jsonObj1 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "malayalamFileName"), getTestData(16, "common_label"));
		JSONObject jsonObj4 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "malayalamFileName"), getTestData(16, "home"));
		JSONObject jsonObj17 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "malayalamFileName"), getTestData(16, "profile_right_menu"));
		JSONObject jsonObj23 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "malayalamFileName"), getTestData(16, "how_it_works"));

		reportStep("chooseLanguage_LanguageMalayalamValidation_HowItWorks started", "INFO");

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo);

		String emailAddress = objCashKaroUtility.generateEmail();
		String mobileNumber = objCashKaroUtility.generateMobileNumber();	

		String otpValue = new EKOnboardingPage(driver, testInfo).
				clickOnSignUpButton().
				enterFullName().
				enterEmail(emailAddress).
				enterPassword().
				enterMobileNumber(mobileNumber).
				clickOnGetOTPButton(mobileNumber);

		new OTPPage(driver, testInfo).
		enterOTP(otpValue).
		clickOnVerifyOTP();

		new HomePage(driver, testInfo).		
		clickOnProfileIconForSignedUser().
		clickOnChooseLanguage().
		clickMalayalamLink().
		clickContinueButton().
		clickRelaunchButton();

		validateRequiredText(retrieveChildKeyValues(jsonObj4, "dynamic_home_top_Category"), "home page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "profile_tab"), "home page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj17, "how_it_works_menu"), "profile page");
		scrollFromUpToDowninApp();scrollFromUpToDowninApp();
		validateRequiredText(retrieveChildKeyValues(jsonObj17, "how_it_works_menu"), "how earnkaro works? page");
		validateRequiredText(retrieveChildKeyValues(jsonObj23, "watch_video_title1"), "how earnkaro works? page");
		validateRequiredText(retrieveChildKeyValues(jsonObj23, "watch_video_txt"), 1, "how earnkaro works? page");
		validateRequiredText(retrieveChildKeyValues(jsonObj23, "watch_video_title2"), "how earnkaro works? page");
		validateRequiredText(retrieveChildKeyValues(jsonObj23, "watch_video_txt"), 2, "how earnkaro works? page");
		validateRequiredText(retrieveChildKeyValues(jsonObj23, "watch_video_title3"), "how earnkaro works? page");
		validateRequiredText(retrieveChildKeyValues(jsonObj23, "watch_video_txt"), 3, "how earnkaro works? page");
		validateRequiredText(retrieveChildKeyValues(jsonObj23, "watch_video_title4"), "how earnkaro works? page");
		validateRequiredText(retrieveChildKeyValues(jsonObj23, "watch_video_txt"), 4, "how earnkaro works? page");
		validateRequiredText(retrieveChildKeyValues(jsonObj23, "watch_video_title5"), "how earnkaro works? page");
		validateRequiredText(retrieveChildKeyValues(jsonObj23, "watch_video_txt"), 5, "how earnkaro works? page");
		validateRequiredText(retrieveChildKeyValues(jsonObj23, "watch_video_title6"), "how earnkaro works? page");
		validateRequiredText(retrieveChildKeyValues(jsonObj23, "watch_video_txt"), 6, "how earnkaro works? page");



	}

	@Test
	public void chooseLanguage_LanguageMalayalamValidation_Testimonials() throws UnsupportedEncodingException, FileNotFoundException, IOException, ParseException {

		JSONObject jsonObj1 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "malayalamFileName"), getTestData(16, "common_label"));
		JSONObject jsonObj4 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "malayalamFileName"), getTestData(16, "home"));
		JSONObject jsonObj17 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "malayalamFileName"), getTestData(16, "profile_right_menu"));
		JSONObject jsonObj24 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "malayalamFileName"), getTestData(16, "testimonial"));
		JSONObject jsonObj25 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "malayalamFileName"), getTestData(16, "testimonial_write"));

		reportStep("chooseLanguage_LanguageMalayalamValidation_Testimonials started", "INFO");

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo);

		String fourStarTestimonials = "(//android.widget.TextView)[8]";
		String threeStarTestimonials = "(//android.widget.TextView)[7]";
		String twoStarTestimonials = "(//android.widget.TextView)[6]";
		String oneStarTestimonials = "(//android.widget.TextView)[5]";

		String emailAddress = objCashKaroUtility.generateEmail();
		String mobileNumber = objCashKaroUtility.generateMobileNumber();	

		String otpValue = new EKOnboardingPage(driver, testInfo).
				clickOnSignUpButton().
				enterFullName().
				enterEmail(emailAddress).
				enterPassword().
				enterMobileNumber(mobileNumber).
				clickOnGetOTPButton(mobileNumber);

		new OTPPage(driver, testInfo).
		enterOTP(otpValue).
		clickOnVerifyOTP();

		new HomePage(driver, testInfo).		
		clickOnProfileIconForSignedUser().
		clickOnChooseLanguage().
		clickMalayalamLink().
		clickContinueButton().
		clickRelaunchButton();

		validateRequiredText(retrieveChildKeyValues(jsonObj4, "dynamic_home_top_Category"), "home page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "profile_tab"), "home page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj17, "rate_us_menu"), "profile page");
		validateRequiredText(retrieveChildKeyValues(jsonObj24, "count_txt"), "testimonials page");
		validateRequiredText(retrieveChildKeyValues(jsonObj24, "happy_users_txt"), "testimonials page");

		clickOnPartialRequiredText(retrieveChildKeyValues(jsonObj24, "write_testimonial_btn"), "testimonials page");
		validateRequiredText(retrieveChildKeyValues(jsonObj24, "write_testimonial_btn"), "write testimonials page");
		validateRequiredText(retrieveChildKeyValues(jsonObj25, "title"), "write testimonials page");

		validateRequiredText(retrieveChildKeyValues(jsonObj25, "rate_experience_txt"), "write testimonials page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj25, "star_txt_awesome_experience"), "write testimonials page");
		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(fourStarTestimonials);
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj25, "star_txt_good_experience"), "write testimonials page");
		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(threeStarTestimonials);
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj25, "star_txt_average"), "write testimonials page");
		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(twoStarTestimonials);
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj25, "star_txt_not_great"), "write testimonials page");
		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(oneStarTestimonials);
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj25, "star_txt_terrible"), "write testimonials page");


	}

	@Test
	public void chooseLanguage_LanguageMalayalamValidation_ContactUs() throws UnsupportedEncodingException, FileNotFoundException, IOException, ParseException {

		JSONObject jsonObj1 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "malayalamFileName"), getTestData(16, "common_label"));
		JSONObject jsonObj2 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "malayalamFileName"), getTestData(16, "common_validate_label"));
		JSONObject jsonObj4 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "malayalamFileName"), getTestData(16, "home"));
		JSONObject jsonObj17 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "malayalamFileName"), getTestData(16, "profile_right_menu"));
		JSONObject jsonObj20 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "malayalamFileName"), getTestData(16, "contact_us"));		
		JSONObject jsonObj21 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "malayalamFileName"), getTestData(16, "faq"));

		reportStep("chooseLanguage_LanguageMalayalamValidation_ContactUs started", "INFO");

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo);

		String textBoxUserName = "//android.widget.EditText[@content-desc='et_contactUs_yourName']";
		String textBoxEmail = "//android.widget.EditText[@content-desc='et_contactUs_email']";

		String emailAddress = objCashKaroUtility.generateEmail();
		String mobileNumber = objCashKaroUtility.generateMobileNumber();	

		String otpValue = new EKOnboardingPage(driver, testInfo).
				clickOnSignUpButton().
				enterFullName().
				enterEmail(emailAddress).
				enterPassword().
				enterMobileNumber(mobileNumber).
				clickOnGetOTPButton(mobileNumber);

		new OTPPage(driver, testInfo).
		enterOTP(otpValue).
		clickOnVerifyOTP();

		new HomePage(driver, testInfo).		
		clickOnProfileIconForSignedUser().
		clickOnChooseLanguage().
		clickMalayalamLink().
		clickContinueButton().
		clickRelaunchButton();

		validateRequiredText(retrieveChildKeyValues(jsonObj4, "dynamic_home_top_Category"), "home page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "profile_tab"), "home page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj17, "help"), "profile page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj17, "contact_us_menu"), "help page");
		enterTextByXpath(textBoxUserName, "..");
		enterTextByXpath(textBoxEmail, "..");
		validateRequiredText(retrieveChildKeyValues(jsonObj21, "faq_btn"), "contact us page");
		validateRequiredText(retrieveChildKeyValues(jsonObj20, "name_inputfield_txt"), "contact us page");
		validateRequiredText(retrieveChildKeyValues(jsonObj20, "email_inputfield_txt"), "contact us page");
		validateRequiredText(retrieveChildKeyValues(jsonObj20, "message_inputfield_txt"), "contact us page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj20, "submit_btn"), "contact us page");

		validateRequiredText(retrieveChildKeyValues(jsonObj2, "select_subject_error_msg"), "contact us page");
		validateRequiredText(retrieveChildKeyValues(jsonObj20, "name_inputfield_txt"), "contact us page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "valid_name_error_msg"), "contact us page");
		validateRequiredText(retrieveChildKeyValues(jsonObj20, "email_inputfield_txt"), "contact us page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "valid_email_error_msg"), "contact us page");
		validateRequiredText(retrieveChildKeyValues(jsonObj20, "message_inputfield_txt"), "contact us page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "valid_details_error_msg"), "contact us page");

		enterTextByXpath(textBoxUserName, getTestData(7, "oneUserChar"));
		enterTextByXpath(textBoxEmail, getTestData(7, "oneUserChar"));
		clickOnRequiredText(retrieveChildKeyValues(jsonObj20, "submit_btn"), "contact us page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "select_subject_error_msg"), "contact us page");
		validateRequiredText(retrieveChildKeyValues(jsonObj20, "name_inputfield_txt"), "contact us page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "minimum_characters_error_msg"), 1, "contact us page");
		validateRequiredText(retrieveChildKeyValues(jsonObj20, "email_inputfield_txt"), "contact us page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "minimum_characters_error_msg"), 2, "contact us page");
		validateRequiredText(retrieveChildKeyValues(jsonObj20, "message_inputfield_txt"), "contact us page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "valid_details_error_msg"), "contact us page");

		enterTextByXpath(textBoxUserName, getTestData(7, "FiftyPlusUserChar"));
		enterTextByXpath(textBoxEmail, getTestData(7, "FiftyPlusUserChar"));
		clickOnRequiredText(retrieveChildKeyValues(jsonObj20, "submit_btn"), "contact us page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "select_subject_error_msg"), "contact us page");
		validateRequiredText(retrieveChildKeyValues(jsonObj20, "name_inputfield_txt"), "contact us page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "maximum_characters_error_msg"), 1, "contact us page");
		validateRequiredText(retrieveChildKeyValues(jsonObj20, "email_inputfield_txt"), "contact us page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "maximum_characters_error_msg"), 2, "contact us page");
		validateRequiredText(retrieveChildKeyValues(jsonObj20, "message_inputfield_txt"), "contact us page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "valid_details_error_msg"), "contact us page");

	}

	@Test
	public void chooseLanguage_LanguageMalayalamValidation_FAQs() throws UnsupportedEncodingException, FileNotFoundException, IOException, ParseException {

		JSONObject jsonObj1 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "malayalamFileName"), getTestData(16, "common_label"));
		JSONObject jsonObj4 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "malayalamFileName"), getTestData(16, "home"));
		JSONObject jsonObj17 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "malayalamFileName"), getTestData(16, "profile_right_menu"));		
		JSONObject jsonObj21 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "malayalamFileName"), getTestData(16, "faq"));

		reportStep("chooseLanguage_LanguageMalayalamValidation_FAQs started", "INFO");

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo);

		String emailAddress = objCashKaroUtility.generateEmail();
		String mobileNumber = objCashKaroUtility.generateMobileNumber();	

		String otpValue = new EKOnboardingPage(driver, testInfo).
				clickOnSignUpButton().
				enterFullName().
				enterEmail(emailAddress).
				enterPassword().
				enterMobileNumber(mobileNumber).
				clickOnGetOTPButton(mobileNumber);

		new OTPPage(driver, testInfo).
		enterOTP(otpValue).
		clickOnVerifyOTP();

		new HomePage(driver, testInfo).		
		clickOnProfileIconForSignedUser().
		clickOnChooseLanguage().
		clickMalayalamLink().
		clickContinueButton().
		clickRelaunchButton();

		validateRequiredText(retrieveChildKeyValues(jsonObj4, "dynamic_home_top_Category"), "home page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "profile_tab"), "home page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj17, "help"), "profile page");

		validateRequiredText(retrieveChildKeyValues(jsonObj17, "call_us_menu"), "help page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj17, "faq_menu"), "help page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj21, "question1"), "faq page");
		validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj21, "answer1"), "faq page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj21, "question2"), "faq page");
		validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj21, "answer2"), "faq page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj21, "question3"), "faq page");
		validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj21, "answer3"), "faq page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj21, "question4"), "faq page");
		validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj21, "answer4"), "faq page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj21, "question5"), "faq page");
		validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj21, "answer5"), "faq page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj21, "question6"), "faq page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj21, "answer6"), "faq page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj21, "question7"), "faq page");
		validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj21, "answer7"), "faq page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj21, "question8"), "faq page");
		validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj21, "answer8"), "faq page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj21, "question9"), "faq page");
		//validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj21, "answer9"), "faq page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj21, "question10"), "faq page");
		validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj21, "answer10"), "faq page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj21, "question11"), "faq page");
		//validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj21, "answer11"), "faq page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj21, "question12"), "faq page");
		//validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj21, "answer12"), "faq page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj21, "question13"), "faq page");
		//validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj21, "answer13"), "faq page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj21, "question14"), "faq page");
		//validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj21, "answer14"), "faq page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj21, "question15"), "faq page");
		//validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj21, "answer15"), "faq page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj21, "question16"), "faq page");
		//validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj21, "answer16"), "faq page");

		validateRequiredText(retrieveChildKeyValues(jsonObj21, "faq_btn"), "faq page");


	}

	@Test
	public void chooseLanguage_LanguageMalayalamValidation_History_NewUser() throws UnsupportedEncodingException, FileNotFoundException, IOException, ParseException {

		JSONObject jsonObj1 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "malayalamFileName"), getTestData(16, "common_label"));
		JSONObject jsonObj4 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "malayalamFileName"), getTestData(16, "home"));
		JSONObject jsonObj17 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "malayalamFileName"), getTestData(16, "profile_right_menu"));
		JSONObject jsonObj52 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "malayalamFileName"), getTestData(16, "my_shared_activities"));

		reportStep("chooseLanguage_LanguageMalayalamValidation_History_NewUser started", "INFO");

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo);

		String emailAddress = objCashKaroUtility.generateEmail();
		String mobileNumber = objCashKaroUtility.generateMobileNumber();	

		String otpValue = new EKOnboardingPage(driver, testInfo).
				clickOnSignUpButton().
				enterFullName().
				enterEmail(emailAddress).
				enterPassword().
				enterMobileNumber(mobileNumber).
				clickOnGetOTPButton(mobileNumber);

		new OTPPage(driver, testInfo).
		enterOTP(otpValue).
		clickOnVerifyOTP();

		new HomePage(driver, testInfo).		
		clickOnProfileIconForSignedUser().
		clickOnChooseLanguage().
		clickMalayalamLink().
		clickContinueButton().
		clickRelaunchButton();

		validateRequiredText(retrieveChildKeyValues(jsonObj4, "dynamic_home_top_Category"), "home page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "profile_tab"), "home page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj17, "history_menu"), "profile page");
		validateRequiredText(retrieveChildKeyValues(jsonObj17, "recent_clicks_menu"), "history page");
		validateRequiredText(retrieveChildKeyValues(jsonObj1, "whoops_msg"), "history page");
		validateRequiredText(retrieveChildKeyValues(jsonObj52, "no_shared_activites"), "history page");
		validateRequiredText(retrieveChildKeyValues(jsonObj52, "saving_click_away"), "history page");


	}

	@Test
	public void chooseLanguage_LanguageMalayalamValidation_AccountSettings() throws UnsupportedEncodingException, FileNotFoundException, IOException, ParseException {

		JSONObject jsonObj1 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "malayalamFileName"), getTestData(16, "common_label"));
		JSONObject jsonObj2 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "malayalamFileName"), getTestData(16, "common_validate_label"));
		JSONObject jsonObj17 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "malayalamFileName"), getTestData(16, "profile_right_menu"));
		JSONObject jsonObj26 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "malayalamFileName"), getTestData(16, "personal_details"));
		JSONObject jsonObj27 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "malayalamFileName"), getTestData(16, "change_password"));
		JSONObject jsonObj40 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "malayalamFileName"), getTestData(16, "giftcard_wallet_inputfield"));

		reportStep("chooseLanguage_LanguageMalayalamValidation_AccountSettings started", "INFO");

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo);

		String textBoxExistingPwd = "//android.widget.EditText[@content-desc='et_changePassword_currentPassword']";
		String textBoxNewPwd = "//android.widget.EditText[@content-desc='et_changePassword_fullName']";
		String textBoxConfirmNewPwd = "//android.widget.EditText[@content-desc='et_changePassword_confirmPassword']";

		String dropDownPaymentSettings = "(//android.widget.EditText)[1]";
		String textNEFT = "//android.widget.CheckedTextView[@text='Bank Payment (NEFT)']";
		String textPaytmWallet = "//android.widget.CheckedTextView[@text='Paytm Wallet']";
		String textAmazonGiftCard = "//android.widget.CheckedTextView[@text='Amazon Gift Card']";

		String textBoxPaytmMobileNumber = "//android.widget.EditText[@content-desc='et_settingsPaytm_mobile']";
		String textBoxPaytmPassword = "//android.widget.EditText[@content-desc='et_settingsPaytm_password']";

		String textBoxAmazonEmail = "//android.widget.EditText[@content-desc='et_settingsAmazon_email']";
		String textBoxAmazonPassword = "//android.widget.EditText[@content-desc='et_settingsAmazon_password']";
		String emailAddress = objCashKaroUtility.generateEmail();
		String mobileNumber = objCashKaroUtility.generateMobileNumber();	

		String otpValue = new EKOnboardingPage(driver, testInfo).
				clickOnSignUpButton().
				enterFullName().
				enterEmail(emailAddress).
				enterPassword().
				enterMobileNumber(mobileNumber).
				clickOnGetOTPButton(mobileNumber);

		new OTPPage(driver, testInfo).
		enterOTP(otpValue).
		clickOnVerifyOTP();

		new HomePage(driver, testInfo).		
		clickOnProfileIconForSignedUser().
		clickOnChooseLanguage().
		clickMalayalamLink().
		clickContinueButton().
		clickRelaunchButton();

		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "profile_tab"), "home page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj17, "account_setting_menu"), "profile page");
		validateRequiredText(retrieveChildKeyValues(jsonObj17, "account_setting_menu"), "profile page");
		clickOnPartialRequiredText(retrieveChildKeyValues(jsonObj1, "personal_details_tab"), "account settings page");
		scrollFromUpToDowninApp();scrollFromUpToDowninApp();
		validateRequiredText(retrieveChildKeyValues(jsonObj26, "name_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj26, "email_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj26, "mobile_number_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj26, "receive_newsletter_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj26, "receive_referral_earning_txt"), "account settings page");
		validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj26, "note_txt"), "account settings page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "save_changes_btn"), "account settings page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj26, "no_value_change_error_msg"), "account settings page");

		clickOnPartialRequiredText(retrieveChildKeyValues(jsonObj1, "change_password_tab"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj27, "change_password_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj27, "new_password_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj27, "confirm_password_inputfield_txt"), "account settings page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "save_changes_btn"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj27, "change_password_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "current_password_error_msg"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj27, "new_password_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "new_password_error_msg"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj27, "confirm_password_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "confirm_password_error_msg"), "account settings page");

		enterTextByXpath(textBoxExistingPwd, getTestData(7, "oneUserChar"));
		enterTextByXpath(textBoxNewPwd, getTestData(7, "oneUserChar"));
		enterTextByXpath(textBoxConfirmNewPwd, getTestData(7, "oneUserChar"));
		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "save_changes_btn"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj27, "change_password_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "password_minimum_characters_error_msg"), 1, "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj27, "new_password_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "password_minimum_characters_error_msg"), 2, "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj27, "confirm_password_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "password_minimum_characters_error_msg"), 3, "account settings page");

		enterTextByXpath(textBoxExistingPwd, getTestData(7, "FiftyUserChar"));
		enterTextByXpath(textBoxNewPwd, getTestData(7, "FiftyUserChar"));
		enterTextByXpath(textBoxConfirmNewPwd, getTestData(7, "FiftyUserChar"));
		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "save_changes_btn"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj27, "change_password_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "password_maximum_characters_error_msg"), 1, "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj27, "new_password_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "password_maximum_characters_error_msg"), 2, "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj27, "confirm_password_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "password_maximum_characters_error_msg"), 3, "account settings page");


		enterTextByXpath(textBoxExistingPwd, getTestData(0, "TC001_ValidLoginPassword"));
		enterTextByXpath(textBoxNewPwd, getTestData(0, "TC001_ValidLoginPassword"));
		enterTextByXpath(textBoxConfirmNewPwd, getTestData(0, "TC001_InValidPassword"));
		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "save_changes_btn"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj27, "change_password_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj27, "new_password_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj27, "confirm_password_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "password_not_match_error_msg"), "account settings page");

		clickOnPartialRequiredText(retrieveChildKeyValues(jsonObj1, "payment_settings_tab"), "account settings page");

		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(dropDownPaymentSettings);
		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(textPaytmWallet);
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "mobile_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "confirm_password_inputfield_txt"), "account settings page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "save_changes_btn"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "mobile_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "mobile_number_error_msg"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "confirm_password_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "password_error_msg"), "account settings page");

		enterTextByXpath(textBoxPaytmMobileNumber, "1");
		enterTextByXpath(textBoxPaytmPassword, "1");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "save_changes_btn"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "mobile_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "minimum_digits_error_msg"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "confirm_password_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "password_minimum_characters_error_msg"), "account settings page");

		enterTextByXpath(textBoxPaytmPassword, getTestData(7, "FiftyUserChar"));
		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "save_changes_btn"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "confirm_password_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "password_maximum_characters_error_msg"), "account settings page");

		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(dropDownPaymentSettings);
		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(textAmazonGiftCard);

		validateRequiredText(retrieveChildKeyValues(jsonObj40, "email_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "confirm_password_inputfield_txt"), "account settings page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "save_changes_btn"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "email_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "email_error_msg"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "confirm_password_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "password_error_msg"), "account settings page");

		enterTextByXpath(textBoxAmazonEmail, "1");
		enterTextByXpath(textBoxAmazonPassword, "1");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "save_changes_btn"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "email_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "minimum_characters_error_msg"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "confirm_password_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "password_minimum_characters_error_msg"), "account settings page");

		enterTextByXpath(textBoxAmazonEmail, "asdfghjkl");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "save_changes_btn"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "email_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "valid_email_error_msg"), "account settings page");

		enterTextByXpath(textBoxAmazonEmail, getTestData(7, "FiftyUserCharWithSpace"));
		enterTextByXpath(textBoxAmazonPassword, getTestData(7, "FiftyUserChar"));
		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "save_changes_btn"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "email_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "maximum_characters_error_msg"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "confirm_password_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "maximum_characters_error_msg"), "account settings page");

		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(dropDownPaymentSettings);
		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(textNEFT);

		validateRequiredText(retrieveChildKeyValues(jsonObj40, "bank_account_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "bank_name_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "branch_name_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "bank_account_number_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "bank_ifsc_code_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "confirm_password_inputfield_txt"), "account settings page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "save_changes_btn"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "bank_account_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "account_holder_error_msg"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "bank_name_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "valid_bank_name_error_msg"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "branch_name_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "branch_minimum_digits_error_msg"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "bank_account_number_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "account_number_error_msg"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "bank_ifsc_code_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "ifsc_code_error_msg"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "confirm_password_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "password_error_msg"), "account settings page");

	}

	@Test
	public void chooseLanguage_LanguageMalayalamValidation_ReferAndEarn() throws UnsupportedEncodingException, FileNotFoundException, IOException, ParseException {

		JSONObject jsonObj1 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "malayalamFileName"), getTestData(16, "common_label"));
		JSONObject jsonObj17 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "malayalamFileName"), getTestData(16, "profile_right_menu"));
		JSONObject jsonObj18 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "malayalamFileName"), getTestData(16, "referandearn"));

		reportStep("chooseLanguage_LanguageMalayalamValidation_AccountSettings started", "INFO");

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo);

		String emailAddress = objCashKaroUtility.generateEmail();
		String mobileNumber = objCashKaroUtility.generateMobileNumber();	

		String otpValue = new EKOnboardingPage(driver, testInfo).
				clickOnSignUpButton().
				enterFullName().
				enterEmail(emailAddress).
				enterPassword().
				enterMobileNumber(mobileNumber).
				clickOnGetOTPButton(mobileNumber);

		new OTPPage(driver, testInfo).
		enterOTP(otpValue).
		clickOnVerifyOTP();

		new HomePage(driver, testInfo).		
		clickOnProfileIconForSignedUser().
		clickOnChooseLanguage().
		clickMalayalamLink().
		clickContinueButton().
		clickRelaunchButton();

		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "profile_tab"), "home page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj17, "my_referral_menu"), "profile page");
		validateRequiredText(retrieveChildKeyValues(jsonObj17, "my_referral_menu"), "my referral page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj17, "referandearn_menu"), "my referral page");
		validateRequiredText(retrieveChildKeyValues(jsonObj17, "referandearn_menu"), "my referral page");
		scrollFromUpToDowninApp();scrollFromUpToDowninApp();
		validateRequiredText(retrieveChildKeyValues(jsonObj18, "title"), "my referral page");
		validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj18, "sub_title"), "my referral page");
		validateRequiredText(retrieveChildKeyValues(jsonObj18, "message"), "my referral page");
		validateRequiredText(retrieveChildKeyValues(jsonObj18, "referral_link_title"), "my referral page");
		validateRequiredText(retrieveChildKeyValues(jsonObj18, "copy_message_title"), "my referral page");
		validateRequiredText(retrieveChildKeyValues(jsonObj18, "tap_copy"), "my referral page");
		validateRequiredText(retrieveChildKeyValues(jsonObj18, "invite_title"), "my referral page");


	}

	@Test
	public void chooseLanguage_LanguageMalayalamValidation_ReferralNetwork() throws UnsupportedEncodingException, FileNotFoundException, IOException, ParseException {

		JSONObject jsonObj1 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "malayalamFileName"), getTestData(16, "common_label"));
		JSONObject jsonObj17 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "malayalamFileName"), getTestData(16, "profile_right_menu"));
		JSONObject jsonObj19 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "malayalamFileName"), getTestData(16, "referral_network"));

		reportStep("chooseLanguage_LanguageMalayalamValidation_AccountSettings started", "INFO");

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo);

		String emailAddress = objCashKaroUtility.generateEmail();
		String mobileNumber = objCashKaroUtility.generateMobileNumber();	

		String otpValue = new EKOnboardingPage(driver, testInfo).
				clickOnSignUpButton().
				enterFullName().
				enterEmail(emailAddress).
				enterPassword().
				enterMobileNumber(mobileNumber).
				clickOnGetOTPButton(mobileNumber);

		new OTPPage(driver, testInfo).
		enterOTP(otpValue).
		clickOnVerifyOTP();

		new HomePage(driver, testInfo).		
		clickOnProfileIconForSignedUser().
		clickOnChooseLanguage().
		clickMalayalamLink().
		clickContinueButton().
		clickRelaunchButton();

		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "profile_tab"), "home page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj17, "my_referral_menu"), "profile page");
		validateRequiredText(retrieveChildKeyValues(jsonObj17, "my_referral_menu"), "my referral page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj17, "referral_network_menu"), "profile page");
		validateRequiredText(retrieveChildKeyValues(jsonObj17, "referral_network_menu"), "referral nw page");
		validateRequiredText(retrieveChildKeyValues(jsonObj19, "title"), "referral nw page");
		validateRequiredText(retrieveChildKeyValues(jsonObj19, "total_referral_earn_txt"), "referral nw page");
		validateRequiredText(retrieveChildKeyValues(jsonObj19, "friend_joined_txt"), "referral nw page");
		validateRequiredText(retrieveChildKeyValues(jsonObj19, "no_referral_title"), "referral nw page");
		validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj19, "no_referral_message"), "referral nw page");
		validateRequiredText(retrieveChildKeyValues(jsonObj19, "refer_earn_btn"), "referral nw page");


	}

	@Test
	public void chooseLanguage_LanguageMalayalamValidation_MyEarnings_NewUser() throws UnsupportedEncodingException, FileNotFoundException, IOException, ParseException {

		JSONObject jsonObj1 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "malayalamFileName"), getTestData(16, "common_label"));
		JSONObject jsonObj4 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "malayalamFileName"), getTestData(16, "home"));
		JSONObject jsonObj17 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "malayalamFileName"), getTestData(16, "profile_right_menu"));
		JSONObject jsonObj28 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "malayalamFileName"), getTestData(16, "my_account_earnings"));
		JSONObject jsonObj30 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "malayalamFileName"), getTestData(16, "earnings_info"));

		JSONObject jsonObj33 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "malayalamFileName"), getTestData(16, "my_earnings"));

		reportStep("chooseLanguage_LanguageMalayalamValidation_MyEarnings_NewUser started", "INFO");

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo);

		String linkEarningsInfo = "//android.widget.TextView[@content-desc='txt_myEarnings_cashbackEarningsInfo']";

		String emailAddress = objCashKaroUtility.generateEmail();
		String mobileNumber = objCashKaroUtility.generateMobileNumber();	

		String otpValue = new EKOnboardingPage(driver, testInfo).
				clickOnSignUpButton().
				enterFullName().
				enterEmail(emailAddress).
				enterPassword().
				enterMobileNumber(mobileNumber).
				clickOnGetOTPButton(mobileNumber);

		new OTPPage(driver, testInfo).
		enterOTP(otpValue).
		clickOnVerifyOTP();

		new HomePage(driver, testInfo).		
		clickOnProfileIconForSignedUser().
		clickOnChooseLanguage().
		clickMalayalamLink().
		clickContinueButton().
		clickRelaunchButton();

		validateRequiredText(retrieveChildKeyValues(jsonObj4, "dynamic_home_top_Category"), "home page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "profile_tab"), "home page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj17, "my_earnings_menu"), "profile page");
		validateRequiredText(retrieveChildKeyValues(jsonObj17, "my_earnings_menu"), "my earnings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj28, "total_earning_txt"), "my earnings page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj33, "cashback_tap"), "my earnings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj33, "cashback_earning_title"), "my earnings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj33, "request_cashback_btn"), "my earnings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj33, "no_cashback_yet_txt"), "my earnings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj33, "shop_today_txt"), "my earnings page");

		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(linkEarningsInfo);
		validateRequiredText(retrieveChildKeyValues(jsonObj30, "message_first_txt"), "earnings info page");


		backButton();

		clickOnRequiredText(retrieveChildKeyValues(jsonObj33, "referral_tap"), "my earnings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj33, "referral_earning_title"), "my earnings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj33, "request_cashback_btn"), "my earnings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj33, "no_referral_yet_txt"), "my earnings page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj33, "no_referral_yet_sub_txt"), "my earnings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj33, "no_referral_invite_some_friends"), "my earnings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj33, "refer_now_btn"), "my earnings page");


	}

	@Test
	public void chooseLanguage_LanguageMalayalamValidation_MakeLinks_NewUser() throws UnsupportedEncodingException, FileNotFoundException, IOException, ParseException {

		JSONObject jsonObj1 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "malayalamFileName"), getTestData(16, "common_label"));
		JSONObject jsonObj2 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "malayalamFileName"), getTestData(16, "common_validate_label"));
		JSONObject jsonObj4 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "malayalamFileName"), getTestData(16, "home"));
		JSONObject jsonObj17 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "malayalamFileName"), getTestData(16, "profile_right_menu"));
		JSONObject jsonObj51 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "malayalamFileName"), getTestData(16, "make_profit_links"));
		JSONObject jsonObj53 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "malayalamFileName"), getTestData(16, "profit_partners_details"));
		JSONObject jsonObj54 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "malayalamFileName"), getTestData(16, "profit_partners_list"));

		reportStep("chooseLanguage_LanguageMalayalamValidation_MakeLinks_NewUser started", "INFO");

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo);

		String imagePartner = "//android.widget.ImageView[@content-desc='img_profitPartnersList_partnersIMG']";
		String emailAddress = objCashKaroUtility.generateEmail();
		String mobileNumber = objCashKaroUtility.generateMobileNumber();	

		String otpValue = new EKOnboardingPage(driver, testInfo).
				clickOnSignUpButton().
				enterFullName().
				enterEmail(emailAddress).
				enterPassword().
				enterMobileNumber(mobileNumber).
				clickOnGetOTPButton(mobileNumber);

		new OTPPage(driver, testInfo).
		enterOTP(otpValue).
		clickOnVerifyOTP();

		new HomePage(driver, testInfo).		
		clickOnProfileIconForSignedUser().
		clickOnChooseLanguage().
		clickMalayalamLink().
		clickContinueButton().
		clickRelaunchButton();

		validateRequiredText(retrieveChildKeyValues(jsonObj4, "dynamic_home_top_Category"), "home page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "profile_tab"), "home page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj17, "make_profit_link_menu"), "profile page");
		validateRequiredText(retrieveChildKeyValues(jsonObj51, "title"), "make links page");
		validateRequiredText(retrieveChildKeyValues(jsonObj51, "subtitle"), "make links page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj51, "see_partner_txt"), "make links page");
		validateRequiredText(retrieveChildKeyValues(jsonObj54, "toolbar_profit_partners_title"), "partners page");
		validateRequiredText(retrieveChildKeyValues(jsonObj54, "profit_partners_information"), "partners page");

		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(imagePartner);
		validateRequiredText(retrieveChildKeyValues(jsonObj53, "profit_rates"), "store page");

		backButton();
		backButton();

		validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj51, "paste_link"), "make links page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj51, "make_profit_link_txt"), "make links page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "url_error_msg"), "make links page");
		validateRequiredText(retrieveChildKeyValues(jsonObj51, "how_make_profit_txt"), "make links page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "url_error_msg"), "make links page");		
		validateRequiredText(retrieveChildKeyValues(jsonObj51, "step_one_title"), "make links page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj51, "step_one_txt"), "make links page");
		validateRequiredText(retrieveChildKeyValues(jsonObj51, "step_two_title"), "make links page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj51, "step_two_txt"), "make links page");
		validateRequiredText(retrieveChildKeyValues(jsonObj51, "step_three_title"), "make links page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj51, "step_three_txt"), "make links page");
		validateRequiredText(retrieveChildKeyValues(jsonObj51, "step_four_title"), "make links page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj51, "step_four_txt"), "make links page");


	}

	@Test
	public void chooseLanguage_LanguageMalayalamValidation_GetStarted_Joinfree_TermsAndConditions() throws UnsupportedEncodingException, FileNotFoundException, IOException, ParseException {

		JSONObject jsonObj1 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "malayalamFileName"), getTestData(16, "common_label"));
		JSONObject jsonObj2 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "malayalamFileName"), getTestData(16, "common_validate_label"));
		JSONObject jsonObj17 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "malayalamFileName"), getTestData(16, "profile_right_menu"));
		JSONObject jsonObj34 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "malayalamFileName"), getTestData(16, "on_boarding"));
		JSONObject jsonObj36 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "malayalamFileName"), getTestData(16, "join_free"));
		JSONObject jsonObj50 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "malayalamFileName"), getTestData(16, "terms_conditions"));

		reportStep("chooseLanguage_LanguageMalayalamValidation_GetStarted_Joinfree_Login_ForgotPassword started", "INFO");

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo);

		String textboxName = "//android.widget.EditText[@content-desc='et_signup_fullname']";
		String textboxEmail = "//android.widget.EditText[@content-desc='et_signup_email']|//android.widget.EditText[@content-desc='et_signin_email']|//android.widget.EditText[@content-desc='et_forgotpassword_email']";
		String textboxPwd = "//android.widget.EditText[@content-desc='et_signup_password']|//android.widget.EditText[@content-desc='et_signin_password']";
		String textboxMobileNumber = "//android.widget.EditText[@content-desc='et_signup_mobile']";		
		String emailAddress = objCashKaroUtility.generateEmail();
		String mobileNumber = objCashKaroUtility.generateMobileNumber();	

		String otpValue = new EKOnboardingPage(driver, testInfo).
				clickOnSignUpButton().
				enterFullName().
				enterEmail(emailAddress).
				enterPassword().
				enterMobileNumber(mobileNumber).
				clickOnGetOTPButton(mobileNumber);

		new OTPPage(driver, testInfo).
		enterOTP(otpValue).
		clickOnVerifyOTP();

		new HomePage(driver, testInfo).		
		clickOnProfileIconForSignedUser().
		clickOnChooseLanguage().
		clickMalayalamLink().
		clickContinueButton().
		clickRelaunchButton();

		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "profile_tab"), "home page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj17, "logout_menu"), "profile page");
		validateRequiredText(retrieveChildKeyValues(jsonObj17, "alert_logout"), "logout popup");
		validateRequiredText(retrieveChildKeyValues(jsonObj17, "alert_logout_msg"), "logout popup");
		validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj17, "alert_cancel_btn"), "logout popup");
		clickOnPartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj17, "alert_ok_btn"), "logout popup");

		new EKOnboardingPage(driver, testInfo).
		clickOnSignUpButton();

		validateRequiredText(retrieveChildKeyValues(jsonObj17, "join_free_menu"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "title"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "subtitle"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "join_facebook_txt"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "join_or_txt"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "name_inputfield_txt"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "email_inputfield_txt"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "password_inputfield_txt"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "mobile_inputfield_txt"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "otp_btn"), "join free page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj36, "already_register"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "sign_in_txt"), "join free page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj36, "signup_terms_txt"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "terms_condition_txt"), "join free page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj36, "otp_btn"), "join free page");		
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "name_inputfield_txt"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "name_error_msg"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "email_inputfield_txt"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "email_error_msg"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "password_inputfield_txt"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "password_error_msg"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "mobile_inputfield_txt"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "mobile_number_error_msg"), "join free page");

		enterTextByXpath(textboxName, getTestData(7, "oneUserChar"));
		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(textboxEmail);
		clickOnNoneOFTheAbove();
		enterTextByXpath(textboxEmail, getTestData(7, "oneUserChar"));
		enterTextByXpath(textboxPwd, getTestData(7, "oneUserChar"));
		enterTextByXpath(textboxMobileNumber, getTestData(7, "oneUserChar"));
		clickOnRequiredText(retrieveChildKeyValues(jsonObj36, "otp_btn"), "join free page");		
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "name_inputfield_txt"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "minimum_characters_error_msg"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "email_inputfield_txt"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "minimum_characters_error_msg"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "password_inputfield_txt"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "password_minimum_characters_error_msg"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "mobile_inputfield_txt"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "minimum_digits_error_msg"), "join free page");

		enterTextByXpath(textboxName, getTestData(7, "FiftyUserCharWithSpace"));
		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(textboxEmail);
		clickOnNoneOFTheAbove();
		enterTextByXpath(textboxEmail, getTestData(7, "FiftyUserCharWithSpace"));
		enterTextByXpath(textboxPwd, getTestData(7, "FiftyUserChar"));
		enterTextByXpath(textboxMobileNumber, "2222222222");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj36, "otp_btn"), "join free page");		
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "name_inputfield_txt"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "maximum_characters_error_msg"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "email_inputfield_txt"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "maximum_characters_error_msg"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "password_inputfield_txt"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "password_maximum_characters_error_msg"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "mobile_inputfield_txt"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "valid_mobile_number_error_msg"), "join free page");

		enterTextByXpath(textboxName, "2*2");
		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(textboxEmail);
		clickOnNoneOFTheAbove();
		enterTextByXpath(textboxEmail, "2*2");
		enterTextByXpath(textboxPwd, "2 2 2 2 2");
		enterTextByXpath(textboxMobileNumber, "2*22222222");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj36, "otp_btn"), "join free page");		
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "name_inputfield_txt"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "valid_name_error_msg"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "email_inputfield_txt"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "valid_email_error_msg"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "password_inputfield_txt"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "valid_password_error_msg"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "mobile_inputfield_txt"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "valid_mobile_number_error_msg"), "join free page");

		backButton();

		new EKOnboardingPage(driver, testInfo).
		clickOnSignUpButton();
		clickOnRequiredText(retrieveChildKeyValues(jsonObj36, "terms_condition_txt"), "join free page");
		scrollFromUpToDowninApp();scrollFromUpToDowninApp();
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "terms_condition_txt"), "terms & conditions page");
		validateRequiredText(retrieveChildKeyValues(jsonObj50, "title"), "terms & conditions page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj50, "introduction_title"), "terms & conditions page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj50, "introduction_details"), "terms & conditions page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj50, "membership_title"), "terms & conditions page");
		validateRequiredText(retrieveChildKeyValues(jsonObj50, "membership_details"), "terms & conditions page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj50, "our_service_title"), "terms & conditions page");
		validateRequiredText(retrieveChildKeyValues(jsonObj50, "our_service_details"), "terms & conditions page");


	}

	@Test
	public void chooseLanguage_LanguageMalayalamValidation_Login() throws UnsupportedEncodingException, FileNotFoundException, IOException, ParseException {

		JSONObject jsonObj1 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "malayalamFileName"), getTestData(16, "common_label"));
		JSONObject jsonObj2 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "malayalamFileName"), getTestData(16, "common_validate_label"));
		JSONObject jsonObj17 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "malayalamFileName"), getTestData(16, "profile_right_menu"));
		JSONObject jsonObj35 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "malayalamFileName"), getTestData(16, "sign_in"));
		JSONObject jsonObj36 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "malayalamFileName"), getTestData(16, "join_free"));

		reportStep("chooseLanguage_LanguageMalayalamValidation_GetStarted_Joinfree_Login_ForgotPassword started", "INFO");

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo);

		String textboxEmail = "//android.widget.EditText[@content-desc='et_signup_email']|//android.widget.EditText[@content-desc='et_signin_email']|//android.widget.EditText[@content-desc='et_forgotpassword_email']";
		String textboxPwd = "//android.widget.EditText[@content-desc='et_signup_password']|//android.widget.EditText[@content-desc='et_signin_password']";
		String emailAddress = objCashKaroUtility.generateEmail();
		String mobileNumber = objCashKaroUtility.generateMobileNumber();	

		String otpValue = new EKOnboardingPage(driver, testInfo).
				clickOnSignUpButton().
				enterFullName().
				enterEmail(emailAddress).
				enterPassword().
				enterMobileNumber(mobileNumber).
				clickOnGetOTPButton(mobileNumber);

		new OTPPage(driver, testInfo).
		enterOTP(otpValue).
		clickOnVerifyOTP();

		new HomePage(driver, testInfo).		
		clickOnProfileIconForSignedUser().
		clickOnChooseLanguage().
		clickMalayalamLink().
		clickContinueButton().
		clickRelaunchButton();

		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "profile_tab"), "home page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj17, "logout_menu"), "profile page");
		validateRequiredText(retrieveChildKeyValues(jsonObj17, "alert_logout"), "logout popup");
		validateRequiredText(retrieveChildKeyValues(jsonObj17, "alert_logout_msg"), "logout popup");
		validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj17, "alert_cancel_btn"), "logout popup");
		clickOnPartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj17, "alert_ok_btn"), "logout popup");

		new EKOnboardingPage(driver, testInfo).
		clickOnSignInbutton();

		validateRequiredText(retrieveChildKeyValues(jsonObj36, "sign_in_txt"), "login page");
		validateRequiredText(retrieveChildKeyValues(jsonObj35, "subtitle"), "login page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj35, "login_txt"), "login page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj35, "share_txt"), "login page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj35, "earn_txt"), "login page");
		validateRequiredText(retrieveChildKeyValues(jsonObj35, "subtitle"), "login page");
		validateRequiredText(retrieveChildKeyValues(jsonObj35, "sign_in_facebook_txt"), "login page");
		validateRequiredText(retrieveChildKeyValues(jsonObj35, "sign_in_or_txt"), "login page");
		validateRequiredText(retrieveChildKeyValues(jsonObj35, "email_inputfield_txt"), "login page");
		validateRequiredText(retrieveChildKeyValues(jsonObj35, "password_inputfield_txt"), "login page");
		validateRequiredText(retrieveChildKeyValues(jsonObj35, "forgot_password_txt"), "login page");
		validateRequiredText(retrieveChildKeyValues(jsonObj35, "sign_with_email_btn"), "login page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj35, "new_user_txt"), "login page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj35, "join_free_txt"), "login page");


		clickOnRequiredText(retrieveChildKeyValues(jsonObj35, "sign_with_email_btn"), "login page");
		validateRequiredText(retrieveChildKeyValues(jsonObj35, "email_inputfield_txt"), "login page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "email_error_msg"), "login page");
		validateRequiredText(retrieveChildKeyValues(jsonObj35, "password_inputfield_txt"), "login page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "password_error_msg"), "login page");

		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(textboxEmail);
		clickOnNoneOFTheAbove();
		enterTextByXpath(textboxEmail, getTestData(7, "oneUserChar"));
		enterTextByXpath(textboxPwd, getTestData(7, "oneUserChar"));
		clickOnRequiredText(retrieveChildKeyValues(jsonObj35, "sign_with_email_btn"), "login page");
		validateRequiredText(retrieveChildKeyValues(jsonObj35, "email_inputfield_txt"), "login page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "minimum_characters_error_msg"), "login page");
		validateRequiredText(retrieveChildKeyValues(jsonObj35, "password_inputfield_txt"), "login page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "password_minimum_characters_error_msg"), "login page");

		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(textboxEmail);
		clickOnNoneOFTheAbove();
		enterTextByXpath(textboxEmail, getTestData(7, "FiftyUserCharWithSpace"));
		enterTextByXpath(textboxPwd, getTestData(7, "FiftyUserChar"));
		clickOnRequiredText(retrieveChildKeyValues(jsonObj35, "sign_with_email_btn"), "login page");
		validateRequiredText(retrieveChildKeyValues(jsonObj35, "email_inputfield_txt"), "login page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "maximum_characters_error_msg"), "login page");
		validateRequiredText(retrieveChildKeyValues(jsonObj35, "password_inputfield_txt"), "login page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "password_maximum_characters_error_msg"), "login page");

		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(textboxEmail);
		clickOnNoneOFTheAbove();
		enterTextByXpath(textboxEmail, "2*2");
		enterTextByXpath(textboxPwd, "2 2 2 2 2");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj35, "sign_with_email_btn"), "login page");
		validateRequiredText(retrieveChildKeyValues(jsonObj35, "email_inputfield_txt"), "login page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "valid_email_error_msg"), "login page");
		validateRequiredText(retrieveChildKeyValues(jsonObj35, "password_inputfield_txt"), "login page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "valid_password_error_msg"), "login page");


	}

	@Test
	public void chooseLanguage_LanguageMalayalamValidationForgotPassword() throws UnsupportedEncodingException, FileNotFoundException, IOException, ParseException {

		JSONObject jsonObj1 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "malayalamFileName"), getTestData(16, "common_label"));
		JSONObject jsonObj2 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "malayalamFileName"), getTestData(16, "common_validate_label"));
		JSONObject jsonObj17 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "malayalamFileName"), getTestData(16, "profile_right_menu"));
		JSONObject jsonObj35 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "malayalamFileName"), getTestData(16, "sign_in"));
		JSONObject jsonObj37 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "malayalamFileName"), getTestData(16, "forgot_password"));

		reportStep("chooseLanguage_LanguageMalayalamValidation_GetStarted_Joinfree_Login_ForgotPassword started", "INFO");

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo);

		String textboxEmail = "//android.widget.EditText[@content-desc='et_signup_email']|//android.widget.EditText[@content-desc='et_signin_email']|//android.widget.EditText[@content-desc='et_forgotpassword_email']";
		String emailAddress = objCashKaroUtility.generateEmail();
		String mobileNumber = objCashKaroUtility.generateMobileNumber();	

		String otpValue = new EKOnboardingPage(driver, testInfo).
				clickOnSignUpButton().
				enterFullName().
				enterEmail(emailAddress).
				enterPassword().
				enterMobileNumber(mobileNumber).
				clickOnGetOTPButton(mobileNumber);

		new OTPPage(driver, testInfo).
		enterOTP(otpValue).
		clickOnVerifyOTP();

		new HomePage(driver, testInfo).		
		clickOnProfileIconForSignedUser().
		clickOnChooseLanguage().
		clickMalayalamLink().
		clickContinueButton().
		clickRelaunchButton();

		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "profile_tab"), "home page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj17, "logout_menu"), "profile page");
		validateRequiredText(retrieveChildKeyValues(jsonObj17, "alert_logout"), "logout popup");
		validateRequiredText(retrieveChildKeyValues(jsonObj17, "alert_logout_msg"), "logout popup");
		validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj17, "alert_cancel_btn"), "logout popup");
		clickOnPartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj17, "alert_ok_btn"), "logout popup");

		new EKOnboardingPage(driver, testInfo).
		clickOnSignInbutton();


		clickOnRequiredText(retrieveChildKeyValues(jsonObj35, "forgot_password_txt"), "login page");
		validateRequiredText(retrieveChildKeyValues(jsonObj35, "forgot_password_txt"), "forgot password page");
		validateRequiredText(retrieveChildKeyValues(jsonObj37, "title"), "forgot password page");
		validateRequiredText(retrieveChildKeyValues(jsonObj37, "subtitle"), "forgot password page");
		validateRequiredText(retrieveChildKeyValues(jsonObj37, "email_inputfield_txt"), "forgot password page");
		validateRequiredText(retrieveChildKeyValues(jsonObj37, "password_link_txt"), "forgot password page");
		validateRequiredText(retrieveChildKeyValues(jsonObj37, "submit_btn"), "forgot password page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj37, "remember_password_txt"), "forgot password page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj37, "login_txt"), "forgot password page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj37, "submit_btn"), "forgot password page");
		validateRequiredText(retrieveChildKeyValues(jsonObj37, "email_inputfield_txt"), "forgot password page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "email_error_msg"), "login page");

		enterTextByXpath(textboxEmail, getTestData(7, "oneUserChar"));		
		clickOnRequiredText(retrieveChildKeyValues(jsonObj37, "submit_btn"), "forgot password page");
		validateRequiredText(retrieveChildKeyValues(jsonObj37, "email_inputfield_txt"), "forgot password page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "minimum_characters_error_msg"), "login page");

		enterTextByXpath(textboxEmail, getTestData(7, "FiftyUserCharWithSpace"));		
		clickOnRequiredText(retrieveChildKeyValues(jsonObj37, "submit_btn"), "forgot password page");
		validateRequiredText(retrieveChildKeyValues(jsonObj37, "email_inputfield_txt"), "forgot password page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "maximum_characters_error_msg"), "login page");

		enterTextByXpath(textboxEmail, "2*2");		
		clickOnRequiredText(retrieveChildKeyValues(jsonObj37, "submit_btn"), "forgot password page");
		validateRequiredText(retrieveChildKeyValues(jsonObj37, "email_inputfield_txt"), "forgot password page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "valid_email_error_msg"), "login page");


	}

	@Test
	public void chooseLanguage_LanguageMalayalamValidation_MyEarnings_History_Payment_ExistingUser() throws UnsupportedEncodingException, FileNotFoundException, IOException, ParseException {

		JSONObject jsonObj1 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "malayalamFileName"), getTestData(16, "common_label"));
		JSONObject jsonObj2 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "malayalamFileName"), getTestData(16, "common_validate_label"));
		JSONObject jsonObj14 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "malayalamFileName"), getTestData(16, "search_result_all"));
		JSONObject jsonObj17 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "malayalamFileName"), getTestData(16, "profile_right_menu"));
		JSONObject jsonObj28 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "malayalamFileName"), getTestData(16, "my_account_earnings"));
		JSONObject jsonObj29 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "malayalamFileName"), getTestData(16, "total_cashback_earned"));
		JSONObject jsonObj30 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "malayalamFileName"), getTestData(16, "earnings_info"));		
		JSONObject jsonObj31 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "malayalamFileName"), getTestData(16, "earnings_referral"));
		JSONObject jsonObj32 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "malayalamFileName"), getTestData(16, "earnings_cashback"));
		JSONObject jsonObj33 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "malayalamFileName"), getTestData(16, "my_earnings"));
		JSONObject jsonObj35 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "malayalamFileName"), getTestData(16, "sign_in"));
		JSONObject jsonObj36 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "malayalamFileName"), getTestData(16, "join_free"));
		JSONObject jsonObj40 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "malayalamFileName"), getTestData(16, "giftcard_wallet_inputfield"));
		JSONObject jsonObj46 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "malayalamFileName"), getTestData(16, "payment"));
		JSONObject jsonObj52 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "malayalamFileName"), getTestData(16, "my_shared_activities"));
		JSONObject jsonObj53 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "malayalamFileName"), getTestData(16, "giftcard_wallet_request_payment"));

		reportStep("chooseLanguage_LanguageMalayalamValidation_MyEarnings_History_Payment_ExistingUser started", "INFO");

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo);

		String dropDownPaymentSettings = "(//android.widget.EditText)[1]";
		String textNEFT = "//android.widget.CheckedTextView[@text='Bank Payment (NEFT)']";
		String textPaytmWallet = "//android.widget.CheckedTextView[@text='Paytm Wallet']";
		String textAmazonGiftCard = "//android.widget.CheckedTextView[@text='Amazon Gift Card']";
		String textBoxPaytmMobileNumberPaymentRequestPage = "//android.widget.EditText[@content-desc='et_requestpaymentPaytm_mobile']";
		String textBoxAmazonEmailPaymentRequestPage = "//android.widget.EditText[@content-desc='et_requestpaymentAmazon_email']";
		String linkEarningsInfoPaymentPage = "//android.widget.TextView[@content-desc='txt_payment_earningsInfo']";	
		String textboxName = "//android.widget.EditText[@content-desc='et_signup_fullname']";
		String textboxEmail = "//android.widget.EditText[@content-desc='et_signup_email']|//android.widget.EditText[@content-desc='et_signin_email']|//android.widget.EditText[@content-desc='et_forgotpassword_email']";
		String textboxPwd = "//android.widget.EditText[@content-desc='et_signup_password']|//android.widget.EditText[@content-desc='et_signin_password']";
		String textboxMobileNumber = "//android.widget.EditText[@content-desc='et_signup_mobile']";
		String searchIcon = "(//android.widget.TextView)[2]";
		String textAreaSearchIcon = "//android.widget.EditText[@content-desc='et_search_enterValue']";

		String cbStoreNameOne = CashKaroUtility.extractValuesFromStoreCategoryJsonAndLoadTheVariablesForStore("CB_Store_One", 0, "CB_Store_One_Name");
		String linkURL = CashKaroUtility.extractValuesFromStoreCategoryJsonAndLoadTheVariablesForStore("CB_Store_One", 0, "CB_Store_One_linkURL");

		String emailAddress = objCashKaroUtility.generateEmail();
		String mobileNumber = objCashKaroUtility.generateMobileNumber();	

		String emailAddressReferralJoinee = objCashKaroUtility.generateEmail();
		String mobileNumberReferralJoinee = objCashKaroUtility.generateMobileNumber();	

		String otpValue = new EKOnboardingPage(driver, testInfo).
				clickOnSignUpButton().
				enterFullName().
				enterEmail(emailAddress).
				enterPassword().
				enterMobileNumber(mobileNumber).
				clickOnGetOTPButton(mobileNumber);

		new OTPPage(driver, testInfo).
		enterOTP(otpValue).
		clickOnVerifyOTP();

		new HomePage(driver, testInfo).		
		clickOnProfileIconForSignedUser().
		clickOnChooseLanguage().
		clickMalayalamLink().
		clickContinueButton().
		clickRelaunchButton();

		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "profile_tab"), "home page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj17, "logout_menu"), "profile page");
		validateRequiredText(retrieveChildKeyValues(jsonObj17, "alert_logout"), "logout popup");
		validateRequiredText(retrieveChildKeyValues(jsonObj17, "alert_logout_msg"), "logout popup");
		validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj17, "alert_cancel_btn"), "logout popup");
		clickOnPartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj17, "alert_ok_btn"), "logout popup");

		new EKOnboardingPage(driver, testInfo);

		CashKaroUtility cu = new CashKaroUtility(udid, port, automationName, deviceName);
		driver = cu.launchChromeWebView(driver);

		AdminCoreFunction adminFunctions = new AdminCoreFunction(driver, testInfo);
		adminFunctions.naviagateAdminPage_ThenSelectEarnKaro();
		adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
		adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
		adminFunctions.clickSubmit();

		AdminUsersPage usersPage = new AdminUsersPage(driver, testInfo);
		adminFunctions.clickOnHamburgerMenu();
		adminFunctions.clickUsersMenu();
		adminFunctions.clickUsersSubMenu();
		usersPage.selectSearchByDropDown("User Email");
		usersPage.enterKeyword(emailAddress);
		usersPage.clickSubmitButton();
		String userId = usersPage.extractUserIdValueFromResultstableFirstRow();
		String userName = usersPage.extractUserNameValueFromResultstableFirstRow();

		CashKaroUtility reopenCKApp1 = new CashKaroUtility(udid, port, automationName, deviceName, systemPort);
		driver = reopenCKApp1.launchEarnKaroApp(driver);

		CashKaroUtility objcashKaroutility = new CashKaroUtility(udid, port, automationName, deviceName);
		AppiumDriver<MobileElement> driver2 = objcashKaroutility.launchChrome();
		driver = driver2;

		String url = Utilities.referralLinkGetCode(userId, userName);

		objcashKaroutility.closeAllTabsAndOneTabInChromeBrowser(driver);
		objcashKaroutility.clickAndEnterURLInSearchOrTypeWebAddress(driver, url);

		objcashKaroutility.clickJoinFreeLink(driver);

		enterTextByXpath(textboxName, "ReferralJoinee");
		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(textboxEmail);
		clickOnNoneOFTheAbove();
		enterTextByXpath(textboxEmail, emailAddressReferralJoinee);
		enterTextByXpath(textboxPwd, "ppounds");
		enterTextByXpath(textboxMobileNumber, mobileNumberReferralJoinee);
		clickOnRequiredText(retrieveChildKeyValues(jsonObj36, "otp_btn"), "join free page");	

		new OTPPage(driver, testInfo);

		String otp = Utilities.getSignUPOTP(mobileNumberReferralJoinee);

		new OTPPage(driver, testInfo).
		enterOTP(otp);

		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "verify_otp"), "otp page");	

		sleep(15000);

		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(searchIcon);
		hideKeyboard();
		//validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj13, "search_inputfield_txt"), "search page");
		enterTextByXpath(textAreaSearchIcon, cbStoreNameOne);
		pressEnter();
		validateRequiredText(retrieveChildKeyValues(jsonObj14, "offer_count_single_message"), "search page");
		validateRequiredText(retrieveChildKeyValues(jsonObj1, "add_to_share"), "search page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "share_now"), "search page");

		WhatsAppUtility whatsAppUtility = new WhatsAppUtility(driver, testInfo, udid, port, automationName, deviceName, systemPort);
		whatsAppUtility.clickOnWhatAppMenuItemSearch().
		enterWhatsAppSearchText("Mallik").
		clickOnRequiredContact().
		clickOnSend().
		clickOnSend().
		clickOnLastSharedLink();

		new IntermediateRetailerPage(driver, testInfo).
		validateRetailerPage(linkURL, linkURL);

		CashKaroUtility cu2 = new CashKaroUtility(udid, port, automationName, deviceName);
		cu = cu2;
		driver = cu.launchChromeWebView(driver);

		AdminCoreFunction adminFunctions2 = new AdminCoreFunction(driver, testInfo);
		adminFunctions = adminFunctions2;
		adminFunctions.naviagateAdminPage_ThenSelectEarnKaro();
		adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
		adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
		adminFunctions.clickSubmit();

		AdminReportsPage reportsPage = new AdminReportsPage(driver, testInfo);
		adminFunctions.clickOnHamburgerMenu();
		adminFunctions.clickOnReports();
		reportsPage.clickExitClickSubMenu();
		reportsPage.clickClear();
		reportsPage.selectSearchByDropDown("User Email");
		reportsPage.enterKeyword(emailAddressReferralJoinee);
		reportsPage.clickSubmit();

		String exitClickOne = reportsPage.extractRequiredExitIdUsingStoreNameExitClickType(cbStoreNameOne, getTestData(7, "Normal"));

		AdminCashbackPage ProfitPage = new AdminCashbackPage(driver, testInfo);
		adminFunctions.clickOnHamburgerMenu();
		adminFunctions.clickCashbackMenu();
		ProfitPage.clickCashbackSubMenu();

		ProfitPage.clickAddNewButton();
		ProfitPage.enterExitId(exitClickOne);
		ProfitPage.entertransactionId(exitClickOne);
		ProfitPage.enterOrderId(exitClickOne);
		ProfitPage.setOrderDate();
		ProfitPage.enterOrderValue("1000");
		ProfitPage.enterconfirmCommisionNetwork(getTestData(11, "EightHundered"));
		ProfitPage.entercashback("700");
		ProfitPage.selectCashbackTypeFromDropDown(getTestData(11, "Cashback"));
		ProfitPage.selectCashbackStatusFromDropDown(getTestData(11, "Pending"));
		ProfitPage.setPendingDate();
		ProfitPage.enterDetails(exitClickOne);
		ProfitPage.clickSubmit();
		ProfitPage.clickClear();

		CashKaroUtility reopenCKAppAgain = new CashKaroUtility(udid, port, automationName, deviceName, systemPort);
		driver = reopenCKAppAgain.launchEarnKaroApp(driver);
		new HomePage(driver, testInfo);

		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "profile_tab"), "home page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj17, "history_menu"), "profile page");
		validateRequiredText(retrieveChildKeyValues(jsonObj1, "recent_clicks_tab"), "history page");
		validateRequiredText(retrieveChildKeyValues(jsonObj52, "title"), "history page");
		validateRequiredText(retrieveChildKeyValues(jsonObj52, "shared_link_details"), "history page");
		validateRequiredText(retrieveChildKeyValues(jsonObj52, "shared_id"), "history page");
		validateRequiredText(retrieveChildKeyValues(jsonObj52, "shared_date_time"), "history page");
		validateRequiredText(retrieveChildKeyValues(jsonObj52, "retailer_name"), "history page");
		validateRequiredText(retrieveChildKeyValues(jsonObj52, "no_of_clicks"), "history page");
		validateRequiredText(retrieveChildKeyValues(jsonObj52, "no_of_transactions"), "history page");
		validateRequiredText(retrieveChildKeyValues(jsonObj52, "total_earning"), "history page");

		backButton();

		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "profile_tab"), "home page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj17, "my_earnings_menu"), "profile page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj29, "total_cashback_earned_txt"), "my earnings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj29, "total_cashback_earned_txt"), 1, "profit page");
		validateRequiredText(retrieveChildKeyValues(jsonObj29, "total_cashback_earned_txt"), 2, "profit page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj29, "paid_cashback_txt"), "profit page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj29, "pending_cashback_txt"), "profit page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj29, "referral_cashback_txt"), "profit page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj29, "available_cashback_txt"), "profit page");

		backButton();

		validateRequiredText(retrieveChildKeyValues(jsonObj32, "order_details"), "my earnings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj32, "date"), "my earnings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj32, "retailer"), "my earnings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj32, "order_amount"), "my earnings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj32, "cashback_amount"), "my earnings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj32, "status"), "my earnings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj32, "expected_date"), "my earnings page");

		backButton();

		clickOnRequiredText(retrieveChildKeyValues(jsonObj17, "logout_menu"), "profile page");
		validateRequiredText(retrieveChildKeyValues(jsonObj17, "alert_logout"), "logout popup");
		validateRequiredText(retrieveChildKeyValues(jsonObj17, "alert_logout_msg"), "logout popup");
		validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj17, "alert_cancel_btn"), "logout popup");
		clickOnPartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj17, "alert_ok_btn"), "logout popup");

		new EKOnboardingPage(driver, testInfo);

		CashKaroUtility cu3 = new CashKaroUtility(udid, port, automationName, deviceName);
		cu = cu3;
		driver = cu.launchChromeWebView(driver);

		AdminCoreFunction adminFunctions3 = new AdminCoreFunction(driver, testInfo);
		adminFunctions = adminFunctions3;
		adminFunctions.naviagateAdminPage_ThenSelectEarnKaro();
		adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
		adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
		adminFunctions.clickSubmit();

		AdminCashbackPage ProfitPage1 = new AdminCashbackPage(driver, testInfo);
		ProfitPage = ProfitPage1;
		adminFunctions.clickOnHamburgerMenu();
		adminFunctions.clickCashbackMenu();
		ProfitPage.clickCashbackSubMenu();

		ProfitPage.selectSearchByStatusFromDropDown("Exit ID");
		ProfitPage.enterKeyword(exitClickOne);
		ProfitPage.selectCashbackStatusFromDropDown(getTestData(11, "Pending"));
		ProfitPage.clickSubmit();
		ProfitPage.clickEdit();
		ProfitPage.selectCashbackStatusFromDropDown(getTestData(11, "Confirmed"));
		ProfitPage.setConfirmDate();
		ProfitPage.clickSubmit();
		ProfitPage.clickClear();

		CashKaroUtility reopenCKAppOnceAgain = new CashKaroUtility(udid, port, automationName, deviceName, systemPort);
		driver = reopenCKAppOnceAgain.launchEarnKaroApp(driver);

		new EKOnboardingPage(driver, testInfo).clickOnSignInbutton();

		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(textboxEmail);
		clickOnNoneOFTheAbove();
		enterTextByXpath(textboxEmail, emailAddress);
		enterTextByXpath(textboxPwd, "ppounds");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj35, "sign_with_email_btn"), "login page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "profile_tab"), "home page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj17, "my_earnings_menu"), "profile page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj33, "referral_tap"), "my earnings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj31, "order_details"), "my earnings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj31, "date"), "my earnings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj31, "referral_name"), "my earnings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj31, "cashback"), "my earnings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj31, "status"), "my earnings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj33, "refer_now_btn"), "my earnings page");

		backButton();

		clickOnRequiredText(retrieveChildKeyValues(jsonObj17, "logout_menu"), "profile page");
		validateRequiredText(retrieveChildKeyValues(jsonObj17, "alert_logout"), "logout popup");
		validateRequiredText(retrieveChildKeyValues(jsonObj17, "alert_logout_msg"), "logout popup");
		validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj17, "alert_cancel_btn"), "logout popup");
		clickOnPartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj17, "alert_ok_btn"), "logout popup");

		new EKOnboardingPage(driver, testInfo).clickOnSignInbutton();

		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(textboxEmail);
		clickOnNoneOFTheAbove();
		enterTextByXpath(textboxEmail, emailAddressReferralJoinee);
		enterTextByXpath(textboxPwd, "ppounds");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj35, "sign_with_email_btn"), "login page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "profile_tab"), "home page");
		validateRequiredText(retrieveChildKeyValues(jsonObj1, "profile_tab"), "profile page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj17, "hello_user_txt"), "profile page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj17, "total_cashback_txt"), "profile page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj17, "payment_menu"), "profile page");

		validateRequiredText(retrieveChildKeyValues(jsonObj17, "payment_menu"), "profile page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj29, "total_cashback_earned_txt"), "payments page");
		validateRequiredText(retrieveChildKeyValues(jsonObj29, "total_cashback_earned_txt"), 1, "profit page");
		validateRequiredText(retrieveChildKeyValues(jsonObj29, "total_cashback_earned_txt"), 2, "profit page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj29, "paid_cashback_txt"), "profit page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj29, "pending_cashback_txt"), "profit page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj29, "referral_cashback_txt"), "profit page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj29, "available_cashback_txt"), "profit page");

		backButton();

		validatePartialRequiredText(retrieveChildKeyValues(jsonObj28, "tap_to_see_details_txt"), "payments page");
		validateRequiredText(retrieveChildKeyValues(jsonObj46, "cashback_earnings"), "payments page");
		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(linkEarningsInfoPaymentPage);
		validateRequiredText(retrieveChildKeyValues(jsonObj30, "message_first_txt"), "earnings info page");


		backButton();

		validateRequiredText(retrieveChildKeyValues(jsonObj46, "request_cashback_btn"), "payments page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj46, "static_text_title"), "payments page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj46, "static_text_1"), "payments page");


		clickOnRequiredText(retrieveChildKeyValues(jsonObj46, "request_cashback_btn"), "payments page");

		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(dropDownPaymentSettings);
		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(textPaytmWallet);
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "mobile_inputfield_txt"), "payment request page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "get_paid_btn"), "payment req page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "mobile_inputfield_txt"), "payment request page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "mobile_number_error_msg"), "payment request page");

		enterTextByXpath(textBoxPaytmMobileNumberPaymentRequestPage, "1");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "get_paid_btn"), "payment req page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "mobile_inputfield_txt"), "payment request page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "minimum_digits_error_msg"), "payment request page");

		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(dropDownPaymentSettings);
		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(textAmazonGiftCard);

		validateRequiredText(retrieveChildKeyValues(jsonObj53, "email_inputfield_txt"), "payment request page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "get_paid_btn"), "payment req page");
		validateRequiredText(retrieveChildKeyValues(jsonObj53, "email_inputfield_txt"), "payment request page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "email_error_msg"), "payment request page");

		enterTextByXpath(textBoxAmazonEmailPaymentRequestPage, "1");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "get_paid_btn"), "payment req page");
		validateRequiredText(retrieveChildKeyValues(jsonObj53, "email_inputfield_txt"), "payment request page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "minimum_characters_error_msg"), "payment request page");

		enterTextByXpath(textBoxAmazonEmailPaymentRequestPage, "asdfghjkl");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "get_paid_btn"), "payment req page");
		validateRequiredText(retrieveChildKeyValues(jsonObj53, "email_inputfield_txt"), "payment request page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "valid_email_error_msg"), "payment request page");

		enterTextByXpath(textBoxAmazonEmailPaymentRequestPage, getTestData(7, "FiftyUserCharWithSpace"));
		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "get_paid_btn"), "payment req page");
		validateRequiredText(retrieveChildKeyValues(jsonObj53, "email_inputfield_txt"), "payment request page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "maximum_characters_error_msg"), "payment request page");

		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(dropDownPaymentSettings);
		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(textNEFT);

		validateRequiredText(retrieveChildKeyValues(jsonObj40, "bank_account_inputfield_txt"), "payment request page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "bank_name_inputfield_txt"), "payment request page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "branch_name_inputfield_txt"), "payment request page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "bank_account_number_inputfield_txt"), "payment request page");
		validateRequiredText(retrieveChildKeyValues(jsonObj53, "bank_ifsc_code_inputfield_txt"), "payment request page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "get_paid_btn"), "payment req page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "bank_account_inputfield_txt"), "payment request page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "account_holder_error_msg"), "payment request page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "bank_name_inputfield_txt"), "payment request page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "valid_bank_name_error_msg"), "payment request page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "branch_name_inputfield_txt"), "payment request page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "branch_minimum_digits_error_msg"), "payment request page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "bank_account_number_inputfield_txt"), "payment request page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "account_number_error_msg"), "payment request page");
		validateRequiredText(retrieveChildKeyValues(jsonObj53, "bank_ifsc_code_inputfield_txt"), "payment request page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "ifsc_code_error_msg"), "payment request page");

	}

	@Test
	public void chooseLanguage_LanguageMalayalamValidation_StoreCategory_StoreDetail() throws UnsupportedEncodingException, FileNotFoundException, IOException, ParseException {

		JSONObject jsonObj1 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "malayalamFileName"), getTestData(16, "common_label"));
		JSONObject jsonObj3 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "malayalamFileName"), getTestData(16, "main_menu"));
		JSONObject jsonObj5 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "malayalamFileName"), getTestData(16, "store_category"));
		JSONObject jsonObj12 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "malayalamFileName"), getTestData(16, "share_counts"));
		JSONObject jsonObj17 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "malayalamFileName"), getTestData(16, "profile_right_menu"));

		reportStep("chooseLanguage_LanguageMalayalamValidation_Store_Product_StoreCategory started", "INFO");

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo);

		String shareButtonWithCount = "//android.view.ViewGroup[@content-desc='txt_sharecount_open_modal']/android.view.ViewGroup/android.widget.TextView[@text='1']";
		String imageStoreCard = "//android.widget.ImageView[@content-desc='img_storeCard_imageValue']";
		String emailAddress = objCashKaroUtility.generateEmail();
		String mobileNumber = objCashKaroUtility.generateMobileNumber();	

		String otpValue = new EKOnboardingPage(driver, testInfo).
				clickOnSignUpButton().
				enterFullName().
				enterEmail(emailAddress).
				enterPassword().
				enterMobileNumber(mobileNumber).
				clickOnGetOTPButton(mobileNumber);

		new OTPPage(driver, testInfo).
		enterOTP(otpValue).
		clickOnVerifyOTP();

		new HomePage(driver, testInfo).		
		clickOnProfileIconForSignedUser().
		clickOnChooseLanguage().
		clickMalayalamLink().
		clickContinueButton().
		clickRelaunchButton();

		validateRequiredText(retrieveChildKeyValues(jsonObj3, "home_lbl"), "home page");

		new HomePage(driver, testInfo).
		clickOnHamburgerIcon();

		validateRequiredText(retrieveChildKeyValues(jsonObj3, "home_lbl"), "home page");

		new SliderScreenPage(driver, testInfo).
		clickRetailerCategory().
		verifyRetailerCategoryMenuNavigation().
		clickAutomationSpecificCategoryInRetailerCategoryMenuUntilAllSubCategoryVisible().
		clickAutomationSpecificSubCategoryOne();

		validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj5, "popular"), "store category page");
		validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj5, "az"), "store category page");
		validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj5, "percent"), "store category page");
		validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj5, "amount"), "store category page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "add_to_share"), "store category page");
		validateRequiredText(retrieveChildKeyValues(jsonObj1, "share_now"), "store category page");
		validateRequiredText(retrieveChildKeyValues(jsonObj1, "remove"), "store category page");

		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(shareButtonWithCount);
		validateRequiredText(retrieveChildKeyValues(jsonObj1, "share_now"), "store category page");
		validateRequiredText(retrieveChildKeyValues(jsonObj1, "copy_Link"), "store category page");
		validateRequiredText(retrieveChildKeyValues(jsonObj12, "close"), "store category page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj12, "clear"), "store category page");

		validateRequiredText(retrieveChildKeyValues(jsonObj12, "clear"), "store category page");
		validateRequiredText(retrieveChildKeyValues(jsonObj12, "confirm_msg_clear_share"), "store category page");
		validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj17, "alert_cancel_btn"), "store category page");
		clickOnPartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj17, "alert_ok_btn"), "store category page");

		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(imageStoreCard);
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj1, "you_will_earn"), "store detail page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "add_to_share"), "store detail page");
		validateRequiredText(retrieveChildKeyValues(jsonObj1, "share_now"), "store detail page");
		validateRequiredText(retrieveChildKeyValues(jsonObj1, "remove"), "store category page");

		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(shareButtonWithCount);
		validateRequiredText(retrieveChildKeyValues(jsonObj1, "share_now"), "store detail page");
		validateRequiredText(retrieveChildKeyValues(jsonObj1, "copy_Link"), "store detail page");
		validateRequiredText(retrieveChildKeyValues(jsonObj12, "close"), "store detail page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj12, "clear"), "store detail page");

		validateRequiredText(retrieveChildKeyValues(jsonObj12, "clear"), "store detail page");
		validateRequiredText(retrieveChildKeyValues(jsonObj12, "confirm_msg_clear_share"), "store detail page");
		validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj17, "alert_cancel_btn"), "store detail page");
		clickOnPartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj17, "alert_ok_btn"), "store detail page");

		new StoreDetailPage(driver, testInfo);

	}

	@Test
	public void chooseLanguage_LanguageMalayalamValidation_Product() throws UnsupportedEncodingException, FileNotFoundException, IOException, ParseException {

		JSONObject jsonObj1 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "malayalamFileName"), getTestData(16, "common_label"));
		JSONObject jsonObj3 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "malayalamFileName"), getTestData(16, "main_menu"));
		JSONObject jsonObj6 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "malayalamFileName"), getTestData(16, "prduct_category"));
		JSONObject jsonObj9 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "malayalamFileName"), getTestData(16, "product_details"));
		JSONObject jsonObj10 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "malayalamFileName"), getTestData(16, "product_card"));
		JSONObject jsonObj49 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "malayalamFileName"), getTestData(16, "prduct_filter"));

		reportStep("chooseLanguage_LanguageMalayalamValidation_Store_Product_StoreCategory started", "INFO");

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo);

		String menFashionCategory = "//android.view.ViewGroup[@content-desc='txt_mainMenu_productDealsTop']";
		String tshirtCategory = "//android.widget.TextView[@content-desc='txt_subMenuProduct_mainMenuName']";
		String menfashionLink = "//android.widget.TextView[@content-desc='txt_filter_category_name']";
		String imageProductCard = "//android.widget.ImageView[@content-desc='img_product_image']";
		String productcard = "(//android.widget.ImageView)[3]";		
		String emailAddress = objCashKaroUtility.generateEmail();
		String mobileNumber = objCashKaroUtility.generateMobileNumber();	

		String otpValue = new EKOnboardingPage(driver, testInfo).
				clickOnSignUpButton().
				enterFullName().
				enterEmail(emailAddress).
				enterPassword().
				enterMobileNumber(mobileNumber).
				clickOnGetOTPButton(mobileNumber);

		new OTPPage(driver, testInfo).
		enterOTP(otpValue).
		clickOnVerifyOTP();

		new HomePage(driver, testInfo).		
		clickOnProfileIconForSignedUser().
		clickOnChooseLanguage().
		clickMalayalamLink().
		clickContinueButton().
		clickRelaunchButton();

		validateRequiredText(retrieveChildKeyValues(jsonObj3, "home_lbl"), "home page");

		new HomePage(driver, testInfo).
		clickOnHamburgerIcon();

		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(menFashionCategory);
		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(tshirtCategory);

		clickOnPartialRequiredText(retrieveChildKeyValues(jsonObj6, "filter"), "product page");
		validateRequiredText(retrieveChildKeyValues(jsonObj49, "filter_by"), "product page");
		validateRequiredText(retrieveChildKeyValues(jsonObj49, "clerar_all"), "product page");
		validateRequiredText(retrieveChildKeyValues(jsonObj49, "close_btn"), "product page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj49, "apply_btn"), "product page");

		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(menfashionLink);
		clickOnRequiredText(retrieveChildKeyValues(jsonObj49, "clerar_all"), "product page");
		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(menfashionLink);
		clickOnRequiredText(retrieveChildKeyValues(jsonObj49, "close_btn"), "product page");
		validateRequiredText(retrieveChildKeyValues(jsonObj49, "discard_changes_title"), "product page");
		validateRequiredText(retrieveChildKeyValues(jsonObj49, "discard_changes_msg"), "product page");
		validateRequiredText(retrieveChildKeyValues(jsonObj49, "apply_changes_btn"), "product page");
		validateRequiredText(retrieveChildKeyValues(jsonObj49, "discard_changes_btn"), "product page");

		backButton();

		clickOnPartialRequiredText(retrieveChildKeyValues(jsonObj6, "sort"), "product page");
		validateRequiredText(retrieveChildKeyValues(jsonObj6, "sort_title"), "product page");
		validateRequiredText(retrieveChildKeyValues(jsonObj6, "sort_popular"), "product page");
		validateRequiredText(retrieveChildKeyValues(jsonObj6, "sort_discount"), "product page");
		validateRequiredText(retrieveChildKeyValues(jsonObj6, "sort_high_price"), "product page");
		validateRequiredText(retrieveChildKeyValues(jsonObj6, "sort_low_price"), "product page");

		backButton();

		validatePartialRequiredText(retrieveChildKeyValues(jsonObj10, "brand"), "product page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj10, "price"), "product page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj10, "sale"), "product page");
		validateRequiredText(retrieveChildKeyValues(jsonObj1, "add_to_share"), "product page");
		validateRequiredText(retrieveChildKeyValues(jsonObj1, "share_now"), "product page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj10, "see_details"), "product page");

		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(imageProductCard);
		clickOnRequiredText(retrieveChildKeyValues(jsonObj9, "seller_price"), "product detail page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "you_will_earn"), "product detail page");
		validateRequiredText(retrieveChildKeyValues(jsonObj1, "add_to_share"), "product page");
		validateRequiredText(retrieveChildKeyValues(jsonObj1, "share_now"), "product page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj9, "product_highlights"), "product detail page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj9, "similar_products"), "product detail page");



	}

	@Test
	public void chooseLanguage_LanguageTeluguValidation_SuccessStories() throws UnsupportedEncodingException, FileNotFoundException, IOException, ParseException {

		JSONObject jsonObj1 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "teluguFileName"), getTestData(16, "common_label"));
		JSONObject jsonObj4 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "teluguFileName"), getTestData(16, "home"));
		JSONObject jsonObj17 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "teluguFileName"), getTestData(16, "profile_right_menu"));
		JSONObject jsonObj22 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "teluguFileName"), getTestData(16, "success_stories"));

		reportStep("chooseLanguage_LanguageTeluguValidation_SuccessStories started", "INFO");

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo);

		String emailAddress = objCashKaroUtility.generateEmail();
		String mobileNumber = objCashKaroUtility.generateMobileNumber();	

		String otpValue = new EKOnboardingPage(driver, testInfo).
				clickOnSignUpButton().
				enterFullName().
				enterEmail(emailAddress).
				enterPassword().
				enterMobileNumber(mobileNumber).
				clickOnGetOTPButton(mobileNumber);

		new OTPPage(driver, testInfo).
		enterOTP(otpValue).
		clickOnVerifyOTP();

		new HomePage(driver, testInfo).		
		clickOnProfileIconForSignedUser().
		clickOnChooseLanguage().
		clickTeluguLink().
		clickContinueButton().
		clickRelaunchButton();

		validateRequiredText(retrieveChildKeyValues(jsonObj4, "dynamic_home_top_Category"), "home page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "profile_tab"), "home page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj17, "success_stories_menu"), "profile page");
		validateRequiredText(retrieveChildKeyValues(jsonObj17, "success_stories_menu"), "success stories page");
		scrollFromUpToDowninApp();scrollFromUpToDowninApp();
		validateRequiredText(retrieveChildKeyValues(jsonObj22, "title"), "success stories page");
		validateRequiredText(retrieveChildKeyValues(jsonObj22, "testimonial_txt1"), "success stories page");
		validateRequiredText(retrieveChildKeyValues(jsonObj22, "testimonial_name1"), "success stories page");
		validateRequiredText(retrieveChildKeyValues(jsonObj22, "testimonial_txt2"), "success stories page");
		validateRequiredText(retrieveChildKeyValues(jsonObj22, "testimonial_name2"), "success stories page");
		validateRequiredText(retrieveChildKeyValues(jsonObj22, "testimonial_txt3"), "success stories page");
		validateRequiredText(retrieveChildKeyValues(jsonObj22, "testimonial_name3"), "success stories page");
		validateRequiredText(retrieveChildKeyValues(jsonObj22, "testimonial_txt4"), "success stories page");
		validateRequiredText(retrieveChildKeyValues(jsonObj22, "testimonial_name4"), "success stories page");
		validateRequiredText(retrieveChildKeyValues(jsonObj22, "testimonial_txt5"), "success stories page");
		validateRequiredText(retrieveChildKeyValues(jsonObj22, "testimonial_name5"), "success stories page");


	}

	@Test
	public void chooseLanguage_LanguageTeluguValidation_HowItWorks() throws UnsupportedEncodingException, FileNotFoundException, IOException, ParseException {

		JSONObject jsonObj1 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "teluguFileName"), getTestData(16, "common_label"));
		JSONObject jsonObj4 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "teluguFileName"), getTestData(16, "home"));
		JSONObject jsonObj17 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "teluguFileName"), getTestData(16, "profile_right_menu"));
		JSONObject jsonObj23 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "teluguFileName"), getTestData(16, "how_it_works"));

		reportStep("chooseLanguage_LanguageTeluguValidation_HowItWorks started", "INFO");

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo);

		String emailAddress = objCashKaroUtility.generateEmail();
		String mobileNumber = objCashKaroUtility.generateMobileNumber();	

		String otpValue = new EKOnboardingPage(driver, testInfo).
				clickOnSignUpButton().
				enterFullName().
				enterEmail(emailAddress).
				enterPassword().
				enterMobileNumber(mobileNumber).
				clickOnGetOTPButton(mobileNumber);

		new OTPPage(driver, testInfo).
		enterOTP(otpValue).
		clickOnVerifyOTP();

		new HomePage(driver, testInfo).		
		clickOnProfileIconForSignedUser().
		clickOnChooseLanguage().
		clickTeluguLink().
		clickContinueButton().
		clickRelaunchButton();

		validateRequiredText(retrieveChildKeyValues(jsonObj4, "dynamic_home_top_Category"), "home page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "profile_tab"), "home page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj17, "how_it_works_menu"), "profile page");
		scrollFromUpToDowninApp();scrollFromUpToDowninApp();
		validateRequiredText(retrieveChildKeyValues(jsonObj17, "how_it_works_menu"), "how earnkaro works? page");
		validateRequiredText(retrieveChildKeyValues(jsonObj23, "watch_video_title1"), "how earnkaro works? page");
		validateRequiredText(retrieveChildKeyValues(jsonObj23, "watch_video_txt"), 1, "how earnkaro works? page");
		validateRequiredText(retrieveChildKeyValues(jsonObj23, "watch_video_title2"), "how earnkaro works? page");
		validateRequiredText(retrieveChildKeyValues(jsonObj23, "watch_video_txt"), 2, "how earnkaro works? page");
		validateRequiredText(retrieveChildKeyValues(jsonObj23, "watch_video_title3"), "how earnkaro works? page");
		validateRequiredText(retrieveChildKeyValues(jsonObj23, "watch_video_txt"), 3, "how earnkaro works? page");
		validateRequiredText(retrieveChildKeyValues(jsonObj23, "watch_video_title4"), "how earnkaro works? page");
		validateRequiredText(retrieveChildKeyValues(jsonObj23, "watch_video_txt"), 4, "how earnkaro works? page");
		validateRequiredText(retrieveChildKeyValues(jsonObj23, "watch_video_title5"), "how earnkaro works? page");
		validateRequiredText(retrieveChildKeyValues(jsonObj23, "watch_video_txt"), 5, "how earnkaro works? page");
		validateRequiredText(retrieveChildKeyValues(jsonObj23, "watch_video_title6"), "how earnkaro works? page");
		validateRequiredText(retrieveChildKeyValues(jsonObj23, "watch_video_txt"), 6, "how earnkaro works? page");



	}

	@Test
	public void chooseLanguage_LanguageTeluguValidation_Testimonials() throws UnsupportedEncodingException, FileNotFoundException, IOException, ParseException {

		JSONObject jsonObj1 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "teluguFileName"), getTestData(16, "common_label"));
		JSONObject jsonObj4 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "teluguFileName"), getTestData(16, "home"));
		JSONObject jsonObj17 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "teluguFileName"), getTestData(16, "profile_right_menu"));
		JSONObject jsonObj24 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "teluguFileName"), getTestData(16, "testimonial"));
		JSONObject jsonObj25 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "teluguFileName"), getTestData(16, "testimonial_write"));

		reportStep("chooseLanguage_LanguageTeluguValidation_Testimonials started", "INFO");

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo);

		String fourStarTestimonials = "(//android.widget.TextView)[8]";
		String threeStarTestimonials = "(//android.widget.TextView)[7]";
		String twoStarTestimonials = "(//android.widget.TextView)[6]";
		String oneStarTestimonials = "(//android.widget.TextView)[5]";

		String emailAddress = objCashKaroUtility.generateEmail();
		String mobileNumber = objCashKaroUtility.generateMobileNumber();	

		String otpValue = new EKOnboardingPage(driver, testInfo).
				clickOnSignUpButton().
				enterFullName().
				enterEmail(emailAddress).
				enterPassword().
				enterMobileNumber(mobileNumber).
				clickOnGetOTPButton(mobileNumber);

		new OTPPage(driver, testInfo).
		enterOTP(otpValue).
		clickOnVerifyOTP();

		new HomePage(driver, testInfo).		
		clickOnProfileIconForSignedUser().
		clickOnChooseLanguage().
		clickTeluguLink().
		clickContinueButton().
		clickRelaunchButton();

		validateRequiredText(retrieveChildKeyValues(jsonObj4, "dynamic_home_top_Category"), "home page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "profile_tab"), "home page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj17, "rate_us_menu"), "profile page");
		validateRequiredText(retrieveChildKeyValues(jsonObj24, "count_txt"), "testimonials page");
		validateRequiredText(retrieveChildKeyValues(jsonObj24, "happy_users_txt"), "testimonials page");

		clickOnPartialRequiredText(retrieveChildKeyValues(jsonObj24, "write_testimonial_btn"), "testimonials page");
		validateRequiredText(retrieveChildKeyValues(jsonObj24, "write_testimonial_btn"), "write testimonials page");
		validateRequiredText(retrieveChildKeyValues(jsonObj25, "title"), "write testimonials page");

		validateRequiredText(retrieveChildKeyValues(jsonObj25, "rate_experience_txt"), "write testimonials page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj25, "star_txt_awesome_experience"), "write testimonials page");
		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(fourStarTestimonials);
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj25, "star_txt_good_experience"), "write testimonials page");
		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(threeStarTestimonials);
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj25, "star_txt_average"), "write testimonials page");
		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(twoStarTestimonials);
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj25, "star_txt_not_great"), "write testimonials page");
		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(oneStarTestimonials);
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj25, "star_txt_terrible"), "write testimonials page");


	}

	@Test
	public void chooseLanguage_LanguageTeluguValidation_ContactUs() throws UnsupportedEncodingException, FileNotFoundException, IOException, ParseException {

		JSONObject jsonObj1 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "teluguFileName"), getTestData(16, "common_label"));
		JSONObject jsonObj2 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "teluguFileName"), getTestData(16, "common_validate_label"));
		JSONObject jsonObj4 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "teluguFileName"), getTestData(16, "home"));
		JSONObject jsonObj17 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "teluguFileName"), getTestData(16, "profile_right_menu"));
		JSONObject jsonObj20 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "teluguFileName"), getTestData(16, "contact_us"));		
		JSONObject jsonObj21 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "teluguFileName"), getTestData(16, "faq"));

		reportStep("chooseLanguage_LanguageTeluguValidation_ContactUs started", "INFO");

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo);

		String textBoxUserName = "//android.widget.EditText[@content-desc='et_contactUs_yourName']";
		String textBoxEmail = "//android.widget.EditText[@content-desc='et_contactUs_email']";

		String emailAddress = objCashKaroUtility.generateEmail();
		String mobileNumber = objCashKaroUtility.generateMobileNumber();	

		String otpValue = new EKOnboardingPage(driver, testInfo).
				clickOnSignUpButton().
				enterFullName().
				enterEmail(emailAddress).
				enterPassword().
				enterMobileNumber(mobileNumber).
				clickOnGetOTPButton(mobileNumber);

		new OTPPage(driver, testInfo).
		enterOTP(otpValue).
		clickOnVerifyOTP();

		new HomePage(driver, testInfo).		
		clickOnProfileIconForSignedUser().
		clickOnChooseLanguage().
		clickTeluguLink().
		clickContinueButton().
		clickRelaunchButton();

		validateRequiredText(retrieveChildKeyValues(jsonObj4, "dynamic_home_top_Category"), "home page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "profile_tab"), "home page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj17, "help"), "profile page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj17, "contact_us_menu"), "help page");
		enterTextByXpath(textBoxUserName, "..");
		enterTextByXpath(textBoxEmail, "..");
		validateRequiredText(retrieveChildKeyValues(jsonObj21, "faq_btn"), "contact us page");
		validateRequiredText(retrieveChildKeyValues(jsonObj20, "name_inputfield_txt"), "contact us page");
		validateRequiredText(retrieveChildKeyValues(jsonObj20, "email_inputfield_txt"), "contact us page");
		validateRequiredText(retrieveChildKeyValues(jsonObj20, "message_inputfield_txt"), "contact us page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj20, "submit_btn"), "contact us page");

		validateRequiredText(retrieveChildKeyValues(jsonObj2, "select_subject_error_msg"), "contact us page");
		validateRequiredText(retrieveChildKeyValues(jsonObj20, "name_inputfield_txt"), "contact us page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "valid_name_error_msg"), "contact us page");
		validateRequiredText(retrieveChildKeyValues(jsonObj20, "email_inputfield_txt"), "contact us page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "valid_email_error_msg"), "contact us page");
		validateRequiredText(retrieveChildKeyValues(jsonObj20, "message_inputfield_txt"), "contact us page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "valid_details_error_msg"), "contact us page");

		enterTextByXpath(textBoxUserName, getTestData(7, "oneUserChar"));
		enterTextByXpath(textBoxEmail, getTestData(7, "oneUserChar"));
		clickOnRequiredText(retrieveChildKeyValues(jsonObj20, "submit_btn"), "contact us page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "select_subject_error_msg"), "contact us page");
		validateRequiredText(retrieveChildKeyValues(jsonObj20, "name_inputfield_txt"), "contact us page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "minimum_characters_error_msg"), 1, "contact us page");
		validateRequiredText(retrieveChildKeyValues(jsonObj20, "email_inputfield_txt"), "contact us page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "minimum_characters_error_msg"), 2, "contact us page");
		validateRequiredText(retrieveChildKeyValues(jsonObj20, "message_inputfield_txt"), "contact us page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "valid_details_error_msg"), "contact us page");

		enterTextByXpath(textBoxUserName, getTestData(7, "FiftyPlusUserChar"));
		enterTextByXpath(textBoxEmail, getTestData(7, "FiftyPlusUserChar"));
		clickOnRequiredText(retrieveChildKeyValues(jsonObj20, "submit_btn"), "contact us page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "select_subject_error_msg"), "contact us page");
		validateRequiredText(retrieveChildKeyValues(jsonObj20, "name_inputfield_txt"), "contact us page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "maximum_characters_error_msg"), 1, "contact us page");
		validateRequiredText(retrieveChildKeyValues(jsonObj20, "email_inputfield_txt"), "contact us page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "maximum_characters_error_msg"), 2, "contact us page");
		validateRequiredText(retrieveChildKeyValues(jsonObj20, "message_inputfield_txt"), "contact us page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "valid_details_error_msg"), "contact us page");

	}

	@Test
	public void chooseLanguage_LanguageTeluguValidation_FAQs() throws UnsupportedEncodingException, FileNotFoundException, IOException, ParseException {

		JSONObject jsonObj1 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "teluguFileName"), getTestData(16, "common_label"));
		JSONObject jsonObj4 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "teluguFileName"), getTestData(16, "home"));
		JSONObject jsonObj17 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "teluguFileName"), getTestData(16, "profile_right_menu"));		
		JSONObject jsonObj21 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "teluguFileName"), getTestData(16, "faq"));

		reportStep("chooseLanguage_LanguageTeluguValidation_FAQs started", "INFO");

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo);

		String emailAddress = objCashKaroUtility.generateEmail();
		String mobileNumber = objCashKaroUtility.generateMobileNumber();	

		String otpValue = new EKOnboardingPage(driver, testInfo).
				clickOnSignUpButton().
				enterFullName().
				enterEmail(emailAddress).
				enterPassword().
				enterMobileNumber(mobileNumber).
				clickOnGetOTPButton(mobileNumber);

		new OTPPage(driver, testInfo).
		enterOTP(otpValue).
		clickOnVerifyOTP();

		new HomePage(driver, testInfo).		
		clickOnProfileIconForSignedUser().
		clickOnChooseLanguage().
		clickTeluguLink().
		clickContinueButton().
		clickRelaunchButton();

		validateRequiredText(retrieveChildKeyValues(jsonObj4, "dynamic_home_top_Category"), "home page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "profile_tab"), "home page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj17, "help"), "profile page");

		validateRequiredText(retrieveChildKeyValues(jsonObj17, "call_us_menu"), "help page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj17, "faq_menu"), "help page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj21, "question1"), "faq page");
		//validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj21, "answer1"), "faq page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj21, "question2"), "faq page");
		//validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj21, "answer2"), "faq page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj21, "question3"), "faq page");
		//validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj21, "answer3"), "faq page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj21, "question4"), "faq page");
		//validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj21, "answer4"), "faq page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj21, "question5"), "faq page");
		//validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj21, "answer5"), "faq page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj21, "question6"), "faq page");
		//validatePartialRequiredText(retrieveChildKeyValues(jsonObj21, "answer6"), "faq page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj21, "question7"), "faq page");
		//validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj21, "answer7"), "faq page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj21, "question8"), "faq page");
		//validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj21, "answer8"), "faq page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj21, "question9"), "faq page");
		//validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj21, "answer9"), "faq page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj21, "question10"), "faq page");
		//validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj21, "answer10"), "faq page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj21, "question11"), "faq page");
		//validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj21, "answer11"), "faq page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj21, "question12"), "faq page");
		//validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj21, "answer12"), "faq page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj21, "question13"), "faq page");
		//validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj21, "answer13"), "faq page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj21, "question14"), "faq page");
		//validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj21, "answer14"), "faq page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj21, "question15"), "faq page");
		//validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj21, "answer15"), "faq page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj21, "question16"), "faq page");
		//validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj21, "answer16"), "faq page");

		validateRequiredText(retrieveChildKeyValues(jsonObj21, "faq_btn"), "faq page");


	}

	@Test
	public void chooseLanguage_LanguageTeluguValidation_History_NewUser() throws UnsupportedEncodingException, FileNotFoundException, IOException, ParseException {

		JSONObject jsonObj1 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "teluguFileName"), getTestData(16, "common_label"));
		JSONObject jsonObj4 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "teluguFileName"), getTestData(16, "home"));
		JSONObject jsonObj17 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "teluguFileName"), getTestData(16, "profile_right_menu"));
		JSONObject jsonObj52 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "teluguFileName"), getTestData(16, "my_shared_activities"));

		reportStep("chooseLanguage_LanguageTeluguValidation_History_NewUser started", "INFO");

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo);

		String emailAddress = objCashKaroUtility.generateEmail();
		String mobileNumber = objCashKaroUtility.generateMobileNumber();	

		String otpValue = new EKOnboardingPage(driver, testInfo).
				clickOnSignUpButton().
				enterFullName().
				enterEmail(emailAddress).
				enterPassword().
				enterMobileNumber(mobileNumber).
				clickOnGetOTPButton(mobileNumber);

		new OTPPage(driver, testInfo).
		enterOTP(otpValue).
		clickOnVerifyOTP();

		new HomePage(driver, testInfo).		
		clickOnProfileIconForSignedUser().
		clickOnChooseLanguage().
		clickTeluguLink().
		clickContinueButton().
		clickRelaunchButton();

		validateRequiredText(retrieveChildKeyValues(jsonObj4, "dynamic_home_top_Category"), "home page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "profile_tab"), "home page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj17, "history_menu"), "profile page");
		validateRequiredText(retrieveChildKeyValues(jsonObj17, "recent_clicks_menu"), "history page");
		validateRequiredText(retrieveChildKeyValues(jsonObj1, "whoops_msg"), "history page");
		validateRequiredText(retrieveChildKeyValues(jsonObj52, "no_shared_activites"), "history page");
		validateRequiredText(retrieveChildKeyValues(jsonObj52, "saving_click_away"), "history page");


	}

	@Test
	public void chooseLanguage_LanguageTeluguValidation_AccountSettings() throws UnsupportedEncodingException, FileNotFoundException, IOException, ParseException {

		JSONObject jsonObj1 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "teluguFileName"), getTestData(16, "common_label"));
		JSONObject jsonObj2 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "teluguFileName"), getTestData(16, "common_validate_label"));
		JSONObject jsonObj17 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "teluguFileName"), getTestData(16, "profile_right_menu"));
		JSONObject jsonObj26 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "teluguFileName"), getTestData(16, "personal_details"));
		JSONObject jsonObj27 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "teluguFileName"), getTestData(16, "change_password"));
		JSONObject jsonObj40 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "teluguFileName"), getTestData(16, "giftcard_wallet_inputfield"));

		reportStep("chooseLanguage_LanguageTeluguValidation_AccountSettings started", "INFO");

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo);

		String textBoxExistingPwd = "//android.widget.EditText[@content-desc='et_changePassword_currentPassword']";
		String textBoxNewPwd = "//android.widget.EditText[@content-desc='et_changePassword_fullName']";
		String textBoxConfirmNewPwd = "//android.widget.EditText[@content-desc='et_changePassword_confirmPassword']";

		String dropDownPaymentSettings = "(//android.widget.EditText)[1]";
		String textNEFT = "//android.widget.CheckedTextView[@text='Bank Payment (NEFT)']";
		String textPaytmWallet = "//android.widget.CheckedTextView[@text='Paytm Wallet']";
		String textAmazonGiftCard = "//android.widget.CheckedTextView[@text='Amazon Gift Card']";

		String textBoxPaytmMobileNumber = "//android.widget.EditText[@content-desc='et_settingsPaytm_mobile']";
		String textBoxPaytmPassword = "//android.widget.EditText[@content-desc='et_settingsPaytm_password']";

		String textBoxAmazonEmail = "//android.widget.EditText[@content-desc='et_settingsAmazon_email']";
		String textBoxAmazonPassword = "//android.widget.EditText[@content-desc='et_settingsAmazon_password']";
		String emailAddress = objCashKaroUtility.generateEmail();
		String mobileNumber = objCashKaroUtility.generateMobileNumber();	

		String otpValue = new EKOnboardingPage(driver, testInfo).
				clickOnSignUpButton().
				enterFullName().
				enterEmail(emailAddress).
				enterPassword().
				enterMobileNumber(mobileNumber).
				clickOnGetOTPButton(mobileNumber);

		new OTPPage(driver, testInfo).
		enterOTP(otpValue).
		clickOnVerifyOTP();

		new HomePage(driver, testInfo).		
		clickOnProfileIconForSignedUser().
		clickOnChooseLanguage().
		clickTeluguLink().
		clickContinueButton().
		clickRelaunchButton();

		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "profile_tab"), "home page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj17, "account_setting_menu"), "profile page");
		validateRequiredText(retrieveChildKeyValues(jsonObj17, "account_setting_menu"), "profile page");
		clickOnPartialRequiredText(retrieveChildKeyValues(jsonObj1, "personal_details_tab"), "account settings page");
		scrollFromUpToDowninApp();scrollFromUpToDowninApp();
		validateRequiredText(retrieveChildKeyValues(jsonObj26, "name_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj26, "email_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj26, "mobile_number_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj26, "receive_newsletter_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj26, "receive_referral_earning_txt"), "account settings page");
		validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj26, "note_txt"), "account settings page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "save_changes_btn"), "account settings page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj26, "no_value_change_error_msg"), "account settings page");

		clickOnPartialRequiredText(retrieveChildKeyValues(jsonObj1, "change_password_tab"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj27, "change_password_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj27, "new_password_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj27, "confirm_password_inputfield_txt"), "account settings page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "save_changes_btn"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj27, "change_password_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "current_password_error_msg"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj27, "new_password_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "new_password_error_msg"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj27, "confirm_password_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "confirm_password_error_msg"), "account settings page");

		enterTextByXpath(textBoxExistingPwd, getTestData(7, "oneUserChar"));
		enterTextByXpath(textBoxNewPwd, getTestData(7, "oneUserChar"));
		enterTextByXpath(textBoxConfirmNewPwd, getTestData(7, "oneUserChar"));
		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "save_changes_btn"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj27, "change_password_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "password_minimum_characters_error_msg"), 1, "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj27, "new_password_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "password_minimum_characters_error_msg"), 2, "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj27, "confirm_password_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "password_minimum_characters_error_msg"), 3, "account settings page");

		enterTextByXpath(textBoxExistingPwd, getTestData(7, "FiftyUserChar"));
		enterTextByXpath(textBoxNewPwd, getTestData(7, "FiftyUserChar"));
		enterTextByXpath(textBoxConfirmNewPwd, getTestData(7, "FiftyUserChar"));
		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "save_changes_btn"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj27, "change_password_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "password_maximum_characters_error_msg"), 1, "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj27, "new_password_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "password_maximum_characters_error_msg"), 2, "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj27, "confirm_password_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "password_maximum_characters_error_msg"), 3, "account settings page");


		enterTextByXpath(textBoxExistingPwd, getTestData(0, "TC001_ValidLoginPassword"));
		enterTextByXpath(textBoxNewPwd, getTestData(0, "TC001_ValidLoginPassword"));
		enterTextByXpath(textBoxConfirmNewPwd, getTestData(0, "TC001_InValidPassword"));
		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "save_changes_btn"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj27, "change_password_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj27, "new_password_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj27, "confirm_password_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "password_not_match_error_msg"), "account settings page");

		clickOnPartialRequiredText(retrieveChildKeyValues(jsonObj1, "payment_settings_tab"), "account settings page");

		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(dropDownPaymentSettings);
		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(textPaytmWallet);
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "mobile_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "confirm_password_inputfield_txt"), "account settings page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "save_changes_btn"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "mobile_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "mobile_number_error_msg"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "confirm_password_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "password_error_msg"), "account settings page");

		enterTextByXpath(textBoxPaytmMobileNumber, "1");
		enterTextByXpath(textBoxPaytmPassword, "1");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "save_changes_btn"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "mobile_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "minimum_digits_error_msg"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "confirm_password_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "password_minimum_characters_error_msg"), "account settings page");

		enterTextByXpath(textBoxPaytmPassword, getTestData(7, "FiftyUserChar"));
		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "save_changes_btn"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "confirm_password_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "password_maximum_characters_error_msg"), "account settings page");

		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(dropDownPaymentSettings);
		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(textAmazonGiftCard);

		validateRequiredText(retrieveChildKeyValues(jsonObj40, "email_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "confirm_password_inputfield_txt"), "account settings page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "save_changes_btn"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "email_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "email_error_msg"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "confirm_password_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "password_error_msg"), "account settings page");

		enterTextByXpath(textBoxAmazonEmail, "1");
		enterTextByXpath(textBoxAmazonPassword, "1");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "save_changes_btn"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "email_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "minimum_characters_error_msg"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "confirm_password_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "password_minimum_characters_error_msg"), "account settings page");

		enterTextByXpath(textBoxAmazonEmail, "asdfghjkl");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "save_changes_btn"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "email_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "valid_email_error_msg"), "account settings page");

		enterTextByXpath(textBoxAmazonEmail, getTestData(7, "FiftyUserCharWithSpace"));
		enterTextByXpath(textBoxAmazonPassword, getTestData(7, "FiftyUserChar"));
		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "save_changes_btn"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "email_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "maximum_characters_error_msg"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "confirm_password_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "maximum_characters_error_msg"), "account settings page");

		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(dropDownPaymentSettings);
		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(textNEFT);

		validateRequiredText(retrieveChildKeyValues(jsonObj40, "bank_account_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "bank_name_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "branch_name_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "bank_account_number_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "bank_ifsc_code_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "confirm_password_inputfield_txt"), "account settings page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "save_changes_btn"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "bank_account_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "account_holder_error_msg"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "bank_name_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "valid_bank_name_error_msg"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "branch_name_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "branch_minimum_digits_error_msg"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "bank_account_number_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "account_number_error_msg"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "bank_ifsc_code_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "ifsc_code_error_msg"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "confirm_password_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "password_error_msg"), "account settings page");

	}

	@Test
	public void chooseLanguage_LanguageTeluguValidation_ReferAndEarn() throws UnsupportedEncodingException, FileNotFoundException, IOException, ParseException {

		JSONObject jsonObj1 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "teluguFileName"), getTestData(16, "common_label"));
		JSONObject jsonObj17 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "teluguFileName"), getTestData(16, "profile_right_menu"));
		JSONObject jsonObj18 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "teluguFileName"), getTestData(16, "referandearn"));

		reportStep("chooseLanguage_LanguageTeluguValidation_AccountSettings started", "INFO");

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo);

		String emailAddress = objCashKaroUtility.generateEmail();
		String mobileNumber = objCashKaroUtility.generateMobileNumber();	

		String otpValue = new EKOnboardingPage(driver, testInfo).
				clickOnSignUpButton().
				enterFullName().
				enterEmail(emailAddress).
				enterPassword().
				enterMobileNumber(mobileNumber).
				clickOnGetOTPButton(mobileNumber);

		new OTPPage(driver, testInfo).
		enterOTP(otpValue).
		clickOnVerifyOTP();

		new HomePage(driver, testInfo).		
		clickOnProfileIconForSignedUser().
		clickOnChooseLanguage().
		clickTeluguLink().
		clickContinueButton().
		clickRelaunchButton();

		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "profile_tab"), "home page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj17, "my_referral_menu"), "profile page");
		validateRequiredText(retrieveChildKeyValues(jsonObj17, "my_referral_menu"), "my referral page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj17, "referandearn_menu"), "my referral page");
		validateRequiredText(retrieveChildKeyValues(jsonObj17, "referandearn_menu"), "my referral page");
		scrollFromUpToDowninApp();scrollFromUpToDowninApp();
		validateRequiredText(retrieveChildKeyValues(jsonObj18, "title"), "my referral page");
		validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj18, "sub_title"), "my referral page");
		validateRequiredText(retrieveChildKeyValues(jsonObj18, "message"), "my referral page");
		validateRequiredText(retrieveChildKeyValues(jsonObj18, "referral_link_title"), "my referral page");
		validateRequiredText(retrieveChildKeyValues(jsonObj18, "copy_message_title"), "my referral page");
		validateRequiredText(retrieveChildKeyValues(jsonObj18, "tap_copy"), "my referral page");
		validateRequiredText(retrieveChildKeyValues(jsonObj18, "invite_title"), "my referral page");


	}

	@Test
	public void chooseLanguage_LanguageTeluguValidation_ReferralNetwork() throws UnsupportedEncodingException, FileNotFoundException, IOException, ParseException {

		JSONObject jsonObj1 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "teluguFileName"), getTestData(16, "common_label"));
		JSONObject jsonObj17 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "teluguFileName"), getTestData(16, "profile_right_menu"));
		JSONObject jsonObj19 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "teluguFileName"), getTestData(16, "referral_network"));

		reportStep("chooseLanguage_LanguageTeluguValidation_AccountSettings started", "INFO");

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo);

		String emailAddress = objCashKaroUtility.generateEmail();
		String mobileNumber = objCashKaroUtility.generateMobileNumber();	

		String otpValue = new EKOnboardingPage(driver, testInfo).
				clickOnSignUpButton().
				enterFullName().
				enterEmail(emailAddress).
				enterPassword().
				enterMobileNumber(mobileNumber).
				clickOnGetOTPButton(mobileNumber);

		new OTPPage(driver, testInfo).
		enterOTP(otpValue).
		clickOnVerifyOTP();

		new HomePage(driver, testInfo).		
		clickOnProfileIconForSignedUser().
		clickOnChooseLanguage().
		clickTeluguLink().
		clickContinueButton().
		clickRelaunchButton();

		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "profile_tab"), "home page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj17, "my_referral_menu"), "profile page");
		validateRequiredText(retrieveChildKeyValues(jsonObj17, "my_referral_menu"), "my referral page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj17, "referral_network_menu"), "profile page");
		validateRequiredText(retrieveChildKeyValues(jsonObj17, "referral_network_menu"), "referral nw page");
		validateRequiredText(retrieveChildKeyValues(jsonObj19, "title"), "referral nw page");
		validateRequiredText(retrieveChildKeyValues(jsonObj19, "total_referral_earn_txt"), "referral nw page");
		validateRequiredText(retrieveChildKeyValues(jsonObj19, "friend_joined_txt"), "referral nw page");
		validateRequiredText(retrieveChildKeyValues(jsonObj19, "no_referral_title"), "referral nw page");
		validateRequiredText(retrieveChildKeyValues(jsonObj19, "no_referral_message"), "referral nw page");
		validateRequiredText(retrieveChildKeyValues(jsonObj19, "refer_earn_btn"), "referral nw page");


	}

	@Test
	public void chooseLanguage_LanguageTeluguValidation_MyEarnings_NewUser() throws UnsupportedEncodingException, FileNotFoundException, IOException, ParseException {

		JSONObject jsonObj1 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "teluguFileName"), getTestData(16, "common_label"));
		JSONObject jsonObj4 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "teluguFileName"), getTestData(16, "home"));
		JSONObject jsonObj17 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "teluguFileName"), getTestData(16, "profile_right_menu"));
		JSONObject jsonObj28 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "teluguFileName"), getTestData(16, "my_account_earnings"));
		JSONObject jsonObj30 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "teluguFileName"), getTestData(16, "earnings_info"));

		JSONObject jsonObj33 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "teluguFileName"), getTestData(16, "my_earnings"));

		reportStep("chooseLanguage_LanguageTeluguValidation_MyEarnings_NewUser started", "INFO");

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo);

		String linkEarningsInfo = "//android.widget.TextView[@content-desc='txt_myEarnings_cashbackEarningsInfo']";

		String emailAddress = objCashKaroUtility.generateEmail();
		String mobileNumber = objCashKaroUtility.generateMobileNumber();	

		String otpValue = new EKOnboardingPage(driver, testInfo).
				clickOnSignUpButton().
				enterFullName().
				enterEmail(emailAddress).
				enterPassword().
				enterMobileNumber(mobileNumber).
				clickOnGetOTPButton(mobileNumber);

		new OTPPage(driver, testInfo).
		enterOTP(otpValue).
		clickOnVerifyOTP();

		new HomePage(driver, testInfo).		
		clickOnProfileIconForSignedUser().
		clickOnChooseLanguage().
		clickTeluguLink().
		clickContinueButton().
		clickRelaunchButton();

		validateRequiredText(retrieveChildKeyValues(jsonObj4, "dynamic_home_top_Category"), "home page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "profile_tab"), "home page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj17, "my_earnings_menu"), "profile page");
		validateRequiredText(retrieveChildKeyValues(jsonObj17, "my_earnings_menu"), "my earnings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj28, "total_earning_txt"), "my earnings page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj33, "cashback_tap"), "my earnings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj33, "cashback_earning_title"), "my earnings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj33, "request_cashback_btn"), "my earnings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj33, "no_cashback_yet_txt"), "my earnings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj33, "shop_today_txt"), "my earnings page");

		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(linkEarningsInfo);
		validateRequiredText(retrieveChildKeyValues(jsonObj30, "message_first_txt"), "earnings info page");


		backButton();

		clickOnRequiredText(retrieveChildKeyValues(jsonObj33, "referral_tap"), "my earnings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj33, "referral_earning_title"), "my earnings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj33, "request_cashback_btn"), "my earnings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj33, "no_referral_yet_txt"), "my earnings page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj33, "no_referral_yet_sub_txt"), "my earnings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj33, "no_referral_invite_some_friends"), "my earnings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj33, "refer_now_btn"), "my earnings page");


	}

	@Test
	public void chooseLanguage_LanguageTeluguValidation_MakeLinks_NewUser() throws UnsupportedEncodingException, FileNotFoundException, IOException, ParseException {

		JSONObject jsonObj1 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "teluguFileName"), getTestData(16, "common_label"));
		JSONObject jsonObj2 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "teluguFileName"), getTestData(16, "common_validate_label"));
		JSONObject jsonObj4 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "teluguFileName"), getTestData(16, "home"));
		JSONObject jsonObj17 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "teluguFileName"), getTestData(16, "profile_right_menu"));
		JSONObject jsonObj51 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "teluguFileName"), getTestData(16, "make_profit_links"));
		JSONObject jsonObj53 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "teluguFileName"), getTestData(16, "profit_partners_details"));
		JSONObject jsonObj54 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "teluguFileName"), getTestData(16, "profit_partners_list"));

		reportStep("chooseLanguage_LanguageTeluguValidation_MakeLinks_NewUser started", "INFO");

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo);

		String imagePartner = "//android.widget.ImageView[@content-desc='img_profitPartnersList_partnersIMG']";
		String emailAddress = objCashKaroUtility.generateEmail();
		String mobileNumber = objCashKaroUtility.generateMobileNumber();	

		String otpValue = new EKOnboardingPage(driver, testInfo).
				clickOnSignUpButton().
				enterFullName().
				enterEmail(emailAddress).
				enterPassword().
				enterMobileNumber(mobileNumber).
				clickOnGetOTPButton(mobileNumber);

		new OTPPage(driver, testInfo).
		enterOTP(otpValue).
		clickOnVerifyOTP();

		new HomePage(driver, testInfo).		
		clickOnProfileIconForSignedUser().
		clickOnChooseLanguage().
		clickTeluguLink().
		clickContinueButton().
		clickRelaunchButton();

		validateRequiredText(retrieveChildKeyValues(jsonObj4, "dynamic_home_top_Category"), "home page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "profile_tab"), "home page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj17, "make_profit_link_menu"), "profile page");
		validateRequiredText(retrieveChildKeyValues(jsonObj51, "title"), "make links page");
		validateRequiredText(retrieveChildKeyValues(jsonObj51, "subtitle"), "make links page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj51, "see_partner_txt"), "make links page");
		validateRequiredText(retrieveChildKeyValues(jsonObj54, "toolbar_profit_partners_title"), "partners page");
		validateRequiredText(retrieveChildKeyValues(jsonObj54, "profit_partners_information"), "partners page");

		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(imagePartner);
		validateRequiredText(retrieveChildKeyValues(jsonObj53, "profit_rates"), "store page");

		backButton();
		backButton();

		validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj51, "paste_link"), "make links page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj51, "make_profit_link_txt"), "make links page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "url_error_msg"), "make links page");
		validateRequiredText(retrieveChildKeyValues(jsonObj51, "how_make_profit_txt"), "make links page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "url_error_msg"), "make links page");		

		//Here no need to scroll : since the swiping happens from left to right
		scrollFromDownToUpinApp();
		validateRequiredText_WithOutScrolling(retrieveChildKeyValues(jsonObj51, "step_one_title"), "make links page");
		validateRequiredText_WithOutScrolling(retrieveChildKeyValues(jsonObj51, "step_one_txt"), "make links page");
		validateRequiredText_WithOutScrolling(retrieveChildKeyValues(jsonObj51, "step_two_title"), "make links page");
		validateRequiredText_WithOutScrolling(retrieveChildKeyValues(jsonObj51, "step_two_txt"), "make links page");
		validateRequiredText_WithOutScrolling(retrieveChildKeyValues(jsonObj51, "step_three_title"), "make links page");
		validateRequiredText_WithOutScrolling(retrieveChildKeyValues(jsonObj51, "step_three_txt"), "make links page");
		validateRequiredText_WithOutScrolling(retrieveChildKeyValues(jsonObj51, "step_four_title"), "make links page");
		validateRequiredText_WithOutScrolling(retrieveChildKeyValues(jsonObj51, "step_four_txt"), "make links page");


	}

	@Test
	public void chooseLanguage_LanguageTeluguValidation_GetStarted_Joinfree_TermsAndConditions() throws UnsupportedEncodingException, FileNotFoundException, IOException, ParseException {

		JSONObject jsonObj1 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "teluguFileName"), getTestData(16, "common_label"));
		JSONObject jsonObj2 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "teluguFileName"), getTestData(16, "common_validate_label"));
		JSONObject jsonObj17 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "teluguFileName"), getTestData(16, "profile_right_menu"));
		JSONObject jsonObj34 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "teluguFileName"), getTestData(16, "on_boarding"));
		JSONObject jsonObj36 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "teluguFileName"), getTestData(16, "join_free"));
		JSONObject jsonObj50 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "teluguFileName"), getTestData(16, "terms_conditions"));

		reportStep("chooseLanguage_LanguageTeluguValidation_GetStarted_Joinfree_Login_ForgotPassword started", "INFO");

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo);

		String textboxName = "//android.widget.EditText[@content-desc='et_signup_fullname']";
		String textboxEmail = "//android.widget.EditText[@content-desc='et_signup_email']|//android.widget.EditText[@content-desc='et_signin_email']|//android.widget.EditText[@content-desc='et_forgotpassword_email']";
		String textboxPwd = "//android.widget.EditText[@content-desc='et_signup_password']|//android.widget.EditText[@content-desc='et_signin_password']";
		String textboxMobileNumber = "//android.widget.EditText[@content-desc='et_signup_mobile']";		
		String emailAddress = objCashKaroUtility.generateEmail();
		String mobileNumber = objCashKaroUtility.generateMobileNumber();	

		String otpValue = new EKOnboardingPage(driver, testInfo).
				clickOnSignUpButton().
				enterFullName().
				enterEmail(emailAddress).
				enterPassword().
				enterMobileNumber(mobileNumber).
				clickOnGetOTPButton(mobileNumber);

		new OTPPage(driver, testInfo).
		enterOTP(otpValue).
		clickOnVerifyOTP();

		new HomePage(driver, testInfo).		
		clickOnProfileIconForSignedUser().
		clickOnChooseLanguage().
		clickTeluguLink().
		clickContinueButton().
		clickRelaunchButton();

		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "profile_tab"), "home page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj17, "logout_menu"), "profile page");
		validateRequiredText(retrieveChildKeyValues(jsonObj17, "alert_logout"), "logout popup");
		validateRequiredText(retrieveChildKeyValues(jsonObj17, "alert_logout_msg"), "logout popup");
		validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj17, "alert_cancel_btn"), "logout popup");
		clickOnPartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj17, "alert_ok_btn"), "logout popup");

		new EKOnboardingPage(driver, testInfo).
		clickOnSignUpButton();

		validateRequiredText(retrieveChildKeyValues(jsonObj17, "join_free_menu"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "title"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "subtitle"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "join_facebook_txt"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "join_or_txt"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "name_inputfield_txt"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "email_inputfield_txt"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "password_inputfield_txt"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "mobile_inputfield_txt"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "otp_btn"), "join free page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj36, "already_register"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "sign_in_txt"), "join free page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj36, "signup_terms_txt"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "terms_condition_txt"), "join free page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj36, "otp_btn"), "join free page");		
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "name_inputfield_txt"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "name_error_msg"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "email_inputfield_txt"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "email_error_msg"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "password_inputfield_txt"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "password_error_msg"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "mobile_inputfield_txt"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "mobile_number_error_msg"), "join free page");

		enterTextByXpath(textboxName, getTestData(7, "oneUserChar"));
		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(textboxEmail);
		clickOnNoneOFTheAbove();
		enterTextByXpath(textboxEmail, getTestData(7, "oneUserChar"));
		enterTextByXpath(textboxPwd, getTestData(7, "oneUserChar"));
		enterTextByXpath(textboxMobileNumber, getTestData(7, "oneUserChar"));
		clickOnRequiredText(retrieveChildKeyValues(jsonObj36, "otp_btn"), "join free page");		
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "name_inputfield_txt"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "minimum_characters_error_msg"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "email_inputfield_txt"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "minimum_characters_error_msg"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "password_inputfield_txt"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "password_minimum_characters_error_msg"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "mobile_inputfield_txt"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "minimum_digits_error_msg"), "join free page");

		enterTextByXpath(textboxName, getTestData(7, "FiftyUserCharWithSpace"));
		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(textboxEmail);
		clickOnNoneOFTheAbove();
		enterTextByXpath(textboxEmail, getTestData(7, "FiftyUserCharWithSpace"));
		enterTextByXpath(textboxPwd, getTestData(7, "FiftyUserChar"));
		enterTextByXpath(textboxMobileNumber, "2222222222");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj36, "otp_btn"), "join free page");		
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "name_inputfield_txt"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "maximum_characters_error_msg"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "email_inputfield_txt"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "maximum_characters_error_msg"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "password_inputfield_txt"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "password_maximum_characters_error_msg"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "mobile_inputfield_txt"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "valid_mobile_number_error_msg"), "join free page");

		enterTextByXpath(textboxName, "2*2");
		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(textboxEmail);
		clickOnNoneOFTheAbove();
		enterTextByXpath(textboxEmail, "2*2");
		enterTextByXpath(textboxPwd, "2 2 2 2 2");
		enterTextByXpath(textboxMobileNumber, "2*22222222");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj36, "otp_btn"), "join free page");		
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "name_inputfield_txt"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "valid_name_error_msg"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "email_inputfield_txt"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "valid_email_error_msg"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "password_inputfield_txt"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "valid_password_error_msg"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "mobile_inputfield_txt"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "valid_mobile_number_error_msg"), "join free page");

		backButton();

		new EKOnboardingPage(driver, testInfo).
		clickOnSignUpButton();
		clickOnRequiredText(retrieveChildKeyValues(jsonObj36, "terms_condition_txt"), "join free page");
		scrollFromUpToDowninApp();scrollFromUpToDowninApp();
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "terms_condition_txt"), "terms & conditions page");
		validateRequiredText(retrieveChildKeyValues(jsonObj50, "title"), "terms & conditions page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj50, "introduction_title"), "terms & conditions page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj50, "introduction_details"), "terms & conditions page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj50, "membership_title"), "terms & conditions page");
		validateRequiredText(retrieveChildKeyValues(jsonObj50, "membership_details"), "terms & conditions page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj50, "our_service_title"), "terms & conditions page");
		validateRequiredText(retrieveChildKeyValues(jsonObj50, "our_service_details"), "terms & conditions page");


	}

	@Test
	public void chooseLanguage_LanguageTeluguValidation_Login() throws UnsupportedEncodingException, FileNotFoundException, IOException, ParseException {

		JSONObject jsonObj1 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "teluguFileName"), getTestData(16, "common_label"));
		JSONObject jsonObj2 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "teluguFileName"), getTestData(16, "common_validate_label"));
		JSONObject jsonObj17 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "teluguFileName"), getTestData(16, "profile_right_menu"));
		JSONObject jsonObj35 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "teluguFileName"), getTestData(16, "sign_in"));
		JSONObject jsonObj36 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "teluguFileName"), getTestData(16, "join_free"));

		reportStep("chooseLanguage_LanguageTeluguValidation_GetStarted_Joinfree_Login_ForgotPassword started", "INFO");

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo);

		String textboxEmail = "//android.widget.EditText[@content-desc='et_signup_email']|//android.widget.EditText[@content-desc='et_signin_email']|//android.widget.EditText[@content-desc='et_forgotpassword_email']";
		String textboxPwd = "//android.widget.EditText[@content-desc='et_signup_password']|//android.widget.EditText[@content-desc='et_signin_password']";
		String emailAddress = objCashKaroUtility.generateEmail();
		String mobileNumber = objCashKaroUtility.generateMobileNumber();	

		String otpValue = new EKOnboardingPage(driver, testInfo).
				clickOnSignUpButton().
				enterFullName().
				enterEmail(emailAddress).
				enterPassword().
				enterMobileNumber(mobileNumber).
				clickOnGetOTPButton(mobileNumber);

		new OTPPage(driver, testInfo).
		enterOTP(otpValue).
		clickOnVerifyOTP();

		new HomePage(driver, testInfo).		
		clickOnProfileIconForSignedUser().
		clickOnChooseLanguage().
		clickTeluguLink().
		clickContinueButton().
		clickRelaunchButton();

		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "profile_tab"), "home page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj17, "logout_menu"), "profile page");
		validateRequiredText(retrieveChildKeyValues(jsonObj17, "alert_logout"), "logout popup");
		validateRequiredText(retrieveChildKeyValues(jsonObj17, "alert_logout_msg"), "logout popup");
		validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj17, "alert_cancel_btn"), "logout popup");
		clickOnPartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj17, "alert_ok_btn"), "logout popup");

		new EKOnboardingPage(driver, testInfo).
		clickOnSignInbutton();

		validateRequiredText(retrieveChildKeyValues(jsonObj36, "sign_in_txt"), "login page");
		validateRequiredText(retrieveChildKeyValues(jsonObj35, "subtitle"), "login page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj35, "login_txt"), "login page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj35, "share_txt"), "login page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj35, "earn_txt"), "login page");
		validateRequiredText(retrieveChildKeyValues(jsonObj35, "subtitle"), "login page");
		validateRequiredText(retrieveChildKeyValues(jsonObj35, "sign_in_facebook_txt"), "login page");
		validateRequiredText(retrieveChildKeyValues(jsonObj35, "sign_in_or_txt"), "login page");
		validateRequiredText(retrieveChildKeyValues(jsonObj35, "email_inputfield_txt"), "login page");
		validateRequiredText(retrieveChildKeyValues(jsonObj35, "password_inputfield_txt"), "login page");
		validateRequiredText(retrieveChildKeyValues(jsonObj35, "forgot_password_txt"), "login page");
		validateRequiredText(retrieveChildKeyValues(jsonObj35, "sign_with_email_btn"), "login page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj35, "new_user_txt"), "login page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj35, "join_free_txt"), "login page");


		clickOnRequiredText(retrieveChildKeyValues(jsonObj35, "sign_with_email_btn"), "login page");
		validateRequiredText(retrieveChildKeyValues(jsonObj35, "email_inputfield_txt"), "login page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "email_error_msg"), "login page");
		validateRequiredText(retrieveChildKeyValues(jsonObj35, "password_inputfield_txt"), "login page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "password_error_msg"), "login page");

		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(textboxEmail);
		clickOnNoneOFTheAbove();
		enterTextByXpath(textboxEmail, getTestData(7, "oneUserChar"));
		enterTextByXpath(textboxPwd, getTestData(7, "oneUserChar"));
		clickOnRequiredText(retrieveChildKeyValues(jsonObj35, "sign_with_email_btn"), "login page");
		validateRequiredText(retrieveChildKeyValues(jsonObj35, "email_inputfield_txt"), "login page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "minimum_characters_error_msg"), "login page");
		validateRequiredText(retrieveChildKeyValues(jsonObj35, "password_inputfield_txt"), "login page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "password_minimum_characters_error_msg"), "login page");

		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(textboxEmail);
		clickOnNoneOFTheAbove();
		enterTextByXpath(textboxEmail, getTestData(7, "FiftyUserCharWithSpace"));
		enterTextByXpath(textboxPwd, getTestData(7, "FiftyUserChar"));
		clickOnRequiredText(retrieveChildKeyValues(jsonObj35, "sign_with_email_btn"), "login page");
		validateRequiredText(retrieveChildKeyValues(jsonObj35, "email_inputfield_txt"), "login page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "maximum_characters_error_msg"), "login page");
		validateRequiredText(retrieveChildKeyValues(jsonObj35, "password_inputfield_txt"), "login page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "password_maximum_characters_error_msg"), "login page");

		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(textboxEmail);
		clickOnNoneOFTheAbove();
		enterTextByXpath(textboxEmail, "2*2");
		enterTextByXpath(textboxPwd, "2 2 2 2 2");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj35, "sign_with_email_btn"), "login page");
		validateRequiredText(retrieveChildKeyValues(jsonObj35, "email_inputfield_txt"), "login page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "valid_email_error_msg"), "login page");
		validateRequiredText(retrieveChildKeyValues(jsonObj35, "password_inputfield_txt"), "login page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "valid_password_error_msg"), "login page");


	}

	@Test
	public void chooseLanguage_LanguageTeluguValidation_ForgotPassword() throws UnsupportedEncodingException, FileNotFoundException, IOException, ParseException {

		JSONObject jsonObj1 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "teluguFileName"), getTestData(16, "common_label"));
		JSONObject jsonObj2 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "teluguFileName"), getTestData(16, "common_validate_label"));
		JSONObject jsonObj17 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "teluguFileName"), getTestData(16, "profile_right_menu"));
		JSONObject jsonObj35 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "teluguFileName"), getTestData(16, "sign_in"));
		JSONObject jsonObj37 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "teluguFileName"), getTestData(16, "forgot_password"));

		reportStep("chooseLanguage_LanguageTeluguValidation_GetStarted_Joinfree_Login_ForgotPassword started", "INFO");

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo);

		String textboxEmail = "//android.widget.EditText[@content-desc='et_signup_email']|//android.widget.EditText[@content-desc='et_signin_email']|//android.widget.EditText[@content-desc='et_forgotpassword_email']";
		String emailAddress = objCashKaroUtility.generateEmail();
		String mobileNumber = objCashKaroUtility.generateMobileNumber();	

		String otpValue = new EKOnboardingPage(driver, testInfo).
				clickOnSignUpButton().
				enterFullName().
				enterEmail(emailAddress).
				enterPassword().
				enterMobileNumber(mobileNumber).
				clickOnGetOTPButton(mobileNumber);

		new OTPPage(driver, testInfo).
		enterOTP(otpValue).
		clickOnVerifyOTP();

		new HomePage(driver, testInfo).		
		clickOnProfileIconForSignedUser().
		clickOnChooseLanguage().
		clickTeluguLink().
		clickContinueButton().
		clickRelaunchButton();

		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "profile_tab"), "home page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj17, "logout_menu"), "profile page");
		validateRequiredText(retrieveChildKeyValues(jsonObj17, "alert_logout"), "logout popup");
		validateRequiredText(retrieveChildKeyValues(jsonObj17, "alert_logout_msg"), "logout popup");
		validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj17, "alert_cancel_btn"), "logout popup");
		clickOnPartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj17, "alert_ok_btn"), "logout popup");

		new EKOnboardingPage(driver, testInfo).
		clickOnSignInbutton();


		clickOnRequiredText(retrieveChildKeyValues(jsonObj35, "forgot_password_txt"), "login page");
		validateRequiredText(retrieveChildKeyValues(jsonObj35, "forgot_password_txt"), "forgot password page");
		validateRequiredText(retrieveChildKeyValues(jsonObj37, "title"), "forgot password page");
		validateRequiredText(retrieveChildKeyValues(jsonObj37, "subtitle"), "forgot password page");
		validateRequiredText(retrieveChildKeyValues(jsonObj37, "email_inputfield_txt"), "forgot password page");
		validateRequiredText(retrieveChildKeyValues(jsonObj37, "password_link_txt"), "forgot password page");
		validateRequiredText(retrieveChildKeyValues(jsonObj37, "submit_btn"), "forgot password page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj37, "remember_password_txt"), "forgot password page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj37, "login_txt"), "forgot password page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj37, "submit_btn"), "forgot password page");
		validateRequiredText(retrieveChildKeyValues(jsonObj37, "email_inputfield_txt"), "forgot password page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "email_error_msg"), "login page");

		enterTextByXpath(textboxEmail, getTestData(7, "oneUserChar"));		
		clickOnRequiredText(retrieveChildKeyValues(jsonObj37, "submit_btn"), "forgot password page");
		validateRequiredText(retrieveChildKeyValues(jsonObj37, "email_inputfield_txt"), "forgot password page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "minimum_characters_error_msg"), "login page");

		enterTextByXpath(textboxEmail, getTestData(7, "FiftyUserCharWithSpace"));		
		clickOnRequiredText(retrieveChildKeyValues(jsonObj37, "submit_btn"), "forgot password page");
		validateRequiredText(retrieveChildKeyValues(jsonObj37, "email_inputfield_txt"), "forgot password page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "maximum_characters_error_msg"), "login page");

		enterTextByXpath(textboxEmail, "2*2");		
		clickOnRequiredText(retrieveChildKeyValues(jsonObj37, "submit_btn"), "forgot password page");
		validateRequiredText(retrieveChildKeyValues(jsonObj37, "email_inputfield_txt"), "forgot password page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "valid_email_error_msg"), "login page");


	}

	@Test
	public void chooseLanguage_LanguageTeluguValidation_MyEarnings_History_Payment_ExistingUser() throws UnsupportedEncodingException, FileNotFoundException, IOException, ParseException {

		JSONObject jsonObj1 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "teluguFileName"), getTestData(16, "common_label"));
		JSONObject jsonObj2 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "teluguFileName"), getTestData(16, "common_validate_label"));
		JSONObject jsonObj14 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "teluguFileName"), getTestData(16, "search_result_all"));
		JSONObject jsonObj17 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "teluguFileName"), getTestData(16, "profile_right_menu"));
		JSONObject jsonObj28 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "teluguFileName"), getTestData(16, "my_account_earnings"));
		JSONObject jsonObj29 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "teluguFileName"), getTestData(16, "total_cashback_earned"));
		JSONObject jsonObj30 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "teluguFileName"), getTestData(16, "earnings_info"));		
		JSONObject jsonObj31 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "teluguFileName"), getTestData(16, "earnings_referral"));
		JSONObject jsonObj32 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "teluguFileName"), getTestData(16, "earnings_cashback"));
		JSONObject jsonObj33 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "teluguFileName"), getTestData(16, "my_earnings"));
		JSONObject jsonObj35 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "teluguFileName"), getTestData(16, "sign_in"));
		JSONObject jsonObj36 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "teluguFileName"), getTestData(16, "join_free"));
		JSONObject jsonObj40 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "teluguFileName"), getTestData(16, "giftcard_wallet_inputfield"));
		JSONObject jsonObj46 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "teluguFileName"), getTestData(16, "payment"));
		JSONObject jsonObj52 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "teluguFileName"), getTestData(16, "my_shared_activities"));
		JSONObject jsonObj53 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "teluguFileName"), getTestData(16, "giftcard_wallet_request_payment"));

		reportStep("chooseLanguage_LanguageTeluguValidation_MyEarnings_History_Payment_ExistingUser started", "INFO");

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo);

		String dropDownPaymentSettings = "(//android.widget.EditText)[1]";
		String textNEFT = "//android.widget.CheckedTextView[@text='Bank Payment (NEFT)']";
		String textPaytmWallet = "//android.widget.CheckedTextView[@text='Paytm Wallet']";
		String textAmazonGiftCard = "//android.widget.CheckedTextView[@text='Amazon Gift Card']";
		String textBoxPaytmMobileNumberPaymentRequestPage = "//android.widget.EditText[@content-desc='et_requestpaymentPaytm_mobile']";
		String textBoxAmazonEmailPaymentRequestPage = "//android.widget.EditText[@content-desc='et_requestpaymentAmazon_email']";
		String linkEarningsInfoPaymentPage = "//android.widget.TextView[@content-desc='txt_payment_earningsInfo']";	
		String textboxName = "//android.widget.EditText[@content-desc='et_signup_fullname']";
		String textboxEmail = "//android.widget.EditText[@content-desc='et_signup_email']|//android.widget.EditText[@content-desc='et_signin_email']|//android.widget.EditText[@content-desc='et_forgotpassword_email']";
		String textboxPwd = "//android.widget.EditText[@content-desc='et_signup_password']|//android.widget.EditText[@content-desc='et_signin_password']";
		String textboxMobileNumber = "//android.widget.EditText[@content-desc='et_signup_mobile']";
		String searchIcon = "(//android.widget.TextView)[2]";
		String textAreaSearchIcon = "//android.widget.EditText[@content-desc='et_search_enterValue']";

		String cbStoreNameOne = CashKaroUtility.extractValuesFromStoreCategoryJsonAndLoadTheVariablesForStore("CB_Store_One", 0, "CB_Store_One_Name");
		String linkURL = CashKaroUtility.extractValuesFromStoreCategoryJsonAndLoadTheVariablesForStore("CB_Store_One", 0, "CB_Store_One_linkURL");

		String emailAddress = objCashKaroUtility.generateEmail();
		String mobileNumber = objCashKaroUtility.generateMobileNumber();	

		String emailAddressReferralJoinee = objCashKaroUtility.generateEmail();
		String mobileNumberReferralJoinee = objCashKaroUtility.generateMobileNumber();	

		String otpValue = new EKOnboardingPage(driver, testInfo).
				clickOnSignUpButton().
				enterFullName().
				enterEmail(emailAddress).
				enterPassword().
				enterMobileNumber(mobileNumber).
				clickOnGetOTPButton(mobileNumber);

		new OTPPage(driver, testInfo).
		enterOTP(otpValue).
		clickOnVerifyOTP();

		new HomePage(driver, testInfo).		
		clickOnProfileIconForSignedUser().
		clickOnChooseLanguage().
		clickTeluguLink().
		clickContinueButton().
		clickRelaunchButton();

		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "profile_tab"), "home page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj17, "logout_menu"), "profile page");
		validateRequiredText(retrieveChildKeyValues(jsonObj17, "alert_logout"), "logout popup");
		validateRequiredText(retrieveChildKeyValues(jsonObj17, "alert_logout_msg"), "logout popup");
		validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj17, "alert_cancel_btn"), "logout popup");
		clickOnPartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj17, "alert_ok_btn"), "logout popup");

		new EKOnboardingPage(driver, testInfo);

		CashKaroUtility cu = new CashKaroUtility(udid, port, automationName, deviceName);
		driver = cu.launchChromeWebView(driver);

		AdminCoreFunction adminFunctions = new AdminCoreFunction(driver, testInfo);
		adminFunctions.naviagateAdminPage_ThenSelectEarnKaro();
		adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
		adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
		adminFunctions.clickSubmit();

		AdminUsersPage usersPage = new AdminUsersPage(driver, testInfo);
		adminFunctions.clickOnHamburgerMenu();
		adminFunctions.clickUsersMenu();
		adminFunctions.clickUsersSubMenu();
		usersPage.selectSearchByDropDown("User Email");
		usersPage.enterKeyword(emailAddress);
		usersPage.clickSubmitButton();
		String userId = usersPage.extractUserIdValueFromResultstableFirstRow();
		String userName = usersPage.extractUserNameValueFromResultstableFirstRow();

		CashKaroUtility reopenCKApp1 = new CashKaroUtility(udid, port, automationName, deviceName, systemPort);
		driver = reopenCKApp1.launchEarnKaroApp(driver);

		CashKaroUtility objcashKaroutility = new CashKaroUtility(udid, port, automationName, deviceName);
		AppiumDriver<MobileElement> driver2 = objcashKaroutility.launchChrome();
		driver = driver2;

		String url = Utilities.referralLinkGetCode(userId, userName);

		objcashKaroutility.closeAllTabsAndOneTabInChromeBrowser(driver);
		objcashKaroutility.clickAndEnterURLInSearchOrTypeWebAddress(driver, url);

		objcashKaroutility.clickJoinFreeLink(driver);

		enterTextByXpath(textboxName, "ReferralJoinee");
		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(textboxEmail);
		clickOnNoneOFTheAbove();
		enterTextByXpath(textboxEmail, emailAddressReferralJoinee);
		enterTextByXpath(textboxPwd, "ppounds");
		enterTextByXpath(textboxMobileNumber, mobileNumberReferralJoinee);
		clickOnRequiredText(retrieveChildKeyValues(jsonObj36, "otp_btn"), "join free page");	

		new OTPPage(driver, testInfo);

		String otp = Utilities.getSignUPOTP(mobileNumberReferralJoinee);

		new OTPPage(driver, testInfo).
		enterOTP(otp);

		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "verify_otp"), "otp page");	

		sleep(15000);

		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(searchIcon);
		hideKeyboard();
		//validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj13, "search_inputfield_txt"), "search page");
		enterTextByXpath(textAreaSearchIcon, cbStoreNameOne);
		pressEnter();
		validateRequiredText(retrieveChildKeyValues(jsonObj14, "offer_count_single_message"), "search page");
		validateRequiredText(retrieveChildKeyValues(jsonObj1, "add_to_share"), "search page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "share_now"), "search page");

		WhatsAppUtility whatsAppUtility = new WhatsAppUtility(driver, testInfo, udid, port, automationName, deviceName, systemPort);
		whatsAppUtility.clickOnWhatAppMenuItemSearch().
		enterWhatsAppSearchText("Mallik").
		clickOnRequiredContact().
		clickOnSend().
		clickOnSend().
		clickOnLastSharedLink();

		new IntermediateRetailerPage(driver, testInfo).
		validateRetailerPage(linkURL, linkURL);

		CashKaroUtility cu2 = new CashKaroUtility(udid, port, automationName, deviceName);
		cu = cu2;
		driver = cu.launchChromeWebView(driver);

		AdminCoreFunction adminFunctions2 = new AdminCoreFunction(driver, testInfo);
		adminFunctions = adminFunctions2;
		adminFunctions.naviagateAdminPage_ThenSelectEarnKaro();
		adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
		adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
		adminFunctions.clickSubmit();

		AdminReportsPage reportsPage = new AdminReportsPage(driver, testInfo);
		adminFunctions.clickOnHamburgerMenu();
		adminFunctions.clickOnReports();
		reportsPage.clickExitClickSubMenu();
		reportsPage.clickClear();
		reportsPage.selectSearchByDropDown("User Email");
		reportsPage.enterKeyword(emailAddressReferralJoinee);
		reportsPage.clickSubmit();

		String exitClickOne = reportsPage.extractRequiredExitIdUsingStoreNameExitClickType(cbStoreNameOne, getTestData(7, "Normal"));

		AdminCashbackPage ProfitPage = new AdminCashbackPage(driver, testInfo);
		adminFunctions.clickOnHamburgerMenu();
		adminFunctions.clickCashbackMenu();
		ProfitPage.clickCashbackSubMenu();

		ProfitPage.clickAddNewButton();
		ProfitPage.enterExitId(exitClickOne);
		ProfitPage.entertransactionId(exitClickOne);
		ProfitPage.enterOrderId(exitClickOne);
		ProfitPage.setOrderDate();
		ProfitPage.enterOrderValue("1000");
		ProfitPage.enterconfirmCommisionNetwork(getTestData(11, "EightHundered"));
		ProfitPage.entercashback("700");
		ProfitPage.selectCashbackTypeFromDropDown(getTestData(11, "Cashback"));
		ProfitPage.selectCashbackStatusFromDropDown(getTestData(11, "Pending"));
		ProfitPage.setPendingDate();
		ProfitPage.enterDetails(exitClickOne);
		ProfitPage.clickSubmit();
		ProfitPage.clickClear();

		CashKaroUtility reopenCKAppAgain = new CashKaroUtility(udid, port, automationName, deviceName, systemPort);
		driver = reopenCKAppAgain.launchEarnKaroApp(driver);
		new HomePage(driver, testInfo);

		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "profile_tab"), "home page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj17, "history_menu"), "profile page");
		validateRequiredText(retrieveChildKeyValues(jsonObj1, "recent_clicks_tab"), "history page");
		validateRequiredText(retrieveChildKeyValues(jsonObj52, "title"), "history page");
		validateRequiredText(retrieveChildKeyValues(jsonObj52, "shared_link_details"), "history page");
		validateRequiredText(retrieveChildKeyValues(jsonObj52, "shared_id"), "history page");
		validateRequiredText(retrieveChildKeyValues(jsonObj52, "shared_date_time"), "history page");
		validateRequiredText(retrieveChildKeyValues(jsonObj52, "retailer_name"), "history page");
		validateRequiredText(retrieveChildKeyValues(jsonObj52, "no_of_clicks"), "history page");
		validateRequiredText(retrieveChildKeyValues(jsonObj52, "no_of_transactions"), "history page");
		validateRequiredText(retrieveChildKeyValues(jsonObj52, "total_earning"), "history page");

		backButton();

		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "profile_tab"), "home page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj17, "my_earnings_menu"), "profile page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj29, "total_cashback_earned_txt"), "my earnings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj29, "total_cashback_earned_txt"), 1, "profit page");
		validateRequiredText(retrieveChildKeyValues(jsonObj29, "total_cashback_earned_txt"), 2, "profit page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj29, "paid_cashback_txt"), "profit page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj29, "pending_cashback_txt"), "profit page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj29, "referral_cashback_txt"), "profit page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj29, "available_cashback_txt"), "profit page");

		backButton();

		validateRequiredText(retrieveChildKeyValues(jsonObj32, "order_details"), "my earnings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj32, "date"), "my earnings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj32, "retailer"), "my earnings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj32, "order_amount"), "my earnings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj32, "cashback_amount"), "my earnings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj32, "status"), "my earnings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj32, "expected_date"), "my earnings page");

		backButton();

		clickOnRequiredText(retrieveChildKeyValues(jsonObj17, "logout_menu"), "profile page");
		validateRequiredText(retrieveChildKeyValues(jsonObj17, "alert_logout"), "logout popup");
		validateRequiredText(retrieveChildKeyValues(jsonObj17, "alert_logout_msg"), "logout popup");
		validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj17, "alert_cancel_btn"), "logout popup");
		clickOnPartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj17, "alert_ok_btn"), "logout popup");

		new EKOnboardingPage(driver, testInfo);

		CashKaroUtility cu3 = new CashKaroUtility(udid, port, automationName, deviceName);
		cu = cu3;
		driver = cu.launchChromeWebView(driver);

		AdminCoreFunction adminFunctions3 = new AdminCoreFunction(driver, testInfo);
		adminFunctions = adminFunctions3;
		adminFunctions.naviagateAdminPage_ThenSelectEarnKaro();
		adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
		adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
		adminFunctions.clickSubmit();

		AdminCashbackPage ProfitPage1 = new AdminCashbackPage(driver, testInfo);
		ProfitPage = ProfitPage1;
		adminFunctions.clickOnHamburgerMenu();
		adminFunctions.clickCashbackMenu();
		ProfitPage.clickCashbackSubMenu();

		ProfitPage.selectSearchByStatusFromDropDown("Exit ID");
		ProfitPage.enterKeyword(exitClickOne);
		ProfitPage.selectCashbackStatusFromDropDown(getTestData(11, "Pending"));
		ProfitPage.clickSubmit();
		ProfitPage.clickEdit();
		ProfitPage.selectCashbackStatusFromDropDown(getTestData(11, "Confirmed"));
		ProfitPage.setConfirmDate();
		ProfitPage.clickSubmit();
		ProfitPage.clickClear();

		CashKaroUtility reopenCKAppOnceAgain = new CashKaroUtility(udid, port, automationName, deviceName, systemPort);
		driver = reopenCKAppOnceAgain.launchEarnKaroApp(driver);

		new EKOnboardingPage(driver, testInfo).clickOnSignInbutton();

		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(textboxEmail);
		clickOnNoneOFTheAbove();
		enterTextByXpath(textboxEmail, emailAddress);
		enterTextByXpath(textboxPwd, "ppounds");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj35, "sign_with_email_btn"), "login page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "profile_tab"), "home page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj17, "my_earnings_menu"), "profile page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj33, "referral_tap"), "my earnings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj31, "order_details"), "my earnings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj31, "date"), "my earnings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj31, "referral_name"), "my earnings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj31, "cashback"), "my earnings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj31, "status"), "my earnings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj33, "refer_now_btn"), "my earnings page");

		backButton();

		clickOnRequiredText(retrieveChildKeyValues(jsonObj17, "logout_menu"), "profile page");
		validateRequiredText(retrieveChildKeyValues(jsonObj17, "alert_logout"), "logout popup");
		validateRequiredText(retrieveChildKeyValues(jsonObj17, "alert_logout_msg"), "logout popup");
		validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj17, "alert_cancel_btn"), "logout popup");
		clickOnPartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj17, "alert_ok_btn"), "logout popup");

		new EKOnboardingPage(driver, testInfo).clickOnSignInbutton();

		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(textboxEmail);
		clickOnNoneOFTheAbove();
		enterTextByXpath(textboxEmail, emailAddressReferralJoinee);
		enterTextByXpath(textboxPwd, "ppounds");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj35, "sign_with_email_btn"), "login page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "profile_tab"), "home page");
		validateRequiredText(retrieveChildKeyValues(jsonObj1, "profile_tab"), "profile page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj17, "hello_user_txt"), "profile page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj17, "total_cashback_txt"), "profile page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj17, "payment_menu"), "profile page");

		validateRequiredText(retrieveChildKeyValues(jsonObj17, "payment_menu"), "profile page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj29, "total_cashback_earned_txt"), "payments page");
		validateRequiredText(retrieveChildKeyValues(jsonObj29, "total_cashback_earned_txt"), 1, "profit page");
		validateRequiredText(retrieveChildKeyValues(jsonObj29, "total_cashback_earned_txt"), 2, "profit page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj29, "paid_cashback_txt"), "profit page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj29, "pending_cashback_txt"), "profit page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj29, "referral_cashback_txt"), "profit page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj29, "available_cashback_txt"), "profit page");

		backButton();

		validatePartialRequiredText(retrieveChildKeyValues(jsonObj28, "tap_to_see_details_txt"), "payments page");
		validateRequiredText(retrieveChildKeyValues(jsonObj46, "cashback_earnings"), "payments page");
		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(linkEarningsInfoPaymentPage);
		validateRequiredText(retrieveChildKeyValues(jsonObj30, "message_first_txt"), "earnings info page");


		backButton();

		validateRequiredText(retrieveChildKeyValues(jsonObj46, "request_cashback_btn"), "payments page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj46, "static_text_title"), "payments page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj46, "static_text_1"), "payments page");


		clickOnRequiredText(retrieveChildKeyValues(jsonObj46, "request_cashback_btn"), "payments page");

		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(dropDownPaymentSettings);
		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(textPaytmWallet);
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "mobile_inputfield_txt"), "payment request page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "get_paid_btn"), "payment req page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "mobile_inputfield_txt"), "payment request page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "mobile_number_error_msg"), "payment request page");

		enterTextByXpath(textBoxPaytmMobileNumberPaymentRequestPage, "1");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "get_paid_btn"), "payment req page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "mobile_inputfield_txt"), "payment request page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "minimum_digits_error_msg"), "payment request page");

		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(dropDownPaymentSettings);
		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(textAmazonGiftCard);

		validateRequiredText(retrieveChildKeyValues(jsonObj53, "email_inputfield_txt"), "payment request page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "get_paid_btn"), "payment req page");
		validateRequiredText(retrieveChildKeyValues(jsonObj53, "email_inputfield_txt"), "payment request page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "email_error_msg"), "payment request page");

		enterTextByXpath(textBoxAmazonEmailPaymentRequestPage, "1");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "get_paid_btn"), "payment req page");
		validateRequiredText(retrieveChildKeyValues(jsonObj53, "email_inputfield_txt"), "payment request page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "minimum_characters_error_msg"), "payment request page");

		enterTextByXpath(textBoxAmazonEmailPaymentRequestPage, "asdfghjkl");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "get_paid_btn"), "payment req page");
		validateRequiredText(retrieveChildKeyValues(jsonObj53, "email_inputfield_txt"), "payment request page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "valid_email_error_msg"), "payment request page");

		enterTextByXpath(textBoxAmazonEmailPaymentRequestPage, getTestData(7, "FiftyUserCharWithSpace"));
		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "get_paid_btn"), "payment req page");
		validateRequiredText(retrieveChildKeyValues(jsonObj53, "email_inputfield_txt"), "payment request page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "maximum_characters_error_msg"), "payment request page");

		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(dropDownPaymentSettings);
		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(textNEFT);

		validateRequiredText(retrieveChildKeyValues(jsonObj40, "bank_account_inputfield_txt"), "payment request page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "bank_name_inputfield_txt"), "payment request page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "branch_name_inputfield_txt"), "payment request page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "bank_account_number_inputfield_txt"), "payment request page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "bank_ifsc_code_inputfield_txt"), "payment request page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "get_paid_btn"), "payment req page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "bank_account_inputfield_txt"), "payment request page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "account_holder_error_msg"), "payment request page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "bank_name_inputfield_txt"), "payment request page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "valid_bank_name_error_msg"), "payment request page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "branch_name_inputfield_txt"), "payment request page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "branch_minimum_digits_error_msg"), "payment request page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "bank_account_number_inputfield_txt"), "payment request page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "account_number_error_msg"), "payment request page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "bank_ifsc_code_inputfield_txt"), "payment request page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "ifsc_code_error_msg"), "payment request page");


	}

	@Test
	public void chooseLanguage_LanguageTeluguValidation_StoreCategory_StoreDetail() throws UnsupportedEncodingException, FileNotFoundException, IOException, ParseException {

		JSONObject jsonObj1 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "teluguFileName"), getTestData(16, "common_label"));
		JSONObject jsonObj3 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "teluguFileName"), getTestData(16, "main_menu"));
		JSONObject jsonObj5 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "teluguFileName"), getTestData(16, "store_category"));
		JSONObject jsonObj12 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "teluguFileName"), getTestData(16, "share_counts"));
		JSONObject jsonObj17 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "teluguFileName"), getTestData(16, "profile_right_menu"));

		reportStep("chooseLanguage_LanguageTeluguValidation_Store_Product_StoreCategory started", "INFO");

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo);

		String shareButtonWithCount = "//android.view.ViewGroup[@content-desc='txt_sharecount_open_modal']/android.view.ViewGroup/android.widget.TextView[@text='1']";
		String imageStoreCard = "//android.widget.ImageView[@content-desc='img_storeCard_imageValue']";
		String emailAddress = objCashKaroUtility.generateEmail();
		String mobileNumber = objCashKaroUtility.generateMobileNumber();	

		String otpValue = new EKOnboardingPage(driver, testInfo).
				clickOnSignUpButton().
				enterFullName().
				enterEmail(emailAddress).
				enterPassword().
				enterMobileNumber(mobileNumber).
				clickOnGetOTPButton(mobileNumber);

		new OTPPage(driver, testInfo).
		enterOTP(otpValue).
		clickOnVerifyOTP();

		new HomePage(driver, testInfo).		
		clickOnProfileIconForSignedUser().
		clickOnChooseLanguage().
		clickTeluguLink().
		clickContinueButton().
		clickRelaunchButton();

		validateRequiredText(retrieveChildKeyValues(jsonObj3, "home_lbl"), "home page");

		new HomePage(driver, testInfo).
		clickOnHamburgerIcon();

		validateRequiredText(retrieveChildKeyValues(jsonObj3, "home_lbl"), "home page");

		new SliderScreenPage(driver, testInfo).
		clickRetailerCategory().
		verifyRetailerCategoryMenuNavigation().
		clickAutomationSpecificCategoryInRetailerCategoryMenuUntilAllSubCategoryVisible().
		clickAutomationSpecificSubCategoryOne();

		validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj5, "popular"), "store category page");
		validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj5, "az"), "store category page");
		validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj5, "percent"), "store category page");
		validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj5, "amount"), "store category page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "add_to_share"), "store category page");
		validateRequiredText(retrieveChildKeyValues(jsonObj1, "share_now"), "store category page");
		validateRequiredText(retrieveChildKeyValues(jsonObj1, "remove"), "store category page");

		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(shareButtonWithCount);
		validateRequiredText(retrieveChildKeyValues(jsonObj1, "share_now"), "store category page");
		validateRequiredText(retrieveChildKeyValues(jsonObj1, "copy_Link"), "store category page");
		validateRequiredText(retrieveChildKeyValues(jsonObj12, "close"), "store category page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj12, "clear"), "store category page");

		validateRequiredText(retrieveChildKeyValues(jsonObj12, "clear"), "store category page");
		validateRequiredText(retrieveChildKeyValues(jsonObj12, "confirm_msg_clear_share"), "store category page");
		validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj17, "alert_cancel_btn"), "store category page");
		clickOnPartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj17, "alert_ok_btn"), "store category page");

		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(imageStoreCard);
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj1, "you_will_earn"), "store detail page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "add_to_share"), "store detail page");
		validateRequiredText(retrieveChildKeyValues(jsonObj1, "share_now"), "store detail page");
		validateRequiredText(retrieveChildKeyValues(jsonObj1, "remove"), "store category page");

		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(shareButtonWithCount);
		validateRequiredText(retrieveChildKeyValues(jsonObj1, "share_now"), "store detail page");
		validateRequiredText(retrieveChildKeyValues(jsonObj1, "copy_Link"), "store detail page");
		validateRequiredText(retrieveChildKeyValues(jsonObj12, "close"), "store detail page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj12, "clear"), "store detail page");

		validateRequiredText(retrieveChildKeyValues(jsonObj12, "clear"), "store detail page");
		validateRequiredText(retrieveChildKeyValues(jsonObj12, "confirm_msg_clear_share"), "store detail page");
		validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj17, "alert_cancel_btn"), "store detail page");
		clickOnPartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj17, "alert_ok_btn"), "store detail page");

		new StoreDetailPage(driver, testInfo);

	}

	@Test
	public void chooseLanguage_LanguageTeluguValidation_Product() throws UnsupportedEncodingException, FileNotFoundException, IOException, ParseException {

		JSONObject jsonObj1 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "teluguFileName"), getTestData(16, "common_label"));
		JSONObject jsonObj3 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "teluguFileName"), getTestData(16, "main_menu"));
		JSONObject jsonObj6 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "teluguFileName"), getTestData(16, "prduct_category"));
		JSONObject jsonObj9 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "teluguFileName"), getTestData(16, "product_details"));
		JSONObject jsonObj10 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "teluguFileName"), getTestData(16, "product_card"));
		JSONObject jsonObj49 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "teluguFileName"), getTestData(16, "prduct_filter"));

		reportStep("chooseLanguage_LanguageTeluguValidation_Store_Product_StoreCategory started", "INFO");

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo);

		String menFashionCategory = "//android.view.ViewGroup[@content-desc='txt_mainMenu_productDealsTop']";
		String tshirtCategory = "//android.widget.TextView[@content-desc='txt_subMenuProduct_mainMenuName']";
		String menfashionLink = "//android.widget.TextView[@content-desc='txt_filter_category_name']";
		String imageProductCard = "//android.widget.ImageView[@content-desc='img_product_image']";
		String productcard = "(//android.widget.ImageView)[3]";		
		String emailAddress = objCashKaroUtility.generateEmail();
		String mobileNumber = objCashKaroUtility.generateMobileNumber();	

		String otpValue = new EKOnboardingPage(driver, testInfo).
				clickOnSignUpButton().
				enterFullName().
				enterEmail(emailAddress).
				enterPassword().
				enterMobileNumber(mobileNumber).
				clickOnGetOTPButton(mobileNumber);

		new OTPPage(driver, testInfo).
		enterOTP(otpValue).
		clickOnVerifyOTP();

		new HomePage(driver, testInfo).		
		clickOnProfileIconForSignedUser().
		clickOnChooseLanguage().
		clickTeluguLink().
		clickContinueButton().
		clickRelaunchButton();

		validateRequiredText(retrieveChildKeyValues(jsonObj3, "home_lbl"), "home page");

		new HomePage(driver, testInfo).
		clickOnHamburgerIcon();

		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(menFashionCategory);
		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(tshirtCategory);

		clickOnPartialRequiredText(retrieveChildKeyValues(jsonObj6, "filter"), "product page");
		validateRequiredText(retrieveChildKeyValues(jsonObj49, "filter_by"), "product page");
		validateRequiredText(retrieveChildKeyValues(jsonObj49, "clerar_all"), "product page");
		validateRequiredText(retrieveChildKeyValues(jsonObj49, "close_btn"), "product page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj49, "apply_btn"), "product page");

		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(menfashionLink);
		clickOnRequiredText(retrieveChildKeyValues(jsonObj49, "clerar_all"), "product page");
		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(menfashionLink);
		clickOnRequiredText(retrieveChildKeyValues(jsonObj49, "close_btn"), "product page");
		validateRequiredText(retrieveChildKeyValues(jsonObj49, "discard_changes_title"), "product page");
		validateRequiredText(retrieveChildKeyValues(jsonObj49, "discard_changes_msg"), "product page");
		validateRequiredText(retrieveChildKeyValues(jsonObj49, "apply_changes_btn"), "product page");
		validateRequiredText(retrieveChildKeyValues(jsonObj49, "discard_changes_btn"), "product page");

		backButton();

		clickOnPartialRequiredText(retrieveChildKeyValues(jsonObj6, "sort"), "product page");
		validateRequiredText(retrieveChildKeyValues(jsonObj6, "sort_title"), "product page");
		validateRequiredText(retrieveChildKeyValues(jsonObj6, "sort_popular"), "product page");
		validateRequiredText(retrieveChildKeyValues(jsonObj6, "sort_discount"), "product page");
		validateRequiredText(retrieveChildKeyValues(jsonObj6, "sort_high_price"), "product page");
		validateRequiredText(retrieveChildKeyValues(jsonObj6, "sort_low_price"), "product page");

		backButton();

		validatePartialRequiredText(retrieveChildKeyValues(jsonObj10, "brand"), "product page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj10, "price"), "product page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj10, "sale"), "product page");
		validateRequiredText(retrieveChildKeyValues(jsonObj1, "add_to_share"), "product page");
		validateRequiredText(retrieveChildKeyValues(jsonObj1, "share_now"), "product page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj10, "see_details"), "product page");

		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(imageProductCard);
		clickOnRequiredText(retrieveChildKeyValues(jsonObj9, "seller_price"), "product detail page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "you_will_earn"), "product detail page");
		validateRequiredText(retrieveChildKeyValues(jsonObj1, "add_to_share"), "product page");
		validateRequiredText(retrieveChildKeyValues(jsonObj1, "share_now"), "product page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj9, "product_highlights"), "product detail page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj9, "similar_products"), "product detail page");



	}

	@Test
	public void chooseLanguage_LanguageBengaliValidation_SuccessStories() throws UnsupportedEncodingException, FileNotFoundException, IOException, ParseException {

		JSONObject jsonObj1 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "bengaliFileName"), getTestData(16, "common_label"));
		JSONObject jsonObj4 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "bengaliFileName"), getTestData(16, "home"));
		JSONObject jsonObj17 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "bengaliFileName"), getTestData(16, "profile_right_menu"));
		JSONObject jsonObj22 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "bengaliFileName"), getTestData(16, "success_stories"));

		reportStep("chooseLanguage_LanguageBengaliValidation_SuccessStories started", "INFO");

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo);

		String emailAddress = objCashKaroUtility.generateEmail();
		String mobileNumber = objCashKaroUtility.generateMobileNumber();	

		String otpValue = new EKOnboardingPage(driver, testInfo).
				clickOnSignUpButton().
				enterFullName().
				enterEmail(emailAddress).
				enterPassword().
				enterMobileNumber(mobileNumber).
				clickOnGetOTPButton(mobileNumber);

		new OTPPage(driver, testInfo).
		enterOTP(otpValue).
		clickOnVerifyOTP();

		new HomePage(driver, testInfo).		
		clickOnProfileIconForSignedUser().
		clickOnChooseLanguage().
		clickBengaliLink().
		clickContinueButton().
		clickRelaunchButton();

		validateRequiredText(retrieveChildKeyValues(jsonObj4, "dynamic_home_top_Category"), "home page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "profile_tab"), "home page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj17, "success_stories_menu"), "profile page");
		validateRequiredText(retrieveChildKeyValues(jsonObj17, "success_stories_menu"), "success stories page");
		scrollFromUpToDowninApp();scrollFromUpToDowninApp();
		validateRequiredText(retrieveChildKeyValues(jsonObj22, "title"), "success stories page");
		validateRequiredText(retrieveChildKeyValues(jsonObj22, "testimonial_txt1"), "success stories page");
		validateRequiredText(retrieveChildKeyValues(jsonObj22, "testimonial_name1"), "success stories page");
		validateRequiredText(retrieveChildKeyValues(jsonObj22, "testimonial_txt2"), "success stories page");
		validateRequiredText(retrieveChildKeyValues(jsonObj22, "testimonial_name2"), "success stories page");
		validateRequiredText(retrieveChildKeyValues(jsonObj22, "testimonial_txt3"), "success stories page");
		validateRequiredText(retrieveChildKeyValues(jsonObj22, "testimonial_name3"), "success stories page");
		validateRequiredText(retrieveChildKeyValues(jsonObj22, "testimonial_txt4"), "success stories page");
		validateRequiredText(retrieveChildKeyValues(jsonObj22, "testimonial_name4"), "success stories page");
		validateRequiredText(retrieveChildKeyValues(jsonObj22, "testimonial_txt5"), "success stories page");
		validateRequiredText(retrieveChildKeyValues(jsonObj22, "testimonial_name5"), "success stories page");


	}

	@Test
	public void chooseLanguage_LanguageBengaliValidation_HowItWorks() throws UnsupportedEncodingException, FileNotFoundException, IOException, ParseException {

		JSONObject jsonObj1 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "bengaliFileName"), getTestData(16, "common_label"));
		JSONObject jsonObj4 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "bengaliFileName"), getTestData(16, "home"));
		JSONObject jsonObj17 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "bengaliFileName"), getTestData(16, "profile_right_menu"));
		JSONObject jsonObj23 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "bengaliFileName"), getTestData(16, "how_it_works"));

		reportStep("chooseLanguage_LanguageBengaliValidation_HowItWorks started", "INFO");

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo);

		String emailAddress = objCashKaroUtility.generateEmail();
		String mobileNumber = objCashKaroUtility.generateMobileNumber();	

		String otpValue = new EKOnboardingPage(driver, testInfo).
				clickOnSignUpButton().
				enterFullName().
				enterEmail(emailAddress).
				enterPassword().
				enterMobileNumber(mobileNumber).
				clickOnGetOTPButton(mobileNumber);

		new OTPPage(driver, testInfo).
		enterOTP(otpValue).
		clickOnVerifyOTP();

		new HomePage(driver, testInfo).		
		clickOnProfileIconForSignedUser().
		clickOnChooseLanguage().
		clickBengaliLink().
		clickContinueButton().
		clickRelaunchButton();

		validateRequiredText(retrieveChildKeyValues(jsonObj4, "dynamic_home_top_Category"), "home page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "profile_tab"), "home page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj17, "how_it_works_menu"), "profile page");
		scrollFromUpToDowninApp();scrollFromUpToDowninApp();
		validateRequiredText(retrieveChildKeyValues(jsonObj17, "how_it_works_menu"), "how earnkaro works? page");
		validateRequiredText(retrieveChildKeyValues(jsonObj23, "watch_video_title1"), "how earnkaro works? page");
		validateRequiredText(retrieveChildKeyValues(jsonObj23, "watch_video_txt"), 1, "how earnkaro works? page");
		validateRequiredText(retrieveChildKeyValues(jsonObj23, "watch_video_title2"), "how earnkaro works? page");
		validateRequiredText(retrieveChildKeyValues(jsonObj23, "watch_video_txt"), 2, "how earnkaro works? page");
		validateRequiredText(retrieveChildKeyValues(jsonObj23, "watch_video_title3"), "how earnkaro works? page");
		validateRequiredText(retrieveChildKeyValues(jsonObj23, "watch_video_txt"), 3, "how earnkaro works? page");
		validateRequiredText(retrieveChildKeyValues(jsonObj23, "watch_video_title4"), "how earnkaro works? page");
		validateRequiredText(retrieveChildKeyValues(jsonObj23, "watch_video_txt"), 4, "how earnkaro works? page");
		validateRequiredText(retrieveChildKeyValues(jsonObj23, "watch_video_title5"), "how earnkaro works? page");
		validateRequiredText(retrieveChildKeyValues(jsonObj23, "watch_video_txt"), 5, "how earnkaro works? page");
		validateRequiredText(retrieveChildKeyValues(jsonObj23, "watch_video_title6"), "how earnkaro works? page");
		validateRequiredText(retrieveChildKeyValues(jsonObj23, "watch_video_txt"), 6, "how earnkaro works? page");



	}

	@Test
	public void chooseLanguage_LanguageBengaliValidation_Testimonials() throws UnsupportedEncodingException, FileNotFoundException, IOException, ParseException {

		JSONObject jsonObj1 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "bengaliFileName"), getTestData(16, "common_label"));
		JSONObject jsonObj4 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "bengaliFileName"), getTestData(16, "home"));
		JSONObject jsonObj17 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "bengaliFileName"), getTestData(16, "profile_right_menu"));
		JSONObject jsonObj24 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "bengaliFileName"), getTestData(16, "testimonial"));
		JSONObject jsonObj25 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "bengaliFileName"), getTestData(16, "testimonial_write"));

		reportStep("chooseLanguage_LanguageBengaliValidation_Testimonials started", "INFO");

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo);

		String fourStarTestimonials = "(//android.widget.TextView)[8]";
		String threeStarTestimonials = "(//android.widget.TextView)[7]";
		String twoStarTestimonials = "(//android.widget.TextView)[6]";
		String oneStarTestimonials = "(//android.widget.TextView)[5]";

		String emailAddress = objCashKaroUtility.generateEmail();
		String mobileNumber = objCashKaroUtility.generateMobileNumber();	

		String otpValue = new EKOnboardingPage(driver, testInfo).
				clickOnSignUpButton().
				enterFullName().
				enterEmail(emailAddress).
				enterPassword().
				enterMobileNumber(mobileNumber).
				clickOnGetOTPButton(mobileNumber);

		new OTPPage(driver, testInfo).
		enterOTP(otpValue).
		clickOnVerifyOTP();

		new HomePage(driver, testInfo).		
		clickOnProfileIconForSignedUser().
		clickOnChooseLanguage().
		clickBengaliLink().
		clickContinueButton().
		clickRelaunchButton();

		validateRequiredText(retrieveChildKeyValues(jsonObj4, "dynamic_home_top_Category"), "home page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "profile_tab"), "home page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj17, "rate_us_menu"), "profile page");
		validateRequiredText(retrieveChildKeyValues(jsonObj24, "count_txt"), "testimonials page");
		validateRequiredText(retrieveChildKeyValues(jsonObj24, "happy_users_txt"), "testimonials page");

		clickOnPartialRequiredText(retrieveChildKeyValues(jsonObj24, "write_testimonial_btn"), "testimonials page");
		validateRequiredText(retrieveChildKeyValues(jsonObj24, "write_testimonial_btn"), "write testimonials page");
		validateRequiredText(retrieveChildKeyValues(jsonObj25, "title"), "write testimonials page");

		validateRequiredText(retrieveChildKeyValues(jsonObj25, "rate_experience_txt"), "write testimonials page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj25, "star_txt_awesome_experience"), "write testimonials page");
		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(fourStarTestimonials);
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj25, "star_txt_good_experience"), "write testimonials page");
		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(threeStarTestimonials);
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj25, "star_txt_average"), "write testimonials page");
		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(twoStarTestimonials);
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj25, "star_txt_not_great"), "write testimonials page");
		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(oneStarTestimonials);
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj25, "star_txt_terrible"), "write testimonials page");


	}

	@Test
	public void chooseLanguage_LanguageBengaliValidation_ContactUs() throws UnsupportedEncodingException, FileNotFoundException, IOException, ParseException {

		JSONObject jsonObj1 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "bengaliFileName"), getTestData(16, "common_label"));
		JSONObject jsonObj2 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "bengaliFileName"), getTestData(16, "common_validate_label"));
		JSONObject jsonObj4 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "bengaliFileName"), getTestData(16, "home"));
		JSONObject jsonObj17 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "bengaliFileName"), getTestData(16, "profile_right_menu"));
		JSONObject jsonObj20 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "bengaliFileName"), getTestData(16, "contact_us"));		
		JSONObject jsonObj21 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "bengaliFileName"), getTestData(16, "faq"));

		reportStep("chooseLanguage_LanguageBengaliValidation_ContactUs started", "INFO");

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo);

		String textBoxUserName = "//android.widget.EditText[@content-desc='et_contactUs_yourName']";
		String textBoxEmail = "//android.widget.EditText[@content-desc='et_contactUs_email']";

		String emailAddress = objCashKaroUtility.generateEmail();
		String mobileNumber = objCashKaroUtility.generateMobileNumber();	

		String otpValue = new EKOnboardingPage(driver, testInfo).
				clickOnSignUpButton().
				enterFullName().
				enterEmail(emailAddress).
				enterPassword().
				enterMobileNumber(mobileNumber).
				clickOnGetOTPButton(mobileNumber);

		new OTPPage(driver, testInfo).
		enterOTP(otpValue).
		clickOnVerifyOTP();

		new HomePage(driver, testInfo).		
		clickOnProfileIconForSignedUser().
		clickOnChooseLanguage().
		clickBengaliLink().
		clickContinueButton().
		clickRelaunchButton();

		validateRequiredText(retrieveChildKeyValues(jsonObj4, "dynamic_home_top_Category"), "home page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "profile_tab"), "home page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj17, "help"), "profile page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj17, "contact_us_menu"), "help page");
		enterTextByXpath(textBoxUserName, "..");
		enterTextByXpath(textBoxEmail, "..");
		validateRequiredText(retrieveChildKeyValues(jsonObj21, "faq_btn"), "contact us page");
		validateRequiredText(retrieveChildKeyValues(jsonObj20, "name_inputfield_txt"), "contact us page");
		validateRequiredText(retrieveChildKeyValues(jsonObj20, "email_inputfield_txt"), "contact us page");
		validateRequiredText(retrieveChildKeyValues(jsonObj20, "message_inputfield_txt"), "contact us page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj20, "submit_btn"), "contact us page");

		validateRequiredText(retrieveChildKeyValues(jsonObj2, "select_subject_error_msg"), "contact us page");
		validateRequiredText(retrieveChildKeyValues(jsonObj20, "name_inputfield_txt"), "contact us page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "valid_name_error_msg"), "contact us page");
		validateRequiredText(retrieveChildKeyValues(jsonObj20, "email_inputfield_txt"), "contact us page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "valid_email_error_msg"), "contact us page");
		validateRequiredText(retrieveChildKeyValues(jsonObj20, "message_inputfield_txt"), "contact us page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "valid_details_error_msg"), "contact us page");

		enterTextByXpath(textBoxUserName, getTestData(7, "oneUserChar"));
		enterTextByXpath(textBoxEmail, getTestData(7, "oneUserChar"));
		clickOnRequiredText(retrieveChildKeyValues(jsonObj20, "submit_btn"), "contact us page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "select_subject_error_msg"), "contact us page");
		validateRequiredText(retrieveChildKeyValues(jsonObj20, "name_inputfield_txt"), "contact us page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "minimum_characters_error_msg"), 1, "contact us page");
		validateRequiredText(retrieveChildKeyValues(jsonObj20, "email_inputfield_txt"), "contact us page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "minimum_characters_error_msg"), 2, "contact us page");
		validateRequiredText(retrieveChildKeyValues(jsonObj20, "message_inputfield_txt"), "contact us page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "valid_details_error_msg"), "contact us page");

		enterTextByXpath(textBoxUserName, getTestData(7, "FiftyPlusUserChar"));
		enterTextByXpath(textBoxEmail, getTestData(7, "FiftyPlusUserChar"));
		clickOnRequiredText(retrieveChildKeyValues(jsonObj20, "submit_btn"), "contact us page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "select_subject_error_msg"), "contact us page");
		validateRequiredText(retrieveChildKeyValues(jsonObj20, "name_inputfield_txt"), "contact us page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "maximum_characters_error_msg"), 1, "contact us page");
		validateRequiredText(retrieveChildKeyValues(jsonObj20, "email_inputfield_txt"), "contact us page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "maximum_characters_error_msg"), 2, "contact us page");
		validateRequiredText(retrieveChildKeyValues(jsonObj20, "message_inputfield_txt"), "contact us page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "valid_details_error_msg"), "contact us page");

	}

	@Test
	public void chooseLanguage_LanguageBengaliValidation_FAQs() throws UnsupportedEncodingException, FileNotFoundException, IOException, ParseException {

		JSONObject jsonObj1 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "bengaliFileName"), getTestData(16, "common_label"));
		JSONObject jsonObj4 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "bengaliFileName"), getTestData(16, "home"));
		JSONObject jsonObj17 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "bengaliFileName"), getTestData(16, "profile_right_menu"));		
		JSONObject jsonObj21 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "bengaliFileName"), getTestData(16, "faq"));

		reportStep("chooseLanguage_LanguageBengaliValidation_FAQs started", "INFO");

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo);

		String emailAddress = objCashKaroUtility.generateEmail();
		String mobileNumber = objCashKaroUtility.generateMobileNumber();	

		String otpValue = new EKOnboardingPage(driver, testInfo).
				clickOnSignUpButton().
				enterFullName().
				enterEmail(emailAddress).
				enterPassword().
				enterMobileNumber(mobileNumber).
				clickOnGetOTPButton(mobileNumber);

		new OTPPage(driver, testInfo).
		enterOTP(otpValue).
		clickOnVerifyOTP();

		new HomePage(driver, testInfo).		
		clickOnProfileIconForSignedUser().
		clickOnChooseLanguage().
		clickBengaliLink().
		clickContinueButton().
		clickRelaunchButton();

		validateRequiredText(retrieveChildKeyValues(jsonObj4, "dynamic_home_top_Category"), "home page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "profile_tab"), "home page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj17, "help"), "profile page");

		validateRequiredText(retrieveChildKeyValues(jsonObj17, "call_us_menu"), "help page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj17, "faq_menu"), "help page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj21, "question1"), "faq page");
		//validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj21, "answer1"), "faq page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj21, "question2"), "faq page");
		//validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj21, "answer2"), "faq page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj21, "question3"), "faq page");
		//validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj21, "answer3"), "faq page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj21, "question4"), "faq page");
		//validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj21, "answer4"), "faq page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj21, "question5"), "faq page");
		//validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj21, "answer5"), "faq page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj21, "question6"), "faq page");
		//validatePartialRequiredText(retrieveChildKeyValues(jsonObj21, "answer6"), "faq page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj21, "question7"), "faq page");
		//validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj21, "answer7"), "faq page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj21, "question8"), "faq page");
		//validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj21, "answer8"), "faq page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj21, "question9"), "faq page");
		//validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj21, "answer9"), "faq page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj21, "question10"), "faq page");
		//validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj21, "answer10"), "faq page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj21, "question11"), "faq page");
		//validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj21, "answer11"), "faq page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj21, "question12"), "faq page");
		//validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj21, "answer12"), "faq page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj21, "question13"), "faq page");
		//validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj21, "answer13"), "faq page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj21, "question14"), "faq page");
		//validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj21, "answer14"), "faq page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj21, "question15"), "faq page");
		//validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj21, "answer15"), "faq page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj21, "question16"), "faq page");
		//validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj21, "answer16"), "faq page");

		validateRequiredText(retrieveChildKeyValues(jsonObj21, "faq_btn"), "faq page");


	}

	@Test
	public void chooseLanguage_LanguageBengaliValidation_History_NewUser() throws UnsupportedEncodingException, FileNotFoundException, IOException, ParseException {

		JSONObject jsonObj1 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "bengaliFileName"), getTestData(16, "common_label"));
		JSONObject jsonObj4 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "bengaliFileName"), getTestData(16, "home"));
		JSONObject jsonObj17 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "bengaliFileName"), getTestData(16, "profile_right_menu"));
		JSONObject jsonObj52 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "bengaliFileName"), getTestData(16, "my_shared_activities"));

		reportStep("chooseLanguage_LanguageBengaliValidation_History_NewUser started", "INFO");

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo);

		String emailAddress = objCashKaroUtility.generateEmail();
		String mobileNumber = objCashKaroUtility.generateMobileNumber();	

		String otpValue = new EKOnboardingPage(driver, testInfo).
				clickOnSignUpButton().
				enterFullName().
				enterEmail(emailAddress).
				enterPassword().
				enterMobileNumber(mobileNumber).
				clickOnGetOTPButton(mobileNumber);

		new OTPPage(driver, testInfo).
		enterOTP(otpValue).
		clickOnVerifyOTP();

		new HomePage(driver, testInfo).		
		clickOnProfileIconForSignedUser().
		clickOnChooseLanguage().
		clickBengaliLink().
		clickContinueButton().
		clickRelaunchButton();

		validateRequiredText(retrieveChildKeyValues(jsonObj4, "dynamic_home_top_Category"), "home page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "profile_tab"), "home page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj17, "history_menu"), "profile page");
		validateRequiredText(retrieveChildKeyValues(jsonObj17, "recent_clicks_menu"), "history page");
		validateRequiredText(retrieveChildKeyValues(jsonObj1, "whoops_msg"), "history page");
		validateRequiredText(retrieveChildKeyValues(jsonObj52, "no_shared_activites"), "history page");
		validateRequiredText(retrieveChildKeyValues(jsonObj52, "saving_click_away"), "history page");


	}

	@Test
	public void chooseLanguage_LanguageBengaliValidation_AccountSettings() throws UnsupportedEncodingException, FileNotFoundException, IOException, ParseException {

		JSONObject jsonObj1 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "bengaliFileName"), getTestData(16, "common_label"));
		JSONObject jsonObj2 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "bengaliFileName"), getTestData(16, "common_validate_label"));
		JSONObject jsonObj17 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "bengaliFileName"), getTestData(16, "profile_right_menu"));
		JSONObject jsonObj26 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "bengaliFileName"), getTestData(16, "personal_details"));
		JSONObject jsonObj27 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "bengaliFileName"), getTestData(16, "change_password"));
		JSONObject jsonObj40 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "bengaliFileName"), getTestData(16, "giftcard_wallet_inputfield"));

		reportStep("chooseLanguage_LanguageBengaliValidation_AccountSettings started", "INFO");

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo);

		String textBoxExistingPwd = "//android.widget.EditText[@content-desc='et_changePassword_currentPassword']";
		String textBoxNewPwd = "//android.widget.EditText[@content-desc='et_changePassword_fullName']";
		String textBoxConfirmNewPwd = "//android.widget.EditText[@content-desc='et_changePassword_confirmPassword']";

		String dropDownPaymentSettings = "(//android.widget.EditText)[1]";
		String textNEFT = "//android.widget.CheckedTextView[@text='Bank Payment (NEFT)']";
		String textPaytmWallet = "//android.widget.CheckedTextView[@text='Paytm Wallet']";
		String textAmazonGiftCard = "//android.widget.CheckedTextView[@text='Amazon Gift Card']";

		String textBoxPaytmMobileNumber = "//android.widget.EditText[@content-desc='et_settingsPaytm_mobile']";
		String textBoxPaytmPassword = "//android.widget.EditText[@content-desc='et_settingsPaytm_password']";

		String textBoxAmazonEmail = "//android.widget.EditText[@content-desc='et_settingsAmazon_email']";
		String textBoxAmazonPassword = "//android.widget.EditText[@content-desc='et_settingsAmazon_password']";
		String emailAddress = objCashKaroUtility.generateEmail();
		String mobileNumber = objCashKaroUtility.generateMobileNumber();	

		String otpValue = new EKOnboardingPage(driver, testInfo).
				clickOnSignUpButton().
				enterFullName().
				enterEmail(emailAddress).
				enterPassword().
				enterMobileNumber(mobileNumber).
				clickOnGetOTPButton(mobileNumber);

		new OTPPage(driver, testInfo).
		enterOTP(otpValue).
		clickOnVerifyOTP();

		new HomePage(driver, testInfo).		
		clickOnProfileIconForSignedUser().
		clickOnChooseLanguage().
		clickBengaliLink().
		clickContinueButton().
		clickRelaunchButton();

		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "profile_tab"), "home page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj17, "account_setting_menu"), "profile page");
		validateRequiredText(retrieveChildKeyValues(jsonObj17, "account_setting_menu"), "profile page");
		clickOnPartialRequiredText(retrieveChildKeyValues(jsonObj1, "personal_details_tab"), "account settings page");
		scrollFromUpToDowninApp();scrollFromUpToDowninApp();
		validateRequiredText(retrieveChildKeyValues(jsonObj26, "name_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj26, "email_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj26, "mobile_number_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj26, "receive_newsletter_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj26, "receive_referral_earning_txt"), "account settings page");
		validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj26, "note_txt"), "account settings page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "save_changes_btn"), "account settings page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj26, "no_value_change_error_msg"), "account settings page");

		clickOnPartialRequiredText(retrieveChildKeyValues(jsonObj1, "change_password_tab"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj27, "change_password_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj27, "new_password_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj27, "confirm_password_inputfield_txt"), "account settings page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "save_changes_btn"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj27, "change_password_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "current_password_error_msg"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj27, "new_password_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "new_password_error_msg"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj27, "confirm_password_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "confirm_password_error_msg"), "account settings page");

		enterTextByXpath(textBoxExistingPwd, getTestData(7, "oneUserChar"));
		enterTextByXpath(textBoxNewPwd, getTestData(7, "oneUserChar"));
		enterTextByXpath(textBoxConfirmNewPwd, getTestData(7, "oneUserChar"));
		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "save_changes_btn"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj27, "change_password_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "password_minimum_characters_error_msg"), 1, "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj27, "new_password_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "password_minimum_characters_error_msg"), 2, "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj27, "confirm_password_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "password_minimum_characters_error_msg"), 3, "account settings page");

		enterTextByXpath(textBoxExistingPwd, getTestData(7, "FiftyUserChar"));
		enterTextByXpath(textBoxNewPwd, getTestData(7, "FiftyUserChar"));
		enterTextByXpath(textBoxConfirmNewPwd, getTestData(7, "FiftyUserChar"));
		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "save_changes_btn"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj27, "change_password_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "password_maximum_characters_error_msg"), 1, "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj27, "new_password_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "password_maximum_characters_error_msg"), 2, "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj27, "confirm_password_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "password_maximum_characters_error_msg"), 3, "account settings page");


		enterTextByXpath(textBoxExistingPwd, getTestData(0, "TC001_ValidLoginPassword"));
		enterTextByXpath(textBoxNewPwd, getTestData(0, "TC001_ValidLoginPassword"));
		enterTextByXpath(textBoxConfirmNewPwd, getTestData(0, "TC001_InValidPassword"));
		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "save_changes_btn"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj27, "change_password_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj27, "new_password_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj27, "confirm_password_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "password_not_match_error_msg"), "account settings page");

		clickOnPartialRequiredText(retrieveChildKeyValues(jsonObj1, "payment_settings_tab"), "account settings page");

		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(dropDownPaymentSettings);
		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(textPaytmWallet);
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "mobile_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "confirm_password_inputfield_txt"), "account settings page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "save_changes_btn"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "mobile_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "mobile_number_error_msg"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "confirm_password_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "password_error_msg"), "account settings page");

		enterTextByXpath(textBoxPaytmMobileNumber, "1");
		enterTextByXpath(textBoxPaytmPassword, "1");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "save_changes_btn"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "mobile_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "minimum_digits_error_msg"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "confirm_password_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "password_minimum_characters_error_msg"), "account settings page");

		enterTextByXpath(textBoxPaytmPassword, getTestData(7, "FiftyUserChar"));
		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "save_changes_btn"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "confirm_password_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "password_maximum_characters_error_msg"), "account settings page");

		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(dropDownPaymentSettings);
		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(textAmazonGiftCard);

		validateRequiredText(retrieveChildKeyValues(jsonObj40, "email_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "confirm_password_inputfield_txt"), "account settings page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "save_changes_btn"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "email_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "email_error_msg"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "confirm_password_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "password_error_msg"), "account settings page");

		enterTextByXpath(textBoxAmazonEmail, "1");
		enterTextByXpath(textBoxAmazonPassword, "1");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "save_changes_btn"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "email_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "minimum_characters_error_msg"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "confirm_password_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "password_minimum_characters_error_msg"), "account settings page");

		enterTextByXpath(textBoxAmazonEmail, "asdfghjkl");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "save_changes_btn"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "email_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "valid_email_error_msg"), "account settings page");

		enterTextByXpath(textBoxAmazonEmail, getTestData(7, "FiftyUserCharWithSpace"));
		enterTextByXpath(textBoxAmazonPassword, getTestData(7, "FiftyUserChar"));
		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "save_changes_btn"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "email_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "maximum_characters_error_msg"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "confirm_password_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "maximum_characters_error_msg"), "account settings page");

		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(dropDownPaymentSettings);
		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(textNEFT);

		validateRequiredText(retrieveChildKeyValues(jsonObj40, "bank_account_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "bank_name_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "branch_name_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "bank_account_number_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "bank_ifsc_code_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "confirm_password_inputfield_txt"), "account settings page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "save_changes_btn"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "bank_account_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "account_holder_error_msg"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "bank_name_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "valid_bank_name_error_msg"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "branch_name_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "branch_minimum_digits_error_msg"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "bank_account_number_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "account_number_error_msg"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "bank_ifsc_code_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "ifsc_code_error_msg"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "confirm_password_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "password_error_msg"), "account settings page");

	}

	@Test
	public void chooseLanguage_LanguageBengaliValidation_ReferAndEarn() throws UnsupportedEncodingException, FileNotFoundException, IOException, ParseException {

		JSONObject jsonObj1 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "bengaliFileName"), getTestData(16, "common_label"));
		JSONObject jsonObj17 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "bengaliFileName"), getTestData(16, "profile_right_menu"));
		JSONObject jsonObj18 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "bengaliFileName"), getTestData(16, "referandearn"));

		reportStep("chooseLanguage_LanguageBengaliValidation_AccountSettings started", "INFO");

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo);

		String emailAddress = objCashKaroUtility.generateEmail();
		String mobileNumber = objCashKaroUtility.generateMobileNumber();	

		String otpValue = new EKOnboardingPage(driver, testInfo).
				clickOnSignUpButton().
				enterFullName().
				enterEmail(emailAddress).
				enterPassword().
				enterMobileNumber(mobileNumber).
				clickOnGetOTPButton(mobileNumber);

		new OTPPage(driver, testInfo).
		enterOTP(otpValue).
		clickOnVerifyOTP();

		new HomePage(driver, testInfo).		
		clickOnProfileIconForSignedUser().
		clickOnChooseLanguage().
		clickBengaliLink().
		clickContinueButton().
		clickRelaunchButton();

		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "profile_tab"), "home page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj17, "my_referral_menu"), "profile page");
		validateRequiredText(retrieveChildKeyValues(jsonObj17, "my_referral_menu"), "my referral page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj17, "referandearn_menu"), "my referral page");
		validateRequiredText(retrieveChildKeyValues(jsonObj17, "referandearn_menu"), "my referral page");
		scrollFromUpToDowninApp();scrollFromUpToDowninApp();
		validateRequiredText(retrieveChildKeyValues(jsonObj18, "title"), "my referral page");
		validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj18, "sub_title"), "my referral page");
		validateRequiredText(retrieveChildKeyValues(jsonObj18, "message"), "my referral page");
		validateRequiredText(retrieveChildKeyValues(jsonObj18, "referral_link_title"), "my referral page");
		validateRequiredText(retrieveChildKeyValues(jsonObj18, "copy_message_title"), "my referral page");
		validateRequiredText(retrieveChildKeyValues(jsonObj18, "tap_copy"), "my referral page");
		validateRequiredText(retrieveChildKeyValues(jsonObj18, "invite_title"), "my referral page");


	}

	@Test
	public void chooseLanguage_LanguageBengaliValidation_ReferralNetwork() throws UnsupportedEncodingException, FileNotFoundException, IOException, ParseException {

		JSONObject jsonObj1 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "bengaliFileName"), getTestData(16, "common_label"));
		JSONObject jsonObj17 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "bengaliFileName"), getTestData(16, "profile_right_menu"));
		JSONObject jsonObj19 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "bengaliFileName"), getTestData(16, "referral_network"));

		reportStep("chooseLanguage_LanguageBengaliValidation_AccountSettings started", "INFO");

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo);

		String emailAddress = objCashKaroUtility.generateEmail();
		String mobileNumber = objCashKaroUtility.generateMobileNumber();	

		String otpValue = new EKOnboardingPage(driver, testInfo).
				clickOnSignUpButton().
				enterFullName().
				enterEmail(emailAddress).
				enterPassword().
				enterMobileNumber(mobileNumber).
				clickOnGetOTPButton(mobileNumber);

		new OTPPage(driver, testInfo).
		enterOTP(otpValue).
		clickOnVerifyOTP();

		new HomePage(driver, testInfo).		
		clickOnProfileIconForSignedUser().
		clickOnChooseLanguage().
		clickBengaliLink().
		clickContinueButton().
		clickRelaunchButton();

		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "profile_tab"), "home page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj17, "my_referral_menu"), "profile page");
		validateRequiredText(retrieveChildKeyValues(jsonObj17, "my_referral_menu"), "my referral page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj17, "referral_network_menu"), "profile page");
		validateRequiredText(retrieveChildKeyValues(jsonObj17, "referral_network_menu"), "referral nw page");
		validateRequiredText(retrieveChildKeyValues(jsonObj19, "title"), "referral nw page");
		validateRequiredText(retrieveChildKeyValues(jsonObj19, "total_referral_earn_txt"), "referral nw page");
		validateRequiredText(retrieveChildKeyValues(jsonObj19, "friend_joined_txt"), "referral nw page");
		validateRequiredText(retrieveChildKeyValues(jsonObj19, "no_referral_title"), "referral nw page");
		validateRequiredText(retrieveChildKeyValues(jsonObj19, "no_referral_message"), "referral nw page");
		validateRequiredText(retrieveChildKeyValues(jsonObj19, "refer_earn_btn"), "referral nw page");


	}

	@Test
	public void chooseLanguage_LanguageBengaliValidation_MyEarnings_NewUser() throws UnsupportedEncodingException, FileNotFoundException, IOException, ParseException {

		JSONObject jsonObj1 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "bengaliFileName"), getTestData(16, "common_label"));
		JSONObject jsonObj4 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "bengaliFileName"), getTestData(16, "home"));
		JSONObject jsonObj17 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "bengaliFileName"), getTestData(16, "profile_right_menu"));
		JSONObject jsonObj28 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "bengaliFileName"), getTestData(16, "my_account_earnings"));
		JSONObject jsonObj30 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "bengaliFileName"), getTestData(16, "earnings_info"));

		JSONObject jsonObj33 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "bengaliFileName"), getTestData(16, "my_earnings"));

		reportStep("chooseLanguage_LanguageBengaliValidation_MyEarnings_NewUser started", "INFO");

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo);

		String linkEarningsInfo = "//android.widget.TextView[@content-desc='txt_myEarnings_cashbackEarningsInfo']";

		String emailAddress = objCashKaroUtility.generateEmail();
		String mobileNumber = objCashKaroUtility.generateMobileNumber();	

		String otpValue = new EKOnboardingPage(driver, testInfo).
				clickOnSignUpButton().
				enterFullName().
				enterEmail(emailAddress).
				enterPassword().
				enterMobileNumber(mobileNumber).
				clickOnGetOTPButton(mobileNumber);

		new OTPPage(driver, testInfo).
		enterOTP(otpValue).
		clickOnVerifyOTP();

		new HomePage(driver, testInfo).		
		clickOnProfileIconForSignedUser().
		clickOnChooseLanguage().
		clickBengaliLink().
		clickContinueButton().
		clickRelaunchButton();

		validateRequiredText(retrieveChildKeyValues(jsonObj4, "dynamic_home_top_Category"), "home page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "profile_tab"), "home page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj17, "my_earnings_menu"), "profile page");
		validateRequiredText(retrieveChildKeyValues(jsonObj17, "my_earnings_menu"), "my earnings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj28, "total_earning_txt"), "my earnings page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj33, "cashback_tap"), "my earnings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj33, "cashback_earning_title"), "my earnings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj33, "request_cashback_btn"), "my earnings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj33, "no_cashback_yet_txt"), "my earnings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj33, "shop_today_txt"), "my earnings page");

		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(linkEarningsInfo);
		validateRequiredText(retrieveChildKeyValues(jsonObj30, "message_first_txt"), "earnings info page");


		backButton();

		clickOnRequiredText(retrieveChildKeyValues(jsonObj33, "referral_tap"), "my earnings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj33, "referral_earning_title"), "my earnings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj33, "request_cashback_btn"), "my earnings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj33, "no_referral_yet_txt"), "my earnings page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj33, "no_referral_yet_sub_txt"), "my earnings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj33, "no_referral_invite_some_friends"), "my earnings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj33, "refer_now_btn"), "my earnings page");


	}

	@Test
	public void chooseLanguage_LanguageBengaliValidation_MakeLinks_NewUser() throws UnsupportedEncodingException, FileNotFoundException, IOException, ParseException {

		JSONObject jsonObj1 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "bengaliFileName"), getTestData(16, "common_label"));
		JSONObject jsonObj2 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "bengaliFileName"), getTestData(16, "common_validate_label"));
		JSONObject jsonObj4 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "bengaliFileName"), getTestData(16, "home"));
		JSONObject jsonObj17 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "bengaliFileName"), getTestData(16, "profile_right_menu"));
		JSONObject jsonObj51 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "bengaliFileName"), getTestData(16, "make_profit_links"));
		JSONObject jsonObj53 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "bengaliFileName"), getTestData(16, "profit_partners_details"));
		JSONObject jsonObj54 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "bengaliFileName"), getTestData(16, "profit_partners_list"));

		reportStep("chooseLanguage_LanguageBengaliValidation_MakeLinks_NewUser started", "INFO");

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo);

		String imagePartner = "//android.widget.ImageView[@content-desc='img_profitPartnersList_partnersIMG']";
		String emailAddress = objCashKaroUtility.generateEmail();
		String mobileNumber = objCashKaroUtility.generateMobileNumber();	

		String otpValue = new EKOnboardingPage(driver, testInfo).
				clickOnSignUpButton().
				enterFullName().
				enterEmail(emailAddress).
				enterPassword().
				enterMobileNumber(mobileNumber).
				clickOnGetOTPButton(mobileNumber);

		new OTPPage(driver, testInfo).
		enterOTP(otpValue).
		clickOnVerifyOTP();

		new HomePage(driver, testInfo).		
		clickOnProfileIconForSignedUser().
		clickOnChooseLanguage().
		clickBengaliLink().
		clickContinueButton().
		clickRelaunchButton();

		validateRequiredText(retrieveChildKeyValues(jsonObj4, "dynamic_home_top_Category"), "home page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "profile_tab"), "home page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj17, "make_profit_link_menu"), "profile page");
		validateRequiredText(retrieveChildKeyValues(jsonObj51, "title"), "make links page");
		validateRequiredText(retrieveChildKeyValues(jsonObj51, "subtitle"), "make links page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj51, "see_partner_txt"), "make links page");
		validateRequiredText(retrieveChildKeyValues(jsonObj54, "toolbar_profit_partners_title"), "partners page");
		validateRequiredText(retrieveChildKeyValues(jsonObj54, "profit_partners_information"), "partners page");

		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(imagePartner);
		validateRequiredText(retrieveChildKeyValues(jsonObj53, "profit_rates"), "store page");

		backButton();
		backButton();

		validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj51, "paste_link"), "make links page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj51, "make_profit_link_txt"), "make links page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "url_error_msg"), "make links page");
		validateRequiredText(retrieveChildKeyValues(jsonObj51, "how_make_profit_txt"), "make links page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "url_error_msg"), "make links page");		

		//Here no need to scroll : since the swiping happens from left to right
		scrollFromDownToUpinApp();
		validateRequiredText_WithOutScrolling(retrieveChildKeyValues(jsonObj51, "step_one_title"), "make links page");
		validateRequiredText_WithOutScrolling(retrieveChildKeyValues(jsonObj51, "step_one_txt"), "make links page");
		validateRequiredText_WithOutScrolling(retrieveChildKeyValues(jsonObj51, "step_two_title"), "make links page");
		validateRequiredText_WithOutScrolling(retrieveChildKeyValues(jsonObj51, "step_two_txt"), "make links page");
		validateRequiredText_WithOutScrolling(retrieveChildKeyValues(jsonObj51, "step_three_title"), "make links page");
		validateRequiredText_WithOutScrolling(retrieveChildKeyValues(jsonObj51, "step_three_txt"), "make links page");
		validateRequiredText_WithOutScrolling(retrieveChildKeyValues(jsonObj51, "step_four_title"), "make links page");
		validateRequiredText_WithOutScrolling(retrieveChildKeyValues(jsonObj51, "step_four_txt"), "make links page");


	}

	@Test
	public void chooseLanguage_LanguageBengaliValidation_GetStarted_Joinfree_TermsAndConditions() throws UnsupportedEncodingException, FileNotFoundException, IOException, ParseException {

		JSONObject jsonObj1 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "bengaliFileName"), getTestData(16, "common_label"));
		JSONObject jsonObj2 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "bengaliFileName"), getTestData(16, "common_validate_label"));
		JSONObject jsonObj17 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "bengaliFileName"), getTestData(16, "profile_right_menu"));
		JSONObject jsonObj34 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "bengaliFileName"), getTestData(16, "on_boarding"));
		JSONObject jsonObj36 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "bengaliFileName"), getTestData(16, "join_free"));
		JSONObject jsonObj50 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "bengaliFileName"), getTestData(16, "terms_conditions"));

		reportStep("chooseLanguage_LanguageBengaliValidation_GetStarted_Joinfree_Login_ForgotPassword started", "INFO");

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo);

		String textboxName = "//android.widget.EditText[@content-desc='et_signup_fullname']";
		String textboxEmail = "//android.widget.EditText[@content-desc='et_signup_email']|//android.widget.EditText[@content-desc='et_signin_email']|//android.widget.EditText[@content-desc='et_forgotpassword_email']";
		String textboxPwd = "//android.widget.EditText[@content-desc='et_signup_password']|//android.widget.EditText[@content-desc='et_signin_password']";
		String textboxMobileNumber = "//android.widget.EditText[@content-desc='et_signup_mobile']";		
		String emailAddress = objCashKaroUtility.generateEmail();
		String mobileNumber = objCashKaroUtility.generateMobileNumber();	

		String otpValue = new EKOnboardingPage(driver, testInfo).
				clickOnSignUpButton().
				enterFullName().
				enterEmail(emailAddress).
				enterPassword().
				enterMobileNumber(mobileNumber).
				clickOnGetOTPButton(mobileNumber);

		new OTPPage(driver, testInfo).
		enterOTP(otpValue).
		clickOnVerifyOTP();

		new HomePage(driver, testInfo).		
		clickOnProfileIconForSignedUser().
		clickOnChooseLanguage().
		clickBengaliLink().
		clickContinueButton().
		clickRelaunchButton();

		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "profile_tab"), "home page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj17, "logout_menu"), "profile page");
		validateRequiredText(retrieveChildKeyValues(jsonObj17, "alert_logout"), "logout popup");
		validateRequiredText(retrieveChildKeyValues(jsonObj17, "alert_logout_msg"), "logout popup");
		validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj17, "alert_cancel_btn"), "logout popup");
		clickOnPartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj17, "alert_ok_btn"), "logout popup");

		new EKOnboardingPage(driver, testInfo).
		clickOnSignUpButton();

		validateRequiredText(retrieveChildKeyValues(jsonObj17, "join_free_menu"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "title"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "subtitle"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "join_facebook_txt"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "join_or_txt"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "name_inputfield_txt"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "email_inputfield_txt"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "password_inputfield_txt"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "mobile_inputfield_txt"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "otp_btn"), "join free page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj36, "already_register"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "sign_in_txt"), "join free page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj36, "signup_terms_txt"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "terms_condition_txt"), "join free page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj36, "otp_btn"), "join free page");		
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "name_inputfield_txt"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "name_error_msg"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "email_inputfield_txt"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "email_error_msg"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "password_inputfield_txt"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "password_error_msg"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "mobile_inputfield_txt"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "mobile_number_error_msg"), "join free page");

		enterTextByXpath(textboxName, getTestData(7, "oneUserChar"));
		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(textboxEmail);
		clickOnNoneOFTheAbove();
		enterTextByXpath(textboxEmail, getTestData(7, "oneUserChar"));
		enterTextByXpath(textboxPwd, getTestData(7, "oneUserChar"));
		enterTextByXpath(textboxMobileNumber, getTestData(7, "oneUserChar"));
		clickOnRequiredText(retrieveChildKeyValues(jsonObj36, "otp_btn"), "join free page");		
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "name_inputfield_txt"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "minimum_characters_error_msg"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "email_inputfield_txt"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "minimum_characters_error_msg"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "password_inputfield_txt"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "password_minimum_characters_error_msg"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "mobile_inputfield_txt"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "minimum_digits_error_msg"), "join free page");

		enterTextByXpath(textboxName, getTestData(7, "FiftyUserCharWithSpace"));
		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(textboxEmail);
		clickOnNoneOFTheAbove();
		enterTextByXpath(textboxEmail, getTestData(7, "FiftyUserCharWithSpace"));
		enterTextByXpath(textboxPwd, getTestData(7, "FiftyUserChar"));
		enterTextByXpath(textboxMobileNumber, "2222222222");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj36, "otp_btn"), "join free page");		
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "name_inputfield_txt"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "maximum_characters_error_msg"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "email_inputfield_txt"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "maximum_characters_error_msg"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "password_inputfield_txt"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "password_maximum_characters_error_msg"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "mobile_inputfield_txt"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "valid_mobile_number_error_msg"), "join free page");

		enterTextByXpath(textboxName, "2*2");
		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(textboxEmail);
		clickOnNoneOFTheAbove();
		enterTextByXpath(textboxEmail, "2*2");
		enterTextByXpath(textboxPwd, "2 2 2 2 2");
		enterTextByXpath(textboxMobileNumber, "2*22222222");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj36, "otp_btn"), "join free page");		
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "name_inputfield_txt"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "valid_name_error_msg"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "email_inputfield_txt"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "valid_email_error_msg"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "password_inputfield_txt"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "valid_password_error_msg"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "mobile_inputfield_txt"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "valid_mobile_number_error_msg"), "join free page");

		backButton();

		new EKOnboardingPage(driver, testInfo).
		clickOnSignUpButton();
		clickOnRequiredText(retrieveChildKeyValues(jsonObj36, "terms_condition_txt"), "join free page");
		scrollFromUpToDowninApp();scrollFromUpToDowninApp();
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "terms_condition_txt"), "terms & conditions page");
		validateRequiredText(retrieveChildKeyValues(jsonObj50, "title"), "terms & conditions page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj50, "introduction_title"), "terms & conditions page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj50, "introduction_details"), "terms & conditions page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj50, "membership_title"), "terms & conditions page");
		validateRequiredText(retrieveChildKeyValues(jsonObj50, "membership_details"), "terms & conditions page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj50, "our_service_title"), "terms & conditions page");
		validateRequiredText(retrieveChildKeyValues(jsonObj50, "our_service_details"), "terms & conditions page");


	}

	@Test
	public void chooseLanguage_LanguageBengaliValidation_Login() throws UnsupportedEncodingException, FileNotFoundException, IOException, ParseException {

		JSONObject jsonObj1 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "bengaliFileName"), getTestData(16, "common_label"));
		JSONObject jsonObj2 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "bengaliFileName"), getTestData(16, "common_validate_label"));
		JSONObject jsonObj17 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "bengaliFileName"), getTestData(16, "profile_right_menu"));
		JSONObject jsonObj35 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "bengaliFileName"), getTestData(16, "sign_in"));
		JSONObject jsonObj36 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "bengaliFileName"), getTestData(16, "join_free"));

		reportStep("chooseLanguage_LanguageBengaliValidation_GetStarted_Joinfree_Login_ForgotPassword started", "INFO");

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo);

		String textboxEmail = "//android.widget.EditText[@content-desc='et_signup_email']|//android.widget.EditText[@content-desc='et_signin_email']|//android.widget.EditText[@content-desc='et_forgotpassword_email']";
		String textboxPwd = "//android.widget.EditText[@content-desc='et_signup_password']|//android.widget.EditText[@content-desc='et_signin_password']";
		String emailAddress = objCashKaroUtility.generateEmail();
		String mobileNumber = objCashKaroUtility.generateMobileNumber();	

		String otpValue = new EKOnboardingPage(driver, testInfo).
				clickOnSignUpButton().
				enterFullName().
				enterEmail(emailAddress).
				enterPassword().
				enterMobileNumber(mobileNumber).
				clickOnGetOTPButton(mobileNumber);

		new OTPPage(driver, testInfo).
		enterOTP(otpValue).
		clickOnVerifyOTP();

		new HomePage(driver, testInfo).		
		clickOnProfileIconForSignedUser().
		clickOnChooseLanguage().
		clickBengaliLink().
		clickContinueButton().
		clickRelaunchButton();

		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "profile_tab"), "home page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj17, "logout_menu"), "profile page");
		validateRequiredText(retrieveChildKeyValues(jsonObj17, "alert_logout"), "logout popup");
		validateRequiredText(retrieveChildKeyValues(jsonObj17, "alert_logout_msg"), "logout popup");
		validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj17, "alert_cancel_btn"), "logout popup");
		clickOnPartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj17, "alert_ok_btn"), "logout popup");

		new EKOnboardingPage(driver, testInfo).
		clickOnSignInbutton();

		validateRequiredText(retrieveChildKeyValues(jsonObj36, "sign_in_txt"), "login page");
		validateRequiredText(retrieveChildKeyValues(jsonObj35, "subtitle"), "login page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj35, "login_txt"), "login page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj35, "share_txt"), "login page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj35, "earn_txt"), "login page");
		validateRequiredText(retrieveChildKeyValues(jsonObj35, "subtitle"), "login page");
		validateRequiredText(retrieveChildKeyValues(jsonObj35, "sign_in_facebook_txt"), "login page");
		validateRequiredText(retrieveChildKeyValues(jsonObj35, "sign_in_or_txt"), "login page");
		validateRequiredText(retrieveChildKeyValues(jsonObj35, "email_inputfield_txt"), "login page");
		validateRequiredText(retrieveChildKeyValues(jsonObj35, "password_inputfield_txt"), "login page");
		validateRequiredText(retrieveChildKeyValues(jsonObj35, "forgot_password_txt"), "login page");
		validateRequiredText(retrieveChildKeyValues(jsonObj35, "sign_with_email_btn"), "login page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj35, "new_user_txt"), "login page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj35, "join_free_txt"), "login page");


		clickOnRequiredText(retrieveChildKeyValues(jsonObj35, "sign_with_email_btn"), "login page");
		validateRequiredText(retrieveChildKeyValues(jsonObj35, "email_inputfield_txt"), "login page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "email_error_msg"), "login page");
		validateRequiredText(retrieveChildKeyValues(jsonObj35, "password_inputfield_txt"), "login page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "password_error_msg"), "login page");

		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(textboxEmail);
		clickOnNoneOFTheAbove();
		enterTextByXpath(textboxEmail, getTestData(7, "oneUserChar"));
		enterTextByXpath(textboxPwd, getTestData(7, "oneUserChar"));
		clickOnRequiredText(retrieveChildKeyValues(jsonObj35, "sign_with_email_btn"), "login page");
		validateRequiredText(retrieveChildKeyValues(jsonObj35, "email_inputfield_txt"), "login page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "minimum_characters_error_msg"), "login page");
		validateRequiredText(retrieveChildKeyValues(jsonObj35, "password_inputfield_txt"), "login page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "password_minimum_characters_error_msg"), "login page");

		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(textboxEmail);
		clickOnNoneOFTheAbove();
		enterTextByXpath(textboxEmail, getTestData(7, "FiftyUserCharWithSpace"));
		enterTextByXpath(textboxPwd, getTestData(7, "FiftyUserChar"));
		clickOnRequiredText(retrieveChildKeyValues(jsonObj35, "sign_with_email_btn"), "login page");
		validateRequiredText(retrieveChildKeyValues(jsonObj35, "email_inputfield_txt"), "login page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "maximum_characters_error_msg"), "login page");
		validateRequiredText(retrieveChildKeyValues(jsonObj35, "password_inputfield_txt"), "login page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "password_maximum_characters_error_msg"), "login page");

		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(textboxEmail);
		clickOnNoneOFTheAbove();
		enterTextByXpath(textboxEmail, "2*2");
		enterTextByXpath(textboxPwd, "2 2 2 2 2");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj35, "sign_with_email_btn"), "login page");
		validateRequiredText(retrieveChildKeyValues(jsonObj35, "email_inputfield_txt"), "login page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "valid_email_error_msg"), "login page");
		validateRequiredText(retrieveChildKeyValues(jsonObj35, "password_inputfield_txt"), "login page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "valid_password_error_msg"), "login page");


	}

	@Test
	public void chooseLanguage_LanguageBengaliValidation_ForgotPassword() throws UnsupportedEncodingException, FileNotFoundException, IOException, ParseException {

		JSONObject jsonObj1 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "bengaliFileName"), getTestData(16, "common_label"));
		JSONObject jsonObj2 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "bengaliFileName"), getTestData(16, "common_validate_label"));
		JSONObject jsonObj17 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "bengaliFileName"), getTestData(16, "profile_right_menu"));
		JSONObject jsonObj35 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "bengaliFileName"), getTestData(16, "sign_in"));
		JSONObject jsonObj37 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "bengaliFileName"), getTestData(16, "forgot_password"));

		reportStep("chooseLanguage_LanguageBengaliValidation_GetStarted_Joinfree_Login_ForgotPassword started", "INFO");

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo);

		String textboxEmail = "//android.widget.EditText[@content-desc='et_signup_email']|//android.widget.EditText[@content-desc='et_signin_email']|//android.widget.EditText[@content-desc='et_forgotpassword_email']";
		String emailAddress = objCashKaroUtility.generateEmail();
		String mobileNumber = objCashKaroUtility.generateMobileNumber();	

		String otpValue = new EKOnboardingPage(driver, testInfo).
				clickOnSignUpButton().
				enterFullName().
				enterEmail(emailAddress).
				enterPassword().
				enterMobileNumber(mobileNumber).
				clickOnGetOTPButton(mobileNumber);

		new OTPPage(driver, testInfo).
		enterOTP(otpValue).
		clickOnVerifyOTP();

		new HomePage(driver, testInfo).		
		clickOnProfileIconForSignedUser().
		clickOnChooseLanguage().
		clickBengaliLink().
		clickContinueButton().
		clickRelaunchButton();

		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "profile_tab"), "home page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj17, "logout_menu"), "profile page");
		validateRequiredText(retrieveChildKeyValues(jsonObj17, "alert_logout"), "logout popup");
		validateRequiredText(retrieveChildKeyValues(jsonObj17, "alert_logout_msg"), "logout popup");
		validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj17, "alert_cancel_btn"), "logout popup");
		clickOnPartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj17, "alert_ok_btn"), "logout popup");

		new EKOnboardingPage(driver, testInfo).
		clickOnSignInbutton();


		clickOnRequiredText(retrieveChildKeyValues(jsonObj35, "forgot_password_txt"), "login page");
		validateRequiredText(retrieveChildKeyValues(jsonObj35, "forgot_password_txt"), "forgot password page");
		validateRequiredText(retrieveChildKeyValues(jsonObj37, "title"), "forgot password page");
		validateRequiredText(retrieveChildKeyValues(jsonObj37, "subtitle"), "forgot password page");
		validateRequiredText(retrieveChildKeyValues(jsonObj37, "email_inputfield_txt"), "forgot password page");
		validateRequiredText(retrieveChildKeyValues(jsonObj37, "password_link_txt"), "forgot password page");
		validateRequiredText(retrieveChildKeyValues(jsonObj37, "submit_btn"), "forgot password page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj37, "remember_password_txt"), "forgot password page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj37, "login_txt"), "forgot password page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj37, "submit_btn"), "forgot password page");
		validateRequiredText(retrieveChildKeyValues(jsonObj37, "email_inputfield_txt"), "forgot password page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "email_error_msg"), "login page");

		enterTextByXpath(textboxEmail, getTestData(7, "oneUserChar"));		
		clickOnRequiredText(retrieveChildKeyValues(jsonObj37, "submit_btn"), "forgot password page");
		validateRequiredText(retrieveChildKeyValues(jsonObj37, "email_inputfield_txt"), "forgot password page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "minimum_characters_error_msg"), "login page");

		enterTextByXpath(textboxEmail, getTestData(7, "FiftyUserCharWithSpace"));		
		clickOnRequiredText(retrieveChildKeyValues(jsonObj37, "submit_btn"), "forgot password page");
		validateRequiredText(retrieveChildKeyValues(jsonObj37, "email_inputfield_txt"), "forgot password page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "maximum_characters_error_msg"), "login page");

		enterTextByXpath(textboxEmail, "2*2");		
		clickOnRequiredText(retrieveChildKeyValues(jsonObj37, "submit_btn"), "forgot password page");
		validateRequiredText(retrieveChildKeyValues(jsonObj37, "email_inputfield_txt"), "forgot password page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "valid_email_error_msg"), "login page");


	}

	@Test
	public void chooseLanguage_LanguageBengaliValidation_MyEarnings_History_Payment_ExistingUser() throws UnsupportedEncodingException, FileNotFoundException, IOException, ParseException {

		JSONObject jsonObj1 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "bengaliFileName"), getTestData(16, "common_label"));
		JSONObject jsonObj2 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "bengaliFileName"), getTestData(16, "common_validate_label"));
		JSONObject jsonObj14 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "bengaliFileName"), getTestData(16, "search_result_all"));
		JSONObject jsonObj17 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "bengaliFileName"), getTestData(16, "profile_right_menu"));
		JSONObject jsonObj28 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "bengaliFileName"), getTestData(16, "my_account_earnings"));
		JSONObject jsonObj29 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "bengaliFileName"), getTestData(16, "total_cashback_earned"));
		JSONObject jsonObj30 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "bengaliFileName"), getTestData(16, "earnings_info"));		
		JSONObject jsonObj31 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "bengaliFileName"), getTestData(16, "earnings_referral"));
		JSONObject jsonObj32 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "bengaliFileName"), getTestData(16, "earnings_cashback"));
		JSONObject jsonObj33 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "bengaliFileName"), getTestData(16, "my_earnings"));
		JSONObject jsonObj35 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "bengaliFileName"), getTestData(16, "sign_in"));
		JSONObject jsonObj36 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "bengaliFileName"), getTestData(16, "join_free"));
		JSONObject jsonObj40 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "bengaliFileName"), getTestData(16, "giftcard_wallet_inputfield"));
		JSONObject jsonObj46 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "bengaliFileName"), getTestData(16, "payment"));
		JSONObject jsonObj52 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "bengaliFileName"), getTestData(16, "my_shared_activities"));
		JSONObject jsonObj53 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "bengaliFileName"), getTestData(16, "giftcard_wallet_request_payment"));

		reportStep("chooseLanguage_LanguageBengaliValidation_MyEarnings_History_Payment_ExistingUser started", "INFO");

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo);

		String dropDownPaymentSettings = "(//android.widget.EditText)[1]";
		String textNEFT = "//android.widget.CheckedTextView[@text='Bank Payment (NEFT)']";
		String textPaytmWallet = "//android.widget.CheckedTextView[@text='Paytm Wallet']";
		String textAmazonGiftCard = "//android.widget.CheckedTextView[@text='Amazon Gift Card']";
		String textBoxPaytmMobileNumberPaymentRequestPage = "//android.widget.EditText[@content-desc='et_requestpaymentPaytm_mobile']";
		String textBoxAmazonEmailPaymentRequestPage = "//android.widget.EditText[@content-desc='et_requestpaymentAmazon_email']";
		String linkEarningsInfoPaymentPage = "//android.widget.TextView[@content-desc='txt_payment_earningsInfo']";	
		String textboxName = "//android.widget.EditText[@content-desc='et_signup_fullname']";
		String textboxEmail = "//android.widget.EditText[@content-desc='et_signup_email']|//android.widget.EditText[@content-desc='et_signin_email']|//android.widget.EditText[@content-desc='et_forgotpassword_email']";
		String textboxPwd = "//android.widget.EditText[@content-desc='et_signup_password']|//android.widget.EditText[@content-desc='et_signin_password']";
		String textboxMobileNumber = "//android.widget.EditText[@content-desc='et_signup_mobile']";
		String searchIcon = "(//android.widget.TextView)[2]";
		String textAreaSearchIcon = "//android.widget.EditText[@content-desc='et_search_enterValue']";

		String cbStoreNameOne = CashKaroUtility.extractValuesFromStoreCategoryJsonAndLoadTheVariablesForStore("CB_Store_One", 0, "CB_Store_One_Name");
		String linkURL = CashKaroUtility.extractValuesFromStoreCategoryJsonAndLoadTheVariablesForStore("CB_Store_One", 0, "CB_Store_One_linkURL");

		String emailAddress = objCashKaroUtility.generateEmail();
		String mobileNumber = objCashKaroUtility.generateMobileNumber();	

		String emailAddressReferralJoinee = objCashKaroUtility.generateEmail();
		String mobileNumberReferralJoinee = objCashKaroUtility.generateMobileNumber();	

		String otpValue = new EKOnboardingPage(driver, testInfo).
				clickOnSignUpButton().
				enterFullName().
				enterEmail(emailAddress).
				enterPassword().
				enterMobileNumber(mobileNumber).
				clickOnGetOTPButton(mobileNumber);

		new OTPPage(driver, testInfo).
		enterOTP(otpValue).
		clickOnVerifyOTP();

		new HomePage(driver, testInfo).		
		clickOnProfileIconForSignedUser().
		clickOnChooseLanguage().
		clickBengaliLink().
		clickContinueButton().
		clickRelaunchButton();

		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "profile_tab"), "home page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj17, "logout_menu"), "profile page");
		validateRequiredText(retrieveChildKeyValues(jsonObj17, "alert_logout"), "logout popup");
		validateRequiredText(retrieveChildKeyValues(jsonObj17, "alert_logout_msg"), "logout popup");
		validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj17, "alert_cancel_btn"), "logout popup");
		clickOnPartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj17, "alert_ok_btn"), "logout popup");

		new EKOnboardingPage(driver, testInfo);

		CashKaroUtility cu = new CashKaroUtility(udid, port, automationName, deviceName);
		driver = cu.launchChromeWebView(driver);

		AdminCoreFunction adminFunctions = new AdminCoreFunction(driver, testInfo);
		adminFunctions.naviagateAdminPage_ThenSelectEarnKaro();
		adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
		adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
		adminFunctions.clickSubmit();

		AdminUsersPage usersPage = new AdminUsersPage(driver, testInfo);
		adminFunctions.clickOnHamburgerMenu();
		adminFunctions.clickUsersMenu();
		adminFunctions.clickUsersSubMenu();
		usersPage.selectSearchByDropDown("User Email");
		usersPage.enterKeyword(emailAddress);
		usersPage.clickSubmitButton();
		String userId = usersPage.extractUserIdValueFromResultstableFirstRow();
		String userName = usersPage.extractUserNameValueFromResultstableFirstRow();

		CashKaroUtility reopenCKApp1 = new CashKaroUtility(udid, port, automationName, deviceName, systemPort);
		driver = reopenCKApp1.launchEarnKaroApp(driver);

		CashKaroUtility objcashKaroutility = new CashKaroUtility(udid, port, automationName, deviceName);
		AppiumDriver<MobileElement> driver2 = objcashKaroutility.launchChrome();
		driver = driver2;

		String url = Utilities.referralLinkGetCode(userId, userName);

		objcashKaroutility.closeAllTabsAndOneTabInChromeBrowser(driver);
		objcashKaroutility.clickAndEnterURLInSearchOrTypeWebAddress(driver, url);

		objcashKaroutility.clickJoinFreeLink(driver);

		enterTextByXpath(textboxName, "ReferralJoinee");
		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(textboxEmail);
		clickOnNoneOFTheAbove();
		enterTextByXpath(textboxEmail, emailAddressReferralJoinee);
		enterTextByXpath(textboxPwd, "ppounds");
		enterTextByXpath(textboxMobileNumber, mobileNumberReferralJoinee);
		clickOnRequiredText(retrieveChildKeyValues(jsonObj36, "otp_btn"), "join free page");	

		new OTPPage(driver, testInfo);

		String otp = Utilities.getSignUPOTP(mobileNumberReferralJoinee);

		new OTPPage(driver, testInfo).
		enterOTP(otp);

		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "verify_otp"), "otp page");	

		sleep(15000);

		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(searchIcon);
		hideKeyboard();
		//validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj13, "search_inputfield_txt"), "search page");
		enterTextByXpath(textAreaSearchIcon, cbStoreNameOne);
		pressEnter();
		validateRequiredText(retrieveChildKeyValues(jsonObj14, "offer_count_single_message"), "search page");
		validateRequiredText(retrieveChildKeyValues(jsonObj1, "add_to_share"), "search page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "share_now"), "search page");

		WhatsAppUtility whatsAppUtility = new WhatsAppUtility(driver, testInfo, udid, port, automationName, deviceName, systemPort);
		whatsAppUtility.clickOnWhatAppMenuItemSearch().
		enterWhatsAppSearchText("Mallik").
		clickOnRequiredContact().
		clickOnSend().
		clickOnSend().
		clickOnLastSharedLink();

		new IntermediateRetailerPage(driver, testInfo).
		validateRetailerPage(linkURL, linkURL);

		CashKaroUtility cu2 = new CashKaroUtility(udid, port, automationName, deviceName);
		cu = cu2;
		driver = cu.launchChromeWebView(driver);

		AdminCoreFunction adminFunctions2 = new AdminCoreFunction(driver, testInfo);
		adminFunctions = adminFunctions2;
		adminFunctions.naviagateAdminPage_ThenSelectEarnKaro();
		adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
		adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
		adminFunctions.clickSubmit();

		AdminReportsPage reportsPage = new AdminReportsPage(driver, testInfo);
		adminFunctions.clickOnHamburgerMenu();
		adminFunctions.clickOnReports();
		reportsPage.clickExitClickSubMenu();
		reportsPage.clickClear();
		reportsPage.selectSearchByDropDown("User Email");
		reportsPage.enterKeyword(emailAddressReferralJoinee);
		reportsPage.clickSubmit();

		String exitClickOne = reportsPage.extractRequiredExitIdUsingStoreNameExitClickType(cbStoreNameOne, getTestData(7, "Normal"));

		AdminCashbackPage ProfitPage = new AdminCashbackPage(driver, testInfo);
		adminFunctions.clickOnHamburgerMenu();
		adminFunctions.clickCashbackMenu();
		ProfitPage.clickCashbackSubMenu();

		ProfitPage.clickAddNewButton();
		ProfitPage.enterExitId(exitClickOne);
		ProfitPage.entertransactionId(exitClickOne);
		ProfitPage.enterOrderId(exitClickOne);
		ProfitPage.setOrderDate();
		ProfitPage.enterOrderValue("1000");
		ProfitPage.enterconfirmCommisionNetwork(getTestData(11, "EightHundered"));
		ProfitPage.entercashback("700");
		ProfitPage.selectCashbackTypeFromDropDown(getTestData(11, "Cashback"));
		ProfitPage.selectCashbackStatusFromDropDown(getTestData(11, "Pending"));
		ProfitPage.setPendingDate();
		ProfitPage.enterDetails(exitClickOne);
		ProfitPage.clickSubmit();
		ProfitPage.clickClear();

		CashKaroUtility reopenCKAppAgain = new CashKaroUtility(udid, port, automationName, deviceName, systemPort);
		driver = reopenCKAppAgain.launchEarnKaroApp(driver);
		new HomePage(driver, testInfo);

		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "profile_tab"), "home page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj17, "history_menu"), "profile page");
		validateRequiredText(retrieveChildKeyValues(jsonObj1, "recent_clicks_tab"), "history page");
		validateRequiredText(retrieveChildKeyValues(jsonObj52, "title"), "history page");
		validateRequiredText(retrieveChildKeyValues(jsonObj52, "shared_link_details"), "history page");
		validateRequiredText(retrieveChildKeyValues(jsonObj52, "shared_id"), "history page");
		validateRequiredText(retrieveChildKeyValues(jsonObj52, "shared_date_time"), "history page");
		validateRequiredText(retrieveChildKeyValues(jsonObj52, "retailer_name"), "history page");
		validateRequiredText(retrieveChildKeyValues(jsonObj52, "no_of_clicks"), "history page");
		validateRequiredText(retrieveChildKeyValues(jsonObj52, "no_of_transactions"), "history page");
		validateRequiredText(retrieveChildKeyValues(jsonObj52, "total_earning"), "history page");

		backButton();

		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "profile_tab"), "home page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj17, "my_earnings_menu"), "profile page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj29, "total_cashback_earned_txt"), "my earnings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj29, "total_cashback_earned_txt"), 1, "profit page");
		validateRequiredText(retrieveChildKeyValues(jsonObj29, "total_cashback_earned_txt"), 2, "profit page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj29, "paid_cashback_txt"), "profit page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj29, "pending_cashback_txt"), "profit page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj29, "referral_cashback_txt"), "profit page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj29, "available_cashback_txt"), "profit page");

		backButton();

		validateRequiredText(retrieveChildKeyValues(jsonObj32, "order_details"), "my earnings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj32, "date"), "my earnings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj32, "retailer"), "my earnings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj32, "order_amount"), "my earnings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj32, "cashback_amount"), "my earnings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj32, "status"), "my earnings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj32, "expected_date"), "my earnings page");

		backButton();

		clickOnRequiredText(retrieveChildKeyValues(jsonObj17, "logout_menu"), "profile page");
		validateRequiredText(retrieveChildKeyValues(jsonObj17, "alert_logout"), "logout popup");
		validateRequiredText(retrieveChildKeyValues(jsonObj17, "alert_logout_msg"), "logout popup");
		validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj17, "alert_cancel_btn"), "logout popup");
		clickOnPartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj17, "alert_ok_btn"), "logout popup");

		new EKOnboardingPage(driver, testInfo);

		CashKaroUtility cu3 = new CashKaroUtility(udid, port, automationName, deviceName);
		cu = cu3;
		driver = cu.launchChromeWebView(driver);

		AdminCoreFunction adminFunctions3 = new AdminCoreFunction(driver, testInfo);
		adminFunctions = adminFunctions3;
		adminFunctions.naviagateAdminPage_ThenSelectEarnKaro();
		adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
		adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
		adminFunctions.clickSubmit();

		AdminCashbackPage ProfitPage1 = new AdminCashbackPage(driver, testInfo);
		ProfitPage = ProfitPage1;
		adminFunctions.clickOnHamburgerMenu();
		adminFunctions.clickCashbackMenu();
		ProfitPage.clickCashbackSubMenu();

		ProfitPage.selectSearchByStatusFromDropDown("Exit ID");
		ProfitPage.enterKeyword(exitClickOne);
		ProfitPage.selectCashbackStatusFromDropDown(getTestData(11, "Pending"));
		ProfitPage.clickSubmit();
		ProfitPage.clickEdit();
		ProfitPage.selectCashbackStatusFromDropDown(getTestData(11, "Confirmed"));
		ProfitPage.setConfirmDate();
		ProfitPage.clickSubmit();
		ProfitPage.clickClear();

		CashKaroUtility reopenCKAppOnceAgain = new CashKaroUtility(udid, port, automationName, deviceName, systemPort);
		driver = reopenCKAppOnceAgain.launchEarnKaroApp(driver);

		new EKOnboardingPage(driver, testInfo).clickOnSignInbutton();

		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(textboxEmail);
		clickOnNoneOFTheAbove();
		enterTextByXpath(textboxEmail, emailAddress);
		enterTextByXpath(textboxPwd, "ppounds");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj35, "sign_with_email_btn"), "login page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "profile_tab"), "home page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj17, "my_earnings_menu"), "profile page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj33, "referral_tap"), "my earnings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj31, "order_details"), "my earnings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj31, "date"), "my earnings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj31, "referral_name"), "my earnings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj31, "cashback"), "my earnings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj31, "status"), "my earnings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj33, "refer_now_btn"), "my earnings page");

		backButton();

		clickOnRequiredText(retrieveChildKeyValues(jsonObj17, "logout_menu"), "profile page");
		validateRequiredText(retrieveChildKeyValues(jsonObj17, "alert_logout"), "logout popup");
		validateRequiredText(retrieveChildKeyValues(jsonObj17, "alert_logout_msg"), "logout popup");
		validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj17, "alert_cancel_btn"), "logout popup");
		clickOnPartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj17, "alert_ok_btn"), "logout popup");

		new EKOnboardingPage(driver, testInfo).clickOnSignInbutton();

		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(textboxEmail);
		clickOnNoneOFTheAbove();
		enterTextByXpath(textboxEmail, emailAddressReferralJoinee);
		enterTextByXpath(textboxPwd, "ppounds");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj35, "sign_with_email_btn"), "login page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "profile_tab"), "home page");
		validateRequiredText(retrieveChildKeyValues(jsonObj1, "profile_tab"), "profile page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj17, "hello_user_txt"), "profile page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj17, "total_cashback_txt"), "profile page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj17, "payment_menu"), "profile page");

		validateRequiredText(retrieveChildKeyValues(jsonObj17, "payment_menu"), "profile page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj29, "total_cashback_earned_txt"), "payments page");
		validateRequiredText(retrieveChildKeyValues(jsonObj29, "total_cashback_earned_txt"), 1, "profit page");
		validateRequiredText(retrieveChildKeyValues(jsonObj29, "total_cashback_earned_txt"), 2, "profit page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj29, "paid_cashback_txt"), "profit page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj29, "pending_cashback_txt"), "profit page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj29, "referral_cashback_txt"), "profit page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj29, "available_cashback_txt"), "profit page");

		backButton();

		validatePartialRequiredText(retrieveChildKeyValues(jsonObj28, "tap_to_see_details_txt"), "payments page");
		validateRequiredText(retrieveChildKeyValues(jsonObj46, "cashback_earnings"), "payments page");
		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(linkEarningsInfoPaymentPage);
		validateRequiredText(retrieveChildKeyValues(jsonObj30, "message_first_txt"), "earnings info page");


		backButton();

		validateRequiredText(retrieveChildKeyValues(jsonObj46, "request_cashback_btn"), "payments page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj46, "static_text_title"), "payments page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj46, "static_text_1"), "payments page");


		clickOnRequiredText(retrieveChildKeyValues(jsonObj46, "request_cashback_btn"), "payments page");

		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(dropDownPaymentSettings);
		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(textPaytmWallet);
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "mobile_inputfield_txt"), "payment request page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "get_paid_btn"), "payment req page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "mobile_inputfield_txt"), "payment request page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "mobile_number_error_msg"), "payment request page");

		enterTextByXpath(textBoxPaytmMobileNumberPaymentRequestPage, "1");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "get_paid_btn"), "payment req page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "mobile_inputfield_txt"), "payment request page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "minimum_digits_error_msg"), "payment request page");

		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(dropDownPaymentSettings);
		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(textAmazonGiftCard);

		validateRequiredText(retrieveChildKeyValues(jsonObj53, "email_inputfield_txt"), "payment request page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "get_paid_btn"), "payment req page");
		validateRequiredText(retrieveChildKeyValues(jsonObj53, "email_inputfield_txt"), "payment request page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "email_error_msg"), "payment request page");

		enterTextByXpath(textBoxAmazonEmailPaymentRequestPage, "1");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "get_paid_btn"), "payment req page");
		validateRequiredText(retrieveChildKeyValues(jsonObj53, "email_inputfield_txt"), "payment request page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "minimum_characters_error_msg"), "payment request page");

		enterTextByXpath(textBoxAmazonEmailPaymentRequestPage, "asdfghjkl");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "get_paid_btn"), "payment req page");
		validateRequiredText(retrieveChildKeyValues(jsonObj53, "email_inputfield_txt"), "payment request page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "valid_email_error_msg"), "payment request page");

		enterTextByXpath(textBoxAmazonEmailPaymentRequestPage, getTestData(7, "FiftyUserCharWithSpace"));
		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "get_paid_btn"), "payment req page");
		validateRequiredText(retrieveChildKeyValues(jsonObj53, "email_inputfield_txt"), "payment request page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "maximum_characters_error_msg"), "payment request page");

		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(dropDownPaymentSettings);
		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(textNEFT);

		validateRequiredText(retrieveChildKeyValues(jsonObj40, "bank_account_inputfield_txt"), "payment request page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "bank_name_inputfield_txt"), "payment request page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "branch_name_inputfield_txt"), "payment request page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "bank_account_number_inputfield_txt"), "payment request page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "bank_ifsc_code_inputfield_txt"), "payment request page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "get_paid_btn"), "payment req page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "bank_account_inputfield_txt"), "payment request page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "account_holder_error_msg"), "payment request page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "bank_name_inputfield_txt"), "payment request page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "valid_bank_name_error_msg"), "payment request page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "branch_name_inputfield_txt"), "payment request page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "branch_minimum_digits_error_msg"), "payment request page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "bank_account_number_inputfield_txt"), "payment request page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "account_number_error_msg"), "payment request page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "bank_ifsc_code_inputfield_txt"), "payment request page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "ifsc_code_error_msg"), "payment request page");


	}

	@Test
	public void chooseLanguage_LanguageBengaliValidation_StoreCategory_StoreDetail() throws UnsupportedEncodingException, FileNotFoundException, IOException, ParseException {

		JSONObject jsonObj1 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "bengaliFileName"), getTestData(16, "common_label"));
		JSONObject jsonObj3 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "bengaliFileName"), getTestData(16, "main_menu"));
		JSONObject jsonObj5 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "bengaliFileName"), getTestData(16, "store_category"));
		JSONObject jsonObj12 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "bengaliFileName"), getTestData(16, "share_counts"));
		JSONObject jsonObj17 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "bengaliFileName"), getTestData(16, "profile_right_menu"));

		reportStep("chooseLanguage_LanguageBengaliValidation_Store_Product_StoreCategory started", "INFO");

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo);

		String shareButtonWithCount = "//android.view.ViewGroup[@content-desc='txt_sharecount_open_modal']/android.view.ViewGroup/android.widget.TextView[@text='1']";
		String imageStoreCard = "//android.widget.ImageView[@content-desc='img_storeCard_imageValue']";
		String emailAddress = objCashKaroUtility.generateEmail();
		String mobileNumber = objCashKaroUtility.generateMobileNumber();	

		String otpValue = new EKOnboardingPage(driver, testInfo).
				clickOnSignUpButton().
				enterFullName().
				enterEmail(emailAddress).
				enterPassword().
				enterMobileNumber(mobileNumber).
				clickOnGetOTPButton(mobileNumber);

		new OTPPage(driver, testInfo).
		enterOTP(otpValue).
		clickOnVerifyOTP();

		new HomePage(driver, testInfo).		
		clickOnProfileIconForSignedUser().
		clickOnChooseLanguage().
		clickBengaliLink().
		clickContinueButton().
		clickRelaunchButton();

		validateRequiredText(retrieveChildKeyValues(jsonObj3, "home_lbl"), "home page");

		new HomePage(driver, testInfo).
		clickOnHamburgerIcon();

		validateRequiredText(retrieveChildKeyValues(jsonObj3, "home_lbl"), "home page");

		new SliderScreenPage(driver, testInfo).
		clickRetailerCategory().
		verifyRetailerCategoryMenuNavigation().
		clickAutomationSpecificCategoryInRetailerCategoryMenuUntilAllSubCategoryVisible().
		clickAutomationSpecificSubCategoryOne();

		validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj5, "popular"), "store category page");
		validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj5, "az"), "store category page");
		validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj5, "percent"), "store category page");
		validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj5, "amount"), "store category page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "add_to_share"), "store category page");
		validateRequiredText(retrieveChildKeyValues(jsonObj1, "share_now"), "store category page");
		validateRequiredText(retrieveChildKeyValues(jsonObj1, "remove"), "store category page");

		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(shareButtonWithCount);
		validateRequiredText(retrieveChildKeyValues(jsonObj1, "share_now"), "store category page");
		validateRequiredText(retrieveChildKeyValues(jsonObj1, "copy_Link"), "store category page");
		validateRequiredText(retrieveChildKeyValues(jsonObj12, "close"), "store category page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj12, "clear"), "store category page");

		validateRequiredText(retrieveChildKeyValues(jsonObj12, "clear"), "store category page");
		validateRequiredText(retrieveChildKeyValues(jsonObj12, "confirm_msg_clear_share"), "store category page");
		validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj17, "alert_cancel_btn"), "store category page");
		clickOnPartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj17, "alert_ok_btn"), "store category page");

		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(imageStoreCard);
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj1, "you_will_earn"), "store detail page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "add_to_share"), "store detail page");
		validateRequiredText(retrieveChildKeyValues(jsonObj1, "share_now"), "store detail page");
		validateRequiredText(retrieveChildKeyValues(jsonObj1, "remove"), "store category page");

		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(shareButtonWithCount);
		validateRequiredText(retrieveChildKeyValues(jsonObj1, "share_now"), "store detail page");
		validateRequiredText(retrieveChildKeyValues(jsonObj1, "copy_Link"), "store detail page");
		validateRequiredText(retrieveChildKeyValues(jsonObj12, "close"), "store detail page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj12, "clear"), "store detail page");

		validateRequiredText(retrieveChildKeyValues(jsonObj12, "clear"), "store detail page");
		validateRequiredText(retrieveChildKeyValues(jsonObj12, "confirm_msg_clear_share"), "store detail page");
		validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj17, "alert_cancel_btn"), "store detail page");
		clickOnPartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj17, "alert_ok_btn"), "store detail page");

		new StoreDetailPage(driver, testInfo);

	}

	@Test
	public void chooseLanguage_LanguageBengaliValidation_Product() throws UnsupportedEncodingException, FileNotFoundException, IOException, ParseException {

		JSONObject jsonObj1 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "bengaliFileName"), getTestData(16, "common_label"));
		JSONObject jsonObj3 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "bengaliFileName"), getTestData(16, "main_menu"));
		JSONObject jsonObj6 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "bengaliFileName"), getTestData(16, "prduct_category"));
		JSONObject jsonObj9 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "bengaliFileName"), getTestData(16, "product_details"));
		JSONObject jsonObj10 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "bengaliFileName"), getTestData(16, "product_card"));
		JSONObject jsonObj49 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "bengaliFileName"), getTestData(16, "prduct_filter"));

		reportStep("chooseLanguage_LanguageBengaliValidation_Store_Product_StoreCategory started", "INFO");

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo);

		String menFashionCategory = "//android.view.ViewGroup[@content-desc='txt_mainMenu_productDealsTop']";
		String tshirtCategory = "//android.widget.TextView[@content-desc='txt_subMenuProduct_mainMenuName']";
		String menfashionLink = "//android.widget.TextView[@content-desc='txt_filter_category_name']";
		String imageProductCard = "//android.widget.ImageView[@content-desc='img_product_image']";
		String productcard = "(//android.widget.ImageView)[3]";		
		String emailAddress = objCashKaroUtility.generateEmail();
		String mobileNumber = objCashKaroUtility.generateMobileNumber();	

		String otpValue = new EKOnboardingPage(driver, testInfo).
				clickOnSignUpButton().
				enterFullName().
				enterEmail(emailAddress).
				enterPassword().
				enterMobileNumber(mobileNumber).
				clickOnGetOTPButton(mobileNumber);

		new OTPPage(driver, testInfo).
		enterOTP(otpValue).
		clickOnVerifyOTP();

		new HomePage(driver, testInfo).		
		clickOnProfileIconForSignedUser().
		clickOnChooseLanguage().
		clickBengaliLink().
		clickContinueButton().
		clickRelaunchButton();

		validateRequiredText(retrieveChildKeyValues(jsonObj3, "home_lbl"), "home page");

		new HomePage(driver, testInfo).
		clickOnHamburgerIcon();

		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(menFashionCategory);
		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(tshirtCategory);

		clickOnPartialRequiredText(retrieveChildKeyValues(jsonObj6, "filter"), "product page");
		validateRequiredText(retrieveChildKeyValues(jsonObj49, "filter_by"), "product page");
		validateRequiredText(retrieveChildKeyValues(jsonObj49, "clerar_all"), "product page");
		validateRequiredText(retrieveChildKeyValues(jsonObj49, "close_btn"), "product page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj49, "apply_btn"), "product page");

		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(menfashionLink);
		clickOnRequiredText(retrieveChildKeyValues(jsonObj49, "clerar_all"), "product page");
		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(menfashionLink);
		clickOnRequiredText(retrieveChildKeyValues(jsonObj49, "close_btn"), "product page");
		validateRequiredText(retrieveChildKeyValues(jsonObj49, "discard_changes_title"), "product page");
		validateRequiredText(retrieveChildKeyValues(jsonObj49, "discard_changes_msg"), "product page");
		validateRequiredText(retrieveChildKeyValues(jsonObj49, "apply_changes_btn"), "product page");
		validateRequiredText(retrieveChildKeyValues(jsonObj49, "discard_changes_btn"), "product page");

		backButton();

		clickOnPartialRequiredText(retrieveChildKeyValues(jsonObj6, "sort"), "product page");
		validateRequiredText(retrieveChildKeyValues(jsonObj6, "sort_title"), "product page");
		validateRequiredText(retrieveChildKeyValues(jsonObj6, "sort_popular"), "product page");
		validateRequiredText(retrieveChildKeyValues(jsonObj6, "sort_discount"), "product page");
		validateRequiredText(retrieveChildKeyValues(jsonObj6, "sort_high_price"), "product page");
		validateRequiredText(retrieveChildKeyValues(jsonObj6, "sort_low_price"), "product page");

		backButton();

		validatePartialRequiredText(retrieveChildKeyValues(jsonObj10, "brand"), "product page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj10, "price"), "product page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj10, "sale"), "product page");
		validateRequiredText(retrieveChildKeyValues(jsonObj1, "add_to_share"), "product page");
		validateRequiredText(retrieveChildKeyValues(jsonObj1, "share_now"), "product page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj10, "see_details"), "product page");

		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(imageProductCard);
		clickOnRequiredText(retrieveChildKeyValues(jsonObj9, "seller_price"), "product detail page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "you_will_earn"), "product detail page");
		validateRequiredText(retrieveChildKeyValues(jsonObj1, "add_to_share"), "product page");
		validateRequiredText(retrieveChildKeyValues(jsonObj1, "share_now"), "product page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj9, "product_highlights"), "product detail page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj9, "similar_products"), "product detail page");



	}

	@Test
	public void chooseLanguage_LanguageMararthiValidation_SuccessStories() throws UnsupportedEncodingException, FileNotFoundException, IOException, ParseException {

		JSONObject jsonObj1 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "marathiFileName"), getTestData(16, "common_label"));
		JSONObject jsonObj4 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "marathiFileName"), getTestData(16, "home"));
		JSONObject jsonObj17 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "marathiFileName"), getTestData(16, "profile_right_menu"));
		JSONObject jsonObj22 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "marathiFileName"), getTestData(16, "success_stories"));

		reportStep("chooseLanguage_LanguageMararthiValidation_SuccessStories started", "INFO");

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo);

		String emailAddress = objCashKaroUtility.generateEmail();
		String mobileNumber = objCashKaroUtility.generateMobileNumber();	

		String otpValue = new EKOnboardingPage(driver, testInfo).
				clickOnSignUpButton().
				enterFullName().
				enterEmail(emailAddress).
				enterPassword().
				enterMobileNumber(mobileNumber).
				clickOnGetOTPButton(mobileNumber);

		new OTPPage(driver, testInfo).
		enterOTP(otpValue).
		clickOnVerifyOTP();

		new HomePage(driver, testInfo).		
		clickOnProfileIconForSignedUser().
		clickOnChooseLanguage().
		clickMarathiLink().
		clickContinueButton().
		clickRelaunchButton();

		validateRequiredText(retrieveChildKeyValues(jsonObj4, "dynamic_home_top_Category"), "home page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "profile_tab"), "home page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj17, "success_stories_menu"), "profile page");
		validateRequiredText(retrieveChildKeyValues(jsonObj17, "success_stories_menu"), "success stories page");
		scrollFromUpToDowninApp();scrollFromUpToDowninApp();
		validateRequiredText(retrieveChildKeyValues(jsonObj22, "title"), "success stories page");
		validateRequiredText(retrieveChildKeyValues(jsonObj22, "testimonial_txt1"), "success stories page");
		validateRequiredText(retrieveChildKeyValues(jsonObj22, "testimonial_name1"), "success stories page");
		validateRequiredText(retrieveChildKeyValues(jsonObj22, "testimonial_txt2"), "success stories page");
		validateRequiredText(retrieveChildKeyValues(jsonObj22, "testimonial_name2"), "success stories page");
		validateRequiredText(retrieveChildKeyValues(jsonObj22, "testimonial_txt3"), "success stories page");
		validateRequiredText(retrieveChildKeyValues(jsonObj22, "testimonial_name3"), "success stories page");
		validateRequiredText(retrieveChildKeyValues(jsonObj22, "testimonial_txt4"), "success stories page");
		validateRequiredText(retrieveChildKeyValues(jsonObj22, "testimonial_name4"), "success stories page");
		validateRequiredText(retrieveChildKeyValues(jsonObj22, "testimonial_txt5"), "success stories page");
		validateRequiredText(retrieveChildKeyValues(jsonObj22, "testimonial_name5"), "success stories page");


	}

	@Test
	public void chooseLanguage_LanguageMararthiValidation_HowItWorks() throws UnsupportedEncodingException, FileNotFoundException, IOException, ParseException {

		JSONObject jsonObj1 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "marathiFileName"), getTestData(16, "common_label"));
		JSONObject jsonObj4 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "marathiFileName"), getTestData(16, "home"));
		JSONObject jsonObj17 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "marathiFileName"), getTestData(16, "profile_right_menu"));
		JSONObject jsonObj23 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "marathiFileName"), getTestData(16, "how_it_works"));

		reportStep("chooseLanguage_LanguageMararthiValidation_HowItWorks started", "INFO");

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo);

		String emailAddress = objCashKaroUtility.generateEmail();
		String mobileNumber = objCashKaroUtility.generateMobileNumber();	

		String otpValue = new EKOnboardingPage(driver, testInfo).
				clickOnSignUpButton().
				enterFullName().
				enterEmail(emailAddress).
				enterPassword().
				enterMobileNumber(mobileNumber).
				clickOnGetOTPButton(mobileNumber);

		new OTPPage(driver, testInfo).
		enterOTP(otpValue).
		clickOnVerifyOTP();

		new HomePage(driver, testInfo).		
		clickOnProfileIconForSignedUser().
		clickOnChooseLanguage().
		clickMarathiLink().
		clickContinueButton().
		clickRelaunchButton();

		validateRequiredText(retrieveChildKeyValues(jsonObj4, "dynamic_home_top_Category"), "home page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "profile_tab"), "home page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj17, "how_it_works_menu"), "profile page");
		scrollFromUpToDowninApp();scrollFromUpToDowninApp();
		validateRequiredText(retrieveChildKeyValues(jsonObj17, "how_it_works_menu"), "how earnkaro works? page");
		validateRequiredText(retrieveChildKeyValues(jsonObj23, "watch_video_title1"), "how earnkaro works? page");
		validateRequiredText(retrieveChildKeyValues(jsonObj23, "watch_video_txt"), 1, "how earnkaro works? page");
		validateRequiredText(retrieveChildKeyValues(jsonObj23, "watch_video_title2"), "how earnkaro works? page");
		validateRequiredText(retrieveChildKeyValues(jsonObj23, "watch_video_txt"), 2, "how earnkaro works? page");
		validateRequiredText(retrieveChildKeyValues(jsonObj23, "watch_video_title3"), "how earnkaro works? page");
		validateRequiredText(retrieveChildKeyValues(jsonObj23, "watch_video_txt"), 3, "how earnkaro works? page");
		validateRequiredText(retrieveChildKeyValues(jsonObj23, "watch_video_title4"), "how earnkaro works? page");
		validateRequiredText(retrieveChildKeyValues(jsonObj23, "watch_video_txt"), 4, "how earnkaro works? page");
		validateRequiredText(retrieveChildKeyValues(jsonObj23, "watch_video_title5"), "how earnkaro works? page");
		validateRequiredText(retrieveChildKeyValues(jsonObj23, "watch_video_txt"), 5, "how earnkaro works? page");
		validateRequiredText(retrieveChildKeyValues(jsonObj23, "watch_video_title6"), "how earnkaro works? page");
		validateRequiredText(retrieveChildKeyValues(jsonObj23, "watch_video_txt"), 6, "how earnkaro works? page");



	}

	@Test
	public void chooseLanguage_LanguageMararthiValidation_Testimonials() throws UnsupportedEncodingException, FileNotFoundException, IOException, ParseException {

		JSONObject jsonObj1 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "marathiFileName"), getTestData(16, "common_label"));
		JSONObject jsonObj4 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "marathiFileName"), getTestData(16, "home"));
		JSONObject jsonObj17 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "marathiFileName"), getTestData(16, "profile_right_menu"));
		JSONObject jsonObj24 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "marathiFileName"), getTestData(16, "testimonial"));
		JSONObject jsonObj25 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "marathiFileName"), getTestData(16, "testimonial_write"));

		reportStep("chooseLanguage_LanguageMararthiValidation_Testimonials started", "INFO");

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo);

		String fourStarTestimonials = "(//android.widget.TextView)[8]";
		String threeStarTestimonials = "(//android.widget.TextView)[7]";
		String twoStarTestimonials = "(//android.widget.TextView)[6]";
		String oneStarTestimonials = "(//android.widget.TextView)[5]";

		String emailAddress = objCashKaroUtility.generateEmail();
		String mobileNumber = objCashKaroUtility.generateMobileNumber();	

		String otpValue = new EKOnboardingPage(driver, testInfo).
				clickOnSignUpButton().
				enterFullName().
				enterEmail(emailAddress).
				enterPassword().
				enterMobileNumber(mobileNumber).
				clickOnGetOTPButton(mobileNumber);

		new OTPPage(driver, testInfo).
		enterOTP(otpValue).
		clickOnVerifyOTP();

		new HomePage(driver, testInfo).		
		clickOnProfileIconForSignedUser().
		clickOnChooseLanguage().
		clickMarathiLink().
		clickContinueButton().
		clickRelaunchButton();

		validateRequiredText(retrieveChildKeyValues(jsonObj4, "dynamic_home_top_Category"), "home page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "profile_tab"), "home page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj17, "rate_us_menu"), "profile page");
		validateRequiredText(retrieveChildKeyValues(jsonObj24, "count_txt"), "testimonials page");
		validateRequiredText(retrieveChildKeyValues(jsonObj24, "happy_users_txt"), "testimonials page");

		clickOnPartialRequiredText(retrieveChildKeyValues(jsonObj24, "write_testimonial_btn"), "testimonials page");
		validateRequiredText(retrieveChildKeyValues(jsonObj24, "write_testimonial_btn"), "write testimonials page");
		validateRequiredText(retrieveChildKeyValues(jsonObj25, "title"), "write testimonials page");

		validateRequiredText(retrieveChildKeyValues(jsonObj25, "rate_experience_txt"), "write testimonials page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj25, "star_txt_awesome_experience"), "write testimonials page");
		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(fourStarTestimonials);
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj25, "star_txt_good_experience"), "write testimonials page");
		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(threeStarTestimonials);
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj25, "star_txt_average"), "write testimonials page");
		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(twoStarTestimonials);
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj25, "star_txt_not_great"), "write testimonials page");
		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(oneStarTestimonials);
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj25, "star_txt_terrible"), "write testimonials page");


	}

	@Test
	public void chooseLanguage_LanguageMararthiValidation_ContactUs() throws UnsupportedEncodingException, FileNotFoundException, IOException, ParseException {

		JSONObject jsonObj1 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "marathiFileName"), getTestData(16, "common_label"));
		JSONObject jsonObj2 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "marathiFileName"), getTestData(16, "common_validate_label"));
		JSONObject jsonObj4 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "marathiFileName"), getTestData(16, "home"));
		JSONObject jsonObj17 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "marathiFileName"), getTestData(16, "profile_right_menu"));
		JSONObject jsonObj20 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "marathiFileName"), getTestData(16, "contact_us"));		
		JSONObject jsonObj21 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "marathiFileName"), getTestData(16, "faq"));

		reportStep("chooseLanguage_LanguageMararthiValidation_ContactUs started", "INFO");

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo);

		String textBoxUserName = "//android.widget.EditText[@content-desc='et_contactUs_yourName']";
		String textBoxEmail = "//android.widget.EditText[@content-desc='et_contactUs_email']";

		String emailAddress = objCashKaroUtility.generateEmail();
		String mobileNumber = objCashKaroUtility.generateMobileNumber();	

		String otpValue = new EKOnboardingPage(driver, testInfo).
				clickOnSignUpButton().
				enterFullName().
				enterEmail(emailAddress).
				enterPassword().
				enterMobileNumber(mobileNumber).
				clickOnGetOTPButton(mobileNumber);

		new OTPPage(driver, testInfo).
		enterOTP(otpValue).
		clickOnVerifyOTP();

		new HomePage(driver, testInfo).		
		clickOnProfileIconForSignedUser().
		clickOnChooseLanguage().
		clickMarathiLink().
		clickContinueButton().
		clickRelaunchButton();

		validateRequiredText(retrieveChildKeyValues(jsonObj4, "dynamic_home_top_Category"), "home page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "profile_tab"), "home page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj17, "help"), "profile page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj17, "contact_us_menu"), "help page");
		enterTextByXpath(textBoxUserName, "..");
		enterTextByXpath(textBoxEmail, "..");
		validateRequiredText(retrieveChildKeyValues(jsonObj21, "faq_btn"), "contact us page");
		validateRequiredText(retrieveChildKeyValues(jsonObj20, "name_inputfield_txt"), "contact us page");
		validateRequiredText(retrieveChildKeyValues(jsonObj20, "email_inputfield_txt"), "contact us page");
		validateRequiredText(retrieveChildKeyValues(jsonObj20, "message_inputfield_txt"), "contact us page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj20, "submit_btn"), "contact us page");

		validateRequiredText(retrieveChildKeyValues(jsonObj2, "select_subject_error_msg"), "contact us page");
		validateRequiredText(retrieveChildKeyValues(jsonObj20, "name_inputfield_txt"), "contact us page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "valid_name_error_msg"), "contact us page");
		validateRequiredText(retrieveChildKeyValues(jsonObj20, "email_inputfield_txt"), "contact us page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "valid_email_error_msg"), "contact us page");
		validateRequiredText(retrieveChildKeyValues(jsonObj20, "message_inputfield_txt"), "contact us page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "valid_details_error_msg"), "contact us page");

		enterTextByXpath(textBoxUserName, getTestData(7, "oneUserChar"));
		enterTextByXpath(textBoxEmail, getTestData(7, "oneUserChar"));
		clickOnRequiredText(retrieveChildKeyValues(jsonObj20, "submit_btn"), "contact us page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "select_subject_error_msg"), "contact us page");
		validateRequiredText(retrieveChildKeyValues(jsonObj20, "name_inputfield_txt"), "contact us page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "minimum_characters_error_msg"), 1, "contact us page");
		validateRequiredText(retrieveChildKeyValues(jsonObj20, "email_inputfield_txt"), "contact us page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "minimum_characters_error_msg"), 2, "contact us page");
		validateRequiredText(retrieveChildKeyValues(jsonObj20, "message_inputfield_txt"), "contact us page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "valid_details_error_msg"), "contact us page");

		enterTextByXpath(textBoxUserName, getTestData(7, "FiftyPlusUserChar"));
		enterTextByXpath(textBoxEmail, getTestData(7, "FiftyPlusUserChar"));
		clickOnRequiredText(retrieveChildKeyValues(jsonObj20, "submit_btn"), "contact us page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "select_subject_error_msg"), "contact us page");
		validateRequiredText(retrieveChildKeyValues(jsonObj20, "name_inputfield_txt"), "contact us page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "maximum_characters_error_msg"), 1, "contact us page");
		validateRequiredText(retrieveChildKeyValues(jsonObj20, "email_inputfield_txt"), "contact us page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "maximum_characters_error_msg"), 2, "contact us page");
		validateRequiredText(retrieveChildKeyValues(jsonObj20, "message_inputfield_txt"), "contact us page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "valid_details_error_msg"), "contact us page");

	}

	@Test
	public void chooseLanguage_LanguageMararthiValidation_FAQs() throws UnsupportedEncodingException, FileNotFoundException, IOException, ParseException {

		JSONObject jsonObj1 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "marathiFileName"), getTestData(16, "common_label"));
		JSONObject jsonObj4 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "marathiFileName"), getTestData(16, "home"));
		JSONObject jsonObj17 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "marathiFileName"), getTestData(16, "profile_right_menu"));		
		JSONObject jsonObj21 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "marathiFileName"), getTestData(16, "faq"));

		reportStep("chooseLanguage_LanguageMararthiValidation_FAQs started", "INFO");

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo);

		String emailAddress = objCashKaroUtility.generateEmail();
		String mobileNumber = objCashKaroUtility.generateMobileNumber();	

		String otpValue = new EKOnboardingPage(driver, testInfo).
				clickOnSignUpButton().
				enterFullName().
				enterEmail(emailAddress).
				enterPassword().
				enterMobileNumber(mobileNumber).
				clickOnGetOTPButton(mobileNumber);

		new OTPPage(driver, testInfo).
		enterOTP(otpValue).
		clickOnVerifyOTP();

		new HomePage(driver, testInfo).		
		clickOnProfileIconForSignedUser().
		clickOnChooseLanguage().
		clickMarathiLink().
		clickContinueButton().
		clickRelaunchButton();

		validateRequiredText(retrieveChildKeyValues(jsonObj4, "dynamic_home_top_Category"), "home page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "profile_tab"), "home page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj17, "help"), "profile page");

		validateRequiredText(retrieveChildKeyValues(jsonObj17, "call_us_menu"), "help page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj17, "faq_menu"), "help page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj21, "question1"), "faq page");
		//validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj21, "answer1"), "faq page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj21, "question2"), "faq page");
		//validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj21, "answer2"), "faq page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj21, "question3"), "faq page");
		//validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj21, "answer3"), "faq page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj21, "question4"), "faq page");
		//validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj21, "answer4"), "faq page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj21, "question5"), "faq page");
		//validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj21, "answer5"), "faq page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj21, "question6"), "faq page");
		//validatePartialRequiredText(retrieveChildKeyValues(jsonObj21, "answer6"), "faq page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj21, "question7"), "faq page");
		//validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj21, "answer7"), "faq page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj21, "question8"), "faq page");
		//validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj21, "answer8"), "faq page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj21, "question9"), "faq page");
		//validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj21, "answer9"), "faq page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj21, "question10"), "faq page");
		//validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj21, "answer10"), "faq page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj21, "question11"), "faq page");
		//validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj21, "answer11"), "faq page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj21, "question12"), "faq page");
		//validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj21, "answer12"), "faq page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj21, "question13"), "faq page");
		//validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj21, "answer13"), "faq page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj21, "question14"), "faq page");
		//validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj21, "answer14"), "faq page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj21, "question15"), "faq page");
		//validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj21, "answer15"), "faq page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj21, "question16"), "faq page");
		//validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj21, "answer16"), "faq page");

		validateRequiredText(retrieveChildKeyValues(jsonObj21, "faq_btn"), "faq page");


	}

	@Test
	public void chooseLanguage_LanguageMararthiValidation_History_NewUser() throws UnsupportedEncodingException, FileNotFoundException, IOException, ParseException {

		JSONObject jsonObj1 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "marathiFileName"), getTestData(16, "common_label"));
		JSONObject jsonObj4 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "marathiFileName"), getTestData(16, "home"));
		JSONObject jsonObj17 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "marathiFileName"), getTestData(16, "profile_right_menu"));
		JSONObject jsonObj52 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "marathiFileName"), getTestData(16, "my_shared_activities"));

		reportStep("chooseLanguage_LanguageMararthiValidation_History_NewUser started", "INFO");

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo);

		String emailAddress = objCashKaroUtility.generateEmail();
		String mobileNumber = objCashKaroUtility.generateMobileNumber();	

		String otpValue = new EKOnboardingPage(driver, testInfo).
				clickOnSignUpButton().
				enterFullName().
				enterEmail(emailAddress).
				enterPassword().
				enterMobileNumber(mobileNumber).
				clickOnGetOTPButton(mobileNumber);

		new OTPPage(driver, testInfo).
		enterOTP(otpValue).
		clickOnVerifyOTP();

		new HomePage(driver, testInfo).		
		clickOnProfileIconForSignedUser().
		clickOnChooseLanguage().
		clickMarathiLink().
		clickContinueButton().
		clickRelaunchButton();

		validateRequiredText(retrieveChildKeyValues(jsonObj4, "dynamic_home_top_Category"), "home page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "profile_tab"), "home page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj17, "history_menu"), "profile page");
		validateRequiredText(retrieveChildKeyValues(jsonObj17, "recent_clicks_menu"), "history page");
		validateRequiredText(retrieveChildKeyValues(jsonObj1, "whoops_msg"), "history page");
		validateRequiredText(retrieveChildKeyValues(jsonObj52, "no_shared_activites"), "history page");
		validateRequiredText(retrieveChildKeyValues(jsonObj52, "saving_click_away"), "history page");


	}

	@Test
	public void chooseLanguage_LanguageMararthiValidation_AccountSettings() throws UnsupportedEncodingException, FileNotFoundException, IOException, ParseException {

		JSONObject jsonObj1 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "marathiFileName"), getTestData(16, "common_label"));
		JSONObject jsonObj2 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "marathiFileName"), getTestData(16, "common_validate_label"));
		JSONObject jsonObj17 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "marathiFileName"), getTestData(16, "profile_right_menu"));
		JSONObject jsonObj26 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "marathiFileName"), getTestData(16, "personal_details"));
		JSONObject jsonObj27 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "marathiFileName"), getTestData(16, "change_password"));
		JSONObject jsonObj40 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "marathiFileName"), getTestData(16, "giftcard_wallet_inputfield"));

		reportStep("chooseLanguage_LanguageMararthiValidation_AccountSettings started", "INFO");

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo);

		String textBoxExistingPwd = "//android.widget.EditText[@content-desc='et_changePassword_currentPassword']";
		String textBoxNewPwd = "//android.widget.EditText[@content-desc='et_changePassword_fullName']";
		String textBoxConfirmNewPwd = "//android.widget.EditText[@content-desc='et_changePassword_confirmPassword']";

		String dropDownPaymentSettings = "(//android.widget.EditText)[1]";
		String textNEFT = "//android.widget.CheckedTextView[@text='Bank Payment (NEFT)']";
		String textPaytmWallet = "//android.widget.CheckedTextView[@text='Paytm Wallet']";
		String textAmazonGiftCard = "//android.widget.CheckedTextView[@text='Amazon Gift Card']";

		String textBoxPaytmMobileNumber = "//android.widget.EditText[@content-desc='et_settingsPaytm_mobile']";
		String textBoxPaytmPassword = "//android.widget.EditText[@content-desc='et_settingsPaytm_password']";

		String textBoxAmazonEmail = "//android.widget.EditText[@content-desc='et_settingsAmazon_email']";
		String textBoxAmazonPassword = "//android.widget.EditText[@content-desc='et_settingsAmazon_password']";
		String emailAddress = objCashKaroUtility.generateEmail();
		String mobileNumber = objCashKaroUtility.generateMobileNumber();	

		String otpValue = new EKOnboardingPage(driver, testInfo).
				clickOnSignUpButton().
				enterFullName().
				enterEmail(emailAddress).
				enterPassword().
				enterMobileNumber(mobileNumber).
				clickOnGetOTPButton(mobileNumber);

		new OTPPage(driver, testInfo).
		enterOTP(otpValue).
		clickOnVerifyOTP();

		new HomePage(driver, testInfo).		
		clickOnProfileIconForSignedUser().
		clickOnChooseLanguage().
		clickMarathiLink().
		clickContinueButton().
		clickRelaunchButton();

		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "profile_tab"), "home page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj17, "account_setting_menu"), "profile page");
		validateRequiredText(retrieveChildKeyValues(jsonObj17, "account_setting_menu"), "profile page");
		clickOnPartialRequiredText(retrieveChildKeyValues(jsonObj1, "personal_details_tab"), "account settings page");
		scrollFromUpToDowninApp();scrollFromUpToDowninApp();
		validateRequiredText(retrieveChildKeyValues(jsonObj26, "name_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj26, "email_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj26, "mobile_number_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj26, "receive_newsletter_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj26, "receive_referral_earning_txt"), "account settings page");
		validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj26, "note_txt"), "account settings page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "save_changes_btn"), "account settings page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj26, "no_value_change_error_msg"), "account settings page");

		clickOnPartialRequiredText(retrieveChildKeyValues(jsonObj1, "change_password_tab"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj27, "change_password_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj27, "new_password_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj27, "confirm_password_inputfield_txt"), "account settings page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "save_changes_btn"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj27, "change_password_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "current_password_error_msg"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj27, "new_password_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "new_password_error_msg"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj27, "confirm_password_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "confirm_password_error_msg"), "account settings page");

		enterTextByXpath(textBoxExistingPwd, getTestData(7, "oneUserChar"));
		enterTextByXpath(textBoxNewPwd, getTestData(7, "oneUserChar"));
		enterTextByXpath(textBoxConfirmNewPwd, getTestData(7, "oneUserChar"));
		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "save_changes_btn"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj27, "change_password_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "password_minimum_characters_error_msg"), 1, "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj27, "new_password_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "password_minimum_characters_error_msg"), 2, "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj27, "confirm_password_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "password_minimum_characters_error_msg"), 3, "account settings page");

		enterTextByXpath(textBoxExistingPwd, getTestData(7, "FiftyUserChar"));
		enterTextByXpath(textBoxNewPwd, getTestData(7, "FiftyUserChar"));
		enterTextByXpath(textBoxConfirmNewPwd, getTestData(7, "FiftyUserChar"));
		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "save_changes_btn"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj27, "change_password_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "password_maximum_characters_error_msg"), 1, "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj27, "new_password_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "password_maximum_characters_error_msg"), 2, "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj27, "confirm_password_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "password_maximum_characters_error_msg"), 3, "account settings page");


		enterTextByXpath(textBoxExistingPwd, getTestData(0, "TC001_ValidLoginPassword"));
		enterTextByXpath(textBoxNewPwd, getTestData(0, "TC001_ValidLoginPassword"));
		enterTextByXpath(textBoxConfirmNewPwd, getTestData(0, "TC001_InValidPassword"));
		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "save_changes_btn"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj27, "change_password_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj27, "new_password_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj27, "confirm_password_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "password_not_match_error_msg"), "account settings page");

		clickOnPartialRequiredText(retrieveChildKeyValues(jsonObj1, "payment_settings_tab"), "account settings page");

		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(dropDownPaymentSettings);
		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(textPaytmWallet);
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "mobile_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "confirm_password_inputfield_txt"), "account settings page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "save_changes_btn"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "mobile_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "mobile_number_error_msg"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "confirm_password_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "password_error_msg"), "account settings page");

		enterTextByXpath(textBoxPaytmMobileNumber, "1");
		enterTextByXpath(textBoxPaytmPassword, "1");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "save_changes_btn"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "mobile_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "minimum_digits_error_msg"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "confirm_password_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "password_minimum_characters_error_msg"), "account settings page");

		enterTextByXpath(textBoxPaytmPassword, getTestData(7, "FiftyUserChar"));
		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "save_changes_btn"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "confirm_password_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "password_maximum_characters_error_msg"), "account settings page");

		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(dropDownPaymentSettings);
		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(textAmazonGiftCard);

		validateRequiredText(retrieveChildKeyValues(jsonObj40, "email_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "confirm_password_inputfield_txt"), "account settings page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "save_changes_btn"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "email_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "email_error_msg"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "confirm_password_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "password_error_msg"), "account settings page");

		enterTextByXpath(textBoxAmazonEmail, "1");
		enterTextByXpath(textBoxAmazonPassword, "1");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "save_changes_btn"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "email_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "minimum_characters_error_msg"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "confirm_password_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "password_minimum_characters_error_msg"), "account settings page");

		enterTextByXpath(textBoxAmazonEmail, "asdfghjkl");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "save_changes_btn"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "email_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "valid_email_error_msg"), "account settings page");

		enterTextByXpath(textBoxAmazonEmail, getTestData(7, "FiftyUserCharWithSpace"));
		enterTextByXpath(textBoxAmazonPassword, getTestData(7, "FiftyUserChar"));
		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "save_changes_btn"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "email_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "maximum_characters_error_msg"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "confirm_password_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "maximum_characters_error_msg"), "account settings page");

		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(dropDownPaymentSettings);
		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(textNEFT);

		validateRequiredText(retrieveChildKeyValues(jsonObj40, "bank_account_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "bank_name_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "branch_name_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "bank_account_number_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "bank_ifsc_code_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "confirm_password_inputfield_txt"), "account settings page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "save_changes_btn"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "bank_account_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "account_holder_error_msg"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "bank_name_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "valid_bank_name_error_msg"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "branch_name_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "branch_minimum_digits_error_msg"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "bank_account_number_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "account_number_error_msg"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "bank_ifsc_code_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "ifsc_code_error_msg"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "confirm_password_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "password_error_msg"), "account settings page");

	}

	@Test
	public void chooseLanguage_LanguageMararthiValidation_ReferAndEarn() throws UnsupportedEncodingException, FileNotFoundException, IOException, ParseException {

		JSONObject jsonObj1 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "marathiFileName"), getTestData(16, "common_label"));
		JSONObject jsonObj17 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "marathiFileName"), getTestData(16, "profile_right_menu"));
		JSONObject jsonObj18 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "marathiFileName"), getTestData(16, "referandearn"));

		reportStep("chooseLanguage_LanguageMararthiValidation_AccountSettings started", "INFO");

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo);

		String emailAddress = objCashKaroUtility.generateEmail();
		String mobileNumber = objCashKaroUtility.generateMobileNumber();	

		String otpValue = new EKOnboardingPage(driver, testInfo).
				clickOnSignUpButton().
				enterFullName().
				enterEmail(emailAddress).
				enterPassword().
				enterMobileNumber(mobileNumber).
				clickOnGetOTPButton(mobileNumber);

		new OTPPage(driver, testInfo).
		enterOTP(otpValue).
		clickOnVerifyOTP();

		new HomePage(driver, testInfo).		
		clickOnProfileIconForSignedUser().
		clickOnChooseLanguage().
		clickMarathiLink().
		clickContinueButton().
		clickRelaunchButton();

		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "profile_tab"), "home page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj17, "my_referral_menu"), "profile page");
		validateRequiredText(retrieveChildKeyValues(jsonObj17, "my_referral_menu"), "my referral page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj17, "referandearn_menu"), "my referral page");
		validateRequiredText(retrieveChildKeyValues(jsonObj17, "referandearn_menu"), "my referral page");
		scrollFromUpToDowninApp();scrollFromUpToDowninApp();
		validateRequiredText(retrieveChildKeyValues(jsonObj18, "title"), "my referral page");
		validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj18, "sub_title"), "my referral page");
		validateRequiredText(retrieveChildKeyValues(jsonObj18, "message"), "my referral page");
		validateRequiredText(retrieveChildKeyValues(jsonObj18, "referral_link_title"), "my referral page");
		validateRequiredText(retrieveChildKeyValues(jsonObj18, "copy_message_title"), "my referral page");
		validateRequiredText(retrieveChildKeyValues(jsonObj18, "tap_copy"), "my referral page");
		validateRequiredText(retrieveChildKeyValues(jsonObj18, "invite_title"), "my referral page");


	}

	@Test
	public void chooseLanguage_LanguageMararthiValidation_ReferralNetwork() throws UnsupportedEncodingException, FileNotFoundException, IOException, ParseException {

		JSONObject jsonObj1 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "marathiFileName"), getTestData(16, "common_label"));
		JSONObject jsonObj17 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "marathiFileName"), getTestData(16, "profile_right_menu"));
		JSONObject jsonObj19 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "marathiFileName"), getTestData(16, "referral_network"));

		reportStep("chooseLanguage_LanguageMararthiValidation_AccountSettings started", "INFO");

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo);

		String emailAddress = objCashKaroUtility.generateEmail();
		String mobileNumber = objCashKaroUtility.generateMobileNumber();	

		String otpValue = new EKOnboardingPage(driver, testInfo).
				clickOnSignUpButton().
				enterFullName().
				enterEmail(emailAddress).
				enterPassword().
				enterMobileNumber(mobileNumber).
				clickOnGetOTPButton(mobileNumber);

		new OTPPage(driver, testInfo).
		enterOTP(otpValue).
		clickOnVerifyOTP();

		new HomePage(driver, testInfo).		
		clickOnProfileIconForSignedUser().
		clickOnChooseLanguage().
		clickMarathiLink().
		clickContinueButton().
		clickRelaunchButton();

		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "profile_tab"), "home page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj17, "my_referral_menu"), "profile page");
		validateRequiredText(retrieveChildKeyValues(jsonObj17, "my_referral_menu"), "my referral page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj17, "referral_network_menu"), "profile page");
		validateRequiredText(retrieveChildKeyValues(jsonObj17, "referral_network_menu"), "referral nw page");
		validateRequiredText(retrieveChildKeyValues(jsonObj19, "title"), "referral nw page");
		validateRequiredText(retrieveChildKeyValues(jsonObj19, "total_referral_earn_txt"), "referral nw page");
		validateRequiredText(retrieveChildKeyValues(jsonObj19, "friend_joined_txt"), "referral nw page");
		validateRequiredText(retrieveChildKeyValues(jsonObj19, "no_referral_title"), "referral nw page");
		validateRequiredText(retrieveChildKeyValues(jsonObj19, "no_referral_message"), "referral nw page");
		validateRequiredText(retrieveChildKeyValues(jsonObj19, "refer_earn_btn"), "referral nw page");


	}

	@Test
	public void chooseLanguage_LanguageMararthiValidation_MyEarnings_NewUser() throws UnsupportedEncodingException, FileNotFoundException, IOException, ParseException {

		JSONObject jsonObj1 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "marathiFileName"), getTestData(16, "common_label"));
		JSONObject jsonObj4 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "marathiFileName"), getTestData(16, "home"));
		JSONObject jsonObj17 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "marathiFileName"), getTestData(16, "profile_right_menu"));
		JSONObject jsonObj28 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "marathiFileName"), getTestData(16, "my_account_earnings"));
		JSONObject jsonObj30 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "marathiFileName"), getTestData(16, "earnings_info"));

		JSONObject jsonObj33 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "marathiFileName"), getTestData(16, "my_earnings"));

		reportStep("chooseLanguage_LanguageMararthiValidation_MyEarnings_NewUser started", "INFO");

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo);

		String linkEarningsInfo = "//android.widget.TextView[@content-desc='txt_myEarnings_cashbackEarningsInfo']";

		String emailAddress = objCashKaroUtility.generateEmail();
		String mobileNumber = objCashKaroUtility.generateMobileNumber();	

		String otpValue = new EKOnboardingPage(driver, testInfo).
				clickOnSignUpButton().
				enterFullName().
				enterEmail(emailAddress).
				enterPassword().
				enterMobileNumber(mobileNumber).
				clickOnGetOTPButton(mobileNumber);

		new OTPPage(driver, testInfo).
		enterOTP(otpValue).
		clickOnVerifyOTP();

		new HomePage(driver, testInfo).		
		clickOnProfileIconForSignedUser().
		clickOnChooseLanguage().
		clickMarathiLink().
		clickContinueButton().
		clickRelaunchButton();

		validateRequiredText(retrieveChildKeyValues(jsonObj4, "dynamic_home_top_Category"), "home page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "profile_tab"), "home page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj17, "my_earnings_menu"), "profile page");
		validateRequiredText(retrieveChildKeyValues(jsonObj17, "my_earnings_menu"), "my earnings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj28, "total_earning_txt"), "my earnings page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj33, "cashback_tap"), "my earnings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj33, "cashback_earning_title"), "my earnings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj33, "request_cashback_btn"), "my earnings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj33, "no_cashback_yet_txt"), "my earnings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj33, "shop_today_txt"), "my earnings page");

		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(linkEarningsInfo);
		validateRequiredText(retrieveChildKeyValues(jsonObj30, "message_first_txt"), "earnings info page");


		backButton();

		clickOnRequiredText(retrieveChildKeyValues(jsonObj33, "referral_tap"), "my earnings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj33, "referral_earning_title"), "my earnings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj33, "request_cashback_btn"), "my earnings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj33, "no_referral_yet_txt"), "my earnings page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj33, "no_referral_yet_sub_txt"), "my earnings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj33, "no_referral_invite_some_friends"), "my earnings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj33, "refer_now_btn"), "my earnings page");


	}

	@Test
	public void chooseLanguage_LanguageMararthiValidation_MakeLinks_NewUser() throws UnsupportedEncodingException, FileNotFoundException, IOException, ParseException {

		JSONObject jsonObj1 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "marathiFileName"), getTestData(16, "common_label"));
		JSONObject jsonObj2 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "marathiFileName"), getTestData(16, "common_validate_label"));
		JSONObject jsonObj4 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "marathiFileName"), getTestData(16, "home"));
		JSONObject jsonObj17 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "marathiFileName"), getTestData(16, "profile_right_menu"));
		JSONObject jsonObj51 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "marathiFileName"), getTestData(16, "make_profit_links"));
		JSONObject jsonObj53 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "marathiFileName"), getTestData(16, "profit_partners_details"));
		JSONObject jsonObj54 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "marathiFileName"), getTestData(16, "profit_partners_list"));

		reportStep("chooseLanguage_LanguageMararthiValidation_MakeLinks_NewUser started", "INFO");

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo);

		String imagePartner = "//android.widget.ImageView[@content-desc='img_profitPartnersList_partnersIMG']";
		String emailAddress = objCashKaroUtility.generateEmail();
		String mobileNumber = objCashKaroUtility.generateMobileNumber();	

		String otpValue = new EKOnboardingPage(driver, testInfo).
				clickOnSignUpButton().
				enterFullName().
				enterEmail(emailAddress).
				enterPassword().
				enterMobileNumber(mobileNumber).
				clickOnGetOTPButton(mobileNumber);

		new OTPPage(driver, testInfo).
		enterOTP(otpValue).
		clickOnVerifyOTP();

		new HomePage(driver, testInfo).		
		clickOnProfileIconForSignedUser().
		clickOnChooseLanguage().
		clickMarathiLink().
		clickContinueButton().
		clickRelaunchButton();

		validateRequiredText(retrieveChildKeyValues(jsonObj4, "dynamic_home_top_Category"), "home page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "profile_tab"), "home page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj17, "make_profit_link_menu"), "profile page");
		validateRequiredText(retrieveChildKeyValues(jsonObj51, "title"), "make links page");
		validateRequiredText(retrieveChildKeyValues(jsonObj51, "subtitle"), "make links page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj51, "see_partner_txt"), "make links page");
		validateRequiredText(retrieveChildKeyValues(jsonObj54, "toolbar_profit_partners_title"), "partners page");
		validateRequiredText(retrieveChildKeyValues(jsonObj54, "profit_partners_information"), "partners page");

		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(imagePartner);
		validateRequiredText(retrieveChildKeyValues(jsonObj53, "profit_rates"), "store page");

		backButton();
		backButton();

		validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj51, "paste_link"), "make links page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj51, "make_profit_link_txt"), "make links page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "url_error_msg"), "make links page");
		validateRequiredText(retrieveChildKeyValues(jsonObj51, "how_make_profit_txt"), "make links page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "url_error_msg"), "make links page");		
		validateRequiredText(retrieveChildKeyValues(jsonObj51, "step_one_title"), "make links page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj51, "step_one_txt"), "make links page");
		validateRequiredText(retrieveChildKeyValues(jsonObj51, "step_two_title"), "make links page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj51, "step_two_txt"), "make links page");
		validateRequiredText(retrieveChildKeyValues(jsonObj51, "step_three_title"), "make links page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj51, "step_three_txt"), "make links page");
		validateRequiredText(retrieveChildKeyValues(jsonObj51, "step_four_title"), "make links page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj51, "step_four_txt"), "make links page");


	}

	@Test
	public void chooseLanguage_LanguageMararthiValidation_GetStarted_Joinfree_TermsAndConditions() throws UnsupportedEncodingException, FileNotFoundException, IOException, ParseException {

		JSONObject jsonObj1 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "marathiFileName"), getTestData(16, "common_label"));
		JSONObject jsonObj2 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "marathiFileName"), getTestData(16, "common_validate_label"));
		JSONObject jsonObj17 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "marathiFileName"), getTestData(16, "profile_right_menu"));
		JSONObject jsonObj34 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "marathiFileName"), getTestData(16, "on_boarding"));
		JSONObject jsonObj36 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "marathiFileName"), getTestData(16, "join_free"));
		JSONObject jsonObj50 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "marathiFileName"), getTestData(16, "terms_conditions"));

		reportStep("chooseLanguage_LanguageMararthiValidation_GetStarted_Joinfree_Login_ForgotPassword started", "INFO");

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo);

		String textboxName = "//android.widget.EditText[@content-desc='et_signup_fullname']";
		String textboxEmail = "//android.widget.EditText[@content-desc='et_signup_email']|//android.widget.EditText[@content-desc='et_signin_email']|//android.widget.EditText[@content-desc='et_forgotpassword_email']";
		String textboxPwd = "//android.widget.EditText[@content-desc='et_signup_password']|//android.widget.EditText[@content-desc='et_signin_password']";
		String textboxMobileNumber = "//android.widget.EditText[@content-desc='et_signup_mobile']";		
		String emailAddress = objCashKaroUtility.generateEmail();
		String mobileNumber = objCashKaroUtility.generateMobileNumber();	

		String otpValue = new EKOnboardingPage(driver, testInfo).
				clickOnSignUpButton().
				enterFullName().
				enterEmail(emailAddress).
				enterPassword().
				enterMobileNumber(mobileNumber).
				clickOnGetOTPButton(mobileNumber);

		new OTPPage(driver, testInfo).
		enterOTP(otpValue).
		clickOnVerifyOTP();

		new HomePage(driver, testInfo).		
		clickOnProfileIconForSignedUser().
		clickOnChooseLanguage().
		clickMarathiLink().
		clickContinueButton().
		clickRelaunchButton();

		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "profile_tab"), "home page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj17, "logout_menu"), "profile page");
		validateRequiredText(retrieveChildKeyValues(jsonObj17, "alert_logout"), "logout popup");
		validateRequiredText(retrieveChildKeyValues(jsonObj17, "alert_logout_msg"), "logout popup");
		validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj17, "alert_cancel_btn"), "logout popup");
		clickOnPartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj17, "alert_ok_btn"), "logout popup");

		new EKOnboardingPage(driver, testInfo).
		clickOnSignUpButton();

		validateRequiredText(retrieveChildKeyValues(jsonObj17, "join_free_menu"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "title"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "subtitle"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "join_facebook_txt"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "join_or_txt"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "name_inputfield_txt"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "email_inputfield_txt"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "password_inputfield_txt"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "mobile_inputfield_txt"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "otp_btn"), "join free page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj36, "already_register"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "sign_in_txt"), "join free page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj36, "signup_terms_txt"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "terms_condition_txt"), "join free page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj36, "otp_btn"), "join free page");		
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "name_inputfield_txt"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "name_error_msg"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "email_inputfield_txt"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "email_error_msg"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "password_inputfield_txt"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "password_error_msg"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "mobile_inputfield_txt"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "mobile_number_error_msg"), "join free page");

		enterTextByXpath(textboxName, getTestData(7, "oneUserChar"));
		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(textboxEmail);
		clickOnNoneOFTheAbove();
		enterTextByXpath(textboxEmail, getTestData(7, "oneUserChar"));
		enterTextByXpath(textboxPwd, getTestData(7, "oneUserChar"));
		enterTextByXpath(textboxMobileNumber, getTestData(7, "oneUserChar"));
		clickOnRequiredText(retrieveChildKeyValues(jsonObj36, "otp_btn"), "join free page");		
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "name_inputfield_txt"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "minimum_characters_error_msg"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "email_inputfield_txt"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "minimum_characters_error_msg"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "password_inputfield_txt"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "password_minimum_characters_error_msg"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "mobile_inputfield_txt"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "minimum_digits_error_msg"), "join free page");

		enterTextByXpath(textboxName, getTestData(7, "FiftyUserCharWithSpace"));
		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(textboxEmail);
		clickOnNoneOFTheAbove();
		enterTextByXpath(textboxEmail, getTestData(7, "FiftyUserCharWithSpace"));
		enterTextByXpath(textboxPwd, getTestData(7, "FiftyUserChar"));
		enterTextByXpath(textboxMobileNumber, "2222222222");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj36, "otp_btn"), "join free page");		
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "name_inputfield_txt"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "maximum_characters_error_msg"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "email_inputfield_txt"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "maximum_characters_error_msg"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "password_inputfield_txt"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "password_maximum_characters_error_msg"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "mobile_inputfield_txt"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "valid_mobile_number_error_msg"), "join free page");

		enterTextByXpath(textboxName, "2*2");
		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(textboxEmail);
		clickOnNoneOFTheAbove();
		enterTextByXpath(textboxEmail, "2*2");
		enterTextByXpath(textboxPwd, "2 2 2 2 2");
		enterTextByXpath(textboxMobileNumber, "2*22222222");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj36, "otp_btn"), "join free page");		
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "name_inputfield_txt"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "valid_name_error_msg"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "email_inputfield_txt"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "valid_email_error_msg"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "password_inputfield_txt"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "valid_password_error_msg"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "mobile_inputfield_txt"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "valid_mobile_number_error_msg"), "join free page");

		backButton();

		new EKOnboardingPage(driver, testInfo).
		clickOnSignUpButton();
		clickOnRequiredText(retrieveChildKeyValues(jsonObj36, "terms_condition_txt"), "join free page");
		scrollFromUpToDowninApp();scrollFromUpToDowninApp();
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "terms_condition_txt"), "terms & conditions page");
		validateRequiredText(retrieveChildKeyValues(jsonObj50, "title"), "terms & conditions page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj50, "introduction_title"), "terms & conditions page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj50, "introduction_details"), "terms & conditions page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj50, "membership_title"), "terms & conditions page");
		validateRequiredText(retrieveChildKeyValues(jsonObj50, "membership_details"), "terms & conditions page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj50, "our_service_title"), "terms & conditions page");
		validateRequiredText(retrieveChildKeyValues(jsonObj50, "our_service_details"), "terms & conditions page");


	}

	@Test
	public void chooseLanguage_LanguageMararthiValidation_Login() throws UnsupportedEncodingException, FileNotFoundException, IOException, ParseException {

		JSONObject jsonObj1 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "marathiFileName"), getTestData(16, "common_label"));
		JSONObject jsonObj2 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "marathiFileName"), getTestData(16, "common_validate_label"));
		JSONObject jsonObj17 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "marathiFileName"), getTestData(16, "profile_right_menu"));
		JSONObject jsonObj35 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "marathiFileName"), getTestData(16, "sign_in"));
		JSONObject jsonObj36 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "marathiFileName"), getTestData(16, "join_free"));

		reportStep("chooseLanguage_LanguageMararthiValidation_GetStarted_Joinfree_Login_ForgotPassword started", "INFO");

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo);

		String textboxEmail = "//android.widget.EditText[@content-desc='et_signup_email']|//android.widget.EditText[@content-desc='et_signin_email']|//android.widget.EditText[@content-desc='et_forgotpassword_email']";
		String textboxPwd = "//android.widget.EditText[@content-desc='et_signup_password']|//android.widget.EditText[@content-desc='et_signin_password']";
		String emailAddress = objCashKaroUtility.generateEmail();
		String mobileNumber = objCashKaroUtility.generateMobileNumber();	

		String otpValue = new EKOnboardingPage(driver, testInfo).
				clickOnSignUpButton().
				enterFullName().
				enterEmail(emailAddress).
				enterPassword().
				enterMobileNumber(mobileNumber).
				clickOnGetOTPButton(mobileNumber);

		new OTPPage(driver, testInfo).
		enterOTP(otpValue).
		clickOnVerifyOTP();

		new HomePage(driver, testInfo).		
		clickOnProfileIconForSignedUser().
		clickOnChooseLanguage().
		clickMarathiLink().
		clickContinueButton().
		clickRelaunchButton();

		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "profile_tab"), "home page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj17, "logout_menu"), "profile page");
		validateRequiredText(retrieveChildKeyValues(jsonObj17, "alert_logout"), "logout popup");
		validateRequiredText(retrieveChildKeyValues(jsonObj17, "alert_logout_msg"), "logout popup");
		validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj17, "alert_cancel_btn"), "logout popup");
		clickOnPartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj17, "alert_ok_btn"), "logout popup");

		new EKOnboardingPage(driver, testInfo).
		clickOnSignInbutton();

		validateRequiredText(retrieveChildKeyValues(jsonObj36, "sign_in_txt"), "login page");
		validateRequiredText(retrieveChildKeyValues(jsonObj35, "subtitle"), "login page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj35, "login_txt"), "login page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj35, "share_txt"), "login page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj35, "earn_txt"), "login page");
		validateRequiredText(retrieveChildKeyValues(jsonObj35, "subtitle"), "login page");
		validateRequiredText(retrieveChildKeyValues(jsonObj35, "sign_in_facebook_txt"), "login page");
		validateRequiredText(retrieveChildKeyValues(jsonObj35, "sign_in_or_txt"), "login page");
		validateRequiredText(retrieveChildKeyValues(jsonObj35, "email_inputfield_txt"), "login page");
		validateRequiredText(retrieveChildKeyValues(jsonObj35, "password_inputfield_txt"), "login page");
		validateRequiredText(retrieveChildKeyValues(jsonObj35, "forgot_password_txt"), "login page");
		validateRequiredText(retrieveChildKeyValues(jsonObj35, "sign_with_email_btn"), "login page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj35, "new_user_txt"), "login page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj35, "join_free_txt"), "login page");


		clickOnRequiredText(retrieveChildKeyValues(jsonObj35, "sign_with_email_btn"), "login page");
		validateRequiredText(retrieveChildKeyValues(jsonObj35, "email_inputfield_txt"), "login page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "email_error_msg"), "login page");
		validateRequiredText(retrieveChildKeyValues(jsonObj35, "password_inputfield_txt"), "login page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "password_error_msg"), "login page");

		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(textboxEmail);
		clickOnNoneOFTheAbove();
		enterTextByXpath(textboxEmail, getTestData(7, "oneUserChar"));
		enterTextByXpath(textboxPwd, getTestData(7, "oneUserChar"));
		clickOnRequiredText(retrieveChildKeyValues(jsonObj35, "sign_with_email_btn"), "login page");
		validateRequiredText(retrieveChildKeyValues(jsonObj35, "email_inputfield_txt"), "login page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "minimum_characters_error_msg"), "login page");
		validateRequiredText(retrieveChildKeyValues(jsonObj35, "password_inputfield_txt"), "login page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "password_minimum_characters_error_msg"), "login page");

		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(textboxEmail);
		clickOnNoneOFTheAbove();
		enterTextByXpath(textboxEmail, getTestData(7, "FiftyUserCharWithSpace"));
		enterTextByXpath(textboxPwd, getTestData(7, "FiftyUserChar"));
		clickOnRequiredText(retrieveChildKeyValues(jsonObj35, "sign_with_email_btn"), "login page");
		validateRequiredText(retrieveChildKeyValues(jsonObj35, "email_inputfield_txt"), "login page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "maximum_characters_error_msg"), "login page");
		validateRequiredText(retrieveChildKeyValues(jsonObj35, "password_inputfield_txt"), "login page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "password_maximum_characters_error_msg"), "login page");

		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(textboxEmail);
		clickOnNoneOFTheAbove();
		enterTextByXpath(textboxEmail, "2*2");
		enterTextByXpath(textboxPwd, "2 2 2 2 2");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj35, "sign_with_email_btn"), "login page");
		validateRequiredText(retrieveChildKeyValues(jsonObj35, "email_inputfield_txt"), "login page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "valid_email_error_msg"), "login page");
		validateRequiredText(retrieveChildKeyValues(jsonObj35, "password_inputfield_txt"), "login page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "valid_password_error_msg"), "login page");


	}

	@Test
	public void chooseLanguage_LanguageMararthiValidation_ForgotPassword() throws UnsupportedEncodingException, FileNotFoundException, IOException, ParseException {

		JSONObject jsonObj1 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "marathiFileName"), getTestData(16, "common_label"));
		JSONObject jsonObj2 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "marathiFileName"), getTestData(16, "common_validate_label"));
		JSONObject jsonObj17 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "marathiFileName"), getTestData(16, "profile_right_menu"));
		JSONObject jsonObj35 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "marathiFileName"), getTestData(16, "sign_in"));
		JSONObject jsonObj37 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "marathiFileName"), getTestData(16, "forgot_password"));

		reportStep("chooseLanguage_LanguageMararthiValidation_GetStarted_Joinfree_Login_ForgotPassword started", "INFO");

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo);

		String textboxEmail = "//android.widget.EditText[@content-desc='et_signup_email']|//android.widget.EditText[@content-desc='et_signin_email']|//android.widget.EditText[@content-desc='et_forgotpassword_email']";
		String emailAddress = objCashKaroUtility.generateEmail();
		String mobileNumber = objCashKaroUtility.generateMobileNumber();	

		String otpValue = new EKOnboardingPage(driver, testInfo).
				clickOnSignUpButton().
				enterFullName().
				enterEmail(emailAddress).
				enterPassword().
				enterMobileNumber(mobileNumber).
				clickOnGetOTPButton(mobileNumber);

		new OTPPage(driver, testInfo).
		enterOTP(otpValue).
		clickOnVerifyOTP();

		new HomePage(driver, testInfo).		
		clickOnProfileIconForSignedUser().
		clickOnChooseLanguage().
		clickMarathiLink().
		clickContinueButton().
		clickRelaunchButton();

		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "profile_tab"), "home page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj17, "logout_menu"), "profile page");
		validateRequiredText(retrieveChildKeyValues(jsonObj17, "alert_logout"), "logout popup");
		validateRequiredText(retrieveChildKeyValues(jsonObj17, "alert_logout_msg"), "logout popup");
		validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj17, "alert_cancel_btn"), "logout popup");
		clickOnPartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj17, "alert_ok_btn"), "logout popup");

		new EKOnboardingPage(driver, testInfo).
		clickOnSignInbutton();


		clickOnRequiredText(retrieveChildKeyValues(jsonObj35, "forgot_password_txt"), "login page");
		validateRequiredText(retrieveChildKeyValues(jsonObj35, "forgot_password_txt"), "forgot password page");
		validateRequiredText(retrieveChildKeyValues(jsonObj37, "title"), "forgot password page");
		validateRequiredText(retrieveChildKeyValues(jsonObj37, "subtitle"), "forgot password page");
		validateRequiredText(retrieveChildKeyValues(jsonObj37, "email_inputfield_txt"), "forgot password page");
		validateRequiredText(retrieveChildKeyValues(jsonObj37, "password_link_txt"), "forgot password page");
		validateRequiredText(retrieveChildKeyValues(jsonObj37, "submit_btn"), "forgot password page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj37, "remember_password_txt"), "forgot password page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj37, "login_txt"), "forgot password page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj37, "submit_btn"), "forgot password page");
		validateRequiredText(retrieveChildKeyValues(jsonObj37, "email_inputfield_txt"), "forgot password page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "email_error_msg"), "login page");

		enterTextByXpath(textboxEmail, getTestData(7, "oneUserChar"));		
		clickOnRequiredText(retrieveChildKeyValues(jsonObj37, "submit_btn"), "forgot password page");
		validateRequiredText(retrieveChildKeyValues(jsonObj37, "email_inputfield_txt"), "forgot password page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "minimum_characters_error_msg"), "login page");

		enterTextByXpath(textboxEmail, getTestData(7, "FiftyUserCharWithSpace"));		
		clickOnRequiredText(retrieveChildKeyValues(jsonObj37, "submit_btn"), "forgot password page");
		validateRequiredText(retrieveChildKeyValues(jsonObj37, "email_inputfield_txt"), "forgot password page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "maximum_characters_error_msg"), "login page");

		enterTextByXpath(textboxEmail, "2*2");		
		clickOnRequiredText(retrieveChildKeyValues(jsonObj37, "submit_btn"), "forgot password page");
		validateRequiredText(retrieveChildKeyValues(jsonObj37, "email_inputfield_txt"), "forgot password page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "valid_email_error_msg"), "login page");


	}

	@Test
	public void chooseLanguage_LanguageMararthiValidation_MyEarnings_History_Payment_ExistingUser() throws UnsupportedEncodingException, FileNotFoundException, IOException, ParseException {

		JSONObject jsonObj1 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "marathiFileName"), getTestData(16, "common_label"));
		JSONObject jsonObj2 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "marathiFileName"), getTestData(16, "common_validate_label"));
		JSONObject jsonObj14 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "marathiFileName"), getTestData(16, "search_result_all"));
		JSONObject jsonObj17 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "marathiFileName"), getTestData(16, "profile_right_menu"));
		JSONObject jsonObj28 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "marathiFileName"), getTestData(16, "my_account_earnings"));
		JSONObject jsonObj29 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "marathiFileName"), getTestData(16, "total_cashback_earned"));
		JSONObject jsonObj30 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "marathiFileName"), getTestData(16, "earnings_info"));		
		JSONObject jsonObj31 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "marathiFileName"), getTestData(16, "earnings_referral"));
		JSONObject jsonObj32 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "marathiFileName"), getTestData(16, "earnings_cashback"));
		JSONObject jsonObj33 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "marathiFileName"), getTestData(16, "my_earnings"));
		JSONObject jsonObj35 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "marathiFileName"), getTestData(16, "sign_in"));
		JSONObject jsonObj36 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "marathiFileName"), getTestData(16, "join_free"));
		JSONObject jsonObj40 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "marathiFileName"), getTestData(16, "giftcard_wallet_inputfield"));
		JSONObject jsonObj46 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "marathiFileName"), getTestData(16, "payment"));
		JSONObject jsonObj52 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "marathiFileName"), getTestData(16, "my_shared_activities"));
		JSONObject jsonObj53 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "marathiFileName"), getTestData(16, "giftcard_wallet_request_payment"));

		reportStep("chooseLanguage_LanguageMararthiValidation_MyEarnings_History_Payment_ExistingUser started", "INFO");

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo);

		String dropDownPaymentSettings = "(//android.widget.EditText)[1]";
		String textNEFT = "//android.widget.CheckedTextView[@text='Bank Payment (NEFT)']";
		String textPaytmWallet = "//android.widget.CheckedTextView[@text='Paytm Wallet']";
		String textAmazonGiftCard = "//android.widget.CheckedTextView[@text='Amazon Gift Card']";
		String textBoxPaytmMobileNumberPaymentRequestPage = "//android.widget.EditText[@content-desc='et_requestpaymentPaytm_mobile']";
		String textBoxAmazonEmailPaymentRequestPage = "//android.widget.EditText[@content-desc='et_requestpaymentAmazon_email']";
		String linkEarningsInfoPaymentPage = "//android.widget.TextView[@content-desc='txt_payment_earningsInfo']";	
		String textboxName = "//android.widget.EditText[@content-desc='et_signup_fullname']";
		String textboxEmail = "//android.widget.EditText[@content-desc='et_signup_email']|//android.widget.EditText[@content-desc='et_signin_email']|//android.widget.EditText[@content-desc='et_forgotpassword_email']";
		String textboxPwd = "//android.widget.EditText[@content-desc='et_signup_password']|//android.widget.EditText[@content-desc='et_signin_password']";
		String textboxMobileNumber = "//android.widget.EditText[@content-desc='et_signup_mobile']";
		String searchIcon = "(//android.widget.TextView)[2]";
		String textAreaSearchIcon = "//android.widget.EditText[@content-desc='et_search_enterValue']";

		String cbStoreNameOne = CashKaroUtility.extractValuesFromStoreCategoryJsonAndLoadTheVariablesForStore("CB_Store_One", 0, "CB_Store_One_Name");
		String linkURL = CashKaroUtility.extractValuesFromStoreCategoryJsonAndLoadTheVariablesForStore("CB_Store_One", 0, "CB_Store_One_linkURL");

		String emailAddress = objCashKaroUtility.generateEmail();
		String mobileNumber = objCashKaroUtility.generateMobileNumber();	

		String emailAddressReferralJoinee = objCashKaroUtility.generateEmail();
		String mobileNumberReferralJoinee = objCashKaroUtility.generateMobileNumber();	

		String otpValue = new EKOnboardingPage(driver, testInfo).
				clickOnSignUpButton().
				enterFullName().
				enterEmail(emailAddress).
				enterPassword().
				enterMobileNumber(mobileNumber).
				clickOnGetOTPButton(mobileNumber);

		new OTPPage(driver, testInfo).
		enterOTP(otpValue).
		clickOnVerifyOTP();

		new HomePage(driver, testInfo).		
		clickOnProfileIconForSignedUser().
		clickOnChooseLanguage().
		clickMarathiLink().
		clickContinueButton().
		clickRelaunchButton();

		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "profile_tab"), "home page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj17, "logout_menu"), "profile page");
		validateRequiredText(retrieveChildKeyValues(jsonObj17, "alert_logout"), "logout popup");
		validateRequiredText(retrieveChildKeyValues(jsonObj17, "alert_logout_msg"), "logout popup");
		validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj17, "alert_cancel_btn"), "logout popup");
		clickOnPartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj17, "alert_ok_btn"), "logout popup");

		new EKOnboardingPage(driver, testInfo);

		CashKaroUtility cu = new CashKaroUtility(udid, port, automationName, deviceName);
		driver = cu.launchChromeWebView(driver);

		AdminCoreFunction adminFunctions = new AdminCoreFunction(driver, testInfo);
		adminFunctions.naviagateAdminPage_ThenSelectEarnKaro();
		adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
		adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
		adminFunctions.clickSubmit();

		AdminUsersPage usersPage = new AdminUsersPage(driver, testInfo);
		adminFunctions.clickOnHamburgerMenu();
		adminFunctions.clickUsersMenu();
		adminFunctions.clickUsersSubMenu();
		usersPage.selectSearchByDropDown("User Email");
		usersPage.enterKeyword(emailAddress);
		usersPage.clickSubmitButton();
		String userId = usersPage.extractUserIdValueFromResultstableFirstRow();
		String userName = usersPage.extractUserNameValueFromResultstableFirstRow();

		CashKaroUtility reopenCKApp1 = new CashKaroUtility(udid, port, automationName, deviceName, systemPort);
		driver = reopenCKApp1.launchEarnKaroApp(driver);

		CashKaroUtility objcashKaroutility = new CashKaroUtility(udid, port, automationName, deviceName);
		AppiumDriver<MobileElement> driver2 = objcashKaroutility.launchChrome();
		driver = driver2;

		String url = Utilities.referralLinkGetCode(userId, userName);

		objcashKaroutility.closeAllTabsAndOneTabInChromeBrowser(driver);
		objcashKaroutility.clickAndEnterURLInSearchOrTypeWebAddress(driver, url);

		objcashKaroutility.clickJoinFreeLink(driver);

		enterTextByXpath(textboxName, "ReferralJoinee");
		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(textboxEmail);
		clickOnNoneOFTheAbove();
		enterTextByXpath(textboxEmail, emailAddressReferralJoinee);
		enterTextByXpath(textboxPwd, "ppounds");
		enterTextByXpath(textboxMobileNumber, mobileNumberReferralJoinee);
		clickOnRequiredText(retrieveChildKeyValues(jsonObj36, "otp_btn"), "join free page");	

		new OTPPage(driver, testInfo);

		String otp = Utilities.getSignUPOTP(mobileNumberReferralJoinee);

		new OTPPage(driver, testInfo).
		enterOTP(otp);

		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "verify_otp"), "otp page");	

		sleep(15000);

		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(searchIcon);
		hideKeyboard();
		//validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj13, "search_inputfield_txt"), "search page");
		enterTextByXpath(textAreaSearchIcon, cbStoreNameOne);
		pressEnter();
		validateRequiredText(retrieveChildKeyValues(jsonObj14, "offer_count_single_message"), "search page");
		validateRequiredText(retrieveChildKeyValues(jsonObj1, "add_to_share"), "search page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "share_now"), "search page");

		WhatsAppUtility whatsAppUtility = new WhatsAppUtility(driver, testInfo, udid, port, automationName, deviceName, systemPort);
		whatsAppUtility.clickOnWhatAppMenuItemSearch().
		enterWhatsAppSearchText("Mallik").
		clickOnRequiredContact().
		clickOnSend().
		clickOnSend().
		clickOnLastSharedLink();

		new IntermediateRetailerPage(driver, testInfo).
		validateRetailerPage(linkURL, linkURL);

		CashKaroUtility cu2 = new CashKaroUtility(udid, port, automationName, deviceName);
		cu = cu2;
		driver = cu.launchChromeWebView(driver);

		AdminCoreFunction adminFunctions2 = new AdminCoreFunction(driver, testInfo);
		adminFunctions = adminFunctions2;
		adminFunctions.naviagateAdminPage_ThenSelectEarnKaro();
		adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
		adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
		adminFunctions.clickSubmit();

		AdminReportsPage reportsPage = new AdminReportsPage(driver, testInfo);
		adminFunctions.clickOnHamburgerMenu();
		adminFunctions.clickOnReports();
		reportsPage.clickExitClickSubMenu();
		reportsPage.clickClear();
		reportsPage.selectSearchByDropDown("User Email");
		reportsPage.enterKeyword(emailAddressReferralJoinee);
		reportsPage.clickSubmit();

		String exitClickOne = reportsPage.extractRequiredExitIdUsingStoreNameExitClickType(cbStoreNameOne, getTestData(7, "Normal"));

		AdminCashbackPage ProfitPage = new AdminCashbackPage(driver, testInfo);
		adminFunctions.clickOnHamburgerMenu();
		adminFunctions.clickCashbackMenu();
		ProfitPage.clickCashbackSubMenu();

		ProfitPage.clickAddNewButton();
		ProfitPage.enterExitId(exitClickOne);
		ProfitPage.entertransactionId(exitClickOne);
		ProfitPage.enterOrderId(exitClickOne);
		ProfitPage.setOrderDate();
		ProfitPage.enterOrderValue("1000");
		ProfitPage.enterconfirmCommisionNetwork(getTestData(11, "EightHundered"));
		ProfitPage.entercashback("700");
		ProfitPage.selectCashbackTypeFromDropDown(getTestData(11, "Cashback"));
		ProfitPage.selectCashbackStatusFromDropDown(getTestData(11, "Pending"));
		ProfitPage.setPendingDate();
		ProfitPage.enterDetails(exitClickOne);
		ProfitPage.clickSubmit();
		ProfitPage.clickClear();

		CashKaroUtility reopenCKAppAgain = new CashKaroUtility(udid, port, automationName, deviceName, systemPort);
		driver = reopenCKAppAgain.launchEarnKaroApp(driver);
		new HomePage(driver, testInfo);

		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "profile_tab"), "home page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj17, "history_menu"), "profile page");
		validateRequiredText(retrieveChildKeyValues(jsonObj1, "recent_clicks_tab"), "history page");
		validateRequiredText(retrieveChildKeyValues(jsonObj52, "title"), "history page");
		validateRequiredText(retrieveChildKeyValues(jsonObj52, "shared_link_details"), "history page");
		validateRequiredText(retrieveChildKeyValues(jsonObj52, "shared_id"), "history page");
		validateRequiredText(retrieveChildKeyValues(jsonObj52, "shared_date_time"), "history page");
		validateRequiredText(retrieveChildKeyValues(jsonObj52, "retailer_name"), "history page");
		validateRequiredText(retrieveChildKeyValues(jsonObj52, "no_of_clicks"), "history page");
		validateRequiredText(retrieveChildKeyValues(jsonObj52, "no_of_transactions"), "history page");
		validateRequiredText(retrieveChildKeyValues(jsonObj52, "total_earning"), "history page");

		backButton();

		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "profile_tab"), "home page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj17, "my_earnings_menu"), "profile page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj29, "total_cashback_earned_txt"), "my earnings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj29, "total_cashback_earned_txt"), 1, "profit page");
		validateRequiredText(retrieveChildKeyValues(jsonObj29, "total_cashback_earned_txt"), 2, "profit page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj29, "paid_cashback_txt"), "profit page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj29, "pending_cashback_txt"), "profit page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj29, "referral_cashback_txt"), "profit page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj29, "available_cashback_txt"), "profit page");

		backButton();

		validateRequiredText(retrieveChildKeyValues(jsonObj32, "order_details"), "my earnings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj32, "date"), "my earnings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj32, "retailer"), "my earnings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj32, "order_amount"), "my earnings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj32, "cashback_amount"), "my earnings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj32, "status"), "my earnings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj32, "expected_date"), "my earnings page");

		backButton();

		clickOnRequiredText(retrieveChildKeyValues(jsonObj17, "logout_menu"), "profile page");
		validateRequiredText(retrieveChildKeyValues(jsonObj17, "alert_logout"), "logout popup");
		validateRequiredText(retrieveChildKeyValues(jsonObj17, "alert_logout_msg"), "logout popup");
		validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj17, "alert_cancel_btn"), "logout popup");
		clickOnPartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj17, "alert_ok_btn"), "logout popup");

		new EKOnboardingPage(driver, testInfo);

		CashKaroUtility cu3 = new CashKaroUtility(udid, port, automationName, deviceName);
		cu = cu3;
		driver = cu.launchChromeWebView(driver);

		AdminCoreFunction adminFunctions3 = new AdminCoreFunction(driver, testInfo);
		adminFunctions = adminFunctions3;
		adminFunctions.naviagateAdminPage_ThenSelectEarnKaro();
		adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
		adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
		adminFunctions.clickSubmit();

		AdminCashbackPage ProfitPage1 = new AdminCashbackPage(driver, testInfo);
		ProfitPage = ProfitPage1;
		adminFunctions.clickOnHamburgerMenu();
		adminFunctions.clickCashbackMenu();
		ProfitPage.clickCashbackSubMenu();

		ProfitPage.selectSearchByStatusFromDropDown("Exit ID");
		ProfitPage.enterKeyword(exitClickOne);
		ProfitPage.selectCashbackStatusFromDropDown(getTestData(11, "Pending"));
		ProfitPage.clickSubmit();
		ProfitPage.clickEdit();
		ProfitPage.selectCashbackStatusFromDropDown(getTestData(11, "Confirmed"));
		ProfitPage.setConfirmDate();
		ProfitPage.clickSubmit();
		ProfitPage.clickClear();

		CashKaroUtility reopenCKAppOnceAgain = new CashKaroUtility(udid, port, automationName, deviceName, systemPort);
		driver = reopenCKAppOnceAgain.launchEarnKaroApp(driver);

		new EKOnboardingPage(driver, testInfo).clickOnSignInbutton();

		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(textboxEmail);
		clickOnNoneOFTheAbove();
		enterTextByXpath(textboxEmail, emailAddress);
		enterTextByXpath(textboxPwd, "ppounds");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj35, "sign_with_email_btn"), "login page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "profile_tab"), "home page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj17, "my_earnings_menu"), "profile page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj33, "referral_tap"), "my earnings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj31, "order_details"), "my earnings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj31, "date"), "my earnings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj31, "referral_name"), "my earnings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj31, "cashback"), "my earnings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj31, "status"), "my earnings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj33, "refer_now_btn"), "my earnings page");

		backButton();

		clickOnRequiredText(retrieveChildKeyValues(jsonObj17, "logout_menu"), "profile page");
		validateRequiredText(retrieveChildKeyValues(jsonObj17, "alert_logout"), "logout popup");
		validateRequiredText(retrieveChildKeyValues(jsonObj17, "alert_logout_msg"), "logout popup");
		validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj17, "alert_cancel_btn"), "logout popup");
		clickOnPartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj17, "alert_ok_btn"), "logout popup");

		new EKOnboardingPage(driver, testInfo).clickOnSignInbutton();

		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(textboxEmail);
		clickOnNoneOFTheAbove();
		enterTextByXpath(textboxEmail, emailAddressReferralJoinee);
		enterTextByXpath(textboxPwd, "ppounds");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj35, "sign_with_email_btn"), "login page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "profile_tab"), "home page");
		validateRequiredText(retrieveChildKeyValues(jsonObj1, "profile_tab"), "profile page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj17, "hello_user_txt"), "profile page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj17, "total_cashback_txt"), "profile page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj17, "payment_menu"), "profile page");

		validateRequiredText(retrieveChildKeyValues(jsonObj17, "payment_menu"), "profile page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj29, "total_cashback_earned_txt"), "payments page");
		validateRequiredText(retrieveChildKeyValues(jsonObj29, "total_cashback_earned_txt"), 1, "profit page");
		validateRequiredText(retrieveChildKeyValues(jsonObj29, "total_cashback_earned_txt"), 2, "profit page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj29, "paid_cashback_txt"), "profit page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj29, "pending_cashback_txt"), "profit page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj29, "referral_cashback_txt"), "profit page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj29, "available_cashback_txt"), "profit page");

		backButton();

		validatePartialRequiredText(retrieveChildKeyValues(jsonObj28, "tap_to_see_details_txt"), "payments page");
		validateRequiredText(retrieveChildKeyValues(jsonObj46, "cashback_earnings"), "payments page");
		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(linkEarningsInfoPaymentPage);
		validateRequiredText(retrieveChildKeyValues(jsonObj30, "message_first_txt"), "earnings info page");


		backButton();

		validateRequiredText(retrieveChildKeyValues(jsonObj46, "request_cashback_btn"), "payments page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj46, "static_text_title"), "payments page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj46, "static_text_1"), "payments page");


		clickOnRequiredText(retrieveChildKeyValues(jsonObj46, "request_cashback_btn"), "payments page");

		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(dropDownPaymentSettings);
		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(textPaytmWallet);
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "mobile_inputfield_txt"), "payment request page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "get_paid_btn"), "payment req page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "mobile_inputfield_txt"), "payment request page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "mobile_number_error_msg"), "payment request page");

		enterTextByXpath(textBoxPaytmMobileNumberPaymentRequestPage, "1");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "get_paid_btn"), "payment req page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "mobile_inputfield_txt"), "payment request page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "minimum_digits_error_msg"), "payment request page");

		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(dropDownPaymentSettings);
		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(textAmazonGiftCard);

		validateRequiredText(retrieveChildKeyValues(jsonObj53, "email_inputfield_txt"), "payment request page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "get_paid_btn"), "payment req page");
		validateRequiredText(retrieveChildKeyValues(jsonObj53, "email_inputfield_txt"), "payment request page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "email_error_msg"), "payment request page");

		enterTextByXpath(textBoxAmazonEmailPaymentRequestPage, "1");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "get_paid_btn"), "payment req page");
		validateRequiredText(retrieveChildKeyValues(jsonObj53, "email_inputfield_txt"), "payment request page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "minimum_characters_error_msg"), "payment request page");

		enterTextByXpath(textBoxAmazonEmailPaymentRequestPage, "asdfghjkl");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "get_paid_btn"), "payment req page");
		validateRequiredText(retrieveChildKeyValues(jsonObj53, "email_inputfield_txt"), "payment request page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "valid_email_error_msg"), "payment request page");

		enterTextByXpath(textBoxAmazonEmailPaymentRequestPage, getTestData(7, "FiftyUserCharWithSpace"));
		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "get_paid_btn"), "payment req page");
		validateRequiredText(retrieveChildKeyValues(jsonObj53, "email_inputfield_txt"), "payment request page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "maximum_characters_error_msg"), "payment request page");

		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(dropDownPaymentSettings);
		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(textNEFT);

		validateRequiredText(retrieveChildKeyValues(jsonObj40, "bank_account_inputfield_txt"), "payment request page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "bank_name_inputfield_txt"), "payment request page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "branch_name_inputfield_txt"), "payment request page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "bank_account_number_inputfield_txt"), "payment request page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "bank_ifsc_code_inputfield_txt"), "payment request page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "get_paid_btn"), "payment req page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "bank_account_inputfield_txt"), "payment request page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "account_holder_error_msg"), "payment request page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "bank_name_inputfield_txt"), "payment request page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "valid_bank_name_error_msg"), "payment request page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "branch_name_inputfield_txt"), "payment request page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "branch_minimum_digits_error_msg"), "payment request page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "bank_account_number_inputfield_txt"), "payment request page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "account_number_error_msg"), "payment request page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "bank_ifsc_code_inputfield_txt"), "payment request page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "ifsc_code_error_msg"), "payment request page");


	}

	@Test
	public void chooseLanguage_LanguageMararthiValidation_StoreCategory_StoreDetail() throws UnsupportedEncodingException, FileNotFoundException, IOException, ParseException {

		JSONObject jsonObj1 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "marathiFileName"), getTestData(16, "common_label"));
		JSONObject jsonObj3 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "marathiFileName"), getTestData(16, "main_menu"));
		JSONObject jsonObj5 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "marathiFileName"), getTestData(16, "store_category"));
		JSONObject jsonObj12 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "marathiFileName"), getTestData(16, "share_counts"));
		JSONObject jsonObj17 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "marathiFileName"), getTestData(16, "profile_right_menu"));

		reportStep("chooseLanguage_LanguageMararthiValidation_Store_Product_StoreCategory started", "INFO");

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo);

		String shareButtonWithCount = "//android.view.ViewGroup[@content-desc='txt_sharecount_open_modal']/android.view.ViewGroup/android.widget.TextView[@text='1']";
		String imageStoreCard = "//android.widget.ImageView[@content-desc='img_storeCard_imageValue']";
		String emailAddress = objCashKaroUtility.generateEmail();
		String mobileNumber = objCashKaroUtility.generateMobileNumber();	

		String otpValue = new EKOnboardingPage(driver, testInfo).
				clickOnSignUpButton().
				enterFullName().
				enterEmail(emailAddress).
				enterPassword().
				enterMobileNumber(mobileNumber).
				clickOnGetOTPButton(mobileNumber);

		new OTPPage(driver, testInfo).
		enterOTP(otpValue).
		clickOnVerifyOTP();

		new HomePage(driver, testInfo).		
		clickOnProfileIconForSignedUser().
		clickOnChooseLanguage().
		clickMarathiLink().
		clickContinueButton().
		clickRelaunchButton();

		validateRequiredText(retrieveChildKeyValues(jsonObj3, "home_lbl"), "home page");

		new HomePage(driver, testInfo).
		clickOnHamburgerIcon();

		validateRequiredText(retrieveChildKeyValues(jsonObj3, "home_lbl"), "home page");

		new SliderScreenPage(driver, testInfo).
		clickRetailerCategory().
		verifyRetailerCategoryMenuNavigation().
		clickAutomationSpecificCategoryInRetailerCategoryMenuUntilAllSubCategoryVisible().
		clickAutomationSpecificSubCategoryOne();

		validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj5, "popular"), "store category page");
		validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj5, "az"), "store category page");
		validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj5, "percent"), "store category page");
		validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj5, "amount"), "store category page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "add_to_share"), "store category page");
		validateRequiredText(retrieveChildKeyValues(jsonObj1, "share_now"), "store category page");
		validateRequiredText(retrieveChildKeyValues(jsonObj1, "remove"), "store category page");

		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(shareButtonWithCount);
		validateRequiredText(retrieveChildKeyValues(jsonObj1, "share_now"), "store category page");
		validateRequiredText(retrieveChildKeyValues(jsonObj1, "copy_Link"), "store category page");
		validateRequiredText(retrieveChildKeyValues(jsonObj12, "close"), "store category page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj12, "clear"), "store category page");

		validateRequiredText(retrieveChildKeyValues(jsonObj12, "clear"), "store category page");
		validateRequiredText(retrieveChildKeyValues(jsonObj12, "confirm_msg_clear_share"), "store category page");
		validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj17, "alert_cancel_btn"), "store category page");
		clickOnPartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj17, "alert_ok_btn"), "store category page");

		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(imageStoreCard);
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj1, "you_will_earn"), "store detail page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "add_to_share"), "store detail page");
		validateRequiredText(retrieveChildKeyValues(jsonObj1, "share_now"), "store detail page");
		validateRequiredText(retrieveChildKeyValues(jsonObj1, "remove"), "store category page");

		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(shareButtonWithCount);
		validateRequiredText(retrieveChildKeyValues(jsonObj1, "share_now"), "store detail page");
		validateRequiredText(retrieveChildKeyValues(jsonObj1, "copy_Link"), "store detail page");
		validateRequiredText(retrieveChildKeyValues(jsonObj12, "close"), "store detail page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj12, "clear"), "store detail page");

		validateRequiredText(retrieveChildKeyValues(jsonObj12, "clear"), "store detail page");
		validateRequiredText(retrieveChildKeyValues(jsonObj12, "confirm_msg_clear_share"), "store detail page");
		validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj17, "alert_cancel_btn"), "store detail page");
		clickOnPartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj17, "alert_ok_btn"), "store detail page");

		new StoreDetailPage(driver, testInfo);

	}

	@Test
	public void chooseLanguage_LanguageMararthiValidation_Product() throws UnsupportedEncodingException, FileNotFoundException, IOException, ParseException {

		JSONObject jsonObj1 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "marathiFileName"), getTestData(16, "common_label"));
		JSONObject jsonObj3 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "marathiFileName"), getTestData(16, "main_menu"));
		JSONObject jsonObj6 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "marathiFileName"), getTestData(16, "prduct_category"));
		JSONObject jsonObj9 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "marathiFileName"), getTestData(16, "product_details"));
		JSONObject jsonObj10 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "marathiFileName"), getTestData(16, "product_card"));
		JSONObject jsonObj49 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "marathiFileName"), getTestData(16, "prduct_filter"));

		reportStep("chooseLanguage_LanguageMararthiValidation_Store_Product_StoreCategory started", "INFO");

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo);

		String menFashionCategory = "//android.view.ViewGroup[@content-desc='txt_mainMenu_productDealsTop']";
		String tshirtCategory = "//android.widget.TextView[@content-desc='txt_subMenuProduct_mainMenuName']";
		String menfashionLink = "//android.widget.TextView[@content-desc='txt_filter_category_name']";
		String imageProductCard = "//android.widget.ImageView[@content-desc='img_product_image']";
		String productcard = "(//android.widget.ImageView)[3]";		
		String emailAddress = objCashKaroUtility.generateEmail();
		String mobileNumber = objCashKaroUtility.generateMobileNumber();	

		String otpValue = new EKOnboardingPage(driver, testInfo).
				clickOnSignUpButton().
				enterFullName().
				enterEmail(emailAddress).
				enterPassword().
				enterMobileNumber(mobileNumber).
				clickOnGetOTPButton(mobileNumber);

		new OTPPage(driver, testInfo).
		enterOTP(otpValue).
		clickOnVerifyOTP();

		new HomePage(driver, testInfo).		
		clickOnProfileIconForSignedUser().
		clickOnChooseLanguage().
		clickMarathiLink().
		clickContinueButton().
		clickRelaunchButton();

		validateRequiredText(retrieveChildKeyValues(jsonObj3, "home_lbl"), "home page");

		new HomePage(driver, testInfo).
		clickOnHamburgerIcon();

		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(menFashionCategory);
		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(tshirtCategory);

		clickOnPartialRequiredText(retrieveChildKeyValues(jsonObj6, "filter"), "product page");
		validateRequiredText(retrieveChildKeyValues(jsonObj49, "filter_by"), "product page");
		validateRequiredText(retrieveChildKeyValues(jsonObj49, "clerar_all"), "product page");
		validateRequiredText(retrieveChildKeyValues(jsonObj49, "close_btn"), "product page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj49, "apply_btn"), "product page");

		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(menfashionLink);
		clickOnRequiredText(retrieveChildKeyValues(jsonObj49, "clerar_all"), "product page");
		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(menfashionLink);
		clickOnRequiredText(retrieveChildKeyValues(jsonObj49, "close_btn"), "product page");
		validateRequiredText(retrieveChildKeyValues(jsonObj49, "discard_changes_title"), "product page");
		validateRequiredText(retrieveChildKeyValues(jsonObj49, "discard_changes_msg"), "product page");
		validateRequiredText(retrieveChildKeyValues(jsonObj49, "apply_changes_btn"), "product page");
		validateRequiredText(retrieveChildKeyValues(jsonObj49, "discard_changes_btn"), "product page");

		backButton();

		clickOnPartialRequiredText(retrieveChildKeyValues(jsonObj6, "sort"), "product page");
		validateRequiredText(retrieveChildKeyValues(jsonObj6, "sort_title"), "product page");
		validateRequiredText(retrieveChildKeyValues(jsonObj6, "sort_popular"), "product page");
		validateRequiredText(retrieveChildKeyValues(jsonObj6, "sort_discount"), "product page");
		validateRequiredText(retrieveChildKeyValues(jsonObj6, "sort_high_price"), "product page");
		validateRequiredText(retrieveChildKeyValues(jsonObj6, "sort_low_price"), "product page");

		backButton();

		validatePartialRequiredText(retrieveChildKeyValues(jsonObj10, "brand"), "product page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj10, "price"), "product page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj10, "sale"), "product page");
		validateRequiredText(retrieveChildKeyValues(jsonObj1, "add_to_share"), "product page");
		validateRequiredText(retrieveChildKeyValues(jsonObj1, "share_now"), "product page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj10, "see_details"), "product page");

		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(imageProductCard);
		clickOnRequiredText(retrieveChildKeyValues(jsonObj9, "seller_price"), "product detail page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "you_will_earn"), "product detail page");
		validateRequiredText(retrieveChildKeyValues(jsonObj1, "add_to_share"), "product page");
		validateRequiredText(retrieveChildKeyValues(jsonObj1, "share_now"), "product page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj9, "product_highlights"), "product detail page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj9, "similar_products"), "product detail page");



	}

	@Test
	public void chooseLanguage_LanguageGujaratiValidation_SuccessStories() throws UnsupportedEncodingException, FileNotFoundException, IOException, ParseException {

		JSONObject jsonObj1 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "gujaratiFileName"), getTestData(16, "common_label"));
		JSONObject jsonObj4 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "gujaratiFileName"), getTestData(16, "home"));
		JSONObject jsonObj17 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "gujaratiFileName"), getTestData(16, "profile_right_menu"));
		JSONObject jsonObj22 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "gujaratiFileName"), getTestData(16, "success_stories"));

		reportStep("chooseLanguage_LanguageGujaratiValidation_SuccessStories started", "INFO");

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo);

		String emailAddress = objCashKaroUtility.generateEmail();
		String mobileNumber = objCashKaroUtility.generateMobileNumber();	

		String otpValue = new EKOnboardingPage(driver, testInfo).
				clickOnSignUpButton().
				enterFullName().
				enterEmail(emailAddress).
				enterPassword().
				enterMobileNumber(mobileNumber).
				clickOnGetOTPButton(mobileNumber);

		new OTPPage(driver, testInfo).
		enterOTP(otpValue).
		clickOnVerifyOTP();

		new HomePage(driver, testInfo).		
		clickOnProfileIconForSignedUser().
		clickOnChooseLanguage().
		clickGujaratiLink().
		clickContinueButton().
		clickRelaunchButton();

		validateRequiredText(retrieveChildKeyValues(jsonObj4, "dynamic_home_top_Category"), "home page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "profile_tab"), "home page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj17, "success_stories_menu"), "profile page");
		validateRequiredText(retrieveChildKeyValues(jsonObj17, "success_stories_menu"), "success stories page");
		scrollFromUpToDowninApp();scrollFromUpToDowninApp();
		validateRequiredText(retrieveChildKeyValues(jsonObj22, "title"), "success stories page");
		validateRequiredText(retrieveChildKeyValues(jsonObj22, "testimonial_txt1"), "success stories page");
		validateRequiredText(retrieveChildKeyValues(jsonObj22, "testimonial_name1"), "success stories page");
		validateRequiredText(retrieveChildKeyValues(jsonObj22, "testimonial_txt2"), "success stories page");
		validateRequiredText(retrieveChildKeyValues(jsonObj22, "testimonial_name2"), "success stories page");
		validateRequiredText(retrieveChildKeyValues(jsonObj22, "testimonial_txt3"), "success stories page");
		validateRequiredText(retrieveChildKeyValues(jsonObj22, "testimonial_name3"), "success stories page");
		validateRequiredText(retrieveChildKeyValues(jsonObj22, "testimonial_txt4"), "success stories page");
		validateRequiredText(retrieveChildKeyValues(jsonObj22, "testimonial_name4"), "success stories page");
		validateRequiredText(retrieveChildKeyValues(jsonObj22, "testimonial_txt5"), "success stories page");
		validateRequiredText(retrieveChildKeyValues(jsonObj22, "testimonial_name5"), "success stories page");


	}

	@Test
	public void chooseLanguage_LanguageGujaratiValidation_HowItWorks() throws UnsupportedEncodingException, FileNotFoundException, IOException, ParseException {

		JSONObject jsonObj1 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "gujaratiFileName"), getTestData(16, "common_label"));
		JSONObject jsonObj4 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "gujaratiFileName"), getTestData(16, "home"));
		JSONObject jsonObj17 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "gujaratiFileName"), getTestData(16, "profile_right_menu"));
		JSONObject jsonObj23 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "gujaratiFileName"), getTestData(16, "how_it_works"));

		reportStep("chooseLanguage_LanguageGujaratiValidation_HowItWorks started", "INFO");

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo);

		String emailAddress = objCashKaroUtility.generateEmail();
		String mobileNumber = objCashKaroUtility.generateMobileNumber();	

		String otpValue = new EKOnboardingPage(driver, testInfo).
				clickOnSignUpButton().
				enterFullName().
				enterEmail(emailAddress).
				enterPassword().
				enterMobileNumber(mobileNumber).
				clickOnGetOTPButton(mobileNumber);

		new OTPPage(driver, testInfo).
		enterOTP(otpValue).
		clickOnVerifyOTP();

		new HomePage(driver, testInfo).		
		clickOnProfileIconForSignedUser().
		clickOnChooseLanguage().
		clickGujaratiLink().
		clickContinueButton().
		clickRelaunchButton();

		validateRequiredText(retrieveChildKeyValues(jsonObj4, "dynamic_home_top_Category"), "home page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "profile_tab"), "home page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj17, "how_it_works_menu"), "profile page");
		scrollFromUpToDowninApp();scrollFromUpToDowninApp();
		validateRequiredText(retrieveChildKeyValues(jsonObj17, "how_it_works_menu"), "how earnkaro works? page");
		validateRequiredText(retrieveChildKeyValues(jsonObj23, "watch_video_title1"), "how earnkaro works? page");
		validateRequiredText(retrieveChildKeyValues(jsonObj23, "watch_video_txt"), 1, "how earnkaro works? page");
		validateRequiredText(retrieveChildKeyValues(jsonObj23, "watch_video_title2"), "how earnkaro works? page");
		validateRequiredText(retrieveChildKeyValues(jsonObj23, "watch_video_txt"), 2, "how earnkaro works? page");
		validateRequiredText(retrieveChildKeyValues(jsonObj23, "watch_video_title3"), "how earnkaro works? page");
		validateRequiredText(retrieveChildKeyValues(jsonObj23, "watch_video_txt"), 3, "how earnkaro works? page");
		validateRequiredText(retrieveChildKeyValues(jsonObj23, "watch_video_title4"), "how earnkaro works? page");
		validateRequiredText(retrieveChildKeyValues(jsonObj23, "watch_video_txt"), 4, "how earnkaro works? page");
		validateRequiredText(retrieveChildKeyValues(jsonObj23, "watch_video_title5"), "how earnkaro works? page");
		validateRequiredText(retrieveChildKeyValues(jsonObj23, "watch_video_txt"), 5, "how earnkaro works? page");
		validateRequiredText(retrieveChildKeyValues(jsonObj23, "watch_video_title6"), "how earnkaro works? page");
		validateRequiredText(retrieveChildKeyValues(jsonObj23, "watch_video_txt"), 6, "how earnkaro works? page");



	}

	@Test
	public void chooseLanguage_LanguageGujaratiValidation_Testimonials() throws UnsupportedEncodingException, FileNotFoundException, IOException, ParseException {

		JSONObject jsonObj1 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "gujaratiFileName"), getTestData(16, "common_label"));
		JSONObject jsonObj4 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "gujaratiFileName"), getTestData(16, "home"));
		JSONObject jsonObj17 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "gujaratiFileName"), getTestData(16, "profile_right_menu"));
		JSONObject jsonObj24 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "gujaratiFileName"), getTestData(16, "testimonial"));
		JSONObject jsonObj25 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "gujaratiFileName"), getTestData(16, "testimonial_write"));

		reportStep("chooseLanguage_LanguageGujaratiValidation_Testimonials started", "INFO");

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo);

		String fourStarTestimonials = "(//android.widget.TextView)[8]";
		String threeStarTestimonials = "(//android.widget.TextView)[7]";
		String twoStarTestimonials = "(//android.widget.TextView)[6]";
		String oneStarTestimonials = "(//android.widget.TextView)[5]";

		String emailAddress = objCashKaroUtility.generateEmail();
		String mobileNumber = objCashKaroUtility.generateMobileNumber();	

		String otpValue = new EKOnboardingPage(driver, testInfo).
				clickOnSignUpButton().
				enterFullName().
				enterEmail(emailAddress).
				enterPassword().
				enterMobileNumber(mobileNumber).
				clickOnGetOTPButton(mobileNumber);

		new OTPPage(driver, testInfo).
		enterOTP(otpValue).
		clickOnVerifyOTP();

		new HomePage(driver, testInfo).		
		clickOnProfileIconForSignedUser().
		clickOnChooseLanguage().
		clickGujaratiLink().
		clickContinueButton().
		clickRelaunchButton();

		validateRequiredText(retrieveChildKeyValues(jsonObj4, "dynamic_home_top_Category"), "home page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "profile_tab"), "home page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj17, "rate_us_menu"), "profile page");
		validateRequiredText(retrieveChildKeyValues(jsonObj24, "count_txt"), "testimonials page");
		validateRequiredText(retrieveChildKeyValues(jsonObj24, "happy_users_txt"), "testimonials page");

		clickOnPartialRequiredText(retrieveChildKeyValues(jsonObj24, "write_testimonial_btn"), "testimonials page");
		validateRequiredText(retrieveChildKeyValues(jsonObj24, "write_testimonial_btn"), "write testimonials page");
		validateRequiredText(retrieveChildKeyValues(jsonObj25, "title"), "write testimonials page");

		validateRequiredText(retrieveChildKeyValues(jsonObj25, "rate_experience_txt"), "write testimonials page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj25, "star_txt_awesome_experience"), "write testimonials page");
		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(fourStarTestimonials);
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj25, "star_txt_good_experience"), "write testimonials page");
		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(threeStarTestimonials);
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj25, "star_txt_average"), "write testimonials page");
		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(twoStarTestimonials);
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj25, "star_txt_not_great"), "write testimonials page");
		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(oneStarTestimonials);
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj25, "star_txt_terrible"), "write testimonials page");


	}

	@Test
	public void chooseLanguage_LanguageGujaratiValidation_ContactUs() throws UnsupportedEncodingException, FileNotFoundException, IOException, ParseException {

		JSONObject jsonObj1 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "gujaratiFileName"), getTestData(16, "common_label"));
		JSONObject jsonObj2 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "gujaratiFileName"), getTestData(16, "common_validate_label"));
		JSONObject jsonObj4 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "gujaratiFileName"), getTestData(16, "home"));
		JSONObject jsonObj17 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "gujaratiFileName"), getTestData(16, "profile_right_menu"));
		JSONObject jsonObj20 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "gujaratiFileName"), getTestData(16, "contact_us"));		
		JSONObject jsonObj21 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "gujaratiFileName"), getTestData(16, "faq"));

		reportStep("chooseLanguage_LanguageGujaratiValidation_ContactUs started", "INFO");

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo);

		String textBoxUserName = "//android.widget.EditText[@content-desc='et_contactUs_yourName']";
		String textBoxEmail = "//android.widget.EditText[@content-desc='et_contactUs_email']";

		String emailAddress = objCashKaroUtility.generateEmail();
		String mobileNumber = objCashKaroUtility.generateMobileNumber();	

		String otpValue = new EKOnboardingPage(driver, testInfo).
				clickOnSignUpButton().
				enterFullName().
				enterEmail(emailAddress).
				enterPassword().
				enterMobileNumber(mobileNumber).
				clickOnGetOTPButton(mobileNumber);

		new OTPPage(driver, testInfo).
		enterOTP(otpValue).
		clickOnVerifyOTP();

		new HomePage(driver, testInfo).		
		clickOnProfileIconForSignedUser().
		clickOnChooseLanguage().
		clickGujaratiLink().
		clickContinueButton().
		clickRelaunchButton();

		validateRequiredText(retrieveChildKeyValues(jsonObj4, "dynamic_home_top_Category"), "home page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "profile_tab"), "home page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj17, "help"), "profile page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj17, "contact_us_menu"), "help page");
		enterTextByXpath(textBoxUserName, "..");
		enterTextByXpath(textBoxEmail, "..");
		validateRequiredText(retrieveChildKeyValues(jsonObj21, "faq_btn"), "contact us page");
		validateRequiredText(retrieveChildKeyValues(jsonObj20, "name_inputfield_txt"), "contact us page");
		validateRequiredText(retrieveChildKeyValues(jsonObj20, "email_inputfield_txt"), "contact us page");
		validateRequiredText(retrieveChildKeyValues(jsonObj20, "message_inputfield_txt"), "contact us page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj20, "submit_btn"), "contact us page");

		validateRequiredText(retrieveChildKeyValues(jsonObj2, "select_subject_error_msg"), "contact us page");
		validateRequiredText(retrieveChildKeyValues(jsonObj20, "name_inputfield_txt"), "contact us page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "valid_name_error_msg"), "contact us page");
		validateRequiredText(retrieveChildKeyValues(jsonObj20, "email_inputfield_txt"), "contact us page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "valid_email_error_msg"), "contact us page");
		validateRequiredText(retrieveChildKeyValues(jsonObj20, "message_inputfield_txt"), "contact us page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "valid_details_error_msg"), "contact us page");

		enterTextByXpath(textBoxUserName, getTestData(7, "oneUserChar"));
		enterTextByXpath(textBoxEmail, getTestData(7, "oneUserChar"));
		clickOnRequiredText(retrieveChildKeyValues(jsonObj20, "submit_btn"), "contact us page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "select_subject_error_msg"), "contact us page");
		validateRequiredText(retrieveChildKeyValues(jsonObj20, "name_inputfield_txt"), "contact us page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "minimum_characters_error_msg"), 1, "contact us page");
		validateRequiredText(retrieveChildKeyValues(jsonObj20, "email_inputfield_txt"), "contact us page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "minimum_characters_error_msg"), 2, "contact us page");
		validateRequiredText(retrieveChildKeyValues(jsonObj20, "message_inputfield_txt"), "contact us page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "valid_details_error_msg"), "contact us page");

		enterTextByXpath(textBoxUserName, getTestData(7, "FiftyPlusUserChar"));
		enterTextByXpath(textBoxEmail, getTestData(7, "FiftyPlusUserChar"));
		clickOnRequiredText(retrieveChildKeyValues(jsonObj20, "submit_btn"), "contact us page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "select_subject_error_msg"), "contact us page");
		validateRequiredText(retrieveChildKeyValues(jsonObj20, "name_inputfield_txt"), "contact us page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "maximum_characters_error_msg"), 1, "contact us page");
		validateRequiredText(retrieveChildKeyValues(jsonObj20, "email_inputfield_txt"), "contact us page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "maximum_characters_error_msg"), 2, "contact us page");
		validateRequiredText(retrieveChildKeyValues(jsonObj20, "message_inputfield_txt"), "contact us page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "valid_details_error_msg"), "contact us page");

	}

	@Test
	public void chooseLanguage_LanguageGujaratiValidation_FAQs() throws UnsupportedEncodingException, FileNotFoundException, IOException, ParseException {

		JSONObject jsonObj1 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "gujaratiFileName"), getTestData(16, "common_label"));
		JSONObject jsonObj4 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "gujaratiFileName"), getTestData(16, "home"));
		JSONObject jsonObj17 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "gujaratiFileName"), getTestData(16, "profile_right_menu"));		
		JSONObject jsonObj21 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "gujaratiFileName"), getTestData(16, "faq"));

		reportStep("chooseLanguage_LanguageGujaratiValidation_FAQs started", "INFO");

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo);

		String emailAddress = objCashKaroUtility.generateEmail();
		String mobileNumber = objCashKaroUtility.generateMobileNumber();	

		String otpValue = new EKOnboardingPage(driver, testInfo).
				clickOnSignUpButton().
				enterFullName().
				enterEmail(emailAddress).
				enterPassword().
				enterMobileNumber(mobileNumber).
				clickOnGetOTPButton(mobileNumber);

		new OTPPage(driver, testInfo).
		enterOTP(otpValue).
		clickOnVerifyOTP();

		new HomePage(driver, testInfo).		
		clickOnProfileIconForSignedUser().
		clickOnChooseLanguage().
		clickGujaratiLink().
		clickContinueButton().
		clickRelaunchButton();

		validateRequiredText(retrieveChildKeyValues(jsonObj4, "dynamic_home_top_Category"), "home page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "profile_tab"), "home page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj17, "help"), "profile page");

		validateRequiredText(retrieveChildKeyValues(jsonObj17, "call_us_menu"), "help page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj17, "faq_menu"), "help page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj21, "question1"), "faq page");
		//validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj21, "answer1"), "faq page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj21, "question2"), "faq page");
		//validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj21, "answer2"), "faq page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj21, "question3"), "faq page");
		//validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj21, "answer3"), "faq page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj21, "question4"), "faq page");
		//validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj21, "answer4"), "faq page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj21, "question5"), "faq page");
		//validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj21, "answer5"), "faq page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj21, "question6"), "faq page");
		//validatePartialRequiredText(retrieveChildKeyValues(jsonObj21, "answer6"), "faq page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj21, "question7"), "faq page");
		//validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj21, "answer7"), "faq page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj21, "question8"), "faq page");
		//validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj21, "answer8"), "faq page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj21, "question9"), "faq page");
		//validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj21, "answer9"), "faq page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj21, "question10"), "faq page");
		//validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj21, "answer10"), "faq page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj21, "question11"), "faq page");
		//validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj21, "answer11"), "faq page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj21, "question12"), "faq page");
		//validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj21, "answer12"), "faq page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj21, "question13"), "faq page");
		//validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj21, "answer13"), "faq page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj21, "question14"), "faq page");
		//validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj21, "answer14"), "faq page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj21, "question15"), "faq page");
		//validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj21, "answer15"), "faq page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj21, "question16"), "faq page");
		//validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj21, "answer16"), "faq page");

		validateRequiredText(retrieveChildKeyValues(jsonObj21, "faq_btn"), "faq page");


	}

	@Test
	public void chooseLanguage_LanguageGujaratiValidation_History_NewUser() throws UnsupportedEncodingException, FileNotFoundException, IOException, ParseException {

		JSONObject jsonObj1 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "gujaratiFileName"), getTestData(16, "common_label"));
		JSONObject jsonObj4 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "gujaratiFileName"), getTestData(16, "home"));
		JSONObject jsonObj17 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "gujaratiFileName"), getTestData(16, "profile_right_menu"));
		JSONObject jsonObj52 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "gujaratiFileName"), getTestData(16, "my_shared_activities"));

		reportStep("chooseLanguage_LanguageGujaratiValidation_History_NewUser started", "INFO");

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo);

		String emailAddress = objCashKaroUtility.generateEmail();
		String mobileNumber = objCashKaroUtility.generateMobileNumber();	

		String otpValue = new EKOnboardingPage(driver, testInfo).
				clickOnSignUpButton().
				enterFullName().
				enterEmail(emailAddress).
				enterPassword().
				enterMobileNumber(mobileNumber).
				clickOnGetOTPButton(mobileNumber);

		new OTPPage(driver, testInfo).
		enterOTP(otpValue).
		clickOnVerifyOTP();

		new HomePage(driver, testInfo).		
		clickOnProfileIconForSignedUser().
		clickOnChooseLanguage().
		clickGujaratiLink().
		clickContinueButton().
		clickRelaunchButton();

		validateRequiredText(retrieveChildKeyValues(jsonObj4, "dynamic_home_top_Category"), "home page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "profile_tab"), "home page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj17, "history_menu"), "profile page");
		validateRequiredText(retrieveChildKeyValues(jsonObj17, "recent_clicks_menu"), "history page");
		validateRequiredText(retrieveChildKeyValues(jsonObj1, "whoops_msg"), "history page");
		validateRequiredText(retrieveChildKeyValues(jsonObj52, "no_shared_activites"), "history page");
		validateRequiredText(retrieveChildKeyValues(jsonObj52, "saving_click_away"), "history page");


	}

	@Test
	public void chooseLanguage_LanguageGujaratiValidation_AccountSettings() throws UnsupportedEncodingException, FileNotFoundException, IOException, ParseException {

		JSONObject jsonObj1 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "gujaratiFileName"), getTestData(16, "common_label"));
		JSONObject jsonObj2 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "gujaratiFileName"), getTestData(16, "common_validate_label"));
		JSONObject jsonObj17 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "gujaratiFileName"), getTestData(16, "profile_right_menu"));
		JSONObject jsonObj26 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "gujaratiFileName"), getTestData(16, "personal_details"));
		JSONObject jsonObj27 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "gujaratiFileName"), getTestData(16, "change_password"));
		JSONObject jsonObj40 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "gujaratiFileName"), getTestData(16, "giftcard_wallet_inputfield"));

		reportStep("chooseLanguage_LanguageGujaratiValidation_AccountSettings started", "INFO");

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo);

		String textBoxExistingPwd = "//android.widget.EditText[@content-desc='et_changePassword_currentPassword']";
		String textBoxNewPwd = "//android.widget.EditText[@content-desc='et_changePassword_fullName']";
		String textBoxConfirmNewPwd = "//android.widget.EditText[@content-desc='et_changePassword_confirmPassword']";

		String dropDownPaymentSettings = "(//android.widget.EditText)[1]";
		String textNEFT = "//android.widget.CheckedTextView[@text='Bank Payment (NEFT)']";
		String textPaytmWallet = "//android.widget.CheckedTextView[@text='Paytm Wallet']";
		String textAmazonGiftCard = "//android.widget.CheckedTextView[@text='Amazon Gift Card']";

		String textBoxPaytmMobileNumber = "//android.widget.EditText[@content-desc='et_settingsPaytm_mobile']";
		String textBoxPaytmPassword = "//android.widget.EditText[@content-desc='et_settingsPaytm_password']";

		String textBoxAmazonEmail = "//android.widget.EditText[@content-desc='et_settingsAmazon_email']";
		String textBoxAmazonPassword = "//android.widget.EditText[@content-desc='et_settingsAmazon_password']";
		String emailAddress = objCashKaroUtility.generateEmail();
		String mobileNumber = objCashKaroUtility.generateMobileNumber();	

		String otpValue = new EKOnboardingPage(driver, testInfo).
				clickOnSignUpButton().
				enterFullName().
				enterEmail(emailAddress).
				enterPassword().
				enterMobileNumber(mobileNumber).
				clickOnGetOTPButton(mobileNumber);

		new OTPPage(driver, testInfo).
		enterOTP(otpValue).
		clickOnVerifyOTP();

		new HomePage(driver, testInfo).		
		clickOnProfileIconForSignedUser().
		clickOnChooseLanguage().
		clickGujaratiLink().
		clickContinueButton().
		clickRelaunchButton();

		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "profile_tab"), "home page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj17, "account_setting_menu"), "profile page");
		validateRequiredText(retrieveChildKeyValues(jsonObj17, "account_setting_menu"), "profile page");
		clickOnPartialRequiredText(retrieveChildKeyValues(jsonObj1, "personal_details_tab"), "account settings page");
		scrollFromUpToDowninApp();scrollFromUpToDowninApp();
		validateRequiredText(retrieveChildKeyValues(jsonObj26, "name_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj26, "email_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj26, "mobile_number_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj26, "receive_newsletter_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj26, "receive_referral_earning_txt"), "account settings page");
		validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj26, "note_txt"), "account settings page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "save_changes_btn"), "account settings page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj26, "no_value_change_error_msg"), "account settings page");

		clickOnPartialRequiredText(retrieveChildKeyValues(jsonObj1, "change_password_tab"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj27, "change_password_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj27, "new_password_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj27, "confirm_password_inputfield_txt"), "account settings page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "save_changes_btn"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj27, "change_password_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "current_password_error_msg"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj27, "new_password_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "new_password_error_msg"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj27, "confirm_password_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "confirm_password_error_msg"), "account settings page");

		enterTextByXpath(textBoxExistingPwd, getTestData(7, "oneUserChar"));
		enterTextByXpath(textBoxNewPwd, getTestData(7, "oneUserChar"));
		enterTextByXpath(textBoxConfirmNewPwd, getTestData(7, "oneUserChar"));
		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "save_changes_btn"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj27, "change_password_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "password_minimum_characters_error_msg"), 1, "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj27, "new_password_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "password_minimum_characters_error_msg"), 2, "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj27, "confirm_password_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "password_minimum_characters_error_msg"), 3, "account settings page");

		enterTextByXpath(textBoxExistingPwd, getTestData(7, "FiftyUserChar"));
		enterTextByXpath(textBoxNewPwd, getTestData(7, "FiftyUserChar"));
		enterTextByXpath(textBoxConfirmNewPwd, getTestData(7, "FiftyUserChar"));
		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "save_changes_btn"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj27, "change_password_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "password_maximum_characters_error_msg"), 1, "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj27, "new_password_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "password_maximum_characters_error_msg"), 2, "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj27, "confirm_password_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "password_maximum_characters_error_msg"), 3, "account settings page");


		enterTextByXpath(textBoxExistingPwd, getTestData(0, "TC001_ValidLoginPassword"));
		enterTextByXpath(textBoxNewPwd, getTestData(0, "TC001_ValidLoginPassword"));
		enterTextByXpath(textBoxConfirmNewPwd, getTestData(0, "TC001_InValidPassword"));
		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "save_changes_btn"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj27, "change_password_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj27, "new_password_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj27, "confirm_password_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "password_not_match_error_msg"), "account settings page");

		clickOnPartialRequiredText(retrieveChildKeyValues(jsonObj1, "payment_settings_tab"), "account settings page");

		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(dropDownPaymentSettings);
		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(textPaytmWallet);
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "mobile_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "confirm_password_inputfield_txt"), "account settings page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "save_changes_btn"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "mobile_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "mobile_number_error_msg"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "confirm_password_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "password_error_msg"), "account settings page");

		enterTextByXpath(textBoxPaytmMobileNumber, "1");
		enterTextByXpath(textBoxPaytmPassword, "1");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "save_changes_btn"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "mobile_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "minimum_digits_error_msg"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "confirm_password_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "password_minimum_characters_error_msg"), "account settings page");

		enterTextByXpath(textBoxPaytmPassword, getTestData(7, "FiftyUserChar"));
		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "save_changes_btn"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "confirm_password_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "password_maximum_characters_error_msg"), "account settings page");

		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(dropDownPaymentSettings);
		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(textAmazonGiftCard);

		validateRequiredText(retrieveChildKeyValues(jsonObj40, "email_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "confirm_password_inputfield_txt"), "account settings page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "save_changes_btn"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "email_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "email_error_msg"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "confirm_password_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "password_error_msg"), "account settings page");

		enterTextByXpath(textBoxAmazonEmail, "1");
		enterTextByXpath(textBoxAmazonPassword, "1");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "save_changes_btn"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "email_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "minimum_characters_error_msg"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "confirm_password_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "password_minimum_characters_error_msg"), "account settings page");

		enterTextByXpath(textBoxAmazonEmail, "asdfghjkl");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "save_changes_btn"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "email_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "valid_email_error_msg"), "account settings page");

		enterTextByXpath(textBoxAmazonEmail, getTestData(7, "FiftyUserCharWithSpace"));
		enterTextByXpath(textBoxAmazonPassword, getTestData(7, "FiftyUserChar"));
		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "save_changes_btn"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "email_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "maximum_characters_error_msg"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "confirm_password_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "maximum_characters_error_msg"), "account settings page");

		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(dropDownPaymentSettings);
		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(textNEFT);

		validateRequiredText(retrieveChildKeyValues(jsonObj40, "bank_account_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "bank_name_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "branch_name_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "bank_account_number_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "bank_ifsc_code_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "confirm_password_inputfield_txt"), "account settings page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "save_changes_btn"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "bank_account_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "account_holder_error_msg"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "bank_name_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "valid_bank_name_error_msg"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "branch_name_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "branch_minimum_digits_error_msg"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "bank_account_number_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "account_number_error_msg"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "bank_ifsc_code_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "ifsc_code_error_msg"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "confirm_password_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "password_error_msg"), "account settings page");

	}

	@Test
	public void chooseLanguage_LanguageGujaratiValidation_ReferAndEarn() throws UnsupportedEncodingException, FileNotFoundException, IOException, ParseException {

		JSONObject jsonObj1 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "gujaratiFileName"), getTestData(16, "common_label"));
		JSONObject jsonObj17 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "gujaratiFileName"), getTestData(16, "profile_right_menu"));
		JSONObject jsonObj18 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "gujaratiFileName"), getTestData(16, "referandearn"));

		reportStep("chooseLanguage_LanguageGujaratiValidation_AccountSettings started", "INFO");

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo);

		String emailAddress = objCashKaroUtility.generateEmail();
		String mobileNumber = objCashKaroUtility.generateMobileNumber();	

		String otpValue = new EKOnboardingPage(driver, testInfo).
				clickOnSignUpButton().
				enterFullName().
				enterEmail(emailAddress).
				enterPassword().
				enterMobileNumber(mobileNumber).
				clickOnGetOTPButton(mobileNumber);

		new OTPPage(driver, testInfo).
		enterOTP(otpValue).
		clickOnVerifyOTP();

		new HomePage(driver, testInfo).		
		clickOnProfileIconForSignedUser().
		clickOnChooseLanguage().
		clickGujaratiLink().
		clickContinueButton().
		clickRelaunchButton();

		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "profile_tab"), "home page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj17, "my_referral_menu"), "profile page");
		validateRequiredText(retrieveChildKeyValues(jsonObj17, "my_referral_menu"), "my referral page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj17, "referandearn_menu"), "my referral page");
		validateRequiredText(retrieveChildKeyValues(jsonObj17, "referandearn_menu"), "my referral page");
		scrollFromUpToDowninApp();scrollFromUpToDowninApp();
		validateRequiredText(retrieveChildKeyValues(jsonObj18, "title"), "my referral page");
		validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj18, "sub_title"), "my referral page");
		validateRequiredText(retrieveChildKeyValues(jsonObj18, "message"), "my referral page");
		validateRequiredText(retrieveChildKeyValues(jsonObj18, "referral_link_title"), "my referral page");
		validateRequiredText(retrieveChildKeyValues(jsonObj18, "copy_message_title"), "my referral page");
		validateRequiredText(retrieveChildKeyValues(jsonObj18, "tap_copy"), "my referral page");
		validateRequiredText(retrieveChildKeyValues(jsonObj18, "invite_title"), "my referral page");


	}

	@Test
	public void chooseLanguage_LanguageGujaratiValidation_ReferralNetwork() throws UnsupportedEncodingException, FileNotFoundException, IOException, ParseException {

		JSONObject jsonObj1 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "gujaratiFileName"), getTestData(16, "common_label"));
		JSONObject jsonObj17 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "gujaratiFileName"), getTestData(16, "profile_right_menu"));
		JSONObject jsonObj19 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "gujaratiFileName"), getTestData(16, "referral_network"));

		reportStep("chooseLanguage_LanguageGujaratiValidation_AccountSettings started", "INFO");

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo);

		String emailAddress = objCashKaroUtility.generateEmail();
		String mobileNumber = objCashKaroUtility.generateMobileNumber();	

		String otpValue = new EKOnboardingPage(driver, testInfo).
				clickOnSignUpButton().
				enterFullName().
				enterEmail(emailAddress).
				enterPassword().
				enterMobileNumber(mobileNumber).
				clickOnGetOTPButton(mobileNumber);

		new OTPPage(driver, testInfo).
		enterOTP(otpValue).
		clickOnVerifyOTP();

		new HomePage(driver, testInfo).		
		clickOnProfileIconForSignedUser().
		clickOnChooseLanguage().
		clickGujaratiLink().
		clickContinueButton().
		clickRelaunchButton();

		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "profile_tab"), "home page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj17, "my_referral_menu"), "profile page");
		validateRequiredText(retrieveChildKeyValues(jsonObj17, "my_referral_menu"), "my referral page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj17, "referral_network_menu"), "profile page");
		validateRequiredText(retrieveChildKeyValues(jsonObj17, "referral_network_menu"), "referral nw page");
		validateRequiredText(retrieveChildKeyValues(jsonObj19, "title"), "referral nw page");
		validateRequiredText(retrieveChildKeyValues(jsonObj19, "total_referral_earn_txt"), "referral nw page");
		validateRequiredText(retrieveChildKeyValues(jsonObj19, "friend_joined_txt"), "referral nw page");
		validateRequiredText(retrieveChildKeyValues(jsonObj19, "no_referral_title"), "referral nw page");
		validateRequiredText(retrieveChildKeyValues(jsonObj19, "no_referral_message"), "referral nw page");
		validateRequiredText(retrieveChildKeyValues(jsonObj19, "refer_earn_btn"), "referral nw page");


	}

	@Test
	public void chooseLanguage_LanguageGujaratiValidation_MyEarnings_NewUser() throws UnsupportedEncodingException, FileNotFoundException, IOException, ParseException {

		JSONObject jsonObj1 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "gujaratiFileName"), getTestData(16, "common_label"));
		JSONObject jsonObj4 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "gujaratiFileName"), getTestData(16, "home"));
		JSONObject jsonObj17 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "gujaratiFileName"), getTestData(16, "profile_right_menu"));
		JSONObject jsonObj28 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "gujaratiFileName"), getTestData(16, "my_account_earnings"));
		JSONObject jsonObj30 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "gujaratiFileName"), getTestData(16, "earnings_info"));

		JSONObject jsonObj33 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "gujaratiFileName"), getTestData(16, "my_earnings"));

		reportStep("chooseLanguage_LanguageGujaratiValidation_MyEarnings_NewUser started", "INFO");

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo);

		String linkEarningsInfo = "//android.widget.TextView[@content-desc='txt_myEarnings_cashbackEarningsInfo']";

		String emailAddress = objCashKaroUtility.generateEmail();
		String mobileNumber = objCashKaroUtility.generateMobileNumber();	

		String otpValue = new EKOnboardingPage(driver, testInfo).
				clickOnSignUpButton().
				enterFullName().
				enterEmail(emailAddress).
				enterPassword().
				enterMobileNumber(mobileNumber).
				clickOnGetOTPButton(mobileNumber);

		new OTPPage(driver, testInfo).
		enterOTP(otpValue).
		clickOnVerifyOTP();

		new HomePage(driver, testInfo).		
		clickOnProfileIconForSignedUser().
		clickOnChooseLanguage().
		clickGujaratiLink().
		clickContinueButton().
		clickRelaunchButton();

		validateRequiredText(retrieveChildKeyValues(jsonObj4, "dynamic_home_top_Category"), "home page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "profile_tab"), "home page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj17, "my_earnings_menu"), "profile page");
		validateRequiredText(retrieveChildKeyValues(jsonObj17, "my_earnings_menu"), "my earnings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj28, "total_earning_txt"), "my earnings page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj33, "cashback_tap"), "my earnings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj33, "cashback_earning_title"), "my earnings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj33, "request_cashback_btn"), "my earnings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj33, "no_cashback_yet_txt"), "my earnings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj33, "shop_today_txt"), "my earnings page");

		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(linkEarningsInfo);
		validateRequiredText(retrieveChildKeyValues(jsonObj30, "message_first_txt"), "earnings info page");


		backButton();

		clickOnRequiredText(retrieveChildKeyValues(jsonObj33, "referral_tap"), "my earnings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj33, "referral_earning_title"), "my earnings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj33, "request_cashback_btn"), "my earnings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj33, "no_referral_yet_txt"), "my earnings page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj33, "no_referral_yet_sub_txt"), "my earnings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj33, "no_referral_invite_some_friends"), "my earnings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj33, "refer_now_btn"), "my earnings page");


	}

	@Test
	public void chooseLanguage_LanguageGujaratiValidation_MakeLinks_NewUser() throws UnsupportedEncodingException, FileNotFoundException, IOException, ParseException {

		JSONObject jsonObj1 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "gujaratiFileName"), getTestData(16, "common_label"));
		JSONObject jsonObj2 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "gujaratiFileName"), getTestData(16, "common_validate_label"));
		JSONObject jsonObj4 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "gujaratiFileName"), getTestData(16, "home"));
		JSONObject jsonObj17 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "gujaratiFileName"), getTestData(16, "profile_right_menu"));
		JSONObject jsonObj51 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "gujaratiFileName"), getTestData(16, "make_profit_links"));
		JSONObject jsonObj53 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "gujaratiFileName"), getTestData(16, "profit_partners_details"));
		JSONObject jsonObj54 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "gujaratiFileName"), getTestData(16, "profit_partners_list"));

		reportStep("chooseLanguage_LanguageGujaratiValidation_MakeLinks_NewUser started", "INFO");

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo);

		String imagePartner = "//android.widget.ImageView[@content-desc='img_profitPartnersList_partnersIMG']";
		String emailAddress = objCashKaroUtility.generateEmail();
		String mobileNumber = objCashKaroUtility.generateMobileNumber();	

		String otpValue = new EKOnboardingPage(driver, testInfo).
				clickOnSignUpButton().
				enterFullName().
				enterEmail(emailAddress).
				enterPassword().
				enterMobileNumber(mobileNumber).
				clickOnGetOTPButton(mobileNumber);

		new OTPPage(driver, testInfo).
		enterOTP(otpValue).
		clickOnVerifyOTP();

		new HomePage(driver, testInfo).		
		clickOnProfileIconForSignedUser().
		clickOnChooseLanguage().
		clickGujaratiLink().
		clickContinueButton().
		clickRelaunchButton();

		validateRequiredText(retrieveChildKeyValues(jsonObj4, "dynamic_home_top_Category"), "home page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "profile_tab"), "home page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj17, "make_profit_link_menu"), "profile page");
		validateRequiredText(retrieveChildKeyValues(jsonObj51, "title"), "make links page");
		validateRequiredText(retrieveChildKeyValues(jsonObj51, "subtitle"), "make links page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj51, "see_partner_txt"), "make links page");
		validateRequiredText(retrieveChildKeyValues(jsonObj54, "toolbar_profit_partners_title"), "partners page");
		validateRequiredText(retrieveChildKeyValues(jsonObj54, "profit_partners_information"), "partners page");

		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(imagePartner);
		validateRequiredText(retrieveChildKeyValues(jsonObj53, "profit_rates"), "store page");

		backButton();
		backButton();

		validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj51, "paste_link"), "make links page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj51, "make_profit_link_txt"), "make links page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "url_error_msg"), "make links page");
		validateRequiredText(retrieveChildKeyValues(jsonObj51, "how_make_profit_txt"), "make links page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "url_error_msg"), "make links page");		

		//Here no need to scroll : since the swiping happens from left to right
		scrollFromDownToUpinApp();
		validateRequiredText_WithOutScrolling(retrieveChildKeyValues(jsonObj51, "step_one_title"), "make links page");
		validateRequiredText_WithOutScrolling(retrieveChildKeyValues(jsonObj51, "step_one_txt"), "make links page");
		validateRequiredText_WithOutScrolling(retrieveChildKeyValues(jsonObj51, "step_two_title"), "make links page");
		validateRequiredText_WithOutScrolling(retrieveChildKeyValues(jsonObj51, "step_two_txt"), "make links page");
		validateRequiredText_WithOutScrolling(retrieveChildKeyValues(jsonObj51, "step_three_title"), "make links page");
		validateRequiredText_WithOutScrolling(retrieveChildKeyValues(jsonObj51, "step_three_txt"), "make links page");
		validateRequiredText_WithOutScrolling(retrieveChildKeyValues(jsonObj51, "step_four_title"), "make links page");
		validateRequiredText_WithOutScrolling(retrieveChildKeyValues(jsonObj51, "step_four_txt"), "make links page");


	}

	@Test
	public void chooseLanguage_LanguageGujaratiValidation_GetStarted_Joinfree_TermsAndConditions() throws UnsupportedEncodingException, FileNotFoundException, IOException, ParseException {

		JSONObject jsonObj1 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "gujaratiFileName"), getTestData(16, "common_label"));
		JSONObject jsonObj2 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "gujaratiFileName"), getTestData(16, "common_validate_label"));
		JSONObject jsonObj17 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "gujaratiFileName"), getTestData(16, "profile_right_menu"));
		JSONObject jsonObj34 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "gujaratiFileName"), getTestData(16, "on_boarding"));
		JSONObject jsonObj36 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "gujaratiFileName"), getTestData(16, "join_free"));
		JSONObject jsonObj50 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "gujaratiFileName"), getTestData(16, "terms_conditions"));

		reportStep("chooseLanguage_LanguageGujaratiValidation_GetStarted_Joinfree_Login_ForgotPassword started", "INFO");

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo);

		String textboxName = "//android.widget.EditText[@content-desc='et_signup_fullname']";
		String textboxEmail = "//android.widget.EditText[@content-desc='et_signup_email']|//android.widget.EditText[@content-desc='et_signin_email']|//android.widget.EditText[@content-desc='et_forgotpassword_email']";
		String textboxPwd = "//android.widget.EditText[@content-desc='et_signup_password']|//android.widget.EditText[@content-desc='et_signin_password']";
		String textboxMobileNumber = "//android.widget.EditText[@content-desc='et_signup_mobile']";		
		String emailAddress = objCashKaroUtility.generateEmail();
		String mobileNumber = objCashKaroUtility.generateMobileNumber();	

		String otpValue = new EKOnboardingPage(driver, testInfo).
				clickOnSignUpButton().
				enterFullName().
				enterEmail(emailAddress).
				enterPassword().
				enterMobileNumber(mobileNumber).
				clickOnGetOTPButton(mobileNumber);

		new OTPPage(driver, testInfo).
		enterOTP(otpValue).
		clickOnVerifyOTP();

		new HomePage(driver, testInfo).		
		clickOnProfileIconForSignedUser().
		clickOnChooseLanguage().
		clickGujaratiLink().
		clickContinueButton().
		clickRelaunchButton();

		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "profile_tab"), "home page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj17, "logout_menu"), "profile page");
		validateRequiredText(retrieveChildKeyValues(jsonObj17, "alert_logout"), "logout popup");
		validateRequiredText(retrieveChildKeyValues(jsonObj17, "alert_logout_msg"), "logout popup");
		validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj17, "alert_cancel_btn"), "logout popup");
		clickOnPartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj17, "alert_ok_btn"), "logout popup");

		new EKOnboardingPage(driver, testInfo).
		clickOnSignUpButton();

		validateRequiredText(retrieveChildKeyValues(jsonObj17, "join_free_menu"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "title"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "subtitle"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "join_facebook_txt"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "join_or_txt"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "name_inputfield_txt"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "email_inputfield_txt"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "password_inputfield_txt"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "mobile_inputfield_txt"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "otp_btn"), "join free page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj36, "already_register"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "sign_in_txt"), "join free page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj36, "signup_terms_txt"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "terms_condition_txt"), "join free page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj36, "otp_btn"), "join free page");		
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "name_inputfield_txt"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "name_error_msg"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "email_inputfield_txt"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "email_error_msg"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "password_inputfield_txt"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "password_error_msg"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "mobile_inputfield_txt"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "mobile_number_error_msg"), "join free page");

		enterTextByXpath(textboxName, getTestData(7, "oneUserChar"));
		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(textboxEmail);
		clickOnNoneOFTheAbove();
		enterTextByXpath(textboxEmail, getTestData(7, "oneUserChar"));
		enterTextByXpath(textboxPwd, getTestData(7, "oneUserChar"));
		enterTextByXpath(textboxMobileNumber, getTestData(7, "oneUserChar"));
		clickOnRequiredText(retrieveChildKeyValues(jsonObj36, "otp_btn"), "join free page");		
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "name_inputfield_txt"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "minimum_characters_error_msg"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "email_inputfield_txt"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "minimum_characters_error_msg"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "password_inputfield_txt"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "password_minimum_characters_error_msg"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "mobile_inputfield_txt"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "minimum_digits_error_msg"), "join free page");

		enterTextByXpath(textboxName, getTestData(7, "FiftyUserCharWithSpace"));
		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(textboxEmail);
		clickOnNoneOFTheAbove();
		enterTextByXpath(textboxEmail, getTestData(7, "FiftyUserCharWithSpace"));
		enterTextByXpath(textboxPwd, getTestData(7, "FiftyUserChar"));
		enterTextByXpath(textboxMobileNumber, "2222222222");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj36, "otp_btn"), "join free page");		
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "name_inputfield_txt"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "maximum_characters_error_msg"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "email_inputfield_txt"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "maximum_characters_error_msg"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "password_inputfield_txt"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "password_maximum_characters_error_msg"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "mobile_inputfield_txt"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "valid_mobile_number_error_msg"), "join free page");

		enterTextByXpath(textboxName, "2*2");
		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(textboxEmail);
		clickOnNoneOFTheAbove();
		enterTextByXpath(textboxEmail, "2*2");
		enterTextByXpath(textboxPwd, "2 2 2 2 2");
		enterTextByXpath(textboxMobileNumber, "2*22222222");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj36, "otp_btn"), "join free page");		
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "name_inputfield_txt"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "valid_name_error_msg"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "email_inputfield_txt"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "valid_email_error_msg"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "password_inputfield_txt"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "valid_password_error_msg"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "mobile_inputfield_txt"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "valid_mobile_number_error_msg"), "join free page");

		backButton();

		new EKOnboardingPage(driver, testInfo).
		clickOnSignUpButton();
		clickOnRequiredText(retrieveChildKeyValues(jsonObj36, "terms_condition_txt"), "join free page");
		scrollFromUpToDowninApp();scrollFromUpToDowninApp();
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "terms_condition_txt"), "terms & conditions page");
		validateRequiredText(retrieveChildKeyValues(jsonObj50, "title"), "terms & conditions page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj50, "introduction_title"), "terms & conditions page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj50, "introduction_details"), "terms & conditions page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj50, "membership_title"), "terms & conditions page");
		validateRequiredText(retrieveChildKeyValues(jsonObj50, "membership_details"), "terms & conditions page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj50, "our_service_title"), "terms & conditions page");
		validateRequiredText(retrieveChildKeyValues(jsonObj50, "our_service_details"), "terms & conditions page");


	}

	@Test
	public void chooseLanguage_LanguageGujaratiValidation_Login() throws UnsupportedEncodingException, FileNotFoundException, IOException, ParseException {

		JSONObject jsonObj1 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "gujaratiFileName"), getTestData(16, "common_label"));
		JSONObject jsonObj2 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "gujaratiFileName"), getTestData(16, "common_validate_label"));
		JSONObject jsonObj17 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "gujaratiFileName"), getTestData(16, "profile_right_menu"));
		JSONObject jsonObj35 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "gujaratiFileName"), getTestData(16, "sign_in"));
		JSONObject jsonObj36 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "gujaratiFileName"), getTestData(16, "join_free"));

		reportStep("chooseLanguage_LanguageGujaratiValidation_GetStarted_Joinfree_Login_ForgotPassword started", "INFO");

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo);

		String textboxEmail = "//android.widget.EditText[@content-desc='et_signup_email']|//android.widget.EditText[@content-desc='et_signin_email']|//android.widget.EditText[@content-desc='et_forgotpassword_email']";
		String textboxPwd = "//android.widget.EditText[@content-desc='et_signup_password']|//android.widget.EditText[@content-desc='et_signin_password']";
		String emailAddress = objCashKaroUtility.generateEmail();
		String mobileNumber = objCashKaroUtility.generateMobileNumber();	

		String otpValue = new EKOnboardingPage(driver, testInfo).
				clickOnSignUpButton().
				enterFullName().
				enterEmail(emailAddress).
				enterPassword().
				enterMobileNumber(mobileNumber).
				clickOnGetOTPButton(mobileNumber);

		new OTPPage(driver, testInfo).
		enterOTP(otpValue).
		clickOnVerifyOTP();

		new HomePage(driver, testInfo).		
		clickOnProfileIconForSignedUser().
		clickOnChooseLanguage().
		clickGujaratiLink().
		clickContinueButton().
		clickRelaunchButton();

		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "profile_tab"), "home page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj17, "logout_menu"), "profile page");
		validateRequiredText(retrieveChildKeyValues(jsonObj17, "alert_logout"), "logout popup");
		validateRequiredText(retrieveChildKeyValues(jsonObj17, "alert_logout_msg"), "logout popup");
		validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj17, "alert_cancel_btn"), "logout popup");
		clickOnPartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj17, "alert_ok_btn"), "logout popup");

		new EKOnboardingPage(driver, testInfo).
		clickOnSignInbutton();

		validateRequiredText(retrieveChildKeyValues(jsonObj36, "sign_in_txt"), "login page");
		validateRequiredText(retrieveChildKeyValues(jsonObj35, "subtitle"), "login page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj35, "login_txt"), "login page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj35, "share_txt"), "login page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj35, "earn_txt"), "login page");
		validateRequiredText(retrieveChildKeyValues(jsonObj35, "subtitle"), "login page");
		validateRequiredText(retrieveChildKeyValues(jsonObj35, "sign_in_facebook_txt"), "login page");
		validateRequiredText(retrieveChildKeyValues(jsonObj35, "sign_in_or_txt"), "login page");
		validateRequiredText(retrieveChildKeyValues(jsonObj35, "email_inputfield_txt"), "login page");
		validateRequiredText(retrieveChildKeyValues(jsonObj35, "password_inputfield_txt"), "login page");
		validateRequiredText(retrieveChildKeyValues(jsonObj35, "forgot_password_txt"), "login page");
		validateRequiredText(retrieveChildKeyValues(jsonObj35, "sign_with_email_btn"), "login page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj35, "new_user_txt"), "login page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj35, "join_free_txt"), "login page");


		clickOnRequiredText(retrieveChildKeyValues(jsonObj35, "sign_with_email_btn"), "login page");
		validateRequiredText(retrieveChildKeyValues(jsonObj35, "email_inputfield_txt"), "login page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "email_error_msg"), "login page");
		validateRequiredText(retrieveChildKeyValues(jsonObj35, "password_inputfield_txt"), "login page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "password_error_msg"), "login page");

		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(textboxEmail);
		clickOnNoneOFTheAbove();
		enterTextByXpath(textboxEmail, getTestData(7, "oneUserChar"));
		enterTextByXpath(textboxPwd, getTestData(7, "oneUserChar"));
		clickOnRequiredText(retrieveChildKeyValues(jsonObj35, "sign_with_email_btn"), "login page");
		validateRequiredText(retrieveChildKeyValues(jsonObj35, "email_inputfield_txt"), "login page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "minimum_characters_error_msg"), "login page");
		validateRequiredText(retrieveChildKeyValues(jsonObj35, "password_inputfield_txt"), "login page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "password_minimum_characters_error_msg"), "login page");

		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(textboxEmail);
		clickOnNoneOFTheAbove();
		enterTextByXpath(textboxEmail, getTestData(7, "FiftyUserCharWithSpace"));
		enterTextByXpath(textboxPwd, getTestData(7, "FiftyUserChar"));
		clickOnRequiredText(retrieveChildKeyValues(jsonObj35, "sign_with_email_btn"), "login page");
		validateRequiredText(retrieveChildKeyValues(jsonObj35, "email_inputfield_txt"), "login page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "maximum_characters_error_msg"), "login page");
		validateRequiredText(retrieveChildKeyValues(jsonObj35, "password_inputfield_txt"), "login page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "password_maximum_characters_error_msg"), "login page");

		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(textboxEmail);
		clickOnNoneOFTheAbove();
		enterTextByXpath(textboxEmail, "2*2");
		enterTextByXpath(textboxPwd, "2 2 2 2 2");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj35, "sign_with_email_btn"), "login page");
		validateRequiredText(retrieveChildKeyValues(jsonObj35, "email_inputfield_txt"), "login page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "valid_email_error_msg"), "login page");
		validateRequiredText(retrieveChildKeyValues(jsonObj35, "password_inputfield_txt"), "login page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "valid_password_error_msg"), "login page");


	}

	@Test
	public void chooseLanguage_LanguageGujaratiValidation_ForgotPassword() throws UnsupportedEncodingException, FileNotFoundException, IOException, ParseException {

		JSONObject jsonObj1 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "gujaratiFileName"), getTestData(16, "common_label"));
		JSONObject jsonObj2 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "gujaratiFileName"), getTestData(16, "common_validate_label"));
		JSONObject jsonObj17 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "gujaratiFileName"), getTestData(16, "profile_right_menu"));
		JSONObject jsonObj35 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "gujaratiFileName"), getTestData(16, "sign_in"));
		JSONObject jsonObj37 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "gujaratiFileName"), getTestData(16, "forgot_password"));

		reportStep("chooseLanguage_LanguageGujaratiValidation_GetStarted_Joinfree_Login_ForgotPassword started", "INFO");

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo);

		String textboxEmail = "//android.widget.EditText[@content-desc='et_signup_email']|//android.widget.EditText[@content-desc='et_signin_email']|//android.widget.EditText[@content-desc='et_forgotpassword_email']";
		String emailAddress = objCashKaroUtility.generateEmail();
		String mobileNumber = objCashKaroUtility.generateMobileNumber();	

		String otpValue = new EKOnboardingPage(driver, testInfo).
				clickOnSignUpButton().
				enterFullName().
				enterEmail(emailAddress).
				enterPassword().
				enterMobileNumber(mobileNumber).
				clickOnGetOTPButton(mobileNumber);

		new OTPPage(driver, testInfo).
		enterOTP(otpValue).
		clickOnVerifyOTP();

		new HomePage(driver, testInfo).		
		clickOnProfileIconForSignedUser().
		clickOnChooseLanguage().
		clickGujaratiLink().
		clickContinueButton().
		clickRelaunchButton();

		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "profile_tab"), "home page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj17, "logout_menu"), "profile page");
		validateRequiredText(retrieveChildKeyValues(jsonObj17, "alert_logout"), "logout popup");
		validateRequiredText(retrieveChildKeyValues(jsonObj17, "alert_logout_msg"), "logout popup");
		validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj17, "alert_cancel_btn"), "logout popup");
		clickOnPartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj17, "alert_ok_btn"), "logout popup");

		new EKOnboardingPage(driver, testInfo).
		clickOnSignInbutton();


		clickOnRequiredText(retrieveChildKeyValues(jsonObj35, "forgot_password_txt"), "login page");
		validateRequiredText(retrieveChildKeyValues(jsonObj35, "forgot_password_txt"), "forgot password page");
		validateRequiredText(retrieveChildKeyValues(jsonObj37, "title"), "forgot password page");
		validateRequiredText(retrieveChildKeyValues(jsonObj37, "subtitle"), "forgot password page");
		validateRequiredText(retrieveChildKeyValues(jsonObj37, "email_inputfield_txt"), "forgot password page");
		validateRequiredText(retrieveChildKeyValues(jsonObj37, "password_link_txt"), "forgot password page");
		validateRequiredText(retrieveChildKeyValues(jsonObj37, "submit_btn"), "forgot password page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj37, "remember_password_txt"), "forgot password page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj37, "login_txt"), "forgot password page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj37, "submit_btn"), "forgot password page");
		validateRequiredText(retrieveChildKeyValues(jsonObj37, "email_inputfield_txt"), "forgot password page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "email_error_msg"), "login page");

		enterTextByXpath(textboxEmail, getTestData(7, "oneUserChar"));		
		clickOnRequiredText(retrieveChildKeyValues(jsonObj37, "submit_btn"), "forgot password page");
		validateRequiredText(retrieveChildKeyValues(jsonObj37, "email_inputfield_txt"), "forgot password page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "minimum_characters_error_msg"), "login page");

		enterTextByXpath(textboxEmail, getTestData(7, "FiftyUserCharWithSpace"));		
		clickOnRequiredText(retrieveChildKeyValues(jsonObj37, "submit_btn"), "forgot password page");
		validateRequiredText(retrieveChildKeyValues(jsonObj37, "email_inputfield_txt"), "forgot password page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "maximum_characters_error_msg"), "login page");

		enterTextByXpath(textboxEmail, "2*2");		
		clickOnRequiredText(retrieveChildKeyValues(jsonObj37, "submit_btn"), "forgot password page");
		validateRequiredText(retrieveChildKeyValues(jsonObj37, "email_inputfield_txt"), "forgot password page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "valid_email_error_msg"), "login page");


	}

	@Test
	public void chooseLanguage_LanguageGujaratiValidation_MyEarnings_History_Payment_ExistingUser() throws UnsupportedEncodingException, FileNotFoundException, IOException, ParseException {

		JSONObject jsonObj1 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "gujaratiFileName"), getTestData(16, "common_label"));
		JSONObject jsonObj2 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "gujaratiFileName"), getTestData(16, "common_validate_label"));
		JSONObject jsonObj14 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "gujaratiFileName"), getTestData(16, "search_result_all"));
		JSONObject jsonObj17 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "gujaratiFileName"), getTestData(16, "profile_right_menu"));
		JSONObject jsonObj28 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "gujaratiFileName"), getTestData(16, "my_account_earnings"));
		JSONObject jsonObj29 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "gujaratiFileName"), getTestData(16, "total_cashback_earned"));
		JSONObject jsonObj30 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "gujaratiFileName"), getTestData(16, "earnings_info"));		
		JSONObject jsonObj31 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "gujaratiFileName"), getTestData(16, "earnings_referral"));
		JSONObject jsonObj32 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "gujaratiFileName"), getTestData(16, "earnings_cashback"));
		JSONObject jsonObj33 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "gujaratiFileName"), getTestData(16, "my_earnings"));
		JSONObject jsonObj35 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "gujaratiFileName"), getTestData(16, "sign_in"));
		JSONObject jsonObj36 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "gujaratiFileName"), getTestData(16, "join_free"));
		JSONObject jsonObj40 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "gujaratiFileName"), getTestData(16, "giftcard_wallet_inputfield"));
		JSONObject jsonObj46 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "gujaratiFileName"), getTestData(16, "payment"));
		JSONObject jsonObj52 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "gujaratiFileName"), getTestData(16, "my_shared_activities"));
		JSONObject jsonObj53 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "gujaratiFileName"), getTestData(16, "giftcard_wallet_request_payment"));

		reportStep("chooseLanguage_LanguageGujaratiValidation_MyEarnings_History_Payment_ExistingUser started", "INFO");

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo);

		String dropDownPaymentSettings = "(//android.widget.EditText)[1]";
		String textNEFT = "//android.widget.CheckedTextView[@text='Bank Payment (NEFT)']";
		String textPaytmWallet = "//android.widget.CheckedTextView[@text='Paytm Wallet']";
		String textAmazonGiftCard = "//android.widget.CheckedTextView[@text='Amazon Gift Card']";
		String textBoxPaytmMobileNumberPaymentRequestPage = "//android.widget.EditText[@content-desc='et_requestpaymentPaytm_mobile']";
		String textBoxAmazonEmailPaymentRequestPage = "//android.widget.EditText[@content-desc='et_requestpaymentAmazon_email']";
		String linkEarningsInfoPaymentPage = "//android.widget.TextView[@content-desc='txt_payment_earningsInfo']";	
		String textboxName = "//android.widget.EditText[@content-desc='et_signup_fullname']";
		String textboxEmail = "//android.widget.EditText[@content-desc='et_signup_email']|//android.widget.EditText[@content-desc='et_signin_email']|//android.widget.EditText[@content-desc='et_forgotpassword_email']";
		String textboxPwd = "//android.widget.EditText[@content-desc='et_signup_password']|//android.widget.EditText[@content-desc='et_signin_password']";
		String textboxMobileNumber = "//android.widget.EditText[@content-desc='et_signup_mobile']";
		String searchIcon = "(//android.widget.TextView)[2]";
		String textAreaSearchIcon = "//android.widget.EditText[@content-desc='et_search_enterValue']";

		String cbStoreNameOne = CashKaroUtility.extractValuesFromStoreCategoryJsonAndLoadTheVariablesForStore("CB_Store_One", 0, "CB_Store_One_Name");
		String linkURL = CashKaroUtility.extractValuesFromStoreCategoryJsonAndLoadTheVariablesForStore("CB_Store_One", 0, "CB_Store_One_linkURL");

		String emailAddress = objCashKaroUtility.generateEmail();
		String mobileNumber = objCashKaroUtility.generateMobileNumber();	

		String emailAddressReferralJoinee = objCashKaroUtility.generateEmail();
		String mobileNumberReferralJoinee = objCashKaroUtility.generateMobileNumber();	

		String otpValue = new EKOnboardingPage(driver, testInfo).
				clickOnSignUpButton().
				enterFullName().
				enterEmail(emailAddress).
				enterPassword().
				enterMobileNumber(mobileNumber).
				clickOnGetOTPButton(mobileNumber);

		new OTPPage(driver, testInfo).
		enterOTP(otpValue).
		clickOnVerifyOTP();

		new HomePage(driver, testInfo).		
		clickOnProfileIconForSignedUser().
		clickOnChooseLanguage().
		clickGujaratiLink().
		clickContinueButton().
		clickRelaunchButton();

		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "profile_tab"), "home page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj17, "logout_menu"), "profile page");
		validateRequiredText(retrieveChildKeyValues(jsonObj17, "alert_logout"), "logout popup");
		validateRequiredText(retrieveChildKeyValues(jsonObj17, "alert_logout_msg"), "logout popup");
		validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj17, "alert_cancel_btn"), "logout popup");
		clickOnPartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj17, "alert_ok_btn"), "logout popup");

		new EKOnboardingPage(driver, testInfo);

		CashKaroUtility cu = new CashKaroUtility(udid, port, automationName, deviceName);
		driver = cu.launchChromeWebView(driver);

		AdminCoreFunction adminFunctions = new AdminCoreFunction(driver, testInfo);
		adminFunctions.naviagateAdminPage_ThenSelectEarnKaro();
		adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
		adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
		adminFunctions.clickSubmit();

		AdminUsersPage usersPage = new AdminUsersPage(driver, testInfo);
		adminFunctions.clickOnHamburgerMenu();
		adminFunctions.clickUsersMenu();
		adminFunctions.clickUsersSubMenu();
		usersPage.selectSearchByDropDown("User Email");
		usersPage.enterKeyword(emailAddress);
		usersPage.clickSubmitButton();
		String userId = usersPage.extractUserIdValueFromResultstableFirstRow();
		String userName = usersPage.extractUserNameValueFromResultstableFirstRow();

		CashKaroUtility reopenCKApp1 = new CashKaroUtility(udid, port, automationName, deviceName, systemPort);
		driver = reopenCKApp1.launchEarnKaroApp(driver);

		CashKaroUtility objcashKaroutility = new CashKaroUtility(udid, port, automationName, deviceName);
		AppiumDriver<MobileElement> driver2 = objcashKaroutility.launchChrome();
		driver = driver2;

		String url = Utilities.referralLinkGetCode(userId, userName);

		objcashKaroutility.closeAllTabsAndOneTabInChromeBrowser(driver);
		objcashKaroutility.clickAndEnterURLInSearchOrTypeWebAddress(driver, url);

		objcashKaroutility.clickJoinFreeLink(driver);

		enterTextByXpath(textboxName, "ReferralJoinee");
		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(textboxEmail);
		clickOnNoneOFTheAbove();
		enterTextByXpath(textboxEmail, emailAddressReferralJoinee);
		enterTextByXpath(textboxPwd, "ppounds");
		enterTextByXpath(textboxMobileNumber, mobileNumberReferralJoinee);
		clickOnRequiredText(retrieveChildKeyValues(jsonObj36, "otp_btn"), "join free page");	

		new OTPPage(driver, testInfo);

		String otp = Utilities.getSignUPOTP(mobileNumberReferralJoinee);

		new OTPPage(driver, testInfo).
		enterOTP(otp);

		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "verify_otp"), "otp page");	

		sleep(15000);

		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(searchIcon);
		hideKeyboard();
		//validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj13, "search_inputfield_txt"), "search page");
		enterTextByXpath(textAreaSearchIcon, cbStoreNameOne);
		pressEnter();
		validateRequiredText(retrieveChildKeyValues(jsonObj14, "offer_count_single_message"), "search page");
		validateRequiredText(retrieveChildKeyValues(jsonObj1, "add_to_share"), "search page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "share_now"), "search page");

		WhatsAppUtility whatsAppUtility = new WhatsAppUtility(driver, testInfo, udid, port, automationName, deviceName, systemPort);
		whatsAppUtility.clickOnWhatAppMenuItemSearch().
		enterWhatsAppSearchText("Mallik").
		clickOnRequiredContact().
		clickOnSend().
		clickOnSend().
		clickOnLastSharedLink();

		new IntermediateRetailerPage(driver, testInfo).
		validateRetailerPage(linkURL, linkURL);

		CashKaroUtility cu2 = new CashKaroUtility(udid, port, automationName, deviceName);
		cu = cu2;
		driver = cu.launchChromeWebView(driver);

		AdminCoreFunction adminFunctions2 = new AdminCoreFunction(driver, testInfo);
		adminFunctions = adminFunctions2;
		adminFunctions.naviagateAdminPage_ThenSelectEarnKaro();
		adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
		adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
		adminFunctions.clickSubmit();

		AdminReportsPage reportsPage = new AdminReportsPage(driver, testInfo);
		adminFunctions.clickOnHamburgerMenu();
		adminFunctions.clickOnReports();
		reportsPage.clickExitClickSubMenu();
		reportsPage.clickClear();
		reportsPage.selectSearchByDropDown("User Email");
		reportsPage.enterKeyword(emailAddressReferralJoinee);
		reportsPage.clickSubmit();

		String exitClickOne = reportsPage.extractRequiredExitIdUsingStoreNameExitClickType(cbStoreNameOne, getTestData(7, "Normal"));

		AdminCashbackPage ProfitPage = new AdminCashbackPage(driver, testInfo);
		adminFunctions.clickOnHamburgerMenu();
		adminFunctions.clickCashbackMenu();
		ProfitPage.clickCashbackSubMenu();

		ProfitPage.clickAddNewButton();
		ProfitPage.enterExitId(exitClickOne);
		ProfitPage.entertransactionId(exitClickOne);
		ProfitPage.enterOrderId(exitClickOne);
		ProfitPage.setOrderDate();
		ProfitPage.enterOrderValue("1000");
		ProfitPage.enterconfirmCommisionNetwork(getTestData(11, "EightHundered"));
		ProfitPage.entercashback("700");
		ProfitPage.selectCashbackTypeFromDropDown(getTestData(11, "Cashback"));
		ProfitPage.selectCashbackStatusFromDropDown(getTestData(11, "Pending"));
		ProfitPage.setPendingDate();
		ProfitPage.enterDetails(exitClickOne);
		ProfitPage.clickSubmit();
		ProfitPage.clickClear();

		CashKaroUtility reopenCKAppAgain = new CashKaroUtility(udid, port, automationName, deviceName, systemPort);
		driver = reopenCKAppAgain.launchEarnKaroApp(driver);
		new HomePage(driver, testInfo);

		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "profile_tab"), "home page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj17, "history_menu"), "profile page");
		validateRequiredText(retrieveChildKeyValues(jsonObj1, "recent_clicks_tab"), "history page");
		validateRequiredText(retrieveChildKeyValues(jsonObj52, "title"), "history page");
		validateRequiredText(retrieveChildKeyValues(jsonObj52, "shared_link_details"), "history page");
		validateRequiredText(retrieveChildKeyValues(jsonObj52, "shared_id"), "history page");
		validateRequiredText(retrieveChildKeyValues(jsonObj52, "shared_date_time"), "history page");
		validateRequiredText(retrieveChildKeyValues(jsonObj52, "retailer_name"), "history page");
		validateRequiredText(retrieveChildKeyValues(jsonObj52, "no_of_clicks"), "history page");
		validateRequiredText(retrieveChildKeyValues(jsonObj52, "no_of_transactions"), "history page");
		validateRequiredText(retrieveChildKeyValues(jsonObj52, "total_earning"), "history page");

		backButton();

		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "profile_tab"), "home page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj17, "my_earnings_menu"), "profile page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj29, "total_cashback_earned_txt"), "my earnings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj29, "total_cashback_earned_txt"), 1, "profit page");
		validateRequiredText(retrieveChildKeyValues(jsonObj29, "total_cashback_earned_txt"), 2, "profit page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj29, "paid_cashback_txt"), "profit page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj29, "pending_cashback_txt"), "profit page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj29, "referral_cashback_txt"), "profit page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj29, "available_cashback_txt"), "profit page");

		backButton();

		validateRequiredText(retrieveChildKeyValues(jsonObj32, "order_details"), "my earnings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj32, "date"), "my earnings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj32, "retailer"), "my earnings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj32, "order_amount"), "my earnings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj32, "cashback_amount"), "my earnings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj32, "status"), "my earnings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj32, "expected_date"), "my earnings page");

		backButton();

		clickOnRequiredText(retrieveChildKeyValues(jsonObj17, "logout_menu"), "profile page");
		validateRequiredText(retrieveChildKeyValues(jsonObj17, "alert_logout"), "logout popup");
		validateRequiredText(retrieveChildKeyValues(jsonObj17, "alert_logout_msg"), "logout popup");
		validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj17, "alert_cancel_btn"), "logout popup");
		clickOnPartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj17, "alert_ok_btn"), "logout popup");

		new EKOnboardingPage(driver, testInfo);

		CashKaroUtility cu3 = new CashKaroUtility(udid, port, automationName, deviceName);
		cu = cu3;
		driver = cu.launchChromeWebView(driver);

		AdminCoreFunction adminFunctions3 = new AdminCoreFunction(driver, testInfo);
		adminFunctions = adminFunctions3;
		adminFunctions.naviagateAdminPage_ThenSelectEarnKaro();
		adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
		adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
		adminFunctions.clickSubmit();

		AdminCashbackPage ProfitPage1 = new AdminCashbackPage(driver, testInfo);
		ProfitPage = ProfitPage1;
		adminFunctions.clickOnHamburgerMenu();
		adminFunctions.clickCashbackMenu();
		ProfitPage.clickCashbackSubMenu();

		ProfitPage.selectSearchByStatusFromDropDown("Exit ID");
		ProfitPage.enterKeyword(exitClickOne);
		ProfitPage.selectCashbackStatusFromDropDown(getTestData(11, "Pending"));
		ProfitPage.clickSubmit();
		ProfitPage.clickEdit();
		ProfitPage.selectCashbackStatusFromDropDown(getTestData(11, "Confirmed"));
		ProfitPage.setConfirmDate();
		ProfitPage.clickSubmit();
		ProfitPage.clickClear();

		CashKaroUtility reopenCKAppOnceAgain = new CashKaroUtility(udid, port, automationName, deviceName, systemPort);
		driver = reopenCKAppOnceAgain.launchEarnKaroApp(driver);

		new EKOnboardingPage(driver, testInfo).clickOnSignInbutton();

		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(textboxEmail);
		clickOnNoneOFTheAbove();
		enterTextByXpath(textboxEmail, emailAddress);
		enterTextByXpath(textboxPwd, "ppounds");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj35, "sign_with_email_btn"), "login page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "profile_tab"), "home page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj17, "my_earnings_menu"), "profile page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj33, "referral_tap"), "my earnings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj31, "order_details"), "my earnings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj31, "date"), "my earnings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj31, "referral_name"), "my earnings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj31, "cashback"), "my earnings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj31, "status"), "my earnings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj33, "refer_now_btn"), "my earnings page");

		backButton();

		clickOnRequiredText(retrieveChildKeyValues(jsonObj17, "logout_menu"), "profile page");
		validateRequiredText(retrieveChildKeyValues(jsonObj17, "alert_logout"), "logout popup");
		validateRequiredText(retrieveChildKeyValues(jsonObj17, "alert_logout_msg"), "logout popup");
		validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj17, "alert_cancel_btn"), "logout popup");
		clickOnPartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj17, "alert_ok_btn"), "logout popup");

		new EKOnboardingPage(driver, testInfo).clickOnSignInbutton();

		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(textboxEmail);
		clickOnNoneOFTheAbove();
		enterTextByXpath(textboxEmail, emailAddressReferralJoinee);
		enterTextByXpath(textboxPwd, "ppounds");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj35, "sign_with_email_btn"), "login page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "profile_tab"), "home page");
		validateRequiredText(retrieveChildKeyValues(jsonObj1, "profile_tab"), "profile page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj17, "hello_user_txt"), "profile page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj17, "total_cashback_txt"), "profile page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj17, "payment_menu"), "profile page");

		validateRequiredText(retrieveChildKeyValues(jsonObj17, "payment_menu"), "profile page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj29, "total_cashback_earned_txt"), "payments page");
		validateRequiredText(retrieveChildKeyValues(jsonObj29, "total_cashback_earned_txt"), 1, "profit page");
		validateRequiredText(retrieveChildKeyValues(jsonObj29, "total_cashback_earned_txt"), 2, "profit page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj29, "paid_cashback_txt"), "profit page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj29, "pending_cashback_txt"), "profit page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj29, "referral_cashback_txt"), "profit page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj29, "available_cashback_txt"), "profit page");

		backButton();

		validatePartialRequiredText(retrieveChildKeyValues(jsonObj28, "tap_to_see_details_txt"), "payments page");
		validateRequiredText(retrieveChildKeyValues(jsonObj46, "cashback_earnings"), "payments page");
		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(linkEarningsInfoPaymentPage);
		validateRequiredText(retrieveChildKeyValues(jsonObj30, "message_first_txt"), "earnings info page");


		backButton();

		validateRequiredText(retrieveChildKeyValues(jsonObj46, "request_cashback_btn"), "payments page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj46, "static_text_title"), "payments page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj46, "static_text_1"), "payments page");


		clickOnRequiredText(retrieveChildKeyValues(jsonObj46, "request_cashback_btn"), "payments page");

		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(dropDownPaymentSettings);
		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(textPaytmWallet);
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "mobile_inputfield_txt"), "payment request page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "get_paid_btn"), "payment req page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "mobile_inputfield_txt"), "payment request page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "mobile_number_error_msg"), "payment request page");

		enterTextByXpath(textBoxPaytmMobileNumberPaymentRequestPage, "1");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "get_paid_btn"), "payment req page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "mobile_inputfield_txt"), "payment request page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "minimum_digits_error_msg"), "payment request page");

		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(dropDownPaymentSettings);
		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(textAmazonGiftCard);

		validateRequiredText(retrieveChildKeyValues(jsonObj53, "email_inputfield_txt"), "payment request page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "get_paid_btn"), "payment req page");
		validateRequiredText(retrieveChildKeyValues(jsonObj53, "email_inputfield_txt"), "payment request page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "email_error_msg"), "payment request page");

		enterTextByXpath(textBoxAmazonEmailPaymentRequestPage, "1");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "get_paid_btn"), "payment req page");
		validateRequiredText(retrieveChildKeyValues(jsonObj53, "email_inputfield_txt"), "payment request page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "minimum_characters_error_msg"), "payment request page");

		enterTextByXpath(textBoxAmazonEmailPaymentRequestPage, "asdfghjkl");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "get_paid_btn"), "payment req page");
		validateRequiredText(retrieveChildKeyValues(jsonObj53, "email_inputfield_txt"), "payment request page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "valid_email_error_msg"), "payment request page");

		enterTextByXpath(textBoxAmazonEmailPaymentRequestPage, getTestData(7, "FiftyUserCharWithSpace"));
		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "get_paid_btn"), "payment req page");
		validateRequiredText(retrieveChildKeyValues(jsonObj53, "email_inputfield_txt"), "payment request page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "maximum_characters_error_msg"), "payment request page");

		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(dropDownPaymentSettings);
		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(textNEFT);

		validateRequiredText(retrieveChildKeyValues(jsonObj53, "bank_account_inputfield_txt"), "payment request page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "bank_name_inputfield_txt"), "payment request page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "branch_name_inputfield_txt"), "payment request page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "bank_account_number_inputfield_txt"), "payment request page");
		validateRequiredText(retrieveChildKeyValues(jsonObj53, "bank_ifsc_code_inputfield_txt"), "payment request page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "get_paid_btn"), "payment req page");
		validateRequiredText(retrieveChildKeyValues(jsonObj53, "bank_account_inputfield_txt"), "payment request page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "account_holder_error_msg"), "payment request page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "bank_name_inputfield_txt"), "payment request page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "valid_bank_name_error_msg"), "payment request page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "branch_name_inputfield_txt"), "payment request page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "branch_minimum_digits_error_msg"), "payment request page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "bank_account_number_inputfield_txt"), "payment request page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "account_number_error_msg"), "payment request page");
		validateRequiredText(retrieveChildKeyValues(jsonObj53, "bank_ifsc_code_inputfield_txt"), "payment request page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "ifsc_code_error_msg"), "payment request page");


	}

	@Test
	public void chooseLanguage_LanguageGujaratiValidation_StoreCategory_StoreDetail() throws UnsupportedEncodingException, FileNotFoundException, IOException, ParseException {

		JSONObject jsonObj1 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "gujaratiFileName"), getTestData(16, "common_label"));
		JSONObject jsonObj3 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "gujaratiFileName"), getTestData(16, "main_menu"));
		JSONObject jsonObj5 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "gujaratiFileName"), getTestData(16, "store_category"));
		JSONObject jsonObj12 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "gujaratiFileName"), getTestData(16, "share_counts"));
		JSONObject jsonObj17 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "gujaratiFileName"), getTestData(16, "profile_right_menu"));

		reportStep("chooseLanguage_LanguageGujaratiValidation_Store_Product_StoreCategory started", "INFO");

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo);

		String shareButtonWithCount = "//android.view.ViewGroup[@content-desc='txt_sharecount_open_modal']/android.view.ViewGroup/android.widget.TextView[@text='1']";
		String imageStoreCard = "//android.widget.ImageView[@content-desc='img_storeCard_imageValue']";
		String emailAddress = objCashKaroUtility.generateEmail();
		String mobileNumber = objCashKaroUtility.generateMobileNumber();	

		String otpValue = new EKOnboardingPage(driver, testInfo).
				clickOnSignUpButton().
				enterFullName().
				enterEmail(emailAddress).
				enterPassword().
				enterMobileNumber(mobileNumber).
				clickOnGetOTPButton(mobileNumber);

		new OTPPage(driver, testInfo). 
		enterOTP(otpValue).
		clickOnVerifyOTP();

		new HomePage(driver, testInfo).		
		clickOnProfileIconForSignedUser().
		clickOnChooseLanguage().
		clickGujaratiLink().
		clickContinueButton().
		clickRelaunchButton();

		validateRequiredText(retrieveChildKeyValues(jsonObj3, "home_lbl"), "home page");

		new HomePage(driver, testInfo).
		clickOnHamburgerIcon();

		validateRequiredText(retrieveChildKeyValues(jsonObj3, "home_lbl"), "home page");

		new SliderScreenPage(driver, testInfo).
		clickRetailerCategory().
		verifyRetailerCategoryMenuNavigation().
		clickAutomationSpecificCategoryInRetailerCategoryMenuUntilAllSubCategoryVisible().
		clickAutomationSpecificSubCategoryOne();

		validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj5, "popular"), "store category page");
		validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj5, "az"), "store category page");
		validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj5, "percent"), "store category page");
		validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj5, "amount"), "store category page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "add_to_share"), "store category page");
		validateRequiredText(retrieveChildKeyValues(jsonObj1, "share_now"), "store category page");
		validateRequiredText(retrieveChildKeyValues(jsonObj1, "remove"), "store category page");

		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(shareButtonWithCount);
		validateRequiredText(retrieveChildKeyValues(jsonObj1, "share_now"), "store category page");
		validateRequiredText(retrieveChildKeyValues(jsonObj1, "copy_Link"), "store category page");
		validateRequiredText(retrieveChildKeyValues(jsonObj12, "close"), "store category page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj12, "clear"), "store category page");

		validateRequiredText(retrieveChildKeyValues(jsonObj12, "clear"), "store category page");
		validateRequiredText(retrieveChildKeyValues(jsonObj12, "confirm_msg_clear_share"), "store category page");
		validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj17, "alert_cancel_btn"), "store category page");
		clickOnPartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj17, "alert_ok_btn"), "store category page");

		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(imageStoreCard);
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj1, "you_will_earn"), "store detail page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "add_to_share"), "store detail page");
		validateRequiredText(retrieveChildKeyValues(jsonObj1, "share_now"), "store detail page");
		validateRequiredText(retrieveChildKeyValues(jsonObj1, "remove"), "store category page");

		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(shareButtonWithCount);
		validateRequiredText(retrieveChildKeyValues(jsonObj1, "share_now"), "store detail page");
		validateRequiredText(retrieveChildKeyValues(jsonObj1, "copy_Link"), "store detail page");
		validateRequiredText(retrieveChildKeyValues(jsonObj12, "close"), "store detail page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj12, "clear"), "store detail page");

		validateRequiredText(retrieveChildKeyValues(jsonObj12, "clear"), "store detail page");
		validateRequiredText(retrieveChildKeyValues(jsonObj12, "confirm_msg_clear_share"), "store detail page");
		validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj17, "alert_cancel_btn"), "store detail page");
		clickOnPartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj17, "alert_ok_btn"), "store detail page");

		new StoreDetailPage(driver, testInfo);

	}

	@Test
	public void chooseLanguage_LanguageGujaratiValidation_Product() throws UnsupportedEncodingException, FileNotFoundException, IOException, ParseException {

		JSONObject jsonObj1 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "gujaratiFileName"), getTestData(16, "common_label"));
		JSONObject jsonObj3 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "gujaratiFileName"), getTestData(16, "main_menu"));
		JSONObject jsonObj6 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "gujaratiFileName"), getTestData(16, "prduct_category"));
		JSONObject jsonObj9 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "gujaratiFileName"), getTestData(16, "product_details"));
		JSONObject jsonObj10 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "gujaratiFileName"), getTestData(16, "product_card"));
		JSONObject jsonObj49 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "gujaratiFileName"), getTestData(16, "prduct_filter"));

		reportStep("chooseLanguage_LanguageGujaratiValidation_Store_Product_StoreCategory started", "INFO");

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo);

		String menFashionCategory = "//android.view.ViewGroup[@content-desc='txt_mainMenu_productDealsTop']";
		String tshirtCategory = "//android.widget.TextView[@content-desc='txt_subMenuProduct_mainMenuName']";
		String menfashionLink = "//android.widget.TextView[@content-desc='txt_filter_category_name']";
		String imageProductCard = "//android.widget.ImageView[@content-desc='img_product_image']";
		String productcard = "(//android.widget.ImageView)[3]";		
		String emailAddress = objCashKaroUtility.generateEmail();
		String mobileNumber = objCashKaroUtility.generateMobileNumber();	

		String otpValue = new EKOnboardingPage(driver, testInfo).
				clickOnSignUpButton().
				enterFullName().
				enterEmail(emailAddress).
				enterPassword().
				enterMobileNumber(mobileNumber).
				clickOnGetOTPButton(mobileNumber);

		new OTPPage(driver, testInfo). 
		enterOTP(otpValue).
		clickOnVerifyOTP();

		new HomePage(driver, testInfo).		
		clickOnProfileIconForSignedUser().
		clickOnChooseLanguage().
		clickGujaratiLink().
		clickContinueButton().
		clickRelaunchButton();

		validateRequiredText(retrieveChildKeyValues(jsonObj3, "home_lbl"), "home page");

		new HomePage(driver, testInfo).
		clickOnHamburgerIcon();

		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(menFashionCategory);
		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(tshirtCategory);

		clickOnPartialRequiredText(retrieveChildKeyValues(jsonObj6, "filter"), "product page");
		validateRequiredText(retrieveChildKeyValues(jsonObj49, "filter_by"), "product page");
		validateRequiredText(retrieveChildKeyValues(jsonObj49, "clerar_all"), "product page");
		validateRequiredText(retrieveChildKeyValues(jsonObj49, "close_btn"), "product page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj49, "apply_btn"), "product page");

		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(menfashionLink);
		clickOnRequiredText(retrieveChildKeyValues(jsonObj49, "clerar_all"), "product page");
		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(menfashionLink);
		clickOnRequiredText(retrieveChildKeyValues(jsonObj49, "close_btn"), "product page");
		validateRequiredText(retrieveChildKeyValues(jsonObj49, "discard_changes_title"), "product page");
		validateRequiredText(retrieveChildKeyValues(jsonObj49, "discard_changes_msg"), "product page");
		validateRequiredText(retrieveChildKeyValues(jsonObj49, "apply_changes_btn"), "product page");
		validateRequiredText(retrieveChildKeyValues(jsonObj49, "discard_changes_btn"), "product page");

		backButton();

		clickOnPartialRequiredText(retrieveChildKeyValues(jsonObj6, "sort"), "product page");
		validateRequiredText(retrieveChildKeyValues(jsonObj6, "sort_title"), "product page");
		validateRequiredText(retrieveChildKeyValues(jsonObj6, "sort_popular"), "product page");
		validateRequiredText(retrieveChildKeyValues(jsonObj6, "sort_discount"), "product page");
		validateRequiredText(retrieveChildKeyValues(jsonObj6, "sort_high_price"), "product page");
		validateRequiredText(retrieveChildKeyValues(jsonObj6, "sort_low_price"), "product page");

		backButton();

		validatePartialRequiredText(retrieveChildKeyValues(jsonObj10, "brand"), "product page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj10, "price"), "product page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj10, "sale"), "product page");
		validateRequiredText(retrieveChildKeyValues(jsonObj1, "add_to_share"), "product page");
		validateRequiredText(retrieveChildKeyValues(jsonObj1, "share_now"), "product page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj10, "see_details"), "product page");

		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(imageProductCard);
		clickOnRequiredText(retrieveChildKeyValues(jsonObj9, "seller_price"), "product detail page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "you_will_earn"), "product detail page");
		validateRequiredText(retrieveChildKeyValues(jsonObj1, "add_to_share"), "product page");
		validateRequiredText(retrieveChildKeyValues(jsonObj1, "share_now"), "product page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj9, "product_highlights"), "product detail page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj9, "similar_products"), "product detail page");



	}

	@Test
	public void chooseLanguage_LanguageKannadaValidation_SuccessStories() throws UnsupportedEncodingException, FileNotFoundException, IOException, ParseException {

		JSONObject jsonObj1 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "kannadaFileName"), getTestData(16, "common_label"));
		JSONObject jsonObj4 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "kannadaFileName"), getTestData(16, "home"));
		JSONObject jsonObj17 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "kannadaFileName"), getTestData(16, "profile_right_menu"));
		JSONObject jsonObj22 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "kannadaFileName"), getTestData(16, "success_stories"));

		reportStep("chooseLanguage_LanguageKannadaValidation_SuccessStories started", "INFO");

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo);

		String emailAddress = objCashKaroUtility.generateEmail();
		String mobileNumber = objCashKaroUtility.generateMobileNumber();	

		String otpValue = new EKOnboardingPage(driver, testInfo).
				clickOnSignUpButton().
				enterFullName().
				enterEmail(emailAddress).
				enterPassword().
				enterMobileNumber(mobileNumber).
				clickOnGetOTPButton(mobileNumber);

		new OTPPage(driver, testInfo).
		enterOTP(otpValue).
		clickOnVerifyOTP();

		new HomePage(driver, testInfo).		
		clickOnProfileIconForSignedUser().
		clickOnChooseLanguage().
		clickKannadaLink().
		clickContinueButton().
		clickRelaunchButton();

		validateRequiredText(retrieveChildKeyValues(jsonObj4, "dynamic_home_top_Category"), "home page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "profile_tab"), "home page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj17, "success_stories_menu"), "profile page");
		validateRequiredText(retrieveChildKeyValues(jsonObj17, "success_stories_menu"), "success stories page");
		scrollFromUpToDowninApp();scrollFromUpToDowninApp();
		validateRequiredText(retrieveChildKeyValues(jsonObj22, "title"), "success stories page");
		validateRequiredText(retrieveChildKeyValues(jsonObj22, "testimonial_txt1"), "success stories page");
		validateRequiredText(retrieveChildKeyValues(jsonObj22, "testimonial_name1"), "success stories page");
		validateRequiredText(retrieveChildKeyValues(jsonObj22, "testimonial_txt2"), "success stories page");
		validateRequiredText(retrieveChildKeyValues(jsonObj22, "testimonial_name2"), "success stories page");
		validateRequiredText(retrieveChildKeyValues(jsonObj22, "testimonial_txt3"), "success stories page");
		validateRequiredText(retrieveChildKeyValues(jsonObj22, "testimonial_name3"), "success stories page");
		validateRequiredText(retrieveChildKeyValues(jsonObj22, "testimonial_txt4"), "success stories page");
		validateRequiredText(retrieveChildKeyValues(jsonObj22, "testimonial_name4"), "success stories page");
		validateRequiredText(retrieveChildKeyValues(jsonObj22, "testimonial_txt5"), "success stories page");
		validateRequiredText(retrieveChildKeyValues(jsonObj22, "testimonial_name5"), "success stories page");


	}

	@Test
	public void chooseLanguage_LanguageKannadaValidation_HowItWorks() throws UnsupportedEncodingException, FileNotFoundException, IOException, ParseException {

		JSONObject jsonObj1 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "kannadaFileName"), getTestData(16, "common_label"));
		JSONObject jsonObj4 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "kannadaFileName"), getTestData(16, "home"));
		JSONObject jsonObj17 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "kannadaFileName"), getTestData(16, "profile_right_menu"));
		JSONObject jsonObj23 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "kannadaFileName"), getTestData(16, "how_it_works"));

		reportStep("chooseLanguage_LanguageKannadaValidation_HowItWorks started", "INFO");

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo);

		String emailAddress = objCashKaroUtility.generateEmail();
		String mobileNumber = objCashKaroUtility.generateMobileNumber();	

		String otpValue = new EKOnboardingPage(driver, testInfo).
				clickOnSignUpButton().
				enterFullName().
				enterEmail(emailAddress).
				enterPassword().
				enterMobileNumber(mobileNumber).
				clickOnGetOTPButton(mobileNumber);

		new OTPPage(driver, testInfo).
		enterOTP(otpValue).
		clickOnVerifyOTP();

		new HomePage(driver, testInfo).		
		clickOnProfileIconForSignedUser().
		clickOnChooseLanguage().
		clickKannadaLink().
		clickContinueButton().
		clickRelaunchButton();

		validateRequiredText(retrieveChildKeyValues(jsonObj4, "dynamic_home_top_Category"), "home page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "profile_tab"), "home page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj17, "how_it_works_menu"), "profile page");
		scrollFromUpToDowninApp();scrollFromUpToDowninApp();
		validateRequiredText(retrieveChildKeyValues(jsonObj17, "how_it_works_menu"), "how earnkaro works? page");
		validateRequiredText(retrieveChildKeyValues(jsonObj23, "watch_video_title1"), "how earnkaro works? page");
		validateRequiredText(retrieveChildKeyValues(jsonObj23, "watch_video_txt"), 1, "how earnkaro works? page");
		validateRequiredText(retrieveChildKeyValues(jsonObj23, "watch_video_title2"), "how earnkaro works? page");
		validateRequiredText(retrieveChildKeyValues(jsonObj23, "watch_video_txt"), 2, "how earnkaro works? page");
		validateRequiredText(retrieveChildKeyValues(jsonObj23, "watch_video_title3"), "how earnkaro works? page");
		validateRequiredText(retrieveChildKeyValues(jsonObj23, "watch_video_txt"), 3, "how earnkaro works? page");
		validateRequiredText(retrieveChildKeyValues(jsonObj23, "watch_video_title4"), "how earnkaro works? page");
		validateRequiredText(retrieveChildKeyValues(jsonObj23, "watch_video_txt"), 4, "how earnkaro works? page");
		validateRequiredText(retrieveChildKeyValues(jsonObj23, "watch_video_title5"), "how earnkaro works? page");
		validateRequiredText(retrieveChildKeyValues(jsonObj23, "watch_video_txt"), 5, "how earnkaro works? page");
		validateRequiredText(retrieveChildKeyValues(jsonObj23, "watch_video_title6"), "how earnkaro works? page");
		validateRequiredText(retrieveChildKeyValues(jsonObj23, "watch_video_txt"), 6, "how earnkaro works? page");



	}

	@Test
	public void chooseLanguage_LanguageKannadaValidation_Testimonials() throws UnsupportedEncodingException, FileNotFoundException, IOException, ParseException {

		JSONObject jsonObj1 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "kannadaFileName"), getTestData(16, "common_label"));
		JSONObject jsonObj4 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "kannadaFileName"), getTestData(16, "home"));
		JSONObject jsonObj17 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "kannadaFileName"), getTestData(16, "profile_right_menu"));
		JSONObject jsonObj24 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "kannadaFileName"), getTestData(16, "testimonial"));
		JSONObject jsonObj25 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "kannadaFileName"), getTestData(16, "testimonial_write"));

		reportStep("chooseLanguage_LanguageKannadaValidation_Testimonials started", "INFO");

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo);

		String fourStarTestimonials = "(//android.widget.TextView)[8]";
		String threeStarTestimonials = "(//android.widget.TextView)[7]";
		String twoStarTestimonials = "(//android.widget.TextView)[6]";
		String oneStarTestimonials = "(//android.widget.TextView)[5]";

		String emailAddress = objCashKaroUtility.generateEmail();
		String mobileNumber = objCashKaroUtility.generateMobileNumber();	

		String otpValue = new EKOnboardingPage(driver, testInfo).
				clickOnSignUpButton().
				enterFullName().
				enterEmail(emailAddress).
				enterPassword().
				enterMobileNumber(mobileNumber).
				clickOnGetOTPButton(mobileNumber);

		new OTPPage(driver, testInfo).
		enterOTP(otpValue).
		clickOnVerifyOTP();

		new HomePage(driver, testInfo).		
		clickOnProfileIconForSignedUser().
		clickOnChooseLanguage().
		clickKannadaLink().
		clickContinueButton().
		clickRelaunchButton();

		validateRequiredText(retrieveChildKeyValues(jsonObj4, "dynamic_home_top_Category"), "home page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "profile_tab"), "home page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj17, "rate_us_menu"), "profile page");
		validateRequiredText(retrieveChildKeyValues(jsonObj24, "count_txt"), "testimonials page");
		validateRequiredText(retrieveChildKeyValues(jsonObj24, "happy_users_txt"), "testimonials page");

		clickOnPartialRequiredText(retrieveChildKeyValues(jsonObj24, "write_testimonial_btn"), "testimonials page");
		validateRequiredText(retrieveChildKeyValues(jsonObj24, "write_testimonial_btn"), "write testimonials page");
		validateRequiredText(retrieveChildKeyValues(jsonObj25, "title"), "write testimonials page");

		validateRequiredText(retrieveChildKeyValues(jsonObj25, "rate_experience_txt"), "write testimonials page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj25, "star_txt_awesome_experience"), "write testimonials page");
		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(fourStarTestimonials);
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj25, "star_txt_good_experience"), "write testimonials page");
		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(threeStarTestimonials);
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj25, "star_txt_average"), "write testimonials page");
		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(twoStarTestimonials);
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj25, "star_txt_not_great"), "write testimonials page");
		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(oneStarTestimonials);
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj25, "star_txt_terrible"), "write testimonials page");


	}

	@Test
	public void chooseLanguage_LanguageKannadaValidation_ContactUs() throws UnsupportedEncodingException, FileNotFoundException, IOException, ParseException {

		JSONObject jsonObj1 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "kannadaFileName"), getTestData(16, "common_label"));
		JSONObject jsonObj2 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "kannadaFileName"), getTestData(16, "common_validate_label"));
		JSONObject jsonObj4 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "kannadaFileName"), getTestData(16, "home"));
		JSONObject jsonObj17 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "kannadaFileName"), getTestData(16, "profile_right_menu"));
		JSONObject jsonObj20 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "kannadaFileName"), getTestData(16, "contact_us"));		
		JSONObject jsonObj21 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "kannadaFileName"), getTestData(16, "faq"));

		reportStep("chooseLanguage_LanguageKannadaValidation_ContactUs started", "INFO");

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo);

		String textBoxUserName = "//android.widget.EditText[@content-desc='et_contactUs_yourName']";
		String textBoxEmail = "//android.widget.EditText[@content-desc='et_contactUs_email']";

		String emailAddress = objCashKaroUtility.generateEmail();
		String mobileNumber = objCashKaroUtility.generateMobileNumber();	

		String otpValue = new EKOnboardingPage(driver, testInfo).
				clickOnSignUpButton().
				enterFullName().
				enterEmail(emailAddress).
				enterPassword().
				enterMobileNumber(mobileNumber).
				clickOnGetOTPButton(mobileNumber);

		new OTPPage(driver, testInfo).
		enterOTP(otpValue).
		clickOnVerifyOTP();

		new HomePage(driver, testInfo).		
		clickOnProfileIconForSignedUser().
		clickOnChooseLanguage().
		clickKannadaLink().
		clickContinueButton().
		clickRelaunchButton();

		validateRequiredText(retrieveChildKeyValues(jsonObj4, "dynamic_home_top_Category"), "home page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "profile_tab"), "home page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj17, "help"), "profile page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj17, "contact_us_menu"), "help page");
		enterTextByXpath(textBoxUserName, "..");
		enterTextByXpath(textBoxEmail, "..");
		validateRequiredText(retrieveChildKeyValues(jsonObj21, "faq_btn"), "contact us page");
		validateRequiredText(retrieveChildKeyValues(jsonObj20, "name_inputfield_txt"), "contact us page");
		validateRequiredText(retrieveChildKeyValues(jsonObj20, "email_inputfield_txt"), "contact us page");
		validateRequiredText(retrieveChildKeyValues(jsonObj20, "message_inputfield_txt"), "contact us page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj20, "submit_btn"), "contact us page");

		validateRequiredText(retrieveChildKeyValues(jsonObj2, "select_subject_error_msg"), "contact us page");
		validateRequiredText(retrieveChildKeyValues(jsonObj20, "name_inputfield_txt"), "contact us page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "valid_name_error_msg"), "contact us page");
		validateRequiredText(retrieveChildKeyValues(jsonObj20, "email_inputfield_txt"), "contact us page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "valid_email_error_msg"), "contact us page");
		validateRequiredText(retrieveChildKeyValues(jsonObj20, "message_inputfield_txt"), "contact us page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "valid_details_error_msg"), "contact us page");

		enterTextByXpath(textBoxUserName, getTestData(7, "oneUserChar"));
		enterTextByXpath(textBoxEmail, getTestData(7, "oneUserChar"));
		clickOnRequiredText(retrieveChildKeyValues(jsonObj20, "submit_btn"), "contact us page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "select_subject_error_msg"), "contact us page");
		validateRequiredText(retrieveChildKeyValues(jsonObj20, "name_inputfield_txt"), "contact us page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "minimum_characters_error_msg"), 1, "contact us page");
		validateRequiredText(retrieveChildKeyValues(jsonObj20, "email_inputfield_txt"), "contact us page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "minimum_characters_error_msg"), 2, "contact us page");
		validateRequiredText(retrieveChildKeyValues(jsonObj20, "message_inputfield_txt"), "contact us page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "valid_details_error_msg"), "contact us page");

		enterTextByXpath(textBoxUserName, getTestData(7, "FiftyPlusUserChar"));
		enterTextByXpath(textBoxEmail, getTestData(7, "FiftyPlusUserChar"));
		clickOnRequiredText(retrieveChildKeyValues(jsonObj20, "submit_btn"), "contact us page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "select_subject_error_msg"), "contact us page");
		validateRequiredText(retrieveChildKeyValues(jsonObj20, "name_inputfield_txt"), "contact us page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "maximum_characters_error_msg"), 1, "contact us page");
		validateRequiredText(retrieveChildKeyValues(jsonObj20, "email_inputfield_txt"), "contact us page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "maximum_characters_error_msg"), 2, "contact us page");
		validateRequiredText(retrieveChildKeyValues(jsonObj20, "message_inputfield_txt"), "contact us page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "valid_details_error_msg"), "contact us page");

	}

	@Test
	public void chooseLanguage_LanguageKannadaValidation_FAQs() throws UnsupportedEncodingException, FileNotFoundException, IOException, ParseException {

		JSONObject jsonObj1 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "kannadaFileName"), getTestData(16, "common_label"));
		JSONObject jsonObj4 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "kannadaFileName"), getTestData(16, "home"));
		JSONObject jsonObj17 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "kannadaFileName"), getTestData(16, "profile_right_menu"));		
		JSONObject jsonObj21 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "kannadaFileName"), getTestData(16, "faq"));

		reportStep("chooseLanguage_LanguageKannadaValidation_FAQs started", "INFO");

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo);

		String emailAddress = objCashKaroUtility.generateEmail();
		String mobileNumber = objCashKaroUtility.generateMobileNumber();	

		String otpValue = new EKOnboardingPage(driver, testInfo).
				clickOnSignUpButton().
				enterFullName().
				enterEmail(emailAddress).
				enterPassword().
				enterMobileNumber(mobileNumber).
				clickOnGetOTPButton(mobileNumber);

		new OTPPage(driver, testInfo).
		enterOTP(otpValue).
		clickOnVerifyOTP();

		new HomePage(driver, testInfo).		
		clickOnProfileIconForSignedUser().
		clickOnChooseLanguage().
		clickKannadaLink().
		clickContinueButton().
		clickRelaunchButton();

		validateRequiredText(retrieveChildKeyValues(jsonObj4, "dynamic_home_top_Category"), "home page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "profile_tab"), "home page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj17, "help"), "profile page");

		validateRequiredText(retrieveChildKeyValues(jsonObj17, "call_us_menu"), "help page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj17, "faq_menu"), "help page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj21, "question1"), "faq page");
		//validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj21, "answer1"), "faq page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj21, "question2"), "faq page");
		//validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj21, "answer2"), "faq page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj21, "question3"), "faq page");
		//validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj21, "answer3"), "faq page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj21, "question4"), "faq page");
		//validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj21, "answer4"), "faq page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj21, "question5"), "faq page");
		//validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj21, "answer5"), "faq page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj21, "question6"), "faq page");
		//validatePartialRequiredText(retrieveChildKeyValues(jsonObj21, "answer6"), "faq page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj21, "question7"), "faq page");
		//validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj21, "answer7"), "faq page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj21, "question8"), "faq page");
		//validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj21, "answer8"), "faq page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj21, "question9"), "faq page");
		//validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj21, "answer9"), "faq page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj21, "question10"), "faq page");
		//validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj21, "answer10"), "faq page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj21, "question11"), "faq page");
		//validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj21, "answer11"), "faq page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj21, "question12"), "faq page");
		//validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj21, "answer12"), "faq page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj21, "question13"), "faq page");
		//validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj21, "answer13"), "faq page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj21, "question14"), "faq page");
		//validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj21, "answer14"), "faq page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj21, "question15"), "faq page");
		//validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj21, "answer15"), "faq page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj21, "question16"), "faq page");
		//validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj21, "answer16"), "faq page");

		validateRequiredText(retrieveChildKeyValues(jsonObj21, "faq_btn"), "faq page");


	}

	@Test
	public void chooseLanguage_LanguageKannadaValidation_History_NewUser() throws UnsupportedEncodingException, FileNotFoundException, IOException, ParseException {

		JSONObject jsonObj1 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "kannadaFileName"), getTestData(16, "common_label"));
		JSONObject jsonObj4 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "kannadaFileName"), getTestData(16, "home"));
		JSONObject jsonObj17 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "kannadaFileName"), getTestData(16, "profile_right_menu"));
		JSONObject jsonObj52 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "kannadaFileName"), getTestData(16, "my_shared_activities"));

		reportStep("chooseLanguage_LanguageKannadaValidation_History_NewUser started", "INFO");

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo);

		String emailAddress = objCashKaroUtility.generateEmail();
		String mobileNumber = objCashKaroUtility.generateMobileNumber();	

		String otpValue = new EKOnboardingPage(driver, testInfo).
				clickOnSignUpButton().
				enterFullName().
				enterEmail(emailAddress).
				enterPassword().
				enterMobileNumber(mobileNumber).
				clickOnGetOTPButton(mobileNumber);

		new OTPPage(driver, testInfo).
		enterOTP(otpValue).
		clickOnVerifyOTP();

		new HomePage(driver, testInfo).		
		clickOnProfileIconForSignedUser().
		clickOnChooseLanguage().
		clickKannadaLink().
		clickContinueButton().
		clickRelaunchButton();

		validateRequiredText(retrieveChildKeyValues(jsonObj4, "dynamic_home_top_Category"), "home page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "profile_tab"), "home page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj17, "history_menu"), "profile page");
		validateRequiredText(retrieveChildKeyValues(jsonObj17, "recent_clicks_menu"), "history page");
		validateRequiredText(retrieveChildKeyValues(jsonObj1, "whoops_msg"), "history page");
		validateRequiredText(retrieveChildKeyValues(jsonObj52, "no_shared_activites"), "history page");
		validateRequiredText(retrieveChildKeyValues(jsonObj52, "saving_click_away"), "history page");


	}

	@Test
	public void chooseLanguage_LanguageKannadaValidation_AccountSettings() throws UnsupportedEncodingException, FileNotFoundException, IOException, ParseException {

		JSONObject jsonObj1 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "kannadaFileName"), getTestData(16, "common_label"));
		JSONObject jsonObj2 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "kannadaFileName"), getTestData(16, "common_validate_label"));
		JSONObject jsonObj17 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "kannadaFileName"), getTestData(16, "profile_right_menu"));
		JSONObject jsonObj26 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "kannadaFileName"), getTestData(16, "personal_details"));
		JSONObject jsonObj27 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "kannadaFileName"), getTestData(16, "change_password"));
		JSONObject jsonObj40 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "kannadaFileName"), getTestData(16, "giftcard_wallet_inputfield"));

		reportStep("chooseLanguage_LanguageKannadaValidation_AccountSettings started", "INFO");

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo);

		String textBoxExistingPwd = "//android.widget.EditText[@content-desc='et_changePassword_currentPassword']";
		String textBoxNewPwd = "//android.widget.EditText[@content-desc='et_changePassword_fullName']";
		String textBoxConfirmNewPwd = "//android.widget.EditText[@content-desc='et_changePassword_confirmPassword']";

		String dropDownPaymentSettings = "(//android.widget.EditText)[1]";
		String textNEFT = "//android.widget.CheckedTextView[@text='Bank Payment (NEFT)']";
		String textPaytmWallet = "//android.widget.CheckedTextView[@text='Paytm Wallet']";
		String textAmazonGiftCard = "//android.widget.CheckedTextView[@text='Amazon Gift Card']";

		String textBoxPaytmMobileNumber = "//android.widget.EditText[@content-desc='et_settingsPaytm_mobile']";
		String textBoxPaytmPassword = "//android.widget.EditText[@content-desc='et_settingsPaytm_password']";

		String textBoxAmazonEmail = "//android.widget.EditText[@content-desc='et_settingsAmazon_email']";
		String textBoxAmazonPassword = "//android.widget.EditText[@content-desc='et_settingsAmazon_password']";
		String emailAddress = objCashKaroUtility.generateEmail();
		String mobileNumber = objCashKaroUtility.generateMobileNumber();	

		String otpValue = new EKOnboardingPage(driver, testInfo).
				clickOnSignUpButton().
				enterFullName().
				enterEmail(emailAddress).
				enterPassword().
				enterMobileNumber(mobileNumber).
				clickOnGetOTPButton(mobileNumber);

		new OTPPage(driver, testInfo).
		enterOTP(otpValue).
		clickOnVerifyOTP();

		new HomePage(driver, testInfo).		
		clickOnProfileIconForSignedUser().
		clickOnChooseLanguage().
		clickKannadaLink().
		clickContinueButton().
		clickRelaunchButton();

		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "profile_tab"), "home page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj17, "account_setting_menu"), "profile page");
		validateRequiredText(retrieveChildKeyValues(jsonObj17, "account_setting_menu"), "profile page");
		clickOnPartialRequiredText(retrieveChildKeyValues(jsonObj1, "personal_details_tab"), "account settings page");
		scrollFromUpToDowninApp();scrollFromUpToDowninApp();
		validateRequiredText(retrieveChildKeyValues(jsonObj26, "name_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj26, "email_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj26, "mobile_number_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj26, "receive_newsletter_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj26, "receive_referral_earning_txt"), "account settings page");
		validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj26, "note_txt"), "account settings page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "save_changes_btn"), "account settings page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj26, "no_value_change_error_msg"), "account settings page");

		clickOnPartialRequiredText(retrieveChildKeyValues(jsonObj1, "change_password_tab"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj27, "change_password_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj27, "new_password_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj27, "confirm_password_inputfield_txt"), "account settings page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "save_changes_btn"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj27, "change_password_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "current_password_error_msg"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj27, "new_password_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "new_password_error_msg"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj27, "confirm_password_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "confirm_password_error_msg"), "account settings page");

		enterTextByXpath(textBoxExistingPwd, getTestData(7, "oneUserChar"));
		enterTextByXpath(textBoxNewPwd, getTestData(7, "oneUserChar"));
		enterTextByXpath(textBoxConfirmNewPwd, getTestData(7, "oneUserChar"));
		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "save_changes_btn"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj27, "change_password_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "password_minimum_characters_error_msg"), 1, "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj27, "new_password_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "password_minimum_characters_error_msg"), 2, "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj27, "confirm_password_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "password_minimum_characters_error_msg"), 3, "account settings page");

		enterTextByXpath(textBoxExistingPwd, getTestData(7, "FiftyUserChar"));
		enterTextByXpath(textBoxNewPwd, getTestData(7, "FiftyUserChar"));
		enterTextByXpath(textBoxConfirmNewPwd, getTestData(7, "FiftyUserChar"));
		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "save_changes_btn"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj27, "change_password_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "password_maximum_characters_error_msg"), 1, "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj27, "new_password_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "password_maximum_characters_error_msg"), 2, "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj27, "confirm_password_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "password_maximum_characters_error_msg"), 3, "account settings page");


		enterTextByXpath(textBoxExistingPwd, getTestData(0, "TC001_ValidLoginPassword"));
		enterTextByXpath(textBoxNewPwd, getTestData(0, "TC001_ValidLoginPassword"));
		enterTextByXpath(textBoxConfirmNewPwd, getTestData(0, "TC001_InValidPassword"));
		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "save_changes_btn"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj27, "change_password_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj27, "new_password_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj27, "confirm_password_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "password_not_match_error_msg"), "account settings page");

		clickOnPartialRequiredText(retrieveChildKeyValues(jsonObj1, "payment_settings_tab"), "account settings page");

		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(dropDownPaymentSettings);
		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(textPaytmWallet);
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "mobile_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "confirm_password_inputfield_txt"), "account settings page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "save_changes_btn"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "mobile_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "mobile_number_error_msg"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "confirm_password_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "password_error_msg"), "account settings page");

		enterTextByXpath(textBoxPaytmMobileNumber, "1");
		enterTextByXpath(textBoxPaytmPassword, "1");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "save_changes_btn"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "mobile_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "minimum_digits_error_msg"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "confirm_password_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "password_minimum_characters_error_msg"), "account settings page");

		enterTextByXpath(textBoxPaytmPassword, getTestData(7, "FiftyUserChar"));
		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "save_changes_btn"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "confirm_password_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "password_maximum_characters_error_msg"), "account settings page");

		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(dropDownPaymentSettings);
		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(textAmazonGiftCard);

		validateRequiredText(retrieveChildKeyValues(jsonObj40, "email_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "confirm_password_inputfield_txt"), "account settings page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "save_changes_btn"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "email_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "email_error_msg"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "confirm_password_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "password_error_msg"), "account settings page");

		enterTextByXpath(textBoxAmazonEmail, "1");
		enterTextByXpath(textBoxAmazonPassword, "1");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "save_changes_btn"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "email_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "minimum_characters_error_msg"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "confirm_password_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "password_minimum_characters_error_msg"), "account settings page");

		enterTextByXpath(textBoxAmazonEmail, "asdfghjkl");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "save_changes_btn"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "email_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "valid_email_error_msg"), "account settings page");

		enterTextByXpath(textBoxAmazonEmail, getTestData(7, "FiftyUserCharWithSpace"));
		enterTextByXpath(textBoxAmazonPassword, getTestData(7, "FiftyUserChar"));
		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "save_changes_btn"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "email_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "maximum_characters_error_msg"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "confirm_password_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "maximum_characters_error_msg"), "account settings page");

		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(dropDownPaymentSettings);
		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(textNEFT);

		validateRequiredText(retrieveChildKeyValues(jsonObj40, "bank_account_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "bank_name_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "branch_name_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "bank_account_number_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "bank_ifsc_code_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "confirm_password_inputfield_txt"), "account settings page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "save_changes_btn"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "bank_account_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "account_holder_error_msg"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "bank_name_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "valid_bank_name_error_msg"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "branch_name_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "branch_minimum_digits_error_msg"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "bank_account_number_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "account_number_error_msg"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "bank_ifsc_code_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "ifsc_code_error_msg"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "confirm_password_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "password_error_msg"), "account settings page");

	}

	@Test
	public void chooseLanguage_LanguageKannadaValidation_ReferAndEarn() throws UnsupportedEncodingException, FileNotFoundException, IOException, ParseException {

		JSONObject jsonObj1 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "kannadaFileName"), getTestData(16, "common_label"));
		JSONObject jsonObj17 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "kannadaFileName"), getTestData(16, "profile_right_menu"));
		JSONObject jsonObj18 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "kannadaFileName"), getTestData(16, "referandearn"));

		reportStep("chooseLanguage_LanguageKannadaValidation_AccountSettings started", "INFO");

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo);

		String emailAddress = objCashKaroUtility.generateEmail();
		String mobileNumber = objCashKaroUtility.generateMobileNumber();	

		String otpValue = new EKOnboardingPage(driver, testInfo).
				clickOnSignUpButton().
				enterFullName().
				enterEmail(emailAddress).
				enterPassword().
				enterMobileNumber(mobileNumber).
				clickOnGetOTPButton(mobileNumber);

		new OTPPage(driver, testInfo).
		enterOTP(otpValue).
		clickOnVerifyOTP();

		new HomePage(driver, testInfo).		
		clickOnProfileIconForSignedUser().
		clickOnChooseLanguage().
		clickKannadaLink().
		clickContinueButton().
		clickRelaunchButton();

		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "profile_tab"), "home page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj17, "my_referral_menu"), "profile page");
		validateRequiredText(retrieveChildKeyValues(jsonObj17, "my_referral_menu"), "my referral page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj17, "referandearn_menu"), "my referral page");
		validateRequiredText(retrieveChildKeyValues(jsonObj17, "referandearn_menu"), "my referral page");
		scrollFromUpToDowninApp();scrollFromUpToDowninApp();
		validateRequiredText(retrieveChildKeyValues(jsonObj18, "title"), "my referral page");
		validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj18, "sub_title"), "my referral page");
		validateRequiredText(retrieveChildKeyValues(jsonObj18, "message"), "my referral page");
		validateRequiredText(retrieveChildKeyValues(jsonObj18, "referral_link_title"), "my referral page");
		validateRequiredText(retrieveChildKeyValues(jsonObj18, "copy_message_title"), "my referral page");
		validateRequiredText(retrieveChildKeyValues(jsonObj18, "tap_copy"), "my referral page");
		validateRequiredText(retrieveChildKeyValues(jsonObj18, "invite_title"), "my referral page");


	}

	@Test
	public void chooseLanguage_LanguageKannadaValidation_ReferralNetwork() throws UnsupportedEncodingException, FileNotFoundException, IOException, ParseException {

		JSONObject jsonObj1 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "kannadaFileName"), getTestData(16, "common_label"));
		JSONObject jsonObj17 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "kannadaFileName"), getTestData(16, "profile_right_menu"));
		JSONObject jsonObj19 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "kannadaFileName"), getTestData(16, "referral_network"));

		reportStep("chooseLanguage_LanguageKannadaValidation_AccountSettings started", "INFO");

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo);

		String emailAddress = objCashKaroUtility.generateEmail();
		String mobileNumber = objCashKaroUtility.generateMobileNumber();	

		String otpValue = new EKOnboardingPage(driver, testInfo).
				clickOnSignUpButton().
				enterFullName().
				enterEmail(emailAddress).
				enterPassword().
				enterMobileNumber(mobileNumber).
				clickOnGetOTPButton(mobileNumber);

		new OTPPage(driver, testInfo).
		enterOTP(otpValue).
		clickOnVerifyOTP();

		new HomePage(driver, testInfo).		
		clickOnProfileIconForSignedUser().
		clickOnChooseLanguage().
		clickKannadaLink().
		clickContinueButton().
		clickRelaunchButton();

		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "profile_tab"), "home page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj17, "my_referral_menu"), "profile page");
		validateRequiredText(retrieveChildKeyValues(jsonObj17, "my_referral_menu"), "my referral page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj17, "referral_network_menu"), "profile page");
		validateRequiredText(retrieveChildKeyValues(jsonObj17, "referral_network_menu"), "referral nw page");
		validateRequiredText(retrieveChildKeyValues(jsonObj19, "title"), "referral nw page");
		validateRequiredText(retrieveChildKeyValues(jsonObj19, "total_referral_earn_txt"), "referral nw page");
		validateRequiredText(retrieveChildKeyValues(jsonObj19, "friend_joined_txt"), "referral nw page");
		validateRequiredText(retrieveChildKeyValues(jsonObj19, "no_referral_title"), "referral nw page");
		validateRequiredText(retrieveChildKeyValues(jsonObj19, "no_referral_message"), "referral nw page");
		validateRequiredText(retrieveChildKeyValues(jsonObj19, "refer_earn_btn"), "referral nw page");


	}

	@Test
	public void chooseLanguage_LanguageKannadaValidation_MyEarnings_NewUser() throws UnsupportedEncodingException, FileNotFoundException, IOException, ParseException {

		JSONObject jsonObj1 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "kannadaFileName"), getTestData(16, "common_label"));
		JSONObject jsonObj4 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "kannadaFileName"), getTestData(16, "home"));
		JSONObject jsonObj17 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "kannadaFileName"), getTestData(16, "profile_right_menu"));
		JSONObject jsonObj28 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "kannadaFileName"), getTestData(16, "my_account_earnings"));
		JSONObject jsonObj30 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "kannadaFileName"), getTestData(16, "earnings_info"));

		JSONObject jsonObj33 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "kannadaFileName"), getTestData(16, "my_earnings"));

		reportStep("chooseLanguage_LanguageKannadaValidation_MyEarnings_NewUser started", "INFO");

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo);

		String linkEarningsInfo = "//android.widget.TextView[@content-desc='txt_myEarnings_cashbackEarningsInfo']";

		String emailAddress = objCashKaroUtility.generateEmail();
		String mobileNumber = objCashKaroUtility.generateMobileNumber();	

		String otpValue = new EKOnboardingPage(driver, testInfo).
				clickOnSignUpButton().
				enterFullName().
				enterEmail(emailAddress).
				enterPassword().
				enterMobileNumber(mobileNumber).
				clickOnGetOTPButton(mobileNumber);

		new OTPPage(driver, testInfo).
		enterOTP(otpValue).
		clickOnVerifyOTP();

		new HomePage(driver, testInfo).		
		clickOnProfileIconForSignedUser().
		clickOnChooseLanguage().
		clickKannadaLink().
		clickContinueButton().
		clickRelaunchButton();

		validateRequiredText(retrieveChildKeyValues(jsonObj4, "dynamic_home_top_Category"), "home page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "profile_tab"), "home page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj17, "my_earnings_menu"), "profile page");
		validateRequiredText(retrieveChildKeyValues(jsonObj17, "my_earnings_menu"), "my earnings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj28, "total_earning_txt"), "my earnings page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj33, "cashback_tap"), "my earnings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj33, "cashback_earning_title"), "my earnings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj33, "request_cashback_btn"), "my earnings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj33, "no_cashback_yet_txt"), "my earnings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj33, "shop_today_txt"), "my earnings page");

		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(linkEarningsInfo);
		validateRequiredText(retrieveChildKeyValues(jsonObj30, "message_first_txt"), "earnings info page");


		backButton();

		clickOnRequiredText(retrieveChildKeyValues(jsonObj33, "referral_tap"), "my earnings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj33, "referral_earning_title"), "my earnings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj33, "request_cashback_btn"), "my earnings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj33, "no_referral_yet_txt"), "my earnings page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj33, "no_referral_yet_sub_txt"), "my earnings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj33, "no_referral_invite_some_friends"), "my earnings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj33, "refer_now_btn"), "my earnings page");


	}

	@Test
	public void chooseLanguage_LanguageKannadaValidation_MakeLinks_NewUser() throws UnsupportedEncodingException, FileNotFoundException, IOException, ParseException {

		JSONObject jsonObj1 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "kannadaFileName"), getTestData(16, "common_label"));
		JSONObject jsonObj2 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "kannadaFileName"), getTestData(16, "common_validate_label"));
		JSONObject jsonObj4 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "kannadaFileName"), getTestData(16, "home"));
		JSONObject jsonObj17 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "kannadaFileName"), getTestData(16, "profile_right_menu"));
		JSONObject jsonObj51 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "kannadaFileName"), getTestData(16, "make_profit_links"));
		JSONObject jsonObj53 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "kannadaFileName"), getTestData(16, "profit_partners_details"));
		JSONObject jsonObj54 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "kannadaFileName"), getTestData(16, "profit_partners_list"));

		reportStep("chooseLanguage_LanguageKannadaValidation_MakeLinks_NewUser started", "INFO");

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo);

		String imagePartner = "//android.widget.ImageView[@content-desc='img_profitPartnersList_partnersIMG']";
		String emailAddress = objCashKaroUtility.generateEmail();
		String mobileNumber = objCashKaroUtility.generateMobileNumber();	

		String otpValue = new EKOnboardingPage(driver, testInfo).
				clickOnSignUpButton().
				enterFullName().
				enterEmail(emailAddress).
				enterPassword().
				enterMobileNumber(mobileNumber).
				clickOnGetOTPButton(mobileNumber);

		new OTPPage(driver, testInfo).
		enterOTP(otpValue).
		clickOnVerifyOTP();

		new HomePage(driver, testInfo).		
		clickOnProfileIconForSignedUser().
		clickOnChooseLanguage().
		clickKannadaLink().
		clickContinueButton().
		clickRelaunchButton();

		validateRequiredText(retrieveChildKeyValues(jsonObj4, "dynamic_home_top_Category"), "home page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "profile_tab"), "home page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj17, "make_profit_link_menu"), "profile page");
		validateRequiredText(retrieveChildKeyValues(jsonObj51, "title"), "make links page");
		validateRequiredText(retrieveChildKeyValues(jsonObj51, "subtitle"), "make links page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj51, "see_partner_txt"), "make links page");
		validateRequiredText(retrieveChildKeyValues(jsonObj54, "toolbar_profit_partners_title"), "partners page");
		validateRequiredText(retrieveChildKeyValues(jsonObj54, "profit_partners_information"), "partners page");

		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(imagePartner);
		validateRequiredText(retrieveChildKeyValues(jsonObj53, "profit_rates"), "store page");

		backButton();
		backButton();

		validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj51, "paste_link"), "make links page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj51, "make_profit_link_txt"), "make links page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "url_error_msg"), "make links page");
		validateRequiredText(retrieveChildKeyValues(jsonObj51, "how_make_profit_txt"), "make links page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "url_error_msg"), "make links page");

		//Here no need to scroll : since the swiping happens from left to right
		scrollFromDownToUpinApp();
		validateRequiredText_WithOutScrolling(retrieveChildKeyValues(jsonObj51, "step_one_title"), "make links page");
		validateRequiredText_WithOutScrolling(retrieveChildKeyValues(jsonObj51, "step_one_txt"), "make links page");
		validateRequiredText_WithOutScrolling(retrieveChildKeyValues(jsonObj51, "step_two_title"), "make links page");
		validateRequiredText_WithOutScrolling(retrieveChildKeyValues(jsonObj51, "step_two_txt"), "make links page");
		validateRequiredText_WithOutScrolling(retrieveChildKeyValues(jsonObj51, "step_three_title"), "make links page");
		validateRequiredText_WithOutScrolling(retrieveChildKeyValues(jsonObj51, "step_three_txt"), "make links page");
		validateRequiredText_WithOutScrolling(retrieveChildKeyValues(jsonObj51, "step_four_title"), "make links page");
		validateRequiredText_WithOutScrolling(retrieveChildKeyValues(jsonObj51, "step_four_txt"), "make links page");


	}

	@Test
	public void chooseLanguage_LanguageKannadaValidation_GetStarted_Joinfree_TermsAndConditions() throws UnsupportedEncodingException, FileNotFoundException, IOException, ParseException {

		JSONObject jsonObj1 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "kannadaFileName"), getTestData(16, "common_label"));
		JSONObject jsonObj2 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "kannadaFileName"), getTestData(16, "common_validate_label"));
		JSONObject jsonObj17 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "kannadaFileName"), getTestData(16, "profile_right_menu"));
		JSONObject jsonObj34 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "kannadaFileName"), getTestData(16, "on_boarding"));
		JSONObject jsonObj36 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "kannadaFileName"), getTestData(16, "join_free"));
		JSONObject jsonObj50 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "kannadaFileName"), getTestData(16, "terms_conditions"));

		reportStep("chooseLanguage_LanguageKannadaValidation_GetStarted_Joinfree_Login_ForgotPassword started", "INFO");

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo);

		String textboxName = "//android.widget.EditText[@content-desc='et_signup_fullname']";
		String textboxEmail = "//android.widget.EditText[@content-desc='et_signup_email']|//android.widget.EditText[@content-desc='et_signin_email']|//android.widget.EditText[@content-desc='et_forgotpassword_email']";
		String textboxPwd = "//android.widget.EditText[@content-desc='et_signup_password']|//android.widget.EditText[@content-desc='et_signin_password']";
		String textboxMobileNumber = "//android.widget.EditText[@content-desc='et_signup_mobile']";		
		String emailAddress = objCashKaroUtility.generateEmail();
		String mobileNumber = objCashKaroUtility.generateMobileNumber();	

		String otpValue = new EKOnboardingPage(driver, testInfo).
				clickOnSignUpButton().
				enterFullName().
				enterEmail(emailAddress).
				enterPassword().
				enterMobileNumber(mobileNumber).
				clickOnGetOTPButton(mobileNumber);

		new OTPPage(driver, testInfo).
		enterOTP(otpValue).
		clickOnVerifyOTP();

		new HomePage(driver, testInfo).		
		clickOnProfileIconForSignedUser().
		clickOnChooseLanguage().
		clickKannadaLink().
		clickContinueButton().
		clickRelaunchButton();

		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "profile_tab"), "home page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj17, "logout_menu"), "profile page");
		validateRequiredText(retrieveChildKeyValues(jsonObj17, "alert_logout"), "logout popup");
		validateRequiredText(retrieveChildKeyValues(jsonObj17, "alert_logout_msg"), "logout popup");
		validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj17, "alert_cancel_btn"), "logout popup");
		clickOnPartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj17, "alert_ok_btn"), "logout popup");

		new EKOnboardingPage(driver, testInfo).
		clickOnSignUpButton();

		validateRequiredText(retrieveChildKeyValues(jsonObj17, "join_free_menu"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "title"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "subtitle"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "join_facebook_txt"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "join_or_txt"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "name_inputfield_txt"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "email_inputfield_txt"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "password_inputfield_txt"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "mobile_inputfield_txt"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "otp_btn"), "join free page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj36, "already_register"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "sign_in_txt"), "join free page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj36, "signup_terms_txt"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "terms_condition_txt"), "join free page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj36, "otp_btn"), "join free page");		
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "name_inputfield_txt"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "name_error_msg"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "email_inputfield_txt"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "email_error_msg"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "password_inputfield_txt"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "password_error_msg"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "mobile_inputfield_txt"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "mobile_number_error_msg"), "join free page");

		enterTextByXpath(textboxName, getTestData(7, "oneUserChar"));
		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(textboxEmail);
		clickOnNoneOFTheAbove();
		enterTextByXpath(textboxEmail, getTestData(7, "oneUserChar"));
		enterTextByXpath(textboxPwd, getTestData(7, "oneUserChar"));
		enterTextByXpath(textboxMobileNumber, getTestData(7, "oneUserChar"));
		clickOnRequiredText(retrieveChildKeyValues(jsonObj36, "otp_btn"), "join free page");		
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "name_inputfield_txt"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "minimum_characters_error_msg"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "email_inputfield_txt"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "minimum_characters_error_msg"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "password_inputfield_txt"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "password_minimum_characters_error_msg"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "mobile_inputfield_txt"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "minimum_digits_error_msg"), "join free page");

		enterTextByXpath(textboxName, getTestData(7, "FiftyUserCharWithSpace"));
		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(textboxEmail);
		clickOnNoneOFTheAbove();
		enterTextByXpath(textboxEmail, getTestData(7, "FiftyUserCharWithSpace"));
		enterTextByXpath(textboxPwd, getTestData(7, "FiftyUserChar"));
		enterTextByXpath(textboxMobileNumber, "2222222222");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj36, "otp_btn"), "join free page");		
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "name_inputfield_txt"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "maximum_characters_error_msg"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "email_inputfield_txt"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "maximum_characters_error_msg"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "password_inputfield_txt"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "password_maximum_characters_error_msg"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "mobile_inputfield_txt"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "valid_mobile_number_error_msg"), "join free page");

		enterTextByXpath(textboxName, "2*2");
		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(textboxEmail);
		clickOnNoneOFTheAbove();
		enterTextByXpath(textboxEmail, "2*2");
		enterTextByXpath(textboxPwd, "2 2 2 2 2");
		enterTextByXpath(textboxMobileNumber, "2*22222222");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj36, "otp_btn"), "join free page");		
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "name_inputfield_txt"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "valid_name_error_msg"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "email_inputfield_txt"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "valid_email_error_msg"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "password_inputfield_txt"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "valid_password_error_msg"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "mobile_inputfield_txt"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "valid_mobile_number_error_msg"), "join free page");

		backButton();

		new EKOnboardingPage(driver, testInfo).
		clickOnSignUpButton();
		clickOnRequiredText(retrieveChildKeyValues(jsonObj36, "terms_condition_txt"), "join free page");
		scrollFromUpToDowninApp();scrollFromUpToDowninApp();
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "terms_condition_txt"), "terms & conditions page");
		validateRequiredText(retrieveChildKeyValues(jsonObj50, "title"), "terms & conditions page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj50, "introduction_title"), "terms & conditions page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj50, "introduction_details"), "terms & conditions page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj50, "membership_title"), "terms & conditions page");
		validateRequiredText(retrieveChildKeyValues(jsonObj50, "membership_details"), "terms & conditions page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj50, "our_service_title"), "terms & conditions page");
		validateRequiredText(retrieveChildKeyValues(jsonObj50, "our_service_details"), "terms & conditions page");


	}

	@Test
	public void chooseLanguage_LanguageKannadaValidation_Login() throws UnsupportedEncodingException, FileNotFoundException, IOException, ParseException {

		JSONObject jsonObj1 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "kannadaFileName"), getTestData(16, "common_label"));
		JSONObject jsonObj2 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "kannadaFileName"), getTestData(16, "common_validate_label"));
		JSONObject jsonObj17 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "kannadaFileName"), getTestData(16, "profile_right_menu"));
		JSONObject jsonObj35 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "kannadaFileName"), getTestData(16, "sign_in"));
		JSONObject jsonObj36 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "kannadaFileName"), getTestData(16, "join_free"));

		reportStep("chooseLanguage_LanguageKannadaValidation_GetStarted_Joinfree_Login_ForgotPassword started", "INFO");

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo);

		String textboxEmail = "//android.widget.EditText[@content-desc='et_signup_email']|//android.widget.EditText[@content-desc='et_signin_email']|//android.widget.EditText[@content-desc='et_forgotpassword_email']";
		String textboxPwd = "//android.widget.EditText[@content-desc='et_signup_password']|//android.widget.EditText[@content-desc='et_signin_password']";
		String emailAddress = objCashKaroUtility.generateEmail();
		String mobileNumber = objCashKaroUtility.generateMobileNumber();	

		String otpValue = new EKOnboardingPage(driver, testInfo).
				clickOnSignUpButton().
				enterFullName().
				enterEmail(emailAddress).
				enterPassword().
				enterMobileNumber(mobileNumber).
				clickOnGetOTPButton(mobileNumber);

		new OTPPage(driver, testInfo).
		enterOTP(otpValue).
		clickOnVerifyOTP();

		new HomePage(driver, testInfo).		
		clickOnProfileIconForSignedUser().
		clickOnChooseLanguage().
		clickKannadaLink().
		clickContinueButton().
		clickRelaunchButton();

		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "profile_tab"), "home page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj17, "logout_menu"), "profile page");
		validateRequiredText(retrieveChildKeyValues(jsonObj17, "alert_logout"), "logout popup");
		validateRequiredText(retrieveChildKeyValues(jsonObj17, "alert_logout_msg"), "logout popup");
		validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj17, "alert_cancel_btn"), "logout popup");
		clickOnPartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj17, "alert_ok_btn"), "logout popup");

		new EKOnboardingPage(driver, testInfo).
		clickOnSignInbutton();

		validateRequiredText(retrieveChildKeyValues(jsonObj36, "sign_in_txt"), "login page");
		validateRequiredText(retrieveChildKeyValues(jsonObj35, "subtitle"), "login page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj35, "login_txt"), "login page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj35, "share_txt"), "login page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj35, "earn_txt"), "login page");
		validateRequiredText(retrieveChildKeyValues(jsonObj35, "subtitle"), "login page");
		validateRequiredText(retrieveChildKeyValues(jsonObj35, "sign_in_facebook_txt"), "login page");
		validateRequiredText(retrieveChildKeyValues(jsonObj35, "sign_in_or_txt"), "login page");
		validateRequiredText(retrieveChildKeyValues(jsonObj35, "email_inputfield_txt"), "login page");
		validateRequiredText(retrieveChildKeyValues(jsonObj35, "password_inputfield_txt"), "login page");
		validateRequiredText(retrieveChildKeyValues(jsonObj35, "forgot_password_txt"), "login page");
		validateRequiredText(retrieveChildKeyValues(jsonObj35, "sign_with_email_btn"), "login page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj35, "new_user_txt"), "login page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj35, "join_free_txt"), "login page");


		clickOnRequiredText(retrieveChildKeyValues(jsonObj35, "sign_with_email_btn"), "login page");
		validateRequiredText(retrieveChildKeyValues(jsonObj35, "email_inputfield_txt"), "login page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "email_error_msg"), "login page");
		validateRequiredText(retrieveChildKeyValues(jsonObj35, "password_inputfield_txt"), "login page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "password_error_msg"), "login page");

		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(textboxEmail);
		clickOnNoneOFTheAbove();
		enterTextByXpath(textboxEmail, getTestData(7, "oneUserChar"));
		enterTextByXpath(textboxPwd, getTestData(7, "oneUserChar"));
		clickOnRequiredText(retrieveChildKeyValues(jsonObj35, "sign_with_email_btn"), "login page");
		validateRequiredText(retrieveChildKeyValues(jsonObj35, "email_inputfield_txt"), "login page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "minimum_characters_error_msg"), "login page");
		validateRequiredText(retrieveChildKeyValues(jsonObj35, "password_inputfield_txt"), "login page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "password_minimum_characters_error_msg"), "login page");

		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(textboxEmail);
		clickOnNoneOFTheAbove();
		enterTextByXpath(textboxEmail, getTestData(7, "FiftyUserCharWithSpace"));
		enterTextByXpath(textboxPwd, getTestData(7, "FiftyUserChar"));
		clickOnRequiredText(retrieveChildKeyValues(jsonObj35, "sign_with_email_btn"), "login page");
		validateRequiredText(retrieveChildKeyValues(jsonObj35, "email_inputfield_txt"), "login page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "maximum_characters_error_msg"), "login page");
		validateRequiredText(retrieveChildKeyValues(jsonObj35, "password_inputfield_txt"), "login page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "password_maximum_characters_error_msg"), "login page");

		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(textboxEmail);
		clickOnNoneOFTheAbove();
		enterTextByXpath(textboxEmail, "2*2");
		enterTextByXpath(textboxPwd, "2 2 2 2 2");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj35, "sign_with_email_btn"), "login page");
		validateRequiredText(retrieveChildKeyValues(jsonObj35, "email_inputfield_txt"), "login page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "valid_email_error_msg"), "login page");
		validateRequiredText(retrieveChildKeyValues(jsonObj35, "password_inputfield_txt"), "login page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "valid_password_error_msg"), "login page");


	}

	@Test
	public void chooseLanguage_LanguageKannadaValidation_ForgotPassword() throws UnsupportedEncodingException, FileNotFoundException, IOException, ParseException {

		JSONObject jsonObj1 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "kannadaFileName"), getTestData(16, "common_label"));
		JSONObject jsonObj2 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "kannadaFileName"), getTestData(16, "common_validate_label"));
		JSONObject jsonObj17 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "kannadaFileName"), getTestData(16, "profile_right_menu"));
		JSONObject jsonObj35 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "kannadaFileName"), getTestData(16, "sign_in"));
		JSONObject jsonObj37 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "kannadaFileName"), getTestData(16, "forgot_password"));

		reportStep("chooseLanguage_LanguageKannadaValidation_GetStarted_Joinfree_Login_ForgotPassword started", "INFO");

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo);

		String textboxEmail = "//android.widget.EditText[@content-desc='et_signup_email']|//android.widget.EditText[@content-desc='et_signin_email']|//android.widget.EditText[@content-desc='et_forgotpassword_email']";
		String emailAddress = objCashKaroUtility.generateEmail();
		String mobileNumber = objCashKaroUtility.generateMobileNumber();	

		String otpValue = new EKOnboardingPage(driver, testInfo).
				clickOnSignUpButton().
				enterFullName().
				enterEmail(emailAddress).
				enterPassword().
				enterMobileNumber(mobileNumber).
				clickOnGetOTPButton(mobileNumber);

		new OTPPage(driver, testInfo).
		enterOTP(otpValue).
		clickOnVerifyOTP();

		new HomePage(driver, testInfo).		
		clickOnProfileIconForSignedUser().
		clickOnChooseLanguage().
		clickKannadaLink().
		clickContinueButton().
		clickRelaunchButton();

		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "profile_tab"), "home page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj17, "logout_menu"), "profile page");
		validateRequiredText(retrieveChildKeyValues(jsonObj17, "alert_logout"), "logout popup");
		validateRequiredText(retrieveChildKeyValues(jsonObj17, "alert_logout_msg"), "logout popup");
		validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj17, "alert_cancel_btn"), "logout popup");
		clickOnPartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj17, "alert_ok_btn"), "logout popup");

		new EKOnboardingPage(driver, testInfo).
		clickOnSignInbutton();


		clickOnRequiredText(retrieveChildKeyValues(jsonObj35, "forgot_password_txt"), "login page");
		validateRequiredText(retrieveChildKeyValues(jsonObj35, "forgot_password_txt"), "forgot password page");
		validateRequiredText(retrieveChildKeyValues(jsonObj37, "title"), "forgot password page");
		validateRequiredText(retrieveChildKeyValues(jsonObj37, "subtitle"), "forgot password page");
		validateRequiredText(retrieveChildKeyValues(jsonObj37, "email_inputfield_txt"), "forgot password page");
		validateRequiredText(retrieveChildKeyValues(jsonObj37, "password_link_txt"), "forgot password page");
		validateRequiredText(retrieveChildKeyValues(jsonObj37, "submit_btn"), "forgot password page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj37, "remember_password_txt"), "forgot password page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj37, "login_txt"), "forgot password page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj37, "submit_btn"), "forgot password page");
		validateRequiredText(retrieveChildKeyValues(jsonObj37, "email_inputfield_txt"), "forgot password page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "email_error_msg"), "login page");

		enterTextByXpath(textboxEmail, getTestData(7, "oneUserChar"));		
		clickOnRequiredText(retrieveChildKeyValues(jsonObj37, "submit_btn"), "forgot password page");
		validateRequiredText(retrieveChildKeyValues(jsonObj37, "email_inputfield_txt"), "forgot password page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "minimum_characters_error_msg"), "login page");

		enterTextByXpath(textboxEmail, getTestData(7, "FiftyUserCharWithSpace"));		
		clickOnRequiredText(retrieveChildKeyValues(jsonObj37, "submit_btn"), "forgot password page");
		validateRequiredText(retrieveChildKeyValues(jsonObj37, "email_inputfield_txt"), "forgot password page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "maximum_characters_error_msg"), "login page");

		enterTextByXpath(textboxEmail, "2*2");		
		clickOnRequiredText(retrieveChildKeyValues(jsonObj37, "submit_btn"), "forgot password page");
		validateRequiredText(retrieveChildKeyValues(jsonObj37, "email_inputfield_txt"), "forgot password page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "valid_email_error_msg"), "login page");


	}

	@Test
	public void chooseLanguage_LanguageKannadaValidation_MyEarnings_History_Payment_ExistingUser() throws UnsupportedEncodingException, FileNotFoundException, IOException, ParseException {

		JSONObject jsonObj1 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "kannadaFileName"), getTestData(16, "common_label"));
		JSONObject jsonObj2 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "kannadaFileName"), getTestData(16, "common_validate_label"));
		JSONObject jsonObj14 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "kannadaFileName"), getTestData(16, "search_result_all"));
		JSONObject jsonObj17 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "kannadaFileName"), getTestData(16, "profile_right_menu"));
		JSONObject jsonObj28 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "kannadaFileName"), getTestData(16, "my_account_earnings"));
		JSONObject jsonObj29 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "kannadaFileName"), getTestData(16, "total_cashback_earned"));
		JSONObject jsonObj30 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "kannadaFileName"), getTestData(16, "earnings_info"));		
		JSONObject jsonObj31 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "kannadaFileName"), getTestData(16, "earnings_referral"));
		JSONObject jsonObj32 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "kannadaFileName"), getTestData(16, "earnings_cashback"));
		JSONObject jsonObj33 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "kannadaFileName"), getTestData(16, "my_earnings"));
		JSONObject jsonObj35 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "kannadaFileName"), getTestData(16, "sign_in"));
		JSONObject jsonObj36 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "kannadaFileName"), getTestData(16, "join_free"));
		JSONObject jsonObj40 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "kannadaFileName"), getTestData(16, "giftcard_wallet_inputfield"));
		JSONObject jsonObj46 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "kannadaFileName"), getTestData(16, "payment"));
		JSONObject jsonObj52 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "kannadaFileName"), getTestData(16, "my_shared_activities"));
		JSONObject jsonObj53 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "kannadaFileName"), getTestData(16, "giftcard_wallet_request_payment"));

		reportStep("chooseLanguage_LanguageKannadaValidation_MyEarnings_History_Payment_ExistingUser started", "INFO");

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo);

		String dropDownPaymentSettings = "(//android.widget.EditText)[1]";
		String textNEFT = "//android.widget.CheckedTextView[@text='Bank Payment (NEFT)']";
		String textPaytmWallet = "//android.widget.CheckedTextView[@text='Paytm Wallet']";
		String textAmazonGiftCard = "//android.widget.CheckedTextView[@text='Amazon Gift Card']";
		String textBoxPaytmMobileNumberPaymentRequestPage = "//android.widget.EditText[@content-desc='et_requestpaymentPaytm_mobile']";
		String textBoxAmazonEmailPaymentRequestPage = "//android.widget.EditText[@content-desc='et_requestpaymentAmazon_email']";
		String linkEarningsInfoPaymentPage = "//android.widget.TextView[@content-desc='txt_payment_earningsInfo']";	
		String textboxName = "//android.widget.EditText[@content-desc='et_signup_fullname']";
		String textboxEmail = "//android.widget.EditText[@content-desc='et_signup_email']|//android.widget.EditText[@content-desc='et_signin_email']|//android.widget.EditText[@content-desc='et_forgotpassword_email']";
		String textboxPwd = "//android.widget.EditText[@content-desc='et_signup_password']|//android.widget.EditText[@content-desc='et_signin_password']";
		String textboxMobileNumber = "//android.widget.EditText[@content-desc='et_signup_mobile']";
		String searchIcon = "(//android.widget.TextView)[2]";
		String textAreaSearchIcon = "//android.widget.EditText[@content-desc='et_search_enterValue']";

		String cbStoreNameOne = CashKaroUtility.extractValuesFromStoreCategoryJsonAndLoadTheVariablesForStore("CB_Store_One", 0, "CB_Store_One_Name");
		String linkURL = CashKaroUtility.extractValuesFromStoreCategoryJsonAndLoadTheVariablesForStore("CB_Store_One", 0, "CB_Store_One_linkURL");

		String emailAddress = objCashKaroUtility.generateEmail();
		String mobileNumber = objCashKaroUtility.generateMobileNumber();	

		String emailAddressReferralJoinee = objCashKaroUtility.generateEmail();
		String mobileNumberReferralJoinee = objCashKaroUtility.generateMobileNumber();	

		String otpValue = new EKOnboardingPage(driver, testInfo).
				clickOnSignUpButton().
				enterFullName().
				enterEmail(emailAddress).
				enterPassword().
				enterMobileNumber(mobileNumber).
				clickOnGetOTPButton(mobileNumber);

		new OTPPage(driver, testInfo).
		enterOTP(otpValue).
		clickOnVerifyOTP();

		new HomePage(driver, testInfo).		
		clickOnProfileIconForSignedUser().
		clickOnChooseLanguage().
		clickKannadaLink().
		clickContinueButton().
		clickRelaunchButton();

		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "profile_tab"), "home page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj17, "logout_menu"), "profile page");
		validateRequiredText(retrieveChildKeyValues(jsonObj17, "alert_logout"), "logout popup");
		validateRequiredText(retrieveChildKeyValues(jsonObj17, "alert_logout_msg"), "logout popup");
		validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj17, "alert_cancel_btn"), "logout popup");
		clickOnPartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj17, "alert_ok_btn"), "logout popup");

		new EKOnboardingPage(driver, testInfo);

		CashKaroUtility cu = new CashKaroUtility(udid, port, automationName, deviceName);
		driver = cu.launchChromeWebView(driver);

		AdminCoreFunction adminFunctions = new AdminCoreFunction(driver, testInfo);
		adminFunctions.naviagateAdminPage_ThenSelectEarnKaro();
		adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
		adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
		adminFunctions.clickSubmit();

		AdminUsersPage usersPage = new AdminUsersPage(driver, testInfo);
		adminFunctions.clickOnHamburgerMenu();
		adminFunctions.clickUsersMenu();
		adminFunctions.clickUsersSubMenu();
		usersPage.selectSearchByDropDown("User Email");
		usersPage.enterKeyword(emailAddress);
		usersPage.clickSubmitButton();
		String userId = usersPage.extractUserIdValueFromResultstableFirstRow();
		String userName = usersPage.extractUserNameValueFromResultstableFirstRow();

		CashKaroUtility reopenCKApp1 = new CashKaroUtility(udid, port, automationName, deviceName, systemPort);
		driver = reopenCKApp1.launchEarnKaroApp(driver);

		CashKaroUtility objcashKaroutility = new CashKaroUtility(udid, port, automationName, deviceName);
		AppiumDriver<MobileElement> driver2 = objcashKaroutility.launchChrome();
		driver = driver2;

		String url = Utilities.referralLinkGetCode(userId, userName);

		objcashKaroutility.closeAllTabsAndOneTabInChromeBrowser(driver);
		objcashKaroutility.clickAndEnterURLInSearchOrTypeWebAddress(driver, url);

		objcashKaroutility.clickJoinFreeLink(driver);

		enterTextByXpath(textboxName, "ReferralJoinee");
		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(textboxEmail);
		clickOnNoneOFTheAbove();
		enterTextByXpath(textboxEmail, emailAddressReferralJoinee);
		enterTextByXpath(textboxPwd, "ppounds");
		enterTextByXpath(textboxMobileNumber, mobileNumberReferralJoinee);
		clickOnRequiredText(retrieveChildKeyValues(jsonObj36, "otp_btn"), "join free page");	

		new OTPPage(driver, testInfo);

		String otp = Utilities.getSignUPOTP(mobileNumberReferralJoinee);

		new OTPPage(driver, testInfo).
		enterOTP(otp);

		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "verify_otp"), "otp page");	

		sleep(15000);

		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(searchIcon);
		hideKeyboard();
		//validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj13, "search_inputfield_txt"), "search page");
		enterTextByXpath(textAreaSearchIcon, cbStoreNameOne);
		pressEnter();
		validateRequiredText(retrieveChildKeyValues(jsonObj14, "offer_count_single_message"), "search page");
		validateRequiredText(retrieveChildKeyValues(jsonObj1, "add_to_share"), "search page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "share_now"), "search page");

		WhatsAppUtility whatsAppUtility = new WhatsAppUtility(driver, testInfo, udid, port, automationName, deviceName, systemPort);
		whatsAppUtility.clickOnWhatAppMenuItemSearch().
		enterWhatsAppSearchText("Mallik").
		clickOnRequiredContact().
		clickOnSend().
		clickOnSend().
		clickOnLastSharedLink();

		new IntermediateRetailerPage(driver, testInfo).
		validateRetailerPage(linkURL, linkURL);

		CashKaroUtility cu2 = new CashKaroUtility(udid, port, automationName, deviceName);
		cu = cu2;
		driver = cu.launchChromeWebView(driver);

		AdminCoreFunction adminFunctions2 = new AdminCoreFunction(driver, testInfo);
		adminFunctions = adminFunctions2;
		adminFunctions.naviagateAdminPage_ThenSelectEarnKaro();
		adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
		adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
		adminFunctions.clickSubmit();

		AdminReportsPage reportsPage = new AdminReportsPage(driver, testInfo);
		adminFunctions.clickOnHamburgerMenu();
		adminFunctions.clickOnReports();
		reportsPage.clickExitClickSubMenu();
		reportsPage.clickClear();
		reportsPage.selectSearchByDropDown("User Email");
		reportsPage.enterKeyword(emailAddressReferralJoinee);
		reportsPage.clickSubmit();

		String exitClickOne = reportsPage.extractRequiredExitIdUsingStoreNameExitClickType(cbStoreNameOne, getTestData(7, "Normal"));

		AdminCashbackPage ProfitPage = new AdminCashbackPage(driver, testInfo);
		adminFunctions.clickOnHamburgerMenu();
		adminFunctions.clickCashbackMenu();
		ProfitPage.clickCashbackSubMenu();

		ProfitPage.clickAddNewButton();
		ProfitPage.enterExitId(exitClickOne);
		ProfitPage.entertransactionId(exitClickOne);
		ProfitPage.enterOrderId(exitClickOne);
		ProfitPage.setOrderDate();
		ProfitPage.enterOrderValue("1000");
		ProfitPage.enterconfirmCommisionNetwork(getTestData(11, "EightHundered"));
		ProfitPage.entercashback("700");
		ProfitPage.selectCashbackTypeFromDropDown(getTestData(11, "Cashback"));
		ProfitPage.selectCashbackStatusFromDropDown(getTestData(11, "Pending"));
		ProfitPage.setPendingDate();
		ProfitPage.enterDetails(exitClickOne);
		ProfitPage.clickSubmit();
		ProfitPage.clickClear();

		CashKaroUtility reopenCKAppAgain = new CashKaroUtility(udid, port, automationName, deviceName, systemPort);
		driver = reopenCKAppAgain.launchEarnKaroApp(driver);
		new HomePage(driver, testInfo);

		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "profile_tab"), "home page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj17, "history_menu"), "profile page");
		validateRequiredText(retrieveChildKeyValues(jsonObj1, "recent_clicks_tab"), "history page");
		validateRequiredText(retrieveChildKeyValues(jsonObj52, "title"), "history page");
		validateRequiredText(retrieveChildKeyValues(jsonObj52, "shared_link_details"), "history page");
		validateRequiredText(retrieveChildKeyValues(jsonObj52, "shared_id"), "history page");
		validateRequiredText(retrieveChildKeyValues(jsonObj52, "shared_date_time"), "history page");
		validateRequiredText(retrieveChildKeyValues(jsonObj52, "retailer_name"), "history page");
		validateRequiredText(retrieveChildKeyValues(jsonObj52, "no_of_clicks"), "history page");
		validateRequiredText(retrieveChildKeyValues(jsonObj52, "no_of_transactions"), "history page");
		validateRequiredText(retrieveChildKeyValues(jsonObj52, "total_earning"), "history page");

		backButton();

		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "profile_tab"), "home page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj17, "my_earnings_menu"), "profile page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj29, "total_cashback_earned_txt"), "my earnings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj29, "total_cashback_earned_txt"), 1, "profit page");
		validateRequiredText(retrieveChildKeyValues(jsonObj29, "total_cashback_earned_txt"), 2, "profit page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj29, "paid_cashback_txt"), "profit page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj29, "pending_cashback_txt"), "profit page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj29, "referral_cashback_txt"), "profit page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj29, "available_cashback_txt"), "profit page");

		backButton();

		validateRequiredText(retrieveChildKeyValues(jsonObj32, "order_details"), "my earnings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj32, "date"), "my earnings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj32, "retailer"), "my earnings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj32, "order_amount"), "my earnings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj32, "cashback_amount"), "my earnings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj32, "status"), "my earnings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj32, "expected_date"), "my earnings page");

		backButton();

		clickOnRequiredText(retrieveChildKeyValues(jsonObj17, "logout_menu"), "profile page");
		validateRequiredText(retrieveChildKeyValues(jsonObj17, "alert_logout"), "logout popup");
		validateRequiredText(retrieveChildKeyValues(jsonObj17, "alert_logout_msg"), "logout popup");
		validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj17, "alert_cancel_btn"), "logout popup");
		clickOnPartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj17, "alert_ok_btn"), "logout popup");

		new EKOnboardingPage(driver, testInfo);

		CashKaroUtility cu3 = new CashKaroUtility(udid, port, automationName, deviceName);
		cu = cu3;
		driver = cu.launchChromeWebView(driver);

		AdminCoreFunction adminFunctions3 = new AdminCoreFunction(driver, testInfo);
		adminFunctions = adminFunctions3;
		adminFunctions.naviagateAdminPage_ThenSelectEarnKaro();
		adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
		adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
		adminFunctions.clickSubmit();

		AdminCashbackPage ProfitPage1 = new AdminCashbackPage(driver, testInfo);
		ProfitPage = ProfitPage1;
		adminFunctions.clickOnHamburgerMenu();
		adminFunctions.clickCashbackMenu();
		ProfitPage.clickCashbackSubMenu();

		ProfitPage.selectSearchByStatusFromDropDown("Exit ID");
		ProfitPage.enterKeyword(exitClickOne);
		ProfitPage.selectCashbackStatusFromDropDown(getTestData(11, "Pending"));
		ProfitPage.clickSubmit();
		ProfitPage.clickEdit();
		ProfitPage.selectCashbackStatusFromDropDown(getTestData(11, "Confirmed"));
		ProfitPage.setConfirmDate();
		ProfitPage.clickSubmit();
		ProfitPage.clickClear();

		CashKaroUtility reopenCKAppOnceAgain = new CashKaroUtility(udid, port, automationName, deviceName, systemPort);
		driver = reopenCKAppOnceAgain.launchEarnKaroApp(driver);

		new EKOnboardingPage(driver, testInfo).clickOnSignInbutton();

		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(textboxEmail);
		clickOnNoneOFTheAbove();
		enterTextByXpath(textboxEmail, emailAddress);
		enterTextByXpath(textboxPwd, "ppounds");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj35, "sign_with_email_btn"), "login page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "profile_tab"), "home page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj17, "my_earnings_menu"), "profile page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj33, "referral_tap"), "my earnings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj31, "order_details"), "my earnings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj31, "date"), "my earnings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj31, "referral_name"), "my earnings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj31, "cashback"), "my earnings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj31, "status"), "my earnings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj33, "refer_now_btn"), "my earnings page");

		backButton();

		clickOnRequiredText(retrieveChildKeyValues(jsonObj17, "logout_menu"), "profile page");
		validateRequiredText(retrieveChildKeyValues(jsonObj17, "alert_logout"), "logout popup");
		validateRequiredText(retrieveChildKeyValues(jsonObj17, "alert_logout_msg"), "logout popup");
		validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj17, "alert_cancel_btn"), "logout popup");
		clickOnPartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj17, "alert_ok_btn"), "logout popup");

		new EKOnboardingPage(driver, testInfo).clickOnSignInbutton();

		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(textboxEmail);
		clickOnNoneOFTheAbove();
		enterTextByXpath(textboxEmail, emailAddressReferralJoinee);
		enterTextByXpath(textboxPwd, "ppounds");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj35, "sign_with_email_btn"), "login page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "profile_tab"), "home page");
		validateRequiredText(retrieveChildKeyValues(jsonObj1, "profile_tab"), "profile page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj17, "hello_user_txt"), "profile page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj17, "total_cashback_txt"), "profile page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj17, "payment_menu"), "profile page");

		validateRequiredText(retrieveChildKeyValues(jsonObj17, "payment_menu"), "profile page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj29, "total_cashback_earned_txt"), "payments page");
		validateRequiredText(retrieveChildKeyValues(jsonObj29, "total_cashback_earned_txt"), 1, "profit page");
		validateRequiredText(retrieveChildKeyValues(jsonObj29, "total_cashback_earned_txt"), 2, "profit page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj29, "paid_cashback_txt"), "profit page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj29, "pending_cashback_txt"), "profit page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj29, "referral_cashback_txt"), "profit page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj29, "available_cashback_txt"), "profit page");

		backButton();

		validatePartialRequiredText(retrieveChildKeyValues(jsonObj28, "tap_to_see_details_txt"), "payments page");
		validateRequiredText(retrieveChildKeyValues(jsonObj46, "cashback_earnings"), "payments page");
		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(linkEarningsInfoPaymentPage);
		validateRequiredText(retrieveChildKeyValues(jsonObj30, "message_first_txt"), "earnings info page");


		backButton();

		validateRequiredText(retrieveChildKeyValues(jsonObj46, "request_cashback_btn"), "payments page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj46, "static_text_title"), "payments page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj46, "static_text_1"), "payments page");


		clickOnRequiredText(retrieveChildKeyValues(jsonObj46, "request_cashback_btn"), "payments page");

		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(dropDownPaymentSettings);
		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(textPaytmWallet);
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "mobile_inputfield_txt"), "payment request page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "get_paid_btn"), "payment req page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "mobile_inputfield_txt"), "payment request page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "mobile_number_error_msg"), "payment request page");

		enterTextByXpath(textBoxPaytmMobileNumberPaymentRequestPage, "1");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "get_paid_btn"), "payment req page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "mobile_inputfield_txt"), "payment request page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "minimum_digits_error_msg"), "payment request page");

		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(dropDownPaymentSettings);
		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(textAmazonGiftCard);

		validateRequiredText(retrieveChildKeyValues(jsonObj53, "email_inputfield_txt"), "payment request page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "get_paid_btn"), "payment req page");
		validateRequiredText(retrieveChildKeyValues(jsonObj53, "email_inputfield_txt"), "payment request page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "email_error_msg"), "payment request page");

		enterTextByXpath(textBoxAmazonEmailPaymentRequestPage, "1");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "get_paid_btn"), "payment req page");
		validateRequiredText(retrieveChildKeyValues(jsonObj53, "email_inputfield_txt"), "payment request page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "minimum_characters_error_msg"), "payment request page");

		enterTextByXpath(textBoxAmazonEmailPaymentRequestPage, "asdfghjkl");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "get_paid_btn"), "payment req page");
		validateRequiredText(retrieveChildKeyValues(jsonObj53, "email_inputfield_txt"), "payment request page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "valid_email_error_msg"), "payment request page");

		enterTextByXpath(textBoxAmazonEmailPaymentRequestPage, getTestData(7, "FiftyUserCharWithSpace"));
		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "get_paid_btn"), "payment req page");
		validateRequiredText(retrieveChildKeyValues(jsonObj53, "email_inputfield_txt"), "payment request page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "maximum_characters_error_msg"), "payment request page");

		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(dropDownPaymentSettings);
		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(textNEFT);

		validateRequiredText(retrieveChildKeyValues(jsonObj40, "bank_account_inputfield_txt"), "payment request page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "bank_name_inputfield_txt"), "payment request page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "branch_name_inputfield_txt"), "payment request page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "bank_account_number_inputfield_txt"), "payment request page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "bank_ifsc_code_inputfield_txt"), "payment request page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "get_paid_btn"), "payment req page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "bank_account_inputfield_txt"), "payment request page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "account_holder_error_msg"), "payment request page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "bank_name_inputfield_txt"), "payment request page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "valid_bank_name_error_msg"), "payment request page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "branch_name_inputfield_txt"), "payment request page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "branch_minimum_digits_error_msg"), "payment request page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "bank_account_number_inputfield_txt"), "payment request page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "account_number_error_msg"), "payment request page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "bank_ifsc_code_inputfield_txt"), "payment request page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "ifsc_code_error_msg"), "payment request page");


	}

	@Test
	public void chooseLanguage_LanguageKannadaValidation_StoreCategory_StoreDetail() throws UnsupportedEncodingException, FileNotFoundException, IOException, ParseException {

		JSONObject jsonObj1 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "kannadaFileName"), getTestData(16, "common_label"));
		JSONObject jsonObj3 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "kannadaFileName"), getTestData(16, "main_menu"));
		JSONObject jsonObj5 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "kannadaFileName"), getTestData(16, "store_category"));
		JSONObject jsonObj12 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "kannadaFileName"), getTestData(16, "share_counts"));
		JSONObject jsonObj17 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "kannadaFileName"), getTestData(16, "profile_right_menu"));

		reportStep("chooseLanguage_LanguageKannadaValidation_Store_Product_StoreCategory started", "INFO");

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo);

		String shareButtonWithCount = "//android.view.ViewGroup[@content-desc='txt_sharecount_open_modal']/android.view.ViewGroup/android.widget.TextView[@text='1']";
		String imageStoreCard = "//android.widget.ImageView[@content-desc='img_storeCard_imageValue']";
		String emailAddress = objCashKaroUtility.generateEmail();
		String mobileNumber = objCashKaroUtility.generateMobileNumber();	

		String otpValue = new EKOnboardingPage(driver, testInfo).
				clickOnSignUpButton().
				enterFullName().
				enterEmail(emailAddress).
				enterPassword().
				enterMobileNumber(mobileNumber).
				clickOnGetOTPButton(mobileNumber);

		new OTPPage(driver, testInfo).
		enterOTP(otpValue).
		clickOnVerifyOTP();

		new HomePage(driver, testInfo).		
		clickOnProfileIconForSignedUser().
		clickOnChooseLanguage().
		clickKannadaLink().
		clickContinueButton().
		clickRelaunchButton();

		validateRequiredText(retrieveChildKeyValues(jsonObj3, "home_lbl"), "home page");

		new HomePage(driver, testInfo).
		clickOnHamburgerIcon();

		validateRequiredText(retrieveChildKeyValues(jsonObj3, "home_lbl"), "home page");

		new SliderScreenPage(driver, testInfo).
		clickRetailerCategory().
		verifyRetailerCategoryMenuNavigation().
		clickAutomationSpecificCategoryInRetailerCategoryMenuUntilAllSubCategoryVisible().
		clickAutomationSpecificSubCategoryOne();

		validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj5, "popular"), "store category page");
		validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj5, "az"), "store category page");
		validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj5, "percent"), "store category page");
		validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj5, "amount"), "store category page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "add_to_share"), "store category page");
		validateRequiredText(retrieveChildKeyValues(jsonObj1, "share_now"), "store category page");
		validateRequiredText(retrieveChildKeyValues(jsonObj1, "remove"), "store category page");

		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(shareButtonWithCount);
		validateRequiredText(retrieveChildKeyValues(jsonObj1, "share_now"), "store category page");
		validateRequiredText(retrieveChildKeyValues(jsonObj1, "copy_Link"), "store category page");
		validateRequiredText(retrieveChildKeyValues(jsonObj12, "close"), "store category page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj12, "clear"), "store category page");

		validateRequiredText(retrieveChildKeyValues(jsonObj12, "clear"), "store category page");
		validateRequiredText(retrieveChildKeyValues(jsonObj12, "confirm_msg_clear_share"), "store category page");
		validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj17, "alert_cancel_btn"), "store category page");
		clickOnPartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj17, "alert_ok_btn"), "store category page");

		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(imageStoreCard);
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj1, "you_will_earn"), "store detail page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "add_to_share"), "store detail page");
		validateRequiredText(retrieveChildKeyValues(jsonObj1, "share_now"), "store detail page");
		validateRequiredText(retrieveChildKeyValues(jsonObj1, "remove"), "store category page");

		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(shareButtonWithCount);
		validateRequiredText(retrieveChildKeyValues(jsonObj1, "share_now"), "store detail page");
		validateRequiredText(retrieveChildKeyValues(jsonObj1, "copy_Link"), "store detail page");
		validateRequiredText(retrieveChildKeyValues(jsonObj12, "close"), "store detail page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj12, "clear"), "store detail page");

		validateRequiredText(retrieveChildKeyValues(jsonObj12, "clear"), "store detail page");
		validateRequiredText(retrieveChildKeyValues(jsonObj12, "confirm_msg_clear_share"), "store detail page");
		validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj17, "alert_cancel_btn"), "store detail page");
		clickOnPartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj17, "alert_ok_btn"), "store detail page");

		new StoreDetailPage(driver, testInfo);

	}

	@Test
	public void chooseLanguage_LanguageKannadaValidation_Product() throws UnsupportedEncodingException, FileNotFoundException, IOException, ParseException {

		JSONObject jsonObj1 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "kannadaFileName"), getTestData(16, "common_label"));
		JSONObject jsonObj3 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "kannadaFileName"), getTestData(16, "main_menu"));
		JSONObject jsonObj6 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "kannadaFileName"), getTestData(16, "prduct_category"));
		JSONObject jsonObj9 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "kannadaFileName"), getTestData(16, "product_details"));
		JSONObject jsonObj10 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "kannadaFileName"), getTestData(16, "product_card"));
		JSONObject jsonObj49 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "kannadaFileName"), getTestData(16, "prduct_filter"));

		reportStep("chooseLanguage_LanguageKannadaValidation_Store_Product_StoreCategory started", "INFO");

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo);

		String menFashionCategory = "//android.view.ViewGroup[@content-desc='txt_mainMenu_productDealsTop']";
		String tshirtCategory = "//android.widget.TextView[@content-desc='txt_subMenuProduct_mainMenuName']";
		String menfashionLink = "//android.widget.TextView[@content-desc='txt_filter_category_name']";
		String imageProductCard = "//android.widget.ImageView[@content-desc='img_product_image']";
		String productcard = "(//android.widget.ImageView)[3]";		
		String emailAddress = objCashKaroUtility.generateEmail();
		String mobileNumber = objCashKaroUtility.generateMobileNumber();	

		String otpValue = new EKOnboardingPage(driver, testInfo).
				clickOnSignUpButton().
				enterFullName().
				enterEmail(emailAddress).
				enterPassword().
				enterMobileNumber(mobileNumber).
				clickOnGetOTPButton(mobileNumber);

		new OTPPage(driver, testInfo).
		enterOTP(otpValue).
		clickOnVerifyOTP();

		new HomePage(driver, testInfo).		
		clickOnProfileIconForSignedUser().
		clickOnChooseLanguage().
		clickKannadaLink().
		clickContinueButton().
		clickRelaunchButton();

		validateRequiredText(retrieveChildKeyValues(jsonObj3, "home_lbl"), "home page");

		new HomePage(driver, testInfo).
		clickOnHamburgerIcon();

		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(menFashionCategory);
		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(tshirtCategory);

		clickOnPartialRequiredText(retrieveChildKeyValues(jsonObj6, "filter"), "product page");
		validateRequiredText(retrieveChildKeyValues(jsonObj49, "filter_by"), "product page");
		validateRequiredText(retrieveChildKeyValues(jsonObj49, "clerar_all"), "product page");
		validateRequiredText(retrieveChildKeyValues(jsonObj49, "close_btn"), "product page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj49, "apply_btn"), "product page");

		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(menfashionLink);
		clickOnRequiredText(retrieveChildKeyValues(jsonObj49, "clerar_all"), "product page");
		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(menfashionLink);
		clickOnRequiredText(retrieveChildKeyValues(jsonObj49, "close_btn"), "product page");
		validateRequiredText(retrieveChildKeyValues(jsonObj49, "discard_changes_title"), "product page");
		validateRequiredText(retrieveChildKeyValues(jsonObj49, "discard_changes_msg"), "product page");
		validateRequiredText(retrieveChildKeyValues(jsonObj49, "apply_changes_btn"), "product page");
		validateRequiredText(retrieveChildKeyValues(jsonObj49, "discard_changes_btn"), "product page");

		backButton();

		clickOnPartialRequiredText(retrieveChildKeyValues(jsonObj6, "sort"), "product page");
		validateRequiredText(retrieveChildKeyValues(jsonObj6, "sort_title"), "product page");
		validateRequiredText(retrieveChildKeyValues(jsonObj6, "sort_popular"), "product page");
		validateRequiredText(retrieveChildKeyValues(jsonObj6, "sort_discount"), "product page");
		validateRequiredText(retrieveChildKeyValues(jsonObj6, "sort_high_price"), "product page");
		validateRequiredText(retrieveChildKeyValues(jsonObj6, "sort_low_price"), "product page");

		backButton();

		validatePartialRequiredText(retrieveChildKeyValues(jsonObj10, "brand"), "product page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj10, "price"), "product page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj10, "sale"), "product page");
		validateRequiredText(retrieveChildKeyValues(jsonObj1, "add_to_share"), "product page");
		validateRequiredText(retrieveChildKeyValues(jsonObj1, "share_now"), "product page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj10, "see_details"), "product page");

		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(imageProductCard);
		clickOnRequiredText(retrieveChildKeyValues(jsonObj9, "seller_price"), "product detail page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "you_will_earn"), "product detail page");
		validateRequiredText(retrieveChildKeyValues(jsonObj1, "add_to_share"), "product page");
		validateRequiredText(retrieveChildKeyValues(jsonObj1, "share_now"), "product page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj9, "product_highlights"), "product detail page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj9, "similar_products"), "product detail page");



	}

	@Test
	public void chooseLanguage_LanguageEnglishValidation_SuccessStories() throws UnsupportedEncodingException, FileNotFoundException, IOException, ParseException {

		JSONObject jsonObj1 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "englishFileName"), getTestData(16, "common_label"));
		JSONObject jsonObj4 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "englishFileName"), getTestData(16, "home"));
		JSONObject jsonObj17 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "englishFileName"), getTestData(16, "profile_right_menu"));
		JSONObject jsonObj22 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "englishFileName"), getTestData(16, "success_stories"));

		reportStep("chooseLanguage_LanguageEnglishValidation_SuccessStories started", "INFO");

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo);

		String emailAddress = objCashKaroUtility.generateEmail();
		String mobileNumber = objCashKaroUtility.generateMobileNumber();	

		String otpValue = new EKOnboardingPage(driver, testInfo).
				clickOnSignUpButton().
				enterFullName().
				enterEmail(emailAddress).
				enterPassword().
				enterMobileNumber(mobileNumber).
				clickOnGetOTPButton(mobileNumber);

		new OTPPage(driver, testInfo).
		enterOTP(otpValue).
		clickOnVerifyOTP();

		new HomePage(driver, testInfo).		
		clickOnProfileIconForSignedUser().
		clickOnChooseLanguage().
		clickEnglishLink().
		clickContinueButton().
		clickRelaunchButton();

		validateRequiredText(retrieveChildKeyValues(jsonObj4, "dynamic_home_top_Category"), "home page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "profile_tab"), "home page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj17, "success_stories_menu"), "profile page");
		validateRequiredText(retrieveChildKeyValues(jsonObj17, "success_stories_menu"), "success stories page");
		scrollFromUpToDowninApp();scrollFromUpToDowninApp();
		validateRequiredText(retrieveChildKeyValues(jsonObj22, "title"), "success stories page");
		validateRequiredText(retrieveChildKeyValues(jsonObj22, "testimonial_txt1"), "success stories page");
		validateRequiredText(retrieveChildKeyValues(jsonObj22, "testimonial_name1"), "success stories page");
		validateRequiredText(retrieveChildKeyValues(jsonObj22, "testimonial_txt2"), "success stories page");
		validateRequiredText(retrieveChildKeyValues(jsonObj22, "testimonial_name2"), "success stories page");
		validateRequiredText(retrieveChildKeyValues(jsonObj22, "testimonial_txt3"), "success stories page");
		validateRequiredText(retrieveChildKeyValues(jsonObj22, "testimonial_name3"), "success stories page");
		validateRequiredText(retrieveChildKeyValues(jsonObj22, "testimonial_txt4"), "success stories page");
		validateRequiredText(retrieveChildKeyValues(jsonObj22, "testimonial_name4"), "success stories page");
		validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj22, "testimonial_txt5"), "success stories page");
		validateRequiredText(retrieveChildKeyValues(jsonObj22, "testimonial_name5"), "success stories page");


	}

	@Test
	public void chooseLanguage_LanguageEnglishValidation_HowItWorks() throws UnsupportedEncodingException, FileNotFoundException, IOException, ParseException {

		JSONObject jsonObj1 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "englishFileName"), getTestData(16, "common_label"));
		JSONObject jsonObj4 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "englishFileName"), getTestData(16, "home"));
		JSONObject jsonObj17 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "englishFileName"), getTestData(16, "profile_right_menu"));
		JSONObject jsonObj23 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "englishFileName"), getTestData(16, "how_it_works"));

		reportStep("chooseLanguage_LanguageEnglishValidation_HowItWorks started", "INFO");

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo);

		String emailAddress = objCashKaroUtility.generateEmail();
		String mobileNumber = objCashKaroUtility.generateMobileNumber();	

		String otpValue = new EKOnboardingPage(driver, testInfo).
				clickOnSignUpButton().
				enterFullName().
				enterEmail(emailAddress).
				enterPassword().
				enterMobileNumber(mobileNumber).
				clickOnGetOTPButton(mobileNumber);

		new OTPPage(driver, testInfo).
		enterOTP(otpValue).
		clickOnVerifyOTP();

		new HomePage(driver, testInfo).		
		clickOnProfileIconForSignedUser().
		clickOnChooseLanguage().
		clickEnglishLink().
		clickContinueButton().
		clickRelaunchButton();

		validateRequiredText(retrieveChildKeyValues(jsonObj4, "dynamic_home_top_Category"), "home page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "profile_tab"), "home page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj17, "how_it_works_menu"), "profile page");
		scrollFromUpToDowninApp();scrollFromUpToDowninApp();
		validateRequiredText(retrieveChildKeyValues(jsonObj17, "how_it_works_menu"), "how earnkaro works? page");
		validateRequiredText(retrieveChildKeyValues(jsonObj23, "watch_video_title1"), "how earnkaro works? page");
		validateRequiredText(retrieveChildKeyValues(jsonObj23, "watch_video_txt"), 1, "how earnkaro works? page");
		validateRequiredText(retrieveChildKeyValues(jsonObj23, "watch_video_title2"), "how earnkaro works? page");
		validateRequiredText(retrieveChildKeyValues(jsonObj23, "watch_video_txt"), 2, "how earnkaro works? page");
		validateRequiredText(retrieveChildKeyValues(jsonObj23, "watch_video_title3"), "how earnkaro works? page");
		validateRequiredText(retrieveChildKeyValues(jsonObj23, "watch_video_txt"), 3, "how earnkaro works? page");
		validateRequiredText(retrieveChildKeyValues(jsonObj23, "watch_video_title4"), "how earnkaro works? page");
		validateRequiredText(retrieveChildKeyValues(jsonObj23, "watch_video_txt"), 4, "how earnkaro works? page");
		validateRequiredText(retrieveChildKeyValues(jsonObj23, "watch_video_title5"), "how earnkaro works? page");
		validateRequiredText(retrieveChildKeyValues(jsonObj23, "watch_video_txt"), 5, "how earnkaro works? page");
		validateRequiredText(retrieveChildKeyValues(jsonObj23, "watch_video_title6"), "how earnkaro works? page");
		validateRequiredText(retrieveChildKeyValues(jsonObj23, "watch_video_txt"), 6, "how earnkaro works? page");



	}

	@Test
	public void chooseLanguage_LanguageEnglishValidation_Testimonials() throws UnsupportedEncodingException, FileNotFoundException, IOException, ParseException {

		JSONObject jsonObj1 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "englishFileName"), getTestData(16, "common_label"));
		JSONObject jsonObj4 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "englishFileName"), getTestData(16, "home"));
		JSONObject jsonObj17 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "englishFileName"), getTestData(16, "profile_right_menu"));
		JSONObject jsonObj24 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "englishFileName"), getTestData(16, "testimonial"));
		JSONObject jsonObj25 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "englishFileName"), getTestData(16, "testimonial_write"));

		reportStep("chooseLanguage_LanguageEnglishValidation_Testimonials started", "INFO");

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo);

		String fourStarTestimonials = "(//android.widget.TextView)[8]";
		String threeStarTestimonials = "(//android.widget.TextView)[7]";
		String twoStarTestimonials = "(//android.widget.TextView)[6]";
		String oneStarTestimonials = "(//android.widget.TextView)[5]";

		String emailAddress = objCashKaroUtility.generateEmail();
		String mobileNumber = objCashKaroUtility.generateMobileNumber();	

		String otpValue = new EKOnboardingPage(driver, testInfo).
				clickOnSignUpButton().
				enterFullName().
				enterEmail(emailAddress).
				enterPassword().
				enterMobileNumber(mobileNumber).
				clickOnGetOTPButton(mobileNumber);

		new OTPPage(driver, testInfo).
		enterOTP(otpValue).
		clickOnVerifyOTP();

		new HomePage(driver, testInfo).		
		clickOnProfileIconForSignedUser().
		clickOnChooseLanguage().
		clickEnglishLink().
		clickContinueButton().
		clickRelaunchButton();

		validateRequiredText(retrieveChildKeyValues(jsonObj4, "dynamic_home_top_Category"), "home page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "profile_tab"), "home page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj17, "rate_us_menu"), "profile page");
		validateRequiredText(retrieveChildKeyValues(jsonObj24, "count_txt"), "testimonials page");
		validateRequiredText(retrieveChildKeyValues(jsonObj24, "happy_users_txt"), "testimonials page");

		clickOnPartialRequiredText(retrieveChildKeyValues(jsonObj24, "write_testimonial_btn"), "testimonials page");
		validateRequiredText(retrieveChildKeyValues(jsonObj24, "write_testimonial_btn"), "write testimonials page");
		validateRequiredText(retrieveChildKeyValues(jsonObj25, "title"), "write testimonials page");

		validateRequiredText(retrieveChildKeyValues(jsonObj25, "rate_experience_txt"), "write testimonials page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj25, "star_txt_awesome_experience"), "write testimonials page");
		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(fourStarTestimonials);
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj25, "star_txt_good_experience"), "write testimonials page");
		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(threeStarTestimonials);
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj25, "star_txt_average"), "write testimonials page");
		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(twoStarTestimonials);
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj25, "star_txt_not_great"), "write testimonials page");
		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(oneStarTestimonials);
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj25, "star_txt_terrible"), "write testimonials page");


	}

	@Test
	public void chooseLanguage_LanguageEnglishValidation_ContactUs() throws UnsupportedEncodingException, FileNotFoundException, IOException, ParseException {

		JSONObject jsonObj1 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "englishFileName"), getTestData(16, "common_label"));
		JSONObject jsonObj2 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "englishFileName"), getTestData(16, "common_validate_label"));
		JSONObject jsonObj4 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "englishFileName"), getTestData(16, "home"));
		JSONObject jsonObj17 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "englishFileName"), getTestData(16, "profile_right_menu"));
		JSONObject jsonObj20 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "englishFileName"), getTestData(16, "contact_us"));		
		JSONObject jsonObj21 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "englishFileName"), getTestData(16, "faq"));

		reportStep("chooseLanguage_LanguageEnglishValidation_ContactUs started", "INFO");

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo);

		String textBoxUserName = "//android.widget.EditText[@content-desc='et_contactUs_yourName']";
		String textBoxEmail = "//android.widget.EditText[@content-desc='et_contactUs_email']";

		String emailAddress = objCashKaroUtility.generateEmail();
		String mobileNumber = objCashKaroUtility.generateMobileNumber();	

		String otpValue = new EKOnboardingPage(driver, testInfo).
				clickOnSignUpButton().
				enterFullName().
				enterEmail(emailAddress).
				enterPassword().
				enterMobileNumber(mobileNumber).
				clickOnGetOTPButton(mobileNumber);

		new OTPPage(driver, testInfo).
		enterOTP(otpValue).
		clickOnVerifyOTP();

		new HomePage(driver, testInfo).		
		clickOnProfileIconForSignedUser().
		clickOnChooseLanguage().
		clickEnglishLink().
		clickContinueButton().
		clickRelaunchButton();

		validateRequiredText(retrieveChildKeyValues(jsonObj4, "dynamic_home_top_Category"), "home page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "profile_tab"), "home page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj17, "help"), "profile page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj17, "contact_us_menu"), "help page");
		enterTextByXpath(textBoxUserName, "..");
		enterTextByXpath(textBoxEmail, "..");
		validateRequiredText(retrieveChildKeyValues(jsonObj21, "faq_btn"), "contact us page");
		validateRequiredText(retrieveChildKeyValues(jsonObj20, "name_inputfield_txt"), "contact us page");
		validateRequiredText(retrieveChildKeyValues(jsonObj20, "email_inputfield_txt"), "contact us page");
		validateRequiredText(retrieveChildKeyValues(jsonObj20, "message_inputfield_txt"), "contact us page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj20, "submit_btn"), "contact us page");

		validateRequiredText(retrieveChildKeyValues(jsonObj2, "select_subject_error_msg"), "contact us page");
		validateRequiredText(retrieveChildKeyValues(jsonObj20, "name_inputfield_txt"), "contact us page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "valid_name_error_msg"), "contact us page");
		validateRequiredText(retrieveChildKeyValues(jsonObj20, "email_inputfield_txt"), "contact us page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "valid_email_error_msg"), "contact us page");
		validateRequiredText(retrieveChildKeyValues(jsonObj20, "message_inputfield_txt"), "contact us page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "valid_details_error_msg"), "contact us page");

		enterTextByXpath(textBoxUserName, getTestData(7, "oneUserChar"));
		enterTextByXpath(textBoxEmail, getTestData(7, "oneUserChar"));
		clickOnRequiredText(retrieveChildKeyValues(jsonObj20, "submit_btn"), "contact us page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "select_subject_error_msg"), "contact us page");
		validateRequiredText(retrieveChildKeyValues(jsonObj20, "name_inputfield_txt"), "contact us page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "minimum_characters_error_msg"), 1, "contact us page");
		validateRequiredText(retrieveChildKeyValues(jsonObj20, "email_inputfield_txt"), "contact us page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "minimum_characters_error_msg"), 2, "contact us page");
		validateRequiredText(retrieveChildKeyValues(jsonObj20, "message_inputfield_txt"), "contact us page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "valid_details_error_msg"), "contact us page");

		enterTextByXpath(textBoxUserName, getTestData(7, "FiftyPlusUserChar"));
		enterTextByXpath(textBoxEmail, getTestData(7, "FiftyPlusUserChar"));
		clickOnRequiredText(retrieveChildKeyValues(jsonObj20, "submit_btn"), "contact us page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "select_subject_error_msg"), "contact us page");
		validateRequiredText(retrieveChildKeyValues(jsonObj20, "name_inputfield_txt"), "contact us page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "maximum_characters_error_msg"), 1, "contact us page");
		validateRequiredText(retrieveChildKeyValues(jsonObj20, "email_inputfield_txt"), "contact us page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "maximum_characters_error_msg"), 2, "contact us page");
		validateRequiredText(retrieveChildKeyValues(jsonObj20, "message_inputfield_txt"), "contact us page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "valid_details_error_msg"), "contact us page");

	}

	@Test
	public void chooseLanguage_LanguageEnglishValidation_FAQs() throws UnsupportedEncodingException, FileNotFoundException, IOException, ParseException {

		JSONObject jsonObj1 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "englishFileName"), getTestData(16, "common_label"));
		JSONObject jsonObj4 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "englishFileName"), getTestData(16, "home"));
		JSONObject jsonObj17 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "englishFileName"), getTestData(16, "profile_right_menu"));		
		JSONObject jsonObj21 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "englishFileName"), getTestData(16, "faq"));

		reportStep("chooseLanguage_LanguageEnglishValidation_FAQs started", "INFO");

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo);

		String emailAddress = objCashKaroUtility.generateEmail();
		String mobileNumber = objCashKaroUtility.generateMobileNumber();	

		String otpValue = new EKOnboardingPage(driver, testInfo).
				clickOnSignUpButton().
				enterFullName().
				enterEmail(emailAddress).
				enterPassword().
				enterMobileNumber(mobileNumber).
				clickOnGetOTPButton(mobileNumber);

		new OTPPage(driver, testInfo).
		enterOTP(otpValue).
		clickOnVerifyOTP();

		new HomePage(driver, testInfo).		
		clickOnProfileIconForSignedUser().
		clickOnChooseLanguage().
		clickEnglishLink().
		clickContinueButton().
		clickRelaunchButton();

		validateRequiredText(retrieveChildKeyValues(jsonObj4, "dynamic_home_top_Category"), "home page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "profile_tab"), "home page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj17, "help"), "profile page");

		validateRequiredText(retrieveChildKeyValues(jsonObj17, "call_us_menu"), "help page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj17, "faq_menu"), "help page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj21, "question1"), "faq page");
		//validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj21, "answer1"), "faq page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj21, "question2"), "faq page");
		//validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj21, "answer2"), "faq page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj21, "question3"), "faq page");
		//validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj21, "answer3"), "faq page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj21, "question4"), "faq page");
		//validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj21, "answer4"), "faq page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj21, "question5"), "faq page");
		//validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj21, "answer5"), "faq page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj21, "question6"), "faq page");
		//validatePartialRequiredText(retrieveChildKeyValues(jsonObj21, "answer6"), "faq page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj21, "question7"), "faq page");
		//validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj21, "answer7"), "faq page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj21, "question8"), "faq page");
		//validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj21, "answer8"), "faq page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj21, "question9"), "faq page");
		//validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj21, "answer9"), "faq page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj21, "question10"), "faq page");
		//validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj21, "answer10"), "faq page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj21, "question11"), "faq page");
		//validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj21, "answer11"), "faq page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj21, "question12"), "faq page");
		//validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj21, "answer12"), "faq page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj21, "question13"), "faq page");
		//validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj21, "answer13"), "faq page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj21, "question14"), "faq page");
		//validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj21, "answer14"), "faq page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj21, "question15"), "faq page");
		//validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj21, "answer15"), "faq page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj21, "question16"), "faq page");
		//validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj21, "answer16"), "faq page");

		validateRequiredText(retrieveChildKeyValues(jsonObj21, "faq_btn"), "faq page");


	}

	@Test
	public void chooseLanguage_LanguageEnglishValidation_History_NewUser() throws UnsupportedEncodingException, FileNotFoundException, IOException, ParseException {

		JSONObject jsonObj1 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "englishFileName"), getTestData(16, "common_label"));
		JSONObject jsonObj4 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "englishFileName"), getTestData(16, "home"));
		JSONObject jsonObj17 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "englishFileName"), getTestData(16, "profile_right_menu"));
		JSONObject jsonObj52 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "englishFileName"), getTestData(16, "my_shared_activities"));

		reportStep("chooseLanguage_LanguageEnglishValidation_History_NewUser started", "INFO");

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo);

		String emailAddress = objCashKaroUtility.generateEmail();
		String mobileNumber = objCashKaroUtility.generateMobileNumber();	

		String otpValue = new EKOnboardingPage(driver, testInfo).
				clickOnSignUpButton().
				enterFullName().
				enterEmail(emailAddress).
				enterPassword().
				enterMobileNumber(mobileNumber).
				clickOnGetOTPButton(mobileNumber);

		new OTPPage(driver, testInfo).
		enterOTP(otpValue).
		clickOnVerifyOTP();

		new HomePage(driver, testInfo).		
		clickOnProfileIconForSignedUser().
		clickOnChooseLanguage().
		clickEnglishLink().
		clickContinueButton().
		clickRelaunchButton();

		validateRequiredText(retrieveChildKeyValues(jsonObj4, "dynamic_home_top_Category"), "home page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "profile_tab"), "home page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj17, "history_menu"), "profile page");
		validateRequiredText(retrieveChildKeyValues(jsonObj17, "recent_clicks_menu"), "history page");
		validateRequiredText(retrieveChildKeyValues(jsonObj1, "whoops_msg"), "history page");
		validateRequiredText(retrieveChildKeyValues(jsonObj52, "no_shared_activites"), "history page");
		validateRequiredText(retrieveChildKeyValues(jsonObj52, "saving_click_away"), "history page");


	}

	@Test
	public void chooseLanguage_LanguageEnglishValidation_AccountSettings() throws UnsupportedEncodingException, FileNotFoundException, IOException, ParseException {

		JSONObject jsonObj1 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "englishFileName"), getTestData(16, "common_label"));
		JSONObject jsonObj2 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "englishFileName"), getTestData(16, "common_validate_label"));
		JSONObject jsonObj17 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "englishFileName"), getTestData(16, "profile_right_menu"));
		JSONObject jsonObj26 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "englishFileName"), getTestData(16, "personal_details"));
		JSONObject jsonObj27 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "englishFileName"), getTestData(16, "change_password"));
		JSONObject jsonObj40 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "englishFileName"), getTestData(16, "giftcard_wallet_inputfield"));

		reportStep("chooseLanguage_LanguageEnglishValidation_AccountSettings started", "INFO");

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo);

		String textBoxExistingPwd = "//android.widget.EditText[@content-desc='et_changePassword_currentPassword']";
		String textBoxNewPwd = "//android.widget.EditText[@content-desc='et_changePassword_fullName']";
		String textBoxConfirmNewPwd = "//android.widget.EditText[@content-desc='et_changePassword_confirmPassword']";

		String dropDownPaymentSettings = "(//android.widget.EditText)[1]";
		String textNEFT = "//android.widget.CheckedTextView[@text='Bank Payment (NEFT)']";
		String textPaytmWallet = "//android.widget.CheckedTextView[@text='Paytm Wallet']";
		String textAmazonGiftCard = "//android.widget.CheckedTextView[@text='Amazon Gift Card']";

		String textBoxPaytmMobileNumber = "//android.widget.EditText[@content-desc='et_settingsPaytm_mobile']";
		String textBoxPaytmPassword = "//android.widget.EditText[@content-desc='et_settingsPaytm_password']";

		String textBoxAmazonEmail = "//android.widget.EditText[@content-desc='et_settingsAmazon_email']";
		String textBoxAmazonPassword = "//android.widget.EditText[@content-desc='et_settingsAmazon_password']";
		String emailAddress = objCashKaroUtility.generateEmail();
		String mobileNumber = objCashKaroUtility.generateMobileNumber();	

		String otpValue = new EKOnboardingPage(driver, testInfo).
				clickOnSignUpButton().
				enterFullName().
				enterEmail(emailAddress).
				enterPassword().
				enterMobileNumber(mobileNumber).
				clickOnGetOTPButton(mobileNumber);

		new OTPPage(driver, testInfo).
		enterOTP(otpValue).
		clickOnVerifyOTP();

		new HomePage(driver, testInfo).		
		clickOnProfileIconForSignedUser().
		clickOnChooseLanguage().
		clickEnglishLink().
		clickContinueButton().
		clickRelaunchButton();

		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "profile_tab"), "home page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj17, "account_setting_menu"), "profile page");
		validateRequiredText(retrieveChildKeyValues(jsonObj17, "account_setting_menu"), "profile page");
		clickOnPartialRequiredText(retrieveChildKeyValues(jsonObj1, "personal_details_tab"), "account settings page");
		scrollFromUpToDowninApp();scrollFromUpToDowninApp();
		validateRequiredText(retrieveChildKeyValues(jsonObj26, "name_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj26, "email_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj26, "mobile_number_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj26, "receive_newsletter_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj26, "receive_referral_earning_txt"), "account settings page");
		validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj26, "note_txt"), "account settings page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "save_changes_btn"), "account settings page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj26, "no_value_change_error_msg"), "account settings page");

		clickOnPartialRequiredText(retrieveChildKeyValues(jsonObj1, "change_password_tab"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj27, "change_password_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj27, "new_password_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj27, "confirm_password_inputfield_txt"), "account settings page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "save_changes_btn"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj27, "change_password_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "current_password_error_msg"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj27, "new_password_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "new_password_error_msg"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj27, "confirm_password_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "confirm_password_error_msg"), "account settings page");

		enterTextByXpath(textBoxExistingPwd, getTestData(7, "oneUserChar"));
		enterTextByXpath(textBoxNewPwd, getTestData(7, "oneUserChar"));
		enterTextByXpath(textBoxConfirmNewPwd, getTestData(7, "oneUserChar"));
		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "save_changes_btn"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj27, "change_password_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "password_minimum_characters_error_msg"), 1, "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj27, "new_password_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "password_minimum_characters_error_msg"), 2, "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj27, "confirm_password_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "password_minimum_characters_error_msg"), 3, "account settings page");

		enterTextByXpath(textBoxExistingPwd, getTestData(7, "FiftyUserChar"));
		enterTextByXpath(textBoxNewPwd, getTestData(7, "FiftyUserChar"));
		enterTextByXpath(textBoxConfirmNewPwd, getTestData(7, "FiftyUserChar"));
		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "save_changes_btn"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj27, "change_password_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "password_maximum_characters_error_msg"), 1, "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj27, "new_password_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "password_maximum_characters_error_msg"), 2, "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj27, "confirm_password_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "password_maximum_characters_error_msg"), 3, "account settings page");


		enterTextByXpath(textBoxExistingPwd, getTestData(0, "TC001_ValidLoginPassword"));
		enterTextByXpath(textBoxNewPwd, getTestData(0, "TC001_ValidLoginPassword"));
		enterTextByXpath(textBoxConfirmNewPwd, getTestData(0, "TC001_InValidPassword"));
		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "save_changes_btn"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj27, "change_password_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj27, "new_password_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj27, "confirm_password_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "password_not_match_error_msg"), "account settings page");

		clickOnPartialRequiredText(retrieveChildKeyValues(jsonObj1, "payment_settings_tab"), "account settings page");

		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(dropDownPaymentSettings);
		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(textPaytmWallet);
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "mobile_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "confirm_password_inputfield_txt"), "account settings page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "save_changes_btn"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "mobile_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "mobile_number_error_msg"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "confirm_password_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "password_error_msg"), "account settings page");

		enterTextByXpath(textBoxPaytmMobileNumber, "1");
		enterTextByXpath(textBoxPaytmPassword, "1");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "save_changes_btn"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "mobile_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "minimum_digits_error_msg"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "confirm_password_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "password_minimum_characters_error_msg"), "account settings page");

		enterTextByXpath(textBoxPaytmPassword, getTestData(7, "FiftyUserChar"));
		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "save_changes_btn"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "confirm_password_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "password_maximum_characters_error_msg"), "account settings page");

		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(dropDownPaymentSettings);
		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(textAmazonGiftCard);

		validateRequiredText(retrieveChildKeyValues(jsonObj40, "email_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "confirm_password_inputfield_txt"), "account settings page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "save_changes_btn"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "email_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "email_error_msg"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "confirm_password_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "password_error_msg"), "account settings page");

		enterTextByXpath(textBoxAmazonEmail, "1");
		enterTextByXpath(textBoxAmazonPassword, "1");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "save_changes_btn"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "email_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "minimum_characters_error_msg"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "confirm_password_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "password_minimum_characters_error_msg"), "account settings page");

		enterTextByXpath(textBoxAmazonEmail, "asdfghjkl");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "save_changes_btn"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "email_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "valid_email_error_msg"), "account settings page");

		enterTextByXpath(textBoxAmazonEmail, getTestData(7, "FiftyUserCharWithSpace"));
		enterTextByXpath(textBoxAmazonPassword, getTestData(7, "FiftyUserChar"));
		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "save_changes_btn"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "email_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "maximum_characters_error_msg"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "confirm_password_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "maximum_characters_error_msg"), "account settings page");

		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(dropDownPaymentSettings);
		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(textNEFT);

		validateRequiredText(retrieveChildKeyValues(jsonObj40, "bank_account_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "bank_name_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "branch_name_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "bank_account_number_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "bank_ifsc_code_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "confirm_password_inputfield_txt"), "account settings page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "save_changes_btn"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "bank_account_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "account_holder_error_msg"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "bank_name_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "valid_bank_name_error_msg"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "branch_name_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "branch_minimum_digits_error_msg"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "bank_account_number_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "account_number_error_msg"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "bank_ifsc_code_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "ifsc_code_error_msg"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "confirm_password_inputfield_txt"), "account settings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "password_error_msg"), "account settings page");

	}

	@Test
	public void chooseLanguage_LanguageEnglishValidation_ReferAndEarn() throws UnsupportedEncodingException, FileNotFoundException, IOException, ParseException {

		JSONObject jsonObj1 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "englishFileName"), getTestData(16, "common_label"));
		JSONObject jsonObj17 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "englishFileName"), getTestData(16, "profile_right_menu"));
		JSONObject jsonObj18 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "englishFileName"), getTestData(16, "referandearn"));

		reportStep("chooseLanguage_LanguageEnglishValidation_AccountSettings started", "INFO");

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo);

		String emailAddress = objCashKaroUtility.generateEmail();
		String mobileNumber = objCashKaroUtility.generateMobileNumber();	

		String otpValue = new EKOnboardingPage(driver, testInfo).
				clickOnSignUpButton().
				enterFullName().
				enterEmail(emailAddress).
				enterPassword().
				enterMobileNumber(mobileNumber).
				clickOnGetOTPButton(mobileNumber);

		new OTPPage(driver, testInfo).
		enterOTP(otpValue).
		clickOnVerifyOTP();

		new HomePage(driver, testInfo).		
		clickOnProfileIconForSignedUser().
		clickOnChooseLanguage().
		clickEnglishLink().
		clickContinueButton().
		clickRelaunchButton();

		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "profile_tab"), "home page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj17, "my_referral_menu"), "profile page");
		validateRequiredText(retrieveChildKeyValues(jsonObj17, "my_referral_menu"), "my referral page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj17, "referandearn_menu"), "my referral page");
		validateRequiredText(retrieveChildKeyValues(jsonObj17, "referandearn_menu"), "my referral page");
		scrollFromUpToDowninApp();scrollFromUpToDowninApp();
		validateRequiredText(retrieveChildKeyValues(jsonObj18, "title"), "my referral page");
		validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj18, "sub_title"), "my referral page");
		validateRequiredText(retrieveChildKeyValues(jsonObj18, "message"), "my referral page");
		validateRequiredText(retrieveChildKeyValues(jsonObj18, "referral_link_title"), "my referral page");
		validateRequiredText(retrieveChildKeyValues(jsonObj18, "copy_message_title"), "my referral page");
		validateRequiredText(retrieveChildKeyValues(jsonObj18, "tap_copy"), "my referral page");
		validateRequiredText(retrieveChildKeyValues(jsonObj18, "invite_title"), "my referral page");


	}

	@Test
	public void chooseLanguage_LanguageEnglishValidation_ReferralNetwork() throws UnsupportedEncodingException, FileNotFoundException, IOException, ParseException {

		JSONObject jsonObj1 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "englishFileName"), getTestData(16, "common_label"));
		JSONObject jsonObj17 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "englishFileName"), getTestData(16, "profile_right_menu"));
		JSONObject jsonObj19 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "englishFileName"), getTestData(16, "referral_network"));

		reportStep("chooseLanguage_LanguageEnglishValidation_AccountSettings started", "INFO");

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo);

		String emailAddress = objCashKaroUtility.generateEmail();
		String mobileNumber = objCashKaroUtility.generateMobileNumber();	

		String otpValue = new EKOnboardingPage(driver, testInfo).
				clickOnSignUpButton().
				enterFullName().
				enterEmail(emailAddress).
				enterPassword().
				enterMobileNumber(mobileNumber).
				clickOnGetOTPButton(mobileNumber);

		new OTPPage(driver, testInfo).
		enterOTP(otpValue).
		clickOnVerifyOTP();

		new HomePage(driver, testInfo).		
		clickOnProfileIconForSignedUser().
		clickOnChooseLanguage().
		clickEnglishLink().
		clickContinueButton().
		clickRelaunchButton();

		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "profile_tab"), "home page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj17, "my_referral_menu"), "profile page");
		validateRequiredText(retrieveChildKeyValues(jsonObj17, "my_referral_menu"), "my referral page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj17, "referral_network_menu"), "profile page");
		validateRequiredText(retrieveChildKeyValues(jsonObj17, "referral_network_menu"), "referral nw page");
		validateRequiredText(retrieveChildKeyValues(jsonObj19, "title"), "referral nw page");
		validateRequiredText(retrieveChildKeyValues(jsonObj19, "total_referral_earn_txt"), "referral nw page");
		validateRequiredText(retrieveChildKeyValues(jsonObj19, "friend_joined_txt"), "referral nw page");
		validateRequiredText(retrieveChildKeyValues(jsonObj19, "no_referral_title"), "referral nw page");
		validateRequiredText(retrieveChildKeyValues(jsonObj19, "no_referral_message"), "referral nw page");
		validateRequiredText(retrieveChildKeyValues(jsonObj19, "refer_earn_btn"), "referral nw page");


	}

	@Test
	public void chooseLanguage_LanguageEnglishValidation_MyEarnings_NewUser() throws UnsupportedEncodingException, FileNotFoundException, IOException, ParseException {

		JSONObject jsonObj1 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "englishFileName"), getTestData(16, "common_label"));
		JSONObject jsonObj4 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "englishFileName"), getTestData(16, "home"));
		JSONObject jsonObj17 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "englishFileName"), getTestData(16, "profile_right_menu"));
		JSONObject jsonObj28 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "englishFileName"), getTestData(16, "my_account_earnings"));
		JSONObject jsonObj30 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "englishFileName"), getTestData(16, "earnings_info"));

		JSONObject jsonObj33 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "englishFileName"), getTestData(16, "my_earnings"));

		reportStep("chooseLanguage_LanguageEnglishValidation_MyEarnings_NewUser started", "INFO");

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo);

		String linkEarningsInfo = "//android.widget.TextView[@content-desc='txt_myEarnings_cashbackEarningsInfo']";

		String emailAddress = objCashKaroUtility.generateEmail();
		String mobileNumber = objCashKaroUtility.generateMobileNumber();	

		String otpValue = new EKOnboardingPage(driver, testInfo).
				clickOnSignUpButton().
				enterFullName().
				enterEmail(emailAddress).
				enterPassword().
				enterMobileNumber(mobileNumber).
				clickOnGetOTPButton(mobileNumber);

		new OTPPage(driver, testInfo).
		enterOTP(otpValue).
		clickOnVerifyOTP();

		new HomePage(driver, testInfo).		
		clickOnProfileIconForSignedUser().
		clickOnChooseLanguage().
		clickEnglishLink().
		clickContinueButton().
		clickRelaunchButton();

		validateRequiredText(retrieveChildKeyValues(jsonObj4, "dynamic_home_top_Category"), "home page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "profile_tab"), "home page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj17, "my_earnings_menu"), "profile page");
		validateRequiredText(retrieveChildKeyValues(jsonObj17, "my_earnings_menu"), "my earnings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj28, "total_earning_txt"), "my earnings page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj33, "cashback_tap"), "my earnings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj33, "cashback_earning_title"), "my earnings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj33, "request_cashback_btn"), "my earnings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj33, "no_cashback_yet_txt"), "my earnings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj33, "shop_today_txt"), "my earnings page");

		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(linkEarningsInfo);
		validateRequiredText(retrieveChildKeyValues(jsonObj30, "message_first_txt"), "earnings info page");


		backButton();

		clickOnRequiredText(retrieveChildKeyValues(jsonObj33, "referral_tap"), "my earnings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj33, "referral_earning_title"), "my earnings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj33, "request_cashback_btn"), "my earnings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj33, "no_referral_yet_txt"), "my earnings page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj33, "no_referral_yet_sub_txt"), "my earnings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj33, "no_referral_invite_some_friends"), "my earnings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj33, "refer_now_btn"), "my earnings page");


	}

	@Test
	public void chooseLanguage_LanguageEnglishValidation_MakeLinks_NewUser() throws UnsupportedEncodingException, FileNotFoundException, IOException, ParseException {

		JSONObject jsonObj1 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "englishFileName"), getTestData(16, "common_label"));
		JSONObject jsonObj2 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "englishFileName"), getTestData(16, "common_validate_label"));
		JSONObject jsonObj4 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "englishFileName"), getTestData(16, "home"));
		JSONObject jsonObj17 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "englishFileName"), getTestData(16, "profile_right_menu"));
		JSONObject jsonObj51 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "englishFileName"), getTestData(16, "make_profit_links"));
		JSONObject jsonObj53 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "englishFileName"), getTestData(16, "profit_partners_details"));
		JSONObject jsonObj54 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "englishFileName"), getTestData(16, "profit_partners_list"));

		reportStep("chooseLanguage_LanguageEnglishValidation_MakeLinks_NewUser started", "INFO");

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo);

		String imagePartner = "//android.widget.ImageView[@content-desc='img_profitPartnersList_partnersIMG']";
		String emailAddress = objCashKaroUtility.generateEmail();
		String mobileNumber = objCashKaroUtility.generateMobileNumber();	

		String otpValue = new EKOnboardingPage(driver, testInfo).
				clickOnSignUpButton().
				enterFullName().
				enterEmail(emailAddress).
				enterPassword().
				enterMobileNumber(mobileNumber).
				clickOnGetOTPButton(mobileNumber);

		new OTPPage(driver, testInfo).
		enterOTP(otpValue).
		clickOnVerifyOTP();

		new HomePage(driver, testInfo).		
		clickOnProfileIconForSignedUser().
		clickOnChooseLanguage().
		clickEnglishLink().
		clickContinueButton().
		clickRelaunchButton();

		validateRequiredText(retrieveChildKeyValues(jsonObj4, "dynamic_home_top_Category"), "home page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "profile_tab"), "home page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj17, "make_profit_link_menu"), "profile page");
		validateRequiredText(retrieveChildKeyValues(jsonObj51, "title"), "make links page");
		validateRequiredText(retrieveChildKeyValues(jsonObj51, "subtitle"), "make links page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj51, "see_partner_txt"), "make links page");
		validateRequiredText(retrieveChildKeyValues(jsonObj54, "toolbar_profit_partners_title"), "partners page");
		validateRequiredText(retrieveChildKeyValues(jsonObj54, "profit_partners_information"), "partners page");

		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(imagePartner);
		validateRequiredText(retrieveChildKeyValues(jsonObj53, "profit_rates"), "store page");

		backButton();
		backButton();

		validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj51, "paste_link"), "make links page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj51, "make_profit_link_txt"), "make links page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "url_error_msg"), "make links page");
		validateRequiredText(retrieveChildKeyValues(jsonObj51, "how_make_profit_txt"), "make links page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "url_error_msg"), "make links page");	

		//Here no need to scroll : since the swiping happens from left to right
		scrollFromDownToUpinApp();
		validateRequiredText_WithOutScrolling(retrieveChildKeyValues(jsonObj51, "step_one_title"), "make links page");
		validateRequiredText_WithOutScrolling(retrieveChildKeyValues(jsonObj51, "step_one_txt"), "make links page");
		validateRequiredText_WithOutScrolling(retrieveChildKeyValues(jsonObj51, "step_two_title"), "make links page");
		validateRequiredText_WithOutScrolling(retrieveChildKeyValues(jsonObj51, "step_two_txt"), "make links page");
		validateRequiredText_WithOutScrolling(retrieveChildKeyValues(jsonObj51, "step_three_title"), "make links page");
		validateRequiredText_WithOutScrolling(retrieveChildKeyValues(jsonObj51, "step_three_txt"), "make links page");
		validateRequiredText_WithOutScrolling(retrieveChildKeyValues(jsonObj51, "step_four_title"), "make links page");
		validateRequiredText_WithOutScrolling(retrieveChildKeyValues(jsonObj51, "step_four_txt"), "make links page");


	}

	@Test
	public void chooseLanguage_LanguageEnglishValidation_GetStarted_Joinfree_TermsAndConditions() throws UnsupportedEncodingException, FileNotFoundException, IOException, ParseException {

		JSONObject jsonObj1 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "englishFileName"), getTestData(16, "common_label"));
		JSONObject jsonObj2 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "englishFileName"), getTestData(16, "common_validate_label"));
		JSONObject jsonObj17 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "englishFileName"), getTestData(16, "profile_right_menu"));
		JSONObject jsonObj34 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "englishFileName"), getTestData(16, "on_boarding"));
		JSONObject jsonObj36 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "englishFileName"), getTestData(16, "join_free"));
		JSONObject jsonObj50 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "englishFileName"), getTestData(16, "terms_conditions"));

		reportStep("chooseLanguage_LanguageEnglishValidation_GetStarted_Joinfree_Login_ForgotPassword started", "INFO");

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo);

		String textboxName = "//android.widget.EditText[@content-desc='et_signup_fullname']";
		String textboxEmail = "//android.widget.EditText[@content-desc='et_signup_email']|//android.widget.EditText[@content-desc='et_signin_email']|//android.widget.EditText[@content-desc='et_forgotpassword_email']";
		String textboxPwd = "//android.widget.EditText[@content-desc='et_signup_password']|//android.widget.EditText[@content-desc='et_signin_password']";
		String textboxMobileNumber = "//android.widget.EditText[@content-desc='et_signup_mobile']";		
		String emailAddress = objCashKaroUtility.generateEmail();
		String mobileNumber = objCashKaroUtility.generateMobileNumber();	

		String otpValue = new EKOnboardingPage(driver, testInfo).
				clickOnSignUpButton().
				enterFullName().
				enterEmail(emailAddress).
				enterPassword().
				enterMobileNumber(mobileNumber).
				clickOnGetOTPButton(mobileNumber);

		new OTPPage(driver, testInfo).
		enterOTP(otpValue).
		clickOnVerifyOTP();

		new HomePage(driver, testInfo).		
		clickOnProfileIconForSignedUser().
		clickOnChooseLanguage().
		clickEnglishLink().
		clickContinueButton().
		clickRelaunchButton();

		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "profile_tab"), "home page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj17, "logout_menu"), "profile page");
		validateRequiredText(retrieveChildKeyValues(jsonObj17, "alert_logout"), "logout popup");
		validateRequiredText(retrieveChildKeyValues(jsonObj17, "alert_logout_msg"), "logout popup");
		validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj17, "alert_cancel_btn"), "logout popup");
		clickOnPartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj17, "alert_ok_btn"), "logout popup");

		new EKOnboardingPage(driver, testInfo).
		clickOnSignUpButton();

		validateRequiredText(retrieveChildKeyValues(jsonObj17, "join_free_menu"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "title"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "subtitle"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "join_facebook_txt"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "join_or_txt"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "name_inputfield_txt"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "email_inputfield_txt"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "password_inputfield_txt"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "mobile_inputfield_txt"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "otp_btn"), "join free page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj36, "already_register"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "sign_in_txt"), "join free page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj36, "signup_terms_txt"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "terms_condition_txt"), "join free page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj36, "otp_btn"), "join free page");		
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "name_inputfield_txt"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "name_error_msg"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "email_inputfield_txt"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "email_error_msg"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "password_inputfield_txt"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "password_error_msg"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "mobile_inputfield_txt"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "mobile_number_error_msg"), "join free page");

		enterTextByXpath(textboxName, getTestData(7, "oneUserChar"));
		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(textboxEmail);
		clickOnNoneOFTheAbove();
		enterTextByXpath(textboxEmail, getTestData(7, "oneUserChar"));
		enterTextByXpath(textboxPwd, getTestData(7, "oneUserChar"));
		enterTextByXpath(textboxMobileNumber, getTestData(7, "oneUserChar"));
		clickOnRequiredText(retrieveChildKeyValues(jsonObj36, "otp_btn"), "join free page");		
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "name_inputfield_txt"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "minimum_characters_error_msg"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "email_inputfield_txt"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "minimum_characters_error_msg"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "password_inputfield_txt"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "password_minimum_characters_error_msg"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "mobile_inputfield_txt"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "minimum_digits_error_msg"), "join free page");

		enterTextByXpath(textboxName, getTestData(7, "FiftyUserCharWithSpace"));
		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(textboxEmail);
		clickOnNoneOFTheAbove();
		enterTextByXpath(textboxEmail, getTestData(7, "FiftyUserCharWithSpace"));
		enterTextByXpath(textboxPwd, getTestData(7, "FiftyUserChar"));
		enterTextByXpath(textboxMobileNumber, "2222222222");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj36, "otp_btn"), "join free page");		
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "name_inputfield_txt"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "maximum_characters_error_msg"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "email_inputfield_txt"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "maximum_characters_error_msg"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "password_inputfield_txt"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "password_maximum_characters_error_msg"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "mobile_inputfield_txt"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "valid_mobile_number_error_msg"), "join free page");

		enterTextByXpath(textboxName, "2*2");
		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(textboxEmail);
		clickOnNoneOFTheAbove();
		enterTextByXpath(textboxEmail, "2*2");
		enterTextByXpath(textboxPwd, "2 2 2 2 2");
		enterTextByXpath(textboxMobileNumber, "2*22222222");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj36, "otp_btn"), "join free page");		
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "name_inputfield_txt"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "valid_name_error_msg"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "email_inputfield_txt"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "valid_email_error_msg"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "password_inputfield_txt"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "valid_password_error_msg"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "mobile_inputfield_txt"), "join free page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "valid_mobile_number_error_msg"), "join free page");

		backButton();

		new EKOnboardingPage(driver, testInfo).
		clickOnSignUpButton();
		clickOnRequiredText(retrieveChildKeyValues(jsonObj36, "terms_condition_txt"), "join free page");
		scrollFromUpToDowninApp();scrollFromUpToDowninApp();
		validateRequiredText(retrieveChildKeyValues(jsonObj36, "terms_condition_txt"), "terms & conditions page");
		validateRequiredText(retrieveChildKeyValues(jsonObj50, "title"), "terms & conditions page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj50, "introduction_title"), "terms & conditions page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj50, "introduction_details"), "terms & conditions page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj50, "membership_title"), "terms & conditions page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj50, "membership_details"), "terms & conditions page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj50, "our_service_title"), "terms & conditions page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj50, "our_service_details"), "terms & conditions page");


	}

	@Test
	public void chooseLanguage_LanguageEnglishValidation_Login() throws UnsupportedEncodingException, FileNotFoundException, IOException, ParseException {

		JSONObject jsonObj1 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "englishFileName"), getTestData(16, "common_label"));
		JSONObject jsonObj2 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "englishFileName"), getTestData(16, "common_validate_label"));
		JSONObject jsonObj17 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "englishFileName"), getTestData(16, "profile_right_menu"));
		JSONObject jsonObj35 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "englishFileName"), getTestData(16, "sign_in"));
		JSONObject jsonObj36 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "englishFileName"), getTestData(16, "join_free"));

		reportStep("chooseLanguage_LanguageEnglishValidation_GetStarted_Joinfree_Login_ForgotPassword started", "INFO");

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo);

		String textboxEmail = "//android.widget.EditText[@content-desc='et_signup_email']|//android.widget.EditText[@content-desc='et_signin_email']|//android.widget.EditText[@content-desc='et_forgotpassword_email']";
		String textboxPwd = "//android.widget.EditText[@content-desc='et_signup_password']|//android.widget.EditText[@content-desc='et_signin_password']";
		String emailAddress = objCashKaroUtility.generateEmail();
		String mobileNumber = objCashKaroUtility.generateMobileNumber();	

		String otpValue = new EKOnboardingPage(driver, testInfo).
				clickOnSignUpButton().
				enterFullName().
				enterEmail(emailAddress).
				enterPassword().
				enterMobileNumber(mobileNumber).
				clickOnGetOTPButton(mobileNumber);

		new OTPPage(driver, testInfo).
		enterOTP(otpValue).
		clickOnVerifyOTP();

		new HomePage(driver, testInfo).		
		clickOnProfileIconForSignedUser().
		clickOnChooseLanguage().
		clickEnglishLink().
		clickContinueButton().
		clickRelaunchButton();

		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "profile_tab"), "home page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj17, "logout_menu"), "profile page");
		validateRequiredText(retrieveChildKeyValues(jsonObj17, "alert_logout"), "logout popup");
		validateRequiredText(retrieveChildKeyValues(jsonObj17, "alert_logout_msg"), "logout popup");
		validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj17, "alert_cancel_btn"), "logout popup");
		clickOnPartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj17, "alert_ok_btn"), "logout popup");

		new EKOnboardingPage(driver, testInfo).
		clickOnSignInbutton();

		validateRequiredText(retrieveChildKeyValues(jsonObj36, "sign_in_txt"), "login page");
		validateRequiredText(retrieveChildKeyValues(jsonObj35, "subtitle"), "login page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj35, "login_txt"), "login page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj35, "share_txt"), "login page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj35, "earn_txt"), "login page");
		validateRequiredText(retrieveChildKeyValues(jsonObj35, "subtitle"), "login page");
		validateRequiredText(retrieveChildKeyValues(jsonObj35, "sign_in_facebook_txt"), "login page");
		validateRequiredText(retrieveChildKeyValues(jsonObj35, "sign_in_or_txt"), "login page");
		validateRequiredText(retrieveChildKeyValues(jsonObj35, "email_inputfield_txt"), "login page");
		validateRequiredText(retrieveChildKeyValues(jsonObj35, "password_inputfield_txt"), "login page");
		validateRequiredText(retrieveChildKeyValues(jsonObj35, "forgot_password_txt"), "login page");
		validateRequiredText(retrieveChildKeyValues(jsonObj35, "sign_with_email_btn"), "login page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj35, "new_user_txt"), "login page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj35, "join_free_txt"), "login page");


		clickOnRequiredText(retrieveChildKeyValues(jsonObj35, "sign_with_email_btn"), "login page");
		validateRequiredText(retrieveChildKeyValues(jsonObj35, "email_inputfield_txt"), "login page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "email_error_msg"), "login page");
		validateRequiredText(retrieveChildKeyValues(jsonObj35, "password_inputfield_txt"), "login page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "password_error_msg"), "login page");

		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(textboxEmail);
		clickOnNoneOFTheAbove();
		enterTextByXpath(textboxEmail, getTestData(7, "oneUserChar"));
		enterTextByXpath(textboxPwd, getTestData(7, "oneUserChar"));
		clickOnRequiredText(retrieveChildKeyValues(jsonObj35, "sign_with_email_btn"), "login page");
		validateRequiredText(retrieveChildKeyValues(jsonObj35, "email_inputfield_txt"), "login page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "minimum_characters_error_msg"), "login page");
		validateRequiredText(retrieveChildKeyValues(jsonObj35, "password_inputfield_txt"), "login page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "password_minimum_characters_error_msg"), "login page");

		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(textboxEmail);
		clickOnNoneOFTheAbove();
		enterTextByXpath(textboxEmail, getTestData(7, "FiftyUserCharWithSpace"));
		enterTextByXpath(textboxPwd, getTestData(7, "FiftyUserChar"));
		clickOnRequiredText(retrieveChildKeyValues(jsonObj35, "sign_with_email_btn"), "login page");
		validateRequiredText(retrieveChildKeyValues(jsonObj35, "email_inputfield_txt"), "login page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "maximum_characters_error_msg"), "login page");
		validateRequiredText(retrieveChildKeyValues(jsonObj35, "password_inputfield_txt"), "login page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "password_maximum_characters_error_msg"), "login page");

		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(textboxEmail);
		clickOnNoneOFTheAbove();
		enterTextByXpath(textboxEmail, "2*2");
		enterTextByXpath(textboxPwd, "2 2 2 2 2");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj35, "sign_with_email_btn"), "login page");
		validateRequiredText(retrieveChildKeyValues(jsonObj35, "email_inputfield_txt"), "login page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "valid_email_error_msg"), "login page");
		validateRequiredText(retrieveChildKeyValues(jsonObj35, "password_inputfield_txt"), "login page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "valid_password_error_msg"), "login page");


	}

	@Test
	public void chooseLanguage_LanguageEnglishValidation_ForgotPassword() throws UnsupportedEncodingException, FileNotFoundException, IOException, ParseException {

		JSONObject jsonObj1 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "englishFileName"), getTestData(16, "common_label"));
		JSONObject jsonObj2 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "englishFileName"), getTestData(16, "common_validate_label"));
		JSONObject jsonObj17 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "englishFileName"), getTestData(16, "profile_right_menu"));
		JSONObject jsonObj35 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "englishFileName"), getTestData(16, "sign_in"));
		JSONObject jsonObj37 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "englishFileName"), getTestData(16, "forgot_password"));

		reportStep("chooseLanguage_LanguageEnglishValidation_GetStarted_Joinfree_Login_ForgotPassword started", "INFO");

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo);

		String textboxEmail = "//android.widget.EditText[@content-desc='et_signup_email']|//android.widget.EditText[@content-desc='et_signin_email']|//android.widget.EditText[@content-desc='et_forgotpassword_email']";
		String emailAddress = objCashKaroUtility.generateEmail();
		String mobileNumber = objCashKaroUtility.generateMobileNumber();	

		String otpValue = new EKOnboardingPage(driver, testInfo).
				clickOnSignUpButton().
				enterFullName().
				enterEmail(emailAddress).
				enterPassword().
				enterMobileNumber(mobileNumber).
				clickOnGetOTPButton(mobileNumber);

		new OTPPage(driver, testInfo).
		enterOTP(otpValue).
		clickOnVerifyOTP();

		new HomePage(driver, testInfo).		
		clickOnProfileIconForSignedUser().
		clickOnChooseLanguage().
		clickEnglishLink().
		clickContinueButton().
		clickRelaunchButton();

		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "profile_tab"), "home page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj17, "logout_menu"), "profile page");
		validateRequiredText(retrieveChildKeyValues(jsonObj17, "alert_logout"), "logout popup");
		validateRequiredText(retrieveChildKeyValues(jsonObj17, "alert_logout_msg"), "logout popup");
		validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj17, "alert_cancel_btn"), "logout popup");
		clickOnPartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj17, "alert_ok_btn"), "logout popup");

		new EKOnboardingPage(driver, testInfo).
		clickOnSignInbutton();


		clickOnRequiredText(retrieveChildKeyValues(jsonObj35, "forgot_password_txt"), "login page");
		validateRequiredText(retrieveChildKeyValues(jsonObj35, "forgot_password_txt"), "forgot password page");
		validateRequiredText(retrieveChildKeyValues(jsonObj37, "title"), "forgot password page");
		validateRequiredText(retrieveChildKeyValues(jsonObj37, "subtitle"), "forgot password page");
		validateRequiredText(retrieveChildKeyValues(jsonObj37, "email_inputfield_txt"), "forgot password page");
		validateRequiredText(retrieveChildKeyValues(jsonObj37, "password_link_txt"), "forgot password page");
		validateRequiredText(retrieveChildKeyValues(jsonObj37, "submit_btn"), "forgot password page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj37, "remember_password_txt"), "forgot password page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj37, "login_txt"), "forgot password page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj37, "submit_btn"), "forgot password page");
		validateRequiredText(retrieveChildKeyValues(jsonObj37, "email_inputfield_txt"), "forgot password page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "email_error_msg"), "login page");

		enterTextByXpath(textboxEmail, getTestData(7, "oneUserChar"));		
		clickOnRequiredText(retrieveChildKeyValues(jsonObj37, "submit_btn"), "forgot password page");
		validateRequiredText(retrieveChildKeyValues(jsonObj37, "email_inputfield_txt"), "forgot password page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "minimum_characters_error_msg"), "login page");

		enterTextByXpath(textboxEmail, getTestData(7, "FiftyUserCharWithSpace"));		
		clickOnRequiredText(retrieveChildKeyValues(jsonObj37, "submit_btn"), "forgot password page");
		validateRequiredText(retrieveChildKeyValues(jsonObj37, "email_inputfield_txt"), "forgot password page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "maximum_characters_error_msg"), "login page");

		enterTextByXpath(textboxEmail, "2*2");		
		clickOnRequiredText(retrieveChildKeyValues(jsonObj37, "submit_btn"), "forgot password page");
		validateRequiredText(retrieveChildKeyValues(jsonObj37, "email_inputfield_txt"), "forgot password page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "valid_email_error_msg"), "login page");


	}

	@Test
	public void chooseLanguage_LanguageEnglishValidation_MyEarnings_History_Payment_ExistingUser() throws UnsupportedEncodingException, FileNotFoundException, IOException, ParseException {

		JSONObject jsonObj1 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "englishFileName"), getTestData(16, "common_label"));
		JSONObject jsonObj2 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "englishFileName"), getTestData(16, "common_validate_label"));
		JSONObject jsonObj14 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "englishFileName"), getTestData(16, "search_result_all"));
		JSONObject jsonObj17 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "englishFileName"), getTestData(16, "profile_right_menu"));
		JSONObject jsonObj28 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "englishFileName"), getTestData(16, "my_account_earnings"));
		JSONObject jsonObj29 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "englishFileName"), getTestData(16, "total_cashback_earned"));
		JSONObject jsonObj30 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "englishFileName"), getTestData(16, "earnings_info"));		
		JSONObject jsonObj31 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "englishFileName"), getTestData(16, "earnings_referral"));
		JSONObject jsonObj32 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "englishFileName"), getTestData(16, "earnings_cashback"));
		JSONObject jsonObj33 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "englishFileName"), getTestData(16, "my_earnings"));
		JSONObject jsonObj35 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "englishFileName"), getTestData(16, "sign_in"));
		JSONObject jsonObj36 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "englishFileName"), getTestData(16, "join_free"));
		JSONObject jsonObj40 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "englishFileName"), getTestData(16, "giftcard_wallet_inputfield"));
		JSONObject jsonObj46 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "englishFileName"), getTestData(16, "payment"));
		JSONObject jsonObj52 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "englishFileName"), getTestData(16, "my_shared_activities"));
		JSONObject jsonObj53 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "englishFileName"), getTestData(16, "giftcard_wallet_request_payment"));

		reportStep("chooseLanguage_LanguageEnglishValidation_MyEarnings_History_Payment_ExistingUser started", "INFO");

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo);

		String dropDownPaymentSettings = "(//android.widget.EditText)[1]";
		String textNEFT = "//android.widget.CheckedTextView[@text='Bank Payment (NEFT)']";
		String textPaytmWallet = "//android.widget.CheckedTextView[@text='Paytm Wallet']";
		String textAmazonGiftCard = "//android.widget.CheckedTextView[@text='Amazon Gift Card']";
		String textBoxPaytmMobileNumberPaymentRequestPage = "//android.widget.EditText[@content-desc='et_requestpaymentPaytm_mobile']";
		String textBoxAmazonEmailPaymentRequestPage = "//android.widget.EditText[@content-desc='et_requestpaymentAmazon_email']";
		String linkEarningsInfoPaymentPage = "//android.widget.TextView[@content-desc='txt_payment_earningsInfo']";	
		String textboxName = "//android.widget.EditText[@content-desc='et_signup_fullname']";
		String textboxEmail = "//android.widget.EditText[@content-desc='et_signup_email']|//android.widget.EditText[@content-desc='et_signin_email']|//android.widget.EditText[@content-desc='et_forgotpassword_email']";
		String textboxPwd = "//android.widget.EditText[@content-desc='et_signup_password']|//android.widget.EditText[@content-desc='et_signin_password']";
		String textboxMobileNumber = "//android.widget.EditText[@content-desc='et_signup_mobile']";
		String searchIcon = "(//android.widget.TextView)[2]";
		String textAreaSearchIcon = "//android.widget.EditText[@content-desc='et_search_enterValue']";

		String cbStoreNameOne = CashKaroUtility.extractValuesFromStoreCategoryJsonAndLoadTheVariablesForStore("CB_Store_One", 0, "CB_Store_One_Name");
		String linkURL = CashKaroUtility.extractValuesFromStoreCategoryJsonAndLoadTheVariablesForStore("CB_Store_One", 0, "CB_Store_One_linkURL");

		String emailAddress = objCashKaroUtility.generateEmail();
		String mobileNumber = objCashKaroUtility.generateMobileNumber();	

		String emailAddressReferralJoinee = objCashKaroUtility.generateEmail();
		String mobileNumberReferralJoinee = objCashKaroUtility.generateMobileNumber();	

		String otpValue = new EKOnboardingPage(driver, testInfo).
				clickOnSignUpButton().
				enterFullName().
				enterEmail(emailAddress).
				enterPassword().
				enterMobileNumber(mobileNumber).
				clickOnGetOTPButton(mobileNumber);

		new OTPPage(driver, testInfo).
		enterOTP(otpValue).
		clickOnVerifyOTP();

		new HomePage(driver, testInfo).		
		clickOnProfileIconForSignedUser().
		clickOnChooseLanguage().
		clickEnglishLink().
		clickContinueButton().
		clickRelaunchButton();

		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "profile_tab"), "home page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj17, "logout_menu"), "profile page");
		validateRequiredText(retrieveChildKeyValues(jsonObj17, "alert_logout"), "logout popup");
		validateRequiredText(retrieveChildKeyValues(jsonObj17, "alert_logout_msg"), "logout popup");
		validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj17, "alert_cancel_btn"), "logout popup");
		clickOnPartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj17, "alert_ok_btn"), "logout popup");

		new EKOnboardingPage(driver, testInfo);

		CashKaroUtility cu = new CashKaroUtility(udid, port, automationName, deviceName);
		driver = cu.launchChromeWebView(driver);

		AdminCoreFunction adminFunctions = new AdminCoreFunction(driver, testInfo);
		adminFunctions.naviagateAdminPage_ThenSelectEarnKaro();
		adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
		adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
		adminFunctions.clickSubmit();

		AdminUsersPage usersPage = new AdminUsersPage(driver, testInfo);
		adminFunctions.clickOnHamburgerMenu();
		adminFunctions.clickUsersMenu();
		adminFunctions.clickUsersSubMenu();
		usersPage.selectSearchByDropDown("User Email");
		usersPage.enterKeyword(emailAddress);
		usersPage.clickSubmitButton();
		String userId = usersPage.extractUserIdValueFromResultstableFirstRow();
		String userName = usersPage.extractUserNameValueFromResultstableFirstRow();

		CashKaroUtility reopenCKApp1 = new CashKaroUtility(udid, port, automationName, deviceName, systemPort);
		driver = reopenCKApp1.launchEarnKaroApp(driver);

		CashKaroUtility objcashKaroutility = new CashKaroUtility(udid, port, automationName, deviceName);
		AppiumDriver<MobileElement> driver2 = objcashKaroutility.launchChrome();
		driver = driver2;

		String url = Utilities.referralLinkGetCode(userId, userName);

		objcashKaroutility.closeAllTabsAndOneTabInChromeBrowser(driver);
		objcashKaroutility.clickAndEnterURLInSearchOrTypeWebAddress(driver, url);

		objcashKaroutility.clickJoinFreeLink(driver);

		enterTextByXpath(textboxName, "ReferralJoinee");
		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(textboxEmail);
		clickOnNoneOFTheAbove();
		enterTextByXpath(textboxEmail, emailAddressReferralJoinee);
		enterTextByXpath(textboxPwd, "ppounds");
		enterTextByXpath(textboxMobileNumber, mobileNumberReferralJoinee);
		clickOnRequiredText(retrieveChildKeyValues(jsonObj36, "otp_btn"), "join free page");	

		new OTPPage(driver, testInfo);

		String otp = Utilities.getSignUPOTP(mobileNumberReferralJoinee);

		new OTPPage(driver, testInfo).
		enterOTP(otp);

		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "verify_otp"), "otp page");	

		sleep(15000);

		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(searchIcon);
		hideKeyboard();
		//validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj13, "search_inputfield_txt"), "search page");
		enterTextByXpath(textAreaSearchIcon, cbStoreNameOne);
		pressEnter();
		validateRequiredText(retrieveChildKeyValues(jsonObj14, "offer_count_single_message"), "search page");
		validateRequiredText(retrieveChildKeyValues(jsonObj1, "add_to_share"), "search page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "share_now"), "search page");

		WhatsAppUtility whatsAppUtility = new WhatsAppUtility(driver, testInfo, udid, port, automationName, deviceName, systemPort);
		whatsAppUtility.clickOnWhatAppMenuItemSearch().
		enterWhatsAppSearchText("Mallik").
		clickOnRequiredContact().
		clickOnSend().
		clickOnSend().
		clickOnLastSharedLink();

		new IntermediateRetailerPage(driver, testInfo).
		validateRetailerPage(linkURL, linkURL);

		CashKaroUtility cu2 = new CashKaroUtility(udid, port, automationName, deviceName);
		cu = cu2;
		driver = cu.launchChromeWebView(driver);

		AdminCoreFunction adminFunctions2 = new AdminCoreFunction(driver, testInfo);
		adminFunctions = adminFunctions2;
		adminFunctions.naviagateAdminPage_ThenSelectEarnKaro();
		adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
		adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
		adminFunctions.clickSubmit();

		AdminReportsPage reportsPage = new AdminReportsPage(driver, testInfo);
		adminFunctions.clickOnHamburgerMenu();
		adminFunctions.clickOnReports();
		reportsPage.clickExitClickSubMenu();
		reportsPage.clickClear();
		reportsPage.selectSearchByDropDown("User Email");
		reportsPage.enterKeyword(emailAddressReferralJoinee);
		reportsPage.clickSubmit();

		String exitClickOne = reportsPage.extractRequiredExitIdUsingStoreNameExitClickType(cbStoreNameOne, getTestData(7, "Normal"));

		AdminCashbackPage ProfitPage = new AdminCashbackPage(driver, testInfo);
		adminFunctions.clickOnHamburgerMenu();
		adminFunctions.clickCashbackMenu();
		ProfitPage.clickCashbackSubMenu();

		ProfitPage.clickAddNewButton();
		ProfitPage.enterExitId(exitClickOne);
		ProfitPage.entertransactionId(exitClickOne);
		ProfitPage.enterOrderId(exitClickOne);
		ProfitPage.setOrderDate();
		ProfitPage.enterOrderValue("1000");
		ProfitPage.enterconfirmCommisionNetwork(getTestData(11, "EightHundered"));
		ProfitPage.entercashback("700");
		ProfitPage.selectCashbackTypeFromDropDown(getTestData(11, "Cashback"));
		ProfitPage.selectCashbackStatusFromDropDown(getTestData(11, "Pending"));
		ProfitPage.setPendingDate();
		ProfitPage.enterDetails(exitClickOne);
		ProfitPage.clickSubmit();
		ProfitPage.clickClear();

		CashKaroUtility reopenCKAppAgain = new CashKaroUtility(udid, port, automationName, deviceName, systemPort);
		driver = reopenCKAppAgain.launchEarnKaroApp(driver);
		new HomePage(driver, testInfo);

		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "profile_tab"), "home page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj17, "history_menu"), "profile page");
		validateRequiredText(retrieveChildKeyValues(jsonObj1, "recent_clicks_tab"), "history page");
		validateRequiredText(retrieveChildKeyValues(jsonObj52, "title"), "history page");
		validateRequiredText(retrieveChildKeyValues(jsonObj52, "shared_link_details"), "history page");
		validateRequiredText(retrieveChildKeyValues(jsonObj52, "shared_id"), "history page");
		validateRequiredText(retrieveChildKeyValues(jsonObj52, "shared_date_time"), "history page");
		validateRequiredText(retrieveChildKeyValues(jsonObj52, "retailer_name"), "history page");
		validateRequiredText(retrieveChildKeyValues(jsonObj52, "no_of_clicks"), "history page");
		validateRequiredText(retrieveChildKeyValues(jsonObj52, "no_of_transactions"), "history page");
		validateRequiredText(retrieveChildKeyValues(jsonObj52, "total_earning"), "history page");

		backButton();

		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "profile_tab"), "home page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj17, "my_earnings_menu"), "profile page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj29, "total_cashback_earned_txt"), "my earnings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj29, "total_cashback_earned_txt"), 1, "profit page");
		validateRequiredText(retrieveChildKeyValues(jsonObj29, "total_cashback_earned_txt"), 2, "profit page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj29, "paid_cashback_txt"), "profit page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj29, "pending_cashback_txt"), "profit page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj29, "referral_cashback_txt"), "profit page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj29, "available_cashback_txt"), "profit page");

		backButton();

		validateRequiredText(retrieveChildKeyValues(jsonObj32, "order_details"), "my earnings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj32, "date"), "my earnings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj32, "retailer"), "my earnings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj32, "order_amount"), "my earnings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj32, "cashback_amount"), "my earnings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj32, "status"), "my earnings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj32, "expected_date"), "my earnings page");

		backButton();

		clickOnRequiredText(retrieveChildKeyValues(jsonObj17, "logout_menu"), "profile page");
		validateRequiredText(retrieveChildKeyValues(jsonObj17, "alert_logout"), "logout popup");
		validateRequiredText(retrieveChildKeyValues(jsonObj17, "alert_logout_msg"), "logout popup");
		validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj17, "alert_cancel_btn"), "logout popup");
		clickOnPartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj17, "alert_ok_btn"), "logout popup");

		new EKOnboardingPage(driver, testInfo);

		CashKaroUtility cu3 = new CashKaroUtility(udid, port, automationName, deviceName);
		cu = cu3;
		driver = cu.launchChromeWebView(driver);

		AdminCoreFunction adminFunctions3 = new AdminCoreFunction(driver, testInfo);
		adminFunctions = adminFunctions3;
		adminFunctions.naviagateAdminPage_ThenSelectEarnKaro();
		adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
		adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
		adminFunctions.clickSubmit();

		AdminCashbackPage ProfitPage1 = new AdminCashbackPage(driver, testInfo);
		ProfitPage = ProfitPage1;
		adminFunctions.clickOnHamburgerMenu();
		adminFunctions.clickCashbackMenu();
		ProfitPage.clickCashbackSubMenu();

		ProfitPage.selectSearchByStatusFromDropDown("Exit ID");
		ProfitPage.enterKeyword(exitClickOne);
		ProfitPage.selectCashbackStatusFromDropDown(getTestData(11, "Pending"));
		ProfitPage.clickSubmit();
		ProfitPage.clickEdit();
		ProfitPage.selectCashbackStatusFromDropDown(getTestData(11, "Confirmed"));
		ProfitPage.setConfirmDate();
		ProfitPage.clickSubmit();
		ProfitPage.clickClear();

		CashKaroUtility reopenCKAppOnceAgain = new CashKaroUtility(udid, port, automationName, deviceName, systemPort);
		driver = reopenCKAppOnceAgain.launchEarnKaroApp(driver);

		new EKOnboardingPage(driver, testInfo).clickOnSignInbutton();

		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(textboxEmail);
		clickOnNoneOFTheAbove();
		enterTextByXpath(textboxEmail, emailAddress);
		enterTextByXpath(textboxPwd, "ppounds");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj35, "sign_with_email_btn"), "login page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "profile_tab"), "home page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj17, "my_earnings_menu"), "profile page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj33, "referral_tap"), "my earnings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj31, "order_details"), "my earnings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj31, "date"), "my earnings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj31, "referral_name"), "my earnings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj31, "cashback"), "my earnings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj31, "status"), "my earnings page");
		validateRequiredText(retrieveChildKeyValues(jsonObj33, "refer_now_btn"), "my earnings page");

		backButton();

		clickOnRequiredText(retrieveChildKeyValues(jsonObj17, "logout_menu"), "profile page");
		validateRequiredText(retrieveChildKeyValues(jsonObj17, "alert_logout"), "logout popup");
		validateRequiredText(retrieveChildKeyValues(jsonObj17, "alert_logout_msg"), "logout popup");
		validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj17, "alert_cancel_btn"), "logout popup");
		clickOnPartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj17, "alert_ok_btn"), "logout popup");

		new EKOnboardingPage(driver, testInfo).clickOnSignInbutton();

		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(textboxEmail);
		clickOnNoneOFTheAbove();
		enterTextByXpath(textboxEmail, emailAddressReferralJoinee);
		enterTextByXpath(textboxPwd, "ppounds");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj35, "sign_with_email_btn"), "login page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "profile_tab"), "home page");
		validateRequiredText(retrieveChildKeyValues(jsonObj1, "profile_tab"), "profile page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj17, "hello_user_txt"), "profile page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj17, "total_cashback_txt"), "profile page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj17, "payment_menu"), "profile page");

		validateRequiredText(retrieveChildKeyValues(jsonObj17, "payment_menu"), "profile page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj29, "total_cashback_earned_txt"), "payments page");
		validateRequiredText(retrieveChildKeyValues(jsonObj29, "total_cashback_earned_txt"), 1, "profit page");
		validateRequiredText(retrieveChildKeyValues(jsonObj29, "total_cashback_earned_txt"), 2, "profit page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj29, "paid_cashback_txt"), "profit page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj29, "pending_cashback_txt"), "profit page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj29, "referral_cashback_txt"), "profit page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj29, "available_cashback_txt"), "profit page");

		backButton();

		validatePartialRequiredText(retrieveChildKeyValues(jsonObj28, "tap_to_see_details_txt"), "payments page");
		validateRequiredText(retrieveChildKeyValues(jsonObj46, "cashback_earnings"), "payments page");
		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(linkEarningsInfoPaymentPage);
		validateRequiredText(retrieveChildKeyValues(jsonObj30, "message_first_txt"), "earnings info page");


		backButton();

		validateRequiredText(retrieveChildKeyValues(jsonObj46, "request_cashback_btn"), "payments page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj46, "static_text_title"), "payments page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj46, "static_text_1"), "payments page");


		clickOnRequiredText(retrieveChildKeyValues(jsonObj46, "request_cashback_btn"), "payments page");

		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(dropDownPaymentSettings);
		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(textPaytmWallet);
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "mobile_inputfield_txt"), "payment request page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "get_paid_btn"), "payment req page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "mobile_inputfield_txt"), "payment request page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "mobile_number_error_msg"), "payment request page");

		enterTextByXpath(textBoxPaytmMobileNumberPaymentRequestPage, "1");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "get_paid_btn"), "payment req page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "mobile_inputfield_txt"), "payment request page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "minimum_digits_error_msg"), "payment request page");

		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(dropDownPaymentSettings);
		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(textAmazonGiftCard);

		validateRequiredText(retrieveChildKeyValues(jsonObj53, "email_inputfield_txt"), "payment request page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "get_paid_btn"), "payment req page");
		validateRequiredText(retrieveChildKeyValues(jsonObj53, "email_inputfield_txt"), "payment request page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "email_error_msg"), "payment request page");

		enterTextByXpath(textBoxAmazonEmailPaymentRequestPage, "1");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "get_paid_btn"), "payment req page");
		validateRequiredText(retrieveChildKeyValues(jsonObj53, "email_inputfield_txt"), "payment request page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "minimum_characters_error_msg"), "payment request page");

		enterTextByXpath(textBoxAmazonEmailPaymentRequestPage, "asdfghjkl");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "get_paid_btn"), "payment req page");
		validateRequiredText(retrieveChildKeyValues(jsonObj53, "email_inputfield_txt"), "payment request page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "valid_email_error_msg"), "payment request page");

		enterTextByXpath(textBoxAmazonEmailPaymentRequestPage, getTestData(7, "FiftyUserCharWithSpace"));
		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "get_paid_btn"), "payment req page");
		validateRequiredText(retrieveChildKeyValues(jsonObj53, "email_inputfield_txt"), "payment request page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "maximum_characters_error_msg"), "payment request page");

		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(dropDownPaymentSettings);
		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(textNEFT);

		validateRequiredText(retrieveChildKeyValues(jsonObj40, "bank_account_inputfield_txt"), "payment request page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "bank_name_inputfield_txt"), "payment request page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "branch_name_inputfield_txt"), "payment request page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "bank_account_number_inputfield_txt"), "payment request page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "bank_ifsc_code_inputfield_txt"), "payment request page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "get_paid_btn"), "payment req page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "bank_account_inputfield_txt"), "payment request page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "account_holder_error_msg"), "payment request page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "bank_name_inputfield_txt"), "payment request page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "valid_bank_name_error_msg"), "payment request page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "branch_name_inputfield_txt"), "payment request page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "branch_minimum_digits_error_msg"), "payment request page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "bank_account_number_inputfield_txt"), "payment request page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "account_number_error_msg"), "payment request page");
		validateRequiredText(retrieveChildKeyValues(jsonObj40, "bank_ifsc_code_inputfield_txt"), "payment request page");
		validateRequiredText(retrieveChildKeyValues(jsonObj2, "ifsc_code_error_msg"), "payment request page");


	}

	@Test
	public void chooseLanguage_LanguageEnglishValidation_StoreCategory_StoreDetail() throws UnsupportedEncodingException, FileNotFoundException, IOException, ParseException {

		JSONObject jsonObj1 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "englishFileName"), getTestData(16, "common_label"));
		JSONObject jsonObj3 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "englishFileName"), getTestData(16, "main_menu"));
		JSONObject jsonObj5 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "englishFileName"), getTestData(16, "store_category"));
		JSONObject jsonObj12 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "englishFileName"), getTestData(16, "share_counts"));
		JSONObject jsonObj17 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "englishFileName"), getTestData(16, "profile_right_menu"));

		reportStep("chooseLanguage_LanguageEnglishValidation_Store_Product_StoreCategory started", "INFO");

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo);

		String shareButtonWithCount = "//android.view.ViewGroup[@content-desc='txt_sharecount_open_modal']/android.view.ViewGroup/android.widget.TextView[@text='1']";
		String imageStoreCard = "//android.widget.ImageView[@content-desc='img_storeCard_imageValue']";
		String emailAddress = objCashKaroUtility.generateEmail();
		String mobileNumber = objCashKaroUtility.generateMobileNumber();	

		String otpValue = new EKOnboardingPage(driver, testInfo).
				clickOnSignUpButton().
				enterFullName().
				enterEmail(emailAddress).
				enterPassword().
				enterMobileNumber(mobileNumber).
				clickOnGetOTPButton(mobileNumber);

		new OTPPage(driver, testInfo).
		enterOTP(otpValue).
		clickOnVerifyOTP();

		new HomePage(driver, testInfo).		
		clickOnProfileIconForSignedUser().
		clickOnChooseLanguage().
		clickEnglishLink().
		clickContinueButton().
		clickRelaunchButton();

		validateRequiredText(retrieveChildKeyValues(jsonObj3, "home_lbl"), "home page");

		new HomePage(driver, testInfo).
		clickOnHamburgerIcon();

		validateRequiredText(retrieveChildKeyValues(jsonObj3, "home_lbl"), "home page");

		new SliderScreenPage(driver, testInfo).
		clickRetailerCategory().
		verifyRetailerCategoryMenuNavigation().
		clickAutomationSpecificCategoryInRetailerCategoryMenuUntilAllSubCategoryVisible().
		clickAutomationSpecificSubCategoryOne();

		validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj5, "popular"), "store category page");
		validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj5, "az"), "store category page");
		validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj5, "percent"), "store category page");
		validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj5, "amount"), "store category page");

		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "add_to_share"), "store category page");
		validateRequiredText(retrieveChildKeyValues(jsonObj1, "share_now"), "store category page");
		validateRequiredText(retrieveChildKeyValues(jsonObj1, "remove"), "store category page");

		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(shareButtonWithCount);
		validateRequiredText(retrieveChildKeyValues(jsonObj1, "share_now"), "store category page");
		validateRequiredText(retrieveChildKeyValues(jsonObj1, "copy_Link"), "store category page");
		validateRequiredText(retrieveChildKeyValues(jsonObj12, "close"), "store category page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj12, "clear"), "store category page");

		validateRequiredText(retrieveChildKeyValues(jsonObj12, "clear"), "store category page");
		validateRequiredText(retrieveChildKeyValues(jsonObj12, "confirm_msg_clear_share"), "store category page");
		validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj17, "alert_cancel_btn"), "store category page");
		clickOnPartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj17, "alert_ok_btn"), "store category page");

		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(imageStoreCard);
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj1, "you_will_earn"), "store detail page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "add_to_share"), "store detail page");
		validateRequiredText(retrieveChildKeyValues(jsonObj1, "share_now"), "store detail page");
		validateRequiredText(retrieveChildKeyValues(jsonObj1, "remove"), "store category page");

		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(shareButtonWithCount);
		validateRequiredText(retrieveChildKeyValues(jsonObj1, "share_now"), "store detail page");
		validateRequiredText(retrieveChildKeyValues(jsonObj1, "copy_Link"), "store detail page");
		validateRequiredText(retrieveChildKeyValues(jsonObj12, "close"), "store detail page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj12, "clear"), "store detail page");

		validateRequiredText(retrieveChildKeyValues(jsonObj12, "clear"), "store detail page");
		validateRequiredText(retrieveChildKeyValues(jsonObj12, "confirm_msg_clear_share"), "store detail page");
		validatePartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj17, "alert_cancel_btn"), "store detail page");
		clickOnPartialRequiredTextWithoutPassingClassName(retrieveChildKeyValues(jsonObj17, "alert_ok_btn"), "store detail page");

		new StoreDetailPage(driver, testInfo);

	}

	@Test
	public void chooseLanguage_LanguageEnglishValidation_Product() throws UnsupportedEncodingException, FileNotFoundException, IOException, ParseException {

		JSONObject jsonObj1 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "englishFileName"), getTestData(16, "common_label"));
		JSONObject jsonObj3 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "englishFileName"), getTestData(16, "main_menu"));
		JSONObject jsonObj6 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "englishFileName"), getTestData(16, "prduct_category"));
		JSONObject jsonObj9 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "englishFileName"), getTestData(16, "product_details"));
		JSONObject jsonObj10 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "englishFileName"), getTestData(16, "product_card"));
		JSONObject jsonObj49 = CashKaroUtility.retrieveParentKeyValues(getTestData(16, "englishFileName"), getTestData(16, "prduct_filter"));

		reportStep("chooseLanguage_LanguageEnglishValidation_Store_Product_StoreCategory started", "INFO");

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo);

		String menFashionCategory = "//android.view.ViewGroup[@content-desc='txt_mainMenu_productDealsTop']";
		String tshirtCategory = "//android.widget.TextView[@content-desc='txt_subMenuProduct_mainMenuName']";
		String menfashionLink = "//android.widget.TextView[@content-desc='txt_filter_category_name']";
		String imageProductCard = "//android.widget.ImageView[@content-desc='img_product_image']";
		String productcard = "(//android.widget.ImageView)[3]";		
		String emailAddress = objCashKaroUtility.generateEmail();
		String mobileNumber = objCashKaroUtility.generateMobileNumber();	

		String otpValue = new EKOnboardingPage(driver, testInfo).
				clickOnSignUpButton().
				enterFullName().
				enterEmail(emailAddress).
				enterPassword().
				enterMobileNumber(mobileNumber).
				clickOnGetOTPButton(mobileNumber);

		new OTPPage(driver, testInfo).
		enterOTP(otpValue).
		clickOnVerifyOTP();

		new HomePage(driver, testInfo).		
		clickOnProfileIconForSignedUser().
		clickOnChooseLanguage().
		clickEnglishLink().
		clickContinueButton().
		clickRelaunchButton();

		validateRequiredText(retrieveChildKeyValues(jsonObj3, "home_lbl"), "home page");

		new HomePage(driver, testInfo).
		clickOnHamburgerIcon();

		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(menFashionCategory);
		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(tshirtCategory);

		clickOnPartialRequiredText(retrieveChildKeyValues(jsonObj6, "filter"), "product page");
		validateRequiredText(retrieveChildKeyValues(jsonObj49, "filter_by"), "product page");
		validateRequiredText(retrieveChildKeyValues(jsonObj49, "clerar_all"), "product page");
		validateRequiredText(retrieveChildKeyValues(jsonObj49, "close_btn"), "product page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj49, "apply_btn"), "product page");

		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(menfashionLink);
		clickOnRequiredText(retrieveChildKeyValues(jsonObj49, "clerar_all"), "product page");
		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(menfashionLink);
		clickOnRequiredText(retrieveChildKeyValues(jsonObj49, "close_btn"), "product page");
		validateRequiredText(retrieveChildKeyValues(jsonObj49, "discard_changes_title"), "product page");
		validateRequiredText(retrieveChildKeyValues(jsonObj49, "discard_changes_msg"), "product page");
		validateRequiredText(retrieveChildKeyValues(jsonObj49, "apply_changes_btn"), "product page");
		validateRequiredText(retrieveChildKeyValues(jsonObj49, "discard_changes_btn"), "product page");

		backButton();

		clickOnPartialRequiredText(retrieveChildKeyValues(jsonObj6, "sort"), "product page");
		validateRequiredText(retrieveChildKeyValues(jsonObj6, "sort_title"), "product page");
		validateRequiredText(retrieveChildKeyValues(jsonObj6, "sort_popular"), "product page");
		validateRequiredText(retrieveChildKeyValues(jsonObj6, "sort_discount"), "product page");
		validateRequiredText(retrieveChildKeyValues(jsonObj6, "sort_high_price"), "product page");
		validateRequiredText(retrieveChildKeyValues(jsonObj6, "sort_low_price"), "product page");

		backButton();

		validatePartialRequiredText(retrieveChildKeyValues(jsonObj10, "brand"), "product page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj10, "price"), "product page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj10, "sale"), "product page");
		validateRequiredText(retrieveChildKeyValues(jsonObj1, "add_to_share"), "product page");
		validateRequiredText(retrieveChildKeyValues(jsonObj1, "share_now"), "product page");
		validatePartialRequiredText(retrieveChildKeyValues(jsonObj10, "see_details"), "product page");

		new ChooseLanguagePage(driver,testInfo,true).clickByXpath(imageProductCard);
		clickOnRequiredText(retrieveChildKeyValues(jsonObj9, "seller_price"), "product detail page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj1, "you_will_earn"), "product detail page");
		validateRequiredText(retrieveChildKeyValues(jsonObj1, "add_to_share"), "product page");
		validateRequiredText(retrieveChildKeyValues(jsonObj1, "share_now"), "product page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj9, "product_highlights"), "product detail page");
		clickOnRequiredText(retrieveChildKeyValues(jsonObj9, "similar_products"), "product detail page");

	}
	
}

