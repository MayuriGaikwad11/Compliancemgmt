package reviewer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import cfo.CFOcountPOM;
import performer.OverduePOM;

public class ReviewerCount
{
	public static WebDriver driver = null;		//WebDriver instance created
	public static WebElement upload = null;		//WebElement to get upload button
	public static ExtentReports extent;			//Instance created for report file
	public static ExtentTest test;				//Instance created for tests
	public static FileInputStream fis = null;	//File input stream variable
	public static XSSFWorkbook workbook = null;	//Excel sheet workbook variable
	public static XSSFSheet sheet = null;		//Sheet variable
	public static List<WebElement> elementsList = null;
	public static List<WebElement> elementsList1 = null;
	
	public static XSSFSheet ReadExcel() throws IOException
	{
		String workingDir = System.getProperty("user.dir");
		fis = new FileInputStream("C:/March2022/PerformerPom/TestData/ComplianceSheet.xlsx");
		workbook = new XSSFWorkbook(fis);
		sheet = workbook.getSheetAt(1);					//Retrieving second sheet of Workbook
		return sheet;
	}
	
	@BeforeTest
	void setBrowser() throws InterruptedException, IOException
	{
		//String workingDir = System.getProperty("user.dir");
		extent = new com.relevantcodes.extentreports.ExtentReports("C:/March2022/PerformerPom/Reports/ReviewerResults.html",true);
		test = extent.startTest("Verify OpenBrowser");
		test.log(LogStatus.INFO, "Browser test is initiated");
		
		XSSFSheet sheet = ReadExcel();
		Row row0 = sheet.getRow(0);						//Selected 0th index row (First row)
		Cell c1 = row0.getCell(1);						//Selected cell (0 row,1 column)
		String URL = c1.getStringCellValue();			//Got the URL stored at position 0,1
		
		login.Login.BrowserSetup(URL);					//Method of Login class to set browser.
		
		test.log(LogStatus.PASS, "Test Passed.");
		extent.endTest(test);
		extent.flush();
	}
	
