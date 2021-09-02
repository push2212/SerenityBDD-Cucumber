@ADomain
Feature: Test Different generic fucntionalities	
  I want to use this feature file for testing all the generic functionalities 
  such as Compose ,TimeZone setup , notification preference , global searh and so on.
  
  
 @DomainLogin001
  Scenario: Login to Domain
  Given User has explicitly logged in to "desired" domain
     
    
  @Domain001
  Scenario: Set Default Landing Page to Company
	  When User sets default landing page to "CompanyLandingPage"
	  
	@Domain002
  Scenario: Domain TimZone Setup

	  When User performs "DomainTimezoneSetup"
	  
	@Domain003
  Scenario: Update Notification Preference 
 
 	  When A User "UpdateNotificationPreference" and Verify
	  
	@Domain004
  Scenario: DeActivate Recognition 
  
	  When Admin "DeActivateRecognition"
	  
	@Domain005
  Scenario: Share an Update from Compose
  
	  When User "SharesAnUpdate" from Compose
	    
	@Domain006
  Scenario: Ask a question from compose box
  Given User has explicitly logged in to "desired" domain
	  When User "AsksAQuestion" from Compose
	  
	@Domain007
  Scenario: Create a Post from Compose box
  Given User has explicitly logged in to "desired" domain
	  When User "CreatesAPost" from Compose
	  
	@Domain008
  Scenario: Create Wiki from Compose Box
  Given User has explicitly logged in to "desired" domain
	  When User "CreatesAWiki" from Compose
	  
	@Domain009
  Scenario: Create Form/tracker from Compose
  
	  When User "CreatesATracker" from Compose
	  
	@Domain010
  Scenario: Create Event from Compose
  
	  When User "CreatesAMeetingEvent" from Compose
	  
	@Domain011
  Scenario: Create Company Event from Compose
  
	  When User "CreatesACompanyEvent" from Compose
	  
	@Domain012
  Scenario: Create a Custom Event from Compose
  
	  When User "CreatesACustomEvent" from Compose
	  
	@Domain013
  Scenario: Make a global search and paginate through Search Results
  Given User has explicitly logged in to "desired" domain
	  When User "MakesGlobalSearch" and paginate through Search Results
	  
	@Domain014
  Scenario: Create a post as an announcement
  
	  When User "CreatesPostAsAnnouncement" from Compose
	  
	@Domain015
  Scenario: Create scheduled post from compose
  
	  When User "CreatesScheduledPost" from Compose
	  
	@Domain016
  Scenario: File Deep Search
  Given User has explicitly logged in to "desired" domain
	  When User performs "FileDeepSearch"
	  
	@Domain017
  Scenario: Change the order of Navigation Modules and Verify
  
	  When User performs "NavigationModuleOrderChange"
	  
	@Domain018
  Scenario: Duplicate a Wiki and Verify

	  When User performs "DuplicateWiki"
	  
 @Domain019
  Scenario: Duplicate a Post and Verify  
	  When User performs "DuplicatePost"	  
	
	 
	@Domain022
  Scenario: View post insights, boost the post and view updated  unique view count
 		Given User has explicitly logged in to "desired" domain
	  When User performs "ViewInsightsAndBoostPost"
	  Given User has explicitly logged in to "WithTestUser" domain
	  When User performs "ViewInsightsUniqueUser"
	  Given User has explicitly logged in to "desired" domain
	  When User performs "ViewInsightsUpdatedCount"	  

	  
 @Domain025
  Scenario: View post insights and download report 
 		Given User has explicitly logged in to "desired" domain
	  When User performs "ViewInsightsAndDownloadReport"
	  
	@Domain026
  Scenario: Create Form/tracker with multiple columns
  Given User has explicitly logged in to "desired" domain
	  When User "CreatesATrackerWithColumns" from Compose
	  
	@Domain027
  Scenario: Create tracker with Required/Primary columns  
	  When User "RequiredPrimaryTrackerColumns" from Compose
	  
	@Domain028
  Scenario: Create tracker with File attachment column 
	  When User "FileAttachmentTrackerColumn" from Compose
	  
	@Domain029
  Scenario: Search Tracker columns 
	  When User performs "TrackerSearch"
	  
	@Domain030
  Scenario: Edit Tracker entry in split and expand view 
	  When User performs "EditTracker"
	  
	@Domain031
  Scenario: Filter Tracker columns
	  When User performs "FilterTracker"
	  
	@Domain032
  Scenario: Create Must Read Post
  Given User has explicitly logged in to "desired" domain
	  When User performs "CreatesMustReadPost"
	  
	@Domain033
  Scenario: Archive/Unarchive Post
  When User performs "ArchiveUnarchivePost"
	  
	@Domain034
  Scenario: Create and verify Formula column in tracker 
	  When User "CreateFormulaColumn" from Compose
	  
	@Domain035
  Scenario: Create classic form with Image as logo
  Given User has explicitly logged in to "desired" domain
	  When User performs "ClassicFormWithImageLogo"
	  
	@Domain036
  Scenario: Create classic form with Icon as logo
	  When User performs "ClassicFormWithIconLogo"
	  
	@Domain037
  Scenario: Create classic form with GIF as logo
	  When User performs "ClassicFormWithGIFLogo"
	  
	@Domain038
  Scenario: Create modern form with Image as logo
	  When User performs "ModernFormWithImageLogo"
	  
	@Domain039
  Scenario: Create modern form with Icon as logo
	  When User performs "ModernFormWithIconLogo"
	  
	@Domain040
  Scenario: Create modern form with GIF as logo
	  When User performs "ModernFormWithGIFLogo"
	  
	@Domain041
  Scenario: Add form to widget/dashboard and submit from dashboard
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
	  
	 @Domain047
  Scenario: Add Quick Link on Dashboard
  Given User has explicitly logged in to "desired" domain
	  When User performs "AddQuickLink"
	  
	@Domain048
  Scenario: Add Library Conditional Quick Link  
	  When User performs "AddLibraryQuickLink"
	  
	  
	  
	
	  
	  
	  
	  
	  
	
	  
	  