package com.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class SearchPage extends BasePage {

    // Locators
    private final By searchResultsHeader = By.cssSelector("h1");
    private final By keywordLabel = By.id("keyword");
    private final By searchResultTitles = By.cssSelector("section.product-grid div h3");
    private final By searchResultItems = By.cssSelector("section.product-grid div a");

    public SearchPage(WebDriver driver) {
        super(driver);
    }

    public boolean isSearchHeaderDisplayed() {
        return isElementDisplayed(searchResultsHeader);
    }

    public String getKeywordText() {
        return readText(keywordLabel);
    }

    public List<String> getSearchResultTitles() {
        List<WebElement> elements = findElements(searchResultTitles);
        List<String> titles = new ArrayList<>();
        for (WebElement element : elements) {
            titles.add(element.getText().trim());
        }
        return titles;
    }

    public ProductPage clickProductByTitle(String title) {
        List<WebElement> elements = findElements(searchResultItems);
        for (WebElement element : elements) {
            WebElement titleEl = element.findElement(By.tagName("h3"));
            if (titleEl.getText().trim().equalsIgnoreCase(title)) {
                element.click();
                return new ProductPage(driver);
            }
        }
        throw new RuntimeException("Product with title " + title + " not found in search results.");
    }
}
