package tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;

public class CheckoutTest extends BaseTest {

    @Test(groups = {"smoke"})
    public void checkoutWithValidInfoTest() {
        new LoginPage(driver).login("standard_user", "secret_sauce");

        ProductPage productPage = new ProductPage(driver);
        productPage.addProductToCart("sauce-labs-backpack");
        driver.findElement(By.className("shopping_cart_link")).click();

        CartPage cartPage = new CartPage(driver);
        cartPage.clickCheckout();

        CheckoutPage checkoutPage = new CheckoutPage(driver);
        checkoutPage.enterCustomerInfo("John", "Doe", "12345");
        checkoutPage.finishCheckout();

        Assert.assertEquals(checkoutPage.getConfirmationMessage(), "Thank you for your order!");
    }

    @Test(groups = {"regression"})
    public void checkoutWithMissingInfoTest() {
        new LoginPage(driver).login("standard_user", "secret_sauce");

        ProductPage productPage = new ProductPage(driver);
        productPage.addProductToCart("sauce-labs-backpack");
        driver.findElement(By.className("shopping_cart_link")).click();

        CartPage cartPage = new CartPage(driver);
        cartPage.clickCheckout();

        CheckoutPage checkoutPage = new CheckoutPage(driver);
        checkoutPage.enterCustomerInfo("", "Doe", "");  // Missing first name and zip

        Assert.assertTrue(checkoutPage.isErrorVisible(), "Error should be visible for missing info");
    }
    @Test
    public void verifyCheckoutPageTitleTest() {
        new LoginPage(driver).login("standard_user", "secret_sauce");
        new ProductPage(driver).addProductToCart("sauce-labs-backpack");
        driver.findElement(By.className("shopping_cart_link")).click();

        new CartPage(driver).clickCheckout();
        CheckoutPage checkoutPage = new CheckoutPage(driver);
        Assert.assertTrue(checkoutPage.isOnStepOnePage(), "Should be on step one checkout page");
    }

    @Test
    public void checkoutWithoutFirstNameTest() {
        new LoginPage(driver).login("standard_user", "secret_sauce");
        new ProductPage(driver).addProductToCart("sauce-labs-backpack");
        driver.findElement(By.className("shopping_cart_link")).click();

        new CartPage(driver).clickCheckout();
        CheckoutPage checkoutPage = new CheckoutPage(driver);
        checkoutPage.enterCustomerInfo("", "Doe", "12345");

        Assert.assertTrue(checkoutPage.isErrorVisible(), "Missing first name should show error");
    }

    @Test
    public void checkoutWithoutLastNameTest() {
        new LoginPage(driver).login("standard_user", "secret_sauce");
        new ProductPage(driver).addProductToCart("sauce-labs-backpack");
        driver.findElement(By.className("shopping_cart_link")).click();

        new CartPage(driver).clickCheckout();
        CheckoutPage checkoutPage = new CheckoutPage(driver);
        checkoutPage.enterCustomerInfo("John", "", "12345");

        Assert.assertTrue(checkoutPage.isErrorVisible(), "Missing last name should show error");
    }

    @Test
    public void checkoutWithoutPostalCodeTest() {
        new LoginPage(driver).login("standard_user", "secret_sauce");
        new ProductPage(driver).addProductToCart("sauce-labs-backpack");
        driver.findElement(By.className("shopping_cart_link")).click();

        new CartPage(driver).clickCheckout();
        CheckoutPage checkoutPage = new CheckoutPage(driver);
        checkoutPage.enterCustomerInfo("John", "Doe", "");

        Assert.assertTrue(checkoutPage.isErrorVisible(), "Missing postal code should show error");
    }

    @Test(groups = {"smoke"})
    public void verifyOrderSummaryTest() {
        new LoginPage(driver).login("standard_user", "secret_sauce");
        ProductPage productPage = new ProductPage(driver);
        productPage.addProductToCart("sauce-labs-backpack");

        driver.findElement(By.className("shopping_cart_link")).click();
        new CartPage(driver).clickCheckout();

        CheckoutPage checkoutPage = new CheckoutPage(driver);
        checkoutPage.enterCustomerInfo("John", "Doe", "12345");

        Assert.assertEquals(checkoutPage.getSummaryItemName(), "Sauce Labs Backpack");
        Assert.assertEquals(checkoutPage.getSummaryItemPrice(), "$29.99");
    }

    @Test
    public void verifyFinishPageURLTest() {
        new LoginPage(driver).login("standard_user", "secret_sauce");
        new ProductPage(driver).addProductToCart("sauce-labs-backpack");
        driver.findElement(By.className("shopping_cart_link")).click();

        new CartPage(driver).clickCheckout();
        CheckoutPage checkoutPage = new CheckoutPage(driver);
        checkoutPage.enterCustomerInfo("John", "Doe", "12345");
        checkoutPage.finishCheckout();

        Assert.assertTrue(checkoutPage.isOnCompletePage(), "Should be on final confirmation page");
    }

}

