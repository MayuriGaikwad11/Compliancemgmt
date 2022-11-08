package cfo;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.time.temporal.TemporalAmount;
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
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import performer.OverduePOM;

public class CFOcountStatutory 
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
	public static List<WebElement> elementsList2 = null;
	public static List<WebElement> elementsList3 = null;
	public static List<WebElement> elementsList4 = null;
	public static List<WebElement> menus = null;
	public int count = 0;
	public int interest = 0;					//Variable created for reading Interest
	public int penalty = 0;						//Variable created for reading Penalty
	
	//Write "CFO-diy" for DIYProduction link.
	//Write "CFO" for login.avantis for CFO Finance
	public static String link = "CFO";		//Check link in excel sheet first.
	
	public static XSSFSheet ReadExcel() throws IOException
	{
		//String workingDir = System.getProperty("webdriver.chrome.driver","C:/March2022/PerformerPom/Driver/chromedriver.exe");
		fis = new FileInputStream("C:/March2022/PerformerPom/TestData/ComplianceSheet.xlsx");
		workbook = new XSSFWorkbook(fis);
		sheet = workbook.getSheetAt(2);					//Retrieving third sheet of Workbook
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
		test = extent.startTest("Loging In - CFO Finance (Statutory)");
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
		
		//CFOcountPOM.clickRefresh(driver).click();
		//Thread.sleep(3000);
		
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
	/*
	@Test(priority = 2)
	void CategoriesCountMatch() throws InterruptedException
	{
		test = extent.startTest(" Count by Clicking on 'Categories'");
		test.log(LogStatus.INFO, "Test Initiated");
		
		Thread.sleep(2000);
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
		test.log(LogStatus.PASS, "Excel file Export Successfully");	
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
	void CompliancesCountMatch() throws InterruptedException
	{
		test = extent.startTest(" Count by Clicking on 'Compliances'");
		test.log(LogStatus.INFO, "Test Initiated");
		
		Thread.sleep(2000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		String string_Compliances =CFOcountPOM.readCompliances(driver).getText();		//Storing old value of Statutory overdue.
	int	CompliancesCountDas = Integer.parseInt(string_Compliances);
		CFOcountPOM.readCompliances(driver).click();
		Thread.sleep(500);
		
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
			test.log(LogStatus.PASS, "Number of Compliances grid matches to Dashboard Compliances  Count.");
			test.log(LogStatus.INFO, "No of Compliances in the grid = "+ComcountGrid+" | Dashboard Compliances  Count = "+CompliancesCountDas);
		}
		else
		{
			test.log(LogStatus.FAIL, "Number of compliances does not matches to Dashboard Statutory Overdue Count.");
			test.log(LogStatus.INFO, "No of Compliances in the grid = "+ComcountGrid+" | Dashboard Compliances  Count = "+CompliancesCountDas);
		}
		js.executeScript("window.scrollBy(500,0)");						//Scrolling UP window by 2000 px.
		driver.switchTo().defaultContent();
		Thread.sleep(3000);
		CFOcountPOM.closeCategories(driver).click();
		extent.endTest(test);
		extent.flush();
	}
	
//	@Test(priority = 4)
	void UsersCountMatch() throws InterruptedException
	{
		test = extent.startTest(" Count by Clicking on 'Users'");
		test.log(LogStatus.INFO, "Test Initiated");
		
		Thread.sleep(2000);
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
	
	@Test(priority = 5)
	void clickPenaltyStatutory() throws InterruptedException
	{
		test = extent.startTest("'Penalty' Export");
		test.log(LogStatus.INFO, "Test Initiated");
		
		Thread.sleep(1500);
	//	String oldStr = CFOcountPOM.readPenaltyCount(driver).getText();
	//	String newStr = oldStr.replaceAll(",","");		//Removing comma (,) from the read input.
	//	int valuePenalty = Integer.parseInt(newStr);	//Storing old value of 'Compliances'.
		
		CFOcountPOM.readPenaltyCount(driver).click();					//Clicking on 'Penalty'.
		Thread.sleep(3000);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("showdetails"));	//Wait until frame get visible and switch to it.
		Thread.sleep(1000);
		CFOcountPOM.clickExportImage(driver).click();
		Thread.sleep(5000);
		test.log(LogStatus.PASS, "Excel file Export Successfully");
	
		driver.switchTo().defaultContent();
		Thread.sleep(3000);
		CFOcountPOM.closeCategories(driver).click();
		
		Thread.sleep(3000);
		
		//CFOcountPOM.CountPenalty(driver, test, valuePenalty);
		
		extent.endTest(test);
		extent.flush();
	}
	
//	@Test(priority = 6)
	void SummaryofOverdueCompliances() throws InterruptedException
	{
		test = extent.startTest(" Summary of Overdue Compliances");
		test.log(LogStatus.INFO, "Test Initiated");
		
		Thread.sleep(4000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		
		CFOcountPOM.ClickShowAll(driver).click();        //Clicking on Show All
		Thread.sleep(3000);
		litigationPerformer.MethodsPOM.progress(driver);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
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
		
		Thread.sleep(500);
		Actions action = new Actions(driver);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,500)");			//Scrolling down window by 1000 px.
		
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
				CFOcountPOM.GraphCount(driver, test, "Critical", critical, "Statutory");
			}
			else
			{
				test.log(LogStatus.SKIP, "'Critical' Risk Compliance Count = "+critical);
			}
			
			if(high > 0)
			{
				CFOcountPOM.GraphCount(driver, test, "High", high, "Statutory");
			}
			else
			{
				test.log(LogStatus.SKIP, "'High' Risk Compliance Count = "+high);
			}
			
			if(medium > 0)
			{
				CFOcountPOM.GraphCount(driver, test, "Medium", medium, "Statutory");
			}
			else
			{
				test.log(LogStatus.SKIP, "'Medium' Risk Compliance Count = "+medium);
			}
			
			if(low > 0)
			{
				CFOcountPOM.GraphCount(driver, test, "Low", low, "Statutory");
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
		int ClosedDelayedValue = Integer.parseInt(CFOcountPOM.clickClosedDelayed(driver).getText());	//Reading value of 'After Due Date'
		
		CFOcountPOM.clickClosedDelayed(driver).click();								//CLicking on 'Not Completed' count
		
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
				CFOcountPOM.GraphCount1(driver, test, "Critical", critical, "Statutory");
			}
			else
			{
				test.log(LogStatus.SKIP, "'Critical' Risk Compliance Count = "+critical);
			}
			
			if(high > 0)
			{
				CFOcountPOM.GraphCount1(driver, test, "High", high, "Statutory");
			}
			else
			{
				test.log(LogStatus.SKIP, "'High' Risk Compliance Count = "+high);
			}
			
			if(medium > 0)
			{
				CFOcountPOM.GraphCount1(driver, test, "Medium", medium, "Statutory");
			}
			else
			{
				test.log(LogStatus.SKIP, "'Medium' Risk Compliance Count = "+medium);
			}
			
			if(low > 0)
			{
				CFOcountPOM.GraphCount1(driver, test, "Low", low, "Statutory");
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
		int ClosedTimelyValue = Integer.parseInt(CFOcountPOM.clickClosedTimely(driver).getText());	//Reading value of 'After Due Date'
		CFOcountPOM.clickClosedTimely(driver).click();								//CLicking on 'Not Completed' count
		
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
				CFOcountPOM.GraphCount1(driver, test, "Critical", critical, "Statutory");
			}
			else
			{
				test.log(LogStatus.SKIP, "'Critical' Risk Compliance Count = "+critical);
			}
			
			if(high > 0)
			{
				CFOcountPOM.GraphCount1(driver, test, "High", high, "Statutory");
			}
			else
			{
				test.log(LogStatus.SKIP, "'High' Risk Compliance Count = "+high);
			}
			
			if(medium > 0)
			{
				CFOcountPOM.GraphCount1(driver, test, "Medium", medium, "Statutory");
			}
			else
			{
				test.log(LogStatus.SKIP, "'Medium' Risk Compliance Count = "+medium);
			}
			
			if(low > 0)
			{
				CFOcountPOM.GraphCount1(driver, test, "Low", low, "Statutory");
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
	
	@Test(priority = 10)
	void NotApplicable_PieChart() throws InterruptedException
	{
		test = extent.startTest("Pie Chart -Completion Status- 'Not Applicable' Count Verification");
		test.log(LogStatus.INFO, "Test Initiated");
		
		Actions action = new Actions(driver);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOf(CFOcountPOM.clickNotApplicable(driver)));
		
		Thread.sleep(1000);
		int NotApplicableValue = Integer.parseInt(CFOcountPOM.clickNotApplicable(driver).getText());	//Reading value of 'After Due Date'
		CFOcountPOM.clickNotApplicable(driver).click();								//CLicking on 'Not Completed' count
		
		Thread.sleep(500);
		int critical = Integer.parseInt(CFOcountPOM.readCritical(driver).getText());	//Reading Critical risk count.
		int high = Integer.parseInt(CFOcountPOM.readHigh(driver).getText());		//reading High risk count.
		int medium = Integer.parseInt(CFOcountPOM.readMedium(driver).getText());	//reading Medium risk count.
		int low = Integer.parseInt(CFOcountPOM.readLow(driver).getText());			//reading Low risk count.
		
		int total = critical + high + medium + low;
		
		if(NotApplicableValue == total)
		{
			test.log(LogStatus.PASS, "'Not Applicable' Compliance Count matches to sum of all risked compliances.");
			test.log(LogStatus.INFO, "Total 'Not Applicable' Compliances : "+total);
		}
		else
		{
			test.log(LogStatus.FAIL, "'Not Applicable' Compliance Count doesn't matches to sum of all risked compliances.");
			test.log(LogStatus.INFO, "Total 'Not Applicable' Compliances : "+total+" | Total Sum : "+NotApplicableValue);
		}
		
		if(NotApplicableValue > 0)
		{
			if(critical > 0)
			{
				CFOcountPOM.GraphCount1(driver, test, "Critical", critical, "Statutory");
			}
			else
			{
				test.log(LogStatus.SKIP, "'Critical' Risk Compliance Count = "+critical);
			}
			
			if(high > 0)
			{
				CFOcountPOM.GraphCount1(driver, test, "High", high, "Statutory");
			}
			else
			{
				test.log(LogStatus.SKIP, "'High' Risk Compliance Count = "+high);
			}
			
			if(medium > 0)
			{
				CFOcountPOM.GraphCount1(driver, test, "Medium", medium, "Statutory");
			}
			else
			{
				test.log(LogStatus.SKIP, "'Medium' Risk Compliance Count = "+medium);
			}
			
			if(low > 0)
			{
				CFOcountPOM.GraphCount1(driver, test, "Low", low, "Statutory");
			}
			else
			{
				test.log(LogStatus.SKIP, "'Low' Risk Compliance Count = "+low);
			}
			
			Thread.sleep(500);
			action.moveToElement(CFOcountPOM.clickBack1(driver)).click().build().perform();	 //Clicking on Back button
			
			Thread.sleep(500);
			performer.OverduePOM.clickDashboard(driver).click();			//Clicking on Dashboard
		}
		else
		{
			test.log(LogStatus.SKIP, "'Not Applicable' Compliance Count = "+NotApplicableValue);
			
			Thread.sleep(500);
			action.moveToElement(CFOcountPOM.clickBack1(driver)).click().build().perform();	//Clicking on Dashboard
			
			Thread.sleep(500);
			performer.OverduePOM.clickDashboard(driver).click();			//Clicking on Dashboard
		}
		extent.endTest(test);
		extent.flush();
	}
		
	@Test(priority = 11)
	void Overdue_PieChart() throws InterruptedException
	{
		test = extent.startTest("Pie Chart -Not Completed Status- 'Overdue' Count Verification");
		test.log(LogStatus.INFO, "Test Initiated");
		
		Thread.sleep(500);
		Actions action = new Actions(driver);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,500)");			//Scrolling down window by 1000 px.
		
		Thread.sleep(3000);
		int OverdueValue = Integer.parseInt(CFOcountPOM.clickOverdue(driver).getText());	//Reading value of 'Not Completed'
		CFOcountPOM.clickOverdue(driver).click();									//CLicking on 'Not Completed' count
		
		Thread.sleep(500);
		int critical = Integer.parseInt(CFOcountPOM.readCritical(driver).getText());	//Reading Critical risk count.
		int high = Integer.parseInt(CFOcountPOM.readHigh(driver).getText());			//Reading High risk count.
		int medium = Integer.parseInt(CFOcountPOM.readMedium(driver).getText());		//Reading Medium risk count.
		int low = Integer.parseInt(CFOcountPOM.readLow(driver).getText());				//Reading Low risk count.
		
		int total = critical + high + medium + low;
		
		if(OverdueValue == total)
		{
			test.log(LogStatus.PASS, "' Overdue' Compliance Count matches to sum of all risked compliances.");
			test.log(LogStatus.INFO, "Total Overdue' Compliances : "+total);
		}
		else
		{
			test.log(LogStatus.FAIL, "'Overdue' Compliance Count doesn't matches to sum of all risked compliances.");
			test.log(LogStatus.INFO, "Total 'Overdue' Compliances : "+total+" | Total Sum : "+OverdueValue);
		}
	
		if(OverdueValue > 0)
		{
			if(critical > 0)
			{
				CFOcountPOM.GraphCount(driver, test, "Critical", critical, "Statutory");
			}
			else
			{
				test.log(LogStatus.SKIP, "'Critical' Risk Compliance Count = "+critical);
			}
			
			if(high > 0)
			{
				CFOcountPOM.GraphCount(driver, test, "High", high, "Statutory");
			}
			else
			{
				test.log(LogStatus.SKIP, "'High' Risk Compliance Count = "+high);
			}
			
			if(medium > 0)
			{
				CFOcountPOM.GraphCount(driver, test, "Medium", medium, "Statutory");
			}
			else
			{
				test.log(LogStatus.SKIP, "'Medium' Risk Compliance Count = "+medium);
			}
			
			if(low > 0)
			{
				CFOcountPOM.GraphCount(driver, test, "Low", low, "Statutory");
			}
			else
			{
				test.log(LogStatus.SKIP, "'Low' Risk Compliance Count = "+low);
			}
			
			Thread.sleep(5000);
			action.moveToElement(CFOcountPOM.clickBack2(driver)).click().build().perform();	 //Clicking on Back button
			Thread.sleep(2000);
			performer.OverduePOM.clickDashboard(driver).click();			//Clicking on Dashboard
	
		}
		else
		{
			test.log(LogStatus.SKIP, " 'Overdue' Compliance Count = "+OverdueValue);
			
			Thread.sleep(500);
			action.moveToElement(CFOcountPOM.clickBack2(driver)).click().build().perform();	//Clicking on Dashboard
			Thread.sleep(2000);
			performer.OverduePOM.clickDashboard(driver).click();			//Clicking on Dashboard
	
		}
		
		extent.endTest(test);
		extent.flush();
	}
	
	@Test(priority = 12)
	void dueToday_PieChart() throws InterruptedException
	{
		test = extent.startTest("Pie Chart -Not Completed Status- 'dueToday' Count Verification");
		test.log(LogStatus.INFO, "Test Initiated");
		
		Thread.sleep(500);
		Actions action = new Actions(driver);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,500)");			//Scrolling down window by 1000 px.
		
		Thread.sleep(3000);
		int dueTodayValue = Integer.parseInt(CFOcountPOM.clickdueToday(driver).getText());	//Reading value of 'Not Completed'
		CFOcountPOM.clickdueToday(driver).click();									//CLicking on 'Not Completed' count
		
		Thread.sleep(500);
		int critical = Integer.parseInt(CFOcountPOM.readCritical(driver).getText());	//Reading Critical risk count.
		int high = Integer.parseInt(CFOcountPOM.readHigh(driver).getText());			//Reading High risk count.
		int medium = Integer.parseInt(CFOcountPOM.readMedium(driver).getText());		//Reading Medium risk count.
		int low = Integer.parseInt(CFOcountPOM.readLow(driver).getText());				//Reading Low risk count.
		
		int total = critical + high + medium + low;
		
		if(dueTodayValue == total)
		{
			test.log(LogStatus.PASS, "' dueToday' Compliance Count matches to sum of all risked compliances.");
			test.log(LogStatus.INFO, "Total Overdue' Compliances : "+total);
		}
		else
		{
			test.log(LogStatus.FAIL, "'dueToday' Compliance Count doesn't matches to sum of all risked compliances.");
			test.log(LogStatus.INFO, "Total 'Overdue' Compliances : "+total+" | Total Sum : "+dueTodayValue);
		}
	
		if(dueTodayValue > 0)
		{
			if(critical > 0)
			{
				CFOcountPOM.GraphCount(driver, test, "Critical", critical, "Statutory");
			}
			else
			{
				test.log(LogStatus.SKIP, "'Critical' Risk Compliance Count = "+critical);
			}
			
			if(high > 0)
			{
				CFOcountPOM.GraphCount(driver, test, "High", high, "Statutory");
			}
			else
			{
				test.log(LogStatus.SKIP, "'High' Risk Compliance Count = "+high);
			}
			
			if(medium > 0)
			{
				CFOcountPOM.GraphCount(driver, test, "Medium", medium, "Statutory");
			}
			else
			{
				test.log(LogStatus.SKIP, "'Medium' Risk Compliance Count = "+medium);
			}
			
			if(low > 0)
			{
				CFOcountPOM.GraphCount(driver, test, "Low", low, "Statutory");
			}
			else
			{
				test.log(LogStatus.SKIP, "'Low' Risk Compliance Count = "+low);
			}
			
			Thread.sleep(5000);
			action.moveToElement(CFOcountPOM.clickBack2(driver)).click().build().perform();	 //Clicking on Back button
			Thread.sleep(2000);
			performer.OverduePOM.clickDashboard(driver).click();			//Clicking on Dashboard
	
		}
		else
		{
			test.log(LogStatus.SKIP, " 'Due Today' Compliance Count = "+dueTodayValue);
			
			Thread.sleep(500);
			action.moveToElement(CFOcountPOM.clickBack2(driver)).click().build().perform();	//Clicking on Dashboard
			Thread.sleep(2000);
			performer.OverduePOM.clickDashboard(driver).click();			//Clicking on Dashboard
	
		}
		
		extent.endTest(test);
		extent.flush();
	}
	
	@Test(priority = 13)
	void pendingForReview_PieChart() throws InterruptedException
	{
		test = extent.startTest("Pie Chart -Not Completed Status- 'Pending For Review' Count Verification");
		test.log(LogStatus.INFO, "Test Initiated");
		
		Thread.sleep(500);
		Actions action = new Actions(driver);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,500)");			//Scrolling down window by 1000 px.
		
		Thread.sleep(3000);
		int pendingForReviewValue = Integer.parseInt(CFOcountPOM.clickpendingForReview(driver).getText());	//Reading value of 'Not Completed'
		CFOcountPOM.clickpendingForReview(driver).click();									//CLicking on 'Not Completed' count
		
		Thread.sleep(500);
		int critical = Integer.parseInt(CFOcountPOM.readCritical(driver).getText());	//Reading Critical risk count.
		int high = Integer.parseInt(CFOcountPOM.readHigh(driver).getText());			//Reading High risk count.
		int medium = Integer.parseInt(CFOcountPOM.readMedium(driver).getText());		//Reading Medium risk count.
		int low = Integer.parseInt(CFOcountPOM.readLow(driver).getText());				//Reading Low risk count.
		
		int total = critical + high + medium + low;
		
		if(pendingForReviewValue == total)
		{
			test.log(LogStatus.PASS, "' Pending For Review' Compliance Count matches to sum of all risked compliances.");
			test.log(LogStatus.INFO, "Total Overdue' Compliances : "+total);
		}
		else
		{
			test.log(LogStatus.FAIL, "'Pending For Review' Compliance Count doesn't matches to sum of all risked compliances.");
			test.log(LogStatus.INFO, "Total 'Overdue' Compliances : "+total+" | Total Sum : "+pendingForReviewValue);
		}
	
		if(pendingForReviewValue > 0)
		{
			if(critical > 0)
			{
				CFOcountPOM.GraphCount1(driver, test, "Critical", critical, "Statutory");
			}
			else
			{
				test.log(LogStatus.SKIP, "'Critical' Risk Compliance Count = "+critical);
			}
			
			if(high > 0)
			{
				CFOcountPOM.GraphCount1(driver, test, "High", high, "Statutory");
			}
			else
			{
				test.log(LogStatus.SKIP, "'High' Risk Compliance Count = "+high);
			}
			
			if(medium > 0)
			{
				CFOcountPOM.GraphCount1(driver, test, "Medium", medium, "Statutory");
			}
			else
			{
				test.log(LogStatus.SKIP, "'Medium' Risk Compliance Count = "+medium);
			}
			
			if(low > 0)
			{
				CFOcountPOM.GraphCount1(driver, test, "Low", low, "Statutory");
			}
			else
			{
				test.log(LogStatus.SKIP, "'Low' Risk Compliance Count = "+low);
			}
			
			Thread.sleep(5000);
			action.moveToElement(CFOcountPOM.clickBack2(driver)).click().build().perform();	 //Clicking on Back button
			Thread.sleep(2000);
			performer.OverduePOM.clickDashboard(driver).click();			//Clicking on Dashboard
	
		}
		else
		{
			test.log(LogStatus.SKIP, " 'Pending For Review' Compliance Count = "+pendingForReviewValue);
			
			Thread.sleep(500);
			action.moveToElement(CFOcountPOM.clickBack2(driver)).click().build().perform();	//Clicking on Dashboard
			Thread.sleep(2000);
			performer.OverduePOM.clickDashboard(driver).click();			//Clicking on Dashboard
	
		}
		
		extent.endTest(test);
		extent.flush();
	}
	
	@Test(priority = 14)
	void inProgress_PieChart() throws InterruptedException
	{
		test = extent.startTest("Pie Chart -Not Completed Status- 'In Progress' Count Verification");
		test.log(LogStatus.INFO, "Test Initiated");
		
		Thread.sleep(500);
		Actions action = new Actions(driver);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,500)");			//Scrolling down window by 1000 px.
		
		Thread.sleep(3000);
		int inProgressValue = Integer.parseInt(CFOcountPOM.clickinProgress(driver).getText());	//Reading value of 'Not Completed'
		CFOcountPOM.clickinProgress(driver).click();									//CLicking on 'Not Completed' count
		
		Thread.sleep(500);
		int critical = Integer.parseInt(CFOcountPOM.readCritical(driver).getText());	//Reading Critical risk count.
		int high = Integer.parseInt(CFOcountPOM.readHigh(driver).getText());			//Reading High risk count.
		int medium = Integer.parseInt(CFOcountPOM.readMedium(driver).getText());		//Reading Medium risk count.
		int low = Integer.parseInt(CFOcountPOM.readLow(driver).getText());				//Reading Low risk count.
		
		int total = critical + high + medium + low;
		
		if(inProgressValue == total)
		{
			test.log(LogStatus.PASS, "' In Progress' Compliance Count matches to sum of all risked compliances.");
			test.log(LogStatus.INFO, "Total Overdue' Compliances : "+total);
		}
		else
		{
			test.log(LogStatus.FAIL, "'In Progress' Compliance Count doesn't matches to sum of all risked compliances.");
			test.log(LogStatus.INFO, "Total 'Overdue' Compliances : "+total+" | Total Sum : "+inProgressValue);
		}
	
		if(inProgressValue > 0)
		{
			if(critical > 0)
			{
				CFOcountPOM.GraphCount1(driver, test, "Critical", critical, "Statutory");
			}
			else
			{
				test.log(LogStatus.SKIP, "'Critical' Risk Compliance Count = "+critical);
			}
			
			if(high > 0)
			{
				CFOcountPOM.GraphCount1(driver, test, "High", high, "Statutory");
			}
			else
			{
				test.log(LogStatus.SKIP, "'High' Risk Compliance Count = "+high);
			}
			
			if(medium > 0)
			{
				CFOcountPOM.GraphCount1(driver, test, "Medium", medium, "Statutory");
			}
			else
			{
				test.log(LogStatus.SKIP, "'Medium' Risk Compliance Count = "+medium);
			}
			
			if(low > 0)
			{
				CFOcountPOM.GraphCount1(driver, test, "Low", low, "Statutory");
			}
			else
			{
				test.log(LogStatus.SKIP, "'Low' Risk Compliance Count = "+low);
			}
			
			Thread.sleep(5000);
			action.moveToElement(CFOcountPOM.clickBack2(driver)).click().build().perform();	 //Clicking on Back button
			Thread.sleep(2000);
			performer.OverduePOM.clickDashboard(driver).click();			//Clicking on Dashboard
	
		}
		else
		{
			test.log(LogStatus.SKIP, " 'In Progress' Compliance Count = "+inProgressValue);
			
			Thread.sleep(500);
			action.moveToElement(CFOcountPOM.clickBack2(driver)).click().build().perform();	//Clicking on Dashboard
			Thread.sleep(2000);
			performer.OverduePOM.clickDashboard(driver).click();			//Clicking on Dashboard
	
		}
		
		extent.endTest(test);
		extent.flush();
	}
	
	@Test(priority = 15)
	void rejected_PieChart() throws InterruptedException
	{
		test = extent.startTest("Pie Chart -Not Completed Status- ' Rejected' Count Verification");
		test.log(LogStatus.INFO, "Test Initiated");
		driver.navigate().refresh();
		Thread.sleep(2000);
		Actions action = new Actions(driver);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,500)");			//Scrolling down window by 1000 px.
		
		Thread.sleep(3000);
		int rejectedValue = Integer.parseInt(CFOcountPOM.clickRejected(driver).getText());	//Reading value of 'Not Completed'
		CFOcountPOM.clickRejected(driver).click();									//CLicking on 'Not Completed' count
		
		Thread.sleep(500);
		int critical = Integer.parseInt(CFOcountPOM.readCritical(driver).getText());	//Reading Critical risk count.
		int high = Integer.parseInt(CFOcountPOM.readHigh(driver).getText());			//Reading High risk count.
		int medium = Integer.parseInt(CFOcountPOM.readMedium(driver).getText());		//Reading Medium risk count.
		int low = Integer.parseInt(CFOcountPOM.readLow(driver).getText());				//Reading Low risk count.
		
		int total = critical + high + medium + low;
		
		if(rejectedValue == total)
		{
			test.log(LogStatus.PASS, "' Rejected' Compliance Count matches to sum of all risked compliances.");
			test.log(LogStatus.INFO, "Total Overdue' Compliances : "+total);
		}
		else
		{
			test.log(LogStatus.FAIL, "'Rejected' Compliance Count doesn't matches to sum of all risked compliances.");
			test.log(LogStatus.INFO, "Total 'Overdue' Compliances : "+total+" | Total Sum : "+rejectedValue);
		}
	
		if(rejectedValue > 0)
		{
			if(critical > 0)
			{
				CFOcountPOM.GraphCount1(driver, test, "Critical", critical, "Statutory");
			}
			else
			{
				test.log(LogStatus.SKIP, "'Critical' Risk Compliance Count = "+critical);
			}
			
			if(high > 0)
			{
				CFOcountPOM.GraphCount1(driver, test, "High", high, "Statutory");
			}
			else
			{
				test.log(LogStatus.SKIP, "'High' Risk Compliance Count = "+high);
			}
			
			if(medium > 0)
			{
				CFOcountPOM.GraphCount1(driver, test, "Medium", medium, "Statutory");
			}
			else
			{
				test.log(LogStatus.SKIP, "'Medium' Risk Compliance Count = "+medium);
			}
			
			if(low > 0)
			{
				CFOcountPOM.GraphCount1(driver, test, "Low", low, "Statutory");
			}
			else
			{
				test.log(LogStatus.SKIP, "'Low' Risk Compliance Count = "+low);
			}
			
			Thread.sleep(5000);
			action.moveToElement(CFOcountPOM.clickBack2(driver)).click().build().perform();	 //Clicking on Back button
			Thread.sleep(2000);
			performer.OverduePOM.clickDashboard(driver).click();			//Clicking on Dashboard
	
		}
		else
		{
			test.log(LogStatus.SKIP, " 'Rejected' Compliance Count = "+rejectedValue);
			
			Thread.sleep(500);
			action.moveToElement(CFOcountPOM.clickBack2(driver)).click().build().perform();	//Clicking on Dashboard
			Thread.sleep(2000);
			performer.OverduePOM.clickDashboard(driver).click();			//Clicking on Dashboard
	
		}
		
		extent.endTest(test);
		extent.flush();
	}
	*/
	//@Test(priority = 16)
	void BargraphLabourCriticalStatutory() throws InterruptedException
	{
		test = extent.startTest("Bar Graph - 'Labour' Count Verification with 'Critical' Risk");
		test.log(LogStatus.INFO, "Test Initiated");
		
		Thread.sleep(500);
		//performer.OverduePOM.clickDashboard(driver).click();			//Clicking on Dashboard
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOf(CFOcountPOM.clickCategories(driver)));
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,925)");						//Scrolling down window by 1000 px.
		
		Thread.sleep(500);
		int labourCritical = Integer.parseInt(CFOcountPOM.clickLabourCritical(driver).getText());	//Reading the High value of Labour compliance
		
		Thread.sleep(1000);
		CFOcountPOM.clickLabourCritical(driver).click();					//Clicking on High bar of Labour  
		
		Thread.sleep(500);
		int ClosedTimely = Integer.parseInt(CFOcountPOM.clickBarClosedTimely(driver).getText());			//reading Closed Timely count.
		int ClosedDelayed = Integer.parseInt(CFOcountPOM.clickBarClosedDelayed(driver).getText());	//reading Closed Delayed count.
		int NotCompleted = Integer.parseInt(CFOcountPOM.clickBarNotCompleted(driver).getText());	//reading Not Completed count.
		int NotApplicable = Integer.parseInt(CFOcountPOM.clickBarNotApplicable(driver).getText());	//reading Not Applicable count.
		
		int total = ClosedTimely + ClosedDelayed + NotCompleted + NotApplicable;				//Calculating the values to match with High value of Labour.
		
		if(labourCritical == total)
		{
			test.log(LogStatus.PASS, "'Labour - Critical' Compliance Count matches to sum of all types of compliances.");
			test.log(LogStatus.INFO, "Total 'Labour - Critical' Compliances : "+total);
		}
		else
		{
			test.log(LogStatus.FAIL, "'Labour - Critical' Compliance Count doesn't matches to sum of all types of compliances.");
			test.log(LogStatus.INFO, "Total 'Labour - Critical' Compliances : "+total+" | Total Sum : "+labourCritical);
		}
		
		if(labourCritical > 0)
		{
			if(ClosedTimely > 0)
			{
				CFOcountPOM.BarGraphCount(driver, test, "Closed Timely", ClosedTimely);
			}
			else
			{
				test.log(LogStatus.SKIP, "'Critical - Closed Timely' Count = "+ClosedTimely);
			}
			
			if(ClosedDelayed > 0)
			{
				CFOcountPOM.BarGraphCount(driver, test, "Closed Delayed", ClosedDelayed);
			}
			else
			{
				test.log(LogStatus.SKIP, "'Critical - Closed Delayed' Count = "+ClosedDelayed);
			}
			if(NotCompleted > 0)
			{
				CFOcountPOM.BarGraphCount(driver, test, "Not Completed", NotCompleted);
			}
			else
			{
				test.log(LogStatus.SKIP, "'Critical - Not Completed' Count = "+NotCompleted);
			}
			if(NotApplicable > 0)
			{
				CFOcountPOM.BarGraphCount(driver, test, "Not Applicable", NotApplicable);
			}
			else
			{
				test.log(LogStatus.SKIP, "'Critical - Not Applicable' Count = "+NotApplicable);
			}
			
			Thread.sleep(500);
			WebElement element = CFOcountPOM.clickBack(driver);
			Actions actions = new Actions(driver);
			actions.moveToElement(element).click().perform();				//Clicking on Back button of Bar Graph.
		}
		else
		{
			test.log(LogStatus.SKIP, "'Labour - Critical' Count = "+labourCritical);
			
			Thread.sleep(500);
			WebElement element = CFOcountPOM.clickBack(driver);
			Actions actions = new Actions(driver);
			actions.moveToElement(element).click().perform();				//Clicking on Back button of Bar Graph.
		}
		extent.endTest(test);
		extent.flush();
	}
	
	@Test(priority = 17)
	void BargraphLabourHighStatutory() throws InterruptedException
	{
		test = extent.startTest("Bar Graph - 'Labour' Count Verification with 'High' risk");
		test.log(LogStatus.INFO, "Test Initiated");
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,925)");						//Scrolling down window by 1000 px.
		
		
		Thread.sleep(1000);
		int labourHigh = Integer.parseInt(CFOcountPOM.clickLabourHigh(driver).getText());	//Reading the Medium value of Labour compliance
		CFOcountPOM.clickLabourHigh(driver).click();					//Clicking on High bar of Labour  
		
		Thread.sleep(500);
		int ClosedTimely = Integer.parseInt(CFOcountPOM.clickBarClosedTimely(driver).getText());			//reading Closed Timely count.
		int ClosedDelayed = Integer.parseInt(CFOcountPOM.clickBarClosedDelayed(driver).getText());	//reading Closed Delayed count.
		int NotCompleted = Integer.parseInt(CFOcountPOM.clickBarNotCompleted(driver).getText());	//reading Not Completed count.
		int NotApplicable = Integer.parseInt(CFOcountPOM.clickBarNotApplicable(driver).getText());	//reading Not Applicable count.
		
		int total = ClosedTimely + ClosedDelayed + NotCompleted + NotApplicable;				//Calculating the values to match with High value of Labour.
		
		if(labourHigh == total)
		{
			test.log(LogStatus.PASS, "'Labour - High' Compliance Count matches to sum of all types of compliances.");
			test.log(LogStatus.INFO, "Total 'Labour - High' Compliances : "+total);
		}
		else
		{
			test.log(LogStatus.FAIL, "'Labour - High' Compliance Count doesn't matches to sum of all types of compliances.");
			test.log(LogStatus.INFO, "Total 'Labour - High' Compliances : "+total+" | Total Sum : "+labourHigh);
		}
		
		if(labourHigh > 0)
		{
			if(ClosedTimely > 0)
			{
				CFOcountPOM.BarGraphCount(driver, test, "Closed Timely", ClosedTimely);
			}
			else
			{
				test.log(LogStatus.SKIP, "'High - Closed Timely' Count = "+ClosedTimely);
			}
			
			if(ClosedDelayed > 0)
			{
				CFOcountPOM.BarGraphCount(driver, test, "Closed Delayed", ClosedDelayed);
			}
			else
			{
				test.log(LogStatus.SKIP, "'High - Closed Delayed' Count = "+ClosedDelayed);
			}
			if(NotCompleted > 0)
			{
				CFOcountPOM.BarGraphCount1(driver, test, "Not Completed", NotCompleted);
			}
			else
			{
				test.log(LogStatus.SKIP, "'High - Not Completed' Count = "+NotCompleted);
			}
			if(NotApplicable > 0)
			{
				CFOcountPOM.BarGraphCount(driver, test, "Not Applicable", NotApplicable);
			}
			else
			{
				test.log(LogStatus.SKIP, "'High - Not Applicable' Count = "+NotApplicable);
			}
			
			Thread.sleep(500);
			WebElement element = CFOcountPOM.clickBack(driver);
			Actions actions = new Actions(driver);
			actions.moveToElement(element).click().perform();				//Clicking on Back button of Bar Graph.
		}
		else
		{
			test.log(LogStatus.SKIP, "'Labour - High' Count = "+labourHigh);
			
			Thread.sleep(500);
			WebElement element = CFOcountPOM.clickBack(driver);
			Actions actions = new Actions(driver);
			actions.moveToElement(element).click().perform();				//Clicking on Back button of Bar Graph.
		}
		extent.endTest(test);
		extent.flush();
	}
	
	
	
	
	
	
	
	
	

	/*@Test(priority = 2)
	void clickCategoriesStatutory() throws InterruptedException
	{
		test = extent.startTest("'Complainces' Count by Clicking on 'Categories'");
		test.log(LogStatus.INFO, "Test Initiated");
		
		Thread.sleep(2000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		int valueCompliances = Integer.parseInt(CFOcountPOM.readCompliances(driver).getText());	//Storing old value of 'Compliances'.
		
		CFOcountPOM.clickCategories(driver).click();					//Clicking on 'Categories'.
		
		Thread.sleep(500);
		litigationPerformer.MethodsPOM.progress(driver);
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("showdetails"));	//Wait until frame get visible and switch to it.
		
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@onclick = 'openpopup(15)']"))); //Wait until first row's Compliance Count to be visible
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='grid']/div[3]/table/tbody/tr[3]/td[4]/div")));
		elementsList = CFOcountPOM.readCompliancesList(driver);			//Searching all values of Compliance 
		int n = elementsList.size();                                      //*[@id="grid"]/div[3]/table/tbody/tr[1]/td[3]/div
		for(int i = 0; i < n; i++)
		{
			int j = Integer.parseInt(elementsList.get(i).getText());	//Reading each Compliance value.
			count = count + j;											//Calculating the read Compliance values.
		}
		
		elementsList1 = CFOcountPOM.readCompliancesList(driver);
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
			
			Thread.sleep(500);
			try
			{
				wait.until(ExpectedConditions.visibilityOf(CFOcountPOM.waitProgress(driver)));
				Thread.sleep(300);
				wait.until(ExpectedConditions.invisibilityOf(CFOcountPOM.waitProgress(driver)));
			}
			catch(Exception e)
			{
				
			}
			
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("APIOverView"));	//Wait until frame get visible and switch to it.
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class='k-grid-content k-auto-scrollable']")));
			
			js.executeScript("window.scrollBy(0,500)");				//Scrolling down window by 2000 px.
			
			CFOcountPOM.readTotalItems1(driver).click();
			
			Thread.sleep(1000);
			String item = CFOcountPOM.readTotalItems1(driver).getText();
			String[] bits = item.split(" ");								//Splitting the String
			String compliancesCount = bits[bits.length - 2];				//Getting the second last word (total number of users)
			
			if(compliancesCount.equalsIgnoreCase("to"))
			{
				Thread.sleep(2500);
				item = CFOcountPOM.readTotalItems1(driver).getText();
				bits = item.split(" ");								//Splitting the String
				compliancesCount = bits[bits.length - 2];
			}
			if(compliancesCount.equalsIgnoreCase("to"))
			{
				Thread.sleep(2500);
				item = CFOcountPOM.readTotalItems1(driver).getText();
				bits = item.split(" ");								//Splitting the String
				compliancesCount = bits[bits.length - 2];
			}
			count1 = Integer.parseInt(compliancesCount);
			
			if(value == count1)
			{
				test.log(LogStatus.PASS, "Compliances count matches. Clicked value = " + value+ ", Grid Records = "+count1);
			}
			else
			{
				test.log(LogStatus.FAIL, "Compliances count does not matches. Clicked value = "+value+", Grid Records = "+count1);
			}
			
			//Thread.sleep(200);
			//js.executeScript("window.scrollBy(500,0)");						//Scrolling UP window by 2000 px.
			driver.switchTo().parentFrame();								//Switching back to parent frame.
			Thread.sleep(100);
			CFOcountPOM.closeCategories_Compliances(driver).click();		//Closing the 'Compliances' pup up.
			
		}
		
		Thread.sleep(200);
		js.executeScript("window.scrollBy(500,0)");						//Scrolling UP window by 2000 px.
		//driver.switchTo().parentFrame();	//Switching back to parent frame.
		driver.switchTo().defaultContent();
		Thread.sleep(3000);
		CFOcountPOM.closeCategories(driver).click();					//Closing the 'Categories' pup up.
	
		//OverduePOM.clickDashboard(driver).click();
		if(count == valueCompliances)
		{
			test.log(LogStatus.PASS, "Categories count = " + n);
			test.log(LogStatus.PASS, "'Compliances' count of Dashboard matches to 'Categories'. Dashboard value = " + valueCompliances + ", Sum of Compliances from 'Categories' click = "+ count);
		}
		else
		{
			test.log(LogStatus.FAIL, "'Compliances' count of Dashboard doesn't matches to 'Categories'. Dashboard value = " + valueCompliances + ", Sum of Compliances from 'Categories' click = "+ count);
		}
		extent.endTest(test);
		extent.flush();
	}
	
	//@Test(priority = 3)
	void ClickCompliancesStatutory() throws InterruptedException
	{
		test = extent.startTest("'Complainces' Count by Clicking on 'Compliances'");
		test.log(LogStatus.INFO, "Test Initiated");
		
		Thread.sleep(1500);
		int valueCompliances = Integer.parseInt(CFOcountPOM.readCompliances(driver).getText());	//Storing value of 'Compliances' as a String to compare.
		
		CFOcountPOM.readCompliances(driver).click();					//Clicking on 'Compliances'.
		
		Thread.sleep(500);
		litigationPerformer.MethodsPOM.progress(driver);
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("showdetails"));	//Wait until frame get visible and switch to it.
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class='k-selectable']")));
		
		Thread.sleep(500);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,500)");				//Scrolling down window by 2000 px.
		
		Thread.sleep(1000);
		CFOcountPOM.readTotalItems1(driver).click();				//Clicking on Total items count to scroll down.
		String getCount = CFOcountPOM.readTotalItems1(driver).getText();	//Storing 'Compliances' count as string.
		String[] bits = getCount.split(" ");							//Splitting the String
		String compliancesCount = bits[bits.length - 2];				//Getting the second last word (total number of users)
		
		if(compliancesCount.equalsIgnoreCase("to"))
		{
			Thread.sleep(2000);
			getCount = CFOcountPOM.readCompliancesItems(driver).getText();
			bits = getCount.split(" ");								//Splitting the String
			compliancesCount = bits[bits.length - 2];
		}
		
		int count = Integer.parseInt(compliancesCount);
		
		driver.switchTo().defaultContent();								//Switching back to parent frame. 
		Thread.sleep(3000);
		CFOcountPOM.closeCategories(driver).click();					//Closing the 'Compliance' window.
		
		if(valueCompliances == count)									//Comparing dashboard Compliance value with inside Compliance value
		{
			test.log(LogStatus.PASS, "'Compliances' count matches to total records count displayed. Dashboard Value = "+ valueCompliances+ ", Actual count = "+ count);
		}
		else
		{
			test.log(LogStatus.FAIL, "'Compliances' count doesn't matches to total records count displayed. Dashboard Value = "+ valueCompliances+ ", Actual count = "+ count);
		}
		extent.endTest(test);
		extent.flush();
	}
	
/*	@Test(priority = 4)
	void clickUsersStatutory() throws InterruptedException
	{
		test = extent.startTest("'Users' Count by Clicking on 'Users'");
		test.log(LogStatus.INFO, "Test Initiated");
		
		Thread.sleep(500);
		if(OverduePOM.closeMessage(driver).isDisplayed())				//If Compliance Updation message popped up,
		{
			OverduePOM.closeMessage(driver).click();					//then close the message.
		}
		
		Thread.sleep(1500);
		int valueUsers = Integer.parseInt(CFOcountPOM.clickUsersCount(driver).getText());	//Storing value of 'Users' as a String to compare.
		
		CFOcountPOM.clickUsersCount(driver).click();					//Clicking on 'Users'. 
		
		//Thread.sleep(1000);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("showdetails"));	//Wait until frame get visible and switch to it.
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,1000)");					//Scrolling down window by 1000 px.
		
		Thread.sleep(3000);
		CFOcountPOM.readUsersCount2(driver).click();
		
		String getCount = CFOcountPOM.readUsersCount2(driver).getText();	//Storing no of Items 'Users' count as string.
		String[] bits = getCount.split(" ");							//Splitting the String
		String usersCount = bits[bits.length - 2];						//Getting the second last word (total number of users)
		if(usersCount.equalsIgnoreCase("to"))
		{
			Thread.sleep(2500);
			getCount = CFOcountPOM.readUsersCount2(driver).getText();
			bits = getCount.split(" ");								//Splitting the String
			usersCount = bits[bits.length - 2];
		}
		int count = Integer.parseInt(usersCount);
		
		driver.switchTo().parentFrame();								//Switching back to parent frame. 
		Thread.sleep(1000);
		CFOcountPOM.closeCategories(driver).click();					//Closing the 'Compliance' window.
		
		if(valueUsers == count)								//Checking if String getCount contains the Value (in string format) 
		{
			test.log(LogStatus.PASS, "'Users' count matches to 'Users' items. Dashboard Value = "+ valueUsers+ ", Actual Value = "+ getCount);
		}
		else
		{
			test.log(LogStatus.FAIL, "Users count does not matches. Dashboard Value = "+ valueUsers+ ", Actual Value = "+ getCount);
		}
		extent.endTest(test);
		extent.flush();
	}
	*/
	
	
	
