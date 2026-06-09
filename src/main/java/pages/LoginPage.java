package pages;

import org.checkerframework.checker.fenum.qual.SwingTextOrientation;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import io.qameta.allure.Step;
import locators.LoginPageLocators;
import utils.ActionHelper;

public class LoginPage 

{
	 WebDriver driver;
	    ActionHelper action;
	    
	    @Step("Wait for Login Page")
	    public void waitForLoginPage() {
	        action.waitForvisible(LoginPageLocators.email);
	    }
  
	    public LoginPage(WebDriver driver) 
	    {
	        this.driver = driver;
	        action = new ActionHelper(driver);
	    }

	    @Step("Click Sign In")
	    public void clickSignIn() {
	        action.waitForClickable(LoginPageLocators.signIn);
	        action.jsClick(LoginPageLocators.signIn); // 💥 replace normal click
	    }
	    
	    @Step("Enter Email: {0}")
	    public void enterEmail(String email) {
	        action.waitForvisible(LoginPageLocators.email);

	        WebElement e = driver.findElement(LoginPageLocators.email);
	        e.sendKeys(Keys.CONTROL + "a");
	        e.sendKeys(Keys.DELETE);
	        e.sendKeys(email);
	    }

	    @Step("Enter Password")
	    public void enterPassword(String pass) {
	        action.waitForvisible(LoginPageLocators.password);

	        WebElement e = driver.findElement(LoginPageLocators.password);
	        e.sendKeys(Keys.CONTROL + "a");
	        e.sendKeys(Keys.DELETE);
	        e.sendKeys(pass);
	    }

	    @Step("Click Login Button")
	    public void clickLogin() {
	        action.click(LoginPageLocators.loginBtn);
	    }
	    
	    
	    public String getEmailValidation() {
	        return driver.findElement(LoginPageLocators.emailError).getText();
	    }
	    public String getPasswordValidation() {
	        return driver.findElement(LoginPageLocators.passwordError).getText();
	    }
        
	    @Step("Verify OTP Page Displayed")
	    public boolean isLoginSuccessful() {
	        try {
	           return action.waitForvisible(LoginPageLocators.isloginsuccessful).isDisplayed();
	        } catch(Exception e) {
	            return false;
	        }
	    }
	    
	    
	    public String getBrowserValidationMessage() {
	        WebElement emailField = driver.findElement(LoginPageLocators.email);
	        return emailField.getAttribute("validationMessage");
	    }
	 
	    public String getErrorMessage() {
	        return driver.findElement(LoginPageLocators.loginErrorToast).getText();
	    }
	    
	    @Step("Verify Invalid Login Error Message")
	    public String getLoginError() {
	        try {
	            action.waitForvisible(LoginPageLocators.loginErrorToast);
	            return driver.findElement(LoginPageLocators.loginErrorToast).getText();
	        } catch (Exception e) {
	            return "Error message not found";
	        }
	    }
}