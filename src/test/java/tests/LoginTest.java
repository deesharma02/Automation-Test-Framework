package tests;

import Pages.HomePage;
import static constants.Browser.*;

import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.*;
import pojo.User;
import utilities.BrowserUtility;
import utilities.LoggerUtilities;

@Listeners({listeners.TestListeners.class})
public class LoginTest extends BaseTest {

    @Test(description = "Verify Login with valid credential" , dataProviderClass =
            dataProviders.LoginDataProvider.class , dataProvider = "LoginTestDataProvider" , retryAnalyzer = listeners.MyRetryAnalyzer.class)
    public void VerifyLoginTestJSON(User user) {
        String userName =
                homePage.ClickOnSignIn().doLoginWithCredentials(user.getEmail() , user.getPassword()).visibleText();
        Assert.assertEquals(userName , "John Doe");
    }

    @Test(description = "Verify Login with valid credential" , dataProviderClass =
            dataProviders.LoginDataProvider.class , dataProvider = "LoginCsvTestDataProvider" , retryAnalyzer = listeners.MyRetryAnalyzer.class)
    public void VerifyLoginTestCSV(User user) {
        String userName =
                homePage.ClickOnSignIn().doLoginWithCredentials(user.getEmail() , user.getPassword()).visibleText();
        Assert.assertEquals(userName , "John Doe");
    }

    @Test(description = "Verify Login with Invalid credential")
    public void VerifyLoginTestWithInvalidCredentialsV() {
        String userName =
                homePage.ClickOnSignIn().doLoginWithInvalidCredentials("Random@mail.com","RandomPassword").getErrorText();
        Assert.assertEquals(userName , "Authentication failed.");
    }
//




}
