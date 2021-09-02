package pages;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.junit.Assert;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;

import org.openqa.selenium.WebElement;

import org.openqa.selenium.interactions.Actions;


import net.serenitybdd.core.Serenity;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Steps;

import net.thucydides.core.util.EnvironmentVariables;
import net.thucydides.core.util.SystemEnvironmentVariables;


import utilities.ReadWorkbook;
import utilities.ReportMessege;
import utilities.common;
import pages.LMSPage;

public class DomainPage extends PageObject {

	@Steps(shared=true) 
	private ReadWorkbook readWorkbook;

	@Steps(shared=true) 
	private UsersPage usersPage;

	@Steps(shared=true) 
	private ReportMessege report;

	@Steps(shared=true) 
	private ProjectPage projectPage;
	
	@Steps(shared=true) 
	private LMSPage lmsPage;

	@Steps(shared=true) 
	private DomainPage domainPage;

	@Steps(shared=true) 
	private LMSPage lmspage;

	@Steps(shared=true)
	private common common;

	@FindBy(xpath=".//input[@id='user_id']")
	public WebElementFacade UserID;

	@FindBy(xpath=".//input[@id='password']")
	public WebElementFacade Password;

	@FindBy(xpath=".//*[@id='yui-gen0']")
	public WebElementFacade LoginButton;

	@FindBy(xpath=".//*[@class='remind-me-button']")
	public WebElementFacade RemindMeLater;

	public String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
	public String autoITScriptPath ="";
	public String domain = "";
	public String imageLink = "https://pushkarvasekar.wixsite.com/photogenie/nature?lightbox=dataItem-ifw9nx791";

	/*
	 * @FindBy(xpath=".//*[@title='Universal compose box']") public WebElementFacade
	 * ComposeButton;
	 */

	@FindBy(xpath=".//a[text()='Login Into Your MangoApps Domain']")
	public WebElementFacade LoginToDomain;

	public void refreshPage() throws InterruptedException {
		getDriver().navigate().refresh();
		Thread.sleep(3000);
	}


	@Step
	public void loginCheck(String logicalName) throws IOException, InterruptedException {


		if(!(logicalName.contains("IE"))|| !(logicalName.contains("FireFox"))){
			getDriver().quit();

		}

		String username = "";
		String password = "";
		String DomainURL = "";
		String postURL = "";
		String wikiURL = "";
		String pageURL = "";
		String projectUrl = "";
		String projectEmailAddress = "";
		String trackerUrl = "";
		String admin = "";
		String user = "";
		//	String dir = System.getProperty("user.dir");
		String postToArchive = "";
		String trackerForm="";
		String trackerTable ="";
		String publicCourseURL ="";
		String privateCourseURL = "";
		String instructor = "";
		String lmsUser = "";

		EnvironmentVariables variables = SystemEnvironmentVariables.createEnvironmentVariables();

		String TestEnvironment = variables.getProperty("BASE_URL");

		TestEnvironment = TestEnvironment.toUpperCase();
		switch (TestEnvironment) {
		case "EE":
			DomainURL = "https://automationee.engageexpress.com/";
			//DomainURL = "https://mediaee.engageexpress.com/";
			domain = "EE";
			//username= "admin@mediaee.com";
			username= "admin@automationee.com";
			password = "Auto1234";
			postURL="https://automationee.engageexpress.com/mlink/post/Mjk4OTI";
			wikiURL="https://automationee.engageexpress.com/mlink/wiki/MTM5MTI";
			pageURL="https://automationee.engageexpress.com/mlink/site_page/ODY2MDI";
			projectUrl="https://automationee.engageexpress.com/mlink/project/mangoappsautomationteam";
			projectEmailAddress= "mangoappsautomationteam.automationee@hubmail.mangoapps.com";
			trackerUrl="https://automationee.engageexpress.com/mlink/tracker/NTI0MA";
			admin = "Pushkar v";
			user = "Siddharth W";
			postToArchive="https://automationee.engageexpress.com/mlink/post/Mjk5NTc";
			trackerForm ="https://automationee.engageexpress.com/mlink/tracker/NTIzMA";
			trackerTable="https://automationee.engageexpress.com/mlink/tracker/NTI0Mg";
			publicCourseURL="https://automationee.engageexpress.com/extranet/pa";
			privateCourseURL = "https://automationee.engageexpress.com/learn/courses/282";
			instructor = "Pushkar v";
			lmsUser = "Siddharth";
			break;

		case "QA":
			DomainURL = "https://syntelinc.mangopulse.com/";
			domain = "QA";
			username= "sat23test@syntelinc.com";
			password = "temp1234";
			postURL="https://automationee.engageexpress.com/mlink/post/Mjk4OTI";
			wikiURL="https://automationee.engageexpress.com/mlink/wiki/MTM5MTI";
			pageURL="https://automationee.engageexpress.com/mlink/site_page/ODY2MDI";
			projectUrl="https://automationee.engageexpress.com/mlink/project/mangoappsautomationteam";
			projectEmailAddress= "mangoappsautomationteam.automationee@hubmail.mangoapps.com";			
			admin = "Robin Hood";
			user = "Siddharth W";
			trackerUrl="https://syntelinc.mangopulse.com/mlink/tracker/ODEzMQ";
			postToArchive="https://syntelinc.mangopulse.com/mlink/post/MjUxNDE";
			trackerForm ="https://syntelinc.mangopulse.com/mlink/tracker/ODEzMg";
			trackerTable="";
			publicCourseURL="https://automationee.engageexpress.com/extranet/pa";
			privateCourseURL = "https://automationee.engageexpress.com/learn/courses/282";
			instructor = "Pushkar v";
			lmsUser = "Siddharth";
			break;
		case "US":
			//DomainURL = "https://mangocustomer.mangoapps.com/";
			DomainURL ="https://usmangoautomation.mangoapps.com/";
			//"https://alivance.mangoapps.com";
			domain = "US";
			username= "pushkarv@mangospring.com";
			password = "mango1234";
			//username= "siddharthw@alivance.com";
			//password = "temp1234";
			postURL="https://usmangoautomation.mangoapps.com/mlink/post/MTE4MzY1";
			wikiURL="https://usmangoautomation.mangoapps.com/mlink/wiki/NjU1MDY";
			pageURL="https://usmangoautomation.mangoapps.com/mlink/site_page/MTI4NDQyNg";
			projectUrl="https://usmangoautomation.mangoapps.com/mlink/project/mangoappsautomationteam";
			projectEmailAddress= "mangoappsautomationteam.usmangoautomation@us.mangoapps.com";			
			admin = "Pushkar v";
			//"MangoApps Assistant";
			user = "Siddharth W";
			trackerUrl="https://usmangoautomation.mangoapps.com/mlink/tracker/MTE0NjA";
			postToArchive="https://usmangoautomation.mangoapps.com/mlink/post/MTE5MjE1";
			trackerForm ="https://usmangoautomation.mangoapps.com/mlink/tracker/MTE2MTQ";
			trackerTable="https://usmangoautomation.mangoapps.com/mlink/tracker/MTE1Mjc";
			publicCourseURL="https://usmangoautomation.mangoapps.com/extranet/home";
			privateCourseURL = "https://usmangoautomation.mangoapps.com/learn/courses/37";
			instructor = "Pushkar v";
			lmsUser = "Siddharth";
			break;
		case "EU":
			DomainURL = "https://europemangoautomation.mangoapps.com/";
			//"https://inboxdesign.mangoapps.com/";
			domain = "EU";
			username= "pushkarv@mangospring.com";
			password = "mango1234";
			//	username= "hello@inboxdesign.me";
			//password = "temp1234";
			postURL ="https://europemangoautomation.mangoapps.com/mlink/post/MTA5NjQ3";
			wikiURL ="https://europemangoautomation.mangoapps.com/mlink/wiki/MjUwODQ";
			pageURL="https://europemangoautomation.mangoapps.com/mlink/site_page/NjE5Nzg";
			projectUrl = "https://europemangoautomation.mangoapps.com/mlink/project/mangoappsautomationteam";
			//"https://inboxdesign.mangoapps.com/mlink/project/automationtest";
			projectEmailAddress = "mangoappsautomationteam.europemangoautomation@europe.mangoapps.com";			
			admin = "Pushkar v";
			//"Europe iPad DC User";
			user = "Siddharth W";
			trackerUrl="https://europemangoautomation.mangoapps.com/mlink/tracker/NTExNA";
			postToArchive="https://europemangoautomation.mangoapps.com/mlink/post/MTExODA3";
			trackerForm ="https://europemangoautomation.mangoapps.com/mlink/tracker/NTExNQ";
			trackerTable="https://europemangoautomation.mangoapps.com/mlink/tracker/NTEyNg";
			publicCourseURL= "https://europemangoautomation.mangoapps.com/extranet/home";
			privateCourseURL = "https://europemangoautomation.mangoapps.com/learn/courses/3";
			instructor = "Pushkar v";
			lmsUser = "Siddharth";
			break;
		case "GER":
			DomainURL ="https://germanymangoautomation.mangoapps.com/";
			//"https://vkcode.mangoapps.com/";
			domain = "GER";
			username= "pushkarv@mangospring.com";
			password = "mango1234";
			//username= "mangoapps@vkcode.ru";
			//password = "temp1234";
			postURL="https://germanymangoautomation.mangoapps.com/mlink/post/MjU2ODQ";
			wikiURL="https://germanymangoautomation.mangoapps.com/mlink/wiki/NDU5Mg";
			pageURL="https://germanymangoautomation.mangoapps.com/mlink/site_page/MjI5Nzc";
			projectUrl="https://germanymangoautomation.mangoapps.com/mlink/project/mangoappsautomationteam";
			projectEmailAddress= "mangoappsautomationteam.germanymangoautomation@germany.mangoapps.com";
			admin = "Pushkar v";
			//admin = "Mango Apps";
			user = "Siddharth W";
			trackerUrl="https://germanymangoautomation.mangoapps.com/mlink/tracker/ODEz";
			postToArchive="https://germanymangoautomation.mangoapps.com/mlink/post/MjYyMDQ";
			trackerForm ="https://germanymangoautomation.mangoapps.com/mlink/tracker/ODE0";
			trackerTable="https://germanymangoautomation.mangoapps.com/mlink/tracker/ODE3";
			publicCourseURL="https://germanymangoautomation.mangoapps.com/extranet/home";
			privateCourseURL = "https://germanymangoautomation.mangoapps.com/learn/courses/3";
			instructor = "Pushkar v";
			lmsUser = "autorobo";
			break;
		case "ASIA":
			DomainURL ="https://asiamangoautomation.mangoapps.com/";
			domain = "ASIA";
			username= "pushkarv@mangospring.com";
			password = "mango1234";
			postURL="https://asiamangoautomation.mangoapps.com/mlink/post/NTE3OQ";
			wikiURL="https://asiamangoautomation.mangoapps.com/mlink/wiki/NjAxNQ";
			pageURL="https://asiamangoautomation.mangoapps.com/mlink/site_page/MTgxOTU";
			projectUrl="https://asiamangoautomation.mangoapps.com/mlink/project/mangoappsautomationteam";
			projectEmailAddress= "mangoappsautomationteam.asiamangoautomation@asia.mangoapps.com";
			admin = "Pushkar v";
			user = "Siddharth W";
			trackerUrl="https://asiamangoautomation.mangoapps.com/mlink/tracker/NTg4";
			postToArchive="https://asiamangoautomation.mangoapps.com/mlink/post/NTI5NQ";
			trackerForm ="https://asiamangoautomation.mangoapps.com/mlink/tracker/NTg5";
			trackerTable="https://asiamangoautomation.mangoapps.com/mlink/tracker/NTkw";
			publicCourseURL="https://asiamangoautomation.mangoapps.com/extranet/home";
			privateCourseURL = "https://asiamangoautomation.mangoapps.com/learn/courses/1";
			instructor = "Pushkar v";
			lmsUser = "Siddharth";
			break;
		case "SYD":
			DomainURL ="https://sydneymangoautomation.mangoapps.com/";
			domain = "SYD";
			username= "pushkarv@mangospring.com";
			password = "mango1234";
			postURL="https://sydneymangoautomation.mangoapps.com/mlink/post/ODI3NA";
			wikiURL="https://sydneymangoautomation.mangoapps.com/mlink/wiki/Mjg0MA";
			pageURL="https://sydneymangoautomation.mangoapps.com/mlink/site_page/MTY0Mzk";
			projectUrl="https://sydneymangoautomation.mangoapps.com/mlink/project/mangoappsautomationteam";
			projectEmailAddress= "mangoappsautomationteam.sydneymangoautomation@sydney.mangoapps.com";			
			admin = "Pushkar v";
			user = "Siddharth W";
			trackerUrl="https://sydneymangoautomation.mangoapps.com/mlink/tracker/NTQ3";
			postToArchive="https://sydneymangoautomation.mangoapps.com/mlink/post/ODM3MQ";
			trackerForm ="https://sydneymangoautomation.mangoapps.com/mlink/tracker/NTQ4";
			trackerTable="https://sydneymangoautomation.mangoapps.com/mlink/tracker/NTU1";
			publicCourseURL="https://sydneymangoautomation.mangoapps.com/extranet/home";
			privateCourseURL = "https://sydneymangoautomation.mangoapps.com/learn/courses/9";
			instructor = "Pushkar";
			lmsUser = "Siddharth";			
			break;
		case "CROWN":
			DomainURL = "https://devportal.yard4.org/";
			domain = "CROWN";
			username= "gapps@mangospring.com";
			password = "temp123";
			postURL="https://automationee.engageexpress.com/mlink/post/Mjk4OTI";
			wikiURL="https://automationee.engageexpress.com/mlink/wiki/MTM5MTI";
			pageURL="https://automationee.engageexpress.com/mlink/site_page/ODY2MDI";
			projectUrl="https://automationee.engageexpress.com/mlink/project/mangoappsautomationteam";
			projectEmailAddress= "mangoappsautomationteam.automationee@hubmail.mangoapps.com";
			trackerUrl="https://automationee.engageexpress.com/mlink/tracker/NTE5Mw";
			admin = "MangoApps Assistant";
			user = "MA Testing";
			postToArchive="";
			trackerForm ="";
			trackerTable="";
			break;
		case "KM":
			DomainURL ="https://kmlivedev.kellymoore.com/";
			domain = "KM";
			username= "ianh@mangoapps.com";
			password = "temp123";
			postURL="https://kmlivedev.kellymoore.com/mlink/post/NjYzOQ";
			wikiURL="https://sydneymangoautomation.mangoapps.com/mlink/wiki/Mjg0MA";
			pageURL="https://sydneymangoautomation.mangoapps.com/mlink/site_page/MTY0Mzk";
			projectUrl="https://sydneymangoautomation.mangoapps.com/mlink/project/mangoappsautomationteam";
			projectEmailAddress= "mangoappsautomationteam.sydneymangoautomation@sydney.mangoapps.com";			
			admin = "Ian Hergert";
			user = "Siddharth W";
			trackerUrl="https://kmlivedev.kellymoore.com/mlink/tracker/Nzc";
			postToArchive="https://kmlivedev.kellymoore.com/mlink/post/NjY0MA";
			trackerForm ="https://kmlivedev.kellymoore.com/mlink/tracker/Nzg";
			trackerTable="";
			break;
		case "TM":
			DomainURL = "https://zenithtest.teamhealth.com/";
			domain = "TM";
			username= "mango_admins@teamhealth.com";
			password = "MobileFirst@123";
			postURL="https://zenithtest.teamhealth.com/mlink/post/NzkxNDc";
			wikiURL="https://zenithtest.teamhealth.com/mlink/wiki/NTc4Nzk";
			pageURL="https://zenithtest.teamhealth.com/mlink/site_page/MTIwMjIyOQ";
			projectUrl="https://zenithtest.teamhealth.com/mlink/group/mangoappsautomationteam";
			projectEmailAddress= "mangoappsautomationteam.automationee@hubmail.mangoapps.com";
			trackerUrl="https://zenithtest.teamhealth.com/mlink/tracker/NjI5OA";
			admin = "Admin User";
			user = "Jwala r";
			postToArchive="https://zenithtest.teamhealth.com/mlink/post/NzkxNDg";
			trackerForm ="https://zenithtest.teamhealth.com/mlink/tracker/NjI5OQ";
			trackerTable="https://zenithtest.teamhealth.com/mlink/tracker/NjMyNQ";
			publicCourseURL="https://zenithtest.teamhealth.com/extranet/home";
			privateCourseURL = "https://zenithtest.teamhealth.com/learn/courses/141";
			instructor = "Admin User";
			lmsUser = "Jwala";
			break;
		case "CABL":
			DomainURL = "https://staging.mangoapps.com/";
			domain = "CABL";
			username= "pushkarv@mangospring.com";
			password = "mango1234";
			postURL="https://staging.mangoapps.com/mlink/post/Nzc1";
			wikiURL="https://staging.mangoapps.com/mlink/wiki/MjI0";
			pageURL="https://staging.mangoapps.com/mlink/site_page/MTc3Ng";
			projectUrl="https://staging.mangoapps.com/mlink/project/mangoappsautomationteam";
			projectEmailAddress= "mangoappsautomationteam.staging@staging.mangoapps.com";
			trackerUrl="https://staging.mangoapps.com/mlink/tracker/NDkx";
			admin = "Pushkar v";
			user = "Siddharth W";
			postToArchive="https://staging.mangoapps.com/mlink/post/Nzc2";
			trackerForm ="https://staging.mangoapps.com/mlink/tracker/NDky";
			trackerTable="https://staging.mangoapps.com/mlink/tracker/NDk0";
			break;
			
		case "YMCA":
			DomainURL = "https://mangotest.ymca.net/";
			domain = "YMCA";
			username= "admin@mangotest.com";
			password = "temp123";
			postURL="https://staging.mangoapps.com/mlink/post/Nzc1";
			wikiURL="https://staging.mangoapps.com/mlink/wiki/MjI0";
			pageURL="https://mangotest.ymca.net/mlink/site_page/MTE1Mw";
			projectUrl="https://mangotest.ymca.net/mlink/project/mangoappsautomationteam";
			projectEmailAddress= "mangoappsautomationteam.mangotest@connectonlink.ymca.net";
			trackerUrl="https://mangotest.ymca.net/mlink/tracker/NjQ";
			admin = "Admin admin";
			user = "Siddharth W";
			postToArchive="https://staging.mangoapps.com/mlink/post/Nzc2";
			trackerForm ="https://mangotest.ymca.net/mlink/tracker/NjM";
			trackerTable="https://mangotest.ymca.net/mlink/tracker/NjY";

			break;
		}

		if(logicalName.contains("Test")) {
			username= "gapps@mangospring.com";
			password = "temp1234";
			if(domain.contains("crown")) {	
				username= "siddharthw@purpletrail.com";
				password = "Ognam@1234";
			}else if(domain.contains("TM")) {	
				username= "jwalar@mangospring.com";
				password = "temp1234";								
			}else if(domain.contains("CABL")) {	
				//username= "jwalar@mangospring.com";
				password = "temp123";								
			}
		} else if(logicalName.contains("guest")) {	
			username= "autorobo@automation.com";
			password = "temp1234";								
		}else if(logicalName.contains("Company")) {	
			username= "mangorobot12@gmail.com";
			password = "temp1234";
			if(domain.contains("TM")) {	
				username= "jwalar@mangospring.com";
				password = "temp1234";
			}
		}


		Serenity.setSessionVariable("domain").to(domain);//Setting session variable for URL to use throughout a test session
		Serenity.setSessionVariable("domainURL").to(DomainURL);//Setting session variable for URL to use throughout a test session
		Serenity.setSessionVariable("User").to(username);
		Serenity.setSessionVariable("existingPostURL").to(postURL);
		Serenity.setSessionVariable("existingWikiURL").to(wikiURL);
		Serenity.setSessionVariable("existingPageURL").to(pageURL);
		Serenity.setSessionVariable("existingProjectUrl").to(projectUrl);
		Serenity.setSessionVariable("projectEmailAddress").to(projectEmailAddress);
		Serenity.setSessionVariable("existingTrackerUrl").to(trackerUrl);
		Serenity.setSessionVariable("admin").to(admin);
		Serenity.setSessionVariable("user").to(user);
		Serenity.setSessionVariable("postToArchive").to(postToArchive);		
		Serenity.setSessionVariable("trackerForm").to(trackerForm);
		Serenity.setSessionVariable("trackerTable").to(trackerTable);
		Serenity.setSessionVariable("publicCourseURL").to(publicCourseURL);
		Serenity.setSessionVariable("privateCourseURL").to(privateCourseURL);
		Serenity.setSessionVariable("instructor").to(instructor);
		Serenity.setSessionVariable("lmsUser").to(lmsUser);

		String path = System.getProperty("user.dir")+"\\replaceText.bat";//changing chrome preferences file for chrome crash
		Runtime.getRuntime().exec(path);
		Thread.sleep(1000);

		getDriver().get(DomainURL);		

		//common.waitForElement(UserID);
		if(domain.contains("TM")) {
			if(logicalName.contains("desired")) {
				Thread.sleep(2000);
				$(".//a[@id='show_other_sso_methods']").waitUntilClickable();
				$(".//a[@id='show_other_sso_methods']").click();
				//				common.clickElement($(".//a[@id='show_other_sso_methods']"));
				Thread.sleep(1500);
				$(".//input[@id='okta-signin-username']").sendKeys(username);
				$(".//input[@id='okta-signin-password']").sendKeys(password);
				//evaluateJavascript("document.querySelector(\"label[data-se-for-name='remember']\").click()");
				evaluateJavascript("document.querySelector(\"input[id='okta-signin-submit']\").click()");
			}else if(logicalName.contains("TestUser")) {
				Thread.sleep(600);
				$("//*[@id='frm_login']/div/div[4]/i").waitUntilClickable();
				$("//*[@id='frm_login']/div/div[4]/i").click();
				UserID.sendKeys(username);	
				//	report.Info("Entered:- "+username);
				Password.sendKeys(password);
				//report.Info("Entered Password:- "+password);
				evaluateJavascript("document.querySelector(\"input[id='remember_me']\").click()");
				evaluateJavascript("document.querySelector(\"button[id='yui-gen0']\").click()");
			}

		}else {
			UserID.waitUntilEnabled();
			UserID.sendKeys(username);	
			//	report.Info("Entered:- "+username);
			Password.sendKeys(password);
			//report.Info("Entered Password:- "+password);
			evaluateJavascript("document.querySelector(\"input[id='remember_me']\").click()");
			evaluateJavascript("document.querySelector(\"button[id='yui-gen0']\").click()");
			//report.Info("Clicked Login button");
		}


		if(logicalName.contains("TestUser")){
			//common.WaitForObjectVisibility(".//a[@class='ack_alert_post actionbutton actionblue waves-effect waves-dark']");
			Thread.sleep(3000);
			List<WebElementFacade> alertAcknowledgeList = findAll(By.xpath(".//a[@class='ack_alert_post actionbutton actionblue waves-effect waves-dark']"));
			int alertAckCount = alertAcknowledgeList.size();
			if(alertAckCount>0) {
				evaluateJavascript("document.querySelector(\"a[class='ack_alert_post actionbutton actionblue waves-effect waves-dark']\").click()");
				report.Info("Alert successfully acknowledged");
			}
		}

		if(logicalName.contains("DeactivatedTestUser")){
			ComposeButton.shouldNotBeVisible();
		}else {
			//Thread.sleep(2000);
			common.WaitForObjectVisibility(".//span[text()='Compose']");
			ComposeButton.shouldBeVisible();
			report.Info("Login Successful for " + logicalName);
		}		
		//lmsPage.checkLearnDisabled("ViewCourseInsights");
	}


