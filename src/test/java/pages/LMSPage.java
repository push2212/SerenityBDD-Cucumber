package pages;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;

import org.junit.Assert;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Steps;
import utilities.ReadWorkbook;
import utilities.ReportMessege;
import utilities.common;

public class LMSPage extends PageObject {

	public String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
	public boolean contentFlag = false;
	public boolean videoFlag = false;
	public boolean audioFlag = false;
	public boolean pptFlag = false;
	//public boolean quizFlag = false;
	public String contentTypes = ""; 



	public String autoITScriptPath="";

	@Steps(shared=true) 
	private ReadWorkbook readWorkbook;

	@Steps(shared=true) 
	private ReportMessege report;

	@Steps(shared=true)
	private common common;


	@FindBy(xpath=".//div/a[text()='Create New Course']")
	public WebElementFacade createNewCourseBtn;

	@FindBy(xpath=".//input[@id='course-name']")
	public WebElementFacade courseName;

	@FindBy(xpath=".//button[@class='actionbutton upload-cover-video left-0 top-20']")
	public WebElementFacade editVideo;


	@Step
	public void createEditCourse(String logicalName) throws IOException, InterruptedException {
		String DomainURL = Serenity.sessionVariableCalled("domainURL").toString();
		String filePath = "\\src\\test\\resources\\TestData\\TestData.xlsx";
		String dataSheet = "LMS";
		String coursename ="";

		Map <String,Map<Integer, String>> tableMap = ReadWorkbook.readRow(logicalName, filePath, dataSheet);		
		readWorkbook.testData(tableMap);
		for (int i = 0 ;i < tableMap.get("Row").size();i++) {		

			String SubURL = tableMap.get("SubURL").get(i);
			String createCourse = tableMap.get("createCourse").get(i);
			String category = tableMap.get("Category").get(i);
			String availableto= tableMap.get("availableTo").get(i);
			String setReset = tableMap.get("setReset").get(i);
			String traversalrule = tableMap.get("traversalRule").get(i);
			Serenity.setSessionVariable("traversalrule").to(traversalrule);
			String completionrule = tableMap.get("completionRule").get(i);	
			Serenity.setSessionVariable("completionrule").to(completionrule);
			String preRequisiteCourse = tableMap.get("preRequisiteCourse").get(i);

			if(!logicalName.equals("EditScormCourse")){
				getDriver().get(DomainURL+SubURL);
				common.WaitForObjectVisibility(".//span[@class='select2-selection select2-selection--single']");
				//(".//table[@id='course_table_list']");	
				report.Info("Navigated to Courses Page");		
			}

			if((createCourse.equalsIgnoreCase("Yes")) || (createCourse.equalsIgnoreCase("Edit"))){
				if(createCourse.equalsIgnoreCase("Yes")) {

					if(!logicalName.equals("EditScormCourse")){
						createNewCourseBtn.click();
					}
					common.WaitForObjectVisibility(".//input[@id='course-name']");
					coursename = "AC" +dateName;
					courseName.type(coursename);
					$(".//span[@class='cke_button_icon cke_button__numberedlist_icon']").click();

				}else if (createCourse.equalsIgnoreCase("Edit")) {
					String locateCol = "Name";
					String value = Serenity.sessionVariableCalled("coursename");					

					String table = ".//table[@id='course_table_list']";
					common.locateColumn(table, locateCol, value, "0");
					common.tableActions(table, "ClickCourseDots", "");
					Thread.sleep(300);
					WebElementFacade editCourseInfo=$(".//ul[@style='display: block;']/li/a[text()='Edit Course Info']");
					editCourseInfo.click();
					common.WaitForObjectVisibility(".//input[@id='course-name']");
					coursename = "ACE" +dateName;
					courseName.type(coursename);
					//$(".//span[@class='cke_button_icon cke_button__bulletedlist_icon']").click();

				}
				Serenity.setSessionVariable("coursename").to(coursename);
				if(!category.equals("")) {
					Serenity.setSessionVariable("category").to(category);
					setResetCourseVideoPhoto("","",setReset);
					WebElementFacade assignChangeCourseBtn = $(".//div/button[@id='assign-course-category']");
					common.waitForElement(assignChangeCourseBtn);
					common.clickElement(assignChangeCourseBtn);	
					common.WaitForObjectVisibility(".//label[text()='"+category+"']");
					WebElementFacade categoryCheckBox = $(".//label[text()='"+category+"']");
					Thread.sleep(500);
					categoryCheckBox.click();
					report.Info("Edited Category");
					WebElementFacade saveCategoryBtn = $(".//div/button[@id='save-assign-category']");
					saveCategoryBtn.click();
				}
				if(createCourse.equalsIgnoreCase("Yes")) {
					WebElementFacade courseCode = $(".//div/input[@name='course[course_code]']");
					courseCode.waitUntilEnabled();
					courseCode.type("C"+dateName);
					WebElementFacade skillsGained= $(".//ul/li/input[@id='token-input-course-skills']");
					skillsGained.sendKeys("Collaboration");
					Thread.sleep(2000);
					skillsGained.sendKeys(Keys.TAB);

					WebElementFacade instructor= $(".//ul/li/input[@id='token-input-course-instructor']");
					String courseinstructor = Serenity.sessionVariableCalled("instructor");
					instructor.sendKeys(courseinstructor);
					Thread.sleep(2500);
					instructor.sendKeys(Keys.TAB);
					Thread.sleep(500);
					WebElementFacade assignCertificate = $(".//div/button[@id='assign-certificates']");
					common.clickElement(assignCertificate);
					//assignCertificate.click();
					common.WaitForObjectVisibility(".//figcaption[@class='mt-preview-dialog--lhs-template-details']");
					//WebElementFacade certificateOfCompletion = $(".//figcaption[@class='mt-preview-dialog--lhs-template-details']/div[@title='Certificate of Completion']");
					WebElementFacade doneButton = $(".//div/a[@id='select-assign-certificate']");
					doneButton.click();
					common.WaitForObjectVisibility(".//h5[text()='[Course Name]']");
					report.Info("Certificate Assigned"); 
					common.scrollToPageEnd();		    
					//WebElementFacade validity =$(".//a[@class='selectBox mango-selectbox white-selectbox selectBox-dropdown']/span[text()='1 Month']");			
					//common.actionClick(validity);
					//	common.actionClick($(".//ul[@class='selectBox-dropdown-menu selectBox-options']/li/a[text()='No Expiry']"));
				}
			} if (createCourse.equals("ChangeVisibility")) {
				String table = ".//table[@id='course_table_list']";
				String locateCol = "Name";
				String value ="Automation Course";

				common.locateColumn(table, locateCol, value, "0");
				common.tableActions(table, "ClickCourseDots", "");
				Thread.sleep(300);
				WebElementFacade editCourseInfo=$(".//ul[@style='display: block;']/li/a[text()='Edit Course Info']");
				editCourseInfo.click();
				common.WaitForObjectVisibility(".//input[@id='course-name']");
			}

			WebElementFacade saveAndContinue = $(".//div/div/a[@id='submit_create_edit_course']");
			saveAndContinue.click();
			Thread.sleep(1000);
			common.WaitForObjectVisibility("(.//a[@class='selectBox mango-selectbox white-selectbox selectBox-dropdown'])[1]");
			if(!availableto.equals("")) {
				WebElementFacade availableTo= $("(.//a[@class='selectBox mango-selectbox white-selectbox selectBox-dropdown'])[1]");
				common.actionClick(availableTo);
				Thread.sleep(500);
				common.actionClick($(".//ul[@class='selectBox-dropdown-menu selectBox-options']/li/a[text()='"+availableto+"']"));
				report.Info("Visibility set to "+availableto);
				if(availableto.contains("Specific Team")) {
					WebElementFacade teamName = $(".//ul/li/input[@id='token-input-course-rule-auto']");
					teamName.sendKeys("MangoApps");
					Thread.sleep(2000);
					teamName.sendKeys(Keys.TAB);
				}else if(availableto.contains("Specific Users")) {
					$(".//ul[@class='data-list-items ma_inputBubbleList']/li/input").click();
					Thread.sleep(300);
					$(".//ul[@class='data-list-items ma_inputBubbleList']/li/input").sendKeys("First");
					Thread.sleep(200);
					$(".//ul[@class='ma_peopleSearchList ma_peopleSubList']/li[@data_label='First Name']").click();
					$(".//input[@placeholder='Enter First Name']").waitUntilVisible();
					String lmsUser = Serenity.sessionVariableCalled("lmsUser");
					$(".//input[@placeholder='Enter First Name']").sendKeys(lmsUser);
					Thread.sleep(200);
					$(".//button[text()='Apply']").click();
				}else if (availableto.contains("Specific Location")) {
					WebElementFacade teamName = $(".//ul/li/input[@id='token-input-course-rule-auto']");
					teamName.sendKeys("Seattle");
					Thread.sleep(2500);
					teamName.sendKeys(Keys.TAB);
				}
			}

			String value ="";
			if(!traversalrule.equals("")) {
				WebElementFacade traversalRule= $("(.//a[@class='selectBox mango-selectbox white-selectbox selectBox-dropdown'])[2]");
				common.actionClick(traversalRule);
				common.actionClick($(".//ul[@class='selectBox-dropdown-menu selectBox-options']/li/a[text()='"+traversalrule+"']"));
				WebElementFacade completionRule= $("(.//a[@class='selectBox mango-selectbox white-selectbox selectBox-dropdown'])[3]");
				common.actionClick(completionRule);
				common.actionClick($(".//ul[@class='selectBox-dropdown-menu selectBox-options']/li/a[text()='"+completionrule+"']"));
				if(completionrule.contains("percentage")){
					WebElementFacade selectPercentage= $("(.//a[@class='selectBox mango-selectbox white-selectbox selectBox-dropdown'])[4]");
					common.actionClick(selectPercentage);
					common.actionClick($(".//ul[@class='selectBox-dropdown-menu selectBox-options']/li/a[text()='50%']"));					
				}
			}
			if(preRequisiteCourse.equals("Yes")) {
				WebElementFacade PreRequisiteCourse = $(".//input[@id='token-input-prerequisit-courses']");
				PreRequisiteCourse.type("PreRequisite Course");
				Thread.sleep(2000);
				PreRequisiteCourse.sendKeys(Keys.TAB);
			}

			WebElementFacade saveAndExit = $(".//div/a[@id='submit_create_edit_course']");
			saveAndExit.click();
			common.WaitForObjectVisibility(".//table[@id='course_table_list']");

			if(createCourse.equals("Yes")) {
				String locateCol = "Name";
				value =  Serenity.sessionVariableCalled("coursename");
				//"Automation Course";		
				String table = ".//table[@id='course_table_list']";

				common.locateColumn(table, locateCol, value, "0");
				report.Info("Course Successfully created/edited");
			}

			if(logicalName.equals("CreateNewCourse")){
				getDriver().get(DomainURL+"learn/instructing_courses");
				common.WaitForObjectVisibility(".//span[text()='"+coursename+"']");
				$(".//span[text()='"+coursename+"']").shouldBeVisible();
				report.Info("Course visible to instructor");
			}
		}

	}

	@FindBy(xpath="(.//ul[@class='gallery-icons ']/li[@class='is-image ']/img)[1]")
	public WebElementFacade firstMedia;

	@FindBy(xpath=".//div[@class='mg-body-tabs']/div[text()='Media']")
	public WebElementFacade mediaTab;

	@FindBy(xpath=".//div[@class='mg-body-tabs']/div[text()='Upload']")
	public WebElementFacade uploadTab;

	@FindBy(xpath=".//div[@class='mg-footer']/button[text()='Ok']")
	public WebElementFacade okButton;

	@FindBy(xpath=".//a[text()='Select Files']")
	public WebElementFacade SelectFilesButton;

	@FindBy(xpath=".//button[@class='actionbutton upload-cover-image left-0 top-20']")
	public WebElementFacade editPhoto;


