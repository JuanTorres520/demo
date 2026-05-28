package com.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductPage extends BasePage {

    // Locators
    private final By productTitle = By.cssSelector("h1[itemprop='name']");
    private final By productPrice = By.cssSelector("span.product-price");
    private final By addToCartButton = By.id("add");
    private final By cartTargetDesktopCount = By.id("cart-target-desktop");

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    public String getProductTitle() {
        return readText(productTitle).trim();
    }

    public String getProductPrice() {
        return readText(productPrice).trim();
    }

    public void clickAddToCart() {
        String initialCount = getCartCount();
        click(addToCartButton);
        // Wait for cart count to update before proceeding
        wait.until(d -> !getCartCount().equals(initialCount));
    }

    public String getCartCount() {
        return readText(cartTargetDesktopCount).trim();
    }

    public CartPage goToCartPage() {
        driver.get(com.saucedemo.utils.ConfigReader.getUrl() + "cart");
        return new CartPage(driver);
    }
}
