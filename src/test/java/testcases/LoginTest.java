package testcases;
import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.BaseLibrary;
import locators.LoginPageLocators;
import pages.LoginPage;
import utilities.ExcelUtility;

public class LoginTest extends BaseLibrary
{

	
	LoginPage login;
	
	@DataProvider(name = "loginData")
	public Object[][] getData() {

	    ExcelUtility excel = new ExcelUtility("src/test/resources/LoginData.xlsx");

	    int rows = excel.getRowCount("LoginData");

	    List<Object[]> dataList = new ArrayList<>();

	    for(int i=1; i<rows; i++) {

	        String email = excel.getCellData("LoginData", i, 1).trim();
	        String password = excel.getCellData("LoginData", i, 2).trim();
	        String expected = excel.getCellData("LoginData", i, 3).trim();

	        // 🔥 BLANK ROW FILTER
	        if((email == null || email.trim().isEmpty()) &&
	           (password == null || password.trim().isEmpty()) &&
	           (expected == null || expected.trim().isEmpty())) {
	            continue;
	        }

	        dataList.add(new Object[]{email, password, expected});
	    }

	    // convert to Object[][]
	    Object[][] data = new Object[dataList.size()][3];

	    for(int i=0; i<dataList.size(); i++) {
	        data[i] = dataList.get(i);
	    }

	    return data;
	}
	 @BeforeClass
	 public void getlaunchurl_globalview()
		{
			getLaunchUrl("https://staging.b2bhaarat.com/");
			
			login = new LoginPage(driver);
			
			login.clickSignIn();
			login.waitForLoginPage();
		}
	 @BeforeMethod
	 
	 public void resetToLoginPage() 
	 {
	     driver.get("https://staging.b2bhaarat.com/login");
	 }
	 @Test(dataProvider = "loginData")
	 public void TC_Login(String email, String password, String expected)
	 {
	     System.out.println("Email: " + email);
	     System.out.println("Password: " + password);
	     System.out.println("Expected: " + expected);

	     if(email != null && !email.isEmpty()) {
	         login.enterEmail(email);
	     }

	     if(password != null && !password.isEmpty()) {
	         login.enterPassword(password);
	     }

	     login.clickLogin();

	     // 🔥 CASE HANDLING
	     //login.clickLogin();

	     switch(expected.toUpperCase()) {

	         case "EMAIL_REQUIRED":
	             String emailError = login.getEmailValidation();
	             System.out.println("Email Error: " + emailError);
	             Assert.assertTrue(emailError.contains("Email ID is required"));
	             break;

	         case "PASSWORD_REQUIRED":
	             String passError = login.getPasswordValidation();
	             System.out.println("Password Error: " + passError);
	             Assert.assertTrue(passError.contains("Password is required"));
	             break;

	         case "INVALID_EMAIL_FORMAT":
	             String browserMsg = login.getBrowserValidationMessage();
	             System.out.println("Browser Msg: " + browserMsg);
	             Assert.assertTrue(
	                 browserMsg.toLowerCase().contains("include") ||
	                 browserMsg.toLowerCase().contains("valid") ||
	                 browserMsg.contains("@")
	             );
	             break;

	         case "INVALID_LOGIN":
	             String error = login.getLoginError();
	             System.out.println("Error: " + error);
	             Assert.assertTrue(error.contains("Invalid login details"));
	             break;

	         case "SUCCESS":
	             Assert.assertTrue(false);
	             break;
	     }
	 }
	 
	 @AfterClass
	 public void tearDown()
	 {
		// driver.quit();
	 }
	 
		
}
