package pages;


import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Steps;
import utilities.ReadWorkbook; 
import utilities.ReportMessege;
import utilities.common;

public class GroupsPage extends PageObject {

	@Steps(shared=true)  
	private ReadWorkbook readWorkbook;

	@Steps(shared=true) 
	ReportMessege report;

	@Steps(shared=true)
	private common common;

	public String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
	public String newPageTitle = "";

	@FindBy(xpath=".//a[@title='MangoApps Automation Team -- Mango Automation Internal Testing']")
	public WebElementFacade AutomationTestProject;

	@FindBy(xpath=".//*[text()='Pages']")
	public WebElementFacade PagesTab;

	@FindBy(xpath=".//*[@id='page_tools_li']/ul/li[2]")
	public WebElementFacade PageToolsDropDown;

	@FindBy(xpath=".//a[text()='New Page']")
	public WebElementFacade NewPageOption;

	@FindBy(xpath=".//*[@id='site_page_name']")
	public WebElementFacade ShortTitle;

	@FindBy(xpath=".//label[contains(text(), 'Template 3')]/span[contains(text(), 'Text and Images')]")
	public WebElementFacade Template3;

	@FindBy(xpath=".//a[text()='Next']")
	public WebElementFacade NextButton;

	@FindBy(xpath="//*[@id='t_img_div2']/p/img")
	public WebElementFacade AddMedia2;
	//
	@FindBy(xpath=".//a[@title='Upload']")
	public WebElementFacade UploadTab;

	@FindBy(xpath=".//a[text()='Select Files']")
	public WebElementFacade SelectFilesButton;
	//
	@FindBy(xpath=".//span[@class='cke_dialog_ui_button' and text()=\'OK']")
	public WebElementFacade UploadMediaOkButton;

	@FindBy(xpath="(.//a[text()='Save And Publish'])[1]")
	public WebElementFacade SaveandPublishButton;

	@FindBy(xpath=".//a[text()='Edit Current Page']")
	public WebElementFacade EditCurrentPage;

	@FindBy(xpath=".//*[@id='save_publish']")
	public WebElementFacade SaveAndPublishNow;

	@FindBy(xpath=".//*[@id='site_page_name_text']")
	public WebElementFacade NewStaticPageName;

	@FindBy(xpath=".//*[@id='site_page_fullname']")
	public WebElementFacade PageTitle;

	@FindBy(xpath=".//a[@class='copylink_wof']")
	public WebElementFacade GetPageLink;

	@FindBy(xpath=".//span[@name='project_description']")
	public WebElementFacade PinnedGroups;

	@Step
	public void defaultGroupsCheck(String logicalName) throws IOException, InterruptedException, UnsupportedFlavorException {

		String filePath = "\\src\\test\\resources\\TestData\\TestData.xlsx";
		String dataSheet = "Groups";
		Map <String,Map<Integer, String>> tableMap = ReadWorkbook.readRow(logicalName, filePath, dataSheet);		
		readWorkbook.testData(tableMap);
		for (int i = 0  ;i < tableMap.get("Row").size();i++) {		
			String SubURL = tableMap.get("SubURL").get(i);			
			String groupsList = tableMap.get("groupsList").get(i);

			String DomainURL = Serenity.sessionVariableCalled("domainURL").toString();
			DomainURL= DomainURL+SubURL;
			getDriver().get(DomainURL);

			try {

				//ExtentTestManager.getTest().log(Status.INFO, "Clicked on groups tab successfully");
				PinnedGroups.waitUntilVisible();
				List<String> defaultGroups = Arrays.asList(groupsList);
				for (int j = 1; j < 5; j++) {
					String locatorName = "//*[@id='project_description']/div[" + j + "]/a/div/div[2]/p[1]/span/span";
					String groupName = getDriver().findElement(By.xpath(locatorName)).getText();					
					if (defaultGroups.contains(groupName)) {
						report.Info(groupName + " group is present for this domain");
					}
					/*
					 * else {
					 * 
					 * report.Info(groupName + " group is not present for this domain");
					 * Assert.assertTrue(false); }
					 */

				}
			}

			catch (Exception e) {
				report.Info("Error in try block " + e.toString());
			}
		}
	}


	@FindBy(xpath=".//a[@class='uploader_name userName grid-link-width text-truncation notranslate custom_tooltip'][contains(@title,'Everyone')]")
	public WebElementFacade EveryoneGroup;

	@FindBy(xpath=".//a[@class='actionbutton actionred waves-effect waves-light ma-h3']")
	public WebElementFacade GroupToolsButton;

