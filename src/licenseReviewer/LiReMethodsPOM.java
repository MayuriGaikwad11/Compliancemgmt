package licenseReviewer;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import cfo.CFOcountPOM;
import licensePerformer.LiPerformerPOM;
import performer.OverduePOM;
import reviewer.ReviewerPOM;

public class LiReMethodsPOM 
{
	private static List<WebElement> elementsList = null;
	private static List<WebElement> elementsList1 = null;
	
	public static FileInputStream fis = null;	//File input stream variable
	public static XSSFWorkbook wb = null;	//Excel sheet workbook variable
	public static XSSFSheet sheet = null;		//Sheet variable
	public static XSSFSheet sheet1 = null;		//Sheet variable
	
	public static void perform(WebDriver driver, ExtentTest test) throws InterruptedException
	{
		Thread.sleep(500);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		
		try
		{
			Thread.sleep(500);
			String lic_no = LiReviewerPOM.clickLicenseNo(driver).getText();		//Reading License no 
			if(lic_no.equalsIgnoreCase("") || lic_no.equalsIgnoreCase(null))	//If License no text box is not empty.
			{
				LiReviewerPOM.clickLicenseNo(driver).sendKeys("1234");			//Writing License no
			}
			
			Thread.sleep(500);
			String lic_title = LiReviewerPOM.clickLicenseTitle(driver).getText();	//Reading License title
			if(lic_title.equalsIgnoreCase("") || lic_title.equalsIgnoreCase(null))	//If License title text box is not empty.
			{
				LiReviewerPOM.clickLicenseTitle(driver).sendKeys("Title Automation");	//Writing License title
			}
		}
		catch(Exception e)
		{
			
		}
		
		try
		{
			Thread.sleep(700);
			LiReviewerPOM.clickStartDate(driver).click();					//Clicking on Start Date text box
			Thread.sleep(300);
			OverduePOM.selectNextMonth(driver).click();						//Clicking on next month arrow.
			Thread.sleep(300);
			OverduePOM.selectDate2(driver).click();							//Selecting particular date
			
			if(LiReviewerPOM.clickStartDate(driver).getText().equalsIgnoreCase(null))
			{
				Thread.sleep(500);
				LiReviewerPOM.clickStartDate(driver).click();					//Clicking on Start Date text box
				Thread.sleep(300);
				OverduePOM.selectLastMonth(driver).click();						//Clicking on next month arrow.
				Thread.sleep(300);
				OverduePOM.selectDate2(driver).click();							//Selecting particular date
			}
		}
		catch(Exception e)
		{
			
		}
		
		try
		{
			Thread.sleep(500);
			LiReviewerPOM.clickEndDate(driver).click();						//Clicking on End Date text box
			Thread.sleep(300);
			OverduePOM.selectNextMonth(driver).click();						//Clicking on next month arrow.
			OverduePOM.selectNextMonth(driver).click();						//Clicking on next month arrow.
			Thread.sleep(300);
			OverduePOM.selectDate3(driver).click();							//Selecting particular date
		}
		catch(Exception e)
		{
			
		}
		
		Thread.sleep(300);
		js.executeScript("window.scrollBy(0,500)");					//Scrolling down window by 500 px.
		
		try
		{
			Thread.sleep(500);
			LiReviewerPOM.clickDate(driver).click();						//Clicking on 'Date' input box
			Thread.sleep(300);
			OverduePOM.selectDate2(driver).click();							//Selecting particular date
		}
		catch(Exception e)
		{
			
		}
		
		try
		{
			String workingDir = System.getProperty("user.dir");
			OverduePOM.fileUploadStatutory(driver).sendKeys(workingDir+"//Reports//PerformerResults.html");	//Uploading file by sending file to Upload Button. (Statutory)
			Thread.sleep(1000);
		}
		catch(Exception e)
		{
			
		}
		
		try
		{
			String workingDir = System.getProperty("user.dir");
			OverduePOM.fileUploadInternal(driver).sendKeys(workingDir+"//Reports//PerformerResults.html");	//Uploading file by sending file to Upload Button. (Internal)
			Thread.sleep(1000);
		}
		catch(Exception e)
		{
			
		}
		
		try									//Statutory compliance document link.
		{
			if(LiPerformerPOM.clickComplDocAddButton(driver) != null)
			{
				LiPerformerPOM.clickComplDoc(driver).sendKeys("www.google.com");	//Providing compliance document link.
				Thread.sleep(500);
				LiPerformerPOM.clickComplDocAddButton(driver).click();				//Clicking on 'Add Link' button.
			}
		}
		catch(Exception e)
		{
			
		}
		
		try									//Internal compliance document link.
		{
			if(LiPerformerPOM.clickComplDocAddButton(driver) != null)
			{
				LiPerformerPOM.clickComplDocInternal(driver).sendKeys("www.google.com");	//Providing compliance document link.
				Thread.sleep(500);
				LiPerformerPOM.clickComplDocAddButton(driver).click();				//Clicking on 'Add Link' button.
				Thread.sleep(500);
			}
		}
		catch(Exception e)
		{
			
		}
		
		Thread.sleep(300);
		js.executeScript("window.scrollBy(0,900)");					//Scrolling down window by 500 px.
		
		try
		{
			Thread.sleep(500);
			LiReviewerPOM.clickDate1(driver).click();					//Clicking on 'Date' input box for(Performer - Expiring count verification)
			Thread.sleep(300);
			WebElement ele = null;
			ele = OverduePOM.selectLastMonth(driver);
			if(ele.equals(null))
			{
				OverduePOM.selectNextMonth(driver).click();						//Clicking on next month arrow.
			}
			else
			{
				OverduePOM.selectLastMonth(driver).click();						//Clicking on next month arrow.
			}
			
			Thread.sleep(300);
			OverduePOM.selectDate2(driver).click();						//Selecting particular date
		}
		catch(Exception e)
		{
			
		}
		
		try
		{
			Thread.sleep(500);					//Select date for Internal.
			OverduePOM.selectDateInternal(driver).click();				//Clicking on 'Date' input box for(Performer - Expiring count verification)
			Thread.sleep(300);
			OverduePOM.selectLastMonth(driver).click();					//Clicking on Last month
			Thread.sleep(300);
			OverduePOM.selectDate2(driver).click();						//Selecting particular date
		}
		catch(Exception e)
		{
			
		}
		
		Thread.sleep(500);
		
		try
		{
			LiReviewerPOM.clikTextArea(driver).sendKeys("Automation Remark");	//Inserting 'Remark'
		}
		catch(Exception e)
		{
			
		}
		
		try
		{
			OverduePOM.remark(driver).sendKeys("Automation Remark");	//Inserting 'Remark'
		}
		catch(Exception e)
		{
			
		}
		
		try
		{
			OverduePOM.clickInternalRemark(driver).sendKeys("Automation Remark");	//Inserting internal 'Remark'
		}
		catch(Exception e)
		{
			
		}
		
		try
		{
			LiReviewerPOM.clikTextArea1(driver).sendKeys("Automation Remark");	//Inserting 'Remark'
		}
		catch(Exception e)
		{
			
		}
	}
	