	@Step
	public void setResetCourseVideoPhoto(String mediaType ,String wayToAdd ,String setReset) throws IOException, InterruptedException {
		//if(mediaType.equals("Video")){
		editVideo.click();
		//if(wayToAdd.equals("Media")) {				
		WebElementFacade pickFromMediaGallary = $(".//div[@class='mteam-upload-link load-video-media-gallery'][text()='Pick from Media Gallery']");
		pickFromMediaGallary.click();
		common.waitForElement(firstMedia);
		mediaTab.click();
		firstMedia.click();
		common.WaitForObjectVisibility(".//div[text()='Details']");
		okButton.click();
		common.WaitForObjectVisibility(".//video[@data-video-filename='SampleVideo.mp4']");
		$(".//video[contains(@data-video-filename,'SampleVideo')]").shouldBeCurrentlyVisible();
		report.Info("Video added from media gallary");

		if(setReset.equalsIgnoreCase("Reset")) {
			Thread.sleep(500);
			editVideo.click();
			WebElementFacade resetVideo = $(".//div[@class='mteam-upload-link reset-course-cover-video'][contains(text(),'Reset Video')]");
			resetVideo.click();
			common.WaitForObjectPresence(".//div[@class='position-relative ms-hide']");
			$(".//div[@class='position-relative ms-hide']").shouldBePresent();
			report.Info("Reset Video successful");
			editVideo.click();
			pickFromMediaGallary.click();
			common.waitForElement(firstMedia);
			uploadTab.click();
			SelectFilesButton.click();
			autoITScriptPath = System.getProperty("user.dir")+"\\AutoIT\\UploadVideo.exe";
			Runtime.getRuntime().exec(autoITScriptPath);
			Thread.sleep(1500);
			common.WaitForObjectPresence(".//div[text()='Details']");	
			okButton.click();
			common.WaitForObjectVisibility(".//video[@data-video-filename='SampleVideo.mp4']");
			$(".//video[@data-video-filename='SampleVideo.mp4']").shouldBeCurrentlyVisible();				
			report.Info("Uploaded video to course");
		}

		editPhoto.click();
		WebElementFacade pickFromMediaGallaryPhoto = $(".//div[@class='mteam-upload-link load-icon-media-gallery'][text()='Pick from Media Gallery']");
		pickFromMediaGallaryPhoto.click();
		common.waitForElement(mediaTab);
		mediaTab.click();
		firstMedia.waitUntilClickable();
		firstMedia.click();
		common.WaitForObjectVisibility(".//div[text()='Details']");
		okButton.click();				
		WebElementFacade savePhotoBtn = $(".//span[@id='save-course-cover-icon']");
		Thread.sleep(1500);
		//	savePhotoBtn.click();
		common.WaitForObjectPresence(".//img[@id='course-cover-image-element'][contains(@src,'https')]");
		WebElementFacade imageAdded = $(".//img[@id='course-cover-image-element'][contains(@src,'https')]");
		imageAdded.shouldBePresent();
		report.Info("Photo added to course");

		if(setReset.equalsIgnoreCase("Reset")) {
			editPhoto.click();
			WebElementFacade resetPhoto = $(".//div[@class='mteam-upload-link reset-course-cover-image'][contains(text(),'Reset Image')]");
			resetPhoto.click();
			Thread.sleep(1500);
			//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>$(".//input[@name='course[img_properties]'][@value='/images/notes_placeholder_image.svg']").shouldBePresent();
			report.Info("Reset Image successful");
			editPhoto.click();
			pickFromMediaGallaryPhoto.click();
			uploadTab.click();
			SelectFilesButton.click();
			autoITScriptPath = System.getProperty("user.dir")+"\\AutoIT\\UploadDP.exe";
			Runtime.getRuntime().exec(autoITScriptPath);
			common.WaitForObjectVisibility(".//div[text()='Details']");	
			okButton.click();	
			// savePhotoBtn = $(".//span[@id='save-course-cover-icon']");
			Thread.sleep(1000);
			//savePhotoBtn.click();
			common.WaitForObjectVisibility(".//img[@id='course-cover-image-element'][contains(@src,'https')]");
			imageAdded.shouldBeCurrentlyVisible();
			report.Info("Upload Image successful");
			editPhoto.click();
			WebElementFacade uploadYourOwn = $(".//div[@class='mteam-upload-link upload-course-cover-photo-outside-option']");
			uploadYourOwn.click();
			//SelectFilesButton.click();
			autoITScriptPath = System.getProperty("user.dir")+"\\AutoIT\\UploadDP.exe";
			Runtime.getRuntime().exec(autoITScriptPath);
			common.WaitForObjectVisibility(".//div[text()='Details']");					
			savePhotoBtn.waitUntilClickable();
			savePhotoBtn.click();
			common.WaitForObjectVisibility(".//img[@id='course-cover-image-element'][contains(@src,'https')]");
			imageAdded.shouldBeCurrentlyVisible();
			//		savePhotoBtn.waitUntilNotVisible();
			Thread.sleep(2000);
			report.Info("Upload your own Image successful");
		}
	}

	public WebElementFacade chapterTitle = $(".//div/input[@id='chapter_title']");
	public WebElementFacade addChapterBtn = $(".//div/a[@id='add-chapter-button']");

	@Step
	public void createCourseContent(String logicalName) throws InterruptedException, IOException {
		String DomainURL = Serenity.sessionVariableCalled("domainURL").toString();
		getDriver().get(DomainURL+"admin/learn/courses");
		common.WaitForObjectVisibility(".//table[@id='course_table_list']");	

		String filePath = "\\src\\test\\resources\\TestData\\TestData.xlsx";
		String dataSheet = "LMS";
		//String coursename ="";
		Map <String,Map<Integer, String>> tableMap = ReadWorkbook.readRow(logicalName, filePath, dataSheet);		
		readWorkbook.testData(tableMap);
		for (int i = 0 ;i < tableMap.get("Row").size();i++) {		

			contentTypes = tableMap.get("contentTypes").get(i);
			String detailedContentChapter = tableMap.get("detailedContentChapter").get(i);			

			String locateCol = "Name";
			String value = Serenity.sessionVariableCalled("coursename");		
			String table = ".//table[@id='course_table_list']";

			common.locateColumn(table, locateCol, value, "0");
			common.tableActions(table, "ClickCourseDots", "");
			Thread.sleep(300);

			Serenity.setSessionVariable("contentTypes").to(contentTypes);

			WebElementFacade createCourseContent=$(".//ul[@style='display: block;']/li/a[text()='Create Course Content']");
			createCourseContent.click();
			common.WaitForObjectVisibility(".//div/a[@id='add-chapter-button']");		

			if(contentTypes.contains("Content")) {
				createContentChapter(logicalName,detailedContentChapter);
				contentFlag = true;				
			}			

			if(contentTypes.contains("Video")) {
				//common.WaitForObjectVisibility(".//div/a[@id='add-chapter-button']");
				$(".//div/a[@id='add-chapter-button']").waitUntilClickable();
				$(".//div/a[@id='add-chapter-button']").click();

				common.WaitForObjectVisibility(".//figcaption/div[@title='Video']");
				WebElementFacade videoChapter = $(".//figcaption/div[@title='Video']");
				videoChapter.click();
				$(".//div/button[@id='continue-add-chapter']").click();
				common.WaitForObjectVisibility(".//div/input[@id='chapter_title']");

				report.Info("Entered Video chapter title");

				WebElementFacade addVideoBtn = $(".//button[text()='+ Add Video']");
				addVideoBtn.click();
				common.clickElement(addVideoBtn);
				common.WaitForObjectVisibility("(.//ul[@class='gallery-icons ']/li[@class='is-image ']/img)[1]");
				firstMedia.click();
				common.WaitForObjectVisibility(".//div[text()='Details']");
				okButton.click();
				Thread.sleep(1500);
				report.Info("Add video successful");
				$(".//div/input[@id='chapter_title']").type("Video Chapter 1");
				//Thread.sleep(500);

				common.WaitForObjectVisibility(".//div[@class='mp-init']/span");
				Thread.sleep(1500);
				videoFlag = true;				
			}

			if(contentTypes.contains("Audio")) {
				$(".//div/a[@id='add-chapter-button']").click();

				common.WaitForObjectVisibility(".//figcaption/div[@title='Audio']");
				WebElementFacade AudioChapter = $(".//figcaption/div[@title='Audio']");
				AudioChapter.click();
				$(".//div/button[@id='continue-add-chapter']").click();
				common.WaitForObjectVisibility(".//div/input[@id='chapter_title']");

				//Thread.sleep(500);
				report.Info("Entered Audio chapter title");		
				WebElementFacade addAudioBtn = $(".//button[text()='+ Add Audio']");
				addAudioBtn.click();
				common.WaitForObjectVisibility("(.//ul[@class='gallery-icons ']/li[@class='is-image ']/img)[1]");
				firstMedia.click();
				common.WaitForObjectVisibility(".//div[text()='Details']");
				okButton.click();
				Thread.sleep(1000);
				report.Info("Add Audio successful");
				$(".//div/input[@id='chapter_title']").type("Audio Chapter 1");
				audioFlag = true;				
			}

			if(contentTypes.contains("PPT")) {
				$(".//div/a[@id='add-chapter-button']").waitUntilClickable();
				$(".//div/a[@id='add-chapter-button']").click();

				common.WaitForObjectVisibility(".//figcaption/div[contains(@title,'Presentation/')]");
				WebElementFacade pptChapter = $(".//figcaption/div[contains(@title,'Presentation/')]");
				pptChapter.click();
				$(".//div/button[@id='continue-add-chapter']").click();
				common.WaitForObjectVisibility(".//div/input[@id='chapter_title']");

				Thread.sleep(500);
				report.Info("Entered PPT chapter title");

				WebElementFacade addPPTBtn = $(".//button[text()='+ Add Powerpoint or PDF']");
				addPPTBtn.click();
				common.WaitForObjectVisibility("(.//ul[@class='gallery-icons ']/li[@class='is-image ']/img)[1]");
				firstMedia.click();
				common.WaitForObjectVisibility(".//div[text()='Details']");
				okButton.click();
				Thread.sleep(1000);
				report.Info("Add PPT/PDF successful");
				$(".//div/input[@id='chapter_title']").type("PPT Chapter 1");
				pptFlag = true;				
			}

			if(contentTypes.contains("Quiz")) {
				$(".//div/a[@id='add-chapter-button']").waitUntilClickable();
				$(".//div/a[@id='add-chapter-button']").click();

				common.WaitForObjectVisibility(".//figcaption/div[@title='Quiz']");
				WebElementFacade quizChapter = $(".//figcaption/div[@title='Quiz']");
				quizChapter.click();
				$(".//div/button[@id='continue-add-chapter']").click();
				common.WaitForObjectVisibility(".//div/input[@id='chapter_title']");	
				Thread.sleep(2000);
				$(".//div/input[@id='chapter_title']").sendKeys("Quiz Chapter 1");
				report.Info("Entered Quiz chapter title");
				WebElementFacade createQuizBtn = $("//*[@id='new-chapter']/div/a[contains(text(),'Create Quiz')]");
				createQuizBtn.click();
				Thread.sleep(2000);

				//action.sendKeys(Keys.ENTER);
				//		getDriver().switchTo().alert().accept();
				Thread.sleep(1000);
				common.WaitForObjectVisibility(".//ul/li[@class='module-list-item module_tracker_sec ']/span/a[@data-tracker_id='tt_1']");
				WebElementFacade SelectQuizTemplate = $(".//ul/li[@class='module-list-item module_tracker_sec ']/span/a[@data-tracker_id='tt_1']");
				common.clickElement(SelectQuizTemplate);
				// $(".//ul/li[@class='module-list-item module_tracker_sec ']/span/a[@data-tracker_id='tt_1']").click();
				Thread.sleep(200);
				//	WebElementFacade continueButton = $("//div[@class='nextbtn button_tracker_save ma-h2']/a");
				$("//div[@class='nextbtn button_tracker_save ma-h2']/a").click();
				common.WaitForObjectVisibility("(.//li[@class='drag_option_form quiz module_draggable moveable '])[1]");		
				//WebElementFacade savecontinueButton = $(".//div[@class='nextbtn button_tracker_save  ma-h2']/a");
				$(".//div[@class='nextbtn button_tracker_save  ma-h2']/a").click();
				common.WaitForObjectVisibility(".//label[@for='quiz_setting_passing_criteria']");
				$(".//div[@class='nextbtn button_tracker_save  ma-h2']/a").click();
				common.WaitForObjectVisibility(".//*[@id='delete-chapter-quiz']");
				//Thread.sleep(1000);
				report.Info("Add Quiz Chapter successful");		
				String quizFlag = "true";
				Serenity.setSessionVariable("quizFlag").to(quizFlag);
			}

			Actions action = new Actions(getDriver()); 
			WebElementFacade savePublishCourse =$(".//a[@class='actionbutton waves-effect waves-dark actionblue cut-dropdown']");
			common.mouseHover(savePublishCourse);
			WebElementFacade saveAndPublishCourse = $(".//a[text()='Save & Publish Course']");
			//saveAndPublishCourse.click();
			common.clickElement(saveAndPublishCourse);
			action = new Actions(getDriver()); 
			action.sendKeys(Keys.ENTER);		

			locateCol = "Name,Status";
			String courseName = Serenity.sessionVariableCalled("coursename");
			value = ""+courseName+",ACTIVE";
			getDriver().get(DomainURL+"admin/learn/courses");
			common.WaitForObjectVisibility(".//table[@id='course_table_list']");

			common.locateColumn(table, locateCol, value, "0");
			report.Info("Course content added successfully");
		}
	}

