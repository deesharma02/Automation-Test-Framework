package tests;

import Pages.HomePage;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import utilities.BrowserUtility;
import utilities.LambdaTestUtility;

import constants.Browser;

public class BaseTest {

    protected HomePage homePage;
    private boolean isLambdaTest;

    @Parameters({"browser" , "isHeadless" , "isLambdaTest"})
    @BeforeMethod(description = "Setting up the page")
    public void setUp(@Optional("chrome") String browser,@Optional("false") boolean isHeadless,@Optional("false") boolean isLambdaTest, ITestResult result){
        WebDriver lambdaDriver;
        this.isLambdaTest = isLambdaTest;
        if(isLambdaTest){
            lambdaDriver = LambdaTestUtility.intializaLambdaTest(browser , result.getMethod().getMethodName());
            homePage = new HomePage(lambdaDriver);
        }
        BrowserUtility.closeBrowser();
        homePage = new HomePage(Browser.valueOf(browser.toUpperCase()) , isHeadless);
    }

    public BrowserUtility getInstance(){
        return homePage;
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(){
        if(isLambdaTest){
            LambdaTestUtility.closeBrowser();
        }
        BrowserUtility.closeBrowser();
    }

}
