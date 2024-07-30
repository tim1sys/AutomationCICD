package companytim.Testcomponent;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import companytim.pageobjectmodel.Landingpage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	
	public WebDriver driver;
	public Landingpage landingpage ;
	
	public WebDriver InitializeDriver() throws IOException {
		
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\companytim\\resources\\globalvariables.properties");
		
		prop.load(fis);
		// using if and then statement with ? and : 
	String BrowserName=System.getProperty("browser")!=null ? System.getProperty("browser"):	prop.getProperty("browser");
	//HEADLESS mode is when you dont let browser open but running
	
	if(BrowserName.contains("chrome")) 
	{
		
		ChromeOptions options = new ChromeOptions();
		WebDriverManager.chromedriver().setup();
		if(BrowserName.contains("headless")) {
		options.addArguments("headless");
		driver = new ChromeDriver(options);
		driver.manage().window().setSize(new Dimension(1920,1080));
		}
		else
		{
			driver= new ChromeDriver();
		}
		 //driver = new ChromeDriver(options);
		// driver.manage().window().fullscreen();
		 //1920,1080
		
	}
	else if(BrowserName.equalsIgnoreCase("firefox")) 
	{
		
		 driver = new FirefoxDriver();
		
	}else if(BrowserName.equalsIgnoreCase("edge")) 
	{
		
		 driver = new EdgeDriver();
		
	}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		return driver;
	}
	
		public List<HashMap<String, String>> getStringFromJson(String Filepath) throws IOException {
			
			// get file to string 
			String Jsoncontent = FileUtils.readFileToString(new File(Filepath),
					StandardCharsets.UTF_8);
			
			// turn string to hashmap using jackson databind 
			
			ObjectMapper mapper = new ObjectMapper();
			List<HashMap<String, String>> data = mapper.readValue(Jsoncontent, new TypeReference<List<HashMap<String, String>>>(){});
			return data;
			
		}
		
		public String getScreenshot(String testcaseSS, WebDriver driver) throws IOException {
			
			 TakesScreenshot ts =(TakesScreenshot)driver;
			File src =  ts.getScreenshotAs(OutputType.FILE);
			File file = new File(System.getProperty("user.dir")+ "src\\test\\java\\companytim\\data" +testcaseSS + ".png");
			 FileUtils.copyFile(src, file );
			 return System.getProperty("user.dir")+ "src\\test\\java\\companytim\\data" +testcaseSS + ".png";
		}

	@BeforeMethod(alwaysRun=true) // this will make test run first before other ones 
	public Landingpage LaunchApplication() throws IOException {
		
		driver = InitializeDriver();
		 landingpage= new Landingpage(driver);
			landingpage.goTo();
			return landingpage;
	}
	
	@AfterMethod(alwaysRun=true)
	public void TearDown() {
		driver.close();
	}

}
