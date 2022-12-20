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
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import performer.OverduePOM;

public class LoginTest {
	public static FileInputStream fis = null;	//File input stream variable
	public static XSSFWorkbook workbook = null;	//Excel sheet workbook variable
	public static XSSFSheet sheet = null;	
	private static List<WebElement> elementsList = null;
   public static ExtentReports extent;
   public static ExtentTest test;
	
	 @Test
	  public void f() throws InterruptedException, IOException {
		 extent=new com.relevantcodes.extentreports.ExtentReports("C:/March2022/PerformerPom/Reports/CFOResultsStatotory.html",true);
		test=extent.startTest("msg");
		  System.setProperty("webdriver.chrome.driver","C:/March2022/PerformerPom/Driver1/chromedriver.exe"); 
		  
		  WebDriver driver=new ChromeDriver();       //Created new Chrome driver instance.
		  
		  driver.manage().window().maximize(); 
		 fis = new FileInputStream("C:/March2022/PerformerPom/TestData/ComplianceSheet.xlsx");
			workbook = new XSSFWorkbook(fis);
			sheet = workbook.getSheetAt(0);					//Retrieving third sheet of Workbook

			Row row0 = sheet.getRow(0);						//Selected 0th index row (First row)
			Cell c1 = row0.getCell(1);						//Selected cell (0 row,1 column)
			String URL = c1.getStringCellValue();			//Got the URL stored at position 0,1
			 driver.get(URL);
			
				Row row1 = sheet.getRow(1);						//Selected 1st index row (Second row)
				 c1 = row1.getCell(1);						//Selected cell (1 row,1 column)
				String uname = c1.getStringCellValue();			//Got the URL stored at position 1,1
				 WebElement userName=driver.findElement(By.xpath("//*[@id='txtemail']"));
				  userName.sendKeys(uname); 
				Row row2 = sheet.getRow(2);						//Selected 2nd index row (Third row)
				 c1 = row2.getCell(1);						//Selected cell (2 row,1 column)
				String password = c1.getStringCellValue();
				  
				  WebElement password1=driver.findElement(By.xpath("//*[@id='txtpass']"));
				  password1.sendKeys(password);                                            //Sent password to input box
				  
				  WebElement signBtn=driver.findElement(By.xpath("//*[@id='Submit']"));
				  signBtn.click();  //Clicked on Sign-in button
				  
				  WebElement clickComplicane=driver.findElement(By.xpath("//div[@id='dvbtnCompliance']/div[1]/img"));
				  clickComplicane.click(); 
				  WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
				  WebElement msg=driver.findElement(By.xpath("//*[@id='divNotification']/div/div/div[1]/button"));

				  wait.until(ExpectedConditions.elementToBeClickable(msg));
				  msg.click();
				  Thread.sleep(2000);
				  WebElement clickworkspace=driver.findElement(By.xpath("//*[@id='leftworkspacemenuLic']/a/span[1]"));
				  clickworkspace.click(); 
				  WebElement clickCompliance=driver.findElement(By.xpath("//*[@id='LiComplist']"));
				  clickCompliance.click(); 
				  
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//*[@role='grid'][@data-role='selectable'])[1]")));	//Waiting for records table to get visible.
		 WebElement clickMoreActions=driver.findElement(By.xpath("//*[@id='example']/div[2]/span[2]"));
		 clickMoreActions.click(); 
		 Thread.sleep(500);
			elementsList = OverduePOM.selectAction(driver);				//Getting all 'More Action' drop down option
			elementsList.get(4).click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='ContentPlaceHolder1_grdTaskPerformer']")));	//Waiting for records table to get visible
			
			Thread.sleep(500);
			OverduePOM.ClickTaskCreation(driver).click();				//Clicking on 'Task Creation' tab
			
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='ContentPlaceHolder1_grdTask']")));	//Waiting for records table to get visible
			Thread.sleep(1000);
			WebElement clickDelete=driver.findElement(By.xpath("//*[@id='ContentPlaceHolder1_grdTask_lbtDelete_0']"));
			 clickDelete.click();
			 Thread.sleep(1000);
			 driver.switchTo().alert().accept();	
				
	 }
}
