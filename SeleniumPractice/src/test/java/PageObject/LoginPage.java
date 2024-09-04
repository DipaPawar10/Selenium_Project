package PageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	WebDriver d;

	@FindBy(xpath="//input[@placeholder='Username']")
	WebElement username;

	@FindBy(xpath="//input[@placeholder='Password']")
	WebElement password;

	@FindBy(xpath="//button[@type='submit']")
	WebElement loginbtn;
	
	@FindBy(xpath="//p[@class='oxd-text oxd-text--p oxd-alert-content-text']\"")
	WebElement InvalidText;

	public LoginPage(WebDriver driver) {
		d=driver;
		PageFactory.initElements(d, this);
	}
	
	public void setUsername(String name) {
		username.sendKeys(name);
	}
	
	public void setPassword( String pwd) {
		password.sendKeys(pwd);
	}
	
	public void clickLoginbtn() {
		loginbtn.submit();
		
	}
	public String getInvalidText() {
		return (InvalidText.getText());
	}
	
}
