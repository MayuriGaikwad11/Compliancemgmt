package reviewer;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ReviewerPOM 
{
	private static WebElement statutoryReview = null;		//WebElement variable created for clicking on value of Statutory 'Pending for Review'
	private static WebElement statutoryAction = null;		//WebElement variable created for clicking on Action button.
	private static WebElement download = null;				//WebElement variable created for clicking on 'Download' link.
	private static WebElement view = null; 					//WebElement variable created for clicking on 'View' link.
	private static WebElement closeView = null;				//WebElement variable created for clicking on Close View cross button.
	private static WebElement closedDelay = null;			//WebElement variable created for clicking on Closed-Delayed radio button.
	private static WebElement closedTimely = null;			//WebElement variable created for clicking on Closed-Timely radio button.
	private static WebElement checkBox = null;				//WebElement variable created for clicking on Checkbox
	private static WebElement liability1 = null;			//WebElement variable created for inserting Liability as per system
	private static WebElement liability2 = null;			//WebElement variable created for inserting Liability as per return
	private static WebElement liability3 = null;			//WebElement variable created for inserting Liability paid
	private static WebElement textArea = null;				//WebElement variable created for inserting Remark
	private static WebElement approve = null;				//WebElement variable created for clicking on Approve button
	private static WebElement reject = null;				//WebElement variable created for clicking on Reject button
	private static WebElement statutoryRejectValue = null;	//WebElement variable created for reading Statutory Reject value 
	private static WebElement dashboard = null;				//WebElement variable created for clicking on dashboard link
	private static WebElement internalReview = null;		//WebElement variable created for clicking on value of Internal 'Pending For Review'
	private static WebElement closeViewInternal = null;		//WebElement variable created for clicking on Close View cross button.
	private static WebElement closedTimelyInternal = null;	//WebElement variable created for clicking on Closed-Timely radio button.
	private static WebElement textAreaInternal = null;		//WebElement variable created for inserting Remark for Internal click Text area
	private static WebElement internalRejectValue = null;	//WebElement variable created for reading Internal Reject value.
	private static List<WebElement> elementsList = null;	//WebElement list created for selecting Status-Asc/Desc (Status shows multiple elements back side)
	private static WebElement AdavanceSearch = null;		//WebElement variable created for clicking on value of Statutory 'Pending for Review'

	public static WebElement ComplainceInternalReviewer(WebDriver driver)
	{
		statutoryReview = driver.findElement(By.xpath("//*[@id='ComplainceInternalReviewer']/div/div/div[1]/button"));
		return statutoryReview;
	}
	
	public static WebElement clickStatutoryReview(WebDriver driver)		//Method for searching Statutory Review value element.
	{
		statutoryReview = driver.findElement(By.xpath("//div[@id='ContentPlaceHolder1_divReviewerPendingforReviewePREOcount']"));
		return statutoryReview;
	}
	
	public static WebElement clickAction(WebDriver driver)			//Method for searching action button
	{
		statutoryAction = driver.findElement(By.xpath("//div[@id='grid']/div[4]/table/tbody/tr[1]/td[17]/a"));	//XPath for clicking third action button  
		return statutoryAction;
	}
	
	public static WebElement clickAdavanceSearch(WebDriver driver)			//Method for searching action button
	{
		AdavanceSearch = driver.findElement(By.xpath("//*[@id='AdavanceSearch']"));	//XPath for clicking third action button  
		return AdavanceSearch;
	}
	
	public static WebElement clickAction1(WebDriver driver)			//Method for searching action button
	{
		statutoryAction = driver.findElement(By.xpath("(//*[@class='k-button k-button-icontext ob-overview k-grid-edit2'])[1]"));	//XPath for clicking first action button  
		return statutoryAction;
	}
	
	public static List<WebElement> clickAction2(WebDriver driver)			//Method for searching action button
	{
		elementsList = driver.findElements(By.xpath("//*[@id='grid1']/div[3]/table/tbody/tr/td[22]/a[1]"));	//XPath for clicking first action button  
		return elementsList;
	}
	//*[@class='k-button k-button-icontext ob-overview k-grid-edit2'])[1]
	public static List<WebElement> clickActions(WebDriver driver)
	{
		elementsList = driver.findElements(By.xpath("//*[@class='k-button k-button-icontext ob-overview k-grid-edit2']"));
		return elementsList;
	}
	
	public static WebElement clickDownload(WebDriver driver)		//Method searching Download link
	{
		download = driver.findElement(By.linkText("Download"));
		return download;
	}
	
	public static WebElement CloseAS(WebDriver driver)		//Method searching Download link
	{
		download = driver.findElement(By.linkText("/html/body/div[35]/div[1]/div/a"));
		return download;
	}
	
	public static WebElement clickDownload1(WebDriver driver)		//Method searching Download link
	{
		download = driver.findElement(By.xpath("//*[@id = 'rptComplianceVersion_lblpathDownload_0']"));
		return download;
	}
	
	public static WebElement clickDownload1Document(WebDriver driver)		//Method searching Download link
	{
		download = driver.findElement(By.xpath("//a[@id='rptComplianceDocumnets_lblCompDocpathDownload_0']"));
		return download;
	}
	
	public static WebElement clickDownload2(WebDriver driver)				//Method searching Download link
	{
		download = driver.findElement(By.xpath("//*[@id = 'rptComplianceVersion_btnComplinceVersionDoc_0']"));
		return download;
	}
	
	public static WebElement clickDownloadInternal1(WebDriver driver)		//Method searching Download link
	{
		download = driver.findElement(By.xpath("//*[@id = 'rptComplianceVersion3_lblInternalpathDownload_0']"));
		return download;
	}
	
	public static WebElement clickView(WebDriver driver)			//Method for searching View link
	{
		view = driver.findElement(By.linkText("View"));
		return view;
	}
	
	public static WebElement clickView1(WebDriver driver)			//Method for searching View link
	{
		view = driver.findElement(By.xpath("//a[@id='rptComplianceVersion_lnkViewDoc_0']"));
		return view;
	}
	
	public static WebElement clickCloseView(WebDriver driver)		//Method for searching Close View cross
	{
		closeView = driver.findElement(By.xpath("//div[@id='DocumentReviewPopUp1']/div/div/div[1]/button"));
		return closeView;
	}
	
	public static WebElement clickCloseView1(WebDriver driver)		//Method for searching Close View cross
	{
		closeView = driver.findElement(By.xpath("//div[@id='DocumentPopUpSampleForm']/div/div/div[1]/button"));
		return closeView;
	}
	
	public static WebElement clickCloseViewInternal(WebDriver driver)	//Method for searching Internal Close View cross 
	{
		closeViewInternal = driver.findElement(By.xpath("//div[@id='DocumentReviewPopUp']/div/div/div[1]/button"));
		return closeViewInternal;
	}
	
	public static WebElement clickCloseViewInternal1(WebDriver driver)	//Method for searching Internal Close View cross 
	{
		closeViewInternal = driver.findElement(By.xpath("//*[@id='modalDocumentReviewerViewerInternal']/div/div/div[1]/button"));
		return closeViewInternal;
	}
	
	public static WebElement clickClosedDelayed(WebDriver driver)		//Method searching Closed-Delayed radio button
	{
		closedDelay = driver.findElement(By.xpath("//*[@id = 'rdbtnStatus1_0']"));
		return closedDelay;
	}
	
	public static WebElement clickClosedTimely(WebDriver driver)		//Method for searching Closed-Timely radio button
	{
		closedTimely = driver.findElement(By.xpath("//*[@id='rdbtnStatus1']/tbody/tr/td[2]/label"));
		return closedTimely;
	}
	
	public static WebElement clickClosedTimelyAS(WebDriver driver)		//Method for searching Closed-Timely radio button
	{
		closedTimely = driver.findElement(By.xpath("//*[@id='rdbtnStatus1_1']"));
		return closedTimely;
	}
	
	public static WebElement clickDownloadAS(WebDriver driver)		//Method for searching Closed-Timely radio button
	{
		closedTimely = driver.findElement(By.xpath("//*[@id='rptComplianceVersion_btnComplinceVersionDoc_0']"));
		return closedTimely;
	}
	
	public static WebElement clickInternalDownloadAS(WebDriver driver)		//Method for searching Closed-Timely radio button
	{
		closedTimely = driver.findElement(By.xpath("//*[@id='rptComplianceVersion3_btnComplinceVersionDoc1_0']"));
		return closedTimely;
	}
	
	
	public static WebElement clickClosedTimelyInternal(WebDriver driver)	//Method for closing View opened in Internal View
	{
		closedTimelyInternal = driver.findElement(By.xpath("//*[@id='rdbtnStatus3_1']"));
		return closedTimelyInternal;
	}
	
	public static WebElement clickCheckBox(WebDriver driver)			//Method for searching check box
	{
		checkBox = driver.findElement(By.xpath("//*[@id='chkPenaltySaveReview']"));
		return checkBox;
	}
	
	public static WebElement insertLiability1(WebDriver driver)			//Method for searching Liability for System text box
	{
		liability1 = driver.findElement(By.xpath("//input[@id='txtValueAsPerSystem']"));
		return liability1;
	}
	
	public static WebElement insertLiability2(WebDriver driver)			//Method for searching Liability for return text box
	{
		liability2 = driver.findElement(By.xpath("//input[@id='txtValueAsPerReturn']"));
		return liability2;
	}
	
	public static WebElement insertLiability3(WebDriver driver)			//Method for searching Liability Paid text box
	{
		liability3 = driver.findElement(By.xpath("//input[@id='txtLiabilityPaid']"));
		return liability3;
	}
	
	public static WebElement insertTextArea(WebDriver driver)			//Method for searching text area
	{
		textArea = driver.findElement(By.xpath("//*[@id='tbxRemarks1']"));
		return textArea;
	}
	
	public static WebElement insertTextAreaInternal(WebDriver driver)	//Method for searching Internal text area
	{
		textAreaInternal = driver.findElement(By.xpath("//textarea[@id='tbxRemarks3']"));
		return textAreaInternal;
	}
	
	public static WebElement clickApprove(WebDriver driver)				//Method for searching Approve button
	{
		approve = driver.findElement(By.xpath("//input[@value='Approve']"));
		return approve;
	}
	
	public static WebElement clickReject(WebDriver driver)				//Method searching Reject button
	{
		reject = driver.findElement(By.xpath("//input[@value='Reject']"));
		return reject;
	}
	
	public static WebElement readStatutoryReject(WebDriver driver)		//Method for searching Statutory Reject button to read statutory value  
	{
		statutoryRejectValue = driver.findElement(By.xpath("//div[@id='ContentPlaceHolder1_divReviewerRejectedPREOcount']"));
		return statutoryRejectValue;
	}
	
	public static WebElement clickDashboard(WebDriver driver)			//Method for searching 'My Dashboard' link
	{
		dashboard = driver.findElement(By.linkText("My Dashboard "));
		return dashboard;
	}
	
	public static WebElement clickInternalReview(WebDriver driver)		//Method for searching Internal Review value element.
	{
		internalReview = driver.findElement(By.xpath("//div[@id='ContentPlaceHolder1_divReviewerPendingforRevieweInternalPREOcount']"));
		return internalReview;
	}
	
	public static WebElement readInternalReject(WebDriver driver)
	{
		internalRejectValue = driver.findElement(By.xpath("//div[@id='ContentPlaceHolder1_divReviewerRejectedInternalPREOcount']"));
		return internalRejectValue;
	}
	
	public static List<WebElement> clickStatus(WebDriver driver) 		//Method to get list of action buttons on web page
	{
		elementsList = driver.findElements(By.xpath("//*[contains(text(),'Status')][@class='k-link']"));
		return elementsList;
	}
	
	public static WebElement clickLastPage(WebDriver driver)			//Searching Last Page arrow button
	{
		statutoryAction = driver.findElement(By.xpath("//*[@title='Go to the last page']"));
		return statutoryAction;
	}
	
	public static WebElement InterimApproveRadio(WebDriver driver)		//Searching radio button for Interim Approve
	{
		statutoryAction = driver.findElement(By.xpath("//*[@id='rdbtnStatus1_0']"));
		return statutoryAction;
	}
	
	public static WebElement clickMyEscalation(WebDriver driver)		//Searching 'My Escalation' link
	{
		statutoryAction = driver.findElement(By.linkText("My Escalation"));
		return statutoryAction;
	}
	
	public static WebElement clickShowDropdown(WebDriver driver)		//Clicking on Show dropdown
	{
		statutoryAction = driver.findElement(By.xpath("//*[@id='ContentPlaceHolder1_ddlPageSize']"));
		return statutoryAction;
	}
	
	public static WebElement checkEscalationTable(WebDriver driver)		//Searching records table to check visibility
	{
		statutoryAction = driver.findElement(By.xpath("//*[@id='ContentPlaceHolder1_grdReviewerComplianceDocument']"));
		return statutoryAction;
	}
	
	public static List<WebElement> clickWorkFileText(WebDriver driver)	//Searching all 'Work File Timeline' text boxes
	{
	//	elementsList = driver.findElements(By.xpath("//*[@class='k-grid-content k-auto-scrollable']/table/tbody/tr/td[9]/input"));
		elementsList = driver.findElements(By.xpath("//*[@id='grid']/div[3]/table/tbody/tr/td[11]/input"));
		return elementsList;
	}
	
	public static List<WebElement> clickOverView(WebDriver driver)	//Searching all 'Work File Timeline' text boxes
	{
		elementsList = driver.findElements(By.xpath("//*[@id='grid']/div[4]/table/tbody/tr/td[23]/a[5]"));
		return elementsList;
	}
	
	public static List<WebElement> clickOverViewE(WebDriver driver)	//Searching all 'Work File Timeline' text boxes
	{
		elementsList = driver.findElements(By.xpath("//*[@id='grid']/div[3]/table/tbody/tr/td[11]/a[2]"));
		return elementsList;//*[@id="grid"]/div[3]/table/tbody/tr[1]/td[7]/a
	}
	
	public static List<WebElement> clickOverViewAE(WebDriver driver)	//Searching all 'Work File Timeline' text boxes
	{
		elementsList = driver.findElements(By.xpath("//*[@id='grid']/div[3]/table/tbody/tr/td[7]/a"));
		return elementsList;
	}
	
	public static List<WebElement> clickOverViewCE(WebDriver driver)	//Searching all 'Work File Timeline' text boxes
	{
		elementsList = driver.findElements(By.xpath("//*[@id='grid']/div[3]/table/tbody/tr/td[11]/a"));
		return elementsList;
	}
	
	public static List<WebElement> clickEscalationText(WebDriver driver)	//Searching all 'Escalation' text boxes
	{
		//elementsList = driver.findElements(By.xpath("//*[@class='k-grid-content k-auto-scrollable']/table/tbody/tr/td[10]/input"));
		elementsList = driver.findElements(By.xpath("//*[@id='grid']/div[3]/table/tbody/tr/td[12]/input"));
		return elementsList;
	}
	
	public static WebElement loadNextPage(WebDriver driver)				//Searching Next Page load button.
	{
		statutoryAction = driver.findElement(By.xpath("//*[@id='ContentPlaceHolder1_lBNext']"));
		return statutoryAction;
	}
	
	public static List<WebElement> getAllButtons(WebDriver driver)		//Searching all 'Action' buttons
	{
		elementsList = driver.findElements(By.xpath("//*[@class='k-button k-button-icontext ob-edit k-grid-edit']"));
		return elementsList;
	}
	
	public static WebElement clickFirstAction(WebDriver driver)		//Searching all 'Action' buttons
	{
		statutoryAction = driver.findElement(By.xpath("(//*[@class='k-button k-button-icontext ob-edit k-grid-edit' and not(@disabled)])[1]"));
		return statutoryAction;
	}
	//*[@class = 'k-checkbox-label k-no-text']
	public static WebElement clickUpdate(WebDriver driver)
	{
		statutoryAction = driver.findElement(By.xpath("//*[@class='k-button k-button-icontext k-primary k-grid-update']"));
		return statutoryAction;
	}
	
	public static WebElement CloseOverview(WebDriver driver)
	{
		statutoryAction = driver.findElement(By.xpath("//*[@id='divOverView']/div/div/div[1]/button"));
		return statutoryAction;
	}
	
	public static WebElement CloseOverview1(WebDriver driver)
	{
		statutoryAction = driver.findElement(By.xpath("//*[@class='k-button k-bare k-button-icon k-window-action']"));
		return statutoryAction;
	}
	
	
	public static WebElement ReadCount(WebDriver driver)
	{
		statutoryAction = driver.findElement(By.xpath("//*[@id='grid']/div[5]/span[2]"));
		return statutoryAction;
	}
	
	public static WebElement ReadCountEvent(WebDriver driver)
	{
		statutoryAction = driver.findElement(By.xpath("//*[@id='grid']/div[4]/span[2]"));
		return statutoryAction;
	}
	
	public static WebElement CalendarDownload(WebDriver driver)
	{
		statutoryAction = driver.findElement(By.xpath("//*[@id='export']"));
		return statutoryAction;
	}
	
	
	
	public static List<WebElement> clickCheckboxes(WebDriver driver)
	{
		elementsList = driver.findElements(By.xpath("//*[@id='grid']/div[3]/table/tbody/tr/td[1]"));
	//	elementsList = driver.findElements(By.xpath("//*[@class='k-checkbox-label k-no-text']"));
		return elementsList;                          
	}
	
	public static WebElement clickSaveButton(WebDriver driver)			//Searching drop down of User to Assign
	{
		statutoryAction = driver.findElement(By.xpath("//*[@id = 'btnsave']"));
		return statutoryAction;
	}
	
	public static WebElement clickRisk(WebDriver driver)			//Searching drop down of User to Assign
	{
		statutoryAction = driver.findElement(By.xpath("//*[@id='example']/div[1]/div[2]/div[2]"));
		return statutoryAction;
	}
	
	public static WebElement clickCritical(WebDriver driver)			//Searching drop down of User to Assign
	{
		statutoryAction = driver.findElement(By.xpath("(//li[@class='k-item k-first'])[1]"));
		return statutoryAction;
	}
	
	public static WebElement clickClear(WebDriver driver)			//Searching drop down of User to Assign
	{
		statutoryAction = driver.findElement(By.xpath("//*[@id='ClearfilterMain']"));
		return statutoryAction;
	}
	
	
	public static WebElement selectUserAssign(WebDriver driver)			//Searching drop down of User to Assign
	{
		statutoryAction = driver.findElement(By.xpath("//*[@id='ContentPlaceHolder1_ddlNewUsers']"));
		return statutoryAction;
	}
	
	public static WebElement readAssignedUser(WebDriver driver)			//Searching name of user assigned in first row.
	{
		statutoryAction = driver.findElement(By.xpath("//*[@id='ContentPlaceHolder1_grdComplianceInstances_lbluser_0']"));
		return statutoryAction;
	}
	
	public static WebElement clickFirstCheckbox(WebDriver driver)		//Searching first checkbox
	{
		statutoryAction = driver.findElement(By.xpath("//*[@id='ContentPlaceHolder1_grdComplianceInstances_chkCompliances_0']"));
		return statutoryAction;
	}
	
	public static WebElement ClickReviewerTaskPFRStatutory(WebDriver driver)		//Searching first checkbox
	{
		statutoryAction = driver.findElement(By.xpath("//*[@id='ContentPlaceHolder1_DivReviewerPendingReviewStat']"));
		return statutoryAction;
	}
	
	public static WebElement ClickReviewerTaskPFRInternal(WebDriver driver)		//Searching first checkbox
	{
		statutoryAction = driver.findElement(By.xpath("//*[@id='ContentPlaceHolder1_DivReviewerPendingReviewInt']"));
		return statutoryAction;
	}
	
	public static WebElement ReviewerTaskDBNSStatutory(WebDriver driver)		//Searching first checkbox
	{
		statutoryAction = driver.findElement(By.xpath("//*[@id='ContentPlaceHolder1_DivReviewerDueNotSubStat']"));
		return statutoryAction;
	}
	
	public static WebElement ReviewerTaskDBNSInternal(WebDriver driver)		//Searching first checkbox
	{
		statutoryAction = driver.findElement(By.xpath("//*[@id='ContentPlaceHolder1_DivReviewerDueNotSubInt']"));
		return statutoryAction;
	}
	
	public static WebElement Clickclose(WebDriver driver)		//Searching first checkbox
	{
		statutoryAction = driver.findElement(By.xpath("//*[@id='ComplainceReviewer1']/div/div/div[1]/button"));
		return statutoryAction;
	}
	
	public static WebElement ClickINclose(WebDriver driver)		//Searching first checkbox
	{
		statutoryAction = driver.findElement(By.xpath("//*[@id='ComplainceInternalReviewer1']/div/div/div[1]/button"));
		return statutoryAction;
	}
	
	public static WebElement ClickCompletedSta(WebDriver driver)		//Searching first checkbox
	{
		statutoryAction = driver.findElement(By.xpath("//*[@id='ContentPlaceHolder1_divCompletedPREOcount']"));
		return statutoryAction;
	}
	
	public static WebElement ClickCompletedIn(WebDriver driver)		//Searching first checkbox
	{
		statutoryAction = driver.findElement(By.xpath("//*[@id='ContentPlaceHolder1_divCompletedInternalPREOcount']"));
		return statutoryAction;
	}
	
	public static WebElement ClickOverdueSta(WebDriver driver)		//Searching first checkbox
	{
		statutoryAction = driver.findElement(By.xpath("//*[@id='ContentPlaceHolder1_divDueButNotSubmittedPREOcount']"));
		return statutoryAction;
	}
	
	public static WebElement ClickOverdueIn(WebDriver driver)		//Searching first checkbox
	{
		statutoryAction = driver.findElement(By.xpath("//*[@id='ContentPlaceHolder1_divDueButNotSubmittedInternalPREOcount']"));
		return statutoryAction;
	}
	
	public static WebElement ClickEvents(WebDriver driver)		//Searching first checkbox
	{
		statutoryAction = driver.findElement(By.xpath("//*[@id='ContentPlaceHolder1_divOverdueEventReviewer']"));
		return statutoryAction;
	}
	
	public static WebElement ClickActivatedEvents(WebDriver driver)		//Searching first checkbox
	{
		statutoryAction = driver.findElement(By.xpath("//*[@id='ContentPlaceHolder1_divActivatedEventReviewer']"));
		return statutoryAction;
	}
	
	public static WebElement ClickClosedEvents(WebDriver driver)		//Searching first checkbox
	{
		statutoryAction = driver.findElement(By.xpath("//*[@id='ContentPlaceHolder1_divClosedEventReviewer']"));
		return statutoryAction;
	}
	
	
	public static WebElement closeAS(WebDriver driver)		//Searching first checkbox
	{
		statutoryAction = driver.findElement(By.xpath("//*[@class='k-button k-bare k-button-icon k-window-action']"));
		return statutoryAction;
	}
	
	public static WebElement sledtre(WebDriver driver)					//
	{
		statutoryAction = driver.findElement(By.xpath(""));
		return statutoryAction;
	}
	
	public static WebElement seletldere(WebDriver driver)				//
	{
		statutoryAction = driver.findElement(By.xpath(""));
		return statutoryAction;
	}
}