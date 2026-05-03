package listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utilities.BrowserUtility;
import utilities.ExtentReporterUtility;
import utilities.LoggerUtilities;

public class TestListeners implements ITestListener {

    Logger logger = LoggerUtilities.getLogger(this.getClass());


    @Override
    public void onTestFailure(ITestResult result) {
        logger.info("{} Failure", result.getMethod().getMethodName());
        ExtentReporterUtility.getTest().log(Status.FAIL , "Test Case Failed is: "+result.getMethod().getMethodName());
        String testName = result.getMethod().getMethodName();

        try {
            String path = BrowserUtility.takeScreenShot(testName);

            if (path != null) {
                ExtentReporterUtility.getTest()
                        .addScreenCaptureFromPath(path);
            }

        } catch (Exception e) {
            System.out.println("Screenshot failed: " + e.getMessage());
        }
    }

    @Override
    public void onTestStart(ITestResult result) {
        logger.info(result.getMethod().getMethodName());
        logger.info(result.getMethod().getDescription());
        ExtentReporterUtility.createExtentTest(result.getMethod().getMethodName());
//        logger.info(result.getMethod().getMethodName());
    }

    @Override
    public void onStart(ITestContext context) {
        logger.info("Test Suite Started");
        ExtentReporterUtility.setUpSparkReport();
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        logger.info(result.getMethod().getMethodName()+" " + "Passed");
        ExtentReporterUtility.getTest().log(Status.PASS , "Test Case Passed is: "+result.getMethod().getMethodName());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        logger.info(result.getMethod().getMethodName()+" "+"Skipped");
        ExtentReporterUtility.getTest().log(Status.SKIP , "Test Case Skipped is: "+result.getMethod().getMethodName());
    }

    @Override
    public void onFinish(ITestContext context) {
        logger.info("Test Suite Finished");
        ExtentReporterUtility.flushReport();
    }
}
