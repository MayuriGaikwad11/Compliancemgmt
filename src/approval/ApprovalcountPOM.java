package approval;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import cfo.CFOcountPOM;

public class ApprovalcountPOM {
	public static FileInputStream fis = null;	//File input stream variable
	public static XSSFWorkbook workbook = null;	//Excel sheet workbook variable
	public static XSSFSheet sheet = null;		//Sheet variable
	
	private static WebElement compliances = null;
	
	
	public static WebElement clickManagement(WebDriver driver)		//Method for reading Compliances value on Dashboard
	{
		compliances = driver.findElement(By.xpath("//*[@id='ContentPlaceHolder1_LinkButton3']"));
		return compliances;
	}
	
	public static void GraphCount(WebDriver driver, ExtentTest test, String risk, int complianceCount, String Compliance)throws InterruptedException
	{
		Thread.sleep(500);
		if(risk.equalsIgnoreCase("Critical"))
		{
			CFOcountPOM.readCritical(driver).click();					//Clicking on Critical value of Pie Chart of 'Not Completed'.
		}
		else if(risk.equalsIgnoreCase("High"))
		{
			CFOcountPOM.readHigh(driver).click();						//Clicking on High value of Pie Chart of 'Not Completed'.
		}
		else if(risk.equalsIgnoreCase("Medium"))
		{
			CFOcountPOM.readMedium(driver).click();						//Clicking on Medium value of Pie Chart of 'Not Completed'.
		}
		else if(risk.equalsIgnoreCase("Low"))
		{
			CFOcountPOM.readLow(driver).click();						//Clicking on Low value of Pie Chart of 'Not Completed'.
		}
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("showdetails"));	//Wait until frame get visible and switch to it.
		try
		{
			
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class='k-selectable']")));
			Thread.sleep(3000);
		}
		catch(Exception e)
		{
			
		}
		
		 CFOcountPOM.clickExportImage(driver).click();
			Thread.sleep(4000);
			test.log(LogStatus.PASS, "Excel file Export Successfully");
			Thread.sleep(3000);
			
  By locator = By.xpath("//*[@id='grid']/div[3]/table/tbody/tr[1]/td[19]/a[1]");

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
		
		Thread.sleep(1000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,300)");						//Scrolling down window by 1000 px.
		Thread.sleep(1000);
		CFOcountPOM.readTotalItemsD(driver).click();					//Clicking on Text of total items just to scroll down.
		String s1 = CFOcountPOM.readTotalItemsD(driver).getText();		//Reading total number of items.
		String[] bits = s1.split(" ");									//Splitting the String
		String itomsCount = bits[bits.length - 2];						//Getting the second last word (total number of items)
		
		int count = 0;
		if(itomsCount.equalsIgnoreCase("to"))							//If not items found
		{
			Thread.sleep(2500);
			s1 = CFOcountPOM.readTotalItems(driver).getText();
			bits = s1.split(" ");										//Splitting the String
			itomsCount = bits[bits.length - 2];
		}
		if(itomsCount.equalsIgnoreCase("to"))							//If not items found
		{
			count = 0;
		}
		else
		{
			count = Integer.parseInt(itomsCount);
		}
		
		Thread.sleep(500);
		driver.switchTo().parentFrame();
		CFOcountPOM.closeCategories(driver).click();					//Closing the High Risk Window.
		
		if(count == complianceCount)
		{
			test.log(LogStatus.PASS, "'"+risk+"' risk compliance count matches to numbers of items from grid.");
			test.log(LogStatus.INFO, "'"+risk+"' risk compliance count = " + complianceCount + " | Total number of items from grid = "+count);
		}
		else
		{
			test.log(LogStatus.FAIL, "'"+risk+"' risk compliance count does not matches to numbers of Items.");
			test.log(LogStatus.INFO, "'"+risk+"' risk compliance count = " + complianceCount + " | Total number of items from grid = "+count);
		}
	}
	
	public static void GraphCount1(WebDriver driver, ExtentTest test, String risk, int complianceCount, String Compliance)throws InterruptedException
	{
		Thread.sleep(500);
		if(risk.equalsIgnoreCase("Critical"))
		{
			CFOcountPOM.readCritical(driver).click();					//Clicking on Critical value of Pie Chart of 'Not Completed'.
		}
		else if(risk.equalsIgnoreCase("High"))
		{
			CFOcountPOM.readHigh(driver).click();						//Clicking on High value of Pie Chart of 'Not Completed'.
		}
		else if(risk.equalsIgnoreCase("Medium"))
		{
			CFOcountPOM.readMedium(driver).click();						//Clicking on Medium value of Pie Chart of 'Not Completed'.
		}
		else if(risk.equalsIgnoreCase("Low"))
		{
			CFOcountPOM.readLow(driver).click();						//Clicking on Low value of Pie Chart of 'Not Completed'.
		}
		
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(50));
		
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("showdetails"));	//Wait until frame get visible and switch to it.
		try
		{
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class='k-selectable']")));
			Thread.sleep(5000);
		}
		catch(Exception e)
		{
			
		}
		
		 CFOcountPOM.clickExportImage(driver).click();
			Thread.sleep(4000);
			test.log(LogStatus.PASS, "Excel file Export Successfully");
			Thread.sleep(3000);
			
			 By locator = By.xpath("//*[@id='grid']/div[3]/table/tbody/tr[1]/td[19]/a");

				wait.until(ExpectedConditions.presenceOfElementLocated(locator));
				Thread.sleep(4000);
				// retrieving "foo-button" HTML element
				List<WebElement> ViewButton = driver.findElements(locator);	
				Thread.sleep(3000);
				ViewButton.get(0).click();
				Thread.sleep(4000);
				CFOcountPOM.closeDocument1(driver).click();
				test.log(LogStatus.INFO, "View successfully");
				Thread.sleep(3000);
				
				ViewButton.get(1).click();
				Thread.sleep(4000);
				test.log(LogStatus.INFO, "Download Doc successfully");
				ViewButton.get(2).click();
			//JavascriptExecutor jse=(JavascriptExecutor)driver;
			//jse.executeScript("arguments[0].click();", ViewButton);
				Thread.sleep(4000);
				test.log(LogStatus.INFO, "overView success");
				CFOcountPOM.closeDocument(driver).click();
				Thread.sleep(3000);
		
		Thread.sleep(1000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,300)");						//Scrolling down window by 1000 px.
		
		CFOcountPOM.readTotalItemsD(driver).click();					//Clicking on Text of total items just to scroll down.
		String s1 = CFOcountPOM.readTotalItemsD(driver).getText();		//Reading total number of items.
		String[] bits = s1.split(" ");									//Splitting the String
		String itomsCount = bits[bits.length - 2];						//Getting the second last word (total number of items)
		
		int count = 0;
		if(itomsCount.equalsIgnoreCase("to"))							//If not items found
		{
			Thread.sleep(2500);
			s1 = CFOcountPOM.readTotalItems(driver).getText();
			bits = s1.split(" ");										//Splitting the String
			itomsCount = bits[bits.length - 2];
		}
		if(itomsCount.equalsIgnoreCase("to"))							//If not items found
		{
			count = 0;
		}
		else
		{
			count = Integer.parseInt(itomsCount);
		}
		
		Thread.sleep(500);
		driver.switchTo().parentFrame();
		CFOcountPOM.closeCategories(driver).click();					//Closing the High Risk Window.
		
		if(count == complianceCount)
		{
			test.log(LogStatus.PASS, "'"+risk+"' risk compliance count matches to numbers of items from grid.");
			test.log(LogStatus.INFO, "'"+risk+"' risk compliance count = " + complianceCount + " | Total number of items from grid = "+count);
		}
		else
		{
			test.log(LogStatus.FAIL, "'"+risk+"' risk compliance count does not matches to numbers of Items.");
			test.log(LogStatus.INFO, "'"+risk+"' risk compliance count = " + complianceCount + " | Total number of items from grid = "+count);
		}
	}
	
	
}
