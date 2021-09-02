package pages;


import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.internet.AddressException;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.By.ByCssSelector;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import static org.hamcrest.Matchers.is;
import io.restassured.RestAssured;
import io.restassured.RestAssured.*;
import io.restassured.response.Response;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Steps;
import utilities.ReadWorkbook; 
import utilities.ReportMessege;
import utilities.common;
import utilities.emailUtility;

public class ProjectPage extends PageObject {

	@Steps(shared=true)  
	private ReadWorkbook readWorkbook;

	@Steps(shared=true) 
	ReportMessege report;

	@Steps(shared=true)
	private common common;

	@Steps(shared=true)
	private DomainPage domainPage;

	@Steps(shared=true)
	private emailUtility emailutility;



	public String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
	public String newPageTitle = "";
	public String expectedTemplate = "";
	public String projectEmailAddress = "";
	public String projectURL="";
	public String textFileCheckInPath="";
	public String textFileNextVersionPath="";
	public String profilePicPath="";
	public String autoITScriptPath ="";


	@FindBy(xpath=".//a[@title='MangoApps Automation Team -- Mango Automation Internal Testing']")
	public WebElementFacade AutomationTestProject;

	@FindBy(xpath=".//*[text()='Pages']")
	public WebElementFacade PagesTab;

	@FindBy(xpath="(.//*[@id='page_tools_li']/ul/li)[2]")
	public WebElementFacade PageToolsDropDown;

	@FindBy(xpath=".//a[text()='New Page']")
	public WebElementFacade NewPageOption;

	@FindBy(id="site_page_name")
	public WebElementFacade ShortTitle;

	@FindBy(xpath=".//label[contains(text(), 'Template 3')]/span[contains(text(), 'Text and Images')]")
	public WebElementFacade StaticTemplate3;


	@FindBy(xpath=".//label[contains(text(), 'Template 3')]/span[contains(text(), 'Flexi-Layout & Widget Gallery')]")
	public WebElementFacade DynamicTemplate3;

	@FindBy(xpath=".//a[text()='Next']")
	public WebElementFacade NextButton;

	@FindBy(xpath="//*[@id='t_img_div2']/p/img")
	public WebElementFacade AddMedia2;
	//
	@FindBy(xpath=".//a[contains(@id,'cke_Upload_')]")
	public WebElementFacade UploadTab;

	@FindBy(xpath=".//a[text()='Select Files']")
	public WebElementFacade SelectFilesButton;
	//
	@FindBy(xpath=".//span[@class='cke_dialog_ui_button' and text()='OK']")
	public WebElementFacade UploadMediaOkButton;

	@FindBy(xpath=".//span[@class='show_on_last']/a[text()='Save And Publish']")
	public WebElementFacade SaveandPublishButton;

	@FindBy(xpath=".//a[text()='Edit Current Page']")
	public WebElementFacade EditCurrentPage;

	@FindBy(id="save_publish")
	public WebElementFacade SaveAndPublishNow;

	@FindBy(id="site_page_name_text")
	public WebElementFacade NewStaticPageName;

	@FindBy(id="site_page_fullname")
	public WebElementFacade PageTitle;

	@FindBy(xpath=".//a[@class='copylink_wof']")
	public WebElementFacade GetPageLink;

	@FindBy(xpath=".//li[@id='dynamic_template_page']/a[contains(text(),'Dynamic')]")
	public WebElementFacade DynamicTab;


	@Step
	public void createPage(String logicalName) throws IOException, InterruptedException, UnsupportedFlavorException {

		String filePath = "\\src\\test\\resources\\TestData\\TestData.xlsx";
		String dataSheet = "Project";
		Map <String,Map<Integer, String>> tableMap = ReadWorkbook.readRow(logicalName, filePath, dataSheet);		

		for (int i = 0  ;i < tableMap.get("Row").size();i++) {		

			String pageName = tableMap.get("pageName").get(i);
			String mediaPath = tableMap.get("MediaPath").get(i);
			String DomainURL = Serenity.sessionVariableCalled("domainURL").toString();
			String SubURL = tableMap.get("SubURL").get(i);
			getDriver().get(DomainURL+SubURL);
			String newPageTitle = "";

			Thread.sleep(2000);
			AutomationTestProject.shouldBePresent();
			AutomationTestProject.click();
			common.waitForElement(PagesTab);
			PagesTab.click();
			//common.waitForElement(PageToolsDropDown);				
			//evaluateJavascript("document.querySelector(\"a[title='Project Tools']\").click()");
			common.WaitForObjectVisibility(".//a[@class='view_count_reader refresh-count']");
			Thread.sleep(2000);	
			PageToolsDropDown.click();
			Thread.sleep(3000);		
			NewPageOption.waitUntilClickable();
			NewPageOption.click();

			ShortTitle.waitUntilEnabled();
			ShortTitle.sendKeys(pageName + dateName);
			if(logicalName.contains("staticPagewithMedia")){
				report.Info("Creating Static page");
				Thread.sleep(2000);
				common.waitForElement(StaticTemplate3);
				//StaticTemplate3.click();
				common.clickElement( StaticTemplate3);
				report.Info("Selected Template for Static Page");
				common.waitForElement(NextButton);
				common.clickElement( NextButton);
				//NextButton.click();
				common.waitForElement(AddMedia2);
				AddMedia2.click();
				common.waitForElement(UploadTab);
				UploadTab.waitUntilEnabled();
				common.clickElement( UploadTab);
				//UploadTab.click();
				SelectFilesButton.click();
				autoITScriptPath = System.getProperty("user.dir")+"\\AutoIT\\UploadNewVersion.exe";
				Runtime.getRuntime().exec(autoITScriptPath);
				Thread.sleep(4000);			
				UploadMediaOkButton.click();
				report.Info("Uploaded Media to static Page");
				newPageTitle = "New Static Page Test";
			}else if(logicalName.contains("DynamicPage")){
				report.Info("Creating Dynamic page");
				DynamicTab.click();
				report.Info("Clicked Dynamic Tab");
				common.waitForElement(DynamicTemplate3);
				Thread.sleep(1000);
				DynamicTemplate3.click();
				report.Info("Selected Template for Dynamic Page");
				common.waitForElement(NextButton);
				NextButton.click();
				newPageTitle = "New Dynamic Page Test";
			}			
			common.waitForElement(NextButton);
			Thread.sleep(2000);
			common.clickElement( NextButton);
			//NextButton.click();
			//find(By.xpath(".//div[@id='page_create_footer']/div/div[@class='nextbtn next_step button_tracker_save  ma-h2']")).click();
			Thread.sleep(2000);
			common.waitForElement(SaveandPublishButton);
			SaveandPublishButton.click();
			report.Info("clicked Save and Publish");
			common.waitForElement(PageToolsDropDown);
			//PageToolsDropDown.click();
			common.clickElement( PageToolsDropDown);
			Thread.sleep(2000);
			WebElementFacade EditCurrentPage=find(By.xpath(".//a[text()='Edit Current Page']"));
			common.waitForElement(EditCurrentPage);	 
			common.scrollToView(EditCurrentPage);
			common.mouseHover(EditCurrentPage);
			common.clickElement( EditCurrentPage);
			//EditCurrentPage.click();
			report.Info("clicked Edit Current Page");
			common.waitForElement(PageTitle);	
			common.WaitForObjectVisibility(".//*[@id='site_page_fullname']");
			newPageTitle = newPageTitle+ dateName;
			Serenity.setSessionVariable("NewTitle").to(newPageTitle);
			System.out.println(newPageTitle);
			PageTitle.typeAndTab(newPageTitle);
			report.Info("Entered new page title");
			//NextButton.click();
			common.clickElement( $(".//a[text()='Next']"));
			Thread.sleep(1000);
			NextButton.click();
			Thread.sleep(1000);
			SaveandPublishButton.click();
			report.Info("clicked Save and Publish");
			common.waitForElement(SaveAndPublishNow);
			SaveAndPublishNow.click();
			report.Info("clicked Save and Publish Now");
		}
	}
	@Step
	public void checkChangedTitle() throws UnsupportedFlavorException, IOException, InterruptedException {

		common.WaitForObjectVisibility(".//*[@id='site_page_name_text']");
		String newTitle = Serenity.sessionVariableCalled("NewTitle");
		if (NewStaticPageName.containsText(newTitle)){
			Assert.assertTrue(true);
			report.Info("Page Title Successfully Changedto :- " +newTitle);
		}else {
			report.Info("Page Title is:- " +newTitle);
			Assert.assertTrue(false);
		}
		PageToolsDropDown.click();
		Thread.sleep(2000);
		GetPageLink.click();
		Serenity.setSessionVariable("pageURL").to(common.onPaste());//Sets session variable pageURL to use all over the tests		 
		report.Info("Page URL is: " + common.onPaste());
	}

