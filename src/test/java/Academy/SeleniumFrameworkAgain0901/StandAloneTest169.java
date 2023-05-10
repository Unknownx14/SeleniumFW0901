package Academy.SeleniumFrameworkAgain0901;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Academy.PageObjects.LandingPagePO;
import Academy.PageObjects.MyCartPO;
import Academy.PageObjects.PlaceOrderPO;
import Academy.PageObjects.ProductCatalogPO;
import Academy.PageObjects.ThanksPagePO;
import Academy.PageObjects.YourOrdersPO;
import Academy.TestComponents.BaseTest02;
import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAloneTest169 extends BaseTest02{
	
/*	public static String
    removeFirstandLast(String str)
    {
 
        // Removing first and last character
        // of a string using substring() method
        str = str.substring(1, str.length() - 1);
 
        // Return the modified string
        return str;
    }*/
	//This is now in the AbstractComponent class

	public String itemNumberReadableFormat;
	
	@Test(dataProvider="getData", groups= {"Purchase"})
	public void submitOrder(String emailDP, String passwordDP, String wantedProductDP) throws IOException
	{

		
		//Login to the landing page with the valid credentials and verify the login message
		
	//	LandingPagePO lppo = launchApplication(); IMPORTANT - since now we have @BeforeMethod we do not need this to be called, it is now public LandingPagePO lppo in the BaseTest02;
		
		lppo.loginApplication(emailDP, passwordDP); //lppo.loginApplication("unknownxjk@gmail.com", "kojikurac123");
		
		ProductCatalogPO pcpo = new ProductCatalogPO(driver);
			
		String expectedLoginMessage = "Login Successfully";
		String actualLoginMessage = pcpo.getLoginMessageText();
		Assert.assertEquals(actualLoginMessage, expectedLoginMessage);
		
		
		//Find all products on the page and make a list of them
		String wantedProduct = "ZARA COAT 3";
		
		pcpo.addProductToCart(wantedProductDP);
		
		
		//In Cart
		MyCartPO mcpo = new MyCartPO(driver);
		
		Assert.assertTrue(mcpo.isProductPresentInMyCart(wantedProductDP));
		
		mcpo.getItemNumber(wantedProductDP);
		
		mcpo.checkoutProduct();
			
		
		//Place order
		PlaceOrderPO popo = new PlaceOrderPO(driver);
		
		String partialCountry = "yugo";
		String wantedCountry = "Yugoslavia";
		
		popo.typeInCountry(partialCountry);
		
		popo.selectMyCountry(wantedCountry);
		
		popo.placeOrder();
		
		
		//Thank you page
		ThanksPagePO tppo = new ThanksPagePO(driver);
		
		String expectedThankYouMessage = "THANKYOU FOR THE ORDER.";
		String thankYouMessage = tppo.getThankYouMessage();
		Assert.assertEquals(thankYouMessage, expectedThankYouMessage);
		
		//To extract order ID
		
		//To get this order ID into readable format
		 itemNumberReadableFormat = tppo.getItemNumberReadableFormat();
		
		
	}
	
	
	@Test(dependsOnMethods = {"submitOrder"})
	public void ordersHistoryValidation()
	{
		
		
		//Go to Your Orders page and verify that the product is listed
		
		lppo.loginApplication("unknownxjk@gmail.com", "kojikurac123");
		
		ProductCatalogPO pcpo = new ProductCatalogPO(driver);
			
		String expectedLoginMessage = "Login Successfully";
		String actualLoginMessage = pcpo.getLoginMessageText();
		Assert.assertEquals(actualLoginMessage, expectedLoginMessage);
		
		YourOrdersPO yopo = new YourOrdersPO(driver);
		
		yopo.goToOrdersPage();
		
		boolean productPresentInOrders = yopo.isMyOrderPresentInOrders(itemNumberReadableFormat);
		System.out.println(itemNumberReadableFormat + "- from ordersHistoryValidation for JK");
		Assert.assertTrue(productPresentInOrders);
		
	}
	
	
	
	//Here we use a simple DataProvider for a small amount of data we want to send
	@DataProvider
	public Object[][] getData()
	{
		return new Object [][]  {{"unknownxjk@gmail.com", "kojikurac123", "ZARA COAT 3"}, {"anshika@gmail.com", "Iamking@000", "ADIDAS ORIGINAL"}}; //Object is a generic type so we can place there int, String, boolean
	}
	

}
