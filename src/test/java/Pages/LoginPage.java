package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utilities.BrowserUtility;

public final class LoginPage extends BrowserUtility {

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    private static final By EMAIL_TEXTBOX_LOCATOR =By.id("email");
    private static final By PASSWORD_TEXTBOX_LOCATOR =By.id("passwd");
    private static final By SIGN_IN_BUTTON_LOCATOR =By.id("SubmitLogin");
    private static final By ERROR_MESSAGE_LOCATOR = By.xpath("//div[contains(@class,'alert alert-danger')]/ol/li");
    private static final By NEW_EMAIL_TEXTBOX_LOCATOR = By.id("email_create");
    private static final By CREATE_ACCOUNT_BUTTON_LOCATOR = By.id("SubmitCreate");

    public MyAccountPage doLoginWithCredentials(String email ,String pswd){
        enterText(EMAIL_TEXTBOX_LOCATOR , email);
        enterText(PASSWORD_TEXTBOX_LOCATOR , pswd);
        clickOn(SIGN_IN_BUTTON_LOCATOR);
        return new MyAccountPage(getDriver());
    }

    public LoginPage doLoginWithInvalidCredentials(String email ,String pswd){
        enterText(EMAIL_TEXTBOX_LOCATOR , email);
        enterText(PASSWORD_TEXTBOX_LOCATOR , pswd);
        clickOn(SIGN_IN_BUTTON_LOCATOR);
        return new LoginPage(getDriver());
    }

    public String getErrorText(){
        return BrowserUtility.getText(ERROR_MESSAGE_LOCATOR);
    }

    public AccountRegisterPage enterNewUserEmail(String email){
        enterText(NEW_EMAIL_TEXTBOX_LOCATOR,email);
        clickOn(CREATE_ACCOUNT_BUTTON_LOCATOR);
        return new AccountRegisterPage(getDriver());
    }






}
