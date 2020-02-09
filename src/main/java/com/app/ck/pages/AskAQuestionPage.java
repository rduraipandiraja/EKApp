package com.app.ck.pages;


import org.openqa.selenium.By;
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

public class AskAQuestionPage extends WrapperMethods {
	
	//Constructor call
	public AskAQuestionPage(AppiumDriver<MobileElement> driver, ExtentTest testInfo) {

		WebDriverWait wait = new WebDriverWait(driver, 30);

		this.driver = driver;
		this.testInfo = testInfo;
		 PageFactory.initElements(new AppiumFieldDecorator(driver), this);

		reportStep("Waiting for the ask a question page ", "INFO");
		try {
			wait.until(ExpectedConditions.visibilityOf(askAQuestion));
			reportStep("Successfully landed on the ask a question page ", "PASS");

		}catch(Exception e) {
			
			reportStep(e.getMessage(), "INFO");
			reportStep("Not able to land on the ask a question Page ", "FAIL");
		}

	}

	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='ASK A QUESTION']")
	MobileElement askAQuestion ;

	@FindBy(how = How.XPATH, using = "//android.widget.ImageView[@content-desc='img_contactUs_contactpage']")
	MobileElement imageAskAQuestion ;

	@FindBy(how = How.XPATH, using = "//android.widget.EditText[@text='Select from list below']")
	MobileElement dropDownSelectFromListBelow ;

	@FindBy(how = How.XPATH, using = "//android.widget.CheckedTextView[@text='Select from list below']")
	MobileElement popupSelectFromListBelow ;

	@FindBy(how = How.XPATH, using = "//android.widget.CheckedTextView[@text='General enquiries']")
	MobileElement popupGeneralEnquiries ;

	@FindBy(how = How.XPATH, using = "//android.widget.CheckedTextView[@text='My Profit is incorrect or missing']")
	MobileElement popupMyProfitIsIncorrect ;

	@FindBy(how = How.XPATH, using = "//android.widget.CheckedTextView[@text='Other']")
	MobileElement popupOther ;

	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='Enter your Name']")
	MobileElement enterYourNameTextBox ;

	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='Enter your Email ID']")
	MobileElement enterYourEmailIdTextBox ;

	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='Your message']")
	MobileElement yourMessageTextBox ;

	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='SUBMIT']")
	MobileElement submitButton ;

	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='Please enter your Name']")
	MobileElement pleaseEnterYourName ;

	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='Please enter the Email ID']")
	MobileElement pleaseEnterYourEmailId ;

	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='Please enter the Details']")
	MobileElement pleaseEnterTheDetails ;

	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='Please choose the subject']")
	MobileElement pleaseChooseTheSubject ;

	@FindBy(how = How.XPATH, using = "//android.widget.EditText[@content-desc='et_contactUs_yourName']")
	MobileElement enterYourNameTextBoxField ;

	@FindBy(how = How.XPATH, using = "//android.widget.EditText[@content-desc='et_contactUs_email']")
	MobileElement enterYourEmailIdTextBoxField ;

	@FindBy(how = How.XPATH, using = "//android.widget.EditText[@content-desc='et_contactUs_yourMessage']")
	MobileElement yourMessageTextBoxField ;

	@FindBy(how = How.XPATH, using = "//android.widget.TextView[contains(@text,'Contact-us Email has been sent.')]")
	MobileElement successMessage ;

	public AskAQuestionPage clickSubmitButton() {

		reportStep("About to click submitButton in AskAQuestion", "INFO");
		
		if(click(submitButton)) {
			
			reportStep("Sucessfully clicked on the submitButton ", "PASS");
			
		}else {
			
			reportStep("Not able to click on the submitButton ", "FAIL");
		}

		return this;
		
	
		
	}
	
	public AskAQuestionPage clickSubmitButtonVerifySuccessMessage() {

		reportStep("About to click submitButton in AskAQuestion", "INFO");
		
		clickSubmitButton();
		validateTheElementPresence(successMessage);

		return this;
		
	
		
	}

	public AskAQuestionPage clickSelectFromListBelow() {

		reportStep("About to click SelectFromListBelow in AskAQuestion", "INFO");
		
		if(click(dropDownSelectFromListBelow)) {
			
			reportStep("Sucessfully clicked on the SelectFromListBelow ", "PASS");
			
		}else {
			
			reportStep("Not able to click on the SelectFromListBelow ", "FAIL");
		}

		return this;
		
	
		
	}

	public AskAQuestionPage clickGeneralEnquirie() {

		reportStep("About to click GeneralEnquirie in AskAQuestion", "INFO");
		
		if(click(popupGeneralEnquiries)) {
			
			reportStep("Sucessfully clicked on the GeneralEnquirie ", "PASS");
			
		}else {
			
			reportStep("Not able to click on the GeneralEnquirie ", "FAIL");
		}

		return this;
		
	
		
	}

	public AskAQuestionPage clickMyProfitIsIncorrect() {

		reportStep("About to click MyProfitIsIncorrect in AskAQuestion", "INFO");
		
		if(click(popupMyProfitIsIncorrect)) {
			
			reportStep("Sucessfully clicked on the MyProfitIsIncorrect ", "PASS");
			
		}else {
			
			reportStep("Not able to click on the MyProfitIsIncorrect ", "FAIL");
		}

		return this;
		
	
		
	}

	public AskAQuestionPage clickOther() {

		reportStep("About to click Other in AskAQuestion", "INFO");
		
		if(click(popupOther)) {
			
			reportStep("Sucessfully clicked on the Other ", "PASS");
			
		}else {
			
			reportStep("Not able to click on the Other ", "FAIL");
		}

		return this;
		
	
		
	}

	public AskAQuestionPage validateTheErrorMessage() {

		reportStep("About to validate errormessage in AskAQuestion", "INFO");
		
		clickSubmitButton();

		validateTheElementPresence(pleaseChooseTheSubject);
		validateTheElementPresence(enterYourNameTextBox);
		validateTheElementPresence(enterYourEmailIdTextBox);
		validateTheElementPresence(yourMessageTextBox);	
		validateTheElementPresence(pleaseEnterYourName);
		validateTheElementPresence(pleaseEnterYourEmailId);
		validateTheElementPresence(pleaseEnterTheDetails);
		
		return this;
		
	
		
	}

	public AskAQuestionPage validateEnterMax50CharacterErrorMessage(String name, String email, int index) {

		reportStep("About to validate EnterMax50CharacterErrorMessage in AskAQuestion", "INFO");
		
		enterEnterYourName(name);
		enterEnterYourEmailId(email);
		clickSubmitButton();
		
		String maximumCharacterErrorMessageXpath= "(//android.widget.TextView[@text='Please enter maximum 50 characters long'])["+index+"]";
		
		if(isElementLocatedByXpathPresent(maximumCharacterErrorMessageXpath)) {

			reportStep("Verify the error message maximum character ", "INFO");
		}else {

			reportStep("Not able to the error message maximum character ", "FAIL");
		}
		
		MobileElement maximumCharacterErrorMessage = driver.findElement(By.xpath(maximumCharacterErrorMessageXpath));

		validateTheElementPresence(maximumCharacterErrorMessage);
		
		return this;
		
	
		
	}

	public AskAQuestionPage validateEnterMin2CharacterErrorMessage(String name, String email, int index) {

		reportStep("About to validate EnterMin2CharacterErrorMessage in AskAQuestion", "INFO");
		
		enterEnterYourName(name);
		enterEnterYourEmailId(email);
		clickSubmitButton();
		
		String minimumCharacterErrorMessageXpath= "(//android.widget.TextView[@text='Please enter minimum 2 characters long'])["+index+"]";
		
		if(isElementLocatedByXpathPresent(minimumCharacterErrorMessageXpath)) {

			reportStep("Verify the error message minimum character ", "INFO");
		}else {

			reportStep("Not able to the error message minimum character ", "FAIL");
		}
		
		MobileElement minimumCharacterErrorMessage = driver.findElement(By.xpath(minimumCharacterErrorMessageXpath));

		validateTheElementPresence(minimumCharacterErrorMessage);
		
		return this;
		
	
		
	}

	public AskAQuestionPage enterEnterYourName(String name) {

		reportStep("About to enter EnterYourName in AskAQuestion", "INFO");
		
		if(enterText(enterYourNameTextBoxField, name)) {
			
			reportStep("Sucessfully entered in the EnterYourName ", "PASS");
			
		}else {
			
			reportStep("Not able to enter in the EnterYourName ", "FAIL");
		}

		return this;
		
	
		
	}
	
	public AskAQuestionPage enterEnterYourEmailId(String email) {

		reportStep("About to enter EnterYourEmailId in AskAQuestion", "INFO");
		
		if(enterText(enterYourEmailIdTextBoxField, email)) {
			
			reportStep("Sucessfully entered in the EnterYourEmailId ", "PASS");
			
		}else {
			
			reportStep("Not able to enter in the EnterYourEmailId ", "FAIL");
		}

		return this;
		
	
		
	}
	
	public AskAQuestionPage enterYourMessage(String message) {

		reportStep("About to enter YourMessage in AskAQuestion", "INFO");
		
		if(enterText(yourMessageTextBoxField, message)) {
			
			reportStep("Sucessfully entered in the YourMessage ", "PASS");
			
		}else {
			
			reportStep("Not able to enter in the YourMessage ", "FAIL");
		}

		return this;
		
	
		
	}

	public ProfilePage clickBackButton() {

		reportStep("About to click on back button in ProfilePage", "INFO");

//		if(click(backButtonClick)) {
//
//			reportStep("Successfully clicked on the back button MyEarningsPage", "PASS");
//		}else {
//
//			reportStep("Failed to  click on the back button in MyEarningsPage", "FAIL");
//		}
		
		driver.navigate().back();

		return new ProfilePage(driver, testInfo);
		
	}
	
	public HelpPage clickBackButtonSignedUser() {

		reportStep("About to click on back button in SignedInProfilePage", "INFO");
		
		driver.navigate().back();

		return new HelpPage(driver, testInfo);
		
	}

	public AskAQuestionPage validateEmailAddressDefaultValue(String emailAddress) {

		reportStep("About to validate the EmailAddressDefaultValue in AskAQuestion", "INFO");
		
		String emailAddressDefaultValue = getText(enterYourEmailIdTextBoxField);
		
		validateTheActualValueWithExpectedValue(emailAddressDefaultValue, emailAddress);

		return this;
		
	
		
	}
	
	public AskAQuestionPage validateUserNameDefaultValue(String userName) {

		reportStep("About to validate the UserNameDefaultValue in AskAQuestion", "INFO");
		
		String userNameDefaultValue = getText(enterYourNameTextBoxField);
		
		validateTheActualValueWithExpectedValue(userNameDefaultValue, userName);

		return this;
		
	
		
	}

	
}
