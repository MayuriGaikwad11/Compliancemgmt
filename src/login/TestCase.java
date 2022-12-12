package login;

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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class TestCase {
	public static WebDriver driver = null;		//WebDriver instance created
	public static WebElement upload = null;		//WebElement to get upload button
	public static FileInputStream fis = null;	//File input stream variable
	public static XSSFWorkbook workbook = null;	//Excel sheet workbook variable
	public static XSSFSheet sheet = null;		//Sheet variable
	public static ExtentReports extent;			//Instance created for report file
	public static ExtentTest test;				//Instance created for tests

	public static String link = "deepali";           //Check link in excel sheet first.
			
		
	
	public static XSSFSheet ReadExcel() throws IOException
	{
		fis = new FileInputStream("C:/March2022/PerformerPom/TestData/ComplianceSheet.xlsx");
		workbook = new XSSFWorkbook(fis);
		sheet = workbook.getSheetAt(8);					//Retrieving third sheet of Workbook
		return sheet;
	}
	
	@BeforeTest
	void setBrowser() throws InterruptedException, IOException
	{
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
	
	//@Test(priority = 1)
	void QueAns() throws InterruptedException, IOException
	{
		test = extent.startTest("question and answer msg Verify");
		test.log(LogStatus.INFO, "Logging into system");
		
		XSSFSheet sheet = ReadExcel();
		Row row1 = sheet.getRow(1);						//Selected 1st index row (Second row)
		Cell c1 = row1.getCell(1);						//Selected cell (1 row,1 column)
		String uname = c1.getStringCellValue();			//Got the URL stored at position 1,1
		
		Row row2 = sheet.getRow(2);						//Selected 2nd index row (Third row)
		Cell c2 = row2.getCell(1);						//Selected cell (2 row,1 column)
		String password = c2.getStringCellValue();		//Got the URL stored at position 2,1
		Thread.sleep(4000);
		driver = login.Login.UserLogin(uname,password,link);		//Method of Login class to login user.
		Thread.sleep(2000);
		String actualMsg = LoginPOM.readMsg1(driver).getText();	//Reading Message after Save
		Thread.sleep(1000);
		String expectedMsg1 = "Please enter correct answers and try again.";	//Expected message
		Thread.sleep(1000);
		if(actualMsg.equalsIgnoreCase(expectedMsg1))
		{
			
			test.log(LogStatus.PASS, "Please enter correct answers and try again.");
		}else
		{
			test.log(LogStatus.FAIL, "Please enter correct answers and try again.");
		}
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
	
	//@Test(priority = 2)
	void LoginHelp() throws InterruptedException, IOException
	{
		test = extent.startTest("Login Help");
		test.log(LogStatus.INFO, "Logging into system");
		Thread.sleep(2000);
		XSSFSheet sheet = ReadExcel();
		Row row1 = sheet.getRow(1);						//Selected 1st index row (Second row)
		Cell c1 = row1.getCell(1);						//Selected cell (1 row,1 column)
		String uname = c1.getStringCellValue();	
			
		Row row2 = sheet.getRow(3);						//Selected 2nd index row (Third row)
		Cell c2 = row2.getCell(1);						//Selected cell (2 row,1 column)
		String password = c2.getStringCellValue();		//Got the URL stored at position 2,1
		Thread.sleep(1000);
	
		driver = login.Login.UserLogin1(uname,password,link);		//Method of Login class to login user.
		
		driver.findElement(By.xpath("//*[@id='hlnkHelp']")).click();
		Thread.sleep(2000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,700)");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id='HyperLink1']")).click();
		test.log(LogStatus.PASS, "Login Help Btn is working");
		test.log(LogStatus.PASS, "Test Passed.");
		extent.endTest(test);
		extent.flush();
	}
	
//	@Test(priority = 3)
	void Google() throws InterruptedException, IOException
	{
		test = extent.startTest("Login Help");
		test.log(LogStatus.INFO, "Logging into system");
		Thread.sleep(2000);
		XSSFSheet sheet = ReadExcel();
		Row row1 = sheet.getRow(1);						//Selected 1st index row (Second row)
		Cell c1 = row1.getCell(1);						//Selected cell (1 row,1 column)
		String uname = c1.getStringCellValue();	
			
		Row row2 = sheet.getRow(3);						//Selected 2nd index row (Third row)
		Cell c2 = row2.getCell(1);						//Selected cell (2 row,1 column)
		String password = c2.getStringCellValue();		//Got the URL stored at position 2,1
		Thread.sleep(1000);
	
		driver = login.Login.UserLogin1(uname,password,link);		//Method of Login class to login user.
		
		driver.findElement(By.xpath("//*[@id='divLogin']/div[10]/div[1]/div")).click();
		Thread.sleep(4000);
		driver.navigate().back();  
		test.log(LogStatus.PASS, "Login Help Btn is working");
		test.log(LogStatus.PASS, "Test Passed.");
		extent.endTest(test);
		extent.flush();
	}
	
	//@Test(priority = 4)
	void AccountUnlocked() throws InterruptedException, IOException
	{
		test = extent.startTest("Account Unlocked-valid mail id");
		test.log(LogStatus.INFO, "Logging into system");
		Thread.sleep(2000);
		
		XSSFSheet sheet = ReadExcel();
		Row row1 = sheet.getRow(1);						//Selected 1st index row (Second row)
		Cell c1 = row1.getCell(1);						//Selected cell (1 row,1 column)
		String uname = c1.getStringCellValue();	
			
		Row row2 = sheet.getRow(3);						//Selected 2nd index row (Third row)
		Cell c2 = row2.getCell(1);						//Selected cell (2 row,1 column)
		String password = c2.getStringCellValue();		//Got the URL stored at position 2,1
		Thread.sleep(1000);
	
		driver = login.Login.UserLogin1(uname,password,link);		//Method of Login class to login user.
		
		driver.findElement(By.xpath("//*[@id='lbtUnlockAccount']")).click();
		Thread.sleep(4000);
		driver.findElement(By.xpath("//*[@id='txtUnlockAccountUserID']")).sendKeys("deepali@tlregtech.in");
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@id='btnProceed']")).click();
		Thread.sleep(5000);
		driver.switchTo().alert().accept();

		test.log(LogStatus.PASS, "Account unlock");
			
		
		extent.endTest(test);
		extent.flush();
	}
	
	//@Test(priority = 5)
	void AccountUnlock() throws InterruptedException, IOException
	{
		test = extent.startTest("Forgot Password -Invalid mail id");
		test.log(LogStatus.INFO, "Logging into system");
		Thread.sleep(2000);
		
		XSSFSheet sheet = ReadExcel();
		Row row1 = sheet.getRow(1);						//Selected 1st index row (Second row)
		Cell c1 = row1.getCell(1);						//Selected cell (1 row,1 column)
		String uname = c1.getStringCellValue();	
			
		Row row2 = sheet.getRow(3);						//Selected 2nd index row (Third row)
		Cell c2 = row2.getCell(1);						//Selected cell (2 row,1 column)
		String password = c2.getStringCellValue();		//Got the URL stored at position 2,1
		Thread.sleep(1000);
	
		driver = login.Login.UserLogin1(uname,password,link);		//Method of Login class to login user.
		
		driver.findElement(By.xpath("//*[@id='lbtUnlockAccount']")).click();
		Thread.sleep(4000);
		driver.findElement(By.xpath("//*[@id='txtUnlockAccountUserID']")).sendKeys("depali@tlregtech.in");
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@id='btnProceed']")).click();
		Thread.sleep(4000);
		
			String actualMsg = LoginPOM.readMsg4(driver).getText();	//Reading Message after Save
			Thread.sleep(1000);
			String expectedMsg1 = "Incorrect Email/ Email is not registered with us.";	//Expected message
			Thread.sleep(1000);
			if(actualMsg.equalsIgnoreCase(expectedMsg1))
			{
				
				test.log(LogStatus.PASS, "Incorrect Email/ Email is not registered with us.");
			}else
			{
				test.log(LogStatus.FAIL, "Incorrect Email/ Email is not registered with us. this msg not displyed");
			}
			Thread.sleep(1000);
			driver.findElement(By.xpath("//*[@id='lnkBackLogin']")).click();
			Thread.sleep(1000);
		extent.endTest(test);
		extent.flush();
	}
	
	
	//@Test(priority = 6)
	void Login() throws InterruptedException, IOException
	{
		test = extent.startTest("Loging In");
		test.log(LogStatus.INFO, "Logging into system");
		
		XSSFSheet sheet = ReadExcel();
		Row row1 = sheet.getRow(1);						//Selected 1st index row (Second row)
		Cell c1 = row1.getCell(1);						//Selected cell (1 row,1 column)
		String uname = c1.getStringCellValue();	
			
		Row row2 = sheet.getRow(3);						//Selected 2nd index row (Third row)
		Cell c2 = row2.getCell(1);						//Selected cell (2 row,1 column)
		String password = c2.getStringCellValue();		//Got the URL stored at position 2,1
		Thread.sleep(1000);
	
		driver = login.Login.UserLogin1(uname,password,link);		//Method of Login class to login user.
		String actualMsg = LoginPOM.readMsg(driver).getText();	//Reading Message after Save
		Thread.sleep(1000);
		String expectedMsg1 = "Please enter valid username or password.";	//Expected message
		Thread.sleep(1000);
		if(actualMsg.equalsIgnoreCase(expectedMsg1))
		{
			
			test.log(LogStatus.PASS, "Please enter valid username or password.");
		}else
		{
			test.log(LogStatus.FAIL, "Please enter valid username or password not displayed");
		}
	
		Thread.sleep(1000);
		extent.endTest(test);
		extent.flush();
		}
		
	
