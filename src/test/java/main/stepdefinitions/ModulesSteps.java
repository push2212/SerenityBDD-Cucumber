package main.stepdefinitions;


import java.io.IOException;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;

import pages.ModulesPage;
import pages.ProjectPage;

public class ModulesSteps {

	@Steps
	ModulesPage modulesPage;

	@Steps
	ProjectPage projectPage;

	@When("User enables {string}")
	public void csv_user_import(String logicalName) throws IOException, InterruptedException {		
		modulesPage.enableAllModulesCheck(logicalName);
	}


}
