package com.app.ck.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.app.ck.base.WrapperMethods;
import com.app.ck.utilities.CashKaroUtility;
import com.aventstack.extentreports.ExtentTest;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class StoreDetailPage extends WrapperMethods {

	//Constructor call
	public StoreDetailPage(AppiumDriver<MobileElement> driver, ExtentTest testInfo) {

		WebDriverWait wait = new WebDriverWait(driver, 30);

		this.driver = driver;
		this.testInfo = testInfo;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);

		reportStep("Waiting for the StoreDetailPage", "INFO");
		try {

			wait.until(ExpectedConditions.visibilityOf(shareNow));
			reportStep("Successfully landed on the StoreDetailPage", "PASS");

		}catch(Exception e) {

			reportStep(e.getMessage(), "INFO");
			reportStep("Not able to land on the StoreDetailPage", "FAIL");
		}

	}

	//Common
	@FindBy(how = How.XPATH, using = "//android.view.ViewGroup[@content-desc='txt_store_addto_share_add_top']")
	MobileElement addToShareButton;

	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@content-desc='btn_store_addto_remove_add']")
	MobileElement removeButton;

	@FindBy(how = How.XPATH, using = "//android.widget.ImageView[@content-desc='img_store_others_share']")
	MobileElement shareIcon;

	@FindBy(how = How.XPATH, using = "//android.view.ViewGroup[@content-desc='txt_sharecount_open_modal']")
	MobileElement shareIconWithCount;

	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@content-desc='txt_store_shortDescription']") 
	MobileElement storeAppShortDesc;

	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='COPY LINK']") 
	MobileElement copyLink;

	@FindBy(how = How.XPATH, using = "//android.view.ViewGroup[@content-desc='txt_store_whatsapp_share_top']")
	MobileElement shareNow;

	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@content-desc='btn_sharecount_whatsapp_share']")
	MobileElement shareNow_Popup;

	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='WhatsApp']") 
	MobileElement whatsApp;

	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='CLEAR ALL']") 
	MobileElement clearAllButton;

	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='CLOSE']") 
	MobileElement closeButton;

	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='Profile']")
	MobileElement profileIcon;

	@FindBy(how = How.XPATH, using = "//*[contains(@text,'OK')]") 
	MobileElement okButton;

	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='See Profit Rates']") 
	MobileElement seeProfitRates;



	public StoreDetailPage validateStoreShortDescription(String storeName , String expected) {

		reportStep("About to validate the store "+ storeName+" 's short description in StoreDetailPage", "INFO");

		String actual = getText(storeAppShortDesc);

		validateTheActualValueWithExpectedValue(actual, expected);

		return this;

	}

	public StoreDetailPage validateprimaryProfit(String primaryProfitValue) {

		reportStep("About to validate the primaryProfit", "INFO");

		String primaryProfitXpath = "//android.widget.TextView[contains(@text,'Your Profit')]/parent::android.view.ViewGroup/android.widget.TextView[contains(@text,'"+primaryProfitValue+"')]";

		if(isElementLocatedByXpathPresent(primaryProfitXpath)){
			reportStep("Verified the presence of primaryProfit", "INFO");

		}else {
			reportStep("Not able to verify the presence primaryProfit", "FAIL");

		}

		MobileElement primaryProfit = driver.findElement(By.xpath(primaryProfitXpath));

		validateTheElementPresence(primaryProfit);

		return this;

	}

	public StoreDetailPage clickOnAddToShareButton() {

		reportStep("About to click on the add to share button", "INFO");

		if(click(addToShareButton)) {

			reportStep("Successfully clicked on add to share button", "PASS");
		}else {

			reportStep("Failed to click on add to share button", "FAIL");
		}

		validateTheElementPresence(shareIconWithCount);

		return this;

	}

	public StoreDetailPage clickOnRemoveButton() {

		reportStep("About to click on the remove button", "INFO");

		if(click(removeButton)) {

			reportStep("Successfully clicked on remove button", "PASS");
		}else {

			reportStep("Failed to click on remove button", "FAIL");
		}

		//validateTheElementAbsence(shareIconWithCount);

		return this;

	}

	public StoreDetailPage validateRemoveButtonAbsence() {

		reportStep("About to validate on the absence remove button", "INFO");

		String removeButtonXpath = "//android.widget.TextView[@content-desc='btn_store_addto_remove_add']";

		if(isElementLocatedByXpathPresent(removeButtonXpath)){
			reportStep("Verified the absence of removeButton", "INFO");

		}else {
			reportStep("Not able to verify the absence of removeButton", "FAIL");

		}

		return this;

	}

	public StoreDetailPage clickOnShareNow() {

		reportStep("About to click on the share now button", "INFO");

		if(clickAfterWait(shareNow)) {

			reportStep("Successfully clicked on share now button", "PASS");
		}else {

			reportStep("Failed to click on share now button", "FAIL");
		}

		return this;

	}

	public StoreDetailPage clickOnShareIcon() {

		reportStep("About to click on the shareIcon button", "INFO");

		if(click(shareIcon)) {

			reportStep("Successfully clicked on shareIcon button", "PASS");
		}else {

			reportStep("Failed to click on shareIcon button", "FAIL");
		}

		//validateTheElementPresence(whatsApp);

		return this;

	}

	public StoreDetailPage clickOnWhatsApp() {

		try {

			clickAfterWait(whatsApp);
			reportStep("Successfully clicked on WhatsAppIcon button", "PASS");

		} catch (Exception e) {
			reportStep("Failed to click on WhatsAppIcon button", "FAIL");
		}

		return this;

	}

	public StoreDetailPage clickOnShareIconWithCount(int shareCount) {

		reportStep("About to click on the share icon", "INFO");

		String shareButtonXpath = "//android.view.ViewGroup[@content-desc='txt_sharecount_open_modal']/android.view.ViewGroup/android.widget.TextView[@text='"+shareCount+"']";

		if(isElementLocatedByXpathPresent(shareButtonXpath)){
			reportStep("Verified the presence of share icon", "INFO");

		}else {
			reportStep("Not able to verify the presence share icon", "FAIL");

		}

		MobileElement shareButton = driver.findElement(By.xpath(shareButtonXpath));

		if(click(shareButton)) {

			reportStep("Successfully clicked on share icon", "PASS");
		}else {

			reportStep("Failed to click on share icon", "FAIL");
		}

		String testimonialXpath = "//android.view.ViewGroup[@content-desc='txt_sharecount_whatsapp_share_top']";

		MobileElement testimonial = driver.findElement(By.xpath(testimonialXpath));

		validateTheElementPresence(testimonial);

		return this;

	}

	public StoreDetailPage clickOnCopyLink() {

		reportStep("About to click on the CopyLink", "INFO");

		if(click(copyLink)) {

			reportStep("Successfully clicked on CopyLink", "PASS");
		}else {

			reportStep("Failed to click on CopyLink", "FAIL");
		}

		validateTheElementPresence(copyLink);

		return this;

	}

	public StoreCategoryPage clickBackButton() {

		reportStep("About to click on back button in store detail page", "INFO");

		driver.navigate().back();

		return new StoreCategoryPage(driver, testInfo);

	}

	public StoreDetailPage clickBackButton_StoreDetailPage() {

		reportStep("About to click on back button in store detail page", "INFO");

		driver.navigate().back();

		return this;

	}

	public StoreDetailPage validateStoreImageInPopup(String shortDesc) {

		reportStep("About to validate the store image", "INFO");

		String storeImageXpath = "//android.widget.TextView['"+shortDesc+"']/parent::android.view.ViewGroup/android.view.ViewGroup/android.widget.ImageView[@content-desc='img_product_image']";

		if(isElementLocatedByXpathPresent(storeImageXpath)){
			reportStep("Verified the presence of storeImage", "INFO");

		}else {
			reportStep("Not able to verify the presence of storeImage", "FAIL");

		}

		return this;

	}

	public StoreDetailPage validateStoreImage(String shortDesc) {

		reportStep("About to validate the store image", "INFO");

		String storeImageXpath = "//android.widget.TextView['"+shortDesc+"']/parent::android.view.ViewGroup/android.widget.ImageView[@content-desc='img_store_logo']";

		if(isElementLocatedByXpathPresent(storeImageXpath)){
			reportStep("Verified the presence of storeImage", "INFO");

		}else {
			reportStep("Not able to verify the presence of storeImage", "FAIL");

		}

		return this;

	}

	public StoreDetailPage validateStoreNameInPopup(String storeName) {

		reportStep("About to validate the store name", "INFO");

		String storeNameXpath = "//android.widget.TextView['"+storeName+"']";

		if(isElementLocatedByXpathPresent(storeNameXpath)){
			reportStep("Verified the presence of storeName", "INFO");

		}else {
			reportStep("Not able to verify the presence of storeName", "FAIL");

		}

		return this;

	}

	public StoreDetailPage validateShortDescriptionInPopup(String shortDesc) {

		reportStep("About to validate the short_Desc", "INFO");

		String shortDescXpath = "//android.widget.TextView['"+shortDesc+"']";

		if(isElementLocatedByXpathPresent(shortDescXpath)){
			reportStep("Verified the presence of short_Desc", "INFO");

		}else {
			reportStep("Not able to verify the presence of short_Desc", "FAIL");

		}

		return this;

	}

	public StoreDetailPage clickOnShareNowInPopup() {

		reportStep("About to click on the share now button", "INFO");

		if(click(shareNow_Popup)) {

			reportStep("Successfully clicked on share now button", "PASS");
		}else {

			reportStep("Failed to click on share now button", "FAIL");
		}

		return this;

	}

	public StoreDetailPage clickOnclearAllButtonInPopup() {

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

	public StoreDetailPage clickOnOkButtonClearAllPopup() {

		reportStep("About to click on the ok button in clearAll popup", "INFO");

		if(click(okButton)) {

			reportStep("Successfully clicked on ok button in clearAll popup", "PASS");
		}else {

			reportStep("Failed to click on ok button in clearAll popup", "FAIL");
		}

		return this;

	}

	public StoreDetailPage clickOncloseButtonInPopup() {

		reportStep("About to click on the closeButton", "INFO");

		if(click(closeButton)) {

			reportStep("Successfully clicked on closeButton", "PASS");
		}else {

			reportStep("Failed to click on closeButton", "FAIL");
		}

		return this;

	}

	public StoreDetailPage validateShareIconWithCountPresence(int shareCount) {

		reportStep("About to validate share button", "INFO");

		String shareButtonXpath = "//android.view.ViewGroup[@content-desc='txt_sharecount_open_modal']/android.widget.TextView["+shareCount+"]";

		if(isElementLocatedByXpathPresent(shareButtonXpath)){
			reportStep("Verified the presence of share button", "INFO");

		}else {
			reportStep("Not able to verify the presence share button", "FAIL");

		}

		return this;

	}

	public StoreDetailPage validateShareIconWithCountAbsence(int shareCount) {

		reportStep("About to validate share button", "INFO");

		String shareButtonXpath = "//android.view.ViewGroup[@content-desc='txt_sharecount_open_modal']/android.widget.TextView["+shareCount+"]";

		if(isElementLocatedByXpathAbsence(shareButtonXpath)){
			reportStep("Verified the presence of share button", "INFO");

		}else {
			reportStep("Not able to verify the presence share button", "FAIL");

		}

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

	public void validateDeepLinkRedirection(){

		if(isElementLocatedByXpathPresent("//android.widget.TextView[@text='Amazon']")) {

			reportStep("Validate the Deffered deep linking redirection ", "PASS");
		} else {
			reportStep("Not able to redirect to the Deffered deep link", "FAIL");
		}

	}

	public StoreDetailPage clickSeeProfitRates() {

		reportStep("About to click See Profit Rates in Store Details Page ", "INFO");

		if(click(seeProfitRates)) {

			reportStep("Successfully clicked on See Profit Rates link ", "PASS");

		} else {

			reportStep("Failed to click on See Profit Rates link ", "FAIL");
		}

		return this;
	}

	public StoreDetailPage validatePartnerCashbackValuePresence(String cashbackValue) {

		reportStep("About to validate the cashback value: "+cashbackValue+"", "INFO");

		String cashbackValueXpath = "//android.widget.TextView[contains(@text,'"+cashbackValue+"')]";

		MobileElement cashbackvalue = driver.findElement(By.xpath(cashbackValueXpath));
		System.out.println("Text with rupee symbol " + cashbackvalue.getText());
		//₹200

		validateTheElementPresence(cashbackvalue);

		reportStep("Successfully validated the cashback value: "+cashbackValue+"", "INFO");

		return this;

	}

	public StoreDetailPage validatePartnerCashbackDetailsPresence(String cashbackDetails) {

		reportStep("About to validate the cashback details: "+cashbackDetails+"", "INFO");

		String cashbackDetailsXpath = "//android.widget.TextView[@text='"+cashbackDetails+"']";

		MobileElement cashbackdetails = driver.findElement(By.xpath(cashbackDetailsXpath));

		validateTheElementPresence(cashbackdetails);

		reportStep("Successfully validated the cashback details: "+cashbackDetails+"", "INFO");

		return this;

	}

	public StoreDetailPage validatePartnerCashbackValueAbsence(String cashbackValue) {

		reportStep("About to validate the cashback value: "+cashbackValue+"", "INFO");

		String cashbackValueXpath = "//android.widget.TextView[contains(@text,'"+cashbackValue+"')]";

		if(driver.findElements(By.xpath(cashbackValueXpath)).size()==0) {

			reportStep("Successfully validated that See Profit Rates can be Closed ", "PASS");

		} else {
			reportStep("See Profit Rates  can not be closed", "FAIL");
		}

		return this;

	}

	public StoreDetailPage validatePartnerCashbackDetailsAbsence(String cashbackDetails) {

		reportStep("About to validate the cashback details: "+cashbackDetails+"", "INFO");

		String cashbackDetailsXpath = "//android.widget.TextView[@text='"+cashbackDetails+"']";

		validateTheElementAbsence(cashbackDetailsXpath);

		reportStep("Successfully validated the cashback details: "+cashbackDetails+"", "INFO");

		return this;

	}

	public StoreDetailPage validateNetoworkErrorsInStoreDetailsPage() {

		reportStep("About to TurnOff the Wifi", "Info");
		//Wifi - ON to OFF
		toggleWifi();

		new NetworkErrorPage(driver, testInfo).
		validateNetworkPageErrors();

		//Wifi - OFF to ON
		toggleWifi();

		new StoreDetailPage(driver, testInfo);
		reportStep("About to Turn On the Wifi", "Info");

		return this;

	}



}
