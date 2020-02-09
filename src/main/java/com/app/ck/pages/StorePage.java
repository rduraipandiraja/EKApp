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

public class StorePage extends WrapperMethods {
	

	//Constructor call to initialize the driver object
	public StorePage(AppiumDriver<MobileElement> driver, ExtentTest testInfo) {

		WebDriverWait wait = new WebDriverWait(driver, 30);

		this.driver = driver;
		this.testInfo = testInfo;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);

		reportStep("Waiting for the store page", "INFO");
		try {
			
			scrollFromUpToDowninApp();
			wait.until(ExpectedConditions.visibilityOf(storeAppShortDesc));
			
			reportStep("Successfully landed on the StorePage ", "PASS");

		}catch(Exception e) {

			reportStep(e.getMessage(), "INFO");
			reportStep("Not able to land on the Store page", "FAIL");
		}

	}

	//Variable initialization
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@content-desc='btn_store_showCashbackRates']") 
	MobileElement showCashbackRatesText;
	
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='ACTIVATE CASHBACK']") 
	MobileElement storeMainCTA;
	
	// need to add the occurate xpath
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@content-desc='txt_store_shortDescription']") 
	MobileElement storeAppShortDesc;
	
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@content-desc='btn_store_activate']") 
	MobileElement storeMainCtaButton;
	
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@content-desc='btn_store_grabDeal']") 
	MobileElement storeGrabDealMainCtaButton;
	
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@content-desc='btn_store_showCashbackRates']") 
	MobileElement showCashbackRatesDropDownlink;
	
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@content-desc='txt_store_primaryCashbackAmount']") 
	MobileElement normalPrimaryCashbackValue;
	
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@content-desc='txt_store_secondaryCashbackAmount']") 
	MobileElement normalSecondaryCashbackValue;
	
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@content-desc='txt_store_primaryCashbackDetails']") 
	MobileElement normalPrimaryCashbackRateDetails;
	
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@content-desc='txt_store_secondaryCashbackDetails']") 
	MobileElement normalSecondaryCashbackRateDetails;
	
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@content-desc='txt_store_secondaryCashbackAmount']") 
	List<MobileElement>  listOfNormalSecondaryCashbackRateAmount;
	
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@content-desc='txt_store_secondaryCashbackDetails']") 
	List<MobileElement> listOfNormalSecondaryCashbackRateDetails;
	
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@content-desc='txt_store_closeCashback']") 
	MobileElement closeButton;
	
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@content-desc='txt_store_hideCashbackRates']") 
	MobileElement hideCashBackRatesDropDownlink;
	
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@content-desc='radio_store_allUsersRadio']") 
	MobileElement allUserRadioButton;
	
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@content-desc='radio_store_newUsersRadio']") 
	MobileElement newUserRadioButton;
	
	@FindBy(how = How.XPATH, using = "//android.widget.Spinner[@content-desc='st_store_categoryDropDown']") 
	MobileElement selectCategoryDropDown;
	
	@FindBy(how = How.XPATH, using = "") 
	MobileElement voucherWithOutCodeTitle;
	
	@FindBy(how = How.XPATH, using = "") 
	MobileElement voucherWithCodeTitle;
	
	@FindBy(how = How.XPATH, using = "") 
	MobileElement voucherWithOutCodeAppDescription;
	
	@FindBy(how = How.XPATH, using = "") 
	MobileElement voucherWithCodeAppDescription;
	
	@FindBy(how = How.XPATH, using = "") 
	MobileElement seeDetailsLink;
	
	@FindBy(how = How.XPATH, using = "") 
	MobileElement hideDetailsLink;
	
	@FindBy(how = How.XPATH, using = "") 
	MobileElement storeFullDescription;
	
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='You May Also Like']") 
	MobileElement youMayAlsoLike;
	
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='About Retailer']") 
	MobileElement aboutRetailer;
	
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='Terms & Conditions']") 
	MobileElement termsAndCondition;
	
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@content-desc='txt_store_voucherExclusiveText']") 
	List<MobileElement> listOfvoucherExpiry;
	
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@content-desc='txt_store_voucherSeeHideDescription']") 
	List<MobileElement> listOfVoucherDescription ;
	
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@content-desc='txt_store_voucherOfferType']") 
	MobileElement CommonofferType;
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='Increased']") 
	List<MobileElement> increasedofferType;
	
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='Exclusive']") 
	List<MobileElement> exclusiveofferType;
	
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@content-desc='btn_store_voucherActivateDeal']|//android.widget.TextView[@content-desc='btn_store_voucherGrabDeal']") 
	List<MobileElement> ListOfvoucherCTAButtons;
	
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@content-desc='btn_store_voucherGrabDeal']") 
	List<MobileElement> ListOfN18voucherCTAButtons;
	
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[contains(@text,'Primary calender')]/following-sibling::android.widget.TextView[contains(@text,'Expires')]") 
	List<MobileElement> primaryCalendarCashbckExpiryTag;
	
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[contains(@text,'Secondary calender')]/following-sibling::android.widget.TextView[contains(@text,'Expires')]") 
	List<MobileElement> secondaryCalendaryCashbackExpiryTag;
	
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@content-desc='txt_store_aboutFullDescription']") 
	MobileElement appFullDescription;
	
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@content-desc='txt_store_termsAndConditions']") 
	MobileElement appTermsAndConditionDetails;
	
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[contains(@text,'Voucher :Seven')]") 
	MobileElement vs;
	
	@FindBy(how = How.XPATH, using = "//android.widget.CheckedTextView[contains(@text,'Select Category')]") 
	MobileElement SelectCategoryDropdownSelectCategory;
	@FindBy(how = How.XPATH, using = "//android.widget.CheckedTextView[contains(@text,'All')]") 
	MobileElement SelectCategoryDropdownAll;
	@FindBy(how = How.XPATH, using = "//android.widget.CheckedTextView[contains(@text,'PPS ATM CASHBACK CATEGORY (7)')]") 
	MobileElement SelectCategoryDropdownPPSATMCASHBACKCAT_7;
	@FindBy(how = How.XPATH, using = "//android.widget.CheckedTextView[contains(@text,'PPS ATM NETWORK 18 CATEGORY (7)')]") 
	MobileElement SelectCategoryDropdownPPSATMN18CAT_7;
	@FindBy(how = How.XPATH, using = "//android.widget.CheckedTextView[contains(@text,'PPS ATM REWARDS CATEGORY (7)')]") 
	MobileElement SelectCategoryDropdownPPSATMREWARDCAT_7;
	@FindBy(how = How.XPATH, using = "(//android.widget.ImageView)[1]") 
	MobileElement backButtonClick;
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='Profile']")
	MobileElement profileIcon;

	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='Search']") 
	MobileElement searchIcon;

	//Method call
	public SignInPage clickOnStoreMainCTA() {

		scrollFromUpToDowninApp();

		reportStep("About to click on the store page Main CTA ", "INFO");

		if(click(storeMainCtaButton)) {

			reportStep("Successfully clicked on the store MainCTA button", "PASS");
		}else {

			reportStep("Fail to  click on the store MainCTA button", "FAIL");
		}

		return new SignInPage(driver, testInfo);
	}
	
	//Method call
	public IntermediateRetailerPage clickOnStoreMainCTAForSignedInUser() {

		scrollFromUpToDowninApp();

		reportStep("About to click on the store page Main CTA for signed In user ", "INFO");

		if(click(storeMainCtaButton)) {

			reportStep("Successfully clicked on the store MainCTA button Sign in user ", "PASS");
		}else {

			reportStep("Fail to  click on the store MainCTA button for Signed in user ", "FAIL");
		}

		return new IntermediateRetailerPage(driver, testInfo);
	}

	//Method call
	public SignInPage clickOnStoreGrabDealMainCTA() {

		reportStep("About to click on the store page Main CTA GrabDeal button", "INFO");

		if(click(storeGrabDealMainCtaButton)) {

			reportStep("Successfully clicked on the store MainCTA GrabDeal  button", "PASS");
		}else {

			reportStep("Fail to  click on the store MainCTA GrabDeal button", "FAIL");
		}

		return new SignInPage(driver, testInfo);
	}

	//Method call
	public IntermediateRetailerPage clickOnStoreGrabDealMainCTA_ForSignedUser() {

		reportStep("About to click on the store page Main CTA GrabDeal button", "INFO");

		if(click(storeGrabDealMainCtaButton)) {

			reportStep("Successfully clicked on the store MainCTA GrabDeal  button", "PASS");
		}else {

			reportStep("Fail to  click on the store MainCTA GrabDeal button", "FAIL");
		}

		return new IntermediateRetailerPage(driver, testInfo);
	}

	//Store Validation :
	public StorePage validateStoreShortDescription(String storeName , String expected) {

		reportStep("About to validate the store "+ storeName+" 's short description ", "INFO");

		String actual = getText(storeAppShortDesc);

		validateTheActualValueWithExpectedValue(actual, expected);

		return this;

	}

	public StorePage validateStoreMainCTALabel(String storeName, String ctaLabel) {
			
			reportStep("About to validate the store page Main CTA label ", "PASS");
					
			String xpath = "//android.widget.TextView[@content-desc='btn_store_grabDeal']|//android.widget.TextView[@content-desc='btn_store_activate']";
			
			isElementLocatedByXpathPresent(xpath);
			
			MobileElement storePageCTALabel = driver.findElement(By.xpath(xpath));

			String actual = getText(storePageCTALabel);

			validateTheActualValueWithExpectedValue(actual, ctaLabel);
		
		return this;

	
	}
	
	public StorePage validateStoreMainCTAGrabDealLabel(String expected) {

		reportStep("About to validate the store page Main CTA label ", "PASS");

		String actual = getText(storeGrabDealMainCtaButton);

		validateTheActualValueWithExpectedValue(actual, expected);

		return this;
	}

	public StorePage clickOnShowCashbackRates() {

		reportStep("About to click on the show Cashback rates link ", "INFO");

		if(click(showCashbackRatesDropDownlink)) {

			reportStep("Successfully clicked on the show cashback rates link at the store Page", "PASS");

		}else {

			reportStep("Failed to click on the show cashback rates drop down link at the store Page ", "FAIL");
		}
		return this;
	}

	public StorePage clickOnHideCashbackRates() {

		reportStep("About to click on the Hide Cashback rates link ", "INFO");

		if(click(hideCashBackRatesDropDownlink)) {

			try {
				validateTheElementPresence(showCashbackRatesDropDownlink);
				reportStep("Successfully clicked on the Hide cashback rates link at the store Page", "PASS");
			}catch (Exception e) {
				reportStep("Fail -  clicking on the HideCashback link is not hiding the details ", "FAIL");
			}

		}else {

			reportStep("Failed to click on the Hide cashback rates drop down link at the store Page ", "FAIL");
		}
		return this;
	}

	public StorePage clickOnCloseButton() {

		reportStep("About to click on the close button at the Hide Cashback rates link ", "INFO");

		if(click(closeButton)) {

			try {
				validateTheElementPresence(showCashbackRatesDropDownlink);
				reportStep("Successfully clicked on the Hide cashback rates link at the store Page", "PASS");
			}catch (Exception e) {
				reportStep("Fail -  clicking on close button at the HideCashback link is not hiding the details ", "FAIL");
			}

		}else {

			reportStep("Failed to close button at the Hide cashback details button ", "FAIL");
		}
		return this;
	}

	public StorePage validateNormalPrimaryCashbackRateInPercentage(String storeName,String expected) {

		reportStep("About to validate the store "+storeName+" 's Normal Primary cashback rate in percentage ", "INFO");

		String actual = getText(normalPrimaryCashbackValue);

		if(actual.contains("%")) {

			actual = actual.replaceAll("%", "").trim();

			reportStep("Successfully validated the Percentage symbol in the cashback "
					+ "rate for the store : "+storeName , "PASS");
		}else {

			reportStep("Fail  to validate the Percentage symbol in the cashback "
					+ "rate for the store : "+storeName , "FAIL");

		}

		validateTheActualValueWithExpectedValue(actual, expected);

		return this;
	}

	public StorePage validateNormalSecondaryCashbackRateInPercentage(String storeName,String expected) {

		reportStep("About to validate the store "+storeName+" 's Normal Secondary cashback rate in percentage  ", "INFO");

		String actual = getText(normalSecondaryCashbackValue);

		if(actual.contains("%")) {

			actual = actual.replaceAll("%", "").trim();

			reportStep("Successfully validated the Percentage symbol in the cashback "
					+ "rate for the store : "+storeName , "PASS");
		}else {

			reportStep("Fail  to validate the Percentage symbol in the cashback "
					+ "rate for the store : "+storeName , "FAIL");

		}

		validateTheActualValueWithExpectedValue(actual, expected);

		return this;
	}

	public StorePage validateNormalPrimaryCashbackRateInCurrency(String storeName,String expected) {

		reportStep("About to validate the store "+storeName+" 's Normal Primary cashback rate in currency  ", "INFO");

		String actual = getText(normalPrimaryCashbackValue);
		String rupeesSymbol = getTestData(4, "RupeesSymbol");


		actual = Utilities.extractOnlyDigitFromString(actual);

		validateTheActualValueWithExpectedValue(actual, expected);

		return this;
	}

	public StorePage validateNormalSecondaryCashbackRateInCurrency(String storeName,String expected) {

		reportStep("About to validate the store "+storeName+" 's Normal Secondary cashback rate in currency ", "INFO");

		String rupeesSymbol = getTestData(4, "RupeesSymbol");
		String actual = getText(normalSecondaryCashbackValue);

		actual = Utilities.extractOnlyDigitFromString(actual);
		validateTheActualValueWithExpectedValue(actual, expected);

		return this;
	}

	public StorePage validateNormalPrimaryCashbackRate_Details(String storeName,String expected) {

		reportStep("About to validate the store "+storeName+" 's Normal Primary cashback rate details ", "INFO");

		String actual = getText(normalPrimaryCashbackRateDetails);

		validateTheActualValueWithExpectedValue(actual, expected);

		return this;
	}

	public StorePage validateNormalSecondaryCashbackRate_Details(String storeName,String expected) {

		reportStep("About to validate the store "+storeName+" 's Normal Secondary cashback rate details ", "INFO");

		String actual = getText(normalSecondaryCashbackRateDetails);

		validateTheActualValueWithExpectedValue(actual, expected);

		return this;
	}

	public StorePage clickOnAllUserRadioButton() {

		scrollFromUpToDowninApp();
		scrollFromUpToDowninApp();
		reportStep("About to click on the All User radio button ", "INFO");

		if(click(allUserRadioButton)) {

			reportStep("Successfully clicked on the All user radio button ", "PASS");

		}else {
			reportStep("Fail to click on the All user radio button  ", "FAIL");
		}


		return this;
	}

	public StorePage clickOnNewRadioUserButton() {

		scrollFromUpToDowninApp();
		scrollFromUpToDowninApp();
		reportStep("About to click on the New User radio button ", "INFO");

		if(click(newUserRadioButton)) {

			reportStep("Successfully clicked on the New user radio button ", "PASS");

		}else {
			reportStep("Fail to click on the New user radio button  ", "FAIL");
		}

		
		return this;
	}
	
	public StorePage clickOnSelectCategoryDropdown() {
		
		scrollFromUpToDowninApp();
		scrollFromUpToDowninApp();

		reportStep("About to click on the select category dropdown ", "INFO");

		if(click(selectCategoryDropDown)) {

			reportStep("Successfully clicked on select category dropdown  ", "PASS");

		}else {
			reportStep("Fail to click on select category dropdown   ", "FAIL");
		}

		
		return this;
	}

	public StorePage clickOnSeeDetailsAndValidateVoucherDescription(String voucherTitleText) {
		
		scrollFromDownToUpinApp_FourSecDuration();

		reportStep("About to click on the see details for the "+ voucherTitleText ,"INFO" );
		
		System.out.println("About to click on the see details for the "+ voucherTitleText);
		
		String voucherDescription = getTestData(4, "EleventhStoreVoucherTitle_One");
		String elementToBeClicked_xpath = "//android.widget.TextView[contains(@text,'"+voucherTitleText+"')]";
		String elementToBeExpected = "//android.widget.TextView[contains(@text,'Details For "+voucherDescription+"')]";

		if(clickMultipleXCoordinatesByXpath(elementToBeClicked_xpath, 60, elementToBeExpected)) {

			reportStep("Successfully clicked on the "+ voucherTitleText + " 's see details and validated the description ","PASS");
		}
		else {

			reportStep("Fail to  click on the "+ voucherTitleText + " 's see details to validate the description " ,"PASS");

		}

		return this;

	}

	public IntermediateRetailerPage clickOnVoucherButtonForSignedUser(int index,String storeName) {

		scrollFromDownToUpinApp();

		reportStep("About to click on the  "+ index+1 + "st/nd/th voucher button at the store page for the store "+ storeName,"INFO" );

		if(click(ListOfvoucherCTAButtons.get(index))){

			reportStep("Successfully clicked on the "+ index+1 + "st/nd/th Vocher button at the store page for the store "+ storeName, "PASS");

		}else {

			reportStep("Failed to click on the "+ index+1 + "st/nd/th voucher button at the store page for the store "+ storeName,"FAIL" );

		}

		return new IntermediateRetailerPage(driver, testInfo);
	}
	
	public SignInPage clickOnVoucherButtonForUnsignedUser(int index,String storeName) {

		scrollFromDownToUpinApp();

		reportStep("About to click on the  "+ index+1 + "st/nd/th voucher button at the store page for the store "+ storeName,"INFO" );

		if(click(ListOfvoucherCTAButtons.get(index))){

			reportStep("Successfully clicked on the "+ index+1 + "st/nd/th Vocher button at the store page for the store "+ storeName, "PASS");

		}else {

			reportStep("Failed to click on the "+ index+1 + "st/nd/th voucher button at the store page for the store "+ storeName,"FAIL" );

		}

		return new SignInPage(driver, testInfo);
	}

	public StorePage validateVoucherTitle(int index,String expected,String storeName) {

		index = index+1;

		reportStep("About to validate the "+index+" st/nd/th Voucher title for the store "+ storeName , "INFO");


		String elementToBeClicked_xpath = "(//android.widget.TextView[@content-desc='txt_store_voucherTitle'])["+index+"]";

		String actual = driver.findElement(By.xpath(elementToBeClicked_xpath)).getText().replace("See Details", "").trim();

		validateTheActualValueWithExpectedValue(actual, expected);

		return this;

	}
	
	public StorePage validateVoucherTitleContainsExpectedText(String expected) {
		
		reportStep("About to validate the voucher "+expected + "'s Title Presence " , "INFO");
		
		if(scrollTillRequiredElementIsVisible(expected)) {
			
			reportStep("Successsfully validted voucher "+ expected+ "  in the store page ", "PASS");
		}else {
			
			reportStep("Voucher "+expected+" is not present in the store page  ", "FAIL");
		}
	
		return this;

	}

	public StorePage validateVoucherExpiry(int index,String expected,String storeName) {

		reportStep("About to validate the "+index+1+" st/nd/th Voucher Expiry for the store "+ storeName , "INFO");

		String actual = getText(listOfvoucherExpiry.get(index));
		validateTheActualValueWithExpectedValue(actual, expected);

		return this;

	}

	public StorePage validateVoucherDescription(int index,String expected,String storeName) {

		reportStep("About to validate the "+index+1+" st/nd/th Voucher description for the store "+ storeName , "INFO");

		String actual = getText(listOfVoucherDescription.get(index));
		validateTheActualValueWithExpectedValue(actual, expected);

		return this;

	}

	public StorePage validateNumberOfIncreasedVoucherOfferType(int expected) {

		int actual = getListOfElementsSize(increasedofferType);

		validateTheActualValueWithExpectedValue(actual, expected);

		return this;
	}

	public StorePage validateNumberOfExclusiveVoucherOfferType(int expected) {

		int actual = getListOfElementsSize(exclusiveofferType);

		validateTheActualValueWithExpectedValue(actual, expected);

		return this;

	}

	public StorePage validateVoucherCountInStorePage(int expected) {

		scrollFromDownToUpinApp();

		int actual = getListOfElementsSize(ListOfvoucherCTAButtons);

		validateTheActualValueWithExpectedValue(actual, expected);

		return this;
	}

	public StorePage validateN18VoucherCountInStorePage(int expected) {

		scrollFromDownToUpinApp();

		int actual = getListOfElementsSize(ListOfN18voucherCTAButtons);

		validateTheActualValueWithExpectedValue(actual, expected);

		return this;
	}

	public StorePage validateThePrimaryCashbackExpiryTag(int index,String expected,String storeName) {

		reportStep("About to validate the Primary Cashback Expiry Tag for the Store " + storeName , "INFO");

		String actual = getText(primaryCalendarCashbckExpiryTag.get(index));

		validateTheActualValueContainsExpectedValue(actual, expected);

		return this;

	}

	public StorePage validateTheSecondaryCashbackExpiryTag(int index,String expected,String storeName) {

		reportStep("About to validate the Primary Cashback Expiry Tag for the Store " + storeName , "INFO");

		String actual =getText(secondaryCalendaryCashbackExpiryTag.get(index));

		validateTheActualValueContainsExpectedValue(actual, expected);

		return this;


	}

	public StorePage clickOnAboutRetailer() {

		reportStep("About to click on the About Retailer link ", "INFO");

		if(click(aboutRetailer)) {

			reportStep("Successfully clicked on the About retiler button", "PASS");
		}else {

			reportStep("Failed to click on the About  retailer link at the store page", "FAIL");
		}

		return this;

	}

	public void clickOnYouMayAlsoLike() {

		reportStep("About to click on the About Retailer link ", "INFO");

		if(click(aboutRetailer)) {

			reportStep("Successfully clicked on the  About retailer  link", "PASS");
		}else {

			reportStep("Failed to click on the About Retailer link  link at the store page", "FAIL");
		}
	}

	public StorePage clickOnTermsAndCondition() {

		reportStep("About to click on the Terms and condition link ", "INFO");

		if(click(termsAndCondition)) {

			reportStep("Successfully clicked on terms and condition link", "PASS");
		}else {

			reportStep("Failed to click on the  Terms and condition  link at the store page", "FAIL");
		}

		return this;

	}

	public StorePage validateAppFullDescription(String expected,String storeName) {

		reportStep("About to validate the About retailer details or App full description for the store " + storeName, "INFO");
		String actual = getText(appFullDescription);
		validateTheActualValueContainsExpectedValue(actual, expected);

		return this;
	}

	public StorePage validteTermsAndConditionValue(String expected,String storeName) {

		reportStep("About to validate the About retailer details or App full description for the store " + storeName, "INFO");
		String actual = getText(appTermsAndConditionDetails);
		validateTheActualValueContainsExpectedValue(actual, expected);

		return this;
	}

	public String getStoreNumericValue(String storeName) {

		storeName= storeName.substring(storeName.indexOf(":")+1);
		System.out.println(storeName);

		return storeName;
	}

	public StorePage validateTheNumberOfSecondaryCashbackDetails(int expected) {

		int actual = getListOfElementsSize(listOfNormalSecondaryCashbackRateDetails);

		validateTheActualValueWithExpectedValue(actual, expected);

		return this;

	}

	public StorePage validateTheNumberOfSecondaryCashbackAmount(int expected) {

		int actual = getListOfElementsSize(listOfNormalSecondaryCashbackRateAmount);

		validateTheActualValueWithExpectedValue(actual, expected);

		return this;

	}

	public StorePage validateSecondSecodaryCashbackDetailsTitle(String expected) {

		reportStep("About to validate the Second secondary cashback Title ", "INFO");

		String actual = listOfNormalSecondaryCashbackRateDetails.get(1).getText().trim();

		validateTheActualValueWithExpectedValue(actual, expected);

		return this;

	}

	public StorePage validateSecondSecodaryCashbackRate(String expected) {

		reportStep("About to validate the Second secondary cashback rate ", "INFO");
		String actual = listOfNormalSecondaryCashbackRateAmount.get(1).getText().trim();

		actual = Utilities.extractOnlyDigitFromString(actual);
		validateTheActualValueWithExpectedValue(actual, expected);

		return this;

	}

	public StorePage validteNovoucherSection(String storeName) {

		reportStep("About to validate the No voucher section ", "INFO");
		
		String xpath = "//android.widget.TextView[@text='Sorry, there are no offers available for "+storeName+" currently. Please check back after some time.']";

		try {
			WebDriverWait wait = new WebDriverWait(driver, 15);
			wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(xpath)));
			
		} catch (Exception e) {
			
			reportStep("Voucher count should be zero but some vouchers are available , Please look into this . More Info  : //android.widget.TextView[@text='Sorry, there are no offers available for "+storeName+" currently. Please check back after some time.']", "INFO");

		}

		MobileElement element = driver.findElementByXPath(xpath);
		
		validateTheElementPresence(element);

		return this;

	}

	public StorePage scrollFromDownToUpInStorePage() {

		reportStep("About scroll from down to up in store page :) ", "PASS");
		scrollFromDownToUpinApp_FourSecDuration();
		return this;

	}

	public StorePage scrollFromDownToUpInStorePage_FastScroll() {

		reportStep("About scroll from down to up in store page :) ", "PASS");
		scrollFromDownToUpinApp();
		return this;

	}

	public StorePage validteSelectCategoryDropDown() {
		
	
		reportStep("About to validate the select category dropdown values ", "INFO");
		validateTheElementPresence(SelectCategoryDropdownSelectCategory);
		validateTheElementPresence(SelectCategoryDropdownAll);
		validateTheElementPresence(SelectCategoryDropdownPPSATMCASHBACKCAT_7);
		validateTheElementPresence(SelectCategoryDropdownPPSATMN18CAT_7);
		validateTheElementPresence(SelectCategoryDropdownPPSATMREWARDCAT_7);
		
		if(click(SelectCategoryDropdownAll)) {
			
			reportStep("Successfully clicked on the All  the select category drop down ", "PASS");
		}else {
			
			reportStep("Failed to clicked on the All from the select category drop down ", "FAIL");
		}
		
		return this;
	}

	public StorePage validateVoucherExpiry_Enhanced(int index,String expected,String storeName) {

		reportStep("About to validate the "+index+1+" st/nd/th Voucher Expiry for the store "+ storeName , "INFO");
		
		String actual = getText(listOfvoucherExpiry.get(index));
		validateTheActualValueContainsExpectedValue(actual, expected);
		
		return this;

	}

	public StoreCategoryPage clickBackButton() {

		reportStep("About to click on back button in store category page", "INFO");

//		if(click(backButtonClick)) {
//
//			reportStep("Successfully clicked on the back button in store cat page", "PASS");
//		}else {
//
//			reportStep("Failed to  click on the back button in store cat page", "FAIL");
//		}
		
		driver.navigate().back();

		return new StoreCategoryPage(driver, testInfo);
		
	}

	public StorePage validateStoreName(String storeName , String expected) {

		reportStep("About to validate the store name "+ storeName+" ", "INFO");
		
		String xpath = "//android.widget.TextView[contains(@text,'"+storeName+"')]";
		MobileElement storePageStoreName = driver.findElement(By.xpath(xpath));

		String actual = getText(storePageStoreName);

		validateTheActualValueWithExpectedValue(actual, expected);

		return this;

	}

	public StorePage validateImage(String storeName) {

		reportStep("About to validate the store name "+ storeName+" ", "INFO");
		
		String xpath = 	"//android.widget.ImageView[@content-desc='img_store_logo']";
				
		MobileElement storePageImage = driver.findElement(By.xpath(xpath));

		validateTheElementPresence(storePageImage);

		return this;

	}

	public SignedInProfilePage clickOnProfileIconForSignedUser() {

		reportStep("About to click on the Profile icon for  Signed user ", "INFO");

		if(click(profileIcon)) {

			reportStep("Successfully clicked on the Profile icon at the Home page for  Signed user ", "PASS");
		}else {

			reportStep("Failed to  click on the Profile icon at the Home page for  Signed user", "FAIL");
		}

		return new SignedInProfilePage(driver, testInfo);
	}

	public SearchPage clickOnSearchIcon() {
		
		reportStep("About to click on the Search Icon from the Home page ", "INFO");
		
		if(click(searchIcon)){
			
			reportStep("Sucessfully clicked on the Search icon ", "PASS");
			
		}else {
			
			reportStep("Failed to click on the Search icon", "FAIL");
		}
		
		return new SearchPage(driver, testInfo);
	}

	public HomePage clickBackButtonSearchPage() {

		reportStep("About to click on back button in search page", "INFO");

//		if(click(backButtonClick)) {
//
//			reportStep("Successfully clicked on the back button in store cat page", "PASS");
//		}else {
//
//			reportStep("Failed to  click on the back button in store cat page", "FAIL");
//		}
		
		driver.navigate().back();

		return new HomePage(driver, testInfo);
		
	}

}