	@FindBy(xpath=".//select[@id='landing_page']")
	public WebElementFacade LandingPageDropDown;

	@FindBy(xpath=".//label[@for='landing_page_apply_to_all']")
	public WebElementFacade LandingPageApplyToAll;

	@FindBy(xpath=".//*[@id='submit_landing_page']")
	public WebElementFacade SaveApplyToAll;

	@FindBy(xpath="(.//span[text()='Apply Settings'])[2]")
	public WebElementFacade ApplySettingsConfirmation;

	@FindBy(xpath=".//li[@class='hover-object matop-nav-link has_status']")
	public WebElementFacade UserSettings;

	@FindBy(xpath=".//a[@href='/sites/home']")	
	public WebElementFacade GoToUserPortal;


	@Step
	public void defaultLandingPageSettingCheck(String logicalName) throws IOException, InterruptedException {

		String expectedURL = "";
		String filePath = "\\src\\test\\resources\\TestData\\TestData.xlsx";
		String dataSheet = "Domain";
		Map <String,Map<Integer, String>> tableMap = ReadWorkbook.readRow(logicalName, filePath, dataSheet);		
		readWorkbook.testData(tableMap);
		for (int i = 0  ;i < tableMap.get("Row").size();i++) {
			String landingPage = tableMap.get("LandingPage").get(i);			
			String SubURL = tableMap.get("SubURL").get(i);
			String DomainURL = Serenity.sessionVariableCalled("domainURL").toString();
			getDriver().navigate().to(DomainURL+SubURL);

			LandingPageDropDown.shouldBeVisible();
			LandingPageDropDown.selectByVisibleText(landingPage);
			//Thread.sleep(5000);
			LandingPageApplyToAll.shouldBeEnabled();
			LandingPageApplyToAll.click();
			Thread.sleep(1000);
			SaveApplyToAll.click();
			ApplySettingsConfirmation.shouldBeEnabled();
			$("(.//span[text()='Apply Settings'])[2]").click();
			//ApplySettingsConfirmation.click();
			/*
			 * UserSettings.shouldBeEnabled(); UserSettings.click();
			 * GoToUserPortal.waitUntilClickable(); GoToUserPortal.click();
			 */
			getDriver().navigate().to(DomainURL+"u");


			if (landingPage.equalsIgnoreCase("Dashboard")) {
				expectedURL = "/ce/pulse/user/overview/index";
			} else if (landingPage.equalsIgnoreCase("Company")) {
				expectedURL = "/sites/";
			} else if (landingPage.equalsIgnoreCase("News Feed")) {
				expectedURL = "/ce/pulse/user/home";
			} else if (landingPage.equalsIgnoreCase("Messages")) {
				expectedURL = "/ce/pulse/user/private_messages";
			} else if (landingPage.equalsIgnoreCase("Projects")) {
				expectedURL = "/user/project";
			} else if (landingPage.equalsIgnoreCase("People")) {
				expectedURL = "/sites/peoples/people_directory";
			} else if (landingPage.equalsIgnoreCase("Groups")) {
				expectedURL = "/ce/pulse/user/teams/group/index";
			} else if (landingPage.equalsIgnoreCase("Files")) {
				expectedURL = "/user/document";
			}
			Thread.sleep(3000);
			if (getDriver().getCurrentUrl().contains(expectedURL)) {

				Assert.assertTrue(true);
				report.Info("Landing page successfully set to " + landingPage + "with URL " + expectedURL);

			} else {
				report.Info("Landing page could not be set to " + landingPage + "it is set to " + expectedURL);
				Assert.assertTrue(false);

			}

		}

	}




	@Step
	public void domainSignUpCheck(String logicalName) throws IOException {

		String filePath = "\\src\\test\\resources\\TestData\\TestData.xlsx";
		String dataSheet = "Domain";
		Map <String,Map<Integer, String>> tableMap = ReadWorkbook.readRow(logicalName, filePath, dataSheet);	
		readWorkbook.testData(tableMap);
		for (int i = 0  ;i < tableMap.get("Row").size();i++) {
			String username = tableMap.get("Username").get(i);
			String password = tableMap.get("Password").get(i);
			String SubURL = tableMap.get("SubURL").get(i);
			String DomainURL = Serenity.sessionVariableCalled("domainURL").toString();
			getDriver().navigate().to(DomainURL+SubURL);

		}

	}


	@FindBy(id="time_zone")
	public WebElementFacade MyTimeZone;

	@FindBy(xpath=".//label[@for='locale_settings_apply_to_all']")
	public WebElementFacade ApplytoAllUsers;

	@FindBy(id="submit_locale_settings")
	public WebElementFacade SaveLocaleSettings;

	@FindBy(xpath="(.//span[text()='Apply Settings'])[2]")
	public WebElementFacade ApplySettings;

	@Step
	public void timeZoneSetup(String logicalName) throws IOException, InterruptedException {

		String filePath = "\\src\\test\\resources\\TestData\\TestData.xlsx";
		String dataSheet = "Domain";
		Map <String,Map<Integer, String>> tableMap = ReadWorkbook.readRow(logicalName, filePath, dataSheet);	
		readWorkbook.testData(tableMap);
		for (int i = 0  ;i < tableMap.get("Row").size();i++) {
			String setTimeZone = tableMap.get("setTimeZone").get(i);
			String SubURL = tableMap.get("SubURL").get(i);
			String DomainURL = Serenity.sessionVariableCalled("domainURL").toString();
			getDriver().navigate().to(DomainURL+SubURL);			
			MyTimeZone.selectByValue(setTimeZone);
			ApplytoAllUsers.click();

			SaveLocaleSettings.click();
			common.waitForElement(ApplySettings);
			ApplySettings.click();
			report.Info("Passed sampleTest Successfully");			
			//loginCheck();need to confirm

		}
	}
	@FindBy(xpath=".//input[@id='domain_s_notify_on_web']")
	public WebElementFacade NotifyOnWeb;

	@FindBy(xpath=".//label[@for='domain_s_notify_on_desktop']")
	public WebElementFacade NotifyOnDesktop;

	@FindBy(xpath=".//label[@for='domain_s_notify_on_mobile']")
	public WebElementFacade NotifyOnMobile;

	@FindBy(xpath=".//label[@for='domain_s_notify_on_email']")
	public WebElementFacade NotifyOnEmail;

	@FindBy(id="notify_push_setting_dialog")
	public WebElementFacade ApplySettingsButton;

	@FindBy(xpath=".//label[@for='in_app_all']")
	public WebElementFacade ApplyForAllRadioButton;

	@FindBy(xpath=".//span[text()='Apply']")
	public WebElementFacade ApplyButtonOnAlert;

	@FindBy(xpath=".//div[@class='onoffswitch inline_block middle']/input[@id='user_s_notify_on_web']")
	public WebElementFacade UserWebNotify;

	@FindBy(xpath=".//input[@id='user_s_notify_on_desktop']")
	public WebElementFacade UserDesktopNotify;

	@FindBy(xpath=".//input[@id='user_s_notify_on_mobile']")
	public WebElementFacade UserMobileNotify;

	@FindBy(xpath=".//input[@id='user_s_notify_on_email']")
	public WebElementFacade UserEmailNotify;



	@Step
	public void notificationPreferenceUpdateCheck(String logicalName) throws IOException, InterruptedException {
		/*
		 * getDriver().get(
		 * "chrome://settings/content/siteDetails?site=https%3A%2F%2Fmediaee.engageexpress.com"
		 * ); Thread.sleep(2000);
		 */
		Actions myAct = new Actions(getDriver());
		String filePath = "\\src\\test\\resources\\TestData\\TestData.xlsx";
		String dataSheet = "Domain";
		Map <String,Map<Integer, String>> tableMap = ReadWorkbook.readRow(logicalName, filePath, dataSheet);	
		readWorkbook.testData(tableMap);
		for (int i = 0  ;i < tableMap.get("Row").size();i++) {
			String SubURL = tableMap.get("SubURL").get(i);
			String DomainURL = Serenity.sessionVariableCalled("domainURL").toString();
			getDriver().navigate().to(DomainURL+SubURL);
			//Thread.sleep(2000);
			report.Info("Entered into notification module of admin");

			WebElementFacade NotifyOnWebChecked = $(".//input[@id='domain_s_notify_on_web']");			
			if (NotifyOnWebChecked.getAttribute("checked") == null){
				common.clickElement(NotifyOnWebChecked);
				//report.Info( "Checked web notification for important only");
			}
			Thread.sleep(500);
			WebElementFacade NotifyOnDesktopChecked = $(".//input[@id='domain_s_notify_on_desktop']");
			if(NotifyOnDesktopChecked.getAttribute("checked") == null){
				common.clickElement(NotifyOnDesktopChecked);
				report.Info("Checked desktop notification for important only");
			}
			Thread.sleep(500);
			WebElementFacade NotifyOnMobileChecked = $(".//input[@id='domain_s_notify_on_mobile']");
			if(NotifyOnMobileChecked.getAttribute("checked") == null){
				common.clickElement(NotifyOnMobileChecked);
				report.Info("Checked mobile notification for important only");
			}
			Thread.sleep(500);
			WebElementFacade NotifyOnEmailChecked = $(".//input[@id='domain_s_notify_on_email']");
			if(NotifyOnEmailChecked.getAttribute("checked") == null){
				common.clickElement(NotifyOnEmailChecked);
				report.Info("Checked email notification for important only");
			}


			evaluateJavascript("document.querySelector(\"a[id='notify_push_setting_dialog']\").click()");
			//ApplySettingsButton.click();
			//report.Info( "Clicked on apply settings");

			common.WaitForObjectVisibility(".//label[@for='in_app_all']");
			ApplyForAllRadioButton.click();
			ApplyButtonOnAlert.click();			
			//	report.Info("Checked to apply these settings to all domain users");


			getDriver().navigate().to(DomainURL+"//user//settings//notification_setting");
			common.WaitForObjectVisibility(".//input[@id='user_s_notify_on_web']");		
			Thread.sleep(3000);

			WebElementFacade UserWebNotifyChecked = $(".//input[@id='user_s_notify_on_web']");
			//UserWebNotifyChecked.shouldBeVisible();
			if(UserWebNotifyChecked.getAttribute("checked") != null){
				report.Info("web notification setting of user for imp only is checked");
			}else {
				report.Info("web notification setting of user for imp only is not checked");
				Assert.assertTrue(false);
			}

			WebElementFacade UserDesktopNotifyChecked = $(".//input[@id='user_s_notify_on_desktop']");
			if(UserDesktopNotifyChecked.getAttribute("checked") != null){
				report.Info("Desktop notification setting of user for imp only is checked");
			}else {
				report.Info("Desktop notification setting of user for imp only is not checked");
				Assert.assertTrue(false);
			}

			WebElementFacade UserMobileNotifyChecked = $(".//input[@id='user_s_notify_on_mobile']");
			if(UserMobileNotifyChecked.getAttribute("checked") != null){
				report.Info("Mobile notification setting of user for imp only is checked");
			}else {
				report.Info("Mobile notification setting of user for imp only is not checked");
				Assert.assertTrue(false);
			}

			WebElementFacade UserEmailNotifyChecked = $(".//input[@id='user_s_notify_on_email']");
			if(UserEmailNotifyChecked.getAttribute("checked") != null){
				report.Info("Email notification setting of user for imp only is checked");
			}else {
				report.Info("Email notification setting of user for imp only is not checked");
				Assert.assertTrue(false);
			}

			/*getDriver().navigate().to(DomainURL+SubURL);
			//Thread.sleep(1000);
			common.clickElement($(".//label[@for='domain_s_notify_on_web']"));
			Thread.sleep(500);
			report.Info("Uncheking all notifications");
			common.clickElement($(".//label[@for='domain_s_notify_on_desktop']"));
			Thread.sleep(500);
			common.clickElement($(".//label[@for='domain_s_notify_on_mobile']"));
			Thread.sleep(500);
			common.clickElement($(".//label[@for='domain_s_notify_on_email']"));

			evaluateJavascript("document.querySelector(\"a[id='notify_push_setting_dialog']\").click()");
			common.WaitForObjectVisibility(".//label[@for='in_app_all']");
			ApplyForAllRadioButton.click();
			ApplyButtonOnAlert.click();	
			report.Info("Disabled all notifications");*/

		}
	}


	@FindBy(xpath=".//a[text()='Deactivate']")
	public WebElementFacade Deactivate;

	@FindBy(xpath=".//span[text()='Give Recognition']")
	public WebElementFacade GiveRecognition;

	@FindBy(id="category_award_list")
	public WebElementFacade AllAwardsList;


	@Step
	public void deActivateRecognitionCheck(String logicalName) throws IOException, InterruptedException {

		String filePath = "\\src\\test\\resources\\TestData\\TestData.xlsx";
		String dataSheet = "Domain";
		Map <String,Map<Integer, String>> tableMap = ReadWorkbook.readRow(logicalName, filePath, dataSheet);	
		readWorkbook.testData(tableMap);
		for (int i = 0  ;i < tableMap.get("Row").size();i++) {

			String SubURL = tableMap.get("SubURL").get(i);
			String awardToDeactivate = tableMap.get("awardToDeactivate").get(i);

			String DomainURL = Serenity.sessionVariableCalled("domainURL").toString();
			getDriver().navigate().to(DomainURL+SubURL);
			report.Info("Opened recognitions tab");
			List<WebElement> table = getDriver().findElements(By.xpath("//*[@id='award_table_list']/tbody/tr"));
			int rowCount = table.size();
			report.Info("converted table into web element");

			for (int rowNum = 1; rowNum < rowCount; rowNum++) {
				String awardName = getDriver()
						.findElement(By.xpath("//*[@id='award_table_list']/tbody/tr[" + rowNum + "]/td[1]"))
						.getText();
				report.Info("traversing each row");
				report.Info("Award name is: " + awardName);

				if (awardName.equalsIgnoreCase(awardToDeactivate)) {
					String awardStatus = getDriver()
							.findElement(By.xpath("//*[@id='award_table_list']/tbody/tr[" + rowNum + "]/td[4]"))
							.getText();
					report.Info("Current Status of the Award is: " + awardStatus);
					if (awardStatus.equalsIgnoreCase("ACTIVE")) {
						getDriver().findElement(By.xpath("//*[@id='award_table_list']/tbody/tr[" + rowNum + "]/td[5]/div"))
						.click();
						//	report.Info("clicked on respective 3 dots of given award");
						Deactivate.click();
						report.Info("Clicked on the deactivate Option");

						SubURL = "/user/recognitions/feeds";
						getDriver().navigate().to(DomainURL+SubURL);

						//common.waitForElement(GiveRecognition);
						GiveRecognition.shouldBePresent();
						GiveRecognition.click();

						if (!AllAwardsList.getText().contains(awardToDeactivate)) {
							report.Info("Award has been successfully deactivated: Now it is not present in the list");
						} else {
							report.Info("Award is still active");
							Assert.assertTrue(false);
						}

						WebElementFacade CloseAllAwards = find(By.xpath(".//*[@id='ms_global_dialog']/span"));
						CloseAllAwards.click();
						report.Info("Closed the Give Recognition Pop-Up");

					} else if (awardStatus.equalsIgnoreCase("IN-ACTIVE")) {
						report.Info("Its already in deactivated status");
					}
					break;
				} else {
					report.Info("Please provide proper award name in the configuration file");
					Assert.assertTrue(false);
				}
			}
		}
	}

