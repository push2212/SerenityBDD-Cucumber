package pages;


import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.webelements.Checkbox;
import utilities.ReadWorkbook;
import utilities.ReportMessege;
import utilities.common;
import pages.DomainPage;

public class UsersPage extends PageObject {

	@Steps(shared=true)  
	private ReadWorkbook readWorkbook;

	@Steps(shared=true) 
	ReportMessege report;

	@Steps(shared=true)
	private common common;

	@Steps(shared=true)
	private DomainPage domainPage;

	public String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
	public String autoITScriptPath = "";

	@FindBy(xpath=".//li[@id='import_wizard_csv']")
	public WebElementFacade ImportUsersTab;

	/*
	 * @FindBy(name="import_file") public WebElementFacade BrowseButton;
	 */

	@FindBy(id="choose_csv_file")
	public WebElementFacade ImportNowButton;
	//
	@FindBy(xpath=".//button[text()='Ok']")
	public WebElementFacade ImportOKButton;

	@Step
	public void importUsersFromCSVfileCheck(String logicalName) throws IOException, InterruptedException {

		/*
		 * getDriver().close(); domainPage.loginCheck("MediaEE");
		 */

		String filePath = "\\src\\test\\resources\\TestData\\TestData.xlsx";
		String dataSheet = "Users";
		Map <String,Map<Integer, String>> tableMap = ReadWorkbook.readRow(logicalName, filePath, dataSheet);		
		readWorkbook.testData(tableMap);
		for (int i = 0  ;i < tableMap.get("Row").size();i++) {		

			String filePathName = System.getProperty("user.dir")+ "\\src\\test\\resources\\TestData\\user_import.csv";			
			String DomainURL = Serenity.sessionVariableCalled("domainURL").toString();
			String SubURL = tableMap.get("SubURL").get(i);

			getDriver().get(DomainURL+SubURL);

			common.waitForElement(ImportUsersTab);
			ImportUsersTab.waitUntilClickable();
			ImportUsersTab.click();
			report.Info("Clicked on Import Tab");
			WebElementFacade BrowseButton = find(By.xpath(".//div[@class='btn actionbutton actionblue waves-effect waves-light static']"));//find(By.name("import_file"));
			common.waitForElement(BrowseButton);
			//common.scrollToView( BrowseButton);
			String csvFilePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\user_import.csv";
			$(".//input[@id='csv_file']").sendKeys(csvFilePath);
			report.Info("Clicked on Browse button");
			//common.clickElement( BrowseButton);
			//Thread.sleep(2000);
			//autoITScriptPath = System.getProperty("user.dir")+"\\AutoIT\\UploadCSV.exe";
			//Runtime.getRuntime().exec(autoITScriptPath);
			Thread.sleep(3000);
			//common.upload( filePathName);
			common.waitForElement(ImportNowButton);
			report.Info("Uploaded CSV");
			//ImportNowButton.wait(1000);
			//Thread.sleep(2000);
			common.waitForElement(ImportNowButton);
			common.clickElement( ImportNowButton);
			report.Info("Clicked on Import Now");
			common.waitForElement(ImportOKButton);
			Thread.sleep(3000);
			ImportOKButton.click();
			report.Info("Clicked on Import Ok button");
		}
	}


	@FindBy(css=".your-avatar-text.notranslate")
	public WebElementFacade YourProfile;

	@FindBy(xpath=".//a[@id='change_profile_photo_link']")
	public WebElementFacade ChangeProfilePhoto;

	@FindBy(xpath=".//span[@id='change_photo_button']")
	public WebElementFacade ChangeProfilePhoto2;

	@FindBy(xpath=".//input[@id='crop_button']")
	public WebElementFacade ApplyProfilePhoto;

	@FindBy(xpath=".//img[@id='ms-user-photo-elem']")
	public WebElementFacade UpdatedProfilePic;

