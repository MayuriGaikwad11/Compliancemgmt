package licensePerformer;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LiPerformerPOM 
{
	private static WebElement license = null;		
	private static List<WebElement> elementsList = null;	//WebElement list created for selecting Status-Asc/Desc (Status shows multiple elements back side)
	
	public static WebElement clickActive(WebDriver driver)			//Searching 'My Admins' link
	{
		license = driver.findElement(By.xpath("//*[@id='ContentPlaceHolder1_divActiveCount']"));
		return license;
	}
	
	public static WebElement Progress(WebDriver driver)
	{
		license = driver.findElement(By.xpath("//*[@id='imgUpdateProgress']"));
		return license;
	}
	
	public static WebElement clickAction(WebDriver driver)
	{
		license = driver.findElement(By.xpath("//*[@id='ContentPlaceHolder1_grdLicenseList_lnkEditLicense_0']"));
		return license;
	}
	
	public static WebElement clickComplDoc(WebDriver driver)
	{
		license = driver.findElement(By.xpath("//input[@id='TxtUploadocumentlnkLIc']"));
		return license;
	}
	
	public static WebElement clickComplDocInternal(WebDriver driver)
	{
		license = driver.findElement(By.xpath("//input[@id='TxtCompliancedocumentlnk']"));
		return license;
	}
	
	public static WebElement clickComplDocAddButton(WebDriver driver)
	{
		license = driver.findElement(By.xpath("//input[@id='UploadlinkCompliancefile']"));
		return license;
	}
	
	public static WebElement readMessage(WebDriver driver)
	{
		license = driver.findElement(By.xpath("//*[@id='ValidationSummary1']"));
		return license;
	}
	
	public static WebElement clickClose(WebDriver driver)
	{
		license = driver.findElement(By.xpath("//*[@onclick='closeModal();']"));
		return license;
	}
	
	public static WebElement clickPendingForReview(WebDriver driver)		//Searching 'Pending For Review' image link
	{
		license = driver.findElement(By.xpath("//*[@id='ContentPlaceHolder1_divPendingForReview']"));
		return license;
	}
	
	public static WebElement clickApplied(WebDriver driver)					//Searching 'Applied' image link
	{
		license = driver.findElement(By.xpath("//*[@id='ContentPlaceHolder1_divAppliedcount']"));
		return license;
	}
	
	public static WebElement readMsg(WebDriver driver)						//Searching 'Message' after submit
	{
		license = driver.findElement(By.xpath("//*[@id='VSLicensePopup']"));
		return license;
	}
	
	public static WebElement clickExpiring(WebDriver driver)				//Searching 'Expiring' image to click
	{
		license = driver.findElement(By.xpath("//*[@id='ContentPlaceHolder1_divExpiringcount']"));
		return license;
	}
	
	public static WebElement clickCheckbox(WebDriver driver)				//Searching 'Checkbox' to click
	{
		license = driver.findElement(By.xpath("//*[@id='chkPenaltySave']"));
		return license;
	}
	
	public static WebElement clickExpired(WebDriver driver)					//Searching 'Expired' image link to click
	{
		license = driver.findElement(By.xpath("//*[@id='ContentPlaceHolder1_divExpiredCount']"));
		return license;
	}
	
	public static WebElement clickType(WebDriver driver)
	{
		license = driver.findElement(By.xpath("//*[@id='ContentPlaceHolder1_ddlComplianceType']"));
		return license;
	}
	
	public static List<WebElement> clickDownload(WebDriver driver)		//Searching 'Download' button to click.
	{
		elementsList = driver.findElements(By.xpath("//*[contains(@id,'ContentPlaceHolder1_grdLicenseList_lblDownLoadfile')]"));
		return elementsList;
	}
	
	public static List<WebElement> clickDownload1(WebDriver driver)		//Searching 'Download' button to click.
	{
		elementsList = driver.findElements(By.xpath("//*[@class='k-grid-content k-auto-scrollable']/table/tbody/tr/td[9]/a[1]"));
		return elementsList;
	}
	
	public static WebElement clickMyDocuments(WebDriver driver)			//Searching 'My Documents' link
	{
		license = driver.findElement(By.xpath("//*[@id='leftdocumentsmenu']"));
		return license;
	}
	
	public static WebElement clickMyDocumentsMenu(WebDriver driver)			//Searching 'My Documents' link
	{
		license = driver.findElement(By.xpath("//li[@id='leftdocumentsmenu']/ul/li[1]"));
		return license;
	}
	
	public static WebElement clickMyReport(WebDriver driver)
	{
		license = driver.findElement(By.xpath("//*[@id='leftreportsmenu']"));
		return license;
	}
	
	public static WebElement clickStatus(WebDriver driver)
	{
		license = driver.findElement(By.xpath("//*[@id='ContentPlaceHolder1_ddlLicenseStatus_chosen']"));
		return license;
	}
	
	public static WebElement clickStatus1(WebDriver driver)
	{
		license = driver.findElement(By.xpath("//*[@aria-owns='dropdownlistStatus_listbox']"));
		return license;
	}
	
	public static List<WebElement> selectStatus1(WebDriver driver)
	{
		elementsList = driver.findElements(By.xpath("//div[@id='dropdownlistStatus-list']/div[3]/ul/li"));
		return elementsList;
	}
	
	public static List<WebElement> selectStatus(WebDriver driver)
	{
		elementsList = driver.findElements(By.xpath("//*[@id='ContentPlaceHolder1_ddlLicenseStatus_chosen']/div/ul/li"));
		return elementsList;
	}
	
	public static WebElement clickApply(WebDriver driver)
	{
		license = driver.findElement(By.xpath("//*[@id='ContentPlaceHolder1_lnkBtnApplyFilter']"));
		return license;
	}
	
	public static WebElement clickExcel(WebDriver driver)
	{
		license = driver.findElement(By.xpath("//*[@id='exportReport']"));
		return license;
	}
	
	public static WebElement clickView(WebDriver driver)
	{
		license = driver.findElement(By.xpath("//*[@id='ContentPlaceHolder1_grdLicenseList_lnkEditLicense_0']"));
		return license;
	}
	
	public static WebElement clickType1(WebDriver driver)
	{
		license = driver.findElement(By.xpath("//*[@id='ContentPlaceHolder1_ddlComplianceType']"));
		return license;
	}
	
	public static WebElement clickType2(WebDriver driver)
	{
		license = driver.findElement(By.xpath("//*[@aria-owns='dropdownlistComplianceType_listbox']"));
		return license;
	}
	
	public static WebElement selectInternal(WebDriver driver)
	{
		license = driver.findElement(By.xpath("//*[@id='dropdownlistComplianceType_listbox']/li[2]"));
		return license;
	}
	
	public static WebElement checkTable(WebDriver driver)
	{
		license = driver.findElement(By.xpath("//*[@id='ContentPlaceHolder1_grdLicenseList']"));
		return license;
	}
	
	public static WebElement readTotalRecords(WebDriver driver)
	{
		license = driver.findElement(By.xpath("//*[@id='ContentPlaceHolder1_lblTotalRecord']"));
		return license;
	}
	
	public static WebElement readTotalRecords1(WebDriver driver)
	{
		license = driver.findElement(By.xpath("//span[@class='k-pager-info k-label']"));
		return license;
	}
	
	public static WebElement clickAon(WebDriver driver)
	{
		license = driver.findElement(By.xpath(""));
		return license;
	}
	
	public static WebElement clickAyn(WebDriver driver)
	{
		license = driver.findElement(By.xpath(""));
		return license;
	}
	
	public static WebElement clickAyen(WebDriver driver)
	{
		license = driver.findElement(By.xpath(""));
		return license;
	}
	
	public static WebElement clickAeons(WebDriver driver)
	{
		license = driver.findElement(By.xpath(""));
		return license;
	}
}
