package performer;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import cfo.CFOcountPOM;

public class OverduePOM
{
	private static WebElement msg = null;				//WebElement variable created for popup message button
	private static WebElement overdue = null;			//WebElement variable created for Statutory Overdue click
	private static WebElement statutoryAction = null;	//WebElement variable created for clicking on Action Image
	private static WebElement review = null;			//WebElement variable created for clicking on Action Image
	private static WebElement dropdown = null;			//WebElement variable created for Compliance Status dropdown box
	private static WebElement upload = null;			//WebElement variable created for uploading file
	private static WebElement date = null;				//WebElement variable created for selecting date
	private static WebElement value = null;				//WebElement variable created for inserting value in textbox
	private static WebElement selectDate = null;		//WebElement variable created for selecting date from second row and fifth column
	private static WebElement dashboard = null;			//WebElement variable created for clicking on dashboard
	private static WebElement submit = null;			//WebElement variable created for Submit button click
	private static WebElement lastMonth = null;			//WebElement variable created for clicking on arrow on calendar which shows last month
	private static List<WebElement> elementsList = null;	//WebElement list created for selecting action button from multiple action buttons
	private static WebElement msgElement = null;			//WebElement variable created for checking Message after action button click in Internal Overdue
	private static WebElement close = null;					//WebElement variable created for closing window of internal compliance if disabled submit button 
	private static WebElement nextPage = null;				//WebElement variable created for clicking on next page arrow (if compliance without message notfound)
	private static WebElement statutoryChecklist = null;	//WebElement variable created for uploading file
	private static List<WebElement> checkboxesList = null;	//WebElement list created for selecting action button from multiple action buttons
	private static WebElement checklistSubmit = null;		//WebElement variable created for clicking on Submit button.
	private static WebElement checklistNotApplicable = null;	//WebElement variable created for clicking on Not Applicable button through Check boxes.
	private static WebElement actionNotApplicable = null;	//WebElement variable created for clicking on Not Applicable button through action button.
	private static WebElement internalChecklist = null;		//WebElement variable created for uploading file
	private static WebElement activatedEvents = null;	//WebElement list created for selecting date text box from multiple date text boxes
	private static WebElement assignedEvents = null;	//WebElement variable created for clicking on Assigned Events value.
	private static List<WebElement> checkboxes = null;	//WebElement list created for selecting action button from multiple action buttons
	private static List<WebElement> textboxes = null;	//WebElement list created for selecting text box from multiple text boxes
	private static List<WebElement> dates = null;		//WebElement list created for selecting date text box from multiple date text boxes
	private static List<WebElement> viewEvent = null;	//WebElement list created for clicking on View Event from multiple View Events
	private static WebElement closeView = null;			//WebElement variable created to close opened View Event
	private static List<WebElement> activate = null;	//WebElement list created for clicking 'Activate Button' image from multiple buttons
	private static WebElement save = null;				//WebElement variable created to click on save button.
	private static WebElement performer = null;			//WebElement variable created to click on Performer dropdown.
	private static WebElement reviewer = null;			//WebElement variable created to click on Reviewer dropdown.
	private static WebElement approver = null;			//WebElement variable created to click on Approver dropdown.
	private static WebElement checkbox = null;			//WebElement variable created to click on checkbox to select all checkboxes.
	
	public static WebElement closeMessage(WebDriver driver)					//Method for closing Message Popup
	{
		msg = driver.findElement(By.xpath("//*[@id='divNotification']/div/div/div[1]/button"));
		return msg;
	}
	
	public static WebElement clickStatutoryOverdue(WebDriver driver)		//Method for clicking on Statutory Overdue value
	{
		overdue = driver.findElement(By.xpath("//div[@id='ContentPlaceHolder1_divOverduePREOcount']"));
		return overdue;
	}
	
	public static WebElement readPendingReviewStatutory(WebDriver driver)	//Method to read Statutory Pending Review
	{
		review = driver.findElement(By.xpath("//*[@id='ContentPlaceHolder1_divPerformerPendingforReviewPREOcount']"));
		return review;
	}
	
	public static WebElement clickStatutoryAction(WebDriver driver)			//Method to click on Third row action button
	{
		statutoryAction = driver.findElement(By.xpath("//*[@id='grid']/div[4]/table/tbody/tr[2]/td[17]/a"));
		return statutoryAction;
	}
	
	public static List<WebElement> clickStatutoryActionButton(WebDriver driver)
	{
		elementsList = driver.findElements(By.xpath("//*[@class='k-button k-button-icontext ob-overview k-grid-edit2']"));
		return elementsList;
	}
	
	public static List<WebElement> ActionButtons(WebDriver driver)
	{
		elementsList = driver.findElements(By.xpath("//*[@class='k-button k-button-icontext ob-overview k-grid-edit2']"));
		return elementsList;
	}
	
	public static WebElement selectStatutoryDropdown(WebDriver driver)		//Method to search dropdown box
	{
		dropdown = driver.findElement(By.xpath("//select[@id='ddlStatus']"));
		return dropdown;
	}
	//*[@id="ddlStatus"]
	public static WebElement complianceDocLink(WebDriver driver)
	{
		dropdown = driver.findElement(By.xpath("//input[@name='TxtworkingdocumentlnkStatutory']"));
		return dropdown;
	}
	
	public static WebElement buttonAddLink(WebDriver driver)
	{
		dropdown = driver.findElement(By.xpath("//input[@name='UploadlinkWorkingfileStatutory']"));
		return dropdown;
	}
	
	public static WebElement fileUploadStatutory(WebDriver driver)			//Method to search Choose File button. 
	{
		upload = driver.findElement(By.xpath("//*[@id='fuSampleFile']"));
		return upload;
	}
	
	public static WebElement fileUploadStatutory1(WebDriver driver)			//Method to search Choose File button. 
	{
		upload = driver.findElement(By.xpath("//input[@id='FileUpload1']"));
		return upload;
	}
	
	public static WebElement selectDateStatutory(WebDriver driver)			//Method to search Date Box
	{
		date = driver.findElement(By.xpath("//*[@id='tbxDate']"));
		return date;
	}
	
	public static WebElement valueSystem(WebDriver driver)					//Method to search text box
	{
		value = driver.findElement(By.xpath("//input[@id='txtValueAsPerSystem']"));
		return value;
	}
	
	public static WebElement valueReturn(WebDriver driver)					//Method to search text box
	{
		value = driver.findElement(By.xpath("//input[@id='txtValueAsPerReturn']"));
		return value;
	}
	
	public static WebElement remark(WebDriver driver)						//Method created to search text area 
	{
		value = driver.findElement(By.xpath("//textarea[@id='tbxRemarks']"));
		return value;//*[@id="tbxRemarks"]
	}
	
	public static WebElement clickComplianceSubmit(WebDriver driver)		//Method for searching button for Submit Form
	{
		submit = driver.findElement(By.xpath("//input[@value ='Submit']"));
		return submit;
	}
	
	public static WebElement clickDashboard(WebDriver driver)				//Method to search Dashboard Link
	{
		dashboard = driver.findElement(By.xpath("//*[@id='leftdashboardmenu']"));
		//dashboard = driver.findElement(By.xpath("//*[@id='leftdashboardmenu']/a/span[1]"));
		return dashboard;
	}
	
	public static WebElement clickAdvancedSearch(WebDriver driver)
	{
	//	dashboard = driver.findElement(By.xpath("//button[@id='AdavanceSearch']"));
		dashboard = driver.findElement(By.xpath("//*[@id='AdavanceSearch']"));
		return dashboard;
	}
	
	public static WebElement clickInternalOverdue(WebDriver driver)			//Method for clicking on Internal Overdue value
	{
		overdue = driver.findElement(By.xpath("//div[@id='ContentPlaceHolder1_divOverdueInternalPREOcount']"));
		return overdue;
	}
	
	public static WebElement readPendingReviewInternal(WebDriver driver)	//Method to read Statutory Pending Review
	{
		review = driver.findElement(By.xpath("//div[@id='ContentPlaceHolder1_divPerformerPendingforRevieweInternalPREOcount']"));
		return review;
	}
	
	public static List<WebElement> clickActionButtonList(WebDriver driver) 	//Method to get list of action buttons on web page
	{
		//elementsList = driver.findElements(By.xpath("//*[@role='button'][@class='k-button k-button-icontext ob-overview k-grid-edit2']"));
		elementsList = driver.findElements(By.xpath("//*[@id='grid']/div[4]/table/tbody/tr/td[24]/a[1]"));
		//elementsList = driver.findElements(By.xpath("//*[@id='grid']/div[4]/table/tbody/tr[1]/td[24]/a[1]"));
		
		return elementsList;
	}
	
	public static WebElement msgCheck(WebDriver driver)						//Method to check message -"Compliance related task is not yet completed."
	{
		msgElement = driver.findElement(By.xpath("//span[@id='lbltaskinternal']"));
		return msgElement;
	}
	
