package licenseReviewer;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LiReviewerPOM 
{
	private static WebElement license = null;		
	private static List<WebElement> elementsList = null;	//WebElement list created for selecting Status-Asc/Desc (Status shows multiple elements back side)
	
	public static WebElement checkTable(WebDriver driver)
	{
		license = driver.findElement(By.xpath("//*[@id='ContentPlaceHolder1_grdLicenseList']"));
		return license;
	}
	
	public static WebElement checkTable1(WebDriver driver)
	{
		license = driver.findElement(By.xpath("//*[@class='k-grid-content k-auto-scrollable']/table/tbody/tr[1]"));
		return license;
	}
	
	public static List<WebElement> clickAction(WebDriver driver)
	{
		elementsList = driver.findElements(By.xpath("//*[contains(@id,'ContentPlaceHolder1_grdLicenseList_lnkEditLicense')]"));
		return elementsList;
	}
	
	public static WebElement clickIndexDropDown(WebDriver driver)
	{
		license = driver.findElement(By.xpath("//div[@id='ContentPlaceHolder1_DropDownListPageNo_chosen']"));
		return license;
	}
	
	public static List<WebElement> clickIndexDropDownOption(WebDriver driver)
	{
		elementsList = driver.findElements(By.xpath("//div[@id='ContentPlaceHolder1_DropDownListPageNo_chosen']/div/ul/li"));
		return elementsList;
	}
	
	public static WebElement clickLicenseNo(WebDriver driver)
	{
		license = driver.findElement(By.xpath("//*[@id='txtLicenseNo']"));
		return license;
	}
	
	public static WebElement clickLicenseTitle(WebDriver driver)
	{
		license = driver.findElement(By.xpath("//*[@id='txtLicenseTitle']"));
		return license;
	}
	
	public static WebElement clickStartDate(WebDriver driver)
	{
		license = driver.findElement(By.xpath("//*[@id='txtStartDate']"));
		return license;
	}
	
	public static WebElement clickEndDate(WebDriver driver)
	{
		license = driver.findElement(By.xpath("//*[@id='txtEndDate']"));
		return license;
	}
	
	public static WebElement clickDate(WebDriver driver)
	{
		license = driver.findElement(By.xpath("//*[@id='tbxDate1']"));
		return license;
	}
	
	public static WebElement clickDate1(WebDriver driver)
	{
		license = driver.findElement(By.xpath("//*[@id='tbxDate']"));
		return license;
	}
	
	public static WebElement clikTextArea(WebDriver driver)
	{
		license = driver.findElement(By.xpath("//*[@id='tbxRemarks1']"));
		return license;
	}
	
	public static WebElement clikTextArea1(WebDriver driver)
	{
		license = driver.findElement(By.xpath("//*[@id='tbxRemarks3']"));
		return license;
	}
	
	public static WebElement clickReviewer(WebDriver driver)
	{
		license = driver.findElement(By.xpath("//a[@id='ContentPlaceHolder1_liReviewer']"));
		return license;
	}
	
	public static WebElement clickin(WebDriver driver)
	{
		license = driver.findElement(By.xpath(""));
		return license;
	}
	
	public static WebElement clikAiorn(WebDriver driver)
	{
		license = driver.findElement(By.xpath(""));
		return license;
	}
	
	public static WebElement clictor(WebDriver driver)
	{
		license = driver.findElement(By.xpath(""));
		return license;
	}
	
	public static WebElement clickirn(WebDriver driver)
	{
		license = driver.findElement(By.xpath(""));
		return license;
	}
}
