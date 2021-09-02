@Groups
Feature: Test Different scenarios for Groups module
  I want to use this template for my feature file

   
  @GROUPS001
  Scenario: Verify the default groups creation
  Given User has explicitly logged in to "desired" domain
	  Then Verify if "defaultGroupsPresent"
	  
	@GROUPS002
  Scenario: Verify that all invited users got added to everyone Group	
  
	  Then Verify if "defaultGroupsPresent"
	  
	@GROUPS003
  Scenario: Add guest user to Everyone Group	 
   Given User has explicitly logged in to "desired" domain
	  When "AddGuestUser" to Everyone Group
	  
	@GROUPS004
  Scenario: Verify updated user count in Everyone Group	
  
	  When "CheckUpdateduserCount" to Everyone Group