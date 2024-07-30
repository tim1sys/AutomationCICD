package companytim.Tests;

import java.io.IOException;
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
import org.testng.annotations.Test;

import com.sun.net.httpserver.Authenticator.Retry;

import companytim.pageobjectmodel.Landingpage;
import companytim.pageobjectmodel.ProductCatalogue;
import companytim.pageobjectmodel.checkoutpage;
import companytim.pageobjectmodel.confirmpage;
import companytim.Testcomponent.BaseTest;
import companytim.pageobjectmodel.Cartpage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class errorValidationTest extends BaseTest {

	@Test(groups= {"ErrorHandling"} , retryAnalyzer=companytim.Testcomponent.Retry.class)
	public void loginValidation() throws IOException, InterruptedException
	{
		landingpage.loginApplication("therokk@gmail.com", "qwertey12");
		Assert.assertEquals("Incorrect email or password.", landingpage.errorMessage());

	}
	
	@Test
	public void productErrorValidation() throws IOException, InterruptedException
	{
		ProductCatalogue productCatalog = landingpage.loginApplication("therokk@gmail.com", "qwerty12");
		String productNeed= "ZARA COAT 3";
	List<WebElement> products=	productCatalog.getProductsList();
		productCatalog.addProductToCart(productNeed);
		Cartpage cartpage=  productCatalog.CartButton();
		boolean match= cartpage.ConfirmCartItem("ZARA COAT 33");
		Assert.assertFalse(match);	
	}
// summary 
	// we created a globalvariable, where we set browsers name and get it inorder to determine which browser
	// to invoke 
}
