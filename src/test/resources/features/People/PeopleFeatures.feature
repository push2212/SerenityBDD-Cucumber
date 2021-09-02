@People
Feature: Test Different scenarios for People module
  I want to use this template for my feature file

 @People001
  Scenario: Visit Colleagues Profile
  Given User has explicitly logged in to "desired" domain
	  When A User "VisitsColleaguesProfile"
	  
	@People002
  Scenario: Verify Pagination in People directory

	  When A User verifies Pagination in People directory