	@Step
	public void createContentChapter(String logicalName ,String detailedChapter) throws InterruptedException {
		common.WaitForObjectVisibility(".//div/a[@id='add-chapter-button']");
		$(".//div/a[@id='add-chapter-button']").click();
		//		common.clickElement($(".//div/a[@id='add-chapter-button']"));
		common.WaitForObjectVisibility(".//figcaption/div[@title='Content']");
		WebElementFacade contentChapter = $(".//figcaption/div[@title='Content']");
		contentChapter.click();
		Thread.sleep(100);
		$(".//div/button[@id='continue-add-chapter']").click();

		common.WaitForObjectVisibility(".//div/a[@id='select-lms-template-button']");
		WebElementFacade useThisTemplate = $(".//div/a[@id='select-lms-template-button']");
		Thread.sleep(2000);
		useThisTemplate.click();

		common.WaitForObjectVisibility(".//div/input[@id='chapter_title']");

		$(".//div/input[@id='chapter_title']").type("Content Chapter 1");
		report.Info("Entered chapter title");
		Thread.sleep(200);
		if (detailedChapter.equals("Yes")) {
			WebElementFacade cover = $(".//div[@class='ma-custom-nav-rhs ma_Template_Nav']/a[text()='Cover']");
			cover.click();
			common.WaitForObjectVisibility(".//div[@class='ps-column ps-column-view ps-outline-hover ps_column_cover  ps-6-column']");
			WebElementFacade replaceImage = $(".//div[@class='ps-column ps-column-view ps-outline-hover ps_column_cover  ps-6-column']/div/div/div/div[text()='Replace Image']");
			common.mouseHover($(".//div[@class='ps-column ps-column-view ps-outline-hover ps_column_cover  ps-6-column']"));
			common.clickElement(replaceImage);
			//replaceImage.click();
			common.WaitForObjectVisibility("(.//ul[@class='gallery-icons ']/li[@class='is-image ']/img)[1]");
			firstMedia.click();
			common.WaitForObjectVisibility(".//div[text()='Details']");
			okButton.click();
			Thread.sleep(1000);
			report.Info("Add Cover successful");

			WebElementFacade Image = $(".//div[@class='ma-custom-nav-rhs ma_Template_Nav']/a[text()='Image']");
			Image.click();
			common.WaitForObjectVisibility(".//div[@class='ps-column ps-column-view ps-outline-hover ps_column_image  ps-6-column']");
			replaceImage = $(".//div[@class='ps-column ps-column-view ps-outline-hover ps_column_image  ps-6-column']/div/div[text()='Replace Image']");
			common.mouseHover($(".//div[@class='ps-column ps-column-view ps-outline-hover ps_column_image  ps-6-column']"));
			//replaceImage.click();
			common.clickElement(replaceImage);
			common.WaitForObjectVisibility("(.//ul[@class='gallery-icons ']/li[@class='is-image ']/img)[1]");
			firstMedia.click();
			common.WaitForObjectVisibility(".//div[text()='Details']");
			okButton.click();
			Thread.sleep(1000);
			report.Info("Add Image successful");

			WebElementFacade Video = $(".//div[@class='ma-custom-nav-rhs ma_Template_Nav']/a[text()='Video']");
			Video.click();
			common.WaitForObjectVisibility(".//div[@class='ps-column ps-column-view ps-outline-hover ps_column_video  ps-6-column']");
			WebElementFacade replaceVideo = $(".//div[@class='ps-column ps-column-view ps-outline-hover ps_column_video  ps-6-column']/div/div[text()='Replace Video']");
			common.mouseHover($(".//div[@class='ps-column ps-column-view ps-outline-hover ps_column_video  ps-6-column']"));
			//replaceVideo.click();
			common.clickElement(replaceVideo);
			common.WaitForObjectVisibility("(.//ul[@class='gallery-icons ']/li[@class='is-image ']/img)[1]");
			firstMedia.click();
			common.WaitForObjectVisibility(".//div[text()='Details']");
			okButton.click();
			Thread.sleep(1000);
			report.Info("Add Video successful");

			WebElementFacade Audio = $(".//div[@class='ma-custom-nav-rhs ma_Template_Nav']/a[text()='Audio']");
			Audio.click();
			common.WaitForObjectVisibility(".//div[@class='ps-column ps-column-view ps-outline-hover ps_column_audio  ps-6-column']");
			WebElementFacade replaceAudio = $(".//div[@class='ps-column ps-column-view ps-outline-hover ps_column_audio  ps-6-column']/div/div[text()='Replace Audio']");
			common.mouseHover($(".//div[@class='ps-column ps-column-view ps-outline-hover ps_column_audio  ps-6-column']"));
			//replaceAudio.click();
			common.clickElement(replaceAudio);
			common.WaitForObjectVisibility("(.//ul[@class='gallery-icons ']/li[@class='is-image ']/img)[1]");
			firstMedia.click();
			common.WaitForObjectVisibility(".//div[text()='Details']");
			okButton.click();
			Thread.sleep(1000);
			report.Info("Add Audio successful");

			WebElementFacade Quote = $(".//div[@class='ma-custom-nav-rhs ma_Template_Nav']/a[text()='Quote']");
			Quote.click();
			common.WaitForObjectVisibility(".//div[@class='ps-column ps-column-view ps-outline-hover ps_column_quote  ps-6-column']/div/div/span[@title='Click to edit']");
			WebElementFacade qupteText = $(".//div[@class='ps-column ps-column-view ps-outline-hover ps_column_quote  ps-6-column']/div/div/span[@title='Click to edit']");
			qupteText.click();
			common.enterText(qupteText, "Automation Quote");

			Thread.sleep(200);
			WebElementFacade Text = $(".//div[@class='ma-custom-nav-rhs ma_Template_Nav']/a[text()='Text']");
			Text.click();
			common.WaitForObjectVisibility(".//div[@class='ps-column ps-column-view ps-outline-hover ps_column_paragraph  ps-6-column']/div/h2/div[text()='Heading']");
			report.Info("Add Text successful");

			WebElementFacade ImagePlusText = $(".//div[@class='ma-custom-nav-rhs ma_Template_Nav']/a[text()='Image + Text']");
			ImagePlusText.click();
			common.WaitForObjectVisibility(".//div[@class='ps-image-top ps-column-has-img-text']");
			replaceImage = $(".//div[@class='ps-image-top ps-column-has-img-text']/div/div[text()='Replace Image']");
			common.mouseHover($(".//div[@class='ps-image-top ps-column-has-img-text']"));
			//replaceImage.click(); 
			common.clickElement(replaceImage);
			common.WaitForObjectVisibility("(.//ul[@class='gallery-icons ']/li[@class='is-image ']/img)[1]");
			firstMedia.click();
			common.WaitForObjectVisibility(".//div[text()='Details']");
			okButton.click();
			Thread.sleep(1000);
			report.Info("Add Image plus Text successful");

			WebElementFacade Button = $(".//div[@class='ma-custom-nav-rhs ma_Template_Nav']/a[text()='Button']");
			Button.click();
			common.WaitForObjectVisibility(".//div[@class='ps-column ps-column-view ps-outline-hover ps_column_button  ps-6-column']/div/a/span");
			WebElementFacade addedButton = $(".//div[@class='ps-column ps-column-view ps-outline-hover ps_column_button  ps-6-column']/div/a/span");
			addedButton.click();
			common.enterText(addedButton,"Automation Button");
			report.Info("Add Button successful");
		}
	}

	@Step
	public void archiveUnArchiveCourse(String logicalName) throws InterruptedException {
		String DomainURL = Serenity.sessionVariableCalled("domainURL").toString();
		String table = ".//table[@id='course_table_list']";
		String locateCol = "Name,Status";
		String courseName =Serenity.sessionVariableCalled("coursename");
		String value = ""+courseName+",ACTIVE";
		getDriver().get(DomainURL+"admin/learn/courses");
		common.WaitForObjectVisibility(".//table[@id='course_table_list']");

		common.locateColumn(table, locateCol, value, "0");		
		common.tableActions(table, "ClickCourseDots", "");
		Thread.sleep(300);
		WebElementFacade archiveCourse=$(".//ul[@style='display: block;']/li/a[text()='Archive']");
		archiveCourse.click();
		report.Info("Clicked Archive option");
		common.WaitForObjectVisibility(".//button[text()='Yes, Archive']");
		WebElementFacade yesArchiveBtn = $(".//button[text()='Yes, Archive']");
		yesArchiveBtn.click();
		Thread.sleep(3500);
		locateCol = "Name,Status";		
		value = ""+courseName+",ARCHIVED";	

		common.locateColumn(table, locateCol, value, "0");
		report.Info("Course Archived");
		//common.locateColumn(table, locateCol, value, "0");		
		common.tableActions(table, "ClickCourseDots", "");
		WebElementFacade unArchiveCourse=$(".//ul[@style='display: block;']/li/a[text()='UnArchive']");
		unArchiveCourse.click();
		WebElementFacade yesUnArchiveBtn = $(".//button[text()='Yes, UnArchive']");
		yesUnArchiveBtn.click();
		Thread.sleep(3000);
		locateCol = "Name,Status";		
		value = ""+courseName+",ACTIVE";

		common.locateColumn(table, locateCol, value, "0");
		report.Info("Course UnArchived");
	}


	@Step
	public void deleteRestoreFromTrash(String logicalName) throws InterruptedException {
		String DomainURL = Serenity.sessionVariableCalled("domainURL").toString();
		String table = ".//table[@id='course_table_list']";
		String locateCol = "Name,Status";
		String courseName ="Automation Course";
		//Serenity.sessionVariableCalled("coursename");
		String value = ""+courseName+",ACTIVE";
		getDriver().get(DomainURL+"admin/learn/courses");
		common.WaitForObjectVisibility(".//table[@id='course_table_list']");

		common.locateColumn(table, locateCol, value, "0");		
		common.tableActions(table, "ClickCourseDots", "");
		Thread.sleep(300);
		WebElementFacade deleteCourse=$(".//ul[@style='display: block;']/li/a[text()='Delete']");
		deleteCourse.click();
		common.WaitForObjectVisibility(".//button[@id='delete-course-permanently']");
		$(".//button[@id='delete-course-permanently']").click();
		$(".//button[@id='delete-course-permanently']").click();
		report.Info("Course Deleted");
		Thread.sleep(2500);
		getDriver().get(DomainURL+"ce/pulse/admin/trash");
		common.WaitForObjectPresence(".//table[@class='DAdmin_table']/tbody/tr[1]/td[1]");
		WebElementFacade trashSearch = $(".//div/input[@id='search_box']");
		trashSearch.type(courseName);
		Thread.sleep(5000);
		common.WaitForObjectVisibility(".//td[@title='"+courseName+"']");
		WebElementFacade courseTitle = $(".//td[@title='"+courseName+"']");
		common.mouseHover(courseTitle);
		//WebElementFacade restoreButton=$(".//div/a[@title='Restore']");
		common.clickElement($(".//div/a[@title='Restore']"));
		Thread.sleep(2500);
		getDriver().get(DomainURL+"admin/learn/courses");
		common.WaitForObjectVisibility(".//table[@id='course_table_list']");

		common.locateColumn(table, locateCol, value, "0");
		report.Info("Course restored");
	}

