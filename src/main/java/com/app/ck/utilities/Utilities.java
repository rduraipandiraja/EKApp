package com.app.ck.utilities;

import static io.restassured.RestAssured.given;

import java.io.File;
import java.security.SecureRandom;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.TimeZone;

import javax.annotation.CheckForNull;

import org.openqa.selenium.logging.LogEntry;
import org.testng.Assert;

import com.app.ck.base.GetCodeSetUP;
import com.app.ck.base.WrapperMethods;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Utilities extends WrapperMethods {
	
	
	private static Random rand = new Random();
	
	public static void createNewFolderForEveryNewDay() {

		// Set HTML reporting file location
		Date objNewDateFolder = new Date();
		SimpleDateFormat dateFormat_Folder = new SimpleDateFormat("yyyy_MM_dd");
		File file = new File(
				System.getProperty("user.dir") + "/results/" + dateFormat_Folder.format(objNewDateFolder));
		boolean value = file.mkdir();
		
		if(value) {
			System.out.println("New file has been created for the report");
		}

	}
	
	public  static String today_dd_MMM_YYYY() {

		SimpleDateFormat dateFormatGmt = new SimpleDateFormat("dd-MMM-yyyy");
		dateFormatGmt.setTimeZone(TimeZone.getTimeZone("IST"));
		Calendar objCalender  		= Calendar.getInstance();
		Date currentDate			= objCalender.getTime();
		String strNumber_Days_To_BackDated_Exit_Clicks  = dateFormatGmt.format(currentDate);
		System.out.println(strNumber_Days_To_BackDated_Exit_Clicks);
		
		return strNumber_Days_To_BackDated_Exit_Clicks.trim(); 

	}
	
	public  static String today_dd_MMM_YYYY_ISTZone() {

		SimpleDateFormat dateFormatGmt = new SimpleDateFormat("dd-MMM-yyyy");
		dateFormatGmt.setTimeZone(TimeZone.getTimeZone("IST"));
		Calendar objCalender  		= Calendar.getInstance();
		Date currentDate			= objCalender.getTime();
		String strNumber_Days_To_BackDated_Exit_Clicks  = dateFormatGmt.format(currentDate);
		System.out.println(strNumber_Days_To_BackDated_Exit_Clicks);
		
		return strNumber_Days_To_BackDated_Exit_Clicks.trim(); 

	}

	public  static String today_dd_MMMM_YYYY_ISTZone() {

		SimpleDateFormat dateFormatGmt = new SimpleDateFormat("dd-MMMM-yyyy");
		dateFormatGmt.setTimeZone(TimeZone.getTimeZone("IST"));
		Calendar objCalender  		= Calendar.getInstance();
		Date currentDate			= objCalender.getTime();
		String strNumber_Days_To_BackDated_Exit_Clicks  = dateFormatGmt.format(currentDate);
		System.out.println(strNumber_Days_To_BackDated_Exit_Clicks);
		
		return strNumber_Days_To_BackDated_Exit_Clicks.trim(); 

	}

	public  static String today_dd_MMMM_YYYY() {

		SimpleDateFormat dateFormatGmt = new SimpleDateFormat("dd-MMMM-yyyy");
		dateFormatGmt.setTimeZone(TimeZone.getTimeZone("UTC"));
		Calendar objCalender  		= Calendar.getInstance();
		Date currentDate			= objCalender.getTime();
		String strNumber_Days_To_BackDated_Exit_Clicks  = dateFormatGmt.format(currentDate);
		System.out.println(strNumber_Days_To_BackDated_Exit_Clicks);
		
		return strNumber_Days_To_BackDated_Exit_Clicks.trim(); 

	}
	
	public  static String today_dd_MMMM_YYYY_IST() {

		SimpleDateFormat dateFormatGmt = new SimpleDateFormat("dd-MMMM-yyyy");
		dateFormatGmt.setTimeZone(TimeZone.getTimeZone("IST"));
		Calendar objCalender  		= Calendar.getInstance();
		Date currentDate			= objCalender.getTime();
		String strNumber_Days_To_BackDated_Exit_Clicks  = dateFormatGmt.format(currentDate);
		System.out.println(strNumber_Days_To_BackDated_Exit_Clicks);
		
		return strNumber_Days_To_BackDated_Exit_Clicks.trim(); 

	}
	
	public  static String customBackDate_dd_MMMM_YYYY(int numberOfDaysToBeBackDated) {

		SimpleDateFormat dateFormatGmt = new SimpleDateFormat("dd-MMMM-yyyy");
		dateFormatGmt.setTimeZone(TimeZone.getTimeZone("IST"));

		Calendar objCalender  = Calendar.getInstance();

		objCalender.add(Calendar.DATE, - numberOfDaysToBeBackDated);
		Date currentDate = objCalender.getTime();

		String backDatedDate  = dateFormatGmt.format(currentDate);

		System.out.println(backDatedDate);
		
		return backDatedDate;

	}
	
	public  static String customBackDate_dd_MM_YYYY(int numberOfDaysToBeBackDated) {

		SimpleDateFormat dateFormatGmt = new SimpleDateFormat("dd-MM-yyyy");
		dateFormatGmt.setTimeZone(TimeZone.getTimeZone("IST"));

		Calendar objCalender  = Calendar.getInstance();

		objCalender.add(Calendar.DATE, - numberOfDaysToBeBackDated);
		Date currentDate = objCalender.getTime();

		String backDatedDate  = dateFormatGmt.format(currentDate);

		System.out.println(backDatedDate);
		
		return backDatedDate;

	}
	
	public static String datestamp() {

		//Created Current date :
		Date objCurrentDate = new Date();
		SimpleDateFormat objDateFormat = new SimpleDateFormat("yyyy.MM.dd.HH.MM.SSSS");
		String strDatenow = objDateFormat.format(objCurrentDate);
		return strDatenow;

	}
	
	public static String datestamp(String storeType) {

		// // created Current date
		Date objCurrentDate = new Date();
		SimpleDateFormat objDateFormat = new SimpleDateFormat("yyyy.MM.dd.HH.MM.SSSS");
		String strDatenow = objDateFormat.format(objCurrentDate);
		
		int intRandNumber = rand.nextInt(50) + 1;

		String strCurrentDate = strDatenow + intRandNumber;
		
		String storeName = storeType + strCurrentDate;
		
		return storeName;

	}
	
	public static String generateEmailWithTimeStamp() {
		
		int intRandNumber = rand.nextInt(999);

		String strCurrentDate = Utilities.datestamp() + intRandNumber;
		
		String email = strCurrentDate +"@appiumtest.com";
		return email;

	}
	
	public static String generateRandomNumber(int lengthOfTheRequiredNumber) {
		
		lengthOfTheRequiredNumber = lengthOfTheRequiredNumber-1;

		String randomNum = "";

		String numbers = "6789";
		
		StringBuffer buffer = new StringBuffer();
		
		char[] c =null;

			c = new char[1];
			for (int i = 0; i < 1; i++) {
				c[i] = numbers.charAt(rand.nextInt(numbers.length()));
				
				buffer.append(c[i]);
				
				 randomNum = buffer.toString();
			}

		String characters = "0123456789";
		String randomNumber = "";
		StringBuffer buffer2 = new StringBuffer();
		char[] text = new char[lengthOfTheRequiredNumber];
		for (int i = 0; i < lengthOfTheRequiredNumber; i++) {
			text[i] = characters.charAt(rand.nextInt(characters.length()));
			buffer2.append(text[i]);
			randomNumber = buffer2.toString();

		}
		
		return randomNum+randomNumber;

	}

	public static String getSignUPOTP(String mobileNumber) {

		System.out.println("The mobile number is : " + mobileNumber);

		String url = GetCodeSetUP.SIGNUPGETCODE+ mobileNumber ;

		System.out.println("Sign up OTP URL is " + url);

		String strResponse = "";

		//Using the OKHTTP client hit the get API call

		OkHttpClient client = new OkHttpClient();
		Request request = new Request.Builder().url(url).build();

		try {
			
			Response response = client.newCall(request).execute(); 

			strResponse = response.body().string();

			System.out.println("Got the response as : "+ strResponse );

		}catch(Exception e){

			e.printStackTrace();


		}

		JsonParser parser = new JsonParser(); // Creating the JsonParser object
		JsonObject rootObj = parser.parse(strResponse).getAsJsonObject(); 
		//Passing the String response to the json parse method of <JsonParser Class> and get as joson object

		String status = rootObj.get("status").getAsString();
		System.out.println("The status from the response is "+ status);

		String message = rootObj.get("message").getAsString();
		System.out.println("The message from the response is " + message);//this is OTP value
		System.out.println("The OTP is : "+ message);

		return message;

	}

	public static String backDateTheExitclick(String exitClickID,String YYYY_MM_DD) {

		System.out.println("The Exit click ID is : " + exitClickID);

		String url = GetCodeSetUP.BACKDATEEXITCLICK+ exitClickID + "&date=" + YYYY_MM_DD ; // eg for date formate : 2018-12-12

		System.out.println(" Exit click Back date URL " + url);

		String strResponse = "";

		//Usiing the OKHTTP client hit the get API call

		OkHttpClient client = new OkHttpClient();
		Request request = new Request.Builder().url(url).build();

		try {
			Response response = client.newCall(request).execute(); 

			strResponse = response.body().string();

			System.out.println("Got the response as : "+ strResponse );

		}catch(Exception e){

			e.printStackTrace();
		}


		return "success";

	}

	public static String fbAccountReset(String email) {

		String url = GetCodeSetUP.FBACCOUNTRESET + email ;
		System.out.println(url);

		String strResponse = "";

		//Usiing the OKHTTP client hit the get API call

		OkHttpClient client = new OkHttpClient();
		Request request = new Request.Builder().url(url).build();

		try {
			Response response = client.newCall(request).execute(); 

			strResponse = response.body().string();

			System.out.println("Got the response as : "+ strResponse );

		}catch(Exception e){

			e.printStackTrace();


		}

		JsonParser parser = new JsonParser(); // Creating the JsonParser object
		JsonObject rootObj = parser.parse(strResponse).getAsJsonObject(); 
		//Passing the String response to the json parse method of <JsonParser Class> and get as joson object

		String status = rootObj.get("status").getAsString();
		System.out.println("The status from the response is "+ status);

		//String message = rootObj.get("message").getAsString();
		//System.out.println("The message from the response is " + message);//this is OTP value
		System.out.println("successfully reset the facebook account , cool  "+ status);


		return status;

	}

	public static String getAPICall(String URL) {


		System.out.println("The Response is + "+ RestAssured.get(URL).asString());

		return RestAssured.get(URL).asString();


	}

	public static String currentDate_DD_MMM_YYYY() {

		Calendar objCalender		= Calendar.getInstance();
		Date currentDate 			= objCalender.getTime();
		SimpleDateFormat objDateFormating = new SimpleDateFormat("dd-MMM-yyyy");
		String strCurrentDate 		= objDateFormating.format(currentDate);

		System.out.println(strCurrentDate);

		return strCurrentDate;


	}

	public static String extractOnlyDigitFromString(String str){

		String digits= str.replaceAll("[^0-9]", "");

		System.out.println("String : "+ str +" digits from the string as : "+ digits);
		return digits;

	}

	public static String setOneDayBeforeCurrentDate() {

		//Set oneday_BeforeDateValue 
		Calendar objCalender		= Calendar.getInstance();
		objCalender.add(Calendar.DATE, -1);

		Date currentDate 			= objCalender.getTime();

		DateFormat objDateFormating = new SimpleDateFormat("yyyy-MM-dd"); 
		objDateFormating.setTimeZone(TimeZone.getTimeZone("UTC"));

		String setBefore1dayDate 		= objDateFormating.format(currentDate);

		return setBefore1dayDate;

	}

	public static String setFourDaysAfterCurrentDate() {

		//Set fourdays_AfterDateValue 
		SimpleDateFormat dateFormatUTC = new SimpleDateFormat("yyyy-MM-dd");
		dateFormatUTC.setTimeZone(TimeZone.getTimeZone("UTC"));

		Calendar objCalender		= Calendar.getInstance();
		objCalender.add(Calendar.DATE, +4);

		Date currentDate 			= objCalender.getTime();

		String setAfter4daysDate 	= dateFormatUTC.format(currentDate);

		return setAfter4daysDate;

	}

	public static String setCurrentDate() {

		Calendar objCalender		= Calendar.getInstance();
		objCalender.add(Calendar.DATE, 0);

		Date currentDate 			= objCalender.getTime();

		DateFormat objDateFormating = new SimpleDateFormat("yyyy-MM-dd"); 
		objDateFormating.setTimeZone(TimeZone.getTimeZone("UTC"));

		String currentdaysDates 		= objDateFormating.format(currentDate);

		return currentdaysDates;

	}

	public static String addCashbackBonus(String email,String CashbackValue,String CashbackType) {

		//Cashback type should be Cashback or Rewards
		//Cashback value should be in integer eg : 120,1005 etc

		String url = GetCodeSetUP.ADDBONUS+email+"&cashback="+CashbackValue+"&cashback_type="+CashbackType;
		System.out.println("Add bonus URL is "+url);

		String strResponse = "";

		//Usiing the OKHTTP client hit the get API call

		OkHttpClient client = new OkHttpClient();
		Request request = new Request.Builder().url(url).build();

		try {
			Response response = client.newCall(request).execute(); 

			strResponse = response.body().string();

			System.out.println("Got the response as : "+ strResponse );
			
			return "success";

		}catch(Exception e){

			e.printStackTrace();
			
			return "failure";


		}

		
		

	}

	public static String cashOut(String email) {

		String url = GetCodeSetUP.CASHOUT+email; 

		System.out.println("Cashout URL is  : "+ url);

		String strResponse = "";

		//Usiing the OKHTTP client hit the get API call

		OkHttpClient client = new OkHttpClient();
		Request request = new Request.Builder().url(url).build();

		try {
			Response response = client.newCall(request).execute(); 

			strResponse = response.body().string();

			System.out.println("Got the response as : "+ strResponse );

		}catch(Exception e){

			e.printStackTrace();


		}

		return "success";
	}

	public static int showRandomInteger(int aStart, int aEnd, Random aRandom){
		if (aStart > aEnd) {
			throw new IllegalArgumentException("Start cannot exceed End.");
		}
		//get the range, casting to long to avoid overflow problems
		long range = (long)aEnd - (long)aStart + 1;
		// compute a fraction of the range, 0 <= frac < range
		long fraction = (long)(range * aRandom.nextDouble());
		int randomNumber =  (int)(fraction + aStart); 
		return randomNumber;
	}

	public static int randomNumbers_for_2digits() {

		int int2Digits_RandomNumber = showRandomInteger(15,99,rand);
		return int2Digits_RandomNumber;
	}

	public static String strNumber_Days_To_BackDated_Exit_Clicks(int numberOfDaysToBackDate) {
		
		SimpleDateFormat dateFormatGmt = new SimpleDateFormat("yyyy-MM-dd");
		dateFormatGmt.setTimeZone(TimeZone.getTimeZone("UTC"));

		Calendar objCalender  = Calendar.getInstance();
		
		objCalender.add(Calendar.DATE, - numberOfDaysToBackDate);
		Date currentDate = objCalender.getTime();

		String strNumber_Days_To_BackDated_Exit_Clicks  = dateFormatGmt.format(currentDate);
		
		//System.out.println(strNumber_Days_To_BackDated_Exit_Clicks);
		
		return strNumber_Days_To_BackDated_Exit_Clicks;
		
	}
	
	public static String generic_AheadDate_dd_MMM_yyyy(int intAheadDays) {

		Calendar objCalender		= Calendar.getInstance();
		objCalender.add(Calendar.DATE, +intAheadDays);
		Date currentDate 			= objCalender.getTime();
		DateFormat objDateFormating = new SimpleDateFormat("dd-MMM-yyyy");
		objDateFormating.setTimeZone(TimeZone.getTimeZone("UTC"));
		String strCurrentDate 		= objDateFormating.format(currentDate);

		return strCurrentDate;
	}

	public static String currentMonthYear() {
		
		String[] monthName = { "January", "February", "March", "April", "May", "June", "July",
				"August", "September", "October", "November", "December" };

		Calendar cal = Calendar.getInstance();
		String month = monthName[cal.get(Calendar.MONTH)];
		int yr = cal.getInstance().get(cal.YEAR);
		String year = Integer.toString(yr);
		
		String monthYear = month +" "+ year;

		return monthYear;


	}
	
	public static String previousMonthCurrentYear(int index) {
		
		String[] monthName = { "January", "February", "March", "April", "May", "June", "July",
				"August", "September", "October", "November", "December" };
		
		Calendar cal = Calendar.getInstance();
		String month = monthName[cal.get(Calendar.MONTH) - index];
		int yr = cal.getInstance().get(cal.YEAR);
		String year = Integer.toString(yr);
		
		String monthYear = month +" "+ year;

		return monthYear;


	}

	public static int getTheNumberOfCharPresenceInString(String setOfcharacters,String requiredChar) {
		
		int counter = 0;
		
		String str = setOfcharacters;
		
		for(int i = 0; i<str.length();i++) {
			
			
			if(String.valueOf(str.charAt(i)).contains(requiredChar)) {
				
				counter ++;
			}
			
		}
		
		System.out.println(counter); 
		return counter;

		}

	public static String referralLinkGetCode(String userId ,String username) {

		String referralLinkGetCodeSetUP = GetCodeSetUP.REFERRALLINK+userId+"&username="+username;
		
		System.out.println("referralLinkGetCodeSetUP: "+referralLinkGetCodeSetUP);
		
		return referralLinkGetCodeSetUP;
		

	}

	public static  int fetchTheCurrentDate() {

		SimpleDateFormat dateFormatGmt = new SimpleDateFormat("dd");

		dateFormatGmt.setTimeZone(TimeZone.getTimeZone("UTC"));

		Calendar objCalender  = Calendar.getInstance();

		Date currentDate = objCalender.getTime();

		String strNumber_Days_To_BackDated_Exit_Clicks  = dateFormatGmt.format(currentDate);

		return Integer.parseInt(strNumber_Days_To_BackDated_Exit_Clicks) ;

	}

	/*******************************************************************/
	/****************  Notification Reusability  Without Template*********/
	/*******************************************************************/
	
	public static String notificationPostAPICall(String fcmID) {

		String postString = "{\r\n\"data\":{\r\n \"type\" : \"send_push_notification\",\r\n \"attributes\" : {\r\n \"fcm_id\" : {\"ios\" :[], \"android\" :[\""+fcmID+"\"]}, \r\n \"app_notification_title\" : \"Notification Title\",\r\n \"app_priority\" : \"high\",\r\n \"app_notification_redirect_url\" : \"earnkaro://stores/ajio\",\r\n \"app_notification_image_url\" : \"https://askopinion.com/images/Files/UserFiles/posting/lrg/2017/8/cash-karo-nbn.png\",\r\n \"app_large_icon\" : \"https://dummyimage.com/72x72/0a6a73/303bdb.png&text=Test+Image\",\r\n \"app_notification_body\" : \"Notification Body\",\r\n \"app_notification_sub_text\" : \"Notification SubText\",\r\n \"app_notification_big_text\" : \"Big Text\"\r\n \r\n \r\n }\r\n}\r\n}\r\n"; 
		
		String notificationPostURL = GetCodeSetUP.EKAPIBASEURI + "v1/notification/app_group_notification";

		// Setting up the multiple header values into Headers Class object
		final Header header1 = new Header("Accept", "application/vnd.api+json" );
		final Header header2 = new Header("Content-Type".trim(),"application/vnd.api+json");
		Headers headers = new Headers(header1, header2);

		// Status Validation for the POST request
		io.restassured.response.Response response = 
				given().
				config(RestAssured.config().encoderConfig(io.restassured.config.EncoderConfig.encoderConfig().appendDefaultContentCharsetToContentTypeIfUndefined(false))).
				headers(headers).
				body(postString.trim()).
				when().
				post(notificationPostURL.trim());
		System.out.println(" Response : "+ response.asString());
		System.out.println(" respnse : "+ response.path("data.attributes.message"));

		Assert.assertEquals("Android App Notification Send Successfully.", response.path("data.attributes.message").toString().trim());

		return "success";

	}
	
	public static String notificationPostAPICall_NormalPriority(String fcmID) {

		//String postString = "{\r\n\"data\":{\r\n \"type\" : \"send_push_notification\",\r\n \"attributes\" : {\r\n \"fcm_id\" : \""+fcmID+"\",\r\n \"app_notification_title\" : \"Notification Title\",\r\n \"app_priority\" : \"normal\",\r\n \"app_notification_redirect_url\" : \"earnkaro://stores/ajio\",\r\n \"app_notification_image_url\" : \"https://askopinion.com/images/Files/UserFiles/posting/lrg/2017/8/cash-karo-nbn.png\",\r\n \"app_large_icon\" : \"https://dummyimage.com/72x72/0a6a73/303bdb.png&text=Test+Image\",\r\n \"app_notification_body\" : \"Notification Body\",\r\n \"app_notification_sub_text\" : \"Notification SubText\",\r\n \"app_notification_big_text\" : \"Big Text\"\r\n \r\n \r\n }\r\n}\r\n}\r\n"; 
		
		String postString = "{\r\n\"data\":{\r\n \"type\" : \"send_push_notification\",\r\n \"attributes\" : {\r\n \"fcm_id\" : {\"ios\" :[], \"android\" :[\""+fcmID+"\"]},\r\n \"app_notification_title\" : \"Notification Title\",\r\n \"app_priority\" : \"normal\",\r\n \"app_notification_redirect_url\" : \"earnkaro://stores/ajio\",\r\n \"app_notification_image_url\" : \"https://askopinion.com/images/Files/UserFiles/posting/lrg/2017/8/cash-karo-nbn.png\",\r\n \"app_large_icon\" : \"https://dummyimage.com/72x72/0a6a73/303bdb.png&text=Test+Image\",\r\n \"app_notification_body\" : \"Notification Body\",\r\n \"app_notification_sub_text\" : \"Notification SubText\",\r\n \"app_notification_big_text\" : \"Big Text\"\r\n \r\n \r\n }\r\n}\r\n}\r\n";
		String notificationPostURL = GetCodeSetUP.EKAPIBASEURI + "v1/notification/app_group_notification";
		
		String nesw = "{\r\n  \"data\": {\r\n\"type\": \"send_push_notification\",\r\n\"attributes\": {\r\n\"fcm_id\": {\r\n\t\t\"ios\": [],\r\n\t\t\"android\": [\""+fcmID+"\"]\r\n\t},\r\n\"app_notification_title\" : \"Testing\",\r\n\"app_priority\" : \"high\",\r\n\"app_notification_title\": \"Testing\",\r\n\"time_to_live\": \"Testing\",\r\n\"app_notification_redirect_url\": \"Testing\",\r\n\"app_notification_image_url\": \"Testing\",\r\n\"app_large_icon\": \"Testing\",\r\n\"app_notification_body\": \"Testing\",\r\n\"app_notification_sub_text\": \"Testing\",\r\n\"app_notification_big_text\": \"Testing\"\r\n}\r\n  }\r\n}";

		// Setting up the multiple header values into Headers Class object
		final Header header1 = new Header("Accept", "application/vnd.api+json" );
		final Header header2 = new Header("Content-Type".trim(),"application/vnd.api+json");
		Headers headers = new Headers(header1, header2);

		// Status Validation for the POST request
		io.restassured.response.Response response = 
				given().
				config(RestAssured.config().encoderConfig(io.restassured.config.EncoderConfig.encoderConfig().appendDefaultContentCharsetToContentTypeIfUndefined(false))).
				headers(headers).
				body(postString.trim()).
				when().
				post(notificationPostURL.trim());
		System.out.println(" Response : "+ response.asString());
		System.out.println(" respnse : "+ response.path("data.attributes.message"));

		Assert.assertEquals("Android App Notification Send Successfully.", response.path("data.attributes.message").toString().trim());

		return "success";

	}
	
	public static String notificationPostAPICall_WithOutBigImage(String fcmID) {

		//String postString = "{\r\n\"data\":{\r\n \"type\" : \"send_push_notification\",\r\n \"attributes\" : {\r\n \"fcm_id\" : \""+fcmID+"\",\r\n \"app_notification_title\" : \"Notification Title\",\r\n \"app_priority\" : \"high\",\r\n \"app_notification_redirect_url\" : \"earnkaro://stores/ajio\",\r\n  \"app_large_icon\" : \"https://dummyimage.com/72x72/0a6a73/303bdb.png&text=Test+Image\",\r\n \"app_notification_body\" : \"Notification Body\",\r\n \"app_notification_sub_text\" : \"Notification SubText\",\r\n \"app_notification_big_text\" : \"Big Text\"\r\n \r\n \r\n }\r\n}\r\n}\r\n"; 
		String postString = "{\r\n\"data\":{\r\n \"type\" : \"send_push_notification\",\r\n \"attributes\" : {\r\n \"fcm_id\" : {\"ios\" :[], \"android\" :[\""+fcmID+"\"]},\r\n \"app_notification_title\" : \"Notification Title\",\r\n \"app_priority\" : \"high\",\r\n \"app_notification_redirect_url\" : \"earnkaro://stores/ajio\",\r\n  \"app_large_icon\" : \"https://dummyimage.com/72x72/0a6a73/303bdb.png&text=Test+Image\",\r\n \"app_notification_body\" : \"Notification Body\",\r\n \"app_notification_sub_text\" : \"Notification SubText\",\r\n \"app_notification_big_text\" : \"Big Text\"\r\n \r\n \r\n }\r\n}\r\n}\r\n";
		String notificationPostURL = GetCodeSetUP.EKAPIBASEURI + "v1/notification/app_group_notification";

		// Setting up the multiple header values into Headers Class object
		final Header header1 = new Header("Accept", "application/vnd.api+json" );
		final Header header2 = new Header("Content-Type".trim(),"application/vnd.api+json");
		Headers headers = new Headers(header1, header2);

		// Status Validation for the POST request
		io.restassured.response.Response response = 
				given().
				config(RestAssured.config().encoderConfig(io.restassured.config.EncoderConfig.encoderConfig().appendDefaultContentCharsetToContentTypeIfUndefined(false))).
				headers(headers).
				body(postString.trim()).
				when().
				post(notificationPostURL.trim());

		System.out.println(" Response : "+ response.asString());
		System.out.println(" respnse : "+ response.path("data.attributes.message"));

		Assert.assertEquals("Android App Notification Send Successfully.", response.path("data.attributes.message").toString().trim());

		return "success";

	}
	
	public static String notificationPostAPICall_WithOutBigImage_NormalPriority(String fcmID) {

		//String postString = "{\r\n\"data\":{\r\n \"type\" : \"send_push_notification\",\r\n \"attributes\" : {\r\n \"fcm_id\" : \""+fcmID+"\",\r\n \"app_notification_title\" : \"Notification Title\",\r\n \"app_priority\" : \"normal\",\r\n \"app_notification_redirect_url\" : \"earnkaro://stores/ajio\",\r\n  \"app_large_icon\" : \"https://dummyimage.com/72x72/0a6a73/303bdb.png&text=Test+Image\",\r\n \"app_notification_body\" : \"Notification Body\",\r\n \"app_notification_sub_text\" : \"Notification SubText\",\r\n \"app_notification_big_text\" : \"Big Text\"\r\n \r\n \r\n }\r\n}\r\n}\r\n"; 
		
		String postString = "{\r\n\"data\":{\r\n \"type\" : \"send_push_notification\",\r\n \"attributes\" : {\r\n \"fcm_id\" : {\"ios\" :[], \"android\" :[\""+fcmID+"\"]},\r\n \"app_notification_title\" : \"Notification Title\",\r\n \"app_priority\" : \"normal\",\r\n \"app_notification_redirect_url\" : \"earnkaro://stores/ajio\",\r\n  \"app_large_icon\" : \"https://dummyimage.com/72x72/0a6a73/303bdb.png&text=Test+Image\",\r\n \"app_notification_body\" : \"Notification Body\",\r\n \"app_notification_sub_text\" : \"Notification SubText\",\r\n \"app_notification_big_text\" : \"Big Text\"\r\n \r\n \r\n }\r\n}\r\n}\r\n";
		String notificationPostURL = GetCodeSetUP.EKAPIBASEURI  + "v1/notification/app_group_notification";

		// Setting up the multiple header values into Headers Class object
		final Header header1 = new Header("Accept", "application/vnd.api+json" );
		final Header header2 = new Header("Content-Type".trim(),"application/vnd.api+json");
		Headers headers = new Headers(header1, header2);

		// Status Validation for the POST request
		io.restassured.response.Response response = 
				given().
				config(RestAssured.config().encoderConfig(io.restassured.config.EncoderConfig.encoderConfig().appendDefaultContentCharsetToContentTypeIfUndefined(false))).
				headers(headers).
				body(postString.trim()).
				when().
				post(notificationPostURL.trim());

		System.out.println(" Response : "+ response.asString());
		System.out.println(" respnse : "+ response.path("data.attributes.message"));

		Assert.assertEquals("Android App Notification Send Successfully.", response.path("data.attributes.message").toString().trim());

		return "success";

	}

	public static String notificationPostAPICall_WithOutRedirectedURL(String fcmID) {

		//String postString = "{\r\n\"data\":{\r\n \"type\" : \"send_push_notification\",\r\n \"attributes\" : {\r\n \"fcm_id\" : \""+fcmID+"\",\r\n \"app_notification_title\" : \"Notification Title\",\r\n \"app_priority\" : \"high\",\r\n\"app_notification_image_url\" : \"https://askopinion.com/images/Files/UserFiles/posting/lrg/2017/8/cash-karo-nbn.png\",\r\n \"app_large_icon\" : \"https://dummyimage.com/72x72/0a6a73/303bdb.png&text=Test+Image\",\r\n \"app_notification_body\" : \"Notification Body\",\r\n \"app_notification_sub_text\" : \"Notification SubText\",\r\n \"app_notification_big_text\" : \"Big Text\"\r\n \r\n \r\n }\r\n}\r\n}\r\n"; 
		String postString = "{\r\n\"data\":{\r\n \"type\" : \"send_push_notification\",\r\n \"attributes\" : {\r\n \"fcm_id\" : {\"ios\" :[], \"android\" :[\""+fcmID+"\"]},\r\n \"app_notification_title\" : \"Notification Title\",\r\n \"app_priority\" : \"high\",\r\n\"app_notification_image_url\" : \"https://askopinion.com/images/Files/UserFiles/posting/lrg/2017/8/cash-karo-nbn.png\",\r\n \"app_large_icon\" : \"https://dummyimage.com/72x72/0a6a73/303bdb.png&text=Test+Image\",\r\n \"app_notification_body\" : \"Notification Body\",\r\n \"app_notification_sub_text\" : \"Notification SubText\",\r\n \"app_notification_big_text\" : \"Big Text\"\r\n \r\n \r\n }\r\n}\r\n}\r\n";
		String notificationPostURL = GetCodeSetUP.EKAPIBASEURI + "v1/notification/app_group_notification";

		// Setting up the multiple header values into Headers Class object
		final Header header1 = new Header("Accept", "application/vnd.api+json" );
		final Header header2 = new Header("Content-Type".trim(),"application/vnd.api+json");
		Headers headers = new Headers(header1, header2);

		// Status Validation for the POST request
		io.restassured.response.Response response = 
				given().
				config(RestAssured.config().encoderConfig(io.restassured.config.EncoderConfig.encoderConfig().appendDefaultContentCharsetToContentTypeIfUndefined(false))).
				headers(headers).
				body(postString.trim()).
				when().
				post(notificationPostURL.trim());

		System.out.println(" Response : "+ response.asString());
		System.out.println(" respnse : "+ response.path("data.attributes.message"));

		Assert.assertEquals("Android App Notification Send Successfully.", response.path("data.attributes.message").toString().trim());

		return "success";

	}

	public static String notificationPostAPICall_WithOutLargeIcon(String fcmID) {
		
		//String postString = "{\r\n\"data\":{\r\n \"type\" : \"send_push_notification\",\r\n \"attributes\" : {\r\n \"fcm_id\" : \""+fcmID+"\",\r\n \"app_notification_title\" : \"Notification Title\",\r\n \"app_priority\" : \"high\",\r\n \"app_notification_redirect_url\" : \"earnkaro://stores/ajio\",\r\n \"app_notification_image_url\" : \"https://askopinion.com/images/Files/UserFiles/posting/lrg/2017/8/cash-karo-nbn.png\",\r\n\"app_notification_body\" : \"Notification Body\",\r\n \"app_notification_sub_text\" : \"Notification SubText\",\r\n \"app_notification_big_text\" : \"Big Text\"\r\n \r\n \r\n }\r\n}\r\n}\r\n"; 
		
		String postString = "{\r\n\"data\":{\r\n \"type\" : \"send_push_notification\",\r\n \"attributes\" : {\r\n \"fcm_id\" : {\"ios\" :[], \"android\" :[\""+fcmID+"\"]},,\r\n \"app_notification_title\" : \"Notification Title\",\r\n \"app_priority\" : \"high\",\r\n \"app_notification_redirect_url\" : \"earnkaro://stores/ajio\",\r\n \"app_notification_image_url\" : \"https://askopinion.com/images/Files/UserFiles/posting/lrg/2017/8/cash-karo-nbn.png\",\r\n\"app_notification_body\" : \"Notification Body\",\r\n \"app_notification_sub_text\" : \"Notification SubText\",\r\n \"app_notification_big_text\" : \"Big Text\"\r\n \r\n \r\n }\r\n}\r\n}\r\n";
		String notificationPostURL = GetCodeSetUP.EKAPIBASEURI  + "v1/notification/app_group_notification";

		// Setting up the multiple header values into Headers Class object
		final Header header1 = new Header("Accept", "application/vnd.api+json" );
		final Header header2 = new Header("Content-Type".trim(),"application/vnd.api+json");
		Headers headers = new Headers(header1, header2);

		// Status Validation for the POST request
		io.restassured.response.Response response = 
				given().
				config(RestAssured.config().encoderConfig(io.restassured.config.EncoderConfig.encoderConfig().appendDefaultContentCharsetToContentTypeIfUndefined(false))).
				headers(headers).
				body(postString.trim()).
				when().
				post(notificationPostURL.trim());

		System.out.println(" Response : "+ response.asString());
		System.out.println(" respnse : "+ response.path("data.attributes.message"));

		Assert.assertEquals("Android App Notification Send Successfully.", response.path("data.attributes.message").toString().trim());

		return "success";

	}

	public static String notificationPostAPICall_WithRedirectionalURLToMyEarningsPage(String fcmID) {

	//	String postString = "{\r\n\"data\":{\r\n \"type\" : \"send_push_notification\",\r\n \"attributes\" : {\r\n \"fcm_id\" : \""+fcmID+"\",\r\n \"app_notification_title\" : \"Notification Title\",\r\n \"app_priority\" : \"high\",\r\n \"app_notification_redirect_url\" : \"earnkaro://my-earnings\",\r\n \"app_notification_image_url\" : \"https://askopinion.com/images/Files/UserFiles/posting/lrg/2017/8/cash-karo-nbn.png\",\r\n \"app_large_icon\" : \"https://dummyimage.com/72x72/0a6a73/303bdb.png&text=Test+Image\",\r\n \"app_notification_body\" : \"Notification Body\",\r\n \"app_notification_sub_text\" : \"Notification SubText\",\r\n \"app_notification_big_text\" : \"Big Text\"\r\n \r\n \r\n }\r\n}\r\n}\r\n"; 
		String postString = "{\r\n\"data\":{\r\n \"type\" : \"send_push_notification\",\r\n \"attributes\" : {\r\n \"fcm_id\" : {\"ios\" :[], \"android\" :[\""+fcmID+"\"]},\r\n \"app_notification_title\" : \"Notification Title\",\r\n \"app_priority\" : \"high\",\r\n \"app_notification_redirect_url\" : \"earnkaro://my-earnings\",\r\n \"app_notification_image_url\" : \"https://askopinion.com/images/Files/UserFiles/posting/lrg/2017/8/cash-karo-nbn.png\",\r\n \"app_large_icon\" : \"https://dummyimage.com/72x72/0a6a73/303bdb.png&text=Test+Image\",\r\n \"app_notification_body\" : \"Notification Body\",\r\n \"app_notification_sub_text\" : \"Notification SubText\",\r\n \"app_notification_big_text\" : \"Big Text\"\r\n \r\n \r\n }\r\n}\r\n}\r\n";
		String notificationPostURL = GetCodeSetUP.EKAPIBASEURI  + "v1/notification/app_group_notification";

		// Setting up the multiple header values into Headers Class object
		final Header header1 = new Header("Accept", "application/vnd.api+json" );
		final Header header2 = new Header("Content-Type".trim(),"application/vnd.api+json");
		Headers headers = new Headers(header1, header2);

		// Status Validation for the POST request
		io.restassured.response.Response response = 
				given().
				config(RestAssured.config().encoderConfig(io.restassured.config.EncoderConfig.encoderConfig().appendDefaultContentCharsetToContentTypeIfUndefined(false))).
				headers(headers).
				body(postString.trim()).
				when().
				post(notificationPostURL.trim());

		System.out.println(" Response : "+ response.asString());
		System.out.println(" respnse : "+ response.path("data.attributes.message"));

		Assert.assertEquals("Android App Notification Send Successfully.", response.path("data.attributes.message").toString().trim());

		return "success";

	}
	
	public static String notificationPostAPICall_WithRedirectionalURLToMyEarningsPage_NormalPriority(String fcmID) {

		//String postString = "{\r\n\"data\":{\r\n \"type\" : \"send_push_notification\",\r\n \"attributes\" : {\r\n \"fcm_id\" : \""+fcmID+"\",\r\n \"app_notification_title\" : \"Notification Title\",\r\n \"app_priority\" : \"high\",\r\n \"app_notification_redirect_url\" : \"earnkaro://my-earnings\",\r\n \"app_notification_image_url\" : \"https://askopinion.com/images/Files/UserFiles/posting/lrg/2017/8/cash-karo-nbn.png\",\r\n \"app_large_icon\" : \"https://dummyimage.com/72x72/0a6a73/303bdb.png&text=Test+Image\",\r\n \"app_notification_body\" : \"Notification Body\",\r\n \"app_notification_sub_text\" : \"Notification SubText\",\r\n \"app_notification_big_text\" : \"Big Text\"\r\n \r\n \r\n }\r\n}\r\n}\r\n"; 
		
		String postString = "{\r\n\"data\":{\r\n \"type\" : \"send_push_notification\",\r\n \"attributes\" : {\r\n \"fcm_id\" : {\"ios\" :[], \"android\" :[\""+fcmID+"\"]},\r\n \"app_notification_title\" : \"Notification Title\",\r\n \"app_priority\" : \"high\",\r\n \"app_notification_redirect_url\" : \"earnkaro://my-earnings\",\r\n \"app_notification_image_url\" : \"https://askopinion.com/images/Files/UserFiles/posting/lrg/2017/8/cash-karo-nbn.png\",\r\n \"app_large_icon\" : \"https://dummyimage.com/72x72/0a6a73/303bdb.png&text=Test+Image\",\r\n \"app_notification_body\" : \"Notification Body\",\r\n \"app_notification_sub_text\" : \"Notification SubText\",\r\n \"app_notification_big_text\" : \"Big Text\"\r\n \r\n \r\n }\r\n}\r\n}\r\n";
		String notificationPostURL = GetCodeSetUP.EKAPIBASEURI + "v1/notification/app_group_notification";

		// Setting up the multiple header values into Headers Class object
		final Header header1 = new Header("Accept", "application/vnd.api+json" );
		final Header header2 = new Header("Content-Type".trim(),"application/vnd.api+json");
		Headers headers = new Headers(header1, header2);

		// Status Validation for the POST request
		io.restassured.response.Response response = 
				given().
				config(RestAssured.config().encoderConfig(io.restassured.config.EncoderConfig.encoderConfig().appendDefaultContentCharsetToContentTypeIfUndefined(false))).
				headers(headers).
				body(postString.trim()).
				when().
				post(notificationPostURL.trim());

		System.out.println(" Response : "+ response.asString());
		System.out.println(" respnse : "+ response.path("data.attributes.message"));

		Assert.assertEquals("Android App Notification Send Successfully.", response.path("data.attributes.message").toString().trim());

		return "success";

	}
	
	public static String notificationPostAPICall_WithoutLargeIcon_WithoutBigImage_WithOutReDirectURL(String fcmID) {
		
		//String postString = "{\r\n\"data\":{\r\n \"type\" : \"send_push_notification\",\r\n \"attributes\" : {\r\n \"fcm_id\" : \""+fcmID+"\",\r\n\"app_notification_title\" : \"Notification Title\",\r\n\"app_priority\" : \"high\",\r\n \"app_notification_body\" : \"Notification Body\",\r\n \"app_notification_sub_text\" : \"Notification SubText\",\r\n \"app_notification_big_text\" : \"Big Text\"\r\n \r\n \r\n }\r\n}\r\n}\r\n"; 
		String postString = "{\r\n\"data\":{\r\n \"type\" : \"send_push_notification\",\r\n \"attributes\" : {\r\n \"fcm_id\" : {\"ios\" :[], \"android\" :[\""+fcmID+"\"]},\r\n\"app_notification_title\" : \"Notification Title\",\r\n\"app_priority\" : \"high\",\r\n \"app_notification_body\" : \"Notification Body\",\r\n \"app_notification_sub_text\" : \"Notification SubText\",\r\n \"app_notification_big_text\" : \"Big Text\"\r\n \r\n \r\n }\r\n}\r\n}\r\n";
		String notificationPostURL = GetCodeSetUP.EKAPIBASEURI +  "v1/notification/app_group_notification";

		// Setting up the multiple header values into Headers Class object
		final Header header1 = new Header("Accept", "application/vnd.api+json" );
		final Header header2 = new Header("Content-Type".trim(),"application/vnd.api+json");
		Headers headers = new Headers(header1, header2);

		// Status Validation for the POST request
		io.restassured.response.Response response = 
				given().
				config(RestAssured.config().encoderConfig(io.restassured.config.EncoderConfig.encoderConfig().appendDefaultContentCharsetToContentTypeIfUndefined(false))).
				headers(headers).
				body(postString.trim()).
				when().
				post(notificationPostURL.trim());
		
		
		System.out.println(" Response : "+ response.asString());
		System.out.println(" respnse : "+ response.path("data.attributes.message"));

		Assert.assertEquals("Android App Notification Send Successfully.", response.path("data.attributes.message").toString().trim());

		return "success";

	}
	
	public static String notificationPostAPICall_WithoutLargeIcon_WithoutBigImage_WithOutReDirectURL_NormalPriority(String fcmID) {

		//String postString = "{\r\n\"data\":{\r\n \"type\" : \"send_push_notification\",\r\n \"attributes\" : {\r\n \"fcm_id\" : \""+fcmID+"\",\r\n\"app_notification_title\" : \"Notification Title\",\r\n\"app_priority\" : \"normal\",\r\n \"app_notification_body\" : \"Notification Body\",\r\n \"app_notification_sub_text\" : \"Notification SubText\",\r\n \"app_notification_big_text\" : \"Big Text\"\r\n \r\n \r\n }\r\n}\r\n}\r\n"; 
		
		String postString = "{\r\n\"data\":{\r\n \"type\" : \"send_push_notification\",\r\n \"attributes\" : {\r\n \"fcm_id\" : {\"ios\" :[], \"android\" :[\""+fcmID+"\"]},\r\n\"app_notification_title\" : \"Notification Title\",\r\n\"app_priority\" : \"normal\",\r\n \"app_notification_body\" : \"Notification Body\",\r\n \"app_notification_sub_text\" : \"Notification SubText\",\r\n \"app_notification_big_text\" : \"Big Text\"\r\n \r\n \r\n }\r\n}\r\n}\r\n";
		String notificationPostURL = GetCodeSetUP.EKAPIBASEURI + "v1/notification/app_group_notification";

		// Setting up the multiple header values into Headers Class object
		final Header header1 = new Header("Accept", "application/vnd.api+json" );
		final Header header2 = new Header("Content-Type".trim(),"application/vnd.api+json");
		Headers headers = new Headers(header1, header2);

		// Status Validation for the POST request
		io.restassured.response.Response response = 
				given().
				config(RestAssured.config().encoderConfig(io.restassured.config.EncoderConfig.encoderConfig().appendDefaultContentCharsetToContentTypeIfUndefined(false))).
				headers(headers).
				body(postString.trim()).
				when().
				post(notificationPostURL.trim());

		System.out.println(" respnse : "+ response.path("data.attributes.message"));

		System.out.println(" Response : "+ response.asString());
		Assert.assertEquals("Android App Notification Send Successfully.", response.path("data.attributes.message").toString().trim());

		return "success";

	}

	@CheckForNull
	public static String fetchFireBaseTokenFCMID(AppiumDriver<MobileElement> driver ) {

		String firebase = null;
		System.out.println("About to get the Fire base token Value ");
		//Fetch the LogCat : 
		List<LogEntry> logEntries = driver.manage().logs().get("logcat").getAll();
		for(LogEntry log : logEntries) {
			if(log.toString().contains("Firebase token")){
				firebase =  log.toString();
			}
		}

		String[] fbArr = firebase.split("token:");
		firebase = fbArr[1];
		System.out.println(" FireBase token is Value is  : "+fbArr[1]);
		
		return firebase;
	}

	
	/*******************************************************************/
	/****************  Notification Reusability  WIth Template*********/
	/*******************************************************************/
	
	public static String notificationPostAPICall_WithTemplate(String templateId,String fcmID) {

		String postString = "{\r\n  \"data\": {\r\n \"type\": \"notifications\",\r\n \"attributes\": {\r\n\"template_id\": \""+templateId+"\",\r\n\"user_details\": {\r\n  \"fcm_id\" : {\"ios\" :[], \"android\" :[\""+fcmID+"\"]}, \r\n \"replacement_variables\": {\r\n\t \"%SITE_URL%\":\"cashkaro.com\"\r\n }\r\n }\r\n  }\r\n}\r\n}\r\n"; 
		System.out.println();
		System.out.println("post Body - Notification "+ postString);
		System.out.println();
		
		String notificationPostURL = GetCodeSetUP.EKAPIBASEURI + "v1/notification/notification";

		// Setting up the multiple header values into Headers Class object
		final Header header1 = new Header("Accept", "application/vnd.api+json" );
		final Header header2 = new Header("Content-Type".trim(),"application/vnd.api+json");
		Headers headers = new Headers(header1, header2);

		// Status Validation for the POST request
		io.restassured.response.Response response = 
				given().
				config(RestAssured.config().encoderConfig(io.restassured.config.EncoderConfig.encoderConfig().appendDefaultContentCharsetToContentTypeIfUndefined(false))).
				headers(headers).
				body(postString.trim()).
				when().
				post(notificationPostURL.trim());
		
		
		System.out.println(" Response : "+ response.asString());
		System.out.println(" Response : "+ response.path("data.attributes.success[0]"));

		Assert.assertEquals("Android App Notification Send Successfully.", response.path("data.attributes.success[0]").toString().trim());

		return "success";

	}
	
}
