package com.app.ck.base;

import static io.appium.java_client.touch.offset.PointOption.point;
import static org.testng.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.json.simple.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.app.ck.pages.SignInPage;
import com.app.ck.utilities.Utilities;
import com.aventstack.extentreports.ReportStatusStats;
import com.aventstack.extentreports.Status;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.HasOnScreenKeyboard;
import io.appium.java_client.MobileElement;
import io.appium.java_client.MultiTouchAction;
import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.StartsActivity;
import io.appium.java_client.android.SupportsNetworkStateManagement;
import io.appium.java_client.android.connection.ConnectionStateBuilder;
import io.appium.java_client.android.connection.HasNetworkConnection;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.android.nativekey.PressesKey;
import io.appium.java_client.clipboard.ClipboardContentType;
import io.appium.java_client.clipboard.HasClipboard;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.touch.LongPressOptions;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.ElementOption;

public class WrapperMethods extends Base {
	
	
	
//********************************Wrapper Methods **************************************************//

	public boolean launchApp(String appPackage, String appActivity, String deviceName) {
		try {
			DesiredCapabilities dc = new DesiredCapabilities();
			dc.setCapability("appPackage", appPackage);
			dc.setCapability("appActivity", appActivity);
			dc.setCapability("deviceName", deviceName);
			dc.setCapability("noReset", true);
			driver = new AndroidDriver<MobileElement>(new URL("http://0.0.0.0:4723/wd/hub"), dc);
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		} catch (MalformedURLException e) {
			
			e.printStackTrace();
		}
		return true;
	}

	public boolean launchAppInIOs(String deviceName, String DeviceID, String xcodeSinginedId, String appPath,
			String bundleId) {
		try {
			DesiredCapabilities caps = new DesiredCapabilities();
			caps.setCapability("platformName", "iOS");
			caps.setCapability("deviceName", deviceName);
			caps.setCapability("automationName", "XCUITest");
			caps.setCapability("udid", DeviceID);
			caps.setCapability("xcodeConfigFile", xcodeSinginedId);
			// caps.setCapability("app", appPath);
			caps.setCapability("bundleId", bundleId);
			caps.setCapability("autoAcceptAlerts", true);
			caps.setCapability("autoGrantPermissions", true);

			driver = new IOSDriver<MobileElement>(new URL("http://0.0.0.0:4723/wd/hub"), caps);
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

			return true;
		} catch (Exception e) {
			return false;
		}

	}

	public boolean launchAppInIOsSimulator(String appPath) {
		
		try {
			DesiredCapabilities caps = new DesiredCapabilities();
			caps.setCapability("deviceName", "iPhone SE");
			caps.setCapability("automationName", "XCUITest");
			caps.setCapability("app", appPath);

			driver = new IOSDriver<MobileElement>(new URL("http://0.0.0.0:4723/wd/hub"), caps);
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

			return true;
		} catch (Exception e) {
			return false;
		}

	}

