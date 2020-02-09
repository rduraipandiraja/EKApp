package com.app.ck.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.app.ck.base.WrapperMethods;
import com.app.ck.utilities.CashKaroUtility;
import com.app.ck.utilities.Utilities;
import com.aventstack.extentreports.ExtentTest;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class PaymentRequestPage extends WrapperMethods {

	// Constructor call
	public PaymentRequestPage(AppiumDriver<MobileElement> driver, ExtentTest testInfo) {

		WebDriverWait wait = new WebDriverWait(driver, 30);

		this.driver = driver;
		this.testInfo = testInfo;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);

		reportStep("Waiting for the PaymentRequestPage ", "INFO");
		try {
			wait.until(ExpectedConditions.visibilityOf(paymentRequest));
			reportStep("Successfully landed on the PaymentRequestPage ", "PASS");

		} catch (Exception e) {

			reportStep(e.getMessage(), "INFO");
			reportStep("Not able to land on the PaymentRequestPage ", "FAIL");
		}

	}

	// common :
	@FindBy(how = How.XPATH, using = "//android.widget.ImageView")
	MobileElement backButton;

	// Generic :
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='Payment Request']")
	MobileElement paymentRequest;
	@FindBy(how = How.XPATH, using = "(//android.widget.EditText)[1]")
	MobileElement paymentMethodDropDown;

	// NEFT
	@FindBy(how = How.XPATH, using = "//android.widget.CheckedTextView[@text='Bank Payment (NEFT)']")
	MobileElement selectBankPaymentNEFT;
	@FindBy(how = How.XPATH, using = "//android.widget.EditText[@content-desc='et_requestpaymentNeft_accountHolder']")
	MobileElement accountHolder;
	@FindBy(how = How.XPATH, using = "//android.widget.EditText[@content-desc='et_requestpaymentNeft_branch']")
	MobileElement bankBranchName;
	@FindBy(how = How.XPATH, using = "//android.widget.EditText[@content-desc='et_requestpaymentNeft_bankName']")
	MobileElement bankName;
	@FindBy(how = How.XPATH, using = "//android.widget.EditText[@content-desc='et_requestpaymentNeft_accountNumber']")
	MobileElement bankAccountNumber;
	@FindBy(how = How.XPATH, using = "//android.widget.EditText[@content-desc='et_requestpaymentNeft_ifscCode']")
	MobileElement bankIFSCCode;
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='GET PAID']")
	MobileElement getPaidButton;

	// Amazon Gift card :
	@FindBy(how = How.XPATH, using = "//android.widget.EditText[@content-desc='et_requestpaymentAmazon_email']")
	MobileElement enterEmailForAmazonGC;
	@FindBy(how = How.XPATH, using = "//android.widget.CheckedTextView[@text='Amazon Gift Card']")
	MobileElement selectAmazonGiftCard;

	// FlipKart Gift card :
	@FindBy(how = How.XPATH, using = "//android.widget.EditText[@content-desc='et_requestpaymentFlipkart_email']")
	MobileElement enterEmailForFlipkartGC;
	@FindBy(how = How.XPATH, using = "//android.widget.CheckedTextView[@text='Flipkart Gift Card']")
	MobileElement selectFlipKartGiftCard;

	// Paytm Wallet :
	@FindBy(how = How.XPATH, using = "//android.widget.EditText[@content-desc='et_requestpaymentPaytm_mobile']")
	MobileElement enterMobileNumatPaytm;
	@FindBy(how = How.XPATH, using = "//android.widget.CheckedTextView[@text='Paytm Wallet']")
	MobileElement selectPaytWallet;

	// Charity :
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='Yes']")
	MobileElement charity_Yes;
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='Would you like to donate to Charity?']")
	MobileElement wouldYouLike_Text;
	@FindBy(how = How.XPATH, using = "//android.widget.ImageView[@content-desc=\"img_requestpaymentNeft_donatehand\"]")
	MobileElement charityImage;
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='Select Charity']")
	MobileElement selectCharityLabelText;
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='Choose Charity']")
	MobileElement chooseCharity;
	@FindBy(how = How.XPATH, using = "(//android.widget.CheckedTextView)[2]")
	MobileElement firstValidChrity;
	@FindBy(how = How.XPATH, using = "//android.widget.EditText[contains(@content-desc,'charityAmount')]")
	MobileElement charityAmountField;
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text = 'Cashback / Rewards you wish to donate']")
	MobileElement placeHolderCashbackcharityWishToDonate;
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[contains(@text , 'Sorry! Your donation amount cannot exceed')]")
	MobileElement charityExceedErroMessage;
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[contains(@text , 'Please enter some amount')]")
	MobileElement pleaseEnterSomeAmount;
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='Would you like to donate to Charity?']/following-sibling::android.widget.TextView")
	MobileElement charityCloseButton;
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[contains(@text,'Please select charity')]")
	MobileElement pleaseSelectTheCharityErroMessage;
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[contains(@text,'Invalid donation amount')]")
	MobileElement invalidDonationAmount;

	@FindBy(how = How.XPATH, using = "//android.widget.TextView[contains(@text,'Name of Bank Account Holder')]/following-sibling::android.widget.EditText[contains(@text,'CashKaroUser')]")
	MobileElement acountHolderNameAfterSet;
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[contains(@text,'Bank Branch Name')]/following-sibling::android.widget.EditText[contains(@text,'CashKaroBranch')]")
	MobileElement branchNameAfterSet;
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[contains(@text,'Bank Account Number')]/following-sibling::android.widget.EditText[contains(@text,'*******89')]")
	MobileElement accountNumberAfterSet;
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[contains(@text,'IFSC Code for Bank')]/following-sibling::android.widget.EditText[contains(@text,'CORP0001206')]")
	MobileElement ifscAfterSet;
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[contains(@text,'Bank Name')]/following-sibling::android.widget.EditText[contains(@text,'PouringPounds')]")
	MobileElement bankNameAfterSet;

	// NEFT negative validations :

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

	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='IFSC Code should be exactly 11 digits']")
	MobileElement ifscCodeShouldBeExactly11digits;
	
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='Please enter the IFSC Code']")
	MobileElement pleaseEnterIFSCCodeError;

	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='Please enter correct Bank Account number.']")
	MobileElement pleaseEnterCorrectBankAccountNumber;

	@FindBy(how = How.XPATH, using = "//android.widget.TextView[contains(@text,'Invalid IFSC Code')]")
	MobileElement invalidIfsc;

	@FindBy(how = How.XPATH, using = "//android.widget.TextView[contains(@text,'Invalid Bank Branch')]")
	MobileElement invalidBankBranch;

	// Payment request Cashback / Rewards section banner Details :

	@FindBy(how = How.XPATH, using = "//android.widget.TextView[contains(@text,'Profit available for payment')]")
	MobileElement cashbackAvailableForPaymentText;

	@FindBy(how = How.XPATH, using = "//android.widget.TextView[contains(@text,'Profit available for payment')]")
	MobileElement rewardsAvailableForPaymentText;

	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@content-desc='txt_requestpayment_cashbackAmount']")
	MobileElement cashbackAvailableForPaymentAmount;

	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@content-desc='txt_requestpayment_rewardsAmount']")
	MobileElement rewardsAvailableForPaymentAmount;

	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@content-desc='txt_requestpayment_availableAmount']")
	MobileElement totalcashbackAvailableForPaymentAmount;

	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='Cashback']")
	MobileElement cashbackText;

	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='Rewards']")
	MobileElement rewardsText;

	// Amazon error validation :

	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='Please enter the Email ID']")
	MobileElement pleaseEnterEmail;

	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='Enter Email ID']")
	MobileElement enterEmailIDtoAmazonGC;

	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='Please enter a valid Email ID']")
	MobileElement pleaseEnterValidEmail;

	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='Please enter maximum 50 characters long']")
	MobileElement pleaseEnterMax50CharsLong;

	@FindBy(how = How.XPATH, using = "//*[contains(@text,'Enter Email ID')]/following-sibling::*[contains(@text,'cktest@test.com')]")
	MobileElement giftCardEmailAfterSet;

	// Method creation :-

	public PaymentRequestPage clickOnPaymentMethodDropDown() {

		reportStep("About to  click on the Payment drop down ", "INFO");

		if (click(paymentMethodDropDown)) {

			reportStep("Successfully clicked on the Payment dropdown to select the Payment Method to get the cashback ",
					"PASS");

		} else {

			reportStep("Failed to click onn the Payment dropdown to select the Payment method to get the cashback ",
					"FAIL");
		}

		return this;
	}

	public PaymentRequestPage selectBankPaymentNEFTFromDropDown() {

		reportStep("About to select the BankPaymentNEFT option from the dropdown ", "INFO");

		if (click(selectBankPaymentNEFT)) {

			reportStep("Successfully selected the BankPayment NEFT from the payment method drop down ", "PASS");

		} else {

			reportStep("Failed to select the BankPayment NEFT from the payment method drop down  ", "FAIL");
		}

		return this;

	}

	public PaymentRequestPage selectAmazonGiftCardFromDropDown() {

		reportStep("About to select the Amazon GiftCard option from the dropdown ", "INFO");

		if (click(selectAmazonGiftCard)) {

			reportStep("Successfully selected the Amazon GiftCard  from the payment method drop down ", "PASS");

		} else {

			reportStep("Failed to select the Amazon GiftCard from the payment method drop down  ", "FAIL");
		}

		return this;

	}

	public PaymentRequestPage selectFlipKartGiftCardFromDropDown() {

		reportStep("About to select the FlipKart GiftCard option from the dropdown ", "INFO");

		if (click(selectFlipKartGiftCard)) {

			reportStep("Successfully selected the FlipKart GiftCard  from the payment method drop down ", "PASS");

		} else {

			reportStep("Failed to select the Amazon FlipKart from the payment method drop down  ", "FAIL");
		}

		return this;

	}

	public PaymentRequestPage selectPaytmWalletCardFromDropDown() {

		reportStep("About to select the Paytm Wallet option from the dropdown ", "INFO");

		if (click(selectPaytWallet)) {

			reportStep("Successfully selected the Paytm Wallet from the payment method drop down ", "PASS");

		} else {

			reportStep("Failed to select the Paytm Wallet  from the payment method drop down  ", "FAIL");
		}

		return this;

	}

	public PaymentRequestPage validateNEFTPaymentSettings() {

		reportStep("About to validate the NEFT Payment Settings - After Set ", "INFO");

		validateTheElementPresence(acountHolderNameAfterSet);
		validateTheElementPresence(bankNameAfterSet);
		validateTheElementPresence(branchNameAfterSet);
		validateTheElementPresence(accountNumberAfterSet);
		validateTheElementPresence(ifscAfterSet);

		return this;

	}

	//Its a common method to validate the auto gift card email in payment request page :
	public PaymentRequestPage validateGiftCardEmail() {

		reportStep("About to validate the Amazon Payment Settings - After Set ", "INFO");

		validateTheElementPresence(giftCardEmailAfterSet);

		return this;

	}

	// NEFT field methods

	public PaymentRequestPage enterNEFTAccountHolderName() {

		String NEFTbankAccountHolder = getTestData(6, "NEFTAccountHolderName");

		reportStep("About to enter  the Account holder name ", "INFO");

		if (enterText(accountHolder, NEFTbankAccountHolder)) {

			reportStep("Successfully enter the  " + NEFTbankAccountHolder + " as bank holder name ", "PASS");
		} else {

			reportStep("Failed to  enter the  " + NEFTbankAccountHolder + " as bank holder name ", "FAIL");
		}

		return this;
	}

	public PaymentRequestPage enterNEFTAccountHolderName(String NEFTbankAccountHolder) {

		reportStep("About to enter  the Account holder name ", "INFO");

		if (enterText(accountHolder, NEFTbankAccountHolder)) {

			reportStep("Successfully enter the  " + NEFTbankAccountHolder + " as bank holder name ", "PASS");
		} else {

			reportStep("Failed to  enter the  " + NEFTbankAccountHolder + " as bank holder name ", "FAIL");
		}

		return this;
	}

	public PaymentRequestPage enterNEFTBankName() {

		String NEFTBankName = getTestData(6, "NEFTBankName");

		reportStep("About to enter  the Account holder name ", "INFO");

		if (enterText(bankName, NEFTBankName)) {

			reportStep("Successfully enter the  " + NEFTBankName + " as bank name ", "PASS");
		} else {

			reportStep("Failed to  enter the  " + NEFTBankName + " as bank  name ", "FAIL");
		}

		return this;
	}

	public PaymentRequestPage enterNEFTBankName(String NEFTBankName) {

		reportStep("About to enter  the Account holder name ", "INFO");

		if (enterText(bankName, NEFTBankName)) {

			reportStep("Successfully enter the  " + NEFTBankName + " as bank name ", "PASS");
		} else {

			reportStep("Failed to  enter the  " + NEFTBankName + " as bank  name ", "FAIL");
		}

		return this;
	}

	public PaymentRequestPage enterNEFTBranchName() {

		String NEFTBranchName = getTestData(6, "NEFTBranchName");

		reportStep("About to enter  the BranchName ", "INFO");

		if (enterText(bankBranchName, NEFTBranchName)) {

			reportStep("Successfully enter the  " + NEFTBranchName + " as BranchName ", "PASS");
		} else {

			reportStep("Failed to  enter the  " + NEFTBranchName + " as BranchName ", "FAIL");
		}

		return this;
	}

	public PaymentRequestPage enterNEFTBranchName(String NEFTBranchName) {

		reportStep("About to enter  the BranchName ", "INFO");

		if (enterText(bankBranchName, NEFTBranchName)) {

			reportStep("Successfully enter the  " + NEFTBranchName + " as BranchName ", "PASS");
		} else {

			reportStep("Failed to  enter the  " + NEFTBranchName + " as BranchName ", "FAIL");
		}

		return this;
	}

	public PaymentRequestPage enterNEFTAccountNumber() {

		String NEFTAccountNumber = getTestData(6, "NEFTAccountNumber");

		reportStep("About to enter  the NEFT acccount number  ", "INFO");

		if (enterText(bankAccountNumber, NEFTAccountNumber)) {

			reportStep("Successfully enter the  " + NEFTAccountNumber + " as  NEFT acccount number ", "PASS");
		} else {

			reportStep("Failed to  enter the  " + NEFTAccountNumber + " as  NEFT acccount number ", "FAIL");
		}

		return this;

	}

	public PaymentRequestPage enterNEFTAccountNumber(String NEFTAccountNumber) {

		reportStep("About to enter  the NEFT acccount number  ", "INFO");

		if (enterText(bankAccountNumber, NEFTAccountNumber)) {

			reportStep("Successfully enter the  " + NEFTAccountNumber + " as  NEFT acccount number ", "PASS");
		} else {

			reportStep("Failed to  enter the  " + NEFTAccountNumber + " as  NEFT acccount number ", "FAIL");
		}

		return this;

	}

	public PaymentRequestPage enterNEFT_IFSC() {

		String NEFTAccountIFSC = getTestData(6, "NEFTAccountIFSC");

		reportStep("About to enter  the NEFT acccount number  ", "INFO");

		if (enterText(bankIFSCCode, NEFTAccountIFSC)) {

			reportStep("Successfully enter the  " + NEFTAccountIFSC + " as  NEFT acccount number ", "PASS");
		} else {

			reportStep("Failed to  enter the  " + NEFTAccountIFSC + " as  NEFT acccount number ", "FAIL");
		}

		return this;

	}

	public PaymentRequestPage enterNEFT_IFSC(String NEFTAccountIFSC) {

		reportStep("About to enter  the NEFT acccount number : NEFT IFSC is -  " + NEFTAccountIFSC, "INFO");

		if (enterText(bankIFSCCode, NEFTAccountIFSC)) {

			reportStep("Successfully enter the  " + NEFTAccountIFSC + " as  NEFT acccount number ", "PASS");
		} else {

			reportStep("Failed to  enter the  " + NEFTAccountIFSC + " as  NEFT acccount number ", "FAIL");
		}

		return this;

	}

	public PaymentOTPPage clickOnGETPAID() {

		reportStep("About to click on the GET PAID button ", "INFO");

		if (clickAfterWait(getPaidButton)) {

			reportStep("Successfully clicked on the GET PAID button at the Payment page ", "PASS");

		} else {

			reportStep("Failed to click on the GET PAID button at the Payment page ", "FAIL");
		}

		return new PaymentOTPPage(driver, testInfo);
	}

	public PaymentRequestPage clickOnGETPAIDForFailure() {

		reportStep("About to click on the GET PAID button for failure scenario  ", "INFO");

		if (click(getPaidButton)) {

			reportStep("Successfully clicked on the GET PAID button at the PaymentRequest Page ", "PASS");

		} else {

			reportStep("Failed to click on the GET PAID button at the PaymentRequest Page ", "FAIL");
		}

		return this;
	}

	public PaymentRequestPage enterBankNEFTPaymentDetails() {

		reportStep("About to enter the Account Holder name as  : ", "INFO");

		enterNEFTAccountHolderName().enterNEFTBankName().enterNEFTBranchName().enterNEFTAccountNumber()
				.enterNEFT_IFSC();

		return this;

	}

	public PaymentRequestPage enterAmazonGiftCardEmailID() {

		reportStep("About to enter the Email id for the Amazon GiftCard Payment request   : ", "INFO");

		if (enterTextInChrome(enterEmailForAmazonGC, getTestData(6, "AmazonGCEmail"))) {

			reportStep("Successfully enter the Amazon GiftCard ", "PASS");

		} else {

			reportStep("Failed to enter the Amazon GiftCard email ", "FAIL");

		}

		return this;

	}

	public PaymentRequestPage enterAmazonGiftCardEmailID(String amazonEmailID) {

		reportStep("About to enter the Email id for the Amazon GiftCard Payment request   : ", "INFO");

		if (enterTextInChrome(enterEmailForAmazonGC, amazonEmailID)) {

			reportStep("Successfully enter the Amazon GiftCard ", "PASS");

		} else {

			reportStep("Failed to enter the Amazon GiftCard email ", "FAIL");

		}

		return this;

	}

	public PaymentRequestPage enterFlipKartGiftCardEmailID() {

		reportStep("About to enter the Email id for the Flipkart GiftCard Payment request   : ", "INFO");

		if (enterTextInChrome(enterEmailForFlipkartGC, getTestData(6, "AmazonGCEmail"))) {

			reportStep("Successfully enter the Flipkart GiftCard ", "PASS");

		} else {

			reportStep("Failed to enter the Flipkart GiftCard email ", "FAIL");

		}

		return this;

	}

	public PaymentRequestPage enterFlipKartGiftCardEmailID(String flipKartEmailID) {

		reportStep("About to enter the Email id as " + flipKartEmailID
				+ "  for the Flipkart GiftCard Payment request   : ", "INFO");

		if (enterTextInChrome(enterEmailForFlipkartGC, flipKartEmailID)) {

			reportStep("Successfully enter the Flipkart GiftCard ", "PASS");

		} else {

			reportStep("Failed to enter value as " + flipKartEmailID + " the Flipkart GiftCard email ", "FAIL");

		}

		return this;

	}

	public PaymentRequestPage enterPaytmWalletMobileNumber(String paytm_MobileNumber) {

		reportStep("About to enter the Mobile number " + paytm_MobileNumber + "  to PaytmWalet payment request  : ",
				"INFO");

		if (enterTextInChrome(enterMobileNumatPaytm, paytm_MobileNumber)) {

			reportStep("Successfully enter Mobile number to the Paytm wallet mobile number to the field  ", "PASS");

		} else {

			reportStep("Failed to enter the mobile number into the Patm mobile wallet payment request  ", "FAIL");

		}

		return this;

	}


	// Payment request page validation with payment method Negative validation :

	public PaymentRequestPage validateNEFTFieldErrorMessages() {

		reportStep("About to validate the NEFT field error validation - ", "INFO");

		validateTheElementPresence(nameOfBankAccountHolderName);
		validateTheElementPresence(pleaseEnterValidACHolderName);
		validateTheElementPresence(bank_Name);
		validateTheElementPresence(pleaseEnterValidBankName);
		validateTheElementPresence(bank_BranchName);

		scrollFromDownToUpinApp();

		try {

			clickOnGETPAIDForFailure();
			reportStep("Successfully Hide the Keyboard", "INFO");
			
		}catch(Exception e) {

			reportStep("Failed to Hide the keyboard", "INFO");

		}

		validateTheElementPresence(pleaseEnterBranchNameOr4DigitBranchCode);
		validateTheElementPresence(bank_AccountNumber);
		scrollFromDownToUpinApp_FourSecDuration();
		try {

			clickOnGETPAIDForFailure();
			reportStep("Successfully Hide the Keyboard", "INFO");
			
		}catch(Exception e) {

			reportStep("Failed to Hide the keyboard", "INFO");

		}
		validateTheElementPresence(pleaseEnterTheBankAccountNum);
		scrollFromDownToUpinApp_FourSecDuration();
		try {

			clickOnGETPAIDForFailure();
			reportStep("Successfully Hide the Keyboard", "INFO");
			
		}catch(Exception e) {

			reportStep("Failed to Hide the keyboard", "INFO");

		}
		validateTheElementPresence(ifscCodeForBank);
		validateTheElementPresence(pleaseEnterIFSCCodeError);

		return this;

	}

	public PaymentRequestPage enterLessCharacterLengthIFSCCodeThenValidateErrorMessage() {

		enterNEFT_IFSC("85858585");
		validateTheElementPresence(ifscCodeShouldBeExactly11digits);
		return this;

	}

	public PaymentRequestPage enterSpecialCharacterIntoAccountNumberAndValidateErrorMessage() {

		reportStep("About to enter the special characters in to the AccountNumbers and validated the error message ",
				"INFO");
		enterNEFTAccountHolderName().enterNEFTBankName().enterNEFTBranchName().enterNEFTAccountNumber("#$%^&*!")
				.enterNEFT_IFSC().clickOnGETPAIDForFailure().validateTheElementPresence(pleaseEnterTheBankAccountNum);
		return this;

	}

	public PaymentRequestPage enterAlphabetsIntoAccountNumberAndValidateErrorMessage() {

		reportStep("About to enter the Alpha characters in to the AccountNumbers and validated the error message ",
				"INFO");
		enterNEFTAccountHolderName().enterNEFTBankName().enterNEFTBranchName().enterNEFTAccountNumber("cashkaro")
				.enterNEFT_IFSC().clickOnGETPAIDForFailure().validateTheElementPresence(pleaseEnterTheBankAccountNum);
		return this;

	}

	public PaymentRequestPage validateInvalidIFSCCodeServerSideValidation(String mobNum) {

		reportStep("About validate the IFSC server side validation  ", "INFO");
		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo);

		 enterNEFTAccountHolderName().enterNEFTBankName().enterNEFTBranchName()
				.enterNEFTAccountNumber().enterNEFT_IFSC("12345678912").clickOnGETPAIDForFailure();

		validateTheElementPresence(invalidIfsc);

		return this;

	}

	public PaymentRequestPage enterNumbersIntoAccountHolderNameAndvalidateErrorMessage() {

		reportStep("About to enter the Number into Account holder Name and validate the error message  ", "INFO");
		enterNEFTAccountHolderName("123456").enterNEFTBankName().enterNEFTBranchName().enterNEFTAccountNumber()
				.enterNEFT_IFSC().clickOnGETPAIDForFailure().validateTheElementPresence(pleaseEnterValidACHolderName);

		return this;

	}

	public PaymentRequestPage enterNumbersIntoBankNameAndvalidateErrorMessage() {

		reportStep("About to enter the number into bank name and validate the error message  ", "INFO");
		enterNEFTAccountHolderName().enterNEFTBankName("12345").enterNEFTBranchName().enterNEFTAccountNumber()
				.enterNEFT_IFSC().clickOnGETPAIDForFailure().validateTheElementPresence(pleaseEnterValidBankName);

		return this;

	}

	public PaymentRequestPage enterSpecialCharacterIntoBankNameAndvalidateErrorMessage() {

		reportStep("About to enter the special characters into bank name and validate the error message  ", "INFO");
		enterNEFTAccountHolderName().enterNEFTBankName("@#$%^").enterNEFTBranchName().enterNEFTAccountNumber()
				.enterNEFT_IFSC().clickOnGETPAIDForFailure().validateTheElementPresence(pleaseEnterValidBankName);

		return this;

	}

	public PaymentRequestPage enterSpecialCharacterIntoBankBranchNameAndValidateErrorMessage(String mobNum) {

		reportStep("About to enter the number into bank branch name and validate the server side error message  ",
				"INFO");

		CashKaroUtility objCashKaroUtility = 
				new CashKaroUtility(driver, testInfo);

		enterNEFTAccountHolderName().enterNEFTBankName()
		.enterNEFTBranchName("@#$%%##123").
		enterNEFTAccountNumber().
		enterNEFT_IFSC().
		clickOnGETPAIDForFailure();

		validateTheElementPresence(pleaseEnterBranchNameOr4DigitBranchCode);

		return this;

	}

	public PaymentPage clickOnBackButton() {

		reportStep("About to click on the Payment Request page", "INFO");

				if(click(backButton)) {

					reportStep("Successfully clicked on the Payment Request page BackButton  ", "Pass");

				}else {

					reportStep("Failed to  click on the Payment Request page BackButton ", "FAIL");

				}


		return new PaymentPage(driver, testInfo);
	}

	public void validateAmazonGiftCardErrorValidation_AllPossible() {

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo);

		reportStep("About to validate the Amazon giftCard field error validation ", "INFO");

		// Basic error message -
		validateTheElementPresence(pleaseEnterEmail);
		validateTheElementPresence(enterEmailIDtoAmazonGC);

		// Test input - 1 : @g.com
		enterAmazonGiftCardEmailID(getTestData(6, "AmazonGiftCardErrorScenario_1")).clickOnGETPAIDForFailure()
				.validateTheElementPresence(pleaseEnterValidEmail);

		// Test input - 1 : s@gcom
		enterAmazonGiftCardEmailID(getTestData(6, "AmazonGiftCardErrorScenario_2")).clickOnGETPAIDForFailure()
				.validateTheElementPresence(pleaseEnterValidEmail);

		// Test input - 1 : s@.com
		enterAmazonGiftCardEmailID(getTestData(6, "AmazonGiftCardErrorScenario_3")).clickOnGETPAIDForFailure()
				.validateTheElementPresence(pleaseEnterValidEmail);

		// Test input - 1 : ##$#$#
		enterAmazonGiftCardEmailID(getTestData(6, "AmazonGiftCardErrorScenario_4")).clickOnGETPAIDForFailure()
				.validateTheElementPresence(pleaseEnterValidEmail);

		// Test input - 1 :123456
		enterAmazonGiftCardEmailID(getTestData(6, "AmazonGiftCardErrorScenario_5")).clickOnGETPAIDForFailure()
				.validateTheElementPresence(pleaseEnterValidEmail);

		// Test input - 1 :456@456.789 Note : As pre discussion with Jaikumar this
		// validationn is commented as of now - ITs a bug
		// enterAmazonGiftCardEmailID(getTestData(6, "AmazonGiftCardErrorScenario_7")).
		// clickOnGETPAIDForFailure().
		// validateTheElementPresence(pleaseEnterValidEmail);

		// Test input - 1 :
		// aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaafffffffffffffd@gmail.com
		enterAmazonGiftCardEmailID(getTestData(6, "AmazonGiftCardErrorScenario_6")).clickOnGETPAIDForFailure()
				.validateTheElementPresence(pleaseEnterMax50CharsLong);

	}

	public void validateFlipKartGiftCardErrorValidation_AllPossible() {

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo);

		reportStep("About to validate the Amazon giftCard field error validation ", "INFO");

		// Basic error message -
		validateTheElementPresence(pleaseEnterEmail);
		validateTheElementPresence(enterEmailIDtoAmazonGC);

		// Test input - 1 : @g.com
		enterFlipKartGiftCardEmailID(getTestData(6, "AmazonGiftCardErrorScenario_1")).clickOnGETPAIDForFailure()
				.validateTheElementPresence(pleaseEnterValidEmail);

		// Test input - 1 : s@gcom
		enterFlipKartGiftCardEmailID(getTestData(6, "AmazonGiftCardErrorScenario_2")).clickOnGETPAIDForFailure()
				.validateTheElementPresence(pleaseEnterValidEmail);

		// Test input - 1 : s@.com
		enterFlipKartGiftCardEmailID(getTestData(6, "AmazonGiftCardErrorScenario_3")).clickOnGETPAIDForFailure()
				.validateTheElementPresence(pleaseEnterValidEmail);

		// Test input - 1 : ##$#$#
		enterFlipKartGiftCardEmailID(getTestData(6, "AmazonGiftCardErrorScenario_4")).clickOnGETPAIDForFailure()
				.validateTheElementPresence(pleaseEnterValidEmail);

		// Test input - 1 :123456
		enterFlipKartGiftCardEmailID(getTestData(6, "AmazonGiftCardErrorScenario_5")).clickOnGETPAIDForFailure()
				.validateTheElementPresence(pleaseEnterValidEmail);

		// Test input - 1 :456@456.789 Note : As pre discussion with Jaikumar this
		// validationn is commented as of now - ITs a bug
		// enterAmazonGiftCardEmailID(getTestData(6, "AmazonGiftCardErrorScenario_7")).
		// clickOnGETPAIDForFailure().
		// validateTheElementPresence(pleaseEnterValidEmail);

		// Test input - 1 :
		// aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaafffffffffffffd@gmail.com
		enterFlipKartGiftCardEmailID(getTestData(6, "AmazonGiftCardErrorScenario_6")).clickOnGETPAIDForFailure()
				.validateTheElementPresence(pleaseEnterMax50CharsLong);

	}

	// Payment request page - Cashback banner validation

	public PaymentRequestPage paymentRequestPaymentAvailabilityValidation(String cashbackType, String cashbackValue,
			String rewardsValue) {

		String actual = "";

		reportStep("About to validate the Cashback and Rewards Payment avilablity section in the payment Request page ",
				"INFO");

		switch (cashbackType) {

		case "Only_Cashback":

			validateTheElementPresence(cashbackAvailableForPaymentText);

			actual = getText(totalcashbackAvailableForPaymentAmount);

			validateTheActualValueContainsExpectedValue(actual, cashbackValue);

			break;

		case "Only_Rewards":

			validateTheElementPresence(rewardsAvailableForPaymentText);

			actual = getText(totalcashbackAvailableForPaymentAmount);

			validateTheActualValueContainsExpectedValue(actual, rewardsValue);

			break;

		case "BothCashback_Rewards":

			float cashbackAmount = Float.parseFloat(cashbackValue);
			float rewardsAmount = Float.parseFloat(rewardsValue);
			float totalAmount = cashbackAmount + rewardsAmount;

			String strTotalAmount = Float.toString(totalAmount) + "0";
			String strOnlyCashbackAmount = Float.toString(cashbackAmount) + "0";
			String strOnlyRewardsAmount = Float.toString(rewardsAmount) + "0";

			validateTheElementPresence(rewardsAvailableForPaymentText);
			validateTheElementPresence(cashbackText);
			validateTheElementPresence(rewardsText);

			// validate total cashback amount
			actual = getText(totalcashbackAvailableForPaymentAmount);
			validateTheActualValueContainsExpectedValue(actual, strTotalAmount);

			// validate only cashback amount
			actual = getText(cashbackAvailableForPaymentAmount);
			validateTheActualValueContainsExpectedValue(actual, strOnlyCashbackAmount);

			// validate only rewards amount
			actual = getText(rewardsAvailableForPaymentAmount);
			validateTheActualValueContainsExpectedValue(actual, strOnlyRewardsAmount);

			break;

		}

		return this;

	}

	public PaymentRequestPage validateWalletMobileNumber(String mobileNumber) {

		reportStep("About to validate the Paytm Wallet Payment Settings - After Set ", "INFO");

		isElementLocatedByXpathPresent(
				"//*[contains(@text,'Mobile Number')]/following-sibling::*[contains(@text,'" + mobileNumber + "')]");

		return this;

	}

}
