package main.stepdefinitions;

import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;

//import pages.LoginPage;
import pages.ProjectPage;

public class ProjectSteps {

	/*
	 * @Steps LoginPage loginpage;
	 */

	@Steps
	ProjectPage projectPage;

	@When("User Creates a {string} in a project or group")
	public void user_creates_static_page(String logicalName) throws IOException, InterruptedException, UnsupportedFlavorException {		
		if(logicalName.contains("staticPagewithMedia")|| logicalName.contains("DynamicPage")){
			projectPage.createPage(logicalName);
		}else if(logicalName.contains("RecurringTask")){
			projectPage.createReccurenceTaskCheck(logicalName);
		}

	}

	@Then("Verify if page edit is successful")
	public void change_title() throws IOException, InterruptedException, UnsupportedFlavorException {		
		projectPage.checkChangedTitle();
	}

	@When("User {string}")
	public void user_creates_project_template(String logicalName) throws IOException, InterruptedException, AddressException, MessagingException {
		if(logicalName.equals("CreatesprojectTemplate")){
			projectPage.createProjectTemplateCheck(logicalName);
		}		
		else if(logicalName.equals("SendEmailToTeam")){
			projectPage.incomingEmailwithTeamIDCheck(logicalName);
		}
		else if(logicalName.equals("ReplyToExternalEmailViaShare")){
			projectPage.incomingEmailReplyViaShareCheck(logicalName);
		}
	}

	/*
	 * @Then("Verfiy if {string} is created") public void
	 * verify_project_template(String logicalName) throws IOException,
	 * InterruptedException { projectPage.verifyCreatedTemplate(logicalName); }
	 */

	@When("User {string} using a project template")
	public void user_creates_project_(String logicalName) throws IOException, InterruptedException, UnsupportedFlavorException {		
		projectPage.projectCreationFromTemplate(logicalName);		
	}

	@When("User {string} and Verifies its Conversion") 
	public void upload_textFile(String logicalName) throws IOException,InterruptedException { 

		projectPage.uploadFuntionality(logicalName);

		if (logicalName.contains("UploadsTextFile")){			
			projectPage.documentConversionCheck();
		}else if (logicalName.contains("UploadsAVideo")){
			projectPage.videoUploadNConversionCheck();
		}

	}

	@When("User {string} and Verify") 
	public void upload_newtextFile(String logicalName) throws IOException,InterruptedException { 
		//projectPage.uploadFuntionality(logicalName);
		projectPage.fileUploadNewVersionCheck(logicalName);
	}

	@When("A User Check out a file and then Check In")
	public void user_checkInOutFile() throws IOException, InterruptedException {		
		projectPage.FileCheckOutNCheckInCheck();		
	}

	@When("User {string} and then Verify") 
	public void folder_public(String logicalName) throws IOException,InterruptedException { 
		if(logicalName.contains("AddsNewMember")){
			projectPage.addNewMembersToTeamCheck(logicalName);
		}else if(logicalName.contains("MakeaFolderAsPublic")){
			projectPage.makeAFolderPublicCheck(logicalName);
		}		
	}

	@When("User Creates file in Office365")
	public void office365_createFile() throws IOException, InterruptedException {		
		projectPage.newFileCreationFromOffice365Check();	
	}

	@When("User verifies out going external email")
	public void outgoing_email() throws IOException, InterruptedException, MessagingException {		
		projectPage.readEmail();	
	}
}