	public static void PendingReviewCount(WebDriver driver, ExtentTest test, XSSFWorkbook workbook) throws InterruptedException
	{		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		try
		{
			Thread.sleep(400);
			wait.until(ExpectedConditions.invisibilityOf(LiPerformerPOM.Progress(driver)));
		}
		catch(Exception e)
		{
			
		}
		
		Thread.sleep(500);
		wait.until(ExpectedConditions.visibilityOf(LiPerformerPOM.clickPendingForReview(driver)));		
		int pending = Integer.parseInt(LiPerformerPOM.clickPendingForReview(driver).getText());	//Reading 'Pending For Review' count
		int applied = Integer.parseInt(LiPerformerPOM.clickApplied(driver).getText());	//Reading 'Applied' count
		LiPerformerPOM.clickPendingForReview(driver).click();		//Clicking on 'Pending For Review' image link
		
		Thread.sleep(500);
		try
		{
			Thread.sleep(400);
			wait.until(ExpectedConditions.invisibilityOf(LiPerformerPOM.Progress(driver)));
		}
		catch(Exception e)
		{
			
		}
		
		wait.until(ExpectedConditions.visibilityOf(LiReviewerPOM.checkTable(driver)));
		LiReviewerPOM.clickReviewer(driver).click();
		
		Thread.sleep(1000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,1000)");					//Scrolling down window by 500 px.
		int total = Integer.parseInt(OverduePOM.readReminder(driver).getText());	//Reading total records count
		
		if(pending == total)
		{
			test.log(LogStatus.PASS, "Dashboard 'Pending Review' count matches to total records displayed.");
			test.log(LogStatus.INFO, "Dashboard 'Pending Review' count = "+pending+" | Total records in grid = "+total);
		}
		else
		{
			test.log(LogStatus.FAIL, "Dashboard 'Pending Review' count doesn't matches to total records displayed.");
			test.log(LogStatus.INFO, "Dashboard 'Pending Review' count = "+pending+" | Total records in grid = "+total);
		}
		
		Thread.sleep(500);
		elementsList = LiReviewerPOM.clickAction(driver);
		int n = elementsList.size();
		int k = 1;
		for(int i = 1; i < n; i++)
		{
			
			elementsList = LiReviewerPOM.clickAction(driver);
			elementsList.get(i).click();									//Clicking on ith Action button.
			Thread.sleep(500);
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("showdetails"));
			
			Thread.sleep(500);
			js.executeScript("window.scrollBy(0,700)");
			if(ReviewerPOM.clickView1(driver).isDisplayed())
			{
				Thread.sleep(1000);
				ReviewerPOM.clickView1(driver).click();							//Clicking on 'View' link
				
				Thread.sleep(3000);
				try
				{
					ReviewerPOM.clickCloseView(driver).click();						//Clicking on Close (cross) to close view
				}
				catch(Exception e)
				{
					ReviewerPOM.clickCloseView1(driver).click();						//Clicking on Close (cross) to close view
				}
				Thread.sleep(500);
				ReviewerPOM.clickDownload2(driver).click();
				
				perform(driver, test);
				
				Thread.sleep(500);
				if(ReviewerPOM.clickApprove(driver).isEnabled())
				{
					ReviewerPOM.clickApprove(driver).click();					//Clicking on Approve
				}
				
				Thread.sleep(500);
				js.executeScript("window.scrollBy(0,-3000)");					//Scrolling down window by 500 px.
				
				Thread.sleep(500);
				String msg = LiPerformerPOM.readMessage(driver).getText();		//Reading message after Appove.
				
				if(msg.equalsIgnoreCase("Approved Sucessfully."))
				{
					test.log(LogStatus.PASS, "Message displayed = "+msg);
				}
				else
				{
					test.log(LogStatus.FAIL, "Message displayed = "+msg);
				}
				
				Thread.sleep(500);
				driver.switchTo().parentFrame();
				
				Thread.sleep(500);
				LiPerformerPOM.clickClose(driver).click();						//Clicking on Close (Cross)
				
				try
				{
					Thread.sleep(300);
					wait.until(ExpectedConditions.invisibilityOf(LiPerformerPOM.Progress(driver)));
				}
				catch(Exception e)
				{
					
				}
				
				Thread.sleep(1000);
				js.executeScript("window.scrollBy(0,700)");
				
				Thread.sleep(500);
				int total1 = Integer.parseInt(OverduePOM.readReminder(driver).getText());	//Reading total records count
				if(total1 < total)
				{
					test.log(LogStatus.PASS, "Total records count from grid decreased.");
					test.log(LogStatus.INFO, "Old Count = "+total+" | New Count = "+total1);
				}
				else
				{
					test.log(LogStatus.FAIL, "Total records count from grid doesn't decreased.");
					test.log(LogStatus.INFO, "Old Count = "+total+" | New Count = "+total1);
				}
				
				Thread.sleep(100);
				wait.until(ExpectedConditions.elementToBeClickable(OverduePOM.clickDashboard(driver)));
				OverduePOM.clickDashboard(driver).click();							//CLicking on Dashboard.
				
				Thread.sleep(700);
				wait.until(ExpectedConditions.visibilityOf(LiPerformerPOM.clickPendingForReview(driver)));		
				int pending1 = Integer.parseInt(LiPerformerPOM.clickPendingForReview(driver).getText());	//Reading 'Pending For Review' count
				int applied1 = Integer.parseInt(LiPerformerPOM.clickApplied(driver).getText());	//Reading 'Applied' count
				
				if(pending1 < pending)
				{
					test.log(LogStatus.PASS, "'Pending For Review' license count decreased.");
					test.log(LogStatus.INFO, "Old Count = "+pending+" | New Count = "+pending1);
				}
				else
				{
					test.log(LogStatus.FAIL, "'Pending For Review' license count doesn't decreased.");
					test.log(LogStatus.INFO, "Old count = "+pending+" | New Count = "+pending1);
				}
				
				if(applied1 > applied)
				{
					test.log(LogStatus.PASS, "'Applied' license count increased.");
					test.log(LogStatus.INFO, "Old Count = "+applied+" | New Count = "+applied1);
				}
				else
				{
					test.log(LogStatus.FAIL, "'Applied' license count doesn't increased.");
					test.log(LogStatus.INFO, "Old count = "+applied+" | New Count = "+applied1);
				}
				break;
			}
			if(i == n-1)
			{
				LiReviewerPOM.clickIndexDropDown(driver).click();
				Thread.sleep(500);
				elementsList1 = LiReviewerPOM.clickIndexDropDownOption(driver);
				int m = elementsList1.size();
				if(k <= m)				//Hear initially k- 1 as we are already on page 0
				{
					elementsList1.get(k).click();
					Thread.sleep(500);
					elementsList = LiReviewerPOM.clickAction(driver);
					n = elementsList.size();
					i = -1;
					k++;
				}
				else
				{
					test.log(LogStatus.PASS, "Didn't found any 'Pending For Review' license.");
					break;
				}
			}
		}
	}
	
	public static void PendingReviewCountInternal(WebDriver driver, ExtentTest test, XSSFWorkbook workbook) throws InterruptedException
	{		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		try
		{
			Thread.sleep(400);
			wait.until(ExpectedConditions.invisibilityOf(LiPerformerPOM.Progress(driver)));
		}
		catch(Exception e)
		{
			
		}
		
		Thread.sleep(500);
		wait.until(ExpectedConditions.visibilityOf(LiPerformerPOM.clickPendingForReview(driver)));		
		int pending = Integer.parseInt(LiPerformerPOM.clickPendingForReview(driver).getText());	//Reading 'Pending For Review' count
		int applied = Integer.parseInt(LiPerformerPOM.clickApplied(driver).getText());	//Reading 'Applied' count
		LiPerformerPOM.clickPendingForReview(driver).click();		//Clicking on 'Pending For Review' image link
		
		Thread.sleep(500);
		try
		{
			wait.until(ExpectedConditions.invisibilityOf(LiPerformerPOM.Progress(driver)));
		}
		catch(Exception e)
		{
			
		}
		
		wait.until(ExpectedConditions.visibilityOf(LiReviewerPOM.checkTable(driver)));
		LiReviewerPOM.clickReviewer(driver).click();
		
		Thread.sleep(1000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,1000)");					//Scrolling down window by 500 px.
		int total = Integer.parseInt(OverduePOM.readReminder(driver).getText());	//Reading total records count
		
		if(pending == total)
		{
			test.log(LogStatus.PASS, "Dashboard 'Pending Review' count matches to total records displayed.");
			test.log(LogStatus.INFO, "Dashboard 'Pending Review' count = "+pending+" | Total records in grid = "+total);
		}
		else
		{
			test.log(LogStatus.FAIL, "Dashboard 'Pending Review' count doesn't matches to total records displayed.");
			test.log(LogStatus.INFO, "Dashboard 'Pending Review' count = "+pending+" | Total records in grid = "+total);
		}
		
		elementsList = LiReviewerPOM.clickAction(driver);
		elementsList.get(0).click();									//Clicking on ith Action button.
		
		Thread.sleep(500);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("showdetails"));
		
		Thread.sleep(500);
		js.executeScript("window.scrollBy(0,700)");
		
		perform(driver, test);
		
		Thread.sleep(500);
		ReviewerPOM.clickApprove(driver).click();					//Clicking on Approve
		
		Thread.sleep(1000);
		js.executeScript("window.scrollBy(0,700)");
		
		Thread.sleep(500);
		int total1 = Integer.parseInt(OverduePOM.readReminder(driver).getText());	//Reading total records count
		if(total1 < total)
		{
			test.log(LogStatus.PASS, "Total records count from grid decreased.");
			test.log(LogStatus.INFO, "Old Count = "+total+" | New Count = "+total1);
		}
		else
		{
			test.log(LogStatus.FAIL, "Total records count from grid doesn't decreased.");
			test.log(LogStatus.INFO, "Old Count = "+total+" | New Count = "+total1);
		}
		
		Thread.sleep(100);
		wait.until(ExpectedConditions.elementToBeClickable(OverduePOM.clickDashboard(driver)));
		OverduePOM.clickDashboard(driver).click();							//CLicking on Dashboard.
		
		wait.until(ExpectedConditions.visibilityOf(LiPerformerPOM.clickType(driver)));
		LiPerformerPOM.clickType(driver).click();				//Clicking on 'Type' drop down.
		
		Select drp = new Select(LiPerformerPOM.clickType(driver));
		drp.selectByIndex(1);									//Selecting 'Internal' type.
		
		Thread.sleep(1000);
		CFOcountPOM.clickApply1(driver).click();				//Clicking on Apply.
		
		Thread.sleep(700);
		wait.until(ExpectedConditions.visibilityOf(LiPerformerPOM.clickPendingForReview(driver)));		
		int pending1 = Integer.parseInt(LiPerformerPOM.clickPendingForReview(driver).getText());	//Reading 'Pending For Review' count
		int applied1 = Integer.parseInt(LiPerformerPOM.clickApplied(driver).getText());	//Reading 'Applied' count
		
		if(pending1 < pending)
		{
			test.log(LogStatus.PASS, "'Pending For Review' license count decreased.");
			test.log(LogStatus.INFO, "Old Count = "+pending+" | New Count = "+pending1);
		}
		else
		{
			test.log(LogStatus.FAIL, "'Pending For Review' license count doesn't decreased.");
			test.log(LogStatus.INFO, "Old count = "+pending+" | New Count = "+pending1);
		}
		
		if(applied1 > applied)
		{
			test.log(LogStatus.PASS, "'Applied' license count increased.");
			test.log(LogStatus.INFO, "Old Count = "+applied+" | New Count = "+applied1);
		}
		else
		{
			test.log(LogStatus.FAIL, "'Applied' license count doesn't increased.");
			test.log(LogStatus.INFO, "Old count = "+applied+" | New Count = "+applied1);
		}
	}
}
