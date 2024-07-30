package companytim.pageobjectmodel;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import companytim.AbstractComponent.AbstractComponent;

public class OrderPage extends AbstractComponent{

	WebDriver driver;
	
	public OrderPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="tr td:nth-child(3)")
	List<WebElement> OrdersHistory;

	

	
	public boolean ConfirmOrderItem(String productNeed) {
		boolean match = OrdersHistory.stream().anyMatch(product->product.getText().equalsIgnoreCase(productNeed));
		return match;
		
		}
	
	
}
