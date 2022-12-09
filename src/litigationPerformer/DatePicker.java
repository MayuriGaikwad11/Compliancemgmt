package litigationPerformer;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class DatePicker {
	
	
	@Test(priority = 1)
	void Date() throws InterruptedException
	{
		String month="January 2023";
		String day="15";
		System.setProperty("webdriver.chrome.driver","C:/March2022/PerformerPom/Driver1/chromedriver.exe");
		WebDriver driver=new ChromeDriver();
		
		driver.manage().window().maximize();
		
		driver.get("http://www.phptravels.net/");
		
		driver.findElement(By.xpath("//*[@id='checkin']")).click();
		Thread.sleep(2000);
		
		while(true)
		{
	        String text=driver.findElement(By.xpath("//*[@id='fadein']/div[8]/div[1]/table/thead/tr[1]/th[2]")).getText();
	           if(text.equals(month)) {
		                       break;
	            }else
	            {
	            	Thread.sleep(2000);
		        driver.findElement(By.xpath("//*[@id='fadein']/div[8]/div[1]/table/thead/tr[1]/th[3]")).click();
	}
		}
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@id='fadein']/div[8]/div[1]/table/tbody/tr/td[contains(text(),"+day+")]")).click();
		
		
	}
}
