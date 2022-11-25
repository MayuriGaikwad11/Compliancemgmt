package performer;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class FePerformer {
	
	public static WebDriver driver = null;			//WebDriver instance created
	public static WebElement upload = null;			//WebElement to get upload button
	public static ExtentReports extent;				//Instance created for report file
	public static ExtentTest test;					//Instance created for tests
	public int overdueStatutory;					//Variable to get old Statutory Overdue
	public int reviewStatutory;						//Variable to get old Statutory Review
	public int newOverdueStatutory;					//Variable to get new Statutory Overdue
	public int newReviewStatutory;					//Variable to get new Statutory Review
	public int overdueInternal;						//Variable to get old Internal Overdue
	public int reviewInternal;						//Variable to get old Internal Review
	public int newOverdueInternal;					//Variable to get new Internal Overdue
	public int newReviewInternal;					//Variable to get new Internal Review
	public static List<WebElement> elementsList = null;		//Variable to get list of action buttons
	public static List<WebElement> checkboxesList = null;	//Variable to get list of check boxes
	public static List<WebElement> elementsList1 = null;	//Variable to get list of text boxes
	public static FileInputStream fis = null;		//File input stream variable
	public static XSSFWorkbook workbook = null;		//Excel sheet workbook variable
	public static XSSFSheet sheet = null;			//Sheet variable
	
	public static String link = "fe";
	
	public static XSSFSheet ReadExcel() throws IOException
	{
		
		fis = new FileInputStream("C:/March2022/PerformerPom/TestData/ComplianceSheet.xlsx");
		workbook = new XSSFWorkbook(fis);
		sheet = workbook.getSheetAt(4);					//Retrieving third sheet of Workbook
		return sheet;
	}
	
	@BeforeTest
	void setBrowser() throws InterruptedException, IOException
	{
		//String workingDir = System.getProperty("user.dir");
		extent = new com.relevantcodes.extentreports.ExtentReports("C:/March2022/PerformerPom/Reports/PerformerResults.html",true);
		test = extent.startTest("Verify Browser Opening");
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
	
	@Test(priority = 1)
	void Login() throws InterruptedException, IOException
	{
		test = extent.startTest("Loging In - Performer");
		test.log(LogStatus.INFO, "Logging into system");
		
		XSSFSheet sheet = ReadExcel();
		Row row1 = sheet.getRow(1);						//Selected 1st index row (Second row)
		Cell c1 = row1.getCell(1);						//Selected cell (1 row,1 column)
		String uname = c1.getStringCellValue();			//Got the URL stored at position 1,1
		
		Row row2 = sheet.getRow(2);						//Selected 2nd index row (Third row)
		Cell c2 = row2.getCell(1);						//Selected cell (2 row,1 column)
		String password = c2.getStringCellValue();		//Got the URL stored at position 2,1
		
		driver = login.Login.UserLogin(uname,password,link);		//Method of Login class to login user.
		
		test.log(LogStatus.PASS, "Test Passed.");
		extent.endTest(test);
		extent.flush();
	}
	
	public static void message(WebDriver driver)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		try
		{
			wait.until(ExpectedConditions.elementToBeClickable(OverduePOM.closeMessage(driver)));
			if(OverduePOM.closeMessage(driver).isDisplayed())	//If Compliance Updation message popped up,
			{
				OverduePOM.closeMessage(driver).click();		//then close the message.
			}
		}
		catch(Exception e)
		{
			
		}
	}
	
	@Test(priority = 2)  
	void AssignedEventsSingle() throws InterruptedException
	{
		test = extent.startTest("Assigned Events Count using Assigned button");
		test.log(LogStatus.INFO, "Test Initiated");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(35));
		Thread.sleep(2000);
		int dasAssignedEventVal = Integer.parseInt(OverduePOM.clickAssignedEventsOw(driver).getText());	//Storing old Activated Events value
		
		Thread.sleep(1000);
		OverduePOM.clickAssignedEventsOw(driver).click();					//Clicking on 'Assigned Events' value
		
		Thread.sleep(3000);
		elementsList1 = OverduePOM.viewEvent(driver);
		elementsList1.get(0).click();									//Clicking on View Event button
		
		Thread.sleep(4000);
		OverduePOM.closeViewEvent(driver).click();						//Closing the View Event
		
		Thread.sleep(2000);
	/*	Thread.sleep(500);
		elementsList1 = OverduePOM.clickTextBoxes(driver);
		elementsList1.get(2).sendKeys("Automation Testing");			//Writing in first text box
		Thread.sleep(500);
		elementsList1 = OverduePOM.clickDates(driver);
	//	elementsList1.get(1).sendKeys("07072021");						//Clicking on first date (Actually second on form)
		Thread.sleep(3000);
		elementsList1.get(0).click();
		
	//	OverduePOM.selectDate(driver).click(); 							//Selecting date - second row and fifth column date from calendar
	
		Thread.sleep(1000);
		elementsList1 = OverduePOM.clickActivate(driver);
		elementsList1.get(1).click();									//Clicking on first Activate button image
		
		Thread.sleep(500);
		litigationPerformer.MethodsPOM.progress(driver);
		
		Thread.sleep(1000);
		driver.switchTo().alert().accept();
		*/
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,500)");
		Thread.sleep(3000);
		OverduePOM.readTotalItemsD(driver).click();					//Clicking on total items count
		Thread.sleep(500);
		String item = OverduePOM.readTotalItemsD(driver).getText();	//Reading total items String value
		String[] bits = item.split(" ");								//Splitting the String
		String compliancesCount = bits[bits.length - 2];				//Getting the second last word (total number of users)
		int countGrid = Integer.parseInt(compliancesCount);
		if(dasAssignedEventVal == countGrid)
		{
			test.log(LogStatus.PASS, "Number of  grid matches to Dashboard  Count.");
			test.log(LogStatus.INFO, "No of  the grid = "+countGrid+" | Dashboard   Count = "+dasAssignedEventVal);
		}
		else
		{
			test.log(LogStatus.FAIL, "Number of Categories does not matches to Dashboard Categories  Count.");
			test.log(LogStatus.INFO, "No of  the grid = "+countGrid+" | Dashboard   Count = "+dasAssignedEventVal);
		}
		Thread.sleep(1000);
		OverduePOM.clickDashboard(driver).click();						//Clicking on Dashboard
		Thread.sleep(2000);
		
		extent.endTest(test);
		extent.flush();
	}
	
	@Test(priority = 3)  
	void ActivatedEventsSingle() throws InterruptedException
	{
		test = extent.startTest("Activated Events Count using Activate button");
		test.log(LogStatus.INFO, "Test Initiated");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(35));
		Thread.sleep(2000);
		int dasActivatedEventVal = Integer.parseInt(OverduePOM.readActivatedEventsOw(driver).getText());	//Storing old Activated Events value
		
		Thread.sleep(1000);
		OverduePOM.readActivatedEventsOw(driver).click();					//Clicking on 'Assigned Events' value
		
		Thread.sleep(3000);
		elementsList1 = OverduePOM.viewEvent(driver);
		elementsList1.get(0).click();									//Clicking on View Event button
		
		Thread.sleep(4000);
		OverduePOM.closeViewEvent(driver).click();						//Closing the View Event
		Thread.sleep(3000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,500)");
		Thread.sleep(3000);
		OverduePOM.readTotalItemsD(driver).click();					//Clicking on total items count
		Thread.sleep(500);
		String item = OverduePOM.readTotalItemsD(driver).getText();	//Reading total items String value
		String[] bits = item.split(" ");								//Splitting the String
		String compliancesCount = bits[bits.length - 2];				//Getting the second last word (total number of users)
		int countGrid = Integer.parseInt(compliancesCount);
		if(dasActivatedEventVal == countGrid)
		{
			test.log(LogStatus.PASS, "Number of  grid matches to Dashboard  Count.");
			test.log(LogStatus.INFO, "No of  the grid = "+countGrid+" | Dashboard   Count = "+dasActivatedEventVal);
		}
		else
		{
			test.log(LogStatus.FAIL, "Number of Categories does not matches to Dashboard Categories  Count.");
			test.log(LogStatus.INFO, "No of  the grid = "+countGrid+" | Dashboard   Count = "+dasActivatedEventVal);
		}
		Thread.sleep(1000);
		OverduePOM.clickDashboard(driver).click();						//Clicking on Dashboard
		Thread.sleep(2000);
		
		extent.endTest(test);
		extent.flush();
	}
	

}