	@FindBy(xpath=".//span[text()='Compose']")
	public WebElementFacade ComposeButton;

	@FindBy(xpath=".//a[text()=' Edit Featured Image ']")
	public WebElementFacade EditFeaturedImage;

	@FindBy(xpath=".//a[text()='Replace']")
	public WebElementFacade ReplaceFeaturedImage;

	@FindBy(xpath=".//a[@class='cke_dialog_tab'][@title='Upload']")
	public WebElementFacade UploadTabFeaturedImage;

	@FindBy(xpath=".//input[@id='token-input-mypostTeams']")
	public WebElementFacade TeamInputBox;


	@Step
	public void composeClick(String logicalName)throws InterruptedException, IOException, AWTException {

		String filePath = "\\src\\test\\resources\\TestData\\TestData.xlsx";
		String dataSheet = "Domain";

		Map <String,Map<Integer, String>> tableMap = ReadWorkbook.readRow(logicalName, filePath, dataSheet);	
		readWorkbook.testData(tableMap);
		for (int i = 0  ;i < tableMap.get("Row").size();i++) {

			String SubURL = tableMap.get("SubURL").get(i);
			String parentAction = tableMap.get("parentAction").get(i);
			String parentActionPath= ".//div[@class='border-bottom-1']/span[text()='"+parentAction+"']";
			String composeAction = tableMap.get("composeAction").get(i);
			String ComposeOptionsPath = ".//span[text()='"+composeAction+"']";
			//			String autoCompleteOption = tableMap.get("autoCompleteOption").get(i);
			String MediaPath= tableMap.get("MediaPath").get(i);
			MediaPath = System.getProperty("user.dir")+MediaPath;
			String DomainURL = "";
			String eventType= tableMap.get("EventType").get(i);
			String recurrance= tableMap.get("Recurrance").get(i);
			String composeSubAction = tableMap.get("composeSubAction").get(i);
			String shareWith = tableMap.get("shareWith").get(i);
			//String composeSubAction = tableMap.get("composeSubAction").get(i);

			DomainURL = Serenity.sessionVariableCalled("domainURL").toString();
			DomainURL= DomainURL+SubURL;
			getDriver().navigate().to(DomainURL);
			Thread.sleep(1500);

			WebElement notify = find(By.xpath("//*[@id='notice']/a"));
			try {
				notify.click();				
			} catch (Exception e) {				
			}
			Thread.sleep(500);
			/*
			 * WebElement notify = driver.findElement(By.xpath("//*[@id=\"notice\"]/a"));
			 * try { notify.click(); report.Info("Closed the Notification bar"); } catch
			 * (Exception e) { System.out.println(e.getMessage()); }
			 */
			//common.waitForElement(ComposeButton);
			ComposeButton.shouldBePresent();
			common.scrollToView( ComposeButton);
			common.clickElement( ComposeButton);
			report.Info("Clicked on Compose Box");
			//Thread.sleep(1000);

			List<WebElementFacade> parentActionList = findAll(By.xpath(".//div[@class='border-bottom-1']/span"));
			int parentActionCount = parentActionList.size();

			if(parentActionCount==0) {
				ComposeOptionsPath =".//a[contains(@header-title,'"+composeAction+"')]";
			}


			WebElementFacade ComposeOption = find(By.xpath(ComposeOptionsPath));//Creates Compose option on the fly based on excel input>composeAction

			WebElementFacade parentActionToHover = find(By.xpath(parentActionPath));//Creates Parent Action option to hover on the fly based on excel input>parentAction


			composeAction = composeAction.toUpperCase();

			switch (composeAction) {
			case "UPDATE":
				//ComposeOption = find(By.xpath(ComposeOptionsPath));//Creates Compose option on the fly based on excel input>composeAction
				if(parentActionCount > 0) {
					common.waitForElement(parentActionToHover);
					report.Info("Compose Box is open now");				
					//parentActionToHover = find(By.xpath(parentActionPath));//Creates Parent Action option to hover on the fly based on excel input>parentAction
					common.mouseHover( parentActionToHover);
					parentActionToHover.click();
				}
				common.waitForElement(ComposeOption);
				report.Info("Compose Box is open now");
				common.mouseHover( ComposeOption);
				ComposeOption.click();

				WebElementFacade updateTo = find(By.xpath(".//input[@id='token-input-myTeams']"));
				common.waitForElement(updateTo);
				updateTo.sendKeys("MangoApps");
				Thread.sleep(1000);
				updateTo.sendKeys(Keys.TAB);
				WebElementFacade UpdateTextArea = find(By.xpath(".//textarea[@id='project_status_update_team']"));

				//common.selectFromAutocomplete(updateTo, autoCompleteOption);//Enters given string into textbox and select the desired value from Auto Complete							
				//upload(driver, "sampleText");

				UpdateTextArea.typeAndTab("This is Test Automation Update");
				WebElementFacade ShareUpdateButton = find(By.xpath(".//*[@id='ms-feed-btn']/span/span"));
				//common.mouseHover(ShareUpdateButton);
				common.clickElement(ShareUpdateButton);
				break;

			case "QUESTION": 
				//ComposeOption = find(By.xpath(ComposeOptionsPath));//Creates Compose option on the fly based on excel input>composeAction
				common.waitForElement(ComposeOption);
				report.Info("Compose Box is open now");
				common.mouseHover( ComposeOption);
				ComposeOption.click();
				report.Info("Clicked on Question Successfully");
				WebElementFacade questionTo = find(By.xpath(".//input[@id='token-input-myquestionTeams']"));
				common.WaitForObjectVisibility(".//input[@id='token-input-myquestionTeams']");

				/*
				 * List<WebElement> radioButtons=
				 * getDriver().findElements(By.xpath(".//input[@type='radio']"));
				 * RadioButtonGroup radiogroup = new RadioButtonGroup(radioButtons);
				 * radiogroup.selectByValue("colleague");
				 */
				Thread.sleep(2000);
				WebElementFacade AllMyFollowers = find(By.xpath(".//*[@id='frm-ask-question']/div/div[1]/div/div/span[3]/label"));

				evaluateJavascript("document.querySelector(\"label[for='questionWithColleague']\").click()");
				//AllMyFollowers.click();

				report.Info("Clicked on To all my followers");
				WebElementFacade YourQuestion = find(By.xpath(".//textarea[@id='project_ask_a_question']"));
				YourQuestion.waitUntilVisible();
				String questionDescription = tableMap.get("questionDescription").get(i);
				YourQuestion.type(questionDescription);
				report.Info("Entered the description of the question");							//
				WebElementFacade AskQuestionButton = find(By.xpath(".//span[text()='Ask']"));
				AskQuestionButton.click();
				report.Info("Clicked on Ask Button Successfully");
				break;
			case "POST":
				String expectedPostTitle ="";
				//ComposeOption = find(By.xpath(ComposeOptionsPath));//Creates Compose option on the fly based on excel input>composeAction
				common.waitForElement(ComposeOption);
				report.Info("Compose Box is open now");
				common.mouseHover( ComposeOption);
				ComposeOption.click();
				report.Info("Clicked on Post Successfully");

				if(shareWith.equalsIgnoreCase("Team")) {
					WebElementFacade teamNameInputBox = find(By.xpath(".//input[@id='token-input-mypostTeams']"));
					common.WaitForObjectVisibility(".//input[@id='token-input-mypostTeams']");
					teamNameInputBox.sendKeys("MangoApps");
					Thread.sleep(2000);
					teamNameInputBox.sendKeys(Keys.TAB);
					report.Info("entered team name");
				}else {
					WebElementFacade ShareWithEveryOne = find(By.xpath(".//label[@for='postWithColleague']"));
					common.WaitForObjectVisibility(".//label[@for='postWithColleague']");
					common.waitForElement(ShareWithEveryOne);
					ShareWithEveryOne.click();
					report.Info("Clicked on Share with Everyone Successfully");
				}

				if(logicalName.contains("CreatesAPostAlert")) {
					WebElementFacade postAlertTab = $(".//a[@id='compose_alert_tab_a']");
					postAlertTab.click();
					WebElementFacade WarningAlert = $(".//div[@title='Blank']");
					//common.WaitForObjectVisibility(".//div[@title='Blank']");
					Thread.sleep(1000);
					evaluateJavascript("document.querySelector(\"p[title='Template to create your own alert from scratch']\").click()");
					//WarningAlert.click();
				}else {
					WebElementFacade postTemplateSelect = find(By.xpath(".//div[@title='Newsletter Template 2']"));
					postTemplateSelect.click();
				}
				WebElementFacade postContinueButton = find(By.xpath(".//span[text()='Continue']"));
				common.clickElement( postContinueButton);
				Thread.sleep(1000);
				//postContinueButton.click();
				report.Info("Clicked on Continue Button");
				common.waitForElement(projectPage.PostTitleTextBox);	
				if(!(logicalName.contains("CreatesAPostAlert"))) {
					expectedPostTitle ="Automation Post" + dateName;				
					projectPage.PostTitleTextBox.type(expectedPostTitle);

				}else {

					expectedPostTitle ="PLEASE IGNORE! TEST ALERT " + dateName;				
					projectPage.PostTitleTextBox.typeAndTab(expectedPostTitle);
					//Thread.sleep(1000);
					WebElementFacade numberdBullets=$("(.//span[@class='cke_toolgroup']/a[@title='Insert/Remove Numbered List'])[1]");					
					numberdBullets.click();
					Thread.sleep(1000);
					/*
					 * String onOff = numberdBullets.getAttribute("class"); if(onOff.contains("on"))
					 * { numberdBullets.click(); }
					 */
					//Robot robot = new Robot();
					//robot.keyPress(KeyEvent.VK_TAB);
					//robot.keyRelease(KeyEvent.VK_TAB);
					projectPage.PostTitleTextBox.click();
					//projectPage.PostTitleTextBox.sendKeys(Keys.TAB);
					Thread.sleep(1000);
					//common.upload(getDriver(), "TEST ALERT PLEASE IGNORE");
					//Thread.sleep(1000);
					/*	WebElementFacade iFrame = $(".//iframe[@title='Rich Text Editor, intranet_post_description']");
					getDriver().switchTo().frame(iFrame);
					Thread.sleep(1000);
					WebElementFacade alertContent = $(".//body[@id='mangoCKeditorContent']");
					alertContent.click();
					alertContent.type("TEST ALERT PLEASE IGNORE");
					getDriver().switchTo().defaultContent();
					Thread.sleep(1000);*/
				}
				report.Info( "Entered the Post Title As: " + expectedPostTitle);
				Serenity.setSessionVariable("expectedPostTitle").to(expectedPostTitle);
				//ComposeSubAction allows you to select for ex Post with video or scheduled post etc.
				if(composeSubAction.equals("PostFeaturedImage")){
					common.scrollToView( EditFeaturedImage);
					common.clickElement( EditFeaturedImage);
					report.Info("Clicked on Edit featured image of the post");
					Thread.sleep(1000);
					common.scrollToView( ReplaceFeaturedImage);
					common.clickElement( ReplaceFeaturedImage);
					report.Info("Clicked on replce Image");
					common.waitForElement(UploadTabFeaturedImage);
					UploadTabFeaturedImage.click();
					projectPage.SelectFilesButton.click();
					Thread.sleep(2000);
					//WebElementFacade SelectFilesButton = find(By.xpath(".//a[text()='Select Files']"));
					//upload(MediaPath).to(SelectFilesButton);
					//Thread.sleep(2000);
					//common.upload(getDriver(), MediaPath);
					autoITScriptPath = System.getProperty("user.dir")+"\\AutoIT\\UploadImage.exe";
					Runtime.getRuntime().exec(autoITScriptPath);
					Thread.sleep(4000);
					report.Info("Uploaded Image");					
					common.WaitForObjectVisibility(".//*[@id='ckeditor_filescke_1']/div[3]/div/div[2]");
					//Thread.sleep(5000);
					WebElementFacade NextButton = find(By.xpath(".//span[text()='Next'][@class='cke_dialog_ui_button']"));
					common.clickElement( NextButton);
					report.Info("Clicked on Next Button replace Image ");
					//findElement(driver, "xpath", "FeaturedImageConfirmation").click();
					WebElementFacade Apply = find(By.xpath(".//a[@title='Apply']"));
					common.waitForElement(Apply);				
					common.clickElement( Apply);
				}
				if(composeSubAction.equals("PostWithVideo")|| (logicalName.equals("CreatesAPost"))){
					WebElementFacade ReplaceVideo = find(By.xpath(".//div[text()='Replace Video']"));
					common.scrollToView( ReplaceVideo);
					common.waitForElement(ReplaceVideo);
					common.clickElement( ReplaceVideo);
					report.Info("Clicked on ReplaceVideo Button");
					WebElementFacade UploadVideoTab = find(By.xpath(".//div[text()='Upload' and contains(@class, 'mg-tabs-tab')]"));			
					UploadVideoTab.click();
					common.waitForElement(projectPage.SelectFilesButton);
					Thread.sleep(1000);
					projectPage.SelectFilesButton.click();				
					Thread.sleep(1000);
					//String VideoFilePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\SampleVideo.mp4";
					//common.upload(getDriver(), VideoFilePath);
					autoITScriptPath = System.getProperty("user.dir")+"\\AutoIT\\UploadVideo.exe";
					Runtime.getRuntime().exec(autoITScriptPath);
					Thread.sleep(3000);
					WebElementFacade postOKButton = find(By.xpath(".//button[text()='Ok']"));
					common.waitForElement(postOKButton);
					Thread.sleep(3000);
					postOKButton.click();
					report.Info("Clicked on postOKButton");
				}
				if (composeSubAction.equalsIgnoreCase("PostAsAnnouncement")) {
					WebElementFacade markAnnouncementCheckBox = find(By.xpath(".//label[contains(@for,'announcement')]"));
					common.clickElement( markAnnouncementCheckBox);
					//report.Info("Clicked on announcement option of the post");
				}
				if (composeSubAction.equalsIgnoreCase("MustReadPost")) {
					WebElementFacade markAnnouncementCheckBox = find(By.xpath(".//label[@for='post_is_department_must_read']"));
					common.clickElement( markAnnouncementCheckBox);
					//report.Info("Clicked on Must Read option of the post");
				}
				if (composeSubAction.equalsIgnoreCase("ScheduledPost")) {
					WebElementFacade scheduleThisPostCheckBox = find(By.xpath(".//label[@for='post_enable_schedule']"));
					common.clickElement( scheduleThisPostCheckBox);					
					//report.Info("Clicked on schedule option of the post");
					WebElementFacade postScheduleDate = find(By.xpath(".//input[@id='post_schedule_date']"));
					common.waitForElement(postScheduleDate);
					//mouseHover(driver, findElement(driver, "id", "postScheduleDate"));
					Thread.sleep(1000);
					postScheduleDate.click();
					//report.Info("Clicked on calander option of the schedule post");
					Thread.sleep(1000);
					common.datePicker("5");
					WebElementFacade schedulePostButton = find(By.xpath(".//button[@id='site_post_schedule']"));
					schedulePostButton.click();
				}else {
					WebElementFacade PublishPostButton = $(".//button[@data-state='Published']");
					//common.scrollToView( PublishPostButton);
					Thread.sleep(500);
					//common.waitForElement(PublishPostButton);
					//common.clickElement( PublishPostButton);
					PublishPostButton.waitUntilEnabled();
					PublishPostButton.click();
					report.Info("Clicked on publish Post Button");
					if(logicalName.contains("Alert")) {
						WebElementFacade publishNowButton = $(".//button[text()='Publish Now']");
						publishNowButton.click();
					}else {						
						WebElementFacade yesConfirmButton = $(".//button[text()='Yes']");
						//common.waitForElement(yesConfirmButton);
						Thread.sleep(1000);
						yesConfirmButton.click();
					}
				}
				break;
			case "WIKIS":
				//ComposeOption = find(By.xpath(ComposeOptionsPath));//Creates Compose option on the fly based on excel input>composeAction	\				
				if(parentActionCount > 0) {
					common.waitForElement(parentActionToHover);
					report.Info("Compose Box is open now");				
					//parentActionToHover = find(By.xpath(parentActionPath));//Creates Parent Action option to hover on the fly based on excel input>parentAction
					common.mouseHover( parentActionToHover);
					parentActionToHover.click();
				}else {
					composeAction = tableMap.get("composeAction").get(i);
					ComposeOptionsPath =".//a[contains(text(),'"+composeAction+"')]";
					ComposeOption = find(By.xpath(ComposeOptionsPath));
				}
				common.mouseHover( ComposeOption);
				ComposeOption.click();
				report.Info("Clicked on Wiki Successfully");				
				break;
			case "TRACKER":
				String columnsToCreate = tableMap.get("columnsToCreate").get(i);
				String trackerTemplateToCreate = tableMap.get("trackerTemplateToCreate").get(i);
				String behaviour = tableMap.get("behaviour").get(i);

				if(parentActionCount > 0) {
					common.waitForElement(parentActionToHover);
					report.Info("Compose Box is open now");				
					//parentActionToHover = find(By.xpath(parentActionPath));//Creates Parent Action option to hover on the fly based on excel input>parentAction
					common.mouseHover( parentActionToHover);
					parentActionToHover.click();					
				}else {
					composeAction = tableMap.get("composeAction").get(i);
					ComposeOptionsPath =".//a[contains(text(),'"+composeAction+"')]";
					ComposeOption = find(By.xpath(ComposeOptionsPath));
				}
				common.mouseHover( ComposeOption);
				ComposeOption.click();
				report.Info("Clicked on TRACKER Successfully");

				WebElementFacade trackerTemplate = find(By.xpath(".//div[@title='"+trackerTemplateToCreate+"']"));
				trackerTemplate.click();
				Thread.sleep(1000);
				//mouseHover(driver, template);				
				//clickElement(driver, template);
				report.Info("Selected the template as needed");				
				WebElementFacade trackerContinueButton = find(By.xpath(".//a[@id='new_tracker_by_template']"));
				trackerContinueButton.click();
				report.Info("Clicked on Continue");

				WebElementFacade trackerName = find(By.xpath(".//input[@id='trackername']"));
				String trackerNameToEnter = "Automation Tracker"+dateName;
				trackerName.type(trackerNameToEnter);				
				report.Info("Entered Tracker Name as: "+trackerNameToEnter);		

				WebElementFacade teamTo = find(By.xpath(".//input[@id='token-input-myFormTeams']"));
				teamTo.click();
				teamTo.sendKeys("Automation");
				Thread.sleep(2000);
				teamTo.sendKeys(Keys.TAB);

				WebElementFacade createTrackerButton = find(By.xpath(".//button[@id='create-tracker-v2']"));
				evaluateJavascript("document.querySelector(\"button[id='create-tracker-v2']\").click()");
				//createTrackerButton.click();
				WebElementFacade createdTracker = find(By.xpath(".//span[@id='tracker-v2-name'][contains(text(),'"+trackerNameToEnter+"')]"));
				common.WaitForObjectVisibility(".//span[@id='tracker-v2-name'][contains(text(),'"+trackerNameToEnter+"')]");
				createdTracker.shouldBeVisible();
				report.Info(trackerNameToEnter + " Tracker creation is successful");
				if(logicalName.contains("Column")) {
					createTrackerColumns(columnsToCreate, behaviour, logicalName);
				}
				break;
			case "EVENT":
				if(parentActionCount > 0) {
					common.waitForElement(parentActionToHover);
					report.Info("Compose Box is open now");				
					//parentActionToHover = find(By.xpath(parentActionPath));//Creates Parent Action option to hover on the fly based on excel input>parentAction
					common.mouseHover( parentActionToHover);
					parentActionToHover.click();
				}
				common.mouseHover( ComposeOption);
				ComposeOption.click();
				report.Info("Clicked on Event Successfully");

				WebElementFacade eventTitle = find(By.xpath(".//input[@id='event_title']"));
				eventTitle.waitUntilVisible();				
				WebElementFacade SelectEventType = find(By.xpath(".//ul[@id='events-list']/li/a[text()='"+eventType+"']"));
				//if(logicalName.equals("CreatesACustomEvent")){
				List<WebElementFacade> SelectEventTypeList = findAll(By.xpath(".//ul[@id='more-events-dropdown']/li/a[text()='"+eventType+"']"));
				int eventCount = SelectEventTypeList.size();
				if(eventCount>0) {
					common.clickElement( find(By.xpath(".//ul[@id='more-events-dropdown']/li/a[text()='"+eventType+"']")));	
				}

				//}
				else {
					SelectEventType.click();
				}

				Thread.sleep(1000);
				eventTitle.waitUntilVisible();
				eventTitle.click();
				eventTitle.sendKeys("This event is to test Event flow");				
				report.Info("Entered the event title Successfully");
				WebElementFacade eventLocation = find(By.xpath(".//input[@id='event_location']"));
				eventLocation.sendKeys("Available Conference Room");				
				report.Info("Entered the event Location Successfully");

				if(!eventType.contains("Outing")) {
					WebElementFacade startTimeDropDown = $(".//span[@id='select2-event_select_start_hour_start_time_5i-container']");
					startTimeDropDown.click();
					WebElementFacade select1145 = $(".//ul[@id='select2-event_select_start_hour_start_time_5i-results']/li[1]");
					common.waitForElement(select1145);
					select1145.click();
				}

				if(recurrance.equalsIgnoreCase("Yes")) {
					WebElementFacade eventRecurrence = find(By.xpath(".//label[@for='onoffswitch_reminder']"));
					evaluateJavascript("document.querySelector(\"label[for='onoffswitch_reminder']\").click()");
					//eventRecurrence.click();
					report.Info("Set the Event Recurrence Successfully");
				}

				if(shareWith.equalsIgnoreCase("Team")) {
					WebElementFacade addEventAttendees =null;
					if(eventType.contains("Outing")) {
						addEventAttendees = find(By.xpath(".//input[@id='token-input-team_event']"));
						addEventAttendees.sendKeys("MangoApps");
						Thread.sleep(2000);
						addEventAttendees.sendKeys(Keys.TAB);
						report.Info("Entered the event attendees Successfully");
					}//else {
					//addEventAttendees = find(By.xpath(".//input[@id='token-input-invite_user_on_step_3']"));
					//addEventAttendees.sendKeys("Mango Robo");
					//}
				}

				//WebElementFacade createEventButton = find(By.xpath(".//button[@id='event_button']/span/span"));	
				WebElementFacade createEventButton = $(".//button[@id='event_button']");
				common.clickElement( createEventButton);
				report.Info("Clicked on create Event");
				WebElementFacade eventSuccessNotification =find(By.xpath(".//*[@id='notice']"));
				eventSuccessNotification.shouldBeVisible();
				report.Info("Event created successfully");
				break;
			}
		}
	}