/*	
	
	
	
	
	
	@Test(priority = 12)
	void BargraphLabourMediumStatutory() throws InterruptedException
	{
		test = extent.startTest("Bar Graph - 'Labour' Count Verification with 'Medium' risk");
		test.log(LogStatus.INFO, "Test Initiated");
		
		Thread.sleep(1000);
		int labourMedium = Integer.parseInt(CFOcountPOM.clickLabourMedium(driver).getText());	//Reading the Medium value of Labour compliance
		CFOcountPOM.clickLabourMedium(driver).click();					//Clicking on High bar of Labour  
		
		Thread.sleep(500);
		int ClosedTimely = Integer.parseInt(CFOcountPOM.clickBarClosedTimely(driver).getText());			//reading Closed Timely count.
		int ClosedDelayed = Integer.parseInt(CFOcountPOM.clickBarClosedDelayed(driver).getText());	//reading Closed Delayed count.
		int NotCompleted = Integer.parseInt(CFOcountPOM.clickBarNotCompleted(driver).getText());	//reading Not Completed count.
		int NotApplicable = Integer.parseInt(CFOcountPOM.clickBarNotApplicable(driver).getText());	//reading Not Applicable count.
		
		int total = ClosedTimely + ClosedDelayed + NotCompleted + NotApplicable;				//Calculating the values to match with High value of Labour.
		
		if(labourMedium == total)
		{
			test.log(LogStatus.PASS, "'Labour - High' Compliance Count matches to sum of all types of compliances.");
			test.log(LogStatus.INFO, "Total 'Labour - High' Compliances : "+total);
		}
		else
		{
			test.log(LogStatus.FAIL, "'Labour - High' Compliance Count doesn't matches to sum of all types of compliances.");
			test.log(LogStatus.INFO, "Total 'Labour - High' Compliances : "+total+" | Total Sum : "+labourMedium);
		}
		
		if(labourMedium > 0)
		{
			if(ClosedTimely > 0)
			{
				CFOcountPOM.BarGraphCount(driver, test, "Closed Timely", ClosedTimely);
			}
			else
			{
				test.log(LogStatus.SKIP, "'Medium - Closed Timely' Count = "+ClosedTimely);
			}
			
			if(ClosedDelayed > 0)
			{
				CFOcountPOM.BarGraphCount(driver, test, "Closed Delayed", ClosedDelayed);
			}
			else
			{
				test.log(LogStatus.SKIP, "'Medium - Closed Delayed' Count = "+ClosedDelayed);
			}
			if(NotCompleted > 0)
			{
				CFOcountPOM.BarGraphCount(driver, test, "Not Completed", NotCompleted);
			}
			else
			{
				test.log(LogStatus.SKIP, "'Medium - Not Completed' Count = "+NotCompleted);
			}
			if(NotApplicable > 0)
			{
				CFOcountPOM.BarGraphCount(driver, test, "Not Applicable", NotApplicable);
			}
			else
			{
				test.log(LogStatus.SKIP, "'Medium - Not Applicable' Count = "+NotApplicable);
			}
			
			Thread.sleep(500);
			WebElement element = CFOcountPOM.clickBack(driver);
			Actions actions = new Actions(driver);
			actions.moveToElement(element).click().perform();				//Clicking on Back button of Bar Graph.
		}
		else
		{
			test.log(LogStatus.SKIP, "'Labour - Medium' Count = "+labourMedium);
			
			Thread.sleep(500);
			WebElement element = CFOcountPOM.clickBack(driver);
			Actions actions = new Actions(driver);
			actions.moveToElement(element).click().perform();				//Clicking on Back button of Bar Graph.
		}
		extent.endTest(test);
		extent.flush();
	}
	
	@Test(priority = 13)
	void BargraphLabourLowStatutory() throws InterruptedException
	{
		test = extent.startTest("Bar Graph - 'Labour' Count Verification with 'Low' risk");
		test.log(LogStatus.INFO, "Test Initiated");
		
		Thread.sleep(1000);
		int labourLow = Integer.parseInt(CFOcountPOM.clickLabourLow(driver).getText());	//Reading the Medium value of Labour compliance
		CFOcountPOM.clickLabourLow(driver).click();					//Clicking on High bar of Labour  
		
		Thread.sleep(500);
		int ClosedTimely = Integer.parseInt(CFOcountPOM.clickBarClosedTimely(driver).getText());			//reading Closed Timely count.
		int ClosedDelayed = Integer.parseInt(CFOcountPOM.clickBarClosedDelayed(driver).getText());	//reading Closed Delayed count.
		int NotCompleted = Integer.parseInt(CFOcountPOM.clickBarNotCompleted(driver).getText());	//reading Not Completed count.
		int NotApplicable = Integer.parseInt(CFOcountPOM.clickBarNotApplicable(driver).getText());	//reading Not Applicable count.
		
		int total = ClosedTimely + ClosedDelayed + NotCompleted + NotApplicable;				//Calculating the values to match with High value of Labour.
		
		if(labourLow == total)
		{
			test.log(LogStatus.PASS, "'Labour - High' Compliance Count matches to sum of all types of compliances.");
			test.log(LogStatus.INFO, "Total 'Labour - High' Compliances : "+total);
		}
		else
		{
			test.log(LogStatus.FAIL, "'Labour - High' Compliance Count doesn't matches to sum of all types of compliances.");
			test.log(LogStatus.INFO, "Total 'Labour - High' Compliances : "+total+" | Total Sum : "+labourLow);
		}
		
		if(labourLow > 0)
		{
			if(ClosedTimely > 0)
			{
				CFOcountPOM.BarGraphCount(driver, test, "Closed Timely", ClosedTimely);
			}
			else
			{
				test.log(LogStatus.SKIP, "'Low - Closed Timely' Count = "+ClosedTimely);
			}
			
			if(ClosedDelayed > 0)
			{
				CFOcountPOM.BarGraphCount(driver, test, "Closed Delayed", ClosedDelayed);
			}
			else
			{
				test.log(LogStatus.SKIP, "'Low - Closed Delayed' Count = "+ClosedDelayed);
			}
			if(NotCompleted > 0)
			{
				CFOcountPOM.BarGraphCount(driver, test, "Not Completed", NotCompleted);
			}
			else
			{
				test.log(LogStatus.SKIP, "'Low - Not Completed' Count = "+NotCompleted);
			}
			if(NotApplicable > 0)
			{
				CFOcountPOM.BarGraphCount(driver, test, "Not Applicable", NotApplicable);
			}
			else
			{
				test.log(LogStatus.SKIP, "'Low - Not Applicable' Count = "+NotApplicable);
			}
			
			Thread.sleep(500);
			WebElement element = CFOcountPOM.clickBack(driver);
			Actions actions = new Actions(driver);
			actions.moveToElement(element).click().perform();				//Clicking on Back button of Bar Graph.
			
			Thread.sleep(500);
			performer.OverduePOM.clickDashboard(driver).click();			//Clicking on Dashboard
		}
		else
		{
			test.log(LogStatus.SKIP, "'Labour - Low' Count = "+labourLow);
			
			Thread.sleep(500);
			WebElement element = CFOcountPOM.clickBack(driver);
			Actions actions = new Actions(driver);
			actions.moveToElement(element).click().perform();				//Clicking on Back button of Bar Graph.
			
			Thread.sleep(500);
			performer.OverduePOM.clickDashboard(driver).click();			//Clicking on Dashboard
		}
		extent.endTest(test);
		extent.flush();
	}*/
	
