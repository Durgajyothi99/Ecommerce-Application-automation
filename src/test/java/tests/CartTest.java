package tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.ProductPage;
import pages.CartPage;

public class CartTest extends BaseTest {

    @Test
    public void verifyCartContentsTest() {
        new LoginPage(driver).login("standard_user", "secret_sauce");
        ProductPage productPage = new ProductPage(driver);
        productPage.addProductToCart("sauce-labs-backpack");

        driver.findElement(By.className("shopping_cart_link")).click();

        CartPage cartPage = new CartPage(driver);
        Assert.assertEquals(cartPage.getCartItemName(), "Sauce Labs Backpack", "Cart item mismatch");
    }

    @Test
    public void removeItemFromCartTest() {
        new LoginPage(driver).login("standard_user", "secret_sauce");
        ProductPage productPage = new ProductPage(driver);
        productPage.addProductToCart("sauce-labs-backpack");

        driver.findElement(By.className("shopping_cart_link")).click();
        CartPage cartPage = new CartPage(driver);
        cartPage.removeItem("sauce-labs-backpack");

        // Optional: assert cart badge disappears
        boolean cartBadgeVisible = driver.findElements(By.className("shopping_cart_badge")).size() > 0;
        Assert.assertFalse(cartBadgeVisible, "Cart badge should be gone after removing item");
    }

    @Test
    public void proceedToCheckoutTest() {
        new LoginPage(driver).login("standard_user", "secret_sauce");
        ProductPage productPage = new ProductPage(driver);
        productPage.addProductToCart("sauce-labs-backpack");

        driver.findElement(By.className("shopping_cart_link")).click();
        CartPage cartPage = new CartPage(driver);
        cartPage.clickCheckout();

        Assert.assertTrue(cartPage.isOnCheckoutPage(), "Should be on checkout-step-one page");
    }
}
