package litigationPerformer;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import cfo.CFOcountPOM;
import licensePerformer.LiPerformerPOM;
import performer.OverduePOM;

public class MethodsPOM 
{
	private static List<WebElement> elementsList = null;
	
	public static FileInputStream fis = null;	//File input stream variable
	public static XSSFWorkbook workbook = null;	//Excel sheet workbook variable
	public static XSSFSheet sheet = null;		//Sheet variable
	public static XSSFSheet sheet1 = null;		//Sheet variable
	
	public static void progress(WebDriver driver) throws InterruptedException
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		try
		{
			Thread.sleep(300);
			wait.until(ExpectedConditions.invisibilityOf(LiPerformerPOM.Progress(driver)));
		}
		catch(Exception e)
		{
			
		}
	}
	
	static void perform(WebDriver driver, ExtentTest test, XSSFSheet sheet, int open, int gridRecords, String type) throws InterruptedException
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(30));
		
		Thread.sleep(500);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		CFOcountPOM.clickNextPage1(driver).sendKeys(Keys.UP);
		js.executeScript("window.scrollBy(0,-700)");
		performerPOM.clickNew(driver).click();						//Clicking on 'New' button
		
		progress(driver);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("showdetails"));
		
		performerPOM.clickDated(driver).click();					//Clicking on 'Dated' button
		OverduePOM.selectLastMonth(driver).click();					//Clicking last month arrow.
		OverduePOM.selectDate3(driver).click();						//Clicking particular date.
		
		Thread.sleep(300);
		performerPOM.clickFinancialYear(driver).click();			//Clicking on 'Financial Year' drop down.
		elementsList = performerPOM.chooseDropDownOption(driver);
		elementsList.get(2).click();								//Clicking third option
		performerPOM.clickFinancialYear(driver).click();			//Clicking on 'Financial Year' drop down.
		
		Thread.sleep(500);
		Row row0 = sheet.getRow(0);						//Selected 0th index row (First row)
		Cell c1 = row0.getCell(1);						//Selected cell (0 row,1 column)
		String refno = c1.getStringCellValue();
		performerPOM.clickRefNo(driver).sendKeys(refno);			//Writing 'Reference No'
		
		Thread.sleep(300);
		row0 = sheet.getRow(1);						//Selected 0th index row (First row)
		c1 = row0.getCell(1);						//Selected cell (0 row,1 column)
		String noticeType = c1.getStringCellValue();
		performerPOM.clickNoticeType(driver).click();
		performerPOM.clickSearch(driver).sendKeys(noticeType, Keys.ENTER);	//Writing 'Notice Type'
		
		Thread.sleep(300);
		progress(driver);
		
		Thread.sleep(300);
		row0 = sheet.getRow(2);						//Selected 0th index row (First row)
		c1 = row0.getCell(1);						//Selected cell (0 row,1 column)
		int actNo = (int) c1.getNumericCellValue();
		performerPOM.clickAct(driver).click();						//Clicking on 'Act' drop down.
		elementsList = performerPOM.chooseAct(driver);
		elementsList.get(actNo).click();							//Selecting particular act no
		performerPOM.clickAct(driver).click();						//Clicking on 'Act' drop down.
		
		Thread.sleep(300);
		row0 = sheet.getRow(3);						//Selected 0th index row (First row)
		c1 = row0.getCell(1);						//Selected cell (0 row,1 column)
		String underSection = c1.getStringCellValue();
		performerPOM.clickUnderSection(driver).sendKeys(underSection);	//Writing 'Under section'
		
		Thread.sleep(300);
		row0 = sheet.getRow(4);						//Selected 0th index row (First row)
		c1 = row0.getCell(1);						//Selected cell (0 row,1 column)
		String opponent = c1.getStringCellValue();
		performerPOM.clickOpponent(driver).click();					//Clicking on 'Opponent'
		performerPOM.clickSearch1(driver).sendKeys(opponent);		//Writing 'Opponent' name
		Thread.sleep(300);
		performerPOM.clickSelectAll(driver).click();
		performerPOM.clickOpponent(driver).click();
		
		Thread.sleep(300);
		row0 = sheet.getRow(5);						//Selected 0th index row (First row)
		c1 = row0.getCell(1);						//Selected cell (0 row,1 column)
		String oppoLawyer = c1.getStringCellValue();
		performerPOM.clickOppLawyer(driver).click();				//Clicking on 'Opponent'
		performerPOM.clickSearch2(driver).sendKeys(oppoLawyer);		//Writing 'Opposition Lawyer' name
		Thread.sleep(300);
		performerPOM.clickSelectAll1(driver).click();
		performerPOM.clickOppLawyer(driver).click();
		
		Thread.sleep(300);
		row0 = sheet.getRow(6);						//Selected 0th index row (First row)
		c1 = row0.getCell(1);						//Selected cell (0 row,1 column)
		String title = c1.getStringCellValue();
		performerPOM.clickNoticeTitle(driver).sendKeys(title);		//Writing 'Notice Title'
		
		Thread.sleep(300);
		row0 = sheet.getRow(7);						//Selected 0th index row (First row)
		c1 = row0.getCell(1);						//Selected cell (0 row,1 column)
		String desc = c1.getStringCellValue();
		performerPOM.clickNoticeDescription(driver).sendKeys(desc);	//Writing 'Notice Description'
		
		Thread.sleep(300);		
		performerPOM.clickNoticeDescription(driver).sendKeys(Keys.PAGE_DOWN);
		
		Thread.sleep(300);
		performerPOM.clickLocation(driver).click();					//Clicking on Location drop down
		Thread.sleep(300);
		//performerPOM.clickPlus(driver).click();
		elementsList = performerPOM.selectLocation(driver);
		elementsList.get(2).click();								//Selecting third visible location
		
		Thread.sleep(300);
		wait.until(ExpectedConditions.visibilityOf(performerPOM.clickDated(driver)));
		wait.until(ExpectedConditions.elementToBeClickable(performerPOM.clickLocation(driver)));
		
		performerPOM.clickNoticeDescription(driver).sendKeys(Keys.PAGE_DOWN);
		
		Thread.sleep(300);
		row0 = sheet.getRow(8);						//Selected 0th index row (First row)
		c1 = row0.getCell(1);						//Selected cell (0 row,1 column)
		String jurisdiction = c1.getStringCellValue();
		performerPOM.clickJurisdiction(driver).click();					//Clicking on 'Jurisdiction' drop down
		Thread.sleep(600);
		performerPOM.clickSearch3(driver).sendKeys(jurisdiction, Keys.ENTER);	//Writing 'Jurisdiction' name
		
		Thread.sleep(300);
		row0 = sheet.getRow(9);						//Selected 0th index row (First row)
		c1 = row0.getCell(1);						//Selected cell (0 row,1 column)
		String dept = c1.getStringCellValue();
		performerPOM.clickDepartment(driver).click();					//Clicking on 'Department' drop down
		performerPOM.clickSearch4(driver).sendKeys(dept, Keys.ENTER);	//Writing 'Department' name
		
		Thread.sleep(300);
		row0 = sheet.getRow(10);					//Selected 0th index row (First row)
		c1 = row0.getCell(1);						//Selected cell (0 row,1 column)
		String ContactDept = c1.getStringCellValue();
		performerPOM.clickContactDept(driver).click();					//Clicking on 'Contact Person of Department' drop down
		performerPOM.clickSearch5(driver).sendKeys(ContactDept, Keys.ENTER);	//Writing 'Contact Person' name
		
		Thread.sleep(300);
		row0 = sheet.getRow(11);					//Selected 0th index row (First row)
		c1 = row0.getCell(1);						//Selected cell (0 row,1 column)
		int noticeTerm = (int) c1.getNumericCellValue();
		performerPOM.clickNoticeTerm(driver).sendKeys(noticeTerm+"");		//Writing 'Notice Term'
		
		Thread.sleep(300);
		row0 = sheet.getRow(12);					//Selected 0th index row (First row)
		c1 = row0.getCell(1);						//Selected cell (0 row,1 column)
		String owner = c1.getStringCellValue();
		performerPOM.clickOwner(driver).click();					//Clicking on 'Owner' drop down
		performerPOM.clickSearch6(driver).sendKeys(owner, Keys.ENTER);	//Writing 'Owner' name
		
		Thread.sleep(300);
		performerPOM.clickWinningProspect(driver).click();
		Thread.sleep(100);
		performerPOM.selectRisk(driver).click();	//Selecting 'Medium' Winning Prospect'
		
		Thread.sleep(300);
		row0 = sheet.getRow(13);					//Selected 0th index row (First row)
		c1 = row0.getCell(1);						//Selected cell (0 row,1 column)
		int noticeBudget = (int) c1.getNumericCellValue();
		performerPOM.clickNoticeBudget(driver).sendKeys(noticeBudget+"");	//Writing 'Notice Budget'
		
		Thread.sleep(300);
		row0 = sheet.getRow(14);					//Selected 0th index row (First row)
		c1 = row0.getCell(1);						//Selected cell (0 row,1 column)
		int claimedAmount = (int) c1.getNumericCellValue();
		performerPOM.clickClaimedAmount(driver).sendKeys(claimedAmount+"");	//Writing 'Claimed Amount'
		
		Thread.sleep(300);
		row0 = sheet.getRow(15);					//Selected 0th index row (First row)
		c1 = row0.getCell(1);						//Selected cell (0 row,1 column)
		String state = c1.getStringCellValue();
		performerPOM.clickState(driver).click();					//Clicking on 'Owner' drop down
		performerPOM.clickSearchState(driver).sendKeys(state, Keys.ENTER);	//Writing 'State' name
		
		Thread.sleep(300);
		row0 = sheet.getRow(16);					//Selected 0th index row (First row)
		c1 = row0.getCell(1);						//Selected cell (0 row,1 column)
		int probAmount = (int) c1.getNumericCellValue();
		performerPOM.clickProbableAmount(driver).sendKeys(probAmount+"");	//Writing 'Probable Amount'
		
		Thread.sleep(300);
		row0 = sheet.getRow(17);					//Selected 0th index row (First row)
		c1 = row0.getCell(1);						//Selected cell (0 row,1 column)
		int provAmount = (int) c1.getNumericCellValue();
		performerPOM.clickProvisionalAmount(driver).sendKeys(provAmount+"");	//Writing 'Provisional Amount'
		
		Thread.sleep(300);
		row0 = sheet.getRow(18);					//Selected 0th index row (First row)
		c1 = row0.getCell(1);						//Selected cell (0 row,1 column)
		int protestAmount = (int) c1.getNumericCellValue();
		performerPOM.clickProtestMoney(driver).sendKeys(protestAmount+"");	//Writing 'Protest Amount'
		
		Thread.sleep(500);
		performerPOM.clickProtestMoney(driver).sendKeys(Keys.PAGE_DOWN);
		
		Thread.sleep(500);
		performerPOM.clickRisk(driver).click();							//Clicking on 'Risk' drop down.
		Thread.sleep(300);
		performerPOM.selectRisk2(driver).click();						//Selecting second option 'High' risk.
		
		Thread.sleep(500);
		performerPOM.clickPotentialImpactRadio(driver).click();			//Clicking on 'Monetary' radio button
		
		Thread.sleep(400);
		performerPOM.clickMonetary(driver).sendKeys("Automation123");
		
		Thread.sleep(300);
		row0 = sheet.getRow(19);					//Selected 0th index row (First row)
		c1 = row0.getCell(1);						//Selected cell (0 row,1 column)
		String lawFirm = c1.getStringCellValue();
		performerPOM.clickLawFirm(driver).click();		//Clicking on 'Law Firm' drop down.
		performerPOM.chooseLawFirm(driver).sendKeys(lawFirm, Keys.DOWN, Keys.ENTER);	//Writing & selecting 'Law Firm' name
		
		Thread.sleep(300);
		progress(driver);
		
		Thread.sleep(300);
		row0 = sheet.getRow(20);						//Selected 0th index row (First row)
		c1 = row0.getCell(1);						//Selected cell (0 row,1 column)
		int internalUserNo = (int) c1.getNumericCellValue();
		performerPOM.clickInternalUser(driver).click();						//Clicking on 'Internal User' drop down.
		elementsList = performerPOM.chooseInternalUser(driver);
		elementsList.get(internalUserNo).click();							//Selecting particular user no
		performerPOM.clickInternalUser(driver).click();						//Clicking on 'Internal User' drop down.
		
		Thread.sleep(300);
		row0 = sheet.getRow(21);						//Selected 0th index row (First row)
		c1 = row0.getCell(1);						//Selected cell (0 row,1 column)
		int lawyerNo = (int) c1.getNumericCellValue();
		performerPOM.clickLawyer(driver).click();						//Clicking on 'Lawyer' drop down.
		elementsList = performerPOM.chooseLawyer(driver);
		elementsList.get(lawyerNo).click();								//Selecting particular lawyer no
		performerPOM.clickLawyer(driver).click();						//Clicking on 'Lawyer' drop down.
		
		Thread.sleep(500);
		OverduePOM.clickSaveButton(driver).click();						//Clicking on 'Save'button.
		
		Thread.sleep(500);
		wait1.until(ExpectedConditions.visibilityOf(performerPOM.readMessage(driver)));
		
		Thread.sleep(500);
		String msg = performerPOM.readMessage(driver).getText();		//Reading Message appeared after save button
		int flag = 0;
		if(msg.equalsIgnoreCase("Notice Created Successfully."))
		{
			test.log(LogStatus.PASS, "Message displayed = "+msg);
			flag = 1;
		}
		else
		{
			test.log(LogStatus.FAIL, "Message displayed = "+msg);
		}
		
		WebElement ele1 = null;
		WebElement ele2 = null;
		WebElement ele3 = null;
		WebElement ele4 = null;
		
		if(flag == 1)
		{
			try
			{
				ele1 = performerPOM.clickLinkNotice(driver);
				ele2 = performerPOM.clickViewDoc(driver);
				ele3 = performerPOM.clickSendMail(driver);
				ele4 = performerPOM.clickEditNotice(driver);
			}
			catch(Exception e)
			{
				
			}
			
			if(ele1 != null && ele2 != null && ele3 != null && ele4 != null)
			{
				test.log(LogStatus.PASS, "Icons displayed are :- Link Notice, View Document, Send Mail with Document, Edit Notice");
			}
			else
			{
				test.log(LogStatus.FAIL, "All icons are not displayed.");
			}
		}
		
		driver.switchTo().parentFrame();
		performerPOM.clickClose(driver).click();			//Clicking on 'Close'
		
		Thread.sleep(500);
		performerPOM.clickExcelReport(driver).sendKeys(Keys.PAGE_DOWN);
		js.executeScript("window.scrollBy(0,700)");
		
		Thread.sleep(1000);
		CFOcountPOM.readTotalItems1(driver).click();
		String item = CFOcountPOM.readTotalItems1(driver).getText();
		String[] bits = item.split(" ");								//Splitting the String
		String compliancesCount = bits[bits.length - 2];				//Getting the second last word (total number of users)
		int count1 = 0;
		if(compliancesCount.equalsIgnoreCase("to"))
		{
			Thread.sleep(2000);
			item = CFOcountPOM.readTotalItems1(driver).getText();
			bits = item.split(" ");								//Splitting the String
			compliancesCount = bits[bits.length - 2];
		}
		count1 = Integer.parseInt(compliancesCount);
		
		if(count1 > gridRecords)
		{
			test.log(LogStatus.PASS, "Total Notice Count increased in grid after adding New Notice.");
			test.log(LogStatus.INFO, "Old Notice Count from Grid = "+gridRecords+" | New Notice Count from Grid = "+count1);
		}
		else
		{
			test.log(LogStatus.FAIL, "Total Notice Count doesn't increased in grid after adding New Notice.");
			test.log(LogStatus.INFO, "Old Notice Count from Grid = "+gridRecords+" | New Notice Count from Grid = "+count1);
		}
		
		Thread.sleep(500);
		OverduePOM.clickDashboard(driver).click();			//Clicking on 'Dashboard'
		
		Thread.sleep(500);
		wait.until(ExpectedConditions.visibilityOf(performerPOM.clickNoticeOpen(driver)));
		int open1 = 0;
		if(type.equalsIgnoreCase("Notice - Open"))
		{
			open1 = Integer.parseInt(performerPOM.clickNoticeOpen(driver).getText());	//Reading Notice Open count.
		}
		else
		{
			open1 = Integer.parseInt(performerPOM.clickNoticeClosed(driver).getText());	//Reading Notice Open count.
		}
		
		if(open1 > open)
		{
			test.log(LogStatus.PASS, type+" Dashboard Count increamented. Old count = "+open+", New Count = "+open1);
		}
		else
		{
			test.log(LogStatus.FAIL, type+" Dashboard Count doesn't increamented. Old count = "+open+", New Count = "+open1);
		}
	}
	
	static void perform1(WebDriver driver, ExtentTest test, XSSFSheet sheet, int open, int gridRecords, String type) throws InterruptedException
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(30));
		
		Thread.sleep(500);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		CFOcountPOM.clickNextPage1(driver).sendKeys(Keys.UP);
		js.executeScript("window.scrollBy(0,-700)");
		performerPOM.clickNew(driver).click();						//Clicking on 'New' button
		
		progress(driver);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("showdetails"));
		
		performerPOM.clickCaseDate(driver).click();					//Clicking on 'Dated' button
		OverduePOM.selectLastMonth(driver).click();					//Clicking last month arrow.
		OverduePOM.selectDate3(driver).click();						//Clicking particular date.
		
		Thread.sleep(300);
		performerPOM.clickFinancialYear(driver).click();			//Clicking on 'Financial Year' drop down.
		elementsList = performerPOM.clickFinanceSearchCheckbox(driver);
		elementsList.get(2).click();								//Clicking third option
		performerPOM.clickFinancialYear(driver).click();			//Clicking on 'Financial Year' drop down.
		
		Thread.sleep(300);
		Row row0 = sheet.getRow(0);								//Selected 0th index row (First row)
		Cell c1 = row0.getCell(1);								//Selected cell (0 row,1 column)
		String refno = c1.getStringCellValue();
		performerPOM.clickRefNo(driver).sendKeys(refno);			//Writing 'Court Case No'
		
		Thread.sleep(300);
		row0 = sheet.getRow(1);								//Selected 0th index row (First row)
		c1 = row0.getCell(1);								//Selected cell (0 row,1 column)
		String caseNo = c1.getStringCellValue();
		performerPOM.clickInternalCaseNo(driver).sendKeys(caseNo);	//Writing 'Court Case No'
		
		Thread.sleep(300);
		row0 = sheet.getRow(2);								//Selected 0th index row (First row)
		c1 = row0.getCell(1);								//Selected cell (0 row,1 column)
		String title = c1.getStringCellValue();
		performerPOM.clickNoticeTitle(driver).sendKeys(title);		//Writing 'Case Title'
		
		Thread.sleep(300);
		row0 = sheet.getRow(3);								//Selected 0th index row (First row)
		c1 = row0.getCell(1);								//Selected cell (0 row,1 column)
		int actNo = (int) c1.getNumericCellValue();
		performerPOM.clickAct(driver).click();						//Clicking on 'Act' drop down.
		elementsList = performerPOM.chooseAct1(driver);
		elementsList.get(actNo).click();							//Selecting particular act no
		performerPOM.clickAct(driver).click();						//Clicking on 'Act' drop down.
		
		Thread.sleep(300);
		row0 = sheet.getRow(4);								//Selected 0th index row (First row)
		c1 = row0.getCell(1);								//Selected cell (0 row,1 column)
		String underSection = c1.getStringCellValue();
		performerPOM.clickUnderSection(driver).sendKeys(underSection);	//Writing 'Under section'
		
		Thread.sleep(300);
		row0 = sheet.getRow(5);								//Selected 0th index row (First row)
		c1 = row0.getCell(1);								//Selected cell (0 row,1 column)
		String caseType = c1.getStringCellValue();
		performerPOM.clickCaseType(driver).click();
		performerPOM.clickSearchCaseType(driver).sendKeys(caseType, Keys.ENTER);	//Writing 'Case Type'
		
		Thread.sleep(500);
		progress(driver);
		
		Thread.sleep(300);
		row0 = sheet.getRow(6);								//Selected 0th index row (First row)
		c1 = row0.getCell(1);								//Selected cell (0 row,1 column)
		int caseBudget = (int) c1.getNumericCellValue();
		performerPOM.clickCaseBudget(driver).sendKeys(caseBudget+"");
		
		Thread.sleep(300);
		row0 = sheet.getRow(7);								//Selected 0th index row (First row)
		c1 = row0.getCell(1);								//Selected cell (0 row,1 column)
		String opponent = c1.getStringCellValue();
		performerPOM.clickOpponent(driver).click();					//Clicking on 'Opponent'
		performerPOM.clickSearchBox(driver).sendKeys(opponent);		//Writing 'Opponent' name
		Thread.sleep(300);
		performerPOM.clickSelectAll2(driver).click();
		performerPOM.clickOpponent(driver).click();
		
		Thread.sleep(300);
		row0 = sheet.getRow(8);								//Selected 0th index row (First row)
		c1 = row0.getCell(1);								//Selected cell (0 row,1 column)
		String oppoLawyer = c1.getStringCellValue();
		performerPOM.clickOppLawyer(driver).click();				//Clicking on 'Opponent'
		performerPOM.clickSearchBox1(driver).sendKeys(oppoLawyer);	//Writing 'Opposition Lawyer' name
		Thread.sleep(300);
		performerPOM.clickSelectAll3(driver).click();
		performerPOM.clickOppLawyer(driver).click();
		
		Thread.sleep(300);
		row0 = sheet.getRow(9);								//Selected 0th index row (First row)
		c1 = row0.getCell(1);								//Selected cell (0 row,1 column)
		String court = c1.getStringCellValue();
		performerPOM.clickCourt(driver).click();
		performerPOM.clickSearchCourt(driver).sendKeys(court, Keys.ENTER);
		
		Thread.sleep(300);
		row0 = sheet.getRow(10);							//Selected 0th index row (First row)
		c1 = row0.getCell(1);								//Selected cell (0 row,1 column)
		String judge = c1.getStringCellValue();
		performerPOM.clickJudge(driver).sendKeys(judge);
		
		Thread.sleep(300);		
		performerPOM.clickCaseBudget(driver).sendKeys(Keys.PAGE_DOWN);
		
		Thread.sleep(300);
		row0 = sheet.getRow(11);							//Selected 0th index row (First row)
		c1 = row0.getCell(1);								//Selected cell (0 row,1 column)
		String caseDesc = c1.getStringCellValue();
		performerPOM.clickNoticeDescription(driver).sendKeys(caseDesc);
		
		Thread.sleep(300);
		performerPOM.clickLocation(driver).click();					//Clicking on Location drop down
		//performerPOM.clickPlus(driver).click();
		Thread.sleep(300);
		elementsList = performerPOM.selectLocation(driver);
		elementsList.get(2).click();								//Selecting third visible location
		
		Thread.sleep(700);
		wait.until(ExpectedConditions.visibilityOf(performerPOM.clickCaseDate(driver)));
		wait.until(ExpectedConditions.elementToBeClickable(performerPOM.clickCaseDate(driver)));
		
		Thread.sleep(700);
		js.executeScript("window.scrollBy(0,600)");
		
		Thread.sleep(300);
		row0 = sheet.getRow(12);							//Selected 0th index row (First row)
		c1 = row0.getCell(1);								//Selected cell (0 row,1 column)
		String jurisdiction = c1.getStringCellValue();
		performerPOM.clickJurisdiction(driver).click();					//Clicking on 'Jurisdiction' drop down
		Thread.sleep(600);
		performerPOM.clickSearch3(driver).sendKeys(jurisdiction, Keys.ENTER);	//Writing 'Jurisdiction' name
		
		Thread.sleep(300);
		row0 = sheet.getRow(13);							//Selected 0th index row (First row)
		c1 = row0.getCell(1);								//Selected cell (0 row,1 column)
		String dept = c1.getStringCellValue();
		performerPOM.clickDepartment(driver).click();					//Clicking on 'Department' drop down
		performerPOM.clickSearch4(driver).sendKeys(dept, Keys.ENTER);	//Writing 'Department' name
		
		Thread.sleep(300);
		row0 = sheet.getRow(14);							//Selected 0th index row (First row)
		c1 = row0.getCell(1);								//Selected cell (0 row,1 column)
		String ContactDept = c1.getStringCellValue();
		performerPOM.clickContactDept(driver).click();					//Clicking on 'Contact Person of Department' drop down
		performerPOM.clickSearch5(driver).sendKeys(ContactDept, Keys.ENTER);	//Writing 'Contact Person' name
		
		Thread.sleep(300);
		row0 = sheet.getRow(15);							//Selected 0th index row (First row)
		c1 = row0.getCell(1);								//Selected cell (0 row,1 column)
		String owner = c1.getStringCellValue();
		performerPOM.clickOwner(driver).click();					//Clicking on 'Owner' drop down
		performerPOM.clickSearch6(driver).sendKeys(owner, Keys.ENTER);	//Writing 'Owner' name
		
		Thread.sleep(300);
		performerPOM.clickWinningProspect1(driver).click();
		Thread.sleep(100);
		performerPOM.selectRisk1(driver).click();			//Selecting 'Medium' Winning Prospect'
		
		Thread.sleep(300);
		row0 = sheet.getRow(16);					//Selected 0th index row (First row)
		c1 = row0.getCell(1);						//Selected cell (0 row,1 column)
		int claimedAmount = (int) c1.getNumericCellValue();
		performerPOM.clickClaimedAmount(driver).sendKeys(claimedAmount+"");	//Writing 'Claimed Amount'
		
		Thread.sleep(300);
		row0 = sheet.getRow(17);					//Selected 0th index row (First row)
		c1 = row0.getCell(1);						//Selected cell (0 row,1 column)
		int probAmount = (int) c1.getNumericCellValue();
		performerPOM.clickProbableAmount(driver).sendKeys(probAmount+"");	//Writing 'Probable Amount'
		
		Thread.sleep(300);
		row0 = sheet.getRow(18);					//Selected 0th index row (First row)
		c1 = row0.getCell(1);						//Selected cell (0 row,1 column)
		int provAmount = (int) c1.getNumericCellValue();
		performerPOM.clickProvisionalAmount(driver).sendKeys(provAmount+"");	//Writing 'Provisional Amount'
		
		Thread.sleep(300);
		row0 = sheet.getRow(19);					//Selected 0th index row (First row)
		c1 = row0.getCell(1);						//Selected cell (0 row,1 column)
		int protestAmount = (int) c1.getNumericCellValue();
		performerPOM.clickProtestMoney(driver).sendKeys(protestAmount+"");	//Writing 'Protest Amount'
		
		Thread.sleep(500);
		performerPOM.clickProtestMoney(driver).sendKeys(Keys.PAGE_DOWN);
		
		Thread.sleep(500);
		performerPOM.clickPotentialImpactRadio(driver).click();			//Clicking on 'Monetary' radio button
		
		Thread.sleep(400);
		performerPOM.clickMonetary(driver).sendKeys("Automation123");
		
		Thread.sleep(300);
		row0 = sheet.getRow(20);					//Selected 0th index row (First row)
		c1 = row0.getCell(1);						//Selected cell (0 row,1 column)
		String state = c1.getStringCellValue();
		performerPOM.clickState(driver).click();					//Clicking on 'Owner' drop down
		performerPOM.clickSearchState(driver).sendKeys(state, Keys.ENTER);	//Writing 'State' name
		
		Thread.sleep(300);
		row0 = sheet.getRow(21);					//Selected 0th index row (First row)
		c1 = row0.getCell(1);						//Selected cell (0 row,1 column)
		String lawFirm = c1.getStringCellValue();
		performerPOM.clickLawFirm(driver).click();		//Clicking on 'Law Firm' drop down.
		performerPOM.chooseLawFirm(driver).sendKeys(lawFirm, Keys.DOWN, Keys.ENTER);	//Writing & selecting 'Law Firm' name
		
		Thread.sleep(300);
		progress(driver);
		
		Thread.sleep(500);
		performerPOM.clickRisk(driver).click();							//Clicking on 'Risk' drop down.
		Thread.sleep(300);
		performerPOM.selectRisk2(driver).click();						//Selecting second option 'High' risk.
		
		Thread.sleep(300);
		row0 = sheet.getRow(22);						//Selected 0th index row (First row)
		c1 = row0.getCell(1);						//Selected cell (0 row,1 column)
		int internalUserNo = (int) c1.getNumericCellValue();
		performerPOM.clickInternalUser(driver).click();						//Clicking on 'Internal User' drop down.
		elementsList = performerPOM.chooseInternalUser1(driver);
		elementsList.get(internalUserNo).click();							//Selecting particular user no
		performerPOM.clickInternalUser(driver).click();						//Clicking on 'Internal User' drop down.
		
		Thread.sleep(300);
		row0 = sheet.getRow(23);						//Selected 0th index row (First row)
		c1 = row0.getCell(1);						//Selected cell (0 row,1 column)
		int lawyerNo = (int) c1.getNumericCellValue();
		performerPOM.clickLawyer(driver).click();						//Clicking on 'Lawyer' drop down.
		elementsList = performerPOM.chooseLawyer(driver);
		elementsList.get(lawyerNo).click();								//Selecting particular lawyer no
		performerPOM.clickLawyer(driver).click();						//Clicking on 'Lawyer' drop down.
		
		Thread.sleep(500);
		OverduePOM.clickSaveButton(driver).click();						//Clicking on 'Save'button.
		
		Thread.sleep(1000);
		wait1.until(ExpectedConditions.visibilityOf(performerPOM.readMessage1(driver)));
		
		Thread.sleep(500);
		String msg = performerPOM.readMessage1(driver).getText();		//Reading Message appeared after save button
		int flag = 0;
		if(msg.equalsIgnoreCase("Case Created Successfully."))
		{
			test.log(LogStatus.PASS, "Message displayed = "+msg);
			flag = 1;
		}
		else
		{
			test.log(LogStatus.FAIL, "Message displayed = "+msg);
		}
		
		WebElement ele1 = null;
		WebElement ele2 = null;
		WebElement ele3 = null;
		WebElement ele4 = null;
		
		if(flag == 1)
		{
			try
			{
				Thread.sleep(700);
				wait.until(ExpectedConditions.visibilityOf(performerPOM.clickEditCase(driver)));
				ele1 = performerPOM.clickLinkCase(driver);
				ele2 = performerPOM.clickViewDoc(driver);
				ele3 = performerPOM.clickSendMail1(driver);
				ele4 = performerPOM.clickEditCase(driver);
			}
			catch(Exception e)
			{
				
			}
			
			if(ele1 != null && ele2 != null && ele3 != null && ele4 != null)
			{
				test.log(LogStatus.PASS, "Icons displayed are :- Link Notice, View Document, Send Mail with Document, Edit Notice");
			}
			else
			{
				test.log(LogStatus.FAIL, "All icons are not displayed.");
			}
		}
		
		driver.switchTo().parentFrame();
		performerPOM.clickClose(driver).click();			//Clicking on 'Close'
		
		Thread.sleep(500);
		performerPOM.clickExcelReport(driver).sendKeys(Keys.PAGE_DOWN);
		js.executeScript("window.scrollBy(0,700)");
		
		Thread.sleep(1000);
		CFOcountPOM.readTotalItems1(driver).click();
		String item = CFOcountPOM.readTotalItems1(driver).getText();
		String[] bits = item.split(" ");								//Splitting the String
		String compliancesCount = bits[bits.length - 2];				//Getting the second last word (total number of users)
		int count1 = 0;
		if(compliancesCount.equalsIgnoreCase("to"))
		{
			Thread.sleep(2000);
			item = CFOcountPOM.readTotalItems1(driver).getText();
			bits = item.split(" ");								//Splitting the String
			compliancesCount = bits[bits.length - 2];
		}
		count1 = Integer.parseInt(compliancesCount);
		
		if(count1 > gridRecords)
		{
			test.log(LogStatus.PASS, "Total Case Count increased in grid after adding New Case.");
			test.log(LogStatus.INFO, "Old Case Count from Grid = "+gridRecords+" | New Case Count from Grid = "+count1);
		}
		else
		{
			test.log(LogStatus.FAIL, "Total Case Count doesn't increased in grid after adding New Case.");
			test.log(LogStatus.INFO, "Old Case Count from Grid = "+gridRecords+" | New Case Count from Grid = "+count1);
		}
		
		Thread.sleep(500);
		OverduePOM.clickDashboard(driver).click();			//Clicking on 'Dashboard'
		
		Thread.sleep(500);
		wait.until(ExpectedConditions.visibilityOf(performerPOM.clickNoticeOpen(driver)));
		int open1 = Integer.parseInt(performerPOM.clickCaseOpen(driver).getText());	//Reading Notice Open count.
		
		if(open1 > open)
		{
			test.log(LogStatus.PASS, type+" Dashboard Count increamented. Old count = "+open+", New Count = "+open1);
		}
		else
		{
			test.log(LogStatus.FAIL, type+" Dashboard Count doesn't increamented. Old count = "+open+", New Count = "+open1);
		}
	}
	
	static void TaskAdd(WebDriver driver, ExtentTest test, XSSFSheet sheet, int open, int gridRecords, String type) throws InterruptedException
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		
		Thread.sleep(500);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		CFOcountPOM.clickNextPage1(driver).sendKeys(Keys.UP);
		js.executeScript("window.scrollBy(0,-700)");
		performerPOM.clickAddNewTask(driver).click();				//Clicking on 'New' button
		
		progress(driver);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("IframeAddTask"));
		
		Thread.sleep(500);
		Row row0 = sheet.getRow(0);								//Selected 0th index row (First row)
		Cell c1 = row0.getCell(1);								//Selected cell (0 row,1 column)
		String title = c1.getStringCellValue();
		performerPOM.clickTaskTitle(driver).sendKeys(title);	//Writing 'Task Title'
		
		Thread.sleep(300);
		row0 = sheet.getRow(1);									//Selected 0th index row (First row)
		c1 = row0.getCell(1);									//Selected cell (0 row,1 column)
		String desc = c1.getStringCellValue();
		performerPOM.clickTaskDesc(driver).sendKeys(desc);		//Writing 'Task Description'
		
		Thread.sleep(300);
		performerPOM.clickDueDate(driver).click();				//Clicking on 'Due Date' text box
		OverduePOM.selectNextMonth(driver).click();
		OverduePOM.selectDate(driver).click();					//Selecting particular date.
		
		Thread.sleep(300);
		Actions action = new Actions(driver);
		action.moveToElement(performerPOM.clickPriority(driver)).click().sendKeys(Keys.DOWN,Keys.ENTER).perform();
		
		Thread.sleep(300);
		row0 = sheet.getRow(2);									//Selected 0th index row (First row)
		c1 = row0.getCell(1);									//Selected cell (0 row,1 column)
		String outcome = c1.getStringCellValue();
		performerPOM.clickExpOutcome(driver).sendKeys(outcome);	//Writing 'Expected Outcome'
		
		Thread.sleep(300);
		row0 = sheet.getRow(3);									//Selected 0th index row (First row)
		c1 = row0.getCell(1);									//Selected cell (0 row,1 column)
		String internalUser = c1.getStringCellValue();
		performerPOM.clickInternalUser1(driver).click();
		performerPOM.clickSearchInternalUser1(driver).sendKeys(internalUser, Keys.ENTER);	//Selecting 'Internal User'
		
		Thread.sleep(1000);
		row0 = sheet.getRow(4);									//Selected 0th index row (First row)
		c1 = row0.getCell(1);									//Selected cell (0 row,1 column)
		String externalUser = c1.getStringCellValue();
		try
		{
			Thread.sleep(300);
			performerPOM.clickExternalUser(driver).click();
			Thread.sleep(500);
			action.moveToElement(performerPOM.clickSearchExternalUser(driver)).sendKeys(externalUser, Keys.ENTER).perform();
		}
		catch(Exception e)
		{
			
		}
		
		Thread.sleep(2000);
		row0 = sheet.getRow(5);									//Selected 0th index row (First row)
		c1 = row0.getCell(1);									//Selected cell (0 row,1 column)
		String remark = c1.getStringCellValue();
		performerPOM.clickRemark(driver).sendKeys(remark);		//Writing 'Remark'
		
		//Thread.sleep(300);
		//String workingDir = System.getProperty("user.dir");
		//performerPOM.clickUpload(driver).sendKeys(workingDir+"//Reports//PerformerResults.html");	//Uploading file
		
		Thread.sleep(300);
		OverduePOM.clickSaveButton(driver).click();				//Clicking on 'Save' button.
		
		Thread.sleep(300);
		wait.until(ExpectedConditions.visibilityOf(performerPOM.clickMessage(driver)));
		
		Thread.sleep(300);
		String msg = performerPOM.clickMessage(driver).getText();
		if(msg.contains("Task Saved Successfully."))
		{
			test.log(LogStatus.PASS, "Task Saved Successfully.");
		}
		else
		{
			test.log(LogStatus.FAIL, "Task didn't saved successfully.");
		}
		
		driver.switchTo().parentFrame();
		performerPOM.clickClose1(driver).click();			//Clicking on 'Close'
		
		Thread.sleep(500);
		wait.until(ExpectedConditions.visibilityOf(performerPOM.clickExcelReport(driver)));
		
		Thread.sleep(300);
		performerPOM.clickStatusDropDown(driver).click();		//Clicking on 'Status drop down.
		Thread.sleep(500);
		performerPOM.selectStatusDropDown(driver).click();		//Selecting 'Pending/Open' status
		
		Thread.sleep(500);
		performerPOM.clickExcelReport(driver).sendKeys(Keys.PAGE_DOWN);
		js.executeScript("window.scrollBy(0,700)");
		
		Thread.sleep(1000);
		CFOcountPOM.readTotalItems1(driver).click();
		String item = CFOcountPOM.readTotalItems1(driver).getText();
		String[] bits = item.split(" ");								//Splitting the String
		String compliancesCount = bits[bits.length - 2];				//Getting the second last word (total number of users)
		int count1 = 0;
		if(compliancesCount.equalsIgnoreCase("to"))
		{
			Thread.sleep(2000);
			item = CFOcountPOM.readTotalItems1(driver).getText();
			bits = item.split(" ");								//Splitting the String
			compliancesCount = bits[bits.length - 2];
		}
		count1 = Integer.parseInt(compliancesCount);
		
		if(count1 > gridRecords)
		{
			test.log(LogStatus.PASS, "Total Task Count increased in grid after adding New Task.");
			test.log(LogStatus.INFO, "Old Task Count from Grid = "+gridRecords+" | New Task Count from Grid = "+count1);
		}
		else
		{
			test.log(LogStatus.FAIL, "Total Task Count doesn't increased in grid after adding New Task.");
			test.log(LogStatus.INFO, "Old Task Count from Grid = "+gridRecords+" | New Task Count from Grid = "+count1);
		}
		
		Thread.sleep(500);
		OverduePOM.clickDashboard(driver).click();			//Clicking on 'Dashboard'
		
		Thread.sleep(500);
		wait.until(ExpectedConditions.visibilityOf(performerPOM.clickTaskOpen(driver)));
		int open1 = Integer.parseInt(performerPOM.clickTaskOpen(driver).getText());	//Reading Notice Open count.
		
		if(open1 > open)
		{
			test.log(LogStatus.PASS, type+" Dashboard Count Increased.");
			test.log(LogStatus.INFO, "Old Count = "+open+" | New Count = "+open1);
		}
		else
		{
			test.log(LogStatus.FAIL, type+" Dashboard Count doesn't increased.");
			test.log(LogStatus.INFO, "Old Count = "+open+" | New Count = "+open1);
		}
	}
	
	static int CountExcel(WebDriver driver, ExtentTest test, String type) throws InterruptedException, IOException
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		progress(driver);
		
		wait.until(ExpectedConditions.visibilityOf(performerPOM.clickNoticeOpen(driver)));
		int open = 0;
		if(type.equalsIgnoreCase("Notice - Open"))
		{
			open = Integer.parseInt(performerPOM.clickNoticeOpen(driver).getText());	//Reading Notice Open count.
			performerPOM.clickNoticeOpen(driver).click();						//Clicking on 'Open' notice
		}
		else if(type.equalsIgnoreCase("Notice - Closed"))
		{
			open = Integer.parseInt(performerPOM.clickNoticeClosed(driver).getText());	//Reading Notice Closed count.
			performerPOM.clickNoticeClosed(driver).click();						//Clicking on 'Closed' notice
		}
		else if(type.equalsIgnoreCase("Case - Open"))
		{
			open = Integer.parseInt(performerPOM.clickCaseOpen(driver).getText());	//Reading Case Open count.
			performerPOM.clickCaseOpen(driver).click();						//Clicking on 'Open' Case
		}
		else if(type.equalsIgnoreCase("Case - Closed"))
		{
			open = Integer.parseInt(performerPOM.clickCaseClosed(driver).getText());	//Reading Case Open count.
			performerPOM.clickCaseClosed(driver).click();						//Clicking on 'Open' Case
		}
		else if(type.equalsIgnoreCase("Task - Open"))
		{
			open = Integer.parseInt(performerPOM.clickTaskOpen(driver).getText());	//Reading Case Open count.
			performerPOM.clickTaskOpen(driver).click();						//Clicking on 'Open' Case
		}
		
		else if(type.equalsIgnoreCase("Task - Closed"))
		{
			open = Integer.parseInt(performerPOM.clickTaskClosed(driver).getText());	//Reading Case Open count.
			performerPOM.clickTaskClosed(driver).click();						//Clicking on 'Open' Case
		}
		
		Thread.sleep(500);
		progress(driver);
		
		Thread.sleep(500);
		wait.until(ExpectedConditions.visibilityOf(performerPOM.GridLoad(driver)));
		
		Thread.sleep(1000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		try
		{
			performerPOM.clickExcelReport(driver).sendKeys(Keys.PAGE_DOWN);
		}
		catch(Exception e)
		{
			
		}
		js.executeScript("window.scrollBy(0,700)");
		
		Thread.sleep(500);
		CFOcountPOM.readTotalItems1(driver).click();
		String item = CFOcountPOM.readTotalItems1(driver).getText();
		String[] bits = item.split(" ");								//Splitting the String
		String compliancesCount = bits[bits.length - 2];				//Getting the second last word (total number of users)
		int count1 = 0;
		if(compliancesCount.equalsIgnoreCase("to"))
		{
			Thread.sleep(2000);
			item = CFOcountPOM.readTotalItems1(driver).getText();
			bits = item.split(" ");								//Splitting the String
			compliancesCount = bits[bits.length - 2];
		}
		if(compliancesCount.equalsIgnoreCase("to"))
		{
			count1 = 0;
		}
		else
		{
			count1 = Integer.parseInt(compliancesCount);
		}
		
		if(open == count1)
		{
			test.log(LogStatus.PASS, type+" count matches to number of records displayed.");
			test.log(LogStatus.INFO, "Dashboard Count = "+open+" | Displayed records from grid = "+count1);
		}
		else
		{
			test.log(LogStatus.FAIL, type+" count doesn't matches to number of records displayed.");
			test.log(LogStatus.INFO, "Dashboard Count = "+open+" | Displayed records from grid = "+count1);
		}
		
		Thread.sleep(100);
		File dir = new File("C://Users//jiya//Downloads//");
		File[] dirContents = dir.listFiles();							//Counting number of files in directory before download 
		
		Thread.sleep(500);
		CFOcountPOM.clickNextPage1(driver).sendKeys(Keys.PAGE_UP);
		Thread.sleep(250);
		performerPOM.clickExcelReport(driver).click();					//Clicking on 'Excel Report' image.
		
		Thread.sleep(5500);
		File dir1 = new File("C://Users//jiya//Downloads//");
		File[] allFilesNew = dir1.listFiles();							//Counting number of files in directory after download
		
		if(dirContents.length < allFilesNew.length)
		{
			test.log(LogStatus.PASS, "File downloaded successfully.");
			
			File lastModifiedFile = allFilesNew[0];			//Storing any 0th index file in 'lastModifiedFile' file name.
		    for (int i = 1; i < allFilesNew.length; i++) 	//For loop till the number of files in directory.
		    {
		       if (lastModifiedFile.lastModified() < allFilesNew[i].lastModified()) 	//If allFilesNew[i] file is having large/latest time time of update then latest modified file be allFilesNew[i] file.
		       {
		           lastModifiedFile = allFilesNew[i];
		       }
		    }
			
			Thread.sleep(100);
			fis = new FileInputStream(lastModifiedFile);
			workbook = new XSSFWorkbook(fis);
			sheet = workbook.getSheetAt(0);					//Retrieving first sheet of Workbook
			
			int no = sheet.getLastRowNum();
			Row row = sheet.getRow(no);
			Cell c1 = row.getCell(0);
			String records = c1.getStringCellValue();
			int SheetRecords = 0;
			int flag = 0;
			try
			{
				SheetRecords = Integer.parseInt(records);
				flag = 1;
			}
			catch(Exception e)
			{
				
			}
			
			if(flag == 0)
			{
				row = sheet.getRow(no-1);
				c1 = row.getCell(0);
				records = c1.getStringCellValue();
				SheetRecords = Integer.parseInt(records);
			}
			fis.close();
			
			if(count1 == SheetRecords)
			{
				test.log(LogStatus.PASS, "No of records from grid matches to no of records in Excel Sheet.");
				test.log(LogStatus.INFO, "Total records from Grid = "+count1+" | Total records from Report = "+SheetRecords);
			}
			else
			{
				test.log(LogStatus.FAIL, "No of records from grid doesn't matches to no of records in Excel Sheet.");
				test.log(LogStatus.INFO, "Total records from Grid = "+count1+" | Total records from Excel Sheet = "+SheetRecords);
			}
		}
		else
		{
			test.log(LogStatus.FAIL, "File doesn't downloaded successfully.");
		}
		return open;
	}
	
	public static void NoticeOpen(WebDriver driver, ExtentTest test, XSSFWorkbook workbook, String login) throws InterruptedException, IOException
	{
		int sheetNo = 0;
	    if(login.equalsIgnoreCase("Performer"))
	    {
	    	sheetNo = 1;
	    }
	    else if(login.equalsIgnoreCase("Company Admin"))
	    {
	    	sheetNo = 5;
	    }
		
		int open = CountExcel(driver, test, "Notice - Open");
		
		Thread.sleep(500);
		performerPOM.clickExcelReport(driver).sendKeys(Keys.PAGE_DOWN);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,700)");
		
		Thread.sleep(300);
		CFOcountPOM.readTotalItems1(driver).click();
		String item = CFOcountPOM.readTotalItems1(driver).getText();
		String[] bits = item.split(" ");								//Splitting the String
		String compliancesCount = bits[bits.length - 2];				//Getting the second last word (total number of users)
		int gridRecords = 0;
		if(compliancesCount.equalsIgnoreCase("to"))
		{
			Thread.sleep(2000);
			item = CFOcountPOM.readTotalItems1(driver).getText();
			bits = item.split(" ");								//Splitting the String
			compliancesCount = bits[bits.length - 2];
		}
		if(compliancesCount.equalsIgnoreCase("to"))
		{
			gridRecords = 0;
		}
		else
		{
			gridRecords = Integer.parseInt(compliancesCount);
		}
		
		sheet = workbook.getSheetAt(sheetNo);
		
		perform(driver, test, sheet, open, gridRecords, "Notice - Open");
	}
	
	public static void NoticeClosed(WebDriver driver, ExtentTest test, XSSFWorkbook workbook, String login) throws InterruptedException, IOException
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		
		CountExcel(driver, test, "Notice - Closed");
		
		Thread.sleep(500);
		wait.until(ExpectedConditions.visibilityOf(performerPOM.clickNew(driver)));
		OverduePOM.clickDashboard(driver).click();			//Clicking on 'Dashboard'
	}
	
	public static void CaseOpen(WebDriver driver, ExtentTest test, XSSFWorkbook workbook, String login) throws InterruptedException, IOException
	{
		int sheetNo = 0;
	    if(login.equalsIgnoreCase("Performer"))
	    {
	    	sheetNo = 2;
	    }
	    else if(login.equalsIgnoreCase("Company Admin"))
	    {
	    	sheetNo = 6;
	    }
		
		int open = CountExcel(driver, test, "Case - Open");
		
		Thread.sleep(500);
		performerPOM.clickExcelReport(driver).sendKeys(Keys.PAGE_DOWN);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,700)");
		
		Thread.sleep(300);
		CFOcountPOM.readTotalItems1(driver).click();
		String item = CFOcountPOM.readTotalItems1(driver).getText();
		String[] bits = item.split(" ");								//Splitting the String
		String compliancesCount = bits[bits.length - 2];				//Getting the second last word (total number of users)
		int gridRecords = 0;
		if(compliancesCount.equalsIgnoreCase("to"))
		{
			Thread.sleep(2000);
			item = CFOcountPOM.readTotalItems1(driver).getText();
			bits = item.split(" ");								//Splitting the String
			compliancesCount = bits[bits.length - 2];
		}
		if(compliancesCount.equalsIgnoreCase("to"))
		{
			gridRecords = 0;
		}
		else
		{
			gridRecords = Integer.parseInt(compliancesCount);
		}
		
		sheet = workbook.getSheetAt(sheetNo);
		
		perform1(driver, test, sheet, open, gridRecords, "Case - Open");
	}
	
	public static void CaseClosed(WebDriver driver, ExtentTest test, XSSFWorkbook workbook, String login) throws InterruptedException, IOException
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		
		CountExcel(driver, test, "Case - Closed");
		
		Thread.sleep(500);
		wait.until(ExpectedConditions.visibilityOf(performerPOM.clickNew(driver)));
		OverduePOM.clickDashboard(driver).click();			//Clicking on 'Dashboard'
	}
	
	public static void TaskOpen(WebDriver driver, ExtentTest test, XSSFWorkbook workbook, String login) throws InterruptedException, IOException
	{
		int sheetNo = 0;
	    if(login.equalsIgnoreCase("Performer"))
	    {
	    	sheetNo = 3;
	    }
	    else if(login.equalsIgnoreCase("Company Admin"))
	    {
	    	sheetNo = 7;
	    }
	    
		int open = CountExcel(driver, test, "Task - Open");
		
		Thread.sleep(500);
		performerPOM.clickExcelReport(driver).sendKeys(Keys.PAGE_DOWN);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,700)");
		
		Thread.sleep(300);
		CFOcountPOM.readTotalItems1(driver).click();
		String item = CFOcountPOM.readTotalItems1(driver).getText();
		String[] bits = item.split(" ");								//Splitting the String
		String compliancesCount = bits[bits.length - 2];				//Getting the second last word (total number of users)
		int gridRecords = 0;
		if(compliancesCount.equalsIgnoreCase("to"))
		{
			Thread.sleep(2000);
			item = CFOcountPOM.readTotalItems1(driver).getText();
			bits = item.split(" ");								//Splitting the String
			compliancesCount = bits[bits.length - 2];
		}
		if(compliancesCount.equalsIgnoreCase("to"))
		{
			gridRecords = 0;
		}
		else
		{
			gridRecords = Integer.parseInt(compliancesCount);
		}
		
		sheet = workbook.getSheetAt(sheetNo);
		
		TaskAdd(driver, test, sheet, open, gridRecords, "Task - Open");
	}
	
	public static void TaskClosed(WebDriver driver, ExtentTest test, XSSFWorkbook workbook, String login) throws InterruptedException, IOException
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		
		CountExcel(driver, test, "Task - Closed");
		
		Thread.sleep(500);
		wait.until(ExpectedConditions.visibilityOf(performerPOM.clickAddNewTask(driver)));
		OverduePOM.clickDashboard(driver).click();			//Clicking on 'Dashboard'
	}
	
	public static void LinkDocument(WebDriver driver, ExtentTest test, XSSFWorkbook workbook, String type) throws InterruptedException, IOException
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		progress(driver);
		
		wait.until(ExpectedConditions.visibilityOf(performerPOM.clickNoticeOpen(driver)));
		if(type.equals("Notice"))
		{
			performerPOM.clickNoticeOpen(driver).click();							//Clicking on 'Open' notice
		}
		else if(type.equals("Case"))
		{
			performerPOM.clickCaseOpen(driver).click();								//Clicking on 'Open' case
		}
		
		progress(driver);
		wait.until(ExpectedConditions.visibilityOf(performerPOM.clickExcelReport(driver)));
		
		Thread.sleep(400);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,500)");
		
		Thread.sleep(1500);
		wait.until(ExpectedConditions.visibilityOf(performerPOM.GridLoad(driver)));
		//performerPOM.GridLoad(driver).click();
		elementsList = performerPOM.clickAction(driver);			//Getting all action buttons.
		js.executeScript("arguments[0].scrollIntoView();", elementsList.get(0));
		
		Thread.sleep(600);
		elementsList = performerPOM.clickAction(driver);			//Getting all action buttons.
		elementsList.get(0).click();								//Clicking on first action button.
		
		String refNo = null;
		Thread.sleep(500);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("showdetails"));	//Waiting and switching to IFrame		
		if(type.equals("Notice"))
		{
			performerPOM.clickLinkNotice(driver).click();			//Clicking on Link Notice icon
			
			Thread.sleep(300);
			progress(driver);
			
			Thread.sleep(300);
			wait.until(ExpectedConditions.elementToBeClickable(performerPOM.clickCheckBox(driver)));	//Waiting for Checkbox to get visible.
			refNo = performerPOM.readRef(driver).getText();			//Reading ref no.
			
			Thread.sleep(300);
			performerPOM.clickCheckBox(driver).click();			//CLicking on first checkbox
		}
		else if(type.equals("Case"))
		{
			performerPOM.clickLinkCase(driver).click();			//Clicking on Link Notice icon
			
			Thread.sleep(300);
			progress(driver);
			
			Thread.sleep(300);
			wait.until(ExpectedConditions.elementToBeClickable(performerPOM.clickCaseCheckBox(driver)));	//Waiting for Checkbox to get visible.
			refNo = performerPOM.readCaseRef(driver).getText();			//Reading ref no.
			
			Thread.sleep(300);
			performerPOM.clickCaseCheckBox(driver).click();		//CLicking on first checkbox
		}
		
		Thread.sleep(300);
		if(type.equals("Case"))
		{
			performerPOM.clickApply(driver).sendKeys(Keys.PAGE_DOWN);
		}
		else
		{
			performerPOM.clickApply1(driver).sendKeys(Keys.PAGE_DOWN);
		}
		
		Thread.sleep(300);
		performerPOM.clickSave(driver).click();				//Clicking on Save button.
		
		Thread.sleep(300);
		progress(driver);
		
		Thread.sleep(500);
		try
		{
			wait.until(ExpectedConditions.elementToBeClickable(performerPOM.readMsg(driver)));
		}
		catch(Exception e)
		{
			wait.until(ExpectedConditions.elementToBeClickable(performerPOM.readMsg(driver)));
		}
		Thread.sleep(300);
		String msg = performerPOM.readMsg(driver).getText();
		if(msg.contains("Linked Successfully"))
		{
			test.log(LogStatus.PASS, "Message displayed = "+msg);
		}
		else
		{
			test.log(LogStatus.FAIL, "Message displayed = "+msg);
		}
		
		int flag = 0;
		int n = 0;
		if(type.equals("Notice"))
		{
			performerPOM.clickClosePopup(driver).click();
			
			Thread.sleep(300);
			performerPOM.clickLinkNotice(driver).sendKeys(Keys.PAGE_DOWN);
			performerPOM.clickLinkNotice(driver).sendKeys(Keys.PAGE_DOWN);
			performerPOM.clickLinkNotice(driver).sendKeys(Keys.PAGE_DOWN);
			
			Thread.sleep(300);
			elementsList = performerPOM.readRef1(driver);
			n = elementsList.size();
			
			if(n > 0)
			{
				for(int i = 0; i < n; i++)
				{
					String ref = elementsList.get(i).getText();
					if(refNo.equalsIgnoreCase(ref))
					{
						flag = 1;
						break;
					}
				}
			}
		}
		else if(type.equals("Case"))
		{
			performerPOM.clickClosePopupCase(driver).click();
			
			Thread.sleep(300);
			performerPOM.clickLinkCase(driver).sendKeys(Keys.PAGE_DOWN);
			performerPOM.clickLinkCase(driver).sendKeys(Keys.PAGE_DOWN);
			performerPOM.clickLinkCase(driver).sendKeys(Keys.PAGE_DOWN);
			
			Thread.sleep(300);
			elementsList = performerPOM.readCaseRef1(driver);
			n = elementsList.size();
			
			if(n > 0)
			{
				for(int i = 0; i < n; i++)
				{
					String ref = elementsList.get(i).getText();
					if(refNo.equalsIgnoreCase(ref))
					{
						flag = 1;
						break;
					}
				}
			}
		}
		
		if(flag == 1)
		{
			test.log(LogStatus.PASS, "Linked "+type+" displayed in "+type+" Summary. Reference No = "+refNo);
		}
		else
		{
			test.log(LogStatus.FAIL, "Linked "+type+" doesn't displayed in "+type+" Summary. Reference No = "+refNo);
		}
		
		if(type.equals("Notice"))
		{
			performerPOM.clickLinkNotice(driver).sendKeys(Keys.PAGE_UP);
			performerPOM.clickLinkNotice(driver).sendKeys(Keys.PAGE_UP);
			performerPOM.clickLinkNotice(driver).sendKeys(Keys.PAGE_UP);
		}
		else if(type.equals("Case"))
		{
			performerPOM.clickLinkCase(driver).sendKeys(Keys.PAGE_UP);
			performerPOM.clickLinkCase(driver).sendKeys(Keys.PAGE_UP);
			performerPOM.clickLinkCase(driver).sendKeys(Keys.PAGE_UP);
		}
		
		Thread.sleep(300);
		driver.switchTo().parentFrame();
		performerPOM.clickClose(driver).click();
		
		Thread.sleep(500);
		OverduePOM.clickDashboard(driver).click();
	}
	
	public static void CloseNoticeCase(WebDriver driver, ExtentTest test, XSSFWorkbook workbook, String type) throws InterruptedException, IOException
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		progress(driver);
		
		wait.until(ExpectedConditions.visibilityOf(performerPOM.clickNoticeOpen(driver)));
		int closed = 0;
		int open = 0;
		int caseOpen = 0;
		if(type.equals("Notice"))
		{
			closed = Integer.parseInt(performerPOM.clickNoticeClosed(driver).getText());	//Reading Notice Closed count.
			open = Integer.parseInt(performerPOM.clickNoticeOpen(driver).getText());		//Reading Notice Open count.
			caseOpen = Integer.parseInt(performerPOM.clickCaseOpen(driver).getText());
			
			performerPOM.clickNoticeOpen(driver).click();									//Clicking on 'Open' notice
		}
		else if(type.equals("Case"))
		{
			open = Integer.parseInt(performerPOM.clickCaseOpen(driver).getText());			//Reading Case Open count.
			closed = Integer.parseInt(performerPOM.clickCaseClosed(driver).getText());		//Reading Case Closed count.
			
			performerPOM.clickCaseOpen(driver).click();										//Clicking on 'Open' case
		}
		else if(type.equals("Task"))
		{
			open = Integer.parseInt(performerPOM.clickTaskOpen(driver).getText());			//Reading Task Open count.
			closed = Integer.parseInt(performerPOM.clickTaskClosed(driver).getText());		//Reading Task Closed count.
			
			performerPOM.clickTaskOpen(driver).click();										//Clicking on 'Open' task
		}
		
		Thread.sleep(300);
		wait.until(ExpectedConditions.visibilityOf(performerPOM.clickExcelReport(driver)));	//Waiting until visibility of Excel Report button.
		
		Thread.sleep(1000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,500)");
		
		Thread.sleep(500);
		performerPOM.GridLoad(driver).click();
		elementsList = performerPOM.clickAction(driver);			//Getting all action buttons.
		js.executeScript("arguments[0].scrollIntoView();", elementsList.get(0));
		
		Thread.sleep(500);
		elementsList = performerPOM.clickAction(driver);			//Getting all action buttons.
		elementsList.get(0).click();								//Clicking on first action button.
		
		Thread.sleep(500);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("showdetails"));	//Waiting and switching to IFrame
		
		Thread.sleep(300);
		if(type.equals("Notice"))
		{
			sheet = workbook.getSheetAt(1);
			
			performerPOM.clickStatusPayments(driver).click();			//Clicking on 'Status/Payments'
			
			wait.until(ExpectedConditions.visibilityOf(performerPOM.clickNoticeStatus(driver)));
			performerPOM.clickNoticeStatus(driver).click();				//Clicking on 'Notice Status' drop down.
			Thread.sleep(300);
			performerPOM.clickClosedStatus(driver).click();				//Selecting 'Closed' option from drop down.
			
			Thread.sleep(300);
			wait.until(ExpectedConditions.visibilityOf(performerPOM.clickCloseDate(driver)));
			performerPOM.clickCloseDate(driver).click();				//Clicking on 'Closed Date' date box
			OverduePOM.selectLastMonth(driver).click();					//Getting last month
			OverduePOM.selectDate2(driver).click();						//Selecting particular date.
			
			Thread.sleep(300);
			performerPOM.clickNoticeResult(driver).click();
			performerPOM.clickSelectResult(driver).sendKeys("In Progress", Keys.ENTER);
			
			Thread.sleep(300);
			Row r1 = sheet.getRow(25);
			Cell c1 = r1.getCell(1);
			String remark = c1.getStringCellValue();
			performerPOM.clickRemark1(driver).sendKeys(remark);
			
			Thread.sleep(300);
			r1 = sheet.getRow(26);
			c1 = r1.getCell(1);
			String CaseNo = c1.getStringCellValue();
			performerPOM.clickCourtCaseNo(driver).sendKeys(CaseNo);
			
			Thread.sleep(300);
			performerPOM.clickSaveConvertCase(driver).click();
		}
		else if(type.equals("Case"))
		{
			performerPOM.clickCaseStatusPayments(driver).click();		//Clicking on 'Status/Payments'
			
			wait.until(ExpectedConditions.visibilityOf(performerPOM.clickCaseStatus(driver)));
			
			performerPOM.clickCaseStage(driver).click();
			Thread.sleep(300);
			performerPOM.selectCaseStage(driver).sendKeys("Hearing", Keys.ENTER);
			
			Thread.sleep(300);
			performerPOM.clickCaseStatus(driver).click();				//Clicking on 'Case Status' drop down.
			Thread.sleep(300);
			performerPOM.clickCaseStatusClose(driver).click();			//Selecting 'Closed' option from drop down.
			
			Thread.sleep(300);
			wait.until(ExpectedConditions.visibilityOf(performerPOM.clickCaseCloseDate(driver)));
			performerPOM.clickCaseCloseDate(driver).click();				//Clicking on 'Closed Date' date box
			OverduePOM.selectLastMonth(driver).click();					//Getting last month
			OverduePOM.selectDate2(driver).click();						//Selecting particular date.
			
			Thread.sleep(300);
			performerPOM.clickCaseResult(driver).click();
			performerPOM.clickSelectCaseResult(driver).sendKeys("In Progress", Keys.ENTER);
			
			Thread.sleep(300);
			performerPOM.clickRemark1(driver).sendKeys("Automation Testing");
			
			Thread.sleep(300);
			performerPOM.clickSave1(driver).click();
		}
		else if(type.equals("Task"))
		{
			
		}
		
		Thread.sleep(300);
		wait.until(ExpectedConditions.visibilityOf(performerPOM.readMessage2(driver)));
		String msg = performerPOM.readMessage2(driver).getText();
		
		if(msg.contains("Successfully"))
		{
			test.log(LogStatus.PASS, "Message displayed - "+msg);
		}
		else if(msg.contains("already exist"))
		{
			test.log(LogStatus.SKIP, "Message displayed - "+msg);
		}
		else
		{
			test.log(LogStatus.FAIL, "Message displayed - "+msg);
		}
		
		Thread.sleep(300);
		driver.switchTo().parentFrame();
		
		Thread.sleep(300);
		performerPOM.clickClose(driver).click();
		
		Thread.sleep(500);
		OverduePOM.clickDashboard(driver).click();
		
		wait.until(ExpectedConditions.visibilityOf(performerPOM.clickNoticeOpen(driver)));
		int closed1 = 0;
		int open1 = 0;
		int caseOpen1 = 0;
		if(type.equals("Notice"))
		{
			closed1 = Integer.parseInt(performerPOM.clickNoticeClosed(driver).getText());	//Reading Notice Open count.
			open1 = Integer.parseInt(performerPOM.clickNoticeOpen(driver).getText());		//Reading Notice Open count.
			caseOpen1 = Integer.parseInt(performerPOM.clickCaseOpen(driver).getText());
			
			if(open > open1 && closed1 > closed && caseOpen1 > caseOpen)
			{
				test.log(LogStatus.PASS, "Notice-Closed count increased.");
				test.log(LogStatus.INFO, "Old Count = "+closed+" | New Count = "+closed1);
				test.log(LogStatus.PASS, "Notice-Open count decreased.");
				test.log(LogStatus.INFO, "Old Count = "+open+" | New Count = "+open1);
				test.log(LogStatus.PASS, "Case-Open count increased.");
				test.log(LogStatus.INFO, "Old Count = "+caseOpen+" | New Count = "+caseOpen1);
			}
			else
			{
				test.log(LogStatus.FAIL, "Notice-Closed count doesn't increased.");
				test.log(LogStatus.INFO, "Old Count = "+closed+" | New Count = "+closed1);
				test.log(LogStatus.FAIL, "Notice-Open count doesn't decreased.");
				test.log(LogStatus.INFO, "Old Count = "+open+" | New Count = "+open1);
				test.log(LogStatus.FAIL, "Case-Open count doesn't increased.");
				test.log(LogStatus.INFO, "Old Count = "+caseOpen+" | New Count = "+caseOpen1);
			}
		}
		else if(type.equals("Case"))
		{
			open1 = Integer.parseInt(performerPOM.clickCaseOpen(driver).getText());			//Reading Case Open count.
			closed1 = Integer.parseInt(performerPOM.clickCaseClosed(driver).getText());		//Reading Case Closed count.
			
			if(open > open1 && closed1 > closed)
			{
				test.log(LogStatus.PASS, "Case-Closed count increased.");
				test.log(LogStatus.INFO, "Old Count = "+closed+" | New Count = "+closed1);
				test.log(LogStatus.PASS, "Case-Open count decreased.");
				test.log(LogStatus.INFO, "Old Count = "+open+" | New Count = "+open1);
			}
			else
			{
				test.log(LogStatus.FAIL, "Case-Closed count doesn't increased.");
				test.log(LogStatus.INFO, "Old Count = "+closed+" | New Count = "+closed1);
				test.log(LogStatus.FAIL, "Case-Open count doesn't decreased.");
				test.log(LogStatus.INFO, "Old Count = "+open+" | New Count = "+open1);
			}
		}
		else if(type.equals("Task"))
		{
			open1 = Integer.parseInt(performerPOM.clickTaskOpen(driver).getText());			//Reading Task Open count.
			closed1 = Integer.parseInt(performerPOM.clickTaskClosed(driver).getText());		//Reading Task Closed count.
			
			if(open > open1 && closed1 > closed)
			{
				test.log(LogStatus.PASS, "Task-Closed count increased.");
				test.log(LogStatus.INFO, "Old Count = "+closed+" | New Count = "+closed1);
				test.log(LogStatus.PASS, "Task-Open count decreased.");
				test.log(LogStatus.INFO, "Old Count = "+open+" | New Count = "+open1);
			}
			else
			{
				test.log(LogStatus.PASS, "Task-Closed count doesn't increased.");
				test.log(LogStatus.INFO, "Old Count = "+closed+" | New Count = "+closed1);
				test.log(LogStatus.PASS, "Task-Open count doesn't decreased.");
				test.log(LogStatus.INFO, "Old Count = "+open+" | New Count = "+open1);
			}
		}
	}
	
	static void Report(WebDriver driver, ExtentTest test, int count1, String type) throws InterruptedException, IOException
	{
		Thread.sleep(700);
		File dir = new File("C://Users//jiya//Downloads//");
		File[] dirContents = dir.listFiles();							//Counting number of files in directory before download 
		
		Thread.sleep(500);
		CFOcountPOM.clickNextPage1(driver).sendKeys(Keys.PAGE_UP);
		Thread.sleep(300);
		performerPOM.clickExcelReport(driver).click();					//Clicking on 'Excel Report' image.
		
		Thread.sleep(6000);
		File dir1 = new File("C://Users//jiya//Downloads//");
		File[] allFilesNew = dir1.listFiles();							//Counting number of files in directory after download
		
		if(dirContents.length < allFilesNew.length)
		{
			test.log(LogStatus.PASS, "File Downloaded Successfully.");
			
			File lastModifiedFile = allFilesNew[0];			//Storing any 0th index file in 'lastModifiedFile' file name.
		    for (int i = 1; i < allFilesNew.length; i++) 	//For loop till the number of files in directory.
		    {
		       if (lastModifiedFile.lastModified() < allFilesNew[i].lastModified()) 	//If allFilesNew[i] file is having large/latest time time of update then latest modified file be allFilesNew[i] file.
		       {
		           lastModifiedFile = allFilesNew[i];
		       }
		    }
			
			Thread.sleep(700);
			fis = new FileInputStream(lastModifiedFile);
			workbook = new XSSFWorkbook(fis);
			sheet = workbook.getSheetAt(0);					//Retrieving first sheet of Workbook
			int no = sheet.getLastRowNum();
			int SheetRecords = 0;
			for(int i = 0; i <= 5; i++)
			{
				Row row = sheet.getRow(no-i);
				Cell c1 = row.getCell(0);
				String records = c1.getStringCellValue();
				if(records.equals("") || records.equals(null))
				{
					
				}
				else
				{
					SheetRecords = Integer.parseInt(records);
					break;
				}
			}
			fis.close();
			
			if(count1 == SheetRecords)
			{
				test.log(LogStatus.PASS, type+" - No of records displayed matches to no of records in Excel Sheet.");
				test.log(LogStatus.INFO, "Total records displayed = "+count1+". Total records in Excel sheet = "+SheetRecords);
			}
			else
			{
				test.log(LogStatus.FAIL, type+" - No of records displayed doesn't matches to no of records in Excel Sheet.");
				test.log(LogStatus.INFO, "Total records displayed = "+count1+". Total records in Excel sheet = "+SheetRecords);
			}
		}
		else
		{
			test.log(LogStatus.FAIL, type+" - File doesn't downloaded successfully.");
		}
	}
	
	public static void MyReports(WebDriver driver, ExtentTest test, XSSFWorkbook workbook, String login) throws InterruptedException, IOException
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		progress(driver);
		
		wait.until(ExpectedConditions.visibilityOf(performerPOM.clickNoticeOpen(driver)));	//Wait until 'Notice-Open' count get visible
		performerPOM.clickMyReports(driver).click();					//Clicking on 'My Reports'
		
		Thread.sleep(500);
		wait.until(ExpectedConditions.visibilityOf(performerPOM.GridLoad(driver)));	//Wait until records table gets visible.
		
		//--------------------------------Notice----------------------------------
		
		Thread.sleep(2000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		performerPOM.clickExcelReport(driver).sendKeys(Keys.PAGE_DOWN);
		performerPOM.clickExcelReport(driver).sendKeys(Keys.END);
		js.executeScript("arguments[0].scrollIntoView();", CFOcountPOM.readTotalItems1(driver));
		
		Thread.sleep(1000);
		CFOcountPOM.readTotalItems1(driver).click();
		String item = CFOcountPOM.readTotalItems1(driver).getText();
		String[] bits = item.split(" ");								//Splitting the String
		if(bits.length < 2)
		{
			performerPOM.clickExcelReport(driver).sendKeys(Keys.END);
			Thread.sleep(300);
			item = CFOcountPOM.readTotalItems1(driver).getText();
			bits = item.split(" ");									//Splitting the String
		}
		String compliancesCount = bits[bits.length - 2];				//Getting the second last word (total number of users)
		int count1 = 0;
		if(compliancesCount.equalsIgnoreCase("to"))
		{
			Thread.sleep(2500);
			item = CFOcountPOM.readTotalItems1(driver).getText();
			bits = item.split(" ");										//Splitting the String
			compliancesCount = bits[bits.length - 2];					//Getting the second last word (total number of users)
		}
		else if(compliancesCount.equalsIgnoreCase("to"))
		{
			count1 = 0;
		}
		else
		{
			count1 = Integer.parseInt(compliancesCount);
		}
		
		Thread.sleep(500);
		Report(driver, test, count1, "Notice");
		
		driver.navigate().refresh();
		
		//--------------------------------Case----------------------------------
		
		Thread.sleep(1500);
		js.executeScript("window.scrollBy(500,0)");
		
		performerPOM.clickTypeDropdown(driver).click();					//Clicking on Type drop down box (i.e. Notice, Case, Task)
		Thread.sleep(300);
		performerPOM.selectTypeCase(driver).click();					//Selecting 'Case' option.
		
		Thread.sleep(1000);
		wait.until(ExpectedConditions.visibilityOf(performerPOM.GridLoad(driver)));	//Wait until records table gets visible.
		Thread.sleep(500);
		performerPOM.clickExcelReport(driver).sendKeys(Keys.END);
		js.executeScript("arguments[0].scrollIntoView();", CFOcountPOM.clickNextPage1(driver));
		js.executeScript("window.scrollBy(0,500)");
		
		Thread.sleep(1000);
		item = CFOcountPOM.readTotalItems1(driver).getText();
		bits = item.split(" ");									//Splitting the String
		if(bits.length < 2)
		{
			performerPOM.clickExcelReport(driver).sendKeys(Keys.END);
			Thread.sleep(300);
			item = CFOcountPOM.readTotalItems1(driver).getText();
			bits = item.split(" ");									//Splitting the String
			
		}
		compliancesCount = bits[bits.length - 2];				//Getting the second last word (total number of users)
		count1 = 0;
		if(compliancesCount.equalsIgnoreCase("to"))
		{
			Thread.sleep(2500);
			item = CFOcountPOM.readTotalItems1(driver).getText();
			bits = item.split(" ");										//Splitting the String
			compliancesCount = bits[bits.length - 2];					//Getting the second last word (total number of users)
		}
		else if(compliancesCount.equalsIgnoreCase("to"))
		{
			count1 = 0;
		}
		else
		{
			count1 = Integer.parseInt(compliancesCount);
		}
		
		Thread.sleep(500);
		Report(driver, test, count1, "Case");
		
		driver.navigate().refresh();

		//--------------------------------Task----------------------------------
		
		Thread.sleep(1500);
		js.executeScript("window.scrollBy(500,0)");
		
		performerPOM.clickTypeDropdown(driver).click();					//Clicking on Type drop down box (i.e. Notice, Case, Task)
		Thread.sleep(300);
		performerPOM.selectTypeTask(driver).click();					//Selecting 'Task' option.
		
		Thread.sleep(1000);
		wait.until(ExpectedConditions.visibilityOf(performerPOM.GridLoad(driver)));	//Wait until records table gets visible.
		Thread.sleep(500);
		performerPOM.clickExcelReport(driver).sendKeys(Keys.END);
		js.executeScript("arguments[0].scrollIntoView();", CFOcountPOM.readTotalItems1(driver));
		
		Thread.sleep(1000);
		item = CFOcountPOM.readTotalItems1(driver).getText();
		bits = item.split(" ");								//Splitting the String
		if(bits.length < 2)
		{
			performerPOM.clickExcelReport(driver).sendKeys(Keys.END);
			Thread.sleep(300);
			item = CFOcountPOM.readTotalItems1(driver).getText();
			bits = item.split(" ");									//Splitting the String
			
		}
		compliancesCount = bits[bits.length - 2];				//Getting the second last word (total number of users)
		count1 = 0;
		if(compliancesCount.equalsIgnoreCase("to"))
		{
			count1 = 0;
		}
		else
		{
			count1 = Integer.parseInt(compliancesCount);
		}
		
		Thread.sleep(500);
		Report(driver, test, count1, "Task");
		
		Thread.sleep(500);
		OverduePOM.clickDashboard(driver).click();				//Clicking on 'My Dashboard'
	}
	
	static void NewReminder(WebDriver driver, ExtentTest test, String type) throws InterruptedException
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		
		Thread.sleep(500);
		wait.until(ExpectedConditions.visibilityOf(performerPOM.clickAddNew1(driver)));
		performerPOM.clickAddNew1(driver).click();		//Clicking on 'Add New' button.
		
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("ContentPlaceHolder1_showReminderDetail"));
		wait.until(ExpectedConditions.visibilityOf(performerPOM.clickType(driver)));
		Actions action = new Actions(driver);
		
		if(type.equalsIgnoreCase("Notice"))
		{
			action.moveToElement(performerPOM.clickType(driver)).click().sendKeys(Keys.ARROW_DOWN, Keys.ENTER).perform();
		}
		else if(type.equalsIgnoreCase("Task"))
		{
			action.moveToElement(performerPOM.clickType(driver)).click().sendKeys(Keys.ARROW_DOWN, Keys.ARROW_DOWN, Keys.ENTER).perform();
		}
		
		Thread.sleep(2000);
		action.moveToElement(performerPOM.clickTitle(driver)).click().sendKeys(Keys.ARROW_DOWN, Keys.ARROW_DOWN, Keys.ENTER).perform();
		
		Thread.sleep(2000);
		performerPOM.clickReminderText(driver).sendKeys("Auomation Testing reminder message2.");
		
		Thread.sleep(300);
		performerPOM.clickDescription(driver).sendKeys("Automation reminder description2.");
		
		Thread.sleep(300);
		performerPOM.clickRemark2(driver).sendKeys("Automation reminder remark2.");
		
		Thread.sleep(300);
		performerPOM.clickDate(driver).click();
		Thread.sleep(300);
		OverduePOM.selectNextMonth(driver).click();
		OverduePOM.selectDate(driver).click();
		
		Thread.sleep(300);
		performerPOM.clickSave(driver).click();				//Clicking on Save button.
		
		Thread.sleep(500);
		try
		{
			wait.until(ExpectedConditions.visibilityOf(performerPOM.readMsg1(driver)));
		}
		catch(Exception e)
		{
			wait.until(ExpectedConditions.visibilityOf(performerPOM.readMsg1(driver)));
		}
		Thread.sleep(300);
		String msg = performerPOM.readMsg1(driver).getText();
		if(msg.contains("Successfully"))
		{
			test.log(LogStatus.PASS, type+" Message Displayed - "+msg);
		}
		else if(msg.contains("already exists"))
		{
			test.log(LogStatus.PASS, type+" Message Displayed - "+msg);
		}
		else
		{
			test.log(LogStatus.FAIL, type+" Message Displayed - "+msg);
		}
		
		Thread.sleep(300);
		driver.switchTo().parentFrame();
		
		Thread.sleep(300);
		performerPOM.clickCloseReminder(driver).click();
	}
	
	public static void MyReminder(WebDriver driver, ExtentTest test, XSSFWorkbook workbook) throws InterruptedException, IOException
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		progress(driver);
		
		wait.until(ExpectedConditions.visibilityOf(performerPOM.clickNoticeOpen(driver)));	//Wait until 'Notice-Open' count get visible
		performerPOM.clickMyReminder(driver).click();					//Clicking on 'My Reports'
		
		wait.until(ExpectedConditions.visibilityOf(performerPOM.CheckRecordsTable(driver)));	//Wait until records table gets visible.
		
		NewReminder(driver, test, "Case");
		
		NewReminder(driver, test, "Notice");
		
		NewReminder(driver, test, "Task");
		
		Thread.sleep(300);
		OverduePOM.clickDashboard(driver).click();
	}
	
	public static void Masters(WebDriver driver, ExtentTest test, XSSFWorkbook workbook) throws InterruptedException, IOException
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		progress(driver);
		
		wait.until(ExpectedConditions.visibilityOf(performerPOM.clickNoticeOpen(driver)));	//Wait until 'Notice-Open' count get visible
		performerPOM.clickMasters(driver).click();
		
		Thread.sleep(300);
		performerPOM.clickMastersMenu(driver).click();
		
		Thread.sleep(300);
		wait.until(ExpectedConditions.visibilityOf(performerPOM.clickAddNew2(driver)));
		performerPOM.clickAddNew2(driver).click();
		
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("ContentPlaceHolder1_IframeCaseType"));
		
		Thread.sleep(300);
		performerPOM.clickCaseNoticeType(driver).sendKeys("New Admin2");
		
		Thread.sleep(300);
		performerPOM.clickSave(driver).click();				//Clicking on Save button.
		
		Thread.sleep(1000);
		wait.until(ExpectedConditions.visibilityOf(performerPOM.readMesg(driver)));
		String msg = performerPOM.readMesg(driver).getText();
		if(msg.contains("Successfully"))
		{
			test.log(LogStatus.PASS, " Message Displayed - "+msg);
		}
		else
		{
			test.log(LogStatus.FAIL, " Message Displayed - "+msg);
		}
		
		Thread.sleep(300);
		driver.switchTo().parentFrame();
		
		Thread.sleep(300);
		performerPOM.clickClose2(driver).click();
		
		Thread.sleep(300);
		OverduePOM.clickDashboard(driver).click();
	}
}
