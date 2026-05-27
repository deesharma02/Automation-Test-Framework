package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utilities.BrowserUtility;

public class AccountRegisterPage extends BrowserUtility {

    private static final By MALE_TITLE_RADIOBUTTON_LOCATOR = By.xpath("//label[@for='id_gender1']");
    private static final By FEMALE_TITLE_RADIOBUTTON_LOCATOR =By.id("uniform-id_gender2");
    private static final By FIRSTNAME_TEXTBOX_LOCATOR = By.id("customer_firstname");
    private static final By LASTNAME_TEXTBOX_LOCATOR = By.id("customer_lastname");
    private static final By PASSWORD_TEXTBOX_LOCATOR = By.id("passwd");
    private static final By DOB_DAYS_LOCATOR = By.id("days");
    private static final By DOB_MONTHS_LOCATOR = By.id("months");
    private static final By DOB_YEARS_LOCATOR = By.id("years");
    private static final By REGISTER_BUTTON_LOCATOR = By.id("submitAccount");

    public AccountRegisterPage(WebDriver driver){
        super(driver);
    }

    public MyAccountPage fillUserDetails(String firstName, String lastName, String password){
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        mouseHoverAction(MALE_TITLE_RADIOBUTTON_LOCATOR);
        clickOn(MALE_TITLE_RADIOBUTTON_LOCATOR);
        enterText(FIRSTNAME_TEXTBOX_LOCATOR,firstName);
        enterText(LASTNAME_TEXTBOX_LOCATOR,lastName);
        enterText(PASSWORD_TEXTBOX_LOCATOR,password);
        selectDropDownValue(DOB_DAYS_LOCATOR,"2");
        selectDropDownValue(DOB_MONTHS_LOCATOR, "12");
        selectDropDownValue(DOB_YEARS_LOCATOR, "1997");
        clickOn(REGISTER_BUTTON_LOCATOR);
        return new MyAccountPage(getDriver());
    }

//    public void clickOnRegisterButton(){
//        clickOn(REGISTER_BUTTON_LOCATOR);
//    }

}
