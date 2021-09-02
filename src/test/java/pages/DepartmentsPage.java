package pages;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import net.serenitybdd.core.Serenity;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Steps;
import utilities.ReadWorkbook;
import utilities.ReportMessege;
import utilities.common;
import pages.ProjectPage;

public class DepartmentsPage extends PageObject {


	public String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
	public String DomainURL = Serenity.sessionVariableCalled("domainURL").toString();;

	@Steps(shared=true) 
	private ReadWorkbook readWorkbook;

	@Steps(shared=true) 
	private ReportMessege report;

	@Steps(shared=true)
	private common common;

	@Steps(shared=true)
	private ProjectPage projectPage;

	@FindBy(xpath=".//span[@class='tools_setting_icon']")
	public WebElementFacade DepartmentTools;

	@FindBy(xpath=".//a[@class='create-department']")
	public WebElementFacade CreateNewDepartment;

	@FindBy(id="description")
	public WebElementFacade ShortDescription;

	@FindBy(xpath=".//*[@class='remind-me-button']")
	public WebElementFacade RemindMeLater;

	@FindBy(xpath=".//*[@title='Universal compose box']")
	public WebElementFacade ComposeButton;

	@FindBy(xpath=".//a[text()='Login Into Your MangoApps Domain']")
	public WebElementFacade LoginToDomain;


	@Step
	public void departmentCreationCheck(String logicalName) throws IOException, InterruptedException {

		String filePath = "\\src\\test\\resources\\TestData\\TestData.xlsx";
		String dataSheet = "Departments";
		Map <String,Map<Integer, String>> tableMap = ReadWorkbook.readRow(logicalName, filePath, dataSheet);	
		readWorkbook.testData(tableMap);
		for (int i = 0  ;i < tableMap.get("Row").size();i++) {
			String SubURL = tableMap.get("SubURL").get(i);
			String expectedDeptName = tableMap.get("DepartmentName").get(i);
			
			getDriver().get(DomainURL+SubURL);	

			report.Info("Entered into department module");
			Thread.sleep(2000);
			//DepartmentTools.waitUntilClickable();
			common.waitForElement(DepartmentTools);
			DepartmentTools.click();
			report.Info("Clicked on department tools");
			CreateNewDepartment.waitUntilVisible();
			CreateNewDepartment.click();
			report.Info("Selected the option to create department");

			WebElementFacade DeptartmentNameTextBox = projectPage.NameTextBox;
			DeptartmentNameTextBox.waitUntilEnabled();
			expectedDeptName = expectedDeptName + dateName;
			DeptartmentNameTextBox.sendKeys(expectedDeptName);
			report.Info("Entered the department Name");
			ShortDescription.typeAndTab("This is to automation test department creation flow");
			report.Info("Entered the short description for it");
			common.scrollToView( projectPage.SaveAndContinue);
			projectPage.SaveAndContinue.waitUntilEnabled();
			common.clickElement( $(".//a[text()='Save & Continue']"));
			//projectPage.SaveAndContinue.click();;
			report.Info("Clicked on Save and Continue");
			//common.waitForElement(projectPage.SaveAndExit);
			common.WaitForObjectVisibility(".//a[contains(text(),'Save & Exit')]");
			$(".//a[contains(text(),'Save & Exit')]").click();
			report.Info("Clicked on Save and Exit");
			WebElementFacade actualDeptName = find(By.xpath(".//span[contains(text(), '" + expectedDeptName + "')]"));

			if (actualDeptName.getText().equalsIgnoreCase(expectedDeptName)) {
				report.Info(expectedDeptName + " Department is created Successfully");
			}else {
				report.Info(expectedDeptName + " Department is Not created Successfully");
				Assert.assertTrue(false);

			}
		} 
	}
}
