package com.app.ck.test;

import org.openqa.selenium.interactions.MoveToOffsetAction;
import org.testng.annotations.Test;

import com.app.ck.base.WrapperMethods;
import com.app.ck.pages.EKOnboardingPage;
import com.app.ck.pages.HomePage;
import com.app.ck.pages.MyEarningsPage;
import com.app.ck.pages.OTPPage;
import com.app.ck.pages.PaymentOTPPage;
import com.app.ck.pages.admin.AdminCoreFunction;
import com.app.ck.pages.admin.AdminPaymentSettingsPage;
import com.app.ck.pages.admin.AdminPendingCashoutsPage;
import com.app.ck.pages.admin.AdminStoresPage;
import com.app.ck.pages.admin.ProductBrowserEditModePage;
import com.app.ck.utilities.CashKaroUtility;
import com.app.ck.utilities.Utilities;

public class EKTestPartnersValidations extends WrapperMethods {

	/*******************************************************************/
	/********              		Partners		  			 ***********/
	/*******************************************************************/
	
	public String storeName;

	@Test
	public void partnersPage_TC001() {

		String partnerName = CashKaroUtility.extractValuesFromPartnerJsonAndLoadTheVariablesForPartner("Store_One", 0, "Store_One_Name");
		String normalPrimaryCashbackValue = CashKaroUtility.extractValuesFromPartnerJsonAndLoadTheVariablesForPartner("Store_One", 0, "Store_One_Primary_Cashback_Value");
		String normalPrimaryCashbackDetails = CashKaroUtility.extractValuesFromPartnerJsonAndLoadTheVariablesForPartner("Store_One", 0, "Store_One_Primary_Cashback_Details");
		String normalSecondaryCashbackValue = CashKaroUtility.extractValuesFromPartnerJsonAndLoadTheVariablesForPartner("Store_One", 0, "Store_One_Secondary_Cashback_Value");
		String normalSecondaryCashbackDetails = CashKaroUtility.extractValuesFromPartnerJsonAndLoadTheVariablesForPartner("Store_One", 0, "Store_One_Secondary_Cashback_Details");

		reportStep("TC001 is  started", "INFO");

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

		OTPPage objOtpPage = new OTPPage(driver, testInfo);
		objOtpPage.
		enterOTP(otpValue).
		clickOnVerifyOTP();

		new HomePage(driver, testInfo).
		clickOnProfileIconForSignedUser().
		clickOnMakeProfitLink().
		clickOnSeePartnersButton().
		clickOnPartnerLink(partnerName).
		validatePartnerNameInHeader(partnerName).
		validatePartnerImage().
		validatePartnerName(partnerName).
		validatePartnerCashbackValuePresence(normalPrimaryCashbackValue).
		validatePartnerCashbackDetailsPresence(normalPrimaryCashbackDetails).
		validatePartnerCashbackValuePresence(normalSecondaryCashbackValue).
		validatePartnerCashbackDetailsPresence(normalSecondaryCashbackDetails);
		
		backButton();
		backButton();
		backButton();
		
		new HomePage(driver, testInfo).
		clickOnProfileIconForSignedUser().
		clickOnLogout();

	}

	@Test
	public void partnersPage_TC002() {

		String partnerName = CashKaroUtility.extractValuesFromPartnerJsonAndLoadTheVariablesForPartner("Store_Two", 1, "Store_Two_Name");
		String normalPrimaryCashbackValue = CashKaroUtility.extractValuesFromPartnerJsonAndLoadTheVariablesForPartner("Store_Two", 1, "Store_Two_Primary_Cashback_Value");
		String normalPrimaryCashbackDetails = CashKaroUtility.extractValuesFromPartnerJsonAndLoadTheVariablesForPartner("Store_Two", 1, "Store_Two_Primary_Cashback_Details");
		String normalSecondaryCashbackValue = CashKaroUtility.extractValuesFromPartnerJsonAndLoadTheVariablesForPartner("Store_Two", 1, "Store_Two_Secondary_Cashback_Value");
		String normalSecondaryCashbackDetails = CashKaroUtility.extractValuesFromPartnerJsonAndLoadTheVariablesForPartner("Store_Two", 1, "Store_Two_Secondary_Cashback_Details");

		reportStep("TC002 is  started", "INFO");

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

		OTPPage objOtpPage = new OTPPage(driver, testInfo);
		objOtpPage.
		enterOTP(otpValue).
		clickOnVerifyOTP();

		new HomePage(driver, testInfo).
		clickOnProfileIconForSignedUser().
		clickOnMakeProfitLink().
		clickOnSeePartnersButton().
		clickOnPartnerLink(partnerName).
		validatePartnerNameInHeader(partnerName).
		validatePartnerImage().
		validatePartnerName(partnerName).
		validatePartnerCashbackValuePresence(normalPrimaryCashbackValue).
		validatePartnerCashbackDetailsPresence(normalPrimaryCashbackDetails).
		validatePartnerCashbackValuePresence(normalSecondaryCashbackValue).
		validatePartnerCashbackDetailsPresence(normalSecondaryCashbackDetails);
		
		backButton();
		backButton();
		backButton();
		
		new HomePage(driver, testInfo).
		clickOnProfileIconForSignedUser().
		clickOnLogout();

	}