	@FindBy(id="teamName")
	public WebElementFacade NameTextBox;

	@FindBy(xpath=".//a[text()='Save & Continue']")
	public WebElementFacade SaveAndContinue;

	@FindBy(xpath=".//a[contains(text(),'Save & Exit')]")
	public WebElementFacade SaveAndExit;
	//
	@FindBy(xpath=".//div[@class='nav-label'][text()='Wikis']")
	public WebElementFacade WikisTab;

	@FindBy(xpath=".//a[text()='New Wiki']")
	public WebElementFacade NewWikiButton;

	@FindBy(xpath=".//span[text()='Create']")
	public WebElementFacade CreateNewWikiButton;

	@FindBy(xpath=".//input[@id='wiki_title']")
	public WebElementFacade NweWikiTitle;
	//
	@FindBy(xpath=".//*[@id='wkTmpltsCntnr']/li[2]/div")
	public WebElementFacade WikiTemplate;

	@FindBy(xpath=".//button[@id='wiki_save_button'][@class='actionbutton waves-effect waves-light smallbutton actionblue wiki_save_button actionmore']")
	public WebElementFacade WikiSavePublishButton;
	//
	@FindBy(xpath=".//a[text()='NewTitleWiki']")
	public WebElementFacade VerifyNewTitle;

	@FindBy(xpath=".//div[text()='Files']")
	public WebElementFacade FilesTab;

	@FindBy(xpath=".//span[@class='tools_setting_icon'][text()='New']")
	public WebElementFacade NewFileButton;

	@FindBy(xpath=".//a[@class='create-new upload_new_file']")
	public WebElementFacade UploadNewFiles;

	@FindBy(xpath=".//*[@id='fileupload']")
	public WebElementFacade AddFilesButton;

	@FindBy(xpath=".//button[@id='file_upload_btn']")
	public WebElementFacade AddFileDoneButton;

	@FindBy(xpath=".//div[text()='Posts']")
	public WebElementFacade PostsTab;

	@FindBy(xpath=".//span[contains(text(),'Post Tools')]")
	public WebElementFacade PostTools;

	@FindBy(xpath=".//a[text()='New Post']")
	public WebElementFacade NewPost;
	//
	@FindBy(xpath=".//div[@title='Newsletter Template 2']")
	public WebElementFacade Template2;
	//
	@FindBy(xpath=".//*[@id='compose_post_submit']/span/span")
	public WebElementFacade ContinueButton;

	@FindBy(xpath=".//input[@value='NEWSLETTER TEMPLATE 2']")
	public WebElementFacade SelectedTemplate;

	@FindBy(xpath=".//input[@id='post_title']")
	public WebElementFacade PostTitleTextBox;

	@FindBy(xpath=".//span[text()='Publish Post']")
	public WebElementFacade PublishPostButton;

	@FindBy(xpath=".//button[text()='Yes']")
	public WebElementFacade YesConfirmButton;



	@Step
	public void createProjectTemplateCheck(String logicalName) throws IOException, InterruptedException {

		String filePath = "\\src\\test\\resources\\TestData\\TestData.xlsx";
		String dataSheet = "Project";
		Map <String,Map<Integer, String>> tableMap = ReadWorkbook.readRow(logicalName, filePath, dataSheet);		
		readWorkbook.testData(tableMap);
		for (int i = 0  ;i < tableMap.get("Row").size();i++) {		

			String pageName = tableMap.get("pageName").get(i);
			String projectTemplateName = tableMap.get("projectTemplateName").get(i);
			projectTemplateName = projectTemplateName + dateName;
			Serenity.setSessionVariable("createdTemplate").to(projectTemplateName);
			String expectedProjectTemplateXpath =".//span[contains(text(), '"+projectTemplateName+"')]";
			String wikiTitle = tableMap.get("wikiTitle").get(i);
			String FilePath = tableMap.get("FilePath").get(i);
			FilePath = System.getProperty("user.dir")+FilePath;
			String postTitle = tableMap.get("postTitle").get(i);
			String DomainURL = Serenity.sessionVariableCalled("domainURL").toString();
			String SubURL = tableMap.get("SubURL").get(i);
			getDriver().get(DomainURL+SubURL);

			common.waitForElement(NameTextBox);			
			NameTextBox.typeAndTab(projectTemplateName);
			common.scrollToView(SaveAndContinue);
			SaveAndContinue.waitUntilEnabled();
			SaveAndContinue.click();
			common.waitForElement(SaveAndExit);
			SaveAndExit.click();

			if (find(By.xpath(expectedProjectTemplateXpath)).isDisplayed()) {	
				WikisTab.click();
				/*
				 * mouseHover(driver, template); Thread.sleep(1500); clickElement(driver,
				 * template);
				 */
				common.WaitForObjectVisibility(".//a[text()='New Wiki']");
				NewWikiButton.click();
				//+++++++++Wikicreation+++++++++++++++++++++++++++++++++++++
				common.waitForElement(CreateNewWikiButton);					
				wikiTitle=wikiTitle + System.currentTimeMillis();
				NweWikiTitle.sendKeys(wikiTitle);
				report.Info("Entered the Title of the Wiki as: " + wikiTitle);
				/*
				 * try { WebElement wikiToTeam = findElement(driver, "id", "wikiToTeam");
				 * wikiToTeam.sendKeys(prop.getProp("wikiToTeam")); Thread.sleep(3000);
				 * wikiToTeam.sendKeys(Keys.ENTER); ExtentTestManager.getTest().log(Status.PASS, >>>>>>//Expected functionality not found
				 * "Entered the Team Name to send Wiki To"); } catch (Exception e) {
				 * ExtentTestManager.getTest().log(Status.INFO, "Team Option is not available "
				 * + e.toString()); } Thread.sleep(500);
				 */

				/*
				 * clickElement(driver, findElement(driver, "xpath", "wikiTemplateSelect"));
				 * ExtentTestManager.getTest().log(Status.PASS,
				 * "Selected Desire Template for Wiki"); Thread.sleep(1500);
				 */
				common.clickElement( WikiTemplate);
				common.clickElement( CreateNewWikiButton);					
				String newWikiTitlexpath = ".//input[@value='"+wikiTitle+"'][@data-title='"+wikiTitle+"']";
				WebElementFacade newWikiTitle=find(By.xpath(newWikiTitlexpath));
				common.waitForElement(newWikiTitle);
				common.clickElement( WikiSavePublishButton);	
				report.Info("Checking now if newly added template is visible");
				//common.waitFor(VerifyNewTitle);
				String VerifyNewTitle = ".//a[text()='"+wikiTitle+"']";
				WebElementFacade newtitle = find(By.xpath(VerifyNewTitle));
				common.WaitForObjectVisibility( ".//a[text()='"+wikiTitle+"']");
				find(By.xpath(VerifyNewTitle)).shouldBeVisible();

				report.Info("newly added template is visible");

				//+++++WikcreationEnd++++++++++++++++++++++++
				FilesTab.click();

				common.WaitForObjectVisibility(".//ul[@id='files-ul']/div");
				//List<WebElementFacade> fileList = findAll(By.xpath(".//ul[@id='files-ul']/div/li"));
				//int fileListSize = fileList.size();

				common.waitForElement(NewFileButton);

				/*
				 * WebElement notify = driver.findElement(By.xpath("//*[@id=\"notice\"]/a"));
				 * try { notify.click(); ExtentTestManager.getTest().log(Status.INFO,
				 * "Closed the Notification bar"); } catch (Exception e) {
				 * System.out.println(e.getMessage()); }
				 */

				NewFileButton.click();
				UploadNewFiles.click();
				Thread.sleep(3000);
				//common.waitForElement(AddFilesButton);
				WebElement AddFiles =  getDriver().findElement(By.id("fileupload"));
				common.scrollToView(AddFiles);
				//common.clickElement( AddFiles);	
				Thread.sleep(1000);
				upload(FilePath).to(AddFiles);
				//common.upload(getDriver(), FilePath);
				common.waitForElement(AddFileDoneButton);
				AddFileDoneButton.click();
				//String addedFileVerifyxpath = ".//a[@title='sampleTest.txt'][@class='file_detail_link ma-file-tooltip']";
				//find(By.xpath(addedFileVerifyxpath)
				addedFileVerifyxpath.shouldBeVisible();

				common.waitForElement(PostsTab);
				common.clickElement( PostsTab);
				//PostsTab.click();
				common.waitForElement(PostTools);
				PostTools.click();
				NewPost.click();
				Template2.click();
				ContinueButton.click();
				PostTitleTextBox.waitUntilEnabled();
				postTitle=postTitle+ System.currentTimeMillis();
				String NewlyAddedPost = ".//header[@title='"+postTitle+"']";
				PostTitleTextBox.type(postTitle);
				//PublishPostButton.click();
				common.clickElement( PublishPostButton);
				common.WaitForObjectVisibility(".//button[text()='Yes']");
				common.waitForElement(YesConfirmButton);
				YesConfirmButton.waitUntilVisible();
				YesConfirmButton.click();
				common.WaitForObjectVisibility(NewlyAddedPost);
				find(By.xpath(NewlyAddedPost)).shouldBeVisible();


			} else {
				Assert.assertTrue(false);

			}
		}
	}
	/*
	 * @Step public void verifyCreatedTemplate(String logicalName) throws
	 * IOException, InterruptedException { String filePath =
	 * "\\src\\test\\resources\\TestData\\TestData.xlsx"; String dataSheet =
	 * "Project"; Map <String,Map<Integer, String>> tableMap =
	 * ReadWorkbook.readRow(logicalName, filePath, dataSheet);
	 * readWorkbook.testData(tableMap); for (int i = 0 ;i <
	 * tableMap.get("Row").size();i++) {
	 * 
	 * String projectTemplateName = tableMap.get("projectTemplateName").get(i);
	 * projectTemplateName = projectTemplateName + dateName;
	 * 
	 * 
	 * 
	 * 
	 * } }
	 */

