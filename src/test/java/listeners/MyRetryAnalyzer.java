package listeners;

import static constants.Environments.*;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
import utilities.BrowserUtility;
import utilities.JSONUtilities;

public class MyRetryAnalyzer implements IRetryAnalyzer {

    private static final int MAX_NUMBER_OR_ATTEMPTS = 3;
    private int currentAttempts = 1;

    @Override
    public boolean retry(ITestResult iTestResult) {
        if (currentAttempts <= MAX_NUMBER_OR_ATTEMPTS){
            currentAttempts++;
            return true;
        }
        return false;
    }
}
