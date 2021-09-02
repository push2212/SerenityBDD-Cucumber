@Users
Feature: Test Different scenarios for Users module
  I want to use this template for my feature file

 @USERS001
  Scenario: Import Users from a CSV File
  Given User has explicitly logged in to "desired" domain
	  When User imports "UsersfromCSV"  
	  	  
	@USERS002
  Scenario: Change User Profile and DP
	When A User changes "AKA" in User Profile and DP
	
	@USERS003
  Scenario: Search for a User from Global search
	When A User performs "UserSearch" from Global Search