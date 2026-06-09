package pages;

import org.openqa.selenium.WebDriver;

import locators.OutlookPageLocators;

public class OutlookPage {

    WebDriver driver;

    public OutlookPage(WebDriver driver) {
        this.driver = driver;
    }

    public void openB2BFolder() {

        driver.findElement(
                OutlookPageLocators.b2bFolder)
                .click();
    }

    public void openLatestOtpMail() {

        driver.findElement(
                OutlookPageLocators.latestOtpMail)
                .click();
    }

    public String getOTP() {

        String otp =
                driver.findElement(
                        OutlookPageLocators.otpText)
                        .getText();

        System.out.println("OTP Found : " + otp);

        return otp;
    }
}