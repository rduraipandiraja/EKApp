package com.app.ck.test;

import java.util.Set;

import org.testng.annotations.Test;

import com.app.ck.base.GetCodeSetUP;
import com.app.ck.base.WrapperMethods;
import com.app.ck.pages.AccountSettingsPage;
import com.app.ck.pages.AskAQuestionPage;
import com.app.ck.pages.DeepLinkPage;
import com.app.ck.pages.FAQPage;
import com.app.ck.pages.ForgotPasswordPage;
import com.app.ck.pages.EKOnboardingPage;
import com.app.ck.pages.HistoryPage;
import com.app.ck.pages.HomePage;
import com.app.ck.pages.HowItWorks;
import com.app.ck.pages.IntermediateRetailerPage;
import com.app.ck.pages.JoinFreePage;
import com.app.ck.pages.MakeProfitLink;
import com.app.ck.pages.MyEarningsPage;
import com.app.ck.pages.OTPPage;
import com.app.ck.pages.PaymentOTPPage;
import com.app.ck.pages.ProductCategoryPage;
import com.app.ck.pages.ProductPage;
import com.app.ck.pages.ReferAndEarnLifeTime;
import com.app.ck.pages.ReferralNetworkPage;
import com.app.ck.pages.SignInPage;
import com.app.ck.pages.SignedInProfilePage;
import com.app.ck.pages.StoreCategoryPage;
import com.app.ck.pages.StoreDetailPage;
import com.app.ck.pages.StorePage;
import com.app.ck.pages.SuccessStories;
import com.app.ck.pages.TermsAndConditionPage;
import com.app.ck.pages.TestimonialsPage;
import com.app.ck.pages.WhatsAppUtility;
import com.app.ck.pages.admin.AdminCashbackPage;
import com.app.ck.pages.admin.AdminCoreFunction;
import com.app.ck.pages.admin.AdminPartnerSettingsPage;
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

public class EKTestSupportiveModuleValidation extends WrapperMethods {


	/*******************************************************************/
	/************************ PERSONAL DETAILS *************************/
	/*******************************************************************/

	// All Possible Positive and Negative flows covered in only one test case
	@Test
	public void personalDetailsPage_PositiveNegativeValidation() {

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo);
		String email = objCashKaroUtility.generateEmail();
		String fiftyUserFullName_ExceedChars = getTestData(7, "FiftyPlusUserChar");
		String fiftyUserFullName = getTestData(7, "FiftyUserChar");