	@FindBy(xpath=".//a[@class='actionbutton actionred waves-effect waves-light left-0']")
	public WebElementFacade postTools;
	//a[@title='Post Tools']

	@Step
	public void checkPostFromCompose(String logicalName) throws UnsupportedFlavorException, IOException, InterruptedException {
		Thread.sleep(3000);
		if (getDriver().getCurrentUrl().contains("posts")) {
			String expectedPostTitle =  Serenity.sessionVariableCalled("expectedPostTitle");
			WebElementFacade actualPost=$(".//*[contains(text(),'"+expectedPostTitle+"')]");
			common.WaitForObjectVisibility(".//a[@title='Company Tools']");
			//(".//div[@id='notice'][contains(text(),'successfully')]");			
			//Thread.sleep(2000);
			if (actualPost.isDisplayed()) {
				// scrollToView(driver, actualPost);

				if(!(expectedPostTitle.contains("ALERT"))){
					//getDriver().navigate().refresh();
					Thread.sleep(7500);					
					$(".//*[contains(text(),'"+expectedPostTitle+"')]").waitUntilClickable();
					common.clickElement($(".//*[contains(text(),'"+expectedPostTitle+"')]"));
					//$(".//*[contains(text(),'"+expectedPostTitle+"')]").click();
					//$(".//span[contains(text(),'" + expectedPostTitle + "')]").click();
					common.waitForElement(postTools);
					postTools.click();
					WebElementFacade copyPostLinkOption = find(By.xpath(".//a[@class='copylink_wof relative copyPostLink']"));
					common.waitForElement(copyPostLinkOption);
					copyPostLinkOption.click();
					Serenity.setSessionVariable("postURL").to(common.onPaste());//Sets session variable postURL to use all over the tests
					WebElementFacade closePostButton = find(By.xpath(".//span[@title='Close']"));
					closePostButton.click();
				}
				if(logicalName.contains("MustRead")) {
					WebElementFacade mustReadPost = $("(.//span[text()='MUST READ'])[1]");
					common.WaitForObjectVisibility("(.//span[text()='MUST READ'])[1]");
					mustReadPost.shouldBeVisible();
					report.Info("Must Read post is visible");
				}

				report.Info("Post has been created and posted Successfully");
			}
		}
	}


	@FindBy(xpath=".//input[@class='wikis_subject']")
	public WebElementFacade WikiTitleTextBox;

	@Step
	public void wikiCreation() throws InterruptedException, UnsupportedFlavorException, IOException {


		common.WaitForObjectVisibility(".//input[@class='wikis_subject']");
		String wikiTitle = "Test Automation Wiki " + System.currentTimeMillis();
		Thread.sleep(2000);
		WikiTitleTextBox.sendKeys(wikiTitle);

		report.Info("Entered the Title of the Wiki as: " + wikiTitle);

		try {
			WebElementFacade wikiToTeam = find(By.xpath(".//input[@id='token-input-myTeams']"));		
			wikiToTeam.sendKeys("MangoApps");
			Thread.sleep(2000);
			wikiToTeam.sendKeys(Keys.TAB);
			report.Info("Entered the Team Name to send Wiki To");
		} catch (Exception e) {
			report.Info("Team Option is not available " + e.toString());
			getDriver().navigate().refresh();
			Thread.sleep(3000);
			Assert.assertTrue(false);
		}

		WebElementFacade wikiTemplate = find(By.xpath(".//li[@data-template='project_proposal']"));
		common.clickElement( wikiTemplate);
		report.Info("Selected Desire Template for Wiki");

		WebElementFacade createWikiButton = find(By.xpath(".//span[contains(text(),'Create')]"));
		common.clickElement( createWikiButton);
		report.Info("Clicked on create Wiki Button");

		WebElementFacade CreatedWiki = find(By.xpath("(.//input[@class='wk-title page-title wiki_title_text'][contains(@value,'Test Automation Wiki')])[1]"));
		//common.waitForElement(CreatedWiki);
		CreatedWiki.shouldBePresent();

		WebElementFacade SaveAndPublishButton = find(By.xpath(".//button[@id='wiki_save_button']"));
		Thread.sleep(1000);
		common.clickElement( SaveAndPublishButton);		
		report.Info("Clicked on Publish Wiki Button");
		
		common.WaitForObjectVisibility(".//span[contains(@title,'Test Automation Wiki')]");
		WebElementFacade PublishedWiki = find(By.xpath(".//span[contains(@title,'Test Automation Wiki')]"));
		PublishedWiki.shouldBeVisible();
		report.Info("Published Wiki is Visible");

		WebElementFacade wikiTools = find(By.xpath(".//span[contains(@id, 'page_options_link')]"));
		common.waitForElement(wikiTools);
		wikiTools.click();
		report.Info("Clicked on WikiTools");
		WebElementFacade getWikiLink = find(By.xpath(".//li[@class='get_wiki_link']/a[contains(text(),'Get Wiki Link')]"));
		common.waitForElement(getWikiLink);
		common.clickElement( getWikiLink);
		//getWikiLink.click();
		Thread.sleep(1000);
		Serenity.setSessionVariable("wikiURL").to(common.onPaste());//Sets session variable postURL to use all over the tests		 
		report.Info("Wiki URL is: " + common.onPaste());
	} 

	/*
	 * @FindBy(xpath=".//input[@class='wikis_subject']") public WebElementFacade
	 * WikiTitleTextBox;
	 */

	@Step
	public void createCustomEventCheck(String logicalName) throws IOException, InterruptedException {

		String filePath = "\\src\\test\\resources\\TestData\\TestData.xlsx";
		String dataSheet = "Domain";
		//	Map <String,Map<Integer, String>> tableMap = ReadWorkbook.readRow(logicalName, filePath, dataSheet);		
		//readWorkbook.testData(tableMap);
		//for (int i = 0  ;i < tableMap.get("Row").size();i++) {

		String DomainURL = Serenity.sessionVariableCalled("domainURL").toString();
		getDriver().navigate().to(DomainURL+"/admin/event_types");

		report.Info("Entered into event management module");

		if (getDriver().findElements(By.xpath(".//span[text()='Outing Trip plan']")).size() != 0) {
			WebElementFacade enableCustomEvent = find(By.xpath("//span[text()='Outing Trip plan']/following::input[contains(@id, 'event_type')]"));
			common.scrollToPageEnd();
			report.Info("Scrolled on to custom event toggle button");

			if (enableCustomEvent.getAttribute("data-enable").equalsIgnoreCase("false")) {
				common.clickElement( enableCustomEvent);
				report.Info("Clicked on toggle button of custom event");
			} else {
				report.Info("toggle button of custom event is already enabled");
			}
		} else {
			WebElementFacade addCustomEvent =  find(By.xpath(".//a[@id='addCustomEvent']"));
			common.waitForElement(addCustomEvent);
			addCustomEvent.click();
			report.Info("Clicked on add custom event");
			WebElementFacade eventName =  find(By.xpath(".//input[@id='event_type_name']"));
			eventName.waitUntilVisible();
			eventName.sendKeys("Outing Trip plan");
			WebElementFacade saveEventButton =  find(By.xpath(".//button[@id='submitEventType']"));
			saveEventButton.click();
			WebElementFacade enableCustomEvent = find(By.xpath("//span[text()='Outing Trip plan']/following::input[contains(@id, 'event_type')]"));
			common.scrollToPageEnd();
			report.Info("Scrolled on to custom event toggle button");
			common.clickElement( enableCustomEvent);
			report.Info("Clicked on toggle button of custom event");
		}
	}
	//}

	@FindBy(xpath=".//input[@id='global_search_box']") 
	public WebElementFacade  GlobalSearchBox;
	/*
	 * @FindBy(xpath=".//div[contains(text(),'results for ')]/span[text()='Test']")
	 * public WebElementFacade SearchedTextresult;
	 */
	@Step
	public void searchResultPaginationCheck(String logicalName) throws InterruptedException, IOException {

		String filePath = "\\src\\test\\resources\\TestData\\TestData.xlsx";
		String dataSheet = "Domain";
		Map <String,Map<Integer, String>> tableMap = ReadWorkbook.readRow(logicalName, filePath, dataSheet);		
		readWorkbook.testData(tableMap);
		for (int i = 0  ;i < tableMap.get("Row").size();i++) {

			String textToSearch = tableMap.get("TextToSearch").get(i);
			String filterType  =  tableMap.get("filterType").get(i);
			String filterTypePath = ".//a[@filter='"+filterType+"']";

			String DomainURL = Serenity.sessionVariableCalled("domainURL").toString();
			getDriver().navigate().to(DomainURL);
			report.Info( "Entered to the Landing Page of the domain");
			globalSearch(textToSearch);

			WebElementFacade FilterBy = find(By.xpath(filterTypePath));//Creates object on the fly based on filterType value from excel

			FilterBy.click();
			filterType = filterType.toLowerCase();

			List<WebElement> NoOfPages = getDriver().findElements(By.xpath(".//div[@id='searchPagination']/div/div/a[contains(@href,'search_in="+filterType+"&')]"));
			int pageCount = NoOfPages.size();

			//	boolean NextButtonState = false;
			//	WebElementFacade NextButton =  find(By.xpath(" .//div[@id='searchPagination']/div/div/a[contains(@href,'search_in="+filterType+"&')][text()='Next']"));
			//WebElementFacade NextButtonDisabled =  find(By.xpath(" .//div[@id='searchPagination']/div/div/span[@class='next_page disabled']"));
			for(int l= 1;l < pageCount;l++) {				
				//Thread.sleep(5000);
				report.Info("global search Pagination for :- "+l+" out of:- "+pageCount);
				WebElementFacade NextButton =  find(By.xpath(".//div[@id='searchPagination']/div/div/a[contains(@href,'search_in="+filterType+"&')][text()='Next']"));
				common.scrollToView(NextButton);
				Thread.sleep(2000);
				common.clickElement( NextButton);
				Thread.sleep(2000);	

			}
			report.Info("global search Pagination for "+pageCount+" search pages for "+filterType+"  is successful");
		}
	}

	@Step
	public void logoutCheck() throws InterruptedException, IOException {


		usersPage.userOptions("logout");
		report.Info("Successfully logged out of the application");
	}

	@Step
	public void chatWindowCheck(String logicalName) throws IOException, InterruptedException {

		String filePath = "\\src\\test\\resources\\TestData\\TestData.xlsx";
		String dataSheet = "Domain";
		Map <String,Map<Integer, String>> tableMap = ReadWorkbook.readRow(logicalName, filePath, dataSheet);		
		readWorkbook.testData(tableMap);
		for (int i = 0  ;i < tableMap.get("Row").size();i++) {


			String filterType  =  tableMap.get("filterType").get(i);
			String filterTypePath = ".//a[@filter='"+filterType+"']";

			String DomainURL = Serenity.sessionVariableCalled("domainURL").toString();
			getDriver().navigate().to(DomainURL);

			WebElementFacade chatPeople = find(By.xpath(".//div[@id='mango-online-tab-button']"));
			chatPeople.click();
			WebElementFacade findPeople = find(By.xpath(".//div[@id='mango-member-list']/div/div/input"));

			String user = Serenity.sessionVariableCalled("user").toString();
			String admin = Serenity.sessionVariableCalled("admin").toString();
			if(logicalName.equals("AdminChat")) {
				findPeople.sendKeys(user);
				WebElementFacade testUser = find(By.xpath(".//div[@class='item-status']/strong[text()='"+user+"']"));

				/*	if(testUser.isDisplayed()){					
					common.WaitForObjectVisibility(".//div[@class='item-status']/strong[text()='Test User1']");
					testUser.click();


				}*///else {
				WebElementFacade searchAllUsers = find(By.xpath(".//li[@id='mango-search-on-server']/a/div[@class='item-status']/strong"));
				searchAllUsers.waitUntilVisible();
				searchAllUsers.click();
				Thread.sleep(1000);
				common.WaitForObjectVisibility(".//div[@class='item-status']/strong[text()='"+user+"']");
				common.clickElement(testUser);
				//testUser.click();
				//}
				report.Info("Opened Test user's chat window");
				Thread.sleep(2000);
				WebElementFacade testUserChatWindow = find(By.xpath(".//textarea[@data-user-name='"+admin+"']"));
				common.WaitForObjectVisibility(".//textarea[@data-user-name='Admin last']");
				$(".//textarea[@data-user-name='"+admin+"']").sendKeys("Hello this is admin", Keys.ENTER);
				report.Info("sent chat message to second user");
			}else if(logicalName.equals("TestUserChat")) {
				findPeople.sendKeys(admin);
				WebElementFacade adminUser = find(By.xpath(".//div[@class='item-status']/strong[text()='"+admin+"']"));		
				//Thread.sleep(1000);

				if(adminUser.isVisible()){					
					//common.WaitForObjectVisibility(".//div[@class='item-status']/strong[text()='Admin last']");
					//Thread.sleep(1000);
					$(".//div[@class='item-status']/strong[text()='"+admin+"']").click();

				}else {
					WebElementFacade ContinueSearchOnServer = find(By.xpath(".//div[@class='item-status']/strong[text()='Continue Search on Server?']"));
					ContinueSearchOnServer.waitUntilVisible();
					ContinueSearchOnServer.click();
					common.WaitForObjectVisibility(".//div[@class='item-status']/strong[text()='"+admin+"']");
					$(".//div[@class='item-status']/strong[text()='"+admin+"']").click();
					Thread.sleep(1000);
				}
				report.Info("Opened Admin user's chat window");
				WebElementFacade adminUserChatWindow = find(By.xpath(".//textarea[@data-user-name='"+user+"']"));
				common.WaitForObjectVisibility(".//textarea[@data-user-name='"+user+"']");
				Thread.sleep(1000);
				adminUserChatWindow.sendKeys("Hello admin, this is Test User", Keys.ENTER);
				report.Info("sent chat message to Admin user");
			}
		}
	}

	@Step
	public void globalSearch(String textTosearch) throws InterruptedException {


		Actions myAct = new Actions(getDriver());
		common.waitForElement(GlobalSearchBox);
		GlobalSearchBox.click();
		//myAct.sendKeys(Keys.chord(Keys.SHIFT + "S")).build().perform();
		Thread.sleep(1000);
		GlobalSearchBox.sendKeys(textTosearch, Keys.ENTER);
		report.Info("Entered the text String in Global search Box");
		common.WaitForObjectVisibility(".//div[contains(text(),'results for ')]/span\"");
		//WebElementFacade SearchedTextresult = find(By.xpath(".//div[contains(text(),'results for ')]/span[text()='"+textTosearch+"']"));
		WebElementFacade SearchedTextresult = find(By.xpath(".//div[contains(text(),'results for ')]/span"));
		Thread.sleep(3000);
		SearchedTextresult.shouldBeVisible();
		report.Info("global search for given text "+textTosearch+" is successful");
	}
	@Step
	public void fileDeepSearchCheck() throws InterruptedException {

		String DomainURL = Serenity.sessionVariableCalled("domainURL").toString();
		getDriver().navigate().to(DomainURL);
		report.Info("Entered to the Landing Page of the domain");

		globalSearch("this is to test automation flow");		

		WebElementFacade desiredFile = find(By.xpath("(.//ul[@id='search-results-container']/li/div[2]/span/a)[1]/span"));
		Thread.sleep(6000);
		common.WaitForObjectVisibility("(.//ul[@id='search-results-container']/li/div[2]/span/a)[1]/span");
		//common.scrollToView( desiredFile);

		if (desiredFile.getText().contains("sample")) {
			report.Info("File Deep Search is Successful");			
		} else {
			report.Info("Entered Text Doesn't Match to any File");
			Assert.assertTrue(false);
		}
	}

