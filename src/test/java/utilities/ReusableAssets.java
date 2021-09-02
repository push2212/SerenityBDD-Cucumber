/*
 * package utilities;
 * 
 * import java.awt.Robot; import java.awt.Toolkit; import
 * java.awt.datatransfer.Clipboard; import java.awt.datatransfer.DataFlavor;
 * import java.awt.datatransfer.StringSelection; import
 * java.awt.datatransfer.UnsupportedFlavorException; import
 * java.awt.event.KeyEvent; import java.io.IOException; import
 * java.text.SimpleDateFormat; import java.util.ArrayList; import
 * java.util.Calendar; import java.util.Date; import java.util.List; import
 * java.util.Properties; import java.util.Random; import java.util.TimeZone;
 * 
 * import javax.activation.DataHandler; import javax.activation.DataSource;
 * import javax.activation.FileDataSource; import javax.mail.BodyPart; import
 * javax.mail.Flags; import javax.mail.Folder; import javax.mail.Message; import
 * javax.mail.MessagingException; import javax.mail.Multipart; import
 * javax.mail.PasswordAuthentication; import javax.mail.Session; import
 * javax.mail.Store; import javax.mail.Transport; import
 * javax.mail.internet.InternetAddress; import javax.mail.internet.MimeBodyPart;
 * import javax.mail.internet.MimeMessage; import
 * javax.mail.internet.MimeMultipart;
 * 
 * import org.openqa.selenium.By; import org.openqa.selenium.JavascriptExecutor;
 * import org.openqa.selenium.Keys; import org.openqa.selenium.WebDriver; import
 * org.openqa.selenium.WebElement; import
 * org.openqa.selenium.support.ui.ExpectedConditions; import
 * org.openqa.selenium.support.ui.Select; import
 * org.openqa.selenium.support.ui.WebDriverWait;
 * 
 * 
 * import com.google.common.annotations.VisibleForTesting;
 * 
 * public class ReusableAssets { public static GetProperty prop = new
 * GetProperty(); public static String dir = System.getProperty("user.dir");
 * public String expectedPostTitle = ""; public static int totalpass; public
 * static int totalfail; public static int totalskip; public static String
 * dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date()); public
 * static String reportDate = new SimpleDateFormat("yyyyMMdd").format(new
 * Date()); public WebDriverWait wait; public String campaignName = "";
 * 
 * public WebElement findElement(WebDriver driver, String locatorType, String
 * locatorName) { WebElement element = null; try { if (locatorType == "xpath") {
 * element = driver.findElement(By.xpath(prop.getElement(locatorName))); } else
 * if (locatorType == "name") { element =
 * driver.findElement(By.name(prop.getElement(locatorName))); } else if
 * (locatorType == "id") { element =
 * driver.findElement(By.id(prop.getElement(locatorName))); } else if
 * (locatorType == "class") { element =
 * driver.findElement(By.className(prop.getElement(locatorName))); } else if
 * (locatorType == "cssSelector") { element =
 * driver.findElement(By.cssSelector(prop.getElement(locatorName))); } else if
 * (locatorType == "linkText") { element =
 * driver.findElement(By.linkText(prop.getElement(locatorName))); } else if
 * (locatorType == "partialLinkText") { element =
 * driver.findElement(By.partialLinkText(prop.getElement(locatorName))); } else
 * if (locatorType == "tagName") { element =
 * driver.findElement(By.tagName(prop.getElement(locatorName))); } else {
 * System.out.println("Please provide valid locator Type"); } wait = new
 * WebDriverWait(driver, 8);
 * wait.until(ExpectedConditions.elementToBeClickable(element)); } catch
 * (Exception e) { System.out.println(e.getMessage()); } return element; }
 * 
 * public WebElement findElement(WebDriver driver, WebDriverWait wait, String
 * locatorType, String locatorName) { WebElement element = null; try { if
 * (locatorType == "xpath") { element =
 * driver.findElement(By.xpath(prop.getElement(locatorName))); } else if
 * (locatorType == "name") { element =
 * driver.findElement(By.name(prop.getElement(locatorName))); } else if
 * (locatorType == "id") { element =
 * driver.findElement(By.id(prop.getElement(locatorName))); } else if
 * (locatorType == "class") { element =
 * driver.findElement(By.className(prop.getElement(locatorName))); } else if
 * (locatorType == "cssSelector") { element =
 * driver.findElement(By.cssSelector(prop.getElement(locatorName))); } else if
 * (locatorType == "linkText") { element =
 * driver.findElement(By.linkText(prop.getElement(locatorName))); } else if
 * (locatorType == "partialLinkText") { element =
 * driver.findElement(By.partialLinkText(prop.getElement(locatorName))); } else
 * if (locatorType == "tagName") { element =
 * driver.findElement(By.tagName(prop.getElement(locatorName))); } else {
 * System.out.println("Please provide valid locator Type"); }
 * wait.until(ExpectedConditions.visibilityOf(element)); } catch (Exception e) {
 * System.out.println(e.getMessage()); } return element; }
 * 
 * public List<WebElement> findElements(WebDriver driver, String locatorType,
 * String locatorName) { List<WebElement> element = null; try { if (locatorType
 * == "xpath") { element =
 * driver.findElements(By.xpath(prop.getElement(locatorName))); } else if
 * (locatorType == "name") { element =
 * driver.findElements(By.name(prop.getElement(locatorName))); } else if
 * (locatorType == "id") { element =
 * driver.findElements(By.id(prop.getElement(locatorName))); } else if
 * (locatorType == "class") { element =
 * driver.findElements(By.className(prop.getElement(locatorName))); } else if
 * (locatorType == "cssSelector") { element =
 * driver.findElements(By.cssSelector(prop.getElement(locatorName))); } else if
 * (locatorType == "linkText") { element =
 * driver.findElements(By.linkText(prop.getElement(locatorName))); } else if
 * (locatorType == "partialLinkText") { element =
 * driver.findElements(By.partialLinkText(prop.getElement(locatorName))); } else
 * if (locatorType == "tagName") { element =
 * driver.findElements(By.tagName(prop.getElement(locatorName))); } else {
 * System.out.println("Please provide valid locator Type"); } } catch (Exception
 * e) { System.out.println(e.getMessage()); } return element; }
 * 
 * public void mouseHover(WebDriver driver, WebElement element) { String
 * strJavaScript =
 * "var element = arguments[0]; var mouseEventObj = document.createEvent('MouseEvents'); mouseEventObj.initEvent( 'mouseover', true, true ); element.dispatchEvent(mouseEventObj);"
 * ; ((JavascriptExecutor) driver).executeScript(strJavaScript, element); }
 * 
 * public void scrollToView(WebDriver driver, WebElement element) {
 * ((JavascriptExecutor)
 * driver).executeScript("arguments[0].scrollIntoView(true);", element); }
 * 
 * public void scrollToPageEnd(WebDriver driver) throws InterruptedException {
 * ((JavascriptExecutor)
 * driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
 * Thread.sleep(2000); }
 * 
 * public void upload(WebDriver driver, String filePathName) { try { // Store
 * the location of the file in clipboard // Clipboard StringSelection strSel =
 * new StringSelection(dir + prop.getProp(filePathName)); Clipboard clip =
 * Toolkit.getDefaultToolkit().getSystemClipboard(); Thread.sleep(500);
 * clip.setContents(strSel, null); // Create an object for robot class Robot
 * robot = new Robot(); // Control key in the keyboard // Ctrl+V
 * robot.keyPress(KeyEvent.VK_CONTROL); robot.keyPress(KeyEvent.VK_V);
 * Thread.sleep(500); robot.keyRelease(KeyEvent.VK_V);
 * robot.keyRelease(KeyEvent.VK_CONTROL); Thread.sleep(1000);
 * robot.keyPress(KeyEvent.VK_ENTER); robot.keyRelease(KeyEvent.VK_ENTER);
 * //ExtentTestManager.getTest().log(Status.PASS,
 * //"Uploaded Given file located at path " + filePathName + " Successfully"); }
 * catch (Exception e) { //ExtentTestManager.getTest().log(Status.FAIL,
 * "Unable to complete the upload action " + e.getMessage());
 * System.out.println("Error in try block: " + e.toString()); } }
 * 
 * public void uploadImageViaAutoIT(WebDriver driver, String filePath) { try {
 * Thread.sleep(1000); // wait for page load Runtime.getRuntime().exec(
 * "rundll32 url.dll,FileProtocolHandler " + dir +
 * "\\src\\main\\java\\utilities\\UploadImage.exe"); // Give // path // where //
 * the // exe // is // saved. System.out.println("auto IT command executed");
 * 
 * ExtentTestManager.getTest().log(Status.PASS,
 * "Uploaded Given file located at path " + filePath + " Successfully");
 * 
 * } catch (Exception e) { //ExtentTestManager.getTest().log(Status.FAIL,
 * "Unable to complete the upload action " + e.getMessage()); } }
 * 
 * public void clickElement(WebDriver driver, WebElement element) {
 * JavascriptExecutor js = (JavascriptExecutor) driver;
 * js.executeScript("arguments[0].click();", element); }
 * 
 * public void clickCheckbox(WebDriver driver, WebElement element) {
 * JavascriptExecutor js = (JavascriptExecutor) driver;
 * js.executeScript("arguments[0].checked=true;", element); }
 * 
 * public void clickCheckbox(WebDriver driver, String elementID) {
 * JavascriptExecutor js = (JavascriptExecutor) driver;
 * js.executeScript("document.getElementById('" + elementID +
 * "').checked=true;"); }
 * 
 * public void sendTextToElement(WebDriver driver, WebElement element, String
 * textToSend) { JavascriptExecutor js = (JavascriptExecutor) driver;
 * js.executeScript("arguments[0].value='" + textToSend + "';", element); }
 * 
 * public void selectFromDropdown(WebElement selectDropdown, String selectBy,
 * String valueToSelect) { Select mySel=new Select(selectDropdown);
 * if(selectBy.equalsIgnoreCase("index")) {
 * mySel.selectByIndex(Integer.parseInt(valueToSelect)); }else
 * if(selectBy.equalsIgnoreCase("value")) { mySel.selectByValue(valueToSelect);
 * }else if(selectBy.equalsIgnoreCase("visibleText")) {
 * mySel.selectByVisibleText(valueToSelect); }else {
 * //ExtentTestManager.getTest().log(Status.PASS,
 * "Please enter valid Select By option"); } }
 * 
 * public void globalSearch(WebDriver driver, String TextToSearch) { try {
 * explicitWait(driver, "id", "globalSearchBox");
 * FunctionLibrary.myAct.sendKeys(Keys.chord(Keys.SHIFT +
 * "S")).build().perform(); Thread.sleep(2500); findElement(driver, "id",
 * "globalSearchBox").sendKeys(TextToSearch, Keys.ENTER);
 * //ExtentTestManager.getTest().log(Status.PASS,
 * "Entered the text String in Global search Box"); Thread.sleep(2000); } catch
 * (Exception e) { //ExtentTestManager.getTest().log(Status.FAIL,
 * "Error in try block" + e.toString()); }
 * 
 * }
 * 
 * public void keyDown(WebDriver driver, WebElement element, String text) throws
 * InterruptedException { // String downEnter = Keys.chord(Keys.ARROW_DOWN,
 * Keys.ENTER); element.sendKeys(text); Thread.sleep(3000);
 * element.sendKeys(Keys.ENTER); } public void keyDownEnter(WebDriver driver,
 * WebElement element, String text) throws InterruptedException { String
 * downEnter = Keys.chord(Keys.ARROW_DOWN, Keys.ENTER); element.sendKeys(text);
 * Thread.sleep(3000); element.sendKeys(downEnter); }
 * 
 * public String getMethodName() { String nameofCurrMethod = new
 * Exception().getStackTrace()[0].getMethodName(); return nameofCurrMethod; }
 * 
 * public void feedSelector(WebDriver driver, String feedType) throws
 * IOException, InterruptedException { findElement(driver, "id",
 * "feedSelector").click(); if (feedType.equalsIgnoreCase("Unread")) {
 * WebElement unread = findElement(driver, "id", "unreadFeeds");
 * Thread.sleep(1000); mouseHover(driver, unread); unread.click(); } else if
 * (feedType.equalsIgnoreCase("MyFeeds")) { WebElement myFeeds =
 * findElement(driver, "id", "myFeeds"); Thread.sleep(1000); mouseHover(driver,
 * myFeeds); myFeeds.click(); } else if (feedType.equalsIgnoreCase("AllFeeds"))
 * { WebElement allFeeds = findElement(driver, "id", "allFeeds");
 * Thread.sleep(1000); mouseHover(driver, allFeeds); allFeeds.click(); } }
 * 
 * public void userOptions(WebDriver driver, String desiredDropdownAction) { //
 * Need to use only text link to find element here // desiredDropdownAction=
 * goToUserPortal, gamificationSection, viewMyProfile, // manageDomain, signOut,
 * signOutAllOther try { findElement(driver, "cssSelector",
 * "userSettings").click(); //ExtentTestManager.getTest().log(Status.PASS,
 * "Clicked on User Settings dropdown"); Thread.sleep(1000); mouseHover(driver,
 * findElement(driver, "linkText", desiredDropdownAction));
 * //ExtentTestManager.getTest().log(Status.INFO, "Moved the cursor on to " +
 * desiredDropdownAction); findElement(driver, "linkText",
 * desiredDropdownAction).click();
 * //ExtentTestManager.getTest().log(Status.PASS, "Clicked on " +
 * desiredDropdownAction); } catch (Exception e) {
 * 
 * //ExtentTestManager.getTest().log(Status.FAIL,
 * //"Unable to perform desired action from UserSettings drop down"); } }
 * 
 * public int randomNumber() { List<Integer> numbers = new ArrayList<Integer>();
 * Random random = new Random(); int next = random.nextInt(100000); int value =
 * 0; if (!numbers.contains(next)) { numbers.add(next); value = next; } else {
 * randomNumber(); } return value; }
 * 
 * public String dateProvider() { Date date = new Date(); // your date // Choose
 * time zone in which you want to interpret your Date Calendar cal =
 * Calendar.getInstance(TimeZone.getTimeZone("GMT+5:30")); cal.setTime(date);
 * cal.add(Calendar.DAY_OF_MONTH, +1); // this makes calendar to point to
 * tomorrow's date // int year = cal.get(Calendar.YEAR); //
 * System.out.println(year); // int month = cal.get(Calendar.MONTH); int day =
 * cal.get(Calendar.DAY_OF_MONTH); return Integer.toString(day); }
 * 
 * public void explicitWait(WebDriver driver, String locatorType, String
 * locatorName) throws IOException { WebDriverWait wait = new
 * WebDriverWait(driver, 10);
 * wait.until(ExpectedConditions.elementToBeClickable(findElement(driver,
 * locatorType, locatorName))); //ExtentTestManager.getTest().log(Status.INFO,
 * "wait was activated until desired element is visible on the page"); }
 * 
 * public void explicitWait(WebDriver driver, String conditionType, String
 * locatorType, String locatorName, int WaitTime) throws IOException {
 * WebDriverWait wait = new WebDriverWait(driver, WaitTime);
 * 
 * if (conditionType.equalsIgnoreCase("visibility")) {
 * wait.until(ExpectedConditions.visibilityOf((findElement(driver, locatorType,
 * locatorName)))); //ExtentTestManager.getTest().log(Status.INFO,
 * //"wait was activated until desired element is visible on the page"); } else
 * if (conditionType.equalsIgnoreCase("clickable")) {
 * wait.until(ExpectedConditions.elementToBeClickable(findElement(driver,
 * locatorType, locatorName))); //ExtentTestManager.getTest().log(Status.INFO,
 * //"wait was activated until desired element is clickable on the page"); }
 * else if (conditionType.equalsIgnoreCase("presence")) { if (locatorType ==
 * "xpath") { wait.until(ExpectedConditions
 * .presenceOfElementLocated(By.xpath("\"" + prop.getElement(locatorName) +
 * "\""))); } else if (locatorType == "name") { wait.until(ExpectedConditions
 * .presenceOfElementLocated(By.name("\"" + prop.getElement(locatorName) +
 * "\""))); } else if (locatorType == "id") { wait.until(
 * ExpectedConditions.presenceOfElementLocated(By.id("\"" +
 * prop.getElement(locatorName) + "\""))); } else if (locatorType == "class") {
 * wait.until(ExpectedConditions .presenceOfElementLocated(By.className("\"" +
 * prop.getElement(locatorName) + "\""))); } else if (locatorType ==
 * "cssSelector") { wait.until(ExpectedConditions
 * .presenceOfElementLocated(By.cssSelector("\"" + prop.getElement(locatorName)
 * + "\""))); } else if (locatorType == "linkText") {
 * wait.until(ExpectedConditions .presenceOfElementLocated(By.linkText("\"" +
 * prop.getElement(locatorName) + "\""))); } else if (locatorType ==
 * "partialLinkText") { wait.until(ExpectedConditions
 * .presenceOfElementLocated(By.partialLinkText("\"" +
 * prop.getElement(locatorName) + "\""))); } else if (locatorType == "tagName")
 * { wait.until(ExpectedConditions .presenceOfElementLocated(By.tagName("\"" +
 * prop.getElement(locatorName) + "\""))); }
 * 
 * } else { //ExtentTestManager.getTest().log(Status.INFO,
 * "given condition type is not matching with available options"); System.out.
 * println("given condition type is not matching with available options"); } //
 * ExtentTestManager.getTest().log(Status.INFO, //
 * "wait was activated until desired element is present in the page source"); }
 * 
 * public void composeClick(WebDriver driver, WebDriverWait wait, String
 * ActionName) throws InterruptedException, IOException { // PLEASE PROVIDE THE
 * KEYWORD/ACTION NAME IN CAPITAL LETTERS final String opt1 = "UPDATE"; final
 * String opt2 = "QUESTION"; final String opt3 = "POST"; final String opt4 =
 * "MESSAGES"; final String opt5 = "TASK"; final String opt6 = "EVENT"; final
 * String opt7 = "POLL"; final String opt8 = "SURVEY"; final String opt9 =
 * "QUIZ"; final String opt10 = "IDEA"; final String opt11 = "CAMPAIGN"; final
 * String opt12 = "RECOGNIZE"; final String opt13 = "OPPORTUNITY"; final String
 * opt14 = "WIKIS"; final String opt15 = "TRACKER"; final String opt16 =
 * "REMINDER"; final String opt17 = "MEDIA"; final String opt18 = "NOTE"; final
 * String opt19 = "GREETING"; final String opt20 = "SCREENSHARE"; final String
 * opt21 = "BALLOT";
 * 
 * driver.get(FunctionLibrary.domainURL + prop.getProp("dashboardTabURL"));
 * Thread.sleep(1500); WebElement notify =
 * driver.findElement(By.xpath("//*[@id=\"notice\"]/a")); try { notify.click();
 * //ExtentTestManager.getTest().log(Status.INFO,
 * "Closed the Notification bar"); } catch (Exception e) {
 * System.out.println(e.getMessage()); } explicitWait(driver, "linkText",
 * "composeBoxButton"); clickElement(driver, findElement(driver, "linkText",
 * "composeBoxButton")); WebElement update = driver.findElement(By.
 * xpath("//*[@class=\"dropdown-menu composebox_menu\"]/li[1]/a")); WebElement
 * question = driver.findElement(By.
 * xpath("//*[@class=\"dropdown-menu composebox_menu\"]/li[2]/a"));
 * //ExtentTestManager.getTest().log(Status.INFO, "Clicked on Compose Box");
 * wait.until(ExpectedConditions.visibilityOf(question));
 * //ExtentTestManager.getTest().log(Status.INFO, "Compose Box is open now");
 * WebElement post = driver.findElement(By.
 * xpath("//*[@class=\"dropdown-menu composebox_menu\"]/li[3]/a")); WebElement
 * message = driver.findElement(By.
 * xpath("//*[@class=\"dropdown-menu composebox_menu\"]/li[4]/a")); WebElement
 * task = driver.findElement(By.
 * xpath("//*[@class=\"dropdown-menu composebox_menu\"]/li[5]/a")); WebElement
 * event = driver.findElement(By.
 * xpath("//*[@class=\"dropdown-menu composebox_menu\"]/li[6]/a")); WebElement
 * poll = driver.findElement(By.
 * xpath("//*[@class=\"dropdown-menu composebox_menu\"]/li[7]/a")); WebElement
 * survey = driver.findElement(By.
 * xpath("//*[@class=\"dropdown-menu composebox_menu\"]/li[8]/a")); WebElement
 * quiz = driver.findElement(By.
 * xpath("//*[@class=\"dropdown-menu composebox_menu\"]/li[9]/a")); WebElement
 * idea = driver.findElement(By.
 * xpath("//*[@class=\"dropdown-menu composebox_menu\"]/li[10]/a")); WebElement
 * campaign = driver.findElement(By.
 * xpath("//*[@class=\"dropdown-menu composebox_menu\"]/li[11]/a")); WebElement
 * recognize = driver.findElement(By.
 * xpath("//*[@class=\"dropdown-menu composebox_menu\"]/li[12]/a")); WebElement
 * opportunity = driver.findElement(By.
 * xpath("//*[@class=\"dropdown-menu composebox_menu\"]/li[13]/a")); WebElement
 * wikis = driver.findElement(By.
 * xpath("//*[@class=\"dropdown-menu composebox_menu\"]/li[14]/a")); WebElement
 * form = driver.findElement(By.
 * xpath("//*[@class=\"dropdown-menu composebox_menu\"]/li[15]/a")); WebElement
 * reminder = driver.findElement(By.
 * xpath("//*[@class=\"dropdown-menu composebox_menu\"]/li[16]/a")); WebElement
 * media = driver.findElement(By.
 * xpath("//*[@class=\"dropdown-menu composebox_menu\"]/li[17]/a")); WebElement
 * note = driver.findElement(By.
 * xpath("//*[@class=\"dropdown-menu composebox_menu\"]/li[18]/a")); WebElement
 * greeting = driver.findElement(By.
 * xpath("//*[@class=\"dropdown-menu composebox_menu\"]/li[19]/a")); WebElement
 * screenShare = driver.findElement(By.
 * xpath("//*[@class=\"dropdown-menu composebox_menu\"]/li[20]/a")); WebElement
 * ballot = driver.findElement(By.
 * xpath("//*[@class=\"dropdown-menu composebox_menu\"]/li[21]/a"));
 * 
 * switch (ActionName) { case opt1: mouseHover(driver, update); update.click();
 * Thread.sleep(1000); WebElement updateTo =
 * driver.findElement(By.id("token-input-myTeams")); keyDown(driver, updateTo,
 * "Automation Test"); //clickElement(driver, findElement(driver, "id",
 * "updateWithColleague")); //Thread.sleep(1000); updateTo.sendKeys(Keys.TAB);
 * 
 * upload(driver, "sampleText"); Thread.sleep(1000); mouseHover(driver,
 * findElement(driver, "xpath", "shareUpdate")); Thread.sleep(700);
 * clickElement(driver, findElement(driver, "xpath", "shareUpdate"));
 * Thread.sleep(1500); break; case opt2: mouseHover(driver, question);
 * question.click(); //ExtentTestManager.getTest().log(Status.PASS,
 * "Clicked on Question Successfully"); explicitWait(driver, "id",
 * "yourQuestion"); findElement(driver, "xpath", "toAllFollowers").click();
 * //ExtentTestManager.getTest().log(Status.INFO,
 * "Clicked on To all my followers"); findElement(driver, "id",
 * "yourQuestion").sendKeys(prop.getProp("questionDescription"));
 * //ExtentTestManager.getTest().log(Status.PASS,
 * "Entered the description of the question"); Thread.sleep(2000);
 * findElement(driver, "xpath", "askButton").click(); Thread.sleep(1000);
 * //ExtentTestManager.getTest().log(Status.PASS,
 * "Clicked on Ask Button Successfully"); break; case opt3: clickElement(driver,
 * post); //ExtentTestManager.getTest().log(Status.PASS,
 * "Clicked on Post Successfully"); Thread.sleep(1000); findElement(driver,
 * "class", "shareWithEveryone").click();
 * //ExtentTestManager.getTest().log(Status.PASS,
 * "Clicked on Share with Everyone Successfully"); findElement(driver, "xpath",
 * "postTemplateSelect").click(); findElement(driver, "id",
 * "postContinueButton").click(); //ExtentTestManager.getTest().log(Status.PASS,
 * "Clicked on Continue Button"); explicitWait(driver, "id", "postTitle");
 * WebElement postTitle = findElement(driver, "id", "postTitle");
 * postTitle.clear(); expectedPostTitle = prop.getProp("postTitle") + dateName;
 * postTitle.sendKeys(expectedPostTitle);
 * //ExtentTestManager.getTest().log(Status.PASS, "Entered the Post Title As: "
 * + expectedPostTitle); Thread.sleep(2000);
 * 
 * findElement(driver, "id", "postFeaturedImage").click();
 * //ExtentTestManager.getTest().log(Status.PASS,
 * "Clicked on featured image of the post"); Thread.sleep(2000);
 * findElement(driver, "id", "replaceMedia").click();
 * //ExtentTestManager.getTest().log(Status.PASS, "Clicked on replce media");
 * explicitWait(driver, "xpath", "uploadPostFeatIMG"); findElement(driver,
 * "xpath", "uploadPostFeatIMG").click(); Thread.sleep(1000);
 * findElement(driver, "xpath", "selectPageMedia").click(); Thread.sleep(1000);
 * 
 * upload(driver, "samplePicPath"); Thread.sleep(3000); findElement(driver,
 * "xpath", "FeaturedImageConfirmation").click(); Thread.sleep(1000);
 * 
 * WebElement videoFile = findElement(driver, "xpath", "videoInPost");
 * scrollToView(driver, videoFile); videoFile.click(); Thread.sleep(1000);
 * findElement(driver, "xpath", "uploadPostVideo").click();
 * wait.until(ExpectedConditions.visibilityOf(findElement(driver, "id",
 * "selectPostFilesButton"))).click(); Thread.sleep(2000); upload(driver,
 * "VideoFilePath");
 * wait.until(ExpectedConditions.elementToBeClickable(findElement(driver,
 * "xpath", "postVideoOkButton"))) .click(); Thread.sleep(2000);
 * findElement(driver, "xpath", "publishPostButton").click(); //
 * ExtentTestManager.getTest().log(Status.PASS,
 * "Clicked on publish Post Button"); Thread.sleep(1500); findElement(driver,
 * "xpath", "userToolsconfirmationButton").click(); Thread.sleep(3000); break;
 * case opt4: clickElement(driver, message); Thread.sleep(1000);
 * //ExtentTestManager.getTest().log(Status.PASS,
 * "Clicked on Message Successfully");
 * wait.until(ExpectedConditions.visibilityOf(findElement(driver, "id",
 * "messageTo"))); WebElement mailTo = findElement(driver, "id", "messageTo");
 * keyDown(driver, mailTo, "Test User1");
 * driver.findElement(By.id("direct_subject_field")).
 * sendKeys("This is a test mail");
 * driver.findElement(By.id("project_status_update")).
 * sendKeys("This mail is just to check the functionality"); Thread.sleep(1000);
 * WebElement sendButton =
 * driver.findElement(By.xpath("//*[@id=\"ms-feed-btn\"]/span/span"));
 * clickElement(driver, sendButton); Thread.sleep(2000); break; case opt5:
 * mouseHover(driver, task); task.click(); Thread.sleep(1000);
 * //ExtentTestManager.getTest().log(Status.PASS,
 * "Clicked on Post Successfully");
 * wait.until(ExpectedConditions.visibilityOf(findElement(driver, "id", "")));
 * break; case opt6: clickElement(driver, event); Thread.sleep(1000);
 * //ExtentTestManager.getTest().log(Status.PASS,
 * "Clicked on Event Successfully");
 * wait.until(ExpectedConditions.visibilityOf(findElement(driver, "id",
 * "eventTitle")));
 * 
 * findElement(driver, "id",
 * "eventTitle").sendKeys("This event is to test Meeting flow");
 * //ExtentTestManager.getTest().log(Status.PASS,
 * "Entered the event title Successfully"); Thread.sleep(1000);
 * findElement(driver, "id", "eventLocation").sendKeys("Conference Room");
 * //ExtentTestManager.getTest().log(Status.PASS,
 * "Entered the event Location Successfully"); findElement(driver, "xpath",
 * "eventRecurrence").click(); Thread.sleep(1500); keyDown(driver,
 * findElement(driver, "id", "addEventAttendees"), "Test User1");
 * 
 * WebElement createEvent = findElement(driver, "xpath", "createEventButton");
 * Thread.sleep(1000); clickElement(driver, createEvent);
 * //ExtentTestManager.getTest().log(Status.PASS, "Clicked on create Event");
 * Thread.sleep(1000); break; case opt7: clickElement(driver, poll);
 * Thread.sleep(1000); //ExtentTestManager.getTest().log(Status.PASS,
 * "Clicked on Poll Successfully"); findElement(driver, "xpath",
 * "pollToAllMyFollowers").click();
 * //ExtentTestManager.getTest().log(Status.PASS,
 * "Selected the option to send to all my followers"); Thread.sleep(1000);
 * findElement(driver, "id", "pollQuestionBody").click(); findElement(driver,
 * "id", "pollQuestionBody").sendKeys("Shall we go for outing?");
 * //ExtentTestManager.getTest().log(Status.PASS,
 * "Entered the body of the Poll"); Thread.sleep(1500); clickElement(driver,
 * findElement(driver, "xpath", "createPollButton"));
 * //ExtentTestManager.getTest().log(Status.PASS,
 * "Clicked on Create Poll Button"); Thread.sleep(2000); break; case opt8:
 * clickElement(driver, survey); Thread.sleep(2000);
 * //ExtentTestManager.getTest().log(Status.PASS,
 * "Clicked on Survey Successfully"); WebElement selectSurvey =
 * findElement(driver, "xpath", "selectSurvey");
 * wait.until(ExpectedConditions.visibilityOf(selectSurvey)); mouseHover(driver,
 * selectSurvey); Thread.sleep(500); selectSurvey.click(); Thread.sleep(1000);
 * findElement(driver, "xpath", "ContinueSurvey").click(); explicitWait(driver,
 * "linkText", "saveNContinue"); List<WebElement> delete =
 * driver.findElements(By.xpath("//a[@title=\"Delete this question\"]")); for
 * (int i = 0; i < delete.size(); i = i + 2) { clickElement(driver,
 * delete.get(i)); Thread.sleep(500); } Thread.sleep(1000); findElement(driver,
 * "linkText", "saveNContinue").click(); Thread.sleep(2000); WebElement
 * pickAudience = findElement(driver, "id", "pickAudience");
 * clickElement(driver, pickAudience); findElement(driver, "linkText",
 * "publishSurvey").click(); Thread.sleep(3000); break; case opt9:
 * mouseHover(driver, quiz); quiz.click(); Thread.sleep(1000);
 * //ExtentTestManager.getTest().log(Status.PASS,
 * "Clicked on Post Successfully");
 * wait.until(ExpectedConditions.visibilityOf(findElement(driver, "id", "")));
 * break; case opt10: clickElement(driver, idea); Thread.sleep(1000);
 * //ExtentTestManager.getTest().log(Status.PASS,
 * "Clicked on IDEA Successfully"); WebElement toAll = findElement(driver, "id",
 * "ideaToAll"); Thread.sleep(2000); clickElement(driver, toAll);
 * //ExtentTestManager.getTest().log(Status.PASS,
 * "Clicked on to All followers radio button");
 * 
 * findElement(driver, "id",
 * "ideaTitle").sendKeys("Automation Test Idea "+dateName);
 * //ExtentTestManager.getTest().log(Status.PASS, "Entered the title for Idea");
 * findElement(driver, "id", "ideaTitle").sendKeys(Keys.TAB);
 * Thread.sleep(1000);
 * 
 * upload(driver, "sampleText");
 * FunctionLibrary.myRobo.keyPress(KeyEvent.VK_TAB);
 * FunctionLibrary.myRobo.keyRelease(KeyEvent.VK_TAB); Thread.sleep(500);
 * 
 * //ExtentTestManager.getTest().log(Status.PASS,
 * "campaign name is: "+campaignName); Select mySel = new
 * Select(findElement(driver, "id", "chooseCampaign"));
 * mySel.selectByVisibleText(campaignName);
 * //ExtentTestManager.getTest().log(Status.PASS,
 * "selected the campaign for the idea"); Thread.sleep(1000);
 * clickElement(driver, findElement(driver, "xpath", "shareIdeaButton"));
 * //ExtentTestManager.getTest().log(Status.PASS, "clicked on Share button");
 * Thread.sleep(2000); break; case opt11: clickElement(driver, campaign);
 * Thread.sleep(1000); //ExtentTestManager.getTest().log(Status.PASS,
 * "Clicked on Campaign Successfully");
 * wait.until(ExpectedConditions.visibilityOf(findElement(driver, "id",
 * "campaignTitle")));
 * 
 * // scrollToView(driver, findElement(driver, "id",
 * "campaignTitle"));Thread.sleep(1000);
 * 
 * campaignName = "Automation Campaign " + dateName; findElement(driver, "id",
 * "campaignTitle").sendKeys(campaignName); findElement(driver, "id",
 * "campaignTitle").sendKeys(Keys.TAB, Keys.TAB); // findElement(driver, "id",
 * "campaignTitle").sendKeys(Keys.TAB); //
 * ExtentTestManager.getTest().log(Status.PASS,
 * "Entered the campaign name as: "+campaignName); Thread.sleep(2500);
 * 
 * // To paste the text into the frame upload(driver, "sampleText");
 * //ExtentTestManager.getTest().log(Status.PASS,
 * "Entered the comment successfully"); Thread.sleep(2000);
 * 
 * findElement(driver, "xpath", "createCampaignButton").click();
 * //ExtentTestManager.getTest().log(Status.PASS, "Clicked on create campaign");
 * Thread.sleep(2000);
 * 
 * break; case opt12: clickElement(driver, recognize); Thread.sleep(1500);
 * //ExtentTestManager.getTest().log(Status.PASS,
 * "Clicked on Recognize Successfully");
 * wait.until(ExpectedConditions.visibilityOf(findElement(driver, "id",
 * "awardToUser"))); WebElement awardToUser = findElement(driver, "id",
 * "awardToUser"); keyDown(driver, awardToUser, "Test User1");
 * Thread.sleep(1500); clickElement(driver,
 * driver.findElement(By.xpath("//span[text()=" + prop.getProp("awardToGive") +
 * "]"))); Thread.sleep(500); clickElement(driver, findElement(driver, "id",
 * "giveAward")); Thread.sleep(2000); break; case opt13: mouseHover(driver,
 * opportunity); opportunity.click(); Thread.sleep(1000);
 * //ExtentTestManager.getTest().log(Status.PASS,
 * "Clicked on Post Successfully");
 * wait.until(ExpectedConditions.visibilityOf(findElement(driver, "id", "")));
 * break; case opt14: clickElement(driver, wikis); Thread.sleep(1000);
 * //ExtentTestManager.getTest().log(Status.PASS,
 * "Clicked on Wikis Successfully"); wikiCreation(driver); break; case opt15:
 * clickElement(driver, form); Thread.sleep(1000);
 * //ExtentTestManager.getTest().log(Status.PASS,
 * "Clicked on TRACKER Successfully");
 * 
 * WebElement template = findElement(driver, "xpath", "trackerTemplate");
 * Thread.sleep(2000); mouseHover(driver, template); template.click();
 * //clickElement(driver, template); Thread.sleep(1000);
 * //ExtentTestManager.getTest().log(Status.PASS,
 * "Selected the template as needed"); findElement(driver, "linkText",
 * "trackerContinue").click(); //ExtentTestManager.getTest().log(Status.PASS,
 * "Clicked on Continue"); Thread.sleep(1500);
 * 
 * findElement(driver, "id", "trackerName").clear(); String
 * trackerName=prop.getProp("trackerName")+dateName; findElement(driver, "id",
 * "trackerName").sendKeys(trackerName);
 * //ExtentTestManager.getTest().log(Status.PASS,
 * "Entered Tracker Name as: "+trackerName); Thread.sleep(1000);
 * 
 * WebElement teamTo=findElement(driver, "id", "formToTeam"); keyDown(driver,
 * teamTo, "Automation Test");
 * 
 * findElement(driver, "id", "createTracker").click(); Thread.sleep(2500);
 * 
 * String actualTracker="//span[@title=\""+trackerName+"\"]";
 * if(driver.findElements(By.xpath(actualTracker)).size()!=0) {
 * //ExtentTestManager.getTest().log(Status.PASS,
 * "Tracker creation is successfull"); }else {
 * //ExtentTestManager.getTest().log(Status.FAIL,
 * "Tracker is not created: Please check"); }
 * 
 * break; case opt16: mouseHover(driver, reminder); reminder.click();
 * Thread.sleep(1000); // ExtentTestManager.getTest().log(Status.PASS,
 * "Clicked on Post Successfully");
 * wait.until(ExpectedConditions.visibilityOf(findElement(driver, "id", "")));
 * break; case opt17: mouseHover(driver, media); media.click();
 * Thread.sleep(1000); //ExtentTestManager.getTest().log(Status.PASS,
 * "Clicked on Post Successfully");
 * wait.until(ExpectedConditions.visibilityOf(findElement(driver, "id", "")));
 * break; case opt18: mouseHover(driver, note); note.click();
 * Thread.sleep(1000); //ExtentTestManager.getTest().log(Status.PASS,
 * "Clicked on Post Successfully");
 * wait.until(ExpectedConditions.visibilityOf(findElement(driver, "id", "")));
 * break; case opt19: mouseHover(driver, greeting); greeting.click();
 * Thread.sleep(1000); // ExtentTestManager.getTest().log(Status.PASS,
 * "Clicked on Post Successfully");
 * wait.until(ExpectedConditions.visibilityOf(findElement(driver, "id", "")));
 * break; case opt20: mouseHover(driver, screenShare); screenShare.click();
 * Thread.sleep(1000); //ExtentTestManager.getTest().log(Status.PASS,
 * "Clicked on Post Successfully");
 * wait.until(ExpectedConditions.visibilityOf(findElement(driver, "id", "")));
 * break; case opt21: mouseHover(driver, ballot); ballot.click();
 * Thread.sleep(1000); //ExtentTestManager.getTest().log(Status.PASS,
 * "Clicked on Post Successfully");
 * wait.until(ExpectedConditions.visibilityOf(findElement(driver, "id", "")));
 * break; default: //ExtentTestManager.getTest().log(Status.FAIL,
 * "Please provide valid ActionItem for compose Box"); break; }
 * 
 * }
 * 
 * public void composeClick(WebDriver driver, WebDriverWait wait, String
 * ActionName, String OptionVariable) throws InterruptedException, IOException {
 * // PLEASE PROVIDE THE KEYWORD/ACTION NAME IN CAPITAL LETTERS final String
 * opt1 = "UPDATE"; final String opt2 = "QUESTION"; final String opt3 = "POST";
 * final String opt4 = "MESSAGES"; final String opt5 = "TASK"; final String opt6
 * = "EVENT"; final String opt7 = "POLL"; final String opt8 = "SURVEY"; final
 * String opt9 = "QUIZ"; final String opt10 = "IDEA"; final String opt11 =
 * "CAMPAIGN"; final String opt12 = "RECOGNIZE"; final String opt13 =
 * "OPPORTUNITY"; final String opt14 = "WIKIS"; final String opt15 = "TRACKER";
 * final String opt16 = "REMINDER"; final String opt17 = "MEDIA"; final String
 * opt18 = "NOTE"; final String opt19 = "GREETING"; final String opt20 =
 * "SCREENSHARE"; final String opt21 = "BALLOT";
 * driver.get(FunctionLibrary.domainURL + prop.getProp("dashboardTabURL"));
 * Thread.sleep(1000); WebElement notify =
 * driver.findElement(By.xpath("//*[@id=\"notice\"]/a")); try { notify.click();
 * //ExtentTestManager.getTest().log(Status.INFO,
 * "Closed the Notification bar"); } catch (Exception e) {
 * System.out.println(e.getMessage()); } explicitWait(driver, "linkText",
 * "composeBoxButton"); clickElement(driver, findElement(driver, "linkText",
 * "composeBoxButton")); WebElement update = driver.findElement(By.
 * xpath("//*[@class=\"dropdown-menu composebox_menu\"]/li[1]/a")); WebElement
 * question = driver.findElement(By.
 * xpath("//*[@class=\"dropdown-menu composebox_menu\"]/li[2]/a"));
 * //ExtentTestManager.getTest().log(Status.INFO, "Clicked on Compose Box");
 * wait.until(ExpectedConditions.visibilityOf(question));
 * //ExtentTestManager.getTest().log(Status.INFO, "Compose Box is open now");
 * WebElement post = driver.findElement(By.
 * xpath("//*[@class=\"dropdown-menu composebox_menu\"]/li[3]/a")); WebElement
 * message = driver.findElement(By.
 * xpath("//*[@class=\"dropdown-menu composebox_menu\"]/li[4]/a")); WebElement
 * task = driver.findElement(By.
 * xpath("//*[@class=\"dropdown-menu composebox_menu\"]/li[5]/a")); WebElement
 * event = driver.findElement(By.
 * xpath("//*[@class=\"dropdown-menu composebox_menu\"]/li[6]/a")); WebElement
 * poll = driver.findElement(By.
 * xpath("//*[@class=\"dropdown-menu composebox_menu\"]/li[7]/a")); WebElement
 * survey = driver.findElement(By.
 * xpath("//*[@class=\"dropdown-menu composebox_menu\"]/li[8]/a")); WebElement
 * quiz = driver.findElement(By.
 * xpath("//*[@class=\"dropdown-menu composebox_menu\"]/li[9]/a")); WebElement
 * idea = driver.findElement(By.
 * xpath("//*[@class=\"dropdown-menu composebox_menu\"]/li[10]/a")); WebElement
 * campaign = driver.findElement(By.
 * xpath("//*[@class=\"dropdown-menu composebox_menu\"]/li[11]/a")); WebElement
 * recognize = driver.findElement(By.
 * xpath("//*[@class=\"dropdown-menu composebox_menu\"]/li[12]/a")); WebElement
 * opportunity = driver.findElement(By.
 * xpath("//*[@class=\"dropdown-menu composebox_menu\"]/li[13]/a")); WebElement
 * wikis = driver.findElement(By.
 * xpath("//*[@class=\"dropdown-menu composebox_menu\"]/li[14]/a")); WebElement
 * form = driver.findElement(By.
 * xpath("//*[@class=\"dropdown-menu composebox_menu\"]/li[15]/a")); WebElement
 * reminder = driver.findElement(By.
 * xpath("//*[@class=\"dropdown-menu composebox_menu\"]/li[16]/a")); WebElement
 * media = driver.findElement(By.
 * xpath("//*[@class=\"dropdown-menu composebox_menu\"]/li[17]/a")); WebElement
 * note = driver.findElement(By.
 * xpath("//*[@class=\"dropdown-menu composebox_menu\"]/li[18]/a")); WebElement
 * greeting = driver.findElement(By.
 * xpath("//*[@class=\"dropdown-menu composebox_menu\"]/li[19]/a")); WebElement
 * screenShare = driver.findElement(By.
 * xpath("//*[@class=\"dropdown-menu composebox_menu\"]/li[20]/a")); WebElement
 * ballot = driver.findElement(By.
 * xpath("//*[@class=\"dropdown-menu composebox_menu\"]/li[21]/a"));
 * 
 * switch (ActionName) { case opt1: mouseHover(driver, update); update.click();
 * Thread.sleep(1000); WebElement updateTo =
 * driver.findElement(By.id("token-input-myTeams")); keyDown(driver, updateTo,
 * OptionVariable); updateTo.sendKeys(Keys.TAB);
 * 
 * upload(driver, "sampleText"); Thread.sleep(1000); mouseHover(driver,
 * findElement(driver, "xpath", "shareUpdate")); Thread.sleep(700);
 * clickElement(driver, findElement(driver, "xpath", "shareUpdate"));
 * Thread.sleep(1500); break; case opt2: mouseHover(driver, question);
 * question.click(); // ExtentTestManager.getTest().log(Status.PASS,
 * "Clicked on Question Successfully"); explicitWait(driver, "id",
 * "yourQuestion"); findElement(driver, "xpath", "toAllFollowers").click();
 * //ExtentTestManager.getTest().log(Status.INFO,
 * "Clicked on To all my followers"); findElement(driver, "id",
 * "yourQuestion").sendKeys(prop.getProp("questionDescription"));
 * //ExtentTestManager.getTest().log(Status.PASS,
 * "Entered the description of the question"); Thread.sleep(2000);
 * findElement(driver, "xpath", "askButton").click(); Thread.sleep(1000);
 * //ExtentTestManager.getTest().log(Status.PASS,
 * "Clicked on Ask Button Successfully"); break; case opt3: clickElement(driver,
 * post); //ExtentTestManager.getTest().log(Status.PASS,
 * "Clicked on Post Successfully"); Thread.sleep(1000); findElement(driver,
 * "class", "shareWithEveryone").click();
 * //ExtentTestManager.getTest().log(Status.PASS,
 * "Clicked on Share with Everyone Successfully");
 * 
 * findElement(driver, "xpath", "postTemplateSelect").click();
 * findElement(driver, "id", "postContinueButton").click();
 * //ExtentTestManager.getTest().log(Status.PASS, "Clicked on Continue Button");
 * 
 * explicitWait(driver, "id", "postTitle"); WebElement postTitle =
 * findElement(driver, "id", "postTitle"); postTitle.clear(); dateName = new
 * SimpleDateFormat("yyyyMMddhhmmss").format(new Date()); expectedPostTitle =
 * prop.getProp("postTitle") + dateName; postTitle.sendKeys(expectedPostTitle);
 * //ExtentTestManager.getTest().log(Status.PASS, "Entered the Post Title As: "
 * + expectedPostTitle); Thread.sleep(1500);
 * 
 * if (OptionVariable.equalsIgnoreCase("PostFeaturedImage")) {
 * findElement(driver, "id", "postFeaturedImage").click();
 * //ExtentTestManager.getTest().log(Status.PASS,
 * "Clicked on featured image of the post"); Thread.sleep(2000);
 * findElement(driver, "id", "replaceMedia").click();
 * //ExtentTestManager.getTest().log(Status.PASS, "Clicked on replce media");
 * explicitWait(driver, "xpath", "uploadPostFeatIMG"); findElement(driver,
 * "xpath", "uploadPostFeatIMG").click(); Thread.sleep(1000);
 * findElement(driver, "xpath", "selectPageMedia").click(); Thread.sleep(1000);
 * 
 * upload(driver, "samplePicPath"); Thread.sleep(3000); findElement(driver,
 * "xpath", "FeaturedImageConfirmation").click(); Thread.sleep(1000); } else if
 * (OptionVariable.equalsIgnoreCase("PostWithVideo")) { WebElement videoFile =
 * findElement(driver, "xpath", "videoInPost"); scrollToView(driver, videoFile);
 * videoFile.click(); //ExtentTestManager.getTest().log(Status.PASS,
 * "Clicked on video content part of the post"); Thread.sleep(1000);
 * findElement(driver, "xpath", "uploadPostVideo").click();
 * wait.until(ExpectedConditions.visibilityOf(findElement(driver, "id",
 * "selectPostFilesButton"))).click(); Thread.sleep(1000); upload(driver,
 * "VideoFilePath");
 * wait.until(ExpectedConditions.elementToBeClickable(findElement(driver,
 * "xpath", "postVideoOkButton"))) .click();
 * //ExtentTestManager.getTest().log(Status.PASS,
 * "Uploaded new video to the post"); } else if
 * (OptionVariable.equalsIgnoreCase("PostAsAnnouncement")) {
 * clickElement(driver, findElement(driver, "id", "postAnnouncement"));
 * //ExtentTestManager.getTest().log(Status.PASS,
 * "Clicked on announcement option of the post"); } else if
 * (OptionVariable.equalsIgnoreCase("ScheduledPost")) { clickElement(driver,
 * findElement(driver, "id", "postSchedule"));
 * //ExtentTestManager.getTest().log(Status.PASS,
 * "Clicked on schedule option of the post"); explicitWait(driver, "id",
 * "postScheduleDate"); mouseHover(driver, findElement(driver, "id",
 * "postScheduleDate")); Thread.sleep(1000); findElement(driver, "id",
 * "postScheduleDate").click(); //ExtentTestManager.getTest().log(Status.PASS,
 * "Clicked on calander option of the schedule post"); Thread.sleep(1000);
 * datePicker(driver, "5"); }
 * 
 * if (OptionVariable.equalsIgnoreCase("ScheduledPost")) { explicitWait(driver,
 * "xpath", "publishSchedulePost"); findElement(driver, "xpath",
 * "publishSchedulePost").click(); } else { explicitWait(driver, "xpath",
 * "publishPostButton"); findElement(driver, "xpath",
 * "publishPostButton").click(); Thread.sleep(1500); findElement(driver,
 * "xpath", "userToolsconfirmationButton").click(); } //
 * ExtentTestManager.getTest().log(Status.PASS,
 * "Clicked on publish Post Button"); Thread.sleep(4000); break; case opt4:
 * mouseHover(driver, message); message.click(); Thread.sleep(1000);
 * //ExtentTestManager.getTest().log(Status.PASS,
 * "Clicked on Message Successfully");
 * wait.until(ExpectedConditions.visibilityOf(findElement(driver, "id", "")));
 * WebElement mailTo =
 * driver.findElement(By.id("token-input-direct_user_field")); keyDown(driver,
 * mailTo, "Satheesh"); driver.findElement(By.id("direct_subject_field")).
 * sendKeys("This is a test mail");
 * driver.findElement(By.id("project_status_update")).
 * sendKeys("This mail is just to check the functionality");
 * 
 * driver.findElement(By.xpath(
 * "//*[@id=\"project_status_update\"]/following-sibling::div[3]")).click();
 * Thread.sleep(2000); WebElement gifimage = driver .findElement(By.xpath(
 * "//*[@id=\"mango-gif-picker--menu-items\"]/div/div/div[1]/a[1]"));
 * wait.until(ExpectedConditions.visibilityOf(gifimage)); try {
 * gifimage.click(); } catch (Exception e) { System.out.println(e.getMessage());
 * }
 * 
 * driver.findElement(By.xpath("//*[@id=\"frm-dm\"]/div[4]/div/div[2]/div[1]/a")
 * ).click(); Thread.sleep(1000); WebElement Done =
 * driver.findElement(By.id("btn_file_upload")); scrollToView(driver, Done);
 * Thread.sleep(500); driver.findElement(By.id("fileupload")).click();
 * Thread.sleep(2000); upload(driver, prop.getProp("filePath1"));
 * Thread.sleep(2000);
 * 
 * driver.findElement(By.xpath(
 * "//*[@id=\"file_dimension_default\"]/li[1]/p[2]/textarea"))
 * .sendKeys("uploading image is for test");
 * driver.findElement(By.id("tag-it_input_c")).sendKeys("awesome");
 * Thread.sleep(1000);
 * driver.findElement(By.id("tag-it_input_c")).sendKeys(Keys.ENTER); Select sel
 * = new Select(driver.findElement(By.id("attachments_attachment_id_658_")));
 * sel.selectByValue("14238"); Done.click(); WebElement sendButton =
 * driver.findElement(By.xpath("//*[@id=\"ms-feed-btn\"]/span/span"));
 * sendButton.click(); break; case opt5: mouseHover(driver, task); task.click();
 * Thread.sleep(1000); //ExtentTestManager.getTest().log(Status.PASS,
 * "Clicked on Post Successfully");
 * wait.until(ExpectedConditions.visibilityOf(findElement(driver, "id", "")));
 * break; case opt6: mouseHover(driver, event); event.click();
 * Thread.sleep(1000); //ExtentTestManager.getTest().log(Status.PASS,
 * "Clicked on Event Successfully"); if
 * (OptionVariable.equalsIgnoreCase("Custom Event")) { WebElement customEvent =
 * findElement(driver, "xpath", "customEvent"); clickElement(driver,
 * customEvent); } else if (OptionVariable.equalsIgnoreCase("Company Event")) {
 * WebElement companyEvent = findElement(driver, "xpath", "companyEvent");
 * clickElement(driver, companyEvent); } explicitWait(driver, "visibility",
 * "id", "eventTitle", 5);
 * wait.until(ExpectedConditions.visibilityOf(findElement(driver, "id",
 * "eventTitle"))); findElement(driver, "id",
 * "eventTitle").sendKeys("This event is to test " + OptionVariable + " flow");
 * // ExtentTestManager.getTest().log(Status.PASS,
 * "Entered the event title Successfully"); Thread.sleep(1000);
 * findElement(driver, "id", "eventLocation").sendKeys("Conference Room");
 * //ExtentTestManager.getTest().log(Status.PASS,
 * "Entered the event Location Successfully"); WebElement createEvent =
 * findElement(driver, "xpath", "createEventButton"); Thread.sleep(1000);
 * clickElement(driver, createEvent);
 * //ExtentTestManager.getTest().log(Status.PASS, "Clicked on create Event");
 * Thread.sleep(1000); break; case opt7: mouseHover(driver, poll); poll.click();
 * Thread.sleep(1000); //ExtentTestManager.getTest().log(Status.PASS,
 * "Clicked on Post Successfully");
 * wait.until(ExpectedConditions.visibilityOf(findElement(driver, "id", "")));
 * break; case opt8: mouseHover(driver, survey); survey.click();
 * Thread.sleep(1000); //ExtentTestManager.getTest().log(Status.PASS,
 * "Clicked on Post Successfully");
 * wait.until(ExpectedConditions.visibilityOf(findElement(driver, "id", "")));
 * break; case opt9: mouseHover(driver, quiz); quiz.click(); Thread.sleep(1000);
 * //ExtentTestManager.getTest().log(Status.PASS,
 * "Clicked on Post Successfully");
 * wait.until(ExpectedConditions.visibilityOf(findElement(driver, "id", "")));
 * break; case opt10: mouseHover(driver, idea); idea.click();
 * Thread.sleep(1000); //ExtentTestManager.getTest().log(Status.PASS,
 * "Clicked on Post Successfully");
 * wait.until(ExpectedConditions.visibilityOf(findElement(driver, "id", "")));
 * break; case opt11: mouseHover(driver, campaign); idea.click();
 * Thread.sleep(1000); //ExtentTestManager.getTest().log(Status.PASS,
 * "Clicked on Post Successfully");
 * wait.until(ExpectedConditions.visibilityOf(findElement(driver, "id", "")));
 * break; case opt12: clickElement(driver, recognize); Thread.sleep(1500);
 * //ExtentTestManager.getTest().log(Status.PASS,
 * "Clicked on Recognize Successfully");
 * wait.until(ExpectedConditions.visibilityOf(findElement(driver, "id",
 * "awardToUser")));
 * 
 * if(OptionVariable.equalsIgnoreCase("awardUser")) { clickElement(driver,
 * findElement(driver, "id", "awardToColleague"));
 * //ExtentTestManager.getTest().log(Status.PASS,
 * "Selected People Radio Button"); WebElement awardToUser = findElement(driver,
 * "id", "awardToUser"); keyDown(driver, awardToUser, "Test User1"); //
 * ExtentTestManager.getTest().log(Status.PASS, "Entered the colleague name");
 * 
 * Thread.sleep(1500); clickElement(driver,
 * driver.findElement(By.xpath("//span[text()=\"" + prop.getProp("awardToGive")
 * + "\"]"))); }else if (OptionVariable.equalsIgnoreCase("awardTeam")) {
 * WebElement awardCategory=findElement(driver, "id", "awardCategory");
 * selectFromDropdown(awardCategory, "visibleText", "Years of Service");
 * clickElement(driver, findElement(driver, "id", "awardToTeam"));
 * //ExtentTestManager.getTest().log(Status.PASS, "Selected Team radio button");
 * WebElement awardToTeam = findElement(driver, "id", "awardTeamName");
 * keyDown(driver, awardToTeam, "Automation Test"); //
 * ExtentTestManager.getTest().log(Status.PASS, "Entered the team name");
 * 
 * Thread.sleep(1500); clickElement(driver,
 * driver.findElement(By.xpath("//span[text()=\"" + prop.getProp("awardToTeam")
 * + "\"]"))); }
 * 
 * Thread.sleep(500); clickElement(driver, findElement(driver, "id",
 * "giveAward")); Thread.sleep(2000); break; case opt13: mouseHover(driver,
 * opportunity); opportunity.click(); Thread.sleep(1000);
 * //ExtentTestManager.getTest().log(Status.PASS,
 * "Clicked on Post Successfully");
 * wait.until(ExpectedConditions.visibilityOf(findElement(driver, "id", "")));
 * break; case opt14: mouseHover(driver, wikis); wikis.click();
 * Thread.sleep(1000); //ExtentTestManager.getTest().log(Status.PASS,
 * "Clicked on Wikis Successfully"); explicitWait(driver, "class", "wikiTitle");
 * String wikiTitle = "Test Automation Wiki " + System.currentTimeMillis();
 * findElement(driver, "class", "wikiTitle").sendKeys(wikiTitle);
 * Thread.sleep(1000); //ExtentTestManager.getTest().log(Status.PASS,
 * "Entered the Title of the Wiki as: " + wikiTitle); WebElement wikiToTeam =
 * findElement(driver, "id", "wikiToTeam");
 * wikiToTeam.sendKeys(prop.getProp("wikiToTeam")); Thread.sleep(3000);
 * wikiToTeam.sendKeys(Keys.ENTER);
 * //ExtentTestManager.getTest().log(Status.PASS,
 * "Entered the Team Name to send Wiki To"); findElement(driver, "xpath",
 * "wikiTemplateSelect").click(); //ExtentTestManager.getTest().log(Status.PASS,
 * "Selected Desire Template for Wiki"); findElement(driver, "xpath",
 * "createWikiButton").click(); //ExtentTestManager.getTest().log(Status.PASS,
 * "Clicked on create Wiki Button"); Thread.sleep(4000); mouseHover(driver,
 * findElement(driver, "xpath", "wikiPublishButton")); findElement(driver,
 * "xpath", "wikiPublishButton").click();
 * //ExtentTestManager.getTest().log(Status.PASS,
 * "Clicked on Publish Wiki Button"); Thread.sleep(2000); break; case opt15:
 * clickElement(driver, form); Thread.sleep(1000);
 * //ExtentTestManager.getTest().log(Status.PASS,
 * "Clicked on TRACKER Successfully");
 * 
 * scrollToPageEnd(driver); Thread.sleep(2000); String
 * temp="//div[text()=\""+OptionVariable+"\"]"; WebElement template =
 * driver.findElement(By.xpath(temp)); Thread.sleep(2000); mouseHover(driver,
 * template); template.click(); //clickElement(driver, template);
 * Thread.sleep(1000); //ExtentTestManager.getTest().log(Status.PASS,
 * "Selected the template as needed"); findElement(driver, "linkText",
 * "trackerContinue").click(); //ExtentTestManager.getTest().log(Status.PASS,
 * "Clicked on Continue"); Thread.sleep(1500);
 * 
 * findElement(driver, "id", "trackerName").clear(); String
 * trackerName="Template Tracker "+dateName; findElement(driver, "id",
 * "trackerName").sendKeys(trackerName);
 * //ExtentTestManager.getTest().log(Status.PASS,
 * "Entered Tracker Name as: "+trackerName); Thread.sleep(1000);
 * 
 * WebElement teamTo=findElement(driver, "id", "formToTeam"); keyDown(driver,
 * teamTo, "Automation Test");
 * 
 * findElement(driver, "id", "createTracker").click(); Thread.sleep(2500);
 * 
 * String actualTracker="//span[@title=\""+trackerName+"\"]";
 * if(driver.findElements(By.xpath(actualTracker)).size()!=0) {
 * //ExtentTestManager.getTest().log(Status.PASS,
 * "Tracker creation is successfull"); }else {
 * //ExtentTestManager.getTest().log(Status.FAIL,
 * "Tracker is not created: Please check"); }
 * 
 * break; case opt16: mouseHover(driver, reminder); reminder.click();
 * Thread.sleep(1000); //ExtentTestManager.getTest().log(Status.PASS,
 * "Clicked on Post Successfully");
 * wait.until(ExpectedConditions.visibilityOf(findElement(driver, "id", "")));
 * break; case opt17: mouseHover(driver, media); media.click();
 * Thread.sleep(1000); // ExtentTestManager.getTest().log(Status.PASS,
 * "Clicked on Post Successfully");
 * wait.until(ExpectedConditions.visibilityOf(findElement(driver, "id", "")));
 * break; case opt18: mouseHover(driver, note); note.click();
 * Thread.sleep(1000); //ExtentTestManager.getTest().log(Status.PASS,
 * "Clicked on Post Successfully");
 * wait.until(ExpectedConditions.visibilityOf(findElement(driver, "id", "")));
 * break; case opt19: mouseHover(driver, greeting); greeting.click();
 * Thread.sleep(1000); //ExtentTestManager.getTest().log(Status.PASS,
 * "Clicked on Post Successfully");
 * wait.until(ExpectedConditions.visibilityOf(findElement(driver, "id", "")));
 * break; case opt20: mouseHover(driver, screenShare); screenShare.click();
 * Thread.sleep(1000); // ExtentTestManager.getTest().log(Status.PASS,
 * "Clicked on Post Successfully");
 * wait.until(ExpectedConditions.visibilityOf(findElement(driver, "id", "")));
 * break; case opt21: mouseHover(driver, ballot); ballot.click();
 * Thread.sleep(1000); // ExtentTestManager.getTest().log(Status.PASS,
 * "Clicked on Post Successfully");
 * wait.until(ExpectedConditions.visibilityOf(findElement(driver, "id", "")));
 * break; default: // ExtentTestManager.getTest().log(Status.FAIL,
 * "Please provide valid ActionItem for compose Box"); break; }
 * 
 * }
 * 
 * public static void sendEmail(String from, String tos[], String subject,
 * String text) throws MessagingException { // Get the session object Properties
 * props = new Properties(); props.put("mail.smtp.host", "smtp.gmail.com");
 * props.put("mail.smtp.socketFactory.port", "465");
 * props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
 * props.put("mail.smtp.auth", "true"); props.put("mail.smtp.port", "465");
 * Session session = Session.getDefaultInstance(props, new
 * javax.mail.Authenticator() { protected PasswordAuthentication
 * getPasswordAuthentication() { return new
 * PasswordAuthentication("mangorobot12@gmail.com", "Mango@05"); //
 * "satheeshg@mangospring.com", // "mango@05");// change accordingly } }); //
 * compose message try { MimeMessage message = new MimeMessage(session);
 * message.setFrom(new InternetAddress(from));// change accordingly for (String
 * to : tos) { message.addRecipient(Message.RecipientType.TO, new
 * InternetAddress(to)); }
 * 
 * for (String cc : ccs) message.addRecipient(Message.RecipientType.CC,new
 * InternetAddress(cc));
 * 
 * message.setSubject(subject); // Option 1: To send normal text message
 * message.setText(text); // Option 2: Send the actual HTML message, as big as
 * you like // message.setContent("<h1>This is actual message</h1></br></hr>" +
 * // text, "text/html"); // Set the attachment path String filename =
 * ReusableAssets.dir + "\\src\\main\\java\\utilities\\profilePic.jpg"; BodyPart
 * objMessageBodyPart = new MimeBodyPart(); // Option 3: Send text along with
 * attachment objMessageBodyPart.setContent(
 * "<h1>Test Mail from Selenium Project for Incoming EMail Test!</h1></br>" +
 * text, "text/html"); Multipart multipart = new MimeMultipart();
 * multipart.addBodyPart(objMessageBodyPart); objMessageBodyPart = new
 * MimeBodyPart(); DataSource source = new FileDataSource(filename);
 * objMessageBodyPart.setDataHandler(new DataHandler(source));
 * objMessageBodyPart.setFileName(filename);
 * multipart.addBodyPart(objMessageBodyPart); message.setContent(multipart); //
 * send message Transport.send(message);
 * System.out.println("message sent successfully"); } catch (MessagingException
 * e) { System.out.println(e.toString()); ; } }
 * 
 * // \\TestReport\\MangoApps-Automaton-Report.html public static void
 * sendFinalReportEmail(String from, String tos[], int totalpass, int totalfail,
 * int totalskip) throws MessagingException { // Get the session object String
 * text =
 * "Please download and view attached html report for more details of the above results"
 * ; Properties props = new Properties(); props.put("mail.smtp.host",
 * "smtp.gmail.com"); props.put("mail.smtp.socketFactory.port", "465");
 * props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
 * props.put("mail.smtp.auth", "true"); props.put("mail.smtp.port", "465");
 * Session session = Session.getDefaultInstance(props, new
 * javax.mail.Authenticator() { protected PasswordAuthentication
 * getPasswordAuthentication() { return new
 * PasswordAuthentication("mangorobot12@gmail.com", "Mango@05"); //
 * "satheeshg@mangospring.com", // "mango@05");// change accordingly } }); //
 * compose message try { MimeMessage message = new MimeMessage(session);
 * message.setFrom(new InternetAddress(from));// change accordingly for (String
 * to : tos) { message.addRecipient(Message.RecipientType.TO, new
 * InternetAddress(to)); }
 * 
 * for (String cc : ccs) message.addRecipient(Message.RecipientType.CC,new
 * InternetAddress(cc));
 * 
 * message.
 * setSubject("Automation Report: Client/Module Specific Automation Suite"); //
 * Option 1: To send normal text message message.setText(text); // Option 2:
 * Send the actual HTML message, as big as you like //
 * message.setContent("<h1>This is actual message</h1></br></hr>" + // text,
 * "text/html"); // Set the attachment path String filename = ReusableAssets.dir
 * + "\\TestReport\\MA-Client-Suite-Automation-Report-" + reportDate + ".html";
 * BodyPart objMessageBodyPart = new MimeBodyPart(); // Option 3: Send text
 * along with attachment // String htmlValue="<thead
 * bgcolor='#3c1d6b'><tr><th><font //
 * color=\"#ffffff\">Feature Name</font></th><th><font color=\"#ffffff\">Planned
 * // count</font></th><th bgcolor='limegreen'><font color=\"#ffffff\">Pass //
 * Count</font></th><th bgcolor='limegreen'><font color=\"#ffffff\">% //
 * Passed</font></th><th bgcolor='red'><font color=\"#ffffff\">Fail //
 * Count</font></th><th bgcolor='red'><font color=\"#ffffff\">% //
 * Failed</font></th><th bgcolor='darkkhaki'><font color=\"#ffffff\">Skipped //
 * Count</font></th><th bgcolor='darkkhaki'><font color=\"#ffffff\">% //
 * Skipped</font></th></tr></thead>"; int totalpassfail = totalpass + totalfail
 * + totalskip;
 * 
 * // double totalpasspercentage =
 * ((double)totalpass*100)/(double)totalpassfail; // double totalfailpercentage
 * = ((double)totalfail*100)/(double)totalpassfail; // double
 * totalskippedpercentage = // ((double)totalskip*100)/(double)totalpassfail;
 * 
 * // htmlValue=htmlValue.concat("<tr //
 * style='background-color:#999999'><td><b>Sanity //
 * Suite</td><td><b>"+String.valueOf(totalpassfail)+"</td><td><b>
 * "+String.valueOf(totalpass)+"</td><td><b>"+String.format("%.2f%%
 * ",totalpasspercentage)+"</td><td><b>"+String.valueOf(totalfail)+"</td><td><b>
 * "+String.format("%.2f%%",totalfailpercentage)+"</td><td><b>
 * "+String.valueOf(totalskip)+"</td><td><b>"+String.format("%.2f%%
 * ",totalskippedpercentage)+"</td></tr>"); String domainRun =
 * "Script Domain URL: <b>" + FunctionLibrary.domainURL; String dataCenter1 =
 * "Data Center of Domain: <b>" + FunctionLibrary.dataCenter; String resultTotal
 * = "Total count is: "; String resultPass = "Pass count is: "; String
 * resultFail = "Failed count is: "; String resultSkip = "Skipped count is: ";
 * String result = resultTotal + "<b>" + String.valueOf(totalpassfail) +
 * "</b><br>" + resultPass + "<b>" + String.valueOf(totalpass) + "</b><br>" +
 * resultFail + "<b>" + String.valueOf(totalfail) + "</b><br>" + resultSkip +
 * "<b>" + String.valueOf(totalskip) + "</b>"; //
 * objMessageBodyPart.setContent("<h3>Mango-Apps Sanity Points Automation Test
 * // Results:</h3>"+htmlValue+"</br><br><br>" + text, "text/html");
 * objMessageBodyPart.
 * setContent("<h3>Client/Module Specific Suite Automation Test Run Results:</h3>"
 * + domainRun + "</b><br>" + dataCenter1 + "</b><br><br>" + result +
 * "</br><br><br>" + text, "text/html"); Multipart multipart = new
 * MimeMultipart(); multipart.addBodyPart(objMessageBodyPart);
 * objMessageBodyPart = new MimeBodyPart(); DataSource source = new
 * FileDataSource(filename); objMessageBodyPart.setDataHandler(new
 * DataHandler(source)); objMessageBodyPart.setFileName(filename);
 * multipart.addBodyPart(objMessageBodyPart); message.setContent(multipart); //
 * send message Transport.send(message); for (String to : tos) {
 * System.out.println("Final report is successfully sent to " + to); } } catch
 * (MessagingException e) { System.out.println(e.toString()); } }
 * 
 * public static void readEmail(String message) { Folder folder = null; Store
 * store = null; System.out.println("***READING MAILBOX..."); try { Properties
 * props = new Properties(); props.put("mail.store.protocol", "imaps"); Session
 * session = Session.getInstance(props); store = session.getStore("imaps");
 * store.connect("imap.gmail.com", "mangorobot12@gmail.com", "Mango@05"); folder
 * = store.getFolder("INBOX"); folder.open(Folder.READ_ONLY); Message[] messages
 * = folder.getMessages(); System.out.println("No of Messages : " +
 * folder.getMessageCount()); System.out.println("No of Unread Messages : " +
 * folder.getUnreadMessageCount()); for (int i = 0; i < messages.length; i++) {
 * System.out.println("Reading MESSAGE # " + (i + 1) + "..."); Message msg =
 * messages[i]; if (!msg.getFlags().contains(Flags.Flag.SEEN)) { String
 * strMailSubject = ""; // strMailBody = ""; // Getting mail subject Object
 * subject = msg.getSubject(); Date receivedDate = msg.getReceivedDate(); //
 * Getting mail body // Object content = getTextFromMessage(msg); // Casting
 * objects of mail subject and body into String
 * 
 * strMailSubject = (String) subject.toString(); // strMailBody = (String)
 * content.toString(); // ---- This is what you want to do------ if
 * (strMailSubject.contains(message)) { System.out.println(strMailSubject);
 * //ExtentTestManager.getTest().log(Status.PASS,
 * "Successfully Received the Outgoing email");
 * //ExtentTestManager.getTest().log(Status.PASS, "Subject of the mail is: " +
 * strMailSubject); //ExtentTestManager.getTest().log(Status.PASS,
 * "Mail was Received on: " + receivedDate); break; } } } } catch
 * (MessagingException messagingException) {
 * messagingException.printStackTrace(); } finally { if (folder != null) { try {
 * folder.close(true); } catch (MessagingException e) { // TODO Auto-generated
 * catch block e.printStackTrace(); } } if (store != null) { try {
 * store.close(); } catch (MessagingException e) { // TODO Auto-generated catch
 * block e.printStackTrace(); } } } }
 * 
 * public static void openNewTab(WebDriver driver) { ((JavascriptExecutor)
 * driver).executeScript("window.open()"); }
 * 
 * public String onPaste() throws UnsupportedFlavorException, IOException {
 * Toolkit toolkit = Toolkit.getDefaultToolkit(); Clipboard clipboard =
 * toolkit.getSystemClipboard(); String result = (String)
 * clipboard.getData(DataFlavor.stringFlavor);
 * System.out.println("String from Clipboard:" + result); return result; }
 * 
 * public int findRowCountOfWebTable(WebDriver driver, String rowPath) {
 * List<WebElement> rowSize = driver.findElements(By.xpath(rowPath)); return
 * rowSize.size(); }
 * 
 * public int findColCountOfWebTable(WebDriver driver, String rowPath) {
 * List<WebElement> rowSize = driver.findElements(By.xpath(rowPath)); int
 * rowCount = rowSize.size(); List<WebElement> colSize =
 * driver.findElements(By.xpath(rowPath + "[" + rowCount + "]/td")); return
 * colSize.size(); }
 * 
 * public int numberOfWebElements(WebDriver driver, String elementPath) {
 * List<WebElement> totalElements = driver.findElements(By.xpath(elementPath));
 * return totalElements.size(); }
 * 
 * public void wikiCreation(WebDriver driver) { try { explicitWait(driver,
 * "class", "wikiTitle"); String wikiTitle = "Test Automation Wiki " +
 * System.currentTimeMillis(); findElement(driver, "class",
 * "wikiTitle").sendKeys(wikiTitle); Thread.sleep(1000); //
 * ExtentTestManager.getTest().log(Status.PASS,
 * "Entered the Title of the Wiki as: " + wikiTitle);
 * 
 * try { WebElement wikiToTeam = findElement(driver, "id", "wikiToTeam");
 * wikiToTeam.sendKeys(prop.getProp("wikiToTeam")); Thread.sleep(3000);
 * wikiToTeam.sendKeys(Keys.ENTER);
 * //ExtentTestManager.getTest().log(Status.PASS,
 * "Entered the Team Name to send Wiki To"); } catch (Exception e) {
 * //ExtentTestManager.getTest().log(Status.INFO,
 * "Team Option is not available " + e.toString()); } Thread.sleep(500);
 * 
 * clickElement(driver, findElement(driver, "xpath", "wikiTemplateSelect")); //
 * ExtentTestManager.getTest().log(Status.PASS,
 * "Selected Desire Template for Wiki"); Thread.sleep(1500);
 * 
 * clickElement(driver, findElement(driver, "xpath", "createWikiButton"));
 * //ExtentTestManager.getTest().log(Status.PASS,
 * "Clicked on create Wiki Button"); Thread.sleep(5000);
 * 
 * clickElement(driver, findElement(driver, "xpath", "wikiPublishButton"));
 * //ExtentTestManager.getTest().log(Status.PASS,
 * "Clicked on Publish Wiki Button"); Thread.sleep(3000); } catch (Exception e)
 * { //ExtentTestManager.getTest().log(Status.FAIL, "Error in try block " +
 * e.toString()); }
 * 
 * }
 * 
 * public void shortCutCreation(WebDriver driver, WebDriverWait wait, String
 * shortCutURL) { try { FunctionLibrary.getURL("navigationURL");
 * explicitWait(driver, "xpath", "addShortCut"); findElement(driver, "xpath",
 * "addShortCut").click(); //ExtentTestManager.getTest().log(Status.PASS,
 * "Clicked on add ShortCut Button");
 * 
 * dateName=new SimpleDateFormat("yyyyMMddhhmmss").format(new Date()); String
 * shortcut = "ShortCutToTeam " + dateName;
 * wait.until(ExpectedConditions.visibilityOf(findElement(driver, "id",
 * "shortCutLabel"))).sendKeys(shortcut); //
 * ExtentTestManager.getTest().log(Status.PASS,
 * "Entered the Label Name of shortcut"); Thread.sleep(1000);
 * findElement(driver, "id", "shortCutURL").sendKeys(shortCutURL);
 * //ExtentTestManager.getTest().log(Status.PASS,
 * "Entered the URL of the shortcut"); Thread.sleep(1000);
 * 
 * findElement(driver, "xpath", "saveShortCut").click(); //
 * ExtentTestManager.getTest().log(Status.PASS, "Clicked on Save Button");
 * Thread.sleep(1000);
 * 
 * findElement(driver, "xpath", "applyNavigation").click(); Thread.sleep(500);
 * findElement(driver, "id", "applyAlertConfirm").click(); Thread.sleep(6000);
 * 
 * FunctionLibrary.getURL("newsfeedTabURL"); Thread.sleep(2000);
 * clickElement(driver, driver.findElement(By.xpath("//i[@title=\"" + shortcut +
 * "\"]"))); Thread.sleep(1500); } catch (Exception e) {
 * //ExtentTestManager.getTest().log(Status.FAIL, "Error in try block " +
 * e.toString()); }
 * 
 * }
 * 
 * public void datePicker(WebDriver driver, String dateNumber) {
 * findElement(driver, "xpath", "nextMonth").click(); //
 * ExtentTestManager.getTest().log(Status.PASS,
 * "Clicked on next month option of calander");
 * 
 * String rowPath = "//*[@id=\"ui-datepicker-div\"]/div[2]/table/tbody/tr"; int
 * rowSize = findRowCountOfWebTable(driver, rowPath); int colSize =
 * findColCountOfWebTable(driver, rowPath); for (int i = 1; i <= rowSize; i++) {
 * for (int j = 1; j <= colSize; j++) { String dateValue =
 * "//*[@id='ui-datepicker-div']/div[2]/table/tbody/tr[" + i + "]/td[" + j +
 * "]"; String date = driver.findElement(By.xpath(dateValue)).getText(); if
 * (date.equalsIgnoreCase(dateNumber)) { WebElement actualDate =
 * driver.findElement(By.xpath(dateValue)); clickElement(driver, actualDate);
 * //ExtentTestManager.getTest().log(Status.PASS, //"Clicked on the date " +
 * dateNumber + " as given by user"); break; } } } }
 * 
 * }
 */