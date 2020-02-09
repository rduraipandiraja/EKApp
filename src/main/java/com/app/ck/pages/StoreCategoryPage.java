package com.app.ck.pages;

import static io.appium.java_client.touch.offset.PointOption.point;

import java.time.Duration;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.app.ck.base.WrapperMethods;
import com.aventstack.extentreports.ExtentTest;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.MultiTouchAction;
import io.appium.java_client.TouchAction;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.touch.WaitOptions;
	
	//Contructor call
	public class StoreCategoryPage extends WrapperMethods {

	//Variable initialization
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='Highest Cashback Stores']") 
	MobileElement sliderHighestCashbackRates; 

	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='ACTIVATE CASHBACK']") 
	MobileElement storeCatStoreBtn;
	
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@content-desc='txt_storeCategory_title']") 
	MobileElement storeCategoryTitle;
	
	String storeCategoryTitleXpath = "//android.widget.TextView[@content-desc='txt_storeCategory_title']";

	@FindBy(how = How.XPATH, using = "//android.widget.TextView[contains(@text,'Popular')]") 
	MobileElement popularTab;

	@FindBy(how = How.XPATH, using = "//android.widget.TextView[contains(@text,'Percent')]") 
	MobileElement percentTab;

	@FindBy(how = How.XPATH, using = "//android.widget.TextView[contains(@text,'Amount')]") 
	MobileElement AmountTab;

	@FindBy(how = How.XPATH, using = "//android.widget.TextView[contains(@text,'A-Z')]") 
	MobileElement atozTab;
	
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[contains(@text,'Today')]|//android.widget.TextView[contains(@text,'Exclusive Offers')]") 
	List<MobileElement> appExclusivesText;

	@FindBy(how = How.XPATH, using = "//android.widget.TextView[contains(@text,'Highest Profit Stores')]") 
	List<MobileElement> highestCashbackStoresText;

	@FindBy(how = How.XPATH, using = "//android.widget.TextView[contains(@text,'PPS ATM APP SPECIFIC SUBCATEGORY')]") 
	List<MobileElement> ppsAtmAppSpecificSubcategory;
	
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[contains(@text,'Highest Cashback Stores')]") 
	List<MobileElement> retailerCategoryText;
	
	@FindBy(how = How.XPATH, using = "(//android.widget.ImageView)[1]") 
	MobileElement backButtonClick;

	@FindBy(how = How.XPATH, using = "//android.widget.TextView[contains(@text,'PPS ATM APP SPECIFIC SUBCATEGORY ONE Offer')]") 
	MobileElement automationSpecificBannerTile;

	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='PPS ATM APP SPECIFIC SUBCATEGORY ONE']") 
	MobileElement automationSpecificHeaderTile;
	
	@FindBy(how = How.XPATH, using = "(//android.widget.TextView[@text='PPS ATM APP SPECIFIC SUBCATEGORY ONE'])[2]") 
	MobileElement automationSpecificDropdown;
	
	@FindBy(how = How.XPATH, using = "//android.widget.CheckedTextView[@text='PPS ATM APP SPECIFIC SUBCATEGORY ONE']") 
	MobileElement automationSpecificDropdownClick;

	@FindBy(how = How.XPATH, using = "(//android.widget.TextView[@content-desc='txt_storeCard_shortDescription'])[1]") 
	MobileElement firstCardShortDescription;
	
	String firstStoreCardDescription = "(//android.widget.TextView[@content-desc='txt_storeCard_shortDescription'])[1]";

	String lastStoreCardDescription = "(//android.widget.TextView[@content-desc='txt_storeCard_shortDescription'])[6]";

	String AmountTabXpath = "//android.widget.TextView[contains(@text,'Amount')]";

	String popularTabXpath = "//android.widget.TextView[contains(@text,'Popular')]";

	String percentTabXpath = "//android.widget.TextView[contains(@text,'Percent')]";

	String atozTabXpath = "//android.widget.TextView[contains(@text,'A-Z')]";

	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@content-desc='txt_storeCard_shortDescription']") 
	List<MobileElement> shortDescription;
	
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@content-desc='txt_storeCard_shortDescription']") 
	MobileElement shortDesc;

	@FindBy(how = How.XPATH, using = "//android.view.ViewGroup[@content-desc='txt_store_addto_share_add_top']")
	MobileElement addToShareButton;

	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@content-desc='btn_store_addto_remove_add']")
	MobileElement removeButton;

	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@content-desc='txt_store_shortDescription']") 
	MobileElement storeAppShortDesc;

	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='COPY LINK']") 
	MobileElement copyLink;

	@FindBy(how = How.XPATH, using = "(//android.widget.TextView[@text='SHARE NOW']|//android.view.ViewGroup[@content-desc='txt_product_whatsapp_share_top'])[1]")
	MobileElement shareNow;

	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='CLEAR ALL']") 
	MobileElement clearAllButton;

	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='CLOSE']") 
	MobileElement closeButton;

	@FindBy(how = How.XPATH, using = "//android.view.ViewGroup[@content-desc='txt_sharecount_open_modal']")
	MobileElement shareIconWithCount;

	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='WhatsApp']") 
	MobileElement whatsApp;

	@FindBy(how = How.XPATH, using = "//android.view.ViewGroup[@content-desc='txt_sharecount_top_view']") 
	MobileElement sharedCountTopView;

	@FindBy(how = How.XPATH, using = "//*[contains(@text,'OK')]") 
	MobileElement okButton;

	//Method creation
	public StoreCategoryPage(AppiumDriver<MobileElement> driver, ExtentTest testInfo) {

		WebDriverWait wait = new WebDriverWait(driver, 30);

		this.driver = driver;
		this.testInfo = testInfo;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);

		reportStep("Waiting for the StoreCategory page", "INFO");
		try {
			
			scrollTillRequiredElementIsVisibleFromUpToDown(storeCategoryTitleXpath);
			
			wait.until(ExpectedConditions.visibilityOf(storeCategoryTitle));
			reportStep("Successfully landed on the StoreCategory page", "PASS");

		}catch(Exception e) {

			reportStep(e.getMessage(), "INFO");
			reportStep("Not able to land on the StoreCategory page", "FAIL");
		}

	}

	public SignInPage clickOnStoreCatStoreButton() {

		reportStep("About to click on the store button at the store category page", "INFO");

		if(click(storeCatStoreBtn)) {

			reportStep("Sucessfully clicked on the  Store category - Store button ", "PASS");
		}else {

			reportStep("Failed to click on the  Store category - Store button ", "FAIL");
		}

		return new SignInPage(driver, testInfo);
	}

	public StoreCategoryPage verifyAppExclusivesPageNavigation() {

		reportStep("About to verify the text app exlusives in app exclusives store category page", "INFO");
			
		int appExclusivesCategoryNameCount = getListOfElementsSize(appExclusivesText);
		
		String appExclusivesCatNameCount = Integer.toString(appExclusivesCategoryNameCount);
		
		validateTheActualValueWithExpectedValue(appExclusivesCatNameCount, getTestData(9, "CountThree"));
		
		return this;
			
	}

	public StoreCategoryPage verifyHighestProfitStorePageNavigation() {

		reportStep("About to verify the text highest cashback stores in highest cashback stores in store category page", "INFO");
		
		int highestCashbackStoresCategoryNameCount = getListOfElementsSize(highestCashbackStoresText);
		
		String highestCashbackStoresCatNameCount = Integer.toString(highestCashbackStoresCategoryNameCount);
		
		validateTheActualValueWithExpectedValue(highestCashbackStoresCatNameCount, getTestData(9, "CountThree"));
		
		return this;
			
	}

	public StoreCategoryPage verifyAutomationSpecificSubCategoryNavigation() {

		reportStep("About to verify the text PPS ATM APP SPECIFIC SUBCATEGORY in store category page", "INFO");
		
		int ppsAtmAppSpecificSubcategoryCount = getListOfElementsSize(ppsAtmAppSpecificSubcategory);
		
		String ppsAtmAppSpecificSubcatCount = Integer.toString(ppsAtmAppSpecificSubcategoryCount);
		
		validateTheActualValueWithExpectedValue(ppsAtmAppSpecificSubcatCount, getTestData(9, "CountThree"));
		
		return this;
			
	}
	
	public HomePage clickBackButton() {

		reportStep("About to click on back button in store category page", "INFO");

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
	
	public StoreCategoryPage clickBackButton_StoreCategoryPage() {

		reportStep("About to click on back button in store category page", "INFO");
		
		driver.navigate().back();

		return this;
		
	}
	
	public RetailerCategoryPage clickBackButtonForRetailerCategoryPageNavigation() {

		reportStep("About to click on back button in store category page", "INFO");

//		if(click(backButtonClick)) {
//
//			reportStep("Successfully clicked on the back button in store cat page", "PASS");
//		}else {
//
//			reportStep("Failed to  click on the back button in store cat page", "FAIL");
//		}
		
		driver.navigate().back();

		return new RetailerCategoryPage(driver, testInfo);
		
	}

	public StoreCategoryPage verifyStoreCategoryPageBannerTitle() {

		reportStep("About to verify banner title", "INFO");
		
		validateTheElementPresence(automationSpecificBannerTile);

		reportStep("Successfully verified banner title", "INFO");
		
		return this;
			
	}
	
	public StoreCategoryPage verifyStoreCategoryPageHeaderTitle() {

		reportStep("About to verify header title", "INFO");
		
		validateTheElementPresence(automationSpecificHeaderTile);

		reportStep("Successfully verified header title", "INFO");
		
		return this;
			
	}
	
	public StoreCategoryPage verifyStoreCategoryPageDropdown() {

		reportStep("About to click dropdown  in store category page", "INFO");
		
		if(click(automationSpecificDropdown)){
			
			reportStep("Successfully clicked dropdown  in store category page", "PASS");
			
		}else {
			
			reportStep("Failed to click dropdown  in store category page", "FAIL");
		}
		
		if(click(automationSpecificDropdownClick)){
			
			reportStep("Successfully selected value from the dropdown", "PASS");
			
		}else {
			
			reportStep("Failed to select value from dropdown", "FAIL");
		}
		
		return this;
			
	}

	public StoreCategoryPage clickPopularTab() {

		reportStep("About to click popularity tab in store category page", "INFO");
		
		scrollTillRequiredElementIsVisibleFromUpToDown(popularTabXpath);
		
		if(click(popularTab)){
			
			reportStep("Successfully clicked popularity tab", "PASS");
			
		}else {
			
			reportStep("Failed to click popularity tab", "FAIL");
		}
		
		return this;
			
	}
	
	public StoreCategoryPage clickPercentTab() {

		reportStep("About to click percent tab in store category page", "INFO");
		
		scrollTillRequiredElementIsVisibleFromUpToDown(percentTabXpath);
		
		if(click(percentTab)){
			
			reportStep("Successfully clicked percent tab", "PASS");
			
		}else {
			
			reportStep("Failed to click percent tab", "FAIL");
		}
		
		return this;
			
	}
	
	public StoreCategoryPage clickAmountTab() {

		reportStep("About to click amount tab in store category page", "INFO");
		
		scrollTillRequiredElementIsVisibleFromUpToDown(AmountTabXpath);
		
		if(click(AmountTab)){
			
			reportStep("Successfully clicked amount tab", "PASS");
			
		}else {
			
			reportStep("Failed to click amount tab", "FAIL");
		}
		
		return this;
			
	}
	
	public StoreCategoryPage clickAzTab() {

		reportStep("About to click AtoZ tab in store category page", "INFO");
		
		scrollTillRequiredElementIsVisibleFromUpToDown(atozTabXpath);
		
		if(click(atozTab)){
			
			reportStep("Successfully clicked AtoZ tab", "PASS");
			
		}else {
			
			reportStep("Failed to click AtoZ tab", "FAIL");
		}
		
		return this;
			
	}

	public StoreCategoryPage verifyStoreCategoryPageObjectPresence() {

		reportStep("About to validate the object presence in store category page", "INFO");
		
		scrollFromUpToDowninApp();
		scrollFromUpToDowninApp();
		scrollFromUpToDowninApp();
		
		validateTheElementPresence(popularTab);
		validateTheElementPresence(atozTab);
		validateTheElementPresence(AmountTab);
		validateTheElementPresence(percentTab);
		validateTheElementPresence(shortDesc);

		reportStep("Successfully validated the object presence in store category page", "INFO");
		
		return this;
			
	}
	
	public StoreCategoryPage verifyFirstCardDescription(String expected) {

		reportStep("About to verify first card short description in store category page", "INFO");
		
		scrollFromDownToUpTillRequiredElementIsVisible(firstStoreCardDescription);
		
		String shortDescription = getText(firstCardShortDescription);
		
		validateTheActualValueWithExpectedValue(shortDescription, expected);
		
		return this;
			
	}
	
	public StoreCategoryPage verifyLastCardDescription(String expected) {

		reportStep("About to verify last card short description in store category page", "INFO");

		for(int value = 0; value<=5; value++) {

			scrollFromDownToUpinApp();
		}
		
		String one = getTestData(9, "CountOne");
		
		int oneValue = Integer.parseInt(one);
		
		int countOfVisibleDescription = shortDescription.size();
		
		int subtractCountOfVisibleDescription = countOfVisibleDescription - oneValue;
		
		MobileElement lastCardShortdescription = shortDescription.get(subtractCountOfVisibleDescription);
		
		String shortDescription = getText(lastCardShortdescription);
		
		validateTheActualValueWithExpectedValue(shortDescription, expected);
		
		return this;
			
	}

	public SignInPage clickStoreCardCTAButton(String shortDesc, String ctaLabel) {

		reportStep("About to click store card CTA button in store category page", "INFO");
		
		String storeCardCTAButtonClick = "//android.widget.TextView[@text='"+shortDesc+"']/parent::android.view.ViewGroup/following-sibling::android.view.ViewGroup/android.widget.TextView[contains(@text,'"+ctaLabel+"')]|//android.widget.TextView[@text='"+shortDesc+"']/parent::android.view.View/following-sibling::android.view.View/android.widget.TextView[contains(@text,'"+ctaLabel+"')]";		
		scrollTillRequiredElementIsVisible(storeCardCTAButtonClick);
		
		if(clickByXpath(storeCardCTAButtonClick)){
			
			reportStep("Successfully clicked "+ctaLabel+" in the store card", "PASS");
			
		}else {
			
			reportStep("Failed to click "+ctaLabel+" in the store card", "FAIL");
		}
		
		return new SignInPage(driver, testInfo);
			
	}

	public int verifyExtractCountFromBannerTitle() {

		reportStep("About to extract banner title count", "INFO");
		
		validateTheElementPresence(automationSpecificBannerTile);
		String bannerTitle = getText(automationSpecificBannerTile);
		
		String bannerTitleCount = StringUtils.substringBetween(bannerTitle, getTestData(7, "startBannertitle"), getTestData(7, "endBannertitle"));
		int bannerCount = Integer.parseInt(bannerTitleCount);

		reportStep("Successfully extracted banner title count", "INFO");
		
		return bannerCount;
			
	}

	public StoreCategoryPage verifyStoreCardViewAllOffersAbsence(String shortDesc, String viewAllOffers) {

		reportStep("About to click store card view all offers link in store category page", "INFO");
		

		String shortDescription = "//android.widget.TextView[@text='"+shortDesc.trim()+"']";
		scrollTillRequiredElementIsVisible(shortDescription);
		
		String storeCardViewAllOffersClick = "//android.widget.TextView[@text='"+shortDesc+"']/parent::android.view.ViewGroup/following-sibling::android.view.ViewGroup/android.widget.TextView[contains(@text,'"+viewAllOffers+"')]";				
		validateTheElementAbsence(storeCardViewAllOffersClick);
		
		return this;
			
	}

	public StoreCategoryPage validateStoreShortDescription(String storeName , String expected) {

		reportStep("About to validate the store "+ storeName+" 's short description in StoreDetailPage", "INFO");

		String actual = getText(storeAppShortDesc);

		validateTheActualValueWithExpectedValue(actual, expected);

		return this;

	}

	public StoreDetailPage clickAndVerifyStoreCardShortDescription(String shortDesc) {

		reportStep("About to click store card short description in store category page", "INFO");
		
		String storeCategoryTitle = "//android.widget.TextView[@content-desc='txt_storeCategory_title']";
		
		scrollTillRequiredElementIsVisibleFromUpToDown(storeCategoryTitle);
		
		String shortDescription = "//android.widget.TextView[@text='"+shortDesc.trim()+"']";
		
		scrollTillRequiredElementIsVisibleFromDownToUp(shortDescription);
		
		if(clickByXpath(shortDescription)){
			
			reportStep("Successfully clicked short description "+shortDesc+"", "PASS");
			
		}else {
			
			reportStep("Failed to click short description "+shortDesc+"", "FAIL");
		}
		
		return new StoreDetailPage(driver, testInfo);
			
	}
	
	public StoreDetailPage clickStoreCardImage(String shortDesc) {

		reportStep("About to click store card image in store category page", "INFO");
		
		String storeCategoryTitle = "//android.widget.TextView[@content-desc='txt_storeCategory_title']";
		
		scrollTillRequiredElementIsVisibleFromUpToDown(storeCategoryTitle);
		
		String storeCardImage = "//android.widget.TextView[@text='"+shortDesc+"']/parent::android.view.ViewGroup/parent::android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.widget.ImageView[@content-desc='img_storeCard_imageValue']";		
		
		scrollTillRequiredElementIsVisibleFromDownToUp(storeCardImage);
		
		if(clickByXpath(storeCardImage)){
			
			reportStep("Successfully clicked image using "+shortDesc+"", "PASS");
			
		}else {
			
			reportStep("Failed to click image using "+shortDesc+"", "FAIL");
		}
		
		return new StoreDetailPage(driver, testInfo);
			
	}

	public StoreCategoryPage clickOnAddToShareButton(String shortDesc) {

		reportStep("About to click on the add to share button", "INFO");
		
		String popularTab = "//android.widget.TextView[contains(@text,'Popular')]";
		
		scrollTillRequiredElementIsVisibleFromUpToDown(popularTab);

		String addToShareButtonXpath = "//android.widget.TextView[@text='"+shortDesc+"']/parent::android.view.ViewGroup/parent::android.view.ViewGroup/android.view.ViewGroup/android.widget.TextView[@text='ADD TO SHARE']";

		scrollTillRequiredElementIsVisibleFromDownToUp(addToShareButtonXpath);
		
		reportStep("Verified the presence of xpath: "+addToShareButtonXpath, "INFO");

		if(clickByXpath(addToShareButtonXpath)) {

			reportStep("Successfully clicked on add to share button", "PASS");
		}else {

			reportStep("Failed to click on add to share button", "FAIL");
		}
		
		validateTheElementPresence(shareIconWithCount);

		return this;

	}

	public StoreCategoryPage clickOnRemoveButton(String shortDesc) {

		reportStep("About to click on the remove button", "INFO");
		
		String popularTab = "//android.widget.TextView[contains(@text,'Popular')]";
		
		scrollTillRequiredElementIsVisibleFromUpToDown(popularTab);

		String removeButtonXpath = "//android.widget.TextView[@text='"+shortDesc+"']/parent::android.view.ViewGroup/parent::android.view.ViewGroup/android.view.ViewGroup/android.widget.TextView[@text='REMOVE']";
		
		scrollTillRequiredElementIsVisibleFromDownToUp(removeButtonXpath);
		
		MobileElement removeButton = driver.findElement(By.xpath(removeButtonXpath));

		if(click(removeButton)) {

			reportStep("Successfully clicked on remove button", "PASS");
		}else {

			reportStep("Failed to click on remove button", "FAIL");
		}
		
		//validateTheElementAbsence(shareIconWithCount);

		return this;

	}

	public StoreCategoryPage validateRemoveButtonPresence(String shortDesc) {

		reportStep("About to validate on the remove button", "INFO");

		String storeCategoryTitle = "//android.widget.TextView[@content-desc='txt_storeCategory_title']";
		
		scrollTillRequiredElementIsVisibleFromUpToDown(storeCategoryTitle);

		String removeButtonXpath = "//android.widget.TextView[@text='"+shortDesc+"']/parent::android.view.ViewGroup/parent::android.view.ViewGroup/android.view.ViewGroup/android.widget.TextView[@text='REMOVE']";
		
		scrollTillRequiredElementIsVisibleFromDownToUp(removeButtonXpath);

		MobileElement removeButton = driver.findElement(By.xpath(removeButtonXpath));

		validateTheElementAbsence(removeButton);

		return this;

	}
	
	public StoreCategoryPage clickOnShareNowButton(String shortDesc) {

		reportStep("About to click on the share now button", "INFO");
		
		String popularTab = "//android.widget.TextView[contains(@text,'Popular')]";
		
		scrollTillRequiredElementIsVisibleFromUpToDown(popularTab);

		String shareNowButtonXpath = "//android.widget.TextView[@text='"+shortDesc+"']/parent::android.view.ViewGroup/parent::android.view.ViewGroup/android.view.ViewGroup/android.widget.TextView[@text='SHARE NOW']";
		
		scrollTillRequiredElementIsVisibleFromDownToUp(shareNowButtonXpath);

		if(clickByXpath(shareNowButtonXpath)) {

			reportStep("Successfully clicked on share now button", "PASS");
		}else {

			reportStep("Failed to click on share now button", "FAIL");
		}

		return this;

	}
	
	public StoreCategoryPage clickOnShareIcon(String shortDesc) {

		reportStep("About to click on the add to share icon button", "INFO");
		
		String storeCategoryTitle = "//android.widget.TextView[@content-desc='txt_storeCategory_title']";
		
		scrollTillRequiredElementIsVisibleFromUpToDown(storeCategoryTitle);

		String shareIconXpath = "//android.widget.TextView[@text='"+shortDesc+"']/parent::android.view.ViewGroup/parent::android.view.ViewGroup/android.view.ViewGroup/android.widget.ImageView[@content-desc='img_storeCard_others_share']";
		
		scrollTillRequiredElementIsVisibleFromDownToUp(shareIconXpath);

		if(clickByXpath(storeCategoryTitle)) {

			reportStep("Successfully clicked on share icon button", "PASS");
		}else {

			reportStep("Failed to click on share icon button", "FAIL");
		}

		//validateTheElementPresence(whatsApp);

		return this;

	}
		
	public StoreCategoryPage clickOnWhatsApp() {
		
		try {

			clickAfterWait(whatsApp);
			reportStep("Successfully clicked on WhatsAppIcon button", "PASS");

		} catch (Exception e) {
			reportStep("Failed to click on WhatsAppIcon button", "FAIL");
		}

		return this;

	}

	public StoreCategoryPage clickOnShare(int shareCount, int popupShareCount) {

		reportStep("About to click on the share button", "INFO");
		
		String shareButtonXpath = "//android.view.ViewGroup[@content-desc='txt_sharecount_open_modal']/android.widget.TextView["+shareCount+"]";

		if(clickByXpath(shareButtonXpath)) {

			reportStep("Successfully clicked on share button", "PASS");
		}else {

			reportStep("Failed to click on share button", "FAIL");
		}
		
		String shareButtonPopupXpath = "//android.view.ViewGroup[@content-desc='txt_shareCounts_shareAllIconModal_share_top']/android.widget.TextView["+popupShareCount+"]";
		
		if(isElementLocatedByXpathPresent(shareButtonPopupXpath)){
			reportStep("Verified the presence of Popup share button", "INFO");

		}else {
			reportStep("Not able to verify the presence Popup share button", "FAIL");

		}
		
		MobileElement shareButtonPopup = driver.findElement(By.xpath(shareButtonPopupXpath));
		
		validateTheElementPresence(shareButtonPopup);

		return this;

	}

	public StoreCategoryPage clickOnShareInPopup(int popupShareCount) {

		reportStep("About to click on the popup share button", "INFO");
		
		String shareButtonPopupXpath = "//android.view.ViewGroup[@content-desc='txt_shareCounts_shareAllIconModal_share_top']/android.widget.TextView["+popupShareCount+"]";
		
		if(clickByXpath(shareButtonPopupXpath)) {

			reportStep("Successfully clicked on popup share button", "PASS");
		}else {

			reportStep("Failed to click on popup share button", "FAIL");
		}
		
		validateTheElementPresence(copyLink);

		return this;

	}

	public StoreCategoryPage clickOnCopyLink() {

		reportStep("About to click on the CopyLink", "INFO");

		if(click(copyLink)) {

			reportStep("Successfully clicked on CopyLink", "PASS");
		}else {

			reportStep("Failed to click on CopyLink", "FAIL");
		}
		
		validateTheElementPresence(copyLink);

		return this;

	}

	public StoreCategoryPage validateStoreImageInPopup(String shortDesc) {

		reportStep("About to validate the store image", "INFO");
		
		String storeImageXpath = "//android.widget.TextView['"+shortDesc+"']/parent::android.view.ViewGroup/android.view.ViewGroup/android.widget.ImageView[@content-desc='img_product_image']";
		
		if(isElementLocatedByXpathPresent(storeImageXpath)){
			reportStep("Verified the presence of storeImage", "INFO");

		}else {
			reportStep("Not able to verify the presence of storeImage", "FAIL");

		}

		return this;

	}

	public StoreCategoryPage validateStoreNameInPopup(String storeName) {

		reportStep("About to validate the store name", "INFO");
		
		String storeNameXpath = "//android.widget.TextView['"+storeName+"']";
		
		if(isElementLocatedByXpathPresent(storeNameXpath)){
			reportStep("Verified the presence of storeName", "INFO");

		}else {
			reportStep("Not able to verify the presence of storeName", "FAIL");

		}

		return this;

	}

	public StoreCategoryPage validateShortDescriptionInPopup(String shortDesc) {

		reportStep("About to validate the short_Desc", "INFO");
		
		String shortDescXpath = "//android.widget.TextView['"+shortDesc+"']";
		
		if(isElementLocatedByXpathPresent(shortDescXpath)){
			reportStep("Verified the presence of short_Desc", "INFO");

		}else {
			reportStep("Not able to verify the presence of short_Desc", "FAIL");

		}

		return this;

	}

	public StoreCategoryPage clickOnShareNowInPopup() {

		reportStep("About to click on the share now button", "INFO");

		if(clickAfterWait(shareNow)) {

			reportStep("Successfully clicked on share now button", "PASS");
		}else {

			reportStep("Failed to click on share now button", "FAIL");
		}

		return this;

	}

	public StoreCategoryPage clickOnclearAllButtonInPopup() {

		reportStep("About to click on the clearAllButton", "INFO");

		if(click(clearAllButton)) {

			reportStep("Successfully clicked on clearAllButton", "PASS");
		}else {

			reportStep("Failed to click on clearAllButton", "FAIL");
		}
		
		clickOnOkButtonClearAllPopup();
		
		//validateTheElementAbsence(shareIconWithCount);

		return this;

	}
	
	public StoreCategoryPage clickOnOkButtonClearAllPopup() {

		reportStep("About to click on the ok button in clearAll popup", "INFO");

		if(click(okButton)) {

			reportStep("Successfully clicked on ok button in clearAll popup", "PASS");
		}else {

			reportStep("Failed to click on ok button in clearAll popup", "FAIL");
		}

		return this;

	}

	public StoreCategoryPage clickOncloseButtonInPopup() {

		reportStep("About to click on the closeButton", "INFO");

		if(click(closeButton)) {

			reportStep("Successfully clicked on closeButton", "PASS");
		}else {

			reportStep("Failed to click on closeButton", "FAIL");
		}

		return this;

	}

	public StoreCategoryPage clickOnShareIconWithCount(int shareCount) {

		reportStep("About to click on the share button", "INFO");
		
		String shareButtonXpath = "//android.view.ViewGroup[@content-desc='txt_sharecount_open_modal']/android.view.ViewGroup/android.widget.TextView[@text='"+shareCount+"']";
		
		if(isElementLocatedByXpathPresent(shareButtonXpath)){
			reportStep("Verified the presence of share button", "INFO");

		}else {
			reportStep("Not able to verify the presence share button", "FAIL");

		}
		
		MobileElement shareButton = driver.findElement(By.xpath(shareButtonXpath));

		if(click(shareButton)) {

			reportStep("Successfully clicked on share button", "PASS");
		}else {

			reportStep("Failed to click on share button", "FAIL");
		}
		
		String testimonialXpath = "//android.view.ViewGroup[@content-desc='txt_sharecount_whatsapp_share_top']";
		
		MobileElement testimonial = driver.findElement(By.xpath(testimonialXpath));
		
		validateTheElementPresence(testimonial);

		return this;

	}
	
	public StoreCategoryPage validateShareIconWithCountPresence(int shareCount) {

		reportStep("About to validate ShareIconWithCountPresence", "INFO");
		
		String shareButtonXpath = "//android.view.ViewGroup[@content-desc='txt_sharecount_open_modal']/android.widget.TextView["+shareCount+"]|//android.widget.TextView[@content-desc='txt_shareCounts_details']|android.widget.TextView["+shareCount+"]";
		
		if(isElementLocatedByXpathPresent(shareButtonXpath)){
			reportStep("Verified the presence of share button", "INFO");

		}else {
			reportStep("Not able to verify the presence share button", "FAIL");

		}

		return this;

	}

	public StoreCategoryPage validateShareIconWithCountAbsence(int shareCount) {

		reportStep("About to validate ShareIconWithCountAbsence", "INFO");
		
		String shareButtonXpath = "//android.view.ViewGroup[@content-desc='txt_sharecount_open_modal']/android.widget.TextView["+shareCount+"]";
		
		if(isElementLocatedByXpathAbsence(shareButtonXpath)){
			reportStep("Verified the absence of share button", "PASS");

		}else {
			reportStep("Not able to verify the absence share button", "FAIL");

		}

		return this;

	}

	public StoreCategoryPage validateHorizontalScrollViewPresence(int HorizontalScrollViewCount) {

		reportStep("About to validate HorizontalScrollViewPresence", "INFO");
		
		String horizontalScrollViewXpath = "//android.widget.TextView/parent::android.view.ViewGroup/parent::android.view.ViewGroup/parent::android.view.ViewGroup/parent::android.widget.HorizontalScrollView/parent::android.view.ViewGroup/android.view.ViewGroup["+HorizontalScrollViewCount+"]";
		
		if(isElementLocatedByXpathPresent(horizontalScrollViewXpath)){
			reportStep("Verified the HorizontalScrollViewPresence", "PASS");

		}else {
			reportStep("Not able to verify the HorizontalScrollViewPresence", "FAIL");

		}

		return this;

	}

	public StoreCategoryPage validateHorizontalScrollViewAbsence(int HorizontalScrollViewCount) {

		reportStep("About to validate HorizontalScrollViewPresence", "INFO");
		
		String horizontalScrollViewXpath = "//android.widget.TextView/parent::android.view.ViewGroup/parent::android.view.ViewGroup/parent::android.view.ViewGroup/parent::android.widget.HorizontalScrollView/parent::android.view.ViewGroup/android.view.ViewGroup["+HorizontalScrollViewCount+"]";
		
		if(isElementLocatedByXpathAbsence(horizontalScrollViewXpath)){
			
			reportStep("Verified the HorizontalScrollViewPresence", "PASS");

		}else {
			reportStep("Not able to verify the HorizontalScrollViewPresence", "FAIL");

		}

		return this;

	}
		
	public boolean swipeFromRightToLeft() {
		
		for(int i=0;i<6;i++) {
			System.out.println("swipe from right to left  i = "+i);
			try {
	
				Dimension size = sharedCountTopView.getSize();
				int x0 = (int) (size.getWidth() * 0.3);
				int y0 = (int) (size.getHeight() * 0.5);
				int x1 = (int) (size.getWidth() * 0.9);
				int y1 = (int) (size.getHeight() * 0.5);
	
				System.out.println("x0  :"+ x0 + " x1  : "+ x1 + " y0  : "+ y0 + " y1 :"+ y1);
				MultiTouchAction touch = new MultiTouchAction(driver);
				touch.add(new TouchAction<>(driver).press(point(x1, y1))
						.waitAction(WaitOptions.waitOptions(Duration.ofSeconds(2))).moveTo(point(x0, y0)).release())
				.perform();
	
	
			} catch (Exception e) {
				e.printStackTrace();
			}
	
			reportStep("Successfully scrolled from Down to Up ", "INFO");
		}
	
	
		return true;
	}

	public StoreCategoryPage validateNetworkErrorsInStoreCategoryPage() {

		reportStep("About to TurnOff the Wifi", "Info");
		//Wifi - ON to OFF
		toggleWifi();

		new NetworkErrorPage(driver, testInfo).
		validateNetworkPageErrors();

		//Wifi - OFF to ON
		toggleWifi();
		new StoreCategoryPage(driver, testInfo);
		reportStep("Successfully Turned On the Wifi ", "Pass");
		return this;

	}

	
	
}