	public static WebElement closeCompliance(WebDriver driver)				//Method to close compliance popup.
	{
		close = driver.findElement(By.xpath("//*[@id='ComplainceInternalPerformaer']/div/div/div[1]/button"));
		return close;
	}
	
	public static WebElement selectInternalDropdown(WebDriver driver)		//Method to search Dropdown.
	{
		dropdown = driver.findElement(By.xpath("//select[@id='ddlStatus2']"));
		return dropdown;
	}
	
	public static WebElement complianceDocLinkInternal(WebDriver driver)
	{
		dropdown = driver.findElement(By.xpath("//input[@name='TxtCompliancedocumentlnk']"));
		return dropdown;
	}
	
	public static WebElement buttonAddLinkInternal(WebDriver driver)
	{
		dropdown = driver.findElement(By.xpath("//input[@name='UploadlinkCompliancefile']"));
		return dropdown;
	}
	
	public static WebElement fileUploadInternal(WebDriver driver)			//Method to search Choose File button.
	{
		upload = driver.findElement(By.xpath("//*[@id='fuSampleFile2']"));
		return upload;
	}
	
	public static WebElement selectDateInternal(WebDriver driver)			//Method to search Date box
	{
		date = driver.findElement(By.xpath("//input[@id='tbxDate2']"));
		return date;
	}
	
	public static WebElement selectDate(WebDriver driver)					//Method to click on date at second row and fourth column
	{
		selectDate = driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/table/tbody/tr[3]/td[3]/a"));
		return selectDate;
	}
	
	public static WebElement selectDate2(WebDriver driver)					//Method to click on date at second row and fifth column
	{
		selectDate = driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/table/tbody/tr[3]/td[3]/a"));
		return selectDate;
	}
	
	public static WebElement selectDate3(WebDriver driver)					//Method to click on date at second row and fifth column
	{
		selectDate = driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/table/tbody/tr[4]/td[4]/a"));
		return selectDate;
	}
	
	public static WebElement selectLastMonth(WebDriver driver)				//Method to click on arrow which shows last month
	{
		lastMonth = driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/div/a[1]/span"));
		return lastMonth;
	}
	
	public static WebElement loadNextPage(WebDriver driver)					//Method to search the forward arrow to load next page records 
	{
		nextPage = driver.findElement(By.xpath("//div[@id='grid']/div[5]/a[3]/span"));
		return nextPage;
	}
	
	public static WebElement clickStatutoryChecklist(WebDriver driver)		//Method to click on Statutory Checklist value
	{
		statutoryChecklist = driver.findElement(By.xpath("//div[@id='ContentPlaceHolder1_divPerformerChecklistPREOcount']"));
		return statutoryChecklist;
	}
	
	public static List<WebElement> readCalendarCompType(WebDriver driver)			//Method to check type compliance displayed
	{
		elementsList = driver.findElements(By.xpath("//*[@id='grid']/div[3]/table/tbody/tr/td[3]"));
		return elementsList;
	}
	
	public static List<WebElement> readCalendarCompType1(WebDriver driver)			//Method to check type compliance displayed
	{
		elementsList = driver.findElements(By.xpath("//*[@id='grid']/div[3]/table/tbody/tr/td[1]"));
		return elementsList;
	}
	
	public static List<WebElement> clickCheckboxesList(WebDriver driver) 	//Method to get list of action buttons on web page
	{
	//	checkboxesList = driver.findElements(By.xpath("//input[@id='sel_chkbx']"));
		//*[@id='sel_chkbx']
		checkboxesList = driver.findElements(By.xpath("//*[@id='sel_chkbx']"));
		return checkboxesList;
	}
	
	public static WebElement clickSubmit(WebDriver driver)					//Method to search Submit button.
	{
		checklistSubmit = driver.findElement(By.xpath("//*[@id='dvbtnSubmit']"));
		return checklistSubmit;
	}
	
	public static WebElement clickCheckboxesNotApplicable(WebDriver driver)	//Method to click on 'Not Applicable' button after clicking multiple check boxes. (Independent of Statutory or Internal)
	{
		checklistNotApplicable = driver.findElement(By.xpath("//button[@id='dvbtnNotApplicableSubmit']"));
		return checklistNotApplicable;
	}
	
	public static List<WebElement> clickStatutoryChecklistAction(WebDriver driver)	//Method to click on Action button. (Independent of Statutory or Internal)
	{
		//elementsList = driver.findElements(By.xpath("//a[@class='k-button k-button-icontext ob-edit k-grid-edit3']"));
		elementsList = driver.findElements(By.xpath("//*[@id='grid']/div[4]/table/tbody/tr/td[20]/a[1]"));
		return elementsList;
	}
	
	public static WebElement clickActionNotApplicable(WebDriver driver)	//Method to click on 'Not Applicable' button through action button.
	{
		actionNotApplicable = driver.findElement(By.xpath("//input[@value='Not Applicable']"));
		return actionNotApplicable;
	}
	
	public static WebElement statutoryComplianceDoc(WebDriver driver)
	{
		checkbox = driver.findElement(By.xpath("//input[@name = 'TxtChecklistDocument']"));
		return checkbox;
	}
	
	public static WebElement statutoryAddLinkButton(WebDriver driver)
	{
		checkbox = driver.findElement(By.xpath("//input[@name = 'Uploadlingchecklistfile']"));
		return checkbox;
	}
	
	public static WebElement clickInternalChecklist(WebDriver driver)	//Method to click on Internal Checklist value
	{
		internalChecklist = driver.findElement(By.xpath("//div[@id='ContentPlaceHolder1_divPerformerChecklistInternalPREOcount']"));
		return internalChecklist;
	}
	//div[@class = 'k-multiselect-wrap k-floatwrap']
	public static WebElement readActivatedEvents(WebDriver driver)		//Method to click on Internal Checklist value
	{
		activatedEvents = driver.findElement(By.xpath("//div[@id='ContentPlaceHolder1_divActivatedEventPREOcount']"));
		return activatedEvents;
	}
	
	public static WebElement clickAssignedEvents(WebDriver driver)		//Method to click on Internal Checklist value
	{
		assignedEvents = driver.findElement(By.xpath("//div[@id='ContentPlaceHolder1_divAssignedEventPREOcount']"));
		return assignedEvents;
	}
	
	public static List<WebElement> clickCheckBoxes(WebDriver driver)	//Method to search multiple check boxes for CheckList count
	{
		checkboxes = driver.findElements(By.xpath("//label[@class='k-checkbox-label k-no-text']"));
		return checkboxes;
	}
	
	public static List<WebElement> clickTextBoxes(WebDriver driver)		//Method to search multiple text boxes for CheckList count
	{
		textboxes = driver.findElements(By.xpath("//input[@type='text'][@class='k-textbox']"));
		return textboxes;
	}
	
	public static List<WebElement> clickDates(WebDriver driver)			//Method to search multiple date textboxes
	{
		//dates = driver.findElements(By.xpath("//input[@type='date'][@class='k-textbox']"));
		dates = driver.findElements(By.xpath("//*[@id='grid']/div[3]/table/tbody/tr/td[6]/input"));
	//	dates = driver.findElements(By.xpath("//*[@id='grid']/div[3]/table/tbody/tr/td[6]"));
		return dates;
	}
	
	public static List<WebElement> viewEvent(WebDriver driver)			//Method to search multuple 'View' button image
	{
		viewEvent = driver.findElements(By.xpath("//a[@class = 'k-button k-button-icontext ob-overview k-grid-edit1']"));
		return viewEvent;
	}//label[@class='k-checkbox-label k-no-text']
	
	public static WebElement closeViewEvent(WebDriver driver)			//Method to search 'Back' button of opened view.
	{
		closeView = driver.findElement(By.xpath("//a[@class='k-button k-bare k-button-icon k-window-action']"));
		return closeView;
	}
	
	public static List<WebElement> clickActivate(WebDriver driver)		//Method to search multiple Activate buttons.
	{
		activate = driver.findElements(By.xpath("//a[@class='k-button k-button-icontext ob-edit k-grid-edit']"));
		return activate;
	}
	
	public static WebElement clickSave(WebDriver driver)				//Method to search Save button.
	{
		save = driver.findElement(By.xpath("//input[@id='ContentPlaceHolder1_ucEventDashboards_btnAllSavechk']"));
		return save;
	}
	
	public static WebElement clickAssigneCompliance(WebDriver driver)	//Method to search 'Assign Compliance' button
	{
		msgElement = driver.findElement(By.xpath("//input[@value='Assign Compliance']"));
		return msgElement;
	}
	
	public static WebElement selectPerformer(WebDriver driver)			//Method to search dropdown to select performer user
	{
		performer = driver.findElement(By.xpath("//select[@id='ContentPlaceHolder1_ucEventDashboards_ddlFilterPerformer']"));
		return performer;
	}
	
	public static WebElement selectReviewer(WebDriver driver)			//Method to search dropdown to select reviewer user
	{
		reviewer = driver.findElement(By.xpath("//select[@id='ContentPlaceHolder1_ucEventDashboards_ddlFilterReviewer']"));
		return reviewer;
	}
	
