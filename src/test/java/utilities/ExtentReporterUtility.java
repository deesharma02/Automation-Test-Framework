package utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReporterUtility {

    private static ExtentReports extentReports;
    private static final ThreadLocal<ExtentTest> extentTestThreadLocal = new ThreadLocal<>();

    public static void setUpSparkReport(){
        ExtentSparkReporter extentSparkReporter = new ExtentSparkReporter(System.getProperty("user.dir") + "/reports/myreport.html");

        extentSparkReporter.config().setDocumentTitle("Automation Report");
        extentSparkReporter.config().setReportName("Functional Testing");
        extentSparkReporter.config().setTheme(Theme.DARK);

        extentReports = new ExtentReports();
        extentReports.attachReporter(extentSparkReporter);
    }

    public static void createExtentTest(String testName){
        ExtentTest test = extentReports.createTest(testName);
        extentTestThreadLocal.set(test);
    }

    public static ExtentTest getTest(){
        return extentTestThreadLocal.get();
    }

    public static void flushReport(){
        extentReports.flush();
    }

}
