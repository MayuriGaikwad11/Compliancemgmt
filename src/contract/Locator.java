package contract;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Locator {

	private static WebElement newdoc=null;
	private static WebElement docname=null;
	private static WebElement savedoc=null;
	
	private static WebElement masterclick=null;
	private static WebElement doctype=null;
	private static WebElement fname=null;
	private static WebElement searchdoc=null;
	private static WebElement editdoc=null;
	private static WebElement updatedoc=null;
	private static WebElement closedoc=null;
	private static WebElement deletedoc=null;
	private static WebElement vendormasterclick=null;
	private static WebElement addvendor=null;
	private static WebElement vendorname=null;
	private static WebElement vendoradd=null;
	private static WebElement savevendor=null;
	private static WebElement closevendor=null;
	
	
	public static WebElement setnewdoc(WebDriver driver) 
	{
		docname=driver.findElement(By.xpath("//*[@id='ContentPlaceHolder1_btnAddNew']"));
		return docname;
		
	}

	public static WebElement setdocname(WebDriver driver)
	{
		docname=driver.findElement(By.xpath("//*[@id='tbxDocumentType']"));
		return docname;
	}

    public static WebElement clicksavedoc(WebDriver driver)
    {
    	savedoc=driver.findElement(By.xpath("//*[@id='btnSave']"));
    	return savedoc;
    }
    
    public static WebElement clickclose(WebDriver driver)
    {
    	closedoc=driver.findElement(By.xpath("//*[@id='btnCancel']"));
    	return closedoc;
    }
    
    public static WebElement masterclick(WebDriver driver)
    {
            masterclick=driver.findElement(By.xpath("//*[@id='leftmastermenu']/a"));
            return masterclick;
    }
    
    public static WebElement doctype(WebDriver driver)
    {
    	doctype=driver.findElement(By.xpath("//*[@id='Mastersubmenu']/li[5]/a"));
    	return doctype;
    }
    
    public static WebElement setnewframe(WebDriver driver) 
	{
		fname=driver.findElement(By.xpath("//*[@id='ContentPlaceHolder1_IframeDocType']")); //*[@id="ContentPlaceHolder1_IframeDocType"]
		return fname;
		
	}
    public static WebElement searchdoctype(WebDriver driver)
    {
    	searchdoc=driver.findElement(By.xpath("//*[@id='ContentPlaceHolder1_tbxFilter']"));
    	return searchdoc;
    }
    public static WebElement editdoctype(WebDriver driver)
    {
    	editdoc=driver.findElement(By.xpath("//*[@id='ContentPlaceHolder1_grdContDoctType_lnkEditContractDocType_0']/img"));
    	return editdoc;
    }
    
    public static WebElement updatedoctype(WebDriver driver)
    { 
    	updatedoc=driver.findElement(By.xpath("//*[@id='btnSave']"));
    	return updatedoc;
    }
    
    public static WebElement closedoctype(WebDriver driver)
    {
    	closedoc=driver.findElement(By.xpath("//*[@id='btnCancel']"));
    	return closedoc;
    }

     public static WebElement deletedoctype(WebDriver driver)
     {
    	 deletedoc=driver.findElement(By.xpath("//*[@id='ContentPlaceHolder1_grdContDoctType_lnkDeleteContractDocType_0']/img"));
    	 return deletedoc;
     }
     
     public static WebElement vendortab(WebDriver driver)
     {
    	 vendormasterclick=driver.findElement(By.xpath("//*[@id='Mastersubmenu']/li[3]"));
    	 return vendormasterclick;
     }
     
     public static WebElement addnewvendor(WebDriver driver)
     {
    	 addvendor=driver.findElement(By.xpath("//*[@id='ContentPlaceHolder1_btnAddNew']"));
    	 return addvendor;
     }
     
     public static WebElement vendorname(WebDriver driver)
     {
    	 vendorname=driver.findElement(By.xpath("//*[@id='tbxName']"));
    	 return vendorname;
     }
     
     public static WebElement vendoraddress(WebDriver driver)
     {
    	 vendoradd=driver.findElement(By.xpath("//*[@id='tbxAddress']"));
    	 return vendoradd;
     }
     
     public static WebElement savevendor(WebDriver driver)
     {
    	 savevendor=driver.findElement(By.xpath("//*[@id='btnSave']"));
    	 return savevendor;
     }
     
     public static WebElement closevendor(WebDriver driver)
     {
    	 closevendor=driver.findElement(By.xpath("//*[@id='btnCancel']"));
    	 return closevendor;
     }
	
}