	public static WebElement selectApprover(WebDriver driver)			//Method to search dropdown to select approver user
	{
		approver = driver.findElement(By.xpath("//select[@id='ContentPlaceHolder1_ucEventDashboards_ddlFilterApprover']"));
		return approver;
	}
	
	public static WebElement selectDate1(WebDriver driver)				//Method to search date text box.
	{
		date = driver.findElement(By.xpath("//input[@id='ContentPlaceHolder1_ucEventDashboards_tbxStartDate']"));
		return date;
	}
	
	public static WebElement selectEvent(WebDriver driver)				//Method to search dropdown to select Event
	{
		date = driver.findElement(By.xpath("//select[@id='ContentPlaceHolder1_ucEventDashboards_ddlEvent']"));
		return date;
	}
	
	public static WebElement clickSave1(WebDriver driver)				//Method to search Save button.
	{
		save = driver.findElement(By.xpath("//input[@id='ContentPlaceHolder1_ucEventDashboards_SaveComplianceList']"));
		return save;
	}
	
	public static WebElement clickCheckbox(WebDriver driver)			//Method to search checkbox of Select all.
	{
		checkbox = driver.findElement(By.xpath("//input[@id='ContentPlaceHolder1_ucEventDashboards_gvComplianceListAssign_chkComplianceCheck']"));
		return checkbox;
	}
	
	public static WebElement clickMyWorkspace(WebDriver driver)			//Searching 'My Workspace' element.
	{
		performer = driver.findElement(By.xpath("//*[@id='leftworkspacemenuLic']"));	//*[@onclick='CheckProduct();']
		return performer;
	}
	
	public static WebElement clickMyWorkspace1(WebDriver driver)			//Searching 'My Workspace' element.
	{
		performer = driver.findElement(By.xpath("//*[@id='leftworkspacemenuLic']/a/span[1]"));
	//	performer = driver.findElement(By.xpath("//*[@onclick='CheckProduct()']"));	//*[@onclick='CheckProduct();']
		return performer;
	}//*[@id="leftworkspacemenuLic"]/a/span[1]
	
	public static WebElement clickCompliance(WebDriver driver)			//Searching Compliance element.
	{
		performer = driver.findElement(By.xpath("//*[@id='LiComplist']"));
		return performer;
	}
	
	public static WebElement clickUserDropDown(WebDriver driver)		//Searching dropdown to select User
	{
		performer = driver.findElement(By.xpath("(//*[@class='k-widget k-dropdown k-header'])[1]"));
		return performer;
	}
	
	public static WebElement clickPerformer(WebDriver driver)			//Selecting performer in User Dropdown
	{
		performer = driver.findElement(By.xpath("//*[@id='dropdownlistUserRole_listbox']/li[1]"));
		return performer;
	}
	
	public static WebElement clickReviewer(WebDriver driver)			//Selecting performer in User Dropdown
	{
		performer = driver.findElement(By.xpath("//*[@id='dropdownlistUserRole_listbox']/li[2]"));
		return performer;
	}
	
	public static WebElement selectType(WebDriver driver)			//Searching Overdue type dropdown
	{
		performer = driver.findElement(By.xpath("(//*[@class='k-widget k-dropdown k-header'])[2]"));
		return performer;
	}
	//*[@class='k-list k-reset'])[1]/li[1]
	public static WebElement clickStatutory(WebDriver driver)			//Searching 'Statutory' sub menu of dropdown
	{
		performer = driver.findElement(By.xpath("(//*[@class='k-list k-reset'])[1]/li[1]"));
		return performer;
	}
	
	public static WebElement clickInternal(WebDriver driver)			//Searching 'Statutory' sub menu of dropdown
	{
		performer = driver.findElement(By.xpath("(//*[@class='k-list k-reset'])[1]/li[2]"));
		return performer;
	}//*[@class='k-list k-reset'])[1]/li[2]
	
	public static WebElement selectStatus(WebDriver driver)				//Searching Status dropdown
	{
		performer = driver.findElement(By.xpath("(//*[@class='k-widget k-dropdown k-header'])[3]"));
		return performer;
	}
	
	public static WebElement clickOverdue(WebDriver driver)				//Searching 'Overdue' sub menu status of dropdown
	{
		performer = driver.findElement(By.xpath("//*[@id='example']/div[1]/span[2]/span"));
	//	performer = driver.findElement(By.xpath("//*[@id='be186438-b9fc-43cb-99d5-938839dd18b1']"));				//(//*[@class='k-list-scroller'])[2]/ul/li[3]
		return performer;
	}//*[@id="example"]/div[1]/span[2]/span
	//li[@id='81b76d1a-9a00-42cb-972c-42433cb3382d']
	public static WebElement clickUser(WebDriver driver)				//Searching User dropdown.
	{
		performer = driver.findElement(By.xpath("//*[@class='k-dropdown-wrap k-state-default k-state-hover']"));
		return performer;
	}
	
	public static WebElement clickMonthDropDown(WebDriver driver)		//Searching Month dropdown
	{
		performer = driver.findElement(By.xpath("(//span[@class='k-dropdown-wrap k-state-default'])[3]"));
		return performer;
	}

	public static List<WebElement> clickCalenderAction(WebDriver driver)		//Searching action button on form near to calendar
	{
		elementsList = driver.findElements(By.xpath("//*[@class='k-button k-button-icontext ob-overview k-grid-edit2']"));
		return elementsList;
	}//*[@role='button'][@class='k-button k-button-icontext ob-overview k-grid-edit2']]
	
	public static WebElement clickMyReminder(WebDriver driver)			//Searching 'My Reminder' button
	{
		performer = driver.findElement(By.xpath("//*[@id='leftremindersmenu']"));
		return performer;
	}
	
	public static WebElement clickAddNew(WebDriver driver)				//Searching 'Add New' button to add reminder
	{
		performer = driver.findElement(By.xpath("//*[@value='Add New']"));
		return performer;
	}
	
	public static WebElement clickAddNewReminder(WebDriver driver)		//Searching 'Add New' button to add reminder
	{
		performer = driver.findElement(By.xpath("//*[@id = 'Addnew']"));
		return performer;
	}
	
	public static WebElement selectComplianceType(WebDriver driver)		//Searching 'Compliance Type' drop down
	{
		performer = driver.findElement(By.xpath("//select[@id='ContentPlaceHolder1_ddlComType']"));
		return performer;
	}
	
	public static WebElement selectComplianceType1(WebDriver driver)	//Searching 'Compliance Type' drop down
	{
		performer = driver.findElement(By.xpath("//div[@id='ddlComType_chosen']"));
		return performer;
	}
	
	public static WebElement selectInternalType(WebDriver driver)		//Searching 'Compliance Type' drop down
	{
		performer = driver.findElement(By.xpath("//*[@id='ddlComType_chosen']/div/ul/li[2]"));
		return performer;
	}
	
	public static WebElement selectLocation(WebDriver driver)			//Searching 'Location' drop down
	{
		performer = driver.findElement(By.xpath("//select[@id='ContentPlaceHolder1_ddlBranch']"));
		return performer;
	}
	
	public static WebElement clickLocation(WebDriver driver)			//Searching 'Location' drop down
	{
		performer = driver.findElement(By.xpath("//div[@id='ddlBranch_chosen']"));
		return performer;
	}
	
	public static List<WebElement> selectLocation1(WebDriver driver)
	{
		elementsList = driver.findElements(By.xpath("//*[@id='ddlBranch_chosen']/div/ul/li"));
		return elementsList;
	}
	
	public static WebElement selectCompliance(WebDriver driver)			//Searching 'Compliance' drop down
	{
		performer = driver.findElement(By.xpath("//select[@id='ContentPlaceHolder1_ddlCompliance']"));
		return performer;
	}
	
	public static WebElement clickCompliance1(WebDriver driver)			//Searching 'Compliance' drop down
	{
		performer = driver.findElement(By.xpath("//div[@id = 'ddlCompliance_chosen']"));
		return performer;
	}
	
	public static List<WebElement> selectCompliance1(WebDriver driver)			//Searching 'Compliance' drop down
	{
		elementsList = driver.findElements(By.xpath("//div[@id = 'ddlCompliance_chosen']/div/ul/li"));
		return elementsList;
	}
	
	public static WebElement selectRole(WebDriver driver)				//Searching 'Role' drop down
	{
		performer = driver.findElement(By.xpath("//select[@id='ContentPlaceHolder1_ddlRole']"));
		return performer;
	}
	
	public static WebElement clickRole(WebDriver driver)
	{
		performer = driver.findElement(By.xpath("//div[@id = 'ddlRole_chosen']"));
		return performer;
	}
	
	public static List<WebElement> selectRole1(WebDriver driver)
	{
		elementsList = driver.findElements(By.xpath("//div[@id = 'ddlRole_chosen']/div/ul/li"));
		return elementsList;
	}
	
	public static WebElement clickDate(WebDriver driver)				//Searching 'Date' input box
	{
		performer = driver.findElement(By.xpath("//input[@id='ContentPlaceHolder1_tbxDate']"));
		return performer;
	}
	
