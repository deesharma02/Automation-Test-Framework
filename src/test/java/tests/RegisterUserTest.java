package tests;

import Pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pojo.RegisterUser;
import utilities.RegisterUserFakerUtility;

public class RegisterUserTest extends BaseTest{

    public static LoginPage loginpage;
    private RegisterUser registerUser;

    @BeforeMethod
    public void setup(){
        loginpage = homePage.ClickOnSignIn();
        registerUser = RegisterUserFakerUtility.getFakerRegisterUser();
    }

    @Test(description = "Register New User")
    public void registerUser(){
        String msg = loginpage.enterNewUserEmail(registerUser.getEmail())
                .fillUserDetails(registerUser.getFirstName(),registerUser.getLastName(),registerUser.getPassword())
                .verifySuccessMsg();
        Assert.assertEquals(msg , "Your account has been created.");
    }

}
