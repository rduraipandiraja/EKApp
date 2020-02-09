package com.app.ck.pages;

import java.util.List;

import org.openqa.selenium.By;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.app.ck.base.WrapperMethods;
import com.app.ck.utilities.Utilities;
import com.aventstack.extentreports.ExtentTest;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class SearchPage extends WrapperMethods {

	//Constructor call
	public SearchPage(AppiumDriver<MobileElement> driver, ExtentTest testInfo) {

		WebDriverWait wait = new WebDriverWait(driver, 30);

		this.driver = driver;
		this.testInfo = testInfo;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);


		reportStep("Waiting for the search page", "INFO");
		try {
			wait.until(ExpectedConditions.visibilityOf(searchbar));
			reportStep("Successfully landed on the Search page ", "PASS");

		}catch(Exception e) {

			reportStep(e.getMessage(), "INFO");
			reportStep("Not able to land on the search page ", "FAIL");
		}

	}

	//Variable initialization
	@FindBy(how = How.XPATH, using = "//android.widget.EditText[@text='Type product or retailer name']") 
	MobileElement searchbar;

	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='ACTIVATE CASHBACK']")
	MobileElement storeCardButton;

	@FindBy(how = How.XPATH, using = "//android.widget.TextView[contains(@text,'View All')]")
	MobileElement viewAllLink;

	@FindBy(how = How.XPATH, using = "(//android.widget.ImageView)[5]")
	MobileElement searchResultsInProducts;

	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text= 'ACTIVATE REWARDS']|//android.widget.TextView[@text= 'ACTIVATE CASHBACK']")
	MobileElement activateRewardsButton;

	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@content-desc='txt_storeCard_shortDescription']")
	MobileElement searchResultsInStoresImage;
	
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@content-desc=\"icon_search_goBack\"]") 
	MobileElement backButtonClick;

	//Method creation

	public SearchPage enterTextIntoTheSearchBar() {

		String searchItem = getTestData(2, "Searchstore");

		reportStep("Entering the text into the searchbar", "INFO");

		if(enterText(searchbar,searchItem)){

			reportStep("Successfully enter the text "+searchItem +  " on the searchbar  ", "PASS");

		}else {

			reportStep("Failed to enter the text "+searchItem +  " on the searchbar  ", "FAIL");
		}

		pressEnter();


		return this;

	}

	public SearchPage enterTextIntoTheSearchBar(String searchItem ) {

		reportStep("Entering the text into the searchbar", "INFO");

		if(enterTextWithOutHideKeyboard(searchbar,searchItem)){

			reportStep("Successfully enter the text "+searchItem +  " on the searchbar  ", "PASS");

		}else {

			reportStep("Failed to enter the text "+searchItem +  " on the searchbar  ", "FAIL");
		}

		pressEnter();


		return this;



	}

	public SignInPage clickOnStoreCardButton() {

		reportStep("About to click on thViewAllLinke Home page store card button", "INFO");

		if(click(storeCardButton)) {

			reportStep("Successfully clicked on the store card button from the Home page", "PASS");
		}else {

			reportStep("Failed to click on the store card button from the Home page", "FAIL");
		}

		return new SignInPage(driver, testInfo);
	}

	public SearchPage clickOnViewAll() {

		reportStep("About to click on the View all link on the search in results  page", "INFO");

		if(click(viewAllLink)) {

			reportStep("Successfully clicked on the View all link on the search in store result page ", "PASS");
		}else {

			reportStep("Failed to click on the View all link on the search in store result page", "FAIL");
		}

		return this;
	}

	public ProductPage clickOnSearchResultsInProducts() {

		reportStep("About to click Search Results in Products ", "INFO");

		if(click(searchResultsInProducts)) {

			reportStep("Successfully clicked on the search results in Products ", "PASS");
		}else {

			reportStep("Failed to click on the searchResultsInProducts ", "FAIL");
		}

		return new ProductPage(driver, testInfo);
	}

	public SignInPage clickOnSearchResultsInCoupons() {

		reportStep("About to click Search Results in Coupons ", "INFO");

		scrollFromDownToUpinApp();

		scrollFromDownToUpinApp();

		if(click(activateRewardsButton)) {

			reportStep("Successfully clicked on the search results in Coupons ", "PASS");
		}else {

			reportStep("Failed to click on the searchResultsIn Coupons ", "FAIL");
		}

		return new SignInPage(driver, testInfo);
	}

	public StoreDetailPage clickOnSearchedStoreImage() {

		reportStep("About to click on the searched store image at the search page ", "INFO");


		if(click(searchResultsInStoresImage)) {

			reportStep("Successfully clicked on the store searched image which navigate to the store page ", "PASS");

		}else {

			reportStep("Fail to click on the store searched at the search page ","FAIL");
		}

		return new StoreDetailPage(driver, testInfo);
	}

	public void validateInvisibilityOfStore(String storeName) {


		reportStep("About to click on the store image card bases on the store short description number", "INFO");
		storeName= storeName.substring(storeName.indexOf(":")+1);
		System.out.println(storeName);

		int intStoreSize = driver.findElementsByXPath("//android.widget.TextView[contains(@text,'description:"+storeName+"')]").size();

		if(intStoreSize==0) {

			reportStep("The store mapped for the Desktop/Mobile/Tablet is not applicable for App", "PASS");

		}else {

			reportStep("The store mapped for the Desktop/Mobile/Tablet is  applicable for App please look into this", "FAIL");
		}


	

	}

	public String getViewAllOffersVouchersCount(String storeNum) {
		
		String xpath = "//android.widget.TextView[contains(@text,'"+storeNum+"')]/../following-sibling::android.view.ViewGroup/android.widget.TextView[contains(@text,'View All Offers')]|//android.widget.TextView[contains(@text,'View All Offers (7)')]";
		
		isElementLocatedByXpathPresent(xpath);
		
		 MobileElement ViewAllOffersLink = driver.findElementByXPath(xpath);
		
		String str = getText(ViewAllOffersLink);
		
		String onlyDigitFromTheString = Utilities.extractOnlyDigitFromString(str);
		
		reportStep("The voucher count in the store card is  :"+  onlyDigitFromTheString,"INFO");
		
		System.out.println("The voucher count in the store card is  : " + onlyDigitFromTheString);
		
		return onlyDigitFromTheString;
		
	}
	
	public SearchPage validateTheNumberOfVoucherCountInStoreCard(String expected,String storeNum) {
		
		
		reportStep("About to validate the store card voucher count ", "INFO");
		
		String actual = getViewAllOffersVouchersCount(storeNum);
		
		validateTheActualValueWithExpectedValue(actual, expected);
		
		return this;
		
		
		
	}
	
	public RetailerCategoryPage clickBackButton() {

		reportStep("About to click on back button in search page", "INFO");

		if(click(backButtonClick)) {

			reportStep("Successfully clicked on the back button in search page", "PASS");
		}else {

			reportStep("Failed to  click on the back button in search page", "FAIL");
		}
		
	//	driver.navigate().back();

		return new RetailerCategoryPage(driver, testInfo);
		
	}

	public StoreDetailPage clickStoreCardShortDescription(String shortDesc) {

		reportStep("About to click store card short description in store page", "INFO");
		
		String shortDescription = "//android.widget.TextView[@text='"+shortDesc.trim()+"']";
		
		scrollTillRequiredElementIsVisible(shortDescription);
		
		if(click(driver.findElement(By.xpath(shortDescription)))){
			
			reportStep("Successfully clicked short description "+shortDesc+"", "PASS");
			
		}else {
			
			reportStep("Failed to click short description "+shortDesc+"", "FAIL");
		}
		
		return new StoreDetailPage(driver, testInfo);
			
	}
	
	public StoreDetailPage clickStoreCardShortDescriptionOnLive(String shortDesc) {

		reportStep("About to click store card short description in store page", "INFO");
		
		String shortDescription = "//android.widget.TextView[contains(@text,'"+shortDesc.trim()+"')]";
		
		scrollTillRequiredElementIsVisible(shortDescription);
		
		if(click(driver.findElement(By.xpath(shortDescription)))){
			
			reportStep("Successfully clicked short description "+shortDesc+"", "PASS");
			
		}else {
			
			reportStep("Failed to click short description "+shortDesc+"", "FAIL");
		}
		
		return new StoreDetailPage(driver, testInfo);
			
	}

	public SearchPage scrollTillRequiredStoreShortDescIsVisible(String storeShrotDesc) {
		
		String xPath = "//android.widget.TextView[@text='"+storeShrotDesc+"']";
		
		reportStep("About to scroll till the xpath value " + xPath + " Is visible ", "Info");
		if(scrollTillRequiredElementIsVisible(xPath)) {
			reportStep("Successfully Scrolled till the element's  Xpath " + xPath + " is Presence", "PASS");
		} else {
			reportStep("Failed to Scroll till the element's  Xpath " + xPath + " is Presence", "FAIL");
		}
		
		return this;
	}
	
	public StoreDetailPage clickOnRequiredStoreShortDesc(String storeShrotDesc) {
		
		reportStep("About to click on short Desc on Search Result Store ", "INFO");
		
		String xPath = "//android.widget.TextView[@text='"+storeShrotDesc+"']";
	
		if(clickByXpath(xPath)) {
			
			reportStep("Successfully clicked on Required Store Short Description - Xpath is " + storeShrotDesc, "PASS");
		} else {
			
			reportStep("Failed to click on Required Store Short Description - Xpath is " + storeShrotDesc, "FAIL");
		}
		
		return new  StoreDetailPage(driver, testInfo);
	}

	
}