	@Test
	public void partnersPage_TC003() {

		String partnerName = CashKaroUtility.extractValuesFromPartnerJsonAndLoadTheVariablesForPartner("Store_Three", 2, "Store_Three_Name");
		String calendarPrimaryCashbackValue = CashKaroUtility.extractValuesFromPartnerJsonAndLoadTheVariablesForPartner("Store_Three", 2, "Store_Three_Primary_Cashback_Value");
		String calendarPrimaryCashbackDetails = CashKaroUtility.extractValuesFromPartnerJsonAndLoadTheVariablesForPartner("Store_Three", 2, "Store_Three_Primary_Cashback_Details");
		String calendarSecondaryCashbackValue = CashKaroUtility.extractValuesFromPartnerJsonAndLoadTheVariablesForPartner("Store_Three", 2, "Store_Three_Secondary_Cashback_Value");
		String calendarSecondaryCashbackDetails = CashKaroUtility.extractValuesFromPartnerJsonAndLoadTheVariablesForPartner("Store_Three", 2, "Store_Three_Secondary_Cashback_Details");

		reportStep("TC003 is  started", "INFO");

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

		OTPPage objOtpPage = new OTPPage(driver, testInfo);
		objOtpPage.
		enterOTP(otpValue).
		clickOnVerifyOTP();

		new HomePage(driver, testInfo).
		clickOnProfileIconForSignedUser().
		clickOnMakeProfitLink().
		clickOnSeePartnersButton().
		clickOnPartnerLink(partnerName).
		validatePartnerNameInHeader(partnerName).
		validatePartnerImage().
		validatePartnerName(partnerName).
		validatePartnerCashbackValuePresence(calendarPrimaryCashbackValue).
		validatePartnerCashbackDetailsPresence(calendarPrimaryCashbackDetails).
		validatePartnerCashbackValuePresence(calendarSecondaryCashbackValue).
		validatePartnerCashbackDetailsPresence(calendarSecondaryCashbackDetails);
		
		backButton();
		backButton();
		backButton();
		
		new HomePage(driver, testInfo).
		clickOnProfileIconForSignedUser().
		clickOnLogout();

	}

	@Test
	public void partnersPage_TC004() {

		String partnerName = CashKaroUtility.extractValuesFromPartnerJsonAndLoadTheVariablesForPartner("Store_Four", 3, "Store_Four_Name");
		String calendarPrimaryCashbackValue = CashKaroUtility.extractValuesFromPartnerJsonAndLoadTheVariablesForPartner("Store_Four", 3, "Store_Four_Primary_Cashback_Value");
		String calendarPrimaryCashbackDetails = CashKaroUtility.extractValuesFromPartnerJsonAndLoadTheVariablesForPartner("Store_Four", 3, "Store_Four_Primary_Cashback_Details");
		String calendarSecondaryCashbackValue = CashKaroUtility.extractValuesFromPartnerJsonAndLoadTheVariablesForPartner("Store_Four", 3, "Store_Four_Secondary_Cashback_Value");
		String calendarSecondaryCashbackDetails = CashKaroUtility.extractValuesFromPartnerJsonAndLoadTheVariablesForPartner("Store_Four", 3, "Store_Four_Secondary_Cashback_Details");

		reportStep("TC004 is  started", "INFO");

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

		OTPPage objOtpPage = new OTPPage(driver, testInfo);
		objOtpPage.
		enterOTP(otpValue).
		clickOnVerifyOTP();

		new HomePage(driver, testInfo).
		clickOnProfileIconForSignedUser().
		clickOnMakeProfitLink().
		clickOnSeePartnersButton().
		clickOnPartnerLink(partnerName).
		validatePartnerNameInHeader(partnerName).
		validatePartnerImage().
		validatePartnerName(partnerName).
		validatePartnerCashbackValuePresence(calendarPrimaryCashbackValue).
		validatePartnerCashbackDetailsPresence(calendarPrimaryCashbackDetails).
		validatePartnerCashbackValuePresence(calendarSecondaryCashbackValue).
		validatePartnerCashbackDetailsPresence(calendarSecondaryCashbackDetails);
		
		backButton();
		backButton();
		backButton();
		
		new HomePage(driver, testInfo).
		clickOnProfileIconForSignedUser().
		clickOnLogout();

	}

