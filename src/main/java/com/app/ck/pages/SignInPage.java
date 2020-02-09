package com.app.ck.pages;

import org.aspectj.lang.reflect.CatchClauseSignature;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.app.ck.base.WrapperMethods;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;


public class SignInPage  extends WrapperMethods{ 


	//static int counter =0;

	//Constructor call to initialize the driver object
	public SignInPage(AppiumDriver<MobileElement> driver, ExtentTest testInfo) {

		WebDriverWait wait = new WebDriverWait(driver, 30);

		this.driver = driver;
		this.testInfo = testInfo;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);

		reportStep("Waiting for the SignPage", "INFO");

		if(Integer.parseInt(apiLevel)>22) {

			try {

				reportStep("About to click on the Allow on the permission popup in GetStarted page", "INFO");

				allowThePermissionPopup(); //It handles the Permission popUp

			} catch (Exception e) {

				System.out.println("No permission popup comes in the GetStarted page");

				reportStep("No permission popup comes in the GetStarted page", "INFO");

			}

			try {

				reportStep("About to click on the Allow on the permission popup in GetStarted page", "INFO");

				allowThePermissionPopup(); //It handles the Permission popUp

			} catch (Exception e) {

				System.out.println("No permission popup comes in the GetStarted page");

				reportStep("No permission popup comes in the GetStarted page", "INFO");

			}

		}


