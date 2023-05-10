package Academy.PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Academy.AbstractComponents.AbstractComponent;

public class YourOrdersPO extends AbstractComponent {

	
WebDriver driver;
	
	public YourOrdersPO(WebDriver driver)
	{
		super(driver); //This way we connect a child to a parent
		this.driver = driver; //The left driver is from this PO class, the right driver is from StandAloneTestAGAIN156
		PageFactory.initElements(driver, this); //The method for the PageFactory to knows about the driver
	}
	
	
	//String loginMessage = driver.findElement(By.cssSelector(".toast-title")).getText();
	//driver.findElement(By.cssSelector("button[routerlink*='cart']")).click();
	//List<WebElement> yourOrdersList = driver.findElements(By.xpath("//tbody/tr"));
	
	
	//PageFactory
		@FindBy(xpath="//tbody/tr")
		List<WebElement> yourOrdersList;
		
		@FindBy(css="button[routerlink*='myorders']")
		WebElement ordersButton;
		
		
		
		
		
		//By
		By itemNumberBy = By.cssSelector(".itemNumber");
		
		
		
		
		
		//Action Methods
	
		public void goToOrdersPage()
		{
			ordersButton.click();
		}
		
		public List<WebElement> getListYourOrders()
		{
			return yourOrdersList;
		}
		
		public boolean isMyOrderPresentInOrders(String itemNumberReadableFormat)
		{
			boolean productPresentInOrders = getListYourOrders().stream().anyMatch(oneOrder -> oneOrder.findElement(By.cssSelector("th")).getText().equals(itemNumberReadableFormat));
			System.out.println(productPresentInOrders);
			return productPresentInOrders;
		}
		
}
