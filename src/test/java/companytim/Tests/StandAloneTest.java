package companytim.Tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import companytim.pageobjectmodel.Landingpage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAloneTest {

	public static void main(String[] args) throws InterruptedException {
		
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		Landingpage landingpage= new Landingpage(driver);
		// we are literarily reducing the lines of codes here by creating methods in another class and 
		driver.get("https://rahulshettyacademy.com/client");
		driver.findElement(By.id("userEmail")).sendKeys("therokk@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("qwerty12");
		driver.findElement(By.id("login")).click();
		String productNeed= "ZARA COAT 3";
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
	List<WebElement> products=	driver.findElements(By.cssSelector(".mb-3"));
	
		WebElement prod = products.stream().filter(product->product.findElement(By.cssSelector("b"))
		.getText().equals(productNeed)).findFirst().orElse(null);
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		
		//waiting until the product added shows 
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		// waiting until the white loading go away cos if you 
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		//click cart button 
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
		
		List<WebElement> cartProducts= driver.findElements(By.cssSelector(".cartSection h3"));
		
		boolean match = cartProducts.stream().anyMatch(cartproduct->cartproduct.getText().equalsIgnoreCase(productNeed));
		Assert.assertTrue(match);
		
		
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".label")));
		
		WebElement checkout= driver.findElement(By.cssSelector(".totalRow button"));

		JavascriptExecutor js = (JavascriptExecutor) driver;

		js.executeScript("arguments[0].click();", checkout);
		
		// driver.findElement(By.cssSelector("li[class='totalRow'] button[type='button']")).click();
		//Actions a = new Actions(driver);
		//a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")), "Nig").build().perform();
		
		
		driver.findElement(By.cssSelector("[placeholder='Select Country']")).sendKeys("Nig");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
		
		List<WebElement> dropdown = driver.findElements(By.xpath("//section[@class='ta-results list-group ng-star-inserted']/button"));
		for(WebElement drop:dropdown) {
			if (drop.getText().equalsIgnoreCase("nigeria")) {
				drop.click();
				break; 
			}
		}
		// we can use JavascriptExecutor to click stubborn buttons 
		WebElement placeOrder= driver.findElement(By.cssSelector(".action__submit"));
		js.executeScript("arguments[0].click()", placeOrder);
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".hero-primary")));
		
		String ExpMsg = driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertTrue(ExpMsg.equalsIgnoreCase("Thankyou for the order."));
	}

}
