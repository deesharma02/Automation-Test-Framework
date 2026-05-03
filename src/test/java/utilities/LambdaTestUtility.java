package utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class LambdaTestUtility {

    public static String hubURL = "https://hub.lambdatest.com/wd/hub";
    private static ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();
    private static ThreadLocal<DesiredCapabilities> capabilitiesThreadLocal = new ThreadLocal<>();

    public static WebDriver intializaLambdaTest(String browser , String testName){
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("browserName", browser);
        capabilities.setCapability("browserVersion", "latest");
        Map<String, Object> ltOptions = new HashMap<>();
        ltOptions.put("user", "dabaangdeepak2");
        ltOptions.put("accessKey", "LT_qTr5XsWZh4X0dMvcZJtWuKbEOHOknY9SoOC7h8z9w1NXGCk");
        ltOptions.put("build", "Selenium 4");
        ltOptions.put("name", testName);
        ltOptions.put("platformName", "Windows 10");
        ltOptions.put("seCdp", true);
        ltOptions.put("selenium_version", "latest");
        capabilities.setCapability("LT:Options", ltOptions);
        capabilitiesThreadLocal.set(capabilities);
        WebDriver driver = null;
        try {
            driver = new RemoteWebDriver(new URL(hubURL), capabilitiesThreadLocal.get());
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        driverThreadLocal.set(driver);
        return driverThreadLocal.get();
//        System.out.println(driver);
    }

    public static void closeBrowser(){
        WebDriver driver = driverThreadLocal.get();
        if (driver != null) {
            driver.quit();
            driverThreadLocal.remove(); // VERY IMPORTANT
        }
    }



}