	@FindBy(xpath=".//a[@title='MangoApps Automation Team -- Mango Automation Internal Testing']")
	public WebElementFacade AutomationTestProject;

	@Step
	public void takeCourseActions(String logicalName) throws IOException, InterruptedException {
		String DomainURL = Serenity.sessionVariableCalled("domainURL").toString();
		String filePath = "\\src\\test\\resources\\TestData\\TestData.xlsx";
		String dataSheet = "LMS";		
		Map <String,Map<Integer, String>> tableMap = ReadWorkbook.readRow(logicalName, filePath, dataSheet);		
		readWorkbook.testData(tableMap);
		for (int i = 0  ;i < tableMap.get("Row").size();i++) {				

			String category =Serenity.sessionVariableCalled("category");
			//String traversalrule =Serenity.sessionVariableCalled("traversalrule");
			String completionrule =Serenity.sessionVariableCalled("completionrule");
			String SubURL = tableMap.get("SubURL").get(i);
			getDriver().get(DomainURL+SubURL);
			//String createCourse = tableMap.get("createCourse").get(i);
			if(!logicalName.equals("TakeCourseGuest")) {
				common.WaitForObjectVisibility(".//span[text()='"+category+"']");
				WebElementFacade courseCategory = $(".//span[text()='"+category+"']");
				courseCategory.click();
				report.Info("Clicked Category");
			}else {
				common.WaitForObjectVisibility(".//a[@title='MangoApps Automation Team -- Mango Automation Internal Testing']");
				AutomationTestProject.click();
				Thread.sleep(2000);
				getDriver().navigate().refresh();
				//Thread.sleep(2000);
			}

			String courseToTake =Serenity.sessionVariableCalled("coursename");
			report.Info("Course to take:-"+courseToTake);
			common.WaitForObjectVisibility(".//span[text()='"+courseToTake+"']");
			//WebElementFacade course = $(".//span[text()='"+courseToTake+"']");
			common.clickElement($(".//span[text()='"+courseToTake+"']"));
			common.WaitForObjectVisibility(".//div[@class='mp-init']/span");
			WebElementFacade startCourse = $(".//div[@id='course_action_container']/a");
			startCourse.click();

			if(logicalName.equals("CheckPreRequisitecourse")) {
				common.WaitForObjectVisibility(".//h3[contains(text(),'Pre-requisite Course Not Completed')]");
				$(".//a[text()='PreRequisite Course']").shouldBeVisible();
				report.Info("Prerequisite course condition is working");
				break;
			}

			contentTypes = Serenity.sessionVariableCalled("contentTypes");

			String arr[] = contentTypes.split(Pattern.quote(","));
			int size = arr.length;

			for(int j=0;j<size;j++) {
				common.WaitForObjectVisibility(".//a[@id='mark-as-complete-chapter']");
				Thread.sleep(2000);

				if(logicalName.equals("TakeCourseSequential")) {
					$(".//span[@title='You can move to the next chapter only after completing the chapter']").shouldBeVisible();
					report.Info("Sequencial completion working");
					break;
				}else if(logicalName.equals("TakeCourseAnyOrder")) {
					$(".//span[@title='You can move to the next chapter only after completing the chapter']").shouldNotBeVisible();
					report.Info("Non Sequencial completion working");
					//break;
				}
				//(!Serenity.sessionVariableCalled("quizFlag").equals(null))&&
				if((arr[j].contains("Quiz"))) {
					Serenity.setSessionVariable("quizFlag").to("false");
					report.Info("Attempting quiz");
					common.WaitForObjectVisibility(".//a[@id='quiz-status-button']");
					WebElementFacade takeQuizButton = $(".//a[@id='quiz-status-button']");
					common.clickElement(takeQuizButton);
					common.clickElement(takeQuizButton);
					common.WaitForObjectVisibility(".//button[@class='actionbutton actionblue spa-btn-ls']");
					WebElementFacade startQuizNow = $(".//button[@class='actionbutton actionblue spa-btn-ls']");
					startQuizNow.click();
					common.WaitForObjectVisibility(".//label[@for='Answer Choice 2']");
					$(".//label[@for='Answer Choice 2']").click();
					$(".//button[@disabled]").waitUntilNotVisible();
					$(".//button[@class='actionbutton actionblue spa-btn left-10']").click();

					Thread.sleep(1500);
					common.clickElement($(".//label[@for='Answer Choice 2']"));
					$(".//button[@disabled]").waitUntilNotVisible();
					common.clickElement($(".//button[@class='actionbutton actionblue spa-btn left-10']"));

					common.WaitForObjectVisibility(".//button[@class='actionbutton actionblue spa-btn-ls']");
					$(".//button[@class='actionbutton actionblue spa-btn-ls']").click();
					common.WaitForObjectVisibility(".//a[@id='quiz-status-button']");
					//$(".//a[@id='mark-as-complete-chapter']").click();

				}

				Thread.sleep(500);
				//WebElementFacade markComplete = $(".//a[@id='mark-as-complete-chapter']");
				$(".//a[@id='mark-as-complete-chapter']").click();
				report.Info(arr[j]+ " Chapter marked completed");
				Thread.sleep(1500);

				List<WebElementFacade> submitReView = findAll(By.xpath(".//button[@id='set_ratings_btn']"));
				/*
				 * if(submitReView.size()>0) {
				 * common.WaitForObjectVisibility(".//button[@id='set_ratings_btn']");
				 * $(".//label[@aria-label='3 stars']").click(); Thread.sleep(200);
				 * $(".//textarea[@id='rating-content']").type("The course is good");
				 * WebElementFacade submitRatingBtn = $(".//button[@id='set_ratings_btn']");
				 * submitRatingBtn.click(); common.
				 * WaitForObjectVisibility(".//div[@id='notice'][contains(text(),'Your response has been submitted successfully.')]"
				 * ); }
				 */
				List<WebElementFacade> nextDisabled = findAll(By.xpath(".//span[@title='You can move to the next chapter only after completing the chapter']"));
				int nextsize = nextDisabled.size();
				if(((size > 1)) &&((nextsize==0)&&(submitReView.size()==0))){
					common.WaitForObjectVisibility(".//a[@id='mark-as-complete-chapter'][@data-chapter-completed='true']");
					$(".//a[@id='proceed-to-next-chapter']").click();
				}else if(((size==2)) &&(completionrule.contains("percentage"))&&(j==0)){
					common.WaitForObjectVisibility(".//button[@id='set_ratings_btn']");
					$(".//label[@aria-label='3 stars']").shouldBeVisible();
					report.Info("Course review can be done after 50% completion");
					$(".//label[@aria-label='3 stars']").click();
					Thread.sleep(200);
					$(".//textarea[@id='rating-content']").type("The course is good");
					WebElementFacade submitRatingBtn = $(".//button[@id='set_ratings_btn']");
					submitRatingBtn.click();
				}

			}

			if(logicalName.equals("TakeCourseSequential")) {
				break;
			}

			if(!completionrule.contains("percentage")){
				common.WaitForObjectVisibility(".//button[@id='set_ratings_btn']");
				$(".//label[@aria-label='3 stars']").click();
				Thread.sleep(200);
				$(".//textarea[@id='rating-content']").type("The course is good");
				WebElementFacade submitRatingBtn = $(".//button[@id='set_ratings_btn']");
				submitRatingBtn.click();
				common.WaitForObjectVisibility(".//div[@id='notice'][contains(text(),'Your response has been submitted successfully.')]");
			}

			if(logicalName.equals("TakeCourse")) {
				getDriver().get(DomainURL+"learn/my_learning");
				common.WaitForObjectVisibility("//div[@class='d-flex flex-column justify-content-between course-description-wrapper non-ongoing-course-listing']");
				common.scrollToPageEnd();
				Thread.sleep(1500);

				$(".//span[text()='"+courseToTake+"']").shouldBeVisible();
				$(".//span[text()='"+courseToTake+"']/following::div[contains(text(),'Completed On:')]").shouldBeVisible();
				report.Info("Course completed and rating submitted");
				$(".//span[text()='"+courseToTake+"']").click();

				WebElementFacade reviewLink = $(".//a[contains(text(),'Review')]");
				common.waitForElement(reviewLink);
				reviewLink.click();
				Thread.sleep(200);
				$(".//p[text()='The course is good']").shouldBePresent();
				report.Info("Rating and given review are present");
				WebElementFacade questionInputBox = $(".//input[@id='quest_message_comment_show']");
				questionInputBox.type("What is the course cost?");
				WebElementFacade askButton = $(".//button[text()='Ask']");
				askButton.click();
				common.WaitForObjectVisibility(".//p[text()='What is the course cost?']");
				$(".//p[text()='What is the course cost?']").shouldBeVisible();
				report.Info("Asked question is visible");
				Thread.sleep(300);
				WebElementFacade addAnswerBtn = $(".//a[text()='Add Answer']");
				common.clickElement(addAnswerBtn);
				Thread.sleep(300);
				$(".//input[@placeholder='Write an answer...']").type("$45");
				$(".//a[text()='Add Answer']").click();
				$(".//p[contains(text(),'What is the course cost?')]").shouldBeVisible();
				report.Info("Added answer is visible");
			} /*
			 * else { WebElementFacade courseCloseButton =
			 * $(".//a[@class='close-course-reading']"); courseCloseButton.click(); }
			 */					
		}
	}

