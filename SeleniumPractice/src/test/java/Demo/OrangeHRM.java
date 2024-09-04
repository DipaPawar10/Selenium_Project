package Demo;


import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

import junit.framework.Assert;

public class OrangeHRM {
	private static final String Interger = null;
	public String baseUrl = "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";
	public WebDriver driver;


	@BeforeTest
	public void setup() {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Lenovo\\Downloads\\drivers\\chromedriver-win64\\chromedriver.exe");
		driver=new ChromeDriver();

		driver.get(baseUrl);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test(priority=2, enabled=false)
	public void loginTestWithValidCredentials() throws InterruptedException {
		driver.findElement(By.xpath("//input[@placeholder='Username']")).sendKeys("Admin");
		driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys("admin123");
		driver.findElement(By.xpath("//button[@type='submit']")).submit();

		String pageTitle = driver.getTitle();
		//		if(pageTitle.equals("OrangeHRM")) {
		//			System.out.println("Login Successful");
		//			}else {
		//				System.out.println("Login Failed");
		logOut();
		Thread.sleep(2000);
		Assert.assertEquals(pageTitle, "OrangeHRM");
	}

	@Test(priority=1, enabled=false)
	public void doLoginTestWithInvalidCredentials() throws InterruptedException  {
		driver.findElement(By.xpath("//input[@placeholder='Username']")).sendKeys("admin");
		driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys("1234");
		driver.findElement(By.xpath("//button[@type='submit']")).submit();
		String messa_expect = "Invalid credentials";
		String messa_actual = driver.findElement(By.xpath("//p[@class='oxd-text oxd-text--p oxd-alert-content-text']")).getText();
		Assert.assertTrue(messa_expect.contains(messa_actual));
		Thread.sleep(5000);
	}

	@Test(priority=3, enabled=false)
	public void addEmployee() throws InterruptedException {
		logIn();
		driver.findElement(By.xpath("//span[text()='PIM']")).click();
		driver.findElement(By.xpath("//a[text()='Add Employee']")).click();
		driver.findElement(By.xpath("//input[@placeholder='First Name']")).sendKeys("Dipali");
		driver.findElement(By.xpath("//input[@placeholder='Last Name']")).sendKeys("Pawar");
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		Thread.sleep(2000);

		String confirmMessage= driver.findElement(By.xpath("//h6[normalize-space()='Personal Details']")).getText();
		if(confirmMessage.contains("Personal Details")) {
			System.out.println("Employee added succdessfuly");
		}else {
			System.out.println("failed to add employee");
		}
		Thread.sleep(5000);

		logOut();
		Assert.assertEquals(confirmMessage, "Personal Details");

	}

	@Test(priority=4, enabled=false)
	public void searchEmployeeByName() throws InterruptedException {
		logIn();
		driver.findElement(By.xpath("//span[text()='PIM']")).click();
		driver.findElement(By.xpath("//a[text()='Employee List']")).click();
		driver.findElements(By.tagName("input")).get(1).sendKeys("radha");
		driver.findElement(By.xpath("//button[normalize-space()='Search']")).click();	   
		Thread.sleep(5000);
		List<WebElement> element = driver.findElements(By.xpath("//span[@class='oxd-text oxd-text--span']"));
		String expect_mess = "Record Found";
		String actual_mess= element.get(0).getText();
		System.out.println(actual_mess);

		logOut();

		Assert.assertTrue(actual_mess.contains(expect_mess));
	}

	@Test(priority=5, enabled=true)
	public void searchEmployeeById() throws InterruptedException {
		String empId="0377";
		String actual_message ="";
		logIn();

		driver.findElement(By.xpath("//span[text()='PIM']")).click();
		driver.findElement(By.xpath("//a[text()='Employee List']")).click();
		driver.findElement(By.xpath("//div[@class='oxd-input-group oxd-input-field-bottom-space']//div//input[@class='oxd-input oxd-input--active']")).sendKeys(empId);
		driver.findElement(By.xpath("//button[normalize-space()='Search']")).click();	
		Thread.sleep(5000);

		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("window.scrollBy(0," + 500 + ")");
		Thread.sleep(2000);

		List<WebElement> rows = driver.findElements(By.xpath("//div[@role='row']"));
		if(rows.size()>1) {
			actual_message = driver.findElement(By.xpath("((//div[@role='row'])[2]/div[@role='cell'][2]")).getText();
		}

		logOut();
		Assert.assertEquals(empId, actual_message);



	}

	@Test(priority=6, enabled=false)
	public void uploadFile() throws IOException, InterruptedException {

		logIn();
		driver.findElement(By.xpath("//span[text()='PIM']")).click();
		driver.findElement(By.xpath("//span[normalize-space()='Configuration']")).click();
		driver.findElement(By.xpath("//a[normalize-space()='Data Import']")).click();
		driver.findElement(By.xpath("//div[text()='Browse']")).click();

		Thread.sleep(2000);

		Runtime.getRuntime().exec("C://Users//Lenovo//Desktop//CS_SeleniumExercises//SeleniumPractice//FileUploadOrangeHRM.exe");

		Thread.sleep(5000);

		driver.findElement(By.xpath("//button[@type='submit']")).submit();
		logOut();
	}

	@Test(priority=7, enabled=false)
	public void uploadImage() throws InterruptedException, IOException {
		logIn();
		driver.findElement(By.xpath("//span[text()='PIM']")).click();
		driver.findElement(By.xpath("//a[text()='Add Employee']")).click();
		driver.findElement(By.xpath("//input[@placeholder='First Name']")).sendKeys("Dipali");
		driver.findElement(By.xpath("//input[@placeholder='Last Name']")).sendKeys("Pawar");

		driver.findElement(By.xpath("//i[@class='oxd-icon bi-plus']")).click();
		Thread.sleep(5000);

		Runtime.getRuntime().exec("C:/Users/Lenovo/Desktop/CS_SeleniumExercises/SeleniumPractice/FileUploadImageOrangeHRM.exe");
		driver.findElement(By.xpath("//button[@type='submit']")).click();


		Thread.sleep(2000);

		logOut();
	}

	@Test(priority=8,enabled=false )	
	public void deleteEmployee() throws InterruptedException {
		logIn();
		driver.findElement(By.xpath("//span[text()='PIM']")).click();
		driver.findElement(By.xpath("//a[text()='Employee List']")).click();
		//driver.findElements(By.tagName("input")).get(1).sendKeys("radha");
		driver.findElement(By.xpath("(//input)[2]")).sendKeys("Amelia");
		driver.findElement(By.xpath("//button[normalize-space()='Search']")).click();	   
		Thread.sleep(3000);

		driver.findElement(By.xpath("//i[@class='oxd-icon bi-trash']")).click();		
		driver.findElement(By.xpath("//button[@class='oxd-button oxd-button--medium oxd-button--label-danger orangehrm-button-margin']")).click();

		String message = driver.findElement(By.xpath("(//span[@class='oxd-text oxd-text--span'])[1]")).getText();
		Thread.sleep(5000);
		logOut();
		Assert.assertEquals(message, "No Records Found");

	}
	@Test(priority=9,enabled=false)
	public void employeeList() throws InterruptedException {
		logIn();
		driver.findElement(By.xpath("//span[text()='PIM']")).click();
		driver.findElement(By.xpath("//a[text()='Employee List']")).click();
		Thread.sleep(3000);
		List<WebElement> totalLinksElements = driver.findElements(By.xpath("//ul[@class='oxd-pagination__ul']/li"));		
		int totalLinks = totalLinksElements.size();
		for(int i=0;i<totalLinks;i++) {
			try 
			{
			String currentLinkText=totalLinksElements.get(i).getText();
			
				int page = Integer.parseInt(currentLinkText);
				System.out.println(page + ":");
				totalLinksElements.get(i).click();	
				Thread.sleep(2000);

				List<WebElement> emp_List = driver.findElements(By.xpath("//div[@role='row']/div[@role='cell'][4]"));
				for(int j=0;j<emp_List.size();j++) {
					String last_name = emp_List.get(j).getText();
					System.out.println(last_name);
				}

			}
			catch(Exception e)
			{
				System.out.println("Not a Number");
			}

		}
		Thread.sleep(3000);
		logOut();
	}
	
	@Test(priority=10,enabled=false)
	public void leaveEmployee() throws InterruptedException {
		logIn();
		driver.findElement(By.linkText("Leave")).click();
		driver.findElement(By.linkText("Apply")).click();
		driver.findElement(By.xpath("//i[@class='oxd-icon bi-caret-down-fill oxd-select-text--arrow']")).click();
		driver.findElement(By.xpath("//*[contains(text(),'CAN')]")).click();
		driver.findElement(By.xpath("//div[@class='oxd-date-input']/input")).sendKeys("2024-03-04");
		driver.findElement(By.tagName("textarea")).sendKeys("This is Personal Leave");
		Thread.sleep(3000);
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		}


	public void logIn() {
		driver.findElement(By.xpath("//input[@placeholder='Username']")).sendKeys("Admin");
		driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys("admin123");
		driver.findElement(By.xpath("//button[@type='submit']")).submit();

	}
    
	@AfterTest
	public void logOut()  {
		driver.findElement(By.xpath("//p[@class='oxd-userdropdown-name']")).click();
		//		driver.findElement(By.xpath("//a[normalize-space()='Logout']")).click();

		List<WebElement> elementList = driver.findElements(By.xpath("//a[@class='oxd-userdropdown-link']"));

		for(int i=0; i<elementList.size(); i++) {
			//			Thread.sleep(10000);
			System.out.println(i+":"+ elementList.get(i).getText());
		}


		elementList.get(3).click();

	}

	@AfterTest
	public void tearDown() {
		//		Thread.sleep(3000);
		driver.close();
		driver.quit();
	}

}
