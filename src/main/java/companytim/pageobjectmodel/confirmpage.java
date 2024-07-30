package companytim.pageobjectmodel;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import companytim.AbstractComponent.AbstractComponent;

public class confirmpage extends AbstractComponent{

	WebDriver driver;
	
	public confirmpage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy (css=".hero-primary")
	WebElement thankyoutext1;
	
	By thankyoutext= By.cssSelector(".hero-primary");
	
	
	public String confirmOrderPlaced() {
		
		waitforElementToAppear(thankyoutext);
		return thankyoutext1.getText();
	
		}
}
