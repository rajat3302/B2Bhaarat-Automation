package utils;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Random;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import base.BaseLibrary;

public class ActionHelper extends BaseLibrary

{
	WebDriver driver;

    // ✅ Constructor hona chahiye
    public ActionHelper(WebDriver driver) 
    {
        this.driver = driver;
    }
    public void click(WebElement element) 
    {
        element.click();
    }
	public void doubleclick(WebElement ele, WebDriver driver)
	{
		Actions act = new Actions(driver);
		act.doubleClick(ele);
	}
	 
	public void rightclick(WebElement ele, WebDriver driver)
	{
		Actions act1= new Actions(driver);
	    act1.contextClick(ele);
	}
	
	public void mousehover(WebElement ele, WebDriver driver)
	{
		Actions act2 = new Actions(driver);
		act2.moveToElement(ele).perform();
	}
	
	public void mousehoverclick(WebElement ele, WebElement clickele)
	{
		Actions act = new Actions(driver);
		act.moveToElement(ele).click(clickele).build().perform();
	}
	
	public void clickByJS(WebDriver driver, WebElement element) 
	{
	    JavascriptExecutor js = (JavascriptExecutor) driver;
	    js.executeScript("arguments[0].scrollIntoView({block:'center'});", element);
	    js.executeScript("arguments[0].click();", element);
	}

	
	public int getCartCountAfterIncrease(WebDriver driver,
            WebElement cartCounter,
            int previousCount) {

WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

wait.until(d -> {
String text = cartCounter.getText().replaceAll("[^0-9]", "");
if (text.isEmpty()) return false;
return Integer.parseInt(text) > previousCount;
});

String finalText = cartCounter.getText().replaceAll("[^0-9]", "");
return finalText.isEmpty() ? 0 : Integer.parseInt(finalText);
}


	public  void chnagewindow(int tabno, WebDriver driver)
	{
		Set<String> set = driver.getWindowHandles();
		ArrayList<String> tab = new ArrayList<String>(set);
		driver.switchTo().window(tab.get(tabno));
	}
	
	public void getscroll (WebElement ele, WebDriver driver)
	{
		try 
		{
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].scrollIntoView(true);", ele);
		} 
		catch (Exception e) 
		{
			System.out.println("Issue in Getscroll" +e);
		}
	}
	
	public void waitForClickable(By locator) 
	{
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    wait.until(ExpectedConditions.elementToBeClickable(locator));
	}
	public void type(By locator, String text) 
	{
	    WebElement element = driver.findElement(locator);
	    element.clear();
	    element.sendKeys(text);
	}
	public void click(By locator) {
	    WebElement element = driver.findElement(locator);
	    element.click();
	}
	
	public String getText(By locator) {
	    return driver.findElement(locator).getText();
	}
	
	public void scrolltotop() throws InterruptedException
	{
		Thread.sleep(2000);
		JavascriptExecutor js= (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(200, 0);");
		
	}
	
	public void jsClick(By locator) {
	    WebElement element = driver.findElement(locator);
	    JavascriptExecutor js = (JavascriptExecutor) driver;
	    js.executeScript("arguments[0].click();", element);
	}
	
	
	public void clearAndType(By locator, String text) {
	    WebElement element = driver.findElement(locator);

	    element.click(); // focus

	    // 🔥 Strong clear (CTRL + A + DELETE)
	    element.sendKeys(Keys.chord(Keys.CONTROL, "a"));
	    element.sendKeys(Keys.DELETE);

	    element.sendKeys(text);
	}
	
	public String getText1(By locator) {
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	    return element.getText();
	}
	
	public void scrolltobottom()
	{
		JavascriptExecutor js= (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
	}
	
	
	
	public void selectDate() 
	
	{
        WebElement dateField = driver.findElement(By.xpath("datePickerInput"));
        dateField.clear(); // Clear existing value if needed
        dateField.sendKeys("2024-12-31"); // Enter desired date in the required format
    }

	public void dropdown(WebElement element, String visibleText)
	{
	
		Select sell = new Select(element);
		sell.selectByVisibleText(visibleText);
	}
	
	public void dropdownvalue (WebElement element, String value)
	{
	    Select sell = new Select(element);
	    sell.selectByValue(value);
	}

	
	public static String getRandomTestCard() 
	    {
	        String[] testCards = 
	            {

	            
	            "5555555555554444",  // MasterCard
	            "378282246310005",   // American Express
	            "6011111111111117",   // Discover
	            "6011111111111117",
	            "4035501428146300",   
	            "4360000001000005",

	            "4242424242424242",  // Visa
	            "5555555555554444",  // MasterCard
	            "378282246310005",   // American Express
	            "6011111111111117",   // Discover
	            "8171999927660000",   // visa
	            "4035501428146300",   
	            "4360000001000005",
	            "5127880999999990",

	            "6703444444444449",
	            "370000000000002"
	             };
	        Random random = new Random();
	        return testCards[random.nextInt(testCards.length)];
	    }

	    public static void main(String[] args) 
	    {
	        System.out.println("Random Test Card: " + getRandomTestCard());
	    }
	
	    public static String generateRandomMobileNumber() 
	    {
			Random random = new Random();

			// Mobile numbers in India typically start with 7, 8, or 9.
			int firstDigit = random.nextInt(3) + 7; // Generates 7, 8, or 9

			// Generate the remaining 9 digits
			long remainingDigits = random.nextLong(1000000000L); // Generates a random number between 0 and 999999999
			remainingDigits = Math.abs(remainingDigits); // Ensure it is non-negative

			// Combine the first digit with the remaining 9 digits
			String mobileNumber = firstDigit + String.format("%09d", remainingDigits);

			return mobileNumber;
	
        }
	    
	    
	    public void hardassertwithstring(String actualvalue, String expectedvalue) 
	    {
	    	 
			try 
			{
				
				Assert.assertEquals(actualvalue, expectedvalue);
				
				
			} 
			catch (AssertionError e) 
			{
				
				System.out.println("issuee in seertion"+e);
				throw e;
			}
	   }
	    public String getToastMessage(By locator) {
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	        
	        WebElement element = wait.until(
	            ExpectedConditions.presenceOfElementLocated(locator)
	        );
	        
	        return element.getText();
	    }
	    
	    public WebElement waitForvisible(By locator) {
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	    }
}