	// Not valid for iOS
	public boolean launchActivity(String appPackage, String appActivity) {
		
		try {
			((StartsActivity) driver).startActivity(new Activity(appPackage, appActivity));
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
		return true;
	}

	public boolean launchBrowser(String browserName, String deviceName, String URL) {
		try {
			DesiredCapabilities dc = new DesiredCapabilities();
			dc.setCapability("browserName", browserName);
			dc.setCapability("deviceName", deviceName);
			driver = new AndroidDriver<MobileElement>(new URL("http://0.0.0.0:4723/wd/hub"), dc);
			driver.get(URL);
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return true;
	}

	public boolean verifyAndInstallApp(String appPackage, String appPath) {
		boolean bInstallSuccess = false;

		if (driver.isAppInstalled(appPackage))
			driver.removeApp(appPackage);

		driver.installApp(appPath);
		bInstallSuccess = true;

		return bInstallSuccess;
	}

	public void sleep(int mSec) {
		try {
			Thread.sleep(mSec);
		} catch (InterruptedException e) {
			e.printStackTrace();
			Thread.currentThread().interrupt();
		}
	}

	public void printContext() {
		try {
			Set<String> contexts = driver.getContextHandles();
			for (String string : contexts) {
				System.out.println(string);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean switchContext(String Context) {
		try {
			driver.context(Context);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	public boolean clickByID(String ID) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 30);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.id(ID)));
			driver.findElementById(ID).click();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	public boolean clickByAccessebilityID(String ID) {
		try {

			driver.findElementByAccessibilityId(ID).click();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	public boolean clickByXpath(String Xpath) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 30);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(Xpath)));
			driver.findElementByXPath(Xpath).click(); 
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean clickByLinkText(String LinkText) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 30);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText(LinkText)));
			driver.findElementByLinkText(LinkText).click();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	public boolean enterTextByID(String ID, String value) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 30);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.id(ID)));
			driver.findElementById(ID).clear();
			driver.findElementById(ID).sendKeys(value);
		} catch (Exception e) {
			e.printStackTrace();
			reportStep( e.getMessage(), "INFO");
		}
		return true;
	}
	
	public boolean enterTextByClassName(String className, String value) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 30);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.className(className)));
			driver.findElementByClassName(className).clear();
			driver.findElementByClassName(className).sendKeys(value);
		} catch (Exception e) {
			e.printStackTrace();
			reportStep( e.getMessage(), "INFO");
		}
		return true;
	}

	// Not available for iOS
	public boolean pressEnter() {
		
		((AndroidDriver) driver).pressKey(new KeyEvent(AndroidKey.ENTER));

		return true;
	}

	public boolean enterTextByXpath(String Xpath, String value) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 30);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(Xpath)));
			driver.findElementByXPath(Xpath).clear();
			driver.findElementByXPath(Xpath).sendKeys(value);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean enterTextByXpath_UsingSetValue(String Xpath, String value) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 30);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(Xpath)));
			driver.findElementByXPath(Xpath).clear();
			driver.findElementByXPath(Xpath).setValue(value);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			reportStep( e.getMessage(), "INFO");
		}
		return false;
	}
	
	public boolean enterTextByXpathInChrome(AppiumDriver<MobileElement> driver,String Xpath, String value) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 30);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(Xpath)));
			driver.findElementByXPath(Xpath).clear();
			driver.findElementByXPath(Xpath).sendKeys(value);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			reportStep( e.getMessage(), "INFO");
		}
		return false;
	}
	
	public boolean clickInChrome(AppiumDriver<MobileElement> chromeBrowserdriver,String Xpath) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 30);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(Xpath)));
			chromeBrowserdriver.findElementByXPath(Xpath).click();

			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean enterText(MobileElement element, String value) {

		try {
	
			element.clear();
			hideKeyboard();
			element.setValue(value);
			hideKeyboard();
			
			return true;

		} catch (Exception e) {

			e.printStackTrace();
			reportStep( e.getMessage(), "INFO");
			return false;
		}

	}
	
	public boolean enterTextUsingSendKeys(MobileElement element, String value) {

		try {
	
			element.clear();
			hideKeyboard();
			element.sendKeys(value);
			hideKeyboard();
			
			return true;

		} catch (Exception e) {

			e.printStackTrace();
			reportStep( e.getMessage(), "INFO");
			return false;
		}

	}
	
	public boolean enterTextWithOutHideKeyboard(MobileElement element, String value) {

		try {
	
			element.clear();
			element.setValue(value);
			
			return true;

		} catch (Exception e) {

			e.printStackTrace();
			reportStep( e.getMessage(), "INFO");
			return false;
		}

	}
	
	public boolean enterTextWithOutClear(MobileElement element, String value) {

		try {
			
			element.setValue(value);
			hideKeyboard();
			
			return true;

		} catch (Exception e) {

			e.printStackTrace();
			reportStep( e.getMessage(), "INFO");
			return false;
		}

	}
	
	public boolean enterTextWithOutClear_And_WithoutHidingKeyBoard(MobileElement element, String value) {

		try {
			
			element.setValue(value);
			return true;

		} catch (Exception e) {

			e.printStackTrace();
			reportStep( e.getMessage(), "INFO");
			return false;
		}

	}


	public boolean enterTextInChrome(MobileElement element, String value) {


		try {
			WebDriverWait wait = new WebDriverWait(driver, 30);
			wait.until(ExpectedConditions.visibilityOf(element));
			element.clear();
			element.sendKeys(value);
			return true;

		} catch (Exception e) {

			e.printStackTrace();
			reportStep( e.getMessage(), "INFO");
			return false;
		}


	}

	public boolean enterTextAfterClick(MobileElement element, String value) {


		try {
			WebDriverWait wait = new WebDriverWait(driver, 30);
			wait.until(ExpectedConditions.visibilityOf(element));
			element.clear();
			element.click();
			element.sendKeys(value);
			return true;

		} catch (Exception e) {

			e.printStackTrace();
			reportStep( e.getMessage(), "INFO");
			return false;
		}


	}

	public boolean takeScreenShot(String FileName) {
		try {
			File srcFiler = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(srcFiler, new File(FileName));
		} catch (WebDriverException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return true;
	}

	public boolean addScreenshot() {
		
		testInfo.log(Status.INFO, "Adding the screen shot at the step by step action level");

		// Take base64Screenshot screenshot.
		String base64Screenshot = "data:image/png;base64,"
				+ ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);

		testInfo.addScreenCaptureFromBase64String(base64Screenshot);
	
		
		return true;
	}

	public boolean verifyTextByID(String ID, String Expected) {
		boolean val = false;
		try {
			WebDriverWait wait = new WebDriverWait(driver, 30);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.id(ID)));
			String name = driver.findElementById(ID).getText();
			if (name.contains(Expected)) {
				val = true;
			} else
				val = false;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return val;
	}
	
	public boolean waitForElementPresence(MobileElement element) {
		boolean val = false;
		try {
			WebDriverWait wait = new WebDriverWait(driver, 30);
			wait.until(ExpectedConditions.visibilityOf(element));
			reportStep("Successfully validated the element is presence ", "PASS");
		}catch (Exception e) {

			reportStep("Required element is not present after even after waiting 30 sec  "+ element, "FAIL");
			return false;

		}
		return true;
	}
	
	// Applicable only for browser
	public boolean scrollDownInBrowser(int val) {
		try {
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("window.scrollBy(0," + val + "\")", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	// Applicable only for browser
	public boolean backButton() {
		try {

			reportStep("About to click back button", "INFO");
			driver.navigate().back();
			reportStep("Successfully clicked back button", "PASS");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	public boolean backButton_WaitForFewSeconds() {
		try {

			reportStep("About to click back button", "INFO");
			
			driver.navigate().back();
			
			reportStep("Successfully clicked back button", "PASS");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	public boolean backButtonApp(AppiumDriver<MobileElement> driver) {
		try {
			driver.navigate().back();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	public boolean scrollUsingDesc(String text) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 30);
			wait.until(ExpectedConditions
					.presenceOfElementLocated(By.xpath("//android.view.View[contains(@content-desc,'" + text + "')]")));
			Dimension size = driver.manage().window().getSize();
			int x0 = (int) (size.getWidth() * 0.2);
			int y0 = (int) (size.getHeight() * 0.2);
			Point xy = driver.findElementByXPath("//android.view.View[contains(@content-desc,'" + text + "')]")
					.getLocation();
			int x1 = (int) (xy.getX());
			int y1 = (int) (xy.getY());
			MultiTouchAction touch = new MultiTouchAction(driver);
			touch.add(new TouchAction<>(driver).press(point(x1, y1))
					.waitAction(WaitOptions.waitOptions(Duration.ofSeconds(2))).moveTo(point(x0, y0)).release())
					.perform();
		} catch (WebDriverException e) {
			e.printStackTrace();
		}
		return true;
	}

	public boolean scrollFromDownToUpinApp() {
		try {
			Dimension size = driver.manage().window().getSize();
			int x0 = (int) (size.getWidth() * 0.5);
			int y0 = (int) (size.getHeight() * 0.2);
			int x1 = (int) (size.getWidth() * 0.5);
			int y1 = (int) (size.getHeight() * 0.8);
			MultiTouchAction touch = new MultiTouchAction(driver);
			touch.add(new TouchAction<>(driver).press(point(x1, y1))
					.waitAction(WaitOptions.waitOptions(Duration.ofSeconds(2))).moveTo(point(x0, y0)).release())
					.perform();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		reportStep("Successfully scrolled from Down to Up ", "INFO");
		return true;
	}

	public boolean swipeFromLeftToRight() {
		
		try {
			Dimension size = driver.manage().window().getSize();
			int x0 = (int) (size.getWidth() * 0.3);
			int y0 = (int) (size.getHeight() * 0.5);
			int x1 = (int) (size.getWidth() * 0.9);
			int y1 = (int) (size.getHeight() * 0.5);
			
			System.out.println("x0  :"+ x0 + " x1  : "+ x1 + " y0  : "+ y0 + " y1 :"+ y1);
			MultiTouchAction touch = new MultiTouchAction(driver);
			touch.add(new TouchAction<>(driver).press(point(x0, y0))
					.waitAction(WaitOptions.waitOptions(Duration.ofSeconds(2))).moveTo(point(x1, y1)).release())
					.perform();

			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		reportStep("Successfully scrolled from Down to Up ", "INFO");
		return true;
	}

	public boolean swipeFromRightToLeft() {
			
			try {
				
				Dimension size = driver.manage().window().getSize();
				int x0 = (int) (size.getWidth() * 0.3);
				int y0 = (int) (size.getHeight() * 0.8);
				int x1 = (int) (size.getWidth() * 0.9);
				int y1 = (int) (size.getHeight() * 0.8);
				
				System.out.println("x0  :"+ x0 + " x1  : "+ x1 + " y0  : "+ y0 + " y1 :"+ y1);
				MultiTouchAction touch = new MultiTouchAction(driver);
				touch.add(new TouchAction<>(driver).press(point(x1, y1))
						.waitAction(WaitOptions.waitOptions(Duration.ofSeconds(2))).moveTo(point(x0, y0)).release())
						.perform();

				
			} catch (Exception e) {
				e.printStackTrace();
			}
	
		reportStep("Successfully swiped from Right to Left ", "INFO");
		
		return true;
	}
	
	public boolean closeEarnKaroAppInRedmi() {
		
		reportStep("About to close the EarnKaro App in RedMi Device ", "INFO");
	
		String earnKaroAppXpath = "//android.widget.TextView[@text='EarnKaro']";
		String chromeAppXpath="//android.widget.TextView[@text='Chrome']";
		
		try {
			isElementLocatedByXpathPresent(earnKaroAppXpath);
			MobileElement element = driver.findElement(By.xpath(earnKaroAppXpath));
			isElementLocatedByXpathPresent(chromeAppXpath);
			MobileElement Chromeelement = driver.findElement(By.xpath(chromeAppXpath));
			
			Dimension appDimension = element.getSize();
			Dimension chromeDimension = Chromeelement.getSize();
			
			Dimension size = driver.manage().window().getSize();
			int x0 = element.getLocation().getX() +  (int) (appDimension.getWidth() /2);
			int y0 = element.getLocation().getY() + (int) (appDimension.getHeight() /2);
			int x1 = Chromeelement.getLocation().getX() +  (int) (chromeDimension.getWidth() /2);
			int y1 = Chromeelement.getLocation().getY() + (int) (chromeDimension.getHeight() /2);
			
			System.out.println("x0  :"+ x0 + " x1  : "+ x1 + " y0  : "+ y0 + " y1 :"+ y1);
			MultiTouchAction touch = new MultiTouchAction(driver);
			touch.add(new TouchAction<>(driver).press(point(x0, y0))
					.waitAction(WaitOptions.waitOptions(Duration.ofSeconds(0))).moveTo(point(x1, y1)).release())
					.perform();

			
		} catch (Exception e) {
			
			reportStep("Not Able  to close the EarnKaro App in RedMi Device ", "FAIL");
			e.printStackTrace();
		}

	return true;
}
	
	public void closeEarnKaroAppInSamsung() {

		try {

			reportStep("About to kill the EarnKaro App from the back ground running .. (Device : Samsung)  ", "INFO");

			String xpathEarnKaroCloseButton = "//android.widget.ImageView[@content-desc='Close EarnKaro']";

			isElementLocatedByXpathPresent(xpathEarnKaroCloseButton);

			driver.findElement(By.xpath(xpathEarnKaroCloseButton)).click();

			reportStep("Successfully  killed the EarnKaro App from the back ground running ..(Device : Samsung)   ", "PASS");

		} catch (Exception e) {

			reportStep("Fail to kill the EarnKaro App from the back ground running ..(Device : Samsung)   ", "FAIL");
		}

		
	}
	
	public boolean closeEarnKaroAppInMoto() {
		
		try {

			reportStep("About to kill the EarnKaro App from the back ground running  (Device : Moto) ", "INFO");

			String xpathEarnKaroCloseButton = "	//android.widget.ImageView[@content-desc=\"Dismiss EarnKaro.\"]";

			isElementLocatedByXpathPresent(xpathEarnKaroCloseButton);

			driver.findElement(By.xpath(xpathEarnKaroCloseButton)).click();

			reportStep("Successfully  killed the EarnKaro App from the back ground running .. (Device : Moto)  ", "PASS");

		} catch (Exception e) {

			reportStep("Fail to kill the EarnKaro App from the back ground running .. (Device : Moto)  ", "FAIL");
		}


	return true;
}
	
	
	public boolean scrollFromDownToUpinApp_FourSecDuration() {
		
		
		try {
			Dimension size = driver.manage().window().getSize();
			int x0 = (int) (size.getWidth() * 0.5);
			int y0 = (int) (size.getHeight() * 0.2);
			int x1 = (int) (size.getWidth() * 0.5);
			int y1 = (int) (size.getHeight() * 0.4);
			MultiTouchAction touch = new MultiTouchAction(driver);
			touch.add(new TouchAction<>(driver).press(point(x1, y1))
					.waitAction(WaitOptions.waitOptions(Duration.ofSeconds(2))).moveTo(point(x0, y0)).release())
					.perform();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		reportStep("Successfully scrolled from Down to Up ", "INFO");
		return true;
	}
	
	public boolean scrollFromUpToDowninApp_FourSecDuration() {
		try {
			Dimension size = driver.manage().window().getSize();
			int x0 = (int) (size.getWidth() * 0.5);
			int y0 = (int) (size.getHeight() * 0.2);
			int x1 = (int) (size.getWidth() * 0.5);
			int y1 = (int) (size.getHeight() * 0.4);
			MultiTouchAction touch = new MultiTouchAction(driver);
			touch.add(new TouchAction<>(driver).press(point(x0, y0))
					.waitAction(WaitOptions.waitOptions(Duration.ofSeconds(2))).moveTo(point(x1, y1)).release())
					.perform();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		reportStep("Successfully scrolled from Down to Up ", "INFO");
		return true;
	}
	
	public boolean scrollFromUpToDown(MobileElement element) {
		
		try {
			Dimension size = element.getSize();
			int x0 = (int) (size.getWidth() * 0.5);
			int y0 = (int) (size.getHeight() * 0.2);
			int x1 = (int) (size.getWidth() * 0.5);
			int y1 = (int) (size.getHeight() * 0.4);
			MultiTouchAction touch = new MultiTouchAction(driver);
			touch.add(new TouchAction<>(driver).press(point(x0, y0))
					.waitAction(WaitOptions.waitOptions(Duration.ofSeconds(2))).moveTo(point(x1, y1)).release())
					.perform();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		reportStep("Successfully scrolled from Down to Up ", "INFO");
		return true;
	}

	public boolean scrollFromUpToDowninApp() {
		try {
			Dimension size = driver.manage().window().getSize();
			int x0 = (int) (size.getWidth() * 0.5);
			int y0 = (int) (size.getHeight() * 0.2);
			int x1 = (int) (size.getWidth() * 0.5);
			int y1 = (int) (size.getHeight() * 0.8);
			MultiTouchAction touch = new MultiTouchAction(driver);
			touch.add(new TouchAction<>(driver).press(point(x0, y0))
					.waitAction(WaitOptions.waitOptions(Duration.ofSeconds(2))).moveTo(point(x1, y1)).release())
					.perform();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		reportStep("Successfully scrolled from UP to Down ", "INFO");
		return true;
	}
	
	public boolean scrollFromUpToDowninAppFromTheMiddleOfTheScreen() {
		try {
			Dimension size = driver.manage().window().getSize();
			int x0 = (int) (size.getWidth() * 0.5);
			int y0 = (int) (size.getHeight() * 0.5);
			int x1 = (int) (size.getWidth() * 0.5);
			int y1 = (int) (size.getHeight() * 0.8);
			MultiTouchAction touch = new MultiTouchAction(driver);
			touch.add(new TouchAction<>(driver).press(point(x0, y0))
					.waitAction(WaitOptions.waitOptions(Duration.ofSeconds(2))).moveTo(point(x1, y1)).release())
					.perform();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		reportStep("Successfully scrolled from UP to Down ", "INFO");
		return true;
	}
	
	public boolean scrollFromDownToUpinAppFromTheMiddleOfTheScreen() {
		try {
			Dimension size = driver.manage().window().getSize();
			int x0 = (int) (size.getWidth() * 0.5);
			int y0 = (int) (size.getHeight() * 0.5);
			int x1 = (int) (size.getWidth() * 0.5);
			int y1 = (int) (size.getHeight() * 0.8);
			MultiTouchAction touch = new MultiTouchAction(driver);
			touch.add(new TouchAction<>(driver).press(point(x1, y1))
					.waitAction(WaitOptions.waitOptions(Duration.ofSeconds(2))).moveTo(point(x0, y0)).release())
					.perform();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		reportStep("Successfully scrolled from Down to Up ", "INFO");
		return true;
	}


	public boolean clickInApp() {
		try {
			
			Dimension size = driver.manage().window().getSize();
			int x0 = (int) (size.getWidth() * 0.5);
			int y0 = (int) (size.getHeight() * 0.5);
			int x1 = (int) (size.getWidth() * 0.5);
			int y1 = (int) (size.getHeight() * 0.8);
		
			TouchAction touch = new TouchAction(driver);
			touch.press(point(x0, y0)).release().perform();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	public boolean tapOnNonClickableElement(MobileElement ele) {

		reportStep("About to tap on the screen  to close the - info display", "INFO");
		try {
			int x0 = ele.getLocation().getX();
			int y0 = ele.getLocation().getY();
			MultiTouchAction touch = new MultiTouchAction(driver);
			touch.add(new TouchAction(driver).press(point(x0, y0)).release())
			.perform();
			
			reportStep("Successfully tapped on the screen", "INFO");
			return true;
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	

	}

	public boolean pullFile(String remotePath) {
		driver.pullFile(remotePath);
		return true;
	}
	
	public  void resetApp() {
		driver.resetApp();
	}

	public  void closeApp() {
		if (driver != null) {
			try {
				driver.closeApp();
			} catch (Exception e) {
			}
		}

	}

	//ToggleWifi
	public boolean toggleWifi() {
		
		try {
		((SupportsNetworkStateManagement) driver).toggleWifi();
		sleep(2000);
		return true;
		}catch (Exception e) {
			return false;
		}
	}
	
	// Not in iOS
	public boolean wiFiOffInAndorid() {

		((HasNetworkConnection) driver)
		.setConnection(new ConnectionStateBuilder().withAirplaneModeEnabled().build());
		return true;
	}

	public boolean wifiOffInIOS() {

		try {
			Dimension size = driver.manage().window().getSize();
			int x0 = (int) (size.getWidth() * 0.2);
			int y0 = (int) (size.getHeight() * 0.2);
			int x1 = (int) (size.getWidth());
			int y1 = (int) (size.getHeight());
			MultiTouchAction touch = new MultiTouchAction(driver);
			touch.add(new TouchAction<>(driver).press(point(x1, y1))
					.waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1))).moveTo(point(x0, y0)).release())
					.perform();

			Thread.sleep(1000);
			String value = driver.findElementByXPath("//XCUIElementTypeSwitch").getAttribute("value");
			if (value.equals("0"))
				clickByXpath("//XCUIElementTypeSwitch");
			new TouchAction(driver).press(point(100, 100)).release().perform();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;

	}

	public boolean wifiOnInIOS() {

		try {
			Dimension size = driver.manage().window().getSize();
			int x0 = (int) (size.getWidth() * 0.2);
			int y0 = (int) (size.getHeight() * 0.2);
			int x1 = (int) (size.getWidth());
			int y1 = (int) (size.getHeight());
			MultiTouchAction touch = new MultiTouchAction(driver);
			touch.add(new TouchAction<>(driver).press(point(x1, y1))
					.waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1))).moveTo(point(x0, y0)).release())
					.perform();

			Thread.sleep(1000);
			String value = driver.findElementByXPath("//XCUIElementTypeSwitch").getAttribute("value");
			if (value.equals("1"))
				clickByXpath("//XCUIElementTypeSwitch");
			new TouchAction(driver).press(point(100, 100)).release().perform();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;

	}

	// Not in iOS
	public boolean wiFiOnInAndroid() {
		((HasNetworkConnection) driver)
		.setConnection(new ConnectionStateBuilder().withWiFiEnabled().build());
		return true;
	}

	public boolean setPortraitOrientation() {
		driver.rotate(ScreenOrientation.PORTRAIT);
		return true;
	}

	public boolean setLanscapeOrientation() {
		driver.rotate(ScreenOrientation.LANDSCAPE);
		return true;
	}

	public void hideKeyboard() {
		
		if(Integer.parseInt(apiLevel)<=23) {
		
		try {
			
			reportStep("Hiding the Keyboard for the API level less than 23 ", "INFO");

			//sleep(1000);

			driver.hideKeyboard();

		}catch (Exception ee) {

			System.out.println("Hide keyboard exception");
			reportStep( ee.getMessage(), "INFO");

			reportStep("Not able to close the keyboard - Exception occured", "INFO");

		}
		
		}


	}

	public void hideKeyboard_All_APILevel() {

		try {

			reportStep("Hiding the Keyboard", "INFO");

			driver.hideKeyboard();

		}catch (Exception ee) {

			System.out.println("Hide keyboard exception");
			reportStep( ee.getMessage(), "INFO");

			reportStep("Not able to close the keyboard - Exception occured", "INFO");

		}

	}

	public String getOrientation() {
		return driver.getOrientation().toString();
	}

	public boolean enterValue(MobileElement ele, String data) {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.elementToBeClickable(ele));
		ele.clear();
		ele.sendKeys(data);
		return true;
	}
	
	public boolean click_WaitFor25Sec(MobileElement ele) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 25);
			wait.until(ExpectedConditions.elementToBeClickable(ele));
			ele.click();
			
		} catch (Exception e) {
			
			reportStep( e.getMessage(), "INFO");
			return false;
		}
		return true;
	}
	
	public boolean click(MobileElement ele) {
		
		
		
		try {
		
			ele.click();

		} catch (Exception e) {
			e.printStackTrace();
			reportStep( e.getMessage(), "INFO");

			return false;
		}
		return true;

	}

	public boolean clickAfterWait(MobileElement ele) {

		try {
			WebDriverWait wait = new WebDriverWait(driver, 30);
			wait.until(ExpectedConditions.elementToBeClickable(ele));
			ele.click();

		} catch (Exception e) {
			e.printStackTrace();
			reportStep( e.getMessage(), "INFO");

			return false;
		}
		return true;

	}

	public boolean clickOnElementVisibility(MobileElement element) {

		try {
			WebDriverWait wait = new WebDriverWait(driver, 30);
			wait.until(ExpectedConditions.visibilityOf(element));
			element.click();

		} catch (Exception e) {
			e.printStackTrace();

			return false;
		}
		return true;
	}

	public boolean click_WaitFor5Sec(MobileElement ele) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 5);
			wait.until(ExpectedConditions.elementToBeClickable(ele));
			ele.click();

		} catch (Exception e) {
			return false;
		}
		return true;
	}

	public String getText(MobileElement ele) {

		try {
			WebDriverWait wait = new WebDriverWait(driver, 30);
			wait.until(ExpectedConditions.visibilityOf(ele));
			reportStep("Successfully got the text value for the element "+ ele + " as : "+ ele.getText().trim()  , "PASS");
			return ele.getText().trim();

		}catch (Exception e) {

			reportStep("Not able to get the text for the element - "+ ele , "FAIL");
			reportStep( e.getMessage(), "INFO");
			return null;
		}
	}

	public boolean loadURL(String URL) {
		driver.get(URL);
		return true;
	}
	
	public MobileElement locateElement(String locator, String locValue) {
		try {
			switch (locator) {
			case("id"): return driver.findElementById(locValue);
			case("linkText"): return driver.findElementByLinkText(locValue);
			case("xpath"):return driver.findElementByXPath(locValue);
			case("name"): return driver.findElementByName(locValue);
			case("className"): return driver.findElementByClassName(locValue);
			case("accessID"):driver.findElementByAccessibilityId(locValue);
			}
		} catch (NoSuchElementException e) {			
			throw new RuntimeException();
		} catch (WebDriverException e) {
		}
		return null;
	}

	//*******************************************My own methods***************************************//
	
	public String getTextByXpath(String xpath) {
		
		String text = driver.findElementByXPath(xpath).getText();
		
		return text;
	}

	public boolean validateTheActualValueWithExpectedValue(String actual,String expected) {

		reportStep("About Asserting the actual value : "+ actual+ " With the expected value : "+ expected, "INFO");

		if(actual.trim().equals(expected.trim())) {

			reportStep("Successfully Assered the actual value : "+ actual+ " With the expected value : "+ expected, "PASS");

		}else {

			reportStep(" Assertion failed for the actual value : "+ actual+ " With the expected value : "+ expected, "FAIL");

			return false;
		}
		return true;


	}
	
	public boolean validateTheActualValueContainsExpectedValue(String actual,String expected) {

		reportStep("****************************************************************************", "INFO");
		reportStep("About Asserting the actual value : "+ actual+ " With the expected value : "+ expected, "INFO");

		if(actual.trim().contains(expected.trim())) {

			reportStep("Successfully Assered the actual value : "+ actual+ " Contains the expected value : "+ expected, "PASS");

		}else {

			reportStep(" Assertion Failed for the actual value : "+ actual+ " does not contain the expected value : "+ expected, "FAIL");

			return false;
		}

		reportStep("****************************************************************************", "INFO");
		return true;


	}

	public boolean validateTheActualValueWithExpectedValue(int actual,int expected) {

		reportStep("About Asserting the actual value : "+ actual+ " With the expected value : "+ expected, "INFO");

		if(actual == expected) {

			reportStep("Successfully Assered the actual value : "+ actual+ " With the expected value : "+ expected, "PASS");

		}else {

			reportStep(" Assertion failed for the actual value : "+ actual+ " With the expected value : "+ expected, "FAIL");

			return false;
		}
		return true;


	}

	public boolean validateTheElementPresence(MobileElement element) {

		try {

			reportStep("Validating the element Presence :  " +  element, "INFO");

			WebDriverWait wait = new WebDriverWait(driver, 30);
			wait.until(ExpectedConditions.visibilityOf(element));

			reportStep("The element "+element+"is present on the visible portion", "PASS");

		} catch (Exception e) {
			
			reportStep("Exception got is : " + e.getMessage(), "INFO");

			reportStep("The element "+element+"  is not present on the visible portion", "FAIL");

			return false;

		}

		return true;
	}
	
	public boolean validateTheElementPresence(MobileElement element, String continueExecution) {

		try {

			reportStep("Validating the element Presence ", "INFO");

			WebDriverWait wait = new WebDriverWait(driver, 40);
			wait.until(ExpectedConditions.visibilityOf(element));

			reportStep("The element "+element+"is present on the visible portion", "PASS");

		} catch (Exception e) {
			
			//Mention INFO for continue the execution, FAIL for stop execution
			reportStep("The element "+element+"  is not present on the visible portion", continueExecution);

			return false;

		}

		return true;
	}
	
	public boolean validateTheElementAbsence(MobileElement element) {

		try {

			reportStep("Validating the element Absence ", "INFO");

			WebDriverWait wait = new WebDriverWait(driver, 30);
			wait.until(ExpectedConditions.invisibilityOf(element));

			reportStep("The element "+element+" is not present on the visible portion -Expected behaviour ", "PASS");

		} catch (Exception e) {

			reportStep("The element "+element+" is  present on the visible portion - UnExpected behaviour ", "FAIL");

			return false;

		}

		return true;
	}

	public void validateTheElementAbsence(String xpath) {
		
		if(this.isDisplayedForCoOrdinatesByXpath(xpath)) {
			
			reportStep("Element "+xpath+" is present", "FAIL");
		}else {

			reportStep("Element "+xpath+" is not present", "PASS");
		}
		
		
		
		
	}
	
	public void validateTheElementAbsenceUsingId(String id) {
		
		if(this.isDisplayedForCoOrdinatesByID(id)) {
			
			reportStep("Element "+id+" is present", "FAIL");
		}else {

			reportStep("Element "+id+" is not present", "PASS");
		}
		
		
		
		
	}

	public boolean isElementLocatedByXpathPresent(String xpath) {

		try {
			WebDriverWait wait = new WebDriverWait(driver, 30);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
			reportStep("The Element Located By Xpath Is Present - xpath as : " +  xpath , "INFO");
			return true;

		}catch (Exception e) {
			String error = e.getMessage();
			System.out.println("Error "+ error);
			reportStep("TIME OUT EXCEPTION  : Failed  To Locate The Element Using Xpath Value as : " +  xpath, "INFO"); // Dont change the INFO status for Fail or Pass here
			return false;
		}

	}

	public boolean isElementLocatedByXpathAbsence(String xpath) {

		try {
			WebDriverWait wait = new WebDriverWait(driver, 40);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(xpath)));
			reportStep("The Element Located By Xpath Is not Present as expected - xpath as : " +  xpath , "INFO");
			return true;

		}catch (Exception e) {

			reportStep("TIME OUT EXCEPTION  : Located The Element Using Xpath Value as : " +  xpath, "FAIL");
			return false;
		}

	}

	public boolean isElementLocatedByIDPresent(String id) {

		try {
			WebDriverWait wait = new WebDriverWait(driver, 30);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.id(id)));
			reportStep("The Element Located By Xpath Is Present - ID as : " +  id , "INFO");
			return true;

		}catch (Exception e) {

			reportStep("TIME OUT EXCEPTION  : Failed  To Locate The Element Using ID Value as : " +  id, "INFO");

			return false;
		}

	}
	
	public boolean isElementLocatedByIDPresent(String id,long timeOutInSec) {

		try {
			WebDriverWait wait = new WebDriverWait(driver, timeOutInSec);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.id(id)));
			reportStep("The Element Located By Xpath Is Present - ID as : " +  id , "INFO");
			return true;

		}catch (Exception e) {

			reportStep("TIME OUT EXCEPTION  : Failed  To Locate The Element Using ID Value as : " +  id, "INFO");

			return false;
		}

	}
	
	public boolean isElementLocatedByXpathPresent(String xpath,long timeOutInSec) {

		try {
			WebDriverWait wait = new WebDriverWait(driver, timeOutInSec);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
			reportStep("The Element Located By Xpath Is Present - xapth as : " +  xpath , "INFO");
			return true;

		}catch (Exception e) {
			String error = e.getMessage();
			System.out.println("Error "+ error);
			reportStep("TIME OUT EXCEPTION  : Failed  To Locate The Element Using Xpath Value as : " +  xpath, "INFO"); // Dont change the INFO status for Fail or Pass here
			return false;
		}

	}
	
	public String getTestData(int index,String key) {

		return testdata.get(index).get(key).trim();

	}

	public String getLiveTestData(int index,String key) {

		return LiveTestData.get(index).get(key).trim();

	}
	
	public  boolean clickMultipleXCoordinatesByXpath(String Xpath, int yCoOrdinates, String ExpectedXpath) {

		int x = driver.findElementByXPath(Xpath).getLocation().getX();
		int y = driver.findElementByXPath(Xpath).getLocation().getY() + yCoOrdinates;
		System.out.println("Initial X: " + x + " and Y: " + y + " co-ordinates");
		boolean status = false;


		while (!isDisplayedForCoOrdinatesByXpath(ExpectedXpath)) {

			try {
				new TouchAction<>(driver).press(point(x, y)).release().perform();
				System.out.println("Clicked at the X: " + x + " and Y: " + y + " co-ordinates");
			} catch (Exception e) {
				System.out.println("Touch failed for :-X :" + x + "--Y :" + y);
				y = y + 25;
				x = driver.findElementByXPath(Xpath).getLocation().getX();
			}
			x = x + 25;

		}
		status = true;
		return status;

	}

	public  boolean clickMultipleXCoordinatesByXpath(String Xpath, int yCoOrdinates,int xCoOrdinates, String ExpectedXpath) {

		int x = driver.findElementByXPath(Xpath).getLocation().getX()+xCoOrdinates;
		int y = driver.findElementByXPath(Xpath).getLocation().getY() + yCoOrdinates;
		System.out.println("Initial X: " + x + " and Y: " + y + " co-ordinates");
		boolean status = false;


		while (!isDisplayedForCoOrdinatesByXpath(ExpectedXpath)) {

			try {
				new TouchAction<>(driver).press(point(x, y)).release().perform();
				System.out.println("Clicked at the X: " + x + " and Y: " + y + " co-ordinates");
			} catch (Exception e) {
				System.out.println("Touch failed for :-X :" + x + "--Y :" + y);
				y = y + 25;
				x = driver.findElementByXPath(Xpath).getLocation().getX();
			}
			x = x + 25;

		}
		status = true;
		return status;

	}

	public  boolean isDisplayedForCoOrdinatesByXpath(String Xpath) {
		
		try {
			WebDriverWait wait = new WebDriverWait(driver, 1);
			return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Xpath))).isDisplayed();
		} catch (Exception e) {
			return false;
		}
		
	}
	
	public  boolean isDisplayedForCoOrdinatesByID(String id) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 1);
			return wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(id))).isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}
	
	public boolean clickOnNoneOFTheAbove() {
		
		if(Integer.parseInt(apiLevel)>22) {
		
	try {
		
		reportStep("About to click on the None of the Above button from the Suggestion Popup", "INFO");
			
		MobileElement strButtonNoneOFTheAbove = driver.findElementByXPath("//android.widget.Button[@text='NONE OF THE ABOVE']");
		
		WebDriverWait wait = new WebDriverWait(driver, 30);
		
		wait.until(ExpectedConditions.visibilityOf(strButtonNoneOFTheAbove));
		
		reportStep("About to click on the None of the Above button from the Suggestion Popup", "INFO");
		
		strButtonNoneOFTheAbove.click();
		
		return true;
		
	} catch (Exception e) {
		
		System.out.println("NO google suggestion is coming  ");
		
		try {
			
			driver.hideKeyboard();
			
		}catch (Exception ee) {
			
			System.out.println("Hide keyboard exception");
			
			reportStep("Not able to close the keyboard", "INFO");
			
		}
		
	}
	return false;
		}
		return true;
		
	}
 	
	public boolean clickOnNoneOFTheAbove(MobileElement MobileElement) {
		
		if(Integer.parseInt(apiLevel)>22) {
		
		if (clickAfterWait(MobileElement)){
			
			reportStep("Successfully clicked on the element to Handle the Google Email or Mobile number suggesstion popup", "PASS");
		}else {
			
			reportStep("Failed to click on the web element where the Google suggestion popup appears", "INFO");
			
		}
			
		try {

			reportStep("About to click on the None of the Above button from the Suggestion Popup", "INFO");
			
			isElementLocatedByXpathPresent("//android.widget.Button[@text='None of the above']|//android.widget.Button[@text='NONE OF THE ABOVE']");
			
			MobileElement strButtonNoneOFTheAbove = driver.findElementByXPath("//android.widget.Button[@text='None of the above']|//android.widget.Button[@text='NONE OF THE ABOVE']");
			
			reportStep("About to click on the None of the Above button from the Suggestion Popup", "INFO");
			
			if (clickAfterWait(strButtonNoneOFTheAbove)){
				
				reportStep("Successfully clicked on None of the Above - link ", "PASS");
			}else {
				
				reportStep("Failed to click on the None of the above link ", "INFO");
				
			}
			
			return true;
			
		} catch (Exception e) {
			
			System.out.println("No Google suggestion is appeared ");
			
			reportStep("Failed to Click On the Email ID - None Of The Above Google Suggestion link ", "INFO");
			
			try {
				
				driver.hideKeyboard();
				
			}catch (Exception ee) {
				
				System.out.println("Hide keyboard exception");
				
				reportStep("Not able to close the keyboard", "INFO");
				
			}
			
			return false;
		}		
		}
		
		return true;
			
		}

	public int getListOfElementsSize(List<MobileElement> MobileElements) {

		return MobileElements.size();


	}

	public boolean scrollFromDownToUpTillRequiredElementIsVisible(String xpath) {

		int counter =0;
		System.out.println("set the counter");

		while(!isDisplayedForCoOrdinatesByXpath(xpath)) {

			counter ++;
			System.out.println("counter value as scrollling times is "+ counter);
			scrollFromDownToUpinApp_FourSecDuration();

			if(counter==25) {
				break;
			}
		}

		if(counter==25) {
			return false;
		}
		else {
			return true;
		}

	}

	public boolean scrollTillRequiredElementIsVisible(String xpath) {

		int counter =0;
		System.out.println("set the counter");
		scrollFromUpToDowninApp();
		scrollFromUpToDowninApp();

		while(!isDisplayedForCoOrdinatesByXpath(xpath)) {

			counter ++;
			System.out.println("counter value as scrollling times is "+ counter);
			scrollFromDownToUpinApp_FourSecDuration();

			if(counter==25) {
				break;
			}
		}

		if(counter==25) {
			return false;
		}
		else {
			return true;
		}

	}
	
	public boolean scrollTillRequiredElementIsVisibleFromDownToUp(String xpath) {

		int counter =0;

		while(!isDisplayedForCoOrdinatesByXpath(xpath)) {

			counter ++;
			System.out.println("counter value as scrolling times is "+ counter);
			scrollFromDownToUpinAppFromTheMiddleOfTheScreen();

			if(counter==25) {
				break;
			}
		}

		if(counter==25) {
			return false;
		}
		else {
			return true;
		}

	}

	public boolean scrollTillRequiredElementIsVisibleFromUpToDown(String xpath) {

		int counter =0;

		while(!isDisplayedForCoOrdinatesByXpath(xpath)) {

			counter ++;
			System.out.println("counter value as scrollling times is "+ counter);
			scrollFromUpToDowninAppFromTheMiddleOfTheScreen();

			if(counter==25) {
				break;
			}
		}

		if(counter==25) {
			return false;
		}
		else {
			return true;
		}

	}

	public boolean scrollTillRequiredElementIsVisibleFromDownToUpFor10Times(String xpath) {
		
		//scrollFromUpToDowninApp();
		//scrollFromUpToDowninApp();
		
		int counter =0;

		while(!isDisplayedForCoOrdinatesByXpath(xpath)) {

			counter ++;
			System.out.println("Scrolling for "+ counter +" time. To identify the xpath: " +xpath);
			scrollFromDownToUpinApp_FourSecDuration();

			if(counter==10) {
				break;
			}
		}

		if(counter==10) {
			return false;
		}
		else {
			return true;
		}

	}

	public  String getTheStoreNumber(String storeName) {

		String storeNum = storeName;
		storeNum= storeNum.substring(storeNum.indexOf(":")+1);
		System.out.println(storeNum);

		return storeNum;

	}

	//*******************************************New methods***************************************//
	
	public boolean selectValuesFromDropdown(MobileElement element, String dropDownValue) {

		reportStep("About to select value "+dropDownValue+" from drop down", "INFO");

		try {

			System.out.println("In try block about to select the value from the dropdown");
			reportStep("select the value  EarnKaro from dropdown", "INFO");
			
			WebDriverWait wait = new WebDriverWait(driver, 30);
			wait.until(ExpectedConditions.visibilityOf(element));
			Select select_Dropdown = new Select(element);			
			select_Dropdown.selectByVisibleText(dropDownValue);
			reportStep("Successfully selected value "+dropDownValue+" from dropdown", "PASS");
			return true;
				
		} catch (Exception e) {

			System.out.println("In Catch - Waited for 30 sec to get loaded the page");
			reportStep("In Catch - Waited for 30 seconds to get loaded the page : Exception is " + e.getMessage(), "INFO");
			return false;
		}
		
	}

	public boolean jsClickUsingXpath(MobileElement element) {

		try {

			System.out.println("About to verify the presence of the "+element+"");

			WebDriverWait wait = new WebDriverWait(driver, 30);
			wait.until(ExpectedConditions.elementToBeClickable(element));
			JavascriptExecutor executor = (JavascriptExecutor)driver;
			executor.executeScript("arguments[0].click();", element);
			return true;

		}catch (Exception e) {

			e.printStackTrace();
			System.out.println("Not able to verify the presence of the "+element+"");
			return false;
		}
	}

	public boolean jsClickUsingID(String elementID) {

		try {

			System.out.println("About to verify the presence of the "+elementID);
			WebDriverWait wait = new WebDriverWait(driver, 30);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.id(elementID)));

			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("document.getElementById('"+elementID+"').click();");
			return true;

		}catch (Exception e) {

			System.out.println("Not able to verify the presence of the "+elementID);
			return false;

		}

	}

	public boolean click_WaitFor80Sec(MobileElement ele) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 300);
			wait.until(ExpectedConditions.elementToBeClickable(ele));
			ele.click();
			
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	
	public boolean waitTillElementPresence(MobileElement ele) {
		
		try {
			reportStep("About to wait till the element appears ", "INFO");
			WebDriverWait wait = new WebDriverWait(driver, 20);
			wait.until(ExpectedConditions.elementToBeClickable(ele));
			return true;
			
		} catch (Exception e) {
			
			return false;
		}
	}

	public int getCurrentHoursInBST() {

		Date objDate = new Date();
		DateFormat objDateFormat = new SimpleDateFormat("HH");
		objDateFormat.setTimeZone(TimeZone.getTimeZone("Europe/London"));
		String txnDateTime = objDateFormat.format(objDate); 
		int int_Hour = Integer.parseInt(txnDateTime);	
		System.out.println("Hour ::: "+int_Hour);
		
		return int_Hour;
		

	}

	public int getCurrentMinutesInBST() {

		Date objDate1 = new Date();
		DateFormat objDateFormat1 = new SimpleDateFormat("mm");
		objDateFormat1.setTimeZone(TimeZone.getTimeZone("Europe/London"));
		String txnDateTime1 = objDateFormat1.format(objDate1); 
		int int_Minutes = Integer.parseInt(txnDateTime1);	 
		System.out.println("Hour ::: "+int_Minutes);
		
		return int_Minutes;
		

	}

	public int getCurrentHoursInUTC() {

		Date objDate = new Date();
		DateFormat objDateFormat = new SimpleDateFormat("HH");
		objDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
		String txnDateTime = objDateFormat.format(objDate); 
		int int_Hour = Integer.parseInt(txnDateTime);	
		System.out.println("Hour ::: "+int_Hour);
		
		return int_Hour;
		

	}

	public int getCurrentMinutesInUTC() {

		Date objDate1 = new Date();
		DateFormat objDateFormat1 = new SimpleDateFormat("mm");
		objDateFormat1.setTimeZone(TimeZone.getTimeZone("UTC"));
		String txnDateTime1 = objDateFormat1.format(objDate1); 
		int int_Minutes = Integer.parseInt(txnDateTime1);	 
		System.out.println("Hour ::: "+int_Minutes);
		
		return int_Minutes;
		

	}

	public boolean enterText(MobileElement element, int value) {
		
		try {
			WebDriverWait wait = new WebDriverWait(driver, 30);
			wait.until(ExpectedConditions.visibilityOf(element));
			element.clear();
			String stringvalue = Integer.toString(value);
			element.sendKeys(stringvalue);
			
			return true;
			
		} catch (Exception e) {
			e.printStackTrace();
			reportStep( e.getMessage(), "INFO");
			return false;
		}
		
	}

	public  boolean select_Dropdown(AppiumDriver driver, String dropdownXpath,String dropdownValue) {

		try {

			WebDriverWait wait = new WebDriverWait(driver, 15);

			wait.until(ExpectedConditions.elementToBeSelected(By.xpath(dropdownXpath)));

			reportStep("Successfully waited till the drop down is visible : dropdown value is  "+ dropdownValue , "INFO");


		}catch (Exception e) {

			reportStep("Failed to find the dropdown element : the dropdown value is  - "+ dropdownValue , "INFO");
		}

		try {

			Select select_Dropdown = new Select(driver.findElement(By.xpath(dropdownXpath)));

			select_Dropdown.selectByVisibleText(dropdownValue);
			reportStep("Successfully selected the value : " +dropdownValue  +" from the drop down ", "INFO");
			
			return true;

		}catch (Exception e) {

			reportStep("Failed to  select the value : " +dropdownValue  +" from the drop down ", "FAIL");
			
			return false;
		}
	}

	public boolean clickByID(AppiumDriver<MobileElement> driver, String ID) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 30);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.id(ID)));
			driver.findElementById(ID).click();
		} catch (Exception e) {
			
			e.printStackTrace();
			reportStep( e.getMessage(), "INFO");
		}
		return true;
	}

	public boolean clickByAccessebilityID(AppiumDriver<MobileElement> driver, String ID) {
		try {

			driver.findElementByAccessibilityId(ID).click();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	public boolean clickByXpath(AppiumDriver<MobileElement> driver, String Xpath) {
		try {
			//WebDriverWait wait = new WebDriverWait(driver, 30);
			//wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(Xpath)));
			Thread.sleep(5000);
			driver.findElementByXPath(Xpath).click(); 
			
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean enterTextByID(AppiumDriver<MobileElement> driver, String ID, String value) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 30);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.id(ID)));
			driver.findElementById(ID).clear();
			driver.findElementById(ID).sendKeys(value);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	public String getClipBoardText() {
		
		reportStep("About to get a text from clipboard" , "INFO");

		((HasClipboard) driver).getClipboard(ClipboardContentType.PLAINTEXT);
		String clipboardText = ((HasClipboard) driver).getClipboardText();
		
		reportStep("Successfully retrieved clipboardText: "+clipboardText+" from clipboard" , "PASS");

		return clipboardText;
	}
	
	public String getClipBoardText(AppiumDriver<MobileElement> driver) {
		
		reportStep("About to get a text from clipboard" , "INFO");

		((HasClipboard) driver).getClipboard(ClipboardContentType.PLAINTEXT);
		String clipboardText = ((HasClipboard) driver).getClipboardText();
		
		reportStep("Successfully retrieved clipboardText: "+clipboardText+" from clipboard" , "PASS");

		return clipboardText;
	}

	public void clickChromeApp() {
		
		try {

			String chromeAppXpath = "//android.widget.TextView[@content-desc='Chrome']|//android.widget.TextView[@text='Chrome']";

			if(isElementLocatedByXpathPresent(chromeAppXpath)) {
				
				reportStep("Verify the required chromeApp presence", "INFO");

			}else {
				reportStep("Not able to verify the required chromeApp presence", "FAIL");

			}

			MobileElement chromeApp = driver.findElement(By.xpath(chromeAppXpath));

			if (clickAfterWait(chromeApp)) {

				reportStep("Clicked on the chromeApp", "PASS");
			}else {

				reportStep("Fail to click on the chromeApp", "FAIL");

			} 

		}catch (Exception e) {

			reportStep("Not able to close the Chrome App ", "INFO");

		}

	}
	
	public void clickEarnkaroApp() {
		
		String earnkaroAppXpath = "//android.widget.TextView[@content-desc='EarnKaro']|//android.widget.TextView[@text='EarnKaro']";

		if (isElementLocatedByXpathPresent(earnkaroAppXpath)) {
			reportStep("Verify the required earnkaroApp presence", "INFO");
		} else {
			reportStep("Not able to verify the required earnkaroApp presence", "FAIL");
		}

		MobileElement earnkaroApp = driver.findElement(By.xpath(earnkaroAppXpath));

		if (clickAfterWait(earnkaroApp)) {
			reportStep("Clicked on the earnkaroAp to Open the app from back ground Running ", "PASS");
		}else {
			reportStep("Fail to click on the earnkaroApp to Open the app from back ground Running", "FAIL");
		}

	}

	public void clickWhatsAppApp() {
		
		String whatsAppAppXpath = "//android.widget.TextView[@content-desc='WhatsApp']";
		
		if(isElementLocatedByXpathPresent(whatsAppAppXpath)) {
			reportStep("Verify the required whatsAppApp presence", "INFO");

		}else {
			reportStep("Not able to verify the required whatsAppApp presence", "FAIL");

		}
		
		MobileElement whatsAppApp = driver.findElement(By.xpath(whatsAppAppXpath));
		
		if (clickAfterWait(whatsAppApp)) {

			reportStep("Clicked on the whatsAppApp", "PASS");
		}else {
			
			reportStep("Fail to click on the whatsAppApp", "FAIL");
			
		}
		
	}

	public void toggleApp() {
		
		reportStep("About to toggle app" , "INFO");
		
		((AndroidDriver<MobileElement>) driver).pressKey(new KeyEvent(AndroidKey.APP_SWITCH));
		
		//sleep(1000);

		reportStep("succefully toggled app" , "PASS");

	}
	
	public void toggleApp(AndroidDriver driver) {
			
			reportStep("About to toggle app" , "INFO");
			
			((AndroidDriver<MobileElement>) driver).pressKey(new KeyEvent(AndroidKey.APP_SWITCH));
			
			//sleep(1000);
	
			reportStep("succefully toggled app" , "PASS");
	
		}

	public void clickChromeApp(AppiumDriver<MobileElement> driver) {
		
		String chromeAppXpath = "//android.widget.TextView[@content-desc='Chrome']";
		
		if(isElementLocatedByXpathPresent(chromeAppXpath)) {
			reportStep("Verify the required chromeApp presence", "INFO");

		}else {
			reportStep("Not able to verify the required chromeApp presence", "FAIL");

		}
		
		MobileElement chromeApp = driver.findElement(By.xpath(chromeAppXpath));
		
		if (clickAfterWait(chromeApp)) {

			reportStep("Clicked on the chromeApp", "PASS");
		}else {
			
			reportStep("Fail to click on the chromeApp", "FAIL");
			
		}
		
	}
	
	public void clickEarnkaroApp(AppiumDriver<MobileElement> driver) {
		
		String earnkaroAppXpath = "//android.widget.TextView[@content-desc='EarnKaro']|//android.widget.TextView[@text='EarnKaro']";
		
		if(isElementLocatedByXpathPresent(earnkaroAppXpath)) {
			reportStep("Verify the required earnkaroApp presence", "INFO");

		}else {
			reportStep("Not able to verify the required earnkaroApp presence", "FAIL");

		}
		
		MobileElement earnkaroApp = driver.findElement(By.xpath(earnkaroAppXpath));
		
		if (clickAfterWait(earnkaroApp)) {

			reportStep("Clicked on the earnkaroApp", "PASS");
		}else {
			
			reportStep("Fail to click on the earnkaroApp", "FAIL");
			
		}
		
	}
	
	public void toggleApp(AppiumDriver<MobileElement> driver) {
		
		reportStep("About to toggle app" , "INFO");
		
		((AndroidDriver<MobileElement>) driver).pressKey(new KeyEvent(AndroidKey.APP_SWITCH));

		reportStep("succefully toggled app" , "PASS");

	}

	/********************************Methods for Languages starts********************************/

	public void clickOnRequiredText(String expected, String page) {
		
		MobileElement requiredText = null;

		reportStep("About click on the required text in "+page+"", "INFO");
					
		String requiredTextXpath = "//android.widget.TextView[@text='"+expected+"']";

		System.out.println("requiredTextXpath:: "+"//android.widget.TextView[@text='"+expected+"']");
		reportStep("requiredTextXpath:: "+"//android.widget.TextView[@text='"+expected+"']", "INFO");

		scrollTillRequiredElementIsVisibleFromDownToUpFor10Times(requiredTextXpath);

		if(isElementLocatedByXpathPresent(requiredTextXpath)) {
			
			reportStep("About to validate the presence of required text:: "+expected+" in "+page+"", "PASS");

		}else {
			reportStep("Not able to validate the presence of required text:: "+expected+" in "+page+"", "FAIL");

		}
		
		try {

		requiredText = driver.findElement(By.xpath(requiredTextXpath));
		
		} catch (Exception e) {

			reportStep("No such element Exeception - Handled . Expected element is : " + requiredTextXpath , "FAIL");
			
		}

		if(clickAfterWait(requiredText)) {

			reportStep("Successfully clicked on the required text:: "+expected+" in "+page+"", "PASS");

		}else {

			reportStep("Failed - Not able to click on the required text:: "+expected+" in "+page+"", "FAIL");
		}
			
	}

	public void clickOnPartialRequiredText(String expected, String page) {

		reportStep("About click on the required text in "+page+"", "INFO");
		
		MobileElement requiredText = null;
			
		String requiredTextXpath = "//android.widget.TextView[contains(@text,'"+expected+"')]";

		System.out.println("requiredTextXpath:: "+"//android.widget.TextView[contains(@text,'"+expected+"')]");
		reportStep("requiredTextXpath:: "+"//android.widget.TextView[contains(@text,'"+expected+"')]", "INFO");

		scrollTillRequiredElementIsVisibleFromDownToUpFor10Times(requiredTextXpath);

		if(isElementLocatedByXpathPresent(requiredTextXpath)) {
			reportStep("About to validate the presence of required text:: "+expected+" in "+page+"", "PASS");

		}else {
			reportStep("Not able to validate the presence of required text:: "+expected+" in "+page+"", "FAIL");

		}
				
		try {

			requiredText = driver.findElement(By.xpath(requiredTextXpath));
		
		} catch (Exception e) {

			reportStep("No such element Exeception - Handled . Expected element is : " + requiredTextXpath , "FAIL");
			
		}

		if(clickAfterWait(requiredText)) {

			reportStep("Successfully clicked on the required text:: "+expected+" in "+page+"", "PASS");

		}else {

			reportStep("Failed - Not able to click on the required:: "+expected+" text in "+page+"", "FAIL");
		}

	}

	public void validateRequiredText(String expected, String page) {

		reportStep("About validate on the required text in "+page+"", "INFO");
			
		String requiredTextXpath = "//android.widget.TextView[contains(@text,'"+expected+"')]";

		System.out.println("requiredTextXpath:: "+"//android.widget.TextView[@text='"+expected+"']");
		reportStep("requiredTextXpath:: "+"//android.widget.TextView[@text='"+expected+"']", "INFO");

		scrollTillRequiredElementIsVisibleFromDownToUpFor10Times(requiredTextXpath);

		if(isElementLocatedByXpathPresent(requiredTextXpath)) {
			reportStep("About to validate the presence of required text:: "+expected+" in "+page+"", "PASS");

		}else {
			reportStep_Language("Not able to validate the presence of required text:: "+expected+" in "+page+"", "FAIL", true);

		}
	}
	
	public void validateRequiredText_WithOutScrolling(String expected, String page) {

		reportStep("About validate on the required text in "+page+"", "INFO");
			
		String requiredTextXpath = "//android.widget.TextView[contains(@text,'"+expected+"')]";

		System.out.println("requiredTextXpath:: "+"//android.widget.TextView[@text='"+expected+"']");
		reportStep("requiredTextXpath:: "+"//android.widget.TextView[@text='"+expected+"']", "INFO");

		if(isElementLocatedByXpathPresent(requiredTextXpath)) {
			reportStep("About to validate the presence of required text:: "+expected+" in "+page+"", "PASS");

		}else {
			reportStep_Language("Not able to validate the presence of required text:: "+expected+" in "+page+"", "FAIL", true);

		}
	}


	public void validateRequiredText(String expected, int index, String page) {

		reportStep("About validate on the required text in "+page+"", "INFO");

		String requiredTextXpath = "(//android.widget.TextView[@text='"+expected+"'])["+index+"]";

		System.out.println("requiredTextXpath:: "+"(//android.widget.TextView[@text='"+expected+"'])["+index+"]");
		reportStep("requiredTextXpath:: "+"(//android.widget.TextView[@text='"+expected+"'])["+index+"]", "INFO");

		scrollTillRequiredElementIsVisibleFromDownToUpFor10Times(requiredTextXpath);

		if(isElementLocatedByXpathPresent(requiredTextXpath)) {
			reportStep("About to validate the presence of required text:: "+expected+" in "+page+"", "PASS");

		}else {
			reportStep_Language("Not able to validate the presence of required text:: "+expected+" in "+page+"", "FAIL", true);

		}

	}

	public void clickOnRequiredText(String expected, int index, String page) {

		reportStep("About click on the required text in "+page+"", "INFO");
		
		MobileElement requiredText = null;
			
		String requiredTextXpath = "(//android.widget.TextView[@text='"+expected+"'])["+index+"]";

		System.out.println("requiredTextXpath:: "+"(//android.widget.TextView[@text='"+expected+"'])["+index+"]");
		reportStep("requiredTextXpath:: "+"(//android.widget.TextView[@text='"+expected+"'])["+index+"]", "INFO");

		scrollTillRequiredElementIsVisibleFromDownToUpFor10Times(requiredTextXpath);

		if(isElementLocatedByXpathPresent(requiredTextXpath)) {
			reportStep("About to validate the presence of required text:: "+expected+" in "+page+"", "PASS");

		}else {
			reportStep("Not able to validate the presence of required text:: "+expected+" in "+page+"", "FAIL");

		}
			
		try {

			requiredText = driver.findElement(By.xpath(requiredTextXpath));
		
		} catch (Exception e) {

			reportStep("No such element Exeception - Handled . Expected element is : " + requiredTextXpath , "FAIL");
			
		}


		if(clickAfterWait(requiredText)) {

			reportStep("Successfully clicked on the required text:: "+expected+" in "+page+"", "PASS");

		}else {

			reportStep("Failed - Not able to click on the required text:: "+expected+" in "+page+"", "FAIL");
		}
			
	}

	public void validatePartialRequiredText(String expected, String page) {

		reportStep("About validate on the partial required text in "+page+"", "INFO");
			
		String requiredTextXpath = "//android.widget.TextView[contains(@text,'"+expected+"')]";

		System.out.println("requiredTextXpath:: "+"//android.widget.TextView[contains(@text,'"+expected+"')]");
		reportStep("requiredTextXpath:: "+"//android.widget.TextView[contains(@text,'"+expected+"')]", "INFO");

		scrollTillRequiredElementIsVisibleFromDownToUpFor10Times(requiredTextXpath);

		if(isElementLocatedByXpathPresent(requiredTextXpath)) {
			reportStep("About to validate the presence of partial required text:: "+expected+" in "+page+"", "PASS");

		}else {
			reportStep_Language("Not able to validate the presence of partial required text:: "+expected+" in "+page+"", "FAIL", true);

		}
			

	}
	
	public void validatePartialRequiredTextWithoutPassingClassName(String expected, String page) {

		reportStep("About validate on the partial required text in "+page+"", "INFO");
			
		String requiredTextXpath = "//*[contains(@text,'"+expected+"')]";

		System.out.println("requiredTextXpath:: "+"//*[contains(@text,'"+expected+"')]");
		reportStep("requiredTextXpath:: "+"//*[contains(@text,'"+expected+"')]", "INFO");

		scrollTillRequiredElementIsVisibleFromDownToUpFor10Times(requiredTextXpath);

		if(isElementLocatedByXpathPresent(requiredTextXpath)) {
			reportStep("About to validate the presence of partial required text:: "+expected+" in "+page+"", "PASS");

		}else {
			reportStep_Language("Not able to validate the presence of partial required text:: "+expected+" in "+page+"", "FAIL", true);

		}
			
	}

	public void clickOnPartialRequiredTextWithoutPassingClassName(String expected, String page) {

		reportStep("About click on the required text in "+page+"", "INFO");
		
		MobileElement requiredText = null;
			
		String requiredTextXpath = "//*[contains(@text,'"+expected+"')]";

		System.out.println("requiredTextXpath:: "+"//*[contains(@text,'"+expected+"')]");
		reportStep("requiredTextXpath:: "+"//*[contains(@text,'"+expected+"')]", "INFO");

		scrollTillRequiredElementIsVisibleFromDownToUpFor10Times(requiredTextXpath);

		if(isElementLocatedByXpathPresent(requiredTextXpath)) {
			reportStep("About to validate the presence of required text:: "+expected+" in "+page+"", "PASS");

		}else {
			reportStep("Not able to validate the presence of required text:: "+expected+" in "+page+"", "FAIL");

		}
				
		try {

			requiredText = driver.findElement(By.xpath(requiredTextXpath));
		
		} catch (Exception e) {

			reportStep("No such element Exeception - Handled . Expected element is : " + requiredTextXpath , "FAIL");
			
		}


		if(clickAfterWait(requiredText)) {

			reportStep("Successfully clicked on the required text:: "+expected+" in "+page+"", "PASS");

		}else {

			reportStep("Failed - Not able to click on the required:: "+expected+" text in "+page+"", "FAIL");
		}
			
	}

	/********************************Methods for Languages ends********************************/
	
	public String retrieveChildKeyValues(JSONObject jsonObj, String childKey) {
		
		return  jsonObj.get(childKey).toString().trim();
	}

	public void closeEarnKaroApp() {

		try {

			reportStep("About to kill the EarnKaro App from the back ground running ..  ", "INFO");

			String xpathEarnKaroCloseButton = "//android.widget.ImageView[@content-desc='Close EarnKaro']";

			isElementLocatedByXpathPresent(xpathEarnKaroCloseButton);

			driver.findElement(By.xpath(xpathEarnKaroCloseButton)).click();

			reportStep("Successfully  killed the EarnKaro App from the back ground running ..  ", "PASS");

		} catch (Exception e) {

			reportStep("Fail to kill the EarnKaro App from the back ground running ..  ", "FAIL");
		}

	}

	public void closeEarnKaroApp(String deviceName) {

		deviceName = deviceName.toLowerCase();

		switch (deviceName) {

		case "samsung":

			this.closeEarnKaroAppInSamsung();

			break;

		case "redmi":

			this.closeEarnKaroAppInRedmi();

			break;

		case "moto":

			this.closeEarnKaroAppInMoto();
			sleep(1); // This sleep is used to fix the moto device type issue.
			
			break;

		}



	}

	public void openNotification() {
		
		reportStep("About to Open the Notification Bar ", "INFO");
		((AndroidDriver) driver).openNotifications();
		reportStep("Successfully Open the Notification bar ", "PASS");
		
	}
	
	public void closeNotification() {
		
		reportStep("About to Close the Notification Bar ", "INFO");
		((PressesKey) driver).pressKey(new KeyEvent(AndroidKey.BACK));
		reportStep("Successfully Close the Notification bar ", "PASS");
		 
	}
	
	public void clearNotificationFromNotificationBar(String deviceName) {

		switch (deviceName) {

		case "samsung":

			reportStep("About to click on the Clear button from the Clear Notification - Samsung device ", "INFO");

			String samsungClearNotification = "com.android.systemui:id/clear_all";

			isElementLocatedByIDPresent(samsungClearNotification,5); //This is for Samsung Device

			if (click(driver.findElementById(samsungClearNotification))) {

				reportStep("Successfully clicked on the Clear Notification for the Samsung Devicee", "PASS");
				
			} else {

				reportStep("Failed - click on the Clear Notification for the Samsung Devicee", "FAIL");
			}

			break;

		case "redmi":

			reportStep("About to click on the Clear button from the Clear Notification In Redmi Device", "INFO");

			String redMIClearNotification = "//android.widget.ImageButton[@content-desc='Clear all notifications.']";


			boolean isClearButtonPresent = isElementLocatedByXpathPresent(redMIClearNotification,5); //This is for RedMi Device

			if (isClearButtonPresent) {
			
			if (click(driver.findElementByXPath(redMIClearNotification))) {

				reportStep("Successfully clicked on the Clear Notification for the RedMI Device", "PASS");
				
			} else {

				reportStep("Failed - click on the Clear Notification for the RedMI Devicee", "FAIL");
			}
			
			} else {
				
				reportStep(" No notification has arrived for Redmi device , so clear all notification button is not in view ", "INFO");
			}

			break;

		case "moto":

			reportStep("About to click on the Clear button from the Clear Notification In Moto Device", "INFO");

			String motoClearNotification = "com.android.systemui:id/dismiss_text";

			boolean isClearButtonPresent_Moto = isElementLocatedByIDPresent(motoClearNotification,5); //This is for Moto Device

			if (isClearButtonPresent_Moto) {
			
			if (click(driver.findElementById(motoClearNotification))) {

				reportStep("Successfully clicked on the Clear Notification for the Moto Device", "PASS");
				
			} else {

				reportStep("Failed - click on the Clear Notification for the Moto Devicee", "FAIL");
			}
			
			} else {
				
				reportStep(" No notification has arrived for Moto device , so clear all notification button is not in view ", "INFO");
			}

			break;

		}

		
		
		
		
	}
	
	public boolean isElementLocatedByXpathPresent(AppiumDriver<MobileElement> driver,String xpath) {

		try {
			WebDriverWait wait = new WebDriverWait(driver, 40);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
			reportStep("The Element Located By Xpath Is Present - xapth as : " +  xpath , "INFO");
			return true;

		}catch (Exception e) {

			reportStep("TIME OUT EXCEPTION  : Failed  To Locate The Element Using Xpath Value as : " +  xpath, "INFO");
			return false;
		}

	}
	
	
	
}