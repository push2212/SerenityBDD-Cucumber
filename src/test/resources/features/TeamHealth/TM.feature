@TeamHealth
Feature: Test Different fucntionalities for Team Health
  I want to use this feature file for testing all the generic functionalities 
  such as Compose ,TimeZone setup , notification preference , global searh and so on.
  
  
 @TMLogin001
  Scenario: Login to TM
    Given User has explicitly logged in to "desired" domain
     
  @TM001
  Scenario: Set Default Landing Page to dashboard
	  When User sets default landing page to "DashboardLandingPage"
	  
	@TM002
  Scenario: Create a Post from Compose box
  Given User has explicitly logged in to "desired" domain
	  When User "CreatesAPostToTeam" from Compose
	  
	@TM003
  Scenario: Create Event from Compose
  
	  When User "CreatesAMeetingEventToTeam" from Compose
	  
	@TM004
  Scenario: Create a Custom Event from Compose
  
	  When User "CreatesACustomEventToTeam" from Compose
	  
	@TM005
  Scenario: Create a post as an announcement
  
	  When User "CreatesPostAsAnnouncementToTeam" from Compose
	  
	@TM006
  Scenario: Create scheduled post from compose
  
	  When User "CreatesScheduledPostToTeam" from Compose
	  
	  @TM008
  Scenario: Create Wiki from Compose Box
  Given User has explicitly logged in to "desired" domain
	  When User "CreatesAWiki" from Compose
	  
	  @TM020
  Scenario: Duplicate a Wiki and Verify

	  When User performs "DuplicateWiki"
	  
	  @TM021
  Scenario: Duplicate a Post and Verify  
	  When User performs "DuplicatePost"
	 
	@TM009
  Scenario: View post insights, boost the post and view updated  unique view count
 		Given User has explicitly logged in to "desired" domain
	  When User performs "ViewInsightsAndBoostPost"
	  Given User has explicitly logged in to "TestUser" domain
	  When User performs "ViewInsightsUniqueUser"
	  Given User has explicitly logged in to "desired" domain
	  When User performs "ViewInsightsUpdatedCount"	  
	  
	@TM010
  Scenario: View post insights and download report 
 		Given User has explicitly logged in to "desired" domain
	  When User performs "ViewInsightsAndDownloadReport"
	  
	@TM011
  Scenario: Create tracker from Compose
  
	  When User "CreatesATrackerToTeam" from Compose
	  	  
	 @TM014
  Scenario: Create a static Page with Media in a project
  Given User has explicitly logged in to "desired" domain
	  When User Creates a "GroupstaticPagewithMedia" in a project or group
	  Then Verify if page edit is successful
	  
	@TM015
  Scenario: Create group
  Given User has explicitly logged in to "desired" domain
	 When User "CreatesGroup" using a project template
	  
	@TM016
  Scenario: Upload a text file and Verify its Conversion
  Given User has explicitly logged in to "desired" domain
	  When User "GroupUploadsTextFile" and Verifies its Conversion
	  
	@TM017
  Scenario: Upload a new Version of Text File and then Verify  	
	  When User "GroupUploadsNewVersionTextFile" and Verify
	  
	@TM018
  Scenario: Check out a file and then Check In  	
	  When A User Check out a file and then Check In  
	  
	@TM019
  Scenario: Create a Dynamic Page
  Given User has explicitly logged in to "desired" domain
	  When User Creates a "GroupDynamicPage" in a project or group
	  Then Verify if page edit is successful
	  
	@TM020
  Scenario: Upload a Video and Verify its Conversion
  Given User has explicitly logged in to "desired" domain
	  When User "GroupcontainsUploadsAVideo" and Verifies its Conversion
	  
	@TM021
  Scenario: Create Form/tracker with multiple columns
  Given User has explicitly logged in to "desired" domain
	  When User "CreatesATrackerWithColumns" from Compose
	  
	@TM022
  Scenario: Create tracker with Required/Primary columns  
	  When User "RequiredPrimaryTrackerColumns" from Compose
	  
	@TM023
  Scenario: Create tracker with File attachment column 
	  When User "FileAttachmentTrackerColumn" from Compose
	  
	@TM024
  Scenario: Search Tracker columns 
	  When User performs "TrackerSearch"
	  
	@TM025
  Scenario: Edit Tracker entry in split and expand view 
	  When User performs "EditTracker"
	  
	@TM026
  Scenario: Filter Tracker columns
	  When User performs "FilterTracker"
	  
	@TM027
  Scenario: Create Must Read Post
  Given User has explicitly logged in to "desired" domain
	  When User performs "CreatesMustReadPost"

	  
	@TM029
  Scenario: Create and verify Formula column in tracker 
	  When User "CreateFormulaColumn" from Compose
	  
	@TM030
  Scenario: Create classic form with Image as logo
  Given User has explicitly logged in to "desired" domain
	  When User performs "ClassicFormWithImageLogo"
	  
	@TM031
  Scenario: Create classic form with Icon as logo
	  When User performs "ClassicFormWithIconLogo"
	  
	@TM032
  Scenario: Create classic form with GIF as logo
	  When User performs "ClassicFormWithGIFLogo"
	  
	@TM033
  Scenario: Create modern form with Image as logo
	  When User performs "ModernFormWithImageLogo"
	  
	@TM034
  Scenario: Create modern form with Icon as logo
	  When User performs "ModernFormWithIconLogo"
	  
	@TM035
  Scenario: Create modern form with GIF as logo
	  When User performs "ModernFormWithGIFLogo"
	 
	 @Domain041
  Scenario: Add form to widget/dashboard and submit from dashboard
   Given User has explicitly logged in to "desired" domain
	  When User performs "AddFormToWidgetDashboard"
	  
	@Domain042
  Scenario: Share and submit the form
	  When User performs "ShareForm"
	  
	@Domain043
  Scenario: Create tracker table with filter and group by
  Given User has explicitly logged in to "desired" domain
	  When User performs "CreateTrackerTable"
	 
	@Domain044
  Scenario: Duplicate tracker table with column hidden
	  When User performs "DuplicateTrackerTable"
	  
	@Domain045
  Scenario: Add table to widget/dashboard
	  When User performs "AddTableToWidgetDashboard"
	  
	@Domain046
  Scenario: Share and add entry to table from non logged in user
	  When User performs "ShareTable"
	  
