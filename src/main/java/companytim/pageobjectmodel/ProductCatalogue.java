package companytim.pageobjectmodel;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import companytim.AbstractComponent.AbstractComponent;

public class ProductCatalogue extends AbstractComponent{
	
	WebDriver driver;
	// receive driver from standaloneTest with constructor cos its the first to run in a class 
	public ProductCatalogue(WebDriver driver)
	{
		super(driver); // sends driver to thier parent class
		this.driver=driver; //
		PageFactory.initElements(driver, this);
	}
		//using pageFactory 
	@FindBy(css=".col-lg-4")
	List<WebElement> products; //to get list
	
	@FindBy(css=".ng-animating")
	WebElement spinner;
	
	
	
	
	
	By productBy = By.cssSelector(".mb-3");
	By addTocart = By.cssSelector(".card-body button:last-of-type");
	By toastMessage = By.cssSelector("#toast-container");


	
	
	public List<WebElement> getProductsList() {
		
		waitforElementToAppear(productBy);
		return products;
	}
	
	// need to create method for getting exact product
	public WebElement getProductName(String productNeed) {
		WebElement prod =  getProductsList().stream().filter(product->product.findElement(By.cssSelector("b"))
		.getText().equals(productNeed)).findFirst().orElse(null);
		return prod;
	}
	public void addProductToCart(String productNeed) throws InterruptedException {
		
		 getProductName(productNeed).findElement(addTocart).click();
		 Thread.sleep(1000);
			//waitforElementToAppear(toastMessage);
		//	waitforElementToDissapear(spinner);
	}
	
	
	
}