	@FindBy(xpath=".//a[@href='/user/settings/profile?loc=T'][@class='transparent-button']")
	public WebElementFacade EditButton;

	@FindBy(name="expertise")
	public WebElementFacade ExpertiseTextBox;

	@FindBy(id="aka")
	public WebElementFacade AlsoKnownAs;

	@FindBy(name="address_line_1_1")
	public WebElementFacade HomeAddressLine1;

	@FindBy(name="address_line_2_1")
	public WebElementFacade HomeAddressLine2;

	@FindBy(id="city_1")
	public WebElementFacade HomeCity;

	@FindBy(id="select_country_1")
	public WebElementFacade HomeCountry;

	@FindBy(id="zip_1")
	public WebElementFacade HomeZipCode;

	@FindBy(name="address_line_1_2")
	public WebElementFacade OfficeAddressLine1;

	@FindBy(name="address_line_2_2")
	public WebElementFacade OfficeAddressLine2;

	@FindBy(id="city_2")
	public WebElementFacade OfficeCity;

	@FindBy(id="select_country_2")
	public WebElementFacade OfficeCountry;

	@FindBy(id="zip_2")
	public WebElementFacade OfficeZipCode;

	@FindBy(id="add_address")
	public WebElementFacade AddAddress;

	@FindBy(id="address_type_2")
	public WebElementFacade AddressType;

	@FindBy(xpath=".//button[@id='submit_profile_btn_top']/span")
	public WebElementFacade SaveButtonAtTop;


	@Step
	public void changeDPnOtherSectionCheck(String logicalName) throws InterruptedException, IOException {

		String filePath = "\\src\\test\\resources\\TestData\\TestData.xlsx";
		String dataSheet = "Users";
		Map <String,Map<Integer, String>> tableMap = ReadWorkbook.readRow(logicalName, filePath, dataSheet);		
		readWorkbook.testData(tableMap);
		for (int i = 0  ;i < tableMap.get("Row").size();i++) {		

			String profilePicPath = tableMap.get("profilePicPath").get(i);
			profilePicPath = System.getProperty("user.dir")+profilePicPath;
			String homeCity = tableMap.get("homeCity").get(i);
			String homeCountry = tableMap.get("homeCountry").get(i);
			String homeZipcode = tableMap.get("homeZipcode").get(i);
			String DomainURL = Serenity.sessionVariableCalled("domainURL").toString();
			String SubURL = tableMap.get("SubURL").get(i);
			getDriver().get(DomainURL+SubURL);

			//YourProfile.click();
			Thread.sleep(2000);
			common.WaitForObjectPresence(".//img[@id='ms-user-photo-elem']");
			common.scrollToView( UpdatedProfilePic);
			common.clickElement( UpdatedProfilePic);
			Thread.sleep(1000);
			//UpdatedProfilePic.click();
			report.Info("Clicked on Change Photo Option");
			common.waitForElement(ChangeProfilePhoto2);
			ChangeProfilePhoto2.click();
			report.Info("Clicked on Change Photo Button");
			Thread.sleep(2000);
			//upload(profilePicPath).to(UpdatedProfilePic);
			//common.upload( profilePicPath);
			autoITScriptPath = System.getProperty("user.dir")+"\\AutoIT\\UploadDP.exe";
			Runtime.getRuntime().exec(autoITScriptPath);
			Thread.sleep(2000);
			common.waitForElement(ApplyProfilePhoto);
			Thread.sleep(2000);
			ApplyProfilePhoto.click();
			report.Info("Clicked on Apply Changes Button");
			common.WaitForObjectPresence(".//img[@id='ms-user-photo-elem']");
			UpdatedProfilePic.shouldBeVisible();
			report.Info("Successfully changed the DP of the user");
			//EditButton.click();
			common.clickElement( EditButton);
			report.Info( "Successfully clicked on edit button");
			if (logicalName.equalsIgnoreCase("AKA")) {
				//common.scrollToView( ExpertiseTextBox);
				AlsoKnownAs.type("Automation Guy @ " + dateName);				
				report.Info( "Entered text to <also known as> field");
			} else if (logicalName.equalsIgnoreCase("Address")) {
				common.scrollToPageEnd();
				HomeAddressLine1.type("This is home address line 1");
				HomeAddressLine2.type("This is home address line 2");
				HomeCity.type(homeCity);
				HomeCountry.selectByValue(homeCountry);
				HomeZipCode.selectByValue(homeZipcode);

				// to add Office Address

				if (OfficeAddressLine1.isCurrentlyVisible()) {
					AddAddress.click();
				}
				common.waitForElement(AddressType);				
				AddressType.selectByVisibleText("Work");

				common.scrollToPageEnd();
				OfficeAddressLine1.type("This is office address line 1");
				OfficeAddressLine2.type("This is office address line 2");
				OfficeCity.type(homeCity);
				OfficeCountry.selectByValue(homeCountry);
				OfficeZipCode.selectByValue(homeZipcode);

			}		
			common.waitForElement(SaveButtonAtTop);
			SaveButtonAtTop.click();
			report.Info("Clicked on save profile data");
			common.waitForElement(EditButton);
			EditButton.shouldBeVisible();
		}

	}