//	@Test(priority = 7)
	void forgotPass() throws InterruptedException, IOException
	{
		test = extent.startTest("Forgot Password-valid mail id");
		test.log(LogStatus.INFO, "Logging into system");
		Thread.sleep(2000);
		
		XSSFSheet sheet = ReadExcel();
		Row row1 = sheet.getRow(1);						//Selected 1st index row (Second row)
		Cell c1 = row1.getCell(1);						//Selected cell (1 row,1 column)
		String uname = c1.getStringCellValue();	
			
		Row row2 = sheet.getRow(3);						//Selected 2nd index row (Third row)
		Cell c2 = row2.getCell(1);						//Selected cell (2 row,1 column)
		String password = c2.getStringCellValue();		//Got the URL stored at position 2,1
		Thread.sleep(1000);
	
		driver = login.Login.UserLogin1(uname,password,link);		//Method of Login class to login user.
		
		driver.findElement(By.xpath("//*[@id='lbtResetPassword']")).click();
		Thread.sleep(4000);
		driver.findElement(By.xpath("//*[@id='txtResetPasswordUserID']")).sendKeys("deepali@tlregtech.in");
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@id='btnProceed']")).click();
		Thread.sleep(5000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollBy(0,300)");
			Thread.sleep(3000);
			String actualMsg = LoginPOM.readMsg2(driver).getText();	//Reading Message after Save
			Thread.sleep(1000);
			String expectedMsg1 = "Temporary password sent on your registered email. Please login with temporary password. You will be requested to change your password on your first login.";	//Expected message
			Thread.sleep(1000);
			if(actualMsg.equalsIgnoreCase(expectedMsg1))
			{
				
				test.log(LogStatus.PASS, "Temporary password sent on your registered email. Please login with temporary password. You will be requested to change your password on your first login.");
			}else
			{
				test.log(LogStatus.FAIL, "Temporary password sent on your registered email. Please login with temporary password. You will be requested to change your password on your first login. not displayed");
			}
		
		extent.endTest(test);
		extent.flush();
	}
	
