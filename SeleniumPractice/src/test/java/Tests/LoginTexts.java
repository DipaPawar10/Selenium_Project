package Tests;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import PageObject.CheckEmployeeList;
import PageObject.DashBoardPage;
import PageObject.DeleteEmployee;
import PageObject.LogOutPage;
import PageObject.LoginPage;
import PageObject.PimPage;
import PageObject.SearchEmployeeById;
import PageObject.SearchEmployeeByName;
import PageObject.UploadFile;
import PageObject.UploadImage;
import junit.framework.Assert;

public class LoginTexts {
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
	
	
	@Test(priority=1, enabled=false)
	public void loginTestWithInvalidCredentials() throws InterruptedException {
		
		LoginPage pg = new LoginPage(driver);
		pg.setUsername("Admin");
		pg.setPassword("1234");
		pg.clickLoginbtn();
		String message_expected = "Invalid credentials";
		String message_actual = pg.getInvalidText();
		
		Thread.sleep(2000);
		Assert.assertEquals(message_expected, message_actual);
	}
	
	public void loginTestWithValidCredentials() throws InterruptedException {
		LoginPage pg = new LoginPage(driver);
		DashBoardPage db = new DashBoardPage(driver);
		pg.setUsername("Admin");
		pg.setPassword("admin123");
		pg.clickLoginbtn();
		

		String pageTitle = db.getPageTitle();
		//		if(pageTitle.equals("OrangeHRM")) {
		//			System.out.println("Login Successful");
		//			}else {
		//				System.out.println("Login Failed");
		
		Assert.assertEquals(pageTitle, "OrangeHRM");
	}
	@Test(priority=2,enabled=false)
	public void addEmployee() throws InterruptedException {
		PimPage pim=new PimPage(driver);
		pim.clickOnPim();
		pim.clickOnAddEmployee();
		pim.addEmployeeFname("Yogesh");
		pim.addEmployeeLname("Bhamare");
		pim.clickOnSaveBtn();
		Thread.sleep(2000);
		String confirm_message=pim.getConfirmMessage();
		if(confirm_message.contains("Employee added succdessfuly")) {
			System.out.println("Employee added succdessfuly");
		}else {
			System.out.println("Failed to add employee");
		}
		Thread.sleep(5000);
		Assert.assertEquals(confirm_message, "Personal Details");
		
	}
	@Test(priority=3,enabled=false)
	public void searchEmployeeByName() throws InterruptedException {
		loginTestWithValidCredentials();
		SearchEmployeeByName Search_emp = new SearchEmployeeByName(driver);
		Search_emp.clickOnPim();
		Search_emp.clickOnEmployeeList();
		Search_emp.searchEmployeeName("Tristan");
		Search_emp.clickOnSubmit();
		Thread.sleep(5000);
		List<WebElement> element = Search_emp.listOfElements();
		System.out.println(element);
		String expect_message="Record Found";
		String actual_message = element.get(0).getText();
		System.out.println(actual_message);
		logOut();
		Assert.assertTrue(actual_message.contains(expect_message));
		}
	@Test(priority=4,enabled=false)
	public void searchEmployeeById() throws InterruptedException  {
		String actual_message="";
		String emp_Id="0377";
		loginTestWithValidCredentials();
		SearchEmployeeById Search_id = new SearchEmployeeById(driver);
		SearchEmployeeByName Search_emp = new SearchEmployeeByName(driver);

		Search_emp.clickOnPim();
		Search_emp.clickOnEmployeeList();
		Search_id.enterEmployeeId(emp_Id);
		Search_emp.clickOnSubmit();
		Thread.sleep(2000);
		
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("window.scrollBy(0,"+500+")");
		Thread.sleep(3000);
		List<WebElement> rows =Search_id.noOfrows();		
		if(rows.size()>1) {
			 actual_message = Search_id.actualMessage();
		}
		logOut();
		Assert.assertEquals(emp_Id, actual_message);
		
	}
	@Test(priority=5, enabled=false)
	public void fileUploadTest() throws IOException, InterruptedException {
		loginTestWithValidCredentials();
		UploadFile up = new UploadFile(driver);
		up.clickOnPim();
		up.clickOnConfiguration();
		up.clickOnDataImport();
		up.uploadFile();
		
		Runtime.getRuntime().exec("C://Users//Lenovo//Desktop//CS_SeleniumExercises//SeleniumPractice//FileUploadOrangeHRM.exe");
		Thread.sleep(3000);
		up.submitButton();
		
		logOut();
		}
	@Test(priority=6, enabled=false)
	public void imageUploadTest() throws InterruptedException, IOException {
		loginTestWithValidCredentials();
		UploadImage upImage=new UploadImage(driver);
		upImage.clickOnPim();
		upImage.clickOnAddEmployee();
		upImage.clickOnPlusBtn();
		
		Runtime.getRuntime().exec("C://Users//Lenovo//Desktop//CS_SeleniumExercises//SeleniumPractice//FileUploadImageOrangeHRM.exe");
		Thread.sleep(3000);
		upImage.clickOnSubmitBtn();
		logOut();
		}
	@Test(priority=7,enabled=false)
	public void DeleteEmployeeInfo() throws InterruptedException {
		loginTestWithValidCredentials();

		SearchEmployeeByName Search_emp = new SearchEmployeeByName(driver);
		DeleteEmployee Emp_del = new DeleteEmployee(driver);
		Search_emp.clickOnPim();
		Search_emp.clickOnEmployeeList();
		Search_emp.searchEmployeeName("Amelia");
		Emp_del.clickOnDeleteLogo();
		Emp_del.clickOnDeleteButton();
		String Actual_mess = Emp_del.messageActual();
		Thread.sleep(2000);
		logOut();
		Assert.assertEquals(Actual_mess, "No Records Found");
	}
	
	@Test(priority=8,enabled=true)
	public void EmployeeList() throws InterruptedException {
		loginTestWithValidCredentials();

		SearchEmployeeByName Search_emp = new SearchEmployeeByName(driver);
		CheckEmployeeList empl_List = new CheckEmployeeList(driver);
		Search_emp.clickOnPim();
		Search_emp.clickOnEmployeeList();
		List<WebElement> totalLinkElements = empl_List.employeeListPages();
		int totalLinks=totalLinkElements.size();
		for(int i=0;i<totalLinks;i++) {
			try {
			String CurrentLinkText = totalLinkElements.get(i).getText();
			int Page= Integer.parseInt(CurrentLinkText);
			System.out.println(Page+":");
			totalLinkElements.get(i).click();
			Thread.sleep(3000);
			
			List<WebElement> list_ele = empl_List.employeeListNames();
			for(int j=0;j<list_ele.size();j++) {
				String Last_name = list_ele.get(j).getText();
				System.out.println(list_ele);
			}
			}
			
			catch(Exception e) {
				
			}
		}
		Thread.sleep(3000);
		logOut();
	
	}

	public void logOut() throws InterruptedException  {
		LogOutPage logOut=new LogOutPage(driver);
		
		logOut.clickOnUserDropdownName();

		List<WebElement> elementList = logOut.clickOnUserDropdownMenu();
		for(int i=0; i<elementList.size(); i++) {
		 Thread.sleep(1000);
			System.out.println(i+":"+ elementList.get(i).getText());
		}
 
}
	@AfterTest
	public void tearDown() {
		//		Thread.sleep(3000);
		driver.close();
		driver.quit();
	}
}
