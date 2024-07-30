package companytim.stepdefinitions;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import companytim.Testcomponent.BaseTest;
import companytim.pageobjectmodel.Cartpage;
import companytim.pageobjectmodel.Landingpage;
import companytim.pageobjectmodel.ProductCatalogue;
import companytim.pageobjectmodel.checkoutpage;
import companytim.pageobjectmodel.confirmpage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinitionImp extends BaseTest {

	public Landingpage landingpage;
	public ProductCatalogue productCatalog;
	public Cartpage cartpage;
	public checkoutpage checkoutpage;
	public confirmpage confirmpage;
	
	@Given("Land on Ecommerce page")
	public void land_on_ecommerce_page() throws IOException {
		
		landingpage=  LaunchApplication();
		
	}
	
	@Given("^Logged in with username (.+) and password (.+)$")
	public void logged_in_with_username_and_password(String username, String password) {
		
		 productCatalog = landingpage.loginApplication(username,password);
		
	}
	
	@When("^I add product (.+) to Cart$")
	public void i_add_product_to_cart(String productNeed) throws InterruptedException {
		
		List<WebElement> products=	productCatalog.getProductsList();
		productCatalog.addProductToCart(productNeed);
	}
	
	
	 @And("^Checkout (.+) and submit the order$")
	 public void checkout_productNeed_and_submit_the_order(String productNeed ) {
		 
		 cartpage=  productCatalog.CartButton();
			boolean match= cartpage.ConfirmCartItem(productNeed);
			Assert.assertTrue(match);
		 checkoutpage =cartpage.goToCheckout();
		 confirmpage= 	checkoutpage.fillInfoandProceed();
	 }
	 
	 
	 @Then("{string} message is displayed on confirmationpage")
	 public void message_displayed_confirmation(String string) {
		 
		 String Msg = confirmpage.confirmOrderPlaced();
			Assert.assertTrue(Msg.equalsIgnoreCase(string));
			driver.close();
			
	 }
	 
	 @Then("Verify {string} is displayed")
	 public void verify_string_is_displayed(String string) {
		Assert.assertEquals(string, landingpage.errorMessage());
		driver.close();
	 }
}
