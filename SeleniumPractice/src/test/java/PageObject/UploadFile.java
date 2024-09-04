package PageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class UploadFile {
	WebDriver d;
	public UploadFile(WebDriver driver) {
		d=driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(xpath="//span[text()='PIM']")
	WebElement pim;
	
	@FindBy(xpath="//span[normalize-space()='Configuration']")
	WebElement config;
	
	@FindBy(xpath="//a[normalize-space()='Data Import']")
	WebElement data_import;
	
	@FindBy(xpath="//div[text()='Browse']")
	WebElement upload;
	
	@FindBy(xpath="//button[@type='submit']")
	WebElement submit_button;
	
	
	public void clickOnPim() {
		pim.click();
	}
	
	public void clickOnConfiguration() {
		config.click();
	}
	
	public void clickOnDataImport() {
		data_import.click();
	}
	
	public void uploadFile() {
		upload.click();
	}
	public void submitButton() {
		submit_button.click();
	}
	
	
}