//	@Test(priority = 14)
	void RiskSummaryCriticalStatutory() throws InterruptedException
	{
		Thread.sleep(500);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,1000)");					//Scrolling down window by 1000 px.
		
		test = extent.startTest("Risk Summary - 'Critical' Count Verification");
		test.log(LogStatus.INFO, "Test Initiated");
		
		Thread.sleep(500);
		String string_Categories =CFOcountPOM.clickRiskCriticalClosedDelayed(driver).getText();		//Storing old value of Statutory overdue.
		int	RiskCritical_NotCompleted = Integer.parseInt(string_Categories);
	//	int RiskCritical_NotCompleted = Integer.parseInt(CFOcountPOM.clickRiskCriticalNotCompleted(driver).getText());	//Reading the High Risk value of Not Completed compliance
		if(RiskCritical_NotCompleted > 0)
		{
			CFOcountPOM.clickRiskCriticalClosedDelayed(driver).click();			//Clicking on Not Completed compliances bar of High risk.  
			
			CFOcountPOM.RiskGraphCount(driver, test, "Critical - Critical_NotCompleted", RiskCritical_NotCompleted, "Statutory");
		}
		else
		{
			test.log(LogStatus.SKIP, "'Critical -  Critical_NotCompleted' Count = "+RiskCritical_NotCompleted);
		}
		
		Thread.sleep(500);
		int RiskCritical_ClosedDelayed = Integer.parseInt(CFOcountPOM.clickRiskCriticalClosedDelayed(driver).getText());	//Reading the High Risk value of Not Completed compliance
		if(RiskCritical_ClosedDelayed > 0)
		{
			CFOcountPOM.clickRiskCriticalClosedDelayed(driver).click();			//Clicking on Not Completed compliances bar of High risk.  
			
			CFOcountPOM.RiskGraphCount(driver, test, "Critical - Closed Delayed", RiskCritical_ClosedDelayed, "Statutory");
		}
		else
		{
			test.log(LogStatus.SKIP, "'Critical - Closed Delayed' Count = "+RiskCritical_ClosedDelayed);
		}
		
		Thread.sleep(500);
		int RiskCritical_ClosedTimely = Integer.parseInt(CFOcountPOM.clickRiskCriticalClosedTimely(driver).getText());	//Reading the High Risk value of Not Completed compliance
		if(RiskCritical_ClosedTimely > 0)
		{
			CFOcountPOM.clickRiskCriticalClosedTimely(driver).click();			//Clicking on Not Completed compliances bar of High risk.  
			
			CFOcountPOM.RiskGraphCount(driver, test, "Critical - Closed Timely", RiskCritical_ClosedTimely, "Statutory");
		}
		else
		{
			test.log(LogStatus.SKIP, "'Critical - Closed Timely' Count = "+RiskCritical_ClosedTimely);
		}
		
		CFOcountPOM.clickRiskCriticalClosedDelayed(driver).click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		Actions action = new Actions(driver);
		
		Thread.sleep(500);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("showdetails"));	//Switching to iFrame.
		Thread.sleep(3000);
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy((By.xpath("//*[@id='grid']/div[3]/table"))));
		Thread.sleep(3000);
		js.executeScript("window.scrollBy(0,1000)");
		js.executeScript("window.scrollBy(0,500)");
		Thread.sleep(1000);
		 CFOcountPOM.clickExportImage(driver).click();
			Thread.sleep(4000);
			test.log(LogStatus.PASS, "Excel file Export Successfully");
			Thread.sleep(3000);
			 By locator = By.xpath("//*[@id='grid']/div[3]/table/tbody/tr[1]/td[16]/a[3]");
				
				wait.until(ExpectedConditions.presenceOfElementLocated(locator));
				Thread.sleep(4000);
				// retrieving "foo-button" HTML element
				WebElement ViewButton = driver.findElement(locator);	
				Thread.sleep(3000);
			JavascriptExecutor jse=(JavascriptExecutor)driver;
			jse.executeScript("arguments[0].click();", ViewButton);
				Thread.sleep(4000);
				test.log(LogStatus.INFO, "overView success");
				CFOcountPOM.closeDocument(driver).click();
				Thread.sleep(3000);
				driver.switchTo().parentFrame();
				Thread.sleep(4000);
			CFOcountPOM.closeCategories(driver).click();
		extent.endTest(test);
		extent.flush();
	}
	