	@Step
	public void assignRemoveCourseUser(String logicalName) throws InterruptedException {
		String DomainURL = Serenity.sessionVariableCalled("domainURL").toString();
		String coursename =Serenity.sessionVariableCalled("coursename");
		getDriver().get(DomainURL+"admin/learn/courses");
		Thread.sleep(2000);
		report.Info("Navigated to courses");
		String locateCol = "Name";
		String value = coursename;

		String table = ".//table[@id='course_table_list']";
		common.locateColumn(table, locateCol, value, "0");
		common.tableActions(table, "ClickCourseDots", "");
		Thread.sleep(300);
		WebElementFacade ViewManageUsers=$(".//ul[@style='display: block;']/li/a[text()='View & Manage Users']");
		ViewManageUsers.click();
		common.WaitForObjectVisibility(".//select[@name='course_user_table_length']");
		Thread.sleep(1000);
		String domain = Serenity.sessionVariableCalled("domain");
		locateCol = "User Name";
		value = 	Serenity.sessionVariableCalled("lmsUser");		
		table = ".//table[@id='course_user_table']";
		boolean flag = false;
		if (common.locateColumn(table, locateCol, value, "0").equals("true")) {
			report.Info("User assigned to course");
			common.tableActions(table, "CheckBox", "");
			WebElementFacade RemoveUserBtn = $(".//div[@class='act-btn-lft']/a[text()='Remove User']");
			RemoveUserBtn.waitUntilClickable();
			RemoveUserBtn.click();
			common.WaitForObjectVisibility(".//span/button[text()='Remove Selected Users']");
			WebElementFacade RemoveSelectedUsers = $(".//span/button[text()='Remove Selected Users']");
			RemoveSelectedUsers.click();
			Thread.sleep(2500);
			report.Info("User removed");
			flag = true;
		}

		WebElementFacade assignUsers = $(".//*[@id='invite_colleagues']");
		common.clickElement(assignUsers);
		common.WaitForObjectVisibility(".//input[@class='data-search-input ma_data_search_input']");
		WebElementFacade searchUsers = find(By.cssSelector(".ma_data_search_input"));
		searchUsers.click();
		searchUsers.sendKeys("First Name");
		Thread.sleep(300);
		$(".//ul[@class='ma_peopleSearchList ma_peopleSubList']/li[@data_label='First Name']").click();
		$(".//input[@placeholder='Enter First Name']").waitUntilVisible();
		String lmsUser= Serenity.sessionVariableCalled("lmsUser");
		$(".//input[@placeholder='Enter First Name']").sendKeys(lmsUser);
		Thread.sleep(600);
		$(".//button[text()='Apply']").click();
		WebElementFacade searchedUser = find(By.xpath(".//ul[@id='searchedUserList']/li/span[1]"));
		common.waitForElement(searchedUser);
		Thread.sleep(600);
		common.clickElement($(".//ul[@id='searchedUserList']/li/span[1]"));
		Thread.sleep(500);
		WebElementFacade addSelectedUser = find(By.xpath(".//button[@id='addSelectedItem']"));
		common.waitForElement(addSelectedUser);
		$(".//button[@id='addSelectedItem']").click();
		Thread.sleep(500);
		$(".//div[@class='dialog_actions align-right top-20 learn-footer']/a[text()='Next']").waitUntilClickable();
		$(".//div[@class='dialog_actions align-right top-20 learn-footer']/a[text()='Next']").click();
		Thread.sleep(500);
		WebElementFacade addUsersBtn = $(".//div[@class='dialog_actions align-right top-20 learn-footer']/a[text()='Add Users']");
		addUsersBtn.waitUntilClickable();
		addUsersBtn.click();
		$(".//table[@id='course_user_table']").waitUntilClickable();
		Thread.sleep(2000);
		report.Info("User added");

		if (flag==false) {
			locateCol = "User Name";
			value = Serenity.sessionVariableCalled("lmsUser");
			table = ".//table[@id='course_user_table']";
			common.locateColumn(table, locateCol, value, "0");
			report.Info("User assigned to course");
			common.tableActions(table, "CheckBox", "");
			WebElementFacade RemoveUserBtn = $(".//div[@class='act-btn-lft']/a[text()='Remove User']");
			RemoveUserBtn.waitUntilClickable();
			RemoveUserBtn.click();
			common.WaitForObjectVisibility(".//span/button[text()='Remove Selected Users']");
			WebElementFacade RemoveSelectedUsers = $(".//span/button[text()='Remove Selected Users']");
			RemoveSelectedUsers.click();
			Thread.sleep(2500);
			report.Info("User removed");
		}
	}

	@Step
	public void createCourseCertificate(String logicalName) throws InterruptedException, IOException {
		String DomainURL = Serenity.sessionVariableCalled("domainURL").toString();
		getDriver().get(DomainURL+"admin/learn/certificate_templates");
		common.WaitForObjectVisibility(".//div/a[text()='Create Certificate']");
		WebElementFacade createCertificate = $(".//div/a[text()='Create Certificate']");
		createCertificate.click();
		WebElementFacade certificateName = $(".//input[@id='cert_template_name']");
		String CertificateName = "Certificate"+dateName;
		certificateName.type(CertificateName);
		//$(".//img[@alt='Certificate of Participation'][contains(@src,'BG6_')]").click();

		String certificateCode = "\r\n" + 
				"<div style=\"width:750px; height:550px; padding:20px; text-align:center; border: 5px solid #787878\">\r\n" + 
				"       <span style=\"font-size:50px; font-weight:bold\">Certificate of Completion</span>\r\n" + 
				"       <br><br>\r\n" + 
				"       <span style=\"font-size:25px\"><i>This is to certify that</i></span>\r\n" + 
				"       <br><br>\r\n" + 
				"       <span style=\"font-size:30px\"><b>[Participant Name]</b></span><br/><br/>\r\n" + 
				"       <span style=\"font-size:25px\"><i>has completed the course</i></span> <br/><br/>\r\n" + 
				"       <span style=\"font-size:30px\">[Course Name]</span> <br/><br/>\r\n" + 
				"       <span style=\"font-size:25px\"><i>dated</i></span><br>\r\n" + 
				"      [Completion Date]      \r\n" + 
				"</div>";

		String FilePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\Automation Certificate.png";
		upload(FilePath).to($(".//input[@id='upload-course-certificate-input']"));
		common.WaitForObjectVisibility(".//a[@id='save-custom-certificate-background']");
		WebElementFacade savePhoto = $(".//a[@id='save-custom-certificate-background']");
		savePhoto.click();
		savePhoto.click();
		Thread.sleep(1500);
		WebElementFacade saveAndContinue = $(".//div/div/a[@id='submit-certificate']");
		common.clickElement(saveAndContinue);
		common.clickElement(saveAndContinue);
		//saveAndContinue.click();
		//saveAndContinue.click();
		//autoITScriptPath = System.getProperty("user.dir")+"\\AutoIT\\UploadCertificate.exe";
		//Runtime.getRuntime().exec(autoITScriptPath);
		$("//*[@id='contentCol']/div/div/ul/li[@class='sort-it']").click();
		WebElementFacade saveAndPublish = $(".//ul/li[@id='save-add-publish-certificate']");
		saveAndPublish.click();

		common.WaitForObjectVisibility(".//h4[@title='"+CertificateName+"']");
		WebElementFacade createdCertificate = $(".//h4[@title='"+CertificateName+"']");
		createdCertificate.shouldBeCurrentlyVisible();
		report.Info("Successfully created certificate");
	}

	@Step
	public void createCourseCategory(String logicalName) throws InterruptedException, IOException {
		String DomainURL = Serenity.sessionVariableCalled("domainURL").toString();
		getDriver().get(DomainURL+"admin/learn/course_categories");
		common.WaitForObjectVisibility(".//button[@id='add-new-course-category']");
		WebElementFacade addNewCategory = $(".//button[@id='add-new-course-category']");
		addNewCategory.click();

		common.WaitForObjectVisibility(".//input[@id='course-category-name']");
		WebElementFacade categoryName = $(".//input[@id='course-category-name']");
		String CategoryName = "Automation Category"+dateName;
		categoryName.type(CategoryName);
		$(".//img[@id='course-category-symbol-cover-image']").click();
		$(".//div[contains(text(),'Pick from Media Gallery')]").click();
		common.WaitForObjectVisibility(".//ul/li/i[@class=' fab fa-accusoft']");
		$(".//ul/li/i[@class=' fab fa-accusoft']").click();
		common.WaitForObjectVisibility(".//div[text()='Details']");
		okButton.click();
		common.WaitForObjectVisibility(".//span[@id='save-course-cover-icon']");
		$(".//span[@id='save-course-cover-icon']").click();
		WebElementFacade saveCategoryBtn = $(".//a[@id='save-course-category']");
		saveCategoryBtn.waitUntilClickable();
		saveCategoryBtn.click();
		common.scrollToPageEnd();
		Thread.sleep(200);
		WebElementFacade createdCategory = $(".//span[text()='"+CategoryName+"']");
		createdCategory.shouldBePresent();
		report.Info("New category created");
	}

	@Step
	public void courseSettings(String logicalName) throws InterruptedException {
		String DomainURL = Serenity.sessionVariableCalled("domainURL").toString();
		getDriver().get(DomainURL+"admin/learn/settings");
		common.WaitForObjectVisibility(".//input[@name='label[learn_label]']");
		WebElementFacade moduleLabel = $(".//input[@name='label[learn_label]']");
		String modulelabel = "Automation";
		moduleLabel.type(modulelabel);

		WebElementFacade courseLabel = $(".//input[@name='label[course_label]']");
		String CourseLabel = "AutomationCourse";
		courseLabel.type(CourseLabel);

		WebElementFacade CoursePlural = $(".//input[@name='label[course_label_plural]']");
		String coursePlural = "AutomationCourses";
		CoursePlural.type(coursePlural);

		WebElementFacade ChapterLabel = $(".//input[@name='label[class_label]']");
		String chapterLabel = "AutomationChapter";
		ChapterLabel.type(chapterLabel);

		WebElementFacade ChapterPlural = $(".//input[@name='label[class_label_plural]']");
		String chapterPlural = "AutomationChapters";
		ChapterPlural.type(chapterPlural);
		report.Info("Changed all labels");

		WebElementFacade enableRatingReview = $(".//label[@for='enable-rating-review']");
		enableRatingReview.click();
		//Thread.sleep(200);

		WebElementFacade anonymousRating = $(".//label[@for='anonymus-rating-review']");
		anonymousRating.click();
		//Thread.sleep(200);

		WebElementFacade enableQuestionAnswer = $(".//label[@for='enable-question-answer']");
		enableQuestionAnswer.click();
		Thread.sleep(200);

		WebElementFacade enablePageView = $(".//label[@for='enable-page-view']");
		enablePageView.click();
		common.scrollToPageEnd();
		Thread.sleep(200);

		WebElementFacade enableLearnerList = $(".//label[@for='enable-learner-list']");
		enableLearnerList.click();
		//Thread.sleep(200);

		WebElementFacade enableCourseSummary = $(".//label[@for='enable-course-summary']");
		enableCourseSummary.click();
		//Thread.sleep(200);
		report.Info("Unchecked all checkboxes");

		WebElementFacade SaveSettingsBtn = $(".//button[@id='submit-learn-setting']");
		SaveSettingsBtn.click();
		Thread.sleep(1000);
		String subURL = "learn/course_catalog";
		getDriver().get(DomainURL+subURL);
		Thread.sleep(2000);

		common.WaitForObjectVisibility(".//ul[@class='mango-breadcrumb-new arrow-brdcrum ma-h1 left ']/li");
		String changedModuleLabel = $(".//ul[@class='mango-breadcrumb-new arrow-brdcrum ma-h1 left ']/li").getText();				
		Assert.assertEquals(modulelabel, changedModuleLabel);
		report.Info("Module label changed");

		String changedCourseLabel = $(".//div[@class='action-button-container course_new_btn right']/a").getText();				
		Assert.assertTrue("Label does not match", changedCourseLabel.contains(CourseLabel));
		report.Info("Course label changed");

		String changedCourseplural = $("//a[@class='nav-link '][@href='/learn/instructing_courses']").getText();						
		Assert.assertTrue("Label does not match", changedCourseplural.contains(coursePlural));
		report.Info("Course plural label changed");

		$(".//span[text()='Soft Skills']").click();
		common.WaitForObjectVisibility(".//span[text()='Automation Course']");
		$(".//span[text()='Automation Course']").click();
		common.WaitForObjectVisibility(".//span[text()='"+chapterPlural+"']");
		Thread.sleep(500);

		String changedChapterplural = $(".//div[@name='lms-chapters-listing-widget']/span").getText();				
		Assert.assertEquals(chapterPlural, changedChapterplural);
		report.Info("Chapter plural label changed");

		WebElementFacade viewsLink = $(".//div/a[contains(text(),'Views')]");
		viewsLink.shouldNotBePresent();
		report.Info("Course views are not present");

		WebElementFacade ratingLink = $(".//div[@class='course-ratings-wrapper relative p-right-10 course-details-overview-topbar-item']");
		ratingLink.shouldNotBePresent();
		report.Info("Rating link is not present");

		WebElementFacade questionAnswers = $(".//h4[text()='Questions & Answers']");
		questionAnswers.shouldNotBePresent();
		report.Info("Questions and Answers are not enabled");

		WebElementFacade learnersList = $(".//div[@class='mangostream-avatar-container bottom-10']");
		learnersList.shouldNotBePresent();
		report.Info("Learners List is not present");

		WebElementFacade courseSummary = $(".//div[@class='lms-course-summary-widget']");
		courseSummary.shouldNotBePresent();
		report.Info("Course Summary is not present");

		getDriver().get(DomainURL+"admin/learn/settings");
		common.WaitForObjectVisibility(".//input[@name='label[learn_label]']");
		moduleLabel = $(".//input[@name='label[learn_label]']");
		modulelabel = "Learn";
		moduleLabel.type(modulelabel);

		courseLabel = $(".//input[@name='label[course_label]']");
		CourseLabel = "Course";
		courseLabel.type(CourseLabel);

		CoursePlural = $(".//input[@name='label[course_label_plural]']");
		coursePlural = "Courses";
		CoursePlural.type(coursePlural);

		ChapterLabel = $(".//input[@name='label[class_label]']");
		chapterLabel = "Chapter";
		ChapterLabel.type(chapterLabel);

		ChapterPlural = $(".//input[@name='label[class_label_plural]']");
		chapterPlural = "Chapters";
		ChapterPlural.type(chapterPlural);
		report.Info("Changed all labels");

		enableRatingReview = $(".//label[@for='enable-rating-review']");
		enableRatingReview.click();
		//Thread.sleep(200);

		anonymousRating = $(".//label[@for='anonymus-rating-review']");
		anonymousRating.click();
		//Thread.sleep(200);

		enableQuestionAnswer = $(".//label[@for='enable-question-answer']");
		enableQuestionAnswer.click();
		//Thread.sleep(200);

		enablePageView = $(".//label[@for='enable-page-view']");
		enablePageView.click();
		common.scrollToPageEnd();
		Thread.sleep(200);

		enableLearnerList = $(".//label[@for='enable-learner-list']");
		enableLearnerList.click();
		//Thread.sleep(200);

		enableCourseSummary = $(".//label[@for='enable-course-summary']");
		enableCourseSummary.click();
		//Thread.sleep(200);

		SaveSettingsBtn = $(".//button[@id='submit-learn-setting']");
		SaveSettingsBtn.click();
		report.Info("Setting restored to default");
		Thread.sleep(1000);
	}

