package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage {
    WebDriver driver;

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getCartItemName() {
        return driver.findElement(By.className("inventory_item_name")).getText();
    }

    public void removeItem(String productId) {
        driver.findElement(By.id("remove-" + productId)).click();
    }

    public void clickCheckout() {
        driver.findElement(By.id("checkout")).click();
    }

    public boolean isOnCheckoutPage() {
        return driver.getCurrentUrl().contains("checkout-step-one");
    }
}
