package PageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DeleteEmployee {
	
	WebDriver d;
	public DeleteEmployee(WebDriver driver) {
		d=driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(xpath="//div[@class='oxd-table-row oxd-table-row--with-border oxd-table-row--clickable']/div[@role='cell'][9]")
	WebElement delemp;
	@FindBy(xpath="//i[@class='oxd-icon bi-trash oxd-button-icon']")
	WebElement delbtn;
	@FindBy(xpath="(//span[@class='oxd-text oxd-text--span'])[1]")
	WebElement message;
	
	public void clickOnDeleteLogo() {
		delemp.click();
	}
	public void clickOnDeleteButton() {
		delbtn.click();
	}
	public String messageActual() {
		return(message.getText());
	}

}
