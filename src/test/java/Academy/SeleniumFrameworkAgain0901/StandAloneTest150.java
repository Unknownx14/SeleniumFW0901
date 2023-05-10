package Academy.SeleniumFrameworkAgain0901;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import Academy.PageObjects.YourOrdersPO;
import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAloneTest150 {
	
	public static String
    removeFirstandLast(String str)
    {
 
        // Removing first and last character
        // of a string using substring() method
        str = str.substring(1, str.length() - 1);
 
        // Return the modified string
        return str;
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		WebDriverManager.chromedriver().setup(); // WebDriver manager for invoking the chromedriver that is not on our local machine
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		//driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://www.rahulshettyacademy.com/client");
		
		
		driver.findElement(By.cssSelector("#userEmail")).sendKeys("unknownxjk@gmail.com");
		driver.findElement(By.cssSelector("#userPassword")).sendKeys("kojikurac123");
		driver.findElement(By.cssSelector("#login")).click();
		
		//Explicit wait - define the object of the class
		WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(5));
		w.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".toast-title")));
		
		String expectedLoginMessage = "Login Successfully";
		String loginMessage = driver.findElement(By.cssSelector(".toast-title")).getText();
		Assert.assertEquals(loginMessage, expectedLoginMessage);
		
		
		//Find all products on the page and make a list of them
		w.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".card-body")));
		
		String wantedProduct = "ZARA COAT 3";
		List<WebElement> allProductsList =driver.findElements(By.cssSelector(".card-body"));
		
		WebElement singleProduct = allProductsList.stream().filter(oneProduct-> oneProduct.findElement(By.cssSelector("b")).getText().equals(wantedProduct))
		.findFirst().orElse(null);
		
		singleProduct.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		
		w.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".toast-message")));
		//w.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-animating")));
		w.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating")))); //This way is more quick
		driver.findElement(By.cssSelector("button[routerlink*='cart']")).click();
		
		
		//In Cart
		List<WebElement> myCartProducts = driver.findElements(By.cssSelector(".cartWrap.ng-star-inserted"));
		WebElement singleProdInMyCart = myCartProducts.stream().filter(oneProd->oneProd.findElement(By.cssSelector("h3")).getText().equals(wantedProduct))
		.findFirst().orElse(null);
		
		boolean productPresentInCart = myCartProducts.stream().anyMatch(oneProdCart -> oneProdCart.findElement(By.cssSelector("h3")).getText().equals(wantedProduct));
		Assert.assertTrue(productPresentInCart);
		System.out.println(productPresentInCart);
		
		String itemNumber = singleProdInMyCart.findElement(By.cssSelector(".itemNumber")).getText();
		System.out.println(itemNumber);
		
		driver.findElement(By.xpath("//button[text()='Checkout']")).click();
		
		
		//Place order
		String partialCountry = "yugo";
		String wantedCountry = "Yugoslavia";
		
		driver.findElement(By.cssSelector("input[placeholder='Select Country']")).sendKeys(partialCountry);

		w.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
		
		List<WebElement> allCountries = driver.findElements(By.cssSelector(".ta-item"));
		WebElement myCountry = allCountries.stream().filter(oneCountry -> oneCountry.findElement(By.cssSelector("span[class='ng-star-inserted']")).getText().equals(wantedCountry))
		.findFirst().orElse(null);
		myCountry.click();
		
		driver.findElement(By.cssSelector(".action__submit")).click();
		
		//Thank you page
		w.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mt-3")));
		
		String expectedThankYouMessage = "THANKYOU FOR THE ORDER.";
		String thankYouMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertEquals(thankYouMessage, expectedThankYouMessage);
		
		//To extract order ID
		String rawItemNumber = driver.findElement(By.cssSelector("label[class='ng-star-inserted']")).getText();
		System.out.println(rawItemNumber);
		
		//To get this order ID into readable format
		String removeCharactersIdNumber = removeFirstandLast(rawItemNumber);
		String itemNumberReadableFormat = removeCharactersIdNumber.trim();
		System.out.println(itemNumberReadableFormat);
		
		
		//Go to Your Orders page and verify that the product is listed
				
				driver.findElement(By.cssSelector("button[routerlink*='myorders']")).click();
				List<WebElement> yourOrdersList = driver.findElements(By.xpath("//tbody/tr"));
				boolean productPresentInOrders = yourOrdersList.stream().anyMatch(oneOrder -> oneOrder.findElement(By.cssSelector("th")).getText().equals(itemNumberReadableFormat));
				System.out.println(productPresentInOrders);
				Assert.assertTrue(productPresentInOrders);
		
		driver.close();

		
	}

}
