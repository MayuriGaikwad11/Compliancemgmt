package pom;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

public class TestCases {
	public static WebDriver driver =null;

	@Test
	void Login1() throws InterruptedException {
		
		Methods.Login(driver);
		
	}
	
}
