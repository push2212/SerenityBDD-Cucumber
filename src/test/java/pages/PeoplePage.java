package pages;


import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Steps;
import utilities.ReadWorkbook;
import utilities.ReportMessege;
import utilities.common;

public class PeoplePage extends PageObject {

	@Steps(shared=true)  
	private ReadWorkbook readWorkbook;

	@Steps(shared=true) 
	ReportMessege report;

	@Steps(shared=true)
	private common common;

	public String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());

	@FindBy(xpath=".//input[@class='data-search-input ma_data_search_input']")
	public WebElementFacade FindPeople;


	@Step
	public void visitColleagueProfile(String logicalName) throws IOException, InterruptedException {
	//	try {
			String filePath = "\\src\\test\\resources\\TestData\\TestData.xlsx";
			String dataSheet = "People";
			Map <String,Map<Integer, String>> tableMap = ReadWorkbook.readRow(logicalName, filePath, dataSheet);		
			readWorkbook.testData(tableMap);
			for (int i = 0  ;i < tableMap.get("Row").size();i++) {		

				String DomainURL = Serenity.sessionVariableCalled("domainURL").toString();
				String SubURL = tableMap.get("SubURL").get(i);
				getDriver().get(DomainURL+SubURL);

				FindPeople.waitUntilVisible();
				String colleagueName = "Mango Robo";
				FindPeople.sendKeys(colleagueName);
				Thread.sleep(2000);
				FindPeople.sendKeys(Keys.ENTER);
				Thread.sleep(2000);	
				report.Info("entered colleague user name in find people");
				//Thread.sleep(3000);		
				WebElementFacade testUserProfile= find(By.xpath(".//table[@class='mango-grid-img-box']/following::div[@class='name-role ma-h4']/p/a[@title='"+colleagueName+"']"));
				common.waitForElement(testUserProfile);
				testUserProfile.click();
				report.Info("Clicked on user's profile");
				Thread.sleep(1000);
				find(By.xpath(".//li[text()='"+colleagueName+"']")).shouldBeVisible();
				report.Info("Opened colleague profile");

			}
			/*
			 * }catch(Exception e){ getDriver().navigate().refresh(); Thread.sleep(3000);
			 * Assert.assertTrue(false); }
			 */
	}

	public void paginationInPeopleDirectoryCheck() throws InterruptedException {
		try {
			// This methods adds some new users in the domain and checks if the system is by
			// default adding
			// them to Everyone group
			String DomainURL = Serenity.sessionVariableCalled("domainURL").toString();
			getDriver().get(DomainURL+"/sites/peoples/people_directory");

			report.Info("Entered into People Module");
			common.scrollToPageEnd();
			report.Info("First Page scroll is completed");	
			common.WaitForObjectPresence(".//*[text()='Fetching more...'][@style='visibility: hidden;']");

			common.scrollToPageEnd();
			report.Info("Second Page scroll is completed");
			common.WaitForObjectPresence(".//*[text()='Fetching more...'][@style='visibility: hidden;']");

			common.scrollToPageEnd();
			report.Info("Third Page scroll is completed");
			common.WaitForObjectPresence(".//*[text()='Fetching more...'][@style='visibility: hidden;']");

			common.scrollToPageEnd();
			report.Info("Final Page scroll is completed");
			common.WaitForObjectPresence(".//*[text()='Fetching more...'][@style='visibility: hidden;']");

			report.Info("Pagination is working properly in People directory");
		}catch(Exception e){
			getDriver().navigate().refresh();
			Thread.sleep(3000);
			Assert.assertTrue(false);
		}
	}


}