//	@Test(priority = 15)
	void RiskSummaryHighStatutory() throws InterruptedException
	{		
		test = extent.startTest("Risk Summary - 'High' Count Verification");
		test.log(LogStatus.INFO, "Test Initiated");
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,1000)");	
		Thread.sleep(500);
		int RiskHigh_NotCompleted = Integer.parseInt(CFOcountPOM.clickRiskHighNotCompleted(driver).getText());	//Reading the High Risk value of Not Completed compliance
		if(RiskHigh_NotCompleted > 0)
		{
			Thread.sleep(500);
			CFOcountPOM.clickRiskHighNotCompleted(driver).click();			//Clicking on Not Completed compliances bar of High risk.  
			
			CFOcountPOM.RiskGraphCount(driver, test, "High - Not Completed", RiskHigh_NotCompleted, "Statutory");
		}
		else
		{
			test.log(LogStatus.SKIP, "'High - Not Completed' Count = "+RiskHigh_NotCompleted);
		}
		
		Thread.sleep(500);
		int RiskHigh_ClosedDelayed = Integer.parseInt(CFOcountPOM.clickRiskHighClosedDelayed(driver).getText());	//Reading the High Risk value of Not Completed compliance
		if(RiskHigh_ClosedDelayed > 0)
		{
			Thread.sleep(500);
			CFOcountPOM.clickRiskHighClosedDelayed(driver).click();			//Clicking on Not Completed compliances bar of High risk.  
			
			CFOcountPOM.RiskGraphCount(driver, test, "High - Closed Delayed", RiskHigh_ClosedDelayed, "Statutory");
		}
		else
		{
			test.log(LogStatus.SKIP, "'High - Closed Delayed' Count = "+RiskHigh_ClosedDelayed);
		}
		
		Thread.sleep(500);
		int RiskHigh_ClosedTimely = Integer.parseInt(CFOcountPOM.clickRiskHighClosedTimely(driver).getText());	//Reading the High Risk value of Not Completed compliance
		if(RiskHigh_ClosedTimely > 0)
		{
			Thread.sleep(500);
			CFOcountPOM.clickRiskHighClosedTimely(driver).click();			//Clicking on Not Completed compliances bar of High risk.  
			
			CFOcountPOM.RiskGraphCount(driver, test, "High - Closed Timely", RiskHigh_ClosedTimely, "Statutory");
		}
		else
		{
			test.log(LogStatus.SKIP, "'High - Closed Timely' Count = "+RiskHigh_ClosedTimely);
		}
		extent.endTest(test);
		extent.flush();
	}
