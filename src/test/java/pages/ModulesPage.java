package pages;


import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import net.serenitybdd.core.Serenity;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Steps;
import utilities.ReadWorkbook;
import utilities.ReportMessege;
import utilities.common;

public class ModulesPage extends PageObject {

	@Steps(shared=true)  
	private ReadWorkbook readWorkbook;

	@Steps(shared=true) 
	ReportMessege report;

	@Steps(shared=true)
	private common common;

	public String DomainURL = Serenity.sessionVariableCalled("domainURL").toString();

	@FindBy(xpath=".//li[@id='import_wizard_csv']")
	public WebElementFacade ImportUsersTab;

	@FindBy(xpath=".//*[@name='import_file']")
	public WebElementFacade BrowseButton;

	@FindBy(xpath=".//*[text()='Import Now']")
	public WebElementFacade ImportNowButton;
	//
	@FindBy(xpath=".//button[text()='Ok']")
	public WebElementFacade ImportOKButton;

	@Step
	public void enableAllModulesCheck(String logicalName) throws IOException, InterruptedException {
		try {
			String filePath = "\\src\\test\\resources\\TestData\\TestData.xlsx";
			String dataSheet = "Modules";
			Map <String,Map<Integer, String>> tableMap = ReadWorkbook.readRow(logicalName, filePath, dataSheet);		
			readWorkbook.testData(tableMap);
			for (int i = 0  ;i < tableMap.get("Row").size();i++) {								

				String SubURL = tableMap.get("SubURL").get(i);
				getDriver().get(DomainURL+SubURL);


				List<WebElement> modules = getDriver().findElements(By.xpath(".//*[@id='all_users']/div/ul/li"));
				try {
					for (int moduleNo = 1; moduleNo <= modules.size(); moduleNo++) {
						String currentModuleName = getDriver().findElement(By.xpath("//*[@id=\"all_users\"]/div/ul/li[" + moduleNo + "]/div[2]/div/div")).getText();
						String enableStatus = getDriver().findElement(By.xpath("//*[@id=\"all_users\"]/div/ul/li[" + moduleNo + "]/div[3]/div/input")).getAttribute("data-enable");
						if (!currentModuleName.equalsIgnoreCase("Levels and badges")) {
							if (enableStatus.equalsIgnoreCase("true") || enableStatus.equalsIgnoreCase("Y")) {
								WebElement enableCheck;
								report.Info("Current module name is: " + currentModuleName+ " and Its earlier staus is: Not Enabled");

								if (moduleNo > 7) {
									enableCheck = getDriver().findElement(By.xpath("//*[@id=\"all_users\"]/div/ul/li[" + (moduleNo - 3) + "]/div[3]"));
									common.scrollToView( enableCheck);
									report.Info("Scrolling the desired Module into view");								
									Thread.sleep(1000);
								}
								getDriver().findElement(By.xpath("//*[@id=\"all_users\"]/div/ul/li[" + moduleNo + "]/div[3]")).click();
								report.Info("Enabled the user Module successfully");							
							} else {
								report.Info("Current module name is: " + currentModuleName + " and Its earlier staus is: Enabled");
							}
						} else {
							if (enableStatus.equalsIgnoreCase("false") || enableStatus.equalsIgnoreCase("N")) {
								WebElement enableCheck;
								if (moduleNo > 4) {
									enableCheck = getDriver().findElement(By.xpath("//*[@id=\"all_users\"]/div/ul/li[" + (moduleNo - 4) + "]/div[3]"));
									common.scrollToView( enableCheck);
									report.Info("Scrolling the desired Module into view");	
								}
								Thread.sleep(500);
								getDriver().findElement(By.xpath("//*[@id=\"all_users\"]/div/ul/li[" + moduleNo + "]/div[3]")).click();
								report.Info("Disabled the Levels and Badges successfully");	
							} else {
								report.Info( "Levels and badges is already in disabled status");							
							}
						}

					}
				} 
				catch (Exception e) {
					//ExtentTestManager.getTest().log(Status.FAIL, "Error in try block " + e.toString());
				}

			}
		}catch(Exception e){
			getDriver().navigate().refresh();
			Thread.sleep(3000);
			Assert.assertTrue(false);
		}
	}


}