      @Test(priority = 1) //pass
	void Login() throws InterruptedException, IOException
	{
		test = extent.startTest("Logging In - Reviewer");
		test.log(LogStatus.INFO, "Logging into system");
		
		XSSFSheet sheet = ReadExcel();
		Row row1 = sheet.getRow(1);						//Selected 1st index row (Second row)
		Cell c1 = row1.getCell(1);						//Selected cell (1 row,1 column)
		String uname = c1.getStringCellValue();			//Got the URL stored at position 1,1
		
		Row row2 = sheet.getRow(2);						//Selected 2nd index row (Third row)
		Cell c2 = row2.getCell(1);						//Selected cell (2 row,1 column)
		String password = c2.getStringCellValue();		//Got the URL stored at position 2,1
		
		driver = login.Login.UserLogin(uname,password,"PendingReview");		//Method of Login class to login user.
		
		test.log(LogStatus.PASS, "Test Passed.");
		extent.endTest(test);
		extent.flush();
	}
	/*
	@Test(priority = 2) //pass
       void ReviewCountStatutoryApprove() throws InterruptedException, IOException
	{
		test = extent.startTest("Statutory Review Count when Approved");
		test.log(LogStatus.INFO, "Test initiated");
		
		WebDriverWait wait = new WebDriverWait(driver,  Duration.ofSeconds(30));
		//Thread.sleep(1000);
		wait.until(ExpectedConditions.visibilityOf(ReviewerPOM.clickStatutoryReview(driver)));	//Wait until Statutory Pending For Review count gets visible.
		
		int oldValue = Integer.parseInt(ReviewerPOM.clickStatutoryReview(driver).getText());	//Reading old value of Statutory Pending For Review
		ReviewerPOM.clickStatutoryReview(driver).click();		//Clicking on Statutory Review value.
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@role='grid'][@class='k-selectable']")));
		elementsList = ReviewerPOM.clickStatus(driver);			//CLicking on Status to sort it in ascending order
		elementsList.get(0).click();
		
		wait.until(ExpectedConditions.elementToBeClickable(ReviewerPOM.clickAction1(driver)));
		ReviewerPOM.clickAction1(driver).click();				//Clicking on third action button.
		
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("iReviewerFrame"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,500)"," ");
		
		try
		{
			//Thread.sleep(500);
			ReviewerPOM.clickDownload1(driver).click();					//Clicking on 'Click Here' label.
		}
		catch(Exception e)
		{
			
		}
		try
		{
			//Thread.sleep(500);
			ReviewerPOM.clickDownload2(driver).click();					//Clicking on 'Click Here' label.
		}
		catch(Exception e)
		{
			
		}
		
		Thread.sleep(500);
		js.executeScript("window.scrollBy(0,300)"," ");					//Scrolling down window by 2000 px.
		
		WebElement el = null;
		try
		{
			wait.until(ExpectedConditions.elementToBeClickable(ReviewerPOM.clickClosedTimely(driver)));
			el = ReviewerPOM.clickClosedTimely(driver);			
		}
		catch(Exception e)
		{
			
		}
		if(el != null)
		{
		//	Thread.sleep(500);
			ReviewerPOM.clickClosedTimely(driver).click();				//Clicking on first radio button.
		}
		
		WebElement checkbox = null;
		try
		{
			wait.until(ExpectedConditions.elementToBeClickable(ReviewerPOM.clickCheckBox(driver)));
			checkbox = ReviewerPOM.clickCheckBox(driver);				//Clicking on Check box
		}
		catch(Exception e)
		{
			
		}
		if(checkbox != null)
		{
			if(checkbox.isDisplayed() && !checkbox.isSelected())
				ReviewerPOM.clickCheckBox(driver).click();
		}
		
		//Thread.sleep(500);
		XSSFSheet sheet = ReadExcel();
		WebElement ele1 = null;
		WebElement ele2 = null;
		WebElement ele3 = null;
		try
		{
		//	Thread.sleep(600);
			//wait.until(ExpectedConditions.elementToBeClickable(ReviewerPOM.insertLiability1(driver)));
			ele1 = ReviewerPOM.insertLiability1(driver);			//Loaded element in ele1. So that, if element not found it will do nothing.
			ele2 = ReviewerPOM.insertLiability2(driver);			//Loaded element in ele2. So that, if element not found it will do nothing.
			ele3 = ReviewerPOM.insertLiability3(driver);			//Loaded element in ele3. So that, if element not found it will do nothing.
		}
		catch(Exception e)
		{
			
		}
		if(ele1 != null)
		{
			Row row3 = sheet.getRow(3);									//Selected 3rd index row (Fourth row)
			Cell c1 = row3.getCell(1);									//Selected cell (3 row,1 column)
			int liability1 = (int) c1.getNumericCellValue();			//Got the amount stored at position 3,1
			String l1 = Integer.toString(liability1); 					//Converting int to String
			ReviewerPOM.insertLiability1(driver).clear();				//Clearing the text box.
			ReviewerPOM.insertLiability1(driver).sendKeys(l1);			//Inserting amount in text box
			Thread.sleep(400);
		}
		
		if(ele2 != null)
		{
			Row row4 = sheet.getRow(4);											//Selected 4th index row (Fifth row)
			Cell c2 = row4.getCell(1);											//Selected cell (4 row,1 column)
			int liability2 = (int) c2.getNumericCellValue();					//Got the amount stored at position 4,1
			String l2 = Integer.toString(liability2); 							//Converting int to String
			ReviewerPOM.insertLiability2(driver).clear();				//Clearing the text box.
			ReviewerPOM.insertLiability2(driver).sendKeys(l2);		//Inserting amount in text box
			Thread.sleep(400);
		}
		
		if(ele3 != null)
		{
			Row row5 = sheet.getRow(5);											//Selected 5th index row (Sixth row)
			Cell c3 = row5.getCell(1);											//Selected cell (5 row,1 column)
			int liability3 = (int) c3.getNumericCellValue();					//Got the amount stored at position 5,1
			String l3 = Integer.toString(liability3); 							//Converting int to String
			ReviewerPOM.insertLiability3(driver).clear();				//Clearing the text box.
			ReviewerPOM.insertLiability3(driver).sendKeys(l3);		//Inserting amount in text box
			Thread.sleep(500);
		}
		js.executeScript("window.scrollBy(0,400)"," ");
		
	//	Thread.sleep(500);
		wait.until(ExpectedConditions.elementToBeClickable(ReviewerPOM.insertTextArea(driver)));
		Row row6 = sheet.getRow(6);											//Selected 6th index row (Seventh row)
		Cell c4 = row6.getCell(1);											//Selected cell (6 row,1 column)
		String remark = c4.getStringCellValue();							//Got the URL stored at position 6,1
		ReviewerPOM.insertTextArea(driver).sendKeys(remark);		//Inserting remark in Text area
		
		wait.until(ExpectedConditions.elementToBeClickable(ReviewerPOM.clickApprove(driver)));
		ReviewerPOM.clickApprove(driver).click();					//Clicking on Approve button.

	//	Thread.sleep(1000);
		driver.switchTo().alert().accept();									//Accepting msg of Successful Submission.
		driver.switchTo().parentFrame();									//Switching back to parent frame from iFrame
		
		Thread.sleep(1000);
		performer.OverduePOM.clickDashboard(driver).click();
		
		Thread.sleep(1000);
		wait.until(ExpectedConditions.elementToBeClickable(ReviewerPOM.clickStatutoryReview(driver)));
		int newValue = Integer.parseInt(ReviewerPOM.clickStatutoryReview(driver).getText());	//Reading new value of Statutory Pending For Review
		
		if(newValue < oldValue)
		{
			test.log(LogStatus.PASS, "Statutory count of Pending For Review decremented.");
			test.log(LogStatus.INFO, "Old Count = "+oldValue + " | New Count = "+ newValue);
		}
		else
		{
			test.log(LogStatus.FAIL, "Statutory count of Pending For Review doesn't decremented.");
			test.log(LogStatus.INFO, "Old Count = "+oldValue + " | New Count = "+ newValue);
		}
		extent.endTest(test);
		extent.flush();
	}
	
	@Test(priority = 3) //pass
	void ReviewCountStatutoryReject() throws InterruptedException, IOException
	{
		test = extent.startTest("Statutory 'Pending For Review' and 'Rejected' Count when Rejected");
		test.log(LogStatus.INFO, "Test initiated");
		
		WebDriverWait wait = new WebDriverWait(driver,  Duration.ofSeconds(30));
		
		wait.until(ExpectedConditions.visibilityOf(ReviewerPOM.clickStatutoryReview(driver)));	//Wait until Statutory Pending For Review count gets visible.
		int oldStatutoryReviewValue = Integer.parseInt(ReviewerPOM.clickStatutoryReview(driver).getText());	//Reading old value of Statutory Pending For Review
		int oldStatutoryRejectValue = Integer.parseInt(ReviewerPOM.readStatutoryReject(driver).getText());	//Reading old value of Statutory Rejected
		ReviewerPOM.clickStatutoryReview(driver).click();			//Clicking on Statutory Review value.
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@role='grid'][@class='k-selectable']")));
		elementsList = ReviewerPOM.clickStatus(driver);				//CLicking on Status to sort it in ascending order
		elementsList.get(0).click();
		
		wait.until(ExpectedConditions.elementToBeClickable(ReviewerPOM.clickAction1(driver)));
		ReviewerPOM.clickAction1(driver).click();					//Clicking on third action button.
		
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("iReviewerFrame"));
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,500)"," ");					//Scrolling down window by 2000 px.
		try
		{
			Thread.sleep(500);
			ReviewerPOM.clickDownload1(driver).click();					//Clicking on 'Click Here' label.
		}
		catch(Exception e)
		{
			
		}
		try
		{
			Thread.sleep(500);
			ReviewerPOM.clickDownload2(driver).click();
		}
		catch(Exception e)
		{
			
		}
		
		Thread.sleep(500);
		js.executeScript("window.scrollBy(0,300)"," ");					//Scrolling down window by 2000 px.
		try
		{
			wait.until(ExpectedConditions.elementToBeClickable(ReviewerPOM.clickClosedTimely(driver)));
			ReviewerPOM.clickClosedTimely(driver).click();				//Clicking on Closed-Delay radio button.
		}
		catch(Exception e)
		{
			
		}
		
		XSSFSheet sheet = ReadExcel();
		WebElement ele1 = null;
		WebElement ele2 = null;
		WebElement ele3 = null;
		try
		{
			Thread.sleep(600);
			//wait.until(ExpectedConditions.elementToBeClickable(ReviewerPOM.insertLiability1(driver)));
			ele1 = ReviewerPOM.insertLiability1(driver);			//Loaded element in ele1. So that, if element not found it will do nothing.
			ele2 = ReviewerPOM.insertLiability2(driver);			//Loaded element in ele2. So that, if element not found it will do nothing.
			ele3 = ReviewerPOM.insertLiability3(driver);			//Loaded element in ele3. So that, if element not found it will do nothing.
		}
		catch(Exception e)
		{
			
		}
		if(ele1 != null)
		{
			js.executeScript("window.scrollBy(0,300)"," ");
			Row row3 = sheet.getRow(3);											//Selected 3rd index row (Fourth row)
			Cell c1 = row3.getCell(1);											//Selected cell (3 row,1 column)
			int liability1 = (int) c1.getNumericCellValue();					//Got the amount stored at position 3,1
			String l1 = Integer.toString(liability1); 							//Converting int to String
			ReviewerPOM.insertLiability1(driver).clear();				//Clearing the text box.
			ReviewerPOM.insertLiability1(driver).sendKeys(l1);		//Inserting amount in text box
			Thread.sleep(500);
		}
		
		if(ele2 != null)
		{
			Row row4 = sheet.getRow(4);										//Selected 4th index row (Fifth row)
			Cell c2 = row4.getCell(1);										//Selected cell (4 row,1 column)
			int liability2 = (int) c2.getNumericCellValue();				//Got the amount stored at position 4,1
			String l2 = Integer.toString(liability2); 						//Converting int to String
			ReviewerPOM.insertLiability2(driver).clear();					//Clearing the text box.
			ReviewerPOM.insertLiability2(driver).sendKeys(l2);				//Inserting amount in text box
			Thread.sleep(500);
		}
		
		if(ele3 != null)
		{
			Row row5 = sheet.getRow(5);										//Selected 5th index row (Sixth row)
			Cell c3 = row5.getCell(1);										//Selected cell (5 row,1 column)
			int liability3 = (int) c3.getNumericCellValue();				//Got the amount stored at position 5,1
			String l3 = Integer.toString(liability3); 						//Converting int to String
			ReviewerPOM.insertLiability3(driver).clear();					//Clearing the text box.
			ReviewerPOM.insertLiability3(driver).sendKeys(l3);				//Inserting amount in text box
			Thread.sleep(500);
		}
		
		wait.until(ExpectedConditions.elementToBeClickable(ReviewerPOM.insertTextArea(driver)));
		Row row6 = sheet.getRow(6);											//Selected 6th index row (Seventh row)
		Cell c4 = row6.getCell(1);											//Selected cell (6 row,1 column)
		String remark = c4.getStringCellValue();							//Got the URL stored at position 6,1
		ReviewerPOM.insertTextArea(driver).sendKeys(remark);				//Inserting remark in Text area
		
		wait.until(ExpectedConditions.elementToBeClickable(ReviewerPOM.clickReject(driver)));
		Actions action = new Actions(driver);
		action.moveToElement(ReviewerPOM.clickReject(driver)).click().perform();
		//ReviewerPOM.clickReject(driver).click();							//Clicking on Reject button.
		
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//input[@value='Reject']")));
		
		Thread.sleep(300);
		driver.switchTo().parentFrame();									//Switching back to parent frame from iFrame
		
		Thread.sleep(1000);
		wait.until(ExpectedConditions.elementToBeClickable(performer.OverduePOM.clickDashboard(driver)));
		WebElement element = performer.OverduePOM.clickDashboard(driver);
		action.moveToElement(element).click().perform();					//Clicking on dashboard,
		
		Thread.sleep(1000);
		wait.until(ExpectedConditions.visibilityOf(ReviewerPOM.clickStatutoryReview(driver)));	//Wait until Statutory Pending For Review count gets visible.
		int newStatutoryReviewValue = Integer.parseInt(ReviewerPOM.clickStatutoryReview(driver).getText());	//Reading new value of Statutory Pending For Review
		int newStatutoryRejectValue = Integer.parseInt(ReviewerPOM.readStatutoryReject(driver).getText());	//Reading new value of Statutory Rejected
		
		if(newStatutoryReviewValue < oldStatutoryReviewValue && newStatutoryRejectValue > oldStatutoryRejectValue)
		{
			test.log(LogStatus.PASS, "Statutory value for 'Pending For Review' decreamented and Statutory value for 'Rejected' incremented.");
			test.log(LogStatus.INFO, "Old Statutory Pending Review Value = "+oldStatutoryReviewValue+ " | New Statutory Pending Review Value = "+ newStatutoryReviewValue+".");
			test.log(LogStatus.INFO, "Old Statutory Reject Value = "+oldStatutoryRejectValue+ " | New Statutory Reject Value = "+ newStatutoryRejectValue+".");
		}
		else
		{
			test.log(LogStatus.FAIL, "Updated statutory values not reverted on Dashboard.");
		}
		extent.endTest(test);
		extent.flush();
	}
	
	@Test(priority = 4) //pass
	void ReviewCountInternalApprove() throws InterruptedException, IOException
	{
		test = extent.startTest("Internal 'Pending For Review' - Approved Verification");
		test.log(LogStatus.INFO, "Test initiated");
		
		ReMethodsPOM.PendingReviewInternal(driver, test, sheet, "Approve");
		
		extent.endTest(test);
		extent.flush();
	}
	
	@Test(priority = 5)  //pass
	void ReviewCountInternalReject() throws InterruptedException, IOException
	{
		test = extent.startTest("Internal 'Pending For Review' - Rejected Verification");
		test.log(LogStatus.INFO, "Test initiated");
		
		ReMethodsPOM.PendingReviewInternal(driver, test, sheet, "Reject");
		
		extent.endTest(test);
		extent.flush();
	}
	*/
//	@Test(priority = 6) // pass
	void MyReminderStatutory() throws InterruptedException, IOException
	{
		test = extent.startTest("My Reminder - Statutory Count Verification");
		test.log(LogStatus.INFO, "Test Initiated");
		
		OverduePOM.MyReminder(driver, test, "Statutory");
		
		extent.endTest(test);
		extent.flush();
	}
	
//	@Test(priority = 7) //pass
	void MyReminderInternal() throws InterruptedException, IOException
	{
		test = extent.startTest("My Reminder - Internal Count Verification");
		test.log(LogStatus.INFO, "Test Initiated");
		
		OverduePOM.MyReminder(driver, test, "Internal");
		
		extent.endTest(test);
		extent.flush();
	}
	
//	@Test(priority = 8) //pass
	void InterimReview() throws InterruptedException, IOException
	{
		test = extent.startTest("Interim Review Count Verification");
		test.log(LogStatus.INFO, "Test Initiated");
		
		ReMethodsPOM.SubmittedInterimReview(driver, test);
		
		extent.endTest(test);
		extent.flush();
	}
	
//	@Test(priority = 9)
	void MyEscalation() throws InterruptedException, IOException
	{
		test = extent.startTest("My Escalation verification");
		test.log(LogStatus.INFO, "Test Initiated");
		
		ReMethodsPOM.MyEscalationReviewer(driver, test);
		
		extent.endTest(test);
		extent.flush();
	}
	
