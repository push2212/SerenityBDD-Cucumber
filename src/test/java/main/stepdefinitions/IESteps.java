package main.stepdefinitions;


import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.core.Serenity;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import pages.DomainPage;

import pages.ProjectPage;

public class IESteps {
	
	@Managed (driver = "ie")
	WebDriver driver1;
	
	@Steps
	DomainPage domainPage;

	@Steps
	ProjectPage projectPage;
	
	
    
	@Given("User has explicitly logged in to {string} domain in IE")
	public void user_has_logged_in_with_username_IE(String logicalName) throws IOException, InterruptedException {
		DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
	    capabilities.setCapability("requireWindowFocus", true);
	    capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
	    capabilities.setCapability("ignoreProtectedModeSettings", true);
	    capabilities.setCapability("disable-popup-blocking", true);
	    capabilities.setCapability("enablePersistentHover", true);
	    capabilities.setCapability("ignoreZoomSetting", true);
	    capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true);
		Serenity.getWebdriverManager().setCurrentDriver(driver1);
		domainPage.loginCheck(logicalName);
	}

	@When("User Checks {string} in IE")
	public void wiki_TOC_IE(String logicalName) throws IOException, InterruptedException {			
		domainPage.wikiTableOfContentsCheck(logicalName);
	}

}
