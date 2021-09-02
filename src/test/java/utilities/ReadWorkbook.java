package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.junit.Assert;

//import pages.DatabasePage;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.core.pages.PageObject;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Steps;

public class ReadWorkbook extends PageObject {

	/*
	 * @Steps private DatabasePage Database;
	 */
	@Steps(shared=true)
	static
	ReportMessege report;
	
	public static int rowNo = 1;
	public static String destPath = "";

	public static Map<String, Map<Integer, String>> readRow(String row, String filePath, String dataSheet) throws IOException{
		System.out.println(filePath);
		Map <String,Map<Integer, String>> tableMap = new HashMap<String, Map<Integer, String>>();
 
		try{

			String currentDir = System.getProperty("user.dir");
			filePath = currentDir.concat(filePath);
			System.out.println(filePath);
			FileInputStream inputStream = new FileInputStream(new File(filePath));

			Workbook workbook = new XSSFWorkbook(inputStream);


			XSSFSheet datatypeSheet = (XSSFSheet) workbook.getSheet(dataSheet);

			Iterator<Row> iterator = datatypeSheet.iterator();


			Row firstRow = iterator.next();
			Iterator<Cell> firstRowCell = firstRow.iterator();
			List<String> cellNames = new ArrayList<String>();
			firstRowCell.next();
			while (firstRowCell.hasNext()){
				//
				Cell cell = firstRowCell.next();
				cellNames.add(cell.getStringCellValue());
			}
			List<Integer> totalRows = new ArrayList<Integer>();
			String flag = "";
			while (iterator.hasNext()) {

				Row currentRow = iterator.next();

				String currRow = currentRow.getCell(0).getStringCellValue();

				if (row.equals(currRow)){
					totalRows.add(currentRow.getRowNum());
					flag = "found";
				} else if (flag == "found"){
					break;
				}

			}
			if (!flag.equals("found")){
				report.Info("Logical Name " + row + " not found in the sheet " + dataSheet);
				Assert.assertTrue(false);        	 
			}

			int i = 1;
			for (String x : cellNames){
				int k = 0; 
				Map<Integer,String> entireRow = new HashMap<Integer,String>();
				for (Integer y : totalRows){
					Row currRow = datatypeSheet.getRow(y);
					String cellContent = "";
					if (currRow.getCell(i) == null){
						cellContent = "";
					}else if (currRow.getCell(i).getCellType() == CellType.STRING){
						cellContent = currRow.getCell(i).getStringCellValue();
					}else if (currRow.getCell(i).getCellType() == CellType.NUMERIC){
						cellContent = String.valueOf(currRow.getCell(i).getNumericCellValue());
					}
					else if (currRow.getCell(i).getCellType() == CellType.BLANK){
						cellContent = "";
					}
					entireRow.put(k, cellContent);
					k = k + 1;
				}
				tableMap.put(x, entireRow);
				i =i +1;

			}

		}	catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e){
			e.printStackTrace();
		}
		return tableMap;

	}

	@Step
	public void testData(Map<String, Map<Integer, String>> tableMap) {

	}

	/*
	 * public void createOutputExcelFile() throws IOException { String currentDir =
	 * System.getProperty("user.dir");
	 * 
	 * System.out.println("myCurrentDir" +currentDir); SimpleDateFormat sdfDate =
	 * new SimpleDateFormat("yyyyMMdd"); Date now = new Date(); try{
	 * 
	 * File file =new File(currentDir+"\\OutputResults\\" +sdfDate.format(now));
	 * if(!file.exists()){ new File(currentDir+"\\OutputResults\\"
	 * +sdfDate.format(now)).mkdir(); } } catch(Exception e) {}
	 * 
	 * //String currentDir = System.getProperty("user.dir"); FileSystem system =
	 * FileSystems.getDefault(); String srcPath =
	 * currentDir.concat("\\OutputResults\\OutputSheet.xls"); Path srcPath1 =
	 * system.getPath(srcPath); //SimpleDateFormat sdfDate = new
	 * SimpleDateFormat("yyyyMMdd"); //Date now = new Date(); String Randomno =
	 * Integer.toString((11111 + (int)(Math.random()*222222))); destPath =
	 * currentDir.concat("\\OutputResults\\"+sdfDate.format(now)+"\\
	 * OutputSheet"+Randomno+".xls"); System.out.println(destPath); Path destPath1 =
	 * system.getPath(destPath); Files.copy(srcPath1, destPath1,
	 * StandardCopyOption.REPLACE_EXISTING);
	 * 
	 * 
	 * 
	 * }
	 * 
	 * public void writeOutputExcelFileTCPass() throws IOException{ FileInputStream
	 * inputStream = new FileInputStream(new File(destPath)); Workbook workbook =
	 * new HSSFWorkbook(inputStream); HSSFSheet datatypeSheet = (HSSFSheet)
	 * workbook.getSheet("Sheet1");
	 * 
	 * Row currRow = datatypeSheet.createRow(rowNo);
	 * 
	 * Cell cell = currRow.createCell(0);
	 * 
	 * cell.setCellType(CellType.STRING);
	 * 
	 * cell.setCellValue(Integer.toString(rowNo));
	 * 
	 * cell = currRow.createCell(1);
	 * 
	 * cell.setCellType(CellType.STRING);
	 * 
	 * cell.setCellValue(Serenity.sessionVariableCalled("Env").toString());
	 * 
	 * cell = currRow.createCell(3);
	 * 
	 * cell.setCellType(CellType.STRING);
	 * 
	 * cell.setCellValue(Serenity.sessionVariableCalled("Meta").toString());
	 * 
	 * 
	 * try{ cell = currRow.createCell(5);
	 * 
	 * cell.setCellType(CellType.STRING);
	 * cell.setCellValue(Serenity.sessionVariableCalled("AccountNo").toString());
	 * 
	 * 
	 * }catch (Exception e){} try{ cell = currRow.createCell(6);
	 * 
	 * cell.setCellType(CellType.STRING);
	 * cell.setCellValue(Serenity.sessionVariableCalled("OrderNo").toString());
	 * 
	 * 
	 * }catch (Exception e){} try{ cell = currRow.createCell(7);
	 * 
	 * cell.setCellType(CellType.STRING);
	 * cell.setCellValue(Serenity.sessionVariableCalled("MSISDN").toString());
	 * 
	 * }catch (Exception e){}
	 * 
	 * cell = currRow.createCell(9);
	 * 
	 * cell.setCellType(CellType.STRING);
	 * 
	 * cell.setCellValue("Pass");
	 * 
	 * try{ cell = currRow.createCell(12);
	 * 
	 * cell.setCellType(CellType.STRING);
	 * cell.setCellValue(Serenity.sessionVariableCalled("PricingValidation").
	 * toString());
	 * 
	 * 
	 * }catch (Exception e){} try{ cell = currRow.createCell(13);
	 * 
	 * cell.setCellType(CellType.STRING);
	 * cell.setCellValue(Serenity.sessionVariableCalled("PartNo").toString());
	 * 
	 * 
	 * }catch (Exception e){}
	 * 
	 * try{ cell = currRow.createCell(14);
	 * 
	 * cell.setCellType(CellType.STRING);
	 * cell.setCellValue(Serenity.sessionVariableCalled("OutputFolder").toString());
	 * 
	 * 
	 * }catch (Exception e){}
	 * 
	 * FileOutputStream fos = new FileOutputStream(destPath);
	 * 
	 * workbook.write(fos);
	 * 
	 * fos.close(); rowNo = rowNo + 1;
	 * 
	 * }
	 * 
	 * public void writeOutputExcelFileTCFail() throws IOException{ FileInputStream
	 * inputStream = new FileInputStream(new File(destPath)); Workbook workbook =
	 * new HSSFWorkbook(inputStream); HSSFSheet datatypeSheet = (HSSFSheet)
	 * workbook.getSheet("Sheet1");
	 * 
	 * Row currRow = datatypeSheet.createRow(rowNo);
	 * 
	 * Cell cell = currRow.createCell(0);
	 * 
	 * cell.setCellType(CellType.STRING);
	 * 
	 * cell.setCellValue(Integer.toString(rowNo));
	 * 
	 * cell = currRow.createCell(1);
	 * 
	 * cell.setCellType(CellType.STRING);
	 * 
	 * cell.setCellValue(Serenity.sessionVariableCalled("Env").toString());
	 * 
	 * cell = currRow.createCell(3);
	 * 
	 * cell.setCellType(CellType.STRING);
	 * 
	 * cell.setCellValue(Serenity.sessionVariableCalled("Meta").toString());
	 * 
	 * 
	 * try{ cell = currRow.createCell(5);
	 * 
	 * cell.setCellType(CellType.STRING);
	 * cell.setCellValue(Serenity.sessionVariableCalled("AccountNo").toString());
	 * 
	 * 
	 * }catch (Exception e){} try{ cell = currRow.createCell(6);
	 * 
	 * cell.setCellType(CellType.STRING);
	 * cell.setCellValue(Serenity.sessionVariableCalled("OrderNo").toString());
	 * 
	 * 
	 * }catch (Exception e){} try{ cell = currRow.createCell(7);
	 * 
	 * cell.setCellType(CellType.STRING);
	 * cell.setCellValue(Serenity.sessionVariableCalled("MSISDN").toString());
	 * 
	 * }catch (Exception e){}
	 * 
	 * cell = currRow.createCell(9);
	 * 
	 * cell.setCellType(CellType.STRING);
	 * 
	 * cell.setCellValue("Fail");
	 * 
	 * try{ cell = currRow.createCell(12);
	 * 
	 * cell.setCellType(CellType.STRING);
	 * cell.setCellValue(Serenity.sessionVariableCalled("PricingValidation").
	 * toString());
	 * 
	 * 
	 * }catch (Exception e){}
	 * 
	 * try{ cell = currRow.createCell(13);
	 * 
	 * cell.setCellType(CellType.STRING);
	 * cell.setCellValue(Serenity.sessionVariableCalled("PartNo").toString());
	 * 
	 * 
	 * }catch (Exception e){}
	 * 
	 * try{ cell = currRow.createCell(14);
	 * 
	 * cell.setCellType(CellType.STRING);
	 * cell.setCellValue(Serenity.sessionVariableCalled("OutputFolder").toString());
	 * 
	 * 
	 * }catch (Exception e){}
	 * 
	 * FileOutputStream fos = new FileOutputStream(destPath);
	 * 
	 * workbook.write(fos);
	 * 
	 * fos.close(); rowNo = rowNo + 1;
	 * 
	 * }
	 * 
	 * public Map<String, Map<Integer, String>> readMSISDNnICCICD(String filePath,
	 * String dataSheet) {
	 * 
	 * // DataType is "MSISDN" or "ICCID" depending on whether we want to fetch
	 * MSISDN // or ICCID from excel
	 * 
	 * try { String currentDir = System.getProperty("user.dir"); filePath =
	 * currentDir.concat(filePath); FileInputStream inputStream = new
	 * FileInputStream(new File(filePath));
	 * 
	 * Workbook workbook = new HSSFWorkbook(inputStream);
	 * 
	 * HSSFSheet datatypeSheet = (HSSFSheet) workbook.getSheet(dataSheet);
	 * Iterator<Row> iterator = datatypeSheet.iterator(); // String row =
	 * "PostpaidConsumerdd";
	 * 
	 * Row firstRow = iterator.next();
	 * 
	 * // List<Integer> totalRows = new ArrayList<Integer>(); int rowNo = -1; String
	 * flag = ""; while (iterator.hasNext()) {
	 * 
	 * Row currentRow = iterator.next(); // CellStyle currRow =
	 * currentRow.getCell(0). String currRow =
	 * currentRow.getCell(3).getStringCellValue();
	 * 
	 * if (currRow.equalsIgnoreCase("Y")) { rowNo = currentRow.getRowNum(); flag =
	 * "found";
	 * 
	 * // ***** currentRow = datatypeSheet.getRow(rowNo); String sMSISDN =
	 * currentRow.getCell(1).getStringCellValue(); String sICCID =
	 * currentRow.getCell(2).getStringCellValue();
	 * 
	 * Serenity.setSessionVariable("MSISDN").to(sMSISDN);
	 * Serenity.setSessionVariable("ICCID").to(sICCID);
	 * 
	 * 
	 * 
	 * Database.ExecuteBRMDBQuery("checkMSISDNAvailability"); String
	 * dbMSISDNCheckResult = Serenity.sessionVariableCalled("COUNT0").toString();
	 * 
	 * Database.ExecuteBRMDBQuery("checkICCIDAvailability"); String
	 * dbICCIDCheckResult = Serenity.sessionVariableCalled("COUNT0").toString();
	 * 
	 * String Combinedres = dbMSISDNCheckResult + dbICCIDCheckResult;
	 * 
	 * if (!Combinedres.equalsIgnoreCase("00")) {
	 * if(!dbICCIDCheckResult.equalsIgnoreCase("0")) { Cell cell =
	 * currentRow.getCell(3); cell.setCellType(CellType.STRING);
	 * cell.setCellValue("N"); currRow = "N"; FileOutputStream fos = new
	 * FileOutputStream(filePath); workbook.write(fos); fos.close(); }
	 * 
	 * } else{ report.Info("MSISDN Retrieved from Excel : " + sMSISDN );
	 * report.Info("ICCID Retrieved from Excel : " + sICCID ); break; } }
	 * 
	 * } if (!flag.equals("found")) { report.Info("No available MSISDN/ICCID");
	 * Assert.assertTrue(false); }
	 * 
	 * 
	 * } catch (Exception e) { System.out.println(e.getMessage()); } return null;
	 * 
	 * }
	 * 
	 * public Map<String, Map<Integer, String>> MakeAddressReserved (String
	 * filePath, String dataSheet){ //Map <String, String> tableMap = new
	 * HashMap<String, String>();
	 * 
	 * try { String currentDir = System.getProperty("user.dir"); filePath =
	 * currentDir.concat(filePath); FileInputStream inputStream = new
	 * FileInputStream(new File(filePath));
	 * 
	 * Workbook workbook = new HSSFWorkbook(inputStream);
	 * 
	 * //FileInputStream excelFile = new FileInputStream(new File(FILE_NAME));
	 * //HSSFWorkbook workbook = new HSSFWorkbook()p HSSFSheet datatypeSheet =
	 * (HSSFSheet) workbook.getSheet(dataSheet); Iterator<Row> iterator =
	 * datatypeSheet.iterator(); //String row = "PostpaidConsumerdd";
	 * 
	 * 
	 * Row firstRow = iterator.next();
	 * 
	 * //List<Integer> totalRows = new ArrayList<Integer>(); int rowNo = -1; String
	 * flag = ""; while (iterator.hasNext()) {
	 * 
	 * Row currentRow = iterator.next(); //CellStyle currRow =
	 * currentRow.getCell(0). String currRow =
	 * currentRow.getCell(10).getStringCellValue();
	 * 
	 * 
	 * if (currRow.equalsIgnoreCase("Available")){ rowNo = currentRow.getRowNum();
	 * flag = "found"; break; }
	 * 
	 * 
	 * } if (!flag.equals("found")){ report.Info("No address Available");
	 * Assert.assertTrue(false); } Row currRow = datatypeSheet.getRow(rowNo); String
	 * sActualAddress = currRow.getCell(2).getStringCellValue(); //String sICCID =
	 * currRow.getCell(2).getStringCellValue();
	 * Serenity.setSessionVariable("ActualAddress").to(sActualAddress); Cell cell =
	 * currRow.getCell(10); cell.setCellType(CellType.STRING);
	 * cell.setCellValue("Available"); FileOutputStream fos = new
	 * FileOutputStream(filePath); workbook.write(fos); fos.close(); }
	 * catch(Exception e) { System.out.println(e.getMessage()); } return null;
	 * 
	 * }
	 * 
	 * public Map<String, Map<Integer, String>> writeFLValidation (String filePath,
	 * String dataSheet){
	 * 
	 * //DataType is "MSISDN" or "ICCID" depending on whether we want to fetch
	 * MSISDN or ICCID from excel //Map <String, String> tableMap = new
	 * HashMap<String, String>();
	 * 
	 * try { String currentDir = System.getProperty("user.dir"); filePath =
	 * currentDir.concat(filePath); FileInputStream inputStream = new
	 * FileInputStream(new File(filePath));
	 * 
	 * Workbook workbook = new HSSFWorkbook(inputStream);
	 * 
	 * //FileInputStream excelFile = new FileInputStream(new File(FILE_NAME));
	 * //HSSFWorkbook workbook = new HSSFWorkbook()p HSSFSheet datatypeSheet =
	 * (HSSFSheet) workbook.getSheet(dataSheet); Iterator<Row> iterator =
	 * datatypeSheet.iterator(); //String row = "PostpaidConsumerdd";
	 * 
	 * Row firstRow = iterator.next();
	 * 
	 * //List<Integer> totalRows = new ArrayList<Integer>(); int rowNo = -1; String
	 * flag = ""; while (iterator.hasNext()) {
	 * 
	 * Row currentRow = iterator.next(); //CellStyle currRow =
	 * currentRow.getCell(0). String currRow =
	 * currentRow.getCell(3).getStringCellValue();
	 * 
	 * if (currRow.equals(Serenity.sessionVariableCalled("FLAccount").toString())){
	 * rowNo = currentRow.getRowNum(); flag = "found"; break; }
	 * 
	 * } if (!flag.equals("found")){
	 * report.Info("Account number not found in sheet"); Assert.assertTrue(false); }
	 * Row currRow = datatypeSheet.getRow(rowNo);
	 * 
	 * //Cell cell = currRow.get
	 * 
	 * 
	 * 
	 * Cell cell = currRow.createCell(6); cell.setCellType(CellType.STRING);
	 * cell.setCellValue(Serenity.sessionVariableCalled("CapturedValueFL").toString(
	 * ));
	 * 
	 * Cell cell1 = currRow.getCell(5);
	 * 
	 * cell1.setCellType(CellType.STRING); cell1.setCellValue("Completed");
	 * FileOutputStream fos = new FileOutputStream(filePath); workbook.write(fos);
	 * fos.close(); } catch(Exception e) { System.out.println(e.getMessage()); }
	 * return null;
	 * 
	 * }
	 * 
	 * public Map<String, Map<Integer, String>> writeFLValidationFail (String
	 * filePath, String dataSheet){
	 * 
	 * //DataType is "MSISDN" or "ICCID" depending on whether we want to fetch
	 * MSISDN or ICCID from excel //Map <String, String> tableMap = new
	 * HashMap<String, String>();
	 * 
	 * try { String currentDir = System.getProperty("user.dir"); filePath =
	 * currentDir.concat(filePath); FileInputStream inputStream = new
	 * FileInputStream(new File(filePath));
	 * 
	 * Workbook workbook = new HSSFWorkbook(inputStream);
	 * 
	 * //FileInputStream excelFile = new FileInputStream(new File(FILE_NAME));
	 * //HSSFWorkbook workbook = new HSSFWorkbook()p HSSFSheet datatypeSheet =
	 * (HSSFSheet) workbook.getSheet(dataSheet); Iterator<Row> iterator =
	 * datatypeSheet.iterator(); //String row = "PostpaidConsumerdd";
	 * 
	 * Row firstRow = iterator.next();
	 * 
	 * //List<Integer> totalRows = new ArrayList<Integer>(); int rowNo = -1; String
	 * flag = ""; while (iterator.hasNext()) {
	 * 
	 * Row currentRow = iterator.next(); //CellStyle currRow =
	 * currentRow.getCell(0). String currRow =
	 * currentRow.getCell(3).getStringCellValue();
	 * 
	 * if (currRow.equals(Serenity.sessionVariableCalled("FLAccount").toString())){
	 * rowNo = currentRow.getRowNum(); flag = "found"; break; }
	 * 
	 * } if (!flag.equals("found")){
	 * report.Info("Account number not found in sheet"); Assert.assertTrue(false); }
	 * Row currRow = datatypeSheet.getRow(rowNo);
	 * 
	 * //Cell cell = currRow.get
	 * 
	 * 
	 * 
	 * Cell cell = currRow.createCell(6); cell.setCellType(CellType.STRING);
	 * cell.setCellValue(Serenity.sessionVariableCalled("CapturedValueFL").toString(
	 * )); FileOutputStream fos = new FileOutputStream(filePath);
	 * workbook.write(fos); fos.close(); } catch(Exception e) {
	 * System.out.println(e.getMessage()); } return null;
	 * 
	 * }
	 * 
	 * public Map<String, Map<Integer, String>> readFLValidationInitial (String
	 * filePath, String dataSheet){
	 * 
	 * //DataType is "MSISDN" or "ICCID" depending on whether we want to fetch
	 * MSISDN or ICCID from excel //Map <String, String> tableMap = new
	 * HashMap<String, String>();
	 * 
	 * try { String currentDir = System.getProperty("user.dir"); filePath =
	 * currentDir.concat(filePath); FileInputStream inputStream = new
	 * FileInputStream(new File(filePath));
	 * 
	 * Workbook workbook = new HSSFWorkbook(inputStream);
	 * 
	 * //FileInputStream excelFile = new FileInputStream(new File(FILE_NAME));
	 * //HSSFWorkbook workbook = new HSSFWorkbook()p HSSFSheet datatypeSheet =
	 * (HSSFSheet) workbook.getSheet(dataSheet); int rwCnt =
	 * datatypeSheet.getLastRowNum();
	 * 
	 * Serenity.setSessionVariable("RowCountFL").to(rwCnt); Iterator<Row> iterator =
	 * datatypeSheet.iterator();
	 * 
	 * //List<Integer> totalRows = new ArrayList<Integer>();
	 * 
	 * String flagLoop1 = ""; Row firstRow = iterator.next(); while
	 * (iterator.hasNext()) {
	 * 
	 * Row currentRow = iterator.next(); //CellStyle currRow =
	 * currentRow.getCell(0). String currRow =
	 * currentRow.getCell(5).getStringCellValue();
	 * 
	 * if ((!currRow.equalsIgnoreCase("PASS"))&&(!currRow.equalsIgnoreCase("FAIL"))
	 * ){ flagLoop1 = "TestDone";
	 * Serenity.setSessionVariable("FirstLoop").to(flagLoop1); break; } else {
	 * Serenity.setSessionVariable("FirstLoop").to("TestNotDone"); }
	 * 
	 * }
	 * 
	 * 
	 * 
	 * } catch(Exception e) { System.out.println(e.getMessage()); } return null;
	 * 
	 * }
	 * 
	 * public Map<String, Map<Integer, String>> readFLValidation (String filePath,
	 * String dataSheet){
	 * 
	 * //DataType is "MSISDN" or "ICCID" depending on whether we want to fetch
	 * MSISDN or ICCID from excel //Map <String, String> tableMap = new
	 * HashMap<String, String>();
	 * 
	 * try { String currentDir = System.getProperty("user.dir"); filePath =
	 * currentDir.concat(filePath); FileInputStream inputStream = new
	 * FileInputStream(new File(filePath));
	 * 
	 * Workbook workbook = new HSSFWorkbook(inputStream);
	 * 
	 * //FileInputStream excelFile = new FileInputStream(new File(FILE_NAME));
	 * //HSSFWorkbook workbook = new HSSFWorkbook()p HSSFSheet datatypeSheet =
	 * (HSSFSheet) workbook.getSheet(dataSheet); int rwCnt =
	 * datatypeSheet.getLastRowNum();
	 * 
	 * Serenity.setSessionVariable("RowCountFL").to(rwCnt); Iterator<Row> iterator =
	 * datatypeSheet.iterator(); //String row = "PostpaidConsumerdd";
	 * 
	 * Row firstRow = iterator.next();
	 * 
	 * //List<Integer> totalRows = new ArrayList<Integer>(); int rowNo = -1; String
	 * flagLoop2 = ""; while (iterator.hasNext()) {
	 * 
	 * Row currentRow = iterator.next(); //CellStyle currRow =
	 * currentRow.getCell(0). String currRow =
	 * currentRow.getCell(5).getStringCellValue();
	 * 
	 * if (currRow.equalsIgnoreCase("PASS")){ rowNo = currentRow.getRowNum();
	 * flagLoop2 = "found"; Serenity.setSessionVariable("SecondLoop").to(flagLoop2);
	 * break; }
	 * 
	 * }
	 * 
	 * if (!flagLoop2.equals("found")){ flagLoop2 = "NotFound";
	 * Serenity.setSessionVariable("SecondLoop").to(flagLoop2);
	 * report.Info("No Orders are in pass state"); //Assert.assertTrue(false); } Row
	 * currRow = datatypeSheet.getRow(rowNo); String sOrderNoFL =
	 * currRow.getCell(4).getStringCellValue(); String sExpectedVal =
	 * currRow.getCell(2).getStringCellValue(); String sAccNoFL =
	 * currRow.getCell(3).getStringCellValue(); //String sICCID =
	 * currRow.getCell(2).getStringCellValue();
	 * Serenity.setSessionVariable("FLOrder").to(sOrderNoFL);
	 * Serenity.setSessionVariable("ExpectedVal").to(sExpectedVal);
	 * Serenity.setSessionVariable("FLAccount").to(sAccNoFL); //Cell cell =
	 * currRow.get Cell cell = currRow.getCell(3);
	 * cell.setCellType(CellType.STRING); cell.setCellValue("N"); //FileOutputStream
	 * fos = new FileOutputStream(filePath); //workbook.write(fos); //fos.close(); }
	 * catch(Exception e) { System.out.println(e.getMessage()); } return null;
	 * 
	 * }
	 */
}
