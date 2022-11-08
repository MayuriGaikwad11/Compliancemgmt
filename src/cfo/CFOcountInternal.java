package cfo;

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
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import performer.OverduePOM;

public class CFOcountInternal 
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
	//Write "CFO" for login.avantis
	public static String link = "MGMT";			//Check link in excel sheet first.
	
	public static XSSFSheet ReadExcel() throws IOException
	{
		String workingDir = System.getProperty("webdriver.chrome.driver","C:/March2022/PerformerPom/Driver/chromedriver.exe");
		fis = new FileInputStream("C:/March2022/PerformerPom/TestData/ComplianceSheet.xlsx");
		workbook = new XSSFWorkbook(fis);
		sheet = workbook.getSheetAt(0);					//Retrieving third sheet of Workbook
		return sheet;
	}
	
	@BeforeTest
	void setBrowser() throws InterruptedException, IOException
	{
		String workingDir = System.getProperty("webdriver.chrome.driver","C:/March2022/PerformerPom/Driver/chromedriver.exe");
		extent = new com.relevantcodes.extentreports.ExtentReports(workingDir+"//Reports//CFOResultsInternal.html",true);
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
		test = extent.startTest("Loging In - MGMT Finance (Internal)");
		test.log(LogStatus.INFO, "Logging into system");
		
		XSSFSheet sheet = ReadExcel();
		Row row1 = sheet.getRow(5);						//Selected 1st index row (Second row)
		Cell c1 = row1.getCell(1);						//Selected cell (1 row,1 column)
		String uname = c1.getStringCellValue();			//Got the URL stored at position 1,1
		
		Row row2 = sheet.getRow(6);						//Selected 2nd index row (Third row)
		Cell c2 = row2.getCell(1);						//Selected cell (2 row,1 column)
		String password = c2.getStringCellValue();		//Got the URL stored at position 2,1
		
		//Write "CFO-diy" for DIYProduction link.
		//Write "CFO" for login.avantis
		driver = login.Login.UserLogin(uname,password,link);		//Method of Login class to login user.
		
		Thread.sleep(700);
		Select drp = new Select(CFOcountPOM.selectInternal(driver));
		drp.selectByIndex(1);
		
		Thread.sleep(1000);
		CFOcountPOM.clickApply(driver).click();
		
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
	
	/*@Test(priority = 2)
	void clickCategoriesInternal() throws InterruptedException
	{
		test = extent.startTest("'Complainces' Count by Clicking on 'Categories'");
		test.log(LogStatus.INFO, "Test Initiated");
		
		Thread.sleep(2000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		int valueCompliances = Integer.parseInt(CFOcountPOM.readCompliancesInternal(driver).getText());	//Storing old value of 'Compliances'.
		
		CFOcountPOM.clickCategories(driver).click();					//Clicking on 'Categories'.
		
		Thread.sleep(500);
		litigationPerformer.MethodsPOM.progress(driver);
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("showdetails"));	//Wait until frame get visible and switch to it.
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class = 'k-grid-content k-auto-scrollable']"))); //Wait until first row's Compliance Count to be visible
		
		CFOcountPOM.readTotalItems1(driver).click();
		String s1 = CFOcountPOM.readTotalItems1(driver).getText();
		String[] bits1 = s1.split(" ");									//Splitting the String
		String itomsCount = bits1[bits1.length - 2];						//Getting the second last word (total number of items)
		int count2 = 0;
		int loop = 0;
		if(itomsCount.equalsIgnoreCase("to"))							//If items not found
		{
			for(int i = 0; i < 4; i++)
			{
				Thread.sleep(2000);
				s1 = CFOcountPOM.readTotalItems1(driver).getText();
				bits1 = s1.split(" ");									//Splitting the String
				itomsCount = bits1[bits1.length - 2];
				if(!itomsCount.equalsIgnoreCase("to"))					//If not items found
				{
					break;
				}
			}
		}
		count2 = Integer.parseInt(itomsCount);
		loop = count2 / 10;									//Dividing by 10, as total number of items in a list is 10
		
		elementsList = CFOcountPOM.readCompliancesList(driver);			//Searching all values of Compliance 
		int n = elementsList.size();
		int value = 0;
		int count1 = 0;
		int CategoriesCount = 0;
		for(int i = 0; i < n; i++)
		{
			elementsList = CFOcountPOM.readCompliancesList(driver);
			value = Integer.parseInt(elementsList.get(i).getText());	//Reading each Compliance value.
			count = count + value;										//Calculating the read Compliance values.
			
			if(value > 0)
			{
				Thread.sleep(500);
				CategoriesCount = CategoriesCount + 1;
				elementsList1 = CFOcountPOM.readCompliancesList(driver);
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
					test.log(LogStatus.PASS, "Compliances count matches. Clicked Value = " + value+ " | Grid Records = "+count1);
				}
				else
				{
					test.log(LogStatus.FAIL, "Compliances count does not matches. Clicked Value = "+value+" | Grid Records = "+count1);
				}
				
				driver.switchTo().parentFrame();								//Switching back to parent frame.
				Thread.sleep(100);
				CFOcountPOM.closeCategories_Compliances(driver).click();		//Closing the 'Compliances' pup up.
			}
			
			if(i == n-1)
			{
				if(CFOcountPOM.clickNextPage1(driver).isEnabled())
				{
					if(loop <= 0)						//Loop calculated from total number of elements.
					{
						break;							//If loop is 0 then break.
					}
					
					elementsList1 = CFOcountPOM.checkTotalIndexes(driver);	//Checking the total indexes available to click.
					if(elementsList1.size() <= 2)							//First element is label so the count two represents 1 index.
					{
						break;							//If we have only one page index then break.
					}
					
					loop --;
					Thread.sleep(500);
					CFOcountPOM.clickNextPage1(driver).click();
					Thread.sleep(250);
					elementsList = CFOcountPOM.readCompliancesList(driver);
					n = elementsList.size();
					i = -1;
				}
			}
		}
		
		Thread.sleep(200);
		js.executeScript("window.scrollBy(500,0)");						//Scrolling UP window by 2000 px.
		driver.switchTo().parentFrame();								//Switching back to parent frame.
		Thread.sleep(500);
		CFOcountPOM.closeCategories(driver).click();					//Closing the 'Categories' pup up.
		
		if(count == valueCompliances)
		{
			test.log(LogStatus.PASS, "Categories Count = " + CategoriesCount);
			test.log(LogStatus.PASS, "'Compliances' count of Dashboard matches to 'Categories'. Dashboard value = " + valueCompliances + " | Sum of Compliances from 'Categories' click = "+ count);
		}
		else
		{
			test.log(LogStatus.PASS, "Categories Count = " + CategoriesCount);
			test.log(LogStatus.FAIL, "'Compliances' count of Dashboard doesn't matches to 'Categories'. Dashboard value = " + valueCompliances + " | Sum of Compliances from 'Categories' click = "+ count);
		}
		extent.endTest(test);
		extent.flush();
	}
	
	@Test(priority = 3)
	void ClickCompliancesInternal() throws InterruptedException
	{
		test = extent.startTest("'Complainces' Count by Clicking on 'Compliances'");
		test.log(LogStatus.INFO, "Test Initiated");
		
		Thread.sleep(1500);
		WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait1.until(ExpectedConditions.visibilityOf(CFOcountPOM.readCompliancesInternal(driver)));
		int valueCompliances = Integer.parseInt(CFOcountPOM.readCompliancesInternal(driver).getText());	//Storing value of 'Compliances' as a String to compare.
		
		driver.findElement(By.xpath("(//*[@class = 'titleMD'])[4]")).click();
		//CFOcountPOM.readCompliancesInternal(driver).click();					//Clicking on 'Compliances'.
		
		Thread.sleep(500);
		litigationPerformer.MethodsPOM.progress(driver);
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
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
	
	@Test(priority = 4)
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
	
	@Test(priority = 5)
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
		int NotCompletedValue = Integer.parseInt(CFOcountPOM.clickNotCompletedInternal(driver).getText());	//Reading value of 'Not Completed'
		CFOcountPOM.clickNotCompletedInternal(driver).click();									//CLicking on 'Not Completed' count
		
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
				CFOcountPOM.GraphCount(driver, test, "Critical", critical, "Internal");
			}
			else
			{
				test.log(LogStatus.SKIP, "'Critical' Risk Compliance Count = "+critical);
			}
			
			if(high > 0)
			{
				CFOcountPOM.GraphCount(driver, test, "High", high, "Internal");
			}
			else
			{
				test.log(LogStatus.SKIP, "'High' Risk Compliance Count = "+high);
			}
			
			if(medium > 0)
			{
				CFOcountPOM.GraphCount(driver, test, "Medium", medium, "Internal");
			}
			else
			{
				test.log(LogStatus.SKIP, "'Medium' Risk Compliance Count = "+medium);
			}
			
			if(low > 0)
			{
				CFOcountPOM.GraphCount(driver, test, "Low", low, "Internal");
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
	
	@Test(priority = 6)
	void ClosedDelayed_PieChart() throws InterruptedException
	{
		test = extent.startTest("Pie Chart - 'Closed Delayed' Count Verification");
		test.log(LogStatus.INFO, "Test Initiated");
		test.log(LogStatus.INFO, "---After selecting all location from 'Entity/Sub-Entity/Location' drop down.");
		
		Actions action = new Actions(driver);
		//JavascriptExecutor js = (JavascriptExecutor) driver;
		//js.executeScript("window.scrollBy(0,500)");						//Scrolling down window by 1000 px.
		
		Thread.sleep(1500);
		int ClosedDelayedValue = Integer.parseInt(CFOcountPOM.clickClosedDelayedInternal(driver).getText());	//Reading value of 'After Due Date'
		
		CFOcountPOM.clickClosedDelayedInternal(driver).click();								//CLicking on 'Not Completed' count
		
		Thread.sleep(500);
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
				CFOcountPOM.GraphCount(driver, test, "Critical", critical, "Internal");
			}
			else
			{
				test.log(LogStatus.SKIP, "'Critical' Risk Compliance Count = "+critical);
			}
			
			if(high > 0)
			{
				CFOcountPOM.GraphCount(driver, test, "High", high, "Internal");
			}
			else
			{
				test.log(LogStatus.SKIP, "'High' Risk Compliance Count = "+high);
			}
			
			if(medium > 0)
			{
				CFOcountPOM.GraphCount(driver, test, "Medium", medium, "Internal");
			}
			else
			{
				test.log(LogStatus.SKIP, "'Medium' Risk Compliance Count = "+medium);
			}
			
			if(low > 0)
			{
				CFOcountPOM.GraphCount(driver, test, "Low", low, "Internal");
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
	
	@Test(priority = 7)
	void ClosedTimely_PieChart() throws InterruptedException
	{
		test = extent.startTest("Pie Chart - 'Closed Timely' Count Verification");
		test.log(LogStatus.INFO, "Test Initiated");
		test.log(LogStatus.INFO, "---After selecting all location from 'Entity/Sub-Entity/Location' drop down.");
		
		Actions action = new Actions(driver);
		//JavascriptExecutor js = (JavascriptExecutor) driver;
		//js.executeScript("window.scrollBy(0,500)");						//Scrolling down window by 1000 px.
		
		Thread.sleep(1500);
		int ClosedTimelyValue = Integer.parseInt(CFOcountPOM.clickClosedTimelyInternal(driver).getText());	//Reading value of 'After Due Date'
		CFOcountPOM.clickClosedTimelyInternal(driver).click();								//CLicking on 'Not Completed' count
		
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
				CFOcountPOM.GraphCount(driver, test, "Critical", critical, "Internal");
			}
			else
			{
				test.log(LogStatus.SKIP, "'Critical' Risk Compliance Count = "+critical);
			}
			
			if(high > 0)
			{
				CFOcountPOM.GraphCount(driver, test, "High", high, "Internal");
			}
			else
			{
				test.log(LogStatus.SKIP, "'High' Risk Compliance Count = "+high);
			}
			
			if(medium > 0)
			{
				CFOcountPOM.GraphCount(driver, test, "Medium", medium, "Internal");
			}
			else
			{
				test.log(LogStatus.SKIP, "'Medium' Risk Compliance Count = "+medium);
			}
			
			if(low > 0)
			{
				CFOcountPOM.GraphCount(driver, test, "Low", low, "Internal");
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
	}
	
	@Test(priority = 8)
	void NotApplicable_PieChart() throws InterruptedException
	{
		test = extent.startTest("Pie Chart - 'Not Applicable' Count Verification");
		test.log(LogStatus.INFO, "Test Initiated");
		test.log(LogStatus.INFO, "---After selecting all location from 'Entity/Sub-Entity/Location' drop down.");
		
		Actions action = new Actions(driver);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOf(CFOcountPOM.clickNotApplicableInternal(driver)));
		
		Thread.sleep(1000);
		int NotApplicableValue = Integer.parseInt(CFOcountPOM.clickNotApplicableInternal(driver).getText());	//Reading value of 'After Due Date'
		CFOcountPOM.clickNotApplicableInternal(driver).click();								//CLicking on 'Not Completed' count
		
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
				CFOcountPOM.GraphCount(driver, test, "Critical", critical, "Internal");
			}
			else
			{
				test.log(LogStatus.SKIP, "'Critical' Risk Compliance Count = "+critical);
			}
			
			if(high > 0)
			{
				CFOcountPOM.GraphCount(driver, test, "High", high, "Internal");
			}
			else
			{
				test.log(LogStatus.SKIP, "'High' Risk Compliance Count = "+high);
			}
			
			if(medium > 0)
			{
				CFOcountPOM.GraphCount(driver, test, "Medium", medium, "Internal");
			}
			else
			{
				test.log(LogStatus.SKIP, "'Medium' Risk Compliance Count = "+medium);
			}
			
			if(low > 0)
			{
				CFOcountPOM.GraphCount(driver, test, "Low", low, "Internal");
			}
			else
			{
				test.log(LogStatus.SKIP, "'Low' Risk Compliance Count = "+low);
			}
			
			Thread.sleep(500);
			action.moveToElement(CFOcountPOM.clickBack1(driver)).click().build().perform();	 //Clicking on Back button
			
			Thread.sleep(500);
			js.executeScript("window.scrollBy(0,-500)");				//Going to Dashboard
		}
		else
		{
			test.log(LogStatus.SKIP, "'Not Applicable' Compliance Count = "+NotApplicableValue);
			
			Thread.sleep(500);
			action.moveToElement(CFOcountPOM.clickBack1(driver)).click().build().perform();	//Clicking on Dashboard
			
			Thread.sleep(500);
			js.executeScript("window.scrollBy(0,-500)");				//Going to Dashboard
		}
		extent.endTest(test);
		extent.flush();
	}
	
	@Test(priority = 9)
	void RiskSummaryCriticalInternal() throws InterruptedException
	{
		Thread.sleep(500);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,1050)");					//Scrolling down window by 1000 px.
		
		test = extent.startTest("Risk Summary - 'Critical' Count Verification");
		test.log(LogStatus.INFO, "Test Initiated");
		test.log(LogStatus.INFO, "---After selecting all location from 'Entity/Sub-Entity/Location' drop down.");
		
		//------------------------------------------------------------------
		
		Thread.sleep(500);
		String NotCompleted = CFOcountPOM.clickRiskCriticalNotCompleted(driver).getText();
		int RiskCritical_NotCompleted = 0;
		if(NotCompleted.equals("") || NotCompleted.equals(null))
		{
			RiskCritical_NotCompleted = 0;
		}
		else
		{
			RiskCritical_NotCompleted = Integer.parseInt(NotCompleted);
		}
		if(RiskCritical_NotCompleted > 0)
		{
			CFOcountPOM.clickRiskCriticalNotCompleted(driver).click();			//Clicking on Not Completed compliances bar of High risk.  
			
			CFOcountPOM.RiskGraphCount(driver, test, "Critical - Not Completed", RiskCritical_NotCompleted, "Internal");
		}
		else
		{
			test.log(LogStatus.SKIP, "'Critical - Not Completed' Count = "+RiskCritical_NotCompleted);
		}
		
		//-----------------------------------------
		
		Thread.sleep(500);
		String ClosedDelayed = CFOcountPOM.clickRiskCriticalClosedDelayed(driver).getText();
		int RiskCritical_ClosedDelayed = 0;
		if(ClosedDelayed.equals("") || ClosedDelayed.equals(null))
		{
			RiskCritical_ClosedDelayed = 0;
		}
		else
		{
			RiskCritical_ClosedDelayed = Integer.parseInt(ClosedDelayed);
		}
		if(RiskCritical_ClosedDelayed > 0)
		{
			CFOcountPOM.clickRiskCriticalClosedDelayed(driver).click();			//Clicking on Not Completed compliances bar of High risk.  
			
			CFOcountPOM.RiskGraphCount(driver, test, "Critical - Closed Delayed", RiskCritical_ClosedDelayed, "Internal");
		}
		else
		{
			test.log(LogStatus.SKIP, "'Critical - Closed Delayed' Count = "+RiskCritical_ClosedDelayed);
		}
		
		//----------------------------------------------------------------
		
		Thread.sleep(500);
		String ClosedTimely = CFOcountPOM.clickRiskCriticalClosedTimely(driver).getText();
		int RiskCritical_ClosedTimely = 0;
		if(ClosedTimely.equals("") || ClosedTimely.equals(null))
		{
			RiskCritical_ClosedTimely = 0;
		}
		else
		{
			RiskCritical_ClosedTimely = Integer.parseInt(ClosedTimely);
		}
		if(RiskCritical_ClosedTimely > 0)
		{
			CFOcountPOM.clickRiskCriticalClosedTimely(driver).click();			//Clicking on Not Completed compliances bar of High risk.  
			
			CFOcountPOM.RiskGraphCount(driver, test, "Critical - Closed Timely", RiskCritical_ClosedTimely, "Internal");
		}
		else
		{
			test.log(LogStatus.SKIP, "'Critical - Closed Timely' Count = "+RiskCritical_ClosedTimely);
		}
		extent.endTest(test);
		extent.flush();
	}
	
	@Test(priority = 10)
	void RiskSummaryHighInternal() throws InterruptedException
	{		
		test = extent.startTest("Risk Summary - 'High' Count Verification");
		test.log(LogStatus.INFO, "Test Initiated");
		test.log(LogStatus.INFO, "---After selecting all location from 'Entity/Sub-Entity/Location' drop down.");
		
		//------------------------------------------------------------
		
		Thread.sleep(500);
		String NotCompleted = CFOcountPOM.clickRiskHighNotCompleted(driver).getText();
		int RiskHigh_NotCompleted = 0;
		if(NotCompleted.equals("") || NotCompleted.equals(null))
		{
			RiskHigh_NotCompleted = 0;
		}
		else
		{
			RiskHigh_NotCompleted = Integer.parseInt(NotCompleted);
		}
		if(RiskHigh_NotCompleted > 0)
		{
			CFOcountPOM.clickRiskHighNotCompleted(driver).click();			//Clicking on Not Completed compliances bar of High risk.  
			
			CFOcountPOM.RiskGraphCount(driver, test, "High - Not Completed", RiskHigh_NotCompleted, "Internal");
		}
		else
		{
			test.log(LogStatus.SKIP, "'High - Not Completed' Count = "+RiskHigh_NotCompleted);
		}
		
		//-------------------------------------------
		
		Thread.sleep(500);
		String ClosedDelayed = CFOcountPOM.clickRiskHighClosedDelayed(driver).getText();
		int RiskHigh_ClosedDelayed = 0;
		if(ClosedDelayed.equals("") || ClosedDelayed.equals(null))
		{
			RiskHigh_ClosedDelayed = 0;
		}
		else
		{
			RiskHigh_ClosedDelayed = Integer.parseInt(ClosedDelayed);
		}
		if(RiskHigh_ClosedDelayed > 0)
		{
			CFOcountPOM.clickRiskHighClosedDelayed(driver).click();			//Clicking on Not Completed compliances bar of High risk.  
			
			CFOcountPOM.RiskGraphCount(driver, test, "High - Closed Delayed", RiskHigh_ClosedDelayed, "Internal");
		}
		else
		{
			test.log(LogStatus.SKIP, "'High - Closed Delayed' Count = "+RiskHigh_ClosedDelayed);
		}
		
		//-------------------------------------------------------
		
		Thread.sleep(500);
		String ClosedTimely = CFOcountPOM.clickRiskHighClosedTimely(driver).getText();
		int RiskHigh_ClosedTimely = 0;
		if(ClosedTimely.equals("") || ClosedTimely.equals(null))
		{
			RiskHigh_ClosedTimely = 0;
		}
		else
		{
			RiskHigh_ClosedTimely = Integer.parseInt(ClosedTimely);
		}
		if(RiskHigh_ClosedTimely > 0)
		{
			CFOcountPOM.clickRiskHighClosedTimely(driver).click();			//Clicking on Not Completed compliances bar of High risk.  
			
			CFOcountPOM.RiskGraphCount(driver, test, "High - Closed Timely", RiskHigh_ClosedTimely, "Internal");
		}
		else
		{
			test.log(LogStatus.SKIP, "'High - Closed Timely' Count = "+RiskHigh_ClosedTimely);
		}
		extent.endTest(test);
		extent.flush();
	}
	
	@Test(priority = 11)
	void RiskSummaryMediumInternal() throws InterruptedException
	{
		test = extent.startTest("Risk Summary - 'Medium' Count Verification");
		test.log(LogStatus.INFO, "Test Initiated");
		test.log(LogStatus.INFO, "---After selecting all location from 'Entity/Sub-Entity/Location' drop down.");
		
		//------------------------------------------------------------
		
		Thread.sleep(500);
		String NotCompleted = CFOcountPOM.clickRiskMediumNotCompleted(driver).getText();
		int RiskMedium_NotCompleted = 0;
		if(NotCompleted.equals("") || NotCompleted.equals(null))
		{
			RiskMedium_NotCompleted = 0;
		}
		else
		{
			RiskMedium_NotCompleted = Integer.parseInt(NotCompleted);
		}
		if(RiskMedium_NotCompleted > 0)
		{
			CFOcountPOM.clickRiskMediumNotCompleted(driver).click();			//Clicking on Not Completed compliances bar of High risk.  
			
			CFOcountPOM.RiskGraphCount(driver, test, "Medium - Not Completed", RiskMedium_NotCompleted, "Internal");
		}
		else
		{
			test.log(LogStatus.SKIP, "'Medium - Not Completed' Count = "+RiskMedium_NotCompleted);
		}
		
		//-------------------------------------------------------------
		
		Thread.sleep(500);
		String ClosedDelayed = CFOcountPOM.clickRiskMediumClosedDelayed(driver).getText();
		int RiskMedium_ClosedDelayed = 0;
		if(ClosedDelayed.equals("") || ClosedDelayed.equals(null))
		{
			RiskMedium_ClosedDelayed = 0;
		}
		else
		{
			RiskMedium_ClosedDelayed = Integer.parseInt(ClosedDelayed);
		}		
		if(RiskMedium_ClosedDelayed > 0)
		{
			CFOcountPOM.clickRiskMediumClosedDelayed(driver).click();			//Clicking on Not Completed compliances bar of High risk.  
			
			CFOcountPOM.RiskGraphCount(driver, test, "Medium - Closed Delayed", RiskMedium_ClosedDelayed, "Internal");
		}
		else
		{
			test.log(LogStatus.SKIP, "'Medium - Closed Delayed' Count = "+RiskMedium_ClosedDelayed);
		}
		
		//-----------------------------------------------------
		
		Thread.sleep(500);
		String ClosedTimely = CFOcountPOM.clickRiskMediumClosedTimely(driver).getText();
		int RiskMedium_ClosedTimely = 0;
		if(ClosedTimely.equals("") || ClosedTimely.equals(null))
		{
			RiskMedium_ClosedTimely = 0;
		}
		else
		{
			RiskMedium_ClosedTimely = Integer.parseInt(ClosedTimely);
		}
		if(RiskMedium_ClosedTimely > 0)
		{
			CFOcountPOM.clickRiskMediumClosedTimely(driver).click();			//Clicking on Not Completed compliances bar of High risk.  
			
			CFOcountPOM.RiskGraphCount(driver, test, "Medium - Closed Timely", RiskMedium_ClosedTimely, "Internal");
		}
		else
		{
			test.log(LogStatus.SKIP, "'Medium - Closed Timely' Count = "+RiskMedium_ClosedTimely);
		}
		extent.endTest(test);
		extent.flush();
	}
	
	@Test(priority = 12)
	void RiskSummaryLowInternal() throws InterruptedException
	{		
		test = extent.startTest("Risk Summary - 'Low' Count Verification");
		test.log(LogStatus.INFO, "Test Initiated");
		test.log(LogStatus.INFO, "---After selecting all location from 'Entity/Sub-Entity/Location' drop down.");
		
		//------------------------------------------------
		
		Thread.sleep(500);
		String NotCompleted = CFOcountPOM.clickRiskLowNotCompleted(driver).getText();
		int RiskLow_NotCompleted = 0;
		if(NotCompleted.equals("") || NotCompleted.equals(null))
		{
			RiskLow_NotCompleted = 0;
		}
		else
		{
			RiskLow_NotCompleted = Integer.parseInt(NotCompleted);
		}
		if(RiskLow_NotCompleted > 0)
		{
			CFOcountPOM.clickRiskLowNotCompleted(driver).click();			//Clicking on Not Completed compliances bar of High risk.  
			
			CFOcountPOM.RiskGraphCount(driver, test, "Low - Not Completed", RiskLow_NotCompleted, "Internal");
		}
		else
		{
			test.log(LogStatus.SKIP, "'Low - Not Completed' Count = "+RiskLow_NotCompleted);
		}
		
		//-------------------------------------------------------------
		
		Thread.sleep(500);
		String ClosedDelayed = CFOcountPOM.clickRiskLowClosedDelayed(driver).getText();
		int RiskLow_ClosedDelayed = 0;
		if(ClosedDelayed.equals("") || ClosedDelayed.equals(null))
		{
			RiskLow_ClosedDelayed = 0;
		}
		else
		{
			RiskLow_ClosedDelayed = Integer.parseInt(ClosedDelayed);
		}
		if(RiskLow_ClosedDelayed > 0)
		{
			CFOcountPOM.clickRiskLowClosedDelayed(driver).click();			//Clicking on Not Completed compliances bar of High risk.  
			
			CFOcountPOM.RiskGraphCount(driver, test, "Low - Closed Delayed", RiskLow_ClosedDelayed, "Internal");
		}
		else
		{
			test.log(LogStatus.SKIP, "'Low - Closed Delayed' Count = "+RiskLow_ClosedDelayed);
		}
		
		//-------------------------------------------------------
		
		Thread.sleep(500);
		String ClosedTimely = CFOcountPOM.clickRiskLowClosedTimely(driver).getText();
		int RiskLow_ClosedTimely = 0;
		if(ClosedTimely.equals("") || ClosedTimely.equals(null))
		{
			RiskLow_ClosedTimely = 0;
		}
		else
		{
			RiskLow_ClosedTimely = Integer.parseInt(ClosedTimely);
		}
		if(RiskLow_ClosedTimely > 0)
		{
			CFOcountPOM.clickRiskLowClosedTimely(driver).click();			//Clicking on Not Completed compliances bar of High risk.  
			
			CFOcountPOM.RiskGraphCount(driver, test, "Low - Closed Timely", RiskLow_ClosedTimely, "Internal");
		}
		else
		{
			test.log(LogStatus.SKIP, "'Low - Closed Timely' Count = "+RiskLow_ClosedTimely);
		}
		
		Thread.sleep(500);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,-1050)");			//Clicking on Dashboard
		
		extent.endTest(test);
		extent.flush();
	}
	
	@Test(priority = 13)
	void DepartmentSummaryNotCompletedInternal() throws InterruptedException
	{
		Thread.sleep(500);		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,1600)");					//Scrolling down window by 1500 px.
		
		test = extent.startTest("Department Summary - 'Not Completed' Count Verification");
		test.log(LogStatus.INFO, "Test Initiated");
		test.log(LogStatus.INFO, "---After selecting all location from 'Entity/Sub-Entity/Location' drop down.");
		
		Thread.sleep(500);
		String AdminNotCompl = CFOcountPOM.clickAdminNotComplInternal(driver).getText();	//Reading the Closed Delayed value of Human Resource
		AdminNotCompl = AdminNotCompl.replaceAll(" ","");								//Removing all white spaces from string. 
		int Admin_NotCompl = Integer.parseInt(AdminNotCompl);						
		if(Admin_NotCompl > 0)
		{
			CFOcountPOM.clickHumanClosedDelayed(driver).click();
			CFOcountPOM.RiskGraphCount(driver, test, "Admin - Not Completed", Admin_NotCompl, "Internal");
		}
		else
		{
			test.log(LogStatus.SKIP, "'Admin - Not Completed' Complaince Count = "+ Admin_NotCompl + ".");
		}
		
		//------------------------------------------------------
		
		Thread.sleep(500);
		String BTRNotCompl = CFOcountPOM.clickBTRNotComplInternal(driver).getText();		//Reading the Closed Timely value of Human Resource
		BTRNotCompl = BTRNotCompl.replaceAll(" ","");									//Removing all white spaces from string. 
		int BTR_NotCompl = Integer.parseInt(BTRNotCompl);						
		if(BTR_NotCompl > 0)
		{
			CFOcountPOM.clickBTRNotComplInternal(driver).click();
			CFOcountPOM.RiskGraphCount(driver, test, "BTR - Not Completed", BTR_NotCompl, "Internal");
		}
		else
		{
			test.log(LogStatus.SKIP, "'BTR - Not Completed' Complaince Count = "+ BTR_NotCompl + ".");
		}
		
		//-----------------------------------------------------
		
		Thread.sleep(500);
		String DSSNotCompl = CFOcountPOM.clickDSSNotComplInternal(driver).getText();			//Reading the Overdue value of Human Resource
		DSSNotCompl = DSSNotCompl.replaceAll(" ","");									//Removing all white spaces from string. 
		int DSS_NotCompl = Integer.parseInt(DSSNotCompl);						
		if(DSS_NotCompl > 0)
		{
			CFOcountPOM.clickDSSNotComplInternal(driver).click();
			CFOcountPOM.RiskGraphCount(driver, test, "DSS - Not Completed", DSS_NotCompl, "Internal");
		}
		else
		{
			test.log(LogStatus.SKIP, "'DSS - Not Completed' Complaince Count = "+ DSS_NotCompl + ".");
		}
		
		Thread.sleep(500);
		js.executeScript("window.scrollBy(0,-1600)");			//Clicking on Dashboard
		
		extent.endTest(test);
		extent.flush();
	}
	
	@Test(priority = 14)
	void GradingReportInternal() throws InterruptedException, IOException
	{
		Thread.sleep(500);		
		test = extent.startTest("'Grading Report' Count Verification");
		test.log(LogStatus.INFO, "Test Initiated");
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,2150)");					//Scrolling down window by 2600 px.
		
		Thread.sleep(500);
		elementsList = CFOcountPOM.clickExpandGrading(driver);
		for(int i = 0; i < elementsList.size(); i++)
		{
			Thread.sleep(250);
			elementsList.get(i).click();
		}
		
		//-----------------------------------------------------------
		
		try
		{
			Thread.sleep(500);
			CFOcountPOM.clickRedGrading(driver).click();					//Clicking on first row, Second Red box of grading button
			CFOcountPOM.CountGrading(driver, test, "High Risk (Red)");
		}
		catch(Exception e)
		{
			
		}
		
		//-----------------------------------------------------------
		
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
		
		//-----------------------------------------------------------
		
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
		js.executeScript("window.scrollBy(0,-2200)");			//Going to Dashboard
		
		extent.endTest(test);
		extent.flush();
	}
	*/
	@AfterTest
	void Closing() throws InterruptedException
	{
		//Thread.sleep(2000);
		//driver.close();
	}
}
