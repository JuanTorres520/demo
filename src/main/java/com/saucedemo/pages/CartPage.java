package com.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class CartPage extends BasePage {

    // Locators
    private final By emptyCartMsg = By.xpath("//section[@id='cart']//p[contains(text(), 'empty') or contains(text(), 'Empty')]");
    private final By cartForm = By.cssSelector("form[action='/cart']");
    private final By cartItems = By.cssSelector("form[action='/cart'] table tr, form[action='/cart'] div.item");
    private final By productLinks = By.cssSelector("form[action='/cart'] a[href*='/products/']");
    private final By checkoutButton = By.name("checkout");

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public boolean isCartEmpty() {
        WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(4));
        try {
            shortWait.until(d -> isElementDisplayedNoWait(emptyCartMsg) || isElementDisplayedNoWait(cartForm));
        } catch (Exception e) {
            // Ignore timeout
        }
        return isElementDisplayedNoWait(emptyCartMsg) && !isElementDisplayedNoWait(cartForm);
    }

    public List<String> getCartItemTitles() {
        List<WebElement> elements = driver.findElements(productLinks);
        List<String> titles = new ArrayList<>();
        for (WebElement element : elements) {
            String text = element.getText().trim();
            if (!text.isEmpty() && !titles.contains(text)) {
                titles.add(text);
            }
        }
        return titles;
    }

    public boolean isProductInCart(String productTitle) {
        if (isCartEmpty()) {
            return false;
        }
        List<String> items = getCartItemTitles();
        for (String item : items) {
            if (item.toLowerCase().contains(productTitle.toLowerCase())) {
                return true;
            }
        }
        return false;
    }

    public CheckoutPage clickCheckout() {
        click(checkoutButton);
        return new CheckoutPage(driver);
    }
}