	@Step
	public void ViewCounterCheck(String logicalName) throws InterruptedException {
		boolean functionVerify = false;
		boolean functionVerify1 = false;
		boolean functionVerify2 = false;

		String DomainURL = Serenity.sessionVariableCalled("domainURL").toString();


		String postURL =Serenity.sessionVariableCalled("existingPostURL").toString();
		String wikiURL =Serenity.sessionVariableCalled("existingWikiURL").toString();
		String pageURL = Serenity.sessionVariableCalled("existingPageURL").toString();

		getDriver().navigate().to(DomainURL);
		getDriver().navigate().to(postURL);
		WebElementFacade playAudioBar = find(By.xpath(".//div[@class='mp-controls']"));
		common.WaitForObjectVisibility(".//div[@class='mp-controls']");
		getDriver().navigate().refresh();
		common.WaitForObjectVisibility(".//span[@class='post_count_text']");

		WebElementFacade postViewCount = $(".//span[@class='post_count_text']");
		common.waitForElement(postViewCount);
		Thread.sleep(2000);
		String viewcount = $(".//span[@class='post_count_text']").getText();
		viewcount= viewcount.replaceAll("[^0-9]", "");
		int postviewcount=Integer.parseInt(viewcount);

		report.Info("Initial post view count is:- "+postviewcount);

		getDriver().navigate().refresh();
		common.waitForElement(postViewCount);
		Thread.sleep(4000);
		viewcount = $(".//span[@class='post_count_text']").getText();
		viewcount= viewcount.replaceAll("[^0-9]", "");
		int postviewcountafterRefresh=Integer.parseInt(viewcount);
		report.Info("post view count after refresh is:- "+postviewcountafterRefresh);
		if(postviewcountafterRefresh==postviewcount+1){
			report.Info("post view count incresed after refresh is:- "+postviewcountafterRefresh);
			functionVerify = true;
		}else {
			report.Info("Failed - post view count after refresh is:- "+postviewcountafterRefresh);
			functionVerify = false;
		}

		getDriver().navigate().refresh();		
		common.waitForElement(postViewCount);
		Thread.sleep(4000);
		viewcount = $(".//span[@class='post_count_text']").getText();
		viewcount= viewcount.replaceAll("[^0-9]", "");
		postviewcountafterRefresh=Integer.parseInt(viewcount);
		report.Info("post view count after refresh is:- "+postviewcount);
		if(postviewcountafterRefresh==postviewcount+2){
			report.Info("post view count incresed after refresh is:- "+postviewcountafterRefresh);
			functionVerify = true;
		}else {
			report.Info("Failed - post view count after refresh is:- "+postviewcountafterRefresh);
			functionVerify = false;
		}

		getDriver().get(wikiURL);
		WebElementFacade wikiView = $(".//a[@class='view_count_reader refresh-count']/span");
		common.waitForElement(wikiView);
		Thread.sleep(4000);
		String Wikiviewcount = wikiView.getText();
		Wikiviewcount= Wikiviewcount.replaceAll("[^0-9]", "");
		int wikiviewcount=Integer.parseInt(Wikiviewcount);
		report.Info("Initial wiki view count is:- "+wikiviewcount);

		getDriver().navigate().refresh();
		common.waitForElement(wikiView);
		Thread.sleep(4000);
		Wikiviewcount = $(".//a[@class='view_count_reader refresh-count']/span").getText();
		Wikiviewcount= Wikiviewcount.replaceAll("[^0-9]", "");
		int wikiviewcountAfterRefresh=Integer.parseInt(Wikiviewcount);
		report.Info("wiki view count after refresh is:- "+wikiviewcountAfterRefresh);
		if(wikiviewcountAfterRefresh==wikiviewcount+1){
			report.Info("wiki view count incresed after refresh is:- "+wikiviewcountAfterRefresh);
			functionVerify1 = true;
		}else {
			report.Info("Failed - wiki view count after refresh is:- "+wikiviewcountAfterRefresh);
			functionVerify1 = false;
		}

		getDriver().navigate().refresh();
		common.waitForElement(wikiView);
		Thread.sleep(4000);
		Wikiviewcount = $(".//a[@class='view_count_reader refresh-count']/span").getText();
		Wikiviewcount= Wikiviewcount.replaceAll("[^0-9]", "");
		wikiviewcountAfterRefresh=Integer.parseInt(Wikiviewcount);
		report.Info("wiki view count after refresh is:- "+wikiviewcountAfterRefresh);
		if(wikiviewcountAfterRefresh==wikiviewcount+2){
			report.Info("wiki view count incresed after refresh is:- "+wikiviewcountAfterRefresh);
			functionVerify1 = true;
		}else {
			report.Info("Failed - wiki view count after refresh is:- "+wikiviewcountAfterRefresh);
			functionVerify1 = false;
		}


		getDriver().get(pageURL);		
		WebElementFacade pageView = $(".//a[@class='view_count_reader refresh-count']/span");
		common.waitForElement(pageView);
		Thread.sleep(4000);
		String Pageviewcount = $(".//a[@class='view_count_reader refresh-count']/span").getText();
		Pageviewcount= Pageviewcount.replaceAll("[^0-9]", "");
		int pageviewcount=Integer.parseInt(Pageviewcount.replaceAll("[^0-9]", ""));
		report.Info("Initial page view count is:- "+pageviewcount);

		getDriver().navigate().refresh();
		common.waitForElement(pageView);
		Thread.sleep(2000);
		Pageviewcount = $(".//a[@class='view_count_reader refresh-count']/span").getText();
		Pageviewcount= Pageviewcount.replaceAll("[^0-9]", "");
		int pageViewCountAfterRefresh=Integer.parseInt(Pageviewcount);
		report.Info("page view count after refresh is:- "+pageviewcount);
		if(pageViewCountAfterRefresh==pageviewcount+1){
			report.Info("page view count incresed after refresh is:- "+pageViewCountAfterRefresh);
			functionVerify2 = true;
		}else {
			report.Info("Failed - page view count after refresh is:- "+pageViewCountAfterRefresh);
			functionVerify2 = false;
		}

		getDriver().navigate().refresh();
		common.waitForElement(pageView);
		Thread.sleep(4000);
		Pageviewcount = $(".//a[@class='view_count_reader refresh-count']/span").getText();
		Pageviewcount= Pageviewcount.replaceAll("[^0-9]", "");
		pageViewCountAfterRefresh=Integer.parseInt(Pageviewcount);
		report.Info("page view count after refresh is:- "+pageViewCountAfterRefresh);
		if(pageViewCountAfterRefresh==pageviewcount+2){
			report.Info("page view count incresed after refresh is:- "+pageViewCountAfterRefresh);
			functionVerify2 = true;
		}else {
			report.Info("Failed - page view count after refresh is:- "+pageViewCountAfterRefresh);
			functionVerify2 = false;
		}

		if (functionVerify && functionVerify1 && functionVerify2) {
			report.Info("View count is successfully getting increased");
		}else {
			report.Info("View count is not getting increased , please check comments for each section");
			Assert.assertTrue(false);
		}

	}
	@Step
	public void navigationModuleOrderChangeCheck(String logicalName) throws IOException, InterruptedException {
		WebElement destination = null;
		String filePath = "\\src\\test\\resources\\TestData\\TestData.xlsx";
		String dataSheet = "Domain";
		Map <String,Map<Integer, String>> tableMap = ReadWorkbook.readRow(logicalName, filePath, dataSheet);		
		readWorkbook.testData(tableMap);
		for (int i = 0  ;i < tableMap.get("Row").size();i++) {
			String moduleOnTop  =  tableMap.get("moduleOnTop").get(i);
			String moduleToBeOnTop  =  tableMap.get("moduleToBeOnTop").get(i);
			String SubURL = tableMap.get("SubURL").get(i);
			String DomainURL = Serenity.sessionVariableCalled("domainURL").toString();
			getDriver().navigate().to(DomainURL+SubURL);
			common.WaitForObjectVisibility(".//button[@class='actionbutton waves-effect waves-dark actionblue waves-effect waves-light save apply_settings disable-event apply_navigation_btn']");
			List<WebElement> modules = getDriver().findElements(By.xpath(".//li[@class='module-list-item dd-item' or @class='module-list-item  dd-item']"));
			for (int moduleNo = 0; moduleNo < modules.size(); moduleNo++) {
				if (modules.get(moduleNo).getText().equalsIgnoreCase(moduleOnTop)) {
					destination = modules.get(moduleNo);
				}
				if (modules.get(moduleNo).getText().equalsIgnoreCase(moduleToBeOnTop)) {
					WebElement source = modules.get(moduleNo);
					Actions moveModule = new Actions(getDriver());
					common.mouseHover( source);
					Thread.sleep(2000);
					moveModule.clickAndHold(source);
					// moveModule.dragAndDrop(source, destination).build().perform();
					Thread.sleep(2000);
					moveModule.moveToElement(destination).build().perform();
					Thread.sleep(2000);
					report.Info("Moved the module "+moduleToBeOnTop+" on top");
					WebElementFacade ApplyAnvigation = find(By.xpath(".//button[@class='actionbutton waves-effect waves-dark actionblue waves-effect waves-light save apply_settings disable-event apply_navigation_btn']"));
					ApplyAnvigation.shouldNotBeVisible();

				}
			}
		}
	}

	@Step
	public void shortCutCreation(String logicalName) throws InterruptedException, IOException {

		String filePath = "\\src\\test\\resources\\TestData\\TestData.xlsx";
		String dataSheet = "Domain";
		Map <String,Map<Integer, String>> tableMap = ReadWorkbook.readRow(logicalName, filePath, dataSheet);		
		readWorkbook.testData(tableMap);
		for (int i = 0  ;i < tableMap.get("Row").size();i++) {

			String SubURL = tableMap.get("SubURL").get(i);
			String whenDropDownValue = tableMap.get("whenDropDownValue").get(i);
			String DomainURL = Serenity.sessionVariableCalled("domainURL").toString();
			getDriver().navigate().to(DomainURL+SubURL);

			WebElementFacade addCustomLink = find(By.xpath(".//span[text()='Add a Custom Link']"));
			common.waitForElement(addCustomLink);
			addCustomLink.click();
			report.Info("Clicked on add Add Custom Link Button");

			String customLabel = "AutoCustomLink"+dateName;

			WebElementFacade customLinkLabel = $(".//input[@id='link_name_value']");
			customLinkLabel.sendKeys(customLabel);
			report.Info("Entered custom Label");
			WebElementFacade conditionBasedLink = $(".//a[@data-anchor='#shortcut_step_menu2']");
			conditionBasedLink.click();
			Thread.sleep(3000);
			WebElementFacade whenDropDown = $(".//select[@id='condition_type']");
			whenDropDown.selectByValue(whenDropDownValue);
			report.Info("Selected Projects from Dropdown");
			Thread.sleep(1000);
			WebElementFacade equalsInputBox1 = $("(.//input[@id='token-input-'])[1]");
			equalsInputBox1.type("Automation Test");
			Thread.sleep(2000);			
			equalsInputBox1.sendKeys(Keys.ENTER);
			WebElementFacade thenCustomLinkIs = $("(.//input[@placeholder='Enter Link URL'])[1]");
			//String projectUrl = Serenity.sessionVariableCalled("projectURL");


			String projectUrl=Serenity.sessionVariableCalled("existingProjectUrl").toString();


			thenCustomLinkIs.typeAndTab(projectUrl);
			Thread.sleep(1000);
			report.Info("Enteretd custom link");
			WebElementFacade saveButton = $(".//span[text()='Save']");
			common.scrollToView( saveButton);
			common.clickElement( saveButton);
			//evaluateJavascript("document.querySelector(\"button[class='\").click()");
			//saveButton.click();
			report.Info("clicked Save");
			Thread.sleep(2000);
			WebElementFacade applyNavigation = $(".//*[@id='home_link_shortcut_container']/div[1]/div/div[1]/span/button/span/span");
			common.waitForElement(applyNavigation);
			Thread.sleep(3000);
			//			evaluateJavascript("document.querySelector(\"button[class='actionbutton waves-effect waves-dark actionblue waves-effect waves-light no-margin  save apply_settings apply_navigation_btn']\").click()");
			common.scrollToView( applyNavigation);
			//common.clickElement( applyNavigation);
			applyNavigation.click();
			report.Info("Clicked Apply NAvigation");
			WebElementFacade applyConfirmButton = $(".//button[@id='apply_reorder']");			

			common.waitForElement(applyConfirmButton);
			Thread.sleep(1000);
			common.WaitForObjectVisibility(".//button[@id='apply_reorder']");
			applyConfirmButton.click();			
			report.Info("Clicked Apply button");
			WebElementFacade applyConfirmButtonDisabled = $(".//button[@class='actionbutton waves-effect waves-dark actionblue waves-effect waves-light save apply_settings disable-event apply_navigation_btn']");
			applyConfirmButtonDisabled.shouldBeVisible();
			report.Info("Successfully added Custom Link");
			getDriver().navigate().to(DomainURL+"u");
			Thread.sleep(3000);
			getDriver().navigate().refresh();
			Thread.sleep(3000);
			report.Info("Refreshed the page");
			getDriver().navigate().refresh();
			Thread.sleep(3000);
			report.Info("Refreshed the page");

			WebElementFacade MoreLink = $(".//a[@class='toggle_more']");
			common.waitForElement(MoreLink);
			common.clickElement( MoreLink);
			//MoreLink.click();
			report.Info("Clicked More Link");

			WebElementFacade customLinkCreated = $(".//a[@href='"+ projectUrl +"']");
			common.waitForElement(customLinkCreated);
			customLinkCreated.click();
			report.Info("Clicked custom link created");

			//$(".//a[@href='"+ projectUrl +"']").shouldNotBePresent();
			//WebElementFacade customLinkCreated = $(".//i[@title='"+ customLabel +"']");
			//WebElementFacade customLinkCreated = $(".//a[@href='"+ projectUrl +"']");
			/*
			 * int k=0; while(!customLinkCreated.isDisplayed() || (k<3)) {
			 * getDriver().navigate().refresh(); Thread.sleep(2000); }
			 */
			/*	if(!customLinkCreated.isDisplayed()) {
				report.Info("Custom link is not displayed as LHS is blank");
				Assert.assertTrue(false);
			}else {
				common.waitForElement(customLinkCreated);
				customLinkCreated.click();
				report.Info("Clicked custom link created");
			}*/

			WebElementFacade reDirectedProject = $(".//*[@id='project_breadcrum_container']/div[1]/ul/li[2]/div/ul/li/span");
			if(reDirectedProject.getText().contains("MangoApps Automation Team")) {
				report.Info("Redirected to correct project via Custom Link");
			}else {
				report.Info("Not Redirected to correct project via Custom Link");
				Assert.assertTrue(false);
			}			
		}
	}

	@Step
	public void duplicateAWikiCheck(String logicalName) throws InterruptedException, IOException {

		/*String filePath = "\\src\\test\\resources\\TestData\\TestData.xlsx";
		String dataSheet = "Domain";
		Map <String,Map<Integer, String>> tableMap = ReadWorkbook.readRow(logicalName, filePath, dataSheet);		
		readWorkbook.testData(tableMap);
		for (int i = 0  ;i < tableMap.get("Row").size();i++) {

			String SubURL = tableMap.get("SubURL").get(i);
			String DomainURL = Serenity.sessionVariableCalled("domainURL").toString();
			getDriver().navigate().to(DomainURL+SubURL);*/

		String wikiURL = Serenity.sessionVariableCalled("existingWikiURL").toString();

		getDriver().get(wikiURL);
		report.Info("Wiki URL is: " + wikiURL);

		WebElementFacade wikiTools = find(By.xpath(".//span[contains(@id, 'page_options_link')]"));
		common.waitForElement(wikiTools);			
		wikiTools.click();
		report.Info("Clicked on wiki Tools");
		Thread.sleep(1000);
		WebElementFacade duplicateWiki = $(".//a[@id='duplicate_wiki']");
		common.clickElement( duplicateWiki);
		report.Info("Clicked on duplicate wiki option");
		WebElementFacade duplicateWikiTitle = $(".//input[@id='dup_wiki_title']");
		String duplicateTitle ="Automation Duplicate Wiki " + dateName;
		duplicateWikiTitle.sendKeys(duplicateTitle);
		report.Info("Entered the title of new Wiki");
		Thread.sleep(1000);
		WebElementFacade duplicateButton = $(".//span[text()='Duplicate']");
		evaluateJavascript("document.querySelector(\"button[id='submit_duplicate_wiki']\").click()");
		//duplicateButton.click();
		report.Info("Clicked on duplicate button");

		WebElementFacade SaveAndPublishButton = find(By.xpath(".//button[@id='wiki_save_button']"));
		common.waitForElement(SaveAndPublishButton);
		Thread.sleep(1000);
		common.clickElement( find(By.xpath(".//button[@id='wiki_save_button']")));		
		report.Info("Clicked on Publish Wiki Button");
		WebElementFacade PublishedWiki = find(By.xpath(".//span[contains(@title,'"+duplicateTitle+"')]"));
		common.waitForElement(PublishedWiki);
		PublishedWiki.shouldBeVisible();
		report.Info("Published duplicated Wiki is Visible");
		//}
	}

	@Step
	public void duplicateAPostCheck(String logicalName) throws InterruptedException {

		String postURL = Serenity.sessionVariableCalled("existingPostURL").toString();

		report.Info("URL of the post is: " + postURL);
		getDriver().get(postURL);
		WebElementFacade postToolsButton = $(".//span[contains(@id, 'page_options_link')]");
		common.waitForElement(postToolsButton);
		postToolsButton.click();
		report.Info("Clicked on post tools");
		WebElementFacade duplicatePostLink = $(".//li/a[@id='clone_post']");
		duplicatePostLink.waitUntilVisible().click();
		report.Info("Clicked on Duplicate post option");

		String expectedPostTitle="Duplicated Post "+dateName;
		WebElementFacade postTitleInputBox = $(".//input[@id='post_title']");
		common.waitForElement(postTitleInputBox);
		postTitleInputBox.sendKeys(expectedPostTitle);
		report.Info("Entered post title");

		WebElementFacade placeDropdown = $(".//select[@id='place_in']");
		placeDropdown.selectByVisibleText("For Everyone");
		WebElementFacade duplicateButton = $(".//span[text()='Duplicate']");
		duplicateButton.click();
		report.Info("Clicked on Duplicate button");

		WebElementFacade PublishPostButton = $(".//button[@name='commit'][@data-state='Published']");
		common.waitForElement(PublishPostButton);
		Thread.sleep(2000);
		common.clickElement( PublishPostButton);
		//PublishPostButton.click();
		report.Info("Clicked on publish Post Button");
		WebElementFacade confirmYesButton = $(".//button[text()='Yes']");
		common.WaitForObjectVisibility(".//button[text()='Yes']");
		confirmYesButton.click();
		report.Info("Clicked on Yes Button");	

		WebElementFacade duplicatedPost = $(".//header[contains(@title,'" + expectedPostTitle + "')]");
		common.waitForElement(duplicatedPost);
		duplicatedPost.shouldBeVisible();
		report.Info("Duplicated the given post successfully");	
	}

