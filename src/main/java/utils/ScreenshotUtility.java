package utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import io.qameta.allure.Attachment;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import base.BaseLibrary;

public class ScreenshotUtility extends BaseLibrary 
{

    public static String captureScreenshot(String testName) 
    {

        String timeStamp =
                new SimpleDateFormat("yyyyMMdd_HHmmss")
                .format(new Date());

        String path =
                System.getProperty("user.dir")
                + "/screenshots/"
                + testName + "_"
                + timeStamp + ".png";

        try 
        {

            File src =
                    ((TakesScreenshot) driver)
                    .getScreenshotAs(OutputType.FILE);

            File dest = new File(path);

            FileUtils.copyFile(src, dest);

            System.out.println(
                    "📸 Screenshot captured: "
                    + path);

        }
        catch (IOException e)
        {

            e.printStackTrace();
        }

        return path;
    }
    @Attachment(value = "Failure Screenshot", type = "image/png")
    public static byte[] attachScreenshot() {

        return ((TakesScreenshot) driver)
                .getScreenshotAs(OutputType.BYTES);
    }
}