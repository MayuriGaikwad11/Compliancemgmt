package pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Methods {

	
	public static void Login(WebDriver driver) throws InterruptedException {
		
		 System.setProperty("webdriver.chrome.driver","C:/March2022/PerformerPom/Driver1/chromedriver.exe"); 
		  
		  driver=new ChromeDriver();       //Created new Chrome driver instance.
		  Thread.sleep(3000);
		  driver.manage().window().maximize();       //Set window size to maximum.
		  Thread.sleep(3000);
		  driver.get("https://login.teamleaseregtech.com/Login.aspx?ReturnUrl=%2f&Session=Expired");     //Set the URL of WebApplication.
		  Thread.sleep(3000);
		  Locators.setUname(driver).sendKeys("performer@avantis.info");
		  Thread.sleep(3000);
		  Locators.setPass(driver).sendKeys("admin@123");
		  Thread.sleep(3000);
		  Locators.clickSumit(driver).click();
		  
	}
	
}
