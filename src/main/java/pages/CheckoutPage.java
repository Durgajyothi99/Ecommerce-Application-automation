package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutPage {
    WebDriver driver;

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
    }

    By firstNameField = By.id("first-name");
    By lastNameField = By.id("last-name");
    By postalCodeField = By.id("postal-code");
    By continueButton = By.id("continue");

    By finishButton = By.id("finish");
    By confirmationMessage = By.className("complete-header");

    public void enterCustomerInfo(String first, String last, String zip) {
        driver.findElement(firstNameField).sendKeys(first);
        driver.findElement(lastNameField).sendKeys(last);
        driver.findElement(postalCodeField).sendKeys(zip);
        driver.findElement(continueButton).click();
    }

    public void finishCheckout() {
        driver.findElement(finishButton).click();
    }

    public String getConfirmationMessage() {
        return driver.findElement(confirmationMessage).getText();
    }

    public boolean isErrorVisible() {
        return driver.findElements(By.cssSelector("[data-test='error']")).size() > 0;
    }
    public boolean isOnStepOnePage() {
        return driver.getCurrentUrl().contains("checkout-step-one");
    }

    public boolean isOnCompletePage() {
        return driver.getCurrentUrl().contains("checkout-complete");
    }

    public String getSummaryItemName() {
        return driver.findElement(By.className("inventory_item_name")).getText();
    }

    public String getSummaryItemPrice() {
        return driver.findElement(By.className("inventory_item_price")).getText();
    }

}

