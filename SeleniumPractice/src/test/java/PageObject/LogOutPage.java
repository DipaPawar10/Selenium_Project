package PageObject;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LogOutPage {
	WebDriver d;
	
	@FindBy(xpath="//p[@class='oxd-userdropdown-name']")
	WebElement userDropdownName;
	@FindBy(xpath="//a[@class='oxd-userdropdown-link']")
	List<WebElement> userDropDownMenu;
	
	
	public LogOutPage(WebDriver driver) {
		d=driver;
		PageFactory.initElements(driver, this);
	}
	public void clickOnUserDropdownName() {
		userDropdownName.click();
	}
	public List clickOnUserDropdownMenu() {
		return userDropDownMenu;
	}

}
