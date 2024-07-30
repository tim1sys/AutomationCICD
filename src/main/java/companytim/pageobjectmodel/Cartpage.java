package companytim.pageobjectmodel;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import companytim.AbstractComponent.AbstractComponent;

public class Cartpage extends AbstractComponent{

	WebDriver driver;
	
	public Cartpage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".cartSection h3")
	List<WebElement> CartContent;

	@FindBy(css=".totalRow button")
	WebElement checkoutButton;
	
	

	
	
	By label = By.cssSelector(".label");
	
	public boolean ConfirmCartItem(String productNeed) {
		
		boolean match = CartContent.stream().anyMatch(cartproduct->cartproduct.getText().equalsIgnoreCase(productNeed));
		return match;
		}
	
	public checkoutpage goToCheckout() {
		waitforElementToAppear(label);
		
		JsExecutorToClick(checkoutButton);
		
		checkoutpage checkoutpage= new checkoutpage(driver);
		return checkoutpage;
		
		
		
	}
	
	
}
