package utilities;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import net.serenitybdd.core.Serenity;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Steps;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

import utilities.ReportMessege;

public class common extends PageObject {

	@Steps(shared = true)
	ReportMessege report;

	@Steps(shared = true)
	private common common;

	int firstRow, lastRow;
	int totalRow = -1;
	public int selectedRow = 1;

	public static Map<String, String> DictionaryTest_G = new HashMap<String, String>();

	public static boolean isAlertPresent(WebDriver driver) {
		try {
			driver.switchTo().alert();
			return true;
		} catch (NoAlertPresentException ex) {
			return false;
		}
	}

	public static boolean acceptAlert(WebDriver driver) {
		try {
			driver.switchTo().alert().accept();
			return true;
		} catch (NoAlertPresentException ex) {
			return false;
		}
	}

	@FindBy(xpath = ".//*[contains(text(),'Halo - please read')]")
	public WebElementFacade ErrorTitleBox;
	@FindBy(xpath = ".//div[contains(@class,'popup-info')]  ")
	public WebElementFacade ErrorMsg;
	@FindBy(xpath = ".//button[contains(@class,'confirm-popup')]")
	public WebElementFacade ErrorOkButton;

	@FindBy(xpath = ".//*[contains(text(),'Halo - please read')]")
	WebElement ErrorTitleBoxSync;

	public void HandleErrorPopUp(String sPopup) throws IOException, AWTException, InterruptedException {
		common.waitForElement(ErrorTitleBoxSync);
		if (ErrorTitleBox.isCurrentlyVisible() && (!sPopup.equalsIgnoreCase("No"))) {
			String ErrorInfo = ErrorMsg.getText();

			if (sPopup.contains("OPTIONAL:")) {
				sPopup = sPopup.substring(9);
			}
			if (ErrorInfo.contains(sPopup)) {
				//// sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
				ErrorOkButton.withTimeoutOf(120, TimeUnit.SECONDS).waitUntilClickable();
				ErrorOkButton.click();
				report.Info("The alert meaasage is:" + ErrorInfo);
			} else {
				// //sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
				report.Info("UnExpected Pop did occured");
				Assert.assertTrue(false);
			}

		} else if (!ErrorTitleBox.isCurrentlyVisible() && (sPopup.contains("OPTIONAL:"))) {
			report.Info("OPTIONAL Pop did not occur");
			Assert.assertTrue(true);
		}

		else if (!ErrorTitleBox.isCurrentlyVisible() && (!sPopup.equalsIgnoreCase("No"))) {
			report.Info("Expected Pop did not occur");
			Assert.assertTrue(false);
		} else if (ErrorTitleBox.isCurrentlyVisible() && (sPopup.equalsIgnoreCase("No"))) {
			report.Info("UnExpected Pop did occured");
			Assert.assertTrue(false);
		}
	}

