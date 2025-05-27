package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;

import java.util.*;
import java.util.stream.Collectors;

public class ProductPage {
    WebDriver driver;

    public ProductPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getFirstProductName() {
        return driver.findElement(By.className("inventory_item_name")).getText();
    }

    public String getFirstProductPrice() {
        return driver.findElement(By.className("inventory_item_price")).getText();
    }

    public void clickFirstProduct() {
        driver.findElement(By.className("inventory_item_name")).click();
    }

    public String getProductDetailPageTitle() {
        return driver.findElement(By.className("inventory_details_name")).getText();
    }

    public String getProductDetailDescription() {
        return driver.findElement(By.className("inventory_details_desc")).getText();
    }

    public void sortBy(String visibleText) {
        Select sort = new Select(driver.findElement(By.className("product_sort_container")));
        sort.selectByVisibleText(visibleText);
    }

    public List<Double> getAllProductPrices() {
        List<WebElement> priceElements = driver.findElements(By.className("inventory_item_price"));
        return priceElements.stream()
            .map(e -> Double.parseDouble(e.getText().replace("$", "")))
            .collect(Collectors.toList());
    }

    public List<String> getAllProductNames() {
        List<WebElement> nameElements = driver.findElements(By.className("inventory_item_name"));
        return nameElements.stream()
            .map(WebElement::getText)
            .collect(Collectors.toList());
    }

    public void addProductToCart(String productId) {
        driver.findElement(By.id("add-to-cart-" + productId)).click();
    }

    public String getCartCount() {
        return driver.findElement(By.className("shopping_cart_badge")).getText();
    }
    public void addMultipleProductsToCart(List<String> productIds) {
        for (String id : productIds) {
            driver.findElement(By.id("add-to-cart-" + id)).click();
        }
    }

    public void removeProductFromCart(String productId) {
        driver.findElement(By.id("remove-" + productId)).click();
    }

    public String getAddButtonText(String productId) {
        return driver.findElement(By.id("add-to-cart-" + productId)).getText();
    }

}
