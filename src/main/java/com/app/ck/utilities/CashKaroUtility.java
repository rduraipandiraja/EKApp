package com.app.ck.utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import com.app.ck.base.GetCodeSetUP;
import com.app.ck.base.WrapperMethods;
import com.aventstack.extentreports.ExtentTest;
import com.google.common.collect.ImmutableMap;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.android.nativekey.PressesKey;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class CashKaroUtility extends WrapperMethods {

	@FindBy(how = How.XPATH, using = "//android.view.View[@text='JOIN FREE']")
	MobileElement joinFreeLinkXpath ;

	String joinFreeLink 	= "//android.view.View[@text='JOIN FREE']";
	String tabSwitcherIcon 	= "com.android.chrome:id/tab_switcher_button";
	String menuButton 		= "com.android.chrome:id/menu_button"; 
	String closeAlltabsLink = "Close all tabs";
	String newTabIcon 		= "com.android.chrome:id/new_tab_button";
	String searchBox 		= "com.android.chrome:id/search_box_text";
	String urlBar 			= "com.android.chrome:id/url_bar";
	String oneLink 			= "//android.view.View[@text='AppsFlyer OneLink']";
	String buttonInstall	= "//*[contains(@text ,'INSTALL')]|//*[contains(@text ,'Install')]";
	
	String allowLinkInPermissionPopup = "com.android.packageinstaller:id/permission_allow_button";
	
	
	public static final JSONParser objJparser = new JSONParser();
	
	
	public CashKaroUtility(AppiumDriver<MobileElement> driver, ExtentTest testInfo,String udid,String port,String automationName,String deviceName,Integer systemPort){

		this.driver = driver;
		this.testInfo = testInfo;
		this.udid = udid;
		this.port = port;
		this.automationName = automationName;
		this.deviceName = deviceName;
		this.systemPort = systemPort;

	}
	
	public CashKaroUtility(String udid,String port,String automationName,String deviceName){

		this.udid = udid;
		this.port = port;
		this.automationName = automationName;
		this.deviceName = deviceName;

	}
	
	public CashKaroUtility(String udid,String port,String automationName,String deviceName,Integer systemPort){

		this.udid = udid;
		this.port = port;
		this.automationName = automationName;
		this.deviceName = deviceName;
		this.systemPort = systemPort;

	}
	
	public CashKaroUtility(AppiumDriver<MobileElement> driver, ExtentTest testInfo){


		this.driver = driver;
		this.testInfo = testInfo;
	}
	
	public AppiumDriver<MobileElement> launchChrome() {

		System.out.println("Base port is "+ this.port + " & Base udid is " + this.udid );

		//Set the Desired Capabilities for chrome browser
		DesiredCapabilities caps = new DesiredCapabilities();

		caps.setCapability("platformName", "Android");
		caps.setCapability("automationName", this.automationName);
		caps.setCapability("appPackage", "com.android.chrome");
		caps.setCapability("appActivity", "com.google.android.apps.chrome.Main");
		caps.setCapability("deviceName", this.deviceName);
		caps.setCapability("newCommandTimeout", 30000);
		caps.setCapability("udid", this.udid);
		caps.setCapability("noReset", true);
		caps.setCapability("systemPort", this.systemPort);
		caps.setCapability("ignoreUnimportantViews", true);
		caps.setCapability("skipServerInstallation", true);

		AndroidDriver<MobileElement> driver1 = null;

		try {

			driver1 = new AndroidDriver<MobileElement>(new URL("http://0.0.0.0:"+this.port+"/wd/hub"), caps);

			System.out.println("Session has been started in the port : http://0.0.0.0:"+this.port+"/wd/hub");

		} catch (MalformedURLException e) {

			System.out.println(e.getMessage());
		}

		try {

			Thread.sleep(2000);

		} catch (InterruptedException e) {

			e.printStackTrace();
			   Thread.currentThread().interrupt();
		}

		System.out.println("Base port is "+ this.port + " & Base udid is " + this.udid );

		return driver1;
	}

	public AppiumDriver<MobileElement> launchChromeWebView(AppiumDriver<MobileElement> driver)  {
		
		this.driver = driver;
		driver.closeApp();

		System.out.println("Base port is "+ this.port + " & Base udid is " + this.udid );

		//Set the Desired Capabilities for chrome browser
		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setCapability("browserName", "chrome");
		caps.setCapability("deviceName", this.deviceName);
		caps.setCapability("udid", this.udid); 
		caps.setCapability("platformName", "Android");
		caps.setCapability("automationName", this.automationName);
		caps.setCapability("newCommandTimeout", 20000);
		caps.setCapability("noReset", true);
		caps.setCapability("ignoreUnimportantViews", true);
		caps.setCapability("skipServerInstallation", true);
		caps.setCapability("appium:chromeOptions", ImmutableMap.of("w3c", false));

		AppiumDriver<MobileElement> driver1 = null;

		try {

			driver1 = new AndroidDriver<MobileElement>(new URL("http://0.0.0.0:"+this.port+"/wd/hub"), caps);

			System.out.println("Session has been started in the port : http://0.0.0.0:"+this.port+"/wd/hub");

		} catch (MalformedURLException e) {

			System.out.println(e.getMessage());
		}
		
		System.out.println("Base port is "+ this.port + " & Base udid is " + this.udid );
		this.driver = driver1;

		return driver1;
	}

	public AppiumDriver<MobileElement> launchChromeWebViewWithoutClosingApp(AppiumDriver<MobileElement> driver)  {

		this.driver = driver;
		
		System.out.println("Base port is "+ this.port + " & Base udid is " + this.udid );

		//Set the Desired Capabilities for chrome browser
		DesiredCapabilities caps = new DesiredCapabilities();

		caps.setCapability("browserName", "chrome");
		caps.setCapability("deviceName", this.deviceName);
		caps.setCapability("udid", this.udid); 
		caps.setCapability("platformName", "Android");
		caps.setCapability("automationName", this.automationName);
		caps.setCapability("newCommandTimeout", 20000);
		caps.setCapability("noReset", true);
		caps.setCapability("ignoreUnimportantViews", true);
		caps.setCapability("skipServerInstallation", true);
		caps.setCapability("appium:chromeOptions", ImmutableMap.of("w3c", false));

		AppiumDriver<MobileElement> driver1 = null;

		try {

			driver1 = new AndroidDriver<MobileElement>(new URL("http://0.0.0.0:"+this.port+"/wd/hub"), caps);

			System.out.println("Session has been started in the port : http://0.0.0.0:"+this.port+"/wd/hub");

		} catch (MalformedURLException e) {

			System.out.println(e.getMessage());
		}
		
		System.out.println("Base port is "+ this.port + " & Base udid is " + this.udid );

		return driver1;
	}

	public AppiumDriver<MobileElement> launchEarnKaroApp(AppiumDriver<MobileElement> driver) {
		
		this.driver = driver;
		driver.closeApp();

		System.out.println("Base port is "+ this.port + " & Base udid is " + this.udid  + " & System port is : "+ this.systemPort + "& Device Name is : "+ this.deviceName );


		//Set the Desired Capabilities for chrome browser
		DesiredCapabilities caps = new DesiredCapabilities();

		caps.setCapability("platformName", "Android");
		caps.setCapability("automationName", this.automationName);
		caps.setCapability("appPackage", "com.earnkaro");
		caps.setCapability("appActivity", "com.earnkaro.MainActivity");
		caps.setCapability("deviceName", this.deviceName);
		caps.setCapability("newCommandTimeout", 30000);
		caps.setCapability("udid", this.udid);
		caps.setCapability("noReset", true);
		caps.setCapability("systemPort", this.systemPort);
		caps.setCapability("ignoreUnimportantViews", true);
		caps.setCapability("skipServerInstallation", true);

		AppiumDriver<MobileElement> driver1 = null;

		try {

			driver1 = new AndroidDriver<MobileElement>(new URL("http://0.0.0.0:"+this.port+"/wd/hub"), caps);

			System.out.println("Session has been started in the port : http://0.0.0.0:"+this.port+"/wd/hub");

		} catch (MalformedURLException e) {

			System.out.println(e.getMessage());
		}

		
		System.out.println("Base port is "+ this.port + " & Base udid is " + this.udid );

		return driver1;
	}

	public AppiumDriver<MobileElement> launchEarnKaroApp() {

		System.out.println("Base port is "+ this.port + " & Base udid is " + this.udid  + " & System port is : "+ this.systemPort + "& Device Name is : "+ this.deviceName );


		//Set the Desired Capabilities for chrome browser
		DesiredCapabilities caps = new DesiredCapabilities();

		caps.setCapability("platformName", "Android");
		caps.setCapability("automationName", this.automationName);
		caps.setCapability("appPackage", "com.earnkaro");
		caps.setCapability("appActivity", "com.earnkaro.MainActivity");
		caps.setCapability("deviceName", this.deviceName);
		caps.setCapability("newCommandTimeout", 30000);
		caps.setCapability("udid", this.udid);
		caps.setCapability("noReset", true);
		caps.setCapability("systemPort", this.systemPort);
		caps.setCapability("skipServerInstallation", true);
		caps.setCapability("ignoreUnimportantViews", true);

		AndroidDriver<MobileElement> driver1 = null;

		try {

			driver1 = new AndroidDriver<MobileElement>(new URL("http://0.0.0.0:"+this.port+"/wd/hub"), caps);

			System.out.println("Session has been started in the port : http://0.0.0.0:"+this.port+"/wd/hub");

		} catch (MalformedURLException e) {

			System.out.println(e.getMessage());
		}

		
		System.out.println("Base port is "+ this.port + " & Base udid is " + this.udid );

		return driver1;
	}

	public  String generateMobileNumber() {
		
		reportStep("About to generate the Mobile number ", "INFO");

		String mobNum = "";

		try {
			mobNum = Utilities.generateRandomNumber(10); 

			reportStep("Successfully generated the Mobile number : the mobile number is "+ mobNum, "INFO");

		}catch (Exception e) {

			String exceptionMessage = e.getMessage();

			reportStep("Failed to  generate the Mobile number and get the exception message as :   "+ exceptionMessage , "FAIL");
		}

		return mobNum;


	}

	public  String generateEmail() {
		
		reportStep("About to generate the Email address witht the time stamp : ", "INFO");
		
		String email = "";

		try {
			email = Utilities.generateEmailWithTimeStamp();

			reportStep("Successfully generated the Email   : the Email  is "+ email, "INFO");
			System.out.println("Successfully generated the Email   : the Email  is "+ email);

		}catch (Exception e) {

			String exceptionMessage = e.getMessage();

			reportStep("Failed to  generate the Email and get the exception message as :   "+ exceptionMessage , "FAIL");
		}

		return email;


	}

	public String generatePaymentOTP(String mobNum) {
		
		String otp = "";

		try {
		reportStep("About to generate Payment OTP : ", "INFO");

		 otp = Utilities.getSignUPOTP(mobNum);
		
		reportStep("Successfully fetch the OTP as : "+otp, "INFO");
		
		}catch (Exception e) {
			
			e.printStackTrace();
			String exceptionMessage = e.getMessage();

			reportStep("Fetching the SignUp OTP had a problem  :   "+ exceptionMessage , "FAIL");
			
		}

		return otp;


	}

	public String cashOut(String email) {
		
		String success = "";
		
		try {
		reportStep("About to cashout for the user  : "+ email , "INFO");

		 success = Utilities.cashOut(email);
		
		reportStep("Successfully created the Cashout  ", "INFO");
		
		}catch (Exception e) {
			e.printStackTrace();
			String exceptionMessage = e.getMessage();
			reportStep("Creating the Cashout had a problem  :   "+ exceptionMessage , "FAIL");
		}

		return success;


	}
	
	public String addCashbackBonus(String email,String CashbackValue,String CashbackType) {
		
		try {

			reportStep("About to add the bonus amount as "+ CashbackValue +" to the User : " + email , "INFO");

			String status = Utilities.addCashbackBonus(email,CashbackValue,CashbackType);

			if(status.equals("success")) {

			}else {

				throw new Exception("There is some Problem with Hitting the add bounus end Point");
			}

			return "success";

		}catch (Exception e) {

			reportStep("Failed at adding the cashback bonus to the User "+ email + " Error is : "+ e.getMessage() , "FAIL");

			return "failure";
		}


		
	}

	// extract_Values_From_Json_And_Load_Internal_Variables
	public static String extractValuesFromStoreCategoryJsonAndLoadTheVariablesForStore(String strStore_Name , int intStore_Index_In_Json, String key) {
		
		String value ="";		
		
		try
		{
			// Upcasting jsonObject to JSONParser
		//	JSONObject objJsonObject 			=  (JSONObject) objJparser.parse(new FileReader(System.getProperty("user.dir") + "/input/StoreCategoryPage_JsonData.json"));

			JSONObject objJsonObject			=  (JSONObject) objJparser.parse(new InputStreamReader(new FileInputStream(System.getProperty("user.dir") + "/input/StoreCategoryPage_JsonData.json"), "UTF-8"));
			
			//Reading the array
			JSONArray objRootArray 				= (JSONArray)objJsonObject.get("root");


			//Creating Json object to fetch the exact node from the array 
			JSONObject objTitleNode 			= (JSONObject) objRootArray.get(intStore_Index_In_Json);

			//Creating Json object to fetch the key value pair values
			JSONObject objMainNode 				= (JSONObject) objTitleNode.get(strStore_Name);

			/********************************************************************* Store Details Assigning *********************************************************************************/

			// Store_One - Core details
			 value 	= 	objMainNode.get(key).toString().trim();


		}
		catch(Exception objError){
			objError.printStackTrace();
		}
        
        return value;
		/********************************************************************End of Stores Details Assigning*******************************************************************************/
	}

	public String backDateTheExitClickForGivenDate(String exitClickID,int numberOfDaysToBackDate) {

		reportStep("About to hit the end point call to back date the Exit click ", "INFO");

		String backDatedDate = Utilities.strNumber_Days_To_BackDated_Exit_Clicks(numberOfDaysToBackDate);

		String success= Utilities.backDateTheExitclick(exitClickID, backDatedDate);

		reportStep("The Exit Click for back date is : ", exitClickID + "Number of days to be back dated is : " + numberOfDaysToBackDate);

		//reportStep("The Example URL formate used to back dating the Exit click is  : https://EarnKaro.iamsavings.co.uk/GetCodeSetUP.php?page=ticket&id=LCCHKR6811662&date=2018-12-12 ", "INFO");

		return  success;


	}

	public  void closeAllTabsAndOneTabInChromeBrowser(AppiumDriver<MobileElement> driver) {
		
		clickByID(driver, tabSwitcherIcon);
		clickByID(driver, menuButton);
		clickByAccessebilityID(driver, closeAlltabsLink);
		clickByID(driver, newTabIcon);
		
	}
	
	public  void clickAndEnterURLInSearchOrTypeWebAddress(AppiumDriver<MobileElement> driver, String GetCodeSetUPUrl) {
				
		//reportStep("About to enter URL: "+GetCodeSetUPUrl+"", "INFO");
		
		clickByID(driver, searchBox);
		clickByID(driver, urlBar);
		enterTextByID(driver, urlBar, GetCodeSetUPUrl);
		((PressesKey) driver).pressKey(new KeyEvent(AndroidKey.ENTER));

		
	}
		
	public void clickJoinFreeLink(AppiumDriver<MobileElement> driver) {
		
		clickByXpath(driver, joinFreeLink);
		
	}

	public void decodeURL(String url) {
		
		reportStep("About to decode the Encoded URL - The Encoded URL is " + url, "INFO");
		
		try {
		    String decodedURL = URLDecoder.decode(url, StandardCharsets.UTF_8.name());
		    System.out.println(decodedURL);
		    reportStep("Decoded URL is "+  decodedURL, "INFO");
		    
		} catch (UnsupportedEncodingException e) {
		}
	}
		
	public void resetFBUser(String fbUser) {
		

		try {

			if (Utilities.fbAccountReset(fbUser).equals("Success")) {

				reportStep("Successfully reset the Facebook account ", "INFO");
			} else {

				reportStep("Not able to reset the Facebook account ", "FAIL");
			}
		} catch (Exception e) {

			reportStep("Not able to reset the Facebook account ", "FAIL");

		}
		
	}

	// extract_Values_From_Json_And_Load_Internal_Variables
	public static String extractValuesFromPartnerJsonAndLoadTheVariablesForPartner(String partnerName , int partnerIndex_In_Json, String key) {
		
		String value ="";		
		
		try
		{
			// Upcasting jsonObject to JSONParser
		//	JSONObject objJsonObject 			=  (JSONObject) objJparser.parse(new FileReader(System.getProperty("user.dir") + "/input/StoreCategoryPage_JsonData.json"));

			JSONObject objJsonObject			=  (JSONObject) objJparser.parse(new InputStreamReader(new FileInputStream(System.getProperty("user.dir") + "/input/PartnersPage_JsonData.json"), "UTF-8"));
			
			//Reading the array
			JSONArray objRootArray 				= (JSONArray)objJsonObject.get("root");


			//Creating Json object to fetch the exact node from the array 
			JSONObject objTitleNode 			= (JSONObject) objRootArray.get(partnerIndex_In_Json);

			//Creating Json object to fetch the key value pair values
			JSONObject objMainNode 				= (JSONObject) objTitleNode.get(partnerName);

			/********************************************************************* Store Details Assigning *********************************************************************************/

			// Store_One - Core details
			 value 	= 	objMainNode.get(key).toString().trim();


		}
		catch(Exception objError){
			objError.printStackTrace();
		}
        
        return value;
		/********************************************************************End of Stores Details Assigning*******************************************************************************/
	}

	/* generateStoreName */
	public String generateStoreName(){
		
		Date objCurrentDate = new Date();
		SimpleDateFormat objDateFormat = new SimpleDateFormat("yyyy.MM.dd.HH.MM.SSSS");
		String strDateNow = objDateFormat.format(objCurrentDate);

    	String storeName = ("A_AppiumStore_"+strDateNow);
		
		return storeName;
	}

	// extract_Values_From_Json_And_Load_Internal_Variables
	public static JSONObject retrieveParentKeyValues(String fileName, String parentKey) throws UnsupportedEncodingException, FileNotFoundException, IOException, ParseException {

		// Upcasting jsonObject to JSONParser
		JSONObject objJsonObject = (JSONObject) objJparser.parse(new InputStreamReader(new FileInputStream(System.getProperty("user.dir") + "/input/" + fileName + ".json"), "UTF-8"));

		return (JSONObject) objJsonObject.get(parentKey);

	}	
	
	//This return true, if the API call works fine without an error.
	public boolean notificationAPICall(String fcmID) {

		reportStep("About to Hit the Post API call to Push the Notification ", "INFO");

		if(Utilities.notificationPostAPICall(fcmID).equals("success")){

			reportStep("Successfully hit the Post End Point :"+GetCodeSetUP.EKAPIBASEURI+"v1/notification/app_group_notification and Get "
					+ " Expected respone. ", "PASS");

			return true;

		} else {

			reportStep("Failed to  hit the Post End Point :"+GetCodeSetUP.EKAPIBASEURI+"v1/notification/app_group_notification and then failed to get "
					+ " the  Expected respone . ", "FAIL");

			return false;
		}

	}

	//This return true, if the API call works fine without an error.
	public boolean notifationAPICall_NormalPriority(String fcmID) {

		reportStep("About to Hit the Post API call to Push the Notification ", "INFO");

		if(Utilities.notificationPostAPICall_NormalPriority(fcmID).equals("success")){

			reportStep("Successfully hit the Post End Point :"+GetCodeSetUP.EKAPIBASEURI+"v1/notification/app_group_notification and Get "
					+ " Expected respone. ", "PASS");

			return true;

		} else {

			reportStep("Failed to  hit the Post End Point :"+GetCodeSetUP.EKAPIBASEURI+"v1/notification/app_group_notification and then failed to get "
					+ " the  Expected respone . ", "FAIL");

			return false;
		}



	}

	//This return true, if the API call works fine without an error.
	public boolean notifationAPICall_WithRedirectionalURLMyEarningsPage(String fcmID) {

		reportStep("About to Hit the Post API call to Push the Notification WIth Redirectional URL to MyEarnings Page ", "INFO");

		if(Utilities.notificationPostAPICall_WithRedirectionalURLToMyEarningsPage(fcmID).equals("success")){

			reportStep("Successfully hit the Post End Point :"+GetCodeSetUP.EKAPIBASEURI+"v1/notification/app_group_notification and Get "
					+ " Expected respone. ", "PASS");

			return true;

		} else {

			reportStep("Failed to  hit the Post End Point :"+GetCodeSetUP.EKAPIBASEURI+"v1/notification/app_group_notification and then failed to get "
					+ " the  Expected respone . ", "FAIL");

			return false;
		}



	}

	//This return true, if the API call works fine without an error.
	public boolean notifationAPICall_WithRedirectionalURLMyEarningsPage_NormalPriority(String fcmID) {

		reportStep("About to Hit the Post API call to Push the Notification WIth Redirectional URL to MyEarnings Page ", "INFO");

		if(Utilities.notificationPostAPICall_WithRedirectionalURLToMyEarningsPage_NormalPriority(fcmID).equals("success")){

			reportStep("Successfully hit the Post End Point :"+GetCodeSetUP.EKAPIBASEURI+"v1/notification/app_group_notification and Get "
					+ " Expected respone. ", "PASS");

			return true;

		} else {

			reportStep("Failed to  hit the Post End Point :"+GetCodeSetUP.EKAPIBASEURI+"v1/notification/app_group_notification and then failed to get "
					+ " the  Expected respone . ", "FAIL");

			return false;
		}



	}

	//This return true, if the API call works fine without an error.
	public boolean notificataionAPICall_WithoutRedirectionURL_WithoutBigImage_WithoutLargeIcon(String fcmID) {

		reportStep("About to Hit the Post API call to Push the Notification which is - without Big image, large icon and Redirectional URL ", "INFO");

		if(Utilities.notificationPostAPICall_WithoutLargeIcon_WithoutBigImage_WithOutReDirectURL(fcmID).equals("success")){

			reportStep("Successfully hit the Post End Point :"+GetCodeSetUP.EKAPIBASEURI+"v1/notification/app_group_notification and Get "
					+ " Expected respone. ", "PASS");

			return true;

		} else {

			reportStep("Failed to  hit the Post End Point :"+GetCodeSetUP.EKAPIBASEURI+"v1/notification/app_group_notification and then failed to get "
					+ " the  Expected respone . ", "FAIL");

			return false;
		}

	}

	//This return true, if the API call works fine without an error.
	public boolean notificataionAPICall_WithoutRedirectionURL_WithoutBigImage_WithoutLargeIcon_NormalPriority(String fcmID) {

		reportStep("About to Hit the Post API call to Push the Notification which is - without Big image, large icon and Redirectional URL ", "INFO");

		if(Utilities.notificationPostAPICall_WithoutLargeIcon_WithoutBigImage_WithOutReDirectURL_NormalPriority(fcmID).equals("success")){

			reportStep("Successfully hit the Post End Point :"+GetCodeSetUP.EKAPIBASEURI+"v1/notification/app_group_notification and Get "
					+ " Expected respone. ", "PASS");

			return true;

		} else {

			reportStep("Failed to  hit the Post End Point :"+GetCodeSetUP.EKAPIBASEURI+"v1/notification/app_group_notification and then failed to get "
					+ " the  Expected respone . ", "FAIL");

			return false;
		}

	}

	//This return true, if the API call works fine without an error.
	public boolean notificataionAPICall_WithoutBigImage(String fcmID) {

		reportStep("About to Hit the Post API call to Push the Notification Without Big Image ", "INFO");

		if(Utilities.notificationPostAPICall_WithOutBigImage(fcmID).equals("success")){

			reportStep("Successfully hit the Post End Point :"+GetCodeSetUP.EKAPIBASEURI+"v1/notification/app_group_notification and Get "
					+ " Expected respone. ", "PASS");

			return true;

		} else {

			reportStep("Failed to  hit the Post End Point :"+GetCodeSetUP.EKAPIBASEURI+"v1/notification/app_group_notification and then failed to get "
					+ " the  Expected respone . ", "FAIL");

			return false;
		}

	}

	//This return true, if the API call works fine without an error.
	public boolean notificataionAPICall_WithoutBigImage_NormalPriority(String fcmID) {

		reportStep("About to Hit the Post API call to Push the Notification Without Big Image ", "INFO");

		if(Utilities.notificationPostAPICall_WithOutBigImage_NormalPriority(fcmID).equals("success")){

			reportStep("Successfully hit the Post End Point :"+GetCodeSetUP.EKAPIBASEURI+"v1/notification/app_group_notification and Get "
					+ " Expected respone. ", "PASS");

			return true;

		} else {

			reportStep("Failed to  hit the Post End Point :"+GetCodeSetUP.EKAPIBASEURI+"v1/notification/app_group_notification and then failed to get "
					+ " the  Expected respone . ", "FAIL");

			return false;
		}

	}

	public String getFCMID() {

		reportStep("About ot get the FCM ID ", "INFO");

		String fireBaseToken = Utilities.fetchFireBaseTokenFCMID(driver);

		reportStep("FireBase Token Value : ", fireBaseToken);

		return	fireBaseToken ;

	}
	
	public String getFCMID(String userEmail) {

		System.out.println("The User Email  is : " + userEmail);

		String url = GetCodeSetUP.FCMGETCODE+ userEmail ;

		System.out.println("To get the FCM Id we hit the end Point -  " + url);
		reportStep("To get the FCM Id we hit the end Point -  " , url );

		String strResponse = "";

		//Using the OKHTTP client hit the get API call

		OkHttpClient client = new OkHttpClient();
		Request request = new Request.Builder().url(url).build();

		try {
			
			Response response = client.newCall(request).execute(); 

			strResponse = response.body().string();

			System.out.println("Got the response as : "+ strResponse );
			reportStep("Response is " ,  strResponse);
			
			JSONParser parser = new JSONParser();
			JSONObject json = (JSONObject) parser.parse(strResponse);
			
			String jsonValue = json.get("fcm_id").toString();
			strResponse = jsonValue;
			
			System.out.println("Json fcm value is "+ jsonValue);

		}catch(Exception e){

			e.printStackTrace();
			
		}

		return strResponse;

	}
	
	//This return true, if the API call works fine without an error.
	public boolean notifationAPICall_WithTemplate(String templateId,String fcmID) {

		reportStep("About to Hit the Post API call to With Template ", "INFO");

		if(Utilities.notificationPostAPICall_WithTemplate(templateId,fcmID).equals("success")) {

			reportStep("Successfully hit the Post End Point :"+GetCodeSetUP.EKAPIBASEURI+"v1/notification/notification and Get "
					+ " Expected respone. ", "PASS");

			return true;

		} else {

			reportStep("Failed to  hit the Post End Point :"+GetCodeSetUP.EKAPIBASEURI+"v1/notification/notification and then failed to get "
					+ " the  Expected respone . ", "FAIL");

			return false;
		}



	}

	public void removeApp(AppiumDriver<MobileElement> driver,String appPackage) {
		
		reportStep("About to uninstall EarnKaro App from the device", "INFO");
		
		try {
		
		driver.removeApp(appPackage); 
		
		reportStep("Successfully uninstall EarnKaro app from the device", "PASS");
		
		} catch (Exception e) {
			
			reportStep("Failed to uninstall EarnKaro app from the device", "FAIL");
		}
		
	}

	public void clickOneLink_ReEngagement(AppiumDriver<MobileElement> driver) {

		reportStep("About to click on the Onelink for Deferred deep Linking", "INFO");

		if (clickByXpath(driver, oneLink)) {

			reportStep("Successfully clicked on the OneLink", "PASS");

		} else {

			reportStep("FAiled to click on the One LInk ", "FAIL");
		}

		if (isElementLocatedByXpathPresent(driver, "//android.widget.TextView[@text='Amazon India']")) {

			reportStep("ReEnagement -Is Working fine", "PASS");

		} else {
			reportStep("ReEnagement is not working - It is not redirected to the Amazon Store", "FAIL");

		}

	}

	public void clickOneLink(AppiumDriver<MobileElement> driver) {
		
		reportStep("About to click on the Onelink for Deferred deep Linking", "INFO");
		
		if (clickByXpath(driver, oneLink)) {
			
			reportStep("Successfully clicked on the OneLink", "PASS");
			
		} else {
			
			reportStep("FAiled to click on the One LInk ", "FAIL");
		}
		
	}
	
	public void playStoreRedirectionVerification(AppiumDriver<MobileElement> driver) {

		if (isElementLocatedByXpathPresent(driver, buttonInstall)) {

			reportStep("Redirected to playstore", "PASS");

		} else {
			reportStep("Not redirected to playstore", "FAIL");

		}

	}

	public AppiumDriver<MobileElement> launchEarnKaroApp(String appPath) {

		System.out.println("Base port is "+ this.port + " & Base udid is " + this.udid  + " & System port is : "+ this.systemPort + "& Device Name is : "+ this.deviceName );


		//Set the Desired Capabilities for chrome browser
		DesiredCapabilities caps = new DesiredCapabilities();

		caps.setCapability("platformName", "Android");
		caps.setCapability("automationName", this.automationName);
		caps.setCapability("app", appPath);
		caps.setCapability("deviceName", this.deviceName);
		caps.setCapability("newCommandTimeout", 30000);
		caps.setCapability("udid", this.udid);
		//caps.setCapability("noReset", true);
		caps.setCapability("systemPort", this.systemPort);

		AppiumDriver<MobileElement> driver1 = null;

		try {

			driver1 = new AndroidDriver<MobileElement>(new URL("http://0.0.0.0:"+this.port+"/wd/hub"), caps);

			System.out.println("Session has been started in the port : http://0.0.0.0:"+this.port+"/wd/hub");

		} catch (MalformedURLException e) {

			System.out.println(e.getMessage());
		}


		System.out.println("Base port is "+ this.port + " & Base udid is " + this.udid );

		return driver1;
	}

	public void allowLinkInPermissionPopup(AppiumDriver<MobileElement> driver) {

		reportStep("About to click Allow button in Permission popup", "INFO");

		try {

			if (clickByID(driver, allowLinkInPermissionPopup)){

				reportStep("Clicked on the Allow link on the permission popup ", "INFO");

			} else {

				reportStep("Not able to click on the Allow link on the permission popup ", "FAIL");
			}

		} catch (Exception e) {

			reportStep(e.getMessage(), "INFO");
			reportStep("Permission popup is not shown", "FAIL");
		}
	}



}
