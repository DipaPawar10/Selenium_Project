package PageObject;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchEmployeeById {
	WebDriver d;
	public SearchEmployeeById(WebDriver driver) {
		d=driver;
		PageFactory.initElements(driver, this);
		}
	
	@FindBy(xpath="//div[@class='oxd-input-group oxd-input-field-bottom-space']//div/input[@class='oxd-input oxd-input--active']")
	WebElement empId;
	@FindBy(xpath="(//div[@role='row'])[2]/div[@role='cell'][2]")
	WebElement actual_mess;
	@FindBy(xpath="//div[@role='row']")
	List<WebElement> rows;
	
	public void enterEmployeeId(String eid) {
		empId.sendKeys(eid);
	}
	public List noOfrows() {
		return rows;
	}
	public String actualMessage() {
		return (actual_mess.getText());
	}
	
	
		
	
	
}