	@Step
	public void courseFields(String logicalName) throws InterruptedException {
		String DomainURL = Serenity.sessionVariableCalled("domainURL").toString();
		getDriver().get(DomainURL+"admin/learn/course_fields");
		common.WaitForObjectVisibility(".//div/a[text()='Add a Section']");
		WebElementFacade addSectionBtn = $(".//div/a[text()='Add a Section']");
		addSectionBtn.click();
		common.WaitForObjectVisibility(".//input[@id='section']");
		WebElementFacade sectionName = $(".//input[@id='section']");
		String sectioname = "Section Automation";
		sectionName.type(sectioname);
		WebElementFacade saveSection = $(".//a[@class='actionbutton waves-effect waves-dark actionblue ma_save_section'][text()='Save']");
		saveSection.waitUntilClickable();
		saveSection.click();
		common.scrollToPageEnd();
		common.WaitForObjectVisibility(".//div[@title='"+sectioname+"']");
		WebElementFacade addedSection = $(".//div[@title='"+sectioname+"']");
		addedSection.shouldBeVisible();
		report.Info("Added section is visible");
		common.mouseHover(addedSection);
		WebElementFacade editSection = $(".//div[@title='"+sectioname+"']/following::i[@title='Edit Section Name']");
		common.clickElement(editSection);
		String sectioName = "Automation Section";
		$(".//div[@title='"+sectioname+"']").type(sectioName);
		Thread.sleep(200);
		$(".//div[@title='"+sectioname+"']").sendKeys(Keys.ENTER);
		Thread.sleep(200);
		//common.WaitForObjectVisibility(".//div[@title='"+sectioname+"']");
		report.Info("Section name edited");
		addedSection = $(".//div[text()='"+sectioName+"']");		
		common.mouseHover(addedSection);
		WebElementFacade deleteSection = $(".//div[text()='"+sectioName+"']/following::i[@title='Delete Section']");
		common.clickElement(deleteSection);
		common.WaitForObjectVisibility(".//input[@id='ma_delete_section_input_learn']");
		WebElementFacade deleteConfirm = $(".//input[@id='ma_delete_section_input_learn']");
		deleteConfirm.sendKeys("DELETE");
		WebElementFacade deleteButton =$(".//button[@id='ma_delete_section_btn_learn']");
		//deleteButton.click();
		common.clickElement(deleteButton);
		common.clickElement(deleteButton);
		addedSection = $(".//div/strong[text()='"+sectioName+"']");
		Thread.sleep(600);
		addedSection.shouldNotBePresent();
		report.Info("Section Deleted");

		WebElementFacade addCustomField = $(".//div/a[text()='Add Custom Fields']");
		addCustomField.waitUntilClickable();
		addCustomField.click();
		common.WaitForObjectVisibility(".//a[@id='add-new-dimension']");
		WebElementFacade newcustomField = $(".//a[@id='add-new-dimension']");
		newcustomField.click();
		common.WaitForObjectVisibility(".//input[@name='dimension[dimension_name]']");
		WebElementFacade fieldName = $(".//input[@name='dimension[dimension_name]']");
		String FieldName = "Field Automation";
		fieldName.type(FieldName);
		Thread.sleep(200);
		WebElementFacade fieldType = $(".//select[@id='dimension_dimension_type']");
		fieldType.selectByVisibleText("Multiple Line Text");
		WebElementFacade saveBtn = $(".//button[@id='dimension_save']");
		saveBtn.click();
		Thread.sleep(500);
		$(".//button/span[text()='Close']").click();
		Thread.sleep(3000);
		common.scrollToPageEnd();
		common.WaitForObjectVisibility(".//label[@data-name='"+FieldName+"']");
		WebElementFacade addedField = $(".//label[@data-name='"+FieldName+"']");
		addedField.shouldBePresent();
		report.Info("Custom field added");

		addCustomField = $(".//div/a[text()='Add Custom Fields']");
		addCustomField.click();
		common.WaitForObjectVisibility(".//a[@id='add-new-dimension']");

		$(".//td/span[contains(text(),'"+FieldName+"')]/following::td[@class='dim-options']/ul/li").click();
		Thread.sleep(200);
		//common.clickElement($(".//td/span[contains(text(),'"+FieldName+"')]/following::td[@class='dim-options']/ul/li/ul/li/a/span[text()='Edit']"));
		$(".//td/span[contains(text(),'"+FieldName+"')]/following::td[@class='dim-options']/ul/li/ul/li/a/span[text()='Edit']").click();
		Thread.sleep(1000);
		fieldName = $(".//input[@name='dimension[dimension_name]']");
		//fieldName.click();
		common.clickElement(fieldName);
		FieldName = "Automation Field";
		fieldName.type(FieldName);
		Thread.sleep(200);
		//	fieldType = $(".//select[@id='dimension_dimension_type']");
		//fieldType.selectByVisibleText("Single Line Text");
		saveBtn = $(".//button[contains(@id,'dimension_save_')]");
		saveBtn.click();
		Thread.sleep(500);
		$(".//button/span[text()='Close']").click();
		common.WaitForObjectVisibility(".//label[@data-name='"+FieldName+"']");
		addedField = $(".//label[@data-name='"+FieldName+"']");
		addedField.shouldBePresent();
		report.Info("Custom field edited");
		common.mouseHover(addedField);
		WebElementFacade editField = $(".//label[@data-name='"+FieldName+"']/following::div/a[text()=' Edit']");
		common.clickElement(editField);
		common.WaitForObjectVisibility(".//label[@for='course_visible_field']");
		WebElementFacade requiredField =$(".//label[@for='course_visible_field']");
		requiredField.click();
		Thread.sleep(200);
		WebElementFacade includeAsFilter =$(".//label[@for='course_include_in_search']");
		includeAsFilter.click();
		$(".//a[@id='ma_save_course_field']").click();
		Thread.sleep(1500);
		common.WaitForObjectVisibility(".//label[@data-name='"+FieldName+"']/following::i[@title='Required']");
		WebElementFacade requiredStar = $(".//label[@data-name='"+FieldName+"']/following::i[@title='Required']");
		requiredStar.shouldBeVisible();
		report.Info("Fiedld marked as required");
		WebElementFacade saveSettings = $(".//button[@id='submit-course-field-top']");
		saveSettings.click();
		Thread.sleep(1000);
		report.Info("Course field settings saved");

		addCustomField = $(".//div/a[text()='Add Custom Fields']");
		addCustomField.click();
		common.WaitForObjectVisibility(".//a[@id='add-new-dimension']");		
		$(".//td/span[contains(text(),'"+FieldName+"')]/following::td[@class='dim-options']/ul/li").click();
		Thread.sleep(200);
		//common.clickElement($(".//td/span[contains(text(),'"+FieldName+"')]/following::td[@class='dim-options']/ul/li/ul/li/a/span[text()='Edit']"));
		$(".//td/span[contains(text(),'"+FieldName+"')]/following::td[@class='dim-options']/ul/li/ul/li/a/span[text()='Delete']").click();
		Thread.sleep(200);
		getDriver().switchTo().alert().accept();
		Thread.sleep(200);
		$(".//button/span[text()='Close']").click();

	}

	@Step
	public void guestCompletedCourseValidation(String logicalName) throws InterruptedException {
		WebElementFacade closeCourse = $(".//a[@class='close-course-reading']");
		closeCourse.waitUntilClickable();
		closeCourse.click();
		common.WaitForObjectVisibility(".//div[@name='member_course_list_show']/a[text()='View All']");
		report.Info("Guest user completed the course");
		WebElementFacade viewAll = $(".//div[@name='member_course_list_show']/a[text()='View All']");
		viewAll.click();
		Thread.sleep(2000);
		String courseTaken =Serenity.sessionVariableCalled("coursename");
		common.WaitForObjectVisibility(".//h4[@title='"+courseTaken+"']");
		WebElementFacade coursetaken = $(".//h4[@title='"+courseTaken+"']");
		coursetaken.click();
		common.WaitForObjectVisibility(".//div[@class='certificate_img_c']");
		$(".//div[@class='dialog_actions right']/a[text()='Close']").click();
		report.Info("Guest user has obtained course certificate");
	}

	@Step
	public void verifyCourseVisiblility(String logicalName) throws IOException, InterruptedException {
		String courseToTake ="Automation Course";
		String DomainURL = Serenity.sessionVariableCalled("domainURL").toString();
		/*	String filePath = "\\src\\test\\resources\\TestData\\TestData.xlsx";
		String dataSheet = "LMS";		
		Map <String,Map<Integer, String>> tableMap = ReadWorkbook.readRow(logicalName, filePath, dataSheet);		
		readWorkbook.testData(tableMap);
		for (int i = 0  ;i < tableMap.get("Row").size();i++) {	*/	
		String availableTo ="";
		if(logicalName.contains("Location")) {
			availableTo ="Specific Location";
		}else if (logicalName.contains("Company")){
			availableTo ="Entire Company";
		}else if (logicalName.contains("Private")){
			String privateCourseURL = Serenity.sessionVariableCalled("privateCourseURL");
			availableTo ="Private";	
			getDriver().get(privateCourseURL);
			Thread.sleep(1500);
			common.WaitForObjectVisibility(".//div[@class='mp-init']/span");
			$("//div[contains(text(),'"+courseToTake+"')]").shouldBeVisible();
			report.Info("visibility success for "+availableTo);
		}else if (logicalName.contains("Public")){
			String publicCourseURL = Serenity.sessionVariableCalled("publicCourseURL").toString();
			availableTo ="Public";
			getDriver().get(publicCourseURL);
			//courseToTake = Serenity.sessionVariableCalled("courseToTake");
			common.WaitForObjectVisibility(".//span[text()='"+courseToTake+"']");
			$("//span[text()='"+courseToTake+"']").shouldBeVisible();
			report.Info("Course visible on Public Page");
			$("//span[text()='"+courseToTake+"']").click();
			common.WaitForObjectVisibility(".//a[contains(text(),'Start Course')]");
			WebElementFacade startCourse = $(".//a[contains(text(),'Start Course')]");
			startCourse.shouldBeVisible();
			report.Info("Public course Clicked");
		}

		if((!logicalName.contains("Private"))&&(!logicalName.contains("Public"))){
			getDriver().get(DomainURL+"learn/course_catalog");
			String category = "Diversity & Inclusion";
			common.WaitForObjectVisibility(".//span[text()='"+category+"']");
			WebElementFacade courseCategory = $(".//span[text()='"+category+"']");
			courseCategory.click();
			report.Info("Clicked Category");			 
			report.Info("Course to take:-"+courseToTake);
			common.WaitForObjectVisibility(".//span[text()='"+courseToTake+"']");
			//	WebElementFacade course = $(".//span[text()='"+courseToTake+"']");
			$(".//span[text()='"+courseToTake+"']").shouldBeVisible();
			report.Info("visibility success for "+availableTo);
		}
	}

