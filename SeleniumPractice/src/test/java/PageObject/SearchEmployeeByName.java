package PageObject;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchEmployeeByName {
	
	WebDriver d;
	public SearchEmployeeByName(WebDriver driver) {
		d=driver;
	    PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//span[text()='PIM']")
	WebElement pim;
	@FindBy(linkText="Employee List")
	WebElement emp_list;
	@FindBy(xpath="(//input[@placeholder='Type for hints...'])[1]")
	WebElement emp_name;
	@FindBy(xpath="//button[@class='oxd-button oxd-button--medium oxd-button--secondary orangehrm-left-space']")
	WebElement search_emp;
	@FindBy(xpath="//span[@class='oxd-text oxd-text--span']")
	List<WebElement> list_Element;

	public void clickOnPim(){
		pim.click();
	}
	public void clickOnEmployeeList() {
		emp_list.click();
	}
	public void searchEmployeeName(String ename) {
		emp_name.sendKeys(ename);
	}
	public void clickOnSubmit() {
		search_emp.click();
	}
	public List listOfElements() {
		return list_Element;
		}
}