	public static WebElement clickSaveButton(WebDriver driver)			//Searching 'Compliance Type' drop down
	{
		performer = driver.findElement(By.xpath("//input[@value='Save']"));
		return performer;
	}
		
	public static WebElement selectNextMonth(WebDriver driver)			//Method to click on arrow which shows last month
	{
		lastMonth = driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/div/a[2]/span"));
		return lastMonth;
	}
	
	public static WebElement readReminder(WebDriver driver)				//Searching total reminders count
	{
		performer = driver.findElement(By.xpath("//*[@id='ContentPlaceHolder1_lblTotalRecord']"));
		return performer;
	}
	
	public static WebElement readReminder1(WebDriver driver)				//Searching total reminders count
	{
		performer = driver.findElement(By.xpath("//*[@id='ContentPlaceHolder1_lblTotalRecordTask']"));
		return performer;
	}
	
	public static WebElement readReminderMsg(WebDriver driver)			//Searching after Save Message in My Reminders
	{
		performer = driver.findElement(By.xpath("//*[@id='ContentPlaceHolder1_ValidationSummary1']/ul/li"));
		return performer;
	}
	
	public static WebElement readReminderMsg1(WebDriver driver)			//Searching after Save Message in My Reminders
	{
		performer = driver.findElement(By.xpath("//*[@id = 'ValidationSummary1']/ul/li"));
		return performer;
	}
	
	public static WebElement closeReminder(WebDriver driver)			//Method to close compliance popup.
	{
		close = driver.findElement(By.xpath("//*[@class='close']"));
		return close;
	}
	
	public static WebElement selectInternal(WebDriver driver)			//Searching 'Internal' compliance for My Reminders
	{
		performer = driver.findElement(By.xpath("//*[@id='ContentPlaceHolder1_ddlDocType']"));
		return performer;
	}
	
	public static WebElement clickComplianceType(WebDriver driver)
	{
		performer = driver.findElement(By.xpath("(//*[@class='k-multiselect-wrap k-floatwrap'])[2]"));
		return performer;
	}
	
	public static WebElement clickInternalCheckbox(WebDriver driver)
	{
		performer = driver.findElement(By.xpath("(//*[@class='k-checkbox-wrapper'])[2]"));
		return performer;
	}
	
	public static WebElement clickStatutoryCheckbox(WebDriver driver)
	{
		performer = driver.findElement(By.xpath("(//*[@class='k-checkbox-wrapper'])[1]"));
		return performer;
	}
	
	public static WebElement clickMyDocuments(WebDriver driver)			//Searching 'My Documents'
	{
		performer = driver.findElement(By.xpath("//*[@id='leftdocumentsmenu']"));
		return performer;
	}
	
	public static WebElement clickCriticalDocuments(WebDriver driver)	//Searching 'Critical Document' under My Documents
	{
		performer = driver.findElement(By.xpath("//*[@id='DocumentShareListNew']"));
		return performer;
	}
	
	public static WebElement clickNew(WebDriver driver)					//Searching '+New' folder element
	{
		performer = driver.findElement(By.xpath("//*[@id='menu1']"));
		return performer;
	}
	
	public static WebElement clickNewFolder(WebDriver driver)			//Searching 'New Folder' after clicking on '+New'
	{
		performer = driver.findElement(By.xpath("//*[@id='ContentPlaceHolder1_lnkAddNewFolder']"));
		return performer;
	}
	
	public static WebElement clickIsUniversal(WebDriver driver)			//Searching Input box to write folder name
	{
		performer = driver.findElement(By.xpath("//input[@id = 'ContentPlaceHolder1_chkIsUnivers']"));
		return performer;
	}
	
	public static WebElement writeFolderName(WebDriver driver)			//Searching Input box to write folder name
	{
		performer = driver.findElement(By.xpath("//*[@id='ContentPlaceHolder1_txtFolderName']"));
		return performer;
	}
	
	public static WebElement clickCreate(WebDriver driver)				//Searching 'Create' button after writing folder name
	{
		performer = driver.findElement(By.xpath("//*[@id = 'ContentPlaceHolder1_btnCreateFolder1']"));
		return performer;
	}
	
	public static WebElement readFolderMsg(WebDriver driver)			//Searching Message after creating folder.
	{
		performer = driver.findElement(By.xpath("//*[@id='ContentPlaceHolder1_FolderValidation']/ul/li"));
		return performer;
	}
	
	public static WebElement closeFolderPoppup(WebDriver driver)		//Closing Create folder pop up.
	{
		performer = driver.findElement(By.xpath("//*[@id='divOpenNewFolderPopup']/div/div/div[1]/button"));
		return performer;
	}
	
	public static WebElement readFolderName(WebDriver driver)			//Searching first folder name
	{
		performer = driver.findElement(By.xpath("(//*[@align='left'])[1]"));
		return performer;
	}
	
	public static WebElement clickShareFolder(WebDriver driver)			//Searching Share Folder image
	{
		performer = driver.findElement(By.xpath("//*[@class='sharedrive']"));
		return performer;
	}
	
	public static WebElement clickPeople(WebDriver driver)				//Searching People input box 
	{
		performer = driver.findElement(By.xpath("//*[@class='multiselect dropdown-toggle btn btn-default']"));
		return performer;
	}
	
	public static WebElement clickSearchPeople(WebDriver driver)		//Searching 'Search People' input box
	{
		performer = driver.findElement(By.xpath("//*[@class='form-control multiselect-search']"));
		return performer;
	}
	
	public static WebElement clickPeopleCheckBox(WebDriver driver)		//Clicking on Checkbox in front of name
	{
		performer = driver.findElement(By.xpath("//*[@id='divOpenPermissionPopup']/div/div/div[2]/div[3]/div/span/div/ul/li[131]/a/label"));
		return performer;
	}
	
	public static WebElement clickLabel(WebDriver driver)				//Searching label to click on it.
	{
		performer = driver.findElement(By.xpath("//*[@class='modal-header-custom']"));
		return performer;
	}
	
	public static WebElement clickDone(WebDriver driver)				//Searching 'Done' button
	{
		//performer = driver.findElement(By.xpath("//*[@value='Done']"));
		performer = driver.findElement(By.xpath("//*[@id='ContentPlaceHolder1_btnPermission']"));
		return performer;
	}
	
	public static WebElement checkShared(WebDriver driver)				//Searching share file button
	{
		performer = driver.findElement(By.xpath("//*[@id='ContentPlaceHolder1_myRepeater_LnkDeletShare_0']"));
		return performer;
	}
	
	public static WebElement closeSharePoppup(WebDriver driver)			//Searching close share file poppup button.
	{
		performer = driver.findElement(By.xpath("//*[@id='divOpenPermissionPopup']/div/div/div[1]/button"));
		return performer;
	}
	
	public static WebElement clickNewFile(WebDriver driver)				//Searching New File button
	{
		performer = driver.findElement(By.xpath("//*[@id='ContentPlaceHolder1_lnkAddNewDocument']"));
		return performer;
	}
	
	public static WebElement uploadNewFile(WebDriver driver)			//Searching upload New File button
	{
		performer = driver.findElement(By.xpath("//*[@id='ContentPlaceHolder1_ContractFileUpload']"));
		return performer;
	}
	
	public static WebElement clickUploadDocument(WebDriver driver)		//Searching upload document button
	{
		performer = driver.findElement(By.xpath("//input[@id='ContentPlaceHolder1_btnUploadDoc']"));
		return performer;
	}
	
	public static WebElement clickUserRole(WebDriver driver)			//Searching 'More Actions' drop down 
	{
		performer = driver.findElement(By.xpath("(//*[@class='k-dropdown-wrap k-state-default'])[1]"));
		return performer;
	}
	
	public static List<WebElement> selectUserRole(WebDriver driver)		//Selecting third option 'Revise Compliance'
	{
		viewEvent = driver.findElements(By.xpath("//*[@id='dropdownlistUserRole-list']/div[2]/ul/li"));
		return viewEvent;
	}
	
	public static WebElement clickMoreActions(WebDriver driver)			//Searching 'More Actions' drop down 
	{
		performer = driver.findElement(By.xpath("(//*[@class='k-dropdown-wrap k-state-default'])[5]"));
		return performer;
	}//*[@id='ContentPlaceHolder1_btnAddPromotor']
	
	public static WebElement clickMoreActions1(WebDriver driver)			//Searching 'More Actions' drop down 
	{
		performer = driver.findElement(By.xpath("(//*[@class='k-dropdown-wrap k-state-default'])[4]"));
		return performer;
	}
	
	public static List<WebElement> selectAction(WebDriver driver)		//Selecting third option 'Revise Compliance'
	{
		viewEvent = driver.findElements(By.xpath("//*[@id='dropdownlistMoreLink-list']/div[2]/ul/li"));
		return viewEvent;
	}
	
	public static WebElement clickAction(WebDriver driver)				//Searching first action button
	{
		performer = driver.findElement(By.xpath("(//a[contains(@id,'ContentPlaceHolder1_grdRviseCompliances_lnkReviseCompliances')])[1]"));
		return performer;
	}
	
