@Projects
Feature: Test Different scenarios for Projects module
  I want to tes all the functionalities in the project module,
  such as Create static/dynamic page , pages , upload text files , videos ,
  add member , creat task and so on.

  @PROJECTS001
  Scenario: Create a static Page with Media in a project
  Given User has explicitly logged in to "desired" domain
	  When User Creates a "staticPagewithMedia" in a project or group
	  Then Verify if page edit is successful
	  
	@PROJECTS002
  Scenario: Create a project template
  Given User has explicitly logged in to "desired" domain
	  When User "CreatesprojectTemplate"	  
	  
	@PROJECTS003
  Scenario: create project using the template	  
	 When User "Createsproject" using a project template
	 
	@PROJECTS004
  Scenario: Incoming email to a Team
  Given User has explicitly logged in to "desired" domain
	  When User "SendEmailToTeam"
	  
	@PROJECTS005
  Scenario: Reply to an external mail via Share
	  When User "ReplyToExternalEmailViaShare"
	  
	@PROJECTS006
  Scenario: Verify out going external email
	  When User verifies out going external email
	  
	@PROJECTS008
  Scenario: Upload a text file and Verify its Conversion
  Given User has explicitly logged in to "desired" domain
	  When User "UploadsTextFile" and Verifies its Conversion
	  
	@PROJECTS009
  Scenario: Upload a new Version of Text File and then Verify  	
	  When User "UploadsNewVersionTextFile" and Verify
	  
	@PROJECTS010
  Scenario: Check out a file and then Check In  	
	  When A User Check out a file and then Check In  
	  
	@PROJECTS011
  Scenario: Create a Dynamic Page
  Given User has explicitly logged in to "desired" domain
	  When User Creates a "DynamicPage" in a project or group
	  Then Verify if page edit is successful
	  
	@PROJECTS012
  Scenario: Create a recurring Task  	
  Given User has explicitly logged in to "desired" domain
	  When User Creates a "RecurringTask" in a project or group
	  
	@PROJECTS013
  Scenario: Upload a Video and Verify its Conversion
  Given User has explicitly logged in to "desired" domain
	  When User "UploadsAVideo" and Verifies its Conversion
	  
	@PROJECTS014
  Scenario: Make a folder as public and then Verify
 
	  When User "MakeaFolderAsPublic" and then Verify
	  
	@PROJECTS015
  Scenario: Add New Members to a Team and Verify

	  When User "AddsNewMember" and then Verify
	  
	@PROJECTS016
  Scenario: Add a shortcut for Team and Verify
  Given User has explicitly logged in to "desired" domain
	  When User performs "AddShortCutToTeam"