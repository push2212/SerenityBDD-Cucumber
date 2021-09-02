package main.stepdefinitions;


import java.io.IOException;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;

import pages.UsersPage;
import pages.ProjectPage;
import pages.DomainPage;
import pages.PeoplePage;
import utilities.ReportMessege;

public class PeopleSteps {

	@Steps
	PeoplePage peoplePage;

	@Steps
	DomainPage domainPage;

	@Steps
	ProjectPage projectPage;

	@Steps
	ReportMessege report;

	@When("A User {string}")
	public void csv_user_import(String logicalName) throws IOException, InterruptedException {		
		peoplePage.visitColleagueProfile(logicalName);
	}
	
	@When("A User verifies Pagination in People directory")
	public void people_pagination() throws IOException, InterruptedException {		
		peoplePage.paginationInPeopleDirectoryCheck();
	}

}
