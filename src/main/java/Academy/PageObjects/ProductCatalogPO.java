package Academy.PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Academy.AbstractComponents.AbstractComponent;

public class ProductCatalogPO extends AbstractComponent {

	
WebDriver driver;
	
	public ProductCatalogPO(WebDriver driver)
	{
		super(driver); //This way we connect a child to a parent
		this.driver = driver; //The left driver is from this PO class, the right driver is from StandAloneTestAGAIN156
		PageFactory.initElements(driver, this); //The method for the PageFactory to knows about the driver
	}
	
	
	//String loginMessage = driver.findElement(By.cssSelector(".toast-title")).getText();
	//driver.findElement(By.cssSelector("button[routerlink*='cart']")).click();
	
	
	//PageFactory
		@FindBy(css=".card-body")
		List<WebElement> allProductsList;
		
		@FindBy(css=".toast-title")
		WebElement toastTitle;
		
		@FindBy(css=".ng-animating")
		WebElement animating;
		
		@FindBy(css="button[routerlink*='cart']")
		WebElement CartButton;
		
		
		
		//By
		By toastTitleBy = By.cssSelector(".toast-title");
		By cardBodyBy = By.cssSelector(".card-body");
		By addToCartButtonBy = By.cssSelector(".card-body button:last-of-type");
		By toastMessageBy = By.cssSelector(".toast-message");
		
		
		
		
		//Action Methods
	
		public String getLoginMessageText()
		{
			waitForElementToAppear(toastTitleBy);
			String loginMessage =  toastTitle.getText();
			System.out.println(loginMessage);
			return loginMessage;
		}
		
		public List<WebElement> getListOfAllProducts()
		{
			waitForElementToAppear(cardBodyBy);
			return allProductsList;
		}
		
		public WebElement getProductByName(String wantedProduct)
		{
			WebElement singleProduct = getListOfAllProducts().stream().filter(oneProduct-> oneProduct.findElement(By.cssSelector("b")).getText().equals(wantedProduct))
					.findFirst().orElse(null);
			return singleProduct;
		}
		
		public void addProductToCart(String wantedProduct)
		{
			WebElement singleProduct = getProductByName( wantedProduct);
			singleProduct.findElement(addToCartButtonBy).click();
			waitForElementToAppear(toastMessageBy);
			waitForElementToDisappear(animating);
			CartButton.click();
		}
		
}
