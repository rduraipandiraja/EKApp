package com.app.ck.test;

import org.openqa.selenium.By;
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
import com.app.ck.utilities.CashKaroUtility;
import com.app.ck.utilities.Utilities;

public class EKTestPayment extends WrapperMethods {
	
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
	
	/*******************************************************************/
	/************************ PAYMENT SCENARIO *************************/
	/*******************************************************************/
	//Pre-requisite Test SetUp |Adding the CashOut limit and Consecutive Cashout limit
	@Test
	public void aaa_setPaymentValue_And_ConsecutivePaymentValue(){

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
		adminFunctions.clickOnEditPartner();
		
		driver.findElementById("cashout_limit").clear();
		driver.findElementById("cashout_limit").sendKeys("250.00");
		
		driver.findElementById("consecutive_payment_limit").clear();
		driver.findElementById("consecutive_payment_limit").sendKeys("250.00");
		driver.findElementById("btn_Submit").click();
		validateTheElementPresence(driver.findElementById("adminMessageSuccess"));
		


	}
	// Payment scenarios :
	@Test
	public void payment_CB_0_ReqCB_TC001() {

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo);
		String email = objCashKaroUtility.generateEmail();
		String mobNum = objCashKaroUtility.generateMobileNumber();

		String otpValue = 
		new EKOnboardingPage(driver, testInfo).
		clickOnSignUpButton().
		enterFullName().
		enterEmail(email).
		enterPassword().
		enterMobileNumber(mobNum).
		clickOnGetOTPButton(mobNum);

		OTPPage objOtpPage = new OTPPage(driver, testInfo);
		objOtpPage.
		enterOTP(otpValue).
		clickOnVerifyOTP();

		// Once after clicking on the Click on verify OTP , it should navigate to the
		// Home page
		HomePage objHomePage = new HomePage(driver, testInfo);

		objHomePage.
		clickOnProfileIconForSignedUser().
		clickOnPaymentsForThresholdNotReached();

	}

	@Test
	public void payment_CB_249_99_ReqCB_TC001() {

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo);
		String email = objCashKaroUtility.generateEmail();
		String mobNum = objCashKaroUtility.generateMobileNumber();

		String otpValue = 
		new EKOnboardingPage(driver, testInfo).
		clickOnSignUpButton().
		enterFullName().
		enterEmail(email).
		enterPassword().
		enterMobileNumber(mobNum).
		clickOnGetOTPButton(mobNum);

		OTPPage objOtpPage = new OTPPage(driver, testInfo);
		objOtpPage.enterOTP(otpValue).
		clickOnVerifyOTP();

		// Once after clicking on the Click on verify OTP , it should navigate to the
		// Home page
		HomePage objHomePage = new HomePage(driver, testInfo);

		objCashKaroUtility.
		addCashbackBonus(email, getTestData(6, "TwoFortyNinePointNine"), getTestData(7, "Cashback"));

		objHomePage.
		clickOnProfileIconForSignedUser().
		clickOnPaymentsForThresholdNotReached();

	}

	// Payment method NEFT :
	@Test
	public void payment_CB_250_ReqCB_TC003_NEFT() {

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo);
		String email = objCashKaroUtility.generateEmail();
		String mobNum = objCashKaroUtility.generateMobileNumber();

		String otpValue = new EKOnboardingPage(driver, testInfo).
		clickOnSignUpButton().
		enterFullName().
		enterEmail(email).
		enterPassword().
		enterMobileNumber(mobNum).
		clickOnGetOTPButton(mobNum);

		new OTPPage(driver, testInfo).
		enterOTP(otpValue).
		clickOnVerifyOTP();

		// Once after clicking on the Click on verify OTP , It Should Navigate to the
		// Home page
		HomePage objHomePage = 
		new HomePage(driver, testInfo);

		// Adding the bonus using getcode.php - contact dev team for the getcode
		objCashKaroUtility.
		addCashbackBonus(email, getTestData(6, "TwoFifty"), getTestData(7, "Cashback"));

		PaymentOTPPage objPaymentOTP = objHomePage.
		clickOnProfileIconForSignedUser().
		clickOnPayments().
		clickOnRequestProfitPaymentButton().
		paymentRequestPaymentAvailabilityValidation("Only_Cashback", getTestData(6, "TwoFifty"),
		getTestData(6, "Zero")).
		clickOnPaymentMethodDropDown().
		selectBankPaymentNEFTFromDropDown().
		enterBankNEFTPaymentDetails().
		clickOnGETPAID();

		// SignUp OTP and payment OTP are same , so Sign up OTP has been used to get the
		// Payment OTP
		otpValue = objCashKaroUtility.
		generatePaymentOTP(mobNum);

		objPaymentOTP.
		enterOTP(otpValue).
		clickOnVerifyOTPAndValidateThePaymentSuccessMessage().
		clickOnRequestProfitPaymentButtonForThresholdNotReached().
		validatePaymentAlreadyPendingAlert();

	}

	@Test
	public void payment_CB_500_ReqCB_TC004_NEFT() {

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo);
		String email = objCashKaroUtility.generateEmail();
		String mobNum = objCashKaroUtility.generateMobileNumber();

		String otpValue = 
		new EKOnboardingPage(driver, testInfo).
		clickOnSignUpButton().
		enterFullName().
		enterEmail(email).
		enterPassword().
		enterMobileNumber(mobNum).
		clickOnGetOTPButton(mobNum);

		new OTPPage(driver, testInfo).
		enterOTP(otpValue).
		clickOnVerifyOTP();

		// Once after clicking on the Click on verify OTP , it should navigate to the
		// Home page
		HomePage objHomePage = new HomePage(driver, testInfo);

		// Adding the bonus using getcode.php - contact dev team for the getcode
		objCashKaroUtility.
		addCashbackBonus(email, getTestData(6, "FiveHundred"), getTestData(7, "Cashback"));

		PaymentOTPPage objPaymentOTP = objHomePage.
		clickOnProfileIconForSignedUser().
		clickOnPayments().
		clickOnRequestProfitPaymentButton().
		paymentRequestPaymentAvailabilityValidation("Only_Cashback", getTestData(6, "FiveHundred"),getTestData(6, "Zero")).
		clickOnPaymentMethodDropDown().
		selectBankPaymentNEFTFromDropDown().
		enterBankNEFTPaymentDetails().
		clickOnGETPAID();

		// SignUp OTP and payment OTP are same , so Sign up OTP has been used to get the
		// Payment OTP
		otpValue = objCashKaroUtility.generatePaymentOTP(mobNum);

		objPaymentOTP.
		enterOTP(otpValue).
		clickOnVerifyOTPAndValidateThePaymentSuccessMessage().
		clickOnRequestProfitPaymentButtonForThresholdNotReached().
		validatePaymentAlreadyPendingAlert();

	}

	// With 
	@Test
	public void payment_CB_250_ReqCBWithNEFTAmount_TC005() {

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo);
		String email = objCashKaroUtility.generateEmail();
		String mobNum = objCashKaroUtility.generateMobileNumber();

		String otpValue = 
		new EKOnboardingPage(driver, testInfo).
		clickOnSignUpButton().
		enterFullName().
		enterEmail(email).
		enterPassword().
		enterMobileNumber(mobNum).
		clickOnGetOTPButton(mobNum);

		new OTPPage(driver, testInfo).
		enterOTP(otpValue).
		clickOnVerifyOTP();

		// Once after clicking on the Click on verify OTP , it should navigate to the
		// Home page
		HomePage objHomePage = new HomePage(driver, testInfo);

		// Adding the bonus using getcode.php - contact dev team for the getcode
		objCashKaroUtility.addCashbackBonus(email, getTestData(6, "TwoFifty"), getTestData(7, "Cashback"));

		PaymentOTPPage objPaymentOTP = objHomePage.
		clickOnProfileIconForSignedUser().
		clickOnPayments().
		clickOnRequestProfitPaymentButton().
		paymentRequestPaymentAvailabilityValidation("Only_Cashback", getTestData(6, "TwoFifty"),getTestData(6, "Zero")).
		clickOnPaymentMethodDropDown().
		selectBankPaymentNEFTFromDropDown().
		enterBankNEFTPaymentDetails().
		clickOnGETPAID();

		// SignUp OTP and payment OTP are same , so Sign up OTP has been used to get the
		// Payment OTP
		otpValue = objCashKaroUtility.generatePaymentOTP(mobNum);

		objPaymentOTP.
		enterOTP(otpValue).
		clickOnVerifyOTPAndValidateThePaymentSuccessMessage();

	}

