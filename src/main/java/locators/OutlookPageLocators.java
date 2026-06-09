package locators;

import org.openqa.selenium.By;

public class OutlookPageLocators {

    public static By b2bFolder =
            By.xpath("//span[contains(text(),'b2bhaarat')]");

    public static By latestOtpMail =
            By.xpath("(//span[contains(text(),'Your B2Bhaarat Login OTP')])[1]");

    public static By otpText =
            By.xpath("//div[contains(@class,'x_otp-code')]");

}