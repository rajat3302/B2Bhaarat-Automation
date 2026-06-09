package locators;

import org.openqa.selenium.By;

public class OTPPageLocators {

	public static By otpBoxes =
		    By.cssSelector(".lp-mfa__otp-row input");

    public static By verifyButton =
            By.xpath("//button[text()='Verify & Continue']");
}