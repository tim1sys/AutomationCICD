package companytim.pageobjectmodel;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import companytim.AbstractComponent.AbstractComponent;

public class Landingpage extends AbstractComponent {
	
	WebDriver driver;
	// receive driver from standaloneTest with constructor cos its the first to run in a class 
	public Landingpage(WebDriver driver)
	{
		super(driver); // send driver to thier parent class
		this.driver=driver; //
		PageFactory.initElements(driver, this);
	}
		//using pageFactory 
	@FindBy(id="userEmail")
	WebElement emailEle;
	
	@FindBy(id="userPassword")
	WebElement passwordEle;
	
	@FindBy(id="login")
	WebElement submit;
	
	
	@FindBy(css="[class*='flyInOut']")
	WebElement errorMsg;
	
	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client");
	}
	
	
	public String errorMessage() {
		waitforWebElementToAppear(errorMsg);
		return errorMsg.getText();
		
	}
	public ProductCatalogue loginApplication(String email,String password) {
		emailEle.sendKeys(email);
		passwordEle.sendKeys(password);
		submit.click();
		ProductCatalogue productCatalog = new ProductCatalogue(driver);
		return productCatalog;
	}
}