//	@Test(priority = 8)
	void forgotPass1() throws InterruptedException, IOException
	{
		test = extent.startTest("Forgot Password -Invalid mail id");
		test.log(LogStatus.INFO, "Logging into system");
		Thread.sleep(2000);
		
		XSSFSheet sheet = ReadExcel();
		Row row1 = sheet.getRow(1);						//Selected 1st index row (Second row)
		Cell c1 = row1.getCell(1);						//Selected cell (1 row,1 column)
		String uname = c1.getStringCellValue();	
			
		Row row2 = sheet.getRow(3);						//Selected 2nd index row (Third row)
		Cell c2 = row2.getCell(1);						//Selected cell (2 row,1 column)
		String password = c2.getStringCellValue();		//Got the URL stored at position 2,1
		Thread.sleep(1000);
	
		driver = login.Login.UserLogin1(uname,password,link);		//Method of Login class to login user.
		
		driver.findElement(By.xpath("//*[@id='lbtResetPassword']")).click();
		Thread.sleep(4000);
		driver.findElement(By.xpath("//*[@id='txtResetPasswordUserID']")).sendKeys("depali@tlregtech.in");
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@id='btnProceed']")).click();
		Thread.sleep(4000);
		
			String actualMsg = LoginPOM.readMsg3(driver).getText();	//Reading Message after Save
			Thread.sleep(1000);
			String expectedMsg1 = "Enter valid email/ email is not registered with us.";	//Expected message
			Thread.sleep(1000);
			if(actualMsg.equalsIgnoreCase(expectedMsg1))
			{
				
				test.log(LogStatus.PASS, "Enter valid email/ email is not registered with us.");
			}else
			{
				test.log(LogStatus.FAIL, "Enter valid email/ email is not registered with us this msg not displyed");
			}
			Thread.sleep(1000);
			driver.findElement(By.xpath("//*[@id='lnklogin']")).click();
			Thread.sleep(1000);
		extent.endTest(test);
		extent.flush();
	}
	
	}

