package com.app.ck.pages;

import java.util.List;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.app.ck.base.WrapperMethods;
import com.app.ck.utilities.CashKaroUtility;
import com.aventstack.extentreports.ExtentTest;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class AccountSettingsPage extends WrapperMethods {

	//Constructor call
	public AccountSettingsPage(AppiumDriver<MobileElement> driver, ExtentTest testInfo) {

		WebDriverWait wait = new WebDriverWait(driver, 30);

		this.driver = driver;
		this.testInfo = testInfo;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);

		reportStep("Waiting for the Account settings page ", "INFO");
		try {
			
			wait.until(ExpectedConditions.visibilityOf(AccountSettings));
			reportStep("Successfully landed on the Account Settings  page ", "PASS");

		}catch(Exception e) {

			reportStep(e.getMessage(), "INFO");
			reportStep("Not able to land on the Account SettingsPage  ", "FAIL");
		}

	}
	
	//Common
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text  = 'Account Settings']")
	MobileElement AccountSettings ;
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[contains(@text ,'Personal')and contains(@text ,'Details')]")
	MobileElement personalDetailsTab ;
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[contains(@text ,'Change')and contains(@text ,'Password')]")
	MobileElement changePasswordtab ;
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[contains(@text ,'Payment')and contains(@text ,'Settings')]")
	MobileElement paymentSettings ;
	
	
	//Personal Details 
	@FindBy(how = How.XPATH, using = "//android.widget.EditText[@content-desc=\"et_personalDetails_fullName\"]")
	MobileElement personalDetailsFullName ;
	@FindBy(how = How.XPATH, using = "//android.widget.EditText[@content-desc=\"et_personalDetails_emailAddress\"]")
	MobileElement personalDetailsEmailAddress ;
	@FindBy(how = How.XPATH, using = "//android.widget.EditText[@content-desc=\"et_personalDetails_mobileNumber\"]")
	MobileElement personalDetailsMobileNumber ;
	@FindBy(how = How.XPATH, using = "//*[@text='Receive our weekly offers newsletter']")
	MobileElement receiveOurWeeklyOffersNewsLetter ;
	@FindBy(how = How.XPATH, using = "//*[@text='Receive email when I get referral earning']")
	MobileElement receiveEmailWhenIGetReferralEarnings ;
	@FindBy(how = How.XPATH, using = "//*[@text='Please enter any details ']")
	MobileElement pleaseEnterAnyDetails ;
	@FindBy(how = How.XPATH, using = "//*[@text='Profile updated ']")
	MobileElement profileUpdated ;
	@FindBy(how = How.XPATH, using = "//*[@text='New Email Address']")
	MobileElement newEmailAddress_PlaceHoler ;
	@FindBy(how = How.XPATH, using = "//*[@text='Confirm Email Address']")
	MobileElement confirmEmailAddress_PlaceHolder ;
	@FindBy(how = How.XPATH, using = "//*[@text='Mobile Number']")
	MobileElement mobileNumber ;
	@FindBy(how = How.XPATH, using = "//*[@text='Email Address']")
	MobileElement emailAddress ;
	@FindBy(how = How.XPATH, using = "//*[@text='Full Name']")
	MobileElement fullName ;
	@FindBy(how = How.XPATH, using = "//android.widget.Switch[@content-desc=\"switch_personalDetails_newsletter\"]")
	MobileElement receiveourWeeklyRadioButton ;
	@FindBy(how = How.XPATH, using = "	//android.widget.Switch[@content-desc=\"switch_personalDetails_referral\"]")
	MobileElement receiveEmailWhenIGetReferralRadioButton ;
	@FindBy(how = How.XPATH, using = "//*[@text='SAVE CHANGES']")
	MobileElement saveChangesButton ;
	@FindBy(how = How.XPATH, using = "//*[@text='Please enter a valid Email ID']")
	MobileElement pleaseEnterAValidEmailId ;
	
	//Error validations
	@FindBy(how = How.XPATH, using = "//*[@text='Please enter a valid Email ID']")
	List<MobileElement> listOfPleaseEnterAValidEmailId ;
	@FindBy(how = How.XPATH, using = "//*[@text='E-Mail Address Does Not Match']")
	MobileElement eMailAddressDoedNotMatch ;
	@FindBy(how = How.XPATH, using = "//*[@text='Please enter minimum 2 characters long']")
	MobileElement min2CharLongError ;
	@FindBy(how = How.XPATH, using = "//*[contains(@text,'The fullname field is required')]")
	MobileElement fullNameFieldIsRequired ;
	@FindBy(how = How.XPATH, using = "//*[@text='Please enter minimum 2 characters long']")
	List <MobileElement> listOfMin2CharLongError ;
	@FindBy(how = How.XPATH, using = "//*[@text='Please enter maximum 50 characters long']")
	MobileElement max50CharLongError ;
	@FindBy(how = How.XPATH, using = "//*[@text='Please enter maximum 50 characters long']")
	List <MobileElement> listOfMax50CharLongError ;
	@FindBy(how = How.XPATH, using = "//*[@text='An account with this Email ID already exists!']")
	MobileElement accountAlreadyExists ;
	
	//Change password page

	@FindBy(how = How.XPATH, using = "//*[@text='Current Password']")
	MobileElement currentPassword;
	@FindBy(how = How.XPATH, using = "//*[@text='Please Enter The Current Password']")
	MobileElement pleaseEnterTheCurrentPassword;
	@FindBy(how = How.XPATH, using = "//*[@text='New Password']")
	MobileElement newPassword;
	@FindBy(how = How.XPATH, using = "//*[@text='Please Enter The New Password']")
	MobileElement pleaseEnterTheNewPassword;
	@FindBy(how = How.XPATH, using = "//*[@text='Confirm Password']")
	MobileElement confirmPassword;
	@FindBy(how = How.XPATH, using = "//*[@text='Please Enter The Confirm Password']")
	MobileElement pleaseEnterTheConfirmPassword;
	@FindBy(how = How.XPATH, using = "//*[@text='Password must be at least 6 characters long']")
	List<MobileElement> passwordMustBeAtLeast6CharError;
	@FindBy(how = How.XPATH, using = "//*[@text='Password must be less than 20 characters long']")
	List<MobileElement> passwordMustBeLessThan20Char;
	@FindBy(how = How.XPATH, using = "//*[@text='Password does not match']")
	MobileElement passwordDoesNotMatch;
	@FindBy(how = How.XPATH, using = "//*[contains(@text ,'Your password does not match. Please check and try again')]")
	MobileElement passwordDoesNotMatchCheckAndTryAgain;
	@FindBy(how = How.XPATH, using = "//*[contains(@text ,'New password updated')]")
	MobileElement newPasswordUpdated;
	@FindBy(how = How.XPATH, using = "//android.widget.EditText[@content-desc=\"et_changePassword_currentPassword\"]")
	MobileElement currentPasswordField;
	@FindBy(how = How.XPATH, using = "//android.widget.EditText[@content-desc=\"et_changePassword_fullName\"]")
	MobileElement newPasswordField;
	@FindBy(how = How.XPATH, using = "//android.widget.EditText[@content-desc=\"et_changePassword_confirmPassword\"]")
	MobileElement confirmPasswordField;
	
	//Payment Settings :
	
	@FindBy(how = How.XPATH, using = "(//android.widget.EditText)[1]")
	MobileElement paymentMethodDropDown ;
	@FindBy(how = How.XPATH, using = "//*[contains(@text,'Payment settings updated')]")
	MobileElement paymentSettingUpdatedText ;
	
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='IFSC Code should be exactly 11 digits']")
	MobileElement ifscCodeShouldBeExactly11digits ;
	
	
	//NEFT Method validations :
	
	//NEFT
	@FindBy(how = How.XPATH, using = "//*[@text='Bank Payment (NEFT)']")
	MobileElement selectBankPaymentNEFT ;
	@FindBy(how = How.XPATH, using = "//android.widget.EditText[@content-desc=\"et_settingsNeft_accountHolder\"]")
	MobileElement accountHolder; 
	@FindBy(how = How.XPATH, using = "//android.widget.EditText[@content-desc=\"et_settingsNeft_branchName\"]")
	MobileElement bankBranchName; 
	@FindBy(how = How.XPATH, using = "//android.widget.EditText[@content-desc=\"et_settingsNeft_bankName\"]")
	MobileElement bankName; 
	@FindBy(how = How.XPATH, using = "//android.widget.EditText[@content-desc=\"et_settingsNeft_accNumber\"]")
	MobileElement bankAccountNumber; 
	@FindBy(how = How.XPATH, using = "//android.widget.EditText[@content-desc=\"et_settingsNeft_ifscCode\"]")
	MobileElement bankIFSCCode; 
	@FindBy(how = How.XPATH, using = "//android.widget.EditText[@content-desc=\"et_settingsNeft_password\"]")
	MobileElement passwordField_Confirmation;

	
	@FindBy(how = How.XPATH, using = "//*[contains(@text,'Name of Bank Account Holder')]/following-sibling::*[contains(@text,'CashKaroUser')]")
	MobileElement acountHolderNameAfterSet;
	@FindBy(how = How.XPATH, using = "//*[contains(@text,'Bank Branch Name')]/following-sibling::*[contains(@text,'CashKaroBranch')]")
	MobileElement branchNameAfterSet;
	@FindBy(how = How.XPATH, using = "//*[contains(@text,'Bank Account Number')]/following-sibling::*[contains(@text,'*******89')]")
	MobileElement accountNumberAfterSet;
	@FindBy(how = How.XPATH, using = "//*[contains(@text,'IFSC Code for Bank')]/following-sibling::*[contains(@text,'CORP0001206')]")
	MobileElement ifscAfterSet;
	@FindBy(how = How.XPATH, using = "//*[contains(@text,'Bank Name')]/following-sibling::*[contains(@text,'PouringPounds')]")
	MobileElement bankNameAfterSet;
	

	
	//NEFT negative validations :
	
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='Name of Bank Account Holder']")
	MobileElement nameOfBankAccountHolderName;
	
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='Please enter valid Account Holder Name']")
	MobileElement pleaseEnterValidACHolderName;
	
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='Bank Name']")
	MobileElement bank_Name;
	
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='Please enter valid Bank Name']")
	MobileElement pleaseEnterValidBankName;
	
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='Bank Branch Name']")
	MobileElement bank_BranchName;
	
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='Please enter Branch Name or 4 digit branch code']")
	MobileElement pleaseEnterBranchNameOr4DigitBranchCode;
	
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='Bank Account Number']")
	MobileElement bank_AccountNumber;
	
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='Please enter the Bank Account Number']")
	MobileElement pleaseEnterTheBankAccountNum;
	
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='IFSC Code for Bank']")
	MobileElement ifscCodeForBank;
	
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='Please enter the IFSC Code']")
	MobileElement pleaseEnterIFSCCode;
	
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='Please enter correct Bank Account number.']")
	MobileElement pleaseEnterCorrectBankAccountNumber;

	@FindBy(how = How.XPATH, using = "//android.widget.TextView[contains(@text,'Invalid IFSC Code')]")
	MobileElement invalidIfsc;
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[contains(@text,'Invalid IFSC Code')]")
	MobileElement ifscCodeFormatIsInvalid;
	
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[contains(@text,'Invalid Bank Branch')]")
	MobileElement invalidBankBranch;
	
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[contains(@text,'Please enter Branch Name or 4 digit branch code')]")
	MobileElement leaseEnterBranchNameOR4DigitBranchCode;
	
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[contains(@text,'Please enter the Password')]")
	MobileElement pleaseEnterPwd;
	
	@FindBy(how = How.XPATH, using = "//android.widget.EditText[@content-desc=\"et_settingsNeft_password\"]")
	MobileElement enterPasswordToConfirm;
	@FindBy(how = How.XPATH, using = "//android.widget.EditText[@content-desc=\"et_settingsAmazon_password\"]")
	MobileElement enterPasswordToConfirm_Amazon;
	@FindBy(how = How.XPATH, using = "//android.widget.EditText[@content-desc=\"et_settingsFlipkart_password\"]")
	MobileElement enterPasswordToConfirm_FlipKart;
	
	//Amazon GiftCard :
	@FindBy(how = How.XPATH, using = "//android.widget.EditText[@content-desc=\"et_settingsAmazon_email\"]")
	MobileElement enterEmailForAmazonGC;
	
	@FindBy(how = How.XPATH, using = "//*[contains(@text,'Amazon Gift Card')]")
	MobileElement selectAmazonGiftCard ;
	
	@FindBy(how = How.XPATH, using = "//*[contains(@text,'E-Mail')]/following-sibling::*[contains(@text,'cktest@test.com')]")
	MobileElement giftCardEmailAfterSet;
	
	//FlipKart GiftCard :
	@FindBy(how = How.XPATH, using = "//android.widget.EditText[@content-desc=\"et_settingsFlipkart_email\"]")
	MobileElement enterEmailForFlipKartGC;
	
	@FindBy(how = How.XPATH, using = "//*[contains(@text,'Flipkart Gift Card')]")
	MobileElement selectFlipKartGiftCard ;
	
	//Paytm Wallet :
	
	@FindBy(how = How.XPATH, using = "//android.widget.EditText[@content-desc=\"et_settingsPaytm_mobile\"]")
	MobileElement paytmMobileNumberField;
	
	@FindBy(how = How.XPATH, using = "//android.widget.EditText[@content-desc=\"et_settingsPaytm_password\"]")
	MobileElement enterPasswordToConfirm_Paytm;
	
	@FindBy(how = How.XPATH, using = "//*[contains(@text,'Paytm Wallet')]")
	MobileElement selectPaytmWallet;
	
	//Amazon error validation :
	
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='Please enter the Email ID']")
	MobileElement pleaseEnterEmail;
	
		@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='E-Mail']")
	MobileElement enterEmailIDtoAmazonGC;
	
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='Please enter a valid Email ID']")
	MobileElement pleaseEnterValidEmail;
	
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='Please enter maximum 50 characters long']")
	MobileElement pleaseEnterMax50CharsLong;
	
	//====================================================================================  Methods =======================================================================================================
	
	//Personal details page methods :
	
	public AccountSettingsPage validateFullName(String expected) {
		
		reportStep("About to validate the Account settings - Personal detais page Full name - Auto populated value ", "INFO");
		
		String actual = getText(personalDetailsFullName);
		
		validateTheActualValueContainsExpectedValue(actual, expected);
		
		return this;
		
	}
	
	public AccountSettingsPage enterFullName(String value) {
		
		scrollFromUpToDowninAppFromTheMiddleOfTheScreen();
		
		reportStep("About to enter the Full name in the Personal details page ", "INFO");
		
		if(enterText(personalDetailsFullName,value )) {
			
			reportStep("About to enter the User Full Name as : " + value , "PASS");
			
		}else {
			
			reportStep("Failed to enter the User Full Name as : "+ value , "FAIL");
		}
		
		return this;
		
	}
	
	public AccountSettingsPage validateEmailAddress(String expected) {
		
		reportStep("About to validate the Account settings - Personal detais page Email Adress - Auto populated value ", "INFO");
		
		String actual = getText(personalDetailsEmailAddress);
		
		validateTheActualValueContainsExpectedValue(actual, expected);
		
		return this;
		
	}
	
	public AccountSettingsPage validateMobileNumber(String expected) {
		
		reportStep("About to validate the Account settings - Personal detais page Mobile Number - Auto populated value ", "INFO");
		
		String actual = getText(personalDetailsMobileNumber);
		
		validateTheActualValueContainsExpectedValue(actual, expected);
		
		return this;
		
	}

	public PersonalDetailsIntermediatePage clickOnEditButton() {
		
		String xpath = "//android.widget.EditText[@content-desc='et_personalDetails_mobileNumber']";
		String expectedXpath = "//android.widget.EditText[@content-desc='et_new_mobile_number']";
		
		reportStep("About to click on The Edit button to Change the Mobile Number of the user Account ", "INFO");
		
		clickMultipleXCoordinatesByXpath(xpath, 40, 500, expectedXpath);
		
		return new PersonalDetailsIntermediatePage(driver, testInfo);
		
	}

	public AccountSettingsPage clickOnReceivWeeklyoffersNewsLetterRadioButton() {
		
		scrollFromDownToUpinApp();
		
		reportStep("About to click on the Receive our Weekly offers newsletter Radio Button ", "INFO");
		
		if (click(receiveourWeeklyRadioButton)) {
			
			reportStep("Suceesfully clicked on the Receive our Weekly offers newsletter Radio Button ", "PASS");
			
		}else {
			
			reportStep("Failed to  click on the Receive our Weekly offers newsletter Radio Button ", "FAIL");
		}
		
		return this;
	}
	
	public AccountSettingsPage clickOnReceiveEmailWhenIGetReferralRadioButton() {
		
		scrollFromDownToUpinApp();

		reportStep("About to click on the Receive email when I get referral earning Radio button  ", "INFO");

		if (click(receiveEmailWhenIGetReferralRadioButton)) {
			
			reportStep("Suceesfully clicked on the Receive email when I get referral earning Radio button", "PASS");

		}else {

			reportStep("Failed to  click on the Receive email when I get referral earning Radio button", "FAIL");
			
		}

		return this;
	}

	public AccountSettingsPage clickOnSaveChangesButton() {
		
		reportStep("About to click on the Save Changes button at the Personal details page ", "INFO");
		
		if (click(saveChangesButton)) {
			
			reportStep("Successfully clicked on the save changes button at the Personal details page ", "PASS");
			
		}else {
			
			reportStep("Failed to click on the save changes button at the Personal details page ", "FAIL");
		}
		
		return this;
	}

	public AccountSettingsPage validateProfileUpdatedSuccessMessage() {
		
		reportStep("About to validate the Profile Updated Success Message ", "INFO");
		validateTheElementPresence(profileUpdated);
		
		return this;
	}

	public AccountSettingsPage validatePleaseEnterDetailsError() {
		
		reportStep("About to validate the Please Enter the Details error in the Personal details page  ", "INFO");
		validateTheElementPresence(pleaseEnterAnyDetails);
		
		return this;
	}

	public AccountSettingsPage validateAccountAlreadyExistsError() {
		
		reportStep("About to validate the Account already exists error ", "INFO");
		
		validateTheElementPresence(accountAlreadyExists);
		
		return this;
	}

	public AccountSettingsPage validateMin2CharError(int expected) {
		
		reportStep("About to validate the Minimu 2 Characters error", "INFO");
		
		int actual = listOfMin2CharLongError.size();
		
		validateTheActualValueWithExpectedValue(actual, expected);
		
		return this;
	}

	public AccountSettingsPage validateMax50CharError(int expected) {
		
		reportStep("About to validate Max 50 Character error ", "INFO");
		
		int actual = listOfMax50CharLongError.size();
		
		validateTheActualValueWithExpectedValue(actual, expected);
		
		return this;
	}
	
	public AccountSettingsPage validateEmailDoesnotMatchError() {
		
		reportStep("About to validate the Email does not match error at the Personal details page ", "INFO");
		
		validateTheElementPresence(eMailAddressDoedNotMatch);
		
		return this;
		
		
	}
	
	public AccountSettingsPage validiatePleaseEnterValidEmailIDError(int expected) {
		
		reportStep("About to validate the Please Enter valid Email ID Error in New Email And Confrim Email Address fields ", "INFO");
		
		int actual = listOfPleaseEnterAValidEmailId.size();

		validateTheActualValueWithExpectedValue(actual, expected);
		
		return this;
		
	}
	
	public AccountSettingsPage validateFullNameFieldIsRequired() {
		
		reportStep("Entering only spaces to the user full name filed and validate the error message saying that Full name Field is Required ", "INFO");
		
		validateTheElementPresence(fullNameFieldIsRequired);
		
		return this;
		
	}
	 
	public SignedInProfilePage clickOnBackButton() {
		
		reportStep("About to click on the back button at the Personal details ,It should navigate to the Profile page", "INFO");
		
//		if(click(backButton)) {

//			reportStep("Successfully clicked on the back button the ", "PASS");
//			
//		}else {
//			
//			reportStep("Failed to  click on the back button the ", "FAIL");
//		}
		
		driver.navigate().back();
		
		return new SignedInProfilePage(driver, testInfo);
	}
	
	//Change Password page methods : 

	public AccountSettingsPage clickOnChangePasswordTab() {

		reportStep("About to click on the Change Password Tab ", "INFO");

		click(changePasswordtab);

		if (validateTheElementPresence(currentPasswordField)) {

			reportStep("Successfully clicked on the Change Password Field ", "PASS");
		}else {

			reportStep("Failed to click on the Change Password Field ", "FAIL");
		}

		return this;

	}

	public AccountSettingsPage enterCurrentPassword(String currentPassword) {

		reportStep("About to enter the current password into the current password field As : "+  currentPassword, "INFO");

		if(enterText(currentPasswordField, currentPassword)) {

			reportStep("Successfully entered the Current password as  : " + currentPassword, "PASS");
		}else {

			reportStep("Failed to enter the Current password as  : " + currentPassword, "FAIL");
		}

		return this;
	}

	public AccountSettingsPage enterNewPassword(String newPassword) {

		reportStep("About to enter the New password into the current password field As : "+  newPassword, "INFO");

		if(enterText(newPasswordField, newPassword)) {

			reportStep("Successfully entered the New password as  : " + newPassword, "PASS");
		}else {

			reportStep("Failed to enter the New password as  : " + newPassword, "FAIL");
		}

		return this;
	}

	public AccountSettingsPage enterConfirmPassword(String confirmPassword) {

		reportStep("About to enter the Confirm Password into the Confirm password field As : "+  confirmPassword, "INFO");

		if(enterText(confirmPasswordField, confirmPassword)) {

			reportStep("Successfully entered the Confirm password as  : " + confirmPassword, "PASS");
		}else {

			reportStep("Failed to enter the Confirm password as  : " + confirmPassword, "FAIL");
		}

		return this;
	}

	public AccountSettingsPage validatePleaseEnterCurrentPassword() {
		
		reportStep("About to validate the Please enter current Password Error ", "INFO");
		
		validateTheElementPresence(currentPassword);
		validateTheElementPresence(pleaseEnterTheCurrentPassword);
		
		return this;
	}

	public AccountSettingsPage validiatePleaseEnterNewPassword() {

		reportStep("About to validate the Please enter New Password Error ", "INFO");

		validateTheElementPresence(newPassword);
		validateTheElementPresence(pleaseEnterTheNewPassword);

		return this;
	}

	public AccountSettingsPage validatePleaseEnterConfirmPassword() {

		reportStep("About to validate the Please enter Confirm Password error ", "INFO");

		validateTheElementPresence(confirmPassword);
		validateTheElementPresence(pleaseEnterTheConfirmPassword);

		return this;
	}

	public AccountSettingsPage validatePasswordMustBeAtleast6CharError(int expected) {

		reportStep("About to validate the Password field error once after entering the less than 6 characters into the Current/New/Confrim Password fields", "INFO");

		int actual = getListOfElementsSize(passwordMustBeAtLeast6CharError);

		validateTheActualValueWithExpectedValue(actual, expected);

		return this;

	}

	public AccountSettingsPage validatePasswordMustBeLessThan20Chars(int expected) {

		reportStep("About to validate the Password field error once after entering more than 20 Max -  characters into the Current/New/Confrim Password fields", "INFO");

		int actual = getListOfElementsSize(passwordMustBeLessThan20Char);

		validateTheActualValueWithExpectedValue(actual, expected);

		return this;

	}
	
	public AccountSettingsPage validiatePasswordDoesNotMatchError() {
		
		reportStep("About to validate the Password Does not match error ", "INFO");
		
		validateTheElementPresence(passwordDoesNotMatch);
		
		return this;
		
	}

	public AccountSettingsPage validatePasswordDoesNotMatchTryAgain() {
		
		reportStep("About to validate the Password does not match and check and Try again error message - Once after entering the wrong current Password ", "INFO");
		
		validateTheElementPresence(passwordDoesNotMatchCheckAndTryAgain);
		
		return this;
		
		
	}

	public AccountSettingsPage validateNewPasswordUpdatedSuccessMessage() {
		
		reportStep("About to validate the New Password updated - Sucess Message ", "INFO");
		
		validateTheElementPresence(newPasswordUpdated);
		
		return this;
	}


	//Payment Settings ;
	
	public AccountSettingsPage clickOnPaymentSettings() {
		
		reportStep("About to click on the Payment Settings Page -", "INFO");
		
		if(click(paymentSettings)) {
			
			reportStep("Successfully clicked on the Payment settings Tab", "PASS");
		}else {
			
			reportStep("Failed to click on the Payment Settings Tab ", "FAIL");
		}
		
		return this;
		
		
	}
	
	public AccountSettingsPage clickonPaymentMethodDropDown() {
		
		reportStep("About to click on the Payment method drop down in Payment settings screen ", "INFO");
		
		if (click(paymentMethodDropDown)) {
			
			reportStep("About to click on the Payment Method drop down from the Payment settings page ", "PASS");
		}else {
			
			reportStep("Failed to click on the Payment method drop down from the Payment Settings page ", "FAIL");
		}
		
		return this;
	}

	public AccountSettingsPage validatePaymentSettingsUpdated() {
		
		reportStep("About to validate the Payment Setting updated - Success Message ", "INFO");
		
		validateTheElementPresence(paymentSettingUpdatedText);
		
		return this;
	}
	
	public AccountSettingsPage validateNEFTPaymentSettings() {
		
		reportStep("About to validate the Payment Settings - After Set ", "INFO");
		
		validateTheElementPresence(acountHolderNameAfterSet);
		validateTheElementPresence(bankNameAfterSet);
		validateTheElementPresence(branchNameAfterSet);
		validateTheElementPresence(accountNumberAfterSet);
		validateTheElementPresence(ifscAfterSet);
	
		return this;
		
	}
	
	//NEFT Positive validations :

	public AccountSettingsPage enterNEFTAccountHolderName() {

		String NEFTbankAccountHolder = getTestData(6, "NEFTAccountHolderName");

		reportStep("About to enter  the Account holder name ", "INFO");

		if(enterText(accountHolder, NEFTbankAccountHolder)) {

			reportStep("Successfully enter the  "+ NEFTbankAccountHolder + " as bank holder name " , "PASS");
		}else {

			reportStep("Failed to  enter the  "+ NEFTbankAccountHolder + " as bank holder name " , "FAIL");
		}
		
		return this;
	}
	
	public AccountSettingsPage enterNEFTAccountHolderName(String NEFTbankAccountHolder) {

		

		reportStep("About to enter  the Account holder name ", "INFO");

		if(enterText(accountHolder, NEFTbankAccountHolder)) {

			reportStep("Successfully enter the  "+ NEFTbankAccountHolder + " as bank holder name " , "PASS");
		}else {

			reportStep("Failed to  enter the  "+ NEFTbankAccountHolder + " as bank holder name " , "FAIL");
		}
		
		return this;
	}
	
	public AccountSettingsPage enterNEFTBankName() {

		String NEFTBankName = getTestData(6, "NEFTBankName");

		reportStep("About to enter  the Account holder name ", "INFO");

		if(enterText(bankName, NEFTBankName)) {

			reportStep("Successfully enter the  "+ NEFTBankName + " as bank name " , "PASS");
		}else {

			reportStep("Failed to  enter the  "+ NEFTBankName + " as bank  name " , "FAIL");
		}
		
		return this;
	}
	
	public AccountSettingsPage enterNEFTBankName(String NEFTBankName ) {


		reportStep("About to enter  the Account holder name ", "INFO");

		if(enterText(bankName, NEFTBankName)) {

			reportStep("Successfully enter the  "+ NEFTBankName + " as bank name " , "PASS");
		}else {

			reportStep("Failed to  enter the  "+ NEFTBankName + " as bank  name " , "FAIL");
		}
		
		return this;
	}
	
	public AccountSettingsPage enterNEFTBranchName() {

		String NEFTBranchName = getTestData(6, "NEFTBranchName");

		reportStep("About to enter  the BranchName ", "INFO");

		if(enterText(bankBranchName, NEFTBranchName)) {

			reportStep("Successfully enter the  "+ NEFTBranchName + " as BranchName " , "PASS");
		}else {

			reportStep("Failed to  enter the  "+ NEFTBranchName + " as BranchName " , "FAIL");
		}
		
		return this;
	}
	
	public AccountSettingsPage enterNEFTBranchName(String NEFTBranchName) {

		

		reportStep("About to enter  the BranchName ", "INFO");

		if(enterText(bankBranchName, NEFTBranchName)) {

			reportStep("Successfully enter the  "+ NEFTBranchName + " as BranchName " , "PASS");
		}else {

			reportStep("Failed to  enter the  "+ NEFTBranchName + " as BranchName " , "FAIL");
		}
		
		return this;
	}
	
	public AccountSettingsPage enterNEFTAccountNumber() {

		String NEFTAccountNumber = getTestData(6, "NEFTAccountNumber");

		reportStep("About to enter  the NEFT acccount number  ", "INFO");

		if(enterText(bankAccountNumber, NEFTAccountNumber)) {

			reportStep("Successfully enter the  "+ NEFTAccountNumber + " as  NEFT acccount number " , "PASS");
		}else {

			reportStep("Failed to  enter the  "+ NEFTAccountNumber + " as  NEFT acccount number " , "FAIL");
		}
		
		return this;
		
	}
	
	public AccountSettingsPage selectBankPaymentNEFTFromDropDown() {
		
		reportStep("About to select the BankPaymentNEFT option from the dropdown ", "INFO");
		
		if(click(selectBankPaymentNEFT)) {
			
			reportStep("Successfully selected the BankPayment NEFT from the payment method drop down ", "PASS");
			
		}else {
			
			reportStep("Failed to select the BankPayment NEFT from the payment method drop down  ", "FAIL");
		}
		
		return this;
		
	}
	
	public AccountSettingsPage enterNEFTAccountNumber(String NEFTAccountNumber ) {


		reportStep("About to enter  the NEFT acccount number  ", "INFO");

		if(enterText(bankAccountNumber, NEFTAccountNumber)) {

			reportStep("Successfully enter the  "+ NEFTAccountNumber + " as  NEFT acccount number " , "PASS");
		}else {

			reportStep("Failed to  enter the  "+ NEFTAccountNumber + " as  NEFT acccount number " , "FAIL");
		}
		
		return this;
		
	}
	
	public AccountSettingsPage enterNEFT_IFSC() {

		String NEFTAccountIFSC = getTestData(6, "NEFTAccountIFSC");

		reportStep("About to enter  the NEFT acccount number  ", "INFO");

		if(enterText(bankIFSCCode, NEFTAccountIFSC)) {

			reportStep("Successfully enter the  "+ NEFTAccountIFSC + " as  NEFT acccount number " , "PASS");
		}else {

			reportStep("Failed to  enter the  "+ NEFTAccountIFSC + " as  NEFT acccount number " , "FAIL");
		}
		
		return this;
		
	}
	
	public AccountSettingsPage enterNEFT_IFSC(String NEFTAccountIFSC) {

		reportStep("About to enter  the NEFT acccount number : NEFT IFSC is -  "+ NEFTAccountIFSC, "INFO");

		if(enterText(bankIFSCCode, NEFTAccountIFSC)) {

			reportStep("Successfully enter the  "+ NEFTAccountIFSC + " as  NEFT acccount number " , "PASS");
		}else {

			reportStep("Failed to  enter the  "+ NEFTAccountIFSC + " as  NEFT acccount number " , "FAIL");
		}
		
		return this;
		
	}

	public AccountSettingsPage enterPasswordToConfirm_NEFT() {
		
		//scrollFromDownToUpinApp_FourSecDuration();

		reportStep("About to Enter the Password to confirm the Payment settings Changes ", "INFO");

		if (enterText(enterPasswordToConfirm, testdata.get(3).get("Password"))) {

			reportStep("Successfully entered the Valid Password  as : " + testdata.get(3).get("Password"), "PASS");
		}else {

			reportStep("Failed to enter the valid Password as : " + testdata.get(3).get("Password") , "FAIL");
		}

		return this;

	}

	public AccountSettingsPage enterPasswordToConfirm_Amazon() {

		reportStep("About to Enter the Password to confirm the Payment settings Changes For Amzon Payment Settings", "INFO");

		if (enterText(enterPasswordToConfirm_Amazon, testdata.get(3).get("Password"))) {

			reportStep("Successfully entered the Valid Password  as : " + testdata.get(3).get("Password"), "PASS");
		}else {

			reportStep("Failed to enter the valid Password as : " + testdata.get(3).get("Password") , "FAIL");
		}

		return this;

	}

	public AccountSettingsPage enterPasswordToConfirm_FlipKart() {

		reportStep("About to Enter the Password to confirm the Payment settings Changes , For FlipKart Payment Settings ", "INFO");

		if (enterText(enterPasswordToConfirm_FlipKart, testdata.get(3).get("Password"))) {

			reportStep("Successfully entered the Valid Password  as : " + testdata.get(3).get("Password"), "PASS");
		}else {

			reportStep("Failed to enter the valid Password as : " + testdata.get(3).get("Password") , "FAIL");
		}

		return this;

	}
	
	public AccountSettingsPage enterPasswordToConfirm_PaytmWallet() {

		reportStep("About to Enter the Password to confirm the Payment settings Changes , For Paytm Wallet ", "INFO");

		if (enterText(enterPasswordToConfirm_Paytm, testdata.get(3).get("Password"))) {

			reportStep("Successfully entered the Valid Password  as : " + testdata.get(3).get("Password"), "PASS");
		}else {

			reportStep("Failed to enter the valid Password as : " + testdata.get(3).get("Password") , "FAIL");
		}

		return this;

	}
	
	public AccountSettingsPage enterBankNEFTPaymentDetails() {

		reportStep("About to enter the Account Holder name as  : ", "INFO");

		AccountSettingsPage accountSettings = enterNEFTAccountHolderName().
		enterNEFTBankName().
		enterNEFTBranchName().
		enterNEFTAccountNumber();
		scrollFromDownToUpinApp_FourSecDuration();
		
		try {
				clickOnSaveChangesButton();

		}catch(Exception e) {
			
			reportStep("Failed to Hide the keyboard", "INFO");
			
		}
		
		accountSettings.enterNEFT_IFSC().
		enterPasswordToConfirm_NEFT();

		return this;

	}

	//Amazon GiftCard :
	
	public AccountSettingsPage enterAmazonGiftCardEmailID() {

		reportStep("About to enter the Email id for the Amazon GiftCard Payment request   : ", "INFO");
		
		if(enterTextInChrome(enterEmailForAmazonGC, getTestData(6, "AmazonGCEmail"))) {
			
			reportStep("Successfully enter the Amazon GiftCard ", "PASS");
			
		}else {
			
			reportStep("Failed to enter the Amazon GiftCard email ", "FAIL");
			
		}
		
		return this;
	
	}
	
	public AccountSettingsPage enterAmazonGiftCardEmailID(String email) {

		reportStep("About to enter the Email id for the Amazon GiftCard Payment request   : ", "INFO");
		
		if(enterTextInChrome(enterEmailForAmazonGC, email)) {
			
			reportStep("Successfully enter the Amazon GiftCard ", "PASS");
			
		}else {
			
			reportStep("Failed to enter the Amazon GiftCard email ", "FAIL");
			
		}
		
		return this;
	
	}

	public AccountSettingsPage selectAmazonGiftCardFromDropDown() {

		reportStep("About to select the Amazon GiftCard option from the dropdown ", "INFO");

		if(click(selectAmazonGiftCard)) {

			reportStep("Successfully selected the Amazon GiftCard  from the payment method drop down ", "PASS");

		}else {

			reportStep("Failed to select the Amazon GiftCard from the payment method drop down  ", "FAIL");
		}
		
		return this;

	}

	public AccountSettingsPage validateGiftCardEmail() {

		reportStep("About to validate the Gift Card payment settings either it might be Amazon or Flipkart - After Set ", "INFO");

		validateTheElementPresence(giftCardEmailAfterSet);

		return this;

	}

	//FlipKart GiftCard :

	public AccountSettingsPage enterFlipKartGiftCardEmailID() {

		reportStep("About to enter the Email id for the FlipKart GiftCard Payment request   : ", "INFO");
		
		if(enterTextInChrome(enterEmailForFlipKartGC, getTestData(6, "AmazonGCEmail"))) {
			
			reportStep("Successfully enter the FlipKart GiftCard ", "PASS");
			
		}else {
			
			reportStep("Failed to enter the FlipKart GiftCard email ", "FAIL");
			
		}
		
		return this;
	
	}

	public AccountSettingsPage enterFlipKartGiftCardEmailID(String email) {

		reportStep("About to enter the Email id for the FlipKart GiftCard Payment request   : ", "INFO");
		
		if(enterTextInChrome(enterEmailForFlipKartGC, email)) {
			
			reportStep("Successfully enter the FlipKart GiftCard ", "PASS");
			
		}else {
			
			reportStep("Failed to enter the FlipKart GiftCard email ", "FAIL");
			
		}
		
		return this;
	
	}

	
	public AccountSettingsPage selectFlipKartGiftCardFromDropDown() {

		reportStep("About to select the FlipKart GiftCard option from the dropdown ", "INFO");

		if(click(selectFlipKartGiftCard)) {

			reportStep("Successfully selected the FlipKart GiftCard  from the payment method drop down ", "PASS");

		}else {

			reportStep("Failed to select the FlipKart GiftCard from the payment method drop down  ", "FAIL");
		}
		
		return this;

	}

	//Paytm Wallet :

	public AccountSettingsPage enterPaytmWalletMobileNumber(String mobileNumber) {

		reportStep("About to enter Paytm Wallet Mobile Number   : ", "INFO");

		if(enterTextInChrome(paytmMobileNumberField, mobileNumber)) {

			reportStep("Successfully enter the Paytm Wallet Mobile Number  ", "PASS");

		}else {

			reportStep("Failed to enter the Paytm Wallet Mobile Number  ", "FAIL");

		}

		return this;

	}

	public AccountSettingsPage selectPaytmWalletFromDropDown() {

		reportStep("About to select the Paytm Wallet from the Payment method drop down ", "INFO");

		if(click(selectPaytmWallet)) {

			reportStep("Successfully selected the Paytm  from the payment method drop down ", "PASS");

		}else {

			reportStep("Failed to select the Paytm from the  payment method drop down  ", "FAIL");
		}

		return this;

	}

	public AccountSettingsPage validateWalletMobileNumber(String mobileNumber) {

		reportStep("About to validate the Paytm Wallet Payment Settings - After Set ", "INFO");

		isElementLocatedByXpathPresent("//*[contains(@text,'Mobile Number')]/following-sibling::*[contains(@text,'"+mobileNumber+"')]");

		return this;

	}

	//NEFT Negative validations :

	//Payment request page validation with payment method Negative validation :

	public AccountSettingsPage validateNEFTFieldErrorMessages() {

		reportStep("About to validate the NEFT field error validation - ", "INFO");

		validateTheElementPresence(nameOfBankAccountHolderName);
		validateTheElementPresence(pleaseEnterValidACHolderName);
		validateTheElementPresence(bank_Name);
		validateTheElementPresence(pleaseEnterValidBankName);
		validateTheElementPresence(bank_BranchName);

		scrollFromDownToUpinApp_FourSecDuration();

		validateTheElementPresence(pleaseEnterBranchNameOr4DigitBranchCode);
		validateTheElementPresence(bank_AccountNumber);
		scrollFromDownToUpinApp_FourSecDuration()	;		
		validateTheElementPresence(pleaseEnterTheBankAccountNum);
		scrollFromDownToUpinApp_FourSecDuration();		
		validateTheElementPresence(ifscCodeForBank);
		validateTheElementPresence(pleaseEnterIFSCCode);

		return this;

	}

	public AccountSettingsPage enterLessCharacterLengthIFSCCodeThenValidateErrorMessage() {

		enterNEFT_IFSC("85858585");
		validateTheElementPresence(ifscCodeShouldBeExactly11digits);
		return this;

	}

	public AccountSettingsPage enterSpecialCharacterIntoAccountNumberAndValidateErrorMessage() {


		reportStep("About to enter the special characters in to the AccountNumbers and validated the error message ", "INFO");
		enterNEFTAccountHolderName().
		enterNEFTBankName().
		enterNEFTBranchName().
		enterNEFTAccountNumber("#$%^&*!").
		enterNEFT_IFSC().
		clickOnSaveChangesButton().
		validateTheElementPresence(pleaseEnterTheBankAccountNum);
		return this;


	}

	public AccountSettingsPage enterAlphabetsIntoAccountNumberAndValidateErrorMessage() {

		reportStep("About to enter the Alpha characters in to the AccountNumbers and validated the error message ", "INFO");
		enterNEFTAccountHolderName().
		enterNEFTBankName().
		enterNEFTBranchName().
		enterNEFTAccountNumber("cashkaro").
		enterNEFT_IFSC().
		clickOnSaveChangesButton().
		validateTheElementPresence(pleaseEnterTheBankAccountNum);
		return this;

	}


	//Need to modify this : 
	public AccountSettingsPage validateInvalidIFSCCodeServerSideValidation(String mobNum) {

		reportStep("About validate the IFSC server side validation  ", "INFO");
		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo); 

		enterNEFTAccountHolderName().
		enterNEFTBankName().
		enterNEFTBranchName().
		enterNEFTAccountNumber().
		enterNEFT_IFSC("12345678912").
		enterPasswordToConfirm_NEFT().
		clickOnSaveChangesButton().
		validateTheElementPresence(ifscCodeFormatIsInvalid);

		return this;

	}

	public AccountSettingsPage enterNumbersIntoAccountHolderNameAndvalidateErrorMessage() {

		reportStep("About to enter the Number into Account holder Name and validate the error message  ", "INFO");
		enterNEFTAccountHolderName("123456").
		enterNEFTBankName().
		enterNEFTBranchName().
		enterNEFTAccountNumber().
		enterNEFT_IFSC().
		enterPasswordToConfirm_NEFT();
		clickOnSaveChangesButton().
		validateTheElementPresence(pleaseEnterValidACHolderName);

		return this;

	}

	public AccountSettingsPage enterNumbersIntoBankNameAndvalidateErrorMessage() {

		reportStep("About to enter the number into bank name and validate the error message  ", "INFO");
		enterNEFTAccountHolderName().
		enterNEFTBankName("12345").
		enterNEFTBranchName().
		enterNEFTAccountNumber().
		enterNEFT_IFSC().
		clickOnSaveChangesButton().
		validateTheElementPresence(pleaseEnterValidBankName);

		return this;

	}

	public AccountSettingsPage enterSpecialCharacterIntoBankNameAndvalidateErrorMessage() {

		reportStep("About to enter the special characters into bank name and validate the error message  ", "INFO");
		enterNEFTAccountHolderName().
		enterNEFTBankName("@#$%^").
		enterNEFTBranchName().
		enterNEFTAccountNumber().
		enterNEFT_IFSC().
		clickOnSaveChangesButton().
		validateTheElementPresence(pleaseEnterValidBankName);

		return this;

	}

	//Need to modify this : 
	public AccountSettingsPage enterSpecialCharacterIntoBankBranchNameAndValidateErrorMessage() {

		reportStep("About to enter the number into bank branch name and validate the server side error message  ", "INFO");

		enterNEFTAccountHolderName().
		enterNEFTBankName().
		enterNEFTBranchName("@#$%%##123").
		enterNEFTAccountNumber().
		enterNEFT_IFSC().
		enterPasswordToConfirm_NEFT().
		clickOnSaveChangesButton();
		validateTheElementPresence(leaseEnterBranchNameOR4DigitBranchCode);

		return this;

	}
	
	//Need to modify this : 
		public AccountSettingsPage enterDetailsWithOutPassword_And_ValidateTheError() {

			reportStep("About to enter the number into bank branch name and validate the server side error message  ", "INFO");

			enterNEFTAccountHolderName().
			enterNEFTBankName().
			enterNEFTBranchName().
			enterNEFTAccountNumber().
			enterNEFT_IFSC().
			clickOnSaveChangesButton();
			scrollFromDownToUpinApp_FourSecDuration();

			try {

				clickOnSaveChangesButton();
				reportStep(" Successfully hide the keyboard", "INFO");

			}catch (Exception e) {
				
				reportStep("Not able to hide the Keyboard ", "INFO");

			}
			
			
			validateTheElementPresence(pleaseEnterPwd);

			return this;


		}


	public AccountSettingsPage validateAmazonGiftCardErrorValidation_AllPossible() {

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo); 

		reportStep("About to validate the Amazon giftCard field error validation ", "INFO");

		//Basic error message - 
		validateTheElementPresence(pleaseEnterEmail);
		validateTheElementPresence(enterEmailIDtoAmazonGC);


		// Test input - 1 : @g.com
		enterAmazonGiftCardEmailID(getTestData(6, "AmazonGiftCardErrorScenario_1")).
		clickOnSaveChangesButton().
		
		
		validateTheElementPresence(pleaseEnterValidEmail);

		// Test input - 1 : s@gcom
		enterAmazonGiftCardEmailID(getTestData(6, "AmazonGiftCardErrorScenario_2")).
		clickOnSaveChangesButton().
		validateTheElementPresence(pleaseEnterValidEmail);
		validateTheElementPresence(pleaseEnterPwd);

		// Test input - 1 : s@.com
		enterAmazonGiftCardEmailID(getTestData(6, "AmazonGiftCardErrorScenario_3")).
		clickOnSaveChangesButton().
		validateTheElementPresence(pleaseEnterValidEmail);
		validateTheElementPresence(pleaseEnterPwd);

		// Test input - 1 : ##$#$#
		enterAmazonGiftCardEmailID(getTestData(6, "AmazonGiftCardErrorScenario_4")).
		clickOnSaveChangesButton().
		validateTheElementPresence(pleaseEnterValidEmail);
		validateTheElementPresence(pleaseEnterPwd);

		// Test input - 1 :123456
		enterAmazonGiftCardEmailID(getTestData(6, "AmazonGiftCardErrorScenario_5")).
		clickOnSaveChangesButton().
		validateTheElementPresence(pleaseEnterValidEmail);
		validateTheElementPresence(pleaseEnterPwd);

		// Test input - 1 :456@456.789 Note : As pre discussion with Jaikumar this validationn is commented as of now - ITs a bug
		//enterAmazonGiftCardEmailID(getTestData(6, "AmazonGiftCardErrorScenario_7")).
		//clickOnSaveChangesButton().
		//validateTheElementPresence(pleaseEnterValidEmail);

		// Test input - 1 : aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaafffffffffffffd@gmail.com
		enterAmazonGiftCardEmailID(getTestData(6, "AmazonGiftCardErrorScenario_6")).
		clickOnSaveChangesButton().
		validateTheElementPresence(pleaseEnterMax50CharsLong);
		validateTheElementPresence(pleaseEnterPwd);

		return this;

	}

	public AccountSettingsPage validateFlipKartGiftCardErrorValidation_AllPossible() {

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo); 

		reportStep("About to validate the Amazon giftCard field error validation ", "INFO");

		//Basic error message - 
		validateTheElementPresence(pleaseEnterEmail);
		validateTheElementPresence(enterEmailIDtoAmazonGC);
		validateTheElementPresence(pleaseEnterPwd);

		// Test input - 1 : @g.com
		enterFlipKartGiftCardEmailID(getTestData(6, "AmazonGiftCardErrorScenario_1")).
		clickOnSaveChangesButton().
		validateTheElementPresence(pleaseEnterValidEmail);
		validateTheElementPresence(pleaseEnterPwd);

		// Test input - 1 : s@gcom
		enterFlipKartGiftCardEmailID(getTestData(6, "AmazonGiftCardErrorScenario_2")).
		clickOnSaveChangesButton().
		validateTheElementPresence(pleaseEnterValidEmail);

		// Test input - 1 : s@.com
		enterFlipKartGiftCardEmailID(getTestData(6, "AmazonGiftCardErrorScenario_3")).
		clickOnSaveChangesButton().
		validateTheElementPresence(pleaseEnterValidEmail);

		// Test input - 1 : ##$#$#
		enterFlipKartGiftCardEmailID(getTestData(6, "AmazonGiftCardErrorScenario_4")).
		clickOnSaveChangesButton().
		validateTheElementPresence(pleaseEnterValidEmail);

		// Test input - 1 :123456
		enterFlipKartGiftCardEmailID(getTestData(6, "AmazonGiftCardErrorScenario_5")).
		clickOnSaveChangesButton().
		validateTheElementPresence(pleaseEnterValidEmail);

		// Test input - 1 :456@456.789 Note : As pre discussion with Jaikumar this validationn is commented as of now - ITs a bug
		//enterAmazonGiftCardEmailID(getTestData(6, "AmazonGiftCardErrorScenario_7")).
		//clickOnSaveChangesButton().
		//validateTheElementPresence(pleaseEnterValidEmail);

		// Test input - 1 : aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaafffffffffffffd@gmail.com
		enterFlipKartGiftCardEmailID(getTestData(6, "AmazonGiftCardErrorScenario_6")).
		clickOnSaveChangesButton().
		validateTheElementPresence(pleaseEnterMax50CharsLong);

		return this;

	}




}
