package PageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PimPage {
	WebDriver d;
	
	@FindBy(xpath="//span[text()='PIM']")
	WebElement pim;
	@FindBy(linkText="Add Employee")
	WebElement addEmployee;
	@FindBy(xpath="//input[@placeholder='First Name']")
	WebElement fname;
	@FindBy(xpath="//input[@placeholder='Last Name']")
	WebElement lname;
	@FindBy(xpath="//button[@type='submit']")
	WebElement savebtn;
	@FindBy(xpath="//h6[normalize-space()='Personal Details']")
	WebElement confirm_message;
	
	public PimPage(WebDriver driver) {
		d=driver;
		PageFactory.initElements(driver, this);
		}
	public void clickOnPim() {
		pim.click();
	}
	public void clickOnAddEmployee() {
		addEmployee.click();
	}
	public void addEmployeeFname(String f_name) {
		fname.sendKeys(f_name);
	}
	public void addEmployeeLname(String L_name) {
		lname.sendKeys(L_name);
	}
	public void clickOnSaveBtn() {
		savebtn.click();
	}
	public String getConfirmMessage() {
		return (confirm_message.getText());
	}

}
