package contract;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestCase {

	public static WebDriver driver = null;
	 
	 @BeforeTest
	  void login() throws InterruptedException
	 {
		 Method.login();
		 
     }
	 
	 
	 @Test
	 void vendormaster() throws InterruptedException
	 {
		 Thread.sleep(4000);
		 Method.vendoroperations();
	 }
	 
	
}