	@Test
	public void partnersPage_TC005() {

		String partnerName = CashKaroUtility.extractValuesFromPartnerJsonAndLoadTheVariablesForPartner("Store_Five", 4, "Store_Five_Name");
		String normalPrimaryCashbackValue = CashKaroUtility.extractValuesFromPartnerJsonAndLoadTheVariablesForPartner("Store_Five", 4, "Store_Five_Primary_Cashback_Value");
		String normalPrimaryCashbackDetails = CashKaroUtility.extractValuesFromPartnerJsonAndLoadTheVariablesForPartner("Store_Five", 4, "Store_Five_Primary_Cashback_Details");
		String normalSecondaryCashbackValue = CashKaroUtility.extractValuesFromPartnerJsonAndLoadTheVariablesForPartner("Store_Five", 4, "Store_Five_Secondary_Cashback_Value");
		String normalSecondaryCashbackDetails = CashKaroUtility.extractValuesFromPartnerJsonAndLoadTheVariablesForPartner("Store_Five", 4, "Store_Five_Secondary_Cashback_Details");
		String normalSecondaryCashbackValueTwo = CashKaroUtility.extractValuesFromPartnerJsonAndLoadTheVariablesForPartner("Store_Five", 4, "Store_Five_Secondary_Cashback_Value");
		String normalSecondaryCashbackDetailsTwo = CashKaroUtility.extractValuesFromPartnerJsonAndLoadTheVariablesForPartner("Store_Five", 4, "Store_Five_Secondary_Cashback_Details");
		String normalSecondaryCashbackValueThree = CashKaroUtility.extractValuesFromPartnerJsonAndLoadTheVariablesForPartner("Store_Five", 4, "Store_Five_Secondary_Cashback_Value");
		String normalSecondaryCashbackDetailsThree = CashKaroUtility.extractValuesFromPartnerJsonAndLoadTheVariablesForPartner("Store_Five", 4, "Store_Five_Secondary_Cashback_Details");

		reportStep("TC005 is  started", "INFO");

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

		OTPPage objOtpPage = new OTPPage(driver, testInfo);
		objOtpPage.
		enterOTP(otpValue).
		clickOnVerifyOTP();

		new HomePage(driver, testInfo).
		clickOnProfileIconForSignedUser().
		clickOnMakeProfitLink().
		clickOnSeePartnersButton().
		clickOnPartnerLink(partnerName).
		validatePartnerNameInHeader(partnerName).
		validatePartnerImage().
		validatePartnerName(partnerName).
		validatePartnerCashbackValuePresence(normalPrimaryCashbackValue).
		validatePartnerCashbackDetailsPresence(normalPrimaryCashbackDetails).
		validatePartnerCashbackValuePresence(normalSecondaryCashbackValue).
		validatePartnerCashbackDetailsPresence(normalSecondaryCashbackDetails).
		validatePartnerCashbackValuePresence(normalSecondaryCashbackValueTwo).
		validatePartnerCashbackDetailsPresence(normalSecondaryCashbackDetailsTwo).
		validatePartnerCashbackValuePresence(normalSecondaryCashbackValueThree).
		validatePartnerCashbackDetailsPresence(normalSecondaryCashbackDetailsThree);
		
		backButton();
		backButton();
		backButton();
		
		new HomePage(driver, testInfo).
		clickOnProfileIconForSignedUser().
		clickOnLogout();

	}

	@Test
	public void partnersPage_TC006() {

		String partnerName = CashKaroUtility.extractValuesFromPartnerJsonAndLoadTheVariablesForPartner("Store_Six", 5, "Store_Six_Name");
		String calendarPrimaryCashbackValue = CashKaroUtility.extractValuesFromPartnerJsonAndLoadTheVariablesForPartner("Store_Six", 5, "Store_Six_Primary_Cashback_Value");
		String calendarPrimaryCashbackDetails = CashKaroUtility.extractValuesFromPartnerJsonAndLoadTheVariablesForPartner("Store_Six", 5, "Store_Six_Primary_Cashback_Details");
		String calendarSecondaryCashbackValue = CashKaroUtility.extractValuesFromPartnerJsonAndLoadTheVariablesForPartner("Store_Six", 5, "Store_Six_Secondary_Cashback_Value");
		String calendarSecondaryCashbackDetails = CashKaroUtility.extractValuesFromPartnerJsonAndLoadTheVariablesForPartner("Store_Six", 5, "Store_Six_Secondary_Cashback_Details");
		String calendarSecondaryCashbackValueTwo = CashKaroUtility.extractValuesFromPartnerJsonAndLoadTheVariablesForPartner("Store_Six", 5, "Store_Six_Secondary_Cashback_Value");
		String calendarSecondaryCashbackDetailsTwo = CashKaroUtility.extractValuesFromPartnerJsonAndLoadTheVariablesForPartner("Store_Six", 5, "Store_Six_Secondary_Cashback_Details");
		String calendarSecondaryCashbackValueThree = CashKaroUtility.extractValuesFromPartnerJsonAndLoadTheVariablesForPartner("Store_Six", 5, "Store_Six_Secondary_Cashback_Value");
		String calendarSecondaryCashbackDetailsThree = CashKaroUtility.extractValuesFromPartnerJsonAndLoadTheVariablesForPartner("Store_Six", 5, "Store_Six_Secondary_Cashback_Details");

		reportStep("TC006 is  started", "INFO");

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

		OTPPage objOtpPage = new OTPPage(driver, testInfo);
		objOtpPage.
		enterOTP(otpValue).
		clickOnVerifyOTP();

		new HomePage(driver, testInfo).
		clickOnProfileIconForSignedUser().
		clickOnMakeProfitLink().
		clickOnSeePartnersButton().
		clickOnPartnerLink(partnerName).
		validatePartnerNameInHeader(partnerName).
		validatePartnerImage().
		validatePartnerName(partnerName).
		validatePartnerCashbackValuePresence(calendarPrimaryCashbackValue).
		validatePartnerCashbackDetailsPresence(calendarPrimaryCashbackDetails).
		validatePartnerCashbackValuePresence(calendarSecondaryCashbackValue).
		validatePartnerCashbackDetailsPresence(calendarSecondaryCashbackDetails).
		validatePartnerCashbackValuePresence(calendarSecondaryCashbackValueTwo).
		validatePartnerCashbackDetailsPresence(calendarSecondaryCashbackDetailsTwo).
		validatePartnerCashbackValuePresence(calendarSecondaryCashbackValueThree).
		validatePartnerCashbackDetailsPresence(calendarSecondaryCashbackDetailsThree);		
		
		backButton();
		backButton();
		backButton();
		
		new HomePage(driver, testInfo).
		clickOnProfileIconForSignedUser().
		clickOnLogout();

	}

