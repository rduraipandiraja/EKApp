package com.app.ck.pages.admin;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.app.ck.base.Base;
import com.app.ck.base.WrapperMethods;
import com.app.ck.utilities.Utilities;
import com.aventstack.extentreports.ExtentTest;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class AdminStoresPage extends WrapperMethods {
	
	//Constructor creation :
	public AdminStoresPage(AppiumDriver<MobileElement> driver, ExtentTest testInfo) {
		
		this.driver = driver;
		this.testInfo = testInfo;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	// Element declaration here :
	
	@FindBy(how = How.ID, using = "sstring") 
	MobileElement storeNameTextBox;

	@FindBy(how = How.ID, using = "name") 
	MobileElement nameTextBox;

	@FindBy(how = How.ID, using = "network_commission") 
	MobileElement networkCommisionTextBox;
	
	@FindBy(how = How.ID, using = "app_desc") 
	MobileElement shortDescriptionTextBox;

	@FindBy(how = How.ID, using = "cashback") 
	MobileElement cashbackTextBox;

	@FindBy(how = How.ID, using = "csh_cashback_0") 
	MobileElement secondaryCashbackCashbackTextBox;

	@FindBy(how = How.ID, using = "cashback_details") 
	MobileElement cashbackDetailsTextBox;

	@FindBy(how = How.ID, using = "calendar_short_desc") 
	MobileElement calendarCashbackDetailsTextBox;
	
	@FindBy(how = How.ID, using = "calendar_csh_cashback_0") 
	MobileElement secondaryCalendarCashbackCashbackTextBox;

	@FindBy(how = How.ID, using = "csh_detail_0") 
	MobileElement secondaryCashbackDetailsTextBox;

	@FindBy(how = How.ID, using = "calendar_csh_detail_0") 
	MobileElement secondaryCalendarCashbackDetailsTextBox;

	@FindBy(how = How.ID, using = "cashback_percentage") 
	MobileElement cashbackPercentageTextBox;

	@FindBy(how = How.ID, using = "calendar_network_commission") 
	MobileElement calendarNetworkCommisionTextBox;

	@FindBy(how = How.ID, using = "calendar_cashback") 
	MobileElement calendarCashbackTextBox;

	@FindBy(how = How.ID, using = "calendar_cashback_percentage") 
	MobileElement calendarCashbackPercentageTextBox;

	@FindBy(how = How.ID, using = "linkurl") 
	MobileElement linkURL;

	@FindBy(how = How.ID, using = "url_key") 
	MobileElement urlKeyTextBox;
	
	@FindBy(how = How.ID, using = "exitclicks") 
	MobileElement exitClickTextBox;

	@FindBy(how = How.ID, using = "status") 
	MobileElement statusDropDown;

	@FindBy(how = How.ID, using = "cashback_offer_type") 
	MobileElement CalendarPrimaryCashbackOfferTypeDropDown;

	@FindBy(how = How.ID, using = "cashback_offer_type_0") 
	MobileElement CalendarSecondaryCashbackOfferTypeDropDown;

	@FindBy(how = How.ID, using = "networkid") 
	MobileElement affiliateNetworkDropDown;

	@FindBy(how = How.ID, using = "store_type") 
	MobileElement storeTypeDropDown;

	@FindBy(how = How.ID, using = "btn_Submit") 
	MobileElement submitButton;

	@FindBy(how = How.ID, using = "anc_AddNew") 
	MobileElement addNewStore;

	@FindBy(how = How.ID, using = "tab_Category") 
	MobileElement categoryTab;
	
	@FindBy(how = How.ID, using = "tab_PrimaryCashback") 
	MobileElement primaryCashbackTab;
	
	@FindBy(how = How.ID, using = "tab_SecondaryCashback") 
	MobileElement secondaryCashbackTab;

	@FindBy(how = How.ID, using = "storeCat_popular-today_anchor") 
	MobileElement categoryPopularToday;

	@FindBy(how = How.ID, using = "calendar_expirytime") 
	MobileElement calendarPrimaryCashbackExpiryTime;

	@FindBy(how = How.ID, using = "calendar_expirytime_Cashback_0") 
	MobileElement calendarSecondaryCashbackExpiryTime;

	@FindBy(how = How.ID, using = "calendar_issuetime") 
	MobileElement calendarPrimaryCashbackIssueTime;

	@FindBy(how = How.ID, using = "calendar_issuetime_Cashback_0") 
	MobileElement calendarSecondaryCashbackIssueTime;

	@FindBy(how = How.XPATH, using = "//input[@class='bootstrap-timepicker-hour']") 
	MobileElement setHours;

	@FindBy(how = How.XPATH, using = "//input[@class='bootstrap-timepicker-minute']") 
	MobileElement setMinutes;

	@FindBy(how = How.XPATH, using = "//input[@class='bootstrap-timepicker-second']") 
	MobileElement setSeconds;

	@FindBy(how = How.XPATH, using = "//a[@class='fa fa-edit']") 
	List <MobileElement> editIConClick;

	@FindBy(how = How.ID, using = "uploadStoreBanner") 
	MobileElement uploadChooseFile;

	@FindBy(how = How.XPATH, using = "//td[text()='In-Active']") 
	MobileElement inactiveStore;

	@FindBy(how = How.ID, using = "calendar_expirytime") 
	MobileElement expiryTime;

	
	// Method Creation starts here :
	
	public void clickAddNewButton() {
		
	reportStep("About to click add new button", "INFO");
	
	jsClickUsingID("anc_AddNew");

	reportStep("Successfully clicked add new button", "PASS");
	
	}
	
	public void enterStoreName(String storeName) {

		reportStep("About to enter store name in storename text box", "INFO");

		//Enter store name in storename textbox
		if(enterTextInChrome(storeNameTextBox, storeName)) {

			reportStep("Successfully entered store name "+storeName+" in storename text box", "PASS");
		}else {

			reportStep("Not able to enter store name "+storeName+" in storename text box", "FAIL");
		}
		
		reportStep("Successfully entered store name "+storeName+" in storename text box", "PASS");
		
	}

	public void enterName(String storeName) {

		reportStep("About to enter name in storename text box", "INFO");

		//Enter store name in storename textbox
		if(enterTextInChrome(nameTextBox, storeName)) {

			reportStep("Successfully entered name "+storeName+" in name text box", "PASS");
		}else {

			reportStep("Not able to enter name "+storeName+" in name text box", "FAIL");
		}
		
		reportStep("Successfully entered name "+storeName+" in name text box", "PASS");
		
	}
	
	public void enterUrlKey(String urlKey) {

		reportStep("About to enter name in url key text box", "INFO");

		//Enter store name in storename textbox
		if(enterTextInChrome(urlKeyTextBox, urlKey)) {

			reportStep("Successfully entered url key "+urlKey+" in url key text box", "PASS");
		}else {

			reportStep("Not able to enter url key "+urlKey+" in url key text box", "FAIL");
		}
		
	}

	public void enterExitClick(String exitClick) {

		reportStep("About to enter exitClick in exitClick text box", "INFO");

		if(enterTextInChrome(exitClickTextBox, exitClick)) {

			reportStep("Successfully entered exitClick "+exitClick+" in exitClick text box", "PASS");
		}else {

			reportStep("Not able to enter exitClick "+exitClick+" in exitClick text box", "FAIL");
		}
		
	}
	
	public void enterNetworkCommision(String nwCommision) {

		reportStep("About to enter name in nw commision text box", "INFO");

		//Enter store name in storename textbox
		if(enterTextInChrome(networkCommisionTextBox, nwCommision)) {

			reportStep("Successfully entered nwCommision "+nwCommision+" in nwCommision text box", "PASS");
		}else {

			reportStep("Not able to enter nwCommision "+nwCommision+" in nwCommision text box", "FAIL");
		}
		
	}
	
	public String enterShortDescription(String shortDescription) {

		reportStep("About to enter name in ShortDescription text box", "INFO");

		if(enterTextInChrome(shortDescriptionTextBox, shortDescription)) {

			reportStep("Successfully entered shortDescription "+shortDescription+" in shortDescription text box", "PASS");
		}else {

			reportStep("Not able to enter shortDescription "+shortDescription+" in shortDescription text box", "FAIL");
		}
		return shortDescription;
		
	}

	public void enterCashback(String cashback) {

		reportStep("About to enter name in cashback text box", "INFO");

		//Enter store name in storename textbox
		if(enterTextInChrome(cashbackTextBox, cashback)) {

			reportStep("Successfully entered cashback "+cashback+" in cashback text box", "PASS");
		}else {

			reportStep("Not able to enter cashback "+cashback+" in cashback text box", "FAIL");
		}
		
	}

	public void entercashbackDetails(String details) {

		reportStep("About to enter name in cashback Details text box", "INFO");

		//Enter store name in storename textbox
		if(enterTextInChrome(cashbackDetailsTextBox, details)) {

			reportStep("Successfully entered details "+details+" in details text box", "PASS");
		}else {

			reportStep("Not able to enter details "+details+" in details text box", "FAIL");
		}
		
	}

	public void enterCalendarCashbackDetails(String details) {

		reportStep("About to enter name in Calendar cashback Details text box", "INFO");

		//Enter store name in storename textbox
		if(enterTextInChrome(calendarCashbackDetailsTextBox, details)) {

			reportStep("Successfully entered details "+details+" in details text box", "PASS");
		}else {

			reportStep("Not able to enter details "+details+" in details text box", "FAIL");
		}
		
	}

	public void enterSecondaryCashback(String cashback) {

		reportStep("About to enter name in secondary cashback text box", "INFO");

		//Enter store name in storename textbox
		if(enterTextInChrome(secondaryCashbackCashbackTextBox, cashback)) {

			reportStep("Successfully entered cashback "+cashback+" in cashback text box", "PASS");
		}else {

			reportStep("Not able to enter cashback "+cashback+" in cashback text box", "FAIL");
		}
		
	}

	public void enterCalendarSecondaryCashback(String cashback) {

		reportStep("About to enter name in calendar secondary cashback text box", "INFO");

		//Enter store name in storename textbox
		if(enterTextInChrome(secondaryCalendarCashbackCashbackTextBox, cashback)) {

			reportStep("Successfully entered cashback "+cashback+" in cashback text box", "PASS");
		}else {

			reportStep("Not able to enter cashback "+cashback+" in cashback text box", "FAIL");
		}
		
	}

	public void enterSecondaryCashbackDetails(String details) {

		reportStep("About to enter name in secondary cashback Details text box", "INFO");

		//Enter store name in storename textbox
		if(enterTextInChrome(secondaryCashbackDetailsTextBox, details)) {

			reportStep("Successfully entered details "+details+" in details text box", "PASS");
		}else {

			reportStep("Not able to enter details "+details+" in details text box", "FAIL");
		}
		
	}

	public void enterCalendarSecondaryCashbackDetails(String details) {

		reportStep("About to enter name in calendar secondary cashback Details text box", "INFO");

		//Enter store name in storename textbox
		if(enterTextInChrome(secondaryCalendarCashbackDetailsTextBox, details)) {

			reportStep("Successfully entered details "+details+" in details text box", "PASS");
		}else {

			reportStep("Not able to enter details "+details+" in details text box", "FAIL");
		}
		
	}
	
	public void enterCashbackPercentage(String cashbackPercentage) {

		reportStep("About to enter name in cashback percentage text box", "INFO");

		//Enter store name in storename textbox
		if(enterTextInChrome(cashbackPercentageTextBox, cashbackPercentage)) {

			reportStep("Successfully entered cashback percentage "+cashbackPercentage+" in cashback percentage text box", "PASS");
		}else {

			reportStep("Not able to enter cashback percentage "+cashbackPercentage+" in cashback percentage text box", "FAIL");
		}
		
	}

	public void enterCalendarNetworkCommision(String calendarnwCommision) {

		reportStep("About to enter name in nw commision text box", "INFO");

		//Enter store name in storename textbox
		if(enterTextInChrome(calendarNetworkCommisionTextBox, calendarnwCommision)) {

			reportStep("Successfully entered nwCommision "+calendarnwCommision+" in nwCommision text box", "PASS");
		}else {

			reportStep("Not able to enter nwCommision "+calendarnwCommision+" in nwCommision text box", "FAIL");
		}
		
	}

	public void enterCalendarCashback(String calendarcashback) {

		reportStep("About to enter name in cashback text box", "INFO");

		//Enter store name in storename textbox
		if(enterTextInChrome(calendarCashbackTextBox, calendarcashback)) {

			reportStep("Successfully entered cashback "+calendarcashback+" in cashback text box", "PASS");
		}else {

			reportStep("Not able to enter cashback "+calendarcashback+" in cashback text box", "FAIL");
		}
		
	}

	public void enterCalendarCashbackPercentage(String calendarcashbackPercentage) {

		reportStep("About to enter name in cashback percentage text box", "INFO");

		//Enter store name in storename textbox
		if(enterTextInChrome(calendarCashbackPercentageTextBox, calendarcashbackPercentage)) {

			reportStep("Successfully entered cashback percentage "+calendarcashbackPercentage+" in cashback percentage text box", "PASS");
		}else {

			reportStep("Not able to enter cashback percentage "+calendarcashbackPercentage+" in cashback percentage text box", "FAIL");
		}
		
	}

	public void enterLinkURL(String storeURL) {

		reportStep("About to enter store link URL", "INFO");

		//Enter store name in storename textbox
		if(enterTextInChrome(linkURL, storeURL)) {

			reportStep("Successfully entered store link URL "+storeURL+" in store link URL text box", "PASS");
		}else {

			reportStep("Not able to enter store link URL "+storeURL+" in store link URL text box", "FAIL");
		}
		
	}

	public void clickSubmitButton() {
	
	reportStep("About to click submit button", "INFO");
	
	//Click submit button
	if(jsClickUsingID("btn_Submit")) {
	
	reportStep("Successfully clicked submit button", "PASS");
	}else {
	
	reportStep("Not able to click submit button", "FAIL");
	}
	
	}

	public void clickPopularTodayCategory() {
		
	reportStep("About to click popular today category", "INFO");
	
	//Click submit button
	if(jsClickUsingID("storeCat_popular-today_anchor")) {
	
	reportStep("Successfully clicked popular today category", "PASS");
	}else {
	
	reportStep("Not able to click popular today category", "FAIL");
	}
	
	}

	public void clickAutomationSpecificCategory() {
		
	reportStep("About to click automation specific sub category", "INFO");
	
	//Click submit button
	if(jsClickUsingID("storeCat_pps-atm-app-specific-category_anchor")) {
	
	reportStep("Successfully clicked automation specific sub category", "PASS");
	}else {
	
	reportStep("Not able to click automation specific sub category", "FAIL");
	}
	
	}

	public void clickAutoSpecificCategory() {
		
	reportStep("About to click automation specific category", "INFO");
	
	//Click submit button
	if(jsClickUsingID("storeCat_app-exclusive_anchor")) {
	
	reportStep("Successfully clicked automation specific category", "PASS");
	}else {
	
	reportStep("Not able to click automation specific category", "FAIL");
	}
	
	}

	public void clickCategoryTab() {
		
	reportStep("About to click category tab", "INFO");
	
	//Click submit button
	if(jsClickUsingID("tab_Category")) {
	
	reportStep("Successfully clicked category tab", "PASS");
	}else {
	
	reportStep("Not able to click category tab", "FAIL");
	}
	
	}

	public void clickPrimaryCashbackTab() {
		
	reportStep("About to click primary cashback tab", "INFO");
	
	//Click submit button
	if(jsClickUsingID("tab_PrimaryCashback")) {
	
	reportStep("Successfully clicked primary cashback tab", "PASS");
	}else {
	
	reportStep("Not able to click primary cashback tab", "FAIL");
	}
	
	}
	
	public void clickSecondaryCashbackTab() {
		
	reportStep("About to click secondary cashback tab", "INFO");
	
	//Click submit button
	if(jsClickUsingID("tab_SecondaryCashback")) {
	
	reportStep("Successfully clicked secondary cashback tab", "PASS");
	}else {
	
	reportStep("Not able to click secondary cashback tab", "FAIL");
	}
	
	}
	
	public void clickEditButton(int index) {
	
	reportStep("About to click edit button", "INFO");
	
	//Click submit button
	if(clickAfterWait(editIConClick.get(index-1))) {
	
	reportStep("Successfully clicked edit button", "PASS");
	}else {
	
	reportStep("Not able to click edit button", "FAIL");
	}
	
	reportStep("Successfully clicked edit button", "PASS");
	
	}

	public void selectStatusFromDropDown(String dropDownValue) {

		reportStep("About to select value "+dropDownValue+" from drop down", "INFO");

		if(selectValuesFromDropdown(statusDropDown, dropDownValue)) {
		
		reportStep("Successfully selected value "+dropDownValue+" from the dropdown", "PASS");
		}else {
		
		reportStep("Not able to select value "+dropDownValue+" from dropdown", "FAIL");
		}
	}

	public void selectPrimaryCashbackOfferTypeFromDropDown(String dropDownValue) {

		reportStep("About to select value "+dropDownValue+" from drop down", "INFO");

		if(selectValuesFromDropdown(CalendarPrimaryCashbackOfferTypeDropDown, dropDownValue)) {
		
		reportStep("Successfully selected value "+dropDownValue+" from the dropdown", "PASS");
		}else {
		
		reportStep("Not able to select value "+dropDownValue+" from dropdown", "FAIL");
		}
	}
	
	public void selectSecondaryCashbackOfferTypeFromDropDown(String dropDownValue) {

		reportStep("About to select value "+dropDownValue+" from drop down", "INFO");

		if(selectValuesFromDropdown(CalendarSecondaryCashbackOfferTypeDropDown, dropDownValue)) {
		
		reportStep("Successfully selected value "+dropDownValue+" from the dropdown", "PASS");
		}else {
		
		reportStep("Not able to select value "+dropDownValue+" from dropdown", "FAIL");
		}
	}

	public void selectAffiliateNetworkFromDropDown(String dropDownValue) {

		reportStep("About to select value "+dropDownValue+" from drop down", "INFO");

		if(selectValuesFromDropdown(affiliateNetworkDropDown, dropDownValue)) {
		
		reportStep("Successfully selected value "+dropDownValue+" from the dropdown", "PASS");
		}else {
		
		reportStep("Not able to select value "+dropDownValue+" from dropdown", "FAIL");
		}
	}
	
	public void selectStoreTypeFromDropDown(String dropDownValue) {

		reportStep("About to select value "+dropDownValue+" from drop down", "INFO");

		if(selectValuesFromDropdown(storeTypeDropDown, dropDownValue)) {
		
		reportStep("Successfully selected value "+dropDownValue+" from the dropdown", "PASS");
		}else {
		
		reportStep("Not able to select value "+dropDownValue+" from dropdown", "FAIL");
		}
	}

	public void clickCheckBoxDesktop() {
		
	reportStep("About to click checkbox desktop", "INFO");
	
	//Click submit button
	if(jsClickUsingID("device_type_desktop")) {
	
	reportStep("Successfully clicked checkbox desktop", "PASS");
	}else {
	
	reportStep("Not able to click checkbox desktop", "FAIL");
	}
	
	}

	public void clickCheckBoxMobile() {
		
	reportStep("About to click checkbox mobile", "INFO");
	
	//Click submit button
	if(jsClickUsingID("device_type_mobile")) {
	
	reportStep("Successfully clicked checkbox mobile", "PASS");
	}else {
	
	reportStep("Not able to click checkbox mobile", "FAIL");
	}
	
	}

	public void clickCheckBoxTablet() {
		
	reportStep("About to click checkbox tablet", "INFO");
	
	//Click submit button
	if(jsClickUsingID("device_type_tablet")) {
	
	reportStep("Successfully clicked checkbox tablet", "PASS");
	}else {
	
	reportStep("Not able to click checkbox tablet", "FAIL");
	}
	
	}

	public void clickCheckBoxApp() {
		
	reportStep("About to click checkbox app", "INFO");
	
	//Click submit button
	if(jsClickUsingID("device_type_app")) {
	
	reportStep("Successfully clicked checkbox app", "PASS");
	}else {
	
	reportStep("Not able to click checkbox app", "FAIL");
	}
	
	}

	public void clickSecondaryCashbackCheckBoxDesktop() {
		
	reportStep("About to click checkbox desktop", "INFO");
	
	//Click submit button
	if(jsClickUsingID("device_type_desktop_0")) {
	
	reportStep("Successfully clicked checkbox desktop", "PASS");
	}else {
	
	reportStep("Not able to click checkbox desktop", "FAIL");
	}
	
	}

	public void clickSecondaryCashbackCheckBoxMobile() {
		
	reportStep("About to click checkbox mobile", "INFO");
	
	//Click submit button
	if(jsClickUsingID("device_type_mobile_0")) {
	
	reportStep("Successfully clicked checkbox mobile", "PASS");
	}else {
	
	reportStep("Not able to click checkbox mobile", "FAIL");
	}
	
	}

	public void clickSecondaryCashbackCheckBoxTablet() {
		
	reportStep("About to click checkbox tablet", "INFO");
	
	//Click submit button
	if(jsClickUsingID("device_type_tablet_0")) {
	
	reportStep("Successfully clicked checkbox tablet", "PASS");
	}else {
	
	reportStep("Not able to click checkbox tablet", "FAIL");
	}
	
	}

	public void clickSecondaryCashbackCheckBoxApp() {
		
	reportStep("About to click checkbox app", "INFO");
	
	//Click submit button
	if(jsClickUsingID("device_type_app_0")) {
	
	reportStep("Successfully clicked checkbox app", "PASS");
	}else {
	
	reportStep("Not able to click checkbox app", "FAIL");
	}
	
	}
	
	public void setCalendarPrimaryCashbackIssueDate() {
		
	reportStep("About to set CalendarPrimaryCashback issue date", "INFO");


	try {

		System.out.println("In try block about to set issue date");

		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("calendar_issuedate")));
			
	} catch (Exception e) {

		System.out.println("In catch block not able to set issue date");
	}
	
	String date = Utilities.setOneDayBeforeCurrentDate();
	
	((JavascriptExecutor)driver).executeScript("document.getElementById('calendar_issuedate').setAttribute('value', '"+date+"')");
	
	reportStep("Successfully set issue date", "INFO");
	
	}

	public void setCalendarPrimaryCashbackIssueDate_CurrentDate() {
		
	reportStep("About to set CalendarPrimaryCashback issue date", "INFO");


	try {

		System.out.println("In try block about to set issue date");

		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("calendar_issuedate")));
			
	} catch (Exception e) {

		System.out.println("In catch block not able to set issue date");
	}
	
	String date = Utilities.setCurrentDate();
	
	((JavascriptExecutor)driver).executeScript("document.getElementById('calendar_issuedate').setAttribute('value', '"+date+"')");
	
	reportStep("Successfully set issue date", "INFO");
	
	}

	public void setCalendarPrimaryCashbackExpiryDate() {
		
	reportStep("About to set CalendarPrimaryCashback expiry date", "INFO");


	try {

		System.out.println("In try block about to set expiry date");
		
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("calendar_expirydate")));
			
	} catch (Exception e) {

		System.out.println("In catch block not able to set expiry date");
	}
	
	String date = Utilities.setCurrentDate();
	
	((JavascriptExecutor)driver).executeScript("document.getElementById('calendar_expirydate').setAttribute('value', '"+date+"')");
	
	reportStep("Successfully set expiry date", "INFO");
	
	}

	public void setCalendarSecondaryCashbackIssueDate() {
		
	reportStep("About to set CalendarSecondaryCashback issue date", "INFO");


	try {

		System.out.println("In try block about to set issue date");

		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("calendar_issuedate_Cashback_0")));
			
	} catch (Exception e) {

		System.out.println("In catch block not able to set issue date");
	}
	
	String date = Utilities.setOneDayBeforeCurrentDate();
	
	((JavascriptExecutor)driver).executeScript("document.getElementById('calendar_issuedate_Cashback_0').setAttribute('value', '"+date+"')");
	
	reportStep("Successfully set issue date", "INFO");
	
	}

	public void setCalendarSecondaryCashbackIssueDate_CurrentDate() {
		
	reportStep("About to set CalendarSecondaryCashback issue date", "INFO");


	try {

		System.out.println("In try block about to set issue date");

		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("calendar_issuedate_Cashback_0")));
			
	} catch (Exception e) {

		System.out.println("In catch block not able to set issue date");
	}
	
	String date = Utilities.setCurrentDate();
	
	((JavascriptExecutor)driver).executeScript("document.getElementById('calendar_issuedate_Cashback_0').setAttribute('value', '"+date+"')");
	
	reportStep("Successfully set issue date", "INFO");
	
	}

	public void setCalendarSecondaryCashbackExpiryDate() {
		
	reportStep("About to set CalendarSecondaryCashback expiry date", "INFO");


	try {

		System.out.println("In try block about to set expiry date");
		
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("calendar_expirydate_Cashback_0")));
			
	} catch (Exception e) {

		System.out.println("In catch block not able to set expiry date");
	}
	
	String date = Utilities.setCurrentDate();
	
	((JavascriptExecutor)driver).executeScript("document.getElementById('calendar_expirydate_Cashback_0').setAttribute('value', '"+date+"')");
	
	reportStep("Successfully set expiry date", "INFO");
	
	}
	
	public void clickPrimaryCashbackExpiryTime(){

		reportStep("About to click primary cashback expiry time", "INFO");

		if(clickAfterWait(calendarPrimaryCashbackExpiryTime)) {

			reportStep("Successfully clicked primary cashback expiry time", "PASS");
		}else {

			reportStep("Not able to click primary cashback expiry time", "FAIL");
		}

	}
	
	public void clickSecondaryCashbackExpiryTime(){

		reportStep("About to click secondary cashback expiry time", "INFO");

		if(clickAfterWait(calendarSecondaryCashbackExpiryTime)) {

			reportStep("Successfully clicked secondary cashback expiry time", "PASS");
		}else {

			reportStep("Not able to click secondary cashback expiry time", "FAIL");
		}

	}

	public void clickPrimaryCashbackIssueTime(){

		reportStep("About to click primary cashback Issue time", "INFO");

		if(clickAfterWait(calendarPrimaryCashbackIssueTime)) {

			reportStep("Successfully clicked primary cashback Issue time", "PASS");
		}else {

			reportStep("Not able to click primary cashback Issue time", "FAIL");
		}

	}
	
	public void clickSecondaryCashbackIssueTime(){

		reportStep("About to click secondary cashback Issue time", "INFO");

		if(clickAfterWait(calendarSecondaryCashbackExpiryTime)) {

			reportStep("Successfully clicked secondary cashback Issue time", "PASS");
		}else {

			reportStep("Not able to click secondary cashback Issue time", "FAIL");
		}

	}

	public void setTimeTagToGetDisplayedInMinutesSeconds(String hours, String minutes, String seconds){

		reportStep("About to set hours expiry time", "INFO");
	
		if(enterTextAfterClick(setHours, hours)) {

			reportStep("Successfully set hours: "+hours+" expiry time", "PASS");
		}else {

			reportStep("Not able to set hours: "+hours+" expiry time", "FAIL");
		}

		if(enterTextAfterClick(setMinutes, minutes)) {

			reportStep("Successfully set minutes: "+minutes+" expiry time", "PASS");
		}else {

			reportStep("Not able to set minutes: "+minutes+" expiry time", "FAIL");
		}

		if(enterTextInChrome(setSeconds, seconds)) {

			reportStep("Successfully set seconds expiry time", "PASS");
		}else {

			reportStep("Not able to set seconds expiry time", "FAIL");
		}

	}

	public void uploadImage(String filePath) {

		reportStep("About to upload image using filePath "+filePath+"", "INFO");

		if(enterTextUsingSendKeys(uploadChooseFile, filePath)) {
		
		reportStep("Successfully upload image using filePath "+filePath+"", "PASS");
		}else {
		
		reportStep("Not able to upload image using filePath "+filePath+"", "FAIL");
		}
	}

	public String getTheStoreStatus() {
		
		reportStep("About to validate the hard coded store must be in active to validate the next scenarios", "INFO");
		
		if(validateTheElementPresence(inactiveStore)) {
			
			reportStep("Success - hard coded data is not changed - it is still in in active", "INFO");
			
			return "InActive";
			
		} else {
			
			try {
			reportStep("Failed - hard coded test data is been changed (Store is changed from InActive to Active)- so upcoming validation may get failed", "FAIL");
			}catch (Exception e) {
				reportStep("Handle the Exception to continue the execution", "INFO");
			}
			return "Active";
		}
		

	}
		
}