	@Step
	public void wikiTableOfContentsCheck(String logicalName) throws InterruptedException {

		String wikiURL = Serenity.sessionVariableCalled("existingWikiURL").toString();
		getDriver().get(wikiURL);
		WebElementFacade tableOfContent = $(".//div[@id='wkTblCntnts']");

		if (tableOfContent.getAttribute("class").equalsIgnoreCase("table_of_contents")) {
			report.Info("Currently the table of Contents is closed because class value is: "+ tableOfContent.getAttribute("class"));
			tableOfContent.click();
			report.Info("Clicked on the Table of Contents");
			Thread.sleep(2000);

			if (tableOfContent.getAttribute("class").equalsIgnoreCase("table_of_contents shown")) {
				report.Info("Now the table of Contents is Open because class value is: "+ tableOfContent.getAttribute("class"));
			}
		} else if (tableOfContent.getAttribute("class").equalsIgnoreCase("table_of_contents shown")) {
			report.Info("Currently the table of Contents is Open because class value is: "+ tableOfContent.getAttribute("class"));
			tableOfContent.click();
			report.Info("Clicked on the Table of Contents");
			Thread.sleep(2000);

			if (tableOfContent.getAttribute("class").equalsIgnoreCase("table_of_contents")) {
				report.Info("Now the table of Contents is Open because class value is: "+ tableOfContent.getAttribute("class"));
			}
		}
		report.Info("Passed sampleTest Successfully");
	}


	@Step
	public void verifyPostInsights(String logicalName) throws InterruptedException, IOException {		

		String filePath = "\\src\\test\\resources\\TestData\\TestData.xlsx";
		String dataSheet = "Domain";
		Map <String,Map<Integer, String>> tableMap = ReadWorkbook.readRow(logicalName, filePath, dataSheet);		
		readWorkbook.testData(tableMap);
		for (int i = 0  ;i < tableMap.get("Row").size();i++) {
			String viewUniqueCount = tableMap.get("viewUniqueCount").get(i);
			String boostPostVia = tableMap.get("boostPostVia").get(i);	
			String boostPostTo = tableMap.get("boostPostTo").get(i);	
			String downloadReport = tableMap.get("downloadReport").get(i);
			//String SubURL = tableMap.get("SubURL").get(i);
			//String DomainURL = Serenity.sessionVariableCalled("domainURL").toString();
			int uniqueViewCount = 0;int boostCount = 0;

			//getDriver().navigate().to(DomainURL+SubURL);
			//String  postURL="https://mangocustomer.mangoapps.com/mlink/post/MTE3NzQ5";
			String newpostURL =Serenity.sessionVariableCalled("postURL").toString();

			report.Info("Copied URL of the Post is: " + newpostURL);
			getDriver().navigate().to(newpostURL);
			common.WaitForObjectVisibility(".//span[@class='post_count_text']");
			report.Info("Navigated to post");

			if(!logicalName.equals("ViewInsightsUniqueUser")) {//this is just to open the post with other user
				WebElementFacade adminTools = $(".//a[@title='Admin Tools']");
				//
				//*[@id='post_tools_strip']/div[1]/ul/li/a
				common.waitForElement(adminTools);
				adminTools.click();
				common.WaitForObjectVisibility(".//ul[@class='page_options_menu'][@style='display: block;']/li/a[contains(text(),'View Insights')]");
				WebElementFacade viewIsightsOption = $(".//ul[contains(@style,'display: block;')]/li/a[contains(text(),'View Insights')]");
				// ".//ul[@class='page_options_menu'][@style='display: block;']/li/a[contains(text(),'View Insights')]");
				viewIsightsOption.click();
				common.WaitForObjectVisibility(".//a[@class='actionbutton boost_post boost_post_admin']");
				WebElementFacade uniqueViews = $("(.//div[@class='inst-viewbx'])[2]/div[2]");

				uniqueViewCount =Integer.parseInt(uniqueViews.getText());
				report.Info("Initial uniqueViewCount is:- "+uniqueViewCount);

				if(Serenity.sessionVariableCalled("uniqueViewCount") == null) {
					Serenity.setSessionVariable("uniqueViewCount").to(uniqueViewCount);
				}


				if(!(boostPostVia.equals(""))) {							

					WebElementFacade sent = $("(.//div[@class='inst-viewbx'])[1]/div[2]");
					int sentCount =Integer.parseInt(sent.getText());
					report.Info("Initial Sent count is:- "+sentCount);				

					WebElementFacade notViewed = $("(.//div[@class='inst-viewbx'])[3]/div[2]");
					int notViewedCount =Integer.parseInt(notViewed.getText());
					report.Info("Initial notViewedCount is:- "+notViewedCount);


					WebElementFacade numberOfBoosts = $("(.//div[@class='inst-viewbx'])[4]/div[2]");					

					boostCount =Integer.parseInt(numberOfBoosts.getText());
					report.Info("Initial boostCount is:- "+boostCount);
					//Serenity.setSessionVariable("boostCount").to(boostCount);	

					WebElementFacade boostPostButton = $(".//a[contains(@class,'actionbutton boost_post')][text()='Boost Post']");
					common.waitForElement(boostPostButton);
					boostPostButton.click();
					common.WaitForObjectVisibility(".//button[@id='boost_post_submit']");
					//Thread.sleep(2000);
					WebElementFacade boostPostViaRadioButton = $(".//label[text()='"+boostPostVia+"']");				
					boostPostViaRadioButton.click();
					report.Info("Selected "+boostPostVia);

					WebElementFacade toDropDown = $(".//select[@id='target_audience']");
					toDropDown.selectByValue(boostPostTo);//values to enter can be not_seen_audience/seen_audience/all_members
					report.Info("Selected "+boostPostTo);

					WebElementFacade subjectInputBox = $(".//input[@id='boost_email_subject']");
					subjectInputBox.sendKeys("Boosted");
					//Thread.sleep(1000);
					report.Info("Entered subject");
					//subjectInputBox.sendKeys(Keys.TAB);
					//Thread.sleep(1000);

					common.waitForElement(boostPostButton);
					evaluateJavascript("document.querySelector(\"button[id='boost_post_submit']\").click()");
					//common.clickElement( boostPostButton);
					//boostPostButton.click();
					//Thread.sleep(3000);
					common.WaitForObjectVisibility("(//*[@id='highcharts-0']/svg/g[4]/text");	
					getDriver().navigate().refresh();
					Thread.sleep(4000);
					common.WaitForObjectVisibility("(.//div[@class='inst-viewbx'])[4]/div[2]");			

					int boostCountAfterRefresh =Integer.parseInt($("(.//div[@class='inst-viewbx'])[4]/div[2]").getText());
					report.Info("boostCount after boost is:- "+boostCountAfterRefresh);

					if(boostCountAfterRefresh==boostCount+1) {
						report.Info("boostCount increased after boost");
					}else {
						report.Info("boostCount is not increased after boost");
						Assert.assertTrue(false);
					}


				}if(!(viewUniqueCount.equals(""))) {
					/*
					 * WebElementFacade
					 * postCloseButton=$("//*[@id='ms-overlay-wrapper']/div[1]/span");
					 * postCloseButton.click();
					 */
					int uniqueViewCountAfterRefresh =Integer.parseInt($("(.//div[@class='inst-viewbx'])[2]/div[2]").getText());
					report.Info("uniqueViewCount after unique view is:- "+uniqueViewCountAfterRefresh);

					uniqueViewCount = Serenity.sessionVariableCalled("uniqueViewCount");

					if(uniqueViewCountAfterRefresh==uniqueViewCount+1) {
						WebElementFacade uniqueviewCountLink = $(".//a[@class='post_audience'][contains(@data-href,'filter=viewed')]");
						uniqueviewCountLink.click();
						WebElementFacade uniquesViewCountOnDialog = $(".//h3[@class='dialog_title'][contains(text(),'"+uniqueViewCountAfterRefresh+"')]");
						common.WaitForObjectVisibility(".//h3[@class='dialog_title'][contains(text(),'"+uniqueViewCountAfterRefresh+"')]");
						uniquesViewCountOnDialog.shouldBeVisible();
						report.Info("uniqueViewCount increased after unique user view");
					}else {
						report.Info("uniqueViewCount is not increased after unique user view");
						Assert.assertTrue(false);
					}
				}if(!(downloadReport.equals(""))) {
					String postURL = Serenity.sessionVariableCalled("existingPostURL").toString();

					String downloadPath = System.getProperty("user.dir");
					WebElementFacade downloadReportButton = $(".//div/a[@class='actionbutton actionblue'][text()='Download Report']");
					downloadReportButton.click();
					Thread.sleep(4000);
					String fileName = $(".//div[@class='ma-h1']").getText();
					File getLatestFile = common.getLatestFilefromDir(downloadPath);
					String downloadedFileName = getLatestFile.getName();
					Assert.assertTrue("Failed to download Expected document",downloadedFileName.equals(fileName+".xlsx"));
					report.Info(fileName+".xlsx"+" Report File successfully downloaded");
					//common.isFileDownloaded(downloadPath, fileName+".xlsx");

				}
			}
		}
	}

	@Step
	public void clearCookies() throws InterruptedException {
		getDriver().manage().deleteAllCookies();
		Thread.sleep(8000);
	}
	@Step
	public void createTrackerColumns(String columnsToCreate , String behaviour ,String logicalName) throws InterruptedException {
		WebElementFacade addColumnsButton = $(".//span[text()='Add columns']");
		common.WaitForObjectVisibility(".//span[text()='Add columns']");
		addColumnsButton.click();				
		//String columnsToCreate = "Amount,Check Box,Date,Dropdown,Email,Phone Number,Radio Box,Text,URL";
		String[] colarr = columnsToCreate.split(Pattern.quote(","));
		int arrayLength = colarr.length;
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+arrayLength);
		int c=0;
		int n=0;
		for(c=0;c < colarr.length;c++) {
			WebElementFacade labelInputBox = $(".//input[@placeholder='Enter label name']");
			Thread.sleep(1000);			
			$(".//input[@name='tracker_column[name]']").type(colarr[c]);
			WebElementFacade columnDropdown = $(".//select[@name='tracker_column[data_type]']");
			Thread.sleep(700);
			$(".//select[@name='tracker_column[data_type]']").selectByVisibleText(colarr[c]);
			WebElementFacade defaultValue = $(".//input[@name='tracker_column[default]']");
			WebElementFacade defaultListValue = $(".//select[@name='tracker_column[default][]']");			
			WebElementFacade behaviourDropDown = $(".//a[@name='tracker_column[permission]']");

			WebElementFacade adminOnlyRadioBtn = $(".//label[@for='visibility_admin']");
			WebElementFacade createAndAddBtn = $(".//button[text()='Create & Add Another']");

			String colChoice = colarr[c].toUpperCase();
			switch (colChoice) {
			case "AMOUNT":
				defaultValue.sendKeys("200");			
				//adminOnlyRadioBtn.click();
				createAndAddBtn.click();
				common.WaitForObjectVisibility(".//input[@placeholder='Enter label name']");
				Thread.sleep(2000);
				break;
			case "CHECK BOX":	
				//common.waitForElement(defaultListValue);
				//Thread.sleep(1500);
				//common.scrollToView( $(".//select[@name='tracker_column[default][]']"));
				//WebElementFacade defaultChkBoxButton = $(".//button[@class='ui-multiselect ui-widget ui-state-default ui-corner-all ma-filter-multi-value']");
				//defaultChkBoxButton.click();
				//WebElementFacade checkAll = $(".//a[@class='ui-multiselect-all']");
				//checkAll.click();
				//WebElementFacade closeDropdownBtn = $(".//a[@class=ui-multiselect-close']");
				//common.waitForElement(closeDropdownBtn);
				//closeDropdownBtn.click();
				//	$(".//select[@name='tracker_column[default][]']").selectByValue("Choice1");
				common.clickElement( adminOnlyRadioBtn);
				////adminOnlyRadioBtn.click();
				$(".//button[text()='Create & Add Another']").click();
				common.WaitForObjectVisibility(".//input[@placeholder='Enter label name']");
				Thread.sleep(2000);
				break;
			case "DATE":				
				WebElementFacade dateFormat = $(".//select[@name='tracker_column[field_format]']");
				dateFormat.selectByValue("DD/MM/YY");
				WebElementFacade dateOfSubmission = $(".//label[@for='date_of_submission']");
				dateOfSubmission.click();
				Thread.sleep(700);
				//adminOnlyRadioBtn.click();
				$(".//button[text()='Create & Add Another']").click();
				common.WaitForObjectVisibility(".//input[@placeholder='Enter label name']");
				Thread.sleep(2000);
				break;
			case "DROPDOWN":				
				defaultListValue.selectByValue("Choice2");
				//adminOnlyRadioBtn.click();
				Thread.sleep(500);
				$(".//button[text()='Create & Add Another']").click();
				common.WaitForObjectVisibility(".//input[@placeholder='Enter label name']");
				Thread.sleep(2000);
				break;
			case "EMAIL":				
				$(".//input[@name='tracker_column[default]']").sendKeys("automation@mangoapps.com");
				//adminOnlyRadioBtn.click();
				Thread.sleep(500);
				$(".//button[text()='Create & Add Another']").click();
				common.WaitForObjectVisibility(".//input[@placeholder='Enter label name']");
				Thread.sleep(2000);
				break;
			case "PHONE NUMBER":				
				$(".//input[@name='tracker_column[default]']").sendKeys("9766224913");
				//adminOnlyRadioBtn.click();
				Thread.sleep(500);
				$(".//button[text()='Create & Add Another']").click();
				common.WaitForObjectVisibility(".//input[@placeholder='Enter label name']");
				Thread.sleep(2000);
				break;
			case "RADIO BOX":		
				$(".//select[@name='tracker_column[default][]']").selectByValue("Choice1");
				Thread.sleep(500);
				//adminOnlyRadioBtn.click();
				$(".//button[text()='Create & Add Another']").click();
				common.WaitForObjectVisibility(".//input[@placeholder='Enter label name']");
				Thread.sleep(2000);
				break;
			case "TEXT":				
				defaultValue.sendKeys("testText");
				//adminOnlyRadioBtn.click();
				Thread.sleep(500);
				$(".//button[text()='Create & Add Another']").click();
				common.WaitForObjectVisibility(".//input[@placeholder='Enter label name']");
				Thread.sleep(2000);
				break;
			case "URL":	
				defaultValue =$(".//input[@name='tracker_column[default][link]']");
				defaultValue.sendKeys("https://test.com");
				//adminOnlyRadioBtn.click();
				Thread.sleep(500);
				$(".//button[text()='Create']").click();
				//common.WaitForObjectVisibility(".//input[@placeholder='Enter label name']");
				//Thread.sleep(2000);
				break;
			case "USER LOOK AHEAD":		
				WebElementFacade defaultUserLookAhead = $(".//select[@name='tracker_column[default]']");
				defaultUserLookAhead.selectByValue("current_user");				
				if(!behaviour.equals("")) {
					behaviourDropDown.click();
					if(behaviour.equals("RequiredPrimary")) {
						$(".//li[@class='is_primary trackColumOpts position-relative']").click();
					}else if(behaviour.equals("Required")) {
						$(".//li[@class='required trackColumOpts position-relative']").click();
					}else if(behaviour.equals("ReadOnly")) {
						$(".//li[@class='read_only trackColumOpts position-relative']").click();
					}
				}
				//adminOnlyRadioBtn.click();
				Thread.sleep(500);
				$(".//button[text()='Create']").click();
				common.WaitForObjectVisibility(".//input[@placeholder='Enter label name']");
				Thread.sleep(2000);
				break;
			case "FILE ATTACHMENT":								
				//adminOnlyRadioBtn.click();
				Thread.sleep(500);
				$(".//button[text()='Create']").click();
				break;
			case "NUMBER":

				if(n==0) {
					$(".//input[@placeholder='Enter label name']").type("number1");
					defaultValue.sendKeys("20");
					n=1;
				}else if(n==1) {
					$(".//input[@placeholder='Enter label name']").type("number2");
					defaultValue.sendKeys("2");
				}				
				//adminOnlyRadioBtn.click();
				Thread.sleep(500);
				$(".//button[text()='Create & Add Another']").click();
				common.WaitForObjectVisibility(".//input[@placeholder='Enter label name']");
				Thread.sleep(2000);
				break;
			case "FORMULA":
				WebElementFacade formulaInputBox = $(".//div[@class='formula-editable']");
				//.//input[@name='tracker_column[values]']				
				formulaInputBox.click();

				//WebElementFacade formula = $(".//ul[@class='typeahead dropdown-menu']/li[@data-value='SUM( %{column}, %{column} )']");
				String sumFormula="SUM(%{number1}, %{number2})";
				common.upload(getDriver(), sumFormula);
				//	formulaInputBox.click();
				//formulaInputBox.sendKeys(sumFormula);
				/*String sumFormula1="SUM";
				String sumFormula2="(";
				String sumFormula3= "%{number1}, %{number2})";
				formulaInputBox.sendKeys(sumFormula1);
				formulaInputBox.sendKeys(sumFormula2);
				formulaInputBox.sendKeys(sumFormula3);*/
				Thread.sleep(800);
				WebElementFacade formatDropDown = $(".//select[@name='tracker_column[field_format]']");
				formatDropDown.selectByVisibleText("Integer");				
				////adminOnlyRadioBtn.click();

				$(".//button[text()='Create']").click();
				//common.WaitForObjectVisibility(".//input[@placeholder='Enter label name']");
				//Thread.sleep(2000);
				break;
			}
		}
		//WebElementFacade columnCloseBtn = $(".//*[@id='easydragHandler']/a");
		//Thread.sleep(2000);
		//common.clickElement( columnCloseBtn);
		//columnCloseBtn.click();
		WebElementFacade addNewEntry = $(".//a[contains(text(),'Add New Entry')]");
		Thread.sleep(3000);
		common.waitForElement(addNewEntry);
		addNewEntry.click();
		if(!behaviour.equals("")) {
			if(behaviour.equals("RequiredPrimary")) {
				WebElementFacade mandatoryField = $("//label[@title='User Look Ahead']/span/span[@class='mandatory']");
				mandatoryField.shouldBeVisible();
				report.Info("Mandatory Field is visible");
				WebElementFacade uniqueField = $(".//label[@title='User Look Ahead']/i[@class='fas fa-key']");
				uniqueField.shouldBeVisible();
				report.Info("Unique Field is visible");
			}
			if(behaviour.equals("Required")) {
				WebElementFacade mandatoryField = $("//label[@title='User Look Ahead']/span/span[@class='mandatory']");
				mandatoryField.shouldBeVisible();
				report.Info("Mandatory Field is visible");
			}
			if(behaviour.equals("ReadOnly")) {
				//WebElementFacade uniqueField = $(".//label[@title='Mandatory']/i[@class='fas fa-key']");
				//uniqueField.shouldBeVisible();
				//report.Info("Mandatory Field is visible");
			}
		}

