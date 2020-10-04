package base;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestNGListenerImpl extends PredefinedActions implements ITestListener {

    @Override
    public void onTestStart(ITestResult iTestResult) {
        System.out.println(iTestResult.getName());
        initializeBrowser("chrome", "qa");
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        closeBrowser();
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        captureScreenshot(iTestResult.getName());
        closeBrowser();
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        closeBrowser();
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

    @Override
    public void onStart(ITestContext iTestContext) {

    }

    @Override
    public void onFinish(ITestContext iTestContext) {

    }
}
