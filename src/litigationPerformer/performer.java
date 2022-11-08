package litigationPerformer;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class performer 
{
	public static WebDriver driver = null;		//WebDriver instance created
	public static WebElement upload = null;		//WebElement to get upload button
	public static ExtentReports extent;			//Instance created for report file
	public static ExtentTest test;				//Instance created for tests
	public static FileInputStream fis = null;	//File input stream variable
	public static XSSFWorkbook workbook = null;	//Excel sheet workbook variable
	public static XSSFSheet sheet = null;		//Sheet variable
	public static List<WebElement> elementsList = null;
	
	public static XSSFSheet ReadExcel() throws IOException
	{
		String workingDir = System.getProperty("webdriver.chrome.driver","C:/March2022/PerformerPom/Driver/chromedriver.exe");
		fis = new FileInputStream("C:/March2022/PerformerPom/TestData/LitigationSheet.xlsx");
		workbook = new XSSFWorkbook(fis);
		sheet = workbook.getSheetAt(0);					//Retrieving second sheet of Workbook
		return sheet;
	}
	
	@BeforeTest
	void setBrowser() throws InterruptedException, IOException
	{
		String workingDir = System.getProperty("user.dir");
		extent = new com.relevantcodes.extentreports.ExtentReports("C:/March2022/PerformerPom/Reports/LitigationPerformer.html",true);
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
	
	@Test(priority = 1)
	void Login() throws InterruptedException, IOException
	{
		test = extent.startTest("Litigation Logging In - Performer");
		test.log(LogStatus.INFO, "Logging into system");
		
		XSSFSheet sheet = ReadExcel();
		Row row1 = sheet.getRow(1);						//Selected 1st index row (Second row)
		Cell c1 = row1.getCell(1);						//Selected cell (1 row,1 column)
		String uname = c1.getStringCellValue();			//Got the URL stored at position 1,1
		
		Row row2 = sheet.getRow(2);						//Selected 2nd index row (Third row)
		Cell c2 = row2.getCell(1);						//Selected cell (2 row,1 column)
		String password = c2.getStringCellValue();		//Got the URL stored at position 2,1
		
		driver = login.Login.UserLogin(uname,password,"Litigation");		//Method of Login class to login user.
		
		test.log(LogStatus.PASS, "Test Passed.");
		extent.endTest(test);
		extent.flush();
	}
	
	@Test(priority = 2)
	void NoticeOpen() throws InterruptedException, IOException
	{
		test = extent.startTest("Notice - Open Count Verification");
		test.log(LogStatus.INFO, "Test Initiated");
		
		MethodsPOM.NoticeOpen(driver, test, workbook, "Performer");
		
		extent.endTest(test);
		extent.flush();
	}
	/*
	@Test(priority = 3)
	void NoticeClosed() throws InterruptedException, IOException
	{
		test = extent.startTest("Notice - Closed Count Verification");
		test.log(LogStatus.INFO, "Test Initiated");
		
		MethodsPOM.NoticeClosed(driver, test, workbook, "Performer");
		
		extent.endTest(test);
		extent.flush();
	}
	
	@Test(priority = 4)
	void CaseOpen() throws InterruptedException, IOException
	{
		test = extent.startTest("Case - Open Count Verification");
		test.log(LogStatus.INFO, "Test Initiated");
		
		MethodsPOM.CaseOpen(driver, test, workbook, "Performer");
		
		extent.endTest(test);
		extent.flush();
	}
	
	@Test(priority = 5)
	void CaseClose() throws InterruptedException, IOException
	{
		test = extent.startTest("Case - Closed Count Verification");
		test.log(LogStatus.INFO, "Test Initiated");
		
		MethodsPOM.CaseClosed(driver, test, workbook, "Performer");
		
		extent.endTest(test);
		extent.flush();
	}
	
	@Test(priority = 6)
	void TaskOpen() throws InterruptedException, IOException
	{
		test = extent.startTest("Task - Open Count Verification");
		test.log(LogStatus.INFO, "Test Initiated");
		
		MethodsPOM.TaskOpen(driver, test, workbook, "Performer");
		
		extent.endTest(test);
		extent.flush();
	}
	
	@Test(priority = 7)
	void TaskClosed() throws InterruptedException, IOException
	{
		test = extent.startTest("Task - Closed Count Verification");
		test.log(LogStatus.INFO, "Test Initiated");
		
		MethodsPOM.TaskClosed(driver, test, workbook, "Performer");
		
		extent.endTest(test);
		extent.flush();
	}
	
	@Test(priority = 8)
	void LinkNotice() throws InterruptedException, IOException
	{
		test = extent.startTest("Link Notice Verification");
		test.log(LogStatus.INFO, "Test Initiated");
		
		MethodsPOM.LinkDocument(driver, test, workbook, "Notice");
		
		extent.endTest(test);
		extent.flush();
	}
	
	@Test(priority = 9)
	void CloseNotice() throws InterruptedException, IOException
	{
		test = extent.startTest("Close Notice Count Verification");
		test.log(LogStatus.INFO, "Test Initiated");
		
		MethodsPOM.CloseNoticeCase(driver, test, workbook,"Notice");
		
		extent.endTest(test);
		extent.flush();
	}
	
	@Test(priority = 10)
	void LinkCase() throws InterruptedException, IOException
	{
		test = extent.startTest("Link Case Verification");
		test.log(LogStatus.INFO, "Test Initiated");
		
		MethodsPOM.LinkDocument(driver, test, workbook, "Case");
		
		extent.endTest(test);
		extent.flush();
	}
	
	@Test(priority = 11)
	void CloseCase() throws InterruptedException, IOException
	{
		test = extent.startTest("Close Case Count Verification");
		test.log(LogStatus.INFO, "Test Initiated");
		
		MethodsPOM.CloseNoticeCase(driver, test, workbook,"Case");
		
		extent.endTest(test);
		extent.flush();
	}
	
	@Test(priority = 12)
	void MyReports() throws InterruptedException, IOException
	{
		test = extent.startTest("Reports excel count verification");
		test.log(LogStatus.INFO, "Test Initiated");
		
		MethodsPOM.MyReports(driver, test, workbook, "Performer");
		
		extent.endTest(test);
		extent.flush();
	}
	
	@Test(priority = 13)
	void MyReminder() throws InterruptedException, IOException
	{
		test = extent.startTest("My Reminder verification");
		test.log(LogStatus.INFO, "Test Initiated");
		
		MethodsPOM.MyReminder(driver, test, workbook);
		
		extent.endTest(test);
		extent.flush();
	}
	
	@Test(priority = 14)
	void Masters() throws InterruptedException, IOException
	{
		test = extent.startTest("Masters - Case/Notice  verification");
		test.log(LogStatus.INFO, "Test Initiated");
		
		MethodsPOM.Masters(driver, test, workbook);
		
		extent.endTest(test);
		extent.flush();
	}*/
}
