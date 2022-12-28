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
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import cfo.CFOcountPOM;
import performer.OverduePOM;

public class ApprovalcountInternal {
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
	
	public static String link = "Shivraj";			//Check link in excel sheet first.
	
	public static XSSFSheet ReadExcel() throws IOException
	{
	//	String workingDir = System.getProperty("webdriver.chrome.driver","C:/March2022/PerformerPom/Driver/chromedriver.exe");
		fis = new FileInputStream("C:/March2022/PerformerPom/TestData/ComplianceSheet.xlsx");
		workbook = new XSSFWorkbook(fis);
		sheet = workbook.getSheetAt(6);					//Retrieving third sheet of Workbook
		return sheet;
	}
	
	@BeforeTest
	void setBrowser() throws InterruptedException, IOException
	{
	//	String workingDir = System.getProperty("webdriver.chrome.driver","C:/March2022/PerformerPom/Driver/chromedriver.exe");
		extent = new com.relevantcodes.extentreports.ExtentReports("C:/March2022/PerformerPom/Reports/CFOResultsInternal.html",true);
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
		test = extent.startTest("Loging In - Approval (Internal)");
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
	//	wait.until(ExpectedConditions.visibilityOf(ApprovalcountPOM.clickManagement(driver)));

	//	ApprovalcountPOM.clickManagement(driver).click();
	//	Thread.sleep(5000);
		Thread.sleep(700);
		Select drp = new Select(CFOcountPOM.selectInternal(driver));
		drp.selectByIndex(1);
		
		Thread.sleep(2000);
		CFOcountPOM.clickApply(driver).click();
		Thread.sleep(2000);
		test.log(LogStatus.PASS, "Test Passed.");
		extent.endTest(test);
		extent.flush();
	}
	
	public static void progress1(WebDriver driver)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		try
		{
			Thread.sleep(500);
			wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath("//*[@id='imgcaldate']"))));
		}
		catch(Exception e)
		{
			
		}
	}
	
	//@Test(priority = 2)
	void FilterWiseCategoriesCountMatch() throws InterruptedException
	{
		test = extent.startTest(" Count Match Filter Wise by Clicking on 'Categories' - Compliances ");
		test.log(LogStatus.INFO, "Test Initiated");
		
		Thread.sleep(2000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		
		CFOcountPOM.clickCategories(driver).click();
		Thread.sleep(500);
		
		litigationPerformer.MethodsPOM.progress(driver);
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(70));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("showdetails"));	//Wait until frame get visible and switch to it.
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='grid']/div[3]/table/tbody/tr[3]/td[4]/div")));
		Thread.sleep(3000);
		CFOcountPOM.clickLocation(driver).click();
		Thread.sleep(1000);
		CFOcountPOM.clickBPVT(driver).click();
		Thread.sleep(3000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='grid']/div[3]/table")));
	
		Thread.sleep(8000);
		elementsList1 = CFOcountPOM.readCompliancesList(driver);
	int	value = Integer.parseInt(elementsList1.get(1).getText());
	Thread.sleep(500);
		elementsList1.get(1).click();
		Thread.sleep(4000);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("APIOverView"));	//Wait until frame get visible and switch to it.
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class='k-grid-content k-auto-scrollable']")));
		Thread.sleep(4000);
		js.executeScript("window.scrollBy(0,3000)");				//Scrolling down window by 2000 px.
		Thread.sleep(1000);
		CFOcountPOM.readTotalItemsD(driver).click();
		
		Thread.sleep(1000);
		String item = CFOcountPOM.readTotalItemsD(driver).getText();
		String[] bits = item.split(" ");								//Splitting the String
		String compliancesCount1 = bits[bits.length - 2];				//Getting the second last word (total number of users)
		
	int	count1 = Integer.parseInt(compliancesCount1);
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
		//Thread.sleep(2000);
	
	//	Thread.sleep(3000);
		js.executeScript("window.scrollBy(2000,0)");     //Scrolling UP window by 2000 px.
		Thread.sleep(3000);
		driver.switchTo().defaultContent();
			
		Thread.sleep(4000);
		CFOcountPOM.closeCategories(driver).click();
		Thread.sleep(2000);
		extent.endTest(test);
		extent.flush();
	}
	

	
