package pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import locators.OTPPageLocators;

public class OTPPage {

    WebDriver driver;

    public OTPPage(WebDriver driver) {
        this.driver = driver;
    }

    public void enterOTP(String otp) throws InterruptedException {

        List<WebElement> boxes =
                driver.findElements(OTPPageLocators.otpBoxes);

        System.out.println("OTP Length = " + otp.length());
        System.out.println("Boxes Found = " + boxes.size());

        if(boxes.size() < 6) {
            throw new RuntimeException("OTP Boxes not found properly. Found = " + boxes.size());
        }

        boxes.get(0).click();

        for (int i = 0; i < otp.length(); i++) {

            System.out.println("Entering OTP Digit : " + otp.charAt(i));

            boxes.get(i)
                 .sendKeys(String.valueOf(otp.charAt(i)));

            Thread.sleep(500);
        }
    }

    public void clickVerify() {

        driver.findElement(OTPPageLocators.verifyButton).click();
        System.out.println("Clicking Verify button");
    }
}