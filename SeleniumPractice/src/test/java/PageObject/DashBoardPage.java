package PageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class DashBoardPage {
	
	WebDriver d;
	public DashBoardPage(WebDriver driver){
		d=driver;
		PageFactory.initElements(driver, this);
	}
	
	public String getPageTitle() {
		return (d.getTitle());
	}
	

}
