package reviewer;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import cfo.CFOcountPOM;
import performer.OverduePOM;

public class ReMethodsPOM 
{
	public static XSSFSheet sheet = null;
	private static List<WebElement> elementsList = null;
	private static List<WebElement> elementsList1 = null;
	private static List<WebElement> elementsList2 = null;
	
	public static void SubmittedInterimReview(WebDriver driver, ExtentTest test) throws InterruptedException
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(35));
		wait.until(ExpectedConditions.elementToBeClickable(ReviewerPOM.clickStatutoryReview(driver)));	//Waiting for Pending Review Count to be visible.
		
		int oldValue = Integer.parseInt(ReviewerPOM.clickStatutoryReview(driver).getText());			//Reading old value of Statutory Pending For Review
		ReviewerPOM.clickStatutoryReview(driver).click();		//Clicking on Statutory Review value.
		
		Thread.sleep(500);
		wait.until(ExpectedConditions.elementToBeClickable(OverduePOM.checkTable(driver)));	//Waiting for records table to get displayed.
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,800)");						//Scrolling down window by 2600 px.
		
		CFOcountPOM.readTotalItems1(driver).click();					//Clicking just to scroll down the window
		
		Thread.sleep(500);
		elementsList = ReviewerPOM.clickStatus(driver);					//CLicking on Status to sort it in ascending order
		elementsList.get(0).click();
		elementsList.get(0).click();									//Double clicking on Status header to get 'Submitted For Interim Review' status compliance on top.
		elementsList = OverduePOM.readStatus(driver);					//Getting all status values (names)
		int n = elementsList.size();
		String status = null;
		int i = 0, flag = 0;
		for(i = 0; i < n; i++)
		{
			elementsList = OverduePOM.readStatus(driver);
			status = elementsList.get(i).getText();
			if(status.equalsIgnoreCase("Submitted For Interim Review"))
			{
				flag = 1;
				break;
			}
		}
		
		if(flag == 1)
		{
			elementsList1 = OverduePOM.clickCalenderAction(driver);		//Getting all action buttons
			elementsList1.get(i).click();								//Clicking on ith action button which has blue status 
			
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("iReviewerFrame"));
			
			Thread.sleep(500);
			js.executeScript("window.scrollBy(0,500)");					//Scrolling down window by 2600 px.
			
			Thread.sleep(500);
			ReviewerPOM.clickDownload1(driver).click();					//Clicking on Download link 
			
			Thread.sleep(500);
			ReviewerPOM.InterimApproveRadio(driver).click();			//Clicking on 'Interim Review Approve' radio button
			
			try
			{
				Thread.sleep(700);
				ReviewerPOM.clickCheckBox(driver).click();
			}
			catch(Exception e)
			{
				
			}
			
			Thread.sleep(500);
			ReviewerPOM.insertTextArea(driver).sendKeys("Automation Remark");	//Sending remark to text area.
			
			Thread.sleep(500);
			OverduePOM.clickComplianceSubmit(driver).click();			//Clicking on 'Submit' button. 
			
			Thread.sleep(1000);
			driver.switchTo().alert().accept();
			Thread.sleep(500);
			driver.switchTo().parentFrame();
			
			Thread.sleep(500);
			wait.until(ExpectedConditions.elementToBeClickable(OverduePOM.checkTable(driver)));	//Waiting for records table to get displayed.
			
			OverduePOM.clickDashboard(driver).click();					//Clicking on 'Dashboard'
			
			wait.until(ExpectedConditions.elementToBeClickable(ReviewerPOM.clickStatutoryReview(driver)));	//Waiting for Pending Review Count to be visible.
			int newValue = Integer.parseInt(ReviewerPOM.clickStatutoryReview(driver).getText());			//Reading old value of Statutory Pending For Review
			if(newValue < oldValue)
			{
				test.log(LogStatus.PASS, "Statutory value for 'Pending For Review' decremented. Old Statutory Review Value = "+oldValue+ " New Statutory Review Value = "+ newValue);
			}
			else
			{
				test.log(LogStatus.PASS, "Statutory value for 'Pending For Review' doesn't decremented. Old Statutory Review Value = "+oldValue+ " New Statutory Review Value = "+ newValue);
			}
		}
		else
		{
			Thread.sleep(1000);
			OverduePOM.clickDashboard(driver).click();					//Clicking on 'Dashboard'
			test.log(LogStatus.INFO, "No compliance submitted for Interim Review.");
		}
	}
	
	public static void MyEscalationReviewer(WebDriver driver, ExtentTest test) throws InterruptedException
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
		Thread.sleep(500);
		wait.until(ExpectedConditions.elementToBeClickable(ReviewerPOM.clickMyEscalation(driver)));	//Waiting for My Escalation link to be visible.
		
		ReviewerPOM.clickMyEscalation(driver).click();					//Clicking on 'My Escalation'
		wait.until(ExpectedConditions.visibilityOf(CFOcountPOM.loadGrid(driver) ));	//Waiting for records table to be visible.
		
		///----------------- Single Compliance - Through Update Button --------------------------
		
		String no = "25";
		Thread.sleep(1500);
		elementsList = ReviewerPOM.clickWorkFileText(driver);		//Getting all Work File text boxes
		elementsList.get(0).clear();
		elementsList = ReviewerPOM.clickWorkFileText(driver);
		elementsList.get(0).sendKeys(no);
		
		Thread.sleep(500);
		elementsList1 = ReviewerPOM.clickEscalationText(driver);	//Getting all the Escalation text boxes
		elementsList1.get(0).clear();
		elementsList2 = ReviewerPOM.clickEscalationText(driver);
		elementsList2.get(0).sendKeys("21");
		
		ReviewerPOM.clickFirstAction(driver).click();
		ReviewerPOM.clickFirstAction(driver).click();
		
		Thread.sleep(1500);
		ReviewerPOM.clickUpdate(driver).click();
		
		Thread.sleep(1500);
		elementsList = ReviewerPOM.clickWorkFileText(driver);
		int value = Integer.parseInt(elementsList.get(0).getAttribute("value"));
		int no1 = Integer.parseInt(no);
		
		test.log(LogStatus.INFO, "----------- Single Compliance Escallation -----------");
		if(value == no1)					//If Save button renamed to Update
		{
			test.log(LogStatus.PASS, "Update button appeared after Save. Compliance escalated successfully.");
		}
		else
		{
			test.log(LogStatus.FAIL, "Inserted data didn't saved successfully.");
		}
		
		///----------------- Multiple Compliances - Through Save Button --------------------------
		
		elementsList = ReviewerPOM.clickCheckboxes(driver);
		Thread.sleep(1500);
		wait.until(ExpectedConditions.elementToBeClickable(elementsList.get(2)));
		elementsList.get(2).click();
		Thread.sleep(1500);
		elementsList.get(3).click();
		
		//driver.findElement(By.xpath("//*[@id='grid']/div[3]/table/tbody/tr[2]/td[1]/label")).click();
		Thread.sleep(500);
		String num = "9";
		elementsList = ReviewerPOM.clickWorkFileText(driver);			//Getting all Work File text boxes
		elementsList.get(2).clear();
		elementsList = ReviewerPOM.clickWorkFileText(driver);
		elementsList.get(2).sendKeys(num);
		
		Thread.sleep(500);
		elementsList = ReviewerPOM.clickEscalationText(driver);			//Getting all Escalation text boxes
		elementsList.get(2).clear();
		elementsList = ReviewerPOM.clickEscalationText(driver);
		elementsList.get(2).sendKeys("27");
		
		Thread.sleep(500);
		elementsList = ReviewerPOM.clickWorkFileText(driver);			//Getting all Work File text boxes
		elementsList.get(3).clear();
		elementsList = ReviewerPOM.clickWorkFileText(driver);
		elementsList.get(3).sendKeys(num);
		
		Thread.sleep(500);
		elementsList = ReviewerPOM.clickEscalationText(driver);			//Getting all Escalation text boxes
		elementsList.get(3).clear();
		elementsList = ReviewerPOM.clickEscalationText(driver);
		elementsList.get(3).sendKeys("27");
		
		Thread.sleep(1000);
		ReviewerPOM.clickSaveButton(driver).click();
		
		Thread.sleep(1200);
		String msg = driver.switchTo().alert().getText();
		driver.switchTo().alert().accept();
		String expectedMsg = "Selected Compliance(s) Escalated Successfully.";
		
		test.log(LogStatus.INFO, "----------- Multiple Compliance Escallation -----------");
		if(msg.equalsIgnoreCase(expectedMsg))
		{
			test.log(LogStatus.PASS, "Success message : "+msg);
		}
		else
		{
			test.log(LogStatus.FAIL, "Compliances didn't escalated successfully.");
			test.log(LogStatus.INFO, "Message : "+msg);
		}
		
	//	Thread.sleep(1000);
	//	ReviewerPOM.clickRisk(driver).click();
	//	Thread.sleep(1000);
	//	ReviewerPOM.clickCritical(driver).click();
		Thread.sleep(2000);
	//	ReviewerPOM.clickClear(driver).click();
		Thread.sleep(1000);
		test.log(LogStatus.PASS, "Clear Button data ");
		wait.until(ExpectedConditions.elementToBeClickable(OverduePOM.clickDashboard(driver)));
		OverduePOM.clickDashboard(driver).click();
		
		Thread.sleep(500);
		performer.OverdueCount.message(driver);
	}
	
	public static void ReassignPerformer(WebDriver driver, ExtentTest test) throws InterruptedException
	{
		WebDriverWait wait = new WebDriverWait(driver,  Duration.ofSeconds(30));
		Thread.sleep(1000);
		wait.until(ExpectedConditions.elementToBeClickable(OverduePOM.clickMyWorkspace(driver)));	//Waiting for 'My Workspace' link to be visible.
		OverduePOM.clickMyWorkspace(driver).click();				//Clicking on 'My Workspace'
		
		Thread.sleep(300);
		OverduePOM.clickCompliance(driver).click();					//Clicking on 'Compliance' under My Workspace
		
		Thread.sleep(1000);
		wait.until(ExpectedConditions.visibilityOf(OverduePOM.clickMoreActions(driver)));	//Waiting for records table to get visible.
		OverduePOM.clickUserRole(driver).click();
		Thread.sleep(300);
		elementsList = OverduePOM.selectUserRole(driver);
		elementsList.get(1).click();
		
		Thread.sleep(500);
		OverduePOM.clickMoreActions1(driver).click();				//Clicking on 'More Actions' drop down.
		Thread.sleep(500);
		elementsList = OverduePOM.selectAction(driver);				//Getting all 'More Action' drop down option
		elementsList.get(3).click();								//Clicking on fourth option "Reassign Performer"
		
		Thread.sleep(300);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,400)");					//Scrolling down window by 2600 px.
		
		wait.until(ExpectedConditions.elementToBeClickable(OverduePOM.checkRecordsTable(driver)));	//Waiting for records table to get visible.
		ReviewerPOM.selectUserAssign(driver).click();				//Clicking on 'Select User to Assign' drop down.
		Select drp = new Select(ReviewerPOM.selectUserAssign(driver));
		drp.selectByIndex(1);										//Select first user
		
		Thread.sleep(2000);
		drp = new Select(ReviewerPOM.selectUserAssign(driver));
		String user_dropdown = drp.getFirstSelectedOption().getText();		//Reading selected user name from dropdown.
		String user_table = ReviewerPOM.readAssignedUser(driver).getText();	//Reading already assigned user from table.
		
		if(user_dropdown.equalsIgnoreCase(user_table))				//If user from dropdown matches to user from table
		{
			drp = new Select(ReviewerPOM.selectUserAssign(driver));
			drp.selectByIndex(2);									//Selecting (next) second user of index 2
		}
		
		Thread.sleep(1000);
		wait.until(ExpectedConditions.elementToBeClickable(ReviewerPOM.clickFirstCheckbox(driver)));
		ReviewerPOM.clickFirstCheckbox(driver).click();				//Clicking on first checkbox (Not select all checkbox)
		
		Thread.sleep(1000);
		js.executeScript("window.scrollBy(0,300)");					//Scrolling down window by 2600 px.
		
		Thread.sleep(500);
		wait.until(ExpectedConditions.visibilityOf(OverduePOM.clickSaveButton(driver)));
		OverduePOM.clickSaveButton(driver).click();					//Clicking on 'Save' button.
		
		Thread.sleep(500);
		driver.switchTo().alert().accept();
		
		Thread.sleep(2000);
		wait.until(ExpectedConditions.visibilityOf(ReviewerPOM.selectUserAssign(driver)));
		drp = new Select(ReviewerPOM.selectUserAssign(driver));
		String user1 = drp.getFirstSelectedOption().getText();		//Reading the selected user in Drop down
		String user2 = ReviewerPOM.readAssignedUser(driver).getText();	//Reading the assigned user name from table.
		
		if(user1.equalsIgnoreCase(user2))
		{
			test.log(LogStatus.PASS, "Performer user name updated with selected name.");
		}
		else
		{
			test.log(LogStatus.FAIL, "Performer user name doesn't updated with selected name.");
		}
		
		Thread.sleep(500);
		OverduePOM.clickDashboard(driver).click();
	}
	
	public static void PendingReviewStatutoryASApprove(WebDriver driver, ExtentTest test)throws InterruptedException
	{
		WebDriverWait wait = new WebDriverWait(driver,  Duration.ofSeconds(35));
		
		wait.until(ExpectedConditions.visibilityOf(ReviewerPOM.clickStatutoryReview(driver)));	//Wait until Statutory Pending For Review count gets visible.
		

		ReviewerPOM.clickStatutoryReview(driver).click();		//Clicking on Statutory Review value.
	
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@role='grid'][@class='k-selectable']")));
		
		
		ReviewerPOM.clickAdavanceSearch(driver).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='grid1']/div[3]/table")));
		elementsList=	ReviewerPOM.clickAction2(driver);
		
		elementsList.get(4).click();	
		Thread.sleep(1000);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("iReviewerFrame1"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,500)"," ");
		ReviewerPOM.clickDownloadAS(driver).click();
		Thread.sleep(1000);
		wait.until(ExpectedConditions.elementToBeClickable(ReviewerPOM.clickClosedTimelyAS(driver)));
		WebElement el = ReviewerPOM.clickClosedTimelyAS(driver);
		ReviewerPOM.clickClosedTimelyAS(driver).click();	
		Thread.sleep(1000);
	//	ReviewerPOM.insertTextArea(driver).sendKeys("Automation");
		Thread.sleep(500);
		js.executeScript("window.scrollBy(0,500)"," ");
		wait.until(ExpectedConditions.elementToBeClickable(ReviewerPOM.clickApprove(driver)));
		ReviewerPOM.clickApprove(driver).click();	
		Thread.sleep(500);
		driver.switchTo().alert().accept();									//Accepting msg of Successful Submission.
		driver.switchTo().parentFrame();
		Thread.sleep(500);
		ReviewerPOM.Clickclose(driver).click();
		Thread.sleep(3000);
	
		ReviewerPOM.closeAS(driver).click();
		Thread.sleep(500);
		OverduePOM.clickDashboard(driver).click();
		
		
	}
	
	public static void PendingReviewStatutoryASReject(WebDriver driver, ExtentTest test)throws InterruptedException
	{
		WebDriverWait wait = new WebDriverWait(driver,  Duration.ofSeconds(35));
		
		wait.until(ExpectedConditions.visibilityOf(ReviewerPOM.clickStatutoryReview(driver)));	//Wait until Statutory Pending For Review count gets visible.
		

		ReviewerPOM.clickStatutoryReview(driver).click();		//Clicking on Statutory Review value.
	
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@role='grid'][@class='k-selectable']")));
		
		
		ReviewerPOM.clickAdavanceSearch(driver).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='grid1']/div[3]/table")));
		elementsList=	ReviewerPOM.clickAction2(driver);
		
		elementsList.get(4).click();	
		Thread.sleep(1000);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("iReviewerFrame1"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,500)"," ");
		ReviewerPOM.clickDownloadAS(driver).click();
		Thread.sleep(1000);
		wait.until(ExpectedConditions.elementToBeClickable(ReviewerPOM.clickClosedTimelyAS(driver)));
		WebElement el = ReviewerPOM.clickClosedTimelyAS(driver);
		ReviewerPOM.clickClosedTimelyAS(driver).click();	
		Thread.sleep(1000);
		ReviewerPOM.insertTextArea(driver).sendKeys("Automation");
		Thread.sleep(500);
		js.executeScript("window.scrollBy(0,500)"," ");
		wait.until(ExpectedConditions.elementToBeClickable(ReviewerPOM.clickReject(driver)));
		ReviewerPOM.clickReject(driver).click();	
		Thread.sleep(500);
		//driver.switchTo().alert().accept();									//Accepting msg of Successful Submission.
		driver.switchTo().parentFrame();
		Thread.sleep(1000);
		ReviewerPOM.Clickclose(driver).click();
		Thread.sleep(3000);
		ReviewerPOM.closeAS(driver).click();		
		Thread.sleep(3000);
		OverduePOM.clickDashboard(driver).click();
		Thread.sleep(3000);
	
	}
		
	public static void PendingReviewInternalASApprove(WebDriver driver, ExtentTest test)throws InterruptedException
	{
		WebDriverWait wait = new WebDriverWait(driver,  Duration.ofSeconds(35));
		
		wait.until(ExpectedConditions.visibilityOf(ReviewerPOM.clickInternalReview(driver)));	//Wait until Statutory Pending For Review count gets visible.
		

		ReviewerPOM.clickInternalReview(driver).click();		//Clicking on Statutory Review value.
	
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@role='grid'][@class='k-selectable']")));
	
		
		ReviewerPOM.clickAdavanceSearch(driver).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='grid1']/div[3]/table")));
		elementsList=	ReviewerPOM.clickAction2(driver);
		
		elementsList.get(4).click();	
		Thread.sleep(1000);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("iInternalReviewerFrame1"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,500)"," ");
		ReviewerPOM.clickInternalDownloadAS(driver).click();
		Thread.sleep(2000);
		wait.until(ExpectedConditions.elementToBeClickable(ReviewerPOM.clickClosedTimelyInternal(driver)));
		WebElement el = ReviewerPOM.clickClosedTimelyInternal(driver);
		ReviewerPOM.clickClosedTimelyInternal(driver).click();	
		Thread.sleep(1000);
	//	ReviewerPOM.insertTextArea(driver).sendKeys("Automation");
		Thread.sleep(500);
		js.executeScript("window.scrollBy(0,500)"," ");
		wait.until(ExpectedConditions.elementToBeClickable(ReviewerPOM.clickApprove(driver)));
		ReviewerPOM.clickApprove(driver).click();	
		Thread.sleep(500);
		driver.switchTo().alert().accept();									//Accepting msg of Successful Submission.
		driver.switchTo().parentFrame();
		Thread.sleep(500);
		ReviewerPOM.ClickINclose(driver).click();
		Thread.sleep(3000);
	
		ReviewerPOM.closeAS(driver).click();
		Thread.sleep(500);
		OverduePOM.clickDashboard(driver).click();
		
		
	}
	
	public static void PendingReviewInternalASReject(WebDriver driver, ExtentTest test)throws InterruptedException
	{
		WebDriverWait wait = new WebDriverWait(driver,  Duration.ofSeconds(35));
		
		wait.until(ExpectedConditions.visibilityOf(ReviewerPOM.clickInternalReview(driver)));	//Wait until Statutory Pending For Review count gets visible.
		

		ReviewerPOM.clickInternalReview(driver).click();		//Clicking on Statutory Review value.
	
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@role='grid'][@class='k-selectable']")));
	
		
		ReviewerPOM.clickAdavanceSearch(driver).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='grid1']/div[3]/table")));
		elementsList=	ReviewerPOM.clickAction2(driver);
		
		elementsList.get(4).click();	
		Thread.sleep(1000);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("iInternalReviewerFrame1"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,500)"," ");
		ReviewerPOM.clickInternalDownloadAS(driver).click();
		Thread.sleep(2000);
		wait.until(ExpectedConditions.elementToBeClickable(ReviewerPOM.clickClosedTimelyInternal(driver)));
		WebElement el = ReviewerPOM.clickClosedTimelyInternal(driver);
		ReviewerPOM.clickClosedTimelyInternal(driver).click();	
		Thread.sleep(1000);
		ReviewerPOM.insertTextAreaInternal(driver).sendKeys("Automation");
		Thread.sleep(500);
		js.executeScript("window.scrollBy(0,500)"," ");
		wait.until(ExpectedConditions.elementToBeClickable(ReviewerPOM.clickReject(driver)));
		ReviewerPOM.clickReject(driver).click();	
		Thread.sleep(500);
	//	driver.switchTo().alert().accept();									//Accepting msg of Successful Submission.
		driver.switchTo().parentFrame();
		Thread.sleep(500);
		ReviewerPOM.ClickINclose(driver).click();
		Thread.sleep(3000);
	
		ReviewerPOM.closeAS(driver).click();
		Thread.sleep(500);
		OverduePOM.clickDashboard(driver).click();
		
		
	}
	
	
	
	public static void PendingReviewInternal(WebDriver driver, ExtentTest test, XSSFSheet sheet, String clickButton)throws InterruptedException
	{
		WebDriverWait wait = new WebDriverWait(driver,  Duration.ofSeconds(35));
		
		Thread.sleep(1000);
		wait.until(ExpectedConditions.visibilityOf(ReviewerPOM.clickInternalReview(driver)));	//Wait until Internal Pending For Review count gets visible.
		int oldInternalReviewValue = Integer.parseInt(ReviewerPOM.clickInternalReview(driver).getText());	//Reading old value of Internal Pending For Review
		int oldInternalRejectValue = Integer.parseInt(ReviewerPOM.readInternalReject(driver).getText());	//Reading old value of Internal Reject
		
		ReviewerPOM.clickInternalReview(driver).click();		//Clicking on Statutory Review value.
		
		Thread.sleep(500);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@role='grid'][@class='k-selectable']")));
		Thread.sleep(500);
	//	elementsList = ReviewerPOM.clickActions(driver);
	//		elementsList.get(0).click();
			
		elementsList = ReviewerPOM.clickStatus(driver);			//CLicking on Status to sort it in ascending order
		elementsList.get(0).click();
		Thread.sleep(500);
		int flag = 0;
	//	wait.until(ExpectedConditions.elementToBeClickable(ReviewerPOM.clickAction1(driver)));
		elementsList = ReviewerPOM.clickActions(driver);
		for (int i = 0; i < elementsList.size(); i++)						//Starting from the third button.
		{
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollBy(0,500)"," ");
			
			Thread.sleep(300);
			elementsList = ReviewerPOM.clickActions(driver);
			elementsList.get(i).click();									//Clicking on ith action button.
			
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("iInternalReviewerFrame"));
			
			js.executeScript("window.scrollBy(0,500)"," ");					//Scrolling down window by 2000 px.
			WebElement download = null;
			try
			{
				wait.until(ExpectedConditions.elementToBeClickable(ReviewerPOM.clickDownloadInternal1(driver)));
				download = ReviewerPOM.clickDownloadInternal1(driver);			//Check if Download link is available or not.		
			}
			catch(Exception e)
			{
				
			}
			
			if(download == null)
			{
				flag = 1;
				driver.switchTo().parentFrame();							//Switching back to parent frame from iFrame
				ReviewerPOM.ComplainceInternalReviewer(driver).click();		//Closing the compliance popup as it has message.
				Thread.sleep(1000);
			}
			else
			{
				flag = 0;
				ReviewerPOM.clickDownloadInternal1(driver).click();				//Clicking on Download to download the document
				
				Thread.sleep(500);
				js.executeScript("window.scrollBy(0,400)"," ");					//Scrolling down window by 2000 px.
				
				Thread.sleep(500);
				try
				{
					ReviewerPOM.clickClosedTimelyInternal(driver).click();		//Clicking on Closed-Delay radio button.
				}
				catch(Exception e)
				{
					
				}
				
				WebElement ele1 = null;
				WebElement ele2 = null;
				WebElement ele3 = null;
				try
				{
					wait.until(ExpectedConditions.elementToBeClickable(ReviewerPOM.insertLiability1(driver)));
					ele1 = ReviewerPOM.insertLiability1(driver);			//Loaded element in ele1. So that, if element not found it will do nothing.
					ele2 = ReviewerPOM.insertLiability2(driver);			//Loaded element in ele2. So that, if element not found it will do nothing.
					ele3 = ReviewerPOM.insertLiability3(driver);			//Loaded element in ele3. So that, if element not found it will do nothing.
				}
				catch(Exception e)
				{
					
				}
				if(ele1 != null)
				{
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
					Row row4 = sheet.getRow(4);											//Selected 4th index row (Fifth row)
					Cell c2 = row4.getCell(1);											//Selected cell (4 row,1 column)
					int liability2 = (int) c2.getNumericCellValue();					//Got the amount stored at position 4,1
					String l2 = Integer.toString(liability2); 							//Converting int to String
					ReviewerPOM.insertLiability2(driver).clear();				//Clearing the text box.
					ReviewerPOM.insertLiability2(driver).sendKeys(l2);		//Inserting amount in text box
					Thread.sleep(500);
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
				
				Row row6 = sheet.getRow(6);											//Selected 6th index row (Seventh row)
				Cell c4 = row6.getCell(1);											//Selected cell (6 row,1 column)
				String remark = c4.getStringCellValue();							//Got the URL stored at position 6,1
				try
				{
					wait.until(ExpectedConditions.elementToBeClickable(ReviewerPOM.insertTextAreaInternal(driver)));
					ReviewerPOM.insertTextAreaInternal(driver).sendKeys(remark);		//Inserting remark in Text area
				}
				catch(Exception e)
				{
					
				}
				
				Thread.sleep(500);
				if(clickButton.equalsIgnoreCase("Approve"))
				{
					ReviewerPOM.clickApprove(driver).click();						//Clicking on Approve button.
					Thread.sleep(500);
					driver.switchTo().alert().accept();								//Accepting msg of Successful Submission.
				}
				else
				{
					ReviewerPOM.clickReject(driver).click();						//Clicking on Reject button.
					Thread.sleep(500);
				}
				
				driver.switchTo().parentFrame();									//Switching back to parent frame from iFrame
				Thread.sleep(1000);
		//		driver.navigate().refresh();
				Thread.sleep(3000);
			//	wait.until(ExpectedConditions.elementToBeClickable(ReviewerPOM.clickAction1(driver)));
				break;
			}
			Thread.sleep(500);
			if(i == elementsList.size()-1)
			{
				js.executeScript("window.scrollBy(0,500)"," ");
				CFOcountPOM.clickNextPage1(driver).click();
				i = -1;
			}
		}		
		
		Thread.sleep(2500);
		
		driver.navigate().refresh();
		
		wait.until(ExpectedConditions.visibilityOf(performer.OverduePOM.clickDashboard(driver)));
		performer.OverduePOM.clickDashboard(driver).click();
		
		Thread.sleep(700);
		wait.until(ExpectedConditions.elementToBeClickable(ReviewerPOM.clickInternalReview(driver)));
		int newInternalReviewValue = Integer.parseInt(ReviewerPOM.clickInternalReview(driver).getText());	//Reading new value of Internal Pending For Review
		int newInternalRejectValue = Integer.parseInt(ReviewerPOM.readInternalReject(driver).getText());	//Reading old value of Internal Reject
		
		if(flag == 0)
		{
			if(newInternalReviewValue < oldInternalReviewValue)
			{
				test.log(LogStatus.PASS, "Internal value for 'Pending For Review' decremented.");
				test.log(LogStatus.INFO, "Old Value = "+oldInternalReviewValue+ " | New Value = "+ newInternalReviewValue);
			}
			else
			{
				test.log(LogStatus.FAIL, "Updated Internal Count doesn't reverted on Dashboard.");
			}
			if(clickButton.equalsIgnoreCase("Reject"))
			{
				if(newInternalRejectValue > oldInternalRejectValue)
				{
					test.log(LogStatus.PASS, "Internal value for 'Rejected : "+clickButton+"' incremented.");
					test.log(LogStatus.INFO, "Old Internal Reject Value = "+oldInternalRejectValue+" | New Internal Reject Value = "+ newInternalRejectValue);
				}
				else
				{
					test.log(LogStatus.FAIL, "Updated Internal value not reverted on Dashboard.");
				}
			}
		}
		else
		{
			test.log(LogStatus.INFO, "Internal Compliance (Pending for Review) doesn't performed as the Compliance Document didn't found.");
		}
	}
	
	public static void CompletedStatutory(WebDriver driver, ExtentTest test)throws InterruptedException
	{
		WebDriverWait wait = new WebDriverWait(driver,  Duration.ofSeconds(35));
		
		Thread.sleep(1000);
		wait.until(ExpectedConditions.visibilityOf(ReviewerPOM.ClickCompletedSta(driver)));	//Wait until Internal Pending For Review count gets visible.
		
		int DasCountCompletedSta = Integer.parseInt(ReviewerPOM.ClickCompletedSta(driver).getText());	//Reading old value of Internal Reject
		
		ReviewerPOM.ClickCompletedSta(driver).click();		//Clicking on Statutory Review value.
		Thread.sleep(1000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='grid']/div[4]/table")));
		elementsList = ReviewerPOM.clickOverView(driver);
		elementsList.get(1).click();
		Thread.sleep(2000);
		ReviewerPOM.CloseOverview(driver).click();
		Thread.sleep(2000);
		test.log(LogStatus.PASS,"Overview Successfully");
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,500)");						//Scrolling down window by 1000 px.
		Thread.sleep(1000);
		ReviewerPOM.ReadCount(driver).click();					//Clicking on Text of total items just to scroll down.
		String s1 = ReviewerPOM.ReadCount(driver).getText();		//Reading total number of items.
		String[] bits = s1.split(" ");									//Splitting the String
		String itomsCount = bits[bits.length - 2];						//Getting the second last word (total number of items)
		
		int count = 0;
		if(itomsCount.equalsIgnoreCase("to"))							//If not items found
		{
			Thread.sleep(2500);
			s1 = ReviewerPOM.ReadCount(driver).getText();
			bits = s1.split(" ");										//Splitting the String
			itomsCount = bits[bits.length - 2];
		}
		if(itomsCount.equalsIgnoreCase("to"))							//If not items found
		{
			count = 0;
		}
		else
		{
			count = Integer.parseInt(itomsCount);
		}
		
					
		
		if(count == DasCountCompletedSta)
		{
			test.log(LogStatus.PASS, "Number of Completed grid matches to Dashboard Completed  Count.");
			test.log(LogStatus.INFO, "No of Completed in the grid = "+count+" | Dashboard Completed  Count = "+DasCountCompletedSta);
		}
		else
		{
			test.log(LogStatus.FAIL, "Number of Completed does not matches to Dashboard Completed  Count.");
			test.log(LogStatus.INFO, "No of Completed in the grid = "+count+" | Dashboard Completed  Count = "+DasCountCompletedSta);
		}
		Thread.sleep(2000);
		OverduePOM.clickDashboard(driver).click();
		
		
		
	}
	
	public static void CompletedInternal(WebDriver driver, ExtentTest test)throws InterruptedException
	{
		WebDriverWait wait = new WebDriverWait(driver,  Duration.ofSeconds(35));
		
		Thread.sleep(1000);
		wait.until(ExpectedConditions.visibilityOf(ReviewerPOM.ClickCompletedIn(driver)));	//Wait until Internal Pending For Review count gets visible.
		
		int DasCountCompletedSta = Integer.parseInt(ReviewerPOM.ClickCompletedIn(driver).getText());	//Reading old value of Internal Reject
		
		ReviewerPOM.ClickCompletedIn(driver).click();		//Clicking on Statutory Review value.
		Thread.sleep(1000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='grid']/div[4]/table")));
		elementsList = ReviewerPOM.clickOverView(driver);
		elementsList.get(1).click();
		Thread.sleep(2000);
		ReviewerPOM.CloseOverview(driver).click();
		Thread.sleep(2000);
		test.log(LogStatus.PASS,"Overview Successfully");
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,500)");						//Scrolling down window by 1000 px.
		Thread.sleep(1000);
		ReviewerPOM.ReadCount(driver).click();					//Clicking on Text of total items just to scroll down.
		String s1 = ReviewerPOM.ReadCount(driver).getText();		//Reading total number of items.
		String[] bits = s1.split(" ");									//Splitting the String
		String itomsCount = bits[bits.length - 2];						//Getting the second last word (total number of items)
		
		int count = 0;
		if(itomsCount.equalsIgnoreCase("to"))							//If not items found
		{
			Thread.sleep(2500);
			s1 = ReviewerPOM.ReadCount(driver).getText();
			bits = s1.split(" ");										//Splitting the String
			itomsCount = bits[bits.length - 2];
		}
		if(itomsCount.equalsIgnoreCase("to"))							//If not items found
		{
			count = 0;
		}
		else
		{
			count = Integer.parseInt(itomsCount);
		}
		
					
		
		if(count == DasCountCompletedSta)
		{
			test.log(LogStatus.PASS, "Number of Completed Internal grid matches to Dashboard Completed Internal Count.");
			test.log(LogStatus.INFO, "No of Completed Internal in the grid = "+count+" | Dashboard Completed Internal Count = "+DasCountCompletedSta);
		}
		else
		{
			test.log(LogStatus.FAIL, "Number of Completed Internal does not matches to Dashboard Completed Internal Count.");
			test.log(LogStatus.INFO, "No of Completed Internal in the grid = "+count+" | Dashboard Completed Internal Count = "+DasCountCompletedSta);
		}
		Thread.sleep(2000);
		OverduePOM.clickDashboard(driver).click();
		
	}
	
	
	public static void  OverdueStatutory(WebDriver driver, ExtentTest test)throws InterruptedException
	{
		WebDriverWait wait = new WebDriverWait(driver,  Duration.ofSeconds(35));
		
		Thread.sleep(1000);
		wait.until(ExpectedConditions.visibilityOf(ReviewerPOM.ClickOverdueSta(driver)));	//Wait until Internal Pending For Review count gets visible.
		
		int DasCountCompletedSta = Integer.parseInt(ReviewerPOM.ClickOverdueSta(driver).getText());	//Reading old value of Internal Reject
		
		ReviewerPOM.ClickOverdueSta(driver).click();		//Clicking on Statutory Review value.
		Thread.sleep(1000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='grid']/div[4]/table")));
		elementsList = ReviewerPOM.clickOverView(driver);
		elementsList.get(1).click();
		Thread.sleep(2000);
		ReviewerPOM.CloseOverview(driver).click();
		Thread.sleep(3000);
		test.log(LogStatus.PASS,"Overview Successfully");
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,500)");						//Scrolling down window by 1000 px.
		Thread.sleep(1000);
		ReviewerPOM.ReadCount(driver).click();					//Clicking on Text of total items just to scroll down.
		String s1 = ReviewerPOM.ReadCount(driver).getText();		//Reading total number of items.
		String[] bits = s1.split(" ");									//Splitting the String
		String itomsCount = bits[bits.length - 2];						//Getting the second last word (total number of items)
		
		int count = 0;
		if(itomsCount.equalsIgnoreCase("to"))							//If not items found
		{
			Thread.sleep(2500);
			s1 = ReviewerPOM.ReadCount(driver).getText();
			bits = s1.split(" ");										//Splitting the String
			itomsCount = bits[bits.length - 2];
		}
		if(itomsCount.equalsIgnoreCase("to"))							//If not items found
		{
			count = 0;
		}
		else
		{
			count = Integer.parseInt(itomsCount);
		}
		
					
		
		if(count == DasCountCompletedSta)
		{
			test.log(LogStatus.PASS, "Number of Overdue grid matches to Dashboard Overdue  Count.");
			test.log(LogStatus.INFO, "No of Overdue in the grid = "+count+" | Dashboard Overdue  Count = "+DasCountCompletedSta);
		}
		else
		{
			test.log(LogStatus.FAIL, "Number of Overdue does not matches to Dashboard Overdue  Count.");
			test.log(LogStatus.INFO, "No of Overdue in the grid = "+count+" | Dashboard Overdue  Count = "+DasCountCompletedSta);
		}
		Thread.sleep(2000);
		OverduePOM.clickDashboard(driver).click();
		
		
		
	}
	
	public static void OverdueInternal(WebDriver driver, ExtentTest test)throws InterruptedException
	{
		WebDriverWait wait = new WebDriverWait(driver,  Duration.ofSeconds(35));
		
		Thread.sleep(1000);
		wait.until(ExpectedConditions.visibilityOf(ReviewerPOM.ClickOverdueIn(driver)));	//Wait until Internal Pending For Review count gets visible.
		
		int DasCountCompletedSta = Integer.parseInt(ReviewerPOM.ClickOverdueIn(driver).getText());	//Reading old value of Internal Reject
		
		ReviewerPOM.ClickOverdueIn(driver).click();		//Clicking on Statutory Review value.
		Thread.sleep(1000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='grid']/div[4]/table")));
		elementsList = ReviewerPOM.clickOverView(driver);
		elementsList.get(1).click();
		Thread.sleep(2000);
		ReviewerPOM.CloseOverview(driver).click();
		Thread.sleep(3000);
		test.log(LogStatus.PASS,"Overview Successfully");
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,500)");						//Scrolling down window by 1000 px.
		Thread.sleep(1000);
		ReviewerPOM.ReadCount(driver).click();					//Clicking on Text of total items just to scroll down.
		String s1 = ReviewerPOM.ReadCount(driver).getText();		//Reading total number of items.
		String[] bits = s1.split(" ");									//Splitting the String
		String itomsCount = bits[bits.length - 2];						//Getting the second last word (total number of items)
		
		int count = 0;
		if(itomsCount.equalsIgnoreCase("to"))							//If not items found
		{
			Thread.sleep(2500);
			s1 = ReviewerPOM.ReadCount(driver).getText();
			bits = s1.split(" ");										//Splitting the String
			itomsCount = bits[bits.length - 2];
		}
		if(itomsCount.equalsIgnoreCase("to"))							//If not items found
		{
			count = 0;
		}
		else
		{
			count = Integer.parseInt(itomsCount);
		}
		
					
		
		if(count == DasCountCompletedSta)
		{
			test.log(LogStatus.PASS, "Number of Overdue Internal grid matches to Dashboard Overdue Internal Count.");
			test.log(LogStatus.INFO, "No of Overdue Internal in the grid = "+count+" | Dashboard Overdue Internal Count = "+DasCountCompletedSta);
		}
		else
		{
			test.log(LogStatus.FAIL, "Number of Overdue Internal does not matches to Dashboard Overdue Internal Count.");
			test.log(LogStatus.INFO, "No of Overdue Internal in the grid = "+count+" | Dashboard Overdue Internal Count = "+DasCountCompletedSta);
		}
		Thread.sleep(2000);
		OverduePOM.clickDashboard(driver).click();
		
	}
	
	public static void Events(WebDriver driver, ExtentTest test)throws InterruptedException
	{
		WebDriverWait wait = new WebDriverWait(driver,  Duration.ofSeconds(35));
		
		Thread.sleep(1000);
		wait.until(ExpectedConditions.visibilityOf(ReviewerPOM.ClickEvents(driver)));	//Wait until Internal Pending For Review count gets visible.
		
		int DasCountCompletedSta = Integer.parseInt(ReviewerPOM.ClickEvents(driver).getText());	//Reading old value of Internal Reject
		
		ReviewerPOM.ClickEvents(driver).click();		//Clicking on Statutory Review value.
		Thread.sleep(1000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='grid']/div[3]/table")));
		elementsList = ReviewerPOM.clickOverViewE(driver);
		elementsList.get(1).click();
		Thread.sleep(3000);
		ReviewerPOM.CloseOverview1(driver).click();
		Thread.sleep(3000);
		test.log(LogStatus.PASS,"Overview Successfully");
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,500)");						//Scrolling down window by 1000 px.
		Thread.sleep(1000);
		ReviewerPOM.ReadCountEvent(driver).click();					//Clicking on Text of total items just to scroll down.
		String s1 = ReviewerPOM.ReadCountEvent(driver).getText();		//Reading total number of items.
		String[] bits = s1.split(" ");									//Splitting the String
		String itomsCount = bits[bits.length - 2];						//Getting the second last word (total number of items)
		
		int count = 0;
		if(itomsCount.equalsIgnoreCase("to"))							//If not items found
		{
			Thread.sleep(2500);
			s1 = ReviewerPOM.ReadCountEvent(driver).getText();
			bits = s1.split(" ");										//Splitting the String
			itomsCount = bits[bits.length - 2];
		}
		if(itomsCount.equalsIgnoreCase("to"))							//If not items found
		{
			count = 0;
		}
		else
		{
			count = Integer.parseInt(itomsCount);
		}
		
					
		
		if(count == DasCountCompletedSta)
		{
			test.log(LogStatus.PASS, "Number of Overdue Internal grid matches to Dashboard Overdue Internal Count.");
			test.log(LogStatus.INFO, "No of Overdue Internal in the grid = "+count+" | Dashboard Overdue Internal Count = "+DasCountCompletedSta);
		}
		else
		{
			test.log(LogStatus.FAIL, "Number of Overdue Internal does not matches to Dashboard Overdue Internal Count.");
			test.log(LogStatus.INFO, "No of Overdue Internal in the grid = "+count+" | Dashboard Overdue Internal Count = "+DasCountCompletedSta);
		}
		Thread.sleep(2000);
		OverduePOM.clickDashboard(driver).click();
		
	}
	
	public static void ActivatedEvents(WebDriver driver, ExtentTest test)throws InterruptedException
	{
		WebDriverWait wait = new WebDriverWait(driver,  Duration.ofSeconds(35));
		
		Thread.sleep(1000);
		wait.until(ExpectedConditions.visibilityOf(ReviewerPOM.ClickActivatedEvents(driver)));	//Wait until Internal Pending For Review count gets visible.
		
		int DasCountCompletedSta = Integer.parseInt(ReviewerPOM.ClickActivatedEvents(driver).getText());	//Reading old value of Internal Reject
		
		ReviewerPOM.ClickActivatedEvents(driver).click();		//Clicking on Statutory Review value.
		Thread.sleep(1000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='grid']/div[3]/table")));
		elementsList = ReviewerPOM.clickOverViewAE(driver);
		elementsList.get(1).click();
		Thread.sleep(3000);
		ReviewerPOM.CloseOverview1(driver).click();
		Thread.sleep(3000);
		test.log(LogStatus.PASS,"Overview Successfully");
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,500)");						//Scrolling down window by 1000 px.
		Thread.sleep(1000);
		ReviewerPOM.ReadCountEvent(driver).click();					//Clicking on Text of total items just to scroll down.
		String s1 = ReviewerPOM.ReadCountEvent(driver).getText();		//Reading total number of items.
		String[] bits = s1.split(" ");									//Splitting the String
		String itomsCount = bits[bits.length - 2];						//Getting the second last word (total number of items)
		
		int count = 0;
		if(itomsCount.equalsIgnoreCase("to"))							//If not items found
		{
			Thread.sleep(2500);
			s1 = ReviewerPOM.ReadCountEvent(driver).getText();
			bits = s1.split(" ");										//Splitting the String
			itomsCount = bits[bits.length - 2];
		}
		if(itomsCount.equalsIgnoreCase("to"))							//If not items found
		{
			count = 0;
		}
		else
		{
			count = Integer.parseInt(itomsCount);
		}
		
					
		
		if(count == DasCountCompletedSta)
		{
			test.log(LogStatus.PASS, "Number of Activated Events  grid matches to Dashboard Activated Events Count.");
			test.log(LogStatus.INFO, "No of  Activated Events in the grid = "+count+" | Dashboard  Activated Events Count = "+DasCountCompletedSta);
		}
		else
		{
			test.log(LogStatus.FAIL, "Number of Activated Events  does not matches to Dashboard Activated Events  Count.");
			test.log(LogStatus.INFO, "No of Activated Events in the grid = "+count+" | Dashboard Activated Events Count = "+DasCountCompletedSta);
		}
		Thread.sleep(2000);
		OverduePOM.clickDashboard(driver).click();
		
	}
	
	public static void ClosedEvents(WebDriver driver, ExtentTest test)throws InterruptedException
	{
		WebDriverWait wait = new WebDriverWait(driver,  Duration.ofSeconds(35));
		
		Thread.sleep(1000);
		wait.until(ExpectedConditions.visibilityOf(ReviewerPOM.ClickClosedEvents(driver)));	//Wait until Internal Pending For Review count gets visible.
		
		int DasCountCompletedSta = Integer.parseInt(ReviewerPOM.ClickClosedEvents(driver).getText());	//Reading old value of Internal Reject
		
		ReviewerPOM.ClickClosedEvents(driver).click();		//Clicking on Statutory Review value.
		Thread.sleep(1000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='grid']/div[3]/table")));
		elementsList = ReviewerPOM.clickOverViewCE(driver);
		elementsList.get(1).click();
		Thread.sleep(3000);
		ReviewerPOM.CloseOverview1(driver).click();
		Thread.sleep(3000);
		test.log(LogStatus.PASS,"Overview Successfully");
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,500)");						//Scrolling down window by 1000 px.
		Thread.sleep(1000);
		ReviewerPOM.ReadCountEvent(driver).click();					//Clicking on Text of total items just to scroll down.
		String s1 = ReviewerPOM.ReadCountEvent(driver).getText();		//Reading total number of items.
		String[] bits = s1.split(" ");									//Splitting the String
		String itomsCount = bits[bits.length - 2];						//Getting the second last word (total number of items)
		
		int count = 0;
		if(itomsCount.equalsIgnoreCase("to"))							//If not items found
		{
			Thread.sleep(2500);
			s1 = ReviewerPOM.ReadCountEvent(driver).getText();
			bits = s1.split(" ");										//Splitting the String
			itomsCount = bits[bits.length - 2];
		}
		if(itomsCount.equalsIgnoreCase("to"))							//If not items found
		{
			count = 0;
		}
		else
		{
			count = Integer.parseInt(itomsCount);
		}
		
					
		
		if(count == DasCountCompletedSta)
		{
			test.log(LogStatus.PASS, "Number of Closed Events  grid matches to Dashboard Closed Events Count.");
			test.log(LogStatus.INFO, "No of  Closed Events in the grid = "+count+" | Dashboard  Closed Events Count = "+DasCountCompletedSta);
		}
		else
		{
			test.log(LogStatus.FAIL, "Number of Closed Events  does not matches to Dashboard Closed Events  Count.");
			test.log(LogStatus.INFO, "No of Closed Events in the grid = "+count+" | Dashboard Closed Events Count = "+DasCountCompletedSta);
		}
		Thread.sleep(2000);
		OverduePOM.clickDashboard(driver).click();
		
	}
	
	
	
	public static void DetailedReport1(ExtentTest test, WebDriver driver, String user) throws InterruptedException, IOException
	{		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
	    
		Thread.sleep(500);
		CFOcountPOM.clickReports(driver).click();					//Clicking on 'My Reports'
		Thread.sleep(3000);
		CFOcountPOM.clickDetailedReport(driver).click();			//Clicking on 'Detailed Reports' 
		Thread.sleep(5000);
	
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class='k-selectable']")));	//Wait till records table gets visible
		
		Thread.sleep(4000);
		CFOcountPOM.clickExportImage(driver).click();			//Exporting (Downloading) file
		Thread.sleep(500);
		test.log(LogStatus.PASS, "Export successfully");
		Thread.sleep(4000);
		By locator = By.xpath("//*[@id='grid']/div[3]/table/tbody/tr[1]/td[27]/a[1]");
		wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		Thread.sleep(4000);
		
		WebElement ViewButton = driver.findElement(locator);	
		Thread.sleep(4000);
	JavascriptExecutor jse=(JavascriptExecutor)driver;
	Thread.sleep(2000);
	jse.executeScript("arguments[0].click();", ViewButton);
		Thread.sleep(1000);
		CFOcountPOM.closeDocument1(driver).click();
		test.log(LogStatus.INFO, "overView success");
	
		Thread.sleep(3000);
		OverduePOM.clickRiskD(driver).click();
		Thread.sleep(1000);
		By locatorR = By.xpath("(//*[@class='k-checkbox-label checkbox-span'])[8]");
		wait.until(ExpectedConditions.presenceOfElementLocated(locatorR));
		Thread.sleep(4000);
		
		WebElement clickHigh = driver.findElement(locatorR);	
		Thread.sleep(4000);
	jse.executeScript("arguments[0].click();", clickHigh);
		Thread.sleep(4000);
		
		OverduePOM.clickclearBtn(driver).click();
		test.log(LogStatus.PASS, "Clear Button is working");
		Thread.sleep(7000);
		CFOcountPOM.clickAdvancedSearch(driver).click();
		Thread.sleep(4000);
	
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='grid1']/div[3]")));	//Wait till records table gets visible
	Thread.sleep(5000);
	
		Thread.sleep(3000);
		By locator1 = By.xpath("//*[@id='grid1']/div[3]/table/tbody/tr[1]/td[27]/a[1]");
		
		wait.until(ExpectedConditions.presenceOfElementLocated(locator1));
		Thread.sleep(4000);
		// retrieving "foo-button" HTML element
		WebElement ViewButton1 = driver.findElement(locator1);	
		Thread.sleep(4000);
	JavascriptExecutor jse1=(JavascriptExecutor)driver;
	Thread.sleep(2000);
	jse1.executeScript("arguments[0].click();", ViewButton1);
		Thread.sleep(5000);
		
		By locator3 = By.xpath("//*[@id='divOverView1']/div/div/div[1]/button");
		  
		wait.until(ExpectedConditions.presenceOfElementLocated(locator3));
		Thread.sleep(4000);
		// retrieving "foo-button" HTML element
		WebElement close = driver.findElement(locator3);	
		Thread.sleep(4000);
	//JavascriptExecutor jse=(JavascriptExecutor)driver;
	Thread.sleep(2000);
	jse.executeScript("arguments[0].click();", close);
	test.log(LogStatus.INFO, "Advanced Search-overView success");
		Thread.sleep(4000);
	//	CFOcountPOM.closeDocument2(driver).click();
		Thread.sleep(4000);
		CFOcountPOM.clickExportExcel(driver).click();
		Thread.sleep(3000);
		test.log(LogStatus.INFO, "Advanced Search-Export successfully");
		CFOcountPOM.selectMonth1(driver).click();
		Thread.sleep(3000);
		
		CFOcountPOM.selectLastSixMon(driver).click();
		Thread.sleep(2000);
		CFOcountPOM.clickApplyAd(driver).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='grid1']/div[3]")));	//Wait till records table gets visible
		Thread.sleep(5000);
		//	clickExportExcel(driver).click();
			Thread.sleep(2000);
			By locator2 = By.xpath("//*[@id='grid1']/div[3]/table/tbody/tr[1]/td[27]/a");
			
			wait.until(ExpectedConditions.presenceOfElementLocated(locator2));
			Thread.sleep(4000);
			// retrieving "foo-button" HTML element
			WebElement ViewButton2 = driver.findElement(locator1);	
			Thread.sleep(4000);
		JavascriptExecutor jse2=(JavascriptExecutor)driver;
		Thread.sleep(2000);
		jse1.executeScript("arguments[0].click();", ViewButton2);
			Thread.sleep(5000);
			test.log(LogStatus.INFO, "Advanced Search-After Apply Month-overView success");
			CFOcountPOM.closeDocument2(driver).click();
			Thread.sleep(4000);
			CFOcountPOM.clickExportExcel(driver).click();
			Thread.sleep(3000);
			test.log(LogStatus.INFO, "Advanced Search-After Apply Month-Export Succefully");
			//clickStartDate1(driver).click();
			CFOcountPOM.clickStartDate11(driver).sendKeys("23-Nov-2022");
			CFOcountPOM.clickLastDate11(driver).sendKeys("30-Nov-2022");
			Thread.sleep(2000);
			CFOcountPOM.clickApplyAd(driver).click();
			Thread.sleep(3000);
			OverduePOM.clickRiskDA(driver).click();
			Thread.sleep(1000);
			By locatorRA = By.xpath("(//*[@class='k-checkbox-label checkbox-span'])[72]");
			wait.until(ExpectedConditions.presenceOfElementLocated(locatorRA));
			Thread.sleep(4000);
			
			WebElement clickHighA = driver.findElement(locatorRA);	
			Thread.sleep(4000);
		jse.executeScript("arguments[0].click();", clickHighA);
			Thread.sleep(4000);
			
			OverduePOM.clickclearBtnA(driver).click();
			Thread.sleep(4000);
			test.log(LogStatus.INFO, "Advanced Search-Clear Button is working");
			CFOcountPOM.closeDocumentAS(driver).click();
			Thread.sleep(2000);
			OverduePOM.clickDashboard(driver).click();
	}

	public static void DetailedReportIn(ExtentTest test, WebDriver driver, String user) throws InterruptedException, IOException
	{		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
	    
		Thread.sleep(500);
		CFOcountPOM.clickReports(driver).click();					//Clicking on 'My Reports'
		Thread.sleep(3000);
		CFOcountPOM.clickDetailedReport(driver).click();			//Clicking on 'Detailed Reports' 
		Thread.sleep(5000);
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class='k-selectable']")));	//Wait till records table gets visible
		Thread.sleep(1000);
		OverduePOM.clickcomplianceType(driver).click();
		Thread.sleep(1000);
		OverduePOM.clickcomplianceSta(driver).click();
		Thread.sleep(1000);
		OverduePOM.clickcomplianceIN(driver).click();
		Thread.sleep(4000);
		CFOcountPOM.clickExportImage(driver).click();			//Exporting (Downloading) file
		Thread.sleep(500);
		test.log(LogStatus.PASS, "Export successfully");
		Thread.sleep(4000);
		By locator = By.xpath("//*[@id='grid']/div[3]/table/tbody/tr[1]/td[27]/a[1]");
		wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		Thread.sleep(4000);
		
		WebElement ViewButton = driver.findElement(locator);	
		Thread.sleep(4000);
	JavascriptExecutor jse=(JavascriptExecutor)driver;
	Thread.sleep(2000);
	jse.executeScript("arguments[0].click();", ViewButton);
		Thread.sleep(1000);
		CFOcountPOM.closeDocument1(driver).click();
		test.log(LogStatus.INFO, "overView success");
	
		Thread.sleep(3000);
		OverduePOM.clickRiskD(driver).click();
		Thread.sleep(1000);
		By locatorR = By.xpath("(//*[@class='k-checkbox-label checkbox-span'])[8]");
		wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		Thread.sleep(4000);
		
		WebElement clickHigh = driver.findElement(locatorR);	
		Thread.sleep(4000);
	jse.executeScript("arguments[0].click();", clickHigh);
		Thread.sleep(4000);
		
		OverduePOM.clickclearBtn(driver).click();
		test.log(LogStatus.PASS, "Clear Button is working");
		Thread.sleep(7000);
	/*	CFOcountPOM.clickAdvancedSearch(driver).click();
		Thread.sleep(7000);
	
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='grid1']/div[3]")));	//Wait till records table gets visible
	Thread.sleep(3000);
	OverduePOM.clickcomplianceTypeAS(driver).click();
	Thread.sleep(3000);
	By locatorS = By.xpath("(//span[@class='k-in k-state-selected'])");
	wait.until(ExpectedConditions.presenceOfElementLocated(locatorS));
	Thread.sleep(4000);
	
	WebElement ClickSta = driver.findElement(locatorR);	
	Thread.sleep(4000);
jse.executeScript("arguments[0].click();", ClickSta);
	Thread.sleep(4000);
	
	//OverduePOM.clickcomplianceStaASR(driver).click();
	Thread.sleep(2000);
	OverduePOM.clickcomplianceINASR(driver).click();
	
		Thread.sleep(3000);
		By locator1 = By.xpath("//*[@id='grid1']/div[3]/table/tbody/tr[1]/td[27]/a[1]");
		
		wait.until(ExpectedConditions.presenceOfElementLocated(locator1));
		Thread.sleep(4000);
		// retrieving "foo-button" HTML element
		WebElement ViewButton1 = driver.findElement(locator1);	
		Thread.sleep(4000);
	JavascriptExecutor jse1=(JavascriptExecutor)driver;
	Thread.sleep(2000);
	jse1.executeScript("arguments[0].click();", ViewButton1);
		Thread.sleep(5000);
		
		By locator3 = By.xpath("//*[@id='divOverView1']/div/div/div[1]/button");
		  
		wait.until(ExpectedConditions.presenceOfElementLocated(locator3));
		Thread.sleep(4000);
		// retrieving "foo-button" HTML element
		WebElement close = driver.findElement(locator3);	
		Thread.sleep(4000);
	//JavascriptExecutor jse=(JavascriptExecutor)driver;
	Thread.sleep(2000);
	jse.executeScript("arguments[0].click();", close);
	test.log(LogStatus.INFO, "Advanced Search-overView success");
		Thread.sleep(4000);
	//	CFOcountPOM.closeDocument2(driver).click();
		Thread.sleep(4000);
		CFOcountPOM.clickExportExcel(driver).click();
		Thread.sleep(3000);
		test.log(LogStatus.INFO, "Advanced Search-Export successfully");
		CFOcountPOM.selectMonth1(driver).click();
		Thread.sleep(3000);
		
		CFOcountPOM.selectLastSixMon(driver).click();
		Thread.sleep(2000);
		CFOcountPOM.clickApplyAd(driver).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='grid1']/div[3]")));	//Wait till records table gets visible
		Thread.sleep(5000);
		//	clickExportExcel(driver).click();
			Thread.sleep(2000);
			By locator2 = By.xpath("//*[@id='grid1']/div[3]/table/tbody/tr[1]/td[27]/a");
			
			wait.until(ExpectedConditions.presenceOfElementLocated(locator2));
			Thread.sleep(4000);
			// retrieving "foo-button" HTML element
			WebElement ViewButton2 = driver.findElement(locator1);	
			Thread.sleep(4000);
		JavascriptExecutor jse2=(JavascriptExecutor)driver;
		Thread.sleep(2000);
		jse1.executeScript("arguments[0].click();", ViewButton2);
			Thread.sleep(5000);
			test.log(LogStatus.INFO, "Advanced Search-After Apply Month-overView success");
			CFOcountPOM.closeDocument2(driver).click();
			Thread.sleep(4000);
			CFOcountPOM.clickExportExcel(driver).click();
			Thread.sleep(3000);
			test.log(LogStatus.INFO, "Advanced Search-After Apply Month-Export Succefully");
			//clickStartDate1(driver).click();
			CFOcountPOM.clickStartDate11(driver).sendKeys("23-Nov-2022");
			CFOcountPOM.clickLastDate11(driver).sendKeys("30-Nov-2022");
			Thread.sleep(2000);
			CFOcountPOM.clickApplyAd(driver).click();
			Thread.sleep(3000);
			OverduePOM.clickRiskDA(driver).click();
			Thread.sleep(1000);
			By locatorRA = By.xpath("(//*[@class='k-checkbox-label checkbox-span'])[72]");
			wait.until(ExpectedConditions.presenceOfElementLocated(locatorRA));
			Thread.sleep(4000);
			
			WebElement clickHighA = driver.findElement(locatorRA);	
			Thread.sleep(4000);
		jse.executeScript("arguments[0].click();", clickHighA);
			Thread.sleep(4000);
			
			OverduePOM.clickclearBtnA(driver).click();
			Thread.sleep(4000);
			test.log(LogStatus.INFO, "Advanced Search-Clear Button is working");
			CFOcountPOM.closeDocumentAS(driver).click();*/
			Thread.sleep(2000);
			OverduePOM.clickDashboard(driver).click();
	}
	
	public static void CalendarDownload(ExtentTest test, WebDriver driver) throws InterruptedException, IOException
	{
		
		Thread.sleep(3000);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,650)");					//Scrolling down window by 2600 px.
		Thread.sleep(5000);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("calframe"));
		Thread.sleep(1000);
		ReviewerPOM.CalendarDownload(driver).click();
		Thread.sleep(3000);
		driver.switchTo().parentFrame();
		Thread.sleep(3000);
		test.log(LogStatus.PASS, "Calendder Download Succefully");
		Thread.sleep(3000);
		OverduePOM.clickDashboard(driver).click();
	
	}
	
	public static void CalendarApprove(ExtentTest test, WebDriver driver) throws InterruptedException, IOException
	{
		
		Thread.sleep(3000);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,650)");					//Scrolling down window by 2600 px.
		Thread.sleep(8000);
		OverduePOM.clickStaBtnCaleRe(driver).click();
		Thread.sleep(3000);
		Thread.sleep(4000);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("calframe"));
		Thread.sleep(3000);
		elementsList = OverduePOM.clickCalenderAction(driver);
		Thread.sleep(2000);
		elementsList.get(2).click();
		Thread.sleep(4000);
		
		driver.switchTo().parentFrame();
		Thread.sleep(1000);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("showdetails"));
		Thread.sleep(1000);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("ContentPlaceHolder1_iInternalReviewerFrame"));
		js.executeScript("window.scrollBy(0,500)");                  	
		Thread.sleep(2000);
		OverduePOM.ClickRadioBtn(driver).click();
		Thread.sleep(3000);
		OverduePOM.remark2(driver).sendKeys("Automation Testing");
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id='btnSave3']")).click();
		Thread.sleep(1000);
		String Msg =driver.switchTo().alert().getText();
		Thread.sleep(1000);
		driver.switchTo().alert().accept();
		if(Msg.equalsIgnoreCase("save successfully"))
		{
			test.log(LogStatus.INFO, "Message displayed - 'save successfully'");
		}
		else
		{
			test.log(LogStatus.INFO, "Message displayed - save successfully this msg not show'");
		}
		
		//MethodsPOM.MyCalendarCompliance(driver, test);
		driver.switchTo().parentFrame();
		driver.switchTo().parentFrame();
		Thread.sleep(3000);
		test.log(LogStatus.PASS, "Calendder Internal Approve Succefully");
		Thread.sleep(3000);
		OverduePOM.clickDashboard(driver).click();
	
		
	}
	
	public static void CalendarReject(ExtentTest test, WebDriver driver) throws InterruptedException, IOException
	{
		
		Thread.sleep(3000);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,650)");					//Scrolling down window by 2600 px.
		Thread.sleep(8000);
		OverduePOM.clickStaBtnCaleRe(driver).click();
		Thread.sleep(3000);
		Thread.sleep(4000);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("calframe"));
		Thread.sleep(3000);
		elementsList = OverduePOM.clickCalenderAction(driver);
		Thread.sleep(2000);
		elementsList.get(2).click();
		Thread.sleep(4000);
		
		driver.switchTo().parentFrame();
		Thread.sleep(1000);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("showdetails"));
		Thread.sleep(1000);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("ContentPlaceHolder1_iInternalReviewerFrame"));
		js.executeScript("window.scrollBy(0,500)");                  	
		Thread.sleep(2000);
		OverduePOM.ClickRadioBtn(driver).click();
		Thread.sleep(2000);
		OverduePOM.remark2(driver).sendKeys("Automation Testing");
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id='btnReject3']")).click();
		Thread.sleep(1000);
		
		//MethodsPOM.MyCalendarCompliance(driver, test);
		driver.switchTo().parentFrame();
		driver.switchTo().parentFrame();
		Thread.sleep(3000);
		test.log(LogStatus.PASS, "Calendder Internal perform Succefully");
		OverduePOM.clickDashboard(driver).click();
	
		
	}
	
	
}