		String mobNum = objCashKaroUtility.generateMobileNumber();
		String userName = getTestData(3, "UserFullName");
		String testUserName = "CashKaro Test User";

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
		new HomePage(driver, testInfo).
		clickOnProfileIconForSignedUser().
		clickOnAccountSettings().
		validateFullName(userName). // Valdiate Full name
		validateEmailAddress(email). // Valdiate User Email
		validateMobileNumber(mobNum). // validate user Mobile number
		clickOnSaveChangesButton().
		validatePleaseEnterDetailsError(). // Without entering details clicing save
		//changes button and validating error
		clickOnReceiveEmailWhenIGetReferralRadioButton(). // clicking receive referral radio button and save
		// changes
		clickOnSaveChangesButton().
		validateProfileUpdatedSuccessMessage().
		clickOnReceivWeeklyoffersNewsLetterRadioButton(). // clicking weeeky news letter readio button and save
		// changes
		validateProfileUpdatedSuccessMessage().
		clickOnReceiveEmailWhenIGetReferralRadioButton().
		clickOnSaveChangesButton().
		validateProfileUpdatedSuccessMessage().
		clickOnReceivWeeklyoffersNewsLetterRadioButton().
		validateProfileUpdatedSuccessMessage().
		enterFullName(testUserName). // enter new full and validate the full name in personal details page
		clickOnSaveChangesButton().
		validateFullName(testUserName).
		enterFullName("a"). // enter min char in user
		clickOnSaveChangesButton().
		validateMin2CharError(1).
		enterFullName("   "). // enter space in user full
				// name and validate error
		clickOnSaveChangesButton().
		validateFullNameFieldIsRequired().
		enterFullName(fiftyUserFullName_ExceedChars).
		clickOnSaveChangesButton().
		validateMax50CharError(1).
		enterFullName(fiftyUserFullName).
		clickOnSaveChangesButton(). // enter max 50 plus char in to user name
				// field and validate errror
		validateProfileUpdatedSuccessMessage();

	}

	/*******************************************************************/
	/************************ CHANGE PASSWORD **************************/
	/*******************************************************************/

	// All Possible Positive and Negative flows covered in only one test case
	@Test
	public void changePasswordPage_PositiveNegativeValidation() {

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo);
		String email = objCashKaroUtility.generateEmail();
		String mobNum = objCashKaroUtility.generateMobileNumber();
		String minPassword = "AA12";
		String maxPassword = "abcdefghijk123456789@####";
		String currentPassword = getTestData(0, "TC001_ValidLoginPassword");
		String password1 = "TestUser@1";
		String password2 = "TestUser@3";

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

		String newEmail = objCashKaroUtility.generateEmail();

		// Once after clicking on the Click on verify OTP , it should navigate to the
		// Home page
		new HomePage(driver, testInfo).
		clickOnProfileIconForSignedUser().
		clickOnAccountSettings().
		clickOnChangePasswordTab().
		clickOnSaveChangesButton().
		validatePleaseEnterCurrentPassword().
		validiatePleaseEnterNewPassword().
		validatePleaseEnterConfirmPassword().
		enterCurrentPassword(minPassword).
		enterNewPassword(minPassword).
		enterConfirmPassword(minPassword).
		clickOnSaveChangesButton().
		validatePasswordMustBeAtleast6CharError(3).
		enterCurrentPassword(maxPassword).
		enterNewPassword(maxPassword).
		enterConfirmPassword(maxPassword).
		clickOnSaveChangesButton().
		validatePasswordMustBeLessThan20Chars(3).
		enterCurrentPassword(currentPassword).
		enterNewPassword(password1).
		enterConfirmPassword(password2).
		clickOnSaveChangesButton().
		validiatePasswordDoesNotMatchError().
		enterCurrentPassword(password1).
		enterNewPassword(password2).
		enterConfirmPassword(password2).
		clickOnSaveChangesButton().
		validatePasswordDoesNotMatchTryAgain().
		enterCurrentPassword(currentPassword).
		enterNewPassword(password2).
		enterConfirmPassword(password2).
		clickOnSaveChangesButton().
		validateNewPasswordUpdatedSuccessMessage().
		clickOnBackButton().
		clickOnLogout().
		clickOnSignInbutton_ForSecondTime().
		enterUserName(email).
		enterPassword(currentPassword).
		clickOnSignInWithEmailForFailure().
		validateAuthenticationFailedErrorMessage().
		enterPassword(password2).
		clickSignInButtonForSuccess();

	}

	/*******************************************************************/
	/************************ PAYMENT SETTINGS *************************/
	/*******************************************************************/

	// All Possible Positive and Negative flows covered in only one test case
	@Test
	public void paymentSettings_NEFTValiation() {

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo);
		String email = objCashKaroUtility.generateEmail();
		String mobNum = objCashKaroUtility.generateMobileNumber();

		String otpValue = new EKOnboardingPage(driver, testInfo).
				clickOnSignUpButton().enterFullName()
				.enterEmail(email).
				enterPassword().
				enterMobileNumber(mobNum).clickOnGetOTPButton(mobNum);

		new OTPPage(driver, testInfo).enterOTP(otpValue).clickOnVerifyOTP();

		String newEmail = objCashKaroUtility.generateEmail();

		// Once after clicking on the Click on verify OTP , it should navigate to the
		// Home page
		AccountSettingsPage objAccountSettings = new HomePage(driver, testInfo).clickOnProfileIconForSignedUser()
				.clickOnAccountSettings().clickOnPaymentSettings().clickonPaymentMethodDropDown()
				.selectBankPaymentNEFTFromDropDown().enterBankNEFTPaymentDetails().clickOnSaveChangesButton()
				.validatePaymentSettingsUpdated();

		// Adding the bonus using GetCodeSetUP.php - contact dev team for the GetCodeSetUP
		objCashKaroUtility.addCashbackBonus(email, getTestData(6, "TwoFifty"), getTestData(7, "Cashback"));

		PaymentOTPPage objPaymentOTP = objAccountSettings.clickOnBackButton().clickOnMyEarnings()
				.clickOnRequestProfitPaymentButton().clickOnPaymentMethodDropDown()
				.selectBankPaymentNEFTFromDropDown().validateNEFTPaymentSettings().clickOnGETPAID();

		// SignUp OTP and payment OTP are same , so Sign up OTP has been used to get the
		// Payment OTP
		otpValue = objCashKaroUtility.generatePaymentOTP(mobNum);

		objPaymentOTP.enterOTP(otpValue).clickOnVerifyOTPAndValidateThePaymentSuccessMessage().clickBackButton()
				.clickOnAccountSettings().clickOnPaymentSettings().clickonPaymentMethodDropDown()
				.selectBankPaymentNEFTFromDropDown().validateNEFTPaymentSettings().enterBankNEFTPaymentDetails()
				.clickOnSaveChangesButton().validatePaymentSettingsUpdated();

	}

	@Test
	public void paymentSettings_AmazonGiftCard() {

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo);
		String email = objCashKaroUtility.generateEmail();
		String mobNum = objCashKaroUtility.generateMobileNumber();
		String email_2 = objCashKaroUtility.generateEmail();

		String otpValue = new EKOnboardingPage(driver, testInfo).clickOnSignUpButton().

				enterFullName().enterEmail(email).enterPassword().enterMobileNumber(mobNum).clickOnGetOTPButton(mobNum);

		new OTPPage(driver, testInfo).enterOTP(otpValue).clickOnVerifyOTP();

		String newEmail = objCashKaroUtility.generateEmail();

		// Once after clicking on the Click on verify OTP , it should navigate to the
		// Home page
		AccountSettingsPage objAccountSettings = new HomePage(driver, testInfo).clickOnProfileIconForSignedUser()
				.clickOnAccountSettings().clickOnPaymentSettings().clickonPaymentMethodDropDown()
				.selectAmazonGiftCardFromDropDown().enterAmazonGiftCardEmailID().enterPasswordToConfirm_Amazon()
				.clickOnSaveChangesButton().validatePaymentSettingsUpdated();

		// Adding the bonus using GetCodeSetUP.php - contact dev team for the GetCodeSetUP
		objCashKaroUtility.addCashbackBonus(email, getTestData(6, "TwoFifty"), getTestData(7, "Cashback"));

		PaymentOTPPage objPaymentOTP = objAccountSettings.clickOnBackButton().clickOnMyEarnings()
				.clickOnRequestProfitPaymentButton().clickOnPaymentMethodDropDown().selectAmazonGiftCardFromDropDown()
				.validateGiftCardEmail().clickOnGETPAID();

		// SignUp OTP and payment OTP are same , so Sign up OTP has been used to get the
		// Payment OTP
		otpValue = objCashKaroUtility.generatePaymentOTP(mobNum);

		objPaymentOTP.enterOTP(otpValue).clickOnVerifyOTPAndValidateThePaymentSuccessMessage().clickBackButton()
				.clickOnAccountSettings().clickOnPaymentSettings().clickonPaymentMethodDropDown()
				.selectAmazonGiftCardFromDropDown().validateGiftCardEmail().enterAmazonGiftCardEmailID(email_2)
				.enterPasswordToConfirm_Amazon().clickOnSaveChangesButton().validatePaymentSettingsUpdated();

	}

	@Test
	public void paymentSettings_FlipkartGiftCard() {

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

		String newEmail = objCashKaroUtility.generateEmail();

		// Once after clicking on the Click on verify OTP , it should navigate to the
		// Home page
		AccountSettingsPage objAccountSettings = new HomePage(driver, testInfo).
		clickOnProfileIconForSignedUser().
		clickOnAccountSettings().
		clickOnPaymentSettings().
		clickonPaymentMethodDropDown().
		selectFlipKartGiftCardFromDropDown().
		enterFlipKartGiftCardEmailID().
		enterPasswordToConfirm_FlipKart().
		clickOnSaveChangesButton().
		validatePaymentSettingsUpdated();

		// Adding the bonus using GetCodeSetUP.php - contact dev team for the GetCodeSetUP
		objCashKaroUtility.
		addCashbackBonus(email, getTestData(6, "TwoFifty"), getTestData(7, "Cashback"));

		PaymentOTPPage objPaymentOTP = objAccountSettings.
		clickOnBackButton().
		clickOnMyEarnings().
		clickOnRequestProfitPaymentButton().
		clickOnPaymentMethodDropDown().
		selectFlipKartGiftCardFromDropDown().
		validateGiftCardEmail().
		clickOnGETPAID();

		// SignUp OTP and payment OTP are same , so Sign up OTP has been used to get the
		// Payment OTP
		otpValue = objCashKaroUtility.generatePaymentOTP(mobNum);

		objPaymentOTP.enterOTP(otpValue).
		clickOnVerifyOTPAndValidateThePaymentSuccessMessage().
		clickBackButton().
		clickOnAccountSettings().
		clickOnPaymentSettings().
		clickonPaymentMethodDropDown().
		selectFlipKartGiftCardFromDropDown().
		validateGiftCardEmail().
		enterFlipKartGiftCardEmailID().
		enterPasswordToConfirm_FlipKart().
		clickOnSaveChangesButton().
		validatePaymentSettingsUpdated();

	}

	@Test
	public void paymentSettings_PaytmWallet() {
		
		//Object Creation :
		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo);
		
		//Test data variables :
		String email 		= objCashKaroUtility.generateEmail();
		String mobileNumber = objCashKaroUtility.generateMobileNumber();
		String mobileNum 	= objCashKaroUtility.generateMobileNumber();
		
		String otpValue = new EKOnboardingPage(driver, testInfo).
		clickOnSignUpButton().
		enterFullName().
		enterEmail(email).
		enterPassword().
		enterMobileNumber(mobileNumber).
		clickOnGetOTPButton(mobileNumber);

		new OTPPage(driver, testInfo).
		enterOTP(otpValue).
		clickOnVerifyOTP();

		String newEmail = objCashKaroUtility.
		generateEmail();

		// Once after clicking on the Click on verify OTP , it should navigate to the
		// Home page
		AccountSettingsPage objAccountSettings = new HomePage(driver, testInfo).
		clickOnProfileIconForSignedUser().
		clickOnAccountSettings().
		clickOnPaymentSettings().
		clickonPaymentMethodDropDown().
		selectPaytmWalletFromDropDown().
		enterPaytmWalletMobileNumber(mobileNumber).
		enterPasswordToConfirm_PaytmWallet().
		clickOnSaveChangesButton().
		validatePaymentSettingsUpdated();

		// Adding the bonus using GetCodeSetUP.php - contact dev team for the GetCodeSetUP
		objCashKaroUtility.addCashbackBonus(email, getTestData(6, "TwoFifty"), getTestData(7, "Cashback"));

		PaymentOTPPage objPaymentOTP = objAccountSettings.
		clickOnBackButton().
		clickOnMyEarnings().
		clickOnRequestProfitPaymentButton().
		clickOnPaymentMethodDropDown().
		selectPaytmWalletCardFromDropDown().
		validateWalletMobileNumber(mobileNumber).
		clickOnGETPAID();

		// SignUp OTP and payment OTP are same , so Sign up OTP has been used to get the
		// Payment OTP
		otpValue = objCashKaroUtility.
		generatePaymentOTP(mobileNumber);

		objPaymentOTP.
		enterOTP(otpValue).
		clickOnVerifyOTPAndValidateThePaymentSuccessMessage().
		clickBackButton().
		clickOnAccountSettings().
		clickOnPaymentSettings().
		clickonPaymentMethodDropDown().
		selectPaytmWalletFromDropDown().
		validateWalletMobileNumber(mobileNumber).
		enterPaytmWalletMobileNumber(mobileNum).
		enterPasswordToConfirm_PaytmWallet().
		clickOnSaveChangesButton().
		validatePaymentSettingsUpdated();

	}

	// All Possible Positive and Negative flows covered in only one test case
	@Test
	public void paymentSettings_NEFT_NegativeValidation() {

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

		String newEmail = objCashKaroUtility.generateEmail();

		// Once after clicking on the Click on verify OTP , it should navigate to the
		// Home page
		AccountSettingsPage objAccountSettings = new HomePage(driver, testInfo).
				clickOnProfileIconForSignedUser()
				.clickOnAccountSettings().
				clickOnPaymentSettings().
				clickonPaymentMethodDropDown().
				selectBankPaymentNEFTFromDropDown().
				clickOnSaveChangesButton().
				validateNEFTFieldErrorMessages(). // Validate
				clickOnBackButton().
				clickOnAccountSettings().
				clickOnPaymentSettings().
				clickonPaymentMethodDropDown().
				selectBankPaymentNEFTFromDropDown().
				enterAlphabetsIntoAccountNumberAndValidateErrorMessage().
				clickOnBackButton().
				clickOnAccountSettings().
				clickOnPaymentSettings().
				clickonPaymentMethodDropDown().
				selectBankPaymentNEFTFromDropDown().
				enterSpecialCharacterIntoAccountNumberAndValidateErrorMessage().
				clickOnBackButton().
				clickOnAccountSettings().
				clickOnPaymentSettings().
				clickonPaymentMethodDropDown().
				selectBankPaymentNEFTFromDropDown().
				validateInvalidIFSCCodeServerSideValidation(mobNum).
				clickOnBackButton().
				clickOnAccountSettings().
				clickOnPaymentSettings().
				clickonPaymentMethodDropDown().
				selectBankPaymentNEFTFromDropDown().
				enterNumbersIntoAccountHolderNameAndvalidateErrorMessage().
				clickOnBackButton().
				clickOnAccountSettings().
				clickOnPaymentSettings().
				clickonPaymentMethodDropDown().
				selectBankPaymentNEFTFromDropDown().
				enterNumbersIntoBankNameAndvalidateErrorMessage().
				clickOnBackButton().
				clickOnAccountSettings().
				clickOnPaymentSettings().
				clickonPaymentMethodDropDown().
				selectBankPaymentNEFTFromDropDown().
				enterSpecialCharacterIntoBankNameAndvalidateErrorMessage().
				clickOnBackButton().
				clickOnAccountSettings().
				clickOnPaymentSettings().
				clickonPaymentMethodDropDown().
				selectBankPaymentNEFTFromDropDown().
				enterSpecialCharacterIntoBankBranchNameAndValidateErrorMessage().
				clickOnBackButton().
				clickOnAccountSettings().
				clickOnPaymentSettings().
				clickonPaymentMethodDropDown().
				selectBankPaymentNEFTFromDropDown().
				enterDetailsWithOutPassword_And_ValidateTheError();

	}

	// All Possible Positive and Negative flows covered in only one test case
	@Test
	public void paymentSettings_AmazonGC_NegativeValidation() {

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo);
		String email = objCashKaroUtility.generateEmail();
		String mobNum = objCashKaroUtility.generateMobileNumber();

		String otpValue = new EKOnboardingPage(driver, testInfo).clickOnSignUpButton().enterFullName()
				.enterEmail(email).enterPassword().enterMobileNumber(mobNum).clickOnGetOTPButton(mobNum);

		new OTPPage(driver, testInfo).enterOTP(otpValue).clickOnVerifyOTP();

		String newEmail = objCashKaroUtility.generateEmail();

		// Once after clicking on the Click on verify OTP , it should navigate to the
		// Home page
		AccountSettingsPage objAccountSettings = new HomePage(driver, testInfo).clickOnProfileIconForSignedUser()
				.clickOnAccountSettings().clickOnPaymentSettings().clickonPaymentMethodDropDown()
				.selectAmazonGiftCardFromDropDown().clickOnSaveChangesButton()
				.validateAmazonGiftCardErrorValidation_AllPossible();

	}

	// All Possible Positive and Negative flows covered in only one test case
	@Test
	public void paymentSettings_FlipKart_NegativeValidation() {

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo);
		String email = objCashKaroUtility.generateEmail();
		String mobNum = objCashKaroUtility.generateMobileNumber();

		String otpValue = new EKOnboardingPage(driver, testInfo).clickOnSignUpButton().

				enterFullName().enterEmail(email).enterPassword().enterMobileNumber(mobNum).clickOnGetOTPButton(mobNum);

		new OTPPage(driver, testInfo).enterOTP(otpValue).clickOnVerifyOTP();

		String newEmail = objCashKaroUtility.generateEmail();

		// Once after clicking on the Click on verify OTP , it should navigate to the
		// Home page
		AccountSettingsPage objAccountSettings = new HomePage(driver, testInfo).clickOnProfileIconForSignedUser()
				.clickOnAccountSettings().clickOnPaymentSettings().clickonPaymentMethodDropDown()
				.selectFlipKartGiftCardFromDropDown().clickOnSaveChangesButton()
				.validateFlipKartGiftCardErrorValidation_AllPossible();

	}

	
	/*******************************************************************/
	/************************ FORGOT PASSWORD **************************/
	/*******************************************************************/

	// Forgot Password validation :
	@Test
	public void forgotPassword_Validation() {

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo);
		String email = objCashKaroUtility.generateEmail();
		String mobNum = objCashKaroUtility.generateMobileNumber();
		String invalidEmail = "abcdtest@zyxu.kom";
		String numericInvalidEmail = "1825@233.856";
		String specialCharacters = "#@%&+_-=@gmail.com";

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
		new HomePage(driver, testInfo).
		clickOnProfileIconForSignedUser().
		clickOnLogout().
		clickOnSignInbutton_ForSecondTime().
		clickOnForgotPasswordLink().
		enterYourEmail(email).
		clickOnSubmitRequestButton(). // Enter a valid email - and click on forgot password button
		validateTheForgotPasswordSuccessMessage().
		enterYourEmail(invalidEmail). // Enter non existing mail and
		clickOnSubmitRequestButton().
		validateSorryThereIsNoActiveUserError(invalidEmail).
		enterYourEmail(numericInvalidEmail). // Enter Numeric chars and validate error
		clickOnSubmitRequestButton().
		validateEmailMustBeAValidEmail().
		enterYourEmail(specialCharacters).
		validateEmailMustBeAValidEmail().
		enterYourEmail("a"). // Enter a single char and validate the error
		clickOnSubmitRequestButton().
		validateMin2CharsLongError().
		enterYourEmail("").
		clickOnSubmitRequestButton().
		validatePleaseEnterTheEmail().
		validateAllobjects().
		clickOnLogin().
		enterUserName(email).
		enterPassword().
		clickSignInButtonForFailure().
		validateAuthenticationFailedErrorMessage().
		clickOnForgotPasswordLink().
		clickOnBackButton();

	}

	/*******************************************************************/
	/************************ TESTIMONIAL ******************************/
	/*******************************************************************/

	// All Possible Positive and Negative flows covered in only one test case
	@Test
	public void testimonial_Validations() {

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo);
		String email = objCashKaroUtility.generateEmail();
		String mobNum = objCashKaroUtility.generateMobileNumber();
		String testimonialCount = "";

		String otpValue = new EKOnboardingPage(driver, testInfo).clickOnSignUpButton().enterFullName()
				.enterEmail(email).enterPassword().enterMobileNumber(mobNum).clickOnGetOTPButton(mobNum);

		new OTPPage(driver, testInfo).enterOTP(otpValue).clickOnVerifyOTP();

		testimonialCount = new HomePage(driver, testInfo).clickOnProfileIconForSignedUser().clickonRateUs()
				.clickOnWriteTestimonialPage().enterfeedBack(getTestData(12, "OneStarFeedback")).clickStarOne()
				.clickOnSubmitButton().clickOnWriteTestimonialPage().enterfeedBack(getTestData(12, "TwoStarFeedback"))
				.clickStarTwo().clickOnSubmitButton().clickOnWriteTestimonialPage()
				.enterfeedBack(getTestData(12, "ThreeStarFeedback")).clickStarThree().clickOnSubmitButton()
				.clickOnWriteTestimonialPage().enterfeedBack(getTestData(12, "FourStarFeedback")).clickStarFour()
				.clickOnSubmitButton().clickOnWriteTestimonialPage().enterfeedBack(getTestData(12, "FiveStarFeedback"))
				.clickStarFive().clickOnSubmitButton().getTestimonialsCount();

		/*
		 * Step 2 : Navigate to Admin and and aprove the Testimonial
		 */

		// Admin actions
		CashKaroUtility cu = new CashKaroUtility(udid, port, automationName, deviceName);
		driver = cu.launchChromeWebView(driver);

		AdminCoreFunction adminFunctions = new AdminCoreFunction(driver, testInfo);
		adminFunctions.naviagateAdminPage_ThenSelectEarnKaro();
		adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
		adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
		adminFunctions.clickSubmit();
		adminFunctions.clickOnHamburgerMenu();
		adminFunctions.clickOnInteractiveMainMenu();
		adminFunctions.clickOnSubMenuTestimonials();

		TestimonialPages testimonial = new TestimonialPages(driver, testInfo);

		testimonial.selectUserEmailInSearchByDropDown();
		testimonial.enterKeyWordAsUserEmail(email);
		testimonial.clickOnSearchButton();

		for (int counter = 5; counter >= 1; counter--) {

			testimonial.clickOnEditButtonBasedOnStarts(email, Integer.toString(counter));
			testimonial.changeStatus("Active");
			testimonial.enterTitle("APPIUM GIVES " + Integer.toString(counter) + " STARS");
			testimonial.clickOnSaveTestimonial();
		}

		/*
		 * Step 3 : Come back to app and validte the Active testimonials and its counts
		 */

		CashKaroUtility cuForCKApp_1 = new CashKaroUtility(udid, port, automationName, deviceName, systemPort);
		driver = cuForCKApp_1.launchEarnKaroApp(driver);

		new HomePage(driver, testInfo).clickOnProfileIconForSignedUser().clickonRateUs()
				.validateTestimonialCountIncreased(testimonialCount)
				.validateAdminTestimonialTitle_UserFeedBack_PostedTimings(testdata.get(3).get("UserFullName"));

		/*
		 * Step 4 : Go back to Admin & then inactive all the testimonials of that
		 * particular user
		 */

		// Admin actions
		CashKaroUtility cu2 = new CashKaroUtility(udid, port, automationName, deviceName);
		driver = cu2.launchChromeWebView(driver);

		AdminCoreFunction adminFunctions2 = new AdminCoreFunction(driver, testInfo);
		adminFunctions2.naviagateAdminPage_ThenSelectEarnKaro();
		adminFunctions2.enterUsername(getTestData(8, "AdminUserName"));
		adminFunctions2.enterPassword(getTestData(8, "AdminPassword"));
		adminFunctions2.clickSubmit();
		adminFunctions2.clickOnHamburgerMenu();
		adminFunctions2.clickOnInteractiveMainMenu();
		adminFunctions2.clickOnSubMenuTestimonials();

		TestimonialPages testimonial2 = new TestimonialPages(driver, testInfo);

		testimonial2.selectUserEmailInSearchByDropDown();
		testimonial2.enterKeyWordAsUserEmail(email);
		testimonial2.clickOnSearchButton();

		for (int counter = 5; counter >= 1; counter--) {

			testimonial2.clickOnEditButtonBasedOnStarts(email, Integer.toString(counter));
			testimonial2.changeStatus("In-Active");
			testimonial2.enterTitle("APPIUM GIVES " + Integer.toString(counter) + " STARS");
			testimonial2.clickOnSaveTestimonial();

		}

		/*
		 * Step 5 : Now, Come back to app and validate the Testimonial count decrement
		 * by 5
		 */

		CashKaroUtility cuForCKApp_2 = new CashKaroUtility(udid, port, automationName, deviceName, systemPort);
		driver = cuForCKApp_2.launchEarnKaroApp(driver);

		new HomePage(driver, testInfo).clickOnProfileIconForSignedUser().clickonRateUs()
				.validateTestimonialCount_Decreased(testimonialCount);

	}

	/*******************************************************************/
	/************************ WHATS APP SCENARIOS **********************/
	/*******************************************************************/

	// @Test
	public void sendDetailsViaWhatsAPP() throws InterruptedException {

		System.out.println("test started parallel ");
		reportStep("TC001 is  started", "INFO");

		WhatsAppUtility whatsAppUtility = new WhatsAppUtility(driver, testInfo, udid, port, automationName, deviceName,systemPort);

		HomePage objHomePage = 
		new EKOnboardingPage(driver, testInfo).
		clickOnSignInbutton().
		enterUserName().enterPassword().
		clickSignInButtonForSuccess();

		// Scroll down for 2 times
		scrollFromDownToUpinApp_FourSecDuration();
		scrollFromDownToUpinApp_FourSecDuration();

		objHomePage.clickOnShareNowButton();

		// Whats app Utility methods
		whatsAppUtility.clickOnWhatAppMenuItemSearch().
		enterWhatsAppSearchText("Cashkaro Jaikumar").
		clickOnRequiredContact().
		clickOnSend().
		clickOnSend().
		clickOnLastSharedLink().
		validateIntermediatePage();
		reportStep("TC001 is completed", "INFO");

	}

	/*******************************************************************/
	/********        	      ASKAQUESTION    			     ***********/
	/*******************************************************************/

	//Contact us positive and negative validation : 
	@Test
	public void askAQuestionForm_TC001() {

		reportStep("TC001 is  started", "INFO");

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo); 
		String email = objCashKaroUtility.generateEmail();
		String mobileNumber = objCashKaroUtility.generateMobileNumber();
		
		new EKOnboardingPage(driver,testInfo).
		clickOnSignInbutton().
		enterUserName().
		enterPassword().
		clickSignInButtonForSuccess().
		clickOnProfileIconForSignedUser().
		clickHelpLink().
		clickEmailUs().
		validateUserNameDefaultValue(getTestData(0, "TC001_EarnKaroUserName")).
		validateEmailAddressDefaultValue(getTestData(0, "TC001_ValidLoginEmail")).
		validateEnterMin2CharacterErrorMessage("1", "1", 1).
		validateEnterMax50CharacterErrorMessage(getTestData(7, "FiftyPlusUserChar"), getTestData(7, "FiftyPlusUserChar"), 1).
		clickBackButtonSignedUser().
		clickEmailUs().
		clickSelectFromListBelow().
		clickGeneralEnquirie().
		enterYourMessage("Message").
		clickSubmitButtonVerifySuccessMessage().
		clickBackButtonSignedUser().
		clickEmailUs().
		clickSelectFromListBelow().
		clickMyProfitIsIncorrect().
		enterYourMessage("Message").
		clickSubmitButtonVerifySuccessMessage().
		clickBackButtonSignedUser().
		clickEmailUs().
		clickSelectFromListBelow().
		clickOther().
		enterYourMessage("Message").
		clickSubmitButtonVerifySuccessMessage().
		clickBackButtonSignedUser().
		clickBackButton().
		clickOnLogout();
				
		String otpValue = new EKOnboardingPage(driver, testInfo).
		clickOnSignUpButton().	
		enterFullName().
		enterEmail(email).
		enterPassword().
		enterMobileNumber(mobileNumber).
		clickOnGetOTPButton(mobileNumber);

		new OTPPage(driver, testInfo).
		enterOTP(otpValue).
		clickOnVerifyOTP();

		new HomePage(driver, testInfo).
		clickOnProfileIconForSignedUser().
		clickHelpLink().
		clickEmailUs().
		validateUserNameDefaultValue(getTestData(3, "UserFullName")).
		validateEmailAddressDefaultValue(email).
		validateEnterMin2CharacterErrorMessage("1", "1", 1).
		validateEnterMax50CharacterErrorMessage(getTestData(7, "FiftyPlusUserChar"), getTestData(7, "FiftyPlusUserChar"), 1).
		clickBackButtonSignedUser().
		clickEmailUs().
		clickSelectFromListBelow().
		clickGeneralEnquirie().
		enterYourMessage("Message").
		clickSubmitButtonVerifySuccessMessage().
		clickBackButtonSignedUser().
		clickEmailUs().
		clickSelectFromListBelow().
		clickMyProfitIsIncorrect().
		enterYourMessage("Message").
		clickSubmitButtonVerifySuccessMessage().
		clickBackButtonSignedUser().
		clickEmailUs().
		clickSelectFromListBelow().
		clickOther().
		enterYourMessage("Message").
		clickSubmitButtonVerifySuccessMessage().
		clickBackButtonSignedUser().
		clickBackButton().
		clickOnLogout();
		
		reportStep("TC001 is completed", "INFO");

	}

	/*******************************************************************/
	/********              HOW EARNKARO WORKS     		     ***********/
	/*******************************************************************/

	// validate How earnkaro Works
	@Test
	public void validateHowEarnKaroWorks_StaticPage() {

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo);
		String email = objCashKaroUtility.generateEmail();
		String mobNumber = objCashKaroUtility.generateMobileNumber();

		String otpValue = new EKOnboardingPage(driver, testInfo).
		clickOnSignUpButton().
		enterFullName().
		enterEmail(email).
		enterPassword().
		enterMobileNumber(mobNumber).
		clickOnGetOTPButton(mobNumber);

		new OTPPage(driver, testInfo).enterOTP(otpValue).clickOnVerifyOTP();

		new HomePage(driver, testInfo).
		clickOnProfileIconForSignedUser().
		clickOnHowEarnKaroWorks();
		
		