	/*
	 * @Step public void suspendUserCheck(String logicalName) {
	 * report.Info("Deactivated user Successfully"); userOptions(logicalName);
	 * Thread.sleep(500); logoutCheck(); driver.navigate().refresh();
	 * Thread.sleep(1000); loginCheck(secondUserID, password); Thread.sleep(1500);
	 * driver.navigate().refresh(); loginCheck(); userToolsActions("Activate",
	 * "networkUser"); report.Info("Activated User Successfully");
	 * userOptions(driver, "goToUserPortal"); Thread.sleep(500); logoutCheck();
	 * driver.navigate().refresh(); Thread.sleep(1000); loginCheck(secondUserID,
	 * password); Thread.sleep(1000);
	 * report.Info("Successfully activated the user back"); logoutCheck();
	 * Thread.sleep(500); driver.navigate().refresh(); loginCheck(); }
	 */


	@FindBy(xpath=".//select[@id='u_status']")
	public WebElementFacade UserStatusDropdown;

	@FindBy(xpath=".//input[@id='emailID']")
	public WebElementFacade newUserEmail;

	@FindBy(xpath=".//select[@id='u_role']")
	public WebElementFacade RoleDropdown;

	@FindBy(xpath=".//button[@id='clear_user_search']/following-sibling::button/span/span")
	public WebElementFacade userSearchButton;