	//@Test(priority = 10) //pass
	void ReassignUser() throws InterruptedException, IOException
	{
		test = extent.startTest("Reassign User verification");
		test.log(LogStatus.INFO, "Test Initiated");
		
		ReMethodsPOM.ReassignPerformer(driver, test);
		
		extent.endTest(test);
		extent.flush();
	}
	// --------- Reviewer Task  -----
      
  //   @Test(priority = 11) //pass
  	void ReviewerTaskPFRStatutory  () throws InterruptedException, IOException
  	{
    	  test = extent.startTest("Statutory 'Pending For Review' - Reviewer Task");
  		test.log(LogStatus.INFO, "Test initiated");
  		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		
		test.log(LogStatus.INFO, "***********Statutory 'Pending For Review'************ ");
		
		js.executeScript("window.scrollBy(0,500)");	//Scrolling down window by 2000 px.
		
		wait.until(ExpectedConditions.visibilityOf(ReviewerPOM.ClickReviewerTaskPFRStatutory(driver)));
		
		Thread.sleep(500);
		litigationPerformer.MethodsPOM.progress(driver);
		ReviewerPOM.ClickReviewerTaskPFRStatutory(driver).click();
		Thread.sleep(2000);
	//	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='ContentPlaceHolder1_grdTaskReviewer']")));	//Waiting for records table to get visible
	//	Thread.sleep(500);
		OverduePOM.ClickTaskCreation(driver).click();				//Clicking on 'Task Creation' tab
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='ContentPlaceHolder1_grdTask']")));	//Waiting for records table to get visible
		
	int total = Integer.parseInt(OverduePOM.readReminder1(driver).getText());	//Reading total records displayed
	OverduePOM.clickAddNew1(driver).click();					//Clicking on 'Add New' button
	//wait.until(ExpectedConditions.invisibilityOf(OverduePOM.clickAddNew1(driver)));		//Waiting for Add New button to get invisible. 
	
	test.log(LogStatus.INFO, "------------- Statutory -------------");
	Thread.sleep(3000);
	OverduePOM.clickActFilter(driver).click();					//Clicking on 'Act Filter' drop down.
	Select drp = new Select(OverduePOM.clickActFilter(driver));
	drp.selectByIndex(2);										//Selecting third Act
	