//	@Test(priority = 2)
	void clickCategoriesInternal() throws InterruptedException
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
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='grid']")));
		Thread.sleep(3000);
		js.executeScript("window.scrollBy(0,500)");
		Thread.sleep(3000);
		CFOcountPOM.readTotalItemsD(driver).click();					//Clicking on total items count
		Thread.sleep(500);
		String item = CFOcountPOM.readTotalItemsD(driver).getText();	//Reading total items String value
		String[] bits = item.split(" ");								//Splitting the String
		String compliancesCount = bits[bits.length - 2];				//Getting the second last word (total number of users)
		int CatcountGrid = Integer.parseInt(compliancesCount);
		WebElement com=driver.findElement(By.xpath("//*[@id='grid']/div[3]/table/tbody/tr/td[4]/div"));
	
	String comp_cat=	com.getText();
	int	CompCountCat = Integer.parseInt(comp_cat);
	com.click();
		Thread.sleep(3000);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("APIOverView"));
		Thread.sleep(1000);
		CFOcountPOM.clickExportImage(driver).click();                    //export excel
		Thread.sleep(5000);
		test.log(LogStatus.PASS, "Excel file Export Successfully");	
		
		Thread.sleep(500);
		litigationPerformer.MethodsPOM.progress(driver);
		
	//	wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("APIOverView"));	//Wait until frame get visible and switch to it.
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class='k-grid-content k-auto-scrollable']")));
		Thread.sleep(4000);
		js.executeScript("window.scrollBy(0,3000)");				//Scrolling down window by 2000 px.
		Thread.sleep(1000);
		CFOcountPOM.readTotalItemsD(driver).click();
		
		Thread.sleep(1000);
		String item1 = CFOcountPOM.readTotalItemsD(driver).getText();
		String[] bits1 = item.split(" ");								//Splitting the String
		String compliancesCount1 = bits[bits.length - 2];				//Getting the second last word (total number of users)
		
	int	count = Integer.parseInt(compliancesCount1);
		js.executeScript("window.scrollBy(0,3000)");
		if(CompCountCat == count)
		{
			test.log(LogStatus.PASS, "Compliances count matches. Clicked value = " + CompCountCat+ ", Grid Records = "+count);
		}
		else
		{
			test.log(LogStatus.FAIL, "Compliances count does not matches. Clicked value = "+CompCountCat+", Grid Records = "+count);
		}
		
		driver.switchTo().parentFrame();								//Switching back to parent frame.
		Thread.sleep(3000);
		CFOcountPOM.closeCategories_Compliances(driver).click();		//Closing the 'Compliances' pup up.
		Thread.sleep(2000);
	
		WebElement User=driver.findElement(By.xpath("//*[@id='grid']/div[3]/table/tbody/tr/td[3]/div"));
		String user_cat=	User.getText();
		int	userCountCat = Integer.parseInt(user_cat);
		User.click();
			Thread.sleep(3000);
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("APIOverView"));
			Thread.sleep(3000);                                             
			CFOcountPOM.clickExportImage(driver).click();                    //export excel
			Thread.sleep(5000);
			test.log(LogStatus.PASS, "Excel file Export Successfully");	
			
			Thread.sleep(500);
			litigationPerformer.MethodsPOM.progress(driver);
			
		//	wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("APIOverView"));	//Wait until frame get visible and switch to it.
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class='k-grid-content k-auto-scrollable']")));
			Thread.sleep(4000);
			js.executeScript("window.scrollBy(0,3000)");				//Scrolling down window by 2000 px.
			Thread.sleep(1000);
			CFOcountPOM.readTotalItemsD(driver).click();
			
			Thread.sleep(1000);
			String item2 = CFOcountPOM.readTotalItemsD(driver).getText();
			String[] bits2 = item.split(" ");								//Splitting the String
			String userCount2 = bits[bits.length - 2];				//Getting the second last word (total number of users)
			
		int	count1 = Integer.parseInt(userCount2);
			js.executeScript("window.scrollBy(0,3000)");
			if(userCountCat == count1)
			{
				test.log(LogStatus.PASS, "Users count matches. Clicked value = " + userCountCat+ ", Grid Records = "+count1);
			}
			else
			{
				test.log(LogStatus.FAIL, "Users count does not matches. Clicked value = "+userCountCat+", Grid Records = "+count1);
			}
			
			driver.switchTo().parentFrame();								//Switching back to parent frame.
			Thread.sleep(3000);
			CFOcountPOM.closeCategories_Compliances(driver).click();		//Closing the 'Compliances' pup up.
			Thread.sleep(2000);
		
			
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
	
//	@Test(priority = 3)
	void ClickCompliancesInternal() throws InterruptedException
	{
		test = extent.startTest("'Complainces' Count by Clicking on 'Compliances'");
		test.log(LogStatus.INFO, "Test Initiated");
		
		Thread.sleep(1500);
		WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait1.until(ExpectedConditions.visibilityOf(CFOcountPOM.uniqueCompliances(driver)));
		int valueCompliances = Integer.parseInt(CFOcountPOM.uniqueCompliances(driver).getText());	//Storing value of 'Compliances' as a String to compare.
		
		//driver.findElement(By.xpath("(//*[@class = 'titleMD'])[4]")).click();
		CFOcountPOM.uniqueCompliances(driver).click();					//Clicking on 'Compliances'.
		
		Thread.sleep(500);
		litigationPerformer.MethodsPOM.progress(driver);
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("showdetails"));	//Wait until frame get visible and switch to it.
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class='k-selectable']")));
		
		Thread.sleep(1000);
		CFOcountPOM.clickExportImage(driver).click();                    //export excel
		Thread.sleep(5000);
		test.log(LogStatus.PASS, "Excel file Export Successfully");	
		
		Thread.sleep(500);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,500)");				//Scrolling down window by 2000 px.
		
		Thread.sleep(1000);
		CFOcountPOM.readTotalItemsD(driver).click();				//Clicking on Total items count to scroll down.
		String getCount = CFOcountPOM.readTotalItemsD(driver).getText();	//Storing 'Compliances' count as string.
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
		
		driver.switchTo().parentFrame();								//Switching back to parent frame. 
		Thread.sleep(500);
		CFOcountPOM.closeCategories(driver).click();					//Closing the 'Compliance' window.
		
		if(valueCompliances == count)									//Comparing dashboard Compliance value with inside Compliance value
		{
			test.log(LogStatus.PASS, "'Compliances' count matches to total records count displayed. Dashboard Value = "+ valueCompliances+ " | Actual count = "+ count);
		}
		else
		{
			test.log(LogStatus.FAIL, "'Compliances' count doesn't matches to total records count displayed. Dashboard Value = "+ valueCompliances+ " } Actual count = "+ count);
		}
		extent.endTest(test);
		extent.flush();
	}
	
