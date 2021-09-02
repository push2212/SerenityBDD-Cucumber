package main.stepdefinitions;


import java.io.IOException;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;

import pages.UsersPage;
import pages.ProjectPage;
import pages.DomainPage;
import utilities.ReportMessege;

public class UsersSteps {

	@Steps
	UsersPage usersPage;

	@Steps
	DomainPage domainPage;

	@Steps
	ProjectPage projectPage;

	@Steps
	ReportMessege report;

	@When("User imports {string}")
	public void csv_user_import(String logicalName) throws IOException, InterruptedException {		
		usersPage.importUsersFromCSVfileCheck(logicalName);
	}

	@When("A User changes {string} in User Profile and DP")
	public void user_chagesProfileDP(String logicalName) throws IOException, InterruptedException {		
		usersPage.changeDPnOtherSectionCheck(logicalName);		
	}

	@When("A User performs {string} action on user tools")
	public void user_tool_actions(String logicalName) throws IOException, InterruptedException {
		usersPage.userToolsActions(logicalName);
		report.Info("Action "+logicalName+ "perfomed successfully");
	}

	@When("A User performs {string} action in user settings")
	public void user_options_action(String logicalName) throws IOException, InterruptedException {
		usersPage.userOptions(logicalName);		
	}
	
	@When("A User performs {string} from Global Search")
	public void user_global_search(String logicalName) throws IOException, InterruptedException {
		usersPage.userSearchCheck(logicalName);		
	}

}