	Thread.sleep(4000);
//	wait.until(ExpectedConditions.elementToBeClickable(OverduePOM.clickComplianceDropDown(driver)));
	OverduePOM.clickComplianceDropDown(driver).click();			//Clicking on 'Compliance' drop down.
	drp = new Select(OverduePOM.clickComplianceDropDown(driver));
	drp.selectByIndex(1);										
	Thread.sleep(3000);
sheet = workbook.getSheetAt(0);					//Retrieving fourth sheet of Workbook(Named - Update Tasks)
	int row = 0;
	Thread.sleep(500);
	Row row0 = sheet.getRow(row);					//Selected 0th index row (First row)
	Cell c1 = null;
	row0= sheet.getRow(3);
	c1 = row0.getCell(1);						//Selected cell (0 row,2 column)	(2 column = third column)
OverduePOM.clickTaskTitle(driver).sendKeys(c1.getStringCellValue());	//Writing Task title

row0 = sheet.getRow(4);							//Selected 1st index row (Second row)
c1 = row0.getCell(1);							//Selected cell (1 row,2 column)
OverduePOM.clickDescription(driver).sendKeys(c1.getStringCellValue());	//Writing description

row0 = sheet.getRow(5);							//Selected 2nd index row (Third row)
c1 = row0.getCell(1);							//Selected cell (2 row,2 column)
int day = (int) c1.getNumericCellValue();
OverduePOM.clickDueDay(driver).sendKeys(""+day+"");	//Writing Due days

Select taskType=new Select(	OverduePOM.clickTaskType(driver));
taskType.selectByIndex(1);
Thread.sleep(3000);

OverduePOM.clickConditionCheckbox(driver).click();	//Clicking on 'Is Task Conditional' checkbox

js.executeScript("window.scrollBy(0,2000)");
row0 = sheet.getRow(6);							//Selected 3rd index row (Fourth row)
c1 = row0.getCell(1);							//Selected cell (3 row,2 column)
OverduePOM.clickConditionalMessage(driver).sendKeys(c1.getStringCellValue());	//Writing condition message

row0 = sheet.getRow(7);							//Selected 4th index row (Fifth row)
c1 = row0.getCell(1);							//Selected cell (4 row,2 column)
OverduePOM.clickYesMsg(driver).sendKeys(c1.getStringCellValue());	//Writing message in 'Yess message' text box

row0 = sheet.getRow(8);							//Selected 5th index row (Sixth row)
c1 = row0.getCell(1);							//Selected cell (5 row,2 column)
OverduePOM.clickNoMsg(driver).sendKeys(c1.getStringCellValue());	//Writing message in 'No message' text box
	
js.executeScript("window.scrollBy(0,1000)");	//Scrolling down window by 2000 px.
Thread.sleep(1500);
String workingDir = System.getProperty("user.dir");
OverduePOM.SampleFormUpload(driver).sendKeys("C:/March2022/PerformerPom/Reports/PerformerResults.html");

Thread.sleep(1000);
OverduePOM.clickSaveButton(driver).click();		//Clicking on 'Save' Button

Thread.sleep(500);
String saveMsg = OverduePOM.taskSavedMsg(driver).getText();
OverduePOM.taskSavedMsg(driver).click();

if(saveMsg.equalsIgnoreCase("Task Saved Successfully."))
{
	test.log(LogStatus.INFO, "Message displayed - 'Task Saved Successfully'");
}
else
{
	test.log(LogStatus.INFO, "Message displayed - 'Task already exist.'");
}

driver.findElement(By.xpath("//*[@id='ContentPlaceHolder1_btnCancel']")).click();
Thread.sleep(1000);
OverduePOM.ClickTaskCreation(driver).click();
int total1 = Integer.parseInt(OverduePOM.readReminder1(driver).getText());	//Reading total records displayed

if(total1 > total)
{
	test.log(LogStatus.PASS, "New Task added and displayed successfully.");
}
else
{
	test.log(LogStatus.FAIL, "New Task doesn't added and displayed.");
}
//-------------------------------Create Sub Task--------------------------------------
Thread.sleep(3000);
elementsList1 = OverduePOM.clickSubTask(driver);	//Getting all Sub Task elements list
int no = elementsList1.size();

Thread.sleep(2000);
elementsList1.get(2).click();

Thread.sleep(4000);

wait.until(ExpectedConditions.visibilityOf(OverduePOM.clickAddNew2(driver)));	//Wait till 'Add New' button gets visible in Sub Task
OverduePOM.clickAddNew2(driver).click();			//Clicking on 'Add New' in Sub Task.
Thread.sleep(3000);
 sheet = workbook.getSheetAt(0);					//Retrieving fourth sheet of Workbook(Named - Update Tasks)
	 row = 0;
	Thread.sleep(500);
	Row row01 = sheet.getRow(row);					//Selected 0th index row (First row)
	Cell c11 = null;
	row01= sheet.getRow(10);
	c11 = row01.getCell(1);						//Selected cell (0 row,2 column)	(2 column = third column)
OverduePOM.clickTaskTitle(driver).sendKeys(c11.getStringCellValue());	//Writing Task title

row01 = sheet.getRow(11);							//Selected 1st index row (Second row)
c11 = row01.getCell(1);							//Selected cell (1 row,2 column)
OverduePOM.clickDescription(driver).sendKeys(c11.getStringCellValue());	//Writing description

row01 = sheet.getRow(12);							//Selected 2nd index row (Third row)
c11 = row01.getCell(1);							//Selected cell (2 row,2 column)
int day1 = (int) c11.getNumericCellValue();
OverduePOM.clickDueDay(driver).sendKeys(""+day1+"");	//Writing Due days

OverduePOM.clickConditionCheckbox(driver).click();	//Clicking on 'Is Task Conditional' checkbox

js.executeScript("window.scrollBy(0,2000)");
row01 = sheet.getRow(13);							//Selected 3rd index row (Fourth row)
c11 = row01.getCell(1);							//Selected cell (3 row,2 column)
OverduePOM.clickConditionalMessage(driver).sendKeys(c11.getStringCellValue());	//Writing condition message

row01 = sheet.getRow(14);							//Selected 4th index row (Fifth row)
c11 = row01.getCell(1);							//Selected cell (4 row,2 column)
OverduePOM.clickYesMsg(driver).sendKeys(c11.getStringCellValue());	//Writing message in 'Yess message' text box

row01 = sheet.getRow(15);							//Selected 5th index row (Sixth row)
c11 = row01.getCell(1);							//Selected cell (5 row,2 column)
OverduePOM.clickNoMsg(driver).sendKeys(c11.getStringCellValue());	//Writing message in 'No message' text box
	
js.executeScript("window.scrollBy(0,1000)");	//Scrolling down window by 2000 px.
Thread.sleep(1500);
//String workingDir = System.getProperty("user.dir");
OverduePOM.SampleFormUpload(driver).sendKeys("C:/March2022/PerformerPom/Reports/PerformerResults.html");

Thread.sleep(1000);
OverduePOM.clickSaveButton(driver).click();		//Clicking on 'Save' Button
Thread.sleep(500);
String saveMsg1 = OverduePOM.taskSavedMsg1(driver).getText();	//Msg = Subtask Saved Successfully.

test.log(LogStatus.INFO, "Message displayed - '"+ saveMsg1 +"'");
	
driver.findElement(By.xpath("//*[@id='ContentPlaceHolder1_btnCancel']")).click();		//Closing the Sub Task form.
try
{
	Thread.sleep(700);
	if(OverduePOM.checkRecordsTable(driver).isDisplayed())
	{
		test.log(LogStatus.PASS, "New Sub Task added and displayed successfully.");
	}
	else
	{
		test.log(LogStatus.FAIL, "New Sub Task doesn't added and displayed.");
	}
}
catch(Exception e)
{
	
}

OverduePOM.clickDashboard(driver).click();
Thread.sleep(1000);
test.log(LogStatus.PASS, "Test Passed.");
extent.endTest(test);
extent.flush();
  	}
      
   //  @Test(priority = 12) //pass
    	void ReviewerTaskPFRInternal  () throws InterruptedException, IOException
    	{
      	  test = extent.startTest("Internal 'Pending For Review' - Reviewer Task");
    		test.log(LogStatus.INFO, "Test initiated");
    		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
  		JavascriptExecutor js = (JavascriptExecutor) driver;
  		
  		test.log(LogStatus.INFO, "***********Internal 'Pending For Review'************ ");
  		
  		js.executeScript("window.scrollBy(0,500)");	//Scrolling down window by 2000 px.
  		
  		wait.until(ExpectedConditions.visibilityOf(ReviewerPOM.ClickReviewerTaskPFRInternal(driver)));
  		
  		Thread.sleep(500);
  		litigationPerformer.MethodsPOM.progress(driver);
  		ReviewerPOM.ClickReviewerTaskPFRInternal(driver).click();
  		Thread.sleep(3000);
  	//	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='ContentPlaceHolder1_grdTaskReviewer']")));	//Waiting for records table to get visible
  		//Thread.sleep(1000);
  		OverduePOM.ClickTaskCreation(driver).click();				//Clicking on 'Task Creation' tab
  	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='ContentPlaceHolder1_grdTask']")));	//Waiting for records table to get visible
  		
  	int total = Integer.parseInt(OverduePOM.readReminder1(driver).getText());	//Reading total records displayed
  	
  	OverduePOM.clickAddNew1(driver).click();
  	Thread.sleep(2000);
	wait.until(ExpectedConditions.elementToBeClickable(OverduePOM.clickInternaRadioButton(driver)));
	
			test.log(LogStatus.INFO, "------------- Internal -------------");
			Thread.sleep(2000);
			OverduePOM.clickInternaRadioButton(driver).click();	//Clicking on Radio button of 'Internal' tasks
			Thread.sleep(4000); 
			Select drp = new Select(OverduePOM.clickInternalCompliance(driver));
			drp.selectByIndex(1);
			Thread.sleep(3000);
			
		   sheet = workbook.getSheetAt(0);					//Retrieving fourth sheet of Workbook(Named - Update Tasks)
				int row = 0;
				Thread.sleep(500);
				Row row0 = sheet.getRow(row);					//Selected 0th index row (First row)
				Cell c1 = null;
				row0= sheet.getRow(3);
				c1 = row0.getCell(1);						//Selected cell (0 row,2 column)	(2 column = third column)
			OverduePOM.clickTaskTitle(driver).sendKeys(c1.getStringCellValue());	//Writing Task title
			
			row0 = sheet.getRow(4);							//Selected 1st index row (Second row)
			c1 = row0.getCell(1);							//Selected cell (1 row,2 column)
			OverduePOM.clickDescription(driver).sendKeys(c1.getStringCellValue());	//Writing description
			
			row0 = sheet.getRow(5);							//Selected 2nd index row (Third row)
			c1 = row0.getCell(1);							//Selected cell (2 row,2 column)
			int day = (int) c1.getNumericCellValue();
			OverduePOM.clickDueDay(driver).sendKeys(""+day+"");	//Writing Due days
			
		  Select taskType=new Select(	OverduePOM.clickTaskType(driver));
		  taskType.selectByIndex(1);
		  Thread.sleep(4000);

			OverduePOM.clickConditionCheckbox(driver).click();	//Clicking on 'Is Task Conditional' checkbox
			Thread.sleep(2000);
			js.executeScript("window.scrollBy(0,2000)");
			row0 = sheet.getRow(6);							//Selected 3rd index row (Fourth row)
			c1 = row0.getCell(1);							//Selected cell (3 row,2 column)
			OverduePOM.clickConditionalMessage(driver).sendKeys(c1.getStringCellValue());	//Writing condition message
			
			row0 = sheet.getRow(7);							//Selected 4th index row (Fifth row)
			c1 = row0.getCell(1);							//Selected cell (4 row,2 column)
			OverduePOM.clickYesMsg(driver).sendKeys(c1.getStringCellValue());	//Writing message in 'Yess message' text box
			
			row0 = sheet.getRow(8);							//Selected 5th index row (Sixth row)
			c1 = row0.getCell(1);							//Selected cell (5 row,2 column)
			OverduePOM.clickNoMsg(driver).sendKeys(c1.getStringCellValue());	//Writing message in 'No message' text box
				
			js.executeScript("window.scrollBy(0,1000)");	//Scrolling down window by 2000 px.
			Thread.sleep(1500);
			String workingDir = System.getProperty("user.dir");
			OverduePOM.SampleFormUpload(driver).sendKeys("C:/March2022/PerformerPom/Reports/PerformerResults.html");
			
			Thread.sleep(1000);
			OverduePOM.clickSaveButton(driver).click();		//Clicking on 'Save' Button
			
			Thread.sleep(500);
			String saveMsg = OverduePOM.taskSavedMsg(driver).getText();
			OverduePOM.taskSavedMsg(driver).click();
			
			if(saveMsg.equalsIgnoreCase("Task Saved Successfully."))
			{
				test.log(LogStatus.INFO, "Message displayed - 'Task Saved Successfully'");
			}
			else
			{
				test.log(LogStatus.INFO, "Message displayed - 'Task already exist.'");
			}
			
			driver.findElement(By.xpath("//*[@id='ContentPlaceHolder1_btnCancel']")).click();
			Thread.sleep(1000);
			OverduePOM.ClickTaskCreation(driver).click();
			int total1 = Integer.parseInt(OverduePOM.readReminder1(driver).getText());	//Reading total records displayed
			
			if(total1 > total)
			{
				test.log(LogStatus.PASS, "New Task added and displayed successfully.");
			}
			else
			{
				test.log(LogStatus.FAIL, "New Task doesn't added and displayed.");
			}
			elementsList1 = OverduePOM.clickSubTask(driver);	//Getting all Sub Task elements list
			int no = elementsList1.size();

			Thread.sleep(1000);
			elementsList1.get(3).click();
			Thread.sleep(2000);
			wait.until(ExpectedConditions.visibilityOf(OverduePOM.clickAddNew2(driver)));	//Wait till 'Add New' button gets visible in Sub Task
			Thread.sleep(5000);
			OverduePOM.clickAddNew2(driver).click();			//Clicking on 'Add New' in Sub Task.
			Thread.sleep(3000);
			 sheet = workbook.getSheetAt(0);					//Retrieving fourth sheet of Workbook(Named - Update Tasks)
				 row = 0;
				Thread.sleep(3000);
				Row row01 = sheet.getRow(row);					//Selected 0th index row (First row)
				Cell c11 = null;
				row01= sheet.getRow(10);
				c11 = row01.getCell(1);						//Selected cell (0 row,2 column)	(2 column = third column)
			OverduePOM.clickTaskTitle(driver).sendKeys(c11.getStringCellValue());	//Writing Task title
			Thread.sleep(2000);
			row01 = sheet.getRow(11);							//Selected 1st index row (Second row)
			c11 = row01.getCell(1);							//Selected cell (1 row,2 column)
			OverduePOM.clickDescription(driver).sendKeys(c11.getStringCellValue());	//Writing description

			row01 = sheet.getRow(12);							//Selected 2nd index row (Third row)
			c11 = row01.getCell(1);							//Selected cell (2 row,2 column)
			int day1 = (int) c11.getNumericCellValue();
			OverduePOM.clickDueDay(driver).sendKeys(""+day1+"");	//Writing Due days

			OverduePOM.clickConditionCheckbox(driver).click();	//Clicking on 'Is Task Conditional' checkbox

			js.executeScript("window.scrollBy(0,2000)");
			row01 = sheet.getRow(13);							//Selected 3rd index row (Fourth row)
			c11 = row01.getCell(1);							//Selected cell (3 row,2 column)
			OverduePOM.clickConditionalMessage(driver).sendKeys(c11.getStringCellValue());	//Writing condition message

			row01 = sheet.getRow(14);							//Selected 4th index row (Fifth row)
			c11 = row01.getCell(1);							//Selected cell (4 row,2 column)
			OverduePOM.clickYesMsg(driver).sendKeys(c11.getStringCellValue());	//Writing message in 'Yess message' text box

			row01 = sheet.getRow(15);							//Selected 5th index row (Sixth row)
			c11 = row01.getCell(1);							//Selected cell (5 row,2 column)
			OverduePOM.clickNoMsg(driver).sendKeys(c11.getStringCellValue());	//Writing message in 'No message' text box
				
			js.executeScript("window.scrollBy(0,1000)");	//Scrolling down window by 2000 px.
			Thread.sleep(1500);
			//String workingDir = System.getProperty("user.dir");
			OverduePOM.SampleFormUpload(driver).sendKeys("C:/March2022/PerformerPom/Reports/PerformerResults.html");

			Thread.sleep(1000);
			OverduePOM.clickSaveButton(driver).click();		//Clicking on 'Save' Button
			Thread.sleep(500);
			String saveMsg1 = OverduePOM.taskSavedMsg1(driver).getText();	//Msg = Subtask Saved Successfully.

			test.log(LogStatus.INFO, "Message displayed - '"+ saveMsg1 +"'");
				
			driver.findElement(By.xpath("//*[@id='ContentPlaceHolder1_btnCancel']")).click();		//Closing the Sub Task form.
			try
			{
				Thread.sleep(700);
				if(OverduePOM.checkRecordsTable(driver).isDisplayed())
				{
					test.log(LogStatus.PASS, "New Sub Task added and displayed successfully.");
				}
				else
				{
					test.log(LogStatus.FAIL, "New Sub Task doesn't added and displayed.");
				}
			}
			catch(Exception e)
			{
				
			}
  	OverduePOM.clickDashboard(driver).click();
  	Thread.sleep(1000);
  	test.log(LogStatus.PASS, "Test Passed.");
  	extent.endTest(test);
  	extent.flush();
    	}
    //	 @Test(priority = 13) //pass
    	  	void ReviewerTaskDBNSStatutory  () throws InterruptedException, IOException
    	  	{
    	    	  test = extent.startTest("Statutory 'Due But Not Submitted' - Reviewer Task");
    	  		test.log(LogStatus.INFO, "Test initiated");
    	  		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    			JavascriptExecutor js = (JavascriptExecutor) driver;
    			
    			test.log(LogStatus.INFO, "***********Statutory 'Due But Not Submitted  '************ ");
    			
    			js.executeScript("window.scrollBy(0,500)");	//Scrolling down window by 2000 px.
    			
    			wait.until(ExpectedConditions.visibilityOf(ReviewerPOM.ReviewerTaskDBNSStatutory(driver)));
    			
    			Thread.sleep(500);
    			litigationPerformer.MethodsPOM.progress(driver);
    			ReviewerPOM.ReviewerTaskDBNSStatutory(driver).click();
    			Thread.sleep(2000);
    		//	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='ContentPlaceHolder1_grdTaskReviewer']")));	//Waiting for records table to get visible
    		//	Thread.sleep(500);
    			OverduePOM.ClickTaskCreation(driver).click();				//Clicking on 'Task Creation' tab
    			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='ContentPlaceHolder1_grdTask']")));	//Waiting for records table to get visible
    				
    			int total = Integer.parseInt(OverduePOM.readReminder1(driver).getText());	//Reading total records displayed
    			OverduePOM.clickAddNew1(driver).click();					//Clicking on 'Add New' button
    			//wait.until(ExpectedConditions.invisibilityOf(OverduePOM.clickAddNew1(driver)));		//Waiting for Add New button to get invisible. 
    			
    			test.log(LogStatus.INFO, "------------- Statutory -------------");
    			Thread.sleep(3000);
    			OverduePOM.clickActFilter(driver).click();					//Clicking on 'Act Filter' drop down.
    			Select drp = new Select(OverduePOM.clickActFilter(driver));
    			drp.selectByIndex(2);										//Selecting third Act
    			
    			Thread.sleep(4000);