	@FindBy(xpath=".//div[@class='button']/a[contains(@class,'actionbutton waves-effect waves-dark ')][contains(@title,'Create')]")
	public WebElementFacade CreateProjectButton;

	@FindBy(xpath=".//select[@name='template_id']")
	public WebElementFacade PickATemplate;

	@FindBy(xpath=".//textarea[@id='description']")
	public WebElementFacade ShortDescription;

	@FindBy(xpath=".//*[@id='public_chk']/parent::span/parent::li")
	public WebElementFacade PublicPermission;

	@FindBy(xpath=".//*[@id='private_chk']/parent::span/parent::li")
	public WebElementFacade PrivatePermission;

	@FindBy(xpath=".//*[@id='default_member_role1']/parent::span")
	public WebElementFacade MemberRole;

	@FindBy(xpath=".//*[@id='default_member_role2']/parent::span")
	public WebElementFacade AdminRole;

	@FindBy(xpath=".//*[contains(@id,'secret_chk')]/parent::span/parent::li")
	public WebElementFacade UnlistedPersmission;

	@FindBy(xpath=".//select[@id='project_time_zone']")
	public WebElementFacade ProjectTimeZone;

	@FindBy(xpath=".//*[@id='enable_overview']/div[2]/div/div")
	public WebElementFacade ProjectPageModule;

	@FindBy(xpath="//*[@id='profileLeftSection']/ul[2]/li/a")
	public WebElementFacade ProjectToolsDropDown;

	@FindBy(xpath=".//a[@id='project-email']")
	public WebElementFacade GetShareLinkOrEmail;

	@FindBy(xpath=".//a[text()='Copy Email Address']")
	public WebElementFacade CopyEmailAddress;

	@FindBy(xpath=".//a[text()='Copy URL']")
	public WebElementFacade CopyProjectURL;

	@FindBy(xpath=".//span[@class='loading_text '][text()='Close']")
	public WebElementFacade CloseButton;

	@Step
	public void projectCreationFromTemplate(String logicalName) throws IOException, InterruptedException, UnsupportedFlavorException {

		String filePath = "\\src\\test\\resources\\TestData\\TestData.xlsx";
		String dataSheet = "Project";
		Map <String,Map<Integer, String>> tableMap = ReadWorkbook.readRow(logicalName, filePath, dataSheet);		
		readWorkbook.testData(tableMap);
		for (int i = 0  ;i < tableMap.get("Row").size();i++) {		

			String expectedProjectName = tableMap.get("expectedProjectName").get(i);
			String projectTemplateName = tableMap.get("projectTemplateName").get(i);
			//projectTemplateName = projectTemplateName + dateName;
			String permissionLevel = tableMap.get("PermissionLevel").get(i);
			String defaultMembership = tableMap.get("defaultMembership").get(i);
			String preferredProjectTimeZone = tableMap.get("PreferredProjectTimeZone").get(i);
			String DomainURL = Serenity.sessionVariableCalled("domainURL").toString();
			String SubURL = tableMap.get("SubURL").get(i);
			getDriver().get(DomainURL+SubURL);

			CreateProjectButton.shouldBePresent();
			Thread.sleep(3000);
			report.Info("Entered into Project/Group Tab module Successfully");
			List<WebElement> teamCount = getDriver().findElements(By.xpath(".//div[contains(@id, 'teamBox')]"));
			int rowCount = teamCount.size();
			List<String> projectList = new ArrayList<String>();
			for (int j = 1; j <= rowCount; j++) {
				String projectLocatorName ="(.//div[@class='userDetails relative']/div/p/a)[" + j + "]";
				//".//*[@id='project_description']/div[" + j + "]/a/div/div[2]/p[1]/span/span";
				projectList.add(getDriver().findElement(By.xpath(projectLocatorName)).getText());
			}
			expectedTemplate = Serenity.sessionVariableCalled("createdTemplate");
			expectedProjectName= expectedProjectName+dateName;
			if (!projectList.contains(expectedProjectName)) {
				CreateProjectButton.click();
				report.Info("Clicked on Create Project Button");
				NameTextBox.waitUntilVisible();
				NameTextBox.click();
				NameTextBox.sendKeys(expectedProjectName);
				report.Info( "Entered Project name as " + expectedProjectName);
				if(!projectTemplateName.equals("")) {
					PickATemplate.selectByVisibleText(expectedTemplate);
				}
				ShortDescription.sendKeys("This Project Is Created By Automation");
				report.Info("Entered the short Description for the project");

				if (permissionLevel.equalsIgnoreCase("Public")) {
					PublicPermission.click();
					report.Info( "Selected the Access Permissions to be Public");
				} else if (permissionLevel.equalsIgnoreCase("Private")) {
					PrivatePermission.click();
					report.Info( "Selected the Access Permissions to be Private");
				} else if (permissionLevel.equalsIgnoreCase("Unlisted")) {
					UnlistedPersmission.click();
					report.Info( "Selected the Access Permissions to be Secret");
				} else {
					report.Info("Please provide valid Permission Type ,default permission , Public ,is selected");
				}

				if (defaultMembership.equalsIgnoreCase("Member")) {
					MemberRole.click();			
					report.Info("Default membership of each member will be as a Member");
				} else if (defaultMembership.equalsIgnoreCase("Admin")) {
					AdminRole.click();	
					report.Info( "Default membership of each member will be as an Admin");
				} else {
					report.Info("Please provide valid value for default Membership");
				}
				if(!preferredProjectTimeZone.equals("")) {
					ProjectTimeZone.selectByValue(preferredProjectTimeZone);			
					report.Info("Selected the Time Zone as per " + preferredProjectTimeZone);
				}
				SaveAndContinue.click();
				report.Info( "Clicked on Save& Continue");	


				// >>>>>>>>>>>Need to enhance code to give option to user to select modules to be enabled>>>>>>>>>>>>>>
				//common.waitForElement(ProjectPageModule);
				common.WaitForObjectVisibility(".//*[@id='enable_overview']/div[2]/div/div");
				Thread.sleep(3000);
				common.scrollToView(ProjectPageModule);

				if (ProjectPageModule.getText().equalsIgnoreCase("Pages")) {
					getDriver().findElement(By.xpath("//*[@id='enable_overview']/div[3]/div/label")).click();
					report.Info("Enabled pages module");
				}
				
				if(Serenity.sessionVariableCalled("domain").equals("CABL")) {
					common.waitForElement(SaveAndContinue);
					SaveAndContinue.click();
					report.Info( "Clicked on Save& Continue");	
				}
				common.waitForElement(SaveAndExit);
				SaveAndExit.click();
				report.Info("Clicked on Save& Exit");

				/*	if (findElement(driver, "linkText", "SaveNExit").isDisplayed()) {
					findElement(driver, "linkText", "SaveNExit").click();
					ExtentTestManager.getTest().log(Status.PASS, "Clicked on Save& Exit");
					Thread.sleep(1000);
				}*/ /*else if (findElement(driver, "xpath", "downArrow").isDisplayed()) {
					findElement(driver, "xpath", "downArrow").click();
					Thread.sleep(1000);
					WebElement finalSave = findElement(driver, "xpath", "saveNGoToProject");
					mouseHover(driver, finalSave);
					finalSave.click();
					ExtentTestManager.getTest().log(Status.PASS, "Clicked on Save& Exit");
					Thread.sleep(1000);
				}*/

				WebElementFacade actualProjectName = find(By.xpath(".//li[@class='opp-brdc-menu visible']//span[contains(text(), '"+ expectedProjectName + "')]"));					
				if (actualProjectName.getText().equalsIgnoreCase(expectedProjectName)) {
					report.Info(expectedProjectName + " Project/Group is created Successfully");					
					actualProjectName.click();
					//common.WaitForObjectVisibility(".//div[@id='feed_page_loader'][@style='display: none;']");
					common.waitForElement(ProjectToolsDropDown);
					//ProjectToolsDropDown.click();
					//common.clickElement( ProjectToolsDropDown);
					ProjectToolsDropDown.waitUntilEnabled();
					$(".//div[@id='profileLeftSection']/ul[2]/li/a").click();
					Thread.sleep(2000);
					common.waitForElement(GetShareLinkOrEmail);					
					GetShareLinkOrEmail.click();
					Thread.sleep(1000);
					if(!logicalName.contains("CreatesGroup")){
						common.waitForElement(CopyEmailAddress);	
						CopyEmailAddress.click();
						projectEmailAddress = common.onPaste();
						Serenity.setSessionVariable("projectEmailAddress").to(projectEmailAddress);
						Thread.sleep(1000);
						report.Info("mail address of the project is: " + projectEmailAddress);
					}
					CopyProjectURL.click();
					projectURL = common.onPaste();
					Serenity.setSessionVariable("projectURL").to(projectURL);
					Thread.sleep(500);
					report.Info("URL of the project is: " + projectURL);
					CloseButton.click();
				} else {
					report.Info("Project got created with a different Name: Please Check");
				}
			} else {
				report.Info("There is an existing Project with the same name: Please give Unique Name");
				Assert.assertTrue(false);
			}

		}
	}