		if(columnsToCreate.contains("File Attachment")) {
			String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\sampleTestV3.txt";
			WebElementFacade chooseFile = $(".//input[contains(@id,'fileupload')]");
			upload(filePath).to(chooseFile);
			common.WaitForObjectVisibility(".//a[@class='attachment_file']");
			//Thread.sleep(4000);
		}
		WebElementFacade saveEntryButton = $(".//button[@id='data-row-save']");
		common.waitForElement(saveEntryButton);
		saveEntryButton.click();
		getDriver().navigate().refresh();
		Thread.sleep(3000);
		String valueTOVerify ="";
		if(columnsToCreate.contains("Formula")) {
			columnsToCreate="number1,number2,Formula";	
			valueTOVerify = "20,2,22";
			String  table = "(.//table[@class='htCore'])[1]";
			common.locateColumn(table, columnsToCreate, valueTOVerify, "0");
		}else {
			common.readTable(columnsToCreate,valueTOVerify);
		}


	}

	@Step
	public void trackerSearch(String logicalName) throws InterruptedException {

		String trackerUrl = Serenity.sessionVariableCalled("existingTrackerUrl").toString();
		getDriver().get(trackerUrl);
		WebElementFacade tableRows = $("(.//table[@class='htCore'])[1]/tbody");
		List<WebElementFacade>tableRowsList = tableRows.thenFindAll(By.tagName("tr"));

		int rows = tableRowsList.size();
		do {
			//System.out.println("In DO");
			Thread.sleep(500);
		}while(rows==0);

		WebElementFacade searchInputBox = $(".//input[@class='trcSub-Serbox boxnewSearch custom-search trackerview']");
		searchInputBox.sendKeys("200");
		Thread.sleep(1000);
		List<WebElementFacade>searchResult = findAll(By.xpath(".//td[contains(@class,'htSearchResult')]"));
		int searchResults = searchResult.size();
		if((searchResults>0) && ($("(.//td[contains(@class,'htSearchResult')])[1]").containsText("200"))){
			report.Info("Tracker search successful, " +searchResults+ " search results found");
		}else {
			report.Info("Tracker search unsuccessful, " +searchResults+ " search results found");
			Assert.assertTrue(false);
		}
	}
	@Step
	public void editEntry(String logicalName) throws InterruptedException {

		String trackerUrl = Serenity.sessionVariableCalled("existingTrackerUrl").toString();
		getDriver().get(trackerUrl);
		WebElementFacade tableRows = $("(.//table[@class='htCore'])[1]/tbody");
		List<WebElementFacade>tableRowsList = tableRows.thenFindAll(By.tagName("tr"));

		int rows = tableRowsList.size();
		do {
			//System.out.println("In DO");
			Thread.sleep(500);
		}while(rows==0);

		WebElementFacade coulumnToClick = $(".//td[text()='$200']");
		coulumnToClick.click();
		common.WaitForObjectVisibility(".//input[@class='addnewRecordData input_msprice price']");
		WebElementFacade amountInSplitView = $(".//input[@class='addnewRecordData input_msprice price']");
		amountInSplitView.type("300");
		WebElementFacade saveSplitView = $(".//button[@id='data-row-submit1']");
		Thread.sleep(1000);
		saveSplitView.click();
		Thread.sleep(1000);

		report.Info("Entry successfully edited in Split View");

		WebElementFacade firstRow=$("(.//div[@data-row-number='0'])[2]");
		common.mouseHover( firstRow);
		WebElementFacade expandIcon = $("(.//a[@href='javascript://'][contains(@onclick,'ES6MangoSpring.trackerModule.handsonModule().expandRecord(event, 0,\"trackerview')])[1]");
		common.clickElement( expandIcon);
		WebElementFacade amountInExpandView = $(".//input[@class='addnewRecordData error-placement input_msprice  price']");
		amountInExpandView.waitUntilVisible();
		amountInExpandView.type("200");
		WebElementFacade saveExpandView = $(".//button[@id='data-row-submit']");
		common.clickElement( saveExpandView);
		Thread.sleep(2500);
		//saveExpandView.click();
		//		$(".//div[@id='notice'][contains(text(),'Entry updated')]").shouldBeVisible();
	}
	@Step
	public void filterTrackerColumns(String logicalName) throws InterruptedException {
		String trackerUrl = Serenity.sessionVariableCalled("existingTrackerUrl").toString();
		getDriver().get(trackerUrl);
		WebElementFacade tableRows = $("(.//table[@class='htCore'])[1]/tbody");
		List<WebElementFacade>tableRowsList = tableRows.thenFindAll(By.tagName("tr"));

		int rows = tableRowsList.size();
		do {
			//System.out.println("In DO");
			Thread.sleep(500);
		}while(rows==0);
		WebElementFacade DateFilterIcon = $(".//*[contains(@id,'trackerview-')]/div[3]/div/div/div/table/thead/tr/th[7]/div/i");
		//"(.//i[@class='far fa-filter filter-icon'])[5]");
		//common.clickElement( DateFilterIcon);
		DateFilterIcon.click();
		WebElementFacade filterByCondition = $("(.//div[@class='htUISelect']/div[@class='htUISelectCaption'])[1]");
		common.waitForElement(filterByCondition);
		filterByCondition.click();
		WebElementFacade isNotEmpty = $(".//div[@class='htItemWrapper'][text()='Is not empty']");
		isNotEmpty.click();
		WebElementFacade filterByValue = $(".//div[@class='htUIMultipleSelectSearch htUIInput']/input");
		common.WaitForObjectVisibility(".//div[@class='htUIMultipleSelectSearch htUIInput']/input");
		filterByValue.type("testText");
		WebElementFacade okButton = $(".//div[@class='htUIButton htUIButtonOK htUIInput']/input");
		okButton.click();
		tableRows = $("(.//table[@class='htCore'])[1]/tbody");
		tableRowsList = tableRows.thenFindAll(By.tagName("tr"));
		rows = tableRowsList.size();
		if(rows==1) {
			report.Info("Filter by Value successful");
		}else {
			report.Info("Filter by Value unsuccessful");
			Assert.assertTrue(false);
		}
	}

	@Step
	public void archiveUnarchivePost(String logicalName) throws InterruptedException {

		String postToarchive = Serenity.sessionVariableCalled("postToArchive");
		getDriver().get(postToarchive);
		WebElementFacade adminTools = $(".//a[@title='Admin Tools']");
		common.waitForElement(adminTools);
		adminTools.click();
		common.WaitForObjectVisibility(".//ul[@class='page_options_menu'][@style='display: block;']/li/a[contains(text(),'Archive Post')]");
		WebElementFacade archivePostOption = $(".//ul[@class='page_options_menu'][@style='display: block;']/li/a[contains(text(),'Archive Post')]");
		archivePostOption.click();
		common.WaitForObjectVisibility(".//button[text()='Archive']");
		WebElementFacade archiveConfirmBtn = $(".//button[text()='Archive']");
		archiveConfirmBtn.click();
		//$(".//div[@id='notice'][contains(text(),'successfully archived')]").shouldBeCurrentlyVisible();

		//adminTools = $(".//a[@title='Admin Tools']");
		common.WaitForObjectVisibility(".//a[@title='Admin Tools']");
		Thread.sleep(3000);
		$(".//a[@title='Admin Tools']").click();
		common.WaitForObjectVisibility("//*[@id='narrow_by_list_1']/li/a[contains(text(),'Unarchive Post')]");				
		WebElementFacade unArchivePostOption = $("//*[@id='narrow_by_list_1']/li/a[contains(text(),'Unarchive Post')]");
		unArchivePostOption.shouldBeVisible();
		report.Info("Post archive successful");
		unArchivePostOption.click();
		common.WaitForObjectVisibility(".//button[text()='Unarchive']");
		WebElementFacade unArchiveConfirmBtn = $(".//button[text()='Unarchive']");
		unArchiveConfirmBtn.click();
		//$(".//div[@id='notice'][contains(text(),'successfully unarchived')]").shouldBeVisible();
		common.WaitForObjectVisibility(".//a[@title='Admin Tools']");
		Thread.sleep(3000);
		$(".//a[@title='Admin Tools']").click();
		//common.WaitForObjectVisibility("//*[@id='narrow_by_list_1']/li/a[contains(text(),'Archive Post')]");
		archivePostOption = $(".//ul[@class='page_options_menu'][@style='display: block;']/li/a[contains(text(),'Archive Post')]");
		archivePostOption.shouldBeVisible();
		report.Info("Post unarchive successful");		
	}

	@Step
	public void createEditForm(String logicalName) throws InterruptedException {
		String trackerForm = Serenity.sessionVariableCalled("trackerForm");		
		getDriver().get(trackerForm);
		WebElementFacade tableRows = $("(.//table[@class='htCore'])[1]/tbody");
		List<WebElementFacade>tableRowsList = tableRows.thenFindAll(By.tagName("tr"));

		int rows = tableRowsList.size();
		do {
			//System.out.println("In DO");
			Thread.sleep(500);
		}while(rows==0);

		WebElementFacade trackerTools=$(".//a[contains(text(),'Tracker Tools')]");
		trackerTools.click();
		WebElementFacade createNewForm = $(".//a[contains(text(),'Create New Form')]");
		createNewForm.click();
		WebElementFacade formName = $(".//input[@id='formviewname']");
		common.WaitForObjectVisibility(".//input[@id='formviewname']");
		String formname="Automation Form"+ dateName;
		formName.sendKeys(formname);
		WebElementFacade saveFormButton =$(".//div[@class='dialog_actions']/button[@type='submit']");
		saveFormButton.click();
		Thread.sleep(500);
		common.WaitForObjectVisibility(".//h1[text()='"+formname+"']");
		WebElementFacade editForm=$(".//a[@id='edit-form-view']");
		editForm.click();
		common.WaitForObjectVisibility("(.//input[@readonly='readonly'])[1]");		
		WebElementFacade formStyle = $(".//select[@id='formViewStyleSelect']");
		if(logicalName.contains("Classic")){
			formStyle.selectByValue("classic");
		}else if(logicalName.contains("Modern")){
			formStyle.selectByValue("modern");
		}
		Thread.sleep(1000);
		WebElementFacade enableColor =$(".//label[@for='show_banner_switch']");
		common.clickElement( $(".//label[@for='show_banner_switch']"));
		WebElementFacade enableImageLogo =$(".//label[@for='show_header_image_switch']");
		Thread.sleep(500);
		common.clickElement( $(".//label[@for='show_header_image_switch']"));
		Thread.sleep(500);
		//enableImageLogo.click();
		String path = "";
		if(logicalName.contains("Logo")){
			//String imagepresent = $("//img[@id='tracker_view_logo']").getAttribute("src");
			//if(!imagepresent.equals("")){
			//	$(".//i[@class='far fa-trash form-logo-remove formLogoRemove']").click();
			//}
			WebElementFacade addHeaderImage=$(".//button[@id='tracker_form_logo_change_btn']");
			addHeaderImage.click();
			common.WaitForObjectVisibility(".//input[@placeholder='Find by name']");
			WebElementFacade logoTab = null;
			if(logicalName.contains("Image")){
				logoTab =	$(".//div[@class='mg-body-tabs']/div[text()='Media']");
				logoTab.click();
				$("(.//li[@class='is-image '])[1]").click();
				path = ".//img[@id='tracker_view_logo']";
			}else if(logicalName.contains("Icon")){
				logoTab =	$(".//div[@class='mg-body-tabs']/div[text()='Icons']");
				$(".//li/i[@class=' fab fa-app-store-ios']").click();
				path=".//img[@id='tracker_view_logo']";
			}else if(logicalName.contains("GIF")){
				logoTab =$(".//div[@class='mg-body-tabs']/div[text()='GIF']");
				logoTab.click();
				$("(.//li[@class='is-gif '])[1]").click();
				path = ".//img[@alt='Tenor']";
			}
			$(".//div[@class='mg-footer']/button[text()='Ok']").waitUntilClickable();
			$(".//div[@class='mg-footer']/button[text()='Ok']").click();
			WebElementFacade saveFormBtn =$(".//a[@id='edit-form-submit']");
			common.waitForElement(saveFormBtn);
			saveFormBtn.click();
			common.WaitForObjectVisibility(".//h1[text()='"+formname+"']");

			common.checkImage(path);

		}

	}
	@Step
	public void addFormToWidget(String logicalName) throws InterruptedException {
		String DomainURL = Serenity.sessionVariableCalled("domainURL").toString();

		String trackerForm = Serenity.sessionVariableCalled("trackerForm");		
		getDriver().get(trackerForm);
		WebElementFacade tableRows = $("(.//table[@class='htCore'])[1]/tbody");
		List<WebElementFacade>tableRowsList = tableRows.thenFindAll(By.tagName("tr"));

		int rows = tableRowsList.size();
		do {
			//System.out.println("In DO");
			Thread.sleep(500);
		}while(rows==0);

		WebElementFacade selectForm =$(".//ul[@id='trackerViewSelect']");
		common.clickElement( selectForm);
		//selectForm.click();
		$(".//ul[@id='trackerViewSelect']/li/a[@title='Automation Form']").click();
		WebElementFacade submitForm = $(".//button/span/span[text()='Submit']");
		common.waitForElement(submitForm);

		WebElementFacade toolsDropdown = $(".//ul/li/a[text()='Tools ']"); 
		toolsDropdown.click();
		WebElementFacade addFormToWidgetGallery = $(".//ul[@id='Traker-More-OP']/li/a[text()='Add Form to Widget Gallery']");
		addFormToWidgetGallery.click();

		common.WaitForObjectVisibility(".//textarea[@id='viewwidgetgaldesc']");
		WebElementFacade widgetDescription = $(".//textarea[@id='viewwidgetgaldesc']");
		widgetDescription.sendKeys("Adding via automation");
		WebElementFacade addToWidgetGallaryBtn = $(".//div[@class='dialog_actions']/input[@value='Add To Widget Gallery']");
		addToWidgetGallaryBtn.click();
		common.WaitForObjectVisibility(".//div[@id='fancybox-content']/div/div/div[2]/button[text()='OK']");
		WebElementFacade widgetOKBtn =$(".//div[@id='fancybox-content']/div/div/div[2]/button[text()='OK']");
		widgetOKBtn.click();
		report.Info("Added form to widget gallery");
		getDriver().get(DomainURL+"ce/pulse/user/overview/index");
		common.WaitForObjectVisibility(".//a[@title='Customize']");
		WebElementFacade customizeButton = $(".//a[@title='Customize']");
		customizeButton.click();
		common.WaitForObjectVisibility(".//a[text()='Widget Gallery']");
		WebElementFacade widgetGalleryBtn = $(".//a[text()='Widget Gallery']");
		widgetGalleryBtn.click();
		common.WaitForObjectVisibility(".//ul/li/a[text()='Tables & Forms']");
		WebElementFacade tablesandForms = $(".//ul/li/a[text()='Tables & Forms']");
		tablesandForms.click();
		common.WaitForObjectVisibility(".//a[@data-name='automation form']/span[text()='Add']");
		WebElementFacade addLink = $(".//a[@data-name='automation form']/span[text()='Add']");
		addLink.click();
		report.Info("clicked on +Add");
		Thread.sleep(500);
		$(".//div[@class='dialog_actions clearfix']/a[text()='Done']").click();
		Thread.sleep(500);
		$(".//div/a[text()='Done']").click();
		common.WaitForObjectVisibility(".//button/span/span[text()='Submit']");
		WebElementFacade submitFormBtn = $(".//button/span/span[text()='Submit']");
		Thread.sleep(500);
		common.clickElement( submitFormBtn);
		//submitFormBtn.click();
		report.Info("clicked on Submit Form");
		Thread.sleep(1500);
		common.WaitForObjectVisibility(".//div[@id='fancybox-content']/div/div/a");
		WebElementFacade okConfirmBtn = $(".//div[@id='fancybox-content']/div/div/a");
		okConfirmBtn.click();
		report.Info("Form successfully submitted via Dashboard");

		getDriver().get(trackerForm);
		tableRows = $("(.//table[@class='htCore'])[1]/tbody");		
		tableRowsList = tableRows.thenFindAll(By.tagName("tr"));
		rows = tableRowsList.size();
		do {
			//System.out.println("In DO");
			Thread.sleep(500);
		}while(rows==0);

		selectForm =$(".//ul[@id='trackerViewSelect']");
		common.clickElement( selectForm);
		$(".//ul[@id='trackerViewSelect']/li/a[@title='Automation Form']").click();
		common.waitForElement(submitForm);
		$(".//ul/li/a[text()='Tools ']").click();
		WebElementFacade removeWidget = $(".//ul[@id='Traker-More-OP']/li/a[text()='Edit or Remove Form in Widget Gallery']");
		common.clickElement(removeWidget);		 
		common.WaitForObjectVisibility("//a[contains(text(),'Remove Form From Widget Gallery')]");
		$(".//a[contains(text(),'Remove Form From Widget Gallery')]").click();
		common.WaitForObjectVisibility("//div[@id='ms_global_dialog']/div/div/div/a[text()='Delete']");
		$("//div[@id='ms_global_dialog']/div/div/div/a[text()='Delete']").click();


	}

	@Step
	public void shareForm(String logicalName) throws InterruptedException, IOException, UnsupportedFlavorException {

		String DomainURL = Serenity.sessionVariableCalled("domainURL").toString();

		String trackerForm = Serenity.sessionVariableCalled("trackerForm");		
		getDriver().get(trackerForm);
		report.Info("Navigated to tracker form");
		WebElementFacade tableRows = $("(.//table[@class='htCore'])[1]/tbody");
		List<WebElementFacade>tableRowsList = tableRows.thenFindAll(By.tagName("tr"));

		int rows = tableRowsList.size();
		do {
			Thread.sleep(500);
		}while(rows==0);

		WebElementFacade selectForm =$(".//ul[@id='trackerViewSelect']");
		common.clickElement( selectForm);
		//selectForm.click();
		$(".//ul[@id='trackerViewSelect']/li/a[@title='Automation Form']").click();
		WebElementFacade submitForm = $(".//button/span/span[text()='Submit']");
		common.waitForElement(submitForm);

		WebElementFacade toolsDropdown = $(".//ul/li/a[text()='Tools ']"); 
		toolsDropdown.click();
		WebElementFacade shareForm = $(".//ul[@id='Traker-More-OP']/li/a[text()='Share Form']");
		shareForm.click();

		common.WaitForObjectVisibility(".//button[@id='view-copy-share-link']");
		WebElementFacade copyLinkBtn = $(".//button[@id='view-copy-share-link']");
		copyLinkBtn.click();
		report.Info("copied link");

		WebElementFacade enableCaptcha =$("//*[@id='protect-from-spam-entries-container']/div[contains(text(),'Enable captcha')]");

		if(!enableCaptcha.isDisplayed()) {
			WebElementFacade allowAnyone = $(".//label[@for='public-share-tracker-view']");
			common.clickElement( allowAnyone);
			//allowAnyone.click();
			enableCaptcha.waitUntilVisible();
			//Thread.sleep(300);
		}			
		WebElementFacade doneBtn = $(".//button/span/span[text()='Done']");
		doneBtn.click();

		getDriver().get(DomainURL);
		domainPage.logoutCheck();

		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Clipboard clipboard = toolkit.getSystemClipboard();
		String copiedLink = (String) clipboard.getData(DataFlavor.stringFlavor);

		getDriver().get(copiedLink);
		report.Info("Navigated to copied form URL");
		common.WaitForObjectVisibility(".//button/span/span[text()='Submit']");
		WebElementFacade submitFormBtn = $(".//button/span/span[text()='Submit']");
		Thread.sleep(500);
		common.clickElement( submitFormBtn);
		//submitFormBtn.click();
		report.Info("clicked on Submit Form");
		common.WaitForObjectVisibility(".//div[@id='mangoCKeditorContent']");
		//WebElementFacade okConfirmBtn = $(".//div[@id='fancybox-content']/div/div/a");
		//okConfirmBtn.click();
		report.Info("Form successfully submitted via shared link");		
	}

	@Step
	public void createTrackerTable(String logicalName) throws InterruptedException {
		String DomainURL = Serenity.sessionVariableCalled("domainURL").toString();

		String trackerTable = Serenity.sessionVariableCalled("trackerTable");		
		getDriver().get(trackerTable);
		report.Info("Navigated to tracker");
		WebElementFacade tableRows = $("(.//table[@class='htCore'])[1]/tbody");
		List<WebElementFacade>tableRowsList = tableRows.thenFindAll(By.tagName("tr"));

		int rows = tableRowsList.size();
		do {
			Thread.sleep(500);
		}while(rows==0);

		WebElementFacade trackerTools=$(".//a[contains(text(),'Tracker Tools')]");
		trackerTools.click();
		WebElementFacade createNewTable = $(".//a[contains(text(),'Create New Table')]");
		createNewTable.click();

		common.WaitForObjectVisibility("//input[@id='gridviewname']");
		WebElementFacade tableName = $(".//input[@id='gridviewname']");
		String tablename="Automation Table"+ dateName;
		tableName.sendKeys(tablename);

		WebElementFacade nextBtn = $(".//span[@id='nextBtn']/button");
		nextBtn.click();
		nextBtn.waitUntilClickable();
		nextBtn.click();

		WebElementFacade addFilterBy = $(".//a[@class='theme_color addNewFilterBtn']");
		addFilterBy.waitUntilClickable();
		addFilterBy.click();

		WebElementFacade addGroupBy = $(".//a[@class='theme_color addNewGroupByBtn']");
		addGroupBy.waitUntilClickable();
		addGroupBy.click();

		WebElementFacade createButton = $(".//span[@id='saveBtn']/button");
		createButton.waitUntilClickable();
		createButton.click();

		Thread.sleep(3000);		
		WebElementFacade selectTable =$(".//ul[@id='trackerViewSelect']");
		common.clickElement( selectTable);
		$(".//ul[@id='trackerViewSelect']/li/a[@title='"+tablename+"']").click();
		report.Info("Tracker table created woth filter and group by");

		/*WebElementFacade toolsDropdown = $(".//ul/li/a[text()='Tools ']"); 
		toolsDropdown.click();
		WebElementFacade shareForm = $(".//ul[@id='Traker-More-OP']/li/a[text()='Share Form']");
		shareForm.click();

		 */
	}

	@Step
	public void duplicateTableHideColumns(String logicalName) throws InterruptedException {
		String trackerTable = Serenity.sessionVariableCalled("trackerTable");		
		getDriver().get(trackerTable);
		report.Info("Navigated to tracker");
		WebElementFacade tableRows = $("(.//table[@class='htCore'])[1]/tbody");
		List<WebElementFacade>tableRowsList = tableRows.thenFindAll(By.tagName("tr"));

		int rows = tableRowsList.size();
		do {
			Thread.sleep(500);
		}while(rows==0);

		WebElementFacade toolsDropdown = $(".//ul/li/a[text()='Tools ']"); 
		toolsDropdown.click();
		WebElementFacade duplicateTable = $(".//ul[@id='Traker-More-OP']/li/a[text()='Duplicate Table']");
		duplicateTable.click();

		common.WaitForObjectVisibility("//input[@id='gridviewname']");
		WebElementFacade nextBtn = $(".//span[@id='nextBtn']/button");

		nextBtn.click();
		$("(//label[@class='onoffswitch-label'])[1]").click();
		Thread.sleep(2000);
		$(".//span[@id='nextBtn']/button").waitUntilClickable();
		$(".//span[@id='nextBtn']/button").click();
		Thread.sleep(1000);
		WebElementFacade saveButton = $(".//span[@id='saveBtn']/button");
		saveButton.waitUntilClickable();
		saveButton.click();
		Thread.sleep(2000);
		report.Info("Tracker table duplicated");	
		String colToVerify = "Name,Age";
		common.readTable(colToVerify,"");

		WebElementFacade selectTable =$(".//ul[@id='trackerViewSelect']");
		common.clickElement( selectTable);
		$(".//ul[@id='trackerViewSelect']/li/a[@title='Master Table']").click();
		Thread.sleep(2500);
		colToVerify = "Title,Name,Age";
		common.readTable(colToVerify,"");
	}

	@Step
	public void addTableToWidget(String logicalName) throws InterruptedException {
		String DomainURL = Serenity.sessionVariableCalled("domainURL").toString();

		String trackerTable = Serenity.sessionVariableCalled("trackerTable");		
		getDriver().get(trackerTable);
		WebElementFacade tableRows = $("(.//table[@class='htCore'])[1]/tbody");
		List<WebElementFacade>tableRowsList = tableRows.thenFindAll(By.tagName("tr"));

		int rows = tableRowsList.size();
		do {
			//System.out.println("In DO");
			Thread.sleep(500);
		}while(rows==0);	

		WebElementFacade toolsDropdown = $(".//ul/li/a[text()='Tools ']"); 
		toolsDropdown.click();
		WebElementFacade addFormToWidgetGallery = $(".//ul[@id='Traker-More-OP']/li/a[text()='Add Table to Widget Gallery']");
		addFormToWidgetGallery.click();

		common.WaitForObjectVisibility(".//textarea[@id='viewwidgetgaldesc']");
		WebElementFacade widgetDescription = $(".//textarea[@id='viewwidgetgaldesc']");
		widgetDescription.sendKeys("Adding via automation");
		WebElementFacade addToWidgetGallaryBtn = $(".//div[@class='dialog_actions']/input[@value='Add To Widget Gallery']");
		addToWidgetGallaryBtn.click();
		common.WaitForObjectVisibility(".//div[@id='fancybox-content']/div/div/div[2]/button[text()='OK']");
		WebElementFacade widgetOKBtn =$(".//div[@id='fancybox-content']/div/div/div[2]/button[text()='OK']");
		widgetOKBtn.click();
		report.Info("Added form to widget gallery");
		getDriver().get(DomainURL+"ce/pulse/user/overview/index");
		common.WaitForObjectVisibility(".//a[@title='Customize']");
		WebElementFacade customizeButton = $(".//a[@title='Customize']");
		customizeButton.click();
		common.WaitForObjectVisibility(".//a[text()='Widget Gallery']");
		WebElementFacade widgetGalleryBtn = $(".//a[text()='Widget Gallery']");
		widgetGalleryBtn.click();
		common.WaitForObjectVisibility(".//ul/li/a[text()='Tables & Forms']");
		WebElementFacade tablesandForms = $(".//ul/li/a[text()='Tables & Forms']");
		tablesandForms.click();
		common.WaitForObjectVisibility(".//a[@data-name='master table']/span[text()='Add']");
		WebElementFacade addLink = $(".//a[@data-name='master table']/span[text()='Add']");
		addLink.click();
		report.Info("clicked on +Add");
		Thread.sleep(1000);
		$(".//div[@class='dialog_actions clearfix']/a[text()='Done']").click();
		Thread.sleep(1500);
		$(".//div/a[text()='Done']").click();
		tableRows = $("(.//table[@class='htCore'])[1]/tbody");		
		tableRowsList = tableRows.thenFindAll(By.tagName("tr"));
		rows = tableRowsList.size();
		do {
			//System.out.println("In DO");
			Thread.sleep(500);
		}while(rows==0);
		report.Info("Table Added to Dashboard");

		WebElementFacade expandTable = $(".//i[@class='far fa-expand-alt mn-icn madn-button'][@title='Expand']");
		common.mouseHover(expandTable);
		Thread.sleep(500);
		common.clickElement(expandTable);
		//expandTable.click();

		common.WaitForObjectVisibility(".//a[contains(text(),'Add New Entry')]");
		WebElementFacade addNewEntry = $(".//a[contains(text(),'Add New Entry')]");
		Thread.sleep(3000);
		common.waitForElement(addNewEntry);
		addNewEntry.click();
		common.WaitForObjectVisibility(".//span[@class='tracker_input_tag ']/input)[1]");
		$("(.//span[@class='tracker_input_tag ']/input)[1]").sendKeys("Mrs");
		$(".//*[@id='data-row-save']/span/span").click();
		common.waitForElement(addNewEntry);		
		report.Info("Table Entry successfully added via dashboard");


		getDriver().get(trackerTable);
		tableRows = $("(.//table[@class='htCore'])[1]/tbody");		
		tableRowsList = tableRows.thenFindAll(By.tagName("tr"));
		rows = tableRowsList.size();
		do {
			//System.out.println("In DO");
			Thread.sleep(500);
		}while(rows==0);

		$(".//ul/li/a[text()='Tools ']").click();
		WebElementFacade removeWidget = $(".//ul[@id='Traker-More-OP']/li/a[text()='Edit or Remove Table in Widget Gallery']");
		common.clickElement(removeWidget);		 
		common.WaitForObjectVisibility("//a[contains(text(),'Remove Table From Widget Gallery')]");
		$(".//a[contains(text(),'Remove Table From Widget Gallery')]").click();
		common.WaitForObjectVisibility("//div[@id='ms_global_dialog']/div/div/div/a[text()='Delete']");
		$("//div[@id='ms_global_dialog']/div/div/div/a[text()='Delete']").click();
	}

	@Step
	public void shareTable(String logicalName) throws InterruptedException, IOException, UnsupportedFlavorException {

		String DomainURL = Serenity.sessionVariableCalled("domainURL").toString();

		String trackerTable = Serenity.sessionVariableCalled("trackerTable");		
		getDriver().get(trackerTable);
		report.Info("Navigated to tracker form");
		WebElementFacade tableRows = $("(.//table[@class='htCore'])[1]/tbody");
		List<WebElementFacade>tableRowsList = tableRows.thenFindAll(By.tagName("tr"));

		int rows = tableRowsList.size();
		do {
			Thread.sleep(500);
		}while(rows==0);

		WebElementFacade toolsDropdown = $(".//ul/li/a[text()='Tools ']"); 
		toolsDropdown.click();
		WebElementFacade shareTable = $(".//ul[@id='Traker-More-OP']/li/a[text()='Share Table']");
		shareTable.click();

		common.WaitForObjectVisibility(".//button[@id='view-copy-share-link']");


		common.WaitForObjectVisibility(".//label[@for='public-share-tracker-view']");
		//WebElementFacade allowToSubmitTable =$(".//label[@for='public-share-tracker-view']");
		//common.clickElement( allowToSubmitTable);
		//allowToSubmitTable.click();
		Thread.sleep(500);
		WebElementFacade allowToAddRows =$(".//select[@id='trackerViewAddPermissionsMode']");
		allowToAddRows.selectByVisibleText("Allow");

		WebElementFacade copyLinkBtn = $(".//button[@id='view-copy-share-link']");
		copyLinkBtn.click();
		report.Info("copied link");
		WebElementFacade doneBtn = $(".//button/span/span[text()='Done']");
		doneBtn.click();
		Thread.sleep(1000);
		report.Info("Clicked Done");
		/*
		 * getDriver().get(DomainURL+"ce/pulse/user/overview/index");
		 * 
		 * 
		 * do { tableRows = $("(.//table[@class='htCore'])[1]/tbody"); tableRowsList =
		 * tableRows.thenFindAll(By.tagName("tr"));
		 * 
		 * rows = tableRowsList.size(); //System.out.println("In DO");
		 * Thread.sleep(500); }while(rows==0);
		 */	

		getDriver().get(DomainURL);
		domainPage.logoutCheck();
		Thread.sleep(2000);

		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Clipboard clipboard = toolkit.getSystemClipboard();
		String copiedLink = (String) clipboard.getData(DataFlavor.stringFlavor);
		Thread.sleep(500);
		getDriver().get(copiedLink);
		Thread.sleep(1500);
		report.Info("Navigated to copied form URL "+copiedLink);
		common.WaitForObjectVisibility(".//a[contains(text(),'Add New Entry')]");
		WebElementFacade addNewEntry = $(".//a[contains(text(),'Add New Entry')]");	
		Thread.sleep(3000);
		common.waitForElement(addNewEntry);
		addNewEntry.click();
		common.WaitForObjectVisibility(".//span/span[text()='Save']");
		$("(.//span[@class='tracker_input_tag ']/input)[1]").sendKeys("Mrs");
		$(".//span/span[text()='Save']").click();
		common.waitForElement(addNewEntry);		
		report.Info("Table Entry successfully submitted via shared link");		
	}

	@FindBy(xpath=".//ul[@class='gallery-icons']/li/i[@class=' fab fa-adn']")
	public WebElementFacade firstMedia;

	public void createQuickLink(String logicalName) throws InterruptedException {
		String DomainURL = Serenity.sessionVariableCalled("domainURL").toString();
		getDriver().get(DomainURL+"ce/pulse/user/overview/index");
		common.WaitForObjectVisibility(".//a[@title='Customize']");
		WebElementFacade customizeButton = $(".//a[@title='Customize']");
		customizeButton.click();
		common.WaitForObjectVisibility(".//a[text()='Widget Gallery']");
		WebElementFacade widgetGalleryBtn = $(".//a[text()='Widget Gallery']");
		widgetGalleryBtn.click();
		common.WaitForObjectVisibility(".//ul/li/a[text()='New Content']");
		WebElementFacade newContent = $(".//ul/li/a[text()='New Content']");
		newContent.click();
		common.WaitForObjectVisibility(".//a[@data-name='quicklinks_widget']/span[text()='Add']");
		WebElementFacade addLink = $(".//a[@data-name='quicklinks_widget']/span[text()='Add']");
		addLink.click();
		report.Info("clicked on +Add");
		Thread.sleep(500);
		$(".//div[@class='dialog_actions clearfix']/a[text()='Done']").click();
		Thread.sleep(500);
		common.WaitForObjectVisibility(".//div/button[text()='Design']");
		WebElementFacade designButton = $(".//div/button[text()='Design']");
		designButton.click();
		common.WaitForObjectPresence("(.//div[@class='link-img-box'])[1]");
		WebElementFacade tamplateSelect = $("(.//div[@class='link-img-box'])[1]");
		common.clickElement(tamplateSelect);
		common.WaitForObjectVisibility(".//ul[@class='gallery-icons']/li/i[@class=' fab fa-adn']");
		$(".//ul[@class='gallery-icons']/li/i[@class=' fab fa-adn']").click();
		Thread.sleep(500);
		WebElementFacade URL = $(".//div/input[@value='https://']");
		
		URL.type(imageLink);
		WebElementFacade title = $(".//div[@class='col-md-8 mg-col mg-input wdt-md-12']/input[@value='']");
		title.type("Automation Quick Link");
		WebElementFacade OpenIn = $(".//div[@data-id='TARGET']");
		OpenIn.click();
		$(".//ul[@class='so-options-ul ']/li[text()='Same Tab']").click();
		report.Info("Added all info");
		$(".//div[@class='mg-footer']/button[text()='Ok']").waitUntilClickable();
		$(".//div[@class='mg-footer']/button[text()='Ok']").click();		
		Thread.sleep(500);
		WebElementFacade saveButton = $(".//div[@class='ql-action-buttons']/button[text()='Save']");
		//common.scrollToView(saveButton);
		common.clickElement(saveButton);
		Thread.sleep(1000);
		$(".//div/a[text()='Done']").click();
		Thread.sleep(1000);
		common.WaitForObjectVisibility("(.//div[@class='link-img-box']/i[@class=' fab fa-adn '])[2]");
		WebElementFacade addedQuickLink = $("(.//div[@class='link-img-box']/i[@class=' fab fa-adn '])[2]");
		addedQuickLink.shouldBeVisible();
		report.Info("Quick Link Added");
		addedQuickLink.click();
		if (getDriver().getCurrentUrl().contains("photogenie")) {
			report.Info("Quick Link click successful");
		}else {
			Assert.assertTrue(false);
			report.Info("Library conditional Link click Unsuccessful");
		}
		getDriver().get(DomainURL+"ce/pulse/user/overview/index");
		common.WaitForObjectVisibility(".//a[@title='Customize']");
		customizeButton = $(".//a[@title='Customize']");
		customizeButton.click();
		common.WaitForObjectVisibility("(//*[contains(@id,'quicklinks_widget')]/div[1]/div/div/span)[1]");
		common.mouseHover($("(//*[contains(@id,'quicklinks_widget')]/div[1]/div/div/span)[1]"));
		Thread.sleep(500);
		common.clickElement($("(//*[contains(@id,'quicklinks_widget')]/div[1]/div/div/span)[1]"));
		common.clickElement($("(.//li[contains(@id,'remove_widget_link_')])[5]"));
		report.Info("Quick Link Removed");
	}

	public void createLibraryLink(String logicalName) throws InterruptedException {
		String DomainURL = Serenity.sessionVariableCalled("domainURL").toString();
		getDriver().get(DomainURL+"user/libraries");
		Thread.sleep(1000);
		common.WaitForObjectVisibility(".//a[@title='Create library item']");
		WebElementFacade addItemsBtn = $(".//a[@title='Create library item']");
		addItemsBtn.click();
		common.WaitForObjectVisibility(".//select[@id='external_apps_link_type']");
		Thread.sleep(1000);
		
		WebElementFacade itemTypeSelect = $(".//select[@id='external_apps_link_type']");
		itemTypeSelect.selectByValue("ConditionalLink");
		common.WaitForObjectVisibility(".//select[@name='external_apps[condition_parameter]']");		
		WebElementFacade whenDropDown = $(".//select[@name='external_apps[condition_parameter]']");
		whenDropDown.selectByValue("Projects");
		
		WebElementFacade equalsInput = $(".//ul[@class='token-input-list-facebook notranslate']/li/input");
		equalsInput.sendKeys("MangoApps");
		Thread.sleep(2000);
		equalsInput.sendKeys(Keys.TAB);
					
		String sLink = "Auto Link"+dateName;
		WebElementFacade linkTitle = $(".//input[contains(@name,'external_apps[conditional_link_parameters_attributes][')][@class='form-control bg-white']");
		linkTitle.sendKeys(sLink);
				
		WebElementFacade link = $(".//input[contains(@name,'external_apps[conditional_link_parameters_attributes][')][@class='form-control bg-white conditionalLinkParameterLink']");
		link.sendKeys(imageLink);				
		
		WebElementFacade openIn = $("(.//select[@id='external_apps_link_target'])[2]");
		common.scrollToView(openIn);
		openIn.selectByVisibleText("Current tab");
		
		report.Info("Entered required fields");
		
		WebElementFacade saveAndClose = $(".//button[@id='library-item-save']");
		saveAndClose.waitUntilClickable();
		saveAndClose.click();
		
		common.WaitForObjectVisibility(".//span[contains(text(),'"+sLink+"')]");
		WebElementFacade addedLink = $(".//span[contains(text(),'"+sLink+"')]");
		addedLink.shouldBeVisible();
		report.Info("Added link is visible");
		addedLink.click();
		if (getDriver().getCurrentUrl().contains("photogenie")) {
			report.Info("Library conditional Link click successful");
		}else {
			Assert.assertTrue(false);
			report.Info("Library conditional Link click Unsuccessful");
		}
	}
}






