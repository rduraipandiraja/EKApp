package com.app.ck.base;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.app.ck.utilities.XMLReader;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.MediaEntityModelProvider;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;


import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Base  {

	//Desired capability variables
	public  String udid ;
	public  String port ;
	public Integer systemPort;
	public  String automationName ;
	public  String deviceName;
	public  String androidVersion = null;
	public  static String apiLevel = null;
	public static boolean screenshotOnTestPass = false;
	public static boolean screenshotOnTestFail = true;
	
	String testName ="";

	//Variable declaration
	public AppiumDriver<MobileElement> driver;
	protected static ExtentReports reports;
	protected  ExtentTest testInfo;
	public  static ExtentTest testInfo_Static;
	public  ExtentHtmlReporter htmlReporter;
	

	// XMLReader.xml declaration
	public static final XMLReader objXMLReader = new com.app.ck.utilities.XMLReader(System.getProperty("user.dir") + "/input/testdata.xml");
	public static final List<Hashtable<String, String>> testdata = objXMLReader.getDataAsList("Framework");
	
	public static final XMLReader objXMLReader1 = new com.app.ck.utilities.XMLReader(System.getProperty("user.dir") + "/input/LiveTestData.xml");
	public static final List<Hashtable<String, String>> LiveTestData = objXMLReader1.getDataAsList("Framework");
	
	//before suite
	@BeforeSuite
	public void beforeSuite() {
		
		
		String adminPassword = testdata.get(8).get("AdminPassword");
		System.out.println("About to set the Password as " + adminPassword );

		try {
			OkHttpClient client = new OkHttpClient();
			Request request = new Request.Builder()
					.url(GetCodeSetUP.EKBASEURL + "getcode.php?page=update_admin_user_password&uname=dev&pwd="+adminPassword)
					.get()
					.addHeader("cache-control", "no-cache")
					.build();
			
			Response response = client.newCall(request).execute();
			System.out.println("Successfully set the admin Password as =  " + adminPassword );

		}catch (Exception e) {

			e.printStackTrace();
			System.out.println("Failed to set the admin Password as " +  adminPassword);
		}
		
		GetCodeSetUP.getCodeSetUP();
		
	}
	
	//Before Test
	@BeforeTest (alwaysRun=true)
	public void setup() {
		
		screenshotOnTestFail = PropertyFile.needScreenshotOnStepFail();
		screenshotOnTestPass = PropertyFile.needScreenshotOnStepPass();
		
		// Set HTML reporting file location
		Date objNewDateFolder = new Date();
		SimpleDateFormat dateFormat_Folder = new SimpleDateFormat("yyyy_MM_dd");
		File file = new File(System.getProperty("user.dir") + "/results/" + dateFormat_Folder.format(objNewDateFolder));
		boolean status = file.mkdir();
		
		if(status) {
			
			System.out.println("New directory is been created :)  ");
		}
		
		String strDatenow = dateFormat_Folder.format(objNewDateFolder);

		// created result file with time stamp in date folder at results folder
		Date objNewTimeFile = new Date();
		SimpleDateFormat dateFormat_File = new SimpleDateFormat("yyyy_MM_dd @ HH_mm_SS");
		

		htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "/results/" + strDatenow + "/" + dateFormat_File.format(objNewTimeFile) + ".html");
		
		//htmlReporter = new ExtentHtmlReporter(new File(System.getProperty("user.dir")+ "results/Automation.html"));
		htmlReporter.loadXMLConfig(new File(System.getProperty("user.dir")+ "/extent-config.xml"),true);
		reports = new ExtentReports();
		reports.setSystemInfo("Environment", "Window8");
		reports.setSystemInfo("Appium testing by", "QA team");
		reports.setSystemInfo("Appium version", "10.0");
		reports.setSystemInfo("Ckapp version", "1.5");
		reports.attachReporter(htmlReporter);
		
	}

	//BeforeMethods
	@Parameters(value= {"udid","port","systemPort","automationName","deviceName","androidVersion","APILevel"})
	@BeforeMethod(alwaysRun = true)
	public void beforeMethodDoThis(String udid,String port,Integer systemPort, String automationName,String deviceName,String androidVersion, String APILevel,Method method) throws Exception  {

		this.udid = udid;
		this.port = port;

		System.out.println("Base port is "+ this.port + " & Base udid is " + this.udid );

		this.automationName = automationName;
		this.deviceName = deviceName;
		this.systemPort = systemPort;
		this.androidVersion = androidVersion;
		Base.apiLevel  = APILevel;		

		testName = method.getName();
		testInfo = reports.createTest(testName);
		testInfo_Static = testInfo;

		System.out.println("Started the Method : "+ testName );
		System.out.println("Setting the Desired caps for the cashkaro app execution .. !");

		DesiredCapabilities dc = new DesiredCapabilities();

		//App info
		dc.setCapability("appPackage", "com.earnkaro");
		dc.setCapability("appActivity", "com.earnkaro.MainActivity");

		//Device Configuration 
		dc.setCapability("platformName", "Android");
		dc.setCapability("automationName", automationName);
		dc.setCapability("deviceName", deviceName);
		dc.setCapability("udid", udid);

		//Other Configuration
		dc.setCapability("newCommandTimeout", 30000);
		dc.setCapability("JSONSource", true);
		dc.setCapability("systemPort", systemPort);
		//dc.setCapability("noReset", true);


		testInfo.log(Status.INFO, "####  DESIRED CAPABILITY IS SET AS ####"
				+ "  platformName : Android ,"
				+ "  automationName : " + this.automationName 
				+ "  appPackage : com.earnkaro "
				+ "  appActivity : com.earnkaro.MainActivity"
				+ "  deviceName : "+ this.deviceName 
				+ "  androidVersion : " + this.androidVersion
				+ "  apiLevel : "+ Base.apiLevel);

		System.out.println("Desired capabilities set as "
				+ "\n platformName : Android,"
				+ "\n automationName : " + this.automationName 
				+ "\n appPackage : com.earnkaro "
				+ "\n appActivity : com.earnkaro.MainActivity"
				+ "\n deviceName : "+ this.deviceName 
				+ "\n androidVersion : " + this.androidVersion
				+ "\n aPILevel : "+ Base.apiLevel);


		try {

			driver = new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:"+port+"/wd/hub"), dc);
			System.out.println("systme port "+ systemPort );
			System.out.println("Port started at : "+ " http://127.0.0.1:"+port+"/wd/hub");
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		}
		catch (Exception e) {

			System.out.println(e);
			testInfo.log(Status.FAIL,"Failed to establish the Connection - Server side error 404  ");
			testInfo.log(Status.FAIL,e);

		}
	}

	//AfterMethod
	@AfterMethod (alwaysRun=true)
	public void afterMethod(ITestResult result) {

		if(result.getStatus() == ITestResult.SUCCESS) {

			testInfo.pass("The TestCase -  successfully Completed .");
			
		}else if(result.getStatus()==ITestResult.FAILURE) {
			
			
			MediaEntityModelProvider img = null;

				long snapNumber = 100000L;
				
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
					   Thread.currentThread().interrupt();
				}
				snapNumber = takeScreenShot();
				try {
					img = MediaEntityBuilder.createScreenCaptureFromPath
							(System.getProperty("user.dir") + "/rep/img/"+snapNumber+".png").build();
				} catch (IOException e) {
					
				}
				
			testInfo.info("Click back to close the notification , If it is open...",img);
			
		//	((AndroidDriver) driver).pressKeyCode(AndroidKeyCode.BACK);
			
			testInfo.fail("UNEXPECTED ERROR DURING THE EXECUTION - NO report step is Added in the line; Please add the report step for more clarity",img);

		} else if(result.getStatus()==ITestResult.SKIP) {

			testInfo.skip("TEST CASE SKIPPED UNEXPECTEDLY DUE TO TESTNG CONFIG ISSUES - PLEASE RE-EXECUTE AND ANALYSE THE RESULTS - DONT IGNORE TO EXECUTE THE TEST CASE , THIS TEST MAY FIND THE ISSUE IN THE APP");
			throw new SkipException("Skipping - This is not ready for testing ");
			
		}
		
		System.out.println("Completed with the Method : "+ testName + "So kill the Session" );
		
		//driver.quit();
	}

	//AfterTest
	@AfterTest (alwaysRun=true)
	public void cleanUp() {
		
		reports.flush();
	}

	//Take screenshot
	public long takeScreenShot() {
		
		long number = (long) Math.floor(Math.random() * 900000000L) + 10000000L;
		try {
			File srcFiler = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(srcFiler, new File("./rep/img/" + number + ".png"));
		} catch (WebDriverException e) {
			e.printStackTrace();
			System.out.println("The browser has been closed.");
		} catch (IOException e) {
			System.out.println("The snapshot could not be taken");
		}
		return number;
	}

	//LogReport
	public void reportStep(String desc, String status, boolean bSnap)  {

		MediaEntityModelProvider img = null;
		
		if(bSnap && !status.equalsIgnoreCase("INFO")){

			long snapNumber = 100000L;
			
			snapNumber = takeScreenShot();
			try {
				img = MediaEntityBuilder.createScreenCaptureFromPath(System.getProperty("user.dir") + "/rep/img/"+snapNumber+".png").build();
				
			} catch (IOException e) {
				
			}
		}
		
		if(status.trim().equalsIgnoreCase("PASS")) {
			testInfo.pass(desc, img);			
		}else if (status.trim().equalsIgnoreCase("FAIL")) {
			testInfo.fail(desc, img);
			throw new RuntimeException();
		}else if (status.trim().equalsIgnoreCase("SKIP")) {
			testInfo.skip(desc, img);
		}else if (status.trim().equalsIgnoreCase("INFO")) {
			testInfo.info(desc);
		}							
	}
	
	//LogReport
	public void reportStep_Language(String desc, String status, boolean bSnap)  {

		MediaEntityModelProvider img = null;
		
		if(bSnap && !status.equalsIgnoreCase("INFO")){

			long snapNumber = 100000L;
			
			snapNumber = takeScreenShot();
			try {
				img = MediaEntityBuilder.createScreenCaptureFromPath(System.getProperty("user.dir") + "/rep/img/"+snapNumber+".png").build();
				
			} catch (IOException e) {
				
			}
		}
		
		if(status.trim().equalsIgnoreCase("PASS")) {
			testInfo.pass(desc, img);			
		}else if (status.trim().equalsIgnoreCase("FAIL")) {
			testInfo.fail(desc, img);
		}else if (status.trim().equalsIgnoreCase("SKIP")) {
			testInfo.skip(desc, img);
		}else if (status.trim().equalsIgnoreCase("INFO")) {
			testInfo.info(desc);
		}							
	}

	//LogReportHelpermethod
	public void reportStep(String desc, String status) {
		
		if(status.equals("FAIL")) {
			
		reportStep(desc, status, screenshotOnTestFail);
		
		} else {
			reportStep(desc, status, screenshotOnTestPass);
		}
		
	
		
	}


	
	
	
	
	
	
}
