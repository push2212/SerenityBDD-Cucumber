package main.stepdefinitions;


import java.io.IOException;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;
import pages.DepartmentsPage;

public class DepartmentsSteps {

	@Steps
	DepartmentsPage departmentsPage;

	@When("User {string} and Verify it")
	public void create_department(String logicalName) throws IOException, InterruptedException {		
		departmentsPage.departmentCreationCheck(logicalName);
	}


}