// Negative validations :
	@Test
	public void payment_negativeErrorValidationForNEFT_TC006() {

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo);
		String email = objCashKaroUtility.generateEmail();
		String mobNum = objCashKaroUtility.generateMobileNumber();

		String otpValue = new EKOnboardingPage(driver, testInfo).
		clickOnSignUpButton().
		enterFullName().
		enterEmail(email).
		enterPassword().
		enterMobileNumber(mobNum).
		clickOnGetOTPButton(mobNum);

		new OTPPage(driver, testInfo).
		enterOTP(otpValue).
		clickOnVerifyOTP();

		// Once after clicking on the Click on verify OTP , it should navigate to the
		// Home page
		HomePage objHomePage = new HomePage(driver, testInfo);

		// Adding the bonus using getcode.php - contact dev team for the getcode
		objCashKaroUtility.addCashbackBonus(email, getTestData(6, "TwoFifty"), getTestData(7, "Cashback"));

		objHomePage.clickOnProfileIconForSignedUser().
		clickOnPayments().
		clickOnRequestProfitPaymentButton().
		clickOnPaymentMethodDropDown().
		selectBankPaymentNEFTFromDropDown().
		clickOnGETPAIDForFailure().
		validateNEFTFieldErrorMessages(). // All the field error messages
		clickOnBackButton().
		clickOnRequestProfitPaymentButton().
		clickOnPaymentMethodDropDown().
		selectBankPaymentNEFTFromDropDown().
		enterAlphabetsIntoAccountNumberAndValidateErrorMessage(). // Enter alphabet into the account number and validate the error Message
		clickOnBackButton().
		clickOnRequestProfitPaymentButton().
		clickOnPaymentMethodDropDown().
		selectBankPaymentNEFTFromDropDown().
		enterSpecialCharacterIntoAccountNumberAndValidateErrorMessage(). //ENTER SPECIAL CHARS INTO THE ACCOUNT NUMBER AND VALIDATE ERROR MESSAGE
		clickOnBackButton().
		clickOnRequestProfitPaymentButton().
		clickOnPaymentMethodDropDown().
		selectBankPaymentNEFTFromDropDown().
		validateInvalidIFSCCodeServerSideValidation(mobNum).  // Validate the server side error for IFSC
		clickOnBackButton().
		clickOnRequestProfitPaymentButton().
		clickOnPaymentMethodDropDown()
		.selectBankPaymentNEFTFromDropDown().
		enterNumbersIntoAccountHolderNameAndvalidateErrorMessage(). //Enter Numbers into the account holder name and validate the error messages
		clickOnBackButton().
		clickOnRequestProfitPaymentButton().
		clickOnPaymentMethodDropDown().
		selectBankPaymentNEFTFromDropDown().
		enterNumbersIntoBankNameAndvalidateErrorMessage(). //Enter Numbers into the BankName and then validateError Messages
		clickOnBackButton().
		clickOnRequestProfitPaymentButton().
		clickOnPaymentMethodDropDown().
		selectBankPaymentNEFTFromDropDown().
		enterSpecialCharacterIntoBankNameAndvalidateErrorMessage(). //Enter special chars into the Bank_Name and then validate Error messages
		clickOnBackButton().
		clickOnRequestProfitPaymentButton().
		clickOnPaymentMethodDropDown().
		selectBankPaymentNEFTFromDropDown().
		enterSpecialCharacterIntoBankBranchNameAndValidateErrorMessage(mobNum); // server side validation into Bank branch name

	}

	// Payment method Amazon :
	@Test
	public void payment_CB_250_ReqCB_TC007_Amazon() {

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo);
		String email = objCashKaroUtility.generateEmail();
		String mobNum = objCashKaroUtility.generateMobileNumber();

		String otpValue = new EKOnboardingPage(driver, testInfo).
		clickOnSignUpButton().
		enterFullName().
		enterEmail(email).
		enterPassword().
		enterMobileNumber(mobNum).
		clickOnGetOTPButton(mobNum);

		new OTPPage(driver, testInfo).
		enterOTP(otpValue).
		clickOnVerifyOTP();

		// Once after clicking on the Click on verify OTP , it should navigate to the
		// Home page
		HomePage objHomePage = new HomePage(driver, testInfo);

		// Adding the bonus using getcode.php - contact dev team for the getcode
		objCashKaroUtility.addCashbackBonus(email, getTestData(6, "TwoFifty"), getTestData(7, "Cashback"));

		PaymentOTPPage objPaymentOTP = objHomePage.
		clickOnProfileIconForSignedUser().
		clickOnPayments().
		clickOnRequestProfitPaymentButton().
		paymentRequestPaymentAvailabilityValidation("Only_Cashback", getTestData(6, "TwoFifty"),getTestData(6, "Zero")).
		clickOnPaymentMethodDropDown().
		selectAmazonGiftCardFromDropDown().
		enterAmazonGiftCardEmailID(email).
		clickOnGETPAID();

		// SignUp OTP and payment OTP are same , so Sign up OTP has been used to get the
		// Payment OTP
		otpValue = objCashKaroUtility.generatePaymentOTP(mobNum);
		objPaymentOTP.
		enterOTP(otpValue).
		clickOnVerifyOTPAndValidateThePaymentSuccessMessage().
		clickOnRequestProfitPaymentButtonForThresholdNotReached().
		validatePaymentAlreadyPendingAlert();

	}

	@Test
	public void payment_CB_500_ReqCB_TC008_Amazon() {

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo);
		String email = objCashKaroUtility.generateEmail();
		String mobNum = objCashKaroUtility.generateMobileNumber();

		String otpValue = new EKOnboardingPage(driver, testInfo).
		clickOnSignUpButton().
		enterFullName().
		enterEmail(email).
		enterPassword().
		enterMobileNumber(mobNum).
		clickOnGetOTPButton(mobNum);

		new OTPPage(driver, testInfo).
		enterOTP(otpValue).
		clickOnVerifyOTP();

		// Once after clicking on the Click on verify OTP , it should navigate to the
		// Home page
		HomePage objHomePage = new HomePage(driver, testInfo);

		// Adding the bonus using getcode.php - contact dev team for the getcode
		objCashKaroUtility.
		addCashbackBonus(email, getTestData(6, "FiveHundred"), getTestData(7, "Cashback"));

		PaymentOTPPage objPaymentOTP = objHomePage.
		clickOnProfileIconForSignedUser().
		clickOnPayments().
		clickOnRequestProfitPaymentButton().
		paymentRequestPaymentAvailabilityValidation("Only_Cashback", getTestData(6, "FiveHundred"),getTestData(6, "Zero")).
		clickOnPaymentMethodDropDown().selectAmazonGiftCardFromDropDown().enterAmazonGiftCardEmailID(email).
		clickOnGETPAID();

		// SignUp OTP and payment OTP are same , so Sign up OTP has been used to get the
		// Payment OTP
		otpValue = objCashKaroUtility.generatePaymentOTP(mobNum);

		objPaymentOTP.
		enterOTP(otpValue).
		clickOnVerifyOTPAndValidateThePaymentSuccessMessage().
		clickOnRequestProfitPaymentButtonForThresholdNotReached().
		validatePaymentAlreadyPendingAlert();

	}

	// With 
	@Test
	public void payment_CB_250_ReqCBWithNEFTAmount_TC009_Amazon() {

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo);
		String email = objCashKaroUtility.generateEmail();
		String mobNum = objCashKaroUtility.generateMobileNumber();

		String otpValue = new EKOnboardingPage(driver, testInfo).
		clickOnSignUpButton().
		enterFullName().
		enterEmail(email).
		enterPassword().
		enterMobileNumber(mobNum).
		clickOnGetOTPButton(mobNum);
		
		new OTPPage(driver, testInfo).
		enterOTP(otpValue).
		clickOnVerifyOTP();

		// Once after clicking on the Click on verify OTP , it should navigate to the
		// Home page
		HomePage objHomePage = new HomePage(driver, testInfo);

		// Adding the bonus using getcode.php - contact dev team for the getcode
		objCashKaroUtility.
		addCashbackBonus(email, getTestData(6, "TwoFifty"), getTestData(7, "Cashback"));

		PaymentOTPPage objPaymentOTP = objHomePage.
		clickOnProfileIconForSignedUser().
		clickOnPayments().
		clickOnRequestProfitPaymentButton().
		paymentRequestPaymentAvailabilityValidation("Only_Cashback", getTestData(6, "TwoFifty"),getTestData(6, "Zero")).
		clickOnPaymentMethodDropDown().
		selectAmazonGiftCardFromDropDown().
		enterAmazonGiftCardEmailID(email).
		clickOnGETPAID();

		// SignUp OTP and payment OTP are same , so Sign up OTP has been used to get the
		// Payment OTP
		otpValue = objCashKaroUtility.
		generatePaymentOTP(mobNum);

		objPaymentOTP.
		enterOTP(otpValue).
		clickOnVerifyOTPAndValidateThePaymentSuccessMessage();

	}

	// Negative validations :
	@Test
	public void payment_negativeErrorValidationForNEFT_TC010_Amazon() {

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo);
		String email = objCashKaroUtility.generateEmail();
		String mobNum = objCashKaroUtility.generateMobileNumber();

		String otpValue = new EKOnboardingPage(driver, testInfo).
		clickOnSignUpButton().
		enterFullName().
		enterEmail(email).
		enterPassword().
		enterMobileNumber(mobNum).
		clickOnGetOTPButton(mobNum);

		new OTPPage(driver, testInfo).
		enterOTP(otpValue).
		clickOnVerifyOTP();

		// Once after clicking on the Click on verify OTP , it should navigate to the
		// Home page
		HomePage objHomePage = 
		new HomePage(driver, testInfo);

		// Adding the bonus using getcode.php - contact dev team for the getcode
		objCashKaroUtility.
		addCashbackBonus(email, getTestData(6, "TwoFifty"), getTestData(7, "Cashback"));

		objHomePage.clickOnProfileIconForSignedUser().
		clickOnPayments().
		clickOnRequestProfitPaymentButton().
		clickOnPaymentMethodDropDown().
		selectAmazonGiftCardFromDropDown().
		clickOnGETPAIDForFailure().
		validateAmazonGiftCardErrorValidation_AllPossible();

	}

	// Payment method FLIPKART :
	@Test
	public void payment_CB_250_ReqCB_TC011_FlipKart() {

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo);
		String email = objCashKaroUtility.generateEmail();
		String mobNum = objCashKaroUtility.generateMobileNumber();

		String otpValue = new EKOnboardingPage(driver, testInfo).
		clickOnSignUpButton().
		enterFullName().
		enterEmail(email).
		enterPassword().
		enterMobileNumber(mobNum).
		clickOnGetOTPButton(mobNum);

		new OTPPage(driver, testInfo).
		enterOTP(otpValue).
		clickOnVerifyOTP();

		// Once after clicking on the Click on verify OTP , it should navigate to the
		// Home page
		HomePage objHomePage = new HomePage(driver, testInfo);

		// Adding the bonus using getcode.php - contact dev team for the getcode
		objCashKaroUtility.addCashbackBonus(email, getTestData(6, "TwoFifty"), getTestData(7, "Cashback"));

		PaymentOTPPage objPaymentOTP = objHomePage.
		clickOnProfileIconForSignedUser().
		clickOnPayments().
		clickOnRequestProfitPaymentButton().
		paymentRequestPaymentAvailabilityValidation("Only_Cashback", getTestData(6, "TwoFifty"),getTestData(6, "Zero")).
		clickOnPaymentMethodDropDown().
		selectAmazonGiftCardFromDropDown().
		enterAmazonGiftCardEmailID(email).
		clickOnGETPAID();

		// SignUp OTP and payment OTP are same , so Sign up OTP has been used to get the
		// Payment OTP
		otpValue = objCashKaroUtility.generatePaymentOTP(mobNum);
		objPaymentOTP.
		enterOTP(otpValue).
		clickOnVerifyOTPAndValidateThePaymentSuccessMessage().
		clickOnRequestProfitPaymentButtonForThresholdNotReached().
		validatePaymentAlreadyPendingAlert();

	}

	@Test
	public void payment_CB_500_ReqCB_TC012_FlipKart() {

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo);
		String email = objCashKaroUtility.generateEmail();
		String mobNum = objCashKaroUtility.generateMobileNumber();

		String otpValue = new EKOnboardingPage(driver, testInfo).
		clickOnSignUpButton().
		enterFullName().
		enterEmail(email).
		enterPassword().
		enterMobileNumber(mobNum).
		clickOnGetOTPButton(mobNum);

		new OTPPage(driver, testInfo).
		enterOTP(otpValue).
		clickOnVerifyOTP();

		// Once after clicking on the Click on verify OTP , it should navigate to the
		// Home page
		HomePage objHomePage = new HomePage(driver, testInfo);

		// Adding the bonus using getcode.php - contact dev team for the getcode
		objCashKaroUtility.
		addCashbackBonus(email, getTestData(6, "FiveHundred"), getTestData(7, "Cashback"));

		PaymentOTPPage objPaymentOTP = objHomePage.
		clickOnProfileIconForSignedUser().
		clickOnPayments().
		clickOnRequestProfitPaymentButton().
		paymentRequestPaymentAvailabilityValidation("Only_Cashback", getTestData(6, "FiveHundred"),getTestData(6, "Zero")).
		clickOnPaymentMethodDropDown().
		selectAmazonGiftCardFromDropDown().
		enterAmazonGiftCardEmailID(email).
		clickOnGETPAID();

		// SignUp OTP and payment OTP are same , so Sign up OTP has been used to get the
		// Payment OTP
		otpValue = objCashKaroUtility.generatePaymentOTP(mobNum);

		objPaymentOTP.
		enterOTP(otpValue).
		clickOnVerifyOTPAndValidateThePaymentSuccessMessage().
		clickOnRequestProfitPaymentButtonForThresholdNotReached().
		validatePaymentAlreadyPendingAlert();

	}

	// With 
	@Test
	public void payment_CB_250_ReqCBWithNEFTAmount_TC013_FlipKart() {

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo);
		String email = objCashKaroUtility.generateEmail();
		String mobNum = objCashKaroUtility.generateMobileNumber();

		String otpValue = new EKOnboardingPage(driver, testInfo).
		clickOnSignUpButton().
		enterFullName().
		enterEmail(email).
		enterPassword().
		enterMobileNumber(mobNum).
		clickOnGetOTPButton(mobNum);

		new OTPPage(driver, testInfo).
		enterOTP(otpValue).
		clickOnVerifyOTP();

		// Once after clicking on the Click on verify OTP , it should navigate to the
		// Home page
		HomePage objHomePage = new HomePage(driver, testInfo);

		// Adding the bonus using getcode.php - contact dev team for the getcode
		objCashKaroUtility.
		addCashbackBonus(email, getTestData(6, "TwoFifty"), getTestData(7, "Cashback"));

		PaymentOTPPage objPaymentOTP = objHomePage.
		clickOnProfileIconForSignedUser().
		clickOnPayments().
		clickOnRequestProfitPaymentButton().
		paymentRequestPaymentAvailabilityValidation("Only_Cashback", getTestData(6, "TwoFifty"),getTestData(6, "Zero")).
		clickOnPaymentMethodDropDown().
		selectAmazonGiftCardFromDropDown().
		enterAmazonGiftCardEmailID(email).
		clickOnGETPAID();

		// SignUp OTP and payment OTP are same , so Sign up OTP has been used to get the
		// Payment OTP
		otpValue = objCashKaroUtility.generatePaymentOTP(mobNum);

		objPaymentOTP.enterOTP(otpValue).
		clickOnVerifyOTPAndValidateThePaymentSuccessMessage();

	}

	//Negative validations :
	@Test
	public void payment_negativeErrorValidationForNEFT_TC014_FlipKart() {

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo);
		String email = objCashKaroUtility.generateEmail();
		String mobNum = objCashKaroUtility.generateMobileNumber();

		String otpValue = new EKOnboardingPage(driver, testInfo).
		clickOnSignUpButton().
		enterFullName().
		enterEmail(email).
		enterPassword().
		enterMobileNumber(mobNum).
		clickOnGetOTPButton(mobNum);

		new OTPPage(driver, testInfo).
		enterOTP(otpValue).
		clickOnVerifyOTP();

		// Once after clicking on the Click on verify OTP , it should navigate to the
		// Home page
		HomePage objHomePage = new HomePage(driver, testInfo);

		// Adding the bonus using getcode.php - contact dev team for the getcode
		objCashKaroUtility.
		addCashbackBonus(email, getTestData(6, "TwoFifty"), getTestData(7, "Cashback"));

		objHomePage.
		clickOnProfileIconForSignedUser().
		clickOnPayments().
		clickOnRequestProfitPaymentButton().
		clickOnPaymentMethodDropDown().
		selectFlipKartGiftCardFromDropDown().
		clickOnGETPAIDForFailure().
		validateFlipKartGiftCardErrorValidation_AllPossible();

	}

	@Test
	public void paymentOTP_NegativeValidation_TC016() {

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo);
		String email = objCashKaroUtility.generateEmail();
		String mobNum = objCashKaroUtility.generateMobileNumber();

		String otpValue = new EKOnboardingPage(driver, testInfo).
				clickOnSignUpButton().
				enterFullName().
				enterEmail(email).
				enterPassword().
				enterMobileNumber(mobNum).
				clickOnGetOTPButton(mobNum);

		new OTPPage(driver, testInfo).
		enterOTP(otpValue).
		clickOnVerifyOTP();

		// Once after clicking on the Click on verify OTP , it should navigate to the
		// Home page
		HomePage objHomePage = new HomePage(driver, testInfo);

		// Adding the bonus using getcode.php - contact dev team for the getcode
		objCashKaroUtility.addCashbackBonus(email, getTestData(6, "TwoFifty"), getTestData(7, "Cashback"));

		PaymentOTPPage objPaymentOTP = 
				objHomePage.clickOnProfileIconForSignedUser().
				clickOnPayments().
				clickOnRequestProfitPaymentButton().
				paymentRequestPaymentAvailabilityValidation("Only_Cashback", getTestData(6, "TwoFifty"),getTestData(6, "Zero")).
				clickOnPaymentMethodDropDown().
				selectBankPaymentNEFTFromDropDown().
				enterBankNEFTPaymentDetails().
				clickOnGETPAID();

		// SignUp OTP and payment OTP are same , so Sign up OTP has been used to get the
		// Payment OTP
		otpValue = objCashKaroUtility.generatePaymentOTP(mobNum);

		objPaymentOTP.verifyReSendCodeIn().
		enterOTP(getTestData(6, "InvalidOTP")).
		clickOnVerifyOTPforFailure().
		verifyInValidOTP().
		waitFor3Minutes().
		enterOTP(otpValue).
		clickOnVerifyOTPforFailure().
		validateOTPExpiredvalidation().
		clickOnResendCode();

		otpValue = objCashKaroUtility.
				generatePaymentOTP(mobNum);

		objPaymentOTP.
		clickOnBackButton().
		clickOnGETPAID().
		enterOTP(otpValue).
		clickOnVerifyOTPforFailure().
		verifyInValidOTP().
		waitFor30Seconds().
		clickOnResendCode();

		otpValue = objCashKaroUtility.
				generatePaymentOTP(mobNum);

		objPaymentOTP.enterOTP(otpValue).
		clickOnVerifyOTPAndValidateThePaymentSuccessMessage();

	}

	// Consecutive Payment
	//@Test
	public void payment_ConsecutivePaymentViaNEFT_TC017() {

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo);
		String email = objCashKaroUtility.generateEmail();
		String mobNum = objCashKaroUtility.generateMobileNumber();

		String otpValue = new EKOnboardingPage(driver, testInfo).
				clickOnSignUpButton().
				enterFullName().
				enterEmail(email).
				enterPassword().
				enterMobileNumber(mobNum).
				clickOnGetOTPButton(mobNum);

		new OTPPage(driver, testInfo).
		enterOTP(otpValue).
		clickOnVerifyOTP();

		// Once after clicking on the Click on verify OTP , it should navigate to the
		// Home page
		HomePage objHomePage = 
				new HomePage(driver, testInfo);

		// Adding the bonus using getcode.php - contact dev team for the getcode
		objCashKaroUtility.
		addCashbackBonus(email, getTestData(6, "TwoFifty"), getTestData(7, "Cashback"));

		PaymentOTPPage objPaymentOTP = objHomePage.
				clickOnProfileIconForSignedUser().
				clickOnPayments().
				clickOnRequestProfitPaymentButton().
				paymentRequestPaymentAvailabilityValidation("Only_Cashback", getTestData(6, "TwoFifty"),getTestData(6, "Zero")).
				clickOnPaymentMethodDropDown().
				selectBankPaymentNEFTFromDropDown().
				enterBankNEFTPaymentDetails().
				clickOnGETPAID();

		// SignUp OTP and payment OTP are same , so Sign up OTP has been used to get the
		// Payment OTP
		otpValue = objCashKaroUtility.
				generatePaymentOTP(mobNum);

		MyEarningsPage objMyEarningsPage = objPaymentOTP.
				enterOTP(otpValue).
				clickOnVerifyOTPAndValidateThePaymentSuccessMessage().
				clickOnRequestProfitPaymentButtonForThresholdNotReached().
				validatePaymentAlreadyPendingAlert();

		/********************************************************************************/
		/*************************************
		 * 		CASHOUT
		 ************************************/
		/********************************************************************************/
		CashKaroUtility cu = new CashKaroUtility(udid, port, automationName, deviceName);
		driver = cu.launchChromeWebView(driver);
		AdminCoreFunction adminFunctions = new AdminCoreFunction(driver, testInfo);
		adminFunctions.naviagateAdminPage_ThenSelectEarnKaro();
		adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
		adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
		adminFunctions.clickSubmit();

		adminFunctions.clickOnHamburgerMenu();
		adminFunctions.clickCashbackMenu();
		adminFunctions.clickPendingCashoutsSubMenu();

		AdminPendingCashoutsPage PendingCashoutsPage = new AdminPendingCashoutsPage(driver, testInfo);
		PendingCashoutsPage.selectSearchByDropDown("User Email");
		PendingCashoutsPage.enterKeyword(email);
		PendingCashoutsPage.clickSubmit();
		PendingCashoutsPage.clickEmail(email);
		PendingCashoutsPage.clickCreateCashout();

		driver.switchTo().alert().accept();
		PendingCashoutsPage.validateSuccessMessage();

		/********************************************************************************/


		CashKaroUtility reopenCKAppTwo = new CashKaroUtility(udid, port, automationName, deviceName, systemPort);
		driver = reopenCKAppTwo.launchEarnKaroApp(driver);

		new HomePage(driver, testInfo).
		clickOnProfileIconForSignedUser().
		clickOnMyEarnings();


		// Adding the bonus using getcode.php - contact dev team for the getcode
		objCashKaroUtility.
		addCashbackBonus(email, getTestData(6, "TwoFortyNinePointNine"), getTestData(7, "Cashback"));

		//Previous "objMyEarningsPage" reference is killed so New My Earnings Page is created Newly
		objMyEarningsPage = new MyEarningsPage(driver, testInfo);

		objMyEarningsPage.
		clickOnRequestProfitPaymentButtonForThresholdNotReached().
		validateThresholdNotReached();

		// Adding the bonus using getcode.php - contact dev team for the getcode
		objCashKaroUtility.
		addCashbackBonus(email, getTestData(6, "Ten"), getTestData(7, "Cashback"));

		objMyEarningsPage.clickOnRequestProfitPaymentButton().
		paymentRequestPaymentAvailabilityValidation("Only_Cashback", getTestData(6, "TwoFiftyNinePointNine"),getTestData(6, "Zero")).
		clickOnPaymentMethodDropDown().
		selectBankPaymentNEFTFromDropDown().
		validateNEFTPaymentSettings().
		clickOnGETPAID();

		// SignUp OTP and payment OTP are same , so Sign up OTP has been used to get the
		// Payment OTP
		otpValue = objCashKaroUtility.
				generatePaymentOTP(mobNum);

		objMyEarningsPage = new PaymentOTPPage(driver, testInfo).
				enterOTP(otpValue).
				clickOnVerifyOTPAndValidateThePaymentSuccessMessage().
				clickOnRequestProfitPaymentButtonForThresholdNotReached().
				validatePaymentAlreadyPendingAlert();

	}

	//@Test
	public void payment_ConsecutivePaymentViaAmazon_TC018() {

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo);
		String email = objCashKaroUtility.generateEmail();
		String mobNum = objCashKaroUtility.generateMobileNumber();

		String otpValue = 
				new EKOnboardingPage(driver, testInfo).
				clickOnSignUpButton().
				enterFullName().
				enterEmail(email).
				enterPassword().
				enterMobileNumber(mobNum).
				clickOnGetOTPButton(mobNum);

		new OTPPage(driver, testInfo).
		enterOTP(otpValue).
		clickOnVerifyOTP();

		// Once after clicking on the Click on verify OTP , it should navigate to the
		// Home page
		HomePage objHomePage = 
				new HomePage(driver, testInfo);

		// Adding the bonus using getcode.php - contact dev team for the getcode
		objCashKaroUtility.
		addCashbackBonus(email, getTestData(6, "TwoFifty"), getTestData(7, "Cashback"));

		PaymentOTPPage objPaymentOTP = objHomePage.
				clickOnProfileIconForSignedUser().
				clickOnPayments().
				clickOnRequestProfitPaymentButton().
				paymentRequestPaymentAvailabilityValidation("Only_Cashback", getTestData(6, "TwoFifty"),getTestData(6, "Zero")).
				clickOnPaymentMethodDropDown().
				selectAmazonGiftCardFromDropDown().
				enterAmazonGiftCardEmailID().
				clickOnGETPAID();

		// SignUp OTP and payment OTP are same , so Sign up OTP has been used to get the
		// Payment OTP
		otpValue = objCashKaroUtility.
				generatePaymentOTP(mobNum);

		MyEarningsPage objMyEarningsPage = objPaymentOTP.
				enterOTP(otpValue).
				clickOnVerifyOTPAndValidateThePaymentSuccessMessage().
				clickOnRequestProfitPaymentButtonForThresholdNotReached().
				validatePaymentAlreadyPendingAlert();

		/********************************************************************************/
		/*************************************
		 *      CASHOUT
		 ************************************/
		/********************************************************************************/
		CashKaroUtility cu = new CashKaroUtility(udid, port, automationName, deviceName);
		driver = cu.launchChromeWebView(driver);
		AdminCoreFunction adminFunctions = new AdminCoreFunction(driver, testInfo);
		adminFunctions.naviagateAdminPage_ThenSelectEarnKaro();
		adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
		adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
		adminFunctions.clickSubmit();

		adminFunctions.clickOnHamburgerMenu();
		adminFunctions.clickCashbackMenu();
		adminFunctions.clickPendingCashoutsSubMenu();

		AdminPendingCashoutsPage PendingCashoutsPage = new AdminPendingCashoutsPage(driver, testInfo);
		PendingCashoutsPage.selectSearchByDropDown("User Email");
		PendingCashoutsPage.enterKeyword(email);
		PendingCashoutsPage.clickSubmit();
		PendingCashoutsPage.clickEmail(email);
		PendingCashoutsPage.clickCreateCashout();

		driver.switchTo().alert().accept();
		PendingCashoutsPage.validateSuccessMessage();

		/********************************************************************************/


		CashKaroUtility reopenCKAppTwo = new CashKaroUtility(udid, port, automationName, deviceName, systemPort);
		driver = reopenCKAppTwo.launchEarnKaroApp(driver);

		new HomePage(driver, testInfo).clickOnProfileIconForSignedUser().clickOnMyEarnings();


		// Adding the bonus using getcode.php - contact dev team for the getcode
		objCashKaroUtility.
		addCashbackBonus(email, getTestData(6, "TwoFortyNinePointNine"), getTestData(7, "Cashback"));

		//Previous "objMyEarningsPage" reference is killed so New My Earnings Page is created Newly
		objMyEarningsPage = new MyEarningsPage(driver, testInfo);

		objMyEarningsPage.
		clickOnRequestProfitPaymentButtonForThresholdNotReached().
		validateThresholdNotReached();

		// Adding the bonus using getcode.php - contact dev team for the getcode
		objCashKaroUtility.
		addCashbackBonus(email, getTestData(6, "Ten"), getTestData(7, "Cashback"));

		objMyEarningsPage.clickOnRequestProfitPaymentButton().
		paymentRequestPaymentAvailabilityValidation("Only_Cashback", getTestData(6, "TwoFiftyNinePointNine"),getTestData(6, "Zero")).
		clickOnPaymentMethodDropDown().
		selectAmazonGiftCardFromDropDown().
		validateGiftCardEmail().
		clickOnGETPAID();

		// SignUp OTP and payment OTP are same , so Sign up OTP has been used to get the
		// Payment OTP
		otpValue = objCashKaroUtility.
				generatePaymentOTP(mobNum);

		objMyEarningsPage = new PaymentOTPPage(driver, testInfo).
				enterOTP(otpValue).
				clickOnVerifyOTPAndValidateThePaymentSuccessMessage().
				clickOnRequestProfitPaymentButtonForThresholdNotReached().
				validatePaymentAlreadyPendingAlert();

	}

	//@Test
	public void payment_ConsecutivePaymentViaFlipKart_TC019() {

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo);
		String email = objCashKaroUtility.generateEmail();
		String mobNum = objCashKaroUtility.generateMobileNumber();

		String otpValue = new EKOnboardingPage(driver, testInfo).
				clickOnSignUpButton().
				enterFullName().
				enterEmail(email).
				enterPassword().
				enterMobileNumber(mobNum).
				clickOnGetOTPButton(mobNum);

		new OTPPage(driver, testInfo).
		enterOTP(otpValue).
		clickOnVerifyOTP();

		// Once after clicking on the Click on verify OTP , it should navigate to the
		// Home page
		HomePage objHomePage = 
				new HomePage(driver, testInfo);

		// Adding the bonus using getcode.php - contact dev team for the getcode
		objCashKaroUtility.
		addCashbackBonus(email, getTestData(6, "TwoFifty"), getTestData(7, "Cashback"));

		PaymentOTPPage objPaymentOTP = objHomePage.
				clickOnProfileIconForSignedUser().
				clickOnPayments().
				clickOnRequestProfitPaymentButton().
				paymentRequestPaymentAvailabilityValidation("Only_Cashback", getTestData(6, "TwoFifty"),getTestData(6, "Zero")).
				clickOnPaymentMethodDropDown().
				selectBankPaymentNEFTFromDropDown().
				enterBankNEFTPaymentDetails().
				clickOnGETPAID();

		// SignUp OTP and payment OTP are same , so Sign up OTP has been used to get the
		// Payment OTP
		otpValue = objCashKaroUtility.
				generatePaymentOTP(mobNum);

		MyEarningsPage objMyEarningsPage = new PaymentOTPPage(driver, testInfo).
				enterOTP(otpValue).
				clickOnVerifyOTPAndValidateThePaymentSuccessMessage().
				clickOnRequestProfitPaymentButtonForThresholdNotReached().
				validatePaymentAlreadyPendingAlert();

		/********************************************************************************/
		/*************************************
		 * 				CASHOUT
		 ************************************/
		/********************************************************************************/
		CashKaroUtility cu = new CashKaroUtility(udid, port, automationName, deviceName);
		driver = cu.launchChromeWebView(driver);
		AdminCoreFunction adminFunctions = new AdminCoreFunction(driver, testInfo);
		adminFunctions.naviagateAdminPage_ThenSelectEarnKaro();
		adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
		adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
		adminFunctions.clickSubmit();

		adminFunctions.clickOnHamburgerMenu();
		adminFunctions.clickCashbackMenu();
		adminFunctions.clickPendingCashoutsSubMenu();

		AdminPendingCashoutsPage PendingCashoutsPage = new AdminPendingCashoutsPage(driver, testInfo);
		PendingCashoutsPage.selectSearchByDropDown("User Email");
		PendingCashoutsPage.enterKeyword(email);
		PendingCashoutsPage.clickSubmit();
		PendingCashoutsPage.clickEmail(email);
		PendingCashoutsPage.clickCreateCashout();

		driver.switchTo().alert().accept();
		PendingCashoutsPage.validateSuccessMessage();

		/********************************************************************************/
		CashKaroUtility reopenCKAppTwo = new CashKaroUtility(udid, port, automationName, deviceName, systemPort);
		driver = reopenCKAppTwo.launchEarnKaroApp(driver);

		new HomePage(driver, testInfo).
		clickOnProfileIconForSignedUser().
		clickOnMyEarnings();

		// Adding the bonus using getcode.php - contact dev team for the getcode
		objCashKaroUtility.
		addCashbackBonus(email, getTestData(6, "TwoFortyNinePointNine"), getTestData(7, "Cashback"));

		//Previous "objMyEarningsPage" reference is killed so New My Earnings Page is created Newly
		objMyEarningsPage = new MyEarningsPage(driver, testInfo);

		objMyEarningsPage.
		clickOnRequestProfitPaymentButtonForThresholdNotReached().
		validateThresholdNotReached();

		// Adding the bonus using getcode.php - contact dev team for the getcode
		objCashKaroUtility.
		addCashbackBonus(email, getTestData(6, "Ten"), getTestData(7, "Cashback"));

		objMyEarningsPage.clickOnRequestProfitPaymentButton().
		paymentRequestPaymentAvailabilityValidation("Only_Cashback", getTestData(6, "TwoFiftyNinePointNine"),getTestData(6, "Zero")).
		clickOnPaymentMethodDropDown().
		selectFlipKartGiftCardFromDropDown().
		enterFlipKartGiftCardEmailID(email).
		clickOnGETPAID();

		// SignUp OTP and payment OTP are same , so Sign up OTP has been used to get the
		// Payment OTP
		otpValue = objCashKaroUtility.
				generatePaymentOTP(mobNum);

		objMyEarningsPage = new PaymentOTPPage(driver, testInfo).
				enterOTP(otpValue).
				clickOnVerifyOTPAndValidateThePaymentSuccessMessage().
				clickOnRequestProfitPaymentButtonForThresholdNotReached().
				validatePaymentAlreadyPendingAlert();

	}

			
}