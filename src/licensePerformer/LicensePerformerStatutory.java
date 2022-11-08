package licensePerformer;

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

public class LicensePerformerStatutory 
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
		String workingDir = System.getProperty("user.dir");
		fis = new FileInputStream(workingDir+"//TestData//ComplianceSheet.xlsx");
		workbook = new XSSFWorkbook(fis);
		sheet = workbook.getSheetAt(0);					//Retrieving second sheet of Workbook
		return sheet;
	}
	
	@BeforeTest
	void setBrowser() throws InterruptedException, IOException
	{
		String workingDir = System.getProperty("user.dir");
		extent = new com.relevantcodes.extentreports.ExtentReports(workingDir+"//Reports//LicensePerformerResults(Statutory).html",true);
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
		test = extent.startTest("Logging In - Performer (Statutory)");
		test.log(LogStatus.INFO, "Logging into system");
		
		XSSFSheet sheet = ReadExcel();
		Row row1 = sheet.getRow(1);						//Selected 1st index row (Second row)
		Cell c1 = row1.getCell(1);						//Selected cell (1 row,1 column)
		String uname = c1.getStringCellValue();			//Got the URL stored at position 1,1
		
		Row row2 = sheet.getRow(2);						//Selected 2nd index row (Third row)
		Cell c2 = row2.getCell(1);						//Selected cell (2 row,1 column)
		String password = c2.getStringCellValue();		//Got the URL stored at position 2,1
		
		driver = login.Login.UserLogin(uname,password,"License");		//Method of Login class to login user.
		
		test.log(LogStatus.PASS, "Test Passed.");
		extent.endTest(test);
		extent.flush();
	}
	
	@Test(priority = 2)
	void Active() throws InterruptedException
	{
		test = extent.startTest("Active License Count Verification");
		test.log(LogStatus.INFO, "Test Initiated");
		
		LiPeMethodsPOM.ActiveLicense(driver, test, "Statutory");
		
		extent.endTest(test);
		extent.flush();
	}
	
	@Test(priority = 3)
	void Expiring() throws InterruptedException
	{
		test = extent.startTest("Expiring License Count Verification");
		test.log(LogStatus.INFO, "Test Initiated");
		
		LiPeMethodsPOM.ExpiringCount(driver, test, "Statutory");
		
		extent.endTest(test);
		extent.flush();
	}
	
	@Test(priority = 4)
	void Expired() throws InterruptedException
	{
		test = extent.startTest("Expired License Count Verification");
		test.log(LogStatus.INFO, "Test Initiated");
		
		LiPeMethodsPOM.ExpiredCount(driver, test, "Statutory");
		
		extent.endTest(test);
		extent.flush();
	}
	
	@Test(priority = 5)
	void Applied() throws InterruptedException
	{
		test = extent.startTest("Applied Count Verification");
		test.log(LogStatus.INFO, "Test Initiated");
		
		LiPeMethodsPOM.AppliedCount(driver, test, "Statutory");
		
		extent.endTest(test);
		extent.flush();
	}
	
	@Test(priority = 6)
	void MyDocuments() throws InterruptedException
	{
		test = extent.startTest("My Documents Download Verification");
		test.log(LogStatus.INFO, "Test Initiated");
		
		LiPeMethodsPOM.Documents(driver, test, "Statutory");
		
		extent.endTest(test);
		extent.flush();
	}
	
	@Test(priority = 7)
	void MyReports() throws InterruptedException, IOException
	{
		test = extent.startTest("My Reports Download Verification");
		test.log(LogStatus.INFO, "Test Initiated");
		
		LiPeMethodsPOM.Reports(driver, test, "Statutory");
		
		extent.endTest(test);
		extent.flush();
	}
}
