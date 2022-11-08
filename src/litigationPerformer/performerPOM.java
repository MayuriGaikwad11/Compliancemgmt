package litigationPerformer;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class performerPOM 
{
	private static WebElement litigation = null;		
	private static List<WebElement> elementsList = null;	//WebElement list created for selecting Status-Asc/Desc (Status shows multiple elements back side)
	
	public static WebElement clickNoticeOpen(WebDriver driver)			//Searching 'Open' Notice link
	{
		litigation = driver.findElement(By.xpath("//*[@id='ContentPlaceHolder1_divOpenNoticeCount']"));
		return litigation;
	}
	
	public static WebElement clickNew(WebDriver driver)					//Searching 'New'
	{
		litigation = driver.findElement(By.xpath("//*[@id='ContentPlaceHolder1_AddNewCaseNotice']"));
		return litigation;
	}
	
	public static WebElement clickDated(WebDriver driver)				//Searching 'Dated' input box
	{
		litigation = driver.findElement(By.xpath("//*[@id='txtNoticeDate']"));
		return litigation;
	}
	
	public static WebElement clickFinancialYear(WebDriver driver)		//Searching 'Financial Year' drop down
	{
		litigation = driver.findElement(By.xpath("(//*[@class='multiselect dropdown-toggle btn btn-default'])[1]"));
		return litigation;
	}
	
	public static List<WebElement> chooseDropDownOption(WebDriver driver)	//Searching drop down in 'Financial Year'
	{
		elementsList = driver.findElements(By.xpath("//*[@id='pnlNotice']/div[1]/div[2]/div[2]/div[2]/span[1]/div/ul/li/a/label"));
		return elementsList;
	}
	
	public static WebElement clickAct(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("(//*[@class='multiselect dropdown-toggle btn btn-default'])[2]"));
		return litigation;
	}
	
	public static List<WebElement> chooseAct(WebDriver driver)
	{
		elementsList = driver.findElements(By.xpath("//*[@id='pnlNotice']/div[3]/div[1]/div[1]/span[1]/div/ul/li/a/label/input"));
		return elementsList;
	}
	
	public static List<WebElement> chooseAct1(WebDriver driver)
	{
		elementsList = driver.findElements(By.xpath("//*[@id='pnlCase']/div[4]/div[1]/div[1]/span[1]/div/ul/li/a/label"));
		return elementsList;
	}
	
	public static WebElement clickRefNo(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='tbxRefNo']"));
		return litigation;
	}
	
	public static WebElement clickNoticeType(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='ddlNoticeCategory_chosen']"));
		return litigation;
	}
	
	public static WebElement clickUnderSection(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='tbxSection']"));
		return litigation;
	}
	
	public static WebElement clickSearch(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='ddlNoticeCategory_chosen']/div/div/input"));
		return litigation;
	}
	
	public static WebElement clickOpponent(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("(//*[@class='multiselect dropdown-toggle btn btn-default'])[3]"));
		return litigation;
	}
	
	public static WebElement clickOppLawyer(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("(//*[@class='multiselect dropdown-toggle btn btn-default'])[4]"));
		return litigation;
	}
	
	public static WebElement clickSearch1(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='pnlNotice']/div[4]/div[1]/div[1]/span[1]/div/ul/li[1]/div/input"));
		return litigation;
	}
	
	public static WebElement clickSearch2(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='pnlNotice']/div[4]/div[2]/span/div/ul/li[1]/div/input"));
		return litigation;
	}
	
	public static WebElement clickSelectAll(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='pnlNotice']/div[4]/div[1]/div[1]/span[1]/div/ul/li[2]/a/label"));
		return litigation;
	}
	
	public static WebElement clickSelectAll1(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='pnlNotice']/div[4]/div[2]/span/div/ul/li[2]/a/label"));
		return litigation;
	}
	
	public static WebElement clickNoticeTitle(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='tbxTitle']"));
		return litigation;
	}
	
	public static WebElement clickNoticeDescription(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='tbxDescription']"));
		return litigation;
	}
	
	public static WebElement clickLocation(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='tbxBranch']"));
		return litigation;
	}
	
	public static WebElement clickPlus(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='tvBranchesn1']"));
		return litigation;
	}
	
	public static List<WebElement> selectLocation(WebDriver driver)
	{
		elementsList = driver.findElements(By.xpath("//*[contains(@id,'tvBranchest')]"));
		return elementsList;
	}
	
	public static WebElement clickJurisdiction(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='ddlJurisdiction_chosen']"));
		return litigation;
	}
	
	public static WebElement clickSearch3(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='ddlJurisdiction_chosen']/div/div/input"));
		return litigation;
	}
	
	public static WebElement clickDepartment(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='ddlDepartment_chosen']"));
		return litigation;
	}
	
	public static WebElement clickSearch4(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='ddlDepartment_chosen']/div/div/input"));
		return litigation;
	}
	
	public static WebElement clickContactDept(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='ddlCPDepartment_chosen']"));
		return litigation;
	}
	
	public static WebElement clickSearch5(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='ddlCPDepartment_chosen']/div/div/input"));
		return litigation;
	}
	
	public static WebElement clickNoticeTerm(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='tbxNoticeTerm']"));
		return litigation;
	}
	
	public static WebElement clickOwner(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='ddlOwner_chosen']"));
		return litigation;
	}
	
	public static WebElement clickSearch6(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='ddlOwner_chosen']/div/div/input"));
		return litigation;
	}
	
	public static WebElement clickWinningProspect(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='ddlNoticeRisk_chosen']/a"));
		return litigation;
	}
	
	public static WebElement clickWinningProspect1(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='ddlCaseRisk_chosen']/a"));
		return litigation;
	}
	
	public static WebElement selectRisk(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='ddlNoticeRisk_chosen']/div/ul/li[2]"));
		return litigation;
	}
	
	public static WebElement selectRisk1(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='ddlCaseRisk_chosen']/div/ul/li[2]"));
		return litigation;
	}
	
	public static WebElement clickNoticeBudget(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='tbxNoticeBudget']"));
		return litigation;
	}
	
	public static WebElement clickClaimedAmount(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='tbxClaimedAmt']"));
		return litigation;
	}
	
	public static WebElement clickState(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='ddlState_chosen']"));
		return litigation;
	}
	
	public static WebElement clickSearchState(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='ddlState_chosen']/div/div/input"));
		return litigation;
	}
	
	public static WebElement clickProbableAmount(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='tbxProbableAmt']"));
		return litigation;
	}
	
	public static WebElement clickProvisionalAmount(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='txtprovisionalamt']"));
		return litigation;
	}
	
	public static WebElement clickProtestMoney(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='txtprotestmoney']"));
		return litigation;
	}
	
	public static WebElement clickRisk(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//div[@id='ddlRisk_chosen']"));
		return litigation;
	}
	
	public static WebElement selectRisk2(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//div[@id='ddlRisk_chosen']/div/ul/li[2]"));
		return litigation;
	}
	
	public static WebElement clickPotentialImpactRadio(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='rblPotentialImpact_0']"));
		return litigation;
	}
	
	public static WebElement clickMonetary(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='tbxMonetory']"));
		return litigation;
	}
	
	public static WebElement clickLawFirm(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='ddlLawFirm_chosen']"));
		return litigation;
	}
	
	public static WebElement chooseLawFirm(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='ddlLawFirm_chosen']/div/div/input"));
		return litigation;
	}
	
	public static WebElement clickInternalUser(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("(//*[@class='multiselect dropdown-toggle btn btn-default'])[5]"));
		return litigation;
	}
	
	public static List<WebElement> chooseInternalUser(WebDriver driver)
	{
		elementsList = driver.findElements(By.xpath("//*[@id='pnlNoticeAssignment']/div[1]/div/span[1]/div/ul/li/a/label/input"));
		return elementsList;
	}
	
	public static List<WebElement> chooseInternalUser1(WebDriver driver)
	{
		elementsList = driver.findElements(By.xpath("//*[@id='pnlCaseAssignment']/div[1]/div/span[1]/div/ul/li/a/label"));
		return elementsList;
	}
	
	public static WebElement clickLawyer(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("(//*[@class='multiselect dropdown-toggle btn btn-default'])[6]"));
		return litigation;
	}
	
	public static List<WebElement> chooseLawyer(WebDriver driver)
	{
		elementsList = driver.findElements(By.xpath("//*[@id='UpdatePanel6']/div/span/div/ul/li/a/label"));
		return 		elementsList;
	}
	
	public static WebElement readTotal(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='grid']/div[3]/span"));
		return litigation;
	}
	
	public static WebElement readMessage(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='VSNoticePopup']"));
		return litigation;
	}
	
	public static WebElement readMessage1(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='VSCasePopup']"));
		return litigation;
	}
	
	public static WebElement clickClose(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='btnAddEditcase']"));
		return litigation;
	}
	
	public static WebElement clickLinkNotice(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='lnkLinkNotice']"));
		return litigation;
	}
	
	public static WebElement clickLinkCase(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='lnkLinkCase']"));
		return litigation;
	}
	
	public static WebElement clickViewDoc(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='lnkActDetails']"));
		return litigation;
	}
	
	public static WebElement clickSendMail(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='btnSendMailPopup']"));
		return litigation;
	}
	
	public static WebElement clickSendMail1(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='lnkSendMailWithDoc']"));
		return litigation;
	}
	
	public static WebElement clickEditNotice(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='btnEditNoticeDetail']"));
		return litigation;
	}
	
	public static WebElement clickEditCase(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='btnEditCaseDetail']"));
		return litigation;
	}
	
	public static WebElement clickNoticeClosed(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='ContentPlaceHolder1_divClosedNoticeCount']"));
		return litigation;
	}
	
	public static WebElement clickExcelReport(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='exportReport']"));
		return litigation;
	}
	
	public static WebElement clickCaseOpen(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='ContentPlaceHolder1_divOpenCaseCount']"));
		return litigation;
	}
	
	public static WebElement clickCaseDate(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='txtCaseDate']"));
		return litigation;
	}
	
	public static List<WebElement> clickFinanceSearchCheckbox(WebDriver driver)
	{
		elementsList = driver.findElements(By.xpath("//*[@id='pnlCase']/div[1]/div[2]/div[2]/div[2]/span[1]/div/ul/li/a/label"));
		return elementsList;
	}
	
	public static WebElement clickInternalCaseNo(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='tbxInternalCaseNo']"));
		return litigation;
	}
	
	public static WebElement clickCaseType(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='ddlCaseCategory_chosen']"));
		return litigation;
	}
	
	public static WebElement clickSearchCaseType(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='ddlCaseCategory_chosen']/div/div/input"));
		return litigation;
	}
	
	public static WebElement clickCaseBudget(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='tbxCaseBudget']"));
		return litigation;
	}
	
	public static WebElement clickSearchBox(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("(//*[@placeholder='Search'])[3]"));
		return litigation;
	}
	
	public static WebElement clickSearchBox1(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("(//*[@placeholder='Search'])[4]"));
		return litigation;
	}
	
	public static WebElement clickSelectAll2(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='pnlCase']/div[6]/div[1]/div[1]/span[1]/div/ul/li[2]/a/label"));
		return litigation;
	}
	
	public static WebElement clickSelectAll3(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("(//*[@value='multiselect-all'])[4]"));
		return litigation;
	}
	
	public static WebElement clickCourt(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='ddlCourt_chosen']/a"));
		return litigation;
	}
	
	public static WebElement clickSearchCourt(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='ddlCourt_chosen']/div/div/input"));
		return litigation;
	}
	
	public static WebElement clickJudge(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='tbxJudge']"));
		return litigation;
	}
	
	public static WebElement clickCaseClosed(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='ContentPlaceHolder1_divClosedCaseCount']"));
		return litigation;
	}
	
	public static WebElement clickTaskOpen(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='ContentPlaceHolder1_divOpenTaskCount']"));
		return litigation;
	}
	
	public static WebElement clickAddNewTask(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='ContentPlaceHolder1_lnkAdd']"));
		return litigation;
	}
	
	public static WebElement clickTaskTitle(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='tbxTaskTitle']"));
		return litigation;
	}
	
	public static WebElement clickTaskDesc(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='tbxTaskDesc']"));
		return litigation;
	}
	
	public static WebElement clickDueDate(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='tbxTaskDueDate']"));
		return litigation;
	}
	
	public static WebElement clickPriority(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='ddlTaskPriorityADD_chosen']"));
		return litigation;
	}
	
	public static WebElement clickExpOutcome(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='tbxExpOutcome']"));
		return litigation;
	}
	
	public static WebElement clickInternalUser1(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='ddlTaskLawyerListInternal_chosen']"));
		return litigation;
	}
	
	public static WebElement clickSearchInternalUser1(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='ddlTaskLawyerListInternal_chosen']/div/div/input"));
		return litigation;
	}
	
	public static WebElement clickExternalUser(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='ddlTaskUserExternal_chosen']"));
		return litigation;
	}
	
	public static WebElement clickSearchExternalUser(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='ddlTaskUserExternal_chosen']/div/div/input"));
		return litigation;
	}
	
	public static WebElement clickRemark(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='tbxTaskRemark']"));
		return litigation;
	}
	
	public static WebElement clickUpload(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='fuTaskDocUpload']"));
		return litigation;
	}
	
	public static WebElement clickMessage(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='VsAddTasValidateSuccess']"));
		return litigation;
	}
	
	public static WebElement clickClose1(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@onclick='javascript:reloadTaskList();']"));
		return litigation;
	}
	
	public static WebElement clickTaskClosed(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='ContentPlaceHolder1_divClosedTaskCount']"));
		return litigation;
	}
	
	public static WebElement clickStatusDropDown(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("(//*[@class='k-multiselect-wrap k-floatwrap'])[3]"));
		return litigation;
	}
	
	public static WebElement selectStatusDropDown(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("(//*[@class='k-group k-treeview-lines']/li[1])[2]"));
		return litigation;
	}
	
	public static WebElement GridLoad(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//div[@class='k-grid-content k-auto-scrollable']"));
		return litigation;
	}
	
	public static WebElement clickGridElement(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//div[@class='k-grid-content k-auto-scrollable']/table/tbody/tr[2]/td[1]"));
		return litigation;
	}
	
	public static List<WebElement> clickAction(WebDriver driver)
	{
		elementsList = driver.findElements(By.xpath("//*[@class='k-button k-button-icontext ob-edit k-grid-edit']"));
		return elementsList;
	}
	
	public static WebElement clickCheckBox(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='grdNoticeList_LinkNotice_chkRowLinkCases_0']"));
		return litigation;
	}
	
	public static WebElement clickApply(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//a[@id='lnkLinkCaseFilter']"));
		return litigation;
	}
	
	public static WebElement clickApply1(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//a[@id='lnkLinkNoticeFilter']"));
		return litigation;
	}
	
	public static WebElement clickSave(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@value='Save']"));
		return litigation;
	}
	
	public static WebElement readMsg(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='vsLinkCase']"));
		return litigation;
	}
	
	public static WebElement clickClosePopup(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='divLinkNoticePopup']/div/div/div[1]/button"));
		return litigation;
	}
	
	public static WebElement readRef(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='grdNoticeList_LinkNotice']/tbody/tr[2]/td[3]/div/span"));
		return litigation;
	}
	
	public static List<WebElement> readRef1(WebDriver driver)
	{
		elementsList = driver.findElements(By.xpath("//*[contains(@id,'grdLinkedNotices_lblCaseNo')]"));
		return elementsList;
	}
	
	public static WebElement clickMyWorkspace(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='leftworkspacemenu']"));
		return litigation;
	}
	
	public static WebElement clickStatus(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='childrow']/div/div[3]/div/span"));
		return litigation;
	}
	
	public static WebElement clickStatusPayments(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='lnkNoticeStatusPayment']"));
		return litigation;
	}
	
	public static WebElement clickNoticeStatus(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='ddlNoticeStatus_chosen']"));
		return litigation;
	}
	
	public static WebElement clickClosedStatus(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='ddlNoticeStatus_chosen']/div/ul/li[2]"));
		return litigation;
	}
	
	public static WebElement clickCloseDate(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='tbxNoticeCloseDate']"));
		return litigation;
	}
	
	public static WebElement clickNoticeResult(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='ddlNoticeResult_chosen']"));
		return litigation;
	}
	
	public static WebElement clickSelectResult(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='ddlNoticeResult_chosen']/div/div/input"));
		return litigation;
	}
	
	public static WebElement clickRemark1(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='tbxCloseRemark']"));
		return litigation;
	}
	
	public static WebElement clickSave1(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//input[@id='btnSaveStatus']"));
		return litigation;
	}
	
	public static WebElement readMessage2(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='ValidationSummary3']"));
		return litigation;
	}
	
	public static WebElement clickCourtCaseNo(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='tbxAppealCaseNo']"));
		return litigation;
	}
	
	public static WebElement clickSaveConvertCase(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='btnSaveConvertCase']"));
		return litigation;
	}
	
	public static WebElement clickCaseStatusPayments(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='lnkCaseStatus']"));
		return litigation;
	}
	
	public static WebElement clickCaseStatus(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='ddlCaseStatus_chosen']"));
		return litigation;
	}
	
	public static WebElement clickCaseStatusClose(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='ddlCaseStatus_chosen']/div/ul/li[3]"));
		return litigation;
	}
	
	public static WebElement clickCaseCloseDate(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='tbxCaseCloseDate']"));
		return litigation;
	}
	
	public static WebElement clickCaseResult(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='ddlCaseResult_chosen']"));
		return litigation;
	}
	
	public static WebElement clickSelectCaseResult(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='ddlCaseResult_chosen']/div/div/input"));
		return litigation;
	}
	
	public static WebElement clickCaseStage(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='ddlCaseStage_chosen']"));
		return litigation;
	}
	
	public static WebElement selectCaseStage(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='ddlCaseStage_chosen']/div/div/input"));
		return litigation;
	}
	
	public static WebElement clickCaseCheckBox(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='grdCaseList_LinkCase_chkRowLinkCases_0']"));
		return litigation;
	}
	
	public static WebElement readCaseRef(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='grdCaseList_LinkCase']/tbody/tr[2]/td[3]/div/span"));
		return litigation;
	}
	
	public static List<WebElement> readCaseRef1(WebDriver driver)
	{
		elementsList = driver.findElements(By.xpath("//*[contains(@id,'grdLinkedCases_lblCaseNo')]"));
		return elementsList;
	}
	
	public static WebElement clickClosePopupCase(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='divLinkCasePopup']/div/div/div[1]/button"));
		return litigation;
	}
	
	public static WebElement clickMyReports(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='leftreportsmenu']"));
		return litigation;
	}
	
	public static WebElement CheckRecordsTable(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='grid']"));
		return litigation;
	}
	
	public static WebElement clickTypeDropdown(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@aria-owns='dropdownType_listbox']"));
		return litigation;
	}
	
	public static WebElement selectTypeCase(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='dropdownType_listbox']/li[2]"));
		return litigation;
	}
	
	public static WebElement selectTypeTask(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='dropdownType_listbox']/li[3]"));
		return litigation;
	}
	
	public static WebElement clickMyReminder(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='leftremindersmenu'][@class='leftdummy']"));
		return litigation;
	}
	
	public static WebElement clickAddNew1(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='Addnew']"));
		return litigation;
	}
	
	public static WebElement clickType(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='ddlTypePopup_chosen']"));
		return litigation;
	}
	
	public static WebElement clickTitle(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='ddlTitlePopup_chosen']"));
		return litigation;
	}
	
	public static WebElement clickReminderText(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='txtReminderTitle']"));
		return litigation;
	}
	
	public static WebElement clickDescription(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='txtReminderDesc']"));
		return litigation;
	}
	
	public static WebElement clickRemark2(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='txtRemark']"));
		return litigation;
	}
	
	public static WebElement clickDate(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='txtRemindOn']"));
		return litigation;
	}
	
	public static WebElement readMsg1(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='vsReminder']"));
		return litigation;
	}
	
	public static WebElement clickCloseReminder(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@onclick='CloseMyReminderPopup();']"));
		return litigation;
	}
	
	public static WebElement clickMasters(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='leftmastermenu']"));
		return litigation;
	}
	
	public static WebElement clickMastersMenu(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='leftmastermenu']/ul"));
		return litigation;
	}
	
	public static WebElement clickAddNew2(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='ContentPlaceHolder1_btnAddPromotor']"));
		return litigation;
	}
	
	public static WebElement clickCaseNoticeType(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='tbxCaseType']"));
		return litigation;
	}
	
	public static WebElement readMesg(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@id='ValidationSummary1']"));
		return litigation;
	}
	
	public static WebElement clickClose2(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath("//*[@onclick='RefreshParent()']"));
		return litigation;
	}
	
	public static WebElement clickDtei(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath(""));
		return litigation;
	}
	
	public static WebElement clickDtevrt(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath(""));
		return litigation;
	}
	
	public static WebElement clickDteir(WebDriver driver)
	{
		litigation = driver.findElement(By.xpath(""));
		return litigation;
	}
}