//    			wait.until(ExpectedConditions.elementToBeClickable(OverduePOM.clickComplianceDropDown(driver)));
    			OverduePOM.clickComplianceDropDown(driver).click();			//Clicking on 'Compliance' drop down.
    			drp = new Select(OverduePOM.clickComplianceDropDown(driver));
    			drp.selectByIndex(1);										
    			Thread.sleep(500);
    		sheet = workbook.getSheetAt(0);					//Retrieving fourth sheet of Workbook(Named - Update Tasks)
    			int row = 0;
    			Thread.sleep(500);
    			Row row0 = sheet.getRow(row);					//Selected 0th index row (First row)
    			Cell c1 = null;
    			row0= sheet.getRow(3);
    			c1 = row0.getCell(2);						//Selected cell (0 row,2 column)	(2 column = third column)
    		OverduePOM.clickTaskTitle(driver).sendKeys(c1.getStringCellValue());	//Writing Task title

    		row0 = sheet.getRow(4);							//Selected 1st index row (Second row)
    		c1 = row0.getCell(2);							//Selected cell (1 row,2 column)
    		OverduePOM.clickDescription(driver).sendKeys(c1.getStringCellValue());	//Writing description

    		row0 = sheet.getRow(5);							//Selected 2nd index row (Third row)
    		c1 = row0.getCell(2);							//Selected cell (2 row,2 column)
    		int day = (int) c1.getNumericCellValue();
    		OverduePOM.clickDueDay(driver).sendKeys(""+day+"");	//Writing Due days

    		Select taskType=new Select(	OverduePOM.clickTaskType(driver));
    		taskType.selectByIndex(1);
    		Thread.sleep(3000);

    		OverduePOM.clickConditionCheckbox(driver).click();	//Clicking on 'Is Task Conditional' checkbox

    		js.executeScript("window.scrollBy(0,2000)");
    		row0 = sheet.getRow(6);							//Selected 3rd index row (Fourth row)
    		c1 = row0.getCell(2);							//Selected cell (3 row,2 column)
    		OverduePOM.clickConditionalMessage(driver).sendKeys(c1.getStringCellValue());	//Writing condition message

    		row0 = sheet.getRow(7);							//Selected 4th index row (Fifth row)
    		c1 = row0.getCell(2);							//Selected cell (4 row,2 column)
    		OverduePOM.clickYesMsg(driver).sendKeys(c1.getStringCellValue());	//Writing message in 'Yess message' text box

    		row0 = sheet.getRow(8);							//Selected 5th index row (Sixth row)
    		c1 = row0.getCell(2);							//Selected cell (5 row,2 column)
    		OverduePOM.clickNoMsg(driver).sendKeys(c1.getStringCellValue());	//Writing message in 'No message' text box
    			
    		js.executeScript("window.scrollBy(0,1000)");	//Scrolling down window by 2000 px.
    		Thread.sleep(1500);
    		String workingDir = System.getProperty("user.dir");
    		OverduePOM.SampleFormUpload(driver).sendKeys("C:/March2022/PerformerPom/Reports/PerformerResults.html");

    		Thread.sleep(1000);
    		OverduePOM.clickSaveButton(driver).click();		//Clicking on 'Save' Button

    		Thread.sleep(500);
    		String saveMsg = OverduePOM.taskSavedMsg(driver).getText();
    		OverduePOM.taskSavedMsg(driver).click();

    		if(saveMsg.equalsIgnoreCase("Task Saved Successfully."))
    		{
    			test.log(LogStatus.INFO, "Message displayed - 'Task Saved Successfully'");
    		}
    		else
    		{
    			test.log(LogStatus.INFO, "Message displayed - 'Task already exist.'");
    		}

    		driver.findElement(By.xpath("//*[@id='ContentPlaceHolder1_btnCancel']")).click();
    		Thread.sleep(1000);
    		OverduePOM.ClickTaskCreation(driver).click();
    		int total1 = Integer.parseInt(OverduePOM.readReminder1(driver).getText());	//Reading total records displayed

    		if(total1 > total)
    		{
    			test.log(LogStatus.PASS, "New Task added and displayed successfully.");
    		}
    		else
    		{
    			test.log(LogStatus.FAIL, "New Task doesn't added and displayed.");
    		}
    		//-------------------------------Create Sub Task--------------------------------------
    		Thread.sleep(3000);
    		elementsList1 = OverduePOM.clickSubTask(driver);	//Getting all Sub Task elements list
    		int no = elementsList1.size();

    		Thread.sleep(2000);
    		elementsList1.get(4).click();

    		Thread.sleep(4000);

    		wait.until(ExpectedConditions.visibilityOf(OverduePOM.clickAddNew2(driver)));	//Wait till 'Add New' button gets visible in Sub Task
    		OverduePOM.clickAddNew2(driver).click();			//Clicking on 'Add New' in Sub Task.
    		Thread.sleep(3000);
    		 sheet = workbook.getSheetAt(0);					//Retrieving fourth sheet of Workbook(Named - Update Tasks)
    			 row = 0;
    			Thread.sleep(500);
    			Row row01 = sheet.getRow(row);					//Selected 0th index row (First row)
    			Cell c11 = null;
    			row01= sheet.getRow(10);
    			c11 = row01.getCell(1);						//Selected cell (0 row,2 column)	(2 column = third column)
    		OverduePOM.clickTaskTitle(driver).sendKeys(c11.getStringCellValue());	//Writing Task title

    		row01 = sheet.getRow(11);							//Selected 1st index row (Second row)
    		c11 = row01.getCell(1);							//Selected cell (1 row,2 column)
    		OverduePOM.clickDescription(driver).sendKeys(c11.getStringCellValue());	//Writing description

    		row01 = sheet.getRow(12);							//Selected 2nd index row (Third row)
    		c11 = row01.getCell(1);							//Selected cell (2 row,2 column)
    		int day1 = (int) c11.getNumericCellValue();
    		OverduePOM.clickDueDay(driver).sendKeys(""+day1+"");	//Writing Due days

    		OverduePOM.clickConditionCheckbox(driver).click();	//Clicking on 'Is Task Conditional' checkbox

    		js.executeScript("window.scrollBy(0,2000)");
    		row01 = sheet.getRow(13);							//Selected 3rd index row (Fourth row)
    		c11 = row01.getCell(1);							//Selected cell (3 row,2 column)
    		OverduePOM.clickConditionalMessage(driver).sendKeys(c11.getStringCellValue());	//Writing condition message

    		row01 = sheet.getRow(14);							//Selected 4th index row (Fifth row)
    		c11 = row01.getCell(1);							//Selected cell (4 row,2 column)
    		OverduePOM.clickYesMsg(driver).sendKeys(c11.getStringCellValue());	//Writing message in 'Yess message' text box

    		row01 = sheet.getRow(15);							//Selected 5th index row (Sixth row)
    		c11 = row01.getCell(1);							//Selected cell (5 row,2 column)
    		OverduePOM.clickNoMsg(driver).sendKeys(c11.getStringCellValue());	//Writing message in 'No message' text box
    			
    		js.executeScript("window.scrollBy(0,1000)");	//Scrolling down window by 2000 px.
    		Thread.sleep(1500);
    		//String workingDir = System.getProperty("user.dir");
    		OverduePOM.SampleFormUpload(driver).sendKeys("C:/March2022/PerformerPom/Reports/PerformerResults.html");

    		Thread.sleep(1000);
    		OverduePOM.clickSaveButton(driver).click();		//Clicking on 'Save' Button
    		Thread.sleep(500);
    		String saveMsg1 = OverduePOM.taskSavedMsg1(driver).getText();	//Msg = Subtask Saved Successfully.

    		test.log(LogStatus.INFO, "Message displayed - '"+ saveMsg1 +"'");
    			
    		driver.findElement(By.xpath("//*[@id='ContentPlaceHolder1_btnCancel']")).click();		//Closing the Sub Task form.
    		try
    		{
    			Thread.sleep(700);
    			if(OverduePOM.checkRecordsTable(driver).isDisplayed())
    			{
    				test.log(LogStatus.PASS, "New Sub Task added and displayed successfully.");
    			}
    			else
    			{
    				test.log(LogStatus.FAIL, "New Sub Task doesn't added and displayed.");
    			}
    		}
    		catch(Exception e)
    		{
    			
    		}

    		OverduePOM.clickDashboard(driver).click();
    	  	Thread.sleep(1000);
    	  	test.log(LogStatus.PASS, "Test Passed.");
    	  	extent.endTest(test);
    	  	extent.flush();
    	  	}
    	  	
    //	  	@Test(priority = 14) //pass
        	void ReviewerTaskDBNSInternal  () throws InterruptedException, IOException
        	{
          	  test = extent.startTest("Internal 'Due But Not Submitted' - Reviewer Task");
        		test.log(LogStatus.INFO, "Test initiated");
        		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
      		JavascriptExecutor js = (JavascriptExecutor) driver;
      		
      		test.log(LogStatus.INFO, "***********Internal 'Due But Not Submitted'************ ");
      		
      		js.executeScript("window.scrollBy(0,500)");	//Scrolling down window by 2000 px.
      		
      		wait.until(ExpectedConditions.visibilityOf(ReviewerPOM.ReviewerTaskDBNSInternal(driver)));
      		
      		Thread.sleep(500);
      		litigationPerformer.MethodsPOM.progress(driver);
      		ReviewerPOM.ReviewerTaskDBNSInternal(driver).click();
      		Thread.sleep(3000);
      	//	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='ContentPlaceHolder1_grdTaskReviewer']")));	//Waiting for records table to get visible
      		//Thread.sleep(1000);
      		OverduePOM.ClickTaskCreation(driver).click();				//Clicking on 'Task Creation' tab
      	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='ContentPlaceHolder1_grdTask']")));	//Waiting for records table to get visible
      		
      	int total = Integer.parseInt(OverduePOM.readReminder1(driver).getText());	//Reading total records displayed
      	OverduePOM.clickAddNew1(driver).click();
      	Thread.sleep(2000);
    	wait.until(ExpectedConditions.elementToBeClickable(OverduePOM.clickInternaRadioButton(driver)));
    	
    			test.log(LogStatus.INFO, "------------- Internal -------------");
    			Thread.sleep(2000);
    			OverduePOM.clickInternaRadioButton(driver).click();	//Clicking on Radio button of 'Internal' tasks
    			Thread.sleep(4000); 
    			Select drp = new Select(OverduePOM.clickInternalCompliance(driver));
    			drp.selectByIndex(1);
    			Thread.sleep(3000);
    			
    		   sheet = workbook.getSheetAt(0);					//Retrieving fourth sheet of Workbook(Named - Update Tasks)
    				int row = 0;
    				Thread.sleep(1000);
    				Row row0 = sheet.getRow(row);					//Selected 0th index row (First row)
    				Cell c1 = null;
    				row0= sheet.getRow(3);
    				c1 = row0.getCell(2);						//Selected cell (0 row,2 column)	(2 column = third column)
    			OverduePOM.clickTaskTitle(driver).sendKeys(c1.getStringCellValue());	//Writing Task title
    			
    			row0 = sheet.getRow(4);							//Selected 1st index row (Second row)
    			c1 = row0.getCell(2);							//Selected cell (1 row,2 column)
    			OverduePOM.clickDescription(driver).sendKeys(c1.getStringCellValue());	//Writing description
    			
    			row0 = sheet.getRow(5);							//Selected 2nd index row (Third row)
    			c1 = row0.getCell(2);							//Selected cell (2 row,2 column)
    			int day = (int) c1.getNumericCellValue();
    			OverduePOM.clickDueDay(driver).sendKeys(""+day+"");	//Writing Due days
    			
    		  Select taskType=new Select(	OverduePOM.clickTaskType(driver));
    		  taskType.selectByIndex(1);
    		  Thread.sleep(3000);

    			OverduePOM.clickConditionCheckbox(driver).click();	//Clicking on 'Is Task Conditional' checkbox
    			Thread.sleep(2000);
    			js.executeScript("window.scrollBy(0,2000)");
    			row0 = sheet.getRow(6);							//Selected 3rd index row (Fourth row)
    			c1 = row0.getCell(2);							//Selected cell (3 row,2 column)
    			OverduePOM.clickConditionalMessage(driver).sendKeys(c1.getStringCellValue());	//Writing condition message
    			
    			row0 = sheet.getRow(7);							//Selected 4th index row (Fifth row)
    			c1 = row0.getCell(2);							//Selected cell (4 row,2 column)
    			OverduePOM.clickYesMsg(driver).sendKeys(c1.getStringCellValue());	//Writing message in 'Yess message' text box
    			
    			row0 = sheet.getRow(8);							//Selected 5th index row (Sixth row)
    			c1 = row0.getCell(2);							//Selected cell (5 row,2 column)
    			OverduePOM.clickNoMsg(driver).sendKeys(c1.getStringCellValue());	//Writing message in 'No message' text box
    				
    			js.executeScript("window.scrollBy(0,1000)");	//Scrolling down window by 2000 px.
    			Thread.sleep(1500);
    			String workingDir = System.getProperty("user.dir");
    			OverduePOM.SampleFormUpload(driver).sendKeys("C:/March2022/PerformerPom/Reports/PerformerResults.html");
    			
    			Thread.sleep(1000);
    			OverduePOM.clickSaveButton(driver).click();		//Clicking on 'Save' Button
    			
    			Thread.sleep(500);
    			String saveMsg = OverduePOM.taskSavedMsg(driver).getText();
    			OverduePOM.taskSavedMsg(driver).click();
    			
    			if(saveMsg.equalsIgnoreCase("Task Saved Successfully."))
    			{
    				test.log(LogStatus.INFO, "Message displayed - 'Task Saved Successfully'");
    			}
    			else
    			{
    				test.log(LogStatus.INFO, "Message displayed - 'Task already exist.'");
    			}
    			
    			driver.findElement(By.xpath("//*[@id='ContentPlaceHolder1_btnCancel']")).click();
    			Thread.sleep(1000);
    			OverduePOM.ClickTaskCreation(driver).click();
    			int total1 = Integer.parseInt(OverduePOM.readReminder1(driver).getText());	//Reading total records displayed
    			
    			if(total1 > total)
    			{
    				test.log(LogStatus.PASS, "New Task added and displayed successfully.");
    			}
    			else
    			{
    				test.log(LogStatus.FAIL, "New Task doesn't added and displayed.");
    			}
    			//-------------------------------Create Sub Task--------------------------------------
    			Thread.sleep(3000);
    			elementsList1 = OverduePOM.clickSubTask(driver);	//Getting all Sub Task elements list
    			int no = elementsList1.size();

    			Thread.sleep(2000);
    			elementsList1.get(5).click();

    			Thread.sleep(4000);

    			wait.until(ExpectedConditions.visibilityOf(OverduePOM.clickAddNew2(driver)));	//Wait till 'Add New' button gets visible in Sub Task
    			OverduePOM.clickAddNew2(driver).click();			//Clicking on 'Add New' in Sub Task.
    			Thread.sleep(3000);
    			 sheet = workbook.getSheetAt(0);					//Retrieving fourth sheet of Workbook(Named - Update Tasks)
    				 row = 0;
    				Thread.sleep(500);
    				Row row01 = sheet.getRow(row);					//Selected 0th index row (First row)
    				Cell c11 = null;
    				row01= sheet.getRow(10);
    				c11 = row01.getCell(1);						//Selected cell (0 row,2 column)	(2 column = third column)
    			OverduePOM.clickTaskTitle(driver).sendKeys(c11.getStringCellValue());	//Writing Task title

    			row01 = sheet.getRow(11);							//Selected 1st index row (Second row)
    			c11 = row01.getCell(1);							//Selected cell (1 row,2 column)
    			OverduePOM.clickDescription(driver).sendKeys(c11.getStringCellValue());	//Writing description

    			row01 = sheet.getRow(12);							//Selected 2nd index row (Third row)
    			c11 = row01.getCell(1);							//Selected cell (2 row,2 column)
    			int day1 = (int) c11.getNumericCellValue();
    			OverduePOM.clickDueDay(driver).sendKeys(""+day1+"");	//Writing Due days

    			OverduePOM.clickConditionCheckbox(driver).click();	//Clicking on 'Is Task Conditional' checkbox

    			js.executeScript("window.scrollBy(0,2000)");
    			row01 = sheet.getRow(13);							//Selected 3rd index row (Fourth row)
    			c11 = row01.getCell(1);							//Selected cell (3 row,2 column)
    			OverduePOM.clickConditionalMessage(driver).sendKeys(c11.getStringCellValue());	//Writing condition message

    			row01 = sheet.getRow(14);							//Selected 4th index row (Fifth row)
    			c11 = row01.getCell(1);							//Selected cell (4 row,2 column)
    			OverduePOM.clickYesMsg(driver).sendKeys(c11.getStringCellValue());	//Writing message in 'Yess message' text box

    			row01 = sheet.getRow(15);							//Selected 5th index row (Sixth row)
    			c11 = row01.getCell(1);							//Selected cell (5 row,2 column)
    			OverduePOM.clickNoMsg(driver).sendKeys(c11.getStringCellValue());	//Writing message in 'No message' text box
    				
    			js.executeScript("window.scrollBy(0,1000)");	//Scrolling down window by 2000 px.
    			Thread.sleep(1500);
    			//String workingDir = System.getProperty("user.dir");
    			OverduePOM.SampleFormUpload(driver).sendKeys("C:/March2022/PerformerPom/Reports/PerformerResults.html");

    			Thread.sleep(1000);
    			OverduePOM.clickSaveButton(driver).click();		//Clicking on 'Save' Button
    			Thread.sleep(500);
    			String saveMsg1 = OverduePOM.taskSavedMsg1(driver).getText();	//Msg = Subtask Saved Successfully.

    			test.log(LogStatus.INFO, "Message displayed - '"+ saveMsg1 +"'");
    				
    			driver.findElement(By.xpath("//*[@id='ContentPlaceHolder1_btnCancel']")).click();		//Closing the Sub Task form.
    			try
    			{
    				Thread.sleep(700);
    				if(OverduePOM.checkRecordsTable(driver).isDisplayed())
    				{
    					test.log(LogStatus.PASS, "New Sub Task added and displayed successfully.");
    				}
    				else
    				{
    					test.log(LogStatus.FAIL, "New Sub Task doesn't added and displayed.");
    				}
    			}
    			catch(Exception e)
    			{
    				
    			}

    			OverduePOM.clickDashboard(driver).click();
        	  	Thread.sleep(1000);
        	  	test.log(LogStatus.PASS, "Test Passed.");
        	  	extent.endTest(test);
        	  	extent.flush();
        	}
        	
     //   @Test(priority = 16)  
        	void DetailedReport() throws InterruptedException, IOException
        	{
        		test = extent.startTest("Detailed Report Count Verification");
        		test.log(LogStatus.INFO, "Test Initiated");
        		
        		CFOcountPOM.DetailedReport(test, driver, "Reviewer");
        		
        		extent.endTest(test);
        		extent.flush();
        	}	
        	
      //  	@Test(priority = 16)  
        	void DetailedReportStat() throws InterruptedException, IOException
        	{
        		test = extent.startTest("Detailed Report Count Internal Verification");
        		test.log(LogStatus.INFO, "Test Initiated");
        		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(70));
        	    
        		Thread.sleep(500);
        		CFOcountPOM.clickReports(driver).click();					//Clicking on 'My Reports'
        		Thread.sleep(3000);
        		CFOcountPOM.clickDetailedReport(driver).click();			//Clicking on 'Detailed Reports' 
        		
        		Thread.sleep(3000);
        		wait.until(ExpectedConditions.visibilityOf(OverduePOM.clickUserDropDown(driver)));
        		OverduePOM.clickUserDropDown(driver).click();		//Clicking on User DropDown
        		Thread.sleep(300);
        		OverduePOM.clickReviewer(driver).click();			//CLicking on Performer under User DropDown.
        		
        		Thread.sleep(500);
        		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class='k-selectable']")));	//Wait till records table gets visible
        		Thread.sleep(3000);
        		CFOcountPOM.selectMonth(driver).click();
        		CFOcountPOM.clickAllMonths(driver).click();
        		Thread.sleep(500);
        		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class='k-selectable']")));	//Wait till records table gets visible
        		Thread.sleep(500);
        		
        		JavascriptExecutor js = (JavascriptExecutor) driver;
        		js.executeScript("window.scrollBy(0,-1000)");				//Scrolling window up.
        		
        		Thread.sleep(1000);
        		wait.until(ExpectedConditions.visibilityOf(CFOcountPOM.clickComplianceDropDown(driver)));
        		CFOcountPOM.clickComplianceDropDown(driver).click();		//Clicking on Compliance drop down.
        		Thread.sleep(500);
        		driver.findElement(By.xpath("/html/body/div[3]/div/div[1]/span")).click();
        		driver.findElement(By.xpath("/html/body/div[3]/div/div[1]/span")).click();
        		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//*[@id='1f607697-e9e7-4740-9d4a-1fa1bb84e40b']/ul"))));
        		driver.findElement(By.xpath("//*[@id='1f607697-e9e7-4740-9d4a-1fa1bb84e40b']/ul/li[2]/div/span[2]")).click();	
        		Thread.sleep(500);
        		/*	try 
        		{
        			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class='k-selectable']")));	//Wait till records table gets visible
        		}
        		catch(Exception e)
        		{
        			
        		}
        		
        		js.executeScript("window.scrollBy(0,500)");					//Scrolling down window by 2600 px.
        		Thread.sleep(500);
        		String s1 = CFOcountPOM.readTotalItemsD(driver).getText();	//Reading the total items count in String
        		String[] bits = s1.split(" ");								//Splitting the String
        		String itomsCount = bits[bits.length - 2];					//Getting the second last word (total number of items)
        		
        		if(bits.length - 2 < 2)
        		{
        			Thread.sleep(1000);
        			s1 = CFOcountPOM.readTotalItemsD(driver).getText();	//Reading the total items count in String
        			bits = s1.split(" ");								//Splitting the String
        			itomsCount = bits[bits.length - 2];					//Getting the second last word (total number of items)
        		}
        		if(itomsCount.equalsIgnoreCase("to"))						//If no records found, it shows 'No items to display'
        		{
        			test.log(LogStatus.INFO, "No records found for '"+Compliance+"'.");
        			driver.navigate().refresh();
        		}
        		else
        		{
        			js.executeScript("window.scrollBy(0,-1000)");					//Scrolling down window by 2600 px.
        			
        		//	String file = "//home//ashitosh-avantis//Downloads//Detailed Report .xlsx";
        			String file = "C:/Users/sandip/Downloads/Detailed Report .xlsx";
        			File dir = new File("C:/Users/sandip/Downloads/");
        			File[] allFiles = dir.listFiles();					//Counting number of files in directory before download
        			
        			Thread.sleep(1000);
        			CFOcountPOM.clickExportImage(driver).click();			//Exporting (Downloading) file
        			
        			Thread.sleep(4000);
        			File dir1 = new File("C:/Users/sandip/Downloads/");
        			File[] allFilesNew = dir1.listFiles();					//Counting number of files in directory after download
        			
        			if(allFiles.length < allFilesNew.length)
        			{
        				test.log(LogStatus.PASS, Compliance+" :- File downloaded successfully.");
        				
        				File lastModifiedFile = allFilesNew[0];			//Storing any 0th index file in 'lastModifiedFile' file name.
        			    for (int i = 1; i < allFilesNew.length; i++) 	//For loop till the number of files in directory.
        			    {
        			       if (lastModifiedFile.lastModified() < allFilesNew[i].lastModified()) 	//If allFilesNew[i] file is having large/latest time time of update then latest modified file be allFilesNew[i] file.
        			       {
        			           lastModifiedFile = allFilesNew[i];
        			       }
        			    }
        				
        				Thread.sleep(500);		
        				fis = new FileInputStream(lastModifiedFile);	//Provided last modified / latest downloaded file.
        				workbook = new XSSFWorkbook(fis);
        				sheet = workbook.getSheetAt(0);					//Retrieving first sheet of Workbook
        				Row row4 = sheet.getRow(3);						//Selected 3rd index row (Fourth row)
        				Cell c1 = row4.createCell(0);					//Selected cell (4th row, 1st column)
        				c1.setCellValue("Test");						//Entered temp data at empty row, so that it could make easy to get Last Row Number
        				FileOutputStream fos = new FileOutputStream(lastModifiedFile);
        				workbook.write(fos);
        				fos.close();
        				
        				int no = sheet.getLastRowNum();
        				int SheetRecords = no - 4;						//Sheet have extra 5 lines of information at top (But row count started from 0, so -4)
        				
        				
        				js.executeScript("window.scrollBy(0,500)");					//Scrolling down window by 2600 px.
        				
        				if(Compliance.equalsIgnoreCase("Statutory CheckList"))
        					Thread.sleep(1500);
        				else
        					Thread.sleep(500);
        				CFOcountPOM.readTotalItemsD(driver);				//clicking on Total items count to scroll down.
        				
        				String s11 = CFOcountPOM.readTotalItemsD(driver).getText();	//Reading the total items count in String
        				String[] bits1 = s11.split(" ");								//Splitting the String
        				String itomsCount1 = bits1[bits1.length - 2];					//Getting the second last word (total number of items)
        				int count;
        				if(itomsCount1.equalsIgnoreCase("to"))						//If no records found, it shows 'No items to display'
        				{
        					count = 0;
        				}
        				else
        				{
        					count = Integer.parseInt(itomsCount1);
        				}
        				
        				if(count == SheetRecords)
        				{
        					test.log(LogStatus.PASS,  " :- No of Records in sheet matches to the number of items.");
        					test.log(LogStatus.INFO, "Number of records in downloaded sheet = " +SheetRecords+ " | No of items in grid = "+count);
        				}
        				else
        				{
        					test.log(LogStatus.FAIL,  " :- No of Records in sheet doesn't matches to the number of items.");
        					test.log(LogStatus.INFO, "Number of records in downloaded sheet = " +SheetRecords+ " | No of items in grid = "+count);
        				}
        			}
        			else
        			{
        				test.log(LogStatus.FAIL, " :- File does not downloaded.");
        			}
        		}*/
        		
        	}
	//@AfterTest
	void Closing() throws InterruptedException
	{
		//Thread.sleep(2000);
		//driver.close();
	}
}
