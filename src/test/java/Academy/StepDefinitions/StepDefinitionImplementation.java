package Academy.StepDefinitions;

import org.testng.Assert;

import Academy.PageObjects.LandingPagePO;
import Academy.PageObjects.MyCartPO;
import Academy.PageObjects.PlaceOrderPO;
import Academy.PageObjects.ProductCatalogPO;
import Academy.PageObjects.ThanksPagePO;
import Academy.TestComponents.BaseTest03;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinitionImplementation extends BaseTest03 {
	
	
	public LandingPagePO lppo;
	public ProductCatalogPO pcpo;
	public MyCartPO mcpo;
	public PlaceOrderPO popo;
	public ThanksPagePO tppo;
	public String itemNumberReadableFormat;
	
	
	@Given("^A user has landed on the Ecommerce landing page$")
    public void a_user_has_landed_on_the_ecommerce_landing_page() throws Throwable {
        
		 lppo = launchApplication();
    }
	
	
	@Given("^A user has logged in with a username (.+) and a password (.+)$")
    public void a_user_has_logged_in_with_a_username_and_a_password(String username, String password) throws Throwable {
        
		lppo.loginApplication(username, password); //lppo.loginApplication("unknownxjk@gmail.com", "kojikurac123"); lppo.loginApplication(emailDP, passwordDP);
		
		 pcpo = new ProductCatalogPO(driver);
			
		String expectedLoginMessage = "Login Successfully";
		String actualLoginMessage = pcpo.getLoginMessageText();
		Assert.assertEquals(actualLoginMessage, expectedLoginMessage);
		
    }
	
	
	@When("^A user adds a product (.+) to the cart$")
    public void a_user_adds_a_product_to_the_cart(String productname) throws Throwable {
        
		//String wantedProduct = "ZARA COAT 3";
		
		pcpo.addProductToCart(productname);
    }
	
	
	@And("^Checkout that this product (.+) is added and submit this order$")
    public void checkout_that_this_product_is_added_and_submit_this_order(String productname) throws Throwable {
        
		 mcpo = new MyCartPO(driver);
		
		Assert.assertTrue(mcpo.isProductPresentInMyCart(productname));
		
		mcpo.getItemNumber(productname);
		
		mcpo.checkoutProduct();
    }
	
		
	
	@And("^Populate Shipping information using (.+) and (.+) and Place the Order$")
    public void populate_shipping_information_using_and_and_place_the_order(String partialcountry, String wantedcountry) throws Throwable {
        
		 	 popo = new PlaceOrderPO(driver);
			
		/*	String partialCountry = "yugo";
			String wantedCountry = "Yugoslavia";
			*/
			popo.typeInCountry(partialcountry);
			
			popo.selectMyCountry(wantedcountry);
			
			popo.placeOrder();
    }
	
	
	@Then("^Verify the message \"([^\"]*)\" is displayed$")
    public void verify_the_message_something_is_displayed(String expectedThankYouMessage) throws Throwable {
        
		 tppo = new ThanksPagePO(driver);
		
		//String expectedThankYouMessage = "THANKYOU FOR THE ORDER.";
		String thankYouMessage = tppo.getThankYouMessage();
		Assert.assertEquals(thankYouMessage, expectedThankYouMessage);
		
		//To extract order ID
		
		//To get this order ID into readable format
		 itemNumberReadableFormat = tppo.getItemNumberReadableFormat();
    }
	
	
	@And("^A browser is closed$")
    public void a_browser_is_closed() throws Throwable {
        
		closeBrowser();
    }
	
	
	@Given("^A user has attempted to login with a valid username (.+) and an invalid password (.+)$")
    public void a_user_has_attempted_to_login_with_a_valid_username_and_an_invalid_password(String username, String invalidpassword) throws Throwable {
        
		lppo.loginApplication(username, invalidpassword);
    }
	
	
	@Then("^Verify that user is not logged in and the message \"([^\"]*)\" is displayed$")
    public void verify_that_user_is_not_logged_in_and_the_message_something_is_displayed(String errorMessage) throws Throwable {
        
		//String wantedErrorMessage = "Incorrect email or password.";
		String actualErrorMessage = lppo.getIncorrectMessage();
		
		Assert.assertEquals(errorMessage, actualErrorMessage);
    }
	

}