	@Step
	public void createSCORMCourse(String logicalName) throws IOException, InterruptedException {
		String DomainURL = Serenity.sessionVariableCalled("domainURL").toString();
		getDriver().get(DomainURL+"admin/learn/courses");
		common.WaitForObjectVisibility(".//ul[@class='narrow_by hand relative n-icn right hover-object scorm_pg']/li/a");
		WebElementFacade scormHoverArrow = $(".//ul[@class='narrow_by hand relative n-icn right hover-object scorm_pg']/li/a");
		common.mouseHover(scormHoverArrow);
		Thread.sleep(500);
		WebElementFacade importScormCourse = $(".//ul/li/a[text()='Import SCORM Course']");
		common.clickElement(importScormCourse);
		common.WaitForObjectVisibility(".//a[text()='Upload Course']");
		$(".//a[text()='Upload Course']").click();

		String FilePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AutomationSCORM.zip";
		WebElementFacade AddFilesButton = $(".//input[@id='fileupload']");		
		upload(FilePath).to(AddFilesButton);
		Thread.sleep(4000);

		WebElementFacade StartImport = $(".//a[@id='file_upload_btn']");
		StartImport.waitUntilClickable();
		report.Info("Importing SCORM");
		StartImport.click();
		common.WaitForObjectVisibility(".//div[@class='dialog_actions right']/button[text()='Ok']");
		WebElementFacade okButton = $(".//div[@class='dialog_actions right']/button[text()='Ok']");
		okButton.click();								
		common.WaitForObjectVisibility(".//table[@id='course_table_list']");
		report.Info("SCORM course imported");

		Serenity.setSessionVariable("coursename").to("OneDrive");
		String locateCol = "Name";
		String value = Serenity.sessionVariableCalled("coursename");
		String table = ".//table[@id='course_table_list']";
		common.locateColumn(table, locateCol, value, "0");
		report.Info("SCORM Course created");
		common.tableActions(table, "ClickCourseDots", "");
		Thread.sleep(300);
		WebElementFacade editCourseInfo=$(".//ul[@style='display: block;']/li/a[text()='Edit Course Info']");
		editCourseInfo.click();
		report.Info("Clicked edit course");

		createEditCourse("EditScormCourse");

		getDriver().get(DomainURL+"admin/learn/courses");
		locateCol = "Name";
		value = Serenity.sessionVariableCalled("coursename");
		table = ".//table[@id='course_table_list']";
		common.locateColumn(table, locateCol, value, "0");
		report.Info("SCORM Course created");
		common.tableActions(table, "ClickCourseDots", "");
		Thread.sleep(300);
		WebElementFacade publishCourse=$(".//ul[@style='display: block;']/li/a[text()='Publish']");
		publishCourse.click();
		Thread.sleep(2000);

		String courseName =  Serenity.sessionVariableCalled("coursename");
		locateCol = "Name,Status";
		value =courseName+",ACTIVE";
		table = ".//table[@id='course_table_list']";
		common.locateColumn(table, locateCol, value, "0");
		report.Info("SCORM Course Published");
		common.tableActions(table, "ClickCourseDots", "");
		Thread.sleep(300);
		WebElementFacade UploadNewVersion=$(".//ul[@style='display: block;']/li/a[text()='Upload New Version']");
		UploadNewVersion.click();
		WebElementFacade ChooseFile = $(".//input[@id='fileupload']");		
		upload(FilePath).to(ChooseFile);
		Thread.sleep(4000);
		StartImport = $(".//a[@id='scorm_file_upload_btn']");
		report.Info("Importing  new version SCORM");
		StartImport.click();
		Thread.sleep(1000);
		common.WaitForObjectVisibility(".//div[@class='dialog_actions right']/button[text()='Ok']");
		okButton = $(".//div[@class='dialog_actions right']/button[text()='Ok']");
		okButton.click();

		locateCol = "Name,Status";
		value =courseName+",ACTIVE";
		table = ".//table[@id='course_table_list']";
		common.locateColumn(table, locateCol, value, "0");
		report.Info("SCORM Course Published");
		common.tableActions(table, "ClickCourseDots", "");
		Thread.sleep(300);
		WebElementFacade VersionSettingsTracking=$(".//ul[@style='display: block;']/li/a[text()='Version Settings & Tracking']");
		VersionSettingsTracking.click();

		common.WaitForObjectVisibility(".//ul[@id='tabs-ul']/li/a[@href='#lms-tracking']");
		WebElementFacade TrackingTab = $(".//ul[@id='tabs-ul']/li/a[@href='#lms-tracking']");
		TrackingTab.click();
		common.WaitForObjectVisibility(".//span[text()='Version: 1']");
		$(".//span[text()='Version: 1']").shouldBePresent();
		report.Info("SCORM versioning successful");
	}

	@Step
	public void viewCourseInsights(String logicalName) throws InterruptedException {
		String DomainURL = Serenity.sessionVariableCalled("domainURL").toString();
		getDriver().get(DomainURL+"admin/learn/courses");
		String courseName = Serenity.sessionVariableCalled("coursename");
		String locateCol = "Name";
		String value =courseName;
		String table = ".//table[@id='course_table_list']";
		common.locateColumn(table, locateCol, value, "0");
		report.Info("SCORM Course created");
		common.tableActions(table, "ClickCourseDots", "");
		Thread.sleep(300);
		WebElementFacade ViewCourseInsights=$(".//ul[@style='display: block;']/li/a[contains(text(),'View Course Insights')]");
		ViewCourseInsights.click();
		report.Info("Clicked View Course Insights");
		common.WaitForObjectVisibility(".//span[@class='green_color'][text()='Completed']/following::p[text()='1']");
		WebElementFacade courseCompleted = $(".//span[@class='green_color'][text()='Completed']/following::p[text()='1']");
		courseCompleted.shouldBeVisible();
		report.Info("1 completed course visible");

		WebElementFacade learnersTab = $(".//ul[@id='tabs-ul']/li[@id='lms-admin-cerfications']");
		String user = Serenity.sessionVariableCalled("User");
		learnersTab.click();
		common.WaitForObjectVisibility(".//table[@id='course_user_table']");
		locateCol = "User,Status";
		value = user+",Completed";
		table = ".//table[@id='course_user_table']";
		common.locateColumn(table, locateCol, value, "0");	
		common.tableActions(table, "DrillDown", "Completed");
		Thread.sleep(300);
		common.WaitForObjectVisibility("(.//span/i[@class='far fa-check check_tick_opt flex-20'])[1]");
		String noOfChapters = Serenity.sessionVariableCalled("contentTypes");
		String arr[] = noOfChapters.split(Pattern.quote(","));
		int size = arr.length;
		//int size = 1;
		List<WebElementFacade> Ticks = findAll(By.xpath(".//span/i[@class='far fa-check check_tick_opt flex-20']")); 
		int noOfTicks = Ticks.size();

		if (noOfTicks==size) {
			report.Info("All the chapters marked completed");
		}else {
			report.Info("All the chapters not marked completed");
			Assert.assertTrue(false);
		}
		$(".//div[@id='fancybox-content']/div/div/a/span[text()='Close']").click();

		locateCol = "User,Certificate";
		value = user+",View Certificate";
		table = ".//table[@id='course_user_table']";
		common.locateColumn(table, locateCol, value, "0");	
		common.tableActions(table, "ClickViewCertificate", "View Certificate");
		common.WaitForObjectVisibility(".//div[@class='dialog_body']/img");
		WebElementFacade userCertificate  =$(".//div[@class='dialog_body']/img");
		Thread.sleep(1500);
		userCertificate.shouldBeVisible();
		report.Info("View certificate successful");
		$(".//div[@class='dialog_actions align-right']/a/span[text()='Close']").click();
	}

	@Step
	public void checkLearnDisabled(String logicalName) throws InterruptedException {
		String DomainURL = Serenity.sessionVariableCalled("domainURL").toString();
		getDriver().get(DomainURL+"ce/pulse/admin/domain/navigation");
		common.scrollToPageEnd();
		Thread.sleep(300);
		common.WaitForObjectVisibility(".//div[@class='onoffswitch module_setting']/label[@for='learn_switch']");
		WebElementFacade learnSwitch = $(".//div[@class='onoffswitch module_setting']/label[@for='learn_switch']");
		learnSwitch.click();
		Thread.sleep(200);
		report.Info("Learn module disabled");
		getDriver().get(DomainURL+"u");
		Thread.sleep(1000);
		WebElementFacade LHSLearn = $(".//ul[@id='lhs_container']/li/a[contains(@href,'learn')]"); 
		LHSLearn.shouldNotBeVisible();
		report.Info("Learn module not present on LHS");
		String publicCourseURL = Serenity.sessionVariableCalled("publicCourseURL").toString();		
		getDriver().get(publicCourseURL);
		Thread.sleep(1000);
		WebElementFacade publicLink = $(".//ul[@class='nav user-top-nav']/li/a[@href='"+publicCourseURL+"']");
		publicLink.shouldNotBeVisible();
		report.Info("Public course link notvisible");

		getDriver().get(DomainURL+"ce/pulse/admin/domain/navigation");
		common.scrollToPageEnd();
		Thread.sleep(300);
		common.WaitForObjectVisibility(".//div[@class='onoffswitch module_setting']/label[@for='learn_switch']");
		learnSwitch = $(".//div[@class='onoffswitch module_setting']/label[@for='learn_switch']");
		learnSwitch.click();
		Thread.sleep(200);
		report.Info("Learn module enabled");
	}

	@Step
	public void viewCourseAsInstructor(String logicalName) throws InterruptedException {
		String privateCourseURL = Serenity.sessionVariableCalled("privateCourseURL");	
		getDriver().get(privateCourseURL);
		Thread.sleep(1500);
		common.WaitForObjectVisibility(".//div[@class='mp-init']/span");
		WebElementFacade ViewAsLearner = $(".//div[@id='course_action_container']/a");
		ViewAsLearner.shouldBePresent();
		report.Info("Instructor can view as learner");
		WebElementFacade AdminTools = $(".//ul[@id='course-details-opt']/li/a[@title='Admin Tools']");
		AdminTools.click();
		Thread.sleep(500);
		$(".//ul[@class='course_options_menu']/li/a[contains(text(),'Edit Course Info')]").shouldBeVisible();
		report.Info("Instructor can view admin options");
	}