	public static WebElement clickRemark(WebDriver driver)				//Searching 'Remark' textarea.
	{
		performer = driver.findElement(By.xpath("//*[@id='ContentPlaceHolder1_tbxRemarks']"));
		return performer;
	}
	
	public static WebElement upload1(WebDriver driver)					//Searching upload button in action
	{
		performer = driver.findElement(By.xpath("//*[@id='ContentPlaceHolder1_fuSampleFile']"));
		return performer;
	}
	
	public static WebElement clickInterest(WebDriver driver)			//Searching Interest input box
	{
		performer = driver.findElement(By.xpath("//*[@id='ContentPlaceHolder1_txtInterest']"));
		return performer;
	}
	
	public static WebElement clickPenalty(WebDriver driver)				//Searching Penalty input box
	{
		performer = driver.findElement(By.xpath("//*[@id='ContentPlaceHolder1_txtPenalty']"));
		return performer;
	}
	
	public static WebElement clickTaskAction(WebDriver driver)			//Clicking on action button (edit image)
	{
		performer = driver.findElement(By.xpath("(//input[contains(@id,'ContentPlaceHolder1_grdTaskPerformer_btnChangeStatus')])[1]"));
		return performer;
	}
	
	public static WebElement TaskUpload(WebDriver driver)				//Searching Upload button
	{
		performer = driver.findElement(By.xpath("//input[@id='fuTaskDoc']"));
		return performer;
	}
	
	public static WebElement clickStatutoryRejected(WebDriver driver)	//Searching Statutory Reject value to click 
	{
		performer = driver.findElement(By.xpath("//*[@id='ContentPlaceHolder1_divPerformerRejectedPREOcount']"));
		return performer;
	}
	
	public static WebElement clickInternalRejected(WebDriver driver)	//Searching Internal Reject value to click
	{
		performer = driver.findElement(By.xpath("//*[@id='ContentPlaceHolder1_divPerformerRejectedInternalPREOcount']"));
		return performer;
	}
	
	public static WebElement clickInternalRemark(WebDriver driver)		//Searching Remark text area.
	{
		performer = driver.findElement(By.xpath("//textarea[@id='tbxRemarks2']"));
		return performer;
	}
	
	public static WebElement clickMoreLink(WebDriver driver)			//Searching 'More Link' button
	{
		performer = driver.findElement(By.xpath("//*[@id='ContentPlaceHolder1_btnAddTaskDetails']"));
		return performer;
	}
	
	public static WebElement clickTaskDetails(WebDriver driver)			//Searching 'Task Details' button
	{
		performer = driver.findElement(By.xpath("//*[@id='ContentPlaceHolder1_btnAddTask']"));
		return performer;
	}
	
	public static WebElement clickAddNew1(WebDriver driver)				//Searching 'Add New' button.
	{
		performer = driver.findElement(By.xpath("//*[@id='ContentPlaceHolder1_btnAddPromotor']"));
		return performer;
	}
	
	public static WebElement clickActFilter(WebDriver driver)			//Searching 'Act Filter' drop down.
	{
		performer = driver.findElement(By.xpath("//*[@id='ContentPlaceHolder1_ddlActTask']"));
		return performer;                       
	}
	
	public static WebElement clickComplianceDropDown(WebDriver driver)	//Searching 'Compliance' drop down
	{
		performer = driver.findElement(By.xpath("//*[@id='ContentPlaceHolder1_ddlCompliance']"));
		return performer;
	}
	
	public static WebElement clickTaskTitle(WebDriver driver)			//Searching 'Task Title' text box
	{
		performer = driver.findElement(By.xpath("//*[@id='ContentPlaceHolder1_txtTaskTitle']"));
		return performer;
	}
	
	public static WebElement clickDescription(WebDriver driver)			//Searching 'Description' TextArea
	{
		performer = driver.findElement(By.xpath("//*[@id='ContentPlaceHolder1_txtDescription']"));
		return performer;
	}
	
	public static WebElement clickDueDay(WebDriver driver)				//Searching Due Day text box
	{
		performer = driver.findElement(By.xpath("//*[@id='ContentPlaceHolder1_txtDueDays']"));
		return performer;
	}
	
	public static WebElement clickTaskType(WebDriver driver)			//Searching Task Type Drop Down
	{
		performer = driver.findElement(By.xpath("//*[@id='ContentPlaceHolder1_ddlAllTaskType']"));
		return performer;
	}
	
	public static WebElement clickSubTaskType(WebDriver driver)			//Searching Sub Task Type Drop Down
	{
		performer = driver.findElement(By.xpath("//*[@id='ContentPlaceHolder1_ddlSubTaskType']"));
		return performer;
	}//*[@id="ContentPlaceHolder1_ddlSubTaskType"]
	
	public static WebElement clickIsAfter(WebDriver driver)				//Searching Is After/Before drop down
	{
		performer = driver.findElement(By.xpath("//*[@id='ContentPlaceHolder1_ddlIsAfter']"));
		return performer;
	}
	
	public static WebElement clickConditionCheckbox(WebDriver driver)	//Searching Condition CheckBox 
	{
		performer = driver.findElement(By.xpath("//*[@id='ContentPlaceHolder1_ChkIsTaskConditional']"));
		return performer;
	}
	
	public static WebElement clickConditionalMessage(WebDriver driver)	//Searching Condition CheckBox 
	{
		performer = driver.findElement(By.xpath("//*[@id='ContentPlaceHolder1_TxtConditionalMessage']"));
		return performer;
	}
	
	public static WebElement clickYesMsg(WebDriver driver)				//Searching Yes Message box
	{
		performer = driver.findElement(By.xpath("//*[@id='ContentPlaceHolder1_txtYesMessage']"));
		return performer;
	}
	
	public static WebElement clickNoMsg(WebDriver driver)				//Searching No Message box
	{
		performer = driver.findElement(By.xpath("//*[@id='ContentPlaceHolder1_txtNoMessage']"));
		return performer;
	}
	
	public static WebElement SampleFormUpload(WebDriver driver)
	{
		performer = driver.findElement(By.xpath("//*[@id='ContentPlaceHolder1_fuSampleFile']"));
		return performer;
	}
	
	public static WebElement taskSavedMsg(WebDriver driver)				//Searching element of Successful Save of task.
	{
		performer = driver.findElement(By.xpath("//*[@id='ContentPlaceHolder1_ctl04']/ul/li"));
		
		return performer;
	}
	
	public static WebElement taskSavedMsg1(WebDriver driver)				//Searching element of Successful Save of task.
	{
		performer = driver.findElement(By.xpath("//*[@id='ContentPlaceHolder1_ctl03']/ul/li"));
		
		return performer;
	}
	
	public static WebElement closeUpdateTasks(WebDriver driver)			//Searching cross to close the form
	{
		performer = driver.findElement(By.xpath("//*[@id='divTaskDetailsDialog']/div/div/div[1]/button"));
		return performer;
	}
	
	public static WebElement closeUpdateTasks1(WebDriver driver)			//Searching cross to close the form
	{
		performer = driver.findElement(By.xpath("//*[@id='divAssignmentDetailsDialog']/div/div/div[1]/button"));
		return performer;
	}
	public static List<WebElement> deleteTask(WebDriver driver)			//Searching element to delete the added task 
	{
		elementsList = driver.findElements(By.xpath("//a[contains(@id,'ContentPlaceHolder1_grdTask_lbtDelete')]"));
		return elementsList;
	}
	
	public static WebElement deleteMsg(WebDriver driver)				//Searching the msg element of Successful deletion
	{
		performer = driver.findElement(By.xpath("//*[@id='ContentPlaceHolder1_ctl01']"));
		return performer;
	}
	
	public static List<WebElement> clickSubTask(WebDriver driver)		//Searching all subtask elements.
	{
		elementsList = driver.findElements(By.linkText("sub-tasks"));
		return elementsList;
	}
	
	public static WebElement clickAddNew2(WebDriver driver)				//Searching add new in Sub Task
	{
		//performer = driver.findElement(By.xpath("//*[@id='ContentPlaceHolder1_btnAddSubTask']"));
		performer = driver.findElement(By.xpath("//a[@id='ContentPlaceHolder1_btnAddSubTask']"));
		return performer;                        //*[@id="ContentPlaceHolder1_btnAddSubTask"]
	}
	//*[@id='ContentPlaceHolder1_grdTaskPerformer']
	public static WebElement selectAllCheckbox1(WebDriver driver)		//Searching 'Select All' checkbox in sub task
	{
		performer = driver.findElement(By.xpath("//*[@id='ContentPlaceHolder1_rptTask_TaskSelectAll']"));
		return performer;
	}
	
	public static WebElement OK(WebDriver driver)						//Searching OK button
	{
		performer = driver.findElement(By.xpath("//*[@value='Ok']"));
		return performer;
	}
	
