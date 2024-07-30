package companytim.Tests;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import companytim.pageobjectmodel.Landingpage;
import companytim.pageobjectmodel.OrderPage;
import companytim.pageobjectmodel.ProductCatalogue;
import companytim.pageobjectmodel.checkoutpage;
import companytim.pageobjectmodel.confirmpage;
import companytim.Testcomponent.BaseTest;
import companytim.pageobjectmodel.Cartpage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class SubmitOrderTest extends BaseTest {
	//String productNeed= "ZARA COAT 3";
	@Test(dataProvider= "getData3", groups="Purchase")
	public void submitOrder(HashMap <String, String> input) throws IOException, InterruptedException
	{
		ProductCatalogue productCatalog = landingpage.loginApplication(input.get("email"),input.get("password"));
		
	List<WebElement> products=	productCatalog.getProductsList();
		productCatalog.addProductToCart(input.get("productNeed"));
		Cartpage cartpage=  productCatalog.CartButton();
		boolean match= cartpage.ConfirmCartItem(input.get("productNeed"));
		Assert.assertTrue(match);
		checkoutpage checkoutpage =cartpage.goToCheckout();
	confirmpage confirmpage= 	checkoutpage.fillInfoandProceed();
	
		String Msg = confirmpage.confirmOrderPlaced();
		Assert.assertTrue(Msg.equalsIgnoreCase("Thankyou for the order."));
		
	}
	
	
	//Creating dependency of tests 
	
	@Test(dependsOnMethods= {"submitOrder"})
	public void OrderHistoryTest() {
		String productNeed= "ZARA COAT 3";
		ProductCatalogue productCatalog = landingpage.loginApplication("shetty@gmail.com", "Iamking@000");
		OrderPage orderpage = landingpage.OrderPageButton();
	Assert.assertTrue(orderpage.ConfirmOrderItem(productNeed));
		
		
	}
	// using data provider to run with different login details 
	
	//@DataProvider
	//public Object[][] getData() {
		
		//return new Object[][] {{"therokk@gmail.com","qwerty12","ZARA COAT 3"}, {"shetty@gmail.com","Iamking@000", "IPHONE 13 PRO"} };
	//}
	
	//using hashmap to send data
	//@DataProvider 
	//public Object[][] getData2() {
		
		//HashMap<String, String> map= new HashMap<String,String>();
		//map.put("email", "therokk@gmail.com");
		//map.put("password", "qwerty12");
		//map.put("productNeed", "ZARA COAT 3");
		
		//HashMap<String, String> map1= new HashMap<String, String>();
		//map1.put("email", "shetty@gmail.com");
		//map1.put("password", "Iamking@000");
		//map1.put("productNeed", "IPHONE 13 PRO");
			//we use input.get to bring values in 
		//return new Object[][] {{map},{map1}};
	//}
	
	
	@DataProvider
	public Object[][] getData3() throws IOException {
		
			List<HashMap<String, String>> data= getStringFromJson( System.getProperty("user.dir")+"\\src\\test\\java\\companytim\\data\\purchaseOrder.json");
	
	return new Object[][] {{data.get(0)},{data.get(1)},{data.get(2)}};
	}
	
// summary 
	// we created a global variable, where we set browsers name and get it inorder to determine which browser
	// to invoke 
}
