package companytim.pageobjectmodel;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import companytim.AbstractComponent.AbstractComponent;

public class checkoutpage extends AbstractComponent{

	WebDriver driver;
	
	public checkoutpage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy (css="[placeholder='Select Country']")
	WebElement countryField;
	
	@FindBy (xpath="//section[@class='ta-results list-group ng-star-inserted']/button")
	List<WebElement> droplist;
	
	@FindBy (css=".action__submit")
	WebElement placeOrderButton;
	
	
	By dropdown = By.cssSelector(".ta-results");
	

	public confirmpage fillInfoandProceed() {
		
		countryField.sendKeys("Nig");
		waitforElementToAppear(dropdown);
		for(WebElement drop:droplist) {
			if (drop.getText().equalsIgnoreCase("nigeria")) {
				drop.click();
				break; 
			}
	}
		JsExecutorToClick(placeOrderButton);
		confirmpage confirmpage = new confirmpage(driver);
		return confirmpage;
		
		}
}