	public static WebElement clickTaskMapping(WebDriver driver)			//Searching 'Task Mapping' text box in Sub Task.
	{
		performer = driver.findElement(By.xpath("//*[@value='< Select Task >']"));
		return performer;
	}
	
	public static List<WebElement> clickAssignTask(WebDriver driver)	//Searching all Assign task buttons.
	{
		elementsList = driver.findElements(By.xpath("//a[contains(@id,'ContentPlaceHolder1_grdTask_LinkButton3')]"));
		return elementsList;
	}
	
	public static WebElement clickComplianceLocation(WebDriver driver)	//Searching Compliance Location text box
	{
		performer = driver.findElement(By.xpath("//*[@id='ContentPlaceHolder1_tbxFilterLocation']"));
		return performer;
	}
	
	public static WebElement clickReportingLocation(WebDriver driver)	//Searching Reporting Location text box
	{
		performer = driver.findElement(By.xpath("//*[@id='ContentPlaceHolder1_tbxFilterLocationTask']"));
		return performer;
	}
	
	public static List<WebElement> clickSubLoacations(WebDriver driver)	//Searching Sub Loactions
	{
		elementsList = driver.findElements(By.xpath("//a[contains(@id,'ContentPlaceHolder1_tvFilterLocationTaskt')]"));
		return elementsList;
	}
	
	public static WebElement clickStartDate(WebDriver driver)			//Searching Start date box
	{
		performer = driver.findElement(By.xpath("//*[@id='ContentPlaceHolder1_tbxStartDate']"));
		return performer;
	}
	
	public static WebElement clickStartDate1(WebDriver driver)			//Searching Start date box
	{
		performer = driver.findElement(By.xpath("//*[@id='ContentPlaceHolder1_txtStartDate']"));
		return performer;
	}
	
	public static WebElement clickEndDate(WebDriver driver)				//Searching Start date box
	{
		performer = driver.findElement(By.xpath("//*[@id='ContentPlaceHolder1_txtEndDate']"));
		return performer;
	}
	
	public static WebElement clickSelectPerformer(WebDriver driver)		//Searching Performers drop down
	{
		performer = driver.findElement(By.xpath("//div[contains(@id,'ContentPlaceHolder1_ddlPerformer')]"));
		return performer;
	}
	
	public static WebElement clickSelectReviewer(WebDriver driver)		//Searching reviewers drop down
	{
		performer = driver.findElement(By.xpath("//div[contains(@id,'ContentPlaceHolder1_ddlReviewer')]"));
		return performer;
	}
	
	public static WebElement checkRecordsTable(WebDriver driver)		//Searching assigned data table
	{
		performer = driver.findElement(By.xpath("//*[@class='clsROWgrid']"));
		return performer;
	}
	
	public static WebElement checkRecordsTable1(WebDriver driver)		//Searching assigned data table
	{
		performer = driver.findElement(By.xpath("//*[@id='ContentPlaceHolder1_GrdAssigment']/tbody/tr[2]"));
		return performer;
	}
	
	public static WebElement clickSearchedUser1(WebDriver driver)		//Searching searched user
	{
		performer = driver.findElement(By.xpath("(//*[@data-option-array-index='1'])[1]"));
		return performer;
	}
	
	public static WebElement clickSearchedUser2(WebDriver driver)		//Searching searched user
	{
		performer = driver.findElement(By.xpath("(//*[@data-option-array-index='1'])[2]"));
		return performer;
	}
	
	public static WebElement clickSaveButton1(WebDriver driver)			//Searching Save button
	{
		performer = driver.findElement(By.xpath("//input[@id='ContentPlaceHolder1_btnSaveTaskAssignment']"));
		return performer;
	}
	
	public static WebElement SelectCheckBox(WebDriver driver)			//
	{
		performer = driver.findElement(By.xpath("//*[@id='ContentPlaceHolder1_tvFilterLocationn2CheckBox']"));
		return performer;
	}
	
	public static WebElement clickMainTaskLink(WebDriver driver)		//Searching Main Task link to go back from Sub Task
	{
		performer = driver.findElement(By.xpath("//*[@id='ContentPlaceHolder1_dlBreadcrumb']"));
		return performer;
	}
	
	public static WebElement clickInternaRadioButton(WebDriver driver)	//Searching radio button for Internal Tasks
	{
		performer = driver.findElement(By.xpath("//*[@id='ContentPlaceHolder1_rdoInternal']"));
		return performer;
	}
	
	public static WebElement clickInternalCompliance(WebDriver driver)	//
	{
		performer = driver.findElement(By.xpath("//*[@id='ContentPlaceHolder1_ddlInternalCompliance']"));
		return performer;
	}
	
	public static WebElement selectAllCheckbox(WebDriver driver)		//Searching 'Select All' checkbox
	{
		performer = driver.findElement(By.xpath("//*[@id='grdGstMappedCompliance_chkComplianceAll']"));
		return performer;
	}
	
	public static WebElement selectNewPerformer(WebDriver driver)		//Searching new Performer drop down
	{
		performer = driver.findElement(By.xpath("//*[@id='ContentPlaceHolder1_ddlNewPerformerUsers']"));
		return performer;
	}
	
	public static WebElement selectNewReviewer(WebDriver driver)		//Searching new Reviewer drop down
	{
		performer = driver.findElement(By.xpath("//*[@id='ContentPlaceHolder1_ddlNewReviewerUsers']"));
		return performer;
	}
	
	public static WebElement selectNewEventOwner(WebDriver driver)		//Searching new Event Owner drop down
	{
		performer = driver.findElement(By.xpath("//*[@id='ContentPlaceHolder1_ddlNewEventOwnerUsers']"));
		return performer;
	}
	
	public static WebElement progressGIF(WebDriver driver)				//Searching Progress GIF Loading image
	{
		performer = driver.findElement(By.xpath("//*[@id='imgUpdateProgress']"));
		return performer;
	}
	
	public static WebElement readMsgLeave(WebDriver driver)				//Searching Message after Save
	{
		performer = driver.findElement(By.xpath("//*[@id='ContentPlaceHolder1_VDS']"));
		return performer;
	}
	
	public static WebElement closeLeave(WebDriver driver)				//Searching cross to close window
	{
		performer = driver.findElement(By.xpath("//*[@id='divLeaveDialog']/div/div/div[1]/button"));
		return performer;
	}
	
	public static WebElement clickUpcomingStatutory(WebDriver driver)	//
	{
		performer = driver.findElement(By.xpath("//*[@id='ContentPlaceHolder1_divupcomingPREOcount']"));
		return performer;
	}
	
	public static WebElement clickUpcomingInternal(WebDriver driver)	//
	{
		performer = driver.findElement(By.xpath("//*[@id='ContentPlaceHolder1_divupcomingInternalPREOcount']"));
		return performer;
	}
	
	public static WebElement checkTable(WebDriver driver)				//Searching table
	{
		performer = driver.findElement(By.xpath("//*[@class='k-grid-content k-auto-scrollable']"));
		return performer;
	}
	
	public static List<WebElement> readStatus(WebDriver driver)			//Searching all Upcoming status
	{
		elementsList = driver.findElements(By.xpath("//*[@id='grid']/div[4]/table/tbody/tr/td[8]"));
		return elementsList;
	}
	
	public static WebElement fileUploadUpcoming(WebDriver driver)		//Method to search Choose File button. 
	{
		upload = driver.findElement(By.xpath("//*[@id='FileUpload1']"));
		return upload;
	}
	
	public static WebElement clickShow(WebDriver driver)					//
	{
		performer = driver.findElement(By.xpath("//*[@id='ContentPlaceHolder1_ddlPageSize']"));
		return performer;
	}
	
	public static WebElement clickShow1(WebDriver driver)					//
	{
		performer = driver.findElement(By.xpath("//*[@id='ContentPlaceHolder1_ddlPageSizeTask']"));
		return performer;
	}//*[@id='ContentPlaceHolder1_grdTaskPerformer']
	
	public static WebElement ClickTaskCreation(WebDriver driver)		//
	{
		performer = driver.findElement(By.xpath("//*[@id='ContentPlaceHolder1_liTaskCreation']"));
		return performer;
	}
	
	public static WebElement clickMyWorkspace2(WebDriver driver)					//
	{
		performer = driver.findElement(By.xpath("//*[@id = 'leftworkspacemenuLic']/a"));
		return performer;
	}
	
	public static WebElement ComplianceDocumentLink	(WebDriver driver)				//
	{
		performer = driver.findElement(By.xpath("//*[@id = 'ContentPlaceHolder1_TxtComplianceDocument']"));
		return performer;
	}
	
	public static WebElement PenaltyRemark(WebDriver driver)					//
	{
		performer = driver.findElement(By.xpath("//*[@id = 'ContentPlaceHolder1_txtremark']"));
		return performer;
	}
	
	public static WebElement clickPTUpcomingStatutory(WebDriver driver)	//
	{
		performer = driver.findElement(By.xpath("//*[@id='ContentPlaceHolder1_DivPerformerUpcomingStat']"));
		return performer;
	}
	
