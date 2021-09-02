package main.stepdefinitions;

import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;
import pages.GroupsPage;
//import pages.LoginPage;
import pages.ProjectPage;

public class GroupSteps {

	/*
	 * @Steps LoginPage loginpage;
	 */

	@Steps
	GroupsPage groupsPage;

	@Then("Verify if {string}")
	public void check_default_groups(String logicalName) throws IOException, InterruptedException, UnsupportedFlavorException {		
		groupsPage.defaultGroupsCheck(logicalName);
	}
	
	@When("{string} to Everyone Group")
	public void addguest_to_everyone(String logicalName) throws IOException, InterruptedException, UnsupportedFlavorException {	
		
		if(logicalName.equals("AddGuestUser")) {
			groupsPage.addGuestUserToEveryoneCheck(logicalName);			
		}else if(logicalName.equals("CheckUpdateduserCount")) {
			groupsPage.verifyUsersInEveryoneCheck(logicalName);
		}		
	}
}