	@Test
	public void partnersPage_TC007() {

		String partnerName = CashKaroUtility.extractValuesFromPartnerJsonAndLoadTheVariablesForPartner("Store_Seven", 6, "Store_Seven_Name");
		String normalPrimaryCashbackValue = CashKaroUtility.extractValuesFromPartnerJsonAndLoadTheVariablesForPartner("Store_Seven", 6, "Store_Seven_Primary_Cashback_Value");
		String normalPrimaryCashbackDetails = CashKaroUtility.extractValuesFromPartnerJsonAndLoadTheVariablesForPartner("Store_Seven", 6, "Store_Seven_Primary_Cashback_Details");
		String normalSecondaryCashbackValue = CashKaroUtility.extractValuesFromPartnerJsonAndLoadTheVariablesForPartner("Store_Seven", 6, "Store_Seven_Secondary_Cashback_Value");
		String normalSecondaryCashbackDetails = CashKaroUtility.extractValuesFromPartnerJsonAndLoadTheVariablesForPartner("Store_Seven", 6, "Store_Seven_Secondary_Cashback_Details");

		reportStep("TC007 is  started", "INFO");

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

		OTPPage objOtpPage = new OTPPage(driver, testInfo);
		objOtpPage.
		enterOTP(otpValue).
		clickOnVerifyOTP();

		new HomePage(driver, testInfo).
		clickOnProfileIconForSignedUser().
		clickOnMakeProfitLink().
		clickOnSeePartnersButton().
		clickOnPartnerLink(partnerName).
		validatePartnerNameInHeader(partnerName).
		validatePartnerImage().
		validatePartnerName(partnerName).
		validatePartnerCashbackValuePresence(normalPrimaryCashbackValue).
		validatePartnerCashbackDetailsPresence(normalPrimaryCashbackDetails).
		validatePartnerCashbackValueAbsence(normalSecondaryCashbackValue).
		validatePartnerCashbackDetailsAbsence(normalSecondaryCashbackDetails);
		
		backButton();
		backButton();
		backButton();
		
		new HomePage(driver, testInfo).
		clickOnProfileIconForSignedUser().
		clickOnLogout();

	}

	@Test
	public void partnersPage_TC008() {

		String partnerName = CashKaroUtility.extractValuesFromPartnerJsonAndLoadTheVariablesForPartner("Store_Eight", 7, "Store_Eight_Name");
		String calendarPrimaryCashbackValue = CashKaroUtility.extractValuesFromPartnerJsonAndLoadTheVariablesForPartner("Store_Eight", 7, "Store_Eight_Primary_Cashback_Value");
		String calendarPrimaryCashbackDetails = CashKaroUtility.extractValuesFromPartnerJsonAndLoadTheVariablesForPartner("Store_Eight", 7, "Store_Eight_Primary_Cashback_Details");
		String calendarSecondaryCashbackValue = CashKaroUtility.extractValuesFromPartnerJsonAndLoadTheVariablesForPartner("Store_Eight", 7, "Store_Eight_Secondary_Cashback_Value");
		String calendarSecondaryCashbackDetails = CashKaroUtility.extractValuesFromPartnerJsonAndLoadTheVariablesForPartner("Store_Eight", 7, "Store_Eight_Secondary_Cashback_Details");

		reportStep("TC008 is  started", "INFO");

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

		OTPPage objOtpPage = new OTPPage(driver, testInfo);
		objOtpPage.
		enterOTP(otpValue).
		clickOnVerifyOTP();

		new HomePage(driver, testInfo).
		clickOnProfileIconForSignedUser().
		clickOnMakeProfitLink().
		clickOnSeePartnersButton().
		clickOnPartnerLink(partnerName).
		validatePartnerNameInHeader(partnerName).
		validatePartnerImage().
		validatePartnerName(partnerName).
		validatePartnerCashbackValuePresence(calendarPrimaryCashbackValue).
		validatePartnerCashbackDetailsPresence(calendarPrimaryCashbackDetails).
		validatePartnerCashbackValueAbsence(calendarSecondaryCashbackValue).
		validatePartnerCashbackDetailsAbsence(calendarSecondaryCashbackDetails);
		
		backButton();
		backButton();
		backButton();
		
		new HomePage(driver, testInfo).
		clickOnProfileIconForSignedUser().
		clickOnLogout();

	}

	@Test
	public void partnersPage_TC009() {

		String partnerName = CashKaroUtility.extractValuesFromPartnerJsonAndLoadTheVariablesForPartner("Store_Nine", 8, "Store_Nine_Name");

		reportStep("TC009 is  started", "INFO");

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

		OTPPage objOtpPage = new OTPPage(driver, testInfo);
		objOtpPage.
		enterOTP(otpValue).
		clickOnVerifyOTP();

		new HomePage(driver, testInfo).
		clickOnProfileIconForSignedUser().
		clickOnMakeProfitLink().
		clickOnSeePartnersButton().
		validatePartnerLinkAbsence_partnersPage(partnerName);
		
		backButton();
		backButton();
		
		new HomePage(driver, testInfo).
		clickOnProfileIconForSignedUser().
		clickOnLogout();

	}

