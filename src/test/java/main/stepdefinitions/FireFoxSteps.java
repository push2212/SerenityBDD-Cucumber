package main.stepdefinitions;


import java.io.IOException;

import org.openqa.selenium.WebDriver;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.core.Serenity;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import pages.DomainPage;

import pages.ProjectPage;

public class FireFoxSteps {
	
	@Managed (driver = "firefox")
	WebDriver driver;
	
	@Steps
	DomainPage domainPage;

	@Steps
	ProjectPage projectPage;
	
	@Given("User has explicitly logged in to {string} domain in firefox")
	public void user_has_logged_in_with_username_firefox(String logicalName) throws IOException, InterruptedException {
		Serenity.getWebdriverManager().setCurrentDriver(driver);
		domainPage.loginCheck(logicalName);
	}

	@When("User Checks {string}")
	public void wiki_TOC_firefox(String logicalName) throws IOException, InterruptedException {			
		domainPage.wikiTableOfContentsCheck(logicalName);
	}


}
