package Academy.PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import Academy.AbstractComponents.AbstractComponent;

public class ThanksPagePO extends AbstractComponent {

	
WebDriver driver;
	
	public ThanksPagePO(WebDriver driver)
	{
		super(driver); //This way we connect a child to a parent
		this.driver = driver; //The left driver is from this PO class, the right driver is from StandAloneTestAGAIN156
		PageFactory.initElements(driver, this); //The method for the PageFactory to knows about the driver
	}
	
	
	//String loginMessage = driver.findElement(By.cssSelector(".toast-title")).getText();
	//driver.findElement(By.cssSelector("button[routerlink*='cart']")).click();
	//String rawItemNumber = driver.findElement(By.cssSelector("label[class='ng-star-inserted']")).getText();
	//System.out.println(rawItemNumber);
	
	
	//PageFactory
		
		@FindBy(css=".hero-primary")
		WebElement thankYou;
		
		@FindBy(css="label[class='ng-star-inserted']")
		WebElement itemNumber;
		
		
		
		
		
		//By
		By thanksBy = By.cssSelector(".mt-3");
		
		
		
		
		//Action Methods
	
		public String getThankYouMessage()
		{
			waitForElementToAppear(thanksBy);
			String thankYouMessage = thankYou.getText();
			System.out.println(thankYouMessage);
			return thankYouMessage;
		}
		
		public String getRawItemNumber()
		{
			String rawItemNumber = itemNumber.getText();
			System.out.println(rawItemNumber);
			return rawItemNumber;
		}
		
		public String getItemNumberReadableFormat()
		{
			String removeCharactersIdNumber = removeFirstandLast(getRawItemNumber());
			String itemNumberReadableFormat = removeCharactersIdNumber.trim();
			System.out.println(itemNumberReadableFormat);
			return itemNumberReadableFormat;
		}
		
		
}
