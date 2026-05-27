package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import utilities.BrowserUtility;

public final class MyAccountPage extends BrowserUtility {

    public MyAccountPage(WebDriver driver) {
        super(driver);
    }

    private static final By VERIFY_USER_LOCATOR = By.xpath("//a[@title = 'View my customer account']/span");
    private static final By SEARCH_BOX_LOCATOR = By.id("search_query_top");
    private static final By ADD_ADDRESS_LOCATOR = By.xpath("//a[@title='Add my first address']");
    private static final By ACCOUNT_CREATED_SUCCESS_MSG_LOCATOR = By.xpath("//p[@class='alert alert-success']");

    public String visibleText(){
        return getText(VERIFY_USER_LOCATOR);
    }

    public SearchPage searchProduct(String product){
        enterText(SEARCH_BOX_LOCATOR , product);
        enterKeys(SEARCH_BOX_LOCATOR , Keys.ENTER);
        return new SearchPage(getDriver());
    }

    public AddressPage goToAddressPage(){
        clickOn(ADD_ADDRESS_LOCATOR);
        return new AddressPage(getDriver());
    }

    public String verifySuccessMsg(){
        return  getText(ACCOUNT_CREATED_SUCCESS_MSG_LOCATOR);
    }



}
