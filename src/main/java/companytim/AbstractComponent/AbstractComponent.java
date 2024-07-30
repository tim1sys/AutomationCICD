package companytim.AbstractComponent;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import companytim.pageobjectmodel.Cartpage;
import companytim.pageobjectmodel.OrderPage;

public class AbstractComponent {
	
	WebDriver driver;
	
		
	public AbstractComponent(WebDriver driver) {
		
		this.driver= driver;
	}
	
	@FindBy(css="[routerlink*='cart']")
	WebElement cartButton;
	
	@FindBy(css="[routerlink*='/dashboard/myorders']")
	WebElement OrderPageButton;
	
	// we need create components that can be reused like wait, visibility and all that 
	
	public void waitforElementToAppear(By findBy) {
		
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}
	
public void waitforWebElementToAppear(WebElement elel) {
		
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(elel));
	}
	
	public void waitforElementToDissapear(WebElement Ele) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.invisibilityOf(Ele));
	}
	
	public Cartpage CartButton () {
		cartButton.click();
		Cartpage checkout1 = new Cartpage(driver);
		return checkout1;
	}
	public OrderPage OrderPageButton () {
		OrderPageButton.click();
		OrderPage Orderpage = new OrderPage(driver);
		return Orderpage;
	}
	public void JsExecutorToClick(WebElement button) {
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", button);
		
	}
	
}
