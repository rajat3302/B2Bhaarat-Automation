package base;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.IReporter;
import org.testng.ISuite;
import org.testng.annotations.AfterTest;
import org.testng.xml.XmlSuite;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseLibrary 
{
static public WebDriver driver;
    

    
	public static void getLaunchUrl (String url)
	{
		WebDriverManager.chromedriver().setup();
	    ChromeOptions options = new ChromeOptions();
//	    options.addArguments("--headless", "--no-sandbox", "--disable-dev-shm-usage");
//	    options.addArguments("--headless");  // no UI
//	    options.addArguments("--headless=new");
//	    options.addArguments("--window-size=1920,1080");
	    options.addArguments("--disable-gpu");
	    options.addArguments("--disable-blink-features=AutomationControlled");
	    options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
	    options.setExperimentalOption("useAutomationExtension", false);
	    options.addArguments("--ignore-certificate-errors");
//	     options.addArguments("--incognito");
//	      options.addArguments("--disable-save-password-bubble");
//        options.addArguments("--disable-popup-blocking");
//        options.addArguments("--disable-infobars");

        Map<String, Object> prefs = new HashMap<>();
        prefs.put("credentials_enable_service", false); // Disable password manager
        prefs.put("profile.password_manager_enabled", false); // Disable password save prompt
        prefs.put("autofill.credit_card_enabled", false); // Disable save card option
        prefs.put("autofill.profile_enabled", false); // Disable autofill completely

        options.setExperimentalOption("prefs", prefs);
//        options.addArguments(
//        		"--user-data-dir=C:\\Users\\rajat.shrotriya\\AppData\\Local\\Google\\Chrome\\User Data");
//
//        		options.addArguments("--profile-directory=Default");
        
        
		driver= new ChromeDriver(options);
		//driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
		
		driver.get(url);
		driver.manage().window().maximize();
//		driver.manage().window().setSize(new Dimension(1920, 1080));
	
	}

	
	
	

//	    @BeforeClass
//	    public void setup() 
//	    {
//	        ExtentSparkReporter spark = new ExtentSparkReporter("extent-report.html");
//	        extent = new ExtentReports();
//	        extent.attachReporter(spark);
//	    }
//
//	    @Test
//	    public void testPass() {
//	        test = extent.createTest("Test Passed");
//	        test.pass("This test passed successfully.");
//	    }
//
//	    @Test
//	    public void testFail() {
//	        test = extent.createTest("Test Failed");
//	        test.fail("This test failed.");
//	    }
//
//	    @AfterClass
//	    public void teardown() {
//	        extent.flush(); // Write the report to the file
//	    }
//	}
//	
	public void initializedriver(String browser, String url)
	{
		if(browser.equalsIgnoreCase("chrome"))
		{
			System.setProperty("webdriver.chrome.driver", "C:\\GlobalViews\\Comglobalview\\driver\\chromedriver.exe");
			 WebDriverManager.chromedriver().setup();
			ChromeOptions options = new ChromeOptions();
		    options.addArguments("--incognito");
			driver = new ChromeDriver();
			driver.get(url);
					
		}
		else if (browser.equalsIgnoreCase("firefox"))
		{
			System.setProperty("webdriver.gecko.driver", "C:\\GlobalViews\\Comglobalview\\driver\\geckodriver.exe");
			WebDriverManager.firefoxdriver().setup();
			FirefoxOptions options = new FirefoxOptions();
	        options.addArguments("-private");
			driver = new FirefoxDriver();
			driver.get(url);
			
		}
		else if (browser.equalsIgnoreCase("edge")) 
		{
			System.setProperty("webdriver.edge.driver", "C:\\GlobalViews\\Comglobalview\\driver\\msedgedriver.exe");
			WebDriverManager.edgedriver().setup();
			EdgeOptions options = new EdgeOptions(); 
	        options.addArguments("-inprivate");
			driver = new EdgeDriver();
			driver.get(url);
			
		}
		else
		{
			throw new IllegalArgumentException("Browser not supported: "+browser);
		}
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}
	
	
	
	public class CustomReporter implements IReporter 
	{

	    public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) 
	        
	{
	        System.out.println("Generating custom report...");
	        // Example: Extract details from test results and format a custom report.
	        for (ISuite suite : suites) {
	            suite.getResults().forEach((key, result) -> {
	                System.out.println("Suite Name: " + suite.getName());
	                System.out.println("Test Name: " + result.getTestContext().getName());
	                System.out.println("Passed Tests: " + result.getTestContext().getPassedTests().size());
	                System.out.println("Failed Tests: " + result.getTestContext().getFailedTests().size());
	                System.out.println("Skipped Tests: " + result.getTestContext().getSkippedTests().size());
	            });
	        }
	    }
	}
	
    public String failed(String testMethodName) throws IOException
    {
    	File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE); 
    	try
    	{
    	FileUtils.copyFile(srcFile, new File("C:\\GlobalViews\\Comglobalview/screenshots/"+testMethodName+"_"+".jpg")); 
    	}
    	catch(IOException e)
    	{
    		e.printStackTrace();
    	}
    	
    	return testMethodName;
    }
	
	
	@AfterTest
	public void teardown()
	{
		//driver.quit();
		
	}
}