	@Step
	public void userToolsActions(String logicalName) throws IOException, InterruptedException {

		String filePath = "\\src\\test\\resources\\TestData\\TestData.xlsx";
		String dataSheet = "Users";
		Map <String,Map<Integer, String>> tableMap = ReadWorkbook.readRow(logicalName, filePath, dataSheet);		
		readWorkbook.testData(tableMap);
		for (int i = 0  ;i < tableMap.get("Row").size();i++) {		

			String userType = tableMap.get("userType").get(i);
			String userEmail = tableMap.get("userEmail").get(i);
			String guestUserEmail = tableMap.get("guestUserEmail").get(i);
			String userAction = tableMap.get("userAction").get(i);
			String DomainURL = Serenity.sessionVariableCalled("domainURL").toString();
			String SubURL = tableMap.get("SubURL").get(i);

			getDriver().get(DomainURL+SubURL);			
			report.Info("Entered into User Module of Admin");
			common.waitForElement(UserStatusDropdown);
			UserStatusDropdown.waitUntilClickable();
			UserStatusDropdown.selectByVisibleText("All");
			report.Info("Selected the user status to All");

			if (userType.equalsIgnoreCase("Network")) {
				newUserEmail.sendKeys(userEmail);
			} else if (userType.equalsIgnoreCase("guestUser")) {
				newUserEmail.sendKeys(guestUserEmail);
				RoleDropdown.selectByVisibleText("Guest Users Only");
				report.Info("Selected the User Role to Guest User only");
			}
			report.Info("Entered the user email ID");
			evaluateJavascript("document.querySelector(\"button[type='submit']\").click()");

			//WebElement ablink = (WebElement) evaluateJavascript("return document.querySelector(\"a[href='/abtest']\")");

			//ablink.click();
			//userSearchButton.click();
			report.Info("Clicked on Search Button");
			Thread.sleep(2000);
			WebElementFacade searchedUser = find(By.xpath(".//span[@title='"+userEmail+"']"));
			common.WaitForObjectPresence(".//span[@title='"+userEmail+"']");
			if(logicalName.contentEquals("ActivateUser")){
				WebElementFacade userCheckbox = find(By.xpath(".//tbody[@id='user_info_rows']/tr[1]/td[1]/label"));	
				common.scrollToView( searchedUser);
				//userCheckbox.click();
				//common.clickElement( userCheckbox);
				/*
				 * Checkbox uCheckbox = new Checkbox(userCheckbox); uCheckbox.setChecked(true);
				 */

			}
			Thread.sleep(1000);
			report.Info("clicked on check box");
			WebElementFacade userToolsButton = find(By.xpath(".//span[@class='tools_setting_icon']"));
			common.scrollToView( userToolsButton);
			common.clickElement( userToolsButton);
			Thread.sleep(1000);
			report.Info("Clicked on User Tools dropDown");
			if (userAction.equalsIgnoreCase("Deactivate") || userAction.equalsIgnoreCase("Activate")
					|| userAction.equalsIgnoreCase("deleteUser") || userAction.equalsIgnoreCase("makeAdmin")
					|| userAction.equalsIgnoreCase("removeAdmin")) {
				if (userAction.equalsIgnoreCase("Deactivate")) {
					WebElementFacade deactivateUser = find(By.xpath(".//a[@id='suspend']"));
					deactivateUser.click();
					report.Info("Deactivated the selected User");
				} else if (userAction.equalsIgnoreCase("Activate")) {
					WebElementFacade activateUser = find(By.xpath(".//a[@id='activate']"));
					activateUser.click();					
					report.Info("Activated the selected User");
				} else if (userAction.equalsIgnoreCase("deleteUser")) {
					WebElementFacade deleteUser = find(By.xpath(".//a[@id='delete_ma_user']"));
					deleteUser.click();
					report.Info("Deleted the selected User");
				} else if (userAction.equalsIgnoreCase("makeAdmin")) {
					WebElementFacade makeAdministrator = find(By.xpath(".//a[@id='make_admin']"));
					common.scrollToView( makeAdministrator);
					makeAdministrator.click();				
					Thread.sleep(1000);					
					report.Info("Converted the selected user to admin");
				} else if (userAction.equalsIgnoreCase("removeAdmin")) {
					WebElementFacade removeUserAdmin = find(By.xpath(".//a[@id='remove_admin']"));
					common.scrollToView( removeUserAdmin);
					removeUserAdmin.click();					
					report.Info("Removed the admin access to selected user");
				}
				WebElementFacade UserToolsYesButton = find(By.xpath(".//button[text()='Yes']"));
				common.WaitForObjectVisibility(".//button[text()='Yes']");
				UserToolsYesButton.click();
				report.Info("Clicked on Yes Button");
				WebElementFacade UserToolsOKButton = find(By.xpath(".//button[text()='Ok']"));
				common.WaitForObjectVisibility(".//button[text()='Ok']");
				UserToolsOKButton.click();			
				report.Info("Clicked on Ok Button");
			} else if (userAction.equalsIgnoreCase("editProfile")) {
				WebElementFacade EditProfile = find(By.xpath(".//a[@id='edit_user_profile']"));
				common.WaitForObjectPresence(".//button[text()='Ok']");
				EditProfile.click();				
				report.Info("Clicked on Edit User Profile");
			} else {
				report.Info("Please provide valid action for User Tools");
				Assert.assertTrue(false);
			}
		}

	}