/*	
	@Test(priority = 16)
	void RiskSummaryMediumStatutory() throws InterruptedException
	{
		test = extent.startTest("Risk Summary - 'Medium' Count Verification");
		test.log(LogStatus.INFO, "Test Initiated");
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,2000)");
		Thread.sleep(500);
		int RiskMedium_NotCompleted = Integer.parseInt(CFOcountPOM.clickRiskMediumNotCompleted(driver).getText());	//Reading the High Risk value of Not Completed compliance
		if(RiskMedium_NotCompleted > 0)
		{
			Thread.sleep(500);
			CFOcountPOM.clickRiskMediumNotCompleted(driver).click();			//Clicking on Not Completed compliances bar of High risk.  
			
			CFOcountPOM.RiskGraphCount(driver, test, "Medium - Not Completed", RiskMedium_NotCompleted, "Statutory");
		}
		else
		{
			test.log(LogStatus.SKIP, "'Medium - Not Completed' Count = "+RiskMedium_NotCompleted);
		}
		
		Thread.sleep(500);
		int RiskMedium_ClosedDelayed = Integer.parseInt(CFOcountPOM.clickRiskMediumClosedDelayed(driver).getText());	//Reading the High Risk value of Not Completed compliance
		if(RiskMedium_ClosedDelayed > 0)
		{
			Thread.sleep(500);
			CFOcountPOM.clickRiskMediumClosedDelayed(driver).click();			//Clicking on Not Completed compliances bar of High risk.  
			
			CFOcountPOM.RiskGraphCount(driver, test, "Medium - Closed Delayed", RiskMedium_ClosedDelayed, "Statutory");
		}
		else
		{
			test.log(LogStatus.SKIP, "'Medium - Closed Delayed' Count = "+RiskMedium_ClosedDelayed);
		}
		
		Thread.sleep(500);
		int RiskMedium_ClosedTimely = Integer.parseInt(CFOcountPOM.clickRiskMediumClosedTimely(driver).getText());	//Reading the High Risk value of Not Completed compliance
		if(RiskMedium_ClosedTimely > 0)
		{
			Thread.sleep(500);
			CFOcountPOM.clickRiskMediumClosedTimely(driver).click();			//Clicking on Not Completed compliances bar of High risk.  
			
			CFOcountPOM.RiskGraphCount(driver, test, "Medium - Closed Timely", RiskMedium_ClosedTimely, "Statutory");
		}
		else
		{
			test.log(LogStatus.SKIP, "'Medium - Closed Timely' Count = "+RiskMedium_ClosedTimely);
		}
		extent.endTest(test);
		extent.flush();
	}
	
/*	@Test(priority = 17)
	void RiskSummaryLowStatutory() throws InterruptedException
	{		
		test = extent.startTest("Risk Summary - 'Low' Count Verification");
		test.log(LogStatus.INFO, "Test Initiated");
		
		Thread.sleep(500);
		int RiskLow_NotCompleted = Integer.parseInt(CFOcountPOM.clickRiskLowNotCompleted(driver).getText());	//Reading the High Risk value of Not Completed compliance
		if(RiskLow_NotCompleted > 0)
		{
			Thread.sleep(500);
			CFOcountPOM.clickRiskLowNotCompleted(driver).click();			//Clicking on Not Completed compliances bar of High risk.  
			
			CFOcountPOM.RiskGraphCount(driver, test, "Low - Not Completed", RiskLow_NotCompleted, "Statutory");
		}
		else
		{
			test.log(LogStatus.SKIP, "'Low - Not Completed' Count = "+RiskLow_NotCompleted);
		}
		
		Thread.sleep(500);
		int RiskLow_ClosedDelayed = Integer.parseInt(CFOcountPOM.clickRiskLowClosedDelayed(driver).getText());	//Reading the High Risk value of Not Completed compliance
		if(RiskLow_ClosedDelayed > 0)
		{
			Thread.sleep(500);
			CFOcountPOM.clickRiskLowClosedDelayed(driver).click();			//Clicking on Not Completed compliances bar of High risk.  
			
			CFOcountPOM.RiskGraphCount(driver, test, "Low - Closed Delayed", RiskLow_ClosedDelayed, "Statutory");
		}
		else
		{
			test.log(LogStatus.SKIP, "'Low - Closed Delayed' Count = "+RiskLow_ClosedDelayed);
		}
		
		Thread.sleep(500);
		int RiskLow_ClosedTimely = Integer.parseInt(CFOcountPOM.clickRiskLowClosedTimely(driver).getText());	//Reading the High Risk value of Not Completed compliance
		if(RiskLow_ClosedTimely > 0)
		{
			Thread.sleep(500);
			CFOcountPOM.clickRiskLowClosedTimely(driver).click();			//Clicking on Not Completed compliances bar of High risk.  
			
			CFOcountPOM.RiskGraphCount(driver, test, "Low - Closed Timely", RiskLow_ClosedTimely, "Statutory");
		}
		else
		{
			test.log(LogStatus.SKIP, "'Low - Closed Timely' Count = "+RiskLow_ClosedTimely);
		}
		
		Thread.sleep(500);
		performer.OverduePOM.clickDashboard(driver).click();			//Clicking on Dashboard
		
		extent.endTest(test);
		extent.flush();
	}
	
	@Test(priority = 18)
	void DepartmentSummaryHumanResourceStatutory() throws InterruptedException
	{
		Thread.sleep(500);		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,2000)");					//Scrolling down window by 1500 px.
		
		test = extent.startTest("Department Summary - 'Human Resource' Count Verification");
		test.log(LogStatus.INFO, "Test Initiated");
		
		Thread.sleep(500);
		String ClosedDelayed = CFOcountPOM.clickHumanClosedDelayed(driver).getText();	//Reading the Closed Delayed value of Human Resource
		ClosedDelayed = ClosedDelayed.replaceAll(" ","");								//Removing all white spaces from string. 
		int Closed_Delayed = Integer.parseInt(ClosedDelayed);						
		if(Closed_Delayed > 0)
		{
			CFOcountPOM.clickHumanClosedDelayed(driver).click();
			CFOcountPOM.RiskGraphCount(driver, test, "Closed Delayed", Closed_Delayed, "Statutory");
		}
		else
		{
			test.log(LogStatus.SKIP, "Closed Delayed Complaince Count = "+ Closed_Delayed + ".");
		}
		
		//-----------------------------------------------------
		
		Thread.sleep(500);
		String ClosedTimely = CFOcountPOM.clickHumanClosedTimely(driver).getText();		//Reading the Closed Timely value of Human Resource
		ClosedTimely = ClosedTimely.replaceAll(" ","");									//Removing all white spaces from string. 
		int Closed_Timely = Integer.parseInt(ClosedTimely);						
		if(Closed_Timely > 0)
		{
			CFOcountPOM.clickHumanClosedTimely(driver).click();
			CFOcountPOM.RiskGraphCount(driver, test, "Closed Timely", Closed_Timely, "Statutory");
		}
		else
		{
			test.log(LogStatus.SKIP, "Closed Timely Complaince Count = "+ Closed_Timely + ".");
		}
		
		//-----------------------------------------------------
		
		Thread.sleep(500);
		String NotCompleted = CFOcountPOM.clickHumanOverdue(driver).getText();			//Reading the Overdue value of Human Resource
		NotCompleted = NotCompleted.replaceAll(" ","");									//Removing all white spaces from string. 
		int Overdue = Integer.parseInt(NotCompleted);						
		if(Overdue > 0)
		{
			CFOcountPOM.clickHumanOverdue(driver).click();
			CFOcountPOM.RiskGraphCount(driver, test, "Overdue", Overdue, "Statutory");
		}
		else
		{
			test.log(LogStatus.SKIP, "Overdue Complaince Count = "+ Overdue + ".");
		}
		
		//-----------------------------------------------------
		
		Thread.sleep(500);
		String PendingReview = CFOcountPOM.clickHumanPendingReview(driver).getText();	//Reading the Pending For Review value of Human Resource
		PendingReview = PendingReview.replaceAll(" ","");								//Removing all white spaces from string. 
		int Pending_Review = Integer.parseInt(PendingReview);						
		if(Pending_Review > 0)
		{
			CFOcountPOM.clickHumanPendingReview(driver).click();
			CFOcountPOM.RiskGraphCount(driver, test, "Pending For Review", Pending_Review, "Statutory");
		}
		else
		{
			test.log(LogStatus.SKIP, "Pending For Review Complaince Count = "+ Pending_Review + ".");
		}
		
		//-----------------------------------------------------
		
		try
		{
			Thread.sleep(500);
			String NotApplicable = CFOcountPOM.clickHumanNotApplicable(driver).getText();	//Reading the Pending For Review value of Human Resource
			NotApplicable = NotApplicable.replaceAll(" ","");								//Removing all white spaces from string. 
			int Not_Applicable = Integer.parseInt(NotApplicable);						
			if(Not_Applicable > 0)
			{
				CFOcountPOM.clickHumanNotApplicable(driver).click();
				CFOcountPOM.RiskGraphCount(driver, test, "Not Applicable", Not_Applicable, "Statutory");
			}
			else
			{
				test.log(LogStatus.SKIP, "Not Applicable Complaince Count = "+ Not_Applicable + ".");
			}
		}
		catch(Exception e)
		{
			
		}
		Thread.sleep(500);
		performer.OverduePOM.clickDashboard(driver).click();			//Clicking on Dashboard
		
		extent.endTest(test);
		extent.flush();
	}
	
	@Test(priority = 19)
	void PenaltySummaryHighStatutory() throws InterruptedException
	{		
		Thread.sleep(500);		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,2525)");					//Scrolling down window by 2100 px.
		
		test = extent.startTest("Penalty Summary Graph - Amount Verification");
		test.log(LogStatus.INFO, "Test Initiated");
		
		Thread.sleep(300);
		CFOcountPOM.clickPenaltyYear(driver).click();
		Select drp = new Select(CFOcountPOM.clickPenaltyYear(driver));
		Thread.sleep(300);
		drp.selectByValue("2020-2021");
		Thread.sleep(300);
		CFOcountPOM.clickPenaltyYear(driver).click();
		
		Thread.sleep(300);
		CFOcountPOM.clickApply3(driver).click();
		
		Thread.sleep(500);
		js.executeScript("window.scrollBy(0,2525)");					//Scrolling down window by 2100 px.
		
		//----------------------------------------------Critical-------------------------------------
		
		Thread.sleep(500);
		String critical = CFOcountPOM.clickPenaltyCritical(driver).getText();	//Reading the High Penalty value of April-Jun
		critical = critical.replaceAll(" ","");									//Removing all white spaces from string. 
		int PenaltyCritical = Integer.parseInt(critical);
		
		if(PenaltyCritical > 0)
		{
			test.log(LogStatus.INFO, "-------- Critical Risk Penalty Count --------");
			CFOcountPOM.clickPenaltyCritical(driver).click();
			CFOcountPOM.CountPenalty(driver, test, PenaltyCritical);
		}
		else
		{
			test.log(LogStatus.SKIP, "'Critical Penalty' value is zero.");
		}
		
		//----------------------------------High-------------------------------
		
		Thread.sleep(500);
		String high = CFOcountPOM.clickPenaltyHigh(driver).getText();	//Reading the High Penalty value of April-Jun
		high = high.replaceAll(" ","");									//Removing all white spaces from string. 
		int PenaltyHigh = Integer.parseInt(high);
		
		if(PenaltyHigh > 0)
		{
			test.log(LogStatus.INFO, "-------- High Risk Penalty Count --------");
			CFOcountPOM.clickPenaltyHigh(driver).click();
			CFOcountPOM.CountPenalty(driver, test, PenaltyHigh);
		}
		else
		{
			test.log(LogStatus.SKIP, "'High Penalty' value is zero.");
		}
		
		//----------------------------------Medium-------------------------------
		
		Thread.sleep(500);
		String medium = CFOcountPOM.clickPenaltyMedium(driver).getText();	//Reading the High Penalty value of April-Jun
		medium = medium.replaceAll(" ","");									//Removing all white spaces from string. 
		int PenaltyMedium = Integer.parseInt(medium);
		
		if(PenaltyMedium > 0)
		{
			test.log(LogStatus.INFO, "-------- Medium Risk Penalty Count --------");
			CFOcountPOM.clickPenaltyMedium(driver).click();
			CFOcountPOM.CountPenalty(driver, test, PenaltyMedium);
		}
		else
		{
			test.log(LogStatus.SKIP, "'Medium Penalty' value is zero.");
		}
		
		//----------------------------------Low-------------------------------
		
		Thread.sleep(500);
		String low = CFOcountPOM.clickPenaltyLow(driver).getText();	//Reading the High Penalty value of April-Jun
		low = low.replaceAll(" ","");									//Removing all white spaces from string. 
		int PenaltyLow = Integer.parseInt(low);
		
		if(PenaltyLow > 0)
		{
			test.log(LogStatus.INFO, "-------- Low Risk Penalty Count --------");
			CFOcountPOM.clickPenaltyLow(driver).click();
			CFOcountPOM.CountPenalty(driver, test, PenaltyLow);
		}
		else
		{
			test.log(LogStatus.SKIP, "'Low Penalty' value is zero.");
		}
		Thread.sleep(500);
		performer.OverduePOM.clickDashboard(driver).click();			//Clicking on Dashboard
		
		extent.endTest(test);
		extent.flush();
	}
	
	@Test(priority = 20)
	void GradingReportStatutory() throws InterruptedException, IOException
	{
		Thread.sleep(500);		
		test = extent.startTest("'Grading Report' Count Verification");
		test.log(LogStatus.INFO, "Test Initiated");
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,3100)");					//Scrolling down window by 2600 px.
		
		Thread.sleep(500);
		elementsList = CFOcountPOM.clickExpandGrading(driver);
		for(int i = 0; i < elementsList.size(); i++)
		{
			Thread.sleep(250);
			elementsList.get(i).click();
		}
		
		try
		{
			Thread.sleep(500);
			CFOcountPOM.clickRedGrading(driver).click();					//Clicking on first row, Second Red box of grading button
			CFOcountPOM.CountGrading(driver, test, "High Risk (Red)");
		}
		catch(Exception e)
		{
			
		}
		
		try
		{
			Thread.sleep(500);
			js.executeScript("window.scrollBy(0,50)");
			CFOcountPOM.clickYellowGrading(driver).click();					//Clicking on first Yellow box of grading button
			CFOcountPOM.CountGrading(driver, test, "Medium Risk (Yellow)");
		}
		catch(Exception e)
		{
			
		}
		
		try
		{
			Thread.sleep(500);
			js.executeScript("window.scrollBy(0,50)");
			CFOcountPOM.clickGreenGrading(driver).click();					//Clicking on first Blue box of grading button
			CFOcountPOM.CountGrading(driver, test, "Low Risk (Green)");
		}
		catch(Exception e)
		{
			
		}
		
		Thread.sleep(500);
		performer.OverduePOM.clickDashboard(driver).click();			//Clicking on Dashboard
		
		extent.endTest(test);
		extent.flush();
	}
	
	int perform() throws InterruptedException
	{
		CFOcountPOM.clickDateCalendar2(driver).click();					//Clicking on first date of the calendar.
		
		WebDriverWait wait = new WebDriverWait(driver, 60);
		Thread.sleep(500);
		progress1(driver);
		
		Thread.sleep(1000);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("calframe"));
		String s1 = CFOcountPOM.readTotalItems1(driver).getText();		//Reading total number of items.
		String[] bits = s1.split(" ");									//Splitting the String
		String itomsCount = bits[bits.length - 2];						//Getting the second last word (total number of items)
		int items = 0;
		if(itomsCount.equalsIgnoreCase("to"))
		{
			items = 0;
		}
		else
			items = Integer.parseInt(itomsCount);
		
		driver.switchTo().parentFrame();
		return items;
	}
	
	@Test(priority = 21)
	void DetailedReport() throws InterruptedException, IOException
	{
		test = extent.startTest("Detailed Report Count Verification");
		test.log(LogStatus.INFO, "Test Initiated");
		
		CFOcountPOM.DetailedReport(test, driver, "cfo");
		
		extent.endTest(test);
		extent.flush();
	}
	
	@Test(priority = 22)
	void AssignmentReport() throws InterruptedException, IOException
	{
		test = extent.startTest("Assignment Report verification");
		test.log(LogStatus.INFO, "Test Initiated");
		
		CFOcountPOM.AssignmentReport(test, driver);
		
		extent.endTest(test);
		extent.flush();
	}
	
	@Test(priority = 23)
	void UsageReport() throws InterruptedException
	{
		test = extent.startTest("Usage Report verification");
		test.log(LogStatus.INFO, "Test Initiated");
		
		Thread.sleep(1000);
		CFOcountPOM.clickReports(driver).click();				//Clicking on 'My Reports'
		
		Thread.sleep(500);
		CFOcountPOM.clickUsageReport(driver).click();			//Clicking on 'Usage Reports'
		
		Thread.sleep(1000);
		CFOcountPOM.clickStartDate(driver).click();				//CLicking on Start Date input box
		performer.OverduePOM.selectDate(driver).click();		//Method to click on date at second row and fourth column
		
		Thread.sleep(500);
		CFOcountPOM.clickEndDate(driver).click();				//CLicking on Start Date input box
		performer.OverduePOM.selectDate(driver).click();		//Method to click on date at second row and fourth column
		
		File dir = new File("//home//ashitosh-avantis//Downloads//");
		File[] dirContents = dir.listFiles();					//Counting number of files in directory before download
		
		Thread.sleep(500);
		CFOcountPOM.clickExport(driver).click();				//CLicking on 'Export to Excel' button
		
		Thread.sleep(3000);
		File dir1 = new File("//home//ashitosh-avantis//Downloads//");
		File[] dirContents1 = dir1.listFiles();					//Counting number of files in directory after download
		
		if(dirContents.length < dirContents1.length)
		{
			test.log(LogStatus.PASS, "File downloaded successfully.");	
		}
		else
		{
			test.log(LogStatus.FAIL, "File does not downloaded.");
		}
		
		Thread.sleep(500);
		performer.OverduePOM.clickDashboard(driver).click();
		
		extent.endTest(test);
		extent.flush();
	}
	
	@Test(priority = 24)
	void AuditReport() throws InterruptedException, IOException
	{
		test = extent.startTest("Audit Report verification");
		test.log(LogStatus.INFO, "Test Initiated");
		
		WebDriverWait wait = new WebDriverWait(driver, 60);
		Thread.sleep(500);
		CFOcountPOM.clickReports(driver).click();				//Clicking on 'My Reports'
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,1000)");
		
		Thread.sleep(500);
		CFOcountPOM.clickAuditReport(driver).click();			//Clicking on 'Assignment Report'
		
		Thread.sleep(500);
		wait.until(ExpectedConditions.visibilityOf(CFOcountPOM.clickShowDropDown(driver)));
		
		//----------------------- All Vendors------------------------
		
		Thread.sleep(500);
		wait.until(ExpectedConditions.elementToBeClickable(CFOcountPOM.clickAllDropDown(driver)));
		
		String vendors = "All Vendors";
		audit(driver, vendors);
		
		//------------------------- Open Venders ---------------------------------
		
		Thread.sleep(1000);
		CFOcountPOM.clickAllDropDown(driver).click();			//Clicking on 'Vendor Types' dropdown.
		Select drp2 = new Select(CFOcountPOM.clickAllDropDown(driver));
		drp2.selectByIndex(1);									//Selecting second value.
		
		Thread.sleep(1000);
		CFOcountPOM.clickApply1(driver).click();				//Clicking on Apply button.
		
		Thread.sleep(1500);
		String vendors1 = "Open Vendors";
		audit(driver, vendors1);
		
		Thread.sleep(500);
		performer.OverduePOM.clickDashboard(driver).click();
		
		extent.endTest(test);
		extent.flush();
	}
	
	void audit(WebDriver driver, String vendors) throws InterruptedException, IOException
	{		
		File dir = new File("//home//ashitosh-avantis//Downloads//");
		File[] dirContents = dir.listFiles();					//Counting number of files in directory before download
		
		Thread.sleep(500);
		Actions action = new Actions(driver);
		action.moveToElement(CFOcountPOM.clickExportReport(driver)).click().build().perform();	//Exporting (Downloading) file
		
		Thread.sleep(3000);
		File dir1 = new File("//home//ashitosh-avantis//Downloads//");
		File[] allFilesNew = dir1.listFiles();					//Counting number of files in directory after download
		
		if(dirContents.length < allFilesNew.length)
		{
			test.log(LogStatus.INFO, "File downloaded successfully.");
			
			File lastModifiedFile = allFilesNew[0];			//Storing any 0th index file in 'lastModifiedFile' file name.
		    for (int i = 1; i < allFilesNew.length; i++) 	//For loop till the number of files in directory.
		    {
		       if (lastModifiedFile.lastModified() < allFilesNew[i].lastModified()) 	//If allFilesNew[i] file is having large/latest time time of update then latest modified file be allFilesNew[i] file.
		       {
		           lastModifiedFile = allFilesNew[i];
		       }
		    }
			
			Thread.sleep(3000);		
			fis = new FileInputStream(lastModifiedFile);
			workbook = new XSSFWorkbook(fis);
			sheet = workbook.getSheetAt(0);					//Retrieving first sheet of Workbook
			
			elementsList1 = CFOcountPOM.getVendorNames(driver);
			int n = elementsList1.size();
			int flag = 0;
			int noVendors = n - 5;							//Counting displayed vendors to match with sheet count.
			for(int i = 5; i < n; i++)						//i = 5 as first five elements are other than required.
			{
				String vendor = elementsList1.get(i).getText();
				int no = sheet.getLastRowNum();
				for(int j = 3; j < no; j++)					//j = 3 as first three rows of sheet are of no use
				{
					Row r = sheet.getRow(j);				//Here j refers to row no. (Row no varies)
					Cell c1 = r.getCell(1);					//Getting vendor name from column 1 (Column no is static)
					String vendor1 = c1.getStringCellValue();	//Reading vendor name from cell;
					if(vendor.equalsIgnoreCase(vendor1))
					{
						flag = flag + 1;
						break;
					}
				}			
			}
			
			if (flag == noVendors)
			{
				test.log(LogStatus.PASS, vendors + " - Displayed vendor names are present in sheet");	
			}
			else
			{
				test.log(LogStatus.FAIL, vendors + " - Displayed vendor names are not present in sheet");
			}
		}
		else
		{
			test.log(LogStatus.FAIL, "File does not downloaded.");
		}
	}
	*/
	@AfterTest
	void Closing() throws InterruptedException
	{
		//Thread.sleep(2000);
		//driver.close();
	}
}
