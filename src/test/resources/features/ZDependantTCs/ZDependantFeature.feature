@ZDependant
Feature: Dependant Test Cases
  I want to use this template for my feature file
  
  @ZDomain001
  Scenario: Check two way chat functionality
 	 Given User has explicitly logged in to "desired" domain
	  When We check two way chat functionality with "AdminChat"
	  And User logs out from domain
	  Given User has explicitly logged in to "WithTestUser" domain
	  When We check two way chat functionality with "TestUserChat"
	  And User logs out from domain
	  Given User has explicitly logged in to "desired" domain
	  
	@ZDomain002
  Scenario: Verify Wiki, Post, Page View Counter
 
	  When User performs "ViewCountercheck"
	  
	@ZDomain003
  Scenario: Suspend a Network User and re-Activate
  Given User has explicitly logged in to "desired" domain
	When A User performs "DeactivateUser" action on user tools
	And A User performs "GoToUserPortal" action in user settings
	And User logs out from domain
	Given User has explicitly logged in to "DeactivatedTestUser" domain
	And User has explicitly logged in to "desired" domain
	When A User performs "ActivateUser" action on user tools
	And A User performs "GoToUserPortal" action in user settings
	And User logs out from domain
	Given User has explicitly logged in to "WithTestUser" domain
	When User logs out from domain
	Given User has explicitly logged in to "desired" domain

	  
	@ZDomain004
  Scenario: Create new file inside a Project by using Office365 Online
  Given User has explicitly logged in to "desired" domain
	  When User Creates file in Office365
	  
	

 @ZDomainLogOut005
  Scenario: User logs out from domain
  Given User has explicitly logged in to "desired" domain
	  When User logs out from domain  
	  

	  