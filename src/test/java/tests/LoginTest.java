package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;

public class LoginTest extends BaseTest {

    @Test
    public void loginWithValidCredentials() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");
        Assert.assertTrue(driver.getCurrentUrl().contains("inventory"));
    }

    @Test
    public void loginWithInvalidPassword() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "wrong_password");
        Assert.assertTrue(loginPage.getErrorMessage().contains("Username and password do not match"));
    }

    @Test
    public void loginWithInvalidUsername() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("invalid_user", "secret_sauce");
        Assert.assertTrue(loginPage.getErrorMessage().contains("Username and password do not match"));
    }

    @Test
    public void loginWithEmptyUsername() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("", "secret_sauce");
        Assert.assertTrue(loginPage.getErrorMessage().contains("Username is required"));
    }

    @Test
    public void loginWithEmptyPassword() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "");
        Assert.assertTrue(loginPage.getErrorMessage().contains("Password is required"));
    }

    @Test
    public void loginWithEmptyFields() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("", "");
        Assert.assertTrue(loginPage.getErrorMessage().contains("Username is required"));
    }

    @Test
    public void loginWithLockedOutUser() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("locked_out_user", "secret_sauce");
        Assert.assertTrue(loginPage.getErrorMessage().contains("Sorry, this user has been locked out"));
    }
    @Test
    public void loginWithCaseSensitiveUsername() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("Standard_User", "secret_sauce");  // Incorrect case
        Assert.assertTrue(loginPage.getErrorMessage().contains("Username and password do not match"));
    }

}

//Trigger test from GitHub webhook


