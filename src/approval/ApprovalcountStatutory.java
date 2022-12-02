package approval;

import java.io.FileInputStream;
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
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import cfo.CFOcountPOM;
import login.LoginPOM;

public class ApprovalcountStatutory {
	public static WebDriver driver = null;		//WebDriver instance created
	public static WebElement upload = null;		//WebElement to get upload button
	public static ExtentReports extent;			//Instance created for report file
	public static ExtentTest test;				//Instance created for tests
	public static FileInputStream fis = null;	//File input stream variable
	public static XSSFWorkbook workbook = null;	//Excel sheet workbook variable
	public static XSSFSheet sheet = null;		//Sheet variable
	public static List<WebElement> elementsList = null;
	public static List<WebElement> elementsList1 = null;
	public static List<WebElement> elementsList2 = null;
	public static List<WebElement> elementsList3 = null;
	public static List<WebElement> elementsList4 = null;
	public static List<WebElement> menus = null;
	public int count = 0;
	public int interest = 0;					//Variable created for reading Interest
	public int penalty = 0;						//Variable created for reading Penalty
	
	public static String link = "Shivraj";           //Check link in excel sheet first.
			
		
	
	public static XSSFSheet ReadExcel() throws IOException
	{
		//String workingDir = System.getProperty("webdriver.chrome.driver","C:/March2022/PerformerPom/Driver/chromedriver.exe");
		fis = new FileInputStream("C:/March2022/PerformerPom/TestData/ComplianceSheet.xlsx");
		workbook = new XSSFWorkbook(fis);
		sheet = workbook.getSheetAt(6);					//Retrieving third sheet of Workbook
		return sheet;
	}
	
	@BeforeTest
	void setBrowser() throws InterruptedException, IOException
	{
	//	String workingDir = System.getProperty("webdriver.chrome.driver","C:/March2022/PerformerPom/Driver/chromedriver.exe");
		extent = new com.relevantcodes.extentreports.ExtentReports("C:/March2022/PerformerPom/Reports/CFOResultsStatotory.html",true);
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
		test = extent.startTest("Loging In - Approval (Statutory)");
		test.log(LogStatus.INFO, "Logging into system");
		
		XSSFSheet sheet = ReadExcel();
		Row row1 = sheet.getRow(1);						//Selected 1st index row (Second row)
		Cell c1 = row1.getCell(1);						//Selected cell (1 row,1 column)
		String uname = c1.getStringCellValue();			//Got the URL stored at position 1,1
		
		Row row2 = sheet.getRow(2);						//Selected 2nd index row (Third row)
		Cell c2 = row2.getCell(1);						//Selected cell (2 row,1 column)
		String password = c2.getStringCellValue();		//Got the URL stored at position 2,1
		
		//Write "CFO-diy" for DIYProduction link.
		//Write "CFO" for login.avantis
		driver = login.Login.UserLogin(uname,password,link);		//Method of Login class to login user.
		Thread.sleep(2000);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
		wait.until(ExpectedConditions.visibilityOf(ApprovalcountPOM.clickManagement(driver)));

		ApprovalcountPOM.clickManagement(driver).click();
		Thread.sleep(5000);
		test.log(LogStatus.PASS, "Test Passed.");
		extent.endTest(test);
		extent.flush();
	}
	