	@Test
	public void partnersPage_TC010() {

		String partnerName = CashKaroUtility.extractValuesFromPartnerJsonAndLoadTheVariablesForPartner("Store_Ten", 9, "Store_Ten_Name");

		reportStep("TC010 is  started", "INFO");

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

		OTPPage objOtpPage = new OTPPage(driver, testInfo);
		objOtpPage.
		enterOTP(otpValue).
		clickOnVerifyOTP();

		new HomePage(driver, testInfo).
		clickOnProfileIconForSignedUser().
		clickOnMakeProfitLink().
		clickOnSeePartnersButton().
		validatePartnerLinkAbsence_partnersPage(partnerName);
		
		backButton();
		backButton();
		
		new HomePage(driver, testInfo).
		clickOnProfileIconForSignedUser().
		clickOnLogout();

	}

	@Test
	public void partnersPage_TC011() {

		String partnerNameOne = CashKaroUtility.extractValuesFromPartnerJsonAndLoadTheVariablesForPartner("Store_One", 0, "Store_One_Name");
		String partnerNameTwo = CashKaroUtility.extractValuesFromPartnerJsonAndLoadTheVariablesForPartner("Store_Two", 1, "Store_Two_Name");
		String partnerNameThree = CashKaroUtility.extractValuesFromPartnerJsonAndLoadTheVariablesForPartner("Store_Three", 2, "Store_Three_Name");
		String partnerNameFour = CashKaroUtility.extractValuesFromPartnerJsonAndLoadTheVariablesForPartner("Store_Four", 3, "Store_Four_Name");
		String partnerNameFive = CashKaroUtility.extractValuesFromPartnerJsonAndLoadTheVariablesForPartner("Store_Five", 4, "Store_Five_Name");

		reportStep("TC001 is  started", "INFO");

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

		OTPPage objOtpPage = new OTPPage(driver, testInfo);
		objOtpPage.
		enterOTP(otpValue).
		clickOnVerifyOTP();

		new HomePage(driver, testInfo).
		clickOnProfileIconForSignedUser().
		clickOnMakeProfitLink().
		clickOnSeePartnersButton().
		validateNameInPartnerLink(partnerNameOne, 1).
		validateNameInPartnerLink(partnerNameTwo, 2).
		validateNameInPartnerLink(partnerNameThree, 3).
		validateNameInPartnerLink(partnerNameFour, 4).
		validateNameInPartnerLink(partnerNameFive, 5);
		
		backButton();
		backButton();
		
		new HomePage(driver, testInfo).
		clickOnProfileIconForSignedUser().
		clickOnLogout();

	}