	@Step
	public void userOptions(String logicalName) throws InterruptedException, IOException {

		// Need to use only text link to find element here
		// desiredDropdownAction= goToUserPortal, gamificationSection, viewMyProfile,
		// manageDomain, signOut, signOutAllOther
		String filePath = "\\src\\test\\resources\\TestData\\TestData.xlsx";
		String dataSheet = "Users";
		Map <String,Map<Integer, String>> tableMap = ReadWorkbook.readRow(logicalName, filePath, dataSheet);		
		readWorkbook.testData(tableMap);
		for (int i = 0  ;i < tableMap.get("Row").size();i++) {		

			String desiredDropdownAction = tableMap.get("desiredDropdownAction").get(i);

			WebElementFacade userSettings = find(By.xpath(".//a[@class='ohidden user-setting-link jr-ten notranslate fal fa-cog']"));			
			userSettings.click();
			report.Info("Clicked on User Settings dropdown");
			Thread.sleep(1000);
			if(desiredDropdownAction.equalsIgnoreCase("Go To User Portal")){
				WebElementFacade GoToUserPortal = find(By.xpath("(.//ul[@class='hover-dropdown right-0-position']/li/a/i)[1]"));
				//common.mouseHover( GoToUserPortal);
				GoToUserPortal.click();				
			}else if(desiredDropdownAction.equalsIgnoreCase("logout")){
				WebElementFacade SignOut = find(By.xpath(".//a[@class='user_sign_out sign_out']"));
				//common.mouseHover( GoToUserPortal);
				SignOut.click();						
			}
			report.Info("Clicked on " + desiredDropdownAction);
		}

	}


	@FindBy(xpath=".//input[@id='global_search_box']") 
	public WebElementFacade  GlobalSearchBox;

	@Step
	public void userSearchCheck(String logicalName) throws IOException, InterruptedException {

		Actions myAct = new Actions(getDriver());
		// This test case needs to have the mentioned User in its network before running
		String filePath = "\\src\\test\\resources\\TestData\\TestData.xlsx";
		String dataSheet = "Users";
		Map <String,Map<Integer, String>> tableMap = ReadWorkbook.readRow(logicalName, filePath, dataSheet);		
		readWorkbook.testData(tableMap);
		for (int i = 0  ;i < tableMap.get("Row").size();i++) {		

			String userToSearch = tableMap.get("userToSearch").get(i);		
			String DomainURL = Serenity.sessionVariableCalled("domainURL").toString();
			String SubURL = tableMap.get("SubURL").get(i);
			getDriver().get(DomainURL+SubURL);		

			GlobalSearchBox.waitUntilVisible();
			//myAct.sendKeys(Keys.chord(Keys.SHIFT + "S")).build().perform();
			GlobalSearchBox.click();
			GlobalSearchBox.sendKeys(userToSearch);			
			report.Info("Entered the User name in the search Box");
			WebElementFacade userSearchAllPeople = find(By.xpath(".//ul/li[@class='title notranslate ui-menu-item']/a/span/span[contains(text(),'Robo')]"));
			//.//span[text()='All People']/following::a"
			//Thread.sleep(2000);
			waitFor(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//ul/li[@class='title notranslate ui-menu-item']/a/span/span[contains(text(),'Robo')]")));			
			userSearchAllPeople.click();
			//userSearchAllPeople.sendKeys(Keys.DOWN);		
			report.Info("Selected the desired search result for given User");

			WebElementFacade SearchedUser = find(By.xpath(".//ul/li[text()='"+userToSearch+"']"));
			SearchedUser.shouldBeVisible();
			report.Info("User search is successful through Global search Box");				

		}
	}

}
