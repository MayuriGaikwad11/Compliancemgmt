package contract;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Method {
	public static WebDriver driver = null;
	
	public static void login() throws InterruptedException
	{
		System.setProperty("webdriver.chrome.driver","C:/March2022/PerformerPom/Driver1/chromedriver.exe"); 
		  
		   driver=new ChromeDriver();                                    //Created new Chrome driver instance.
		  
		  driver.manage().window().maximize();                                     //Set window size to maximum.
		  
		  driver.get("https://login.teamleaseregtech.com");     //Set the URL of WebApplication.
		  
		  WebElement userName=driver.findElement(By.xpath("//*[@id='txtemail']"));
		  userName.sendKeys("sandeep@bitaconsulting.co.in");                              //Sent username to input box 
		  
		  WebElement password=driver.findElement(By.xpath("//*[@id='txtpass']"));
		  password.sendKeys("admin@123");                                            //Sent password to input box
		  
		  WebElement signBtn=driver.findElement(By.xpath("//*[@id='Submit']"));
		  signBtn.click();                                                          //Clicked on Sign-in button
		  
		  WebElement DonotAcc=driver.findElement(By.xpath("//*[@id='lnkSecurityQA']"));
		  DonotAcc.click();                                                          //Clicking on QA Link instead of OTP.
		   Thread.sleep(3000);
		   WebElement que1=driver.findElement(By.xpath("//*[@id='lblQuestion1']"));
		   WebElement que2=driver.findElement(By.xpath("//*[@id='lblQuestion2']"));
		   WebElement firstQ=driver.findElement(By.id("txtAnswer1"));
		   WebElement secQ=driver.findElement(By.xpath("//*[@id='txtAnswar2']"));
		   String question1=que1.getText();
		   if(question1.contains("power"))
		   
		  
		  firstQ.sendKeys("power");
		   else if(question1.contains("car"))
			   firstQ.sendKeys("car");
			   
		    
		   else
		   {
			   firstQ.sendKeys("pet");
		   }
		  //Sending answer to the input box.
		
		  String question2=que2.getText();
		  if(question2.contains("power"))
		  secQ.sendKeys("power");                                              //Sending answer to the input box.
		  else if(question2.contains("car"))
			  secQ.sendKeys("car");
		  else
			  secQ.sendKeys("pet");
		                                            //Sending answer to the input box.
		  
		  WebElement validate=driver.findElement(By.xpath("//*[@id='btnValidateQuestions']"));
		  validate.click();                                        //Clicking on Validate button.
		  
		  WebElement contractbox=driver.findElement(By.xpath("//*[@id='dvbtnContract']/div[1]/img"));
		  contractbox.click();
		  Thread.sleep(4000);
		
	}
		 
        public static void vendoroperations() throws InterruptedException
       {
	 	 
        Thread.sleep(5000);
       
        WebElement mastermenu=driver.findElement(By.xpath("//*[@id='leftmastermenu']"));
		  mastermenu.click();
	     Locator.vendortab(driver).click();
	     Thread.sleep(3000);
	  
     	 
	     Locator.addnewvendor(driver).click();
	     Thread.sleep(3000);
	     driver.switchTo().frame("ContentPlaceHolder1_IframePartyDetial");
	     Thread.sleep(3000);
	     Locator.vendorname(driver).sendKeys("Trupti Vendors");
	     Thread.sleep(2000);
	     Locator.vendoraddress(driver).sendKeys("Pune");
	     Thread.sleep(2000);
	     Locator.savevendor(driver).click();
	     Thread.sleep(2000);
	     Locator.closevendor(driver).click();
       }
	
}
