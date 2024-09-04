package PageObject;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckEmployeeList {
	WebDriver driver;
	public CheckEmployeeList(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(xpath="//ul[@class='oxd-pagination__ul']/li")
	List<WebElement> totalLinkElement;
	
	@FindBy(xpath="//div[@role='row']/div[@role='cell'][4]")
	List<WebElement> Empoyees;
	
	public List employeeListPages() {
		return totalLinkElement;
		}
	public List employeeListNames() {
		return Empoyees;
	}

}
