package listeners;

import org.testng.ITestListener;
import org.testng.ITestResult;

import utils.ScreenshotUtility;

public class TestListener implements ITestListener 
{

	@Override
	public void onTestFailure(ITestResult result) {

	    String testName = result.getName();

	    ScreenshotUtility.captureScreenshot(testName);

	    ScreenshotUtility.attachScreenshot();

	    System.out.println("❌ Test Failed : " + testName);
	}

    @Override
    public void onTestSuccess(ITestResult result) 
    {

        System.out.println("✅ Test Passed : " + result.getName());
    }
}