	public static WebElement clickPTUpcomingInternal(WebDriver driver)	//
	{
		performer = driver.findElement(By.xpath("//*[@id='ContentPlaceHolder1_DivPerformerUpcomingInt']"));
		return performer;
	}
	
	public static WebElement clickPTOverdueStatutory(WebDriver driver)	//
	{
		performer = driver.findElement(By.xpath("//*[@id='ContentPlaceHolder1_DivPerformerOverdueStat']"));
		return performer;
	}
	
	public static WebElement clickPTOverdueInternal(WebDriver driver)	//
	{
		performer = driver.findElement(By.xpath("//*[@id='ContentPlaceHolder1_DivPerformerOverdueInt']"));
		return performer;
	}
	
	public static WebElement clickPTRejectedStatutory(WebDriver driver)	//
	{
		performer = driver.findElement(By.xpath("//*[@id='ContentPlaceHolder1_DivPerformerRejectedStat']"));
		return performer;
	}
	
	public static WebElement clickPTRejectedInternal(WebDriver driver)	//
	{
		performer = driver.findElement(By.xpath("//*[@id='ContentPlaceHolder1_DivPerformerRejectedInt']"));
		return performer;
	}
	public static WebElement sletre(WebDriver driver)					//
	{
		performer = driver.findElement(By.xpath(""));
		return performer;
	}
	
	public static WebElement seletlere(WebDriver driver)				//
	{
		performer = driver.findElement(By.xpath(""));
		return performer;
	}
	
	public static WebElement seletre(WebDriver driver)					//
	{
		performer = driver.findElement(By.xpath(""));
		return performer;
	}
	
	public static WebElement sledtre(WebDriver driver)					//
	{
		performer = driver.findElement(By.xpath(""));
		return performer;
	}
	
	public static WebElement seletldere(WebDriver driver)				//
	{
		performer = driver.findElement(By.xpath(""));
		return performer;
	}
	
	public static WebElement seletdre(WebDriver driver)					//
	{
		performer = driver.findElement(By.xpath(""));
		return performer;
	}
	
