package com.app.ck.pages.admin;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.app.ck.base.WrapperMethods;
import com.aventstack.extentreports.ExtentTest;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class EmailMasterBrowserPage extends WrapperMethods {

	public EmailMasterBrowserPage(AppiumDriver<MobileElement> driver, ExtentTest testInfo) {

		this.driver = driver;
		this.testInfo = testInfo;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);

	}


	//Element initialization :
	
	@FindBy(how = How.ID, using = "sendNotificationDowndown") 
	MobileElement sendNotificationButton;
	
	@FindBy(how = How.ID, using = "WOTsendNotificationModal") 
	MobileElement withOutTemplateNotification;
	@FindBy(how = How.ID, using = "sendNotificationModal") 
	MobileElement withTemplateNotification;
	
	@FindBy(how = How.ID, using = "app_notification_title") 
	MobileElement appNotificationTitle;
	
	@FindBy(how = How.ID, using = "app_notification_image_url") 
	MobileElement appImageURL;
	
	@FindBy(how = How.ID, using = "app_notification_body") 
	MobileElement appNotificationBody;
	
	@FindBy(how = How.ID, using = "app_notification_big_text") 
	MobileElement appNotificationBigText;
	
	@FindBy(how = How.ID, using = "app_notification_redirect_url") 
	MobileElement appNotificationRedirectURL;
	
	@FindBy(how = How.ID, using = "app_large_icon") 
	MobileElement appLargeIcon;
	
	@FindBy(how = How.ID, using = "app_notification_sub_text") 
	MobileElement appNotificationSubText;
	
	@FindBy(how = How.XPATH, using = "(//button[text()='Submit'])[1]") 
	MobileElement submitButton;
	
	@FindBy(how = How.XPATH, using = "(//button[text()='Confirm'])[1]|(//*[@text ='Confirm'])[1]") 
	MobileElement confirmButton;
	
	@FindBy(how = How.XPATH, using = "//button[@id='PushNotification']") 
	MobileElement withTemplateSendNotificationTemplate;
	
	@FindBy(how = How.XPATH, using = "(//label[@class='m-radio'])[2]") 
	MobileElement normalPriorityRadioBtn;

	
	String notificationTemplateSelectionXpath = "//select[@id='notificationTemplates']";
	String nameTitleSelectXpath = "//select[@name='NAME']";
	
	
	
	
	//Method creation : 
	
	/*It clicks on Send Notification button*/
	public void clickOnSendNotification() {

		reportStep("About to click on Send Notification button ", "INFO");

		if(clickAfterWait(sendNotificationButton)) {

			reportStep("Successfully clicked on the Send notification Button ", "PASS");

		}else {

			reportStep("Failed to click onn the Send Notificationn Buttonn ", "FAIL");
		}
	}

	/*It clicks on Without Notification Template*/
	public void clickOnWithoutNotificationTemplate() {

		reportStep("About to click on With out Notification Template", "INFO");

		if(clickAfterWait(withOutTemplateNotification)) {

			reportStep("Successfully clicked on the Without Notification template ", "PASS");

		}else {
			reportStep("Failed to click on the Without Notification template ", "FAIL");
		}
	}

	/*Enter app Notification Title*/
	/*EnterAppNotificationTitle*/
	public void enterAppNotificationTitle(String value) {

		reportStep("About to enter App Notification Title as :  "+ value, "INFO");

		if(enterTextInChrome(appNotificationTitle,value)) {

			reportStep("Successfully enter App Notification Title as " +  value , "PASS");

		}else {
			
			reportStep("Failed to enter App notification Title as : "+ value, "FAIL");

		}
	}

	public void clickOnNormalPriority() {
		
		reportStep("About to click on Normal Priority radio button ", "INFO");
		
		if(jsClickUsingXpath(normalPriorityRadioBtn)) {
			
			reportStep("Successfully clicked on the Normal Priority Radio button ", "PASS");
		} else {
			
			reportStep("Failed to click on the Normal Priority Radio Button", "FAIL");
		}
	}
	
	/*Enter Image URL*/
	/*EnterImageURL*/
	public void enterImageURL(String value) {

		reportStep("About to enter the Image URL " + value, "INFO");

		if(enterTextInChrome(appImageURL,value)) {
			
			reportStep("Successfully entered the Image URL " + value, "PASS");

		}else {

			reportStep("Failed to enter the Image URL " + value, "FAIL");
		}
	}

	/*Enter App Notification Body*/
	/*EnterAppNotificationBody*/
	public void enterAppNotificationBody(String value) {

		reportStep("About to enter the  App notification Body  " + value, "INFO");

		if(enterTextInChrome(appNotificationBody,value)) {

			reportStep("Successfully enter the App Notification body Text " + value, "PASS");

		}else {
			
			reportStep("Failed to enter the App notification Body Text " + value, "FAIL");

		}
	}

	/*Enter BigText*/
	/*enter BigText*/
	public void enterBigText(String value) {

		reportStep("About to enter the Big Text " + value, "INFO");

		if(enterTextInChrome(appNotificationBigText,value)) {

			reportStep("Successfully entered the BigText " + value, "PASS");

		}else {
			
			reportStep("Failed to enter the BigText " + value, "FAIL");

		}
	}

	/*Etner redirectional URL*/
	/*Enter Notification Redirection URL*/
	public void enterRedirectionalURL(String value) {

		reportStep("About to enter the Redirectional URL " + value, "INFO");

		if(enterTextInChrome(appNotificationRedirectURL,value)) {

			reportStep("Successfully enter the Re Directional URL " + value, "PASS");

		}else {
			 
			reportStep("Failed to enter the Redirectional URL" + value, "FAIL");

		}
	}

	/*Enter Large Icon*/
	/*Enter large icon URL*/
	public void enterLargeIcon(String value) {

		reportStep("About to enter the Large icon URL " + value, "INFO");

		if(enterTextInChrome(appLargeIcon,value)) {

			reportStep("Successfully entered the Large icon URL" + value, "PASS");

		}else {

			reportStep("Failed to enter the Large Icon URL " + value, "FAIL");

		}
	}

	/*Enter Sub Text*/
	/*Enter Sub Text*/
	public void enterSubText(String value) {

		reportStep("About to enter the SubText  " + value, "INFO");

		if(enterTextInChrome(appNotificationSubText,value)) {

			reportStep("Successfully entered the SubText " + value, "PASS");

		}else {

			reportStep("Failed to enter the subText as : " + value, "FAIL");

		}
	}

	/*Click on Submit*/
	/*Click on  Submit*/
	public void clickOnSubmit() {

		reportStep("About to click on Submit button ", "INFO");

		if(clickAfterWait(submitButton)) {

			reportStep("Successfully clicked on the Submit button ", "PASS");

		}else {
			clickAfterWait(submitButton);
			reportStep("Failed to click on the Submit button ", "INFO");

		}
	}
	
	/*Click on  confirm */
	public void clickOnConfirmButton() {

		reportStep("About to click on Submit button ", "INFO");

		if(clickAfterWait(confirmButton)) {

			reportStep("Successfully clicked on the Submit button ", "PASS");

		}else {
			
			reportStep("Failed to click on the Submit button ", "FAIL");

		}
	}

	/*It clicks on With Notification Template*/
	public void clickOnWithNotificationTemplate() {

		reportStep("About to click on With  Notification Template", "INFO");

		if(clickAfterWait(withTemplateNotification)) {

			reportStep("Successfully clicked on the With Notification template ", "PASS");

		}else {
			reportStep("Failed to click on the With Notification template ", "FAIL");
		}
	}

	public void selectHighPriorityWithNotificationTemplate() {
		
		reportStep("About to select the WithNotification Template High Priority (With all the fields Entered)", "INFO");
		
		if(select_Dropdown(driver, notificationTemplateSelectionXpath, getTestData(17, "WithNotificationTemplate_HighPriority"))) {
			
			reportStep("Successfully selected the Notification Template ->"+ getTestData(17, "WithNotificationTemplate_HighPriority"), "PASS");
			
		}else {
			
			reportStep("Failed to select the Notification Template ->"+ getTestData(17, "WithNotificationTemplate_HighPriority"), "FAIL");
			
		}
	}

	public void selectHighPriorityWithNotificationTemplate_WithoutRedirctURL_WithoutLargeIcon_WithoutBigImage() {

		reportStep("About to select the WithNotification Template without Redirectional URL , without large icon, without big image ", "INFO");

		if(select_Dropdown(driver, notificationTemplateSelectionXpath, getTestData(17, "WithNotificationTemplate_HighPriority_WithoutRedirectionURLBigImageLLargeIcon"))) {

			reportStep("Successfully selected the Notification Template ->"+ getTestData(17, "WithNotificationTemplate_HighPriority_WithoutRedirectionURLBigImageLLargeIcon"), "PASS");

		}else {

			reportStep("Failed to select the Notification Template ->"+ getTestData(17, "WithNotificationTemplate_HighPriority_WithoutRedirectionURLBigImageLLargeIcon"), "FAIL");

		}
	}

	public void selectHighPriorityWithNotificationTemplate_WithoutRedirctURL() {

		reportStep("About to select the WithNotification Template without Redirectional URL ", "INFO");

		if(select_Dropdown(driver, notificationTemplateSelectionXpath, getTestData(17, "WithNotificationTemplate_HighPriority_WithoutRedirectURL"))) {

			reportStep("Successfully selected the Notification Template ->"+ getTestData(17, "WithNotificationTemplate_HighPriority_WithoutRedirectURL"), "PASS");

		}else {

			reportStep("Failed to select the Notification Template ->"+ getTestData(17, "WithNotificationTemplate_HighPriority_WithoutRedirectURL"), "FAIL");

		}
	}

	public void selectHighPriorityWithNotificationTemplate_RedirectionalURLAtDifferentPage() {

		reportStep("About to select the Notification Template with Redirectional URL to at different page of the app ", "INFO");

		if(select_Dropdown(driver, notificationTemplateSelectionXpath, getTestData(17, "WithNotificationTemplate_HighPriority_RedirectionURLAtDifferentPage"))) {

			reportStep("Successfully selected the Notification Template ->"+ getTestData(17, "WithNotificationTemplate_HighPriority_RedirectionURLAtDifferentPage"), "PASS");

		}else {

			reportStep("Failed to select the Notification Template ->"+ getTestData(17, "WithNotificationTemplate_HighPriority_RedirectionURLAtDifferentPage"), "FAIL");

		}
	}

	public void selectNormalPriorityWithNotificationTemplate() {

		if(select_Dropdown(driver, notificationTemplateSelectionXpath, getTestData(17, "WithNotificationTemplate_NormalPriority"))) {

			reportStep("Successfully selected the Notification Template ->"+ getTestData(17, "WithNotificationTemplate_NormalPriority"), "PASS");

		}else {

			reportStep("Failed to select the Notification Template ->"+ getTestData(17, "WithNotificationTemplate_NormalPriority"), "FAIL");

		}
	}

	public void selectSiteTitleAsUserName() {

		reportStep("About to selecte the Site Title as :" +  getTestData(17, "DropDownValue_UserName"), "INFO");

		if(select_Dropdown(driver, nameTitleSelectXpath, getTestData(17, "DropDownValue_UserName"))) {

			reportStep("Successfully selected the Site Title value as : "+ getTestData(17, "DropDownValue_UserName"), "PASS");

		} else {

			reportStep("Failed to select the Site Title value as : "+ getTestData(17, "DropDownValue_UserName") , "FAIL");
		}

	}

	public void clickOnSendNotificatonButtonForWithTemplate() {

		reportStep("About to click on the Send Notification button for With Template", "INFO");

		if(clickAfterWait(withTemplateSendNotificationTemplate)) {

			reportStep("Sucessfully clicked on the Send Notification button  , for With Template Notification ", "PASS");

		}  else {

			reportStep("Fail to  clicked on the Send Notification button  , for With Template Notification ", "FAIL");

		}
	}



	
	
	
}
