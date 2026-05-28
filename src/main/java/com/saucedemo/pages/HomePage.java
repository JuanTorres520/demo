package com.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {

    // Locators
    private final By logo = By.id("logo");
    private final By searchField = By.id("search-field");
    private final By searchSubmit = By.id("search-submit");
    private final By customerLoginLink = By.id("customer_login_link");
    private final By cartLinkDesktop = By.cssSelector(".toggle-drawer.cart.desktop");
    
    // Individual products
    private final By greyJacketLink = By.id("product-1");
    private final By noirJacketLink = By.id("product-2");
    private final By stripedTopLink = By.id("product-3");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public boolean isLogoDisplayed() {
        return isElementDisplayed(logo);
    }

    public SearchPage searchProduct(String query) {
        writeText(searchField, query);
        click(searchSubmit);
        return new SearchPage(driver);
    }

    public LoginPage clickLogin() {
        click(customerLoginLink);
        return new LoginPage(driver);
    }

    public ProductPage clickGreyJacket() {
        click(greyJacketLink);
        return new ProductPage(driver);
    }

    public ProductPage clickNoirJacket() {
        click(noirJacketLink);
        return new ProductPage(driver);
    }

    public ProductPage clickStripedTop() {
        click(stripedTopLink);
        return new ProductPage(driver);
    }

    public void openCartDrawer() {
        click(cartLinkDesktop);
    }
}
