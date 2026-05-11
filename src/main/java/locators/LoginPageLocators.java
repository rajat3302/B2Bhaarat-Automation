package locators;

import org.openqa.selenium.By;


public class LoginPageLocators 

{
	public static By signIn = By.xpath("//span[text()='Sign In']");
	public static By email =  By.name("email");
	public static By password =  By.name("password");
	public static By loginBtn = By.xpath("//button[@type='submit']");
	public static By loginErrorToast = By.xpath("//div[contains(@class,'notistack-MuiContent-error')]");
	public static By emailError = By.xpath("//p[contains(text(),'Email')]");
	public static By passwordError = By.xpath("//p[contains(text(),'Password')]");
	public static By isloginsuccessful = By.xpath("//h2[text()='Two-Step Verification']");
	
	
}
