package Academy.PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Academy.AbstractComponents.AbstractComponent;

public class MyCartPO extends AbstractComponent {

	
WebDriver driver;
	
	public MyCartPO(WebDriver driver)
	{
		super(driver); //This way we connect a child to a parent
		this.driver = driver; //The left driver is from this PO class, the right driver is from StandAloneTestAGAIN156
		PageFactory.initElements(driver, this); //The method for the PageFactory to knows about the driver
	}
	
	
	//String loginMessage = driver.findElement(By.cssSelector(".toast-title")).getText();
	//driver.findElement(By.cssSelector("button[routerlink*='cart']")).click();
	//driver.findElement(By.xpath("//button[text()='Checkout']"))
	
	
	//PageFactory
		@FindBy(css=".cartWrap.ng-star-inserted")
		List<WebElement> myCartProducts;
		
		@FindBy(xpath="//button[text()='Checkout']")
		WebElement checkOut;
		
		
		
		
		
		//By
		By itemNumberBy = By.cssSelector(".itemNumber");
		
		
		
		
		
		//Action Methods
	
		public List<WebElement> getListOfProductsInMyCart()
		{
			return myCartProducts;
		}
		
		public WebElement getSingleProductInMyCart(String wantedProduct)
		{
			WebElement singleProdInMyCart = getListOfProductsInMyCart().stream().filter(oneProd->oneProd.findElement(By.cssSelector("h3")).getText().equals(wantedProduct))
					.findFirst().orElse(null);
			
			return singleProdInMyCart;
		}
		
		public boolean isProductPresentInMyCart(String wantedProduct)
		{
			boolean productPresentInCart = getListOfProductsInMyCart().stream().anyMatch(oneProdCart -> oneProdCart.findElement(By.cssSelector("h3")).getText().equals(wantedProduct));
			System.out.println(productPresentInCart);
			return productPresentInCart;
		}
		
		public void getItemNumber(String wantedProduct)
		{
			String itemNumber = getSingleProductInMyCart(wantedProduct).findElement(itemNumberBy).getText();
			System.out.println(itemNumber);
		}
		
		public void checkoutProduct()
		{
			checkOut.click();
		}
		
}
