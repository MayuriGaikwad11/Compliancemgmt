package login;
	import org.openqa.selenium.By;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;

	public class LoginPOM 
	{
		private static WebElement uname = null;			//WebElement variable created for Username input 
		private static WebElement pass = null;			//WebElement variable created for Password input
		private static WebElement submit = null;		//WebElement variable created for Submit button click
		private static WebElement QALink = null;		//WebElement variable created for Question Answer link after comes up after login
		private static WebElement question1 = null;		//WebElement variable created for question1 element
		private static WebElement question2 = null;		//WebElement variable created for question2 element
		private static WebElement ans1 = null;			//WebElement variable created for Answer1 input
		private static WebElement ans2 = null;			//WebElement variable created for Answer2 input
		private static WebElement validate = null;		//WebElement variable created for Answer Submit/Validate button
		private static WebElement comp_img = null;		//WebElement variable created for Compliance Image click
		private static WebElement liti_img = null;
		public static WebElement setUname(WebDriver driver)		//Method for searching Username input
		{
			uname = driver.findElement(By.xpath("//input[@placeholder='Username']"));
			return uname;
		}
		
		public static WebElement setPassword(WebDriver driver)	//Method for searching Password input
		{
			pass =driver.findElement(By.xpath("//input[@placeholder='Password']"));
			return pass;
		}
		
		public static WebElement readMsg(WebDriver driver)	//Method for searching Password input
		{
			pass =driver.findElement(By.xpath("//*[@id='vsLogin']/ul/li"));
			return pass;
		}
		
		public static WebElement readMsg1(WebDriver driver)	//Method for searching Password input
		{
			pass =driver.findElement(By.xpath("//*[@id='ValidationSummary2']/ul/li"));
			return pass;
		}
		
		public static WebElement readMsg2(WebDriver driver)	//Method for searching Password input
		{
			pass =driver.findElement(By.xpath("//*[@id='vsLogin']/ul/li"));
			return pass;
		}
		
		public static WebElement readMsg3(WebDriver driver)	//Method for searching Password input
		{
			pass =driver.findElement(By.xpath("//*[@id='ValidationSummary3']/ul/li"));
			return pass;
		}
		
		public static WebElement readMsg4(WebDriver driver)	//Method for searching Password input
		{
			pass =driver.findElement(By.xpath("//*[@id='ValidationSummary3']/ul/li"));
			return pass;
		}
		
		public static WebElement clickSubmit(WebDriver driver)	//Method for searching button for Save/Sign-in
		{
			submit = driver.findElement(By.xpath("//input[@name='Submit']"));
			return submit;
		}
		
		public static WebElement clickQALink(WebDriver driver)	//Method for searching QALink for user login
		{
			QALink = driver.findElement(By.xpath("//a[@id='lnkSecurityQA']"));
			return QALink;
		}
		
		public static WebElement Answer1(WebDriver driver)		//Method for searching input box of first answer
		{
			ans1 = driver.findElement(By.xpath("//input[@name='txtAnswer1']"));
			return ans1;
		}
		
		public static WebElement Answer2(WebDriver driver)		//Method for searching input box of second answer
		{
			ans2 = driver.findElement(By.xpath("//input[@name='txtAnswar2']"));
			return ans2;
		}
		public static WebElement Question1(WebDriver driver)	//Method for searching first Question 
		{
			question1 = driver.findElement(By.xpath("//span[@id='lblQuestion1']"));
			return question1;
		}
		
		public static WebElement Question2(WebDriver driver)	//Method for searching second Question
		{
			question2 = driver.findElement(By.xpath("//span[@id='lblQuestion2']"));
			return question2;
		}
		
		public static WebElement SubmitAnswer(WebDriver driver)	//Method for searching button to submit answers
		{
			validate = driver.findElement(By.xpath("//input[@value='Validate']"));
			return validate;
		}
		
		public static WebElement clickComplicane(WebDriver driver)		//Method for searching Compliance image
		{
			comp_img = driver.findElement(By.xpath("//div[@id='dvbtnCompliance']/div[1]/img"));
			return comp_img;
		}
		//div[@id='dvbtnCompliance']/div[1]/img
		public static WebElement clickLicense(WebDriver driver)
		{
			comp_img = driver.findElement(By.xpath("//*[@id='dvbtnLicense']"));
			return comp_img;
		}
		
		public static WebElement ClickLitigation(WebDriver driver)
		{
			comp_img = driver.findElement(By.xpath("//*[@id='dvbtnLitigation']/div[2]"));
			return comp_img;
		}
		
		public static WebElement ClickContract(WebDriver driver)
		{
			comp_img = driver.findElement(By.xpath("//*[@id='dvbtnContract']"));
			return comp_img;
		}
	}