	@FindBy(xpath=".//span[contains(text(),'sampleTest')]")
	public WebElementFacade addedFileVerifyxpath;

	@Step
	public void uploadFuntionality(String logicalName) throws InterruptedException, IOException {

		//domainPage.reLoginCheck("MediaEE");

		String filePath = "\\src\\test\\resources\\TestData\\TestData.xlsx";
		String dataSheet = "Project";
		Map <String,Map<Integer, String>> tableMap = ReadWorkbook.readRow(logicalName, filePath, dataSheet);		
		readWorkbook.testData(tableMap);
		for (int i = 0  ;i < tableMap.get("Row").size();i++) {		

			//String expectedProjectName = tableMap.get("expectedProjectName").get(i);		
			String FilePath = tableMap.get("FilePath").get(i);
			FilePath = System.getProperty("user.dir")+FilePath;
			report.Info(FilePath);
			String DomainURL = Serenity.sessionVariableCalled("domainURL").toString();
			String SubURL = tableMap.get("SubURL").get(i);
			getDriver().get(DomainURL+SubURL);

			WebElementFacade AutomationProject = find(By.xpath(".//a[@title='MangoApps Automation Team -- Mango Automation Internal Testing']"));
			common.waitForElement(AutomationProject);
			//find(By.xpath(".//span[text()='Automation Test']")).waitUntilVisible();
			report.Info("Entered into Project Module");
			find(By.xpath(".//a[@title='MangoApps Automation Team -- Mango Automation Internal Testing']")).click();
			report.Info("Entered into Desired Project");
			common.waitForElement(FilesTab);
			FilesTab.click();
			report.Info("Successfully clicked on Project Files Tab");
			common.WaitForObjectVisibility(".//ul[@id='files-ul']/div");
			common.waitForElement(NewFileButton);
			common.clickElement( NewFileButton);
			//NewFileButton.click();
			report.Info("Clicked on New file Drop Down");
			//Thread.sleep(1000);
			common.WaitForObjectVisibility("(.//a[@title='Attachments'])[2]");
			common.mouseHover(UploadNewFiles);
			UploadNewFiles.click();
			report.Info("Moved on to Upload New File and clicked on it");
			Thread.sleep(3000);
			WebElement AddFiles =  getDriver().findElement(By.id("fileupload"));
			common.scrollToView(AddFiles);
			//	common.clickElement( AddFiles);				
			//Thread.sleep(2000);
			upload(FilePath).to(AddFiles);
			//common.upload(getDriver(), FilePath);
			Thread.sleep(1000);
			common.waitForElement(AddFileDoneButton);
			common.scrollToView(AddFileDoneButton);
			AddFileDoneButton.click();		

		} 
	}

	@Step
	public void documentConversionCheck() throws InterruptedException {
		Thread.sleep(2000);
		common.waitForElement(addedFileVerifyxpath);
		addedFileVerifyxpath.shouldBeVisible();	
		report.Info("Text file successfully uploaded");	
		Thread.sleep(2000);
		common.scrollToView(addedFileVerifyxpath);;
		addedFileVerifyxpath.click();
		Thread.sleep(2000);
		report.Info("Opening the file that Just got uploaded");
		//common.waitForElement(DocCloseButton);
		//DocCloseButton.click();
		getDriver().navigate().refresh();
		//common.clickElement( DocCloseButton);
		report.Info("Closing Preview screen");
		common.WaitForObjectVisibility(".//span[contains(text(),'sampleTest')]");
		addedFileVerifyxpath.click();
		WebElementFacade textverify = find(By.xpath(".//div[contains(text(), 'this is to test')]"));
		common.WaitForObjectVisibility(".//div[contains(text(), 'this is to test')]");
		WebElementFacade textFromFile = find(By.xpath(".//*[@id='pageContainer1']/div[2]/div"));
		if (textFromFile.getText().equalsIgnoreCase("this is to test automation flow")) {
			report.Info("Uploaded document Name is: " + NewlyAddedFile.getText());
			report.Info("Inner text of the document is: " + NewlyAddedFileContent.getText());
			report.Info("Text Document conversion is Successfull");			
		} else {
			report.Info("Either the document was opened lately/it didn't open preview at all/Inner text is not matching: Please check");
			Assert.assertTrue(false);
		}
	}


	@FindBy(xpath=".//li[@id='file_tools']/a/span")
	public WebElementFacade fileAdvancedTools;

	@FindBy(xpath=".//*[@class='upload_new_version']")
	public WebElementFacade uploadNewVersion;

	@FindBy(xpath=".//a[@id='file_upload']/input[@id='fileupload']")
	public WebElementFacade ChooseFile;

	@FindBy(xpath=".//span[text()='Do not notify']")
	public WebElementFacade DoNotNotifyButton;

	@FindBy(xpath="//*[@id='close']/a")
	public WebElementFacade DocCloseButton;

	@FindBy(xpath=".//*[@class='texttruncate']/span")
	public WebElementFacade NewlyAddedFile;

	@FindBy(xpath=".//*[@id='pageContainer1']/div[2]/div")
	public WebElementFacade NewlyAddedFileContent;

	/*
	 * @FindBy(xpath=".//span[contains(text(),'sampleTestV2')]") public WebElementFacade
	 * V2File;
	 */

	public WebElementFacade textverify = find(By.xpath(".//div[contains(text(), 'this is to test')]"));