//	@Test(priority = 4)
	void clickUsersInternal() throws InterruptedException
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
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("showdetails"));	//Wait until frame get visible and switch to it.
		
		Thread.sleep(1000);
		CFOcountPOM.clickExportImage(driver).click();                    //export excel
		Thread.sleep(5000);
		test.log(LogStatus.PASS, "Excel file Export Successfully");	
		
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,1000)");					//Scrolling down window by 1000 px.
		
		Thread.sleep(3000);
		CFOcountPOM.readTotalItemsD(driver).click();
		
		String getCount = CFOcountPOM.readTotalItemsD(driver).getText();	//Storing no of Items 'Users' count as string.
		String[] bits = getCount.split(" ");							//Splitting the String
		String usersCount = bits[bits.length - 2];						//Getting the second last word (total number of users)
		if(usersCount.equalsIgnoreCase("to"))
		{
			Thread.sleep(2500);
			getCount = CFOcountPOM.readTotalItemsD(driver).getText();
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
	
//	@Test(priority = 5)
	void SummaryofOverdueCompliances() throws InterruptedException
	{
		test = extent.startTest(" Summary of Overdue Compliances Internal");
		test.log(LogStatus.INFO, "Test Initiated");
		
		Thread.sleep(4000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		
		CFOcountPOM.ClickShowAllIn(driver).click();        //Clicking on Show All
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
	
//	@Test(priority = 6)
	void NotCompleted_PieChart() throws InterruptedException
	{
		test = extent.startTest("Pie Chart - 'Not Completed' Count Verification");
		test.log(LogStatus.INFO, "Test Initiated");
		test.log(LogStatus.INFO, "---After selecting all location from 'Entity/Sub-Entity/Location' drop down.");
		
		Thread.sleep(500);
		Actions action = new Actions(driver);
	      JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,500)");						//Scrolling down window by 1000 px.
		
		Thread.sleep(500);
		int NotCompletedValue = Integer.parseInt(CFOcountPOM.clickNotCompletedInternalA(driver).getText());	//Reading value of 'Not Completed'
		CFOcountPOM.clickNotCompletedInternalA(driver).click();									//CLicking on 'Not Completed' count
		
		Thread.sleep(500);
		int critical = Integer.parseInt(CFOcountPOM.readCritical(driver).getText());	//Reading Critical risk count.
		int high = Integer.parseInt(CFOcountPOM.readHigh(driver).getText());			//Reading High risk count.
		int medium = Integer.parseInt(CFOcountPOM.readMedium(driver).getText());		//Reading Medium risk count.
		int low = Integer.parseInt(CFOcountPOM.readLow(driver).getText());				//Reading Low risk count.
		
		int total = critical + high + medium + low;
		
		if(NotCompletedValue == total)
		{
			test.log(LogStatus.PASS, "'Not Completed' Compliance Count matches to sum of all risked compliances.");
			test.log(LogStatus.INFO, "Total 'Not Completed' Compliances : "+total);
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
				ApprovalcountPOM.GraphCountIn(driver, test, "Critical", critical, "Internal");
			}
			else
			{
				test.log(LogStatus.SKIP, "'Critical' Risk Compliance Count = "+critical);
			}
			
			if(high > 0)
			{
				ApprovalcountPOM.GraphCountIn(driver, test, "High", high, "Internal");
			}
			else
			{
				test.log(LogStatus.SKIP, "'High' Risk Compliance Count = "+high);
			}
			
			if(medium > 0)
			{
				ApprovalcountPOM.GraphCountIn(driver, test, "Medium", medium, "Internal");
			}
			else
			{
				test.log(LogStatus.SKIP, "'Medium' Risk Compliance Count = "+medium);
			}
			
			if(low > 0)
			{
				ApprovalcountPOM.GraphCountIn(driver, test, "Low", low, "Internal");
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
			test.log(LogStatus.SKIP, "'Not Completed' Compliance Count = "+NotCompletedValue);
			
			Thread.sleep(500);
			action.moveToElement(CFOcountPOM.clickBack1(driver)).click().build().perform();	//Clicking on Dashboard
		}
		extent.endTest(test);
		extent.flush();
	}
	
	//@Test(priority = 7)
	void Overdue_PieChartInternal() throws InterruptedException
	{
		test = extent.startTest("Pie Chart - 'Overdue' Count Verification");
		test.log(LogStatus.INFO, "Test Initiated");
		test.log(LogStatus.INFO, "---After selecting all location from 'Entity/Sub-Entity/Location' drop down.");
		//driver.navigate().refresh();
		Thread.sleep(500);
		Actions action = new Actions(driver);
	//	JavascriptExecutor js = (JavascriptExecutor) driver;
	//	js.executeScript("window.scrollBy(0,500)");						//Scrolling down window by 1000 px.
		
		Thread.sleep(500);
		int OverdueValue = Integer.parseInt(CFOcountPOM.clickOverdueInternal(driver).getText());	//Reading value of 'Not Completed'
		CFOcountPOM.clickOverdueInternal(driver).click();									//CLicking on 'Not Completed' count
		
		Thread.sleep(500);
		int critical = Integer.parseInt(CFOcountPOM.readCritical(driver).getText());	//Reading Critical risk count.
		int high = Integer.parseInt(CFOcountPOM.readHigh(driver).getText());			//Reading High risk count.
		int medium = Integer.parseInt(CFOcountPOM.readMedium(driver).getText());		//Reading Medium risk count.
		int low = Integer.parseInt(CFOcountPOM.readLow(driver).getText());				//Reading Low risk count.
		
		int total = critical + high + medium + low;
		
		if(OverdueValue == total)
		{
			test.log(LogStatus.PASS, "'Overdue' Compliance Count matches to sum of all risked compliances.");
			test.log(LogStatus.INFO, "Total 'Not Completed' Compliances : "+total);
		}
		else
		{
			test.log(LogStatus.FAIL, "'Overdue' Compliance Count doesn't matches to sum of all risked compliances.");
			test.log(LogStatus.INFO, "Total 'Not Completed' Compliances : "+total+" | Total Sum : "+OverdueValue);
		}
		
		if(OverdueValue > 0)
		{
			if(critical > 0)
			{
				ApprovalcountPOM.GraphCountIn(driver, test, "Critical", critical, "Internal");
			}
			else
			{
				test.log(LogStatus.SKIP, "'Critical' Risk Compliance Count = "+critical);
			}
			
			if(high > 0)
			{
				ApprovalcountPOM.GraphCountIn(driver, test, "High", high, "Internal");
			}
			else
			{
				test.log(LogStatus.SKIP, "'High' Risk Compliance Count = "+high);
			}
			
			if(medium > 0)
			{
				ApprovalcountPOM.GraphCountIn(driver, test, "Medium", medium, "Internal");
			}
			else
			{
				test.log(LogStatus.SKIP, "'Medium' Risk Compliance Count = "+medium);
			}
			
			if(low > 0)
			{
				ApprovalcountPOM.GraphCountIn(driver, test, "Low", low, "Internal");
			}
			else
			{
				test.log(LogStatus.SKIP, "'Low' Risk Compliance Count = "+low);
			}
			
			Thread.sleep(500);
		//	action.moveToElement(CFOcountPOM.clickBack2(driver)).click().build().perform();	 //Clicking on Back button
			performer.OverduePOM.clickDashboard(driver).click();			//Clicking on Dashboard

		}
		else
		{
			test.log(LogStatus.SKIP, "'Overdue' Compliance Count = "+OverdueValue);
			
			Thread.sleep(500);
		//	action.moveToElement(CFOcountPOM.clickBack2(driver)).click().build().perform();	//Clicking on Dashboard
			performer.OverduePOM.clickDashboard(driver).click();			//Clicking on Dashboard

		}
		extent.endTest(test);
		extent.flush();
	}
	
	@Test(priority = 8)
	void PFR_PieChartInternal() throws InterruptedException
	{
		test = extent.startTest("Pie Chart - 'Overdue' Count Verification");
		test.log(LogStatus.INFO, "Test Initiated");
		test.log(LogStatus.INFO, "---After selecting all location from 'Entity/Sub-Entity/Location' drop down.");
		//driver.navigate().refresh();
		Thread.sleep(3000);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
	//	wait.until(ExpectedConditions.visibilityOf(ApprovalcountPOM.clickManagement(driver)));

	/*	ApprovalcountPOM.clickManagement(driver).click();
		Thread.sleep(5000);
		Select drp = new Select(CFOcountPOM.selectInternal(driver));
		drp.selectByIndex(1);
		
		Thread.sleep(2000);
		CFOcountPOM.clickApply(driver).click();
		Thread.sleep(4000);
		Thread.sleep(500);*/
		Actions action = new Actions(driver);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,500)");						//Scrolling down window by 1000 px.
		
		Thread.sleep(500);
		int OverdueValue = Integer.parseInt(CFOcountPOM.clickpendingForReviewIN(driver).getText());	//Reading value of 'Not Completed'
		CFOcountPOM.clickpendingForReviewIN(driver).click();									//CLicking on 'Not Completed' count
		
		Thread.sleep(500);
		int critical = Integer.parseInt(CFOcountPOM.readCritical(driver).getText());	//Reading Critical risk count.
		int high = Integer.parseInt(CFOcountPOM.readHigh(driver).getText());			//Reading High risk count.
		int medium = Integer.parseInt(CFOcountPOM.readMedium(driver).getText());		//Reading Medium risk count.
		int low = Integer.parseInt(CFOcountPOM.readLow(driver).getText());				//Reading Low risk count.
		
		int total = critical + high + medium + low;
		
		if(OverdueValue == total)
		{
			test.log(LogStatus.PASS, "'Overdue' Compliance Count matches to sum of all risked compliances.");
			test.log(LogStatus.INFO, "Total 'Not Completed' Compliances : "+total);
		}
		else
		{
			test.log(LogStatus.FAIL, "'Overdue' Compliance Count doesn't matches to sum of all risked compliances.");
			test.log(LogStatus.INFO, "Total 'Not Completed' Compliances : "+total+" | Total Sum : "+OverdueValue);
		}
		
		if(OverdueValue > 0)
		{
			if(critical > 0)
			{
				ApprovalcountPOM.GraphCountIn1(driver, test, "Critical", critical, "Internal");
			}
			else
			{
				test.log(LogStatus.SKIP, "'Critical' Risk Compliance Count = "+critical);
			}
			
			if(high > 0)
			{
				ApprovalcountPOM.GraphCountIn1(driver, test, "High", high, "Internal");
			}
			else
			{
				test.log(LogStatus.SKIP, "'High' Risk Compliance Count = "+high);
			}
			
			if(medium > 0)
			{
				ApprovalcountPOM.GraphCountIn1(driver, test, "Medium", medium, "Internal");
			}
			else
			{
				test.log(LogStatus.SKIP, "'Medium' Risk Compliance Count = "+medium);
			}
			
			if(low > 0)
			{
				ApprovalcountPOM.GraphCountIn1(driver, test, "Low", low, "Internal");
			}
			else
			{
				test.log(LogStatus.SKIP, "'Low' Risk Compliance Count = "+low);
			}
			
			Thread.sleep(500);
		//	action.moveToElement(CFOcountPOM.clickBack2(driver)).click().build().perform();	 //Clicking on Back button
			performer.OverduePOM.clickDashboard(driver).click();			//Clicking on Dashboard

		}
		else
		{
			test.log(LogStatus.SKIP, "'Overdue' Compliance Count = "+OverdueValue);
			
			Thread.sleep(500);
		//	action.moveToElement(CFOcountPOM.clickBack2(driver)).click().build().perform();	//Clicking on Dashboard
			performer.OverduePOM.clickDashboard(driver).click();			//Clicking on Dashboard

		}
		extent.endTest(test);
		extent.flush();
	}
	
	@Test(priority = 11)
	void Rejected_PieChartInternal() throws InterruptedException
	{
		test = extent.startTest("Pie Chart - 'Rejected' Count Verification");
		test.log(LogStatus.INFO, "Test Initiated");
		test.log(LogStatus.INFO, "---After selecting all location from 'Entity/Sub-Entity/Location' drop down.");
		//driver.navigate().refresh();
		Thread.sleep(500);
		Thread.sleep(2000);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
	//	wait.until(ExpectedConditions.visibilityOf(ApprovalcountPOM.clickManagement(driver)));

	//	ApprovalcountPOM.clickManagement(driver).click();
		Thread.sleep(5000);
		Select drp = new Select(CFOcountPOM.selectInternal(driver));
		drp.selectByIndex(1);
		
		Thread.sleep(1000);
		CFOcountPOM.clickApply(driver).click();
		Thread.sleep(4000);
		Actions action = new Actions(driver);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,500)");						//Scrolling down window by 1000 px.
		
		Thread.sleep(500);
		int OverdueValue = Integer.parseInt(CFOcountPOM.clickRejected(driver).getText());	//Reading value of 'Not Completed'
		CFOcountPOM.clickRejected(driver).click();									//CLicking on 'Not Completed' count
		
		Thread.sleep(500);
		int critical = Integer.parseInt(CFOcountPOM.readCritical(driver).getText());	//Reading Critical risk count.
		int high = Integer.parseInt(CFOcountPOM.readHigh(driver).getText());			//Reading High risk count.
		int medium = Integer.parseInt(CFOcountPOM.readMedium(driver).getText());		//Reading Medium risk count.
		int low = Integer.parseInt(CFOcountPOM.readLow(driver).getText());				//Reading Low risk count.
		
		int total = critical + high + medium + low;
		
		if(OverdueValue == total)
		{
			test.log(LogStatus.PASS, "'Overdue' Compliance Count matches to sum of all risked compliances.");
			test.log(LogStatus.INFO, "Total 'Not Completed' Compliances : "+total);
		}
		else
		{
			test.log(LogStatus.FAIL, "'Overdue' Compliance Count doesn't matches to sum of all risked compliances.");
			test.log(LogStatus.INFO, "Total 'Not Completed' Compliances : "+total+" | Total Sum : "+OverdueValue);
		}
		
		if(OverdueValue > 0)
		{
			if(critical > 0)
			{
				ApprovalcountPOM.GraphCountIn1(driver, test, "Critical", critical, "Internal");
			}
			else
			{
				test.log(LogStatus.SKIP, "'Critical' Risk Compliance Count = "+critical);
			}
			
			if(high > 0)
			{
				ApprovalcountPOM.GraphCountIn1(driver, test, "High", high, "Internal");
			}
			else
			{
				test.log(LogStatus.SKIP, "'High' Risk Compliance Count = "+high);
			}
			
			if(medium > 0)
			{
				ApprovalcountPOM.GraphCountIn1(driver, test, "Medium", medium, "Internal");
			}
			else
			{
				test.log(LogStatus.SKIP, "'Medium' Risk Compliance Count = "+medium);
			}
			
			if(low > 0)
			{
				ApprovalcountPOM.GraphCountIn1(driver, test, "Low", low, "Internal");
			}
			else
			{
				test.log(LogStatus.SKIP, "'Low' Risk Compliance Count = "+low);
			}
			
			Thread.sleep(500);
		//	action.moveToElement(CFOcountPOM.clickBack2(driver)).click().build().perform();	 //Clicking on Back button
		//	performer.OverduePOM.clickDashboard(driver).click();			//Clicking on Dashboard

		}
		else
		{
			test.log(LogStatus.SKIP, "'Overdue' Compliance Count = "+OverdueValue);
			
			Thread.sleep(500);
		//	action.moveToElement(CFOcountPOM.clickBack2(driver)).click().build().perform();	//Clicking on Dashboard
		//	performer.OverduePOM.clickDashboard(driver).click();			//Clicking on Dashboard

		}
		extent.endTest(test);
		extent.flush();
	}
	
	//@Test(priority = 12)
	void DepartmentSummaryInternal() throws InterruptedException
	{
		Thread.sleep(500);		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,1000)");					//Scrolling down window by 1500 px.
	//	js.executeScript("window.scrollBy(0,1500)");
		
		test = extent.startTest("Department Summary - 'Account-Closed Delayed' Count Verification");
		test.log(LogStatus.INFO, "Test Initiated");
		test.log(LogStatus.INFO, "---After selecting all location from 'Entity/Sub-Entity/Location' drop down.");
		
		/*Thread.sleep(500);
		String financeClosedDelayed= CFOcountPOM.clickFinanceClosedDelayedInternal(driver).getText();	//Reading the Closed Delayed value of Human Resource
		financeClosedDelayed = financeClosedDelayed.replaceAll(" ","");								//Removing all white spaces from string. 
		int Finance_CloseDelayed= Integer.parseInt(financeClosedDelayed);						
		if(Finance_CloseDelayed > 0)
		{
			CFOcountPOM.clickFinanceClosedDelayedInternal(driver).click();
			CFOcountPOM.RiskGraphCount(driver, test, "Finance -Closed Delayed ", Finance_CloseDelayed, "Internal");
		}
		else
		{
			test.log(LogStatus.SKIP, "'Finance -Closed Delayed' Complaince Count = "+ Finance_CloseDelayed + ".");
		}
		
		//------------------------------------------------------
		
		Thread.sleep(500);
		String FinaClosedTimely = CFOcountPOM.clickFinanceClosedTimelyInternal(driver).getText();		//Reading the Closed Timely value of Human Resource
		FinaClosedTimely = FinaClosedTimely.replaceAll(" ","");									//Removing all white spaces from string. 
		int Fina_ClosedTimely = Integer.parseInt(FinaClosedTimely);						
		if(Fina_ClosedTimely > 0)
		{
			CFOcountPOM.clickFinanceClosedTimelyInternal(driver).click();
			CFOcountPOM.RiskGraphCount(driver, test, "Finance -Closed Timely", Fina_ClosedTimely, "Internal");
		}
		else
		{
			test.log(LogStatus.SKIP, "'Finance -Closed Timely' Complaince Count = "+ Fina_ClosedTimely + ".");
		}
		*/
		//-----------------------------------------------------
		
		Thread.sleep(500);
	/*	String FinaOverdue = CFOcountPOM.clickAccountOverdueInternal(driver).getText();			//Reading the Overdue value of Human Resource
		FinaOverdue = FinaOverdue.replaceAll(" ","");									//Removing all white spaces from string. 
		int Fina_Overdue = Integer.parseInt(FinaOverdue);						
		if(Fina_Overdue > 0)
		{
			CFOcountPOM.clickAccountOverdueInternal(driver).click();
			ApprovalcountPOM.RiskGraphCount1(driver, test, "Account -Overdue", Fina_Overdue, "Internal");
		}
		else
		{
			test.log(LogStatus.SKIP, "'Account - Overdue' Complaince Count = "+ Fina_Overdue + ".");
		}
		*/
		Thread.sleep(500);
		String FinaPFReview = CFOcountPOM.clickAccountPenFReviewInternal(driver).getText();			//Reading the Overdue value of Human Resource
		FinaPFReview = FinaPFReview.replaceAll(" ","");									//Removing all white spaces from string. 
		int Fina_PFR = Integer.parseInt(FinaPFReview);						
		if(Fina_PFR > 0)
		{
			CFOcountPOM.clickAccountPenFReviewInternal(driver).click();
			ApprovalcountPOM.RiskGraphCount(driver, test, "Account -Pending For Review", Fina_PFR, "Internal");
		}
		else
		{
			test.log(LogStatus.SKIP, "'Account - Pending For Review' Complaince Count = "+ Fina_PFR + ".");
		}
		
		Thread.sleep(3000);
	/*	
		String FinaInprogress = CFOcountPOM.clickFinanceInProgressInternal(driver).getText();			//Reading the Overdue value of Human Resource
		FinaInprogress = FinaInprogress.replaceAll(" ","");									//Removing all white spaces from string. 
		int Fina_InProgress = Integer.parseInt(FinaInprogress);						
		if(Fina_InProgress > 0)
		{
			CFOcountPOM.clickFinanceInProgressInternal(driver).click();
			CFOcountPOM.RiskGraphCount(driver, test, "Finance -In Progress", Fina_InProgress, "Internal");
		}
		else
		{
			test.log(LogStatus.SKIP, "'Finance - In Progress ' Complaince Count = "+ Fina_InProgress + ".");
		}
		*/
		Thread.sleep(3000);
		String FinaRejected = CFOcountPOM.clickAccountRejectedInternal(driver).getText();			//Reading the Overdue value of Human Resource
		FinaRejected = FinaRejected.replaceAll(" ","");									//Removing all white spaces from string. 
		int Fina_Rejected= Integer.parseInt(FinaRejected);						
		if(Fina_Rejected > 0)
		{
			CFOcountPOM.clickAccountRejectedInternal(driver).click();
			ApprovalcountPOM.RiskGraphCount(driver, test, "Finance -Rejected", Fina_Rejected, "Internal");
		}
		else
		{
			test.log(LogStatus.SKIP, "'Finance - Rejected' Complaince Count = "+ Fina_Rejected + ".");
		}
		
	/*	Thread.sleep(3000);
		String FinaNotAppli = CFOcountPOM.clickFinanceNotAppliInternal(driver).getText();			//Reading the Overdue value of Human Resource
		FinaNotAppli = FinaNotAppli.replaceAll(" ","");									//Removing all white spaces from string. 
		int Fina_NotAppli= Integer.parseInt(FinaNotAppli);						
		if(Fina_NotAppli > 0)
		{
			CFOcountPOM.clickFinanceRejectedInternal(driver).click();
			CFOcountPOM.RiskGraphCount(driver, test, "Finance -Not Applicable", Fina_NotAppli, "Internal");
		}
		else
		{
			test.log(LogStatus.SKIP, "'Finance - Not Applicable' Complaince Count = "+ Fina_NotAppli + ".");
		}
		*/
		Thread.sleep(500);
	//	js.executeScript("window.scrollBy(0,-1600)");			//Clicking on Dashboard
	//	performer.OverduePOM.clickDashboard(driver).click();
		extent.endTest(test);
		extent.flush();
	}

	
