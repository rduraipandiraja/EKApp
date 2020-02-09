package com.app.ck.pages.admin;

import java.util.List;

import org.omg.CosNaming.IstringHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.app.ck.base.Base;
import com.app.ck.base.GetCodeSetUP;
import com.app.ck.base.PropertyFile;
import com.app.ck.base.WrapperMethods;
import com.aventstack.extentreports.ExtentTest;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class AdminCoreFunction extends WrapperMethods {

	public AdminCoreFunction(AppiumDriver<MobileElement> driver, ExtentTest testInfo) {

		this.driver = driver;
		this.testInfo = testInfo;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);

	}


	@FindBy(how = How.ID, using = "username") 
	MobileElement adminUserNameTextBox;

	@FindBy(how = How.ID, using = "password") 
	MobileElement adminPasswordTextBox;

	@FindBy(how = How.ID, using = "btn_Submit") 
	MobileElement submitButton;

	@FindBy(how = How.ID, using = "m_aside_left_offcanvas_toggle") 
	MobileElement HamburgerMenu;

	@FindBy(how = How.XPATH, using = "(//span[text()='Stores'])[2]") 
	MobileElement submenuStores;						

	@FindBy(how = How.ID, using = "SubMenu_Vouchers") 
	MobileElement submenuVouchers;

	@FindBy(how = How.XPATH, using = "(//span[text()='Stores'])[1]") 
	MobileElement menuStores;

	@FindBy(how = How.ID, using = "MainMenu_Users") 
	MobileElement menuUsers;						

	@FindBy(how = How.ID, using = "SubMenu_Users") 
	MobileElement subMenuUsers;						

	@FindBy(how = How.ID, using = "SubMenu_ProductBrowserEditMode") 
	MobileElement submenuProductBrowserEdit;

	@FindBy(how = How.XPATH, using = "//span[contains(.,'Product Feeds')]") 
	MobileElement menuProductFeed;

	@FindBy(how = How.ID, using = "MainMenu_Settings") 
	MobileElement menuSettings;

	@FindBy(how = How.ID, using = "SubMenu_PartnerSettings") 
	MobileElement menuParterSettings;
	@FindBy(how = How.ID, using = "partnerEdit_6") 
	MobileElement editPartner;
	@FindBy(how = How.ID, using = "SubMenu_AffiliateNetworks") 
	MobileElement menuAffliateNetworks;
	@FindBy(how = How.XPATH, using = "//textarea[@id='productCashbackUrl']") 
	MobileElement productURLTextArea;
	@FindBy(how = How.ID, using = "btn_Submit") 
	MobileElement editNetwork;
	@FindBy(how = How.XPATH, using = "//span[text()='Cashback']") 
	MobileElement menuCashback;
	@FindBy(how = How.XPATH, using = "//div[@id='adminMessageSuccess']") 
	MobileElement netWorkEditSuccessMessage;

	//Exit id page : 
	@FindBy(how = How.XPATH, using = "//span[contains(.,'Reports')]") 
	MobileElement reports;
	@FindBy(how = How.XPATH, using = "//a[contains(.,'Exit Clicks')]") 
	MobileElement exitClicks;
	@FindBy(how = How.XPATH, using = "//select[@id='sby']") 
	MobileElement exitClickPagesearchByDropdown;
	@FindBy(how = How.XPATH, using = "//input[@id='sterm']") 
	MobileElement exitClickPagesearchKeyword;
	@FindBy(how = How.XPATH, using = "//button[@id='btn_Submit']") 
	MobileElement exitClickPageSubmitButton;
	@FindBy(how = How.XPATH, using = "//table[@id='all_list']") 
	MobileElement resultTableOfExitID;

	//Email master :
	@FindBy(how = How.XPATH, using = "//select[@id='searchBy']") 
	MobileElement emailMasterSearchByDropDown;

	@FindBy(how = How.XPATH, using = "//input[@id='key']") 
	MobileElement searchKey;

	@FindBy(how = How.ID, using = "btnSubmit") 
	MobileElement searchButton;

	@FindBy(how = How.ID, using = "MainMenu_EmailMaster") 
	MobileElement emailMasterMainMenu;

	@FindBy(how = How.ID, using = "SubMenu_EmailMasterBrowser") 
	MobileElement emailMasterBrowserSubMenu;

	//EarnKaro admin drop down  :

	@FindBy(how = How.XPATH, using = "//select[@id='partnerdomain']") 
	MobileElement selectEarnKaro;

	//Missing Cashaback

	@FindBy(how = How.XPATH, using = "//span[contains(.,'Interactive')]") 
	MobileElement interActive;
	@FindBy(how = How.XPATH, using = "//a[@id='SubMenu_UserTickets']") 
	MobileElement subMenuUserTickets;
	@FindBy(how = How.XPATH, using = "//a[@id='SubMenu_Testimonials']") 
	MobileElement subMenuTestimonials;

	String earnkaroLogo = "//img[contains(@src,'earnkaro')]";

	public void naviagateAdminPage_ThenSelectEarnKaro() {

		reportStep("About to navigate to admin page", "INFO");

		//Enter the Admin URL

		if(PropertyFile.environment().equalsIgnoreCase("Staging")) {

			loadURL(GetCodeSetUP.ADMINURL_STAGING);

		} else if(PropertyFile.environment().equalsIgnoreCase("Beta")) {

			loadURL(GetCodeSetUP.ADMINURL);
		} else {
			
			loadURL("https://asdfqwer1980.com");
		}

		selectValuesFromDropdown(selectEarnKaro, "EarnKaro");

		try {
			reportStep("Enter try block to validate earnkaro logo", "INFO");

			if(isElementLocatedByXpathPresent(earnkaroLogo)) {

				reportStep("Successfully validated earnkaro logo", "INFO");

			} else {

				reportStep("Not validated earnkaro logo", "INFO");
			}

		} catch (Exception e) {

			reportStep("Enter catch block to validate earnkaro logo", "INFO");

			driver.navigate().refresh();

			selectValuesFromDropdown(selectEarnKaro, "EarnKaro");

			if(isElementLocatedByXpathPresent(earnkaroLogo)) {

				reportStep("Successfully validated earnkaro logo", "INFO");

			} else {

				reportStep("Not validated earnkaro logo", "INFO");
			}
		}

		reportStep("Successfully navigated to admin page", "PASS");

	}

	public void enterUsername(String username) {

		reportStep("About to enter username in admin login page", "INFO");


		if(enterTextInChrome(adminUserNameTextBox, username)) {

			reportStep("Successfully entered username "+username+" in text box", "PASS");

		}else {

			reportStep("Not able to enter username "+username+" in text box", "FAIL");
		}
	}

	public void enterPassword(String password) {

		reportStep("About to enter password in admin page", "INFO");

		//Enter the password
		if(enterTextInChrome(adminPasswordTextBox, password)) {

			reportStep("Successfully entered pwd "+password+" in text box", "PASS");
		}else {

			reportStep("Not able to enter pwd "+password+" in text box", "FAIL");
		}
	}

	public void clickSubmit(){

		reportStep("About to click submit button after filling the form in admin page", "INFO");

		try {

			if(clickAfterWait(submitButton)) {

				reportStep("Successfully clicked on the submit button", "PASS");
			}else {

				reportStep("Not able to click the submit button", "FAIL");
			}

			validateTheElementPresence(HamburgerMenu, "INFO");

		} catch (Exception e) {

			if(clickAfterWait(submitButton)) {

				reportStep("Successfully clicked on the submit button", "PASS");
			}else {

				reportStep("Not able to click the submit button", "FAIL");
			}

			validateTheElementPresence(HamburgerMenu);
		}
	}

	public void clickOnHamburgerMenu() {

		reportStep("About to click hamburger button in admin homepage", "INFO");

		try {

			//Click hamburger menu button
			if(clickAfterWait(HamburgerMenu)) {

				reportStep("Successfully clicked on the hamburger menu button", "PASS");
			}else {

				reportStep("Not able to click the hamburger menu button", "INFO");
				throw new Exception();
			}

		} catch (Exception e) {


			this.naviagateAdminPage_ThenSelectEarnKaro();
			this.enterUsername(getTestData(8, "AdminUserName"));
			this.enterPassword(getTestData(8, "AdminPassword"));
			this.clickSubmit();

			//Click hamburger menu button
			if(clickAfterWait(HamburgerMenu)) {

				reportStep("Successfully clicked on the hamburger menu button", "PASS");
			}else {

				reportStep("Not able to click the hamburger menu button", "FAIL");
			}
		}
	}

	public void clickStoresMenu() {

		reportStep("About to click menu stores in admin homepage", "INFO");

		//Click stores menu button
		if(clickAfterWait(menuStores)) {

			reportStep("Successfully clicked on the menu stores", "PASS");
		}else {

			reportStep("Not able to click the menu stores", "FAIL");
		}

	}

	public void clickStoresSubMenu() {

		reportStep("About to click submenu stores in admin homepage", "INFO");

		jsClickUsingID("SubMenu_Stores");

		reportStep("Successfully clicked submenu stores in admin homepage", "INFO");

	}

	public void clickVouchersSubMenu() {

		reportStep("About to click submenu voucher in admin homepage", "INFO");

		jsClickUsingID("SubMenu_Vouchers");

		reportStep("Successfully clicked submenu vouchers in admin homepage", "INFO");

	}

	public void clickPendingCashoutsSubMenu() {

		reportStep("About to click submenu PendingCashouts in admin homepage", "INFO");

		jsClickUsingID("SubMenu_PendingCashouts");

		reportStep("Successfully clicked submenu PendingCashouts in admin homepage", "INFO");

	}

	public void clickProductFeedMenu() {

		reportStep("About to click menu product feed in admin homepage", "INFO");

		//Click stores menu button
		if(clickAfterWait(menuProductFeed)) {

			reportStep("Successfully clicked on the menu product feed", "PASS");
		}else {

			reportStep("Not able to click the menu product feed", "FAIL");
		}

	}

	public void clickProductBrowserEditModeSubMenu() {

		reportStep("About to click submenu product browser edit mode in admin homepage", "INFO");

		//Click stores menu button
		if(clickAfterWait(submenuProductBrowserEdit)) {

			reportStep("Successfully clicked on the submenu product browser edit mode", "PASS");
		}else {

			reportStep("Not able to click the submenu product browser edit mode", "FAIL");
		}

	}

	public void clickOnSettings() {

		reportStep("About to click on the menu Settings in the admin ", "INFO");

		if(clickAfterWait(menuSettings)) {

			reportStep("successfully clicked on the menu settings in Admin ", "PASS");

		}else {

			reportStep("Not able to clicked on the menu settings in Admin ", "FAIL");
		}


	}

	public void clickOnAffiliateNetworks() {

		reportStep("About to click on the Affilitate Networks  ", "INFO");

		if(jsClickUsingXpath(menuAffliateNetworks)) {

			reportStep("successfully clicked on the Affiliate Networks ", "PASS");

		}else {

			reportStep("Failed - Not able to click on Affiliate Networks ", "FAIL");
		}
		
		if(isElementLocatedByIDPresent("AffiliateNetworks")) {

			reportStep("Validated Affiliate Network breadcrumb element presence", "PASS");

		}else {

			reportStep("Not able to validate presence of Affiliate Network breadcrumb", "FAIL");
		}


	}

	public void editTheAffiliateNetwork(String networkName) {

		reportStep("About to edit the Affliate Network : "+ networkName, "INFO");

		String networkEditLink = "//td[text()='"+networkName.trim()+"']/following-sibling::td[4]/a[1]";

		MobileElement element = driver.findElement(By.xpath(networkEditLink));

		if(clickAfterWait(element)) {

			reportStep("Successfully click on the Edit Button for the network : "+ networkName, "PASS");
		}else {

			reportStep("Failed to click on the Edit Button for the network : "+ networkName, "FAIL");
		}

	}

	public void enterProductCashbackURL(String productCashbackUrl) {

		reportStep("About to Config the Cashback URL  as "+ productCashbackUrl,"INFO");

		if(enterTextInChrome(productURLTextArea, productCashbackUrl)) {

			reportStep("Successfully Entered the ProductCashbackURL in to the Text Area as : "+ productCashbackUrl, "PASS");

		}else {
			reportStep("Failed to Enter the ProductCashbackURL in to the Text Area as : "+ productCashbackUrl, "FAIL");
		}

	}

	public void clickOnSubmitButton() {

		reportStep("About to click on the Edit Network Button  ", "INFO");

		if(clickAfterWait(editNetwork)) {

			reportStep("Successfully clicked on the Edit Network button -  ", "PASS");
		} else {

			reportStep("Failed to click on the EDit Network button", "FAIL");
		}

	}

	public void successMessageValidation() {

		reportStep("About to vaidate the Success Message - Once after editing the Network ", "INFO");
		validateTheElementPresence(netWorkEditSuccessMessage);

	}

	public void clickOnPartnerSettings() {

		reportStep("About to click on the Partner Settings in the admin ", "INFO");

		if(clickAfterWait(menuParterSettings)) {

			reportStep("Successfully clicked on the Partner settings in Admin ", "PASS");

		} else {

			reportStep("Failed - Not able to clicked on the Partner settings in Admin ", "FAIL");
		}
	}

	public void clickOnEditPartner() {

		reportStep("About to click on the edit Partner ", "INFO");

		if(clickAfterWait(editPartner)) {

			reportStep("Successfully clicked on edit Partner ", "PASS");

		} else {

			reportStep("Failed to  click on edit Partner  ", "FAIL");
		}
	}

	public void clickCashbackMenu() {

		reportStep("About to click menu cashback in admin homepage", "INFO");

		//Click csashback menu button
		if(clickAfterWait(menuCashback)) {

			reportStep("Successfully clicked on the menu cashback", "PASS");
		}else {

			reportStep("Not able to click the menu cashback", "FAIL");
		}

	}

	public void clickOnReports() {

		reportStep("About to click on Reports Main menu from the Admin ", "INFO");

		if (clickAfterWait(reports)) {

			reportStep("Successfully clicked on the reports menu from Exit click page ", "PASS");
		}else {

			reportStep("Failed to click on the reports menu from Exit click page ", "FAIL");
		}

	}

	public void clickOnExitClick() {

		reportStep("About to click on the Report's main menu -> Exit click sub menu", "INFO");

		if (clickAfterWait(exitClicks)) {

			reportStep("Successfully clicked on the Exit click sub menu from Exit click page ", "PASS");
		}else {

			reportStep("Failed to click on the reports Exit click sub menu from Exit click page ", "FAIL");
		}


	}

	public void selectSearchByEmailFromExitClickPage() {

		reportStep("About to select the Search by drop down from the exit click page ", "INFO");

		selectValuesFromDropdown(exitClickPagesearchByDropdown, "User Email");
	}

	public void enterSearchKeywordFromExitClickPage(String email) {

		reportStep("About to enter the Search keyword from the exit click page ", "INFO");

		if (enterTextInChrome(exitClickPagesearchKeyword, email)) {

			reportStep("Successfully entered the text : "+ email + " to the Search keyword field ", "PASS");

		}else {

			reportStep("Failed to  enter the text : "+ email + " to the Search keyword field at Admin Exit click page  ", "FAIL");

		}
	}

	public void clickOnSearchButtonFromExitClickPage() {

		reportStep("About to click on the Search button from the exit click page  ", "INFO");

		if (clickAfterWait(exitClickPageSubmitButton)) {

			reportStep("Successfully clicked on the Search button at the Admin Exit click page ", "PASS");

		}else {

			reportStep("Failed to click on the Search button at the Admin Exit click page ", "FAIL");
		}

	}

	public String extractExitClickFromTable() {

		if(waitTillElementPresence(resultTableOfExitID)) {

			reportStep("Exit click results table is present ", "INFO");

		}else {

			reportStep("Exit click results table is not present - Once after clicking on the Search button from the exit click page ", "FAIL");

		}

		reportStep("About to extract the exit click id from the RESULT TABLE ", "INFO");

		String exitClickId = driver.findElementByXPath("((//tr)[3]/td)[1]").getText();

		System.out.println("exit click id is " +  exitClickId );

		reportStep("Successfully got the Exit Click Id As ", exitClickId);

		return exitClickId;


	}

	public void clickOnInteractiveMainMenu() {

		reportStep("About to click on the Interactive Main menu at the Admin page ", "INFO");

		if (clickAfterWait(interActive)) {

			reportStep("Successfully clicked on the Interactive Main menu at the Admin ", "PASS");

		}else {

			reportStep("Failed to  click on the Interactive Main menu at the Admin", "FAIL");

		}

	}

	public void clickOnsubMenuUserTickets() {

		reportStep("About to click on the subMenuUserTickets Main menu at the Admin page ", "INFO");

		if (clickAfterWait(subMenuUserTickets)) {

			reportStep("Successfully clicked on the subMenuUserTickets Main menu at the Admin ", "PASS");

		}else {

			reportStep("Failed to  click on the SUB Menu UserTickets at the Admin", "FAIL");

		}

	}

	public void clickOnSubMenuTestimonials() {

		reportStep("About to click on the subMenu -  Testimonials  ", "INFO");

		if (clickAfterWait(subMenuTestimonials)) {

			reportStep("Successfully clicked on subMenu -  Testimonials ", "PASS");

		}else {

			reportStep("Failed to  click on the subMenu -  Testimonials", "FAIL");

		}

	}

	public void clickUsersMenu() {

		reportStep("About to click menu users in admin homepage", "INFO");

		//Click stores menu button
		if(clickAfterWait(menuUsers)) {

			reportStep("Successfully clicked on the menu users", "PASS");
		}else {

			reportStep("Not able to click the menu users", "FAIL");
		}

	}

	public void clickUsersSubMenu() {

		reportStep("About to click submenu users in admin homepage", "INFO");

		//Click stores menu button
		if(clickAfterWait(subMenuUsers)) {

			reportStep("Successfully clicked on the submenu users", "PASS");
		}else {

			reportStep("Not able to click the submenu users", "FAIL");
		}

	}

	/*It clicks on the Email Master*/
	public void clickOnEmailMaster() {

		reportStep("About to click on the Email master - Main menu ", "INFO");

		if(clickAfterWait(emailMasterMainMenu)) {

			reportStep("Successfully clicked on the Email Master - Main Menu ", "PASS");
		} else {

			reportStep("Failed to click on the Email Master - Main Menu", "FAIL");
		}

	}

	/*It clicks on the Email Master Browser*/
	public void clickOnEmailMasterBrowser() {

		reportStep("About to click on the Email master Browser - Main menu ", "INFO");

		if(clickAfterWait(emailMasterBrowserSubMenu)) {

			reportStep("Successfully clicked on the Email Master Browser - Main Menu ", "PASS");
		} else {

			reportStep("Failed to click on the Email Master  Browser- Main Menu", "FAIL");
		}

	}

	/*It clicks on the Email Master Browser*/
	public void selectEmailFromSearchByDropDown_EmailMaster() {

		reportStep("Select Email from the Search By drop down ", "INFO");
		selectValuesFromDropdown(emailMasterSearchByDropDown, "Email");

	}

	/*Enter User Email to search */
	public void enterUserEmailAsKeyForSearchEmailMaster(String value) {

		reportStep("About to enter the User Email to search the user" + value, "INFO");

		if(enterTextInChrome(searchKey,value)) {
			reportStep("Failed to enter search Key Email  as " + value, "PASS");

		} else {

			reportStep("Failed to enter the search key Email Value as : " + value , "FAIL");

		}




	}

	/*click on Search button*/
	public void clickOnSearchButtonEmailMaster() {

		reportStep("About to click on the Search Button ", "INFO");

		if(clickAfterWait(searchButton)) {

			reportStep("Successfully clicked on the Search Button - Email Master", "PASS");
		} else {

			reportStep("Failed to click on the Search Button ", "FAIL");
		}

	}

}