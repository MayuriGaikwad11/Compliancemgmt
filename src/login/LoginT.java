package login;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import com.google.common.collect.Table.Cell;

public class LoginT {
	
	public static FileInputStream file=null;
	public static XSSFWorkbook workbook= null;
	public static XSSFSheet sheet=null;
    
	
	 @Test
	  public void f() throws InterruptedException, IOException {
		  System.setProperty("webdriver.chrome.driver","C:/March2022/PerformerPom/Driver1/chromedriver.exe"); 
		  
		  WebDriver driver=new ChromeDriver();       //Created new Chrome driver instance.
		  
		  driver.manage().window().maximize(); 
		  
		  file= new FileInputStream("C:/March2022/PerformerPom/TestData/ComplianceSheet.xlsx");
		  workbook=new XSSFWorkbook(file);
		  sheet=workbook.getSheetAt(9);
		  
		  Row row0= sheet.getRow(0);
		  org.apache.poi.ss.usermodel.Cell c1= row0.getCell(1);
		  String Url=c1.getStringCellValue();
		  driver.get(Url);
		  
		  Row row1= sheet.getRow(1);
		   c1= row1.getCell(1);
		  String uname=c1.getStringCellValue();
		  WebElement userName=driver.findElement(By.xpath("//*[@id='txtemail']"));
		  userName.sendKeys(uname);
		  
		  Row row2= sheet.getRow(2);
		   c1= row2.getCell(1);
		  String pass=c1.getStringCellValue();
		  WebElement password=driver.findElement(By.xpath("//*[@id='txtpass']"));
		  password.sendKeys(pass);
		  
		  WebElement signBtn=driver.findElement(By.xpath("//*[@id='Submit']"));
		  signBtn.click(); 
		  
		  
		  
		  
		  
		  
		  
	 }
}
