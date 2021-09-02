@LMS
Feature: Test LMS functionalities
  I want to use this template for my LMS feature file

 @LMS001
  Scenario: Create course add-reset video photo category rules , add instructor
  Given User has explicitly logged in to "desired" domain
	  When User performs "CreateNewCourse" action
	  
	@LMS002
  Scenario: Edit course add-reset video photo category rules
	  When User performs "EditCourse" action
	  
	@LMS003
  Scenario: Create course content -add cover,image,video,audio,quote,button,text
	  When User performs "CreateCourseContent" action
	  
	@LMS006
  Scenario: Take course ,ask question , ansewer question and rate course
  Given User has explicitly logged in to "WithTestUser" domain
	  When User performs "TakeCourse" action
	  Given User has explicitly logged in to "desired" domain
	  When User performs "ViewCourseInsights" action
	  
	@LMS007
  Scenario: Assign and remove course users
  Given User has explicitly logged in to "desired" domain
	  When User performs "AssignRemoveCourseUsers" action
	  
	@LMS008
  Scenario: Create course certificate
	  When User performs "CreateCertificate" action
	  
  @LMS009
  Scenario: Create course category
	  When User performs "CreateCategory" action
	  
	@LMS011
  Scenario: Add/Edit course fields
	  When User performs "AddEditCourseFields" action
	  
	@LMS012
  Scenario: Take course as guest user   
    Given User has explicitly logged in to "desired" domain
	  When User performs "CreateNewCourse" action
    When User performs "CreateContentChapter" action
    Given User has explicitly logged in to "guestUser" domain
	  When User performs "TakeCourseGuest" action
	  Given User has explicitly logged in to "desired" domain
	  
	@LMS013
  Scenario: Verify course visibility specific to location
	  Given User has explicitly logged in to "desired" domain
	  When User performs "SetLocationCourseVisibility" action
	  Given User has explicitly logged in to "WithTestUser" domain
		Then Verify "LocationSpecificVisibility" action
		
	@LMS014
  Scenario: Verify course visibility to Entire company
	  Given User has explicitly logged in to "desired" domain
	  When User performs "SetCompanyCourseVisibility" action
	  Given User has explicitly logged in to "CompanyUser" domain
		Then Verify "CompanySpecificVisibility" action
		
	@LMS015
  Scenario: Verify course visibility to Private
	  Given User has explicitly logged in to "desired" domain
	  When User performs "SetPrivateCourseVisibility" action
	  Given User has explicitly logged in to "WithTestUser" domain
		Then Verify "PrivateVisibility" action
		
	@LMS016
  Scenario: Verify course visibility to Public
	  Given User has explicitly logged in to "desired" domain
	  When User performs "SetPublicCourseVisibility" action
	  And User logs out from domain 
		Then Verify "PublicVisibility" action
		Given User has explicitly logged in to "desired" domain
		
	@LMS017
  Scenario: Create course to check mandatory a Pre-requisitie course
  	Given User has explicitly logged in to "desired" domain
	  When User performs "CreateCoursePreRequisite" action
	  When User performs "CreateContentChapter" action
	  Given User has explicitly logged in to "WithTestUser" domain
	  Then Verify "CheckPreRequisitecourse" action
	  Given User has explicitly logged in to "desired" domain
	  
	@LMS018
  Scenario: Check Traversal and Completion Rules 
  	Given User has explicitly logged in to "desired" domain
  	When User performs "CreateTraversalCourse" action
  	When User performs "CreateTraversalContent" action
  	Given User has explicitly logged in to "WithTestUser" domain
  	When User performs "TakeCourseSequential" action
  	Given User has explicitly logged in to "desired" domain
  	When User performs "EditTraversalCourse" action
  	Given User has explicitly logged in to "WithTestUser" domain
  	When User performs "TakeCourseAnyOrder" action
  	Given User has explicitly logged in to "desired" domain
  	
	  
	@LMS010
  Scenario: Edit course settings
  Given User has explicitly logged in to "desired" domain
	  When User performs "EditCourseSettings" action
	  
	 @LMS004
  Scenario: Archive and Unarchive a course
	  When User performs "ArchiveUnarchiveCourse" action
	  
	@LMS005
  Scenario: Delete and restore a course
	  When User performs "DeleteRestoreCourse" action
	  
	@LMS019
  Scenario: Create SCORM course , upload new version , check versioning
	  When User performs "CreateSCORMCourse" action
	  
	@LMS020
  Scenario: Disable Learn module and verfiy
	  When User performs "DisableLearnModule" action
	  
	@LMS021
  Scenario: View course as instructor and verify
	  When User performs "ViewAsInstructor" action
	  
	@LMS022
  Scenario: Create sequencial curriculum 
	  When User performs "CreateCurriculum" action