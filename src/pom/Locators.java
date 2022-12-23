package pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Locators {
	

	private static WebElement uName=null;
	private static WebElement pass=null;
	private static WebElement sumit=null;
	
	public static WebElement setUname(WebDriver driver) {
		uName=driver.findElement(By.xpath("//*[@id='txtemail']"));
		return uName;
	}
	 
	public static WebElement setPass(WebDriver driver) {
		pass=driver.findElement(By.xpath("//*[@id='txtpass']"));
		return pass;
	}
	
	public static WebElement clickSumit(WebDriver driver) {
		sumit=driver.findElement(By.xpath("//*[@id='Submit']"));
		return sumit;
	}
	
}
