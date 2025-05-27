package tests;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.ProductPage;

public class ProductTest extends BaseTest {
	
	@BeforeMethod
	public void loginBeforeEachTest() {
	    LoginPage loginPage = new LoginPage(driver);
	    loginPage.login("standard_user", "secret_sauce");
	}
	@Test
    public void verifyProductNameAndPrice() {
    
        ProductPage productPage = new ProductPage(driver);

        Assert.assertEquals(productPage.getFirstProductName(), "Sauce Labs Backpack");
        Assert.assertEquals(productPage.getFirstProductPrice(), "$29.99");
    }

    @Test
    public void verifyProductDetailsPage() {

        ProductPage productPage = new ProductPage(driver);

        productPage.clickFirstProduct();
        Assert.assertTrue(driver.getCurrentUrl().contains("inventory-item"));
        Assert.assertFalse(productPage.getProductDetailDescription().isEmpty(), "Description should be visible");
    }

    @Test
    public void sortProductsByPriceLowToHigh() {
    
        ProductPage productPage = new ProductPage(driver);

        productPage.sortBy("Price (low to high)");
        List<Double> prices = productPage.getAllProductPrices();
        List<Double> sorted = new ArrayList<>(prices);
        Collections.sort(sorted);
        Assert.assertEquals(prices, sorted, "Prices not sorted low to high");
    }

    @Test
    public void sortProductsByPriceHighToLow() {
        ProductPage productPage = new ProductPage(driver);

        productPage.sortBy("Price (high to low)");
        List<Double> prices = productPage.getAllProductPrices();
        List<Double> sorted = new ArrayList<>(prices);
        sorted.sort(Collections.reverseOrder());
        Assert.assertEquals(prices, sorted, "Prices not sorted high to low");
    }

    @Test
    public void sortProductsByNameAZ() {
        ProductPage productPage = new ProductPage(driver);

        productPage.sortBy("Name (A to Z)");
        List<String> names = productPage.getAllProductNames();
        List<String> sorted = new ArrayList<>(names);
        Collections.sort(sorted);
        Assert.assertEquals(names, sorted, "Names not sorted A to Z");
    }

    @Test
    public void sortProductsByNameZA() {
    	ProductPage productPage = new ProductPage(driver);

        productPage.sortBy("Name (Z to A)");
        List<String> names = productPage.getAllProductNames();
        List<String> sorted = new ArrayList<>(names);
        sorted.sort(Collections.reverseOrder());
        Assert.assertEquals(names, sorted, "Names not sorted Z to A");
    }

    @Test
    public void verifyCartIconUpdates() {
    
        ProductPage productPage = new ProductPage(driver);

        productPage.addProductToCart("sauce-labs-backpack");
        Assert.assertEquals(productPage.getCartCount(), "1", "Cart icon should show count 1");
    }

    @Test
    public void addMultipleProductsToCartTest() {
    	ProductPage productPage = new ProductPage(driver);
        List<String> productIds = Arrays.asList("sauce-labs-backpack", "sauce-labs-bike-light");
        productPage.addMultipleProductsToCart(productIds);

        String cartCount = productPage.getCartCount();
        Assert.assertEquals(cartCount, String.valueOf(productIds.size()), "Cart count should match number of products added");
    }

    @Test
    public void removeProductFromCartTest() {
  
        ProductPage productPage = new ProductPage(driver);

        productPage.addProductToCart("sauce-labs-backpack");
        productPage.removeProductFromCart("sauce-labs-backpack");

        // Re-locate the button after removal (to avoid stale element)
        String buttonText = driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).getText();
        Assert.assertEquals(buttonText, "Add to cart", "Button should revert back to 'Add to cart'");
    }

    


}