	//@Test
	public void partnersPage_TC012() {

		reportStep("TC0012 is  started", "INFO");
		
		try {
			
			CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo);
			String emailAddress = objCashKaroUtility.generateEmail();
			String mobileNumber = objCashKaroUtility.generateMobileNumber();
			String hour = Integer.toString(getCurrentHoursInUTC());
			String minute = Integer.toString(getCurrentMinutesInUTC()+10);

			String otpValue = new EKOnboardingPage(driver, testInfo).
			clickOnSignUpButton().
			enterFullName().
			enterEmail(emailAddress).
			enterPassword().
			enterMobileNumber(mobileNumber).
			clickOnGetOTPButton(mobileNumber);

			OTPPage objOtpPage = new OTPPage(driver, testInfo);
			objOtpPage.
			enterOTP(otpValue).
			clickOnVerifyOTP();

			new HomePage(driver, testInfo);
			
			CashKaroUtility cuOne = new CashKaroUtility(udid, port, automationName, deviceName);
			driver = cuOne.launchChromeWebView(driver);
			
			AdminCoreFunction adminFunctions = new AdminCoreFunction(driver, testInfo);
			AdminStoresPage adminStoresPage = new AdminStoresPage(driver, testInfo);
			ProductBrowserEditModePage productBrowserEditModePage = new ProductBrowserEditModePage(driver, testInfo);
			
			storeName = cuOne.generateStoreName();
			String urlKey = cuOne.generateStoreName();
			String affiliateNetwork = getTestData(7, "Adda52");
			String linkURL = getTestData(4, "StoreURL");

			String exclusiveRate = getTestData(7, "ExclusiveRate");

			String networkCommision = getTestData(7, "FiveThousand");
			String cashbackPercentageValue = getTestData(7, "Twenty");
			String cashbackDetails = getTestData(7, "NormalPrimaryCashbackDetails");
			
			String calendarNetworkCommision = getTestData(7, "FiveThousand");
			String calendarCashbackPercentageValue = getTestData(7, "Ten"); 
			String calendarCashbackDetails = getTestData(7, "CalendarPrimaryCashbackDetails");
			
			String secondaryCashbackValue = "1000";
			String secondaryCashbackDetails = getTestData(7, "NormalSecondaryCashbackDetails");
			String calendarSecondaryCashbackValue = "500";
			String calendarSecondaryCashbackDetails = getTestData(7, "CalendarSecondaryCashbackDetails");
			
			adminFunctions.naviagateAdminPage_ThenSelectEarnKaro();
			adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
			adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
			adminFunctions.clickSubmit();
			
			adminFunctions.clickOnHamburgerMenu();
			adminFunctions.clickStoresSubMenu();
			
			adminStoresPage.clickAddNewButton();
			adminStoresPage.enterName(storeName);
			adminStoresPage.enterUrlKey(urlKey);
			adminStoresPage.clickCheckBoxApp();
			adminStoresPage.selectAffiliateNetworkFromDropDown(affiliateNetwork);
			adminStoresPage.enterLinkURL(linkURL);

			adminStoresPage.clickCategoryTab();
			adminStoresPage.clickAutoSpecificCategory();

			adminStoresPage.clickPrimaryCashbackTab();
			adminStoresPage.enterNetworkCommision(networkCommision);
			adminStoresPage.enterCashbackPercentage(cashbackPercentageValue);
			adminStoresPage.entercashbackDetails(cashbackDetails);
			adminStoresPage.enterCalendarNetworkCommision(calendarNetworkCommision);
			adminStoresPage.enterCalendarCashbackPercentage(calendarCashbackPercentageValue);
			adminStoresPage.enterCalendarCashbackDetails(calendarCashbackDetails);
			adminStoresPage.selectPrimaryCashbackOfferTypeFromDropDown(exclusiveRate);
			adminStoresPage.setCalendarPrimaryCashbackIssueDate();
			adminStoresPage.setCalendarPrimaryCashbackExpiryDate();
			adminStoresPage.clickPrimaryCashbackExpiryTime();
			adminStoresPage.setTimeTagToGetDisplayedInMinutesSeconds(hour, minute, "00");

			adminStoresPage.clickSecondaryCashbackTab();
			adminStoresPage.enterSecondaryCashback(secondaryCashbackValue);
			adminStoresPage.enterSecondaryCashbackDetails(secondaryCashbackDetails);
			adminStoresPage.enterCalendarSecondaryCashback(calendarSecondaryCashbackValue);
			adminStoresPage.enterCalendarSecondaryCashbackDetails(calendarSecondaryCashbackDetails);
			adminStoresPage.selectSecondaryCashbackOfferTypeFromDropDown(exclusiveRate);
			adminStoresPage.clickSecondaryCashbackCheckBoxApp();
			adminStoresPage.setCalendarSecondaryCashbackIssueDate();
			adminStoresPage.setCalendarSecondaryCashbackExpiryDate();
			adminStoresPage.clickSecondaryCashbackExpiryTime();
			adminStoresPage.setTimeTagToGetDisplayedInMinutesSeconds(hour, minute, "00");
			adminStoresPage.clickSubmitButton();

			adminFunctions.clickOnHamburgerMenu();
			adminFunctions.clickProductFeedMenu();
			adminFunctions.clickProductBrowserEditModeSubMenu();
			
			
			productBrowserEditModePage.clickRefreshPrimaryCashaback();
			productBrowserEditModePage.clickRefreshButton();
			productBrowserEditModePage.clickOkButton();
			productBrowserEditModePage.clickRefreshSecondaryCashaback();
			productBrowserEditModePage.clickRefreshButton();
			productBrowserEditModePage.clickOkButton();
			
			CashKaroUtility reopenCKApp = new CashKaroUtility(udid, port, automationName, deviceName, systemPort);
			driver = reopenCKApp.launchEarnKaroApp(driver);

			new HomePage(driver, testInfo).
			clickOnProfileIconForSignedUser().
			clickOnMakeProfitLink().
			clickOnSeePartnersButton().
			clickOnPartnerLink(storeName).
			validatePartnerNameInHeader(storeName).
			validatePartnerImage().
			validatePartnerName(storeName).
			validatePartnerCashbackValuePresence("500").
			validatePartnerCashbackDetailsPresence(calendarCashbackDetails).
			validatePartnerCashbackValuePresence("500").
			validatePartnerCashbackDetailsPresence(calendarSecondaryCashbackDetails);
			
			sleep(190000);
			
			backButton();
			backButton();
			backButton();
			
			new HomePage(driver, testInfo).
			clickOnProfileIconForSignedUser().
			clickOnMakeProfitLink().
			clickOnSeePartnersButton().
			clickOnPartnerLink(storeName).
			validatePartnerCashbackValuePresence("1000").
			validatePartnerCashbackDetailsPresence(cashbackDetails).
			validatePartnerCashbackValuePresence("1000").
			validatePartnerCashbackDetailsPresence(secondaryCashbackDetails);
			
			backButton();
			backButton();
			backButton();
			
			new HomePage(driver, testInfo).
			clickOnProfileIconForSignedUser().
			clickOnLogout();

			CashKaroUtility cuTwo = new CashKaroUtility(udid, port, automationName, deviceName);
			driver = cuTwo.launchChromeWebView(driver);

			AdminCoreFunction adminFunctionsOne = new AdminCoreFunction(driver, testInfo);
			AdminStoresPage adminStoresPageOne = new AdminStoresPage(driver, testInfo);
			ProductBrowserEditModePage productBrowserEditModePageOne = new ProductBrowserEditModePage(driver, testInfo);
			adminFunctions = adminFunctionsOne;
			adminStoresPage = adminStoresPageOne;
			productBrowserEditModePage = productBrowserEditModePageOne;
			
			adminFunctions.naviagateAdminPage_ThenSelectEarnKaro();
			adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
			adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
			adminFunctions.clickSubmit();
			
			adminFunctions.clickOnHamburgerMenu();
			adminFunctions.clickStoresSubMenu();
			
			adminStoresPage.enterStoreName(storeName);
			adminStoresPage.clickSubmitButton();
			
			adminStoresPage.clickEditButton(1);
			adminStoresPage.selectStatusFromDropDown("In-Active");
			adminStoresPage.clickSubmitButton();
			adminFunctions.clickOnHamburgerMenu();
			adminFunctions.clickProductFeedMenu();
			adminFunctions.clickProductBrowserEditModeSubMenu();
			
			productBrowserEditModePage.clickRefreshPrimaryCashaback();
			productBrowserEditModePage.clickRefreshButton();
			productBrowserEditModePage.clickOkButton();

			
		} catch (Exception e) {

			CashKaroUtility cuTwo = new CashKaroUtility(udid, port, automationName, deviceName);
			driver = cuTwo.launchChromeWebView(driver);

			AdminCoreFunction adminFunctions = new AdminCoreFunction(driver, testInfo);
			AdminStoresPage adminStoresPage = new AdminStoresPage(driver, testInfo);
			ProductBrowserEditModePage productBrowserEditModePage = new ProductBrowserEditModePage(driver, testInfo);
			
			adminFunctions.naviagateAdminPage_ThenSelectEarnKaro();
			adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
			adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
			adminFunctions.clickSubmit();
			
			adminFunctions.clickOnHamburgerMenu();
			adminFunctions.clickStoresSubMenu();
			
			adminStoresPage.enterStoreName(storeName);
			adminStoresPage.clickSubmitButton();
			
			adminStoresPage.clickEditButton(1);
			adminStoresPage.selectStatusFromDropDown("In-Active");
			adminStoresPage.clickSubmitButton();
			adminFunctions.clickOnHamburgerMenu();
			adminFunctions.clickProductFeedMenu();
			adminFunctions.clickProductBrowserEditModeSubMenu();
			
			productBrowserEditModePage.clickRefreshPrimaryCashaback();
			productBrowserEditModePage.clickRefreshButton();
			productBrowserEditModePage.clickOkButton();
		}



	}

	//@Test
	public void partnersPage_TC013() {

		reportStep("TC0013 is  started", "INFO");

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo);
		String emailAddress = objCashKaroUtility.generateEmail();
		String mobileNumber = objCashKaroUtility.generateMobileNumber();
		String hour = Integer.toString(getCurrentHoursInUTC());
		String minute = Integer.toString(getCurrentMinutesInUTC()+10);

		String otpValue = new EKOnboardingPage(driver, testInfo).
		clickOnSignUpButton().
		enterFullName().
		enterEmail(emailAddress).
		enterPassword().
		enterMobileNumber(mobileNumber).
		clickOnGetOTPButton(mobileNumber);

		OTPPage objOtpPage = new OTPPage(driver, testInfo);
		objOtpPage.
		enterOTP(otpValue).
		clickOnVerifyOTP();

		new HomePage(driver, testInfo);
		
		CashKaroUtility cuOne = new CashKaroUtility(udid, port, automationName, deviceName);
		driver = cuOne.launchChromeWebView(driver);
		
		AdminCoreFunction adminFunctions = new AdminCoreFunction(driver, testInfo);
		AdminStoresPage adminStoresPage = new AdminStoresPage(driver, testInfo);
		ProductBrowserEditModePage productBrowserEditModePage = new ProductBrowserEditModePage(driver, testInfo);
		
		String storeName = cuOne.generateStoreName();
		String urlKey = cuOne.generateStoreName();
		String affiliateNetwork = getTestData(7, "Adda52");
		String linkURL = getTestData(4, "StoreURL");

		String exclusiveRate = getTestData(7, "ExclusiveRate");

		String networkCommision = getTestData(7, "FiveThousand");
		String cashbackPercentageValue = getTestData(7, "Twenty");
		String cashbackDetails = getTestData(7, "NormalPrimaryCashbackDetails");
		
		String calendarNetworkCommision = getTestData(7, "FiveThousand");
		String calendarCashbackPercentageValue = getTestData(7, "Ten"); 
		String calendarCashbackDetails = getTestData(7, "CalendarPrimaryCashbackDetails");
		
		String secondaryCashbackValue = "1000";
		String secondaryCashbackDetails = getTestData(7, "NormalSecondaryCashbackDetails");
		String calendarSecondaryCashbackValue = "500";
		String calendarSecondaryCashbackDetails = getTestData(7, "CalendarSecondaryCashbackDetails");
		
		adminFunctions.naviagateAdminPage_ThenSelectEarnKaro();
		adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
		adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
		adminFunctions.clickSubmit();
		
		adminFunctions.clickOnHamburgerMenu();
		adminFunctions.clickStoresSubMenu();
		
		adminStoresPage.clickAddNewButton();
		adminStoresPage.enterName(storeName);
		adminStoresPage.enterUrlKey(urlKey);
		adminStoresPage.clickCheckBoxApp();
		adminStoresPage.selectAffiliateNetworkFromDropDown(affiliateNetwork);
		adminStoresPage.enterLinkURL(linkURL);

		adminStoresPage.clickCategoryTab();
		adminStoresPage.clickAutoSpecificCategory();

		adminStoresPage.clickPrimaryCashbackTab();
		adminStoresPage.enterNetworkCommision(networkCommision);
		adminStoresPage.enterCashbackPercentage(cashbackPercentageValue);
		adminStoresPage.entercashbackDetails(cashbackDetails);
		adminStoresPage.enterCalendarNetworkCommision(calendarNetworkCommision);
		adminStoresPage.enterCalendarCashbackPercentage(calendarCashbackPercentageValue);
		adminStoresPage.enterCalendarCashbackDetails(calendarCashbackDetails);
		adminStoresPage.selectPrimaryCashbackOfferTypeFromDropDown(exclusiveRate);
		adminStoresPage.setCalendarPrimaryCashbackIssueDate_CurrentDate();
		adminStoresPage.setCalendarPrimaryCashbackExpiryDate();
		adminStoresPage.clickPrimaryCashbackIssueTime();
		adminStoresPage.setTimeTagToGetDisplayedInMinutesSeconds(hour, minute, "00");

		adminStoresPage.clickSecondaryCashbackTab();
		adminStoresPage.enterSecondaryCashback(secondaryCashbackValue);
		adminStoresPage.enterSecondaryCashbackDetails(secondaryCashbackDetails);
		adminStoresPage.enterCalendarSecondaryCashback(calendarSecondaryCashbackValue);
		adminStoresPage.enterCalendarSecondaryCashbackDetails(calendarSecondaryCashbackDetails);
		adminStoresPage.selectSecondaryCashbackOfferTypeFromDropDown(exclusiveRate);
		adminStoresPage.clickSecondaryCashbackCheckBoxApp();
		adminStoresPage.setCalendarSecondaryCashbackIssueDate_CurrentDate();
		adminStoresPage.setCalendarSecondaryCashbackExpiryDate();
		adminStoresPage.clickSecondaryCashbackIssueTime();
		adminStoresPage.setTimeTagToGetDisplayedInMinutesSeconds(hour, minute, "00");
		adminStoresPage.clickSubmitButton();

		adminFunctions.clickOnHamburgerMenu();
		adminFunctions.clickProductFeedMenu();
		adminFunctions.clickProductBrowserEditModeSubMenu();
		
		
		productBrowserEditModePage.clickRefreshPrimaryCashaback();
		productBrowserEditModePage.clickRefreshButton();
		productBrowserEditModePage.clickOkButton();
		productBrowserEditModePage.clickRefreshSecondaryCashaback();
		productBrowserEditModePage.clickRefreshButton();
		productBrowserEditModePage.clickOkButton();
		
		CashKaroUtility reopenCKApp = new CashKaroUtility(udid, port, automationName, deviceName, systemPort);
		driver = reopenCKApp.launchEarnKaroApp(driver);

		new HomePage(driver, testInfo).
		clickOnProfileIconForSignedUser().
		clickOnMakeProfitLink().
		clickOnSeePartnersButton().
		clickOnPartnerLink(storeName).
		validatePartnerNameInHeader(storeName).
		validatePartnerImage().
		validatePartnerName(storeName).
		validatePartnerCashbackValuePresence("1000").
		validatePartnerCashbackDetailsPresence(cashbackDetails).
		validatePartnerCashbackValuePresence("1000").
		validatePartnerCashbackDetailsPresence(secondaryCashbackDetails);
		
		sleep(190000);
		
		backButton();
		backButton();
		backButton();
		
		new HomePage(driver, testInfo).
		clickOnProfileIconForSignedUser().
		clickOnMakeProfitLink().
		clickOnSeePartnersButton().
		clickOnPartnerLink(storeName).
		validatePartnerCashbackValuePresence("500").
		validatePartnerCashbackDetailsPresence(calendarCashbackDetails).
		validatePartnerCashbackValuePresence("500").
		validatePartnerCashbackDetailsPresence(calendarSecondaryCashbackDetails);
		
		backButton();
		backButton();
		backButton();
		
		new HomePage(driver, testInfo).
		clickOnProfileIconForSignedUser().
		clickOnLogout();

		CashKaroUtility cuTwo = new CashKaroUtility(udid, port, automationName, deviceName);
		driver = cuTwo.launchChromeWebView(driver);

		AdminCoreFunction adminFunctionsOne = new AdminCoreFunction(driver, testInfo);
		AdminStoresPage adminStoresPageOne = new AdminStoresPage(driver, testInfo);
		ProductBrowserEditModePage productBrowserEditModePageOne = new ProductBrowserEditModePage(driver, testInfo);
		adminFunctions = adminFunctionsOne;
		adminStoresPage = adminStoresPageOne;
		productBrowserEditModePage = productBrowserEditModePageOne;
		
		adminFunctions.naviagateAdminPage_ThenSelectEarnKaro();
		adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
		adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
		adminFunctions.clickSubmit();
		
		adminFunctions.clickOnHamburgerMenu();
		adminFunctions.clickStoresSubMenu();
		
		adminStoresPage.enterStoreName(storeName);
		adminStoresPage.clickSubmitButton();
		
		adminStoresPage.clickEditButton(1);
		adminStoresPage.selectStatusFromDropDown("In-Active");
		adminStoresPage.clickSubmitButton();
		adminFunctions.clickOnHamburgerMenu();
		adminFunctions.clickProductFeedMenu();
		adminFunctions.clickProductBrowserEditModeSubMenu();
		
		productBrowserEditModePage.clickRefreshPrimaryCashaback();
		productBrowserEditModePage.clickRefreshButton();
		productBrowserEditModePage.clickOkButton();


	}

	
			
}