	public static void progress1(WebDriver driver)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		try
		{
			Thread.sleep(500);
			wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath("//*[@id='imgcaldate']"))));
		}
		catch(Exception e)
		{
			
		}
	}
	
	@Test(priority = 2)
	void CategoriesCountMatch() throws InterruptedException
	{
		test = extent.startTest(" Count by Clicking on 'Categories'");
		test.log(LogStatus.INFO, "Test Initiated");
		
		Thread.sleep(4000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		String string_Categories =CFOcountPOM.clickCategories(driver).getText();		//Storing old value of Statutory overdue.
	int	CategoriesCountDas = Integer.parseInt(string_Categories);
		CFOcountPOM.clickCategories(driver).click();
		Thread.sleep(500);
		
		litigationPerformer.MethodsPOM.progress(driver);
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(70));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("showdetails"));	//Wait until frame get visible and switch to it.
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='grid']/div[3]/table/tbody/tr[3]/td[4]/div")));
		Thread.sleep(3000);
		js.executeScript("window.scrollBy(0,500)");
		Thread.sleep(3000);
		CFOcountPOM.readTotalItemsD(driver).click();					//Clicking on total items count
		Thread.sleep(500);
		String item = CFOcountPOM.readTotalItemsD(driver).getText();	//Reading total items String value
		String[] bits = item.split(" ");								//Splitting the String
		String compliancesCount = bits[bits.length - 2];				//Getting the second last word (total number of users)
		int CatcountGrid = Integer.parseInt(compliancesCount);
		
		elementsList1 = CFOcountPOM.readCompliancesList(driver);
		elementsList1.get(1).click();
		Thread.sleep(3000);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("APIOverView"));
		Thread.sleep(1000);
		CFOcountPOM.clickExportImage(driver).click();                    //export excel
		Thread.sleep(5000);
		test.log(LogStatus.INFO, "Excel file Export Successfully");	
		driver.switchTo().parentFrame();								//Switching back to parent frame.
		Thread.sleep(3000);
		CFOcountPOM.closeCategories_Compliances(driver).click();		//Closing the 'Compliances' pup up.
		Thread.sleep(2000);
	
		int n1 = elementsList1.size();
		int value = 0;
		int count1 = 0;
		for(int i = 0; i < n1; i++)
		{		
			Thread.sleep(500);
			elementsList1 = CFOcountPOM.readCompliancesList(driver);
			value = Integer.parseInt(elementsList1.get(i).getText());
			elementsList1.get(i).click();
			
			Thread.sleep(500);
			litigationPerformer.MethodsPOM.progress(driver);
			
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("APIOverView"));	//Wait until frame get visible and switch to it.
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class='k-grid-content k-auto-scrollable']")));
			Thread.sleep(4000);
			js.executeScript("window.scrollBy(0,3000)");				//Scrolling down window by 2000 px.
			Thread.sleep(1000);
			CFOcountPOM.readTotalItemsD(driver).click();
			
			Thread.sleep(1000);
			String item1 = CFOcountPOM.readTotalItemsD(driver).getText();
			String[] bits1 = item.split(" ");								//Splitting the String
			String compliancesCount1 = bits[bits.length - 2];				//Getting the second last word (total number of users)
			
			count1 = Integer.parseInt(compliancesCount1);
			js.executeScript("window.scrollBy(0,3000)");
			if(value == count1)
			{
				test.log(LogStatus.PASS, "Compliances count matches. Clicked value = " + value+ ", Grid Records = "+count1);
			}
			else
			{
				test.log(LogStatus.FAIL, "Compliances count does not matches. Clicked value = "+value+", Grid Records = "+count1);
			}
			driver.switchTo().parentFrame();								//Switching back to parent frame.
			Thread.sleep(3000);
			CFOcountPOM.closeCategories_Compliances(driver).click();		//Closing the 'Compliances' pup up.
			Thread.sleep(2000);
		}	
		if(CategoriesCountDas == CatcountGrid)
		{
			test.log(LogStatus.PASS, "Number of Categories grid matches to Dashboard Categories  Count.");
			test.log(LogStatus.INFO, "No of Categories in the grid = "+CatcountGrid+" | Dashboard Categories  Count = "+CategoriesCountDas);
		}
		else
		{
			test.log(LogStatus.FAIL, "Number of Categories does not matches to Dashboard Categories  Count.");
			test.log(LogStatus.INFO, "No of Categories in the grid = "+CatcountGrid+" | Dashboard Categories  Count = "+CategoriesCountDas);
		}
		Thread.sleep(3000);
		js.executeScript("window.scrollBy(2000,0)");     //Scrolling UP window by 2000 px.
		Thread.sleep(3000);
		driver.switchTo().defaultContent();
		
		Thread.sleep(4000);
		
		CFOcountPOM.closeCategories(driver).click();
		Thread.sleep(2000);
		extent.endTest(test);
		extent.flush();
	}
	
	@Test(priority = 3)
	void UniqueCompliancesCountMatch() throws InterruptedException
	{
		test = extent.startTest(" Count by Clicking on 'Unique Compliances'");
		test.log(LogStatus.INFO, "Test Initiated");
		
		Thread.sleep(3000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		String string_Compliances =CFOcountPOM.uniqueCompliances(driver).getText();		//Storing old value of Statutory overdue.
	int	CompliancesCountDas = Integer.parseInt(string_Compliances);
		CFOcountPOM.uniqueCompliances(driver).click();
		Thread.sleep(1000);
		
		litigationPerformer.MethodsPOM.progress(driver);
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("showdetails"));	//Wait until frame get visible and switch to it.
		Thread.sleep(1000);
		CFOcountPOM.clickExportImage(driver).click();                    //export excel
		Thread.sleep(5000);
		test.log(LogStatus.PASS, "Excel file Export Successfully");	
		js.executeScript("window.scrollBy(0,500)");
		Thread.sleep(3000);
		CFOcountPOM.readTotalItemsD(driver).click();					//Clicking on total items count
		Thread.sleep(500);
		String item = CFOcountPOM.readTotalItemsD(driver).getText();	//Reading total items String value
		String[] bits = item.split(" ");								//Splitting the String
		String compliancesCount = bits[bits.length - 2];				//Getting the second last word (total number of users)
		int ComcountGrid = Integer.parseInt(compliancesCount);
		if(CompliancesCountDas == ComcountGrid)
		{
			test.log(LogStatus.PASS, "Number of Unique Compliances grid matches to Dashboard Unique Compliances  Count.");
			test.log(LogStatus.INFO, "No of Unique Compliances in the grid = "+ComcountGrid+" | Dashboard Unique Compliances  Count = "+CompliancesCountDas);
		}
		else
		{
			test.log(LogStatus.FAIL, "Number of Unique compliances does not matches to Dashboard Statutory Overdue Count.");
			test.log(LogStatus.INFO, "No of Unique Compliances in the grid = "+ComcountGrid+" | Dashboard Unique Compliances  Count = "+CompliancesCountDas);
		}
		js.executeScript("window.scrollBy(500,0)");						//Scrolling UP window by 2000 px.
		driver.switchTo().defaultContent();
		Thread.sleep(3000);
		CFOcountPOM.closeCategories(driver).click();
		extent.endTest(test);
		extent.flush();
	}
	
	@Test(priority = 4)
	void TotalCompliancesCountMatch() throws InterruptedException
	{
		test = extent.startTest(" Count by Clicking on 'Total Compliances'");
		test.log(LogStatus.INFO, "Test Initiated");
		
		Thread.sleep(3000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		String string_Compliances =CFOcountPOM.TotalCompliances(driver).getText();		//Storing old value of Statutory overdue.
	int	CompliancesCountDas = Integer.parseInt(string_Compliances);
		CFOcountPOM.TotalCompliances(driver).click();
		Thread.sleep(1000);
		
		litigationPerformer.MethodsPOM.progress(driver);
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("showdetails"));	//Wait until frame get visible and switch to it.
		Thread.sleep(1000);
		CFOcountPOM.clickExportExcel(driver).click();                    //export excel
		Thread.sleep(5000);
		test.log(LogStatus.PASS, "Excel file Export Successfully");	
		js.executeScript("window.scrollBy(0,500)");
		Thread.sleep(3000);
		CFOcountPOM.readTotalItemsD(driver).click();					//Clicking on total items count
		Thread.sleep(500);
		String item = CFOcountPOM.readTotalItemsD(driver).getText();	//Reading total items String value
		String[] bits = item.split(" ");								//Splitting the String
		String compliancesCount = bits[bits.length - 2];				//Getting the second last word (total number of users)
		int ComcountGrid = Integer.parseInt(compliancesCount);
		if(CompliancesCountDas == ComcountGrid)
		{
			test.log(LogStatus.PASS, "Number of Total Compliances grid matches to Dashboard Total Compliances  Count.");
			test.log(LogStatus.INFO, "No of Total Compliances in the grid = "+ComcountGrid+" | Dashboard Total Compliances  Count = "+CompliancesCountDas);
		}
		else
		{
			test.log(LogStatus.FAIL, "Number of Total compliances does not matches to Dashboard Statutory Overdue Count.");
			test.log(LogStatus.INFO, "No of Total Compliances in the grid = "+ComcountGrid+" | Dashboard Total Compliances  Count = "+CompliancesCountDas);
		}
		js.executeScript("window.scrollBy(500,0)");						//Scrolling UP window by 2000 px.
		driver.switchTo().defaultContent();
		Thread.sleep(3000);
		CFOcountPOM.closeCategories(driver).click();
		extent.endTest(test);
		extent.flush();
	}
	
	@Test(priority = 5)
	void UsersCountMatch() throws InterruptedException
	{
		test = extent.startTest(" Count by Clicking on 'Users'");
		test.log(LogStatus.INFO, "Test Initiated");
		
		Thread.sleep(4000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		String string_User =CFOcountPOM.clickUsersCount(driver).getText();		//Storing old value of Statutory overdue.
	int	UserCountDas = Integer.parseInt(string_User);
		CFOcountPOM.clickUsersCount(driver).click();
		Thread.sleep(500);
		
		litigationPerformer.MethodsPOM.progress(driver);
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("showdetails"));	//Wait until frame get visible and switch to it.

		js.executeScript("window.scrollBy(0,500)");
		Thread.sleep(3000);
		CFOcountPOM.readTotalItemsD(driver).click();					//Clicking on total items count
		Thread.sleep(500);
		String item = CFOcountPOM.readTotalItemsD(driver).getText();	//Reading total items String value
		String[] bits = item.split(" ");								//Splitting the String
		String compliancesCount = bits[bits.length - 2];				//Getting the second last word (total number of users)
		int UserGrid = Integer.parseInt(compliancesCount);
		if(UserCountDas == UserGrid)
		{
			test.log(LogStatus.PASS, "Number of User grid matches to Dashboard User  Count.");
			test.log(LogStatus.INFO, "No of User in the grid = "+UserGrid+" | Dashboard User  Count = "+UserCountDas);
		}
		else
		{
			test.log(LogStatus.FAIL, "Number of User does not matches to Dashboard User  Count.");
			test.log(LogStatus.INFO, "No of User in the grid = "+UserGrid+" | Dashboard User  Count = "+UserCountDas);
		}
		
		js.executeScript("window.scrollBy(500,0)");						//Scrolling UP window by 2000 px.
		Thread.sleep(4000);
		CFOcountPOM.clickExportImage(driver).click();
		Thread.sleep(5000);
		test.log(LogStatus.PASS, "Excel file Export Successfully");
		Thread.sleep(3000);
		driver.switchTo().defaultContent();
		Thread.sleep(3000);
		CFOcountPOM.closeCategories(driver).click();
		extent.endTest(test);
		extent.flush();
	}
	
	@Test(priority = 6)
	void SummaryofOverdueCompliances() throws InterruptedException
	{
		test = extent.startTest(" Summary of Overdue Compliances");
		test.log(LogStatus.INFO, "Test Initiated");
		
		Thread.sleep(4000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		
		CFOcountPOM.ClickShowAll(driver).click();        //Clicking on Show All
		Thread.sleep(3000);
		litigationPerformer.MethodsPOM.progress(driver);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(100));
		//wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("showdetails"));	//Wait until frame get visible and switch to it.
	WebElement farme=	driver.findElement(By.xpath("//*[@id='showdetails']"));
      driver.switchTo().frame(farme);
      Thread.sleep(3000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='grid']")));
		 Thread.sleep(3000); 
	//	elementsList1=	CFOcountPOM.ActionviewList(driver);
		//elementsList1.get(1).click();                   //Clicking on OverdueView  button
	//	driver.findElement(By.xpath("//*[@id='grid']/div[3]/table/tbody/tr[1]/td[16]/a[1]")).click();
	//	Thread.sleep(3000);
	//	CFOcountPOM.closeDocument(driver).click();						//Closing the View Document
		 CFOcountPOM.clickExportImage(driver).click();
			Thread.sleep(4000);
			test.log(LogStatus.PASS, "Excel file Export Successfully");
			Thread.sleep(4000);
			By locator = By.xpath("//*[@id='grid']/div[3]/table/tbody/tr/td/a[1]");
			
			wait.until(ExpectedConditions.presenceOfElementLocated(locator));
			Thread.sleep(4000);
			// retrieving "foo-button" HTML element
			List<WebElement> ViewButtons = driver.findElements(locator);							
			ViewButtons.get(1).click();
			Thread.sleep(3000);
			test.log(LogStatus.INFO, "overView success");
			CFOcountPOM.closeDocument(driver).click();
			Thread.sleep(3000);
			driver.switchTo().defaultContent();
			Thread.sleep(3000);
			CFOcountPOM.closeCategories(driver).click();
			Thread.sleep(1000);
		extent.endTest(test);
		extent.flush();			
		
	}
	
	@Test(priority = 7)
	void NotCompleted_PieChart() throws InterruptedException
	{
		test = extent.startTest("Pie Chart -Completion Status- 'Not Completed' Count Verification");
		test.log(LogStatus.INFO, "Test Initiated");
		
		Thread.sleep(3000);
		Actions action = new Actions(driver);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,500)");			//Scrolling down window by 1000 px.cfo
	//	js.executeScript("window.scrollBy(0,100)");
		Thread.sleep(3000);
		int NotCompletedValue = Integer.parseInt(CFOcountPOM.clickNotCompleted(driver).getText());	//Reading value of 'Not Completed'
		CFOcountPOM.clickNotCompleted(driver).click();									//CLicking on 'Not Completed' count
		
		Thread.sleep(500);
		int critical = Integer.parseInt(CFOcountPOM.readCritical(driver).getText());	//Reading Critical risk count.
		int high = Integer.parseInt(CFOcountPOM.readHigh(driver).getText());			//Reading High risk count.
		int medium = Integer.parseInt(CFOcountPOM.readMedium(driver).getText());		//Reading Medium risk count.
		int low = Integer.parseInt(CFOcountPOM.readLow(driver).getText());				//Reading Low risk count.
		
		int total = critical + high + medium + low;
		
		if(NotCompletedValue == total)
		{
			test.log(LogStatus.PASS, "Not Completed' Compliance Count matches to sum of all risked compliances.");
			test.log(LogStatus.INFO, "Total Not Completed' Compliances : "+total);
		}
		else
		{
			test.log(LogStatus.FAIL, "'Not Completed' Compliance Count doesn't matches to sum of all risked compliances.");
			test.log(LogStatus.INFO, "Total 'Not Completed' Compliances : "+total+" | Total Sum : "+NotCompletedValue);
		}
	
		if(NotCompletedValue > 0)
		{
			if(critical > 0)
			{
				ApprovalcountPOM.GraphCount(driver, test, "Critical", critical, "Statutory");
			}
			else
			{
				test.log(LogStatus.SKIP, "'Critical' Risk Compliance Count = "+critical);
			}
			
			if(high > 0)
			{
				ApprovalcountPOM.GraphCount(driver, test, "High", high, "Statutory");
			}
			else
			{
				test.log(LogStatus.SKIP, "'High' Risk Compliance Count = "+high);
			}
			
			if(medium > 0)
			{
				ApprovalcountPOM.GraphCount(driver, test, "Medium", medium, "Statutory");
			}
			else
			{
				test.log(LogStatus.SKIP, "'Medium' Risk Compliance Count = "+medium);
			}
			
			if(low > 0)
			{
				ApprovalcountPOM.GraphCount(driver, test, "Low", low, "Statutory");
			}
			else
			{
				test.log(LogStatus.SKIP, "'Low' Risk Compliance Count = "+low);
			}
			
			Thread.sleep(500);
			action.moveToElement(CFOcountPOM.clickBack1(driver)).click().build().perform();	 //Clicking on Back button
		}
		else
		{
			test.log(LogStatus.SKIP, " 'Completed' Compliance Count = "+NotCompletedValue);
			
			Thread.sleep(500);
			action.moveToElement(CFOcountPOM.clickBack1(driver)).click().build().perform();	//Clicking on Dashboard
		}
		
		
		extent.endTest(test);
		extent.flush();
	}
	
	@Test(priority = 8)
	void ClosedDelayed_PieChart() throws InterruptedException
	{
		test = extent.startTest("Pie Chart -Completion Status- 'Closed Delayed' Count Verification");
		test.log(LogStatus.INFO, "Test Initiated");
		
		Actions action = new Actions(driver);
	//	JavascriptExecutor js = (JavascriptExecutor) driver;
	//	js.executeScript("window.scrollBy(0,500)");						//Scrolling down window by 1000 px.
		
		Thread.sleep(2000);
		int ClosedDelayedValue = Integer.parseInt(CFOcountPOM.clickClosedDelayedA(driver).getText());	//Reading value of 'After Due Date'
		
		CFOcountPOM.clickClosedDelayedA(driver).click();								//CLicking on 'Not Completed' count
		
		Thread.sleep(3000);
		int critical = Integer.parseInt(CFOcountPOM.readCritical(driver).getText());	//Reading Critical risk count.
		int high = Integer.parseInt(CFOcountPOM.readHigh(driver).getText());		//reading High risk count.
		int medium = Integer.parseInt(CFOcountPOM.readMedium(driver).getText());	//reading Medium risk count.
		int low = Integer.parseInt(CFOcountPOM.readLow(driver).getText());			//reading Low risk count.
		
		int total = critical + high + medium + low;
		
		if(ClosedDelayedValue == total)
		{
			test.log(LogStatus.PASS, "'Closed Delayed' Compliance Count matches to sum of all risked compliances.");
			test.log(LogStatus.INFO, "Total 'Closed Delayed' Compliances : "+total);
		}
		else
		{
			test.log(LogStatus.FAIL, "'Closed Delayed' Compliance Count doesn't matches to sum of all risked compliances.");
			test.log(LogStatus.INFO, "Total 'Closed Delayed' Compliances : "+total+" | Total Sum : "+ClosedDelayedValue);
		}
		
		if(ClosedDelayedValue > 0)
		{
			if(critical > 0)
			{
				ApprovalcountPOM.GraphCount1(driver, test, "Critical", critical, "Statutory");
			}
			else
			{
				test.log(LogStatus.SKIP, "'Critical' Risk Compliance Count = "+critical);
			}
			
			if(high > 0)
			{
				ApprovalcountPOM.GraphCount1(driver, test, "High", high, "Statutory");
			}
			else
			{
				test.log(LogStatus.SKIP, "'High' Risk Compliance Count = "+high);
			}
			
			if(medium > 0)
			{
				ApprovalcountPOM.GraphCount1(driver, test, "Medium", medium, "Statutory");
			}
			else
			{
				test.log(LogStatus.SKIP, "'Medium' Risk Compliance Count = "+medium);
			}
			
			if(low > 0)
			{
				ApprovalcountPOM.GraphCount1(driver, test, "Low", low, "Statutory");
			}
			else
			{
				test.log(LogStatus.SKIP, "'Low' Risk Compliance Count = "+low);
			}
			
			Thread.sleep(500);
			action.moveToElement(CFOcountPOM.clickBack1(driver)).click().build().perform();	 //Clicking on Back button
		}
		else
		{
			test.log(LogStatus.SKIP, "'Closed Delayed' Compliance Count = "+ClosedDelayedValue);
			
			Thread.sleep(500);
			action.moveToElement(CFOcountPOM.clickBack1(driver)).click().build().perform();	//Clicking on Dashboard
		}
		extent.endTest(test);
		extent.flush();
	}
	
	@Test(priority = 9)
	void ClosedTimely_PieChart() throws InterruptedException
	{
		test = extent.startTest("Pie Chart -Completion Status- 'Closed Timely' Count Verification");
		test.log(LogStatus.INFO, "Test Initiated");
		
		Actions action = new Actions(driver);
		//JavascriptExecutor js = (JavascriptExecutor) driver;
		//js.executeScript("window.scrollBy(0,500)");						//Scrolling down window by 1000 px.
		try{
		Thread.sleep(1500);
		int ClosedTimelyValue = Integer.parseInt(CFOcountPOM.clickClosedTimelyA(driver).getText());	//Reading value of 'After Due Date'
		CFOcountPOM.clickClosedTimelyA(driver).click();								//CLicking on 'Not Completed' count
		
		Thread.sleep(500);
		int critical = Integer.parseInt(CFOcountPOM.readCritical(driver).getText());	//Reading Critical risk count.
		int high = Integer.parseInt(CFOcountPOM.readHigh(driver).getText());		//reading High risk count.
		int medium = Integer.parseInt(CFOcountPOM.readMedium(driver).getText());	//reading Medium risk count.
		int low = Integer.parseInt(CFOcountPOM.readLow(driver).getText());			//reading Low risk count.
		
		int total = critical + high + medium + low;
		
		if(ClosedTimelyValue == total)
		{
			test.log(LogStatus.PASS, "'Closed Timely' Compliance Count matches to sum of all risked compliances.");
			test.log(LogStatus.INFO, "Total 'Closed Timely' Compliances : "+total);
		}
		else
		{
			test.log(LogStatus.FAIL, "'Closed Timely' Compliance Count doesn't matches to sum of all risked compliances.");
			test.log(LogStatus.INFO, "Total 'Closed Timely' Compliances : "+total+" | Total Sum : "+ClosedTimelyValue);
		}
		
		if(ClosedTimelyValue > 0)
		{
			if(critical > 0)
			{
				ApprovalcountPOM.GraphCount1(driver, test, "Critical", critical, "Statutory");
			}
			else
			{
				test.log(LogStatus.SKIP, "'Critical' Risk Compliance Count = "+critical);
			}
			
			if(high > 0)
			{
				ApprovalcountPOM.GraphCount1(driver, test, "High", high, "Statutory");
			}
			else
			{
				test.log(LogStatus.SKIP, "'High' Risk Compliance Count = "+high);
			}
			
			if(medium > 0)
			{
				ApprovalcountPOM.GraphCount1(driver, test, "Medium", medium, "Statutory");
			}
			else
			{
				test.log(LogStatus.SKIP, "'Medium' Risk Compliance Count = "+medium);
			}
			
			if(low > 0)
			{
				ApprovalcountPOM.GraphCount1(driver, test, "Low", low, "Statutory");
			}
			else
			{
				test.log(LogStatus.SKIP, "'Low' Risk Compliance Count = "+low);
			}
			
			Thread.sleep(500);
			action.moveToElement(CFOcountPOM.clickBack1(driver)).click().build().perform();	 //Clicking on Back button
		}
		else
		{
			test.log(LogStatus.SKIP, "'Closed Timely' Compliance Count = "+ClosedTimelyValue);
			
			Thread.sleep(500);
			action.moveToElement(CFOcountPOM.clickBack1(driver)).click().build().perform();	//Clicking on Dashboard
		}
		extent.endTest(test);
		extent.flush();
		}catch(NumberFormatException ex){
			ex.printStackTrace();
		}
	}
	
	
}
