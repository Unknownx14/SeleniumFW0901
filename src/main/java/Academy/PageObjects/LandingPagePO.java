package Academy.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Academy.AbstractComponents.AbstractComponent;

public class LandingPagePO extends AbstractComponent {
	
	WebDriver driver;
	
	public LandingPagePO(WebDriver driver)
	{
		super(driver); //This way we connect a child to a parent
		this.driver = driver; //The left driver is from this PO class, the right driver is from StandAloneTestAGAIN156
		PageFactory.initElements(driver, this); //The method for the PageFactory to knows about the driver
	}
	
	
	//WebElement userEmail = driver.findElement(By.cssSelector("#userEmail"));
	//String actualErrorMessage = driver.findElement(By.cssSelector(".ng-trigger-flyInOut")).getText();
	
	
	//PageFactory
		@FindBy(css="#userEmail")
		WebElement userEmail;
		
		@FindBy(css="#userPassword")
		WebElement userPassword;
		
		@FindBy(css="#login")
		WebElement loginButton;
		
		@FindBy(css=".ng-trigger-flyInOut")
		WebElement incorrectMessage;
		
		//By
		By incorrectMessageBy = By.cssSelector(".ng-trigger-flyInOut");
		
		
		//Action Methods
		public void loginApplication(String email, String password)
		{
			userEmail.sendKeys(email);
			userPassword.sendKeys(password);
			loginButton.click();
		}
		
		public void goTo()
		{
			driver.get("https://www.rahulshettyacademy.com/client");
		}
		
		public String getIncorrectMessage()
		{
			waitForElementToAppear(incorrectMessageBy);
			String actualErrorMessage = incorrectMessage.getText();
			return actualErrorMessage;
		}

}
