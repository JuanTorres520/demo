package com.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    // Locators
    private final By emailInput = By.id("customer_email");
    private final By passwordInput = By.id("customer_password");
    private final By signInButton = By.cssSelector("form[action*='/account/login'] input[type='submit']");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void login(String email, String password) {
        writeText(emailInput, email);
        writeText(passwordInput, password);
        click(signInButton);
    }
}