	@Step
	public void fileUploadNewVersionCheck(String logicalName) throws InterruptedException, IOException {

		String filePath = "\\src\\test\\resources\\TestData\\TestData.xlsx";
		String dataSheet = "Project";
		Map <String,Map<Integer, String>> tableMap = ReadWorkbook.readRow(logicalName, filePath, dataSheet);		
		readWorkbook.testData(tableMap);
		for (int i = 0  ;i < tableMap.get("Row").size();i++) {		
			//String expectedProjectName = tableMap.get("expectedProjectName").get(i);		
			String FilePath = tableMap.get("FilePath").get(i);
			FilePath = System.getProperty("user.dir")+FilePath;

			common.waitForElement(fileAdvancedTools);
			fileAdvancedTools.waitUntilVisible();
			fileAdvancedTools.click();
			report.Info("Clicked on Advanced Tools drop down");
			common.waitForElement(uploadNewVersion);
			common.mouseHover(uploadNewVersion);
			uploadNewVersion.click();
			//Thread.sleep(10000);
			report.Info("Selected Upload New Version Option");
			common.WaitForObjectVisibility(".//a[@id='file_upload']/input[@id='fileupload']");
			common.waitForElement(ChooseFile);

			//Thread.sleep(3000);
			//common.clickElement( $(".//html/body/div[5]/msbody/div[7]/div/div[9]/div/div[2]/form/div[1]/div[1]/div[1]/div[3]/a"));
			//	common.mouseHover(ChooseFile);
			//$(".//html/body/div[5]/msbody/div[7]/div/div[9]/div/div[2]/form/div[1]/div[1]/div[1]/div[3]/a").click();
			//$(".//a[@id='file_upload']").click();
			//Thread.sleep(2000);
			//evaluateJavascript("document.querySelector(\"input[class='fileuploadInput hand']\").click()");
			//ChooseFile.click();
			//common.clickElement( $(".//html/body/div[5]/msbody/div[7]/div/div[9]/div/div[2]/form/div[1]/div[1]/div[1]/div[3]/a"));
			report.Info("Clicked on Choose files button");
			textFileNextVersionPath = System.getProperty("user.dir") +"\\src\\test\\resources\\TestData\\sampleTestV2.txt";
			$(".//input[@id='fileupload']").sendKeys(textFileNextVersionPath);
			//Thread.sleep(20000);
			//WebElementFacade chooseFile = find(By.xpath(".//*[@id='file_upload']"));
			//upload(textFileNextVersionPath).to($(".//html /body/div[5]/msbody/div[7]/div/div[9]/div/div[2]/form/div[1]/div[1]/div[1]/div[3]/a"));
			//common.upload(getDriver(), textFileNextVersionPath);

			//autoITScriptPath = System.getProperty("user.dir")+"\\AutoIT\\UploadNewVersion.exe";
			//Runtime.getRuntime().exec(autoITScriptPath);
			Thread.sleep(4000);
			report.Info("Uploaded new version of the text file");
			getDriver().navigate().refresh();
			Thread.sleep(3000);
			//common.waitForElement(DoNotNotifyButton);
			//DoNotNotifyButton.waitUntilVisible();
			//DoNotNotifyButton.click();
			//	DocCloseButton.waitUntilClickable();
			//common.clickElement( DocCloseButton);	
			//DocCloseButton.click();


			WebElementFacade V2File =find(By.xpath(".//span[contains(text(),'sampleTestV2')]"));
			common.WaitForObjectVisibility(".//span[contains(text(),'sampleTestV2')]");
			//common.waitForElement(V2File);
			//common.scrollToView(V2File);	
			//Thread.sleep(2000);DELETED
			common.clickElement( V2File);
			Thread.sleep(3000);
			//V2File.click();	
			report.Info("Clicked on the New version of the file");
			getDriver().navigate().refresh();
			//Thread.sleep(3000); DELETED
			//common.waitForElement(DocCloseButton);
			//common.clickElement( DocCloseButton);	
			report.Info("Closed the Preview window");
			//Thread.sleep(10000);
			report.Info("after thread");
			common.WaitForObjectVisibility(".//span[contains(text(),'sampleTestV2')]");
			//common.waitForElement(V2File);
			//common.scrollToView(V2File);
			common.clickElement( find(By.xpath(".//span[contains(text(),'sampleTestV2')]")));
			report.Info("after reclick");
			//common.retryingClick(V2File);
			//V2File.click();	
			//Thread.sleep(6000);
			common.WaitForObjectVisibility(".//div[contains(text(), 'this is to test')]");
			common.waitForElement(textverify);

			Thread.sleep(2000);
			report.Info("Inner text of the document is: " + NewlyAddedFileContent.getText());
			String newlyAddedText = NewlyAddedFileContent.getText();

			if (newlyAddedText.equalsIgnoreCase("this is to test file next version upload flow")) {
				report.Info("Uploaded document Name is: " + NewlyAddedFile.getText());

				report.Info("Text Document conversion is Successfull");
			}
			else {
				report.Info("Either the document was opened lately or it didn't open the proper text preview at all: Please check");
				Assert.assertTrue(false);
			}
		}
	}


	@FindBy(xpath=".//a[@title='Checkout this file']")
	public WebElementFacade CheckOutFile;

	@FindBy(xpath=".//a[@title='check-in your changes with new version of this file']")
	public WebElementFacade CheckInFile;


	@Step
	public void FileCheckOutNCheckInCheck() throws InterruptedException, IOException {

		fileAdvancedTools.waitUntilVisible();
		common.waitForElement(fileAdvancedTools);
		$(".//li[@id='file_tools']/a/span").click();
		//fileAdvancedTools.click();
		report.Info("Clicked on Advanced Tools drop down");
		common.waitForElement(CheckOutFile);
		common.mouseHover(CheckOutFile);
		CheckOutFile.click();
		report.Info("Clicked on Check Out Option");
		Thread.sleep(3000);
		common.mouseHover(fileAdvancedTools);
		fileAdvancedTools.click();
		common.WaitForObjectVisibility(".//a[@title='check-in your changes with new version of this file']");
		Thread.sleep(1000);
		common.mouseHover(CheckInFile);
		CheckInFile.click();
		report.Info("Clicked on Check In option");	
		common.WaitForObjectVisibility(".//a[@id='file_upload']/input[@id='fileupload']");
		common.waitForElement(ChooseFile);

		//	common.scrollToView(ChooseFile);
		//Thread.sleep(10000);
		//common.clickElement( $(".//html/body/div[5]/msbody/div[7]/div/div[9]/div/div[2]/form/div[1]/div[1]/div[1]/div[3]/a"));
		//evaluateJavascript("document.querySelector(\"input[class='fileuploadInput hand']\").click()");

		//ChooseFile.click();
		//Thread.sleep(2000);
		//	$(".//html/body/div[5]/msbody/div[7]/div/div[9]/div/div[2]/form/div[1]/div[1]/div[1]/div[3]/a").click();
		//Thread.sleep(3000);
		report.Info("Clicked on Choose Files Button");
		textFileCheckInPath = System.getProperty("user.dir") +"\\src\\test\\resources\\TestData\\sampleTestV3.txt";	
		//$(".//html/body/div[5]/msbody/div[7]/div/div[9]/div/div[2]/form/div[1]/div[1]/div[1]/div[3]/a").sendKeys(textFileCheckInPath);
		//WebElementFacade chooseFile = find(By.xpath(".//*[@id='file_upload']"));
		//upload(textFileCheckInPath).to(chooseFile);
		//common.upload(getDriver(), textFileCheckInPath);
		//autoITScriptPath = System.getProperty("user.dir")+"\\AutoIT\\UploadNewVersion3.exe";
		//Runtime.getRuntime().exec(autoITScriptPath);
		$(".//input[@id='fileupload']").sendKeys(textFileCheckInPath);
		//Thread.sleep(6000);
		common.waitForElement(DoNotNotifyButton);
		common.WaitForObjectVisibility(".//span[text()='Do not notify']");
		common.clickElement( DoNotNotifyButton);
		//DoNotNotifyButton.click();
		common.waitForElement(DocCloseButton);

		WebElementFacade DocCloseButton = find(By.xpath(".//*[@id='close']/a"));
		common.clickElement( DocCloseButton);
		//DocCloseButton.click();
		//getDriver().navigate().refresh();
		//Thread.sleep(4000);
		report.Info("Closed the Preview window");
		Thread.sleep(1000);
		common.WaitForObjectVisibility(".//span[contains(text(),'sampleTestV3')]");
		find(By.xpath(".//span[contains(text(),'sampleTestV3')]")).click();
		//Thread.sleep(4000);
		report.Info("Clicked on the Checked In version of the file");
		/*
		 * getDriver().navigate().refresh(); Thread.sleep(4000);
		 * common.WaitForObjectVisibility(".//span[contains(text(),'sampleTestV3')]");
		 * find(By.xpath(".//span[contains(text(),'sampleTestV3')]")).click();
		 * report.Info("ReClicked on the Checked In version of the file");
		 */
		getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		common.WaitForObjectVisibility(".//*[@id='pageContainer1']/div[2]/div");				
		report.Info("Inner text of the document is: " + NewlyAddedFileContent.getText());
		String newlyAddedText = NewlyAddedFileContent.getText();		

		if (newlyAddedText.equalsIgnoreCase("this is to test file Check In functionality")) {
			report.Info("Uploaded document Name is: " + newlyAddedText);

			report.Info("Text Document conversion is Successfull");
		}
		else {
			report.Info("Either the document was opened lately or it didn't open the proper text preview at all: Please check");
			Assert.assertTrue(false);
		}
		getDriver().navigate().refresh();
		//DocCloseButton.click();
		/*
		 * common.clickElement( DocCloseButton);
		 * report.Info("Closed the Preview window");
		 */
	}