//		clickRequiredLinkHowEarKaroWorks(getTestData(7, "HowtoShareLinksandEarn")).
//		clickBackButton().
//		clickRequiredLinkHowEarKaroWorks(getTestData(7, "WhatIsEarnKaroPendingProfit")).
//		clickBackButton().
//		clickRequiredLinkHowEarKaroWorks(getTestData(7, "HowToBecomeFlipkartLinks")).
//		clickBackButton().
//		clickRequiredLinkHowEarKaroWorks(getTestData(7, "HowToBecomeMyntraLinks")).
//		clickBackButton().
//		clickRequiredLinkHowEarKaroWorks(getTestData(7, "HowToBecomeAmzonLinks")).
//		clickBackButton().
//		clickRequiredLinkHowEarKaroWorks(getTestData(7, "HowToBecomeAjioLinks")).
//		clickBackButton();
//
//		backButton();
//
//		new SignedInProfilePage(driver, testInfo).clickOnLogout();

	}

	/*******************************************************************/
	/********           		 CALL US     			     ***********/
	/*******************************************************************/

	// validate Call Us
	@Test
	public void validateCallUsFunctionality() {

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo);
		String email = objCashKaroUtility.generateEmail();
		String mobNumber = objCashKaroUtility.generateMobileNumber();

		String otpValue = new EKOnboardingPage(driver, testInfo).
		clickOnSignUpButton().
		enterFullName().
		enterEmail(email).
		enterPassword().
		enterMobileNumber(mobNumber).
		clickOnGetOTPButton(mobNumber);

		new OTPPage(driver, testInfo).enterOTP(otpValue).clickOnVerifyOTP();

		new HomePage(driver, testInfo).
		clickOnProfileIconForSignedUser().
		clickHelpLink().
		clickCallUs();

		isElementLocatedByXpathPresent("//android.widget.EditText[contains(@text,'8178021403')]");

		backButton();
		backButton();
		backButton();

		new SignedInProfilePage(driver, testInfo).clickOnLogout();

	}

	/*******************************************************************/
	/********           		   FAQ	     			     ***********/
	/*******************************************************************/

	// validate FAQ
	@Test
	public void validateFAQFunctionality() {

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo);
		String email = objCashKaroUtility.generateEmail();
		String mobNumber = objCashKaroUtility.generateMobileNumber();

		String otpValue = new EKOnboardingPage(driver, testInfo).
		clickOnSignUpButton().
		enterFullName().
		enterEmail(email).
		enterPassword().
		enterMobileNumber(mobNumber).
		clickOnGetOTPButton(mobNumber);

		new OTPPage(driver, testInfo).enterOTP(otpValue).clickOnVerifyOTP();

		new HomePage(driver, testInfo).
		clickOnProfileIconForSignedUser().
		clickHelpLink().
		clickFAQ();

		clickOnRequiredText(getTestData(13, "HowcanIstartearning"), "FAQ page");

		new FAQPage(driver, testInfo).
		clickAskAQuestionButton();

		backButton();
		backButton();
		backButton();

		new SignedInProfilePage(driver, testInfo).clickOnLogout();

	}

	/*******************************************************************/
	/********           	SUCCESS STORIES     			     *******/
	/*******************************************************************/

	// validate SuccessStories
	@Test
	public void validateSuccessStoriesFunctionality() {

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo);
		String email = objCashKaroUtility.generateEmail();
		String mobNumber = objCashKaroUtility.generateMobileNumber();

		String otpValue = new EKOnboardingPage(driver, testInfo).
		clickOnSignUpButton().
		enterFullName().
		enterEmail(email).
		enterPassword().
		enterMobileNumber(mobNumber).
		clickOnGetOTPButton(mobNumber);

		new OTPPage(driver, testInfo).
		enterOTP(otpValue).
		clickOnVerifyOTP();

		new HomePage(driver, testInfo).
		clickOnProfileIconForSignedUser().
		clickonSuccessStories();

		new SuccessStories(driver, testInfo);

		backButton();

		new SignedInProfilePage(driver, testInfo).clickOnLogout();

	}
	
	/*******************************************************************/
	/********              		Refer and Earn  ***********/
	/*******************************************************************/
	
	/*Validate Refer & Earn LifeTime*/
	@Test
	public void referAndEarnLifeTime() {

		reportStep("Refer and Earn Life Time - Page element validations started ", "INFO");
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

		objHomePage.
		clickOnProfileIconForSignedUser().
		clickOnMyReferral().
		clickReferAndEarnLifeTime().
		validateReferAndEarnPage();
		
		reportStep("Refer and Earn Life Time - Page element validations end ", "PASS");
	}
	
	/*Validate Refer & Earn LifeTime*/
	@Test
	public void referAndEarnLifeTimeFacebookShareConsecutively() {

		reportStep("Refer and Earn Life Time - Page element validations started ", "INFO");
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

		objHomePage.
		clickOnProfileIconForSignedUser().
		clickOnMyReferral().
		clickReferAndEarnLifeTime().
		clickOnFacebook().
		enterFBUserEmail().
		enterFBPassword().
		clickOnFBLoginButton().
		clickOnFbPost().
		clickOnFacebook().
		clickOnFbPost().
		clickOnFacebook().
		clickOnFbPost();
		
		
		reportStep("Refer and Earn Life Time - Page element validations end ", "PASS");
		
	}
	
	/*Validate Refer & Earn LifeTime*/
	@Test
	public void referAndEarnLifeTimeTwitterShare() {

		reportStep("Refer and Earn Life Time - Page element validations started ", "INFO");
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

		objHomePage.
		clickOnProfileIconForSignedUser().
		clickOnMyReferral().
		clickReferAndEarnLifeTime().
		clickOnTwitter();
		
		reportStep("Refer and Earn Life Time - Page element validations end ", "PASS");
		
	}
	
	/*Validate Refer & Earn LifeTime*/
	@Test
	public void referAndEarnLifeTimeTWhatsAppShare() {

		reportStep("Refer and Earn Life Time - Page element validations started ", "INFO");
		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo);
		String email = objCashKaroUtility.generateEmail();
		String mobNum = objCashKaroUtility.generateMobileNumber();
		WhatsAppUtility whatsAppUtility = new WhatsAppUtility(driver, testInfo, udid, port, automationName, deviceName,systemPort);

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

		objHomePage.
		clickOnProfileIconForSignedUser().
		clickOnMyReferral().
		clickReferAndEarnLifeTime().
		clickOnWhatsApp();

		// Whats App Utility methods : 
		whatsAppUtility.clickOnWhatAppMenuItemSearch().
		enterWhatsAppSearchText(getTestData(18, "WhatsAppContact")).
		clickOnRequiredContact().
		clickOnSend().
		clickOnSend().
		validateReferAndEarnSharedTextInWhatsApp();
		reportStep("TC001 is completed", "INFO");
		
		reportStep("Refer and Earn Life Time - Page element validations end ", "PASS");
		
	}

	/*******************************************************************/
	/********              		DeepLinks				     ***********/
	/*******************************************************************/
	
	@Test
	public void deepLink_AppBackGround_LoggedInAsANewUser() {

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
		objOtpPage.enterOTP(otpValue).clickOnVerifyOTP();

		new HomePage(driver, testInfo);
		
		/***********************************************************************************************/
		/********************************** DeepLink - Home Link ***************************************/
		/***********************************************************************************************/
		
		CashKaroUtility objcashKaroutility = new CashKaroUtility(udid, port, automationName, deviceName);
		driver = objcashKaroutility.launchChromeWebView(driver);
		
		loadURL(GetCodeSetUP.DEEPLINKURL);
		
		Set<String> contextNames = driver.getContextHandles();
		driver.context("NATIVE_APP");

		new DeepLinkPage(driver, testInfo).
		clickHomeLink();

		new HomePage(driver, testInfo);
		
		/***********************************************************************************************/
		/********************************** DeepLink - Store Link **************************************/
		/***********************************************************************************************/
		
		driver = objcashKaroutility.launchChromeWebView(driver);
		
		loadURL(GetCodeSetUP.DEEPLINKURL);
		
		driver.context("NATIVE_APP");

		new DeepLinkPage(driver, testInfo).
		clickMakeMyTripStoreLink();

		new StoreDetailPage(driver, testInfo);
		
		/***********************************************************************************************/
		/******************************** DeepLink - Category Link *************************************/
		/***********************************************************************************************/
		
		driver = objcashKaroutility.launchChromeWebView(driver);
		
		loadURL(GetCodeSetUP.DEEPLINKURL);
		
		driver.context("NATIVE_APP");

		new DeepLinkPage(driver, testInfo).
		clickAppExclusiveLink();

		new ProductCategoryPage(driver, testInfo);
		
		/***********************************************************************************************/
		/******************************** DeepLink - Category Link *************************************/
		/***********************************************************************************************/
		
		driver = objcashKaroutility.launchChromeWebView(driver);
		
		loadURL(GetCodeSetUP.DEEPLINKURL);
		
		driver.context("NATIVE_APP");

		new DeepLinkPage(driver, testInfo).
		clickHighestCashbackLink();

		new ProductCategoryPage(driver, testInfo);
		
		/***********************************************************************************************/
		/******************************* DeepLink - How it works Link **********************************/
		/***********************************************************************************************/
		
		driver = objcashKaroutility.launchChromeWebView(driver);
		
		loadURL(GetCodeSetUP.DEEPLINKURL);
		
		driver.context("NATIVE_APP");

		new DeepLinkPage(driver, testInfo).
		clickHowItWorksLink();

		new HowItWorks(driver, testInfo);
		
		/***********************************************************************************************/
		/******************************* DeepLink - Testimonials Link **********************************/
		/***********************************************************************************************/
		
		driver = objcashKaroutility.launchChromeWebView(driver);
		
		loadURL(GetCodeSetUP.DEEPLINKURL);
		
		driver.context("NATIVE_APP");

		new DeepLinkPage(driver, testInfo).
		clickTestimonialsLink();

		new TestimonialPages(driver, testInfo);
		
		/***********************************************************************************************/
		/****************************** DeepLink - Contact Us Link *************************************/
		/***********************************************************************************************/
		
		driver = objcashKaroutility.launchChromeWebView(driver);
		
		loadURL(GetCodeSetUP.DEEPLINKURL);
		
		driver.context("NATIVE_APP");

		new DeepLinkPage(driver, testInfo).
		clickContactUsLink();

		new AskAQuestionPage(driver, testInfo);
		
		/***********************************************************************************************/
		/******************************** DeepLink - Settings Link *************************************/
		/***********************************************************************************************/
		
		driver = objcashKaroutility.launchChromeWebView(driver);
		
		loadURL(GetCodeSetUP.DEEPLINKURL);
		
		driver.context("NATIVE_APP");

		new DeepLinkPage(driver, testInfo).
		clickSettingsLink();

		new AccountSettingsPage(driver, testInfo);
		
		/***********************************************************************************************/
		/******************************* DeepLink - My earnings Link ***********************************/
		/***********************************************************************************************/
		
		driver = objcashKaroutility.launchChromeWebView(driver);
		
		loadURL(GetCodeSetUP.DEEPLINKURL);
		
		driver.context("NATIVE_APP");

		new DeepLinkPage(driver, testInfo).
		clickMyEarningsLink();

		new MyEarningsPage(driver, testInfo);
		
		/***********************************************************************************************/
		/****************************** DeepLink - Shared activities Link ******************************/
		/***********************************************************************************************/
		
		driver = objcashKaroutility.launchChromeWebView(driver);
		
		loadURL(GetCodeSetUP.DEEPLINKURL);
		
		driver.context("NATIVE_APP");

		new DeepLinkPage(driver, testInfo).
		clickMySharedActivitiesLink();

		new HistoryPage(driver, testInfo);
		
		/***********************************************************************************************/
		/****************************** DeepLink - Make profit Link ************************************/
		/***********************************************************************************************/
		
		driver = objcashKaroutility.launchChromeWebView(driver);
		
		loadURL(GetCodeSetUP.DEEPLINKURL);
		
		driver.context("NATIVE_APP");

		new DeepLinkPage(driver, testInfo).
		clickMakeProfitLinksLink();

		new MakeProfitLink(driver, testInfo);
		
		/***********************************************************************************************/
		/****************************** DeepLink - Success stories Link ********************************/
		/***********************************************************************************************/
		
		driver = objcashKaroutility.launchChromeWebView(driver);
		
		loadURL(GetCodeSetUP.DEEPLINKURL);
		
		driver.context("NATIVE_APP");

		new DeepLinkPage(driver, testInfo).
		clickSuccessStoriesLink();

		new SuccessStories(driver, testInfo);
		
		/***********************************************************************************************/
		/****************************** DeepLink - Refer & earn Link ***********************************/
		/***********************************************************************************************/
		
		driver = objcashKaroutility.launchChromeWebView(driver);
		
		loadURL(GetCodeSetUP.DEEPLINKURL);
		
		driver.context("NATIVE_APP");

		new DeepLinkPage(driver, testInfo).
		clickReferEarnLink();

		new ReferAndEarnLifeTime(driver, testInfo);
		
		/***********************************************************************************************/
		/************************* DeepLink - Referral network Link ************************************/
		/***********************************************************************************************/
		
		driver = objcashKaroutility.launchChromeWebView(driver);
		
		loadURL(GetCodeSetUP.DEEPLINKURL);
		
		driver.context("NATIVE_APP");

		new DeepLinkPage(driver, testInfo).
		clickMyReferralNetworkLink();

		new ReferralNetworkPage(driver, testInfo);
		
		/***********************************************************************************************/
		/********************************** DeepLink - FAQ Link ****************************************/
		/***********************************************************************************************/
		
		driver = objcashKaroutility.launchChromeWebView(driver);
		
		loadURL(GetCodeSetUP.DEEPLINKURL);
		
		driver.context("NATIVE_APP");

		new DeepLinkPage(driver, testInfo).
		clickFaqLink();

		new FAQPage(driver, testInfo);

		/***********************************************************************************************/
		
		CashKaroUtility reopenCKApp = new CashKaroUtility(udid, port, automationName, deviceName, systemPort);
		driver = reopenCKApp.launchEarnKaroApp(driver);
		
		new HomePage(driver, testInfo).
		clickOnProfileIconForSignedUser().
		clickOnLogout();
		
		/***********************************************************************************************/
		/******************************* DeepLink - Join free Link *************************************/
		/***********************************************************************************************/
		
		driver = objcashKaroutility.launchChromeWebView(driver);
		
		loadURL(GetCodeSetUP.DEEPLINKURL);
		
		driver.context("NATIVE_APP");

		new DeepLinkPage(driver, testInfo).
		clickJoinFreeLink();

		new JoinFreePage(driver, testInfo);
		
		/***********************************************************************************************/
		/********************************** DeepLink - Sign in Link ************************************/
		/***********************************************************************************************/
		
		driver = objcashKaroutility.launchChromeWebView(driver);
		
		loadURL(GetCodeSetUP.DEEPLINKURL);
		
		driver.context("NATIVE_APP");

		new DeepLinkPage(driver, testInfo).
		clickSignInLink();

		new SignInPage(driver, testInfo);
		
		/***********************************************************************************************/
		/******************************* DeepLink - Forgot pwd Link ************************************/
		/***********************************************************************************************/
		
		driver = objcashKaroutility.launchChromeWebView(driver);
		
		loadURL(GetCodeSetUP.DEEPLINKURL);
		
		driver.context("NATIVE_APP");

		new DeepLinkPage(driver, testInfo).
		clickForgotPasswordLink();

		new ForgotPasswordPage(driver, testInfo);
		
		/***********************************************************************************************/
		/****************************** DeepLink - Terms & conditions Link *****************************/
		/***********************************************************************************************/
		
		driver = objcashKaroutility.launchChromeWebView(driver);
		
		loadURL(GetCodeSetUP.DEEPLINKURL);
		
		driver.context("NATIVE_APP");

		new DeepLinkPage(driver, testInfo).
		clickTermsAndConditionsLink();

		new TermsAndConditionPage(driver, testInfo);
		
		/***********************************************************************************************/
				
	}

	@Test
	public void deepLink_AppClosed_LoggedInAsANewUser() {

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
		objOtpPage.enterOTP(otpValue).clickOnVerifyOTP();

		new HomePage(driver, testInfo);
		
		toggleApp();
		closeEarnKaroApp(deviceName);
		
		/***********************************************************************************************/
		/********************************** DeepLink - Home Link ***************************************/
		/***********************************************************************************************/
		
		CashKaroUtility objcashKaroutility = new CashKaroUtility(udid, port, automationName, deviceName);
		driver = objcashKaroutility.launchChromeWebView(driver);
		
		loadURL(GetCodeSetUP.DEEPLINKURL);
		
		Set<String> contextNames = driver.getContextHandles();
		driver.context("NATIVE_APP");

		new DeepLinkPage(driver, testInfo).
		clickHomeLink();

		new HomePage(driver, testInfo);
		
		toggleApp();
		closeEarnKaroApp(deviceName);
		
		/***********************************************************************************************/
		/********************************** DeepLink - Store Link **************************************/
		/***********************************************************************************************/
		
		driver = objcashKaroutility.launchChromeWebView(driver);
		
		loadURL(GetCodeSetUP.DEEPLINKURL);
		
		driver.context("NATIVE_APP");

		new DeepLinkPage(driver, testInfo).
		clickMakeMyTripStoreLink();

		new StoreDetailPage(driver, testInfo);
		
		toggleApp();
		closeEarnKaroApp(deviceName);
		
		
		/***********************************************************************************************/
		/******************************** DeepLink - Category Link *************************************/
		/***********************************************************************************************/
		
		driver = objcashKaroutility.launchChromeWebView(driver);
		
		loadURL(GetCodeSetUP.DEEPLINKURL);
		
		driver.context("NATIVE_APP");

		new DeepLinkPage(driver, testInfo).
		clickAppExclusiveLink();

		new ProductCategoryPage(driver, testInfo);
		
		toggleApp();
		closeEarnKaroApp(deviceName);
		
		
		/***********************************************************************************************/
		/******************************** DeepLink - Category Link *************************************/
		/***********************************************************************************************/
		
		driver = objcashKaroutility.launchChromeWebView(driver);
		
		loadURL(GetCodeSetUP.DEEPLINKURL);
		
		driver.context("NATIVE_APP");

		new DeepLinkPage(driver, testInfo).
		clickHighestCashbackLink();

		new ProductCategoryPage(driver, testInfo);
		
		toggleApp();
		closeEarnKaroApp(deviceName);
		
		
		/***********************************************************************************************/
		/******************************* DeepLink - How it works Link **********************************/
		/***********************************************************************************************/
		
		driver = objcashKaroutility.launchChromeWebView(driver);
		
		loadURL(GetCodeSetUP.DEEPLINKURL);
		
		driver.context("NATIVE_APP");

		new DeepLinkPage(driver, testInfo).
		clickHowItWorksLink();

		new HowItWorks(driver, testInfo);
		
		toggleApp();
		closeEarnKaroApp(deviceName);
		
		
		/***********************************************************************************************/
		/******************************* DeepLink - Testimonials Link **********************************/
		/***********************************************************************************************/
		
		driver = objcashKaroutility.launchChromeWebView(driver);
		
		loadURL(GetCodeSetUP.DEEPLINKURL);
		
		driver.context("NATIVE_APP");

		new DeepLinkPage(driver, testInfo).
		clickTestimonialsLink();

		new TestimonialPages(driver, testInfo);
		
		toggleApp();
		closeEarnKaroApp(deviceName);
		
		
		/***********************************************************************************************/
		/****************************** DeepLink - Contact Us Link *************************************/
		/***********************************************************************************************/
		
		driver = objcashKaroutility.launchChromeWebView(driver);
		
		loadURL(GetCodeSetUP.DEEPLINKURL);
		
		driver.context("NATIVE_APP");

		new DeepLinkPage(driver, testInfo).
		clickContactUsLink();

		new AskAQuestionPage(driver, testInfo);
		
		toggleApp();
		closeEarnKaroApp(deviceName);
		
		
		/***********************************************************************************************/
		/******************************** DeepLink - Settings Link *************************************/
		/***********************************************************************************************/
		
		driver = objcashKaroutility.launchChromeWebView(driver);
		
		loadURL(GetCodeSetUP.DEEPLINKURL);
		
		driver.context("NATIVE_APP");

		new DeepLinkPage(driver, testInfo).
		clickSettingsLink();

		new AccountSettingsPage(driver, testInfo);
		
		toggleApp();
		closeEarnKaroApp(deviceName);
		
		
		/***********************************************************************************************/
		/******************************* DeepLink - My earnings Link ***********************************/
		/***********************************************************************************************/
		
		driver = objcashKaroutility.launchChromeWebView(driver);
		
		loadURL(GetCodeSetUP.DEEPLINKURL);
		
		driver.context("NATIVE_APP");

		new DeepLinkPage(driver, testInfo).
		clickMyEarningsLink();

		new MyEarningsPage(driver, testInfo);
		
		toggleApp();
		closeEarnKaroApp(deviceName);
		
		
		/***********************************************************************************************/
		/****************************** DeepLink - Shared activities Link ******************************/
		/***********************************************************************************************/
		
		driver = objcashKaroutility.launchChromeWebView(driver);
		
		loadURL(GetCodeSetUP.DEEPLINKURL);
		
		driver.context("NATIVE_APP");

		new DeepLinkPage(driver, testInfo).
		clickMySharedActivitiesLink();

		new HistoryPage(driver, testInfo);
		
		toggleApp();
		closeEarnKaroApp(deviceName);
		
		
		/***********************************************************************************************/
		/****************************** DeepLink - Make profit Link ************************************/
		/***********************************************************************************************/
		
		driver = objcashKaroutility.launchChromeWebView(driver);
		
		loadURL(GetCodeSetUP.DEEPLINKURL);
		
		driver.context("NATIVE_APP");

		new DeepLinkPage(driver, testInfo).
		clickMakeProfitLinksLink();

		new MakeProfitLink(driver, testInfo);
		
		toggleApp();
		closeEarnKaroApp(deviceName);
		
		
		/***********************************************************************************************/
		/****************************** DeepLink - Success stories Link ********************************/
		/***********************************************************************************************/
		
		driver = objcashKaroutility.launchChromeWebView(driver);
		
		loadURL(GetCodeSetUP.DEEPLINKURL);
		
		driver.context("NATIVE_APP");

		new DeepLinkPage(driver, testInfo).
		clickSuccessStoriesLink();

		new SuccessStories(driver, testInfo);
		
		toggleApp();
		closeEarnKaroApp(deviceName);
		
		
		/***********************************************************************************************/
		/****************************** DeepLink - Refer & earn Link ***********************************/
		/***********************************************************************************************/
		
		driver = objcashKaroutility.launchChromeWebView(driver);
		
		loadURL(GetCodeSetUP.DEEPLINKURL);
		
		driver.context("NATIVE_APP");

		new DeepLinkPage(driver, testInfo).
		clickReferEarnLink();

		new ReferAndEarnLifeTime(driver, testInfo);
		
		toggleApp();
		closeEarnKaroApp(deviceName);
		
		
		/***********************************************************************************************/
		/************************* DeepLink - Referral network Link ************************************/
		/***********************************************************************************************/
		
		driver = objcashKaroutility.launchChromeWebView(driver);
		
		loadURL(GetCodeSetUP.DEEPLINKURL);
		
		driver.context("NATIVE_APP");

		new DeepLinkPage(driver, testInfo).
		clickMyReferralNetworkLink();

		new ReferralNetworkPage(driver, testInfo);
		
		toggleApp();
		closeEarnKaroApp(deviceName);
		
		
		/***********************************************************************************************/
		/********************************** DeepLink - FAQ Link ****************************************/
		/***********************************************************************************************/
		
		driver = objcashKaroutility.launchChromeWebView(driver);
		
		loadURL(GetCodeSetUP.DEEPLINKURL);
		
		driver.context("NATIVE_APP");

		new DeepLinkPage(driver, testInfo).
		clickFaqLink();

		new FAQPage(driver, testInfo);
		
		toggleApp();
		closeEarnKaroApp(deviceName);
		

		/***********************************************************************************************/
		
		CashKaroUtility reopenCKApp = new CashKaroUtility(udid, port, automationName, deviceName, systemPort);
		driver = reopenCKApp.launchEarnKaroApp(driver);
		
		new HomePage(driver, testInfo).
		clickOnProfileIconForSignedUser().
		clickOnLogout();
		
		toggleApp();
		closeEarnKaroApp(deviceName);
		
		
		/***********************************************************************************************/
		/******************************* DeepLink - Join free Link *************************************/
		/***********************************************************************************************/
		
		driver = objcashKaroutility.launchChromeWebView(driver);
		
		loadURL(GetCodeSetUP.DEEPLINKURL);
		
		driver.context("NATIVE_APP");

		new DeepLinkPage(driver, testInfo).
		clickJoinFreeLink();

		new JoinFreePage(driver, testInfo);
		
		toggleApp();
		closeEarnKaroApp(deviceName);
		
		
		/***********************************************************************************************/
		/********************************** DeepLink - Sign in Link ************************************/
		/***********************************************************************************************/
		
		driver = objcashKaroutility.launchChromeWebView(driver);
		
		loadURL(GetCodeSetUP.DEEPLINKURL);
		
		driver.context("NATIVE_APP");

		new DeepLinkPage(driver, testInfo).
		clickSignInLink();

		new SignInPage(driver, testInfo);
		
		toggleApp();
		closeEarnKaroApp(deviceName);
		
		
		/***********************************************************************************************/
		/******************************* DeepLink - Forgot pwd Link ************************************/
		/***********************************************************************************************/
		
		driver = objcashKaroutility.launchChromeWebView(driver);
		
		loadURL(GetCodeSetUP.DEEPLINKURL);
		
		driver.context("NATIVE_APP");

		new DeepLinkPage(driver, testInfo).
		clickForgotPasswordLink();

		new ForgotPasswordPage(driver, testInfo);
		
		toggleApp();
		closeEarnKaroApp(deviceName);
		
		/***********************************************************************************************/
		/****************************** DeepLink - Terms & conditions Link *****************************/
		/***********************************************************************************************/
		
		driver = objcashKaroutility.launchChromeWebView(driver);
		
		loadURL(GetCodeSetUP.DEEPLINKURL);
		
		driver.context("NATIVE_APP");

		new DeepLinkPage(driver, testInfo).
		clickTermsAndConditionsLink();

		new TermsAndConditionPage(driver, testInfo);
		
		toggleApp();
		closeEarnKaroApp(deviceName);
		
		/***********************************************************************************************/
				
	}

	@Test
	public void deepLink_AppBackGround_LoggedOut() {

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
		objOtpPage.enterOTP(otpValue).clickOnVerifyOTP();

		new HomePage(driver, testInfo).
		clickOnProfileIconForSignedUser().
		clickOnLogout();
		
		/***********************************************************************************************/
		/********************************** DeepLink - Home Link ***************************************/
		/***********************************************************************************************/
		
		CashKaroUtility objcashKaroutility = new CashKaroUtility(udid, port, automationName, deviceName);
		driver = objcashKaroutility.launchChromeWebView(driver);
		
		loadURL(GetCodeSetUP.DEEPLINKURL);
		
		Set<String> contextNames = driver.getContextHandles();
		driver.context("NATIVE_APP");

		new DeepLinkPage(driver, testInfo).
		clickHomeLink();

		new SignInPage(driver, testInfo).
		enterUserName(emailAddress).
		enterPassword().clickSignInButtonForSuccess_RedirectTo_Deeplink();
		
		new HomePage(driver, testInfo).
		clickOnProfileIconForSignedUser().
		clickOnLogout_RedirectTo_SignInPage();
		
		/***********************************************************************************************/
		/********************************** DeepLink - Store Link **************************************/
		/***********************************************************************************************/
		
		driver = objcashKaroutility.launchChromeWebView(driver);
		
		loadURL(GetCodeSetUP.DEEPLINKURL);
		
		driver.context("NATIVE_APP");

		new DeepLinkPage(driver, testInfo).
		clickMakeMyTripStoreLink();

		new SignInPage(driver, testInfo).
		enterUserName(emailAddress).
		enterPassword().clickSignInButtonForSuccess_RedirectTo_Deeplink();
		
		new StorePage(driver, testInfo);
		
		backButton();
		
		new HomePage(driver, testInfo).
		clickOnProfileIconForSignedUser().
		clickOnLogout_RedirectTo_SignInPage();
		
		/***********************************************************************************************/
		/******************************** DeepLink - Category Link *************************************/
		/***********************************************************************************************/
		
		driver = objcashKaroutility.launchChromeWebView(driver);
		
		loadURL(GetCodeSetUP.DEEPLINKURL);
		
		driver.context("NATIVE_APP");

		new DeepLinkPage(driver, testInfo).
		clickAppExclusiveLink();

		new SignInPage(driver, testInfo).
		enterUserName(emailAddress).
		enterPassword().clickSignInButtonForSuccess_RedirectTo_Deeplink();
		
		new ProductCategoryPage(driver, testInfo);
		
		backButton();
		
		new HomePage(driver, testInfo).
		clickOnProfileIconForSignedUser().
		clickOnLogout_RedirectTo_SignInPage();
		
		/***********************************************************************************************/
		/******************************** DeepLink - Category Link *************************************/
		/***********************************************************************************************/
		
		driver = objcashKaroutility.launchChromeWebView(driver);
		
		loadURL(GetCodeSetUP.DEEPLINKURL);
		
		driver.context("NATIVE_APP");

		new DeepLinkPage(driver, testInfo).
		clickHighestCashbackLink();

		new SignInPage(driver, testInfo).
		enterUserName(emailAddress).
		enterPassword().clickSignInButtonForSuccess_RedirectTo_Deeplink();
		
		new ProductCategoryPage(driver, testInfo);
		
		backButton();
		
		new HomePage(driver, testInfo).
		clickOnProfileIconForSignedUser().
		clickOnLogout_RedirectTo_SignInPage();
		
		/***********************************************************************************************/
		/******************************* DeepLink - How it works Link **********************************/
		/***********************************************************************************************/
		
//		driver = objcashKaroutility.launchChromeWebView(driver);
//		
//		loadURL(GetCodeSetUP.DEEPLINKURL);
//		
//		driver.context("NATIVE_APP");
//
//		new DeepLinkPage(driver, testInfo).
//		clickHowItWorksLink();
//		
//		new HowItWorks(driver, testInfo);
//		
//		backButton();
//		
//		new HomePage(driver, testInfo).
//		clickOnProfileIconForSignedUser().
//		clickOnLogout_RedirectTo_SignInPage();
		
		/***********************************************************************************************/
		/******************************* DeepLink - Testimonials Link **********************************/
		/***********************************************************************************************/
		
		driver = objcashKaroutility.launchChromeWebView(driver);
		
		loadURL(GetCodeSetUP.DEEPLINKURL);
		
		driver.context("NATIVE_APP");

		new DeepLinkPage(driver, testInfo).
		clickTestimonialsLink();

		new SignInPage(driver, testInfo).
		enterUserName(emailAddress).
		enterPassword().clickSignInButtonForSuccess_RedirectTo_Deeplink();
		
		new TestimonialsPage(driver, testInfo);
		
		backButton();
		
		new HomePage(driver, testInfo).
		clickOnProfileIconForSignedUser().
		clickOnLogout_RedirectTo_SignInPage();
		
		/***********************************************************************************************/
		/****************************** DeepLink - Contact Us Link *************************************/
		/***********************************************************************************************/
		
		driver = objcashKaroutility.launchChromeWebView(driver);
		
		loadURL(GetCodeSetUP.DEEPLINKURL);
		
		driver.context("NATIVE_APP");

		new DeepLinkPage(driver, testInfo).
		clickContactUsLink();

		new SignInPage(driver, testInfo).
		enterUserName(emailAddress).
		enterPassword().clickSignInButtonForSuccess_RedirectTo_Deeplink();
		
		new AskAQuestionPage(driver, testInfo);
		
		backButton();
		
		new HomePage(driver, testInfo).
		clickOnProfileIconForSignedUser().
		clickOnLogout_RedirectTo_SignInPage();
		
		/***********************************************************************************************/
		/****************************** DeepLink - Terms & conditions Link *****************************/
		/***********************************************************************************************/
		
		driver = objcashKaroutility.launchChromeWebView(driver);
		
		loadURL(GetCodeSetUP.DEEPLINKURL);
		
		driver.context("NATIVE_APP");

		new DeepLinkPage(driver, testInfo).
		clickTermsAndConditionsLink();
		
		new TermsAndConditionPage(driver, testInfo);
		
		/***********************************************************************************************/
		/******************************** DeepLink - Settings Link *************************************/
		/***********************************************************************************************/
		
		driver = objcashKaroutility.launchChromeWebView(driver);
		
		loadURL(GetCodeSetUP.DEEPLINKURL);
		
		driver.context("NATIVE_APP");

		new DeepLinkPage(driver, testInfo).
		clickSettingsLink();

		new SignInPage(driver, testInfo).
		enterUserName(emailAddress).
		enterPassword().clickSignInButtonForSuccess_RedirectTo_Deeplink();
		
		new AccountSettingsPage(driver, testInfo);
		
		backButton();
		
		new HomePage(driver, testInfo).
		clickOnProfileIconForSignedUser().
		clickOnLogout_RedirectTo_SignInPage();
		
		/***********************************************************************************************/
		/******************************* DeepLink - My earnings Link ***********************************/
		/***********************************************************************************************/
		
		driver = objcashKaroutility.launchChromeWebView(driver);
		
		loadURL(GetCodeSetUP.DEEPLINKURL);
		
		driver.context("NATIVE_APP");

		new DeepLinkPage(driver, testInfo).
		clickMyEarningsLink();

		new SignInPage(driver, testInfo).
		enterUserName(emailAddress).
		enterPassword().clickSignInButtonForSuccess_RedirectTo_Deeplink();
		
		new MyEarningsPage(driver, testInfo);
		
		backButton();
		
		new HomePage(driver, testInfo).
		clickOnProfileIconForSignedUser().
		clickOnLogout_RedirectTo_SignInPage();
		
		/***********************************************************************************************/
		/****************************** DeepLink - Shared activities Link ******************************/
		/***********************************************************************************************/
		
		driver = objcashKaroutility.launchChromeWebView(driver);
		
		loadURL(GetCodeSetUP.DEEPLINKURL);
		
		driver.context("NATIVE_APP");

		new DeepLinkPage(driver, testInfo).
		clickMySharedActivitiesLink();

		new SignInPage(driver, testInfo).
		enterUserName(emailAddress).
		enterPassword().clickSignInButtonForSuccess_RedirectTo_Deeplink();
		
		new HistoryPage(driver, testInfo);
		
		backButton();
		
		new HomePage(driver, testInfo).
		clickOnProfileIconForSignedUser().
		clickOnLogout_RedirectTo_SignInPage();
		
		/***********************************************************************************************/
		/****************************** DeepLink - Make profit Link ************************************/
		/***********************************************************************************************/
		
		driver = objcashKaroutility.launchChromeWebView(driver);
		
		loadURL(GetCodeSetUP.DEEPLINKURL);
		
		driver.context("NATIVE_APP");

		new DeepLinkPage(driver, testInfo).
		clickMakeProfitLinksLink();

		new SignInPage(driver, testInfo).
		enterUserName(emailAddress).
		enterPassword().clickSignInButtonForSuccess_RedirectTo_Deeplink();
		
		new MakeProfitLink(driver, testInfo);
		
		backButton();
		
		new HomePage(driver, testInfo).
		clickOnProfileIconForSignedUser().
		clickOnLogout_RedirectTo_SignInPage();
		
		/***********************************************************************************************/
		/****************************** DeepLink - Success stories Link ********************************/
		/***********************************************************************************************/
		
		driver = objcashKaroutility.launchChromeWebView(driver);
		
		loadURL(GetCodeSetUP.DEEPLINKURL);
		
		driver.context("NATIVE_APP");

		new DeepLinkPage(driver, testInfo).
		clickSuccessStoriesLink();

		new SignInPage(driver, testInfo).
		enterUserName(emailAddress).
		enterPassword().clickSignInButtonForSuccess_RedirectTo_Deeplink();
		
		new SuccessStories(driver, testInfo);
		
		backButton();
		
		new HomePage(driver, testInfo).
		clickOnProfileIconForSignedUser().
		clickOnLogout_RedirectTo_SignInPage();
		
		/***********************************************************************************************/
		/****************************** DeepLink - Refer & earn Link ***********************************/
		/***********************************************************************************************/
		
		driver = objcashKaroutility.launchChromeWebView(driver);
		
		loadURL(GetCodeSetUP.DEEPLINKURL);
		
		driver.context("NATIVE_APP");

		new DeepLinkPage(driver, testInfo).
		clickReferEarnLink();

		new SignInPage(driver, testInfo).
		enterUserName(emailAddress).
		enterPassword().clickSignInButtonForSuccess_RedirectTo_Deeplink();
		
		new ReferAndEarnLifeTime(driver, testInfo);
		
		backButton();
		
		new HomePage(driver, testInfo).
		clickOnProfileIconForSignedUser().
		clickOnLogout_RedirectTo_SignInPage();
		
		/***********************************************************************************************/
		/************************* DeepLink - Referral network Link ************************************/
		/***********************************************************************************************/
		
		driver = objcashKaroutility.launchChromeWebView(driver);
		
		loadURL(GetCodeSetUP.DEEPLINKURL);
		
		driver.context("NATIVE_APP");

		new DeepLinkPage(driver, testInfo).
		clickMyReferralNetworkLink();

		new SignInPage(driver, testInfo).
		enterUserName(emailAddress).
		enterPassword().clickSignInButtonForSuccess_RedirectTo_Deeplink();
		
		new ReferralNetworkPage(driver, testInfo);
		
		backButton();
		
		new HomePage(driver, testInfo).
		clickOnProfileIconForSignedUser().
		clickOnLogout_RedirectTo_SignInPage();
		
		/***********************************************************************************************/
		/********************************** DeepLink - FAQ Link ****************************************/
		/***********************************************************************************************/
		
		driver = objcashKaroutility.launchChromeWebView(driver);
		
		loadURL(GetCodeSetUP.DEEPLINKURL);
		
		driver.context("NATIVE_APP");

		new DeepLinkPage(driver, testInfo).
		clickFaqLink();

		new SignInPage(driver, testInfo).
		enterUserName(emailAddress).
		enterPassword().clickSignInButtonForSuccess_RedirectTo_Deeplink();
		
		new FAQPage(driver, testInfo);
		
		backButton();
		
		new HomePage(driver, testInfo).
		clickOnProfileIconForSignedUser().
		clickOnLogout_RedirectTo_SignInPage();
		
		/***********************************************************************************************/
		/******************************* DeepLink - Join free Link *************************************/
		/***********************************************************************************************/
		
		driver = objcashKaroutility.launchChromeWebView(driver);
		
		loadURL(GetCodeSetUP.DEEPLINKURL);
		
		driver.context("NATIVE_APP");

		new DeepLinkPage(driver, testInfo).
		clickJoinFreeLink();
		
		new JoinFreePage(driver, testInfo);
		
		/***********************************************************************************************/
		/********************************** DeepLink - Sign in Link ************************************/
		/***********************************************************************************************/
		
		driver = objcashKaroutility.launchChromeWebView(driver);
		
		loadURL(GetCodeSetUP.DEEPLINKURL);
		
		driver.context("NATIVE_APP");

		new DeepLinkPage(driver, testInfo).
		clickSignInLink();
		
		new SignInPage(driver, testInfo);
		
		/***********************************************************************************************/
		/******************************* DeepLink - Forgot pwd Link ************************************/
		/***********************************************************************************************/
		
		driver = objcashKaroutility.launchChromeWebView(driver);
		
		loadURL(GetCodeSetUP.DEEPLINKURL);
		
		driver.context("NATIVE_APP");

		new DeepLinkPage(driver, testInfo).
		clickForgotPasswordLink();
		
		new ForgotPasswordPage(driver, testInfo);
		
		/***********************************************************************************************/
				
	}

	@Test
	public void deepLink_AppClosed_LoggedOut() {

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
		objOtpPage.enterOTP(otpValue).clickOnVerifyOTP();

		new HomePage(driver, testInfo).
		clickOnProfileIconForSignedUser().
		clickOnLogout();
		
		/***********************************************************************************************/
		/********************************** DeepLink - Home Link ***************************************/
		/***********************************************************************************************/
		
		CashKaroUtility objcashKaroutility = new CashKaroUtility(udid, port, automationName, deviceName);
		driver = objcashKaroutility.launchChromeWebView(driver);
		
		loadURL(GetCodeSetUP.DEEPLINKURL);
		
		Set<String> contextNames = driver.getContextHandles();
		driver.context("NATIVE_APP");

		new DeepLinkPage(driver, testInfo).
		clickHomeLink();

		new SignInPage(driver, testInfo).
		enterUserName(emailAddress).
		enterPassword().clickSignInButtonForSuccess_RedirectTo_Deeplink();
		
		new HomePage(driver, testInfo).
		clickOnProfileIconForSignedUser().
		clickOnLogout_RedirectTo_SignInPage();
		
		toggleApp();
		closeEarnKaroApp(deviceName);
		
		/***********************************************************************************************/
		/********************************** DeepLink - Store Link **************************************/
		/***********************************************************************************************/
		
		driver = objcashKaroutility.launchChromeWebView(driver);
		
		loadURL(GetCodeSetUP.DEEPLINKURL);
		
		driver.context("NATIVE_APP");

		new DeepLinkPage(driver, testInfo).
		clickMakeMyTripStoreLink();

		new SignInPage(driver, testInfo).
		enterUserName(emailAddress).
		enterPassword().clickSignInButtonForSuccess_RedirectTo_Deeplink();
		
		new StorePage(driver, testInfo);
		
		backButton();
		
		new HomePage(driver, testInfo).
		clickOnProfileIconForSignedUser().
		clickOnLogout_RedirectTo_SignInPage();
		
		toggleApp();
		closeEarnKaroApp(deviceName);
		
		/***********************************************************************************************/
		/******************************** DeepLink - Category Link *************************************/
		/***********************************************************************************************/
		
		driver = objcashKaroutility.launchChromeWebView(driver);
		
		loadURL(GetCodeSetUP.DEEPLINKURL);
		
		driver.context("NATIVE_APP");

		new DeepLinkPage(driver, testInfo).
		clickAppExclusiveLink();

		new SignInPage(driver, testInfo).
		enterUserName(emailAddress).
		enterPassword().clickSignInButtonForSuccess_RedirectTo_Deeplink();
		
		new ProductCategoryPage(driver, testInfo);
		
		backButton();
		
		new HomePage(driver, testInfo).
		clickOnProfileIconForSignedUser().
		clickOnLogout_RedirectTo_SignInPage();
		
		toggleApp();
		closeEarnKaroApp(deviceName);
		
		/***********************************************************************************************/
		/******************************** DeepLink - Category Link *************************************/
		/***********************************************************************************************/
		
		driver = objcashKaroutility.launchChromeWebView(driver);
		
		loadURL(GetCodeSetUP.DEEPLINKURL);
		
		driver.context("NATIVE_APP");

		new DeepLinkPage(driver, testInfo).
		clickHighestCashbackLink();

		new SignInPage(driver, testInfo).
		enterUserName(emailAddress).
		enterPassword().clickSignInButtonForSuccess_RedirectTo_Deeplink();
		
		new ProductCategoryPage(driver, testInfo);
		
		backButton();
		
		new HomePage(driver, testInfo).
		clickOnProfileIconForSignedUser().
		clickOnLogout_RedirectTo_SignInPage();
		
		toggleApp();
		closeEarnKaroApp(deviceName);
		
		/***********************************************************************************************/
		/******************************* DeepLink - How it works Link **********************************/
		/***********************************************************************************************/
		
//		driver = objcashKaroutility.launchChromeWebView(driver);
//		
//		loadURL(GetCodeSetUP.DEEPLINKURL);
//		
//		driver.context("NATIVE_APP");
//
//		new DeepLinkPage(driver, testInfo).
//		clickHowItWorksLink();
//		
//		new HowItWorks(driver, testInfo);
//		
//		backButton();
//		
//		new HomePage(driver, testInfo).
//		clickOnProfileIconForSignedUser().
//		clickOnLogout_RedirectTo_SignInPage();
//		
//		toggleApp();
//		closeEarnKaroApp(deviceName);
		
		/***********************************************************************************************/
		/******************************* DeepLink - Testimonials Link **********************************/
		/***********************************************************************************************/
		
		driver = objcashKaroutility.launchChromeWebView(driver);
		
		loadURL(GetCodeSetUP.DEEPLINKURL);
		
		driver.context("NATIVE_APP");

		new DeepLinkPage(driver, testInfo).
		clickTestimonialsLink();

		new SignInPage(driver, testInfo).
		enterUserName(emailAddress).
		enterPassword().clickSignInButtonForSuccess_RedirectTo_Deeplink();
		
		new TestimonialsPage(driver, testInfo);
		
		backButton();
		
		new HomePage(driver, testInfo).
		clickOnProfileIconForSignedUser().
		clickOnLogout_RedirectTo_SignInPage();
		
		toggleApp();
		closeEarnKaroApp(deviceName);
		
		/***********************************************************************************************/
		/****************************** DeepLink - Contact Us Link *************************************/
		/***********************************************************************************************/
		
		driver = objcashKaroutility.launchChromeWebView(driver);
		
		loadURL(GetCodeSetUP.DEEPLINKURL);
		
		driver.context("NATIVE_APP");

		new DeepLinkPage(driver, testInfo).
		clickContactUsLink();

		new SignInPage(driver, testInfo).
		enterUserName(emailAddress).
		enterPassword().clickSignInButtonForSuccess_RedirectTo_Deeplink();
		
		new AskAQuestionPage(driver, testInfo);
		
		backButton();
		
		new HomePage(driver, testInfo).
		clickOnProfileIconForSignedUser().
		clickOnLogout_RedirectTo_SignInPage();
		
		toggleApp();
		closeEarnKaroApp(deviceName);
		
		/***********************************************************************************************/
		/****************************** DeepLink - Terms & conditions Link *****************************/
		/***********************************************************************************************/
		
		driver = objcashKaroutility.launchChromeWebView(driver);
		
		loadURL(GetCodeSetUP.DEEPLINKURL);
		
		driver.context("NATIVE_APP");

		new DeepLinkPage(driver, testInfo).
		clickTermsAndConditionsLink();
		
		new TermsAndConditionPage(driver, testInfo);
		
		/***********************************************************************************************/
		/******************************** DeepLink - Settings Link *************************************/
		/***********************************************************************************************/
		
		driver = objcashKaroutility.launchChromeWebView(driver);
		
		loadURL(GetCodeSetUP.DEEPLINKURL);
		
		driver.context("NATIVE_APP");

		new DeepLinkPage(driver, testInfo).
		clickSettingsLink();

		new SignInPage(driver, testInfo).
		enterUserName(emailAddress).
		enterPassword().clickSignInButtonForSuccess_RedirectTo_Deeplink();
		
		new AccountSettingsPage(driver, testInfo);
		
		backButton();
		
		new HomePage(driver, testInfo).
		clickOnProfileIconForSignedUser().
		clickOnLogout_RedirectTo_SignInPage();
		
		toggleApp();
		closeEarnKaroApp(deviceName);
		
		/***********************************************************************************************/
		/******************************* DeepLink - My earnings Link ***********************************/
		/***********************************************************************************************/
		
		driver = objcashKaroutility.launchChromeWebView(driver);
		
		loadURL(GetCodeSetUP.DEEPLINKURL);
		
		driver.context("NATIVE_APP");

		new DeepLinkPage(driver, testInfo).
		clickMyEarningsLink();

		new SignInPage(driver, testInfo).
		enterUserName(emailAddress).
		enterPassword().clickSignInButtonForSuccess_RedirectTo_Deeplink();
		
		new MyEarningsPage(driver, testInfo);
		
		backButton();
		
		new HomePage(driver, testInfo).
		clickOnProfileIconForSignedUser().
		clickOnLogout_RedirectTo_SignInPage();
		
		toggleApp();
		closeEarnKaroApp(deviceName);
		
		/***********************************************************************************************/
		/****************************** DeepLink - Shared activities Link ******************************/
		/***********************************************************************************************/
		
		driver = objcashKaroutility.launchChromeWebView(driver);
		
		loadURL(GetCodeSetUP.DEEPLINKURL);
		
		driver.context("NATIVE_APP");

		new DeepLinkPage(driver, testInfo).
		clickMySharedActivitiesLink();

		new SignInPage(driver, testInfo).
		enterUserName(emailAddress).
		enterPassword().clickSignInButtonForSuccess_RedirectTo_Deeplink();
		
		new HistoryPage(driver, testInfo);
		
		backButton();
		
		new HomePage(driver, testInfo).
		clickOnProfileIconForSignedUser().
		clickOnLogout_RedirectTo_SignInPage();
		
		toggleApp();
		closeEarnKaroApp(deviceName);
		
		/***********************************************************************************************/
		/****************************** DeepLink - Make profit Link ************************************/
		/***********************************************************************************************/
		
		driver = objcashKaroutility.launchChromeWebView(driver);
		
		loadURL(GetCodeSetUP.DEEPLINKURL);
		
		driver.context("NATIVE_APP");

		new DeepLinkPage(driver, testInfo).
		clickMakeProfitLinksLink();

		new SignInPage(driver, testInfo).
		enterUserName(emailAddress).
		enterPassword().clickSignInButtonForSuccess_RedirectTo_Deeplink();
		
		new MakeProfitLink(driver, testInfo);
		
		backButton();
		
		new HomePage(driver, testInfo).
		clickOnProfileIconForSignedUser().
		clickOnLogout_RedirectTo_SignInPage();
		
		toggleApp();
		closeEarnKaroApp(deviceName);
		
		/***********************************************************************************************/
		/****************************** DeepLink - Success stories Link ********************************/
		/***********************************************************************************************/
		
		driver = objcashKaroutility.launchChromeWebView(driver);
		
		loadURL(GetCodeSetUP.DEEPLINKURL);
		
		driver.context("NATIVE_APP");

		new DeepLinkPage(driver, testInfo).
		clickSuccessStoriesLink();

		new SignInPage(driver, testInfo).
		enterUserName(emailAddress).
		enterPassword().clickSignInButtonForSuccess_RedirectTo_Deeplink();
		
		new SuccessStories(driver, testInfo);
		
		backButton();
		
		new HomePage(driver, testInfo).
		clickOnProfileIconForSignedUser().
		clickOnLogout_RedirectTo_SignInPage();
		
		toggleApp();
		closeEarnKaroApp(deviceName);
		
		/***********************************************************************************************/
		/****************************** DeepLink - Refer & earn Link ***********************************/
		/***********************************************************************************************/
		
		driver = objcashKaroutility.launchChromeWebView(driver);
		
		loadURL(GetCodeSetUP.DEEPLINKURL);
		
		driver.context("NATIVE_APP");

		new DeepLinkPage(driver, testInfo).
		clickReferEarnLink();

		new SignInPage(driver, testInfo).
		enterUserName(emailAddress).
		enterPassword().clickSignInButtonForSuccess_RedirectTo_Deeplink();
		
		new ReferAndEarnLifeTime(driver, testInfo);
		
		backButton();
		
		new HomePage(driver, testInfo).
		clickOnProfileIconForSignedUser().
		clickOnLogout_RedirectTo_SignInPage();
		
		toggleApp();
		closeEarnKaroApp(deviceName);
		
		/***********************************************************************************************/
		/************************* DeepLink - Referral network Link ************************************/
		/***********************************************************************************************/
		
		driver = objcashKaroutility.launchChromeWebView(driver);
		
		loadURL(GetCodeSetUP.DEEPLINKURL);
		
		driver.context("NATIVE_APP");

		new DeepLinkPage(driver, testInfo).
		clickMyReferralNetworkLink();

		new SignInPage(driver, testInfo).
		enterUserName(emailAddress).
		enterPassword().clickSignInButtonForSuccess_RedirectTo_Deeplink();
		
		new ReferralNetworkPage(driver, testInfo);
		
		backButton();
		
		new HomePage(driver, testInfo).
		clickOnProfileIconForSignedUser().
		clickOnLogout_RedirectTo_SignInPage();
		
		toggleApp();
		closeEarnKaroApp(deviceName);
		
		/***********************************************************************************************/
		/********************************** DeepLink - FAQ Link ****************************************/
		/***********************************************************************************************/
		
		driver = objcashKaroutility.launchChromeWebView(driver);
		
		loadURL(GetCodeSetUP.DEEPLINKURL);
		
		driver.context("NATIVE_APP");

		new DeepLinkPage(driver, testInfo).
		clickFaqLink();

		new SignInPage(driver, testInfo).
		enterUserName(emailAddress).
		enterPassword().clickSignInButtonForSuccess_RedirectTo_Deeplink();
		
		new FAQPage(driver, testInfo);
		
		backButton();
		
		new HomePage(driver, testInfo).
		clickOnProfileIconForSignedUser().
		clickOnLogout_RedirectTo_SignInPage();
		
		toggleApp();
		closeEarnKaroApp(deviceName);
		
		/***********************************************************************************************/
		/******************************* DeepLink - Join free Link *************************************/
		/***********************************************************************************************/
		
		driver = objcashKaroutility.launchChromeWebView(driver);
		
		loadURL(GetCodeSetUP.DEEPLINKURL);
		
		driver.context("NATIVE_APP");

		new DeepLinkPage(driver, testInfo).
		clickJoinFreeLink();
		
		new JoinFreePage(driver, testInfo);
		
		toggleApp();
		closeEarnKaroApp(deviceName);
		
		/***********************************************************************************************/
		/********************************** DeepLink - Sign in Link ************************************/
		/***********************************************************************************************/
		
		driver = objcashKaroutility.launchChromeWebView(driver);
		
		loadURL(GetCodeSetUP.DEEPLINKURL);
		
		driver.context("NATIVE_APP");

		new DeepLinkPage(driver, testInfo).
		clickSignInLink();
		
		new SignInPage(driver, testInfo);
		
		toggleApp();
		closeEarnKaroApp(deviceName);
		
		/***********************************************************************************************/
		/******************************* DeepLink - Forgot pwd Link ************************************/
		/***********************************************************************************************/
		
		driver = objcashKaroutility.launchChromeWebView(driver);
		
		loadURL(GetCodeSetUP.DEEPLINKURL);
		
		driver.context("NATIVE_APP");

		new DeepLinkPage(driver, testInfo).
		clickForgotPasswordLink();
		
		new ForgotPasswordPage(driver, testInfo);
		
		toggleApp();
		closeEarnKaroApp(deviceName);
		
		/***********************************************************************************************/
				
	}

	/*******************************************************************/
	/********              		Video					     ***********/
	/*******************************************************************/
	
	@Test
	public void video_HindiVideo_VideoPlayValidation() {

		new EKOnboardingPage(driver, testInfo).
		clickOnHindibutton();
		
		sleep(250000);
		
		new EKOnboardingPage(driver, testInfo);

	}
	
	@Test
	public void video_TamilVideo_VideoPlayValidation() {

		new EKOnboardingPage(driver, testInfo).
		clickOnTamilbutton();
		
		sleep(250000);
		
		new EKOnboardingPage(driver, testInfo);

	}
	
	@Test
	public void video_MalayalamVideo_VideoPlayValidation() {

		new EKOnboardingPage(driver, testInfo).
		clickOnMalayalambutton();
		
		sleep(250000);
		
		new EKOnboardingPage(driver, testInfo);

	}
	
	@Test
	public void video_BengaliVideo_VideoPlayValidation() {

		new EKOnboardingPage(driver, testInfo).
		clickOnBengalibutton();
		
		sleep(250000);
		
		new EKOnboardingPage(driver, testInfo);

	}

	@Test
	public void video_HindiVideo_CloseValidation() {

		new EKOnboardingPage(driver, testInfo).
		clickOnHindibutton().
		clickOnClosebutton();
		
		new EKOnboardingPage(driver, testInfo);
	}
	
	@Test
	public void video_TamilVideo_CloseValidation() {

		new EKOnboardingPage(driver, testInfo).
		clickOnTamilbutton().
		clickOnClosebutton();
		
		new EKOnboardingPage(driver, testInfo);

	}
	
	@Test
	public void video_MalayalamVideo_CloseValidation() {

		new EKOnboardingPage(driver, testInfo).
		clickOnMalayalambutton().
		clickOnClosebutton();
		
		new EKOnboardingPage(driver, testInfo);

	}
	
	@Test
	public void video_BengaliVideo_CloseValidation() {

		new EKOnboardingPage(driver, testInfo).
		clickOnBengalibutton().
		clickOnClosebutton();
		
		new EKOnboardingPage(driver, testInfo);

	}

	/*******************************************************************/
	/********              		StoreDetails Page				     ***********/
	/*******************************************************************/
	
	@Test
	public void validateStoreDetailsPageSeeProfitRatesLinkWhenCashbackRateInPercentage() {
		
		String appiumStoreShortDesc = "A_AppiumStore_1"; 
		String partnerName = CashKaroUtility.extractValuesFromPartnerJsonAndLoadTheVariablesForPartner("Store_One", 0, "Store_One_Name");
		String normalPrimaryCashbackValue = CashKaroUtility.extractValuesFromPartnerJsonAndLoadTheVariablesForPartner("Store_One", 0, "Store_One_Primary_Cashback_Value");
		String normalPrimaryCashbackDetails = CashKaroUtility.extractValuesFromPartnerJsonAndLoadTheVariablesForPartner("Store_One", 0, "Store_One_Primary_Cashback_Details");
		String normalSecondaryCashbackValue = CashKaroUtility.extractValuesFromPartnerJsonAndLoadTheVariablesForPartner("Store_One", 0, "Store_One_Secondary_Cashback_Value");
		String normalSecondaryCashbackDetails = CashKaroUtility.extractValuesFromPartnerJsonAndLoadTheVariablesForPartner("Store_One", 0, "Store_One_Secondary_Cashback_Details");


		//TEST STEPS :
		new EKOnboardingPage(driver, testInfo).
		clickOnSignInbutton().
		enterUserName("api26@test.com").
		enterPassword().
		clickSignInButtonForSuccess();

		new HomePage(driver, testInfo).
		clickOnSearchIcon().
		enterTextIntoTheSearchBar("appium").
		clickOnViewAll().
		scrollTillRequiredStoreShortDescIsVisible(appiumStoreShortDesc).
		clickOnRequiredStoreShortDesc(appiumStoreShortDesc).
		clickSeeProfitRates().
		validatePartnerCashbackValuePresence(normalPrimaryCashbackValue).
		validatePartnerCashbackDetailsPresence(normalPrimaryCashbackDetails).
		validatePartnerCashbackValuePresence(normalSecondaryCashbackValue).
		validatePartnerCashbackDetailsPresence(normalSecondaryCashbackDetails);

	}
	
	@Test
	public void validateStoreDetailsPageSeeProfitRatesLinkWhenCashbackRateInRupees() {
		
		String appiumStoreShortDesc = "A_AppiumStore_2"; 
		String partnerName = CashKaroUtility.extractValuesFromPartnerJsonAndLoadTheVariablesForPartner("Store_Two", 1, "Store_Two_Name");
		String normalPrimaryCashbackValue = CashKaroUtility.extractValuesFromPartnerJsonAndLoadTheVariablesForPartner("Store_Two", 1, "Store_Two_Primary_Cashback_Value");
		String normalPrimaryCashbackDetails = CashKaroUtility.extractValuesFromPartnerJsonAndLoadTheVariablesForPartner("Store_Two", 1, "Store_Two_Primary_Cashback_Details");
		String normalSecondaryCashbackValue = CashKaroUtility.extractValuesFromPartnerJsonAndLoadTheVariablesForPartner("Store_Two", 1, "Store_Two_Secondary_Cashback_Value");
		String normalSecondaryCashbackDetails = CashKaroUtility.extractValuesFromPartnerJsonAndLoadTheVariablesForPartner("Store_Two", 1, "Store_Two_Secondary_Cashback_Details");


		//TEST STEPS :
		new EKOnboardingPage(driver, testInfo).
		clickOnSignInbutton().
		enterUserName("api26@test.com").
		enterPassword().
		clickSignInButtonForSuccess();

		new HomePage(driver, testInfo).
		clickOnSearchIcon().
		enterTextIntoTheSearchBar("appium").
		clickOnViewAll().
		scrollTillRequiredStoreShortDescIsVisible(appiumStoreShortDesc).
		clickOnRequiredStoreShortDesc(appiumStoreShortDesc).
		clickSeeProfitRates().
		validatePartnerCashbackValuePresence(normalPrimaryCashbackValue).
		validatePartnerCashbackDetailsPresence(normalPrimaryCashbackDetails).
		validatePartnerCashbackValuePresence(normalSecondaryCashbackValue).
		validatePartnerCashbackDetailsPresence(normalSecondaryCashbackDetails);

	}

	@Test
	public void validateStoreDetailsPageSeeProfitRatesLinkWhenCashbackRateWithMultipleCalendarCashback() {
		
		String appiumStoreShortDesc = "A_AppiumStore_3"; 
		String partnerName = CashKaroUtility.extractValuesFromPartnerJsonAndLoadTheVariablesForPartner("Store_Three", 2, "Store_Three_Name");
		String calendarPrimaryCashbackValue = CashKaroUtility.extractValuesFromPartnerJsonAndLoadTheVariablesForPartner("Store_Three", 2, "Store_Three_Primary_Cashback_Value");
		String calendarPrimaryCashbackDetails = CashKaroUtility.extractValuesFromPartnerJsonAndLoadTheVariablesForPartner("Store_Three", 2, "Store_Three_Primary_Cashback_Details");
		String calendarSecondaryCashbackValue = CashKaroUtility.extractValuesFromPartnerJsonAndLoadTheVariablesForPartner("Store_Three", 2, "Store_Three_Secondary_Cashback_Value");
		String calendarSecondaryCashbackDetails = CashKaroUtility.extractValuesFromPartnerJsonAndLoadTheVariablesForPartner("Store_Three", 2, "Store_Three_Secondary_Cashback_Details");

		//TEST STEPS :
		new EKOnboardingPage(driver, testInfo).
		clickOnSignInbutton().
		enterUserName("api26@test.com").
		enterPassword().
		clickSignInButtonForSuccess();

		new HomePage(driver, testInfo).
		clickOnSearchIcon().
		enterTextIntoTheSearchBar("appium").
		clickOnViewAll().
		scrollTillRequiredStoreShortDescIsVisible(appiumStoreShortDesc).
		clickOnRequiredStoreShortDesc(appiumStoreShortDesc).
		clickSeeProfitRates().
		validatePartnerCashbackValuePresence(calendarPrimaryCashbackValue).
		validatePartnerCashbackDetailsPresence(calendarPrimaryCashbackDetails).
		validatePartnerCashbackValuePresence(calendarSecondaryCashbackValue).
		validatePartnerCashbackDetailsPresence(calendarSecondaryCashbackDetails);

	}

	@Test
	public void validateSeeProfitRateCanbeOpenAndClose() {
		
		String appiumStoreShortDesc = "A_AppiumStore_3"; 
		String partnerName = CashKaroUtility.extractValuesFromPartnerJsonAndLoadTheVariablesForPartner("Store_Three", 2, "Store_Three_Name");
		String calendarPrimaryCashbackValue = CashKaroUtility.extractValuesFromPartnerJsonAndLoadTheVariablesForPartner("Store_Three", 2, "Store_Three_Primary_Cashback_Value");
		String calendarPrimaryCashbackDetails = CashKaroUtility.extractValuesFromPartnerJsonAndLoadTheVariablesForPartner("Store_Three", 2, "Store_Three_Primary_Cashback_Details");
		String calendarSecondaryCashbackValue = CashKaroUtility.extractValuesFromPartnerJsonAndLoadTheVariablesForPartner("Store_Three", 2, "Store_Three_Secondary_Cashback_Value");
		String calendarSecondaryCashbackDetails = CashKaroUtility.extractValuesFromPartnerJsonAndLoadTheVariablesForPartner("Store_Three", 2, "Store_Three_Secondary_Cashback_Details");

		//TEST STEPS :
		new EKOnboardingPage(driver, testInfo).
		clickOnSignInbutton().
		enterUserName("api26@test.com").
		enterPassword().
		clickSignInButtonForSuccess();

		new HomePage(driver, testInfo).
		clickOnSearchIcon().
		enterTextIntoTheSearchBar("appium").
		clickOnViewAll().
		scrollTillRequiredStoreShortDescIsVisible(appiumStoreShortDesc).
		clickOnRequiredStoreShortDesc(appiumStoreShortDesc).
		clickSeeProfitRates().
		validatePartnerCashbackValuePresence(calendarPrimaryCashbackValue).
		clickSeeProfitRates().
		validatePartnerCashbackValueAbsence(calendarPrimaryCashbackValue);

	}

	/*******************************************************************/
	/********              		SignedProfie   page enhancements    ***********/
	/*******************************************************************/
	
	@Test
	public void validateSeePartnersAndProfitRatesFromTheProfileMenu() {

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
		clickSeePartnersAndProfitRates();

	}

	@Test
	public void validatePrivacyPolicyPage() {

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
		clickPrivacyAndPolicy().
		validateCountOfPrivacyPolicy();

	}

	@Test
	public void validateSignedProfilePageHelloUserSection() {

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo);
		String email = objCashKaroUtility.generateEmail();
		String mobNum = objCashKaroUtility.generateMobileNumber();
		String userFullName = "TestUser";

		String otpValue = 
		new EKOnboardingPage(driver, testInfo).
		clickOnSignUpButton().
		enterFullName(userFullName).
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
		validateTheUserNameChangesInProfilePage(userFullName).
		validateProfitEarnedValue("₹0.00");
		
		//249.99
		objCashKaroUtility.
		addCashbackBonus(email, getTestData(6, "TwoFortyNinePointNine"), getTestData(7, "Cashback"));
		
		objHomePage.
		clickOnHistoryIcon().
		clickOnProfileIconForSignedUser().
		validateProfitEarnedValue("₹249.99");
		
		//249.99 
		objCashKaroUtility.
		addCashbackBonus(email, getTestData(6, "FiveHundred"), getTestData(7, "Cashback"));
		
		objHomePage.
		clickOnHistoryIcon().
		clickOnProfileIconForSignedUser().
		validateProfitEarnedValue("₹750.98");
		
	}
	
	/*******************************************************************/
	/********              	No Internet Page validation  ***********/
	/*******************************************************************/
	

	//This will check the network erros in Home page, Make link page, History page and Signed in Profile page
	//@Test
	public void validateNetworkErrorsInAllFooterLinks() {


		// REPORT STEP - START
		reportStep("About to  validate the - Network Error In Home Page", "INFO");

		// TEST STEPS :
		new EKOnboardingPage(driver, testInfo).
		clickOnSignInbutton().
		enterUserName().
		enterPassword().
		clickSignInButtonForSuccess().
		validateNetworkErrorsInHomePage().
		clickOnMakeLinksIcon().
		validateNetworkErrorsInMakeLinksPage().
		clickOnHistoryIcon().
		validateNetworkErrorsInHistoryPage().
		clickOnProfileIconForSignedUser().
		validateNetworkErrorsInHistoryPage();

		reportStep("Successfully validated the - Network Error In Home Page", "INFO");

	}

	//@Test
	public void validateNeworkErrorInStoreDetailsPage() {

		reportStep("ValidateNetworkError in Store Details Page ", "INFO");
		String appiumStoreShortDesc = "A_AppiumStore_1"; 
		String partnerName = CashKaroUtility.extractValuesFromPartnerJsonAndLoadTheVariablesForPartner("Store_One", 0, "Store_One_Name");
		String normalPrimaryCashbackValue = CashKaroUtility.extractValuesFromPartnerJsonAndLoadTheVariablesForPartner("Store_One", 0, "Store_One_Primary_Cashback_Value");
		String normalPrimaryCashbackDetails = CashKaroUtility.extractValuesFromPartnerJsonAndLoadTheVariablesForPartner("Store_One", 0, "Store_One_Primary_Cashback_Details");
		String normalSecondaryCashbackValue = CashKaroUtility.extractValuesFromPartnerJsonAndLoadTheVariablesForPartner("Store_One", 0, "Store_One_Secondary_Cashback_Value");
		String normalSecondaryCashbackDetails = CashKaroUtility.extractValuesFromPartnerJsonAndLoadTheVariablesForPartner("Store_One", 0, "Store_One_Secondary_Cashback_Details");


		//TEST STEPS :
		new EKOnboardingPage(driver, testInfo).
		clickOnSignInbutton().
		enterUserName("api26@test.com").
		enterPassword().
		clickSignInButtonForSuccess();

		new HomePage(driver, testInfo).
		clickOnSearchIcon().
		enterTextIntoTheSearchBar("appium").
		clickOnViewAll().
		scrollTillRequiredStoreShortDescIsVisible(appiumStoreShortDesc).
		clickOnRequiredStoreShortDesc(appiumStoreShortDesc).
		validateNetoworkErrorsInStoreDetailsPage();

	}

	//@Test
	public void validateNeworkErrorInMyEarningsPage() {

		reportStep("ValidateNetworkError in MyEarnings Page", "INFO");

		String OtpValue	=	new EKOnboardingPage(driver,testInfo).
				clickOnSignUpButton().
				enterAllJoinFreeFieldsAndVerifyOTP();

		OTPPage objOtpPage = new OTPPage(driver, testInfo);
		objOtpPage.
		enterOTP(OtpValue).
		clickOnVerifyOTP();

		new HomePage(driver, testInfo).
		clickOnProfileIconForSignedUser().
		clickOnMyEarnings().
		validateNetoworkErrorsInMyEarningsPage();

	}

	//@Test
	public void validateNeworkErrorInStoreCategoryPage() {

		reportStep("ValidateNetworkError in Store Category Page", "INFO");
		
		new EKOnboardingPage(driver, testInfo).
		clickOnSignInbutton().
		enterUserName().
		enterPassword().
		clickSignInButtonForSuccess().
		clickOnHamburgerIcon().
		clickRetailerCategory().
		verifyRetailerCategoryMenuNavigation().
		clickAutomationSpecificCategoryInRetailerCategoryMenuUntilAllSubCategoryVisible().
		clickAutomationSpecificSubCategoryOne().
		verifyAutomationSpecificSubCategoryNavigation().
		clickPopularTab().
		validateNetworkErrorsInStoreCategoryPage();
		
	}

	//@Test
	public void validateNetworkErrorDuringLogin() {

		// REPORT STEP - START
		reportStep("About to  validate the - Network Errors During Login " , "INFO");

		// TEST STEPS :
		new EKOnboardingPage(driver, testInfo).
		clickOnSignInbutton().
		enterUserName().
		enterPassword().
		validateNetworkErrorsDuringSignIn();
		
		reportStep("Successfully validated the - Network Error In Home Page", "INFO");

	}


	
}