	public void createCurriculum(String logicalName) throws InterruptedException, IOException {
		String DomainURL = Serenity.sessionVariableCalled("domainURL").toString();
		String filePath = "\\src\\test\\resources\\TestData\\TestData.xlsx";
		String dataSheet = "LMS";		
		String curriculumname ="";
		Map <String,Map<Integer, String>> tableMap = ReadWorkbook.readRow(logicalName, filePath, dataSheet);		
		readWorkbook.testData(tableMap);
		for (int i = 0 ;i < tableMap.get("Row").size();i++) {		

			String SubURL = tableMap.get("SubURL").get(i);
			String createCurriculum = tableMap.get("createCurriculum").get(i);
			String category = tableMap.get("Category").get(i);
			String availableto= tableMap.get("availableTo").get(i);
			String setReset = tableMap.get("setReset").get(i);
			String traversalrule = tableMap.get("traversalRule").get(i);
			Serenity.setSessionVariable("traversalrule").to(traversalrule);
			String completionrule = tableMap.get("completionRule").get(i);	
			Serenity.setSessionVariable("completionrule").to(completionrule);
			String preRequisiteCourse = tableMap.get("preRequisiteCourse").get(i);

			if((createCurriculum.equalsIgnoreCase("Yes")) || (createCurriculum.equalsIgnoreCase("Edit"))){
				if(createCurriculum.equalsIgnoreCase("Yes")) {
					getDriver().get(DomainURL+"admin/learn/courses");
					common.WaitForObjectVisibility(".//ul[@class='narrow_by hand relative n-icn right hover-object scorm_pg']/li/a");
					WebElementFacade HoverArrow = $(".//ul[@class='narrow_by hand relative n-icn right hover-object scorm_pg']/li/a");
					common.mouseHover(HoverArrow);
					Thread.sleep(500);
					WebElementFacade CreateCurriculum = $(".//ul/li/a[text()='Create a Curriculum']");
					common.clickElement(CreateCurriculum);
					common.WaitForObjectVisibility(".//input[@id='course-name']");
					curriculumname = "ACR" +dateName;
					courseName.type(curriculumname);
					$(".//span[@class='cke_button_icon cke_button__numberedlist_icon']").click();
					Serenity.setSessionVariable("curriculumname").to(curriculumname);
					if(!category.equals("")) {
						Serenity.setSessionVariable("category").to(category);
						setResetCourseVideoPhoto("","",setReset);
						WebElementFacade assignChangeCourseBtn = $(".//div/button[@id='assign-course-category']");
						common.waitForElement(assignChangeCourseBtn);
						common.clickElement(assignChangeCourseBtn);	
						common.WaitForObjectVisibility(".//label[text()='"+category+"']");
						WebElementFacade categoryCheckBox = $(".//label[text()='"+category+"']");
						Thread.sleep(500);
						categoryCheckBox.click();
						report.Info("Edited Category");
						WebElementFacade saveCategoryBtn = $(".//div/button[@id='save-assign-category']");
						saveCategoryBtn.click();
					}
					if(createCurriculum.equalsIgnoreCase("Yes")) {
						WebElementFacade courseCode = $(".//div/input[@name='course[course_code]']");
						courseCode.waitUntilEnabled();
						courseCode.type("C"+dateName);
						WebElementFacade skillsGained= $(".//ul/li/input[@id='token-input-course-skills']");
						skillsGained.sendKeys("Collaboration");
						Thread.sleep(2000);
						skillsGained.sendKeys(Keys.TAB);

						WebElementFacade instructor= $(".//ul/li/input[@id='token-input-course-instructor']");
						String courseinstructor = Serenity.sessionVariableCalled("instructor");
						instructor.sendKeys(courseinstructor);
						Thread.sleep(2500);
						instructor.sendKeys(Keys.TAB);
						Thread.sleep(500);
						WebElementFacade assignCertificate = $(".//div/button[@id='assign-certificates']");
						common.clickElement(assignCertificate);
						//assignCertificate.click();
						common.WaitForObjectVisibility(".//figcaption[@class='mt-preview-dialog--lhs-template-details']");
						//WebElementFacade certificateOfCompletion = $(".//figcaption[@class='mt-preview-dialog--lhs-template-details']/div[@title='Certificate of Completion']");
						WebElementFacade doneButton = $(".//div/a[@id='select-assign-certificate']");
						doneButton.click();
						common.WaitForObjectVisibility(".//h5[text()='[Course Name]']");
						report.Info("Certificate Assigned"); 
						common.scrollToPageEnd();		    
						//WebElementFacade validity =$(".//a[@class='selectBox mango-selectbox white-selectbox selectBox-dropdown']/span[text()='1 Month']");			
						//common.actionClick(validity);
						//	common.actionClick($(".//ul[@class='selectBox-dropdown-menu selectBox-options']/li/a[text()='No Expiry']"));
					}
				} if (createCurriculum.equals("ChangeVisibility")) {
					String table = ".//table[@id='course_table_list']";
					String locateCol = "Name";
					String value ="Automation Course";

					common.locateColumn(table, locateCol, value, "0");
					common.tableActions(table, "ClickCourseDots", "");
					Thread.sleep(300);
					WebElementFacade editCourseInfo=$(".//ul[@style='display: block;']/li/a[text()='Edit Course Info']");
					editCourseInfo.click();
					common.WaitForObjectVisibility(".//input[@id='course-name']");
				}

				WebElementFacade saveAndContinue = $(".//a[@id='submit_create_edit_course']");
				saveAndContinue.click();
				Thread.sleep(1000);
				if (createCurriculum.equals("Yes")) {
					curriculumSteps(logicalName);
				}
				common.WaitForObjectVisibility("(.//a[@class='selectBox mango-selectbox white-selectbox selectBox-dropdown'])[1]");
				if(!availableto.equals("")) {
					WebElementFacade availableTo= $("(.//a[@class='selectBox mango-selectbox white-selectbox selectBox-dropdown'])[1]");
					common.actionClick(availableTo);
					Thread.sleep(500);
					common.actionClick($(".//ul[@class='selectBox-dropdown-menu selectBox-options']/li/a[text()='"+availableto+"']"));
					report.Info("Visibility set to "+availableto);
					if(availableto.contains("Specific Team")) {
						WebElementFacade teamName = $(".//ul/li/input[@id='token-input-course-rule-auto']");
						teamName.sendKeys("MangoApps");
						Thread.sleep(2000);
						teamName.sendKeys(Keys.TAB);
					}else if(availableto.contains("Specific Users")) {
						$(".//ul[@class='data-list-items ma_inputBubbleList']/li/input").click();
						Thread.sleep(300);
						$(".//ul[@class='data-list-items ma_inputBubbleList']/li/input").sendKeys("First");
						Thread.sleep(200);
						$(".//ul[@class='ma_peopleSearchList ma_peopleSubList']/li[@data_label='First Name']").click();
						$(".//input[@placeholder='Enter First Name']").waitUntilVisible();
						String lmsUser = Serenity.sessionVariableCalled("lmsUser");
						$(".//input[@placeholder='Enter First Name']").sendKeys(lmsUser);
						Thread.sleep(200);
						$(".//button[text()='Apply']").click();
					}else if (availableto.contains("Specific Location")) {
						WebElementFacade teamName = $(".//ul/li/input[@id='token-input-course-rule-auto']");
						teamName.sendKeys("Seattle");
						Thread.sleep(2500);
						teamName.sendKeys(Keys.TAB);
					}
				}

				String value ="";		
				if(preRequisiteCourse.equals("Yes")) {
					WebElementFacade PreRequisiteCourse = $(".//input[@id='token-input-prerequisit-courses']");
					PreRequisiteCourse.type("PreRequisite Course");
					Thread.sleep(2000);
					PreRequisiteCourse.sendKeys(Keys.TAB);
				}

				Thread.sleep(500);
				WebElementFacade saveAndExit = $(".//div/a[@id='save-add-publish']");
				saveAndExit.click();
				common.WaitForObjectVisibility(".//table[@id='course_table_list']");

				if(createCurriculum.equals("Yes")) {
					String locateCol = "Name,Status";
					value =  Serenity.sessionVariableCalled("coursename")+",ACTIVE";
					String table = ".//table[@id='course_table_list']";
					common.locateColumn(table, locateCol, value, "0");
					report.Info("Curriculum Successfully created/edited");
				}

				/*if(logicalName.equals("CreateNewCourse")){
					getDriver().get(DomainURL+"learn/instructing_courses");
					common.WaitForObjectVisibility(".//span[text()='"+coursename+"']");
					$(".//span[text()='"+coursename+"']").shouldBeVisible();
					report.Info("Course visible to instructor");
				}*/

			}

		}
	}

	@Step
	public void curriculumSteps(String logicalName) throws InterruptedException {
		common.WaitForObjectVisibility(".//div/input[@id='learn_course_curriculum_steps_attributes_0_name']");
		WebElementFacade Step1 = $(".//div/input[@id='learn_course_curriculum_steps_attributes_0_name']");
		Step1.type("ACR Step1");
		WebElementFacade AddCourses = $(".//ul[@class='token-input-list-facebook notranslate']/li/input[@id='token-input-']");
		String course1 = "Automation Course";
		String course2 = "PreRequisite Course";
		AddCourses.sendKeys(course1);
		Thread.sleep(2000);
		AddCourses.sendKeys(Keys.TAB);
		common.WaitForObjectVisibility(".//strong[text()='"+course1+"']");
		AddCourses.sendKeys(course2);
		Thread.sleep(2000);
		AddCourses.sendKeys(Keys.TAB);
		common.WaitForObjectVisibility(".//strong[text()='"+course2+"']");

		WebElementFacade EnforceCourseSequence = $(".//label[@for='learn_course_curriculum_steps_attributes_0_is_sequential']");
		common.clickElement(EnforceCourseSequence);
		Thread.sleep(1000);

		WebElementFacade saveAndContinue = $(".//div/div/a[@id='submit_curriculum_steps']");
		saveAndContinue.click();

	}

	@Step
	public void takeCurriculumActions(String logicalName) throws IOException, InterruptedException {
		String DomainURL = Serenity.sessionVariableCalled("domainURL").toString();
		String filePath = "\\src\\test\\resources\\TestData\\TestData.xlsx";
		String dataSheet = "LMS";		
		Map <String,Map<Integer, String>> tableMap = ReadWorkbook.readRow(logicalName, filePath, dataSheet);		
		readWorkbook.testData(tableMap);
		for (int i = 0  ;i < tableMap.get("Row").size();i++) {				

			String category =Serenity.sessionVariableCalled("category");
			//String traversalrule =Serenity.sessionVariableCalled("traversalrule");
			String completionrule =Serenity.sessionVariableCalled("completionrule");
			String SubURL = tableMap.get("SubURL").get(i);
			getDriver().get(DomainURL+SubURL);
			//String createCourse = tableMap.get("createCourse").get(i);
			if(!logicalName.equals("TakeCourseGuest")) {
				common.WaitForObjectVisibility(".//span[text()='"+category+"']");
				WebElementFacade courseCategory = $(".//span[text()='"+category+"']");
				courseCategory.click();
				report.Info("Clicked Category");
			}else {
				common.WaitForObjectVisibility(".//a[@title='MangoApps Automation Team -- Mango Automation Internal Testing']");
				AutomationTestProject.click();
				Thread.sleep(2000);
				getDriver().navigate().refresh();
				//Thread.sleep(2000);
			}

			String curriculumname =Serenity.sessionVariableCalled("curriculumname");
			report.Info("Curriculum to take:-"+curriculumname);
			common.WaitForObjectVisibility(".//span[text()='"+curriculumname+"']");
			//WebElementFacade course = $(".//span[text()='"+courseToTake+"']");
			common.clickElement($(".//span[text()='"+curriculumname+"']"));
			common.WaitForObjectVisibility(".//div[@class='mp-init']/span");
			WebElementFacade startCurriculum = $(".//div[@id='curriculum_action_container']/a");
			startCurriculum.click();

			common.WaitForObjectVisibility(".//div/a[@id='start_course']");

			if(logicalName.equals("TakeCourseSequential")) {
				$(".//span[@title='You can move to the next chapter only after completing the chapter']").shouldBeVisible();
				report.Info("Sequencial completion working");
				break;
			}else if(logicalName.equals("TakeCourseAnyOrder")) {
				$(".//span[@title='You can move to the next chapter only after completing the chapter']").shouldNotBeVisible();
				report.Info("Non Sequencial completion working");
				//break;
			}

			for(int n=0;n<2;n++) {

				WebElementFacade StartCourse = $(".//div/a[@id='start_course']");
				StartCourse.click();

				common.WaitForObjectVisibility(".//a[@id='mark-as-complete-chapter']");
				Thread.sleep(2000);

				$(".//a[@id='mark-as-complete-chapter']").click();
				Thread.sleep(1500);
				WebElementFacade CloseCourse = $(".//a[@class='close-course-reading']");
				CloseCourse.click();
				common.WaitForObjectVisibility(".//div[@class='mp-init']/span");
				common.scrollToPageEnd();
				Thread.sleep(500);
				if(n==0) {
					WebElementFacade Course1Completed = $(".//span[text()=' Automation Course']/following::span[text()='Completed']");
					Course1Completed.shouldBeVisible();
					report.Info("Course 1 successfully completed");
				}else if (n==1) {
					WebElementFacade Course2Completed = $(".//span[text()=' PreRequisite Course']/following::span[text()='Completed']");
					Course2Completed.shouldBeVisible();
					report.Info("Course 2 successfully completed");
				}
			}
		}
	}
}
//.//a[@data-disabled='true'][@id='start_course']