	@FindBy(xpath=".//a[@title='Tasks']")
	public WebElementFacade TasksTab;


	@FindBy(xpath=".//span[contains(text(),'Task Tools')]")
	public WebElementFacade TaskTools;

	@FindBy(xpath=".//textarea[@id='task_name']")
	public WebElementFacade AddTaskName;

	@Step
	public void createReccurenceTaskCheck(String logicalName) throws IOException, InterruptedException {

		String filePath = "\\src\\test\\resources\\TestData\\TestData.xlsx";
		String dataSheet = "Project";
		Map <String,Map<Integer, String>> tableMap = ReadWorkbook.readRow(logicalName, filePath, dataSheet);		
		readWorkbook.testData(tableMap);
		for (int i = 0  ;i < tableMap.get("Row").size();i++) {			

			String DomainURL = Serenity.sessionVariableCalled("domainURL").toString();
			String SubURL = tableMap.get("SubURL").get(i);
			getDriver().get(DomainURL+SubURL);
			report.Info("Entered into Project Module");

			AutomationTestProject.click();
			report.Info("Entered into Desired Project");
			common.waitForElement(TasksTab);
			TasksTab.click();
			report.Info("Clicked on Task Tab of Projects");

			common.waitForElement(TaskTools);
			TaskTools.click();
			report.Info("Clicked on task tools");
			Thread.sleep(2000);
			WebElement addNewTask = find(By.xpath(".//span[text()='Add New Task ']"));
			//common.mouseHover(addNewTask);
			common.clickElement( addNewTask);
			//addNewTask.click();
			report.Info("Clicked on add new task");

			common.WaitForObjectVisibility(".//textarea[@id='task_name']");
			String taskName = "Automation Task" + dateName;
			AddTaskName.sendKeys(taskName);
			report.Info("Entered the new task name as " + taskName);
			WebElementFacade recurrinTaskToggleButton=find(By.xpath(".//label[@for='task_is_recurring']"));
			recurrinTaskToggleButton.click();
			report.Info("Clicked on reccurance Toggle Button to ON it");			
			WebElementFacade recurringTask=find(By.xpath(".//a[@id='task_recurrence_link']"));
			recurringTask.click();
			report.Info("Expanded the fields of Recurrance Task");			
			WebElementFacade everyDays=find(By.xpath(".//input[@id='recurrence_daily_recur_every_x']"));
			everyDays.sendKeys("1");
			report.Info("Entered number of recurrances Needed");
			recurringTask.click();
			report.Info("Minimized the fields of Recurrance Task");
			Thread.sleep(1000);
			WebElementFacade taskSaveButton=find(By.xpath(".//button[@class='actionbutton waves-effect waves-dark ma-primary waves-effect waves-dark save_btn']"));
			taskSaveButton.click();
			report.Info("Clicked on Save Option: Task Created");

			WebElementFacade createdTask=find(By.xpath(".//span/span[text()='"+taskName+"']"));
			common.WaitForObjectVisibility(".//span/span[text()='"+taskName+"']");
			createdTask.click();
			common.WaitForObjectVisibility(".//div/div[@id='view_task_name']");
			WebElementFacade taskDetailsAtRight=find(By.xpath(".//div/div[@id='view_task_name']"));

			if(!taskDetailsAtRight.isDisplayed()){
				createdTask.click();
				common.WaitForObjectVisibility(".//div/div[@id='view_task_name']");
			}
			taskDetailsAtRight.shouldBeVisible();
			WebElementFacade nextReccurance=find(By.xpath(".//*[@id='taskDetails']/div/table/tbody/tr[24]/td"));
			common.scrollToView(nextReccurance);
			report.Info(nextReccurance.getText());
			if (nextReccurance.getText().contains(common.getTommorrowsDate())) {
				report.Info("Verified the Recurrance Date Successfully and its matching with tomorrow's Date");					
			}	
		}
	}

	@Step
	public void videoUploadNConversionCheck() throws InterruptedException {	

		WebElementFacade addedVideoFile = find(By.xpath(".//span[contains(text(),'SampleVideo')]"));
		common.waitForElement(addedVideoFile);
		Thread.sleep(2000);
		common.scrollToView(find(By.xpath(".//span[contains(text(),'SampleVideo')]")));
		//common.clickElement( addedVideoFile);
		find(By.xpath(".//span[contains(text(),'SampleVideo')]")).click();		
		report.Info("Opening the Video file that just got uploaded");

		common.WaitForObjectVisibility(".//*[@id='mediaspace_preview']/div/div/video");		
		WebElementFacade videoPreview = find(By.xpath(".//*[@id='mediaspace_preview']/div/div/video"));
		Thread.sleep(6000);
		common.WaitForObjectVisibility(".//*[@id='mediaspace_preview']/div/div/video");
		videoPreview.shouldBePresent();
		report.Info("Uploaded Video Name is: " + videoPreview.getText());
		report.Info("Video Conversion is Successful");
		WebElementFacade videoCloseButton = find(By.xpath(".//a[@data-dismiss='modal']"));
		videoCloseButton.click();
		report.Info("Closed the Preview window");	
	}


