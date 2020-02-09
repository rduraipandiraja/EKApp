package com.app.ck.pages;

import java.util.List;

import org.apache.http.conn.scheme.LayeredSchemeSocketFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.app.ck.base.WrapperMethods;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class HomePage extends WrapperMethods {
	
	//Constructor initialization
	public HomePage(AppiumDriver<MobileElement> driver, ExtentTest testInfo){

		this.driver = driver;
		this.testInfo = testInfo;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);

		WebDriverWait wait = new WebDriverWait(driver, 30);

		reportStep("Waiting for the Home page", "INFO");
		
		try {
			
			String xpath 	= "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.ImageView";

			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
		
			driver.findElementByXPath(xpath).click();
			
		}catch (Exception e) {
			
			System.out.println("NO close popup is present ");
			
		}


		try {
			
			wait.until(ExpectedConditions.visibilityOf(bannerImg));
			reportStep("Successfully landed on the Home page ", "PASS");

		}catch (Exception e) {

			reportStep(e.getMessage(), "INFO");
			reportStep("Not able to land on the Home page", "FAIL");
		} 

	}
	
	//Variable creation
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='Home']")
	MobileElement homeIcon;
	
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='Make Links']")
	MobileElement makeLinksIcon;

	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='History']")
	MobileElement historyIcon;
	
	@FindBy(how = How.XPATH, using = "//android.widget.ImageView[@content-desc='img_banner_select']")
	MobileElement bannerImg;
	
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='Profile']")
	MobileElement profileIcon;
	
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='Recent Clicks']")
	MobileElement recentClicksIcon;
	
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='ACTIVATE CASHBACK']|//android.widget.TextView[@text='ACTIVATE REWARDS']")
	MobileElement storeCardButton;
			
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@content-desc='txt_recommendStoreCard_shortDescription']|(//android.widget.ImageView[@content-desc='img_storeCard_imageValue'])[1]")
	MobileElement storeCardImage;

	@FindBy(how = How.XPATH, using = "(//android.widget.TextView)[1]")
	MobileElement HamburgerIcon;

	@FindBy(how = How.XPATH, using = "(//android.widget.TextView)[2]") 
	MobileElement searchIcon;
	
	@FindBy(how = How.XPATH, using = "(//android.widget.TextView[@content-desc='txt_recommendStoreCard_shortDescription'])[1]") 
	MobileElement storecardShortDesc; ///This xpath is being changed by developer - ask them to give correct xpath

	@FindBy(how = How.XPATH, using = "(//android.widget.TextView[@text='Home'])[2]") 
	MobileElement sliderMenuHomeIcon;
	
	String sliderMenuHomeIconXpath = "(//android.widget.TextView[@text='Home'])[2]";

	@FindBy(how = How.XPATH, using = "(//android.widget.ImageView)[1]") 
	MobileElement cashkaroLogo;

	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='App Exclusives']") 
	MobileElement sliderMenuAppExclusives;

	@FindBy(how = How.XPATH, using = "(//android.widget.TextView[@text='Recent Clicks'])[2]|//android.widget.TextView[@text='Recent Clicks']")
	MobileElement recentClickBottom ;
	
	//EarnKaro Whats app scenario :
	
	@FindBy(how = How.XPATH, using = "(//android.view.ViewGroup[@content-desc=\"txt_storeCardSearch_whatsapp_share_top\"])[1] ")
	MobileElement shareNow ;

	@FindBy(how = How.ID, using = "com.whatsapp:id/menuitem_search")
	MobileElement whatsAppSearchIcon ;

	@FindBy(how = How.XPATH, using = "//android.widget.EditText[@text='Type product or retailer name']") 
	MobileElement searchbar;
	

	//Method creation : 
	
	public ProfilePage clickOnProfileIcon() {

		reportStep("About to click on the Profile icon ", "INFO");

		if(click(profileIcon)) {

			reportStep("Successfully clicked on the Profile icon at the Home page", "PASS");
		}else {

			reportStep("Failed to  click on the Profile icon at the Home page", "FAIL");
		}

		return new ProfilePage(driver, testInfo);
	}
	
	public SignedInProfilePage clickOnProfileIconForSignedUser() {
		
		reportStep("About to click on the Profile icon for  Signed user ", "INFO");

		if(click(profileIcon)) {

			reportStep("Successfully clicked on the Profile icon at the Home page for  Signed user ", "PASS");
		} else {

			reportStep("Failed to  click on the Profile icon at the Home page for  Signed user", "FAIL");
		}

		return new SignedInProfilePage(driver, testInfo);
	}
	
	public SignInPage clickOnStoreCardButton() {

		reportStep("About to click on the Home page store card button", "INFO");

		if(click(storeCardButton)) {

			reportStep("Successfully clicked on the store card button from the Home page", "PASS");
		}else {

			reportStep("Failed to click on the store card button from the Home page", "FAIL");
		}

		return new SignInPage(driver, testInfo);
	}

	
	public SliderScreenPage clickOnHamburgerIcon() {
		
		String hamburgerSymbol = getTestData(7, "hamburgerSymbol");
		
		String hamburgerMenu = "//android.widget.TextView[contains(@text,'"+hamburgerSymbol+"')]";
		
		MobileElement HamburgerIcon = driver.findElement(By.xpath(hamburgerMenu));
		
		if(click(HamburgerIcon)){
			
			reportStep("Sucessfully clicked on the Hamburger icon ", "PASS");
			
		}else {
			
			reportStep("Failed to click on the Hamburger icon", "FAIL");
		}
		
		return new SliderScreenPage(driver, testInfo);
	}
		
	public SearchPage clickOnSearchIcon() {
		
		reportStep("About to click on the Search Icon in the Home page ", "INFO");
		
		String bannerXpath = "//android.widget.ImageView[@content-desc='img_banner_select']";
		
		isElementLocatedByXpathPresent(bannerXpath);
		
		if(clickAfterWait(searchIcon)){
			
			reportStep("Sucessfully clicked on the Search icon ", "PASS");
			
		}else {
			
			reportStep("Failed to click on the Search icon", "FAIL");
			
		}
		
		return new SearchPage(driver, testInfo);
	}

	public HomePage clickOnProfileIcon_enhanced() {

		reportStep("About to click on the Profile icon ", "INFO");

		if(click(profileIcon)) {

			reportStep("Successfully clicked on the Profile icon at the Home page", "PASS");
		}else {

			reportStep("Failed to  click on the Profile icon at the Home page", "FAIL");
		}
		
		return this;
	}

	public HomePage validateTheAbsenceOfSliderMenu() {

		reportStep("About to validate the absence of slider menu ", "INFO");
		
		validateTheElementAbsence(sliderMenuHomeIconXpath);
		
		return this;
			
	}

	//EarnKaro special :
	public HomePage clickOnShareNowButton() {

		reportStep("About to click on the Share Now button from the Home Page  ", "INFO");

		if(clickAfterWait(shareNow)){

			reportStep("Successfully clicked on the Share Now link from the Home page ", "PASS");

		}else{

			reportStep("Failed to click on the Share Now Link from the Home Page ", "FAIL");
		}

		return this;
	}

	public MakeProfitLink clickOnMakeLinksIcon() {

		reportStep("About to click on the Make links from the Home Page ", "INFO");

		if(click(makeLinksIcon)) {

			reportStep("Successfully clicked on the Make Link from the Home Page ", "PASS");
		} else {

			reportStep("Failed to click on the Make Link from the Home Page ", "FAIL");
		}


		return new MakeProfitLink(driver, testInfo);
	}

	public HistoryPage clickOnHistoryIcon() {

		reportStep("About to click on the History from the Home Page ", "INFO");

		if(click(historyIcon)) {

			reportStep("Successfully clicked on the History from the Home Page ", "PASS");
		} else {

			reportStep("Failed to click on the History from the Home Page ", "FAIL");
		}


		return new HistoryPage(driver, testInfo);
	}

	public HomePage validateNetworkErrorsInHomePage() {
		
		reportStep("About to TurnOff the Wifi", "Info");
		//Wifi - ON to OFF
		toggleWifi();
		
		new NetworkErrorPage(driver, testInfo).
		validateNetworkPageErrors();
		
		//Wifi - OFF to ON
		toggleWifi();
		new HomePage(driver, testInfo);
		reportStep("Successfully Turned On the Wifi ", "Pass");
		return this;
		
	}
	
	
	
	
}