	public void waitForPageLoad(WebDriver driver) {
		ExpectedCondition<Boolean> pageLoadCondition = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
			}
		};
		WebDriverWait wait = new WebDriverWait(driver, 150);
		wait.until(pageLoadCondition);
	}

	public boolean waitForElement(WebElement w) {
		try {
			WebDriverWait wait = new WebDriverWait(getDriver(), 30);
			wait.until(ExpectedConditions.elementToBeClickable(w));

			return true;
		} catch (Exception e) {
			return false;
		}

	}

	public boolean waitForElement(WebElementFacade w) {
		try {
			WebDriverWait wait = new WebDriverWait(getDriver(), 30);
			wait.until(ExpectedConditions.elementToBeClickable(w));

			return true;
		} catch (Exception e) {
			return false;
		}

	}

	public boolean waitForElement30(WebElement w) {
		try {
			WebDriverWait wait = new WebDriverWait(getDriver(), 30);
			wait.until(ExpectedConditions.elementToBeClickable(w));

			return true;
		} catch (Exception e) {
			return false;
		}

	}

	public boolean waitForElement(By w) {
		try {
			WebDriverWait wait = new WebDriverWait(getDriver(), 30);
			wait.until(ExpectedConditions.elementToBeClickable(w));

			return true;
		} catch (Exception e) {
			return false;
		}

	}

	public boolean WaitForClock(WebElement Clockobj) {
		try {
			WebDriverWait wait = new WebDriverWait(getDriver(), 30);
			wait.until(ExpectedConditions.presenceOfElementLocated(
					By.xpath(".//div[contains(@style,'display: none;')][ @id='maskoverlay']")));
			return true;

		} catch (Exception e) {
			return false;
		}

	}

	public void PageRefresh() {
		try {
			Robot robot = new Robot();
			robot.keyPress(KeyEvent.VK_F5);
			robot.keyRelease(KeyEvent.VK_F5);
			WebDriverWait wait = new WebDriverWait(getDriver(), 150);
			wait.until(ExpectedConditions.presenceOfElementLocated(
					By.xpath(".//div[contains(@style,'display: none;')][ @id='maskoverlay']")));
		} catch (Exception e) {

		}
	}

	public boolean WaitForObjectPresence(String Object) {
		try {
			WebDriverWait wait = new WebDriverWait(getDriver(), 30);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(Object)));
			return true;
		} catch (Exception e) {
			return false;

		}
	}
	
	public boolean WaitForObjectAbsence(String Object) {
		try {
			WebDriverWait wait = new WebDriverWait(getDriver(), 30);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(Object)));
			return true;
		} catch (Exception e) {
			return false;

		}
	}

	public boolean WaitForObjectVisibility(String Object) {
		try {
			WebDriverWait wait = new WebDriverWait(getDriver(), 30);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Object)));
			return true;
		} catch (Exception e) {
			return false;

		}
	}

	public void Meta(String Meta) throws IOException {
		String currentDir = System.getProperty("user.dir");
		FileWriter fr = new FileWriter(currentDir + "\\Performance.txt", true);
		BufferedWriter br = new BufferedWriter(fr);
		br.newLine();
		br.write("--------------------------------------------------------");
		br.newLine();
		br.write(Meta);
		br.newLine();
		br.close();
	}

	public void WritePerformanceOutput(String sPerformance) throws IOException {
		String currentDir = System.getProperty("user.dir");
		FileWriter fr = new FileWriter(currentDir + "\\Performance.txt", true);
		BufferedWriter br = new BufferedWriter(fr);
		br.newLine();
		br.write(sPerformance);
		br.close();
	}

	public void HandleAlert(String Popup) throws IOException, AWTException, InterruptedException {

		// Intialising variables
		String sPopup = "";
		Alert alert;
		String ActAlertText = "";

		// Code to handle any popup
		if (Popup.equalsIgnoreCase("ACCEPTALERT")) {
			if (isAlertPresent(getDriver())) {
				alert = getDriver().switchTo().alert();
				ActAlertText = alert.getText();
				ActAlertText = alert.getText();
				//// sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
				report.Info("alert messsage is" + ActAlertText);
				alert.accept();
				return;
			}
		}
		// code to handle specific popup
		if (!Popup.equals("")) {
			sPopup = Popup;
			if (sPopup.equals("")) {
				sPopup = "No";
			}

			if (isAlertPresent(getDriver()) && (!sPopup.equalsIgnoreCase("No"))) {
				alert = getDriver().switchTo().alert();
				ActAlertText = alert.getText();
				//// sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
				if (sPopup.contains("OPTIONAL:")) {
					sPopup = sPopup.substring(9);
				}
				if (ActAlertText.contains(sPopup)) {
					alert.accept();
					report.Info("alert messsage is" + ActAlertText);
				}
			} else if (!isAlertPresent(getDriver()) && (sPopup.contains("OPTIONAL:"))) {
				report.Info("OPTIONAL Alert did not occur");
				Assert.assertTrue(true);
			} else if (!isAlertPresent(getDriver()) && (!sPopup.equalsIgnoreCase("No"))) {
				report.Info("Expected Alert did not occur");
				Assert.assertTrue(false);
			} else if (isAlertPresent(getDriver()) && (sPopup.equalsIgnoreCase("No"))) {
				report.Info("UnExpected Alert occured");
				//// sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
				Assert.assertTrue(false);
			}

		}

	}

	public void applyWait(String timeInMiliSec) {

		try {
			Thread.sleep(Long.valueOf(timeInMiliSec).longValue());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void uploadImageViaAutoIT(WebDriver driver, String filePath) {
		String dir = System.getProperty("user.dir");
		try {
			Thread.sleep(1000); // wait for page load
			Process process = Runtime.getRuntime().exec(dir + filePath); // Give path where the exe is saved

			report.Info("autoIT command executed");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String onPaste() throws UnsupportedFlavorException, IOException {
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Clipboard clipboard = toolkit.getSystemClipboard();
		String result = (String) clipboard.getData(DataFlavor.stringFlavor);
		report.Info("String from Clipboard:" + result);
		return result;
	}

	public void upload(WebDriver driver, String filePathName) {
		try {
			// Store the location of the file in clipboard
			// Clipboard
			StringSelection strSel = new StringSelection(filePathName);
			Clipboard clip = Toolkit.getDefaultToolkit().getSystemClipboard();
			Thread.sleep(500);
			clip.setContents(strSel, null);
			// Create an object for robot class
			Robot robot = new Robot();
			// Control key in the keyboard
			// Ctrl+V
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_V);
			Thread.sleep(500);
			robot.keyRelease(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_CONTROL);
			Thread.sleep(1000);
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);

			report.Info("Uploaded Given file located at path " + filePathName + " Successfully");

		} catch (Exception e) {
			report.Info("Error in try block: " + e.toString());
			Assert.assertTrue(false);
		}
	}

	public void scrollToView( WebElement element) {
		((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", element);
	}

	public void scrollToView( WebElementFacade element) {
		((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", element);

	}

	public void clickElement(WebElementFacade element) {
		JavascriptExecutor js = (JavascriptExecutor) getDriver();
		js.executeScript("arguments[0].click();", element);
	}
	
	public void enterText(WebElementFacade element ,String text) {
		JavascriptExecutor js = (JavascriptExecutor) getDriver();
		js.executeScript("arguments[0].value='"+text+"';", element);
	}


	public void clickElement( WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor)getDriver();
		js.executeScript("arguments[0].click();", element);
	}

	public void clickCheckbox( WebElementFacade element) {
		JavascriptExecutor js = (JavascriptExecutor) getDriver();
		js.executeScript("arguments[0].checked=true;", element);
	}

	public void mouseHover( WebElement element) {
		String strJavaScript = "var element = arguments[0]; var mouseEventObj = document.createEvent('MouseEvents'); mouseEventObj.initEvent( 'mouseover', true, true ); element.dispatchEvent(mouseEventObj);";
		((JavascriptExecutor) getDriver()).executeScript(strJavaScript, element);
	}

	public void mouseHover(WebElementFacade element) {
		String strJavaScript = "var element = arguments[0]; var mouseEventObj = document.createEvent('MouseEvents'); mouseEventObj.initEvent( 'mouseover', true, true ); element.dispatchEvent(mouseEventObj);";
		((JavascriptExecutor) getDriver()).executeScript(strJavaScript, element);
	}

	public void scrollToPageEnd() throws InterruptedException {
		((JavascriptExecutor) getDriver()).executeScript("window.scrollTo(0, document.body.scrollHeight)");
		Thread.sleep(2000);
	}

	public boolean retryingFindClick(WebElementFacade element) throws InterruptedException {
		boolean result = false;
		int attempts = 0;
		while (attempts < 3) {
			try {
				element.click();
				result = true;
				break;
			} catch (StaleElementReferenceException e) {
				report.Info("Got Stale Element Exception , will retry click");
				if (attempts == 1) {
					getDriver().navigate().refresh();
					Thread.sleep(3000);
				}
			}
			attempts++;
		}
		if (result == false) {
			Assert.assertTrue(false);
			report.Info("Got Stale Element Exception even after 3 tries");
		}
		return result;
	}

	public void selectFromAutocomplete(WebElementFacade element, String autoCompleteOption) {

		element.sendKeys(autoCompleteOption);

		WebDriverWait wait = new WebDriverWait(getDriver(), 10);
		wait.until(
				ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(".//ul[@style='display: block;']//li")));

		List<WebElement> list = getDriver().findElements(By.xpath(".//ul[@style='display: block;']//li"));

		System.out.println("Auto Suggest List ::" + list.size());

		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i).getText());

			if (list.get(i).getText().equals(autoCompleteOption)) {
				list.get(i).click();
				break;
			}
		}

	}

	public String getTommorrowsDate() {
		Date date = new Date(); // your date
		// Choose time zone in which you want to interpret your Date
		Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT+5:30"));
		cal.setTime(date);
		cal.add(Calendar.DAY_OF_MONTH, +1); // this makes calendar to point to tomorrow's date
		// int year = cal.get(Calendar.YEAR);
		// System.out.println(year);
		// int month = cal.get(Calendar.MONTH);
		int day = cal.get(Calendar.DAY_OF_MONTH);
		return Integer.toString(day);
	}

	public int findRowCountOfWebTable(String rowPath) {
		List<WebElement> rowSize = getDriver().findElements(By.xpath(rowPath));
		return rowSize.size();
	}

	public int findColCountOfWebTable(String rowPath) {
		List<WebElement> rowSize = getDriver().findElements(By.xpath(rowPath));
		int rowCount = rowSize.size();
		List<WebElement> colSize = getDriver().findElements(By.xpath(rowPath + "[" + rowCount + "]/td"));
		return colSize.size();
	}

	public void datePicker(String dateNumber) {
		WebElementFacade postScheduleDate = find(By.xpath(".//input[@id='post_schedule_date']"));
		postScheduleDate.click();
		WebElementFacade nextMonth = find(By.xpath(".//a[@data-handler='next']"));
		nextMonth.click();
		report.Info("Clicked on next month option of calander");

		String rowPath = "//*[@id='ui-datepicker-div']/div[2]/table/tbody/tr";
		int rowSize = common.findRowCountOfWebTable(rowPath);
		int colSize = common.findColCountOfWebTable(rowPath);
		for (int i = 1; i <= rowSize; i++) {
			for (int j = 1; j <= colSize; j++) {
				String dateValue = rowPath + "[" + i + "]/td[" + j + "]";
				String date = getDriver().findElement(By.xpath(dateValue)).getText();
				if (date.equalsIgnoreCase(dateNumber)) {
					WebElement actualDate = getDriver().findElement(By.xpath(dateValue));
					clickElement( actualDate);
					report.Info("Clicked on the date " + dateNumber + " as given by user");
					break;
				}
			}
		}
	}

	public boolean isFileDownloaded(String downloadPath, String fileName) {
		boolean flag = false;
		File dir = new File(downloadPath);
		File[] dir_contents = dir.listFiles();

		for (int i = 0; i < dir_contents.length; i++) {
			if (dir_contents[i].getName().equals(fileName))
				return flag = true;
		}

		return flag;
	}

	/* Get the latest file from a specific directory */
	public File getLatestFilefromDir(String dirPath) {
		File dir = new File(dirPath);
		File[] files = dir.listFiles();
		if (files == null || files.length == 0) {
			return null;
		}

		File lastModifiedFile = files[0];
		for (int i = 1; i < files.length; i++) {
			if (lastModifiedFile.lastModified() < files[i].lastModified()) {
				lastModifiedFile = files[i];
			}
		}
		return lastModifiedFile;
	}

	@Step
	public void readTable(String columnsToVerify,String valuesToVerify) {
		String headerText="";
		String celtext ="";
		WebElementFacade table = $("(.//table[@class='htCore'])[1]");
		//WebElementFacade tableHeaders = $(("(.//table[@class='htCore'])[1]/thead"));
		// To locate rows of table.
		List<WebElementFacade> rows_table = table.thenFindAll(By.tagName("tr"));
		// To calculate no of rows In table.
		int h=0;
		List<String> columnList = new ArrayList<String>();
		List<String> headerList = new ArrayList<String>();
		int rows_count = rows_table.size();
		// Loop will execute till the last row of table.
		for (int row = 0; row < rows_count; row++) {
			// To locate columns(cells) of that specific row.
			List<WebElement> Columns_row = rows_table.get(row).findElements(By.tagName("td"));
			//List<WebElement> header_row = rows_header.get(row).findElements(By.tagName("th"));
			// To calculate no of columns (cells). In that specific row.
			int columns_count = Columns_row.size();
			int header_Count = columns_count+1;
			//System.out.println("Number of cells In Row " + row + " are " + columns_count);
			// Loop will execute till the last cell of that specific row.

			for ( h = 5; h <= header_Count; h++) {

				headerText = ($("(.//table[@class='htCore'])[1]/thead/tr/th["+h+"]/div/span/span").getTextContent());
				//report.Info("Value of header  Is " + headerText);								
				headerList.add(headerText);
			}

			for (int column = 3; column < columns_count; column++) {				
				// To retrieve text from that specific cell\
				celtext = Columns_row.get(column).getText();
				//report.Info("Value of column  Is " + celtext);
				columnList.add(celtext);
			}

		}		
		int size =headerList.size();
		String[] colarr = columnsToVerify.split(Pattern.quote(","));
		String[] valarr = valuesToVerify.split(Pattern.quote(","));
		
		int colSize = colarr.length;

		for (int v = 0; v < size; v++) {
			if (v == colSize) {
				break;
			}

			if(!valuesToVerify.equals("")) {
				if((colarr[v].contains(headerList.get(v)))&&(valarr[v].contains(columnList.get(v)))) {
					report.Info("Value of column "+colarr[v] + " is " +valarr[v] + " as expected");
				}else {
					report.Info("Value of column "+colarr[v] + " is " +valarr[v] + " not as expected");
					Assert.assertTrue(false);
				}
			}else {
				report.Info("Header: "+headerList.get(v)+ " value: "+columnList.get(v));
				if(headerList.get(v).contains(colarr[v])) {
					report.Info("column "+colarr[v] + " is present as expected");
				}else {
					report.Info("column "+colarr[v] + " should not be present");
					Assert.assertTrue(false);
				}
			}
		}
	} 

	@Step
	public String locateColumn(String table,String sLocateCol,String sLocateColValue,String index){
		try {
			boolean flag=false;
			// simplified: find table which contains the keyword
			WebElementFacade tableElement = $(table);
			String tempValue="";
			String actualValue = "";
			// create empty table object and iterate through all rows of the found table element
			ArrayList<HashMap<String, WebElement>> Table = new ArrayList<HashMap<String, WebElement>>();
			List<WebElement> rowElements = tableElement.findElements(By.xpath(".//tr"));
			for(int i=1;i<=rowElements.size();i++) {
				// get column names of table from table headers
				ArrayList<String> columnNames = new ArrayList<String>();
				List<WebElement> headerElements = rowElements.get(0).findElements(By.xpath(".//th"));
				for (WebElement headerElement: headerElements) {
					columnNames.add(headerElement.getText());
				}

				// iterate through all rows and add their content to table arrayd
				for (WebElement rowElement: rowElements) {
					HashMap<String, WebElement> row = new HashMap<String, WebElement>();

					// add table cells to current row
					int columnIndex = 0;
					List<WebElement> cellElements = rowElement.findElements(By.xpath(".//td"));
					for (WebElement cellElement: cellElements) {
						row.put(columnNames.get(columnIndex), cellElement);
						columnIndex++;
					}

					Table.add(row);
				}
				Map<String,String> map1 = new HashMap<String,String>();
				String[] arr1 = sLocateCol.split(Pattern.quote(","));
				String[] arr2 = sLocateColValue.split(Pattern.quote(","));


				for (int k =0; k < arr1.length; k++){
					map1.put(arr1[k], arr2[k]);
				}
				String[] arr = (String[]) map1.keySet().toArray(new String[map1.size()]);
				Map<String,String> map2 = new HashMap<String,String>();
				for (int j =0; j < arr.length; j++){

					try{//map1.put(arr1[j], arr2[j]);
						// finally fetch the desired data
						WebElement cellInSecondRowFourthColumn = Table.get(i).get(arr1[j]);
						actualValue = cellInSecondRowFourthColumn.getText();
						actualValue=actualValue.trim();
						map2.put(arr1[j], actualValue);
						tempValue = map1.get(arr[j]);						

						if (map2.get(arr[j]).equals(tempValue)){
							flag = true;					
							System.out.println("Header:-"+arr[j]+" ActualValue:-"+actualValue);
							this.selectedRow = i;
							return "true";
						}else {
							flag = false;
							break;
						}
					}catch(NullPointerException ex){
					}
				}
			}
			return "true";
		}catch (Exception e){
			//System.out.println(e.getMessage());
			Assert.assertTrue(false);
			return e.getMessage();
			
		}	

	}
	public String tableActions(String table, String actions, String object) throws InterruptedException{
		//String domain = Serenity.sessionVariableCalled("domain");
		String[] objDetailsArray = actions.split(Pattern.quote(","));
		String className = objDetailsArray[0];
		String uiName = object;
		int i = this.selectedRow;
		String cellPath = "";
		String cellInputPath = table+"/tbody/tr["+i+"]/td/label[@class='midl-it']";
		String cellCheckBoxPath = table+"/tbody/tr["+i+"]/td/label[@class='midl-it']";
		String cellInputPath1 = table+"//tbody//tr['"+i+"']//td[contains(@id,'"+uiName+"')]//span";
		String cellLinkPath = table+"//tbody//tr['"+i+"']//td/a[contains(text(),'"+uiName+"')]";
		String cellOpenUIPath = table+"//tbody//tr['"+i+"']//td[contains(@id,'"+uiName+"')]//span";
		String cellTextAreaPath = table+"//tbody//tr['"+i+"']//td[contains(@id,'"+uiName+"')]//textarea";
		
		if (className.equals("ClickCourseDots")){
			cellPath = table+"/tbody/tr["+i+"]/td/div/div/ul/li";
			find(By.xpath(cellPath)).click();
			report.Info("Clicked on course more actions dots");
		}
		
		if (className.equals("ClickCourseFieldDots")){
			cellPath = table+"/tbody/tr["+i+"]/td/ul/li";
			find(By.xpath(cellPath)).click();
			report.Info("Clicked on more actions dots");
		}

		if (className.equals("DoubleClick")){
			common.waitForElement(By.xpath(cellPath));
			find(By.xpath(cellPath)).click();
			Actions action = new Actions(getDriver());
			action.moveToElement(find(By.xpath(cellTextAreaPath))).doubleClick().perform();
		}
		if (className.equals("DrillDown")){						 
			find(By.xpath(cellLinkPath)).click();                
		}
		if (className.equals("ClickViewCertificate")){	
			cellLinkPath = table+"/tbody/tr['"+i+"']/td/li/a[contains(text(),'"+uiName+"')]";
			find(By.xpath(cellLinkPath)).click();                
		}

		if (className.equals("Text")){
			common.WaitForObjectPresence(cellPath); 
			//	withTimeoutOf(120,TimeUnit.SECONDS).waitFor(By.xpath(cellPath));
			//
			find(By.xpath(cellPath)).click();
			//
			find(By.xpath(cellInputPath)).sendKeys(object);
			find(By.xpath(cellPath)).sendKeys(Keys.TAB);
		}
		if (className.equals("TextArea")){
			//	withTimeoutOf(140,TimeUnit.SECONDS).waitFor(By.xpath(cellPath));
			common.waitForElement(By.xpath(cellPath)); 
			//
			find(By.xpath(cellPath)).click();
			//
			find(By.xpath(cellTextAreaPath)).sendKeys(object);

		}
		if (className.equals("Clear")){
			//withTimeoutOf(120,TimeUnit.SECONDS).waitFor(By.xpath(cellPath));
			common.waitForElement(By.xpath(cellPath)); 
			//
			find(By.xpath(cellPath)).click();
			//
			Thread.sleep(1000);
			find(By.xpath(cellInputPath)).click();
			getDriver().findElement(By.xpath(cellInputPath)).clear();

		}
		else if (className.equals("List1")){
			common.waitForElement(By.xpath(cellPath));  //dont delete
			find(By.xpath(cellPath)).click();
			find(By.xpath(cellInputPath1)).click();

			find(By.xpath("//li[text()='"+object+"']")).click();

		}  
		else if (className.equals("List2")){
			//withTimeoutOf(120,TimeUnit.SECONDS).waitFor(By.xpath(cellPath));
			common.waitForElement(By.xpath(cellPath)); 
			find(By.xpath(cellPath)).click();            
			find(By.xpath(cellInputPath)).sendKeys(Keys.CLEAR);
			find(By.xpath(cellInputPath)).sendKeys(object);
		}

		else if (className.equals("List3")){			
			common.waitForElement(By.xpath(cellPath)); 
			find(By.xpath(cellPath)).click();            
			find(By.xpath(cellInputPath)).typeAndEnter(object);
		}

		else if (className.equals("List")){
			common.waitForElement(By.xpath(cellPath));  //dont delete
			find(By.xpath(cellPath)).click();
			find(By.xpath(cellInputPath1)).click();

			find(By.xpath("//li[text()='"+object+"']")).click();
		}   

		/*		else if (className.equals("List")){
			//	withTimeoutOf(120,TimeUnit.SECONDS).waitFor(By.xpath(cellPath));
			common.waitForElement(By.xpath(cellPath)); 
			find(By.xpath(cellPath)).click();            
			find(By.xpath(cellInputPath)).typeAndEnter(value1);
		}*/
		else if (className.equals("CaptureTextValue")){
			withTimeoutOf(120,TimeUnit.SECONDS).waitFor(By.xpath(cellPath));
			String capturedValue = find(By.xpath(cellPath)).getTextValue();
			return capturedValue;
			//Serenity.setSessionVariable("CapturedValue").to(CapturedValue);

		}
		else if (className.equals("CaptureCompareTextValue")){
			try{

				String keyVal = Serenity.sessionVariableCalled(object).toString();
				object = object.replace(object, keyVal);

			}
			catch(NullPointerException e){}
			String flag ="N";
			try{
				withTimeoutOf(120,TimeUnit.SECONDS).waitFor(By.xpath(cellPath));
			}
			catch(Exception e){

			}
			String capturedValue = find(By.xpath(cellPath)).getTextValue();
			if (object.contains("*")){
				object = object.replace("*", "");

				Assert.assertTrue("Expected value " + object + "* doesnot match with actual " + capturedValue, capturedValue.contains(object));
				report.Info("Expected value " + object + " found");

			}
			else {
				Assert.assertTrue("Expected value " + object + "* doesnot match with actual " + capturedValue, capturedValue.equals(object));
				report.Info("Expected value " + object + " found");
			}


		}
		else if (className.equals("CheckBox")){
			//withTimeoutOf(120,TimeUnit.SECONDS).waitFor(By.xpath(cellPath));
			//common.waitForElement(By.xpath(cellPath)); 
			common.clickElement($(cellCheckBoxPath));
			report.Info("CheckBox Checked");

		}
		else if (className.equals("CheckBoxStatusChecked")){
			try{
				withTimeoutOf(120,TimeUnit.SECONDS).waitFor(By.xpath(cellPath));
			}
			catch(Exception e){

			}
			String ischecked = (find(By.xpath(cellPath)).getAttribute("title"));
			if (ischecked.equals("Checked")||ischecked.equals("Y")){
				return "true";            	  
			}
		}
		
		else if (className.equals("OpenPopUp")){
			//withTimeoutOf(120,TimeUnit.SECONDS).waitFor(By.xpath(cellPath));
			common.waitForElement(By.xpath(cellPath)); 
			find(By.xpath(cellPath)).click();
			getDriver().findElement(By.xpath(cellOpenUIPath)).click();
		} 
		else if (className.equals("OpenPopUpUsingTAB")){
			//withTimeoutOf(120,TimeUnit.SECONDS).waitFor(By.xpath(cellPath));
			common.waitForElement(By.xpath(cellPath)); 
			if (find(By.xpath(cellPath + "/preceding-sibling::td[1]//a")).isCurrentlyVisible()){
				find(By.xpath(cellPath + "/preceding-sibling::td[2]")).click();
				find(By.xpath(cellPath + "/preceding-sibling::td[2]//input")).sendKeys(Keys.TAB);
				find(By.xpath(cellPath + "/preceding-sibling::td[1]//input")).sendKeys(Keys.TAB);
				getDriver().findElement(By.xpath(cellOpenUIPath)).click();

			}
			else {
				find(By.xpath(cellPath + "/preceding-sibling::td[1]")).click();
				find(By.xpath(cellPath + "/preceding-sibling::td[1]//input")).sendKeys(Keys.TAB);
				getDriver().findElement(By.xpath(cellOpenUIPath)).click();
			}
		} 

		return null;
	}

	public void checkImage(String xpath) {
		WebElementFacade ImageFile = $(xpath);
		common.WaitForObjectVisibility(xpath);
		Boolean ImagePresent = (Boolean) ((JavascriptExecutor)getDriver()).executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", ImageFile);
		if (!ImagePresent)
		{
			report.Info("Image not displayed.");
			Assert.assertTrue(false);
		}
		else
		{
			report.Info("Image displayed.");
		}
	}

	@Step
	public void actionClick(WebElementFacade w) {
		Actions xAct = new Actions(getDriver());
		xAct.moveToElement(w);
		xAct.click();
		xAct.perform();
	}

	@Step
	public void locateColumn1(String table,String sLocateCol,String sLocateColValue,String index){

		Map<String,String> map1 = new HashMap<String,String>();
		String[] arr1 = sLocateCol.split(Pattern.quote("|"));
		String[] arr2 = sLocateColValue.split(Pattern.quote("|"));

		int arrCnt1 = arr1.length;

		for (int k =0; k < arrCnt1; k++){
			map1.put(arr1[k], arr2[k]);
		}

		Map<String,String> map2 = new HashMap<String,String>();
		String[] arr = (String[]) map1.keySet().toArray(new String[map1.size()]);
		// simplified: find table which contains the keyword
		WebElementFacade tableElement = $(".//table[@id='course_table_list']");

		// create empty table object and iterate through all rows of the found table element
		ArrayList<HashMap<String, WebElement>> Table = new ArrayList<HashMap<String, WebElement>>();
		List<WebElement> rowElements = tableElement.findElements(By.xpath(".//tr"));

		// get column names of table from table headers
		ArrayList<String> columnNames = new ArrayList<String>();
		List<WebElement> headerElements = rowElements.get(0).findElements(By.xpath(".//th"));
		for (WebElement headerElement: headerElements) {
			//map2.put(headerElement.getText());
		}

		// iterate through all rows and add their content to table arrayd
		for (WebElement rowElement: rowElements) {
			HashMap<String, WebElement> row = new HashMap<String, WebElement>();

			// add table cells to current row
			int columnIndex = 0;
			List<WebElement> cellElements = rowElement.findElements(By.xpath(".//td"));
			for (WebElement cellElement: cellElements) {
				row.put(columnNames.get(columnIndex), cellElement);
				columnIndex++;
			}

			Table.add(row);
		}

		// finally fetch the desired data
		WebElement cellInSecondRowFourthColumn = Table.get(1).get("Status");
		String ss = cellInSecondRowFourthColumn.getText();
		System.out.println(ss);
	}
	
	public void setCourseEntries() throws InterruptedException {		
		WebElementFacade entries= $(".//span[@class='select2-selection select2-selection--single']");
		common.scrollToPageEnd();
		Thread.sleep(500);
		common.actionClick(entries);
		Thread.sleep(500);
		common.actionClick($(".//ul/li[text()='100']"));
	}
}