	@Step
	public void makeAFolderPublicCheck(String logicalName) throws InterruptedException, IOException {

		Actions myAct = new Actions(getDriver());
		String filePath = "\\src\\test\\resources\\TestData\\TestData.xlsx";
		String dataSheet = "Project";
		Map <String,Map<Integer, String>> tableMap = ReadWorkbook.readRow(logicalName, filePath, dataSheet);		
		readWorkbook.testData(tableMap);
		for (int i = 0  ;i < tableMap.get("Row").size();i++) {			
			String FilePath = tableMap.get("FilePath").get(i);
			FilePath = System.getProperty("user.dir") +FilePath;
			String DomainURL = Serenity.sessionVariableCalled("domainURL").toString();
			String SubURL = tableMap.get("SubURL").get(i);
			getDriver().get(DomainURL+SubURL);			
			report.Info("Entered into Project Module");

			//WebElementFacade AutomationProject = find(By.xpath(".//span[text()='Automation Test']"));
			common.waitForElement(AutomationTestProject);			
			report.Info("Entered into Project Module");
			$(".//a[@title='MangoApps Automation Team -- Mango Automation Internal Testing']").click();
			report.Info("Entered into Desired Project");
			common.waitForElement(FilesTab);
			FilesTab.click();
			report.Info("Successfully clicked on Project Files Tab");
			common.WaitForObjectVisibility(".//ul[@id='files-ul']/div");
			common.waitForElement(NewFileButton);
			NewFileButton.click();
			report.Info("Clicked on New file Drop Down");
			Thread.sleep(1000);
			WebElement createNewSubFolder = find(By.cssSelector(".add-folder"));
			common.mouseHover(createNewSubFolder);
			common.clickElement( createNewSubFolder);
			//createNewSubFolder.click();
			report.Info("Clicked on make a new sub folder");

			WebElementFacade folderNameInputBox = find(By.xpath(".//input[@id='folder_name']"));

			common.waitForElement(folderNameInputBox);
			String folderName = "Automation Test Public Folder" + dateName;
			folderNameInputBox.type(folderName);
			WebElementFacade folderCreationDoneButton = find(By.xpath(".//span[contains(text(),'Done')]"));
			folderCreationDoneButton.click();

			WebElementFacade addedNewFolder = find(By.xpath(".//span/a[@title='"+folderName+"']"));
			common.waitForElement(addedNewFolder);
			addedNewFolder.click();
			Thread.sleep(3000);
			common.WaitForObjectVisibility(".//ul[@id='files-ul']/div");
			//WebElementFacade newProjectFileButton = find(By.xpath(".//*[@id='filter-container']/div/div[1]/ul/li/a/span"));
			NewFileButton.click();
			report.Info("Clicked on file upload option from Drop Down");
			Thread.sleep(1000);
			common.mouseHover(UploadNewFiles);
			UploadNewFiles.click();
			report.Info("Moved on to Upload New File and clicked on it");

			/*
			 * WebElement notify = driver.findElement(By.xpath("//*[@id=\"notice\"]/a"));
			 * try { notify.click(); report.Info("Closed the Notification bar"); } catch
			 * (Exception e) { System.out.println(e.getMessage()); }
			 */

			Thread.sleep(3000);
			//common.waitForElement(AddFilesButton);
			WebElement AddFiles =  getDriver().findElement(By.id("fileupload"));
			common.scrollToView(AddFiles);
			//common.clickElement( AddFiles);	
			report.Info(" Clicked Add Files");
			Thread.sleep(1000);
			upload(FilePath).to(AddFiles);
			//common.upload(getDriver(), FilePath);
			common.waitForElement(AddFileDoneButton);
			AddFileDoneButton.click();
			report.Info(" Clicked on Done");

			WebElementFacade leftSideProjectName = find(By.xpath(".//div[@id='left_splitter']/div/ul/li[1]/a[text()='MangoApps Automation Team']"));
			Thread.sleep(2000);
			common.waitForElement(leftSideProjectName);
			leftSideProjectName.click();
			report.Info(" Clicked Project Name on Left");
			WebElementFacade folderOnLeft = find(By.xpath(".//div[@id='left_splitter']/div/ul/li/ul/li[@label='"+folderName+"']"));			

			myAct.contextClick(folderOnLeft).build().perform();
			report.Info("Right Clicked on Folder");

			WebElementFacade shareFolder = find(By.xpath(".//a[@class='share_folder']"));
			common.mouseHover(shareFolder);
			shareFolder.click();
			report.Info("Selected Share Folder Option");

			WebElementFacade withPublic = find(By.cssSelector(".bloc.public_link_opt.relative"));
			common.waitForElement(withPublic);
			withPublic.click();
			report.Info("Selected Share Folder Public Option");
			WebElement MakePublicButton = find(By.xpath(".//span[text()='Make Public']"));
			common.waitForElement(MakePublicButton);
			MakePublicButton.click();
			report.Info("Clicked on make Folder Public Option");

			WebElementFacade FolderLink = find(By.xpath(".//input[@id='input_copy_link']"));
			common.waitForElement(FolderLink);			
			String FileURL = FolderLink.getAttribute("value");
			report.Info("Copied the folder link " +FileURL+ " into a String");
			WebElementFacade sharePermissionsCloseButton = find(By.xpath(".//button[@name='cancel_selection']"));
			sharePermissionsCloseButton.click();
			report.Info("Closed the share permissions Window");

			/*
			 * openNewTab(driver); ArrayList<String> tabs = new
			 * ArrayList<String>(driver.getWindowHandles());
			 * driver.switchTo().window(tabs.get(1)); driver.get(folderLink);
			 */
			getDriver().get(FileURL);
			WebElementFacade FileFromPublicFolder = find(By.xpath(".//span[text()='sampleTest.txt']"));
			common.WaitForObjectVisibility(".//span[text()='sampleTest.txt']");
			FileFromPublicFolder.shouldBePresent();		
			report.Info("Folder has been made public and able to view its files from outside");	
		}
	}



	@FindBy(xpath=".//a[@href='/ce/pulse/user/teams/project_teams/projects']")
	public WebElementFacade ProjectsModule;

	@FindBy(xpath=".//a[@class='project-members waves-effect waves-grey waves-ripple']/div[text()='Members']")
	public WebElementFacade MembersTab;


	@FindBy(xpath=".//a[@id='invite_colleagues']")
	public WebElementFacade InviteMembersButton;

	@Step
	public void addNewMembersToTeamCheck(String logicalName) throws InterruptedException, IOException {

		String filePath = "\\src\\test\\resources\\TestData\\TestData.xlsx";
		String dataSheet = "Project";
		Map <String,Map<Integer, String>> tableMap = ReadWorkbook.readRow(logicalName, filePath, dataSheet);		
		readWorkbook.testData(tableMap);
		for (int i = 0  ;i < tableMap.get("Row").size();i++) {			
			String FilePath = tableMap.get("FilePath").get(i);
			FilePath = System.getProperty("user.dir") +FilePath;
			String DomainURL = Serenity.sessionVariableCalled("domainURL").toString();
			String SubURL = tableMap.get("SubURL").get(i);
			getDriver().get(DomainURL+SubURL);
			//ProjectsModule.click();
			report.Info("Entered into Project Module");

			common.waitForElement(AutomationTestProject);
			AutomationTestProject.click();
			report.Info("Entered into Desired Project");
			Thread.sleep(2000);
			common.waitForElement(MembersTab);
			MembersTab.click();
			report.Info("Successfully clicked on Project Members Tab");

			common.waitForElement(InviteMembersButton);
			InviteMembersButton.click();
			report.Info("Clicked on Invite members");

			WebElementFacade searchUsers = find(By.cssSelector(".ma_data_search_input"));
			searchUsers.click();
			searchUsers.sendKeys("Last Name");			
			Thread.sleep(2000);
			searchUsers.sendKeys(Keys.ENTER);
			Thread.sleep(1000);

			WebElementFacade userLastNameLabel = find(By.xpath(".//input[@placeholder='Enter Last Name']"));	
			userLastNameLabel.type("robo");

			WebElementFacade applyUserSelection = find(By.xpath(".//button[text()='Apply']"));
			common.waitForElement(applyUserSelection);
			applyUserSelection.click();

			WebElementFacade searchedUser = find(By.xpath(".//*[@id='searchedUserList'] //span[1]"));
			common.waitForElement(searchedUser);
			Thread.sleep(3000);
			$(".//*[@id='searchedUserList'] //span[1]").click();
			Thread.sleep(2000);
			WebElementFacade addSelectedUser = find(By.xpath(".//button[@id='addSelectedItem']"));
			common.waitForElement(addSelectedUser);
			$(".//button[@id='addSelectedItem']").click();						

			WebElementFacade InviteButton = find(By.xpath(".//a[@class='actionbutton actionblue user-selection-invite-members']"));
			common.waitForElement(InviteButton);
			common.clickElement( InviteButton);
			//InviteButton.click();
			Thread.sleep(2000);
			report.Info("Successfully verified the new user added to team");
		}
	}

