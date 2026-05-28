package com.saucedemo.tests;

import com.saucedemo.pages.CartPage;
import com.saucedemo.pages.CheckoutPage;
import com.saucedemo.pages.HomePage;
import com.saucedemo.pages.ProductPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CheckoutTest extends BaseTest {

    @Test(description = "Verify checkout page loading and customer info entry")
    public void testCheckoutFlow() {
        System.out.println("[CheckoutTest] Starting checkout test...");
        HomePage homePage = new HomePage(getDriver());

        System.out.println("[CheckoutTest] Selecting Grey jacket...");
        ProductPage productPage = homePage.clickGreyJacket();

        System.out.println("[CheckoutTest] Adding to cart...");
        productPage.clickAddToCart();

        System.out.println("[CheckoutTest] Navigating to Cart...");
        CartPage cartPage = productPage.goToCartPage();

        System.out.println("[CheckoutTest] Verifying cart items...");
        Assert.assertFalse(cartPage.isCartEmpty(), "Cart should not be empty.");

        System.out.println("[CheckoutTest] Clicking checkout button...");
        CheckoutPage checkoutPage = cartPage.clickCheckout();

        System.out.println("[CheckoutTest] Verifying checkout page is displayed...");
        Assert.assertTrue(checkoutPage.isCheckoutPageDisplayed(), "Checkout page was not displayed.");

        System.out.println("[CheckoutTest] Filling customer shipping details...");
        checkoutPage.fillCustomerInformation(
                "john.doe@example.com",
                "John",
                "Doe",
                "123 Main Street",
                "London",
                "SW1A 1AA"
        );

        System.out.println("[CheckoutTest] Details filled out successfully. Verification complete.");
    }
}