	@FindBy(xpath=".//ul[@class='page_options_menu ma-h4']/li/a[text()='Invite Guest Users']")
	public WebElementFacade InviteGuestUsers;

	@FindBy(xpath=".//textarea[@id='oon_user_list']")
	public WebElementFacade GuestEmailID;

	@FindBy(xpath=".//button[@id='save_selection']")
	public WebElementFacade inviteUserButton;

	@FindBy(xpath="(.//a[text()='Close'])[2]")
	public WebElementFacade InviteCloseButton;

	@Step
	public void addGuestUserToEveryoneCheck(String logicalName) throws InterruptedException, IOException {

		String filePath = "\\src\\test\\resources\\TestData\\TestData.xlsx";
		String dataSheet = "Groups";
		Map <String,Map<Integer, String>> tableMap = ReadWorkbook.readRow(logicalName, filePath, dataSheet);		
		readWorkbook.testData(tableMap);
		for (int i = 0  ;i < tableMap.get("Row").size();i++) {		
			String SubURL = tableMap.get("SubURL").get(i);					
			String DomainURL = Serenity.sessionVariableCalled("domainURL").toString();
			String guestUserEmail = tableMap.get("guestUserEmail").get(i);

			DomainURL= DomainURL+SubURL;
			getDriver().get(DomainURL);
			report.Info("Entered Groups Module");
			common.waitForElement(EveryoneGroup);
			Thread.sleep(3000);
			common.WaitForObjectVisibility(".//a[@class='uploader_name userName grid-link-width text-truncation notranslate custom_tooltip'][contains(@title,'Everyone')]");
			//EveryoneGroup.waitUntilVisible();
			EveryoneGroup.click();
			report.Info("Clicked on Everyone group");

			Thread.sleep(4000);
			common.waitForElement(GroupToolsButton);
			//common.clickElement(GroupToolsButton);
			$(".//a[@class='actionbutton actionred waves-effect waves-light ma-h3']").click();
			Thread.sleep(3000);
			//InviteGuestUsers.click();
			common.clickElement(InviteGuestUsers);
			GuestEmailID.waitUntilVisible();
			GuestEmailID.typeAndTab(guestUserEmail);
			report.Info("Entered user email ID");
			inviteUserButton.click();
			//evaluateJavascript("document.querySelector(\"button[id='save_selection']\").click()");
			common.waitForElement(InviteCloseButton);
			Thread.sleep(5000);	
			common.WaitForObjectVisibility("//div[@id='fancybox-content']/div/div/div[3]/div/a");
			WebElementFacade InviteCloseButton=$("(.//a[text()='Close'])[2]");
			common.clickElement( InviteCloseButton);
			//InviteCloseButton.click();
			report.Info("User Invited");	
			
		}
	}


	@FindBy(xpath=".//a[@class='project-members waves-effect waves-grey waves-ripple']/div[text()='Members']")
	public WebElementFacade MembersTab;

	@Step
	public void verifyUsersInEveryoneCheck(String logicalName) throws IOException, InterruptedException {

		String filePath = "\\src\\test\\resources\\TestData\\TestData.xlsx";
		String dataSheet = "Groups";
		Map <String,Map<Integer, String>> tableMap = ReadWorkbook.readRow(logicalName, filePath, dataSheet);		
		readWorkbook.testData(tableMap);
		for (int i = 0  ;i < tableMap.get("Row").size();i++) {		
			String SubURL = tableMap.get("SubURL").get(i);					
			String DomainURL = Serenity.sessionVariableCalled("domainURL").toString();
			String totalMemberCount= tableMap.get("totalMemberCount").get(i);

			DomainURL= DomainURL+SubURL;
			getDriver().get(DomainURL);			
			report.Info("Clicked on Groups Module");

			common.waitForElement(EveryoneGroup);
			EveryoneGroup.click();
			report.Info("Clicked Everyone group");
			common.waitForElement(MembersTab);
			MembersTab.click(); 
			getDriver().navigate().refresh();
			Thread.sleep(2000);
			report.Info("Clicked on Members tab of this group");			
			WebElementFacade allMembers = find(By.xpath(".//span[@id='pie_options']"));
			common.WaitForObjectVisibility(".//span[@id='pie_options']");
			String allMembersCount = allMembers.getText();
			allMembersCount= allMembersCount.replaceAll("[^0-9]", "");

			if (allMembersCount.contains(totalMemberCount)) {
				report.Info("all Guest users have been added to Everyone group:- "+allMembersCount);				
			} else {
				report.Info("There is Count mismatch in everyone group: Please cross check:- " +allMembersCount);
				Assert.assertTrue(false);

			}
		}
	}
}

