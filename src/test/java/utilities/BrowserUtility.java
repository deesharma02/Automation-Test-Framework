package utilities;

import constants.Browser;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BrowserUtility {

    private static ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();

    protected BrowserUtility(WebDriver driver){
        super();
        driverThreadLocal.set(driver);
    }


    protected BrowserUtility(String driver){
        super();
        if(driver.equalsIgnoreCase("chrome")){
            driverThreadLocal.set(new ChromeDriver());
        } else if(driver.equalsIgnoreCase("edge")) {
            driverThreadLocal.set(new EdgeDriver());
        }else{
            System.out.println("Invalid Browser Name");
        }
    }

    protected BrowserUtility(Browser driver){
        super();
        if(driver == Browser.CHROME){
            driverThreadLocal.set(new ChromeDriver());
        } else if(driver == Browser.EDGE) {
            driverThreadLocal.set(new EdgeDriver());
        }else{
            System.out.println("Invalid Browser Name");
        }
    }

    protected BrowserUtility(Browser driver , boolean isHeaded){
        super();
        if(!isHeaded){
            if(driver == Browser.CHROME){
                driverThreadLocal.set(new ChromeDriver());
            } else if(driver == Browser.EDGE) {
                driverThreadLocal.set(new EdgeDriver());
            }else{
                System.out.println("Invalid Browser Name");
            }
        }else {
            if (driver == Browser.CHROME) {
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--headless=new");
                options.addArguments("--window-size=1920,1080");
                driverThreadLocal.set(new ChromeDriver(options));
            } else if (driver == Browser.EDGE) {
                EdgeOptions options = new EdgeOptions();
                options.addArguments("--headless=new");
                options.addArguments("--window-size=1920,1080");
                driverThreadLocal.set(new EdgeDriver());
            } else {
                System.out.println("Invalid Browser Name");
            }
        }
    }

    public void gotoWebsite(String URL){
        driverThreadLocal.get().get(URL);
    }

    public WebDriver getDriver() {
        return driverThreadLocal.get();
    }

    public void maximizaBrowser(){
        driverThreadLocal.get().manage().window().maximize();
    }

    public void clickOn(By locator){
        WebElement ele = driverThreadLocal.get().findElement(locator);
        ele.click();
    }

    public void enterText(By locator , String text){
        WebElement ele = driverThreadLocal.get().findElement(locator);
        ele.sendKeys(text);
    }

    public String getText(By locator){
        WebElement ele = driverThreadLocal.get().findElement(locator);
        return ele.getText();
    }

    public static String takeScreenShot(String name) {

        WebDriver driver = driverThreadLocal.get();

        if (driver == null) {
            System.out.println("⚠️ Driver is null. Screenshot skipped for: " + name);
            return null; // don't crash test
        }

        TakesScreenshot ts = (TakesScreenshot) driver;
        File screenShotData = ts.getScreenshotAs(OutputType.FILE);

        String timestamp = new SimpleDateFormat("yyyyMMdd_HH-mm-ss_SSS").format(new Date());
        String threadId = String.valueOf(Thread.currentThread().getId());

        String path = System.getProperty("user.dir") + "/screenshots/"
                + name + "_" + threadId + "_" + timestamp + ".png";

        try {
            FileUtils.copyFile(screenShotData, new File(path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return path;
    }

    public static void closeBrowser(){
        WebDriver driver = driverThreadLocal.get();
        if (driver != null) {
            driver.quit();
            driverThreadLocal.remove(); // VERY IMPORTANT
        }
    }
}
