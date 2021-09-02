package main.stepdefinitions;

import java.awt.AWTException;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.model.TestResult;
import net.thucydides.core.requirements.reports.ScenarioOutcome;
import pages.DomainPage;
import pages.ProjectPage;
import utilities.ReportMessege;

public class DomainSteps  {

	@Steps
	DomainPage domainPage;

	@Steps
	ProjectPage projectPage;

	@Steps(shared=true) 
	private ReportMessege report;

	@Given("User has explicitly logged in to {string} domain")
	public void user_has_logged_in_with_testeUser(String logicalName) throws IOException, InterruptedException {
		domainPage.loginCheck(logicalName);	
	}

	@Given("User has explicitly logged in to domain")
	public void user_has_logged_in_to_domain(String logicalName) throws IOException, InterruptedException {
		domainPage.loginCheck("");	
	}

	/*
	 * @After public void afterScenario(Scenario scenario) throws
	 * InterruptedException, IOException { String status =
	 * scenario.getStatus().toString(); if (!status.equals("PASSED")) {
	 * report.Info("Scenario failed , logging in again");
	 * domainPage.loginCheck("MediaEE"); }
	 * 
	 * }
	 */
	@When("User sets default landing page to {string}")
	public void user_sets_landing_page(String logicalName) throws IOException, InterruptedException {		
		domainPage.defaultLandingPageSettingCheck(logicalName);
	}

	@When("User performs {string}")
	public void user_Performs_actions(String logicalName) throws IOException, InterruptedException, UnsupportedFlavorException {

		if(logicalName.contains("DomainTimezoneSetup")){
			domainPage.timeZoneSetup(logicalName);
		}else if(logicalName.contains("FileDeepSearch")){
			domainPage.fileDeepSearchCheck();
		}else if(logicalName.contains("ViewCountercheck")){
			domainPage.ViewCounterCheck(logicalName);
		}else if(logicalName.contains("NavigationModuleOrderChange")){
			domainPage.navigationModuleOrderChangeCheck(logicalName);
		}else if(logicalName.contains("AddShortCutToTeam")){
			domainPage.shortCutCreation(logicalName);
		}else if(logicalName.contains("DuplicateWiki")){
			domainPage.duplicateAWikiCheck(logicalName);
		}else if(logicalName.contains("DuplicatePost")){
			domainPage.duplicateAPostCheck(logicalName);
		}else if(logicalName.contains("ViewInsights")){
			domainPage.verifyPostInsights(logicalName);
		}else if(logicalName.contains("ArchiveUnarchivePost")){
			domainPage.archiveUnarchivePost(logicalName);
		}else if(logicalName.contains("TrackerSearch")){
			domainPage.trackerSearch(logicalName);
		}else if(logicalName.contains("EditTracker")){
			domainPage.editEntry(logicalName);
		}else if(logicalName.contains("FilterTracker")){
			domainPage.filterTrackerColumns(logicalName);
		}else if(logicalName.contains("FormWith")){
			domainPage.createEditForm(logicalName);
		}else if(logicalName.contains("AddFormToWidgetDashboard")){
			domainPage.addFormToWidget(logicalName);
		}else if(logicalName.contains("ShareForm")){
			domainPage.shareForm(logicalName);
		}else if(logicalName.contains("CreateTrackerTable")){
			domainPage.createTrackerTable(logicalName);
		}else if(logicalName.contains("DuplicateTrackerTable")){
			domainPage.duplicateTableHideColumns(logicalName);
		}else if(logicalName.contains("AddTableToWidgetDashboard")){
			domainPage.addTableToWidget(logicalName);
		}else if(logicalName.contains("ShareTable")){
			domainPage.shareTable(logicalName);
		}else if(logicalName.equals("AddQuickLink")){
			domainPage.createQuickLink(logicalName);
		}else if(logicalName.equals("AddLibraryQuickLink")){
			domainPage.createLibraryLink(logicalName);
		}
		
	}

	@When("A User {string} and Verify")
	public void update_notification_pref(String logicalName) throws IOException, InterruptedException {		
		domainPage.notificationPreferenceUpdateCheck(logicalName);
	}

	@When("Admin {string}")
	public void deactivate_recognition(String logicalName) throws IOException, InterruptedException {		
		domainPage.deActivateRecognitionCheck(logicalName);
	}

	@When("User {string} from Compose")
	public void Compose_Actions(String logicalName) throws IOException, InterruptedException, UnsupportedFlavorException, AWTException {		

		if((logicalName.contains("CreatesAPost"))||(logicalName.contains("MustRead"))){
			domainPage.composeClick(logicalName);
			domainPage.checkPostFromCompose(logicalName);
		}else if(logicalName.contains("CreatesAWiki")){
			domainPage.composeClick(logicalName);
			domainPage.wikiCreation();
		}else {
			domainPage.composeClick(logicalName);
		}
	}

	@When("User {string} and paginate through Search Results")
	public void global_search(String logicalName) throws IOException, InterruptedException {		
		domainPage.searchResultPaginationCheck(logicalName);
	}

	@When("User logs out from domain")
	public void log_Out() throws IOException, InterruptedException {		
		domainPage.logoutCheck();
		domainPage.clearCookies();
	}

	@When("We check two way chat functionality with {string}")
	public void two_way_chat(String logicalName) throws IOException, InterruptedException {
		//domainPage.reLoginCheck("MediaEE");
		domainPage.chatWindowCheck(logicalName);		
	}
}
