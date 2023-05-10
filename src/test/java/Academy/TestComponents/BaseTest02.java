package Academy.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import Academy.PageObjects.LandingPagePO;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest02 {
	
	
	//Here we have done the following - Initializing the WebDriver driver is moved here, Properties class is used for .properties file, moved all common things
	//at the end of the IfElse loop, made sure that WebDriver driver is stated at the beginning
	
	public WebDriver driver;
	public LandingPagePO lppo;
	
	public WebDriver initializeDriver() throws IOException
	{
		
		//Properties class is for using GlobalData.properties file
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "\\src\\main\\java\\Academy\\Resources\\GlobalData.properties");
		prop.load(fis);
		String browserName = prop.getProperty("browser");
		
		if(browserName.equals("chrome"))
		{
		WebDriverManager.chromedriver().setup(); // WebDriver manager for invoking the chromedriver that is not on our local machine
		 driver = new ChromeDriver();
		
		}
		else if(browserName.equals("firefox"))
		{
			System.setProperty("webdriver.gecko.driver", "C:\\JK\\geckodriver-v0.31.0-win64\\geckodriver.exe");
			 driver = new FirefoxDriver();
			
		}
		else if(browserName.equals("edge"))
		{
			System.setProperty("webdriver.edge.driver", "C:\\JK\\edgedriver_win64\\msedgedriver.exe");
			 driver = new EdgeDriver();
			
		}
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		//driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		return driver;
		
	}
	
	@BeforeMethod(alwaysRun=true)
	public LandingPagePO launchApplication() throws IOException
	{
		 driver = initializeDriver();
		 lppo = new LandingPagePO(driver);
		lppo.goTo();
		return lppo;
	}
	
	@AfterMethod(alwaysRun=true)
	public void closeBrowser()
	{
		driver.close();
	}
	
	
	public List<HashMap<String, String>> getJsonDataToHashMap(String FilePath) throws IOException
	{
		//Read json file as a String
				String jsonContent = FileUtils.readFileToString(new File(FilePath), 
						StandardCharsets.UTF_8);//new File(System.getProperty("user.dir") + "\\src\\test\\java\\Academy\\data\\PurchaseOrder.json" is now sent
												//from our TC so we do not have a hardcoded json file
				
				//Convert this String to a HashMap with the Jackson Databind dependency
				ObjectMapper mapper = new ObjectMapper();
				List<HashMap<String, String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>(){});
				//As a result we get these HashMaps {hmap}, {hmap01} as a List!!!
				return data;		
		
	}
	
	
	public String getScreenshot(String testCaseName, WebDriver driver) throws IOException
	{
		TakesScreenshot ts = (TakesScreenshot)driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir") + "\\reports\\" + testCaseName + ".png");
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir") + "\\reports\\" + testCaseName + ".png";
		//This method returns a path where a screenshot is stored
	}
	
	

}
