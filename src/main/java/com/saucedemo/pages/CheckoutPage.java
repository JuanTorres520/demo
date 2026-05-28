package com.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutPage extends BasePage {

    // Locators supporting both modern Shopify One-Page Checkout and classic multi-step layouts
    private final By emailInput = By.cssSelector("#email, #checkout_email");
    private final By firstNameInput = By.cssSelector("input[name='firstName'], #checkout_shipping_address_first_name");
    private final By lastNameInput = By.cssSelector("input[name='lastName'], #checkout_shipping_address_last_name");
    private final By addressInput = By.cssSelector("input[name='address1'], #checkout_shipping_address_address1");
    private final By cityInput = By.cssSelector("input[name='city'], #checkout_shipping_address_city");
    private final By zipInput = By.cssSelector("input[name='postalCode'], #checkout_shipping_address_zip");
    private final By phoneInput = By.cssSelector("input[name='phone'], #checkout_shipping_address_phone");
    private final By continueButton = By.cssSelector("button[type='submit'], #continue_button");

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    public boolean isCheckoutPageDisplayed() {
        return isElementDisplayed(emailInput) || isElementDisplayed(continueButton);
    }

    public void fillCustomerInformation(String email, String firstName, String lastName, String address, String city, String zip) {
        waitForElementToBeVisible(emailInput);
        writeText(emailInput, email);
        writeText(firstNameInput, firstName);
        writeText(lastNameInput, lastName);
        writeText(addressInput, address);
        writeText(cityInput, city);
        writeText(zipInput, zip);
    }

    public void clickContinue() {
        click(continueButton);
    }
}
