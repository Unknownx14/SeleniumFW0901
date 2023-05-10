package Academy.PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import Academy.AbstractComponents.AbstractComponent;

public class PlaceOrderPO extends AbstractComponent {

	
WebDriver driver;
	
	public PlaceOrderPO(WebDriver driver)
	{
		super(driver); //This way we connect a child to a parent
		this.driver = driver; //The left driver is from this PO class, the right driver is from StandAloneTestAGAIN156
		PageFactory.initElements(driver, this); //The method for the PageFactory to knows about the driver
	}
	
	
	//String loginMessage = driver.findElement(By.cssSelector(".toast-title")).getText();
	//driver.findElement(By.cssSelector("button[routerlink*='cart']")).click();
	//driver.findElement(By.cssSelector(".action__submit")).click();
	
	
	//PageFactory
		@FindBy(css=".ta-item")
		List<WebElement> allCountries;
		
		@FindBy(css="input[placeholder='Select Country']")
		WebElement selectCountry;
		
		@FindBy(css=".action__submit")
		WebElement placeOrderButton;
		
		
		
		
		
		//By
		By countriesBy = By.cssSelector(".ta-results");
		
		
		
		
		//Action Methods
	
		public void typeInCountry(String partialCountry)
		{
			selectCountry.sendKeys(partialCountry);
		}
		
		public List<WebElement> getListAllCountries()
		{
			waitForElementToAppear(countriesBy);
			
			return allCountries;
		}
		
		public void selectMyCountry(String wantedCountry)
		{
			WebElement myCountry = getListAllCountries().stream().filter(oneCountry -> oneCountry.findElement(By.cssSelector("span[class='ng-star-inserted']")).getText().equals(wantedCountry))
					.findFirst().orElse(null);
					myCountry.click();
		}
		
		public void placeOrder()
		{
			placeOrderButton.click();
		}
		
		
}
