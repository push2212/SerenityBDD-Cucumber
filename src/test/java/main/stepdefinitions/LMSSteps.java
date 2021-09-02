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
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import pages.DomainPage;
import pages.LMSPage;
import pages.ProjectPage;
import utilities.common;

public class LMSSteps {


	@Steps
	DomainPage domainPage;

	@Steps
	LMSPage lmspage;

	@Steps
	common common;



	@Given("User performs {string} action")
	public void lms_actions(String logicalName) throws IOException, InterruptedException {
		if((logicalName.equals("CreateNewCourse")) || (logicalName.equals("EditCourse"))) {
			lmspage.createEditCourse(logicalName);
		}else if(logicalName.equals("CreateCourseContent")) {			
			lmspage.createCourseContent(logicalName);
		}else if(logicalName.equals("ArchiveUnarchiveCourse")) {			
			lmspage.archiveUnArchiveCourse(logicalName);
		}else if(logicalName.equals("DeleteRestoreCourse")) {			
			lmspage.deleteRestoreFromTrash(logicalName);
		}else if(logicalName.contains("TakeCourse")) {			
			lmspage.takeCourseActions(logicalName);
		}else if(logicalName.equals("AssignRemoveCourseUsers")) {			
			lmspage.assignRemoveCourseUser(logicalName);
		}else if(logicalName.equals("CreateCertificate")) {			
			lmspage.createCourseCertificate(logicalName);
		}else if(logicalName.equals("CreateCategory")) {			
			lmspage.createCourseCategory(logicalName);
		}else if(logicalName.equals("EditCourseSettings")) {			
			lmspage.courseSettings(logicalName);
		}else if(logicalName.equals("AddEditCourseFields")) {			
			lmspage.courseFields(logicalName);
		}else if(logicalName.equals("TakeCourseGuest")) {	
			lmspage.takeCourseActions(logicalName);
			lmspage.guestCompletedCourseValidation(logicalName);
		}else if(logicalName.equals("SetLocationCourseVisibility")) {			
			lmspage.createEditCourse(logicalName);			
		}else if(logicalName.equals("SetCompanyCourseVisibility")) {			
			lmspage.createEditCourse(logicalName);			
		}else if(logicalName.equals("SetPrivateCourseVisibility")) {			
			lmspage.createEditCourse(logicalName);			
		}else if(logicalName.equals("SetPublicCourseVisibility")) {			
			lmspage.createEditCourse(logicalName);			
		}else if(logicalName.equals("CreateCoursePreRequisite")) {			
			lmspage.createEditCourse(logicalName);			
		}else if((logicalName.equals("CreateTraversalCourse"))|| (logicalName.equals("EditTraversalCourse"))) {			
			lmspage.createEditCourse(logicalName);			
		}else if(logicalName.equals("CreateTraversalContent")) {			
			lmspage.createCourseContent(logicalName);			
		}else if(logicalName.equals("CreateContentChapter")) {			
			lmspage.createCourseContent(logicalName);
		}else if(logicalName.equals("CreateSCORMCourse")) {			
			lmspage.createSCORMCourse(logicalName);
		}else if(logicalName.equals("ViewCourseInsights")) {			
			lmspage.viewCourseInsights(logicalName);
		}else if(logicalName.equals("DisableLearnModule")) {			
			lmspage.checkLearnDisabled(logicalName);
		}else if(logicalName.equals("ViewAsInstructor")) {			
			lmspage.viewCourseAsInstructor(logicalName);
		}else if(logicalName.equals("CreateCurriculum")) {			
			lmspage.createCurriculum(logicalName);
		}
		
		
	}	
	
	@Then ("Verify {string} action")
	public void course_visibilityCheck(String logicalName) throws IOException, InterruptedException {
		if((logicalName.contains("Visibility"))) {
			lmspage.verifyCourseVisiblility(logicalName);
		}else if(logicalName.contains("CreateCourseContent")) {			
			lmspage.createCourseContent(logicalName);
		}else if(logicalName.equals("CheckPreRequisitecourse")) {			
			lmspage.takeCourseActions(logicalName);
		}
		
	}
}
