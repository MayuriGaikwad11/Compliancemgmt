package login;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class LoginTest {
	public static FileInputStream fis = null;	//File input stream variable
	public static XSSFWorkbook workbook = null;	//Excel sheet workbook variable
	public static XSSFSheet sheet = null;	
	
	 @Test
	  public void f() throws InterruptedException, IOException {
		  System.setProperty("webdriver.chrome.driver","C:/March2022/PerformerPom/Driver1/chromedriver.exe"); 
		  
		  WebDriver driver=new ChromeDriver();       //Created new Chrome driver instance.
		  
		  driver.manage().window().maximize(); 
		 fis = new FileInputStream("C:/March2022/PerformerPom/TestData/ComplianceSheet.xlsx");
			workbook = new XSSFWorkbook(fis);
			sheet = workbook.getSheetAt(9);					//Retrieving third sheet of Workbook

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
				  signBtn.click();                                                          //Clicked on Sign-in button
				  
			
	 }
}