//	@Test(priority = 13)
	void NotCompleted_PieChartPeriod() throws InterruptedException
	{
		test = extent.startTest("Period-Pie Chart - 'Not Completed' Count Verification");
		test.log(LogStatus.INFO, "Test Initiated");
	//	test.log(LogStatus.INFO, "---After selecting all location from 'Entity/Sub-Entity/Location' drop down.");
		Thread.sleep(500);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
		wait.until(ExpectedConditions.visibilityOf(ApprovalcountPOM.clickManagement(driver)));

		ApprovalcountPOM.clickManagement(driver).click();
		Thread.sleep(5000);
		Select drp = new Select(CFOcountPOM.selectInternal(driver));
		drp.selectByIndex(1);
		
		Thread.sleep(1000);
		CFOcountPOM.clickApply(driver).click();
		Thread.sleep(4000);
		Thread.sleep(500);
		Actions action = new Actions(driver);
	      JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,2000)");						//Scrolling down window by 1000 px.
		Thread.sleep(1000);
	//	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("IFNewPeriodGraphCompliance"));
		Thread.sleep(500);
		int NotCompletedValue = Integer.parseInt(CFOcountPOM.clickNotCompletedInternalA(driver).getText());	//Reading value of 'Not Completed'
		CFOcountPOM.clickNotCompletedInternalA(driver).click();									//CLicking on 'Not Completed' count
		
		Thread.sleep(500);
		int critical = Integer.parseInt(CFOcountPOM.readCritical(driver).getText());	//Reading Critical risk count.
		int high = Integer.parseInt(CFOcountPOM.readHigh(driver).getText());			//Reading High risk count.
		int medium = Integer.parseInt(CFOcountPOM.readMedium(driver).getText());		//Reading Medium risk count.
		int low = Integer.parseInt(CFOcountPOM.readLow(driver).getText());				//Reading Low risk count.
		
		int total = critical + high + medium + low;
		
		if(NotCompletedValue == total)
		{
			test.log(LogStatus.PASS, "'Not Completed' Compliance Count matches to sum of all risked compliances.");
			test.log(LogStatus.INFO, "Total 'Not Completed' Compliances : "+total);
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
				ApprovalcountPOM.GraphCountInPe1(driver, test, "Critical", critical, "Internal");
			}
			else
			{
				test.log(LogStatus.SKIP, "'Critical' Risk Compliance Count = "+critical);
			}
			
			if(high > 0)
			{
				ApprovalcountPOM.GraphCountInPe1(driver, test, "High", high, "Internal");
			}
			else
			{
				test.log(LogStatus.SKIP, "'High' Risk Compliance Count = "+high);
			}
			
			if(medium > 0)
			{
				wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("IFNewPeriodGraphCompliance"));
				Thread.sleep(200);
				ApprovalcountPOM.GraphCountInPe1(driver, test, "Medium", medium, "Internal");
			}
			else
			{
				test.log(LogStatus.SKIP, "'Medium' Risk Compliance Count = "+medium);
			}
			
			if(low > 0)
			{
				wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("IFNewPeriodGraphCompliance"));
				Thread.sleep(200);
				ApprovalcountPOM.GraphCountInPe1(driver, test, "Low", low, "Internal");
			}
			else
			{
				test.log(LogStatus.SKIP, "'Low' Risk Compliance Count = "+low);
			}
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("IFNewPeriodGraphCompliance"));
			Thread.sleep(500);
			action.moveToElement(CFOcountPOM.clickBackPe(driver)).click().build().perform();	 //Clicking on Back button
			driver.switchTo().parentFrame();
		}
		else
		{
			test.log(LogStatus.SKIP, "'Not Completed' Compliance Count = "+NotCompletedValue);
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("IFNewPeriodGraphCompliance"));
			Thread.sleep(500);
			action.moveToElement(CFOcountPOM.clickBackPe(driver)).click().build().perform();	//Clicking on Dashboard
			driver.switchTo().parentFrame();
		}
		extent.endTest(test);
		extent.flush();
	}
	
	//@Test(priority = 14)
	void Overdue_PieChartInternalPeriod() throws InterruptedException
	{
		test = extent.startTest("Period-Pie Chart - 'Overdue' Count Verification");
		test.log(LogStatus.INFO, "Test Initiated");
	//	test.log(LogStatus.INFO, "---After selecting all location from 'Entity/Sub-Entity/Location' drop down.");
		//driver.navigate().refresh();
		Thread.sleep(500);
		Actions action = new Actions(driver);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,300)");						//Scrolling down window by 1000 px.
	//	js.executeScript("window.scrollBy(0,2500)");
		Thread.sleep(1000);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("IFNewPeriodGraphCompliance"));
		Thread.sleep(1000);
		int OverdueValue = Integer.parseInt(CFOcountPOM.clickOverdueInternal(driver).getText());	//Reading value of 'Not Completed'
		CFOcountPOM.clickOverdueInternal(driver).click();									//CLicking on 'Not Completed' count
		
		Thread.sleep(500);
		int critical = Integer.parseInt(CFOcountPOM.readCritical(driver).getText());	//Reading Critical risk count.
		int high = Integer.parseInt(CFOcountPOM.readHigh(driver).getText());			//Reading High risk count.
		int medium = Integer.parseInt(CFOcountPOM.readMedium(driver).getText());		//Reading Medium risk count.
		int low = Integer.parseInt(CFOcountPOM.readLow(driver).getText());				//Reading Low risk count.
		
		int total = critical + high + medium + low;
		
		if(OverdueValue == total)
		{
			test.log(LogStatus.PASS, "'Overdue' Compliance Count matches to sum of all risked compliances.");
			test.log(LogStatus.INFO, "Total 'Not Completed' Compliances : "+total);
		}
		else
		{
			test.log(LogStatus.FAIL, "'Overdue' Compliance Count doesn't matches to sum of all risked compliances.");
			test.log(LogStatus.INFO, "Total 'Not Completed' Compliances : "+total+" | Total Sum : "+OverdueValue);
		}
		
		if(OverdueValue > 0)
		{
			if(critical > 0)
			{
				ApprovalcountPOM.GraphCountInPe1(driver, test, "Critical", critical, "Internal");
			}
			else
			{
				test.log(LogStatus.SKIP, "'Critical' Risk Compliance Count = "+critical);
			}
			
			if(high > 0)
			{
				ApprovalcountPOM.GraphCountInPe1(driver, test, "High", high, "Internal");
			}
			else
			{
				test.log(LogStatus.SKIP, "'High' Risk Compliance Count = "+high);
			}
			
			if(medium > 0)
			{
				wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("IFNewPeriodGraphCompliance"));
				Thread.sleep(500);
				ApprovalcountPOM.GraphCountInPe1(driver, test, "Medium", medium, "Internal");
			}
			else
			{
				
				test.log(LogStatus.SKIP, "'Medium' Risk Compliance Count = "+medium);
			}
			
			if(low > 0)
			{
				wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("IFNewPeriodGraphCompliance"));
				Thread.sleep(500);
				ApprovalcountPOM.GraphCountInPe1(driver, test, "Low", low, "Internal");
			}
			else
			{
				test.log(LogStatus.SKIP, "'Low' Risk Compliance Count = "+low);
			}
			
			Thread.sleep(500);
			//action.moveToElement(CFOcountPOM.clickBack2(driver)).click().build().perform();	 //Clicking on Back button
			performer.OverduePOM.clickDashboard(driver).click();			//Clicking on Dashboard

		}
		else
		{
			test.log(LogStatus.SKIP, "'Overdue' Compliance Count = "+OverdueValue);
			
			Thread.sleep(500);
			action.moveToElement(CFOcountPOM.clickBack2(driver)).click().build().perform();	//Clicking on Dashboard
			performer.OverduePOM.clickDashboard(driver).click();			//Clicking on Dashboard

		}
		extent.endTest(test);
		extent.flush();
	}
	
	@Test(priority = 15)
	void PFR_PieChartInternalPeriod() throws InterruptedException
	{
		test = extent.startTest("Period-Pie Chart - 'PFR' Count Verification");
		test.log(LogStatus.INFO, "Test Initiated");
	//	test.log(LogStatus.INFO, "---After selecting all location from 'Entity/Sub-Entity/Location' drop down.");
		//driver.navigate().refresh();
		
		Thread.sleep(3000);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
	/*	wait.until(ExpectedConditions.visibilityOf(ApprovalcountPOM.clickManagement(driver)));

		ApprovalcountPOM.clickManagement(driver).click();
		Thread.sleep(5000);
		Select drp = new Select(CFOcountPOM.selectInternal(driver));
		drp.selectByIndex(1);
		
		Thread.sleep(2000);
		CFOcountPOM.clickApply(driver).click();
		Thread.sleep(4000);*/
		Thread.sleep(500);
		Actions action = new Actions(driver);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,1500)");						//Scrolling down window by 1000 px.
		Thread.sleep(1000);
	//	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("IFNewPeriodGraphCompliance"));
		Thread.sleep(500);
		int OverdueValue = Integer.parseInt(CFOcountPOM.clickpendingForReviewIN(driver).getText());	//Reading value of 'Not Completed'
		CFOcountPOM.clickpendingForReviewIN(driver).click();									//CLicking on 'Not Completed' count
		
		Thread.sleep(500);
		int critical = Integer.parseInt(CFOcountPOM.readCritical(driver).getText());	//Reading Critical risk count.
		int high = Integer.parseInt(CFOcountPOM.readHigh(driver).getText());			//Reading High risk count.
		int medium = Integer.parseInt(CFOcountPOM.readMedium(driver).getText());		//Reading Medium risk count.
		int low = Integer.parseInt(CFOcountPOM.readLow(driver).getText());				//Reading Low risk count.
		
		int total = critical + high + medium + low;
		
		if(OverdueValue == total)
		{
			test.log(LogStatus.PASS, "'Overdue' Compliance Count matches to sum of all risked compliances.");
			test.log(LogStatus.INFO, "Total 'Not Completed' Compliances : "+total);
		}
		else
		{
			test.log(LogStatus.FAIL, "'Overdue' Compliance Count doesn't matches to sum of all risked compliances.");
			test.log(LogStatus.INFO, "Total 'Not Completed' Compliances : "+total+" | Total Sum : "+OverdueValue);
		}
		
		if(OverdueValue > 0)
		{
			if(critical > 0)
			{
				ApprovalcountPOM.GraphCountInPe(driver, test, "Critical", critical, "Internal");
			}
			else
			{
				test.log(LogStatus.SKIP, "'Critical' Risk Compliance Count = "+critical);
			}
			
			if(high > 0)
			{
				ApprovalcountPOM.GraphCountInPe(driver, test, "High", high, "Internal");
			}
			else
			{
				test.log(LogStatus.SKIP, "'High' Risk Compliance Count = "+high);
			}
			
			if(medium > 0)
			{
				ApprovalcountPOM.GraphCountInPe(driver, test, "Medium", medium, "Internal");
			}
			else
			{
				test.log(LogStatus.SKIP, "'Medium' Risk Compliance Count = "+medium);
			}
			
			if(low > 0)
			{
				ApprovalcountPOM.GraphCountInPe(driver, test, "Low", low, "Internal");
			}
			else
			{
				test.log(LogStatus.SKIP, "'Low' Risk Compliance Count = "+low);
			}
			
			Thread.sleep(500);
		//	action.moveToElement(CFOcountPOM.clickBack2(driver)).click().build().perform();	 //Clicking on Back button
			performer.OverduePOM.clickDashboard(driver).click();			//Clicking on Dashboard

		}
		else
		{
			test.log(LogStatus.SKIP, "'Overdue' Compliance Count = "+OverdueValue);
			
			Thread.sleep(500);
			//action.moveToElement(CFOcountPOM.clickBack2(driver)).click().build().perform();	//Clicking on Dashboard
			performer.OverduePOM.clickDashboard(driver).click();			//Clicking on Dashboard

		}
		extent.endTest(test);
		extent.flush();
	}
	
	@Test(priority = 16)
	void Rejected_PieChartInternalPeriod() throws InterruptedException
	{
		test = extent.startTest("Period-Pie Chart - 'Rejected' Count Verification");
		test.log(LogStatus.INFO, "Test Initiated");
	//	test.log(LogStatus.INFO, "---After selecting all location from 'Entity/Sub-Entity/Location' drop down.");
		//driver.navigate().refresh();
		Thread.sleep(500);
		Thread.sleep(2000);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
	//	wait.until(ExpectedConditions.visibilityOf(ApprovalcountPOM.clickManagement(driver)));

		//ApprovalcountPOM.clickManagement(driver).click();
		Thread.sleep(5000);
		Select drp = new Select(CFOcountPOM.selectInternal(driver));
		drp.selectByIndex(1);
		
		Thread.sleep(1000);
		CFOcountPOM.clickApply(driver).click();
		Thread.sleep(4000);
		Actions action = new Actions(driver);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,2000)");						//Scrolling down window by 1000 px.
		Thread.sleep(1000);
	//	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("IFNewPeriodGraphCompliance"));
		Thread.sleep(500);
		int OverdueValue = Integer.parseInt(CFOcountPOM.clickRejectedPe1(driver).getText());	//Reading value of 'Not Completed'
		CFOcountPOM.clickRejectedPe1(driver).click();									//CLicking on 'Not Completed' count
		
		Thread.sleep(500);
		int critical = Integer.parseInt(CFOcountPOM.readCritical(driver).getText());	//Reading Critical risk count.
		int high = Integer.parseInt(CFOcountPOM.readHigh(driver).getText());			//Reading High risk count.
		int medium = Integer.parseInt(CFOcountPOM.readMedium(driver).getText());		//Reading Medium risk count.
		int low = Integer.parseInt(CFOcountPOM.readLow(driver).getText());				//Reading Low risk count.
		
		int total = critical + high + medium + low;
		
		if(OverdueValue == total)
		{
			test.log(LogStatus.PASS, "'Overdue' Compliance Count matches to sum of all risked compliances.");
			test.log(LogStatus.INFO, "Total 'Not Completed' Compliances : "+total);
		}
		else
		{
			test.log(LogStatus.FAIL, "'Overdue' Compliance Count doesn't matches to sum of all risked compliances.");
			test.log(LogStatus.INFO, "Total 'Not Completed' Compliances : "+total+" | Total Sum : "+OverdueValue);
		}
		
		if(OverdueValue > 0)
		{
			if(critical > 0)
			{
				ApprovalcountPOM.GraphCountInPe(driver, test, "Critical", critical, "Internal");
			}
			else
			{
				test.log(LogStatus.SKIP, "'Critical' Risk Compliance Count = "+critical);
			}
			
			if(high > 0)
			{
				ApprovalcountPOM.GraphCountInPe(driver, test, "High", high, "Internal");
			}
			else
			{
				test.log(LogStatus.SKIP, "'High' Risk Compliance Count = "+high);
			}
			
			if(medium > 0)
			{
				ApprovalcountPOM.GraphCountInPe(driver, test, "Medium", medium, "Internal");
			}
			else
			{
				test.log(LogStatus.SKIP, "'Medium' Risk Compliance Count = "+medium);
			}
			
			if(low > 0)
			{
				ApprovalcountPOM.GraphCountInPe(driver, test, "Low", low, "Internal");
			}
			else
			{
				test.log(LogStatus.SKIP, "'Low' Risk Compliance Count = "+low);
			}
			
			Thread.sleep(500);
		//	action.moveToElement(CFOcountPOM.clickBack2(driver)).click().build().perform();	 //Clicking on Back button
		//	performer.OverduePOM.clickDashboard(driver).click();			//Clicking on Dashboard

		}
		else
		{
			test.log(LogStatus.SKIP, "'Overdue' Compliance Count = "+OverdueValue);
			
			Thread.sleep(500);
			//action.moveToElement(CFOcountPOM.clickBack2(driver)).click().build().perform();	//Clicking on Dashboard
		//	performer.OverduePOM.clickDashboard(driver).click();			//Clicking on Dashboard

		}
		extent.endTest(test);
		extent.flush();
	}
	
//	@Test(priority = 17)
	void AssignmentReportInternal() throws InterruptedException, IOException
	{
		test = extent.startTest("Assignment Report verification");
		test.log(LogStatus.INFO, "Test Initiated");
		
		CFOcountPOM.AssignmentReportIn(test, driver);
		
		extent.endTest(test);
		extent.flush();
	}
	
	
		
	
}