	public static void MyReminder(WebDriver driver, ExtentTest test, String compliance) throws InterruptedException
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(30));
		
		Thread.sleep(500);
		wait.until(ExpectedConditions.elementToBeClickable(clickMyReminder(driver)));
		//Actions action = new Actions(driver);
		//action.moveToElement(clickMyReminder(driver)).click().perform();	//Clicking on 'My Reminder'
		clickMyReminder(driver).click();
		
		wait.until(ExpectedConditions.elementToBeClickable(clickComplianceType(driver)));
		clickComplianceType(driver).click();
		Thread.sleep(500);
		if(compliance.equalsIgnoreCase("Internal"))
		{
			clickInternalCheckbox(driver).click();
		}
		else
		{
			clickStatutoryCheckbox(driver).click();
		}
		
		Thread.sleep(500);
		litigationPerformer.MethodsPOM.progress(driver);
		
		Thread.sleep(500);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,1000)");
		
		wait1.until(ExpectedConditions.elementToBeClickable(CFOcountPOM.readTotalItems1(driver)));
		CFOcountPOM.readTotalItems1(driver).click();
		String item = CFOcountPOM.readTotalItems1(driver).getText();
		String[] bits = item.split(" ");							//Splitting the String
		String remindersCount = bits[bits.length - 2];				//Getting the second last word (total number of users)
		for(int i = 0; i <= 1; i++)
		{
			if(remindersCount.equalsIgnoreCase("to"))
			{
				js.executeScript("window.scrollBy(0,1000)");
				try
				{			//Waiting till the grid's second row's fifth column data gets clickable.
					wait1.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@class = 'k-grid-content k-auto-scrollable']/table/tbody/tr[2]/td[5]")));
				}
				catch(Exception e)
				{
					
				}
				item = CFOcountPOM.readTotalItems1(driver).getText();
				bits = item.split(" ");									//Splitting the String
				remindersCount = bits[bits.length - 2];
			}
			else
			{
				break;
			}
		}
		
		int count = Integer.parseInt(remindersCount);				//Reading number of records.
		Thread.sleep(500);
		js.executeScript("window.scrollBy(0,-1000)");
		Thread.sleep(500);
		clickAddNewReminder(driver).click();								//Clicking on 'Add New' button 
		
		Thread.sleep(500);
		
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("ContentPlaceHolder1_showReminderDetail"));
		wait.until(ExpectedConditions.visibilityOf(selectComplianceType1(driver)));
		
		if(compliance.equalsIgnoreCase("Internal"))
		{
			selectComplianceType1(driver).click();				//Clicking on 'Compliance Type' drop down 
			Thread.sleep(500);
			selectInternalType(driver).click();					//Clicking on 'Internal' menu.
			Thread.sleep(1500);
		}
		
		Thread.sleep(1000);
		clickLocation(driver).click();							//Clicking on 'Location' drop down
		Thread.sleep(500);
		elementsList = selectLocation1(driver);
		js.executeScript("window.scrollBy(0,1000)");
		elementsList.get(4).click();							//Selecting third menu from drop down.
		
		Thread.sleep(2000);
		wait.until(ExpectedConditions.visibilityOf(clickCompliance1(driver)));
		clickCompliance1(driver).click();						//Clicking on 'Compliance' drop down
		elementsList = selectCompliance1(driver);
		elementsList.get(1).click();							//Selecting second menu from drop down.
		
		Thread.sleep(500);
		litigationPerformer.MethodsPOM.progress(driver);
		
		Thread.sleep(500);
		clickRole(driver).click();								//Clicking on 'Role' drop down.
		elementsList = selectRole1(driver);
		elementsList.get(1).click();							//Selecting second menu from drop down.
		
		Thread.sleep(500);
		litigationPerformer.MethodsPOM.progress(driver);
		
		Thread.sleep(500);
		selectDateStatutory(driver).click();					//Clicking on 'Date' input box
		Thread.sleep(300);
		selectNextMonth(driver).click();						//Clicking on Next month arrow on calendar
		Thread.sleep(300);
		selectDate(driver).click();								//Clicking on date at second row and fourth column
		
		Thread.sleep(500);
		clickSaveButton(driver).click();						//Clicking 'Save' button.
		
		Thread.sleep(500);
		litigationPerformer.MethodsPOM.progress(driver);
		
		Thread.sleep(500);
		String actualMsg = readReminderMsg1(driver).getText();	//Reading Message after Save
		String expectedMsg1 = "Reminder Saved Sucessfully.";	//Expected message
		
		driver.switchTo().parentFrame();
		closeReminder(driver).click();							//Closing the Reminder popped window
		
		test.log(LogStatus.INFO, actualMsg);
		
		Thread.sleep(1000);
		driver.navigate().refresh();
		Thread.sleep(500);
		wait1.until(ExpectedConditions.visibilityOf(clickComplianceType(driver)));
		wait.until(ExpectedConditions.elementToBeClickable(clickComplianceType(driver)));
		clickComplianceType(driver).click();
		Thread.sleep(500);
		if(compliance.equalsIgnoreCase("Internal"))
		{
			clickInternalCheckbox(driver).click();
		}
		else
		{
			clickStatutoryCheckbox(driver).click();
		}
		
		Thread.sleep(500);
		litigationPerformer.MethodsPOM.progress(driver);
				
		js.executeScript("window.scrollBy(0,1000)");
		
		wait1.until(ExpectedConditions.elementToBeClickable(CFOcountPOM.readTotalItems1(driver)));
		CFOcountPOM.readTotalItems1(driver).click();
		item = CFOcountPOM.readTotalItems1(driver).getText();
		bits = item.split(" ");							//Splitting the String
		remindersCount = bits[bits.length - 2];				//Getting the second last word (total number of users)
		for(int i = 0; i <= 1; i++)
		{
			if(remindersCount.equalsIgnoreCase("to"))
			{
				js.executeScript("window.scrollBy(0,1000)");
				try
				{			//Waiting till the grid's second row's fifth column data gets clickable.
					wait1.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@class = 'k-grid-content k-auto-scrollable']/table/tbody/tr[2]/td[5]")));
				}
				catch(Exception e)
				{
					
				}
				item = CFOcountPOM.readTotalItems1(driver).getText();
				bits = item.split(" ");									//Splitting the String
				remindersCount = bits[bits.length - 2];
			}
			else
			{
				break;
			}
		}
		
		int count1 = Integer.parseInt(remindersCount);				//Reading number of records.
		
		if(actualMsg.equalsIgnoreCase(expectedMsg1))
		{
			if(count < count1)
			{
				test.log(LogStatus.PASS, compliance + " - Reminder count updated. Old count = "+count+" New count = "+count1);
			}
			else
			{
				test.log(LogStatus.FAIL, compliance + " - Reminder count doesn't updated. Old count = "+count+" New count = "+count1);
			}
		}
		else
		{
			if(count == count1)
			{
				test.log(LogStatus.PASS, compliance + " - Reminder count doesn't updated. Old count = "+count+" New count = "+count1);
			}
			else
			{
				test.log(LogStatus.FAIL, compliance + " - Reminder count updated. Old count = "+count+" New count = "+count1);
			}
		}
		Thread.sleep(500);
		OverduePOM.clickDashboard(driver).click();
	}
	
	public static void CriticalDocuments(WebDriver driver, ExtentTest test) throws InterruptedException
	{
		Thread.sleep(1000);
		clickMyDocuments(driver).click();					//Clicking on 'My Documents'
		
		Thread.sleep(500);
		clickCriticalDocuments(driver).click();				//Clicking on 'Critical Documents'
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='ContentPlaceHolder1_grdFolderDetail']")));	//Wating till the content table gets visible
		
		Thread.sleep(500);
		String name = readFolderName(driver).getText();		//Reading the folder name to create new folder.
		
		String folder = name+"T"; 
		
		clickNew(driver).click();							//Clicking on '+New' button.
		
		Thread.sleep(300);
		litigationPerformer.MethodsPOM.progress(driver);
		
		Thread.sleep(500);
		clickNewFolder(driver).click();						//Clicking on 'New Folder'
		
		Thread.sleep(300);
		litigationPerformer.MethodsPOM.progress(driver);
		
		Thread.sleep(300);
		clickIsUniversal(driver).click();
		
		Thread.sleep(300);
		writeFolderName(driver).sendKeys(folder);			//Writing Folder name.
		
		Thread.sleep(500);
		clickCreate(driver).click();						//Clicking on create button.
		
		Thread.sleep(500);
		litigationPerformer.MethodsPOM.progress(driver);
		
		Thread.sleep(500);
	//	String msg = driver.switchTo().alert().getText();
		//test.log(LogStatus.INFO, msg);
		Thread.sleep(300);
		//driver.switchTo().alert().accept();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//*[@align='left'])[1]")));
		name = readFolderName(driver).getText();				//Reading the folder name we had created
		
		if(folder.equalsIgnoreCase(name))
		{
			test.log(LogStatus.PASS, "Created folder '"+folder+"' displayed in the records.");
		}
		else
		{
			test.log(LogStatus.FAIL, "Created folder '"+folder+"' doesn't displayed in the records.");
		}
		
		Thread.sleep(500);
		readFolderName(driver).click();						//Clicking on folder name we had created.
		Thread.sleep(500);
		readFolderName(driver).click();						//Clicking on folder name we had created.
		
		Thread.sleep(500);
		litigationPerformer.MethodsPOM.progress(driver);
		
		Thread.sleep(500);
		wait.until(ExpectedConditions.elementToBeClickable(clickNew(driver)));
		clickNew(driver).click();							//Clicking on 'New'
		
		Thread.sleep(500);
		litigationPerformer.MethodsPOM.progress(driver);
		
		//Thread.sleep(500);
		wait.until(ExpectedConditions.elementToBeClickable(clickNewFile(driver)));
		clickNewFile(driver).click();						//CLicking on 'New File'
		
		Thread.sleep(500);
		litigationPerformer.MethodsPOM.progress(driver);
		
		Thread.sleep(500);
		String workingDir = System.getProperty("user.dir");
		uploadNewFile(driver).sendKeys("C:/March2022/PerformerPom/Reports/PerformerResults.html");	//uploading new file		
		
		Thread.sleep(500);
		wait.until(ExpectedConditions.elementToBeClickable(clickUploadDocument(driver)));
		clickUploadDocument(driver).click();				//Clicking on 'Upload Document'
		
		Thread.sleep(100);
		litigationPerformer.MethodsPOM.progress(driver);
		
		Thread.sleep(500);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//*[@align='left'])[1]")));
		if(readFolderName(driver).isDisplayed())			//Checking if file got created or not.
			test.log(LogStatus.PASS, "Uploaded file displayed.");
		else
			test.log(LogStatus.PASS, "Uploaded file does not displayed.");
		
		readFolderName(driver).click();						//Clicking on file we had uploaded.
				
		Thread.sleep(500);
		clickShareFolder(driver).click();					//Clicking on Share Folder image.
		
		Thread.sleep(500);
		litigationPerformer.MethodsPOM.progress(driver);
		
		Thread.sleep(500);
		wait.until(ExpectedConditions.elementToBeClickable(clickPeople(driver)));
		clickPeople(driver).click();						//Clicking on People drop down 
		clickSearchPeople(driver).click();					//Clicking on Search People drop down.
		
		Thread.sleep(500);
		clickSearchPeople(driver).sendKeys("amol");			//Writing user name to search for
		
		Thread.sleep(500);
		clickPeopleCheckBox(driver).click();				//Clicking on label to get out from people search box
		driver.findElement(By.xpath("//*[@id='divOpenPermissionPopup']/div/div/div[2]")).click();
		
		Thread.sleep(500);
		clickDone(driver).click();	//Clicking on 'Done' to share folder.
		Thread.sleep(500);
		driver.switchTo().alert().accept();
		
		Thread.sleep(500);
		readFolderName(driver).click();						//Clicking on file name we had uploaded.
		
		Thread.sleep(500);
		clickShareFolder(driver).click();					//Clicking on Share File image.
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='ContentPlaceHolder1_myRepeater_LnkDeletShare_0']")));	//Waiting till the share element gets visible
		
		//Thread.sleep(1000);
		if(checkShared(driver).isDisplayed())				//Checking if folder gor shared or not.
			test.log(LogStatus.PASS, "Uploaded file shared.");
		else
			test.log(LogStatus.PASS, "Uploaded file does not shared.");
		
		Thread.sleep(500);
		closeSharePoppup(driver).click();
		
		Thread.sleep(500);
		wait.until(ExpectedConditions.elementToBeClickable(OverduePOM.clickDashboard(driver)));
		OverduePOM.clickDashboard(driver).click();			//Clicking on Dashboard
	}

	public static void ReviseCompliance(WebDriver driver, ExtentTest test, int no) throws InterruptedException
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		
		Thread.sleep(1500);
		Actions action = new Actions(driver);
		//action.moveToElement(clickMyWorkspace1(driver)).click().perform();
		clickMyWorkspace(driver).click();				//Clicking on 'My Workspace'
		
		Thread.sleep(500);
		clickCompliance(driver).click();				//Clicking on 'Compliance' under My Workspace
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//*[@role='grid'][@data-role='selectable'])[1]")));	//Waiting for records table to get visible.
		
		clickMoreActions(driver).click();				//Clicking on 'More Actions' drop down.
		Thread.sleep(300);
		elementsList = selectAction(driver);			//Clicking on drop down option
		elementsList.get(no).click();
		
		Thread.sleep(1000);
		wait.until(ExpectedConditions.elementToBeClickable(readReminder(driver)));
		
		int complianceCount = Integer.parseInt(readReminder(driver).getText());		//Reading total number of compliances before action
				
		String compliance = null;
		if(no == 1)
		{
			compliance = "Update Penalty";
			
			action.moveToElement(clickAction(driver)).click().perform();
			
			Thread.sleep(1000);
			clickInterest(driver).sendKeys("100");
			
			Thread.sleep(500);
			clickPenalty(driver).sendKeys("50000");
			
			Thread.sleep(500);
			PenaltyRemark(driver).sendKeys("Automation Testing");
			
			Thread.sleep(500);
		}
		if(no == 2)
		{
			compliance = "Revised Compliance";
			action.moveToElement(clickAction(driver)).click().perform();
			
			Thread.sleep(1500);
			wait.until(ExpectedConditions.elementToBeClickable(clickDate(driver)));
			clickDate(driver).click();								//Clicking on Revise Date.
			
			Thread.sleep(500);
			selectLastMonth(driver).click();
			Thread.sleep(700);
			selectDate(driver).click();								//Selecting second row fourth date.
			
			Thread.sleep(500);
			clickRemark(driver).sendKeys("Automation Testing");		//Sending remark to Text area.
			
			Thread.sleep(500);
			//fileUploadStatutory(driver).sendKeys("C:/Users/sandip/Downloads/Holiday List 2022.xlsx");
		    driver.findElement(By.xpath("//*[@id='ContentPlaceHolder1_fuSampleFile']")).sendKeys("C:/Users/sandip/Downloads/Holiday List 2022.xlsx");
			Thread.sleep(500);
			String workingDir = System.getProperty("user.dir");			
			upload1(driver).sendKeys("C:/March2022/PerformerPom/Reports/PerformerResults.html");	//uploading new file
		}
		
		wait.until(ExpectedConditions.elementToBeClickable(clickSaveButton(driver)));
		clickSaveButton(driver).click();							//Clicking on save button
		
		Thread.sleep(1000);
		int complianceCountNew = Integer.parseInt(readReminder(driver).getText());		//Reading total number of compliances after action
		
		if(complianceCountNew < complianceCount)
			test.log(LogStatus.PASS, compliance + " - Compliances count decreased. Old count = "+complianceCount+" | New count = "+complianceCountNew);
		else
			test.log(LogStatus.FAIL, compliance + " - Compliances count doesn't decreased. Old count = "+complianceCount+" | New count = "+complianceCountNew);
	}
}
