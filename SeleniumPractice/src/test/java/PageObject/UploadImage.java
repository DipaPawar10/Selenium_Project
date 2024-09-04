package PageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class UploadImage{
	
	WebDriver d;
	public UploadImage(WebDriver driver) {
		d=driver;
	    PageFactory.initElements(driver,this);
		}
	@FindBy(xpath="//span[text()='PIM']")
	WebElement pim;
	
	@FindBy(linkText="Add Employee")
	WebElement addEmployee;
	
	@FindBy(xpath="//i[@class='oxd-icon bi-plus']")
	WebElement plusBtn;
	
	@FindBy(xpath="//button[@type='submit']")
	WebElement submit_button;
	
	
	
	
	public void clickOnPim() {
		pim.click();
	}
	public void clickOnAddEmployee() {
		addEmployee.click();
	}
	public void clickOnPlusBtn() {
		plusBtn.click();
	}
	public void clickOnSubmitBtn() {
		submit_button.click();
	}
	
	

}


