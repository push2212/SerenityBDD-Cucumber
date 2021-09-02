@KMPaints
Feature: Test Different fucntionalities	for KMPaints
  I want to use this feature file for testing all the generic functionalities 
  such as Compose ,TimeZone setup , notification preference , global searh and so on.
  
  
 @KMLogin001
  Scenario: Login to KM
    Given User has explicitly logged in to "desired" domain
     
  @KM001
  Scenario: Set Default Landing Page to dashboard
	  When User sets default landing page to "DashboardLandingPage"
	  
	@KM002
  Scenario: Create a Post from Compose box
  Given User has explicitly logged in to "desired" domain
	  When User "CreatesAPostToTeam" from Compose
	  
	@KM003
  Scenario: Create Event from Compose
  
	  When User "CreatesAMeetingEventToTeam" from Compose
	  
	@KM004
  Scenario: Create a Custom Event from Compose
  
	  When User "CreatesACustomEventToTeam" from Compose
	  
	@KM005
  Scenario: Create a post as an announcement
  
	  When User "CreatesPostAsAnnouncementToTeam" from Compose
	  
	@KM006
  Scenario: Create scheduled post from compose
  
	  When User "CreatesScheduledPostToTeam" from Compose
	 
	@KM009
  Scenario: View post insights, boost the post and view updated  unique view count
 		Given User has explicitly logged in to "desired" domain
	  When User performs "ViewInsightsAndBoostPost"
	  Given User has explicitly logged in to "TestUser" domain
	  When User performs "ViewInsightsUniqueUser"
	  Given User has explicitly logged in to "desired" domain
	  When User performs "ViewInsightsUpdatedCount"	  
	  
	@KM010
  Scenario: View post insights and download report 
 		Given User has explicitly logged in to "desired" domain
	  When User performs "ViewInsightsAndDownloadReport"
	  
	@KM011
  Scenario: Create tracker from Compose
  
	  When User "CreatesATrackerToTeam" from Compose
	  	  
	 @KM014
  Scenario: Create a static Page with Media in a project
  Given User has explicitly logged in to "desired" domain
	  When User Creates a "GroupstaticPagewithMedia" in a project or group
	  Then Verify if page edit is successful
	  
	@KM015
  Scenario: Create group
  Given User has explicitly logged in to "desired" domain
	 When User "CreatesGroup" using a project template
	  
	@KM016
  Scenario: Upload a text file and Verify its Conversion
  Given User has explicitly logged in to "desired" domain
	  When User "GroupUploadsTextFile" and Verifies its Conversion
	  
	@KM017
  Scenario: Upload a new Version of Text File and then Verify  	
	  When User "GroupUploadsNewVersionTextFile" and Verify
	  
	@KM018
  Scenario: Check out a file and then Check In  	
	  When A User Check out a file and then Check In  
	  
	@KM019
  Scenario: Create a Dynamic Page
  Given User has explicitly logged in to "desired" domain
	  When User Creates a "GroupDynamicPage" in a project or group
	  Then Verify if page edit is successful
	  
	@KM020
  Scenario: Upload a Video and Verify its Conversion
  Given User has explicitly logged in to "desired" domain
	  When User "GroupcontainsUploadsAVideo" and Verifies its Conversion
	  
	@KM021
  Scenario: Create Form/tracker with multiple columns
  Given User has explicitly logged in to "desired" domain
	  When User "CreatesATrackerWithColumns" from Compose
	  
	@KM022
  Scenario: Create tracker with Required/Primary columns  
	  When User "RequiredPrimaryTrackerColumns" from Compose
	  
	@KM023
  Scenario: Create tracker with File attachment column 
	  When User "FileAttachmentTrackerColumn" from Compose
	  
	@KM024
  Scenario: Search Tracker columns 
	  When User performs "TrackerSearch"
	  
	@KM025
  Scenario: Edit Tracker entry in split and expand view 
	  When User performs "EditTracker"
	  
	@KM026
  Scenario: Filter Tracker columns
	  When User performs "FilterTracker"
	  
	@KM027
  Scenario: Create Must Read Post
  Given User has explicitly logged in to "desired" domain
	  When User performs "CreatesMustReadPost"

	  
	@KM029
  Scenario: Create and verify Formula column in tracker 
	  When User "CreateFormulaColumn" from Compose
	  
	@KM030
  Scenario: Create classic form with Image as logo
  Given User has explicitly logged in to "desired" domain
	  When User performs "ClassicFormWithImageLogo"
	  
	@KM031
  Scenario: Create classic form with Icon as logo
	  When User performs "ClassicFormWithIconLogo"
	  
	@KM032
  Scenario: Create classic form with GIF as logo
	  When User performs "ClassicFormWithGIFLogo"
	  
	@KM033
  Scenario: Create modern form with Image as logo
	  When User performs "ModernFormWithImageLogo"
	  
	@KM034
  Scenario: Create modern form with Icon as logo
	  When User performs "ModernFormWithIconLogo"
	  
	@KM035
  Scenario: Create modern form with GIF as logo
	  When User performs "ModernFormWithGIFLogo"
	  