		try {
			wait.until(ExpectedConditions.visibilityOf(userName));
			reportStep("Successfully landed on the SignIn page", "PASS");

		}catch(Exception e) {

			reportStep(e.getMessage(), "INFO");
			reportStep("Not able to land on the SignIn page", "FAIL");
		}


	}


	public SignInPage(AppiumDriver<MobileElement> driver, ExtentTest testInfo,int signInpopUpCounter) {

		System.out.println("Sign In Popup counter "+ signInpopUpCounter);

		this.driver = driver;
		this.testInfo = testInfo;

		reportStep("Second time onwards this constructor is being called to avoid Waiting for permission Popup  ", "INFO");
		reportStep(signInpopUpCounter + " th time Entering into the SignIn page ", "INFO");

		PageFactory.initElements(new AppiumFieldDecorator(driver), this);

		reportStep("Waiting for the GetStarted button Element ..!!", "INFO");

		WebDriverWait wait = new WebDriverWait(driver, 15);

		try {

			wait.until(ExpectedConditions.visibilityOf(userName));
			reportStep("Successfully landed on the SignIn page", "PASS");

		} catch (Exception e) {

			reportStep("Not able to land on the SignIn page( In Second Catch block )", "FAIL");

		}
	}



	//Page Elements :-
	//Login Page Error Page :-
	private String xpathPleaseEnterTheEmailErrorMsg 	= "//android.widget.TextView[@text ='Please enter the Email ID' ]|//android.widget.TextView[@text = 'Please enter a valid Email ID']";


	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='SIGN IN']") 
	MobileElement signInText;
	@FindBy(how = How.XPATH, using = "//*[@text='Login I Share I Earn']") 
	MobileElement loginShareEarnText;
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='OR']") 
	MobileElement orText;
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='Your Email']")
	MobileElement yourEmailPlaceholder;
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='Your Password']")
	MobileElement yourPasswordPlaceholder;
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='Forgot Password?']") 
	MobileElement forgotPasswordlink;
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[contains(@text,'New to EarnKaro?')]")
	MobileElement newToEarnKaroJoinFreeText;
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text = 'Password must be at least 6 characters long']")
	MobileElement errorMessageLessNumberOfChar;
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='SIGN IN WITH FACEBOOK']")
	MobileElement signInWithFacebookButton;
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@content-desc=\"txt_signin_to_signup\"]")
	MobileElement joinFreeLink;
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='SIGN UP']")
	MobileElement joinFreeLinkText;
	@FindBy(how = How.XPATH, using = "//android.widget.EditText[@content-desc='et_signin_email']") 
	MobileElement userName;
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='SIGN IN WITH EMAIL']") 
	MobileElement signInButton;
	@FindBy(how = How.XPATH, using = "//android.widget.EditText[@content-desc='et_signin_password']") 
	MobileElement password;
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='https://cashkaro.iamsavings.co.uk']") 
	MobileElement intermediatePageURL;
	@FindBy(how = How.ID, using = "com.android.packageinstaller:id/permission_allow_button") 
	MobileElement allowLinkInPermissionPopup;
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='SIGN IN WITH EMAIL']") 
	MobileElement signInWithEmailButton;
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text = 'Your Email']") 
	MobileElement yourEmailErrorMsg;
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text = 'Please enter the Email ID']") 
	MobileElement pleaseEnterTheEmailErrorMsg;
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text = 'Your Password']") 
	MobileElement yourPasswordErrorMsg;
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text = 'Please enter the Password']") 
	MobileElement pleaseEnterThePasswordErrorMsg;
	@FindBy(how = How.XPATH, using = "(//android.view.ViewGroup)[10]|(//android.view.View)[10]") 
	MobileElement backButton;
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[contains(@text,'Authentication Failed')]") 
	MobileElement errorAuthenticationFailed;

	//Google Suggestions ;
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='Sign in with']") 
	MobileElement gSuggestion_signInWithText;
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='Cashkaro Development']") 
	MobileElement gSuggestion_Name;
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[contains(@text,'@gmail.com')]") 
	MobileElement gSuggestion_Gmail;

	public SignInPage validateSignInPage() {

		validateTheElementPresence(signInText);
		validateTheElementPresence(loginShareEarnText);
		validateTheElementPresence(orText);
		validateTheElementPresence(yourEmailPlaceholder);
		validateTheElementPresence(yourPasswordPlaceholder);

		scrollFromDownToUpinApp();
		scrollFromDownToUpinApp();

		validateTheElementPresence(forgotPasswordlink);
		validateTheElementPresence(newToEarnKaroJoinFreeText);
		validateTheElementPresence(joinFreeLinkText);

		return this;
	}
	//Enter the Login Username 
	public SignInPage enterUserName() {

		String validUserEmail = testdata.get(0).get("TC001_ValidLoginEmail");

		reportStep("Entering the userEmail in the login page ", "INFO");

		if(clickOnNoneOFTheAbove(userName)) {

			reportStep("Successfully clicked on the None Of the Above link in the popup ", "INFO");
		}else {

			reportStep("Fail to click on the None Of the Above link in the popup ", "INFO");
		}

		if(enterText(userName, validUserEmail)) {

			reportStep(" UserEmail "+validUserEmail + " entered successfully  " , "PASS");

		}

		return this;

	}

	//Enter the Login Username 
	public SignInPage enterUserName(String userEmail) {


		reportStep("Entering the userEmail in the login page ", "INFO");

		if(clickOnNoneOFTheAbove(userName)) {

			reportStep("Successfully clicked on the None Of the Above link in the popup ", "INFO");
		}else {

			reportStep("Fail to click on the None Of the Above link in the popup ", "INFO");
		}

		if(enterText(userName, userEmail)) {

			reportStep(" UserEmail "+userEmail + " entered successfully  " , "PASS");

		}

		return this;

	}

	public SignInPage enterPassword(){

		String validPassword = testdata.get(0).get("TC001_ValidLoginPassword");

		if(enterValue(password, validPassword)) {
			reportStep("Password "+ validPassword +" entered successfully", "PASS");
		}
		else {
			reportStep("Password "+password+" enter failed", "FAIL");
		}
		return this;
	}

	public SignInPage enterPassword(String userpassword){


		if(enterValue(password, userpassword)) {

			reportStep("Password "+ userpassword +" entered successfully", "PASS");
		}
		else {
			reportStep("Password : "+userpassword+" enter failed", "FAIL");
		}
		return this;
	}

	public SignInPage clickSignInButtonForFailure(){

		hideKeyboard();

		if(click(signInButton)) {

			reportStep("Sign In Button clicked successfully", "PASS");
		} else {
			reportStep("Sign In button click failed", "FAIL");
		}
		return this;
	}

	public HomePage clickSignInButtonForSuccess(){

		hideKeyboard();

		if(click(signInButton)) {

			reportStep("Successfully clicked On SignIn button ", "PASS");

		}else {

			reportStep("Failed to click on SignIn Button ", "FAIL");
		}

		return new HomePage(driver,testInfo);
	}

	public void validateNetworkErrorsDuringSignIn() {
		
		try {
			
			click(signInButton);
			//Turned Off the Wifi
			toggleWifi();
			reportStep("Successfully clicked On SignIn button ", "Info");
			reportStep("About to TurnOff the Wifi", "Info");
		
			new SignInPage(driver, testInfo).
			clickSignInButtonForFailure();
			SignInPage objSignInPage = new SignInPage(driver, testInfo);
			
			//Wifi - OFF to ON
			toggleWifi();
			objSignInPage.
			clickSignInButtonForSuccess();
			
			reportStep("Successfully Turned On the Wifi During SignIn ", "Pass");
		
			
		} catch (Exception e) {
			
			reportStep("Failed to click on SignIn Button ", "FAIL");
			
		}
	
	}

	public void clickSignInButtonForSuccess_RedirectTo_Deeplink(){

		hideKeyboard();

		if(clickAfterWait(signInButton)) {

			reportStep("Successfully clicked On SignIn button ", "PASS");

		}else {

			reportStep("Failed to click on SignIn Button ", "FAIL");
		}

	}

	public SignInPage allowThePermissionPopup() {

		System.out.println("About to click onn the Allow on the permissionn popup");


		if(click(allowLinkInPermissionPopup)) {

			reportStep("Cliked on the Allow link "+ allowLinkInPermissionPopup + " on the permission popup at the Login page", "INFO");

		}else {

			reportStep("Not able to click on the Allow link on the permission popup at the Login  page", "INFO");
		}

		return this;



	}

	public SignInPage clickOnSignInWithEmailForFailure() {

		hideKeyboard();

		reportStep("About Clicking on the SignInWithEmail button without entering the username and password", "INFO");

		scrollFromDownToUpinApp();

		if(click(signInWithEmailButton)) {

			reportStep("Clicked on the SignIn With Email button ", "PASS");

		}else {

			reportStep("Not clicked on the SignIn With Email button  ", "FAIL");

		}

		return this;

	}

	public SignInPage validateUserNameFieldErrorMessage() {

		reportStep("Without entering the value clicking on the Sign in with email button - should show the error \"\r\n" + 
				"				+ \"message in  the User name and password field- ", "INFO");

		String expectedErrorMessageOnUserNameField = getTestData(0, "TC001_InValidLoginEmail");

		String actualErrorMessageOnUserNameFiled = getText(yourEmailErrorMsg);	

		validateTheActualValueWithExpectedValue(actualErrorMessageOnUserNameFiled, expectedErrorMessageOnUserNameField);

		expectedErrorMessageOnUserNameField = getTestData(0, "TC001_InValidLoginEmail_2");

		actualErrorMessageOnUserNameFiled = getTextByXpath(xpathPleaseEnterTheEmailErrorMsg);	


		if(isElementLocatedByXpathPresent(xpathPleaseEnterTheEmailErrorMsg)) {

			reportStep("Successfully validated the Error Message in User Name field ", "PASS");
		}else {

			reportStep("Failed - Username filed Error Message is Wrong ", "FAIL");
		}



		return this;
	}

	public SignInPage validatePasswordFieldErrorMessage() {

		reportStep("Without entering the value clicking on the Sign in with email button - should show the error \"\r\n" + 
				"+ \"message in  the User name and password field- ", "INFO");

		String expectedErrorMessageOnPasswordField = getTestData(0, "TC001_InValidLoginPassword");

		String actualErrorMessageOnPasswordFiled = getText(yourPasswordErrorMsg);	

		validateTheActualValueWithExpectedValue(actualErrorMessageOnPasswordFiled, expectedErrorMessageOnPasswordField);

		testInfo.log(Status.INFO, "Successfully Validated the error message on the user name field - 1 ");

		expectedErrorMessageOnPasswordField = getTestData(0, "TC001_InValidLoginPassword_2");

		actualErrorMessageOnPasswordFiled = getText(pleaseEnterThePasswordErrorMsg);	

		validateTheActualValueWithExpectedValue(actualErrorMessageOnPasswordFiled, expectedErrorMessageOnPasswordField);

		return this;
	}

	public EKOnboardingPage clickOnBackButton() {

		reportStep("About click on the back button at the Sign in page", "INFO");

		//		if(click(backButton)) {
		//
		//			reportStep("Successfully clicked on the back button at the SignIn Page", "INFO");
		//		}else {
		//
		//			reportStep("Not clicked on the back button at the SignIn page", "FAIL");
		//		}

		driver.navigate().back();

		return new EKOnboardingPage(driver, testInfo);
	}

	public EKOnboardingPage clickOnBackButton_SecondTimeNavigationToTheGetStartedPage() {

		reportStep("About click on the back button at the Sign in page", "INFO");

		if(click(backButton)) {

			reportStep("Successfully clicked on the back button at the SignIn Page", "INFO");
		}else {

			reportStep("Not clicked on the back button at the SignIn page", "FAIL");
		}


		return new EKOnboardingPage(driver, testInfo,0);
	}


	//Enter the invalid Username 
	public SignInPage enterInvalidUserName() {

		String inValiduserEmail = getTestData(0, "TC001_InValidLoginUserEmail");

		reportStep("Entering the invalid userName as "+inValiduserEmail , "INFO");

		if(clickOnNoneOFTheAbove(userName)) {

			reportStep("Successfully clicked on the None Of the Above link in the popup ", "INFO");
		}else {

			reportStep("Fail to click on the None Of the Above link in the popup ", "INFO");
		}

		if(enterText(userName, inValiduserEmail)) {

			reportStep("Entered the invalid userName as "+inValiduserEmail + " Successfully", "PASS");
		}else {

			reportStep("Failed to Enter the invalid userName as "+inValiduserEmail , "FAIL");
		}

		return this;

	}

	//Enter the invalid Username 
	public SignInPage enterInvalidPassword() {

		String inValidPassword = getTestData(0, "TC001_InValidPassword");

		reportStep("Entering the invalid Password as "+inValidPassword , "INFO");

		if(enterText(password, inValidPassword)) {

			reportStep("Entered the invalid Password as "+inValidPassword + " Successfully", "PASS");
		}else {

			reportStep("Failed to Enter the invalid Password as "+ inValidPassword , "FAIL");
		}

		return this;

	}

	public SignInPage validateAuthenticationFailedErrorMessage() {

		reportStep("When the UserEmail or Password is wrong, Authentication failed error message should"
				+ " be diplayed.","INFO");

		String actual = getText(errorAuthenticationFailed);

		validateTheActualValueWithExpectedValue(actual, getTestData(0, "TC001_ErrorMsgAuthenticationFailed"));

		return this;
	}
	//Enter the Login Password
	public SignInPage enterPasswordwithAsNull() {

		reportStep("Enterinig the  blank password", "INFO");

		if(enterText(password, "")) {

			reportStep("Successfully entered the blank password", "PASS");

		}else {
			reportStep("Not entered the blank password", "FAIL");
		}

		return this;

	}		

	//Enter the Login Password
	public SignInPage enterLessNumberOfCharacterToThePassword() {

		System.out.println("Entering the less number of characters to the Password field and then validating the error message");

		reportStep("Entering the less number of characters to the Password field and"
				+ " then validating the error message","INFO");


		if(enterText(password, getTestData(0, "TC001_LessLengthPassword"))){

			reportStep("Entered the less lenth password "+password +"to the password field", "PASS");

		}else {

			reportStep("Failed to Enter the less lenth password "+password +"to the password field", "FAIL");
		}

		return this;

	}

	public SignInPage validatePasswordFieldLessNumberOfCharachetersError() {

		validateTheElementPresence(errorMessageLessNumberOfChar);

		return this;

	}

	public SignInPage enterNumericIntoUserNameField() {

		testInfo.log(Status.INFO, "Entering the numberic characters into the UserName field to verify the error message");

		if(clickOnNoneOFTheAbove(userName)) {

			reportStep("Successfully clicked on the None Of the Above link in the popup ", "INFO");
		}else {

			reportStep("Fail to click on the None Of the Above link in the popup ", "INFO");
		}
		enterText(userName,testdata.get(0).get("TC001_InvalidNumeric"));

		return this;


	}

	public FacebookPage clickOnSignInWithFacebook(){

		reportStep("About to click on the SignInWithFacebook button ", "INFO");

		if(click(signInWithFacebookButton)) {

			reportStep("Cliked on the SigninWithFacebook button successfully ", "PASS");
		}else {
			reportStep("Failed to Clike on the SigninWithFacebook button  ", "FAIL");

		}

		return new FacebookPage(driver, testInfo);
	}

	public JoinFreePage clickOnJoinFreeLink() {

		reportStep("About to click on the JoinFree link at the signIn page ", "INFO");

		scrollFromDownToUpinApp();

		if(click(joinFreeLink)){

			reportStep("Successfully clicked on the JoinFree  link at the signIn page ", "PASS");

		}else {

			reportStep("Failed to click on the JoinFree  link at the signIn page ", "FAIL");
		}

		return new JoinFreePage(driver, testInfo,0);

	}

	public ForgotPasswordPage clickOnForgotPasswordLink() {

		reportStep("About to click on the Forgot password link in the SignIn page ", "INFO");

		if(click(forgotPasswordlink)) {

			reportStep("Successfully clicked on the Forgot password link", "PASS");

		}else {

			reportStep("Failed to click on the Forgot password link in the SignIn page ", "FAIL");
		}

		return new ForgotPasswordPage(driver, testInfo);

	}

	public SignInPage validateGoogleSuggestionInSignInPage() {

		reportStep("About to validate the Google suggestion Popup in Login page ", "INFO");

		if (clickAfterWait(userName)){

			reportStep("Successfully clicked on the element to Handle the Google Email or Mobile number suggesstion popup", "PASS");
		}else {

			reportStep("Failed to click on the web element where the Google suggestion popup appears", "INFO");

		}
		validateTheElementPresence(gSuggestion_signInWithText);
		validateTheElementPresence(gSuggestion_Name);
		validateTheElementPresence(gSuggestion_Gmail);

		reportStep("About to get the Gmail Id configured with the system ", "INFO");

		String gSuggestionEMail = getText(gSuggestion_Gmail);

		gSuggestion_Gmail = driver.findElement(By.xpath("//android.widget.TextView[@text='"+gSuggestionEMail+"']"));

		if (click(gSuggestion_Gmail)) {

			reportStep("Successfully clicked on Google suggestions -  Mail ", "PASS");
		}else {

			reportStep("Failed to click on the Google Suggestion - Mail ", "FAIL");
		}

		isElementLocatedByXpathPresent("//android.widget.EditText[@text='"+ gSuggestionEMail+ "']");

		return this;
	}




}







