package com.saucedemo.tests;

import com.saucedemo.pages.CartPage;
import com.saucedemo.pages.HomePage;
import com.saucedemo.pages.ProductPage;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CartTest extends BaseTest {

    @Test(description = "Verify adding a product to the cart")
    public void testAddProductToCart() {
        System.out.println("[CartTest] Starting test...");
        HomePage homePage = new HomePage(getDriver());
        
        System.out.println("[CartTest] Clicking on Grey jacket link...");
        ProductPage productPage = homePage.clickGreyJacket();
        
        System.out.println("[CartTest] Verifying product title...");
        String expectedTitle = "Grey jacket";
        String expectedPrice = "£55.00";
        
        Assert.assertEquals(productPage.getProductTitle(), expectedTitle, "Product title does not match.");
        String actualPrice = productPage.getProductPrice();
        System.out.println("[CartTest] Product price: " + actualPrice);
        Assert.assertTrue(actualPrice.contains("55.00") || actualPrice.contains("55"), 
                "Product price is incorrect. Expected: " + expectedPrice + " but got: " + actualPrice);
        
        System.out.println("[CartTest] Clicking Add to Cart...");
        productPage.clickAddToCart();
        System.out.println("[CartTest] Added to cart, count now: " + productPage.getCartCount());
        System.out.println("[CartTest] Product page cookies: " + getDriver().manage().getCookies());
        System.out.println("[CartTest] Product page URL: " + getDriver().getCurrentUrl());
        
        System.out.println("[CartTest] Navigating to Cart page...");
        CartPage cartPage = productPage.goToCartPage();
        System.out.println("[CartTest] Navigation to Cart page complete.");
        System.out.println("[CartTest] Cart page cookies: " + getDriver().manage().getCookies());
        System.out.println("[CartTest] Cart page URL: " + getDriver().getCurrentUrl());
        
        System.out.println("[CartTest] Checking if cart is empty...");
        boolean empty = cartPage.isCartEmpty();
        System.out.println("[CartTest] Cart empty status: " + empty);
        
        try {
            System.out.println("[CartTest] Cart HTML: " + getDriver().getPageSource());
        } catch (Exception e) {
            System.out.println("[CartTest] Could not fetch Cart HTML: " + e.getMessage());
        }
        
        Assert.assertFalse(empty, "Shopping cart is empty, but item should have been added.");
        
        System.out.println("[CartTest] Verifying product in cart...");
        boolean inCart = cartPage.isProductInCart(expectedTitle);
        System.out.println("[CartTest] Is product in cart: " + inCart);
        Assert.assertTrue(inCart, "Product '" + expectedTitle + "' was not found in the shopping cart.");
    }
}