	@Step
	public void newFileCreationFromOffice365Check() throws InterruptedException {

		getDriver().navigate().refresh();
		Thread.sleep(2000);
		String DomainURL = Serenity.sessionVariableCalled("domainURL").toString();
		getDriver().get(DomainURL+"/user/project");
		report.Info("Entered into Project Module");
		common.waitForElement(AutomationTestProject);	
		AutomationTestProject.click();
		report.Info("Entered into Desired Project");

		common.waitForElement(FilesTab);
		//Thread.sleep(3000);
		FilesTab.click();
		report.Info("Successfully clicked on Project Files Tab");
		common.WaitForObjectVisibility(".//ul[@id='files-ul']/div");
		common.waitForElement(NewFileButton);
		NewFileButton.click();
		report.Info("Clicked on New file Drop Down");
		Thread.sleep(2000);
		WebElementFacade newWordDocumentLink= $(".//a[@class='create_file']/i[@class='far fa-file-upload wd_doc']");	
		common.clickElement( newWordDocumentLink);
		//newWordDocumentLink.click();
		report.Info("Clicked on create a new word document");
		WebElementFacade newDocName= $(".//input[@id='new_filename']");
		common.waitForElement(newDocName);
		String documentName = "TestWordDoc " + dateName;
		newDocName.sendKeys(documentName);
		report.Info("Entered the document name");
		WebElementFacade documentDoneButton= $(".//span[text()='Done']");
		documentDoneButton.click();
		report.Info("Clicked on Done button");
		Thread.sleep(2000);
		ArrayList<String> tabs = new ArrayList<String>(getDriver().getWindowHandles());
		getDriver().switchTo().window(tabs.get(1));
		report.Info("Driver switched to the new tab");
		//Thread.sleep(25000);


		/*WebElementFacade signInWithWorkOrSchool= $(".//a[@id='b_signInOrgId']");
		//common.waitForElement(signInWithWorkOrSchool);
		common.WaitForObjectVisibility(".//a[@id='b_signInOrgId']");
		signInWithWorkOrSchool.click();
		report.Info("Clicked on sign in");

		WebElementFacade msEmailInputBox= $(".//input[@name='loginfmt']");
		common.waitForElement(msEmailInputBox);
//		msEmailInputBox.sendKeys("firstuser@mangoappsindia.onmicrosoft.com");
		report.Info("Entered user Email");

		WebElementFacade NextButton= $(".//input[@id='idSIButton9'][@value='Next']");
		//common.waitForElement(NextButton);
		//NextButton.click();
		report.Info("Clicked on NextButton");

		WebElementFacade msPasswordInputBox= $(".//input[@name='passwd']");
		//common.waitForElement(msPasswordInputBox);
		//$(".//input[@name='passwd']").sendKeys("OfficeMango@0365");
		report.Info("Entered user password");

		WebElementFacade SignInButton= $(".//input[@id='idSIButton9'][@value='Sign in']");
		//common.waitForElement(SignInButton);
		//SignInButton.click();
		report.Info("Clicked on SignInButton");

		WebElementFacade YesButton= $(".//input[@id='idSIButton9'][@value='Yes']");
	//	common.waitForElement(YesButton);
	//	YesButton.click();
		report.Info("Clicked on SignInButton");	*/

		getDriver().switchTo().frame("office_frame");
		Thread.sleep(2000);
		report.Info("Switched the driver to iframe");
		common.upload(getDriver(), "This is to test Office 365 file creation and saving");
		Thread.sleep(2000);
		getDriver().close();

		getDriver().switchTo().window(tabs.get(0));
		getDriver().navigate().refresh();
		Thread.sleep(3000);
		if (getDriver().findElement(By.xpath("//span[text()='" + documentName + ".docx']")).isDisplayed()) {
			getDriver().findElement(By.xpath("//span[text()='" + documentName + ".docx']")).click();
			Thread.sleep(5000);				
			report.Info("Created document successfully");
			getDriver().get(DomainURL);
			Thread.sleep(3000);
		}
	}


	@Step
	public void incomingEmailwithTeamIDCheck(String logicalName) throws IOException, AddressException, MessagingException {
		String filePath = "\\src\\test\\resources\\TestData\\TestData.xlsx";
		String dataSheet = "Project";
		Map <String,Map<Integer, String>> tableMap = ReadWorkbook.readRow(logicalName, filePath, dataSheet);		
		readWorkbook.testData(tableMap);
		for (int i = 0  ;i < tableMap.get("Row").size();i++) {

			String feedType = tableMap.get("feedType").get(i);
			String feedTypePath = ".//a[@title='"+feedType+"']";
			String SubURL = tableMap.get("SubURL").get(i);
			String DomainURL = Serenity.sessionVariableCalled("domainURL").toString();
			getDriver().navigate().to(DomainURL+SubURL);

			WebElementFacade feedToSelect = $(feedTypePath);
			common.waitForElement(feedToSelect);
			report.Info("Navigated to News Feed Page");
			feedToSelect.click();

			emailutility.sendEmail(logicalName);

			WebElementFacade seeNewUpdate = $(".//div[@id='new_updates_notification_cont']");
			common.WaitForObjectVisibility(".//div[@id='new_updates_notification_cont']");
			getDriver().navigate().to(DomainURL+SubURL);
			WebElementFacade receivedEmailSubject = $(".//div[contains(text(),'Testing email via Automation')]");
			receivedEmailSubject.waitUntilVisible();
			receivedEmailSubject.shouldBeVisible();
			report.Info("Email is Recieved by application successfully");
		}

	}
	@Step
	public void incomingEmailReplyViaShareCheck(String logicalName) {
		WebElementFacade receivedEmailSubject = $(".//div[contains(text(),'Testing email via Automation')]");
		receivedEmailSubject.click();

		WebElementFacade mailPopUp = $(".//div[@id='message-list']");
		common.WaitForObjectVisibility(".//div[@id='message-list']");
		mailPopUp.find(By.xpath("//a[contains(@id,'share_actions_el')]")).click();

		WebElementFacade shareOverEmail = mailPopUp.find(By.xpath(".//a[@title='Share over Email']"));
		shareOverEmail.waitUntilVisible();
		common.mouseHover(shareOverEmail);
		shareOverEmail.click();

		WebElementFacade toAddressForSendingEmail = $(".//textarea[@id='users_list']");
		common.WaitForObjectVisibility(".//textarea[@id='users_list']");
		toAddressForSendingEmail.sendKeys("mangorobot12@gmail.com");
		WebElementFacade sendingEmailSubject = $(".//input[@id='subject']");
		sendingEmailSubject.type("This is to check Outgoing Email Functionality");
		WebElementFacade sendEmailButton = $(".//span[text()='Send Email']");
		evaluateJavascript("document.querySelector(\"button[type='submit']\").click()");
		//sendEmailButton.click();
		common.WaitForObjectVisibility(".//div[@class='mango-comment-content comment-details']");
		WebElementFacade commentSection = $(".//div[@class='mango-comment-content comment-details']");

		if (commentSection.find(By.xpath("//span[contains(text(),'has shared this feed over Email with')]"))
				.isDisplayed()) {
			report.Info("Share over email functionality is working successfully");
		} else {
			report.Info("Comment is not generated even after sharing this feed over email");
			Assert.assertTrue(false);
		}

		WebElementFacade closeEmailButton = $(".//div[@class='ms-overlay-close']/span");
		closeEmailButton.click();
	}

	public void readEmail() throws MessagingException {
		String message = "check Outgoing Email";
		Folder folder = null;
		Store store = null;
		report.Info("***READING EMAIL INBOX...");
		//try {
		Properties props = new Properties();
		props.put("mail.store.protocol", "imaps");
		Session session = Session.getInstance(props);
		store = session.getStore("imaps");
		store.connect("imap.gmail.com", "mangorobot12@gmail.com", "Mango@05");
		folder = store.getFolder("INBOX");
		folder.open(Folder.READ_ONLY);
		Message[] messages = folder.getMessages();
		System.out.println("No of Messages : " + folder.getMessageCount());
		System.out.println("No of Unread Messages : " + folder.getUnreadMessageCount());
		for (int i = 0; i < messages.length; i++) {
			System.out.println("Reading MESSAGE # " + (i + 1) + "...");
			Message msg = messages[i];
			if (!msg.getFlags().contains(Flags.Flag.SEEN)) {
				String strMailSubject = "";
				// strMailBody = "";
				// Getting mail subject
				Object subject = msg.getSubject();
				Date receivedDate = msg.getReceivedDate();
				// Getting mail body
				// Object content = getTextFromMessage(msg);
				// Casting objects of mail subject and body into String

				strMailSubject = (String) subject.toString();
				// strMailBody = (String) content.toString();
				// ---- This is what you want to do------
				if (strMailSubject.contains(message)) {
					System.out.println(strMailSubject);
					report.Info("Successfully Received the Outgoing email");
					report.Info("Subject of the mail is: " + strMailSubject);
					report.Info("Mail was Received on: " + receivedDate);
					break;
				}
			}
		}
		//} catch (MessagingException messagingException) {
		//messagingException.printStackTrace();
		//} finally {
		if (folder != null) {
			//try {
			folder.close(true);
			//	} catch (MessagingException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			//}
		}
		if (store != null) {
			//try {
			store.close();
			//} catch (MessagingException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			//}
		}
	}


	/*
	 * public void verifyProjectAPI(){ RestAssured.regi
	 * RestAssured.given().header("Cookie",
	 * "_felix_session_id=fb7b1d150478bb7fd3541e4c688a25bb").
	 * //registerParser("Content-Type","application/json").
	 * //param("username","admin@mediaee.com"). //param("password","dGVtcDEyMzQ=").
	 * //param("name","ProjectSerenity").
	 * when().get("https://mediaee.engageexpress.com/api/project.json").
	 * 
	 * then().assertThat().statusCode(200).
	 * and().assertThat().body("ms_response.project.name",
	 * is("ProjectFromApi2214")); }
	 */
}




